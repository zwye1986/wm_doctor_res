package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.TeacherThesisDetail;
import com.pinde.sci.model.mo.TeacherThesisDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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