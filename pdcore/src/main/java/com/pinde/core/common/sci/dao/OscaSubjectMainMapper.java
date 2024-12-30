package com.pinde.core.common.sci.dao;

import com.pinde.core.model.OscaSubjectMain;
import com.pinde.core.model.OscaSubjectMainExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OscaSubjectMainMapper {
    int countByExample(OscaSubjectMainExample example);

    int deleteByExample(OscaSubjectMainExample example);

    int deleteByPrimaryKey(String subjectFlow);

    int insert(OscaSubjectMain record);

    int insertSelective(OscaSubjectMain record);

    List<OscaSubjectMain> selectByExample(OscaSubjectMainExample example);

    OscaSubjectMain selectByPrimaryKey(String subjectFlow);

    int updateByExampleSelective(@Param("record") OscaSubjectMain record, @Param("example") OscaSubjectMainExample example);

    int updateByExample(@Param("record") OscaSubjectMain record, @Param("example") OscaSubjectMainExample example);

    int updateByPrimaryKeySelective(OscaSubjectMain record);

    int updateByPrimaryKey(OscaSubjectMain record);
}