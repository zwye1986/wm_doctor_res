package com.pinde.sci.dao.base;

import com.pinde.core.model.LcjnTeaEvaluate;
import com.pinde.core.model.LcjnTeaEvaluateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LcjnTeaEvaluateMapper {
    int countByExample(LcjnTeaEvaluateExample example);

    int deleteByExample(LcjnTeaEvaluateExample example);

    int deleteByPrimaryKey(String teaEvaluateFlow);

    int insert(LcjnTeaEvaluate record);

    int insertSelective(LcjnTeaEvaluate record);

    List<LcjnTeaEvaluate> selectByExample(LcjnTeaEvaluateExample example);

    LcjnTeaEvaluate selectByPrimaryKey(String teaEvaluateFlow);

    int updateByExampleSelective(@Param("record") LcjnTeaEvaluate record, @Param("example") LcjnTeaEvaluateExample example);

    int updateByExample(@Param("record") LcjnTeaEvaluate record, @Param("example") LcjnTeaEvaluateExample example);

    int updateByPrimaryKeySelective(LcjnTeaEvaluate record);

    int updateByPrimaryKey(LcjnTeaEvaluate record);
}