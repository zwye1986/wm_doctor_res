package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorSkill;
import com.pinde.sci.model.mo.ResDoctorSkillExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResDoctorSkillMapper {
    int countByExample(ResDoctorSkillExample example);

    int deleteByExample(ResDoctorSkillExample example);

    int deleteByPrimaryKey(String doctorSkillFlow);

    int insert(ResDoctorSkill record);

    int insertSelective(ResDoctorSkill record);

    List<ResDoctorSkill> selectByExample(ResDoctorSkillExample example);

    ResDoctorSkill selectByPrimaryKey(String doctorSkillFlow);

    int updateByExampleSelective(@Param("record") ResDoctorSkill record, @Param("example") ResDoctorSkillExample example);

    int updateByExample(@Param("record") ResDoctorSkill record, @Param("example") ResDoctorSkillExample example);

    int updateByPrimaryKeySelective(ResDoctorSkill record);

    int updateByPrimaryKey(ResDoctorSkill record);
}