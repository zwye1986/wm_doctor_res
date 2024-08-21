package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResTrainingSpeDept;
import com.pinde.sci.model.mo.ResTrainingSpeDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResTrainingSpeDeptMapper {
    int countByExample(ResTrainingSpeDeptExample example);

    int deleteByExample(ResTrainingSpeDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResTrainingSpeDept record);

    int insertSelective(ResTrainingSpeDept record);

    List<ResTrainingSpeDept> selectByExample(ResTrainingSpeDeptExample example);

    ResTrainingSpeDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResTrainingSpeDept record, @Param("example") ResTrainingSpeDeptExample example);

    int updateByExample(@Param("record") ResTrainingSpeDept record, @Param("example") ResTrainingSpeDeptExample example);

    int updateByPrimaryKeySelective(ResTrainingSpeDept record);

    int updateByPrimaryKey(ResTrainingSpeDept record);
}