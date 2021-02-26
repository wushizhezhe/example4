package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangcz7
 * @Created 2021/2/26 9:42 上午.
 */
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(){
        return "hello world!";
    }
}
