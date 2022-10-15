package com.pushihao.dao;

import com.pushihao.bean.ApproveLog;
import com.pushihao.pojo.QueryApproveLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApproveLogDao {
    public Integer addOneApproveLog(ApproveLog<?> approveLog);

    public List<ApproveLog<?>> getAllApproveLog();

    public List<ApproveLog<?>> queryApproveLog(QueryApproveLog queryApproveLog);
}
