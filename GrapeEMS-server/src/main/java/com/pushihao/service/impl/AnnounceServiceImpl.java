package com.pushihao.service.impl;

import com.pushihao.bean.Announce;
import com.pushihao.bean.Dept;
import com.pushihao.dao.AnnounceDao;
import com.pushihao.dao.DeptDao;
import com.pushihao.dao.UserDao;
import com.pushihao.pojo.AddAnnounce;
import com.pushihao.pojo.QueryAnnounce;
import com.pushihao.pojo.UserCenterUsers;
import com.pushihao.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AnnounceServiceImpl implements AnnounceService {
    @Autowired
    private UserDao userDao;

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
        if (announce.getDeptName().equals("全体部门")) {
            a.setDeptId(1L);
        } else {
            a.setDeptId(deptDao.getDeptIdByName(announce.getDeptName()));
        }
        a.setIsDeleted(1);
        Integer result = announceDao.addOneAnnounce(a);
        return result == 1;
    }

    @Override
    public List<Announce> getAllAnnounce() {
        List<Announce> announces = announceDao.getAllAnnounce();
        return setAnnounceDeptName(announces);
    }

    @Override
    public Announce getAnnounceById(Long id) {
        return announceDao.getAnnounceById(id);
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
        String deptName = queryAnnounce.getDeptName();
        if (deptName != null) {
            Long deptId;
            if (deptName.equals("全体部门")) {
                deptId = 1L;
            } else {
                deptId = deptDao.getDeptIdByName(deptName);
            }
            queryAnnounce.setDeptId(deptId);
        }

        if (queryAnnounce.getStartTime() != null && !Objects.equals(queryAnnounce.getStartTime(), "")) {
            queryAnnounce.setStartTime(new Timestamp(Long.parseLong(queryAnnounce.getStartTime())).toString());
        }
        if (queryAnnounce.getEndTime() != null && !Objects.equals(queryAnnounce.getEndTime(), "")) {
            queryAnnounce.setEndTime(new Timestamp(Long.parseLong(queryAnnounce.getEndTime())).toString());
        }

        List<Announce> announces = announceDao.queryAnnounce(queryAnnounce);
        return setAnnounceDeptName(announces);
    }

    private List<Announce> setAnnounceDeptName(List<Announce> announces) {
        for (Announce announce : announces) {
            long deptId = announce.getDeptId();
            Dept dept = new Dept();
            if (deptId == 1L) {
                dept.setDeptName("全体部门");
            } else {
                dept.setDeptName(deptDao.getDeptNameById(announce.getDeptId()));
            }
            announce.setDept(dept);
        }
        return announces;
    }

    @Override
    public List<Announce> getAllAnnounceByUserId(Long id) {
        UserCenterUsers user = userDao.getOneUserById(id);
        Long deptId = deptDao.getDeptIdByName(user.getDeptName());
        return announceDao.getAnnounceByDeptId(deptId);
    }
}
