package com.xiaoniucr.jdk8;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class DateHelp {

    private static String DATE_YYYYMMDD_HHMMSS = "yyyy-MM-dd HH:mm:ss";


    /**
     * jdk8 localDate转date
     * @param date
     * @return
     */
    public static Date localDate2Date(LocalDate date){
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = date.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);

    }


    /**
     * 计算几天前的日期
     * @param days 天数 - 负值
     * @return
     */
    public static Date daysBefore(int days){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, days);
        return c.getTime();
    }


    /**
     * 计算几天之后的日期
     * @param days 天数 - 正数
     * @return
     */
    public static Date daysAfter(int days){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, +7);
        return c.getTime();
    }


    /**
     * 几个月之前的时间
     * @param months 月数 - 负数
     * @return
     */
    public static Date monthsBefore(int months){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH,months);
        return c.getTime();
    }


    /**
     * 几个月之后的时间
     * @param months 月数 - 正数
     * @return
     */
    public static Date monthsAfter(int months){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, months);
        return c.getTime();

    }

    /**
     * 几年之前的时间
     * @param years 年份 - 负值
     * @return
     */
    public static Date yearsBefore(int years){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR,years);
        return c.getTime();
    }

    /**
     * 几年之后的日期
     * @param years 年份 - 正数
     * @return
     */
    public static Date yearsAfter(int years){

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR,years);
        return c.getTime();
    }


    /**
     * 计算两个日期之间相差的年份
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static int yearBetween(Date startDate,Date endDate){

        long differ = endDate.getTime() - startDate.getTime();
        if(differ < 0L){
            return 0;
        }
        int years = (int) (differ / (365 * 24 * 60 * 60 * 1000));
        return years;
    }

    /**
     * 计算两个日期之间相差的天数
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static int daysBetween(Date startDate,Date endDate){

        long differ = endDate.getTime() - startDate.getTime();
        if(differ < 0L){
            return 0;
        }
        int days = (int) (differ / (24 * 60 * 60 * 1000));
        return days;
    }

    /**
     * 计算两个日期之间相差的小时
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static int hoursBetween(Date startDate,Date endDate){

        long differ = endDate.getTime() - startDate.getTime();
        if(differ < 0L){
            return 0;
        }
        int hours = (int) (differ / (60 * 60 * 1000));
        return hours;
    }


    /**
     * 计算两个日期之间相差的分钟
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static int minutesBetween(Date startDate,Date endDate){
        long differ = endDate.getTime() - startDate.getTime();
        if(differ < 0L){
            return 0;
        }
        int minutes = (int) (differ / (60 * 1000));
        return minutes;
    }

    /**
     * 计算两个日期之间的秒数
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static int secondsBetween(Date startDate,Date endDate){
        long differ = endDate.getTime() - startDate.getTime();
        if(differ < 0L){
            return 0;
        }
        int seconds = (int) (differ / 1000);
        return seconds;
    }

    /**
     * 比较两个日期的大小
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(Date date1,Date date2){

        long time1 = date1.getTime();
        long time2 = date2.getTime();
        if(time1>=time2){
            return true;
        }
        return false;
    }


    public static void main(String[] args) {


    }
}
