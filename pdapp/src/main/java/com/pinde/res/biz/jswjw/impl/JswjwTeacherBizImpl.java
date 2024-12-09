package com.pinde.res.biz.jswjw.impl;


import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.ResRecTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IJswjwTeacherBiz;
import com.pinde.res.biz.jswjw.IResDoctorProcessBiz;
import com.pinde.res.dao.jswjw.ext.*;
import com.pinde.res.dao.stdp.ext.StdpResDoctorExtMapper;
import com.pinde.sci.dao.base.*;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JswjwTeacherBizImpl implements IJswjwTeacherBiz{

	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private SysUserExtMapper sysUserExtMapper;
	@Resource
	private ResRecMapper recMapper;
//	@Autowired
//	private ResRecCampaignRegistryMapper campaignRegistryMapper;
//	@Autowired
//	private ResRecCaseRegistryMapper caseRegistryMapper;
//	@Autowired
//	private ResRecDiseaseRegistryMapper diseaseRegistryMapper;
//	@Autowired
//	private ResRecLanguageRegistryMapper languageRegistryMapper;
//	@Autowired
//	private ResRecOperationRegistryMapper operationRegistryMapper;
//	@Autowired
//	private ResRecSkillRegistryMapper skillRegistryMapper;
	@Resource
	private ResDoctorMapper doctorMapper;
	@Resource
	private SchRotationDeptExtMapper schRotationDeptExtMapper;
	@Resource
	private SchRotationDeptMapper schRotationDeptMapper;
	@Resource
	private SchRotationDeptReqMapper schRotationDeptReqMapper;
	@Resource
	private SchArrangeResultMapper resultMapper;
	@Resource
	private SysCfgMapper cfgMapper;
	@Resource
	private SchRotationMapper rotationMapper;
	@Resource
	private SchRotationGroupMapper rotationGroupMapper;
	@Resource
	private SchArrangeResultExtMapper resultExtMapper;
	@Resource
	private SchDoctorDeptMapper doctorDeptMapper;
	@Resource
	private SysLogMapper logMapper;
	@Resource
	private ResDoctorSchProcessMapper processMapper;
	@Resource
	private SysDeptMapper sysDeptMapper;
	@Resource
	private ResAssessCfgMapper assessCfgMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;
	@Autowired
	private StdpResDoctorExtMapper docExtMapper;
	@Autowired
	private ResDoctorRecruitMapper recruitMapper;
	@Autowired
	private SysDeptExtMapper sysDeptExtMapper;
	@Autowired
	private SysOrgMapper orgMapper;
	@Autowired
	private ResLectureScanRegistMapper lectureScanRegistMapper;
	@Autowired
	private ResLectureInfoMapper lectureInfoMapper;
	@Autowired
	private ResLectureEvaDetailMapper lectureEvaDetailMapper;
	@Autowired
	private ResDoctorSchProcessExtMapper resDoctorProcessExtMapper;
	@Autowired
	private ResScoreMapper scoreMapper;

	@Override
	public int schDoctorSchProcessDistinctQuery(
			String teacherUserFlow, String deptFlow) {
		return resDoctorProcessExtMapper.schDoctorSchProcessDistinctQuery(teacherUserFlow,deptFlow);
	}
	@Override
	public int schDoctorSchProcessTeacherQuery(
			String teacherUserFlow, String deptFlow) {
		return resDoctorProcessExtMapper.schDoctorSchProcessTeacherQuery(teacherUserFlow,deptFlow);
	}
	@Override
	public int schDoctorSchProcessWaitingExamineQuery(
			String teacherUserFlow) {
		return resDoctorProcessExtMapper.schDoctorSchProcessWaitingExamineQuery(teacherUserFlow);
	}

	@Override
	public List<Map<String,String>>  schDoctorSchProcessQuery(
			Map<String, String> map) {
		List<Map<String,String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.schDoctorSchProcessQuery(map);
		return resDoctorSchProcesseList;
	}
	@Override
	public List<ResRec> searchRecByProcess(String processFlow, String doctorFlow) {
//		ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//		campaignRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		campaignRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExample(campaignRegistryExample);
//		List<ResRec> resRecList = campaignRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList());
//		ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//		caseRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		caseRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExample(caseRegistryExample);
//		resRecList.addAll(caseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//		diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		diseaseRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExample(diseaseRegistryExample);
//		resRecList.addAll(diseaseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//		languageRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		languageRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExample(languageRegistryExample);
//		resRecList.addAll(languageRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//		operationRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		operationRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExample(operationRegistryExample);
//		resRecList.addAll(operationRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//		skillRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
//		skillRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExample(skillRegistryExample);
//		resRecList.addAll(skillRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		return resRecList;
		ResRecExample example = new ResRecExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow);
		example.setOrderByClause("OPER_TIME");
		return recMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public List<ResRec> searchRecByProcessAndRecType(String processFlow,String doctorFlow,String recType,String biaoJi) {

//		ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//		ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//		ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//		ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//		ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//		ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//		ResRecCampaignRegistryExample.Criteria campaignRegistryCriteria = campaignRegistryExample.createCriteria()
//				.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow).andRecTypeIdEqualTo(recType);
//		ResRecCaseRegistryExample.Criteria caseRegistryCriteria = caseRegistryExample.createCriteria()
//				.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow).andRecTypeIdEqualTo(recType);
//		ResRecDiseaseRegistryExample.Criteria diseaseRegistryCriteria = diseaseRegistryExample.createCriteria()
//				.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow).andRecTypeIdEqualTo(recType);
//		ResRecLanguageRegistryExample.Criteria languageRegistryCriteria = languageRegistryExample.createCriteria()
//				.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow).andRecTypeIdEqualTo(recType);
//		ResRecOperationRegistryExample.Criteria operationRegistryCriteria = operationRegistryExample.createCriteria()
//				.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow).andRecTypeIdEqualTo(recType);
//		ResRecSkillRegistryExample.Criteria skillRegistryCriteria = skillRegistryExample.createCriteria()
//				.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow).andRecTypeIdEqualTo(recType);

		ResRecExample example = new ResRecExample();
	 ResRecExample.Criteria criteria=	example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(doctorFlow).andRecTypeIdEqualTo(recType);
		if(StringUtil.isNotBlank(biaoJi)) {
//			campaignRegistryCriteria.andAuditStatusIdIsNull();
//			caseRegistryCriteria.andAuditStatusIdIsNull();
//			diseaseRegistryCriteria.andAuditStatusIdIsNull();
//			languageRegistryCriteria.andAuditStatusIdIsNull();
//			operationRegistryCriteria.andAuditStatusIdIsNull();
//			skillRegistryCriteria.andAuditStatusIdIsNull();
			criteria.andAuditStatusIdIsNull();
		}
//		campaignRegistryExample.setOrderByClause("OPER_TIME");
//		caseRegistryExample.setOrderByClause("OPER_TIME");
//		diseaseRegistryExample.setOrderByClause("OPER_TIME");
//		languageRegistryExample.setOrderByClause("OPER_TIME");
//		operationRegistryExample.setOrderByClause("OPER_TIME");
//		skillRegistryExample.setOrderByClause("OPER_TIME");
		example.setOrderByClause("OPER_TIME");

//		List<ResRec> resRecList = null;
//		if (recType.equals(RegistryTypeEnum.CampaignRegistry.getId())) {
//			List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExampleWithBLOBs(campaignRegistryExample);
//			resRecList = campaignRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recType.equals(RegistryTypeEnum.CaseRegistry.getId())) {
//			List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExampleWithBLOBs(caseRegistryExample);
//			resRecList = caseRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recType.equals(RegistryTypeEnum.DiseaseRegistry.getId())) {
//			List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExampleWithBLOBs(diseaseRegistryExample);
//			resRecList = diseaseRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recType.equals(RegistryTypeEnum.LanguageTeachingResearch.getId())) {
//			List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExampleWithBLOBs(languageRegistryExample);
//			resRecList = languageRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recType.equals(RegistryTypeEnum.OperationRegistry.getId())) {
//			List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExampleWithBLOBs(operationRegistryExample);
//			resRecList = operationRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		} else if (recType.equals(RegistryTypeEnum.SkillRegistry.getId())) {
//			List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExampleWithBLOBs(skillRegistryExample);
//			resRecList = skillRegistryList.stream().map(e -> {
//				ResRec resRec = new ResRec();
//				BeanUtils.copyProperties(e, resRec);
//				return resRec;
//			}).collect(Collectors.toList());
//		}
//		return resRecList;
		return recMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public SchRotationGroup readSchRotationGroup(String groupFlow){
		return rotationGroupMapper.selectByPrimaryKey(groupFlow);
	}

	@Override
	public SchArrangeResult readSchArrangeResult(String resultFlow) {
		return resultMapper.selectByPrimaryKey(resultFlow);
	}
	@Override
	public SchRotationDept searchGroupFlowAndStandardDeptIdQuery(
			String groupFlow, String standardDeptId) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criter=example.createCriteria();
        criter.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(groupFlow)) {
			criter.andGroupFlowEqualTo(groupFlow);
		}
		if (StringUtil.isNotBlank(standardDeptId)) {
			criter.andStandardDeptIdEqualTo(standardDeptId);
		}
		List<SchRotationDept> rotationDeptList = schRotationDeptMapper.selectByExample(example);
		SchRotationDept rotationDept = null;
		if(rotationDeptList!=null && rotationDeptList.size()>0){
			rotationDept = rotationDeptList.get(0);
		}
		return rotationDept;
	}
	@Override
	public Map<String,Object> parseRecContent(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
                Element afterEvaluation = rootElement.element(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER + com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				if(afterEvaluation==null){
                    afterEvaluation = rootElement.element(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD + com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				}
				if(afterEvaluation==null){
                    afterEvaluation = rootElement.element(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER + com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				}
				List<Element> elements = null;
				if(afterEvaluation!=null){
					elements = afterEvaluation.elements();
				}else{
					elements = rootElement.elements();
				}
				for(Element element : elements) {

					if ("imageList".equals(element.getName())) {

						List<Element> images = element.elements();
						if (images != null) {
							List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
							for (Element ele : images) {
								Map<String, String> map = new HashMap<String, String>();
								map.put("imageFlow", ele.attributeValue("imageFlow"));
								map.put("imageUrl", ele.elementText("imageUrl"));
								map.put("thumbUrl", ele.elementText("thumbUrl"));
								dataList.add(map);
							}
							formDataMap.put(element.getName(), dataList);
						}
					} else {
						List<Node> valueNodes = element.selectNodes("value");
						if (valueNodes != null && !valueNodes.isEmpty()) {
							String value = "";
							for (Node node : valueNodes) {
								if (StringUtil.isNotBlank(value)) {
									value += ",";
								}
								value += node.getText();
							}
							formDataMap.put(element.getName(), value);
						} else {
							String isSelect = element.attributeValue("id");
							if (StringUtil.isNotBlank(isSelect)) {
								formDataMap.put(element.getName() + "_id", isSelect);
								formDataMap.put(element.getName(), element.getText());
							} else {
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
	public List<ResRec> searchRecByProcessWithBLOBs(List<String> recTypeIds,String processFlow,String operUserFlow){

//		ResRecCampaignRegistryExample campaignRegistryExample = new ResRecCampaignRegistryExample();
//		ResRecCaseRegistryExample caseRegistryExample = new ResRecCaseRegistryExample();
//		ResRecDiseaseRegistryExample diseaseRegistryExample = new ResRecDiseaseRegistryExample();
//		ResRecLanguageRegistryExample languageRegistryExample = new ResRecLanguageRegistryExample();
//		ResRecOperationRegistryExample operationRegistryExample = new ResRecOperationRegistryExample();
//		ResRecSkillRegistryExample skillRegistryExample = new ResRecSkillRegistryExample();
//		campaignRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//				.andRecTypeIdIn(recTypeIds).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//		caseRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//				.andRecTypeIdIn(recTypeIds).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//		diseaseRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//				.andRecTypeIdIn(recTypeIds).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//		languageRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//				.andRecTypeIdIn(recTypeIds).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//		operationRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//				.andRecTypeIdIn(recTypeIds).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//		skillRegistryExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
//				.andRecTypeIdIn(recTypeIds).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
//		campaignRegistryExample.setOrderByClause("OPER_TIME");
//		caseRegistryExample.setOrderByClause("OPER_TIME");
//		diseaseRegistryExample.setOrderByClause("OPER_TIME");
//		languageRegistryExample.setOrderByClause("OPER_TIME");
//		operationRegistryExample.setOrderByClause("OPER_TIME");
//		skillRegistryExample.setOrderByClause("OPER_TIME");
//		List<ResRecCampaignRegistry> campaignRegistryList = campaignRegistryMapper.selectByExampleWithBLOBs(campaignRegistryExample);
//		List<ResRec> resRecList = campaignRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList());
//		List<ResRecCaseRegistry> caseRegistryList = caseRegistryMapper.selectByExampleWithBLOBs(caseRegistryExample);
//		resRecList.addAll(caseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecDiseaseRegistry> diseaseRegistryList = diseaseRegistryMapper.selectByExampleWithBLOBs(diseaseRegistryExample);
//		resRecList.addAll(diseaseRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecLanguageRegistry> languageRegistryList = languageRegistryMapper.selectByExampleWithBLOBs(languageRegistryExample);
//		resRecList.addAll(languageRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecOperationRegistry> operationRegistryList = operationRegistryMapper.selectByExampleWithBLOBs(operationRegistryExample);
//		resRecList.addAll(operationRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		List<ResRecSkillRegistry> skillRegistryList = skillRegistryMapper.selectByExampleWithBLOBs(skillRegistryExample);
//		resRecList.addAll(skillRegistryList.stream().map(e -> {
//			ResRec resRec = new ResRec();
//			BeanUtils.copyProperties(e, resRec);
//			return resRec;
//		}).collect(Collectors.toList()));
//		return resRecList;

		ResRecExample example = new ResRecExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andRecTypeIdIn(recTypeIds).andProcessFlowEqualTo(processFlow).andOperUserFlowEqualTo(operUserFlow);
		example.setOrderByClause("OPER_TIME");
		return recMapper.selectByExampleWithBLOBs(example);
	}
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;

	@Autowired
	private IJswjwBiz jswjwBiz;
	@Override
	public ResRec searResRecZhuZhi(String formFileName, String recFlow,
								   String operUserFlow, String roleFlag, String processFlow,String recordFlow,String userFlow,
								   HttpServletRequest req) {
		operUserFlow = StringUtil.defaultIfEmpty(operUserFlow,userFlow);
		SysUser sysUser=jswjwBiz.readSysUser(operUserFlow);
		ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.getNameById(formFileName);
		String currDate=DateUtil.getCurrDateTime();
		ResRec rec=new ResRec();
		rec.setRecFlow(recFlow);
		rec.setOrgFlow(sysUser.getOrgFlow());
		rec.setOrgName(sysUser.getOrgName());
		rec.setDeptFlow(resDoctorSchProcess.getDeptFlow());
		rec.setDeptName(resDoctorSchProcess.getDeptName());
		rec.setRecTypeId(formFileName);
		rec.setRecTypeName(recTypeId);
		rec.setRecVersion("2.0");
		rec.setOperTime(currDate);
		rec.setOperUserFlow(operUserFlow);
		rec.setOperUserName(sysUser.getUserName());
		rec.setProcessFlow(processFlow);
		rec.setSchRotationDeptFlow(recordFlow);
		rec.setRecForm("jsst");
		String recContent=getXmlByRequest(req,formFileName);

        String medicineTypeId="";
        if(sysUser!=null) medicineTypeId=sysUser.getMedicineTypeId();
        rec.setMedicineType(medicineTypeId);
		rec.setRecContent(recContent);
		return rec;
	}

	@Override
	public List<Map<String, String>> schDoctorSchProcessInfoQuery(Map<String, String> schArrangeResultMap) {
		List<Map<String,String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.schDoctorSchProcessInfoQuery(schArrangeResultMap);
		return resDoctorSchProcesseList;
	}

	@Override
	public List<Map<String, String>> schDoctorSchProcessEvalList(Map<String, String> schArrangeResultMap) {
		List<Map<String,String>>  resDoctorSchProcesseList=resDoctorProcessExtMapper.schDoctorSchProcessEvalList(schArrangeResultMap);
		return resDoctorSchProcesseList;
	}

	@Override
	public List<Map<String, Object>> findProcessEvals(Map<String, Object> params) {
		List<Map<String,Object>>  evals=resDoctorProcessExtMapper.findProcessEvals(params);
		return evals;
	}
	@Override
	public List<Map<String, String>> schDoctorSchProcessUserList(Map<String, String> params) {
		List<Map<String,String>>  evals=resDoctorProcessExtMapper.schDoctorSchProcessUserList(params);
		return evals;
	}

	@Override
	public List<TeachingActivityInfo> searchJoinActivityByProcessFlow(String processFlow) {
		return resDoctorProcessExtMapper.searchJoinActivityByProcessFlow(processFlow);
	}

	@Override
	public List<TeachingActivityInfo> searchJoinActivityByProcessFlowNotScore(String processFlow) {
		return resDoctorProcessExtMapper.searchJoinActivityByProcessFlowNotScore(processFlow);
	}

	@Override
	public List<Map<String, Object>> findSkillNoAudit(Map<String, Object> param) {
		return resDoctorProcessExtMapper.findSkillNoAudit(param);
	}

	@Override
	public List<Map<String, String>> schDoctorSchProcessDayAttend(Map<String, String> schArrangeResultMap) {
		return resDoctorProcessExtMapper.schDoctorSchProcessDayAttend(schArrangeResultMap);
	}

	@Override
	public List<Map<String, Object>> dayAttendList(Map<String, Object> param) {
		return resDoctorProcessExtMapper.dayAttendList(param);
	}

	@Override
	public int dayAttendListCount(Map<String, Object> param) {
		return resDoctorProcessExtMapper.dayAttendListCount(param);
	}

	@Override
	public ResScore readScoreByProcessFlow(String processFlow) {
		if(StringUtil.isNotBlank(processFlow)){
			ResScoreExample example=new ResScoreExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andProcessFlowEqualTo(processFlow);
			List<ResScore> list=scoreMapper.selectByExample(example);
			if(list!=null&&list.size()>0){
				return  list.get(0);
			}
		}
		return null;
	}

	//根据request获取这次表单的xml
	private String getXmlByRequest(HttpServletRequest request,String rootName){
		if(request!=null){
			rootName = StringUtil.defaultIfEmpty(rootName,"root");
			Map<String,String[]> paramsMap = request.getParameterMap();
			if(paramsMap!=null && !paramsMap.isEmpty()){
				//创建xmldom对象和根节点
				Document doc = DocumentHelper.createDocument();
				Element root = doc.addElement(rootName);

				for(String key : paramsMap.keySet()){
					String[] vs = paramsMap.get(key);

					String vss = "";

					String idCsVv = request.getParameter(key+"_name");

					if(vs!=null && vs.length>0){
						vss = vs[0];
					}
//					try {
//						if(idCsVv!=null)
//							idCsVv = URLDecoder.decode(idCsVv, "UTF-8") ;
//						if(vss!=null) vss = URLDecoder.decode(vss,"UTF-8") ;
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
					//开始创建xml子节点
					Element currEle = root.addElement(key);
					if(idCsVv==null){
						currEle.setText(vss);
					}else{
						currEle.addAttribute("id",vss);
						currEle.setText(idCsVv);
					}
				}
				return doc.asXML();
			}
		}
		return null;
	}

}  
  