package com.pushihao.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RoleDao {
    public List<String> getAllRoleName();

    public Long getRoleIdByName(@Param("roleName") String name);
}
