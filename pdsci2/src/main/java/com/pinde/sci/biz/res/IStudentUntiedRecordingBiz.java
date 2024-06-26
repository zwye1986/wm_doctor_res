package com.pinde.sci.biz.res;

import com.pinde.sci.model.mo.DoctorUntiedRecording;

import java.util.List;

public interface IStudentUntiedRecordingBiz {

    DoctorUntiedRecording selectByPrimaryKey(String doctorFlow);

    int editDoctorUntiedRecording(DoctorUntiedRecording recording);

    List<DoctorUntiedRecording> selectDoctorUntiedRecordingList(DoctorUntiedRecording recording);
}
