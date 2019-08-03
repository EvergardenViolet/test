package com.xueliang.crud.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class HelloController {


    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello!!";
    }

    @RequestMapping("/success")
    public String success(){
        return "success";
    }

}
