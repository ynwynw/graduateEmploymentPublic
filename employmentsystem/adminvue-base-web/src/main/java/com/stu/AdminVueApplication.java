package com.stu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.stu.system.mapper")
//开启SwaggerUI
@EnableSwagger2
public class AdminVueApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminVueApplication.class);
    }
}
