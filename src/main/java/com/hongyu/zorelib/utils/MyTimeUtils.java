package com.hongyu.zorelib.utils;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * <pre>
 *     author : 宇
 *     time   : 2020/06/10
 *     desc   : 格式化时间
 * </pre>
 */
@SuppressLint("SimpleDateFormat")
public class MyTimeUtils {

    /**
     * @return 一天的毫秒值
     */
    public static long get1Day() {
        return 1000 * 60 * 60 * 24L;
    }

    /**
     * @return 一分钟的毫秒值
     */
    public static long get1Minute() {
        return 60 * 1000L;
    }

    /**
     * 获取时间毫秒值
     */
    public static long getLongTimeZone(String type, String time) {
        if (TextUtils.isEmpty(time)) return 0L;
        SimpleDateFormat dateFormat = new SimpleDateFormat(type);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Date date = dateFormat.parse(time, new ParsePosition(0));
        return date == null ? 0L : date.getTime();
    }


    @SuppressLint("DefaultLocale")
    public static String getCheckTaskUpdateTime() {
        long time = TimeZone.getDefault().getRawOffset();
        if (time < 0) time = MyTimeUtils.get1Day() + time;
        long totalSeconds = time / 1000;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        return String.format("%02d:%02d", hours, minutes);
    }

    public static String getTimeYYYYMMDD_HHMM(Long time) {
        return time == null || time == 0 ? "" : getTime(AppCons.TIME_YYYYMMDD_HHMM, time);
    }


    /**
     * 获取时区
     */
    public static String getTimeZoneId() {
        return TimeZone.getDefault().getID();
    }

    /**
     * @return 获取当前时间
     */
    public static String getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(AppCons.TIME_TYPE);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }

    /**
     * @return 获取当天开始时间
     */
    public static String getTodayTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(AppCons.TIME_TYPE_000);
        Date curDate = new Date(System.currentTimeMillis());
        return formatter.format(curDate);
    }


    public static long getStartOfDayTimestamp(long timestamp) {
        LocalDateTime date = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
        LocalDateTime startOfDay = date.with(ChronoField.HOUR_OF_DAY, 0)
                .with(ChronoField.MINUTE_OF_HOUR, 0)
                .with(ChronoField.SECOND_OF_MINUTE, 0)
                .with(ChronoField.NANO_OF_SECOND, 0);
        return ZonedDateTime.of(startOfDay, ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * @return 获取当天的0点
     */
    public static long getTodayStartTime() {
        // 获取当前日期
        LocalDate today = LocalDate.now();
        // 直接转换为当天0点的ZonedDateTime
        ZonedDateTime startOfDay = today.atStartOfDay(ZoneId.systemDefault());
        // 转换为毫秒值
        return startOfDay.toInstant().toEpochMilli();
    }

    /**
     * @return 获取昨天的0点
     */
    public static long getYesterdayStartTime() {
        // 获取当前日期的前一天
        LocalDate yesterday = LocalDate.now().minusDays(1);
        // 直接转换为当天0点的ZonedDateTime
        ZonedDateTime startOfDay = yesterday.atStartOfDay(ZoneId.systemDefault());
        // 转换为毫秒值
        return startOfDay.toInstant().toEpochMilli();
    }


    /**
     * @return 获取明天的0点
     */
    public static long getTomorrowStartTime() {
        // 获取当前日期
        // 加一天获取明天的日期
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        //  转换为当天的开始时间（0点） 将LocalDateTime转换为ZonedDateTime，这里使用系统默认时区
        ZonedDateTime zonedStartOfTomorrow = tomorrow.atStartOfDay(ZoneId.systemDefault());
        // 获取对应的时间戳
        return zonedStartOfTomorrow.toInstant().toEpochMilli();
    }

    /**
     * @return 获取一个月后
     */
    public static String get1NewMonty() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        Date mBefore = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(AppCons.TIME_TYPE_000);
        return sdf.format(mBefore);
    }

    /**
     * @return 获取2年前
     */
    public static String get2OldYell() {
        SimpleDateFormat sdf = new SimpleDateFormat(AppCons.TIME_TYPE);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, -2);
        Date mBefore = calendar.getTime();
        return sdf.format(mBefore);
    }

    public static Date getStringToDate(String dateString, String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @return 根据提供的年月日获取该月份的第一天
     */
    public static long getSupportBeginDayOfMonth(Date date) {
        date.getTime();
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, 1);
        startDate.set(Calendar.HOUR_OF_DAY, 0);
        startDate.set(Calendar.MINUTE, 0);
        startDate.set(Calendar.SECOND, 0);
        startDate.set(Calendar.MILLISECOND, 0);
        Date firstDate = startDate.getTime();
        return firstDate.getTime();
    }

    /**
     * @return 根据提供的年月获取该月份的最后一天
     */
    public static long getSupportEndDayOfMonth(Date date) {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(date);
        startDate.set(Calendar.DAY_OF_MONTH, startDate.getActualMaximum(Calendar.DAY_OF_MONTH));
        startDate.set(Calendar.HOUR_OF_DAY, 23);
        startDate.set(Calendar.MINUTE, 59);
        startDate.set(Calendar.SECOND, 59);
        startDate.set(Calendar.MILLISECOND, 999);
        Date firstDate = startDate.getTime();
        return firstDate.getTime();
    }

    /**
     * @return 当天的开始时间
     */
    public static long startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date.getTime();
    }

    /**
     * 毫秒值转指定时间格式
     */
    public static String getTime(String pattern,long timestamp) {
        return timestamp > 0 ? new SimpleDateFormat(pattern).format(new Date(timestamp)) : AppCons.STR_DEFAULT;
    }

    /**
     * 将毫秒转时分秒
     *
     * @return 12' 30'' 50'''
     */
    @SuppressLint("DefaultLocale")
    public static String generateTime(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        return hours > 0 ? String.format("%02d'%02d''%02d'''", hours, minutes, seconds) : String.format("%02d''%02d'''", minutes, seconds);
    }

    /**
     * 将毫秒转时分秒
     *
     * @return 12:30:50
     */
    @SuppressLint("DefaultLocale")
    public static String generateTime2(long time) {
        int totalSeconds = (int) (time / 1000);
        int seconds = totalSeconds % 60;
        int minutes = (totalSeconds / 60) % 60;
        int hours = totalSeconds / 3600;
        return hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds) : String.format("%02d:%02d", minutes, seconds);
    }


    ///获取当前葛林位置时间
    public static String getUTCTime(String pattern, long time) {
        SimpleDateFormat dateFormat2 = new SimpleDateFormat(pattern);
        dateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat2.format(new Date(time));
    }
}
