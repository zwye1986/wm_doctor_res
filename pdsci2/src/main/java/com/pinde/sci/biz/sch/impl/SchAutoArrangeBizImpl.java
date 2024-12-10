package com.pinde.sci.biz.sch.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchAutoArrangeBiz;
import com.pinde.sci.biz.sch.ISchRotationGroupBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.SchArrangeResultMapper;
import com.pinde.sci.dao.base.SchAutoArrangeCfgMapper;
import com.pinde.sci.dao.base.SchAutoArrangeMapper;
import com.pinde.sci.dao.sch.SchAutoArrangeExtMapper;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchAutoArrangeBizImpl implements ISchAutoArrangeBiz {

    @Autowired
    private SchAutoArrangeExtMapper autoArrangeExtMapper;
    @Autowired
    private SchAutoArrangeCfgMapper autoArrangeCfgMapper;
    @Autowired
    private SchAutoArrangeMapper autoArrangeMapper;
    @Autowired
    private SchArrangeResultMapper resultMapper;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private IResDoctorProcessBiz processBiz;
    @Override
    public int checkRotationLocal(String orgFlow, String sessionNumber) {
        return autoArrangeExtMapper.checkRotationLocal(orgFlow,sessionNumber);
    }
    @Override
    public int checkDoctorCount(String orgFlow, String sessionNumber, String schFlag) {
        return autoArrangeExtMapper.checkDoctorCount(orgFlow,sessionNumber,schFlag);
    }
    @Override
    public int checkOrgDoctorCount(String orgFlow, String sessionNumber, String schFlag) {
        return autoArrangeExtMapper.checkOrgDoctorCount(orgFlow,sessionNumber,schFlag);
    }
    @Override
    public int checkArrangeCount(String orgFlow, String sessionNumber) {
        return autoArrangeExtMapper.checkArrangeCount(orgFlow,sessionNumber);
    }
    @Override
    public int checkDoctorCycleCount(String orgFlow, String sessionNumber, String schFlag) {
        return autoArrangeExtMapper.checkDoctorCycleCount(orgFlow,sessionNumber,schFlag);
    }
    @Override
    public int checkDoctorSecondCount(String orgFlow, String sessionNumber, String schFlag) {
        return autoArrangeExtMapper.checkDoctorSecondCount(orgFlow,sessionNumber,schFlag);
    }
    @Override
    public int getLastDoctorCount(String orgFlow, String sessionNumber) {
        return autoArrangeExtMapper.getLastDoctorCount(orgFlow,sessionNumber);
    }

    @Override
    public SchAutoArrangeCfg findSessionNumberStartDate(String sessionNumber, String orgFlow) {
        SchAutoArrangeCfgExample example=new SchAutoArrangeCfgExample();
        example.createCriteria().andSessionNumberEqualTo(sessionNumber).andOrgFlowEqualTo(orgFlow)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SchAutoArrangeCfg> list=autoArrangeCfgMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return  list.get(0);
        }
        return null;
    }

    @Override
    public int saveSessionNumberStartDate(SchAutoArrangeCfg cfg) {
        if(StringUtil.isBlank(cfg.getRecordFlow()))
        {
            cfg.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(cfg,true);
            return autoArrangeCfgMapper.insertSelective(cfg);
        }else{
            GeneralMethod.setRecordInfo(cfg,false);
            return autoArrangeCfgMapper.updateByPrimaryKeySelective(cfg);
        }
    }

    @Override
    public String startAutoArrange(String sessionNumber, String orgFlow, String startDate, String flag) {
        if(StringUtil.isBlank(sessionNumber))
        {
            return "未选择需要排班的年级！";
        }
        if(StringUtil.isBlank(startDate))
        {
            return "未设置排班开始时间！";
        }
        List<ResDoctor> doctorList=getDoctorList(orgFlow,sessionNumber,flag);
        if(doctorList==null||doctorList.size()<=0)
        {
            return "无需要排班的学员";
        }
        //获取所有学员 分开他们一级轮转方案与二级轮转方案
        Map<String,List<ResDoctor>> rotationDoctorMap=new HashMap<>();
        Map<String,List<ResDoctor>> secondRotationDoctorMap=new HashMap<>();
        for(ResDoctor doctor:doctorList)
        {
            if(StringUtil.isNotBlank(doctor.getRotationFlow()))
            {
                List<ResDoctor> temp=rotationDoctorMap.get(doctor.getRotationFlow());
                if(temp==null) temp=new ArrayList<>();
                temp.add(doctor);
                rotationDoctorMap.put(doctor.getRotationFlow(),temp);
            }
            if(StringUtil.isNotBlank(doctor.getSecondRotationFlow()))
            {
                List<ResDoctor> temp=secondRotationDoctorMap.get(doctor.getSecondRotationFlow());
                if(temp==null) temp=new ArrayList<>();
                temp.add(doctor);
                secondRotationDoctorMap.put(doctor.getSecondRotationFlow(),temp);
            }
        }

        Map<String,List<SchRotationDept>> deptMap=new HashMap<>();
        List<SchRotationDept> schRotationDepts=getSchRotationDepts(orgFlow,sessionNumber);
        if(schRotationDepts==null||schRotationDepts.size()<=0)
        {
            return "未维护方案配置";
        }
        for(SchRotationDept d:schRotationDepts)
        {
            List<SchRotationDept> temp=deptMap.get(d.getRotationFlow());
            if(temp==null)  temp=new ArrayList<>();
            temp.add(d);
            deptMap.put(d.getRotationFlow(),temp);
        }
        //循环方案为每个方案配置
        List<SchRotation> rotations=getSchRotations(orgFlow);
        if(rotations!=null&&rotations.size()>0)
        {
            List<SchRotationDept> depts;
            int all;
            int i;
            for(SchRotation rotation:rotations)
            {
                //轮转方案下是否有配置的轮转科室，有则继续
                depts=deptMap.get(rotation.getRotationFlow());
                if(depts!=null&&depts.size()>0) {
                    all=depts.size();
                    i=0;
                    List<ResDoctor> doctorList1=null;
                    Map<String,Integer> indexMap=new HashMap<>();
                    for(SchRotationDept d:depts)
                    {
                        indexMap.put(d.getRecordFlow(),i++);
                    }
                    doctorList1 = rotationDoctorMap.get(rotation.getRotationFlow());
                    if (doctorList1 != null && doctorList1.size() > 0) {
                        for (ResDoctor doctor : doctorList1) {
                            autoArrangeDoctor(all,orgFlow,sessionNumber,doctor,indexMap,startDate,rotation,depts);
                        }

                    }
                    doctorList1=secondRotationDoctorMap.get(rotation.getRotationFlow());
                    if (doctorList1 != null && doctorList1.size() > 0) {
                        for (ResDoctor doctor : doctorList1) {
                            autoArrangeDoctor(all,orgFlow,sessionNumber,doctor,indexMap,startDate,rotation,depts);
                        }

                    }

                }
            }
        }

        return null;
    }

    @Override
    public String reStartAutoArrange(String sessionNumber, String orgFlow, String startDate) {
        if(StringUtil.isBlank(sessionNumber))
        {
            return "未选择需要排班的年级！";
        }
        if(StringUtil.isBlank(startDate))
        {
            return "未设置排班开始时间！";
        }
        List<ResDoctor> doctorList=getDoctorList(orgFlow,sessionNumber, "");
        if(doctorList==null||doctorList.size()<=0)
        {
            return "无需要排班的学员";
        }
        //获取所有学员 分开他们一级轮转方案与二级轮转方案
        Map<String,List<ResDoctor>> rotationDoctorMap=new HashMap<>();
        Map<String,List<ResDoctor>> secondRotationDoctorMap=new HashMap<>();
        for(ResDoctor doctor:doctorList)
        {
            if(StringUtil.isNotBlank(doctor.getRotationFlow()))
            {
                List<ResDoctor> temp=rotationDoctorMap.get(doctor.getRotationFlow());
                if(temp==null) temp=new ArrayList<>();
                temp.add(doctor);
                rotationDoctorMap.put(doctor.getRotationFlow(),temp);
            }
            if(StringUtil.isNotBlank(doctor.getSecondRotationFlow()))
            {
                List<ResDoctor> temp=secondRotationDoctorMap.get(doctor.getSecondRotationFlow());
                if(temp==null) temp=new ArrayList<>();
                temp.add(doctor);
                secondRotationDoctorMap.put(doctor.getSecondRotationFlow(),temp);
            }
        }

        Map<String,List<SchRotationDept>> deptMap=new HashMap<>();
        List<SchRotationDept> schRotationDepts=getSchRotationDepts(orgFlow,sessionNumber);
        if(schRotationDepts==null||schRotationDepts.size()<=0)
        {
            return "未维护方案配置";
        }
        for(SchRotationDept d:schRotationDepts)
        {
            List<SchRotationDept> temp=deptMap.get(d.getRotationFlow());
            if(temp==null)  temp=new ArrayList<>();
            temp.add(d);
            deptMap.put(d.getRotationFlow(),temp);
        }
        //循环方案为每个方案配置
        List<SchRotation> rotations=getSchRotations(orgFlow);
        if(rotations!=null&&rotations.size()>0)
        {
            for(SchRotation rotation:rotations)
            {
                //重新排班需要清除下一次开始轮转的科室记录
                SchAutoArrange arrange = getLastDeptFlow(orgFlow, sessionNumber, rotation.getRotationFlow(),"");
                if(arrange!=null)
                {
                    arrange.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                    updateLastDeptFlow(arrange);
                }
                //轮转方案下是否有配置的轮转科室，有则继续
                List<SchRotationDept> depts=deptMap.get(rotation.getRotationFlow());
                if(depts!=null&&depts.size()>0) {
                    int all=depts.size();
                    int i=0;
                    Map<String,Integer> indexMap=new HashMap<>();
                    for(SchRotationDept d:depts)
                    {
                        indexMap.put(d.getRecordFlow(),i++);
                    }
                    List<ResDoctor> doctorList1 = rotationDoctorMap.get(rotation.getRotationFlow());
                    if (doctorList1 != null && doctorList1.size() > 0) {
                        for (ResDoctor doctor : doctorList1) {
                            autoArrangeDoctor(all,orgFlow,sessionNumber,doctor,indexMap,startDate,rotation,depts);
                        }

                    }
                    List<ResDoctor> doctorList2=secondRotationDoctorMap.get(rotation.getRotationFlow());
                    if (doctorList2 != null && doctorList2.size() > 0) {
                        for (ResDoctor doctor : doctorList2) {
                            autoArrangeDoctor(all,orgFlow,sessionNumber,doctor,indexMap,startDate,rotation,depts);
                        }

                    }

                }
            }
        }

        return null;
    }

    @Override
    public List<SchArrangeResult> getArrangeResult(String startDate, String endDate, String orgFlow, String sessionNumber, List<String> doctorFlows) {
        SchArrangeResultExample example = new SchArrangeResultExample();
        SchArrangeResultExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSessionNumberEqualTo(sessionNumber);
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        if(StringUtil.isNotBlank(startDate)){
            criteria.andSchEndDateGreaterThanOrEqualTo(startDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.andSchStartDateLessThanOrEqualTo(endDate);
        }
        if(doctorFlows!=null&&doctorFlows.size()>0)
        {
            criteria.andDoctorFlowIn(doctorFlows);
        }
        return resultMapper.selectByExample(example);
    }

    private void autoArrangeDoctor(int all, String orgFlow, String sessionNumber, ResDoctor doctor,
                                   Map<String, Integer> indexMap, String startDate,
                                   SchRotation rotation, List<SchRotationDept> depts) {

        //删除学员所有已排班的结果
        delDoctorResult(doctor.getDoctorFlow(),rotation.getRotationFlow());
        Integer index = 0;
        SchAutoArrange arrange = getLastDeptFlow(orgFlow, sessionNumber, rotation.getRotationFlow(), com.pinde.core.common.GlobalConstant.FLAG_Y);
        if(arrange!=null&&StringUtil.isNotBlank(arrange.getRotationDeptFlow()))
        {
            index=indexMap.get(arrange.getRotationDeptFlow());
        }
        if(index==null)
            index=0;
        //已安排的轮转时间，并且轮转结束时间大于排班开始时间
        List<Map<String,String>> results=getResultByBeginStartDate(doctor.getDoctorFlow(),startDate);
        if(results==null) {
            results = new ArrayList<>();
        }
        //取已经安排的轮转的第一个时间
        if(results.size()>0)
        {
            Map<String,String> m=results.get(0);
            if(startDate.compareTo(m.get("startDate"))<0)
            {
                Map<String,String> m1=new HashMap<>();
                m1.put("endDate",DateUtil.addDate(startDate,-1));
                results.add(0,m1);
            }
        }
        //为学员添加轮转记录
        for(int j=index;j<all;j++)
        {
            addCycleRecord(depts.get(j),doctor,results,startDate);
        }
        for(int j=0;j<index;j++)
        {
            addCycleRecord(depts.get(j),doctor,results,startDate);
        }
        //设置学员已排班
        doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
        doctorBiz.editDoctor(doctor);
        index=index+1;
        if(index>=all)
            index=0;
        //更新下一个学员开始轮转的科室
        if(arrange==null)
            arrange=new SchAutoArrange();
        arrange.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        arrange.setOrgFlow(orgFlow);
        arrange.setSessionNumber(sessionNumber);
        arrange.setRotationDeptFlow(depts.get(index).getRecordFlow());
        arrange.setRotationFlow( rotation.getRotationFlow());
        updateLastDeptFlow(arrange);

    }

    private List<Map<String, String>> getResultByBeginStartDate(String doctorFlow, String startDate) {
        return autoArrangeExtMapper.getResultByBeginStartDate(doctorFlow,startDate);
    }
    public static void main(String[] args)
    {
        List<Map<String, String>> results=new ArrayList<>();
        Map<String, String> m=new HashMap<>();
        m.put("startDate","2017-08-01");
        m.put("endDate","2017-09-15");
        m.put("schMonth","1.5");
        m.put("old","true");
        results.add(0,m);
        m=new HashMap<>();
        m.put("startDate","2017-11-01");
        m.put("endDate","2017-11-30");
        m.put("old","true");
        results.add(1,m);
        addCycleRecord("2.0",results,"2017-09-01");
        addCycleRecord("1.0",results,"2017-09-01");
        addCycleRecord("1.5",results,"2017-09-01");
        System.err.println(JSON.toJSONString(results));
    }

    private static void addCycleRecord(String schMonth, List<Map<String, String>> results, String startDate) {
        //处理一下时间
        handleTime(results);
        String schStartDate="";
        String schEndDate="";
        if(results!=null&&results.size()==0)
        {
            schStartDate=startDate;
            schEndDate= DateUtil.addMonthForArrange(schStartDate,schMonth,false);
            Map<String, String> m=new HashMap<>();
            m.put("startDate",schStartDate);
            m.put("endDate",schEndDate);
            m.put("schMonth",schMonth);
            m.put("old","false");
            results.add(0,m);
        }else{
            int index=-1;
            for(int i=0;i<results.size();i++)
            {
                Map<String, String> m1=results.get(i);
                if(StringUtil.isNotBlank(m1.get("nextSchMonth"))) {
                    if (Double.valueOf(schMonth) <= Double.valueOf(m1.get("nextSchMonth"))) {
                        index=i;
                        break;
                    }
                }
            }
            if(index<0)
            {
                index=results.size()-1;
            }
            Map<String, String> m1=results.get(index);
            schStartDate=DateUtil.addDate(m1.get("endDate"),+1);
            schEndDate= DateUtil.addMonthForArrange(schStartDate,schMonth,false);
            Map<String, String> m=new HashMap<>();
            m.put("startDate",schStartDate);
            m.put("endDate",schEndDate);
            m.put("schMonth",schMonth);
            m.put("old","false");
            results.add(index+1,m);
        }
    }

    private void addCycleRecord(SchRotationDept dept, ResDoctor doctor, List<Map<String, String>> results, String startDate) {
        //处理一下时间
        handleTime(results);
        if(dept!=null&&doctor!=null)
        {
            Map<String,String> param=new HashMap<>();
            param.put("orgFlow", doctor.getOrgFlow());
            param.put("sessionNumber", doctor.getSessionNumber());
            param.put("doctorFlow", doctor.getDoctorFlow());
            param.put("rotationFlow", dept.getRotationFlow());
            param.put("schDeptFlow", dept.getSchDeptFlow());
            param.put("standardDeptId", dept.getStandardDeptId());
            SchRotationGroup group = groupBiz.readSchRotationGroup(dept.getGroupFlow());
            param.put("standardGroupFlow", group.getStandardGroupFlow());
            SchArrangeResult result = resultBiz.getResultBySchRotationDept(param);
            boolean isNew=true;
            if(result!=null)
            {
                ResDoctorSchProcess process=processBiz.searchByResultFlow(result.getResultFlow());
                if (process != null && !com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(process.getRecordStatus()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(process.getSchFlag()))
                {
                    isNew=false;
                }
            }
            if(isNew){
                String schStartDate="";
                String schEndDate="";
                String schMonth= dept.getSchMonth();
                if(results!=null&&results.size()==0)
                {
                    schStartDate=startDate;
                    schEndDate= DateUtil.addMonthForArrange(schStartDate,schMonth,false);
                    Map<String, String> m=new HashMap<>();
                    m.put("startDate",schStartDate);
                    m.put("endDate",schEndDate);
                    m.put("schMonth",schMonth);
                    results.add(0,m);
                }else{
                    int index=-1;
                    for(int i=0;i<results.size();i++)
                    {
                        Map<String, String> m1=results.get(i);
                        if(StringUtil.isNotBlank(m1.get("nextSchMonth"))) {
                            if (Double.valueOf(schMonth) <= Double.valueOf(m1.get("nextSchMonth"))) {
                                index=i;
                                break;
                            }
                        }
                    }
                    if(index<0)
                    {
                        index=results.size()-1;
                    }
                    Map<String, String> m1=results.get(index);
                    schStartDate=DateUtil.addDate(m1.get("endDate"),+1);
                    schEndDate= DateUtil.addMonthForArrange(schStartDate,schMonth,false);
                    Map<String, String> m=new HashMap<>();
                    m.put("startDate",schStartDate);
                    m.put("endDate",schEndDate);
                    m.put("schMonth",schMonth);
                    results.add(index+1,m);
                }
                result=new SchArrangeResult();
                String resultFlow=PkUtil.getUUID();
                result.setResultFlow(resultFlow);
                result.setArrangeFlow(resultFlow);
                result.setSchMonth(schMonth);
                result.setSchStartDate(schStartDate);
                result.setSchEndDate(schEndDate);
                result.setSchDeptFlow(dept.getSchDeptFlow());
                result.setSchDeptName(dept.getSchDeptName());
                result.setDeptFlow(dept.getDeptFlow());
                result.setDeptName(dept.getDeptName());
                result.setOrgFlow(doctor.getOrgFlow());
                result.setOrgName(doctor.getOrgName());
                result.setSessionNumber(doctor.getSessionNumber());
                result.setGroupFlow(dept.getGroupFlow());
                result.setStandardGroupFlow(group.getStandardGroupFlow());
                result.setStandardDeptId(dept.getStandardDeptId());
                result.setStandardDeptName(dept.getStandardDeptName());
                result.setSchYear(doctor.getSessionNumber());
                result.setIsRequired(dept.getIsRequired());
                String rotationFlow = dept.getRotationFlow();
                result.setRotationFlow(rotationFlow);
                if(rotationFlow.equals(doctor.getRotationFlow())){
                    result.setRotationName(doctor.getRotationName());
                }else if(rotationFlow.equals(doctor.getSecondRotationFlow())){
                    result.setRotationName(doctor.getSecondRotationName());
                }
                result.setIsRotation(com.pinde.core.common.GlobalConstant.FLAG_Y);
                result.setDoctorFlow(doctor.getDoctorFlow());
                result.setDoctorName(doctor.getDoctorName());
            }
            result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            if(isNew)
            {
                resultBiz.save(result);
            }else{
                resultBiz.update(result);
            }
        }
    }

    private static void handleTime(List<Map<String, String>> results) {
        if(results==null)
            results=new ArrayList<>();
        for(int k=0;k<results.size();k++)
        {
            Map<String, String> m = results.get(k);
            if(k<results.size()-1) {
                Map<String, String> nextM = results.get(k + 1);
                String startDate1=DateUtil.addDate(m.get("endDate"),1);
                String endDate=DateUtil.addDate(nextM.get("startDate"),-1);
                Map<String,String> map= new HashMap<>();
                map.put("startDate",startDate1);
                map.put("endDate",endDate);
                Double month = null;
                try {
                    month = TimeUtil.getMonthsBetween(map);
                } catch (ParseException e) {
                    logger.error("", e);
                }
                String schMonth = String.valueOf(Double.parseDouble(month + ""));
                m.put("nextSchMonth",schMonth);
            }
        }
    }

    private void updateLastDeptFlow(SchAutoArrange arrange) {
        if(StringUtil.isBlank(arrange.getRecordFlow()))
        {
            arrange.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(arrange,true);
             autoArrangeMapper.insertSelective(arrange);
        }else{
            GeneralMethod.setRecordInfo(arrange,false);
             autoArrangeMapper.updateByPrimaryKeySelective(arrange);
        }
    }

    private void delDoctorResult(String doctorFlow, String rotationFlow) {
        autoArrangeExtMapper.delProcess(doctorFlow,rotationFlow);
        autoArrangeExtMapper.delResult(doctorFlow,rotationFlow);
    }

    private SchAutoArrange getLastDeptFlow(String orgFlow, String sessionNumber, String rotationFlow, String recordStatus) {
        SchAutoArrangeExample example=new SchAutoArrangeExample();
        SchAutoArrangeExample.Criteria criteria=  example.createCriteria()
                .andOrgFlowEqualTo(orgFlow).andSessionNumberEqualTo(sessionNumber).andRotationFlowEqualTo(rotationFlow);
        if(StringUtil.isNotBlank(recordStatus))
        {
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        }
        List<SchAutoArrange> list=autoArrangeMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return  list.get(0);
        }
        return null;
    }

    private List<SchRotationDept> getSchRotationDepts(String orgFlow, String sessionNumber) {
        return autoArrangeExtMapper.getSchRotationDepts(orgFlow,sessionNumber);
    }

    private List<ResDoctor> getDoctorList(String orgFlow, String sessionNumber, String schFlag) {
        return autoArrangeExtMapper.getDoctorList(orgFlow,sessionNumber,schFlag);
    }
    public List<SysUser> getUserList(String orgFlow, String sessionNumber) {
        return autoArrangeExtMapper.getUserList(orgFlow,sessionNumber);
    }

    private List<SchRotation> getSchRotations(String orgFlow) {
        return autoArrangeExtMapper.getSchRotations(orgFlow);
    }

    private static Logger logger = LoggerFactory.getLogger(SchAutoArrangeBizImpl.class);

}
