package com.boot.demo.exception;


import com.boot.demo.common.ResultStatus;

/**
 * @author baomingli
 */
public class InvalidRequestException extends RuntimeException {

    private ResultStatus status;


    public InvalidRequestException(ResultStatus status) {
        super();
        this.status = status;
    }

    public ResultStatus getStatus() {
        return status;
    }
}
