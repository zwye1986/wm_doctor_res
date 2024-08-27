package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.StudyCourseDetail;
import com.pinde.sci.model.mo.StudyCourseDetailExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyCourseDetailMapper {
    int countByExample(StudyCourseDetailExample example);

    int deleteByExample(StudyCourseDetailExample example);

    int deleteByPrimaryKey(String detailFlow);

    int insert(StudyCourseDetail record);

    int insertSelective(StudyCourseDetail record);

    List<StudyCourseDetail> selectByExample(StudyCourseDetailExample example);

    StudyCourseDetail selectByPrimaryKey(String detailFlow);

    int updateByExampleSelective(@Param("record") StudyCourseDetail record, @Param("example") StudyCourseDetailExample example);

    int updateByExample(@Param("record") StudyCourseDetail record, @Param("example") StudyCourseDetailExample example);

    int updateByPrimaryKeySelective(StudyCourseDetail record);

    int updateByPrimaryKey(StudyCourseDetail record);
}