package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EmploymentFeedback;
import com.pinde.sci.model.mo.EmploymentFeedbackExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmploymentFeedbackMapper {
    int countByExample(EmploymentFeedbackExample example);

    int deleteByExample(EmploymentFeedbackExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EmploymentFeedback record);

    int insertSelective(EmploymentFeedback record);

    List<EmploymentFeedback> selectByExample(EmploymentFeedbackExample example);

    EmploymentFeedback selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EmploymentFeedback record, @Param("example") EmploymentFeedbackExample example);

    int updateByExample(@Param("record") EmploymentFeedback record, @Param("example") EmploymentFeedbackExample example);

    int updateByPrimaryKeySelective(EmploymentFeedback record);

    int updateByPrimaryKey(EmploymentFeedback record);
}