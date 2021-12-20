package com.danssion.java.baseType.transfer;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class bigDecimal {
    public static void main(String[] args) {

        Integer cid = 123132;
        //interger
        new BigDecimal(Integer.parseInt(cid.toString()));

//        bigToInt();

//        compare();
//        stringToBigDec();
        BigToStringWithoutScientific();
    }

    public static void compare() {
        Integer i = 111;
        BigDecimal b1 = new BigDecimal(110);
        BigDecimal b2 = new BigDecimal(112);
        BigDecimal bd = new BigDecimal(i);

//        BigDecimal bnull = new BigDecimal(null);
        BigDecimal bnull = null;

        System.out.println("bigdecimal 111 vs 110 :" + bd.compareTo(b1));
        System.out.println("bigdecimal 111 vs 112 :" + bd.compareTo(b2));
        System.out.println("bigdecimal 111 vs null :" + bd.compareTo(bnull));
        // xception in thread "main" java.lang.NullPointerException
        System.out.println("bigdecimal 111 vs null :" + bd.compareTo(null));

    }

    public static void bigToInt() {
        BigDecimal b = new BigDecimal("1213.65127635");
        Integer i = b.intValue();
        System.out.println(i);
        if (b.intValue() > 0) {
            System.out.println(b);
        }
        b.setScale(2, RoundingMode.HALF_UP);
        i = b.intValue();
        System.out.println(i);

        Double d = b.doubleValue();
        System.out.println(d);

        BigDecimal bi = new BigDecimal("0.123");
        BigDecimal mu = new BigDecimal(100);
        System.out.println(bi.multiply(mu).setScale(2).stripTrailingZeros());
    }

    public static void stringToBigDec() {
        String str1 = "2.30";
        BigDecimal bd = new BigDecimal(str1);
        System.out.println(bd);


        /*由数字字符串构造BigDecimal的方法
         *设置BigDecimal的小数位数的方法
         */
//        数字字符串
        String StrBd = "1048576.1024";
//构造以字符串内容为值的BigDecimal类型的变量bd
        BigDecimal bd1 = new BigDecimal(StrBd);
//设置小数位数，第一个变量是小数位数，第二个变量是取舍方法(四舍五入)
        bd1 = bd1.setScale(2, BigDecimal.ROUND_HALF_UP);
//转化为字符串输出
        String OutString = bd1.toString();

        System.out.println(BigDecimal.valueOf(new Double(null)));

    }

    //object to bigdecimal
    public static BigDecimal getBigDecimal(Object value) {
        BigDecimal ret = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal) value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String) value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger) value);
            } else if (value instanceof Number) {
                ret = new BigDecimal(((Number) value).doubleValue());
            } else {
                throw new ClassCastException("Not possible to coerce [" + value + "] from class " + value.getClass() + " into a BigDecimal.");
            }
        }
        return ret;
    }

    public static void BigToStringWithoutScientific() {
        BigDecimal b1 = new BigDecimal(1000000000);
        System.out.println(b1 + " -> " + formatPairPrice(b1));

        BigDecimal b2 = new BigDecimal(0.000001234);
        System.out.println(b2 + " -> " + formatPairPrice(b2));

        BigDecimal b3 = new BigDecimal(0.0000001234);
        System.out.println(b3 + " -> " + formatPairPrice(b3));
    }

    public static String formatPairPrice(BigDecimal value) {
        NumberFormat nf = new DecimalFormat("#,###.####");
        if (value.compareTo(BigDecimal.ZERO) > 0 && value.compareTo(BigDecimal.ONE) <= 0) {
            nf.setMaximumFractionDigits(8);
        }
        if (value.compareTo(BigDecimal.ONE) > 0 && value.compareTo(new BigDecimal(100)) <= 0) {
            nf.setMaximumFractionDigits(6);
        }
        if (value.compareTo(new BigDecimal(100)) > 0 && value.compareTo(new BigDecimal(10000)) <= 0) {
            nf.setMaximumFractionDigits(2);
        }
        if (value.compareTo(new BigDecimal(10000)) > 0) {
            nf.setMaximumFractionDigits(0);
        }
        String valueFormat = nf.format(value.doubleValue());

        return valueFormat;
    }

}
