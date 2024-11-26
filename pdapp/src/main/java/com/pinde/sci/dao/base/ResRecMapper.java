package com.pinde.sci.dao.base;

import com.pinde.core.model.ResRec;
import com.pinde.core.model.ResRecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRecMapper {
    int countByExample(ResRecExample example);

    int deleteByExample(ResRecExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(ResRec record);

    int insertSelective(ResRec record);

    List<ResRec> selectByExampleWithBLOBs(ResRecExample example);

    List<ResRec> selectByExample(ResRecExample example);

    ResRec selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") ResRec record, @Param("example") ResRecExample example);

    int updateByExampleWithBLOBs(@Param("record") ResRec record, @Param("example") ResRecExample example);

    int updateByExample(@Param("record") ResRec record, @Param("example") ResRecExample example);

    int updateByPrimaryKeySelective(ResRec record);

    int updateByPrimaryKeyWithBLOBs(ResRec record);

    int updateByPrimaryKey(ResRec record);
}