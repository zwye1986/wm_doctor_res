package com.pinde.sci.dao.base;

import com.pinde.core.model.ResTeacherTraining;
import com.pinde.core.model.ResTeacherTrainingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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