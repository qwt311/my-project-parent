package com.project.service;

import com.project.entity.PmBaseDate;

import java.util.List;

/**
 * Created by qiaowentao on 2016/11/3.
 */
public interface BaseDataService {

    List<PmBaseDate> selectBaseDataList(PmBaseDate baseDate);

}
