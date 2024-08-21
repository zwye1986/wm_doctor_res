package com.pinde.sci.biz.jszy.impl;


import com.pinde.sci.biz.jszy.IJszyResRecruitDoctorInfoBiz;
import com.pinde.sci.dao.jszy.JszyResRecruitDoctorInfoExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class JszyResRecruitDoctorInfoBizImpl implements IJszyResRecruitDoctorInfoBiz {
	@Autowired
	private JszyResRecruitDoctorInfoExtMapper jszyResDoctorRecruitExtMapper;

    @Override
    public List<Map<String, Object>> zltjSecondSpe(Map<String,Object> param) {
        return jszyResDoctorRecruitExtMapper.zltjSecondSpe(param);
    }
}
