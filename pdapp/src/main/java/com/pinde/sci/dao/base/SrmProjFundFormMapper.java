package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmProjFundForm;
import com.pinde.sci.model.mo.SrmProjFundFormExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmProjFundFormMapper {
    int countByExample(SrmProjFundFormExample example);

    int deleteByExample(SrmProjFundFormExample example);

    int deleteByPrimaryKey(String fundFormFlow);

    int insert(SrmProjFundForm record);

    int insertSelective(SrmProjFundForm record);

    List<SrmProjFundForm> selectByExample(SrmProjFundFormExample example);

    SrmProjFundForm selectByPrimaryKey(String fundFormFlow);

    int updateByExampleSelective(@Param("record") SrmProjFundForm record, @Param("example") SrmProjFundFormExample example);

    int updateByExample(@Param("record") SrmProjFundForm record, @Param("example") SrmProjFundFormExample example);

    int updateByPrimaryKeySelective(SrmProjFundForm record);

    int updateByPrimaryKey(SrmProjFundForm record);
}