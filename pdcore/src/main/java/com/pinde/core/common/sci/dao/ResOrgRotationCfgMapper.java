package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResOrgRotationCfg;
import com.pinde.core.model.ResOrgRotationCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResOrgRotationCfgMapper {
    int countByExample(ResOrgRotationCfgExample example);

    int deleteByExample(ResOrgRotationCfgExample example);

    int deleteByPrimaryKey(String rotationCfgFlow);

    int insert(ResOrgRotationCfg record);

    int insertSelective(ResOrgRotationCfg record);

    List<ResOrgRotationCfg> selectByExample(ResOrgRotationCfgExample example);

    ResOrgRotationCfg selectByPrimaryKey(String rotationCfgFlow);

    int updateByExampleSelective(@Param("record") ResOrgRotationCfg record, @Param("example") ResOrgRotationCfgExample example);

    int updateByExample(@Param("record") ResOrgRotationCfg record, @Param("example") ResOrgRotationCfgExample example);

    int updateByPrimaryKeySelective(ResOrgRotationCfg record);

    int updateByPrimaryKey(ResOrgRotationCfg record);
}