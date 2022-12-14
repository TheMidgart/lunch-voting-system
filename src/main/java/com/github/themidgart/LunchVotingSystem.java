package com.github.themidgart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.themidgart.*")
public class LunchVotingSystem {
    public static void main(String[] args) {
        SpringApplication.run(LunchVotingSystem.class);
    }
}