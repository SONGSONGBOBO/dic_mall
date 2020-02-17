package com.songbo.dicshop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.songbo.dicshop.mapper")
public class DicshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(DicshopApplication.class, args);
    }

}
