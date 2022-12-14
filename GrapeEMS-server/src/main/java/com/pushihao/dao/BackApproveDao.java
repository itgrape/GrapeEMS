package com.pushihao.dao;

import com.pushihao.entity.BackApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BackApproveDao {
    public List<BackApplication> getAllBackApplication();

    public List<BackApplication> getBackApplicationByUserId(@Param("userId") Long userId);

    public BackApplication getBackApplicationById(@Param("id") Long id);

    public Integer updateBackApplication(BackApplication backApplication);

    public Integer addOneBackApprove(BackApplication backApplication);
}
