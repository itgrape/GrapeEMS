package com.pushihao.dao;

import com.pushihao.bean.User;
import com.pushihao.pojo.UserCenterUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    public List<UserCenterUsers> getAllUserCenterUsers();

    public User getOneUserByEmail(@Param("userEmail") String userEmail);
}
