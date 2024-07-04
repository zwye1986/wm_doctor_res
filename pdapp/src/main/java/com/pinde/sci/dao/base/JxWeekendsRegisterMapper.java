package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.JxWeekendsRegister;
import com.pinde.sci.model.mo.JxWeekendsRegisterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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