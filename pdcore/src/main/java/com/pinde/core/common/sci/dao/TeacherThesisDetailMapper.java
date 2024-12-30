package com.pinde.core.common.sci.dao;

import com.pinde.core.model.TeacherThesisDetail;
import com.pinde.core.model.TeacherThesisDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherThesisDetailMapper {
    int countByExample(TeacherThesisDetailExample example);

    int deleteByExample(TeacherThesisDetailExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(TeacherThesisDetail record);

    int insertSelective(TeacherThesisDetail record);

    List<TeacherThesisDetail> selectByExample(TeacherThesisDetailExample example);

    TeacherThesisDetail selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") TeacherThesisDetail record, @Param("example") TeacherThesisDetailExample example);

    int updateByExample(@Param("record") TeacherThesisDetail record, @Param("example") TeacherThesisDetailExample example);

    int updateByPrimaryKeySelective(TeacherThesisDetail record);

    int updateByPrimaryKey(TeacherThesisDetail record);
}