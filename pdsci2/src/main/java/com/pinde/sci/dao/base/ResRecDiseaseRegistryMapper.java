package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResRecDiseaseRegistry;
import com.pinde.sci.model.mo.ResRecDiseaseRegistryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResRecDiseaseRegistryMapper {
    int countByExample(ResRecDiseaseRegistryExample example);

    int deleteByExample(ResRecDiseaseRegistryExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(ResRecDiseaseRegistry record);

    int insertSelective(ResRecDiseaseRegistry record);

    List<ResRecDiseaseRegistry> selectByExampleWithBLOBs(ResRecDiseaseRegistryExample example);

    List<ResRecDiseaseRegistry> selectByExample(ResRecDiseaseRegistryExample example);

    ResRecDiseaseRegistry selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") ResRecDiseaseRegistry record, @Param("example") ResRecDiseaseRegistryExample example);

    int updateByExampleWithBLOBs(@Param("record") ResRecDiseaseRegistry record, @Param("example") ResRecDiseaseRegistryExample example);

    int updateByExample(@Param("record") ResRecDiseaseRegistry record, @Param("example") ResRecDiseaseRegistryExample example);

    int updateByPrimaryKeySelective(ResRecDiseaseRegistry record);

    int updateByPrimaryKeyWithBLOBs(ResRecDiseaseRegistry record);

    int updateByPrimaryKey(ResRecDiseaseRegistry record);
}