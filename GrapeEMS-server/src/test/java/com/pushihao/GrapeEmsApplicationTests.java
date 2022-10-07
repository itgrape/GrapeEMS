package com.pushihao;

import com.pushihao.bean.User;
import com.pushihao.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;

@SpringBootTest
class GrapeEmsApplicationTests {

    @Autowired
    private UserDao userDao;

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
}
