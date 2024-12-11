package com.pinde.sci.dao.base;

import com.pinde.core.model.DoctorAuth;
import com.pinde.core.model.DoctorAuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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