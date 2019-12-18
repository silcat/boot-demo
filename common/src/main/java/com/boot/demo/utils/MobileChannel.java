package com.boot.demo.utils;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wudi on 2018/6/1
 */
public enum MobileChannel {
    MOBILE("中国移动"),
    UNICOM("中国联通"),
    TELECOM("中国电信"),
    MVNO("虚拟运营商"),
    ;

    private String desc;
    MobileChannel(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static MobileChannel getChannel(String mobile) {
        String prefix = mobile.substring(0, 3);
        if (MOBILEPREFIX.contains(prefix)) {
            return MOBILE;
        }
        if (UNICOMPREFIX.contains(prefix)) {
            return UNICOM;
        }
        if (TELECOMPREFIX.contains(prefix)) {
            return TELECOM;
        }
        prefix = mobile.substring(0, 4);
        if (MOBILEPREFIX.contains(prefix)) {
            return MOBILE;
        }
        if (UNICOMPREFIX.contains(prefix)) {
            return UNICOM;
        }
        if (TELECOMPREFIX.contains(prefix)) {
            return TELECOM;
        }
        return MVNO;
    }

    /**
     * 移动号段：
     * 134 135 136 137 138 139 147 148 150 151 152 157 158 159 172 178 182 183 184 187 188 198 1705
     * 联通号段：
     * 130 131 132 145 146 155 156 166 171 175 176 185 186 1709
     * 电信号段：
     * 133 149 153 173 174 177 180 181 189 199 1700
     * 虚拟运营商:
     * 170
     */
    private final static List<String> MOBILEPREFIX = new ArrayList<>();
    private final static List<String> UNICOMPREFIX = new ArrayList<>();
    private final static List<String> TELECOMPREFIX = new ArrayList<>();
    static {
        MOBILEPREFIX.add("134");
        MOBILEPREFIX.add("135");
        MOBILEPREFIX.add("136");
        MOBILEPREFIX.add("137");
        MOBILEPREFIX.add("138");
        MOBILEPREFIX.add("139");
        MOBILEPREFIX.add("147");
        MOBILEPREFIX.add("148");
        MOBILEPREFIX.add("150");
        MOBILEPREFIX.add("151");
        MOBILEPREFIX.add("152");
        MOBILEPREFIX.add("157");
        MOBILEPREFIX.add("158");
        MOBILEPREFIX.add("159");
        MOBILEPREFIX.add("172");
        MOBILEPREFIX.add("178");
        MOBILEPREFIX.add("182");
        MOBILEPREFIX.add("183");
        MOBILEPREFIX.add("184");
        MOBILEPREFIX.add("187");
        MOBILEPREFIX.add("188");
        MOBILEPREFIX.add("198");
        MOBILEPREFIX.add("1705");

        UNICOMPREFIX.add("130");
        UNICOMPREFIX.add("131");
        UNICOMPREFIX.add("132");
        UNICOMPREFIX.add("145");
        UNICOMPREFIX.add("146");
        UNICOMPREFIX.add("155");
        UNICOMPREFIX.add("156");
        UNICOMPREFIX.add("166");
        UNICOMPREFIX.add("167");
        UNICOMPREFIX.add("171");
        UNICOMPREFIX.add("175");
        UNICOMPREFIX.add("176");
        UNICOMPREFIX.add("185");
        UNICOMPREFIX.add("186");
        UNICOMPREFIX.add("1709");

        TELECOMPREFIX.add("133");
        TELECOMPREFIX.add("149");
        TELECOMPREFIX.add("153");
        TELECOMPREFIX.add("173");
        TELECOMPREFIX.add("174");
        TELECOMPREFIX.add("177");
        TELECOMPREFIX.add("180");
        TELECOMPREFIX.add("181");
        TELECOMPREFIX.add("189");
        TELECOMPREFIX.add("191");
        TELECOMPREFIX.add("199");
        TELECOMPREFIX.add("1700");
    }


    public static String getOperatorByMobile(String mobile) {
        MobileChannel channel = getChannel(mobile);
        return channel.getDesc();
    }

    public static String generateEmail(String mobile) {
        MobileChannel channel = getChannel(mobile);
        switch (channel) {
            case MOBILE:
                return mobile + "@139.com";
            case UNICOM:
                return mobile + "@wo.cn";
            case TELECOM:
                return mobile + "@189.cn";
        }
        throw new InvalidParameterException("手机号码错误!");
    }

}
