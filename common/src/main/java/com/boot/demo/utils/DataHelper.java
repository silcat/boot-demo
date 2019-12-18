package com.boot.demo.utils;


public class DataHelper {
    private static final ThreadLocal<String> creditLendRequestIdHolder = new ThreadLocal<String>();


    private static final ThreadLocal<Long> uidHolder = new ThreadLocal<Long>();

    public static String getCreditLendRequestId() {
        return creditLendRequestIdHolder.get();
    }

    public static Long getUid() {
        return uidHolder.get();
    }

    public static void setUid(long uid) {
        uidHolder.set(uid);
    }

    public static void setCreditLenRequestId(String creditLenRequestId) {
        creditLendRequestIdHolder.set(creditLenRequestId);
    }

}
