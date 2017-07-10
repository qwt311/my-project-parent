package com.project.service.impl;

import com.project.dao.OnlyOneUserMapper;
import com.project.entity.OnlyOneUser;
import com.project.service.OnlyOneUserService;
import com.project.service.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qiaowentao on 2016/11/1.
 */
@Service
public class OnlyOneUserServiceImpl implements OnlyOneUserService {

    @Autowired
    private OnlyOneUserMapper oneUserMapper;


    @Override
    public void addOnlyOneUser(OnlyOneUser oneUser) {
        DBContextHolder.setDataSource("dataSourceOne");
        oneUserMapper.addOnlyOneUserMessage(oneUser);
    }

    @Override
    public int updateOnlyOneUserMessage(OnlyOneUser oneUser) {
        DBContextHolder.setDataSource("dataSourceOne");
        return oneUserMapper.updateOnlyOneUserMessage(oneUser);
    }

    @Override
    public OnlyOneUser selectOnlyWithCondition(OnlyOneUser oneUser) {
        DBContextHolder.setDataSource("dataSourceOne");
        return oneUserMapper.selectOnlyOneUser(oneUser);
    }
}
