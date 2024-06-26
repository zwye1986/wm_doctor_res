package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcVisit;
import com.pinde.sci.model.mo.EdcVisitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcVisitMapper {
    int countByExample(EdcVisitExample example);

    int deleteByExample(EdcVisitExample example);

    int deleteByPrimaryKey(String visitFlow);

    int insert(EdcVisit record);

    int insertSelective(EdcVisit record);

    List<EdcVisit> selectByExample(EdcVisitExample example);

    EdcVisit selectByPrimaryKey(String visitFlow);

    int updateByExampleSelective(@Param("record") EdcVisit record, @Param("example") EdcVisitExample example);

    int updateByExample(@Param("record") EdcVisit record, @Param("example") EdcVisitExample example);

    int updateByPrimaryKeySelective(EdcVisit record);

    int updateByPrimaryKey(EdcVisit record);
}