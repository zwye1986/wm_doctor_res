package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResRecruitRegister;
import com.pinde.sci.model.mo.ResRecruitRegisterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResRecruitRegisterMapper {
    int countByExample(ResRecruitRegisterExample example);

    int deleteByExample(ResRecruitRegisterExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResRecruitRegister record);

    int insertSelective(ResRecruitRegister record);

    List<ResRecruitRegister> selectByExample(ResRecruitRegisterExample example);

    ResRecruitRegister selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResRecruitRegister record, @Param("example") ResRecruitRegisterExample example);

    int updateByExample(@Param("record") ResRecruitRegister record, @Param("example") ResRecruitRegisterExample example);

    int updateByPrimaryKeySelective(ResRecruitRegister record);

    int updateByPrimaryKey(ResRecruitRegister record);
}