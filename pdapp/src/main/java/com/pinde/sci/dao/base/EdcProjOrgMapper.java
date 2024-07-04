package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcProjOrg;
import com.pinde.sci.model.mo.EdcProjOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcProjOrgMapper {
    int countByExample(EdcProjOrgExample example);

    int deleteByExample(EdcProjOrgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcProjOrg record);

    int insertSelective(EdcProjOrg record);

    List<EdcProjOrg> selectByExample(EdcProjOrgExample example);

    EdcProjOrg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcProjOrg record, @Param("example") EdcProjOrgExample example);

    int updateByExample(@Param("record") EdcProjOrg record, @Param("example") EdcProjOrgExample example);

    int updateByPrimaryKeySelective(EdcProjOrg record);

    int updateByPrimaryKey(EdcProjOrg record);
}