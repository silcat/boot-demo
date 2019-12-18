package com.boot.demo.utils;


import com.boot.demo.common.ResultStatus;
import com.boot.demo.exception.ServerException;
import com.renrendai.loan.ucredit.common.model.server.MonthDstDayParis;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @author sunlichuan
 * Created by sunlichuan on 18-6-6
 */
public class TimeUtils {

    /**
     * 获取当前的秒值
     *
     * @return
     */
    public static long currentSecond() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 获取指定时间的秒值
     *
     * @param d
     * @return
     */
    public static int getSecond(Date d) {
        return (int) (d.getTime() / 1000);
    }

    /**
     * 获取指定时间与当前时间的分钟差
     *
     * @param beginTime
     * @return
     */
    public static long diffMinuteNow(Date beginTime) {
        ZonedDateTime ldt1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(beginTime.getTime()), ZoneId.systemDefault());
        ZonedDateTime ldt2 = ZonedDateTime.now();
        return ChronoUnit.MINUTES.between(ldt1, ldt2);
    }

    /**
     * 获取当前时间到明天的秒值
     *
     * @return
     */
    public static long diffSecondToTomorrow() {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.SECONDS.between(now, today);
    }

    /**
     * 获取指定秒值更当天的天数差
     *
     * @param second
     * @return
     */
    public static long diffDaysToCurrent(Integer second) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault());
        ZonedDateTime now = ZonedDateTime.now();
        return ChronoUnit.DAYS.between(now, zdt);
    }

    /**
     * 获取指定时间与当天的天数差值
     *
     * @param beginTime
     * @return
     */
    public static long diffDaysNow(Date beginTime) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(beginTime.getTime()), ZoneId.systemDefault());
        ZonedDateTime now = ZonedDateTime.now();
        return ChronoUnit.DAYS.between(now, zdt);
    }

    /**
     * 获取指定时间与当天的天数差值
     *
     * @return
     */
    public static long diffDaysNow(Calendar c1, Calendar c2) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(c1.getTimeInMillis() / 1000), ZoneId.systemDefault());
        ZonedDateTime zdt1 = ZonedDateTime.ofInstant(Instant.ofEpochSecond(c2.getTimeInMillis() / 1000), ZoneId.systemDefault());
        return ChronoUnit.DAYS.between(zdt, zdt1);
    }

    /**
     * 获取指定时间与当前时间的小时差
     *
     * @param beginTime
     * @return
     */
    public static long diffHourNow(Date beginTime) {
        ZonedDateTime ldt1 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(beginTime.getTime()), ZoneId.systemDefault());
        ZonedDateTime ldt2 = ZonedDateTime.now();
        return ChronoUnit.HOURS.between(ldt1, ldt2);
    }

    /**
     * 获取指定秒值的年-月
     *
     * @param second
     * @return
     */
    public static String getYearMonth(Integer second) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochSecond(second), ZoneId.systemDefault());
        return String.format("%d-%d", zdt.getYear(), zdt.getMonth().getValue());
    }

    /**
     * 获取n天后的秒值
     *
     * @param day
     * @return
     */
    public static long futureDaySecond(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, day);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取n月后的秒值
     *
     * @param month
     * @return
     */
    public static long futureMonthSecond(int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, month);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取n秒后的秒值
     *
     * @param second
     * @return
     */
    public static long futureSecond(long second) {
        return currentSecond() + second;
    }

    /**
     * 获取n秒后的秒值
     *
     * @param second
     * @return
     */
    public static long futureSecond(String second) {
        long t = Long.parseLong(second);
        return currentSecond() + t;
    }

    /**
     * 是否早于当前时间
     *
     * @param t2second
     * @return
     */
    public static boolean beforeNow(Long t2second) {
        if (t2second == null) {
            t2second = 0L;
        }
        // t2 --> date
        return currentSecond() > t2second;
    }

    public static boolean beforeNow(Date date) {
        long millisSecond = date == null ? 0L : date.getTime();
        return System.currentTimeMillis() > millisSecond;
    }

    /**
     * 获取当月的第一天
     *
     * @return
     */
    public static Date getFirstDateCurrentMonth() {
        LocalDate today = LocalDate.now();
        //本月的第一天
        LocalDate firstday = LocalDate.of(today.getYear(), today.getMonth(), 1);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = firstday.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取当天的00:00:00
     *
     * @return
     */
    public static Date current() {
        LocalDate today = LocalDate.now();
        //本月的第一天
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取当月的最后一天
     *
     * @return
     */
    public static Date getLastDateCurrentMonth() {
        LocalDate lastDay = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime today = LocalDateTime.of(lastDay, LocalTime.MAX);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取下个月的这一天23：59：59
     *
     * @return
     */
    public static Date getNextMonthDay() {
        LocalDate now = LocalDate.now();
        LocalDate nextMonth = now.plus(1, ChronoUnit.MONTHS);
        LocalDateTime today = LocalDateTime.of(nextMonth, LocalTime.MAX);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取下个月的第一天
     *
     * @return
     */
    public static Date getFirstDateNextMonth() {
        LocalDate now = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate nextMonth = now.plus(1, ChronoUnit.MONTHS);
        LocalDateTime today = LocalDateTime.of(nextMonth, LocalTime.MIN);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取下下个月的第一天
     *
     * @return
     */
    public static Date getFirstDateNextNextMonth() {
        LocalDate now = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate nextMonth = now.plus(2, ChronoUnit.MONTHS);
        LocalDateTime today = LocalDateTime.of(nextMonth, LocalTime.MIN);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取某月第一天
     *
     * @return
     */
    public static Date getMonthFirstDate(Calendar calendar) {
        LocalDate firstday = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, 1);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = firstday.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());

    }

    /**
     * 获取某月最后一天
     *
     * @return
     */
    public static Date getMonthLastDate(Calendar calendar) {

        LocalDate now = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDateTime today = LocalDateTime.of(now, LocalTime.MAX);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }


    /**
     * 获取下个月的最后一天
     *
     * @return
     */
    public static Date getLastDateNextMonth() {
        LocalDate now = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalDate nextMonth = now.plus(1, ChronoUnit.MONTHS);
        LocalDateTime today = LocalDateTime.of(nextMonth, LocalTime.MAX);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取下下个月的最后一天
     *
     * @return
     */
    public static Date getLastDateNextNextMonth() {
        LocalDate now = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        LocalDate nextMonth = now.plus(2, ChronoUnit.MONTHS);
        LocalDateTime today = LocalDateTime.of(nextMonth, LocalTime.MAX);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取n天后的时间
     *
     * @param day
     * @return
     */
    public static Date futureDate(int day) {
        LocalDate now = LocalDate.now();
        now = now.plus(day, ChronoUnit.DAYS);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = now.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取day天前的时间
     *
     * @param day
     * @return
     */
    public static Date oldDate(int day) {
        LocalDate now = LocalDate.now();
        now = now.minus(day, ChronoUnit.DAYS);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = now.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取当天的00:00:00
     *
     * @return
     */
    public static Date getBeginToday() {
        LocalDate now = LocalDate.now();
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = now.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取当天的23：59：59
     *
     * @return
     */
    public static Date getEndToday() {
        LocalDateTime today = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取指定年月的第一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getFirstDayOfMonth(String year, String month) {
        LocalDate firstDay = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = firstDay.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取指定年月的最后一天
     *
     * @param year
     * @param month
     * @return
     */
    public static Date getLastDayOfMonth(String year, String month) {
        LocalDate now = LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
        LocalDate lastDay = now.with(TemporalAdjusters.lastDayOfMonth());
        LocalDateTime today = LocalDateTime.of(lastDay, LocalTime.MAX);
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = today.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    /**
     * 获取今天是2019节假日的第几天
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int isHolidayIn2019(int year, int month, int day) {
        // 2.4-2.10|4.5-4.7|5.1|6.7-6.9|9.13-9.15|10.1-10.7
        int yearEnum = 2019;
        int day4 = 4, day10 = 10, day5 = 5, day7 = 7, day6 = 6, day8 = 8;
        int day13 = 13, day15 = 15;
        if (year == yearEnum) {
            switch (month) {
                case 2:
                    if (day >= day4 && day <= day10) {
                        return day - 3;
                    }
                    break;
                case 4:
                    if (day >= day5 && day <= day7) {
                        return day - 4;
                    }
                    break;
                case 5:
                    if (day == 1) {
                        return 1;
                    }
                    break;
                case 6:
                    if (day >= day6 && day <= day8) {
                        return day - 5;
                    }
                    break;
                case 9:
                    if (day >= day13 && day <= day15) {
                        return day - 12;
                    }
                    break;
                case 10:
                    if (day >= 1 && day <= day7) {
                        return day;
                    }
                    break;
                default:
                    // 默认值
            }
        }
        return 0;
    }

    /**
     * 是否2019年的节假日
     *
     * @return
     */
    public static int isHolidayIn2019() {
        LocalDate ld = LocalDate.now();
        return isHolidayIn2019(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
    }

    /**
     * 是否2019的节假日
     *
     * @param date
     * @return
     */
    public static int isHolidayIn2019(Date date) {
        ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        return isHolidayIn2019(zdt.getYear(), zdt.getMonthValue(), zdt.getDayOfMonth());
    }

    public static Calendar getDate(long date) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(date * 1000);
        return c;
    }

    public static String getCurrentYMD() {
        LocalDate ld = LocalDate.now();
        return String.format("%d年%d月%d日", ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
    }


    public static boolean isSameMonth(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) && calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }
    /**
     * 获取指定还款日
     *
     * @param lastChar
     * @return
     */
    public static Date getFirstBillDate(char lastChar) {
        return getFirstBillDate(System.currentTimeMillis(), lastChar);
    }

    /**
     * 根据身份证获取还款日
     *
     * @param currentTime
     * @param lastChar
     * @return
     */
    public static Date getFirstBillDate(long currentTime, char lastChar) {
        Instant instant = Instant.ofEpochMilli(currentTime);
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime nowTime = LocalDateTime.ofInstant(instant, zoneId);
        LocalDate now = nowTime.toLocalDate();
        now = now.plus(2, ChronoUnit.DAYS);
        int tDay = now.get(ChronoField.DAY_OF_MONTH);

        MonthDstDayParis monthDstDayParis = getMonthDstDayParis(tDay, lastChar);
        now = LocalDate.of(now.getYear(), now.getMonthValue() + monthDstDayParis.getMonthPlus(), monthDstDayParis.getDstDay());
        ZonedDateTime zdt = now.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }
    /**
     * 根据身份证获取还款日
     *
     * @param lendDay
     * @param lastChar
     * @return
     */
    public static MonthDstDayParis getMonthDstDayParis(Integer lendDay, char lastChar) {
        int lastDay = 31;
        if (lendDay <= 0 || lendDay > lastDay) {
            throw new ServerException(ResultStatus.CONTRACT_LEND_DAY_ERROR);
        }
        MonthDstDayParis match;
        boolean prefix = lastChar == 88 || lastChar == 120 || lastChar % 2 == 1;

        switch (lendDay) {
            case 23:
                match = prefix ?
                        new MonthDstDayParis(1, 20) :
                        new MonthDstDayParis(2, 1);
                break;
            case 24:
                match = prefix ?
                        new MonthDstDayParis(1, 21) :
                        new MonthDstDayParis(2, 2);
                break;
            case 25:
                match = prefix ?
                        new MonthDstDayParis(1, 22) :
                        new MonthDstDayParis(2, 2);
                break;
            case 26:
                match = prefix ?
                        new MonthDstDayParis(2, 4) :
                        new MonthDstDayParis(2, 5);
                break;
            case 27:
                match = prefix ?
                        new MonthDstDayParis(2, 6) :
                        new MonthDstDayParis(2, 7);
                break;
            case 28:
                match = prefix ?
                        new MonthDstDayParis(2, 7) :
                        new MonthDstDayParis(2, 8);
                break;
            case 29:
                match = new MonthDstDayParis(2, 9);
                break;
            case 30:
                match = new MonthDstDayParis(2, 10);
                break;
            case 31:
                match = new MonthDstDayParis(2, 11);
                break;
            default:
                match = new MonthDstDayParis(1, lendDay);
        }
        return match;
    }
}
