package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.StudySubject;
import com.pinde.sci.model.mo.StudySubjectExample;
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