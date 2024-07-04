package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpDrugOut;
import com.pinde.sci.model.mo.GcpDrugOutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GcpDrugOutMapper {
    int countByExample(GcpDrugOutExample example);

    int deleteByExample(GcpDrugOutExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(GcpDrugOut record);

    int insertSelective(GcpDrugOut record);

    List<GcpDrugOut> selectByExample(GcpDrugOutExample example);

    GcpDrugOut selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") GcpDrugOut record, @Param("example") GcpDrugOutExample example);

    int updateByExample(@Param("record") GcpDrugOut record, @Param("example") GcpDrugOutExample example);

    int updateByPrimaryKeySelective(GcpDrugOut record);

    int updateByPrimaryKey(GcpDrugOut record);
}