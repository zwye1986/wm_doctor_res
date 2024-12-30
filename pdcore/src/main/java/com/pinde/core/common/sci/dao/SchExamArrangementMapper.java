package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SchExamArrangement;
import com.pinde.core.model.SchExamArrangementExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchExamArrangementMapper {
    int countByExample(SchExamArrangementExample example);

    int deleteByExample(SchExamArrangementExample example);

    int deleteByPrimaryKey(String arrangeFlow);

    int insert(SchExamArrangement record);

    int insertSelective(SchExamArrangement record);

    List<SchExamArrangement> selectByExample(SchExamArrangementExample example);

    SchExamArrangement selectByPrimaryKey(String arrangeFlow);

    int updateByExampleSelective(@Param("record") SchExamArrangement record, @Param("example") SchExamArrangementExample example);

    int updateByExample(@Param("record") SchExamArrangement record, @Param("example") SchExamArrangementExample example);

    int updateByPrimaryKeySelective(SchExamArrangement record);

    int updateByPrimaryKey(SchExamArrangement record);
}