package com.danssion.java.json.jackson.exp1;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDeserializerDate extends StdDeserializer<Date> {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected CustomDeserializerDate(Class<?> vc){
        super(vc);
    }

    //需要一个无参构造方法，否则会报错
    public CustomDeserializerDate(){
        this(null);
    }

    @Override
    public Date deserialize(
            JsonParser p,
            DeserializationContext ctxt) throws IOException {
        String date = p.getText();
        try {
            return sdf.parse(date);
        } catch (ParseException e){
            e.printStackTrace();
        }
        return null;
    }
}
