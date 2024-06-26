package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResRecCampaignRegistry;
import com.pinde.sci.model.mo.ResRecCampaignRegistryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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