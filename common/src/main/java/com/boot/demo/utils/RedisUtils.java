package com.boot.demo.utils;

import java.util.Random;

/**
 * Created by sunlichuan on 18-6-5
 */
public class RedisUtils {

    public static final String GRAY_INSTITUTION_OFFER_OPEN = "offer_open";
    public static final String UNLOCK_LUA;
    public static final String LOCK_SUCCESS = "OK";
    public static final String SET_IF_NOT_EXIST = "NX";
    public static final String SET_WITH_EXPIRE_TIME = "PX";
    static {
        StringBuilder buffer = new StringBuilder();
        buffer.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        buffer.append("then ");
        buffer.append("    return redis.call(\"del\",KEYS[1]) ");
        buffer.append("else ");
        buffer.append("    return 0 ");
        buffer.append("end ");
        UNLOCK_LUA = buffer.toString();
    }

    public static String getRedisLockKey(String prefix, String key) {
        return prefix + "_" + key;
    }


    public static String contactLong(String prefix, Long suffix) {
        return prefix + "_" + suffix;
    }
    public static String contactInteger(String prefix, Integer suffix) {
        return prefix + "_" + suffix;
    }

    public static String contactString(String... prefix) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < prefix.length; i++) {
            builder.append(prefix[i]);
            if (i < prefix.length - 1) {
                builder.append("_");
            }
        }
        return builder.toString();
    }

    public static long random(int day) {
        Random random = new Random(System.currentTimeMillis());
        return day * 86400L + random.nextInt(86400);
    }
}
