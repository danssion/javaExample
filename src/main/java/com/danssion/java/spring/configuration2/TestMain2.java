package com.danssion.java.spring.configuration2;

import com.danssion.java.spring.configuration.TestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain2 {
    public static void main(String[] args) {

        // @Configuration注解的spring容器加载方式，用AnnotationConfigApplicationContext替换ClassPathXmlApplicationContext
        ApplicationContext context = new AnnotationConfigApplicationContext(WebConfig.class);

        // 如果加载spring-context.xml文件：
        // ApplicationContext context = new
        // ClassPathXmlApplicationContext("spring-context.xml");

        // 获取bean
        TestBean2 tb2 = (TestBean2) context.getBean("testBean2");
        tb2.sayHello();
        TestBean tb = (TestBean) context.getBean("testBean");
        tb.sayHello();
    }
}

//output 各个bean生成
//TestConfiguration容器启动初始化。。。
//        TestBean2 sayHello...
//        TestBean sayHello...