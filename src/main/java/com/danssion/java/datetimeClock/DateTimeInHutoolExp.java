package com.danssion.java.datetimeClock;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/4/16 下午8:25
 * @desc JavaExample-DdsignPattern
 */
public class DateTimeInHutoolExp {

    public static void main(String[] args) {
        st();
    }

    public static void st() {
        Date date = DateUtil.date();
        Date beginOfDay = DateUtil.beginOfDay(new Date());


        //偏移量
        Date newDate = DateUtil.offset(beginOfDay, DateField.HOUR_OF_DAY, 8);
        newDate.toInstant().getEpochSecond();

        Date newDate2 = DateUtil.offset(beginOfDay, DateField.HOUR_OF_DAY, 18);

        System.out.println(beginOfDay.getHours());
        System.out.println(newDate.getHours());
        System.out.println(newDate2.getHours());

        Calendar calendar = Calendar.getInstance();
        int curHour24 = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.println("calendar 24 hour:"+curHour24);

        Calendar calendar12 = Calendar.getInstance();
        int curHour12 = calendar12.get(Calendar.HOUR);
        System.out.println("calendar 12 hour:"+curHour12);

        System.out.println("calendar start :"+DateUtil.beginOfDay(Calendar.getInstance()).get(Calendar.HOUR_OF_DAY));

        System.out.println("calendar start day ts :"+ DateUtil.beginOfDay(Calendar.getInstance()).toInstant().getEpochSecond());

        System.out.println("calendar yesterday ts :" + DateUtil.yesterday().toInstant().getEpochSecond());

        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println("format data:"+DateUtil.format(beginOfDay,"yyMMdd"));

    }
}
