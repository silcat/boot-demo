package com.boot.demo.secondkill;

import com.boot.demo.config.common.RedisUtils;
import com.boot.demo.limit.ILimitService;
import com.boot.demo.service.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

import static org.apache.poi.hssf.record.ExternSheetRecord.sid;

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
    private Boolean isFinish = false;

    /**
     * redis乐观锁
     * @param sid
     */
    @Override
    public void createOrderWithLimitAndRedisWatchAndKafka(int sid) {
        if (!limitService.limit()){
            throw new RuntimeException("请求数量达到最大限制");
        }
        //异步更新库存（实际通过消息队列）
        Stock stock = updateStockWithRedisWatch(sid);
        boolean fail = true;
        //异步
        do {
            int i = stockService.updateStockByOptimistic(stock);
            if (i!=0){
                fail = false;
            }
        }while (!fail);

    }

    /**
     *  数据库乐观锁
     * @param sid
     */

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
        if (count < 1 || isFinish) {
            log.info("库存不足");
            isFinish = true;
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
        updateStockWithRedis(stock);
    }
    public  void updateStockWithRedis(Stock stock) {
        try {
            if(isFinish) {
                throw new RuntimeException("秒杀结束");
            }
            // 开始事务
            redisTemplate.setEnableTransactionSupport(true);
            redisTemplate.multi();
            // 事务操作
            redisService.decr(RedisUtils.STOCK_COUNT + sid);
            redisService.incr(RedisUtils.STOCK_SALE + sid);
            redisService.incr(RedisUtils.STOCK_VERSION + sid);

            // 结束事务
            redisTemplate.exec();
        } catch (Exception e) {
            log.error("updateStock 失败：", e);
        }
    }
    public  Stock updateStockWithRedisWatch(int sid) {
        Stock stock1 = new Stock();
        if(isFinish) {
            throw new RuntimeException("秒杀结束");
        }
        //监视商品库存key
        redisTemplate.watch(RedisUtils.STOCK_COUNT);
        String count = redisService.get(RedisUtils.STOCK_COUNT + sid);
        if(Integer.valueOf(count)  < 1){
            isFinish = true;
            System.out.println("库存不足");
            redisTemplate.unwatch();
            throw new RuntimeException("秒杀结束");
        }

        // 开始事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.multi();
        // 事务操作
        redisService.decr(RedisUtils.STOCK_COUNT + sid);
        redisService.incr(RedisUtils.STOCK_SALE + sid);
        redisService.incr(RedisUtils.STOCK_VERSION + sid);
        stock1.setId(sid);
        stock1.setCount( Integer.parseInt(redisService.get(RedisUtils.STOCK_COUNT + sid)));
        stock1.setSale(Integer.parseInt(redisService.get(RedisUtils.STOCK_SALE + sid)));
        stock1.setVersion(Integer.parseInt(redisService.get(RedisUtils.STOCK_VERSION + sid)));
        // 结束事务
        List exec = redisTemplate.exec();
        if (!Optional.ofNullable(exec).isPresent()){
            throw new RuntimeException("事务执行失败：获取watch锁失败");
            //重试或放入MQ等重试
        }
        return stock1;
    }

}
