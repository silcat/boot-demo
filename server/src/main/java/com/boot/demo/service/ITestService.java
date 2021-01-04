package com.boot.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.boot.demo.bean.Test;

/**
 * 用户的收货地址 服务类
 *
 * @author dengdegao
 * @since 2020-03-27
 */
public interface ITestService {

    Test getByUid(Long uid);
    void save(Long uid);
    void update(Long uid);
}
