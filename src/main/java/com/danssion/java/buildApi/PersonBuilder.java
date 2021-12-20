package com.danssion.java.buildApi;

public class PersonBuilder {
    private final int id;//身份证号
    private final String name;//姓名
    private int age;//年龄
    private boolean sex;//性别
    private String desc;//个人描述

    private PersonBuilder(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.sex = builder.sex;
        this.desc = builder.desc;
    }

    public static class Builder {
        private final int id;
        private final String name;
        private int age;
        private boolean sex;
        private String desc;

        public Builder(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder age(int age) {
            //年龄合法性检查
            if (age < 0) throw new IllegalArgumentException();
            else this.age = age;
            return this;
        }

        public Builder sex(boolean sex) {
            this.sex = sex;
            return this;
        }

        public Builder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public PersonBuilder build() {
            return new PersonBuilder(this);
        }
    }

    @Override
    public String toString() {
        return "id:" + this.id + " 姓名:" + this.name + " 年龄:" + this.age + " 性别:" + this.sex + " 描述:" + this.desc;
    }


    public static void main(String[] args) {
        Builder builder = new PersonBuilder.Builder(123, "张三");
        PersonBuilder person = builder.age(18).sex(false).desc("好好学习，天天向上").build();
        System.out.println(person.toString());
    }
}


