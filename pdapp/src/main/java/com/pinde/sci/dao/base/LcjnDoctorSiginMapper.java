package com.pinde.sci.dao.base;

import com.pinde.core.model.LcjnDoctorSigin;
import com.pinde.core.model.LcjnDoctorSiginExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LcjnDoctorSiginMapper {
    int countByExample(LcjnDoctorSiginExample example);

    int deleteByExample(LcjnDoctorSiginExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(LcjnDoctorSigin record);

    int insertSelective(LcjnDoctorSigin record);

    List<LcjnDoctorSigin> selectByExample(LcjnDoctorSiginExample example);

    LcjnDoctorSigin selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") LcjnDoctorSigin record, @Param("example") LcjnDoctorSiginExample example);

    int updateByExample(@Param("record") LcjnDoctorSigin record, @Param("example") LcjnDoctorSiginExample example);

    int updateByPrimaryKeySelective(LcjnDoctorSigin record);

    int updateByPrimaryKey(LcjnDoctorSigin record);
}