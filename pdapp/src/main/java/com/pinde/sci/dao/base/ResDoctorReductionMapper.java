package com.pinde.sci.dao.base;

import com.pinde.core.model.ResDoctorReduction;
import com.pinde.core.model.ResDoctorReductionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResDoctorReductionMapper {
    int countByExample(ResDoctorReductionExample example);

    int deleteByExample(ResDoctorReductionExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(ResDoctorReduction record);

    int insertSelective(ResDoctorReduction record);

    List<ResDoctorReduction> selectByExample(ResDoctorReductionExample example);

    ResDoctorReduction selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") ResDoctorReduction record, @Param("example") ResDoctorReductionExample example);

    int updateByExample(@Param("record") ResDoctorReduction record, @Param("example") ResDoctorReductionExample example);

    int updateByPrimaryKeySelective(ResDoctorReduction record);

    int updateByPrimaryKey(ResDoctorReduction record);
}