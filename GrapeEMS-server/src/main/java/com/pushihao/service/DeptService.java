package com.pushihao.service;

import com.pushihao.bean.Dept;

import java.util.List;

public interface DeptService {
    public List<String> getAllDeptName();

    public Long getDeptIdByName(String deptName);

    public List<Dept> getAllDept();

    public Boolean deletedOneDeptById(Long id);

    public Boolean addNewDept(String deptName);

    public Boolean editDept(Dept dept);
}
