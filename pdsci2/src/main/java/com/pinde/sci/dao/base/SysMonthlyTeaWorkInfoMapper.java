package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysMonthlyTeaWorkInfo;
import com.pinde.sci.model.mo.SysMonthlyTeaWorkInfoExample;
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