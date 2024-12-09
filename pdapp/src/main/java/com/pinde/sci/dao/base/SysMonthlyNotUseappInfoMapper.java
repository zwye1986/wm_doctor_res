package com.pinde.sci.dao.base;

import com.pinde.core.model.SysMonthlyNotUseappInfo;
import com.pinde.core.model.SysMonthlyNotUseappInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMonthlyNotUseappInfoMapper {
    int countByExample(SysMonthlyNotUseappInfoExample example);

    int deleteByExample(SysMonthlyNotUseappInfoExample example);

    int deleteByPrimaryKey(String smrdiFlow);

    int insert(SysMonthlyNotUseappInfo record);

    int insertSelective(SysMonthlyNotUseappInfo record);

    List<SysMonthlyNotUseappInfo> selectByExample(SysMonthlyNotUseappInfoExample example);

    SysMonthlyNotUseappInfo selectByPrimaryKey(String smrdiFlow);

    int updateByExampleSelective(@Param("record") SysMonthlyNotUseappInfo record, @Param("example") SysMonthlyNotUseappInfoExample example);

    int updateByExample(@Param("record") SysMonthlyNotUseappInfo record, @Param("example") SysMonthlyNotUseappInfoExample example);

    int updateByPrimaryKeySelective(SysMonthlyNotUseappInfo record);

    int updateByPrimaryKey(SysMonthlyNotUseappInfo record);
}