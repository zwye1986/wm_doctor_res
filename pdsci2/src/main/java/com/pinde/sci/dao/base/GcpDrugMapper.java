package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpDrug;
import com.pinde.sci.model.mo.GcpDrugExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GcpDrugMapper {
    int countByExample(GcpDrugExample example);

    int deleteByExample(GcpDrugExample example);

    int deleteByPrimaryKey(String drugFlow);

    int insert(GcpDrug record);

    int insertSelective(GcpDrug record);

    List<GcpDrug> selectByExample(GcpDrugExample example);

    GcpDrug selectByPrimaryKey(String drugFlow);

    int updateByExampleSelective(@Param("record") GcpDrug record, @Param("example") GcpDrugExample example);

    int updateByExample(@Param("record") GcpDrug record, @Param("example") GcpDrugExample example);

    int updateByPrimaryKeySelective(GcpDrug record);

    int updateByPrimaryKey(GcpDrug record);
}