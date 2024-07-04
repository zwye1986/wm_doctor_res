package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResTrainingOpinion;
import com.pinde.sci.model.mo.ResTrainingOpinionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResTrainingOpinionMapper {
    int countByExample(ResTrainingOpinionExample example);

    int deleteByExample(ResTrainingOpinionExample example);

    int deleteByPrimaryKey(String trainingOpinionFlow);

    int insert(ResTrainingOpinion record);

    int insertSelective(ResTrainingOpinion record);

    List<ResTrainingOpinion> selectByExample(ResTrainingOpinionExample example);

    ResTrainingOpinion selectByPrimaryKey(String trainingOpinionFlow);

    int updateByExampleSelective(@Param("record") ResTrainingOpinion record, @Param("example") ResTrainingOpinionExample example);

    int updateByExample(@Param("record") ResTrainingOpinion record, @Param("example") ResTrainingOpinionExample example);

    int updateByPrimaryKeySelective(ResTrainingOpinion record);

    int updateByPrimaryKey(ResTrainingOpinion record);
}