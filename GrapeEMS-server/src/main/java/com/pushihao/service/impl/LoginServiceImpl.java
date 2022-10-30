package com.pushihao.service.impl;

import com.pushihao.pojo.LoginUser;
import com.pushihao.pojo.ResponseResult;
import com.pushihao.entity.User;
import com.pushihao.service.LoginService;
import com.pushihao.utils.JwtUtil;
import com.pushihao.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    //AuthenticationManager authenticate 进行用户认证
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;


    @Override
    public ResponseResult<Map<String, String>> login(User user) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserEmail(), user.getUserPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);


        //如果认证没通过，抛异常，给出提示
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("登录失败");
        }

        //如果认证通过了，使用uid生成一个jwt，jwt存入ResponseResult进行返回
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        Long uid = loginUser.getUser().getUserId();
        String jwt = JwtUtil.createJWT(uid.toString());

        Map<String, String> result = new HashMap<>();
        result.put("id", uid.toString());
        result.put("token", jwt);
        result.put("role", loginUser.getUser().getUserRole().toString());

        //把完整的用户信息存入redis，uid作为key
        redisCache.setCacheObject("login:" + uid, loginUser);
        return new ResponseResult<Map<String, String>>(200, "登录成功", result);
    }

    @SuppressWarnings({"all"})
    @Override
    public ResponseResult logout() {
        //获取SecurityContextHolder中的uid
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long uid = loginUser.getUser().getUserId();

        //删除redis中的值
        redisCache.deleteObject("login:" + uid);
        return new ResponseResult(200, "注销成功");
    }
}
