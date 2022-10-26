package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCenterUsers {
    private Long userId;

    private String userName;

    private String userSex;

    private Integer userAge;

    private String deptName;

    private String roleName;

    private String userEmail;

    private String userPassword;

    private String userProvince;

    private String userCity;

    private String userCommunity;

    private Timestamp userInterTime;

    private String userState;
}
