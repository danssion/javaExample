package com.danssion.java.datetimeClock;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/12/8 下午2:13
 * @desc JavaExample-DdsignPattern
 */
public class DateTimeTransfer {
    public static void main(String[] args) {
        strToDate();
        dateToStr();
        strToTimestamp();
        timestampToStr();
        dateToTimestamp();
        timestampToDate();
    }

    private static void strToDate() {
        String dateStr = "2010/05/04 12:34:23";
        Date date = new Date();
        //注意format的格式要与日期String的格式相匹配
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            date = sdf.parse(dateStr);
            System.out.println("str to date:");
            System.out.println(date.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dateToStr() {
        String dateStr = "";
        Date date = new Date();
        //format的格式可以任意
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH/mm/ss");
        try {
            dateStr = sdf.format(date);
            System.out.println("date to str:"+ dateStr);
            dateStr = sdf2.format(date);
            System.out.println("date to str:"+ dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void strToTimestamp() {
        String dateStr = "2021-12-01 12:22:12";
        Date date = new Date();
        //format的格式可以任意
        DateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        DateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Timestamp ts2,ts = new Timestamp(System.currentTimeMillis());
        String tsStr = "2011-05-09 11:49:45";
        String ts2Str = "2011/05/09 11:49:45";
        //注：String的类型必须形如： yyyy-mm-dd hh:mm:ss[.f...] 这样的格式，中括号表示可选，否则报错！！！
        try {
            ts = Timestamp.valueOf(tsStr);
            System.out.println("str to ts:"+ts);
            date = sdf1.parse(ts2Str);
            ts2 = Timestamp.valueOf(sdf2.format(date));
            System.out.println("str to ts:"+ts2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void timestampToStr() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String tsStr = "";
        DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            //方法一
            tsStr = sdf.format(ts);
            System.out.println("ts to str: "+tsStr);
            //方法二
            tsStr = ts.toString();
            System.out.println("ts to str: "+tsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查API可知，Date和Timesta是父子类关系
     */
    private static void timestampToDate() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        Date date = new Date();
        try {
            date = ts;
            System.out.println("ts to date : "+date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void dateToTimestamp() {
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        System.out.println("data to ts: "+ts);
    }
}
