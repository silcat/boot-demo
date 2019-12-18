package com.boot.demo.config.filter;

import javax.servlet.http.HttpServletRequest;

public class TraceIdHttpServletRequestWrapper extends RequestWrapper {

    private String traceId;

    public TraceIdHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

}
