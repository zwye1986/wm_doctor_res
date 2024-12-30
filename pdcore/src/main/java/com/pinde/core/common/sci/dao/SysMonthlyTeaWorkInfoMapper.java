package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysMonthlyTeaWorkInfo;
import com.pinde.core.model.SysMonthlyTeaWorkInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyTeaWorkInfoMapper {
    int countByExample(SysMonthlyTeaWorkInfoExample example);

    int deleteByExample(SysMonthlyTeaWorkInfoExample example);

    int deleteByPrimaryKey(String smrdiFlow);

    int insert(SysMonthlyTeaWorkInfo record);

    int insertSelective(SysMonthlyTeaWorkInfo record);

    List<SysMonthlyTeaWorkInfo> selectByExample(SysMonthlyTeaWorkInfoExample example);

    SysMonthlyTeaWorkInfo selectByPrimaryKey(String smrdiFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyTeaWorkInfo record, @Param("example") SysMonthlyTeaWorkInfoExample example);

    int updateByExample(@Param("record") SysMonthlyTeaWorkInfo record, @Param("example") SysMonthlyTeaWorkInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyTeaWorkInfo record);

    int updateByPrimaryKey(SysMonthlyTeaWorkInfo record);
}