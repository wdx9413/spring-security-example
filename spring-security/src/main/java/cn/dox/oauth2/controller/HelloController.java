package cn.dox.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: weidx
 * @date: 2019/6/1
 */
@RestController
public class HelloController {
    @GetMapping("/")
    public String i() {
        return "empty";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
