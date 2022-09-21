package com.pushihao.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/h1")
    @PreAuthorize("hasAuthority('admin')")
    public String h1() {
        return "Hello, world!";
    }


}
