package com.pushihao.dao;

import com.pushihao.entity.User;
import com.pushihao.pojo.QueryUser;
import com.pushihao.pojo.UserCenterUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    public List<UserCenterUsers> getAllUserCenterUsers();

    public Integer getTotalNum();

    public User getOneUserByEmail(@Param("userEmail") String userEmail);

    public List<UserCenterUsers> queryUserCenterUsers(QueryUser queryUser);

    public Integer addNewUser(User user);

    public UserCenterUsers getOneUserById(@Param("userId") Long id);

    public Integer editOneUser(User user);

    public Integer deleteOneUserById(@Param("userId") Long id);

    public Integer getDeptPersonNumber(@Param("deptId") Long deptId);

    public Integer addMoreUser(List<User> users);
}
