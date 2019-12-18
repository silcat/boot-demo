package com.boot.demo.config.filter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import static com.boot.demo.config.common.TraceIdUtil.HTTP_TRACE_ID;

public class TraceIdHttpServletRespondWrapper extends HttpServletResponseWrapper {

    private String traceId;

    public TraceIdHttpServletRespondWrapper(HttpServletResponse response) {
        super(response);
    }
    public String getTraceId() {
        return traceId;
    }
    public void setTraceId(String traceId) {
        this.traceId = traceId;
        super.setHeader(HTTP_TRACE_ID,traceId);
    }

}
