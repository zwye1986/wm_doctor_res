package com.pinde.sci.dao.base;

import com.pinde.core.model.JxNationalHolidaysRegister;
import com.pinde.core.model.JxNationalHolidaysRegisterExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JxNationalHolidaysRegisterMapper {
    int countByExample(JxNationalHolidaysRegisterExample example);

    int deleteByExample(JxNationalHolidaysRegisterExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(JxNationalHolidaysRegister record);

    int insertSelective(JxNationalHolidaysRegister record);

    List<JxNationalHolidaysRegister> selectByExample(JxNationalHolidaysRegisterExample example);

    JxNationalHolidaysRegister selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") JxNationalHolidaysRegister record, @Param("example") JxNationalHolidaysRegisterExample example);

    int updateByExample(@Param("record") JxNationalHolidaysRegister record, @Param("example") JxNationalHolidaysRegisterExample example);

    int updateByPrimaryKeySelective(JxNationalHolidaysRegister record);

    int updateByPrimaryKey(JxNationalHolidaysRegister record);
}