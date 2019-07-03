package com.ningdali.springbootwebdemo.config;

import com.ningdali.springbootwebdemo.component.LoginHandlerInterceptor;
import com.ningdali.springbootwebdemo.component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//是一个配置类
@Configuration
public class DemoConfig extends WebMvcConfigurerAdapter {

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
        //静态资源；  *.css , *.js
        //SpringBoot已经做好了静态资源映射
                registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/")
                        .excludePathPatterns("/index.html","/","/user/login");
    }

    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("index");
                registry.addViewController("/index.html").setViewName("index");
                registry.addViewController("/main.html").setViewName("dashboard");
            }
        };
        return adapter;
    }

    @Bean
    public LocaleResolver myLocal(){
        return  new MyLocaleResolver();
    }
}
