package com.boot.demo.config.common;


public class RedisUtils {
    /**
     * 库存值
     */
    public final static String STOCK_COUNT = "stock_count_";

    /**
     * 库存值
     */
    public final static String STOCK_SALE = "stock_sale_";

    /**
     * 库存值
     */
    public final static String STOCK_VERSION = "stock_version_";
    /**
     * 分布式锁
     */
    public static final String UNLOCK_LUA;
    /**
     * 限流器
     */
    public static final String LIMIT_LUA;

    static {
        StringBuilder buffer = new StringBuilder();
        buffer.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        buffer.append("then ");
        buffer.append("    return redis.call(\"del\",KEYS[1]) ");
        buffer.append("else ");
        buffer.append("    return 0 ");
        buffer.append("end ");
        UNLOCK_LUA = buffer.toString();
        StringBuilder bufferLimit = new StringBuilder();
        bufferLimit.append("local key = KEYS[1]");
        bufferLimit.append("local limit = tonumber(ARGV[1]) ");
        bufferLimit.append("local currentLimit = tonumber(redis.call('get', key) or \"0\")");
        bufferLimit.append("if currentLimit + 1 > limit then");
        bufferLimit.append("return 0; ");
        bufferLimit.append("else");
        bufferLimit.append("redis.call(\"INCRBY\", key, 1)");
        bufferLimit.append("redis.call(\"EXPIRE\", key, 2)");
        bufferLimit.append("return currentLimit + 1");
        bufferLimit.append("end");
        LIMIT_LUA = buffer.toString();

    }


}
