/**
 * \* Created with IntelliJ IDEA.
 * \* Name: InstantExp
 * \* User: danssion
 * \* Date: 2019/12/19
 * \* Time: 11:49
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.datetimeClock;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashMap;

public class InstantExp {
    public static void main(String[] args) {
        InstantExp exp = new InstantExp();
        exp.inst();
    }


    public void inst() {
        Instant instantNow = Instant.now();

        System.out.println("instant获取当前yyyy-mm-dd : " + instantNow);

        Clock clock = Clock.systemDefaultZone();

        System.out.println("default zone id:" + clock);

        Instant instantClock = Instant.now(clock);

        System.out.println("instantClock获取当前yyyy-mm-dd : " + instantClock);
        System.out.println("set clock zone yyyy-mm-dd : " + Instant.now().atZone(clock.getZone()));

        Instant localDateGet = Instant.now();

        System.out.println("UTC+8: " + localDateGet.atZone(ZoneId.of("UTC+8")));
        System.out.println("从1970-01-01T00：00：00Z开始的秒数 :" + localDateGet.getEpochSecond());
        System.out.println("localDateGet获取当前是本月的第几天 :" + localDateGet.atZone(ZoneId.of("GMT+8", new HashMap<>(64))));


        Instant instantEpochSecond = Instant.ofEpochSecond(200);
        System.out.println("从1970-01-01T00：00：00Z的秒数:" + instantEpochSecond);
        Instant instantEpochMilli = Instant.ofEpochMilli(600);
        System.out.println("从1970-01-01T00：00：00Z的毫秒数:\n" + instantEpochMilli);


        Instant localDateFormatNow = Instant.from(ZonedDateTime.now());
        System.out.println("当前日期是:\n" + localDateFormatNow);
        System.out.println("当前日期是:\n" + localDateFormatNow.atZone(Clock.systemDefaultZone().getZone()));

        Instant instantText = Instant.parse("2007-12-03T10:15:30.00Z");
        System.out.println("instantText输出字符串日期 :\n" + instantText);

        Instant instantMax = Instant.MAX;
        System.out.println("instantMax年的最大取值范围 :\n" + instantMax);

        Instant instantMin = Instant.MIN;
        System.out.println("instantMin年的最小取值范围 :\n" + instantMin);


        //时间点的比较  A.compareTo(B):A>B，return 1；A= B,return 0;A<B,return -1;
        Instant datetime1 = Instant.parse("2007-12-03T10:15:30.00Z");
        Instant datetime2 = Instant.parse("2017-12-03T10:15:30.00Z");
        Instant datetime3 = Instant.parse("2019-12-03T10:15:30.00Z");


        System.out.println(datetime2.compareTo(datetime3));
        System.out.println(datetime2.compareTo(datetime2));
        System.out.println(datetime2.compareTo(datetime1));
        System.out.println(datetime2.isAfter(datetime2));
        System.out.println(datetime2.isBefore(datetime3));


        //时间点加任意时间单位
        Instant plusSeconds = Instant.EPOCH.plusSeconds(100000L);
        System.out.println(plusSeconds);
        System.out.println(plusSeconds.getEpochSecond());
        System.out.println(plusSeconds.toEpochMilli());
        System.out.println(plusSeconds.getNano());

        Instant plus100days = Instant.EPOCH.plus(100, ChronoUnit.DAYS);
        System.out.println(plus100days);
        System.out.println(plus100days.getEpochSecond());
        System.out.println(plus100days.toEpochMilli());
        System.out.println(plus100days.getNano());

        Instant lastday = Instant.now().minus(1, ChronoUnit.DAYS);
        System.out.println(lastday.getEpochSecond());
    }
}

