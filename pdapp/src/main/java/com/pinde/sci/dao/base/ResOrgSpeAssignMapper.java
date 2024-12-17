package com.pinde.sci.dao.base;

import com.pinde.core.model.ResOrgSpeAssign;
import com.pinde.core.model.ResOrgSpeAssignExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResOrgSpeAssignMapper {
    int countByExample(ResOrgSpeAssignExample example);

    int deleteByExample(ResOrgSpeAssignExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResOrgSpeAssign record);

    int insertSelective(ResOrgSpeAssign record);

    List<ResOrgSpeAssign> selectByExample(ResOrgSpeAssignExample example);

    ResOrgSpeAssign selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResOrgSpeAssign record, @Param("example") ResOrgSpeAssignExample example);

    int updateByExample(@Param("record") ResOrgSpeAssign record, @Param("example") ResOrgSpeAssignExample example);

    int updateByPrimaryKeySelective(ResOrgSpeAssign record);

    int updateByPrimaryKey(ResOrgSpeAssign record);
}