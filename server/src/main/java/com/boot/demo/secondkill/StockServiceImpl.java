package com.boot.demo.secondkill;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.demo.mapper.StockMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class StockServiceImpl implements StockService {
    @Resource
    private StockMapper stockMapper;


    @Override
    public int getStockCount(int id) {
        Stock stock = stockMapper.selectById(id);
        return stock.getCount();
    }

    @Override
    public Stock getStockById(int id) {
        return stockMapper.selectById(id);
    }


    @Override
    public int updateStockByOptimistic(Stock stock) {
        Wrapper<Stock> queryWrapper = Wrappers.<Stock>query()
                .lambda()
                .eq(Stock::getId,stock.getId())
                .eq(Stock::getVersion, stock.getVersion());
        stock.setCount(stock.getCount()-1);
        stock.setVersion(stock.getVersion()+1);
        stock.setSale(stock.getSale()+1);
        return stockMapper.update(stock,queryWrapper);
    }

    @Override
    public int initDBBefore() {
        Stock stock = Stock.builder()
                .count(5)
                .sale(0)
                .version(0)
                .name("手机").build();
        return stockMapper.insert(stock);
    }
}
