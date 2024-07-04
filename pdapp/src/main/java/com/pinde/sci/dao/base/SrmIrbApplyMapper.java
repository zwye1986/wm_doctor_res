package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmIrbApply;
import com.pinde.sci.model.mo.SrmIrbApplyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmIrbApplyMapper {
    int countByExample(SrmIrbApplyExample example);

    int deleteByExample(SrmIrbApplyExample example);

    int deleteByPrimaryKey(String irbFlow);

    int insert(SrmIrbApply record);

    int insertSelective(SrmIrbApply record);

    List<SrmIrbApply> selectByExample(SrmIrbApplyExample example);

    SrmIrbApply selectByPrimaryKey(String irbFlow);

    int updateByExampleSelective(@Param("record") SrmIrbApply record, @Param("example") SrmIrbApplyExample example);

    int updateByExample(@Param("record") SrmIrbApply record, @Param("example") SrmIrbApplyExample example);

    int updateByPrimaryKeySelective(SrmIrbApply record);

    int updateByPrimaryKey(SrmIrbApply record);
}