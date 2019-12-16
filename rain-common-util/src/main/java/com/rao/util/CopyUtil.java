package com.rao.util;

import com.rao.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : hudelin
 * @className :CopyUtil
 * @description : 对象属性复制工具类
 * @date: 2019-12-16 15:39
 */
public class CopyUtil {

    /**
     * 字段属性一致的列表复制
     * @param source
     * @param targetClazz
     * @param <T>
     * @param <S>
     * @return
     * @throws Exception
     */
    public static  <T, S> List<T> transToOList(List<S> source, Class<T> targetClazz) {
        return source.stream().map(item -> {
            T target = transToO(item, targetClazz);
            return target;
        }).collect(Collectors.toList());
    }

    /**
     * 字段属性一致的对象复制
     * @param source
     * @param targetClazz
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S> T transToO(S source, Class<T> targetClazz) {
        if (source == null) {
            return null;
        }
        T target = null;
        try {
            target = targetClazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw BusinessException.operate("系统异常，实例化失败");
        }
        BeanUtils.copyProperties(source, target);
        return target;
    }

}
