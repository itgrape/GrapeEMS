package com.pushihao.dao;

import com.pushihao.entity.Announce;
import com.pushihao.pojo.QueryAnnounce;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AnnounceDao {

    public Integer addOneAnnounce(Announce announce);

    public Announce getAnnounceById(@Param("id") Long id);

    public List<Announce> getAllAnnounce();

    public List<Announce> getAnnounceByDeptId(@Param("deptId") Long deptId);

    public Integer editOneAnnounce(Announce announce);

    public Integer deleteOneAnnounce(Announce announce);

    public List<Announce> queryAnnounce(QueryAnnounce queryAnnounce);
}
