/**
 * \* Created with IntelliJ IDEA.
 * \* Name: StringFormat
 * \* User: danssion
 * \* Date: 2019/12/25
 * \* Time: 20:19
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.baseType.string;

import java.util.Date;
import java.util.Locale;

public class StringFormat {
    public static void main(String[] args) {

    }

    /**
     * @desc 不同数据类型到字符串的转换
     * <p>
     * %s 字符串类型 "mingrisoft"
     * <p>
     * %c 字符类型 'm'
     * <p>
     * %b 布尔类型 true
     * <p>
     * %d 整数类型（十进制） 99
     * <p>
     * %x 整数类型（十六进制） FF
     * <p>
     * %o 整数类型（八进制） 77
     * <p>
     * %f 浮点类型 99.99
     * <p>
     * %a 十六进制浮点类型 FF.35AE
     * <p>
     * %e 指数类型 9.38e+5
     * <p>
     * %g 通用浮点类型（f和e类型中较短的）
     * <p>
     * %h 散列码
     * <p>
     * %% 百分比类型 ％
     * <p>
     * %n 换行符
     * <p>
     * %tx 日期与时间类型（x代表不同的日期与时间转换符
     */
    public static void typeTrans() {
        String str = null;
        str = String.format("Hi,%s", "王力");
        System.out.println(str);
        str = String.format("Hi,%s:%s.%s", "王南", "王力", "王张");
        System.out.println(str);
        System.out.printf("字母a的大写是：%c %n", 'A');
        System.out.printf("3>7的结果是：%b %n", 3 > 7);
        System.out.printf("100的一半是：%d %n", 100 / 2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50 * 0.85);
        System.out.printf("上面价格的16进制数是：%a %n", 50 * 0.85);
        System.out.printf("上面价格的指数表示：%e %n", 50 * 0.85);
        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50 * 0.85);
        System.out.printf("上面的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');
    }

    /**
     * @desc 转换符的标志
     * <p>
     * + 为正数或者负数添加符号 ("%+d",15)+15
     * −  左对齐  ("%-5d",15)  |15   |
     * 0  数字前面补0  ("%04d", 99)  0099
     * 空格  在整数之前添加指定数量的空格  ("% 4d", 99)  |  99|
     * ,  以“,”对数字分组  ("%,f", 9999.99)  9,999.990000
     * (  使用括号包含负数  ("%(f", -99.99)  (99.990000)
     * #  如果是浮点数则包含小数点，如果是16进制或8进制则添加0x或0  ("%#x", 99)  ("%#o", 99)  0x63  0143
     * <   格式化前一个转换符所描述的参数  ("%f和%<3.2f", 99.45)  99.450000和99.45
     * $  被格式化的参数索引  ("%1$d,%2$s", 99,"abc")  99,abc
     */

    public static void tranSign() {
        String str = null;
        //$使用
        str = String.format("格式参数$的使用：%1$d,%2$s", 99, "abc");
        System.out.println(str);
        //+使用
        System.out.printf("显示正负数的符号：%+d与%d%n", 99, -99);
        //补O使用
        System.out.printf("最牛的编号是：%03d%n", 7);
        //空格使用
        System.out.printf("Tab键的效果是：% 8d%n", 7);
        //.使用
        System.out.printf("整数分组的效果是：%,d%n", 9989997);
        //空格和小数点后面个数
        System.out.printf("一本书的价格是：% 50.5f元%n", 49.8);
    }

    public static void dateFormat() {
        Date date = new Date();
        //c的使用
        System.out.printf("全部日期和时间信息：%tc%n", date);
        //f的使用
        System.out.printf("年-月-日格式：%tF%n", date);
        //d的使用
        System.out.printf("月/日/年格式：%tD%n", date);
        //r的使用
        System.out.printf("HH:MM:SS PM格式（12时制）：%tr%n", date);
        //t的使用
        System.out.printf("HH:MM:SS格式（24时制）：%tT%n", date);
        //R的使用
        System.out.printf("HH:MM格式（24时制）：%tR", date);

        //b的使用，月份简称
        String str = String.format(Locale.US, "英文月份简称：%tb", date);
        System.out.println(str);
        System.out.printf("本地月份简称：%tb%n", date);
        //B的使用，月份全称
        str = String.format(Locale.US, "英文月份全称：%tB", date);
        System.out.println(str);
        System.out.printf("本地月份全称：%tB%n", date);
        //a的使用，星期简称
        str = String.format(Locale.US, "英文星期的简称：%ta", date);
        System.out.println(str);
        //A的使用，星期全称
        System.out.printf("本地星期的简称：%tA%n", date);
        //C的使用，年前两位
        System.out.printf("年的前两位数字（不足两位前面补0）：%tC%n", date);
        //y的使用，年后两位
        System.out.printf("年的后两位数字（不足两位前面补0）：%ty%n", date);
        //j的使用，一年的天数
        System.out.printf("一年中的天数（即年的第几天）：%tj%n", date);
        //m的使用，月份
        System.out.printf("两位数字的月份（不足两位前面补0）：%tm%n", date);
        //d的使用，日（二位，不够补零）
        System.out.printf("两位数字的日（不足两位前面补0）：%td%n", date);
        //e的使用，日（一位不补零）
        System.out.printf("月份的日（前面不补0）：%te", date);


        //H的使用
        System.out.printf("2位数字24时制的小时（不足2位前面补0）:%tH%n", date);
        //I的使用
        System.out.printf("2位数字12时制的小时（不足2位前面补0）:%tI%n", date);
        //k的使用
        System.out.printf("2位数字24时制的小时（前面不补0）:%tk%n", date);
        //l的使用
        System.out.printf("2位数字12时制的小时（前面不补0）:%tl%n", date);
        //M的使用
        System.out.printf("2位数字的分钟（不足2位前面补0）:%tM%n", date);
        //S的使用
        System.out.printf("2位数字的秒（不足2位前面补0）:%tS%n", date);
        //L的使用
        System.out.printf("3位数字的毫秒（不足3位前面补0）:%tL%n", date);
        //N的使用
        System.out.printf("9位数字的毫秒数（不足9位前面补0）:%tN%n", date);
        //p的使用
        String str1 = String.format(Locale.US, "小写字母的上午或下午标记(英)：%tp", date);
        System.out.println(str1);
        System.out.printf("小写字母的上午或下午标记（中）：%tp%n", date);
        //z的使用
        System.out.printf("相对于GMT的RFC822时区的偏移量:%tz%n", date);
        //Z的使用
        System.out.printf("时区缩写字符串:%tZ%n", date);
        //s的使用
        System.out.printf("1970-1-1 00:00:00 到现在所经过的秒数：%ts%n", date);
        //Q的使用

    }


}