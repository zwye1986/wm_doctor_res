package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResRotationOrg;
import com.pinde.sci.model.mo.ResRotationOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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