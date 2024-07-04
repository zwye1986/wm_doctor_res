package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCoursePeriodRef;
import com.pinde.sci.model.mo.EduCoursePeriodRefExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCoursePeriodRefMapper {
    int countByExample(EduCoursePeriodRefExample example);

    int deleteByExample(EduCoursePeriodRefExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCoursePeriodRef record);

    int insertSelective(EduCoursePeriodRef record);

    List<EduCoursePeriodRef> selectByExample(EduCoursePeriodRefExample example);

    EduCoursePeriodRef selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCoursePeriodRef record, @Param("example") EduCoursePeriodRefExample example);

    int updateByExample(@Param("record") EduCoursePeriodRef record, @Param("example") EduCoursePeriodRefExample example);

    int updateByPrimaryKeySelective(EduCoursePeriodRef record);

    int updateByPrimaryKey(EduCoursePeriodRef record);
}