package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.sci.dao.JsresSupervisioFileMapper;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.FtpHelperUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.ISysSupervisioUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.jsres.JsresSupervisioSubjectExtMapper;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SysSupervisioUserBizImpl implements ISysSupervisioUserBiz {
	@Autowired
	private SysSupervisioUserMapper supervisioUserMapper;
	@Autowired
	private JsresSupervisioSubjectExtMapper subjectExtMapper;
	@Autowired
	private ResSupervisioSubjectMapper subjectMapper;
	@Autowired
	private ResSupervisioSubjectUserMapper subjectUserMapper;
	@Autowired
	private ResEvaluationScoreMapper evaluationScoreMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private  ResSupervisioSubjectRecordsMapper subjectRecordsMapper;
	@Autowired
	private JsresSupervisioFileMapper jsresSupervisioFileMapper;
	@Autowired
	private ResSupervisioReportMapper reportMapper;
	@Autowired
	private ResHospSupervSubjectMapper hospSupervSubjectMapper;

    private static final Logger logger = LoggerFactory.getLogger(SysSupervisioUserBizImpl.class);

	@Override
	public List<ResSupervisioSubject> selectBaseSubjectList(Map<String, Object> param) {
		return subjectExtMapper.selectBaseSubjectList(param);
	}

	@Override
	public List<ResSupervisioSubject> selectSubjectActiveListByParam(Map<String, Object> param) {
		return subjectExtMapper.selectSubjectActiveListByParam(param);
	}

	@Override
	public List<Map<String, String>> searchBaseExpertSupervisio(Map<String, Object> param) {
		return subjectExtMapper.searchBaseExpertSupervisio(param);
	}

	@Override
	public List<ResSupervisioSubjectRecords> selectRecordBySubjectFlowAndRoleFlag(String subjectFlow,String roleFlag) {
		return  subjectExtMapper.selectRecordBySubjectFlowAndRoleFlag(subjectFlow,roleFlag);
	}

	@Override
	public int saveReport(ResSupervisioReport resSupervisioReport) {
		ResSupervisioReportExample example=new ResSupervisioReportExample();
		if (StringUtil.isNotBlank(resSupervisioReport.getRecordFlow())){
			example.createCriteria().andRecordFlowEqualTo(resSupervisioReport.getRecordFlow());
		}
		return reportMapper.updateByExampleWithBLOBs(resSupervisioReport,example);
	}

	@Override
	public ResSupervisioReport expertMajorBySubject(String subjectFlow,String subjectActivitiFlows,String partofFlow) {
		ResSupervisioReportExample example=new ResSupervisioReportExample();
		ResSupervisioReportExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		if (StringUtil.isNotBlank(subjectActivitiFlows)){
			criteria.andSubjectFlowEqualTo(subjectActivitiFlows);
		}
		if (StringUtil.isNotBlank(partofFlow)){
			criteria.andPartofFlowEqualTo(partofFlow);
		}
		List<ResSupervisioReport> list = reportMapper.selectByExampleWithBLOBs(example);
		if (list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addReport(ResSupervisioReport resSupervisioReport) {
		return reportMapper.insert(resSupervisioReport);
	}

	@Override
	public ResSupervisioReport selectReportContextMas(ResSupervisioReport resSupervisioReport) {
		ResSupervisioReportExample example=new ResSupervisioReportExample();
		ResSupervisioReportExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(resSupervisioReport.getContentTitle())){
			criteria.andContentTitleEqualTo(resSupervisioReport.getContentTitle());
		}
		if (StringUtil.isNotBlank(resSupervisioReport.getSubjectFlow())){
			criteria.andSubjectFlowEqualTo(resSupervisioReport.getSubjectFlow());
		}
		if (StringUtil.isNotBlank(resSupervisioReport.getPartof())){
			criteria.andPartofEqualTo(resSupervisioReport.getPartof());
		}
		List<ResSupervisioReport> reportList = reportMapper.selectByExampleWithBLOBs(example);
		if (reportList!=null && reportList.size()>0){
			return reportList.get(0);
		}
		return null;
	}

	@Override
	public ResSupervisioReport expertMajorByrecordFlow(String recordFlow) {
		ResSupervisioReportExample example=new ResSupervisioReportExample();
		if (StringUtil.isNotBlank(recordFlow)){
			example.createCriteria().andRecordFlowEqualTo(recordFlow);
		}
		List<ResSupervisioReport> list = reportMapper.selectByExampleWithBLOBs(example);
		if (list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<SysUser> selectUserBysubjectActivitiFlows(String subjectActivitiFlows) {
		return subjectExtMapper.selectUserBysubjectActivitiFlows(subjectActivitiFlows);
	}


	@Override
	public List<ResSupervisioReport> reportBySubjectList(String subjectFlow, String subjectActivitiFlows, String partof) {
		ResSupervisioReportExample example=new ResSupervisioReportExample();
		ResSupervisioReportExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		if (StringUtil.isNotBlank(subjectActivitiFlows)){
			criteria.andSubjectFlowEqualTo(subjectActivitiFlows);
		}
		if (StringUtil.isNotBlank(partof)){
			criteria.andPartofEqualTo(partof);
		}
		return reportMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<JsresSupervisioFile> searchSubjectFile(String subjectYear, String subjectFlow, String speId) {
		JsresSupervisioFileExample example = new JsresSupervisioFileExample();
		JsresSupervisioFileExample.Criteria criteria = example.createCriteria();
		if (com.pinde.core.util.StringUtil.isNotBlank(subjectFlow)) {
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		if (com.pinde.core.util.StringUtil.isNotBlank(subjectYear)) {
			criteria.andPlanYearEqualTo(subjectYear);
		}
		if (com.pinde.core.util.StringUtil.isNotBlank(speId)) {
			criteria.andSpeIdEqualTo(speId);
		}
		return jsresSupervisioFileMapper.selectByExample(example);
	}

	@Override
	public int insertRecores(String subjectFlow, String userFlow, String roleFlag) {
		//添加提交记录
		ResSupervisioSubjectRecords records = new ResSupervisioSubjectRecords();
		records.setRecordFlow(PkUtil.getUUID());
        records.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		records.setCreateTime(DateUtil.getCurrDateTime());
		records.setCreateUserFlow(userFlow);
		records.setRoleFlag(roleFlag);
		records.setUserFlow(userFlow);
		records.setSubjectFlow(subjectFlow);
		ResSupervisioSubjectRecords subjectRecords = selectRecords(subjectFlow,userFlow);
		if (null!=subjectRecords){
			records.setSubNum(String.valueOf(Integer.parseInt(subjectRecords.getSubNum())+1));
		}else {
			records.setSubNum("1");
		}
		return subjectRecordsMapper.insert(records);
	}

	@Override
	public List<ResSupervisioSubject> selectLocalSubjectListByParam(Map<String, Object> param) {
		return subjectExtMapper.selectLocalSubjectListByParam(param);
	}

	@Override
	public List<ResSupervisioSubjectUser> selectSubjectUserBySubjectActivitiFlows(String subjectActivitiFlows) {
		ResSupervisioSubjectUserExample example=new ResSupervisioSubjectUserExample();
		if (StringUtil.isNotBlank(subjectActivitiFlows)){
			example.createCriteria().andSubjectActivitiFlowsEqualTo(subjectActivitiFlows).andEvaluationDateIsNotNull();
		}
		return subjectUserMapper.selectByExample(example);
	}

	@Override
	public List<ResSupervisioSubjectUser> searchSubjectAndUserLevedId(String subjectFlow, String userLevedId) {
		return subjectExtMapper.searchSubjectAndUserLevedId(subjectFlow,userLevedId);
	}

	@Override
	public ResSupervisioSubjectRecords selectRecords(String subjectFlow,String userFlow) {
		ResSupervisioSubjectRecordsExample example=new ResSupervisioSubjectRecordsExample();
		ResSupervisioSubjectRecordsExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		if (StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		example.setOrderByClause("CREATE_TIME DESC");
		List<ResSupervisioSubjectRecords> list = subjectRecordsMapper.selectByExample(example);
		if (null != list  && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public SysUser findUserByUserCode(String userCode) {
		SysUserExample sysUserExample=new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode);
		List<SysUser> userList = sysUserMapper.selectByExample(sysUserExample);
		if(userList.size()>0){
			return userList.get(0);
		}
		return null;
	}

	@Override
	public SysSupervisioUser findByUserCode(String userCode) {
		SysSupervisioUserExample sysUserExample=new SysSupervisioUserExample();
		SysSupervisioUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode);
		List<SysSupervisioUser> sysUserList = supervisioUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public SysSupervisioUser findByUserPhone(String userPhone) {
		SysSupervisioUserExample sysUserExample=new SysSupervisioUserExample();
		SysSupervisioUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserPhoneEqualTo(userPhone);
		List<SysSupervisioUser> sysUserList = supervisioUserMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public List<SysUser> findByUserPhoneForLeader(String userFlow,String userPhone) {
		SysUserExample example=new SysUserExample();
		SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andUserPhoneEqualTo(userPhone).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowNotEqualTo(userFlow);
		}else {
			criteria.andUserFlowIsNotNull();
		}
		return sysUserMapper.selectByExample(example);
	}

	@Override
	public List<SysUser> selectSupervisioUserList(Map<String, Object> param) {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank((String)param.get("userName"))){
			criteria.andUserNameLike("%"+param.get("userName")+"%");
		}
		if(StringUtil.isNotBlank((String)param.get("userPhone"))){
			criteria.andUserPhoneEqualTo((String)param.get("userPhone"));
		}
		if (StringUtil.isNotBlank((String)param.get("suAoth"))){
			criteria.andUserLevelIdEqualTo((String)param.get("userLevelId"));
		}else {
			criteria.andUserLevelIdNotEqualTo("baseExpert");
			if(StringUtil.isNotBlank((String)param.get("userLevelId"))){
				criteria.andUserLevelIdEqualTo((String)param.get("userLevelId"));
			}
		}
		if(StringUtil.isNotBlank((String)param.get("speId"))){
			criteria.andResTrainingSpeIdEqualTo((String)param.get("speId"));
		}
		if (StringUtil.isNotBlank((String)param.get("orgFlow"))){
			criteria.andOrgFlowEqualTo((String)param.get("orgFlow"));
		}
		if(param.get("userLevel") != null){
			criteria.andUserLevelIdIn((List<String>)param.get("userLevel"));
		}
		return sysUserMapper.selectByExample(sysUserExample);
	}


	@Override
	public List<SysUser> selectSysUserList(Map<String, Object> param) {
		SysUserExample sysUserExample = new SysUserExample();
		SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank((String)param.get("userName"))){
			criteria.andUserNameLike("%"+param.get("userName")+"%");
		}
		if(StringUtil.isNotBlank((String)param.get("userPhone"))){
			criteria.andUserPhoneEqualTo((String)param.get("userPhone"));
		}
		if (StringUtil.isNotBlank((String)param.get("suAoth"))){
			criteria.andUserLevelIdEqualTo((String)param.get("userLevelId"));
		}else {
			criteria.andUserLevelIdNotEqualTo("baseExpert");
			if(StringUtil.isNotBlank((String)param.get("userLevelId"))){
				criteria.andUserLevelIdEqualTo((String)param.get("userLevelId"));
			}
		}
		if (StringUtil.isNotBlank((String)param.get("orgFlow"))){
			criteria.andOrgFlowEqualTo((String)param.get("orgFlow"));
		}
		if(param.get("speId") != null){
			criteria.andResTrainingSpeIdIn((List<String>)param.get("speId"));
		}

		if(param.get("userLevel") != null){
			criteria.andUserLevelIdIn((List<String>)param.get("userLevel"));
		}
		if (param.get("deptFlow") != null && StringUtil.isNotBlank((String) param.get("deptFlow"))){
			criteria.andDeptFlowEqualTo((String) param.get("deptFlow"));
		}
		return sysUserMapper.selectByExample(sysUserExample);
	}

	@Override
	public SysSupervisioUser findByUserFlow(String userFlow) {
		return supervisioUserMapper.selectByPrimaryKey(userFlow);
	}

	@Transactional
	@Override
	public int addSupervisioUser(SysUser user) {
		// 新增用户授权角色
		SysUserRole userRole = new SysUserRole();
		userRole.setUserFlow(user.getUserFlow());
		userRole.setOrgFlow(user.getOrgFlow());
		userRole.setWsId("res");
		String roleFlow = "";
		if(StringUtil.isNotBlank(user.getUserLevelId())){
			// 管理专家
			if("management".equals(user.getUserLevelId())){
				roleFlow = InitConfig.getSysCfg("res_management_role_flow");
			}else if("expertLeader".equals(user.getUserLevelId())){
				// 专业专家
				roleFlow = InitConfig.getSysCfg("res_expertLeader_role_flow");
			}else if("baseExpert".equals(user.getUserLevelId())){
				// 基地专业专家
				roleFlow = InitConfig.getSysCfg("res_baseExpert_role_flow");
			}else if("basePer".equals(user.getUserLevelId())){
				// 基地专业科室专家
				roleFlow = InitConfig.getSysCfg("res_base_per_expert_role_flow");
			}else if("hospitalLeader".equals(user.getUserLevelId())){
				// 基地专业科室专家
				roleFlow = InitConfig.getSysCfg("res_hospitalLeader_role_flow");
			}
		}
		userRole.setRoleFlow(roleFlow);
		userRole.setAuthTime(DateUtil.getCurrDateTime());
		userRole.setAuthUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		userRole.setRecordFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(userRole, true);
		sysUserRoleMapper.insertSelective(userRole);
		GeneralMethod.setRecordInfo(user, true);
		return sysUserMapper.insertSelective(user);
	}

	@Override
	public int editSupervisioUser(SysUser user) {
		GeneralMethod.setRecordInfo(user, false);
		return sysUserMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int importSupervisioUser(MultipartFile file) throws Exception {
		InputStream is = file.getInputStream();
		byte[] fileData = new byte[(int) file.getSize()];
		is.read(fileData);
		Workbook wb =  createUserWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
		return parseExcelToUser(wb);
	}

	@Override
	public int importHospitalLeader(MultipartFile file) throws Exception {
		InputStream is = file.getInputStream();
		byte[] fileData = new byte[(int) file.getSize()];
		is.read(fileData);
		Workbook wb =  createUserWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
		return parseExcelToHospitalLeader(wb);
	}

	@Override
	public List<Map<String, String>> searchManegeSupervisioList(Map<String, Object> param) {
		return subjectExtMapper.searchManegeSupervisioList(param);
	}

	@Override
	public List<SysUser> findByUserPhoneAndNotSelf(String userFlow,String userPhone) {
		SysUserExample example = new SysUserExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andUserPhoneEqualTo(userPhone).andUserFlowNotEqualTo(userFlow);
		return sysUserMapper.selectByExample(example);
	}

	@Override
	public String saveFileToDirs(String oldFolderName, MultipartFile file, String folderName){
        String path = com.pinde.core.common.GlobalConstant.FLAG_N;
		if(file.getSize() > 0){
			//创建目录
			String dateString = DateUtil.getCurrDate2();
			String newDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator + folderName + File.separator+ dateString ;
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
                logger.error("", e);
				throw new RuntimeException("保存文件失败！");
			}

			//删除原文件
			if(com.pinde.core.util.StringUtil.isNotBlank(oldFolderName)){
				try {
					oldFolderName = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + oldFolderName;
					File imgFile = new File(oldFolderName);
					if(imgFile.exists()){
						imgFile.delete();
					}
				} catch (Exception e) {
                    logger.error("", e);
					throw new RuntimeException("删除文件失败！");
				}
			}
			path = folderName + "/"+dateString+"/"+originalFilename;
			FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
			String localFilePath=fileDir+File.separator+originalFilename;
			String ftpDir= folderName+File.separator +dateString ;
			String ftpFileName=originalFilename;

			System.out.println("===============FTP上传开始 ============= localFilePath："+localFilePath);
			ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
			System.out.println("===============FTP上传开始 ============= localFilePath："+localFilePath);
		}
		return path;
	}

	@Override
	public String eidtPassword(String userCode, String oldUserPasswd, String ideentityCheck, String userPasswd) {
		userCode = com.pinde.core.util.StringUtil.defaultIfEmpty(userCode, "").trim();
		System.out.println("----------------userCode="+userCode);
		System.out.println("----------------oldUserPasswd="+oldUserPasswd);
		SysSupervisioUser user = this.findByUserPhone(userCode);
		//判断用户是否存在
		if (user == null) {
			return "当前用户在系统中不存在，请联系管理员";
		}
		if (user != null) {
			if (!user.getUserPasswd().equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), oldUserPasswd))) {
				return "原密码不正确";
			}
			System.out.println("----------------1原密码不正确");
			if (StringUtil.isNotBlank(ideentityCheck) && (ideentityCheck.equals(user.getUserPhone()))) {
				user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd));
//				this.editSupervisioUser(user);
                return com.pinde.core.common.GlobalConstant.UPDATE_SUCCESSED;
			} else {
				return "您的手机号有误";
			}
		}
        return com.pinde.core.common.GlobalConstant.UPDATE_FAIL;
	}

	@Override
	public List<ResSupervisioSubject> selectSubjectList(Map<String, Object> param) {
		return subjectExtMapper.selectSubjectListInfo(param);
	}

	@Override
	public List<ResSupervisioSubject> selectGrobalSubjectList(Map<String, Object> param) {
		return subjectExtMapper.selectGrobalSubjectListInfo(param);
	}

	@Override
	public List<ResSupervisioSubjectUser> selectSupervisioUserListByFlowAndUserLevelID(String subjectFlow) {
		if (StringUtil.isNotBlank(subjectFlow)){
			return subjectExtMapper.selectSupervisioUserListByFlowAndUserLevelID(subjectFlow);
		}
		return null;
	}

	@Override
	public List<ResSupervisioSubject> selectBySubjectFlow(String subjectFlow) {
		ResSupervisioSubjectExample example=new ResSupervisioSubjectExample();
        ResSupervisioSubjectExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		return subjectMapper.selectByExample(example);
	}

	@Override
	public List<ResSupervisioSubjectUser> selectSubjectUserBysubjectActivitiFlows(String userFlow, String subjectActivitiFlows) {
		ResSupervisioSubjectUserExample example = new ResSupervisioSubjectUserExample();
        ResSupervisioSubjectUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		if(StringUtil.isNotBlank(subjectActivitiFlows)){
			criteria.andSubjectActivitiFlowsEqualTo(subjectActivitiFlows);
		}
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return subjectUserMapper.selectByExample(example);
	}

	@Override
	public ResSupervisioSubjectUser selectSysSupervisioUserByUserFlowAndSubjectFlow(String userFlow, String subjectFlow) {
		ResSupervisioSubjectUserExample example=new ResSupervisioSubjectUserExample();
		ResSupervisioSubjectUserExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		if (StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		List<ResSupervisioSubjectUser> subjectUserList = subjectUserMapper.selectByExample(example);
		if (subjectUserList.size()>0){
			return subjectUserList.get(0);
		}
		return null;
	}

	@Override
	public List<ResSupervisioSubject> selectBySubjectActivitiFlows(String subjectActivitiFlows) {
		ResSupervisioSubjectExample example=new ResSupervisioSubjectExample();
        ResSupervisioSubjectExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(subjectActivitiFlows)){
			criteria.andSubjectActivitiFlowsEqualTo(subjectActivitiFlows);
		}
		return subjectMapper.selectByExample(example);
	}

	@Override
	public ResSupervisioSubjectUser searchSubjectUser(String userFlow,String subjectFlow) {
		ResSupervisioSubjectUserExample example = new ResSupervisioSubjectUserExample();
        ResSupervisioSubjectUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		if(StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		List<ResSupervisioSubjectUser> list = subjectUserMapper.selectByExample(example);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int updateSubjectFeedback(Map<String, Object> param) {
		return subjectExtMapper.updateSubjectFeedback(param);
	}

	@Override
	public Map<String, Object> selectAvgScoreBySubjectActivitiFlows(String subjectActivitiFlows) {
		return subjectExtMapper.selectAvgScoreBySubjectActivitiFlows(subjectActivitiFlows);
	}

	@Override
	public String searchSubjectNameNum(String subjectName) {
		return subjectExtMapper.searchSubjectNameNum(subjectName);
	}

	@Override
	public String searchSubExpertNum(String subjectFlow) {
		return subjectExtMapper.searchSubExpertNum(subjectFlow);
	}

	@Override
	public List<ResSupervisioReport> selectAllName(String name) {
		ResSupervisioReportExample example=new ResSupervisioReportExample();
		if (StringUtil.isNotBlank(name)){
			example.createCriteria().andOrderNameEqualTo(name);
		}
		example.setOrderByClause("RECORD_FLOW");
		return reportMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResSupervisioReport> expertMajorBySubjectList(String subjectFlow, String subjectActivitiFlows) {
		ResSupervisioReportExample example=new ResSupervisioReportExample();
		ResSupervisioReportExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		if (StringUtil.isNotBlank(subjectActivitiFlows)){
			criteria.andSubjectFlowEqualTo(subjectActivitiFlows);
		}
		return reportMapper.selectByExampleWithBLOBs(example);
	}


	@Override
	public List<ResHospSupervSubject> readHospSuperBySubjectName(String subjectName) {
		ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
		if (StringUtil.isNotBlank(subjectName)){
            example.createCriteria().andSubjectNameEqualTo(subjectName).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		}

		return hospSupervSubjectMapper.selectByExample(example);
	}

	@Override
	public List<ResHospSupervSubject> selectHospSuperList(HashMap<String, Object> map) {
		return subjectExtMapper.selectHospSuperList(map);
	}

	@Override
	public ResHospSupervSubject selectHospSupervisioBySubjectFlow(String subjectFlow) {
		ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
		ResHospSupervSubjectExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		List<ResHospSupervSubject> list = hospSupervSubjectMapper.selectByExample(example);
		if (null !=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int delHospSupervisioBySubjectFlow(String subjectFlow) {
		ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
		ResHospSupervSubjectExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		List<ResHospSupervSubject> list = hospSupervSubjectMapper.selectByExample(example);
		if (null !=list && !list.isEmpty()){
			ResHospSupervSubject subject = list.get(0);
            subject.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
			return hospSupervSubjectMapper.updateByExample(subject, example);
		}
		return 0;
	}

	@Override
	public int updateHospSupervisioBySubjectFlow(ResHospSupervSubject resHospSupervSubject) {
		ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
		ResHospSupervSubjectExample.Criteria criteria = example.createCriteria();
		if (null != resHospSupervSubject.getSubjectFlow()){
			criteria.andSubjectFlowEqualTo(resHospSupervSubject.getSubjectFlow());
			return hospSupervSubjectMapper.updateByExample(resHospSupervSubject,example);
		}
		return 0;
	}

	@Override
	public List<ResSupervisioSubject> selectBySubject(ResSupervisioSubject subject) {
		ResSupervisioSubjectExample example = new ResSupervisioSubjectExample();
		ResSupervisioSubjectExample.Criteria criteria = example.createCriteria();
		if (StringUtil.isNotBlank(subject.getSubjectYear())){
			criteria.andSubjectYearEqualTo(subject.getSubjectYear());
		}
		if (StringUtil.isNotBlank(subject.getBaseCode())){
			criteria.andBaseCodeEqualTo(subject.getBaseCode());
		}
		if (StringUtil.isNotBlank(subject.getSpeId())){
			criteria.andSpeIdEqualTo(subject.getSpeId());
		}
		if (StringUtil.isNotBlank(subject.getSupervisioResults())){
			criteria.andSupervisioResultsEqualTo(subject.getSupervisioResults());
		}
		if (StringUtil.isNotBlank(subject.getOrgFlow())){
			criteria.andOrgFlowEqualTo(subject.getOrgFlow());
		}
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		return subjectMapper.selectByExample(example);
	}

	@Override
	public ResHospSupervSubject selectHospByActivityFlow(String activityFlow) {
		ResHospSupervSubjectExample example = new ResHospSupervSubjectExample();
		example.createCriteria().andActivityFlowEqualTo(activityFlow);
		List<ResHospSupervSubject> list = hospSupervSubjectMapper.selectByExample(example);
		if (null !=list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, String>> searchEvaluationIndicators(ArrayList<String> list) {
		return subjectExtMapper.searchEvaluationIndicators(list);
	}

	@Override
	public List<ResHospSupervSubject> hospitalLeaderScoreList(Map<String, Object> map) {
		return subjectExtMapper.hospitalLeaderScoreList(map);
	}

	@Override
	public ResSupervisioSubject selectSubjectByFlow(String subjectFlow) {
		return subjectMapper.selectByPrimaryKey(subjectFlow);
	}

	@Override
	public List<ResSupervisioSubjectUser> selectSupervisioUserListByFlow(String subjectFlow) {
		ResSupervisioSubjectUserExample example = new ResSupervisioSubjectUserExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
				.andSubjectFlowEqualTo(subjectFlow);
		return subjectUserMapper.selectByExample(example);
	}

	@Override
	public List<SysSupervisioUser> queryUserList(String userName) {
		SysSupervisioUserExample example = new SysSupervisioUserExample();
        SysSupervisioUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if (StringUtil.isNotBlank(userName)) {
			criteria.andUserNameLike("%"+userName+"%");
		}
		return supervisioUserMapper.selectByExample(example);
	}

	@Override
	public int saveSubject(ResSupervisioSubject subject) {
		if(StringUtil.isNotBlank(subject.getSubjectFlow())) {
			GeneralMethod.setRecordInfo(subject, false);
			return subjectMapper.updateByPrimaryKeySelective(subject);
		}else {
			subject.setSubjectFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(subject, true);
			return subjectMapper.insert(subject);
		}
	}

	@Override
	public int delSubjectUserBySubjectFlow(String subjectFlow) {
		return subjectExtMapper.delSubjectUserBySubjectFlow(subjectFlow);
	}

	@Override
	public int saveSubjectUser(ResSupervisioSubjectUser subjectUser) {
		if(StringUtil.isNotBlank(subjectUser.getRecordFlow())) {
			GeneralMethod.setRecordInfo(subjectUser, false);
			return subjectUserMapper.updateByPrimaryKeySelective(subjectUser);
		}else {
			subjectUser.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(subjectUser, true);
			return subjectUserMapper.insert(subjectUser);
		}
	}

	@Override
	public List<String> selectSupervisioUserFlowListByFlow(String subjectFlow) {
		return subjectExtMapper.selectSupervisioUserFlowListByFlow(subjectFlow);
	}

	@Override
	public List<ResSupervisioSubject> selectSubjectListByParam(Map<String, Object> param) {
		return subjectExtMapper.selectSubjectListByParam(param);
	}

	@Override
	public ResEvaluationScore searchEvaluationOwnerScoreByItemId(ResEvaluationScore evaluationScore) {
		ResEvaluationScoreExample example = new ResEvaluationScoreExample();
        ResEvaluationScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(evaluationScore.getSubjectFlow())){
			criteria.andSubjectFlowEqualTo(evaluationScore.getSubjectFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getItemId())){
			criteria.andItemIdEqualTo(evaluationScore.getItemId());
		}
		if(StringUtil.isNotBlank(evaluationScore.getOrgFlow())){
			criteria.andOrgFlowEqualTo(evaluationScore.getOrgFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeId())){
			criteria.andSpeIdEqualTo(evaluationScore.getSpeId());
		}
		if(StringUtil.isNotBlank(evaluationScore.getEvaluationYear())){
			criteria.andEvaluationYearEqualTo(evaluationScore.getEvaluationYear());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow()) && "Owner".equals(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowIsNull();
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow()) && !"Owner".equals(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowEqualTo(evaluationScore.getSpeUserFlow());
		}
		List<ResEvaluationScore> list = evaluationScoreMapper.selectByExample(example);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int saveScore(ResEvaluationScore evaluationScore) {
		if(StringUtil.isNotBlank(evaluationScore.getScoreFlow())){
			GeneralMethod.setRecordInfo(evaluationScore,false);
			return evaluationScoreMapper.updateByPrimaryKey(evaluationScore);
		}else{
			evaluationScore.setScoreFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(evaluationScore,true);
			return evaluationScoreMapper.insert(evaluationScore);
		}
	}

	@Override
	public List<ResEvaluationScore> searchEvaluationScore(ResEvaluationScore evaluationScore) {
		ResEvaluationScoreExample example = new ResEvaluationScoreExample();
        ResEvaluationScoreExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(evaluationScore.getSubjectFlow())){
			criteria.andSubjectFlowEqualTo(evaluationScore.getSubjectFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getOrgFlow())){
			criteria.andOrgFlowEqualTo(evaluationScore.getOrgFlow());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeId())){
			criteria.andSpeIdEqualTo(evaluationScore.getSpeId());
		}
		if(StringUtil.isNotBlank(evaluationScore.getEvaluationYear())){
			criteria.andEvaluationYearEqualTo(evaluationScore.getEvaluationYear());
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow()) && "Owner".equals(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowIsNull();
		}
		if(StringUtil.isNotBlank(evaluationScore.getSpeUserFlow()) && !"Owner".equals(evaluationScore.getSpeUserFlow())){
			criteria.andSpeUserFlowEqualTo(evaluationScore.getSpeUserFlow());
		}
		if (StringUtil.isNotBlank(evaluationScore.getSpeScore())){
			criteria.andSpeScoreIsNotNull();
		}
		if(StringUtil.isNotBlank(evaluationScore.getItemId())){
			criteria.andItemIdEqualTo(evaluationScore.getItemId());
		}
		return evaluationScoreMapper.selectByExample(example);
	}

	@Override
	public ResSupervisioSubjectUser selectSubjectUserByFlow(String userFlow, String subjectFlow) {
		ResSupervisioSubjectUserExample example = new ResSupervisioSubjectUserExample();
        ResSupervisioSubjectUserExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}
		if(StringUtil.isNotBlank(subjectFlow)){
			criteria.andSubjectFlowEqualTo(subjectFlow);
		}
		List<ResSupervisioSubjectUser> list = subjectUserMapper.selectByExample(example);
		if(null != list && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<ResSupervisioSubject> selectSubjectListByOrgFlow(String orgFlow, String currYear) {
		ResSupervisioSubjectExample example = new ResSupervisioSubjectExample();
        ResSupervisioSubjectExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(orgFlow)){
			criteria.andOrgFlowEqualTo(orgFlow);
		}
		if(StringUtil.isNotBlank(currYear)){
			criteria.andSubjectYearEqualTo(currYear);
		}
		return subjectMapper.selectByExample(example);
	}

	@Override
	public int insertSubject(ResSupervisioSubject subject) {
		GeneralMethod.setRecordInfo(subject, true);
		return subjectMapper.insert(subject);
	}

	@Override
	public List<ResSupervisioSubject> selectSubjectListBySpeId(String speId, String currYear) {
		ResSupervisioSubjectExample example = new ResSupervisioSubjectExample();
        ResSupervisioSubjectExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(StringUtil.isNotBlank(speId)){
			criteria.andSpeIdEqualTo(speId);
		}
		if(StringUtil.isNotBlank(currYear)){
			criteria.andSubjectYearEqualTo(currYear);
		}
		return subjectMapper.selectByExample(example);
	}

	@Override
	public List<Map<String,Object>> searchSpeAssignBySpeIdAndYear(String speId, String currYear) {
		return subjectExtMapper.searchSpeAssignBySpeIdAndYear(speId,currYear);
	}

	@Override
	public int updateSubject(ResSupervisioSubject subject) {
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(subject.getRecordStatus())) {
			GeneralMethod.setRecordInfo(subject, false);
			return subjectMapper.updateByPrimaryKeySelective(subject);
		}else{
			GeneralMethod.setRecordInfo(subject, false);
            subject.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
			return subjectMapper.updateByPrimaryKeySelective(subject);
		}
	}

	// 解析excel
	private static Workbook createUserWorkbook(InputStream inS) throws IOException, InvalidFormatException {
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

	// 读取数据
	private int parseExcelToUser(Workbook wb) {
		// 导入成功条数
		int succCount = 0;
		int sheetNum = wb.getNumberOfSheets();
		if (sheetNum > 0) {
			Sheet sheet = wb.getSheetAt(0);
			int row_num = sheet.getLastRowNum();//获取sheet行数，索引0开始
			if (row_num < 1) {
				throw new RuntimeException("没有数据！");
			}
			Row titleR = sheet.getRow(0);//获取表头
			int cell_num = titleR.getLastCellNum();//获取表头单元格数
			List<String> colnames = new ArrayList<>();
			for (int i = 0; i < cell_num; i++) {
				colnames.add(titleR.getCell(i).getStringCellValue());
			}
			List<Map<String, Object>> userList = new ArrayList<>();
			//数据行开始遍历
			for (int i = 1; i <= row_num; i++) {
				Row r = sheet.getRow(i);
				String value = "";
                String flag = com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED;
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < colnames.size(); j++) {
					Cell cell = r.getCell(j);
					if (null == cell || StringUtil.isBlank(cell.toString().trim())) {
						continue;
					}
					if (r.getCell((short) j).getCellType().getCode() == 1) {
						value = r.getCell((short) j).getStringCellValue();
					}else{
						value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
					}
					if ("专家名称*".equals(colnames.get(j))) {
						map.put("userName", value);
					} else if ("手机号码*".equals(colnames.get(j))) {
						map.put("userPhone", value);
					} else if ("专家分类*".equals(colnames.get(j))) {
						map.put("userLevelName", value);
					} else if ("专业*".equals(colnames.get(j))) {
						map.put("speName", value);
					}else if (("开户行".equals(colnames.get(j)))){
						map.put("bankOfDeposit",value);
					} else if (("银行卡账号".equals(colnames.get(j)))){
						map.put("bankAccountNumber",value);
					}
				}
				if (StringUtil.isBlank(String.valueOf(map.get("userName")))) {
					throw new RuntimeException("导入失败！第" + i + "行，专家名称为空！");
				}
				if (StringUtil.isBlank(String.valueOf(map.get("userPhone")))) {
					throw new RuntimeException("导入失败！第" + i + "行，手机号码为空！");
				}
				if (StringUtil.isBlank(String.valueOf(map.get("userLevelName")))) {
					throw new RuntimeException("导入失败！第" + i + "行，专家等级为空！");
				}
				if (StringUtil.isBlank(String.valueOf(map.get("speName")))) {
					throw new RuntimeException("导入失败！第" + i + "行，专业为空！");
				}

				// 查询专业专家是否存在
				SysUserExample example = new SysUserExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
						.andUserPhoneEqualTo((String)map.get("userPhone"));
				int count = sysUserMapper.countByExample(example);
				if(count > 0){
					throw new RuntimeException("导入失败！第" + i + "行，手机号码已存在！");
				}
				userList.add(map);
			}
			if(null != userList && 0 < userList.size()){
				SysUser user = new SysUser();
				for(Map<String, Object> map : userList){
					String userFlow = PkUtil.getUUID();
					user.setUserFlow(userFlow);
					user.setUserName((String)map.get("userName"));
					user.setUserCode((String)map.get("userPhone"));
					user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, "JSzp123456#"));
					user.setUserPhone((String)map.get("userPhone"));
					String speName = (String)map.get("speName");
					user.setResTrainingSpeName(speName);
                    user.setResTrainingSpeId(getDictId(speName, com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId()));
					String userLevelName = (String)map.get("userLevelName");
					user.setUserLevelName(userLevelName);
					if("管理专家".equals(userLevelName)){
						user.setUserLevelId("management");
					}else if("专业专家".equals(userLevelName)){
						user.setUserLevelId("expertLeader");
					}
					user.setStatusId(UserStatusEnum.Activated.getId());
					user.setStatusDesc(UserStatusEnum.Activated.getName());
					user.setBankOfDeposit((String)map.get("bankOfDeposit"));
					user.setBankAccountNumber((String)map.get("bankAccountNumber"));
					this.addSupervisioUser(user);
					succCount ++;
				}
			}
		}
		return succCount;
	}
	private int parseExcelToHospitalLeader(Workbook wb) {
		// 导入成功条数
		int succCount = 0;
		int sheetNum = wb.getNumberOfSheets();
		if (sheetNum > 0) {
			Sheet sheet = wb.getSheetAt(0);
			int row_num = sheet.getLastRowNum();//获取sheet行数，索引0开始
			if (row_num < 1) {
				throw new RuntimeException("没有数据！");
			}
			Row titleR = sheet.getRow(0);//获取表头
			int cell_num = titleR.getLastCellNum();//获取表头单元格数
			List<String> colnames = new ArrayList<>();
			for (int i = 0; i < cell_num; i++) {
				colnames.add(titleR.getCell(i).getStringCellValue());
			}
			List<Map<String, Object>> userList = new ArrayList<>();
			//数据行开始遍历
			for (int i = 1; i <= row_num; i++) {
				Row r = sheet.getRow(i);
				String value = "";
                String flag = com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED;
				Map<String, Object> map = new HashMap<>();
				for (int j = 0; j < colnames.size(); j++) {
					Cell cell = r.getCell(j);
					if (null == cell || StringUtil.isBlank(cell.toString().trim())) {
						continue;
					}
					if (r.getCell((short) j).getCellType().getCode() == 1) {
						value = r.getCell((short) j).getStringCellValue();
					}else{
						value = _doubleTrans(r.getCell((short) j).getNumericCellValue());
					}
					if ("专家名称*".equals(colnames.get(j))) {
						map.put("userName", value);
					} else if ("手机号码*".equals(colnames.get(j))) {
						map.put("userPhone", value);
					} else if ("专业*".equals(colnames.get(j))) {
						map.put("speName", value);
					}else if ("科室".equals(colnames.get(j))) {
						map.put("deptName", value);
					}
				}
				if (StringUtil.isBlank(String.valueOf(map.get("userName")))) {
					throw new RuntimeException("导入失败！第" + i + "行，专家名称为空！");
				}
				if (StringUtil.isBlank(String.valueOf(map.get("userPhone")))) {
					throw new RuntimeException("导入失败！第" + i + "行，手机号码为空！");
				}
				if (StringUtil.isBlank(String.valueOf(map.get("speName")))) {
					throw new RuntimeException("导入失败！第" + i + "行，专业为空！");
				}


				// 查询专业专家是否存在
				List<SysUser> userLists = findByUserPhoneForLeader("",(String)map.get("userPhone"));
				if (null != userLists && userLists.size() > 0) {
					for (SysUser sysUser : userLists) {
						if (StringUtil.isNotBlank(sysUser.getUserLevelName()) && StringUtil.isNotBlank(sysUser.getUserLevelId())) {
							if (sysUser.getUserLevelName().equals("评分专家") && sysUser.getUserLevelId().equals("hospitalLeader")) {
								throw new RuntimeException("导入失败！第" + i + "行，手机号码已存在！");
							}
						}
					}
				}
				userList.add(map);
			}
			List<SysDept> deptList = subjectExtMapper.selectDeptByOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			if(null != userList && 0 < userList.size()){
				SysUser user = new SysUser();
				for(Map<String, Object> map : userList){
					String userFlow = PkUtil.getUUID();
					user.setUserFlow(userFlow);
					user.setUserName((String)map.get("userName"));
					user.setUserCode((String)map.get("userPhone"));
					user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, "Njpd@2022!!!"));
					user.setUserPhone((String)map.get("userPhone"));
					String speName = (String)map.get("speName");
					String dept = (String)map.get("deptName");
					if (StringUtil.isNotBlank(dept)){
						for (SysDept sysDept : deptList) {
							if (sysDept.getDeptName().equals(dept)){
								user.setDeptName(dept);
								user.setDeptFlow(sysDept.getDeptFlow());
								break;
							}
						}
					}
					user.setResTrainingSpeName(speName);
                    user.setResTrainingSpeId(getDictId(speName, com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId()));
					user.setUserLevelName("评分专家");
					user.setUserLevelId("hospitalLeader");
					user.setStatusId(UserStatusEnum.Activated.getId());
					user.setStatusDesc(UserStatusEnum.Activated.getName());
					SysUser sysUser = GlobalContext.getCurrentUser();
					user.setOrgFlow(sysUser.getOrgFlow());
					user.setOrgName(sysUser.getOrgName());
					this.addSupervisioUser(user);
					succCount ++;
				}
			}
		}
		return succCount;
	}

	private static String _doubleTrans(double d){
		if((double)Math.round(d) - d == 0.0D){
			return String.valueOf((long)d);
		}else{
			return String.valueOf(d);
		}
	}

	private String getDictId(String dictName,String dictTypeId){
		Map<String,String> dictNameMap= InitConfig.getDictNameMap(dictTypeId);
		return dictNameMap.get(dictName);
	}
}
