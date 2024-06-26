package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResEvaluationScore;
import com.pinde.sci.model.mo.ResEvaluationScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResEvaluationScoreMapper {
    int countByExample(ResEvaluationScoreExample example);

    int deleteByExample(ResEvaluationScoreExample example);

    int deleteByPrimaryKey(String scoreFlow);

    int insert(ResEvaluationScore record);

    int insertSelective(ResEvaluationScore record);

    List<ResEvaluationScore> selectByExample(ResEvaluationScoreExample example);

    ResEvaluationScore selectByPrimaryKey(String scoreFlow);

    int updateByExampleSelective(@Param("record") ResEvaluationScore record, @Param("example") ResEvaluationScoreExample example);

    int updateByExample(@Param("record") ResEvaluationScore record, @Param("example") ResEvaluationScoreExample example);

    int updateByPrimaryKeySelective(ResEvaluationScore record);

    int updateByPrimaryKey(ResEvaluationScore record);
}