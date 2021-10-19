package com.danssion.java.json.jackson.exp1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Date;

public class User implements java.io.Serializable {
    private String name;
    private int age;

    //用于在序列化和反序列化时，显示时间的格式
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;

    //测试自定义反序列化
    @JsonDeserialize(using = CustomDeserializerDate.class)
    private Date birthday_custom;

    private GENDER gender;

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }

    @JsonValue
    public GENDER getGender(){
        return gender;
    }

    public void setGender(GENDER gender){
        this.gender = gender;
    }

    public User(){
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public Date getBirthday_custom(){
        return birthday_custom;
    }

    public void setBirthday_custom(Date birthday_custom){
        this.birthday_custom = birthday_custom;
    }

    @Override
    public String toString(){
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", birthday_custom=" + birthday_custom +
                ", gender=" + gender +
                '}';
    }
}
