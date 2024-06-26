package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubPatientAe;
import com.pinde.sci.model.mo.PubPatientAeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubPatientAeMapper {
    int countByExample(PubPatientAeExample example);

    int deleteByExample(PubPatientAeExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubPatientAe record);

    int insertSelective(PubPatientAe record);

    List<PubPatientAe> selectByExampleWithBLOBs(PubPatientAeExample example);

    List<PubPatientAe> selectByExample(PubPatientAeExample example);

    PubPatientAe selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubPatientAe record, @Param("example") PubPatientAeExample example);

    int updateByExampleWithBLOBs(@Param("record") PubPatientAe record, @Param("example") PubPatientAeExample example);

    int updateByExample(@Param("record") PubPatientAe record, @Param("example") PubPatientAeExample example);

    int updateByPrimaryKeySelective(PubPatientAe record);

    int updateByPrimaryKeyWithBLOBs(PubPatientAe record);

    int updateByPrimaryKey(PubPatientAe record);
}