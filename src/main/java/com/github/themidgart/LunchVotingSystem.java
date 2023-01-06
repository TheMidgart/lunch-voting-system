package com.github.themidgart;

import com.github.themidgart.web.converter.LocalDateFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan("com.github.themidgart.*")
public class LunchVotingSystem {
    public static void main(String[] args) {
        SpringApplication.run(LunchVotingSystem.class);
    }

    @Configuration
    static class MyConfig implements WebMvcConfigurer {
        @Override
        public void addFormatters(FormatterRegistry registry) {
            registry.addFormatter(new LocalDateFormatter());
        }
    }
}