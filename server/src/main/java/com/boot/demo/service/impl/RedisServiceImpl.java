package com.boot.demo.service.impl;

import com.boot.demo.config.common.RedisUtils;
import com.boot.demo.service.IRedisService;
import io.netty.util.CharsetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.*;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
@ConditionalOnClass(name = "org.springframework.data.redis.core.RedisTemplate")
public class RedisServiceImpl implements IRedisService {

    @Autowired
    private RedisTemplate<String, String> template;

    @Override
    public Boolean acquireLock(String key, String resource, long second) {
        // 加锁
        return template.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.stringCommands()
                        .set(key.getBytes(),
                                resource.getBytes(),
                                Expiration.seconds(second),
                                RedisStringCommands.SetOption.SET_IF_ABSENT);
            }
        });
//        if (RedisUtils.LOCK_SUCCESS.equals(result)) {
//            return Boolean.TRUE;
//        }
//        return Boolean.FALSE;
    }

    @Override
    public Boolean releaseLock(String key, String resource) {
        // 调用lua脚本去删除，只能删除自己添加的分布式锁
        return template.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.scriptingCommands()
                        .eval(RedisUtils.UNLOCK_LUA.getBytes(),
                                ReturnType.BOOLEAN,
                                1,
                                key.getBytes(),
                                resource.getBytes());
            }
        });
    }

    //////// map


    @Override
    public void hPutAll(String key, Map<String, String> valueMap) {
        template.opsForHash().putAll(key, valueMap);
    }

    @Override
    public void hPut(String key, String filed, String value) {
        template.opsForHash().put(key, filed, value);
    }

    @Override
    public void hDel(String key, List<String> valueKeyList) {
        template.opsForHash().delete(key, valueKeyList);
    }

    @Override
    public String hGet(String key, String filed) {
        HashOperations<String, String, String> operations = template.opsForHash();
        return operations.get(key, filed);
    }

    @Override
    public void hSet(String key, String filed, String value) {
        template.opsForHash().put(key, filed, value);
    }

    @Override
    public void hSet(String key, String filed, BigDecimal amount) {
        template.opsForHash().put(key, filed, amount.toPlainString());
    }

    @Override
    public void hSet(String key, String filed, Long count) {
        template.opsForHash().put(key, filed, count.toString());
    }

    @Override
    public BigDecimal hIncByDouble(String key, String filed, BigDecimal delta) {
        HashOperations<String, String, Double> operations = template.opsForHash();
        double result = operations.increment(key, filed, delta.doubleValue());
        return new BigDecimal(result + "");
    }

    @Override
    public Long hIncByLong(String key, String filed, Long delta) {
        return template.opsForHash().increment(key, filed, delta);
    }

    @Override
    public Long sAdd(String key, String value) {
        return template.opsForSet().add(key, value);
    }

    @Override
    public Boolean sIsMember(String key, String value) {
        return template.opsForSet().isMember(key, value);
    }

    @Override
    public Long sCard(String key) {
        return template.opsForSet().size(key);
    }

    @Override
    public Long sRem(String key, String filed) {
        return template.opsForSet().remove(key, filed);
    }

    @Override
    public Boolean zadd(String key, double score, String member) {
        return template.opsForZSet().add(key, member, score);
    }

    @Override
    public Long zcard(String key) {
        return template.opsForZSet().zCard(key);
    }

    @Override
    public Long zcount(String key, double min, double max) {
        return template.opsForZSet().count(key, min, max);
    }

    @Override
    public Long zremrangebyscore(String key, double min, double max) {
        return template.opsForZSet().removeRangeByScore(key, min, max);
    }

    @Override
    public Set<String> zRange(String key) {
        return template.opsForZSet().range(key, 0, -1);
    }

    @Override
    public Boolean del(String key) {
        return template.delete(key);
    }

    @Override
    public String get(String key) {
        return template.boundValueOps(key).get();
    }

    @Override
    public void set(String key, String value) {
        template.boundValueOps(key).set(value);
    }

    @Override
    public void setex(String key, long seconds, String value) {
        template.boundValueOps(key).set(value,seconds,TimeUnit.SECONDS);
    }

    @Override
    public Boolean getBitSwitch(String key, Long offset) {
        return template.opsForValue().getBit(key, offset);
    }

    @Override
    public Boolean setBitSwitch(String key, Long offset, boolean value) {
        return template.opsForValue().setBit(key, offset, value);
    }

    @Override
    public Boolean limit(String key, String limit) {
        return template.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.scriptingCommands()
                        .eval(RedisUtils.LIMIT_LUA.getBytes(),
                                ReturnType.BOOLEAN,
                                1,
                                key.getBytes(),
                                limit.getBytes());
            }
        });
    }

    @Override
    public void incr(String key) {
        Long increment = template.boundValueOps(key).increment();

    }

    @Override
    public void decr(String key) {
        Long decrement = template.boundValueOps(key).decrement();
    }

    @Override
    public void publis(String key,String string) {
        template.convertAndSend(key, string.getBytes(CharsetUtil.UTF_8));

    }
}
