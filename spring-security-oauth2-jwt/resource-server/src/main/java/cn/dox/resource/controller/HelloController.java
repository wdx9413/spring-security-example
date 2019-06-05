package cn.dox.resource.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: weidx
 * @date: 2019/6/5
 */
@RestController
public class HelloController {

    @PostMapping("/api/hi")
    public String hi(String name) {
        return String.format("hi , %s", name);
    }
}
