package com.project.service;

import com.project.common.util.NewTrain;

import java.util.List;

/**
 * Created by qiaowentao on 2017/2/24.
 */
public interface TicketService {

    /**
     * 根据信息查询12306余票信息
     *
     *  若可以查询出数据，则返回列车集合信息
     *  若查不出数据，即：选择的查询日期不在预售日期范围内，则返回为空
     * @param startCity
     * @param endCity
     * @param trainDate
     * @param isAdult
     * @return
     */
    List<NewTrain> getTicketFromKyfw(String startCity, String endCity, String trainDate , int isAdult);

}
