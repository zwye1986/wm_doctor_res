package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.EdcPatientVisitData;
import com.pinde.sci.model.mo.EdcPatientVisitDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EdcPatientVisitDataMapper {
    int countByExample(EdcPatientVisitDataExample example);

    int deleteByExample(EdcPatientVisitDataExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(EdcPatientVisitData record);

    int insertSelective(EdcPatientVisitData record);

    List<EdcPatientVisitData> selectByExample(EdcPatientVisitDataExample example);

    EdcPatientVisitData selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") EdcPatientVisitData record, @Param("example") EdcPatientVisitDataExample example);

    int updateByExample(@Param("record") EdcPatientVisitData record, @Param("example") EdcPatientVisitDataExample example);

    int updateByPrimaryKeySelective(EdcPatientVisitData record);

    int updateByPrimaryKey(EdcPatientVisitData record);
}