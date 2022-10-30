package com.pushihao.controller;

import com.pushihao.entity.User;
import com.pushihao.pojo.ResponseResult;
import com.pushihao.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings({"all"})
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseResult login(@RequestBody User user) {
        return loginService.login(user);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseResult logout() {
        return loginService.logout();
    }
}
