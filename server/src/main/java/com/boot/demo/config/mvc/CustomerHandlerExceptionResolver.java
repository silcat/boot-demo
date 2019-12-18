package com.boot.demo.config.mvc;

import com.alibaba.fastjson.JSON;
import com.boot.demo.common.Result;
import com.boot.demo.common.ResultStatus;
import com.renrendai.loan.ucredit.common.exception.UcreditException;
import com.boot.demo.config.filter.RequestWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 统一异常处理类
 */
@Slf4j
public class CustomerHandlerExceptionResolver extends AbstractHandlerExceptionResolver {
    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) {
       //包装异常结果
        Result result = this.parseException(e);
        RequestWrapper requestWrapper = (RequestWrapper) request;
        //打印异常结果
        String message;
        if (handler instanceof HandlerMethod) {
            message = String.format("errorRespond: url:%s, param:%s,errorCause：%s",
                    requestWrapper.getRequestURI(),
                    requestWrapper.getPayload(),
                    result.getMsg());
        } else {
            message = e.getMessage();
        }
        log.error(message, e);

        //返回异常结果
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(200);
        try {
            response.getWriter().write(JSON.toJSONString(result));
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }

        return new ModelAndView();
    }
    /**
     * 处理异常
     * @param e
     * @return
     */
    private Result parseException(Exception e) {
        Result result = new Result();
        //业务失败的异常
        if (e instanceof UcreditException) {
            result = Result.builder()
                    .code(((UcreditException) e).getErrorCode())
                    .msg(((UcreditException) e).getErrorMsg())
                    .build();
            //URL请求错误异常
        } else if (e instanceof NoHandlerFoundException) {
            result = Result.builder()
                    .code(ResultStatus.GATEWAY_ERROR.code)
                    .msg(ResultStatus.GATEWAY_ERROR.msg)
                    .build();
            //参数校验错误异常
        } else if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException e1 = (MethodArgumentNotValidException) e;
            String defaultMessage = e1.getBindingResult().getAllErrors().get(0).getDefaultMessage();
            result = Result.builder()
                    .code(ResultStatus.MISS_PARAM.code)
                    .msg(defaultMessage)
                    .build();
        } else if (e instanceof BindException) {
            String fieldName = ((BindException) e).getFieldErrors().get(0).getField();
            String defaultMessage  = ((BindException) e).getFieldErrors().get(0).getDefaultMessage();
            result = Result.builder()
                    .code(ResultStatus.MISS_PARAM.code)
                    .msg(fieldName+defaultMessage)
                    .build();
            //系统内部异常
        } else if (e instanceof ServletException) {
            result = Result.builder()
                    .code(ResultStatus.SERVER_UNKNOW_ERROR.code)
                    .msg(ResultStatus.SERVER_UNKNOW_ERROR.msg)
                    .build();
            //其他异常
        } else {
            result = Result.builder()
                    .code(ResultStatus.FAILED.code)
                    .msg(ResultStatus.FAILED.msg)
                    .build();
        }
        return result;
    }
}
