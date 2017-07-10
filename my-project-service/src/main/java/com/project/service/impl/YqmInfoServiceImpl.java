package com.project.service.impl;

import com.project.dao.YqmInfoMapper;
import com.project.entity.YqmInfo;
import com.project.service.YqmInfoService;
import com.project.service.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by qiaowentao on 2016/8/25.
 */
@Service
public class YqmInfoServiceImpl implements YqmInfoService {

    @Autowired
    private YqmInfoMapper yqmInfoMapper;


    @Override
    public YqmInfo selectYqmInfoByCondition(YqmInfo yqmInfo) {
        DBContextHolder.setDataSource("dataSourceOne");
        return yqmInfoMapper.selectYqmInfoByYqm(yqmInfo);
    }
}
