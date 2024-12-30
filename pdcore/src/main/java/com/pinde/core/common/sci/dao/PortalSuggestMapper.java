package com.pinde.core.common.sci.dao;

import com.pinde.core.model.PortalSuggest;
import com.pinde.core.model.PortalSuggestExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalSuggestMapper {
    int countByExample(PortalSuggestExample example);

    int deleteByExample(PortalSuggestExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PortalSuggest record);

    int insertSelective(PortalSuggest record);

    List<PortalSuggest> selectByExample(PortalSuggestExample example);

    PortalSuggest selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PortalSuggest record, @Param("example") PortalSuggestExample example);

    int updateByExample(@Param("record") PortalSuggest record, @Param("example") PortalSuggestExample example);

    int updateByPrimaryKeySelective(PortalSuggest record);

    int updateByPrimaryKey(PortalSuggest record);
}