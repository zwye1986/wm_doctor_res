package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SchRotationOrgCfg;
import com.pinde.sci.model.mo.SchRotationOrgCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SchRotationOrgCfgMapper {
    int countByExample(SchRotationOrgCfgExample example);

    int deleteByExample(SchRotationOrgCfgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SchRotationOrgCfg record);

    int insertSelective(SchRotationOrgCfg record);

    List<SchRotationOrgCfg> selectByExample(SchRotationOrgCfgExample example);

    SchRotationOrgCfg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SchRotationOrgCfg record, @Param("example") SchRotationOrgCfgExample example);

    int updateByExample(@Param("record") SchRotationOrgCfg record, @Param("example") SchRotationOrgCfgExample example);

    int updateByPrimaryKeySelective(SchRotationOrgCfg record);

    int updateByPrimaryKey(SchRotationOrgCfg record);
}