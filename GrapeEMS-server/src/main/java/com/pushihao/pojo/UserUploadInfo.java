package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUploadInfo {
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

    private String userInterTime;
}
