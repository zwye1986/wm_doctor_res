package com.pinde.core.common.sci.dao;

import com.pinde.core.model.ResDoctorSignin;
import com.pinde.core.model.ResDoctorSigninExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorSigninMapper {
    int countByExample(ResDoctorSigninExample example);

    int deleteByExample(ResDoctorSigninExample example);

    int deleteByPrimaryKey(String reportFlow);

    int insert(ResDoctorSignin record);

    int insertSelective(ResDoctorSignin record);

    List<ResDoctorSignin> selectByExample(ResDoctorSigninExample example);

    ResDoctorSignin selectByPrimaryKey(String reportFlow);

    int updateByExampleSelective(@Param("record") ResDoctorSignin record, @Param("example") ResDoctorSigninExample example);

    int updateByExample(@Param("record") ResDoctorSignin record, @Param("example") ResDoctorSigninExample example);

    int updateByPrimaryKeySelective(ResDoctorSignin record);

    int updateByPrimaryKey(ResDoctorSignin record);
}