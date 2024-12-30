package com.pinde.core.common.sci.dao;

import com.pinde.core.model.RecruitInterviewInfo;
import com.pinde.core.model.RecruitInterviewInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitInterviewInfoMapper {
    int countByExample(RecruitInterviewInfoExample example);

    int deleteByExample(RecruitInterviewInfoExample example);

    int deleteByPrimaryKey(String recruitFlow);

    int insert(RecruitInterviewInfo record);

    int insertSelective(RecruitInterviewInfo record);

    List<RecruitInterviewInfo> selectByExample(RecruitInterviewInfoExample example);

    RecruitInterviewInfo selectByPrimaryKey(String recruitFlow);

    int updateByExampleSelective(@Param("record") RecruitInterviewInfo record, @Param("example") RecruitInterviewInfoExample example);

    int updateByExample(@Param("record") RecruitInterviewInfo record, @Param("example") RecruitInterviewInfoExample example);

    int updateByPrimaryKeySelective(RecruitInterviewInfo record);

    int updateByPrimaryKey(RecruitInterviewInfo record);
}