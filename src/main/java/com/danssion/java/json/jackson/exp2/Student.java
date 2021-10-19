package com.danssion.java.json.jackson.exp2;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Student {
    // 正常case
    private String name;
    // 日期转换case
    private LocalDateTime date;
}
