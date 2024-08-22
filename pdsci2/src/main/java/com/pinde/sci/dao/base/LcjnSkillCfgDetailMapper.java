package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.LcjnSkillCfgDetail;
import com.pinde.sci.model.mo.LcjnSkillCfgDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnSkillCfgDetailMapper {
    int countByExample(LcjnSkillCfgDetailExample example);

    int deleteByExample(LcjnSkillCfgDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnSkillCfgDetail record);

    int insertSelective(LcjnSkillCfgDetail record);

    List<LcjnSkillCfgDetail> selectByExample(LcjnSkillCfgDetailExample example);

    LcjnSkillCfgDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnSkillCfgDetail record, @Param("example") LcjnSkillCfgDetailExample example);

    int updateByExample(@Param("record") LcjnSkillCfgDetail record, @Param("example") LcjnSkillCfgDetailExample example);

    int updateByPrimaryKeySelective(LcjnSkillCfgDetail record);

    int updateByPrimaryKey(LcjnSkillCfgDetail record);
}