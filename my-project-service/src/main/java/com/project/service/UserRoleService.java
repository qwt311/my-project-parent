package com.project.service;

import com.project.entity.UserRole;

import java.util.List;

/**
 * Created by qiaowentao on 2016/11/10.
 */
public interface UserRoleService {

    /**
     * 根据用户名查询用户具有的角色
     * @param userRole
     * @return
     */
    List<UserRole> selectUserRoleWithPhone(UserRole userRole);

}
