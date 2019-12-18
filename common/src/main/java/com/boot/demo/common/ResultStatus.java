package com.boot.demo.common;

import lombok.Getter;

@Getter
public enum ResultStatus {
    /**
     * 未知错误
     */
    FAILED(-1, "未知错误"),
    /**
     * SUCCESS
     */
    SUCCESS(0, "SUCCESS"),
    SERVER_UNKNOW_ERROR(500, "服务器开小差了,请稍后再试"),
    GATEWAY_ERROR(1500, "gateway捕获异常"),
    REMOTE_SERVER_ERROR(502, "远程服务调用失败"),
    MISS_PARAM(400, "参数异常"),
    DB_ERROR(10000, "DB操作失败"),
    PARAM_NOT_EXIST(1002, "必填字段不存在"),
    THIRD_PARTY_ERROR(1102, "服务器开小差了，请稍后重试"),
    SERVICE_OFF(2, "非常抱歉，今晚20:00至明日08:00，系统升级维护"),
    SERVER_UNKONW_ERROR(500, "服务器开小差了,请稍后再试"),
    SIGN_VALIDATE_ERROR(1100, "验签失败"),
    FILE_IO_ERROR(1101, "文件操作异常"),
    PARAM_VALUE_ERROR(1104, "参数错误"),
    REQUEST_ERROR(1105, "错误请求"),
    ;

    ResultStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public final int code;
    public final String msg;
    public static boolean isSuccess(int code) {
        return code == ResultStatus.SUCCESS.code;
    }


    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


}
