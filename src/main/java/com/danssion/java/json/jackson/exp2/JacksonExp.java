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
        // ?????????
        Person person = new Person();
        person.setName("Tom");
        person.setAge(40);
        person.setDate(new Date());
        System.out.println("?????????");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
        System.out.println(jsonString);
        System.out.println("not writerWithDefaultPrettyPrinter " + mapper.writeValueAsString(person));
        System.out.println("????????????");
        Person deserializedPerson = mapper.readValue(jsonString, Person.class);
        System.out.println(deserializedPerson);
    }

    public static void serializeOrDeserializeUseAnnotation() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // ?????????
        Map<String, Object> map = new HashMap<>();
        map.put("user_name", "Tom");
        map.put("date", "2020-07-26 19:28:44");
        map.put("age", 100);
        map.put("demoKey", "demoValue");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(jsonString);
        System.out.println("????????????");
        User user = mapper.readValue(jsonString, User.class);
        System.out.println(user);
        System.out.println("?????????");
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
        ????????????
        @JsonCreator ???????????????????????????????????????????????????Tom
        JackSonTest.User(other={demoKey=demoValue}, userName=Tom, age=100, date=Sun Jul 26 19:28:44 CST 2020, height=0)
        ?????????
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
     * @desc ????????????????????? java.time.LocalDate, java.time.LocalDateTime
     */
    public static void withTheDate() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // ???????????????LocalDate?????????
        mapper.registerModule(JavaTimeModule());
        // ?????????
        Student student = new Student();
        student.setName("Tom");
        student.setDate(LocalDateTime.now());
        System.out.println("?????????");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println(jsonString);
        System.out.println("????????????");
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
        // ?????????
        List<Person> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setName("Tom");
            person.setAge(new Random().nextInt(100));
            person.setDate(new Date());
            list.add(person);
        }
        System.out.println("?????????");
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
        System.out.println(jsonInString);
        System.out.println("????????????????????? javaType");
        List<Person> personList = mapper.readValue(jsonInString, javaType);
        System.out.println(personList);
        System.out.println("????????????????????? TypeReference");
        List<Person> personList2 = mapper.readValue(jsonInString, new TypeReference<List<Person>>() {
        });
        System.out.println(personList2);
    }

    public static void withMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //??????????????? map ??? key ??????????????????????????? map ??? value ?????????
        MapType javaType = mapper.getTypeFactory().constructMapType(HashMap.class, String.class, Person.class);
        // ?????????
        Map<String, Person> map = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            Person person = new Person();
            person.setName("Tom");
            person.setAge(new Random().nextInt(100));
            person.setDate(new Date());
            map.put("key" + i, person);
        }
        System.out.println("?????????");
        String jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map);
        System.out.println(jsonInString);
        System.out.println("????????????: ?????? javaType");
        Map<String, Person> personMap = mapper.readValue(jsonInString, javaType);
        System.out.println(personMap);
        System.out.println("????????????: ?????? TypeReference");
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
     * @throws IOException
     * @desc JackSon ????????????????????????????????????????????????????????????????????????????????????????????????????????????
     * ???????????????????????? public??????????????????????????????????????????
     * ??????????????????????????? public??????????????? getter ????????? setter ????????? public???????????????????????????????????????????????? getter ???????????????????????? ??? setter ???????????????????????????
     * ??????????????? public ??? setter ??????????????? public ??? getter ??? ??????????????????????????????????????????
     */
    public static void withVisibility() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        // PropertyAccessor ?????????????????? ALL,CREATOR,FIELD,GETTER,IS_GETTER,NONE,SETTER
        // Visibility ?????????????????? ANY,DEFAULT,NON_PRIVATE,NONE,PROTECTED_AND_PUBLIC,PUBLIC_ONLY
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        // ?????????
        People people = new People();
        people.name = "Tom";
        people.age = 40;
        System.out.println("?????????");
        String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(people);
        System.out.println(jsonString);
        System.out.println("????????????");
        People deserializedPerson = mapper.readValue(jsonString, People.class);
        System.out.println(deserializedPerson);
    }

    @JsonFilter("myFilter")
    public interface MyFilter {
    }

    /**
     * @throws JsonProcessingException
     * @desc ??? Java ?????????????????? json ??? ????????????????????????????????????????????? json ???
     */
    public static void filterParams() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        //?????? addMixIn
        mapper.addMixIn(Person.class, MyFilter.class);
        //?????? SimpleBeanPropertyFilter ??? serializeAllExcept ??????
        SimpleBeanPropertyFilter newFilter = SimpleBeanPropertyFilter.serializeAllExcept("age");
        //????????? SimpleBeanPropertyFilter ??? serializeAsField ??????
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
        //?????? FilterProvider
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("myFilter", newFilter);
        // ?????????
        Person person = new Person();
        person.setName("Tom");
        person.setAge(40); // ?????????????????????
        person.setDate(new Date());
        // ?????????
        String jsonString = mapper.setFilterProvider(filterProvider).writeValueAsString(person);
        System.out.println(jsonString);
    }

    /**
     * @throws IOException
     * @desc Jackson ?????????????????????(tree model)?????????????????? json???
     * ????????????????????? json ?????????????????????????????????????????????
     * ???????????? JsonNode ???????????????????????????????????? ObjectNode???ObjectNode ????????? JsonNode
     */
    public static void treeModel() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //?????? ObjectNode
        ObjectNode personNode = mapper.createObjectNode();
        //??????/????????????
        personNode.put("name", "Tom");
        personNode.put("age", 40);
        ObjectNode addressNode = mapper.createObjectNode();
        addressNode.put("zip", "000000");
        addressNode.put("street", "Road NanJing");
        //???????????????
        personNode.set("address", addressNode);
        System.out.println("?????? ObjectNode:\n" + personNode.toString());
        //?????? path ????????????
        JsonNode searchNode = personNode.path("name");
        System.out.println("??????????????? name:\n" + searchNode.asText());
        //????????????
        ((ObjectNode) personNode).remove("address");
        System.out.println("???????????? ObjectNode:\n" + personNode.toString());
        //?????? json
        JsonNode rootNode = mapper.readTree(personNode.toString());
        System.out.println("Json ??? JsonNode:\n" + rootNode);
        //JsonNode ????????? java ??????
        Person person = mapper.treeToValue(personNode, Person.class);
        System.out.println("JsonNode ?????????:\n" + person);
        //java ??????????????? JsonNode
        JsonNode node = mapper.valueToTree(person);
        System.out.println("????????? JsonNode:\n" + node);
    }
}
