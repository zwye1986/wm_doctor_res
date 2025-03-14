package com.pinde.core.common.sci.dao;
import java.util.List;

import com.pinde.core.model.ResTeacherTrainingInfo;
import com.pinde.core.model.ResTeacherTrainingInfoExample;
import org.apache.ibatis.annotations.Param;

public interface ResTeacherTrainingInfoMapper {
    int countByExample(ResTeacherTrainingInfoExample example);

    int deleteByExample(ResTeacherTrainingInfoExample example);

    int deleteByPrimaryKey(String trainingFlow);

    int insert(ResTeacherTrainingInfo record);

    int insertSelective(ResTeacherTrainingInfo record);

    List<ResTeacherTrainingInfo> selectByExample(ResTeacherTrainingInfoExample example);

    ResTeacherTrainingInfo selectByPrimaryKey(String trainingFlow);

    int updateByExampleSelective(@Param("record") ResTeacherTrainingInfo record, @Param("example") ResTeacherTrainingInfoExample example);

    int updateByExample(@Param("record") ResTeacherTrainingInfo record, @Param("example") ResTeacherTrainingInfoExample example);

    int updateByPrimaryKeySelective(ResTeacherTrainingInfo record);

    int updateByPrimaryKey(ResTeacherTrainingInfo record);
}