package com.ningdali.springbootwebdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.Arrays;
import java.util.Map;

@Controller
public class DemoController {

    @ResponseBody
    @RequestMapping("/hello")
    public String sayHello(){
        return "<h1>hello world<h1>";
    }

    //查出用户数据，在页面展示
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users",Arrays.asList("zhangsan","lisi","wangwu"));
        return "success";
    }

//
//    @RequestMapping({"/","/index.html"})
//    public String index(){
//        return "index";
//    }
}
