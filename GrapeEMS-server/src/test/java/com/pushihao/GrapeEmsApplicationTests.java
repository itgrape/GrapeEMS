package com.pushihao;

import com.pushihao.entity.Announce;
import com.pushihao.entity.Dept;
import com.pushihao.entity.User;
import com.pushihao.dao.AnnounceDao;
import com.pushihao.dao.DeptDao;
import com.pushihao.dao.LeaveApproveDao;
import com.pushihao.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest
class GrapeEmsApplicationTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AnnounceDao announceDao;

    @Autowired
    private DeptDao deptDao;

    @Autowired
    private LeaveApproveDao leaveApproveDao;

    @Test
    void testUserMapper() {
        userDao.getAllUserCenterUsers().forEach(System.out::println);
    }

    @Test
    void testGetOneUser() {
        System.out.println(userDao.getOneUserByEmail("pushihao@email.com"));
    }

    @Test
    void testAddNewUser() {
        Timestamp timestamp = new Timestamp(2022, 9, 5, 14, 17, 30, 0);
        User u = new User("李四", "男", 19, "110@qq.com", "lisi", "河南", "周口", "沈丘", timestamp, "正常", 1, 1, 1000L, 1000L);
        System.out.println(userDao.addNewUser(u));
    }

    @Test
    void testSelectOneUserById() {
        System.out.println(userDao.getOneUserById(1000L));
    }


    @Test
    void testInsertAnnounce() {
        Announce announce = new Announce();
        announce.setAnnounceCreateTime(new Timestamp(new Date().getTime()));
        announce.setAnnounceTitle("测试");
        announce.setAnnounceContent("测试公告内容");
        announce.setIsDeleted(1);
        announce.setDeptId(1000L);
        Integer result = announceDao.addOneAnnounce(announce);
        System.out.println("================================");
        System.out.println(result);
    }

    @Test
    void testGetAllDept() {
        List<Dept> deptList = deptDao.getAllDept();
        System.out.println(deptList.toString());
    }

    @Test
    void testGetAllLeaveApplication() {
        System.out.println("========================");
        System.out.println(leaveApproveDao.getAllLeaveApplication());
    }

    @Test
    void testDec() {
        System.out.println(29 / 10);
    }

}
