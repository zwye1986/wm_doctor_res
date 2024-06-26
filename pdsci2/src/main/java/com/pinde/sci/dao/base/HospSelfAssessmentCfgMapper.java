package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.HospSelfAssessmentCfg;
import com.pinde.sci.model.mo.HospSelfAssessmentCfgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HospSelfAssessmentCfgMapper {
    int countByExample(HospSelfAssessmentCfgExample example);

    int deleteByExample(HospSelfAssessmentCfgExample example);

    int deleteByPrimaryKey(String cfgFlow);

    int insert(HospSelfAssessmentCfg record);

    int insertSelective(HospSelfAssessmentCfg record);

    List<HospSelfAssessmentCfg> selectByExample(HospSelfAssessmentCfgExample example);

    HospSelfAssessmentCfg selectByPrimaryKey(String cfgFlow);

    int updateByExampleSelective(@Param("record") HospSelfAssessmentCfg record, @Param("example") HospSelfAssessmentCfgExample example);

    int updateByExample(@Param("record") HospSelfAssessmentCfg record, @Param("example") HospSelfAssessmentCfgExample example);

    int updateByPrimaryKeySelective(HospSelfAssessmentCfg record);

    int updateByPrimaryKey(HospSelfAssessmentCfg record);
}