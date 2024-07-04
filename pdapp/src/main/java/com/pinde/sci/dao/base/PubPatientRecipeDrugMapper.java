package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubPatientRecipeDrug;
import com.pinde.sci.model.mo.PubPatientRecipeDrugExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubPatientRecipeDrugMapper {
    int countByExample(PubPatientRecipeDrugExample example);

    int deleteByExample(PubPatientRecipeDrugExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubPatientRecipeDrug record);

    int insertSelective(PubPatientRecipeDrug record);

    List<PubPatientRecipeDrug> selectByExample(PubPatientRecipeDrugExample example);

    PubPatientRecipeDrug selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubPatientRecipeDrug record, @Param("example") PubPatientRecipeDrugExample example);

    int updateByExample(@Param("record") PubPatientRecipeDrug record, @Param("example") PubPatientRecipeDrugExample example);

    int updateByPrimaryKeySelective(PubPatientRecipeDrug record);

    int updateByPrimaryKey(PubPatientRecipeDrug record);
}