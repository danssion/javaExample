package com.danssion.java.spring.configurationProperties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
@Service(value = "abc")
@ComponentScan(basePackages = "com.danssion.java.spring.configurationProperties")
public class TestApplicationConfig {
    @Autowired
    Person person;

    @Test
    public void testPersonProperties() {
        System.out.println(person);
    }
}