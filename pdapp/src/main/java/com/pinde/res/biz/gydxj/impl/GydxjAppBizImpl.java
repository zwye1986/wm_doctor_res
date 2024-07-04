package com.pinde.res.biz.gydxj.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.GlobalUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gydxj.IGydxjAppBiz;
import com.pinde.res.dao.gydxj.ext.GyXjEduNoticeExtMapper;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class GydxjAppBizImpl implements IGydxjAppBiz {
    @Resource
    private SysUserMapper userMapper;
    @Resource
    private SysUserRoleMapper userRoleMapper;
    @Resource
    private SysCfgMapper cfgMapper;
    @Resource
    private ResDoctorMapper doctorMapper;
    @Resource
    private GyXjEduNoticeExtMapper eduExtMapper;
    @Resource
    private EduTeachingnoticeMapper noticeMapper;
    @Resource
    private EduUserMapper eduUserMapper;
    @Resource
    private EduUserInfoStatusMapper infoStatusMapper;
    @Resource
    private NydsOfficialDoctorMapper offMapper;
    @Resource
    private SysOrgMapper sysOrgMapper;
    @Resource
    private SysDeptMapper sysDeptMapper;
    @Resource
    private EduCourseMapper courseMapper;

    @Override
    public SysUser getUserByCode(String userCode)
    {
        SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andUserCodeEqualTo(userCode);
        List<SysUser> sysUserList = userMapper.selectByExample(sysUserExample);
        if(sysUserList.size()>0){
            return sysUserList.get(0);
        }
        return null;

    }

    @Override
    public SysUser readSysUser(String sysUserFlow) {
        return userMapper.selectByPrimaryKey(sysUserFlow);
    }

    @Override
    public List<SysUserRole> getSysUserRole(String userFlow){
        SysUserRoleExample example = new SysUserRoleExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andUserFlowEqualTo(userFlow);
        return userRoleMapper.selectByExample(example);
    }

    @Override
    public String getCfgByCode(String code){
        if(StringUtil.isNotBlank(code)){
            String v= GlobalUtil.getLocalCfgMap().get(code);
            if(StringUtil.isNotBlank(v)){
                return v;
            }
            SysCfg cfg = cfgMapper.selectByPrimaryKey(code);
            if(cfg!=null){
                String val = cfg.getCfgValue();
                if(!StringUtil.isNotBlank(val)){
                    val = cfg.getCfgBigValue();
                }
                return val;
            }
        }
        return null;
    }

    @Override
    public ResDoctor readResDoctor(String userFlow) {
        return doctorMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public EduUser readEduUser(String userFlow) {
        return eduUserMapper.selectByPrimaryKey(userFlow);
    }

    @Override
    public List<EduTeachingnotice> searchByRoles(String userFlow, String infoTypeId) {
        return eduExtMapper.searchByRoles(userFlow,infoTypeId);
    }

    @Override
    public EduTeachingnotice findNoticByFlow(String flow) {
        return noticeMapper.selectByPrimaryKey(flow);
    }

    @Override
    public int updateUser(SysUser sysUser) {
        if(StringUtil.isNotBlank(sysUser.getUserFlow())){
            return userMapper.updateByPrimaryKeySelective(sysUser);
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public SysCfg readCfg(String cfgCode) {
        return cfgMapper.selectByPrimaryKey(cfgCode);
    }

    @Override
    public List<EduUserInfoStatus> searchPartStatus(String userFlow) {
        EduUserInfoStatusExample example = new EduUserInfoStatusExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return infoStatusMapper.selectByExample(example);
    }

    @Override
    public List<NydsOfficialDoctor> queryDoctorList(NydsOfficialDoctor officialDoctor) {
        NydsOfficialDoctorExample example=new NydsOfficialDoctorExample();
        NydsOfficialDoctorExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(officialDoctor.getXwkAuditStatusId())){
            criteria.andXwkAuditStatusIdEqualTo(officialDoctor.getXwkAuditStatusId());
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return offMapper.selectByExample(example);
    }

    @Override
    public List<SysOrg> searchSysOrg() {
        SysOrgExample example=new SysOrgExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        example.setOrderByClause("ORDINAL");
        return sysOrgMapper.selectByExample(example);
    }

    @Override
    public List<SysDept> searchDeptByOrg(String orgFlow) {
        SysDeptExample example = new SysDeptExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
        example.setOrderByClause("ORDINAL");
        return sysDeptMapper.selectByExample(example);
    }

    @Override
    public SysDept readSysDept(String deptFlow) {
        return sysDeptMapper.selectByPrimaryKey(deptFlow);
    }

    @Override
    public EduCourse readEduCourse(String courseFlow) {
        return courseMapper.selectByPrimaryKey(courseFlow);
    }

    @Override
    public int saveEduUser(EduUser user) {
        if(StringUtil.isNotBlank(user.getUserFlow())){
            return eduUserMapper.updateByPrimaryKeySelective(user);
        }
        return 0;
    }
}
