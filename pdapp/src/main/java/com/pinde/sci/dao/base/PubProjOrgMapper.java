package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubProjOrg;
import com.pinde.sci.model.mo.PubProjOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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