package com.danssion.java.json.jackson.exp2;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2020/8/27 20:39
 * @desc JavaExample-DdsignPattern
 */
public class CustomSerializer extends StdSerializer<Person> {
    protected CustomSerializer() {
        super(Person.class);
    }

    @Override
    public void serialize(Person person, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("age", person.getAge());
        jgen.writeStringField("name", person.getName());
        jgen.writeStringField("msg", "已被自定义序列化");
        jgen.writeEndObject();
    }
}