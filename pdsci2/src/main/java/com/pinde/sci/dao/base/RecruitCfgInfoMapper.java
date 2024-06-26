package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.RecruitCfgInfo;
import com.pinde.sci.model.mo.RecruitCfgInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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