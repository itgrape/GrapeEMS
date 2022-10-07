package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QueryUser {
    private String userName;

    private String userSex;

    private Integer userAgeStart;

    private Integer userAgeEnd;

    private String deptName;

    private String roleName;

    private String userState;

    private String userProvince;

    private String userCity;

    private String userCommunity;

    private String userInterTimeStart;

    private String userInterTimeEnd;
}
