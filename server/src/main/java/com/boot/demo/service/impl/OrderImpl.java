package com.boot.demo.service.impl;



import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.boot.demo.bean.Order;
import com.boot.demo.bean.Test;
import com.boot.demo.mapper.OrderMapper;
import com.boot.demo.mapper.TestMapper;

import com.boot.demo.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;


@Slf4j
@Service
public class OrderImpl implements IOrderService {
    @Resource
    private OrderMapper orderMapper;


    @Override
    public void save(Order order) {
        orderMapper.insert(order);
        if (order.getUserId()== -1){
          throw new RuntimeException("数据错误");
        }
    }

    @Override
    public void update(Order order) {
        Order order1 = getByUid(order.getUserId());
        if (Optional.ofNullable(order1).isPresent()) {
            order.setLoanId(order1.getLoanId());
            order.setUserId(null);
            orderMapper.updateById(order);
        }
    }


    private Order getByUid(Long uid) {
        Wrapper<Order> queryWrapper = Wrappers.<Order>query()
                .lambda()
                .eq(Order::getUserId, uid)
                .last("limit 1");
        return orderMapper.selectOne(queryWrapper);
    }

}
