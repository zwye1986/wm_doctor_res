package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpQcRecordRec;
import com.pinde.sci.model.mo.GcpQcRecordRecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GcpQcRecordRecMapper {
    int countByExample(GcpQcRecordRecExample example);

    int deleteByExample(GcpQcRecordRecExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(GcpQcRecordRec record);

    int insertSelective(GcpQcRecordRec record);

    List<GcpQcRecordRec> selectByExampleWithBLOBs(GcpQcRecordRecExample example);

    List<GcpQcRecordRec> selectByExample(GcpQcRecordRecExample example);

    GcpQcRecordRec selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") GcpQcRecordRec record, @Param("example") GcpQcRecordRecExample example);

    int updateByExampleWithBLOBs(@Param("record") GcpQcRecordRec record, @Param("example") GcpQcRecordRecExample example);

    int updateByExample(@Param("record") GcpQcRecordRec record, @Param("example") GcpQcRecordRecExample example);

    int updateByPrimaryKeySelective(GcpQcRecordRec record);

    int updateByPrimaryKeyWithBLOBs(GcpQcRecordRec record);

    int updateByPrimaryKey(GcpQcRecordRec record);
}