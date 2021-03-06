package com.example.epi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.example.epi.mapper")
@ServletComponentScan("com.example.epi.servlet")
public class EpiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpiApplication.class, args);
    }

}
