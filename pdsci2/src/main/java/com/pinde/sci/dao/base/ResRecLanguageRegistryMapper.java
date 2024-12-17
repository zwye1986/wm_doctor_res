package com.pinde.sci.dao.base;

import com.pinde.core.model.ResRecLanguageRegistry;
import com.pinde.core.model.ResRecLanguageRegistryExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRecLanguageRegistryMapper {
    int countByExample(ResRecLanguageRegistryExample example);

    int deleteByExample(ResRecLanguageRegistryExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(ResRecLanguageRegistry record);

    int insertSelective(ResRecLanguageRegistry record);

    List<ResRecLanguageRegistry> selectByExampleWithBLOBs(ResRecLanguageRegistryExample example);

    List<ResRecLanguageRegistry> selectByExample(ResRecLanguageRegistryExample example);

    ResRecLanguageRegistry selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") ResRecLanguageRegistry record, @Param("example") ResRecLanguageRegistryExample example);

    int updateByExampleWithBLOBs(@Param("record") ResRecLanguageRegistry record, @Param("example") ResRecLanguageRegistryExample example);

    int updateByExample(@Param("record") ResRecLanguageRegistry record, @Param("example") ResRecLanguageRegistryExample example);

    int updateByPrimaryKeySelective(ResRecLanguageRegistry record);

    int updateByPrimaryKeyWithBLOBs(ResRecLanguageRegistry record);

    int updateByPrimaryKey(ResRecLanguageRegistry record);
}