package com.pushihao.controller;

import com.alibaba.fastjson.JSON;
import com.pushihao.pojo.QueryApproveLog;
import com.pushihao.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/getAllApproveLogs")
    public String getAllApproveLogs() {
        return JSON.toJSONString(approveService.getAllApproveLog());
    }

    @PostMapping("/queryApproveLog")
    public String queryApproveLog(@RequestBody QueryApproveLog queryApproveLog) {
        return JSON.toJSONString(approveService.queryApproveLog(queryApproveLog));
    }

    @GetMapping("/deleteOneApproveLog/{id}")
    public String deleteOneApproveLog(@PathVariable("id") Long id) {
        return JSON.toJSONString(approveService.deleteOneApproveLog(id));
    }
}
