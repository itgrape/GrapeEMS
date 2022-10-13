package com.pushihao.dao;

import com.pushihao.bean.Announce;
import com.pushihao.pojo.QueryAnnounce;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnounceDao {

    public Integer addOneAnnounce(Announce announce);

    public List<Announce> getAllAnnounce();

    public Integer editOneAnnounce(Announce announce);

    public Integer deleteOneAnnounce(Announce announce);

    public List<Announce> queryAnnounce(QueryAnnounce queryAnnounce);
}
