package com.pushihao.service;

import com.pushihao.bean.User;
import com.pushihao.pojo.ResponseResult;

public interface LoginService {

    public ResponseResult login(User user);

    public ResponseResult logout();

}
