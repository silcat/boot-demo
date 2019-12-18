package com.boot.demo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final String dt = "yyyyMMddHHmmss";
    public static final String ds = "yyyy-MM-dd";

    public static final String formate = "yyyy-MM-dd HH:mm:ss";
    public static final int MINUTE_SECONDS = 60;
    public static final int HOUR_SECONDS = 60 * MINUTE_SECONDS;
    public static final int DAY_SECONDS = 24 * HOUR_SECONDS;

    /**
     * 格式化日期
     */
    public static String getFormatDate(Date date, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    public static String getFormatDate(long timeMillis, String format) {
        Date d = new Date(timeMillis);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(d);
    }

    public static String getDateTime() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dt);
        return df.format(date);
    }

    public static String getCurrentDate() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(ds);
        return df.format(date);
    }


    /**
     * string 转 date
     * @param dateTimeStr
     * @return
     */
    public static Date stringConvertToDate(String dateTimeStr) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, df);
        return localDateTimeToDate(dateTime);
    }
    /**
     * date 转 LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateConvertToLocalDateTime(Date date) {
        String dateTimeStr = "2018-07-28 14:11:15";
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }
    /**
     * LocalDateTime 转date
     * @param localDate
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDate){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atZone(zone).toInstant();
        return Date.from(instant);
    }
    public static Date subtractDay(Date beginTime, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(beginTime);
        cal.add(Calendar.DATE, -days);

        return cal.getTime();
    }

    /**
     * 获取字段
     */
    public static Integer getField(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String getDiffHour(String beginTime, Date currTime) {
        if (beginTime == null || beginTime.equals("") || beginTime.trim().equals("")) {
            return "";
        }
        try {
            Date tmpTime = sdf.parse(beginTime);
            double value = (currTime.getTime() - tmpTime.getTime()) / 3600000.0;
            return String.format("%.1f", value);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static int getDiffNow(Date beginTime) {
        ZonedDateTime ldt1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(beginTime.getTime()), ZoneId.systemDefault());
        ZonedDateTime ldt2 = ZonedDateTime.now();
        return new Long(ChronoUnit.DAYS.between(ldt1, ldt2)).intValue();
    }


    public static int getDiffDay(Date beginTime, Date endTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTime);
        LocalDate beginLd = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

        calendar.setTime(endTime);
        LocalDate endLd = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));

        return new Long(ChronoUnit.DAYS.between(beginLd, endLd)).intValue();
    }

    /***
     * 功能描述：日期转换cron表达式时间格式
     *
     * @param date
     * @param dateFormat : e.g:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String formatDateByPattern(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String formatTimeStr = null;
        if (date != null) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     * convert Date to cron ,eg.  "14 01 17 22 07 ? 2017"
     *
     * @param date:时间点
     * @return
     */
    public static String getCron(Date date) {
        String dateFormat = "ss mm HH * * ?";
        return formatDateByPattern(date, dateFormat);
    }

    public static Date getFirstBillDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, getBillDay(date));
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static int getBillDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int billDay;
        if (day >= 1 && day <= 19) {
            billDay = day;
        } else if (day >= 20 && day <= 22) {
            billDay = 20;
        } else if (day >= 23 && day <= 25) {
            billDay = 21;
        } else if (day >= 26 && day <= 28) {
            billDay = 22;
        } else {
            billDay = 23;
        }
        return billDay;
    }

    public static int getSecond() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    public static Date getPriorDayStartTime(int num, Date startTime) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Calendar date = Calendar.getInstance();
        if (startTime != null) {
            date.setTime(startTime);
        }
        date.add(Calendar.DATE, -num);
        String beforeDate = dft.format(date.getTime()) + " 00:00:00";
        Date fdate = null;
        try {
            fdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beforeDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fdate;
    }


}
