package com.project.service.impl;

import com.project.dao.RoleAuthorityMapper;
import com.project.entity.RoleAuthority;
import com.project.service.RoleAuthorityService;
import com.project.service.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by qiaowentao on 2016/11/10.
 */
@Service
public class RoleAuthorityServiceImpl implements RoleAuthorityService {

    @Autowired
    private RoleAuthorityMapper roleAuthorityMapper;

    @Override
    public List<RoleAuthority> selectRoleAuthorityWithRole(RoleAuthority roleAuthority) {
        DBContextHolder.setDataSource("dataSourceOne");
        return roleAuthorityMapper.selectWithConditions(roleAuthority);
    }
}
