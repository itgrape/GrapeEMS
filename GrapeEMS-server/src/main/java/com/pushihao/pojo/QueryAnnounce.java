package com.pushihao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryAnnounce {
    private Long deptId;

    private String deptName;

    private String startTime;

    private String endTime;
}
