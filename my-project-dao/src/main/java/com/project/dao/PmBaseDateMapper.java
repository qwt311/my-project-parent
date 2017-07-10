package com.project.dao;

import com.project.entity.PmBaseDate;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmBaseDateMapper {

    /**
     * 根据条件查询 pmBaseData 集合
     * @param pmBaseDate
     * @return
     */
    List<PmBaseDate> selectByConditions(PmBaseDate pmBaseDate);

    /**
     * 插入一条新的数据
     * @param pmBaseDate
     * @return
     */
    int insertPmBaseData(PmBaseDate pmBaseDate);

    /**
     * 批量插入 pmBaseData 数据
     * @param list
     * @return
     */
    int insertByList(List<PmBaseDate> list);

    /**
     * 批量更新数据
     * @param pmBaseDate
     * @return
     */
    int updatePmBaseDataList(PmBaseDate pmBaseDate);

    /**
     * 根据主键更新一条数据
     * @param id
     * @return
     */
    int updateById(Long id);

}