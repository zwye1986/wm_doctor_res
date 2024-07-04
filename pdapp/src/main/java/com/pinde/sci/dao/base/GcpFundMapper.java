package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.GcpFund;
import com.pinde.sci.model.mo.GcpFundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GcpFundMapper {
    int countByExample(GcpFundExample example);

    int deleteByExample(GcpFundExample example);

    int deleteByPrimaryKey(String fundFlow);

    int insert(GcpFund record);

    int insertSelective(GcpFund record);

    List<GcpFund> selectByExample(GcpFundExample example);

    GcpFund selectByPrimaryKey(String fundFlow);

    int updateByExampleSelective(@Param("record") GcpFund record, @Param("example") GcpFundExample example);

    int updateByExample(@Param("record") GcpFund record, @Param("example") GcpFundExample example);

    int updateByPrimaryKeySelective(GcpFund record);

    int updateByPrimaryKey(GcpFund record);
}