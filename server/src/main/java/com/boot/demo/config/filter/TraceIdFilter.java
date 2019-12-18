package com.boot.demo.config.filter;

import com.boot.demo.config.common.TraceIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * @author yangtianfeng
 */
@Component
@Slf4j
public class TraceIdFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        TraceIdHttpServletRequestWrapper traceIdResquestWrapper = new TraceIdHttpServletRequestWrapper(request);
        String requestId = request.getHeader(TraceIdUtil.HTTP_TRACE_ID);
        String traceId = "";
        if (StringUtils.isNotBlank(requestId)) {
            traceIdResquestWrapper.setTraceId(requestId);
            traceId = requestId;
        } else {
            Random random = new Random();
            traceId = TraceIdUtil.genTraceId(random.nextLong());
            traceIdResquestWrapper.setTraceId(traceId);
        }
        TraceIdHttpServletRespondWrapper traceIdRespondWrapper = new TraceIdHttpServletRespondWrapper(httpServletResponse);
        traceIdRespondWrapper.setTraceId(traceId);
        TraceIdUtil.setTraceId(traceId);
        filterChain.doFilter(traceIdResquestWrapper, traceIdRespondWrapper);
    }

    @Override
    public void destroy() {

    }
}
