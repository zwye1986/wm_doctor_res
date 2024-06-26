package com.pinde.sci.biz.hbzy.impl;


import com.pinde.sci.biz.hbzy.IJszyResRecruitDoctorInfoBiz;
import com.pinde.sci.dao.hbzy.HbzyResRecruitDoctorInfoExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class HbzyResRecruitDoctorInfoBizImpl implements IJszyResRecruitDoctorInfoBiz {
	@Autowired
	private HbzyResRecruitDoctorInfoExtMapper jszyResDoctorRecruitExtMapper;

    @Override
    public List<Map<String, Object>> zltjSecondSpe(Map<String,Object> param) {
        return jszyResDoctorRecruitExtMapper.zltjSecondSpe(param);
    }
}
