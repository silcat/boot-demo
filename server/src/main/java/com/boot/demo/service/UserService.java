package com.boot.demo.service;



import com.boot.demo.bean.User;

import java.util.List;

/**
 * @Description: 用户相关接口
 *
 * @author xub
 * @date 2019/8/24 下午6:32
 */
public interface UserService {

    /**
     *  获取所有用户信息
     */
    User list(Long uid);

    /**
     *  批量 保存用户信息
     * @param userVOList
     */
    String  insertForeach(User userVOList);

}