package com.danssion.java.json.jackson.exp2;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.IntNode;

import java.io.IOException;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2020/8/27 20:40
 * @desc JavaExample-DdsignPattern
 */
public class CustomDeserializer extends StdDeserializer<Person> {
    protected CustomDeserializer() {
        super(Person.class);
    }

    @Override
    public Person deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        Person person = new Person();
        int age = (Integer) ((IntNode) node.get("age")).numberValue();
        String name = node.get("name").asText();
        person.setAge(age);
        person.setName(name);
        return person;
    }
}