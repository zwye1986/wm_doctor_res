package com.pinde.core.common.sci.dao;

import com.pinde.core.model.RecruitInfo;
import com.pinde.core.model.RecruitInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitInfoMapper {
    int countByExample(RecruitInfoExample example);

    int deleteByExample(RecruitInfoExample example);

    int deleteByPrimaryKey(String recruitFlow);

    int insert(RecruitInfo record);

    int insertSelective(RecruitInfo record);

    List<RecruitInfo> selectByExample(RecruitInfoExample example);

    RecruitInfo selectByPrimaryKey(String recruitFlow);

    int updateByExampleSelective(@Param("record") RecruitInfo record, @Param("example") RecruitInfoExample example);

    int updateByExample(@Param("record") RecruitInfo record, @Param("example") RecruitInfoExample example);

    int updateByPrimaryKeySelective(RecruitInfo record);

    int updateByPrimaryKey(RecruitInfo record);
}