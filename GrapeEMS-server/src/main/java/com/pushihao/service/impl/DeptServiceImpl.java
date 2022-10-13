package com.pushihao.service.impl;

import com.pushihao.dao.DeptDao;
import com.pushihao.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public List<String> getAllDeptName() {
        return deptDao.getAllDeptName();
    }

    @Override
    public Long getDeptIdByName(String deptName) {
        return deptDao.getDeptIdByName(deptName);
    }
}
