package com.chaoszc.order.utils;

import org.apache.commons.lang.math.RandomUtils;

import java.util.Random;

public class KeyUtil {
    /**
     * 生成唯一主键
     * 格式：时间戳+随机数
     */
    public static synchronized String genUniqueKey(){
        Random random =new Random();
        Integer number = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }
}
