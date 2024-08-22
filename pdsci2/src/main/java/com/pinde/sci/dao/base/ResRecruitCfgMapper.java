package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResRecruitCfg;
import com.pinde.sci.model.mo.ResRecruitCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRecruitCfgMapper {
    int countByExample(ResRecruitCfgExample example);

    int deleteByExample(ResRecruitCfgExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(ResRecruitCfg record);

    int insertSelective(ResRecruitCfg record);

    List<ResRecruitCfg> selectByExample(ResRecruitCfgExample example);

    ResRecruitCfg selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") ResRecruitCfg record, @Param("example") ResRecruitCfgExample example);

    int updateByExample(@Param("record") ResRecruitCfg record, @Param("example") ResRecruitCfgExample example);

    int updateByPrimaryKeySelective(ResRecruitCfg record);

    int updateByPrimaryKey(ResRecruitCfg record);
}