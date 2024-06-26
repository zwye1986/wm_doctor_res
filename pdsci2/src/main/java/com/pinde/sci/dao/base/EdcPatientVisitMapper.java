package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcPatientVisit;
import com.pinde.sci.model.mo.EdcPatientVisitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcPatientVisitMapper {
    int countByExample(EdcPatientVisitExample example);

    int deleteByExample(EdcPatientVisitExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcPatientVisit record);

    int insertSelective(EdcPatientVisit record);

    List<EdcPatientVisit> selectByExample(EdcPatientVisitExample example);

    EdcPatientVisit selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcPatientVisit record, @Param("example") EdcPatientVisitExample example);

    int updateByExample(@Param("record") EdcPatientVisit record, @Param("example") EdcPatientVisitExample example);

    int updateByPrimaryKeySelective(EdcPatientVisit record);

    int updateByPrimaryKey(EdcPatientVisit record);
}