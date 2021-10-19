package com.danssion.java.spring.configurationProperties;

import com.danssion.java.spring.configuration3.DataSource;
import com.danssion.java.spring.configuration3.TestBean3;
import com.danssion.java.spring.configuration3.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {

        // @Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContexts
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);

        //bean
        TestApplicationConfig tb = (TestApplicationConfig) context.getBean("abc");
        tb.testPersonProperties();

    }
}

//output
// TestConfiguration容器启动初始化。。。
// TestBean3 sayHello...
// DataSource [dbUser=null, dbPass=null]

