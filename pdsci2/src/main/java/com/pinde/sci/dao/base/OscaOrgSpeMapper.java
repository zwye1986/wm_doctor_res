package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaOrgSpe;
import com.pinde.sci.model.mo.OscaOrgSpeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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