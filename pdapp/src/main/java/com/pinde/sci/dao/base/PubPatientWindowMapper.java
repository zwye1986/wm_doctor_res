package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.PubPatientWindow;
import com.pinde.sci.model.mo.PubPatientWindowExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PubPatientWindowMapper {
    int countByExample(PubPatientWindowExample example);

    int deleteByExample(PubPatientWindowExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(PubPatientWindow record);

    int insertSelective(PubPatientWindow record);

    List<PubPatientWindow> selectByExample(PubPatientWindowExample example);

    PubPatientWindow selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") PubPatientWindow record, @Param("example") PubPatientWindowExample example);

    int updateByExample(@Param("record") PubPatientWindow record, @Param("example") PubPatientWindowExample example);

    int updateByPrimaryKeySelective(PubPatientWindow record);

    int updateByPrimaryKey(PubPatientWindow record);
}