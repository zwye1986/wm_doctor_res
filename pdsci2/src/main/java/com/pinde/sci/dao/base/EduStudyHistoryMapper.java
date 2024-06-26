package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduStudyHistory;
import com.pinde.sci.model.mo.EduStudyHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduStudyHistoryMapper {
    int countByExample(EduStudyHistoryExample example);

    int deleteByExample(EduStudyHistoryExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduStudyHistory record);

    int insertSelective(EduStudyHistory record);

    List<EduStudyHistory> selectByExample(EduStudyHistoryExample example);

    EduStudyHistory selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduStudyHistory record, @Param("example") EduStudyHistoryExample example);

    int updateByExample(@Param("record") EduStudyHistory record, @Param("example") EduStudyHistoryExample example);

    int updateByPrimaryKeySelective(EduStudyHistory record);

    int updateByPrimaryKey(EduStudyHistory record);
}