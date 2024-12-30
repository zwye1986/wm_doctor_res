package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResSyntheticalFund;
import com.pinde.core.model.ResSyntheticalFundExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSyntheticalFundMapper {
    int countByExample(ResSyntheticalFundExample example);

    int deleteByExample(ResSyntheticalFundExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResSyntheticalFund record);

    int insertSelective(ResSyntheticalFund record);

    List<ResSyntheticalFund> selectByExample(ResSyntheticalFundExample example);

    ResSyntheticalFund selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResSyntheticalFund record, @Param("example") ResSyntheticalFundExample example);

    int updateByExample(@Param("record") ResSyntheticalFund record, @Param("example") ResSyntheticalFundExample example);

    int updateByPrimaryKeySelective(ResSyntheticalFund record);

    int updateByPrimaryKey(ResSyntheticalFund record);
}