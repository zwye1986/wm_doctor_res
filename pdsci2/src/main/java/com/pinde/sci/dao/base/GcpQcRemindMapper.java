package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpQcRemind;
import com.pinde.sci.model.mo.GcpQcRemindExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GcpQcRemindMapper {
    int countByExample(GcpQcRemindExample example);

    int deleteByExample(GcpQcRemindExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(GcpQcRemind record);

    int insertSelective(GcpQcRemind record);

    List<GcpQcRemind> selectByExample(GcpQcRemindExample example);

    GcpQcRemind selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") GcpQcRemind record, @Param("example") GcpQcRemindExample example);

    int updateByExample(@Param("record") GcpQcRemind record, @Param("example") GcpQcRemindExample example);

    int updateByPrimaryKeySelective(GcpQcRemind record);

    int updateByPrimaryKey(GcpQcRemind record);
}