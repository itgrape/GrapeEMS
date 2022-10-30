package com.pushihao.controller.user;

import com.alibaba.fastjson.JSON;
import com.pushihao.entity.BackApplication;
import com.pushihao.entity.LeaveApplication;
import com.pushihao.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plainUser/approve")
public class PlainUserApproveController {
    @Autowired
    private ApproveService approveService;

    @PostMapping("/leaveApprove")
    public String leaveApprove(@RequestBody LeaveApplication leaveApplication) {
        return JSON.toJSONString(approveService.addOneLeaveApprove(leaveApplication));
    }

    @PostMapping("/backApprove")
    public String backApprove(@RequestBody BackApplication backApplication) {
        return JSON.toJSONString(approveService.addOneBackApprove(backApplication));
    }

    @GetMapping("/getLeaveApplicationByUserId/{id}")
    public String getLeaveApplicationByUserId(@PathVariable("id") Long id) {
        return JSON.toJSONString(approveService.getLeaveApplicationByUserId(id));
    }
}
