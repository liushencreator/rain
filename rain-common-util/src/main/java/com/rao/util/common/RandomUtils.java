package com.rao.util.common;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


/**
 * 随机数工具类
 *
 * @author raojing
 * @date 2020/1/6 15:45
 */
public class RandomUtils {
    private final static String ALL_CHAR = "abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    private final static String LETTER_CHAR = "abcdefghigklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String NUMBER_CHAR = "0123456789";
    
    private final static String DATE_FORMAT = "yyyyMMddHHmmssSSSS";

    /**
     * 获取 时间字符串 + 随机的字符串(包含大小写字母和数字)
     *
     * @param length 出去时间以外的字符串长度，dateFormat 时间格式(默认为yyyyMMddHHmmssSSSS)
     * @return
     */
    public static String randomStringWithTime(int length, String dateFormat) {
        if (StringUtils.isEmpty(dateFormat)) {
            dateFormat = DATE_FORMAT;
        }
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dateFormat);
        return df.format(date) + randomString(length, ALL_CHAR);
    }

    /**
     * 获取随机的字符串(只包含大小写字母)
     *
     * @param length 字符串长度
     * @return
     */
    public static String randomLetterString(int length) {
        return randomString(length, LETTER_CHAR);
    }

    /**
     * 获取随机的字符串(只包含数字)
     *
     * @param length 字符串长度
     * @return
     */
    public static String randomNumberString(int length) {
        return randomString(length, NUMBER_CHAR);
    }

    /**
     * 获取随机的字符串
     *
     * @param length    字符串长度
     * @param charRange 字符范围
     * @return
     */
    private static String randomString(int length, String charRange) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(charRange.charAt(random.nextInt(charRange.length())));
        }
        return sb.toString();
    }

}
