package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResBookStudyRecord;
import com.pinde.core.model.ResBookStudyRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBookStudyRecordMapper {
    int countByExample(ResBookStudyRecordExample example);

    int deleteByExample(ResBookStudyRecordExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBookStudyRecord record);

    int insertSelective(ResBookStudyRecord record);

    List<ResBookStudyRecord> selectByExampleWithBLOBs(ResBookStudyRecordExample example);

    List<ResBookStudyRecord> selectByExample(ResBookStudyRecordExample example);

    ResBookStudyRecord selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBookStudyRecord record, @Param("example") ResBookStudyRecordExample example);

    int updateByExampleWithBLOBs(@Param("record") ResBookStudyRecord record, @Param("example") ResBookStudyRecordExample example);

    int updateByExample(@Param("record") ResBookStudyRecord record, @Param("example") ResBookStudyRecordExample example);

    int updateByPrimaryKeySelective(ResBookStudyRecord record);

    int updateByPrimaryKeyWithBLOBs(ResBookStudyRecord record);

    int updateByPrimaryKey(ResBookStudyRecord record);
}