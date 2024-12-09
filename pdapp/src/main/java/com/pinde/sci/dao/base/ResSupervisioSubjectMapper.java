package com.pinde.sci.dao.base;

import com.pinde.core.model.ResSupervisioSubject;
import com.pinde.core.model.ResSupervisioSubjectExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResSupervisioSubjectMapper {
    int countByExample(ResSupervisioSubjectExample example);

    int deleteByExample(ResSupervisioSubjectExample example);

    int deleteByPrimaryKey(String subjectFlow);

    int insert(ResSupervisioSubject record);

    int insertSelective(ResSupervisioSubject record);

    List<ResSupervisioSubject> selectByExample(ResSupervisioSubjectExample example);

    ResSupervisioSubject selectByPrimaryKey(String subjectFlow);

    int updateByExampleSelective(@Param("record") ResSupervisioSubject record, @Param("example") ResSupervisioSubjectExample example);

    int updateByExample(@Param("record") ResSupervisioSubject record, @Param("example") ResSupervisioSubjectExample example);

    int updateByPrimaryKeySelective(ResSupervisioSubject record);

    int updateByPrimaryKey(ResSupervisioSubject record);
}