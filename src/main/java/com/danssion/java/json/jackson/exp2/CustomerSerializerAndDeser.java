package com.danssion.java.json.jackson.exp2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.util.Date;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2020/8/27 20:39
 * @desc JavaExample-DdsignPattern
 *
 * 自定义的序列化类需要直接或间接继承 StdSerializer 或 JsonSerializer，
 * 同时需要利用 JsonGenerator 生成 json，重写方法 serialize
 *
 * JsonGenerator 有多种 write 方法以支持生成复杂的类型的 json，
 * 比如 writeArray，writeTree 等 。若想单独创建 JsonGenerator，可以通过 JsonFactory() 的 createGenerator
 *
 * 定义反序列化类：自定义的反序列化类需要直接或间接继承 StdDeserializer 或 StdDeserializer，
 * 同时需要利用 JsonParser 读取 json，重写方法 deserialize
 *
 * JsonParser 提供很多方法来读取 json 信息， 如 isClosed(), nextToken(), getValueAsString()等。
 * 若想单独创建 JsonParser，可以通过 JsonFactory() 的 createParser
 *
 * 定义好自定义序列化类和自定义反序列化类，若想在程序中调用它们，还需要注册到 ObjectMapper 的 Module
 * or
 * 或者也可通过注解方式加在 java 对象的属性，方法或类上面来调用它们：
 * @JsonSerialize(using = CustomSerializer.class)
 * @JsonDeserialize(using = CustomDeserializer.class)
 *
 */
public class CustomerSerializerAndDeser {
    public static void main(String[] args) {

    }

    public static void test() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 生成 module
        SimpleModule module = new SimpleModule("myModule");
        module.addSerializer(new CustomSerializer());
        module.addDeserializer(Person.class, new CustomDeserializer());
        // 注册 module
        mapper.registerModule(module);
        // 造数据
        Person person = new Person();
        person.setName("Tom");
        person.setAge(40);
        person.setDate(new Date());
        System.out.println("序列化");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println(jsonString);
        System.out.println("反序列化");
        Person deserializedPerson = mapper.readValue(jsonString, Person.class);
        System.out.println(deserializedPerson);
    }
}
