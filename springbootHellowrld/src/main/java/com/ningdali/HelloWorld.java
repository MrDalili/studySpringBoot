package com.ningdali;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用来标注这个是一个springboot程序
 */
@SpringBootApplication
public class HelloWorld {
    public static void main(String[] args) {
        //以HelloWorld为主类，args当参数启动
        SpringApplication.run(HelloWorld.class,args);
    }
}
