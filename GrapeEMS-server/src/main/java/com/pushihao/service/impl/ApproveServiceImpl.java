package com.pushihao.service.impl;

import com.pushihao.bean.ApproveLog;
import com.pushihao.bean.BackApplication;
import com.pushihao.bean.LeaveApplication;
import com.pushihao.dao.ApproveLogDao;
import com.pushihao.dao.BackApproveDao;
import com.pushihao.dao.LeaveApproveDao;
import com.pushihao.dao.UserDao;
import com.pushihao.pojo.QueryApproveLog;
import com.pushihao.service.ApproveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings({"all"})
public class ApproveServiceImpl implements ApproveService {
    @Autowired
    private LeaveApproveDao leaveApproveDao;

    @Autowired
    private BackApproveDao backApproveDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ApproveLogDao approveLogDao;

    @Override
    public List<LeaveApplication> getAllLeaveApplication() {
        List<LeaveApplication> leaveApplications = leaveApproveDao.getAllLeaveApplication();
        for (LeaveApplication leaveApplication : leaveApplications) {
            leaveApplication.setUser(userDao.getOneUserById(leaveApplication.getUserId()));
        }
        return leaveApplications;
    }

    @Override
    public Boolean acceptLeave(Long id) {
        LeaveApplication leaveApplication = leaveApproveDao.getLeaveApplicationById(id);
        leaveApplication.setLeaveApplicationIsApprove(1);
        leaveApplication.setLeaveApplicationApproveTime(new Timestamp(new Date().getTime()));
        leaveApplication.setLeaveApplicationApproveResult("同意");
        return handleLeave(id, leaveApplication);
    }

    @Override
    public Boolean rejectLeave(Long id) {
        LeaveApplication leaveApplication = leaveApproveDao.getLeaveApplicationById(id);
        leaveApplication.setLeaveApplicationIsApprove(1);
        leaveApplication.setLeaveApplicationApproveTime(new Timestamp(new Date().getTime()));
        leaveApplication.setLeaveApplicationApproveResult("拒绝");
        return handleLeave(id, leaveApplication);
    }

    private Boolean handleLeave(Long id, LeaveApplication leaveApplication) {
        int result1 = leaveApproveDao.updateLeaveApplication(leaveApplication);

        ApproveLog<?> approveLog = new ApproveLog<>();
        approveLog.setApproveLogKind(1);
        approveLog.setApproveLogContentId(id);
        int result2 = approveLogDao.addOneApproveLog(approveLog);
        return (result1 == 1 && result2 == 1);
    }

    @Override
    public List<BackApplication> getAllBackApplication() {
        List<BackApplication> backApplications = backApproveDao.getAllBackApplication();
        for (BackApplication backApplication : backApplications) {
            backApplication.setUser(userDao.getOneUserById(backApplication.getUserId()));
            backApplication.setLeaveApplication(leaveApproveDao.getLeaveApplicationById(backApplication.getLeaveApplicationId()));
        }
        return backApplications;
    }

    @Override
    public Boolean acceptBack(Long id) {
        BackApplication backApplication = backApproveDao.getBackApplicationById(id);
        backApplication.setBackApplicationIsApprove(1);
        backApplication.setBackApplicationApproveTime(new Timestamp(new Date().getTime()));
        backApplication.setBackApplicationApproveResult("同意");
        int result1 = backApproveDao.updateBackApplication(backApplication);

        ApproveLog<?> approveLog = new ApproveLog<>();
        approveLog.setApproveLogKind(2);
        approveLog.setApproveLogContentId(id);
        int result2 = approveLogDao.addOneApproveLog(approveLog);
        return (result1 == 1 && result2 == 1);
    }

    @Override
    public List<ApproveLog<?>> getAllApproveLog() {
        List<ApproveLog<?>> approveLogs = approveLogDao.getAllApproveLog();
        for (ApproveLog approveLog : approveLogs) {
            if (approveLog.getApproveLogKind() == 1) {
                LeaveApplication leaveApplication = leaveApproveDao.getLeaveApplicationById(approveLog.getApproveLogContentId());
                leaveApplication.setUser(userDao.getOneUserById(leaveApplication.getUserId()));
                approveLog.setContent(leaveApplication);
            } else if (approveLog.getApproveLogKind() == 2) {
                BackApplication backApplication = backApproveDao.getBackApplicationById(approveLog.getApproveLogContentId());
                backApplication.setUser(userDao.getOneUserById(backApplication.getUserId()));
                approveLog.setContent(backApplication);
            }
        }
        return approveLogs;
    }

    @Override
    public List<ApproveLog<?>> queryApproveLog(QueryApproveLog queryApproveLog) {
        List<ApproveLog<?>> approveLogs = approveLogDao.queryApproveLog(queryApproveLog);
        for (ApproveLog approveLog : approveLogs) {
            if (approveLog.getApproveLogKind() == 1) {
                LeaveApplication leaveApplication = leaveApproveDao.getLeaveApplicationById(approveLog.getApproveLogContentId());
                leaveApplication.setUser(userDao.getOneUserById(leaveApplication.getUserId()));
                approveLog.setContent(leaveApplication);
            } else if (approveLog.getApproveLogKind() == 2) {
                BackApplication backApplication = backApproveDao.getBackApplicationById(approveLog.getApproveLogContentId());
                backApplication.setUser(userDao.getOneUserById(backApplication.getUserId()));
                approveLog.setContent(backApplication);
            }
        }
        return approveLogs;
    }
}
