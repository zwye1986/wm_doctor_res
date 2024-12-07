package com.pinde.res.biz.jszy.impl;


import com.pinde.app.common.GlobalUtil;
import com.pinde.core.common.enums.SigninTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.FtpHelperUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.jszy.IJszyAppBiz;
import com.pinde.res.biz.jszy.IJszyStudentBiz;
import com.pinde.res.biz.stdp.IResActivityBiz;
import com.pinde.res.ctrl.jswjw.ActivityImageFileForm;
import com.pinde.res.dao.jswjw.ext.ResLectureInfoExtMapper;
import com.pinde.res.dao.jswjw.ext.SysDeptExtMapper;
import com.pinde.res.dao.jszy.ext.JszyResLectureInfoExtMapper;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.util.DateTimeUtil;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class JszyAppBizImpl implements IJszyAppBiz {

	private static Logger logger = LoggerFactory.getLogger(JszyAppBizImpl.class);


	@Autowired
	private ResLectureRandomScanMapper lectureRandomScanMapper;
	@Autowired
	private ResLectureRandomSignMapper lectureRandomSignMapper;
	@Resource
	private SysUserMapper userMapper;
	@Resource
	private ResDoctorMapper doctorMapper;
	@Resource
	private SysUserRoleMapper userRoleMapper;
	@Resource
	private SysCfgMapper cfgMapper;
	@Resource
	private SchRotationDeptReqMapper schRotationDeptReqMapper;
	@Autowired
	private ResScoreMapper scoreMapper;
	@Autowired
	private ResDoctorSchProcessMapper  resDoctorProcessMapper;
	@Resource
	private SchDoctorDeptMapper doctorDeptMapper;
	@Resource
	private SysUserMapper sysUserMapper;
	@Resource
	private DeptTeacherGradeInfoMapper gradeInfoMapper;
	@Resource
	private ResRecMapper recMapper;
	@Resource
	private ResAssessCfgMapper assessCfgMapper;
	@Autowired
	private ResLectureScanRegistMapper lectureScanRegistMapper;
	@Autowired
	private ResLectureInfoMapper lectureInfoMapper;
	@Autowired
	private JszyResLectureInfoExtMapper lectureInfoExtMapper;
	@Autowired
	private ResLectureEvaDetailMapper lectureEvaDetailMapper;
	@Resource
	private JsresAttendanceDetailMapper jsresAttendanceDetailMapper;
	@Resource
	private JsresAttendanceMapper jsresAttendanceMapper;
	@Resource
	private IJszyStudentBiz jszyStudentBiz;
	@Resource
	private ResSigninMapper signinMapper;
	@Resource
	private ResDoctorSchProcessMapper processMapper;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private SysDeptExtMapper sysDeptExtMapper;
	@Autowired
	private IResActivityBiz activityBiz;
	@Autowired
	private ResLectureInfoExtMapper resLectureInfoExtMapper;
	@Resource
	private SchArrangeResultMapper resultMapper;
	@Autowired
	private JsresPowerCfgMapper jsresPowerCfgMapper;
	@Autowired
	private ResDoctorRecruitMapper recruitMapper;
	@Autowired
	private SysDictMapper dictMapper;


	//老师展示内容配置
	private static Map<String,Map<String,String>> teacherDataListContentCfg;
	//需要计算和的表单
	private static Map<String,List<String>> sumItemMap;

	static{
		teacherDataListContentCfg = GlobalUtil.getTeacherDataListContentCfg();
		sumItemMap = GlobalUtil.getSumItemMap();
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
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
	

	//解析登记信息的xml
	@Override
	public Map<String,String> parseRecContent(String recContent){
		if(StringUtil.isNotBlank(recContent)){
			try {
				Document doc = DocumentHelper.parseText(recContent);

				if(doc!=null){
					Element rootElement = doc.getRootElement();
					if(rootElement!=null){
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
				logger.error("", e);
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
                    .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
				logger.error("", e);
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
				logger.error("", e);
			}
		}
		return null;
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
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
			.andUserFlowEqualTo(userFlow).andSignDateEqualTo(currDate);
//			example.setOrderByClause("SIGN_DATE");
			List<ResSignin> signs = signinMapper.selectByExample(example);
			if(signs==null || signs.isEmpty()){
				ResSignin sign = new ResSignin();
				sign.setRecordFlow(PkUtil.getUUID());
				sign.setUserFlow(userFlow);
				
				//获取轮转信息
				SchArrangeResult result = jszyStudentBiz.searcheDocResult(null,deptFlow);
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

                sign.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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



	@Override
	public List<Map<String, Object>> commReqOptionNameList(String userFlow,String deptFlow,String dataType,String catFlow) {
		String recTypeId = dataType;
		SchRotationDeptReqExample reqExample = new SchRotationDeptReqExample();
        SchRotationDeptReqExample.Criteria criteria = reqExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
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
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
			List<ResDoctorSchProcess> list=resDoctorProcessMapper.selectByExample(example);
			if(list!=null&&list.size()>0){
				return list.get(0);
			}
		}
		return null;
	}


	@Override
	public List<ResLectureInfo> SearchNewLectures(String orgFlow){
		List<ResLectureInfo> lectureInfos = lectureInfoExtMapper.searchLecturesList(orgFlow,null,null);

		return lectureInfos;
	}

	@Override
	public ResLectureScanRegist searchByUserFlowAndLectureFlow(String userFlow,String lectureFlow) {
		ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
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
        ResLectureScanRegistExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).
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
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow)
					.andOperUserFlowEqualTo(userFlow);
		}
		return lectureEvaDetailMapper.selectByExample(example);
	}
	@Override
	public int editLectureScanRegist(String lectureFlow, SysUser currUser, ResLectureScanRegist regist){
		String userFlow = currUser.getUserFlow();
		ResLectureScanRegist lectureScanRegist =null;
		if(regist==null) {
			lectureScanRegist = new ResLectureScanRegist();
			lectureScanRegist.setRecordFlow(PkUtil.getUUID());
            lectureScanRegist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			lectureScanRegist.setCreateUserFlow(userFlow);
			lectureScanRegist.setCreateTime(DateUtil.getCurrDateTime());
			lectureScanRegist.setModifyUserFlow(userFlow);
			lectureScanRegist.setModifyTime(DateUtil.getCurrDateTime());
			lectureScanRegist.setLectureFlow(lectureFlow);
			lectureScanRegist.setOperUserFlow(userFlow);
			ResDoctor doctor = readResDoctor(userFlow);
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
            lectureScanRegist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_Y);
			return lectureScanRegistMapper.insertSelective(lectureScanRegist);
		}else{
			lectureScanRegist=regist;
            lectureScanRegist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_Y);
			return lectureScanRegistMapper.updateByPrimaryKey(lectureScanRegist);
		}

	}

	@Override
	public int editResLectureEvaDetail(ResLectureEvaDetail resLectureEvaDetail,String userFlow) {
		if(resLectureEvaDetail!=null){
			if(StringUtil.isNotBlank(resLectureEvaDetail.getRecordFlow())){
                resLectureEvaDetail.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				resLectureEvaDetail.setModifyUserFlow(userFlow);
				resLectureEvaDetail.setModifyTime(DateUtil.getCurrDateTime());
				return lectureEvaDetailMapper.updateByPrimaryKeySelective(resLectureEvaDetail);
			}else{
				resLectureEvaDetail.setRecordFlow(PkUtil.getUUID());
                resLectureEvaDetail.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
                regist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				regist.setModifyUserFlow(regist.getOperUserFlow());
				regist.setModifyTime(DateUtil.getCurrDateTime());
				return lectureScanRegistMapper.updateByPrimaryKeySelective(regist);
			}else{
				regist.setRecordFlow(PkUtil.getUUID());
                regist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andAttendDateEqualTo(nowDay).andDoctorFlowEqualTo(userFlow);
		example.setOrderByClause("ATTEND_TIME DESC,CREATE_TIME DESC");
		return jsresAttendanceDetailMapper.selectByExample(example);
	}
	@Override
	public JsresAttendance getJsresAttendance(String nowDay, String userFlow) {
		JsresAttendanceExample example=new JsresAttendanceExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
                score.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				score.setModifyUserFlow(user.getUserFlow());
				score.setModifyTime(DateUtil.getCurrDateTime());
				return this.scoreMapper.updateByPrimaryKeySelective(score);
			}else{//新增
				score.setScoreFlow(PkUtil.getUUID());
                score.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				score.setCreateUserFlow(user.getUserFlow());
				score.setCreateTime(DateUtil.getCurrDateTime());
				score.setModifyUserFlow(user.getUserFlow());
				score.setModifyTime(DateUtil.getCurrDateTime());
				return this.scoreMapper.insertSelective(score);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public Map<String, SchDoctorDept> getReductionDeptMap(String doctorFlow, String rotationFlow, String secondRotationFlow) {
		Map<String,SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();

		List<SchDoctorDept> doctorDeptList = searchReductionDept(doctorFlow,rotationFlow,secondRotationFlow);
		if(doctorDeptList!=null && !doctorDeptList.isEmpty()){
			doctorDeptMap = new HashMap<String, SchDoctorDept>();
			for(SchDoctorDept sdd : doctorDeptList){
				String key = sdd.getGroupFlow()+sdd.getStandardDeptId();
				doctorDeptMap.put(key, sdd);
			}
		}
		return doctorDeptMap;
	}
	@Override
	public List<SchDoctorDept> searchReductionDept(String doctorFlow,String rotationFlow,String secondRotationFlow){
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
        doctorDeptExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andDoctorFlowEqualTo(doctorFlow).andRotationFlowIn(flows);
		return doctorDeptMapper.selectByExample(doctorDeptExample);
	}
	@Override
	public List<DeptTeacherGradeInfo> searchAllGrade(String userFlow) {
		List<String> recTypes = new ArrayList<String>();
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        recTypes.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
		DeptTeacherGradeInfoExample example = new DeptTeacherGradeInfoExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
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
				logger.error("", e);
			}
		}
		return gradeMap;
	}
	@Override
	public ResDoctorSchProcess getProcessByResultFlow(String resultFlow) {
		ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSchResultFlowEqualTo(resultFlow);
		List<ResDoctorSchProcess> processList = processMapper.selectByExample(example);
		if(processList.size()>0){
			return processList.get(0);
		}
		return null;
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
	//获取系统配置信息
	@Override
	public String getCfgCode(String code){
		if(StringUtil.isNotBlank(code)){

			String v= GlobalUtil.getLocalCfgMap().get(code);
			if(StringUtil.isNotBlank(v)){
				return v;
			}
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
	public List<SysDept> getHeadDeptList(String userFlow, String deptFlow) {
		return sysDeptExtMapper.getHeadDeptList(userFlow,deptFlow);
	}
	@Override
	public void addActivityImage(ActivityImageFileForm form, SysUser user) {

		String cfg = getCfgCode("upload_base_dir");
		String dateString = DateUtil.getCurrDate2();
		String newDir = cfg+ File.separator+"activityFlie"+File.separator +dateString ;
		String fileName = form.getFileName();
		String preffix = PkUtil.getUUID();//fileName.substring(0,fileName.lastIndexOf("."));
		String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
		fileName=preffix+suffix;
		File fileDir = new File(newDir);
		if(!fileDir.exists()){
			fileDir.mkdirs();
		}
		try {
			String imgDataString = StringUtil.defaultIfEmpty(form.getUploadFileData(),"");
			byte[] data = null;
			if(StringUtil.isNotBlank(imgDataString)){
				BASE64Decoder decoder = new BASE64Decoder();
				// Base64解码
				data = decoder.decodeBuffer(imgDataString);
				for (int i = 0; i < data.length; ++i) {
					if (data[i] < 0) {// 调整异常数据
						data[i] += 256;
					}
				}
			}
			if(data==null){
				data = form.getUploadFile().getBytes();
			}
			File imageFile = new File(fileDir,fileName);
			if(form.getUploadFile()!=null){
				form.getUploadFile().transferTo(imageFile);
			}else {
				FileOutputStream fos = new FileOutputStream(imageFile);
				fos.write(data);
				fos.flush();
				fos.close();
			}

			FtpHelperUtil ftpHelperUtil=new FtpHelperUtil();
			String localFilePath=fileDir+File.separator+fileName;
			String ftpDir= "activityFlie"+File.separator +dateString ;
			String ftpFileName=fileName;
			ftpHelperUtil.uploadFile(localFilePath,ftpDir,ftpFileName);

			String url = getCfgCode("upload_base_url")+"/activityFlie/"+dateString+"/"+fileName;
			if(StringUtil.isNotBlank(form.getActivityFlow())){
				TeachingActivityInfo resRec = activityBiz.readActivityInfo(form.getActivityFlow());
				String content =resRec.getImageUrl();
				if(StringUtil.isBlank(content))
				{
					Document dom = DocumentHelper.createDocument();
					Element root= dom.addElement("ActivityImages");
					Element elem=root.addElement("image");
					String imageFlow=PkUtil.getUUID();
					elem.addAttribute("imageFlow",imageFlow);
					Element imageUrl=elem.addElement("imageUrl");
					Element thumbUrl=elem.addElement("thumbUrl");
					imageUrl.setText(url);
					thumbUrl.setText(url);
					content=root.asXML();
					resRec.setImageUrl(content);
					activityBiz.saveActivityInfo(resRec,user);
				}else{
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
						resRec.setImageUrl(document.asXML());
						activityBiz.saveActivityInfo(resRec,user);
					}
				}
			}
		} catch (FileNotFoundException e) {
			logger.error("", e);
		}
		catch (IOException e) {
			logger.error("", e);
		} catch (DocumentException e) {
			logger.error("", e);
		}
	}

	@Override
	public void deleteActivityImage(SysUser user, String activityFlow, String imageFlow) throws DocumentException {
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		if(StringUtil.isNotBlank(activity.getImageUrl())) {
			String content = activity.getImageUrl();
			Document document = DocumentHelper.parseText(content);
			Element elem = document.getRootElement();
			Node delNode = elem.selectSingleNode("image[@imageFlow='" + imageFlow + "']");
			if (delNode != null) {
				delNode.detach();
				activity.setImageUrl(document.asXML());
				activityBiz.saveActivityInfo(activity,user);
			}
		}
	}
	@Override
	public List<ResLectureInfo> SearchNewLectures(String orgFlow, String roleId, String roleFlow){
		List<ResLectureInfo> lectureInfos = resLectureInfoExtMapper.searchLecturesList(orgFlow,roleId,roleFlow);
		return lectureInfos;
	}
	@Override
	public List<ResLectureScanRegist> searchIsScan(String lectureFlow) {
		ResLectureScanRegistExample example = new ResLectureScanRegistExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andLectureFlowEqualTo(lectureFlow).
				andIsScanIsNotNull();
		return lectureScanRegistMapper.selectByExample(example);
	}
	@Override
	public synchronized int editLectureScanRegist(String lectureFlow, SysUser currUser, ResLectureScanRegist regist, ResDoctor doctor){
		int count=resLectureInfoExtMapper.checkRegistNum(lectureFlow);
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
            lectureScanRegist.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
            lectureScanRegist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_Y);
			return lectureScanRegistMapper.insertSelective(lectureScanRegist);
		}else{
			lectureScanRegist=regist;
            lectureScanRegist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_Y);
			return lectureScanRegistMapper.updateByPrimaryKey(lectureScanRegist);
		}
	}
	@Override
	public List<SchArrangeResult> getSchArrangeResult(String userFlow,String orgFlow,Integer pageIndex,Integer pageSize) {
		if(pageIndex!=null && pageSize!=null){
			PageHelper.startPage(pageIndex, pageSize);
		}
		SchArrangeResultExample example = new SchArrangeResultExample();
        example.createCriteria().andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("SCH_START_DATE");
		return resultMapper.selectByExample(example);
	}
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


	@Override
	public List<SysDict> getDictListByDictId(String dictTypeId) {
		if(StringUtil.isNotBlank(dictTypeId)){
			SysDictExample example = new SysDictExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDictTypeIdEqualTo(dictTypeId);
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
	public ResLectureRandomSign readLectureRandomSign(String randomFlow) {
		return lectureRandomSignMapper.selectByPrimaryKey(randomFlow);
	}

	@Override
	public ResLectureRandomScan readLectureRandomScan(String userFlow, String randomFlow) {
		ResLectureRandomScanExample example=new ResLectureRandomScanExample();
        example.createCriteria().andOperUserFlowEqualTo(userFlow).andRandomFlowEqualTo(randomFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
                scan.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				scan.setModifyUserFlow(scan.getOperUserFlow());
				scan.setModifyTime(DateUtil.getCurrDateTime());
				return lectureRandomScanMapper.updateByPrimaryKeySelective(scan);
			}else{
				scan.setRecordFlow(PkUtil.getUUID());
                scan.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
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
  