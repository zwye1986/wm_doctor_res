package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.StudySubjectDetail;
import com.pinde.sci.model.mo.StudySubjectDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudySubjectDetailMapper {
    int countByExample(StudySubjectDetailExample example);

    int deleteByExample(StudySubjectDetailExample example);

    int deleteByPrimaryKey(String detailFlow);

    int insert(StudySubjectDetail record);

    int insertSelective(StudySubjectDetail record);

    List<StudySubjectDetail> selectByExample(StudySubjectDetailExample example);

    StudySubjectDetail selectByPrimaryKey(String detailFlow);

    int updateByExampleSelective(@Param("record") StudySubjectDetail record, @Param("example") StudySubjectDetailExample example);

    int updateByExample(@Param("record") StudySubjectDetail record, @Param("example") StudySubjectDetailExample example);

    int updateByPrimaryKeySelective(StudySubjectDetail record);

    int updateByPrimaryKey(StudySubjectDetail record);
}