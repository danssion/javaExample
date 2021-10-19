package com.danssion.java.spring.configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringBeanLifeTest {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringBeanLife.class);

        TestBean tb = (TestBean) context.getBean("testBean");
        tb.sayHello();
        System.out.println(tb);

        TestBean tb2 = (TestBean) context.getBean("testBean");
        tb2.sayHello();
        System.out.println(tb2);
    }
}
// output
//        TestConfiguration容器启动初始化。。。
//        TestConfiguration容器启动初始化。。。
//        TestBean 初始化。。。    表明initMethod生效
//        TestBean sayHello...
//        com.danssion.java.spring.configuration.TestBean@473b46c3     表明@Scope("prototype")生效
//        TestBean 初始化。。。
//        TestBean sayHello...
//        com.danssion.java.spring.configuration.TestBean@516be40f     表明@Scope("prototype")生效