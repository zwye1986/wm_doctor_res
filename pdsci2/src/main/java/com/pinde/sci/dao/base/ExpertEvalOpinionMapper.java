package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertEvalOpinion;
import com.pinde.sci.model.mo.ExpertEvalOpinionExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpertEvalOpinionMapper {
    int countByExample(ExpertEvalOpinionExample example);

    int deleteByExample(ExpertEvalOpinionExample example);

    int deleteByPrimaryKey(String opinionFlow);

    int insert(ExpertEvalOpinion record);

    int insertSelective(ExpertEvalOpinion record);

    List<ExpertEvalOpinion> selectByExample(ExpertEvalOpinionExample example);

    ExpertEvalOpinion selectByPrimaryKey(String opinionFlow);

    int updateByExampleSelective(@Param("record") ExpertEvalOpinion record, @Param("example") ExpertEvalOpinionExample example);

    int updateByExample(@Param("record") ExpertEvalOpinion record, @Param("example") ExpertEvalOpinionExample example);

    int updateByPrimaryKeySelective(ExpertEvalOpinion record);

    int updateByPrimaryKey(ExpertEvalOpinion record);
}