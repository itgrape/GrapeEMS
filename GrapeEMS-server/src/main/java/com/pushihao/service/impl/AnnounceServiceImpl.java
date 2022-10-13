package com.pushihao.service.impl;

import com.pushihao.bean.Announce;
import com.pushihao.dao.AnnounceDao;
import com.pushihao.dao.DeptDao;
import com.pushihao.pojo.AddAnnounce;
import com.pushihao.pojo.QueryAnnounce;
import com.pushihao.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class AnnounceServiceImpl implements AnnounceService {
    @Autowired
    private AnnounceDao announceDao;

    @Autowired
    private DeptDao deptDao;

    @Override
    public Boolean addOneAnnounce(AddAnnounce announce) {
        Announce a = new Announce();
        a.setAnnounceTitle(announce.getTitle());
        a.setAnnounceContent(announce.getContent());
        a.setAnnounceCreateTime(new Timestamp(new Date().getTime()));
        a.setDeptId(deptDao.getDeptIdByName(announce.getDeptName()));
        a.setIsDeleted(1);
        Integer result = announceDao.addOneAnnounce(a);
        return result == 1;
    }

    @Override
    public List<Announce> getAllAnnounce() {
        return announceDao.getAllAnnounce();
    }

    @Override
    public Boolean editOneAnnounce(Announce announce) {
        Integer result = announceDao.editOneAnnounce(announce);
        return result == 1;
    }

    @Override
    public Boolean deleteOneAnnounce(Long announceId) {
        Announce announce = new Announce();
        announce.setAnnounceId(announceId);
        announce.setIsDeleted(0);
        Integer result = announceDao.deleteOneAnnounce(announce);
        return result == 1;
    }

    @Override
    public List<Announce> queryAnnounce(QueryAnnounce queryAnnounce) {
        return announceDao.queryAnnounce(queryAnnounce);
    }
}
