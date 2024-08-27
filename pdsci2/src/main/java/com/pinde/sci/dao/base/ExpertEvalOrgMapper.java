package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ExpertEvalOrg;
import com.pinde.sci.model.mo.ExpertEvalOrgExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExpertEvalOrgMapper {
    int countByExample(ExpertEvalOrgExample example);

    int deleteByExample(ExpertEvalOrgExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ExpertEvalOrg record);

    int insertSelective(ExpertEvalOrg record);

    List<ExpertEvalOrg> selectByExample(ExpertEvalOrgExample example);

    ExpertEvalOrg selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ExpertEvalOrg record, @Param("example") ExpertEvalOrgExample example);

    int updateByExample(@Param("record") ExpertEvalOrg record, @Param("example") ExpertEvalOrgExample example);

    int updateByPrimaryKeySelective(ExpertEvalOrg record);

    int updateByPrimaryKey(ExpertEvalOrg record);
}