package com.pushihao.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DeptDao {
    public List<String> getAllDeptName();

    public String getDeptNameById(@Param("deptId") Long id);

    public Long getDeptIdByName(@Param("deptName") String name);
}
