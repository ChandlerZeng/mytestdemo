package com.example.mytestdemo.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * ���ڲ���������.
 * 
 * @Project ERPForAndroid
 * @Package com.ymerp.android.tools
 * @author chenlin
 * @version 1.0
 * @Date 2013��5��11��
 */

public class DateUtil {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");
    private static final SimpleDateFormat timeFormat = new SimpleDateFormat(
            "HH:mm:ss");

    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    /**
     * �ַ���תʱ��
     * 
     * @param str
     * @param format
     * @return
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;

    }

    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);

    }

    public static Calendar str2Calendar(String str, String format) {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);

        return c;

    }

    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-"
                + c.get(Calendar.DAY_OF_MONTH) + "-"
                + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE)
                + ":" + c.get(Calendar.SECOND);
    }

    /**
     * ��õ�ǰ���ڵ��ַ�����ʽ
     * 
     * @param format
     * @return
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    /**
     * ��ʽ����
     * 
     * @param time
     * @return
     */
    public static String getMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);

    }

    /**
     * ��ʽ����
     * 
     * @param time
     * @return
     */
    public static String getDay(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);

    }

    /**
     * ��ʽ������
     * 
     * @param time
     * @return
     */
    public static String getSMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);

    }

    /**
     * �ַ���ת����ʱ���ʽ
     * 
     * @param dateStr
     *            ��Ҫת�����ַ���
     * @param formatStr
     *            ��Ҫ��ʽ��Ŀ���ַ��� ���� yyyy-MM-dd
     * @return Date ����ת�����ʱ��
     * @throws ParseException
     *             ת���쳣
     */
    public static Date StringToDate(String dateStr, String formatStr) {
        DateFormat sdf = new SimpleDateFormat(formatStr);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * ת��ʱ������ʱ���뵱ǰʱ��ļ��
     * 
     * @param timestamp
     * @return
     */
    public static String converTime(long timestamp) {
        long currentSeconds = System.currentTimeMillis() / 1000;
        long timeGap = currentSeconds - timestamp;// ������ʱ���������
        String timeStr = null;
        if (timeGap > 2 * 24 * 60 * 60) {// 1������
            timeStr = getStandardTime(timestamp);
        }else if (timeGap > 24 * 60 * 60) {// 1������
            timeStr = timeGap / (24 * 60 * 60) + "��ǰ";
        } else if (timeGap > 60 * 60) {// 1Сʱ-24Сʱ
            timeStr = timeGap / (60 * 60) + "Сʱǰ";
        } else if (timeGap > 60) {// 1����-59����
            timeStr = timeGap / 60 + "����ǰ";
        } else {// 1����-59����
            timeStr = "�ո�";
        }
        return timeStr;
    }

    /**
     * ���ַ���ת��Ϊʱ���ʽ
     * 
     * @param timestamp
     * @return
     */
    public static String getStandardTime(long timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM��dd�� HH:mm");
        Date date = new Date(timestamp * 1000);
        sdf.format(date);
        return sdf.format(date);
    }

    /**
     * ��õ�ǰ����ʱ�� ����ʱ���ʽyyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String currentDatetime() {
        return datetimeFormat.format(now());
    }

    /**
     * ��ʽ������ʱ�� ����ʱ���ʽyyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String formatDatetime(Date date) {
        return datetimeFormat.format(date);
    }

    /**
     * ��õ�ǰʱ�� ʱ���ʽHH:mm:ss
     * 
     * @return
     */
    public static String currentTime() {
        return timeFormat.format(now());
    }

    /**
     * ��ʽ��ʱ�� ʱ���ʽHH:mm:ss
     * 
     * @return
     */
    public static String formatTime(Date date) {
        return timeFormat.format(date);
    }

    /**
     * ��õ�ǰʱ���<code>java.util.Date</code>����
     * 
     * @return
     */
    public static Date now() {
        return new Date();
    }

    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * ��õ�ǰʱ��ĺ�����
     * 
     * ���{@link System#currentTimeMillis()}
     * 
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     * 
     * ��õ�ǰChinese�·�
     * 
     * @return
     */
    public static int month() {
        return calendar().get(Calendar.MONTH) + 1;
    }

    /**
     * ����·��еĵڼ���
     * 
     * @return
     */
    public static int dayOfMonth() {
        return calendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * ���������ڵĵڼ���
     * 
     * @return
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * ���������еĵڼ���
     * 
     * @return
     */
    public static int dayOfYear() {
        return calendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * �ж�ԭ�����Ƿ���Ŀ������֮ǰ
     * 
     * @param src
     * @param dst
     * @return
     */
    public static boolean isBefore(Date src, Date dst) {
        return src.before(dst);
    }

    /**
     * �ж�ԭ�����Ƿ���Ŀ������֮��
     * 
     * @param src
     * @param dst
     * @return
     */
    public static boolean isAfter(Date src, Date dst) {
        return src.after(dst);
    }

    /**
     * �ж��������Ƿ���ͬ
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    /**
     * �ж�ĳ�������Ƿ���ĳ�����ڷ�Χ
     * 
     * @param beginDate
     *            ���ڷ�Χ��ʼ
     * @param endDate
     *            ���ڷ�Χ����
     * @param src
     *            ��Ҫ�жϵ�����
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }

    /**
     * ��õ�ǰ�µ����һ��
     * 
     * HH:mm:ssΪ0������Ϊ999
     * 
     * @return
     */
    public static Date lastDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 0); // M������
        cal.set(Calendar.HOUR_OF_DAY, 0);// H����
        cal.set(Calendar.MINUTE, 0);// m����
        cal.set(Calendar.SECOND, 0);// s����
        cal.set(Calendar.MILLISECOND, 0);// S����
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// �·�+1
        cal.set(Calendar.MILLISECOND, -1);// ����-1
        return cal.getTime();
    }

    /**
     * ��õ�ǰ�µĵ�һ��
     * 
     * HH:mm:ss SSΪ��
     * 
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 1); // M����1
        cal.set(Calendar.HOUR_OF_DAY, 0);// H����
        cal.set(Calendar.MINUTE, 0);// m����
        cal.set(Calendar.SECOND, 0);// s����
        cal.set(Calendar.MILLISECOND, 0);// S����
        return cal.getTime();
    }

    private static Date weekDay(int week) {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_WEEK, week);
        return cal.getTime();
    }

    /**
     * �����������
     * 
     * ע��������������{@link #calendar()}������ÿ�����ڵĵ�һ��ΪMonday��US��ÿ���ڵ�һ��Ϊsunday
     * 
     * @return
     */
    public static Date friday() {
        return weekDay(Calendar.FRIDAY);
    }

    /**
     * �����������
     * 
     * ע��������������{@link #calendar()}������ÿ�����ڵĵ�һ��ΪMonday��US��ÿ���ڵ�һ��Ϊsunday
     * 
     * @return
     */
    public static Date saturday() {
        return weekDay(Calendar.SATURDAY);
    }

    /**
     * ����������� ע��������������{@link #calendar()}������ÿ�����ڵĵ�һ��ΪMonday��US��ÿ���ڵ�һ��Ϊsunday
     * 
     * @return
     */
    public static Date sunday() {
        return weekDay(Calendar.SUNDAY);
    }

    /**
     * ���ַ�������ʱ��ת����java.util.Date���� ����ʱ���ʽyyyy-MM-dd HH:mm:ss
     * 
     * @param datetime
     * @return
     */
    public static Date parseDatetime(String datetime) throws ParseException {
        return datetimeFormat.parse(datetime);
    }

    /**
     * ���ַ�������ת����java.util.Date���� ����ʱ���ʽyyyy-MM-dd
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * ���ַ�������ת����java.util.Date���� ʱ���ʽ HH:mm:ss
     * 
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date parseTime(String time) throws ParseException {
        return timeFormat.parse(time);
    }

    /**
     * �����Զ���pattern���ַ�������ת����java.util.Date����
     * 
     * @param datetime
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDatetime(String datetime, String pattern)
            throws ParseException {
        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
        format.applyPattern(pattern);
        return format.parse(datetime);
    }

    /**
     * �����ʽ��Ϊ����Сʱ
     * 
     * @param second
     * @return
     */
    public static String parseSecond(int second) {
        if (second >= 60) {
            return second / 60 + "��";
        } else if (second >= 60 * 60) {
            return second / 60 * 60 + "ʱ";
        } else if (second >= 60 * 60 * 24) {
            return second / 60 * 60 + "��";
        } else {
            return second + "��";
        }
    }

    /**
     * �Ƚ�ʱ���С
     * 
     * @param begin
     * @param end
     * @return
     */
    public static int compareDate(String begin, String end) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date beginDate = df.parse(begin);
            Date endDate = df.parse(end);
            if (beginDate.getTime() < endDate.getTime()) {
                return 1;
            } else if (beginDate.getTime() > endDate.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * ������
     * 
     * @param date
     * @return
     */
    public int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    /**
     * ����·�
     * 
     * @param date
     * @return
     */
    public int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * ������ڼ�
     * 
     * @param date
     * @return
     */
    public int getWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * �������
     * 
     * @param date
     * @return
     */
    public int getDay(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DATE);
    }

    /**
     * ���������
     * 
     * @param begin
     * @param end
     * @return
     */
    public long getDayDiff(Date begin, Date end) {
        long day = 1;
        if (end.getTime() < begin.getTime()) {
            day = -1;
        } else if (end.getTime() == begin.getTime()) {
            day = 1;
        } else {
            day += (end.getTime() - begin.getTime()) / (24 * 60 * 60 * 1000);
        }
        return day;
    }
}
