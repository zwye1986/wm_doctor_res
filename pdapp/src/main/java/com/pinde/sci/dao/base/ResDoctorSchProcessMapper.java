package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.ResDoctorSchProcessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResDoctorSchProcessMapper {
    int countByExample(ResDoctorSchProcessExample example);

    int deleteByExample(ResDoctorSchProcessExample example);

    int deleteByPrimaryKey(String processFlow);

    int insert(ResDoctorSchProcess record);

    int insertSelective(ResDoctorSchProcess record);

    List<ResDoctorSchProcess> selectByExample(ResDoctorSchProcessExample example);

    ResDoctorSchProcess selectByPrimaryKey(String processFlow);

    int updateByExampleSelective(@Param("record") ResDoctorSchProcess record, @Param("example") ResDoctorSchProcessExample example);

    int updateByExample(@Param("record") ResDoctorSchProcess record, @Param("example") ResDoctorSchProcessExample example);

    int updateByPrimaryKeySelective(ResDoctorSchProcess record);

    int updateByPrimaryKey(ResDoctorSchProcess record);
}