package com.project.service;

import com.project.entity.RoleAuthority;

import java.util.List;

/**
 * Created by qiaowentao on 2016/11/10.
 */
public interface RoleAuthorityService {

    /**
     * 根据角色查询对应的权限
     * @param roleAuthority
     * @return
     */
    List<RoleAuthority> selectRoleAuthorityWithRole(RoleAuthority roleAuthority);

}
