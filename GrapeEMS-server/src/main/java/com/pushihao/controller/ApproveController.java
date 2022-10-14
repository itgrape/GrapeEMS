package com.pushihao.controller;

import com.alibaba.fastjson.JSON;
import com.pushihao.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/approve")
public class ApproveController {
    @Autowired
    private ApproveService approveService;

    @GetMapping("/getAllLeaveApplication")
    public String getAllLeaveApplication() {
        return JSON.toJSONString(approveService.getAllLeaveApplication());
    }

    @GetMapping("/getAllBackApplication")
    public String getAllBackApplication() {
        return JSON.toJSONString(approveService.getAllBackApplication());
    }

    @GetMapping("/acceptLeave/{id}")
    public String acceptLeave(@PathVariable("id") Long id) {
        return JSON.toJSONString(approveService.acceptLeave(id));
    }

    @GetMapping("/rejectLeave/{id}")
    public String rejectLeave(@PathVariable("id") Long id) {
        return JSON.toJSONString(approveService.rejectLeave(id));
    }

    @GetMapping("/acceptBack/{id}")
    public String acceptBack(@PathVariable("id") Long id) {
        return JSON.toJSONString(approveService.acceptBack(id));
    }
}
