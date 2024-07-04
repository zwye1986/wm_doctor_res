package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcVisitDataEvent;
import com.pinde.sci.model.mo.EdcVisitDataEventExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcVisitDataEventMapper {
    int countByExample(EdcVisitDataEventExample example);

    int deleteByExample(EdcVisitDataEventExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcVisitDataEvent record);

    int insertSelective(EdcVisitDataEvent record);

    List<EdcVisitDataEvent> selectByExample(EdcVisitDataEventExample example);

    EdcVisitDataEvent selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcVisitDataEvent record, @Param("example") EdcVisitDataEventExample example);

    int updateByExample(@Param("record") EdcVisitDataEvent record, @Param("example") EdcVisitDataEventExample example);

    int updateByPrimaryKeySelective(EdcVisitDataEvent record);

    int updateByPrimaryKey(EdcVisitDataEvent record);
}