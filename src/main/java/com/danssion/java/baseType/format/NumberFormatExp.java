/**
 * \* Created with IntelliJ IDEA.
 * \* Name: NumberFormatExp
 * \* User: danssion
 * \* Date: 2019/12/30
 * \* Time: 16:20
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.baseType.format;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatExp {
    public static void main(String[] args) {
//        numberFormat();

//        currencyFormat();
        decimalFormat();
    }

    private static void numberFormat() {
        double d = 12345.676688000;
        NumberFormat nf = NumberFormat.getNumberInstance();
        System.out.println(nf.format(d));//12,345.677 默认只保留到小数点后三位
        nf.setMinimumIntegerDigits(2);
        System.out.println(nf.format(d));//12,345.677 整数部分大于2位按默认最大小数位数3位输出
        d = 1234.0;
        nf.setMaximumIntegerDigits(3);
        System.out.println(nf.format(d));//234
        nf = NumberFormat.getInstance();
        d = 12345.6766;
        nf.setMinimumFractionDigits(1);
        System.out.println(nf.format(d));//12,345.677 小数部分大于1位，按默认最大小数位数3位输出
        nf.setMinimumFractionDigits(5);
        System.out.println(nf.format(d));//12,345.67660 不够位数补0
        nf.setMaximumFractionDigits(1);
        System.out.println(nf.format(d));//12,345.7
        nf = NumberFormat.getNumberInstance(Locale.US);
        d = 12345.6789;
        System.out.println(nf.format(d));//12,345.679
        nf = NumberFormat.getNumberInstance(Locale.FRANCE);
        System.out.println(nf.format(d));//12 345,679
    }


    private static void decimalFormat() {
        double d1 = 123456.36987, d2 = 12.3698;
        DecimalFormat nf = new DecimalFormat("0000.000");
        System.out.println("d1=" + nf.format(d1) + " d2=" + nf.format(d2));//d1=123456.370 d2=0012.370
        nf = new DecimalFormat("#");
        System.out.println("d1=" + nf.format(d1));//d1=123456
        nf = new DecimalFormat(".####");
        System.out.println("d1=" + nf.format(d1));//d1=123456.3699
        nf = new DecimalFormat("0000,0000.00000");//注意“,”不能放在小数部分
        System.out.println("d1=" + nf.format(d1));//d1=0012,3456.36987

        nf = new DecimalFormat("#,#.###");

        String str = nf.format(new Double(0.342884));
        System.out.println(str);
    }


    private static void currencyFormat() {
        //按系统预设的货币格式输出，这里是人民币
        System.out.println(Locale.getDefault());
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        System.out.println(nf.format(21123.456));//￥21,123.46
        System.out.println(nf.format(123.456));//￥123.46


        //按指定的货币格式输出，这里是美元
        //Locale locale = Locale.US;
        nf = NumberFormat.getCurrencyInstance(Locale.US);
        System.out.println(nf.format(21123.456));//$21,123.46
        System.out.println(nf.format(123.456));//$123.46

        nf = NumberFormat.getCurrencyInstance(Locale.KOREA);
        System.out.println(nf.format(1123.456));//￦1,123
        System.out.println(nf.format(13.456));//￦13


        nf = NumberFormat.getCurrencyInstance(Locale.JAPAN);
        System.out.println(nf.format(21123.456));//￥21,123
        System.out.println(nf.format(13.456));//￥13

        nf = NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
        System.out.println(nf.format(21123.456));//21.123 đ
        System.out.println(nf.format(13.456));//13 đ

        nf = NumberFormat.getCurrencyInstance(new Locale("in","ID"));
        System.out.println(nf.format(21123.456));//Rp21.123,46
        System.out.println(nf.format(13.456));//Rp13,46



        Locale[] locales = Locale.getAvailableLocales();
        for( Locale locale : locales ) {
//            System.out.print(locale.getDisplayCountry() + "=" + locale.getCountry());
//            System.out.println("\t\t\t\t\t\t\t" + locale.getDisplayLanguage() + "=" + locale.getLanguage());

        }
    }

    private static void percentFormat() {
        //按系统预设百分比格式输出
        double d = 123.456;
        NumberFormat nf = NumberFormat.getPercentInstance();
        System.out.println(nf.format(d));//12,346%
        //按指定百分比格式输出，这里是法国格式
        nf = NumberFormat.getPercentInstance(Locale.FRANCE);
        System.out.println(nf.format(d));//12 346 %
    }

    /**
     * 移除数字前面和后面冗余的0，只保留3位小数
     *
     * @param isFormat 是否需要千位分隔符(,)这种格式输出
     * @param num
     * @return
     */
    public static String trim0(boolean isFormat, double num) {
        NumberFormat nf = NumberFormat.getInstance();
        if (!isFormat) {
            //设置输出格式是否使用“,”分组,默认是使用的
            nf.setGroupingUsed(false);
        }
        String result = nf.format(num);
//        return isFormat ? result : result.replace(",", ""); //用上面代码替换去除分隔符操作
        return result;
    }

    /**
     * 移除数字前面和后面冗余的0
     *
     * @param isFormat      是否需要千位分隔符(,)这种格式输出
     * @param num
     * @param fractionDigit 要保留的小数位数
     * @return
     */
    public static String trim0(boolean isFormat, double num, int fractionDigit) {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(fractionDigit);
        //setMaximumFractionDigits不会保留小数点和后面多余的0，不需下面正则去除
//        if (result.contains(".") && result.endsWith("0")) {
//            result = result.replaceAll("0+?$", "");//去掉多余的0
//            result = result.replaceAll("[.]$", "");//如最后一位是.则去掉
//        }
        if (!isFormat) {
            //设置输出格式是否使用“,”分组,默认是使用的
            nf.setGroupingUsed(false);
        }
        String result = nf.format(num);
//        return isFormat ? result : result.replace(",", "");
        return result;
    }

    /**
     * 指定位数输出，不足补0
     * 整数部分如果位数大于需要的位数按实际位数输出
     * 小数部分如果大于需要的位数四舍五入
     *
     * @param num
     * @param integerDigit  整数部分位数
     * @param fractionDigit 小数部分位数
     * @return
     */
    public static String add0Format(double num, int integerDigit, int fractionDigit) {
        StringBuilder rule = new StringBuilder();
        if (integerDigit <= 0) {
            rule.append("#");
        } else {
            for (int i = 0; i < integerDigit; i++) {
                rule.append("0");
            }
        }
        if (fractionDigit > 0) {
            rule.append(".");
            for (int i = 0; i < fractionDigit; i++) {
                rule.append("0");
            }
        }
        DecimalFormat df = new DecimalFormat(rule.toString());
        return df.format(num);
    }

    /**
     * 保留几位小数，不足不补0，小数部分冗余的0也不显示
     *
     * @param num
     * @param fractionDigit 要保留小数的位数
     * @return
     */
    public static String fractionDigitFormat(double num, int fractionDigit) {
        /*方法一*/
//        StringBuilder rule = new StringBuilder("#");
//        if (fractionDigit > 0) {
//            rule.append(".");
//            for (int i = 0; i < fractionDigit; i++) {
//                rule.append("#");
//            }
//        }
//        DecimalFormat df = new DecimalFormat(rule.toString());
//        return df.format(num);

        /*方法二*/
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(fractionDigit);
        //设置输出格式是否使用“,”分组,这里不使用
        nf.setGroupingUsed(false);
        return nf.format(num);
    }

}
