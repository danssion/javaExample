package com.danssion.java.json.jackson.util;

import cn.hutool.core.collection.ListUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.*;

public final class JacksonUtil {
    private JacksonUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    private final static Logger log = LoggerFactory.getLogger(JacksonUtil.class);

    private final static ObjectMapper objectMapper;



    static {
        //最常用的 API 就是基于"对象绑定" 的 ObjectMapper
        objectMapper = new ObjectMapper();

        /**
         * 更多配置信息可以查看 Jackson 的 DeserializationFeature，SerializationFeature 和 Include。
         */
        //去掉默认的时间戳格式 yyyy-MM-dd'T'HH:mm:ss.SSSZ
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        //序列化时，日期的统一格式
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

        //设置时区
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        //空值不序列化
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        //在序列化时忽略值为 null 的属性
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //在序列化时忽略默认值为 空 的属性
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        //在序列化时忽略值为默认值的属性
//        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);

        //反序列化时，属性不存在的兼容处理
//        objectMapper.getDeserializationConfig().withoutFeatures(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//        在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        //单引号处理
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true) ;
    }

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * @desc 获取json 对象
     * @return
     */
    public static ObjectNode getJson() {
        return objectMapper.createObjectNode();
    }

    /**
     * @desc ArrayNode (类似fastjson中的JSONArray)
     * @return
     */
    public static ArrayNode getJsonArray() {
        return objectMapper.createArrayNode();
    }

    /**
     * JSON串转换为Java泛型对象，可以是各种类型
     *
     * @param <T>
     * @param jsonString JSON字符串
     * @param tr         TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return List对象列表
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToGenericObject(String jsonString, TypeReference<T> tr) {

        if (jsonString == null || "".equals(jsonString)) {
            return null;
        } else {
            try {
                return (T) objectMapper.readValue(jsonString, tr);
            } catch (Exception e) {
                log.error("json error:" + e.getMessage());
            }
        }
        return null;
    }

    /**
     * Java对象转Json字符串
     */
    public static String toJsonString(Object object) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            log.warn("json error:" + e.getMessage());
        }
        return jsonString;
    }

    /**
     * java 对象转 ObjectNode
     * @param object
     * @param <T>
     * @return
     */
    public static <T> ObjectNode objectToObjectNode(T object) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(object);
            return objectMapper.readValue(jsonString, ObjectNode.class);
        } catch (Exception e) {
            log.warn("json error:" + e.getMessage());
        }
        return null;
    }


    /**
     * java 对象转 JsonNode
     * @param object
     * @param <T>
     * @return
     */
    public static <T> JsonNode objectToJsonNode(T object) {
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(object);
            return objectMapper.readTree(jsonString);
        } catch (Exception e) {
            log.warn("json error:" + e.getMessage());
        }
        return null;
    }

    /**
     * Json字符串转Java对象
     */
    public static <T> T jsonToObject(String jsonString, Class<T> c) {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        } else {
            try {
                return objectMapper.readValue(jsonString, c);
            } catch (Exception e) {
                log.warn("json error:" + e.getMessage());
            }
        }
        return null;
    }
    public static <T> List<T> jsonToObjectList(String jsonString, Class<T> clazz) {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        } else {
            try {
                List<T> list = ListUtil.empty();
                if (jsonString.indexOf("[") > 0) {
                    T re = objectMapper.readValue(jsonString, clazz);
                    list.add(re);
                } else {
                    JavaType javaType = getCollectionType(ArrayList.class, clazz);
                    list = objectMapper.readValue(jsonString, javaType);
                    //LinkHashMap  not T
//                    list = objectMapper.readValue(jsonString, new TypeReference<List<T>>(){});
                }
                return list;
            } catch (Exception e) {
                log.warn("json error:" + e.getMessage());
            }
            return parseJsonList(jsonString,clazz);
        }
    }


    public static String toJsonCompress(Object object){
        if (object == null)  {
            return null;
        }
        String json = toJsonString(object);
        json = toJsonCompress(json);
        return json;
    }


    public static String toJsonCompress(String json){
        if(StringUtils.isBlank(json)) {
            return json;
        }
        StringBuilder sb = new StringBuilder();
        boolean skip = true;// true 允许截取(表示未进入string双引号)
        boolean escaped = false;// 转义符
        for (int i = 0; i < json.length(); i++)
        {
            char c = json.charAt(i);
            if (!escaped && c == '\\')
            {
                escaped = true;
            }
            else
            {
                escaped = false;
            }
            if (skip)
            {
                if (c == ' ' || c == '\r' || c == '\n' || c == '\t')
                {
                    continue;
                }
            }
            sb.append(c);
            if (c == '"' && !escaped)
            {
                skip = !skip;
            }
        }
        return sb.toString().replaceAll("\r\n", "\\\\r\\\\n");
    }






    public static final class IosDateTimeJsonSerializer extends JsonSerializer {

        public void serialize(Date value, JsonGenerator paramJsonGenerator, SerializerProvider provider) throws IOException, JsonProcessingException {
            if (value != null) {
                paramJsonGenerator.writeString(FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss").format(value));
            }
        }

        @Override
        public void serialize(Object value, JsonGenerator gen,
                              SerializerProvider serializers) throws IOException,
                JsonProcessingException {
            // TODO Auto-generated method stub
        }
    }






    public static <T> T copyAttributeFromMap(Map<String, ?> requestMap, Class<T> clazz) throws InstantiationException, IllegalAccessException{
        T bean = clazz.newInstance();
        if(bean!=null && requestMap!=null){
            Class userCla = (Class)bean.getClass();
            Field[] fs = userCla.getDeclaredFields();
            for (int i = 0; i < fs.length; i++) {
                Field f = fs[i];
                f.setAccessible(true); // 设置些属性是可以访问的
                Object val = requestMap.get(f.getName());
                f.set(bean, val);
            }
        }
        return bean;
    }

    /**
     * @param json 准备转换json
     * @param clazz 集合元素类型
     * @return
     * @description json字符串转换成对象集合
     * @version V1.0
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> parseJsonList(String json, Class<T> clazz) {
        try {
            JavaType javaType = getCollectionType(ArrayList.class, clazz);
            return (List<T>) objectMapper.readValue(json, javaType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * @param collectionClass 集合类
     * @param elementClasses 集合元素类
     * @return
     * @description 获取泛型的ColloectionType
     * @version V1.0
     */
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses)  {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }



}
