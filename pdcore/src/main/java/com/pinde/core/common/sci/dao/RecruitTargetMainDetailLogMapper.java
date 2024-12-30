package com.pinde.core.common.sci.dao;

import com.pinde.core.model.RecruitTargetMainDetailLog;
import com.pinde.core.model.RecruitTargetMainDetailLogExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitTargetMainDetailLogMapper {
    int countByExample(RecruitTargetMainDetailLogExample example);

    int deleteByExample(RecruitTargetMainDetailLogExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(RecruitTargetMainDetailLog record);

    int insertSelective(RecruitTargetMainDetailLog record);

    List<RecruitTargetMainDetailLog> selectByExample(RecruitTargetMainDetailLogExample example);

    RecruitTargetMainDetailLog selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") RecruitTargetMainDetailLog record, @Param("example") RecruitTargetMainDetailLogExample example);

    int updateByExample(@Param("record") RecruitTargetMainDetailLog record, @Param("example") RecruitTargetMainDetailLogExample example);

    int updateByPrimaryKeySelective(RecruitTargetMainDetailLog record);

    int updateByPrimaryKey(RecruitTargetMainDetailLog record);
}