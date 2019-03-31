package com.brainsci.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ...
 *
 * @author izhx 2018/7/27 13:13
 */
// SimpleDateFormat 是线程不安全的类，不能作为静态类变量给多线程并发访问。
// 推荐使用ThreadLocal来维护，减少new的开销。
public class Time {
    private final static ThreadLocal<SimpleDateFormat> DATE = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    private final static ThreadLocal<SimpleDateFormat> TIME = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH:mm:ss"));
    private final static ThreadLocal<SimpleDateFormat> DATE_MIN = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm"));
    private final static ThreadLocal<SimpleDateFormat> DATE_TIME = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    /**
     * @return Long型13位时间戳，自1970年1月1日0时起的毫秒数，至少200年不会增加位数
     */
    public static Long timestamp() {
        return System.currentTimeMillis();
    }

    public static Long timestamp(String dateTime) throws ParseException {
        return DATE_TIME.get().parse(dateTime).getTime();
    }

    public static Long timestamp(Date date) {
        return date.getTime();
    }

    /**
     * @return 日期时间字符串例如 “2018-07-27 13:13:13”
     */
    public static String dateTimeString() {
        return DATE_TIME.get().format(new Date());
    }

    public static String dateTimeString(Long timestamp) {
        return DATE_TIME.get().format(new Date(timestamp));
    }

    public static String dateTimeString(Date date) {
        return DATE_TIME.get().format(date);
    }

    /**
     * @return 日期时间字符串例如 “2018-07-27 13:13”
     */
    public static String date2MinString() {
        return DATE_MIN.get().format(new Date());
    }

    public static String date2MinString(Long timestamp) {
        return DATE_MIN.get().format(new Date(timestamp));
    }

    public static String date2MinString(Date date) {
        return DATE_MIN.get().format(date);
    }

    /**
     * @return 日期字符串例如 “2018-07-27”
     */
    public static String dateString() {
        return DATE.get().format(new Date());
    }

    public static String dateString(Long timestamp) {
        return DATE.get().format(new Date(timestamp));
    }

    public static String dateString(Date date) {
        return DATE.get().format(date);
    }

    /**
     * @return 时间字符串例如 “13:13:13”
     */
    public static String timeString() {
        return TIME.get().format(new Date());
    }

    public static String timeString(Long timestamp) {
        return TIME.get().format(new Date(timestamp));
    }

    public static String timeString(Date date) {
        return TIME.get().format(date);
    }
}
