package com.pushihao.controller;

import com.alibaba.fastjson.JSON;
import com.pushihao.bean.Dept;
import com.pushihao.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    @GetMapping("/getAllDept")
    public String getAllDept() {
        return JSON.toJSONString(deptService.getAllDept());
    }

    @GetMapping("/deleteDept/{id}")
    public String deleteDept(@PathVariable("id") Long id) {
        return JSON.toJSONString(deptService.deletedOneDeptById(id));
    }

    @GetMapping("/addNewDept/{deptName}")
    public String addNewDept(@PathVariable("deptName") String deptName) {
        return JSON.toJSONString(deptService.addNewDept(deptName));
    }

    @GetMapping("/editDept/{deptId}/{deptName}")
    public String editDept(@PathVariable("deptId") Long id, @PathVariable("deptName") String deptName) {
        Dept dept = new Dept();
        dept.setDeptName(deptName);
        dept.setDeptId(id);
        return JSON.toJSONString(deptService.editDept(dept));
    }
}
