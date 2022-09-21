package com.pushihao;

import com.pushihao.dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
}
