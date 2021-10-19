package com.danssion.java.json.jackson.exp2;

import lombok.Data;

import java.util.Date;

/**
 * @author duanxiang  duanxiang@haodelian.com
 * @version 1.0
 * @date 2020/8/27 19:02
 * @desc JavaExample-DdsignPattern
 */
@Data
public class Person {

    // 正常case
    private String name;
    // 空对象case
    private Integer age;
    // 日期转换case
    private Date date;
    // 默认值case
    private int height;
}
