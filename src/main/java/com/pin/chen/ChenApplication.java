package com.pin.chen;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.pin.chen.dao.mapper"})
public class ChenApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChenApplication.class, args);
    }

}
