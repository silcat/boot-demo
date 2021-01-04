package com.boot.demo.limit;

import com.boot.demo.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * redis限流器
 */
@Slf4j
@Service
public class RedisLimitService implements ILimitService {
    @Autowired
    private IRedisService iRedisService;

    @Override
    public  Boolean limit() {
        return  limit("5");

    }
    @Override
    public Boolean limit(String limit) {
        Object result = null;
        try {
            // 请求限流
            String key = String.valueOf(System.currentTimeMillis() / 1000);
            Boolean success = iRedisService.limit(key,limit);
            // 计数限流
            if (success) {
                log.info("成功获取令牌");
                return true;
            }
        } catch (Exception e) {
            log.error("获取令牌失败", e);
        }
        return false;
    }
}
