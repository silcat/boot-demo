package com.boot.demo.config.mvc;

import io.seata.core.context.RootContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SeataHandlerInterceptor implements HandlerInterceptor {
    @Override
   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String xid = request.getHeader(RootContext.KEY_XID);
        if (StringUtils.isNotBlank(xid)) {
            RootContext.bind(xid);
            System.out.println("feign xid：" + xid);
        }
        return true;
    }
}
