package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmExpert;
import com.pinde.sci.model.mo.SrmExpertExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmExpertMapper {
    int countByExample(SrmExpertExample example);

    int deleteByExample(SrmExpertExample example);

    int deleteByPrimaryKey(String userFlow);

    int insert(SrmExpert record);

    int insertSelective(SrmExpert record);

    List<SrmExpert> selectByExample(SrmExpertExample example);

    SrmExpert selectByPrimaryKey(String userFlow);

    int updateByExampleSelective(@Param("record") SrmExpert record, @Param("example") SrmExpertExample example);

    int updateByExample(@Param("record") SrmExpert record, @Param("example") SrmExpertExample example);

    int updateByPrimaryKeySelective(SrmExpert record);

    int updateByPrimaryKey(SrmExpert record);
}