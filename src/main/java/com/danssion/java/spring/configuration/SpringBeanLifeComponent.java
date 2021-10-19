package com.danssion.java.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
//添加自动扫描注解，basePackages为TestBean包路径
@ComponentScan(basePackages = "com.danssion.java.spring.configuration")
public class SpringBeanLifeComponent {
    public SpringBeanLifeComponent() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }

    /*// @Bean注解注册bean,同时可以指定初始化和销毁方法
    // @Bean(name="testNean",initMethod="start",destroyMethod="cleanUp")
    @Bean
    @Scope("prototype")
    public TestBean testBean() {
        return new TestBean();
    }*/
}