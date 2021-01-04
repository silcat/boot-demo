package com.boot.demo.secondkill;

import com.boot.demo.config.common.RedisUtils;
import com.boot.demo.limit.ILimitService;
import com.boot.demo.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private ILimitService limitService;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private StockService stockService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void createOrderWithLimitAndRedisAndKafka(int sid) {
        if (!limitService.limit()){
            throw new RuntimeException("请求数量达到最大限制");
        }
        Stock stock = checkStockWithRedis(sid);
        //异步更新库存（实际通过消息队列）
        saleStockOptimsticWithRedis(stock);

    }
    private Stock checkStockWithRedis(int sid) {
        Integer count = Integer.parseInt(redisService.get(RedisUtils.STOCK_COUNT + sid));
        Integer sale = Integer.parseInt(redisService.get(RedisUtils.STOCK_SALE + sid));
        Integer version = Integer.parseInt(redisService.get(RedisUtils.STOCK_VERSION + sid));
        if (count < 1) {
            log.info("库存不足");
            throw new RuntimeException("库存不足 Redis currentCount: " + sale);
        }
        Stock stock = new Stock();
        stock.setId(sid);
        stock.setCount(count);
        stock.setSale(sale);
        stock.setVersion(version);
        // 此处应该是热更新，但是在数据库中只有一个商品，所以直接赋值
        stock.setName("手机");
        return stock;
    }
    @Async
    public void saleStockOptimsticWithRedis(Stock stock) {
        int res = stockService.updateStockByOptimistic(stock);
        if (res == 0) {
            throw new RuntimeException("并发更新库存失败");
        }
        // 更新 Redis
        StockWithRedis.updateStockWithRedis(stock);
    }
    public  void updateStockWithRedis(Stock stock) {
        redisTemplate.opsForHash().multiGet()
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis();
            // 开始事务
            Transaction transaction = jedis.multi();

            // 事务操作
            Integer count = Integer.parseInt(redisService.get(RedisUtils.STOCK_COUNT + sid));
            Integer sale = Integer.parseInt(redisService.get(RedisUtils.STOCK_SALE + sid));
            Integer version = Integer.parseInt(redisService.get(RedisUtils.STOCK_VERSION + sid));
            // 结束事务
            List<Object> list = transaction.exec();
        } catch (Exception e) {
            log.error("updateStock 获取 Jedis 实例失败：", e);
        } finally {
            RedisPool.jedisPoolClose(jedis);
        }
    }

}
