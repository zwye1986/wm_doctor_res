package com.pinde.sci.biz.jszy.impl;


import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyGraduationBiz;
import com.pinde.sci.biz.jszy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.jszy.JszyResDoctorRecruitExtMapper;
import com.pinde.sci.dao.jszy.JszyResRecruitDoctorInfoExtMapper;
import com.pinde.sci.enums.jszy.*;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.enums.res.CertificateTypeEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.res.ResScoreTypeEnum;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.model.jszy.JszyDoctorInfoExt;
import com.pinde.sci.model.jszy.JszyDoctorInfoLogExt;
import com.pinde.sci.model.jszy.JszyResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JszyResDoctorRecruitBizImpl implements IJszyResDoctorRecruitBiz {
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private ResDoctorRecruitMapper doctorRecruitMapper;
    @Autowired
    private JszyResRecruitDoctorInfoExtMapper jszyResDoctorRecruitInfoExtMapper;
    @Autowired
    private JsresRecruitDocInfoMapper jsResDoctorRecruitMapper;
    @Autowired
    private JsresRecruitInfoMapper recruitInfoMapper;
    @Autowired
	private JszyResDoctorRecruitExtMapper jszyResDoctorRecruitExtMapper;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private SchRotationMapper rotationMapper;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private ResBaseMapper resBaseMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IJszyGraduationBiz graduationBiz;

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
     * 根据recruitFlow获取相对应的一条记录
     */
    @Override
    public JsresRecruitDocInfoWithBLOBs readResDoctorRecruitInfo(String recruitFlow) {
        if (StringUtil.isNotBlank(recruitFlow)) {
            return jsResDoctorRecruitMapper.selectByPrimaryKey(recruitFlow);
        }
        return null;
    }

	/**
	 * 查询当前机构下医师
	 */
	@Override
	public List<JszyResDoctorRecruitExt> resDoctorRecruitExtList(ResDoctorRecruit resDoctorRecruit, SysUser user, List<String>orgFlowList) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("orgFlowList", orgFlowList);
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<JszyResDoctorRecruitExt> resDoctorRecruitExtList(Map<String, Object> param) {
		return jszyResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(param);
	}

	@Override
	public List<JszyResDoctorRecruitExt> searchTrainInfoList(List<String>jointOrgFlowList,ResDoctorRecruit resDoctorRecruit,SysUser user,String flag) {
		Map<String, Object> doctorRecruitMap=new HashMap<String, Object>();
		doctorRecruitMap.put("resDoctorRecruit", resDoctorRecruit);
		doctorRecruitMap.put("user", user);
		doctorRecruitMap.put("derateFlag", flag);
		doctorRecruitMap.put("jointOrgFlowList", jointOrgFlowList);
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
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
	public List<ResDoctorRecruit> searchResDoctorRecruitList(ResDoctorRecruit recruit, String orderByClause) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria();
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
		return doctorRecruitMapper.selectByExample(example);
	}
    @Override
    public List<JsresRecruitDocInfo> searchResDoctorRecruitInfoList(ResDoctorRecruit recruit, String orderByClause) {
        JsresRecruitDocInfoExample example = new JsresRecruitDocInfoExample();
        JsresRecruitDocInfoExample.Criteria criteria = example.createCriteria();
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
        return jsResDoctorRecruitMapper.selectByExample(example);
    }

    @Override
	public int searchBasePassCount(ResDoctorRecruit recruit,List<String> orgFlowList) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("recruit", recruit);
		paramMap.put("orgFlowList", orgFlowList);
		return jszyResDoctorRecruitExtMapper.searchBasePassCount(paramMap);
	}
	
	@Override
	public int saveAuditRecruit(ResDoctorRecruitWithBLOBs recWithBLOBs){
		String auditStatusId =  recWithBLOBs.getAuditStatusId();
		if(StringUtil.isNotBlank(auditStatusId)){
			recWithBLOBs.setAuditStatusName(JszyResDoctorAuditStatusEnum.getNameById(auditStatusId));
		}
		String msgTitle = "培训信息审核结果";
		String msgContent = null;
		if(JszyResDoctorAuditStatusEnum.Passed.getId().equals(auditStatusId)){//审核通过
			msgContent = "您的培训信息审核通过。";
			
			String recruitFlow = recWithBLOBs.getRecruitFlow();
			String doctorFlow = recWithBLOBs.getDoctorFlow();
			ResDoctorRecruit recruit = null;
			if(StringUtil.isNotBlank(recruitFlow) && StringUtil.isNotBlank(doctorFlow)){
				recruit = readResDoctorRecruit(recruitFlow);
				ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
				if(recruit != null && doctor != null){
					doctor.setOrgFlow(recruit.getOrgFlow());
					doctor.setOrgName(recruit.getOrgName());
					doctor.setTrainingTypeId(recruit.getCatSpeId());
					doctor.setTrainingTypeName(recruit.getCatSpeName());
					doctor.setDoctorCategoryId(recruit.getCatSpeId());
					doctor.setDoctorCategoryName(recruit.getCatSpeName());
					doctor.setTrainingSpeId(recruit.getSpeId());
					doctor.setTrainingSpeName(recruit.getSpeName());
					doctor.setDoctorStatusId(recruit.getDoctorStatusId());
					doctor.setDoctorStatusName(recruit.getDoctorStatusName());
					doctor.setTrainingYears(recruit.getTrainYear());
					doctor.setDegreeCategoryId(recruit.getCurrDegreeCategoryId());
					doctor.setDegreeCategoryName(recruit.getCurrDegreeCategoryName());
					doctor.setGraduationYear(recruit.getGraduationYear());
					doctor.setSecondSpeId(recruit.getSecondSpeId());
					doctor.setSecondSpeName(recruit.getSecondSpeName());
					//回写届数
					doctor.setSessionNumber(recruit.getSessionNumber());
					resDoctorBiz.editDoctor(doctor, null);
					doctor=rotationBiz.updateDocRotation(doctor);
					resDoctorBiz.editDoctor(doctor, null);
				}
			}
		}else{//审核不通过
			msgContent = "您的培训信息审核不通过。";
		}
		int result = saveDoctorRecruit(recWithBLOBs);
		if(result==1 && JszyResDoctorAuditStatusEnum.Passed.getId().equals(auditStatusId))
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
	public List<JszyDoctorInfoExt> searchDoctorInfoResume(Map<String,Object> paramMap) {
		return jszyResDoctorRecruitExtMapper.searchDoctorInfoExts(paramMap);
	}
	
	public int searchDoctorNum(ResDoctorRecruit recruit){
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
		return doctorRecruitMapper.countByExample(example);
	}
	
	@Override
	public int searchCountByCondition(ResDoctorRecruitWithBLOBs recruitWithBLOBs,String flag){
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("recruit", recruitWithBLOBs);
		if(GlobalConstant.FLAG_Y.equals(flag)){
			paramMap.put("ProvFlag", flag);
		}
		if(GlobalConstant.FLAG_N.equals(flag)){
			paramMap.put("UrlFlag", flag);
		}
		return jszyResDoctorRecruitExtMapper.searchTrainInfoCount(paramMap);
	}
	
	@Override
	public int editResDoctorRecruit(ResDoctorRecruitWithBLOBs recWithBLOBs, ResDoctorRecruitWithBLOBs prevDocRec) {
		//医师状态
		if(StringUtil.isNotBlank(recWithBLOBs.getDoctorStatusId())){
			recWithBLOBs.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById(recWithBLOBs.getDoctorStatusId()));
		}else{
			recWithBLOBs.setDoctorStatusName("");
		}
		//医师走向
		if(StringUtil.isNotBlank(recWithBLOBs.getDoctorStrikeId())){
			recWithBLOBs.setDoctorStrikeName(DictTypeEnum.DoctorStrike.getDictNameById(recWithBLOBs.getDoctorStrikeId()));
		}else{
			recWithBLOBs.setDoctorStrikeName("");
		}
		if(StringUtil.isNotBlank(recWithBLOBs.getCurrDegreeCategoryId())){
			recWithBLOBs.setCurrDegreeCategoryName(JszyResDegreeCategoryEnum.getNameById(recWithBLOBs.getCurrDegreeCategoryId()));
		}
		recWithBLOBs.setAuditStatusId(JszyResDoctorAuditStatusEnum.NotSubmit.getId());
		recWithBLOBs.setAuditStatusName(JszyResDoctorAuditStatusEnum.NotSubmit.getName());
		String doctorFlow = recWithBLOBs.getDoctorFlow();
//		if(StringUtil.isNotBlank(recWithBLOBs.getSecondSpeId())) {
//			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
//			if (doctor != null) {
//				if (StringUtil.isBlank(doctor.getSecondSpeId()) || !recWithBLOBs.getSecondSpeId().equals(doctor.getSecondSpeId())) {
//					doctor.setSecondSpeId(recWithBLOBs.getSecondSpeId());
//					doctor.setSecondSpeName(recWithBLOBs.getSecondSpeName());
//					resDoctorBiz.editDoctor(doctor);
//				}
//			}
//		}
		//如果培训类别为中医全科则去掉二级专业
		if(JszyTrainCategoryEnum.TCMAssiGeneral.getId().equals(recWithBLOBs.getCatSpeId()))
		{
			ResDoctorRecruit r=readResDoctorRecruit(recWithBLOBs.getRecruitFlow());
			if(r!=null)
			{
				r.setSecondSpeName("");
				r.setSecondSpeId("");
				updateAllRecruit(r);
			}
		}

		//结业审核年份
		int year=0;
		if(StringUtil.isNotBlank(recWithBLOBs.getSessionNumber())&&StringUtil.isNotBlank(recWithBLOBs.getTrainYear())){
			int num=0;
			if(JszyResTrainYearEnum.OneYear.getId().equals(recWithBLOBs.getTrainYear())){
				num=1;
			}
			if(JszyResTrainYearEnum.TwoYear.getId().equals(recWithBLOBs.getTrainYear())){
				num=2;
			}
			if(JszyResTrainYearEnum.ThreeYear.getId().equals(recWithBLOBs.getTrainYear())){
				num=3;
			}
			year = Integer.parseInt(recWithBLOBs.getSessionNumber())+num;
			recWithBLOBs.setGraduationYear(year+"");
		}
		//其中已审核通过
		ResDoctorRecruit exitRec = new ResDoctorRecruit();
		exitRec.setDoctorFlow(doctorFlow);
		exitRec.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
		exitRec.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
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
//		//当前为二阶段
//		if(JszyTrainCategoryEnum.WMSecond.getId().equals(recWithBLOBs.getCatSpeId()) ){
//			if(passedRecruitList.isEmpty()){//无审核通过
//				ResDoctorRecruitWithBLOBs addWMFirst = new ResDoctorRecruitWithBLOBs();
//				addWMFirst.setCompleteFileUrl(recWithBLOBs.getCompleteFileUrl());
//				addWMFirst.setCompleteCertNo(recWithBLOBs.getCompleteCertNo());
//				//查询状态为N的记录
//				ResDoctorRecruit exitN = new ResDoctorRecruit();
//				exitN.setDoctorFlow(doctorFlow);
//				exitN.setCatSpeId(JszyTrainCategoryEnum.WMFirst.getId());
//				exitN.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
//				List<ResDoctorRecruit> exitNList = searchResDoctorRecruitList(exitN, null);
//				if(exitNList != null && !exitNList.isEmpty()){
//					exitN = exitNList.get(0);
//					addWMFirst.setRecruitFlow(exitN.getRecruitFlow());
//					addWMFirst.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
//					saveDoctorRecruit(addWMFirst);
//				}else{//新增
//					addWMFirst.setDoctorFlow(doctorFlow);
//					addWMFirst.setCatSpeId(JszyTrainCategoryEnum.WMFirst.getId());
//					addWMFirst.setCatSpeName(JszyTrainCategoryEnum.WMFirst.getName());
//					addWMFirst.setAuditStatusId(JszyResDoctorAuditStatusEnum.Passed.getId());
//					addWMFirst.setAuditStatusName(JszyResDoctorAuditStatusEnum.Passed.getName());
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
//			}
//		}else{//非二阶段
			if(passedRecruitList.size() == 1 && firstIsWMSecond){//首条记录为二阶段  修改为非二阶段  ==>删除自动生成的一阶段
				ResDoctorRecruitWithBLOBs deleWMFirst = new ResDoctorRecruitWithBLOBs();
				deleWMFirst.setRecruitFlow(passedRecruitList.get(0).getRecruitFlow());
				deleWMFirst.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				saveDoctorRecruit(deleWMFirst);
			}
//		}
		//上一阶段：结业证书、证书编号
		if(StringUtil.isNotBlank(prevDocRec.getRecruitFlow())){
			saveDoctorRecruit(prevDocRec);
		}
		return saveDoctorRecruit(recWithBLOBs);
	}

	private void updateAllRecruit(ResDoctorRecruit r) {
		if(StringUtil.isNotBlank(r.getRecruitFlow()))
		{
			doctorRecruitMapper.updateByPrimaryKey(r);
		}
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
				if(JszyResTrainYearEnum.OneYear.getId().equals(recruitWithBLOBs.getTrainYear())){
					year=1;
				}
				if(JszyResTrainYearEnum.TwoYear.getId().equals(recruitWithBLOBs.getTrainYear())){
					year=2;
				}
				if(JszyResTrainYearEnum.ThreeYear.getId().equals(recruitWithBLOBs.getTrainYear())){
					year=3;
				}
				recruitWithBLOBs.setGraduationYear((Integer.parseInt(recruitWithBLOBs.getSessionNumber())+year)+"");
			}
			if(StringUtil.isNotBlank(recruitWithBLOBs.getCurrDegreeCategoryId())){
				recruitWithBLOBs.setCurrDegreeCategoryName(JszyResDegreeCategoryEnum.getNameById(recruitWithBLOBs.getCurrDegreeCategoryId()));
			}
			
			saveDoctorRecruit(recruitWithBLOBs);
			return resDoctorBiz.editDoctor(doctor, null);
		}
		return GlobalConstant.ZERO_LINE;
	}
	@Override
	public ResDoctorRecruitWithBLOBs readRecruit(String recruitFlow) {
		if (StringUtil.isNotBlank(recruitFlow)) {
			return doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
		}
		return null;
	}

	@Override
	public List<JszyResDoctorRecruitExt> searchDoctorInfoExts(Map<String, Object> doctorRecruitMap) {
		return jszyResDoctorRecruitExtMapper.searchJsDoctorRecruitExtList(doctorRecruitMap);
	}
	@Override
	public List<JszyResDoctorRecruitExt>  searchDoctorScoreInfoExts(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList)
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
		doctorRecruitMap.put("typeId", ResScoreTypeEnum.TheoryScore.getId());
		doctorRecruitMap.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchJsDoctorScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
	}@Override
	public List<JszyResDoctorRecruitExt>  searchDoctorSkillAndTheoryScoreExts( ResDoctorRecruit resDoctorRecruit,ResDoctor doctor, SysUser  user,SysOrg  sysOrg,List<String>  jointOrgFlowList,  String flag , String  scoreYear, String  isHege,String  skillIsHege,List<String> docTypeList)
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
		doctorRecruitMap.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));
		if(StringUtil.isNotBlank(skillIsHege))
		{
			if(GlobalConstant.FLAG_Y.equals(skillIsHege))
			{
				skillIsHege="1";
			}
			if(GlobalConstant.FLAG_N.equals(skillIsHege))
			{
				skillIsHege="0";
			}
		}
		doctorRecruitMap.put("skillIsHege",skillIsHege);
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchDoctorSkillAndTheoryScoreExts(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JszyResDoctorRecruitExt> searchDoctorSkillScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList)
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
		if(StringUtil.isNotBlank(isHege))
		{
			if(GlobalConstant.FLAG_Y.equals(isHege))
			{
				isHege="1";
			}
			if(GlobalConstant.FLAG_N.equals(isHege))
			{
				isHege="0";
			}
		}
		doctorRecruitMap.put("isHege",isHege);
		doctorRecruitMap.put("typeId", ResScoreTypeEnum.SkillScore.getId());
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchJsDoctorSkillScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
	}	@Override
	public List<JszyResDoctorRecruitExt> searchDoctorPublicScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String notAllQualified,String allQualified, List<String> docTypeList)
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
			if(GlobalConstant.FLAG_Y.equals(notAllQualified))
			{
				notAllQualified="1";
			}
			if(GlobalConstant.FLAG_N.equals(notAllQualified))
			{
				notAllQualified="0";
			}
		}
		doctorRecruitMap.put("notAllQualified",notAllQualified);//非全科是否合格
		doctorRecruitMap.put("hegeScore", InitConfig.getSysCfg("res_theoryScore"));
		doctorRecruitMap.put("allQualified",allQualified);//全科是否合格
		doctorRecruitMap.put("typeId", ResScoreTypeEnum.PublicScore.getId());
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchJsDoctorPublicScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public List<ResDoctorRecruit> readDoctorRecruits(ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
		return doctorRecruitMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctorRecruitWithBLOBs> searchRecruitWithBLOBs(ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
		recruitExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
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
		
		return JszyBaseStatusEnum.Passed.getId().equals(recruit.getAuditStatusId());
	}

	@Override
	public List<Map<String, Object>> searchJointOrgList() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return jszyResDoctorRecruitExtMapper.searchJointOrgList(paramMap);
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
		List<Map<String, Object>> doctorRecruitList=jszyResDoctorRecruitExtMapper.doctorScoreQuery(doctorRecruitMap);
		return doctorRecruitList;
	}

	@Override
	public int saveSecondSpe(ResDoctorRecruitWithBLOBs dBloBs) {
		if(dBloBs!=null&&StringUtil.isNotBlank(dBloBs.getRecruitFlow()))
		{
			int count=0;
			ResDoctorRecruitWithBLOBs doctorRecruitWb=readRecruit(dBloBs.getRecruitFlow());
			doctorRecruitWb.setProveFileUrl(dBloBs.getProveFileUrl());
			doctorRecruitWb.setProvRemark(dBloBs.getProvRemark());
			doctorRecruitWb.setSecondSpeId(dBloBs.getSecondSpeId());
			doctorRecruitWb.setSecondSpeName(dBloBs.getSecondSpeName());
			count+=saveDoctorRecruit(doctorRecruitWb);
			ResDoctor doctor=resDoctorBiz.readDoctor(doctorRecruitWb.getDoctorFlow());
			if(doctor!=null)
			{
				doctor.setSecondSpeId(dBloBs.getSecondSpeId());
				doctor.setSecondSpeName(dBloBs.getSecondSpeName());

				String trainingType= RecDocCategoryEnum.ChineseMedicine.getId();
				SchRotation rotation2 = null;
				if(StringUtil.isNotBlank(trainingType) && StringUtil.isNotBlank(doctor.getSecondSpeId())){
					SchRotationExample example = new SchRotationExample();
					example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
							.andSpeIdEqualTo(doctor.getSecondSpeId()).andPublishFlagEqualTo(GlobalConstant.RECORD_STATUS_Y)
							.andDoctorCategoryIdEqualTo(trainingType);
					example.setOrderByClause("create_time desc");

					List<SchRotation> rotations = rotationMapper.selectByExample(example);
					if(rotations!=null && rotations.size()>0){
						rotation2 = rotations.get(0);
					}
				}
				if(rotation2!=null){
					doctor.setSecondRotationFlow(rotation2.getRotationFlow());
					doctor.setSecondRotationName(rotation2.getRotationName());
				}else{
					doctor.setSecondRotationFlow("");
					doctor.setSecondRotationName("");
				}
				count+=resDoctorBiz.editDoctor(doctor);
			}

			if(count>0)
				return 1;
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> zlxytj(Map<String,Object> param) {
		return jszyResDoctorRecruitExtMapper.zlxytj(param);
	}

	@Override
	public List<Map<String, Object>> zlxytj2(Map<String, Object> param) {
		return jszyResDoctorRecruitExtMapper.zlxytj2(param);
	}

	@Override
	public List<Map<String, Object>> zlxytjJoint(Map<String, Object> param) {
		return jszyResDoctorRecruitExtMapper.zlxytjJoint(param);
	}

	@Override
	public List<Map<String, Object>> zlxytj3(Map<String, Object> param) {
		return jszyResDoctorRecruitExtMapper.zlxytj3(param);
	}
	@Override
	public List<Map<String, Object>> zlxytj4(Map<String, Object> param) {
		return jszyResDoctorRecruitExtMapper.zlxytj4(param);
	}

    @Override
    public List<Map<String, Object>> zlxytj5(Map<String, Object> param) {
        return jszyResDoctorRecruitExtMapper.zlxytj5(param);
    }

    @Override
	public void exportForDetailLog(List<JszyDoctorInfoLogExt> doctorInfoExts, HttpServletResponse response) throws Exception {
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
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 19));//合并单元格
		HSSFCell cell = rowDep.createCell(1);
		cell.setCellStyle(stylevwc);
		cell.setCellValue("学生基本信息");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 20, 35));
		HSSFCell cellTwo = rowDep.createCell(20);
		cellTwo.setCellStyle(styleCenter);
		cellTwo.setCellValue("学历信息");
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 36, 46));
		HSSFCell cellThree = rowDep.createCell(36);
		cellThree.setCellStyle(styleCenter);
		cellThree.setCellValue("单位信息");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 19, 22));//合并单元格
		HSSFCell cellFour = rowTwo.createCell(19);
		cellFour.setCellStyle(styleCenter);
		cellFour.setCellValue("本科阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 23, 28));//合并单元格
		HSSFCell cellFive = rowTwo.createCell(23);
		cellFive.setCellStyle(styleCenter);
		cellFive.setCellValue("硕士研究生阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 30, 35));//合并单元格
		HSSFCell cellSix = rowTwo.createCell(30);
		cellSix.setCellStyle(styleCenter);
		cellSix.setCellValue("博士研究生阶段");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 36, 37));//合并单元格
		HSSFCell cellSeven = rowTwo.createCell(36);
		cellSeven.setCellStyle(styleCenter);
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 38, 40));//合并单元格
		HSSFCell cellEight = rowTwo.createCell(38);
		cellEight.setCellStyle(styleCenter);
		cellEight.setCellValue("在“医疗卫生机构”选择“医院”则填");
		HSSFCell cellNine = rowTwo.createCell(10);
		cellNine.setCellStyle(styleCenter);
		cellNine.setCellValue("在“医疗卫生机构”选择“基层医疗卫生机构”则填");
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 42, 44));//合并单元格
		HSSFCell cellTen = rowTwo.createCell(42);
		cellTen.setCellStyle(styleCenter);

		HSSFRow rowThree = sheet.createRow(2);//第三行
		String[] titles = new String[]{
				"基地名称",
				"姓名",
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
		if (doctorInfoExts != null && !doctorInfoExts.isEmpty()) {
			for (int i = 0; i < doctorInfoExts.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行

				JszyDoctorInfoLogExt doctorInfoExt = doctorInfoExts.get(i);
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

				String age ="";
				if(doctorInfoExt.getSysUser()!=null&&StringUtil.isNotBlank(doctorInfoExt.getSysUser().getUserBirthday())) {
					age = (Integer.parseInt(DateUtil.getCurrDate().substring(0, 4)) - Integer.parseInt(doctorInfoExt.getSysUser().getUserBirthday().substring(0, 4))) + "";
				}
				SysUser sysUser = doctorInfoExt.getSysUser();
				ResDoctor doctor = doctorInfoExt.getResDoctor();

				String CretType = "";
				if (!CertificateTypeEnum.Shenfenzheng.getId().equals(sysUser.getCretTypeId())) {
					CretType = "其他";
				} else {
					CretType = "身份证";
				}
				String isYearGraduate = "";
				if (GlobalConstant.FLAG_Y.equals(doctor.getIsYearGraduate())) {
					isYearGraduate = "应届";
				} else {
					isYearGraduate = "往届";
				}

				String recruitDate = doctorInfoExt.getRecruitDate().substring(0, 4) + "年" + doctorInfoExt.getRecruitDate().substring(5, 7) + "月";
				//解析xml
				PubUserResume userResume = doctorInfoExt.getUserResume();

				BaseUserResumeExtInfoForm userResumeExt = null;
				userResumeExt = userResumeBiz.converyToJavaBean(userResume.getUserResume(), BaseUserResumeExtInfoForm.class);

				//是否是执业医师和编号
				String qualificationFlag = "";
				if (StringUtil.isNotBlank(userResumeExt.getQualificationMaterialCode())) {
					qualificationFlag = "是";
				} else {
					userResumeExt.setQualificationMaterialCode("");
				}
				//研究生
				String masterFlag = "";
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsMaster()) || StringUtil.isBlank(userResumeExt.getIsMaster())) {
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
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsDoctor()) || StringUtil.isBlank(userResumeExt.getIsDoctor())) {
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
				if (JszyResDocTypeEnum.Company.getId().equals(doctor.getDoctorTypeId())) {
					ResBase resBase = resBaseMapper.selectByPrimaryKey(doctor.getOrgFlow());
					if (resBase != null && jointFlag.equals("是")) {
						property = resBase.getBaseGradeName();
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || !"1".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setHospitalAttrName("");
						userResumeExt.setHospitalCategoryName("");
						userResumeExt.setBaseAttributeName("");
					}
					if (StringUtil.isBlank(userResumeExt.getMedicalHeaithOrgId()) || "3".equals(userResumeExt.getMedicalHeaithOrgId())) {
						userResumeExt.setBasicHealthOrgName("");
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
				if (GlobalConstant.FLAG_Y.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee = "是";
				}
				if (GlobalConstant.FLAG_N.equals(userResumeExt.getIsGeneralOrderOrientationTrainee())) {
					isGeneralOrderOrientationTrainee="否";
				}
				//规培年限
				String trainYear = "";
				trainYear = doctorInfoExt.getTrainYear();
				switch (trainYear){
					case "OneYear":{trainYear="一年";break;}
					case "TwoYear":{trainYear="两年";break;}
					case "ThreeYear":{trainYear="三年";break;}
				}
				dataList = new String[]{
						orgName,
						sysUser.getUserName(),
						sysUser.getSexName(),
						age,
						CretType,
						sysUser.getIdNo(),
						sysUser.getNationName(),
						sysUser.getUserPhone(),
						sysUser.getUserEmail(),
						sysUser.getUserQq(),
						isYearGraduate,
						doctor.getTrainingSpeName(),
						qualificationFlag,
						userResumeExt.getQualificationMaterialCode(),
						doctorInfoExt.getSessionNumber(),
						trainYear,
						recruitDate,

						"在培",//医师状态
						"",

						doctor.getDoctorTypeName(),

						userResumeExt.getGraduatedName(),
						graduationTime,
						userResumeExt.getSpecialized(),
						sysUser.getEducationName(),
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
						userResumeExt.getMedicalHeaithOrgName(),

						userResumeExt.getHospitalAttrName(),
						userResumeExt.getHospitalCategoryName(),
						userResumeExt.getBaseAttributeName(),

						userResumeExt.getBasicHealthOrgName(),

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
	public List<JszyResDoctorRecruitExt> searchDoctorCertificateList(Map<String, Object> doctorRecruitMap) {
		List<JszyResDoctorRecruitExt> doctorRecruitList=jszyResDoctorRecruitExtMapper.searchDoctorCertificateList(doctorRecruitMap);
		return doctorRecruitList;
	}

	/**
	 * 加上同步锁机制，以防多次导入时，引起证书编号重复问题
	 * @param file
	 * @return
	 */
	@Override
	public synchronized ExcelUtile importDoctorCertificateNoFromExcel2(MultipartFile file) {
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
					"sessionNumber",
					"graduationCertificateNo"
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
						recruit.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById("21"));
						ResDoctor resDoctor=resDoctorBiz.readDoctor(recruit.getDoctorFlow());
						if(resDoctor!=null) {
							resDoctor.setDoctorStatusId("21");
							resDoctor.setDoctorStatusName(DictTypeEnum.DoctorStatus.getDictNameById("21"));
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
					data.put("recruit",recruit);
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
	public synchronized ExcelUtile importGraduationPeople(MultipartFile file) {
		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));

			final String[] keys = {
					"结业年份:graduationYear",
					"姓名:doctorName",
					"证件号码:idNo",
					"基地编码:orgCode",
					"国家基地:nationalBaseName",
					"培训基地:trainingBaseName",
					"所在单位:company",
					"培训专业:trainingTypeName",
					"实际培训开始时间:trainingStartTime",
					"实际培训结束时间:trainingEndTime",
					"理论考核通过年度:passTheoryAssessmentYear",
					"年度考核是否通过:ifPassAnnualAssessment",
					"培训时间是否完成:ifCompleteTrainingTime",
					"登记手册是否完整:ifCompleteRegisterManual",
					"跟师考核是否合格:ifPassDiscipleAssessment"
			};

			final List<String> nums = new ArrayList<>();
			return ExcelUtile.importDataExcel2(ResDoctorGraduationInfo.class, 1, wb, keys, new IExcelUtil<ResDoctorGraduationInfo>() {
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<ResDoctorGraduationInfo> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					for(int i=0;i<datas.size();i++)
					{
						count+=graduationBiz.editGraduationInfo(datas.get(i));
					}
					eu.put("code",code);
					eu.put("count",count);
				}
				@Override
				public String checkExcelData(ResDoctorGraduationInfo data, ExcelUtile eu) {
					String sheetName=(String)eu.get("SheetName");
					if(sheetName==null||!"GraduationCertificate".equals(sheetName))
					{
						return errorMsg(eu,"请使用系统提供的结业学员信息模板！！");
					}
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					SysUser u=null;
					ResDoctor resdoctor=null;
					if(StringUtil.isBlank(data.getGraduationYear()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行结业年份为空，请确认后提交！！");
					}else{
						if (!data.getGraduationYear().matches("^[1-9][0-9]{3}$")) {
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行结业年份格式有误，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(data.getDoctorName()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行姓名为空，请确认后提交！！");
					}
					if(StringUtil.isBlank(data.getIdNo())) {
						return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行身份证号为空，请确认后提交！！");
					}else{
						ResDoctorGraduationInfo old=graduationBiz.findGraduationInfoByIdNo(data.getIdNo());
						if(old!=null)
						{
							return errorMsg(eu, "导入文件第" + (rowNum + 1) + "行学员已导入，请确认后提交！！");
						}
						if(nums.contains(data.getIdNo()))
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行身份证号存在重复，请确认后提交！！");
						}
						nums.add(data.getIdNo());

						u=userBiz.findByIdNo(data.getIdNo());
						if(null==u)
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行证件号码为【"+data.getIdNo()+"】学员信息不存在，请确认后提交！！");
						}
						data.setDoctorFlow(u.getUserFlow());
						data.setDoctorName(u.getUserName());
						resdoctor=resDoctorBiz.findByFlow(u.getUserFlow());
						if(resdoctor==null)
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行证件号码为【"+data.getIdNo()+"】学员医师信息不存在，请确认后提交！！");
						}
						if(StringUtil.isBlank(resdoctor.getSessionNumber()))
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行证件号码为【"+data.getIdNo()+"】学员医师年级信息不存在，请确认后提交！！");
						}
						data.setSessionNumber(resdoctor.getSessionNumber());
						data.setTrainingSpeId(resdoctor.getTrainingSpeId());
						data.setTrainingSpeName(resdoctor.getTrainingSpeName());
						data.setDoctorTypeId(resdoctor.getDoctorTypeId());
						data.setDoctorTypeName(resdoctor.getDoctorTypeName());
					}
					if(StringUtil.isBlank(data.getOrgCode()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行基地编码为空，请确认后提交！！");
					}else{
						SysOrg org=orgBiz.readSysOrgByCode(data.getOrgCode());
						if(org==null)
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行基地编码与系统中不匹配，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(data.getNationalBaseName()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行国家基地为空，请确认后提交！！");
					}else{
						SysOrg org=orgBiz.readSysOrgByName(data.getNationalBaseName());
						if(org==null)
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行国家基地在系统中不存在，请确认后提交！！");
						}
						if(!data.getOrgCode().equals(org.getOrgCode()))
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行基地代码与国家基地在系统中不匹配，请确认后提交！！");
						}
						data.setNationalBaseFlow(org.getOrgFlow());
					}
					if(StringUtil.isBlank(data.getTrainingBaseName()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行培训基地为空，请确认后提交！！");
					}else{
						SysOrg org=orgBiz.readSysOrgByName(data.getTrainingBaseName());
						if(org==null)
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行培训基地在系统中不存在，请确认后提交！！");
						}
						data.setTrainingBaseFlow(org.getOrgFlow());
					}
					if(StringUtil.isBlank(data.getCompany()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行所在单位为空，请确认后提交！！");
					}
					if(StringUtil.isBlank(data.getTrainingTypeName()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行培训专业为空，请确认后提交！！");
					}else{
						if(!"中医全科".equals(data.getTrainingTypeName())&&!"中医".equals(data.getTrainingTypeName())&&!"中医助理全科".equals(data.getTrainingTypeName()))
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行培训专业只能是【中医】或【中医全科】或【中医助理全科】，请确认后提交！！");
						}
						if("中医".equals(data.getTrainingTypeName()))
						{
							data.setTrainingTypeId(RecDocCategoryEnum.ChineseMedicine.getId());
						}
						if("中医助理全科".equals(data.getTrainingTypeName()))
						{
							data.setTrainingTypeId(RecDocCategoryEnum.TCMAssiGeneral.getId());
						}
						if("中医全科".equals(data.getTrainingTypeName()))
						{
							data.setTrainingTypeId(RecDocCategoryEnum.TCMGeneral.getId());
						}
					}

					if(StringUtil.isBlank(data.getTrainingStartTime()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行实际培训开始时间为空，请确认后提交！！");
					}else{
						try
						{
							SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy年MM月");
							dateFormat2.parse(data.getTrainingStartTime());
						}
						catch (Exception e)
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行实际培训开始时间格式有误，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(data.getTrainingEndTime()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行实际培训结束时间为空，请确认后提交！！");
					}else{
						try
						{
							SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月");
							dateFormat.parse(data.getTrainingEndTime());
						}
						catch (Exception e)
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行实际培训结束时间格式有误，请确认后提交！！");
						}
					}
					if(data.getTrainingEndTime().compareTo(data.getTrainingStartTime())<0)
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行实际培训开始时间大于结束时间，请确认后提交！！");
					}
					if(StringUtil.isBlank(data.getPassTheoryAssessmentYear()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行理论考核通过年份为空，请确认后提交！！");
					}else{
						if (!data.getPassTheoryAssessmentYear().matches("^[1-9][0-9]{3}$")) {
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行理论考核通过年份格式有误，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(data.getIfPassAnnualAssessment()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行年度考核是否通过为空，请确认后提交！！");
					}else{
						if(!"是".equals(data.getIfPassAnnualAssessment())&&!"否".equals(data.getIfPassAnnualAssessment()))
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行年度考核是否通过只能是【是】或【否】，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(data.getIfCompleteTrainingTime()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行培训时间是否完成为空，请确认后提交！！");
					}else{
						if(!"是".equals(data.getIfCompleteTrainingTime())&&!"否".equals(data.getIfCompleteTrainingTime()))
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行培训时间是否完成只能是【是】或【否】，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(data.getIfCompleteRegisterManual()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行登记手册是否完成为空，请确认后提交！！");
					}else{
						if(!"是".equals(data.getIfCompleteRegisterManual())&&!"否".equals(data.getIfCompleteRegisterManual()))
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行登记手册是否完成只能是【是】或【否】，请确认后提交！！");
						}
					}
					if(StringUtil.isBlank(data.getIfPassDiscipleAssessment()))
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行跟师考核是否完成为空，请确认后提交！！");
					}else{
						if(!"是".equals(data.getIfPassDiscipleAssessment())&&!"否".equals(data.getIfPassDiscipleAssessment()))
						{
							return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行跟师考核是否完成只能是【是】或【否】，请确认后提交！！");
						}
					}
					ResDoctorRecruit search=new ResDoctorRecruit();
					search.setAuditStatusId(AuditStatusEnum.Passed.getId());
					search.setDoctorFlow(u.getUserFlow());
					search.setSessionNumber(resdoctor.getSessionNumber());
					List<ResDoctorRecruitWithBLOBs> recruits=readDoctorRecruitBlobs(search);
					if(null==recruits||recruits.size()<=0)
					{
						return errorMsg(eu,"导入文件第" + (rowNum + 1) + "行学员未添加过培训记录，请确认后提交！！");
					}
					data.setRecruitFlow(recruits.get(0).getRecruitFlow());
					data.setCurrentAuditStatusId(JszyBaseStatusEnum.Auditing.getId());
					data.setCurrentAuditStatusName("待基地确认");
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
	public List<ResDoctorRecruitWithBLOBs> readDoctorRecruitBlobs(ResDoctorRecruit recruit) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		ResDoctorRecruitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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

	private String errorMsg(ExcelUtile eu, String msg) {
		eu.put("count", 0);
		eu.put("code", "1");
		eu.put("msg", msg);
		return ExcelUtile.RETURN;
	}

    @Override
    public List<Map<String,Object>> zpxytj(Map<String, Object> param){
        return jszyResDoctorRecruitExtMapper.zpxytj(param);
    }
    @Override
    public List<Map<String,Object>> zpxytjChanges(Map<String, Object> param){
        return jszyResDoctorRecruitExtMapper.zpxytjChanges(param);
    }
    @Override
    public List<Map<String,Object>> zpxytjChangesDetail(Map<String, Object> param){
        return jszyResDoctorRecruitExtMapper.zpxytjChangesDetail(param);
    }
}
