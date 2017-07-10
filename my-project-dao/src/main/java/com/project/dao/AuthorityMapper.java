package com.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.entity.Authority;

/**
 * 资源接口
 */
public interface AuthorityMapper {

    /**
     * 批量插入资源列表
     * @param list
     * @return
     */
    int insertInroAuthority(List<Authority> list);

    /**
     * 根据条件删除资源
     * @param authority
     * @return
     */
    int deleteWithCondition(Authority authority);

    /**
     * 查询资源列表信息
     * @return
     */
    List<Authority> selectAuthorityList();

    /**
     * 根据条件查询资源信息
     * @param authority
     * @return
     */
    List<Authority> selectAuthorityWithCondition(Authority authority);

    /**
     * 根据条件更新资源
     * @param authority
     * @return
     */
    int updateAuthority(Authority authority);

}