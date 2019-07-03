package com.ningdali.springbootwebdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class LoginController {

    @PostMapping
    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Map<String,String> map){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            map.put("user",username);
            return "redirect:/main.html";
        }else {
            map.put("msg","账号或密码错误");
            return "index";
        }
    }
}
