package com.pinde.res.biz.osca.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.UserStatusEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.osca.IOscaAppBiz;
import com.pinde.res.biz.osca.IOscaDoctorRegistBiz;
import com.pinde.res.dao.osca.ext.OscaDoctorRegistExtMapper;
import com.pinde.res.enums.osca.AuditStatusEnum;
import com.pinde.sci.dao.base.OscaDoctorRegistMapper;
import com.pinde.core.model.OscaDoctorRegist;
import com.pinde.core.model.OscaDoctorRegistExample;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class OscaDoctorRegistBizImpl implements IOscaDoctorRegistBiz {
    @Autowired
    private OscaDoctorRegistMapper oscaDoctorRegistMapper;
    @Autowired
    private OscaDoctorRegistExtMapper oscaDoctorRegistExtMapper;
    @Autowired
    private IOscaAppBiz oscaAppBiz;
    @Override
    public OscaDoctorRegist read(String recordFlow) {
        return oscaDoctorRegistMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<OscaDoctorRegist> searchRegist(OscaDoctorRegist oscaDoctorRegist) {
        OscaDoctorRegistExample example = new OscaDoctorRegistExample();
        OscaDoctorRegistExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(oscaDoctorRegist.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(oscaDoctorRegist.getDoctorFlow());
        }
        return oscaDoctorRegistMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> search(Map<String, Object> paramMap) {
        return oscaDoctorRegistExtMapper.search(paramMap);
    }

    @Override
    public List<Map<String, Object>> searchStudents(Map<String, Object> paramMap) {
        return oscaDoctorRegistExtMapper.searchStudents(paramMap);
    }

    @Override
    public int edit(OscaDoctorRegist oscaDoctorRegist,SysUser user) {
        String recordFlow = oscaDoctorRegist.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            user.setModifyUserFlow(user.getUserFlow());
            user.setModifyTime(DateUtil.getCurrDateTime());
            return oscaDoctorRegistMapper.updateByPrimaryKeySelective(oscaDoctorRegist);
        }else {
            oscaDoctorRegist.setRecordFlow(PkUtil.getUUID());
            user.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            user.setCreateUserFlow(user.getUserFlow());
            user.setCreateTime(DateUtil.getCurrDateTime());
            user.setModifyUserFlow(user.getUserFlow());
            user.setModifyTime(DateUtil.getCurrDateTime());
            return oscaDoctorRegistMapper.insertSelective(oscaDoctorRegist);
        }
    }

    @Override
    public void editAudit(OscaDoctorRegist oscaDoctorRegist,SysUser saveuser) {
        edit(oscaDoctorRegist,saveuser);
        if(AuditStatusEnum.Passed.getId().equals(oscaDoctorRegist.getStatusId())){
            //修改user状态
            SysUser user = new SysUser();
            user.setUserFlow(oscaDoctorRegist.getDoctorFlow());
            user.setStatusId(UserStatusEnum.Activated.getId());
            user.setStatusDesc(UserStatusEnum.Activated.getName());
            user.setModifyUserFlow(saveuser.getUserFlow());
            user.setModifyTime(DateUtil.getCurrDateTime());
            oscaAppBiz.edit(user,saveuser);
            //修改doctor信息
            ResDoctor doctor = new ResDoctor();
            SysUser sysUser = oscaAppBiz.readSysUser(oscaDoctorRegist.getDoctorFlow());
            doctor.setDoctorFlow(oscaDoctorRegist.getDoctorFlow());
            doctor.setOrgFlow(sysUser.getOrgFlow());
            doctor.setOrgName(sysUser.getOrgName());
            oscaAppBiz.editDoctor(doctor,saveuser);
        }
    }

}
