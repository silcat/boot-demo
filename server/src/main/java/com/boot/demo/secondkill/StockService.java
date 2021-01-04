package com.boot.demo.secondkill;


/**
 * @auther G.Fukang
 * @date 6/7 12:35
 */
public interface StockService {

    /**
     * 根据 id 获取剩余库存
     * @param id
     * @return int
     */
    int getStockCount(int id);

    /**
     * 根据 id 查询剩余库存信息
     * @param id
     * @return stock
     */
    Stock getStockById(int id);


    /**
     * 乐观锁更新库存，解决超卖问题
     */
    int updateStockByOptimistic(Stock stock);

    /**
     * 初始化数据库
     */
    int initDBBefore();
}
