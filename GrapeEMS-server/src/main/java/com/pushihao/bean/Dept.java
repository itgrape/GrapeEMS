package com.pushihao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dept {
    private Long deptId;

    private String deptName;

    private Timestamp deptBuildTime;

    private Integer deptPersonNumber;

    private Integer isDeleted;
}
