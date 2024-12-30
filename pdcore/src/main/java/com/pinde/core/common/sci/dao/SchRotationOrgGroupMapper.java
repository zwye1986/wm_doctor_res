package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SchRotationOrgGroup;
import com.pinde.core.model.SchRotationOrgGroupExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchRotationOrgGroupMapper {
    int countByExample(SchRotationOrgGroupExample example);

    int deleteByExample(SchRotationOrgGroupExample example);

    int deleteByPrimaryKey(String groupFlow);

    int insert(SchRotationOrgGroup record);

    int insertSelective(SchRotationOrgGroup record);

    List<SchRotationOrgGroup> selectByExample(SchRotationOrgGroupExample example);

    SchRotationOrgGroup selectByPrimaryKey(String groupFlow);

    int updateByExampleSelective(@Param("record") SchRotationOrgGroup record, @Param("example") SchRotationOrgGroupExample example);

    int updateByExample(@Param("record") SchRotationOrgGroup record, @Param("example") SchRotationOrgGroupExample example);

    int updateByPrimaryKeySelective(SchRotationOrgGroup record);

    int updateByPrimaryKey(SchRotationOrgGroup record);
}