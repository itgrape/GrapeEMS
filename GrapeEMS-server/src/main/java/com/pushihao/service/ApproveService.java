package com.pushihao.service;

import com.pushihao.bean.ApproveLog;
import com.pushihao.bean.BackApplication;
import com.pushihao.bean.LeaveApplication;
import com.pushihao.dao.BackApproveDao;
import com.pushihao.pojo.QueryApproveLog;

import java.util.List;

public interface ApproveService {

    public List<LeaveApplication> getAllLeaveApplication();

    public Boolean acceptLeave(Long id);

    public Boolean rejectLeave(Long id);

    public List<BackApplication> getAllBackApplication();

    public Boolean acceptBack(Long id);

    public List<ApproveLog<?>> getAllApproveLog();

    public List<ApproveLog<?>> queryApproveLog(QueryApproveLog queryApproveLog);

    public Boolean deleteOneApproveLog(Long id);

    public Boolean addOneLeaveApprove(LeaveApplication leaveApplication);

    public List<LeaveApplication> getLeaveApplicationByUserId(Long userId);

    public Boolean addOneBackApprove(BackApplication backApplication);
}
