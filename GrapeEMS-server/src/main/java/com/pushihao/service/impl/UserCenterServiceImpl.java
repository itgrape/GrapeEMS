package com.pushihao.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.util.ListUtils;
import com.pushihao.entity.Dept;
import com.pushihao.entity.User;
import com.pushihao.dao.DeptDao;
import com.pushihao.dao.RoleDao;
import com.pushihao.dao.UserDao;
import com.pushihao.pojo.*;
import com.pushihao.service.UserCenterService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.ArrayList;
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
            matchUser(passwordEncoder, user, addUser.getUserName(), addUser.getUserSex(), addUser.getUserAge(), addUser.getUserEmail(), addUser.getUserPassword(), addUser.getUserProvince(), addUser.getUserCity(), addUser.getUserCommunity(), addUser.getUserInterTime(), addUser.getUserState(), addUser.getDeptName(), addUser.getRoleName(), addUser);
            Integer result = userDao.addNewUser(user);

            Dept dept = new Dept();
            dept.setDeptId(deptDao.getDeptIdByName(addUser.getDeptName()));
            dept.setDeptPersonNumber(getDeptPersonNumber(addUser.getDeptName()));
            int result2 = deptDao.updateDept(dept);
            return result == 1 && result2 == 1;
        }
    }

    private void matchUser(BCryptPasswordEncoder passwordEncoder, User user, String userName, String userSex, Integer userAge, String userEmail, String userPassword, String userProvince, String userCity, String userCommunity, Timestamp userInterTime, String userState, String deptName, String roleName, AddUser addUser) {
        user.setUserName(userName);
        user.setUserSex(userSex);
        user.setUserAge(userAge);
        user.setUserEmail(userEmail);
        user.setUserPassword(passwordEncoder.encode(userPassword));
        user.setUserProvince(userProvince);
        user.setUserCity(userCity);
        user.setUserCommunity(userCommunity);
        user.setUserInterTime(userInterTime);
        user.setUserState(userState);
        user.setUserRole(1);
        user.setIsDeleted(1);
        user.setDeptId(deptDao.getDeptIdByName(deptName));
        user.setRoleId(roleDao.getRoleIdByName(roleName));
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
            matchUser(passwordEncoder, user, userCenterUsers.getUserName(), userCenterUsers.getUserSex(), userCenterUsers.getUserAge(), userCenterUsers.getUserEmail(), userCenterUsers.getUserPassword(), userCenterUsers.getUserProvince(), userCenterUsers.getUserCity(), userCenterUsers.getUserCommunity(), userCenterUsers.getUserInterTime(), userCenterUsers.getUserState(), userCenterUsers.getDeptName(), userCenterUsers.getRoleName(), null);
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

    @Override
    public void downloadUser(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");

        String fileName = URLEncoder.encode("用户信息", StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        EasyExcel.write(response.getOutputStream(), UserDownloadInfo.class)
                .head(head())
                .sheet("用户信息")
                .doWrite(data());
    }

    private List<List<String>> head() {
        List<String> head1 = ListUtils.newArrayList("姓名");
        List<String> head2 = ListUtils.newArrayList("性别");
        List<String> head3 = ListUtils.newArrayList("年龄");
        List<String> head4 = ListUtils.newArrayList("部门");
        List<String> head5 = ListUtils.newArrayList("身份");
        List<String> head6 = ListUtils.newArrayList("邮箱");
        List<String> head7 = ListUtils.newArrayList("省");
        List<String> head8 = ListUtils.newArrayList("市");
        List<String> head9 = ListUtils.newArrayList("区");
        List<String> head10 = ListUtils.newArrayList("入职时间");
        List<List<String>> list = ListUtils.newArrayList();
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        list.add(head7);
        list.add(head8);
        list.add(head9);
        list.add(head10);
        return list;
    }

    private List<UserDownloadInfo> data() {
        List<UserDownloadInfo> list = ListUtils.newArrayList();
        List<UserCenterUsers> users = userDao.getAllUserCenterUsers();
        for (UserCenterUsers user : users) {
            UserDownloadInfo u = new UserDownloadInfo(
                    user.getUserName(), user.getUserSex(),
                    user.getUserAge(), user.getDeptName(),
                    user.getRoleName(), user.getUserEmail(),
                    user.getUserProvince(), user.getUserCity(),
                    user.getUserCommunity(), user.getUserInterTime().toString().substring(0, user.getUserInterTime().toString().indexOf(' '))
            );
            list.add(u);
        }
        return list;
    }

    @Override
    public Boolean saveBatchUser(List<UserUploadInfo> userList) {
        try {
            List<User> users = new ArrayList<>();
            for (UserUploadInfo userUploadInfo : userList) {
                User user = new User();
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                matchUser(passwordEncoder, user, userUploadInfo.getUserName(), userUploadInfo.getUserSex(), userUploadInfo.getUserAge(), userUploadInfo.getUserEmail(), userUploadInfo.getUserPassword(), userUploadInfo.getUserProvince(), userUploadInfo.getUserCity(), user.getUserCommunity(), handleDate(userUploadInfo.getUserInterTime()), null, userUploadInfo.getDeptName(), userUploadInfo.getRoleName(), null);
                users.add(user);
            }
            userDao.addMoreUser(users);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    Timestamp handleDate(String str) {
        int year = 0, month = 0, day = 0;

        if (!Objects.isNull(str)) {
            year = Integer.parseInt(str.split("-")[0]) - 1900;
            month = Integer.parseInt(str.split("-")[1]) - 1;

            String s = str.split("-")[2];
            int index = s.indexOf(' ');
            day = Integer.parseInt(s.substring(0, index));
        }

        return new Timestamp(year, month, day, 0, 0, 0, 0);
    }
}