package com.project.service;

import com.project.entity.User;

import java.util.List;

/**
 * Created by qiaowentao on 2016/8/18.
 */
public interface UserService {

    /**
     * 新用户注册
     * @param user
     * @return
     */
    int registerUser(User user,String utr);

    /**
     * 用户登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 更新用户的信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户，实际上是将表中一个字段修改为禁用状态
     * @param user
     */
    void deleteUser(User user);

    /**
     * 根据条件获取符合的所有用户
     * @param user
     * @return
     */
    List<User> selectUserList(User user);

    /**
     * 根据条件查询符合的用户信息
     * @param user
     * @return
     */
    User checkUserWithConditions(User user);

}
