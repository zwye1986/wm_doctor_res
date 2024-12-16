package com.pinde.sci.dao.base;

import com.pinde.core.model.PubProjRec;
import com.pinde.core.model.PubProjRecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubProjRecMapper {
    int countByExample(PubProjRecExample example);

    int deleteByExample(PubProjRecExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(PubProjRec record);

    int insertSelective(PubProjRec record);

    List<PubProjRec> selectByExampleWithBLOBs(PubProjRecExample example);

    List<PubProjRec> selectByExample(PubProjRecExample example);

    PubProjRec selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") PubProjRec record, @Param("example") PubProjRecExample example);

    int updateByExampleWithBLOBs(@Param("record") PubProjRec record, @Param("example") PubProjRecExample example);

    int updateByExample(@Param("record") PubProjRec record, @Param("example") PubProjRecExample example);

    int updateByPrimaryKeySelective(PubProjRec record);

    int updateByPrimaryKeyWithBLOBs(PubProjRec record);

    int updateByPrimaryKey(PubProjRec record);
}