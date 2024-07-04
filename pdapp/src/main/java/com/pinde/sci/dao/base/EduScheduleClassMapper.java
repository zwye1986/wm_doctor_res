package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduScheduleClass;
import com.pinde.sci.model.mo.EduScheduleClassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduScheduleClassMapper {
    int countByExample(EduScheduleClassExample example);

    int deleteByExample(EduScheduleClassExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduScheduleClass record);

    int insertSelective(EduScheduleClass record);

    List<EduScheduleClass> selectByExample(EduScheduleClassExample example);

    EduScheduleClass selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduScheduleClass record, @Param("example") EduScheduleClassExample example);

    int updateByExample(@Param("record") EduScheduleClass record, @Param("example") EduScheduleClassExample example);

    int updateByPrimaryKeySelective(EduScheduleClass record);

    int updateByPrimaryKey(EduScheduleClass record);
}