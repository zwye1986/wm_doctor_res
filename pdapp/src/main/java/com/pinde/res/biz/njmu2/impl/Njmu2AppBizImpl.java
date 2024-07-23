package com.pinde.res.biz.njmu2.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.pinde.app.common.GlobalUtil;
import com.pinde.res.enums.njmu2.RegistryTypeEnum;
import com.pinde.res.dao.jswjw.ext.SchArrangeResultExtMapper;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.sql.visitor.functions.Isnull;
import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu2.INjmu2AppBiz;
import com.pinde.res.biz.njmu2.INjmu2StudentBiz;
import com.pinde.res.dao.stdp.ext.StdpResDoctorSchProcessExtMapper;
import com.pinde.res.dao.stdp.ext.StdpResRecExtMapper;
import com.pinde.res.dao.stdp.ext.StdpSchArrangeResultExtMapper;
import com.pinde.res.enums.njmu2.ResAssessTypeEnum;
import com.pinde.res.enums.njmu2.ResRecTypeEnum;
import com.pinde.res.enums.njmu2.SigninTypeEnum;
import com.pinde.sci.model.mo.SysUserExample.Criteria;

@Service
@Transactional(rollbackFor=Exception.class)
public class Njmu2AppBizImpl implements INjmu2AppBiz{
	@Autowired
	private ResDoctorExtMapper doctorExtMapper;
	@Resource
	private SysUserMapper userMapper;
	@Resource
	private ResDoctorMapper doctorMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;
	@Resource
	private SysCfgMapper cfgMapper;
	@Resource
	private StdpSchArrangeResultExtMapper njmu2ResultExtMapper;
	@Resource
	private StdpResDoctorSchProcessExtMapper njmu2ProcessExtMapper;
	@Resource
	private ResRecMapper recMapper;
	@Resource
	private ResAssessCfgMapper assessCfgMapper;
	@Resource
	private StdpResRecExtMapper recExtMapper;
	@Resource
	private SchRotationDeptMapper rotationDeptMapper;
	@Resource
	private SchRotationDeptReqMapper reqMapper;
	@Resource
	private INjmu2StudentBiz njmu2studentBiz;
	@Resource
	private ResSigninMapper signinMapper;
	@Resource
	private ResDoctorSchProcessMapper processMapper;
	@Autowired
	private SchArrangeResultExtMapper resultMapper;
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private SchDeptMapper schDeptMapper;
	@Autowired
	private SchArrangeResultMapper arrangeResultMapper;
	@Autowired
	private SysDictMapper sysDictMapper;
	/*************************************功能配置START*******************************************************/
	//配置id对应的value
	private static Map<String,String> idValCfg;
	//老师展示内容配置
	private static Map<String,Map<String,String>> teacherDataListContentCfg;
	//保存子项名称的节点需要同时保存子项id
	private static List<String> itemIdAttrCfg;
	//需要计算和的表单
	private static Map<String,List<String>> sumItemMap;
	static{
		idValCfg = new HashMap<String, String>();
		idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"status1","亲自");
		idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"status2","一助");
		idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"status3","二助");
		idValCfg.put(ResRecTypeEnum.SkillRegistry.getId()+"status4","参观");
		idValCfg.put(ResRecTypeEnum.TeachRecordRegistry.getId()+"teachType1","讲课");
		idValCfg.put(ResRecTypeEnum.TeachRecordRegistry.getId()+"teachType2","示教");
		idValCfg.put(ResRecTypeEnum.TeachRecordRegistry.getId()+"teachType3","讨论");
		idValCfg.put(ResRecTypeEnum.TeachRecordRegistry.getId()+"teachType4","教学查房");
		idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation1","优");
		idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation2","良");
		idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation3","中");
		idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation4","合格");
		idValCfg.put(ResRecTypeEnum.AfterSummary.getId()+"internshipEvaluation5","不合格");
		
		teacherDataListContentCfg = new HashMap<String, Map<String,String>>();
		Map<String,String> vierCfgMap = new LinkedHashMap<String, String>();
		vierCfgMap.put("hospitalNumbers","病案号");
		vierCfgMap.put("diseaseName","病名");
		teacherDataListContentCfg.put(ResRecTypeEnum.CaseRegistry.getId(),vierCfgMap);
		vierCfgMap = new LinkedHashMap<String, String>();
		vierCfgMap.put("activeTitle","讲座题目");
		teacherDataListContentCfg.put(ResRecTypeEnum.CampaignRegistry.getId(),vierCfgMap);
		vierCfgMap = new LinkedHashMap<String, String>();
		vierCfgMap.put("caseNo","病历号");
		vierCfgMap.put("caseName","病名");
		teacherDataListContentCfg.put(ResRecTypeEnum.Grave.getId(),vierCfgMap);
		vierCfgMap = new LinkedHashMap<String, String>();
		vierCfgMap.put("content","实习主要内容");
		teacherDataListContentCfg.put(ResRecTypeEnum.Internship.getId(),vierCfgMap);
		vierCfgMap = new LinkedHashMap<String, String>();
		vierCfgMap.put("caseNo","病历号");
		vierCfgMap.put("name","病名");
		teacherDataListContentCfg.put(ResRecTypeEnum.ManageBedRegistry.getId(),vierCfgMap);
		vierCfgMap = new LinkedHashMap<String, String>();
		vierCfgMap.put("skillName","操作名称");
		vierCfgMap.put("status","操作方式");
		teacherDataListContentCfg.put(ResRecTypeEnum.SkillRegistry.getId(),vierCfgMap);
		vierCfgMap = new LinkedHashMap<String, String>();
		vierCfgMap.put("teacher","教师");
		vierCfgMap.put("teachType","教学形式");
		vierCfgMap.put("teachDetail","教学内容");
		teacherDataListContentCfg.put(ResRecTypeEnum.TeachRecordRegistry.getId(),vierCfgMap);
		
		itemIdAttrCfg = new ArrayList<String>();
		itemIdAttrCfg.add(ResRecTypeEnum.SkillRegistry.getId()+"skillName");
		
		sumItemMap = new HashMap<String, List<String>>();
		List<String> nodeNames = new ArrayList<String>();
		nodeNames.add("theoryTest");
		nodeNames.add("skillTest");
		sumItemMap.put(ResRecTypeEnum.AfterSummary.getId(),nodeNames);
	}
	/*************************************功能配置END*******************************************************/
	
	//根据用户名获取用户信息
	@Override
	public SysUser getUserByCode(String userCode)
	{
		SysUserExample sysUserExample=new SysUserExample();
		Criteria criteria=sysUserExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserCodeEqualTo(userCode);
		List<SysUser> sysUserList = userMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}		
		return null;
				
	}
	
	//读取一个用户的医师信息
	@Override
	public ResDoctor readResDoctor(String userFlow) {
		return doctorMapper.selectByPrimaryKey(userFlow); 
	}
	
	//获取用户拥有的角色列表
	@Override
	public List<SysUserRole> getSysUserRole(String userFlow){
		SysUserRoleExample example = new SysUserRoleExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
		.andUserFlowEqualTo(userFlow);
		return userRoleMapper.selectByExample(example);
	}
	
	//获取系统配置信息
	@Override
	public String getCfgByCode(String code){
		if(StringUtil.isNotBlank(code)){
			String v= GlobalUtil.getLocalCfgMap().get(code);
			if(StringUtil.isNotBlank(v))
				return v;
			SysCfg cfg = cfgMapper.selectByPrimaryKey(code);
			if(cfg!=null){
				String val = cfg.getCfgValue();
				if(!StringUtil.isNotBlank(val)){
					val = cfg.getCfgBigValue();
				}
				return val;
			}
		}
		return null;
	}
	
	
	//获取轮转计划日期区间
	@Override
	public Map<String,Object> getDocResultArea(String doctorFlow){
		if(StringUtil.isNotBlank(doctorFlow)){
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",doctorFlow);
			return njmu2ResultExtMapper.getDocResultArea(paramMap);
		}
		return null;
	}
	
	//获取当前轮转状态的日期区间
	@Override
	public Map<String,Object> getDocProcessArea(String doctorFlow){
		if(StringUtil.isNotBlank(doctorFlow)){
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",doctorFlow);
			return njmu2ProcessExtMapper.getDocProcessArea(paramMap);
		}
		return null;
	}
	
	//读取一条登记信息
	@Override
	public ResRec getRecByRecFlow(String recFlow){
		if(StringUtil.isNotBlank(recFlow)){
			return recMapper.selectByPrimaryKey(recFlow);
		}
		return null;
	}
	
	//读取一条登记信息
	@Override
	public ResRec getRecByRecType(String processFlow,String recTypeId){
		if(StringUtil.isNotBlank(processFlow) && StringUtil.isNotBlank(recTypeId)){
			ResRecExample example = new ResRecExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andProcessFlowEqualTo(processFlow).andRecTypeIdEqualTo(recTypeId);
			List<ResRec> recs = recMapper.selectByExampleWithBLOBs(example);
			if(recs!=null && !recs.isEmpty()){
				return recs.get(0);
			}
		}
		return null;
	}
	
	//解析登记信息的xml
	@Override
	public Map<String,String> parseRecContent(String recContent){
		if(StringUtil.isNotBlank(recContent)){
			try {
				Document doc = DocumentHelper.parseText(recContent);
				if(doc!=null){
					Element root = doc.getRootElement();
					if(root!=null){
						List<Element> elements = root.elements();
						if(elements!=null && !elements.isEmpty()){
							Map<String,String> formDataMap = new HashMap<String, String>();
							
							for(Element element : elements){
								String eleName = element.getName();
								
								List<Node> valueNodes = element.selectNodes("value");
								
								if(valueNodes != null && !valueNodes.isEmpty()){
									String value = "";
									for(Node node : valueNodes){
										if(StringUtil.isNotBlank(value)){
											value+=",";
										}
										value += node.getText();
									}
									formDataMap.put(eleName,value);
								}else {
									
									String isSelect = element.attributeValue("id");
									
									if(StringUtil.isNotBlank(isSelect)){
										formDataMap.put(eleName+"_id",isSelect);
										formDataMap.put(eleName,element.getText());
									}else{
										formDataMap.put(eleName,element.getText());
									}
								}
							}
							return formDataMap;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//获取评分模板
	@Override
	public ResAssessCfg getGradeTemplate(String cfgCode){
		if(StringUtil.isNotBlank(cfgCode)){
			ResAssessCfgExample example = new ResAssessCfgExample();
			com.pinde.sci.model.mo.ResAssessCfgExample.Criteria criteria = example.createCriteria()
					.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andCfgCodeIdEqualTo(cfgCode);
			
			List<ResAssessCfg> assessList = assessCfgMapper.selectByExampleWithBLOBs(example);
			
			if(assessList!=null && !assessList.isEmpty()){
				return assessList.get(0);
			}
		}
		return null;
	}
	
	//解析评分模板
	@Override
	public List<Map<String,Object>> parseAssessCfg(String content){
		if(StringUtil.isNotBlank(content)){
			try {
				Document dom = DocumentHelper.parseText(content);
				if(dom!=null){
					Element root = dom.getRootElement();
					if(root!=null){
						//获取根节点下的所有title节点
						List<Element> titles = root.elements();
						if(titles!=null && !titles.isEmpty()){
							List<Map<String,Object>> dataMaps = new ArrayList<Map<String,Object>>();
							for(Element i : titles){
								Map<String,Object> dataMap = new HashMap<String, Object>();
								//获取title对象的所有属性,属性名为key,属性值为value
								putAttrToMap(i,dataMap);
								List<Element> items = i.elements();
								if(items!=null && !items.isEmpty()){
									List<Map<String,Object>> itemMaps = new ArrayList<Map<String,Object>>();
									for(Element si : items){
										Map<String,Object> itemMap = new HashMap<String, Object>();
										//获取节点的所有属性包装进Map
										putAttrToMap(si,itemMap);
										//以节点名称为key将节点文本包装进map
										putTextToMap(si,itemMap);
										itemMaps.add(itemMap);
									}
									dataMap.put("items",itemMaps);
								}
								dataMaps.add(dataMap);
							}
							return dataMaps;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//获取节点的所有属性包装进Map
	private <T> void putAttrToMap(Element e,Map<String,T> map){
		if(e!=null && map!=null){
			List<?> attrs = e.attributes();
			if(attrs!=null && !attrs.isEmpty()){
				int attrSize = attrs.size();
				for(int index = 0 ; index < attrSize ; index++){
					Attribute attr = (Attribute)attrs.get(index);
					if(attr!=null){
						String name = attr.getName();
						String val = attr.getValue();
						map.put(name,(T)val);
					}
				}
			}
		}
	}
	
	//以节点名称为key将节点文本包装进map
	private <T> void putTextToMap(Element e,Map<String,T> map){
		if(e!=null && map!=null){
			List<Element> es = e.elements();
			if(es!=null && !es.isEmpty()){
				for(Element se : es){
					String eleName = se.getName();
					String eleText = se.getText();
					map.put(eleName,(T)eleText);
				}
			}
		}
	}
	
	//解析评分数据
	@Override
	public List<Map<String,String>> parseDocGradeXml(String content){
		if(StringUtil.isNotBlank(content)){
			List<Map<String,String>> gradeMaps = parseGradeXml(content);
			if(gradeMaps!=null && !gradeMaps.isEmpty()){
				List<Map<String,String>> gms = new ArrayList<Map<String,String>>();
				//重新包装解析的评分数据
				for(Map<String,String> grameMap : gradeMaps){
					String id = grameMap.get("assessId");
					String score = grameMap.get("score");
					String lostReason = grameMap.get("lostReason");
					
					Map<String,String> gm1 = new HashMap<String, String>();
					gm1.put("inputId",id+"_score");
					gm1.put("value",score);
					
					Map<String,String> gm2 = new HashMap<String, String>();
					gm2.put("inputId",id+"_lostReason");
					gm2.put("value",lostReason);
					
					gms.add(gm1);
					gms.add(gm2);
				}
				return gms;
			}
		}
		return null;
	}
	
	//讲评分数据解析成基础的对象集合格式
	private List<Map<String,String>> parseGradeXml(String content){
		if(StringUtil.isNotBlank(content)){
			try {
				Document dom = DocumentHelper.parseText(content);
				if(dom!=null){
					Element root = dom.getRootElement();
					if(root!=null){
						List<Element> grades = root.elements();
						if(grades!=null && !grades.isEmpty()){
							List<Map<String,String>> gradeMaps = new ArrayList<Map<String,String>>();
							for(Element grade : grades){
								Map<String,String> gradeMap = new HashMap<String, String>();
								putAttrToMap(grade,gradeMap);
								putTextToMap(grade,gradeMap);
								gradeMaps.add(gradeMap);
							}
							return gradeMaps;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	//根据条件查询并且解析rec数据
	@Override
	public List<Map<String,Object>> getParsedRecs(Map<String,Object> paramMap){
		if(paramMap!=null && !paramMap.isEmpty()){
			List<ResRec> recs = getRecs(paramMap);
			if(recs!=null && !recs.isEmpty()){
				List<Map<String,Object>> recMaps = new ArrayList<Map<String,Object>>();
				for(ResRec rr : recs){
					Map<String,Object> recMap = new HashMap<String,Object>();
					String content = rr.getRecContent();
					if(StringUtil.isNotBlank(content)){
						Map<String,String> dataMap = parseRecContent(content);
						putAll(recMap,dataMap);
					}
					recMap.put("rec",rr);
					recMaps.add(recMap);
				}
				return recMaps;
			}
		}
		return null;
	}
	
	//教师端的登记数据列表
	@Override
	public List<Map<String,Object>> getTeacherParsedRecs(Map<String,Object> paramMap){
		if(paramMap!=null && !paramMap.isEmpty()){
			List<ResRec>  recs = getRecs(paramMap);
			if(recs!=null && !recs.isEmpty()){
				List<Map<String,Object>> recMaps = new ArrayList<Map<String,Object>>();
				for(ResRec rr : recs){
					Map<String,Object> recMap = new HashMap<String,Object>();
					String content = rr.getRecContent();
					if(StringUtil.isNotBlank(content)){
						Map<String,String> dataMap = parseRecContent(content);
						
						String recTypeId = rr.getRecTypeId();
						String viewContent = groupContent(recTypeId,dataMap,":",",");
						recMap.put("content",viewContent);
					}
					recMap.put("rec",rr);
					recMaps.add(recMap);
				}
				return recMaps;
			}
		}
		return null;
	}
	
	//组接content
	private String groupContent(String recTypeId,Map<String,String> dataMap,String mapStr,String separator){
		String content = "";
		if(StringUtil.isNotBlank(recTypeId) && dataMap!=null){
			//获取配置
			Map<String,String> cfgMap = teacherDataListContentCfg.get(recTypeId);
			if(cfgMap!=null && !cfgMap.isEmpty()){
				for(String key : cfgMap.keySet()){
					String title = cfgMap.get(key);
					String value = dataMap.get(key);
					value = StringUtil.defaultString(value);
					if(StringUtil.isNotBlank(content)){
						content+=separator;
					}
					content+=(title+mapStr+value);
				}
			}
		}
		return content;
	}
	
	//将一个map put进另一个map
	private void putAll(Map<String,Object> map,Map<String,String> subMap){
		if(map!=null && subMap!=null){
			for(String k : subMap.keySet()){
				Object v = subMap.get(k);
				if(map.containsKey(k)){
					k += "_n";
				}
				map.put(k,v);
			}
		}
	}
	
	//根据条件查询rec
	private List<ResRec> getRecs(Map<String,Object> paramMap){
		if(paramMap!=null && !paramMap.isEmpty()){
			String operUserFlow = (String)paramMap.get("operUserFlow");
			if(StringUtil.isNotBlank(operUserFlow)){
				return recExtMapper.getRecs(paramMap);
			}
		}
		return null;
	}
	
	//根据排班流水获取当前科室的要求
	@Override
	public List<Map<String,Object>> getReqByResult(Map<String,Object> paramMap){
		if(paramMap!=null && !paramMap.isEmpty()){
			
			String relRecordFlow = (String)paramMap.get("relRecordFlow");
			
			if(StringUtil.isNotBlank(relRecordFlow)){
				SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
				
				com.pinde.sci.model.mo.SchRotationDeptReqExample.Criteria criteria = reqExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRelRecordFlowEqualTo(relRecordFlow);
				
				String recTypeId = (String)paramMap.get("recTypeId");
				if(StringUtil.isNotBlank(recTypeId)){
					criteria.andRecTypeIdEqualTo(recTypeId);
				}
				
				String itemName = (String)paramMap.get("itemName");
				if(StringUtil.isNotBlank(itemName)){
					criteria.andItemNameLike("%"+itemName+"%");
				}
				
				List<SchRotationDeptReq> reqs = reqMapper.selectByExample(reqExample);
				if(reqs!=null && !reqs.isEmpty()){
					List<Map<String,Object>> reqMaps = new ArrayList<Map<String,Object>>();
					for(SchRotationDeptReq req : reqs){
						Map<String,Object> reqMap = new HashMap<String, Object>();
						reqMap.put("req",req);
						reqMaps.add(reqMap);
					}
					return reqMaps;
				}
			}
		}
		return null;
	}
	
	//根据标准组和标准科室获取一条标准规则
	@Override
	public SchRotationDept getRotationDeptByResult(String standardGroupFlow,String standardDeptId){
		if(StringUtil.isNotBlank(standardGroupFlow) && StringUtil.isNotBlank(standardDeptId)){
			SchRotationDeptExample example = new SchRotationDeptExample();
			
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andGroupFlowEqualTo(standardGroupFlow).andStandardDeptIdEqualTo(standardDeptId);
			
			List<SchRotationDept> rotationDepts = rotationDeptMapper.selectByExample(example);
			if(rotationDepts!=null && !rotationDepts.isEmpty()){
				return rotationDepts.get(0);
			}
		}
		return null;
	}
	
	//编辑一条登记数据
	@Override
	public void editRec(String recFlow,
			String operUserFlow,
			String resultFlow,
			String recTypeId,
			String itemId,
			HttpServletRequest request,String roleId){
		ResRec rec = new ResRec();
		ResDoctorSchProcess writeBackProcess = new ResDoctorSchProcess();
		
		//大字段内容
		String content = "";
		//评分类型
		String assessType = "";
		if(ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId) || ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)){
			//为评分类型赋值
			if(ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)){
				assessType = ResAssessTypeEnum.TeacherAssess.getId();
			}else if(ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)){
				assessType = ResAssessTypeEnum.DeptAssess.getId();
			}
			
			if(StringUtil.isNotBlank(assessType)){
				ResAssessCfg assessCfg = getGradeTemplate(assessType);
				
				if(assessCfg!=null){
					String gradeContent = assessCfg.getCfgBigValue();
					List<Map<String,Object>> assessMaps = parseAssessCfg(gradeContent);
					if(assessMaps!=null && !assessMaps.isEmpty()){
						content = getGradeXml(assessMaps,request);
					}
				}
			}
			
		}else{
			content = getXmlByRequest(request,recTypeId,",",itemId,writeBackProcess,roleId);
		}
		
		boolean isNew = !StringUtil.isNotBlank(recFlow);
		
		if(isNew){
			//生成rec流水号
			recFlow = PkUtil.getUUID();
			//读取医师数据
			if(StringUtil.isNotBlank(operUserFlow)){
				ResDoctor doc = readResDoctor(operUserFlow);
				if(doc!=null){
					rec.setOrgFlow(doc.getOrgFlow());
					rec.setOrgName(doc.getOrgName());
					rec.setOperTime(DateUtil.getCurrDateTime());
					rec.setOperUserFlow(operUserFlow);
					rec.setOperUserName(doc.getDoctorName());
				}
			}

            SysUser user=getUserByFlow(operUserFlow);
            String medicineTypeId="";
            if(user!=null) medicineTypeId=user.getMedicineTypeId();
            rec.setMedicineType(medicineTypeId);
			//读取科室数据
			if(StringUtil.isNotBlank(resultFlow)){
				ResDoctorSchProcess process = njmu2studentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					String processFlow = process.getProcessFlow();
					rec.setProcessFlow(processFlow);
					writeBackProcess.setProcessFlow(processFlow);
				}
				SchArrangeResult result = njmu2studentBiz.searcheDocResult(null,resultFlow);
				if(result!=null){
					rec.setDeptFlow(result.getDeptFlow());
					rec.setDeptName(result.getDeptName());
					rec.setSchDeptFlow(result.getSchDeptFlow());
					rec.setSchDeptName(result.getSchDeptName());
					
					//获取子项信息
					if(StringUtil.isNotBlank(itemId)){
						String standardDeptId = result.getStandardDeptId();
						String standardGroupFlow = result.getStandardGroupFlow();
						SchRotationDept rotationDept = getRotationDeptByResult(standardGroupFlow,standardDeptId);
						if(rotationDept!=null){
							String relRecordFlow = rotationDept.getRecordFlow();
							SchRotationDeptReq req = readReq(null,relRecordFlow,itemId);
							if(req!=null){
								rec.setItemId(itemId);
								rec.setItemName(req.getItemName());
							}
							
							rec.setSchRotationDeptFlow(relRecordFlow);
						}
					}
				}
			}
			//获取表单类型名称
			if(StringUtil.isNotBlank(recTypeId)){
				rec.setRecTypeId(recTypeId);
				rec.setRecTypeName(ResRecTypeEnum.getNameById(recTypeId));
			}
			
			rec.setRecVersion(GlobalConstant.RES_DEFAULT_FORM_VER);
			rec.setRecForm(GlobalConstant.RES_DEFAULT_FORM);
			
			rec.setCreateTime(DateUtil.getCurrDateTime());
			rec.setCreateUserFlow(operUserFlow);
			
			rec.setRecordStatus(GlobalConstant.FLAG_Y);
		}
		
		rec.setRecFlow(recFlow);
		
		rec.setRecContent(content);
		
		rec.setModifyTime(DateUtil.getCurrDateTime());
		rec.setModifyUserFlow(operUserFlow);
		
		//回写分数
		if(writeBackProcess.getSchScore()!=null){
			if(!StringUtil.isNotBlank(writeBackProcess.getProcessFlow())){
				ResDoctorSchProcess process = njmu2studentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					String processFlow = process.getProcessFlow();
					writeBackProcess.setProcessFlow(processFlow);
				}
			}
			processMapper.updateByPrimaryKeySelective(writeBackProcess);
		}
		
		if(isNew){
			recMapper.insertSelective(rec);
		}else{
			recMapper.updateByPrimaryKeySelective(rec);
		}
	}
	
	//计算总分
	private Float countSum(HttpServletRequest request,String rootName){
		if(request==null){
			return null;
		}
		if(!StringUtil.isNotBlank(rootName)){
			return null;
		}
		List<String> nodeNames = sumItemMap.get(rootName);
		if(nodeNames==null || nodeNames.isEmpty()){
			return null;
		}
		
		Float sum = 0f;
		for(String nodeName : nodeNames){
			String v = request.getParameter(nodeName);
			try {
				sum+=Float.valueOf(v);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sum;
	}
	
	//根据request获取这次表单的xml
	private String getXmlByRequest(HttpServletRequest request,String rootName,String separator,String itemId,ResDoctorSchProcess writeBackProcess,String roleId){
		if(request!=null){
			separator = separator==null?",":separator;
			rootName = StringUtil.defaultIfEmpty(rootName,"root");
			Map<String,String[]> paramsMap = request.getParameterMap();
			if(paramsMap!=null && !paramsMap.isEmpty()){
				//创建xmldom对象和根节点
				Document doc = DocumentHelper.createDocument();
				Element root = doc.addElement(rootName);
				
				for(String key : paramsMap.keySet()){
					String[] vs = paramsMap.get(key);
					
					String vss = "";
					
					String idCsVv = null;
					
					List<String> vsLs = null;
					if(vs!=null){
						if(vs.length>1){
							vss = StringUtils.join(vs,separator);
							
							vsLs = Arrays.asList(vs);
						}else{
							vss = vs[0];
							
							if(StringUtil.isNotBlank(vss)){
								if(vss.startsWith("[") && vss.endsWith("]")){
									vsLs = (List<String>)JSON.parse(vss);
									Object[] vsOs = vsLs.toArray();
									vss = StringUtils.join(vsOs,separator);
								}
							}
						}
						
						if(vsLs!=null && !vsLs.isEmpty()){
							for(String v : vsLs){
								String iv = idValCfg.get(rootName+key+v);
								if(iv!=null){
									if(idCsVv == null){
										idCsVv = iv;
									}else{
										idCsVv+=(separator+iv);
									}
								}
							}
						}
						
						if(idCsVv==null){
							idCsVv = idValCfg.get(rootName+key+vss);
						}
						
					}
					
					//开始创建xml子节点
					Element currEle = root.addElement(key);
					if(idCsVv==null){
						currEle.setText(vss);
					}else{
						currEle.addAttribute("id",vss);
						currEle.setText(idCsVv);
					}
					
					//该属性包含在子项id配置里则为该节点增加id属性
					if(itemIdAttrCfg.contains(rootName+key)){
						if(!GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(itemId)){
							currEle.addAttribute("id",itemId);
						}
					}
				}
				
				//计算总分方法
				if(StringUtil.isNotBlank(roleId)&&!roleId.equals("Student")) {
					Float totalScore = countSum(request, rootName);
					if (totalScore != null) {
						//将总分放进根节点
						Element totalScoreEle = root.addElement("totalScore");
						totalScoreEle.setText(totalScore.toString());
						//同时回写进process
						writeBackProcess.setSchScore(BigDecimal.valueOf(totalScore));
					}
				}
				return doc.asXML();
			}
		}
		return null;
	}
	
	//根据request获取评分表单的xml
	private String getGradeXml(List<Map<String,Object>> assessMaps,HttpServletRequest request){
		if(request!=null){
			String rootName = "gradeInfo";
			if(assessMaps!=null && !assessMaps.isEmpty()){
				//创建xmldom对象和根节点
				Document doc = DocumentHelper.createDocument();
				Element root = doc.addElement(rootName);
				
				//计算总分
				Float totalScore = 0f;
				//遍历配置获取用户填写的值
				for(Map<String,Object> map : assessMaps){
					if(map!=null){
						//标题内的子项
						List<Map<String,Object>> items = (List<Map<String, Object>>)map.get("items");
						if(items!=null && !items.isEmpty()){
							for(Map<String,Object> item : items){
								String itemId = (String)item.get("id");
								if(StringUtil.isNotBlank(itemId)){
									Element grade = root.addElement("grade");
									grade.addAttribute("assessId",itemId);
									
									//获取分数并统计
									String score = request.getParameter(itemId+"_score");
									Element scoreEle = grade.addElement("score");
									if(StringUtil.isNotBlank(score)){
										scoreEle.setText(score);
										Float scf = Float.valueOf(score);
										totalScore+=scf;
									}
									
									String lostReason = request.getParameter(itemId+"_lostReason");
									Element lostReasonEle = grade.addElement("lostReason");
									if(StringUtil.isNotBlank(lostReason)){
										lostReasonEle.setText(lostReason);
									}
								}
							}
						}
					}
				}
				
				Element totalScoreEle = root.addElement("totalScore");
				totalScoreEle.setText(totalScore.toString());
				
				return doc.asXML();
			}
		}
		return null;
	}
	
	//根据reqFlow读取一条req
	@Override
	public SchRotationDeptReq readReq(String reqFlow,String relRecordFlow,String itemId){
		if(StringUtil.isNotBlank(reqFlow)){
			return reqMapper.selectByPrimaryKey(reqFlow);
		}
		if(StringUtil.isNotBlank(relRecordFlow) && StringUtil.isNotBlank(itemId)){
			SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
			
			reqExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRelRecordFlowEqualTo(relRecordFlow).andItemIdEqualTo(itemId);
			
			List<SchRotationDeptReq> reqs = reqMapper.selectByExample(reqExample);
			
			if(reqs!=null && !reqs.isEmpty()){
				return reqs.get(0);
			}
		}
		return null;
	}
	
	//删除一条rec
	@Override
	public void delRec(String recFlow){
		if(StringUtil.isNotBlank(recFlow)){
			ResRec rec = new ResRec();
			
			rec.setRecFlow(recFlow);
			rec.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			
			recMapper.updateByPrimaryKeySelective(rec);
		}
	}
	
	//验证签到信息并且签到
	@Override
	public boolean signin(String userFlow,String deptFlow,String signinType){
		if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(deptFlow) && StringUtil.isNotBlank(signinType)){
			String currDate = DateUtil.getCurrDate();
			//按月签到或是按日签到
			if(SigninTypeEnum.Month.getId().equals(signinType)){
				currDate = currDate.substring(0,7);
			}
			
			ResSigninExample example = new ResSigninExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andUserFlowEqualTo(userFlow).andSignDateEqualTo(currDate);
//			example.setOrderByClause("SIGN_DATE");
			List<ResSignin> signs = signinMapper.selectByExample(example);
			if(signs==null || signs.isEmpty()){
				ResSignin sign = new ResSignin();
				sign.setRecordFlow(PkUtil.getUUID());
				sign.setUserFlow(userFlow);
				
				//获取轮转信息
				SchArrangeResult result = njmu2studentBiz.searcheDocResult(null,deptFlow);
				if(result!=null){
					sign.setUserName(result.getDoctorName());
					sign.setOrgFlow(result.getOrgFlow());
					sign.setOrgName(result.getOrgName());
					sign.setDeptFlow(result.getDeptFlow());
					sign.setDeptName(result.getDeptName());
					sign.setSchDeptFlow(result.getSchDeptFlow());
					sign.setSchDeptName(result.getSchDeptName());
				}
				
				sign.setSignDate(DateUtil.getCurrDate());
				sign.setSignTypeId(signinType);
				sign.setSignTypeName(SigninTypeEnum.getNameById(signinType));
				
				sign.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				sign.setCreateTime(DateUtil.getCurrDateTime());
				sign.setCreateUserFlow(userFlow);
				sign.setModifyTime(DateUtil.getCurrDateTime());
				
				signinMapper.insertSelective(sign);
			}else{
				return false;
			}
		}
		return true;
	}
	
	//获取该科室最签到记录
	@Override
	public List<ResSignin> getSignin(String userFlow){
		if(StringUtil.isNotBlank(userFlow)){
			ResSigninExample example = new ResSigninExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andUserFlowEqualTo(userFlow);
			example.setOrderByClause("SIGN_DATE DESC");
			return signinMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public SysUser getUserByFlow(String userFlow) {
		SysUserExample sysUserExample=new SysUserExample();
		Criteria criteria=sysUserExample.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andUserFlowEqualTo(userFlow);
		List<SysUser> sysUserList = userMapper.selectByExample(sysUserExample);
		if(sysUserList.size()>0){
			return sysUserList.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String,Object>> searchSign(Map<String,Object> paramMap){
		return doctorExtMapper.searchSign(paramMap);
	}
	@Override
	public List<SysOrg> searchSysOrg() {
		SysOrgExample example=new SysOrgExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgTypeIdEqualTo("Hospital").andOrgFlowNotEqualTo(GlobalConstant.RES_REQ_OTHER_ITEM_ID);
		example.setOrderByClause("ORDINAL");
		return sysOrgMapper.selectByExample(example);
	}
	@Override
	public List<SchDept> searchSchDeptList(String orgFlow) {
		SchDeptExample example = new SchDeptExample();
		example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("ORDINAL ");
		return schDeptMapper.selectByExample(example);
	}
	@Override
	public List<SchArrangeResult> searchArrangeResultByDateAndOrg(String schStartDate,String schEndDate,String orgFlow,String sessionNumber){
		SchArrangeResultExample example = new SchArrangeResultExample();
		SchArrangeResultExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
		if(StringUtil.isNotBlank(schStartDate)){
			criteria.andSchEndDateGreaterThanOrEqualTo(schStartDate);
		}
		if(StringUtil.isNotBlank(schEndDate)){
			criteria.andSchStartDateLessThanOrEqualTo(schEndDate);
		}
		if(StringUtil.isNotBlank(sessionNumber)){
			criteria.andSessionNumberEqualTo(sessionNumber);
		}
		return arrangeResultMapper.selectByExample(example);
	}
	@Override
	public List<ResDoctorSchProcess> searchProcessByDoctor(String doctorFlow){
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andUserFlowEqualTo(doctorFlow);
		return processMapper.selectByExample(example);
	}
	@Override
	public List<SchArrangeResult> searchSchArrangeResultByDoctor(String doctorFlow){
		SchArrangeResultExample example = new SchArrangeResultExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow);
		example.setOrderByClause("SCH_DEPT_ORDER,SCH_START_DATE");
		return arrangeResultMapper.selectByExample(example);
	}
	@Override
	public Map<String,String> getFinishPer(List<SchArrangeResult> arrResultList,String doctorFlow){
		Map<String,String> processMap = new HashMap<String, String>();
		Map<String,SchArrangeResult> proResultMap = new HashMap<String,SchArrangeResult>();
		if(arrResultList!=null&&arrResultList.size()>0){
			for (SchArrangeResult result : arrResultList) {
				String resultFlow = result.getResultFlow();
				if(StringUtil.isNotBlank(resultFlow)){
					ResDoctorSchProcess process = njmu2studentBiz.getProcessByResult(resultFlow);
					if(process!=null){
						String processFlow = process.getProcessFlow();
						processMap.put(resultFlow,process.getProcessFlow());
						processMap.put(processFlow,resultFlow);
						proResultMap.put(processFlow,result);
					}
				}
			}
		}
		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(getCfgByCode("res_registry_type_"+regType.getId()))){
				recTypeIds.add(regType.getId());
			}
		}
		if(recTypeIds==null || recTypeIds.size()<=0){
			return null;
		}

		Map<String,String> finishPerMap = null;
		List<ResRec> recList = searchFinishRec(recTypeIds,doctorFlow);
		Map<String,Integer> recFinishMap = new HashMap<String, Integer>();
		if(recList!=null && recList.size()>0){
			for(ResRec rec : recList){
				String processFlow = rec.getProcessFlow();
				String schDeptFlow = rec.getSchDeptFlow();
				String recTypeId = rec.getRecTypeId();
				String itemId = rec.getItemId();

				if(recFinishMap.get(schDeptFlow)==null){
					recFinishMap.put(schDeptFlow,1);
				}else{
					recFinishMap.put(schDeptFlow,recFinishMap.get(schDeptFlow)+1);
				}
				if(recFinishMap.get(schDeptFlow+recTypeId)==null){
					recFinishMap.put(schDeptFlow+recTypeId,1);
				}else{
					recFinishMap.put(schDeptFlow+recTypeId,recFinishMap.get(schDeptFlow+recTypeId)+1);
				}
				if(StringUtil.isNotBlank(itemId)){
					if(recFinishMap.get(schDeptFlow+recTypeId+itemId)==null){
						recFinishMap.put(schDeptFlow+recTypeId+itemId,1);
					}else{
						recFinishMap.put(schDeptFlow+recTypeId+itemId,recFinishMap.get(schDeptFlow+recTypeId+itemId)+1);
					}
				}
				if(recFinishMap.get(processFlow)==null){
					recFinishMap.put(processFlow,1);
				}else{
					recFinishMap.put(processFlow,recFinishMap.get(processFlow)+1);
				}
				if(recFinishMap.get(processFlow+recTypeId)==null){
					recFinishMap.put(processFlow+recTypeId,1);
				}else{
					recFinishMap.put(processFlow+recTypeId,recFinishMap.get(processFlow+recTypeId)+1);
				}
				if(StringUtil.isNotBlank(itemId)){
					if(recFinishMap.get(processFlow+recTypeId+itemId)==null){
						recFinishMap.put(processFlow+recTypeId+itemId,1);
					}else{
						recFinishMap.put(processFlow+recTypeId+itemId,recFinishMap.get(processFlow+recTypeId+itemId)+1);
					}
				}

				SchArrangeResult result = proResultMap.get(processFlow);
				if(result!=null){
					String globalUpKey = result.getStandardGroupFlow()+result.getStandardDeptId();
					if(recFinishMap.get(globalUpKey)==null){
						recFinishMap.put(globalUpKey,1);
					}else{
						recFinishMap.put(globalUpKey,recFinishMap.get(globalUpKey)+1);
					}
					if(recFinishMap.get(globalUpKey+recTypeId)==null){
						recFinishMap.put(globalUpKey+recTypeId,1);
					}else{
						recFinishMap.put(globalUpKey+recTypeId,recFinishMap.get(globalUpKey+recTypeId)+1);
					}
					if(StringUtil.isNotBlank(itemId)){
						if(recFinishMap.get(globalUpKey+recTypeId+itemId)==null){
							recFinishMap.put(globalUpKey+recTypeId+itemId,1);
						}else{
							recFinishMap.put(globalUpKey+recTypeId+itemId,recFinishMap.get(globalUpKey+recTypeId+itemId)+1);
						}
					}
				}
			}
		}

		if(arrResultList!=null&&arrResultList.size()>0){
			finishPerMap = new HashMap<String, String>();

			//List<String> schDeptFlows = new ArrayList<String>();

			for (SchArrangeResult result : arrResultList) {
				String resultFlow = result.getResultFlow();
				//schDeptFlows.add(result.getSchDeptFlow());

				List<SchRotationDeptReq> deptReqList = searchStandardReqByResult(result);
				Map<String,Integer> reqNumMap = new HashMap<String, Integer>();
				Map<String,List<String>> itemMap = new HashMap<String, List<String>>();
				reqNumMap.put("reqSum",0);
				if(deptReqList!=null && deptReqList.size()>0){
					for(SchRotationDeptReq deptReq : deptReqList){
						String recTypeId=deptReq.getRecTypeId();//登记类型
						int req=deptReq.getReqNum().intValue();//要求数
						String itemId=deptReq.getItemId();//子项ID
						if(GlobalConstant.FLAG_Y.equals(getCfgByCode("res_registry_type_"+recTypeId))){
							reqNumMap.put("reqSum",reqNumMap.get("reqSum")+(req));
							if(reqNumMap.get(recTypeId)==null){
								reqNumMap.put(recTypeId,(deptReq.getReqNum().intValue()));
							}else{
								reqNumMap.put(recTypeId,reqNumMap.get(recTypeId)+(req));
							}
							String key=recTypeId+"itemCount";
							if(reqNumMap.get(key)==null){
								reqNumMap.put(key,1);
							}else{
								reqNumMap.put(key,reqNumMap.get(key)+1);
							}
							if(StringUtil.isNotBlank(recTypeId)&&itemId!=null&&StringUtil.isNotBlank(itemId)) {
								key=recTypeId+itemId;
								if (reqNumMap.get(key) == null) {
									reqNumMap.put(key, deptReq.getReqNum().intValue());
								} else {
									reqNumMap.put(key, reqNumMap.get(key) + (req));
								}
							}
							RegistryTypeEnum rectype=RegistryTypeEnum.valueOf(recTypeId);
							if(rectype!=null&&rectype.getHaveItem().equals(GlobalConstant.FLAG_Y)&&itemId!=null)
							{
								if(StringUtil.isNotBlank(itemId)){
									List<String> itemIds = itemMap.get(recTypeId);
									if(itemIds==null){
										itemIds = new ArrayList<String>();
										itemMap.put(recTypeId,itemIds);
									}
									itemIds.add(itemId);
								}
							}
						}
					}
				}
				String processFlow = processMap.get(resultFlow);
				String globalUpKey = result.getStandardGroupFlow()+result.getStandardDeptId();
				finishPerMap.put(resultFlow+"finish",defaultString(recFinishMap.get(globalUpKey)));
				finishPerMap.put(resultFlow+"finishSingle",defaultString(recFinishMap.get(processFlow)));
				finishPerMap.put(resultFlow+"req",defaultString(reqNumMap.get("reqSum")));

				for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
					if(GlobalConstant.FLAG_Y.equals(getCfgByCode("res_registry_type_"+regType.getId()))){
						finishPerMap.put(resultFlow+regType.getId()+"finish",defaultString(recFinishMap.get(processFlow+regType.getId())));
						if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
							finishPerMap.put(resultFlow+regType.getId()+"req",defaultString(reqNumMap.get(regType.getId())));
						}

						finishPerMap.put(resultFlow+regType.getId()+"itemCount",defaultString(reqNumMap.get(regType.getId()+"itemCount")));
					}
				}

				setFinishPerMap(finishPerMap,result,itemMap,recFinishMap,reqNumMap);
			}
		}
		return finishPerMap;
	}

	@Override
	public List<ResSignin> getSigninByDeptFlow(String userFlow, String schDeptFlow) {
		if(StringUtil.isNotBlank(userFlow)&&StringUtil.isNotBlank(schDeptFlow)){
			ResSigninExample example = new ResSigninExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andUserFlowEqualTo(userFlow).andSchDeptFlowEqualTo(schDeptFlow);
			example.setOrderByClause("SIGN_DATE DESC");
			return signinMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<SysDict> getDictListByDictId(String doctorTrainingSpe) {
		if(StringUtil.isNotBlank(doctorTrainingSpe))
		{
			SysDictExample example = new SysDictExample();
			example.createCriteria().andDictTypeIdEqualTo(doctorTrainingSpe).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			List<SysDict> sysDictList = sysDictMapper.selectByExample(example);

			return sysDictList;
		}
		return null;
	}

	private List<SchRotationDeptReq> searchStandardReqByResult(SchArrangeResult result) {
		List<SchRotationDeptReq> reqList = null;
		if (result != null) {
			reqList = searchStandardReqByGroup(result.getStandardGroupFlow(), result.getStandardDeptId());
		}
		return reqList;
	}
	public List<SchRotationDeptReq> searchStandardReqByGroup(String standardGroupFlow,String standardDeptId){
		if(!StringUtil.isNotBlank(standardDeptId) || !StringUtil.isNotBlank(standardGroupFlow)){
			return null;
		}
		SchRotationDeptExample rotationDeptExample = new SchRotationDeptExample();
		rotationDeptExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andGroupFlowEqualTo(standardGroupFlow)
				.andStandardDeptIdEqualTo(standardDeptId)
				.andOrgFlowIsNull();
		List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExample(rotationDeptExample);

		List<SchRotationDeptReq> reqList = null;
		if(rotationDeptList!=null && rotationDeptList.size()>0){
			String relRecordFlow = rotationDeptList.get(0).getRecordFlow();
			reqList = searchDeptReqByRel(relRecordFlow);
		}
		return reqList;
	}
	public List<SchRotationDeptReq> searchDeptReqByRel(String relRecordFlow){
		SchRotationDeptReqExample example = new SchRotationDeptReqExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRelRecordFlowEqualTo(relRecordFlow);
		return reqMapper.selectByExample(example);
	}


	private String defaultString(Integer num){
		return num==null?"0":(num+"");
	}

	private void setFinishPerMap(Map<String,String> finishPerMap,SchArrangeResult result,Map<String,List<String>> itemMap,Map<String,Integer> recFinishMap,Map<String,Integer> reqNumMap){
		if(finishPerMap!=null){
			String resultFlow = result.getResultFlow();
			if(resultFlow.equals("837fb1816cf44de8a989c798f9711fc8"))
			{
				System.err.println(JSON.toJSONString(finishPerMap));
				System.err.println(JSON.toJSONString(itemMap));
				System.err.println(JSON.toJSONString(recFinishMap));
				System.err.println(JSON.toJSONString(reqNumMap));
			}
			String standardGroupFlow = result.getStandardGroupFlow();
			String standardDeptId = result.getStandardDeptId();

			String globalUpKey = standardGroupFlow+standardDeptId;

			Float deptReq = getValDefaultZero(finishPerMap,resultFlow+"req");

			Float deptFin = getValDefaultZero(finishPerMap,resultFlow+"finish");


			Float deptPer = 0f;

			for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
				if(GlobalConstant.FLAG_Y.equals(getCfgByCode("res_registry_type_"+regType.getId()))){
					if(GlobalConstant.FLAG_Y.equals(regType.getHaveReq())){
						String recTypeId = regType.getId();

						Float typePer = 0f;

						boolean isFinish = true;

						Float typeFin = getValDefaultZero(recFinishMap,globalUpKey+recTypeId);

						Float typeReq = getValDefaultZero(reqNumMap,recTypeId);

						Float typeReqPer = getPer(typeReq,deptReq);

						List<String> itemList = null;

						if(itemMap!=null && itemMap.size()>0){
							itemList = itemMap.get(recTypeId);
						}

						if(isFinish = itemList!=null && itemList.size()>0){
							for(String itemId : itemList){

								Float itemFin = getValDefaultZero(recFinishMap,globalUpKey+recTypeId+itemId);

								Float itemReq = getValDefaultZero(reqNumMap,recTypeId+itemId);


								Float itemReqPre = getPer(itemReq,typeReq);

								Float itemPre = getPer(itemFin,itemReq);

								if(itemReq==0 && itemFin>0){
									itemPre = 1f;
								}

								BigDecimal b = new BigDecimal(itemPre*100).setScale(0,BigDecimal.ROUND_HALF_UP);
								finishPerMap.put(resultFlow+recTypeId+itemId,b.toString());

								typePer+=(itemPre*itemReqPre);
								if(isFinish){
									isFinish = (itemReq-itemFin)<=0;
								}
							}
						}else{
							typePer = getPer(typeFin,typeReq);

							if(typeReq==0 && typeFin>0){
								typePer = 1f;
							}

						}
						finishPerMap.put(resultFlow+recTypeId+"isFinish",isFinish+"");

						BigDecimal b=new BigDecimal(typePer*100).setScale(0, BigDecimal.ROUND_HALF_UP);
						finishPerMap.put(resultFlow+recTypeId,b.toString());

						deptPer+=(typeReqPer*typePer);
					}
				}
			}

			if(deptReq==0 && deptFin>0){
				deptPer=1f;
			}

			BigDecimal b=new BigDecimal(deptPer*100).setScale(0, BigDecimal.ROUND_HALF_UP);
			finishPerMap.put(resultFlow,b.toString());
		}
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
	public List<ResRec> searchFinishRec(List<String> recTypeIds,String operUserFlow){
		ResRecExample example = new ResRecExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecTypeIdIn(recTypeIds)
				.andOperUserFlowEqualTo(operUserFlow);//.andAuditStatusIdEqualTo(RecStatusEnum.TeacherAuditY.getId());
		example.setOrderByClause("OPER_TIME");
		return recMapper.selectByExample(example);
	}
	@Override
	public List<ResDoctorExt> searchDocUser(ResDoctorExt resDoctorExt){
		return doctorExtMapper.searchResDoctorUser(resDoctorExt);
	}

	@Override
	public List<Map<String,Object>> countResultByUser(List<String> userFlows){
		return resultMapper.countResultByUser(userFlows);
	}

	@Override
	public List<Map<String,Object>> countProcessByUser(List<String> userFlows){
		return njmu2ProcessExtMapper.countProcessByUser(userFlows);
	}
}  
  