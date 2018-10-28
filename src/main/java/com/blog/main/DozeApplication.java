package com.blog.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan // 扫描filter和servlet
@EnableTransactionManagement
@MapperScan({ "com.blog.main.doze.dao", "com.blog.main.doze.dao"})//与dao层的@Mapper二选一写上即可(0主要作用是扫包)
@EnableCaching
public class DozeApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return super.configure(builder);
        return builder.sources(this.getClass());
    }

    public static void main(String[] args) {
        SpringApplication.run(DozeApplication.class, args);
    }
}
