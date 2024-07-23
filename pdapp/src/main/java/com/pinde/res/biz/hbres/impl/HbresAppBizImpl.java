package com.pinde.res.biz.hbres.impl;


import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.GlobalUtil;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IHbresAppBiz;
import com.pinde.res.biz.hbres.IHbresStudentBiz;
import com.pinde.res.dao.hbres.ext.HbResLectureInfoExtMapper;
import com.pinde.res.dao.hbres.ext.HbresResDoctorSchProcessExtMapper;
import com.pinde.res.dao.hbres.ext.SchDeptExtMapper;
import com.pinde.res.dao.jswjw.ext.SchArrangeResultExtMapper;
import com.pinde.res.dao.jswjw.ext.SysDeptExtMapper;
import com.pinde.res.dao.stdp.ext.StdpResRecExtMapper;
import com.pinde.res.dao.stdp.ext.StdpSchArrangeResultExtMapper;
import com.pinde.res.enums.hbres.ResAssessTypeEnum;
import com.pinde.res.enums.hbres.ResRecTypeEnum;
import com.pinde.res.enums.hbres.SigninTypeEnum;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.SysUserExample.Criteria;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional(rollbackFor=Exception.class)
public class HbresAppBizImpl implements IHbresAppBiz {

	@Resource
	private SysUserMapper userMapper;
	@Resource
	private ResDoctorMapper doctorMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;
	@Resource
	private SysCfgMapper cfgMapper;
	@Resource
	private StdpSchArrangeResultExtMapper stdpResultExtMapper;
	@Resource
	private HbresResDoctorSchProcessExtMapper stdpProcessExtMapper;
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
	private IHbresStudentBiz hbresstudentBiz;
	@Resource
	private ResSigninMapper signinMapper;
	@Resource
	private ResDoctorSchProcessMapper processMapper;
	@Resource
	private SchDeptMapper schDeptMapper;
	@Autowired
	private SchDeptExtMapper schDeptExtMapper;
	@Resource
	private SchArrangeResultMapper resultMapper;
	@Resource
	private SchRotationDeptMapper schRotationDeptMapper;
	@Resource
	private DeptTeacherGradeInfoMapper gradeInfoMapper;
	@Resource
	private SchArrangeResultExtMapper resultExtMapper;
	@Resource
	private SchDeptRelMapper deptRelMapper;
	@Resource
	private SchEntryReportMapper reportMapper;
	@Resource
	private QingpuDoctorSigninMapper qpsigninMapper;
	@Resource
	private ResUserBindMacidMapper macidMapper;

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
		//GlobalUtil.setCfg();
		idValCfg = GlobalUtil.getIdValCfg();
		teacherDataListContentCfg = GlobalUtil.getTeacherDataListContentCfg();
		itemIdAttrCfg = GlobalUtil.getItemIdAttrCfg();
		sumItemMap = GlobalUtil.getSumItemMap();
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

	@Override
	public SysUser readSysUser(String userFlow){
		return userMapper.selectByPrimaryKey(userFlow);
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
			return stdpResultExtMapper.getDocResultArea(paramMap);
		}
		return null;
	}
	
	//获取当前轮转状态的日期区间
	@Override
	public Map<String,Object> getDocProcessArea(String doctorFlow){
		if(StringUtil.isNotBlank(doctorFlow)){
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",doctorFlow);
			return stdpProcessExtMapper.getDocProcessArea(paramMap);
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
//						List<Element> elements = root.elements();
						Element afterEvaluation = root.element(GlobalConstant.RES_ROLE_SCOPE_MANAGER+ResRecTypeEnum.AfterEvaluation.getId());
						if(afterEvaluation==null){
							afterEvaluation = root.element(GlobalConstant.RES_ROLE_SCOPE_HEAD+ResRecTypeEnum.AfterEvaluation.getId());
						}
						if(afterEvaluation==null){
							afterEvaluation = root.element(GlobalConstant.RES_ROLE_SCOPE_TEACHER+ResRecTypeEnum.AfterEvaluation.getId());
						}
						List<Element> elements = null;
						if(afterEvaluation!=null){
							elements = afterEvaluation.elements();
						}else{
							elements = root.elements();
						}
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
										formDataMap.put(eleName+"_name",element.getText());
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
			ResAssessCfgExample.Criteria criteria = example.createCriteria()
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
	@Override
	public String groupContent(String recTypeId,Map<String,String> dataMap,String mapStr,String separator){
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

				SchRotationDeptReqExample.Criteria criteria = reqExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
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

	//根据request获取这次表单的xml
	private String getXmlByRequest(HttpServletRequest request,String rootName,ResDoctorSchProcess writeBackProcess){
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
					if("score".equals(key)){
						String v = request.getParameter(key);
						try {
							Float sum=Float.valueOf(v);//同时回写进process
							writeBackProcess.setSchScore(BigDecimal.valueOf(sum));

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				return doc.asXML();
			}
		}
		return null;
	}
	//编辑一条登记数据
	@Override
	public String editRec(String recFlow,
			String operUserFlow,
			String resultFlow,
			String recTypeId,
			String itemId,
			HttpServletRequest request){
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
			
		}else if(ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId)){
			content = getXmlByRequest(request,recTypeId,writeBackProcess);
		}else{
			content = getXmlByRequest(request,recTypeId,",",itemId,writeBackProcess);
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
            SysUser user=readSysUser(operUserFlow);
            String medicineTypeId="";
            if(user!=null) medicineTypeId=user.getMedicineTypeId();
            rec.setMedicineType(medicineTypeId);
			//读取科室数据
			if(StringUtil.isNotBlank(resultFlow)){
				ResDoctorSchProcess process = hbresstudentBiz.getProcessByResult(resultFlow);
				if(process!=null){
					String processFlow = process.getProcessFlow();
					rec.setProcessFlow(processFlow);
					writeBackProcess.setProcessFlow(processFlow);
				}
				SchArrangeResult result = hbresstudentBiz.searcheDocResult(null,resultFlow);
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
			rec.setRecForm(GlobalConstant.RES_HBRES_DEFAULT_FORM);
			
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
				ResDoctorSchProcess process = hbresstudentBiz.getProcessByResult(resultFlow);
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
		return rec.getRecFlow();
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
	private String getXmlByRequest(HttpServletRequest request,String rootName,String separator,String itemId,ResDoctorSchProcess writeBackProcess){
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
				Float totalScore = countSum(request,rootName);
				if(totalScore!=null){
					//将总分放进根节点
					Element totalScoreEle = root.addElement("totalScore");
					totalScoreEle.setText(totalScore.toString());
					//同时回写进process
					writeBackProcess.setSchScore(BigDecimal.valueOf(totalScore));
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
				SchArrangeResult result = hbresstudentBiz.searcheDocResult(null,deptFlow);
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
		return getSignin(userFlow,null);
	}

	//获取该科室最签到记录
	@Override
	public List<ResSignin> getSignin(String userFlow, String deptFlow){
		if(StringUtil.isNotBlank(userFlow)){
			ResSigninExample example = new ResSigninExample();
			ResSigninExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andUserFlowEqualTo(userFlow);
			if(StringUtil.isNotBlank(deptFlow)){
				SchArrangeResult result = hbresstudentBiz.searcheDocResult(null,deptFlow);
				if(result!=null){
					deptFlow = result.getDeptFlow();
					criteria.andDeptFlowEqualTo(deptFlow);
				}
			}
			example.setOrderByClause("SIGN_DATE DESC");
			return signinMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<SchDept> getSchDeptListByOrg(String orgFlow,String deptName){
		SchDeptExample example = new SchDeptExample();
		SchDeptExample.Criteria criteria=example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andOrgFlowEqualTo(orgFlow);
		if(StringUtil.isNotBlank(deptName))
		{
			criteria.andSchDeptNameLike("%"+deptName+"%");
		}
		return schDeptMapper.selectByExample(example);
	}

	@Override
	public List<SchDeptRel> searchRelByStandard(String orgFlow,String standardDeptId,String deptName){
		return schDeptExtMapper.searchRelByStandard(orgFlow,standardDeptId,deptName);
	}

	@Override
	public List<SysUser> getUserListBySchDept(String schDeptFlow, String roleFlow,String userName){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("schDeptFlow",schDeptFlow);
		paramMap.put("roleFlow",roleFlow);
		paramMap.put("userName",userName);
		return schDeptExtMapper.getUserListBySchDept(paramMap);
	}

	@Override
	public SchDept readSchDept(String deptFlow){
		return schDeptMapper.selectByPrimaryKey(deptFlow);
	}

	@Override
	public int editResDoctor(ResDoctor doctor) {
		return doctorMapper.updateByPrimaryKey(doctor);
	}

	@Resource
	private SchRotationDeptReqMapper schRotationDeptReqMapper;
	@Autowired
	private ResScoreMapper scoreMapper;
	@Autowired
	private ResDoctorSchProcessMapper  resDoctorProcessMapper;
	@Override
	public List<Map<String, Object>> commReqOptionNameList(String userFlow,String deptFlow,String dataType,String catFlow) {
		String recTypeId = dataType;
		SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
		com.pinde.sci.model.mo.SchRotationDeptReqExample.Criteria  criteria = reqExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).
				andRelRecordFlowEqualTo(deptFlow).andRecTypeIdEqualTo(recTypeId);
		if(StringUtil.isNotBlank(catFlow)){
			criteria.andItemIdEqualTo(catFlow);
		}
		List<SchRotationDeptReq> reqList = schRotationDeptReqMapper.selectByExample(reqExample);
		List<Map<String, Object>> diseaseDiagNameList = new ArrayList<Map<String,Object>>();
		for(SchRotationDeptReq req : reqList){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("itemId",req.getItemId());
			map.put("itemName",req.getItemName());
			diseaseDiagNameList.add(map);
		}
		return diseaseDiagNameList;
	}
	@Override
	public ResScore getScoreByProcess(String processFlow){
		if(!StringUtil.isNotBlank(processFlow)){
			return null;
		}

		ResScoreExample example=new ResScoreExample();
		ResScoreExample.Criteria criteria= example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andProcessFlowEqualTo(processFlow);

		List<ResScore> scores = scoreMapper.selectByExample(example);

		if(scores==null || scores.isEmpty()){
			return null;
		}

		return scores.get(0);
	}

	@Override
	public ResDoctorSchProcess readSchProcess(String processFlow) {
		ResDoctorSchProcess process = null;
		if(StringUtil.isNotBlank(processFlow)){
			process = this.resDoctorProcessMapper.selectByPrimaryKey(processFlow);
		}
		return process;
	}

	@Override
	public ResDoctorSchProcess readSchProcessByResultFlow(String resultFlow) {
		if(StringUtil.isNotBlank(resultFlow)) {
			ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
			ResDoctorSchProcessExample.Criteria criteria = example.createCriteria();
			criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
			List<ResDoctorSchProcess> list=resDoctorProcessMapper.selectByExample(example);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}

	@Autowired
	private SysDictMapper dictMapper;
	@Autowired
	private SysDeptExtMapper sysDeptExtMapper;
	@Autowired
	private ResLectureScanRegistMapper lectureScanRegistMapper;
	@Autowired
	private ResLectureInfoMapper lectureInfoMapper;
	@Autowired
	private DictFormMapper dictFormMapper;
	@Autowired
	private HbResLectureInfoExtMapper lectureInfoExtMapper;
	@Autowired
	private ResLectureEvaDetailMapper lectureEvaDetailMapper;
	@Resource
	private JsresAttendanceDetailMapper jsresAttendanceDetailMapper;
	@Resource
	private JsresAttendanceMapper jsresAttendanceMapper;
	@Override
	public List<ResLectureInfo> SearchNewLectures(String orgFlow, String roleId, String roleFlow){
		List<ResLectureInfo> lectureInfos = lectureInfoExtMapper.searchLecturesList(orgFlow,roleId,roleFlow);
		return lectureInfos;
	}

	@Override
	public ResLectureScanRegist searchByUserFlowAndLectureFlow(String userFlow,String lectureFlow) {
		ResLectureScanRegistExample example = new ResLectureScanRegistExample();
		ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).
				andLectureFlowEqualTo(lectureFlow).andOperUserFlowEqualTo(userFlow);
		List<ResLectureScanRegist> lectureScanRegists = lectureScanRegistMapper.selectByExample(example);
		if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
			return lectureScanRegists.get(0);
		}else{
			return null;
		}

	}

	@Override
	public List<ResLectureScanRegist> searchByUserFLowAndRegist(String userFlow){
		ResLectureScanRegistExample example = new ResLectureScanRegistExample();
		ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).
				andOperUserFlowEqualTo(userFlow);
		example.setOrderByClause("CREATE_TIME");
		return lectureScanRegistMapper.selectByExample(example);
	}


	@Override
	public ResLectureInfo read(String lectureFlow) {
		ResLectureInfo lectureInfo = null;
		if(StringUtil.isNotBlank(lectureFlow)) {
			lectureInfo = lectureInfoMapper.selectByPrimaryKey(lectureFlow);
		}
		return  lectureInfo;
	}
	@Override
	public List<ResLectureEvaDetail> searchByUserFlowLectureFlow(String userFlow, String lectureFlow) {
		ResLectureEvaDetailExample example = new ResLectureEvaDetailExample();
		if(StringUtil.isNotBlank(userFlow)&&StringUtil.isNotBlank(lectureFlow)){
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow)
					.andOperUserFlowEqualTo(userFlow);
		}
		return lectureEvaDetailMapper.selectByExample(example);
	}
	@Override
	public synchronized int editLectureScanRegist(String lectureFlow, SysUser currUser, ResLectureScanRegist regist, ResDoctor doctor){
		int count=lectureInfoExtMapper.checkRegistNum(lectureFlow);
		if(count<=0)
		{

			if(count==0)
				count=-1;
			return count;
		}
		String userFlow = currUser.getUserFlow();
		ResLectureScanRegist lectureScanRegist =null;
		if(regist==null) {
			lectureScanRegist = new ResLectureScanRegist();
			lectureScanRegist.setRecordFlow(PkUtil.getUUID());
			lectureScanRegist.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			lectureScanRegist.setCreateUserFlow(userFlow);
			lectureScanRegist.setCreateTime(DateUtil.getCurrDateTime());
			lectureScanRegist.setModifyUserFlow(userFlow);
			lectureScanRegist.setModifyTime(DateUtil.getCurrDateTime());
			lectureScanRegist.setLectureFlow(lectureFlow);
			lectureScanRegist.setOperUserFlow(userFlow);
			if(doctor!=null)
			{
				String session = doctor.getSessionNumber();
				String trainingTypeId = doctor.getDoctorCategoryId();
				String trainingTypeName = doctor.getDoctorCategoryName();
				String trainingSpeId = doctor.getTrainingSpeId();
				String trainingSpeName = doctor.getTrainingSpeName();
				if (StringUtil.isNotBlank(session)) {
					lectureScanRegist.setSessionNumber(session);
				}
				if (StringUtil.isNotBlank(trainingTypeId)) {
					lectureScanRegist.setTrainingTypeId(trainingTypeId);
				}
				if (StringUtil.isNotBlank(trainingTypeName)) {
					lectureScanRegist.setTrainingTypeName(trainingTypeName);
				}
				if (StringUtil.isNotBlank(trainingSpeId)) {
					lectureScanRegist.setTrainingSpeId(trainingSpeId);
				}
				if (StringUtil.isNotBlank(trainingSpeName)) {
					lectureScanRegist.setTrainingSpeName(trainingSpeName);
				}
			}

			if (StringUtil.isNotBlank(currUser.getUserName())) {
				lectureScanRegist.setOperUserName(currUser.getUserName());
			}
			lectureScanRegist.setIsRegist("Y");
			return lectureScanRegistMapper.insertSelective(lectureScanRegist);
		}else{
			lectureScanRegist=regist;
			lectureScanRegist.setIsRegist("Y");
			return lectureScanRegistMapper.updateByPrimaryKey(lectureScanRegist);
		}

	}


	@Override
	public int editResLectureEvaDetail(ResLectureEvaDetail resLectureEvaDetail,String userFlow) {
		if(resLectureEvaDetail!=null){
			if(StringUtil.isNotBlank(resLectureEvaDetail.getRecordFlow())){
				resLectureEvaDetail.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				resLectureEvaDetail.setModifyUserFlow(userFlow);
				resLectureEvaDetail.setModifyTime(DateUtil.getCurrDateTime());
				return lectureEvaDetailMapper.updateByPrimaryKeySelective(resLectureEvaDetail);
			}else{
				resLectureEvaDetail.setRecordFlow(PkUtil.getUUID());
				resLectureEvaDetail.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				resLectureEvaDetail.setCreateUserFlow(userFlow);
				resLectureEvaDetail.setCreateTime(DateUtil.getCurrDateTime());
				resLectureEvaDetail.setModifyUserFlow(userFlow);
				resLectureEvaDetail.setModifyTime(DateUtil.getCurrDateTime());
				return lectureEvaDetailMapper.insertSelective(resLectureEvaDetail);
			}
		}
		return 0;
	}

	@Override
	public int scanRegist(ResLectureScanRegist regist) {
		if(regist!=null){
			if(StringUtil.isNotBlank(regist.getRecordFlow())){
				regist.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				regist.setModifyUserFlow(regist.getOperUserFlow());
				regist.setModifyTime(DateUtil.getCurrDateTime());
				return lectureScanRegistMapper.updateByPrimaryKeySelective(regist);
			}else{
				regist.setRecordFlow(PkUtil.getUUID());
				regist.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				regist.setCreateUserFlow(regist.getOperUserFlow());
				regist.setCreateTime(DateUtil.getCurrDateTime());
				regist.setModifyUserFlow(regist.getOperUserFlow());
				regist.setModifyTime(DateUtil.getCurrDateTime());
				return lectureScanRegistMapper.insertSelective(regist);
			}
		}
		return 0;
	}

	@Override
	public List<JsresAttendanceDetail> getAttendanceDetailList(String nowDay, String userFlow) {
		JsresAttendanceDetailExample example=new JsresAttendanceDetailExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andAttendDateEqualTo(nowDay).andDoctorFlowEqualTo(userFlow);
		example.setOrderByClause("ATTEND_TIME DESC,CREATE_TIME DESC");
		return jsresAttendanceDetailMapper.selectByExample(example);
	}
	@Override
	public JsresAttendance getJsresAttendance(String nowDay, String userFlow) {
		JsresAttendanceExample example=new JsresAttendanceExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andAttendDateEqualTo(nowDay).andDoctorFlowEqualTo(userFlow);
		JsresAttendance bean=null;
		List<JsresAttendance> list=jsresAttendanceMapper.selectByExample(example);
		if(list!=null&&list.size()>0){
			bean=list.get(0);
		}
		return bean;
	}

	@Override
	public int addJsresAttendance(JsresAttendance attendance) {
		return  jsresAttendanceMapper.insertSelective(attendance);
	}

	@Override
	public int addJsresAttendanceDetail(JsresAttendanceDetail detail) {
		return jsresAttendanceDetailMapper.insertSelective(detail);
	}

	@Override
	public List<Map<String,String>> getHistoryLecture(String userFlow) {
		return lectureInfoExtMapper.getHistoryLecture(userFlow);
	}

	@Override
	public int saveScore(ResScore score, SysUser user) {
		if(score!=null){
			if(StringUtil.isNotBlank(score.getScoreFlow())){//修改
				score.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				score.setModifyUserFlow(user.getUserFlow());
				score.setModifyTime(DateUtil.getCurrDateTime());
				return this.scoreMapper.updateByPrimaryKeySelective(score);
			}else{//新增
				score.setScoreFlow(PkUtil.getUUID());
				score.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				score.setCreateUserFlow(user.getUserFlow());
				score.setCreateTime(DateUtil.getCurrDateTime());
				score.setModifyUserFlow(user.getUserFlow());
				score.setModifyTime(DateUtil.getCurrDateTime());
				return this.scoreMapper.insertSelective(score);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	//获取系统配置信息
	@Override
	public String getCfgCode(String code){
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
	@Override
	public List<DeptTeacherGradeInfo> searchAllGrade(String userFlow) {
		List<String> recTypes = new ArrayList<String>();
		recTypes.add(com.pinde.res.enums.stdp.ResRecTypeEnum.TeacherGrade.getId());
		recTypes.add(com.pinde.res.enums.stdp.ResRecTypeEnum.DeptGrade.getId());
		DeptTeacherGradeInfoExample example = new DeptTeacherGradeInfoExample();
		example.createCriteria().andOperUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
				.andRecTypeIdIn(recTypes);
		return gradeInfoMapper.selectByExampleWithBLOBs(example);
	}
	@Override
	public Map<String, String> getNewGradeMap(List<DeptTeacherGradeInfo> gradeList) {
		Map<String,String> gradeMap = new HashMap<String, String>();
		for(DeptTeacherGradeInfo rec :gradeList ){
			try {
				Document doc = DocumentHelper.parseText(rec.getRecContent());
				if(StringUtil.isNotBlank(rec.getProcessFlow())) {
					String totalScore = StringUtil.defaultIfEmpty(doc.getRootElement().elementText("totalScore"), "未评价");
					gradeMap.put(rec.getProcessFlow() + "_" + rec.getRecTypeId(), totalScore);
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return gradeMap;
	}
	@Override
	public ResDoctorSchProcess getProcessByResultFlow(String resultFlow) {
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
		List<ResDoctorSchProcess> processList = processMapper.selectByExample(example);
		if(processList.size()>0){
			return processList.get(0);
		}
		return null;
	}
	@Override
	public SchRotationDept searchGroupFlowAndStandardDeptIdQuery(
			String groupFlow, String standardDeptId) {
		SchRotationDeptExample example = new SchRotationDeptExample();
		SchRotationDeptExample.Criteria criter=example.createCriteria();
		criter.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
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
	public List<SchArrangeResult> checkResultDate(String doctorFlow,String startDate,String endDate,String subDeptFlow,String rotationFlow){
		List<SchArrangeResult> result = null;
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && StringUtil.isNotBlank(doctorFlow)){
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",doctorFlow);
			paramMap.put("startDate",startDate);
			paramMap.put("endDate",endDate);
			paramMap.put("subDeptFlow",subDeptFlow);
			paramMap.put("rotationFlow",rotationFlow);
			result = resultExtMapper.checkResultDate(paramMap);
		}
		return result;
	}
	@Override
	public ResScore readScoreByProcessFlow(String processFlow) {
		if(StringUtil.isNotBlank(processFlow)){
			ResScoreExample example=new ResScoreExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
					.andProcessFlowEqualTo(processFlow);
			List<ResScore> list=scoreMapper.selectByExample(example);
			if(list!=null&&list.size()>0){
				return  list.get(0);
			}
		}
		return null;
	}

	@Override
	public int findLectureRegistNum(String lectureFlow) {
		if(StringUtil.isBlank(lectureFlow)) return 0;
		return lectureInfoExtMapper.findLectureRegistNum(lectureFlow);
	}

	@Override
	public SchArrangeResult getResultByDeptFlow(List<String> deptFlows, String userFlow) {
		List<SchArrangeResult> results = null;
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",userFlow);
		paramMap.put("deptFlows",deptFlows);
		results = resultExtMapper.getResultByDeptFlow(paramMap);
		if(results!=null&&results.size()>0)
		{
			return results.get(0);
		}
		return null;
	}

	@Override
	public SchEntryReport getSchEntryReport(String resultFlow) {
		return reportMapper.selectByPrimaryKey(resultFlow);
	}

	@Override
	public int addEntryReport(SchEntryReport schEntryReport,String userFlow) {
		if(schEntryReport!=null) {
			schEntryReport.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			schEntryReport.setCreateUserFlow(userFlow);
			schEntryReport.setCreateTime(DateUtil.getCurrDateTime());
			schEntryReport.setModifyUserFlow(userFlow);
			schEntryReport.setModifyTime(DateUtil.getCurrDateTime());
			return this.reportMapper.insertSelective(schEntryReport);
		}
		return 0;
	}

	@Override
	public List<ResDoctorSchProcess> getCurrentProcesses(String userFlow) {
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andIsCurrentFlagEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return resDoctorProcessMapper.selectByExample(example);
	}

	@Override
	public List<QingpuDoctorSignin> searchQingpuDoctorSignin(QingpuDoctorSignin doctorSignin) {
		QingpuDoctorSigninExample example = new QingpuDoctorSigninExample();
		QingpuDoctorSigninExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(doctorSignin.getSigninDate())){
			criteria.andSigninDateEqualTo(doctorSignin.getSigninDate());
		}
		return qpsigninMapper.selectByExample(example);
	}

	@Override
	public int addQingpuDoctorSignin(QingpuDoctorSignin doctorSignin) {
		if(doctorSignin!=null) {
			doctorSignin.setReportFlow(PkUtil.getUUID());
			doctorSignin.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			doctorSignin.setCreateUserFlow(doctorSignin.getDoctorFlow());
			doctorSignin.setCreateTime(DateUtil.getCurrDateTime());
			doctorSignin.setModifyUserFlow(doctorSignin.getDoctorFlow());
			doctorSignin.setModifyTime(DateUtil.getCurrDateTime());
			return this.qpsigninMapper.insertSelective(doctorSignin);
		}
		return 0;
	}

	@Override
	public ResUserBindMacid readMacidByUserFlow(String userFlow) {
		return macidMapper.selectByPrimaryKey(userFlow);
	}

	@Override
	public void saveUserMacid(ResUserBindMacid macid) {
		ResUserBindMacid old=readMacidByUserFlow(macid.getUserFlow());
		if(old==null)
		{
			macid.setRecordStatus("Y");
			macidMapper.insertSelective(macid);
		}else{
			macidMapper.updateByPrimaryKeySelective(macid);
		}
	}

	@Override
	public ResUserBindMacid readMacidByMacid(String uuid) {
		ResUserBindMacidExample example=new ResUserBindMacidExample();
		example.createCriteria().andRecordStatusEqualTo("Y").andMacIdEqualTo(uuid);
		List<ResUserBindMacid> list=macidMapper.selectByExample(example);
		if(list!=null&&list.size()>0)
		{
			return list.get(0);
		}
		return null;
	}
	@Override
	public List<ResLectureScanRegist> searchIsScan(String lectureFlow) {
		ResLectureScanRegistExample example = new ResLectureScanRegistExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow).
				andIsScanIsNotNull();
		return lectureScanRegistMapper.selectByExample(example);
	}

	@Override
	public List<ResLectureScanRegist> searchIsRegist(String lectureFlow) {
		ResLectureScanRegistExample example = new ResLectureScanRegistExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow).
				andIsRegistEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return lectureScanRegistMapper.selectByExample(example);
	}

	@Override
	public String getDictNameByTypeId(String dictId, String dictTypeId) {

		if(StringUtil.isNotBlank(dictTypeId)){
			SysDictExample example = new SysDictExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDictTypeIdEqualTo(dictTypeId)
					.andDictIdEqualTo(dictId);
			List<SysDict> dictList = dictMapper.selectByExample(example);
			if(null != dictList && !dictList.isEmpty()){
				return dictList.get(0).getDictName();
			}
		}
		return "";
	}

	@Autowired
	private JsresPowerCfgMapper jsresPowerCfgMapper;
	@Override
	public String getJsResCfgCode(String code){
		if(StringUtil.isNotBlank(code)){
			JsresPowerCfg cfg = jsresPowerCfgMapper.selectByPrimaryKey(code);
			if(cfg != null){
				String val = cfg.getCfgValue();
				return val;
			}
		}
		return null;
	}
	@Autowired
	private ResPowerCfgMapper resPowerCfgMapper;
	@Override
	public String getResCfgCode(String code){
		if(StringUtil.isNotBlank(code)){
			ResPowerCfg cfg = resPowerCfgMapper.selectByPrimaryKey(code);
			if(cfg != null){
				String val = cfg.getCfgValue();
				return val;
			}
		}
		return null;
	}
	@Override
	public List<SysDept> getHeadDeptList(String userFlow, String deptFlow) {

		return sysDeptExtMapper.getHeadDeptList(userFlow,deptFlow);
	}

	@Override
	public List<SysDict> getDictListByDictId(String dictTypeId) {

		if(StringUtil.isNotBlank(dictTypeId)){
			SysDictExample example = new SysDictExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andDictTypeIdEqualTo(dictTypeId);
			example.setOrderByClause("ordinal");
			return dictMapper.selectByExample(example);
		}
		return null;
	}
	@Override
	public List<ResLectureInfo> checkJoinList(String lectureFlow, String userFlow) {
		return lectureInfoExtMapper.checkJoinList(lectureFlow, userFlow);
	}

	@Override
	public List<DictForm> readDictFormsByFlow(String dictFlow) {

		DictFormExample example = new DictFormExample();
		DictFormExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andDictFlowEqualTo(dictFlow);
		example.setOrderByClause("INPUT_TYPE,DETAIL_ORDER");
		return dictFormMapper.selectByExample(example);
	}

	@Override
	public SysDict getDictByTypeId(String dictTypeId, String dictId) {
		if(StringUtil.isNotBlank(dictTypeId)){
			SysDictExample example = new SysDictExample();
			example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
			.andDictTypeIdEqualTo(dictTypeId).andDictIdEqualTo(dictId);
			List<SysDict> dictList = dictMapper.selectByExample(example);
			if(null != dictList && !dictList.isEmpty()){
				return dictList.get(0);
			}
		}
		return null;
	}
	@Autowired
	private ResLectureRandomScanMapper lectureRandomScanMapper;
	@Autowired
	private ResLectureRandomSignMapper lectureRandomSignMapper;

	@Override
	public ResLectureRandomSign readLectureRandomSign(String randomFlow) {
		return lectureRandomSignMapper.selectByPrimaryKey(randomFlow);
	}

	@Override
	public ResLectureRandomScan readLectureRandomScan(String userFlow, String randomFlow) {
		ResLectureRandomScanExample example=new ResLectureRandomScanExample();
		example.createCriteria().andOperUserFlowEqualTo(userFlow).andRandomFlowEqualTo(randomFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		List<ResLectureRandomScan> lectureRandomScans=lectureRandomScanMapper.selectByExample(example);
		if(lectureRandomScans!=null&&lectureRandomScans.size()>0)
		{
			return lectureRandomScans.get(0);
		}
		return null;
	}

	@Override
	public int saveLectureRandomScan(ResLectureRandomScan scan) {
		if(scan!=null){
			if(StringUtil.isNotBlank(scan.getRecordFlow())){
				scan.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				scan.setModifyUserFlow(scan.getOperUserFlow());
				scan.setModifyTime(DateUtil.getCurrDateTime());
				return lectureRandomScanMapper.updateByPrimaryKeySelective(scan);
			}else{
				scan.setRecordFlow(PkUtil.getUUID());
				scan.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				scan.setCreateUserFlow(scan.getOperUserFlow());
				scan.setCreateTime(DateUtil.getCurrDateTime());
				scan.setModifyUserFlow(scan.getOperUserFlow());
				scan.setModifyTime(DateUtil.getCurrDateTime());
				return lectureRandomScanMapper.insertSelective(scan);
			}
		}
		return 0;
	}
}
  