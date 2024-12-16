package com.pinde.sci.dao.base;

import com.pinde.core.model.DoctorData;
import com.pinde.core.model.DoctorDataExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorDataMapper {
    int countByExample(DoctorDataExample example);

    int deleteByExample(DoctorDataExample example);

    int insert(DoctorData record);

    int insertSelective(DoctorData record);

    List<DoctorData> selectByExample(DoctorDataExample example);

    int updateByExampleSelective(@Param("record") DoctorData record, @Param("example") DoctorDataExample example);

    int updateByExample(@Param("record") DoctorData record, @Param("example") DoctorDataExample example);
}