package com.pushihao.bean;

import com.pushihao.pojo.UserCenterUsers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackApplication {
    private Long backApplicationId;

    private Timestamp backApplicationCreateTime;

    private Integer backApplicationIsApprove;

    private Timestamp backApplicationApproveTime;

    private String backApplicationApproveResult;

    private Integer isDeleted;

    private Long userId;

    private Long leaveApplicationId;

    private UserCenterUsers user;

    private LeaveApplication leaveApplication;
}
