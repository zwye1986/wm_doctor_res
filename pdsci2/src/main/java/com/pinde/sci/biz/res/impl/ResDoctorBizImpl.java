package com.pinde.sci.biz.res.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONUtil;
import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.entyties.SysDict;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.*;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.jsres.JsResDoctorExtMapper;
import com.pinde.sci.dao.res.ResDoctorExtMapper;
import com.pinde.sci.dao.sch.SchDoctorDeptExtMapper;
import com.pinde.sci.enums.jsres.CertificateStatusEnum;
import com.pinde.sci.enums.jsres.TrainCategoryEnum;
import com.pinde.sci.enums.pub.UserNationEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sch.SchStatusEnum;
import com.pinde.sci.enums.sys.CertificateTypeEnum;
import com.pinde.sci.enums.sys.*;
import com.pinde.sci.excelListens.model.ResRecItem;
import com.pinde.sci.form.hbres.*;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import com.pinde.sci.model.res.ResDoctorScoreExt;
import com.pinde.sci.model.sys.SysUserResDoctorExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Service
@Transactional(rollbackFor=Exception.class)
public class ResDoctorBizImpl implements IResDoctorBiz{
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ResDoctorExtMapper doctorExtMapper;
	@Resource
	private ResUserBindMacidMapper macidMapper;
	@Autowired
	private JsresRecruitDocInfoMapper docInfoMapper;
	@Autowired
	private JsresRecruitInfoMapper jsresRecruitInfoMapper;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private ResDoctorSchProcessMapper resDoctorProcessMapper;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IResRegBiz resRegBiz;
	@Autowired
	private SchArrangeResultMapper resultMapper;
	@Autowired
	private SchDoctorDeptMapper docDeptMapper;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchRotationBiz schRotationBiz;
	@Autowired
	private IResJointOrgBiz resJointBiz;
	@Autowired
	private IResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private ResDoctorRecruitMapper doctorRecruitMapper;
	@Autowired
	private ResSigninMapper signinMapper;
	@Autowired
	private PubUserResumeMapper resumpMapper;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private JsResDoctorExtMapper jsResDoctorExtMapper;
	@Autowired
	private IPubUserResumeBiz pubUserResumeBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private IResHBArchiveBiz archiveBiz;
	@Autowired
	private SchOrgArrangeResultMapper orgArrangeResultMapper;
	@Autowired
	private SchDoctorSelectDeptMapper doctorSelectDeptMapper;
	@Autowired
	private IResTestConfigBiz resTestConfigBiz;
	@Autowired
	private JsresGraduationApplyMapper applyMapper;
	@Autowired
	private JsresExamSignupMapper signupMapper;

	private static final String EXT_INFO_ROOT = "extInfo";
	private static final String EXT_INFO_ELE = "extInfoForm";

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@Override
	public	List<ResDoctor> searchDoctor(){
		ResDoctorExample example = new ResDoctorExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public	ResDoctor readDoctor(String recordFlow){
		return doctorMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public List<ResDoctor> readDoctorByExample(ResDoctorExample example) {
		return doctorMapper.selectByExample(example);
	}

	@Override
	public List<Map<String, Object>> readDoctorCountBySpe(ResDoctor resDoctor) {
		return doctorMapper.readDoctorCountBySpe(resDoctor);
	}

	@Override
	public	int editDoctor(ResDoctor doctor){
		if(doctor!=null){
//			checkSelDeptFlag(doctor);
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				GeneralMethod.setRecordInfo(doctor, false);
				return doctorMapper.updateByPrimaryKeySelective(doctor);
			}else{
				doctor.setDoctorFlow(PkUtil.getUUID());
				return onlySaveResDoctor(doctor);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int editDoctor(ResDoctor doctor, SysUser sysUser) {
		if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
			GeneralMethod.setRecordInfo(doctor, false);
			return doctorMapper.updateByPrimaryKeySelective(doctor);
		}else{
			if(sysUser !=null){
				doctor.setDoctorFlow(sysUser.getUserFlow());
				doctor.setDoctorName(sysUser.getUserName());
			}else{
				SysUser currUser = GlobalContext.getCurrentUser();
				doctor.setDoctorFlow(currUser.getUserFlow());
			}
			GeneralMethod.setRecordInfo(doctor, true);
			return doctorMapper.insert(doctor);
		}
	}

	@Override
	public int onlySaveResDoctor(ResDoctor resDoctor){
		GeneralMethod.setRecordInfo(resDoctor, true);
		resDoctor.setSelDeptFlag(GlobalConstant.FLAG_N);
		resDoctor.setSchFlag(GlobalConstant.FLAG_N);
		return doctorMapper.insertSelective(resDoctor);
	}

//	private void checkSelDeptFlag(ResDoctor doctor){
//		if(doctor!=null && StringUtil.isNotBlank(doctor.getRotationFlow()) && StringUtil.isBlank(doctor.getSelDeptFlag())){
//			List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(doctor.getRotationFlow());
//			if(groupList==null || groupList.size()<1){
//				doctor.setSelDeptFlag(GlobalConstant.FLAG_Y);
//			}
//		}
//	}

	@Override
	public int updateDocSelFlag(String orgFlow) {
		return doctorExtMapper.updateDocSelFlag(orgFlow);
	}

	@Override
	public int editDocUser(ResDoctor doctor,SysUser user){
		if(doctor!=null && user!=null){
//			if(GlobalConstant.RECORD_STATUS_N.equals(doctor.getRecordStatus())){
//				user.setStatusId(UserStatusEnum.Locked.getId());
//				user.setStatusDesc(UserStatusEnum.Locked.getName());
//			}else{
//				user.setStatusId(UserStatusEnum.Activated.getId());
//				user.setStatusDesc(UserStatusEnum.Activated.getName());
//			}

			String docRole = InitConfig.getSysCfg("res_doctor_role_flow");
			SysUserRole userRole = null;
			if(StringUtil.isNotBlank(user.getUserFlow()) && StringUtil.isNotBlank(docRole)){
				userRole = userRoleBiz.readUserRole(user.getUserFlow(),docRole);
			}

			userBiz.saveUser(user);

			if(userRole == null){
				userRole = new SysUserRole();
				userRole.setUserFlow(user.getUserFlow());
				userRole.setOrgFlow(user.getOrgFlow());
				String currWsId = (String)GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
				userRole.setWsId(currWsId);
				userRole.setRoleFlow(InitConfig.getSysCfg("res_doctor_role_flow"));
				userRole.setAuthTime(DateUtil.getCurrDate());
				userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				userRoleBiz.saveSysUserRole(userRole);
			}
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_inSch_flag")))
			{

				 docRole = InitConfig.getSysCfg("sch_doctor_role_flow");
				 userRole = null;
				if(StringUtil.isNotBlank(user.getUserFlow()) && StringUtil.isNotBlank(docRole)){
					userRole = userRoleBiz.readUserRole(user.getUserFlow(),docRole);
				}
				if(userRole == null){
					userRole = new SysUserRole();
					userRole.setUserFlow(user.getUserFlow());
					userRole.setOrgFlow(user.getOrgFlow());
					String currWsId =GlobalConstant.SCH_WS_ID;
					userRole.setWsId(currWsId);
					userRole.setRoleFlow(InitConfig.getSysCfg("sch_doctor_role_flow"));
					userRole.setAuthTime(DateUtil.getCurrDate());
					userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
					userRoleBiz.saveSysUserRole(userRole);
				}
			}


			if(!StringUtil.isNotBlank(doctor.getDoctorFlow())){
				doctor.setDoctorFlow(user.getUserFlow());
				GeneralMethod.setRecordInfo(doctor, true);

//				if(GlobalConstant.RECORD_STATUS_N.equals(doctor.getRecordStatus())){
//					doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
//				}
//				checkSelDeptFlag(doctor);
				doctor.setSelDeptFlag(GlobalConstant.FLAG_N);
				doctor.setSchFlag(GlobalConstant.FLAG_N);
				doctorMapper.insertSelective(doctor);
			}else{
				editDoctor(doctor);
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int editSchDocUser(ResDoctor doctor,SysUser user){
		if(doctor!=null && user!=null){

			String docRole = InitConfig.getSysCfg("sch_doctor_role_flow");
			SysUserRole userRole = null;
			if(StringUtil.isNotBlank(user.getUserFlow()) && StringUtil.isNotBlank(docRole)){
				userRole = userRoleBiz.readUserRole(user.getUserFlow(),docRole);
			}

			userBiz.saveUser(user);

			if(userRole == null){
				userRole = new SysUserRole();
				userRole.setUserFlow(user.getUserFlow());
				userRole.setOrgFlow(user.getOrgFlow());
				String currWsId = (String)GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WS_ID);
				userRole.setWsId(currWsId);
				userRole.setRoleFlow(InitConfig.getSysCfg("sch_doctor_role_flow"));
				userRole.setAuthTime(DateUtil.getCurrDate());
				userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				userRoleBiz.saveSysUserRole(userRole);
			}
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sch_inRes_flag")))
			{
				docRole = InitConfig.getSysCfg("res_doctor_role_flow");
				userRole = null;
				if(StringUtil.isNotBlank(user.getUserFlow()) && StringUtil.isNotBlank(docRole)){
					userRole = userRoleBiz.readUserRole(user.getUserFlow(),docRole);
				}
				if(userRole == null){
					userRole = new SysUserRole();
					userRole.setUserFlow(user.getUserFlow());
					userRole.setOrgFlow(user.getOrgFlow());
					String currWsId =GlobalConstant.RES_WS_ID;
					userRole.setWsId(currWsId);
					userRole.setRoleFlow(InitConfig.getSysCfg("res_doctor_role_flow"));
					userRole.setAuthTime(DateUtil.getCurrDate());
					userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
					userRoleBiz.saveSysUserRole(userRole);
				}
			}
			if(!StringUtil.isNotBlank(doctor.getDoctorFlow())){
				doctor.setDoctorFlow(user.getUserFlow());
				GeneralMethod.setRecordInfo(doctor, true);
				doctor.setSelDeptFlag(GlobalConstant.FLAG_N);
				doctor.setSchFlag(GlobalConstant.FLAG_N);
				doctorMapper.insertSelective(doctor);
			}else{
				editDoctor(doctor);
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int editDocUserFromRegister(ResDoctor doctor,SysUser user,BaseUserResumeExtInfoForm extInfo){
		if(doctor!=null && user!=null && extInfo!=null){
			String userFlow = user.getUserFlow();
			String regYear = InitConfig.getSysCfg("res_reg_year");

			//判断是否已提交，未提交可继续完善信息，已提交则结束完善信息节点
			ResDoctor exitDoctor = this.doctorMapper.selectByPrimaryKey(userFlow);
			ResReg recentReg = resRegBiz.searchRecentYearResReg(user.getUserFlow());
			if (null != exitDoctor && null != recentReg && regYear.equals(recentReg.getRegYear()) && !RegStatusEnum.UnSubmit.getId().equals(exitDoctor.getDoctorStatusId())
					&& !(RegStatusEnum.UnPassed.getId().equals(exitDoctor.getDoctorStatusId()) && GlobalConstant.FLAG_Y.equals(exitDoctor.getReeditFlag()))//省厅可让其重填
					&& !"Y".equals(exitDoctor.getIsUnderLine())//线下招录人员
				) {//已提交
				return GlobalConstant.ZERO_LINE;
			}
			doctor.setDoctorStatusId(RegStatusEnum.UnSubmit.getId());
			doctor.setDoctorStatusName(RegStatusEnum.UnSubmit.getName());
			doctor.setSessionNumber(regYear);
			userBiz.saveUser(user);
			String xmlContent = JaxbUtil.convertToXml(extInfo);
			PubUserResume pubUserResume = pubUserResumeBiz.readPubUserResume(user.getUserFlow());
			if(pubUserResume == null){
				pubUserResume = new PubUserResume();
			}
			pubUserResume.setUserResume(xmlContent);
			pubUserResumeBiz.savePubUserResume(user, pubUserResume);
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				if(exitDoctor!=null){
					GeneralMethod.setRecordInfo(doctor, false);
					return doctorMapper.updateByPrimaryKeySelective(doctor);
				}else{
					doctor.setDoctorFlow(userFlow);
					doctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
					doctor.setDoctorCategoryName(RecDocCategoryEnum.Doctor.getName());
					GeneralMethod.setRecordInfo(doctor, true);
					doctor.setSelDeptFlag(GlobalConstant.FLAG_N);
					doctor.setSchFlag(GlobalConstant.FLAG_N);
					return doctorMapper.insertSelective(doctor);
				}

			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int editDocUserFromRegister2(ResDoctor doctor,SysUser user,BaseUserResumeExtInfoForm extInfo){
		if(doctor!=null && user!=null && extInfo!=null){
			String userFlow = user.getUserFlow();
			ResDoctor exitDoctor = this.doctorMapper.selectByPrimaryKey(userFlow);
			userBiz.saveUser(user);
			String xmlContent = JaxbUtil.convertToXml(extInfo);
			PubUserResume pubUserResume = pubUserResumeBiz.readPubUserResume(user.getUserFlow());
			if(pubUserResume == null){
				pubUserResume = new PubUserResume();
			}
			pubUserResume.setUserResume(xmlContent);
			pubUserResumeBiz.savePubUserResume(user, pubUserResume);
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				if(exitDoctor!=null){
					GeneralMethod.setRecordInfo(doctor, false);
					return doctorMapper.updateByPrimaryKeySelective(doctor);
				}
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	/**
	 * 检查文件大小及类型
	 * @param file
	 * @return
	 */
	@Override
	public String checkFile(MultipartFile file) {
		List<String> mimeList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
			mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
		}
		List<String> suffixList = new ArrayList<String>();
		if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
			suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix").toLowerCase()).split(","));
		}
		String fileType = file.getContentType();//MIME类型;
		String fileName = file.getOriginalFilename();//文件名
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix.toLowerCase()))){
			return "请上传 "+InitConfig.getSysCfg("inx_image_support_suffix")+"格式的文件";
		}
		long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
		if(file.getSize() > limitSize*1024*1024){
			return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M" ;
		}
		return GlobalConstant.FLAG_Y;//可执行保存
	}

	@Override
	public List<ResDoctor> searchByDoc(ResDoctor doctor){
		ResDoctorExample example = new ResDoctorExample();
		ResDoctorExample.Criteria criteria = example.createCriteria();
		setCriteria(doctor,criteria);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctor> searchByDocHaveRotation(ResDoctor doctor){
		ResDoctorExample example = new ResDoctorExample();
		ResDoctorExample.Criteria criteria = example.createCriteria();
		setCriteria(doctor,criteria);
		criteria.andRotationFlowIsNotNull().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctor> searchByDocNotSelf(ResDoctor doctor,String doctorFlow){
		ResDoctorExample example = new ResDoctorExample();
		ResDoctorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andDoctorFlowNotEqualTo(doctorFlow);
		setCriteria(doctor,criteria);
		return doctorMapper.selectByExample(example);
	}

	private ResDoctorExample.Criteria setCriteria(ResDoctor doctor,ResDoctorExample.Criteria criteria){
		if(doctor!=null){
			if(StringUtil.isNotBlank(doctor.getSessionNumber())){
				criteria.andSessionNumberEqualTo(doctor.getSessionNumber());
			}
			if(StringUtil.isNotBlank(doctor.getTrainingSpeId())){
				criteria.andTrainingSpeIdEqualTo(doctor.getTrainingSpeId());
			}
			if(StringUtil.isNotBlank(doctor.getGraduatedId())){
				criteria.andGraduatedIdEqualTo(doctor.getGraduatedId());
			}
			if(StringUtil.isNotBlank(doctor.getRecordStatus())){
				criteria.andRecordStatusEqualTo(doctor.getRecordStatus());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorCategoryId())){
				criteria.andDoctorCategoryIdEqualTo(doctor.getDoctorCategoryId());
			}
			if(StringUtil.isNotBlank(doctor.getOrgFlow())){
				criteria.andOrgFlowEqualTo(doctor.getOrgFlow());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorName())){
				criteria.andDoctorNameEqualTo(doctor.getDoctorName());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				criteria.andDoctorFlowEqualTo(doctor.getDoctorFlow());
			}
			if(StringUtil.isNotBlank(doctor.getSchStatusId())){
				criteria.andSchStatusIdEqualTo(doctor.getSchStatusId());
			}
			if(StringUtil.isNotBlank(doctor.getDeptFlow())){
				criteria.andDeptFlowEqualTo(doctor.getDeptFlow());
			}
			if(StringUtil.isNotBlank(doctor.getGroupId())){
				criteria.andGroupIdEqualTo(doctor.getGroupId());
			}
			if(StringUtil.isNotBlank(doctor.getSelDeptFlag())){
				criteria.andSelDeptFlagEqualTo(doctor.getSelDeptFlag());
			}
			if(StringUtil.isNotBlank(doctor.getSchFlag())){
				criteria.andSchFlagEqualTo(doctor.getSchFlag());
			}
		}
		return criteria;
	}

	@Override
	public List<ResDoctorExt> searchDocUser(ResDoctorExt resDoctorExt, String medicineTypeId){
		if(resDoctorExt!=null)
			resDoctorExt.setMedicineTypeId(medicineTypeId);
		return doctorExtMapper.searchResDoctorUser(resDoctorExt);
	}


    @Override
    public List<ResDoctorExt> searchDocUserForAnnualCheck(ResDoctorExt resDoctorExt) {
        return doctorExtMapper.searchDocUserForAnnualCheck(resDoctorExt);
    }

    @Override
	public List<ResDoctorScoreExt> searchDocGrades(ResDoctorScoreExt resDoctorExt){
		return doctorExtMapper.searchResDoctorGrades(resDoctorExt);
	}

	@Override
	public List<SysUserResDoctorExt> searchSysUserAndResDoctor(SysUserResDoctorExt userDoctorExt,List<String> checkUserFlowList,String schDeptFlow){
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("userDoctorExt", userDoctorExt);
		if(checkUserFlowList!=null && !checkUserFlowList.isEmpty()){
			paramMap.put("checkUserFlowList", checkUserFlowList);
		}
		if(StringUtil.isNotBlank(schDeptFlow)){
			paramMap.put("schDeptFlow", schDeptFlow);
		}
		return doctorExtMapper.searchSysUserAndResDoctor(paramMap);
	}

	@Override
	public List<ResDoctorExt> searchRegUser(Map<String , Object> paramMap){
		return doctorExtMapper.searchResRegUserForAudit(paramMap);
	}

	@Override
	public ResDoctor searchByUserFlow(String userFlow) {
		return doctorMapper.selectByPrimaryKey(userFlow);
	}

	@Override
	public List<SysUser> searchTeacherOrHead(String resultFlow, String roleFlow) {
		List<SysUser> userList = null;
		if(StringUtil.isNotBlank(resultFlow)&&StringUtil.isNotBlank(roleFlow)){
			SchArrangeResult result = this.schArrangeResultBiz.readSchArrangeResult(resultFlow);
			if(result!=null){
				SysUserRole userRole = new SysUserRole();
				userRole.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
				userRole.setRoleFlow(roleFlow);
				List<SysUserRole> userRoleList = userRoleBiz.searchUserRole(userRole);
				List<String> userFlows = new ArrayList<String>();
				if(userRoleList!=null&&!userRoleList.isEmpty()){
					for (SysUserRole sur : userRoleList) {
						userFlows.add(sur.getUserFlow());
					}
				}
				userList = this.userBiz.searchSysUserByuserFlows(userFlows,result.getDeptFlow());
			}
		}
		return userList;
	}

	@Override
	public void saveChoose(ResDoctorSchProcess process, String resultFlow, String preResultFlow) {
		SchArrangeResult result = this.schArrangeResultBiz.readSchArrangeResult(resultFlow);
		if(result!=null){

			ResDoctorSchProcess old=resDoctorProcessBiz.searchByResultFlow(resultFlow);
			if(old!=null)
			{
				process.setDeptFlow(result.getDeptFlow());
				process.setDeptName(result.getDeptName());
				process.setSchDeptFlow(result.getSchDeptFlow());
				process.setSchDeptName(result.getSchDeptName());
				process.setSchResultFlow(resultFlow);
				process.setSchStartDate(result.getSchStartDate());
				process.setSchEndDate(result.getSchEndDate());
				process.setSchFlag(GlobalConstant.FLAG_N);
				process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
				process.setSchPer((short) 0);
				process.setStartDate(DateUtil.getCurrDate());
				if (StringUtil.isNotBlank(process.getTeacherUserFlow())) {
					SysUser teacher = userBiz.readSysUser(process.getTeacherUserFlow());
					process.setTeacherUserName(teacher.getUserName());
				}
				if (StringUtil.isNotBlank(process.getHeadUserFlow())) {
					SysUser head = userBiz.readSysUser(process.getHeadUserFlow());
					process.setHeadUserName(head.getUserName());
				}
				process.setProcessFlow(old.getProcessFlow());
				process.setIsExternal(old.getIsExternal());
			}else {
				ResDoctor doctor = readDoctor(result.getDoctorFlow());
				if (doctor != null) {
					process.setUserFlow(doctor.getDoctorFlow());
					process.setOrgFlow(result.getOrgFlow());
					process.setOrgName(result.getOrgName());

					doctor.setDeptFlow(result.getDeptFlow());
					doctor.setDeptName(result.getDeptName());
					editDoctor(doctor);
				}
				process.setDeptFlow(result.getDeptFlow());
				process.setDeptName(result.getDeptName());
				process.setSchDeptFlow(result.getSchDeptFlow());
				process.setSchDeptName(result.getSchDeptName());
				process.setSchResultFlow(resultFlow);
				process.setSchStartDate(result.getSchStartDate());
				process.setSchEndDate(result.getSchEndDate());
				process.setSchFlag(GlobalConstant.FLAG_N);
				process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
				process.setSchPer((short) 0);
				process.setStartDate(DateUtil.getCurrDate());
				if (StringUtil.isNotBlank(process.getTeacherUserFlow())) {
					SysUser teacher = userBiz.readSysUser(process.getTeacherUserFlow());
					process.setTeacherUserName(teacher.getUserName());
				}
				if (StringUtil.isNotBlank(process.getHeadUserFlow())) {
					SysUser head = userBiz.readSysUser(process.getHeadUserFlow());
					process.setHeadUserName(head.getUserName());
				}
			}
			this.resDoctorProcessBiz.edit(process);

//			if(StringUtil.isNotBlank(preResultFlow)){
//				ResDoctorSchProcess preProcess = this.resDoctorProcessBiz.searchByResultFlow(preResultFlow);
//				if(preProcess!=null){
//					preProcess.setIsCurrentFlag(GlobalConstant.FLAG_N);
//					//preProcess.setSchFlag(GlobalConstant.FLAG_Y);
//					this.resDoctorProcessBiz.edit(preProcess);
//				}
//			}
		}
	}
//	@Override
//	public List<SysUser> searchResDoctorByuserFlows(List<String> userFlows){
//		if(userFlows != null && !userFlows.isEmpty()){
//			ResDoctorExample example = new ResDoctorExample();
//			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(userFlows);
//			return doctorMapper.selectByExample(example);
//		}
//		return null;
//	}

	@Override
	public List<ResDoctor> searchDoctorByuserFlow(List<String> userFlows) {
		ResDoctorExample example = new ResDoctorExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(userFlows);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public String saveImg(String oldImg,MultipartFile file, String folderName){
		String path = GlobalConstant.FLAG_N;
		if(file.getSize() > 0){
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + folderName + File.separator+ dateString ;
			File fileDir = new File(newDir);
			if(!fileDir.exists()){
				fileDir.mkdirs();
			}
			//文件名
			String originalFilename = file.getOriginalFilename();
			originalFilename = PkUtil.getUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			File newFile = new File(fileDir, originalFilename);
			try {
				file.transferTo(newFile);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("保存图片失败！");
			}
			//删除原图片
			if(StringUtil.isNotBlank(oldImg)){
				try {
					oldImg = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldImg;
					File imgFile = new File(oldImg);
					if(imgFile.exists()){
						imgFile.delete();
					}
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("删除图片失败！");
				}
			}
			path = folderName + "/"+dateString+"/"+originalFilename;
		}

		return path;
	}

	@Override
	public Integer searchResDoctorUserCount(Map<String, Object> paramMap) {
		Integer i = this.doctorExtMapper.searchResDoctorUserCount(paramMap);
		if(i==null){
			i = 0;
		}
		return i;
	}

	@Override
	public Integer searchResRegUserCount(Map<String, Object> paramMap) {
		Integer i = this.doctorExtMapper.searchResRegUserCount(paramMap);
		if(i==null){
			i = 0;
		}
		return i;
	}

	@Override
	public int auditBatchDoctor(String userFlow) {
		ResDoctor rd = new ResDoctorExt();
		rd.setDoctorFlow(userFlow);
		rd.setDoctorStatusId(RegStatusEnum.Passed.getId());
		rd.setDoctorStatusName(RegStatusEnum.Passed.getName());
		int count = editDoctor(rd);
		if(count > 0){
			String regYear = InitConfig.getSysCfg("res_reg_year");
			ResReg reg = resRegBiz.searchResReg(userFlow,regYear);
			reg.setStatusId(RegStatusEnum.Passed.getId());
			reg.setStatusName(RegStatusEnum.Passed.getName());
			resRegBiz.editResReg(reg);
			this.msgBiz.addSysMsg(userFlow, "您的报名材料审核结果：通过。", "");
		}
		return count;
	}
	@Override
	public ResDoctor searchResDoctor(String userFlow,String regYear){
		ResDoctor doctor = null;
		ResDoctorExample example = new ResDoctorExample();
		ResDoctorExample.Criteria criteria = example.createCriteria();
		criteria.andDoctorFlowEqualTo(userFlow).andSessionNumberEqualTo(regYear);
		List<ResDoctor> list = doctorMapper.selectByExample(example);
		if (list != null && list.size() >0) {
			doctor = list.get(0);
		}
		return doctor;
	}

	@Override
	public int submitUserInfo(SysUser user, ResDoctor doctor) {
		if(user != null && doctor != null){
			userBiz.saveUser(user);
			editDoctor(doctor);
			String userFlow = user.getUserFlow();
			String regYear = InitConfig.getSysCfg("res_reg_year");
			ResReg reg = resRegBiz.searchResReg(userFlow,regYear);
			if (reg == null) {
				reg = new ResReg();
				reg.setUserFlow(userFlow);
				reg.setUserName(doctor.getDoctorName());
				reg.setRegYear(regYear);
				resRegBiz.editResReg(reg);
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<Map<String,Object>> searchTrainPlanCount(ResDoctor doctor,String countType){
		return doctorExtMapper.searchTrainPlanCount(doctor, countType);
	}

	@Override
	public List<Map<String,Object>> countDocByOrg(String orgName,ResDoctor doctor){
		return doctorExtMapper.countDocByOrg(orgName,doctor);
	}

	@Override
	public int modifyResDoctorRotation(ResDoctor doctor){
		return doctorExtMapper.modifyResDoctorRotation(doctor);
	}

	@Override
	public List<Map<String,String>> searGroupRotation(ResDoctor doctor){
		return doctorExtMapper.searGroupRotation(doctor);
	}

	@Override
	public ResExamDoctor searchExamDoctor(String doctorFlow,String examType,String examYear) {
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("doctorFlow", doctorFlow);
		paramMap.put("examType", examType);
		paramMap.put("examYear", examYear);
		return doctorExtMapper.searchExamDoctor(paramMap);
	}

	@Override
	public List<ResDoctorExt> searchRegisterList(Map<String, Object> paramMap) {
		return doctorExtMapper.searchRegisterList(paramMap);
	}

	@Override
	public void withdrawDoctor(ResDoctor doctor) {
		editDoctor(doctor);
		SysUser exitUser = userBiz.readSysUser(doctor.getDoctorFlow());
		String title = InitConfig.getSysCfg("res_audit_email_title");
		String content = InitConfig.getSysCfg("res_reedit_email_content");
		this.msgBiz.addEmailMsg(exitUser.getUserEmail() , title, content);
	}

	@Override
	public List<ResDoctor> searchMonthRotationDoctor(String schDeptFlow,String month){
		return doctorExtMapper.searchMonthRotationDoctor(schDeptFlow,month);
	}

	@Override
	public int resultAudit(String orgFlow){
		ResDoctorExample example = new ResDoctorExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andSchStatusIdEqualTo(SchStatusEnum.Submit.getId())
		.andOrgFlowEqualTo(orgFlow);

		ResDoctor doctor = new ResDoctor();
		doctor.setSchStatusId(SchStatusEnum.AuditY.getId());
		doctor.setSchStatusName(SchStatusEnum.AuditY.getName());
		doctor.setSchFlag(GlobalConstant.FLAG_Y);

		return doctorMapper.updateByExampleSelective(doctor,example);
	}

	@Override
	public List<ResDoctor> searchSelDeptDoctor(ResDoctor doctor){
		return doctorExtMapper.searchSelDeptDoctor(doctor);
	}

	@Override
	public List<ResDoctor> searchDoctorForChange(ResDoctor doctor,ResDoctorOrgHistory docOrgHis){
		return doctorExtMapper.searchDoctorForChange(doctor,docOrgHis);
	}

	@Override
	public List<ResDoctorExt> searchDocByteacher(Map<String , Object> paramMap){
		return doctorExtMapper.searchDocByteacher(paramMap);
	}
	@Override
	public List<ResDoctor> searchDocByteacher2(Map<String , Object> paramMap){
		return doctorExtMapper.searchDocByteacher2(paramMap);
	}

	@Override
	public void insertRecruitDocInfo(String recruitFlow) {
		doctorExtMapper.insertRecruitDocInfo(recruitFlow);
	}

	@Override
	public void insertRecruitInfo(String recruitFlow) {
		doctorExtMapper.insertRecruitInfo(recruitFlow);
	}

	@Override
	public void insertRecruitDocInfoNotPass(String recruitFlow) {
		doctorExtMapper.insertRecruitDocInfoNotPass(recruitFlow);
	}

	@Override
	public void insertRecruitInfoNotPass(String recruitFlow) {
		doctorExtMapper.insertRecruitInfoNotPass(recruitFlow);
	}

	@Override
	public JsresRecruitDocInfoWithBLOBs selectRecruitDocInfo(String recruitFlow) {
		return doctorExtMapper.selectRecruitDocInfo( recruitFlow);
	}

	@Override
	public JsresRecruitInfo selectRecruitInfo(String recruitFlow) {
		return doctorExtMapper.selectRecruitInfo( recruitFlow);
	}

	@Override
	public void delJsresRecruitDocInfo(String recruitFlow) {
		docInfoMapper.deleteByPrimaryKey(recruitFlow);
	}

	@Override
	public void delJsresRecruitInfo(String recruitFlow) {
		jsresRecruitInfoMapper.deleteByPrimaryKey(recruitFlow);
	}

	@Override
	public JsresRecruitDocInfoWithBLOBs getRecruitDocInfoBySessionNumber(String doctorFlow, String sessionNumber) {
		JsresRecruitDocInfoExample example=new JsresRecruitDocInfoExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow).andSessionNumberEqualTo(sessionNumber);
		List<JsresRecruitDocInfoWithBLOBs> list=docInfoMapper.selectByExampleWithBLOBs(example);
		if(list!=null&&list.size()>0)
		{
			return  list.get(0);
		}
		return null;
	}

	@Override
	public JsresRecruitInfo getRecruitInfoBysessionNumber(String doctorFlow, String sessionNumber) {
		JsresRecruitInfoExample example=new JsresRecruitInfoExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow).andSessionNumberEqualTo(sessionNumber);
		List<JsresRecruitInfo> list=jsresRecruitInfoMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return  list.get(0);
		}
		return null;
	}

	@Override
	public List<ResDoctor> searchDocByteacherJx(Map<String , Object> paramMap){
		return doctorExtMapper.searchDocByteacherJx(paramMap);
	}

	@Override
	public int editDoctorUser(ResDoctor doctor,SysUser user){
		if(doctor!=null && user!=null){
			userBiz.saveUser(user);
			editDoctor(doctor);

			String doctorFlow = doctor.getDoctorFlow();
			if(StringUtil.isNotBlank(doctorFlow)){
				ResDoctorRecruitExample example = new ResDoctorRecruitExample();
				example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow);

				ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
				recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				doctorRecruitMapper.updateByExampleSelective(recruit, example);
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int disabledDoctorUser(ResDoctor doctor,SysUser user){
		if(doctor!=null && user!=null){
			userBiz.saveUser(user);
			editDoctor(doctor);

			String doctorFlow = doctor.getDoctorFlow();
			if(StringUtil.isNotBlank(doctorFlow)){
				//停用志愿
				ResDoctorRecruitExample example = new ResDoctorRecruitExample();
				example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow);
				ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
				recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				doctorRecruitMapper.updateByExampleSelective(recruit, example);

				//停用选科
//				SchDoctorDeptExample docDeptExample = new SchDoctorDeptExample();
//				docDeptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
//				.andDoctorFlowEqualTo(doctorFlow);
//				SchDoctorDept doctorDept = new SchDoctorDept();
//				doctorDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
//				docDeptMapper.updateByExample(doctorDept,docDeptExample);

				//停用排班记录
				SchArrangeResultExample resultExample = new SchArrangeResultExample();
				resultExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow);
				SchArrangeResult result = new SchArrangeResult();
				result.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				resultMapper.updateByExampleSelective(result,resultExample);

				//停用入科记录
				ResDoctorSchProcessExample processExample = new ResDoctorSchProcessExample();
				processExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andUserFlowEqualTo(doctorFlow);
				ResDoctorSchProcess docProcess = new ResDoctorSchProcess();
				docProcess.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				resDoctorProcessMapper.updateByExampleSelective(docProcess,processExample);
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int resetDoctorRecruit(String doctorFlow){
		if (StringUtil.isNotBlank(doctorFlow)) {
			String regYear = InitConfig.getSysCfg("res_reg_year");
			List<ResDoctorRecruit> doctorRecruits = doctorRecruitBiz.findResDoctorRecruits(regYear, doctorFlow);
			ResDoctorRecruitWithBLOBs recruit = null;
			if (doctorRecruits != null && doctorRecruits.size() >0) {
				int size = doctorRecruits.size();
				if (size == 1) {
					recruit = new ResDoctorRecruitWithBLOBs();
					recruit.setRecruitFlow(doctorRecruits.get(0).getRecruitFlow());
					recruit.setAdmitFlag(GlobalConstant.FLAG_N);
					recruit.setRecruitFlag("");
					recruit.setAdmitNotice("");
					this.doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
				} else {
					for (int i=0;i<size;i++) {
						if (i==0) {
							recruit = new ResDoctorRecruitWithBLOBs();
							recruit.setRecruitFlow(doctorRecruits.get(0).getRecruitFlow());
							recruit.setAdmitFlag(GlobalConstant.FLAG_N);
							recruit.setRecruitFlag("");
							recruit.setAdmitNotice("");
							this.doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
						} else {
							ResDoctorRecruit docRecruit = doctorRecruits.get(i);
							docRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
							this.doctorRecruitMapper.updateByPrimaryKey(docRecruit);
						}
					}
				}
			}
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int updateRedundancyData(ResDoctor doctor){
		return doctorExtMapper.updateRedundancyData(doctor);
	}

	@Override
	public int findDoctorCountInOrg(ResDoctor doctor) {
		ResDoctorExample example = new ResDoctorExample();
		ResDoctorExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(doctor.getDoctorStatusId())){
			criteria.andDoctorStatusIdEqualTo(doctor.getDoctorStatusId());
		}
		if(StringUtil.isNotBlank(doctor.getOrgFlow())){
			criteria.andOrgFlowEqualTo(doctor.getOrgFlow());
		}
		if(StringUtil.isNotBlank(doctor.getOrgName())){
			criteria.andOrgNameLike("%"+doctor.getOrgName()+"%");
		}
		if(StringUtil.isNotBlank(doctor.getSessionNumber())){
			criteria.andSessionNumberEqualTo(doctor.getSessionNumber());
		}
		return this.doctorMapper.countByExample(example);
	}

	@Override
	public List<Map<String,Object>> countGroupDoc(ResDoctor doctor){
		return doctorExtMapper.countGroupDoc(doctor);
	}

	@Override
	public int clearSelAndRostering(List<String> doctorFlowList){
		if(doctorFlowList!=null && doctorFlowList.size()>0){
			//删除选科数据
			SchDoctorDept docDept = new SchDoctorDept();
			docDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			SchDoctorDeptExample docDeptExample = new SchDoctorDeptExample();
			docDeptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(doctorFlowList);
			docDeptMapper.updateByExampleSelective(docDept,docDeptExample);

			//删除排班数据
			SchArrangeResult result = new SchArrangeResult();
			result.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			SchArrangeResultExample resultExample = new SchArrangeResultExample();
			resultExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(doctorFlowList);
			resultMapper.updateByExampleSelective(result,resultExample);

			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}


	@Override
	public ResDoctor findByFlow(String doctorFlow) {
		return doctorMapper.selectByPrimaryKey(doctorFlow);
	}

	@Override
	public List<ResDoctorExt> searchDoctorAccountList(SysUser sysUser, SysOrg sysOrg,String baseFlag,String orgFlow,String lockStatus,String trainingSpeId,String trainingTypeId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sysUser", sysUser);
		paramMap.put("sysOrg", sysOrg);
		paramMap.put("baseFlag", baseFlag);
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("lockStatus", lockStatus);
		paramMap.put("trainingSpeId", trainingSpeId);
		paramMap.put("trainingTypeId", trainingTypeId);

		List<ResDoctorExt> doctorExtList = doctorExtMapper.searchDoctorAccountList(paramMap);
		return doctorExtList;
	}

	/**
	 * 学员维护导入
	 */
	@Override
	public int importStudentMainExcel(MultipartFile file,String orgFlow) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseManagerExcel(wb, orgFlow);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	/**
	 * 学员维护导入 排班系统
	 */
	@Override
	public int importStudentExcel(MultipartFile file,String orgFlow) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseManagerStudentExcel(wb, orgFlow);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	private int parseManagerStudentExcel(Workbook wb,String orgFlow) {
		SysUser cuurUser=GlobalContext.getCurrentUser();
		String curorgFlow = cuurUser.getOrgFlow();
		String curorgName = cuurUser.getOrgName();
		SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
		String sysOrgFlow = sysOrg.getOrgFlow();
		String sysOrgName = sysOrg.getOrgName();
		String resDocRole = InitConfig.getSysCfg("res_doctor_role_flow");
		String schDocRole = InitConfig.getSysCfg("sch_doctor_role_flow");
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum()+1;
			if(row_num==1){
				throw new RuntimeException("导入文件内容为空！");
			}
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title);
			}
			for(int i = 1;i <row_num; i++){
				Row r =  sheet.getRow(i);
				SysUser sysUser = new SysUser();
				ResDoctor doctor = new ResDoctor();
				BaseUserResumeExtInfoForm resumeExtInfoForm = new BaseUserResumeExtInfoForm();
				for(int j = 0; j < colnames.size(); j++){
					String value = "";
					String currTitle=colnames.get(j);
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if(cell.getCellType().getCode() == 1){
							value = cell.getStringCellValue().trim();
						}else{
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if("轮转方案".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							doctor.setRotationName(value);
							List<SchRotation> rotationList =schRotationBiz.searchRotationByName(value);
							if(rotationList!=null && rotationList.size()>0){
								SchRotation rotation = rotationList.get(0);
								if(rotation!=null){
									doctor.setRotationFlow(rotation.getRotationFlow());
									sysUser.setMedicineTypeId(rotation.getRotationTypeId());
									sysUser.setMedicineTypeName(rotation.getRotationTypeName());
								}
							}
						}
					}
					else if("二级轮转方案".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							doctor.setSecondRotationName(value);
							List<SchRotation> rotationList =schRotationBiz.searchRotationByName(value);
							if(rotationList!=null && rotationList.size()>0){
								SchRotation rotation = rotationList.get(0);
								if(rotation!=null){
									doctor.setSecondRotationFlow(rotation.getRotationFlow());
								}
							}
						}
					}
					else if("用户名".equals(currTitle)) {
						sysUser.setUserCode(value);
					}else if("培训类别".equals(currTitle)){
						doctor.setDoctorCategoryName(value);
						Object id = parseEnumId(RecDocCategoryEnum.values(),value);
						if(id!=null){
							doctor.setDoctorCategoryId(id.toString());
							doctor.setTrainingTypeId(id.toString());
						}
						doctor.setTrainingTypeName(value);
					}else if("真实姓名".equals(currTitle)){
						sysUser.setUserName(value);
					}else if("性别".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							sysUser.setSexName(value);
							Object id = parseEnumId(UserSexEnum.values(),value);
							if(id!=null){
								sysUser.setSexId(id.toString());
							}
						}
					}
					else if("参培年份".equals(currTitle)){
						doctor.setSessionNumber(value);
					}else if("培养年限".equals(currTitle)){
						doctor.setTrainingYears(value);
					}else if("培训专业".equals(currTitle)){
						doctor.setTrainingSpeName(value);
					}else if("二级专业".equals(currTitle)){
						doctor.setSecondSpeName(value);
						if(StringUtil.isBlank(doctor.getSecondSpeId())){
							doctor.setSecondSpeId(getDictId(value, DictTypeEnum.SecondTrainingSpe.getId()));
						}
					}else if("医师状态".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							doctor.setDoctorStatusName(value);
							doctor.setDoctorStatusId(getDictId(value, DictTypeEnum.DoctorStatus.getId()));
						}
					}else if("证件类型".equals(currTitle)){
						sysUser.setCretTypeName(value);
						Object id = parseEnumId(CertificateTypeEnum.values(),value);
						if(id!=null){
							sysUser.setCretTypeId(id.toString());
						}
					}else if("学员类型".equals(currTitle)){
						doctor.setDoctorTypeName(value);
						doctor.setDoctorTypeId(getDictId(value, DictTypeEnum.DoctorType.getId()));
					}
					else if("证件号码".equals(currTitle)){
						sysUser.setIdNo(value);
					}else if("手机".equals(currTitle)){
						sysUser.setUserPhone(value);
					}else if("邮箱".equals(currTitle)){
						sysUser.setUserEmail(value);
					}else if("民族".equals(currTitle)){
						sysUser.setNationName(value);
						Object id = parseEnumId(UserNationEnum.values(),value);
						if(id!=null){
							sysUser.setNationId(id.toString());
						}
					}
				}

				if(StringUtil.isNotBlank(doctor.getDoctorCategoryId()))
				{
					if(StringUtil.isBlank(doctor.getTrainingSpeId())
							&&StringUtil.isNotBlank(doctor.getTrainingSpeName())) {
						if (RecDocCategoryEnum.ChineseMedicine.getId().equals(doctor.getDoctorCategoryId())) {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.ChineseMedicine.getId()));
						} else if (RecDocCategoryEnum.TCMGeneral.getId().equals(doctor.getDoctorCategoryId())) {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.TCMGeneral.getId()));
						} else if (RecDocCategoryEnum.TCMAssiGeneral.getId().equals(doctor.getDoctorCategoryId())) {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.TCMAssiGeneral.getId()));
						} else {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.DoctorTrainingSpe.getId()));
						}
					}
				}
				if(StringUtil.isBlank(doctor.getDoctorCategoryName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训类型不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorCategoryName())&&StringUtil.isBlank(doctor.getDoctorCategoryId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训类型名称错误！");
				}
				if(RecDocCategoryEnum.ChineseMedicine.getId().equals(doctor.getDoctorCategoryId()))
				{
					if(StringUtil.isBlank(doctor.getSecondSpeName())){
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级专业不能为空！");
					}
					if(StringUtil.isNotBlank(doctor.getSecondSpeName())&&StringUtil.isBlank(doctor.getSecondSpeId())){
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级专业名称错误！");
					}

					if(StringUtil.isBlank(doctor.getSecondRotationName())){
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级轮转方案不能为空！");
					}
					if(StringUtil.isNotBlank(doctor.getSecondRotationName())&&StringUtil.isBlank(doctor.getSecondRotationFlow())){
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级轮转方案名称错误！");
					}
					SchRotation rotation=schRotationBiz.readSchRotation(doctor.getSecondRotationFlow());
					if(rotation==null)
					{
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级轮转方案名称错误！");
					}
					if(!rotation.getSpeId().equals(doctor.getSecondSpeId()))
					{
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级轮转方案与二级专业不匹配！");
					}
				}
				if(StringUtil.isBlank(doctor.getSessionNumber())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，参培年份不能为空！");
				}else{

					List<SysDict> dicts=DictTypeEnum.DoctorSessionNumber.getSysDictList();
					boolean f=false;
					if(dicts!=null&&dicts.size()>0)
					{
						for(SysDict d:dicts)
						{
							if(d.getDictId().equals(doctor.getSessionNumber()))
							{
								f=true;
								break;
							}
						}
					}
					if(!f)
					{
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，参培年份错误！");
					}
				}
				if(StringUtil.isBlank(doctor.getTrainingYears())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培养年限不能为空！");
				}else {
					List<SysDict> dicts=DictTypeEnum.TrainingYears.getSysDictList();
					boolean f=false;
					if(dicts!=null&&dicts.size()>0)
					{
						for(SysDict d:dicts)
						{
							if(d.getDictId().equals(doctor.getTrainingYears()))
							{
								f=true;
								break;
							}
						}
					}
					if(!f)
					{
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，培养年限名称错误！");
					}
				}
				if(StringUtil.isBlank(doctor.getTrainingSpeName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getTrainingSpeName())&&StringUtil.isBlank(doctor.getTrainingSpeId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业名称错误！");
				}
				if(StringUtil.isBlank(sysUser.getUserCode())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，用户名不能为空！");
				}
				if(StringUtil.isBlank(doctor.getDoctorTypeName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，学员类型不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorTypeName())&&StringUtil.isBlank(doctor.getDoctorTypeId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，学员类型文字错误！");
				}
				if(StringUtil.isBlank(doctor.getDoctorStatusName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，医师状态不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorStatusName())&&StringUtil.isBlank(doctor.getDoctorStatusId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，医师状态文字错误！");
				}
				if(StringUtil.isNotBlank(doctor.getRotationName())&&StringUtil.isBlank(doctor.getRotationFlow())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，轮转方案名称错误！");
				}
				SchRotation rotation=schRotationBiz.readSchRotation(doctor.getRotationFlow());
				if(!doctor.getTrainingSpeId().equals(rotation.getSpeId()))
				{
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，轮转方案中的专业与学员的培训专业不一致！");
				}
				//执行保存
				//判断用户id是否重复
				SysUser old = userBiz.findByUserCode(sysUser.getUserCode());
				if(old!=null){
					throw new RuntimeException("导入失败！第"+(count+2) +"行，该用户名已经被注册！");
				}

				if(StringUtil.isNotBlank(sysUser.getIdNo())){
					old = userBiz.findByIdNo(sysUser.getIdNo());
					if(old!=null){
						throw new RuntimeException("导入失败！第"+(count+2) +"行，该身份证号已经被注册！");
					}
				}

				if(StringUtil.isNotBlank(sysUser.getUserPhone())){
					old =userBiz.findByUserPhone(sysUser.getUserPhone());
					if(old!=null){
						throw new RuntimeException("导入失败！第"+(count+2) +"行，该手机号已经被注册！");
					}
				}

				if(StringUtil.isNotBlank(sysUser.getUserEmail())){
					old = userBiz.findByUserEmail(sysUser.getUserEmail());
					if(old!=null){
						throw new RuntimeException("导入失败！第"+(count+2) +"行,该电子邮箱已经被注册！");
					}
				}
				sysUser.setUserFlow(PkUtil.getUUID());
//				sysUser.setOrgFlow(sysOrgFlow);
//				sysUser.setOrgName(sysOrgName);
				userBiz.addUser(sysUser);

				doctor.setDoctorFlow(sysUser.getUserFlow());
				doctor.setDoctorName(sysUser.getUserName());
				doctor.setOrgFlow(sysOrgFlow);
				doctor.setOrgName(sysOrgName);
				doctor.setTrainingTypeName(doctor.getDoctorCategoryName());
				doctor.setTrainingTypeId(doctor.getDoctorCategoryId());
				onlySaveResDoctor(doctor);

				SysUserRole userRole = null;
				if(StringUtil.isNotBlank(sysUser.getUserFlow()) && StringUtil.isNotBlank(schDocRole)){
					userRole = userRoleBiz.readUserRole(sysUser.getUserFlow(),schDocRole);
				}

				userBiz.saveUser(sysUser);

				if(userRole == null){
					userRole = new SysUserRole();
					userRole.setUserFlow(sysUser.getUserFlow());
					userRole.setOrgFlow(sysUser.getOrgFlow());
					String currWsId = (String)GlobalContext.getSessionAttribute(GlobalConstant.PORTALS_WS_ID);
					userRole.setWsId(currWsId);
					userRole.setRoleFlow(schDocRole);
					userRole.setAuthTime(DateUtil.getCurrDate());
					userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
					userRoleBiz.saveSysUserRole(userRole);
				}
				if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sch_inRes_flag")))
				{

					userRole = null;
					if(StringUtil.isNotBlank(sysUser.getUserFlow()) && StringUtil.isNotBlank(resDocRole)){
						userRole = userRoleBiz.readUserRole(sysUser.getUserFlow(),resDocRole);
					}
					if(userRole == null){
						userRole = new SysUserRole();
						userRole.setUserFlow(sysUser.getUserFlow());
						userRole.setOrgFlow(sysUser.getOrgFlow());
						String currWsId =GlobalConstant.RES_WS_ID;
						userRole.setWsId(currWsId);
						userRole.setRoleFlow(resDocRole);
						userRole.setAuthTime(DateUtil.getCurrDate());
						userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
						userRoleBiz.saveSysUserRole(userRole);
					}
				}
				count ++;
			}
		}
		return count;
	}

	private Object parseEnumId(GeneralEnum[] ts,String enumName){
		Object id = null;
		if(StringUtil.isNotBlank(enumName)){
			for(GeneralEnum e : ts){
				String name = e.getName();


				if(enumName.equals(name)){
					id = e.getId();
					break;
				}
			}
		}
		return id;
	}
	/**
	 * 根据字典名称获取Id
	 * @param dictName
	 * @param dictTypeId
	 * @return
	 */
	public String getDictId(String dictName,String dictTypeId){
		Map<String,String> dictNameMap=InitConfig.getDictNameMap(dictTypeId);
		return dictNameMap.get(dictName);
	}
	private int parseManagerExcel(Workbook wb,String orgFlow) {
		SysUser cuurUser=GlobalContext.getCurrentUser();
		String curorgFlow = cuurUser.getOrgFlow();
		String curorgName = cuurUser.getOrgName();
		SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
		String sysOrgFlow = sysOrg.getOrgFlow();
		String sysOrgName = sysOrg.getOrgName();
		String resDocRole = InitConfig.getSysCfg("res_doctor_role_flow");
		String schDocRole = InitConfig.getSysCfg("sch_doctor_role_flow");
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum()+1;
			if(row_num==1){
				throw new RuntimeException("导入文件内容为空！");
			}
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title);
			}
			for(int i = 1;i <row_num; i++){
				Row r =  sheet.getRow(i);
				SysUser sysUser = new SysUser();
				ResDoctor doctor = new ResDoctor();
				BaseUserResumeExtInfoForm resumeExtInfoForm = new BaseUserResumeExtInfoForm();
				for(int j = 0; j < colnames.size(); j++){
					String value = "";
					String currTitle=colnames.get(j);
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if(cell.getCellType().getCode() == 1){
							value = cell.getStringCellValue().trim();
						}else{
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if("轮转方案".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							doctor.setRotationName(value);
							List<SchRotation> rotationList =schRotationBiz.searchRotationByName(value);
							if(rotationList!=null && rotationList.size()>0){
								SchRotation rotation = rotationList.get(0);
								if(rotation!=null){
									doctor.setRotationFlow(rotation.getRotationFlow());
									sysUser.setMedicineTypeId(rotation.getRotationTypeId());
									sysUser.setMedicineTypeName(rotation.getRotationTypeName());
								}
							}
						}
					}
					else if("用户名".equals(currTitle)) {
						sysUser.setUserCode(value);
					}else if("培训类别".equals(currTitle)){
						doctor.setDoctorCategoryName(value);
						Object id = parseEnumId(RecDocCategoryEnum.values(),value);
						if(id!=null){
							doctor.setDoctorCategoryId(id.toString());
							doctor.setTrainingTypeId(id.toString());
						}
						doctor.setTrainingTypeName(value);
						doctor.setDegreeCategoryName(value);
					}else if("真实姓名".equals(currTitle)){
						sysUser.setUserName(value);
					}else if("性别".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							sysUser.setSexName(value);
							Object id = parseEnumId(UserSexEnum.values(),value);
							if(id!=null){
								sysUser.setSexId(id.toString());
							}
						}
					}
					else if("参培年份".equals(currTitle)){
						doctor.setSessionNumber(value);
					}else if("培养年限".equals(currTitle)){
						doctor.setTrainingYears(value);
					}else if("培训专业".equals(currTitle)){
						doctor.setTrainingSpeName(value);
					}else if("医师状态".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							doctor.setDoctorStatusName(value);
							doctor.setDoctorStatusId(getDictId(value, DictTypeEnum.DoctorStatus.getId()));
						}
					}else if("证件类型".equals(currTitle)){
						sysUser.setCretTypeName(value);
						Object id = parseEnumId(CertificateTypeEnum.values(),value);
						if(id!=null){
							sysUser.setCretTypeId(id.toString());
						}
					}else if("学员类型".equals(currTitle)){
						doctor.setDoctorTypeName(value);
						doctor.setDoctorTypeId(getDictId(value, DictTypeEnum.DoctorType.getId()));
					}
					else if("证件号码".equals(currTitle)){
						sysUser.setIdNo(value);
					}else if("手机".equals(currTitle)){
						sysUser.setUserPhone(value);
					}else if("邮箱".equals(currTitle)){
						sysUser.setUserEmail(value);
					}else if("民族".equals(currTitle)){
						sysUser.setNationName(value);
						Object id = parseEnumId(UserNationEnum.values(),value);
						if(id!=null){
							sysUser.setNationId(id.toString());
						}
					}else if("是否取得执业资格证".equals(currTitle)){
						doctor.setDoctorLicenseFlag(value);
					}else if("执业医师资格证号".equals(currTitle)){
						doctor.setDoctorLicenseNo(value);
					}else if("工作单位".equals(currTitle)){
						doctor.setWorkOrgName(value);
					}else if("实际培训开始时间".equals(currTitle)){
						doctor.setInHosDate(value);
					}else if("结业考核年份".equals(currTitle)){
						doctor.setGraduationYear(value);
					}else if("年龄".equals(currTitle)){
						resumeExtInfoForm.setAge(value);
					}else if("医疗卫生机构".equals(currTitle)){
						resumeExtInfoForm.setMedicalInstitution(value);
					}else if("基层医疗卫生机构类型".equals(currTitle)){
						resumeExtInfoForm.setBasicmedicalInstitutionType(value);
					}else if("本科毕业院校".equals(currTitle)){
						resumeExtInfoForm.setGraduatedCollegeName(value);
					}else if("本科毕业年份".equals(currTitle)){
						resumeExtInfoForm.setGraduatedCollegeYear(value);
					}else if("本科毕业专业".equals(currTitle)){
						resumeExtInfoForm.setGraduatedCollegeMajor(value);
					}else if("是否为应届生".equals(currTitle)){
						doctor.setIsYearGraduate(value);
					}else if("是否全科订单定向学员".equals(currTitle)){
						resumeExtInfoForm.setIsGeneralOrderOrientationTrainee(value);
					}else if("本科阶段学位".equals(currTitle)){
						doctor.setDegreeCategoryName(value);
					}else if("是否为硕士".equals(currTitle)){
						resumeExtInfoForm.setIsMaster(value);
					}else if("硕士毕业院校".equals(currTitle)){
						resumeExtInfoForm.setGraduatedMasterName(value);
					}else if("硕士毕业年份".equals(currTitle)){
						resumeExtInfoForm.setGraduatedMasterYear(value);
					}else if("硕士毕业专业".equals(currTitle)){
						resumeExtInfoForm.setGraduatedMasterMajor(value);
					}else if("硕士阶段学位".equals(currTitle)){
						resumeExtInfoForm.setMasterDegreeCategory(value);
					}else if("硕士学位类型".equals(currTitle)){
						resumeExtInfoForm.setMasterDegreeType(value);
					}else if("是否为博士".equals(currTitle)){
						resumeExtInfoForm.setIsDoctor(value);
					}else if("博士毕业院校".equals(currTitle)){
						resumeExtInfoForm.setGraduatedDoctorName(value);
					}else if("博士毕业年份".equals(currTitle)){
						resumeExtInfoForm.setGraduatedDoctorYear(value);
					}else if("博士毕业专业".equals(currTitle)){
						resumeExtInfoForm.setGraduatedDoctorMajor(value);
					}else if("博士阶段学位".equals(currTitle)){
						resumeExtInfoForm.setDoctorDegreeCategory(value);
					}else if("博士学位类型".equals(currTitle)) {
						resumeExtInfoForm.setDoctorDegreeType(value);
					}
//					else if("人员分类".equals(currTitle)){
//						doctor.setDoctorTypeName(value);
//						if(StringUtil.isBlank(doctor.getDoctorTypeId())){
//							doctor.setDoctorTypeId(getDictId(value, DictTypeEnum.DoctorType.getId()));
//						}
//					}
				}
				if(StringUtil.isNotBlank(doctor.getDoctorCategoryId()))
				{
					if(StringUtil.isBlank(doctor.getTrainingSpeId())
							&&StringUtil.isNotBlank(doctor.getTrainingSpeName())) {
						if (RecDocCategoryEnum.ChineseMedicine.getId().equals(doctor.getDoctorCategoryId())) {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.ChineseMedicine.getId()));
						} else if (RecDocCategoryEnum.TCMGeneral.getId().equals(doctor.getDoctorCategoryId())) {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.TCMGeneral.getId()));
						} else if (RecDocCategoryEnum.TCMAssiGeneral.getId().equals(doctor.getDoctorCategoryId())) {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.TCMAssiGeneral.getId()));
						} else {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.DoctorTrainingSpe.getId()));
						}
					}
				}

				if(StringUtil.isBlank(doctor.getDoctorCategoryName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训类型不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorCategoryName())&&StringUtil.isBlank(doctor.getDoctorCategoryId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训类型名称错误！");
				}
				if(StringUtil.isBlank(doctor.getSessionNumber())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，参培年份不能为空！");
				}else{

					List<SysDict> dicts=DictTypeEnum.DoctorSessionNumber.getSysDictList();
					boolean f=false;
					if(dicts!=null&&dicts.size()>0)
					{
						for(SysDict d:dicts)
						{
							if(d.getDictId().equals(doctor.getSessionNumber()))
							{
								f=true;
								break;
							}
						}
					}
					if(!f)
					{
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，参培年份错误！");
					}
				}
				if(StringUtil.isBlank(doctor.getTrainingYears())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培养年限不能为空！");
				}else {
					List<SysDict> dicts=DictTypeEnum.TrainingYears.getSysDictList();
					boolean f=false;
					if(dicts!=null&&dicts.size()>0)
					{
						for(SysDict d:dicts)
						{
							if(d.getDictId().equals(doctor.getTrainingYears()))
							{
								f=true;
								break;
							}
						}
					}
					if(!f)
					{
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，培养年限名称错误！");
					}
				}
				if(StringUtil.isBlank(doctor.getTrainingSpeName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getTrainingSpeName())&&StringUtil.isBlank(doctor.getTrainingSpeId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业名称错误！");
				}
				if(StringUtil.isBlank(sysUser.getUserCode())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，用户名不能为空！");
				}
				if(StringUtil.isBlank(doctor.getDoctorTypeName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，人员类型不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorTypeName())&&StringUtil.isBlank(doctor.getDoctorTypeId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，人员类型文字错误！");
				}
				if(StringUtil.isBlank(doctor.getDoctorStatusName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，医师状态不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorStatusName())&&StringUtil.isBlank(doctor.getDoctorStatusId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，医师状态文字错误！");
				}
				if(StringUtil.isNotBlank(doctor.getRotationName())&&StringUtil.isBlank(doctor.getRotationFlow())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，轮转方案名称错误！");
				}
				SchRotation rotation=schRotationBiz.readSchRotation(doctor.getRotationFlow());
				if(!doctor.getTrainingSpeId().equals(rotation.getSpeId()))
				{
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，轮转方案中的专业与学员的培训专业不一致！");
				}
				//执行保存
				//判断用户id是否重复
				SysUser old = userBiz.findByUserCode(sysUser.getUserCode());
				if(old!=null){
					throw new RuntimeException("导入失败！第"+(count+2) +"行，该用户名已经被注册！");
				}

				if(StringUtil.isNotBlank(sysUser.getIdNo())){
					old = userBiz.findByIdNo(sysUser.getIdNo());
					if(old!=null){
						throw new RuntimeException("导入失败！第"+(count+2) +"行，该身份证号已经被注册！");
					}
				}

				if(StringUtil.isNotBlank(sysUser.getUserPhone())){
					old =userBiz.findByUserPhone(sysUser.getUserPhone());
					if(old!=null){
						throw new RuntimeException("导入失败！第"+(count+2) +"行，该手机号已经被注册！");
					}
				}

				if(StringUtil.isNotBlank(sysUser.getUserEmail())){
					old = userBiz.findByUserEmail(sysUser.getUserEmail());
					if(old!=null){
						throw new RuntimeException("导入失败！第"+(count+2) +"行,该电子邮箱已经被注册！");
					}
				}
				sysUser.setUserFlow(PkUtil.getUUID());
				sysUser.setOrgFlow(sysOrgFlow);
				sysUser.setOrgName(sysOrgName);
				userBiz.addUser(sysUser);
//					ResDoctor doctor = new ResDoctor();
				doctor.setDoctorFlow(sysUser.getUserFlow());
				doctor.setDoctorName(sysUser.getUserName());
				doctor.setOrgFlow(sysOrgFlow);
				doctor.setOrgName(sysOrgName);
				onlySaveResDoctor(doctor);

				SysUserRole userRole = null;
				if(StringUtil.isNotBlank(sysUser.getUserFlow()) && StringUtil.isNotBlank(schDocRole)){
					userRole = userRoleBiz.readUserRole(sysUser.getUserFlow(),schDocRole);
				}

				if(userRole == null){
					userRole = new SysUserRole();
					userRole.setUserFlow(sysUser.getUserFlow());
					userRole.setOrgFlow(sysUser.getOrgFlow());
					String currWsId = (String)GlobalContext.getSessionAttribute(GlobalConstant.PORTALS_WS_ID);
					userRole.setWsId(currWsId);
					userRole.setRoleFlow(schDocRole);
					userRole.setAuthTime(DateUtil.getCurrDate());
					userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
					userRoleBiz.saveSysUserRole(userRole);
				}
				if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sch_inRes_flag")))
				{

					userRole = null;
					if(StringUtil.isNotBlank(sysUser.getUserFlow()) && StringUtil.isNotBlank(resDocRole)){
						userRole = userRoleBiz.readUserRole(sysUser.getUserFlow(),resDocRole);
					}
					if(userRole == null){
						userRole = new SysUserRole();
						userRole.setUserFlow(sysUser.getUserFlow());
						userRole.setOrgFlow(sysUser.getOrgFlow());
						String currWsId =GlobalConstant.RES_WS_ID;
						userRole.setWsId(currWsId);
						userRole.setRoleFlow(resDocRole);
						userRole.setAuthTime(DateUtil.getCurrDate());
						userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
						userRoleBiz.saveSysUserRole(userRole);
					}
				}
				count ++;
			}
		}
		return count;
	}

	private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
		// 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
		if (!inS.markSupported()) {
			// 还原流信息
			inS = new PushbackInputStream(inS);
		}
//		// EXCEL2003使用的是微软的文件系统
//		if (POIFSFileSystem.hasPOIFSHeader(inS)) {
//			return new HSSFWorkbook(inS);
//		}
//		// EXCEL2007使用的是OOM文件格式
//		if (POIXMLDocument.hasOOXMLHeader(inS)) {
//			// 可以直接传流参数，但是推荐使用OPCPackage容器打开
//			return new XSSFWorkbook(OPCPackage.open(inS));
//		}
		try{
			return WorkbookFactory.create(inS);
		}catch (Exception e) {
			throw new IOException("不能解析的excel版本");
		}
	}

	@Override
	public List<ResDoctor> searchReductionDoc(List<String> degreeTypes,
											  String sessionNumber,
											  List<String> trainingYears,
											  String trainType,
											  List<String> orgFlows,
											  String doctorName){
		ResDoctorExample example = new ResDoctorExample();
		ResDoctorExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andOrgFlowIn(orgFlows).andSessionNumberGreaterThanOrEqualTo(sessionNumber)
				.andDegreeCategoryIdIn(degreeTypes).andTrainingTypeIdEqualTo(trainType)
				.andTrainingYearsIn(trainingYears);
		if(StringUtil.isNotBlank(doctorName)){
			criteria.andDoctorNameLike("%"+doctorName+"%");
		}

		return doctorMapper.selectByExample(example);
	}

	@Override
	public List<ResSignin> searchSignList(List<String> deptFlows,String signDate) {
		ResSigninExample signExample = new ResSigninExample();
		com.pinde.sci.model.mo.ResSigninExample.Criteria criteria =
				signExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDeptFlowIn(deptFlows);
		if(StringUtil.isNotBlank(signDate)){
			criteria.andSignDateEqualTo(signDate);
		}
		signExample.setOrderByClause("create_time desc");
		return signinMapper.selectByExample(signExample);
	}

	@Override
	public List<Map<String, Object>> schDoctorQuery(String teacherUserFlow, String startDate, String endDate, List<String> typeList, String workOrgId) {
		return doctorExtMapper.schDoctorQuery(teacherUserFlow, startDate, endDate,typeList, workOrgId);
	}
	@Override
	public List<Map<String, Object>> chTeacherProcessFlowRec(String teacherUserFlow, String startDate, String endDate, List<String> typeList) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("teacherUserFlow", teacherUserFlow);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("typeList", typeList);
		return doctorExtMapper.chTeacherProcessFlowRec(paramMap);
	}

	@Override
	public List<Map<String, Object>> searchRegStuLst(Map<String, Object> parMp) {
		return doctorExtMapper.searchRegStuLst(parMp);
	}

	@Override
	public void saveInfo(ReplenishInfoForm form) {
		SysUser user = form.getUser();
		userBiz.saveUser(user);//保存民族必填信息
		this.editDoctor(form.getDoctor());//保存医师必填信息
		//保存额外信息
		PubUserResume resume = resumpMapper.selectByPrimaryKey(user.getUserFlow());
		String content = getXmlFromExtInfo(form.getExtInfo());
		if(resume==null){
			resume = new PubUserResume();
			resume.setUserFlow(user.getUserFlow());
			resume.setUserName(user.getUserName());
			resume.setUserResume(content);
			GeneralMethod.setRecordInfo(resume, true);
			this.resumpMapper.insertSelective(resume);
		}else{
			resume.setUserResume(content);
			GeneralMethod.setRecordInfo(resume,false);
			this.resumpMapper.updateByPrimaryKeySelective(resume);
		}
	}
	/**
	 * 获取额外信息xml
	 * @param extInfo
	 * @return
	 */
	private String getXmlFromExtInfo(ExtInfoForm extInfo){
		String xmlBody = null;
		if(extInfo!=null){
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement(EXT_INFO_ROOT);
			Element extInfoForm = root.addElement(EXT_INFO_ELE);
			Map<String,String> filedMap = getClassFieldMap(extInfo);
			if(filedMap!=null && filedMap.size()>0){
				for(String key : filedMap.keySet()){
					Element item = extInfoForm.addElement(key);
					item.setText(filedMap.get(key));
				}
			}
			xmlBody=doc.asXML();
		}
		return xmlBody;
	}

	/**
	 * 获取属性名和值
	 * @param obj
	 * @return
	 */
	private Map<String,String> getClassFieldMap(Object obj){
		Map<String,String> filedMap = null;
		if(obj!=null){
			try{
				filedMap = new HashMap<String, String>();
				String stringClassName = String.class.getSimpleName();
				Class<?> objClass = obj.getClass();
				Field[] fileds = objClass.getDeclaredFields();
				for(Field f : fileds){
					String typeName = f.getType().getSimpleName();
					if(stringClassName.equals(typeName)){
						String attrName = f.getName();
						String firstLetter = attrName.substring(0,1).toUpperCase();
						String methedName = "get"+firstLetter+attrName.substring(1);
						Method getMethod = objClass.getMethod(methedName);
						String value = (String)getMethod.invoke(obj);
						filedMap.put(attrName,StringUtil.defaultString(value));
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return filedMap;
	}

	/**
	 * 解析额外信息xml
	 */
	@Override
	public ExtInfoForm parseExtInfoXml(String extInfoXml){
		ExtInfoForm extInfo = null;
		if(StringUtil.isNotBlank(extInfoXml)){
			try{
				extInfo = new ExtInfoForm();
				Document doc = DocumentHelper.parseText(extInfoXml);
				Element root = doc.getRootElement();
				Element extInfoFormEle = root.element(EXT_INFO_ELE);
				if(extInfoFormEle!=null){
					List<Element> extInfoAttrEles = extInfoFormEle.elements();
					if(extInfoAttrEles!=null && extInfoAttrEles.size()>0){
						for(Element attr : extInfoAttrEles){
							String attrName = attr.getName();
							String attrValue = attr.getText();
							setValue(extInfo,attrName,attrValue);
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return extInfo;
	}

	/**
	 * 为对象自动复制
	 */
	private void setValue(Object obj,String attrName,String attrValue){
		try{
			Class<?> objClass = obj.getClass();
			String firstLetter = attrName.substring(0,1).toUpperCase();
			String methedName = "set"+firstLetter+attrName.substring(1);
			Method setMethod = objClass.getMethod(methedName,new Class[] {String.class});
			setMethod.invoke(obj,new Object[] {attrValue});
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@Override
	public ExcelUtile  importCourseFromExcel(MultipartFile file, String scoreYear, String trainingTypeId) {
		List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
		List<ResScore> resScoreList = new ArrayList<>();
		final String[] msg = {""};
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			//map中的keys  个数与execl中表头字段数量一致
			final String[] keys = {
					"testId",
					"userName",
					"idKind",
					"idNo",
					"doctorType",
					"sessionNumber",
					"realScore",
					"theoryScore"
			};
			final String a = scoreYear;
			ExcelUtile excelUtile = ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<Map<String, Object>> datas = eu.getExcelDatas();
					int count = 0;
					String code = "0";
					List<ResScore> list = new ArrayList<ResScore>();
					for (int i = 0; i < datas.size(); i++) {
						Map<String, Object> data = datas.get(i);
						ResDoctor resDoctor = new ResDoctor();//学生
						ResScore resScore = new ResScore();//成绩
						SysUser sysUser = new SysUser();
						String score;//成绩
						String scoreYear = a;
						resDoctor.setSessionNumber(scoreYear);
						for (String key : data.keySet()) {
							Map<String, Object> map = new HashMap<String, Object>();
							String value = (String) data.get(key);
							if ("idNo".equals(key)) {
								sysUser.setIdNo(value);
							} else if ("theoryScore".equals(key)) {
								if ("合格".equals(value)) {
									resScore.setTheoryScore(BigDecimal.valueOf(Double.valueOf(1)));
								} else if ("不合格".equals(value)) {
									resScore.setTheoryScore(BigDecimal.valueOf(Double.valueOf(0)));
								} else if ("缺考".equals(value)) {
									resScore.setTheoryScore(BigDecimal.valueOf(Double.valueOf(2)));
								}
							} else if ("testId".equals(key)) {
								resScore.setTestId(value);
							} else if ("sessionNumber".equals(key)) {
								resScore.setSessionNumber(value);
							} else if ("realScore".equals(key)) {
								resScore.setRealScore(value);
							}
						}

						//根据学生身份证号
						//sysUser.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
						sysUser = userBiz.findByIdNo(sysUser.getIdNo());
						if (sysUser != null) {
							resDoctor.setDoctorFlow(sysUser.getUserFlow());
							ResDoctorRecruitExample example = new ResDoctorRecruitExample();
							example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(sysUser.getUserFlow()).andSessionNumberEqualTo(resScore.getSessionNumber());
							List<ResDoctorRecruit> resDoctorRecruits = doctorRecruitMapper.selectByExample(example);
							if (CollectionUtils.isNotEmpty(resDoctorRecruits)) {
								resScore.setRecruitFlow(resDoctorRecruits.get(0).getRecruitFlow());
							}

							resScore.setDoctorFlow(resDoctor.getDoctorFlow());//医师流水号
							resScore.setScorePhaseId(scoreYear);//年份或阶段id
							resScore.setScorePhaseName(scoreYear);//年份或阶段name
							resScore.setScoreTypeId(ResScoreTypeEnum.TheoryScore.getId());
							resScore.setScoreTypeName(ResScoreTypeEnum.TheoryScore.getName());
						}
						resScoreList.add(resScore);
						count++;
					}
					eu.put("code", code);
					eu.put("count", count);
				}


				@Override
				public String checkExcelData(HashMap data, ExcelUtile eu) {
					String sheetName = (String) eu.get("SheetName");
					if (sheetName == null || !"TheoryScore".equals(sheetName)) {
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "请使用系统提供的理论成绩模板！！");
						return ExcelUtile.RETURN;
					}
					SysUser sysUser = new SysUser();
					String sessionNumber = "";
					int rowNum = (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					for (Object key1 : data.keySet()) {
						String key = (String) key1;
						Map<String, Object> map = new HashMap<String, Object>();
						String value = (String) data.get(key);
						if ("idNo".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行身份证号为空，请确认后提交！！</br>";
								return null;
							}
							sysUser.setIdNo(value);
						} else if ("theoryScore".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行成绩是否合格为空，请确认后提交！！</br>";
								return null;
							}
							if (!StringUtil.isBlank(value)) {
								String flay = checkTheroyScore(value, "成绩", rowNum, eu);
								if (null != flay) {
									return ExcelUtile.RETURN;
								}
							}
						} else if ("testId".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行考试编号为空，请确认后提交！！</br>";
								return null;
							} else {
								if (resTestConfigs != null && resTestConfigs.size() > 0) {
									//判断导入的考试编号在系统中存不存在
									List<ResTestConfig> resTestConfigList = resTestConfigs.stream().filter(resTestConfig -> value.equals(resTestConfig.getTestId())).collect(Collectors.toList());
									if (resTestConfigList == null || resTestConfigList.size() == 0) {
										msg[0] += "导入文件第" + (rowNum + 1) + "行考试编号在系统中不存在！！</br>";
										return null;
									}
								} else {
									msg[0] += "当前没有任何考试！！</br>";
									return null;
								}
							}
						} else if ("sessionNumber".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行学员届别为空，请确认后提交！！</br>";
								return null;
							}
							sessionNumber = value;
						} else if ("userName".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行学员姓名为空，请确认后提交！！</br>";
								return null;
							}
							sysUser.setUserName(value);
						} else if ("idKind".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行证件类型为空，请确认后提交！！</br>";
								return null;
							}
							sysUser.setCretTypeName(value);
						} else if ("doctorType".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行培训类别为空，请确认后提交！！</br>";
								return null;
							}
							sysUser.setTrainingTypeName(value);
						} else if ("realScore".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行成绩为空，请确认后提交！！</br>";
								return null;
							}
						}
					}
					//校验学生是否存在
					String flay = checkUser(sysUser, rowNum, eu, sessionNumber, a, trainingTypeId, msg);
					if (null != flay) {
						return ExcelUtile.RETURN;
					}
					return null;
				}
			});
			if (StringUtil.isNotBlank(msg[0])) {
				excelUtile.put("code", "1");
				excelUtile.put("count", 0);
				excelUtile.put("msg", msg[0]);
			}
			String code= (String) excelUtile.get("code");
			if ("0".equals(code)) {
				for (ResScore resScore : resScoreList) {
					save(resScore);
				}
			}
			return excelUtile;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}

	private String checkUser(SysUser sysUser, int rowNum, ExcelUtile eu, String sessionNumber, String scoreYear, String trainingTypeId, String[] msg) {
		String cretTypeName = sysUser.getCretTypeName();
		String userName = sysUser.getUserName();
		String trainingTypeName = sysUser.getTrainingTypeName();
		//根据学生身份证号
		//sysUser.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
		sysUser=userBiz.findByIdNo(sysUser.getIdNo());
		if(null==sysUser)
		{
			msg[0] += "导入文件第"+(rowNum+1)+"行,证件号所属学生不存在，请确认后提交！！</br>";
			return null;
		}
		if (!cretTypeName.equals(sysUser.getCretTypeName())) {
			msg[0] += "导入文件第"+(rowNum+1)+"行,证件类型与所属学生存储的证件类型不符合，请确认后提交！！</br>";
			return null;
		}
		if (!userName.equals(sysUser.getUserName())) {
			msg[0] += "导入文件第"+(rowNum+1)+"行,姓名与证件号所属学生不符合，请确认后提交！！</br>";
			return null;
		}
		if (!trainingTypeName.equals(sysUser.getTrainingTypeName())) {
			msg[0] += "导入文件第"+(rowNum+1)+"行,培训类别与证件号所属学生不符合，请确认后提交！！</br>";
			return null;
		}
		if (!trainingTypeId.equals(sysUser.getTrainingTypeId())) {
			msg[0] += "导入文件第"+(rowNum+1)+"行,培训类别与证件号所属学生不符合，请确认后提交！！</br>";
			return null;
		}
		ResDoctor resDoctor=searchByUserFlow(sysUser.getUserFlow());
		if(null==resDoctor)
		{
			msg[0] += "导入文件第"+(rowNum+1)+"行,身份证号所属学生医师信息不存在，请确认后提交！！</br>";
			return null;
		}
		if(StringUtil.isBlank(resDoctor.getOrgFlow())){
			msg[0] += "导入文件第"+(rowNum+1)+"行,身份证号所属学生暂未参加培训，请确认后提交！！</br>";
			return null;
		}
		if (StringUtil.isNotBlank(sessionNumber)) {
			ResDoctorRecruitExample example = new ResDoctorRecruitExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(sysUser.getUserFlow()).andSessionNumberEqualTo(sessionNumber);
			List<ResDoctorRecruit> resDoctorRecruits = doctorRecruitMapper.selectByExample(example);
			if (resDoctorRecruits.size() == 0) {
				msg[0] += "导入文件第" + (rowNum + 1) + "行,该学员在当前届别下没有培训记录，请确认后提交！！</br>";
				return null;
			}
			if (resDoctorRecruits.size() != 0 && resDoctorRecruits.size() != 1) {
				msg[0] += "导入文件第" + (rowNum + 1) + "行,该学员培训数据出现异常，请确认后提交！！</br>";
				return null;
			}

			if (StringUtil.isNotBlank(scoreYear)) {
				JsresGraduationApplyExample applyExample = new JsresGraduationApplyExample();
				applyExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andApplyYearEqualTo(scoreYear).andRecruitFlowEqualTo(resDoctorRecruits.get(0).getRecruitFlow()).andAuditStatusIdEqualTo("GlobalPassed");
				List<JsresGraduationApply> applyList = applyMapper.selectByExample(applyExample);

				JsresExamSignupExample signupExample = new JsresExamSignupExample();
				signupExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andSignupYearEqualTo(scoreYear).andDoctorFlowEqualTo(sysUser.getUserFlow()).andAuditStatusIdEqualTo("GlobalPassed");
				List<JsresExamSignup> signupList = signupMapper.selectByExample(signupExample);

				if (CollectionUtils.isEmpty(applyList) && CollectionUtils.isEmpty(signupList)) {
					msg[0] += "导入文件第" + (rowNum + 1) + "行,该学员没有被审核通过的考试申请，请确认后提交！！</br>";
					return null;
				}
			}
		}
		return  null;
	}

	/**
	 * 技能成绩导入处理
	 * @param file
	 * @param scoreYear
	 * @return
	 */
	@Override
	public ExcelUtile  importSkillScoreFromExcel(MultipartFile file, String scoreYear, String trainingTypeId) {
		List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
		List<ResScore> resScoreList = new ArrayList<>();
		final String[] msg = {""};
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			//map中的keys  个数与execl中表头字段数量一致
			final String[] keys = {
					"testId",
					"userName",
					"idKind",
					"idNo",
					"doctorType",
					"sessionNumber",
					"realScore",
					"skillScore"
			};
			final String a = scoreYear;
			ExcelUtile excelUtile = ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<Map<String,Object>> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					List<ResScore> list=new ArrayList<ResScore>();
					for(int i=0;i<datas.size();i++) {
						Map<String, Object> data=datas.get(i);
						ResDoctor resDoctor = new ResDoctor();//学生
						ResScore resScore=new ResScore();//成绩
						SysUser sysUser=new SysUser();
//						Map<String,String> extScoreMap=new HashMap<String, String>();//各个站的成绩
						String scoreYear=a;
						resDoctor.setSessionNumber(scoreYear);
						for (String key : data.keySet()) {
							String value=(String) data.get(key);
							if("idNo".equals(key))
							{
								sysUser.setIdNo(value);
							}else if("skillScore".equals(key)){
								if("合格".equals(value)) {
									resScore.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.PASS)));
								}else if("不合格".equals(value)) {
									resScore.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.UNPASS)));
								}else if("缺考".equals(value)){
									resScore.setSkillScore(BigDecimal.valueOf(Double.valueOf(2)));
								}
							}else if("testId".equals(key)){
								resScore.setTestId(value);
							}else if("sessionNumber".equals(key)){
								resScore.setSessionNumber(value);
							} else if ("realScore".equals(key)) {
								resScore.setRealScore(value);
							}
						}
						//根据学生身份证号
						//sysUser.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
						sysUser=userBiz.findByIdNo(sysUser.getIdNo());
						if (sysUser != null) {
							resDoctor.setDoctorFlow(sysUser.getUserFlow());

							ResDoctorRecruitExample example = new ResDoctorRecruitExample();
							example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(sysUser.getUserFlow()).andSessionNumberEqualTo(resScore.getSessionNumber());
							List<ResDoctorRecruit> resDoctorRecruits = doctorRecruitMapper.selectByExample(example);
							if (CollectionUtils.isNotEmpty(resDoctorRecruits)) {
								resScore.setRecruitFlow(resDoctorRecruits.get(0).getRecruitFlow());
							}

							resScore.setDoctorFlow(resDoctor.getDoctorFlow());//医师流水号
							resScore.setScorePhaseId(scoreYear);//年份或阶段id
							resScore.setScorePhaseName(scoreYear);//年份或阶段name
							resScore.setScoreTypeId(ResScoreTypeEnum.SkillScore.getId());
							resScore.setScoreTypeName(ResScoreTypeEnum.SkillScore.getName());
						}
						//处理各个站的成绩
						/*String extScore= null;
						try {
							extScore = convertMapToXml(extScoreMap,resScore);
						} catch (Exception e) {
							e.printStackTrace();
						}
						resScore.setExtScore(extScore);*/
						resScoreList.add(resScore);
						count++;
						//list.add(resScore);
					}
					//saveBylist(list);
					eu.put("code",code);
					eu.put("count",count);
				}

				@Override
				public String checkExcelData(HashMap data, ExcelUtile eu) {
					String sheetName=(String)eu.get("SheetName");
					if(sheetName==null||!"SkillScore".equals(sheetName))
					{
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "请使用系统提供的技能成绩模板！！");
						return ExcelUtile.RETURN;
					}
					SysUser sysUser=new SysUser();
					String sessionNumber = "";
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					for (Object key1 : data.keySet()) {
						String key=(String) key1;
						String value=(String) data.get(key);
						if("idNo".equals(key))
						{
							if(StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行身份证号为空，请确认后提交！！</br>";
								return null;
							}
							sysUser.setIdNo(value);
						} else if ("skillScore".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行成绩为空，请确认后提交！！</br>";
								return null;
							}
						}else if("testId".equals(key)){
							if(StringUtil.isBlank(value)){
								msg[0] += "导入文件第" + (rowNum + 1) + "行考试编号为空，请确认后提交！！</br>";
								return null;
							} else {
								if (resTestConfigs != null && resTestConfigs.size() > 0) {
									//判断导入的考试编号在系统中存不存在
									List<ResTestConfig> resTestConfigList = resTestConfigs.stream().filter(resTestConfig -> value.equals(resTestConfig.getTestId())).collect(Collectors.toList());
									if (resTestConfigList == null || resTestConfigList.size() == 0) {
										msg[0] += "导入文件第" + (rowNum + 1) + "行考试编号在系统中不存在！！</br>";
										return null;
									}
								} else {
									msg[0] += "当前没有任何考试！！</br>";
									return null;
								}
							}
						} else if ("sessionNumber".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行学员届别为空，请确认后提交！！</br>";
								return null;
							}
							sessionNumber = value;
						} else if ("userName".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行学员姓名为空，请确认后提交！！</br>";
								return null;
							}
							sysUser.setUserName(value);
						} else if ("idKind".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行证件类型为空，请确认后提交！！</br>";
								return null;
							}
							sysUser.setCretTypeName(value);
						} else if ("doctorType".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行培训类别为空，请确认后提交！！</br>";
								return null;
							}
							sysUser.setTrainingTypeName(value);
						} else if ("realScore".equals(key)) {
							if (StringUtil.isBlank(value)) {
								msg[0] += "导入文件第" + (rowNum + 1) + "行成绩为空，请确认后提交！！</br>";
								return null;
							}
						}
					}
					//校验学生是否存在
					String flay=checkUser(sysUser,rowNum,eu, sessionNumber, a, trainingTypeId, msg);
					if(null!=flay)
					{
						return ExcelUtile.RETURN;
					}
					return null;
				}
			});
			if (StringUtil.isNotBlank(msg[0])) {
				excelUtile.put("code", "1");
				excelUtile.put("count", 0);
				excelUtile.put("msg", msg[0]);
			}
			String code= (String) excelUtile.get("code");
			if ("0".equals(code)) {
				for (ResScore resScore : resScoreList) {
					save(resScore);
				}
			}
			return excelUtile;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}

	/**
	 * 公共科目成绩导入处理
	 * @param file
	 * @return
	 */
	@Override
	public ExcelUtile  importPublicScoreFromExcel(MultipartFile file) {
		InputStream is = null;
		final String[] msg = {""};
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			//map中的keys  个数与execl中表头字段数量一致
			final String[] keys = {
					"userName",
					"idNo",
					"orgName",
					"catSpeName",
					"speName",
					"sessionNumber",
					"lawScore",
					"medicineScore",
					"clinicalScore",
					"ckScore",
					"isPass",
			};
			return  ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {

				@Override
				public void operExcelData(ExcelUtile eu) {
					List<Map<String,Object>> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					for(int i=0;i<datas.size();i++)
					{
						Map<String, Object> data=datas.get(i);
						ResDoctor resDoctor = new ResDoctor();//学生
						ResScore resScore=new ResScore();//
						SysUser sysUser=new SysUser();
						Map<String,String> extScoreMap=new HashMap<String, String>();//各类成绩
						for (String key : data.keySet()) {
							String value=(String) data.get(key);
							if("idNo".equals(key))
							{
								sysUser.setIdNo(value);
							}else if("lawScore".equals(key))
							{
								extScoreMap.put("lawScore",value);
							}else if("medicineScore".equals(key)){

								extScoreMap.put("medicineScore",value);
							}else
							if("clinicalScore".equals(key)){
								extScoreMap.put("clinicalScore",value);
							}else
							if("ckScore".equals(key)){
								extScoreMap.put("ckScore",value);
							}else if("isPass".equals(key)){
								if("是".equals(value))
								{
									resScore.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.PASS)));
								}else if("否".equals(value))
								{
									resScore.setSkillScore(BigDecimal.valueOf(Double.valueOf(GlobalConstant.UNPASS)));
								}
							} else if ("userName".equals(key)) {
								sysUser.setUserName(value);
							} else if ("orgName".equals(key)) {
								sysUser.setOrgName(value);
							} else if ("catSpeName".equals(key)) {
								resDoctor.setTrainingTypeName(value);
							} else if ("speName".equals(key)) {
								resDoctor.setTrainingSpeName(value);
							} else if ("sessionNumber".equals(key)) {
								resDoctor.setSessionNumber(value);
							}
						}
						//根据学生身份证号
						//sysUser.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
						sysUser=userBiz.findByIdNo(sysUser.getIdNo());
						resDoctor.setDoctorFlow(sysUser.getUserFlow());
						resScore.setDoctorFlow(resDoctor.getDoctorFlow());//医师流水号
						resScore.setScoreTypeId(ResScoreTypeEnum.PublicScore.getId());
						resScore.setScoreTypeName(ResScoreTypeEnum.PublicScore.getName());
						resScore.setPaperFlow(GlobalConstant.FLAG_Y);
						ResDoctorRecruit recruit=doctorRecruitBiz.getNewRecruit(sysUser.getUserFlow());
						if(recruit!=null)
							resScore.setRecruitFlow(recruit.getRecruitFlow());
						//处理各个站的成绩
						String extScore= null;
						try {
							extScore = convertMapToXml(extScoreMap,resScore);
						} catch (Exception e) {
							e.printStackTrace();
						}
						resScore.setExtScore(extScore);
						count+=savePublic(resScore);
					}
					eu.put("code",code);
					eu.put("count",count);
				}

				@Override
				public String checkExcelData(HashMap data, ExcelUtile eu) {
					String sheetName=(String)eu.get("SheetName");
					if(sheetName==null||!"PublicScore".equals(sheetName))
					{
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "请使用系统提供的公共科目成绩模板！！");
						return ExcelUtile.RETURN;
					}
					SysUser sysUser=new SysUser();
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					String isPass="";
					String userName="";
					String orgName="";
					String catSpeName="";
					String speName="";
					String sessionNumber="";
					for (Object key1 : data.keySet()) {
						String key=(String) key1;
						String value=(String) data.get(key);
						if("idNo".equals(key))
						{
							if(StringUtil.isBlank(value)) {
								eu.put("count", 0);
								eu.put("code", "1");
								eu.put("msg", "导入文件第" + (rowNum + 1) + "行身份证号为空，请确认后提交！！");
								return ExcelUtile.RETURN;
							}
							sysUser.setIdNo(value);
						}else
						if("lawScore".equals(key))
						{
							if(!StringUtil.isBlank(value)) {
								String flay=checkScore(value,"卫生法律和法规",rowNum,eu);
								if(null!=flay)
								{
									return ExcelUtile.RETURN;
								}
							}
						}else if("medicineScore".equals(key)){
							if(!StringUtil.isBlank(value)) {
								String flay=checkScore(value,"循证医学",rowNum,eu);
								if(null!=flay)
								{
									return ExcelUtile.RETURN;
								}
							}
						}else
						if("clinicalScore".equals(key)){
							if(!StringUtil.isBlank(value)) {
								String flay=checkScore(value,"临床思维与人际沟通",rowNum,eu);
								if(null!=flay)
								{
									return ExcelUtile.RETURN;
								}
							}
						}else
						if("ckScore".equals(key)){
							if(!StringUtil.isBlank(value)) {
								String flay=checkScore(value,"重点传染病防治知识",rowNum,eu);
								if(null!=flay)
								{
									return ExcelUtile.RETURN;
								}
							}
						}else
						if("isPass".equals(key)){
							isPass=value;

						} else if ("userName".equals(key)) {
							userName=value;
						} else if ("orgName".equals(key)) {
							orgName=value;
						} else if ("catSpeName".equals(key)) {
							catSpeName=value;
						} else if ("speName".equals(key)) {
							speName=value;
						} else if ("sessionNumber".equals(key)) {
							sessionNumber=value;
						}
					}
					if (StringUtil.isBlank(isPass)) {
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行是否合格信息为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					} else if (!"是".equals(isPass) && !"否".equals(isPass)) {
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行是否合格信息只能填‘是’或‘否’，请确认后提交！！");
						return ExcelUtile.RETURN;
					}else if(StringUtil.isBlank(userName)){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行姓名信息为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}else if(StringUtil.isBlank(orgName)){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行培训基地信息为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}else if(StringUtil.isBlank(catSpeName)){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行培训类别信息为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}else if(StringUtil.isBlank(speName)){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行培训专业信息为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}else if(StringUtil.isBlank(sessionNumber)){
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "导入文件第" + (rowNum + 1) + "行年级信息为空，请确认后提交！！");
						return ExcelUtile.RETURN;
					}

					//校验学生是否存在
					String flay=checkUser(sysUser,rowNum,eu, sessionNumber, "","", msg);
					if(null!=flay)
					{
						return ExcelUtile.RETURN;
					}
					return null;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		return null;
	}

	@Override
	public int batchApplyAudit(String[] datas,String roleFlag,String applyType,String reason,String doctorStatusId) {
		int sucon=0;
		for(String doctorFlow:datas)
		{
			ResDoctor resDoctor=findByFlow(doctorFlow);
			if(null==resDoctor)
			{
				continue;
			}
			//培训基地审核
			if(roleFlag.equals(GlobalConstant.USER_LIST_LOCAL)) {
				//上报
				if (StringUtil.isNotBlank(applyType) && applyType.equals(CertificateStatusEnum.ManagerPassed.getId())) {
					resDoctor.setGraduationStatusId(CertificateStatusEnum.ManagerPassed.getId());
					resDoctor.setGraduationStatusName(CertificateStatusEnum.ManagerPassed.getName());
					resDoctor.setDisagreeReason("");
				} else {
					//退回
					resDoctor.setGraduationStatusId("");
					resDoctor.setGraduationStatusName("");
					try {
						reason = java.net.URLDecoder.decode(reason, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						reason="";
					}
					resDoctor.setDisagreeReason(reason);
				}
			}else  //省厅审核
				if(roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL)) {
					//同意发证
					if (StringUtil.isNotBlank(applyType) && applyType.equals(CertificateStatusEnum.GrantCertf.getId())) {
						resDoctor.setGraduationStatusId(CertificateStatusEnum.GrantCertf.getId());
						resDoctor.setGraduationStatusName(CertificateStatusEnum.GrantCertf.getName());
						resDoctor.setDisagreeReason("同意发放合格证书");
						if(!StringUtil.isNotBlank(resDoctor.getCompleteNo())) {
							//年份代码（4位）+省（自治区、直辖市）代码（2位）+专业代码（4位）+培训基地代码（3位）+该培训基地该年度结业人员顺序号（3位）
							String certificateNo = getCertificateNo(resDoctor);
							resDoctor.setCompleteNo(certificateNo);
						}
						//状态改为结业
						resDoctor.setDoctorStatusId("21");
						resDoctor.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById("21"));
					} else {
						//暂不发证
						resDoctor.setGraduationStatusId(CertificateStatusEnum.UnGrantCertf.getId());
						resDoctor.setGraduationStatusName(CertificateStatusEnum.UnGrantCertf.getName());
						try {
							doctorStatusId = java.net.URLDecoder.decode(doctorStatusId, "UTF-8");
							doctorStatusId = doctorStatusId+java.net.URLDecoder.decode(reason, "UTF-8");
						} catch (UnsupportedEncodingException e) {
							doctorStatusId="暂不发证";
						}

						resDoctor.setDisagreeReason(doctorStatusId);
						//状态改为已考核待结业
						resDoctor.setDoctorStatusId("22");
						resDoctor.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById("22"));
					}
					//获取最新培训记录
					if(StringUtil.isNotBlank(doctorFlow)){
						ResDoctorRecruit docRecruit =  new ResDoctorRecruit();
						docRecruit.setDoctorFlow(doctorFlow);
						docRecruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
						List<ResDoctorRecruit> docRecruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(docRecruit, "CREATE_TIME DESC");
						if(docRecruitList != null && !docRecruitList.isEmpty()) {
							docRecruit = docRecruitList.get(0);
							//DoctorRecruit回写
							ResDoctorRecruitWithBLOBs docRecruitWithBLOBs = new ResDoctorRecruitWithBLOBs();
							docRecruitWithBLOBs.setRecruitFlow(docRecruit.getRecruitFlow());
							//状态改为结业
							docRecruitWithBLOBs.setDoctorStatusId(resDoctor.getDoctorStatusId());
							docRecruitWithBLOBs.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById(resDoctor.getDoctorStatusId()));
							jsResDoctorRecruitBiz.saveDoctorRecruit(docRecruitWithBLOBs);
						}
					}
				}
			int result = editDoctor(resDoctor);
			if(GlobalConstant.ZERO_LINE==result){
				sucon++;
			}
		}
		return  sucon;
	}

	@Override
	public String getCertificateNo(ResDoctor resDoctor){
		//
		String completeNo = "";
		//所有助理全科人员都只生成助理全科证书
		if(resDoctor.getTrainingTypeId().equals(TrainCategoryEnum.AssiGeneral.getId()))
		{
//			编码规则：举例：15 02 04 0007（15年毕业，全科中医，常州市，流水号0004）
//			共10位，第1、2位为年份，比如2016年为16；第3、4位为科目代码，01为全科西医，02为全科中医，
// 			03为助理全科西医，04为助理全科中医；第5、6位为地级市地区代码。7-10位为该市的流水号
//			地区代码：01南京，02无锡，03徐州，04常州，05苏州，06南通，07连云港，08淮安，09盐城，10扬州，11镇江，12泰州，13宿迁
			Map<String,String> city=new HashMap<String,String>();
			city.put("320100","01");
			city.put("320200","02");
			city.put("320300","03");
			city.put("320400","04");
			city.put("320500","05");
			city.put("320600","06");
			city.put("320700","07");
			city.put("320800","08");
			city.put("320900","09");
			city.put("321000","10");
			city.put("321100","11");
			city.put("321200","12");
			city.put("321300","13");
			String year=DateUtil.getYear();
			SysOrg org = orgBiz.readSysOrg(resDoctor.getOrgFlow());
			String dishiCode= (String) city.get(org.getOrgCityId());
			String kumu="";
			//18 ==04  50 ===03
			if(resDoctor.getTrainingSpeId().equals("18"))
			{
				kumu="04";
			}else if(resDoctor.getTrainingSpeId().equals("50"))
			{
				kumu="03";
			}
			String yearbefore=year.substring(0,2);
			//查询当前年份下，当前地市已经结业的流水号
			Map map=jsResDoctorExtMapper.getOrgNumByCityId(org.getOrgFlow(),org.getOrgCityId(),year,yearbefore);
			String num= (String) map.get("NUM");
			completeNo=year.substring(2)+kumu+dishiCode+num;
		}else {
			int sessionYear = Integer.valueOf(resDoctor.getSessionNumber());
			//2013年以前全部用江苏省证书
			if (sessionYear <= 2013) {
				//江苏省生成规则待定
				completeNo = getProvinceOrgNo(resDoctor);
			} else if (sessionYear == 2014) {
				SysOrg org = orgBiz.readSysOrg(resDoctor.getOrgFlow());
				//只有国家基地使用国家证书
				if (org.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
					completeNo = getCountryOrgNo(resDoctor);
				} else {
					//江苏省生成规则待定
					completeNo = getProvinceOrgNo(resDoctor);
				}
			} else {
				SysOrg org = orgBiz.readSysOrg(resDoctor.getOrgFlow());
				//国家基地使用国家证书
				if (org.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
					completeNo = getCountryOrgNo(resDoctor);
				} else {
					List<ResJointOrg> jointOrgList = resJointBiz.searchResJointByJointOrgFlow(org.getOrgFlow());
					if (jointOrgList != null && jointOrgList.size() > 0) {
						ResJointOrg resJointOrg = jointOrgList.get(0);
						SysOrg org2 = orgBiz.readSysOrg(resJointOrg.getOrgFlow());
						//国家基地的协同基地也使用国家证书
						if (org2.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
							completeNo = getCountryOrgNo(resDoctor);
						} else {
							//江苏省生成规则待定
							completeNo = getProvinceOrgNo(resDoctor);
						}
					} else {
						//江苏省生成规则待定
						completeNo = getProvinceOrgNo(resDoctor);
					}
				}
			}
		}
		return  completeNo;

	}
	public String getProvinceOrgNo(ResDoctor resDoctor)
	{
		String no="";
		//全科  一阶段全科专业代码为51为中医  52为西医 住院医师全科专业代码为0700
		if(resDoctor.getTrainingSpeId().equals("51")||resDoctor.getTrainingSpeId().equals("52")||resDoctor.getTrainingSpeId().equals("0700"))
		{
//			编码规则：举例：1502040007（15年毕业，全科中医，常州市，流水号0004）
//			共10位，第1、2位为年份，比如2016年为16；第3、4位为科目代码，01为全科西医，02为全科中医，03为助理全科西医，
// 			04为助理全科中医；第5、6位为地级市地区代码。7-10位为该市的流水号
//
//			地区代码：01南京，02无锡，03徐州，04常州，05苏州，06南通，07连云港，08淮安，09盐城，10扬州，11镇江，12泰州，13宿迁
			Map<String,String> city=new HashMap<String,String>();
			city.put("320100","01");
			city.put("320200","02");
			city.put("320300","03");
			city.put("320400","04");
			city.put("320500","05");
			city.put("320600","06");
			city.put("320700","07");
			city.put("320800","08");
			city.put("320900","09");
			city.put("321000","10");
			city.put("321100","11");
			city.put("321200","12");
			city.put("321300","13");
			String year=DateUtil.getYear();
			SysOrg org = orgBiz.readSysOrg(resDoctor.getOrgFlow());
			String dishiCode= (String) city.get(org.getOrgCityId());
			String kumu="";
			if(resDoctor.getTrainingSpeId().equals("52")||resDoctor.getTrainingSpeId().equals("0700"))
			{
				kumu="01";
			}else if(resDoctor.getTrainingSpeId().equals("51"))
			{
				kumu="02";
			}
			//查询当前年份下，当前地市已经结业的流水号
			String yearbefore=year.substring(0,2);
			Map map=jsResDoctorExtMapper.getQuanKe(org.getOrgCityId(),year,yearbefore);
			String num= (String) map.get("NUM");
			no=year.substring(2)+kumu+dishiCode+num;
		}
		else{
			//非全科 默认为1
//			编码规则：
//			（苏卫2015）住院医培字第A1-001号
//			1、2015为结业年份
//			2、A南京B无锡C徐州D常州E苏州F南通G连云港H淮安J盐城K扬州L镇江M泰州N宿迁
//			3、1为除发国家证书人员、助理全科、二阶段剩下的学员，2为二阶段
//			4、001为该市的顺序码，会存在4位数情况，001~999使用三位，1000~9999使用四位
			Map<String,String> city=new HashMap<String,String>();
			city.put("320100","A");
			city.put("320200","B");
			city.put("320300","C");
			city.put("320400","D");
			city.put("320500","E");
			city.put("320600","F");
			city.put("320700","G");
			city.put("320800","H");
			city.put("320900","J");
			city.put("321000","K");
			city.put("321100","L");
			city.put("321200","M");
			city.put("321300","N");
			String year=DateUtil.getYear();
			SysOrg org = orgBiz.readSysOrg(resDoctor.getOrgFlow());
			String dishiCode= (String) city.get(org.getOrgCityId());
			String p="1";//需求说写死
			//查询当前年份下，当前地市已经结业的流水号
			String yearbefore=year.substring(0,2);
			Map map=jsResDoctorExtMapper.getFeiQuanKe(org.getOrgCityId(),year,yearbefore);
			String num= (String) map.get("NUM");
			if(!StringUtil.isBlank(num))
			{
				int n=Integer.valueOf(num);
				if(n<=999)
				{
					num=num.substring(1);
				}
			}
			no="苏卫"+year+"-"+dishiCode+p+"-"+num;
		}
		return no;
	}
	public String getCountryOrgNo(ResDoctor resDoctor)
	{
		//年份代码（4位）+省（自治区、直辖市）代码（2位）+专业代码（4位）+培训基地代码（3位）+该培训基地该年度结业人员顺序号（3位）
		String year=DateUtil.getYear();
		String proCode="32";
		String speId=resDoctor.getTrainingSpeId();
		Map<String ,Object> map=getOrgCodeAndNum(resDoctor.getOrgFlow(),year);
		String orgCode=(String)map.get("orgCode");
		String num=(String)map.get("num");
		return  year+proCode+speId+orgCode+num;
	}
	public  Map<String,Object> getOrgCodeAndNum(String orgFlow,String year)
	{
		System.err.println(orgFlow);
		List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByJointOrgFlow(orgFlow);
		//是协同基地 计算所属国家基地及其协同基地已发证的人数
		if(!jointOrgs.isEmpty()&&jointOrgs.size()>0){
			orgFlow=jointOrgs.get(0).getOrgFlow();
		}
		if(StringUtil.isBlank(orgFlow)){
			throw new RuntimeException("生成证书编号失败，orgFlow 为空！");
		}
		return  jsResDoctorExtMapper.getOrgCodeAndNum(orgFlow,year);
	}
	@Override
	public List<ResDoctor> searchResDoctorByMap(Map<String, Object> map)
	{

		return  jsResDoctorExtMapper.searchResDoctorByMap(map);
	}

	@Override
	public List<ResDoctor> searchResDoctorByAssessment(Map<String, Object> map) {
		return doctorExtMapper.searchResDoctorByAssessment(map);
	}

	@Override
	public List<ResDoctor> searchResDoctorByMapForUni(Map<String, Object> map)
	{

		return  jsResDoctorExtMapper.searchResDoctorByMapForUni(map);
	}

	@Override
	public Map<String, String> getPowerMap(String doctorFlow) {
		return jsResDoctorExtMapper.getPowerMap(doctorFlow);
	}

	@Override
	public Map<String, Object> getDocProcessArea(String userFlow) {
		return jsResDoctorExtMapper.getDocProcessArea(userFlow);
	}

	@Override
	public boolean getCkkPower(String doctorFlow) {
		if(StringUtil.isNotBlank(doctorFlow)){
			boolean f=false;
			//学员相应的出科考核权限
			//			Map<String, String> powerMap = getPowerMap(doctorFlow);
//			ResDoctor doctor=readResDoctor(doctorFlow);
//			if (powerMap != null) {
//				if ("Graduate".equals(doctor.getDoctorTypeId()))//在校专硕
//				{
//					//派送学校过程开通及派送学校出科考开通
//					if (StringUtil.isNotBlank(doctor.getWorkOrgId())) {
//						String isSendFlag = powerMap.get("STU_SEND_SCHOOL_GC");
//						String SendCkk = powerMap.get("STU_SEND_SCHOOL_CKK");
//						if (!GlobalConstant.FLAG_N.equals(isSendFlag) && GlobalConstant.FLAG_Y.equals(SendCkk)) {
//							f = true;
//						}
//					}
//					//培训基地过程开通，培训基地在校专硕过程开通，培训基地专硕出科考开通
//					if (!f) {
//						String isJDGC = powerMap.get("STU_JD_GC");
//						String isJDZSGC = powerMap.get("STU_JDZS_GC");
//						String isJDZSCKK = powerMap.get("STU_JDZS_CKK");
//						if (GlobalConstant.FLAG_Y.equals(isJDGC) && GlobalConstant.FLAG_Y.equals(isJDZSGC) && GlobalConstant.FLAG_Y.equals(isJDZSCKK)) {
//							f = true;
//						}
//					}
//				} else {
//					//非在校专硕 需要基地开通过程并且出科考也开通
//					String stuJdGc = powerMap.get("STU_JD_GC");
//					String stuJdCkk = powerMap.get("STU_JD_CKK");
//					if (GlobalConstant.FLAG_Y.equals(stuJdGc) && GlobalConstant.FLAG_Y.equals(stuJdCkk)) {
//						f = true;
//					}
//				}
//			}
			String ckk = "";
			String cfgCode = "jsres_doctor_exam_" + doctorFlow;
			JsresPowerCfg cfg = jsResPowerCfgBiz.read(cfgCode);
			if(cfg != null){
				ckk = cfg.getCfgValue();
			}else {
				ckk = GlobalConstant.FLAG_N;
			}
			if(StringUtil.isNotBlank(ckk) && "Y".equals(ckk)) {
				f = true;
			}
			return f;
		}
		return false;
	}


	@Override
	public void exportForDetail(List<ResDoctorExt> doctorList, HttpServletResponse response) throws DocumentException, IOException {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

		//列宽自适应
		HSSFRow rowDep = sheet.createRow(0);//第一行
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));//合并单元格
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(stylevwc);
		cellOne.setCellValue("培训基地");
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 21));//合并单元格
		HSSFCell cell = rowDep.createCell(1);
		cell.setCellStyle(stylevwc);
		cell.setCellValue("学生基本信息");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 22, 39));
		HSSFCell cellTwo = rowDep.createCell(22);
		cellTwo.setCellStyle(styleCenter);
		cellTwo.setCellValue("学历信息");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 40, 48));
		HSSFCell cellThree = rowDep.createCell(38);
		cellThree.setCellStyle(styleCenter);
		cellThree.setCellValue("单位信息");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 22, 27));//合并单元格
		HSSFCell cellFour = rowTwo.createCell(22);
		cellFour.setCellStyle(styleCenter);
		cellFour.setCellValue("本科阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 28, 33));//合并单元格
		HSSFCell cellFive = rowTwo.createCell(28);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("硕士研究生阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 34, 39));//合并单元格
		HSSFCell cellSix = rowTwo.createCell(34);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("博士研究生阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 40, 42));//合并单元格
		HSSFCell cellEight = rowTwo.createCell(40);
		cellEight.setCellStyle(styleCenter);
		cellEight.setCellValue("在“医疗卫生机构”选择“医院”则填");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 43, 45));//合并单元格
		HSSFCell cellNine = rowTwo.createCell(43);
		cellNine.setCellStyle(styleCenter);
		cellNine.setCellValue("在“医疗卫生机构”选择“基层医疗卫生机构”则填");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 46, 48));//合并单元格
		HSSFCell cellTen = rowTwo.createCell(46);
		cellTen.setCellStyle(styleCenter);

		HSSFRow rowThree = sheet.createRow(2);//第三行
		String[] titles = new String[]{
				"基地名称",
				"姓名",
				"用户名",
				"性别",
				"年龄",
				"证件类型",
				"证件号",
				"民族",
				"手机号",
				"邮箱",
				"QQ（非必填）",
				"往届/应届",
				"培训专业",
				"是否执业医师",
				"如是执业医师，请填写执业医师资格证号",
				"是否取得执业医师证书",
				"执业医师证书编号",
				"参培年份",
				"规培年限",
				"实际培训开始时间（****年**月）",
				"培训状态",
				"退培/延期原因",
				"学员类型",
				"毕业学校",
				"毕业年份",
				"毕业专业",
				"学历",
				"是否全科定向生",
				"学位",
				"是否硕士研究生",
				"毕业学校",
				"毕业年份",
				"毕业专业",
				"学位",
				"学位类型",
				"是否博士研究生",
				"毕业学校",
				"毕业年份",
				"毕业专业",
				"学位",
				"学位类型",
				"工作单位（与单位公章对应的官方全称）",
				"医疗卫生机构",
				"医院属性",
				"医院类别",
				"单位性质",
				"基层医疗卫生机构",
				"是否在协同单位培训",
				"协同单位（与单位公章对应的官方全称）",
				"协同单位性质",
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 3;
		String[] dataList = null;
		if (doctorList != null && !doctorList.isEmpty()) {
			for (int i = 0; i < doctorList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				ResDoctorExt resDoctorExt = doctorList.get(i);
				//大字段信息
				PubUserResume pubUserResume = resDoctorExt.getPubUserResume();//pubUserResumeBiz.readPubUserResume(resDoctorExt.getDoctorFlow());
				BaseUserResumeExtInfoForm extInfo=new BaseUserResumeExtInfoForm();
				if(pubUserResume != null){
					String xmlContent =  pubUserResume.getUserResume();
					if(StringUtil.isNotBlank(xmlContent)){
						//xml转换成JavaBean
						extInfo=pubUserResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class,null);
					}
				}
				//培训基地
				String orgName = resDoctorExt.getOrgName();
				String joinName = "";
				String jointFlag = "否";
				String age ="";
				if(resDoctorExt.getSysUser()!=null&&StringUtil.isNotBlank(resDoctorExt.getSysUser().getUserBirthday())) {
					try {
						age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(resDoctorExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
					}catch (Exception e)
					{
						age="";
					}
				}
				if(StringUtil.isBlank(age)){
					age=extInfo.getAge();
				}
				SysUser sysUser = resDoctorExt.getSysUser();
				ResDoctor doctor = readDoctor(sysUser.getUserFlow());
				String CretType = "";
				if (!CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "其他";
				} else {
					CretType = "身份证";
				}
				//民族
				String userNation = "";
				if(StringUtil.isNotBlank(sysUser.getNationId())) {
					userNation=UserNationEnum.getNameById(sysUser.getNationId());
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}
				//培训专业
				String trainingSpeName = resDoctorExt.getTrainingSpeName();
				//实际培训开始时间
				String recruitDate = doctor.getInHosDate();
				if(StringUtil.isNotBlank(recruitDate)){
					try{
						recruitDate = recruitDate.substring(0,4) + "年" + recruitDate.substring(5,7) + "月" + recruitDate.substring(8,10) +"日";
					}catch (Exception e)
					{
						recruitDate="";
					}
				}
				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(doctor.getDoctorLicenseNo())) {
					qualificationFlag = "是";
				} else {
					doctor.setDoctorLicenseNo("");
				}

				//参培年份
				String sessionNumber = resDoctorExt.getSessionNumber();

				//是否是执业医师和编号
				String practPhysicianFlag = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getPractPhysicianFlag())) {
					practPhysicianFlag = "是";
				}
				if (GlobalConstant.FLAG_N.equals(doctor.getPractPhysicianFlag())) {
					practPhysicianFlag="否";
					doctor.setPractPhysicianCertificateNo("");
				}

				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(extInfo.getIsMaster()) || StringUtil.isBlank(extInfo.getIsMaster())) {
					extInfo.setMasterDegreeName("");
					extInfo.setMasterDegreeTypeName("");
					extInfo.setMasterGraSchoolName("");
					extInfo.setMasterGraTime("");
					extInfo.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
					if(StringUtil.isNotBlank(extInfo.getMasterDegreeId())){
						List<SysDict> dicts = DictTypeEnum.UserDegree.getSysDictList();
						if(dicts != null && dicts.size() > 0){
							for(SysDict tempDict : dicts){
								if(extInfo.getMasterDegreeId().equals(tempDict.getDictId())){
									extInfo.setMasterDegreeName(tempDict.getDictName());
								}
							}
						}
					}
					if(StringUtil.isNotBlank(extInfo.getMasterDegreeTypeId())){
						if("1".equals(extInfo.getMasterDegreeTypeId())){
							extInfo.setMasterDegreeTypeName("专业型");
						}
						if("2".equals(extInfo.getMasterDegreeTypeId())){
							extInfo.setMasterDegreeTypeName("科学型");
						}
					}
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(extInfo.getIsDoctor()) || StringUtil.isBlank(extInfo.getIsDoctor())) {
					extInfo.setDoctorDegreeName("");
					extInfo.setDoctorDegreeTypeName("");
					extInfo.setDoctorGraSchoolName("");
					extInfo.setDoctorGraTime("");
					extInfo.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
					if(StringUtil.isNotBlank(extInfo.getDoctorDegreeId())){
						List<SysDict> dicts = DictTypeEnum.UserDegree.getSysDictList();
						if(dicts != null && dicts.size() > 0){
							for(SysDict tempDict : dicts){
								if(extInfo.getDoctorDegreeId().equals(tempDict.getDictId())){
									extInfo.setDoctorDegreeName(tempDict.getDictName());
								}
							}
						}
					}
					if (StringUtil.isNotBlank(extInfo.getDoctorDegreeTypeId())) {
						if("1".equals(extInfo.getDoctorDegreeTypeId())){
							extInfo.setDoctorDegreeTypeName("专业型");
						}
						if("2".equals(extInfo.getDoctorDegreeTypeId())){
							extInfo.setDoctorDegreeTypeName("科学型");
						}
					}
				}
				//工作单位
				String property = "";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(extInfo.getMedicalHeaithOrgId()) || !"1".equals(extInfo.getMedicalHeaithOrgId())) {
						extInfo.setHospitalAttrName("");
						extInfo.setHospitalCategoryName("");
						extInfo.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(extInfo.getMedicalHeaithOrgId()) || "3".equals(extInfo.getMedicalHeaithOrgId())) {
						extInfo.setBasicHealthOrgName("");
					}

				} else {
					doctor.setWorkOrgName("");
					extInfo.setMedicalHeaithOrgName("");
				}
				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(extInfo.getMasterGraTime())) {
					masterGraTime = extInfo.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(extInfo.getDoctorGraTime())) {
					doctorGraTime = extInfo.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(extInfo.getGraduationTime())) {
					graduationTime = extInfo.getGraduationTime().substring(0, 4);
				}
				String s="";
				if(StringUtil.isNotBlank(extInfo.getZbkbySpe()))
				{
					s=extInfo.getZbkbySpe();
				}else if(StringUtil.isNotBlank(extInfo.getSpecialized())){
					s=extInfo.getSpecialized();
				}
				//学历
				String educationName = "";
				educationName = sysUser.getEducationName();
				if(StringUtil.isBlank(sysUser.getEducationName())&&StringUtil.isNotBlank(sysUser.getEducationId())){
					List<SysDict> dicts = DictTypeEnum.UserEducation.getSysDictList();
					if(dicts != null && dicts.size() > 0){
						for(SysDict tempDict : dicts){
							if(sysUser.getEducationId().equals(tempDict.getDictId())){
								sysUser.setEducationName(tempDict.getDictName());
							}
						}
					}
					educationName = sysUser.getEducationName();
				}

				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(extInfo.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(extInfo.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				if(StringUtil.isNotBlank(extInfo.getDegreeId())){
					List<SysDict> dicts = DictTypeEnum.UserDegree.getSysDictList();
					if(dicts != null && dicts.size() > 0){
						for(SysDict tempDict : dicts){
							if(extInfo.getDegreeId().equals(tempDict.getDictId())){
								extInfo.setDegreeName(tempDict.getDictName());
							}
						}
					}
				}
				//规培年限
				String trainYear = "";
				trainYear = resDoctorExt.getTrainingYears();
				if(StringUtil.isNotBlank(trainYear)){
					switch (trainYear){
						case "1":{trainYear="一年";break;}
						case "2":{trainYear="两年";break;}
						case "3":{trainYear="三年";break;}
					}
				}
				//医师状态
				String doctorStatus = "";
				//退赔延期原因
				String backDelayInfo ="";
				if("Back".equals(doctorList.get(0).getIs5plus3())){
					doctorStatus="退培";
					List<ResDocotrDelayTeturn> resDocotrDelayTeturns = resDoctorDelayTeturnBiz.searchByDoctorFlow(doctor.getDoctorFlow());
					if(resDocotrDelayTeturns != null && resDocotrDelayTeturns.size()>0){
						for(ResDocotrDelayTeturn tempBack : resDocotrDelayTeturns){
							if(ResRecTypeEnum.ReturnTraining.getId().equals(tempBack.getTypeId())){
//								doctorStatus+="（"+tempBack.getAuditStatusName()+"）";
								String auditOpinion = tempBack.getAuditOpinion()==null?"省厅未添加意见":tempBack.getAuditOpinion();
								backDelayInfo = tempBack.getRemark();
								break;
							}
						}
					}
				}else {
					doctorStatus="在培";
					List<ResDocotrDelayTeturn> resDocotrDelayTeturns = resDoctorDelayTeturnBiz.searchByDoctorFlow(doctor.getDoctorFlow());
					if(resDocotrDelayTeturns != null && resDocotrDelayTeturns.size()>0){
						for(ResDocotrDelayTeturn tempDelay : resDocotrDelayTeturns){
							if(ResRecTypeEnum.Delay.getId().equals(tempDelay.getTypeId())){
								backDelayInfo = tempDelay.getDelayreason();
								break;
							}
						}
					}
				}

				dataList = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getUserCode(),
						sysUser.getSexName(),
						age,
						CretType,
						sysUser.getIdNo(),
						userNation,
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						trainingSpeName,
						qualificationFlag,
						doctor.getDoctorLicenseNo(),
						practPhysicianFlag,
						doctor.getPractPhysicianCertificateNo(),
						sessionNumber,
						trainYear,
						recruitDate,

						doctorStatus,
						backDelayInfo,

						doctor.getDoctorTypeName(),

						extInfo.getGraduatedName(),
						graduationTime,
						s,
						educationName,
						isGeneralOrderOrientationTrainee,
						extInfo.getDegreeName(),

						masterFlag,
						extInfo.getMasterGraSchoolName(),
						masterGraTime,
						extInfo.getMasterMajor(),
						extInfo.getMasterDegreeName(),
						extInfo.getMasterDegreeTypeName(),

						doctorFlag,
						extInfo.getDoctorGraSchoolName(),
						doctorGraTime,
						extInfo.getDoctorMajor(),
						extInfo.getDoctorDegreeName(),
						extInfo.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						DictTypeEnum.MedicalHeaithOrg.getDictNameById(extInfo.getHospitalAttrId()),
						DictTypeEnum.HospitalAttr.getDictNameById(extInfo.getHospitalAttrId()),
						DictTypeEnum.HospitalCategory.getDictNameById(extInfo.getHospitalCategoryId()),
						DictTypeEnum.BaseAttribute.getDictNameById(extInfo.getBaseAttributeId()),
						DictTypeEnum.BasicHealthOrg.getDictNameById(extInfo.getBasicHealthOrgId()),

						jointFlag,
						joinName,
						property
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@Override
	public void exportForDetail4HB(List<ResDoctorExt> doctorList, Map<String, Object> paramMap, HttpServletResponse response) throws DocumentException, IOException {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

		//列宽自适应
		HSSFRow rowDep = sheet.createRow(0);//第一行
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));//合并单元格
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(stylevwc);
		cellOne.setCellValue("培训基地");
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 26));//合并单元格
		HSSFCell cell = rowDep.createCell(1);
		cell.setCellStyle(stylevwc);
		cell.setCellValue("学生基本信息");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 27, 44));
		HSSFCell cellTwo = rowDep.createCell(27);
		cellTwo.setCellStyle(styleCenter);
		cellTwo.setCellValue("学历信息");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 45, 53));
		HSSFCell cellThree = rowDep.createCell(43);
		cellThree.setCellStyle(styleCenter);
		cellThree.setCellValue("单位信息");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 27, 32));//合并单元格
		HSSFCell cellFour = rowTwo.createCell(27);
		cellFour.setCellStyle(styleCenter);
		cellFour.setCellValue("本科阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 33, 37));//合并单元格
		HSSFCell cellFive = rowTwo.createCell(33);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("硕士研究生阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 38, 43));//合并单元格
		HSSFCell cellSix = rowTwo.createCell(38);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("博士研究生阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 44, 46));//合并单元格
		HSSFCell cellEight = rowTwo.createCell(44);
		cellEight.setCellStyle(styleCenter);
		cellEight.setCellValue("在“医疗卫生机构”选择“医院”则填");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 47, 49));//合并单元格
		HSSFCell cellNine = rowTwo.createCell(47);
		cellNine.setCellStyle(styleCenter);
		cellNine.setCellValue("在“医疗卫生机构”选择“基层医疗卫生机构”则填");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 50, 52));//合并单元格
		HSSFCell cellTen = rowTwo.createCell(50);
		cellTen.setCellStyle(styleCenter);

		HSSFRow rowThree = sheet.createRow(2);//第三行
		String[] titles = new String[]{
				"基地名称",
				"姓名",
				"用户名",
				"性别",
				"年龄",
				"证件类型",
				"证件号",
				"民族",
				"手机号",
				"邮箱",
				"人员属性",
				"QQ（非必填）",
				"往届/应届",
				"培训专业",
				"是否执业医师",
				"如是执业医师，请填写执业医师资格证号",
				"是否取得执业医师证书",
				"执业医师证书编号",
				"参培年份",
				"规培年限",
				"是否为援疆援藏住院医师",
				"援疆援藏住院医师送出省份",
				"援疆援藏住院医师送出单位统一社会信用代码/组织机构代码",
				"实际培训开始时间（****年**月）",
				"培训状态",
				"退培/延期原因",
				"学员类型",
				"毕业学校",
				"毕业年份",
				"毕业专业",
				"学历",
				"是否全科定向生",
				"学位",
				"是否硕士研究生",
				"毕业学校",
				"毕业年份",
				"毕业专业",
				"学位",
				"学位类型",
				"是否博士研究生",
				"毕业学校",
				"毕业年份",
				"毕业专业",
				"学位",
				"学位类型",
				"工作单位（与单位公章对应的官方全称）",
				"医疗卫生机构",
				"医院属性",
				"医院类别",
				"单位性质",
				"基层医疗卫生机构",
				"是否在协同单位培训",
				"协同单位（与单位公章对应的官方全称）",
				"协同单位性质",
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 3;
		String[] dataList = null;
		if (doctorList != null && !doctorList.isEmpty()) {
			for (int i = 0; i < doctorList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				ResDoctorExt resDoctorExt = doctorList.get(i);

				SysUser sysUser = resDoctorExt.getSysUser();
				paramMap.put("userFlow",sysUser.getUserFlow());
				//大字段信息
				PubUserResume pubUserResume = archiveBiz.readUserResumeArchive(paramMap);
				BaseUserResumeExtInfoForm extInfo=new BaseUserResumeExtInfoForm();
				if(pubUserResume != null){
					String xmlContent =  pubUserResume.getUserResume();
					if(StringUtil.isNotBlank(xmlContent)){
						//xml转换成JavaBean
						extInfo=pubUserResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
					}
				}
				//培训基地
				String orgName = resDoctorExt.getOrgName();
				String joinName = "";
				String jointFlag = "否";
				String age ="";
				if(resDoctorExt.getSysUser()!=null&&StringUtil.isNotBlank(resDoctorExt.getSysUser().getUserBirthday())) {
					try {
						age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(resDoctorExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
					}catch (Exception e)
					{
						age="";
					}
				}
				if(StringUtil.isBlank(age)){
					age=extInfo.getAge();
				}
				ResDoctor doctor = archiveBiz.readDoctorArchive(paramMap);
				String CretType = "";
				if (!CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "其他";
				} else {
					CretType = "身份证";
				}
				//民族
				String userNation = "";
				if(StringUtil.isNotBlank(sysUser.getNationId())) {
					userNation=UserNationEnum.getNameById(sysUser.getNationId());
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}
				//实际培训开始时间
				String recruitDate = doctor.getInHosDate();
				if(StringUtil.isNotBlank(recruitDate)){
					try{
						recruitDate = recruitDate.substring(0,4) + "年" + recruitDate.substring(5,7) + "月" + recruitDate.substring(8,10) +"日";
					}catch (Exception e)
					{
						recruitDate="";
					}
				}
				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(doctor.getDoctorLicenseNo())) {
					qualificationFlag = "是";
				} else {
					doctor.setDoctorLicenseNo("");
				}

				//是否是执业医师和编号
				String practPhysicianFlag = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getPractPhysicianFlag())) {
					practPhysicianFlag = "是";
				}
				if (GlobalConstant.FLAG_N.equals(doctor.getPractPhysicianFlag())) {
					practPhysicianFlag="否";
					doctor.setPractPhysicianCertificateNo("");
				}

				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(extInfo.getIsMaster()) || StringUtil.isBlank(extInfo.getIsMaster())) {
					extInfo.setMasterDegreeName("");
					extInfo.setMasterDegreeTypeName("");
					extInfo.setMasterGraSchoolName("");
					extInfo.setMasterGraTime("");
					extInfo.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
					if(StringUtil.isNotBlank(extInfo.getMasterDegreeId())){
						List<SysDict> dicts = DictTypeEnum.UserDegree.getSysDictList();
						if(dicts != null && dicts.size() > 0){
							for(SysDict tempDict : dicts){
								if(extInfo.getMasterDegreeId().equals(tempDict.getDictId())){
									extInfo.setMasterDegreeName(tempDict.getDictName());
								}
							}
						}
					}
					if(StringUtil.isNotBlank(extInfo.getMasterDegreeTypeId())){
						if("1".equals(extInfo.getMasterDegreeTypeId())){
							extInfo.setMasterDegreeTypeName("专业型");
						}
						if("2".equals(extInfo.getMasterDegreeTypeId())){
							extInfo.setMasterDegreeTypeName("科学型");
						}
					}
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(extInfo.getIsDoctor()) || StringUtil.isBlank(extInfo.getIsDoctor())) {
					extInfo.setDoctorDegreeName("");
					extInfo.setDoctorDegreeTypeName("");
					extInfo.setDoctorGraSchoolName("");
					extInfo.setDoctorGraTime("");
					extInfo.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
					if(StringUtil.isNotBlank(extInfo.getDoctorDegreeId())){
						List<SysDict> dicts = DictTypeEnum.UserDegree.getSysDictList();
						if(dicts != null && dicts.size() > 0){
							for(SysDict tempDict : dicts){
								if(extInfo.getDoctorDegreeId().equals(tempDict.getDictId())){
									extInfo.setDoctorDegreeName(tempDict.getDictName());
								}
							}
						}
					}
					if (StringUtil.isNotBlank(extInfo.getDoctorDegreeTypeId())) {
						if("1".equals(extInfo.getDoctorDegreeTypeId())){
							extInfo.setDoctorDegreeTypeName("专业型");
						}
						if("2".equals(extInfo.getDoctorDegreeTypeId())){
							extInfo.setDoctorDegreeTypeName("科学型");
						}
					}
				}
				//工作单位
				String property = "";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(extInfo.getMedicalHeaithOrgId()) || !"1".equals(extInfo.getMedicalHeaithOrgId())) {
						extInfo.setHospitalAttrName("");
						extInfo.setHospitalCategoryName("");
						extInfo.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(extInfo.getMedicalHeaithOrgId()) || "3".equals(extInfo.getMedicalHeaithOrgId())) {
						extInfo.setBasicHealthOrgName("");
					}

				} else {
					doctor.setWorkOrgName("");
					extInfo.setMedicalHeaithOrgName("");
				}
				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(extInfo.getMasterGraTime())) {
					masterGraTime = extInfo.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(extInfo.getDoctorGraTime())) {
					doctorGraTime = extInfo.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(extInfo.getGraduationTime())) {
					graduationTime = extInfo.getGraduationTime().substring(0, 4);
				}
				String s="";
				if(StringUtil.isNotBlank(extInfo.getZbkbySpe()))
				{
					s=extInfo.getZbkbySpe();
				}else if(StringUtil.isNotBlank(extInfo.getSpecialized())){
					s=extInfo.getSpecialized();
				}
				//学历
				String educationName = "";
				educationName = sysUser.getEducationName();
				if(StringUtil.isBlank(sysUser.getEducationName())&&StringUtil.isNotBlank(sysUser.getEducationId())){
					List<SysDict> dicts = DictTypeEnum.UserEducation.getSysDictList();
					if(dicts != null && dicts.size() > 0){
						for(SysDict tempDict : dicts){
							if(sysUser.getEducationId().equals(tempDict.getDictId())){
								sysUser.setEducationName(tempDict.getDictName());
							}
						}
					}
					educationName = sysUser.getEducationName();
				}

				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(extInfo.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(extInfo.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				if(StringUtil.isNotBlank(extInfo.getDegreeId())){
					List<SysDict> dicts = DictTypeEnum.UserDegree.getSysDictList();
					if(dicts != null && dicts.size() > 0){
						for(SysDict tempDict : dicts){
							if(extInfo.getDegreeId().equals(tempDict.getDictId())){
								extInfo.setDegreeName(tempDict.getDictName());
							}
						}
					}
				}
				//规培年限
				String trainYear = "";
				trainYear = doctor.getTrainingYears();
				if(StringUtil.isNotBlank(trainYear)){
					switch (trainYear){
						case "1":{trainYear="一年";break;}
						case "2":{trainYear="两年";break;}
						case "3":{trainYear="三年";break;}
					}
				}
				//医师状态
				String doctorStatus = "";
				//退赔延期原因
				String backDelayInfo ="";
				if("Back".equals(doctorList.get(0).getIs5plus3())){
					doctorStatus="退培";
					List<ResDocotrDelayTeturn> resDocotrDelayTeturns = resDoctorDelayTeturnBiz.searchByDoctorFlow(doctor.getDoctorFlow());
					if(resDocotrDelayTeturns != null && resDocotrDelayTeturns.size()>0){
						for(ResDocotrDelayTeturn tempBack : resDocotrDelayTeturns){
							if(ResRecTypeEnum.ReturnTraining.getId().equals(tempBack.getTypeId())){
								doctorStatus+="（"+tempBack.getAuditStatusName()+"）";
								String auditOpinion = tempBack.getAuditOpinion()==null?"省厅未添加意见":tempBack.getAuditOpinion();
								backDelayInfo = tempBack.getRemark()+"（省厅审核意见："+auditOpinion+"）";
								break;
							}
						}
					}
				}else {
					doctorStatus="在培";
					List<ResDocotrDelayTeturn> resDocotrDelayTeturns = resDoctorDelayTeturnBiz.searchByDoctorFlow(doctor.getDoctorFlow());
					if(resDocotrDelayTeturns != null && resDocotrDelayTeturns.size()>0){
						for(ResDocotrDelayTeturn tempDelay : resDocotrDelayTeturns){
							if(ResRecTypeEnum.Delay.getId().equals(tempDelay.getTypeId())){
								backDelayInfo = tempDelay.getDelayreason();
								break;
							}
						}
					}
				}

				dataList = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getUserCode(),
						sysUser.getSexName(),
						age,
						CretType,
						sysUser.getIdNo(),
						userNation,
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						extInfo.getPersonnelAttributeName(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						doctor.getDoctorLicenseNo(),
						practPhysicianFlag,
						doctor.getPractPhysicianCertificateNo(),
						doctor.getSessionNumber(),
						trainYear,
						StringUtil.isNotBlank(extInfo.getIsAssistance())?(("Y").equals(extInfo.getIsAssistance())?"是":"否"):"",
						extInfo.getAssistanceProvince(),
						extInfo.getAssistanceCode(),
						recruitDate,

						doctorStatus,
						backDelayInfo,

						doctor.getDoctorTypeName(),

						extInfo.getGraduatedName(),
						graduationTime,
						s,
						educationName,
						isGeneralOrderOrientationTrainee,
						extInfo.getDegreeName(),

						masterFlag,
						extInfo.getMasterGraSchoolName(),
						masterGraTime,
						extInfo.getMasterMajor(),
						extInfo.getMasterDegreeName(),
						extInfo.getMasterDegreeTypeName(),

						doctorFlag,
						extInfo.getDoctorGraSchoolName(),
						doctorGraTime,
						extInfo.getDoctorMajor(),
						extInfo.getDoctorDegreeName(),
						extInfo.getDoctorDegreeTypeName(),

						doctor.getWorkOrgName(),
						DictTypeEnum.MedicalHeaithOrg.getDictNameById(extInfo.getHospitalAttrId()),
						DictTypeEnum.HospitalAttr.getDictNameById(extInfo.getHospitalAttrId()),
						DictTypeEnum.HospitalCategory.getDictNameById(extInfo.getHospitalCategoryId()),
						DictTypeEnum.BaseAttribute.getDictNameById(extInfo.getBaseAttributeId()),
						DictTypeEnum.BasicHealthOrg.getDictNameById(extInfo.getBasicHealthOrgId()),

						jointFlag,
						joinName,
						property
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
	@Override
	public void exportForDetail4HB2017(List<ResDoctorExt> doctorList, HttpServletResponse response) throws DocumentException, IOException {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

		//列宽自适应
		HSSFRow rowDep = sheet.createRow(0);//第一行
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));//合并单元格
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(stylevwc);
		cellOne.setCellValue("培训基地");
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 24));//合并单元格
		HSSFCell cell = rowDep.createCell(1);
		cell.setCellStyle(stylevwc);
		cell.setCellValue("基本信息");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 25, 133));
		HSSFCell cellTwo = rowDep.createCell(25);
		cellTwo.setCellStyle(styleCenter);
		cellTwo.setCellValue("教育经历");
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 134, 151));
		HSSFCell cellThree = rowDep.createCell(134);
		cellThree.setCellStyle(styleCenter);
		cellThree.setCellValue("住培招收信息");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 25, 45));//合并单元格
		HSSFCell cellFour = rowTwo.createCell(25);
		cellFour.setCellStyle(styleCenter);
		cellFour.setCellValue("中专");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 46, 66));//合并单元格
		HSSFCell cellFive = rowTwo.createCell(46);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("大专");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 67, 87));//合并单元格
		HSSFCell cellSix = rowTwo.createCell(67);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("本科");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 88, 110));//合并单元格
		HSSFCell cellSeven = rowTwo.createCell(88);
		cellSeven.setCellStyle(styleCenter);
		cellSeven.setCellValue("硕士");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 111, 133));//合并单元格
		HSSFCell cellEight = rowTwo.createCell(111);
		cellEight.setCellStyle(styleCenter);
		cellEight.setCellValue("博士");

		HSSFRow rowThree = sheet.createRow(2);//第三行
		String[] titles = new String[]{
				"基地名称",
				"姓名",
				"性别",
				"民族",
				"出生日期",
				"婚姻状况",
				"国籍及地区",
				"身份证件类别",
				"身份证件号码",
				"户口所在地省行政区划",
				"有效的手机号码",
				"有效的QQ号码",
				"有效的电子邮箱地址",
				"是否通过医师资格考试",
				"通过医师资格考试时间",
				"是否获得医师资格证书",
				"取得医师资格证书时间",
				"医师资格级别",
				"医师资格类别",
				"医师资格证书编码",
				"外语等级考试类型",
				"英语能力",
				"外语能力",
				"外语等级考试证书编号",
				"外语等级考试证书取得时间",
				"是否中专",
				"是否是全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",
				"是否大专",
				"是否是全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",
				"是否本科",
				"是否是全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",
				"是否硕士研究生",
				"是否是全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"其他专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"其他专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",

				"是否博士研究生",
				"是否是全日制",
				"是否在读",
				"在读学历",
				"预计毕业时间",
				"在读专业",
				"其他专业",
				"在读院校",
				"其他在读院校",
				"学历",
				"毕业时间",
				"毕业专业",
				"其他专业",
				"毕业院校",
				"其他毕业院校",
				"是否获得毕业证书",
				"学历证书编号",
				"学历证书取得时间",
				"是否获得学位证书",
				"学位",
				"学位类型",
				"学位证书编号",
				"学位证书取得时间",

				"培养类型",
				"人员类型",
				"其他人员",
				"工作单位名称",
				"是否军队医院",
				"工作单位统一信用代码",
				"往届生/应届生",
				"是否为农村订单定向免费医学毕业生",
				"招收年度",
				"培训所在省行政区划",
				"培训专业",
				"核定的培训年限（年）",
				"培训起止时间",
				"是否为西部支援行动住院医师",
				"西部支援行动住院医师送出省份",
				"西部支援行动住院医师送出单位",
				"西部支援行动住院医师送出单位是否军队医院",
				"西部支援行动住院医师送出单位统一社会信用代码"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 45);
		}

		int rowNum = 3;
		String[] dataList = null;
		if (doctorList != null && !doctorList.isEmpty()) {
			for (int i = 0; i < doctorList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				ResDoctorExt resDoctorExt = doctorList.get(i);
				//大字段信息
				PubUserResume pubUserResume = pubUserResumeBiz.readPubUserResume(resDoctorExt.getDoctorFlow());
				BaseUserResumeExtInfoForm extInfo=new BaseUserResumeExtInfoForm();
				if(pubUserResume != null){
					String xmlContent =  pubUserResume.getUserResume();
					if(StringUtil.isNotBlank(xmlContent)){
						//xml转换成JavaBean
						extInfo=pubUserResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
					}
				}
				//培训基地
				String orgName = resDoctorExt.getOrgName();
				String joinName = "";
				String jointFlag = "否";
				String age ="";
				if(resDoctorExt.getSysUser()!=null&&StringUtil.isNotBlank(resDoctorExt.getSysUser().getUserBirthday())) {
					try {
						age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(resDoctorExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
					}catch (Exception e)
					{
						age="";
					}
				}
				if(StringUtil.isBlank(age)){
					age=extInfo.getAge();
				}
				SysUser sysUser = resDoctorExt.getSysUser();
				ResDoctor doctor = readDoctor(sysUser.getUserFlow());
				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(cretTypeId)) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(cretTypeId)) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "护照";
					area="";
				}else if (CertificateTypeEnum.HongKongMacao.getId().equals(cretTypeId)) {
					CretType = "港澳居民来往内地通行证";
					area="";
				}else if (CertificateTypeEnum.Passport.getId().equals(cretTypeId)) {
					CretType = "台湾居民来往内地通行证";
					area="";
				}else {
					CretType = "其他";
					area = "其他";
				}
				//民族
				String userNation = "";
				if(StringUtil.isNotBlank(sysUser.getNationId())) {
					userNation=UserNationEnum.getNameById(sysUser.getNationId());
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}
				//实际培训开始时间
				String recruitDate = doctor.getInHosDate();
				if(StringUtil.isNotBlank(recruitDate)){
					try{
						recruitDate = recruitDate.substring(0,4) + "-" + recruitDate.substring(5,7);
					}catch (Exception e)
					{
						recruitDate="";
					}
				}
				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(doctor.getDoctorLicenseNo())) {
					qualificationFlag = "是";
				} else {
					doctor.setDoctorLicenseNo("");
				}

				//是否是执业医师和编号
				String practPhysicianFlag = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getPractPhysicianFlag())) {
					practPhysicianFlag = "是";
				}
				if (GlobalConstant.FLAG_N.equals(doctor.getPractPhysicianFlag())) {
					practPhysicianFlag="否";
					doctor.setPractPhysicianCertificateNo("");
				}

				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(extInfo.getIsMaster()) || StringUtil.isBlank(extInfo.getIsMaster())) {
					extInfo.setMasterDegreeName("");
					extInfo.setMasterDegreeTypeName("");
					extInfo.setMasterGraSchoolName("");
					extInfo.setMasterGraTime("");
					extInfo.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
					if(StringUtil.isNotBlank(extInfo.getMasterDegreeId())){
						List<SysDict> dicts = DictTypeEnum.UserDegree.getSysDictList();
						if(dicts != null && dicts.size() > 0){
							for(SysDict tempDict : dicts){
								if(extInfo.getMasterDegreeId().equals(tempDict.getDictId())){
									extInfo.setMasterDegreeName(tempDict.getDictName());
								}
							}
						}
					}
					if(StringUtil.isNotBlank(extInfo.getMasterDegreeTypeId())){
						if("1".equals(extInfo.getMasterDegreeTypeId())){
							extInfo.setMasterDegreeTypeName("专业型");
						}
						if("2".equals(extInfo.getMasterDegreeTypeId())){
							extInfo.setMasterDegreeTypeName("科学型");
						}
					}
				}
				//博士
				String doctorFlag = "";
				if (GlobalConstant.FLAG_N.equals(extInfo.getIsDoctor()) || StringUtil.isBlank(extInfo.getIsDoctor())) {
					extInfo.setDoctorDegreeName("");
					extInfo.setDoctorDegreeTypeName("");
					extInfo.setDoctorGraSchoolName("");
					extInfo.setDoctorGraTime("");
					extInfo.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
					if(StringUtil.isNotBlank(extInfo.getDoctorDegreeId())){
						List<SysDict> dicts = DictTypeEnum.UserDegree.getSysDictList();
						if(dicts != null && dicts.size() > 0){
							for(SysDict tempDict : dicts){
								if(extInfo.getDoctorDegreeId().equals(tempDict.getDictId())){
									extInfo.setDoctorDegreeName(tempDict.getDictName());
								}
							}
						}
					}
					if (StringUtil.isNotBlank(extInfo.getDoctorDegreeTypeId())) {
						if("1".equals(extInfo.getDoctorDegreeTypeId())){
							extInfo.setDoctorDegreeTypeName("专业型");
						}
						if("2".equals(extInfo.getDoctorDegreeTypeId())){
							extInfo.setDoctorDegreeTypeName("科学型");
						}
					}
				}
//				//工作单位
//				String property = "";
//				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())) {
//					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
//					if (resBase != null && jointFlag.equals("是")) {
//						property = resBase.getBaseGradeName();
//					}
//					if (StringUtil.isBlank(extInfo.getMedicalHeaithOrgId()) || !"1".equals(extInfo.getMedicalHeaithOrgId())) {
//						extInfo.setHospitalAttrName("");
//						extInfo.setHospitalCategoryName("");
//						extInfo.setBaseAttributeName("");
//					}
//					if (StringUtil.isBlank(extInfo.getMedicalHeaithOrgId()) || "3".equals(extInfo.getMedicalHeaithOrgId())) {
//						extInfo.setBasicHealthOrgName("");
//					}
//
//				} else {
//					doctor.setWorkOrgName("");
//					extInfo.setMedicalHeaithOrgName("");
//				}
				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(extInfo.getMasterGraTime())) {
					masterGraTime = extInfo.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(extInfo.getDoctorGraTime())) {
					doctorGraTime = extInfo.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(extInfo.getGraduationTime())) {
					graduationTime = extInfo.getGraduationTime().substring(0, 4);
				}
				String s="";
				if(StringUtil.isNotBlank(extInfo.getZbkbySpe()))
				{
					s=extInfo.getZbkbySpe();
				}else if(StringUtil.isNotBlank(extInfo.getSpecialized())){
					s=extInfo.getSpecialized();
				}
				//学历
				String educationName = "";
				educationName = sysUser.getEducationName();
				if(StringUtil.isBlank(sysUser.getEducationName())&&StringUtil.isNotBlank(sysUser.getEducationId())){
					List<SysDict> dicts = DictTypeEnum.UserEducation.getSysDictList();
					if(dicts != null && dicts.size() > 0){
						for(SysDict tempDict : dicts){
							if(sysUser.getEducationId().equals(tempDict.getDictId())){
								sysUser.setEducationName(tempDict.getDictName());
							}
						}
					}
					educationName = sysUser.getEducationName();
				}

				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
				if (GlobalConstant.FLAG_Y.equals(extInfo.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(extInfo.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				if(StringUtil.isNotBlank(extInfo.getDegreeId())){
					List<SysDict> dicts = DictTypeEnum.UserDegree.getSysDictList();
					if(dicts != null && dicts.size() > 0){
						for(SysDict tempDict : dicts){
							if(extInfo.getDegreeId().equals(tempDict.getDictId())){
								extInfo.setDegreeName(tempDict.getDictName());
							}
						}
					}
				}
				//规培年限
				String trainYear = "";
				trainYear = doctor.getTrainingYears();
				if(StringUtil.isNotBlank(trainYear)){
					switch (trainYear){
						case "1":{trainYear="一年";break;}
						case "2":{trainYear="两年";break;}
						case "3":{trainYear="三年";break;}
					}
				}
				//医师状态
				String doctorStatus = "";
				//退赔延期原因
				String backDelayInfo ="";
				if("Back".equals(doctorList.get(0).getIs5plus3())){
					doctorStatus="退培";
					List<ResDocotrDelayTeturn> resDocotrDelayTeturns = resDoctorDelayTeturnBiz.searchByDoctorFlow(doctor.getDoctorFlow());
					if(resDocotrDelayTeturns != null && resDocotrDelayTeturns.size()>0){
						for(ResDocotrDelayTeturn tempBack : resDocotrDelayTeturns){
							if(ResRecTypeEnum.ReturnTraining.getId().equals(tempBack.getTypeId())){
								doctorStatus+="（"+tempBack.getAuditStatusName()+"）";
								String auditOpinion = tempBack.getAuditOpinion()==null?"省厅未添加意见":tempBack.getAuditOpinion();
								backDelayInfo = tempBack.getRemark()+"（省厅审核意见："+auditOpinion+"）";
								break;
							}
						}
					}
				}else {
					doctorStatus="在培";
					List<ResDocotrDelayTeturn> resDocotrDelayTeturns = resDoctorDelayTeturnBiz.searchByDoctorFlow(doctor.getDoctorFlow());
					if(resDocotrDelayTeturns != null && resDocotrDelayTeturns.size()>0){
						for(ResDocotrDelayTeturn tempDelay : resDocotrDelayTeturns){
							if(ResRecTypeEnum.Delay.getId().equals(tempDelay.getTypeId())){
								backDelayInfo = tempDelay.getDelayreason();
								break;
							}
						}
					}
				}
				//工作单位
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
				if (ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())||"CompanyEntrust".equals(doctor.getDoctorTypeId())) {
					if (StringUtil.isBlank(extInfo.getMedicalHeaithOrgId()) || !"1".equals(extInfo.getMedicalHeaithOrgId())) {
						extInfo.setHospitalAttrName("");
						extInfo.setHospitalCategoryName("");
						extInfo.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(extInfo.getMedicalHeaithOrgId()) || !"3".equals(extInfo.getMedicalHeaithOrgId())) {
						extInfo.setBasicHealthOrgName("");
					}
					if("1".equals(extInfo.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(extInfo.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(extInfo.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(extInfo.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(extInfo.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(extInfo.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(extInfo.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(extInfo.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(extInfo.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(extInfo.getMedicalHeaithOrgId()))
					{
						hospitalCateName=DictTypeEnum.HospitalCategory.getDictNameById(extInfo.getHospitalCategoryId());
					}
					if("3".equals(extInfo.getMedicalHeaithOrgId()))
					{
						hospitalCateName=DictTypeEnum.BasicHealthOrg.getDictNameById(extInfo.getBasicHealthOrgId());
					}
				} else {
					doctor.setWorkOrgName("");
					extInfo.setMedicalHeaithOrgName("");
				}
				dataList = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						userNation,
						sysUser.getUserBirthday(),
						extInfo.getMaritalStatusName(),
						area,
						CretType,
						sysUser.getIdNo(),
						extInfo.getLocationOfProvinceName(),
						sysUser.getUserPhone(),
						sysUser.getUserQq(),
						sysUser.getUserEmail(),
						StringUtil.isNotBlank(extInfo.getIsPassQualifyingExamination())?(("Y").equals(extInfo.getIsPassQualifyingExamination())?"是":"否"):"",
						extInfo.getPassQualifyingExaminationTime(),
						StringUtil.isNotBlank(doctor.getDoctorLicenseFlag())?(("Y").equals(doctor.getDoctorLicenseFlag())?"是":"否"):"",
						extInfo.getHaveQualificationCertificateTime(),
						StringUtil.isNotBlank(extInfo.getPhysicianQualificationLevel())?(("zyys").equals(extInfo.getPhysicianQualificationLevel())?"执业医师":"助理执业医师"):"",
						extInfo.getPhysicianQualificationClassName(),
						doctor.getDoctorLicenseNo(),
						"",
						"",
						doctor.getForeignSkills(),
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						StringUtil.isNotBlank(extInfo.getIsCollegeDegree())?(("Y").equals(extInfo.getIsCollegeDegree())?"是":"否"):"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						extInfo.getCollegeGraduationTime(),
						extInfo.getCollegeGraduationSpe(),
						extInfo.getCollegeName(),
						"",
						"",
						"",
						"",
						"",
						extInfo.getCollegeDegree(),
						"",
						"",
						"",
						StringUtil.isNotBlank(extInfo.getIsUndergraduateDegree())?(("Y").equals(extInfo.getIsUndergraduateDegree())?"是":"否"):"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						extInfo.getGraduationTime(),
						extInfo.getZbkbySpe(),
						extInfo.getGraduatedName(),
						"",
						"",
						"",
						"",
						"",
						extInfo.getUndergraduateDegreeName(),
						"",
						"",
						"",
						StringUtil.isNotBlank(extInfo.getIsMaster())?(("Y").equals(extInfo.getIsMaster())?"是":"否"):"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						extInfo.getMasterGraTime(),
						extInfo.getMasterMajor(),
						"",
						extInfo.getMasterGraSchoolName(),
						"",
						"",
						"",
						"",
						"",
						extInfo.getMasterDegreeName(),
						extInfo.getMasterDegreeTypeName(),
						"",
						"",
						StringUtil.isNotBlank(extInfo.getIsDoctor())?(("Y").equals(extInfo.getIsDoctor())?"是":"否"):"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						"",
						extInfo.getDoctorGraTime(),
						extInfo.getDoctorMajor(),
						"",
						extInfo.getDoctorGraSchoolName(),
						"",
						"",
						"",
						"",
						"",
						extInfo.getDoctorDegreeName(),
						extInfo.getDoctorDegreeTypeName(),
						"",
						"",

						"",
						doctor.getDoctorTypeName(),
						"",
						doctor.getWorkOrgName(),
						"",
						"",
						isYearGraduate,
						StringUtil.isNotBlank(extInfo.getIsGeneralOrderOrientationTrainee())?(("Y").equals(extInfo.getIsGeneralOrderOrientationTrainee())?"是":"否"):"",
						doctor.getSessionNumber(),
						"湖北",
						doctor.getTrainingSpeName(),
						doctor.getTrainingYears(),
						"",
						StringUtil.isNotBlank(extInfo.getIsAssistance())?(("Y").equals(extInfo.getIsAssistance())?"是":"否"):"",
						extInfo.getAssistanceProvince(),
						extInfo.getWesternSupportResidentsSendWorkUnit(),
						StringUtil.isNotBlank(extInfo.getIsMilitary())?(("Y").equals(extInfo.getIsMilitary())?"是":"否"):"",
						extInfo.getAssistanceCode()
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
	@Override
	public void exportForDetail2(List<ResDoctorExt> doctorList, HttpServletResponse response) throws DocumentException, IOException {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);


		HSSFRow rowThree = sheet.createRow(0);//第三行
		String[] titles = new String[]{
				"姓名",
				"性别",
				"年龄",
				"证件类型",
				"证件号",
				"民族",
				"手机号",
				"邮箱",
				"实习基地",
				"实习专业",
				"年级",
				"实习年限",
				"派送院校"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 1;
		String[] dataList = null;
		if (doctorList != null && !doctorList.isEmpty()) {
			for (int i = 0; i < doctorList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				ResDoctorExt resDoctorExt = doctorList.get(i);
				//大字段信息
				PubUserResume pubUserResume = resDoctorExt.getPubUserResume();//pubUserResumeBiz.readPubUserResume(resDoctorExt.getDoctorFlow());
				BaseUserResumeExtInfoForm extInfo=new BaseUserResumeExtInfoForm();
				if(pubUserResume != null){
					String xmlContent =  pubUserResume.getUserResume();
					if(StringUtil.isNotBlank(xmlContent)){
						//xml转换成JavaBean
						extInfo=pubUserResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class,null);
					}
				}
				//培训基地
				String orgName = resDoctorExt.getOrgName();
				String age ="";
				if(resDoctorExt.getSysUser()!=null&&StringUtil.isNotBlank(resDoctorExt.getSysUser().getUserBirthday())) {
					try {
						age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(resDoctorExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
					}catch (Exception e)
					{
						age="";
					}
				}
				if(StringUtil.isBlank(age)){
					age=extInfo.getAge();
				}
				SysUser sysUser = resDoctorExt.getSysUser();
				ResDoctor doctor = readDoctor(sysUser.getUserFlow());
				String CretType = "";
				if (!CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "其他";
				} else {
					CretType = "身份证";
				}
				//民族
				String userNation = "";
				if(StringUtil.isNotBlank(sysUser.getNationId())) {
					userNation=UserNationEnum.getNameById(sysUser.getNationId());
				}
				//规培年限
				String trainYear = "";
				trainYear = doctor.getTrainingYears();
				if(StringUtil.isNotBlank(trainYear)){
					switch (trainYear){
						case "1":{trainYear="一年";break;}
						case "2":{trainYear="两年";break;}
						case "3":{trainYear="三年";break;}
					}
				}
				dataList = new String[]{
						sysUser.getUserName(),
						sysUser.getSexName(),
						age,
						CretType,
						sysUser.getIdNo(),
						userNation,
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						orgName,
						doctor.getTrainingSpeName(),
						doctor.getSessionNumber(),
						trainYear,
						doctor.getWorkOrgName()
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@Override
	public void exportSchDocDetail(List<ResDoctorExt> doctorList, HttpServletResponse response) throws Exception {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);


		HSSFRow rowThree = sheet.createRow(0);//第三行
		String[] titles = new String[]{
				"姓名",
				"性别",
				"证件类型",
				"证件号",
				"民族",
				"手机号",
				"邮箱",
				"人员类型",
				"培训类别",
				"培训专业",
				"二级专业",
				"年级",
				"培养年限"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowThree.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}

		int rowNum = 1;
		String[] dataList = null;
		if (doctorList != null && !doctorList.isEmpty()) {
			for (int i = 0; i < doctorList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				ResDoctorExt resDoctorExt = doctorList.get(i);
				//培训基地
				String orgName = resDoctorExt.getOrgName();
				SysUser sysUser = resDoctorExt.getSysUser();
				ResDoctor doctor = readDoctor(sysUser.getUserFlow());
				String CretType = "";
				if (!CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "其他";
				} else {
					CretType = "身份证";
				}
				//民族
				String userNation = "";
				if(StringUtil.isNotBlank(sysUser.getNationId())) {
					userNation=UserNationEnum.getNameById(sysUser.getNationId());
				}
				//规培年限
				String trainYear = "";
				trainYear = doctor.getTrainingYears();
				if(StringUtil.isNotBlank(trainYear)){
					switch (trainYear){
						case "1":{trainYear="一年";break;}
						case "2":{trainYear="两年";break;}
						case "3":{trainYear="三年";break;}
					}
				}
				dataList = new String[]{
						sysUser.getUserName(),
						sysUser.getSexName(),
						CretType,
						sysUser.getIdNo(),
						userNation,
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						doctor.getDoctorTypeName(),
						doctor.getTrainingTypeName(),
						doctor.getTrainingSpeName(),
						RecDocCategoryEnum.ChineseMedicine.getId().equals(doctor.getTrainingTypeId())?
								doctor.getSecondSpeName():"--",
						doctor.getSessionNumber(),
						trainYear,
						doctor.getTrainingYears()
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@Override
	public int deleteSchDocUser(String userFlow) {
		SysUser user=new SysUser();
		user.setUserFlow(userFlow);
		user.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		ResDoctor doctor=new ResDoctor();
		doctor.setDoctorFlow(userFlow);
		doctor.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		SchDoctorSelectDept selectDept=new SchDoctorSelectDept();
		SchDoctorSelectDeptExample example=new SchDoctorSelectDeptExample();
		example.createCriteria().andRecordStatusEqualTo("Y").andDoctorFlowEqualTo(userFlow);
		selectDept.setDoctorFlow(userFlow);
		selectDept.setModifyUserFlow("delete");
		selectDept.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		SchOrgArrangeResult result=new SchOrgArrangeResult();
		SchOrgArrangeResultExample example1=new SchOrgArrangeResultExample();
		example1.createCriteria().andRecordStatusEqualTo("Y").andDoctorFlowEqualTo(userFlow);
		result.setDoctorFlow(userFlow);
		result.setModifyUserFlow("delete");
		result.setRecordStatus(GlobalConstant.RECORD_STATUS_N);

		return userBiz.updateUser(user)+doctorMapper.updateByPrimaryKeySelective(doctor)
				+doctorSelectDeptMapper.updateByExample(selectDept,example)
				+orgArrangeResultMapper.updateByExample(result,example1);
	}

	@Override
	public List<ResDoctorExt> getStudents(Map<String, String> params) {
		return doctorExtMapper.getStudents(params);
	}

	@Override
	public ResUserBindMacid readBindMacidByFlow(String doctorFlow) {
		return macidMapper.selectByPrimaryKey(doctorFlow);
	}

	@Override
	public int saveUserMacid(ResUserBindMacid macid) {
		ResUserBindMacid old=readBindMacidByFlow(macid.getUserFlow());
		if(old==null)
		{
			macid.setRecordStatus("Y");
			return macidMapper.insertSelective(macid);
		}else{
			return macidMapper.updateByPrimaryKeySelective(macid);
		}
	}

	@Override
	public void insertDoctor(ResDoctor doctor) {
		if(StringUtil.isNotBlank(doctor.getDoctorFlow()))
		{
			GeneralMethod.setRecordInfo(doctor, true);
			doctorMapper.insert(doctor);
		}
	}

	@Override
	public List<ResDoctorExt> searchDoctorExt(List<String> doctorFlows) {
		if(doctorFlows==null||doctorFlows.size()<1){
			return null;
		}
		return doctorExtMapper.searchDoctorExt(doctorFlows);
	}

	@Override
	public List<ResDoctorExt> searchDoctorExt4Back(List<String> doctorFlows) {
		if(doctorFlows==null||doctorFlows.size()<1){
			return null;
		}
		return doctorExtMapper.searchDoctorExt4Back(doctorFlows);
	}

	@Override
	public List<ResDoctorClobForm> searchClobInfoDoc(String year) {
		return doctorExtMapper.searchClobInfoDoc(year);
	}

	@Override
	public List<ResDoctorExt> searchDocByDiscipleTea(Map<String, Object> param) {
		return doctorExtMapper.searchDocByDiscipleTea(param);
	}

	@Override
	public List<ResDoctorExt> searchDocByDiscipleAdmin(Map<String, Object> param) {
		return doctorExtMapper.searchDocByDiscipleAdmin(param);
	}

	@Override
	public List<Map<String, Object>> teaQueryDocDisciple(Map<String, Object> param) {
		return doctorExtMapper.teaQueryDocDisciple(param);
	}
	@Override
	public List<Map<String, Object>> adminQueryDocDisciple(Map<String, Object> param) {
		return doctorExtMapper.adminQueryDocDisciple(param);
	}

	@Override
	public List<Map<String, Object>> searchSysRecords(Map<String, Object> beMap) {
		return doctorExtMapper.searchSysRecords(beMap);
	}

	@Override
	public List<Map<String, Object>> searchDocSysRecords(Map<String, Object> beMap) {
		return doctorExtMapper.searchDocSysRecords(beMap);
	}

	private String checkScore(String score, String stationName, int i, ExcelUtile resultMap)
	{

		boolean f = score.matches("^(([0-9]{1,4}+)([.]([0-9]{0,2}+))?)$");
		if (!f) {
			resultMap.put("count", 0);
			resultMap.put("code", "1");
			resultMap.put("msg", "导入文件第" + (i + 1) + "行"+stationName+"成绩格式不正确，请确认后提交！！");
			return ExcelUtile.RETURN;
		} else {
			if (Double.valueOf(score) > 100 || Double.valueOf(score) <0) {
				resultMap.put("count", 0);
				resultMap.put("code", "1");
				resultMap.put("msg", "导入文件第" + (i + 1) + "行"+stationName+"成绩不得大于100或小于0，请确认后提交！！");
				return ExcelUtile.RETURN;
			}
		}
		return null;
	}
	private String checkTheroyScore(String score, String stationName, int i, ExcelUtile resultMap)
	{
//		boolean f = score.matches("^(([0-9]{1,4}+)([.]([0-9]{0,2}+))?)$");
//		if (!f) {
//			resultMap.put("count", 0);
//			resultMap.put("code", "1");
//			resultMap.put("msg", "导入文件第" + (i + 1) + "行"+stationName+"成绩格式不正确，请确认后提交！！");
//			return ExcelUtile.RETURN;
//		} else {
//			if (Double.valueOf(score) <0) {
//				resultMap.put("count", 0);
//				resultMap.put("code", "1");
//				resultMap.put("msg", "导入文件第" + (i + 1) + "行"+stationName+"成绩不得小于0，请确认后提交！！");
//				return ExcelUtile.RETURN;
//			}
//		}
		return null;
	}
	@Override
	public String convertMapToXml(Map<String,String> map,ResScore resScore) throws Exception {
		String xmlBody = null;
		Document doc=null;
		Element root =null;
		ResScore old=null;
		if(ResScoreTypeEnum.PublicScore.getId().equals(resScore.getScoreTypeId()))
		{
			old=resScoreBiz.getScoreByDocFlowAndYearAndType(resScore.getDoctorFlow(),null,resScore.getScoreTypeId());
		}else
		{
			old=resScoreBiz.getScoreByDocFlowAndYearAndType(resScore.getDoctorFlow(),resScore.getScorePhaseId(),resScore.getScoreTypeId());
		}
		String content = null==old ? "":old.getExtScore();
		if(null!=old&&StringUtil.isNotBlank(content)) {
			doc = DocumentHelper.parseText(content);
			root = doc.getRootElement();
			Element extScoreInfo = root.element("extScoreInfo");
			if (extScoreInfo != null) {
				List<Element> extInfoAttrEles = extScoreInfo.elements();
				if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
					if (map != null && map.size() > 0) {
						for (String key : map.keySet()) {
							Element item = extScoreInfo.element(key);
							if(item==null)	item=extScoreInfo.addElement(key);
							item.setText(map.get(key));
						}
					}
				}else{
					if (map != null && map.size() > 0) {
						for (String key : map.keySet()) {
							Element item = extScoreInfo.addElement(key);
							item.setText(map.get(key));
						}
					}
				}
			}else{
				extScoreInfo = root.addElement("extScoreInfo");
				if (map != null && map.size() > 0) {
					for (String key : map.keySet()) {
						Element item = extScoreInfo.addElement(key);
						item.setText(map.get(key));
					}
				}
			}
			xmlBody = doc.asXML();
		}else {
			doc = DocumentHelper.createDocument();
			root = doc.addElement("resExtScore");
			Element extScoreInfo = root.addElement("extScoreInfo");
			if (map != null && map.size() > 0) {
				for (String key : map.keySet()) {
					Element item = extScoreInfo.addElement(key);
					item.setText(map.get(key));
				}
			}
			xmlBody = doc.asXML();
		}
		return xmlBody;
	}


	public int  saveBylist( List<ResScore> list)
	{
		if(list!=null&&list.size()>0)
		{
			int count=0;
			for(ResScore resScore: list)
			{

				ResScore old=resScoreBiz.getScoreByDocFlowAndYearAndType(resScore.getDoctorFlow(),resScore.getScorePhaseId(),resScore.getScoreTypeId());
				if(old!=null)
					resScore.setScoreFlow(old.getScoreFlow());
				resScoreBiz.save(resScore);
				count++;
			}
			return  count;
		}
		return  GlobalConstant.ZERO_LINE;
	}
	public int  save(ResScore resScore)
	{
		ResScore old=resScoreBiz.getScoreByDocFlowAndYearAndType(resScore.getDoctorFlow(),resScore.getScorePhaseId(),resScore.getScoreTypeId());
		if(old!=null){
			resScore.setScoreFlow(old.getScoreFlow());
			List<JsresExamSignup> signupLists = doctorRecruitBiz.readDoctorExanSignUps(resScore.getDoctorFlow(), null);
			if(CollectionUtils.isNotEmpty(signupLists)  && ("0".equals(resScore.getTheoryScore()) ||"0".equals(resScore.getSkillScore()))){
				if(StringUtils.isNotBlank(resScore.getRecruitFlow()) && signupLists.size()<=2 ){
					ResDoctorRecruit resDoctorRecruit = doctorRecruitBiz.readResDoctorRecruit(resScore.getRecruitFlow());
					int year = Integer.parseInt(resDoctorRecruit.getGraduationYear());
					resDoctorRecruit.setGraduationYear(year + 1 +"");
					doctorRecruitBiz.updateDocrecruit(resDoctorRecruit);
				}

			}
		}
		return  resScoreBiz.save(resScore);
	}
	@Override
	public int  savePublic(ResScore resScore)
	{
		ResScore old=resScoreBiz.getScoreByDocFlowAndYearAndType(resScore.getDoctorFlow(),null,resScore.getScoreTypeId());
		if(old!=null) resScore.setScoreFlow(old.getScoreFlow());
		return  resScoreBiz.save(resScore);
	}

	@Override
	public List<ResDoctor> searchDoctor4Kq(Map<String, Object> paramMap) {
		return doctorExtMapper.searchDoctor4Kq(paramMap);
	}

	@Override
	public List<Map<String, Object>> globalQueryDocDisciple(Map<String, Object> param) {
		return doctorExtMapper.globalQueryDocDisciple(param);
	}
	@Override
	public int importStudentMainExcel4jszy(MultipartFile file,String orgFlow,String role) {
		InputStream is = null;
		try {
			is =  file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
			return parseManagerExcel4jszy(wb, orgFlow,role);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			try {
				is.close();
			} catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}

	private int parseManagerExcel4jszy(Workbook wb,String orgFlow,String role) {
		SysUser cuurUser=GlobalContext.getCurrentUser();
		String sysOrgFlow = "";
		String sysOrgName = "";
		List<SysOrg> orgList=new ArrayList<>();
		if(StringUtil.isNotBlank(orgFlow)){
			SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
			sysOrgFlow = sysOrg.getOrgFlow();
			sysOrgName = sysOrg.getOrgName();
		}else{
			orgList=orgBiz.searchHbresOrgList();//用来高校导入学员时匹配培训基地
		}
		String resDocRole = InitConfig.getSysCfg("res_doctor_role_flow");
		String schDocRole = InitConfig.getSysCfg("sch_doctor_role_flow");
		SysOrg cuurOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		int count = 0;
		int sheetNum = wb.getNumberOfSheets();
		if(sheetNum>0){
			List<String> colnames = new ArrayList<String>();
			Sheet sheet;
			try{
				sheet = wb.getSheetAt(0);
			}catch(Exception e){
				sheet = wb.getSheetAt(0);
			}
			int row_num = sheet.getLastRowNum()+1;
			if(row_num==1){
				throw new RuntimeException("导入文件内容为空！");
			}
			//获取表头
			Row titleR =  sheet.getRow(0);
			//获取表头单元格数
			int cell_num = titleR.getLastCellNum();
			String title = "";
			for(int i = 0 ; i <cell_num; i++){
				title = titleR.getCell(i).getStringCellValue();
				colnames.add(title);
			}
			for(int i = 1;i <row_num; i++){
				Row r =  sheet.getRow(i);
				SysUser sysUser = new SysUser();
				ResDoctor doctor = new ResDoctor();
				sysUser.setOrgFlow(sysOrgFlow);
				sysUser.setOrgName(sysOrgName);
				doctor.setOrgFlow(sysOrgFlow);
				doctor.setOrgName(sysOrgName);
				BaseUserResumeExtInfoForm resumeExtInfoForm = new BaseUserResumeExtInfoForm();
				for(int j = 0; j < colnames.size(); j++){
					String value = "";
					String currTitle=colnames.get(j);
					Cell cell = r.getCell(j);
					if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
						if(cell.getCellType().getCode() == 1){
							value = cell.getStringCellValue().trim();
						}else{
							value = _doubleTrans(cell.getNumericCellValue()).trim();
						}
					}
					if("培训专业轮转方案".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							doctor.setRotationName(value);
							List<SchRotation> rotationList =schRotationBiz.searchRotationByName(value);
							if(rotationList!=null && rotationList.size()>0){
								SchRotation rotation = rotationList.get(0);
								if(rotation!=null){
									doctor.setRotationFlow(rotation.getRotationFlow());
									sysUser.setMedicineTypeId(rotation.getRotationTypeId());
									sysUser.setMedicineTypeName(rotation.getRotationTypeName());
								}
							}
						}
					}else if("培训基地".equals(currTitle)) {//20190122 高校导入增加培训基地列
						if(StringUtil.isNotBlank(value)&&orgList.size()>0){
							for (SysOrg oo:orgList) {
								if(value.equals(oo.getOrgName())){
									sysUser.setOrgFlow(oo.getOrgFlow());
									sysUser.setOrgName(oo.getOrgName());
									doctor.setOrgFlow(oo.getOrgFlow());
									doctor.setOrgName(oo.getOrgName());
								}
							}
						}
					}else if("用户名".equals(currTitle)) {
						sysUser.setUserCode(value);
					}else if("培训类别".equals(currTitle)){
						doctor.setDoctorCategoryName(value);
						Object id = parseEnumId(RecDocCategoryEnum.values(),value);
						if(id!=null){
							doctor.setDoctorCategoryId(id.toString());
							doctor.setTrainingTypeId(id.toString());
						}
						doctor.setTrainingTypeName(value);
					}else if("真实姓名".equals(currTitle)){
						sysUser.setUserName(value);
					}else if("性别".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							sysUser.setSexName(value);
							Object id = parseEnumId(UserSexEnum.values(),value);
							if(id!=null){
								sysUser.setSexId(id.toString());
							}
						}
					}
					else if("参培年份".equals(currTitle)){
						doctor.setSessionNumber(value);
					}else if("培养年限".equals(currTitle)){
						doctor.setTrainingYears(value);
						if(StringUtil.isBlank(doctor.getTrainingYears())){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，培养年限不能为空！");
						}else {
							List<SysDict> dicts=DictTypeEnum.TrainingYears.getSysDictList();
							boolean f=false;
							if(dicts!=null&&dicts.size()>0){
								for(SysDict d:dicts){
									if(d.getDictName().equals(doctor.getTrainingYears())){
										f=true;
										doctor.setTrainingYears(d.getDictId());
									}
								}
							}
							if(!f){
								throw new RuntimeException("导入失败！第"+ (count+2) +"行，培养年限名称错误！");
							}
						}
					}else if("培训专业".equals(currTitle)){
						doctor.setDoctorCategoryName(value);
						Object id = parseEnumId(RecDocCategoryEnum.values(),value);
						if(id!=null){
							doctor.setDoctorCategoryId(id.toString());
							doctor.setTrainingTypeId(id.toString());
						}
						doctor.setTrainingTypeName(value);
					}else if("对应专业".equals(currTitle)){
						doctor.setTrainingSpeName(value);
					}else if("医师状态".equals(currTitle)){
						if(StringUtil.isNotBlank(value)){
							doctor.setDoctorStatusName(value);
							doctor.setDoctorStatusId(getDictId(value, DictTypeEnum.DoctorStatus.getId()));
						}
					}else if("证件类型".equals(currTitle)){
						sysUser.setCretTypeName(value);
						Object id = parseEnumId(CertificateTypeEnum.values(),value);
						if(id!=null){
							sysUser.setCretTypeId(id.toString());
						}
					}else if("学员类型".equals(currTitle)){
						doctor.setDoctorTypeName(value);
						doctor.setDoctorTypeId(getDictId(value, DictTypeEnum.DoctorType.getId()));
					}
					else if("证件号码".equals(currTitle)){
						sysUser.setIdNo(value);
					}else if("手机".equals(currTitle)){
						sysUser.setUserPhone(value);
					}else if("邮箱".equals(currTitle)){
						sysUser.setUserEmail(value);
					}else if("民族".equals(currTitle)){
						sysUser.setNationName(value);
						Object id = parseEnumId(UserNationEnum.values(),value);
						if(id!=null){
							sysUser.setNationId(id.toString());
						}
					}else if("在读院校".equals(currTitle)){
						doctor.setWorkOrgName(value);
						if(StringUtil.isNotBlank(value)){
							doctor.setWorkOrgId(getDictId(doctor.getWorkOrgName(), DictTypeEnum.SendSchool.getId()));
						}
					}else if("二级专业".equals(currTitle)){
						doctor.setSecondSpeName(value);
						if(StringUtil.isNotBlank(value)){
							doctor.setSecondSpeId(getDictId(doctor.getSecondSpeName(), DictTypeEnum.SecondTrainingSpe.getId()));
						}
					}else if("二级专业轮转方案".equals(currTitle)){
						doctor.setSecondRotationName(value);
					}
				}
				if(StringUtil.isNotBlank(doctor.getDoctorCategoryId()))
				{
					if(StringUtil.isBlank(doctor.getTrainingSpeId())
							&&StringUtil.isNotBlank(doctor.getTrainingSpeName())) {
						if (RecDocCategoryEnum.ChineseMedicine.getId().equals(doctor.getDoctorCategoryId())) {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.ChineseMedicine.getId()));
						} else if (RecDocCategoryEnum.TCMGeneral.getId().equals(doctor.getDoctorCategoryId())) {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.TCMGeneral.getId()));
						} else if (RecDocCategoryEnum.TCMAssiGeneral.getId().equals(doctor.getDoctorCategoryId())) {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.TCMAssiGeneral.getId()));
						} else {
							doctor.setTrainingSpeId(getDictId(doctor.getTrainingSpeName(), DictTypeEnum.DoctorTrainingSpe.getId()));
						}
					}
				}

				if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
					//学员类型、WorkOrgId等
					if(cuurOrg!=null){
						doctor.setWorkOrgId(cuurOrg.getSendSchoolId());
						doctor.setWorkOrgName(cuurOrg.getSendSchoolName());
					}
					doctor.setDoctorTypeId("Graduate");
					doctor.setDoctorTypeName(DictTypeEnum.DoctorType.getDictNameById("Graduate"));
				}
				if(StringUtil.isBlank(sysUser.getOrgName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，请填写正确的培训基地！");
				}
				if(StringUtil.isBlank(doctor.getDoctorCategoryName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训类别不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorCategoryName())&&StringUtil.isBlank(doctor.getDoctorCategoryId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训类别名称错误！");
				}
				if(StringUtil.isBlank(doctor.getSessionNumber())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，参培年份不能为空！");
				}else{
					List<SysDict> dicts=DictTypeEnum.DoctorSessionNumber.getSysDictList();
					boolean f=false;
					if(dicts!=null&&dicts.size()>0)
					{
						for(SysDict d:dicts)
						{
							if(d.getDictId().equals(doctor.getSessionNumber()))
							{
								f=true;
								break;
							}
						}
					}
					if(!f)
					{
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，参培年份错误！");
					}
				}
				if(StringUtil.isBlank(doctor.getDoctorCategoryName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorCategoryName())&&StringUtil.isBlank(doctor.getDoctorCategoryId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业名称错误！");
				}
				if(StringUtil.isBlank(doctor.getTrainingSpeName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，对应专业不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getTrainingSpeName())&&StringUtil.isBlank(doctor.getTrainingSpeId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，对应专业名称错误！");
				}
				if(StringUtil.isBlank(sysUser.getUserCode())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，用户名不能为空！");
				}
				if(StringUtil.isBlank(sysUser.getUserName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，真实姓名不能为空！");
				}
				if(StringUtil.isBlank(sysUser.getSexId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，性别填写有误！");
				}
				if(StringUtil.isBlank(doctor.getDoctorTypeName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，学员类型不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorTypeName())&&StringUtil.isBlank(doctor.getDoctorTypeId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，学员类型文字错误！");
				}
				if(StringUtil.isBlank(doctor.getDoctorStatusName())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，医师状态不能为空！");
				}
				if(StringUtil.isNotBlank(doctor.getDoctorStatusName())&&StringUtil.isBlank(doctor.getDoctorStatusId())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，医师状态文字错误！");
				}
				if(StringUtil.isNotBlank(doctor.getRotationName())&&StringUtil.isBlank(doctor.getRotationFlow())){
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业轮转方案名称错误！");
				}
				SchRotation rotation=schRotationBiz.readSchRotation(doctor.getRotationFlow());
				if(!(doctor.getDoctorCategoryId().equals(rotation.getDoctorCategoryId())&&doctor.getTrainingSpeId().equals(rotation.getSpeId())))
				{
					throw new RuntimeException("导入失败！第"+ (count+2) +"行，培训专业轮转方案中的专业与学员的对应专业不一致！");
				}
				if(RecDocCategoryEnum.ChineseMedicine.getId().equals(doctor.getDoctorCategoryId())
						||RecDocCategoryEnum.TCMGeneral.getId().equals(doctor.getDoctorCategoryId())){
					if(StringUtil.isBlank(doctor.getTrainingSpeName())){
						throw new RuntimeException("导入失败！第"+ (count+2) +"行，对应专业不能为空！");
					}
					if(RecDocCategoryEnum.ChineseMedicine.getId().equals(doctor.getDoctorCategoryId())){
						if(StringUtil.isBlank(doctor.getSecondSpeId())){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级专业填写有误！");
						}
						if(StringUtil.isBlank(doctor.getSecondSpeName())){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级专业不能为空！");
						}
						if(StringUtil.isBlank(doctor.getSecondRotationName())){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级专业轮转方案不能为空！");
						}
						if(StringUtil.isNotBlank(doctor.getSecondRotationName())){
							String secondSpeId = "";
							List<SchRotation> rotationSecondList =schRotationBiz.searchRotationByName(doctor.getSecondRotationName());
							if(rotationSecondList!=null && rotationSecondList.size()>0){
								SchRotation rotationSecond = rotationSecondList.get(0);
								if(rotationSecond!=null){
									secondSpeId=rotationSecond.getSpeId();
									doctor.setSecondRotationFlow(rotationSecond.getRotationFlow());
								}
							}
							if(StringUtil.isBlank(doctor.getSecondRotationFlow())){
								throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级专业轮转方案名称错误！");
							}else if(!doctor.getSecondSpeId().equals(secondSpeId)){
								throw new RuntimeException("导入失败！第"+ (count+2) +"行，二级专业轮转方案中的专业与学员的二级专业不一致！");
							}
						}
					}
					if("在校专硕".equals(doctor.getDoctorTypeName())){
						if(StringUtil.isBlank(doctor.getWorkOrgName())){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，在读院校不能为空！");
						}
						if(StringUtil.isBlank(doctor.getWorkOrgId())){
							throw new RuntimeException("导入失败！第"+ (count+2) +"行，在读院校填写有误！");
						}
					}
				}
				//执行保存
				//判断用户id是否重复
				SysUser old = userBiz.findByUserCode(sysUser.getUserCode());
				if(old!=null){
					throw new RuntimeException("导入失败！第"+(count+2) +"行，该用户名已经被注册！");
				}

				if(StringUtil.isNotBlank(sysUser.getIdNo())){
					old = userBiz.findByIdNo(sysUser.getIdNo());
					if(old!=null){
						throw new RuntimeException("导入失败！第"+(count+2) +"行，该身份证号已经被注册！");
					}
				}

				if(StringUtil.isNotBlank(sysUser.getUserPhone())){
					old =userBiz.findByUserPhone(sysUser.getUserPhone());
					if(old!=null){
						throw new RuntimeException("导入失败！第"+(count+2) +"行，该手机号已经被注册！");
					}
				}

				if(StringUtil.isNotBlank(sysUser.getUserEmail())){
					old = userBiz.findByUserEmail(sysUser.getUserEmail());
					if(old!=null){
						throw new RuntimeException("导入失败！第"+(count+2) +"行,该电子邮箱已经被注册！");
					}
				}
				if(StringUtil.isNotBlank(doctor.getSessionNumber())&&StringUtil.isNotBlank(doctor.getTrainingYears())){
					int gyear=Integer.parseInt(doctor.getSessionNumber())+Integer.parseInt(doctor.getTrainingYears());
					doctor.setGraduationYear(gyear+"");
				}
				sysUser.setUserFlow(PkUtil.getUUID());
				userBiz.addUser(sysUser);
//					ResDoctor doctor = new ResDoctor();
				doctor.setDoctorFlow(sysUser.getUserFlow());
				doctor.setDoctorName(sysUser.getUserName());
				onlySaveResDoctor(doctor);

				SysUserRole userRole = null;
				if(StringUtil.isNotBlank(sysUser.getUserFlow()) && StringUtil.isNotBlank(resDocRole)){
					userRole = userRoleBiz.readUserRole(sysUser.getUserFlow(),resDocRole);
				}
				if(userRole == null){
					userRole = new SysUserRole();
					userRole.setUserFlow(sysUser.getUserFlow());
					userRole.setOrgFlow(sysUser.getOrgFlow());
					String currWsId =GlobalConstant.RES_WS_ID;
					userRole.setWsId(currWsId);
					userRole.setRoleFlow(resDocRole);
					userRole.setAuthTime(DateUtil.getCurrDate());
					userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
					userRoleBiz.saveSysUserRole(userRole);
				}
				count ++;
			}
		}
		return count;
	}

	/**
	 * @Department：研发部
	 * @Description 导出学员出科成绩和技能成绩
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	@Override
	public void exportDoctorRecruitResultList(ResDoctorExt resDoctor, HttpServletResponse response, String papersFlag) throws IOException {
		//1.获取临时文件夹
		String tempFolder = System.getProperty("java.io.tmpdir") + File.separator + PkUtil.getUUID();
		// 学员出科信息和技能评估成绩
		List<Map<String, String>> doctorRecruitList = doctorExtMapper.searchDoctorRecruitResultList(resDoctor);
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle();
		//居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		// 设置边框
		styleCenter.setBorderBottom(BorderStyle.THIN);
		styleCenter.setBorderTop(BorderStyle.THIN);
		styleCenter.setBorderRight(BorderStyle.THIN);
		styleCenter.setBorderLeft(BorderStyle.THIN);
		//第一行 列宽自适应
		HSSFRow rowDep = sheet.createRow(0);
		//合并单元格
		CellRangeAddress cellRowOne = new CellRangeAddress(0, 0, 0, 35);
		sheet.addMergedRegion(cellRowOne);
		// 给合并单元格设置边框
		excelSetBorderForMergeCell(wb, sheet, cellRowOne);
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("学员出科记录表");
		//第二行
		HSSFRow rowTwo = sheet.createRow(1);
		//合并单元格
		CellRangeAddress cellRowTwo = new CellRangeAddress(1, 1, 9, 23);
		sheet.addMergedRegion(cellRowTwo);
		// 给合并单元格设置边框
		excelSetBorderForMergeCell(wb, sheet, cellRowOne);
		HSSFCell secCellZero = rowTwo.createCell(9);
		secCellZero.setCellStyle(styleCenter);
		secCellZero.setCellValue("DOPS");
		//合并单元格
		CellRangeAddress cellFiveRowTwo = new CellRangeAddress(1, 1, 24, 34);
		sheet.addMergedRegion(cellFiveRowTwo);
		// 给合并单元格设置边框
		excelSetBorderForMergeCell(wb, sheet, cellRowOne);
		HSSFCell cellFive = rowTwo.createCell(24);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("Mini-Cex");
		//第三行
		HSSFRow rowThree = sheet.createRow(2);
		String[] titles = new String[]{
				"姓名","专业","年级","轮转科室","轮转时间","带教老师","理论成绩","技能名称","技能成绩",
				"操作例数","复杂程度","1","2","3","4","5","6","7","8","9","10","11",
				"满意度","评语","严重情况","诊治重点","1","2","3","4","5","6","7",
				"满意度","评语","出科考核表材料" };
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			if (i < 9 || i == 35){
				// 合并单元格
				CellRangeAddress cellRangePlanNo = new CellRangeAddress(1, 2, i, i);
				sheet.addMergedRegion(cellRangePlanNo);
				cellTitle = rowTwo.createCell(i);
				// 给合并单元格设置边框
				excelSetBorderForMergeCell(wb, sheet, cellRangePlanNo);
			} else{
				cellTitle = rowThree.createCell(i);
			}
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 1 * 156);
		}
		// 用户名用以给压缩文件命名
		String doctorName = "";
		int rowNum = 3;
		String[] dataList = null;
		if (null != doctorRecruitList && 0 < doctorRecruitList.size()) {
			for (Map<String, String> map : doctorRecruitList) {
				// 导出单个学员出科成绩时取学员姓名作为压缩文件名
				if(StringUtil.isNotBlank(resDoctor.getDoctorFlow()) && "".equals(doctorName)){
					doctorName = map.get("DOCTOR_NAME");
				}
				HSSFRow rowFour = sheet.createRow(rowNum);
				// 封装数据
				dataList = new String[]{
						map.get("DOCTOR_NAME"),
						map.get("TRAINING_TYPE_NAME"),
						map.get("SESSION_NUMBER"),
						map.get("SCH_DEPT_NAME"),
						map.get("SCH_DATE"),
						map.get("TEACHER_USER_NAME"),
						map.get("SCH_SCORE"),
						map.get("SKILLNAME"),
						map.get("SCORE"),
						map.get("STUDENTSKILLNUM"),
						map.get("SKILLCOMPLEXDEGREE"),
						map.get("SKILLLEVEL"),
						map.get("CONSENTFORM"),
						map.get("READYTOWORK"),
						map.get("PAINANDSTABILIZATION"),
						map.get("SKILLABILITY"),
						map.get("ASEPTICTECHNIQUE"),
						map.get("SEEKASSISTANCE"),
						map.get("RELATEDDISPOSAL"),
						map.get("COMMUNICATIONSKILLS"),
						map.get("FEELPROFESSIONALDEGREE"),
						map.get("OVERALLPERFORMANCE"),
						map.get("DEGREESATISFACTION"),
						map.get("TEACHERCOMMENT"),
						map.get("SEVERITY"),
						map.get("DIAGNOSISKEYNOTE"),
						map.get("MEDICALINTERVIEW"),
						map.get("PHYSICALEXAMINATION"),
						map.get("HUMANISTICCARE"),
						map.get("CLINICALJUDGMENT"),
						map.get("COMMUNICATIONSKILLS"),
						map.get("ORGANIZATION"),
						map.get("HOLISTICCARE"),
						map.get("DEGREESATISFACTION"),
						map.get("TEACHERCOMMENT"),
						map.get("FILE_UPLOAD")
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
				rowNum++;
			}
		}
		String fileName = "学员出科记录表.xls";
		// 是否导出试卷 Y是 N否
		if(GlobalConstant.FLAG_Y.equals(papersFlag)){
			int count = downloadPaper(wb, fileName, tempFolder, doctorRecruitList);
			// count > 0 存在试卷信息则生成压缩包 否则直接导出出科成绩信息
			if(0 < count){
				String startEndDate = resDoctor.getSchStart() + "~" + resDoctor.getSchEnd();
				// 导出出科成绩开始时间是空，学员姓名不为空时表示导出单个学员
				if(StringUtil.isBlank(resDoctor.getSchStart()) && StringUtil.isNotBlank(doctorName)){
					startEndDate = doctorName;
				}
				download(tempFolder,startEndDate, response);
			}else{
				// 只导出成绩
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				response.setContentType("application/octet-stream;charset=UTF-8");
				wb.write(response.getOutputStream());
			}
		}else{
			// 只导出成绩
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			wb.write(response.getOutputStream());
		}
	}
	/**
	 * @Department：研发部
	 * @Description 更改开通权限提交
	 * @Author lim
	 * @Date 2020/8/5
	 */
	@Override
	public int updateSubmit(ResDoctor doctor) {
		return doctorMapper.updateByPrimaryKeySelective(doctor);
	}
	/**
	 * @Department：研发部
	 * @Description 更改开通权限提交
	 * @Author lim
	 * @Date 2020/8/6
	 */
	@Override
	public int updateSubmitAll(Map<String,Object> param) {
		return doctorExtMapper.updateSubmitAll(param);
	}

	@Override
	public int saveSubmitAll(List<String> userFlowList) {
		return doctorExtMapper.saveSubmitAll(userFlowList);
	}

	@Override
	public int updateCheckAll(Map<String,Object> param) {
        int count = doctorExtMapper.updateCheckAll(param);
        if(count > 0){
            String flag = (String)param.get("flag");
            List<String> userFlows = (List<String>)param.get("list");
            if (StringUtil.isNotBlank(flag) && userFlows != null && userFlows.size()>0 ) {
                Map<String,Object> newParam = new HashMap<>();
                // 批量修改状态
                for (String userFlow :userFlows ) {
                    newParam.put("flag",flag);
                    newParam.put("userFlow",userFlow);
                    int cfgCount = doctorExtMapper.updatePowerCfg(newParam);
                }
            }
        }
        return count;
	}

	/**
	 * @Department：研发部
	 * @Description 查询带教学员名单
	 * @Author fengxf
	 * @Date 2020/11/18
	 */
	@Override
	public List<Map<String, String>> searchSchDoctorList(Map<String, Object> paramMap) {
		return doctorExtMapper.searchSchDoctorList(paramMap);
	}


	/**
	 * @Department：研发部
	 * @Description 给合并的单元格设置边框
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	private void excelSetBorderForMergeCell(HSSFWorkbook wb, HSSFSheet sheet, CellRangeAddress cellRangePlanNo){
		// 下边框
		RegionUtil.setBorderBottom(BorderStyle.THIN, cellRangePlanNo, sheet);
		// 左边框
		RegionUtil.setBorderLeft(BorderStyle.THIN, cellRangePlanNo, sheet);
		// 右边框
		RegionUtil.setBorderRight(BorderStyle.THIN, cellRangePlanNo, sheet);
		// 上边框
		RegionUtil.setBorderTop(BorderStyle.THIN, cellRangePlanNo, sheet);
	}

	/**
	 * @Department：研发部
	 * @Description 下载试卷
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	private int downloadPaper(HSSFWorkbook wb, String scoreFileName, String tempFolder, List<Map<String, String>> doctorRecruitList){
		// 记录是否执行到试卷信息下载方法
		int count = 0;
		String downloadUrl = InitConfig.getSysCfg("res_after_exam_download_url");
		Map<String, String> urlMap = new HashMap<String, String>();
		String url = "";
		String fileName = "";
		URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		try {
			for(Map<String, String> map : doctorRecruitList){
				// 查询学员考试成绩和试卷信息
				Map<String,String> paperResult = doctorExtMapper.getDoctorScoreAndPaper(map.get("PROCESS_FLOW"));
				if(null == paperResult){
					continue;
				}
				String dowland = "";
				if(StringUtil.isNotBlank(paperResult.get("WORD_URL"))){
					dowland = paperResult.get("WORD_URL");
				}
				if (StringUtil.isBlank(dowland)) {
					url = downloadUrl + "/api/examresultword.ashx?Action=ExamResultWord&CardID=" + URLEncoder.encode(paperResult.get("USER_CODE"),"utf-8") + "&ResultID=" + paperResult.get("RESULTS_ID");
					urlfile = new URL(url);
					httpUrl = (HttpURLConnection) urlfile.openConnection();
					httpUrl.setRequestProperty("Accept-Charset", "utf-8");
					httpUrl.setRequestProperty("contentType", "utf-8");
					httpUrl.connect();
					bis = new BufferedInputStream(httpUrl.getInputStream());
					int len = 2048;
					byte[] b = new byte[len];
					while ((len = bis.read(b)) != -1) {
						dowland += new String(b, "UTF-8").trim();
					}
					if(StringUtil.isNotBlank(dowland)) {
						dowland=java.net.URLDecoder.decode(dowland, "UTF-8");
					}
					url = downloadUrl + "/" + dowland;
					// 文件名
					fileName = paperResult.get("USER_NAME") + "_" + map.get("DEPT_NAME") + "_" + map.get("SCH_START_DATE") + "_" + map.get("SCH_END_DATE") + ".doc";
					urlMap.put(url, fileName);
					// 根据下载链接，下载试卷放入临时文件夹
					String dest = tempFolder + File.separator +"papers";
					downloadPapersByUrl(wb,scoreFileName, urlMap, dest, paperResult.get("USER_NAME"));
					count++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if(httpUrl !=null){
					httpUrl.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	/**
	 * @Department：研发部
	 * @Description 根据url列表下载试卷到指定文件夹
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	private void downloadPapersByUrl(HSSFWorkbook wb, String scoreFileName, Map<String,String> urlMap, String dest, String userName) {
		URL urlfile = null;
		HttpURLConnection httpUrl = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			for (String url : urlMap.keySet()) {
				String fileName = urlMap.get(url);
				// 试卷不是当前用户的则跳过
				if(!fileName.contains(userName)){
					continue;
				}
				urlfile = new URL(url);
				httpUrl = (HttpURLConnection) urlfile.openConnection();
				httpUrl.connect();
				bis = new BufferedInputStream(httpUrl.getInputStream());
				file = new File(dest + File.separator + userName + File.separator + fileName);
				File fileParent = file.getParentFile();
				if (!fileParent.exists()) {
					fileParent.mkdirs();
				}
				file.createNewFile();
				fos = new FileOutputStream(file);
				int len = 2048;
				byte[] b = new byte[len];
				while ((len = bis.read(b)) != -1) {
					fos.write(b, 0, len);
				}
			}
			// 导出时选择下载则从这边下载成绩信息文件
			if(StringUtil.isNotBlank(scoreFileName)){
				file = new File(dest + File.separator + scoreFileName);
				File fileParent = file.getParentFile();
				if (!fileParent.exists()) {
					fileParent.mkdirs();
				}
				file.createNewFile();
				fos = new FileOutputStream(file);
				wb.write(fos);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
					fos.close();
				}
				if(httpUrl !=null){
					httpUrl.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Department：研发部
	 * @Description 生成压缩包导出
	 * @Author fengxf
	 * @Date 2019/11/12
	 */
	private void download(String tempFolder, String sycleMonth, HttpServletResponse response) throws IOException {
		// 压缩文件夹
		File directory = new File(tempFolder + File.separator + "papers");
		File zipFlie = new File(tempFolder + File.separator + sycleMonth + ".zip");
		String zipFolderName = "";
		ZipUtil.makeDirectoryToZip(directory,zipFlie,zipFolderName,7);
		// 输出
		BufferedInputStream bis =  new BufferedInputStream(new FileInputStream(zipFlie));
		byte[] data= new byte[bis.available()];
		int len = 2048;
		byte[] b = new byte[len];
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(zipFlie.getName().getBytes("gbk"),"ISO8859-1" ) + "\"");
		response.addHeader("Content-Length", "" + data.length);
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		while ((len = bis.read(b)) != -1)
		{
			outputStream.write(b, 0, len);
		}
		bis.close();
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * @param resDoctorExt
	 * @param medicineTypeId
	 * @Department：研发部
	 * @Description 查询医师列表
	 * @Author Zjie
	 * @Date 0013, 2021年1月13日
	 */
	@Override
	public List<JsResDoctorRecruitExt> searchDocUserNew(ResDoctorExt resDoctorExt, String medicineTypeId) {
		if (resDoctorExt != null) {
			resDoctorExt.setMedicineTypeId(medicineTypeId);
		}
		return doctorExtMapper.searchResDoctorUserNew(resDoctorExt);
	}

	@Override
	public List<Map<String, String>> searchDoctorSpe() {
		return doctorExtMapper.searchDoctorSpe();
	}

    @Override
    public List<Map<String,Object>> getCountBySessionNumber(List<String> userFlowList) {
        return doctorExtMapper.getCountBySessionNumber(userFlowList);
    }

	@Override
	public List<String> searchRecruitListByOrgFlow(String orgFlow, String isJointOrg) {
		return doctorExtMapper.searchRecruitListByOrgFlow(orgFlow,isJointOrg);
	}

	@Override
	public List<String> getSchools() {
		return doctorExtMapper.getSchools();
	}

	@Autowired
	private SchDoctorDeptExtMapper doctorDeptExtMapper;

	@Resource
	private RedisTemplate<String,String> redisTemplate;

	@Override
	public List<SysUser> listByNameOrIdNo(Set<String> userName, Set<String> idNo) {
		String redisKey = "listByNameOrIdNo:";
		if (CollectionUtil.isNotEmpty(userName)){
			String userKey = StringUtils.join(userName, ",");
			redisKey = redisKey+ userKey;
		}else {
			redisKey = redisKey+ "null";
		}
		if (CollectionUtil.isNotEmpty(idNo)){
			String idNoKey = StringUtils.join(idNo, ",");
			redisKey = redisKey+":"+idNoKey;
		}else {
			redisKey = redisKey+ ":null";
		}
		String s = redisTemplate.opsForValue().get(redisKey);
		if (StringUtils.isNotEmpty(s)) {
			List<SysUser> list = JSONUtil.toList(s, SysUser.class);
			if (CollectionUtil.isNotEmpty(list)) {
				return list;
			}
		}
		List<SysUser> result = doctorDeptExtMapper.listByNameOrIdNo(userName, idNo);
		redisTemplate.opsForValue().set(redisKey,JSONUtil.toJsonStr(result),3, TimeUnit.MINUTES);
		return result;
	}

	@Override
	public List<SchArrangeResult> listDoctorResult(Set<String> userIds) {
		return doctorExtMapper.listDoctorResult(userIds);
	}

	@Override
	public List<ResRecItem> resRecCount(List<String> resultFlowList) {
		if (CollectionUtil.isEmpty(resultFlowList)) {
			return new ArrayList<>();
		}
		return doctorExtMapper.resRecCount(resultFlowList);
	}
}
