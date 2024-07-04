package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmFundSchemeDetail;
import com.pinde.sci.model.mo.SrmFundSchemeDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmFundSchemeDetailMapper {
    int countByExample(SrmFundSchemeDetailExample example);

    int deleteByExample(SrmFundSchemeDetailExample example);

    int deleteByPrimaryKey(String itemFlow);

    int insert(SrmFundSchemeDetail record);

    int insertSelective(SrmFundSchemeDetail record);

    List<SrmFundSchemeDetail> selectByExample(SrmFundSchemeDetailExample example);

    SrmFundSchemeDetail selectByPrimaryKey(String itemFlow);

    int updateByExampleSelective(@Param("record") SrmFundSchemeDetail record, @Param("example") SrmFundSchemeDetailExample example);

    int updateByExample(@Param("record") SrmFundSchemeDetail record, @Param("example") SrmFundSchemeDetailExample example);

    int updateByPrimaryKeySelective(SrmFundSchemeDetail record);

    int updateByPrimaryKey(SrmFundSchemeDetail record);
}