package com.pushihao.service;

import java.util.List;

public interface DeptService {
    public List<String> getAllDeptName();

    public Long getDeptIdByName(String deptName);
}
