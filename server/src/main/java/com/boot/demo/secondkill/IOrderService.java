package com.boot.demo.secondkill;

public interface IOrderService {
    void createOrderWithLimitAndRedisAndKafka(int sid);
    void createOrderWithLimitAndRedisWatchAndKafka(int sid);
}
