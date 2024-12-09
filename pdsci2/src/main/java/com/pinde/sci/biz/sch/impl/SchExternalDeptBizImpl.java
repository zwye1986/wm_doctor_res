package com.pinde.sci.biz.sch.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.SchExternalDeptMapper;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.sci.dao.base.SchRotationMapper;
import com.pinde.sci.dao.sch.SchExternalDeptExtMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SchExternalDeptExample.Criteria;
import com.pinde.sci.model.res.ResDoctorExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchExternalDeptBizImpl implements ISchExternalDeptBiz {
	@Autowired
	private SchExternalDeptMapper externalDeptMapper;
	@Autowired
	private SchExternalDeptExtMapper externalDeptExtMapper;
	@Autowired
	private SchRotationMapper rotationMapper;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;

	@Override
	public List<SchExternalDept> getSchDeptExtDepts(String orgFlow, String schDeptFlow) {
		if(StringUtil.isNotBlank(schDeptFlow)) {
			SchExternalDeptExample example = new SchExternalDeptExample();
            Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andSchDeptFlowEqualTo(schDeptFlow);
			if(StringUtil.isNotBlank(orgFlow))
			{
				criteria.andOrgFlowEqualTo(orgFlow);
			}
			example.setOrderByClause(" STANDARD_DEPT_ID,START_DATE ");
			return externalDeptMapper.selectByExample(example);
		}
		return null;
	}
	@Override
	public List<SchExternalDept> getStandardSchDeptExtDepts(String standardDeptId, String schDeptFlow) {
		if(StringUtil.isNotBlank(schDeptFlow)) {
			SchExternalDeptExample example = new SchExternalDeptExample();
            Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andSchDeptFlowEqualTo(schDeptFlow);
			if(StringUtil.isNotBlank(standardDeptId))
			{
				criteria.andStandardDeptIdEqualTo(standardDeptId);
			}
			example.setOrderByClause(" STANDARD_DEPT_ID,START_DATE ");
			return externalDeptMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExtStudents(Map<String, String> params) {
		return externalDeptExtMapper.getExtStudents(params);
	}

	@Override
	public String delDoctorProcess(String resultFlow, String processFlow) {
		ResDoctorSchProcess process=processBiz.read(processFlow);
		if(process==null)
		{
			return "学员轮转信息不存在，请刷新页面后再试！";
		}
		SchArrangeResult result=resultBiz.readSchArrangeResult(resultFlow);
		if(result==null)
		{
			return "学员轮转计划不存在，请刷新页面后再试！";
		}
		if(StringUtil.isNotBlank(process.getTeacherUserFlow())&&StringUtil.isNotBlank(process.getHeadUserFlow()))
		{
			return "学员已入科，不得删除！";
		}
        process.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		processBiz.edit(process);
		resultBiz.saveSchArrangeResult(result);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
	}

	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExtRelBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;

	@Autowired
	private IResDoctorBiz doctorBiz;
	@Override
	public String addDoctorProcess(String recordFlow, String schDeptFlow, String[] recordFlows, String[] doctorFlows) throws Exception {
		SchRotationDept dept =schRotationDeptBiz.readSchRotationDept(recordFlow);//标准科室
		SchRotation rotation=rotationMapper.selectByPrimaryKey(dept.getRotationFlow());
		SchDept  schDept=schDeptBiz.readSchDept(schDeptFlow);//轮转科室
		List<SchExternalDept> externalDepts=getSchDeptExtDeptsByFlows(Arrays.asList(recordFlows));//开放科室的时间
		List<Map<String,String>> times=new ArrayList<>();
		List<SysUser> doctors=new ArrayList<>();
		for(String doctorFlow:doctorFlows)
		{
			SysUser user=userBiz.readSysUser(doctorFlow);
			doctors.add(user);
		}
		if(externalDepts!=null&&externalDepts.size()>0)
		{
			for(SchExternalDept d:externalDepts)
			{

				Map<String,Object> params=new HashMap<>();
				params.put("startDate",d.getStartDate());
				params.put("endDate",d.getEndDate());
				params.put("schDeptFlow",schDeptFlow);
				params.put("standardDeptId",dept.getStandardDeptId());
				int result=isHaveSelect(params);
				int peopleNum=Integer.valueOf(d.getPeopleNum());
				if((peopleNum-result)<doctorFlows.length)
				{
					return "轮转科室【"+d.getSchDeptName()+"】在开放时间"+d.getStartDate()+"~"+d.getEndDate()+"内，人数不足！无法添加！";
				}
				for(SysUser user:doctors)
				{
					params.put("doctorFlow",user.getUserFlow());
					ResDoctor doctor=doctorBiz.readDoctor(user.getUserFlow());
					if(doctor!=null) {
						params.put("rotationFlow", doctor.getRotationFlow());
						params.put("secondRotationFlow", doctor.getSecondRotationFlow());
					}
//					int count=resultBiz.checkResultDate(params);
//					if(count>0)
//					{
//						return "学员【"+user.getUserName()+"】在"+d.getStartDate()+"~"+d.getEndDate()+"时间段内已开始轮转，无法添加！";
//					}

				}
				Map<String,String>time=new HashMap<>();
				time.put("startDate",d.getStartDate());
				time.put("endDate",d.getEndDate());
				times.add(time);
			}
			SchExternalDept extDept=externalDepts.get(0);
			//连续的时间段组合成一个时间段
			List<Map<String,String>> newTimes= TimeUtil.getNewTimes(times);
			for(Map<String,String> map:newTimes)
			{
				for(SysUser user:doctors)
				{

					ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());

					String startDate = map.get("startDate");
					String endDate = map.get("endDate");
					SchArrangeResult result = new SchArrangeResult();
					String resultFlow = PkUtil.getUUID();
					result.setResultFlow(resultFlow);
					result.setArrangeFlow(resultFlow);
					result.setSchStartDate(startDate);
					result.setSchEndDate(endDate);
					int month = TimeUtil.getMonths(map);
					BigDecimal bg = new BigDecimal(month).setScale(1, BigDecimal.ROUND_HALF_UP);
					result.setSchMonth(String.valueOf(bg.doubleValue()));

					result.setDeptFlow(schDept.getDeptFlow());
					result.setDeptName(schDept.getDeptName());
					result.setSchDeptName(schDept.getSchDeptName());
					result.setSchDeptFlow(schDeptFlow);
					result.setStandardDeptId(dept.getStandardDeptId());
					result.setStandardDeptName(dept.getStandardDeptName());
					result.setStandardGroupFlow(dept.getGroupFlow());
					result.setDoctorFlow(user.getUserFlow());
					result.setDoctorName(user.getUserName());
					if(doctor!=null) {
						result.setSessionNumber(doctor.getSessionNumber());
					}
					result.setRotationFlow(rotation.getRotationFlow());
					result.setRotationName(rotation.getRotationName());
					result.setOrgFlow(extDept.getOrgFlow());
					result.setOrgName(extDept.getOrgName());
					result.setIsRequired(dept.getIsRequired());

					ResDoctorSchProcess process = new ResDoctorSchProcess();
					process.setStartDate(startDate);
					process.setEndDate(endDate);
                    process.setIsExternal(com.pinde.core.common.GlobalConstant.FLAG_Y);
					process.setSchStartDate(startDate);
					process.setSchEndDate(endDate);

					process.setDeptFlow(schDept.getDeptFlow());
					process.setDeptName(schDept.getDeptName());
					process.setSchDeptName(schDept.getSchDeptName());
					process.setSchDeptFlow(schDeptFlow);
					process.setSchResultFlow(String.valueOf(month));
					process.setSchResultFlow(resultFlow);
                    process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
                    process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
					process.setSchResultFlow(resultFlow);
					process.setUserFlow(user.getUserFlow());

					process.setOrgFlow(extDept.getOrgFlow());
					process.setOrgName(extDept.getOrgName());

					String schFlag = doctor.getSchFlag();
                    if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(schFlag)) {
                        doctor.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        doctor.setSelDeptFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
						doctorBiz.editDoctor(doctor);
					}
					resultBiz.save(result);
					processBiz.edit(process);
				}

			}
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@Override
	public List<SchRotationDept> getSchRotationDepts(String rotationFlow, String orgFlow, String sessionNumber) {
		SchRotationDeptExample example = new SchRotationDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andRotationFlowEqualTo(rotationFlow).andOrgFlowEqualTo(orgFlow).andSessionNumberEqualTo(sessionNumber);
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}


	private List<SchExternalDept> getSchDeptExtDeptsByFlows(List<String> recordFlows) {
		if(recordFlows!=null&&recordFlows.size()>0) {
			SchExternalDeptExample example = new SchExternalDeptExample();
            Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andRecordFlowIn(recordFlows);
			example.setOrderByClause(" STANDARD_DEPT_ID,START_DATE ");
			return externalDeptMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public int checkTime(SchExternalDept schExternalDept) {
		return externalDeptExtMapper.checkTime(schExternalDept);
	}

	@Override
	public int save(SchExternalDept schExternalDept) {
		if(StringUtil.isNotBlank(schExternalDept.getRecordFlow()))
		{
			GeneralMethod.setRecordInfo(schExternalDept,false);
			return  externalDeptMapper.updateByPrimaryKeySelective(schExternalDept);
		}else{
			schExternalDept.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(schExternalDept,true);
			return  externalDeptMapper.insertSelective(schExternalDept);
		}
	}

	@Override
	public int saveTimes(List<Map<String, String>> moreTimes, SchExternalDept schExternalDept) {
		int count=0;
		if(moreTimes!=null&&moreTimes.size()>0)
		{
			for(Map<String,String>map:moreTimes)
			{

				String startDate = map.get("startDate");
				String endDate = map.get("endDate");
				schExternalDept.setStartDate(startDate);
				schExternalDept.setEndDate(endDate);
				schExternalDept.setRecordFlow("");
				count+=save(schExternalDept);
			}
		}
		return count;
	}

	@Override
	public SchExternalDept readByFlow(String flow) {
		return externalDeptMapper.selectByPrimaryKey(flow);
	}

	@Override
	public int isHaveSelect(Map<String, Object> time) {
		return externalDeptExtMapper.isHaveSelect(time);
	}

	@Override
	public int delExternalDept(String[] recordFlows) {
		int count=0;
		if(recordFlows!=null&&recordFlows.length>0)
		{
			for(String recordFlow:recordFlows)
			{

				SchExternalDept schExternalDept=readByFlow(recordFlow);
                schExternalDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				count+=save(schExternalDept);
			}
		}
		return count;
	}

	@Override
	public List<SchRotation> changRotation(String trainingSpeId) {
		SchRotationExample example=new SchRotationExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andPublishFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andSpeIdEqualTo(trainingSpeId);
		return rotationMapper.selectByExample(example);
	}

	@Override
	public List<SchRotationDept> getSchRotationDepts(String rotationFlow) {
		SchRotationDeptExample example = new SchRotationDeptExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgFlowIsNull()
		.andRotationFlowEqualTo(rotationFlow);
		example.setOrderByClause("ORDINAL");
		return rotationDeptMapper.selectByExample(example);
	}

	@Override
	public List<SchDept> getNotSelfSchDeptByDeptIdAndExternal(Map<String, String> params) {
		return externalDeptExtMapper.getNotSelfSchDeptByDeptIdAndExternal(params);
	}
	@Override
	public List<ResDoctorExt> getStudents(Map<String, String> params) {
		return externalDeptExtMapper.getStudents(params);
	}
}
