package com.pushihao.dao;

import com.pushihao.bean.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptDao {
    public List<String> getAllDeptName();

    public List<Dept> getAllDept();

    public String getDeptNameById(@Param("deptId") Long id);

    public Long getDeptIdByName(@Param("deptName") String name);

    public Integer deleteOneDeptById(@Param("id") Long id);

    public Integer addNewDept(Dept dept);

    public Integer editDept(Dept dept);

    public Integer updateDept(Dept dept);
}
