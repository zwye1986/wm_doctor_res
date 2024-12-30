package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SysMonthly;
import com.pinde.core.model.SysMonthlyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyMapper {
    int countByExample(SysMonthlyExample example);

    int deleteByExample(SysMonthlyExample example);

    int deleteByPrimaryKey(String dateMonth);

    int insert(SysMonthly record);

    int insertSelective(SysMonthly record);

    List<SysMonthly> selectByExample(SysMonthlyExample example);

    SysMonthly selectByPrimaryKey(String dateMonth);

    int updateByExampleSelective(@Param("record") SysMonthly record, @Param("example") SysMonthlyExample example);

    int updateByExample(@Param("record") SysMonthly record, @Param("example") SysMonthlyExample example);

    int updateByPrimaryKeySelective(SysMonthly record);

    int updateByPrimaryKey(SysMonthly record);
}