package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.ResJointOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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
     * @param orgFlow
     * @return
     */
    ResJointOrg selectByJointOrgFlow(String orgFlow);

    int deleteByOrgFlow(@Param("orgFlow") String orgFlow, @Param("sessionNumber") String sessionNumber);
}