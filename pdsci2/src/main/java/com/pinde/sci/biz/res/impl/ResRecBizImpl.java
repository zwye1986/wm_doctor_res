package com.pinde.sci.biz.res.impl;

import com.google.common.collect.Lists;
import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.util.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.res.IResSchProcessExpressBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.common.util.JspFormUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.res.ResAppealExtMapper;
import com.pinde.sci.dao.res.ResDoctorSchProcessExtMapper;
import com.pinde.sci.dao.res.ResRecExtMapper;
import com.pinde.sci.dao.sch.SchArrangeResultExtMapper;
import com.pinde.sci.enums.jszy.JszyResTrainYearEnum;
import com.pinde.sci.enums.jszy.JszyTrainCategoryEnum;
import com.pinde.sci.enums.res.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.res.ResRecForm;
import com.pinde.sci.keyUtil.PdUtil;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.ResAppealExample.Criteria;
import com.pinde.sci.model.res.ResDoctorSchProcessExt;
import com.pinde.sci.model.res.ResRecExt;
import com.pinde.sci.model.res.SchArrangeResultExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.poi.ss.usermodel.Workbook;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor=Exception.class)
public class ResRecBizImpl implements IResRecBiz {
	private static Logger logger = LoggerFactory.getLogger(ResRecBizImpl.class);
	/**
	 * 要求标识
	 */
	final static private String REQ_NUM = "ReqNum";
	/**
	 * 完成数标识
	 */
	final static private String FINISHED = "Finished";
	/**
	 * 总数标识
	 */
	final static private String TOTAL = "Total";
	@Autowired
	private ResRecMapper resRecMapper;
	/*@Autowired
	private ResRecCampaignRegistryMapper campaignRegistryMapper;
	@Autowired
	private ResRecCaseRegistryMapper caseRegistryMapper;
	@Autowired
	private ResRecDiseaseRegistryMapper diseaseRegistryMapper;
	@Autowired
	private ResRecLanguageRegistryMapper languageRegistryMapper;
	@Autowired
	private ResRecOperationRegistryMapper operationRegistryMapper;
	@Autowired
	private ResRecSkillRegistryMapper skillRegistryMapper;*/
	@Autowired
	private ResRecExtMapper resRecExtMapper;
	/*@Autowired
	private ResRecCampaignRegistryExtMapper campaignRegistryExtMapper;
	@Autowired
	private ResRecCaseRegistryExtMapper caseRegistryExtMapper;
	@Autowired
	private ResRecDiseaseRegistryExtMapper diseaseRegistryExtMapper;
	@Autowired
	private ResRecLanguageRegistryExtMapper languageRegistryExtMapper;
	@Autowired
	private ResRecOperationRegistryExtMapper operationRegistryExtMapper;
	@Autowired
	private ResRecSkillRegistryExtMapper skillRegistryExtMapper;*/
	@Autowired
	private ResAppealExtMapper resAppealExtMapper;
	@Autowired
	private ResDoctorSchProcessMapper resDoctorProcessMapper;
	@Autowired
	private ResAppealMapper resAppealMapper;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private ResSchProcessExpressMapper resSchProcessExpressMapper;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ResDoctorSchProcessExtMapper processExtBiz;
	@Autowired
	private ResAppealExtMapper appealExtMapper;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private SchRotationDeptReqMapper rotationDeptReqMapper;
	@Autowired
	private SchDoctorDeptMapper docDeptMapper;
	@Autowired
	private SchArrangeResultExtMapper resultExtMapper;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private ResStandardDeptPerMapper perMapper;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;

//	private IrbSingleForm findTheForm(String currVer,String recTypeId,String recForm){
//		String productType = StringUtil.defaultIfEmpty(recForm,InitConfig.getSysCfg("res_form_category"));//与对应开关保持一致
//		productType = StringUtil.defaultIfEmpty(productType,GlobalConstant.RES_FORM_PRODUCT);
//
//		if (StringUtil.isBlank(currVer)){
//			currVer = InitConfig.resFormRequestUtil.getVersionMap().get(productType+"_"+recTypeId);
//		}
//		if(StringUtil.isBlank(currVer)){
//			currVer = InitConfig.resFormRequestUtil.getVersionMap().get(GlobalConstant.RES_FORM_PRODUCT+"_"+recTypeId);
//		}
//
//		currVer = StringUtil.defaultIfEmpty(currVer,GlobalConstant.RES_FORM_PRODUCT_VER);
//
//		Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(recTypeId);
//		IrbSingleForm singleForm = singleFormMap.get(productType+"_"+currVer);
//		if(singleForm==null){
//			singleForm = singleFormMap.get(GlobalConstant.RES_FORM_PRODUCT+"_"+currVer);
//		}
//		if(singleForm == null){
//			throw new RuntimeException("未发现表单 模版类型:"+productType+",表单类型:"+ResRecTypeEnum.getNameById(recTypeId)+",版本号:"+currVer);
//		}
//		return singleForm;
//	}

	/**
	 * 获取匹配的itemGroup name的item索引
	 *
	 * @param pattern
	 * @param datasMap
	 * @return
	 */
	private static int getMatchDataIndex(String pattern, Map<String, String[]> datasMap) {
		int count = 0;
		Set<String> keys = datasMap.keySet();
		for (String key : keys) {
			if (key.startsWith(pattern + "_")) {
				String[] values = datasMap.get(key);
				count = values.length;
				break;
			}
		}
		return count;
	}

	/**
	 * 创建item节点 , 并根据索引设置值
	 *
	 * @param itemList
	 * @param ele
	 * @param datasMap
	 * @param index
	 */
	private static void iteratorItem(List<Element> itemList, Element ele, Map<String, String[]> datasMap, int index) {
		if (itemList != null && itemList.size() > 0) {
			for (Element item : itemList) {
				Element itemEle = ele.addElement("item");
				String name = item.attributeValue("name");
				itemEle.addAttribute("name", name);
				String remark = item.attributeValue("remark");
				itemEle.addAttribute("remark", remark);
				String[] values = datasMap.get(name);
				String multiple = item.attributeValue("multiple");
				itemEle.addAttribute("multiple", multiple);
				Element valEle = itemEle.addElement("value");
				if (values != null && values.length >= index + 1) {
					valEle.setText(values[index]);
				}
			}
		}
	}

	@Override
	public int edit(ResRec rec) {
		if(rec!=null){
			if(StringUtil.isNotBlank(rec.getRecFlow())){//修改
				GeneralMethod.setRecordInfo(rec, false);
				return this.resRecMapper.updateByPrimaryKeySelective(rec);
			}else{//新增
				rec.setRecFlow(PkUtil.getUUID());
				if(!ResRecTypeEnum.AnnualTrainForm.getId().equals(rec.getRecTypeId())){//培训年度
					rec.setOperTime(DateUtil.getCurrDateTime());
				}
				GeneralMethod.setRecordInfo(rec, true);
				return this.resRecMapper.insertSelective(rec);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int editByType(ResRec rec, String recTypeId) {
//		if(rec!=null){
//			if(StringUtil.isNotBlank(rec.getRecFlow())){//修改
//				GeneralMethod.setRecordInfo(rec, false);
//				if (recTypeId.equals(RegistryTypeEnum.CampaignRegistry.getId())) {
//					ResRecCampaignRegistry campaignRegistry = new ResRecCampaignRegistry();
//					BeanUtils.copyProperties(rec, campaignRegistry);
//					return campaignRegistryMapper.updateByPrimaryKeySelective(campaignRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.CaseRegistry.getId())) {
//					ResRecCaseRegistry caseRegistry = new ResRecCaseRegistry();
//					BeanUtils.copyProperties(rec, caseRegistry);
//					return caseRegistryMapper.updateByPrimaryKeySelective(caseRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.DiseaseRegistry.getId())) {
//					ResRecDiseaseRegistry diseaseRegistry = new ResRecDiseaseRegistry();
//					BeanUtils.copyProperties(rec, diseaseRegistry);
//					return diseaseRegistryMapper.updateByPrimaryKeySelective(diseaseRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.LanguageTeachingResearch.getId())) {
//					ResRecLanguageRegistry languageRegistry = new ResRecLanguageRegistry();
//					BeanUtils.copyProperties(rec, languageRegistry);
//					return languageRegistryMapper.updateByPrimaryKeySelective(languageRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.OperationRegistry.getId())) {
//					ResRecOperationRegistry operationRegistry = new ResRecOperationRegistry();
//					BeanUtils.copyProperties(rec, operationRegistry);
//					return operationRegistryMapper.updateByPrimaryKeySelective(operationRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.SkillRegistry.getId())) {
//					ResRecSkillRegistry skillRegistry = new ResRecSkillRegistry();
//					BeanUtils.copyProperties(rec, skillRegistry);
//					return skillRegistryMapper.updateByPrimaryKeySelective(skillRegistry);
//				}
//			}else{//新增
//				rec.setRecFlow(PkUtil.getUUID());
//				if(!ResRecTypeEnum.AnnualTrainForm.getId().equals(rec.getRecTypeId())){//培训年度
//					rec.setOperTime(DateUtil.getCurrDateTime());
//				}
//				GeneralMethod.setRecordInfo(rec, true);
//				if (recTypeId.equals(RegistryTypeEnum.CampaignRegistry.getId())) {
//					ResRecCampaignRegistry campaignRegistry = new ResRecCampaignRegistry();
//					BeanUtils.copyProperties(rec, campaignRegistry);
//					return campaignRegistryMapper.insertSelective(campaignRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.CaseRegistry.getId())) {
//					ResRecCaseRegistry caseRegistry = new ResRecCaseRegistry();
//					BeanUtils.copyProperties(rec, caseRegistry);
//					return caseRegistryMapper.insertSelective(caseRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.DiseaseRegistry.getId())) {
//					ResRecDiseaseRegistry diseaseRegistry = new ResRecDiseaseRegistry();
//					BeanUtils.copyProperties(rec, diseaseRegistry);
//					return diseaseRegistryMapper.insertSelective(diseaseRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.LanguageTeachingResearch.getId())) {
//					ResRecLanguageRegistry languageRegistry = new ResRecLanguageRegistry();
//					BeanUtils.copyProperties(rec, languageRegistry);
//					return languageRegistryMapper.insertSelective(languageRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.OperationRegistry.getId())) {
//					ResRecOperationRegistry operationRegistry = new ResRecOperationRegistry();
//					BeanUtils.copyProperties(rec, operationRegistry);
//					return operationRegistryMapper.insertSelective(operationRegistry);
//				} else if (recTypeId.equals(RegistryTypeEnum.SkillRegistry.getId())) {
//					ResRecSkillRegistry skillRegistry = new ResRecSkillRegistry();
//					BeanUtils.copyProperties(rec, skillRegistry);
//					return skillRegistryMapper.insertSelective(skillRegistry);
//				}
//			}
//		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int editWithFiles(ResRec rec,List<PubFile> pubFiles) {
		if(rec!=null){
			if(StringUtil.isNotBlank(rec.getRecFlow())){//修改
				GeneralMethod.setRecordInfo(rec, false);
				if(pubFiles!=null&&pubFiles.size()>0){
					for(PubFile file : pubFiles){
						file.setFileFlow(PkUtil.getUUID());
						file.setProductFlow(rec.getRecFlow());
						GeneralMethod.setRecordInfo(file, true);
						fileBiz.addFile(file);
					}
				}
				return this.resRecMapper.updateByPrimaryKeySelective(rec);
			}else{//新增
				rec.setRecFlow(PkUtil.getUUID());
				if(pubFiles!=null&&pubFiles.size()>0){
					for(PubFile file : pubFiles){
						file.setFileFlow(PkUtil.getUUID());
						file.setProductFlow(rec.getRecFlow());
						GeneralMethod.setRecordInfo(file, true);
						fileBiz.addFile(file);
					}
				}
				if(!ResRecTypeEnum.AnnualTrainForm.getId().equals(rec.getRecTypeId())){//培训年度
					rec.setOperTime(DateUtil.getCurrDateTime());
				}
				GeneralMethod.setRecordInfo(rec, true);
				return this.resRecMapper.insertSelective(rec);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public int editRec(ResRec rec) {
		if(StringUtil.isNotBlank(rec.getRecFlow())){
			GeneralMethod.setRecordInfo(rec, false);
			return resRecMapper.updateByPrimaryKeySelective(rec);
		}else{
			rec.setRecFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(rec, true);
			return resRecMapper.insertSelective(rec);
		}
	}

	@Override
	public int editAndOut(ResRec rec,ResDoctorSchProcess process){
		if(process!=null){
			resDoctorProcessMapper.updateByPrimaryKeySelective(process);
		}
		return edit(rec);
	}

	/**
	 *  保存表单
	 * @param formFileName 登记类型
	 * @param recFlow 登记表流水号
	 * @param schDeptFlow 轮转科室流水号
	 * @param rotationFlow 轮转方案流水号
	 * @param req
	 * @param orgFlow
	 * @param medicineTypeId
	 * @return
	 */
	@Override
	public int saveRegistryForm(String formFileName, String recFlow, String schDeptFlow, String rotationFlow, HttpServletRequest req, String orgFlow, String medicineTypeId,String canEditAppendix){
		//当前操作的用户
		SysUser currUser = GlobalContext.getCurrentUser();
		//当前时间
		String currTime = DateUtil.getCurrDateTime();

		if(StringUtil.isNotBlank(formFileName)){
			String recordFlow="";
			//获取过程flow
			String processFlow = req.getParameter("processFlow");
			//读取过程数据
			ResDoctorSchProcess process = processBiz.read(processFlow);
			if(process!=null) {
				SchRotationDept dept = rotationDeptBiz.readStandardRotationDept(process.getSchResultFlow());
				if(dept!=null)
				{
					recordFlow=dept.getRecordFlow();
				}
			}

			String productType = getRecFormByCfg(rotationFlow,recordFlow,formFileName);

			String currVer = InitConfig.resFormRequestUtil.getVersionMap().get(productType+"_"+formFileName);
			if(StringUtil.isBlank(currVer)){
				currVer = InitConfig.resFormRequestUtil.getVersionMap().get(GlobalConstant.RES_FORM_PRODUCT+"_"+formFileName);
			}
			if(StringUtil.isBlank(currVer)){
				currVer = GlobalConstant.RES_FORM_PRODUCT_VER;
			}
			Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(formFileName);
			IrbSingleForm singleForm = singleFormMap.get(productType+"_"+currVer);
			if(singleForm == null){
				singleForm = singleFormMap.get(GlobalConstant.RES_FORM_PRODUCT+"_"+currVer);
			}
			if(singleForm == null){
				throw new RuntimeException("未发现表单 模版类型:"+productType+",表单类型:"+ResRecTypeEnum.getNameById(formFileName)+",版本号:"+currVer);
			}

			if(singleForm != null){
				//是否存在总分
				String totalScore = req.getParameter("totalScore");
				//如果存在总分回写进过程表
				Double score = new Double(0);
				if(process!=null){
					boolean toUpdate = false;
					//是否出科，是则更新出科标记为Y，更新当前轮转标记为N，更新实际轮转结束日期为当前日期
					if(GlobalConstant.FLAG_Y.equals(req.getParameter("isAgree"))){
						process.setSchFlag(GlobalConstant.FLAG_Y);
						process.setIsCurrentFlag(GlobalConstant.FLAG_N);
//						process.setEndDate(DateUtil.getCurrDate());
						toUpdate = true;
					}
					//存在总分则更新过程表的出科成绩
					if(StringUtil.isNotBlank(totalScore)){
						score = Double.parseDouble(totalScore);
						process.setSchScore(BigDecimal.valueOf(score));
						toUpdate = true;
					}
					//江苏中医 学生出科考核表，如果学员无考试权限则出科考核表的总分为其出科的分数
					String operUserFlow = req.getParameter("operUserFlow");
					if(StringUtil.isBlank(operUserFlow)){
						operUserFlow=GlobalContext.getCurrentUser().getUserFlow();
					}
					String totalScore2 = req.getParameter("totalScore2");
					boolean f=resDoctorBiz.getCkkPower(operUserFlow);
					if(!f){
						//存在总分则更新过程表的出科成绩
						if(StringUtil.isNotBlank(totalScore2)){
							score = Double.parseDouble(totalScore2);
							process.setSchScore(BigDecimal.valueOf(score));
							toUpdate = true;
						}
					}
					if(toUpdate){
						processBiz.edit(process);
					}
				}
				//读取一条表单记录
				ResRec rec = readResRec(recFlow);
				if (rec == null){
					rec = new ResRec();
					//轮转科室信息
					SchDept schDept = schDeptBiz.readSchDept(schDeptFlow);
					rec.setSchDeptFlow(schDeptFlow);
					if(schDept!=null){
						rec.setSchDeptName(schDept.getSchDeptName());
						rec.setOrgFlow(schDept.getOrgFlow());
						rec.setOrgName(schDept.getOrgName());
						rec.setDeptFlow(schDept.getDeptFlow());
						rec.setDeptName(schDept.getDeptName());
					}

					rec.setMedicineType(medicineTypeId);
					//表单类型
					rec.setRecTypeId(formFileName);
					rec.setRecTypeName(ResRecTypeEnum.valueOf(formFileName).getTypeName());
					//表单版本
					rec.setRecVersion(currVer);
					rec.setRecForm(productType);

					//表单学员 设置表单的用户和机构信息
					String operUserFlow = req.getParameter("operUserFlow");
					if(StringUtil.isNotBlank(operUserFlow)){
						ResDoctor doc = resDoctorBiz.readDoctor(operUserFlow);
						rec.setOperUserFlow(doc.getDoctorFlow());
						rec.setOperUserName(doc.getDoctorName());
						if(!StringUtil.isNotBlank(rec.getOrgFlow())){
							rec.setOrgFlow(doc.getOrgFlow());
							rec.setOrgName(doc.getOrgName());
						}
					}else{
						rec.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
						rec.setOperUserName(GlobalContext.getCurrentUser().getUserName());
						if(!StringUtil.isNotBlank(rec.getOrgFlow())){
							rec.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
							rec.setOrgName(GlobalContext.getCurrentUser().getOrgName());
						}
					}

					//记录该表单在哪个过程中产生的
					rec.setProcessFlow(processFlow);
				}

				String recContent = "";
				//根据表单类型组织大字段
				if (GlobalRecTypeEnum.Ethics.getId().equals(rec.getRecTypeId()) || //医德医风
						GlobalRecTypeEnum.Document.getId().equals(rec.getRecTypeId()) || //医学文案
						GlobalRecTypeEnum.NursingSkills.getId().equals(rec.getRecTypeId()) || //护理操作技能
						GlobalRecTypeEnum.ClinicalEnglish.getId().equals(rec.getRecTypeId()) || //临床英语应用
						GlobalRecTypeEnum.Appraisal.getId().equals(rec.getRecTypeId())|| //实习总鉴定
						GlobalRecTypeEnum.CourseScore.getId().equals(rec.getRecTypeId())) { //实习成绩单
					recContent = getRecContent(formFileName, singleForm, req);
				} else if(ResRecTypeEnum.TeachRegistry.getId().equals(rec.getRecTypeId())){ //教学登记
					recContent = getRecContent(formFileName, singleForm.getItemList(), req,rec.getRecContent());
				} else if(ResRecTypeEnum.AfterEvaluation.getId().equals(rec.getRecTypeId())){ //出科考核表
					String roleFlag = req.getParameter("roleFlag");
					recContent = getEvaluationContent(
							formFileName,
							singleForm.getItemList(),
							req,roleFlag,
							rec.getRecContent()
					);
				}else{
					recContent = getRecContent(rec.getRecContent(),formFileName, singleForm.getItemList(), req);
				}

				//老师审核状态
				if(req.getParameter("auditStatusId")!=null){
					rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
					rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
					rec.setAuditTime(currTime);
					rec.setAuditUserFlow(currUser.getUserFlow());
					rec.setAuditUserName(currUser.getUserName());
				}

				//科主任审核状态
				if(req.getParameter("headAuditStatusId")!=null){
					rec.setHeadAuditStatusId(RecStatusEnum.HeadAuditY.getId());
					rec.setHeadAuditStatusName(RecStatusEnum.HeadAuditY.getName());
					rec.setHeadAuditTime(currTime);
					rec.setHeadAuditUserFlow(currUser.getUserFlow());
					rec.setHeadAuditUserName(currUser.getUserName());
				}

				//基地主任审核状态
				if(req.getParameter("managerAuditStatusId")!=null){
					rec.setManagerAuditStatusId(RecStatusEnum.ManagerAuditY.getId());
					rec.setManagerAuditStatusName(RecStatusEnum.ManagerAuditY.getName());
					rec.setManagerAuditStatusId(RecStatusEnum.ManagerAuditN.getId());
					rec.setManagerAuditStatusName(RecStatusEnum.ManagerAuditN.getName());
					rec.setManagerAuditTime(currTime);
					rec.setManagerAuditUserFlow(currUser.getUserFlow());
					rec.setManagerAuditUserName(currUser.getUserName());
				}

				//医院管理员审核状态
				if(req.getParameter("adminAuditStatusId")!=null){
					rec.setAdminAuditStatusId(RecStatusEnum.AdminAuditY.getId());
					rec.setAdminAuditStatusName(RecStatusEnum.AdminAuditY.getName());
					rec.setAdminAuditTime(currTime);
					rec.setAdminAuditUserFlow(currUser.getUserFlow());
					rec.setAdminAuditUserName(currUser.getUserName());
				}

				//培训年度
				if(ResRecTypeEnum.AnnualTrainForm.getId().equals(rec.getRecTypeId())){
					rec.setOperTime(req.getParameter("trainDate"));
				}

				//更新大字段内容
				rec.setRecContent(recContent);
				if(!GlobalConstant.FLAG_Y.equals(canEditAppendix)){
					rec.setAuditStatusId("");
					rec.setAuditStatusName("");
				}
				String useContent = recContent;

				String regItem = req.getParameter("regItem");
				String xmlItemName = req.getParameter("xmlItemName");
				if(!StringUtil.isNotBlank(rec.getRecFlow())){
					String[] itemIds = req.getParameterValues("itemId");
					String[] itemNames = req.getParameterValues("itemName");
					if(itemIds!=null && itemIds.length>0){
						for(int i = 0 ; i<itemIds.length ; i++){
							String itemId = itemIds[i];
							String itemName = null;
							if(itemNames!=null && i<itemNames.length){
								itemName = itemNames[i];
							}else{
								itemName = "";
							}

							rec.setItemName(itemName);

							if(StringUtil.isNotBlank(xmlItemName)){
								if(GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(itemId)){
									itemName = regItem;
								}
								recContent = replaceNodeValue(useContent,xmlItemName,itemName,itemId);
								rec.setRecContent(recContent);
							}
							rec.setRecFlow(null);
							rec.setItemId(itemId);
							edit(rec);
						}
					}else{
						edit(rec);
					}
				}else{
					String itemId = req.getParameter("itemId");
					String itemName = req.getParameter("itemName");
					rec.setItemName(itemName);
					if(StringUtil.isNotBlank(xmlItemName)){
						if(GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(itemId)){
							itemName = regItem;
						}
						recContent = replaceNodeValue(useContent,xmlItemName,itemName,itemId);
						rec.setRecContent(recContent);
					}
					rec.setItemId(itemId);
					edit(rec);
				}

				return GlobalConstant.ONE_LINE;
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	private String replaceNodeValue(String content,String nodeName,String value,String itemId){
		String resultContent = null;
		try {
			Document doc = DocumentHelper.parseText(content);
			Element rootEle = doc.getRootElement();
			Element nade = rootEle.element(nodeName);
			if(nade!=null){
				if(!GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(itemId)){
					nade.addAttribute("id",itemId);
				}
				nade.setText(value);
			}
			resultContent = doc.asXML();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return resultContent;
	}

	private String getRecContent(String formName,List<Element> list,HttpServletRequest req,String recContent){
		Element rootEle = null;
		if(StringUtil.isNotBlank(recContent)){
			try {
				rootEle = DocumentHelper.parseText(recContent).getRootElement();
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}else{
			rootEle = DocumentHelper.createElement(formName);
		}

		if(list!=null && list.size()>0){
			for(Element e : list){
				String tagName = e.getName();
				String recordFlow = req.getParameter("recordFlow");

				if("itemGroup".equals(tagName)){
					if(recordFlow!=null){
						String delFlag = req.getParameter("delFlag");
						if(GlobalConstant.FLAG_Y.equals(delFlag)){
							Node delNode = rootEle.selectSingleNode("itemGroup[@recordFlow='"+recordFlow+"']");
							delNode.detach();
						}else{
							Element group = null;
							if(StringUtil.isNotBlank(recordFlow)){
								group = (Element)rootEle.selectSingleNode("itemGroup[@recordFlow='"+recordFlow+"']");
								group.setText("");
								group = getContentEle("",e.elements(),req,group);
							}else{
								group = DocumentHelper.createElement("itemGroup");
								group.addAttribute("recordFlow",PkUtil.getUUID());
								group.addAttribute("name",e.attributeValue("name"));
//							group.addAttribute("title",e.attributeValue("remark"));
								group = getContentEle("",e.elements(),req,group);
								rootEle.add(group);
							}

						}
					}
				}else{
					if(recordFlow==null){
						String nodeName = e.attributeValue("name");
						Element itemEle = (Element)rootEle.selectSingleNode(nodeName);
						if(itemEle!=null){
							itemEle.detach();
						}
						String multiple = e.attributeValue("multiple");
						if(!GlobalConstant.FLAG_Y.equals(multiple)) {
							String value = req.getParameter(nodeName);
							Element element = DocumentHelper.createElement(nodeName);
							if (StringUtil.isNotBlank(value)) {
								element.setText(value);
							}
							rootEle.add(element);
						}else {
							String[] values = req.getParameterValues(nodeName);
							Element element = DocumentHelper.createElement(nodeName);
							if(values != null && values.length > 0) {
								for (String value : values) {
									Element valueEle = DocumentHelper.createElement("value");
									if (StringUtil.isNotBlank(value)) {
										valueEle.setText(value);
									}
									element.add(valueEle);
								}
							}
							rootEle.add(element);
						}
					}
				}
			}
		}
		return rootEle.asXML();
	}

	//为rootEle组织内容
	private Element getContentEle(String recContent,List<Element> list,HttpServletRequest req,Element rootEle){
		if(list !=null && list.size()>0 && rootEle!=null){
			Element oldRootEle = null;
			if(StringUtil.isNotBlank(recContent)){
				try {
					oldRootEle = DocumentHelper.parseText(recContent).getRootElement();
				} catch (DocumentException e) {
					e.printStackTrace();
				}
			}
			for(Element itemEle : list){
				String eleName=itemEle.getName();
				if("itemGroup".equals(eleName))
				{
					String name = itemEle.attributeValue("name");
					Element element = DocumentHelper.createElement(name);
					element.addAttribute("isGroup","Y");
					List<Element> igItemList = itemEle.elements();//查找itemGroup所有item子节点
					if (igItemList != null && igItemList.size() > 0) {
						String itemName = igItemList.get(0).attributeValue("name");;//查找itemGroup第一个item子节点的name
						String[] itemValues = req.getParameterValues(itemName);//获取保存itemGroup中item的组数
						int	count = itemValues.length;
						for(int i=0;i<count;i++)
						{
							Element dataEle = DocumentHelper.createElement("data");
							for (Element igItem : igItemList) {
								func(igItem,dataEle,oldRootEle,req,i);
							}
							element.add(dataEle);
						}
					}
					rootEle.add(element);
				}else{
					func(itemEle,rootEle,oldRootEle,req);
				}
			}
		}
		return rootEle;
	}

	public void func(Element itemEle,Element rootEle,Element oldRootEle,HttpServletRequest req)
	{

		String multiple = itemEle.attributeValue("multiple");
		//xml中的节点是否是文件
		String isFile = itemEle.attributeValue("isFile");
		boolean isMultipart = JspFormUtil.isMultipart(req);
		if(GlobalConstant.FLAG_Y.equals(isFile)&&isMultipart)
		{
			String name = itemEle.attributeValue("name");
			String isRe = "";

			String[] values = req.getParameterValues(name+"IsRe");
			String[] flowValues = req.getParameterValues(name+"Flow");
			String[] nameValues = req.getParameterValues(name+"Name");

			if (values != null && values.length > 0) {
				isRe = values[0];
			}
			if(GlobalConstant.FLAG_Y.equals(isRe)||StringUtil.isBlank(isRe)) {
				Map<String, String[]> dataMap = getParameterMap(req, rootEle.getName(), name);
				Element element = DocumentHelper.createElement(name);
				Element elementFlow = DocumentHelper.createElement(name + "_Flow");
				Element elementName = DocumentHelper.createElement(name + "_FileName");
				String[] flows = dataMap.get(name);
				String flow = "";
				String flow2 = "";

				if (flowValues != null && flowValues.length > 0) {
					flow = StringUtils.join(flowValues, ",");
				}
				if (flows != null && flows.length > 0) {
					flow2 = StringUtils.join(flows, ",");
				}
				if(StringUtil.isNotBlank(flow) && StringUtil.isNotBlank(flow2)){
					flow+=","+flow2;
				}else if(StringUtil.isNotBlank(flow) && StringUtil.isBlank(flow2)){

				}else{
					flow=flow2;
				}
				elementFlow.setText(flow);
				rootEle.add(elementFlow);

				String[] names = dataMap.get(name + "_FileName");
				String fileName = "";
				String fileName2 = "";
				if (nameValues != null && nameValues.length > 0) {
					fileName = StringUtils.join(nameValues, ",");
				}
				if (names != null && names.length > 0) {
					fileName2 = StringUtils.join(names, ",");
				}
				if(StringUtil.isNotBlank(fileName) && StringUtil.isNotBlank(fileName2)){
					fileName+=","+fileName2;
				}else if(StringUtil.isNotBlank(fileName) && StringUtil.isBlank(fileName2)){

				}else{
					fileName=fileName2;
				}
				element.setText(fileName);
				element.addAttribute("flow", flow);
				rootEle.add(element);
			}else if(oldRootEle!=null){
				Element element = oldRootEle.element(name);
				Element elementFlow = oldRootEle.element(name + "_Flow");
				Element newElement=DocumentHelper.createElement(name);
				Element newElementFlow=DocumentHelper.createElement(name+ "_Flow");
				if(element!=null) {
					if(StringUtil.isNotBlank(element.getTextTrim()))
					{
						newElement.setText(element.getTextTrim());
					}
					if(StringUtil.isNotBlank(element.attributeValue("flow")))
					{
						newElement.addAttribute("flow", element.attributeValue("flow"));
					}
				}
				rootEle.add(newElement);
				if(elementFlow!=null){
					if(StringUtil.isNotBlank(elementFlow.getTextTrim()))
					{
						newElementFlow.setText(elementFlow.getTextTrim());
					}
					if(StringUtil.isNotBlank(elementFlow.attributeValue("flow")))
					{
						newElementFlow.addAttribute("flow", elementFlow.attributeValue("flow"));
					}
				}
				rootEle.add(newElementFlow);
			}
		}else {
			if (!GlobalConstant.FLAG_Y.equals(multiple)) {
				String name = itemEle.attributeValue("name");

				String isSelect = itemEle.attributeValue("select");

				String value = "";

				Element element = DocumentHelper.createElement(name);

				if (GlobalConstant.FLAG_Y.equals(isSelect)) {
					String[] values = req.getParameterValues(name);

					if (values != null && values.length > 0) {
						value = StringUtils.join(values, ",");
					}

					element.addAttribute("id", value);

					String[] valueNames = req.getParameterValues(name + "_name");

					if (valueNames != null && valueNames.length > 0) {
						value = StringUtils.join(valueNames, ",");
					}
				} else {
					value = req.getParameter(name);
				}

				if (StringUtil.isNotBlank(value)) {
					element.setText(value);
				}
				rootEle.add(element);
			} else {
				String[] values = req.getParameterValues(itemEle.attributeValue("name"));
				Element element = DocumentHelper.createElement(itemEle.attributeValue("name"));
				if (values != null && values.length > 0) {
					for (String value : values) {
						Element valueEle = DocumentHelper.createElement("value");
						if (StringUtil.isNotBlank(value)) {
							valueEle.setText(value);
						}
						element.add(valueEle);
					}
				}
				rootEle.add(element);
			}
		}
	}
	public void func(Element itemEle, Element rootEle, Element oldRootEle, HttpServletRequest req,  Integer index)
	{
		if (index!=null) {
			String multiple = itemEle.attributeValue("multiple");
			//xml中的节点是否是文件
			String isFile = itemEle.attributeValue("isFile");
			boolean isMultipart = JspFormUtil.isMultipart(req);
			if (GlobalConstant.FLAG_Y.equals(isFile) && isMultipart) {
				String name = itemEle.attributeValue("name");
				String isRe = "";

				String[] values = req.getParameterValues(name + "IsRe");
				String[] flowValues = req.getParameterValues(name+"Flow");
				String[] nameValues = req.getParameterValues(name+"Name");

				if (values != null && values.length > 0&& values.length > index) {
					isRe = values[index];
				}
				if (GlobalConstant.FLAG_Y.equals(isRe) || StringUtil.isBlank(isRe)) {
					Map<String, String[]> dataMap = getParameterMap(req, rootEle.getName(), name,index);
					Element element = DocumentHelper.createElement(name);
					Element elementFlow = DocumentHelper.createElement(name + "_Flow");
					Element elementName = DocumentHelper.createElement(name + "_FileName");
					String[] flows = dataMap.get(name);
					String flow = "";
					String flow2 = "";

					if (flowValues != null && flowValues.length > 0) {
						if(StringUtil.isNotBlank(flowValues[index])){
							flow = flowValues[index];
						}
					}
					if (flows != null && flows.length > 0) {
						flow2 = StringUtils.join(flows, ",");
					}
					if(StringUtil.isNotBlank(flow) && StringUtil.isBlank(flow2)){

					}else if(StringUtil.isNotBlank(flow) && StringUtil.isNotBlank(flow2)){
						flow+=","+flow2;
					}else{
						flow=flow2;
					}
					elementFlow.setText(flow);
					rootEle.add(elementFlow);

					String[] names = dataMap.get(name + "_FileName");
					String fileName = "";
					String fileName2 = "";
					if (nameValues != null && nameValues.length > 0) {
						if(StringUtil.isNotBlank(nameValues[index])){
							fileName = nameValues[index];
						}
					}
					if (names != null && names.length > 0) {
						fileName2 = StringUtils.join(names, ",");
					}
					if(StringUtil.isNotBlank(fileName) && StringUtil.isBlank(fileName2)){

					}else if(StringUtil.isNotBlank(fileName) && StringUtil.isNotBlank(fileName2)){
						fileName+=","+fileName2;
					}else {
						fileName=fileName2;
					}
					element.setText(fileName);
					element.addAttribute("flow", flow);
					rootEle.add(element);
				} else if (oldRootEle != null) {
					Element element = oldRootEle.element(name);
					Element elementFlow = oldRootEle.element(name + "_Flow");
					Element newElement = DocumentHelper.createElement(name);
					Element newElementFlow = DocumentHelper.createElement(name + "_Flow");
					if (element != null) {
						if (StringUtil.isNotBlank(element.getTextTrim())) {
							newElement.setText(element.getTextTrim());
						}
						if (StringUtil.isNotBlank(element.attributeValue("flow"))) {
							newElement.addAttribute("flow", element.attributeValue("flow"));
						}
					}
					rootEle.add(newElement);
					if (elementFlow != null) {
						if (StringUtil.isNotBlank(elementFlow.getTextTrim())) {
							newElementFlow.setText(elementFlow.getTextTrim());
						}
						if (StringUtil.isNotBlank(elementFlow.attributeValue("flow"))) {
							newElementFlow.addAttribute("flow", elementFlow.attributeValue("flow"));
						}
					}
					rootEle.add(newElementFlow);
				}
			} else {
				if (!GlobalConstant.FLAG_Y.equals(multiple)) {
					String name = itemEle.attributeValue("name");

					String isSelect = itemEle.attributeValue("select");

					String value = "";

					Element element = DocumentHelper.createElement(name);

					if (GlobalConstant.FLAG_Y.equals(isSelect)) {
						String[] values = req.getParameterValues(name);

						if (values != null && values.length > 0&& values.length > index) {
							value = values[index];
						}

						element.addAttribute("id", value);

						String[] valueNames = req.getParameterValues(name + "_name");

						if (valueNames != null && valueNames.length > 0&& valueNames.length > index) {
							value = valueNames[index];
						}
					} else {
						String[] valueNames = req.getParameterValues(name);
						if (valueNames != null && valueNames.length > 0&& valueNames.length > index) {
							value = valueNames[index];
						}else{
							value = req.getParameter(name);
						}
					}

					if (StringUtil.isNotBlank(value)) {
						element.setText(value);
					}
					rootEle.add(element);
				} else {
					String[] values = req.getParameterValues(itemEle.attributeValue("name"));
					Element element = DocumentHelper.createElement(itemEle.attributeValue("name"));
					if (values != null && values.length > 0) {
						for (String value : values) {
							Element valueEle = DocumentHelper.createElement("value");
							if (StringUtil.isNotBlank(value)) {
								valueEle.setText(value);
							}
							element.add(valueEle);
						}
					}
					rootEle.add(element);
				}
			}
		}
	}

	/**
	 * 解析request对象
	 * 如果该对象中有描述文件的就上传 ， 返回的文件流水号重新添加到map中
	 * @param request
	 * @return
	 */
	public static Map<String , String[]> getParameterMap(HttpServletRequest request,String fromName,String paramName){

		String paramFileName=paramName+"_FileName";
		boolean isMultipart = JspFormUtil.isMultipart(request);
		if(isMultipart){
			Map<String , String[]> dataMap = new HashMap<String, String[]>();
			try {
				//转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
				List<MultipartFile> files=multiRequest.getFiles(paramName);
				if(files!=null&&files.size()>0) {
					for(MultipartFile file:files)
					{
						if (!file.isEmpty()) {
							String fileName = file.getOriginalFilename();
							String fileFlow ="";
							if (fileName != null) {
								fileFlow = PkUtil.getUUID();
								PubFile pubFile = new PubFile();
								pubFile.setFileFlow(fileFlow);
								pubFile.setFileName(fileName);
								pubFile.setFileRemark(paramName);
								pubFile.setFileSuffix("");
								pubFile.setFileUpType("1");
								String dateString = DateUtil.getCurrDate2();
								String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+fromName+File.separator+dateString;
								pubFile.setCreateTime(DateUtil.getCurrentTime());
								File fileDir = new File(filePath);
								if(!fileDir.exists()){
									fileDir.mkdirs();
								}
								String saveFileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
								File newFile = new File(fileDir,saveFileName);
								file.transferTo(newFile);
								String uploadFile = File.separator+fromName+File.separator+dateString+File.separator+saveFileName;
								pubFile.setFilePath(uploadFile);
								pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
								GeneralMethod.setRecordInfo(pubFile, false);
								IFileBiz fileBiz = SpringUtil.getBean(IFileBiz.class);
								fileBiz.addFile(pubFile);
							} else {
								fileFlow="";
								fileName="";
							}
							if(StringUtil.isNotBlank(fileFlow)) {
								if (!dataMap.containsKey(paramName)) {
									dataMap.put(paramName, new String[]{fileFlow});
								} else {
									String[] values = dataMap.get(paramName);
									List<String> valueList = new ArrayList<String>();
									for (String temp : values) {
										valueList.add(temp);
									}
									valueList.add(fileFlow);
									String[] newValues = new String[valueList.size()];
									dataMap.put(paramName, valueList.toArray(newValues));
								}
							}
							if(StringUtil.isNotBlank(fileName)) {
								if (!dataMap.containsKey(paramFileName)) {
									dataMap.put(paramFileName, new String[]{fileName});
								} else {
									String[] values = dataMap.get(paramFileName);
									List<String> valueList = new ArrayList<String>();
									for (String temp : values) {
										valueList.add(temp);
									}
									valueList.add(fileName);
									String[] newValues = new String[valueList.size()];
									dataMap.put(paramFileName, valueList.toArray(newValues));
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return dataMap;
		}else{
			Map<String , String[]> dataMap = request.getParameterMap();
			return dataMap;
		}
	}

	public static Map<String , String[]> getParameterMap(HttpServletRequest request,String fromName,String paramName,Integer index){

		String paramFileName=paramName+"_FileName";
		boolean isMultipart = JspFormUtil.isMultipart(request);
		if(isMultipart){
			Map<String , String[]> dataMap = new HashMap<String, String[]>();
			try {
				//转换成多部分request
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
				List<MultipartFile> files=multiRequest.getFiles(paramName);
				if(files!=null&&files.size()>0&&files.size()>index) {
					MultipartFile file=files.get(index);
					if (!file.isEmpty()) {
						String fileName = file.getOriginalFilename();
						String fileFlow ="";
						if (fileName != null) {
							fileFlow = PkUtil.getUUID();
							PubFile pubFile = new PubFile();
							pubFile.setFileFlow(fileFlow);
							pubFile.setFileName(fileName);
							pubFile.setFileRemark(paramName);
							pubFile.setFileSuffix("");
							pubFile.setFileUpType("1");
							String dateString = DateUtil.getCurrDate2();
							String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+fromName+File.separator+dateString;
							pubFile.setCreateTime(DateUtil.getCurrentTime());
							File fileDir = new File(filePath);
							if(!fileDir.exists()){
								fileDir.mkdirs();
							}
							String saveFileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
							File newFile = new File(fileDir,saveFileName);
							file.transferTo(newFile);
							String uploadFile = File.separator+fromName+File.separator+dateString+File.separator+saveFileName;
							pubFile.setFilePath(uploadFile);
							pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
							GeneralMethod.setRecordInfo(pubFile, false);
							IFileBiz fileBiz = SpringUtil.getBean(IFileBiz.class);
							fileBiz.addFile(pubFile);
						} else {
							fileFlow="";
							fileName="";
						}
						if(StringUtil.isNotBlank(fileFlow)) {
							if (!dataMap.containsKey(paramName)) {
								dataMap.put(paramName, new String[]{fileFlow});
							} else {
								String[] values = dataMap.get(paramName);
								List<String> valueList = new ArrayList<String>();
								for (String temp : values) {
									valueList.add(temp);
								}
								valueList.add(fileFlow);
								String[] newValues = new String[valueList.size()];
								dataMap.put(paramName, valueList.toArray(newValues));
							}
						}
						if(StringUtil.isNotBlank(fileName)) {
							if (!dataMap.containsKey(paramFileName)) {
								dataMap.put(paramFileName, new String[]{fileName});
							} else {
								String[] values = dataMap.get(paramFileName);
								List<String> valueList = new ArrayList<String>();
								for (String temp : values) {
									valueList.add(temp);
								}
								valueList.add(fileName);
								String[] newValues = new String[valueList.size()];
								dataMap.put(paramFileName, valueList.toArray(newValues));
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return dataMap;
		}else{
			Map<String , String[]> dataMap = request.getParameterMap();
			return dataMap;
		}
	}
	@Override
	public String getRecContent(String recContent, String formName, List<Element> list, HttpServletRequest req) {
		Element rootEle = DocumentHelper.createElement(formName);
		rootEle = getContentEle(recContent,list,req,rootEle);
		return rootEle.asXML();
	}

	/**
	 * 获取表单内容 [出科考核表]
	 * @param formName 登记类型
	 * @param list 表单内容信息
	 * @param req
	 * @param roleFlag
	 * @param oldContent
	 * @return
	 */
	private String getEvaluationContent(String formName,List<Element> list,HttpServletRequest req,String roleFlag,String oldContent){
		String content = null;
		try {
			Document doc = null;
			Element root = null;
			Element roleNode = null;
			if(StringUtil.isNotBlank(oldContent)){
				doc = DocumentHelper.parseText(oldContent);
				root = doc.getRootElement();
				roleNode = root.element(roleFlag+ResRecTypeEnum.AfterEvaluation.getId());
				if(roleNode != null){
					roleNode.detach();
				}
			}else{
				doc = DocumentHelper.createDocument();
				root = doc.addElement(formName);
			}
			roleNode = DocumentHelper.createElement(roleFlag+ResRecTypeEnum.AfterEvaluation.getId());
			getContent(list,req,roleNode);
			root.add(roleNode);
			content = root.asXML();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 根据Item信息，获取表单内容
	 * @param list
	 * @param req
	 * @param rootEle
	 * @return
	 */
	private String getContent(List<Element> list,HttpServletRequest req,Element rootEle){
		if(list !=null && list.size()>0 && rootEle!=null){
			for(Element itemEle : list){
				String multiple = itemEle.attributeValue("multiple");
				if(!GlobalConstant.FLAG_Y.equals(multiple)){
					String name = itemEle.attributeValue("name");
					String isSelect = itemEle.attributeValue("select");

					String value = req.getParameter(name);

					Element element = DocumentHelper.createElement(itemEle.attributeValue("name"));

					if(GlobalConstant.FLAG_Y.equals(isSelect)){
						String valueName = req.getParameter(name+"_name");
						if(StringUtil.isBlank(value)){
							valueName = (String) req.getAttribute(name+"_name");
						}

						element.addAttribute("id",value);

						value = valueName;
					}

					if (StringUtil.isNotBlank(value)) {
						element.setText(value);
					}
					rootEle.add(element);
				}else {
					String[] values = req.getParameterValues(itemEle.attributeValue("name"));
					Element element = DocumentHelper.createElement(itemEle.attributeValue("name"));
					if(values != null && values.length > 0) {
						for (String value : values) {
							Element valueEle = DocumentHelper.createElement("value");
							if (StringUtil.isNotBlank(value)) {
								valueEle.setText(value);
							}
							element.add(valueEle);
						}
					}
					rootEle.add(element);
				}
			}
		}
		return rootEle.asXML();
	}

	@Override
	public List<Map<String, String>> parseTitle(String recForm, String recType,String recVer) {
		List<Map<String,String>> titleList = null;

		Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(recType);
		if(singleFormMap!=null){
			IrbSingleForm singleForm = singleFormMap.get(recForm+"_"+recVer);
			if(singleForm!=null){
				List<Element> eles = singleForm.getItemList();
				if(eles!=null && !eles.isEmpty()){
					titleList = new ArrayList<Map<String,String>>();

					for(Element e : eles){
						String isView = e.attributeValue("isView");
						if(StringUtil.isNotBlank(isView)){
							String eName = e.attributeValue("name");

							Map<String,String> propInfo = new HashMap<String, String>();
							propInfo.put("isView",isView);

							String isFile = e.attributeValue("isFile");
							propInfo.put("isFile",isFile);

							String title = e.attributeValue("remark");
							propInfo.put("title",title);

							propInfo.put("name", eName);
							titleList.add(propInfo);
						}
					}
					if(titleList!=null && titleList.size()>0){
						Collections.sort(titleList,new Comparator<Map<String,String>>() {
							@Override
							public int compare(Map<String, String> map0,Map<String, String> map1){
								if(map0!=null && map1!=null){
									Integer a = 0;
									Integer b = 0;
									try {
										a = Integer.parseInt(map0.get("isView"));
										b = Integer.parseInt(map1.get("isView"));
									} catch (Exception e) {
										e.printStackTrace();
									}
									return a-b;
								}
								return 0;
							}
						});
					}
				}
			}
		}
		return titleList;
	}

	/**
	 * 解析展示节点
	 */
	@Override
	public List<Map<String,String>> parseViewValue(String recForm,String recType,String recVer,String recContent){
		List<Map<String,String>> viewList = null;

		Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(recType);
		if(singleFormMap!=null){
			IrbSingleForm singleForm = singleFormMap.get(recForm+"_"+recVer);
			if(singleForm!=null){
				List<Element> eles = singleForm.getItemList();
				if(eles!=null && !eles.isEmpty()){
					if(StringUtil.isNotBlank(recContent)){
						try {
							Document document = DocumentHelper.parseText(recContent);
							Element root = document.getRootElement();

							if(root!=null){
								viewList = new ArrayList<Map<String,String>>();

								for(Element e : eles){
									String isView = e.attributeValue("isView");
									if(StringUtil.isNotBlank(isView)){
										String eName = e.attributeValue("name");
										Element currElement = root.element(eName);

										if(currElement!=null){
											Map<String,String> propInfo = new HashMap<String, String>();
											propInfo.put("isView",isView);

											String title = e.attributeValue("remark");
											propInfo.put("title",title);

											String value = currElement.getText();
											propInfo.put("value",value);

											viewList.add(propInfo);
										}
									}
								}
							}
						} catch (DocumentException e) {
							e.printStackTrace();
						}
					}

					if(viewList!=null && viewList.size()>0){
						Collections.sort(viewList,new Comparator<Map<String,String>>() {
							@Override
							public int compare(Map<String, String> map0,Map<String, String> map1){
								if(map0!=null && map1!=null){
									Integer a = 0;
									Integer b = 0;
									try {
										a = Integer.parseInt(map0.get("isView"));
										b = Integer.parseInt(map1.get("isView"));
									} catch (Exception e) {
										e.printStackTrace();
									}
									return a-b;
								}
								return 0;
							}
						});
					}
				}
			}
		}
		return viewList;
	}

	@Override
	public ResRec readResRec(String recFlow) {
		ResRec rec = null;
		if(StringUtil.isNotBlank(recFlow)){
			rec = this.resRecMapper.selectByPrimaryKey(recFlow);
		}
		return rec;
	}

	@Override
	public ResRec readResRecByType(String recFlow, String recTypeId) {
		ResRec rec = new ResRec();
//		if(StringUtil.isNotBlank(recFlow)){
//			if (recTypeId.equals(RegistryTypeEnum.CampaignRegistry.getId())) {
//				ResRecCampaignRegistry campaignRegistry = campaignRegistryMapper.selectByPrimaryKey(recFlow);
//				BeanUtils.copyProperties(campaignRegistry, rec);
//			} else if (recTypeId.equals(RegistryTypeEnum.CaseRegistry.getId())) {
//				ResRecCaseRegistry caseRegistry = caseRegistryMapper.selectByPrimaryKey(recFlow);
//				BeanUtils.copyProperties(caseRegistry, rec);
//			} else if (recTypeId.equals(RegistryTypeEnum.DiseaseRegistry.getId())) {
//				ResRecDiseaseRegistry diseaseRegistry = diseaseRegistryMapper.selectByPrimaryKey(recFlow);
//				BeanUtils.copyProperties(diseaseRegistry, rec);
//			} else if (recTypeId.equals(RegistryTypeEnum.LanguageTeachingResearch.getId())) {
//				ResRecLanguageRegistry languageRegistry = languageRegistryMapper.selectByPrimaryKey(recFlow);
//				BeanUtils.copyProperties(languageRegistry, rec);
//			} else if (recTypeId.equals(RegistryTypeEnum.OperationRegistry.getId())) {
//				ResRecOperationRegistry operationRegistry = operationRegistryMapper.selectByPrimaryKey(recFlow);
//				BeanUtils.copyProperties(operationRegistry, rec);
//			} else if (recTypeId.equals(RegistryTypeEnum.SkillRegistry.getId())) {
//				ResRecSkillRegistry skillRegistry = skillRegistryMapper.selectByPrimaryKey(recFlow);
//				BeanUtils.copyProperties(skillRegistry, rec);
//			}
//		}
		return rec;
	}

	@Override
	public List<ResRec> searchByRecWithBLOBs(String recTypeId,String schDeptFlow,String operUserFlow){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andSchDeptFlowEqualTo(schDeptFlow).andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchRecByProcessWithBLOBs(String processFlow,String recTypeId){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRecTypeIdEqualTo(recTypeId).andProcessFlowEqualTo(processFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchByDeptWithBLOBs(String recTypeId,List<String> schDeptFlows){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andSchDeptFlowIn(schDeptFlows);
		example.setOrderByClause("OPER_USER_FLOW,OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchByRecWithBLOBs(String recTypeId,String operUserFlow){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public List<ResRec> searchByRecWithBLOBsBySchRotationFlows(String recTypeId,String operUserFlow, List<String> schRotationDeptFlows){
		if(schRotationDeptFlows!=null&&schRotationDeptFlows.size()>0) {
			ResRecExample example = new ResRecExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
					.andOperUserFlowEqualTo(operUserFlow).andSchRotationDeptFlowIn(schRotationDeptFlows);
			example.setOrderByClause("OPER_TIME");
			return resRecMapper.selectByExampleWithBLOBs(example);
		}
		return null;
	}

	@Override
	public List<ResRec> searchByRecWithBLOBs(ResRec resRec, String trainYear){
		ResRecExample example = new ResRecExample();
		com.pinde.sci.model.mo.ResRecExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(resRec.getRecTypeId())
				.andOperUserFlowEqualTo(resRec.getOperUserFlow());
		if(StringUtil.isNotBlank(trainYear)){
			criteria.andOperTimeLike(trainYear + "%");
		}
		example.setOrderByClause("NLSSORT(SCH_DEPT_NAME,'NLS_SORT = SCHINESE_PINYIN_M'), OPER_TIME DESC");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

//	@Override
//	public List<ResRec> searchByUserFlowsWithBLOBs(String recTypeId,String schDeptFlow,List<String> operUserFlows){
//		ResRecExample example = new ResRecExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
//		.andSchDeptFlowEqualTo(schDeptFlow).andOperUserFlowIn(operUserFlows);
//		example.setOrderByClause("OPER_TIME");
//		return resRecMapper.selectByExampleWithBLOBs(example);
//	}

	@Override
	public List<ResRec> searchByRecWithBLOBs(String recTypeId,String schDeptFlow,String operUserFlow,String itemId){
		ResRecExample example = new ResRecExample();
		com.pinde.sci.model.mo.ResRecExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andSchDeptFlowEqualTo(schDeptFlow).andOperUserFlowEqualTo(operUserFlow);
		if(StringUtil.isNotBlank(itemId)){
			criteria.andItemIdEqualTo(itemId);
		}
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchFinishRec(List<String> recTypeIds,String operUserFlow){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdIn(recTypeIds)
				.andOperUserFlowEqualTo(operUserFlow);//.andAuditStatusIdEqualTo(RecStatusEnum.TeacherAuditY.getId());
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExample(example);
	}

	@Override
	public List<ResRec> searchFinishRec2(Map<String, List<String>> medicineToDoctorMap, Map<String, List<String>> medicinToRecTypeMap) {
		List<ResRec> res = new ArrayList<>();
		for (Map.Entry<String, List<String>> entry : medicineToDoctorMap.entrySet()) {
			List<String> recTypList = medicinToRecTypeMap.get(entry.getKey());
			List<String> doctorFlowList = entry.getValue();
			res.addAll(resRecMapper.selectByUserAndRecTypeList(doctorFlowList, recTypList));
		}
		Collections.sort(res, Comparator.comparing(ResRec::getOperTime));
		return res;
	}

	@Override
	public List<ResRec> searchByRec(String recTypeId,String schDeptFlow,String operUserFlow){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andSchDeptFlowEqualTo(schDeptFlow).andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExample(example);
	}

	//	@Override
//	public List<ResRec> searchByUserFlows(String recTypeId,String schDeptFlow,List<String> operUserFlows){
//		ResRecExample example = new ResRecExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
//		.andSchDeptFlowEqualTo(schDeptFlow).andOperUserFlowIn(operUserFlows);
//		example.setOrderByClause("OPER_TIME");
//		return resRecMapper.selectByExample(example);
//	}
	public List<ResRec> searchRecInfo(ResRec resRec,List<String> operUserFlows){
		ResRecExample example = new ResRecExample();
		com.pinde.sci.model.mo.ResRecExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(resRec.getRecTypeId())){
			criteria.andRecTypeIdEqualTo(resRec.getRecTypeId());
		}
		if(StringUtil.isNotBlank(resRec.getSchDeptFlow())){
			criteria.andSchDeptFlowEqualTo(resRec.getSchDeptFlow());
		}
		if(operUserFlows!=null&&!operUserFlows.isEmpty()){
			criteria.andOperUserFlowIn(operUserFlows);
		}
		if(StringUtil.isNotBlank(resRec.getOperUserName())){
			criteria.andOperUserNameLike("%"+resRec.getOperUserName()+"%");
		}
		if(StringUtil.isNotBlank(resRec.getOrgFlow())){
			criteria.andOrgFlowEqualTo(resRec.getOrgFlow());
		}
		if(StringUtil.isNotBlank(resRec.getOrgName())){
			criteria.andOrgNameLike("%"+resRec.getOrgName()+"%");
		}
		example.setOrderByClause("create_time desc");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

//	@Override
//	public List<ResRec> searchByRec(List<String> recTypeIds,String schDeptFlow,String operUserFlow){
//		ResRecExample example = new ResRecExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdIn(recTypeIds)
//			.andSchDeptFlowEqualTo(schDeptFlow).andOperUserFlowEqualTo(operUserFlow);
//		example.setOrderByClause("OPER_TIME");
//		return resRecMapper.selectByExample(example);
//	}

	@Override
	public List<ResRec> searchByUserFlows(String recTypeId,List<String> operUserFlows){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andOperUserFlowIn(operUserFlows);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExample(example);
	}

	@Override
	public List<ResRec> searchByUserFlowsWithBLOBs(String recTypeId,List<String> operUserFlows){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andOperUserFlowIn(operUserFlows);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchByRecForAudit(String processFlow, String recTypeId){
		return resRecExtMapper.searchByRecForAudit(processFlow, recTypeId);
	}

//	@Override
//	public List<ResRec> searchByRecWithBLOBs(List<String> recFlows){
//		ResRecExample example = new ResRecExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecFlowIn(recFlows);
//		return resRecMapper.selectByExampleWithBLOBs(example);
//	}

	@Override
	public List<Map<String,Object>> searchDoctorNotAuditCount(String schDeptFlow,String teacherUserFlow,String isAudit,List<String> recTypeIds){
		return resRecExtMapper.searchDoctorNotAuditCount(schDeptFlow,teacherUserFlow,isAudit,recTypeIds);
	}

	@Override
	public int oneKeyAudit(String recTypeId,String processFlow,String operUserFlow){

		ResRecExample example = new ResRecExample();
		ResRec rec = new ResRec();
		rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
		rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
		SysUser user= GlobalContext.getCurrentUser();
		rec.setAuditUserFlow(user.getUserFlow());
		rec.setAuditUserName(user.getUserName());
		rec.setAuditTime(DateUtil.getCurrDateTime());
		GeneralMethod.setRecordInfo(rec,false);
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow).andAuditStatusIdIsNull();
		resRecMapper.updateByExampleSelective(rec,example);

		ResAppealExample appealExample = new ResAppealExample();
		appealExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow).andAuditStatusIdIsNull();
		ResAppeal appeal = new ResAppeal();
		appeal.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
		appeal.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
		appeal.setAuditUserFlow(user.getUserFlow());
		appeal.setAuditUserName(user.getUserName());
		appeal.setAuditTime(DateUtil.getCurrDateTime());
		GeneralMethod.setRecordInfo(appeal,false);
		resAppealMapper.updateByExampleSelective(appeal, appealExample);
		return GlobalConstant.ONE_LINE;
	}
	@Override
	public int oneKeyAuditAll( String type){
		List<String> regTypeIds = new ArrayList<String>();
		if("resRec".equals(type)){
			for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
				if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
						&& !(regType.getId().equals("CampaignNoItemRegistry") && "shrjyy".equals(InitConfig.getSysCfg("res_form_category")))){//培训数据审核，上海瑞金医院“参与活动”无需带教审核
					regTypeIds.add(regType.getId());
				}
			}
		}else if("resAppeal".equals(type)){
			for(TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()){
				if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_"+regType.getId()))){
					regTypeIds.add(regType.getId());
				}
			}
		}else if("practicRegistry".equals(type)){//社会实践
			for(PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()){
				if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_"+regType.getId()))){
					regTypeIds.add(regType.getId());
				}
			}
		}

		ResRec rec = new ResRec();
		rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
		rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
		SysUser user= GlobalContext.getCurrentUser();
		rec.setAuditUserFlow(user.getUserFlow());
		rec.setAuditUserName(user.getUserName());
		rec.setAuditTime(DateUtil.getCurrDateTime());
		GeneralMethod.setRecordInfo(rec,false);
		resRecExtMapper.oneKeyAudit(rec, regTypeIds,user.getUserFlow());

		ResAppeal appeal = new ResAppeal();
		appeal.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
		appeal.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
		appeal.setAuditUserFlow(user.getUserFlow());
		appeal.setAuditUserName(user.getUserName());
		appeal.setAuditTime(DateUtil.getCurrDateTime());
		GeneralMethod.setRecordInfo(appeal,false);
		resAppealExtMapper.oneKeyAudit(appeal, regTypeIds,user.getUserFlow());
		return GlobalConstant.ONE_LINE;
	}

	@Override
	public  int oneKeyAuditByOrg(String orgFlow,String userFlow){
		resRecExtMapper.oneKeyAuditByOrg(orgFlow,userFlow);
		resAppealExtMapper.oneKeyAuditByOrg(orgFlow,userFlow);
		return GlobalConstant.ONE_LINE;
	}
//	@Override
//	public Map<String, BigDecimal> searchAuditCount(String userFlow, String roleFlag) {
//		Map<String, BigDecimal> countMap = null;
//		if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(roleFlag)){
//			countMap = new HashMap<String, BigDecimal>();
//			List<Map<String,Object>> mapList = this.resRecExtMapper.searchAuditCount(userFlow, roleFlag);
//			if(mapList!=null&&!mapList.isEmpty()){
//				for (Map<String, Object> map : mapList) {
//					String key = null;
//					BigDecimal value = null;
//					for(String myKey:map.keySet() ) {
//						if("key".equals(myKey)){
//							key = (String)map.get(myKey);
//						}else if("value".equals(myKey)){
//							value = (BigDecimal)map.get(myKey);
//						}
//					}
//					countMap.put(key, value);
//				}
//			}
//		}
//		return countMap;
//	}

//	@Override
//	public List<ResRecExt> searchAuditList(String userFlow,
//			String roleFlag,
//			String recTypeId,
//			String doctorFlow,
//			String isCurrentFlag) {
//		List<ResRecExt> auditList = null;
//		if(StringUtil.isNotBlank(userFlow)&&StringUtil.isNotBlank(roleFlag)&&StringUtil.isNotBlank(recTypeId)){
//			auditList = this.resRecExtMapper.searchAuditList(userFlow, roleFlag, recTypeId,doctorFlow,isCurrentFlag);
//		}
//		return auditList;
//	}

	@Override
	public List<ResRec> searchRecByProcessWithBLOBs(List<String> recTypeIds,String processFlow,String operUserFlow){
		List<ResRec> resRecList = new ArrayList<>();
//		for (String recTypeId : recTypeIds) {
//			if (recTypeId.equals(RegistryTypeEnum.CampaignRegistry.getId())) {
//				ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//				campaignRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//				campaignRegistryExample.setOrderByClause("OPER_TIME");
//				List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExampleWithBLOBs(campaignRegistryExample);
//				resRecList.addAll(campaignRegistryList.stream().map(e -> {
//					ResRec resRec = new ResRec();
//					BeanUtils.copyProperties(e, resRec);
//					return resRec;
//				}).collect(Collectors.toList()));
//			} else if (recTypeId.equals(RegistryTypeEnum.CaseRegistry.getId())) {
//				ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//				caseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//				caseRegistryExample.setOrderByClause("OPER_TIME");
//				List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExampleWithBLOBs(caseRegistryExample);
//				resRecList.addAll(caseRegistryList.stream().map(e -> {
//					ResRec resRec = new ResRec();
//					BeanUtils.copyProperties(e, resRec);
//					return resRec;
//				}).collect(Collectors.toList()));
//			} else if (recTypeId.equals(RegistryTypeEnum.DiseaseRegistry.getId())) {
//				ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//				diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//				diseaseRegistryExample.setOrderByClause("OPER_TIME");
//				List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExampleWithBLOBs(diseaseRegistryExample);
//				resRecList.addAll(diseaseRegistryList.stream().map(e -> {
//					ResRec resRec = new ResRec();
//					BeanUtils.copyProperties(e, resRec);
//					return resRec;
//				}).collect(Collectors.toList()));
//			} else if (recTypeId.equals(RegistryTypeEnum.LanguageTeachingResearch.getId())) {
//				ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//				languageRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//				languageRegistryExample.setOrderByClause("OPER_TIME");
//				List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExampleWithBLOBs(languageRegistryExample);
//				resRecList.addAll(languageRegistryList.stream().map(e -> {
//					ResRec resRec = new ResRec();
//					BeanUtils.copyProperties(e, resRec);
//					return resRec;
//				}).collect(Collectors.toList()));
//			} else if (recTypeId.equals(RegistryTypeEnum.OperationRegistry.getId())) {
//				ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//				operationRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//				operationRegistryExample.setOrderByClause("OPER_TIME");
//				List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExampleWithBLOBs(operationRegistryExample);
//				resRecList.addAll(operationRegistryList.stream().map(e -> {
//					ResRec resRec = new ResRec();
//					BeanUtils.copyProperties(e, resRec);
//					return resRec;
//				}).collect(Collectors.toList()));
//			} else if (recTypeId.equals(RegistryTypeEnum.SkillRegistry.getId())) {
//				ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//				skillRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//				skillRegistryExample.setOrderByClause("OPER_TIME");
//				List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExampleWithBLOBs(skillRegistryExample);
//				resRecList.addAll(skillRegistryList.stream().map(e -> {
//					ResRec resRec = new ResRec();
//					BeanUtils.copyProperties(e, resRec);
//					return resRec;
//				}).collect(Collectors.toList()));
//			}
//		}
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdIn(recTypeIds)
				.andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
//		return resRecList;
	}

	@Override
	public List<ResRec> searchRecByProcess(String recTypeId,String rotationDeptFlow,String operUserFlow){
//		ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//		campaignRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId).andSchRotationDeptFlowEqualTo(rotationDeptFlow).andOperUserFlowEqualTo(operUserFlow);
//		campaignRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExampleWithBLOBs(campaignRegistryExample);
//		List<ResRec> resRecList = campaignRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList());
//		ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//		caseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId).andSchRotationDeptFlowEqualTo(rotationDeptFlow).andOperUserFlowEqualTo(operUserFlow);
//		caseRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExampleWithBLOBs(caseRegistryExample);
//		resRecList.addAll(caseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//		diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId).andSchRotationDeptFlowEqualTo(rotationDeptFlow).andOperUserFlowEqualTo(operUserFlow);
//		diseaseRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExampleWithBLOBs(diseaseRegistryExample);
//		resRecList.addAll(diseaseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//		languageRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId).andSchRotationDeptFlowEqualTo(rotationDeptFlow).andOperUserFlowEqualTo(operUserFlow);
//		languageRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExampleWithBLOBs(languageRegistryExample);
//		resRecList.addAll(languageRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//		operationRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId).andSchRotationDeptFlowEqualTo(rotationDeptFlow).andOperUserFlowEqualTo(operUserFlow);
//		operationRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExampleWithBLOBs(operationRegistryExample);
//		resRecList.addAll(operationRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//		skillRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId).andSchRotationDeptFlowEqualTo(rotationDeptFlow).andOperUserFlowEqualTo(operUserFlow);
//		skillRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExampleWithBLOBs(skillRegistryExample);
//		resRecList.addAll(skillRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		return resRecList;


		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andSchRotationDeptFlowEqualTo(rotationDeptFlow).andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	public List<ResRec> searchByRecWithBLOBs(List<String> recTypeIds,String schDeptFlow,String operUserFlow){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdIn(recTypeIds)
				.andSchDeptFlowEqualTo(schDeptFlow).andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchByRec(String recTypeId,List<String> schDeptFlows,String operUserFlow){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andSchDeptFlowIn(schDeptFlows).andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Map<String,Object> parseGradeXml(String recContent){
		Map<String,Object> gradeMap = null;
		if(StringUtil.isNotBlank(recContent)){
			try {
				Document doc = DocumentHelper.parseText(recContent);
				Element root = doc.getRootElement();
				if(root!=null){
					List<Element> items = root.elements("grade");
					if(items!=null && items.size()>0){
						gradeMap = new HashMap<String, Object>();
						for(Element e : items){
							String assessId = e.attributeValue("assessId");
							Map<String,String> dataMap = new HashMap<String, String>();
							gradeMap.put(assessId,dataMap);

							Element score = e.element("score");
							if(score!=null){
								String scoreStr = score.getText();
								dataMap.put("score",scoreStr);
							}

						}

						Element totalScore = root.element("totalScore");
						if(totalScore!=null){
							gradeMap.put("totalScore",totalScore.getText());
						}
						Element lostReason = root.element("lostReason");
						if(lostReason!=null){
							gradeMap.put("lostReason",lostReason.getText());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gradeMap;
	}
	@Override
	public Map<String,Object> parseDocotrGradeXml(String recContent){
		Map<String,Object> gradeMap = null;
		if(StringUtil.isNotBlank(recContent)){
			try {
				Document doc = DocumentHelper.parseText(recContent);
				Element root = doc.getRootElement();
				if(root!=null){

					Element gradeinfo = root.element("gradeInfo");
					if(gradeinfo!=null) {
						List<Element> items = gradeinfo.elements("grade");
						if (items != null && items.size() > 0) {
							gradeMap = new HashMap<String, Object>();
							for (Element e : items) {
								String assessId = e.attributeValue("assessId");
								Map<String, String> dataMap = new HashMap<String, String>();
								gradeMap.put(assessId, dataMap);

								Element score = e.element("score");
								if (score != null) {
									String scoreStr = score.getText();
									dataMap.put("score", scoreStr);
								}
								Element lostReason = e.element("lostReason");
								if (lostReason != null) {
									dataMap.put("lostReason", lostReason.getText());
								}
							}
							Element totalScore = gradeinfo.element("totalScore");
							if (totalScore != null) {
								gradeMap.put("totalScore", totalScore.getText());
							}
							Element teach = gradeinfo.element("teach");
							if (teach != null) {
								gradeMap.put("teach", teach.getText());
							}
							Element activty = gradeinfo.element("activty");
							if (activty != null) {
								gradeMap.put("activty", activty.getText());
							}
							Element jianyi = gradeinfo.element("jianyi");
							if (jianyi != null) {
								gradeMap.put("jianyi", jianyi.getText());
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gradeMap;
	}
	@Override
	public Map<String,Object> parseDeptGradeXml(String recContent){
		Map<String,Object> gradeMap = null;
		if(StringUtil.isNotBlank(recContent)){
			try {
				Document doc = DocumentHelper.parseText(recContent);
				Element root = doc.getRootElement();
				if(root!=null){

					Element gradeinfo = root.element(GlobalConstant.RES_ROLE_SCOPE_MANAGER+"GradeInfo");
					if(gradeinfo==null){
						gradeinfo = root.element(GlobalConstant.RES_ROLE_SCOPE_HEAD+"GradeInfo");
					}
					if(gradeinfo==null){
						gradeinfo = root.element(GlobalConstant.RES_ROLE_SCOPE_TEACHER+"GradeInfo");
					}
					if(gradeinfo!=null) {
						List<Element> items = gradeinfo.elements("grade");
						if (items != null && items.size() > 0) {
							gradeMap = new HashMap<String, Object>();
							for (Element e : items) {
								String assessId = e.attributeValue("assessId");
								Map<String, String> dataMap = new HashMap<String, String>();
								gradeMap.put(assessId, dataMap);

								Element score = e.element("score");
								if (score != null) {
									String scoreStr = score.getText();
									dataMap.put("score", scoreStr);
								}
								Element lostReason = e.element("lostReason");
								if (lostReason != null) {
									dataMap.put("lostReason", lostReason.getText());
								}
							}

							Element totalScore = gradeinfo.element("totalScore");
							if (totalScore != null) {
								gradeMap.put("totalScore", totalScore.getText());
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gradeMap;
	}

	@Override
	public ExcelUtile importJsresData(MultipartFile file, String recordFlow, String processFlow, String doctorFlow, String recTypeId) {
		if(StringUtil.isBlank(recordFlow))
		{
			ExcelUtile eu=	 new ExcelUtile();
			eu.put("count", 0);
			eu.put("code", "1");
			eu.put("msg", "请选择标准科室");
			return eu;
		}
		if(StringUtil.isBlank(processFlow))
		{
			ExcelUtile eu=	 new ExcelUtile();
			eu.put("count", 0);
			eu.put("code", "1");
			eu.put("msg", "请选择轮转科室");
			return eu;
		}
		if(StringUtil.isBlank(recTypeId))
		{
			ExcelUtile eu=	 new ExcelUtile();
			eu.put("count", 0);
			eu.put("code", "1");
			eu.put("msg", "请选择需要导入的数据类型");
			return eu;
		}
		List<SchRotationDeptReq> reqs=schRotationDeptBiz.searchRotationDeptReq(recordFlow,recTypeId);
		if(reqs==null||reqs.size()==0)
		{
			ExcelUtile eu=	 new ExcelUtile();
			eu.put("count", 0);
			eu.put("code", "1");
			eu.put("msg", "请联系系统管理员维护【操作名称】！！");
			return eu;
		}
		if(ResRecTypeEnum.SkillRegistry.getId().equals(recTypeId))
		{
			return importJsresSkillData(file, recordFlow,  processFlow,  doctorFlow,  recTypeId,reqs);
		}
		if(ResRecTypeEnum.DiseaseRegistry.getId().equals(recTypeId))
		{
			return importJsresDiseaseData(file, recordFlow,  processFlow,  doctorFlow,  recTypeId,reqs);
		}
		return null;
	}




	private ExcelUtile importJsresDiseaseData(MultipartFile file, String recordFlow, String processFlow, String doctorFlow, String recTypeId, List<SchRotationDeptReq> reqs) {

		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			//map中的keys  个数与execl中表头字段数量一致
			final String[] keys = {
					"病种名称:itemName",
					"名称:name",
					"日期:disease_pDate",
					"病人姓名:disease_pName",
					"病历号/病理号:disease_mrNo",
					"诊断类型:disease_diagType",
					"是否主管:disease_isCharge",
					"是否抢救:disease_isRescue",
					"转归情况:disease_treatStep"
			};
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);

			Map<String,String> itemMap=new HashMap<>();
			for(SchRotationDeptReq req:reqs)
			{
				itemMap.put(req.getItemName(),req.getItemId());
			}
			ResDoctor docote = resDoctorBiz.readDoctor(doctorFlow);
			SysUser user=userBiz.readSysUser(doctorFlow);
			ResDoctorSchProcess process = processBiz.read(processFlow);
			List<String> list=new ArrayList<>();
			return ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<Map<String,Object>> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					for(int i=0;i<datas.size();i++)
					{
						ResRec rec= (ResRec) datas.get(i).get("rec");
						if(rec!=null)
						{
							count+=resRecMapper.insertSelective(rec);
						}
					}
					eu.put("code",code);
					eu.put("count",count);
				}


				@Override
				public String checkExcelData(HashMap data,ExcelUtile eu) {
					String sheetName=(String)eu.get("SheetName");
					if(sheetName==null||!recTypeId.equals(sheetName))
					{
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "请使用系统提供的导入模板！！");
						return ExcelUtile.RETURN;
					}
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					for (Object key1 : data.keySet()) {
						String key=(String) key1;
						Map<String,Object> map=new HashMap<String, Object>();
						String value=(String) data.get(key);
						if("itemName".equals(key))
						{
							if(StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行病种名称为空，请确认后提交！！");
							}
							String itemId=itemMap.get(value);
							if(StringUtil.isBlank(itemId)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行病种名称填写错误，请确认后提交！！");
							}
							if("其他".equals(value))
							{
								String otherName=(String) data.get("name");
								if(StringUtil.isBlank(otherName)) {
									return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行名称为空，请确认后提交！！");
								}
							}
						}
						if("disease_pDate".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行日期为空，请确认后提交！！");
							}
							Date date = null;
							try {
								date = format.parse(value);
							} catch (ParseException e) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行日期格式不正确，请确认后提交！！");
							}
						}
						if("disease_pName".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行病人姓名为空，请确认后提交！！");
							}
						}
						if("disease_mrNo".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行病历号/病理号为空，请确认后提交！！");
							}
						}
						if("disease_diagType".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行诊断类型为空，请确认后提交！！");
							}
							if(!"主要诊断".equals(value)&&!"次要诊断".equals(value)&&!"并行诊断".equals(value))
							{
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行诊断类型格式错误只能为【主要诊断】或【次要诊断】或【并行诊断】，请确认后提交！！");
							}
						}
						if("disease_isCharge".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行是否主管为空，请确认后提交！！");
							}
							if(!"是".equals(value)&&!"否".equals(value))
							{
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行是否主管格式错误只能为【是】或【否】，请确认后提交！！");
							}
						}
						if("disease_isRescue".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行是否抢救为空，请确认后提交！！");
							}
							if(!"是".equals(value)&&!"否".equals(value))
							{
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行是否抢救格式错误只能为【是】或【否】，请确认后提交！！");
							}
						}
						if("disease_treatStep".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行转归情况为空，请确认后提交！！");
							}
						}

					}
					data.put("recTypeId",recTypeId);
					data.put("processFlow",processFlow);
					data.put("doctorFlow",doctorFlow);
					data.put("deptFlow",recordFlow);
					data.put("itemId",itemMap.get(data.get("itemName")));
					ResRec record = new ResRec();
					record.setRecFlow(PkUtil.getUUID());
					record.setOrgFlow(docote.getOrgFlow());
					record.setOrgName(docote.getOrgName());
					record.setOperTime(DateUtil.getCurrDateTime());
					record.setOperUserFlow(doctorFlow);
					record.setOperUserName(docote.getDoctorName());
					record.setRecTypeId(recTypeId);
					record.setRecTypeName(RegistryTypeEnum.getNameById(recTypeId));
					record.setItemId((String) data.get("itemId"));
					record.setItemName((String) data.get("itemName"));
					record.setStatusId("importData");
					//标准科室主键
					record.setSchRotationDeptFlow(recordFlow);

					String medicineTypeId="";
					if(user!=null)
						medicineTypeId=user.getMedicineTypeId();
					record.setMedicineType(medicineTypeId);

					if(process!=null){
						record.setDeptFlow(process.getDeptFlow());
						record.setDeptName(process.getDeptName());
						record.setSchDeptFlow(process.getSchDeptFlow());
						record.setSchDeptName(process.getSchDeptName());
						record.setProcessFlow(process.getProcessFlow());
						data.put("resultFlow",process.getSchResultFlow());
					}
					Element rootEle = createElement(recTypeId,data);
					record.setRecContent(rootEle.asXML());
					if(list.contains(record.getRecContent()))
					{
						return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "数据存在重复，请确认后提交！！");
					}
					list.add(record.getRecContent());
					record.setRecForm("jsst");
					record.setRecVersion("2.0");
					record.setCreateTime(DateUtil.getCurrDateTime());
					record.setCreateUserFlow(doctorFlow);
					record.setModifyTime(DateUtil.getCurrDateTime());
					record.setModifyUserFlow(doctorFlow);
					record.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					data.put("rec",record);
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
		return  null;
	}

	private ExcelUtile importJsresSkillData(MultipartFile file, String recordFlow, String processFlow, String doctorFlow, String recTypeId, List<SchRotationDeptReq> reqs) {

		InputStream is = null;
		try {
			is = file.getInputStream();
			byte[] fileData = new byte[(int) file.getSize()];
			is.read(fileData);
			Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
			//map中的keys  个数与execl中表头字段数量一致
			final String[] keys = {
					"操作名称:itemName",
					"名称:name",
					"操作日期:skill_operDate",
					"病人姓名:skill_pName",
					"病历号/病理号/检查号:skill_mrNo",
					"成功:skill_result",
					"失败原因:fail_reason"
			};
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			format.setLenient(false);

			Map<String,String> itemMap=new HashMap<>();
			for(SchRotationDeptReq req:reqs)
			{
				itemMap.put(req.getItemName(),req.getItemId());
			}
			ResDoctor docote = resDoctorBiz.readDoctor(doctorFlow);
			SysUser user=userBiz.readSysUser(doctorFlow);
			ResDoctorSchProcess process = processBiz.read(processFlow);
			List<String> list=new ArrayList<>();
			return ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
				@Override
				public void operExcelData(ExcelUtile eu) {
					List<Map<String,Object>> datas=eu.getExcelDatas();
					int count = 0;
					String code="0";
					for(int i=0;i<datas.size();i++)
					{
						ResRec rec= (ResRec) datas.get(i).get("rec");
						if(rec!=null)
						{
							count+=resRecMapper.insertSelective(rec);
						}
					}
					eu.put("code",code);
					eu.put("count",count);
				}


				@Override
				public String checkExcelData(HashMap data,ExcelUtile eu) {
					String sheetName=(String)eu.get("SheetName");
					if(sheetName==null||!recTypeId.equals(sheetName))
					{
						eu.put("count", 0);
						eu.put("code", "1");
						eu.put("msg", "请使用系统提供的导入模板！！");
						return ExcelUtile.RETURN;
					}
					int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
					for (Object key1 : data.keySet()) {
						String key=(String) key1;
						Map<String,Object> map=new HashMap<String, Object>();
						String value=(String) data.get(key);
						if("itemName".equals(key))
						{
							if(StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行操作名称为空，请确认后提交！！");
							}
							String itemId=itemMap.get(value);
							if(StringUtil.isBlank(itemId)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行操作名称填写错误，请确认后提交！！");
							}
							if("其他".equals(value))
							{
								String otherName=(String) data.get("name");
								if(StringUtil.isBlank(otherName)) {
									return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行名称为空，请确认后提交！！");
								}
							}
						}
						if("skill_operDate".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行操作日期为空，请确认后提交！！");
							}
							Date date = null;
							try {
								date = format.parse(value);
							} catch (ParseException e) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行操作日期格式不正确，请确认后提交！！");
							}
						}
						if("skill_pName".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行病人姓名为空，请确认后提交！！");
							}
						}
						if("skill_mrNo".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行病历号/病理号/检查号为空，请确认后提交！！");
							}
						}
						if("skill_result".equals(key)) {
							if (StringUtil.isBlank(value)) {
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行成功为空，请确认后提交！！");
							}
							if(!"是".equals(value)&&!"否".equals(value))
							{
								return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "行成功格式错误只能为【是】或【否】，请确认后提交！！");
							}
						}

					}
					data.put("recTypeId",recTypeId);
					data.put("processFlow",processFlow);
					data.put("doctorFlow",doctorFlow);
					data.put("deptFlow",recordFlow);
					data.put("itemId",itemMap.get(data.get("itemName")));
					ResRec record = new ResRec();
					record.setRecFlow(PkUtil.getUUID());
					record.setOrgFlow(docote.getOrgFlow());
					record.setOrgName(docote.getOrgName());
					record.setOperTime(DateUtil.getCurrDateTime());
					record.setOperUserFlow(doctorFlow);
					record.setOperUserName(docote.getDoctorName());
					record.setRecTypeId(recTypeId);
					record.setRecTypeName(RegistryTypeEnum.getNameById(recTypeId));
					record.setItemId((String) data.get("itemId"));
					record.setItemName((String) data.get("itemName"));
					record.setStatusId("importData");
					//标准科室主键
					record.setSchRotationDeptFlow(recordFlow);

					String medicineTypeId="";
					if(user!=null)
						medicineTypeId=user.getMedicineTypeId();
					record.setMedicineType(medicineTypeId);

					if(process!=null){
						record.setDeptFlow(process.getDeptFlow());
						record.setDeptName(process.getDeptName());
						record.setSchDeptFlow(process.getSchDeptFlow());
						record.setSchDeptName(process.getSchDeptName());
						record.setProcessFlow(process.getProcessFlow());
						data.put("resultFlow",process.getSchResultFlow());
					}
					Element rootEle = createElement(recTypeId,data);
					record.setRecContent(rootEle.asXML());
					if(list.contains(record.getRecContent()))
					{
						return ExcelUtile.errorMsg(eu, "导入文件第" + (rowNum + 1) + "数据存在重复，请确认后提交！！");
					}
					list.add(record.getRecContent());
					record.setRecForm("jsst");
					record.setRecVersion("2.0");
					record.setCreateTime(DateUtil.getCurrDateTime());
					record.setCreateUserFlow(doctorFlow);
					record.setModifyTime(DateUtil.getCurrDateTime());
					record.setModifyUserFlow(doctorFlow);
					record.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
					data.put("rec",record);
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

	private Element createElement(String recTypeId, HashMap<String, Object> data) {

		Element rootEle = DocumentHelper.createElement(recTypeId);
		for(Map.Entry<String, Object> entry: data.entrySet()){
			String key = entry.getKey();
			String value =  String.valueOf(entry.getValue());
			Element element = DocumentHelper.createElement(key);
			if (ResRecTypeEnum.SkillRegistry.getId().equals(recTypeId)) {
				if ("itemName".equals(entry.getKey())) {
					Element element1 = DocumentHelper.createElement("skill_operName");
					if ("其他".equals(value)) {
						element.setText(value);
						element1.setText((String) data.get("name"));
					} else {
						element.addAttribute("id", (String) data.get("itemId"));
						element.setText(value);
						element1.addAttribute("id", (String) data.get("itemId"));
						element1.setText(value);
					}
					rootEle.add(element1);
				} else if ("skill_result".equals(entry.getKey())) {
					element.setText(value);
					if ("是".equals(value)) {
						element.addAttribute("id", "1");
					} else if ("否".equals(value)) {
						element.addAttribute("id", "0");
					}
				} else {
					element.setText(value);
				}
			} else if (ResRecTypeEnum.DiseaseRegistry.getId().equals(recTypeId)) {
				if ("itemName".equals(entry.getKey())) {
					Element element1 = DocumentHelper.createElement("disease_diagName");
					if ("其他".equals(value)) {
						element.setText(value);
						element1.setText((String) data.get("name"));
					} else {
						element.addAttribute("id", (String) data.get("itemId"));
						element.setText(value);
						element1.addAttribute("id", (String) data.get("itemId"));
						element1.setText(value);
					}
					rootEle.add(element1);
				} else if ("disease_diagType".equals(entry.getKey())) {
					element.setText(value);
					if ("主要诊断".equals(value)) {
						element.addAttribute("id", "1");
					} else if ("次要诊断".equals(value)) {
						element.addAttribute("id", "2");
					} else if ("并行诊断".equals(value)) {
						element.addAttribute("id", "3");
					}
				} else if ("disease_isCharge".equals(entry.getKey())) {
					element.setText(value);
					if ("是".equals(value)) {
						element.addAttribute("id", "1");
					} else if ("否".equals(value)) {
						element.addAttribute("id", "0");
					}
				} else if ("disease_isRescue".equals(entry.getKey())) {
					element.setText(value);
					if ("是".equals(value)) {
						element.addAttribute("id", "1");
					} else if ("否".equals(value)) {
						element.addAttribute("id", "0");
					}
				} else {
					element.setText(value);
				}
			} else {
				element.setText(value);
			}
			rootEle.add(element);
		}
		return rootEle;
	}

	@Override
	public List<ResRecExt> searchRegistryList(String userFlow, String roleFlag,ResRec rec,ResDoctorSchProcess process) {
		return this.resRecExtMapper.searchRegistryList(userFlow, roleFlag,rec,process);
	}

	@Override
	public List<ResRecExt> searchTeacherAudit(String schDeptFlow, String isCurrentFlag,String userFlow) {
		return resRecExtMapper.searchTeacherAudit(schDeptFlow, isCurrentFlag,userFlow);
	}

	@Override
	public List<ResRecExt> searchScoreList(String userFlow,
										   String roleFlag,
										   String sessionNumber,
										   String recTypeId,
										   String isCurrentFlag) {
		return this.resRecExtMapper.searchScoreList(userFlow, roleFlag, sessionNumber, recTypeId, isCurrentFlag);
	}

//	@Override
//	public List<ResRecExt> searchRecExtByRecExt(ResRecExt recExt){
//		return resRecExtMapper.searchRecExtByRecExt(recExt);
//	}

	@Override
	public Map<String,Object> parseRecContent(String content) {
		logger.info("parseRecContent content = " + content);
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				Element afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_MANAGER+ResRecTypeEnum.AfterEvaluation.getId());
				if(afterEvaluation==null){
					afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE+ResRecTypeEnum.AfterEvaluation.getId());
				}
				if(afterEvaluation==null){
					afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_HEAD+ResRecTypeEnum.AfterEvaluation.getId());
				}
				if(afterEvaluation==null){
					afterEvaluation = rootElement.element(GlobalConstant.RES_ROLE_SCOPE_TEACHER+ResRecTypeEnum.AfterEvaluation.getId());
				}
				List<Element> elements = null;
				if(afterEvaluation!=null){
					elements = afterEvaluation.elements();
				}else{
					elements = rootElement.elements();
				}
				for(Element element : elements){

					if("imageList".equals(element.getName()))
					{
						List<Element> images = element.elements();
						if(images!=null){
							List<Map<String,String>> dataList = new ArrayList<Map<String,String>>();
							for(Element ele : images){
								Map<String,String> map = new HashMap<String, String>();
								map.put("imageFlow", ele.attributeValue("imageFlow"));
								map.put("imageUrl", ele.elementText("imageUrl"));
								map.put("thumbUrl", ele.elementText("thumbUrl"));
								dataList.add(map);
							}
							formDataMap.put(element.getName(),dataList);
						}
					}else {
						String isGroup = element.attributeValue("isGroup");
						if (GlobalConstant.FLAG_Y.equals(isGroup)) {
							List<Map<String, Object>> items = new ArrayList<>();
							List<Element> subList = element.elements();
							if (subList != null && subList.size() > 0) {
								for (Element sub : subList) {
									Map<String, Object> item = new HashMap<>();
									List<Element> itemList = sub.elements();
									for (Element it : itemList) {
										parseRecItem(item, it);
									}
									items.add(item);
								}
							}

							formDataMap.put(element.getName(), items);
						} else {
							parseRecItem(formDataMap, element);
						}
					}
				}
			} catch (DocumentException e) {
				logger.error("parseRecContent error,content = " + content,e);
			}
		}
		return formDataMap;
	}

	private void parseRecItem(Map<String, Object> formDataMap, Element element) {

		List<Node> valueNodes = element.selectNodes("value");
		if(valueNodes != null && !valueNodes.isEmpty()){
			String value = "";
			for(Node node : valueNodes){
				if(StringUtil.isNotBlank(value)){
					value+=",";
				}
				value += node.getText();
			}
			formDataMap.put(element.getName(), value);
		}else {
			String isSelect = element.attributeValue("id");
			if(StringUtil.isNotBlank(isSelect)){
				formDataMap.put(element.getName()+"_id",isSelect);
				formDataMap.put(element.getName(),element.getText());
			}else{
				formDataMap.put(element.getName(), element.getText());
			}
		}
	}

	@Override
	public Map<String,List<Map<String,String>>> parseRecContentAppraise(String content){
		Map<String,List<Map<String,String>>> appraiseMap = null;
		if(StringUtil.isNotBlank(content)){
			appraiseMap = new HashMap<String, List<Map<String,String>>>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements("deptAppraise");
				if(elements!=null && elements.size()>0){
					List<Map<String,String>> deptAppraiseList = new ArrayList<Map<String,String>>();
					for(Element e : elements){
						Map<String,String> appraise = new HashMap<String, String>();
						appraise.put("operTime",e.attributeValue("operTime"));
						appraise.put("operUserName",e.attributeValue("operUserName"));
						appraise.put("auditStatusName",e.attributeValue("auditStatusName"));
						appraise.put("appraise",e.getText());
						deptAppraiseList.add(appraise);
					}
					appraiseMap.put("deptAppraise",deptAppraiseList);
				}

				elements = rootElement.elements("deptHeadAutograth");
				if(elements!=null && elements.size()>0){
					List<Map<String,String>> deptAppraiseList = new ArrayList<Map<String,String>>();
					for(Element e : elements){
						Map<String,String> appraise = new HashMap<String, String>();
						appraise.put("operTime",e.attributeValue("operTime"));
						appraise.put("appraise",e.getText());
						deptAppraiseList.add(appraise);
					}
					appraiseMap.put("deptHeadAutograth",deptAppraiseList);
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return appraiseMap;
	}

	@Override
	public List<ResRec> searchRecByUserFlows(List<String> userFlows,String recTypeId){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andOperUserFlowIn(userFlows);
		return resRecMapper.selectByExample(example);
	}

	@Override
	public List<ResDoctorSchProcessExt> searchProcessExt(ResDoctorSchProcessExt processExt){
		return resRecExtMapper.searchProcessExt(processExt);
	}

	@Override
	public List<ResRec> searchRecByUserAndSchdept(List<String> userFlows,List<String> processFlows,String recTypeId,String itemName){
		return resRecExtMapper.searchRecByUserAndSchdept(userFlows, processFlows, recTypeId, itemName);
	}

	@Override
	public List<Map<String,Object>> countProcessByUser(List<String> userFlows){
		return processExtBiz.countProcessByUser(userFlows);
	}

	@Override
	public List<Map<String,Object>> searchTeacherAuditCount(String headUserFlow,String isAudit){
		return resRecExtMapper.searchTeacherAuditCount(headUserFlow,isAudit);
	}

	/******************************************************获取百分比和数量**************************************************************/
	@Override
	public Map<String,String> getStandardDeptFinishPer(List<SchRotationDept> depts){
		return getStandardDeptFinishPer(depts,GlobalContext.getCurrentUser().getUserFlow());
	}
	@Override
	public Map<String,String> getStandardDeptFinishPer(List<SchRotationDept> depts,String doctorFlow) {

		Map<String, String> finishPerMap = new HashMap<String, String>();

		String medicineTypeId="";
		SysUser user=userBiz.readSysUser(doctorFlow);
		if(user!=null)
			medicineTypeId=user.getMedicineTypeId();
		List<String> recTypeIds = new ArrayList<String>();
		for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
			if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())) {
				recTypeIds.add(regType.getId());
			}
		}
		for(PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_"+regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
				recTypeIds.add(regType.getId());
			}
		}
		for(TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_"+regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
				recTypeIds.add(regType.getId());
			}
		}
		if (recTypeIds == null || recTypeIds.size() <= 0) {
			return finishPerMap;
		}
		if (depts != null && depts.size() > 0) {

			ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
			//判断学员是否需要减免
			String trainingType = doctor.getTrainingTypeId();
			String sessionNumber = doctor.getSessionNumber();
			String trainingYears = doctor.getTrainingYears();
			boolean isReduction = JszyTrainCategoryEnum.ChineseMedicine.getId().equals(trainingType) || JszyTrainCategoryEnum.TCMGeneral.getId().equals(trainingType);
			isReduction = isReduction && "2015".compareTo(sessionNumber) <= 0;
			isReduction = isReduction && (JszyResTrainYearEnum.OneYear.getId().equals(trainingYears) || JszyResTrainYearEnum.TwoYear.getId().equals(trainingYears));
			Map<String, SchDoctorDept> doctorDeptMap = new HashMap<>();
			if (isReduction) {
				//获取所有缩减的调整科室
				List<SchDoctorDept> doctorDeptList = searchReductionDept(doctorFlow, doctor.getRotationFlow(), doctor.getSecondRotationFlow());
				if (doctorDeptList != null && doctorDeptList.size() > 0) {
					//获取标准科室列表以绑定要求数据
					doctorDeptMap = transListObjToMapsSoM(false, doctorDeptList, "groupFlow", "standardDeptId");
				}
			}

			Map<String, String> processMap = new HashMap<String, String>();
			Map<String, SchArrangeResult> proResultMap = new HashMap<String, SchArrangeResult>();

			Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
			List<SchArrangeResult> arrResultList = resultBiz.searchResultByRotationDepts(depts,doctorFlow);
			if (arrResultList != null && arrResultList.size() > 0) {
				for (SchArrangeResult result : arrResultList) {
					String resultFlow = result.getResultFlow();

					String key = result.getStandardGroupFlow()+result.getStandardDeptId();
					List<SchArrangeResult> resultList= resultMap.get(key);
					if(resultList==null){
						resultList = new ArrayList<SchArrangeResult>();
						resultMap.put(key, resultList);
					}
					resultList.add(result);
					if (StringUtil.isNotBlank(resultFlow)) {
						ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
						if (process != null) {
							String processFlow = process.getProcessFlow();
							processMap.put(resultFlow, process.getProcessFlow());
							processMap.put(processFlow, resultFlow);
							proResultMap.put(processFlow, result);
						}
					}
				}
			}
			List<ResRec> recList = searchFinishRec(recTypeIds, doctorFlow);
			Map<String, Integer> recFinishMap = new HashMap<String, Integer>();
			if (recList != null && recList.size() > 0) {
				for (ResRec rec : recList) {
					String processFlow = rec.getProcessFlow();
					String schDeptFlow = rec.getSchDeptFlow();
					String recTypeId = rec.getRecTypeId();
					String itemId = rec.getItemId();

					if (recFinishMap.get(schDeptFlow) == null) {
						recFinishMap.put(schDeptFlow, 1);
					} else {
						recFinishMap.put(schDeptFlow, recFinishMap.get(schDeptFlow) + 1);
					}
					if (recFinishMap.get(schDeptFlow + recTypeId) == null) {
						recFinishMap.put(schDeptFlow + recTypeId, 1);
					} else {
						recFinishMap.put(schDeptFlow + recTypeId, recFinishMap.get(schDeptFlow + recTypeId) + 1);
					}
					if (StringUtil.isNotBlank(itemId)) {
						if (recFinishMap.get(schDeptFlow + recTypeId + itemId) == null) {
							recFinishMap.put(schDeptFlow + recTypeId + itemId, 1);
						} else {
							recFinishMap.put(schDeptFlow + recTypeId + itemId, recFinishMap.get(schDeptFlow + recTypeId + itemId) + 1);
						}
					}
					if (recFinishMap.get(processFlow) == null) {
						recFinishMap.put(processFlow, 1);
					} else {
						recFinishMap.put(processFlow, recFinishMap.get(processFlow) + 1);
					}
					if (recFinishMap.get(processFlow + recTypeId) == null) {
						recFinishMap.put(processFlow + recTypeId, 1);
					} else {
						recFinishMap.put(processFlow + recTypeId, recFinishMap.get(processFlow + recTypeId) + 1);
					}
					if (StringUtil.isNotBlank(itemId)) {
						if (recFinishMap.get(processFlow + recTypeId + itemId) == null) {
							recFinishMap.put(processFlow + recTypeId + itemId, 1);
						} else {
							recFinishMap.put(processFlow + recTypeId + itemId, recFinishMap.get(processFlow + recTypeId + itemId) + 1);
						}
					}

					SchArrangeResult result = proResultMap.get(processFlow);
					if (result != null) {
						String standardKey = result.getStandardGroupFlow() + result.getStandardDeptId();
						if (recFinishMap.get(standardKey) == null) {
							recFinishMap.put(standardKey, 1);
						} else {
							recFinishMap.put(standardKey, recFinishMap.get(standardKey) + 1);
						}
						if (recFinishMap.get(standardKey + recTypeId) == null) {
							recFinishMap.put(standardKey + recTypeId, 1);
						} else {
							recFinishMap.put(standardKey + recTypeId, recFinishMap.get(standardKey + recTypeId) + 1);
						}
						if (StringUtil.isNotBlank(itemId)) {
							if (recFinishMap.get(standardKey + recTypeId + itemId) == null) {
								recFinishMap.put(standardKey + recTypeId + itemId, 1);
							} else {
								recFinishMap.put(standardKey + recTypeId + itemId, recFinishMap.get(standardKey + recTypeId + itemId) + 1);
							}
						}
					}
				}
			}

			for (SchRotationDept dept : depts) {

				String standardKey = dept.getGroupFlow() + dept.getStandardDeptId();

				SchRotation rotation = rotationBiz.readSchRotation(dept.getRotationFlow());
				double per = 1;
				String baseSchMonth = dept.getSchMonth();
				if (isReduction) {
					SchDoctorDept sdd = doctorDeptMap.get(standardKey);
					if (sdd != null) {
						if (GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus())) {
							per = Double.valueOf(sdd.getSchMonth()) / Double.valueOf(dept.getSchMonth());
							baseSchMonth = sdd.getSchMonth();
						}
					}
				}
				//是否翻倍
				if (rotation != null && GlobalConstant.FLAG_Y.equals(rotation.getIsDouble()) && StringUtil.isNotBlank(baseSchMonth)) {
					List<SchArrangeResult> results = resultMap.get(standardKey);
					if (results != null && results.size() > 0) {
						double allMonth = 0;
						for (SchArrangeResult result : results) {
							if (StringUtil.isNotBlank(result.getSchMonth())) {
								allMonth += Double.valueOf(result.getSchMonth());
							}
						}
						if (allMonth > 0) {
							per = allMonth / Double.valueOf(baseSchMonth) * per;
						}
					}
				}
				//要求数
				List<SchRotationDeptReq> deptReqList = rotationDeptBiz.searchDeptReqByRel(dept.getRecordFlow());
				Map<String, Integer> reqNumMap = new HashMap<String, Integer>();
				Map<String, List<String>> itemMap = new HashMap<String, List<String>>();
				reqNumMap.put("reqSum", 0);
				if (deptReqList != null && deptReqList.size() > 0) {
					for (SchRotationDeptReq deptReq : deptReqList) {
						if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + deptReq.getRecTypeId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {

							int num = new BigDecimal(Math.ceil(deptReq.getReqNum().intValue() * per)).intValue();
							reqNumMap.put("reqSum", reqNumMap.get("reqSum") + (num));
							if (reqNumMap.get(deptReq.getRecTypeId()) == null) {
								reqNumMap.put(deptReq.getRecTypeId(), (num));
							} else {
								reqNumMap.put(deptReq.getRecTypeId(), reqNumMap.get(deptReq.getRecTypeId()) + (num));
							}
							if (reqNumMap.get(deptReq.getRecTypeId() + "itemCount") == null) {
								reqNumMap.put(deptReq.getRecTypeId() + "itemCount", 1);
							} else {
								reqNumMap.put(deptReq.getRecTypeId() + "itemCount", reqNumMap.get(deptReq.getRecTypeId() + "itemCount") + 1);
							}
							if (reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) == null) {
								reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), num);
							} else {
								reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) + (num));
							}
							RegistryTypeEnum rectype = RegistryTypeEnum.valueOf(deptReq.getRecTypeId());
							if (rectype != null && rectype.getHaveItem().equals(GlobalConstant.FLAG_Y)) {
								if (StringUtil.isNotBlank(deptReq.getItemId())) {
									List<String> itemIds = itemMap.get(deptReq.getRecTypeId());
									if (itemIds == null) {
										itemIds = new ArrayList<String>();
										itemMap.put(deptReq.getRecTypeId(), itemIds);
									}
									itemIds.add(deptReq.getItemId());
								}
							}
						}
						if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + deptReq.getRecTypeId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {

							int num = new BigDecimal(Math.ceil(deptReq.getReqNum().intValue() * per)).intValue();
							reqNumMap.put("reqSum", reqNumMap.get("reqSum") + (num));
							if (reqNumMap.get(deptReq.getRecTypeId()) == null) {
								reqNumMap.put(deptReq.getRecTypeId(), (num));
							} else {
								reqNumMap.put(deptReq.getRecTypeId(), reqNumMap.get(deptReq.getRecTypeId()) + (num));
							}
							if (reqNumMap.get(deptReq.getRecTypeId() + "itemCount") == null) {
								reqNumMap.put(deptReq.getRecTypeId() + "itemCount", 1);
							} else {
								reqNumMap.put(deptReq.getRecTypeId() + "itemCount", reqNumMap.get(deptReq.getRecTypeId() + "itemCount") + 1);
							}
							if (reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) == null) {
								reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), num);
							} else {
								reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) + (num));
							}
							PracticRegistryTypeEnum rectype = PracticRegistryTypeEnum.valueOf(deptReq.getRecTypeId());
							if (rectype != null && rectype.getHaveItem().equals(GlobalConstant.FLAG_Y)) {
								if (StringUtil.isNotBlank(deptReq.getItemId())) {
									List<String> itemIds = itemMap.get(deptReq.getRecTypeId());
									if (itemIds == null) {
										itemIds = new ArrayList<String>();
										itemMap.put(deptReq.getRecTypeId(), itemIds);
									}
									itemIds.add(deptReq.getItemId());
								}
							}
						}
						if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + deptReq.getRecTypeId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {

							int num = new BigDecimal(Math.ceil(deptReq.getReqNum().intValue() * per)).intValue();
							reqNumMap.put("reqSum", reqNumMap.get("reqSum") + (num));
							if (reqNumMap.get(deptReq.getRecTypeId()) == null) {
								reqNumMap.put(deptReq.getRecTypeId(), (num));
							} else {
								reqNumMap.put(deptReq.getRecTypeId(), reqNumMap.get(deptReq.getRecTypeId()) + (num));
							}
							if (reqNumMap.get(deptReq.getRecTypeId() + "itemCount") == null) {
								reqNumMap.put(deptReq.getRecTypeId() + "itemCount", 1);
							} else {
								reqNumMap.put(deptReq.getRecTypeId() + "itemCount", reqNumMap.get(deptReq.getRecTypeId() + "itemCount") + 1);
							}
							if (reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) == null) {
								reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), num);
							} else {
								reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) + (num));
							}
							TheoreticalRegistryTypeEnum rectype = TheoreticalRegistryTypeEnum.valueOf(deptReq.getRecTypeId());
							if (rectype != null && rectype.getHaveItem().equals(GlobalConstant.FLAG_Y)) {
								if (StringUtil.isNotBlank(deptReq.getItemId())) {
									List<String> itemIds = itemMap.get(deptReq.getRecTypeId());
									if (itemIds == null) {
										itemIds = new ArrayList<String>();
										itemMap.put(deptReq.getRecTypeId(), itemIds);
									}
									itemIds.add(deptReq.getItemId());
								}
							}
						}
					}
				}
				finishPerMap.put(standardKey + "finish", defaultString(recFinishMap.get(standardKey)));
				finishPerMap.put(standardKey + "req", defaultString(reqNumMap.get("reqSum")));

				for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
					if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))
							&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())) {
						finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
						if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
							finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
						}

						finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
					}
				}
				for (PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()) {
					if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + regType.getId()))
							&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())) {
						finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
						if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
							finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
						}

						finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
					}
				}
				for (TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()) {
					if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + regType.getId()))
							&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())) {
						finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
						if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
							finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
						}

						finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
					}
				}

				setStarndrdDeptFinishPerMap(finishPerMap, dept, itemMap, recFinishMap, reqNumMap,medicineTypeId);
			}
		}
		return finishPerMap;
	}

	@Override
	public Map<String,String> getFinishPer2(List<SchArrangeResult> arrResultList, ResDoctor resDoctor, SysUser user, Map<String, SchArrangeResult> arrangeResultMap, Map<String, SchRotationGroup> rotationGroupFlowToEntityMap) {
		String doctorFlow = resDoctor.getDoctorFlow();
		Map<String,String> processMap = new HashMap<String, String>();
		Map<String,SchArrangeResult> proResultMap = new HashMap<String,SchArrangeResult>();
		if(arrResultList!=null&&arrResultList.size()>0){
			List<String> resultFlowList = arrResultList.stream().map(vo -> vo.getResultFlow()).collect(Collectors.toList());
			ResDoctorSchProcessExample resDoctorSchProcessExample = new ResDoctorSchProcessExample();
			resDoctorSchProcessExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andSchResultFlowIn(resultFlowList);
			List<ResDoctorSchProcess> resDoctorSchProcessList = processBiz.readResDoctorSchProcessByExample(resDoctorSchProcessExample);
			Map<String, ResDoctorSchProcess> schResultFlowToDoctorSchProcessMap = resDoctorSchProcessList.stream().collect(Collectors.toMap(vo -> vo.getSchResultFlow(), Function.identity(), (vo1, vo2) -> vo1));

			for (SchArrangeResult result : arrResultList) {
				String resultFlow = result.getResultFlow();
				if(StringUtil.isNotBlank(resultFlow)){
					ResDoctorSchProcess process = schResultFlowToDoctorSchProcessMap.get(resultFlow);
					if(process!=null){
						String processFlow = process.getProcessFlow();
						processMap.put(resultFlow,process.getProcessFlow());
						processMap.put(processFlow,resultFlow);
						proResultMap.put(processFlow,result);
					}
				}
			}
		}
		String medicineTypeId="";
		if(user!=null)
			medicineTypeId=user.getMedicineTypeId();
		Map<String,List<String>> recTypeMap=new HashMap<>();
		List<String> recTypeIds = new ArrayList<String>();
		List<String> nowRecTypeIds1 = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
				recTypeIds.add(regType.getId());
				nowRecTypeIds1.add(regType.getId());
			}
			recTypeMap.put("N",nowRecTypeIds1);
		}
		List<String> nowRecTypeIds2 = new ArrayList<String>();
		for(PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_"+regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
				recTypeIds.add(regType.getId());
				nowRecTypeIds2.add(regType.getId());
			}
			recTypeMap.put("BasicPractice",nowRecTypeIds2);
		}
		List<String> nowRecTypeIds3 = new ArrayList<String>();
		for(TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()){

			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_"+regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
				recTypeIds.add(regType.getId());
				nowRecTypeIds3.add(regType.getId());
			}
			recTypeMap.put("TheoreticalStudy",nowRecTypeIds3);
		}
		if(recTypeIds==null || recTypeIds.size()<=0){
			return null;
		}

		Map<String,String> finishPerMap = new HashMap<String, String>();
		Map<String, List<SchArrangeResult>> resultMap = new HashMap<String,List<SchArrangeResult>>();
		List<ResRec> recList = searchFinishRec(recTypeIds,doctorFlow);
		Map<String,Integer> recFinishMap = new HashMap<String, Integer>();
		Map<String,SchRotationDept> resultSchRotationDeptMap = new HashMap<>();
		for (SchArrangeResult result : arrResultList) {
			SchRotationDept schRotationDept = null;
			if(rotationGroupFlowToEntityMap.get(result.getStandardGroupFlow()) != null) {
				schRotationDept = readStandardRotationDept(result);
			}

			resultSchRotationDeptMap.put(result.getResultFlow(),schRotationDept);
		}
		if(recList!=null && recList.size()>0){
			for(ResRec rec : recList){
				String processFlow = rec.getProcessFlow();
				String schDeptFlow = rec.getSchDeptFlow();
				String recTypeId = rec.getRecTypeId();
				String itemId = rec.getItemId();
				SchArrangeResult result = proResultMap.get(processFlow);

				if(result != null){


					SchRotationDept schRotationDepttemp = resultSchRotationDeptMap.get(result.getResultFlow());
					List<String> recTypeIds4Ever = new ArrayList<String>();
					if (schRotationDepttemp == null || JszyTCMPracticEnum.N.getId().equals(schRotationDepttemp.getPracticOrTheory())) {
						recTypeIds4Ever = recTypeMap.get("N");
					} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(schRotationDepttemp.getPracticOrTheory())) {
						recTypeIds4Ever = recTypeMap.get("BasicPractice");
					} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(schRotationDepttemp.getPracticOrTheory())) {
						recTypeIds4Ever = recTypeMap.get("TheoreticalStudy");
					}
					if(recTypeIds4Ever.contains(recTypeId)){
						countFinshFuc(recTypeId, schDeptFlow, result, recFinishMap, itemId, processFlow);
					}
				}

			}
		}
		boolean isReduction =false;
		Map<String,SchDoctorDept> doctorDeptMap=new HashMap<>();
		if(arrResultList!=null&&arrResultList.size()>0){
			//判断学员是否需要减免
			String trainingType = resDoctor.getTrainingTypeId();
			String sessionNumber = resDoctor.getSessionNumber();
			String trainingYears = resDoctor.getTrainingYears();
			isReduction=JszyTrainCategoryEnum.ChineseMedicine.getId().equals(trainingType)||JszyTrainCategoryEnum.TCMGeneral.getId().equals(trainingType);
			isReduction = isReduction && "2015".compareTo(sessionNumber)<=0;
			isReduction = isReduction && (JszyResTrainYearEnum.OneYear.getId().equals(trainingYears) || JszyResTrainYearEnum.TwoYear.getId().equals(trainingYears));

			if(isReduction)
			{
				//获取所有缩减的调整科室
				List<SchDoctorDept> doctorDeptList = searchReductionDept(doctorFlow,resDoctor.getRotationFlow(),resDoctor.getSecondRotationFlow());
				if(doctorDeptList!=null&&doctorDeptList.size()>0)
				{
					//获取标准科室列表以绑定要求数据
					doctorDeptMap = transListObjToMapsSoM(false,doctorDeptList,"groupFlow","standardDeptId");
				}
			}
			List<String> rotationFlowList = arrResultList.stream().map(vo -> vo.getRotationFlow()).collect(Collectors.toList());
			SchRotationExample schRotationExample = new SchRotationExample();
			schRotationExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andRotationFlowIn(rotationFlowList);
			List<SchRotation> schRotationList = rotationBiz.readSchRotationByExample(schRotationExample);
			Map<String, SchRotation> rotationFlowToEntityMap = schRotationList.stream().collect(Collectors.toMap(vo -> vo.getRotationFlow(), Function.identity(), (vo1, vo2) -> vo1));
			for (SchArrangeResult result : arrResultList) {

				String key = result.getStandardGroupFlow()+result.getStandardDeptId();
				List<SchArrangeResult> resultList= resultMap.get(key);
				if(resultList==null){
					resultList = new ArrayList<SchArrangeResult>();
					resultMap.put(key, resultList);
				}
				resultList.add(result);

				String resultFlow = result.getResultFlow();
				//schDeptFlows.add(result.getSchDeptFlow());
				SchRotation rotation=rotationFlowToEntityMap.get(result.getRotationFlow());
				SchRotationDept srd=resultSchRotationDeptMap.get(resultFlow);

				double per=1;
				String baseSchMonth="";
				if(srd!=null)
				{
					baseSchMonth=srd.getSchMonth();
				}
				if(isReduction&&srd!=null)
				{
					SchDoctorDept sdd=doctorDeptMap.get(srd.getGroupFlow()+srd.getStandardDeptId());
					if(sdd!=null)
					{
						if(GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus()))
						{
							per=Double.valueOf(sdd.getSchMonth())/Double.valueOf(srd.getSchMonth());
							baseSchMonth=sdd.getSchMonth();
						}
					}
				}
				//是否翻倍
				if(rotation!=null&&GlobalConstant.FLAG_Y.equals(rotation.getIsDouble())&&StringUtil.isNotBlank(baseSchMonth))
				{
					if(StringUtil.isNotBlank(result.getSchMonth()))
					{
						per=Double.valueOf(result.getSchMonth())/Double.valueOf(baseSchMonth)*per;
					}
				}
				//要求数 todo 双重循环查数据库
				List<SchRotationDeptReq> deptReqList = rotationDeptBiz.searchStandardReqByResult(result,resDoctor);
				Map<String,Integer> reqNumMap = new HashMap<String, Integer>();
				Map<String,List<String>> itemMap = new HashMap<String, List<String>>();
				reqNumMap.put("reqSum",0);
				if(deptReqList!=null && deptReqList.size()>0){
					for(SchRotationDeptReq deptReq : deptReqList){
						if (srd == null || JszyTCMPracticEnum.N.getId().equals(srd.getPracticOrTheory())) {
							if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+deptReq.getRecTypeId()))
									&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())){
								countReqFuc(deptReq,per,reqNumMap,itemMap,"N");
							}
						} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(srd.getPracticOrTheory())) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + deptReq.getRecTypeId()))
									&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {
								countReqFuc(deptReq,per,reqNumMap,itemMap,"BasicPractice");
							}
						} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(srd.getPracticOrTheory())) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + deptReq.getRecTypeId()))
									&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {
								countReqFuc(deptReq,per,reqNumMap,itemMap,"TheoreticalStudy");
							}
						}
					}
				}
				String processFlow = processMap.get(resultFlow);
				String globalUpKey = result.getResultFlow()+result.getStandardGroupFlow()+result.getStandardDeptId();
				finishPerMap.put(resultFlow+"finish",defaultString(recFinishMap.get(globalUpKey)));
				finishPerMap.put(resultFlow+"finishSingle",defaultString(recFinishMap.get(processFlow)));
				finishPerMap.put(resultFlow+"req",defaultString(reqNumMap.get("reqSum")));

				if (srd == null || JszyTCMPracticEnum.N.getId().equals(srd.getPracticOrTheory())) {
					for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(resultFlow+regType.getId()+"finish",defaultString(recFinishMap.get(processFlow+regType.getId())));
							if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
								finishPerMap.put(resultFlow+regType.getId()+"req",defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(resultFlow+regType.getId()+"itemCount",defaultString(reqNumMap.get(regType.getId()+"itemCount")));
						}
					}
				} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(srd.getPracticOrTheory())) {
					for(PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(resultFlow+regType.getId()+"finish",defaultString(recFinishMap.get(processFlow+regType.getId())));
							if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
								finishPerMap.put(resultFlow+regType.getId()+"req",defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(resultFlow+regType.getId()+"itemCount",defaultString(reqNumMap.get(regType.getId()+"itemCount")));
						}
					}
				} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(srd.getPracticOrTheory())) {
					for(TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(resultFlow+regType.getId()+"finish",defaultString(recFinishMap.get(processFlow+regType.getId())));
							if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
								finishPerMap.put(resultFlow+regType.getId()+"req",defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(resultFlow+regType.getId()+"itemCount",defaultString(reqNumMap.get(regType.getId()+"itemCount")));
						}
					}

				}
				setFinishPerMap(finishPerMap,result,itemMap,recFinishMap,reqNumMap,medicineTypeId);
			}

//			List<ResRec> eRecList = searchByRec(ResRecTypeEnum.AfterEvaluation.getId(),schDeptFlows,GlobalContext.getCurrentUser().getUserFlow());
//			if(eRecList!=null && eRecList.size()>0){
//				for(ResRec eRec : eRecList){
//					Map<String,String> recContent = parseRecContent(eRec.getRecContent());
//					if(recContent!=null){
//						finishPerMap.put(eRec.getSchDeptFlow(),recContent.get("totalScore"));
//					}
//				}
//			}
		}

		List<SchRotationDept> allDeptList=new ArrayList<>();	//轮转科室
		List<SchRotationDept> deptList=null;	//轮转科室
		//PPbear
		if(StringUtil.isNotBlank(resDoctor.getRotationFlow())){
			deptList=rotationDeptBiz.searchSchRotationDept(resDoctor.getRotationFlow());
			if(deptList!=null && !deptList.isEmpty() ){
				allDeptList.addAll(deptList);
			}
		}
		//第二专业
		if(StringUtil.isNotBlank(resDoctor.getSecondRotationFlow())){
			deptList=rotationDeptBiz.searchSchRotationDept(resDoctor.getSecondRotationFlow());
			if(deptList!=null && !deptList.isEmpty() ){
				allDeptList.addAll(deptList);
			}
		}
		if(allDeptList.size()>0) {
			List<String> recordFlowList = allDeptList.stream().map(vo -> vo.getRecordFlow()).collect(Collectors.toList());
			SchRotationDeptReqExample schRotationDeptReqExample = new SchRotationDeptReqExample();
			schRotationDeptReqExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andRelRecordFlowIn(recordFlowList);
			List<SchRotationDeptReq> schRotationDeptReqList = rotationDeptBiz.searchDeptReqByExample(schRotationDeptReqExample);
			Map<String, List<SchRotationDeptReq>> relRecordFlowToListEntityMap = schRotationDeptReqList.stream().collect(Collectors.groupingBy(vo -> vo.getRelRecordFlow()));

			List<String> rotationFlowList = allDeptList.stream().map(vo -> vo.getRotationFlow()).collect(Collectors.toList());
			SchRotationExample schRotationExample = new SchRotationExample();
			schRotationExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
							.andRotationFlowIn(rotationFlowList);
			List<SchRotation> schRotationList = rotationBiz.readSchRotationByExample(schRotationExample);
			Map<String, SchRotation> rotationFlowToEntityMap = schRotationList.stream().collect(Collectors.toMap(vo -> vo.getRotationFlow(), Function.identity(), (vo1, vo2) -> vo1));
			for(SchRotationDept dept:allDeptList) {

				String standardKey = dept.getGroupFlow() + dept.getStandardDeptId();

				SchRotation rotation = rotationFlowToEntityMap.get(dept.getRotationFlow());
				double per = 1;
				String baseSchMonth = dept.getSchMonth();
				if (isReduction) {
					SchDoctorDept sdd = doctorDeptMap.get(standardKey);
					if (sdd != null) {
						if (GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus())) {
							per = Double.valueOf(sdd.getSchMonth()) / Double.valueOf(dept.getSchMonth());
							baseSchMonth = sdd.getSchMonth();
						}
					}
				}
				//是否翻倍
				if (rotation != null && GlobalConstant.FLAG_Y.equals(rotation.getIsDouble()) && StringUtil.isNotBlank(baseSchMonth)) {
					List<SchArrangeResult> results=resultMap.get(standardKey);
					if(results!=null&&results.size()>0) {
						double allMonth = 0;
						for (SchArrangeResult result : results) {
							if (StringUtil.isNotBlank(result.getSchMonth())) {
								allMonth+=Double.valueOf(result.getSchMonth());
							}
						}
						if(allMonth>0)
						{
							per = allMonth / Double.valueOf(baseSchMonth) * per;
						}
					}
				}
				//要求数
				List<SchRotationDeptReq> deptReqList = relRecordFlowToListEntityMap.get(dept.getRecordFlow());
				Map<String, Integer> reqNumMap = new HashMap<String, Integer>();
				Map<String, List<String>> itemMap = new HashMap<String, List<String>>();
				reqNumMap.put("reqSum", 0);
				if (deptReqList != null && deptReqList.size() > 0) {
					for (SchRotationDeptReq deptReq : deptReqList) {
						setStarndrdDeptReqMap(dept,deptReq,per,reqNumMap,itemMap,medicineTypeId);
					}
				}
				finishPerMap.put(standardKey + "finish", defaultString(recFinishMap.get(standardKey)));
				finishPerMap.put(standardKey + "req", defaultString(reqNumMap.get("reqSum")));

				if(JszyTCMPracticEnum.N.getId().equals(dept.getPracticOrTheory())){
					for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
							if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
								finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
						}
					}
				}else if(JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory())){
					for(PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
							if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
								finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
						}
					}
				}else if(JszyTCMPracticEnum.TheoreticalStudy.getId().equals(dept.getPracticOrTheory())){
					for(TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
							if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
								finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
						}
					}
				}


				setStarndrdDeptFinishPerMap(finishPerMap, dept, itemMap, recFinishMap, reqNumMap,medicineTypeId);
			}
		}
		return finishPerMap;
	}

	/**
	 * 原来单个医生的操作改为多个医生
	 * @param arrResultListMap
	 * @param resDoctorList
	 * @param userFlowToEntityMap
	 * @param arrangeResultMap
	 * @param rotationGroupFlowToEntityMap
	 * @return
	 */
	@Override
	public Map<String, Map<String, String>> getFinishPer2List(Map<String, List<SchArrangeResult>> arrResultListMap, List<ResDoctor> resDoctorList, Map<String, SysUser> userFlowToEntityMap, Map<String, SchArrangeResult> arrangeResultMap,
		Map<String, SchRotationGroup> rotationGroupFlowToEntityMap, Map<String, SchRotationDept> keyToRotationDeptMap) {
		Map<String, Map<String, String>> res = new HashMap<>();
		Map<String,String> processMap = new HashMap<String, String>();
		Map<String,SchArrangeResult> proResultMap = new HashMap<String,SchArrangeResult>();
		List<SchArrangeResult> arrResultAllList = new ArrayList<>();
		for (List<SchArrangeResult> value : arrResultListMap.values()) {
			arrResultAllList.addAll(value);
		}
		if(arrResultAllList!=null&&arrResultAllList.size()>0){
			List<String> resultFlowList = arrResultAllList.stream().map(vo -> vo.getResultFlow()).collect(Collectors.toList());
			List<List<String>> resultFlowPartionList = Lists.partition(resultFlowList, 1000);
			List<ResDoctorSchProcess> resDoctorSchProcessList = processBiz.searchByResultFlowPartitionList(resultFlowPartionList);
			Map<String, ResDoctorSchProcess> schResultFlowToDoctorSchProcessMap = resDoctorSchProcessList.stream().collect(Collectors.toMap(vo -> vo.getSchResultFlow(), Function.identity(), (vo1, vo2) -> vo1));

			for (SchArrangeResult result : arrResultAllList) {
				String resultFlow = result.getResultFlow();
				if(StringUtil.isNotBlank(resultFlow)){
					ResDoctorSchProcess process = schResultFlowToDoctorSchProcessMap.get(resultFlow);
					if(process!=null){
						String processFlow = process.getProcessFlow();
						processMap.put(resultFlow,process.getProcessFlow());
						processMap.put(processFlow,resultFlow);
						proResultMap.put(processFlow,result);
					}
				}
			}
		}
		Map<String, String> doctorFlowToMedicineTypeIdMap = new HashMap<>();
		Map<String, List<String>> medicineTypeToDoctorMap = new HashMap<>();
		for (ResDoctor resDoctor : resDoctorList) {
			SysUser user = userFlowToEntityMap.get(resDoctor.getDoctorFlow());
			if(user != null) {
				doctorFlowToMedicineTypeIdMap.put(user.getUserFlow(), user.getMedicineTypeId());
				List<String> tempList = medicineTypeToDoctorMap.getOrDefault(user.getMedicineTypeId(), new ArrayList<>());
				tempList.add(user.getUserFlow());
				medicineTypeToDoctorMap.put(user.getMedicineTypeId(), tempList);
			}
		}

		Map<String, Map<String,List<String>>> recTypeDoctorMap = new HashMap<>();
		Map<String, List<String>> recTypeIdDoctorMap = new HashMap<>();

		Map<String, List<String>> medicineTypeToRecTypeMap = new HashMap<>();
		for (ResDoctor resDoctor : resDoctorList) {
			Map<String, List<String>> recTypeMap = new HashMap<>();
			List<String> recTypeIds = new ArrayList<String>();
			List<String> nowRecTypeIds1 = new ArrayList<String>();
			for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
				if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))
						&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(resDoctor.getDoctorFlow()), regType.getId())) {
					recTypeIds.add(regType.getId());
					nowRecTypeIds1.add(regType.getId());
				}
				recTypeMap.put("N", nowRecTypeIds1);
			}
			List<String> nowRecTypeIds2 = new ArrayList<String>();
			for (PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()) {
				if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + regType.getId()))
						&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(resDoctor.getDoctorFlow()), regType.getId())) {
					recTypeIds.add(regType.getId());
					nowRecTypeIds2.add(regType.getId());
				}
				recTypeMap.put("BasicPractice", nowRecTypeIds2);
			}
			List<String> nowRecTypeIds3 = new ArrayList<String>();
			for (TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()) {

				if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + regType.getId()))
						&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(resDoctor.getDoctorFlow()), regType.getId())) {
					recTypeIds.add(regType.getId());
					nowRecTypeIds3.add(regType.getId());
				}
				recTypeMap.put("TheoreticalStudy", nowRecTypeIds3);
			}

			if(CollectionUtils.isNotEmpty(recTypeIds)) {
				recTypeDoctorMap.put(resDoctor.getDoctorFlow(), recTypeMap);
				recTypeIdDoctorMap.put(resDoctor.getDoctorFlow(), recTypeIds);
				String medicineType = doctorFlowToMedicineTypeIdMap.get(resDoctor.getDoctorFlow());
				if(medicineTypeToRecTypeMap.get(medicineType) == null) {
					medicineTypeToRecTypeMap.put(medicineType, recTypeIds);
				}
			}
		}
		if(MapUtils.isEmpty(recTypeIdDoctorMap)){
			return new HashMap<>();
		}

		Map<String, List<SchArrangeResult>> resultMap = new HashMap<String,List<SchArrangeResult>>();
		Map<String, Map<String,Integer>> recFinishDoctorMap = new HashMap<>();
		List<ResRec> recAllList = searchFinishRec2(medicineTypeToDoctorMap, medicineTypeToRecTypeMap);
		Map<String,SchRotationDept> resultSchRotationDeptMap = new HashMap<>();
		for (SchArrangeResult result : arrResultAllList) {
			SchRotationDept schRotationDept = null;
			if(rotationGroupFlowToEntityMap.get(result.getStandardGroupFlow()) != null) {
				schRotationDept = keyToRotationDeptMap.get(result.getRotationFlow() + result.getStandardDeptId() + result.getStandardGroupFlow());
			}

			resultSchRotationDeptMap.put(result.getResultFlow(),schRotationDept);
		}
		if(recAllList!=null && recAllList.size()>0){
			Map<String, List<ResRec>> resRecDoctorMap = recAllList.stream().collect(Collectors.groupingBy(vo -> vo.getOperUserFlow()));
			for(Map.Entry<String, List<ResRec>> entry: resRecDoctorMap.entrySet()) {
				Map<String,Integer> recFinishMap = new HashMap<String, Integer>();
				List<ResRec> recList = entry.getValue();
				Map<String, List<String>> recTypeMap = recTypeDoctorMap.get(entry.getKey());
				for(ResRec rec : recList) {
					String processFlow = rec.getProcessFlow();
					String schDeptFlow = rec.getSchDeptFlow();
					String recTypeId = rec.getRecTypeId();
					String itemId = rec.getItemId();
					SchArrangeResult result = proResultMap.get(processFlow);

					if (result != null) {


						SchRotationDept schRotationDepttemp = resultSchRotationDeptMap.get(result.getResultFlow());
						List<String> recTypeIds4Ever = new ArrayList<String>();
						if (schRotationDepttemp == null || JszyTCMPracticEnum.N.getId().equals(schRotationDepttemp.getPracticOrTheory())) {
							recTypeIds4Ever = recTypeMap.get("N");
						} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(schRotationDepttemp.getPracticOrTheory())) {
							recTypeIds4Ever = recTypeMap.get("BasicPractice");
						} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(schRotationDepttemp.getPracticOrTheory())) {
							recTypeIds4Ever = recTypeMap.get("TheoreticalStudy");
						}
						if (recTypeIds4Ever.contains(recTypeId)) {
							countFinshFuc(recTypeId, schDeptFlow, result, recFinishMap, itemId, processFlow);
						}
					}
				}
				recFinishDoctorMap.put(entry.getKey(), recFinishMap);
			}
		}
		boolean isReduction =false;
		Map<String,SchDoctorDept> doctorDeptMap=new HashMap<>();
		Map<String, Boolean> isReductionMap = new HashMap<>();
		if(arrResultAllList!=null&&arrResultAllList.size()>0){
			List<ResDoctor> reductionDoctorList = new ArrayList<>();
			for (ResDoctor resDoctor : resDoctorList) {
				//判断学员是否需要减免
				String trainingType = resDoctor.getTrainingTypeId();
				String sessionNumber = resDoctor.getSessionNumber();
				String trainingYears = resDoctor.getTrainingYears();
				isReduction=JszyTrainCategoryEnum.ChineseMedicine.getId().equals(trainingType)||JszyTrainCategoryEnum.TCMGeneral.getId().equals(trainingType);
				isReduction = isReduction && "2015".compareTo(sessionNumber)<=0;
				isReduction = isReduction && (JszyResTrainYearEnum.OneYear.getId().equals(trainingYears) || JszyResTrainYearEnum.TwoYear.getId().equals(trainingYears));

				if(isReduction) {
					reductionDoctorList.add(resDoctor);
				}
				isReductionMap.put(resDoctor.getDoctorFlow(), isReduction);
			}


			if(CollectionUtils.isNotEmpty(reductionDoctorList))
			{
				//获取所有缩减的调整科室
				List<SchDoctorDept> doctorDeptList = searchReductionDept2(reductionDoctorList);
				if(doctorDeptList!=null&&doctorDeptList.size()>0)
				{
					//获取标准科室列表以绑定要求数据
					doctorDeptMap = transListObjToMapsSoM(false,doctorDeptList,"doctorFlow", "groupFlow","standardDeptId");
				}
			}
			List<String> rotationFlowList = arrResultAllList.stream().map(vo -> vo.getRotationFlow()).collect(Collectors.toList());
			List<List<String>> rotationFlowListList = Lists.partition(rotationFlowList, 1000);
			List<SchRotation> schRotationList = rotationBiz.readSchRotationByPartitionList(rotationFlowListList);
			Map<String, SchRotation> rotationFlowToEntityMap = schRotationList.stream().collect(Collectors.toMap(vo -> vo.getRotationFlow(), Function.identity(), (vo1, vo2) -> vo1));

			List<String> stdGroupFlowList = new ArrayList<>();
			List<String> stdDeptIdList = new ArrayList<>();
			arrResultAllList.forEach(arrResult -> {
				stdGroupFlowList.add(arrResult.getStandardGroupFlow());
				stdDeptIdList.add(arrResult.getStandardDeptId());
			});
			Pair<List<SchRotationDeptReq>, List<SchRotationDept>> listPair = rotationDeptBiz.searchStandardReqByGroup2(stdGroupFlowList, stdDeptIdList);
			List<SchRotationDept> schRotationDeptList = listPair.getRight();
			List<SchRotationDeptReq> schRotationDeptReqList = listPair.getLeft();
			Map<String, List<SchRotationDeptReq>> relRecordFlowToEntityMap = schRotationDeptReqList.stream().collect(Collectors.groupingBy(vo -> vo.getRelRecordFlow()));
			Map<String, List<SchRotationDept>> keyToRotationDeptMap2 = schRotationDeptList.stream().collect(Collectors.groupingBy(vo -> vo.getGroupFlow() + vo.getStandardDeptId()));
			Map<String, List<SchRotationDeptReq>> keyToRotationDeptReqMap = new HashMap<>();
			for(Map.Entry<String, List<SchRotationDept>> entry: keyToRotationDeptMap2.entrySet()) {
				List<SchRotationDept> value = entry.getValue();
				value.forEach(vo -> {
					List<SchRotationDeptReq> schRotationDeptReqList1 = relRecordFlowToEntityMap.get(vo.getRecordFlow());
					if(CollectionUtils.isNotEmpty(schRotationDeptReqList1)) {
						List<SchRotationDeptReq> tempList = keyToRotationDeptReqMap.getOrDefault(entry.getKey(), new ArrayList<>());
						tempList.addAll(schRotationDeptReqList1);
						keyToRotationDeptReqMap.put(entry.getKey(), tempList);
					}
				});
			}
			for (Map.Entry<String, List<SchArrangeResult>> entry : arrResultListMap.entrySet()) {
				List<SchArrangeResult> resultList1 = entry.getValue();
				for(SchArrangeResult result: resultList1) {
					String key = entry.getKey() + result.getStandardGroupFlow() + result.getStandardDeptId();
					Map<String, Integer> recFinishMap = recFinishDoctorMap.getOrDefault(entry.getKey(), new HashMap<>());
					List<SchArrangeResult> resultList = resultMap.get(key);
					if (resultList == null) {
						resultList = new ArrayList<SchArrangeResult>();
						resultMap.put(key, resultList);
					}
					resultList.add(result);

					String resultFlow = result.getResultFlow();
					//schDeptFlows.add(result.getSchDeptFlow());
					SchRotation rotation = rotationFlowToEntityMap.get(result.getRotationFlow());
					SchRotationDept srd = resultSchRotationDeptMap.get(resultFlow);

					double per = 1;
					String baseSchMonth = "";
					if (srd != null) {
						baseSchMonth = srd.getSchMonth();
					}
					if (isReduction && srd != null) {
						SchDoctorDept sdd = doctorDeptMap.get(entry.getKey() + srd.getGroupFlow() + srd.getStandardDeptId());
						if (sdd != null) {
							if (GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus())) {
								per = Double.valueOf(sdd.getSchMonth()) / Double.valueOf(srd.getSchMonth());
								baseSchMonth = sdd.getSchMonth();
							}
						}
					}
					//是否翻倍
					if (rotation != null && GlobalConstant.FLAG_Y.equals(rotation.getIsDouble()) && StringUtil.isNotBlank(baseSchMonth)) {
						if (StringUtil.isNotBlank(result.getSchMonth())) {
							per = Double.valueOf(result.getSchMonth()) / Double.valueOf(baseSchMonth) * per;
						}
					}
					//要求数
					Map<String, Integer> reqNumMap = new HashMap<String, Integer>();
					Map<String, List<String>> itemMap = new HashMap<String, List<String>>();
					reqNumMap.put("reqSum", 0);
					List<SchRotationDeptReq> deptReqList = keyToRotationDeptReqMap.get(result.getStandardGroupFlow() + result.getStandardDeptId());
					if (deptReqList != null && deptReqList.size() > 0) {
						for (SchRotationDeptReq deptReq : deptReqList) {
							if (srd == null || JszyTCMPracticEnum.N.getId().equals(srd.getPracticOrTheory())) {
								if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + deptReq.getRecTypeId()))
										&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(entry.getKey()), deptReq.getRecTypeId())) {
									countReqFuc(deptReq, per, reqNumMap, itemMap, "N");
								}
							} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(srd.getPracticOrTheory())) {
								if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + deptReq.getRecTypeId()))
										&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(entry.getKey()), deptReq.getRecTypeId())) {
									countReqFuc(deptReq, per, reqNumMap, itemMap, "BasicPractice");
								}
							} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(srd.getPracticOrTheory())) {
								if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + deptReq.getRecTypeId()))
										&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(entry.getKey()), deptReq.getRecTypeId())) {
									countReqFuc(deptReq, per, reqNumMap, itemMap, "TheoreticalStudy");
								}
							}
						}
					}
					String processFlow = processMap.get(resultFlow);
					String globalUpKey = result.getResultFlow() + result.getStandardGroupFlow() + result.getStandardDeptId();
					Map<String,String> finishPerMap = new HashMap<String, String>();
					finishPerMap.put(resultFlow + "finish", defaultString(recFinishMap.get(globalUpKey)));
					finishPerMap.put(resultFlow + "finishSingle", defaultString(recFinishMap.get(processFlow)));
					finishPerMap.put(resultFlow + "req", defaultString(reqNumMap.get("reqSum")));

					if (srd == null || JszyTCMPracticEnum.N.getId().equals(srd.getPracticOrTheory())) {
						for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))
									&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(entry.getKey()), regType.getId())) {
								finishPerMap.put(resultFlow + regType.getId() + "finish", defaultString(recFinishMap.get(processFlow + regType.getId())));
								if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
									finishPerMap.put(resultFlow + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
								}

								finishPerMap.put(resultFlow + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
							}
						}
					} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(srd.getPracticOrTheory())) {
						for (PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + regType.getId()))
									&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(entry.getKey()), regType.getId())) {
								finishPerMap.put(resultFlow + regType.getId() + "finish", defaultString(recFinishMap.get(processFlow + regType.getId())));
								if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
									finishPerMap.put(resultFlow + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
								}

								finishPerMap.put(resultFlow + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
							}
						}
					} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(srd.getPracticOrTheory())) {
						for (TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + regType.getId()))
									&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(entry.getKey()), regType.getId())) {
								finishPerMap.put(resultFlow + regType.getId() + "finish", defaultString(recFinishMap.get(processFlow + regType.getId())));
								if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
									finishPerMap.put(resultFlow + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
								}

								finishPerMap.put(resultFlow + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
							}
						}

					}
					setFinishPerMap(finishPerMap, result, itemMap, recFinishMap, reqNumMap, doctorFlowToMedicineTypeIdMap.get(entry.getKey()));
					res.put(entry.getKey() + resultFlow, finishPerMap);
				}
			}
		}

		List<SchRotationDept> allDeptList=new ArrayList<>();	//轮转科室
		List<SchRotationDept> deptList=null;	//轮转科室

		List<String> rotationFlowList = resDoctorList.stream().filter(vo -> StringUtils.isNotBlank(vo.getRotationFlow())).map(vo -> vo.getRotationFlow()).collect(Collectors.toList());
		rotationFlowList.addAll(resDoctorList.stream().filter(vo -> StringUtils.isNotBlank(vo.getSecondRotationFlow())).map(vo -> vo.getSecondRotationFlow()).collect(Collectors.toList()));
		List<List<String>> rotationFlowListList = Lists.partition(rotationFlowList, 1000);
		List<SchRotationDept> schRotationDeptList = rotationDeptBiz.searchSchRotationDeptByPartitionList(rotationFlowListList);

		Map<String, List<SchRotationDept>> doctorToRotationDeptMap = new HashMap<>();
		Map<String, List<SchRotationDept>> flowToRotationDeptMap = schRotationDeptList.stream().collect(Collectors.groupingBy(vo -> vo.getRotationFlow()));
		for (ResDoctor resDoctor : resDoctorList) {
			if(StringUtils.isNotBlank(resDoctor.getRotationFlow())) {
				deptList = flowToRotationDeptMap.getOrDefault(resDoctor.getRotationFlow(), new ArrayList<>());
				doctorToRotationDeptMap.put(resDoctor.getDoctorFlow(), deptList);
			}
			if(StringUtils.isNotBlank(resDoctor.getSecondRotationFlow())) {
				deptList = flowToRotationDeptMap.getOrDefault(resDoctor.getSecondRotationFlow(), new ArrayList<>());
				List<SchRotationDept> tempList = doctorToRotationDeptMap.getOrDefault(resDoctor.getDoctorFlow(), new ArrayList<>());
				tempList.addAll(deptList);
				doctorToRotationDeptMap.put(resDoctor.getDoctorFlow(), tempList);
			}
		}


		List<String> allRotationFlowList = schRotationDeptList.stream().map(vo -> vo.getRotationFlow()).collect(Collectors.toList());
		List<List<String>> allRotationFlowListList = Lists.partition(allRotationFlowList, 1000);
		List<SchRotation> schRotationList1 = rotationBiz.readSchRotationByPartitionList(allRotationFlowListList);
		Map<String, List<SchRotation>> rotationFlowToEntityMap2 = schRotationList1.stream().collect(Collectors.groupingBy(vo -> vo.getRotationFlow()));

		List<String> allRecordFlowList = schRotationDeptList.stream().map(vo -> vo.getRecordFlow()).collect(Collectors.toList());
		List<List<String>> allRecordFlowListList = Lists.partition(allRecordFlowList, 1000);
		List<SchRotationDeptReq> schRotationDeptReqList1 = rotationDeptBiz.searchDeptReqByRel2(allRecordFlowListList);
		Map<String, List<SchRotationDeptReq>> relRecordToEntityMap = schRotationDeptReqList1.stream().collect(Collectors.groupingBy(vo -> vo.getRelRecordFlow()));

		Map<String, SchRotationDept> flowToRotationDeptMap2 = schRotationDeptList.stream().collect(Collectors.toMap(vo -> vo.getRecordFlow(), Function.identity(), (vo1, vo2) -> vo1));
		Map<String, List<ResDoctor>> rotationToDoctorListMap = resDoctorList.stream().collect(Collectors.groupingBy(vo -> vo.getRotationFlow()));

		Map<String, List<SchRotation>> doctorToSchRotationMap = new HashMap<>();
		Map<String, List<SchRotationDeptReq>> doctorToSchRotationDeptReqMap = new HashMap<>();
		for (ResDoctor resDoctor : resDoctorList) {
			List<SchRotation> tempRotationList = rotationFlowToEntityMap2.getOrDefault(resDoctor.getRotationFlow(), new ArrayList<>());
			doctorToSchRotationMap.put(resDoctor.getDoctorFlow(), tempRotationList);
		}
		for(Map.Entry<String, List<SchRotationDeptReq>> entry: relRecordToEntityMap.entrySet()) {
			SchRotationDept schRotationDept = flowToRotationDeptMap2.get(entry.getKey());
			if(null != schRotationDept) {
				List<ResDoctor> resDoctorList1 = rotationToDoctorListMap.get(schRotationDept.getRotationFlow());
				for (ResDoctor resDoctor : resDoctorList1) {
					List<SchRotationDeptReq> rotationReqList = doctorToSchRotationDeptReqMap.getOrDefault(resDoctor.getDoctorFlow(), new ArrayList<>());
					rotationReqList.addAll(entry.getValue());
					doctorToSchRotationDeptReqMap.put(resDoctor.getDoctorFlow(), rotationReqList);
				}
			}
		}


		for (ResDoctor resDoctor : resDoctorList) {
			Map<String, Integer> recFinishMap = recFinishDoctorMap.getOrDefault(resDoctor.getDoctorFlow(), new HashMap<>());
			Map<String, String> finishPerMap = res.getOrDefault(resDoctor.getDoctorFlow(), new HashMap<>());
			allDeptList = doctorToRotationDeptMap.get(resDoctor.getDoctorFlow());
			if (allDeptList.size() > 0) {
				List<SchRotationDeptReq> schRotationDeptReqList = doctorToSchRotationDeptReqMap.get(resDoctor.getDoctorFlow());
				Map<String, List<SchRotationDeptReq>> relRecordFlowToListEntityMap = schRotationDeptReqList.stream().collect(Collectors.groupingBy(vo -> vo.getRelRecordFlow()));

				List<SchRotation> schRotationList = doctorToSchRotationMap.get(resDoctor.getDoctorFlow());
				Map<String, SchRotation> rotationFlowToEntityMap = schRotationList.stream().collect(Collectors.toMap(vo -> vo.getRotationFlow(), Function.identity(), (vo1, vo2) -> vo1));

				for (SchRotationDept dept : allDeptList) {

					String standardKey = dept.getGroupFlow() + dept.getStandardDeptId();

					SchRotation rotation = rotationFlowToEntityMap.get(dept.getRotationFlow());
					double per = 1;
					String baseSchMonth = dept.getSchMonth();
					if (isReduction) {
						SchDoctorDept sdd = doctorDeptMap.get(standardKey);
						if (sdd != null) {
							if (GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus())) {
								per = Double.valueOf(sdd.getSchMonth()) / Double.valueOf(dept.getSchMonth());
								baseSchMonth = sdd.getSchMonth();
							}
						}
					}
					//是否翻倍
					if (rotation != null && GlobalConstant.FLAG_Y.equals(rotation.getIsDouble()) && StringUtil.isNotBlank(baseSchMonth)) {
						List<SchArrangeResult> results = resultMap.get(standardKey);
						if (results != null && results.size() > 0) {
							double allMonth = 0;
							for (SchArrangeResult result : results) {
								if (StringUtil.isNotBlank(result.getSchMonth())) {
									allMonth += Double.valueOf(result.getSchMonth());
								}
							}
							if (allMonth > 0) {
								per = allMonth / Double.valueOf(baseSchMonth) * per;
							}
						}
					}
					//要求数
					List<SchRotationDeptReq> deptReqList = relRecordFlowToListEntityMap.get(dept.getRecordFlow());
					Map<String, Integer> reqNumMap = new HashMap<String, Integer>();
					Map<String, List<String>> itemMap = new HashMap<String, List<String>>();
					reqNumMap.put("reqSum", 0);
					if (deptReqList != null && deptReqList.size() > 0) {
						for (SchRotationDeptReq deptReq : deptReqList) {
							setStarndrdDeptReqMap(dept, deptReq, per, reqNumMap, itemMap, doctorFlowToMedicineTypeIdMap.get(resDoctor.getDoctorFlow()));
						}
					}
					finishPerMap.put(standardKey + "finish", defaultString(recFinishMap.get(standardKey)));
					finishPerMap.put(standardKey + "req", defaultString(reqNumMap.get("reqSum")));

					if (JszyTCMPracticEnum.N.getId().equals(dept.getPracticOrTheory())) {
						for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))
									&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(resDoctor.getDoctorFlow()), regType.getId())) {
								finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
								if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
									finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
								}

								finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
							}
						}
					} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory())) {
						for (PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + regType.getId()))
									&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(resDoctor.getDoctorFlow()), regType.getId())) {
								finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
								if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
									finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
								}

								finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
							}
						}
					} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(dept.getPracticOrTheory())) {
						for (TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + regType.getId()))
									&& PdUtil.findChineseOrWestern(doctorFlowToMedicineTypeIdMap.get(resDoctor.getDoctorFlow()), regType.getId())) {
								finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
								if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
									finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
								}

								finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
							}
						}
					}


					setStarndrdDeptFinishPerMap(finishPerMap, dept, itemMap, recFinishMap, reqNumMap, doctorFlowToMedicineTypeIdMap.get(resDoctor.getDoctorFlow()));
					res.put(resDoctor.getDoctorFlow() + standardKey, finishPerMap);
				}
			}
		}
		return res;
	}

	private SchRotationDept readStandardRotationDept(SchArrangeResult result){
		SchRotationDept rotationDept = null;
		if(result!=null){
			String rotationFlow = result.getRotationFlow();
			String standardDeptId = result.getStandardDeptId();
			if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(standardDeptId)){
				SchRotationDeptExample example = new SchRotationDeptExample();
				SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y)
						.andRotationFlowEqualTo(rotationFlow)
						.andStandardDeptIdEqualTo(standardDeptId)
						.andOrgFlowIsNull()
						.andGroupFlowEqualTo(result.getStandardGroupFlow());
				List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExampleWithBLOBs(example);
				if(rotationDeptList!=null && rotationDeptList.size()>0){
					rotationDept = rotationDeptList.get(0);
				}
			}
		}
		return rotationDept;
	}

	@Override
	public Map<String,String> getFinishPer(List<SchArrangeResult> arrResultList){
		return getFinishPer(arrResultList,GlobalContext.getCurrentUser().getUserFlow());
	}

	@Override
	public Map<String,String> getFinishPer(List<SchArrangeResult> arrResultList,String doctorFlow){
		Map<String,String> processMap = new HashMap<String, String>();
		Map<String,SchArrangeResult> proResultMap = new HashMap<String,SchArrangeResult>();
		if(arrResultList!=null&&arrResultList.size()>0){
			for (SchArrangeResult result : arrResultList) {
				String resultFlow = result.getResultFlow();
				if(StringUtil.isNotBlank(resultFlow)){
					ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
					if(process!=null){
						String processFlow = process.getProcessFlow();
						processMap.put(resultFlow,process.getProcessFlow());
						processMap.put(processFlow,resultFlow);
						proResultMap.put(processFlow,result);
					}
				}
			}
		}
		SysUser user=userBiz.readSysUser(doctorFlow);
		String medicineTypeId="";
		if(user!=null)
			medicineTypeId=user.getMedicineTypeId();
		Map<String,List<String>> recTypeMap=new HashMap<>();
		List<String> recTypeIds = new ArrayList<String>();
		List<String> nowRecTypeIds1 = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
				recTypeIds.add(regType.getId());
				nowRecTypeIds1.add(regType.getId());
			}
			recTypeMap.put("N",nowRecTypeIds1);
		}
		List<String> nowRecTypeIds2 = new ArrayList<String>();
		for(PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_"+regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
				recTypeIds.add(regType.getId());
				nowRecTypeIds2.add(regType.getId());
			}
			recTypeMap.put("BasicPractice",nowRecTypeIds2);
		}
		List<String> nowRecTypeIds3 = new ArrayList<String>();
		for(TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()){

			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_"+regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
				recTypeIds.add(regType.getId());
				nowRecTypeIds3.add(regType.getId());
			}
			recTypeMap.put("TheoreticalStudy",nowRecTypeIds3);
		}
		if(recTypeIds==null || recTypeIds.size()<=0){
			return null;
		}

		Map<String,String> finishPerMap = new HashMap<String, String>();
		Map<String, List<SchArrangeResult>> resultMap = new HashMap<String,List<SchArrangeResult>>();
		List<ResRec> recList = searchFinishRec(recTypeIds,doctorFlow);
		Map<String,Integer> recFinishMap = new HashMap<String, Integer>();
		Map<String,SchRotationDept> resultSchRotationDeptMap = new HashMap<>();
		for (SchArrangeResult result : arrResultList) {
			SchRotationDept schRotationDept = rotationDeptBiz.readStandardRotationDept(result.getResultFlow());
			resultSchRotationDeptMap.put(result.getResultFlow(),schRotationDept);
		}
		if(recList!=null && recList.size()>0){
			for(ResRec rec : recList){
				String processFlow = rec.getProcessFlow();
				String schDeptFlow = rec.getSchDeptFlow();
				String recTypeId = rec.getRecTypeId();
				String itemId = rec.getItemId();
				SchArrangeResult result = proResultMap.get(processFlow);

				if(result != null){


					SchRotationDept schRotationDepttemp = resultSchRotationDeptMap.get(result.getResultFlow());
					List<String> recTypeIds4Ever = new ArrayList<String>();
					if (schRotationDepttemp == null || JszyTCMPracticEnum.N.getId().equals(schRotationDepttemp.getPracticOrTheory())) {
						recTypeIds4Ever = recTypeMap.get("N");
					} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(schRotationDepttemp.getPracticOrTheory())) {
						recTypeIds4Ever = recTypeMap.get("BasicPractice");
					} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(schRotationDepttemp.getPracticOrTheory())) {
						recTypeIds4Ever = recTypeMap.get("TheoreticalStudy");
					}
					if(recTypeIds4Ever.contains(recTypeId)){
						countFinshFuc(recTypeId, schDeptFlow, result, recFinishMap, itemId, processFlow);
					}
				}

			}
		}
		boolean isReduction =false;
		Map<String,SchDoctorDept> doctorDeptMap=new HashMap<>();
		ResDoctor doctor=resDoctorBiz.readDoctor(doctorFlow);
		if(arrResultList!=null&&arrResultList.size()>0){
			//判断学员是否需要减免
			String trainingType = doctor.getTrainingTypeId();
			String sessionNumber = doctor.getSessionNumber();
			String trainingYears = doctor.getTrainingYears();
			isReduction=JszyTrainCategoryEnum.ChineseMedicine.getId().equals(trainingType)||JszyTrainCategoryEnum.TCMGeneral.getId().equals(trainingType);
			isReduction = isReduction && "2015".compareTo(sessionNumber)<=0;
			isReduction = isReduction && (JszyResTrainYearEnum.OneYear.getId().equals(trainingYears) || JszyResTrainYearEnum.TwoYear.getId().equals(trainingYears));

			if(isReduction)
			{
				//获取所有缩减的调整科室
				List<SchDoctorDept> doctorDeptList = searchReductionDept(doctorFlow,doctor.getRotationFlow(),doctor.getSecondRotationFlow());
				if(doctorDeptList!=null&&doctorDeptList.size()>0)
				{
					//获取标准科室列表以绑定要求数据
					doctorDeptMap = transListObjToMapsSoM(false,doctorDeptList,"groupFlow","standardDeptId");
				}
			}
			//List<String> schDeptFlows = new ArrayList<String>();

			for (SchArrangeResult result : arrResultList) {

				String key = result.getStandardGroupFlow()+result.getStandardDeptId();
				List<SchArrangeResult> resultList= resultMap.get(key);
				if(resultList==null){
					resultList = new ArrayList<SchArrangeResult>();
					resultMap.put(key, resultList);
				}
				resultList.add(result);

				String resultFlow = result.getResultFlow();
				//schDeptFlows.add(result.getSchDeptFlow());
				SchRotation rotation=rotationBiz.readSchRotation(result.getRotationFlow());
				SchRotationDept srd=resultSchRotationDeptMap.get(resultFlow);

				double per=1;
				String baseSchMonth="";
				if(srd!=null)
				{
					baseSchMonth=srd.getSchMonth();
				}
				if(isReduction&&srd!=null)
				{
					SchDoctorDept sdd=doctorDeptMap.get(srd.getGroupFlow()+srd.getStandardDeptId());
					if(sdd!=null)
					{
						if(GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus()))
						{
							per=Double.valueOf(sdd.getSchMonth())/Double.valueOf(srd.getSchMonth());
							baseSchMonth=sdd.getSchMonth();
						}
					}
				}
				//是否翻倍
				if(rotation!=null&&GlobalConstant.FLAG_Y.equals(rotation.getIsDouble())&&StringUtil.isNotBlank(baseSchMonth))
				{
					if(StringUtil.isNotBlank(result.getSchMonth()))
					{
						per=Double.valueOf(result.getSchMonth())/Double.valueOf(baseSchMonth)*per;
					}
				}
				//要求数
				List<SchRotationDeptReq> deptReqList = rotationDeptBiz.searchStandardReqByResult(result,doctor);
				Map<String,Integer> reqNumMap = new HashMap<String, Integer>();
				Map<String,List<String>> itemMap = new HashMap<String, List<String>>();
				reqNumMap.put("reqSum",0);
				if(deptReqList!=null && deptReqList.size()>0){
					for(SchRotationDeptReq deptReq : deptReqList){
						if (srd == null || JszyTCMPracticEnum.N.getId().equals(srd.getPracticOrTheory())) {
							if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+deptReq.getRecTypeId()))
									&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())){
								countReqFuc(deptReq,per,reqNumMap,itemMap,"N");
							}
						} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(srd.getPracticOrTheory())) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + deptReq.getRecTypeId()))
									&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {
								countReqFuc(deptReq,per,reqNumMap,itemMap,"BasicPractice");
							}
						} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(srd.getPracticOrTheory())) {
							if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + deptReq.getRecTypeId()))
									&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {
								countReqFuc(deptReq,per,reqNumMap,itemMap,"TheoreticalStudy");
							}
						}
					}
				}
				String processFlow = processMap.get(resultFlow);
				String globalUpKey = result.getResultFlow()+result.getStandardGroupFlow()+result.getStandardDeptId();
				finishPerMap.put(resultFlow+"finish",defaultString(recFinishMap.get(globalUpKey)));
				finishPerMap.put(resultFlow+"finishSingle",defaultString(recFinishMap.get(processFlow)));
				finishPerMap.put(resultFlow+"req",defaultString(reqNumMap.get("reqSum")));

				if (srd == null || JszyTCMPracticEnum.N.getId().equals(srd.getPracticOrTheory())) {
					for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(resultFlow+regType.getId()+"finish",defaultString(recFinishMap.get(processFlow+regType.getId())));
							if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
								finishPerMap.put(resultFlow+regType.getId()+"req",defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(resultFlow+regType.getId()+"itemCount",defaultString(reqNumMap.get(regType.getId()+"itemCount")));
						}
					}
				} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(srd.getPracticOrTheory())) {
					for(PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(resultFlow+regType.getId()+"finish",defaultString(recFinishMap.get(processFlow+regType.getId())));
							if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
								finishPerMap.put(resultFlow+regType.getId()+"req",defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(resultFlow+regType.getId()+"itemCount",defaultString(reqNumMap.get(regType.getId()+"itemCount")));
						}
					}
				} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(srd.getPracticOrTheory())) {
					for(TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(resultFlow+regType.getId()+"finish",defaultString(recFinishMap.get(processFlow+regType.getId())));
							if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
								finishPerMap.put(resultFlow+regType.getId()+"req",defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(resultFlow+regType.getId()+"itemCount",defaultString(reqNumMap.get(regType.getId()+"itemCount")));
						}
					}

				}
				setFinishPerMap(finishPerMap,result,itemMap,recFinishMap,reqNumMap,medicineTypeId);
			}

//			List<ResRec> eRecList = searchByRec(ResRecTypeEnum.AfterEvaluation.getId(),schDeptFlows,GlobalContext.getCurrentUser().getUserFlow());
//			if(eRecList!=null && eRecList.size()>0){
//				for(ResRec eRec : eRecList){
//					Map<String,String> recContent = parseRecContent(eRec.getRecContent());
//					if(recContent!=null){
//						finishPerMap.put(eRec.getSchDeptFlow(),recContent.get("totalScore"));
//					}
//				}
//			}
		}

		List<SchRotationDept> allDeptList=new ArrayList<>();	//轮转科室
		List<SchRotationDept> deptList=null;	//轮转科室
		//PPbear
		if(StringUtil.isNotBlank(doctor.getRotationFlow())){
			deptList=rotationDeptBiz.searchSchRotationDept(doctor.getRotationFlow());
			if(deptList!=null && !deptList.isEmpty() ){
				allDeptList.addAll(deptList);
			}
		}
		//第二专业
		if(StringUtil.isNotBlank(doctor.getSecondRotationFlow())){
			deptList=rotationDeptBiz.searchSchRotationDept(doctor.getSecondRotationFlow());
			if(deptList!=null && !deptList.isEmpty() ){
				allDeptList.addAll(deptList);
			}
		}
		if(allDeptList.size()>0) {
			for(SchRotationDept dept:allDeptList) {

				String standardKey = dept.getGroupFlow() + dept.getStandardDeptId();

				SchRotation rotation = rotationBiz.readSchRotation(dept.getRotationFlow());
				double per = 1;
				String baseSchMonth = dept.getSchMonth();
				if (isReduction) {
					SchDoctorDept sdd = doctorDeptMap.get(standardKey);
					if (sdd != null) {
						if (GlobalConstant.RECORD_STATUS_Y.equals(sdd.getRecordStatus())) {
							per = Double.valueOf(sdd.getSchMonth()) / Double.valueOf(dept.getSchMonth());
							baseSchMonth = sdd.getSchMonth();
						}
					}
				}
				//是否翻倍
				if (rotation != null && GlobalConstant.FLAG_Y.equals(rotation.getIsDouble()) && StringUtil.isNotBlank(baseSchMonth)) {
					List<SchArrangeResult> results=resultMap.get(standardKey);
					if(results!=null&&results.size()>0) {
						double allMonth = 0;
						for (SchArrangeResult result : results) {
							if (StringUtil.isNotBlank(result.getSchMonth())) {
								allMonth+=Double.valueOf(result.getSchMonth());
							}
						}
						if(allMonth>0)
						{
							per = allMonth / Double.valueOf(baseSchMonth) * per;
						}
					}
				}
				//要求数
				List<SchRotationDeptReq> deptReqList = rotationDeptBiz.searchDeptReqByRel(dept.getRecordFlow());
				Map<String, Integer> reqNumMap = new HashMap<String, Integer>();
				Map<String, List<String>> itemMap = new HashMap<String, List<String>>();
				reqNumMap.put("reqSum", 0);
				if (deptReqList != null && deptReqList.size() > 0) {
					for (SchRotationDeptReq deptReq : deptReqList) {
						setStarndrdDeptReqMap(dept,deptReq,per,reqNumMap,itemMap,medicineTypeId);
					}
				}
				finishPerMap.put(standardKey + "finish", defaultString(recFinishMap.get(standardKey)));
				finishPerMap.put(standardKey + "req", defaultString(reqNumMap.get("reqSum")));

				if(JszyTCMPracticEnum.N.getId().equals(dept.getPracticOrTheory())){
					for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
							if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
								finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
						}
					}
				}else if(JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory())){
					for(PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
							if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
								finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
						}
					}
				}else if(JszyTCMPracticEnum.TheoreticalStudy.getId().equals(dept.getPracticOrTheory())){
					for(TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()){
						if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_"+regType.getId()))
								&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
							finishPerMap.put(standardKey + regType.getId() + "finish", defaultString(recFinishMap.get(standardKey + regType.getId())));
							if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
								finishPerMap.put(standardKey + regType.getId() + "req", defaultString(reqNumMap.get(regType.getId())));
							}

							finishPerMap.put(standardKey + regType.getId() + "itemCount", defaultString(reqNumMap.get(regType.getId() + "itemCount")));
						}
					}
				}


				setStarndrdDeptFinishPerMap(finishPerMap, dept, itemMap, recFinishMap, reqNumMap,medicineTypeId);
			}
		}
		return finishPerMap;
	}

	private String defaultString(Integer num){
		return num==null?"0":(num+"");
	}

	private void countFinshFuc(String recTypeId, String schDeptFlow, SchArrangeResult result,
							   Map<String, Integer> recFinishMap, String itemId, String processFlow) {
		if (recFinishMap.get(schDeptFlow) == null) {
			recFinishMap.put(schDeptFlow, 1);
		} else {
			recFinishMap.put(schDeptFlow, recFinishMap.get(schDeptFlow) + 1);
		}
		if (recFinishMap.get(schDeptFlow + recTypeId) == null) {
			recFinishMap.put(schDeptFlow + recTypeId, 1);
		} else {
			recFinishMap.put(schDeptFlow + recTypeId, recFinishMap.get(schDeptFlow + recTypeId) + 1);
		}
		if (StringUtil.isNotBlank(itemId)) {
			if (recFinishMap.get(schDeptFlow + recTypeId + itemId) == null) {
				recFinishMap.put(schDeptFlow + recTypeId + itemId, 1);
			} else {
				recFinishMap.put(schDeptFlow + recTypeId + itemId, recFinishMap.get(schDeptFlow + recTypeId + itemId) + 1);
			}
		}
		if (recFinishMap.get(processFlow) == null) {
			recFinishMap.put(processFlow, 1);
		} else {
			recFinishMap.put(processFlow, recFinishMap.get(processFlow) + 1);
		}
		if (recFinishMap.get(processFlow + recTypeId) == null) {
			recFinishMap.put(processFlow + recTypeId, 1);
		} else {
			recFinishMap.put(processFlow + recTypeId, recFinishMap.get(processFlow + recTypeId) + 1);
		}
		if (StringUtil.isNotBlank(itemId)) {
			if (recFinishMap.get(processFlow + recTypeId + itemId) == null) {
				recFinishMap.put(processFlow + recTypeId + itemId, 1);
			} else {
				recFinishMap.put(processFlow + recTypeId + itemId, recFinishMap.get(processFlow + recTypeId + itemId) + 1);
			}
		}


		if (result != null) {
			String globalUpKey = result.getResultFlow() + result.getStandardGroupFlow() + result.getStandardDeptId();
			if (recFinishMap.get(globalUpKey) == null) {
				recFinishMap.put(globalUpKey, 1);
			} else {
				recFinishMap.put(globalUpKey, recFinishMap.get(globalUpKey) + 1);
			}
			if (recFinishMap.get(globalUpKey + recTypeId) == null) {
				recFinishMap.put(globalUpKey + recTypeId, 1);
			} else {
				recFinishMap.put(globalUpKey + recTypeId, recFinishMap.get(globalUpKey + recTypeId) + 1);
			}
			if (StringUtil.isNotBlank(itemId)) {
				if (recFinishMap.get(globalUpKey + recTypeId + itemId) == null) {
					recFinishMap.put(globalUpKey + recTypeId + itemId, 1);
				} else {
					recFinishMap.put(globalUpKey + recTypeId + itemId, recFinishMap.get(globalUpKey + recTypeId + itemId) + 1);
				}
			}
			String standardKey = result.getStandardGroupFlow() + result.getStandardDeptId();
			if (recFinishMap.get(standardKey) == null) {
				recFinishMap.put(standardKey, 1);
			} else {
				recFinishMap.put(standardKey, recFinishMap.get(standardKey) + 1);
			}
			if (recFinishMap.get(standardKey + recTypeId) == null) {
				recFinishMap.put(standardKey + recTypeId, 1);
			} else {
				recFinishMap.put(standardKey + recTypeId, recFinishMap.get(standardKey + recTypeId) + 1);
			}
			if (StringUtil.isNotBlank(itemId)) {
				if (recFinishMap.get(standardKey + recTypeId + itemId) == null) {
					recFinishMap.put(standardKey + recTypeId + itemId, 1);
				} else {
					recFinishMap.put(standardKey + recTypeId + itemId, recFinishMap.get(standardKey + recTypeId + itemId) + 1);
				}
			}
		}
	}
	private void countReqFuc(SchRotationDeptReq deptReq, double per, Map<String,Integer> reqNumMap,Map<String,List<String>> itemMap,String typeId) {
		int num= new BigDecimal(Math.ceil(deptReq.getReqNum().intValue()*per)).intValue();
		reqNumMap.put("reqSum",reqNumMap.get("reqSum")+(num));
		if(reqNumMap.get(deptReq.getRecTypeId())==null){
			reqNumMap.put(deptReq.getRecTypeId(),(num));
		}else{
			reqNumMap.put(deptReq.getRecTypeId(),reqNumMap.get(deptReq.getRecTypeId())+(num));
		}
		if(reqNumMap.get(deptReq.getRecTypeId()+"itemCount")==null){
			reqNumMap.put(deptReq.getRecTypeId()+"itemCount",1);
		}else{
			reqNumMap.put(deptReq.getRecTypeId()+"itemCount",reqNumMap.get(deptReq.getRecTypeId()+"itemCount")+1);
		}
		if(reqNumMap.get(deptReq.getRecTypeId()+deptReq.getItemId())==null){
			reqNumMap.put(deptReq.getRecTypeId()+deptReq.getItemId(),num);
		}else{
			reqNumMap.put(deptReq.getRecTypeId()+deptReq.getItemId(), reqNumMap.get(deptReq.getRecTypeId()+deptReq.getItemId())+(num));
		}
		if (JszyTCMPracticEnum.N.getId().equals(typeId)) {
			RegistryTypeEnum rectype=RegistryTypeEnum.valueOf(deptReq.getRecTypeId());
			if(rectype!=null&&rectype.getHaveItem().equals(GlobalConstant.FLAG_Y))
			{
				if(StringUtil.isNotBlank(deptReq.getItemId())){
					List<String> itemIds = itemMap.get(deptReq.getRecTypeId());
					if(itemIds==null){
						itemIds = new ArrayList<String>();
						itemMap.put(deptReq.getRecTypeId(),itemIds);
					}
					itemIds.add(deptReq.getItemId());
				}
			}
		} else if (JszyTCMPracticEnum.BasicPractice.getId().equals(typeId)) {
			PracticRegistryTypeEnum rectype=PracticRegistryTypeEnum.valueOf(deptReq.getRecTypeId());
			if(rectype!=null&&rectype.getHaveItem().equals(GlobalConstant.FLAG_Y))
			{
				if(StringUtil.isNotBlank(deptReq.getItemId())){
					List<String> itemIds = itemMap.get(deptReq.getRecTypeId());
					if(itemIds==null){
						itemIds = new ArrayList<String>();
						itemMap.put(deptReq.getRecTypeId(),itemIds);
					}
					itemIds.add(deptReq.getItemId());
				}
			}
		} else if (JszyTCMPracticEnum.TheoreticalStudy.getId().equals(typeId)) {
			TheoreticalRegistryTypeEnum rectype=TheoreticalRegistryTypeEnum.valueOf(deptReq.getRecTypeId());
			if(rectype!=null&&rectype.getHaveItem().equals(GlobalConstant.FLAG_Y))
			{
				if(StringUtil.isNotBlank(deptReq.getItemId())){
					List<String> itemIds = itemMap.get(deptReq.getRecTypeId());
					if(itemIds==null){
						itemIds = new ArrayList<String>();
						itemMap.put(deptReq.getRecTypeId(),itemIds);
					}
					itemIds.add(deptReq.getItemId());
				}
			}
		}


	}

	private void setStarndrdDeptReqMap(SchRotationDept dept, SchRotationDeptReq deptReq, double per, Map<String, Integer> reqNumMap, Map<String, List<String>> itemMap, String medicineTypeId){
		if(JszyTCMPracticEnum.N.getId().equals(dept.getPracticOrTheory())){
			if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + deptReq.getRecTypeId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {
				int num = new BigDecimal(Math.ceil(deptReq.getReqNum().intValue() * per)).intValue();
				reqNumMap.put("reqSum", reqNumMap.get("reqSum") + (num));
				if (reqNumMap.get(deptReq.getRecTypeId()) == null) {
					reqNumMap.put(deptReq.getRecTypeId(), (num));
				} else {
					reqNumMap.put(deptReq.getRecTypeId(), reqNumMap.get(deptReq.getRecTypeId()) + (num));
				}
				if (reqNumMap.get(deptReq.getRecTypeId() + "itemCount") == null) {
					reqNumMap.put(deptReq.getRecTypeId() + "itemCount", 1);
				} else {
					reqNumMap.put(deptReq.getRecTypeId() + "itemCount", reqNumMap.get(deptReq.getRecTypeId() + "itemCount") + 1);
				}
				if (reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) == null) {
					reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), num);
				} else {
					reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) + (num));
				}
				RegistryTypeEnum rectype = RegistryTypeEnum.valueOf(deptReq.getRecTypeId());
				if (rectype != null && rectype.getHaveItem().equals(GlobalConstant.FLAG_Y)) {
					if (StringUtil.isNotBlank(deptReq.getItemId())) {
						List<String> itemIds = itemMap.get(deptReq.getRecTypeId());
						if (itemIds == null) {
							itemIds = new ArrayList<String>();
							itemMap.put(deptReq.getRecTypeId(), itemIds);
						}
						itemIds.add(deptReq.getItemId());
					}
				}
			}
		}
		if(JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory())){
			if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + deptReq.getRecTypeId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {

				int num = new BigDecimal(Math.ceil(deptReq.getReqNum().intValue() * per)).intValue();
				reqNumMap.put("reqSum", reqNumMap.get("reqSum") + (num));
				if (reqNumMap.get(deptReq.getRecTypeId()) == null) {
					reqNumMap.put(deptReq.getRecTypeId(), (num));
				} else {
					reqNumMap.put(deptReq.getRecTypeId(), reqNumMap.get(deptReq.getRecTypeId()) + (num));
				}
				if (reqNumMap.get(deptReq.getRecTypeId() + "itemCount") == null) {
					reqNumMap.put(deptReq.getRecTypeId() + "itemCount", 1);
				} else {
					reqNumMap.put(deptReq.getRecTypeId() + "itemCount", reqNumMap.get(deptReq.getRecTypeId() + "itemCount") + 1);
				}
				if (reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) == null) {
					reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), num);
				} else {
					reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) + (num));
				}
				PracticRegistryTypeEnum rectype = PracticRegistryTypeEnum.valueOf(deptReq.getRecTypeId());
				if (rectype != null && rectype.getHaveItem().equals(GlobalConstant.FLAG_Y)) {
					if (StringUtil.isNotBlank(deptReq.getItemId())) {
						List<String> itemIds = itemMap.get(deptReq.getRecTypeId());
						if (itemIds == null) {
							itemIds = new ArrayList<String>();
							itemMap.put(deptReq.getRecTypeId(), itemIds);
						}
						itemIds.add(deptReq.getItemId());
					}
				}
			}
		}
		if(JszyTCMPracticEnum.TheoreticalStudy.getId().equals(dept.getPracticOrTheory())){
			if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + deptReq.getRecTypeId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,deptReq.getRecTypeId())) {

				int num = new BigDecimal(Math.ceil(deptReq.getReqNum().intValue() * per)).intValue();
				reqNumMap.put("reqSum", reqNumMap.get("reqSum") + (num));
				if (reqNumMap.get(deptReq.getRecTypeId()) == null) {
					reqNumMap.put(deptReq.getRecTypeId(), (num));
				} else {
					reqNumMap.put(deptReq.getRecTypeId(), reqNumMap.get(deptReq.getRecTypeId()) + (num));
				}
				if (reqNumMap.get(deptReq.getRecTypeId() + "itemCount") == null) {
					reqNumMap.put(deptReq.getRecTypeId() + "itemCount", 1);
				} else {
					reqNumMap.put(deptReq.getRecTypeId() + "itemCount", reqNumMap.get(deptReq.getRecTypeId() + "itemCount") + 1);
				}
				if (reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) == null) {
					reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), num);
				} else {
					reqNumMap.put(deptReq.getRecTypeId() + deptReq.getItemId(), reqNumMap.get(deptReq.getRecTypeId() + deptReq.getItemId()) + (num));
				}
				TheoreticalRegistryTypeEnum rectype = TheoreticalRegistryTypeEnum.valueOf(deptReq.getRecTypeId());
				if (rectype != null && rectype.getHaveItem().equals(GlobalConstant.FLAG_Y)) {
					if (StringUtil.isNotBlank(deptReq.getItemId())) {
						List<String> itemIds = itemMap.get(deptReq.getRecTypeId());
						if (itemIds == null) {
							itemIds = new ArrayList<String>();
							itemMap.put(deptReq.getRecTypeId(), itemIds);
						}
						itemIds.add(deptReq.getItemId());
					}
				}
			}
		}
	}
	private void setFinishPerMap(Map<String,String> finishPerMap,SchArrangeResult result,Map<String,List<String>> itemMap,Map<String,Integer> recFinishMap,Map<String,Integer> reqNumMap,String medicineTypeId) {
		if (finishPerMap != null) {
			String resultFlow = result.getResultFlow();

			String standardGroupFlow = result.getStandardGroupFlow();
			String standardDeptId = result.getStandardDeptId();

			String globalUpKey = result.getResultFlow() + standardGroupFlow + standardDeptId;

			Float deptReq = getValDefaultZero(finishPerMap, resultFlow + "req");

			Float deptFin = getValDefaultZero(finishPerMap, resultFlow + "finish");

//			String reqSum = finishPerMap.get(resultFlow+"req");

			Float deptPer = 0f;

//			Float count = 0f;
			for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
				if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))
						&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())) {
					if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
//						Float reqCount = 0f;
//						Float itemInReqCount = 0f;

						String recTypeId = regType.getId();

						Float typePer = 0f;

						boolean isFinish = true;

						Float typeFin = getValDefaultZero(recFinishMap, globalUpKey + recTypeId);

						Float typeReq = getValDefaultZero(reqNumMap, recTypeId);

						Float typeReqPer = getPer(typeReq, deptReq);

						List<String> itemList = null;

						if (itemMap != null && itemMap.size() > 0) {
							itemList = itemMap.get(recTypeId);
						}

						if (isFinish = itemList != null && itemList.size() > 0) {
//							String req = finishPerMap.get(resultFlow+recTypeId+"req");
							Float allItemReq = 0f;
							Float allItemFin = 0f;
							for (String itemId : itemList) {

								Float itemFin = getValDefaultZero(recFinishMap, globalUpKey + recTypeId + itemId);

								Float itemReq = getValDefaultZero(reqNumMap, recTypeId + itemId);
								allItemFin += itemFin;
								allItemReq += itemReq;

								finishPerMap.put(resultFlow + recTypeId + itemId + "req", itemReq + "");
								finishPerMap.put(resultFlow + recTypeId + itemId + "finish", itemFin + "");
//								String itemFinish = defaultString(recFinishMap.get(globalUpKey+recTypeId+itemId));
//								String itemReq = defaultString(reqNumMap.get(recTypeId+itemId));

								Float itemReqPre = getPer(itemReq, typeReq);

								Float itemPre = getPer(itemFin, itemReq);

								if (itemReq == 0 && itemFin > 0) {
									itemPre = 1f;
								}

								BigDecimal b = new BigDecimal(itemPre * 100).setScale(0, BigDecimal.ROUND_HALF_UP);
								finishPerMap.put(resultFlow + recTypeId + itemId, b.toString());

								typePer += (itemPre * itemReqPre);
								if (isFinish) {
									isFinish = (itemReq - itemFin) <= 0;
								}
							}
							//所有子项的都没有要求，并且只要有一个子项完成了 那么完成的百分比就是100%
							if (allItemReq == 0) {
								typePer = 1f;
							}
						} else {
							typePer = getPer(typeFin, typeReq);

							if (typeReq == 0) {
								typePer = 1f;
							}

						}
						finishPerMap.put(resultFlow + recTypeId + "isFinish", isFinish + "");

						BigDecimal b = new BigDecimal(typePer * 100).setScale(0, BigDecimal.ROUND_HALF_UP);
						finishPerMap.put(resultFlow + recTypeId, b.toString());

						deptPer += (typeReqPer * typePer);
					}
				}
			}
			for (PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()) {
				if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_" + regType.getId()))
						&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())) {
					if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
//						Float reqCount = 0f;
//						Float itemInReqCount = 0f;

						String recTypeId = regType.getId();

						Float typePer = 0f;

						boolean isFinish = true;

						Float typeFin = getValDefaultZero(recFinishMap, globalUpKey + recTypeId);

						Float typeReq = getValDefaultZero(reqNumMap, recTypeId);

						Float typeReqPer = getPer(typeReq, deptReq);

						List<String> itemList = null;

						if (itemMap != null && itemMap.size() > 0) {
							itemList = itemMap.get(recTypeId);
						}

						if (isFinish = itemList != null && itemList.size() > 0) {
//							String req = finishPerMap.get(resultFlow+recTypeId+"req");
							Float allItemReq = 0f;
							Float allItemFin = 0f;
							for (String itemId : itemList) {

								Float itemFin = getValDefaultZero(recFinishMap, globalUpKey + recTypeId + itemId);

								Float itemReq = getValDefaultZero(reqNumMap, recTypeId + itemId);
								allItemFin += itemFin;
								allItemReq += itemReq;

								finishPerMap.put(resultFlow + recTypeId + itemId + "req", itemReq + "");
								finishPerMap.put(resultFlow + recTypeId + itemId + "finish", itemFin + "");
//								String itemFinish = defaultString(recFinishMap.get(globalUpKey+recTypeId+itemId));
//								String itemReq = defaultString(reqNumMap.get(recTypeId+itemId));

								Float itemReqPre = getPer(itemReq, typeReq);

								Float itemPre = getPer(itemFin, itemReq);

								if (itemReq == 0 && itemFin > 0) {
									itemPre = 1f;
								}

								BigDecimal b = new BigDecimal(itemPre * 100).setScale(0, BigDecimal.ROUND_HALF_UP);
								finishPerMap.put(resultFlow + recTypeId + itemId, b.toString());

								typePer += (itemPre * itemReqPre);
								if (isFinish) {
									isFinish = (itemReq - itemFin) <= 0;
								}
							}
							//所有子项的都没有要求，并且只要有一个子项完成了 那么完成的百分比就是100%
							if (allItemReq == 0) {
								typePer = 1f;
							}
						} else {
							typePer = getPer(typeFin, typeReq);

							if (typeReq == 0) {
								typePer = 1f;
							}

						}
						finishPerMap.put(resultFlow + recTypeId + "isFinish", isFinish + "");

						BigDecimal b = new BigDecimal(typePer * 100).setScale(0, BigDecimal.ROUND_HALF_UP);
						finishPerMap.put(resultFlow + recTypeId, b.toString());

						deptPer += (typeReqPer * typePer);
					}
				}
			}
			for (TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()) {
				if (GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_" + regType.getId()))
						&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())) {
					if (GlobalConstant.FLAG_Y.equals(regType.getHaveReq())) {
//						Float reqCount = 0f;
//						Float itemInReqCount = 0f;

						String recTypeId = regType.getId();

						Float typePer = 0f;

						boolean isFinish = true;

						Float typeFin = getValDefaultZero(recFinishMap, globalUpKey + recTypeId);

						Float typeReq = getValDefaultZero(reqNumMap, recTypeId);

						Float typeReqPer = getPer(typeReq, deptReq);

						List<String> itemList = null;

						if (itemMap != null && itemMap.size() > 0) {
							itemList = itemMap.get(recTypeId);
						}

						if (isFinish = itemList != null && itemList.size() > 0) {
//							String req = finishPerMap.get(resultFlow+recTypeId+"req");
							Float allItemReq = 0f;
							Float allItemFin = 0f;
							for (String itemId : itemList) {

								Float itemFin = getValDefaultZero(recFinishMap, globalUpKey + recTypeId + itemId);

								Float itemReq = getValDefaultZero(reqNumMap, recTypeId + itemId);
								allItemFin += itemFin;
								allItemReq += itemReq;

								finishPerMap.put(resultFlow + recTypeId + itemId + "req", itemReq + "");
								finishPerMap.put(resultFlow + recTypeId + itemId + "finish", itemFin + "");
//								String itemFinish = defaultString(recFinishMap.get(globalUpKey+recTypeId+itemId));
//								String itemReq = defaultString(reqNumMap.get(recTypeId+itemId));

								Float itemReqPre = getPer(itemReq, typeReq);

								Float itemPre = getPer(itemFin, itemReq);

								if (itemReq == 0 && itemFin > 0) {
									itemPre = 1f;
								}

								BigDecimal b = new BigDecimal(itemPre * 100).setScale(0, BigDecimal.ROUND_HALF_UP);
								finishPerMap.put(resultFlow + recTypeId + itemId, b.toString());

								typePer += (itemPre * itemReqPre);
								if (isFinish) {
									isFinish = (itemReq - itemFin) <= 0;
								}
							}
							//所有子项的都没有要求，并且只要有一个子项完成了 那么完成的百分比就是100%
							if (allItemReq == 0) {
								typePer = 1f;
							}
						} else {
							typePer = getPer(typeFin, typeReq);

							if (typeReq == 0) {
								typePer = 1f;
							}

						}
						finishPerMap.put(resultFlow + recTypeId + "isFinish", isFinish + "");

						BigDecimal b = new BigDecimal(typePer * 100).setScale(0, BigDecimal.ROUND_HALF_UP);
						finishPerMap.put(resultFlow + recTypeId, b.toString());

						deptPer += (typeReqPer * typePer);
					}
				}
			}

			if (deptReq == 0) {
				deptPer = 1f;
			}

			BigDecimal b = new BigDecimal(deptPer * 100).setScale(0, BigDecimal.ROUND_HALF_UP);
			finishPerMap.put(resultFlow, b.toString());
		}
	}

	private void setStarndrdDeptFinishPerMap(Map<String,String> finishPerMap,SchRotationDept dept,Map<String,List<String>> itemMap,Map<String,Integer> recFinishMap,Map<String,Integer> reqNumMap,String medicineTypeId){

		String standardGroupFlow = dept.getGroupFlow();
		String standardDeptId = dept.getStandardDeptId();
		//标准科室完成百分比
		String standardKey = standardGroupFlow+standardDeptId;

		if(finishPerMap!=null&&reqNumMap!=null){
			Float deptReq = getValDefaultZero(finishPerMap,standardKey+"req");

			Float deptFin = getValDefaultZero(finishPerMap,standardKey+"finish");

			Float deptPer = 0f;
			//轮转记录完成百分比
			for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
				if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
						&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
					if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
						String recTypeId = regType.getId();

						Float typePer = 0f;

						boolean isFinish = true;

						Float typeFin = getValDefaultZero(recFinishMap,standardKey+recTypeId);

						Float typeReq = getValDefaultZero(reqNumMap,recTypeId);

						Float typeReqPer = getPer(typeReq,deptReq);

						List<String> itemList = null;

						if(itemMap!=null && itemMap.size()>0){
							itemList = itemMap.get(recTypeId);
						}

						if(isFinish = itemList!=null && itemList.size()>0){
//							String req = finishPerMap.get(resultFlow+recTypeId+"req");
							Float allItemReq=0f;
							Float allItemFin=0f;
							for(String itemId : itemList){

								Float itemFin = getValDefaultZero(recFinishMap,standardKey+recTypeId+itemId);

								Float itemReq = getValDefaultZero(reqNumMap,recTypeId+itemId);
								allItemFin+=itemFin;
								allItemReq+=itemReq;

								finishPerMap.put(standardKey+recTypeId+itemId+"req",itemReq+"");
								finishPerMap.put(standardKey+recTypeId+itemId+"finish",itemFin+"");
//								String itemFinish = defaultString(recFinishMap.get(globalUpKey+recTypeId+itemId));
//								String itemReq = defaultString(reqNumMap.get(recTypeId+itemId));

								Float itemReqPre = getPer(itemReq,typeReq);

								Float itemPre = getPer(itemFin,itemReq);

								if(itemReq==0 && itemFin>0){
									itemPre = 1f;
								}

								BigDecimal b = new BigDecimal(itemPre*100).setScale(0,BigDecimal.ROUND_HALF_UP);
								finishPerMap.put(standardKey+recTypeId+itemId,b.toString());

								typePer+=(itemPre*itemReqPre);
								if(isFinish){
									isFinish = (itemReq-itemFin)<=0;
								}
							}
							//所有子项的都没有要求，并且只要有一个子项完成了 那么完成的百分比就是100%
							if(allItemReq==0)
							{
								typePer = 1f;
							}
						}else{
							typePer = getPer(typeFin,typeReq);

							if(typeReq==0){
								typePer = 1f;
							}

						}
						finishPerMap.put(standardKey+recTypeId+"isFinish",isFinish+"");

						BigDecimal b=new BigDecimal(typePer*100).setScale(0, BigDecimal.ROUND_HALF_UP);
						finishPerMap.put(standardKey+recTypeId,b.toString());

						deptPer+=(typeReqPer*typePer);
					}
				}
			}

			for(PracticRegistryTypeEnum regType : PracticRegistryTypeEnum.values()){
				if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("practic_registry_type_"+regType.getId()))){
					if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
						String recTypeId = regType.getId();

						Float typePer = 0f;

						boolean isFinish = true;

						Float typeFin = getValDefaultZero(recFinishMap,standardKey+recTypeId);

						Float typeReq = getValDefaultZero(reqNumMap,recTypeId);

						Float typeReqPer = getPer(typeReq,deptReq);

						List<String> itemList = null;

						if(itemMap!=null && itemMap.size()>0){
							itemList = itemMap.get(recTypeId);
						}

						if(isFinish = itemList!=null && itemList.size()>0){
//							String req = finishPerMap.get(resultFlow+recTypeId+"req");
							Float allItemReq=0f;
							Float allItemFin=0f;
							for(String itemId : itemList){

								Float itemFin = getValDefaultZero(recFinishMap,standardKey+recTypeId+itemId);

								Float itemReq = getValDefaultZero(reqNumMap,recTypeId+itemId);
								allItemFin+=itemFin;
								allItemReq+=itemReq;

								finishPerMap.put(standardKey+recTypeId+itemId+"req",itemReq+"");
								finishPerMap.put(standardKey+recTypeId+itemId+"finish",itemFin+"");
//								String itemFinish = defaultString(recFinishMap.get(globalUpKey+recTypeId+itemId));
//								String itemReq = defaultString(reqNumMap.get(recTypeId+itemId));

								Float itemReqPre = getPer(itemReq,typeReq);

								Float itemPre = getPer(itemFin,itemReq);

								if(itemReq==0 && itemFin>0){
									itemPre = 1f;
								}

								BigDecimal b = new BigDecimal(itemPre*100).setScale(0,BigDecimal.ROUND_HALF_UP);
								finishPerMap.put(standardKey+recTypeId+itemId,b.toString());

								typePer+=(itemPre*itemReqPre);
								if(isFinish){
									isFinish = (itemReq-itemFin)<=0;
								}
							}
							//所有子项的都没有要求，并且只要有一个子项完成了 那么完成的百分比就是100%
							if(allItemReq==0)
							{
								typePer = 1f;
							}
						}else{
							typePer = getPer(typeFin,typeReq);

							if(typeReq==0){
								typePer = 1f;
							}

						}
						finishPerMap.put(standardKey+recTypeId+"isFinish",isFinish+"");

						BigDecimal b=new BigDecimal(typePer*100).setScale(0, BigDecimal.ROUND_HALF_UP);
						finishPerMap.put(standardKey+recTypeId,b.toString());

						deptPer+=(typeReqPer*typePer);
					}
				}
			}
			for(TheoreticalRegistryTypeEnum regType : TheoreticalRegistryTypeEnum.values()){
				if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("theoretical_registry_type_"+regType.getId()))){
					if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
						String recTypeId = regType.getId();

						Float typePer = 0f;

						boolean isFinish = true;

						Float typeFin = getValDefaultZero(recFinishMap,standardKey+recTypeId);

						Float typeReq = getValDefaultZero(reqNumMap,recTypeId);

						Float typeReqPer = getPer(typeReq,deptReq);

						List<String> itemList = null;

						if(itemMap!=null && itemMap.size()>0){
							itemList = itemMap.get(recTypeId);
						}

						if(isFinish = itemList!=null && itemList.size()>0){
//							String req = finishPerMap.get(resultFlow+recTypeId+"req");
							Float allItemReq=0f;
							Float allItemFin=0f;
							for(String itemId : itemList){

								Float itemFin = getValDefaultZero(recFinishMap,standardKey+recTypeId+itemId);

								Float itemReq = getValDefaultZero(reqNumMap,recTypeId+itemId);
								allItemFin+=itemFin;
								allItemReq+=itemReq;

								finishPerMap.put(standardKey+recTypeId+itemId+"req",itemReq+"");
								finishPerMap.put(standardKey+recTypeId+itemId+"finish",itemFin+"");
//								String itemFinish = defaultString(recFinishMap.get(globalUpKey+recTypeId+itemId));
//								String itemReq = defaultString(reqNumMap.get(recTypeId+itemId));

								Float itemReqPre = getPer(itemReq,typeReq);

								Float itemPre = getPer(itemFin,itemReq);

								if(itemReq==0 && itemFin>0){
									itemPre = 1f;
								}

								BigDecimal b = new BigDecimal(itemPre*100).setScale(0,BigDecimal.ROUND_HALF_UP);
								finishPerMap.put(standardKey+recTypeId+itemId,b.toString());

								typePer+=(itemPre*itemReqPre);
								if(isFinish){
									isFinish = (itemReq-itemFin)<=0;
								}
							}
							//所有子项的都没有要求，并且只要有一个子项完成了 那么完成的百分比就是100%
							if(allItemReq==0)
							{
								typePer = 1f;
							}
						}else{
							typePer = getPer(typeFin,typeReq);

							if(typeReq==0){
								typePer = 1f;
							}

						}
						finishPerMap.put(standardKey+recTypeId+"isFinish",isFinish+"");

						BigDecimal b=new BigDecimal(typePer*100).setScale(0, BigDecimal.ROUND_HALF_UP);
						finishPerMap.put(standardKey+recTypeId,b.toString());

						deptPer+=(typeReqPer*typePer);
					}
				}
			}
			if(deptReq==0){
				deptPer=1f;
			}
			BigDecimal b=new BigDecimal(deptPer*100).setScale(0, BigDecimal.ROUND_HALF_UP);
			finishPerMap.put(standardKey,b.toString());
		}
	}

	public static void main(String[] args) {
		double b=Math.ceil(0.1);
		System.out.println(b);
	}
	private <T> Float getValDefaultZero(Map<String,T> dataMap,String key){
		Float result = 0f;
		if(dataMap!=null){
			T t = dataMap.get(key);
			if(t!=null){
				String ts = t.toString();
				if(StringUtil.isNotBlank(ts)){
					result = Float.valueOf(ts);
				}
			}
		}
		return result;
	}

	private Float getPer(float a,float b){
		float result = 0f;
		if(a!=0 && b!=0){
			result = a/b;
			if (result > 1) {
				result = 1f;
			}
		}
		return result;
	}

	@Override
	public List<ResRec> searchByProcessFlowAndRecTypeId(String processFlow,String recTypeId){
		ResRecExample example = new ResRecExample();
		if(StringUtil.isNotBlank(processFlow)&&StringUtil.isNotBlank(recTypeId)){
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
		}
		return resRecMapper.selectByExample(example);
	}
	@Override
	public List<ResRec> searchByProcessFlowAndRecTypeIdClob(String processFlow,String recTypeId){
//		ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//		ResRecCampaignRegistryExample.Criteria campaignRegistryCriteria = campaignRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//		ResRecCaseRegistryExample.Criteria caseRegistryCriteria = caseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//		ResRecDiseaseRegistryExample.Criteria diseaseRegistryCriteria = diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//		ResRecLanguageRegistryExample.Criteria languageRegistryCriteria = languageRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//		ResRecOperationRegistryExample.Criteria operationRegistryCriteria = operationRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//		ResRecSkillRegistryExample.Criteria skillRegistryCriteria = skillRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		ResRecExample example = new ResRecExample();
		if(StringUtil.isNotBlank(processFlow)&&StringUtil.isNotBlank(recTypeId)){
//			campaignRegistryCriteria.andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
//			caseRegistryCriteria.andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
//			diseaseRegistryCriteria.andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
//			languageRegistryCriteria.andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
//			operationRegistryCriteria.andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
//			skillRegistryCriteria.andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
		}
//		List<ResRec> recList;
//		List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExampleWithBLOBs(campaignRegistryExample);
//		recList = campaignRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList());
//		List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExampleWithBLOBs(caseRegistryExample);
//		recList.addAll(caseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExampleWithBLOBs(diseaseRegistryExample);
//		recList.addAll(diseaseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExampleWithBLOBs(languageRegistryExample);
//		recList.addAll(languageRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExampleWithBLOBs(operationRegistryExample);
//		recList.addAll(operationRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExampleWithBLOBs(skillRegistryExample);
//		recList.addAll(skillRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		return recList;
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchByRecAndProcess(String recTypeId, String operUserFlow, String processFlow) {
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId).andOperUserFlowEqualTo(operUserFlow).andProcessFlowEqualTo(processFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

//	@Override
//	public List<ResAppeal> searchAppeal(ResAppeal appeal){
//		ResAppealExample example = new ResAppealExample();
//		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//
//		createAppealCriteria(criteria,appeal);
//
//		example.setOrderByClause("OPER_TIME");
//
//		return resAppealMapper.selectByExample(example);
//	}

	/******************************************************
	 * 获取百分比和数量
	 **************************************************************/

//	@Override
//	public int saveRecContentForm(String recFlow, String recTypeId,String roleFlag, String processFlow , Object form) {
//		if(StringUtil.isNotBlank(recFlow)&&StringUtil.isNotBlank(recTypeId)&&form!=null){
//			ResRec rec = this.readResRec(recFlow);
//			if(rec!=null){
//				Document dom = null;
//				try {
//					dom = DocumentHelper.parseText(rec.getRecContent());
//				} catch (DocumentException e) {
//					e.printStackTrace();
//					throw new RuntimeException("resRec Xml转换成dom出错，resTypeId："+recTypeId+"，recFlow："+recFlow);
//				}
//				Element root = dom.getRootElement();
//				if(ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId)){//出科考核表
//
//					}else{
//						ResAfterSummaryForm myForm = (ResAfterSummaryForm) form;
//						String userName = GlobalContext.getCurrentUser().getUserName();
//						String userFlow = GlobalContext.getCurrentUser().getUserFlow();
//						String auditResult = myForm.getAuditResult();
//						if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)){//带教老师
//							Element deptAppraiseEl = root.element("deptAppraise");
//							if(deptAppraiseEl==null){
//								deptAppraiseEl = root.addElement("deptAppraise");
//							}
//							deptAppraiseEl.setText(myForm.getDeptAppraise());
//							rec.setAuditTime(DateUtil.getCurrDateTime());
//							rec.setAuditUserFlow(userFlow);
//							rec.setAuditUserName(userName);
//							rec.setAuditStatusId(auditResult);
//							rec.setAuditStatusName(RecStatusEnum.getNameById(auditResult));
////							if(RecStatusEnum.TeacherAuditN.getId().equals(auditResult)){
////								rec.setStatusId(RecStatusEnum.Edit.getId());
////								rec.setStatusName(RecStatusEnum.Edit.getName());
////							}
//						}else if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)){//科主任
//							Element deptHeadAutograthEl = root.element("deptHeadAutograth");
//							if(deptHeadAutograthEl==null){
//								deptHeadAutograthEl = root.addElement("deptHeadAutograth");
//							}
//							deptHeadAutograthEl.setText(myForm.getDeptHeadAutograth());
//							if(StringUtil.isNotBlank(myForm.getIsAgree())){
//								Element isAgreeEl = root.element("isAgree");
//								if(isAgreeEl==null){
//									isAgreeEl = root.addElement("isAgree");
//								}
//								/*修改process出科标记*/
//								isAgreeEl.setText(myForm.getIsAgree());
//								if(GlobalConstant.FLAG_Y.equals(myForm.getIsAgree())){
//									ResDoctorSchProcess process = this.resDoctorProcessMapper.selectByPrimaryKey(processFlow);
//									if(process!=null){
//										process.setSchFlag(GlobalConstant.FLAG_Y);
//										process.setIsCurrentFlag(GlobalConstant.FLAG_N);
//										process.setEndDate(DateUtil.getCurrDate());
//										this.resDoctorProcessMapper.updateByPrimaryKeySelective(process);
//									}
//								}
//							}
//							rec.setHeadAuditStatusId(auditResult);
//							rec.setHeadAuditStatusName(RecStatusEnum.getNameById(auditResult));
//							rec.setHeadAuditTime(DateUtil.getCurrDateTime());
//							rec.setHeadAuditUserFlow(userFlow);
//							rec.setHeadAuditUserName(userName);
////							if(RecStatusEnum.HeadAuditN.getId().equals(auditResult)){
////								rec.setStatusId(RecStatusEnum.Edit.getId());
////								rec.setStatusName(RecStatusEnum.Edit.getName());
////							}
//					}
//				}
//				rec.setRecContent(root.asXML());
//				return this.edit(rec);
//			}
//		}
//		return GlobalConstant.ZERO_LINE;
//	}

	@Override
	public List<ResRec> searchResRec(List<String> schDeptFlows,ResRec rec){
		ResRecExample example = new ResRecExample();
		com.pinde.sci.model.mo.ResRecExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSchDeptFlowIn(schDeptFlows);
		if(rec!=null){
			if(StringUtil.isNotBlank(rec.getSchDeptName())){
				criteria.andSchDeptNameLike("%"+rec.getSchDeptName()+"%");
			}
			if(StringUtil.isNotBlank(rec.getItemName())){
				criteria.andItemNameLike("%"+rec.getItemName()+"%");
			}
			if(StringUtil.isNotBlank(rec.getRecTypeId())){
				criteria.andRecTypeIdEqualTo(rec.getRecTypeId());
			}
			if(StringUtil.isNotBlank(rec.getAuditStatusId())){
				String auditStatusId = rec.getAuditStatusId();
				if(RecStatusEnum.TeacherAuditY.getId().equals(auditStatusId)){
					criteria.andAuditStatusIdIsNotNull();
				}
				if(RecStatusEnum.TeacherAuditN.getId().equals(auditStatusId)){
					criteria.andAuditStatusIdIsNull();
				}
			}
			if(StringUtil.isNotBlank(rec.getHeadAuditStatusId())){
				String headAuditStatusId = rec.getHeadAuditStatusId();
				if(RecStatusEnum.HeadAuditY.getId().equals(headAuditStatusId)){
					criteria.andHeadAuditStatusIdIsNotNull();
				}
				if(RecStatusEnum.HeadAuditN.getId().equals(headAuditStatusId)){
					criteria.andHeadAuditStatusIdIsNull();
				}
			}
			if(StringUtil.isNotBlank(rec.getOperUserName())){
				criteria.andOperUserNameLike("%"+rec.getOperUserName()+"%");
			}
//			if(StringUtil.isNotBlank(rec.getStatusId())){
//				criteria.andStatusIdEqualTo(rec.getStatusId());
//			}
		}
		example.setOrderByClause("OPER_TIME");
		if(StringUtil.isNotBlank(rec.getRecTypeId()) && (ResRecTypeEnum.AfterEvaluation.getId().equals(rec.getRecTypeId()))){
			return resRecMapper.selectByExampleWithBLOBs(example);
		}
		return resRecMapper.selectByExample(example);
	}

	@Override
	public List<Map<String,Object>> countRecWithDoc(List<String> userFlows,List<String> processFlows) {
		return countRecWithDoc(userFlows, processFlows, null);
	}

//	@Override
//	public ResAppeal readAppeal(String appealFlow){
//		return resAppealMapper.selectByPrimaryKey(appealFlow);
//	}

	@Override
	public List<Map<String,Object>> countRecWithDoc(List<String> userFlows,List<String> processFlows,String isAudit){
		return resRecExtMapper.countRecWithDoc(userFlows, processFlows,isAudit);
	}

	/**********************************************申述*********************************************/

	@Override
	public int editAppeal(ResAppeal appeal){
		if(appeal!=null){
			if(StringUtil.isNotBlank(appeal.getAppealFlow())){
				GeneralMethod.setRecordInfo(appeal, false);
				return resAppealMapper.updateByPrimaryKeySelective(appeal);
			}else{
				appeal.setAppealFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(appeal, true);
				return resAppealMapper.insertSelective(appeal);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<ResAppeal> searchAppeal(String recTypeId,String schDeptFlow,String operUserFlow,String processFlow){
		ResAppealExample example = new ResAppealExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andSchDeptFlowEqualTo(schDeptFlow).andOperUserFlowEqualTo(operUserFlow).andProcessFlowEqualTo(processFlow);
		example.setOrderByClause("AUDIT_STATUS_ID ,OPER_TIME");
		return resAppealMapper.selectByExample(example);
	}

	@Override
	public List<ResAppeal> searchAppeal(ResAppeal appeal,String startTime,String endTime){
		ResAppealExample example = new ResAppealExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

		createAppealCriteria(criteria,appeal);

		if(StringUtil.isNotBlank(startTime)){
			criteria.andOperTimeGreaterThanOrEqualTo(startTime);
		}

		if(StringUtil.isNotBlank(endTime)){
			criteria.andOperTimeLessThanOrEqualTo(endTime);
		}

		example.setOrderByClause("OPER_TIME");

		return resAppealMapper.selectByExample(example);
	}

	private Criteria createAppealCriteria(Criteria criteria,ResAppeal appeal){
		if(appeal!=null){
			if(StringUtil.isNotBlank(appeal.getOperUserFlow())){
				criteria.andOperUserFlowEqualTo(appeal.getOperUserFlow());
			}
			if(StringUtil.isNotBlank(appeal.getRecTypeId())){
				criteria.andRecTypeIdEqualTo(appeal.getRecTypeId());
			}
			if(StringUtil.isNotBlank(appeal.getItemName())){
				criteria.andItemNameLike("%"+appeal.getItemName()+"%");
			}
		}
		return criteria;
	}

	@Override
	public List<ResAppeal> searchAppeal(List<String> schDeptFlows,ResAppeal appeal){
		ResAppealExample example = new ResAppealExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSchDeptFlowIn(schDeptFlows);
		if(appeal!=null){
			if(StringUtil.isNotBlank(appeal.getSchDeptName())){
				criteria.andSchDeptNameLike("%"+appeal.getSchDeptName()+"%");
			}
			if(StringUtil.isNotBlank(appeal.getItemName())){
				criteria.andItemNameLike("%"+appeal.getItemName()+"%");
			}
			if(StringUtil.isNotBlank(appeal.getRecTypeId())){
				criteria.andRecTypeIdEqualTo(appeal.getRecTypeId());
			}
			if(StringUtil.isNotBlank(appeal.getStatusId())){
				criteria.andStatusIdEqualTo(appeal.getStatusId());
			}
			if(StringUtil.isNotBlank(appeal.getAuditStatusId())){
				String auditStatusId = appeal.getAuditStatusId();
				if(RecStatusEnum.TeacherAuditY.getId().equals(auditStatusId)){
					criteria.andAuditStatusIdIsNotNull();
				}
				if(RecStatusEnum.TeacherAuditN.getId().equals(auditStatusId)){
					criteria.andAuditStatusIdIsNull();
				}
			}
			if(StringUtil.isNotBlank(appeal.getHeadAuditStatusId())){
				String headAuditStatusId = appeal.getHeadAuditStatusId();
				if(RecStatusEnum.HeadAuditY.getId().equals(headAuditStatusId)){
					criteria.andHeadAuditStatusIdIsNotNull();
				}
				if(RecStatusEnum.HeadAuditN.getId().equals(headAuditStatusId)){
					criteria.andHeadAuditStatusIdIsNull();
				}
			}
		}
		example.setOrderByClause("OPER_TIME");
		return resAppealMapper.selectByExample(example);
	}

	@Override
	public List<Map<String,Object>> appealCountWithUser(List<String> userFlows,List<String> processFlows){
		return appealExtMapper.appealCountWithUser(userFlows, processFlows,null);
	}

	@Override
	public List<Map<String,Object>> appealCountWithUser(List<String> userFlows,List<String> processFlows,String roleFlag){
		return appealExtMapper.appealCountWithUser(userFlows, processFlows,roleFlag);
	}

	@Override
	public List<ResAppeal> searchAppealForAudit(String processFlow, String recTypeId){
		return appealExtMapper.searchAppealForAudit(processFlow,recTypeId);
	}

	@Override
	public List<Map<String,Object>> searchAppealCount(String schDeptFlow,String operUserFlow){
		return appealExtMapper.searchAppealCount(schDeptFlow,operUserFlow);
	}

	@Override
	public List<Map<String,Object>> searchNotAuditAppealCount(String schDeptFlow,String teacherUserFlow,String isAudit){
		return appealExtMapper.searchNotAuditAppealCount(schDeptFlow,teacherUserFlow,isAudit);
	}

	/**********************************************申述*********************************************/


	@Override
	public int saveResRecContent(ResRecForm form) throws Exception {
		SysUser currUser = GlobalContext.getCurrentUser();
		String teachTypeId = StringUtil.defaultIfEmpty(form.getTeachTypeId(),"");
		String teachTypeName = "";
		if(StringUtil.isNotBlank(teachTypeId)){
			teachTypeName = DictTypeEnum.TeachType.getDictNameById(teachTypeId);
		}
		String teachAddress = StringUtil.defaultIfEmpty(form.getTeachAddress(),"");
		String teachDate = StringUtil.defaultIfEmpty(form.getTeachDate(),"");
		String teachExplain = StringUtil.defaultIfEmpty(form.getTeachExplain(),"");
		Document dom = null;
		Element root = null;
		ResRec resRec = readResRec(form.getRecFlow());
		if(resRec == null){//新增XML
			resRec = new ResRec();
			dom = DocumentHelper.createDocument();
			root = dom.addElement("content");
			Element teachElement = root.addElement("teach");
			Element teachTypeElement = teachElement.addElement("teachType");
			Element teachAddressElement = teachElement.addElement("teachAddress");
			Element teachDateElement = teachElement.addElement("teachDate");
			Element teachExplainElement = teachElement.addElement("teachExplain");
			teachTypeElement.addAttribute("id", teachTypeId);
			teachTypeElement.setText(teachTypeName);
			teachAddressElement.setText(teachAddress);
			teachDateElement.setText(teachDate);
			teachExplainElement.setText(teachExplain);

			ResDoctor resDoctor = resDoctorBiz.readDoctor(currUser.getUserFlow());
			//SysUser sysUser = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
			if(resDoctor != null){
				resRec.setOrgFlow(resDoctor.getOrgFlow());
				resRec.setOrgName(resDoctor.getOrgName());
				resRec.setDeptFlow(resDoctor.getDeptFlow());
				resRec.setDeptName(resDoctor.getDeptName());
				//resRec.setSchDeptFlow();
				//resRec.setSchDeptName();
				//教学活动
				resRec.setRecTypeId(ResRecTypeEnum.TeachRegistry.getId());
				resRec.setRecTypeName(ResRecTypeEnum.TeachRegistry.getName());
				//resRec.setItemName();
				//resRec.setRecVersion();
			}
		}else{
			String content = resRec.getRecContent();
			if(StringUtil.isNotBlank(content)){
				dom = DocumentHelper.parseText(content);
				String teachXpath = "//teach";
				Element teachElement = (Element) dom.selectSingleNode(teachXpath);
				if(teachElement != null){
					Element teachTypeElement = teachElement.element("teachType");
					if(teachTypeElement != null){
						teachTypeElement.attribute("id").setValue(teachTypeId);
						teachTypeElement.setText(teachTypeName);
					}
					Element teachAddressElement = teachElement.element("teachAddress");
					if(teachAddressElement != null){
						teachAddressElement.setText(teachAddress);
					}
					Element teachDateElement = teachElement.element("teachDate");
					if(teachDateElement != null){
						teachDateElement.setText(teachDate);
					}
					Element teachExplainElement = teachElement.element("teachExplain");
					if(teachExplainElement != null){
						teachExplainElement.setText(teachExplain);
					}
				}
			}
		}
		resRec.setRecContent(dom.asXML());
		return edit(resRec);
	}

	@Override
	public List<ResRecForm> searchResRecFormList(ResRec resRec) throws Exception {
		ResRecExample example = new ResRecExample();
		com.pinde.sci.model.mo.ResRecExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(resRec.getOrgFlow())){
			criteria.andOrgFlowEqualTo(resRec.getOrgFlow());
		}
		if(StringUtil.isNotBlank(resRec.getRecTypeId())){
			criteria.andRecTypeIdEqualTo(resRec.getRecTypeId());
		}
		if(StringUtil.isNotBlank(resRec.getOperUserFlow())){
			criteria.andOperUserFlowEqualTo(resRec.getOperUserFlow());
		}
		example.setOrderByClause("CREATE_TIME DESC");
		List<ResRec> resRecList = resRecMapper.selectByExampleWithBLOBs(example);
		List<ResRecForm> resRecFormList = new ArrayList<ResRecForm>();
		if(resRecList != null && !resRecList.isEmpty()){
			for(ResRec rec : resRecList){
				ResRecForm form = new ResRecForm();
				form.setRecFlow(rec.getRecFlow());
				if(StringUtil.isNotBlank(rec.getRecContent())){
					Document dom = DocumentHelper.parseText(rec.getRecContent());
					String teachXpath = "//teach";
					Element teachElement = (Element) dom.selectSingleNode(teachXpath);
					if(teachElement != null){
						Element teachTypeElement = teachElement.element("teachType");
						if(teachTypeElement != null){
							form.setTeachTypeId(teachTypeElement.attributeValue("id"));
						}
						Element teachAddressElement = teachElement.element("teachAddress");
						if(teachAddressElement != null){
							form.setTeachAddress(teachAddressElement.getText());
						}
						Element teachDateElement = teachElement.element("teachDate");
						if(teachDateElement != null){
							form.setTeachDate(teachDateElement.getText());
						}
						Element teachExplainElement = teachElement.element("teachExplain");
						if(teachExplainElement != null){
							form.setTeachExplain(teachExplainElement.getText());
						}
					}
				}
				resRecFormList.add(form);
			}
		}
		return resRecFormList;
	}

	@Override
	public ResRecForm getRecContentByRecFlow(String recFlow) throws Exception {
		ResRecForm form = null;
		ResRec resRec = readResRec(recFlow);
		if(resRec != null){
			form = new ResRecForm();
			form.setRecFlow(resRec.getRecFlow());
			Document dom = DocumentHelper.parseText(resRec.getRecContent());
			String teachXpath = "//teach";
			Element teachElement = (Element) dom.selectSingleNode(teachXpath);
			if(teachElement != null){
				Element teachTypeElement = teachElement.element("teachType");
				if(teachTypeElement != null){
					form.setTeachTypeId(teachTypeElement.attributeValue("id"));
				}
				Element teachAddressElement = teachElement.element("teachAddress");
				if(teachAddressElement != null){
					form.setTeachAddress(teachAddressElement.getText());
				}
				Element teachDateElement = teachElement.element("teachDate");
				if(teachDateElement != null){
					form.setTeachDate(teachDateElement.getText());
				}
				Element teachExplainElement = teachElement.element("teachExplain");
				if(teachExplainElement != null){
					form.setTeachExplain(teachExplainElement.getText());
				}
			}
		}
		return form;
	}

	@Override
	public ResRec searchRecWithBLOBs(String recTypeId,String operUserFlow){
		ResRec rec = null;
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("OPER_TIME");
		List<ResRec> list = resRecMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() >0) {
			rec = list.get(0);
		}
		return rec;
	}

	private String getRecContent(String formName,IrbSingleForm singleForm,HttpServletRequest req){
		Map<String , String[]> dataMap = JspFormUtil.getParameterMap(req);
		Element rootEle = DocumentHelper.createElement(formName);

		List<Element> itemList = singleForm.getItemList();
		if(itemList !=null && itemList.size()>0){
			for(Element itemEle : itemList){
				if ("itemGroup".equals(itemEle.getName())) {	//itemGroup
					String igName = itemEle.attributeValue("name");
					String modelStyle = itemEle.attributeValue("modelStyle");
					if("add".equals(modelStyle)){//平滑式
						int count = getMatchDataIndex(igName, dataMap);
						for (int i = 0; i < count; i++) {
							Element itemGroupEle = rootEle.addElement("itemGroup");
							itemGroupEle.addAttribute("name", igName);

							String remark = itemEle.attributeValue("remark");

							if(StringUtil.isNotBlank(remark)){
								itemGroupEle.addAttribute("remark", remark);
							}
							List<Element> igItemList = itemEle.selectNodes("item");
							iteratorItem(igItemList , itemGroupEle, dataMap , i);
						}
					}
				} else {//item
					String multiple = itemEle.attributeValue("multiple");
					if(GlobalConstant.FLAG_N.equals(multiple) || StringUtil.isBlank(multiple)){
						String name = itemEle.attributeValue("name");
						String value = "";
						if (dataMap.get(name) != null && dataMap.get(name).length > 0) {
							value = dataMap.get(name)[0];
						}
						Element element = rootEle.addElement(itemEle.attributeValue("name"));

						if (StringUtil.isNotBlank(value)) {
							element.setText(value);
						}
					} else {
						String[] values = dataMap.get(itemEle.attributeValue("name"));
						Element element = rootEle.addElement(itemEle.attributeValue("name"));
						if (values != null && values.length > 0) {
							for (String value : values) {
								Element valueEle = element.addElement("value");
								if (StringUtil.isNotBlank(value)) {
									valueEle.setText(value);
								}
							}
						}
					}
				}
			}
		}

		return rootEle.asXML();
	}

//	@Override
//	public int editSpeAbilityAccess(String recFlow,String resultFlow,String roleFlag,HttpServletRequest req){
//		ResRec rec = getRec(recFlow,roleFlag,resultFlow,ResRecTypeEnum.PreTrainForm.getId());
//		String content=getSpeAbilityAccessXml(roleFlag,rec.getRecContent(),req);
//		rec.setRecContent(content);
//		return edit(rec);
//	}

	@Override
	public Map<String,Object> parseContent(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();

				for(Element element : elements){
					if ("itemGroup".equals(element.getName())) {//itemGroup
						String key = element.attributeValue("name");
						Map<String , Object> itemGroupDataMap = null;
						if(ResRecTypeEnum.TeachRegistry.getId().equals(rootElement.getName())){
							itemGroupDataMap = new HashMap<String, Object>();
							List<Element> items = element.elements();
							for(Element item : items){
								List<Node> valueNodes = item.selectNodes("value");
								if(valueNodes != null && !valueNodes.isEmpty()){
									String value = "";
									for(Node node : valueNodes){
										if(StringUtil.isNotBlank(value)){
											value+=",";
										}
										value += node.getText();
									}
									itemGroupDataMap.put(item.getName(), value);
								}else {
									itemGroupDataMap.put(item.getName(), item.getText());
								}
							}
						}else{
							itemGroupDataMap = JspFormUtil.IteratorItemNode(element);
						}
						ItemGroupData itemGroupData = new ItemGroupData();
						itemGroupData.setFlow(element.attributeValue("recordFlow"));
						itemGroupData.setObjMap(itemGroupDataMap);

						if(formDataMap.containsKey(key)){
							Object obj = formDataMap.get(key);
							List<ItemGroupData> itemGroupDataList = (List<ItemGroupData>)obj;
							itemGroupDataList.add(itemGroupData);
							formDataMap.put(key, itemGroupDataList);
						}else{
							List<ItemGroupData> itemGroupDataList = new ArrayList<ItemGroupData>();
							itemGroupDataList.add(itemGroupData);
							formDataMap.put(key, itemGroupDataList);
						}
					} else {//item
						List<Node> valueNodes = element.selectNodes("value");
						if(valueNodes != null && !valueNodes.isEmpty()){
							String value = "";
							for(Node node : valueNodes){
								if(StringUtil.isNotBlank(value)){
									value+=",";
								}
								value += node.getText();
							}
							formDataMap.put(element.getName(), value);
						}else {
							String isSelect = element.attributeValue("id");
							if(StringUtil.isNotBlank(isSelect)){
								formDataMap.put(element.getName()+"_id",isSelect);
								formDataMap.put(element.getName(),element.getText());
							}else{
								formDataMap.put(element.getName(), element.getText());
							}
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}

	@Override
	public Map<String,Object> parseTeachPlanContent(String content,String recordFlow) {
		Map<String,Object> dataMap = null;
		try {
			Document doc = DocumentHelper.parseText(content);
			if(doc!=null){
				Element group = (Element)doc.selectSingleNode("//itemGroup[@recordFlow='"+recordFlow+"']");
				if(group!=null){
					dataMap = new HashMap<String, Object>();
					dataMap.put("recordFlow",group.attributeValue("recordFlow"));
					List<Element> elements = group.elements();
					for(Element element : elements){
						List<Node> valueNodes = element.selectNodes("value");
						if(valueNodes != null && !valueNodes.isEmpty()){
							String value = "";
							for(Node node : valueNodes){
								if(StringUtil.isNotBlank(value)){
									value+=",";
								}
								value += node.getText();
							}
							dataMap.put(element.getName(), value);
						}else {
							dataMap.put(element.getName(), element.getText());
						}
					}
				}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return dataMap;
	}

	@Override
	public int editPreTrainForm(String recFlow,String resultFlow,String roleFlag,HttpServletRequest req){
		ResRec rec = getRec(recFlow,roleFlag,resultFlow,ResRecTypeEnum.PreTrainForm.getId());
		String contet = getPreTrainXml(roleFlag,rec.getRecContent(),req);
		rec.setRecContent(contet);
		return edit(rec);
	}

	@Override
	public int editAnnualTrainForm(String recFlow,String roleFlag,HttpServletRequest req){
		ResRec rec = getRec(recFlow,roleFlag,null,ResRecTypeEnum.AnnualTrainForm.getId());

		String contet = getAnnualTrainXml(req);
		rec.setRecContent(contet);
		return edit(rec);
	}

	public String getSpeAbilityAccessXml(String roleFlag,String content,HttpServletRequest req){
		Enumeration<String> paramNames = req.getParameterNames();
		if(paramNames!=null){
			try{
				Document document = null;
				Element root = null;
				if(StringUtil.isNotBlank(content)){
					document = DocumentHelper.parseText(content);
					root = document.getRootElement();
				}else{
					root = DocumentHelper.createElement("speAbilityAssess");
				}

				Node oldNode = root.selectSingleNode("//"+roleFlag);
				if(oldNode!=null){
					oldNode.detach();
				}

				Element newEle = root.addElement(roleFlag);
				while(paramNames.hasMoreElements()){
					String name = paramNames.nextElement();
					Element item = newEle.addElement(name);
					item.setText(req.getParameter(name));
				}

				content = root.asXML();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return content;
	}

	private String getPreTrainXml(String roleFlag,String content,HttpServletRequest req){
		Enumeration<String> paramNames = req.getParameterNames();
		if(paramNames!=null){
			try{
				Document document = null;
				Element root = null;
				if(StringUtil.isNotBlank(content)){
					document = DocumentHelper.parseText(content);
					root = document.getRootElement();
				}else{
					root = DocumentHelper.createElement("preTrainForm");
				}

				Node oldNode = root.selectSingleNode("//"+roleFlag);
				if(oldNode!=null){
					oldNode.detach();
				}

				Element newEle = root.addElement(roleFlag);
				while(paramNames.hasMoreElements()){
					String name = paramNames.nextElement();
					Element item = newEle.addElement(name);
					item.setText(req.getParameter(name));
				}

				content = root.asXML();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return content;
	}

//	@Override
//	public Map<String,Map<String,String>> getSpeAbilityAssessDataMap(String recContent){
//		Map<String,Map<String,String>> dataMap = null;
//		if(StringUtil.isNotBlank(recContent)){
//			try{
//				dataMap = new HashMap<String, Map<String,String>>();
//				Document document = DocumentHelper.parseText(recContent);
//				if(document != null){
//					Element root = document.getRootElement();
//					if(root != null){
//						List<Element> roleEles = root.elements();
//						if(roleEles!=null && roleEles.size()>0){
//							for(Element roleEle : roleEles){
//								Map<String,String> itemMap = new HashMap<String, String>();
//								dataMap.put(roleEle.getName(),itemMap);
//								List<Element> items = roleEle.elements();
//								if(items!=null && items.size()>0){
//									for(Element item : items){
//										itemMap.put(item.getName(),item.getText());
//									}
//								}
//							}
//						}
//					}
//				}
//
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		return dataMap;
//	}

	private String getAnnualTrainXml(HttpServletRequest req){
		Element root = DocumentHelper.createElement("annualTrainForm");

		String[] deptFlows = req.getParameterValues("deptFlow");
		if(deptFlows!=null && deptFlows.length>0){
			for(String flow : deptFlows){
				Element dept = root.addElement("dept");
				dept.addAttribute("id",flow);

				String[] studyType = req.getParameterValues(flow+"_studyType");
				String[] content = req.getParameterValues(flow+"_content");
				String[] trainDate = req.getParameterValues(flow+"_trainDate");
				String[] studyScore = req.getParameterValues(flow+"_studyScore");
				String[] studyHours = req.getParameterValues(flow+"_studyHours");
				String[] remark = req.getParameterValues(flow+"_remark");
				if(studyType!=null && studyType.length>0){
					int length = studyType.length;
					for(int i = 0 ;i<length ;i++){
						Element item = dept.addElement("item");

						Element studyTypeValue = item.addElement("value");
						studyTypeValue.addAttribute("name",flow+"_studyType");
						studyTypeValue.setText(studyType[i]);

						Element contentValue = item.addElement("value");
						contentValue.addAttribute("name",flow+"_content");
						contentValue.setText(content[i]);

						Element trainDateValue = item.addElement("value");
						trainDateValue.addAttribute("name",flow+"_trainDate");
						trainDateValue.setText(trainDate[i]);

						Element studyScoreValue = item.addElement("value");
						studyScoreValue.addAttribute("name",flow+"_studyScore");
						studyScoreValue.setText(studyScore[i]);

						Element studyHoursValue = item.addElement("value");
						studyHoursValue.addAttribute("name",flow+"_studyHours");
						studyHoursValue.setText(studyHours[i]);

						Element remarkValue = item.addElement("value");
						remarkValue.addAttribute("name",flow+"_remark");
						remarkValue.setText(remark[i]);
					}
				}
			}
		}

		String doctorName = req.getParameter("doctorName");
		Element doctorNameEle = root.addElement("doctorName");
		doctorNameEle.setText(doctorName);

		String doctorOperDate = req.getParameter("doctorOperDate");
		Element doctorOperDateEle = root.addElement("doctorOperDate");
		doctorOperDateEle.setText(doctorOperDate);

		String teacherName = req.getParameter("teacherName");
		if(StringUtil.isNotBlank(teacherName)){
			Element teacherNameEle = root.addElement("teacherName");
			teacherNameEle.setText(teacherName);
		}

		String operDate = req.getParameter("operDate");
		if(StringUtil.isNotBlank(operDate)){
			Element operDateEle = root.addElement("operDate");
			operDateEle.setText(operDate);
		}

		return root.asXML();
	}

	private ResRec getRec(String recFlow,String roleFlag,String resultFlow,String recTypeId){
		ResRec rec = null;

		if(StringUtil.isNotBlank(recFlow)){
			rec = readResRec(recFlow);
			if(GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)){
				rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
				rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
				rec.setAuditTime(DateUtil.getCurrDateTime());
				rec.setAuditUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				rec.setAuditUserName(GlobalContext.getCurrentUser().getUserName());
			}
		}else{
			rec = new ResRec();
			if(StringUtil.isNotBlank(resultFlow)){
				SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
				if(result!=null){
					rec.setDeptFlow(result.getDeptFlow());
					rec.setDeptName(result.getDeptName());
					rec.setSchDeptFlow(result.getSchDeptFlow());
					rec.setSchDeptName(result.getSchDeptName());
				}
			}
			ResDoctor doctor = resDoctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
			if(doctor!=null){
				rec.setOrgFlow(doctor.getOrgFlow());
				rec.setOrgName(doctor.getOrgName());
			}
			rec.setOperUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			rec.setOperUserName(GlobalContext.getCurrentUser().getUserName());
			rec.setRecTypeId(recTypeId);
			rec.setRecTypeName(ResRecTypeEnum.getNameById(recTypeId));
		}
		return rec;
	}

	@Override
	public Map<String,Map<String,String>> getPreTrainFormDataMap(String recContent){
		Map<String,Map<String,String>> dataMap = null;
		if(StringUtil.isNotBlank(recContent)){
			try{
				dataMap = new HashMap<String, Map<String,String>>();
				Document document = DocumentHelper.parseText(recContent);
				if(document != null){
					Element root = document.getRootElement();
					if(root != null){
						List<Element> roleEles = root.elements();
						if(roleEles!=null && roleEles.size()>0){
							for(Element roleEle : roleEles){
								Map<String,String> itemMap = new HashMap<String, String>();
								dataMap.put(roleEle.getName(),itemMap);
								List<Element> items = roleEle.elements();
								if(items!=null && items.size()>0){
									for(Element item : items){
										itemMap.put(item.getName(),item.getText());
									}
								}
							}
						}
					}
				}

			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return dataMap;
	}

	@Override
	public Map<String,Object> getAnnualTrainFormDataMap(String recContent){
		Map<String,Object> dataMap = null;
		if(StringUtil.isNotBlank(recContent)){
			try{
				dataMap = new HashMap<String, Object>();
				Document document = DocumentHelper.parseText(recContent);
				if(document != null){
					Element root = document.getRootElement();
					if(root != null){
						List<Element> baseEles = root.elements();
						if(baseEles!=null && baseEles.size()>0){
							for(Element base : baseEles){
								String name = base.getName();
								if("dept".equals(name)){
									List<Map<String,String>> itemMaps = new ArrayList<Map<String,String>>();
									List<Element> items = base.elements();
									String id = base.attributeValue("id");
									for(Element item : items){
										Map<String,String> data = new HashMap<String, String>();
										List<Element> values = item.elements();
										for(Element value : values){
											data.put(value.attributeValue("name"),value.getText());
										}
										itemMaps.add(data);
									}
									dataMap.put(id,itemMaps);
								}else{
									dataMap.put(name,base.getText());
								}
							}
						}
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return dataMap;
	}

	@Override
	public List<ResRec> searchByUserFlowAndTypeId(String operUserFlow,
												  String recTypeId) {
		ResRecExample example=new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andOperUserFlowEqualTo(operUserFlow);
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchAfterAuditRec(ResDoctorSchProcess process,SysUser user,List<String> recTypeIds,Map<String,String> roleFlagMap){
		return resRecExtMapper.searchAfterAuditRec(process,user,recTypeIds,roleFlagMap);
	}

	@Override
	public List<ResRec> searchSXSRecData(List<String> recTypeIds,Map<String,String> roleFlagMap){
		return resRecExtMapper.searchSXSRecData(recTypeIds,roleFlagMap);
	}

	@Override
	public List<Map<String, String>> findTrainCharts(List<String> orgFlowList, String year, String speName) {
		return resRecExtMapper.findTrainCharts(orgFlowList,year,speName);
	}

	@Override
	public List<Map<String,Object>> searchDeptWillAfter(String orgFlow){
		return resRecExtMapper.searchDeptWillAfter(orgFlow);
	}

	@Override
	public List<Map<String,Object>> searchDeptWillAfterDoc(String orgFlow,String deptFlow) {
		return resRecExtMapper.searchDeptWillAfterDoc(orgFlow,deptFlow);
	}

//	@Override
//	public List<ResRec> searchRecByOrgWithBLOBs(String orgFlow,String recTypeId) {
//		ResRecExample example = new ResRecExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
//				.andRecTypeIdEqualTo(recTypeId).andOrgFlowEqualTo(orgFlow);
//		example.setOrderByClause("OPER_TIME");
//		return resRecMapper.selectByExampleWithBLOBs(example);
//	}

	@Override
	public List<ResRec> searchProssingRec(Map<String,Object> paramMap){
//		List<ResRec> resRecList = campaignRegistryExtMapper.searchProssingRec(paramMap);
//		resRecList.addAll(caseRegistryExtMapper.searchProssingRec(paramMap));
//		resRecList.addAll(diseaseRegistryExtMapper.searchProssingRec(paramMap));
//		resRecList.addAll(languageRegistryExtMapper.searchProssingRec(paramMap));
//		resRecList.addAll(operationRegistryExtMapper.searchProssingRec(paramMap));
//		resRecList.addAll(skillRegistryExtMapper.searchProssingRec(paramMap));
//		return resRecList;
		return resRecExtMapper.searchProssingRec(paramMap);
	}

	@Override
	public int auditRecs(String[] recFlows,ResRec rec){
		if(recFlows!=null && recFlows.length>0 && rec!=null){
			List<String> recFlowList = Arrays.asList(recFlows);
			ResRecExample example = new ResRecExample();
			example.createCriteria().andRecFlowIn(recFlowList);
			return resRecMapper.updateByExampleSelective(rec,example);
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<ResSchProcessExpress> searchRecByProcessWithBLOBs(String processFlow,
																  List<String> recTypeIds) {
		ResSchProcessExpressExample example = new ResSchProcessExpressExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRecTypeIdIn(recTypeIds).andProcessFlowEqualTo(processFlow);
		example.setOrderByClause("OPER_TIME");
		return resSchProcessExpressMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public ResSchProcessExpress readResSchProcessExpress(String recFlow) {
		return resSchProcessExpressMapper.selectByPrimaryKey(recFlow);
	}

	/************************************************* JSRES百分比计算START **********************************************************************************/
	@Override
	public Map<String,String> getProcessPer(String doctorFlow,String rotationFlow){
		return getProcessPer(doctorFlow,rotationFlow,null);
	}

	@Override
	public Map<String,String> getProcessPer(String doctorFlow,String rotationFlow,String relRecordFlow){
		return getProcessPer(doctorFlow,rotationFlow,relRecordFlow,0);
	}

	@Override
	public Map<String,String> getProcessPer(String doctorFlow,String rotationFlow,String relRecordFlow,int format){
		return getProcessPer(doctorFlow,rotationFlow,relRecordFlow,0,null);
	}

	@Override
	public Map<String,String> getProcessPer(
			String doctorFlow,
			String rotationFlow,
			String relRecordFlow,
			int format,
			List<ResRec> recList
	){
		Map<String,String> finishedPer = null;
		if(StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(rotationFlow)){
			finishedPer = new HashMap<String, String>();
			String medicineTypeId="";
			SysUser user=userBiz.readSysUser(doctorFlow);
			if(user!=null)
				medicineTypeId=user.getMedicineTypeId();
			//获取所有缩减的调整科室
			List<SchDoctorDept> doctorDeptList = searchReductionDept(doctorFlow,rotationFlow);

			//获取所有当前方案下的轮转规则
			SchRotationDeptExample rotationDeptExample = new SchRotationDeptExample();
			com.pinde.sci.model.mo.SchRotationDeptExample.Criteria rotationDeptCriteria = rotationDeptExample
					.createCriteria()
					.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andOrgFlowIsNull().andRotationFlowEqualTo(rotationFlow);
			if(StringUtil.isNotBlank(relRecordFlow)){
				rotationDeptCriteria.andRecordFlowEqualTo(relRecordFlow);
			}

			List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExample(rotationDeptExample);

			if(rotationDeptList!=null && !rotationDeptList.isEmpty()){
				//计算缩减要求比例
				Map<String,Float> reqReductionPer = new HashMap<String, Float>();
				if(doctorDeptList!=null && !doctorDeptList.isEmpty()){
					Map<String,Float> reductionMonthMap = new HashMap<String, Float>();
					for(SchDoctorDept sdd : doctorDeptList){
						String month = sdd.getSchMonth();
						if(StringUtil.isNotBlank(month)){
							Float monthF = Float.parseFloat(month);
							String key = sdd.getGroupFlow()+sdd.getStandardDeptId();
							reductionMonthMap.put(key,monthF);
						}
					}

					for(SchRotationDept srd : rotationDeptList){
						String standardMonth = srd.getSchMonth();
						if(StringUtil.isNotBlank(standardMonth)){
							String key = srd.getGroupFlow()+srd.getStandardDeptId();
							Float reductionMonth = reductionMonthMap.get(key);
							Float standardMonthF = Float.parseFloat(standardMonth);
							if(reductionMonth!=null && reductionMonth!=0f && standardMonthF!=0f){
								reqReductionPer.put(srd.getRecordFlow(),reductionMonth/standardMonthF);
							}
						}
					}
				}

				//获取所有当前方案下的要求
				SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
				com.pinde.sci.model.mo.SchRotationDeptReqExample.Criteria reqCriteria = reqExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
						.andRotationFlowEqualTo(rotationFlow);
				if(StringUtil.isNotBlank(relRecordFlow)){
					reqCriteria.andRelRecordFlowEqualTo(relRecordFlow);
				}

				List<String> recTypeList=new ArrayList<>();
				for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
					if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
							&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
						recTypeList.add(regType.getId());
					}
				}
				if(recTypeList.size()>0){
					reqCriteria.andRecTypeIdIn(recTypeList);
				}
				List<SchRotationDeptReq> reqList = rotationDeptReqMapper.selectByExample(reqExample);

				if(reqList!=null && !reqList.isEmpty()){
					//分类统计要求
					Map<String,Float> reqNumMap = new HashMap<String, Float>();
					Map<String,List<String>> itemIdListMap = new HashMap<String, List<String>>();
					for(SchRotationDeptReq req : reqList){
						String rotationDeptFlow = req.getRelRecordFlow();
						String recFlowRecType = rotationDeptFlow+req.getRecTypeId();
						String itemId = req.getItemId();

						//要求数
						Float reqNum = 0f;
						if(req.getReqNum()!=null){
							reqNum = req.getReqNum().floatValue();

							Float reductionPer = reqReductionPer.get(rotationDeptFlow);
							if(reductionPer!=null && reductionPer!=0f){
								reqNum *=reductionPer;
								if(reqNum>0 && reqNum<1){
									reqNum = 1f;
								}

								reqNum = (float)Math.round(reqNum);
							}
						}

						//各规则下的总要求
						setNumMap(reqNumMap,rotationDeptFlow,reqNum);
						BigDecimal b = new BigDecimal(reqNumMap.get(rotationDeptFlow)).setScale(0,BigDecimal.ROUND_HALF_UP);
						finishedPer.put(rotationDeptFlow+"ReqNum",b.toString());

						//各登记类型下的总要求
						setNumMap(reqNumMap,recFlowRecType,reqNum);
						b = new BigDecimal(reqNumMap.get(recFlowRecType)).setScale(0,BigDecimal.ROUND_HALF_UP);
						finishedPer.put(recFlowRecType+"ReqNum",b.toString());

						if(StringUtil.isNotBlank(itemId)){
							//获取各规则下各登记类型的子项列表
							if(itemIdListMap.get(recFlowRecType)==null){
								List<String> itemNameList = new ArrayList<String>();
								itemNameList.add(itemId);
								itemIdListMap.put(recFlowRecType,itemNameList);
							}else{
								itemIdListMap.get(recFlowRecType).add(itemId);
							}

							String recFlowRecTypeItem = recFlowRecType+itemId;
							//各子项要求
							setNumMap(reqNumMap,recFlowRecTypeItem,reqNum);
							b = new BigDecimal(reqNumMap.get(recFlowRecTypeItem)).setScale(0,BigDecimal.ROUND_HALF_UP);
							finishedPer.put(recFlowRecTypeItem+"ReqNum",b.toString());
						}
					}

					if(recList==null){
						//获取当前学员的所有登记数据
//						ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//						ResRecCampaignRegistryExample.Criteria campaignRegistryCriteria = campaignRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//						ResRecCaseRegistryExample.Criteria caseRegistryCriteria = caseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//						ResRecDiseaseRegistryExample.Criteria diseaseRegistryCriteria = diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//						ResRecLanguageRegistryExample.Criteria languageRegistryCriteria = languageRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//						ResRecOperationRegistryExample.Criteria operationRegistryCriteria = operationRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//						ResRecSkillRegistryExample.Criteria skillRegistryCriteria = skillRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
						ResRecExample recExample = new ResRecExample();
						com.pinde.sci.model.mo.ResRecExample.Criteria recCriteria = recExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(doctorFlow);
//						if(StringUtil.isNotBlank(relRecordFlow)){
//							campaignRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							caseRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							diseaseRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							languageRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							operationRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//							skillRegistryCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
////							recCriteria.andSchRotationDeptFlowEqualTo(relRecordFlow);
//						}
//						List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExample(campaignRegistryExample);
//						recList = campaignRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList());
//						List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExample(caseRegistryExample);
//						recList.addAll(caseRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
//						List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExample(diseaseRegistryExample);
//						recList.addAll(diseaseRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
//						List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExample(languageRegistryExample);
//						recList.addAll(languageRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
//						List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExample(operationRegistryExample);
//						recList.addAll(operationRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
//						List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExample(skillRegistryExample);
//						recList.addAll(skillRegistryList.stream().map(e -> {
//							ResRec resRec = new ResRec();
//							BeanUtils.copyProperties(e, resRec);
//							return resRec;
//						}).collect(Collectors.toList()));
						recList = resRecMapper.selectByExample(recExample);
					}

					//分类统计登记记录
					Map<String,Float> recFinishedMap = new HashMap<String, Float>();
					List<String> recTypeIdList = new ArrayList<String>();
					if(recList!=null && !recList.isEmpty()){
						for(ResRec rec : recList){
							String rotationDeptFlow = rec.getSchRotationDeptFlow();
							String recTypeId = rec.getRecTypeId();
							String recFlowRecType = rotationDeptFlow+recTypeId;
							String itemId = rec.getItemId();
							String recFlowRecTypeItem = recFlowRecType+itemId;

							//各规则下的总完成数
							setNumMap(recFinishedMap,rotationDeptFlow,1f);
							BigDecimal b = new BigDecimal(recFinishedMap.get(rotationDeptFlow)).setScale(0,BigDecimal.ROUND_HALF_UP);
							finishedPer.put(rotationDeptFlow+"Finished",b.toString());

							//各登记类型下的总完成数
							setNumMap(recFinishedMap,recFlowRecType,1f);
							b = new BigDecimal(recFinishedMap.get(recFlowRecType)).setScale(0,BigDecimal.ROUND_HALF_UP);
							finishedPer.put(recFlowRecType+"Finished",b.toString());

							if(StringUtil.isNotBlank(itemId)){
								//各子项完成数
								setNumMap(recFinishedMap,recFlowRecTypeItem,1f);
								b = new BigDecimal(recFinishedMap.get(recFlowRecTypeItem)).setScale(0,BigDecimal.ROUND_HALF_UP);
								finishedPer.put(recFlowRecTypeItem+"Finished",b.toString());
							}

							//获取用到的登记类型
							if(!recTypeIdList.contains(recTypeId)){
								recTypeIdList.add(recTypeId);
							}
						}
					}

					//开始计算百分比
					for(SchRotationDept rotationDept : rotationDeptList){
						String recordFlow = rotationDept.getRecordFlow();
						//总完成数
						Float finishSum = recFinishedMap.get(recordFlow);
						if(finishSum==null){
							finishSum = 0f;
						}
						//总比
						Float sumPer = 0f;
						//当前规则要求总数
						Float reqSum = reqNumMap.get(recordFlow);
						if(reqSum==null){
							reqSum = 0f;
						}
//						finishedPer.put(recordFlow+"ReqNum",reqSum.toString());

						for(String recTypeId : recTypeList){
							//类型总比
							Float recSumPer = 0f;

							String recordFlowRecType = recordFlow+recTypeId;

							//该类型完成总数
							Float recFinished = recFinishedMap.get(recordFlowRecType);
							if(recFinished==null){
								recFinished = 0f;
							}
							//当前类型所要求总和
							Float recReqSum = reqNumMap.get(recordFlowRecType);
							if(recReqSum==null){
								recReqSum = 0f;
							}
//							finishedPer.put(recordFlowRecType+"ReqNum",recReqSum.toString());

							//当前类型占总和的比
							float recPer = cdec(recReqSum,reqSum);//recReqSum/(reqSum+0f);

							//当前类型下的子项列表
							List<String> itemIds = itemIdListMap.get(recordFlowRecType);

							if(!RegistryTypeEnum.CampaignRegistry.getId().equals(recTypeId)) {
								if(itemIds!=null && !itemIds.isEmpty()){
									for(String itemId : itemIds){
										String recordFlowRecTypeItemId = recordFlowRecType+itemId;
										//当前子项要求数
										Float recItemReqSum = reqNumMap.get(recordFlowRecTypeItemId);
										if(recItemReqSum==null)
											recItemReqSum = 0f;
										//									finishedPer.put(recordFlowRecTypeItemId+"ReqNum",recItemReqSum.toString());
										//当前子项所占当前类型的比
										float itemPer = cdec(recItemReqSum,recReqSum);//recItemReqSum/(recReqSum+0f);
										//当前子项的完成数
										Float itemFinished = recFinishedMap.get(recordFlowRecTypeItemId);
										if(itemFinished==null){
											itemFinished = 0f;
										}

										float itemFinPer = cdec(itemFinished,recItemReqSum);
										if((recItemReqSum==null || recItemReqSum==0)){
											itemFinPer = 1;
										}

										//子项占比
										sumPer+=itemFinPer*itemPer*recPer;//((itemFinished/(recItemReqSum+0f))*itemPer*recPer);

										recSumPer+=itemFinPer*itemPer;//((itemFinished/(recItemReqSum+0f))*itemPer);

										BigDecimal b = new BigDecimal(itemFinPer*100).setScale(format,BigDecimal.ROUND_HALF_UP);//(itemFinished/(recItemReqSum+0f))
										finishedPer.put(recordFlowRecTypeItemId,b.toString());
									}
								}else{
									float recFinishPer = cdec(recFinished,recReqSum);//recFinished/(recReqSum+0f);

									if((recReqSum==null || recReqSum==0)){
										recFinishPer = 1;
									}

									recSumPer+=recFinishPer;

									sumPer+=(recPer*recFinishPer);
								}
							}else{
								float recFinishPer = cdec(recFinished,recReqSum);//recFinished/(recReqSum+0f);

								if((recReqSum==null || recReqSum==0)){
									recFinishPer = 1;
								}

								recSumPer+=recFinishPer;

								sumPer+=(recPer*recFinishPer);
							}

							if(recReqSum==0){
								recSumPer = 1f;
							}

							BigDecimal b = new BigDecimal(recSumPer*100).setScale(format,BigDecimal.ROUND_HALF_UP);
							finishedPer.put(recordFlowRecType,b.toString());
						}

						if(reqSum==0){
							sumPer=1f;
						}

						BigDecimal b = new BigDecimal(sumPer*100).setScale(format,BigDecimal.ROUND_HALF_UP);
						finishedPer.put(recordFlow,b.toString());
					}
				}
			}
		}
		return finishedPer;
	}

	//统计Map
	private void setNumMap(Map<String,Float> numMap,String key,Float num){
		if(numMap.get(key)==null){
			numMap.put(key,num);
		}else{
			numMap.put(key,numMap.get(key)+num);
		}
	}

	//两数相除
	private float cdec(float a,float b){
		float result = 0;
		if(a!=0 && b!=0){
			result = a/b;
			if(result>1){
				result = 1;
			}
		}else if(b==0)
		{
			result=1;
		}
		return result;
	}

	private List<SchDoctorDept> searchReductionDept(String doctorFlow,String rotationFlow){
		SchDoctorDeptExample doctorDeptExample = new SchDoctorDeptExample();
		doctorDeptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow).andRotationFlowEqualTo(rotationFlow);
		return docDeptMapper.selectByExample(doctorDeptExample);
	}

	/*************************************************JSRES百分比计算END**********************************************************************************/
	@Override
	public ResRec queryResRec(String processFlow, String operUserFlow,
							  String recTypeId) {
		ResRec rec=null;
		ResRecExample example = new ResRecExample();
		com.pinde.sci.model.mo.ResRecExample.Criteria create=example.createCriteria();
		create.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(processFlow)) {
			create.andSchRotationDeptFlowEqualTo(processFlow);
		}
		if (StringUtil.isNotBlank(operUserFlow)) {
			create.andOperUserFlowEqualTo(operUserFlow);
		}
		if (StringUtil.isNotBlank(recTypeId)) {
			create.andRecTypeIdEqualTo(recTypeId);
		}
		List<ResRec> list = resRecMapper.selectByExampleWithBLOBs(example);
		if (list != null && list.size() >0) {
			rec = list.get(0);
		}
		return rec;
	}

	@Override
	public Map<String, String> resRecImg(String recFlow,MultipartFile file,String fileAddress) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("status", GlobalConstant.OPRE_FAIL_FLAG);
		if(file!=null){
			List<String> mimeList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
				mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
			}
			List<String> suffixList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
				suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
			}

			String fileType = file.getContentType();//MIME类型;
			String fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
				map.put("error", GlobalConstant.UPLOAD_IMG_TYPE_ERROR);
				return  map;

			}
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if (file.getSize() > limitSize * 1024 * 1024) {
				map.put("error", GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize +"M") ;
				return  map;
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+fileAddress+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = file.getOriginalFilename();
				fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				String url = InitConfig.getSysCfg("upload_base_url")+"/"+fileAddress+"/"+dateString+"/"+fileName;
				if(StringUtil.isNotBlank(recFlow)){
//					ResRec resRec=readResRec(recFlow);
					ResSchProcessExpress resRec = expressBiz.readResExpress(recFlow);
					String content =resRec.getRecContent();
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
						resRec.setRecContent(document.asXML());
						expressBiz.edit(resRec);
						map.put("url",url);
						map.put("flow",imageFlow);
						//更新schArrangeresult 表中have_after_pic
						updateResultHaveAfter(resRec.getSchRotationDeptFlow(),resRec.getOperUserFlow(),resRec.getRecContent());
					}
				}
				FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
				String localFilePath=fileDir+File.separator+fileName;
				String ftpDir= fileAddress+File.separator +dateString ;
				String ftpFileName=fileName;
				ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);
				map.put("status",GlobalConstant.OPRE_SUCCESSED_FLAG);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public void updateResultHaveAfter(String schRotationDeptFlow, String operUserFlow, String recContent) throws DocumentException {
		String haveAfterPic="N";
		if(StringUtil.isNotBlank(recContent))
		{
			Document document=DocumentHelper.parseText(recContent);
			if (document!=null) {
				Element element=document.getRootElement();
				List<Object> elem=element.elements("image");
				if(elem!=null&&elem.size()>0)
				{
					haveAfterPic="Y";
				}
			}
		}
		resultBiz.updateResultHaveAfter(haveAfterPic,schRotationDeptFlow,operUserFlow);
	}

	@Override
	public Map<String, String> resRecImgs(String recFlow,MultipartFile file,String fileAddress) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("status", GlobalConstant.OPRE_FAIL_FLAG);
		if(file!=null){
			List<String> mimeList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
				mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
			}
			List<String> suffixList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
				suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
			}

			String fileType = file.getContentType();//MIME类型;
			String fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
				map.put("error", GlobalConstant.UPLOAD_IMG_TYPE_ERROR);
				return  map;

			}
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if (file.getSize() > limitSize * 1024 * 1024) {
				map.put("error", GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize +"M") ;
				return  map;
			}
			try {
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+fileAddress+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				fileName = file.getOriginalFilename();
				fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				String url = fileAddress+"/"+dateString+"/"+fileName;
				if(StringUtil.isNotBlank(recFlow)){
					ResRec resRec=readResRec(recFlow);
					resRec.setRecContent(url);
					edit(resRec);
					map.put("url",url);
				}
				map.put("status",GlobalConstant.OPRE_SUCCESSED_FLAG);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public List<ResRec> searchByResRecWithBLOBs(String recTypeId,
												String schRotationDeptFlow, String operUserFlow, String itemId) {
		ResRecExample example = new ResRecExample();
		ResRecExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdEqualTo(recTypeId)
				.andSchRotationDeptFlowEqualTo(schRotationDeptFlow).andOperUserFlowEqualTo(operUserFlow);
		if (StringUtil.isNotBlank(itemId)) {
			criteria.andItemIdEqualTo(itemId);
		}
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchResRecWithBLOBs(String recTypeId, String processFlow, String operUserFlow) {
//		List<ResRec> resRecList;
//		if (recTypeId.equals(RegistryTypeEnum.CampaignRegistry.getId())) {
//			ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//			campaignRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//			campaignRegistryExample.setOrderByClause("AUDIT_STATUS_ID ,item_id");
//			List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExampleWithBLOBs(campaignRegistryExample);
//			resRecList = campaignRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recTypeId.equals(RegistryTypeEnum.CaseRegistry.getId())) {
//			ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//			caseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//			caseRegistryExample.setOrderByClause("AUDIT_STATUS_ID ,item_id");
//			List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExampleWithBLOBs(caseRegistryExample);
//			resRecList = caseRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recTypeId.equals(RegistryTypeEnum.DiseaseRegistry.getId())) {
//			ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//			diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//			diseaseRegistryExample.setOrderByClause("AUDIT_STATUS_ID ,item_id");
//			List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExampleWithBLOBs(diseaseRegistryExample);
//			resRecList = diseaseRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recTypeId.equals(RegistryTypeEnum.LanguageTeachingResearch.getId())) {
//			ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//			languageRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//			languageRegistryExample.setOrderByClause("AUDIT_STATUS_ID ,item_id");
//			List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExampleWithBLOBs(languageRegistryExample);
//			resRecList = languageRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recTypeId.equals(RegistryTypeEnum.OperationRegistry.getId())) {
//			ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//			operationRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//			operationRegistryExample.setOrderByClause("AUDIT_STATUS_ID ,item_id");
//			List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExampleWithBLOBs(operationRegistryExample);
//			resRecList = operationRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recTypeId.equals(RegistryTypeEnum.SkillRegistry.getId())) {
//			ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//			skillRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//			skillRegistryExample.setOrderByClause("AUDIT_STATUS_ID ,item_id");
//			List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExampleWithBLOBs(skillRegistryExample);
//			resRecList = skillRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else {
//			resRecList = new ArrayList<>();
//		}
//
//		return resRecList;

		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRecTypeIdEqualTo(recTypeId)
				.andProcessFlowEqualTo(processFlow)
				.andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("AUDIT_STATUS_ID ,item_id");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchResRecWithBLOBs(String recTypeId,
											  String processFlow, String operUserFlow,String itemId) {
		if(StringUtil.isBlank(processFlow))
		{
			return new ArrayList<>();
		}
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRecTypeIdEqualTo(recTypeId)
				.andProcessFlowEqualTo(processFlow)
				.andOperUserFlowEqualTo(operUserFlow)
				.andItemIdEqualTo(itemId);
		example.setOrderByClause("AUDIT_STATUS_ID ");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public List<ResRec> searchResRecWithBLOBsByRotationDeptFlow(String recTypeId,
																String recordFlow, String operUserFlow) {
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRecTypeIdEqualTo(recTypeId)
				.andSchRotationDeptFlowEqualTo(recordFlow)
				.andOperUserFlowEqualTo(operUserFlow);
		return resRecMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public List<ResRec> searchByUserFlow(String operUserFlow) {
		ResRecExample example=new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOperUserFlowEqualTo(operUserFlow);
		return resRecMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public Map<String, String> upload(MultipartFile file,String fileAddress) {
		Map<String, String> map=new HashMap<String, String>();
		map.put("status", GlobalConstant.OPRE_FAIL_FLAG);
		if(file!=null){
			String fileName = file.getOriginalFilename();//文件名
			map.put("fileName",fileName);
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if (file.getSize() > limitSize * 1024 * 1024) {
				map.put("error", GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize +"M") ;
				return  map;
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+fileAddress+File.separator + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				String url ="/"+fileAddress+"/"+dateString+"/"+fileName;
				map.put("fileUrl",url);
				map.put("status",GlobalConstant.OPRE_SUCCESSED_FLAG);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	@Override
	public List<ResRec> searchRecByProcess(String processFlow,String doctorFlow) {
//		ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//		campaignRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		campaignRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExample(campaignRegistryExample);
//		List<ResRec> resRecList = campaignRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList());
//		ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//		caseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		caseRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExample(caseRegistryExample);
//		resRecList.addAll(caseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//		diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		diseaseRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExample(diseaseRegistryExample);
//		resRecList.addAll(diseaseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//		languageRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		languageRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExample(languageRegistryExample);
//		resRecList.addAll(languageRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//		operationRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		operationRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExample(operationRegistryExample);
//		resRecList.addAll(operationRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//		skillRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		skillRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExample(skillRegistryExample);
//		resRecList.addAll(skillRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		return resRecList;

		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
		example.setOrderByClause("OPER_TIME");
		return resRecMapper.selectByExample(example);
	}


	//	@Override
//	public int searchByDeptFlow(String deptFlow) {
//		ResRecExample example=new ResRecExample();
//		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDeptFlowEqualTo(deptFlow);
//		return resRecMapper.countByExample(example);
//	}
	@Override
	public List<ResRec> searchProssFlowRec(String teacherUserFlow,String startDate,String endDate) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("teacherUserFlow",teacherUserFlow);
		if (StringUtil.isNotBlank(startDate)) {
			startDate = DateUtil.getDate(startDate)+"000000";
			map.put("startDate",startDate);
		}
		if (StringUtil.isNotBlank(endDate)) {
			endDate = DateUtil.getDate(endDate)+"235959";
			map.put("endDate",endDate);
		}
		return resRecExtMapper.searchProssFlowRecRec(map);
	}

	@Override
	public List<ResRec> resSearchProssFlowRec(String teacherUserFlow,String startDate,String endDate) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("teacherUserFlow",teacherUserFlow);
		if (StringUtil.isNotBlank(startDate)) {
			startDate = DateUtil.getDate(startDate)+"000000";
			map.put("startDate",startDate);
		}
		if (StringUtil.isNotBlank(endDate)) {
			endDate = DateUtil.getDate(endDate)+"235959";
			map.put("endDate",endDate);
		}
		return resRecExtMapper.resSearchProssFlowRecRec(map);
	}

	@Override
	public List<ResRec> resSearchDeptFlowRec(String deptFlow,String startDate,String endDate) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("deptFlow",deptFlow);
		if (StringUtil.isNotBlank(startDate)) {
			startDate = DateUtil.getDate(startDate)+"000000";
			map.put("startDate",startDate);
		}
		if (StringUtil.isNotBlank(endDate)) {
			endDate = DateUtil.getDate(endDate)+"235959";
			map.put("endDate",endDate);
		}
		return resRecExtMapper.resSearchDeptFlowRec(map);
	}
	@Override
	public List<ResRec> searchDeptFlowRec(String deptFlow,String startDate,String endDate) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("deptFlow",deptFlow);
		if (StringUtil.isNotBlank(startDate)) {
			startDate = DateUtil.getDate(startDate)+"000000";
			map.put("startDate",startDate);
		}
		if (StringUtil.isNotBlank(endDate)) {
			endDate = DateUtil.getDate(endDate)+"235959";
			map.put("endDate",endDate);
		}
		return resRecExtMapper.searchDeptFlowRec(map);
	}

	@Override
	public List<Map<String,String>> getRecContentByProcess(Map<String,Object> paramMap) {
		return resRecExtMapper.getRecContentByProcess(paramMap);
	}
	@Override
	public List<Map<String,String>> getRecContentByProcessForUni(Map<String,Object> paramMap) {
		return resRecExtMapper.getRecContentByProcessForUni(paramMap);
	}

	/*************************************************
	 * jsres&product百分比算法,兼容缩减START
	 ************************************************************/
	/**
	 * 工具方法START
	 */

	/**
	 * get方法实现
	 * @param prop	属性名
	 * @param obj	对象
	 * @return
	 * @throws Exception
	 */
	private <T> T getMethod(String prop,Object obj) throws Exception{
		Class clazz = obj.getClass();
		T value = null;
		String firstWord = prop.substring(0,1);
		firstWord = firstWord.toUpperCase();
		String surplusWords = "";
		if(prop.length()>1){
			surplusWords = prop.substring(1);
		}
		String methodName = "get"+firstWord+surplusWords;
		Method method = clazz.getMethod(methodName);
		value = (T)method.invoke(obj);
		return value;
	}

	/**
	 * 在本对象内获取key
	 * @param obj	该对象
	 * @param keys	组合key的属性名
	 * @return
	 */
	private String groupTheKey(Object obj,String[] keys){
		String key = "";
		for(String s : keys){
			String val = "";
			try {
				val = getMethod(s,obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
			key+=val;
		}
		return key;
	}

	/**
	 * 以对象本身的属性作为key组织一个map 1-1/N
	 * @param isMany	是否是1-n
	 * @param objs	数据列表
	 * @param keys	以该对象的某个或多个属性作为key
	 * @return
	 */
	private <T,E> Map<String,T> transListObjToMapsSoM(boolean isMany,List<E> objs,String ...keys){
		if(objs!=null && !objs.isEmpty() && keys!=null && keys.length>0){
			Map<String,T> map = new HashMap<String, T>();
			for(E e : objs){
				String key = groupTheKey(e,keys);
				if(isMany){
					List<E> es = (ArrayList<E>)map.get(key);
					if(es==null){
						es = new ArrayList<E>();
					}
					es.add(e);
					map.put(key,(T)es);
				}else{
					map.put(key,(T)e);
				}
			}
			return map;
		}
		return null;
	}

	/**
	 * 两数相除且不能大于1
	 * @param a
	 * @param b
	 * @return
	 */
	private float getPerResult(float a,float b){
		if(a<=0){
			return 0;
		}
		if(b<=0){
			return 0;
		}
		float result = a/b;
		if(result>1){
			result = 1;
		}
		return result;
	}

	/**
	 * 为一个Map<String,Float> 类型的map赋值	同key算和
	 * @param numMap	被赋值的map
	 * @param key	map的key
	 * @param num	map的值
	 */
	private void setVal4MapStrFloat(Map<String,Float> numMap,String key,Float num){
		if(numMap!=null){
			Float v = numMap.get(key);
			if(v==null){
				numMap.put(key,num);
			}else{
				numMap.put(key,v+num);
			}
		}
	}

	/**
	 * 取出map内的数字默认0
	 * @param map	存放数据的map
	 * @param key	获取数据的key
	 * @return
	 */
	private Float getFdefaultZero(Map<String,Float> map,String key){
		Float result = 0f;
		if(map!=null){
			Float v = map.get(key);
			if(v!=null){
				result = v;
			}
		}
		return result;
	}

	/**
	 * 把值格式化后塞进map
	 * @param perMap	被赋值的Map
	 * @param key
	 * @param val
	 * @param format	小数点后位数
	 */
	private void setVal4PerMap(Map<String,Object> perMap,String key,Float val,int format){
		if(perMap!=null){
			BigDecimal b = new BigDecimal(val).setScale(format,BigDecimal.ROUND_HALF_UP);
			perMap.put(key,b.toString());
		}
	}

	/**
	 * 工具方法END
	 */

	/**
	 * 基础数据列表获取方法START
	 */

	/**
	 * resRec数据
	 * @param userFlow	用户流水号
	 * @param processFlow	轮转过程流水号
	 * @param recTypeId	登记类型id
	 * @param itemId	登记子项id
	 * @return	rec列表不含recContent字段
	 */
	public List<ResRec> getRecs(String userFlow,String processFlow,String recTypeId,String itemId){
		int coditionCount = 0;

//		ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//		ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//		ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//		ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//		ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//		ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//		ResRecCampaignRegistryExample.Criteria campaignRegistryCriteria = campaignRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecCaseRegistryExample.Criteria caseRegistryCriteria = caseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecDiseaseRegistryExample.Criteria diseaseRegistryCriteria = diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecLanguageRegistryExample.Criteria languageRegistryCriteria = languageRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecOperationRegistryExample.Criteria operationRegistryCriteria = operationRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//		ResRecSkillRegistryExample.Criteria skillRegistryCriteria = skillRegistryExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

		ResRecExample recExample = new ResRecExample();
		com.pinde.sci.model.mo.ResRecExample.Criteria recCriteria = recExample.createCriteria()
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(userFlow)){
			coditionCount++;
			recCriteria.andOperUserFlowEqualTo(userFlow);
//			campaignRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			caseRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			diseaseRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			languageRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			operationRegistryCriteria.andOperUserFlowEqualTo(userFlow);
//			skillRegistryCriteria.andOperUserFlowEqualTo(userFlow);
		}
		if(StringUtil.isNotBlank(processFlow)){
			coditionCount++;
			recCriteria.andProcessFlowEqualTo(processFlow);
//			campaignRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			caseRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			diseaseRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			languageRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			operationRegistryCriteria.andProcessFlowEqualTo(processFlow);
//			skillRegistryCriteria.andProcessFlowEqualTo(processFlow);
			if(StringUtil.isNotBlank(recTypeId)){
				coditionCount++;
				recCriteria.andRecTypeIdEqualTo(recTypeId);
//				campaignRegistryCriteria.andRecTypeIdEqualTo(recTypeId);
//				caseRegistryCriteria.andRecTypeIdEqualTo(recTypeId);
//				diseaseRegistryCriteria.andRecTypeIdEqualTo(recTypeId);
//				languageRegistryCriteria.andRecTypeIdEqualTo(recTypeId);
//				operationRegistryCriteria.andRecTypeIdEqualTo(recTypeId);
//				skillRegistryCriteria.andRecTypeIdEqualTo(recTypeId);
			}
			if(StringUtil.isNotBlank(itemId)){
				coditionCount++;
				recCriteria.andItemIdEqualTo(itemId);
//				campaignRegistryCriteria.andItemIdEqualTo(itemId);
//				caseRegistryCriteria.andItemIdEqualTo(itemId);
//				diseaseRegistryCriteria.andItemIdEqualTo(itemId);
//				languageRegistryCriteria.andItemIdEqualTo(itemId);
//				operationRegistryCriteria.andItemIdEqualTo(itemId);
//				skillRegistryCriteria.andItemIdEqualTo(itemId);
			}
		}
		if(coditionCount==0){
			return null;
		}
//		List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExample(campaignRegistryExample);
//		List<ResRec> resRecList = campaignRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList());
//		List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExample(caseRegistryExample);
//		resRecList.addAll(caseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExample(diseaseRegistryExample);
//		resRecList.addAll(diseaseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExample(languageRegistryExample);
//		resRecList.addAll(languageRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExample(operationRegistryExample);
//		resRecList.addAll(operationRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExample(skillRegistryExample);
//		resRecList.addAll(skillRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		return resRecList;
		return resRecMapper.selectByExample(recExample);
	}

	/**
	 * 要求数据
	 * @param rotationFlow	轮转方案流水号
	 * @param relRecordFlow	标准科室流水号
	 * @param recTypeId	要求类型id
	 * @param itemId	要求子项id
	 * @return
	 */
	public List<SchRotationDeptReq> getReqs(String rotationFlow,String relRecordFlow,String recTypeId,String itemId,List<String> recTypeIds){
		int coditionCount = 0;

		SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
		com.pinde.sci.model.mo.SchRotationDeptReqExample.Criteria reqCriteria = reqExample.createCriteria()
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

		if(StringUtil.isNotBlank(rotationFlow)){
			coditionCount++;
			reqCriteria.andRotationFlowEqualTo(rotationFlow);
		}

		if(StringUtil.isNotBlank(relRecordFlow)){
			coditionCount++;
			reqCriteria.andRelRecordFlowEqualTo(relRecordFlow);

			if(StringUtil.isNotBlank(recTypeId)){
				coditionCount++;
				reqCriteria.andRecTypeIdEqualTo(recTypeId);
			}
			if(recTypeIds!=null&& recTypeIds.size()>0)
			{
				coditionCount++;
				reqCriteria.andRecTypeIdIn(recTypeIds);
			}
			if(StringUtil.isNotBlank(itemId)){
				coditionCount++;
				reqCriteria.andItemIdEqualTo(itemId);
			}
		}

		if(coditionCount==0){
			return null;
		}

		return rotationDeptReqMapper.selectByExample(reqExample);
	}

	/**
	 * 查询学员的轮转计划和过程信息
	 * @param userFlow	学员流水号
	 * @param resultFlow	计划流水号
	 * @param processFlow	过程流水号
	 * @return
	 * 至少有传参数
	 */
	public List<SchArrangeResultExt> getResults(String userFlow,String resultFlow,String processFlow){
		int coditionCount = 0;

		Map<String,Object> paramMap = new HashMap<String, Object>();

		if(StringUtil.isNotBlank(userFlow)){
			coditionCount++;
			paramMap.put("userFlow",userFlow);
		}

		if(StringUtil.isNotBlank(resultFlow)){
			coditionCount++;
			paramMap.put("resultFlow",resultFlow);
		}

		if(StringUtil.isNotBlank(processFlow)){
			coditionCount++;
			paramMap.put("processFlow",processFlow);
		}

		if(coditionCount==0){
			return null;
		}

		return resultExtMapper.getResults(paramMap);
	}

	/**
	 * 获取轮转方案的规则
	 * @param orgFlow    是否是标准(机构号)
	 * @param rotationFlow	轮转方案流水号
	 * @param recordFlow	轮转科室流水号
	 * @return
	 */
	public List<SchRotationDept> getDept(String orgFlow,String rotationFlow,String recordFlow){
		int coditionCount = 0;

		SchRotationDeptExample deptExample = new SchRotationDeptExample();
		com.pinde.sci.model.mo.SchRotationDeptExample.Criteria deptCriteria = deptExample.createCriteria()
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

		if(StringUtil.isNotBlank(orgFlow)){
			deptCriteria.andOrgFlowEqualTo(orgFlow);
		}else{
			deptCriteria.andOrgFlowIsNull();
		}

		if(StringUtil.isNotBlank(rotationFlow)){
			coditionCount++;
			deptCriteria.andRotationFlowEqualTo(rotationFlow);
		}

		if(StringUtil.isNotBlank(recordFlow)){
			coditionCount++;
			deptCriteria.andRecordFlowEqualTo(recordFlow);
		}

		if(coditionCount==0){
			return null;
		}

		return rotationDeptMapper.selectByExample(deptExample);
	}

	/**
	 * 暂时作为缩减方案表
	 * @param doctorFlow
	 * @param rotationFlow
	 * @return
	 */
	public List<SchDoctorDept> getSelDepts(String doctorFlow,String rotationFlow){
		int coditionCount = 0;

		SchDoctorDeptExample docDeptExample = new SchDoctorDeptExample();
		com.pinde.sci.model.mo.SchDoctorDeptExample.Criteria docDeptCriteria = docDeptExample.createCriteria()
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

		if(StringUtil.isNotBlank(doctorFlow)){
			coditionCount++;
			docDeptCriteria.andDoctorFlowEqualTo(doctorFlow);
		}

		if(StringUtil.isNotBlank(rotationFlow)){
			coditionCount++;
			docDeptCriteria.andRotationFlowEqualTo(rotationFlow);
		}

		if(coditionCount==0){
			return null;
		}

		return docDeptMapper.selectByExample(docDeptExample);
	}

	/**
	 * 基础数据列表获取方法END
	 */

	/**
	 * 基本数据包装方法START
	 */

	/**
	 * 统计各层面的rec数量
	 * @param recs	rec数据列表
	 * @return
	 */
	public Map<String,Float> getRecCount(List<ResRec> recs){
		if(recs!=null && !recs.isEmpty()){
			Map<String,Float> recCountMap = new HashMap<String,Float>();
			for(ResRec rr : recs){
				if(rr!=null){
					//计算总完成数
					setVal4MapStrFloat(recCountMap,TOTAL,1f);

					//当前轮转科室的登记数量
					String processFlow = rr.getProcessFlow();
					setVal4MapStrFloat(recCountMap,processFlow,1f);

					//当前标准科室的登记数量
					String schRotationDeptFlow = rr.getSchRotationDeptFlow();
					setVal4MapStrFloat(recCountMap,schRotationDeptFlow,1f);

					//登记类型
					String recTypeId = rr.getRecTypeId();

					//轮转科室及标准科室下各类型的登记数量
					setVal4MapStrFloat(recCountMap,processFlow+recTypeId,1f);
					setVal4MapStrFloat(recCountMap,schRotationDeptFlow+recTypeId,1f);

					//子项id
					String itemId = rr.getItemId();

					//轮转科室及标准科室下各子项的登记数量
					if(StringUtil.isNotBlank(itemId)){
						setVal4MapStrFloat(recCountMap,processFlow+recTypeId+itemId,1f);
						setVal4MapStrFloat(recCountMap,schRotationDeptFlow+recTypeId+itemId,1f);
					}
				}
			}
			return recCountMap;
		}
		return null;
	}

	/**
	 * 计算各层面要求的同时组织出各类型下的子项列表
	 * @param reqs	要求数据列表
	 * @param itemsMap	子项列表Map
	 * @return
	 */
	public Map<String,Float> getReqCount(List<SchRotationDept> depts,List<SchRotationDeptReq> reqs,List<SchDoctorDept> selDepts,Map<String,List<String>> itemsMap){
		//验证参数是否可用
		if(depts==null || depts.isEmpty()){
			return null;
		}
		if(reqs==null || reqs.isEmpty()){
			return null;
		}

		Map<String,SchDoctorDept> selDeptMap = transListObjToMapsSoM(false,selDepts,"groupFlow","standardDeptId");

		Map<String,SchRotationDept> deptMap = transListObjToMapsSoM(false,depts,"recordFlow");

		Map<String,Float> reqCountMap = new HashMap<String,Float>();
		for(SchRotationDeptReq req : reqs){
			if(req!=null){
				BigDecimal reqNumBig = req.getReqNum();
				if(reqNumBig!=null){
					Float reqNum = reqNumBig.floatValue();

					//标准科室flow
					String relRecordFlow = req.getRelRecordFlow();

					//标准科室
					SchRotationDept dept = deptMap.get(relRecordFlow);
					if(dept!=null){
						//用组合flow和标准科室id去绑定以便于result获取
						String standardGroupFlow = dept.getGroupFlow();
						String standardDeptId = dept.getStandardDeptId();

						//计算缩减
						if(selDeptMap!=null){
							SchDoctorDept selDept = selDeptMap.get(standardGroupFlow+standardDeptId);
							if(selDept!=null){
								String reqMonth = dept.getSchMonth();
								String reductionMonth = selDept.getSchMonth();

								try {
									Float reqMonthF = Float.valueOf(reqMonth);
									Float reductionMonthF = Float.valueOf(reductionMonth);

									if(reqMonthF!=0 && reductionMonthF!=0){
										Float reductionPer = reductionMonthF/reqMonthF;

										reqNum*=reductionPer;

										if(reqNum>0 && reqNum<1){
											reqNum = 1f;
										}

										reqNum = (float)Math.round(reqNum);
									}
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}

						//计算总完成数
						setVal4MapStrFloat(reqCountMap,TOTAL,reqNum);
						//要求绑定至标准科室
						setVal4MapStrFloat(reqCountMap,standardGroupFlow+standardDeptId,reqNum);

						//标准科室下各类型要求数
						String recTypeId = req.getRecTypeId();
						setVal4MapStrFloat(reqCountMap,standardGroupFlow+standardDeptId+recTypeId,reqNum);

						//标准科室下各子项要求数
						String itemId = req.getItemId();
						if(StringUtil.isNotBlank(itemId)){
							setVal4MapStrFloat(reqCountMap,standardGroupFlow+standardDeptId+recTypeId+itemId,reqNum);

							//为该标准科室各类型绑定自己的子项
							if(itemsMap!=null){
								List<String> items = itemsMap.get(standardGroupFlow+standardDeptId+recTypeId);
								if(items==null){
									items = new ArrayList<String>();
									itemsMap.put(standardGroupFlow+standardDeptId+recTypeId,items);
								}
								items.add(itemId);
							}
						}
					}
				}
			}
		}

		return reqCountMap;
	}

	/**
	 * 基本数据包装方法END
	 */

	/**
	 * 百分比及各数据统计计算START
	 */

	/**
	 * 进一步包装数据,以便调用计算方法
	 * @param doctorFlow	医师流水号
	 * @param processFlow    计划或方案流水号
	 * @param recTypeId	登记类型
	 * @param itemId	子项id
	 * @return
	 */
	@Override
	public Map<String,Object> getRecProgressIn(
			String doctorFlow,
			String processFlow,
			String recTypeId,
			String itemId
	){

		if(!StringUtil.isNotBlank(doctorFlow)){
			return null;
		}

		//读取医师信息以获取方案
		ResDoctor doctor = resDoctorBiz.readDoctor(doctorFlow);
		if(doctor==null){
			return null;
		}
		String medicineTypeId="";
		//读取医师信息以获取方案
		SysUser user = userBiz.readSysUser(doctorFlow);
		if(user==null){
			return null;
		}else{
			medicineTypeId=user.getMedicineTypeId();
		}

		//获取方案
		String rotationFlow = doctor.getRotationFlow();
		if(!StringUtil.isNotBlank(rotationFlow)){
			return null;
		}

		//获取该医师的计划
		List<SchArrangeResultExt> resultExts = getResults(doctorFlow,null,processFlow);
		if(resultExts==null || resultExts.isEmpty()){
			return null;
		}

		//获取该方案的标准科室
		List<SchRotationDept> depts = null;
		String schRotationDeptFlow = null;

		//process为空的情况下不支持更精确的统计
		if(!StringUtil.isNotBlank(processFlow)){
			recTypeId = null;
			itemId = null;

			depts = getDept(null,rotationFlow,null);
		}else{
			SchArrangeResultExt resultExt = resultExts.get(0);
			if(resultExt!=null){
				String standardGroupFlow = resultExt.getStandardGroupFlow();
				String standardDeptId = resultExt.getStandardDeptId();

				SchRotationDept dept = rotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standardGroupFlow,standardDeptId);
				if(dept!=null){
					depts = new ArrayList<SchRotationDept>();
					depts.add(dept);

					schRotationDeptFlow = dept.getRecordFlow();
				}
			}
		}

		if(depts==null){
			return null;
		}

		//统计登记的数据
		List<ResRec> recs = getRecs(doctorFlow, processFlow, recTypeId, itemId);
		Map<String,Float> recCountMap = getRecCount(recs);

		//选科表数据,西医作为缩减调整
		List<SchDoctorDept> selDepts = getSelDepts(doctorFlow,rotationFlow);

		//获取所有登记数据类型 并且后台开通的数据类型
		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))
					&& PdUtil.findChineseOrWestern(medicineTypeId,regType.getId())){
				recTypeIds.add(regType.getId());
			}
		}
		//要求统计,统计的要求为缩减后的要求,同时拿出子项列表
		List<SchRotationDeptReq> reqs = getReqs(rotationFlow,schRotationDeptFlow,recTypeId,itemId,recTypeIds);
		Map<String,List<String>> itemsMap = new HashMap<String, List<String>>();
		Map<String,Float> reqCountMap = getReqCount(depts,reqs,selDepts,itemsMap);


		return getRecProgress(true,0,resultExts,recCountMap,reqCountMap,recTypeIds,itemsMap);
	}

	public Map<String,Object> getRecProgress(
			boolean haveNoReqFull,
			int format,
			List<SchArrangeResultExt> resultExts,
			Map<String,Float> recCountMap,
			Map<String,Float> reqCountMap,
			List<String> recTypeIds,
			Map<String,List<String>> itemIdsMap
	){
		//格式化参数不可小于0
		format = format<0?0:format;

		if(resultExts==null || resultExts.isEmpty()){
			return null;
		}

		//为各科室计算比例
		Map<String,Object> resultMap = new HashMap<String,Object>();
		for(SchArrangeResultExt sarExt : resultExts){//可变列表,可由process/result/rotationDept构成
			ResDoctorSchProcess process = sarExt.getProcess();
			if(process==null){
				continue;
			}

			String resultFlow = sarExt.getResultFlow();
			String processFlow = process.getProcessFlow();

			String standardGroupFlow = sarExt.getStandardGroupFlow();
			String standardDeptId = sarExt.getStandardDeptId();

			//科室总完成数
			Float deptFin = getFdefaultZero(recCountMap,processFlow);
			setVal4PerMap(resultMap,processFlow+FINISHED,deptFin,0);
			setVal4PerMap(resultMap,resultFlow+FINISHED,deptFin,0);

			//科室总要求数
			Float deptReq = getFdefaultZero(reqCountMap,standardGroupFlow+standardDeptId);
			setVal4PerMap(resultMap,processFlow+REQ_NUM,deptReq,0);
			setVal4PerMap(resultMap,resultFlow+REQ_NUM,deptReq,0);

			//如果没有需要计算的登记类型直接忽略后面计算
			if(recTypeIds==null || recTypeIds.isEmpty()){
				continue;
			}

			//科室总比例
			Float deptPer = 0f;

			for(String recTypeId : recTypeIds){//getPerResult
				//类型总比例
				Float typePer = 0f;

				//类型完成数
				Float typeFin = getFdefaultZero(recCountMap,processFlow+recTypeId);
				setVal4PerMap(resultMap,processFlow+recTypeId+FINISHED,typeFin,0);
				setVal4PerMap(resultMap,resultFlow+recTypeId+FINISHED,typeFin,0);

				//类型要求
				Float typeReq = getFdefaultZero(reqCountMap,standardGroupFlow+standardDeptId+recTypeId);
				setVal4PerMap(resultMap,processFlow+recTypeId+REQ_NUM,typeReq,0);
				setVal4PerMap(resultMap,resultFlow+recTypeId+REQ_NUM,typeReq,0);
				//类型要求占总要求比例
				Float typeReqPer = getPerResult(typeReq,deptReq);

				//该类型的子项列表
				List<String> itemIds = null;
				if(itemIdsMap!=null){
					itemIds =  itemIdsMap.get(standardGroupFlow+standardDeptId+recTypeId);
				}
				//如果没有子项列表则按无子项方法计算
				if(itemIds!=null){
					for(String itemId : itemIds){
						//子项完成数
						Float itemFin = getFdefaultZero(recCountMap,processFlow+recTypeId+itemId);
						setVal4PerMap(resultMap,processFlow+recTypeId+itemId+FINISHED,itemFin,0);
						setVal4PerMap(resultMap,resultFlow+recTypeId+itemId+FINISHED,itemFin,0);

						//子项要求数
						Float itemReq = getFdefaultZero(reqCountMap,standardGroupFlow+standardDeptId+recTypeId+itemId);
						setVal4PerMap(resultMap,processFlow+recTypeId+itemId+REQ_NUM,itemReq,0);
						setVal4PerMap(resultMap,resultFlow+recTypeId+itemId+REQ_NUM,itemReq,0);

						//子项要求占类型要求比例的比例
						Float itemReqPer = getPerResult(itemReq,typeReq);

						//子项比例
						Float itemPer = getPerResult(itemFin,itemReq);
						if(haveNoReqFull && itemReq==0){
							itemPer = 1f;
						}
						setVal4PerMap(resultMap,processFlow+recTypeId+itemId,itemPer*100,format);
						setVal4PerMap(resultMap,resultFlow+recTypeId+itemId,itemPer*100,format);
						typePer+=(itemReqPer*itemPer);
					}
				}else{
					if(typeReq>0){
						typePer = getPerResult(typeFin,typeReq);
					}
				}

				if(haveNoReqFull && typeReq==0){
					typePer = 1f;
				}

				setVal4PerMap(resultMap,processFlow+recTypeId,typePer*100,format);
				setVal4PerMap(resultMap,resultFlow+recTypeId,typePer*100,format);

				deptPer+=(typePer*typeReqPer);
			}

			if(haveNoReqFull && deptReq==0){
				deptPer = 1f;
			}

			setVal4PerMap(resultMap,processFlow,deptPer*100,format);
			setVal4PerMap(resultMap,resultFlow,deptPer*100,format);
		}

		return resultMap;
	}
	/**
	 * 百分比及各数据统计计算END
	 */
	/*********************************************************
	 * jsres&product百分比算法,兼容缩减END
	 *************************************************************/


	@Override
	public List<ResAppeal> searResAppeal(String processFlow,
										 String recTypeId, String userFlow, String itemId) {
		ResAppealExample example = new ResAppealExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow)
				.andRecTypeIdEqualTo(recTypeId).andOperUserFlowEqualTo(userFlow).andItemIdEqualTo(itemId);
		return resAppealMapper.selectByExample(example);
	}
	@Override
	public int searResRecWan(String processFlow, String recTypeId,
							 String itemId) {
		ResRecExample example = new ResRecExample();
		com.pinde.sci.model.mo.ResRecExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(processFlow)) {
			criteria.andProcessFlowEqualTo(processFlow);
		}
		if (StringUtil.isNotBlank(recTypeId)) {
			criteria.andRecTypeIdEqualTo(recTypeId);
		}
		criteria.andItemIdEqualTo(itemId);
		return resRecMapper.countByExample(example);
	}
	@Override
	public List<ResRec> searchInfo(ResRec resRec, List<String> operUserFlows,List<String> orgFlowList) {
		ResRecExample example = new ResRecExample();
		com.pinde.sci.model.mo.ResRecExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(resRec.getRecTypeId())){
			criteria.andRecTypeIdEqualTo(resRec.getRecTypeId());
		}
		if(StringUtil.isNotBlank(resRec.getSchDeptFlow())){
			criteria.andSchDeptFlowEqualTo(resRec.getSchDeptFlow());
		}
		if(operUserFlows!=null&&!operUserFlows.isEmpty()){
			criteria.andOperUserFlowIn(operUserFlows);
		}
		if(StringUtil.isNotBlank(resRec.getOperUserName())){
			criteria.andOperUserNameLike("%"+resRec.getOperUserName()+"%");
		}
		if(StringUtil.isNotBlank(resRec.getOrgFlow())){
			criteria.andOrgFlowEqualTo(resRec.getOrgFlow());
		}
		if(StringUtil.isNotBlank(resRec.getOrgName())){
			criteria.andOrgNameLike("%"+resRec.getOrgName()+"%");
		}
		if(orgFlowList!=null && !orgFlowList.isEmpty()){
			criteria.andOrgFlowIn(orgFlowList);
		}
		example.setOrderByClause("create_time desc");
		return resRecMapper.selectByExampleWithBLOBs(example);
	}


	/**
	 * 根据老师获取未审核数
	 * @param teacherUserFlow
	 * @param typeList
	 * @return
	 */
	@Override
	public int notAudited(String teacherUserFlow, String startDate, String endDate, List<String> typeList) {
		return resRecExtMapper.notAudited(teacherUserFlow,startDate,endDate,typeList);
	}
	/**
	 * 根据老师获取已审核数
	 * @param teacherUserFlow
	 * @param typeList
	 * @return
	 */
	@Override
	public int isNotAudited(String teacherUserFlow, String startDate, String endDate, List<String> typeList) {
		return resRecExtMapper.isNotAudited(teacherUserFlow,startDate,endDate,typeList);
	}
	/**
	 * 根据老师获取未审核数
	 * @return
	 */
	@Override
	public List<Map<String,String>> notAuditedMaps(Map<String, Object> paramMap) {
		return resRecExtMapper.notAuditedMaps(paramMap);
	}
	/**
	 * 根据老师获取已审核数
	 * @return
	 */
	@Override
	public List<Map<String,String>> isNotAuditedMaps(Map<String, Object> paramMap) {
		return resRecExtMapper.isNotAuditedMaps(paramMap);
	}
	@Override
	public List<ResRec> searchProssFlowRec(String teacherUserFlow) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("teacherUserFlow",teacherUserFlow);
		return resRecExtMapper.searchProssFlowRec(map);
	}
	@Override
	public List<Map<String, String>> parseImageXml(String content) throws DocumentException {
		Document document=DocumentHelper.parseText(content);
		Element elem=document.getRootElement();
		List<Element> ec = elem.elements();
		List<Map<String, String>> imageList=new ArrayList<Map<String,String>>();
		for (Element element : ec) {
			Map<String,String> imageMap=new HashMap<String, String>();
			String imageFlow=element.attributeValue("imageFlow");
			imageMap.put("imageFlow", imageFlow);
			List<Element> items=element.elements();
			for (Element item : items) {
				String itemName=item.getName();
				String itemText=item.getTextTrim();
				imageMap.put(itemName, itemText);
			}
			imageList.add(imageMap);
		}
		return imageList;
	}

	/*************表单xml生成与解析START***************/
	/**
	 * 根据方案查找对应的表单
	 * @param rotationFlow
	 * @param recordFlow
	 *@param recTypeId @return
	 */
	private String getRecFormByCfg(String rotationFlow, String recordFlow, String recTypeId){
		String recForm = InitConfig.getSysCfg("res_form_category_"+rotationFlow+"_"+recordFlow+"_"+recTypeId);

		if(!StringUtil.isNotBlank(recForm)){
			recForm = InitConfig.getSysCfg("res_form_category_"+rotationFlow);
		}
		if(!StringUtil.isNotBlank(recForm)){
			recForm = InitConfig.getSysCfg("res_form_category");
		}

		if(!StringUtil.isNotBlank(recForm)){
			recForm = GlobalConstant.RES_FORM_PRODUCT;
		}

		return recForm;
	}

	/**
	 * 获取表单信息
	 * @param recForm 使用的表单 (如:product、sczyfy...)
	 * @param recTypeId 登记类型ID  (ResRecTypeEnum.id  如：CaseRegistry...)
	 * @param ver 使用的版本号
	 * @return
	 */
	private IrbSingleForm findTheRecForm(String recForm,String recTypeId,String ver){
		if(!StringUtil.isNotBlank(recTypeId)){
			return null;
		}

		if(!StringUtil.isNotBlank(recForm)){
			recForm = GlobalConstant.RES_FORM_PRODUCT;
		}

		IrbFormRequestUtil resFormRequestUtil = InitConfig.resFormRequestUtil;
		if(resFormRequestUtil!=null){
			if(!StringUtil.isNotBlank(ver)){
				Map<String,String> verMap = resFormRequestUtil.getVersionMap();
				if(verMap!=null){
					ver = verMap.get(recForm+"_"+recTypeId);
				}
			}
			if(!StringUtil.isNotBlank(ver)){
				ver = GlobalConstant.RES_FORM_PRODUCT_VER;
			}

			Map<String,Map<String,IrbSingleForm>> singleFormMapMap = resFormRequestUtil.getFormMap();
			if(singleFormMapMap!=null){
				Map<String,IrbSingleForm> singleFormMap = singleFormMapMap.get(recTypeId);
				if(singleFormMap!=null){
					IrbSingleForm singleForm = singleFormMap.get(recForm+"_"+ver);

					if(singleForm == null){
						singleForm = singleFormMap.get(GlobalConstant.RES_FORM_PRODUCT+"_"+GlobalConstant.RES_FORM_PRODUCT_VER);
					}

					if(singleForm == null){
						throw new RuntimeException("未发现表单 模版类型:"+recForm+",表单类型:"+ResRecTypeEnum.getNameById(recTypeId)+",版本号:"+ver);
					}else{
						return singleForm;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 获取该表单jsp地址
	 * @param recTypeId 登记类型
	 * @param currVer 版本号
	 * @param recForm 使用的表单
	 * @param type
	 * @return
	 */
	@Override
	public String getFormPath(String recTypeId, String currVer, String recForm, String type,String medicineTypeId){
		if(StringUtil.isBlank(medicineTypeId))
			medicineTypeId="";
		if(StringUtil.isNotBlank(recTypeId)){
			IrbSingleForm singleForm = findTheRecForm(recForm,recTypeId,currVer);
			if(singleForm!=null){
				String jspPath = singleForm.getJspPath();
				if(StringUtil.isNotBlank(jspPath)){
					jspPath = MessageFormat.format(jspPath,singleForm.getProductType(),singleForm.getVersion(),type,medicineTypeId);
				}
				return jspPath;
			}
		}
		return "error/404";
	}

	/**
	 * 获取表单路径
	 * @param rotationFlow 方案流水
	 * @param recTypeId 登记类型
	 * @param currVer 版本号
	 * @param recForm 使用的表单
	 * @param type
	 * @return
	 */
	@Override
	public String getFormPath(String rotationFlow, String recTypeId, String currVer, String recForm, String type,String medicineTypeId,String recordFlow){
		// 1 如果传入使用的表单为空，则根据 "res_form_category_方案流水号" 获取表单，如没有，则根据 "res_form_category" 获取，还没有，则获取全局配置GlobalConstant.RES_FORM_PRODUCT
		if(!StringUtil.isNotBlank(recForm)){
			recForm = getRecFormByCfg(rotationFlow,recordFlow,recTypeId);
		}
		return getFormPath(recTypeId,currVer,recForm,type,medicineTypeId);
	}

	private String getRecContent(HttpServletRequest req,IrbSingleForm singleForm,String recContent){
		if(singleForm==null){
			throw new RuntimeException("表单信息获取出错...");
		}
		//表单类型获取验证
		String recTypeId = req.getParameter("recTypeId");
		if(StringUtil.isNotBlank(recTypeId)){
			throw new RuntimeException("缺少必要参数:recTypeId");
		}

		//角色标识获取验证
		String roleFlag = req.getParameter("roleFlag");
		if(StringUtil.isNotBlank(roleFlag)){
			throw new RuntimeException("缺少必要参数:roleFlag");
		}

		//角色是否留痕
		String roleMark = req.getParameter("roleMark");

		try {
			Document dom = null;
			Element root = null;
			if(StringUtil.isNotBlank(recContent)){
				dom = DocumentHelper.parseText(recContent);
			}
			if(dom == null){
				dom = DocumentHelper.createDocument();
				root = dom.addElement(recTypeId);
			}else{
				root = dom.getRootElement();
			}
			if(GlobalConstant.FLAG_Y.equals(roleMark)){
				String roleEleName = roleFlag+recTypeId;
				Element roleEle = root.element(roleEleName);
				if(roleEle!=null){
					roleEle.detach();
				}
				root = root.addElement(roleEleName);
			}

			List<Element> items = singleForm.getItemList();
			if(items!=null && !items.isEmpty()){
				for(Element item : items){
					String name = item.getName();
					if("itemgroup".equals(name.toLowerCase())){

					}else{

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recContent;
	}

	@Override
	public List<ResRec> searchByRecWithBLOBsByMap4Hb(Map paramMap) {
		return resRecExtMapper.searchByRecWithBLOBsByMap4Hb(paramMap);
	}

	/****************************高******校******管******理******员******角******色************************************************/

	@Override
	public List<Map<String,String>> searchInfoForUni(ResRec resRec,ResDoctor doctor) {
		if(StringUtil.isBlank(doctor.getWorkOrgId())&&StringUtil.isBlank(doctor.getWorkOrgName())){
			return null;
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resRec", resRec);
		paramMap.put("resDoctor", doctor);
		return resRecExtMapper.searchInfoForUni(paramMap);
	}

	@Override
	public List<ResRec> searchDelayInfoForUni(ResRec resRec, ResDoctor doctor) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resRec", resRec);
		paramMap.put("resDoctor", doctor);
		return resRecExtMapper.searchDelayInfoForUni(paramMap);
	}

	@Override
	public List<Map<String, String>> searchInfo2(ResRec resRec, ResDoctor doctor, List<String> userFlowList, List<String> orgFlowList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("resRec", resRec);
		paramMap.put("resDoctor", doctor);
		paramMap.put("userFlowList", userFlowList);
		paramMap.put("orgFlowList", orgFlowList);
		return resRecExtMapper.searchInfo2(paramMap);
	}

	@Override
	public String getTeaAuditRecContent(String formName, List<Element> list, HttpServletRequest req, Element rootEle) {
		if(rootEle==null)
			rootEle = DocumentHelper.createElement(formName);
		if(list !=null && list.size()>0 && rootEle!=null){
			for(Element itemEle : list){
				String multiple = itemEle.attributeValue("multiple");
				//xml中的节点是否是文件
				String isFile = itemEle.attributeValue("isFile");
				boolean isMultipart = JspFormUtil.isMultipart(req);
				if(GlobalConstant.FLAG_Y.equals(isFile)&&isMultipart)
				{
					String name = itemEle.attributeValue("name");
					Map<String , String[]> dataMap=getParameterMap(req,rootEle.getName(),name);

					Element element = rootEle.element(name);
					boolean f=false;
					if(element==null) {
						f=true;
						element = DocumentHelper.createElement(name);
					}
					Element elementFlow = DocumentHelper.createElement(name+"_Flow");
					Element elementName = DocumentHelper.createElement(name+"_FileName");
					String []flows=dataMap.get(name);
					String flow="";
					if (flows != null && flows.length > 0) {
						flow = StringUtils.join(flows, ",");
						elementFlow.setText(flow);
						rootEle.add(elementFlow);
						String []names=dataMap.get(name+"_FileName");
						String fn="";
						if (names != null && names.length > 0) {
							fn = StringUtils.join(names, ",");
						}
						element.setText(fn);

						element.addAttribute("flow",flow);
					}
					if(f)
						rootEle.add(element);
				}
			}
		}
		return rootEle.asXML();
	}

	@Override
	public int orgBatchAuditDoctorInfo(Map<String, Object> param) {
		return resRecExtMapper.orgBatchAuditDoctorInfo(param);
	}

	@Override
	public List<ResRec> getAllAfterSummary(String id) {
		if(StringUtil.isNotBlank(id))
		{
			ResRecExample example=new ResRecExample();
			example.createCriteria().andRecTypeIdEqualTo(id);
			return resRecMapper.selectByExampleWithBLOBs(example);
		}
		return null;
	}

	@Override
	public List<SchDoctorDept> searchReductionDept(String userFlow, String rotationFlow, String secondRotationFlow) {

		SchDoctorDeptExample doctorDeptExample = new SchDoctorDeptExample();
		List<String> flows=new ArrayList<>();
		if(StringUtil.isNotBlank(rotationFlow))
		{
			flows.add(rotationFlow);
		}
		if(StringUtil.isNotBlank(secondRotationFlow))
		{
			flows.add(secondRotationFlow);
		}
		doctorDeptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(userFlow).andRotationFlowIn(flows);
		return docDeptMapper.selectByExample(doctorDeptExample);
	}

	@Override
	public List<SchDoctorDept> searchReductionDept2(List<ResDoctor> resDoctorList) {
		if(CollectionUtils.isEmpty(resDoctorList)) {
			return Collections.emptyList();
		}

		List<String> doctorFlowList = new ArrayList<>();
		List<List<String>> rotationFlowListList = new ArrayList<>();
		for (ResDoctor resDoctor : resDoctorList) {
			doctorFlowList.add(resDoctor.getDoctorFlow());
			List<String> rotationFlowList = new ArrayList<>();
			if(org.apache.commons.lang3.StringUtils.isNotEmpty(resDoctor.getRotationFlow())) {
				rotationFlowList.add(resDoctor.getRotationFlow());
			}
			if(org.apache.commons.lang3.StringUtils.isNotEmpty(resDoctor.getSecondRotationFlow())) {
				rotationFlowList.add(resDoctor.getSecondRotationFlow());
			}
			rotationFlowListList.add(rotationFlowList);
		}

		return docDeptMapper.selectListByRotationFLowListList(doctorFlowList, rotationFlowListList);
	}

	@Override
	public ResStandardDeptPer getStandardDeptPer(String doctorFlow, String recordFlow, String rotationFlow) {

		ResStandardDeptPer per=null;
		ResStandardDeptPerExample example=new ResStandardDeptPerExample();
		example.createCriteria().andDoctorFlowEqualTo(doctorFlow).andSchRotationDeptFlowEqualTo(recordFlow)
				.andRotationFlowEqualTo(rotationFlow)
				.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("create_time desc");
		List<ResStandardDeptPer> pers= perMapper.selectByExample(example);
		if(pers!=null&&!pers.isEmpty())
		{
			per=pers.get(0);
		}
		return per;
	}

	@Override
	public List<TeachingActivityInfo> searchJoinActivityByProcessFlow(String processFlow) {

		return  resRecExtMapper.searchJoinActivityByProcessFlow(processFlow);
	}

	@Override
	public List<TeachingActivityInfo> searchJoinActivityByProcessFlownotScore(String processFlow) {
		return  resRecExtMapper.searchJoinActivityByProcessFlow2(processFlow);
	}

	@Override
	public List<TeachingActivityInfo> searchJoinActivityByStrandDeptFlow(String doctorFlow, String recordFlow) {
		return resRecExtMapper.searchJoinActivityByStrandDeptFlow(doctorFlow,recordFlow);
	}

	@Override
	public List<TeachingActivityInfo> searchJoinActivityByStrandDeptFlowAndNotScore(String doctorFlow, String recordFlow) {
		return resRecExtMapper.searchJoinActivityByStrandDeptFlowAndNotScore(doctorFlow,recordFlow);
	}

	@Override
	public List<Map<String, Object>> queryJoinActivityAndEditList(String doctorFlow, String recTypeId, String processFlow) {
//		List<Map<String, Object>> mapList = campaignRegistryExtMapper.queryJoinActivityAndEditList(doctorFlow, recTypeId, processFlow);
//		mapList.addAll(caseRegistryExtMapper.queryJoinActivityAndEditList(doctorFlow, recTypeId, processFlow));
//		mapList.addAll(diseaseRegistryExtMapper.queryJoinActivityAndEditList(doctorFlow, recTypeId, processFlow));
//		mapList.addAll(languageRegistryExtMapper.queryJoinActivityAndEditList(doctorFlow, recTypeId, processFlow));
//		mapList.addAll(operationRegistryExtMapper.queryJoinActivityAndEditList(doctorFlow, recTypeId, processFlow));
//		mapList.addAll(skillRegistryExtMapper.queryJoinActivityAndEditList(doctorFlow, recTypeId, processFlow));
//		return mapList;
		return resRecExtMapper.queryJoinActivityAndEditList(doctorFlow,recTypeId,processFlow);
	}

	@Override
	public List<ResRec> searchByRecWithBLOBsByRotationDeptFlows(String operUserFlow, List<String> schRotationDeptFlows) {
		if(schRotationDeptFlows!=null&&schRotationDeptFlows.size()>0) {
			ResRecExample example = new ResRecExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andOperUserFlowEqualTo(operUserFlow).andSchRotationDeptFlowIn(schRotationDeptFlows);
			example.setOrderByClause("OPER_TIME");
			return resRecMapper.selectByExampleWithBLOBs(example);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> searchRecActivity(Map<String, Object> paramMap) {
		return resRecMapper.searchRecActivity(paramMap);
	}

	/**
	 * @param paramMap
	 * @Department：研发部
	 * @Description 查询审核信息
	 * @Author fengxf
	 * @Date 2020/11/18
	 */
	@Override
	public List<Map<String, String>> searchAuditedDataList(Map<String, Object> paramMap) {
		return resRecExtMapper.searchAuditedDataList(paramMap);
	}

	/**
	 * @Department：研发部
	 * @Description 查询审核信息拆分成每个学员
	 * @Author xieyongheng
	 * @Date 2023/4/12
	 */
	@Override
	public List<Map<String, String>> searchAuditedDataListByOper(Map<String, Object> paramMap) {
		return resRecExtMapper.searchAuditedDataListByOper(paramMap);
	}

	@Override
	public List<Map<String, String>> searchNurseAssEvaluate(Map<String, Object> paramMap) {
		return resRecExtMapper.searchNurseAssEvaluate(paramMap);
	}

	@Override
	public List<ResRec>  searchRecAndActivityByProcess(Map<String, Object> paramMap) {
//		List<ResRec> resRecList;
//		resRecList = campaignRegistryExtMapper.searchRecAndActivityByProcess(paramMap);
//		resRecList.addAll(caseRegistryExtMapper.searchRecAndActivityByProcess(paramMap));
//		resRecList.addAll(diseaseRegistryExtMapper.searchRecAndActivityByProcess(paramMap));
//		resRecList.addAll(languageRegistryExtMapper.searchRecAndActivityByProcess(paramMap));
//		resRecList.addAll(operationRegistryExtMapper.searchRecAndActivityByProcess(paramMap));
//		resRecList.addAll(skillRegistryExtMapper.searchRecAndActivityByProcess(paramMap));
//		return resRecList;
		return resRecMapper.searchRecAndActivityByProcess(paramMap);
	}

//	private Element buildTheItem(String id,String name,String value){
//
//	}

//	@Override
//	public int saveTheRecForm(HttpServletRequest req){
//		int fail = GlobalConstant.ZERO_LINE;
//		int sucess = GlobalConstant.ONE_LINE;
//
//		//角色标识获取验证
//		String roleFlag = req.getParameter("roleFlag");
//		if(StringUtil.isNotBlank(roleFlag)){
//			throw new RuntimeException("缺少必要参数:roleFlag");
//		}
//
//		//表单类型获取验证
//		String recTypeId = req.getParameter("recTypeId");
//		if(StringUtil.isNotBlank(recTypeId)){
//			throw new RuntimeException("缺少必要参数:recTypeId");
//		}
//
//		//过程标识获取验证
//		String processFlow = req.getParameter("processFlow");
//		if(StringUtil.isNotBlank(processFlow)){
//			throw new RuntimeException("缺少必要参数:processFlow");
//		}
//
//		//表单所属获取验证
//		String operUserFlow = req.getParameter("operUserFlow");
//		if(StringUtil.isNotBlank(operUserFlow)){
//			throw new RuntimeException("缺少必要参数:operUserFlow");
//		}
//
//		//表单标识获取验证
//		String recFlow = req.getParameter("recFlow");
//
//		ResRec rec = null;
//		IrbSingleForm singleForm = null;//表单信息
//		String recContent = null;//xml信息
//
//		if(!StringUtil.isNotBlank(recFlow)){
//			rec = new ResRec();
//
//			//读取表单所属的用户信息
//			SysUser user = userBiz.findByFlow(operUserFlow);
//			if(user==null){
//				throw new RuntimeException("读取用户信息失败...");
//			}
//
//			//读取表单所属的医师信息
//			ResDoctor doctor = resDoctorBiz.readDoctor(operUserFlow);
//			if(doctor==null){
//				throw new RuntimeException("读取医师信息失败...");
//			}
//
//			//读取过程信息
//			ResDoctorSchProcess process = processBiz.read(processFlow);
//			if(process==null){
//				throw new RuntimeException("读取过程信息失败...");
//			}
//
//			//获取轮转计划信息
//			String resultFlow = process.getSchResultFlow();
//			if(StringUtil.isNotBlank(resultFlow)){
//				SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
//				if(result!=null){
//					String standardGroupFlow = result.getStandardGroupFlow();//标准组别标识
//					String standardDeptId = result.getStandardDeptId();//标准科室标识
//					if(StringUtil.isNotBlank(standardGroupFlow) && StringUtil.isNotBlank(standardDeptId)){
//						SchRotationDept rotationDept = rotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(standardGroupFlow,standardDeptId);
//						if(rotationDept!=null){
//							String recordFlow = rotationDept.getRecordFlow();
//							rec.setSchRotationDeptFlow(recordFlow);
//						}
//					}
//				}
//			}
//
//			//获取表单
//			String recForm = getRecFormByCfg(doctor.getRotationFlow());
//			//获取表单信息
//			singleForm = findTheRecForm(recForm,recTypeId,null);
//			if(singleForm==null){
//				throw new RuntimeException("获取表单信息失败...");
//			}
//			rec.setRecVersion(singleForm.getVersion());
//			rec.setRecForm(singleForm.getProductType());
//
//			rec.setOperUserFlow(operUserFlow);
//			rec.setOperUserName(user.getUserName());
//			rec.setOperTime(DateUtil.getCurrDateTime());
//
//			rec.setOrgFlow(doctor.getOrgFlow());
//			rec.setOrgName(doctor.getOrgName());
//
//			rec.setDeptFlow(process.getDeptFlow());
//			rec.setDeptName(process.getDeptName());
//			rec.setSchDeptFlow(process.getSchDeptFlow());
//			rec.setSchDeptName(process.getSchDeptName());
//
//			String recTypeName = ResRecTypeEnum.getNameById(recTypeId);
//			rec.setRecTypeId(recTypeId);
//			rec.setRecTypeName(recTypeName);
//
//			rec.setProcessFlow(processFlow);
//		}else{
//			rec = readResRec(recFlow);
//			if(rec!=null){
//				throw new RuntimeException("读取表单记录失败...");
//			}
//
//			recContent = rec.getRecContent();
//			String recForm = rec.getRecForm();
//			String recVer = rec.getRecVersion();
//			singleForm = findTheRecForm(recForm,recTypeId,recVer);
//			if(singleForm==null){
//				throw new RuntimeException("获取表单信息失败...");
//			}
//		}
//
//		String itemId = req.getParameter("itemId");
//		String itemName = req.getParameter("itemName");
//		rec.setItemId(itemId);
//		rec.setItemName(itemName);
//
//		String currTime = DateUtil.getCurrDateTime();
//		SysUser currUser = GlobalContext.getCurrentUser();
//		String statusId = null;
//
//		//老师审核状态
//		statusId = req.getParameter("auditStatusId");
//		if(statusId!=null){
//			rec.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
//			rec.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
//			rec.setAuditTime(currTime);
//			rec.setAuditUserFlow(currUser.getUserFlow());
//			rec.setAuditUserName(currUser.getUserName());
//		}
//
//		//科主任审核状态
//		statusId = req.getParameter("headAuditStatusId");
//		if(statusId!=null){
//			rec.setHeadAuditStatusId(RecStatusEnum.HeadAuditY.getId());
//			rec.setHeadAuditStatusName(RecStatusEnum.HeadAuditY.getName());
//			rec.setHeadAuditTime(currTime);
//			rec.setHeadAuditUserFlow(currUser.getUserFlow());
//			rec.setHeadAuditUserName(currUser.getUserName());
//		}
//
//		//基地主任审核状态
//		statusId = req.getParameter("managerAuditStatusId");
//		if(statusId!=null){
//			rec.setManagerAuditStatusId(RecStatusEnum.ManagerAuditY.getId());
//			rec.setManagerAuditStatusName(RecStatusEnum.ManagerAuditY.getName());
//			rec.setManagerAuditTime(currTime);
//			rec.setManagerAuditUserFlow(currUser.getUserFlow());
//			rec.setManagerAuditUserName(currUser.getUserName());
//		}
//
//		//医院管理员审核状态
//		statusId = req.getParameter("adminAuditStatusId");
//		if(statusId!=null){
//			rec.setAdminAuditStatusId(RecStatusEnum.AdminAuditY.getId());
//			rec.setAdminAuditStatusName(RecStatusEnum.AdminAuditY.getName());
//			rec.setAdminAuditTime(currTime);
//			rec.setAdminAuditUserFlow(currUser.getUserFlow());
//			rec.setAdminAuditUserName(currUser.getUserName());
//		}
//
//		recContent = getRecContent(req,singleForm,recContent);
//
//		return sucess;
//	}
	/*************表单xml生成与解析END*****************/


}
