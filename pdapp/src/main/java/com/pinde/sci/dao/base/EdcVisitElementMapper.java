package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcVisitElement;
import com.pinde.sci.model.mo.EdcVisitElementExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcVisitElementMapper {
    int countByExample(EdcVisitElementExample example);

    int deleteByExample(EdcVisitElementExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcVisitElement record);

    int insertSelective(EdcVisitElement record);

    List<EdcVisitElement> selectByExample(EdcVisitElementExample example);

    EdcVisitElement selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcVisitElement record, @Param("example") EdcVisitElementExample example);

    int updateByExample(@Param("record") EdcVisitElement record, @Param("example") EdcVisitElementExample example);

    int updateByPrimaryKeySelective(EdcVisitElement record);

    int updateByPrimaryKey(EdcVisitElement record);
}