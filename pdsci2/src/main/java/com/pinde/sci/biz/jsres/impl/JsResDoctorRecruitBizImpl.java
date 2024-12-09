package com.pinde.sci.biz.jsres.impl;


import com.alibaba.fastjson.JSON;
import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.common.enums.BaseStatusEnum;
import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.core.common.enums.sys.CertificateTypeEnum;
import com.pinde.core.model.SysDict;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.jsres.IResTestConfigBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.jsres.JsResDoctorRecruitExtMapper;
import com.pinde.sci.form.jsres.JykhInfoForm;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.jsres.JsDoctorInfoExt;
import com.pinde.sci.model.jsres.JsGraduateExt;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JsResDoctorRecruitBizImpl implements IJsResDoctorRecruitBiz{
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private ResDoctorRecruitMapper doctorRecruitMapper;
	@Autowired
	private JsResDoctorRecruitExtMapper jsResDoctorRecruitExtMapper;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IPubUserResumeBiz resumeBiz;
	@Autowired
	private IJsResPowerCfgBiz powerCfgBiz;
    @Autowired
    private ISchArrangeResultBiz schArrangeResultBiz;
    @Autowired
    private ISchRotationDeptBiz schRotationDeptBiz;
    @Autowired
    private IResDoctorProcessBiz resDoctorProcessBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private IResRecBiz resRecBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;
	@Autowired
	private SysDictMapper sysDictMapper;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private JsresSignMapper jsresSignMapper;
	@Autowired
	private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
	@Autowired
	private IResTestConfigBiz resTestConfigBiz;
	@Autowired
	private ResDoctorRecruitInfoMapper resDoctorRecruitInfoMapper;

	Logger logger = LoggerFactory.getLogger(JsResDoctorRecruitBizImpl.class);
	/**
	 * 根据recruitFlow获取相对应的一条记录
	 */
	@Override
	public ResDoctorRecruit readResDoctorRecruit(String recruitFlow) {
		if (StringUtil.isNotBlank(recruitFlow)) {
			return doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
		}
		return null;
	}
		
	/**
	 * 查询当前机构下医师
	 */
	@Override
	public List<JsResDoctorRecruitExt> resDoctorRecruitExtList(ResDoctorRecruit resDoctorRecruit,SysUser user,List<String>orgFlowList,List<String> docTypeList) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> resDoctorRecruitExtList1(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> orgFlowList, List<String> docTypeList, List<String> sessionNumbers) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> resDoctorRecruitExtNew(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> jointOrgList,
															  List<String> docTypeList, List<String> sessionNumbers,String joinOrgFlow,String isJointOrg,String isArmy) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		doctorRecruitMap.put("joinOrgFlow", joinOrgFlow);
		doctorRecruitMap.put("isJointOrg", isJointOrg);
		doctorRecruitMap.put("isArmy", isArmy);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> resDoctorRecruitExtNew2(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> jointOrgList,
															  List<String> docTypeList, List<String> sessionNumbers,String joinOrgFlow,String isJointOrg,String isArmy) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		doctorRecruitMap.put("joinOrgFlow", joinOrgFlow);
		doctorRecruitMap.put("isJointOrg", isJointOrg);
		doctorRecruitMap.put("isArmy", isArmy);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList4(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> resDoctorRecruitExtList2(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> orgFlowList, List<String> docTypeList, List<String> sessionNumbers, String sortType ,String scoreType) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		doctorRecruitMap.put("sortType", sortType);//排序方式
		doctorRecruitMap.put("scoreType", scoreType);//成绩类型
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList2(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> resDoctorRecruitExtList3(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> orgFlowList, List<String> docTypeList, List<String> sessionNumbers, String sortType ,String scoreType) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		doctorRecruitMap.put("sortType", sortType);//排序方式
		doctorRecruitMap.put("scoreType", scoreType);//成绩类型
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList3(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> resDoctorRecruitExtList3New(ResDoctorRecruit resDoctorRecruit) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList3New(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsDoctorInfoExt> resDoctorInfoExtList(ResDoctorRecruit resDoctorRecruit,SysUser user,List<String>orgFlowList,List<String> docTypeList,List<String> sessionNumbers) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		List<JsDoctorInfoExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList1(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt> searchTrainInfoList(List<String>jointOrgFlowList,ResDoctorRecruit resDoctorRecruit,SysUser user,String flag) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
		return doctorRecruitList;
	}
	/**
	 * 修改
	 */
	@Override
	public int saveDoctorRecruit(ResDoctorRecruitWithBLOBs docRecWithBLOBs) {
		if(StringUtil.isNotBlank(docRecWithBLOBs.getRecruitFlow())){
			GeneralMethod.setRecordInfo(docRecWithBLOBs, false);
			return doctorRecruitMapper.updateByPrimaryKeySelective(docRecWithBLOBs);
		}else{
			docRecWithBLOBs.setRecruitFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(docRecWithBLOBs, true);
			return doctorRecruitMapper.insert(docRecWithBLOBs);
		}
	}

	@Override
	public int queryCountByDoctFlow(String doctorFlow) {
		return jsResDoctorRecruitExtMapper.selectCountByDoctFlow(doctorFlow);
	}

	@Override
	public int resDoctorRecruitRefresh(ResDoctorRecruit recruit) {
		return doctorRecruitMapper.updateByPrimaryKey(recruit);
	}

	@Override
	public List<ResDoctorRecruit> searchResDoctorRecruitList(ResDoctorRecruit recruit, String orderByClause) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		com.pinde.sci.model.mo.ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			 criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if(StringUtil.isNotBlank(recruit.getCatSpeId())){
			criteria.andCatSpeIdEqualTo(recruit.getCatSpeId());
		}
		if(StringUtil.isNotBlank(recruit.getAuditStatusId())){
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		if(StringUtil.isNotBlank(recruit.getRecordStatus())){
			criteria.andRecordStatusEqualTo(recruit.getRecordStatus());
		}
		if(StringUtil.isNotBlank(orderByClause)){
			example.setOrderByClause(orderByClause);
		}
		if(StringUtil.isNotBlank(recruit.getRecruitFlow())){
			criteria.andRecruitFlowEqualTo(recruit.getRecruitFlow());
		}
		if(StringUtil.isNotBlank(recruit.getDoctorStatusId())){
			criteria.andDoctorStatusIdEqualTo(recruit.getDoctorStatusId());
		}
		if(StringUtil.isNotBlank(recruit.getRecruitYear())){
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
		return doctorRecruitMapper.selectByExample(example);
	}
	
	@Override 
	public int searchBasePassCount(ResDoctorRecruit recruit,List<String> orgFlowList,String year) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("orgFlowList", orgFlowList);
		paramMap.put("year",year);
		return jsResDoctorRecruitExtMapper.searchBasePassCount(paramMap);
	}

	@Override
	public int saveAuditResult(ResDoctorRecruitWithBLOBs recruitWithBLOBs) {

		if(StringUtil.isNotBlank(recruitWithBLOBs.getExamStatusId())){
			recruitWithBLOBs.setExamStatusName(BaseStatusEnum.getNameById(recruitWithBLOBs.getExamStatusId()));
		}
		if(StringUtil.isNotBlank(recruitWithBLOBs.getAuditionStatusId())){
			recruitWithBLOBs.setAuditionStatusName(BaseStatusEnum.getNameById(recruitWithBLOBs.getAuditionStatusId()));
		}
		if(StringUtil.isNotBlank(recruitWithBLOBs.getOperStatusId())){
			recruitWithBLOBs.setOperStatusName(BaseStatusEnum.getNameById(recruitWithBLOBs.getOperStatusId()));
		}
		int result = saveDoctorRecruit(recruitWithBLOBs);

		return result;
	}

	@Override
	public int modifyResult(ResDoctorRecruitWithBLOBs recruitWithBLOBs) {

		BigDecimal examResult = recruitWithBLOBs.getExamResult();
		BigDecimal auditionResult = recruitWithBLOBs.getAuditionResult();
		BigDecimal operResult = recruitWithBLOBs.getOperResult();
		BigDecimal totalResult = new BigDecimal(0);

		//计算总分=笔试分 + 面试分 + 操作技能分
		if(null != examResult){
			totalResult = totalResult.add(examResult);
		}
		if(null != auditionResult){
			totalResult = totalResult.add(auditionResult);
		}
		if(null != operResult){
			totalResult = totalResult.add(operResult);
		}
		recruitWithBLOBs.setTotleResult(totalResult);
		int result = saveDoctorRecruit(recruitWithBLOBs);

		return result;
	}

	@Override
	public int saveAuditRecruitInfo(ResDoctorRecruitWithBLOBs recruitWithBLOBs) {
		try {
			SysUser currUser = GlobalContext.getCurrentUser();
			//判断是否是协同基地
            String isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_N;
			List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
			if (!tempJoinOrgs.isEmpty() && tempJoinOrgs.size() > 0) {
                isJointOrg = com.pinde.core.common.GlobalConstant.FLAG_Y;
			}
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isJointOrg)) {//主基地审核后  省厅审核
				if (StringUtil.isNotBlank(recruitWithBLOBs.getDoctorStatusId())) {
					recruitWithBLOBs.setDoctorStatusName(BaseStatusEnum.getNameById(recruitWithBLOBs.getDoctorStatusId()));
                    recruitWithBLOBs.setAuditStatusId(com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getId());
                    recruitWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getName());
                    recruitWithBLOBs.setReviewFlag(com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getId());
				}
                if (com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId().equals(recruitWithBLOBs.getDoctorStatusId())) {
                    recruitWithBLOBs.setJointOrgAudit(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId());
                    recruitWithBLOBs.setOrgAudit(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId());
                    recruitWithBLOBs.setAuditStatusId(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId());
                    recruitWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getName());
                    recruitWithBLOBs.setReviewFlag(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId());
				}
			} else {
                if (com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getId().equals(recruitWithBLOBs.getDoctorStatusId())) {
					recruitWithBLOBs.setDoctorStatusId("OrgAuditing");//待主基地审核
					recruitWithBLOBs.setDoctorStatusName("待主基地审核");
					recruitWithBLOBs.setAuditStatusName("待主基地审核");
                    recruitWithBLOBs.setJointOrgAudit(com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getId());
                    recruitWithBLOBs.setReviewFlag(com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getId());
				}
                if (com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId().equals(recruitWithBLOBs.getDoctorStatusId())) {
                    recruitWithBLOBs.setJointOrgAudit(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId());
                    recruitWithBLOBs.setOrgAudit(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId());
                    recruitWithBLOBs.setAuditStatusId(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId());
                    recruitWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getName());
                    recruitWithBLOBs.setReviewFlag(com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId());
				}
			}
			ResDoctor resDoctor = resDoctorBiz.findByFlow(recruitWithBLOBs.getDoctorFlow());
			if("Passed".equals(recruitWithBLOBs.getDoctorStatusId())){
				resDoctor.setDoctorStatusName("报名信息审核通过");
			}else if("NotPassed".equals(recruitWithBLOBs.getDoctorStatusId())){
				resDoctor.setDoctorStatusName("报名信息审核不通过");
			}
			resDoctorBiz.editDoctor(resDoctor);
			String msgTitle = "报名信息审核结果";
			String msgContent = null;
			ResDoctorRecruit recruit = readResDoctorRecruit(recruitWithBLOBs.getRecruitFlow());
            if (com.pinde.core.common.enums.ResBaseStatusEnum.Passed.getId().equals(recruitWithBLOBs.getAuditStatusId())) {
				msgContent = "恭喜您已通过本院线上报名申请,请与"+ recruit.getOrgName() +"医院联系,参加后续考试。" + DateUtil.getCurrDateTime2();
            } else if (com.pinde.core.common.enums.ResBaseStatusEnum.NotPassed.getId().equals(recruitWithBLOBs.getAuditStatusId())) {
				msgContent = "很遗憾您未通过"+ recruit.getOrgName() +"线上报名申请，请重新报名！"+ DateUtil.getCurrDateTime2();
			}
			msgBiz.addSysMsg(recruitWithBLOBs.getDoctorFlow(), msgTitle , msgContent);
		} catch (Exception e) {
            logger.error("", e);
		}
		int result = saveDoctorRecruit(recruitWithBLOBs);
		return result;
	}

	@Override
	public int saveAuditRecruit(ResDoctorRecruitWithBLOBs recWithBLOBs){
		String auditStatusId =  recWithBLOBs.getAuditStatusId();
		if(StringUtil.isNotBlank(auditStatusId) && !"WaitGlobalPass".equals(auditStatusId)){
            recWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.getNameById(auditStatusId));
		}
		String msgTitle = "培训信息审核结果";
		String msgContent = null;
        if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId().equals(auditStatusId)) {
			//审核通过
			if(StringUtil.isNotBlank(recWithBLOBs.getAdmitNotice())){
				msgContent = "您的培训信息基地审核通过("+recWithBLOBs.getAdmitNotice()+")，";
			}else {
				msgContent = "您的培训信息基地审核通过，";
			}
			String recruitFlow = recWithBLOBs.getRecruitFlow();
			String doctorFlow = recWithBLOBs.getDoctorFlow();
			ResDoctorRecruit recruit = null;
			if(StringUtil.isNotBlank(recruitFlow) && StringUtil.isNotBlank(doctorFlow)){
				ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
				recruit = readResDoctorRecruit(recruitFlow);
				if(recruit != null && doctor != null){
                    if (!com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId()))
					{
						//自动更新学员app登录权限
						String key="jsres_doctor_app_login_"+doctorFlow;
						JsresPowerCfg cfg=powerCfgBiz.read(key);
						if(cfg==null)
							cfg=new JsresPowerCfg();
						cfg.setCfgCode(key);
                        cfg.setCfgValue(com.pinde.core.common.GlobalConstant.FLAG_Y);
						cfg.setCheckStatusId("Passed");
						cfg.setCheckStatusName("审核通过");
						cfg.setCfgDesc("是否开放学员app登录权限");
                        cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
						powerCfgBiz.save(cfg);
					}
					doctor.setOrgFlow(recruit.getOrgFlow());
					doctor.setOrgName(recruit.getOrgName());
					doctor.setTrainingTypeId(recruit.getCatSpeId());
					doctor.setTrainingTypeName(recruit.getCatSpeName());
					doctor.setTrainingSpeId(recruit.getSpeId());
					doctor.setTrainingSpeName(recruit.getSpeName());
					doctor.setDoctorStatusId("20");
					doctor.setDoctorStatusName("在培");
					doctor.setTrainingYears(recruit.getTrainYear());
					doctor.setDegreeCategoryId(recruit.getCurrDegreeCategoryId());
					doctor.setDegreeCategoryName(recruit.getCurrDegreeCategoryName());
					//回写届数
					doctor.setSessionNumber(recruit.getSessionNumber());
					resDoctorBiz.editDoctor(doctor, null);
					doctor=rotationBiz.updateDocRotation(doctor);
					resDoctorBiz.editDoctor(doctor, null);
					//每一条培训记录保存一个培训方案
//						recWithBLOBs.setRotationFlow(doctor.getRotationFlow());
//						recWithBLOBs.setRotationName(doctor.getRotationName());
					SysUser user=userBiz.readSysUser(doctorFlow);
					if(user!=null)
					{
						user.setOrgFlow(doctor.getOrgFlow());
						user.setOrgName(doctor.getOrgName());
						GeneralMethod.setRecordInfo(user,false);
						userBiz.edit(user);
					}

				}
			}
        } else if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getId().equals(auditStatusId)) {//审核不通过
			if(StringUtil.isNotBlank(recWithBLOBs.getAdmitNotice())){
				msgContent = "您的培训信息基地审核不通过("+recWithBLOBs.getAdmitNotice()+")，";
			}else {
				msgContent = "您的培训信息基地审核不通过，";
			}

		}

        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recWithBLOBs.getRecruitFlag())) {
			if(StringUtil.isNotBlank(recWithBLOBs.getAdmitNotice())){
				msgContent = "您的培训信息基地已录取("+recWithBLOBs.getAdmitNotice()+")，";
			}else {
				msgContent = "您的培训信息基地已录取，";
			}
        } else if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(recWithBLOBs.getRecruitFlag())) {
			if(StringUtil.isNotBlank(recWithBLOBs.getAdmitNotice())){
				msgContent = "您的培训信息基地未录取("+recWithBLOBs.getAdmitNotice()+")，";
			}else {
				msgContent = "您的培训信息基地未录取，";
			}
		}
		if (StringUtil.isNotBlank(auditStatusId) && auditStatusId.equals("NotPassed")){
            recWithBLOBs.setRecruitFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		int result = saveDoctorRecruit(recWithBLOBs);
        if (result == 1 && com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId().equals(auditStatusId))
		{
			ResDoctorRecruit resDoctorRecruit=readResDoctorRecruit(recWithBLOBs.getRecruitFlow());
			if(resDoctorRecruit!=null) {
				String sessionNumber=resDoctorRecruit.getSessionNumber();
				String doctorFlow=resDoctorRecruit.getDoctorFlow();
				JsresRecruitDocInfoWithBLOBs info2 = resDoctorBiz.getRecruitDocInfoBySessionNumber(doctorFlow, sessionNumber);
				JsresRecruitInfo recruitInfo2 = resDoctorBiz.getRecruitInfoBysessionNumber(doctorFlow, sessionNumber);

//			JsresRecruitDocInfoWithBLOBs info= resDoctorBiz.selectRecruitDocInfo(recWithBLOBs.getRecruitFlow());
//			JsresRecruitInfo recruitInfo = resDoctorBiz.selectRecruitInfo(recWithBLOBs.getRecruitFlow());
				if (info2 != null) {
					resDoctorBiz.delJsresRecruitDocInfo(info2.getRecruitFlow());
				}
				if (recruitInfo2 != null)
					resDoctorBiz.delJsresRecruitInfo(recruitInfo2.getRecordFlow());

				//审核通过就往jsres_recruit_doc_info 插入res_doctor_recruit 的数据
				//jsres_recruit_info插入res_doctor_recruit与res_doctor 结合的数据
				resDoctorBiz.insertRecruitDocInfo(recWithBLOBs.getRecruitFlow());
				resDoctorBiz.insertRecruitInfo(recWithBLOBs.getRecruitFlow());
			}
        } else if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getId().equals(auditStatusId)) {
            try {
                ResDoctorRecruit resDoctorRecruit = readResDoctorRecruit(recWithBLOBs.getRecruitFlow());
                String sessionNumber=resDoctorRecruit.getSessionNumber();
                JsresRecruitDocInfoWithBLOBs recruitDocInfo = resDoctorBiz.getRecruitDocInfoBySessionNumber(recWithBLOBs.getDoctorFlow(),sessionNumber);
                JsresRecruitInfo recruitInfo = resDoctorBiz.getRecruitInfoBysessionNumber(recWithBLOBs.getDoctorFlow(),sessionNumber);
                /**审核不通过需要删除jsres_recruit_doc_info、jsres_recruit_info原有的审核通过数据**/
                if(recruitDocInfo != null){
                    resDoctorBiz.delJsresRecruitDocInfo(recruitDocInfo.getRecruitFlow());
                }
                if(recruitInfo != null){
                    resDoctorBiz.delJsresRecruitInfo(recruitInfo.getRecordFlow());
                }
				//jsres_recruit_doc_info 插入res_doctor_recruit 的数据
				//jsres_recruit_info插入res_doctor_recruit与res_doctor 结合的数据
				recWithBLOBs.setDoctorStatusId("24");
				recWithBLOBs.setDoctorStatusName("终止");
				resDoctorBiz.insertRecruitDocInfoNotPass(recWithBLOBs.getRecruitFlow());
				resDoctorBiz.insertRecruitInfoNotPass(recWithBLOBs.getRecruitFlow());
				doctorRecruitMapper.updateByPrimaryKeySelective(recWithBLOBs);
            } catch (Exception e) {
                logger.error("", e);
            }
        }
		String modifyTime = DateUtil.transDateTime(recWithBLOBs.getModifyTime());
		String operUserFlow = recWithBLOBs.getModifyUserFlow();
		SysUser user = userBiz.readSysUser(operUserFlow);
		String orgName = user.getOrgName();
		msgContent += orgName + "，" +modifyTime;
		msgBiz.addSysMsg(recWithBLOBs.getDoctorFlow(), msgTitle , msgContent);
		return result;
	}
	
	
	@Override
	public List<JsDoctorInfoExt> searchDoctorInfoResume(ResDoctorRecruit recruit,ResDoctor doctor,SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList,String flag,List<String>docTypeList) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("resDoctorRecruit", recruit);
		paramMap.put("doctor", doctor);
		paramMap.put("user", user);
		paramMap.put("derateFlag", flag);
		paramMap.put("jointOrgFlowList", jointOrgFlowList);
		paramMap.put("sysOrg", sysOrg);
		paramMap.put("docTypeList", docTypeList);
		return jsResDoctorRecruitExtMapper.searchDoctorInfoExts(paramMap);
	}
	@Override
	public List<JsDoctorInfoExt> searchDoctorInfoResume1(ResDoctorRecruit recruit,ResDoctor doctor,SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList,String flag,List<String>docTypeList,List<String>trainYearList,List<String>sessionNumbers,String baseFlag) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("resDoctorRecruit", recruit);
		paramMap.put("doctor", doctor);
		paramMap.put("user", user);
		paramMap.put("derateFlag", flag);
		paramMap.put("jointOrgFlowList", jointOrgFlowList);
		paramMap.put("sysOrg", sysOrg);
		paramMap.put("docTypeList", docTypeList);
		paramMap.put("trainYearList", trainYearList);
		paramMap.put("sessionNumbers", sessionNumbers);

		List<JsDoctorInfoExt> JsDoctorInfoExtList = new ArrayList<JsDoctorInfoExt>(16);
		if(StringUtil.isNotBlank(baseFlag))
		{
			JsDoctorInfoExtList = jsResDoctorRecruitExtMapper.searchDoctorInfoBaseExts(paramMap);
		} else {
			JsDoctorInfoExtList = jsResDoctorRecruitExtMapper.searchDoctorInfoExts(paramMap);
		}
		return JsDoctorInfoExtList;

	}

	@Override
	public List<JsDoctorInfoExt> searchDoctorInfoResume2(ResDoctorRecruit recruit,ResDoctor doctor,SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList,String flag,List<String>docTypeList,List<String>trainYearList,List<String>sessionNumbers,String baseFlag) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("resDoctorRecruit", recruit);
		paramMap.put("doctor", doctor);
		paramMap.put("user", user);
		paramMap.put("derateFlag", flag);
		paramMap.put("jointOrgFlowList", jointOrgFlowList);
		paramMap.put("sysOrg", sysOrg);
		paramMap.put("docTypeList", docTypeList);
		paramMap.put("trainYearList", trainYearList);
		paramMap.put("sessionNumbers", sessionNumbers);
		//判断是否为协同基地
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            paramMap.put("isJointOrg", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}else{
            paramMap.put("isJointOrg", com.pinde.core.common.GlobalConstant.FLAG_N);
		}
		List<JsDoctorInfoExt> JsDoctorInfoExtList = new ArrayList<JsDoctorInfoExt>(16);
		if(StringUtil.isNotBlank(baseFlag))
		{
			JsDoctorInfoExtList = jsResDoctorRecruitExtMapper.searchDoctorInfoBaseExts(paramMap);
		} else {
			JsDoctorInfoExtList = jsResDoctorRecruitExtMapper.searchDoctorInfoExts2(paramMap);
		}
		return JsDoctorInfoExtList;

	}

	@Override
	public List<JsDoctorInfoExt> searchDoctorInfoResume3(ResDoctorRecruit recruit,ResDoctor doctor,SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList,String flag,List<String>docTypeList,List<String>trainYearList,List<String>sessionNumbers,String baseFlag,String isPostpone,String isArmy, String workOrgId,String workOrgName) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("resDoctorRecruit", recruit);
		paramMap.put("doctor", doctor);
		paramMap.put("user", user);
		paramMap.put("derateFlag", flag);
		paramMap.put("jointOrgFlowList", jointOrgFlowList);
		paramMap.put("sysOrg", sysOrg);
		paramMap.put("docTypeList", docTypeList);
		paramMap.put("trainYearList", trainYearList);
		paramMap.put("sessionNumbers", sessionNumbers);
		paramMap.put("isPostpone", isPostpone);
		paramMap.put("isArmy", isArmy);
		paramMap.put("workOrgId", workOrgId);
		paramMap.put("workOrgName", workOrgName);

		//判断是否为协同基地
		List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){//是协同基地
            paramMap.put("isJointOrg", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}else{
            paramMap.put("isJointOrg", com.pinde.core.common.GlobalConstant.FLAG_N);
		}
		List<JsDoctorInfoExt> JsDoctorInfoExtList = new ArrayList<JsDoctorInfoExt>(16);
		if(StringUtil.isNotBlank(baseFlag))
		{
			JsDoctorInfoExtList = jsResDoctorRecruitExtMapper.searchDoctorInfoBaseExts(paramMap);
		} else {
			JsDoctorInfoExtList = jsResDoctorRecruitExtMapper.searchDoctorInfoExts2(paramMap);
		}
		return JsDoctorInfoExtList;

	}

	public int searchDoctorNum(ResDoctorRecruit recruit){
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
		}
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if (StringUtil.isNotBlank(recruit.getSpeId())) {
			criteria.andSpeIdEqualTo(recruit.getSpeId());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
//		if (recruit.getOrdinal() != null) {
//			criteria.andOrdinalEqualTo(recruit.getOrdinal());
//		}
		if(StringUtil.isNotBlank(recruit.getRecruitTypeId())){
			criteria.andRecruitTypeIdEqualTo(recruit.getRecruitTypeId());
		}
		if (StringUtil.isNotBlank(recruit.getRetestFlag())) {
			criteria.andRetestFlagEqualTo(recruit.getRetestFlag());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
			criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
		}
		if (StringUtil.isNotBlank(recruit.getConfirmFlag())) {
			criteria.andConfirmFlagEqualTo(recruit.getConfirmFlag());
		}
		if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		return doctorRecruitMapper.countByExample(example);
	}
	
	@Override
	public int searchCountByCondition(ResDoctorRecruitWithBLOBs recruitWithBLOBs,String flag){
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("recruit", recruitWithBLOBs);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
			paramMap.put("ProvFlag", flag);
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(flag)) {
			paramMap.put("UrlFlag", flag);
		}
		return jsResDoctorRecruitExtMapper.searchTrainInfoCount(paramMap);
	}
	
	@Override
	public int editResDoctorRecruit(ResDoctorRecruitWithBLOBs recWithBLOBs, ResDoctorRecruitWithBLOBs prevDocRec) {
		//医师状态
		if(StringUtil.isNotBlank(recWithBLOBs.getDoctorStatusId())){
            recWithBLOBs.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById(recWithBLOBs.getDoctorStatusId()));
		}else{
			recWithBLOBs.setDoctorStatusName("");
		}
		//医师走向
		if(StringUtil.isNotBlank(recWithBLOBs.getDoctorStrikeId())){
            recWithBLOBs.setDoctorStrikeName(com.pinde.core.common.enums.DictTypeEnum.DoctorStrike.getDictNameById(recWithBLOBs.getDoctorStrikeId()));
		}else{
			recWithBLOBs.setDoctorStrikeName("");
		}
		if(StringUtil.isNotBlank(recWithBLOBs.getCurrDegreeCategoryId())){
            recWithBLOBs.setCurrDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(recWithBLOBs.getCurrDegreeCategoryId()));
		}
        recWithBLOBs.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId());
        recWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getName());
		String doctorFlow = recWithBLOBs.getDoctorFlow();
		//结业审核年份
		int year=0;
		if(StringUtil.isNotBlank(recWithBLOBs.getSessionNumber())&&StringUtil.isNotBlank(recWithBLOBs.getTrainYear())){
			int num=0;
            if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(recWithBLOBs.getTrainYear())) {
				num=1;
			}
            if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(recWithBLOBs.getTrainYear())) {
				num=2;
			}
            if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(recWithBLOBs.getTrainYear())) {
				num=3;
			}
			year = Integer.parseInt(recWithBLOBs.getSessionNumber())+num;
			recWithBLOBs.setGraduationYear(year+"");
		}
		//其中已审核通过
		ResDoctorRecruit exitRec = new ResDoctorRecruit();
		exitRec.setDoctorFlow(doctorFlow);
        exitRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        exitRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);

		//页面字段被去除   默认
		exitRec.setDoctorStatusId("Auditing");
		exitRec.setDoctorStatusName("待审核");

		List<ResDoctorRecruit> passedRecruitList = searchResDoctorRecruitList(exitRec, "CREATE_TIME");
		boolean firstIsWMSecond = false;//首条是否为二阶段(有自动生成一阶段)
		if(passedRecruitList != null && !passedRecruitList.isEmpty()){
			for(ResDoctorRecruit  rec : passedRecruitList){
				if(StringUtil.isBlank(rec.getSpeId())){
					firstIsWMSecond = true;
					break;
				}
			}
		}
		//当前为二阶段
        if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(recWithBLOBs.getCatSpeId())) {
			if(passedRecruitList.isEmpty()){//无审核通过
//				ResDoctorRecruitWithBLOBs addWMFirst = new ResDoctorRecruitWithBLOBs();
//				addWMFirst.setCompleteFileUrl(recWithBLOBs.getCompleteFileUrl());
//				addWMFirst.setCompleteCertNo(recWithBLOBs.getCompleteCertNo());
//				//查询状态为N的记录
//				ResDoctorRecruit exitN = new ResDoctorRecruit();
//				exitN.setDoctorFlow(doctorFlow);
//				exitN.setCatSpeId(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId());
//				exitN.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
//				List<ResDoctorRecruit> exitNList = searchResDoctorRecruitList(exitN, null);
//				if(exitNList != null && !exitNList.isEmpty()){
//					exitN = exitNList.get(0);
//					addWMFirst.setRecruitFlow(exitN.getRecruitFlow());
//					addWMFirst.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//					saveDoctorRecruit(addWMFirst);
//				}else{//新增
//					addWMFirst.setDoctorFlow(doctorFlow);
//					addWMFirst.setCatSpeId(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId());
//					addWMFirst.setCatSpeName(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getName());
//					addWMFirst.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
//					addWMFirst.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getName());
//					//saveDoctorRecruit(addWMFirst);
//					addWMFirst.setRecruitFlow(PkUtil.getUUID());
//					GeneralMethod.setRecordInfo(addWMFirst, true);
//					String createTime = addWMFirst.getCreateTime();
//					if(StringUtil.isNotBlank(recWithBLOBs.getCreateTime())){//当前为首条记录 (修改创建时间解决排序：生成一阶段置前)
//						createTime = recWithBLOBs.getCreateTime();
//					}
//					long createTimeVal = Long.valueOf(createTime) - 1;
//					addWMFirst.setCreateTime(String.valueOf(createTimeVal));
//					doctorRecruitMapper.insert(addWMFirst);
//					prevDocRec.setRecruitFlow(addWMFirst.getRecruitFlow());
//				}
			}
		}else{//非二阶段
			if(passedRecruitList.size() == 1 && firstIsWMSecond){//首条记录为二阶段  修改为非二阶段  ==>删除自动生成的一阶段
				ResDoctorRecruitWithBLOBs deleWMFirst = new ResDoctorRecruitWithBLOBs();
				deleWMFirst.setRecruitFlow(passedRecruitList.get(0).getRecruitFlow());
                deleWMFirst.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				saveDoctorRecruit(deleWMFirst);
			}
		}
		//上一阶段：结业证书、证书编号
		if(StringUtil.isNotBlank(prevDocRec.getRecruitFlow())){
			saveDoctorRecruit(prevDocRec);
		}
		return saveDoctorRecruit(recWithBLOBs);
	}
	
	@Override
	public int updateDoctorTrend(ResDoctorRecruitWithBLOBs recruitWithBLOBs) {
		String doctorFlow = recruitWithBLOBs.getDoctorFlow();
		if(StringUtil.isNotBlank(doctorFlow)){
			//ResDoctor医师状态
			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			doctor.setDoctorStatusId(recruitWithBLOBs.getDoctorStatusId());
			doctor.setDoctorStatusName(recruitWithBLOBs.getDoctorStatusName());
			if(StringUtil.isNotBlank(recruitWithBLOBs.getTrainYear())){
				doctor.setTrainingYears(recruitWithBLOBs.getTrainYear());
			}
			if(StringUtil.isNotBlank(recruitWithBLOBs.getSessionNumber())&&StringUtil.isNotBlank(recruitWithBLOBs.getTrainYear())){
				doctor.setSessionNumber(recruitWithBLOBs.getSessionNumber());
				int year =0;
                if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(recruitWithBLOBs.getTrainYear())) {
					year=1;
				}
                if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(recruitWithBLOBs.getTrainYear())) {
					year=2;
				}
                if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(recruitWithBLOBs.getTrainYear())) {
					year=3;
				}
				int newGraduationYear=(Integer.parseInt(recruitWithBLOBs.getSessionNumber())+year);
				ResDoctorRecruit recruit=doctorRecruitMapper.selectByPrimaryKey(recruitWithBLOBs.getRecruitFlow());
				//获取最新延期信息
				int oldGraduationYear= Integer.parseInt(DateUtil.getYear());
				if(recruit != null){
                    try {
                        oldGraduationYear = Integer.parseInt(recruit.getGraduationYear());
                    } catch (NumberFormatException e) {
                        logger.error("", e);
                    }
                }
				ResDocotrDelayTeturn ret=resDoctorDelayTeturnBiz.findDelayInfo(recruitWithBLOBs.getRecruitFlow());
				if(ret!=null&&StringUtil.isNotBlank(ret.getGraduationYear()))
				{
					oldGraduationYear=Integer.parseInt(ret.getGraduationYear());
				}
				if(oldGraduationYear<newGraduationYear)
					recruitWithBLOBs.setGraduationYear(newGraduationYear+"");
				else
					recruitWithBLOBs.setGraduationYear(oldGraduationYear+"");
			}
			if(StringUtil.isNotBlank(recruitWithBLOBs.getCurrDegreeCategoryId())){
                recruitWithBLOBs.setCurrDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(recruitWithBLOBs.getCurrDegreeCategoryId()));
			}
			saveDoctorRecruit(recruitWithBLOBs);
			return resDoctorBiz.editDoctor(doctor, null);
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	@Override
	public ResDoctorRecruitWithBLOBs readRecruit(String recruitFlow) {
		if (StringUtil.isNotBlank(recruitFlow)) {
			return doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
		}
		return null;
	}

	@Override
	public List<JsResDoctorRecruitExt> searchDoctorInfoExts(ResDoctorRecruit resDoctorRecruit,ResDoctor doctor,SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList,String flag,List<String>docTypeList) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt> searchDoctorInfoExts1(ResDoctorRecruit resDoctorRecruit,ResDoctor doctor,SysUser user, SysOrg sysOrg,
															 List<String> jointOrgFlowList,String flag,List<String>docTypeList,List<String>trainYearList,
															 List<String>sessionNumbers,String baseFlag,String userOrgFlow, String newFlag,String isJointOrg) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(newFlag)) {
			if (sessionNumbers == null || sessionNumbers.size() == 0) {
				String sessionNumberBefore = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 6);
				doctorRecruitMap.put("sessionNumberBefore", sessionNumberBefore);
			}
		}
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("trainYearList", trainYearList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		doctorRecruitMap.put("isJointOrg", isJointOrg);
		List<JsResDoctorRecruitExt> doctorRecruitList = new ArrayList<JsResDoctorRecruitExt>(16);
		if(StringUtil.isNotBlank(baseFlag))
		{
			if(StringUtil.isNotBlank(userOrgFlow)){
				doctorRecruitMap.put("userOrgFlow", userOrgFlow);
				doctorRecruitList = jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtBaseList(doctorRecruitMap);
			} else {
				return doctorRecruitList;
			}

		} else {
			doctorRecruitList = jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
		}

		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> searchDoctorInfoExts2(ResDoctorRecruit resDoctorRecruit,ResDoctor doctor,SysUser user, SysOrg sysOrg,
															 List<String> jointOrgFlowList,String flag,List<String>docTypeList,List<String>trainYearList,
															 List<String>sessionNumbers,String baseFlag,String userOrgFlow, String newFlag,String isJointOrg,String isPostpone,String isArmy,String workOrgId, String workOrgName) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(newFlag)) {
			if (sessionNumbers == null || sessionNumbers.size() == 0) {
				String sessionNumberBefore = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 6);
				doctorRecruitMap.put("sessionNumberBefore", sessionNumberBefore);
			}
		}
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("isPostpone", isPostpone);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("trainYearList", trainYearList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		doctorRecruitMap.put("isJointOrg", isJointOrg);
		doctorRecruitMap.put("isArmy", isArmy);
		doctorRecruitMap.put("workOrgId", workOrgId);
		doctorRecruitMap.put("workOrgName", workOrgName);
		List<JsResDoctorRecruitExt> doctorRecruitList = new ArrayList<JsResDoctorRecruitExt>(16);
		if(StringUtil.isNotBlank(baseFlag))
		{
			if(StringUtil.isNotBlank(userOrgFlow)){
				doctorRecruitMap.put("userOrgFlow", userOrgFlow);
				doctorRecruitList = jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtBaseList(doctorRecruitMap);
			} else {
				return doctorRecruitList;
			}

		} else {
			doctorRecruitList = jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
		}

		return doctorRecruitList;
	}

	@Override
	public List<String> searchDoctorNum(List<String> doctorTypeId, String doctorStatusId,String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchDoctorNum(doctorTypeId,doctorStatusId,catSpeId);
	}

	@Override
	public List<String> searchDoctorNum2(List<String> doctorTypeId,String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchDoctorNum2(doctorTypeId,catSpeId);
	}

	@Override
	public String searchDoctorNumYear(String doctorStatusId,String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchDoctorNumYear(doctorStatusId,catSpeId);
	}

	@Override
	public String searchDoctorNumYear2(String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchDoctorNumYear2(catSpeId);
	}

	@Override
	public List<Map<String, Object>> searchDoctorTrainingNum(String sessionNumber,String statisticsType,String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchDoctorTrainingNum(sessionNumber,statisticsType,catSpeId);
	}

	@Override
	public List<Map<String, Object>> searchDoctorTrainingNumWithNotJoin(String sessionNumber, String statisticsType,String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchDoctorTrainingNumWithNotJoin(sessionNumber,statisticsType,catSpeId);
	}

	@Override
	public List<Map<String, Object>> searchSpeDoctorTrainingNum(String sessionNumber,String statisticsType,String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchSpeDoctorTrainingNum(sessionNumber,statisticsType,catSpeId);
	}

	@Override
	public List<Map<String, Object>> searchDoctorRecruitNum(String sessionNumber,String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchDoctorRecruitNum(sessionNumber,catSpeId);
	}

	@Override
	public List<Map<String, Object>> searchDoctorRecruitNumWithNotJoin(String sessionNumber,String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchDoctorRecruitNumWithNotJoin(sessionNumber,catSpeId);
	}

	@Override
	public List<Map<String, Object>> searchSpeDoctorRecruitNum(String sessionNumber,String catSpeId) {
		return jsResDoctorRecruitExtMapper.searchSpeDoctorRecruitNum(sessionNumber,catSpeId);
	}

	@Override
	public List<JsResDoctorRecruitExt> searchDoctorCertificateList(Map<String, Object> doctorRecruitMap) {
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchDoctorCertificateList(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt> searchDoctorCertificateList2(Map<String, Object> doctorRecruitMap) {
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchDoctorCertificateList2(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt>  searchDoctorScoreInfoExts(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList,String hegeScore,String testId)
	{
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("scoreYear",scoreYear);
		doctorRecruitMap.put("isHege",isHege);
		doctorRecruitMap.put("testId",testId);
        doctorRecruitMap.put("typeId", com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId());
//		doctorRecruitMap.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));

		doctorRecruitMap.put("hegeScore",hegeScore);

		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt>  searchDoctorSkillAndTheoryScoreExts( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  user,SysOrg  sysOrg,List<String>  jointOrgFlowList,  String flag , String  scoreYear, String  isHege,String  skillIsHege,List<String> docTypeList,String hegeScore)
	{
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("scoreYear",scoreYear);
		doctorRecruitMap.put("isHege",isHege);


//		doctorRecruitMap.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));
		doctorRecruitMap.put("hegeScore", hegeScore);


		if(StringUtil.isNotBlank(skillIsHege))
		{
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(skillIsHege))
			{
				skillIsHege="1";
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(skillIsHege))
			{
				skillIsHege="0";
			}
		}
		doctorRecruitMap.put("skillIsHege",skillIsHege);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchDoctorSkillAndTheoryScoreExts(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt>  searchDoctorSkillAndTheoryScoreExts1( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  user,SysOrg  sysOrg,List<String>  jointOrgFlowList,  String flag , String  theroyScoreYear, String  skillScoreYear, String  isHege,String  skillIsHege,List<String> docTypeList,String hegeScore
			,String testId,String isMakeUp,String trainingTypeId,String scoreTestId,String roleFlag) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("trainingTypeId", trainingTypeId);
		doctorRecruitMap.put("testId", testId);
		doctorRecruitMap.put("scoreTestId", scoreTestId);
		doctorRecruitMap.put("isMakeUp", isMakeUp);
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("theroyScoreYear",theroyScoreYear);
		doctorRecruitMap.put("skillScoreYear",skillScoreYear);
		doctorRecruitMap.put("isHege",isHege);
		doctorRecruitMap.put("roleFlag",roleFlag);

//		doctorRecruitMap.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));
		doctorRecruitMap.put("hegeScore", hegeScore);


		if(StringUtil.isNotBlank(skillIsHege))
		{
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(skillIsHege))
			{
				skillIsHege="1";
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(skillIsHege))
			{
				skillIsHege="0";
			}
		}
		doctorRecruitMap.put("skillIsHege",skillIsHege);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchDoctorSkillAndTheoryScoreExts1(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt> searchDoctorSkillScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList,String testId)
	{
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("testId", testId);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("scoreYear",scoreYear);
		doctorRecruitMap.put("isHege",isHege);
        doctorRecruitMap.put("typeId", com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId());
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorSkillScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
	}
	//理论成绩查询
	@Override
	public List<JsResDoctorRecruitExt> searchDoctorTheoryScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList, String testId) {
		Map<String, Object> doctorRecruitMap = new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("testId", testId);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("scoreYear", scoreYear);
		doctorRecruitMap.put("isHege", isHege);
        doctorRecruitMap.put("typeId", com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId());
		List<JsResDoctorRecruitExt> doctorRecruitList = jsResDoctorRecruitExtMapper.searchJsDoctorTheoryScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> searchDoctorPublicScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String notAllQualified,String allQualified, List<String> docTypeList)
	{
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("scoreYear",scoreYear);
		if(StringUtil.isNotBlank(notAllQualified))
		{
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(notAllQualified))
			{
				notAllQualified="1";
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(notAllQualified))
			{
				notAllQualified="0";
			}
		}
		doctorRecruitMap.put("notAllQualified",notAllQualified);//非全科是否合格
		doctorRecruitMap.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));
		/*//根据年份从res_pass_score_cfg取数据
		ResPassScoreCfg cfg = new ResPassScoreCfg();
		cfg.setCfgYear(scoreYear);
		ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
		String hegeScore = resPassScoreCfg.getCfgPassScore();
		if(StringUtil.isBlank(hegeScore)){
			hegeScore="60";
		}
		doctorRecruitMap.put("hegeScore",hegeScore);*/
		doctorRecruitMap.put("allQualified",allQualified);//全科是否合格
        doctorRecruitMap.put("typeId", com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId());
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorPublicScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt> searchDoctorPublicScore1(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String notAllQualified,String allQualified, List<String> docTypeList, List<String> sessionNumbers)
	{
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("scoreYear",scoreYear);
		doctorRecruitMap.put("sessionNumbers",sessionNumbers);
		if(StringUtil.isNotBlank(notAllQualified))
		{
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(notAllQualified))
			{
				notAllQualified="1";
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(notAllQualified))
			{
				notAllQualified="0";
			}
		}
		doctorRecruitMap.put("notAllQualified",notAllQualified);//非全科是否合格
		doctorRecruitMap.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));
		/*//根据年份从res_pass_score_cfg取数据
		ResPassScoreCfg cfg = new ResPassScoreCfg();
		cfg.setCfgYear(scoreYear);
		ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(cfg);
		String hegeScore = resPassScoreCfg.getCfgPassScore();
		if(StringUtil.isBlank(hegeScore)){
			hegeScore="60";
		}
		doctorRecruitMap.put("hegeScore",hegeScore);*/
		doctorRecruitMap.put("allQualified",allQualified);//全科是否合格
        doctorRecruitMap.put("typeId", com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId());
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorPublicScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
	}
//	@Override
//	public List<ResDoctorRecruit> searchRecruitByDoc(String doctorFlow,String orgFlow){
//		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
//		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//				.andDoctorFlowEqualTo(doctorFlow).andOrgFlowEqualTo(orgFlow);
//		return doctorRecruitMapper.selectByExample(example);
//	}

	@Override
	public List<ResDoctorRecruit> readDoctorRecruits(ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
		}
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if (StringUtil.isNotBlank(recruit.getSpeId())) {
			criteria.andSpeIdEqualTo(recruit.getSpeId());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
		if(StringUtil.isNotBlank(recruit.getRecruitTypeId())){
			criteria.andRecruitTypeIdEqualTo(recruit.getRecruitTypeId());
		}
		if (StringUtil.isNotBlank(recruit.getRetestFlag())) {
			criteria.andRetestFlagEqualTo(recruit.getRetestFlag());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
			criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
		}
		if (StringUtil.isNotBlank(recruit.getConfirmFlag())) {
			criteria.andConfirmFlagEqualTo(recruit.getConfirmFlag());
		}
		if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
			criteria.andAuditStatusIdIn(Arrays.asList(recruit.getAuditStatusId().split(",")));
		}
		if (StringUtil.isNotBlank(recruit.getSessionNumber())) {
			criteria.andSessionNumberEqualTo(recruit.getSessionNumber());
		}
		if (StringUtil.isNotBlank(recruit.getGraduationYear())) {
			criteria.andGraduationYearEqualTo(recruit.getGraduationYear());
		}
		return doctorRecruitMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctorRecruitWithBLOBs> readDoctorRecruitBlobs(ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(recruit.getOrgFlow())) {
			criteria.andOrgFlowEqualTo(recruit.getOrgFlow());
		}
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if (StringUtil.isNotBlank(recruit.getSpeId())) {
			criteria.andSpeIdEqualTo(recruit.getSpeId());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
		if(StringUtil.isNotBlank(recruit.getRecruitTypeId())){
			criteria.andRecruitTypeIdEqualTo(recruit.getRecruitTypeId());
		}
		if (StringUtil.isNotBlank(recruit.getRetestFlag())) {
			criteria.andRetestFlagEqualTo(recruit.getRetestFlag());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitFlag())) {
			criteria.andRecruitFlagEqualTo(recruit.getRecruitFlag());
		}
		if (StringUtil.isNotBlank(recruit.getConfirmFlag())) {
			criteria.andConfirmFlagEqualTo(recruit.getConfirmFlag());
		}
		if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		if (StringUtil.isNotBlank(recruit.getSessionNumber())) {
			criteria.andSessionNumberEqualTo(recruit.getSessionNumber());
		}
		if (StringUtil.isNotBlank(recruit.getGraduationYear())) {
			criteria.andGraduationYearEqualTo(recruit.getGraduationYear());
		}
		if (StringUtil.isNotBlank(recruit.getGraduationCertificateNo())) {
			criteria.andGraduationCertificateNoEqualTo(recruit.getGraduationCertificateNo());
		}
		return doctorRecruitMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResDoctorRecruitWithBLOBs> searchRecruitWithBLOBs(ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow()); 
		}  
		if(StringUtil.isNotBlank(recruit.getAuditStatusId())){
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		example.setOrderByClause("create_time desc");
		return doctorRecruitMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int updateRecruit(ResDoctorRecruitWithBLOBs resDoctorRecruitWithBLOBs) {
		int result=0;
		if(StringUtil.isNotBlank(resDoctorRecruitWithBLOBs.getRecruitFlow())){
			result=doctorRecruitMapper.updateByPrimaryKeySelective(resDoctorRecruitWithBLOBs);
		}
		return result;
	}
	
	@Override
	public boolean getRecruitStatus(String doctorFlow){
		if(!StringUtil.isNotBlank(doctorFlow)){
			return false;
		}
		
		ResDoctorRecruitExample recruitExample = new ResDoctorRecruitExample();
        recruitExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
		.andDoctorFlowEqualTo(doctorFlow);
		recruitExample.setOrderByClause("CREATE_TIME DESC");
		
		List<ResDoctorRecruit> recruits = doctorRecruitMapper.selectByExample(recruitExample);
		if(recruits==null || recruits.isEmpty()){
			return false;
		}
		
		ResDoctorRecruit recruit = recruits.get(0);
		
		if(recruit==null){
			return false;
		}
		
		return BaseStatusEnum.Passed.getId().equals(recruit.getAuditStatusId());
	}

//	@Override
//	public List<JsDoctorInfoExt> searchDoctorInfo(ResDoctorRecruit recruit, ResDoctor doctor, SysUser user,SysOrg sysOrg, List<String> jointOrgFlowList, String flag,List<String> docTypeList) {
//		Map<String,Object> paramMap=new HashMap<String,Object>();
//		paramMap.put("resDoctorRecruit", recruit);
//		paramMap.put("doctor", doctor);
//		paramMap.put("user", user);
//		paramMap.put("derateFlag", flag);
//		paramMap.put("jointOrgFlowList", jointOrgFlowList);
//		paramMap.put("sysOrg", sysOrg);
//		paramMap.put("docTypeList", docTypeList);
//		return jsResDoctorRecruitExtMapper.searchDoctorInfo(paramMap);
//	}

	@Override
	public List<Map<String, Object>> searchJointOrgList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return jsResDoctorRecruitExtMapper.searchJointOrgList(paramMap);
	}

	@Override
	public List<Map<String, Object>> doctorScoreQuery(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, List<String> docTypeList, List<String> cerStatusList) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		doctorRecruitMap.put("sysOrg", sysOrg);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("cerStatusList", cerStatusList);
		List<Map<String, Object>> doctorRecruitList=jsResDoctorRecruitExtMapper.doctorScoreQuery(doctorRecruitMap);
		return doctorRecruitList;
	}

	/****************************高******校******管******理******员******角******色************************************************/

	@Override
	public List<JsResDoctorRecruitExt> searchDoctorInfoExtsForUni(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser sysUser, SysOrg org, String derateFlag, List<String> docTypeList,SysUser currUser) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("doctor", doctor);
		doctorRecruitMap.put("user", sysUser);
		doctorRecruitMap.put("derateFlag", derateFlag);
		doctorRecruitMap.put("sysOrg", org);
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("currUser", currUser);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtListForUni(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<Map<String, Object>> doctorScoreQueryForUni(Map<String, Object> param) {
		List<Map<String, Object>> doctorRecruitList=jsResDoctorRecruitExtMapper.doctorScoreQueryForUni(param);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt> searchDoctorSkillScoreForUni(Map<String, Object> param)
	{

//		param.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchDoctorSkillScoreForUni(param);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt> searchDoctorSkillScoreForUni1(Map<String, Object> param)
	{
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchDoctorSkillScoreForUni1(param);
		return doctorRecruitList;
	}

	@Override
	public List<JsDoctorInfoExt> searchDoctorInfoResumeForUni(ResDoctorRecruit recruit, ResDoctor doctor, SysUser sysUser, SysOrg org, String derateFlag, List<String> docTypeList, SysUser exSysUser) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("doctor", doctor);
		paramMap.put("user", sysUser);
		paramMap.put("derateFlag", derateFlag);
		paramMap.put("sysOrg", org);
		paramMap.put("resDoctorRecruit", recruit);
		paramMap.put("docTypeList", docTypeList);
		paramMap.put("currUser", exSysUser);
		return jsResDoctorRecruitExtMapper.searchDoctorInfoExtsForUni(paramMap);
	}

	@Override
	public List<Map<String, String>> searchOrgNotUseAppDoc(Map<String, Object> param) {
		return jsResDoctorRecruitExtMapper.searchOrgNotUseAppDoc(param);
	}

	@Override
	public List<Map<String, String>> findSchArrengResultByDoctorAndYearMonth(Map<String, Object> param) {
		return jsResDoctorRecruitExtMapper.findSchArrengResultByDoctorAndYearMonth(param);
	}

	@Override
	public List<JsResDoctorRecruitExt> searchDoctorManualForUni(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, List<String> docTypeList,SysUser currUser) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("resDoctorRecruit", resDoctorRecruit);
		paramMap.put("doctor", doctor);
		paramMap.put("docTypeList", docTypeList);
		paramMap.put("currUser", currUser);
		return jsResDoctorRecruitExtMapper.searchDoctorManualForUni(paramMap);
	}

	@Override
	public Map<String, Object> getDocProcessArea(String doctorFlow, String rotationFlow) {
		return jsResDoctorRecruitExtMapper.getDocProcessArea(doctorFlow,rotationFlow);
	}

	@Override
	public List<JykhInfoForm> graduateExtList(Map<String, Object> parMap) throws Exception{
		List<JykhInfoForm> jykhFormList = new ArrayList<>();
		List<JsGraduateExt> dataList = jsResDoctorRecruitExtMapper.graduateExtList(parMap);
		if(null!= dataList && dataList.size() > 0){
			for(JsGraduateExt ext : dataList){
				JykhInfoForm jykhForm = new JykhInfoForm();
				jykhForm.setProvince("江苏");
				jykhForm.setUserName(ext.getUserName());
				jykhForm.setSexName(ext.getSexName());
				jykhForm.setIdCardType(ext.getCretTypeName());
				jykhForm.setIdCard(ext.getIdNo());
				jykhForm.setNationName(ext.getNationName());
				jykhForm.setBirthDate(ext.getUserBirthday());
				jykhForm.setPhoneNo(ext.getUserPhone());
				jykhForm.setEmailNo(ext.getUserEmail());
				String resumeContent = ext.getUserResume();
				if(StringUtil.isNotBlank(resumeContent)){
					UserResumeExtInfoForm resume = resumeBiz.converyToJavaBean(resumeContent,UserResumeExtInfoForm.class);
					String qmId = resume.getQualificationMaterialId();
					//是否通过医师资格证
					if("176".equals(qmId) || "177".equals(qmId) || "178".equals(qmId) || "200".equals(qmId) || "201".equals(qmId)){
						jykhForm.setIsYszgTest("是");
					}else if("202".equals(qmId)){
						jykhForm.setIsYszgTest("否");
					}
					//是否获得医师资格证书
					if("176".equals(qmId) || "177".equals(qmId) || "200".equals(qmId)){
						jykhForm.setIsYszgCert("是");
					}else if("178".equals(qmId) || "201".equals(qmId) || "202".equals(qmId)){
						jykhForm.setIsYszgCert("否");
					}
					//医师资格级别
					if("176".equals(qmId) || "177".equals(qmId)){
						jykhForm.setYszgLevel("执业医师");
					}else if("200".equals(qmId)){
						jykhForm.setYszgLevel("执业助理医师");
					}
					jykhForm.setYszgType(resume.getPracticingCategoryName());
					jykhForm.setYszgCertNo(resume.getQualificationMaterialCode());
					//是否全科订单定向学员
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resume.getIsGeneralOrderOrientationTrainee())) {
						jykhForm.setIsQkdddxxy("是");
                    } else if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(resume.getIsGeneralOrderOrientationTrainee())) {
						jykhForm.setIsQkdddxxy("否");
					}
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resume.getIsDoctor())) {
						jykhForm.setSchoolName(resume.getDoctorGraSchoolName());//毕业/在读院校
						jykhForm.setSchoolSpeName(resume.getDoctorMajor());//毕业（在读）专业/二级学科
						jykhForm.setDegreeName(resume.getDoctorDegreeName());//学位
                    } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resume.getIsMaster())) {
						jykhForm.setSchoolName(resume.getMasterGraSchoolName());
						jykhForm.setSchoolSpeName(resume.getMasterMajor());
						jykhForm.setDegreeName(resume.getMasterDegreeName());
					}else{//本科学历
						jykhForm.setSchoolName(resume.getGraduatedName());
						jykhForm.setSchoolSpeName(resume.getSpecialized());
						jykhForm.setDegreeName(resume.getDegreeName());
					}
					//学位类型
					if("博士".equals(jykhForm.getDegreeName())){
						jykhForm.setDegreeType("博士学位类型");
					}else if("硕士".equals(jykhForm.getDegreeName())){
						jykhForm.setDegreeType("硕士学位类型");
					}else{
						jykhForm.setDegreeType("无学位类型");
					}
				}
				String doctorTypeId = ext.getDoctorTypeId();
				//是否有工作单位
                if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctorTypeId)) {
					jykhForm.setIsGzdw("是");
					jykhForm.setGzdwName(ext.getWorkOrgName());//工作单位名称
				}else{
					jykhForm.setIsGzdw("否");
				}
				jykhForm.setRyAttributes("地方人员");
				jykhForm.setRyType(ext.getDoctorTypeName());
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ext.getIsYearGraduate())) {
					jykhForm.setThisPastYear("应届");
                } else if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(ext.getIsYearGraduate())) {
					jykhForm.setThisPastYear("往届");
				}
				jykhForm.setSessionNumber(ext.getSessionNumber());
				jykhForm.setZsztName("已录取");
				jykhForm.setTrainOrgName(ext.getOrgName());
				jykhForm.setTrainSpeName(ext.getSpeName());
                jykhForm.setTrainYear(com.pinde.core.common.enums.JsResTrainYearEnum.getNameById(ext.getTrainYear()));
				jykhForm.setTrainBeginTime(ext.getRecruitDate());
                if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(ext.getTrainYear())) {
					jykhForm.setTrainEndTime(DateUtil.addYear(ext.getRecruitDate(),1));
                } else if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(ext.getTrainYear())) {
					jykhForm.setTrainEndTime(DateUtil.addYear(ext.getRecruitDate(),2));
                } else if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(ext.getTrainYear())) {
					jykhForm.setTrainEndTime(DateUtil.addYear(ext.getRecruitDate(),3));
				}
				//是否在读
                if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctorTypeId) || com.pinde.core.common.enums.ResDocTypeEnum.Social.getId().equals(doctorTypeId)) {
					jykhForm.setIsReading("否");
				}else{
					jykhForm.setIsReading("是");
				}
				String educationId = ext.getEducationId();
				//学历
				if("193".equals(educationId) || "194".equals(educationId)){//193硕士或194博士，学历为研究生；其他为大学本科
					jykhForm.setEducationName("研究生");
				}else{
					jykhForm.setEducationName("大学本科");
				}
				jykhForm.setIsQrzjy("是");
				String certNo = ext.getCertificateNo();
				if(StringUtil.isNotBlank(certNo)){
					jykhForm.setIsGainCert("是");
				}else{
					jykhForm.setIsGainCert("否");
				}
				jykhForm.setXlCertNo(certNo);
				jykhForm.setDegreeCertNo(ext.getDegreeNo());
				if(StringUtil.isNotBlank(ext.getJointFlow())){//为协同机构
					jykhForm.setIsInXtOrg("是");
				}else{
					jykhForm.setIsInXtOrg("否");
				}
				jykhForm.setTheoryTestYear(ext.getTheoryYear());
				jykhForm.setSkillTestYear(ext.getSkillYear());
				BigDecimal theoryScore = ext.getTheoryScore();
				if(null != theoryScore){
					jykhForm.setTheoryTestScore(theoryScore.toString());
					BigDecimal lineScore = new BigDecimal(ext.getCfgPassScore());
					if(theoryScore.compareTo(lineScore) > 0){
						jykhForm.setTheoryTestResult("通过");
					}else{
						jykhForm.setTheoryTestResult("不通过");
					}
				}
				String scoreContent = ext.getExtScore();
				if(StringUtil.isNotBlank(scoreContent)) {
					Map<String,String> score = new HashMap<>();
					Document doc = DocumentHelper.parseText(scoreContent);
					Element root = doc.getRootElement();
					Element extScoreInfo = root.element("extScoreInfo");
					if (null != extScoreInfo) {
						List<Element> extInfoAttrEles = extScoreInfo.elements();
						if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
							for (Element attr : extInfoAttrEles) {
								String attrName = attr.getName();
								String attrValue = attr.getText();
								score.put(attrName, attrValue);
							}
						}
					}
					Double scoreSum = 0.0;
					jykhForm.setOneScore(score.get("firstStationScore"));
					jykhForm.setTwoScore(score.get("secondStationScore"));
					jykhForm.setThreeScore(score.get("thirdStationScore"));
					jykhForm.setFourScore(score.get("fourthStationScore"));
					jykhForm.setFiveScore(score.get("fifthStationScore"));
					jykhForm.setSixScore(score.get("sixthStationScore"));
					jykhForm.setSevenScore(score.get("seventhStationScore"));
					jykhForm.setEightScore(score.get("eighthStationScore"));
					jykhForm.setNineScore(score.get("ninthStationScore"));
					if(StringUtil.isNotBlank(jykhForm.getOneScore())){
						scoreSum += Double.valueOf(jykhForm.getOneScore());
					}
					if(StringUtil.isNotBlank(jykhForm.getTwoScore())){
						scoreSum += Double.valueOf(jykhForm.getTwoScore());
					}
					if(StringUtil.isNotBlank(jykhForm.getThreeScore())){
						scoreSum += Double.valueOf(jykhForm.getThreeScore());
					}
					if(StringUtil.isNotBlank(jykhForm.getFourScore())){
						scoreSum += Double.valueOf(jykhForm.getFourScore());
					}
					if(StringUtil.isNotBlank(jykhForm.getFiveScore())){
						scoreSum += Double.valueOf(jykhForm.getFiveScore());
					}
					if(StringUtil.isNotBlank(jykhForm.getSixScore())){
						scoreSum += Double.valueOf(jykhForm.getSixScore());
					}
					if(StringUtil.isNotBlank(jykhForm.getSevenScore())){
						scoreSum += Double.valueOf(jykhForm.getSevenScore());
					}
					if(StringUtil.isNotBlank(jykhForm.getEightScore())){
						scoreSum += Double.valueOf(jykhForm.getEightScore());
					}
					if(StringUtil.isNotBlank(jykhForm.getNineScore())){
						scoreSum += Double.valueOf(jykhForm.getNineScore());
					}
					scoreSum=new BigDecimal(scoreSum).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
					jykhForm.setSkillTestScore(scoreSum+"");
				}
				BigDecimal skillScore = ext.getSkillScore();
				if(null != skillScore){
					if(skillScore.compareTo(new BigDecimal("1")) == 0){
						jykhForm.setSkillTestResult("通过");
					}else if(skillScore.compareTo(new BigDecimal("0")) == 0){
						jykhForm.setSkillTestResult("不通过");
					}
				}
				if("通过".equals(jykhForm.getTheoryTestResult()) && "通过".equals(jykhForm.getSkillTestResult())){
					jykhForm.setGraduateTestResult("通过");
				}else if(StringUtil.isNotBlank(jykhForm.getTheoryTestResult())&& StringUtil.isNotBlank(jykhForm.getSkillTestResult())){
					jykhForm.setGraduateTestResult("不通过");
				}
				jykhForm.setIsPassAllTest("是");
				jykhFormList.add(jykhForm);
			}
		}
		return jykhFormList;
	}

	@Override
	public void createCertificateNo(List<ResDoctorRecruit> resDoctorRecruits) {
		for (ResDoctorRecruit recruit: resDoctorRecruits) {
			String no = "";// getCertificateNo(recruit);证书不生成，通过导入
			doctorRecruitMapper.updateCertificateNo(no,recruit.getRecruitFlow(),"未发放");
		}
	}

	@Override
	public void createNotCertificateNo(List<ResDoctorRecruit> resDoctorRecruits) {
		for (ResDoctorRecruit recruit: resDoctorRecruits) {
			String no = "";// 取消成绩合格确认的状态.-- 取消确认.
			doctorRecruitMapper.updateNotCertificateNo(recruit.getRecruitFlow());
		}
	}

	@Autowired
	private IResJointOrgBiz resJointBiz;
	@Autowired
	private IOrgBiz orgBiz;
	public String getCertificateNo(ResDoctorRecruit recruit){
		String completeNo = "";
		//所有助理全科人员都只生成助理全科证书
        if (recruit.getCatSpeId().equals(com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId()))
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
			SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
			String dishiCode= (String) city.get(org.getOrgCityId());
			String kumu="";
			//18 ==04  50 ===03
			if(recruit.getSpeId().equals("18"))
			{
				kumu="04";
			}else if(recruit.getSpeId().equals("50"))
			{
				kumu="03";
			}
			String yearbefore=year.substring(0,2);
			//查询当前年份下，当前地市已经结业的流水号
			Map map=jsResDoctorRecruitExtMapper.getOrgNumByCityId(org.getOrgFlow(),org.getOrgCityId(),year,yearbefore);
			String num= (String) map.get("NUM");
			completeNo=year.substring(2)+kumu+dishiCode+num;
		}else {
			int sessionYear = Integer.valueOf(recruit.getSessionNumber());
			//2013年以前全部用江苏省证书
			if (sessionYear <= 2013) {
				//江苏省生成规则待定
				completeNo = getProvinceOrgNo(recruit);
			} else if (sessionYear == 2014) {
				SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
				//只有国家基地使用国家证书
                if (org.getOrgLevelId().equals(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId())) {
					completeNo = getCountryOrgNo(recruit);
				} else {
					//江苏省生成规则待定
					completeNo = getProvinceOrgNo(recruit);
				}
			} else {
				SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
				//国家基地使用国家证书
                if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
					completeNo = getCountryOrgNo(recruit);
				} else {
					List<ResJointOrg> jointOrgList = resJointBiz.searchResJointByJointOrgFlow(org.getOrgFlow());
					if (jointOrgList != null && jointOrgList.size() > 0) {
						ResJointOrg resJointOrg = jointOrgList.get(0);
						SysOrg org2 = orgBiz.readSysOrg(resJointOrg.getOrgFlow());
						//国家基地的协同基地也使用国家证书
                        if (org2.getOrgLevelId().equals(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId())) {
							completeNo = getCountryOrgNo(recruit);
						} else {
							//江苏省生成规则待定
							completeNo = getProvinceOrgNo(recruit);
						}
					} else {
						//江苏省生成规则待定
						completeNo = getProvinceOrgNo(recruit);
					}
				}
			}
		}
		return  completeNo;

	}
	public String getProvinceOrgNo(ResDoctorRecruit recruit)
	{
		String no="";
		//全科  一阶段全科专业代码为51为中医  52为西医 住院医师全科专业代码为0700
		if(recruit.getSpeId().equals("51")||recruit.getSpeId().equals("52")||recruit.getSpeId().equals("0700"))
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
			SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
			String dishiCode= (String) city.get(org.getOrgCityId());
			String kumu="";
			if(recruit.getSpeId().equals("52")||recruit.getSpeId().equals("0700"))
			{
				kumu="01";
			}else if(recruit.getSpeId().equals("51"))
			{
				kumu="02";
			}
			//查询当前年份下，当前地市已经结业的流水号
			String yearbefore=year.substring(0,2);
			Map map=jsResDoctorRecruitExtMapper.getQuanKe(org.getOrgCityId(),year,yearbefore);
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
            city.put("321300", com.pinde.core.common.GlobalConstant.FLAG_N);
			String year=DateUtil.getYear();
			SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
			String dishiCode= (String) city.get(org.getOrgCityId());
			String p="1";//需求说写死
			//查询当前年份下，当前地市已经结业的流水号
			String yearbefore=year.substring(0,2);
			Map map=jsResDoctorRecruitExtMapper.getFeiQuanKe(org.getOrgCityId(),year,yearbefore);
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
	public String getCountryOrgNo(ResDoctorRecruit recruit)
	{
		//年份代码（4位）+省（自治区、直辖市）代码（2位）+专业代码（4位）+培训基地代码（3位）+该培训基地该年度结业人员顺序号（3位）
		String year=DateUtil.getYear();
		String proCode="32";
		String speId=recruit.getSpeId();
		Map<String ,Object> map=getOrgCodeAndNum(recruit.getOrgFlow(),year);
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
		return  jsResDoctorRecruitExtMapper.getOrgCodeAndNum(orgFlow,year);
	}

	/**
	 * 加上同步锁机制，以防多次导入时，引起证书编号重复问题
	 * @param file
	 * @return
     */
	@Override
	public synchronized ExcelUtile importDoctorCertificateNoFromExcel(MultipartFile file) {
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));

			final String[] keys = {
					"graduationYear",
					"userName",
					"idNo",
					"orgName",
					"catSpeId",
					"speId",
					"sessionNumber"
			};

			final List<String> execlRows = new ArrayList<>();
			return ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<Map<String,Object>> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					for(int i=0;i<datas.size();i++)
					{
						ResDoctorRecruitWithBLOBs recruit= (ResDoctorRecruitWithBLOBs) datas.get(i).get("recruit");
						recruit.setGraduationCertificateNo(getCertificateNo(recruit));
						recruit.setCertificateDate(DateUtil.getCurrDate());
						recruit.setDoctorStatusId("21");
                        recruit.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById("21"));
						ResDoctor resDoctor=resDoctorBiz.readDoctor(recruit.getDoctorFlow());
						if(resDoctor!=null) {
							resDoctor.setDoctorStatusId("21");
                            resDoctor.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById("21"));
						}
						resDoctorBiz.editDoctor(resDoctor);
						count+=saveDoctorRecruit(recruit);
					}
					eu.put("code",code);
					eu.put("count",count);
				}
				@Override
				public String checkExcelData(HashMap data,ExcelUtile eu) {
					String sheetName=(String)eu.get("SheetName");
					if(sheetName==null||!"GraduationCertificate".equals(sheetName))
					{
						return errorMsg(eu,"请使用系统提供的结业学员信息模板！！");
					}
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					String graduationYear="";
					String idNo="";
					String sessionNumber="";
					for (Object key1 : data.keySet()) {
						String key=(String) key1;
						Map<String,Object> map=new HashMap<String, Object>();
						String value=(String) data.get(key);
						if("graduationYear".equals(key))
						{
							graduationYear=value;
						}else if("idNo".equals(key))
						{
							idNo=value;
						}else if("sessionNumber".equals(key))
						{
							sessionNumber=value;
						}
					}
					if(StringUtil.isBlank(graduationYear))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行结业年份为空，请确认后提交！！");
					}else{
						if (!graduationYear.matches("^[1-9][0-9]{3}$")) {
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行结业年份格式有误，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(idNo))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行身份证号为空，请确认后提交！！");
					}
					if(StringUtil.isBlank(sessionNumber))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行年级为空，请确认后提交！！");
					}else{
						if (!sessionNumber.matches("^[1-9][0-9]{3}$")) {
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行年级格式有误，请确认后提交！！");
						}
					}
					SysUser u=userBiz.findByIdNo(idNo);
					if(null==u)
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行证件号码为【"+idNo+"】学员信息不存在，请确认后提交！！");
					}
					String key=graduationYear+idNo+sessionNumber;
					if(execlRows.contains(key))
					{
						int lastRowNum=execlRows.indexOf(key)+2;
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行与第"+lastRowNum+"行必填信息存在重复，请确认后提交！！");
					}
					execlRows.add(key);
					ResDoctorRecruit search=new ResDoctorRecruit();
					search.setAuditStatusId(AuditStatusEnum.Passed.getId());
					search.setDoctorFlow(u.getUserFlow());
					search.setGraduationYear(graduationYear);
					search.setSessionNumber(sessionNumber);
					List<ResDoctorRecruitWithBLOBs> recruits=readDoctorRecruitBlobs(search);
					if(null==recruits||recruits.size()<=0)
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行学员培训记录不存在，请确认后提交！！");
					}
					ResDoctorRecruitWithBLOBs recruit=recruits.get(0);
					if(StringUtil.isNotBlank(recruit.getGraduationCertificateNo()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行学员结业证书已存在，请确认后提交！！");
					}
					data.put("recruit",recruit);
					return null;
				}
			});
		} catch (Exception e) {
            logger.error("", e);
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
	 * 加上同步锁机制，以防多次导入时，引起证书编号重复问题
	 * @param file
	 * @return
     */
	@Override
	public synchronized ExcelUtile importDoctorCertificateNoFromExcel2(MultipartFile file) {
		List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));

			final String[] keys = {
					"qualifiedId",
					"graduationYear",
					"userName",
					"idNo",
					"orgName",
					"catSpeId",
					"speId",
					"sessionNumber",
					"graduationCertificateNo",
					"certificateIssuingStatus"
			};

			final List<String> execlRows = new ArrayList<>();
			final List<String> nums = new ArrayList<>();
			return ExcelUtile.importDataExcel(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<Map<String,Object>> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					for(int i=0;i<datas.size();i++)
					{
						ResDoctorRecruitWithBLOBs recruit= (ResDoctorRecruitWithBLOBs) datas.get(i).get("recruit");
						recruit.setGraduationCertificateType("country");
						recruit.setCertificateDate(DateUtil.getCurrDate());
						recruit.setDoctorStatusId("21");
                        recruit.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById("21"));
						ResDoctor resDoctor=resDoctorBiz.readDoctor(recruit.getDoctorFlow());
						if(resDoctor!=null) {
							resDoctor.setDoctorStatusId("21");
                            resDoctor.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById("21"));
						}
						resDoctorBiz.editDoctor(resDoctor);
						count+=saveDoctorRecruit(recruit);
					}
					eu.put("code",code);
					eu.put("count",count);
				}
				@Override
				public String checkExcelData(HashMap data,ExcelUtile eu) {
					String sheetName=(String)eu.get("SheetName");
					if(sheetName==null||!"GraduationCertificate".equals(sheetName))
					{
						return errorMsg(eu,"请使用系统提供的结业学员信息模板！！");
					}
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					String graduationYear="";
					String idNo="";
					String sessionNumber="";
					String graduationCertificateNo="";
					String qualifiedId="";
					String certificateIssuingStatus="";
					String userName="";
					String orgName="";
					String catSpeId="";
					String speId="";
					for (Object key1 : data.keySet()) {
						String key=(String) key1;
						Map<String,Object> map=new HashMap<String, Object>();
						String value=(String) data.get(key);
						if("graduationYear".equals(key))
						{
							graduationYear=value;
						}else if("idNo".equals(key))
						{
							idNo=value;
						}else if("sessionNumber".equals(key))
						{
							sessionNumber=value;
						}else if("graduationCertificateNo".equals(key))
						{
							graduationCertificateNo=value;
						} else if ("qualifiedId".equals(key)) {
							qualifiedId = value;
						} else if ("certificateIssuingStatus".equals(key)) {
							certificateIssuingStatus = value;
						} else if ("userName".equals(key)) {
							userName = value;
						} else if ("orgName".equals(key)) {
							orgName = value;
						} else if ("catSpeId".equals(key)) {
							catSpeId = value;
						} else if ("speId".equals(key)) {
							speId = value;
						}
					}
					if (StringUtil.isBlank(userName)) {
						return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行姓名为空，请确认后提交！！");
					}
					if (StringUtil.isBlank(orgName)) {
						return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行培训基地为空，请确认后提交！！");
					}
					if (StringUtil.isBlank(catSpeId)) {
						return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行培训类别为空，请确认后提交！！");
					}
					if (StringUtil.isBlank(speId)) {
						return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行培训专业为空，请确认后提交！！");
					}
					if (StringUtil.isBlank(qualifiedId)) {
						return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行合格批次为空，请确认后提交！！");
					} else {
						if (resTestConfigs != null && resTestConfigs.size() > 0) {
							//判断导入的合格批次在系统中存不存在
							String finalQualifiedId = qualifiedId;
							List<ResTestConfig> resTestConfigList = resTestConfigs.stream().filter(resTestConfig -> finalQualifiedId.equals(resTestConfig.getTestId())).collect(Collectors.toList());
							if (resTestConfigList == null || resTestConfigList.size() == 0) {
								return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行合格批次在系统中不存在，请确认后提交！！");
							}
						} else {
							return errorMsg(eu, "当前没有任何考试，请确认后提交！！");
						}
					}
					if (StringUtil.isBlank(certificateIssuingStatus)) {
						return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行证书发放状态为空，请确认后提交！！");
					} else {
						if(!("发放".equals(certificateIssuingStatus) || "未发放".equals(certificateIssuingStatus))){
							return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行证书发放状态格式有误，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(graduationYear))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行结业年份为空，请确认后提交！！");
					}else{
						if (!graduationYear.matches("^[1-9][0-9]{3}$")) {
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行结业年份格式有误，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(idNo))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行身份证号为空，请确认后提交！！");
					}
					if(StringUtil.isBlank(sessionNumber))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行年级为空，请确认后提交！！");
					}else{
						if (!sessionNumber.matches("^[1-9][0-9]{3}$")) {
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行年级格式有误，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(graduationCertificateNo))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行证书编号为空，请确认后提交！！");
					}
					if(nums.contains(graduationCertificateNo))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行证书编号存在重复，请确认后提交！！");
					}
					ResDoctorRecruit s=new ResDoctorRecruit();
					s.setGraduationCertificateNo(graduationCertificateNo);
					List<ResDoctorRecruitWithBLOBs> ss=readDoctorRecruitBlobs(s);
					if(null!=ss&&ss.size()>0)
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行证书编号已发放，请确认后提交！！");
					}
					nums.add(graduationCertificateNo);
					SysUser u=userBiz.findByIdNo(idNo);
					if(null==u)
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行证件号码为【"+idNo+"】学员信息不存在，请确认后提交！！");
					}
					String key=graduationYear+idNo+sessionNumber;
					if(execlRows.contains(key))
					{
						int lastRowNum=execlRows.indexOf(key)+2;
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行与第"+lastRowNum+"行必填信息存在重复，请确认后提交！！");
					}
					execlRows.add(key);
					ResDoctorRecruit search=new ResDoctorRecruit();
					search.setAuditStatusId(AuditStatusEnum.Passed.getId());
					search.setDoctorFlow(u.getUserFlow());
					search.setGraduationYear(graduationYear);
					search.setSessionNumber(sessionNumber);
					List<ResDoctorRecruitWithBLOBs> recruits=readDoctorRecruitBlobs(search);
					if(null==recruits||recruits.size()<=0)
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行学员培训记录不存在，请确认后提交！！");
					}
					ResDoctorRecruitWithBLOBs recruit=recruits.get(0);
					if(StringUtil.isNotBlank(recruit.getGraduationCertificateNo()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行学员结业证书已存在，请确认后提交！！");
					}
					recruit.setGraduationCertificateNo(graduationCertificateNo);
					recruit.setQualifiedId(qualifiedId);
					recruit.setCertificateIssuingStatus(certificateIssuingStatus);
					data.put("recruit",recruit);
					return null;
				}
			});
		} catch (Exception e) {
            logger.error("", e);
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
	public List<Map<String, Object>> zlxytj(String sessionNumber) {
		return jsResDoctorRecruitExtMapper.zlxytj(sessionNumber);
	}
	@Override
	public List<Map<String, Object>> zlxytj2(Map<String,Object> param) {
		return jsResDoctorRecruitExtMapper.zlxytj2(param);
	}
	@Override
	public List<Map<String, Object>> zlxytjJoint(Map<String,Object> param) {
		return jsResDoctorRecruitExtMapper.zlxytjJoint(param);
	}

    @Override
    public HSSFWorkbook createCycleResultsByDoc(String doctorFlow, String roleId, String schStartDate, String schEndDate) {
        ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
        SysUser doc = userBiz.readSysUser(doctorFlow);
        Map<String,String> pMap = new HashMap<>();
        if("kzr".equals(roleId)){
            SysUser currentUser= GlobalContext.getCurrentUser();
            pMap.put("deptFlow",currentUser.getDeptFlow());
            pMap.put("kzrFlow",currentUser.getUserFlow());
            pMap.put("doctorFlow",doctorFlow);
        }else {
            pMap.put("doctorFlow",doctorFlow);
        }
		pMap.put("schStartDate", schStartDate);
		pMap.put("schEndDate", schEndDate);
        /*

         */
        List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByMap(pMap);
        Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> skillMap = new HashMap<>();
        Map<String,String> DOPSFlowMap = new HashMap<>();
		Map<String,Object> dopsInfoMap = new HashMap<>();
        Map<String,String> MiniFlowMap = new HashMap<>();
		Map<String,Object> miniInfoMap = new HashMap<>();
        Map<String,String> AfterFlowMap = new HashMap<>();
        Map<String,String> AfterSummFlowMap = new HashMap<>();
        if(arrResultList!=null&&arrResultList.size()>0){
            for(SchArrangeResult schArrangeResult:arrResultList){
                String resultFlow = schArrangeResult.getResultFlow();

                String standardDeptId = schArrangeResult.getStandardDeptId();
                String standarGroupFlow = schArrangeResult.getStandardGroupFlow();
                SchRotationDept schRotationDept = deptMap.get(standarGroupFlow + standardDeptId);
                if (schRotationDept == null)
                    schRotationDept = schRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standarGroupFlow, standardDeptId);
                deptMap.put(standarGroupFlow + standardDeptId, schRotationDept);

                ResDoctorSchProcess doctorSchProcess = resDoctorProcessBiz.searchByResultFlow(resultFlow);
                resultMap.put(resultFlow,doctorSchProcess);
                if(doctorSchProcess!=null){
                    String processFlow = doctorSchProcess.getProcessFlow();
                    List<ResSchProcessExpress> resRecs = expressBiz.searchByProcessFlowClob(processFlow);
                    if(resRecs!=null&&resRecs.size()>0)
                    {
                        for(ResSchProcessExpress r:resRecs)
                        {
                            if(AfterRecTypeEnum.DOPS.getId().equals(r.getRecTypeId()))
                            {
                                DOPSFlowMap.put(processFlow,r.getRecFlow());
								 String recContent = r.getRecContent();
								 dopsInfoMap.put(processFlow, resRecBiz.parseRecContent(recContent));
                            }
                            if(AfterRecTypeEnum.Mini_CEX.getId().equals(r.getRecTypeId()))
                            {
                                MiniFlowMap.put(processFlow,r.getRecFlow());
								 String recContent = r.getRecContent();
								 miniInfoMap.put(processFlow, resRecBiz.parseRecContent(recContent));
                            }
                            if(AfterRecTypeEnum.AfterSummary.getId().equals(r.getRecTypeId()))
                            {
                                AfterSummFlowMap.put(processFlow,r.getRecFlow());
                            }
                            if(AfterRecTypeEnum.AfterEvaluation.getId().equals(r.getRecTypeId()))
                            {
                                AfterFlowMap.put(processFlow,r.getRecFlow());
                                String recContent = r.getRecContent();
                                skillMap.put(processFlow, resRecBiz.parseRecContent(recContent));
                            }
                        }
                    }
                }
            }
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        if(arrResultList != null){
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
			int rowNum = 3;
            String[] resultList = null;
            if (arrResultList != null && !arrResultList.isEmpty()) {
                for (int i = 0; i < arrResultList.size(); i++, rowNum++) {
                    if (arrResultList != null && arrResultList.size() > 0) {
                        String theoryScore = "";
                        String skillName = "";
                        String skillScore = "";
                        String haveAfterPic = "未上传";
                        Object process = resultMap.get(arrResultList.get(i).getResultFlow());
                        if(process != null){
                            String key = ((ResDoctorSchProcess)process).getProcessFlow();
							 Map<String, Object> obj1 = (Map<String, Object>) skillMap.get(key);
							 Map<String, Object> dopsInfo = (Map<String, Object>) dopsInfoMap.get(key);
							 Map<String, Object> miniInfo = (Map<String, Object>) miniInfoMap.get(key);
                            if (obj1 != null) {
                                if (obj1.get("theoreResult") != null) {
                                    theoryScore = obj1.get("theoreResult").toString();
                                } else {
                                    if (((ResDoctorSchProcess) process).getSchScore() != null) {
                                        theoryScore = ((ResDoctorSchProcess) process).getSchScore().toString();
                                    }
                                }
                                if (obj1.get("skillName") != null) {
                                    skillName = obj1.get("skillName").toString();
                                }
                                if (obj1.get("score") != null) {
                                    skillScore = obj1.get("score").toString();
                                }
                            }
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(arrResultList.get(i).getHaveAfterPic())) {
                                haveAfterPic = "已上传";
                            } else {
                                haveAfterPic = "未上传";
                            }
							HSSFRow rowFour = sheet.createRow(rowNum);
							// 封装数据
							resultList = new String[]{
									doc.getUserName(),
									doctor.getTrainingSpeName(),
									doctor.getSessionNumber(),
									arrResultList.get(i).getSchDeptName(),
									arrResultList.get(i).getSchStartDate() + "~" + arrResultList.get(i).getSchEndDate(),
									process == null ? "" : ((ResDoctorSchProcess) process).getTeacherUserName(),
									theoryScore,
									skillName,
									skillScore,
									(null == dopsInfo || null == dopsInfo.get("studentSkillNum")) ? "" : dopsInfo.get("studentSkillNum").toString(),
									(null == dopsInfo || null == dopsInfo.get("skillComplexDegree")) ? "" : dopsInfo.get("skillComplexDegree").toString(),
									(null == dopsInfo || null == dopsInfo.get("skillLevel")) ? "" : dopsInfo.get("skillLevel").toString(),
									(null == dopsInfo || null == dopsInfo.get("consentForm")) ? "" : dopsInfo.get("consentForm").toString(),
									(null == dopsInfo || null == dopsInfo.get("readyToWork")) ? "" : dopsInfo.get("readyToWork").toString(),
									(null == dopsInfo || null == dopsInfo.get("painAndStabilization")) ? "" : dopsInfo.get("painAndStabilization").toString(),
									(null == dopsInfo || null == dopsInfo.get("SkillAbility")) ? "" : dopsInfo.get("SkillAbility").toString(),
									(null == dopsInfo || null == dopsInfo.get("asepticTechnique")) ? "" : dopsInfo.get("asepticTechnique").toString(),
									(null == dopsInfo || null == dopsInfo.get("seekAssistance")) ? "" : dopsInfo.get("seekAssistance").toString(),
									(null == dopsInfo || null == dopsInfo.get("relatedDisposal")) ? "" : dopsInfo.get("relatedDisposal").toString(),
									(null == dopsInfo || null == dopsInfo.get("communicationSkills")) ? "" : dopsInfo.get("communicationSkills").toString(),
									(null == dopsInfo || null == dopsInfo.get("feelProfessionalDegree")) ? "" : dopsInfo.get("feelProfessionalDegree").toString(),
									(null == dopsInfo || null == dopsInfo.get("overallPerformance")) ? "" : dopsInfo.get("overallPerformance").toString(),
									(null == dopsInfo || null == dopsInfo.get("degreeSatisfaction")) ? "" : dopsInfo.get("degreeSatisfaction").toString(),
									(null == dopsInfo || null == dopsInfo.get("teacherComment")) ? "" : dopsInfo.get("teacherComment").toString(),
									(null == miniInfo || null == miniInfo.get("severity")) ? "" : miniInfo.get("severity").toString(),
									(null == miniInfo || null == miniInfo.get("diagnosisKeynote")) ? "" : miniInfo.get("diagnosisKeynote").toString(),
									(null == miniInfo || null == miniInfo.get("medicalInterview")) ? "" : miniInfo.get("medicalInterview").toString(),
									(null == miniInfo || null == miniInfo.get("physicalExamination")) ? "" : miniInfo.get("physicalExamination").toString(),
									(null == miniInfo || null == miniInfo.get("humanisticCare")) ? "" : miniInfo.get("humanisticCare").toString(),
									(null == miniInfo || null == miniInfo.get("clinicalJudgment")) ? "" : miniInfo.get("clinicalJudgment").toString(),
									(null == miniInfo || null == miniInfo.get("communicationSkills")) ? "" : miniInfo.get("communicationSkills").toString(),
									(null == miniInfo || null == miniInfo.get("organization")) ? "" : miniInfo.get("organization").toString(),
									(null == miniInfo || null == miniInfo.get("holisticCare")) ? "" : miniInfo.get("holisticCare").toString(),
									(null == miniInfo || null == miniInfo.get("degreeSatisfaction")) ? "" : miniInfo.get("degreeSatisfaction").toString(),
									(null == miniInfo || null == miniInfo.get("teacherComment")) ? "" : miniInfo.get("teacherComment").toString(),
									haveAfterPic
							};
							for (int j = 0; j < titles.length; j++) {
								HSSFCell cellFirst = rowFour.createCell(j);
								cellFirst.setCellStyle(styleCenter);
								cellFirst.setCellValue(resultList[j]);
							}
                        }
                    }
                }
            }
        }
        return wb;
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

    private void setColumnWidth(int length, int key, Map<Integer, Integer> columnWidth) {
        if(columnWidth.containsKey(key)) {
            Integer ol = columnWidth.get(key);
            if(ol<length)
                columnWidth.put(key,length);
        }else{
            columnWidth.put(key,length);
        }
    }
    private String errorMsg(ExcelUtile eu, String msg) {
		eu.put("count", 0);
		eu.put("code", "1");
		eu.put("msg", msg);
		return ExcelUtile.RETURN;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void exportForDetailByOrg(List<JsDoctorInfoExt> doctorInfoExts,String titleYear, HttpServletResponse response) throws Exception {
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
		HSSFRow rowThree = sheet.createRow(0);//第三行
		String[] titles = new String[]{
				"培训基地",
				"*姓名",
				"*性别",
				"*出生日期（yyyy-mm-dd）",
				"*身份证件类型",
				"#身份证件号码",
				"*国家或地区",
				"*民族",
				"*手机号",
				"邮箱",
				"QQ（非必填）",
				"*往届/应届",
				"*培训专业",
				"*是否通过全国医师资格考试",
				"执业医师资格证号",
				"*招收年度",
				"*实际培训开始时间（yyyy-mm）",
				"*是否为对口支援计划住院医师",
				"#对口支援计划住院医师送出单位（请填全称）",
				"*培训年限核定",
				"*学员类型",
				"*毕业学校（本科）",
				"*毕业年份（本科）",
				"*毕业专业（本科）",
				"*是否全科订单定向学员",
				"*学位（本科）",
				"*是否硕士研究生",
				"#毕业院校（硕士）",
				"#毕业年份（硕士）",
				"#毕业专业（硕士）",
				"#学位（硕士）",
				"#学位类型（硕士）",
				"*是否博士研究生",
				"#毕业院校（博士）",
				"#毕业年份（博士）",
				"#毕业专业（博士）",
				"#学位（博士）",
				"#学位类型（博士）",
				"#工作单位（与单位公章对应的官方全称）",
				"#医院级别",
				"#医院等次",
				"#医疗卫生机构类别",
				"#医疗卫生机构隶属关系",
				"*是否在协同单位培训",
				"#协同单位（与单位公章对应的官方全称）",
				"#协同医院级别",
				"#协同医院等次",
				"#医疗卫生机构类别",
				"审核状态"
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
		if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
			for (int i = 0; i < doctorInfoExts.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行

				JsDoctorInfoExt doctorInfoExt = doctorInfoExts.get(i);
				//培训基地
				String orgName = "";
				String joinName = "";
				String jointFlag = "";
				List<Map<String, Object>> jointOrgs = searchJointOrgList();
				Map<Object, Object> orgAndJointNameMap = new HashMap<Object, Object>();
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
					for (Map<String, Object> en : jointOrgs) {
						Object key = en.get("key");
						Object value = en.get("value");
						orgAndJointNameMap.put(key, value);
					}
				}
				if (orgAndJointNameMap.containsKey(doctorInfoExt.getOrgFlow())) {
					orgName = (String) orgAndJointNameMap.get(doctorInfoExt.getOrgFlow());
					joinName = doctorInfoExt.getOrgName();
					jointFlag = "是";
				} else {
					jointFlag = "否";
					orgName = doctorInfoExt.getOrgName();
				}
                SysUser sysUser = doctorInfoExt.getSysUser();
				String age ="";
				if(sysUser != null) {
                    String userBirthday = sysUser.getUserBirthday();
                    if (StringUtil.isNotBlank(userBirthday) && userBirthday.length() >= 4) {
                        age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(userBirthday.substring(0, 4))) + "";
                    }
				}

				ResDoctor doctor = doctorInfoExt.getResDoctor();

				String CretType = "";
				String area = "";
				String cretTypeId = sysUser.getCretTypeId() == null ? "" : sysUser.getCretTypeId();
				if (CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "居民身份证";
					area="中国大陆";
				}
//				else if (CertificateTypeEnum.Junguanzheng.getId().equals(sysUser.getCretTypeId())) {
//					CretType = "军队证件";
//					area="中国大陆";
//				}
				else if (CertificateTypeEnum.Passport.getId().equals(sysUser.getCretTypeId())) {
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
				String isYearGraduate = "";
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}
                String recruitDate = doctorInfoExt.getRecruitDate();
                if (recruitDate != null && recruitDate.length() >= 7) {
                    recruitDate = doctorInfoExt.getRecruitDate().substring(0, 4) + "-" + doctorInfoExt.getRecruitDate().substring(5, 7) ;
                }

				//解析xml
				PubUserResume userResume = doctorInfoExt.getUserResume();
				UserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), UserResumeExtInfoForm.class);

				//是否是执业医师和编号
				String qualificationFlag = "";
//				if("176".equals(userResumeExt.getQualificationMaterialId()) || "177".equals(userResumeExt.getQualificationMaterialId())){
//					qualificationFlag = "是";
//				}else{
//					qualificationFlag = "否";
//				}
//				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
//					qualificationFlag = "是";
//				} else {
//					qualificationFlag = "否";
//					userResumeExt.setQualificationMaterialCode("");
//				}
                if (userResumeExt.getIsPassQualifyingExamination().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					qualificationFlag = "是";
				} else {
					qualificationFlag = "否";
				}
				//研究生
				String masterFlag = "";
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
					userResumeExt.setMasterDegreeName("");
					userResumeExt.setMasterDegreeTypeName("");
					userResumeExt.setMasterGraSchoolName("");
					userResumeExt.setMasterGraTime("");
					userResumeExt.setMasterMajor("");
					masterFlag = "否";
				} else {
					masterFlag = "是";
				}
				//博士
				String doctorFlag = "";
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
					userResumeExt.setDoctorDegreeName("");
					userResumeExt.setDoctorDegreeTypeName("");
					userResumeExt.setDoctorGraSchoolName("");
					userResumeExt.setDoctorGraTime("");
					userResumeExt.setDoctorMajor("");
					doctorFlag = "否";
				} else {
					doctorFlag = "是";
				}
				//工作单位
				String property = "";
				String yyjb="";
				String yydc="";
				String hospitalCateName="";
				String hospitalAttrName="";
                if (com.pinde.core.common.enums.ResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
					}
					if("1".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("2".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="一级";
						yydc="未定等";
					}
					if("3".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="未定等";
					}
					if("4".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="甲等";
					}
					if("5".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="三级";
						yydc="乙等";
					}
					if("6".equals(userResumeExt.getBaseAttributeId()))
					{
						yyjb="未定级";
						yydc="未定等";
					}
					if("1".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省、自治区、直辖市属";
					}
					if("2".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="省辖市（地区、州、盟、直辖市区）属";
					}
					if("3".equals(userResumeExt.getHospitalAttrId()))
					{
						hospitalAttrName="县级市（省辖市区）属";
					}
					if("1".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getHospitalCategoryName();
					}
					if("3".equals(userResumeExt.getMedicalHeaithOrgId()))
					{
						hospitalCateName=userResumeExt.getBasicHealthOrgName();
					}
				} else {
					doctor.setWorkOrgName("");
					userResumeExt.setMedicalHeaithOrgName("");
				}

				//研究生毕业时间
				String masterGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getMasterGraTime())) {
					masterGraTime = userResumeExt.getMasterGraTime().substring(0, 4);
				}
				//博士毕业时间
				String doctorGraTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getDoctorGraTime())) {
					doctorGraTime = userResumeExt.getDoctorGraTime().substring(0, 4);
				}
				//本科毕业时间
				String graduationTime = "";
				if (StringUtil.isNotBlank(userResumeExt.getGraduationTime())) {
					graduationTime = userResumeExt.getGraduationTime().substring(0, 4);
				}
				//是否全科定向生
				String isGeneralOrderOrientationTrainee = "";
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				if(StringUtil.isNotBlank(doctorInfoExt.getTrainYear())){
					trainYear = doctorInfoExt.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				//审核状态
				String shzt="";
//				if(StringUtil.isNotBlank(doctorInfoExt.getDoctorStatusId())){
//					if("OrgAuditing".equals(doctorInfoExt.getDoctorStatusId())){
//						shzt = "待主基地审核";
//					}else {
//						shzt = com.pinde.core.common.enums.ResDoctorAuditStatusEnum.getNameById(doctorInfoExt.getDoctorStatusId());
//					}
//				}
				if(StringUtil.isNotBlank(doctorInfoExt.getAuditStatusId())){
                    shzt = com.pinde.core.common.enums.ResDoctorAuditStatusEnum.getNameById(doctorInfoExt.getAuditStatusId());
				}
				dataList = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						sysUser.getUserBirthday(),
						CretType,
						sysUser.getIdNo(),
						area,
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						doctorInfoExt.getSessionNumber(),
						recruitDate,
						"否",
						"",
						trainYear,
						doctor.getDoctorTypeName(),
						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						isGeneralOrderOrientationTrainee,
						userResumeExt.getDegreeName(),
						masterFlag,
						userResumeExt.getMasterGraSchoolName(),
						masterGraTime,
						userResumeExt.getMasterMajor(),
						userResumeExt.getMasterDegreeName(),
						userResumeExt.getMasterDegreeTypeName(),
						doctorFlag,
						userResumeExt.getDoctorGraSchoolName(),
						doctorGraTime,
						userResumeExt.getDoctorMajor(),
						userResumeExt.getDoctorDegreeName(),
						userResumeExt.getDoctorDegreeTypeName(),
						doctor.getWorkOrgName(),
						yyjb,
						yydc,
						hospitalCateName,
						hospitalAttrName,
						jointFlag,
						joinName,
						"",
						"",
						"",
						shzt
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = titleYear+"学生信息一览表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());

	}

	@Override
	public void exportRecruitList(List<JsResDoctorRecruitExt> recruitList, HttpServletResponse response) throws Exception{
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
		HSSFRow rowThree = sheet.createRow(0);//第三行
		String[] titles = new String[]{
				"学员姓名",
				"身份证号码",

				"性别",
				"年龄",

				"联系电话",
				"最高学历",

				"毕业院校",
				"毕业时间",

				"培训专业",
				"审核状态",

				"规培起止时间",
				"当前学位类别",
				"届别",
				"所在地区",
				"培训基地",
				"培训类别",
				"培训年限",
				"已培训年限",
				"医师状态",
				"医师走向",
				"结业考核年份",
				"人员类型",

				"地址",
				"工作单位",

				"派送学校",
				"军队人员"
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
		if (recruitList != null && !recruitList.isEmpty()) {
			for (int i = 0; i < recruitList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行

				JsResDoctorRecruitExt recruitExt = recruitList.get(i);
				//规培年限
				String trainYear = "";
				int trainStep = 0;
				if(StringUtil.isNotBlank(recruitExt.getTrainYear())){
					trainYear = recruitExt.getTrainYear();
				}
				switch (trainYear){
					case "OneYear":{trainYear="一年";trainStep=1;break;}
					case "TwoYear":{trainYear="两年";trainStep=2;break;}
					case "ThreeYear":{trainYear="三年";trainStep=3;break;}
				}
				//已培训年限
				String yetTrainYear = "";
				String yetTrainName = "";
				if(StringUtil.isNotBlank(recruitExt.getYetTrainYear())){
					yetTrainYear = recruitExt.getYetTrainYear();
				}
				SysDictExample example = new SysDictExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                        .andDictTypeIdEqualTo(com.pinde.core.common.enums.DictTypeEnum.YetTrainYear.getId());
				List<SysDict> dictList = sysDictMapper.selectByExample(example);
				if(null != dictList && dictList.size()>0){
					for (SysDict dict:dictList) {
						if(StringUtil.isNotBlank(yetTrainYear) && yetTrainYear.equals(dict.getDictId())){
							yetTrainName = dict.getDictName();
							break;
						}
					}
				}
				dataList = new String[]{
						recruitExt.getSysUser().getUserName(),
						recruitExt.getSysUser().getIdNo(),
						recruitExt.getSex(),
						recruitExt.getAge(),
						recruitExt.getSysUser().getUserPhone(),
						recruitExt.getSysUser().getEducationName(),
						recruitExt.getGraduateSchool(),
						recruitExt.getGraduateTime(),
						recruitExt.getSpeName(),
						recruitExt.getAuditStatusName(),

						recruitExt.getRecruitDate()+"~"+ DateUtil.addYear(recruitExt.getRecruitDate(),trainStep),
						recruitExt.getCurrDegreeCategoryName(),
						recruitExt.getSessionNumber(),
						recruitExt.getPlaceName(),
//						GlobalContext.getCurrentUser().getOrgName(),
						StringUtils.isNotEmpty(recruitExt.getJointOrgName()) ? recruitExt.getJointOrgName() : recruitExt.getOrgName(),
						recruitExt.getCatSpeName(),
						trainYear,
						yetTrainName,
						recruitExt.getDoctorStatusName(),
						recruitExt.getDoctorStrikeName(),
						recruitExt.getGraduationYear(),
						recruitExt.getResDoctor().getDoctorTypeName(),
						recruitExt.getSysUser().getUserAddress(),
						recruitExt.getWorkAddr(),
						recruitExt.getWorkSchoolName(),
                        StringUtil.isBlank(recruitExt.getArmyType()) ? "" : com.pinde.core.common.enums.ArmyTypeEnum.getNameById(recruitExt.getArmyType())
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(dataList[j]);
				}
			}
		}
		String fileName = "学员信息审核表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@Override
	public String saveDoctorRecruitNew(ResDoctorRecruitWithBLOBs docRecWithBLOBs) {
		if (StringUtil.isNotBlank(docRecWithBLOBs.getRecruitFlow())) {
			GeneralMethod.setRecordInfo(docRecWithBLOBs, false);
			int i = doctorRecruitMapper.updateByPrimaryKeySelective(docRecWithBLOBs);
			return docRecWithBLOBs.getRecruitFlow();
		} else {
			docRecWithBLOBs.setRecruitFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(docRecWithBLOBs, true);
			int insert = doctorRecruitMapper.insert(docRecWithBLOBs);
			return docRecWithBLOBs.getRecruitFlow();
		}
	}

	@Override
	public int saveDoctorRecruitInfo(ResDoctorRecruitInfo resDoctorRecruitInfo) {
		if (StringUtil.isNotBlank(resDoctorRecruitInfo.getRecordFlow())) {
			GeneralMethod.setRecordInfo(resDoctorRecruitInfo, false);
			return resDoctorRecruitInfoMapper.updateByPrimaryKeySelective(resDoctorRecruitInfo);
		} else {
			resDoctorRecruitInfo.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(resDoctorRecruitInfo, true);
			return resDoctorRecruitInfoMapper.insert(resDoctorRecruitInfo);
		}
	}

	/**
	 * @param recruitFlow
	 * @Department：研发部
	 * @Description 查询招录报名信息
	 * @Author Zjie
	 * @Date 0028, 2020年11月28日
	 */
	@Override
	public ResDoctorRecruitInfo findRecruitInfo(String recruitFlow) {
		ResDoctorRecruitInfoExample example = new ResDoctorRecruitInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecruitFlowEqualTo(recruitFlow);
		example.setOrderByClause("CREATE_TIME DESC");
		List<ResDoctorRecruitInfo> resDoctorRecruitInfos = resDoctorRecruitInfoMapper.selectByExample(example);
		if (resDoctorRecruitInfos != null && resDoctorRecruitInfos.size() > 0) {
			return resDoctorRecruitInfos.get(0);
		}
		return null;
	}

	@Override
	public List<JsResDoctorRecruitExt> resDoctorRecruitExtList1New(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String> orgFlowList, List<String> docTypeList, List<String> sessionNumbers) {
		Map<String, Object> doctorRecruitMap = new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		List<JsResDoctorRecruitExt> doctorRecruitList = jsResDoctorRecruitExtMapper.searchJsDoctorRecruitExtListNew(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<ResDoctorRecruit> searchResDoctorRecruitListNew(ResDoctorRecruit recruit, String orderByClause) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		com.pinde.sci.model.mo.ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(recruit.getDoctorFlow())) {
			criteria.andDoctorFlowEqualTo(recruit.getDoctorFlow());
		}
		if (StringUtil.isNotBlank(recruit.getCatSpeId())) {
			criteria.andCatSpeIdEqualTo(recruit.getCatSpeId());
		}
		if (StringUtil.isNotBlank(recruit.getAuditStatusId())) {
			criteria.andAuditStatusIdEqualTo(recruit.getAuditStatusId());
		}
		if (StringUtil.isNotBlank(recruit.getRecordStatus())) {
			criteria.andRecordStatusEqualTo(recruit.getRecordStatus());
		}
		if (StringUtil.isNotBlank(orderByClause)) {
			example.setOrderByClause(orderByClause);
		}
		if (StringUtil.isNotBlank(recruit.getRecruitFlow())) {
			criteria.andRecruitFlowEqualTo(recruit.getRecruitFlow());
		}
		if (StringUtil.isNotBlank(recruit.getDoctorStatusId())) {
			criteria.andDoctorStatusIdEqualTo(recruit.getDoctorStatusId());
		}
		if (StringUtil.isNotBlank(recruit.getRecruitYear())) {
			criteria.andRecruitYearEqualTo(recruit.getRecruitYear());
		}
		return doctorRecruitMapper.selectByExample(example);
	}

	@Override
	public List<JsResDoctorRecruitExt> searchResDoctorRecruitExtList1(ResDoctorRecruit resDoctorRecruit, SysUser sysUser, List<String> orgFlowList, List<String> docTypeList, List<String> sessionNumbers, String isJointOrg) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", sysUser);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		doctorRecruitMap.put("isJointOrg", isJointOrg);
		logger.info("invoke searchResDoctorRecruitExtList1 , doctorRecruitMap = {}", JSON.toJSONString(doctorRecruitMap));
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchResDoctorRecruitExtList(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> searchResDoctorRecruitExtList2(ResDoctorRecruit resDoctorRecruit, SysUser sysUser, List<String> orgFlowList, List<String> docTypeList, List<String> sessionNumbers, String isJointOrg) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", sysUser);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		doctorRecruitMap.put("isJointOrg", isJointOrg);
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchResDoctorRecruitExtList2(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsDoctorInfoExt> resDoctorInfoExtListNew(ResDoctorRecruit resDoctorRecruit,SysUser user,List<String>orgFlowList,List<String> docTypeList,List<String> sessionNumbers,String isJointOrg) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		doctorRecruitMap.put("docTypeList", docTypeList);
		doctorRecruitMap.put("sessionNumbers", sessionNumbers);
		doctorRecruitMap.put("isJointOrg", isJointOrg);
		List<JsDoctorInfoExt> doctorRecruitList=jsResDoctorRecruitExtMapper.resDoctorInfoExtListNew(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JsResDoctorRecruitExt> searchNotCertificateList(Map<String, Object> doctorRecruitMap) {
		List<JsResDoctorRecruitExt> doctorRecruitList=jsResDoctorRecruitExtMapper.searchNotCertificateList(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public int createCertificate(ResDoctorRecruit recruit,String tabTag) {
		String orgFlow = "";
//		List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(recruit.getOrgFlow());
//		//是协同基地 显示国家基地
//		if (!jointOrgs.isEmpty() && jointOrgs.size() > 0) {
//			orgFlow = jointOrgs.get(0).getOrgFlow();
//		}else{
//			orgFlow = recruit.getOrgFlow();
//		}
		if(StringUtil.isNotBlank(recruit.getJointOrgFlow())){
			orgFlow = recruit.getJointOrgFlow();
		}else{
			orgFlow = recruit.getOrgFlow();
		}
		//查询基地院长签名图片
		JsresSignExample example = new JsresSignExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andOrgFlowEqualTo(orgFlow).andAuditStatusIdEqualTo("Passed");
		List<JsresSign> signList = jsresSignMapper.selectByExample(example);
		if(null == signList || signList.size() == 0){
			return -1;
		}
		//证书编号
		Map<String,String> map = jsResDoctorRecruitExtMapper.searchCertificateNo(recruit.getRecruitFlow(),recruit.getOrgFlow(),recruit.getGraduationYear());
		recruit.setGraduationCertificateNo(map.get("CERTIFICATE_NUMBER"));
		recruit.setCertificateFlow(map.get("CERTIFICATE_FLOW"));
		recruit.setCertificateIssuingStatus("未发放");
		if("CountryCertificate".equals(tabTag)) {
			//证书样式  国家证书
			recruit.setGraduationCertificateType("country");
		}else{
			//省级证书
			recruit.setGraduationCertificateType("province");
		}
		recruit.setCertificateDate(DateUtil.getCurrDate());

//		else{
//			recruit.setProvinceCertificateNo(map.get("CERTIFICATE_NUMBER"));
//			recruit.setCertificateFlow(map.get("CERTIFICATE_FLOW"));
//			recruit.setProvinceCertificateStatus("未发放");
//			recruit.setProvinceCertificateDate(DateUtil.getCurrDate());
//		}
		return doctorRecruitMapper.updateByPrimaryKey(recruit);
	}

	@Override
	public List<ResDoctorRecruit> searchResDoctorRecruitListByFlows(List<String> recruitFlows) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andRecruitFlowIn(recruitFlows);
		return doctorRecruitMapper.selectByExample(example);
	}

	@Override
	public int findSignupCount(String doctorFlow, String year) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andRecruitYearEqualTo(year)
                .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
				.andAuditStatusIdNotEqualTo("NotPassed");
		return doctorRecruitMapper.countByExample(example);
	}

}
