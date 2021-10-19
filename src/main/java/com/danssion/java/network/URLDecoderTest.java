/**
 * \* Created with IntelliJ IDEA.
 * \* Name: URLDecoderTest
 * \* User: danssion
 * \* Date: 2019/5/16
 * \* Time: 11:11
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.network;

import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLDecoderTest {
    public static void main(String[] args)
            throws Exception
    {
        // 将application/x-www-form-urlencoded字符串
        // 转换成普通字符串
        // 其中的字符串直接从图17.3所示窗口复制过来
        String keyWord = URLDecoder.decode(
                "%E7%96%AF%E7%8B%82java", "utf-8");
        System.out.println(keyWord);
        // 将普通字符串转换成
        // application/x-www-form-urlencoded字符串
        String urlStr = URLEncoder.encode(
                "疯狂Android讲义" , "GBK");
        System.out.println(urlStr);
    }
}
