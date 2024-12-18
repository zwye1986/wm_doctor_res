package com.pinde.sci.dao.base;

import com.pinde.core.model.LectureInfoTarget;
import com.pinde.core.model.LectureInfoTargetExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LectureInfoTargetMapper {
    int countByExample(LectureInfoTargetExample example);

    int deleteByExample(LectureInfoTargetExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LectureInfoTarget record);

    int insertSelective(LectureInfoTarget record);

    List<LectureInfoTarget> selectByExample(LectureInfoTargetExample example);

    LectureInfoTarget selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LectureInfoTarget record, @Param("example") LectureInfoTargetExample example);

    int updateByExample(@Param("record") LectureInfoTarget record, @Param("example") LectureInfoTargetExample example);

    int updateByPrimaryKeySelective(LectureInfoTarget record);

    int updateByPrimaryKey(LectureInfoTarget record);
}