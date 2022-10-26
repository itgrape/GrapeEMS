package com.pushihao.service.impl;

import com.pushihao.bean.Dept;
import com.pushihao.bean.User;
import com.pushihao.dao.DeptDao;
import com.pushihao.dao.RoleDao;
import com.pushihao.dao.UserDao;
import com.pushihao.pojo.*;
import com.pushihao.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

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
    public Integer getTotalNum() {
        return userDao.getTotalNum();
    }

    @Override
    public List<UserCenterUsers> queryUserCenterUsers(QueryUser queryUser) {
        return userDao.queryUserCenterUsers(queryUser);
    }

    @Override
    public Boolean addNewUser(AddUser addUser) {
        if (userDao.getOneUserByEmail(addUser.getUserEmail()) != null) {
            return false;
        } else {
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

            Dept dept = new Dept();
            dept.setDeptId(deptDao.getDeptIdByName(addUser.getDeptName()));
            dept.setDeptPersonNumber(getDeptPersonNumber(addUser.getDeptName()));
            int result2 = deptDao.updateDept(dept);
            return result == 1 && result2 == 1;
        }
    }

    @Override
    public UserCenterUsers getOneUserById(Long id) {
        return userDao.getOneUserById(id);
    }

    @Override
    public Boolean editOneUser(UserCenterUsers userCenterUsers) {
        if (userDao.getOneUserByEmail(userCenterUsers.getUserEmail()) == null || Objects.equals(userDao.getOneUserByEmail(userCenterUsers.getUserEmail()).getUserId(), userCenterUsers.getUserId())) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            User user = new User();
            user.setUserId(userCenterUsers.getUserId());
            user.setUserName(userCenterUsers.getUserName());
            user.setUserSex(userCenterUsers.getUserSex());
            user.setUserAge(userCenterUsers.getUserAge());
            user.setUserEmail(userCenterUsers.getUserEmail());
            user.setUserPassword(passwordEncoder.encode(userCenterUsers.getUserPassword()));
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

            Dept dept = new Dept();
            dept.setDeptId(deptDao.getDeptIdByName(userCenterUsers.getDeptName()));
            dept.setDeptPersonNumber(getDeptPersonNumber(userCenterUsers.getDeptName()));
            int result2 = deptDao.updateDept(dept);
            return result == 1 && result2 == 1;
        } else {
            return false;
        }
    }

    @Override
    public Boolean deleteOneUserByUserId(Long id) {
        if (userDao.getOneUserById(id) == null) {
            return false;
        } else {
            UserCenterUsers users = userDao.getOneUserById(id);
            Integer result = userDao.deleteOneUserById(id);
            Dept dept = new Dept();
            dept.setDeptId(deptDao.getDeptIdByName(users.getDeptName()));
            dept.setDeptPersonNumber(getDeptPersonNumber(users.getDeptName()));
            int result2 = deptDao.updateDept(dept);
            return result == 1 && result2 == 1;
        }
    }

    @Override
    public Integer getDeptPersonNumber(String deptName) {
        Long deptId = deptDao.getDeptIdByName(deptName);
        return userDao.getDeptPersonNumber(deptId);
    }

    @Override
    public UserSexInfo getUserSexInfo() {
        List<UserCenterUsers> users = userDao.getAllUserCenterUsers();
        if (users != null) {
            UserSexInfo userSexInfo = new UserSexInfo(0, 0, 0);
            for (UserCenterUsers user : users) {
                switch (user.getUserSex()) {
                    case "男" -> {
                        int num = userSexInfo.getMenNum();
                        userSexInfo.setMenNum(++num);
                        break;
                    }
                    case "女" -> {
                        int num = userSexInfo.getWomenNum();
                        userSexInfo.setWomenNum(++num);
                        break;
                    }
                    case "非二元性别" -> {
                        int num = userSexInfo.getOther();
                        userSexInfo.setOther(++num);
                        break;
                    }
                }
            }
            return userSexInfo;
        } else {
            return new UserSexInfo(0, 0, 0);
        }
    }

    @Override
    public UserAgeInfo getUserAgeInfo() {
        List<UserCenterUsers> users = userDao.getAllUserCenterUsers();
        if (users != null) {
            UserAgeInfo userAgeInfo = new UserAgeInfo(0, 0, 0, 0, 0);
            for (UserCenterUsers user : users) {
                switch (user.getUserAge() / 10) {
                    case 1 -> userAgeInfo.oneAgeAdd();
                    case 2 -> userAgeInfo.twoAgeAdd();
                    case 3 -> userAgeInfo.threeAgeAdd();
                    case 4 -> userAgeInfo.fourAgeAdd();
                    case 5 -> userAgeInfo.fiveAgeAdd();
                }
            }
            return userAgeInfo;
        } else {
            return new UserAgeInfo(0, 0, 0, 0, 0);
        }
    }
}
