package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.DoctorData;
import com.pinde.sci.model.mo.DoctorDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorDataMapper {
    int countByExample(DoctorDataExample example);

    int deleteByExample(DoctorDataExample example);

    int insert(DoctorData record);

    int insertSelective(DoctorData record);

    List<DoctorData> selectByExample(DoctorDataExample example);

    int updateByExampleSelective(@Param("record") DoctorData record, @Param("example") DoctorDataExample example);

    int updateByExample(@Param("record") DoctorData record, @Param("example") DoctorDataExample example);
}