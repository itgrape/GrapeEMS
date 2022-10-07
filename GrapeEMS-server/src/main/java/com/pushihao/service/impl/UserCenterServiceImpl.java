package com.pushihao.service.impl;

import com.pushihao.bean.User;
import com.pushihao.dao.DeptDao;
import com.pushihao.dao.RoleDao;
import com.pushihao.dao.UserDao;
import com.pushihao.pojo.AddUser;
import com.pushihao.pojo.QueryUser;
import com.pushihao.pojo.UserCenterUsers;
import com.pushihao.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCenterServiceImpl implements UserCenterService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private DeptDao deptDao;

    @Override
    public List<UserCenterUsers> getAllUserCenterUsers() {
        return userDao.getAllUserCenterUsers();
    }

    @Override
    public List<UserCenterUsers> queryUserCenterUsers(QueryUser queryUser) {
        return userDao.queryUserCenterUsers(queryUser);
    }

    @Override
    public Boolean addNewUser(AddUser addUser) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUserName(addUser.getUserName());
        user.setUserSex(addUser.getUserSex());
        user.setUserAge(addUser.getUserAge());
        user.setUserEmail(addUser.getUserEmail());
        user.setUserPassword(passwordEncoder.encode(addUser.getUserPassword()));
        user.setUserProvince(addUser.getUserProvince());
        user.setUserCity(addUser.getUserCity());
        user.setUserCommunity(addUser.getUserCommunity());
        user.setUserInterTime(addUser.getUserInterTime());
        user.setUserState(addUser.getUserState());
        user.setUserRole(1);
        user.setIsDeleted(1);
        user.setDeptId(deptDao.getDeptIdByName(addUser.getDeptName()));
        user.setRoleId(roleDao.getRoleIdByName(addUser.getRoleName()));
        Integer result = userDao.addNewUser(user);
        return result == 1;
    }

    @Override
    public UserCenterUsers getOneUserById(Long id) {
        return userDao.getOneUserById(id);
    }

    @Override
    public Boolean editOneUser(UserCenterUsers userCenterUsers) {
        User user = new User();
        user.setUserId(userCenterUsers.getUserId());
        user.setUserName(userCenterUsers.getUserName());
        user.setUserSex(userCenterUsers.getUserSex());
        user.setUserAge(userCenterUsers.getUserAge());
        user.setUserEmail(userCenterUsers.getUserEmail());
        user.setUserProvince(userCenterUsers.getUserProvince());
        user.setUserCity(userCenterUsers.getUserCity());
        user.setUserCommunity(userCenterUsers.getUserCommunity());
        user.setUserInterTime(userCenterUsers.getUserInterTime());
        user.setUserState(userCenterUsers.getUserState());
        user.setUserRole(1);
        user.setIsDeleted(1);
        user.setDeptId(deptDao.getDeptIdByName(userCenterUsers.getDeptName()));
        user.setRoleId(roleDao.getRoleIdByName(userCenterUsers.getRoleName()));
        Integer result = userDao.editOneUser(user);
        return result == 1;
    }

    @Override
    public Boolean deleteOneUserByUserId(Long id) {
        if (userDao.getOneUserById(id) == null) {
            return false;
        } else {
            Integer result = userDao.deleteOneUserById(id);
            return result == 1;
        }
    }
}