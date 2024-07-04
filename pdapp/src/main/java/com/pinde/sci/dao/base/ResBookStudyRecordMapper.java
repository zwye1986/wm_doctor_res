package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResBookStudyRecord;
import com.pinde.sci.model.mo.ResBookStudyRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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