package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpDrugIn;
import com.pinde.sci.model.mo.GcpDrugInExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GcpDrugInMapper {
    int countByExample(GcpDrugInExample example);

    int deleteByExample(GcpDrugInExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(GcpDrugIn record);

    int insertSelective(GcpDrugIn record);

    List<GcpDrugIn> selectByExample(GcpDrugInExample example);

    GcpDrugIn selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") GcpDrugIn record, @Param("example") GcpDrugInExample example);

    int updateByExample(@Param("record") GcpDrugIn record, @Param("example") GcpDrugInExample example);

    int updateByPrimaryKeySelective(GcpDrugIn record);

    int updateByPrimaryKey(GcpDrugIn record);
}