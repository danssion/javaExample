package com.danssion.java.json.fastjs;

import com.alibaba.fastjson.JSON;
import com.danssion.java.serialize.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2022/3/20 下午5:41
 * @desc JavaExample-DdsignPattern
 */
public class JsonSerializer {
    public static void main(String[] args) {
        User user = new User();
        user.setName("Dx");
        user.setAge(19);

        String json = serialToJson(user);
        System.out.println(json);

        User unu = deserialFromJson(json);
        System.out.println(unu);
    }


    private static String serialToJson(User u) {
        return JSON.toJSONString(u);
    }

    private static <T> T deserialFromJson(String json) {
        return (T)JSON.parseObject(json, User.class );

    }
}