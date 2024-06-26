package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubPatientIe;
import com.pinde.sci.model.mo.PubPatientIeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubPatientIeMapper {
    int countByExample(PubPatientIeExample example);

    int deleteByExample(PubPatientIeExample example);

    int deleteByPrimaryKey(String patientFlow);

    int insert(PubPatientIe record);

    int insertSelective(PubPatientIe record);

    List<PubPatientIe> selectByExampleWithBLOBs(PubPatientIeExample example);

    List<PubPatientIe> selectByExample(PubPatientIeExample example);

    PubPatientIe selectByPrimaryKey(String patientFlow);

    int updateByExampleSelective(@Param("record") PubPatientIe record, @Param("example") PubPatientIeExample example);

    int updateByExampleWithBLOBs(@Param("record") PubPatientIe record, @Param("example") PubPatientIeExample example);

    int updateByExample(@Param("record") PubPatientIe record, @Param("example") PubPatientIeExample example);

    int updateByPrimaryKeySelective(PubPatientIe record);

    int updateByPrimaryKeyWithBLOBs(PubPatientIe record);

    int updateByPrimaryKey(PubPatientIe record);
}