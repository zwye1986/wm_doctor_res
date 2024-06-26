package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmExpertProjEval;
import com.pinde.sci.model.mo.SrmExpertProjEvalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmExpertProjEvalMapper {
    int countByExample(SrmExpertProjEvalExample example);

    int deleteByExample(SrmExpertProjEvalExample example);

    int deleteByPrimaryKey(String evalSetFlow);

    int insert(SrmExpertProjEval record);

    int insertSelective(SrmExpertProjEval record);

    List<SrmExpertProjEval> selectByExample(SrmExpertProjEvalExample example);

    SrmExpertProjEval selectByPrimaryKey(String evalSetFlow);

    int updateByExampleSelective(@Param("record") SrmExpertProjEval record, @Param("example") SrmExpertProjEvalExample example);

    int updateByExample(@Param("record") SrmExpertProjEval record, @Param("example") SrmExpertProjEvalExample example);

    int updateByPrimaryKeySelective(SrmExpertProjEval record);

    int updateByPrimaryKey(SrmExpertProjEval record);
}