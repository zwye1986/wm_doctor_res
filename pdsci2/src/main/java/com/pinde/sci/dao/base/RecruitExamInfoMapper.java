package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitExamInfo;
import com.pinde.sci.model.mo.RecruitExamInfoExample;
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