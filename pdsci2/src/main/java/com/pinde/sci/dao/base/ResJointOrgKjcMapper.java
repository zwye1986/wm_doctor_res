package com.pinde.sci.dao.base;

import com.pinde.core.model.ResJointOrgKjc;
import com.pinde.core.model.ResJointOrgKjcExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResJointOrgKjcMapper {
    int countByExample(ResJointOrgKjcExample example);

    int deleteByExample(ResJointOrgKjcExample example);

    int deleteByPrimaryKey(String jointFlow);

    int insert(ResJointOrgKjc record);

    int insertSelective(ResJointOrgKjc record);

    List<ResJointOrgKjc> selectByExample(ResJointOrgKjcExample example);

    ResJointOrgKjc selectByPrimaryKey(String jointFlow);

    int updateByExampleSelective(@Param("record") ResJointOrgKjc record, @Param("example") ResJointOrgKjcExample example);

    int updateByExample(@Param("record") ResJointOrgKjc record, @Param("example") ResJointOrgKjcExample example);

    int updateByPrimaryKeySelective(ResJointOrgKjc record);

    int updateByPrimaryKey(ResJointOrgKjc record);
}