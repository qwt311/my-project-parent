package com.project.common.util;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by qiaowentao on 2016/9/28.
 */
public class StrUtil {

    /**
     * 根据输入的字符串和指定的字符串截取内容，并获取指定的部分
     * @param target
     * @param code
     * @param index
     * @return
     */
    public static String splitStr(String target,String code,int index){
        String split[] = target.split(code);
        String string = "";
        try {
            string = split[index];
        } catch (Exception e) {
            string = split[0];
        }

        return string;
    }

    /**
     * 根据传入的时间和格式进行格式化
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDateToTime(Date date, String pattern){

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String format = sdf.format(date);
        return format;
    }


    /**
     * 根据传入的时间字符串和格式进行解析
     * @param time
     * @param pattern
     * @return
     */
    public static Date parseTimeToDate(String time,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            date = new Date();
        }
        return date;
    }

    /**
     * 获取指定日期那天的开始时间   第一种方法
     * @param date
     * @return
     */
    public static Date getOneDayStart(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期那天的结束时间    第一种方法
     * @param date
     * @return
     */
    public static Date getOneDayEnd(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.SECOND, -1);
        return calendar.getTime();
    }

    /**
     * 获取指定日期那天的开始时间   另一种方法
     * @param date
     * @return
     */
    public static Date getOneDaySartAother(Date date){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date start = null;
        try {
            start = formater2.parse(formater.format(new Date())+ " 00:00:00");
        } catch (ParseException e) {
        }

        return start;
    }

    /**
     * 获取指定日期那天的结束时间   另一种方法
     * @param date
     * @return
     */
    public static Date getOneDayEndAnother(Date date){
        SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formater2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date end = null;
        try {
            end = formater2.parse(formater.format(new Date())+ " 23:59:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return end;
    }

    /**
     * 获得指定日期的前一天
     * @param dNow
     * @return
     */
    public static String getSpecifiedDayBefore(Date dNow){
        Date dBefore = new Date();
        Calendar calendar = Calendar.getInstance(); //得到日历
        calendar.setTime(dNow);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
        dBefore = calendar.getTime();   //得到前一天的时间

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //设置时间格式
        String defaultStartDate = sdf.format(dBefore);    //格式化前一天
        return defaultStartDate;
    }

    /**
     * 获得指定日期的前一天 的结束日期
     * @param date
     * @return
     */
    public static String getDayBeforeEnd(Date date){
        String endTime = StrUtil.getSpecifiedDayBefore(date);
        date = StrUtil.parseTimeToDate(endTime, "yyyy-MM-dd HH:mm:ss");
        Date end = StrUtil.getOneDayEnd(date);
        String endTimes = StrUtil.formatDateToTime(end, "yyyy-MM-dd HH:mm:ss");
        return endTimes;
    }

    /**
     * 获得指定日期的后一天
     *
     * 如：now：2016-09-28 14:08:07
     * 	  得到：2016-09-29 00:00:00
     *
     * @param date
     * @return
     */
    public static String getSpecifiedDayAfter(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);

        String dayAfter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(c.getTime());
        return dayAfter;
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */
    public static boolean isNullOrEmpty(Object obj){

        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection<?>) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map<?, ?>) obj).isEmpty();

        if (obj instanceof Object[]) {
            return Array.getLength(obj) == 0;
        }
        return false;

    }

    /**
     *
     * 过滤数组中的空元素
     *
     */
    public static Object[][] filterEmpty(Object[][] arrays) {
        int sumNotNull = 0;
        /***
         * 统计非空元素的总个数
         */
        for (int i = 0; i < arrays.length; i++) {
            Object object = arrays[i];
            if (!StrUtil.isNullOrEmpty(object)
                    && !StrUtil.isNullOrEmpty((Object[]) object)) {// 判断元素是否为空
                sumNotNull = sumNotNull + 1;
            }
        }
        Object[][] filtedObjs = new Object[sumNotNull][];
        int index = 0;
        for (int i = 0; i < arrays.length; i++) {
            Object[] object_tmp = arrays[i];
            if (!StrUtil.isNullOrEmpty(object_tmp)
                    && !StrUtil.isNullOrEmpty((Object[]) object_tmp)) {// 判断元素是否为空
                filtedObjs[index++] = object_tmp;
            }
        }
        return filtedObjs;
    }

}
