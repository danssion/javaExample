package com.danssion.java.spring.configuration2;

import com.danssion.java.spring.configuration.SpringBeanLife;
import com.danssion.java.spring.configuration.SpringConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource("classpath:applicationContextConfiguration2.xml")
@Import(SpringConfiguration.class)
//@Import(SpringBeanLife.class)  //在@configuration中引入其它注解配置
public class WebConfig {

}
