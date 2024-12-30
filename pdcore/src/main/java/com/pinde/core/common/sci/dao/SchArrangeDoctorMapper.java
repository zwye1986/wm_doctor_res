package com.pinde.core.common.sci.dao;

import com.pinde.core.model.SchArrangeDoctor;
import com.pinde.core.model.SchArrangeDoctorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchArrangeDoctorMapper {
    int countByExample(SchArrangeDoctorExample example);

    int deleteByExample(SchArrangeDoctorExample example);

    int deleteByPrimaryKey(String arrDocFlow);

    int insert(SchArrangeDoctor record);

    int insertSelective(SchArrangeDoctor record);

    List<SchArrangeDoctor> selectByExample(SchArrangeDoctorExample example);

    SchArrangeDoctor selectByPrimaryKey(String arrDocFlow);

    int updateByExampleSelective(@Param("record") SchArrangeDoctor record, @Param("example") SchArrangeDoctorExample example);

    int updateByExample(@Param("record") SchArrangeDoctor record, @Param("example") SchArrangeDoctorExample example);

    int updateByPrimaryKeySelective(SchArrangeDoctor record);

    int updateByPrimaryKey(SchArrangeDoctor record);
}