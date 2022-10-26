package com.pushihao.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Announce {
    private Long announceId;

    private Timestamp announceCreateTime;

    private String announceTitle;

    private String announceContent;

    private Integer isDeleted;

    private Long deptId;

    private Dept dept;
}
