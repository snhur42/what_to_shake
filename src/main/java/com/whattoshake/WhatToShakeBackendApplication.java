package com.whattoshake;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application-default.properties")
public class WhatToShakeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhatToShakeBackendApplication.class, args);
    }

}
