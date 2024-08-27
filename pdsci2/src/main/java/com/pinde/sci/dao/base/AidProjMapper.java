package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.AidProj;
import com.pinde.sci.model.mo.AidProjExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AidProjMapper {
    int countByExample(AidProjExample example);

    int deleteByExample(AidProjExample example);

    int deleteByPrimaryKey(String projFlow);

    int insert(AidProj record);

    int insertSelective(AidProj record);

    List<AidProj> selectByExampleWithBLOBs(AidProjExample example);

    List<AidProj> selectByExample(AidProjExample example);

    AidProj selectByPrimaryKey(String projFlow);

    int updateByExampleSelective(@Param("record") AidProj record, @Param("example") AidProjExample example);

    int updateByExampleWithBLOBs(@Param("record") AidProj record, @Param("example") AidProjExample example);

    int updateByExample(@Param("record") AidProj record, @Param("example") AidProjExample example);

    int updateByPrimaryKeySelective(AidProj record);

    int updateByPrimaryKeyWithBLOBs(AidProj record);

    int updateByPrimaryKey(AidProj record);
}