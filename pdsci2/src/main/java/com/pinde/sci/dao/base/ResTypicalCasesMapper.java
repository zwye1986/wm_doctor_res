package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResTypicalCases;
import com.pinde.sci.model.mo.ResTypicalCasesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResTypicalCasesMapper {
    int countByExample(ResTypicalCasesExample example);

    int deleteByExample(ResTypicalCasesExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResTypicalCases record);

    int insertSelective(ResTypicalCases record);

    List<ResTypicalCases> selectByExampleWithBLOBs(ResTypicalCasesExample example);

    List<ResTypicalCases> selectByExample(ResTypicalCasesExample example);

    ResTypicalCases selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResTypicalCases record, @Param("example") ResTypicalCasesExample example);

    int updateByExampleWithBLOBs(@Param("record") ResTypicalCases record, @Param("example") ResTypicalCasesExample example);

    int updateByExample(@Param("record") ResTypicalCases record, @Param("example") ResTypicalCasesExample example);

    int updateByPrimaryKeySelective(ResTypicalCases record);

    int updateByPrimaryKeyWithBLOBs(ResTypicalCases record);

    int updateByPrimaryKey(ResTypicalCases record);
}