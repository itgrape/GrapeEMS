package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDownloadInfo {
    private String name;

    private String sex;

    private Integer age;

    private String deptName;

    private String roleName;

    private String email;

    private String province;

    private String city;

    private String community;

    private String interTime;
}
