package com.pushihao.service.impl;

import com.pushihao.bean.User;
import com.pushihao.dao.UserDao;
import com.pushihao.pojo.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //因为用户名不唯一，所以根据邮箱查询用户登录
        User user = userDao.getOneUserByEmail(username);

        //如何查询不到数据，就抛出异常
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }

        //赋予用户权限信息
        List<String> list = new ArrayList<>();
        if (user.getUserRole() == 0) {
            list.add("admin");
        } else if (user.getUserRole() == 1) {
            list.add("plainUser");
        }

        return new LoginUser(user, list);
    }

}
