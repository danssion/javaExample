package com.danssion.java.json.jackson.exp2;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;
import lombok.ToString;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2020/8/27 19:03
 * @desc JavaExample-DdsignPattern
 */
public class JacksonExp {
    public static void main(String[] args) {
        try {
//            serializeOrDeserialize();
//            serializeOrDeserializeUseAnnotation();
//            withTheDate();
//            withList();
//            withMap();
//            withVisibility();
//            filterParams();
            treeModel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void serializeOrDeserialize() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 造数据
        Person person = new Person();
        person.setName("Tom");
        person.setAge(40);
        person.setDate(new Date());
        System.out.println("序列化");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println(jsonString);
        System.out.println("not writerWithDefaultPrettyPrinter "+mapper.writeValueAsString(person));
        System.out.println("反序列化");
        Person deserializedPerson = mapper.readValue(jsonString, Person.class);
        System.out.println(deserializedPerson);
    }

    public static void serializeOrDeserializeUseAnnotation() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 造数据
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "Tom");
        map.put("date", "2020-07-26 19:28:44");
        map.put("age", 100);
        map.put("demoKey", "demoValue");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(jsonString);
        System.out.println("反序列化");
        User user = mapper.readValue(jsonString, User.class);
        System.out.println(user);
        System.out.println("序列化");
        jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
        System.out.println(jsonString);


        //output
/*
        {
            "date" : "2020-07-26 19:28:44",
                "demoKey" : "demoValue",
                "user_name" : "Tom",
                "age" : 100
        }
        反序列化
        @JsonCreator 注解使得反序列化自动执行该构造方法Tom
        JackSonTest.User(other={demoKey=demoValue}, userName=Tom, age=100, date=Sun Jul 26 19:28:44 CST 2020, height=0)
        序列化
        {
            "date" : "2020-07-26 19:28:44",
                "user_name" : "Tom",
                "age" : 100,
                "height" : 0,
                "demoKey" : "demoValue"
        }
 */

    }

    /**
     * @desc 对于日期类型为 java.time.LocalDate, java.time.LocalDateTime
     *
     */
    public static void withTheDate() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // 必须添加对LocalDate的支持
        mapper.registerModule(JavaTimeModule());
        // 造数据
        Student student = new Student();
        student.setName("Tom");
        student.setDate(LocalDateTime.now());
        System.out.println("序列化");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println(jsonString);
        System.out.println("反序列化");
        Student deserializedPerson = mapper.readValue(jsonString, Student.class);
        System.out.println(deserializedPerson);
    }
    private static Module JavaTimeModule() {
        JavaTimeModule module = new JavaTimeModule();
        String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String DATE_FORMAT = "yyyy-MM-dd";
        String TIME_FORMAT = "HH:mm:ss";
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        module.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern((TIME_FORMAT))));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_FORMAT)));
        module.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_FORMAT)));
        module.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_FORMAT)));
        return module;
    }


    public static void withList() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType javaType = mapper.getTypeFactory().constructCollectionType(List.class, Person.class);
        // 造数据
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setName("Tom");
            person.setAge(new Random().nextInt(100));
            person.setDate(new Date());
            list.add(person);
        }
        System.out.println("序列化");
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        System.out.println(jsonInString);
        System.out.println("反序列化：使用 javaType");
        List<Person> personList = mapper.readValue(jsonInString, javaType);
        System.out.println(personList);
        System.out.println("反序列化：使用 TypeReference");
        List<Person> personList2 = mapper.readValue(jsonInString, new TypeReference<List<Person>>() {
        });
        System.out.println(personList2);
    }

    public static void withMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //第二参数是 map 的 key 的类型，第三参数是 map 的 value 的类型
        MapType javaType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Person.class);
        // 造数据
        Map<String, Person> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setName("Tom");
            person.setAge(new Random().nextInt(100));
            person.setDate(new Date());
            map.put("key" + i, person);
        }
        System.out.println("序列化");
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(jsonInString);
        System.out.println("反序列化: 使用 javaType");
        Map<String, Person> personMap = mapper.readValue(jsonInString, javaType);
        System.out.println(personMap);
        System.out.println("反序列化: 使用 TypeReference");
        Map<String, Person> personMap2 = mapper.readValue(jsonInString, new TypeReference<Map<String, Person>>() {
        });
        System.out.println(personMap2);
    }

    @ToString
    public static class People {
        public int age;
        protected String name;
    }

    /**
     * @desc
     * JackSon 默认不是所有的属性都可以被序列化和反序列化。默认的属性可视化的规则如下：
     * 若该属性修饰符是 public，该属性可序列化和反序列化。
     * 若属性的修饰符不是 public，但是它的 getter 方法和 setter 方法是 public，该属性可序列化和反序列化。因为 getter 方法用于序列化， 而 setter 方法用于反序列化。
     * 若属性只有 public 的 setter 方法，而无 public 的 getter 方 法，该属性只能用于反序列化。
     *
     * @throws IOException
     */
    public static void withVisibility() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // PropertyAccessor 支持的类型有 ALL,CREATOR,FIELD,GETTER,IS_GETTER,NONE,SETTER
        // Visibility 支持的类型有 ANY,DEFAULT,NON_PRIVATE,NONE,PROTECTED_AND_PUBLIC,PUBLIC_ONLY
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        // 造数据
        People people = new People();
        people.name = "Tom";
        people.age = 40;
        System.out.println("序列化");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(people);
        System.out.println(jsonString);
        System.out.println("反序列化");
        People deserializedPerson = mapper.readValue(jsonString, People.class);
        System.out.println(deserializedPerson);
    }

    @JsonFilter("myFilter")
    public interface MyFilter {
    }

    /**
     * @desc 将 Java 对象序列化为 json 时 ，有些属性需要过滤掉，不显示在 json 中
     * @throws JsonProcessingException
     */
    public static void filterParams() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //设置 addMixIn
        mapper.addMixIn(Person.class, MyFilter.class);
        //调用 SimpleBeanPropertyFilter 的 serializeAllExcept 方法
        SimpleBeanPropertyFilter newFilter = SimpleBeanPropertyFilter.serializeAllExcept("age");
        //或重写 SimpleBeanPropertyFilter 的 serializeAsField 方法
        SimpleBeanPropertyFilter newFilter2 = new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField(Object pojo, JsonGenerator jgen,
                                         SerializerProvider provider, PropertyWriter writer)
                    throws Exception {
                if (!writer.getName().equals("age")) {
                    writer.serializeAsField(pojo, jgen, provider);
                }
            }
        };
        //设置 FilterProvider
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("myFilter", newFilter);
        // 造数据
        Person person = new Person();
        person.setName("Tom");
        person.setAge(40); // 该属性将被忽略
        person.setDate(new Date());
        // 序列化
        String jsonString = mapper.setFilterProvider(filterProvider).writeValueAsString(person);
        System.out.println(jsonString);
    }

    /**
     * @desc Jackson 也提供了树模型(tree model)来生成和解析 json。
     * 若想修改或访问 json 部分属性，树模型是不错的选择。
     * 树模型由 JsonNode 节点组成。程序中常常使用 ObjectNode，ObjectNode 继承于 JsonNode
     *
     * @throws IOException
     */
    public static void treeModel() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //构建 ObjectNode
        ObjectNode personNode = mapper.createObjectNode();
        //添加/更改属性
        personNode.put("name", "Tom");
        personNode.put("age", 40);
        ObjectNode addressNode = mapper.createObjectNode();
        addressNode.put("zip", "000000");
        addressNode.put("street", "Road NanJing");
        //设置子节点
        personNode.set("address", addressNode);
        System.out.println("构建 ObjectNode:\n" + personNode.toString());
        //通过 path 查找节点
        JsonNode searchNode = personNode.path("name");
        System.out.println("查找子节点 name:\n" + searchNode.asText());
        //删除属性
        ((ObjectNode) personNode).remove("address");
        System.out.println("删除后的 ObjectNode:\n" + personNode.toString());
        //读取 json
        JsonNode rootNode = mapper.readTree(personNode.toString());
        System.out.println("Json 转 JsonNode:\n" + rootNode);
        //JsonNode 转换成 java 对象
        Person person = mapper.treeToValue(personNode, Person.class);
        System.out.println("JsonNode 转对象:\n" + person);
        //java 对象转换成 JsonNode
        JsonNode node = mapper.valueToTree(person);
        System.out.println("对象转 JsonNode:\n" + node);
    }
}
