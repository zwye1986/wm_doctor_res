package com.pinde.sci.dao.base;

import com.pinde.core.model.ResTeacherTraining;
import com.pinde.core.model.ResTeacherTrainingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResTeacherTrainingMapper {
    int countByExample(ResTeacherTrainingExample example);

    int deleteByExample(ResTeacherTrainingExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResTeacherTraining record);

    int insertSelective(ResTeacherTraining record);

    List<ResTeacherTraining> selectByExample(ResTeacherTrainingExample example);

    List<ResTeacherTraining> selectByCondition(@Param("record") ResTeacherTraining record);

    ResTeacherTraining selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResTeacherTraining record, @Param("example") ResTeacherTrainingExample example);

    int updateByExample(@Param("record") ResTeacherTraining record, @Param("example") ResTeacherTrainingExample example);

    int updateByPrimaryKeySelective(ResTeacherTraining record);

    int updateByPrimaryKey(ResTeacherTraining record);

    ResTeacherTraining selectDetailByKey(@Param("recordFlow") String recordFlow);

    List<ResTeacherTraining> selectByConditionAddUserDept(@Param("record") ResTeacherTraining record);
}