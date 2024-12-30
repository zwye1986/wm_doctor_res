package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResGradeBorderlineOrg;
import com.pinde.core.model.ResGradeBorderlineOrgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResGradeBorderlineOrgMapper {
    int countByExample(ResGradeBorderlineOrgExample example);

    int deleteByExample(ResGradeBorderlineOrgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResGradeBorderlineOrg record);

    int insertSelective(ResGradeBorderlineOrg record);

    List<ResGradeBorderlineOrg> selectByExample(ResGradeBorderlineOrgExample example);

    ResGradeBorderlineOrg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResGradeBorderlineOrg record, @Param("example") ResGradeBorderlineOrgExample example);

    int updateByExample(@Param("record") ResGradeBorderlineOrg record, @Param("example") ResGradeBorderlineOrgExample example);

    int updateByPrimaryKeySelective(ResGradeBorderlineOrg record);

    int updateByPrimaryKey(ResGradeBorderlineOrg record);
}