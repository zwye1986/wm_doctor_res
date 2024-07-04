package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysMonthlyActivityInfo;
import com.pinde.sci.model.mo.SysMonthlyActivityInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMonthlyActivityInfoMapper {
    int countByExample(SysMonthlyActivityInfoExample example);

    int deleteByExample(SysMonthlyActivityInfoExample example);

    int deleteByPrimaryKey(String smrdiFlow);

    int insert(SysMonthlyActivityInfo record);

    int insertSelective(SysMonthlyActivityInfo record);

    List<SysMonthlyActivityInfo> selectByExample(SysMonthlyActivityInfoExample example);

    SysMonthlyActivityInfo selectByPrimaryKey(String smrdiFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyActivityInfo record, @Param("example") SysMonthlyActivityInfoExample example);

    int updateByExample(@Param("record") SysMonthlyActivityInfo record, @Param("example") SysMonthlyActivityInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyActivityInfo record);

    int updateByPrimaryKey(SysMonthlyActivityInfo record);
}