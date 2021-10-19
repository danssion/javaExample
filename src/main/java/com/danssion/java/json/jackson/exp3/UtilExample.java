package com.danssion.java.json.jackson.exp3;

import com.danssion.java.json.jackson.exp2.User;
import com.danssion.java.json.jackson.util.JacksonUtil;
import org.junit.Test;

import java.util.Date;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2021/1/26 20:41
 * @desc JavaExample-DdsignPattern
 */
public class UtilExample {
    @Test
    public void serializer() {
        User u = new User();
        u.setAge(12);
        u.setDate(new Date());
        u.setUserName("abc");
        /** int 类型的 0 被认为是默认值  objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);  **/
        u.setHeight(0);
        u.setBaseName("");

        String jsonStr = JacksonUtil.toJsonString(u);

        System.out.println(jsonStr);
    }

    @Test
    public void deserializer() {
        User u = new User();
        u.setAge(12);
        u.setDate(new Date());
        u.setUserName("abc");
        /** int 类型的 0 被认为是默认值, 没有设置也会默认给0 值  objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);  **/
//        u.setHeight(0);
        u.setBaseName("");

        String jsonStr = JacksonUtil.toJsonString(u);

        System.out.println("serializer : ");
        System.out.println(jsonStr);

        User deu = JacksonUtil.jsonToObject(jsonStr,User.class);

        System.out.println("deserializer : ");
        System.out.println(deu);

    }
}
