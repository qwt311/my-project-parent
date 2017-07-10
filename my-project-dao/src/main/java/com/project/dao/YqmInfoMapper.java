package com.project.dao;

import com.project.entity.YqmInfo;
import java.util.List;

public interface YqmInfoMapper {

    /**
     * 给员工分配邀请码
     * @param yqmInfo
     * @return
     */
    int insertIntoYqmInfo(YqmInfo yqmInfo);

    List<YqmInfo> selectAllInfo(YqmInfo yqmInfo);

    /**
     * 注册时根据邀请码判断是否可用
     * @param yqmInfo
     * @return
     */
    YqmInfo selectYqmInfoByYqm(YqmInfo yqmInfo);

    /**
     * 新用户注册时更新员工邀请码使用次数
     * @param yqmInfo
     * @return
     */
    int updateYqmCount(YqmInfo yqmInfo);

    /**
     * 员工离职同时更新邀请码为不可用
     * @param yqmInfo
     * @return
     */
    int updateZzzt(YqmInfo yqmInfo);
}