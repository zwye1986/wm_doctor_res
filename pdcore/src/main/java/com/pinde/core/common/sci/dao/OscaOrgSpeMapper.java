package com.pinde.core.common.sci.dao;

import com.pinde.core.model.OscaOrgSpe;
import com.pinde.core.model.OscaOrgSpeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaOrgSpeMapper {
    int countByExample(OscaOrgSpeExample example);

    int deleteByExample(OscaOrgSpeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(OscaOrgSpe record);

    int insertSelective(OscaOrgSpe record);

    List<OscaOrgSpe> selectByExample(OscaOrgSpeExample example);

    OscaOrgSpe selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") OscaOrgSpe record, @Param("example") OscaOrgSpeExample example);

    int updateByExample(@Param("record") OscaOrgSpe record, @Param("example") OscaOrgSpeExample example);

    int updateByPrimaryKeySelective(OscaOrgSpe record);

    int updateByPrimaryKey(OscaOrgSpe record);
}