package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysMonthlyBlacklistInfo;
import com.pinde.sci.model.mo.SysMonthlyBlacklistInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMonthlyBlacklistInfoMapper {
    int countByExample(SysMonthlyBlacklistInfoExample example);

    int deleteByExample(SysMonthlyBlacklistInfoExample example);

    int deleteByPrimaryKey(String smrdiFlow);

    int insert(SysMonthlyBlacklistInfo record);

    int insertSelective(SysMonthlyBlacklistInfo record);

    List<SysMonthlyBlacklistInfo> selectByExample(SysMonthlyBlacklistInfoExample example);

    SysMonthlyBlacklistInfo selectByPrimaryKey(String smrdiFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyBlacklistInfo record, @Param("example") SysMonthlyBlacklistInfoExample example);

    int updateByExample(@Param("record") SysMonthlyBlacklistInfo record, @Param("example") SysMonthlyBlacklistInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyBlacklistInfo record);

    int updateByPrimaryKey(SysMonthlyBlacklistInfo record);
}