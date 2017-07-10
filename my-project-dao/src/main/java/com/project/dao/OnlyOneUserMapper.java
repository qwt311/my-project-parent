package com.project.dao;

import com.project.entity.OnlyOneUser;

/**
 * Created by qiaowentao on 2016/10/31.
 */
public interface OnlyOneUserMapper {

    /**
     * 用户注册时插入一条用户登录信息
     * @param oneUser
     * @return
     */
    int addOnlyOneUserMessage(OnlyOneUser oneUser);

    /**
     * 每次用户登录时更新随机字符串
     * @param oneUser
     */
    int updateOnlyOneUserMessage(OnlyOneUser oneUser);

    /**
     * 根据用户手机号查询用户登录状态
     * @param oneUser
     * @return
     */
    OnlyOneUser selectOnlyOneUser(OnlyOneUser oneUser);

}
