package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmProjFundDetail;
import com.pinde.sci.model.mo.SrmProjFundDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmProjFundDetailMapper {
    int countByExample(SrmProjFundDetailExample example);

    int deleteByExample(SrmProjFundDetailExample example);

    int deleteByPrimaryKey(String fundDetailFlow);

    int insert(SrmProjFundDetail record);

    int insertSelective(SrmProjFundDetail record);

    List<SrmProjFundDetail> selectByExample(SrmProjFundDetailExample example);

    SrmProjFundDetail selectByPrimaryKey(String fundDetailFlow);

    int updateByExampleSelective(@Param("record") SrmProjFundDetail record, @Param("example") SrmProjFundDetailExample example);

    int updateByExample(@Param("record") SrmProjFundDetail record, @Param("example") SrmProjFundDetailExample example);

    int updateByPrimaryKeySelective(SrmProjFundDetail record);

    int updateByPrimaryKey(SrmProjFundDetail record);
}