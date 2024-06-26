package com.pinde.sci.biz.zseyjxres.impl;

import com.pinde.core.util.BeanUtil;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.zseyjxres.IZseyjxDocSingupBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.StuAuditProcessMapper;
import com.pinde.sci.dao.base.StuBatchMapper;
import com.pinde.sci.dao.base.StuHeadAuditStatusMapper;
import com.pinde.sci.dao.base.StuUserResumeMapper;
import com.pinde.sci.enums.res.RegStatusEnum;
import com.pinde.sci.enums.res.StuStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.zseyjxres.*;
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
public class ZseyjxDocSingupBizImpl implements IZseyjxDocSingupBiz {
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private StuUserResumeMapper stuUserMapper;
	@Autowired
	private StuAuditProcessMapper auditMapper;
	@Autowired
	private StuBatchMapper batchMapper;
	@Autowired
	private StuHeadAuditStatusMapper headAuditStatusMapper;
	@Autowired
	private IMsgBiz msgBiz;

	private static final String EXT_INFO_ROOT = "extInfo";
	private static final String EXT_INFO_ELE = "extInfoForm";
	private static final String WORK_RESUME_LIST_ELE = "workResumeList";
	private static final String WORK_RESUME_ELE = "workResumeForm";
	private static final String SPE_FORM_LIST_ELE = "speFormList";
	private static final String SPE_FORM_ELE = "speForm";
	private static final String PROJECT_LIST_ELE = "projectList";
	private static final String THESIS_LIST_ELE = "thesisList";
	private static final String EDUCATION_LIST_ELE = "educationList";
	private static final String REG_LIST_ELE = "regList";
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

				Element speFormListEle = root.element(SPE_FORM_LIST_ELE);
				if(speFormListEle!=null){
					List<Element> speEles = speFormListEle.elements();
					if(speEles!=null && speEles.size()>0){
						List<SpeForm> speFormList = new ArrayList<SpeForm>();
						for(Element resumeEle : speEles){
							SpeForm speForm = new SpeForm();
							List<Element> resumeAttrEles = resumeEle.elements();
							if(resumeAttrEles!=null && resumeAttrEles.size()>0){
								for(Element attr : resumeAttrEles){
									String attrName = attr.getName();
									String attrValue = attr.getText();
									setValue(speForm,attrName,attrValue);
								}
							}
							speFormList.add(speForm);
						}
						extInfo.setSpeFormList(speFormList);
					}
				}



				Element projectListEle = root.element(PROJECT_LIST_ELE);
				if(projectListEle!=null){
					List<Element> projectEles = projectListEle.elements();
					if(projectEles!=null && projectEles.size()>0){
						List<ProjectForm> resumeList = new ArrayList<ProjectForm>();
						for(Element resumeEle : projectEles){
							ProjectForm resume = new ProjectForm();
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
						extInfo.setProjectList(resumeList);
					}
				}
				Element thesisListEle = root.element(THESIS_LIST_ELE);
				if(thesisListEle!=null){
					List<Element> thesisEles = thesisListEle.elements();
					if(thesisEles!=null && thesisEles.size()>0){
						List<ThesisForm> resumeList = new ArrayList<ThesisForm>();
						for(Element resumeEle : thesisEles){
							ThesisForm resume = new ThesisForm();
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
						extInfo.setThesisList(resumeList);
					}
				}
				Element educationListEle = root.element(EDUCATION_LIST_ELE);
				if(educationListEle!=null){
					List<Element> educationEles = educationListEle.elements();
					if(educationEles!=null && educationEles.size()>0){
						List<EducationForm> resumeList = new ArrayList<EducationForm>();
						for(Element resumeEle : educationEles){
							EducationForm resume = new EducationForm();
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
						extInfo.setEducationList(resumeList);
					}
				}
				Element regListEle = root.element(REG_LIST_ELE);
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
						extInfo.setRegList(regList);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return extInfo;
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
	public int deleteHeadAudit(String resumeFlow) {
		StuHeadAuditStatusExample example = new StuHeadAuditStatusExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andResumeFlowEqualTo(resumeFlow);

		StuHeadAuditStatus status = new StuHeadAuditStatus();
		status.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		return headAuditStatusMapper.updateByExampleSelective(status,example);
	}

	@Override
	public int saveHeadstatus(Map<String, String> mp,List<SpeForm> speFormList,List<StuHeadAuditStatus> statuses) {
		int count=0;

		if(null!=statuses && statuses.size()>0){
            deleteHeadAudit(mp.get("resumeFlow"));
        }

		for(SpeForm speForm:speFormList){
			StuHeadAuditStatus headAuditStatus = new StuHeadAuditStatus();
			headAuditStatus.setStuStatusId(StuStatusEnum.Passing.getId());
			headAuditStatus.setStuStatusName(StuStatusEnum.Passing.getName());
			headAuditStatus.setDeptFlow(speForm.getSpeId());

			SysDept sysDept = deptBiz.readSysDept(speForm.getSpeId());
			headAuditStatus.setDeptName(sysDept.getDeptName());

			headAuditStatus.setResumeFlow(mp.get("resumeFlow"));
			headAuditStatus.setUserFlow(mp.get("userFlow"));
			headAuditStatus.setProcessFlow(PkUtil.getUUID());
			headAuditStatus.setStuBatId(mp.get("stuBatId"));
			headAuditStatus.setStuBatName(mp.get("stuBatName"));
			headAuditStatus.setIsBack(mp.get("isBack"));
			GeneralMethod.setRecordInfo(headAuditStatus,true);
			count = headAuditStatusMapper.insertSelective(headAuditStatus);
		}
		if(count>0){
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<StuHeadAuditStatus> getHeadStatus(String batchId,String resumeFlow,String deptName,String statusId,String deptFlow,String userFlow) {
		StuHeadAuditStatusExample example = new StuHeadAuditStatusExample();
		StuHeadAuditStatusExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(batchId)){
			criteria.andStuBatIdEqualTo(batchId);
		}
		if(StringUtil.isNotBlank(resumeFlow)){
			criteria.andResumeFlowEqualTo(resumeFlow);
		}
		if(StringUtil.isNotBlank(deptName)){
			criteria.andDeptNameEqualTo(deptName);
		}
		if(StringUtil.isNotBlank(statusId)){
			criteria.andStuStatusIdEqualTo(statusId);
		}
		if(StringUtil.isNotBlank(deptFlow)){
			criteria.andDeptFlowEqualTo(deptFlow);
		}
		if(StringUtil.isNotBlank(userFlow)){
			criteria.andUserFlowEqualTo(userFlow);
		}

		return headAuditStatusMapper.selectByExample(example);
	}

	@Override
	public void auditOption(Map<String, String> mp) {
		StuUserResume stuUser = new StuUserResume();
		stuUser.setResumeFlow(mp.get("resumeFlow"));
		stuUser.setStuStatusId(mp.get("statusId"));
		stuUser.setStuStatusName(StuStatusEnum.getNameById(mp.get("statusId")));
		String smsTempFlow = "";//短信模板流水号
		if(StuStatusEnum.Passed.getId().equals(stuUser.getStuStatusId()))
		{
			stuUser.setIsAudit(GlobalConstant.FLAG_Y);
			smsTempFlow = "zseyjxres10001";
		}else	if(StuStatusEnum.UnPassed.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsAudit(GlobalConstant.FLAG_N);
			smsTempFlow = "zseyjxres10003";
		}
		//添加是否已录取字段标识
		if(StuStatusEnum.Recruitted.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsRecruit(GlobalConstant.FLAG_Y);
			smsTempFlow = "zseyjxres10004";
		}else if(StuStatusEnum.UnRecruitted.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsRecruit(GlobalConstant.FLAG_N);
			smsTempFlow = "zseyjxres10005";
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
		this.msgBiz.addSysMsg(mp.get("userFlow"), "您的报名材料审核结果："+stuUser.getStuStatusName(), auditProcess.getAuditContent());
	}

	@Override
	public int updateHeadAndResume(String batchId,String resumeFlow, String processFlow, String userFlow, String reason, String statusId) {

		StuHeadAuditStatus headAuditStatus = new StuHeadAuditStatus();
		headAuditStatus.setAuditContent(reason);
		headAuditStatus.setProcessFlow(processFlow);
		headAuditStatus.setStuStatusName(StuStatusEnum.getNameById(statusId));
		headAuditStatus.setStuStatusId(statusId);
//		headAuditStatus.setResumeFlow(resumeFlow);
//		headAuditStatus.setStuBatId(batchId);
		int result = headAudit(headAuditStatus);

		boolean updateFlag = false;
		//所有科室审核完之后（即子表状态不为Passing）,并且其中有一个是 审核不通过的，那就更改主表为 科室审核不通过
		List<StuHeadAuditStatus> headAudit = getHeadStatus(batchId,resumeFlow,null,StuStatusEnum.Passing.getId(),null,userFlow);

		if(null!=headAudit && headAudit.size()>0){
			updateFlag = false;
		}else{
			//查询是否有审核不通过,只要存在，就更改主表状态为 科室审核不通过
			List<StuHeadAuditStatus> headAudit2 = getHeadStatus(batchId,resumeFlow,null,null,null,userFlow);
			if(null!=headAudit2 && headAudit2.size()>0){
				for(StuHeadAuditStatus audit :headAudit2){
					if(StuStatusEnum.UnPassed.getId().equals(audit.getStuStatusId())){
						updateFlag = true;
						break;
					}
				}
			}
		}
		if(updateFlag){
			StuUserResume resume = new StuUserResume();
			resume.setResumeFlow(resumeFlow);
			resume.setStuStatusId(StuStatusEnum.UnHeadPassed.getId());
			resume.setStuStatusName(StuStatusEnum.UnHeadPassed.getName());
			updateStuUserResume(resume);
		}

		//先查询 headAuditStatus表，都审核通过才修改stuUserResume表

		List<StuHeadAuditStatus> statuses = getHeadStatus(batchId,resumeFlow,null,null,null,null);
		boolean b=true;
		for(StuHeadAuditStatus status:statuses){
			if(!StuStatusEnum.Passed.getId().equals(status.getStuStatusId())){
				b=false;
				break;
			}
		}
		if(b){
			StuUserResume resume = new StuUserResume();
			resume.setResumeFlow(resumeFlow);
			resume.setStuStatusId(StuStatusEnum.Passed.getId());
			resume.setStuStatusName(StuStatusEnum.Passed.getName());
			 updateStuUserResume(resume);
			//当全部审核同过，给学员发送通知
			this.msgBiz.addSysMsg(userFlow, "您的报名材料审核结果："+StuStatusEnum.Passed.getName(), null);
		}
		if(result>0){
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int updateheadAudit(String batchId,String resumeFlow,String userFlow,String deptFlow,String statusId) {
		int count=0;
		StuHeadAuditStatusExample example = new StuHeadAuditStatusExample();
		StuHeadAuditStatusExample.Criteria  criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andResumeFlowEqualTo(resumeFlow);
		criteria.andUserFlowEqualTo(userFlow);
		criteria.andDeptFlowEqualTo(deptFlow);
		criteria.andStuBatIdEqualTo(batchId);
		StuHeadAuditStatus headAuditStatus = new StuHeadAuditStatus();
		headAuditStatus.setStuStatusId(statusId);
		headAuditStatus.setStuStatusName(StuStatusEnum.getNameById(statusId));
		count = headAuditStatusMapper.updateByExampleSelective(headAuditStatus,example);
		//当所有科室都撤销时，即所有子表状态都更改为待审核时，同时更改主表的审核状态为待 科室审核。
		List<StuHeadAuditStatus> statusList = getHeadStatus(batchId,resumeFlow,null,null,deptFlow,userFlow);
		boolean flag=true;
		for(StuHeadAuditStatus status:statusList){
			if(!StuStatusEnum.Passing.getId().equals(status.getStuStatusId())){
				flag =false;
				break;
			}
		}
		if(flag){
			StuUserResume resume = new StuUserResume();
			resume.setResumeFlow(resumeFlow);
			resume.setStuStatusId(StuStatusEnum.HeadPassing.getId());
			resume.setStuStatusName(StuStatusEnum.HeadPassing.getName());
			updateStuUserResume(resume);
		}

		if(count>GlobalConstant.ZERO_LINE){
			return GlobalConstant.ONE_LINE;
		}
		return count;
	}

	@Override
	public int headAudit(StuHeadAuditStatus headAuditStatus) {
		int count=0;
		GeneralMethod.setRecordInfo(headAuditStatus,false);
		if(StringUtil.isNotBlank(headAuditStatus.getProcessFlow())){
			count= headAuditStatusMapper.updateByPrimaryKeySelective(headAuditStatus);
		}
		if(count>GlobalConstant.ZERO_LINE){
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public void changeRecruit(String resumeFlow, String statusId) {
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
		}else	if(StuStatusEnum.UnAdmitd.getId().equals(stuUser.getStuStatusId())){
			stuUser.setIsAdmited(GlobalConstant.FLAG_N);
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

			List<SpeForm> speFormList = extInfo.getSpeFormList();
			if(speFormList!=null && speFormList.size()>0){
				Element speListEle = root.addElement(SPE_FORM_LIST_ELE);
				for(SpeForm resume : speFormList){
					Element resumeEle = speListEle.addElement(SPE_FORM_ELE);
					Map<String,String> resumeFiledMap = getClassFieldMap(resume);
					if(resumeFiledMap!=null && resumeFiledMap.size()>0){
						for(String key : resumeFiledMap.keySet()){
							Element item = resumeEle.addElement(key);
							item.setText(resumeFiledMap.get(key));
						}
					}
				}
			}


			List<ProjectForm> projectList = extInfo.getProjectList();
			if(projectList!=null && projectList.size()>0){
				Element projectListEle = root.addElement(PROJECT_LIST_ELE);
				for(ProjectForm resume : projectList){
					Element resumeEle = projectListEle.addElement(PROJECT_LIST_ELE);
					Map<String,String> resumeFiledMap = getClassFieldMap(resume);
					if(resumeFiledMap!=null && resumeFiledMap.size()>0){
						for(String key : resumeFiledMap.keySet()){
							Element item = resumeEle.addElement(key);
							item.setText(resumeFiledMap.get(key));
						}
					}
				}
			}
			List<ThesisForm> thesisList = extInfo.getThesisList();
			if(thesisList!=null && thesisList.size()>0){
				Element thesisListEle = root.addElement(THESIS_LIST_ELE);
				for(ThesisForm resume : thesisList){
					Element resumeEle = thesisListEle.addElement(THESIS_LIST_ELE);
					Map<String,String> resumeFiledMap = getClassFieldMap(resume);
					if(resumeFiledMap!=null && resumeFiledMap.size()>0){
						for(String key : resumeFiledMap.keySet()){
							Element item = resumeEle.addElement(key);
							item.setText(resumeFiledMap.get(key));
						}
					}
				}
			}
			List<EducationForm> educationList = extInfo.getEducationList();
			if(educationList!=null && educationList.size()>0){
				Element educationListEle = root.addElement(EDUCATION_LIST_ELE);
				for(EducationForm resume : educationList){
					Element resumeEle = educationListEle.addElement(EDUCATION_LIST_ELE);
					Map<String,String> resumeFiledMap = getClassFieldMap(resume);
					if(resumeFiledMap!=null && resumeFiledMap.size()>0){
						for(String key : resumeFiledMap.keySet()){
							Element item = resumeEle.addElement(key);
							item.setText(resumeFiledMap.get(key));
						}
					}
				}
			}
			List<RegForm> regList = extInfo.getRegList();
			if(regList!=null && regList.size()>0){
				Element regListEle = root.addElement(REG_LIST_ELE);
				for(RegForm reg : regList){
					Element regEle = regListEle.addElement(REG_ELE);
					Map<String,String> rgeFiledMap = getClassFieldMap(reg);
					if(rgeFiledMap!=null && rgeFiledMap.size()>0){
						for(String key : rgeFiledMap.keySet()){
							Element item = regEle.addElement(key);
							item.setText(rgeFiledMap.get(key));
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
			//发现 因为mergeObject是如果为null不覆盖的，所以如果科研信息已经填过信息并保存之后，即使界面操作删除还是会有值的。
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
	public int returnInfo(String resumeFlow, String reason,String userFlow) {
		SysUser user = null;
		StuUserResume resume = new StuUserResume();
		resume.setResumeFlow(resumeFlow);
		resume.setStuStatusId(StuStatusEnum.UnPassed.getId());
		resume.setStuStatusName(StuStatusEnum.UnPassed.getName());
		resume.setIsBack(GlobalConstant.FLAG_Y);
		int count = this.stuUserMapper.updateByPrimaryKeySelective(resume);
		if(count > 0){
			 user = userBiz.readSysUser(userFlow);
			this.msgBiz.sendSMS(user.getUserPhone(),"zseyjxres10002",user.getUserName(),resume.getResumeFlow(),"STU_USER_RESUME");
		}
		//当管理员退回操作后，更改科主任审核表中的 退回标识 为Y
		StuHeadAuditStatus headAuditStatus = new StuHeadAuditStatus();
		headAuditStatus.setIsBack(GlobalConstant.FLAG_Y);
//		headAuditStatus.setStuStatusId(StuStatusEnum.UnPassed.getId());
//		headAuditStatus.setStuStatusName(StuStatusEnum.UnPassed.getName());

		StuHeadAuditStatusExample headExample = new StuHeadAuditStatusExample();
		StuHeadAuditStatusExample.Criteria criteria = headExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andResumeFlowEqualTo(resumeFlow);

		headAuditStatusMapper.updateByExampleSelective(headAuditStatus,headExample);
		//给学员所选每个科室的科主任发送消息
		 //查询该学员所选的所有科室

		StuUserResumeExample resumeExample = new StuUserResumeExample();
		resumeExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andResumeFlowEqualTo(resumeFlow);
		List<StuUserResume> stuUserLst = this.stuUserMapper.selectByExampleWithBLOBs(resumeExample);
		ExtInfoForm extInfo = null;
		if(null!=stuUserLst && stuUserLst.size()>0){
			extInfo = parseExtInfoXml(stuUserLst.get(0).getResumeInfo());
			if(null!=extInfo && null!=extInfo.getSpeFormList() && extInfo.getSpeFormList().size()>0){
				for(SpeForm speForm:extInfo.getSpeFormList()){
					String deptFlow = null;
					List<SysDict> dwjxList = DictTypeEnum.DwjxSpe.getSysDictList();
					for(SysDict dict:dwjxList){
						if(dict.getDictName().equals(speForm.getSpeId())){
							deptFlow = dict.getDictId();
							break;
						}
					}
					msgBiz.addSysMsg(deptFlow, user.getUserName()+"的报名信息已被管理员退回！",reason);

//					List<SysUser> headUserList = userBiz.searcherUserByDeptFlow(deptFlow);
//					if(null!=headUserList && headUserList.size()>0){
//						msgBiz.addSysMsg(headUserList.get(0).getUserFlow(), user.getUserName()+"的报名信息已被管理员退回！",null);
//					}

				}
			}

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
		this.msgBiz.addSysMsg(userFlow, "您的报名材料审核结果：报名信息已被退回，请重新填写。", auditProcess.getAuditContent());
		return GlobalConstant.ONE_LINE;
	}

	@Override
	public int updateStuUserResume(StuUserResume stuUserResume) {
		return stuUserMapper.updateByPrimaryKeySelective(stuUserResume);
	}
}
