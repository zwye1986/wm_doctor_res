package com.pinde.sci.biz.hbres.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.hbres.IHbResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IRecruitCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResOrgSpeAssignBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.ResDoctorRecruitMapper;
import com.pinde.sci.dao.base.ResRecruitRegisterMapper;
import com.pinde.sci.dao.hbres.HbresDoctorRecruitExtMapper;
import com.pinde.sci.dao.hbres.HbresResOrgSpeAssignExtMapper;
import com.pinde.sci.dao.res.ResDoctorRecruitExtMapper;
import com.pinde.sci.enums.res.RecruitTypeEnum;
import com.pinde.sci.enums.res.ResScoreTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.hbres.ResDoctorTrainingSpeForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorRecruitExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class HbResDoctorRecruitBizImpl implements IHbResDoctorRecruitBiz {
	@Autowired
	HbresDoctorRecruitExtMapper hbresDoctorRecruitExtMapper;
	@Autowired
	ResDoctorRecruitExtMapper doctorRecruitExtMapper;
	@Autowired
	private ResDoctorRecruitMapper doctorRecruitMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResOrgSpeAssignBiz speAssignBiz;
	@Autowired
	private IRecruitCfgBiz recruitCfgBiz;
	@Autowired
	private IResDoctorRecruitBiz resDoctorRecruitBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private HbresResOrgSpeAssignExtMapper resOrgSpeAssignExtMapper;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ResRecruitRegisterMapper recruitRegisterMapper;

	@Override
	public List<SysDict> searchTrainSpeList(Map<String , Object> paramMap){
		return doctorRecruitExtMapper.searchTrainSpeList(paramMap);
	}

//	@Override
//	public List<ResDoctorRecruitExt> searchDoctorRecruitExt(Map<String, Object> paramMap) {
//		return doctorRecruitExtMapper.searchDoctorRecruitExt(paramMap);
//	}

	@Override
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
	public void noticeRetestBatch(String[] recruitFlows,String retestNotice){
		if(recruitFlows!=null && recruitFlows.length>0){
			for(String recruitFlow:recruitFlows){
				resDoctorRecruitBiz.noticeRetest(recruitFlow, retestNotice);
			}
		}
	}

	@Override
	public List<ResDoctorTrainingSpeForm> searchRegisterStatistics(Map<String, Object> paramMap) {
		return hbresDoctorRecruitExtMapper.searchRegisterStatistics(paramMap);
	}

//	@Override
//	public String searchNoticeDoctorNum(Map<String, Object> paramMap) {
//		return doctorRecruitExtMapper.searchNoticeDoctorNum(paramMap);
//	}

	@Override
	public List<ResDoctorRecruitExt> selectDoctorRecruitExt(Map<String, Object> paramMap) {
		return doctorRecruitExtMapper.selectDoctorRecruitExt(paramMap);
	}

//	@Override
//	public	int editRecruitUnSelective(ResDoctorRecruit recruit){
//		if(recruit!=null){
//			if(StringUtil.isNotBlank(recruit.getDoctorFlow())){
//				GeneralMethod.setRecordInfo(recruit, false);
//				return doctorRecruitMapper.updateByPrimaryKey(recruit);
//			}else{
//				recruit.setDoctorFlow(PkUtil.getUUID());
//				GeneralMethod.setRecordInfo(recruit, true);
//				return doctorRecruitMapper.insert(recruit);
//			}
//		}
//		return GlobalConstant.ZERO_LINE;
//	}

//	@Override
//	public List<ResDoctorRecruitExt> readDoctorRecruitExt(Map<String, Object> paramMap) {
//		return doctorRecruitExtMapper.readDoctorRecruitExt(paramMap);
//	}

	@Override
	public void noticeRecruit(String admitNotice,String[] recruitFlows,String [] adminSwapFlags){
		String content = admitNotice;
		if (recruitFlows != null && recruitFlows.length>0) {
			ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
			recruit.setRecruitFlag(GlobalConstant.FLAG_Y);
			recruit.setAdmitFlag(GlobalConstant.FLAG_Y);
			recruit.setAdmitNotice(admitNotice);
			for (int i=0;i<recruitFlows.length;i++) {
				if(null != adminSwapFlags && adminSwapFlags.length>0 && adminSwapFlags[i].equals(GlobalConstant.FLAG_Y)){
					recruit.setConfirmFlag(GlobalConstant.FLAG_Y);
				}
				recruit.setRecruitFlow(recruitFlows[i]);
				resDoctorRecruitBiz.editDoctorRecruit(recruit);

				ResDoctorRecruit exitRecruit = resDoctorRecruitBiz.readResDoctorRecruit(recruitFlows[i]);
				SysUser exitUser = userBiz.readSysUser(exitRecruit.getDoctorFlow());
				String title = InitConfig.getSysCfg("res_admit_notice_email_title");
				this.msgBiz.addEmailMsg(exitUser.getUserEmail() , title, content);
			}
		}
	}

	@Override
	public void swapRecruit(String recruitFlow, String speFlow, String swapNotice,String adminSwapFlag,String trainYear){
		ResDoctorRecruitWithBLOBs recruit = new ResDoctorRecruitWithBLOBs();
		recruit.setRecruitFlow(recruitFlow);
		recruit.setRecruitFlag(GlobalConstant.FLAG_N);
		ResOrgSpeAssign spe = this.speAssignBiz.findSpeAssignByFlow(speFlow);
		recruit.setSwapSpeId(spe.getSpeId());
		recruit.setSwapSpeName(spe.getSpeName());
		resDoctorRecruitBiz.editDoctorRecruit(recruit);

		recruit = this.doctorRecruitMapper.selectByPrimaryKey(recruitFlow);
		ResDoctorRecruitWithBLOBs swapRecruit = new ResDoctorRecruitWithBLOBs();
		swapRecruit.setDoctorFlow(recruit.getDoctorFlow());
		swapRecruit.setOrgFlow(recruit.getOrgFlow());
		swapRecruit.setOrgName(recruit.getOrgName());
		swapRecruit.setSpeId(spe.getSpeId());
		swapRecruit.setSpeName(spe.getSpeName());
		swapRecruit.setSwapFlag(recruit.getSwapFlag());
		swapRecruit.setRecruitTypeId(RecruitTypeEnum.Swap.getId());
		swapRecruit.setRecruitTypeName(RecruitTypeEnum.Swap.getName());
		swapRecruit.setRecruitYear(recruit.getRecruitYear());
		swapRecruit.setExamResult(recruit.getExamResult());
		swapRecruit.setAuditionResult(recruit.getAuditionResult());
		swapRecruit.setOperResult(recruit.getOperResult());
		swapRecruit.setTotleResult(recruit.getTotleResult());
		swapRecruit.setAdmitFlag(GlobalConstant.FLAG_Y);
		swapRecruit.setAdmitNotice(swapNotice);
		swapRecruit.setRecruitFlag(GlobalConstant.FLAG_Y);
		swapRecruit.setRecruitDate(DateUtil.getCurrDate());
		swapRecruit.setTrainYear(trainYear);
		if(StringUtil.isNotBlank(adminSwapFlag)){
			swapRecruit.setAdminSwapFlag(adminSwapFlag);
		}
		resDoctorRecruitBiz.editDoctorRecruit(swapRecruit);

		SysUser exitUser = userBiz.readSysUser(swapRecruit.getDoctorFlow());
		String title = InitConfig.getSysCfg("res_admit_notice_email_title");
		this.msgBiz.addEmailMsg(exitUser.getUserEmail() , title, swapNotice);
	}
	@Override
	public void swapRecruit(String recruitFlow, String speFlow, String swapNotice, String trainYear) {
		swapRecruit(recruitFlow,speFlow,swapNotice,null,trainYear);
	}

	@Override
	public void setDoctorConfirmFlagForOutOfDate(String year, String doctorFlow) {
		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(year);
		String wishBeginDate = recruitCfg.getWishBeginDate();
		String wishEndDate = recruitCfg.getWishEndDate();
		String admitEndDate = recruitCfg.getAdmitEndDate();

		//2015 04 23 170839
		//获取当前日期
		String currDate = DateUtil.getCurrDate();
		if(StringUtil.isNotBlank(admitEndDate) && currDate.compareTo(admitEndDate)>0){
			ResDoctorRecruitWithBLOBs record = new ResDoctorRecruitWithBLOBs();
			record.setConfirmFlag(GlobalConstant.FLAG_N);//学员过期标记，直接默认放弃录取
			ResDoctorRecruitExample example = new ResDoctorRecruitExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andRecruitYearEqualTo(year).andDoctorFlowEqualTo(doctorFlow).andConfirmFlagIsNull()
			.andRecruitFlagEqualTo(GlobalConstant.FLAG_Y).andRecruitDateBetween(wishBeginDate, wishEndDate);
			this.doctorRecruitMapper.updateByExampleSelective(record , example);
		}
	}

	@Override
	public boolean doctorIsConfirmAdmit(String doctorFlow) {
		ResDoctorRecruitExample example = new ResDoctorRecruitExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
		.andDoctorFlowEqualTo(doctorFlow)
		.andConfirmFlagEqualTo(GlobalConstant.FLAG_Y);
		int count = this.doctorRecruitMapper.countByExample(example);
		return count > 0;
	}

	@Override
	public void modResDoctorRecruitByRecruitFlow(
			ResDoctorRecruitWithBLOBs recruit , boolean isModAll) {
		GeneralMethod.setRecordInfo(recruit , false);
		if(isModAll){
			this.doctorRecruitMapper.updateByPrimaryKeyWithBLOBs(recruit);
		}else{
			this.doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
		}
	}

	@Override
	public List<ResDoctorRecruitExt> searchDoctorRecruitWithUserList(Map<String, Object> paramMap) {
		return doctorRecruitExtMapper.searchDoctorRecruitWithUserList(paramMap);
	}

	@Override
	public ResDoctorRecruitWithBLOBs docRecruitClobSearch(Map<String, Object> paraMp) {
		return doctorRecruitExtMapper.docRecruitClobSearch(paraMp);
	}

	@Override
	public List<Map<String,Object>> getRecruitDocCount(Map<String, Object> paraMap) {
		return hbresDoctorRecruitExtMapper.getRecruitDocCount(paraMap);
	}

	@Override
	public List<Map<String,Object>> searchSwapDocList(Map<String, Object> paramMap){
		return hbresDoctorRecruitExtMapper.searchSwapDocList(paramMap);
	}

	@Override
	public List<Map<String,Object>> getSpeAssignCount(Map<String,Object> paramMap){
		return resOrgSpeAssignExtMapper.getSpeAssignCount(paramMap);
	}

	@Override
	public List<Map<String,Object>> getAdminSwapCount(Map<String,Object> paramMap){
		return hbresDoctorRecruitExtMapper.getAdminSwapCount(paramMap);
	}

	private static final String SWAP_NOTICE_TITLE = "调剂通知";
	@Override
	public int adminSwapRecruit(ResDoctorRecruitWithBLOBs docRecruit, String msg){
		if(docRecruit!=null){
			String doctorFlow = docRecruit.getDoctorFlow();
			if(StringUtil.isNotBlank(doctorFlow)){
				String recruitFlow = docRecruit.getRecruitFlow();

				if(!StringUtil.isNotBlank(recruitFlow)){
					String orgFlow = docRecruit.getOrgFlow();
					if(StringUtil.isNotBlank(orgFlow)){
						SysOrg org = orgBiz.readSysOrg(orgFlow);
						if(org!=null){
							docRecruit.setOrgName(org.getOrgName());
						}
					}

					String speId = docRecruit.getSpeId();
					if(StringUtil.isNotBlank(speId)){
						docRecruit.setSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(speId));
					}

					String assignYear = InitConfig.getSysCfg("res_reg_year");
					docRecruit.setRecruitYear(assignYear);

					docRecruit.setSwapFlag(GlobalConstant.FLAG_N);
					docRecruit.setRecruitTypeId(RecruitTypeEnum.Swap.getId());
					docRecruit.setRecruitTypeName(RecruitTypeEnum.Swap.getName());
					docRecruit.setRecruitDate(DateUtil.getCurrDate());
					docRecruit.setRetestFlag(GlobalConstant.FLAG_Y);
					docRecruit.setAdmitFlag(GlobalConstant.FLAG_N);
					docRecruit.setAdminSwapFlag(GlobalConstant.FLAG_Y);
					docRecruit.setRetestNotice("该学员为省厅调剂学员！");
				}else{
					ResDoctor doctor = new ResDoctor();
					doctor.setDoctorFlow(doctorFlow);
					doctor.setOrgFlow("");
					doctor.setOrgName("");
					doctorBiz.editDoctor(doctor);
				}

				docRecruit.setAdmitNotice(msg);

				resDoctorRecruitBiz.editDoctorRecruit(docRecruit);

				SysUser exitUser = userBiz.readSysUser(doctorFlow);
				if(exitUser!=null){
					msgBiz.addEmailMsg(exitUser.getUserEmail(),SWAP_NOTICE_TITLE,msg);
				}

				return GlobalConstant.ONE_LINE;
			}
		}
		return GlobalConstant.ZERO_LINE;
	}
	@Override
	public int editRegister(ResRecruitRegister recruitRegister) {
		if(StringUtil.isNotBlank(recruitRegister.getRecordFlow())){
			GeneralMethod.setRecordInfo(recruitRegister,false);
			return recruitRegisterMapper.updateByPrimaryKeySelective(recruitRegister);
		}else{
			recruitRegister.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(recruitRegister,true);
			return recruitRegisterMapper.insertSelective(recruitRegister);
		}
	}

	@Override
	public ResRecruitRegister readRecruitRegister(String recordFlow) {
		if(StringUtil.isNotBlank(recordFlow)){
			return recruitRegisterMapper.selectByPrimaryKey(recordFlow);
		}
		return null;
	}

	@Override
	public List<ResRecruitRegister> searchRecruitRegister(ResRecruitRegister recruitRegister) {
		if(recruitRegister!=null){
			ResRecruitRegisterExample example = new ResRecruitRegisterExample();
			ResRecruitRegisterExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			if(StringUtil.isNotBlank(recruitRegister.getDoctorFlow())){
				criteria.andDoctorFlowEqualTo(recruitRegister.getDoctorFlow());
			}
			if(StringUtil.isNotBlank(recruitRegister.getRecruitYear())){
				criteria.andRecruitYearEqualTo(recruitRegister.getRecruitYear());
			}
			return recruitRegisterMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<Map<String, String>> searchRegistList(Map<String, Object> paramMap) {
		return hbresDoctorRecruitExtMapper.searchRegistList(paramMap);
	}

	@Override
	public List<JsResDoctorRecruitExt>  searchDoctorScoreInfoExts(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList,String hegeScore)
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
		doctorRecruitMap.put("hegeScore",hegeScore);
		List<JsResDoctorRecruitExt> doctorRecruitList=hbresDoctorRecruitExtMapper.searchJsDoctorScoreExtList(doctorRecruitMap);
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
		doctorRecruitMap.put("hegeScore", hegeScore);
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
		List<JsResDoctorRecruitExt> doctorRecruitList=hbresDoctorRecruitExtMapper.searchDoctorSkillAndTheoryScoreExts(doctorRecruitMap);
		return doctorRecruitList;
	}
	@Override
	public List<JsResDoctorRecruitExt> searchDoctorSkillScore(ResDoctorRecruit resDoctorRecruit, ResDoctor doctor, SysUser user, SysOrg sysOrg, List<String> jointOrgFlowList, String flag, String scoreYear, String isHege, List<String> docTypeList)
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
		List<JsResDoctorRecruitExt> doctorRecruitList=hbresDoctorRecruitExtMapper.searchJsDoctorSkillScoreExtList(doctorRecruitMap);
		return doctorRecruitList;
	}
}
