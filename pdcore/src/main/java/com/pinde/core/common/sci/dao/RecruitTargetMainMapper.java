package com.pinde.core.common.sci.dao;

import com.pinde.core.model.RecruitTargetMain;
import com.pinde.core.model.RecruitTargetMainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitTargetMainMapper {
    int countByExample(RecruitTargetMainExample example);

    int deleteByExample(RecruitTargetMainExample example);

    int deleteByPrimaryKey(String rargetFlow);

    int insert(RecruitTargetMain record);

    int insertSelective(RecruitTargetMain record);

    List<RecruitTargetMain> selectByExample(RecruitTargetMainExample example);

    RecruitTargetMain selectByPrimaryKey(String rargetFlow);

    int updateByExampleSelective(@Param("record") RecruitTargetMain record, @Param("example") RecruitTargetMainExample example);

    int updateByExample(@Param("record") RecruitTargetMain record, @Param("example") RecruitTargetMainExample example);

    int updateByPrimaryKeySelective(RecruitTargetMain record);

    int updateByPrimaryKey(RecruitTargetMain record);
}