package com.rao.Utils.common;

import org.springframework.beans.factory.annotation.Value;

/**
 * Created by Lenovo on 2018/9/10.
 */
public class TwiterIdUtil {

    @Value("1")
    private static int node;
    public static final int NODE_SHIFT = 10;
    public static final int SEQ_SHIFT = 12;

    public static final short MAX_NODE = 1024;
    public static final short MAX_SEQUENCE = 4096;

    private static short sequence;
    private static long referenceTime;


    private static synchronized long next() {

        long currentTime = System.currentTimeMillis();
        long counter;

        synchronized (TwiterIdUtil.class) {

            if (currentTime < referenceTime) {
                throw new RuntimeException(String.format("Last referenceTime %s is after reference time %s", referenceTime, currentTime));
            } else if (currentTime > referenceTime) {
                sequence = 0;
            } else {
                if (sequence < MAX_SEQUENCE) {
                    sequence++;
                } else {
                    throw new RuntimeException("Sequence exhausted at " + sequence);
                }
            }
            counter = sequence;
            referenceTime = currentTime;
        }

        return currentTime << NODE_SHIFT << SEQ_SHIFT | node << SEQ_SHIFT | counter;
    }

    public static Long getTwiterId() {
        return next();
    }


    /**
     * 随机生成指定数
     * @param len
     * @return
     */
    public String getRandomCode(Integer len) {
        //字符源，可以根据需要删减
        String generateSource = "23456789abcdefghgklmnpqrstuvwxyz";//去掉1和i ，0和o
        String rtnStr = "";
        for (int i = 0; i < len; i++) {
            //循环随机获得当次字符，并移走选出的字符
            String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
            rtnStr += nowStr;
            generateSource = generateSource.replaceAll(nowStr, "");
        }
        return rtnStr.toUpperCase();
    }


    public static void main(String[] args) {
        System.out.println(getTwiterId());
    }


}
