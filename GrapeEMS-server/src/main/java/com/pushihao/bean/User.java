package com.pushihao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long userId;

    private String userName;

    private String userSex;

    private Integer userAge;

    private String userEmail;

    private String userPassword;

    private String userProvince;

    private String userCity;

    private String userCommunity;

    private Timestamp userInterTime;

    private String userState;

    private Integer userRole;

    private Integer isDeleted;

    private Long deptId;

    private Long roleId;

    public User(String userName, String userSex, Integer userAge, String userEmail, String userPassword, String userProvince, String userCity, String userCommunity, Timestamp userInterTime, String userState, Integer userRole, Integer isDeleted, Long deptId, Long roleId) {
        this.userName = userName;
        this.userSex = userSex;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userProvince = userProvince;
        this.userCity = userCity;
        this.userCommunity = userCommunity;
        this.userInterTime = userInterTime;
        this.userState = userState;
        this.userRole = userRole;
        this.isDeleted = isDeleted;
        this.deptId = deptId;
        this.roleId = roleId;
    }
}
