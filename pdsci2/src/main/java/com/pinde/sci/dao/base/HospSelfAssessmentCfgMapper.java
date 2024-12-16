package com.pinde.sci.dao.base;

import com.pinde.core.model.HospSelfAssessmentCfg;
import com.pinde.core.model.HospSelfAssessmentCfgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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