package com.github.themidgart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({"classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-cache.xml"})
public class Main {

    @Autowired
    private static ApplicationContext context;
    public static void main(String[] args) {
//        System.out.println(context.getBeanDefinitionNames());
        SpringApplication.run(Main.class, args);
    }
}