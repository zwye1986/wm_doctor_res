package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubPatientVisit;
import com.pinde.sci.model.mo.PubPatientVisitExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubPatientVisitMapper {
    int countByExample(PubPatientVisitExample example);

    int deleteByExample(PubPatientVisitExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubPatientVisit record);

    int insertSelective(PubPatientVisit record);

    List<PubPatientVisit> selectByExampleWithBLOBs(PubPatientVisitExample example);

    List<PubPatientVisit> selectByExample(PubPatientVisitExample example);

    PubPatientVisit selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubPatientVisit record, @Param("example") PubPatientVisitExample example);

    int updateByExampleWithBLOBs(@Param("record") PubPatientVisit record, @Param("example") PubPatientVisitExample example);

    int updateByExample(@Param("record") PubPatientVisit record, @Param("example") PubPatientVisitExample example);

    int updateByPrimaryKeySelective(PubPatientVisit record);

    int updateByPrimaryKeyWithBLOBs(PubPatientVisit record);

    int updateByPrimaryKey(PubPatientVisit record);
}