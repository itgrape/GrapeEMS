package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUser {
    private String userName;

    private String userSex;

    private Integer userAge;

    private String userEmail;

    private String userPassword;

    private String deptName;

    private String roleName;

    private String userState;

    private String userProvince;

    private String userCity;

    private String userCommunity;

    private Timestamp userInterTime;
}
