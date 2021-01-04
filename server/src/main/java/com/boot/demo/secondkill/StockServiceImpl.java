package com.boot.demo.secondkill;



import org.springframework.stereotype.Service;

/**
 * @auther G.Fukang
 * @date 6/7 12:45
 */
@Service
public class StockServiceImpl implements StockService {


    @Override
    public int getStockCount(int id) {
        Stock stock = stockMapper.selectByPrimaryKey(id);
        return stock.getCount();
    }

    @Override
    public Stock getStockById(int id) {

        return stockMapper.selectByPrimaryKey(id);
    }


    @Override
    public int updateStockByOptimistic(Stock stock) {

        return stockMapper.updateByOptimistic(stock);
    }

    @Override
    public int initDBBefore() {

        return stockMapper.initDBBefore();
    }
}
