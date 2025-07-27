package com.buildyouragent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.buildyouragent.mapper")
@SpringBootApplication
public class BuildYourAgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildYourAgentApplication.class, args);
    }

}
