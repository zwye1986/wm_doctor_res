package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertOrgSpe;
import com.pinde.sci.model.mo.ExpertOrgSpeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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