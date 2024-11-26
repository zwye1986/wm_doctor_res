package com.pinde.res.biz.jswjw.impl;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.GlobalUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IIeaveAppBiz;
import com.pinde.res.dao.jswjw.ext.StudentExtMapper;
import com.pinde.res.dao.sctcm120.ext.ResDoctorKqExtMapper;
import com.pinde.res.model.jswjw.mo.ResDoctorKqExt;
import com.pinde.sci.dao.base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName StudentAppBizImpl
 * @Deacription APP学员功能Biz
 * @Author shengl
 * @Date 2021-01-12 15:44
 * @Version 1.0
 **/
@Service
public class EaveAppBizImpl implements IIeaveAppBiz {

    @Autowired
    private ResKgCfgMapper kgCfgMapper;
    @Autowired
    private StudentExtMapper studentExtMapper;
    @Resource
    private SysCfgMapper cfgMapper;
    @Autowired
    private ResDoctorKqMapper resDoctorKqMapper;
    @Autowired
    private SysDictMapper sysDictMapper;
    @Autowired
    private ResDoctorSchProcessMapper resDoctorProcessMapper;
    @Autowired
    private ResDoctorKqExtMapper resDoctorKqExtMapper;
    @Autowired
    private ResDoctorKqLogMapper kqLogMapper;


    @Override
    public List<ResKgCfg> readKqCfgList(String orgFlow, String doctorCategoryId) {
        ResKgCfgExample example=new ResKgCfgExample();
        ResKgCfgExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andOrgFlowEqualTo(orgFlow);
        if(StringUtil.isNotBlank(doctorCategoryId))
        {
            criteria.andDoctorTypeIdEqualTo(doctorCategoryId);
        }
        return kgCfgMapper.selectByExample(example);
    }

    @Override
    public double readAllIntervalDays(Map<String,String> daysMap) {
        return studentExtMapper.readAllIntervalDays(daysMap);
    }

    /*@Override
    public double readAllIntervalDays(String recordFlow, String doctorFlow, String startDate, String endDate, String id) {
        return studentExtMapper.readAllIntervalDays( recordFlow,doctorFlow,  startDate,  endDate,  id);
    }
*/
    //获取系统配置信息
    @Override
    public String getCfgByCode(String code){
        if(StringUtil.isNotBlank(code)){
            String v= GlobalUtil.getLocalCfgMap().get(code);
            if(StringUtil.isNotBlank(v))
                return v;
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
    public List<SysUser> searchUserWithRole(Map<String, Object> paramMap) {

        return studentExtMapper.searchUserWithRole(paramMap);
    }

    @Override
    public ResDoctorKq readResDoctorKq(String recordFlow) {
        return resDoctorKqMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int checkTime(String recordFlow, String doctorFlow, String startDate, String endDate, String id, String sessionNumber) {
        return studentExtMapper.checkTime( recordFlow,doctorFlow,  startDate,  endDate,  id, sessionNumber);
    }

    @Override
    public List<SysDict> getDictListByDictId(String id) {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(id))
        {
            criteria.andDictTypeIdEqualTo(id);
        }
        List<SysDict> sysDictList = sysDictMapper.selectByExample(example);
        return sysDictList;
    }

    @Override
    public int editResDoctorKq(ResDoctorKq kq, SysUser sysUser, ResDoctorKq old) {
        if(old==null)
        {
            kq.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            kq.setCreateTime(DateUtil.getCurrDateTime());
            kq.setCreateUserFlow(sysUser.getUserFlow());
            kq.setModifyTime(DateUtil.getCurrDateTime());
            kq.setModifyUserFlow(sysUser.getUserFlow());
            return resDoctorKqMapper.insertSelective(kq);
        }else{
            kq.setModifyTime(DateUtil.getCurrDateTime());
            kq.setModifyUserFlow(sysUser.getUserFlow());
            return resDoctorKqMapper.updateByPrimaryKeySelective(kq);
        }
    }

    @Override
    public int updateResDoctorKq(ResDoctorKq kq) {
        /*kq.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        kq.setModifyTime(DateUtil.getCurrDateTime());
        kq.setModifyUserFlow(sysUser.getUserFlow());*/
        return resDoctorKqMapper.updateByPrimaryKeySelective(kq);
    }

    /**
     * @param status
     * @param kq
     * @Author shengl
     * @Description //请假列表
     * @Date 2021-01-12
     */
    @Override
    public List<ResDoctorKq> leaveAndAppealList(List<String> status, ResDoctorKq kq) {

        ResDoctorKqExample example = new ResDoctorKqExample();
        ResDoctorKqExample.Criteria criteria = example.createCriteria()
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(kq.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(kq.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(kq.getKqTypeId())){
            criteria.andKqTypeIdEqualTo(kq.getKqTypeId());
        }
        if(StringUtil.isNotBlank(kq.getTypeId())){
            criteria.andTypeIdEqualTo(kq.getTypeId());
        }
        if(StringUtil.isNotBlank(kq.getStartDate())){
            criteria.andEndDateGreaterThanOrEqualTo(kq.getStartDate());
        }
        if(StringUtil.isNotBlank(kq.getEndDate())){
            criteria.andStartDateLessThanOrEqualTo(kq.getEndDate());
        }
        if(StringUtil.isNotBlank(kq.getTeacherFlow())){
            criteria.andTeacherFlowEqualTo(kq.getTeacherFlow());
        }
        if(StringUtil.isNotBlank(kq.getHeadFlow())){
            criteria.andHeadFlowEqualTo(kq.getHeadFlow());
        }
        if(StringUtil.isNotBlank(kq.getTutorFlow())){
            criteria.andTutorFlowEqualTo(kq.getTutorFlow());
        }
        if(StringUtil.isNotBlank(kq.getManagerFlow())){
            criteria.andManagerFlowEqualTo(kq.getManagerFlow());
        }
        if(StringUtil.isNotBlank(kq.getDoctorName())){
            criteria.andDoctorNameLike("%"+kq.getDoctorName()+"%");
        }
        if(StringUtil.isNotBlank(kq.getOrgFlow())){
            criteria.andOrgFlowEqualTo(kq.getOrgFlow());
        }
        if(status!=null&&status.size()>0)
        {
            criteria.andAuditStatusIdIn(status);
        }
        example.setOrderByClause("create_time");
        return resDoctorKqMapper.selectByExample(example);
    }

    @Override
    public List<ResDoctorSchProcess> searchProcessByDoctor(String doctorFlow){
        ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(doctorFlow);
        example.setOrderByClause("sch_start_date");
        return resDoctorProcessMapper.selectByExample(example);
    }

    @Override
    public List<ResDoctorKq> searchResDoctorKq(Map<String, Object> param) {
        return studentExtMapper.searchResDoctorKq(param);
    }

    /**
     * @param param
     * @Author shengl
     * @Description // 查询数据总数
     * @Date 2021-01-14
     */
    @Override
    public List<Map<String,Object>> queryResDoctorKqCount(Map<String, Object> param) {
        return studentExtMapper.queryResDoctorKqCount(param);
    }

    @Override
    public List<Map<String, String>> searchDoctorKqList(ResDoctorKq kq) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("orgFlow",kq.getOrgFlow());
        paramMap.put("doctorFlow",kq.getDoctorFlow());
        paramMap.put("kqTypeId",kq.getKqTypeId());
        if(StringUtil.isNotBlank(kq.getTypeId())){
            paramMap.put("typeId",kq.getTypeId());
        }
        if(StringUtil.isNotBlank(kq.getSchDeptFlow())){
            paramMap.put("schDeptFlow",kq.getSchDeptFlow());
        }
        if(StringUtil.isNotBlank(kq.getAuditStatusId())){
            paramMap.put("auditStatusId",kq.getAuditStatusId());
        }
        return resDoctorKqExtMapper.searchDoctorKqList(paramMap);
    }

    @Override
    public List<ResDoctorSchProcess> searchProcessByDoctorFlow(String userFlow) {
        ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        ResDoctorSchProcessExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(userFlow)){
            criteria.andUserFlowEqualTo(userFlow);
        }
        example.setOrderByClause("SCH_START_DATE DESC");
        return resDoctorProcessMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> searchDictList(String dictTypeId) {
        return resDoctorKqExtMapper.searchDictList(dictTypeId);
    }

    @Override
    public int checkLeaveTime(String recordFlow, String doctorFlow, String startDate, String endDate, String typeId) {
        return resDoctorKqExtMapper.checkLeaveTime( recordFlow,doctorFlow,  startDate,  endDate,  typeId);
    }

    @Override
    public int addResDoctorKq(ResDoctorKqExt kqExt,SysUser user) {
        ResDoctorKq kq = kqExt;
        kq.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        kq.setCreateTime(DateUtil.getCurrDateTime());
        kq.setCreateUserFlow(user.getUserFlow());
        kq.setModifyTime(DateUtil.getCurrDateTime());
        kq.setModifyUserFlow(user.getUserFlow());
        return resDoctorKqMapper.insertSelective(kq);

    }

    @Override
    public List<ResDoctorKqLog> searchKqLogList(String kqRecordFlow, String typeId) {
        ResDoctorKqLogExample example = new ResDoctorKqLogExample();
        ResDoctorKqLogExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(kqRecordFlow)){
            criteria.andKqRecordFlowEqualTo(kqRecordFlow);
        }
        if(StringUtil.isNotBlank(typeId)){
            criteria.andTypeIdEqualTo(typeId);
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return kqLogMapper.selectByExample(example);
    }

    @Override
    public int updateKqLogsRecordStatusN(String kqRecordFlow, String typeId) {
        return resDoctorKqExtMapper.updateKqLogsRecordStatusN(kqRecordFlow,typeId);
    }

    @Override
    public int editResDoctorKqNew(ResDoctorKq kq,SysUser user) {
        kq.setModifyTime(DateUtil.getCurrDateTime());
        kq.setModifyUserFlow(user.getUserFlow());
        return resDoctorKqMapper.updateByPrimaryKeySelective(kq);
    }

    @Override
    public ResDoctorKqLog searchKqLog(String kqRecordFlow, String typeId,String roleId) {
        ResDoctorKqLogExample example = new ResDoctorKqLogExample();
        ResDoctorKqLogExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        if(StringUtil.isNotBlank(kqRecordFlow)){
            criteria.andKqRecordFlowEqualTo(kqRecordFlow);
        }
        if(StringUtil.isNotBlank(typeId)){
            criteria.andTypeIdEqualTo(typeId);
        }
        if(StringUtil.isNotBlank(roleId)){
            criteria.andRoleIdEqualTo(roleId);
        }
        example.setOrderByClause("CREATE_TIME DESC");
        List<ResDoctorKqLog> logs = kqLogMapper.selectByExample(example);
        if(null != logs && logs.size()>0){
            return logs.get(0);
        }
        return null;
    }

    @Override
    public int addResDoctorKqLog(ResDoctorKqLog log,SysUser user) {
        log.setRecordFlow(PkUtil.getUUID());
        log.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        log.setCreateTime(DateUtil.getCurrDateTime());
        log.setCreateUserFlow(user.getUserFlow());
        log.setModifyTime(DateUtil.getCurrDateTime());
        log.setModifyUserFlow(user.getUserFlow());
        return kqLogMapper.insertSelective(log);
    }

    @Override
    public List<ResDoctorKq> searchAuditResDoctorKq(Map<String, Object> paramMap) {
        return studentExtMapper.searchAuditResDoctorKq(paramMap);
    }

    @Override
    public int saveKqLog(ResDoctorKqLog kqLog, SysUser user) {
        if(StringUtil.isNotBlank(kqLog.getRecordFlow())) {
            kqLog.setModifyTime(DateUtil.getCurrDateTime());
            kqLog.setModifyUserFlow(user.getUserFlow());
            return kqLogMapper.updateByPrimaryKeySelective(kqLog);
        }else{
            kqLog.setRecordFlow(PkUtil.getUUID());
            kqLog.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            kqLog.setCreateTime(DateUtil.getCurrDateTime());
            kqLog.setCreateUserFlow(user.getUserFlow());
            kqLog.setModifyTime(DateUtil.getCurrDateTime());
            kqLog.setModifyUserFlow(user.getUserFlow());
            return kqLogMapper.insertSelective(kqLog);
        }
    }

    @Override
    public List<Map<String,Object>> queryResDoctorKqCountNew(Map<String, Object> param) {
        return studentExtMapper.queryResDoctorKqCountNew(param);
    }

}
