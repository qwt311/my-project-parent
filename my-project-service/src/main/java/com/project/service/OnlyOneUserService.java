package com.project.service;

import com.project.dao.OnlyOneUserMapper;
import com.project.entity.OnlyOneUser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qiaowentao on 2016/11/1.
 */
public interface OnlyOneUserService {

    /**
     * 用户注册时新增一条信息
     * @param oneUser
     * @return
     */
    void addOnlyOneUser(OnlyOneUser oneUser);

    /**
     * 用户每次登录时刷新 utr 字符串
     * @param oneUser
     */
    int updateOnlyOneUserMessage(OnlyOneUser oneUser);

    /**
     * 根据条件查询信息
     * @param oneUser
     * @return
     */
    OnlyOneUser selectOnlyWithCondition(OnlyOneUser oneUser);

}
