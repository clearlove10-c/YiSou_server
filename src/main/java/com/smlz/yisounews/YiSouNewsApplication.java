package com.smlz.yisounews;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.smlz.yisounews.mapper")
public class YiSouNewsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YiSouNewsApplication.class, args);
    }

}
