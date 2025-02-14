package com.pinde.res.biz.stdp.impl;

import com.pinde.app.common.GlobalUtil;
import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.eval.IEvalAppBiz;
import com.pinde.res.biz.jswjw.IJswjwHospitalSupervisioBiz;
import com.pinde.res.biz.stdp.ICfgBiz;
import com.pinde.res.biz.stdp.IResActivityBiz;
import com.pinde.res.biz.stdp.IResActivityTargetBiz;
import com.pinde.res.ctrl.hbres.ActivityImageFileForm;
import com.pinde.res.dao.stdp.ext.TeachingActivityInfoExtMapper;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResActivityBizImpl implements IResActivityBiz {

	private static Logger logger = LoggerFactory.getLogger(ResActivityBizImpl.class);


	@Autowired
	private TeachingActivityInfoExtMapper activityInfoExtMapper;
	@Autowired
	private TeachingActivityEvalMapper activityEvalMapper;
	@Autowired
	private TeachingActivityInfoMapper activityInfoMapper;
	@Autowired
	private TeachingActivityFormValueMapper activityFormValueMapper;
	@Autowired
	private TeachingActivityTargetMapper targetMapper;
	@Autowired
	private TeachingActivityResultMapper resultMapper;
	@Autowired
	private PubFileMapper pubFileMapper;
	@Autowired
	private IResActivityTargetBiz targetBiz;
	@Autowired
	private IEvalAppBiz evalAppBiz;
	@Resource
	private SysCfgMapper cfgMapper;
	@Autowired
	private SysCfgMapper sysCfgMapper;
	@Autowired
	IJswjwHospitalSupervisioBiz hospitalSupervisioBiz;
	@Autowired
	private SysDeptMapper deptMapper;
	@Autowired
	private SysOrgMapper orgMapper;
	@Autowired
	protected ICfgBiz cfgBiz;

	@Override
	public List<Map<String, Object>> findActivityList(Map<String, String> param) {
		List<Map<String, Object>> list=activityInfoExtMapper.findActivityList(param);
		return list;
	}

	@Override
	public Map<String, Object> readActivity(String activityFlow) {
		return activityInfoExtMapper.readActivity(activityFlow);
	}
	@Override
	public Map<String, Object> getDeptActivityStatisticsMap(String deptFlow,String startTime,String endTime) {

		Map<String, Object> map=new HashMap<>();
		List<Map<String,String>> datas=activityInfoExtMapper.getDeptActivityStatisticsMap(deptFlow,startTime,endTime);
		if(datas!=null)
		{
			for(Map<String,String> d:datas)
			{
				map.put(d.get("typeId"),d.get("qty"));
			}
		}
		return map;
	}

	@Override
	public int countByActivity(String activityFlow) {
		TeachingActivityResultExample example = new TeachingActivityResultExample();
		TeachingActivityResultExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(activityFlow)){
			criteria.andActivityFlowEqualTo(activityFlow);
		}
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return resultMapper.countByExample(example);
	}

	@Override
	public TeachingActivityResult readRegistInfo(String activityFlow, String userFlow) {
		TeachingActivityResultExample example=new TeachingActivityResultExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andActivityFlowEqualTo(activityFlow)
				.andUserFlowEqualTo(userFlow);
		List<TeachingActivityResult> list=resultMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<Map<String, Object>> readActivityResults(String activityFlow, String searchStr) {
		return activityInfoExtMapper.readActivityResults(activityFlow,searchStr);
	}
	@Override
	public List<Map<String, Object>> readActivityResultsByType(String activityFlow, String searchStr, String typeId) {
		return activityInfoExtMapper.readActivityResultsByType(activityFlow,searchStr,typeId);
	}

	@Override
	public List<TeachingActivityEval> readActivityResultEvals(String resultFlow) {
		TeachingActivityEvalExample example=new TeachingActivityEvalExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andResultFlowEqualTo(resultFlow);
		return activityEvalMapper.selectByExample(example);
	}

	@Override
	public TeachingActivityInfo readActivityInfo(String activityFlow) {
		return activityInfoMapper.selectByPrimaryKey(activityFlow);
	}


	@Override
	public TeachingActivityResult readResult(String resultFlow) {
		return resultMapper.selectByPrimaryKey(resultFlow);
	}


	@Override
	public int checkJoin2(String activityFlow, String userFlow) {
		return activityInfoExtMapper.checkJoin2(activityFlow,userFlow);
	}

	@Override
	public int saveEvalInfo(List<TeachingActivityEval> evals, String resultFlow,String userFlow) {
		if(evals!=null&&evals.size()>0)
		{
			TeachingActivityResult result=readResult(resultFlow);
			if(result==null)
				return 0;
			double sum=0;
			int remarks=0;
			for(TeachingActivityEval eval:evals)
			{
				TeachingActivityTarget target=targetMapper.selectByPrimaryKey(eval.getTargetFlow());
				if(target==null)
					return 0;
				if(eval.getEvalScore()==null)
				{
					return 0;
				}
				eval.setTargetName(target.getTargetName());
				eval.setOrdinal(target.getOrdinal());
				eval.setUserFlow(userFlow);
				eval.setResultFlow(resultFlow);
                if (eval.getIsText().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
					sum+=eval.getEvalScore();
				}else {
					remarks=remarks+1;
				}
			}
			for(TeachingActivityEval eval:evals) {
				saveResultEval(eval, userFlow);
			}
			int count=evals.size()-remarks;
			BigDecimal evalScore=new BigDecimal(sum/count).setScale(0,BigDecimal.ROUND_HALF_UP);
			result.setEvalScore(evalScore);
			return saveResult(result,userFlow);

		}
		return 0;
	}

	@Override
	public  int saveResult(TeachingActivityResult info,String userFlow) {
		if (StringUtil.isBlank(info.getResultFlow())) {
			info.setResultFlow(PkUtil.getUUID());
            info.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			info.setCreateUserFlow(userFlow);
			info.setCreateTime(DateUtil.getCurrDateTime());
			info.setModifyUserFlow(userFlow);
			info.setModifyTime(DateUtil.getCurrDateTime());
			return resultMapper.insertSelective(info);
		} else {
            info.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			info.setModifyUserFlow(userFlow);
			info.setModifyTime(DateUtil.getCurrDateTime());
			return resultMapper.updateByPrimaryKeySelective(info);
		}
	}

	@Override
	public  int saveResultEval(TeachingActivityEval eval,String userFlow) {
		if (StringUtil.isBlank(eval.getEvalFlow())) {
			eval.setEvalFlow(PkUtil.getUUID());
            eval.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			eval.setCreateUserFlow(userFlow);
			eval.setCreateTime(DateUtil.getCurrDateTime());
			eval.setModifyUserFlow(userFlow);
			eval.setModifyTime(DateUtil.getCurrDateTime());
			return activityEvalMapper.insertSelective(eval);
		} else {
            eval.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			eval.setModifyUserFlow(userFlow);
			eval.setModifyTime(DateUtil.getCurrDateTime());
			return activityEvalMapper.updateByPrimaryKeySelective(eval);
		}
	}

	@Override
	public List<Map<String, String>> parseImageXml(String content) throws DocumentException {
		if(StringUtil.isNotBlank(content)) {
			Document document = DocumentHelper.parseText(content);
			Element elem = document.getRootElement();
			List<Element> ec = elem.elements();
			List<Map<String, String>> imageList = new ArrayList<Map<String, String>>();
			for (Element element : ec) {
				Map<String, String> imageMap = new HashMap<String, String>();
				String imageFlow = element.attributeValue("imageFlow");
				imageMap.put("imageFlow", imageFlow);
				List<Element> items = element.elements();
				for (Element item : items) {
					String itemName = item.getName();
					String itemText = item.getTextTrim();
					imageMap.put(itemName, itemText);
				}
				imageList.add(imageMap);
			}
			return imageList;
		}
		return null;
	}

	@Override
	public int saveActivityInfo2(TeachingActivityInfo info,SysUser user) {
		Map<String,String> param = new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		SysCfgExample example = new SysCfgExample();
		SysCfgExample.Criteria criteria = example.createCriteria();
		List<String> userRoleList = new ArrayList();
		String roleId = info.getRoleId();
		if (roleId!=null &&roleId!= "") {
			if ("Head".equals(roleId)) {
				userRoleList.add("res_head_role_flow");
			} else if ("Teacher".equals(roleId)) {
				userRoleList.add("res_teacher_role_flow");
			} else if ("Seretary".equals(roleId)) {
				userRoleList.add("res_secretary_role_flow");
			} else if ("TeachingHead".equals(roleId)) {
				userRoleList.add("res_teaching_head_role_flow");
			} else if ("TeachingSeretary".equals(roleId)) {
				userRoleList.add("res_teaching_secretary_role_flow");
			}
			criteria.andCfgCodeIn(userRoleList);
			SysCfg sysCfg = sysCfgMapper.selectByExample(example).get(0);     //获取该用户当前角色信息
			List<SysUserRole> subRoleList = evalAppBiz.getSysUserRole(user.getUserFlow()); //获取该用户下的所有角色信息
			List<ActivityCfgExt> list = activityEvalMapper.searchActvity(param);         //获取该基地下的所有教学活动配置
			String subName="";
			for (ActivityCfgExt actCfg: list) {                                //嵌套循环基地下的教学活动和用户角色
				if(actCfg.getSubmitRole().equals(sysCfg.getCfgValue())){   //若教学活动发起人角色和当前用户角色相同时将当前基地下的提交人和审核人角色信息存在该教学活动下
					info.setSubmitRole(actCfg.getSubmitRole());
					info.setAuditRole(actCfg.getAuditRole());
					for (SysUserRole userRole:subRoleList) {
						if("audit".equals(info.getActivityStatus()) || StringUtil.isBlank(info.getActivityStatus())) {
							if (actCfg.getAuditRole().contains(userRole.getRoleFlow())) {  //判断该角色发起的活动审批角色是否包含该用户所拥有的角色，有的话就直接审核通过
								info.setActivityStatus("pass");
							}else{
								info.setActivityStatus("audit");
							}
						}
					}
				}else if(StringUtil.isBlank(info.getActivityStatus())){
					info.setActivityStatus("audit");
				}
				if("带教老师角色".equals(actCfg.getSubRoleName())){
					subName=subName+"Teacher";
				}else if("科主任角色".equals(actCfg.getSubRoleName())){
					subName=subName+"Head";
				}else if("科秘角色".equals(actCfg.getSubRoleName())){
					subName=subName+"Seretary";
				}else if("教学秘书角色".equals(actCfg.getSubRoleName())){
					subName=subName+"TeachingSeretary";
				}else if("教学主任角色".equals(actCfg.getSubRoleName())){
					subName=subName+"TeachingHead";
				}
			}
			if(("undefined".equals(info.getSubmitRole())||StringUtil.isBlank(info.getSubmitRole()))&&(!subName.contains(roleId))){
				return -1;
			}
		}

		if (StringUtil.isBlank(info.getActivityFlow())) {

			//判断教学活动新增判断时间是否符合要求
            if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(info.getRecordStatus())) {
				String startDateTime = info .getStartTime();
				String currDate = DateUtil.getCurrDateTime("yyyy-MM-dd");
				String currTime = DateUtil.getCurrDateTime("HH:mm:ss");
				String keyOfDay = "jsres_" + user.getOrgFlow() + "_org_activity_add_day";
				String keyOfTime = "jsres_" + user.getOrgFlow() + "_org_activity_add_time";
				String day = jsresPowerCfgMap(keyOfDay);
				String time = jsresPowerCfgMap(keyOfTime);
				if (StringUtil.isNotBlank(day) && StringUtil.isNotBlank(time)) {
					String startDate = startDateTime.substring(0, 10).trim();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate1 = null;
					try {
						startDate1 = sdf.parse(currDate);
					} catch (ParseException e) {
						logger.error("", e);
					}
					Date endDate = null;
					try {
						endDate = sdf.parse(startDate);
					} catch (ParseException e) {
						logger.error("", e);
					}
					Calendar startCal = Calendar.getInstance();
					startCal.setTime(startDate1);
					Calendar endCal = Calendar.getInstance();
					endCal.setTime(endDate);
					long days = 0;
					if (startCal.before(endCal)) {
						while (startCal.before(endCal)) {
							startCal.add(Calendar.DAY_OF_MONTH, 1);
							days++;
						}
					}
					if (endCal.before(startCal)) {
						while (endCal.before(startCal)) {
							endCal.add(Calendar.DAY_OF_MONTH, 1);
							days--;
						}
					}
					if ((days == new Long(day) && currTime.compareTo(time) > 0) || days < new Long(day)) {
						return -2;
					}
				}
			}

			info.setActivityFlow(PkUtil.getUUID());
            info.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			info.setCreateUserFlow(user.getUserFlow());
			info.setCreateTime(DateUtil.getCurrDateTime());
			info.setModifyUserFlow(user.getUserFlow());
			info.setModifyTime(DateUtil.getCurrDateTime());

			//创建院级督导的项目
			ResHospSupervSubject subject = new ResHospSupervSubject();
			subject.setSubjectFlow(PkUtil.getUUID());
			subject.setSubjectName(info.getActivityName());
			subject.setActivityFlow(info.getActivityFlow());
			subject.setActivityName(info.getActivityName());
			SysOrg org=orgMapper.selectByPrimaryKey(info.getOrgFlow());
			if (null !=org){
				subject.setOrgFlow(org.getOrgFlow());
				subject.setOrgName(org.getOrgName());
				if (null !=org && StringUtil.isNotBlank(org.getBaseCode())){
					subject.setBaseCode(org.getBaseCode());
				}
			}
			subject.setOrderAction("free");
			subject.setCreateTime(DateUtil.getCurrDateTime2());
			subject.setCreateUserFlow(user.getUserFlow());
			subject.setSubjectYear(DateUtil.getYear());
			SysDept sysDept = deptMapper.selectByPrimaryKey(info.getDeptFlow());
			if (sysDept != null) {
				subject.setDeptFlow(info.getDeptFlow());
				subject.setDeptName(sysDept.getDeptName());
			}
			subject.setInspectionType(info.getActivityTypeId());
			subject.setTeachFlow(user.getUserFlow());
			subject.setTeachName(user.getUserName());
            subject.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			subject.setActualSpeaker(info.getRealitySpeaker());
			subject.setActivityStartTime(info.getStartTime());
			subject.setActivityEndTime(info.getEndTime());
			hospitalSupervisioBiz.insertHospSupervisio(subject);
			return activityInfoMapper.insertSelective(info);
		} else {
			info.setModifyUserFlow(user.getUserFlow());
			info.setModifyTime(DateUtil.getCurrDateTime());

			//修改督导的信息
			ResHospSupervSubject subject = hospitalSupervisioBiz.selectHospSupervisioByActivityFlow(info.getActivityFlow());
			if (null != subject){
				subject.setModifyTime(DateUtil.getCurrDateTime2());
				subject.setModifyUserFlow(user.getUserFlow());
				subject.setActualSpeaker(info.getRealitySpeaker());
				subject.setInspectionType(info.getActivityTypeId());
				subject.setSubjectName(info.getActivityName());
				subject.setActivityName(info.getActivityName());
				subject.setActivityStartTime(info.getStartTime());
				subject.setActivityEndTime(info.getEndTime());
				subject.setActualSpeaker(info.getRealitySpeaker());
				if (StringUtil.isNotBlank(info.getDeptFlow())){
					SysDept sysDept = deptMapper.selectByPrimaryKey(info.getDeptFlow());
					if (sysDept != null) {
						subject.setDeptFlow(info.getDeptFlow());
						subject.setDeptName(sysDept.getDeptName());
					}
				}
				hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject);
			}

			return activityInfoMapper.updateByPrimaryKeySelective(info);
		}
	}

	@Override
	public int saveActivityInfo(TeachingActivityInfo info,SysUser user) {
		Map<String,String> param = new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		SysCfgExample example = new SysCfgExample();
		SysCfgExample.Criteria criteria = example.createCriteria();
		List<String> userRoleList = new ArrayList();
		String roleId = info.getRoleId();
		if (roleId!=null &&roleId!= "") {
			if ("Head".equals(roleId)) {
				userRoleList.add("res_head_role_flow");
			} else if ("Teacher".equals(roleId)) {
				userRoleList.add("res_teacher_role_flow");
			} else if ("Seretary".equals(roleId)) {
				userRoleList.add("res_secretary_role_flow");
			} else if ("TeachingHead".equals(roleId)) {
				userRoleList.add("res_teaching_head_role_flow");
			} else if ("TeachingSeretary".equals(roleId)) {
				userRoleList.add("res_teaching_secretary_role_flow");
			}
			criteria.andCfgCodeIn(userRoleList);
			SysCfg sysCfg = sysCfgMapper.selectByExample(example).get(0);     //获取该用户当前角色信息
		List<SysUserRole> subRoleList = evalAppBiz.getSysUserRole(user.getUserFlow()); //获取该用户下的所有角色信息
		List<ActivityCfgExt> list = activityEvalMapper.searchActvity(param);         //获取该基地下的所有教学活动配置
		String subName="";
		for (ActivityCfgExt actCfg: list) {                                //嵌套循环基地下的教学活动和用户角色
			if(actCfg.getSubmitRole().equals(sysCfg.getCfgValue())){   //若教学活动发起人角色和当前用户角色相同时将当前基地下的提交人和审核人角色信息存在该教学活动下
				info.setSubmitRole(actCfg.getSubmitRole());
				info.setAuditRole(actCfg.getAuditRole());
				for (SysUserRole userRole:subRoleList) {
					if("audit".equals(info.getActivityStatus()) || StringUtil.isBlank(info.getActivityStatus())) {
						if (actCfg.getAuditRole().contains(userRole.getRoleFlow())) {  //判断该角色发起的活动审批角色是否包含该用户所拥有的角色，有的话就直接审核通过
							info.setActivityStatus("pass");
						}else{
							info.setActivityStatus("audit");
						}
					}
				}
			}else if(StringUtil.isBlank(info.getActivityStatus())){
				info.setActivityStatus("audit");
			}
			if("带教老师角色".equals(actCfg.getSubRoleName())){
				subName=subName+"Teacher";
			}else if("科主任角色".equals(actCfg.getSubRoleName())){
				subName=subName+"Head";
			}else if("科秘角色".equals(actCfg.getSubRoleName())){
				subName=subName+"Seretary";
			}else if("教学秘书角色".equals(actCfg.getSubRoleName())){
				subName=subName+"TeachingSeretary";
			}else if("教学主任角色".equals(actCfg.getSubRoleName())){
				subName=subName+"TeachingHead";
			}
		}
		if(("undefined".equals(info.getSubmitRole())||StringUtil.isBlank(info.getSubmitRole()))&&(!subName.contains(roleId))){
			return -1;
		 }
		}

		if (StringUtil.isBlank(info.getActivityFlow())) {
			info.setActivityFlow(PkUtil.getUUID());
            info.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			info.setCreateUserFlow(user.getUserFlow());
			info.setCreateTime(DateUtil.getCurrDateTime());
			info.setModifyUserFlow(user.getUserFlow());
			info.setModifyTime(DateUtil.getCurrDateTime());

			//创建院级督导的项目
			ResHospSupervSubject subject = new ResHospSupervSubject();
			subject.setSubjectFlow(PkUtil.getUUID());
			subject.setSubjectName(info.getActivityName());
			subject.setActivityFlow(info.getActivityFlow());
			subject.setActivityName(info.getActivityName());
			SysOrg org=orgMapper.selectByPrimaryKey(info.getOrgFlow());
			if (null !=org){
				subject.setOrgFlow(org.getOrgFlow());
				subject.setOrgName(org.getOrgName());
				if (null !=org && StringUtil.isNotBlank(org.getBaseCode())){
					subject.setBaseCode(org.getBaseCode());
				}
			}
			subject.setOrderAction("free");
			subject.setCreateTime(DateUtil.getCurrDateTime2());
			subject.setCreateUserFlow(user.getUserFlow());
			subject.setSubjectYear(DateUtil.getYear());
			SysDept sysDept = deptMapper.selectByPrimaryKey(info.getDeptFlow());
			if (sysDept != null) {
				subject.setDeptFlow(info.getDeptFlow());
				subject.setDeptName(sysDept.getDeptName());
			}
			subject.setInspectionType(info.getActivityTypeId());
			subject.setTeachFlow(user.getUserFlow());
			subject.setTeachName(user.getUserName());
            subject.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			subject.setActualSpeaker(info.getRealitySpeaker());
			subject.setActivityStartTime(info.getStartTime());
			subject.setActivityEndTime(info.getEndTime());
			hospitalSupervisioBiz.insertHospSupervisio(subject);
			return activityInfoMapper.insertSelective(info);
		} else {
			info.setModifyUserFlow(user.getUserFlow());
			info.setModifyTime(DateUtil.getCurrDateTime());

			//修改督导的信息
			ResHospSupervSubject subject = hospitalSupervisioBiz.selectHospSupervisioByActivityFlow(info.getActivityFlow());
			if (null != subject){
				subject.setModifyTime(DateUtil.getCurrDateTime2());
				subject.setModifyUserFlow(user.getUserFlow());
				subject.setActualSpeaker(info.getRealitySpeaker());
				subject.setInspectionType(info.getActivityTypeId());
				subject.setSubjectName(info.getActivityName());
				subject.setActivityName(info.getActivityName());
				subject.setActivityStartTime(info.getStartTime());
				subject.setActivityEndTime(info.getEndTime());
				subject.setActualSpeaker(info.getRealitySpeaker());
				if (StringUtil.isNotBlank(info.getDeptFlow())){
					SysDept sysDept = deptMapper.selectByPrimaryKey(info.getDeptFlow());
					if (sysDept != null) {
						subject.setDeptFlow(info.getDeptFlow());
						subject.setDeptName(sysDept.getDeptName());
					}
				}
				hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject);
			}

			return activityInfoMapper.updateByPrimaryKeySelective(info);
		}
	}

	@Override
	public String jsresPowerCfgMap (String cfgCode){
		JsresPowerCfgMapper jsresPowerCfgMapper = SpringUtil.getBean(JsresPowerCfgMapper.class);
		JsresPowerCfg jsresPowerCfg = jsresPowerCfgMapper.selectByPrimaryKey(cfgCode);
		String value = "";
		if(jsresPowerCfg != null){
			value = jsresPowerCfg.getCfgValue();
		}
		return value;
	}

	@Override
	public int saveActivityInfo2(TeachingActivityInfo info,SysUser user,String recordFlow) {
		Map<String,String> param = new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		SysCfgExample example = new SysCfgExample();
		SysCfgExample.Criteria criteria = example.createCriteria();
		List<String> userRoleList = new ArrayList();
		String roleId = info.getRoleId();
		if (roleId!=null &&roleId!= "") {
			if ("Head".equals(roleId)) {
				userRoleList.add("res_head_role_flow");
			} else if ("Teacher".equals(roleId)) {
				userRoleList.add("res_teacher_role_flow");
			} else if ("Seretary".equals(roleId)) {
				userRoleList.add("res_secretary_role_flow");
			} else if ("TeachingHead".equals(roleId)) {
				userRoleList.add("res_teaching_head_role_flow");
			} else if ("TeachingSeretary".equals(roleId)) {
				userRoleList.add("res_teaching_secretary_role_flow");
			}
			criteria.andCfgCodeIn(userRoleList);
			SysCfg sysCfg = sysCfgMapper.selectByExample(example).get(0);     //获取该用户当前角色信息
			List<SysUserRole> subRoleList = evalAppBiz.getSysUserRole(user.getUserFlow()); //获取该用户下的所有角色信息
			List<ActivityCfgExt> list = activityEvalMapper.searchActvity(param);         //获取该基地下的所有教学活动配置
			String subName="";
			for (ActivityCfgExt actCfg: list) {                                //嵌套循环基地下的教学活动和用户角色
				if(actCfg.getSubmitRole().equals(sysCfg.getCfgValue())){   //若教学活动发起人角色和当前用户角色相同时将当前基地下的提交人和审核人角色信息存在该教学活动下
					info.setSubmitRole(actCfg.getSubmitRole());
					info.setAuditRole(actCfg.getAuditRole());
					for (SysUserRole userRole:subRoleList) {
						if("audit".equals(info.getActivityStatus()) || StringUtil.isBlank(info.getActivityStatus())) {
							if (actCfg.getAuditRole().contains(userRole.getRoleFlow())) {  //判断该角色发起的活动审批角色是否包含该用户所拥有的角色，有的话就直接审核通过
								info.setActivityStatus("pass");
							}else{
								info.setActivityStatus("audit");
							}
						}
					}
				}else if(StringUtil.isBlank(info.getActivityStatus())){
					info.setActivityStatus("audit");
				}
				if("带教老师角色".equals(actCfg.getSubRoleName())){
					subName=subName+",Teacher";
				}else if("科主任角色".equals(actCfg.getSubRoleName())){
					subName=subName+",Head";
				}else if("科秘角色".equals(actCfg.getSubRoleName())){
					subName=subName+",Seretary";
				}else if("教学主任角色".equals(actCfg.getSubRoleName())){
					subName=subName+",TeachingHead";
				}else if("教学秘书角色".equals(actCfg.getSubRoleName())){
					subName=subName+",TeachingSeretary";
				}
			}
			String[] submitName = subName.split(",");
			List<String> submitList = Arrays.asList(submitName);
			if(("undefined".equals(info.getSubmitRole())||StringUtil.isBlank(info.getSubmitRole()))&&(!submitList.contains(roleId))){
				return -1;
			}
		}
		if (StringUtil.isBlank(info.getActivityFlow())) {

			//判断教学活动新增判断时间是否符合要求
            if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(info.getRecordStatus())) {
				String startDateTime = info .getStartTime();
				String currDate = DateUtil.getCurrDateTime("yyyy-MM-dd");
				String currTime = DateUtil.getCurrDateTime("HH:mm:ss");
				String keyOfDay = "jsres_" + user.getOrgFlow() + "_org_activity_add_day";
				String keyOfTime = "jsres_" + user.getOrgFlow() + "_org_activity_add_time";
				String day = jsresPowerCfgMap(keyOfDay);
				String time = jsresPowerCfgMap(keyOfTime);
				if (StringUtil.isNotBlank(day) && StringUtil.isNotBlank(time)) {
					String startDate = startDateTime.substring(0, 10).trim();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date startDate1 = null;
					try {
						startDate1 = sdf.parse(currDate);
					} catch (ParseException e) {
						logger.error("", e);
					}
					Date endDate = null;
					try {
						endDate = sdf.parse(startDate);
					} catch (ParseException e) {
						logger.error("", e);
					}
					Calendar startCal = Calendar.getInstance();
					startCal.setTime(startDate1);
					Calendar endCal = Calendar.getInstance();
					endCal.setTime(endDate);
					long days = 0;
					if (startCal.before(endCal)) {
						while (startCal.before(endCal)) {
							startCal.add(Calendar.DAY_OF_MONTH, 1);
							days++;
						}
					}
					if (endCal.before(startCal)) {
						while (endCal.before(startCal)) {
							endCal.add(Calendar.DAY_OF_MONTH, 1);
							days--;
						}
					}
					if ((days == new Long(day) && currTime.compareTo(time) > 0) || days < new Long(day)) {
						return -2;
					}
				}
			}

//			info.setActivityFlow(PkUtil.getUUID());
			if(StringUtil.isNotBlank(recordFlow)){
				info.setActivityFlow(recordFlow);
			}else{
				info.setActivityFlow(PkUtil.getUUID());
			}
            info.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			info.setCreateUserFlow(user.getUserFlow());
			info.setCreateTime(DateUtil.getCurrDateTime());
			info.setModifyUserFlow(user.getUserFlow());
			info.setModifyTime(DateUtil.getCurrDateTime());

			//创建院级督导的项目
			ResHospSupervSubject subject = new ResHospSupervSubject();
			subject.setSubjectFlow(PkUtil.getUUID());
			subject.setSubjectName(info.getActivityName());
			subject.setActivityFlow(info.getActivityFlow());
			subject.setActivityName(info.getActivityName());
			SysOrg org=orgMapper.selectByPrimaryKey(info.getOrgFlow());
			if (null !=org){
				subject.setOrgFlow(org.getOrgFlow());
				subject.setOrgName(org.getOrgName());
				if (null !=org && StringUtil.isNotBlank(org.getBaseCode())){
					subject.setBaseCode(org.getBaseCode());
				}
			}
			subject.setOrderAction("free");
			subject.setCreateTime(DateUtil.getCurrDateTime2());
			subject.setCreateUserFlow(user.getUserFlow());
			subject.setSubjectYear(DateUtil.getYear());
			SysDept sysDept = deptMapper.selectByPrimaryKey(info.getDeptFlow());
			if (sysDept != null) {
				subject.setDeptFlow(info.getDeptFlow());
				subject.setDeptName(sysDept.getDeptName());
			}
			subject.setInspectionType(info.getActivityTypeId());
			subject.setTeachFlow(user.getUserFlow());
			subject.setTeachName(user.getUserName());
            subject.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			subject.setActualSpeaker(info.getRealitySpeaker());
			subject.setActivityStartTime(info.getStartTime());
			subject.setActivityEndTime(info.getEndTime());
			hospitalSupervisioBiz.insertHospSupervisio(subject);
			return activityInfoMapper.insertSelective(info);
		} else {
			info.setModifyUserFlow(user.getUserFlow());
			info.setModifyTime(DateUtil.getCurrDateTime());

			//修改督导的信息
			ResHospSupervSubject subject = hospitalSupervisioBiz.selectHospSupervisioByActivityFlow(info.getActivityFlow());
			if (null != subject){
				subject.setModifyTime(DateUtil.getCurrDateTime2());
				subject.setModifyUserFlow(user.getUserFlow());
				subject.setActualSpeaker(info.getRealitySpeaker());
				subject.setInspectionType(info.getActivityTypeId());
				subject.setSubjectName(info.getActivityName());
				subject.setActivityName(info.getActivityName());
				subject.setActivityStartTime(info.getStartTime());
				subject.setActivityEndTime(info.getEndTime());
				subject.setActualSpeaker(info.getRealitySpeaker());
				if (StringUtil.isNotBlank(info.getDeptFlow())){
					SysDept sysDept = deptMapper.selectByPrimaryKey(info.getDeptFlow());
					if (sysDept != null) {
						subject.setDeptFlow(info.getDeptFlow());
						subject.setDeptName(sysDept.getDeptName());
					}
				}
				hospitalSupervisioBiz.updateHospSupervisioBySubjectFlow(subject);
			}

			return activityInfoMapper.updateByPrimaryKeySelective(info);
		}
	}

	@Override	public int checkTime(TeachingActivityInfo activity) {
		return activityInfoExtMapper.checkTime(activity);
	}

	@Override
	public int addActivityInfo(TeachingActivityInfo activity, SysUser userinfo, List<TeachingActivityTarget> targets, List<TeachingActivityFormValue> formValues) {
		int c=saveActivityInfo2(activity,userinfo);
		if(c==1) {
			saveActivityTarget(activity, userinfo, targets);
			saveActivityForm(activity, userinfo, formValues);
		}
		return c;
	}

	@Override
	public int addActivityInfo2(TeachingActivityInfo activity, SysUser userinfo, List<TeachingActivityTarget> targets, List<TeachingActivityFormValue> formValues, String  recordFlow) {
		int c=saveActivityInfo2(activity,userinfo,recordFlow);
		if(c==1) {
			saveActivityTarget(activity, userinfo, targets);
			saveActivityForm(activity, userinfo, formValues);
		}
		return c;
	}

	private void saveActivityForm(TeachingActivityInfo activity, SysUser userinfo, List<TeachingActivityFormValue> formValues) {
		if(formValues!=null)
		{
			for(TeachingActivityFormValue save:formValues)
			{
				TeachingActivityFormValue old=readActivityFormValue(save.getFormFlow(),activity.getActivityFlow());
				if(old!=null)
					save.setRecordStatus(old.getRecordFlow());
					save.setActivityFlow(activity.getActivityFlow());
				editActivityFormValue(save,userinfo);
			}
		}
	}

	private TeachingActivityFormValue readActivityFormValue(String formFlow, String activityFlow) {
		TeachingActivityFormValueExample example=new TeachingActivityFormValueExample();
		example.createCriteria().andActivityFlowEqualTo(activityFlow).andFormFlowEqualTo(formFlow);
		List<TeachingActivityFormValue> values=activityFormValueMapper.selectByExample(example);
		if(values!=null&&values.size()>0)
		{
			return values.get(0);
		}
		return null;
	}

	@Override
	public int editActivityFormValue(TeachingActivityFormValue formValue,SysUser user) {
		String recordFlow = formValue.getRecordFlow();
		if(StringUtil.isNotBlank(recordFlow)){
			formValue.setModifyUserFlow(user.getUserFlow());
			formValue.setModifyTime(DateUtil.getCurrDateTime());
			return activityFormValueMapper.updateByPrimaryKeySelective(formValue);
		}else {
			formValue.setRecordFlow(PkUtil.getUUID());
            formValue.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			formValue.setCreateUserFlow(user.getUserFlow());
			formValue.setCreateTime(DateUtil.getCurrDateTime());
			formValue.setModifyUserFlow(user.getUserFlow());
			formValue.setModifyTime(DateUtil.getCurrDateTime());
			return activityFormValueMapper.insertSelective(formValue);
		}
	}
	@Override
	public Map<String, Object> findActivityTypeMap(Map<String, String> param) {
		Map<String, Object> map=new HashMap<>();
		List<Map<String, Object>> list=activityInfoExtMapper.findActivityTypeList(param);
		if(list!=null)
		{
			for(Map<String, Object> m:list)
			{
				map.put(String.valueOf(m.get("activityTypeId")),m.get("qty"));
			}
			return map;
		}
		return null;
	}

	private void saveActivityTarget(TeachingActivityInfo activity, SysUser userinfo, List<TeachingActivityTarget> targets) {
		int i=1;
		for(TeachingActivityTarget target:targets)
		{
			TeachingActivityInfoTarget infoTarget=targetBiz.readActivityTarget(activity.getActivityFlow(),target.getTargetFlow());
			if(infoTarget==null)
				infoTarget=new TeachingActivityInfoTarget();
			infoTarget.setActivityFlow(activity.getActivityFlow());
			infoTarget.setTargetFlow(target.getTargetFlow());
			infoTarget.setTargetName(target.getTargetName());
			infoTarget.setIsText(target.getIsText());
			infoTarget.setOrdinal(i++);
            infoTarget.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			targetBiz.saveInfoTarget(infoTarget,userinfo);
		}
	}

	//获取系统配置信息
	public String getCfgCode(String code){
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
	public void addActivityImage(ActivityImageFileForm form, SysUser user){

		String cfg = getCfgCode("upload_base_dir");
		String dateString = DateUtil.getCurrDate2();
		String newDir = cfg+ File.separator+"activityFlie"+File.separator +dateString ;
		String fileName = form.getFileName();
		String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		fileName=preffix+suffix;
		File fileDir = new File(newDir);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		try {
			String imgDataString = StringUtil.defaultIfEmpty(form.getUploadFileData(),"");
			byte[] data = null;
			if(StringUtil.isNotBlank(imgDataString)){
				BASE64Decoder decoder = new BASE64Decoder();
				// Base64解码
				data = decoder.decodeBuffer(imgDataString);
				for (int i = 0; i < data.length; ++i) {
					if (data[i] < 0) {// 调整异常数据
						data[i] += 256;
					}
				}
			}
			if(data==null){
				data = form.getUploadFile().getBytes();
			}
			File imageFile = new File(fileDir,fileName);
			if(form.getUploadFile()!=null){
				form.getUploadFile().transferTo(imageFile);
			}else {
				FileOutputStream fos = new FileOutputStream(imageFile);
				fos.write(data);
				fos.flush();
				fos.close();
			}


			String url = getCfgCode("upload_base_url")+"/activityFlie/"+dateString+"/"+fileName;
			if(StringUtil.isNotBlank(form.getActivityFlow())){
				TeachingActivityInfo resRec = readActivityInfo(form.getActivityFlow());
				String content =resRec.getImageUrl();
				if(StringUtil.isBlank(content))
				{
					Document dom = DocumentHelper.createDocument();
					Element root= dom.addElement("ActivityImages");
					Element elem=root.addElement("image");
					String imageFlow=PkUtil.getUUID();
					elem.addAttribute("imageFlow",imageFlow);
					Element imageUrl=elem.addElement("imageUrl");
					Element thumbUrl=elem.addElement("thumbUrl");
					imageUrl.setText(url);
					thumbUrl.setText(url);
					content=root.asXML();
					resRec.setImageUrl(content);
					saveActivityInfo(resRec,user);
				}else{
					Document document=DocumentHelper.parseText(content);
					if (document!=null) {
						Element element=document.getRootElement();
						Element elem=element.addElement("image");
						String imageFlow=PkUtil.getUUID();
						elem.addAttribute("imageFlow",imageFlow);
						Element imageUrl=elem.addElement("imageUrl");
						Element thumbUrl=elem.addElement("thumbUrl");
						imageUrl.setText(url);
						thumbUrl.setText(url);
						resRec.setImageUrl(document.asXML());
						saveActivityInfo(resRec,user);
					}
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("", e);
		}
		catch (IOException e) {
			logger.error("", e);
		} catch (DocumentException e) {
			logger.error("", e);
		}
	}

	@Override
	public List<TeachingActivityInfo> checkJoinList(String activityFlow, String userFlow) {
		return activityInfoExtMapper.checkJoinList(activityFlow,userFlow);
	}

	@Override
	public List<Map<String, Object>> searchDeptByDoctor(String userFlow, String orgFlow) {
		return activityInfoExtMapper.searchDeptByDoctor(userFlow,orgFlow);
	}

	@Override
	public void deleteActivityImage(SysUser user, String activityFlow, String imageFlow) throws DocumentException{
		TeachingActivityInfo activity=readActivityInfo(activityFlow);
		if(StringUtil.isNotBlank(activity.getImageUrl())) {
			String content = activity.getImageUrl();
			Document document = DocumentHelper.parseText(content);
			Element elem = document.getRootElement();
			Node delNode = elem.selectSingleNode("image[@imageFlow='" + imageFlow + "']");
			if (delNode != null) {
				delNode.detach();
				activity.setImageUrl(document.asXML());
				saveActivityInfo(activity,user);
			}
		}
	}

	@Override
	public List<Map<String, Object>> readActivityTargetEvals(String activityFlow) {
		return activityInfoExtMapper.readActivityTargetEvals(activityFlow);
	}

	@Override
	public List<TeachingActivityTarget> readByOrg(String activityTypeId, String orgFlow) {
		TeachingActivityTargetExample example=new TeachingActivityTargetExample();
        TeachingActivityTargetExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andOrgFlowEqualTo(orgFlow);
		// 查询评价指标活动形式和教学活动的活动形式一致的评价指标
		if(StringUtil.isNotBlank(activityTypeId)){
			criteria.andActivityTypeIdEqualTo(activityTypeId);
		}
		// 查询类型是教学活动的评价指标
		criteria.andTargetTypeEqualTo("JX");
		example.setOrderByClause("ordinal ");
		List<TeachingActivityTarget> list= targetMapper.selectByExample(example);
		return list;
	}

	@Override
	public List<Map<String, Object>> getActivityListByRole(Map<String, Object> param) {
		return activityInfoExtMapper.getActivityListByRole(param);
	}
}
