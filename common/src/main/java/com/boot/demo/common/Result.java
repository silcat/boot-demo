package com.boot.demo.common;

import com.boot.demo.utils.RES_STATUS;
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


    public Result(RES_STATUS status) {
        super();
        this.code = status.code;
        this.msg = status.msg;
    }

    public Result(RES_STATUS status, String message) {
        super();
        this.code = status.code;
        this.msg = message;
    }

    public Result(int code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public Result(T data, RES_STATUS status) {
        this(status);
        this.data = data;
    }

}
