package com.pinde.sci.dao.base;

import com.pinde.core.model.ResRecCampaignRegistry;
import com.pinde.core.model.ResRecCampaignRegistryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRecCampaignRegistryMapper {
    int countByExample(ResRecCampaignRegistryExample example);

    int deleteByExample(ResRecCampaignRegistryExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(ResRecCampaignRegistry record);

    int insertSelective(ResRecCampaignRegistry record);

    List<ResRecCampaignRegistry> selectByExampleWithBLOBs(ResRecCampaignRegistryExample example);

    List<ResRecCampaignRegistry> selectByExample(ResRecCampaignRegistryExample example);

    ResRecCampaignRegistry selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") ResRecCampaignRegistry record, @Param("example") ResRecCampaignRegistryExample example);

    int updateByExampleWithBLOBs(@Param("record") ResRecCampaignRegistry record, @Param("example") ResRecCampaignRegistryExample example);

    int updateByExample(@Param("record") ResRecCampaignRegistry record, @Param("example") ResRecCampaignRegistryExample example);

    int updateByPrimaryKeySelective(ResRecCampaignRegistry record);

    int updateByPrimaryKeyWithBLOBs(ResRecCampaignRegistry record);

    int updateByPrimaryKey(ResRecCampaignRegistry record);
}