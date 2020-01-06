package com.rao.util.wx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rao.dto.WxUserInfo;
import com.rao.exception.BusinessException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.util.StringUtils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 微信小程序工具类
 * @author raojing
 * @date 2020/1/6 15:45
 */
public class WxAppletUtils {

    /**
     * 获取微信小程序 session_key 和 openid
     *
     * @param code
     * @return
     */
    public static JSONObject getSessionKeyOropenid(String code) {
        //微信端登录code值
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        String appId = "wxec5f0e6389a02a23";
        String appSecret = "1304a531102040177d5465d19162f062";
        String grantType = "authorization_code";

        Map<String, String> requestUrlParam = new HashMap<String, String>();
        // 开发者设置中的appId
        requestUrlParam.put("appid", appId);
        // 开发者设置中的appSecret
        requestUrlParam.put("secret", appSecret);
        // //小程序调用wx.login返回的code
        requestUrlParam.put("js_code", code);
        // //默认参数-授权码模式 authorization_code
        requestUrlParam.put("grant_type", grantType);
        JSONObject jsonObject = JSON.parseObject(sendPost(requestUrl, requestUrlParam));
        return jsonObject;
    }


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url
     * @param paramMap
     * @return
     */
    private static String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";

        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();

        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    /**
     * 解密用户敏感数据获取用户信息
     *
     * @return
     */
    public static WxUserInfo getUserInfo(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据
        byte[] dataByte = Base64.decodeBase64(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64.decodeBase64(sessionKey);
        // 偏移量
        byte[] ivByte = Base64.decodeBase64(iv);
        try {

            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return praseUserInfoJson(JSON.parseObject(result));
            }
            return null;
        } catch (Exception e) {
            throw BusinessException.operate(e.getMessage());
        }
    }


    private static WxUserInfo praseUserInfoJson(JSONObject jsonObject) {
        WxUserInfo user = null;
        String phoneNumber = jsonObject.getString("phoneNumber");
        if (!StringUtils.isEmpty(phoneNumber)) {
            user = new WxUserInfo();
            user.setPhoneNumber(phoneNumber);
            user.setPurePhoneNumber(jsonObject.getString("purePhoneNumber"));
            user.setAvatarUrl(jsonObject.getString("avatarUrl"));
            user.setNickName(jsonObject.getString("nickName"));
            user.setGender(jsonObject.getString("gender"));
            user.setCountryCode(jsonObject.getString("countryCode"));
        }
        return user;
    }

    public static void main(String[] args) {
        String encryptedData = "Gd1HEeaBNhSBMvL+PKymiMKi75SdF8A3T5YxoYQ0jWxVvvr7q/5UR8+0dlfmT+tqq84xAAx04yi/hZxnVaGF1FHMzHGcuJAujyQxEX5px4cynf7uHOQh1/LpCpz7bdz431Kr+GiyWbD7ro9rGU4yJceQpr4Qfpi96QhXesg4TbJMUUc0V7qItco5uyn6MzMTy6UPUc/tXH+P4zh5vG14OQ==";
        String iv = "1RZhNUP2hH7qFvJc2SMaRw==";

        JSONObject sessionKeyOropenid = WxAppletUtils.getSessionKeyOropenid("0230Qiup025n6q1V0evp0d9fup00Qiuf");
        String session_key = sessionKeyOropenid.getString("session_key");
        System.out.println(sessionKeyOropenid);
        System.out.println(session_key);
        System.out.println("=====================================================================");
        WxUserInfo userInfo = WxAppletUtils.getUserInfo(encryptedData, session_key, iv);
        System.out.println(userInfo);

    }

}
