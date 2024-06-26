package com.pinde.sci.biz.sczyres.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.res.IRecruitCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResRegBiz;
import com.pinde.sci.biz.sczyres.DoctorRecruitBiz;
import com.pinde.sci.biz.sczyres.DoctorSingupBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.PubUserResumeMapper;
import com.pinde.sci.dao.base.ResDoctorMapper;
import com.pinde.sci.dao.base.ResDoctorRecruitMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.res.RecruitTypeEnum;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.sczyres.SczyRecDocTypeEnum;
import com.pinde.sci.enums.sczyres.SpeCatEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.sczyres.ExtInfoForm;
import com.pinde.sci.form.sczyres.SingUpForm;
import com.pinde.sci.form.sczyres.WorkResumeForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
@Transactional(rollbackFor=Exception.class)
public class DoctorSingupBizImpl implements DoctorSingupBiz{

	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ResDoctorMapper doctorMapper;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ResDoctorRecruitMapper doctorRecruitMapper;
	@Autowired
	private IResRegBiz resResBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private DoctorRecruitBiz recruitBiz;
	@Autowired
	private PubUserResumeMapper resumpMapper;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IRecruitCfgBiz recruitCfgBiz;
	
	private static final String EXT_INFO_ROOT = "extInfo";
	private static final String EXT_INFO_ELE = "extInfoForm";
	private static final String WORK_RESUME_LIST_ELE = "workResumeList";
	private static final String WORK_RESUME_ELE = "workResumeForm";
	
	@Override
	public int returnInfo(String userFlow,String recruitFlow){
		String regYear = InitConfig.getSysCfg("res_reg_year");
		
		ResDoctor doctor=doctorBiz.searchResDoctor(userFlow, regYear);
		if(doctor!=null){
			doctor.setDoctorStatusId(RegStatusEnum.UnSubmit.getId());
	        doctor.setDoctorStatusName(RegStatusEnum.UnSubmit.getName());
			doctorBiz.editDoctor(doctor);
		}
		
		ResReg reg= resResBiz.searchResReg(userFlow, regYear);
		if(reg!=null){
			reg.setStatusId(RegStatusEnum.UnSubmit.getId());
			reg.setStatusName(RegStatusEnum.UnSubmit.getName());
			resResBiz.editResReg(reg);
		}
		
		if(StringUtil.isNotBlank(recruitFlow)){
			ResDoctorRecruitWithBLOBs recruitBlob = new ResDoctorRecruitWithBLOBs();
			recruitBlob.setRecruitFlow(recruitFlow);
			recruitBlob.setAdmitFlag("");
			recruitBlob.setRecruitFlag("");
//			recruitBlob.setDoctorStatusId(RegStatusEnum.UnSubmit.getId());
//			recruitBlob.setDoctorStatusName(RegStatusEnum.UnSubmit.getName());
			recruitBlob.setConfirmFlag("");
			recruitBiz.editDoctorRecruit(recruitBlob);
		}

		this.msgBiz.addSysMsg(userFlow, "注意，您的报名材料被退回.", null);
		return GlobalConstant.ONE_LINE;
	}
	
	@Override
	public void submitSingup(SingUpForm form) {
		SysUser user = form.getUser();
		String userFlow = user.getUserFlow();
		//修改user信息
		user.setUserCode(user.getIdNo());
		this.userBiz.updateUser(form.getUser());
		String regYear = InitConfig.getSysCfg("res_reg_year");
		ResDoctor doctor = form.getDoctor();
		doctor.setDoctorFlow(userFlow);
		doctor.setDoctorName(user.getUserName());
		//设置报名年份
		doctor.setSessionNumber(regYear);
		
        //在InitConfig.getSysCfg("res_reg_year")报名年份下是否存在res_reg的记录
        ResReg reg = this.resResBiz.searchResReg(userFlow, regYear);
        if(reg==null){
        	reg = new ResReg();
        	reg.setUserFlow(userFlow);
        	reg.setUserName(user.getUserName());
        	reg.setRegYear(regYear);
        	reg.setStatusId(RegStatusEnum.UnSubmit.getId());
        	reg.setStatusName(RegStatusEnum.UnSubmit.getName());
        	this.resResBiz.editResReg(reg);
        }
		
		//保存额外信息
		PubUserResume resume = resumpMapper.selectByPrimaryKey(userFlow);
		ExtInfoForm extInfoForm = form.getExtInfo();
		if(StringUtil.isNotBlank(extInfoForm.getDegreeId())){//本科学位
			extInfoForm.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(extInfoForm.getDegreeId()));
		}
		if(StringUtil.isNotBlank(extInfoForm.getMasterDegreeId())){//硕士学位
			extInfoForm.setMasterDegreeName(DictTypeEnum.UserDegree.getDictNameById(extInfoForm.getMasterDegreeId()));
		}
		if(StringUtil.isNotBlank(extInfoForm.getDoctorDegreeId())){//博士学位
			extInfoForm.setDoctorDegreeName(DictTypeEnum.UserDegree.getDictNameById(extInfoForm.getDoctorDegreeId()));
		}
		//人员类型
		if(StringUtil.isNotBlank(doctor.getDoctorTypeId())){
			if(SczyRecDocTypeEnum.Agency.getId().equals(doctor.getDoctorTypeId())){
				if("1".equals(extInfoForm.getMedicalHeaithOrgId())){
					//基层医疗卫生机构
					extInfoForm.setBasicHealthOrgId("");//基层医疗卫生机构
					extInfoForm.setBasicHealthOrgName("");
					extInfoForm.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
					extInfoForm.setBasicHealthOrgLevelName("");
				}else if("2".equals(extInfoForm.getMedicalHeaithOrgId()) || "4".equals(extInfoForm.getMedicalHeaithOrgId())){
					//医院相关
					extInfoForm.setHospitalAttrId("");//医院性质
					extInfoForm.setHospitalAttrName("");
					extInfoForm.setHospitalCategoryId("");//医院类别
					extInfoForm.setHospitalCategoryName("");
					extInfoForm.setBaseAttributeId("");//单位等级
					extInfoForm.setBaseAttributeName("");
					//基层医疗卫生机构
					extInfoForm.setBasicHealthOrgId("");//基层医疗卫生机构
					extInfoForm.setBasicHealthOrgName("");
					extInfoForm.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
					extInfoForm.setBasicHealthOrgLevelName("");
				}else if("3".equals(extInfoForm.getMedicalHeaithOrgId())){
					//医院相关
					extInfoForm.setHospitalCategoryId("");//医院类别
					extInfoForm.setHospitalCategoryName("");
					extInfoForm.setBaseAttributeId("");//单位等级
					extInfoForm.setBaseAttributeName("");
				}
			}else if(SczyRecDocTypeEnum.Industry.getId().equals(doctor.getDoctorTypeId())){
				//派送单位（派送学校）相关
				doctor.setWorkOrgId("");
				doctor.setWorkOrgName("");
				//单位人类型
				doctor.setCompanyType("");
				/************以下医疗卫生机构相关************/
				extInfoForm.setMedicalHeaithOrgId("");
				extInfoForm.setMedicalHeaithOrgName("");
				//医院相关
				extInfoForm.setHospitalAttrId("");//医院性质
				extInfoForm.setHospitalAttrName("");
				extInfoForm.setHospitalCategoryId("");//医院类别
				extInfoForm.setHospitalCategoryName("");
				extInfoForm.setBaseAttributeId("");//单位等级
				extInfoForm.setBaseAttributeName("");
				//基层医疗卫生机构
				extInfoForm.setBasicHealthOrgId("");//基层医疗卫生机构
				extInfoForm.setBasicHealthOrgName("");
				extInfoForm.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
				extInfoForm.setBasicHealthOrgLevelName("");
				//基层医疗卫生机构相关
				/************以上医疗卫生机构相关************/
			}else if(SczyRecDocTypeEnum.Graduate.getId().equals(doctor.getDoctorTypeId())){
				//单位人类型
				doctor.setCompanyType("");
				/************以下医疗卫生机构相关************/
				extInfoForm.setMedicalHeaithOrgId("");
				extInfoForm.setMedicalHeaithOrgName("");
				//医院相关
				extInfoForm.setHospitalAttrId("");//医院性质
				extInfoForm.setHospitalAttrName("");
				extInfoForm.setHospitalCategoryId("");//医院类别
				extInfoForm.setHospitalCategoryName("");
				extInfoForm.setBaseAttributeId("");//单位等级
				extInfoForm.setBaseAttributeName("");
				//基层医疗卫生机构
				extInfoForm.setBasicHealthOrgId("");//基层医疗卫生机构
				extInfoForm.setBasicHealthOrgName("");
				extInfoForm.setBasicHealthOrgLevelId("");//基层医疗卫生机构等级
				extInfoForm.setBasicHealthOrgLevelName("");
				//基层医疗卫生机构相关
				/************以上医疗卫生机构相关************/
			}
		}else{
			doctor.setDoctorTypeName("");
		}

		String content = getXmlFromExtInfo(extInfoForm);
		if(resume==null){
			resume = new PubUserResume();
			resume.setUserFlow(userFlow);
			resume.setUserName(user.getUserName());
			resume.setUserResume(content);
			GeneralMethod.setRecordInfo(resume, true);
			this.resumpMapper.insertSelective(resume);
		}else{
			resume.setUserResume(content);
			GeneralMethod.setRecordInfo(resume,false);
			this.resumpMapper.updateByPrimaryKeySelective(resume);
		}

		//是否存在doctor信息
		ResDoctor existDoctor = this.doctorMapper.selectByPrimaryKey(userFlow);
		if(existDoctor==null){
			//学员状态为未提交
			doctor.setDoctorStatusId(RegStatusEnum.UnSubmit.getId());
			doctor.setDoctorStatusName(RegStatusEnum.UnSubmit.getName());
			GeneralMethod.setRecordInfo(doctor, true);
			doctor.setSelDeptFlag(GlobalConstant.FLAG_N);
			doctor.setSchFlag(GlobalConstant.FLAG_N);
			doctor.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
			doctor.setDoctorCategoryName(RecDocCategoryEnum.Doctor.getName());
			this.doctorMapper.insertSelective(doctor);
		}else{
			GeneralMethod.setRecordInfo(doctor, false);
			this.doctorMapper.updateByPrimaryKeySelective(doctor);
		}
	}

	@Override
	public void submitRecruit(ResDoctorRecruitWithBLOBs recruit,String jsonData) {
		//计算学员结业考核年份
		String doctorFlow = recruit.getDoctorFlow();
		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		String trainYear = recruit.getTrainYear();
		String sessionNumber = doctor.getSessionNumber();
		String graduationYear = String.valueOf(Integer.parseInt(trainYear)+Integer.parseInt(sessionNumber));
		recruit.setSessionNumber(sessionNumber);
		recruit.setGraduationYear(graduationYear);
		recruit.setRecruitTypeId(RecruitTypeEnum.Fill.getId());
		recruit.setRecruitTypeName(RecruitTypeEnum.Fill.getName());
		saveRecruit(recruit,jsonData);
		//将学员状态更改为待审核
		doctor.setDoctorStatusId(RegStatusEnum.Passing.getId());
		doctor.setDoctorStatusName(RegStatusEnum.Passing.getName());
		doctor.setOrgFlow(recruit.getOrgFlow());
		doctor.setOrgName(recruit.getOrgName());
		doctor.setTrainingYears(trainYear);
		doctor.setGraduationYear(graduationYear);

//		String regYear = InitConfig.getSysCfg("res_reg_year");
//		ResRecruitCfg recruitCfg = recruitCfgBiz.findRecruitCfgByYear(regYear);
//		String currDate = DateUtil.getCurrDate();
//		DateCfgMsg wishDateCfgMsg = new DateCfgMsg(recruitCfg);
//		wishDateCfgMsg.setWishDateMsg(currDate);
//		if(wishDateCfgMsg.getAllowWish()) {//第二次报名
//			doctor.setSczyRecriutBanch("2");
//		}

		this.doctorMapper.updateByPrimaryKeySelective(doctor);

		SysUser user = new SysUser();
		user.setUserFlow(doctorFlow);
		user.setOrgFlow(recruit.getOrgFlow());
		user.setOrgName(recruit.getOrgName());
		userMapper.updateByPrimaryKeySelective(user);

		ResReg reg = this.resResBiz.searchResReg(recruit.getDoctorFlow(), recruit.getRecruitYear());
		reg.setStatusId(RegStatusEnum.Passing.getId());
		reg.setStatusName(RegStatusEnum.Passing.getName());
		this.resResBiz.editResReg(reg);
		
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
			
			List<WorkResumeForm> workResumeList = extInfo.getWorkResumeList();
			if(workResumeList!=null && workResumeList.size()>0){
				Element workResumeListEle = root.addElement(WORK_RESUME_LIST_ELE);
				for(WorkResumeForm resume : workResumeList){
					Element resumeEle = workResumeListEle.addElement(WORK_RESUME_ELE);
					Map<String,String> resumeFiledMap = getClassFieldMap(resume);
					if(resumeFiledMap!=null && resumeFiledMap.size()>0){
						for(String key : resumeFiledMap.keySet()){
							Element item = resumeEle.addElement(key);
							item.setText(resumeFiledMap.get(key));
						}
					}
				}
			}
			
			xmlBody=doc.asXML();
		}
		return xmlBody;
	}
	
	/**
	 * 获取属性名和值
	 * @param
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
				
				Element workResumeListEle = root.element(WORK_RESUME_LIST_ELE);
				if(workResumeListEle!=null){
					List<Element> workResumeEles = workResumeListEle.elements();
					if(workResumeEles!=null && workResumeEles.size()>0){
						List<WorkResumeForm> resumeList = new ArrayList<WorkResumeForm>();
						for(Element resumeEle : workResumeEles){
							WorkResumeForm resume = new WorkResumeForm();
							List<Element> resumeAttrEles = resumeEle.elements();
							if(resumeAttrEles!=null && resumeAttrEles.size()>0){
								for(Element attr : resumeAttrEles){
									String attrName = attr.getName();
									String attrValue = attr.getText();
									setValue(resume,attrName,attrValue);
								}
							}
							resumeList.add(resume);
						}
						extInfo.setWorkResumeList(resumeList);
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
	public List<SysDict> findSpe(String catSpeId) {
		List<SysDict> spes = new ArrayList<SysDict>();
		if(SpeCatEnum.ChineseMedicine.getId().equals(catSpeId)){
			spes = DictTypeEnum.sysListDictMap.get(DictTypeEnum.ZyCoSpe.getId());
		}
		if(SpeCatEnum.TCMGeneral.getId().equals(catSpeId)){
			spes = DictTypeEnum.sysListDictMap.get(DictTypeEnum.ZyqkCoSpe.getId());
		}
		return spes;
	}

	@Override
	public void saveRecruit(ResDoctorRecruitWithBLOBs recruit,String jsonData) {
		String regYear = InitConfig.getSysCfg("res_reg_year");
		String recruitFlow = recruit.getRecruitFlow();
		String orgFlow = recruit.getOrgFlow();
		SysOrg org = this.orgBiz.readSysOrg(orgFlow);
		recruit.setRecruitDate(regYear+"-09-01");
		recruit.setOrgName(org.getOrgName());
		recruit.setRecruitYear(regYear);
		if(StringUtil.isBlank(recruit.getSwapFlag())){
			recruit.setSwapFlag("");
		}
		//是否存在该记录
		//不存在就新增 存在就修改
		String uuid = PkUtil.getUUID();
		if(StringUtil.isBlank(recruitFlow)){
			recruit.setRecruitFlow(uuid);
			GeneralMethod.setRecordInfo(recruit, true);
			this.doctorRecruitMapper.insertSelective(recruit);
		}else{
			GeneralMethod.setRecordInfo(recruit, false);
			this.doctorRecruitMapper.updateByPrimaryKeySelective(recruit);
		}
		//第二、三志愿保存
		if(StringUtil.isNotBlank(jsonData)){
			List<Map<String,Object>> dataMapList = JSON.parseObject(jsonData, ArrayList.class);
			if(dataMapList!=null&&dataMapList.size()>0){
				for(Map<String,Object> map:dataMapList){
					String orderNum = (String)map.get("orderNum");
					String orgFlow0 = (String)map.get("orgFlow");
					String orgName = (String)map.get("orgName");
					String catSpeId = (String)map.get("catSpeId");
					String catSpeName = (String)map.get("catSpeName");
					String speId = (String)map.get("speId");
					String speName = (String)map.get("speName");
					String secondSpeId = (String)map.get("secondSpeId");
					String secondSpeName = (String)map.get("secondSpeName");
					ScresRecuritMoreSpe search = new ScresRecuritMoreSpe();
					ScresRecuritMoreSpe save = new ScresRecuritMoreSpe();
					save.setOrderNum(orderNum);
					save.setOrgFlow(orgFlow0);
					save.setOrgName(orgName);
					save.setCatSpeId(catSpeId);
					save.setCatSpeName(catSpeName);
					save.setSpeId(speId);
					save.setSpeName(speName);
					save.setSecondSpeId(secondSpeId);
					save.setSecondSpeName(secondSpeName);
					if(StringUtil.isNotBlank(recruitFlow)){
						search.setRecruitFlow(recruitFlow);
						search.setOrderNum(orderNum);
						List<ScresRecuritMoreSpe> moreSpeList = recruitBiz.searchMoreSpe(search);
						if(moreSpeList!=null&&moreSpeList.size()>0){
							save.setRecordFlow(moreSpeList.get(0).getRecordFlow());
						}else{
							save.setRecruitFlow(recruitFlow);
						}
					}else{
						save.setRecruitFlow(uuid);
					}
					recruitBiz.editMoreSpe(save);
				}
			}
		}
	}

	@Override
	public void modDoctorByDoctorFlow(ResDoctor doctor) {
		if(doctor!=null){
			this.doctorMapper.updateByPrimaryKeySelective(doctor);
		}
		
	}
}
