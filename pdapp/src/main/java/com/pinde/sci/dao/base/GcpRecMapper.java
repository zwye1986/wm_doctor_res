package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpRec;
import com.pinde.sci.model.mo.GcpRecExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GcpRecMapper {
    int countByExample(GcpRecExample example);

    int deleteByExample(GcpRecExample example);

    int deleteByPrimaryKey(String recFlow);

    int insert(GcpRec record);

    int insertSelective(GcpRec record);

    List<GcpRec> selectByExampleWithBLOBs(GcpRecExample example);

    List<GcpRec> selectByExample(GcpRecExample example);

    GcpRec selectByPrimaryKey(String recFlow);

    int updateByExampleSelective(@Param("record") GcpRec record, @Param("example") GcpRecExample example);

    int updateByExampleWithBLOBs(@Param("record") GcpRec record, @Param("example") GcpRecExample example);

    int updateByExample(@Param("record") GcpRec record, @Param("example") GcpRecExample example);

    int updateByPrimaryKeySelective(GcpRec record);

    int updateByPrimaryKeyWithBLOBs(GcpRec record);

    int updateByPrimaryKey(GcpRec record);
}