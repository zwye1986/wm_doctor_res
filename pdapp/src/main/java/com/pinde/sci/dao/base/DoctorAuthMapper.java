package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.DoctorAuth;
import com.pinde.sci.model.mo.DoctorAuthExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DoctorAuthMapper {
    int countByExample(DoctorAuthExample example);

    int deleteByExample(DoctorAuthExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(DoctorAuth record);

    int insertSelective(DoctorAuth record);

    List<DoctorAuth> selectByExample(DoctorAuthExample example);

    DoctorAuth selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") DoctorAuth record, @Param("example") DoctorAuthExample example);

    int updateByExample(@Param("record") DoctorAuth record, @Param("example") DoctorAuthExample example);

    int updateByPrimaryKeySelective(DoctorAuth record);

    int updateByPrimaryKey(DoctorAuth record);
}