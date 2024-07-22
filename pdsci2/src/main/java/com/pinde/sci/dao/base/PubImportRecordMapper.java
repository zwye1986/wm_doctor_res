package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubImportRecord;
import com.pinde.sci.model.mo.PubImportRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubImportRecordMapper {
    int countByExample(PubImportRecordExample example);

    int deleteByExample(PubImportRecordExample example);

    int deleteByPrimaryKey(String impFlow);

    int insert(PubImportRecord record);

    int insertSelective(PubImportRecord record);

    List<PubImportRecord> selectByExample(PubImportRecordExample example);

    PubImportRecord selectByPrimaryKey(String impFlow);

    int updateByExampleSelective(@Param("record") PubImportRecord record, @Param("example") PubImportRecordExample example);

    int updateByExample(@Param("record") PubImportRecord record, @Param("example") PubImportRecordExample example);

    int updateByPrimaryKeySelective(PubImportRecord record);

    int updateByPrimaryKey(PubImportRecord record);
}