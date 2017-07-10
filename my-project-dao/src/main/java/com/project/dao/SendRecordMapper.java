package com.project.dao;

import com.project.entity.SendRecord;
import java.util.List;

public interface SendRecordMapper {

    /**
     * 根据条件查询sendRecord 集合
     * @param sendRecord
     * @return
     */
    List<SendRecord> selectByConditions(SendRecord sendRecord);

    /**
     * 批量插入数据
     * @param sendRecords
     * @return
     */
    int insertByList(List<SendRecord> sendRecords);

    /**
     * 根据条件更新数据
     * @param sendRecord
     * @return
     */
    int updateSendRecord(SendRecord sendRecord);

    /**
     * 根据主键更新数据
     * @param id
     * @return
     */
    int updateById(Long id);
}