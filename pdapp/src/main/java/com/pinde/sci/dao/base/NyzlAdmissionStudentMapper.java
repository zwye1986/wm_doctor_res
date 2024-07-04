package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.NyzlAdmissionStudent;
import com.pinde.sci.model.mo.NyzlAdmissionStudentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NyzlAdmissionStudentMapper {
    int countByExample(NyzlAdmissionStudentExample example);

    int deleteByExample(NyzlAdmissionStudentExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(NyzlAdmissionStudent record);

    int insertSelective(NyzlAdmissionStudent record);

    List<NyzlAdmissionStudent> selectByExample(NyzlAdmissionStudentExample example);

    NyzlAdmissionStudent selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") NyzlAdmissionStudent record, @Param("example") NyzlAdmissionStudentExample example);

    int updateByExample(@Param("record") NyzlAdmissionStudent record, @Param("example") NyzlAdmissionStudentExample example);

    int updateByPrimaryKeySelective(NyzlAdmissionStudent record);

    int updateByPrimaryKey(NyzlAdmissionStudent record);
}