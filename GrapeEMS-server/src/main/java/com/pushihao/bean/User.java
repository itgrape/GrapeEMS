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

    private Integer deptId;

    private Integer roleId;

}
