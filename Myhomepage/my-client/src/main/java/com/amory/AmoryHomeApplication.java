package com.amory;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.amory.mapper")
public class AmoryHomeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AmoryHomeApplication.class,args);
    }
}
