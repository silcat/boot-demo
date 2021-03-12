package com.boot.demo.service;

import com.boot.demo.bean.Order;
import com.boot.demo.bean.Test;

/**
 * 用户的收货地址 服务类
 *
 * @author dengdegao
 * @since 2020-03-27
 */
public interface IOrderService {

    void save(Order order);
    void update(Order order);
}
