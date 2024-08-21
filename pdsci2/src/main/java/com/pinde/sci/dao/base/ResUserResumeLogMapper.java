package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResUserResumeLog;
import com.pinde.sci.model.mo.ResUserResumeLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResUserResumeLogMapper {
    int countByExample(ResUserResumeLogExample example);

    int deleteByExample(ResUserResumeLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResUserResumeLog record);

    int insertSelective(ResUserResumeLog record);

    List<ResUserResumeLog> selectByExample(ResUserResumeLogExample example);

    ResUserResumeLog selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResUserResumeLog record, @Param("example") ResUserResumeLogExample example);

    int updateByExample(@Param("record") ResUserResumeLog record, @Param("example") ResUserResumeLogExample example);

    int updateByPrimaryKeySelective(ResUserResumeLog record);

    int updateByPrimaryKey(ResUserResumeLog record);
}