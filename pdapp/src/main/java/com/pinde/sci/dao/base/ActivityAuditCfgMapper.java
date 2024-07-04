package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ActivityAuditCfg;
import com.pinde.sci.model.mo.ActivityAuditCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ActivityAuditCfgMapper {
    int countByExample(ActivityAuditCfgExample example);

    int deleteByExample(ActivityAuditCfgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ActivityAuditCfg record);

    int insertSelective(ActivityAuditCfg record);

    List<ActivityAuditCfg> selectByExample(ActivityAuditCfgExample example);

    ActivityAuditCfg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ActivityAuditCfg record, @Param("example") ActivityAuditCfgExample example);

    int updateByExample(@Param("record") ActivityAuditCfg record, @Param("example") ActivityAuditCfgExample example);

    int updateByPrimaryKeySelective(ActivityAuditCfg record);

    int updateByPrimaryKey(ActivityAuditCfg record);
}