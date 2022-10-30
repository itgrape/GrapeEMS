package com.pushihao.service;

import com.pushihao.entity.User;
import com.pushihao.pojo.ResponseResult;

public interface LoginService {

    public ResponseResult login(User user);

    public ResponseResult logout();

}
