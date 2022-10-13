package com.pushihao.service;

import com.pushihao.bean.Announce;
import com.pushihao.pojo.AddAnnounce;
import com.pushihao.pojo.QueryAnnounce;

import java.util.List;

public interface AnnounceService {

    public Boolean addOneAnnounce(AddAnnounce announce);

    public List<Announce> getAllAnnounce();

    public Boolean editOneAnnounce(Announce announce);

    public Boolean deleteOneAnnounce(Long announceId);

    public List<Announce> queryAnnounce(QueryAnnounce queryAnnounce);
}
