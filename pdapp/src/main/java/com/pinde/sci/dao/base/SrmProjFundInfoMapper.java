package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmProjFundInfo;
import com.pinde.sci.model.mo.SrmProjFundInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmProjFundInfoMapper {
    int countByExample(SrmProjFundInfoExample example);

    int deleteByExample(SrmProjFundInfoExample example);

    int deleteByPrimaryKey(String fundFlow);

    int insert(SrmProjFundInfo record);

    int insertSelective(SrmProjFundInfo record);

    List<SrmProjFundInfo> selectByExample(SrmProjFundInfoExample example);

    SrmProjFundInfo selectByPrimaryKey(String fundFlow);

    int updateByExampleSelective(@Param("record") SrmProjFundInfo record, @Param("example") SrmProjFundInfoExample example);

    int updateByExample(@Param("record") SrmProjFundInfo record, @Param("example") SrmProjFundInfoExample example);

    int updateByPrimaryKeySelective(SrmProjFundInfo record);

    int updateByPrimaryKey(SrmProjFundInfo record);
}