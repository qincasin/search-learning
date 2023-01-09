package com.qjx.es;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.qjx.es.dao")
public class Es1Application {

    public static void main(String[] args) {
        SpringApplication.run(Es1Application.class, args);
    }

}
