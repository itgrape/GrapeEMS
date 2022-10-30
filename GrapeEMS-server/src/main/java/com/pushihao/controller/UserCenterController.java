package com.pushihao.controller;

import com.alibaba.fastjson.JSON;
import com.pushihao.pojo.AddUser;
import com.pushihao.pojo.QueryUser;
import com.pushihao.pojo.UserCenterUsers;
import com.pushihao.service.DeptService;
import com.pushihao.service.RoleService;
import com.pushihao.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userCenter")
public class UserCenterController {
    @Autowired
    private UserCenterService userCenterService;

    @Autowired
    private DeptService deptService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("/getAllUserCenterUsers")
    public String getAllUserCenterUsers() {
        return JSON.toJSONString(userCenterService.getAllUserCenterUsers());
    }

    @GetMapping("/getTotalNum")
    public String getTotalNum() {
        return JSON.toJSONString(userCenterService.getTotalNum());
    }

    @RequestMapping("/getAllDeptName")
    public String getAllDeptName() {
        return JSON.toJSONString(deptService.getAllDeptName());
    }

    @RequestMapping("/getAllRoleName")
    public String getAllRoleName() {
        return JSON.toJSONString(roleService.getAllRoleName());
    }

    @PostMapping("/queryUserCenterUsers")
    public String queryUserCenterUsers(@RequestBody QueryUser queryUser) {
        return JSON.toJSONString(userCenterService.queryUserCenterUsers(queryUser));
    }

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody AddUser addUser) {
        return JSON.toJSONString(userCenterService.addNewUser(addUser));
    }

    @GetMapping("/getOneUserById/{id}")
    public String getOneUserById(@PathVariable("id") Long id) {
        return JSON.toJSONString(userCenterService.getOneUserById(id));
    }

    @PostMapping("/editOneUser")
    public String editOneUser(@RequestBody UserCenterUsers userCenterUsers) {
        return JSON.toJSONString(userCenterService.editOneUser(userCenterUsers));
    }

    @GetMapping("/deleteOneUserById/{id}")
    public String deleteOneUserById(@PathVariable("id") Long id) {
        return JSON.toJSONString(userCenterService.deleteOneUserByUserId(id));
    }

    @GetMapping("/getUserSexInfo")
    public String getUserSexInfo() {
        return JSON.toJSONString(userCenterService.getUserSexInfo());
    }

    @GetMapping("/getUserAgeInfo")
    public String getUserAgeInfo() {
        return JSON.toJSONString(userCenterService.getUserAgeInfo());
    }
}
