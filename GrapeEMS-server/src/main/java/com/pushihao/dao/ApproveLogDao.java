package com.pushihao.dao;

import com.pushihao.bean.ApproveLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ApproveLogDao {
    public Integer addOneApproveLog(ApproveLog<?> approveLog);
}
