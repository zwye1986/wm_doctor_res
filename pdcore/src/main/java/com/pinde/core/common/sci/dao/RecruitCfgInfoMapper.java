package com.pinde.core.common.sci.dao;

import com.pinde.core.model.RecruitCfgInfo;
import com.pinde.core.model.RecruitCfgInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecruitCfgInfoMapper {
    int countByExample(RecruitCfgInfoExample example);

    int deleteByExample(RecruitCfgInfoExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(RecruitCfgInfo record);

    int insertSelective(RecruitCfgInfo record);

    List<RecruitCfgInfo> selectByExample(RecruitCfgInfoExample example);

    RecruitCfgInfo selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") RecruitCfgInfo record, @Param("example") RecruitCfgInfoExample example);

    int updateByExample(@Param("record") RecruitCfgInfo record, @Param("example") RecruitCfgInfoExample example);

    int updateByPrimaryKeySelective(RecruitCfgInfo record);

    int updateByPrimaryKey(RecruitCfgInfo record);
}