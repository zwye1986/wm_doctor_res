package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResJointOrg;
import com.pinde.core.model.ResJointOrgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResJointOrgMapper {
    int countByExample(ResJointOrgExample example);

    int deleteByExample(ResJointOrgExample example);

    int deleteByPrimaryKey(String jointFlow);

    int insert(ResJointOrg record);

    int insertAll(@Param("resJointOrgList") List<ResJointOrg> resJointOrgList);

    int insertSelective(ResJointOrg record);

    List<ResJointOrg> selectByExample(ResJointOrgExample example);

    ResJointOrg selectByPrimaryKey(String jointFlow);

    int updateByExampleSelective(@Param("record") ResJointOrg record, @Param("example") ResJointOrgExample example);

    int updateByExample(@Param("record") ResJointOrg record, @Param("example") ResJointOrgExample example);

    int updateByPrimaryKeySelective(ResJointOrg record);

    int updateByPrimaryKey(ResJointOrg record);

    /**
     * 查看是否为协同基地
     *
     * @param orgFlow
     * @return
     */
    ResJointOrg selectByJointOrgFlow(String orgFlow);

    int deleteByOrgFlow(@Param("orgFlow") String orgFlow);

    int deleteJointOrg(ResJointOrg jointOrg);
}