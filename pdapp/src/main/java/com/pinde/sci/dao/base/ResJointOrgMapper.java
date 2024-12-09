package com.pinde.sci.dao.base;

import com.pinde.core.model.ResJointOrg;
import com.pinde.core.model.ResJointOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResJointOrgMapper {
    int countByExample(ResJointOrgExample example);

    int deleteByExample(ResJointOrgExample example);

    int deleteByPrimaryKey(String jointFlow);

    int insert(ResJointOrg record);

    int insertSelective(ResJointOrg record);

    List<ResJointOrg> selectByExample(ResJointOrgExample example);

    ResJointOrg selectByPrimaryKey(String jointFlow);

    int updateByExampleSelective(@Param("record") ResJointOrg record, @Param("example") ResJointOrgExample example);

    int updateByExample(@Param("record") ResJointOrg record, @Param("example") ResJointOrgExample example);

    int updateByPrimaryKeySelective(ResJointOrg record);

    int updateByPrimaryKey(ResJointOrg record);
}