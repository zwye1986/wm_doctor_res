package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResSyntheticalFund;
import com.pinde.sci.model.mo.ResSyntheticalFundExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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