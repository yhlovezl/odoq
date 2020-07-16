package com.xiaoniucr.utils;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author yanghl
 * @date 2020/5/22 16:09
 */
public class DateUtil {

    public static final String NORM_DATE_PATTERN = "yyyy-MM-dd";
    public static final String NORM_DATETIME_MINUTE_PATTERN = "yyyy-MM-dd HH:mm";
    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String NORM_DATETIME_MS_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String NORM_DATE_NO_LINE_PATTERN = "yyyyMMdd";
    public static final String NORM_TIME_PATTERN = "HH:mm:ss";

    public static String[] possiblePatterns =
            {
                    "yyyy-MM",
                    "yyyy-MM-dd",
                    "yyyy-MM-dd HH:mm:ss",
                    "yyyyMMdd",
                    "yyyy/MM/dd",
                    "yyyy年MM月dd日",
                    "yyyy MM dd"
            };

    /**
     * @param inputDate 要解析的字符串
     * @param patterns 可能出现的日期格式
     * @return 解析出来的日期，如果没有匹配的返回null
     */
    public static Date parseDate(String inputDate, String[] patterns) {
        SimpleDateFormat df = new SimpleDateFormat();
        for(String pattern:patterns){
            df.applyPattern(pattern);
            df.setLenient(false);//设置解析日期格式是否严格解析日期
            ParsePosition pos = new ParsePosition(0);
            Date date = df.parse(inputDate, pos);
            if(date!=null){
                return date;
            }
        }
        return null;
        //org.apache.commons.lang3.time.DateUtils
//		Date date = DateUtils.parseDate(inputDate, patterns);
//		return date;
    }

    /**
     * @param date 要解析的日期
     * @param patterns 可能出现的日期格式
     * @return 解析出来的日期，如果没有匹配的返回null
     */
    public static String parseDateString(Date date,String[] patterns){
        SimpleDateFormat df = new SimpleDateFormat();
        for(String pattern:patterns){
            df.applyPattern(pattern);
            df.setLenient(false);//设置解析日期格式是否严格解析日期
            FieldPosition pos = new FieldPosition(0);
            StringBuffer format = df.format(date, new StringBuffer(), pos);
            if(date!=null){
                return format.toString();
            }
        }
        return null;
    }





    /**
     * 比较两个日期之间的大小
     *
     * @param d1
     * @param d2
     * @return 前者大于后者返回true 反之false
     */
    public static boolean compareDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);

        int result = c1.compareTo(c2);
        if (result >= 0)
            return true;
        else
            return false;
    }

    /**
     * 字符串转日期格式 format
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date strDateConvertDate(String str,String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(str);
        return date;
    }

    /**
     * 日期格式转字符串 format
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static String dateConvertString(Date date,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String str = simpleDateFormat.format(date);
        return str;
    }


    /**
     * jdk1.8的LocalDate转date
     * @param localDate
     * @return
     */
    public static Date localDate2Date(LocalDate localDate){

        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        return date;
    }

    public static Integer daysBetween(Date begin,Date end){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int days = 0;
        try {
            begin = sdf.parse(sdf.format(begin));
            end = sdf.parse(sdf.format(end));
            days = (int) ((end.getTime() - begin.getTime()) / (24*3600*1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


    /**
     * 获取日期之间的毫秒数
     * @param begin
     * @param end
     * @return
     */
    public static Long durationMillis(Date begin,Date end){
        Long millis = end.getTime()-begin.getTime();
        return millis;
    }

    /**
     * 随机生成某个时间段的时分秒
     * 随机10-12点 传入min=10 max=12
     * @param min
     * @param max
     * @return 随机时分秒字符串
     */
    public static String getRandomTime(int min, int max){
        StringBuilder sb = new StringBuilder();

        Random random = new Random();
        int hour = random.nextInt(max)%(max-min+1) + min;
        int minute =  random.nextInt(60);
        int second = random.nextInt(60);
        sb.append(hour)
                .append(":")
                .append(minute > 9 ? minute : "0" + minute)
                .append(":")
                .append(second > 9 ? minute : "0" + minute);
        return sb.toString();
    }

    /**
     * 格式化时间
     * date -> string
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date,String format){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化日期字符串
     * string -> date
     * @param str
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date parseDateString(String str,String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.parse(str);
    }
    public static String dateFormat(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static Date formDateToNeedFormat(Date date , String format) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String dateString = simpleDateFormat.format(date);
        return simpleDateFormat.parse(dateString);
    }

    public static void main(String[] args) throws ParseException {
        Date date = DateUtil.parseDate("2020-01", DateUtil.possiblePatterns);
        System.out.println(date);
    }

    public static Integer getExpireDays(Date begin, Date end) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int days = 0;
        try {
            begin = sdf.parse(sdf.format(begin));
            end = sdf.parse(sdf.format(end));
            days = (int) ((end.getTime() - begin.getTime()) / (24*3600*1000));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }
}
