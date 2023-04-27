package com.github.themidgart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.themidgart.*")
@EnableCaching
@Slf4j
public class LunchVotingSystem {
    public static void main(String[] args) {
        SpringApplication.run(LunchVotingSystem.class);
        log.info("\n_#####_######__##########_######__##########_######__######## \n" +
                "_#####_######_###########_######_###########_######_######## \n" +
                "_############_##################_##################_####### \n" +
                "_######################################################### \n" +
                "_##################_##################_################## \n" +
                "_#################__#################__################# \n" +
                "_#######_########___#######_########___#######_######## \n" +
                "_######__#######____######__#######____######__####### \n" +
                "_#####____#####_____#####___#####______#####____#####");
    }

}