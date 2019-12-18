package com.boot.demo.config.common;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.slf4j.MDC;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.Callable;

@JsonAutoDetect(
        fieldVisibility = JsonAutoDetect.Visibility.ANY
)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class TraceIdUtil {
    public static final String HTTP_TRACE_ID = "REQUEST_ID";
    public static final String SPAN_ID_NAME = "X-B3-SpanId";
    public static final String TRACE_ID_NAME = "X-B3-TraceId";
    public static final Random random  = new Random();
    static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 生成traceId
     * @param id
     * @return
     */
    public static String genTraceId(long id) {
        char[] data = new char[16];
        writeHexLong(data, 0, id);
        return new String(data);
    }
    public static String genTraceId() {
        return genTraceId(random.nextLong());
    }
    static void writeHexLong(char[] data, int pos, long v) {
        writeHexByte(data, pos + 0, (byte)((int)(v >>> 56 & 255L)));
        writeHexByte(data, pos + 2, (byte)((int)(v >>> 48 & 255L)));
        writeHexByte(data, pos + 4, (byte)((int)(v >>> 40 & 255L)));
        writeHexByte(data, pos + 6, (byte)((int)(v >>> 32 & 255L)));
        writeHexByte(data, pos + 8, (byte)((int)(v >>> 24 & 255L)));
        writeHexByte(data, pos + 10, (byte)((int)(v >>> 16 & 255L)));
        writeHexByte(data, pos + 12, (byte)((int)(v >>> 8 & 255L)));
        writeHexByte(data, pos + 14, (byte)((int)(v & 255L)));
    }
    static void writeHexByte(char[] data, int pos, byte b) {
        data[pos + 0] = HEX_DIGITS[b >> 4 & 15];
        data[pos + 1] = HEX_DIGITS[b & 15];
    }
    /**
     * 保存traceId至MDC
     * @param
     * @return
     */
    public static void setTraceId() {
        MDC.put(TRACE_ID_NAME, genTraceId());
    }
    public static void setTraceId(String traceId) {
        MDC.put(TRACE_ID_NAME, traceId);
    }
    public static String getTraceId() {
        return MDC.get(TRACE_ID_NAME);
    }
    public static void remove() {
        MDC.remove(TRACE_ID_NAME);
    }

    /**
     * 线程池设置traceId
     * @param callable
     * @param context
     * @param <T>
     * @return
     */
    public static <T> Callable<T> wrap(final Callable<T> callable, final Map<String, String> context) {
        return new Callable<T>() {
            @Override
            public T call() throws Exception {
                if (context == null) {
                    MDC.clear();
                } else {
                    MDC.setContextMap(context);
                }
                if (MDC.get(TRACE_ID_NAME) == null) {
                    MDC.put(TRACE_ID_NAME, genTraceId());
                }
                try {
                    return callable.call();
                } finally {
                    MDC.clear();
                }
            }
        };
    }

    public static Runnable wrap(final Runnable runnable, final Map<String, String> context) {
        return () -> {
            if (context == null) {
                MDC.clear();
            } else {
                MDC.setContextMap(context);
            }
            if (MDC.get(TRACE_ID_NAME) == null) {
                MDC.put(TRACE_ID_NAME, genTraceId());
            }
            try {
                runnable.run();
            } finally {
                MDC.clear();
            }
        };
    }
}
