package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertEvalResult;
import com.pinde.sci.model.mo.ExpertEvalResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExpertEvalResultMapper {
    int countByExample(ExpertEvalResultExample example);

    int deleteByExample(ExpertEvalResultExample example);

    int deleteByPrimaryKey(String resultFlow);

    int insert(ExpertEvalResult record);

    int insertSelective(ExpertEvalResult record);

    List<ExpertEvalResult> selectByExampleWithBLOBs(ExpertEvalResultExample example);

    List<ExpertEvalResult> selectByExample(ExpertEvalResultExample example);

    ExpertEvalResult selectByPrimaryKey(String resultFlow);

    int updateByExampleSelective(@Param("record") ExpertEvalResult record, @Param("example") ExpertEvalResultExample example);

    int updateByExampleWithBLOBs(@Param("record") ExpertEvalResult record, @Param("example") ExpertEvalResultExample example);

    int updateByExample(@Param("record") ExpertEvalResult record, @Param("example") ExpertEvalResultExample example);

    int updateByPrimaryKeySelective(ExpertEvalResult record);

    int updateByPrimaryKeyWithBLOBs(ExpertEvalResult record);

    int updateByPrimaryKey(ExpertEvalResult record);
}