package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmApplyLimit;
import com.pinde.sci.model.mo.SrmApplyLimitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmApplyLimitMapper {
    int countByExample(SrmApplyLimitExample example);

    int deleteByExample(SrmApplyLimitExample example);

    int deleteByPrimaryKey(String limitFlow);

    int insert(SrmApplyLimit record);

    int insertSelective(SrmApplyLimit record);

    List<SrmApplyLimit> selectByExample(SrmApplyLimitExample example);

    SrmApplyLimit selectByPrimaryKey(String limitFlow);

    int updateByExampleSelective(@Param("record") SrmApplyLimit record, @Param("example") SrmApplyLimitExample example);

    int updateByExample(@Param("record") SrmApplyLimit record, @Param("example") SrmApplyLimitExample example);

    int updateByPrimaryKeySelective(SrmApplyLimit record);

    int updateByPrimaryKey(SrmApplyLimit record);
}