package com.pinde.sci.dao.base;

import com.pinde.core.model.SysMonthlyDocCycleInfo;
import com.pinde.core.model.SysMonthlyDocCycleInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMonthlyDocCycleInfoMapper {
    int countByExample(SysMonthlyDocCycleInfoExample example);

    int deleteByExample(SysMonthlyDocCycleInfoExample example);

    int deleteByPrimaryKey(String smdciFlow);

    int insert(SysMonthlyDocCycleInfo record);

    int insertSelective(SysMonthlyDocCycleInfo record);

    List<SysMonthlyDocCycleInfo> selectByExample(SysMonthlyDocCycleInfoExample example);

    SysMonthlyDocCycleInfo selectByPrimaryKey(String smdciFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyDocCycleInfo record, @Param("example") SysMonthlyDocCycleInfoExample example);

    int updateByExample(@Param("record") SysMonthlyDocCycleInfo record, @Param("example") SysMonthlyDocCycleInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyDocCycleInfo record);

    int updateByPrimaryKey(SysMonthlyDocCycleInfo record);
}