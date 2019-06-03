package cn.dox.jwt.controller;

import cn.dox.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: weidx
 * @date: 2019/6/4
 */
@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/auth/login")
    public String authLogin(String username, String password) {
        return authService.login(username, password);
    }

    @GetMapping(value = "/user/hi")
    public String userHi(@RequestParam("name") String name) {
        return "hi " + name + " , you have 'user' role";
    }

    @GetMapping(value = "/admin/hi")
    public String adminHi(@RequestParam("name") String name) {
        return "hi " + name + " , you have 'admin' role";
    }

}
