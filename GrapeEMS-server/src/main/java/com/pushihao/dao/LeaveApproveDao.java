package com.pushihao.dao;

import com.pushihao.bean.LeaveApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LeaveApproveDao {

    public List<LeaveApplication> getAllLeaveApplication();

    public LeaveApplication getLeaveApplicationById(@Param("id") Long id);

    public Integer updateLeaveApplication(LeaveApplication leaveApplication);

}
