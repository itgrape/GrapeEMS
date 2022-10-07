package com.pushihao.service.impl;

import com.pushihao.dao.RoleDao;
import com.pushihao.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<String> getAllRoleName() {
        return roleDao.getAllRoleName();
    }
}
