package com.pinde.sci.dao.base;

import com.pinde.core.model.ResBaseSpeDept;
import com.pinde.core.model.ResBaseSpeDeptExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResBaseSpeDeptMapper {
    int countByExample(ResBaseSpeDeptExample example);

    int deleteByExample(ResBaseSpeDeptExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResBaseSpeDept record);

    int insertSelective(ResBaseSpeDept record);

    List<ResBaseSpeDept> selectByExampleWithBLOBs(ResBaseSpeDeptExample example);

    List<ResBaseSpeDept> selectByExample(ResBaseSpeDeptExample example);

    ResBaseSpeDept selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResBaseSpeDept record, @Param("example") ResBaseSpeDeptExample example);

    int updateByExampleWithBLOBs(@Param("record") ResBaseSpeDept record, @Param("example") ResBaseSpeDeptExample example);

    int updateByExample(@Param("record") ResBaseSpeDept record, @Param("example") ResBaseSpeDeptExample example);

    int updateByPrimaryKeySelective(ResBaseSpeDept record);

    int updateByPrimaryKeyWithBLOBs(ResBaseSpeDept record);

    int updateByPrimaryKey(ResBaseSpeDept record);
}