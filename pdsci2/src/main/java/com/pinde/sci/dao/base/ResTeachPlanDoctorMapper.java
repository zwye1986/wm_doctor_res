package com.pinde.sci.dao.base;

import com.pinde.core.model.ResTeachPlanDoctor;
import com.pinde.core.model.ResTeachPlanDoctorExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResTeachPlanDoctorMapper {
    int countByExample(ResTeachPlanDoctorExample example);

    int deleteByExample(ResTeachPlanDoctorExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResTeachPlanDoctor record);

    int insertSelective(ResTeachPlanDoctor record);

    List<ResTeachPlanDoctor> selectByExample(ResTeachPlanDoctorExample example);

    ResTeachPlanDoctor selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResTeachPlanDoctor record, @Param("example") ResTeachPlanDoctorExample example);

    int updateByExample(@Param("record") ResTeachPlanDoctor record, @Param("example") ResTeachPlanDoctorExample example);

    int updateByPrimaryKeySelective(ResTeachPlanDoctor record);

    int updateByPrimaryKey(ResTeachPlanDoctor record);
}