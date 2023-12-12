package com.dynasty.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.dynasty.blog.dao")
public class MyBlogUser9961Application {
  public static void main(String[] args) {
    SpringApplication.run(MyBlogUser9961Application.class, args);
  }
}