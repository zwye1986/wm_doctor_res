package com.pinde.sci.biz.res.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.res.IStudentUntiedRecordingBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.ctrl.sch.plan.util.StringUtil;
import com.pinde.sci.dao.base.DoctorUntiedRecordingMapper;
import com.pinde.sci.model.mo.DoctorUntiedRecording;
import com.pinde.sci.model.mo.DoctorUntiedRecordingExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class StudentUntiedRecordingBizLimp  implements IStudentUntiedRecordingBiz {

    @Autowired
    DoctorUntiedRecordingMapper recordingMapper;

    @Override
    public DoctorUntiedRecording selectByPrimaryKey(String doctorFlow) {

        return recordingMapper.selectByPrimaryKey(doctorFlow);
    }


    @Override
    public int editDoctorUntiedRecording(DoctorUntiedRecording recording) {
        String recordFlow = recording.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(recording,false);
            return recordingMapper.updateByPrimaryKeySelective(recording);
        } else {
            recordFlow = PkUtil.getUUID();
            recording.setRecordFlow(recordFlow);
            GeneralMethod.setRecordInfo(recording,true);
            return recordingMapper.insertSelective(recording);
        }
    }

    @Override
    public List<DoctorUntiedRecording> selectDoctorUntiedRecordingList(DoctorUntiedRecording recording) {
        DoctorUntiedRecordingExample example = new DoctorUntiedRecordingExample();
        DoctorUntiedRecordingExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(recording != null){
            String doctorFlow = recording.getDoctorFlow();
            if(StringUtil.isNotBlank(doctorFlow)){
                criteria.andDoctorFlowEqualTo(doctorFlow);
            }
            String lockStatus = recording.getLockStatus();
            if(StringUtil.isNotBlank(lockStatus)){
                criteria.andLockStatusEqualTo(lockStatus);
            }
        }
        example.setOrderByClause("UNTIED_DATE DESC");
        return recordingMapper.selectByExample(example);
    }
}
