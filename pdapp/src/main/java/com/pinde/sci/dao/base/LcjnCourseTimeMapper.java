package com.pinde.sci.dao.base;

import com.pinde.core.model.LcjnCourseTime;
import com.pinde.core.model.LcjnCourseTimeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LcjnCourseTimeMapper {
    int countByExample(LcjnCourseTimeExample example);

    int deleteByExample(LcjnCourseTimeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnCourseTime record);

    int insertSelective(LcjnCourseTime record);

    List<LcjnCourseTime> selectByExample(LcjnCourseTimeExample example);

    LcjnCourseTime selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnCourseTime record, @Param("example") LcjnCourseTimeExample example);

    int updateByExample(@Param("record") LcjnCourseTime record, @Param("example") LcjnCourseTimeExample example);

    int updateByPrimaryKeySelective(LcjnCourseTime record);

    int updateByPrimaryKey(LcjnCourseTime record);
}