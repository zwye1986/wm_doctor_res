package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SysMonthly;
import com.pinde.sci.model.mo.SysMonthlyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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