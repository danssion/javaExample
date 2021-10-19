package com.danssion.java.json.jackson.exp1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GENDER{
    MALE("男", 1), FEMALE("女", 0);
    private String name;
    private int value;

    GENDER(String name, int value){
        this.name = name;
        this.value = value;
    }

    /**
     * 通过注解@JsonCreator来处理枚举反序列化，该方法接收一个int类型的参数，也就是枚举的value值，返回枚举类型GENDER。
     * 如果没找到，则返回null
     *
     * @param value
     * @return
     */
    @JsonCreator
    public static GENDER getGenderById(int value){
        for (GENDER c: GENDER.values()){
            if (c.getValue() == value){
                return c;
            }
        }
        return null;
    }

    @JsonValue
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getValue(){
        return value;
    }

    public void setValue(int value){
        this.value = value;
    }

}
