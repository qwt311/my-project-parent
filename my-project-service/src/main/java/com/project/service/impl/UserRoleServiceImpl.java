package com.project.service.impl;

import com.project.dao.UserRoleMapper;
import com.project.entity.UserRole;
import com.project.service.UserRoleService;
import com.project.service.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qiaowentao on 2016/11/10.
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> selectUserRoleWithPhone(UserRole userRole) {
        DBContextHolder.setDataSource("dataSourceOne");
        return userRoleMapper.selectWithConditions(userRole);
    }
}
