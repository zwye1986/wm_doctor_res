package com.pinde.sci.biz.osca.impl;

import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaDoctorRegistBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.OscaDoctorRegistMapper;
import com.pinde.sci.dao.osca.OscaDoctorRegistExtMapper;
import com.pinde.sci.model.mo.OscaDoctorRegist;
import com.pinde.sci.model.mo.OscaDoctorRegistExample;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;
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
    private IRoleBiz roleBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;

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
    public int edit(OscaDoctorRegist oscaDoctorRegist) {
        String recordFlow = oscaDoctorRegist.getRecordFlow();
        if(StringUtil.isNotBlank(recordFlow)){
            GeneralMethod.setRecordInfo(oscaDoctorRegist,false);
            return oscaDoctorRegistMapper.updateByPrimaryKeySelective(oscaDoctorRegist);
        }else {
            oscaDoctorRegist.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(oscaDoctorRegist,true);
            return oscaDoctorRegistMapper.insertSelective(oscaDoctorRegist);
        }
    }

    @Override
    public void editAudit(OscaDoctorRegist oscaDoctorRegist) {
        edit(oscaDoctorRegist);
        if(AuditStatusEnum.Passed.getId().equals(oscaDoctorRegist.getStatusId())){
            //增加学员角色
//            SysRole sysRole = new SysRole();
//            sysRole.setWsId("osca");
//            List<String> levelIds=new ArrayList<>();
//            levelIds.add(RoleLevelEnum.SysLevel.getId());
//            levelIds.add(RoleLevelEnum.GateLevel.getId());
//            sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//            List<SysRole> sysRoleList = roleBiz.search(sysRole,levelIds);
//            if(sysRoleList!=null&&sysRoleList.size()>0){
//                for(SysRole sysRole1:sysRoleList){
//                    if("学员".equals(sysRole1.getRoleName())){
//                        SysUserRole userRole = new SysUserRole();
//                        userRole.setUserFlow(oscaDoctorRegist.getDoctorFlow());
//                        String currWsId = "osca";
//                        userRole.setWsId(currWsId);
//                        userRole.setRoleFlow(sysRole1.getRoleFlow());
//                        userRole.setAuthTime(DateUtil.getCurrDate());
//                        userRoleBiz.saveSysUserRole(userRole);
//                    }
//                }
//            }
            //修改user状态
            SysUser user = new SysUser();
            user.setUserFlow(oscaDoctorRegist.getDoctorFlow());
            user.setStatusId(UserStatusEnum.Activated.getId());
            user.setStatusDesc(UserStatusEnum.Activated.getName());
            userBiz.edit(user);
            //修改doctor信息
            ResDoctor doctor = new ResDoctor();
            SysUser sysUser = userBiz.readSysUser(oscaDoctorRegist.getDoctorFlow());
            doctor.setDoctorFlow(oscaDoctorRegist.getDoctorFlow());
            doctor.setOrgFlow(sysUser.getOrgFlow());
            doctor.setOrgName(sysUser.getOrgName());
            doctorBiz.editDoctor(doctor);
        }
    }

}
