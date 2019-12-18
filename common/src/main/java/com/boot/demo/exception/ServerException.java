package com.boot.demo.exception;

import com.boot.demo.common.ResultStatus;
import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ServerException extends RuntimeException {
    private static final long serialVersionUID = 2195068675053227207L;

    private int errorCode = ResultStatus.SERVER_UNKNOW_ERROR.code;

    private String errorMsg = "unknown error";

    public ServerException() {
        super();
    }

    public ServerException(String message, int errorCode, String errorMsg) {
        super(message);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    public ServerException(int code, String message) {
        super();
        this.errorCode = code;
        this.errorMsg = message;
    }

    public ServerException(String message, ResultStatus status) {
        super(message);
        this.errorCode = status.code;
        this.errorMsg = StringUtils.isEmpty(message) ? status.msg : message;
    }


    public ServerException(ResultStatus errorInfo) {
        this(errorInfo.getCode(), errorInfo.getMsg());
    }

    public ServerException(ResultStatus errorInfo, String message) {
        this(message, errorInfo);
    }


    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public static String getErrorInfoFromException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "ErrorInfoFromException";
        } finally {
            try {
                sw.close();
                pw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
