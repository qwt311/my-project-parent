package com.project.service;

import com.project.entity.YqmInfo;

/**
 * Created by qiaowentao on 2016/8/25.
 */
public interface YqmInfoService {

    /**
     * 根据条件查询邀请码信息
     * @param yqmInfo
     * @return
     */
    YqmInfo selectYqmInfoByCondition(YqmInfo yqmInfo);

}
