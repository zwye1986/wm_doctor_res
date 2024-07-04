package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.QingpuDoctorSignin;
import com.pinde.sci.model.mo.QingpuDoctorSigninExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QingpuDoctorSigninMapper {
    int countByExample(QingpuDoctorSigninExample example);

    int deleteByExample(QingpuDoctorSigninExample example);

    int deleteByPrimaryKey(String reportFlow);

    int insert(QingpuDoctorSignin record);

    int insertSelective(QingpuDoctorSignin record);

    List<QingpuDoctorSignin> selectByExample(QingpuDoctorSigninExample example);

    QingpuDoctorSignin selectByPrimaryKey(String reportFlow);

    int updateByExampleSelective(@Param("record") QingpuDoctorSignin record, @Param("example") QingpuDoctorSigninExample example);

    int updateByExample(@Param("record") QingpuDoctorSignin record, @Param("example") QingpuDoctorSigninExample example);

    int updateByPrimaryKeySelective(QingpuDoctorSignin record);

    int updateByPrimaryKey(QingpuDoctorSignin record);
}