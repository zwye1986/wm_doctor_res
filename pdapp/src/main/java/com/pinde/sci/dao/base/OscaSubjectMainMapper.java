package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.OscaSubjectMain;
import com.pinde.sci.model.mo.OscaSubjectMainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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