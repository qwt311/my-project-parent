package com.project.service.impl;

import com.project.dao.PmBaseDateMapper;
import com.project.entity.PmBaseDate;
import com.project.service.BaseDataService;
import com.project.service.util.DBContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by qiaowentao on 2016/11/3.
 */
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
@Service
public class BaseDataServiceImpl implements BaseDataService {

    @Autowired
    private PmBaseDateMapper baseDateMapper;


    @Override
    public List<PmBaseDate> selectBaseDataList(PmBaseDate baseDate) {
        DBContextHolder.setDataSource("dataSourceOne");
        return baseDateMapper.selectByConditions(baseDate);
    }
}
