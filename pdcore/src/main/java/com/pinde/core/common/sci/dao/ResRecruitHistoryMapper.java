package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResRecruitHistory;
import com.pinde.core.model.ResRecruitHistoryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRecruitHistoryMapper {
    int countByExample(ResRecruitHistoryExample example);

    int deleteByExample(ResRecruitHistoryExample example);

    int deleteByPrimaryKey(String historyFlow);

    int insert(ResRecruitHistory record);

    int insertSelective(ResRecruitHistory record);

    List<ResRecruitHistory> selectByExample(ResRecruitHistoryExample example);

    ResRecruitHistory selectByPrimaryKey(String historyFlow);

    int updateByExampleSelective(@Param("record") ResRecruitHistory record, @Param("example") ResRecruitHistoryExample example);

    int updateByExample(@Param("record") ResRecruitHistory record, @Param("example") ResRecruitHistoryExample example);

    int updateByPrimaryKeySelective(ResRecruitHistory record);

    int updateByPrimaryKey(ResRecruitHistory record);
}