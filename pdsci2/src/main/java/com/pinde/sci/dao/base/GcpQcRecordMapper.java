package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpQcRecord;
import com.pinde.sci.model.mo.GcpQcRecordExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GcpQcRecordMapper {
    int countByExample(GcpQcRecordExample example);

    int deleteByExample(GcpQcRecordExample example);

    int deleteByPrimaryKey(String qcFlow);

    int insert(GcpQcRecord record);

    int insertSelective(GcpQcRecord record);

    List<GcpQcRecord> selectByExample(GcpQcRecordExample example);

    GcpQcRecord selectByPrimaryKey(String qcFlow);

    int updateByExampleSelective(@Param("record") GcpQcRecord record, @Param("example") GcpQcRecordExample example);

    int updateByExample(@Param("record") GcpQcRecord record, @Param("example") GcpQcRecordExample example);

    int updateByPrimaryKeySelective(GcpQcRecord record);

    int updateByPrimaryKey(GcpQcRecord record);
}