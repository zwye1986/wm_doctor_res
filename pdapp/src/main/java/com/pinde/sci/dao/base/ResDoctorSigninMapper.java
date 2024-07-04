package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorSignin;
import com.pinde.sci.model.mo.ResDoctorSigninExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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