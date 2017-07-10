package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.entity.Role;

/**
 * 角色接口
 */
public interface RoleMapper {

    /**
     * 批量添加角色
     * @param list
     * @return
     */
    int insertIntoList(List<Role> list);

    /**
     * 根据条件删除角色数据
     * @param role
     * @return
     */
    int deleteRole(Role role);

    /**
     * 根据条件查询角色
     * @param role
     * @return
     */
    List<Role> selectRoleWithCondition(Role role);

    /**
     * 查询角色列表
     * @return
     */
    List<Role> selectRole();

    /**
     * 根据条件更新角色信息
     * @param role
     * @return
     */
    int updateRole(Role role);

}