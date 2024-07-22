package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpDrugStoreRec;
import com.pinde.sci.model.mo.GcpDrugStoreRecExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GcpDrugStoreRecMapper {
    int countByExample(GcpDrugStoreRecExample example);

    int deleteByExample(GcpDrugStoreRecExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(GcpDrugStoreRec record);

    int insertSelective(GcpDrugStoreRec record);

    List<GcpDrugStoreRec> selectByExample(GcpDrugStoreRecExample example);

    GcpDrugStoreRec selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") GcpDrugStoreRec record, @Param("example") GcpDrugStoreRecExample example);

    int updateByExample(@Param("record") GcpDrugStoreRec record, @Param("example") GcpDrugStoreRecExample example);

    int updateByPrimaryKeySelective(GcpDrugStoreRec record);

    int updateByPrimaryKey(GcpDrugStoreRec record);
}