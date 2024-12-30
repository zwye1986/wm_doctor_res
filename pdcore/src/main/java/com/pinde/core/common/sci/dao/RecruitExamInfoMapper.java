package com.pinde.core.common.sci.dao;

import com.pinde.core.model.RecruitExamInfo;
import com.pinde.core.model.RecruitExamInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitExamInfoMapper {
    int countByExample(RecruitExamInfoExample example);

    int deleteByExample(RecruitExamInfoExample example);

    int deleteByPrimaryKey(String examFlow);

    int insert(RecruitExamInfo record);

    int insertSelective(RecruitExamInfo record);

    List<RecruitExamInfo> selectByExample(RecruitExamInfoExample example);

    RecruitExamInfo selectByPrimaryKey(String examFlow);

    int updateByExampleSelective(@Param("record") RecruitExamInfo record, @Param("example") RecruitExamInfoExample example);

    int updateByExample(@Param("record") RecruitExamInfo record, @Param("example") RecruitExamInfoExample example);

    int updateByPrimaryKeySelective(RecruitExamInfo record);

    int updateByPrimaryKey(RecruitExamInfo record);
}