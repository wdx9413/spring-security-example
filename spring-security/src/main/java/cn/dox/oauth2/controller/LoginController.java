package cn.dox.oauth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: weidx
 * @date: 2019/6/3
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String hello(@RequestParam(value = "error", required = false) String error, String username, String password) {
        if (error == null) {
            return "login success";
        } else {
            return "login error";
        }
    }
}
