package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryApproveLog {
    private String name;

    private String dept;

    private Integer kind;

    private String approveStartTime;

    private String approveEndTime;
}
