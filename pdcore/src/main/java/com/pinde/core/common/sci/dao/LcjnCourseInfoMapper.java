package com.pinde.core.common.sci.dao;

import com.pinde.core.model.LcjnCourseInfo;
import com.pinde.core.model.LcjnCourseInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnCourseInfoMapper {
    int countByExample(LcjnCourseInfoExample example);

    int deleteByExample(LcjnCourseInfoExample example);

    int deleteByPrimaryKey(String courseFlow);

    int insert(LcjnCourseInfo record);

    int insertSelective(LcjnCourseInfo record);

    List<LcjnCourseInfo> selectByExample(LcjnCourseInfoExample example);

    LcjnCourseInfo selectByPrimaryKey(String courseFlow);

    int updateByExampleSelective(@Param("record") LcjnCourseInfo record, @Param("example") LcjnCourseInfoExample example);

    int updateByExample(@Param("record") LcjnCourseInfo record, @Param("example") LcjnCourseInfoExample example);

    int updateByPrimaryKeySelective(LcjnCourseInfo record);

    int updateByPrimaryKey(LcjnCourseInfo record);
}