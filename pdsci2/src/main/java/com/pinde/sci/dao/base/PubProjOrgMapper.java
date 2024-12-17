package com.pinde.sci.dao.base;

import com.pinde.core.model.PubProjOrg;
import com.pinde.core.model.PubProjOrgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PubProjOrgMapper {
    int countByExample(PubProjOrgExample example);

    int deleteByExample(PubProjOrgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubProjOrg record);

    int insertSelective(PubProjOrg record);

    List<PubProjOrg> selectByExample(PubProjOrgExample example);

    PubProjOrg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubProjOrg record, @Param("example") PubProjOrgExample example);

    int updateByExample(@Param("record") PubProjOrg record, @Param("example") PubProjOrgExample example);

    int updateByPrimaryKeySelective(PubProjOrg record);

    int updateByPrimaryKey(PubProjOrg record);
}