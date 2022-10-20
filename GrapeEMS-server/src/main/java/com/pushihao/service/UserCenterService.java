package com.pushihao.service;

import com.pushihao.pojo.AddUser;
import com.pushihao.pojo.QueryUser;
import com.pushihao.pojo.UserCenterUsers;

import java.util.List;

public interface UserCenterService {
    public List<UserCenterUsers> getAllUserCenterUsers();

    public Integer getTotalNum();

    public List<UserCenterUsers> queryUserCenterUsers(QueryUser queryUser);

    public Boolean addNewUser(AddUser addUser);

    public UserCenterUsers getOneUserById(Long id);

    public Boolean editOneUser(UserCenterUsers userCenterUsers);

    public Boolean deleteOneUserByUserId(Long id);

    public Integer getDeptPersonNumber(String deptName);
}
