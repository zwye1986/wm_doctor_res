package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResRecCaseRegistry;
import com.pinde.sci.model.mo.ResRecCaseRegistryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResRecCaseRegistryMapper {
    int countByExample(ResRecCaseRegistryExample example);

    int deleteByExample(ResRecCaseRegistryExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(ResRecCaseRegistry record);

    int insertSelective(ResRecCaseRegistry record);

    List<ResRecCaseRegistry> selectByExampleWithBLOBs(ResRecCaseRegistryExample example);

    List<ResRecCaseRegistry> selectByExample(ResRecCaseRegistryExample example);

    ResRecCaseRegistry selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") ResRecCaseRegistry record, @Param("example") ResRecCaseRegistryExample example);

    int updateByExampleWithBLOBs(@Param("record") ResRecCaseRegistry record, @Param("example") ResRecCaseRegistryExample example);

    int updateByExample(@Param("record") ResRecCaseRegistry record, @Param("example") ResRecCaseRegistryExample example);

    int updateByPrimaryKeySelective(ResRecCaseRegistry record);

    int updateByPrimaryKeyWithBLOBs(ResRecCaseRegistry record);

    int updateByPrimaryKey(ResRecCaseRegistry record);
}