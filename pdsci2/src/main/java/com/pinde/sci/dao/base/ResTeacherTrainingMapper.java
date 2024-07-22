package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResTeacherTraining;
import com.pinde.sci.model.mo.ResTeacherTrainingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResTeacherTrainingMapper {
    int countByExample(ResTeacherTrainingExample example);

    int deleteByExample(ResTeacherTrainingExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResTeacherTraining record);

    int insertSelective(ResTeacherTraining record);

    List<ResTeacherTraining> selectByExample(ResTeacherTrainingExample example);

    ResTeacherTraining selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResTeacherTraining record, @Param("example") ResTeacherTrainingExample example);

    int updateByExample(@Param("record") ResTeacherTraining record, @Param("example") ResTeacherTrainingExample example);

    int updateByPrimaryKeySelective(ResTeacherTraining record);

    int updateByPrimaryKey(ResTeacherTraining record);
}