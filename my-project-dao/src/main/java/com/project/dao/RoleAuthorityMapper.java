package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.entity.RoleAuthority;

/**
 * 角色资源接口
 */
public interface RoleAuthorityMapper {

    /**
     * 批量插入角色资源信息，即给角色分配资源
     * @param list
     * @return
     */
    int insertIntoList(List<RoleAuthority> list);

    /**
     * 根据条件删除角色资源对应的信息
     * @param roleAuthority
     * @return
     */
    int deleteRoleAuthorityWithRole(RoleAuthority roleAuthority);

    /**
     * 根据角色条件查询资源列表信息
     * @param roleAuthority
     * @return
     */
    List<RoleAuthority> selectWithConditions(RoleAuthority roleAuthority);

    /**
     * 查看角色资源列表
     * @return
     */
    List<RoleAuthority> selectList();

    /**
     *批量更新角色资源信息
     * @param list
     * @return
     */
    int batchUpdateRoleAuthority(List<RoleAuthority> list);

    /**
     *根据条件更新角色的资源信息
     * @param roleAuthority
     * @return
     */
    int updateRoleAuthority(RoleAuthority roleAuthority);
}