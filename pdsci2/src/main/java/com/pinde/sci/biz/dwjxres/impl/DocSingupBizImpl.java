package com.pinde.sci.biz.dwjxres.impl;

import com.pinde.core.util.*;
import com.pinde.sci.biz.dwjxres.IDocSingupBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.StuAuditProcessMapper;
import com.pinde.sci.dao.base.StuBatchMapper;
import com.pinde.sci.dao.base.StuUserResumeMapper;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.form.dwjxres.ExtInfoForm;
import com.pinde.sci.form.dwjxres.SingUpForm;
import com.pinde.sci.form.dwjxres.WorkResumeForm;
import com.pinde.sci.form.dwjxres.RegForm;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

@Service
@Transactional(rollbackFor=Exception.class)
public class DocSingupBizImpl implements IDocSingupBiz {
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private StuUserResumeMapper stuUserMapper;
	@Autowired
	private StuAuditProcessMapper auditMapper;
	@Autowired
	private StuBatchMapper batchMapper;
	@Autowired
	private IMsgBiz msgBiz;

	private static final String EXT_INFO_ROOT = "extInfo";
	private static final String EXT_INFO_ELE = "extInfoForm";
	private static final String WORK_RESUME_LIST_ELE = "workResumeList";
	private static final String WORK_RESUME_ELE = "workResumeForm";
	private static final String REG_LIST_ELE = "regList";
	private static final String REG_IDNO_LIST_ELE = "idNoUriList";
	private static final String REG_GRADUATE_LIST_ELE = "graduatedUriList";
	private static final String REG_QUALIFIED_LIST_ELE = "qualifiedUriList";
	private static final String REG_CERTIFIED_LIST_ELE = "certifiedUriList";
	private static final String REG_ELE = "regForm";

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
				addListElement(root,REG_LIST_ELE,extInfo);
				addListElement(root,REG_IDNO_LIST_ELE,extInfo);
				addListElement(root,REG_GRADUATE_LIST_ELE,extInfo);
				addListElement(root,REG_QUALIFIED_LIST_ELE,extInfo);
				addListElement(root,REG_CERTIFIED_LIST_ELE,extInfo);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return extInfo;
	}

	private void addListElement(Element root, String key, ExtInfoForm extInfo) {
		Element regListEle = root.element(key);
		if(regListEle!=null){
			List<Element> regEles = regListEle.elements();
			if(regEles!=null && regEles.size()>0){
				List<RegForm> regList = new ArrayList<RegForm>();
				for(Element regEle : regEles){
					RegForm reg = new RegForm();
					List<Element> regAttrEles = regEle.elements();
					if(regAttrEles!=null && regAttrEles.size()>0){
						for(Element attr : regAttrEles){
							String attrName = attr.getName();
							String attrValue = attr.getText();
							setValue(reg,attrName,attrValue);
						}
					}
					regList.add(reg);
				}
				if(REG_LIST_ELE.equals(key))
					extInfo.setRegList(regList);
				if(REG_IDNO_LIST_ELE.equals(key))
					extInfo.setIdNoUriList(regList);
				if(REG_GRADUATE_LIST_ELE.equals(key))
					extInfo.setGraduatedUriList(regList);
				if(REG_QUALIFIED_LIST_ELE.equals(key))
					extInfo.setQualifiedUriList(regList);
				if(REG_CERTIFIED_LIST_ELE.equals(key))
					extInfo.setCertifiedUriList(regList);
			}
		}
	}

	@Override
	public void submitSingup(SingUpForm form) {
		//修改user信息
		this.userBiz.updateUser(form.getUser());
		//是否存在医师信息
		StuUserResumeExample example = new StuUserResumeExample();
		example.createCriteria().andUserFlowEqualTo(form.getUser().getUserFlow())
				.andStuBatIdEqualTo(form.getStuUser().getStuBatId());
		List<StuUserResume> stuUserLst = this.stuUserMapper.selectByExample(example);
		StuUserResume stuUser = form.getStuUser();
		if(null != stuUserLst && stuUserLst.size() > 0){
			stuUser.setResumeInfo(getXmlFromExtInfo(form.getExtInfo()));//工作经历等
			GeneralMethod.setRecordInfo(stuUser, false);
			example.clear();
			example.createCriteria().andResumeFlowEqualTo(stuUserLst.get(0).getResumeFlow());
			this.stuUserMapper.updateByExampleSelective(stuUser,example);
		}else{
			stuUser.setResumeFlow(PkUtil.getUUID());
			stuUser.setResumeInfo(getXmlFromExtInfo(form.getExtInfo()));//工作经历等
			stuUser.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			stuUser.setModifyUserFlow(form.getUser().getUserFlow());
			stuUser.setModifyTime(DateUtil.getCurrentTime());
			stuUser.setCreateUserFlow(form.getUser().getUserFlow());
			stuUser.setCreateTime(DateUtil.getCurrentTime());
			this.stuUserMapper.insertSelective(stuUser);
		}
	}

	@Override
	public void auditOption(Map<String, String> mp) {
		StuUserResume stuUser = new StuUserResume();
		stuUser.setResumeFlow(mp.get("resumeFlow"));
		stuUser.setStuStatusId(mp.get("statusId"));
		stuUser.setStuStatusName(StuStatusEnum.getNameById(mp.get("statusId")));
		if(mp.get("titleTypeName").equals("护士")) {
			stuUser.setNursingStatusId(mp.get("statusId"));
			stuUser.setNursingStatusName(StuStatusEnum.getNameById(mp.get("statusId")));
		}
		String smsTempFlow = "";//短信模板流水号
		if(StuStatusEnum.Passed.getId().equals(stuUser.getStuStatusId()))
		{
			stuUser.setIsAudit(GlobalConstant.FLAG_Y);
			smsTempFlow = "dwjxres10001";
		}else	if(StuStatusEnum.UnPassed.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsAudit(GlobalConstant.FLAG_N);
			smsTempFlow = "dwjxres10003";
		}
		//添加是否已录取字段标识
		if(StuStatusEnum.Recruitted.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsRecruit(GlobalConstant.FLAG_Y);
		}else if(StuStatusEnum.UnRecruitted.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsRecruit(GlobalConstant.FLAG_N);
			smsTempFlow = "dwjxres10005";
		}

		if(StuStatusEnum.Admited.getId().equals(stuUser.getStuStatusId()))
		{
			stuUser.setIsAdmited(GlobalConstant.FLAG_Y);
		}else	if(StuStatusEnum.UnAdmitd.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsAdmited(GlobalConstant.FLAG_N);
		}

		if(StuStatusEnum.UnSubmit.getId().equals(stuUser.getStuStatusId()))
		{
			stuUser.setIsAdmited("");
			stuUser.setIsAudit("");
			stuUser.setIsRecruit("");
		}
		int count = this.stuUserMapper.updateByPrimaryKeySelective(stuUser);
		if(count > 0 && StringUtil.isNotBlank(smsTempFlow)){
			SysUser user = userBiz.readSysUser(mp.get("userFlow"));
			this.msgBiz.sendSMS(user.getUserPhone(),smsTempFlow,user.getUserName(),stuUser.getResumeFlow(),"STU_USER_RESUME");
		}
		StuAuditProcessExample example = new StuAuditProcessExample();
		example.createCriteria().andResumeFlowEqualTo(mp.get("resumeFlow")).andUserFlowEqualTo(mp.get("userFlow"))
			.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<StuAuditProcess> auditProLst = this.auditMapper.selectByExample(example);
		StuAuditProcess auditProcess = new StuAuditProcess();
		if(null != auditProLst && auditProLst.size() > 0){
			auditProcess.setProcessFlow(auditProLst.get(0).getProcessFlow());
			auditProcess.setStuStatusId(mp.get("statusId"));
			auditProcess.setStuStatusName(StuStatusEnum.getNameById(mp.get("statusId")));
			auditProcess.setAuditContent(mp.get("reason"));
			this.auditMapper.updateByPrimaryKeySelective(auditProcess);
		}else{
			auditProcess.setProcessFlow(PkUtil.getUUID());
			auditProcess.setResumeFlow(mp.get("resumeFlow"));
			auditProcess.setUserFlow(mp.get("userFlow"));
			auditProcess.setStuStatusId(mp.get("statusId"));
			auditProcess.setStuStatusName(RegStatusEnum.getNameById(mp.get("statusId")));
			auditProcess.setAuditContent(mp.get("reason"));
			auditProcess.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			auditProcess.setOperUserName(GlobalContext.getCurrentUser().getUserName());
			auditProcess.setOperTime(DateUtil.getCurrentTime());
			GeneralMethod.setRecordInfo(auditProcess,true);
			this.auditMapper.insertSelective(auditProcess);
		}
		this.msgBiz.addSysMsg(mp.get("userFlow"), "报名材料审核结果："+stuUser.getStuStatusName(), auditProcess.getAuditContent());
	}

	@Override
	public void changeRecruit(String resumeFlow, String statusId,String titleTypeName) {
		StuUserResume stuUser = new StuUserResume();
		stuUser.setResumeFlow(resumeFlow);
		stuUser.setStuStatusId(statusId);
		stuUser.setStuStatusName(StuStatusEnum.getNameById(statusId));
		if(StuStatusEnum.Recruitted.getId().equals(stuUser.getStuStatusId()))
		{
			stuUser.setIsAudit(GlobalConstant.FLAG_Y);
		}else	if(StuStatusEnum.UnRecruitted.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsAudit(GlobalConstant.FLAG_N);
		}
		if(StuStatusEnum.Admited.getId().equals(stuUser.getStuStatusId()))
		{
			stuUser.setIsAdmited(GlobalConstant.FLAG_Y);
			if("护士".equals(titleTypeName)){
				stuUser.setNursingStatusId(StuStatusEnum.Admited.getId());
				stuUser.setNursingStatusName(StuStatusEnum.Admited.getName());
			}
		}else	if(StuStatusEnum.UnAdmitd.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsAdmited(GlobalConstant.FLAG_N);
			if("护士".equals(titleTypeName)){
				stuUser.setNursingStatusId(StuStatusEnum.UnAdmitd.getId());
				stuUser.setNursingStatusName(StuStatusEnum.UnAdmitd.getName());
			}
		}
		if(StuStatusEnum.UnSubmit.getId().equals(stuUser.getStuStatusId()))
		{
			stuUser.setIsAdmited("");
			stuUser.setIsAudit("");
			stuUser.setIsRecruit("");
		}
		GeneralMethod.setRecordInfo(stuUser,false);
		this.stuUserMapper.updateByPrimaryKeySelective(stuUser);
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

	/**
	 * 获取额外信息xml
	 * @param extInfo
	 * @return
	 */
	@Override
	public String getXmlFromExtInfo(ExtInfoForm extInfo){
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
					if(resume!=null&&resume.getClinicalRoundDate()!=null
							&&resume.getHospitalName()!=null&&resume.getPostName()!=null&&resume.getWorkDescription()!=null) {
						Element resumeEle = workResumeListEle.addElement(WORK_RESUME_ELE);
						Map<String, String> resumeFiledMap = getClassFieldMap(resume);
						if (resumeFiledMap != null && resumeFiledMap.size() > 0) {
							for (String key : resumeFiledMap.keySet()) {
								Element item = resumeEle.addElement(key);
								item.setText(resumeFiledMap.get(key));
							}
						}
					}
				}
			}
			addXmlContent(root,extInfo.getRegList(),REG_LIST_ELE);
			addXmlContent(root,extInfo.getIdNoUriList(),REG_IDNO_LIST_ELE);
			addXmlContent(root,extInfo.getGraduatedUriList(),REG_GRADUATE_LIST_ELE);
			addXmlContent(root,extInfo.getQualifiedUriList(),REG_QUALIFIED_LIST_ELE);
			addXmlContent(root,extInfo.getCertifiedUriList(),REG_CERTIFIED_LIST_ELE);

			xmlBody=doc.asXML();
		}
		return xmlBody;
	}

	private void addXmlContent(Element root, List<RegForm> regList, String listKey) {
		if(regList!=null && regList.size()>0){
			Element regListEle = root.addElement(listKey);
			for(RegForm reg : regList){
				if(reg!=null&&(REG_LIST_ELE.equals(listKey)&&reg.getRegNo()!=null&&reg.getRegUri()!=null
				||!REG_LIST_ELE.equals(listKey)&&reg.getRegUri()!=null)) {
					Element regEle = regListEle.addElement(REG_ELE);
					Map<String, String> rgeFiledMap = getClassFieldMap(reg);
					if (rgeFiledMap != null && rgeFiledMap.size() > 0) {
						for (String key : rgeFiledMap.keySet()) {
							Element item = regEle.addElement(key);
							item.setText(rgeFiledMap.get(key));
						}
					}
				}
			}
		}
	}

	/**
	 * 获取属性名和值
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
	@Override
	public void saveSingupByPage(SingUpForm form) {
		//修改user信息
		this.userBiz.updateUser(form.getUser());
		//是否存在医师信息
		StuUserResumeExample example = new StuUserResumeExample();
		example.createCriteria().andUserFlowEqualTo(form.getUser().getUserFlow())
				.andStuBatIdEqualTo(form.getStuUser().getStuBatId());
		List<StuUserResume> stuUserLst = this.stuUserMapper.selectByExampleWithBLOBs(example);
		StuUserResume stuUser = form.getStuUser();
		if(null != stuUserLst && stuUserLst.size() > 0){
			StuUserResume tempUser = stuUserLst.get(0);
			if(tempUser != null && stuUser != null){
				tempUser.setStuStatusId(stuUser.getStuStatusId());
				tempUser.setStuStatusName(stuUser.getStuStatusName());
				BeanUtil.mergeObject(stuUser,tempUser);
			}
			String tempUserInfo = tempUser.getResumeInfo();
			ExtInfoForm tempUserInfoForm=new ExtInfoForm();
			if(StringUtil.isNotBlank(tempUserInfo))
			{
				tempUserInfoForm = parseExtInfoXml(tempUserInfo);
			}
			if(tempUserInfoForm != null && form.getExtInfo()!= null){
				BeanUtil.mergeObject(form.getExtInfo(),tempUserInfoForm);
			}
			tempUser.setResumeInfo(getXmlFromExtInfo(tempUserInfoForm));//工作经历等
			GeneralMethod.setRecordInfo(tempUser, false);
			example.clear();
			example.createCriteria().andResumeFlowEqualTo(tempUser.getResumeFlow());
			this.stuUserMapper.updateByExampleWithBLOBs(tempUser,example);
		}else{
			stuUser.setResumeFlow(PkUtil.getUUID());
			stuUser.setResumeInfo(getXmlFromExtInfo(form.getExtInfo()));//工作经历等
			stuUser.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			stuUser.setModifyUserFlow(form.getUser().getUserFlow());
			stuUser.setModifyTime(DateUtil.getCurrentTime());
			stuUser.setCreateUserFlow(form.getUser().getUserFlow());
			stuUser.setCreateTime(DateUtil.getCurrentTime());
			this.stuUserMapper.insertSelective(stuUser);
		}
	}

	@Override
	public StuBatch findBatchByBatchFlow(String stuBatId) {
		StuBatch stuBatch = null;
		if(StringUtil.isNotBlank(stuBatId)){
			stuBatch = batchMapper.selectByPrimaryKey(stuBatId);
		}
		return stuBatch;
	}

	@Override
	public int returnInfo(String resumeFlow, String reason,String userFlow,String titleTypeName) {
		StuUserResume resume = new StuUserResume();
		resume.setResumeFlow(resumeFlow);
		resume.setStuStatusId(StuStatusEnum.UnPassed.getId());
		resume.setStuStatusName(StuStatusEnum.UnPassed.getName());
		if(titleTypeName.equals("护士")){
            resume.setNursingStatusId(StuStatusEnum.UnPassed.getId());
            resume.setNursingStatusName(StuStatusEnum.UnPassed.getName());
        }
		resume.setIsBack(GlobalConstant.FLAG_Y);
		int count = this.stuUserMapper.updateByPrimaryKeySelective(resume);
		if(count > 0){
			SysUser user = userBiz.readSysUser(userFlow);
			this.msgBiz.sendSMS(user.getUserPhone(),"dwjxres10002",user.getUserName(),resume.getResumeFlow(),"STU_USER_RESUME");
		}
		StuAuditProcessExample example = new StuAuditProcessExample();
		example.createCriteria().andResumeFlowEqualTo(resumeFlow).andUserFlowEqualTo(userFlow)
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<StuAuditProcess> auditProLst = this.auditMapper.selectByExample(example);
		StuAuditProcess auditProcess = new StuAuditProcess();
		if(null != auditProLst && auditProLst.size() > 0){
			auditProcess.setProcessFlow(auditProLst.get(0).getProcessFlow());
			auditProcess.setStuStatusId(StuStatusEnum.UnPassed.getId());
			auditProcess.setStuStatusName(StuStatusEnum.UnPassed.getName());
			auditProcess.setAuditContent(reason);
			this.auditMapper.updateByPrimaryKeySelective(auditProcess);
		}else{
			auditProcess.setProcessFlow(PkUtil.getUUID());
			auditProcess.setResumeFlow(resumeFlow);
			auditProcess.setUserFlow(userFlow);
			auditProcess.setStuStatusId(StuStatusEnum.UnPassed.getId());
			auditProcess.setStuStatusName(StuStatusEnum.UnPassed.getName());
			auditProcess.setAuditContent(reason);
			auditProcess.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			auditProcess.setOperUserName(GlobalContext.getCurrentUser().getUserName());
			auditProcess.setOperTime(DateUtil.getCurrentTime());
			GeneralMethod.setRecordInfo(auditProcess,true);
			this.auditMapper.insertSelective(auditProcess);
		}
		this.msgBiz.addSysMsg(userFlow, "报名材料审核结果：报名信息已被退回，请重新填写。", auditProcess.getAuditContent());
		return 1;
	}

	@Override
	public void auditNurseOption(Map<String, String> mp) {

		StuUserResume stuUser = new StuUserResume();
		stuUser.setResumeFlow(mp.get("resumeFlow"));
		stuUser.setStuStatusId(mp.get("statusId"));
		stuUser.setStuStatusName(StuStatusEnum.getNameById(mp.get("statusId")));
		stuUser.setNursingStatusId(mp.get("nursingStatusId"));
		stuUser.setNursingStatusName(StuStatusEnum.getNameById(mp.get("nursingStatusId")));
		if(mp.get("nursingStatusId").equals(StuStatusEnum.Passed.getId())) {
			 this.stuUserMapper.updateByPrimaryKeySelective(stuUser);
			 return;
		}
		int count = this.stuUserMapper.updateByPrimaryKeySelective(stuUser);
		String smsTempFlow = "";//短信模板流水号
		if(StuStatusEnum.Passed.getId().equals(stuUser.getStuStatusId())) {
			stuUser.setIsAudit(GlobalConstant.FLAG_Y);
			smsTempFlow = "dwjxres10001";
		}else if(StuStatusEnum.UnPassed.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsAudit(GlobalConstant.FLAG_N);
			smsTempFlow = "dwjxres10003";
		}
		if(count > 0 && StringUtil.isNotBlank(smsTempFlow)){
			SysUser user = userBiz.readSysUser(mp.get("userFlow"));
			this.msgBiz.sendSMS(user.getUserPhone(),smsTempFlow,user.getUserName(),stuUser.getResumeFlow(),"STU_USER_RESUME");
		}
		StuAuditProcessExample example = new StuAuditProcessExample();
		example.createCriteria().andResumeFlowEqualTo(mp.get("resumeFlow")).andUserFlowEqualTo(mp.get("userFlow"))
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<StuAuditProcess> auditProLst = this.auditMapper.selectByExample(example);
		StuAuditProcess auditProcess = new StuAuditProcess();

		if(null != auditProLst && auditProLst.size() > 0){
			auditProcess.setProcessFlow(auditProLst.get(0).getProcessFlow());
			auditProcess.setStuStatusId(mp.get("statusId"));
			auditProcess.setStuStatusName(StuStatusEnum.getNameById(mp.get("statusId")));
			auditProcess.setAuditContent(mp.get("reason"));
			this.auditMapper.updateByPrimaryKeySelective(auditProcess);
		}else{
			auditProcess.setProcessFlow(PkUtil.getUUID());
			auditProcess.setResumeFlow(mp.get("resumeFlow"));
			auditProcess.setUserFlow(mp.get("userFlow"));
			auditProcess.setStuStatusId(mp.get("statusId"));
			auditProcess.setStuStatusName(RegStatusEnum.getNameById(mp.get("statusId")));
			auditProcess.setAuditContent(mp.get("reason"));
			auditProcess.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			auditProcess.setOperUserName(GlobalContext.getCurrentUser().getUserName());
			auditProcess.setOperTime(DateUtil.getCurrentTime());
			GeneralMethod.setRecordInfo(auditProcess,true);
			this.auditMapper.insertSelective(auditProcess);
		}
		this.msgBiz.addSysMsg(mp.get("userFlow"), "报名材料审核结果："+stuUser.getStuStatusName(), auditProcess.getAuditContent());
	}

	@Override
	public int returnNurseInfo(String resumeFlow, String reason, String userFlow) {
		StuUserResume resume = new StuUserResume();
		resume.setResumeFlow(resumeFlow);
		resume.setStuStatusId(StuStatusEnum.UnPassed.getId());
		resume.setStuStatusName(StuStatusEnum.UnPassed.getName());
		resume.setNursingStatusId(StuStatusEnum.UnPassed.getId());
		resume.setNursingStatusName(StuStatusEnum.UnPassed.getName());
		resume.setIsBack(GlobalConstant.FLAG_Y);
		int count = this.stuUserMapper.updateByPrimaryKeySelective(resume);
		if(count > 0){
			SysUser user = userBiz.readSysUser(userFlow);
			this.msgBiz.sendSMS(user.getUserPhone(),"dwjxres10002",user.getUserName(),resume.getResumeFlow(),"STU_USER_RESUME");
		}
		StuAuditProcessExample example = new StuAuditProcessExample();
		example.createCriteria().andResumeFlowEqualTo(resumeFlow).andUserFlowEqualTo(userFlow)
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<StuAuditProcess> auditProLst = this.auditMapper.selectByExample(example);
		StuAuditProcess auditProcess = new StuAuditProcess();
		if(null != auditProLst && auditProLst.size() > 0){
			auditProcess.setProcessFlow(auditProLst.get(0).getProcessFlow());
			auditProcess.setStuStatusId(StuStatusEnum.UnPassed.getId());
			auditProcess.setStuStatusName(StuStatusEnum.UnPassed.getName());
			auditProcess.setAuditContent(reason);
			this.auditMapper.updateByPrimaryKeySelective(auditProcess);
		}else{
			auditProcess.setProcessFlow(PkUtil.getUUID());
			auditProcess.setResumeFlow(resumeFlow);
			auditProcess.setUserFlow(userFlow);
			auditProcess.setStuStatusId(StuStatusEnum.UnPassed.getId());
			auditProcess.setStuStatusName(StuStatusEnum.UnPassed.getName());
			auditProcess.setAuditContent(reason);
			auditProcess.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			auditProcess.setOperUserName(GlobalContext.getCurrentUser().getUserName());
			auditProcess.setOperTime(DateUtil.getCurrentTime());
			GeneralMethod.setRecordInfo(auditProcess,true);
			this.auditMapper.insertSelective(auditProcess);
		}
		this.msgBiz.addSysMsg(userFlow, "报名材料审核结果：报名信息已被退回，请重新填写。", auditProcess.getAuditContent());
		return 1;
	}
}
