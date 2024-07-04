package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmFundScheme;
import com.pinde.sci.model.mo.SrmFundSchemeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmFundSchemeMapper {
    int countByExample(SrmFundSchemeExample example);

    int deleteByExample(SrmFundSchemeExample example);

    int deleteByPrimaryKey(String schemeFlow);

    int insert(SrmFundScheme record);

    int insertSelective(SrmFundScheme record);

    List<SrmFundScheme> selectByExample(SrmFundSchemeExample example);

    SrmFundScheme selectByPrimaryKey(String schemeFlow);

    int updateByExampleSelective(@Param("record") SrmFundScheme record, @Param("example") SrmFundSchemeExample example);

    int updateByExample(@Param("record") SrmFundScheme record, @Param("example") SrmFundSchemeExample example);

    int updateByPrimaryKeySelective(SrmFundScheme record);

    int updateByPrimaryKey(SrmFundScheme record);
}