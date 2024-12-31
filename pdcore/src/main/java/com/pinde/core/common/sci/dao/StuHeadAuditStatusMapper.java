package com.pinde.core.common.sci.dao;

import com.pinde.core.model.StuHeadAuditStatus;
import com.pinde.core.model.StuHeadAuditStatusExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StuHeadAuditStatusMapper {
    int countByExample(StuHeadAuditStatusExample example);

    int deleteByExample(StuHeadAuditStatusExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(StuHeadAuditStatus record);

    int insertSelective(StuHeadAuditStatus record);

    List<StuHeadAuditStatus> selectByExample(StuHeadAuditStatusExample example);

    StuHeadAuditStatus selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") StuHeadAuditStatus record, @Param("example") StuHeadAuditStatusExample example);

    int updateByExample(@Param("record") StuHeadAuditStatus record, @Param("example") StuHeadAuditStatusExample example);

    int updateByPrimaryKeySelective(StuHeadAuditStatus record);

    int updateByPrimaryKey(StuHeadAuditStatus record);
}