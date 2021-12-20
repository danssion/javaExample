package com.danssion.java.json.jackson.exp1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2020/8/26 19:11
 * @desc JavaExample-DdsignPattern
 */
public class JacksonExample {

    public static void main(String[] args) {
//        testJsonObject();

        testSerialize();

        testDeSerialize();

//        testDeSerializeDate();
    }


    /**
     * 要一个json对象和json数组的时候，我们可以使用下面的方法来构造ObjectNode和ArrayNode，
     * 类似fastjson中的JSONObject和JSONArray
     */
    public static void testJsonObject() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode json = mapper.createObjectNode();
        json.put("name", "Tom");
        json.put("age", 1);
        System.out.println(json);

        ObjectNode json1 = mapper.createObjectNode();
        json1.put("new", "newnew");
        json1.put("ww", "cc");


        ArrayNode jsonNodes = mapper.createArrayNode();
        jsonNodes.add(json);
        jsonNodes.add(json1);
        System.out.println(jsonNodes);
        System.out.println(mapper);
    }

    /**
     * 序列化操作就是将Java对象转化成json
     */
    public static void testSerialize() {
        User user = new User();
        user.setAge(1);
        user.setName("zhangsan");
        user.setGender(GENDER.MALE);
        user.setBirthday(new Date());
        ObjectMapper mapper = new ObjectMapper();
        String s = null;
        try {
            s = mapper.writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(s);
    }

    public static void testDeSerialize() {
        String json = "{\"name\":\"zhangsan\",\"age\":10}";
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(json, User.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }


    /**
     * 对于Date类型，目前支持以下的格式：
     * long类型的时间戳
     * 通过@JsonFormat 注解指定类型格式：yyyy-MM-dd HH:mm:ss
     */
    public static void testDeSerializeDate() {
        String json = "{\"name\":\"zhangsan\",\"age\":10,\"birthday\":1592800446397}";
        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = mapper.readValue(json, User.class);
            System.out.println(user);

            String json1 = "{\"name\":\"zhangsan\",\"age\":10,\"birthday\":\"2020-01-01 12:13:14\"}";
            User user1 = mapper.readValue(json1, User.class);
            System.out.println(user1);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(user1.getBirthday()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }


}


