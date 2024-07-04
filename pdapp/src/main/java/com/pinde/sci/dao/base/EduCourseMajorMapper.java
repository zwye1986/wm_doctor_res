package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduCourseMajor;
import com.pinde.sci.model.mo.EduCourseMajorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduCourseMajorMapper {
    int countByExample(EduCourseMajorExample example);

    int deleteByExample(EduCourseMajorExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduCourseMajor record);

    int insertSelective(EduCourseMajor record);

    List<EduCourseMajor> selectByExample(EduCourseMajorExample example);

    EduCourseMajor selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduCourseMajor record, @Param("example") EduCourseMajorExample example);

    int updateByExample(@Param("record") EduCourseMajor record, @Param("example") EduCourseMajorExample example);

    int updateByPrimaryKeySelective(EduCourseMajor record);

    int updateByPrimaryKey(EduCourseMajor record);
}