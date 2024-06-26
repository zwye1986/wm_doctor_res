package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.SrmAchAppraisal;
import com.pinde.sci.model.mo.SrmAchAppraisalExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SrmAchAppraisalMapper {
    int countByExample(SrmAchAppraisalExample example);

    int deleteByExample(SrmAchAppraisalExample example);

    int deleteByPrimaryKey(String appraisalFlow);

    int insert(SrmAchAppraisal record);

    int insertSelective(SrmAchAppraisal record);

    List<SrmAchAppraisal> selectByExample(SrmAchAppraisalExample example);

    SrmAchAppraisal selectByPrimaryKey(String appraisalFlow);

    int updateByExampleSelective(@Param("record") SrmAchAppraisal record, @Param("example") SrmAchAppraisalExample example);

    int updateByExample(@Param("record") SrmAchAppraisal record, @Param("example") SrmAchAppraisalExample example);

    int updateByPrimaryKeySelective(SrmAchAppraisal record);

    int updateByPrimaryKey(SrmAchAppraisal record);
}