package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResRecOperationRegistry;
import com.pinde.core.model.ResRecOperationRegistryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRecOperationRegistryMapper {
    int countByExample(ResRecOperationRegistryExample example);

    int deleteByExample(ResRecOperationRegistryExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(ResRecOperationRegistry record);

    int insertSelective(ResRecOperationRegistry record);

    List<ResRecOperationRegistry> selectByExampleWithBLOBs(ResRecOperationRegistryExample example);

    List<ResRecOperationRegistry> selectByExample(ResRecOperationRegistryExample example);

    ResRecOperationRegistry selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") ResRecOperationRegistry record, @Param("example") ResRecOperationRegistryExample example);

    int updateByExampleWithBLOBs(@Param("record") ResRecOperationRegistry record, @Param("example") ResRecOperationRegistryExample example);

    int updateByExample(@Param("record") ResRecOperationRegistry record, @Param("example") ResRecOperationRegistryExample example);

    int updateByPrimaryKeySelective(ResRecOperationRegistry record);

    int updateByPrimaryKeyWithBLOBs(ResRecOperationRegistry record);

    int updateByPrimaryKey(ResRecOperationRegistry record);
}