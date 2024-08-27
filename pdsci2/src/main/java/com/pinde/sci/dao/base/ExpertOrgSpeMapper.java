package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertOrgSpe;
import com.pinde.sci.model.mo.ExpertOrgSpeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpertOrgSpeMapper {
    int countByExample(ExpertOrgSpeExample example);

    int deleteByExample(ExpertOrgSpeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ExpertOrgSpe record);

    int insertSelective(ExpertOrgSpe record);

    List<ExpertOrgSpe> selectByExample(ExpertOrgSpeExample example);

    ExpertOrgSpe selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ExpertOrgSpe record, @Param("example") ExpertOrgSpeExample example);

    int updateByExample(@Param("record") ExpertOrgSpe record, @Param("example") ExpertOrgSpeExample example);

    int updateByPrimaryKeySelective(ExpertOrgSpe record);

    int updateByPrimaryKey(ExpertOrgSpe record);
}