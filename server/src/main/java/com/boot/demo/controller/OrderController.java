package com.boot.demo.controller;

import com.boot.demo.bean.Order;
import com.boot.demo.bean.User;
import com.boot.demo.common.Result;
import com.boot.demo.common.ResultGenerator;
import com.boot.demo.common.ResultStatus;
import com.boot.demo.mapper.StockMapper;
import com.boot.demo.service.IOrderService;
import com.boot.demo.service.UserService;
import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(value = "order")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @RequestMapping("update")
    Result update(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("status") Integer status){
        return ResultGenerator.genSuccessResult();
    }
    @RequestMapping("create")
    Result create(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money, @RequestParam("status") Integer status){
        return ResultGenerator.genFailResult(ResultStatus.DB_ERROR);
    }
}
