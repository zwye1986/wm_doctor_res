package com.pinde.sci.dao.base;

import com.pinde.core.model.LcjnTeaEvaluateDetail;
import com.pinde.core.model.LcjnTeaEvaluateDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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