package com.pinde.sci.dao.base;

import com.pinde.core.model.SysMonthlyChangeInfo;
import com.pinde.core.model.SysMonthlyChangeInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMonthlyChangeInfoMapper {
    int countByExample(SysMonthlyChangeInfoExample example);

    int deleteByExample(SysMonthlyChangeInfoExample example);

    int deleteByPrimaryKey(String smciFlow);

    int insert(SysMonthlyChangeInfo record);

    int insertSelective(SysMonthlyChangeInfo record);

    List<SysMonthlyChangeInfo> selectByExample(SysMonthlyChangeInfoExample example);

    SysMonthlyChangeInfo selectByPrimaryKey(String smciFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyChangeInfo record, @Param("example") SysMonthlyChangeInfoExample example);

    int updateByExample(@Param("record") SysMonthlyChangeInfo record, @Param("example") SysMonthlyChangeInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyChangeInfo record);

    int updateByPrimaryKey(SysMonthlyChangeInfo record);
}