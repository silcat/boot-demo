package com.boot.demo.exception;


import com.boot.demo.common.ResultStatus;

/**
 * Created by baomingli on 2018/8/22.
 */
public class ThirdPartyRequestException extends ServerException {


    public ThirdPartyRequestException(ResultStatus status) {
        super(status);
    }


    public ThirdPartyRequestException() {
        super(ResultStatus.THIRD_PARTY_ERROR);
    }

    public ThirdPartyRequestException(String message) {
        super(ResultStatus.THIRD_PARTY_ERROR, message);
    }

}
