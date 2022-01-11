package com.boot.demo.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IRedisService {

    /**
     * redis锁
     */
    Boolean acquireLock(String key, String resource, long expired);

    /**
     * redis解锁
     */
    Boolean releaseLock(String key, String resource);

    /**
     * 设置key的数据
     */
    void hPutAll(String key, Map<String, String> valueMap);

    /**
     * hash set key value
     */
    void hPut(String key, String filed, String value);

    /**
     * 删除对应的key
     */
    void hDel(String key, List<String> valueKeyList);

    /**
     * 获取对应的数据
     */
    String hGet(String key, String filed);
    void hSet(String key, String filed, String value);
    void hSet(String key, String filed, BigDecimal amount);
    void hSet(String key, String filed, Long count);

    /**
     * 增加指定的数值
     */
    BigDecimal hIncByDouble(String key, String filed, BigDecimal delta);

    /**
     * 增加指定数值
     */
    Long hIncByLong(String key, String filed, Long delta);


    //////// set

    /**
     * set添加元素
     */
    Long sAdd(String key, String value);

    /**
     * 是否set元素
     */
    Boolean sIsMember(String key, String value);

    /**
     * set长度
     */
    Long sCard(String key);

    /**
     * 移除redis数据
     */
    Long sRem(String key, String filed);

    //////// zset

    /**
     * 添加数据到zset
     */
    Boolean zadd(String key, double score, String member);

    /**
     * 统计zset的基数
     */
    Long zcard(String key);

    /**
     * 计算zset的数量
     */
    Long zcount(String key, double min, double max);

    /**
     * 移除zset中min--max之间的记录
     */
    Long zremrangebyscore(String key, double min, double max);

    /**
     * 获取zset内容
     */
    Set<String> zRange(String key);

    /**
     * 删除redis key
     */
    Boolean del(String key);


    String get(String key);

    void set(String key, String serviceFee);

    void setex(String key, long seconds, String toString);

    /**
     * 获取对应的bit位开关
     */
    Boolean getBitSwitch(String key, Long offset);

    /**
     * 设置对应的bit位
     */
    Boolean setBitSwitch(String key, Long offset, boolean value);

    Boolean limit(String key, String limit);
    void incr(String key);
    void decr(String key);
    void publis(String key,String string);
}
