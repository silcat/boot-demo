package com.boot.demo.utils;


import com.boot.demo.common.Result;
import com.boot.demo.common.ResultStatus;
import com.boot.demo.exception.ServerException;
import lombok.extern.slf4j.Slf4j;

/**
 * @author baomingli
 * Created by baomingli on 2018/10/8.
 */
@Slf4j
public class FeignResultUtil {
    public static <T> T getResult(Result<T> result, ResultStatus... status) {
        if (ResultStatus.SUCCESS.getCode() != result.getCode()) {
            throw new ServerException(ResultStatus.REMOTE_SERVER_ERROR);
        }
        return result.getData();
    }

    public static <T> T getResult(Result<T> result, boolean isNull) {
        if (ResultStatus.SUCCESS.getCode() != result.getCode()) {
            if (isNull) {
                throw new ServerException(ResultStatus.REMOTE_SERVER_ERROR);
            }
            return null;
        }
        return result.getData();
    }
}
