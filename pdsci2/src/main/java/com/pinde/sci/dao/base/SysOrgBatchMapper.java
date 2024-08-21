package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysOrgBatch;
import com.pinde.sci.model.mo.SysOrgBatchExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysOrgBatchMapper {
    int countByExample(SysOrgBatchExample example);

    int deleteByExample(SysOrgBatchExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(SysOrgBatch record);

    int insertSelective(SysOrgBatch record);

    List<SysOrgBatch> selectByExample(SysOrgBatchExample example);

    SysOrgBatch selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") SysOrgBatch record, @Param("example") SysOrgBatchExample example);

    int updateByExample(@Param("record") SysOrgBatch record, @Param("example") SysOrgBatchExample example);

    int updateByPrimaryKeySelective(SysOrgBatch record);

    int updateByPrimaryKey(SysOrgBatch record);
}