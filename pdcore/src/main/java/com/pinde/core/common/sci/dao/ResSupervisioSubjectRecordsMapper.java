package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResSupervisioSubjectRecords;
import com.pinde.core.model.ResSupervisioSubjectRecordsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSupervisioSubjectRecordsMapper {
    int countByExample(ResSupervisioSubjectRecordsExample example);

    int deleteByExample(ResSupervisioSubjectRecordsExample example);

    int insert(ResSupervisioSubjectRecords record);

    int insertSelective(ResSupervisioSubjectRecords record);

    List<ResSupervisioSubjectRecords> selectByExample(ResSupervisioSubjectRecordsExample example);

    int updateByExampleSelective(@Param("record") ResSupervisioSubjectRecords record, @Param("example") ResSupervisioSubjectRecordsExample example);

    int updateByExample(@Param("record") ResSupervisioSubjectRecords record, @Param("example") ResSupervisioSubjectRecordsExample example);
}