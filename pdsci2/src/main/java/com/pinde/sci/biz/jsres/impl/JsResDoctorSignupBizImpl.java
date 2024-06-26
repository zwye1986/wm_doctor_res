package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorSignupBiz;
import com.pinde.sci.dao.jsres.JsResDoctorSignupMapper;
import com.pinde.sci.model.jsres.DoctorSignup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class JsResDoctorSignupBizImpl implements IJsResDoctorSignupBiz {
    @Autowired
    private JsResDoctorSignupMapper doctorSignupMapper;

    @Override
    public int saveDoctorSignup(DoctorSignup doctorSignup) {
        doctorSignup.setRecordFlow(PkUtil.getUUID());
        try {
            return doctorSignupMapper.insert(doctorSignup);
        } catch (DataAccessException e) {
            return 0;
        }
    }

}
