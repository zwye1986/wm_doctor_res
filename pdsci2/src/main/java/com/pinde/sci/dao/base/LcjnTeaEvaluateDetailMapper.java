package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.LcjnTeaEvaluateDetail;
import com.pinde.sci.model.mo.LcjnTeaEvaluateDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnTeaEvaluateDetailMapper {
    int countByExample(LcjnTeaEvaluateDetailExample example);

    int deleteByExample(LcjnTeaEvaluateDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnTeaEvaluateDetail record);

    int insertSelective(LcjnTeaEvaluateDetail record);

    List<LcjnTeaEvaluateDetail> selectByExample(LcjnTeaEvaluateDetailExample example);

    LcjnTeaEvaluateDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnTeaEvaluateDetail record, @Param("example") LcjnTeaEvaluateDetailExample example);

    int updateByExample(@Param("record") LcjnTeaEvaluateDetail record, @Param("example") LcjnTeaEvaluateDetailExample example);

    int updateByPrimaryKeySelective(LcjnTeaEvaluateDetail record);

    int updateByPrimaryKey(LcjnTeaEvaluateDetail record);
}