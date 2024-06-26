package com.pinde.sci.biz.webservice.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.biz.webservice.IShzsWebServiceBiz;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.sys.SysDeptExtMapper;
import com.pinde.sci.dao.webService.ShzsWebServiceMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.webservice.bean.shzs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class ShzsWebServiceBizImpl implements IShzsWebServiceBiz {

	@Autowired
	private SysDeptMapper deptMapper;
	@Autowired
	private SchDeptMapper schDeptMapper;
	@Autowired
	private SysDeptExtMapper sysDeptExtMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private SysUserDeptMapper userDeptMapper;
	@Autowired
	private ShzsWebServiceMapper webServiceMapper;
	@Autowired
	private SchRotationMapper rotationMapper;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;

	@Autowired
	private SchArrangeResultMapper resultMapper;
	@Autowired
	private ResDoctorSchProcessMapper processMapper;


	@Override
	public int saveDept(DeptInfo deptInfo) {

		SysDept dept=new SysDept();
		dept.setDeptFlow(deptInfo.getDeptId());
		dept.setDeptName(deptInfo.getDeptName());
		dept.setDeptId(deptInfo.getDeptId());
		dept.setOrgFlow(ShzsGlobalContent.SHZS_ORG_FLOW);
		dept.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_Y);
		ShzsGlobalContent.setRecordInfo(dept,true);

		SchDept schDept=new SchDept();
		schDept.setSchDeptFlow(deptInfo.getDeptId());
		schDept.setSchDeptName(deptInfo.getDeptName());
		schDept.setDeptFlow(deptInfo.getDeptId());
		schDept.setDeptName(deptInfo.getDeptName());
		schDept.setDeptId(deptInfo.getDeptId());
		schDept.setOrgFlow(ShzsGlobalContent.SHZS_ORG_FLOW);
		schDept.setOrgName(ShzsGlobalContent.SHZS_ORG_FLOW);
		schDept.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_Y);
		ShzsGlobalContent.setRecordInfo(schDept,true);

		return deptMapper.insertSelective(dept)+schDeptMapper.insertSelective(schDept);
	}

	@Override
	public SysDept readDeptById(String deptID) {
		return deptMapper.selectByPrimaryKey(deptID);
	}

	@Override
	public SchDept readSchDeptById(String deptID) {
		return schDeptMapper.selectByPrimaryKey(deptID);
	}

	@Override
	public SysDept readDeptByName(String deptName, String orgFlow) {
		SysDeptExample sysDeptExample = new SysDeptExample();
		sysDeptExample.createCriteria().andDeptNameEqualTo(deptName)
				.andOrgFlowEqualTo(orgFlow);
		List<SysDept> deptList = deptMapper.selectByExample(sysDeptExample);
		if(deptList!=null&&deptList.size()>0)
		{
			return deptList.get(0);
		}
		return null;
	}

	@Override
	public int saveEditDept(DeptInfo deptInfo) {
		int c=0;
		SysDept dept=new SysDept();
		dept.setDeptFlow(deptInfo.getDeptId());
		dept.setDeptName(deptInfo.getDeptName());
		c+=deptMapper.updateByPrimaryKeySelective(dept);
		SchDept schDept=new SchDept();
		schDept.setSchDeptFlow(deptInfo.getDeptId());
		schDept.setSchDeptName(deptInfo.getDeptName());
		schDept.setDeptFlow(deptInfo.getDeptId());
		schDept.setDeptName(deptInfo.getDeptName());
		c+=schDeptMapper.updateByPrimaryKeySelective(schDept);
		if(c>0)
		{
			webServiceMapper.updateInProcessDeptNameByFlow(dept);
			webServiceMapper.updateUserDeptNameByFlow(dept);
			webServiceMapper.updateResultDeptNameByFlow(schDept);
			webServiceMapper.updateProcessDeptNameByFlow(schDept);
		}
		return c;
	}

	@Override
	public int stopDept(DeptInfo deptInfo) {
		int c=0;
		c+=webServiceMapper.stopSchDept(deptInfo);
		if(c>0)
		{
			c+=webServiceMapper.delDeptRel(deptInfo);
			c+=webServiceMapper.delExamStandardDept(deptInfo);
		}
		return c;
	}

	@Override
	public int enableDept(DeptInfo deptInfo) {
		int c=0;
		c+=webServiceMapper.enableDept(deptInfo);
		if(c>0)
		{
			c+=webServiceMapper.enableDeptRel(deptInfo);
			c+=webServiceMapper.enableExamStandardDept(deptInfo);
		}
		return c;
	}

	@Override
	public String saveUserInfo(UserInfoForm form, List<SysDept> depts) {
		if(form!=null&&form.getUserInfo()!=null)
		{
			UserInfo userinfo=form.getUserInfo();
			SysUser user=new SysUser();
			user.setUserName(userinfo.getUserName());
			user.setUserCode(userinfo.getUserCode());
			user.setUserPhone(userinfo.getUserPhone());
			user.setIdNo(userinfo.getIdNo());
			boolean isNew=false;
			if(StringUtil.isBlank(userinfo.getUserFlow())) {
				isNew=true;
				user.setUserFlow(PkUtil.getUUID());
				user.setStatusId("Activated");
				user.setStatusDesc("已激活");
			}else{
				user.setUserFlow(userinfo.getUserFlow());
			}
			user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(),userinfo.getPassWord()));
			user.setDeptFlow(depts.get(0).getDeptId());
			user.setDeptName(depts.get(0).getDeptName());
			user.setOrgFlow(ShzsGlobalContent.SHZS_ORG_FLOW);
			user.setOrgName(ShzsGlobalContent.SHZS_ORG_NAME);
			user.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_Y);
			ShzsGlobalContent.setRecordInfo(user,true);
			int c=0;
			if(isNew)
			 	c=sysUserMapper.insertSelective(user);
			else
				c=sysUserMapper.updateByPrimaryKeySelective(user);
			if(c!=0)
			{
				//删除人员所有角色
				webServiceMapper.delUserRole(user.getUserFlow());
				//添加人员分配的角色
				for(String role:form.getRoles())
				{
					String roleFlow="";
					if("Head".equals(role))
					{
						roleFlow=ShzsGlobalContent.SHZS_HEAD_ROLE_FLOW;
					}
					if("Teacher".equals(role))
					{
						roleFlow=ShzsGlobalContent.SHZS_TEACHER_ROLE_FLOW;
					}
					SysUserRole userRole=userRoleBiz.readUserRoleNoReoordStatus(user.getUserFlow(), roleFlow);
					if(userRole==null)
					{
						userRole=new SysUserRole();
					}
					userRole.setUserFlow(user.getUserFlow());
					userRole.setRoleFlow(roleFlow);
					userRole.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_Y);
					userRole.setWsId(ShzsGlobalContent.RES_WS_ID);
					userRole.setAuthTime(DateUtil.getCurrentTime());
					userRole.setAuthUserFlow("shzs.webservice");
					ShzsGlobalContent.setRecordInfo(userRole,true);
					userRoleBiz.saveSysUserRole(userRole);
				}
				//删除人员所有的科室
				webServiceMapper.delUserDept(user.getUserFlow());
				//添加人员的所有科室

				for(SysDept dept:depts)
				{
					SysUserDept userDept=readDeptByFlow(user.getUserFlow(), dept.getDeptFlow());
					if(userDept==null)
					{
						userDept=new SysUserDept();
					}
					userDept.setUserFlow(user.getUserFlow());
					userDept.setDeptFlow(dept.getDeptFlow());
					userDept.setDeptName(dept.getDeptName());
					userDept.setOrgFlow(ShzsGlobalContent.SHZS_ORG_FLOW);
					userDept.setOrgName(ShzsGlobalContent.SHZS_ORG_NAME);
					userDept.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_Y);
					ShzsGlobalContent.setRecordInfo(userDept,true);
					saveUserDept(userDept);
				}
				return user.getUserFlow();
			}
		}
		return null;
	}
	@Override
	public String saveDocInfo(DocInfoForm form, SchRotation rotation) {
		if(form!=null&&form.getUserInfo()!=null)
		{
			DocInfo userinfo=form.getUserInfo();
			SysUser user=new SysUser();
			user.setUserName(userinfo.getUserName());
			user.setUserCode(userinfo.getUserCode());
			user.setUserPhone(userinfo.getUserPhone());
			user.setIdNo(userinfo.getIdNo());
			boolean isNew=false;
			if(StringUtil.isBlank(userinfo.getUserFlow())) {
				isNew=true;
				user.setUserFlow(PkUtil.getUUID());
				user.setStatusId("Activated");
				user.setStatusDesc("已激活");
			}else{
				user.setUserFlow(userinfo.getUserFlow());
			}
			user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(),userinfo.getPassWord()));
			user.setOrgFlow(ShzsGlobalContent.SHZS_ORG_FLOW);
			user.setOrgName(ShzsGlobalContent.SHZS_ORG_NAME);
			user.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_Y);
			user.setMedicineTypeId(rotation.getRotationTypeId());
			user.setMedicineTypeName(rotation.getRotationTypeName());
			ShzsGlobalContent.setRecordInfo(user,true);

			ResDoctor doctor=new ResDoctor();
			doctor.setDoctorFlow(user.getUserFlow());
			doctor.setDoctorName(user.getUserName());
			doctor.setOrgFlow(ShzsGlobalContent.SHZS_ORG_FLOW);
			doctor.setOrgName(ShzsGlobalContent.SHZS_ORG_NAME);
			doctor.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_Y);
			doctor.setRotationFlow(rotation.getRotationFlow());
			doctor.setRotationName(rotation.getRotationName());
			doctor.setTrainingSpeId(userinfo.getTrainingSpeId());
			doctor.setTrainingSpeName(ShzsGlobalContent.SpeEnum.getNameById(userinfo.getTrainingSpeId()));
			doctor.setDoctorTypeId(userinfo.getDoctorTypeId());
			doctor.setDoctorTypeName(ShzsGlobalContent.DocTypeEnum.getNameById(userinfo.getDoctorTypeId()));
			doctor.setTrainingYears(userinfo.getTrainingYears());
			doctor.setSessionNumber(userinfo.getSessionNumber());
			doctor.setGraduationYear(Integer.valueOf(userinfo.getSessionNumber())+Integer.valueOf(userinfo.getTrainingYears())+"");
			doctor.setTrainingTypeId("Doctor");
			doctor.setTrainingTypeName("住院医师");
			doctor.setDoctorCategoryId("Doctor");
			doctor.setDoctorCategoryName("住院医师");

			int c=0;
			if(isNew)
			 	c=sysUserMapper.insertSelective(user);
			else
				c=sysUserMapper.updateByPrimaryKeySelective(user);
			if(c!=0) {
				doctorBiz.editDoctor(doctor);
				String roleFlow = ShzsGlobalContent.SHZS_DOCTOR_ROLE_FLOW;
				SysUserRole userRole = userRoleBiz.readUserRoleNoReoordStatus(user.getUserFlow(), roleFlow);
				if (userRole == null) {
					userRole = new SysUserRole();
				}
				userRole.setUserFlow(user.getUserFlow());
				userRole.setRoleFlow(roleFlow);
				userRole.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_Y);
				userRole.setWsId(ShzsGlobalContent.RES_WS_ID);
				userRole.setAuthTime(DateUtil.getCurrentTime());
				userRole.setAuthUserFlow("shzs.webservice");
				ShzsGlobalContent.setRecordInfo(userRole, true);
				userRoleBiz.saveSysUserRole(userRole);

				return user.getUserFlow();
			}
		}
		return null;
	}

	@Override
	public SchRotation readRotationBySpeId(String trainingSpeId, String trainingType) {
		SchRotationExample example = new SchRotationExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andSpeIdEqualTo(trainingSpeId).andPublishFlagEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorCategoryIdEqualTo(trainingType);
		example.setOrderByClause("create_time desc");
		List<SchRotation> list = rotationMapper.selectByExample(example);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int delDocInfo(DocInfo docInfo) {
		if(docInfo!=null&&StringUtil.isNotBlank(docInfo.getUserFlow()))
		{
			SysUser user=new SysUser();
			user.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_N);
			user.setUserFlow(docInfo.getUserFlow());
			ResDoctor doctor=new ResDoctor();
			doctor.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_N);
			doctor.setDoctorFlow(docInfo.getUserFlow());
			return doctorBiz.editDoctor(doctor)+sysUserMapper.updateByPrimaryKeySelective(user);
		}
		return 0;
	}

	@Override
	public List<Map<String, String>> readRotationDeptByFlow(String rotationFlow) {
		return webServiceMapper.readRotationDeptByFlow(rotationFlow);
	}

	@Override
	public int checkUserDept(String userFlow, String deptFlow) {
		return webServiceMapper.checkUserDept( userFlow,  deptFlow);
	}

	@Override
	public List<SchArrangeResult> checkResultDate(String userFlow, String schStartDate, String schEndDate, String rotationFlow) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",userFlow);
		paramMap.put("startDate",schStartDate);
		paramMap.put("endDate",schEndDate);
		paramMap.put("rotationFlow",rotationFlow);
		return webServiceMapper.checkResultDate(paramMap);
	}

	@Override
	public int editDoctorResult(SysUser user, SchRotationDept rotationDept, SysDept sysDept, String schStartDate, String schEndDate, SysUser tea, SysUser head) throws ParseException {
		SchArrangeResult result;
		ResDoctorSchProcess process;
		ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
		result = new SchArrangeResult();
		process = new ResDoctorSchProcess();


		if (doctor != null) {
			result.setResultFlow(PkUtil.getUUID());
			result.setArrangeFlow(PkUtil.getUUID());
			result.setDoctorFlow(user.getUserFlow());
			result.setDoctorName(doctor.getDoctorName());
			result.setSessionNumber(doctor.getSessionNumber());
			result.setOrgFlow(doctor.getOrgFlow());
			result.setOrgName(doctor.getOrgName());
			result.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			result.setCreateUserFlow(user.getUserFlow());
			result.setCreateTime(DateUtil.getCurrentTime());
			result.setModifyUserFlow(user.getUserFlow());
			result.setModifyTime(DateUtil.getCurrentTime());

			process.setProcessFlow(PkUtil.getUUID());
			process.setUserFlow(user.getUserFlow());
			process.setOrgFlow(doctor.getOrgFlow());
			process.setOrgName(doctor.getOrgName());
			process.setSchResultFlow(result.getResultFlow());
			process.setSchFlag(GlobalConstant.FLAG_N);
			process.setIsCurrentFlag(GlobalConstant.FLAG_N);
			process.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			process.setCreateUserFlow(user.getUserFlow());
			process.setCreateTime(DateUtil.getCurrentTime());
			process.setModifyUserFlow(user.getUserFlow());
			process.setModifyTime(DateUtil.getCurrentTime());

			String rotationFlow = doctor.getRotationFlow();
			if (StringUtil.isNotBlank(rotationFlow)) {
				SchRotation rotation = rotationMapper.selectByPrimaryKey(rotationFlow);
				result.setRotationFlow(rotation.getRotationFlow());
				result.setRotationName(rotation.getRotationName());
				result.setSchYear(rotation.getRotationYear());
			}
		}

		if (rotationDept != null) {
			Integer ordi = rotationDept.getOrdinal();
			BigDecimal ord = null;
			if (ordi != null) {
				ord = BigDecimal.valueOf(ordi);
			}
			result.setSchDeptOrder(ord);
			result.setIsRequired(rotationDept.getIsRequired());
			result.setStandardDeptId(rotationDept.getStandardDeptId());
			result.setStandardDeptName(rotationDept.getStandardDeptName());
			result.setStandardGroupFlow(rotationDept.getGroupFlow());
		}

		SchDept schDept = readSchDeptById(sysDept.getDeptFlow());
		if (schDept != null) {
			result.setDeptFlow(schDept.getDeptFlow());
			result.setDeptName(schDept.getDeptName());
			result.setSchDeptFlow(schDept.getSchDeptFlow());
			result.setSchDeptName(schDept.getSchDeptName());

			process.setDeptFlow(schDept.getDeptFlow());
			process.setDeptName(schDept.getDeptName());
			process.setSchDeptFlow(schDept.getSchDeptFlow());
			process.setSchDeptName(schDept.getSchDeptName());
		}
		process.setTeacherUserFlow(tea.getUserFlow());
		process.setTeacherUserName(tea.getUserName());
		process.setHeadUserFlow(head.getUserFlow());
		process.setHeadUserName(head.getUserName());
		process.setSchStartDate(schStartDate);
		process.setSchEndDate(schEndDate);
		process.setStartDate(schStartDate);
		process.setEndDate(schEndDate);

		result.setSchStartDate(schStartDate);
		result.setSchEndDate(schEndDate);
		if (DateUtil.getCurrDate().compareTo(schStartDate) >= 0) {
			process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
		}
		//默认按月计算
		Map<String, String> map = new HashMap<>();
		map.put("startDate", result.getSchStartDate());
		map.put("endDate", result.getSchEndDate());
		Double month = TimeUtil.getMonthsBetween(map);
		String schMonth = String.valueOf(Double.parseDouble(month + ""));
		result.setSchMonth(schMonth);

		if (!GlobalConstant.FLAG_Y.equals(doctor.getSchFlag()) || !GlobalConstant.FLAG_Y.equals(doctor.getSelDeptFlag())) {
			doctor.setSchFlag(GlobalConstant.FLAG_Y);
			doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
			doctorBiz.editDoctor(doctor);
		}
		return	resultMapper.insertSelective(result) + processMapper.insertSelective(process);
	}

	@Override
	public int readDocCountByTea(String userFlow) {
		return webServiceMapper.readDocCountByTea(userFlow);
	}

	@Override
	public int readDocCountByHead(String userFlow) {
		return webServiceMapper.readDocCountByHead(userFlow);
	}

	@Override
	public int readDocProcess(String userFlow) {
		return webServiceMapper.readDocProcess(userFlow);
	}

	@Override
	public String saveUserBaseInfo(UserInfoForm form) {
		if(form!=null&&form.getUserInfo()!=null) {
			UserInfo userinfo = form.getUserInfo();
			SysUser user = new SysUser();
			user.setUserName(userinfo.getUserName());
			user.setUserCode(userinfo.getUserCode());
			user.setUserPhone(userinfo.getUserPhone());
			user.setIdNo(userinfo.getIdNo());
			user.setUserFlow(userinfo.getUserFlow());
			user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), userinfo.getPassWord()));
			user.setOrgFlow(ShzsGlobalContent.SHZS_ORG_FLOW);
			user.setOrgName(ShzsGlobalContent.SHZS_ORG_NAME);
			user.setRecordStatus(ShzsGlobalContent.RECORD_STATUS_Y);
			ShzsGlobalContent.setRecordInfo(user, true);
			ResDoctor doctor=doctorBiz.readDoctor(user.getUserFlow());
			if(doctor!=null)
			{
				doctor.setDoctorName(user.getUserName());
				doctorBiz.editDoctor(doctor);
			}
			int c = sysUserMapper.updateByPrimaryKeySelective(user);
			if(c!=0)
			{
				return user.getUserFlow();
			}
		}
		return null;
	}

	private int saveUserDept(SysUserDept userDept) {

		if(StringUtil.isNotBlank(userDept.getRecordFlow())){
			return userDeptMapper.updateByPrimaryKeySelective(userDept);
		}else{
			userDept.setRecordFlow(PkUtil.getUUID());
			return userDeptMapper.insertSelective(userDept);
		}
	}

	private  SysUserDept readDeptByFlow(String userFlow, String deptFlow)
	{
		SysUserDeptExample example = new SysUserDeptExample();
		SysUserDeptExample.Criteria criteria = example.createCriteria()
				.andDeptFlowEqualTo(deptFlow).andUserFlowEqualTo(userFlow);
		List<SysUserDept> userDepts=  userDeptMapper.selectByExample(example);
		if(userDepts!=null&&userDepts.size()>0)
		{
			return userDepts.get(0);
		}
		return null;
	}
}
