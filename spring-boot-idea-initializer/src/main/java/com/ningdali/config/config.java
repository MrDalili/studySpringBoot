package com.ningdali.config;

import com.ningdali.pojo.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 指明当前类为一个配置类，就是用来代替之前的spring配置文件的
 */
@Configuration
public class config {
    //将方法的返回值添加到容器里面，容器中的默认id就是方法名
    @Bean
    public HelloService helloService(){
        return new HelloService();
    }
}
