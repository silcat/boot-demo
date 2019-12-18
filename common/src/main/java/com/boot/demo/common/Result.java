package com.boot.demo.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一API响应结果封装
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {

    /**
     * 对外返回的对象
     */
    private T data;

    /**
     * 返回状态码
     */
    private int code = ResultStatus.SERVER_UNKNOW_ERROR.code;

    /**
     * 返回消息
     */
    private String msg = ResultStatus.SERVER_UNKNOW_ERROR.msg;



    public Result(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }


}
