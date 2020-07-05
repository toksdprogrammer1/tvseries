package com.global.accelerex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TVSeriesApplication {

    public static void main(String[] args) {
        SpringApplication.run(TVSeriesApplication.class, args);
    }

}