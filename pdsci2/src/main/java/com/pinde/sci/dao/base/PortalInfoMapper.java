package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PortalInfo;
import com.pinde.sci.model.mo.PortalInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PortalInfoMapper {
    int countByExample(PortalInfoExample example);

    int deleteByExample(PortalInfoExample example);

    int deleteByPrimaryKey(String infoFlow);

    int insert(PortalInfo record);

    int insertSelective(PortalInfo record);

    List<PortalInfo> selectByExampleWithBLOBs(PortalInfoExample example);

    List<PortalInfo> selectByExample(PortalInfoExample example);

    PortalInfo selectByPrimaryKey(String infoFlow);

    int updateByExampleSelective(@Param("record") PortalInfo record, @Param("example") PortalInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") PortalInfo record, @Param("example") PortalInfoExample example);

    int updateByExample(@Param("record") PortalInfo record, @Param("example") PortalInfoExample example);

    int updateByPrimaryKeySelective(PortalInfo record);

    int updateByPrimaryKeyWithBLOBs(PortalInfo record);

    int updateByPrimaryKey(PortalInfo record);
}