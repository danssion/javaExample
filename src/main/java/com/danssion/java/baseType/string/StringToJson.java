/**
 * \* Created with IntelliJ IDEA.
 * \* Name: StringToJson
 * \* User: danssion
 * \* Date: 2019/12/25
 * \* Time: 17:30
 * \*
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */


package com.danssion.java.baseType.string;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.List;

public class StringToJson {
    public static void main(String[] args) {
        StringToJson.toJson();
    }

    public static void toJson() {
        String str = "[\"ARS\",\"AUD\",\"BOB\",\"BRL\",\"CAD\",\"CHF\",\"CLP\",\"CNY\",\"COP\",\"DKK\",\"EUR\",\"GBP\",\"HKD\",\"IDR\",\"INR\",\"JPY\",\"KRW\",\"PEN\",\"PHP\",\"PLN\",\"RUB\",\"SGD\",\"THB\",\"TRY\",\"TWD\",\"UAH\",\"USD\",\"VND\"]";

        List<String> list = Arrays.asList(str);

        System.out.println(str);
        System.out.println(JSONObject.parse(str));
//        JSONObject jsonObject = JSONObject.parseObject(str);
        JSONArray js = JSONArray.parseArray(str);
        System.out.println(js.contains("JPY"));
        System.out.println(JSONArray.parseArray(str));

        System.out.println(list);
    }
}
