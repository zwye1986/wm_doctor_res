package com.pinde.sci.dao.base;

import com.pinde.sci.model.mo.DoctorUntiedRecording;
import com.pinde.sci.model.mo.DoctorUntiedRecordingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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