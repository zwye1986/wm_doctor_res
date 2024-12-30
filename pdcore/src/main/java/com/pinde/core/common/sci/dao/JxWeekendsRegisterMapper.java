package com.pinde.core.common.sci.dao;

import com.pinde.core.model.JxWeekendsRegister;
import com.pinde.core.model.JxWeekendsRegisterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JxWeekendsRegisterMapper {
    int countByExample(JxWeekendsRegisterExample example);

    int deleteByExample(JxWeekendsRegisterExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JxWeekendsRegister record);

    int insertSelective(JxWeekendsRegister record);

    List<JxWeekendsRegister> selectByExample(JxWeekendsRegisterExample example);

    JxWeekendsRegister selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JxWeekendsRegister record, @Param("example") JxWeekendsRegisterExample example);

    int updateByExample(@Param("record") JxWeekendsRegister record, @Param("example") JxWeekendsRegisterExample example);

    int updateByPrimaryKeySelective(JxWeekendsRegister record);

    int updateByPrimaryKey(JxWeekendsRegister record);
}