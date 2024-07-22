package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertEvalOrgSpe;
import com.pinde.sci.model.mo.ExpertEvalOrgSpeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpertEvalOrgSpeMapper {
    int countByExample(ExpertEvalOrgSpeExample example);

    int deleteByExample(ExpertEvalOrgSpeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ExpertEvalOrgSpe record);

    int insertSelective(ExpertEvalOrgSpe record);

    List<ExpertEvalOrgSpe> selectByExample(ExpertEvalOrgSpeExample example);

    ExpertEvalOrgSpe selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ExpertEvalOrgSpe record, @Param("example") ExpertEvalOrgSpeExample example);

    int updateByExample(@Param("record") ExpertEvalOrgSpe record, @Param("example") ExpertEvalOrgSpeExample example);

    int updateByPrimaryKeySelective(ExpertEvalOrgSpe record);

    int updateByPrimaryKey(ExpertEvalOrgSpe record);
}