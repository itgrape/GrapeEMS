package com.pushihao.service.impl;

import com.pushihao.bean.Dept;
import com.pushihao.dao.DeptDao;
import com.pushihao.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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

    @Override
    public List<Dept> getAllDept() {
        return deptDao.getAllDept();
    }

    @Override
    public Boolean deletedOneDeptById(Long id) {
        Integer result = deptDao.deleteOneDeptById(id);
        return result == 1;
    }

    @Override
    public Boolean addNewDept(String deptName) {
        Dept dept = new Dept();
        dept.setDeptName(deptName);
        dept.setDeptBuildTime(new Timestamp(new Date().getTime()));
        dept.setDeptPersonNumber(0);
        Integer result = deptDao.addNewDept(dept);
        return result == 1;
    }

    @Override
    public Boolean editDept(Dept dept) {
        Integer result = deptDao.editDept(dept);
        return result == 1;
    }
}
