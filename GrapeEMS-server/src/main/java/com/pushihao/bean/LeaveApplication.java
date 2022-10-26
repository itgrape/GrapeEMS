package com.pushihao.bean;

import com.pushihao.pojo.UserCenterUsers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveApplication {
    private Long leaveApplicationId;

    private Timestamp leaveApplicationCreateTime;

    private String leaveApplicationReason;

    private String leaveApplicationDetail;

    private Timestamp leaveApplicationLeaveStartTime;

    private Timestamp leaveApplicationLeaveEndTime;

    private Integer leaveApplicationIsApprove;

    private Timestamp leaveApplicationApproveTime;

    private String leaveApplicationApproveResult;

    private Integer leaveApplicationIsDestroy;

    private Integer isDeleted;

    private Long userId;

    private UserCenterUsers user;
}
