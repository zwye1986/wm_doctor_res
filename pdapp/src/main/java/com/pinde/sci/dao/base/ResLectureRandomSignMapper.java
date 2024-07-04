package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResLectureRandomSign;
import com.pinde.sci.model.mo.ResLectureRandomSignExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResLectureRandomSignMapper {
    int countByExample(ResLectureRandomSignExample example);

    int deleteByExample(ResLectureRandomSignExample example);

    int deleteByPrimaryKey(String randomFlow);

    int insert(ResLectureRandomSign record);

    int insertSelective(ResLectureRandomSign record);

    List<ResLectureRandomSign> selectByExample(ResLectureRandomSignExample example);

    ResLectureRandomSign selectByPrimaryKey(String randomFlow);

    int updateByExampleSelective(@Param("record") ResLectureRandomSign record, @Param("example") ResLectureRandomSignExample example);

    int updateByExample(@Param("record") ResLectureRandomSign record, @Param("example") ResLectureRandomSignExample example);

    int updateByPrimaryKeySelective(ResLectureRandomSign record);

    int updateByPrimaryKey(ResLectureRandomSign record);
}