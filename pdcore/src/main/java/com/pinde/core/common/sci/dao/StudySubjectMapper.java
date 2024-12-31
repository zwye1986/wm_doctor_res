package com.pinde.core.common.sci.dao;

import com.pinde.core.model.StudySubject;
import com.pinde.core.model.StudySubjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudySubjectMapper {
    int countByExample(StudySubjectExample example);

    int deleteByExample(StudySubjectExample example);

    int deleteByPrimaryKey(String subjectFlow);

    int insert(StudySubject record);

    int insertSelective(StudySubject record);

    List<StudySubject> selectByExample(StudySubjectExample example);

    StudySubject selectByPrimaryKey(String subjectFlow);

    int updateByExampleSelective(@Param("record") StudySubject record, @Param("example") StudySubjectExample example);

    int updateByExample(@Param("record") StudySubject record, @Param("example") StudySubjectExample example);

    int updateByPrimaryKeySelective(StudySubject record);

    int updateByPrimaryKey(StudySubject record);
}