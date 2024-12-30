package com.pinde.core.common.sci.dao;

import com.pinde.core.model.DoctorUntiedRecording;
import com.pinde.core.model.DoctorUntiedRecordingExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorUntiedRecordingMapper {
    int countByExample(DoctorUntiedRecordingExample example);

    int deleteByExample(DoctorUntiedRecordingExample example);

    int deleteByPrimaryKey(String recordFlow);

    int insert(DoctorUntiedRecording record);

    int insertSelective(DoctorUntiedRecording record);

    List<DoctorUntiedRecording> selectByExample(DoctorUntiedRecordingExample example);

    DoctorUntiedRecording selectByPrimaryKey(String recordFlow);

    int updateByExampleSelective(@Param("record") DoctorUntiedRecording record, @Param("example") DoctorUntiedRecordingExample example);

    int updateByExample(@Param("record") DoctorUntiedRecording record, @Param("example") DoctorUntiedRecordingExample example);

    int updateByPrimaryKeySelective(DoctorUntiedRecording record);

    int updateByPrimaryKey(DoctorUntiedRecording record);
}