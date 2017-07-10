package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.entity.UserRole;

/**
 * 用户角色接口
 */
public interface UserRoleMapper {

    /**
     * 批量插入用户角色表数据,即给用户分配角色
     * @param list
     * @return
     */
    int insertIntoList(List<UserRole> list);

    /**
     * 根据条件删除用户的角色
     * @param userRole
     * @return
     */
    int deleteWithUser(UserRole userRole);

    /**
     * 根据条件查询用户具有的角色
     * @param userRole
     * @return
     */
    List<UserRole> selectWithConditions(UserRole userRole);

    /**
     * 根据条件更新用户的角色
     * @param userRole
     * @return
     */
    int updateUserRole(UserRole userRole);

}