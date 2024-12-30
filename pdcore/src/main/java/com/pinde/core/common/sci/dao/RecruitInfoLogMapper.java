package com.pinde.core.common.sci.dao;

import com.pinde.core.model.RecruitInfoLog;
import com.pinde.core.model.RecruitInfoLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitInfoLogMapper {
    int countByExample(RecruitInfoLogExample example);

    int deleteByExample(RecruitInfoLogExample example);

    int deleteByPrimaryKey(String logFlow);

    int insert(RecruitInfoLog record);

    int insertSelective(RecruitInfoLog record);

    List<RecruitInfoLog> selectByExample(RecruitInfoLogExample example);

    RecruitInfoLog selectByPrimaryKey(String logFlow);

    int updateByExampleSelective(@Param("record") RecruitInfoLog record, @Param("example") RecruitInfoLogExample example);

    int updateByExample(@Param("record") RecruitInfoLog record, @Param("example") RecruitInfoLogExample example);

    int updateByPrimaryKeySelective(RecruitInfoLog record);

    int updateByPrimaryKey(RecruitInfoLog record);
}