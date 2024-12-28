package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResRotationOrg;
import com.pinde.core.model.ResRotationOrgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRotationOrgMapper {
    int countByExample(ResRotationOrgExample example);

    int deleteByExample(ResRotationOrgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResRotationOrg record);

    int insertSelective(ResRotationOrg record);

    List<ResRotationOrg> selectByExample(ResRotationOrgExample example);

    ResRotationOrg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResRotationOrg record, @Param("example") ResRotationOrgExample example);

    int updateByExample(@Param("record") ResRotationOrg record, @Param("example") ResRotationOrgExample example);

    int updateByPrimaryKeySelective(ResRotationOrg record);

    int updateByPrimaryKey(ResRotationOrg record);
}