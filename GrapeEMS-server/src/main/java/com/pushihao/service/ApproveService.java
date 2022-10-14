package com.pushihao.service;

import com.pushihao.bean.BackApplication;
import com.pushihao.bean.LeaveApplication;

import java.util.List;

public interface ApproveService {

    public List<LeaveApplication> getAllLeaveApplication();

    public Boolean acceptLeave(Long id);

    public Boolean rejectLeave(Long id);

    public List<BackApplication> getAllBackApplication();

    public Boolean acceptBack(Long id);
}
