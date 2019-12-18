package com.boot.demo.common;

/**
 * 统一响应类
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        return  Result.builder()
                .code(ResultStatus.SUCCESS.code)
                .msg(DEFAULT_SUCCESS_MESSAGE)
                .build();
    }

    public static <T> Result<T> genSuccessResult(T data) {
        Result result = new Result();
        result.setCode(ResultStatus.SUCCESS.code);
        result.setMsg(ResultStatus.SUCCESS.msg);
        result.setData(data);
        return result;
    }

    public static Result genFailResult(ResultStatus ResultStatus) {
        return genResult(ResultStatus.code,ResultStatus.msg);
    }

    public static Result genFailResult(ResultStatus ResultStatus,String msg) {
        return genResult(ResultStatus.code,msg);
    }

    public static Result genResult(int code ,String message) {
        return  Result.builder()
                .code(code)
                .msg(message)
                .build();
    }

}
