package com.rao.util.cache;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis 操作工具类
 *
 * @author raojing
 * @date 2020/1/6 17:35
 */
@Component
public class RedisTemplateUtils {

    public static String UNLOCK_LUA = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 获取分布式锁
     *
     * @param lockKey
     * @param requestId
     * @param expire
     * @return
     */
    public boolean tryGetDistributedLock(String lockKey, String requestId, long expire) {
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) (connection) -> {
            return connection.set(lockKey.getBytes(Charset.forName("UTF-8")), requestId.getBytes(Charset.forName("UTF-8")), Expiration.seconds(expire), RedisStringCommands.SetOption.SET_IF_ABSENT);
        });
    }

    /**
     * 释放分布式锁
     *
     * @param lockKey
     * @param requestId
     * @return
     */
    public boolean releaseDistributedLock(String lockKey, String requestId) {
        return (Boolean) redisTemplate.execute((RedisCallback<Boolean>) (connection) -> {
            return connection.eval(UNLOCK_LUA.getBytes(), ReturnType.BOOLEAN, 1, lockKey.getBytes(Charset.forName("UTF-8")), requestId.getBytes(Charset.forName("UTF-8")));
        });
    }

    /*********************************** string ***********************************/
    /**
     * 自增1
     *
     * @param key
     * @return
     */
    public Long incr(String key) {
        return incrBy(key, 1);
    }

    /**
     * 自减1
     *
     * @param key
     * @return
     */
    public Long decr(String key) {
        return decrBy(key, 1);
    }

    /**
     * 自减 decrement
     *
     * @param key
     * @param decrement
     * @return
     */
    public Long decrBy(String key, long decrement) {
        return redisTemplate.opsForValue().increment(key, -decrement);
    }

    /**
     * 自增 decrement
     *
     * @param key
     * @param increment
     * @return
     */
    public Long incrBy(String key, long increment) {
        return redisTemplate.opsForValue().increment(key, increment);
    }

    /**
     * 删除key
     *
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 删除Keys
     *
     * @param keySet
     */
    public void delete(Set<String> keySet) {
        redisTemplate.delete(keySet);
    }

    /**
     * 获取所有符合通配符为pattern的Key
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存 key=Obj
     *
     * @param key
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T getObject(String key, Class<T> clz) {
        String json = get(key);
        return StringUtils.isEmpty(json) ? null : JSONObject.parseObject(json, clz);
    }

    /**
     * 获取集合
     *
     * @param key
     * @param clz
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T> List<T> getList(String key, Class<T> clz) {
        String json = get(key);
        return StringUtils.isEmpty(json) ? null : JSONObject.parseArray(json, clz);
    }

    /**
     * 是否存在key
     *
     * @param key
     * @return
     */
    public boolean isExists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 获取key的过期时间 （秒）
     *
     * @param key
     * @return
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存的有效时间
     *
     * @param key
     * @param expire
     * @return
     */
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置缓存（并设置有效时间）
     *
     * @param key
     * @param value
     * @param expire
     */
    public void set(String key, String value, long expire) {
        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);
    }

    /**
     * 保存集合
     *
     * @param key
     * @param obj
     * @throws Exception
     */
    public void setObject(String key, Object obj) {
        set(key, JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue));
    }

    /**
     * 缓存 key=Obj sec秒
     *
     * @param key
     * @param obj
     * @param expire
     * @throws Exception
     */
    public void setObject(String key, Object obj, long expire) {
        String value = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        set(key, value, expire);
    }
    /*********************************** string ***********************************/

    /*********************************** list ***********************************/
    /**
     * 将obj存入列表key头部
     *
     * @param key
     * @param obj
     * @return
     */
    public Long lPush(String key, Object obj) {
        String value = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        return redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 将obj存入列表key尾部
     *
     * @param key
     * @param obj
     * @return
     * @throws Exception
     */
    public Long rPush(String key, Object obj) throws Exception {
        String value = JSONObject.toJSONString(obj, SerializerFeature.WriteMapNullValue);
        return redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @param key
     * @param value
     */
    public void lPush(String key, List<String> value) {
        redisTemplate.opsForList().leftPushAll(key, value);
    }

    /**
     * 将list放入缓存 并设置有效时间
     *
     * @param key
     * @param value
     * @param expire
     */
    public void lPush(String key, List<String> value, long expire) {
        redisTemplate.opsForList().leftPushAll(key, value);
        if (expire > 0) {
            expire(key, expire);
        }
        ;
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key
     * @param index
     * @param value
     */
    public void lUpdateIndex(String key, long index, String value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除并返回列表key的头元素(头部检索)
     *
     * @param key
     * @return
     */
    public String lPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 移除并返回列表key的头元素（尾部检索）
     *
     * @param key
     * @return
     */
    public String rPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 移除N个值为value
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    public Long lRemove(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, value);
        } catch (Exception e) {
            return 0L;
        }
    }

    /**
     * 获取list缓存的内容
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> lGet(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key
     * @param index
     * @return
     */
    public Object lGetIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 获取队列长度
     *
     * @param key
     * @return
     */
    public Long lLen(String key) {
        Long result = redisTemplate.opsForList().size(key);
        return result;
    }
    /*********************************** list ***********************************/

    /*********************************** hash ***********************************/
    /**
     * 保存hash类型
     *
     * @param key
     * @param params
     */
    public void hSet(String key, Map<String, String> params) {
        redisTemplate.opsForHash().putAll(key, params);
    }

    /**
     * 保存hash类型,并设置时间
     *
     * @param key
     * @param params
     * @param expire
     */
    public void hSet(String key, Map<String, String> params, long expire) {
        redisTemplate.opsForHash().putAll(key, params);
        if (expire > 0) {
            this.expire(key, expire);
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key
     * @param item
     * @param value
     */
    public void hSet(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key
     * @param item
     * @param value
     * @param expire
     */
    public void hSet(String key, String item, Object value, long expire) {
        redisTemplate.opsForHash().put(key, item, value);
        if (expire > 0) {
            expire(key, expire);
        }
    }

    /**
     * 删除hash表中的值
     *
     * @param key
     * @param item
     */
    public void hDel(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key
     * @param item
     * @return
     */
    public boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double hIncr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key
     * @param item
     * @param by
     * @return
     */
    public double hDecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 获取整个hash
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hmGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 获取hash的某个属性
     *
     * @param key
     * @param item
     * @return
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }
    /*********************************** hash ***********************************/

    /*********************************** set ***********************************/
    /**
     * 根据key获取Set中的所有值
     *
     * @param key
     * @return
     */
    public Set<String> sGet(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key
     * @param value
     * @return
     */
    public boolean sHasKey(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key
     * @param values
     * @return
     */
    public void sSet(String key, String... values) {
        redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存，并设置过期时间
     *
     * @param key
     * @param expire
     * @param values
     */
    public void sSet(String key, long expire, String... values) {
        redisTemplate.opsForSet().add(key, values);
        if (expire > 0) {
            expire(key, expire);
        }
    }

    /**
     * 获取set缓存的长度
     *
     * @param key
     * @return
     */
    public Long sLen(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值为value的
     *
     * @param key
     * @param values
     * @return
     */
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }
    /*********************************** set ***********************************/

}
