package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubPatient;
import com.pinde.sci.model.mo.PubPatientExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubPatientMapper {
    int countByExample(PubPatientExample example);

    int deleteByExample(PubPatientExample example);

    int deleteByPrimaryKey(String patientFlow);

    int insert(PubPatient record);

    int insertSelective(PubPatient record);

    List<PubPatient> selectByExample(PubPatientExample example);

    PubPatient selectByPrimaryKey(String patientFlow);

    int updateByExampleSelective(@Param("record") PubPatient record, @Param("example") PubPatientExample example);

    int updateByExample(@Param("record") PubPatient record, @Param("example") PubPatientExample example);

    int updateByPrimaryKeySelective(PubPatient record);

    int updateByPrimaryKey(PubPatient record);
}