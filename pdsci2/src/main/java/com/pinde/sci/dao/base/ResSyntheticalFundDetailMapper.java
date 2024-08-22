package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResSyntheticalFundDetail;
import com.pinde.sci.model.mo.ResSyntheticalFundDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSyntheticalFundDetailMapper {
    int countByExample(ResSyntheticalFundDetailExample example);

    int deleteByExample(ResSyntheticalFundDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResSyntheticalFundDetail record);

    int insertSelective(ResSyntheticalFundDetail record);

    List<ResSyntheticalFundDetail> selectByExample(ResSyntheticalFundDetailExample example);

    ResSyntheticalFundDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResSyntheticalFundDetail record, @Param("example") ResSyntheticalFundDetailExample example);

    int updateByExample(@Param("record") ResSyntheticalFundDetail record, @Param("example") ResSyntheticalFundDetailExample example);

    int updateByPrimaryKeySelective(ResSyntheticalFundDetail record);

    int updateByPrimaryKey(ResSyntheticalFundDetail record);
}