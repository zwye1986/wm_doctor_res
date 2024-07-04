package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EduScheduleLimit;
import com.pinde.sci.model.mo.EduScheduleLimitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EduScheduleLimitMapper {
    int countByExample(EduScheduleLimitExample example);

    int deleteByExample(EduScheduleLimitExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EduScheduleLimit record);

    int insertSelective(EduScheduleLimit record);

    List<EduScheduleLimit> selectByExample(EduScheduleLimitExample example);

    EduScheduleLimit selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EduScheduleLimit record, @Param("example") EduScheduleLimitExample example);

    int updateByExample(@Param("record") EduScheduleLimit record, @Param("example") EduScheduleLimitExample example);

    int updateByPrimaryKeySelective(EduScheduleLimit record);

    int updateByPrimaryKey(EduScheduleLimit record);
}