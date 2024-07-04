package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResGradeBorderlineOrg;
import com.pinde.sci.model.mo.ResGradeBorderlineOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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