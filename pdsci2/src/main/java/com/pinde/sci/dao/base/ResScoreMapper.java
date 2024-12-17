package com.pinde.sci.dao.base;

import com.pinde.core.model.ResScore;
import com.pinde.core.model.ResScoreExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResScoreMapper {
    int countByExample(ResScoreExample example);

    int deleteByExample(ResScoreExample example);

    int deleteByPrimaryKey(String scoreFlow);

    int insert(ResScore record);

    int insertSelective(ResScore record);

    List<ResScore> selectByExampleWithBLOBs(ResScoreExample example);

    List<ResScore> selectByExample(ResScoreExample example);

    ResScore selectByPrimaryKey(String scoreFlow);

    int updateByExampleSelective(@Param("record") ResScore record, @Param("example") ResScoreExample example);

    int updateByExampleWithBLOBs(@Param("record") ResScore record, @Param("example") ResScoreExample example);

    int updateByExample(@Param("record") ResScore record, @Param("example") ResScoreExample example);

    int updateByPrimaryKeySelective(ResScore record);

    int updateByPrimaryKeyWithBLOBs(ResScore record);

    int updateByPrimaryKey(ResScore record);
}