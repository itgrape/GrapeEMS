package com.pushihao.controller.user;

import com.alibaba.fastjson.JSON;
import com.pushihao.pojo.EditUserPassword;
import com.pushihao.pojo.UserCenterUsers;
import com.pushihao.service.UserCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/plainUser/info")
public class PlainUserController {
    @Autowired
    private UserCenterService userCenterService;

    @GetMapping("/getUserName/{userId}")
    public String getUserName(@PathVariable("userId") Long userId) {
        UserCenterUsers user = userCenterService.getOneUserById(userId);
        return user.getUserName();
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody EditUserPassword editUserPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String oldPassword = editUserPassword.getOldPassword();
        UserCenterUsers user = userCenterService.getOneUserById(editUserPassword.getUserId());

        if (passwordEncoder.matches(oldPassword, user.getUserPassword())) {
            user.setUserPassword(editUserPassword.getNewPassword());
            return JSON.toJSONString(userCenterService.editOneUser(user));
        } else {
            return JSON.toJSONString(false);
        }
    }
}
