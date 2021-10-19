package com.danssion.java.spring.configuration3;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {

        // @Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContexts
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        //bean
        TestBean3 tb = (TestBean3) context.getBean("testBean3");
        tb.sayHello();

        DataSource ds = (DataSource) context.getBean("dataSource");
        System.out.println(ds);
    }
}

//output
// TestConfiguration容器启动初始化。。。
// TestBean3 sayHello...
// DataSource [dbUser=null, dbPass=null]

