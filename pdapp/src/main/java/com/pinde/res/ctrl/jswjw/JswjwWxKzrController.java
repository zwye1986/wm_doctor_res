package com.pinde.res.ctrl.jswjw;

import com.pinde.app.common.GeneralController;
import com.pinde.app.common.InitConfig;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.*;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.hbres.IResInprocessInfoBiz;
import com.pinde.res.biz.jswjw.*;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.model.jswjw.mo.FromItem;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.res.model.jswjw.mo.ResAssessCfgItemForm;
import com.pinde.res.model.jswjw.mo.ResAssessCfgTitleForm;
import com.pinde.sci.dao.base.JsresPowerCfgMapper;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/jswjw/wx/kzr")
public class JswjwWxKzrController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(JswjwWxKzrController.class);

	@Autowired
	private IJswjwBiz jswjwBiz;
	@Autowired
	private IResGradeBiz gradeBiz;
	@Autowired
	private IJswjwTeacherBiz jswjwTeacherBiz;
	@Autowired
	private IJswjwKzrBiz jswjwKzrBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IResInprocessInfoBiz resInprocessInfoBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IInxInfoBiz inxInfoBiz;
	@Autowired
	private IResActivityBiz activityBiz;
	@Autowired
	private IResActivityTargetBiz activityTargeBiz;
	@Autowired
	private ResPaperBiz paperBiz;
	@Autowired
	private JsresPowerCfgMapper jsresPowerCfgMapper;
	@Autowired
	IIeaveAppBiz ieaveAppBiz;


	@RequestMapping(value={"/index"},method = {RequestMethod.POST})
	@ResponseBody
	public Object index(String userFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		resultMap.put("userInfo",userinfo);
		//logger.debug("1========================================="+System.currentTimeMillis()+"===================");
		//培训学员总数
		int conut=iResDoctorProcessBiz.schProcessStudentDistinctQuery("",userFlow,"");
        int cconut = iResDoctorProcessBiz.schProcessStudentDistinctQuery("", userFlow, com.pinde.core.common.GlobalConstant.FLAG_Y);
		//logger.debug("2========================================="+System.currentTimeMillis()+"===================");
		resultMap.put("count",conut);
		resultMap.put("ccount",cconut);
		//所属基地是否付费
		String isChargeOrg=jswjwBiz.getJsResCfgCode("jsres_"+userinfo.getOrgFlow()+"_guocheng");
		resultMap.put("isChargeOrg",isChargeOrg);

        resultMap.put("trainingTypes", com.pinde.core.common.enums.TrainCategoryEnum.values());

		List<Map<String,String>> typeList = new ArrayList<>();
		Map<String,String> typeMap = new HashMap<>();
        typeMap.put("doctorTypeId", com.pinde.core.common.enums.ResDocTypeEnum.Company.getId());
        typeMap.put("doctorTypeName", com.pinde.core.common.enums.ResDocTypeEnum.Company.getName());
		typeList.add(typeMap);
		typeMap = new HashMap<>();
        typeMap.put("doctorTypeId", com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getId());
        typeMap.put("doctorTypeName", com.pinde.core.common.enums.ResDocTypeEnum.CompanyEntrust.getName());
		typeList.add(typeMap);
		typeMap = new HashMap<>();
        typeMap.put("doctorTypeId", com.pinde.core.common.enums.ResDocTypeEnum.Social.getId());
        typeMap.put("doctorTypeName", com.pinde.core.common.enums.ResDocTypeEnum.Social.getName());
		typeList.add(typeMap);
		typeMap = new HashMap<>();
        typeMap.put("doctorTypeId", com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
        typeMap.put("doctorTypeName", com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getName());
		typeList.add(typeMap);
		resultMap.put("doctorTypes", typeList);

		List<Map<String,Object>> dictMapList = new ArrayList<>();
		Map<String,Object> dictMap = new HashMap<>();
        dictMap.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
        dictMap.put("trainingTypeName", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getName());
		List<SysDict> dictList = new ArrayList<>();
		SysDict dict = new SysDict();
		dict.setDictId("");
		dict.setDictName("全部");
		dictList.add(dict);
        dictList.addAll(jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId()));
		dictMap.put("dictList",dictList);
		dictMapList.add(dictMap);

		dictMap = new HashMap<>();
        dictMap.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId());
        dictMap.put("trainingTypeName", com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getName());
		dictList = new ArrayList<>();
		dictList.add(dict);
        dictList.addAll(jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId()));
		dictMap.put("dictList",dictList);
		dictMapList.add(dictMap);

		dictMap = new HashMap<>();
        dictMap.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId());
        dictMap.put("trainingTypeName", com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getName());
		dictList = new ArrayList<>();
		dictList.add(dict);
        dictList.addAll(jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId()));
		dictMap.put("dictList",dictList);
		dictMapList.add(dictMap);

		dictMap = new HashMap<>();
        dictMap.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());
        dictMap.put("trainingTypeName", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getName());
		dictList = new ArrayList<>();
		dictList.add(dict);
        dictList.addAll(jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId()));
		dictMap.put("dictList",dictList);
		dictMapList.add(dictMap);
		resultMap.put("dictMap", dictMapList);

//		dictMap.put(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId(),jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId()));
//		dictMap.put(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId(),jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId()));
//		dictMap.put(com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId(),jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId()));
//		dictMap.put(com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId(),jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId()));
//		resultMap.put("dictMap", dictMap);

        List<Map<String, String>> infos = this.noticeBiz.searchInfoByOrgNotRead("", com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
		if(infos!=null)
		{
			resultMap.put("hasNotReadInfo",infos.size());
		}else{
			resultMap.put("hasNotReadInfo",0);
		}
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
//		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		if(userinfo != null && StringUtil.isNotBlank(userinfo.getUserHeadImg())) {
			resultMap.put("userHeadImage", uploadBaseUrl + "/" + userinfo.getUserHeadImg());
		}else{
			resultMap.put("userHeadImage", "");
		}
		// 查询师资权限
		getTeacherAuthorityInfo(resultMap,userFlow,userinfo.getOrgFlow());
		return resultMap;
	}

	private void getTeacherAuthorityInfo(Map<String,Object> resultMap, String userFlow,String orgFlow) {
		boolean isActivity = false;
		boolean isAttendance = false;
		boolean jzxxgl = false;
//        boolean pxsjsh = false;
		boolean pxsjsh = true;
		boolean sxpjcx = false;
		boolean zpyjfk = false;
		if (StringUtil.isNotBlank(userFlow)) {
			SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
			if (sysOrg != null) {
				// 审核通过
				if(CheckStatusEnum.Passed.getId().equals(sysOrg.getCheckStatusId())){
					JsresPowerCfgExample example = new JsresPowerCfgExample();
                    JsresPowerCfgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    criteria.andCfgValueEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					criteria.andCfgCodeLike("%"+orgFlow);
					List<JsresPowerCfg> cfgList = jsresPowerCfgMapper.selectByExample(example);
					if (cfgList != null && cfgList.size() > 0) {
						for (JsresPowerCfg powerCfg: cfgList) {
							String cfgCode = powerCfg.getCfgCode() == null ? "" : powerCfg.getCfgCode();
							if(cfgCode.contains("jsres_hospital_activity_")){
								// 活动
								isActivity = true;
							}else if(cfgCode.contains("jsres_hospital_xykqcx_")){
								// 考勤
								isAttendance = true;
							}else if(cfgCode.contains("jsres_hospital_jzxxgl_")){
								// 讲座信息
								jzxxgl = true;
							}else if(cfgCode.contains("jsres_hospital_pxsjsh_")){
								// 出科审核
								pxsjsh = true;
							}else if(cfgCode.contains("jsres_hospital_sxpjcx_")){
								// 评价查询
								sxpjcx = true;
							}else if(cfgCode.contains("jsres_hospital_zpyjfk_")){
								// 住培意见反馈
								zpyjfk = true;
							}
						}
					}
				}
			}
		}
		resultMap.put("isActivity", isActivity);
		resultMap.put("isAttendance", isAttendance);
		resultMap.put("jzxxgl", jzxxgl);
		resultMap.put("pxsjsh", pxsjsh);
		resultMap.put("sxpjcx", sxpjcx);
		resultMap.put("zpyjfk", zpyjfk);
	}
	/**
	 * 学员列表
	 * @param userFlow
	 * @param roleId
	 * @param studentName
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = {"/studentList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object studentList(String userFlow,String roleId,String seachStr,String deptFlow,String doctorFlow,
							  String studentName, String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,String statusId,
							  String typeId,String yearMonth,
							  Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(doctorFlow)){
			return ResultDataThrow("doctorFlow为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		if("waitSch".equals(typeId)&&StringUtil.isBlank(yearMonth))
		{
			yearMonth=DateUtil.addMonth(DateUtil.getMonth(),1);
		}
		if("waitSch".equals(typeId)&&StringUtil.isNotBlank(yearMonth))
		{
			yearMonth=DateUtil.addMonth(yearMonth,1);
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		if(StringUtil.isBlank(deptFlow))
		{
			deptFlow=userinfo.getDeptFlow();
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
//		schArrangeResultMap.put("deptFlow", deptFlow);
		schArrangeResultMap.put("doctorFlow", doctorFlow);
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("typeId", typeId);
		schArrangeResultMap.put("yearMonth", yearMonth);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
//		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.schDoctorSchProcessHead(schArrangeResultMap);

		Map<String, SchRotationDept> schRotationDeptMap=new HashMap<String, SchRotationDept>();
		Map<String,Object> resRecMap=new HashMap<String,Object>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		Map<String,String> schScoreMap=new HashMap<String,String>();
		if(resDoctorSchProcess!=null&&resDoctorSchProcess.size()>0) {
			for (Map<String, String> map : resDoctorSchProcess) {
				Map<String,Object> perMap = jswjwBiz.getRecProgressIn(map.get("userFlow"), map.get("processFlow"),null,null);
				if(perMap!=null) {
					Object o=perMap.get(map.get("processFlow"));
					String per=(null==o)?"0":String.valueOf(perMap.get(map.get("processFlow")));
					map.put("per", per);
				}
				SchArrangeResult schArrangeResult = jswjwTeacherBiz.readSchArrangeResult(map.get("schResultFlow"));
				if (schArrangeResult != null) {
					SchRotationDept schRotationDept = jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(), schArrangeResult.getStandardDeptId());
					if (schRotationDept != null) {
						schRotationDeptMap.put(map.get("processFlow"), schRotationDept);
					}
				}

				List<String> recTypeIds = new ArrayList<>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"), recTypeIds);

				if (expressList != null && expressList.size() > 0) {
					for (ResSchProcessExpress express : expressList) {

                        if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(express.getRecTypeId())) {
							resRecMap.put(map.get("processFlow"), express);
							Map<String, Object> formDataMap = null;
							if (express != null) {
								String recContent = express.getRecContent();
								formDataMap = jswjwTeacherBiz.parseRecContent(recContent);

								boolean f = false;
								f = jswjwBiz.getCkkPower(map.get("userFlow"));
								if (f) {
									ResScore outScore = jswjwTeacherBiz.readScoreByProcessFlow(map.get("processFlow"));
									if (outScore != null) {
										String score = "0";
										if (null != outScore.getTheoryScore()) {
											score = outScore.getTheoryScore().toString();
										}
										schScoreMap.put(map.get("processFlow") + "schScore", score);
									} else {
										schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
									}
								} else {
									schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
								}
							}
						}
						String key = express.getProcessFlow() + express.getRecTypeId();
						resRecMap.put(key, express);
					}
				}
			}
		}
//		model.addAttribute("resRecMap", resRecMap);
//		model.addAttribute("resRecCountMap", resRecCountMap);
//		model.addAttribute("schRotationDeptMap", schRotationDeptMap);
//		model.addAttribute("schScoreMap", schScoreMap);
//		model.addAttribute("list",resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != resDoctorSchProcess && resDoctorSchProcess.size()>0){
			for (Map<String, String> m : resDoctorSchProcess) {
				Map<String,Object> map = new HashMap<>();
//				String dopsKey = m.get("processFlow")+"DOPS";
//				String miniKey = m.get("processFlow")+"Mini_CEX";
//				String afterKey = m.get("processFlow")+"AfterEvaluation";
				String scoreKey = m.get("processFlow")+"schScore";
				String currDate = DateUtil.getCurrDate();
				map.put("currDate", currDate);
				map.put("userName",m.get("userName"));
				map.put("schStartDate",m.get("schStartDate"));
				map.put("schEndDate",m.get("schEndDate"));
				map.put("schScore",StringUtil.isBlank(schScoreMap.get(scoreKey)) ? "暂无" : schScoreMap.get(scoreKey));
				map.put("docFlow",m.get("userFlow"));
				map.put("schResultFlow",m.get("schResultFlow"));
				map.put("resultFlow",m.get("resultFlow"));
				map.put("sessionNumber",m.get("sessionNumber"));
				map.put("speName",m.get("speName"));
				map.put("teacherUserName",m.get("teacherUserName"));
				map.put("processFlow",m.get("processFlow"));
				map.put("orgFlow",m.get("orgFlow"));
				map.put("orgName",m.get("orgName"));
				map.put("deptFlow",m.get("deptFlow"));
				map.put("deptName",m.get("deptName"));
				map.put("schDeptFlow",m.get("schDeptFlow"));
				map.put("schDeptName",m.get("schDeptName"));
				map.put("recordFlow",null == schRotationDeptMap.get(m.get("processFlow")) ? "" : schRotationDeptMap.get(m.get("processFlow")).getRecordFlow());
				map.put("per", StringUtil.isBlank(m.get("per")) ? "0" : m.get("per"));
				map.put("schType",m.get("statusId"));
				map.put("schStatus",m.get("statusName"));
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/userList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object userList(String userFlow,String roleId,String seachStr,String deptFlow,String doctorFlow,
						   String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,String statusId,
						   String typeId,String yearMonth,
						   Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		if (StringUtil.isBlank(yearMonth)) {
			return ResultDataThrow("日期为空");
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(yearMonth);
		} catch (Exception e) {
			return ResultDataThrow("yearMonth格式有误");
		}
		if("waitSch".equals(typeId))
		{
			yearMonth=DateUtil.addMonth(yearMonth,1);
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		if(StringUtil.isBlank(deptFlow))
		{
			deptFlow=userinfo.getDeptFlow();
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
//		schArrangeResultMap.put("deptFlow", deptFlow);
		schArrangeResultMap.put("doctorFlow", doctorFlow);
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("typeId", typeId);
		schArrangeResultMap.put("yearMonth", yearMonth);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.schDoctorSchProcessHeadUserList(schArrangeResultMap);

		resultMap.put("list",resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		return resultMap;
	}

	@RequestMapping(value = {"/afterUserList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object afterUserList(String userFlow,String roleId,String seachStr,
								String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,
								Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.afterUserList(schArrangeResultMap);

		resultMap.put("list",resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		return resultMap;
	}

	@RequestMapping(value = {"/afterAuditList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object afterAuditList(String userFlow,String roleId,String doctorFlow) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(doctorFlow)){
			return ResultDataThrow("学员流水号为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}

		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("doctorFlow", doctorFlow);
		resDoctorSchProcess=jswjwKzrBiz.afterAuditList(schArrangeResultMap);

		Map<String, SchRotationDept> schRotationDeptMap=new HashMap<String, SchRotationDept>();
		Map<String,ResSchProcessExpress> resRecMap=new HashMap<String,ResSchProcessExpress>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		Map<String,String> schScoreMap=new HashMap<String,String>();
		if(resDoctorSchProcess!=null&&resDoctorSchProcess.size()>0) {
			for (Map<String, String> map : resDoctorSchProcess) {
				Map<String,Object> perMap = jswjwBiz.getRecProgressIn(map.get("userFlow"), map.get("processFlow"),null,null);
				if(perMap!=null) {
					Object o=perMap.get(map.get("processFlow"));
					String per=(null==o)?"0":String.valueOf(perMap.get(map.get("processFlow")));
					map.put("per", per);
				}
				SchArrangeResult schArrangeResult = jswjwTeacherBiz.readSchArrangeResult(map.get("schResultFlow"));
				if (schArrangeResult != null) {
					SchRotationDept schRotationDept = jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(), schArrangeResult.getStandardDeptId());
					if (schRotationDept != null) {
						schRotationDeptMap.put(map.get("processFlow"), schRotationDept);
					}
				}

				List<String> recTypeIds = new ArrayList<>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"),  recTypeIds);

				if (expressList != null && expressList.size() > 0) {
					for (ResSchProcessExpress express : expressList) {

                        if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(express.getRecTypeId())) {
							resRecMap.put(map.get("processFlow"), express);
							Map<String, Object> formDataMap = null;
							if (express != null) {
								String recContent = express.getRecContent();
								formDataMap = jswjwTeacherBiz.parseRecContent(recContent);

								boolean f = false;
								f = jswjwBiz.getCkkPower(map.get("userFlow"));
								if (f) {
									ResScore outScore = jswjwTeacherBiz.readScoreByProcessFlow(map.get("processFlow"));
									if (outScore != null) {
										String score = "0";
										if (null != outScore.getTheoryScore()) {
											score = outScore.getTheoryScore().toString();
										}
										schScoreMap.put(map.get("processFlow") + "schScore", score);
									} else {
										schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
									}
								} else {
									schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
								}
							}
						}
						String key = express.getProcessFlow() + express.getRecTypeId();
						resRecMap.put(key, express);
					}
				}
			}
		}
//		resultMap.put("resRecMap", resRecMap);
//		resultMap.put("resRecCountMap", resRecCountMap);
//		resultMap.put("schRotationDeptMap", schRotationDeptMap);
//		resultMap.put("schScoreMap", schScoreMap);
//		resultMap.put("list",resDoctorSchProcess);

		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != resDoctorSchProcess && resDoctorSchProcess.size()>0){
			for (Map<String,String> process:resDoctorSchProcess) {
				Map<String,Object> map = new HashMap<>();
				String processFlow = process.get("processFlow");
				String scoreKey = processFlow + "schScore";
				String currDate = DateUtil.getCurrDate();
				map.put("userName",process.get("userName"));
				map.put("schStartDate",process.get("schStartDate"));
				map.put("schEndDate",process.get("schEndDate"));
				map.put("userHeadImg",process.get("userHeadImg"));
				map.put("afterRecFlow",null == resRecMap ? "" : null == resRecMap.get(processFlow) ? "" : resRecMap.get(processFlow).getRecFlow());
				map.put("schScore", null == schScoreMap ? "暂无" : null == schScoreMap.get(scoreKey) ? "暂无" : schScoreMap.get(scoreKey));
				map.put("docFlow",process.get("userFlow"));
				map.put("schResultFlow",process.get("schResultFlow"));
				map.put("resultFlow",process.get("resultFlow"));
				map.put("sessionNumber",process.get("sessionNumber"));
				map.put("speName",process.get("speName"));
				map.put("teacherUserName",process.get("teacherUserName"));
				map.put("processFlow",process.get("processFlow"));
				map.put("deptFlow",process.get("deptFlow"));
				map.put("deptName",process.get("deptName"));
				map.put("schDeptFlow",process.get("schDeptFlow"));
				map.put("schDeptName",process.get("schDeptName"));
				map.put("recordFlow",null == schRotationDeptMap ? "" : null == schRotationDeptMap.get(processFlow) ? "" : schRotationDeptMap.get(processFlow).getRecordFlow());
				map.put("per", null == process.get("per") ? "0" : (String)process.get("per"));
				map.put("currDate",currDate);
				map.put("schType",process.get("statusId"));
				map.put("schStatus",process.get("statusName"));
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/attendList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object attendList(String userFlow,String roleId,String seachStr,String yearMonth,
							 Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		if (StringUtil.isBlank(yearMonth)) {
			return ResultDataThrow("日期为空");
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(yearMonth);
		} catch (Exception e) {
			return ResultDataThrow("yearMonth格式有误");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("yearMonth", yearMonth);
		schArrangeResultMap.put("nowDate", DateUtil.getCurrDate());
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.attendList(schArrangeResultMap);

		resultMap.put("list",resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		return resultMap;
	}

	@RequestMapping(value = {"/deptUsers"},method = {RequestMethod.POST})
	@ResponseBody
	public Object deptUsers(String userFlow,String deptFlow,String seachStr,
							Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		String cfgTeacher=jswjwBiz.getCfgCode("res_teacher_role_flow");
		List<Map<String,String>> list=null;
		Map<String, String> param=new HashMap<String, String>();
//		param.put("deptFlow", deptFlow);
		param.put("userFlow", userFlow);
		param.put("seachStr", seachStr);
		param.put("roleFlow", cfgTeacher);
		PageHelper.startPage(pageIndex, pageSize);
		list=jswjwKzrBiz.deptUsers(param);
		resultMap.put("dataCount", PageHelper.total);
//		resultMap.put("list",list);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		Map<String,Object> map = null;
		if(pageIndex == 1) {
			map = new HashMap<>();
			map.put("teacherFlow", "");
			map.put("userName", "全部");
			map.put("deptFlow", "");
			map.put("deptName", "");
			resultMapList.add(map);
		}
		if(null != list && list.size()>0){
			for (Map<String,String> m:list) {
				map = new HashMap<>();
				map.put("teacherFlow",m.get("userFlow"));
				map.put("userName",m.get("userName"));
				map.put("deptFlow",m.get("deptFlow"));
				map.put("deptName",m.get("deptName"));
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList", resultMapList);

		int count=jswjwKzrBiz.deptUsersDocCount(param);
		resultMap.put("count", count);
		int tcount=jswjwKzrBiz.deptUsersCount(param);
		resultMap.put("tcount", tcount);
		return resultMap;
	}

	@RequestMapping(value = {"/deptTeacherUsers"},method = {RequestMethod.POST})
	@ResponseBody
	public Object deptTeacherUsers(String userFlow,String deptFlow,String teacherFlow,String seachStr,
								   Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		String cfgTeacher=jswjwBiz.getCfgCode("res_teacher_role_flow");
		List<Map<String,String>> list=null;
		Map<String, String> param=new HashMap<String, String>();
//		param.put("deptFlow", deptFlow);
		param.put("teacherFlow", teacherFlow);
		param.put("userFlow", userFlow);
		param.put("seachStr", seachStr);
		param.put("roleFlow", cfgTeacher);
		PageHelper.startPage(pageIndex, pageSize);
		list=jswjwKzrBiz.deptTeacherUsers(param);
		resultMap.put("list",list);
		resultMap.put("dataCount", PageHelper.total);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		return resultMap;
	}

	@RequestMapping(value = {"/deptTeacherDocList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object deptTeacherDocList(String userFlow,String doctorFlow,String teacherFlow,String seachStr,
									 Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(doctorFlow)){
			return ResultDataThrow("doctorFlow为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		String cfgTeacher=jswjwBiz.getCfgCode("res_teacher_role_flow");
		List<Map<String,String>> list=null;
		Map<String, String> param=new HashMap<String, String>();
//		param.put("deptFlow", deptFlow);
		param.put("doctorFlow", doctorFlow);
		param.put("teacherFlow", teacherFlow);
		param.put("userFlow", userFlow);
		param.put("seachStr", seachStr);
		param.put("roleFlow", cfgTeacher);
		PageHelper.startPage(pageIndex, pageSize);
		list=jswjwKzrBiz.deptTeacherDocList(param);
		Map<String, SchRotationDept> schRotationDeptMap=new HashMap<String, SchRotationDept>();
		Map<String,ResSchProcessExpress> resRecMap=new HashMap<String,ResSchProcessExpress>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		Map<String,String> schScoreMap=new HashMap<String,String>();
		if(list!=null&&list.size()>0) {
			for (Map<String, String> map : list) {
				Map<String,Object> perMap = jswjwBiz.getRecProgressIn(map.get("userFlow"), map.get("processFlow"),null,null);
				if(perMap!=null) {
					Object o=perMap.get(map.get("resultFlow"));
					String per=(null==o)?"0":String.valueOf(perMap.get(map.get("resultFlow")));
					map.put("per", per);
				}
				SchArrangeResult schArrangeResult = jswjwTeacherBiz.readSchArrangeResult(map.get("schResultFlow"));
				if (schArrangeResult != null) {
					SchRotationDept schRotationDept = jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(), schArrangeResult.getStandardDeptId());
					if (schRotationDept != null) {
						schRotationDeptMap.put(map.get("processFlow"), schRotationDept);
					}
				}

				List<String> recTypeIds = new ArrayList<>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"), recTypeIds);

				if (expressList != null && expressList.size() > 0) {
					for (ResSchProcessExpress express : expressList) {

                        if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(express.getRecTypeId())) {
							resRecMap.put(map.get("processFlow"), express);
							Map<String, Object> formDataMap = null;
							if (express != null) {
								String recContent = express.getRecContent();
								formDataMap = jswjwTeacherBiz.parseRecContent(recContent);

								boolean f = false;
								f = jswjwBiz.getCkkPower(map.get("userFlow"));
								if (f) {
									ResScore outScore = jswjwTeacherBiz.readScoreByProcessFlow(map.get("processFlow"));
									if (outScore != null) {
										String score = "0";
										if (null != outScore.getTheoryScore()) {
											score = outScore.getTheoryScore().toString();
										}
										schScoreMap.put(map.get("processFlow") + "schScore", score);
									} else {
										schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
									}
								} else {
									schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
								}
							}
						}
						String key = express.getProcessFlow() + express.getRecTypeId();
						resRecMap.put(key, express);
					}
				}
			}
		}
//		resultMap.put("resRecMap", resRecMap);
//		resultMap.put("resRecCountMap", resRecCountMap);
//		resultMap.put("schRotationDeptMap", schRotationDeptMap);
//		resultMap.put("schScoreMap", schScoreMap);
//		resultMap.put("list",list);
		resultMap.put("dataCount", PageHelper.total);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != list && list.size()>0){
			for (Map<String,String> m:list) {
				Map<String,Object> map = new HashMap<>();
				String processFlow = m.get("processFlow");
				String dopsKey = processFlow+"DOPS";
				String miniKey = processFlow+"Mini_CEX";
				String afterKey = processFlow+"AfterEvaluation";
				String scoreKey = processFlow+"schScore";
				String currDate = DateUtil.getCurrDate();
				map.put("userName",m.get("userName"));
				map.put("schStartDate",m.get("schStartDate"));
				map.put("schEndDate",m.get("schEndDate"));
				map.put("afterRecFlow",null == resRecMap ? "" : null == resRecMap.get("processFlow") ? "" : resRecMap.get("processFlow").getRecFlow());
				map.put("schScore", null == schScoreMap ? "暂无" : null == schScoreMap.get(scoreKey) ? "暂无" : schScoreMap.get(scoreKey));
				map.put("docFlow",m.get("userFlow"));
				map.put("schResultFlow",m.get("schResultFlow"));
				map.put("resultFlow",m.get("resultFlow"));
				map.put("sessionNumber",m.get("sessionNumber"));
				map.put("speName",m.get("speName"));
				map.put("teacherUserName",m.get("teacherUserName"));
				map.put("processFlow",m.get("processFlow"));
				map.put("orgFlow",m.get("orgFlow"));
				map.put("orgName",m.get("orgName"));
				map.put("deptFlow",m.get("deptFlow"));
				map.put("deptName",m.get("deptName"));
				map.put("schDeptFlow",m.get("schDeptFlow"));
				map.put("schDeptName",m.get("schDeptName"));
				map.put("recordFlow",null == schRotationDeptMap ? "" : null == schRotationDeptMap.get(processFlow) ? "" : schRotationDeptMap.get(processFlow).getRecordFlow());
				map.put("per", StringUtil.isBlank(m.get("per")) ? "0" : m.get("per"));
				map.put("currDate",currDate);
				map.put("schType",m.get("statusId"));
				map.put("schStatus",m.get("statusName"));
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	/**
	 * 科室列表
	 * @param userFlow
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/deptList"},method={RequestMethod.POST})
	@ResponseBody
	public Object deptList(String userFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		SysUser user=jswjwBiz.readSysUser(userFlow);
		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,user.getDeptFlow());

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		for(SysDept result:depts) {
			Map<String,Object> map = new HashMap<>();
			map.put("deptFlow",result.getDeptFlow());
			map.put("deptName",result.getDeptName());
			ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(result.getDeptFlow(), result.getOrgFlow());
			if(null == info){
                map.put("canShowInfo", com.pinde.core.common.GlobalConstant.FLAG_N);
			}else {
                map.put("canShowInfo", com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
			resultMapList.add(map);
		}
//		resultMap.put("depts", depts);
		resultMap.put("resultMapList", resultMapList);
		return resultMap;
	}

	@RequestMapping(value={"/inProcessInfo"},method={RequestMethod.POST})
	@ResponseBody
	public Object inProcessInfo(String userFlow,String deptFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(deptFlow)){
			return ResultDataThrow("科室标识符为空");
		}
		SysDept dept=jswjwBiz.readSysDept(deptFlow);
		if(dept==null) {
			return ResultDataThrow("科室信息不存在");
		}
		String orgFlow = dept.getOrgFlow();
		ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow, orgFlow);
		resultMap.put("info", info);
		if (info != null) {
			List<ResInprocessInfoMember> members = resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
			resultMap.put("members", members);
			List<PubFile> files = pubFileBiz.searchByProductFlow(info.getRecordFlow());
			resultMap.put("files", files);
			String arrange = info.getTeachingArrangement();
			if (StringUtil.isNotBlank(arrange)) {
				Map<String, Object> arrangeMap = new HashMap<>();
				arrangeMap = paressXml(arrange);
				resultMap.put("arrangeMap", arrangeMap);

				Map<Integer, String> week = new HashMap<>();
				week.put(1, "周一");
				week.put(2, "周二");
				week.put(3, "周三");
				week.put(4, "周四");
				week.put(5, "周五");
				week.put(6, "周六");
				week.put(7, "周日");
				List<Map<String, String>> days = new ArrayList<>();
				for (int i = 1; i <= 7; i++) {
					String addressV = (String) arrangeMap.get("address" + i);
					String contentV = (String) arrangeMap.get("content" + i);
					if (StringUtil.isNotBlank(addressV) || StringUtil.isNotBlank(contentV)) {
						Map<String, String> m = new HashMap<>();
						m.put("address", addressV);
						m.put("content", contentV);
						m.put("dayName", week.get(i));
						days.add(m);
					}
				}
				resultMap.put("days", days);
			}
		}else{
			return ResultDataThrow("科室暂未添加入科教育信息");
		}
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl",uploadBaseUrl);
		return resultMap;
	}

	@RequestMapping(value={"/funcList"},method={RequestMethod.POST})
	@ResponseBody
	public Object funcList(String userFlow,String doctorFlow,String processFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		if(StringUtil.isEmpty(doctorFlow)){
			return ResultDataThrow("学员标识符为空");
		}

		Map<String,ResSchProcessExpress> resRecMap=new HashMap<String,ResSchProcessExpress>();
		List<String> recTypeIds=new ArrayList<>();
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
		List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(processFlow, recTypeIds);
		for(ResSchProcessExpress express:expressList)
		{
			String key =express.getProcessFlow()+express.getRecTypeId();
			resRecMap.put(key, express);
		}
		ResDoctorSchProcess process=iResDoctorProcessBiz.read(processFlow);
		if(process==null){
			return ResultDataThrow("轮转信息不存在");
		}
		String canViewImage = "";
		SchArrangeResult schArrangeResult=jswjwTeacherBiz.readSchArrangeResult(process.getSchResultFlow());
		if (schArrangeResult!=null) {
			SchRotationDept schRotationDept=jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(),schArrangeResult.getStandardDeptId());
//			resultMap.put("schRotationDept",schRotationDept);
			resultMap.put("recordFlow",schRotationDept.getRecordFlow());
			List<Map<String,String>>  dataList =  jswjwBiz.viewImage(doctorFlow,schRotationDept.getRecordFlow());
			if(dataList!=null&&dataList.size()>0) {
                canViewImage = com.pinde.core.common.GlobalConstant.FLAG_Y;
			}
		}
//		resultMap.put("resRecMap", resRecMap);
		resultMap.put("doctorFlow",doctorFlow);
		resultMap.put("processFlow",processFlow);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("order","1");
		map.put("typeName","大病历");
		map.put("recType","mr");
		map.put("reqType","resRecList");
		map.put("recFlow","");
		map.put("statusId","");
		map.put("statusName","");
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("order","2");
		map.put("typeName","病种");
		map.put("recType","disease");
		map.put("reqType","resRecList");
		map.put("recFlow","");
		map.put("statusId","");
		map.put("statusName","");
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("order","3");
		map.put("typeName","操作技能");
		map.put("recType","skill");
		map.put("reqType","resRecList");
		map.put("recFlow","");
		map.put("statusId","");
		map.put("statusName","");
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("order","4");
		map.put("typeName","手术");
		map.put("recType","operation");
		map.put("reqType","resRecList");
		map.put("recFlow","");
		map.put("statusId","");
		map.put("statusName","");
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("order","5");
		map.put("typeName","参加活动");
		map.put("recType","activity");
		map.put("reqType","resRecList");
		map.put("recFlow","");
		map.put("statusId","");
		map.put("statusName","");
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("order","6");
		map.put("typeName","临床操作技能评估量化表(DOPS)");
		map.put("recType","DOPS");
		map.put("reqType","DOPS");
		String dopsKey = processFlow + "DOPS";
		map.put("recFlow",null == resRecMap ? "" : null == resRecMap.get(dopsKey) ? "" : resRecMap.get(dopsKey).getRecFlow());
		map.put("statusId",null == resRecMap ? "notAudit" : null == resRecMap.get(dopsKey) ? "notAudit" : "isAudit");
		map.put("statusName",null == resRecMap ? "未审核" : null == resRecMap.get(dopsKey) ? "未审核" : "已审核");
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("order","7");
		map.put("typeName","迷你临床演练评估量化表(Mini_CEX)");
		map.put("recType","MINI-CEX");
		map.put("reqType","mini_cex");
		String miniKey = processFlow + "Mini_CEX";
		map.put("recFlow",null == resRecMap ? "" : null == resRecMap.get(miniKey) ? "" : resRecMap.get(miniKey).getRecFlow());
		map.put("statusId",null == resRecMap ? "notAudit" : null == resRecMap.get(miniKey) ? "notAudit" : "isAudit");
		map.put("statusName",null == resRecMap ? "未审核" : null == resRecMap.get(miniKey) ? "未审核" : "已审核");
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("order","8");
		map.put("typeName","出科考核表");
		map.put("recType","AfterEvaluation");
		map.put("reqType","afterEvaluation");
		String afterKey = processFlow + "AfterEvaluation";
		map.put("recFlow",null == resRecMap ? "" : null == resRecMap.get(afterKey) ? "" : resRecMap.get(afterKey).getRecFlow());
		map.put("statusId",null == resRecMap ? "notAudit" : null == resRecMap.get(afterKey) ? "notAudit" : "isAudit");
		map.put("statusName",null == resRecMap ? "未审核" : null == resRecMap.get(afterKey) ? "未审核" : "已审核");
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("order","9");
		map.put("typeName","出科考核表附件");
		map.put("recType","viewImage");
		map.put("reqType","viewImage");
		map.put("recFlow", "");
        map.put("statusId", !canViewImage.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) ? "notAudit" : "isAudit");
        map.put("statusName", !canViewImage.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) ? "未上传" : "已上传");
		resultMapList.add(map);
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	/**
	 * o数据列表
	 * @param
	 * @param doctorFlow
	 * @param processFlow
	 * @param recType
	 * @return
	 */
	@RequestMapping(value="/resRecList",method=RequestMethod.POST)
	@ResponseBody
	public Object resRecList(String userFlow,String doctorFlow,String processFlow,String recType,Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(doctorFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		//rec类型转换一下
		String recTypeId=getRecTypeId(recType);
//		resultMap.put("recTypeId",recTypeId);
		if(StringUtil.isBlank(recTypeId)){
			return ResultDataThrow("数据类型为空");
		}
		Map<String, Object> processPerMap = jswjwBiz.getRecProgressIn(doctorFlow, processFlow,null,null);
//		resultMap.put("processPerMap", processPerMap);

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow( "用户不存在");
		}

		if(pageIndex==null){
			return ResultDataThrow( "当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow( "每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResRec> recList=jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow,doctorFlow,recTypeId,"");
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		for(ResRec rec : recList){
			String recContent = rec.getRecContent();
			Map<String, Object> formDataMap = parseRecContent(recContent);
			formDataMap.put("dataFlow", rec.getRecFlow());
			formDataMap.put("auditId", rec.getAuditStatusId());
			formDataMap.put("operTime", DateUtil.transDate(rec.getOperTime()));
			dataList.add(formDataMap);
		}
//		resultMap.put("dataList", dataList);
		resultMap.put("dataCount", PageHelper.total);
        List<ResRec> noAuditList = jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow, doctorFlow, recTypeId, com.pinde.core.common.GlobalConstant.FLAG_Y);
		int count=0;
		if(noAuditList!=null){
			count=noAuditList.size();
		}
		resultMap.put("notAuditNum",count);
		String finishkey = processFlow + recTypeId + "Finished";
		resultMap.put("finishNum",null == processPerMap ? "0" : null == processPerMap.get(finishkey) ? "0" : processPerMap.get(finishkey));
		String reqkey = processFlow + recTypeId + "ReqNum";
		resultMap.put("reqNum",null == processPerMap ? "0" : null == processPerMap.get(reqkey) ? "0" : processPerMap.get(reqkey));

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != dataList && dataList.size()>0){
			for (Map<String,Object> data:dataList) {
				Map<String,Object> map = new HashMap<>();
				if("mr".equals(recType)){
					map.put("dataFlow",data.get("dataFlow"));
					map.put("mr_pName",data.get("mr_pName"));
					map.put("mr_no",data.get("mr_no"));
					map.put("cataFlow",data.get("cataFlow"));
					map.put("operTime",data.get("operTime"));
					map.put("auditId",StringUtil.isNotBlank((String)data.get("auditId")) ? "isAudit" : "notAudit");
					map.put("auditName",StringUtil.isNotBlank((String)data.get("auditId")) ? "已审核" : "未审核");
					map.put("deptFlow",data.get("deptFlow"));
				}
				if("disease".equals(recType)){
					map.put("dataFlow",data.get("dataFlow"));
					map.put("disease_pName",data.get("disease_pName"));
					map.put("disease_mrNo",data.get("disease_mrNo"));
					map.put("disease_pDate",data.get("disease_pDate"));
					map.put("cataFlow",data.get("cataFlow"));
					map.put("operTime",data.get("operTime"));
					map.put("auditId",StringUtil.isNotBlank((String)data.get("auditId")) ? "isAudit" : "notAudit");
					map.put("auditName",StringUtil.isNotBlank((String)data.get("auditId")) ? "已审核" : "未审核");
					map.put("deptFlow",data.get("deptFlow"));
				}
				if("skill".equals(recType)){
					map.put("dataFlow",data.get("dataFlow"));
					map.put("skill_pName",data.get("skill_pName"));
					map.put("skill_mrNo",data.get("skill_mrNo"));
					map.put("skill_operDate",data.get("skill_operDate"));
					map.put("cataFlow",data.get("cataFlow"));
					map.put("operTime",data.get("operTime"));
					map.put("auditId",StringUtil.isNotBlank((String)data.get("auditId")) ? "isAudit" : "notAudit");
					map.put("auditName",StringUtil.isNotBlank((String)data.get("auditId")) ? "已审核" : "未审核");
					map.put("deptFlow",data.get("deptFlow"));
				}
				if("operation".equals(recType)){
					map.put("dataFlow",data.get("dataFlow"));
					map.put("operation_pName",data.get("operation_pName"));
					map.put("operation_mrNo",data.get("operation_mrNo"));
					map.put("operation_operDate",data.get("operation_operDate"));
					map.put("cataFlow",data.get("cataFlow"));
					map.put("operTime",data.get("operTime"));
					map.put("auditId",StringUtil.isNotBlank((String)data.get("auditId")) ? "isAudit" : "notAudit");
					map.put("auditName",StringUtil.isNotBlank((String)data.get("auditId")) ? "已审核" : "未审核");
					map.put("deptFlow",data.get("deptFlow"));
				}
				if("activity".equals(recType)){
					map.put("dataFlow",data.get("dataFlow"));
					map.put("activity_speaker",data.get("activity_speaker"));
					map.put("activity_way",data.get("activity_way"));
					map.put("activity_date",data.get("activity_date"));
					map.put("cataFlow",data.get("cataFlow"));
					map.put("operTime",data.get("operTime"));
					map.put("auditId",StringUtil.isNotBlank((String)data.get("auditId")) ? "isAudit" : "notAudit");
					map.put("auditName",StringUtil.isNotBlank((String)data.get("auditId")) ? "已审核" : "未审核");
					map.put("deptFlow",data.get("deptFlow"));
				}
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}
	/**
	 * 单条数据详情
	 * @param
	 * @param recType
	 * @return
	 */
	@RequestMapping(value="/resRecDeatil",method=RequestMethod.POST)
	@ResponseBody
	public Object resRecDeatil(String userFlow,String recFlow,String deptFlow,String cataFlow,String recType){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(recFlow)){
			return ResultDataThrow("数据标识符为空");
		}
		if(StringUtil.isBlank(recType)){
			return ResultDataThrow("数据类型为空");
		}
		if(StringUtil.isBlank(deptFlow)){
			return ResultDataThrow("科室流水号为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		ResRec rec = jswjwBiz.readResRec(recFlow);
		String recContent = rec.getRecContent();
		_inputList(userFlow, deptFlow, recType,cataFlow, resultMap);
		Map<String, Object> formDataMap = parseRecContent(recContent);
		formDataMap.put("auditId",rec.getAuditStatusId());
		resultMap.put("resultData", formDataMap);
        resultMap.put("isOther", com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
		return resultMap;
	}
	private String getRecTypeId(String recType) {
		String recTypeId = "";
		switch (recType) {
			case "mr":
				recTypeId = RegistryTypeEnum.CaseRegistry.getId();
				break;
			case "disease":
				recTypeId = RegistryTypeEnum.DiseaseRegistry.getId();
				break;
			case "skill":
				recTypeId = RegistryTypeEnum.SkillRegistry.getId();
				break;
			case "operation":
				recTypeId = RegistryTypeEnum.OperationRegistry.getId();
				break;
			case "activity":
				recTypeId = RegistryTypeEnum.CampaignRegistry.getId();
				break;
			case "summary":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId();
				break;
			case "dops":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId();
				break;
			case "miniCex":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId();
				break;
			case "after":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
				break;
			default:
				break;
		}
		return recTypeId;
	}
	public Map<String,Object> parseRecContent(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();;
				for(Element element : elements){

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
							if (StringUtil.isNotBlank(element.attributeValue("id"))) {
								formDataMap.put(element.getName() + "_id", element.attributeValue("id"));
							}
							formDataMap.put(element.getName(), element.getText());
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}
	private void _inputList(String userFlow,String deptFlow,String dataType,String cataFlow ,Map<String,Object> map){
		switch (dataType) {
			case "mr":
				break;
			case "disease":
				List<Map<String,Object>> diseaseDiagNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType, cataFlow);
				map.put("diseaseDiagNameList", diseaseDiagNameList);
				break;
			case "skill":
				List<Map<String,Object>> skillOperNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType,cataFlow);
				map.put("skillOperNameList", skillOperNameList);
				break;
			case "operation":
				List<Map<String,Object>> operationOperNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType,cataFlow);
				map.put("operationOperNameList", operationOperNameList);
				break;
			case "activity":
				break;
			case "summary":
				break;
			default:
				break;
		}
	}
	/**
	 * 查看出科考核上传情况
	 * @param userFlow
	 * @param request
	 * @return
	 */
	@RequestMapping(value={"/viewImage"},method={RequestMethod.POST})
	@ResponseBody
	public Object viewImage(String userFlow,String doctorFlow,String recordFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(doctorFlow)){
			return ResultDataThrow("学员标识符为空");
		}
		if(StringUtil.isEmpty(recordFlow)){
			return ResultDataThrow( "科室标识符为空");
		}
		List<Map<String,String>>  dataList =  jswjwBiz.viewImage(doctorFlow,recordFlow);
		resultMap.put("dataList", dataList);
		return resultMap;
	}
	private Map<String,Object> paressXml(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();
				for(Element element : elements){
					formDataMap.put(element.getName(), element.getText());
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}

	final static String Jxcf = "1";
	final static String Ynbltl = "2"; final static String Wzbltl = "3";final static String Swbltl = "5";final static String Jxbltl = "11";
	final static String Xsjz = "4";  final static String Rkjy = "6";
	final static String Ckks = "7"; final static String Jnpx = "8"; final static String Yph = "9";
	final static String Jxhz = "10";
	final static String Lcczjnzd = "12"; final static String Lcblsxzd = "13";
	final static String Ssczzd = "14"; final static String Yxzdbgsxzd = "15"; final static String Lcwxyd = "16";
	final static String Ryjy = "17"; final static String Rzyjdjy = "18"; final static String Cjbg = "19";
	final static String Bgdfx = "20"; final static String Jxsj = "21";final static String Sjys = "22";
	/**
	 * 出科考核表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/afterEvaluation",method = {RequestMethod.POST})
	@ResponseBody
	public Object evaluationSun(String userFlow,
								String docFlow,
								String processFlow,
								String recFlow,
								String roleId,
								String deptFlow,
								String recordFlow) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(docFlow)){
			return ResultDataThrow("学员标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("过程标识符为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
		ResDoctor doctor=null;
		SysUser operUser=null;
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
//		resultMap.put("currUser",currUser);
		if(StringUtil.isNotBlank(docFlow)){
			doctor  = jswjwBiz.readResDoctor(docFlow);
//			resultMap.put("doctor", doctor);
			operUser  = jswjwBiz.readSysUser(docFlow);
//			resultMap.put("operUser",operUser);
		}
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		if(rec==null)
		{
			return ResultDataThrow("带教还未审核");
		}
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
//			resultMap.put("formDataMap", formDataMap);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();

		Map<String,Object> processPerMap=jswjwBiz.getRecProgressIn(docFlow,processFlow,null,null);
		if(processPerMap == null){
			processPerMap = new HashMap<String, Object>();
		}
		boolean f=false;
		f=jswjwBiz.getCkkPower(docFlow);
		ResScore outScore = null;
		String theoreticalCfg = null;
		//填写百分比限制
		JsresPowerCfg jsresPowerCfg1= jswjwBiz.readPowerCfg("out_filling_check_" + rec.getOrgFlow());
		if(null != jsresPowerCfg1){
			resultMap.put("checkProcess",jsresPowerCfg1.getCfgValue());
		}else{
			resultMap.put("checkProcess","0");
		}
		if(f) {
			outScore = jswjwTeacherBiz.readScoreByProcessFlow(processFlow);
//			resultMap.put("outScore", outScore);
			// 如果有出科成绩 出科试卷flow不为空则查询试卷对应的及格分
			if(null != outScore && StringUtil.isNotBlank(outScore.getPaperFlow())){
				// 查询对应试卷的及格分
				TestPaper testPaper = paperBiz.readTestPaper(outScore.getPaperFlow());
//				resultMap.put("testPaper", testPaper);
				// 是否校验出科理论成绩
				theoreticalCfg = jswjwBiz.getJsResCfgCode("theoretical_qualified_flag_" + currUser.getOrgFlow());
//				resultMap.put("theoreticalCfg", theoreticalCfg);
			}
		}
//		resultMap.put("f",f);
		ResDoctorSchProcess p=iResDoctorProcessBiz.read(processFlow);
		if(StringUtil.isNotBlank(p.getSchStartDate())&&StringUtil.isNotBlank(p.getSchEndDate()))
		{
			resultMap.put("attendance",DateUtil.signDaysBetweenTowDate(p.getSchEndDate(),p.getSchStartDate())+1);
		}
		SysDept dept=jswjwBiz.readSysDept(p.getDeptFlow());
		String cksh = jswjwBiz.getJsResCfgCode("jsres_"+dept.getOrgFlow()+"_org_cksh");
		if(StringUtil.isBlank(cksh))
		{
            cksh = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		resultMap.put("cksh",cksh);
		//获取不同类型并定义接受
		if(processPerMap!=null){
            String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
            String diseaseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
            String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
            String operationRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();

			String caseRegistry=(String)processPerMap.get(processFlow+caseRegistryId);
			String caseRegistryReqNum=(String)processPerMap.get(processFlow+caseRegistryId+"ReqNum");
			String caseRegistryFinished=(String)processPerMap.get(processFlow+caseRegistryId+"Finished");

			String diseaseRegistry=(String)processPerMap.get(processFlow+diseaseRegistryId);
			String diseaseRegistryReqNum=(String)processPerMap.get(processFlow+diseaseRegistryId+"ReqNum");
			String diseaseRegistryFinished=(String)processPerMap.get(processFlow+diseaseRegistryId+"Finished");

			String skillRegistry=(String)processPerMap.get(processFlow+skillRegistryId);
			String skillRegistryReqNum=(String)processPerMap.get(processFlow+skillRegistryId+"ReqNum");
			String skillRegistryFinished=(String)processPerMap.get(processFlow+skillRegistryId+"Finished");

			String skillAndOperationRegistry=(String)processPerMap.get(processFlow+operationRegistryId);
			String skillAndOperationRegistryReqNum=(String)processPerMap.get(processFlow+operationRegistryId+"ReqNum");
			String skillAndOperationRegistryFinished=(String)processPerMap.get(processFlow+operationRegistryId+"Finished");

            String recTypeIdt = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
//			int teachingRounds=0;
//			int difficult=0;
//			int lecture=0;
//			int death=0;
			int jxcf = 0;  int xjk = 0;
			int rkjy = 0; int jnpx=0;
			int yph = 0; int jxhz = 0; int jxbltl = 0; int lcczjnzd = 0;
			int lcblsxzd = 0; int ssczzd = 0; int yxzdbgsxzd = 0; int lcwxyd = 0;
			int ryjy = 0; int rzyjdjy = 0; int cjbg = 0;
			int bgdfx=0;	int jxsj=0;	int sjys=0;
			List<String> recTypes=new ArrayList<String>();
			recTypes.add(recTypeIdt);
			List<ResRec> recs= jswjwTeacherBiz.searchRecByProcessWithBLOBs(recTypes,processFlow,operUser.getUserFlow());
			for (ResRec resRec : recs) {
				String content=resRec.getRecContent();
				Document document=DocumentHelper.parseText(content);
				Element root=document.getRootElement();
				Element ec = root.element("activity_way");
				if (ec!=null) {
					String text=ec.attributeValue("id");
					if(Jxcf.equals(text)){
						jxcf++;
					}else if(Ynbltl.equals(text)){
						jxbltl++;
					}else if(Wzbltl.equals(text)){
						jxbltl++;
					}else if(Xsjz.equals(text)){
						xjk++;
					}else if(Swbltl.equals(text)){
						jxbltl++;
					}else if(Rkjy.equals(text)){
						rkjy++;
					}else if(Ckks.equals(text)){
//						ckkh++;
					}else if(Jnpx.equals(text)){
						jnpx++;
					}else if(Yph.equals(text)){
						yph++;
					}else if(Jxhz.equals(text)){
						jxhz++;
					}else if(Jxbltl.equals(text)){
						jxbltl++;
					}else if(Lcczjnzd.equals(text)){
						lcczjnzd++;
					}else if(Lcblsxzd.equals(text)){
						lcblsxzd++;
					}else if(Ssczzd.equals(text)){
						ssczzd++;
					}else if(Yxzdbgsxzd.equals(text)){
						yxzdbgsxzd++;
					}else if(Lcwxyd.equals(text)){
						lcwxyd++;
					}else if(Ryjy.equals(text)){
						ryjy++;
					}else if(Rzyjdjy.equals(text)){
						rzyjdjy++;
					}else if(Cjbg.equals(text)){
						cjbg++;
					}else if (Bgdfx.equals(text)){
						bgdfx++;
					}else if(Jxsj.equals(text)){
						jxsj++;
					}else if (Sjys.equals(text)){
						sjys++;
					}
				}
			}
			List<TeachingActivityInfo> infos=new ArrayList<>();
			String orgFlow = currUser.getOrgFlow();
			JsresPowerCfg orgApprove = jsresPowerCfgMapper.selectByPrimaryKey("jsres_"+orgFlow+"_org_ctrl_approve_activity");//教学活动评价配置
			JsresPowerCfg approve = jsresPowerCfgMapper.selectByPrimaryKey("jsres_"+orgFlow+"_org_approve_activity");//教学活动评价配置评审类型
            if (null != orgApprove && null != approve && StringUtil.isNotNullAndEquala(approve.getCfgValue(), orgApprove.getCfgValue(), com.pinde.core.common.GlobalConstant.FLAG_Y)) {        //开启必评
				infos=jswjwTeacherBiz.searchJoinActivityByProcessFlowNotScore(processFlow);
			}else {
				infos=jswjwTeacherBiz.searchJoinActivityByProcessFlow(processFlow);
			}
			if(infos!=null&&infos.size()>0)
			{
				for(TeachingActivityInfo info:infos)
				{
					String text=info.getActivityTypeId();
					if(Jxcf.equals(text)){
						jxcf++;
					}else if(Ynbltl.equals(text)){
						jxbltl++;
					}else if(Wzbltl.equals(text)){
						jxbltl++;
					}else if(Xsjz.equals(text)){
						xjk++;
					}else if(Swbltl.equals(text)){
						jxbltl++;
					}else if(Rkjy.equals(text)){
						rkjy++;
					}else if(Ckks.equals(text)){
//						ckkh++;
					}else if(Jnpx.equals(text)){
						jnpx++;
					}else if(Yph.equals(text)){
						yph++;
					}else if(Jxhz.equals(text)){
						jxhz++;
					}else if(Jxbltl.equals(text)){
						jxbltl++;
					}else if(Lcczjnzd.equals(text)){
						lcczjnzd++;
					}else if(Lcblsxzd.equals(text)){
						lcblsxzd++;
					}else if(Ssczzd.equals(text)){
						ssczzd++;
					}else if(Yxzdbgsxzd.equals(text)){
						yxzdbgsxzd++;
					}else if(Lcwxyd.equals(text)){
						lcwxyd++;
					}else if(Ryjy.equals(text)){
						ryjy++;
					}else if(Rzyjdjy.equals(text)){
						rzyjdjy++;
					}else if(Cjbg.equals(text)){
						cjbg++;
					}else if (Bgdfx.equals(text)){
						bgdfx++;
					}else if(Jxsj.equals(text)){
						jxsj++;
					}else if (Sjys.equals(text)){
						sjys++;
					}
				}
			}
			dataMap.put("userName",operUser.getUserName());
			dataMap.put("sessionNumber",doctor.getSessionNumber());
			dataMap.put("trainingSpeName",doctor.getTrainingSpeName());

			dataMap.put("caseRegistry",StringUtil.defaultIfEmpty(caseRegistry, "0"));
			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

			if (dataMap.get("caseRegistryReqNum")==null||"0".equals(dataMap.get("caseRegistryReqNum"))) {
				dataMap.put("caseRegistryReqNum","0");
				dataMap.put("caseRegistry","100");
			}

			dataMap.put("diseaseRegistry",StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
			dataMap.put("diseaseRegistryReqNum",StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
			dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

			if (dataMap.get("diseaseRegistryReqNum")==null||"0".equals(dataMap.get("diseaseRegistryReqNum"))) {
				dataMap.put("diseaseRegistryReqNum","0");
				dataMap.put("diseaseRegistry","100");
			}

			dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

			if (dataMap.get("skillRegistryReqNum")==null||"0".equals(dataMap.get("skillRegistryReqNum"))) {
				dataMap.put("skillRegistryReqNum","0");
				dataMap.put("skillRegistry","100");
			}

			dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
			dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
			dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

			if (dataMap.get("operationRegistryReqNum")==null||"0".equals(dataMap.get("operationRegistryReqNum"))) {
				dataMap.put("operationRegistryReqNum","0");
				dataMap.put("operationRegistry","100");
			}

			dataMap.put("jxcf",String.valueOf(jxcf));
//			dataMap.put("ynbltl",String.valueOf(ynbltl));
//			dataMap.put("wzbltl",String.valueOf(wzbltl));
			dataMap.put("xjk",String.valueOf(xjk));
//			dataMap.put("swbltl",String.valueOf(swbltl));
			dataMap.put("rkjy",String.valueOf(rkjy));
//			dataMap.put("ckkh",String.valueOf(ckkh));
			dataMap.put("jnpx",String.valueOf(jnpx));
			dataMap.put("yph",String.valueOf(yph));
			dataMap.put("jxhz",String.valueOf(jxhz));
			dataMap.put("jxbltl",String.valueOf(jxbltl));
			dataMap.put("lcczjnzd",String.valueOf(lcczjnzd));
			dataMap.put("lcblsxzd",String.valueOf(lcblsxzd));
			dataMap.put("ssczzd",String.valueOf(ssczzd));
			dataMap.put("yxzdbgsxzd",String.valueOf(yxzdbgsxzd));
			dataMap.put("lcwxyd",String.valueOf(lcwxyd));
			dataMap.put("ryjy",String.valueOf(ryjy));
			dataMap.put("rzyjdjy",String.valueOf(rzyjdjy));
			dataMap.put("cjbg",String.valueOf(cjbg));
			dataMap.put("bgdfx",String.valueOf(bgdfx));
			dataMap.put("jxsj",String.valueOf(jxsj));
			dataMap.put("sjys",String.valueOf(sjys));
			ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
//			resultMap.put("resDoctorSchProcess", resDoctorSchProcess);
//			resultMap.put("dataMap", dataMap);
//			resultMap.put("rec", rec);
//			resultMap.put("processFlow",processFlow);
//			resultMap.put("formFileName",recTypeId);
			resultMap.put("roleFlag", roleId);

            boolean showEdit = (roleId.equals("teacher") && (null == rec || StringUtil.isBlank(rec.getAuditStatusId())) || (roleId.equals("teacher") && (!cksh.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) || ((null == rec || StringUtil.isBlank(rec.getManagerAuditUserFlow()))))))
                    || (roleId.equals("Head") || roleId.equals("Seretary")) && (cksh.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) || (null != rec && StringUtil.isNotBlank(rec.getManagerAuditUserFlow())));
			boolean showSave = false;
			if(null == rec){
				if(roleId.equals("teacher")){
					showSave = true;
				}
			}else{
				if(roleId.equals("teacher") && StringUtil.isBlank(rec.getManagerAuditUserFlow())){
					showSave = true;
				}
				if(roleId.equals("Head") && StringUtil.isNotBlank(rec.getManagerAuditUserFlow())){
					showSave = true;
				}
				if(roleId.equals("Seretary") && StringUtil.isNotBlank(rec.getManagerAuditUserFlow())){
					showSave = true;
				}
			}

			boolean readonly = false;
			if(null == rec){
                if (roleId.equals("teacher") && cksh.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					readonly = true;
				}
			}else{
				if(roleId.equals("teacher") && StringUtil.isNotBlank(rec.getManagerAuditUserFlow())){
					readonly = true;
				}
			}
			resultMap.put("isExam",f);
			resultMap.put("showEdit",showEdit);
			resultMap.put("showSave",showSave);
			resultMap.put("readonly",readonly);

			resultMap.put("formFileName",recTypeId);
			resultMap.put("deptFlow",deptFlow);
			resultMap.put("operUserFlow",docFlow);
			resultMap.put("recFlow", null == rec ? "" : rec.getRecFlow());
			resultMap.put("processFlow",processFlow);
			resultMap.put("recordFlow",recordFlow);
			resultMap.put("cksh",cksh);
			resultMap.put("auditStatusId","TeacherAuditY");
			resultMap.put("headAuditStatusId","HeadAuditY");
			resultMap.put("teacherName", null == formDataMap ? "" : null == formDataMap.get("teacherName") ? "" : formDataMap.get("teacherName"));
			resultMap.put("teacherDate", null == formDataMap ? "" : null == formDataMap.get("teacherDate") ? "" : formDataMap.get("teacherDate"));
			if(roleId.equals("Head")){
				resultMap.put("directorName",currUser.getUserName());
				resultMap.put("directorDate", null == formDataMap ? DateUtil.getCurrDate() :  StringUtil.isBlank((String)formDataMap.get("directorDate")) ? DateUtil.getCurrDate() : formDataMap.get("directorDate"));
			}
			if(roleId.equals("Seretary")){
				resultMap.put("directorName",currUser.getUserName()+"/"+resDoctorSchProcess.getHeadUserName());
				resultMap.put("directorDate", null == formDataMap ? DateUtil.getCurrDate() : StringUtil.isBlank((String)formDataMap.get("directorDate")) ? DateUtil.getCurrDate() : formDataMap.get("directorDate"));
			}
			if(!roleId.equals("Head") && !roleId.equals("Seretary")){
				resultMap.put("directorName",null == formDataMap ? "" : StringUtil.isBlank((String)formDataMap.get("directorName")) ? "" : formDataMap.get("directorName"));
				resultMap.put("directorDate", null == formDataMap ? "" : StringUtil.isBlank((String)formDataMap.get("directorDate")) ? "" : formDataMap.get("directorDate"));
			}

            resultMap.put("theoreticalCfg", null == theoreticalCfg ? com.pinde.core.common.GlobalConstant.FLAG_N : theoreticalCfg);

			List<Map<String,Object>> resultMapList = new ArrayList<>();
			Map<String,Object> map = new HashMap<>();
			map.put("inputId", "name");
			map.put("label", "学员姓名");
			if(null == formDataMap || null == formDataMap.get("name")){
				map.put("value",doctor.getDoctorName());
			}else{
				map.put("value",formDataMap.get("name"));
			}
			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "sessional");
			map.put("label", "学员届别");
			if(null == formDataMap || null == formDataMap.get("sessional")){
				map.put("value",doctor.getSessionNumber());
			}else{
				map.put("value",formDataMap.get("sessional"));
			}
			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "trainMajor");
			map.put("label", "学员培训专业");
			if(null == formDataMap || null == formDataMap.get("trainMajor")){
				map.put("value",doctor.getTrainingSpeName());
			}else{
				map.put("value",formDataMap.get("trainMajor"));
			}
			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "deptName");
			map.put("label", "学员轮转科室名称");
			if(null == formDataMap || null == formDataMap.get("deptName")){
				map.put("value",resDoctorSchProcess.getSchDeptName());
			}else{
				map.put("value",formDataMap.get("deptName"));
			}
			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "cycleTimeQ");
			map.put("label", "学员轮转开始时间");
			if(null == formDataMap || null == formDataMap.get("cycleTimeQ")){
				map.put("value",resDoctorSchProcess.getSchStartDate());
			}else{
				map.put("value",formDataMap.get("cycleTimeQ"));
			}
			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "cycleTimeH");
			map.put("label", "学员轮转结束时间");
			if(null == formDataMap || null == formDataMap.get("cycleTimeH")){
				map.put("value",resDoctorSchProcess.getSchEndDate());
			}else{
				map.put("value",formDataMap.get("cycleTimeH"));
			}
			resultMapList.add(map);
			//考勤
			map = new HashMap<>();
			map.put("inputId", "kaoqin");
			map.put("label", "考勤");
			List<Map<String,Object>> attendanceList = new ArrayList<>();
			Map<String,Object> attendanceMap = new HashMap<>();
			attendanceMap.put("inputId", "attendance");
			attendanceMap.put("label", "全勤");
			attendanceMap.put("value",resultMap.get("attendance"));
			attendanceList.add(attendanceMap);
			attendanceMap = new HashMap<>();
			attendanceMap.put("inputId", "leave");
			attendanceMap.put("label", "事假");
			attendanceMap.put("value", null == formDataMap ? "" : null == formDataMap.get("leave") ? "" : formDataMap.get("leave"));
			attendanceList.add(attendanceMap);
			attendanceMap = new HashMap<>();
			attendanceMap.put("inputId", "sickLeave");
			attendanceMap.put("label", "病假");
			attendanceMap.put("value", null == formDataMap ? "" : null == formDataMap.get("sickLeave") ? "" : formDataMap.get("sickLeave"));
			attendanceList.add(attendanceMap);
			attendanceMap = new HashMap<>();
			attendanceMap.put("inputId", "materLeave");
			attendanceMap.put("label", "产假");
			attendanceMap.put("value", null == formDataMap ? "" : null == formDataMap.get("materLeave") ? "" : formDataMap.get("materLeave"));
			attendanceList.add(attendanceMap);
			attendanceMap = new HashMap<>();
			attendanceMap.put("inputId", "absenteeism");
			attendanceMap.put("label", "旷工");
			attendanceMap.put("value", null == formDataMap ? "" : null == formDataMap.get("absenteeism") ? "" : formDataMap.get("absenteeism"));
			attendanceList.add(attendanceMap);
			map.put("value",attendanceList);
			resultMapList.add(map);
			//临床实践指标完成情况
			map = new HashMap<>();
			map.put("inputId", "lcsjzbwcqk");
			map.put("label", "临床实践指标完成情况");
			List<Map<String,Object>> lcsjzbList = new ArrayList<>();
			Map<String,Object> lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId", "blsywc");
			lcsjzbMap.put("label", "学员病历数应完成");
			lcsjzbMap.put("value",null == dataMap ? "" : null == dataMap.get("caseRegistryReqNum") ? "" : dataMap.get("caseRegistryReqNum"));
//			lcsjzbList.add(lcsjzbMap);
//			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId2", "blsyjwc");
			lcsjzbMap.put("label2", "学员病历数已完成");
			lcsjzbMap.put("value2",null == dataMap ? "" : null == dataMap.get("caseRegistryFinished") ? "" : dataMap.get("caseRegistryFinished"));
//			lcsjzbList.add(lcsjzbMap);
//			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId3", "blswcbl");
			lcsjzbMap.put("label3", "学员病历数完成比例");
			lcsjzbMap.put("value3",null == dataMap ? "" : null == dataMap.get("caseRegistry") ? "" : dataMap.get("caseRegistry"));
			lcsjzbList.add(lcsjzbMap);
			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId", "bzsywc");
			lcsjzbMap.put("label", "学员病种数应完成");
			lcsjzbMap.put("value",null == dataMap ? "" : null == dataMap.get("diseaseRegistryReqNum") ? "" : dataMap.get("diseaseRegistryReqNum"));
//			lcsjzbList.add(lcsjzbMap);
//			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId2", "bzsyjwc");
			lcsjzbMap.put("label2", "学员病种数已完成");
			lcsjzbMap.put("value2",null == dataMap ? "" : null == dataMap.get("diseaseRegistryFinished") ? "" : dataMap.get("diseaseRegistryFinished"));
//			lcsjzbList.add(lcsjzbMap);
//			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId3", "bzswcbl");
			lcsjzbMap.put("label3", "学员病种数完成比例");
			lcsjzbMap.put("value3",null == dataMap ? "" : null == dataMap.get("diseaseRegistry") ? "" : dataMap.get("diseaseRegistry"));
			lcsjzbList.add(lcsjzbMap);
			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId", "czsywc");
			lcsjzbMap.put("label", "学员操作数应完成");
			lcsjzbMap.put("value",null == dataMap ? "" : null == dataMap.get("skillRegistryReqNum") ? "" : dataMap.get("skillRegistryReqNum"));
//			lcsjzbList.add(lcsjzbMap);
//			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId2", "czsyjwc");
			lcsjzbMap.put("label2", "学员操作数已完成");
			lcsjzbMap.put("value2",null == dataMap ? "" : null == dataMap.get("skillRegistryFinished") ? "" : dataMap.get("skillRegistryFinished"));
//			lcsjzbList.add(lcsjzbMap);
//			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId3", "czswcbl");
			lcsjzbMap.put("label3", "学员操作数完成比例");
			lcsjzbMap.put("value3",null == dataMap ? "" : null == dataMap.get("skillRegistry") ? "" : dataMap.get("skillRegistry"));
			lcsjzbList.add(lcsjzbMap);
			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId", "sssywc");
			lcsjzbMap.put("label", "学员手术数应完成");
			lcsjzbMap.put("value",null == dataMap ? "" : null == dataMap.get("operationRegistryReqNum") ? "" : dataMap.get("operationRegistryReqNum"));
//			lcsjzbList.add(lcsjzbMap);
//			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId2", "sssyjwc");
			lcsjzbMap.put("label2", "学员手术数已完成");
			lcsjzbMap.put("value2",null == dataMap ? "" : null == dataMap.get("operationRegistryFinished") ? "" : dataMap.get("operationRegistryFinished"));
//			lcsjzbList.add(lcsjzbMap);
//			lcsjzbMap = new HashMap<>();
			lcsjzbMap.put("inputId3", "ssswcbl");
			lcsjzbMap.put("label3", "学员手术数完成比例");
			lcsjzbMap.put("value3",null == dataMap ? "" : null == dataMap.get("operationRegistry") ? "" : dataMap.get("operationRegistry"));
			lcsjzbList.add(lcsjzbMap);
			map.put("value",lcsjzbList);
			resultMapList.add(map);
			//医德医风人机沟通团队合作评价
			map = new HashMap<>();
			map.put("inputId", "ydyfrjgttdhzpj");
			map.put("label", "医德医风人机沟通团队合作评价");
			List<Map<String,Object>> ydyfList = new ArrayList<>();
			Map<String,Object> ydyfMap = new HashMap<>();
			ydyfMap.put("inputId", "zsgjflfg");
			ydyfMap.put("label", "遵守国家法律法规、医院规章制度");
			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("zsgjflfg_id") ? "" : formDataMap.get("zsgjflfg_id"));
			ydyfList.add(ydyfMap);
//			ydyfMap = new HashMap<>();
//			ydyfMap.put("inputId", "zsgjflfg_name");
//			ydyfMap.put("label", "遵守国家法律法规、医院规章制度_name");
//			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("zsgjflfg_name") ? "" : formDataMap.get("zsgjflfg_name"));
//			ydyfList.add(ydyfMap);
			ydyfMap = new HashMap<>();
			ydyfMap.put("inputId", "lxgwzz");
			ydyfMap.put("label", "履行岗位职责");
			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("lxgwzz_id") ? "" : formDataMap.get("lxgwzz_id"));
			ydyfList.add(ydyfMap);
//			ydyfMap = new HashMap<>();
//			ydyfMap.put("inputId", "lxgwzz_name");
//			ydyfMap.put("label", "履行岗位职责_name");
//			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("lxgwzz_name") ? "" : formDataMap.get("lxgwzz_name"));
//			ydyfList.add(ydyfMap);
			ydyfMap = new HashMap<>();
			ydyfMap.put("inputId", "ybrwzx");
			ydyfMap.put("label", "以病人为中心，体现人文关怀");
			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("ybrwzx_id") ? "" : formDataMap.get("ybrwzx_id"));
			ydyfList.add(ydyfMap);
//			ydyfMap = new HashMap<>();
//			ydyfMap.put("inputId", "ybrwzx_name");
//			ydyfMap.put("label", "以病人为中心，体现人文关怀_name");
//			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("ybrwzx_name") ? "" : formDataMap.get("ybrwzx_name"));
//			ydyfList.add(ydyfMap);
			ydyfMap = new HashMap<>();
			ydyfMap.put("inputId", "rjgtbdnl");
			ydyfMap.put("label", "人际（医患）沟通和表达能力");
			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("rjgtbdnl_id") ? "" : formDataMap.get("rjgtbdnl_id"));
			ydyfList.add(ydyfMap);
//			ydyfMap = new HashMap<>();
//			ydyfMap.put("inputId", "rjgtbdnl_name");
//			ydyfMap.put("label", "人际（医患）沟通和表达能力_name");
//			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("rjgtbdnl_name") ? "" : formDataMap.get("rjgtbdnl_name"));
//			ydyfList.add(ydyfMap);
			ydyfMap = new HashMap<>();
			ydyfMap.put("inputId", "tjxzjsxm");
			ydyfMap.put("label", "团结协作精神");
			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("tjxzjsxm_id") ? "" : formDataMap.get("tjxzjsxm_id"));
			ydyfList.add(ydyfMap);
//			ydyfMap = new HashMap<>();
//			ydyfMap.put("inputId", "tjxzjsxm_name");
//			ydyfMap.put("label", "团结协作精神_name");
//			ydyfMap.put("value",null == formDataMap ? "" : null == formDataMap.get("tjxzjsxm_name") ? "" : formDataMap.get("tjxzjsxm_name"));
//			ydyfList.add(ydyfMap);
			map.put("value",ydyfList);
			resultMapList.add(map);
			//临床综合能力评价
			map = new HashMap<>();
			map.put("inputId", "lczhnlpj");
			map.put("label", "临床综合能力评价");
			List<Map<String,Object>> lczhnlList = new ArrayList<>();
			Map<String,Object> lczhnlMap = new HashMap<>();
			lczhnlMap.put("inputId", "jbllzwcd");
			lczhnlMap.put("label", "临床基本知识、基本理论掌握程度");
			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("jbllzwcd_id") ? "" : formDataMap.get("jbllzwcd_id"));
			lczhnlList.add(lczhnlMap);
//			lczhnlMap = new HashMap<>();
//			lczhnlMap.put("inputId", "jbllzwcd_name");
//			lczhnlMap.put("label", "临床基本知识、基本理论掌握程度_name");
//			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("jbllzwcd_name") ? "" : formDataMap.get("jbllzwcd_name"));
//			lczhnlList.add(lczhnlMap);
			lczhnlMap = new HashMap<>();
			lczhnlMap.put("inputId", "njbjnzwcd");
			lczhnlMap.put("label", "临床基本技能掌握程度");
			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("njbjnzwcd_id") ? "" : formDataMap.get("njbjnzwcd_id"));
			lczhnlList.add(lczhnlMap);
//			lczhnlMap = new HashMap<>();
//			lczhnlMap.put("inputId", "njbjnzwcd_name");
//			lczhnlMap.put("label", "临床基本技能掌握程度_name");
//			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("njbjnzwcd_name") ? "" : formDataMap.get("njbjnzwcd_name"));
//			lczhnlList.add(lczhnlMap);
			lczhnlMap = new HashMap<>();
			lczhnlMap.put("inputId", "lcswnl");
			lczhnlMap.put("label", "临床思维能力");
			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("lcswnl_id") ? "" : formDataMap.get("lcswnl_id"));
			lczhnlList.add(lczhnlMap);
//			lczhnlMap = new HashMap<>();
//			lczhnlMap.put("inputId", "lcswnl_name");
//			lczhnlMap.put("label", "临床思维能力_name");
//			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("lcswnl_name") ? "" : formDataMap.get("lcswnl_name"));
//			lczhnlList.add(lczhnlMap);
			lczhnlMap = new HashMap<>();
			lczhnlMap.put("inputId", "lczlnl");
			lczhnlMap.put("label", "临床诊疗能力");
			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("lczlnl_id") ? "" : formDataMap.get("lczlnl_id"));
			lczhnlList.add(lczhnlMap);
//			lczhnlMap = new HashMap<>();
//			lczhnlMap.put("inputId", "lczlnl_name");
//			lczhnlMap.put("label", "临床诊疗能力_name");
//			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("lczlnl_name") ? "" : formDataMap.get("lczlnl_name"));
//			lczhnlList.add(lczhnlMap);
			lczhnlMap = new HashMap<>();
			lczhnlMap.put("inputId", "jjclnl");
			lczhnlMap.put("label", "危重病人的识别及紧急处理能力");
			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("jjclnl_id") ? "" : formDataMap.get("jjclnl_id"));
			lczhnlList.add(lczhnlMap);
//			lczhnlMap = new HashMap<>();
//			lczhnlMap.put("inputId", "jjclnl_name");
//			lczhnlMap.put("label", "危重病人的识别及紧急处理能力_name");
//			lczhnlMap.put("value",null == formDataMap ? "" : null == formDataMap.get("jjclnl_name") ? "" : formDataMap.get("jjclnl_name"));
//			lczhnlList.add(lczhnlMap);
			map.put("value",lczhnlList);
			resultMapList.add(map);
			//参加各种形式活动
			map = new HashMap<>();
			map.put("inputId", "cjgzxshd");
			map.put("label", "参加各种形式活动");
			List<Map<String,Object>> activityList = new ArrayList<>();
			Map<String,Object> cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "jxcb");
			cjhdMap.put("label", "教学查房");
			if(null == formDataMap || null == formDataMap.get("jxcb") || formDataMap.get("jxcb").equals("0")){
				cjhdMap.put("value",null == dataMap ? "" : null == dataMap.get("jxcf") ? "" : dataMap.get("jxcf"));
			}else{
				cjhdMap.put("value",formDataMap.get("jxcb"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "jxbltl");
			cjhdMap.put("label", "教学病例讨论");
			if(null == formDataMap || null == formDataMap.get("jxbltl") || formDataMap.get("jxbltl").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("jxbltl") ? "0" : dataMap.get("jxbltl"));
			}else{
				cjhdMap.put("value",formDataMap.get("jxbltl"));
			}
			activityList.add(cjhdMap);
//			cjhdMap = new HashMap<>();
//			cjhdMap.put("inputId", "wzbltl");
//			cjhdMap.put("label", "学员危重病例讨论");
//			if(null == formDataMap || null == formDataMap.get("wzbltl") || formDataMap.get("wzbltl").equals("0")){
//				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("wzbltl") ? "0" : dataMap.get("wzbltl"));
//			}else{
//				cjhdMap.put("value",formDataMap.get("wzbltl"));
//			}
//			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "xjk");
			cjhdMap.put("label", "临床小讲课");
			if(null == formDataMap || null == formDataMap.get("xjk") || formDataMap.get("xjk").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("xjk") ? "0" : dataMap.get("xjk"));
			}else{
				cjhdMap.put("value",formDataMap.get("xjk"));
			}
			activityList.add(cjhdMap);
//			cjhdMap = new HashMap<>();
//			cjhdMap.put("inputId", "swbltl");
//			cjhdMap.put("label", "学员死亡病例讨论");
//			if(null == formDataMap || null == formDataMap.get("swbltl") || formDataMap.get("swbltl").equals("0")){
//				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("swbltl") ? "0" : dataMap.get("swbltl"));
//			}else{
//				cjhdMap.put("value",formDataMap.get("swbltl"));
//			}
//			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "rkjy");
			cjhdMap.put("label", "入轮转科室教育");
			if(null == formDataMap || null == formDataMap.get("rkjy") || formDataMap.get("rkjy").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("rkjy") ? "0" : dataMap.get("rkjy"));
			}else{
				cjhdMap.put("value",formDataMap.get("rkjy"));
			}
			activityList.add(cjhdMap);
//			cjhdMap = new HashMap<>();
//			cjhdMap.put("inputId", "ckkh");
//			cjhdMap.put("label", "学员出科考核");
//			if(null == formDataMap || null == formDataMap.get("ckkh") || formDataMap.get("ckkh").equals("0")){
//				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("ckkh") ? "0" : dataMap.get("ckkh"));
//			}else{
//				cjhdMap.put("value",formDataMap.get("ckkh"));
//			}
//			activityList.add(cjhdMap);
//			cjhdMap = new HashMap<>();
//			cjhdMap.put("inputId", "jnpx");
//			cjhdMap.put("label", "学员技能培训");
//			if(null == formDataMap || null == formDataMap.get("jnpx") || formDataMap.get("jnpx").equals("0")){
//				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("jnpx") ? "0" : dataMap.get("jnpx"));
//			}else{
//				cjhdMap.put("value",formDataMap.get("jnpx"));
//			}
//			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "yph");
			cjhdMap.put("label", "教学阅片");
			if(null == formDataMap || null == formDataMap.get("yph") || formDataMap.get("yph").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("yph") ? "0" : dataMap.get("yph"));
			}else{
				cjhdMap.put("value",formDataMap.get("yph"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "jxhz");
			cjhdMap.put("label", "门诊教学");
			if(null == formDataMap || null == formDataMap.get("jxhz") || formDataMap.get("jxhz").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("jxhz") ? "0" : dataMap.get("jxhz"));
			}else{
				cjhdMap.put("value",formDataMap.get("jxhz"));
			}
			activityList.add(cjhdMap);
//			cjhdMap = new HashMap<>();
//			cjhdMap.put("inputId", "jxbltl");
//			cjhdMap.put("label", "学员教学病例讨论");
//			if(null == formDataMap || null == formDataMap.get("jxbltl") || formDataMap.get("jxbltl").equals("0")){
//				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("jxbltl") ? "0" : dataMap.get("jxbltl"));
//			}else{
//				cjhdMap.put("value",formDataMap.get("jxbltl"));
//			}
//			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "lcczjnzd");
			cjhdMap.put("label", "临床操作技能床旁教学");
			if(null == formDataMap || null == formDataMap.get("lcczjnzd") || formDataMap.get("lcczjnzd").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("lcczjnzd") ? "0" : dataMap.get("lcczjnzd"));
			}else{
				cjhdMap.put("value",formDataMap.get("lcczjnzd"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "lcblsxzd");
			cjhdMap.put("label", "住院病历书写指导教学");
			if(null == formDataMap || null == formDataMap.get("lcblsxzd") || formDataMap.get("lcblsxzd").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("lcblsxzd") ? "0" : dataMap.get("lcblsxzd"));
			}else{
				cjhdMap.put("value",formDataMap.get("lcblsxzd"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "ssczzd");
			cjhdMap.put("label", "手术操作指导教学");
			if(null == formDataMap || null == formDataMap.get("ssczzd") || formDataMap.get("ssczzd").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("ssczzd") ? "0" : dataMap.get("ssczzd"));
			}else{
				cjhdMap.put("value",formDataMap.get("ssczzd"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "yxzdbgsxzd");
			cjhdMap.put("label", "影像诊断报告书写指导教学");
			if(null == formDataMap || null == formDataMap.get("yxzdbgsxzd") || formDataMap.get("yxzdbgsxzd").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("yxzdbgsxzd") ? "0" : dataMap.get("yxzdbgsxzd"));
			}else{
				cjhdMap.put("value",formDataMap.get("yxzdbgsxzd"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "lcwxyd");
			cjhdMap.put("label", "临床文献研读会");
			if(null == formDataMap || null == formDataMap.get("lcwxyd") || formDataMap.get("lcwxyd").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("lcwxyd") ? "0" : dataMap.get("lcwxyd"));
			}else{
				cjhdMap.put("value",formDataMap.get("lcwxyd"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "ryjy");
			cjhdMap.put("label", "入院教育");
			if(null == formDataMap || null == formDataMap.get("ryjy") || formDataMap.get("ryjy").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("ryjy") ? "0" : dataMap.get("ryjy"));
			}else{
				cjhdMap.put("value",formDataMap.get("ryjy"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "rzyjdjy");
			cjhdMap.put("label", "入专业基地教育");
			if(null == formDataMap || null == formDataMap.get("rzyjdjy") || formDataMap.get("rzyjdjy").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("rzyjdjy") ? "0" : dataMap.get("rzyjdjy"));
			}else{
				cjhdMap.put("value",formDataMap.get("rzyjdjy"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "cjbg");
			cjhdMap.put("label", "晨间报告");
			if(null == formDataMap || null == formDataMap.get("cjbg") || formDataMap.get("cjbg").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("cjbg") ? "0" : dataMap.get("cjbg"));
			}else{
				cjhdMap.put("value",formDataMap.get("cjbg"));
			}
			activityList.add(cjhdMap);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "bgdfx");
			cjhdMap.put("label", "报告单分析");
			if(null == formDataMap || null == formDataMap.get("bgdfx") || formDataMap.get("bgdfx").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("bgdfx") ? "0" : dataMap.get("bgdfx"));
			}else{
				cjhdMap.put("value",formDataMap.get("bgdfx"));
			}
			activityList.add(cjhdMap);
			map.put("value",activityList);
			resultMapList.add(map);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "jxsj");
			cjhdMap.put("label", "教学上机");
			if(null == formDataMap || null == formDataMap.get("jxsj") || formDataMap.get("jxsj").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("jxsj") ? "0" : dataMap.get("jxsj"));
			}else{
				cjhdMap.put("value",formDataMap.get("jxsj"));
			}
			activityList.add(cjhdMap);
			map.put("value",activityList);
			resultMapList.add(map);
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "sjys");
			cjhdMap.put("label", "上机演示");
			if(null == formDataMap || null == formDataMap.get("sjys") || formDataMap.get("sjys").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("sjys") ? "0" : dataMap.get("sjys"));
			}else{
				cjhdMap.put("value",formDataMap.get("sjys"));
			}
			activityList.add(cjhdMap);
			map.put("value",activityList);
			resultMapList.add(map);
			//出科考核
			map = new HashMap<>();
			map.put("inputId", "ckkh");
			map.put("label", "出科考核");
			List<Map<String,Object>> afterDeptList = new ArrayList<>();
			Map<String,Object> ckkhMap = new HashMap<>();
			ckkhMap.put("inputId", "theoreResult");
			ckkhMap.put("label", "理论成绩");
			String score = null == formDataMap ? "": null == formDataMap.get("theoreResult") ? "" : (String)formDataMap.get("theoreResult");
			if(f && (null == formDataMap || null == formDataMap.get("theoreResult"))){
				score = null == outScore ? "" : null == outScore.getTheoryScore() ? "" : outScore.getTheoryScore().toString();
			}
			ckkhMap.put("value",score);
			afterDeptList.add(ckkhMap);
			ckkhMap = new HashMap<>();
			ckkhMap.put("inputId", "skillName");
			ckkhMap.put("label", "技能考核名称");
			ckkhMap.put("value",null == formDataMap ? "" : null == formDataMap.get("skillName") ? "" : formDataMap.get("skillName"));
			afterDeptList.add(ckkhMap);
			ckkhMap = new HashMap<>();
			ckkhMap.put("inputId", "score");
			ckkhMap.put("label", "技能得分");
			ckkhMap.put("value",null == formDataMap ? "" : null == formDataMap.get("score") ? "" : formDataMap.get("score"));
			afterDeptList.add(ckkhMap);
			ckkhMap = new HashMap<>();
			ckkhMap.put("inputId", "examiner1");
			ckkhMap.put("label", "考官1");
			ckkhMap.put("value",null == formDataMap ? "" : null == formDataMap.get("examiner1") ? "" : formDataMap.get("examiner1"));
			afterDeptList.add(ckkhMap);
			ckkhMap = new HashMap<>();
			ckkhMap.put("inputId", "examiner2");
			ckkhMap.put("label", "考官2");
			ckkhMap.put("value",null == formDataMap ? "" : null == formDataMap.get("examiner2") ? "" : formDataMap.get("examiner2"));
			afterDeptList.add(ckkhMap);

			map.put("value",afterDeptList);
			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "szkskhxzztpj");
			map.put("label", "所在科室考核小组总体评价");
			map.put("value",null == formDataMap ? "" : null == formDataMap.get("szkskhxzztpj_id") ? "" : formDataMap.get("szkskhxzztpj_id"));
			resultMapList.add(map);
//			map = new HashMap<>();
//			map.put("inputId", "szkskhxzztpj_name");
//			map.put("label", "所在科室考核小组总体评价_name");
//			map.put("value",null == formDataMap ? "" : null == formDataMap.get("szkskhxzztpj_name") ? "" : formDataMap.get("szkskhxzztpj_name"));
//			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "teacherComment");
			map.put("label", "带教老师评价");
			map.put("value",null == rec ? "" : rec.getTeacherComment());
			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "teacherName");
			map.put("label", "带教老师签名");
			map.put("value",null == rec ? currUser.getUserName() : StringUtil.isBlank(rec.getAuditStatusId()) ? currUser.getUserName() : formDataMap.get("teacherName"));
			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "teacherDate");
			map.put("label", "日期");
			map.put("value", null == formDataMap ? "" : null == formDataMap.get("teacherDate") ? "" : formDataMap.get("teacherDate"));
			resultMapList.add(map);

//			map = new HashMap<>();
//			map.put("inputId", "directorName");
//			map.put("label", "主任签名");
//			map.put("value", null == formDataMap ? "" : null == formDataMap.get("directorName") ? "" : formDataMap.get("directorName"));
//			resultMapList.add(map);
//
//			map = new HashMap<>();
//			map.put("inputId", "directorDate");
//			map.put("label", "日期");
//			map.put("value", null == formDataMap ? "" : null == formDataMap.get("directorDate") ? "" : formDataMap.get("directorDate"));
//			resultMapList.add(map);

			resultMap.put("resultMapList",resultMapList);
		}
		return resultMap;
	}

	/**
	 * 保存出科考核表，临床操作技能评估量化表，迷你临床演练评估量化表
	 * @param formFileName
	 * @param recFlow
	 * @param operUserFlow
	 * @param roleFlag
	 * @param processFlow
	 * @param recordFlow
	 * @param userFlow
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/saveRegistryForm",method={RequestMethod.POST})
	@ResponseBody
	public Object saveRegistryForm(String formFileName,String recFlow,String operUserFlow,String roleFlag,String cksh,String processFlow,String recordFlow,String userFlow,HttpServletRequest req){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		SysUser user=jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
		}
		if(StringUtil.isBlank(operUserFlow)){
			return ResultDataThrow("学员标识符为空");
		}
		if(StringUtil.isBlank(roleFlag)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("过程标识符为空");
		}
		if(!roleFlag.equals("Head")&&!roleFlag.equals("Seretary")&&!roleFlag.equals("TeachingHead")&&!roleFlag.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

		ResSchProcessExpress resRec=expressBiz.searResRecZhuZhi(formFileName,recFlow,operUserFlow,roleFlag,processFlow,recordFlow,userFlow,cksh,req);

		int i=expressBiz.editResRec(resRec,user);
		if (i==0) {
			return ResultDataThrow("保存失败");
		}
		return resultMap;


	}
	/**
	 * 迷你临床演练评估量化表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mini_cex",method = {RequestMethod.POST})
	@ResponseBody
	public Object mini_cex(String userFlow,
						   String doctorFlow,
						   String processFlow,
						   String recFlow) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(doctorFlow)){
			return ResultDataThrow("学员标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("过程标识符为空");
		}
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
		resultMap.put("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			resultMap.put("formDataMap", formDataMap);
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(doctorFlow);

		resultMap.put("doctor", doctor);
		resultMap.put("formFileName", recTypeId);
		resultMap.put("rec", rec);
		resultMap.put("roleFlag", "teacher");
		return resultMap;
	}
	/**
	 * 临床操作技能评估量化表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DOPS",method = {RequestMethod.POST})
	@ResponseBody
	public Object DOPS(String userFlow,
					   String doctorFlow,
					   String processFlow,
					   String recFlow) throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(doctorFlow)){
			return ResultDataThrow( "学员标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow( "过程标识符为空");
		}
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
		resultMap.put("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			resultMap.put("formDataMap", formDataMap);
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(doctorFlow);

		resultMap.put("doctor", doctor);
		resultMap.put("formFileName", recTypeId);
		resultMap.put("rec", rec);
		resultMap.put("roleFlag", "teacher");
		return resultMap;
	}

	/**
	 * 带教评分list
	 * @return
	 */
	@RequestMapping(value="/teacherGradeList",method=RequestMethod.POST)
	@ResponseBody
	public Object teacherGradeList(String userFlow,String teaName,Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}

		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		String cfgTeacher=jswjwBiz.getCfgCode("res_teacher_role_flow");
		PageHelper.startPage(pageIndex, pageSize);
		List<SysUser> sysUserList=jswjwKzrBiz.teacherRoleCheckUser(userinfo.getDeptFlow(),cfgTeacher,teaName,userFlow);

		Map<String, List<DeptTeacherGradeInfo>> studentListMap=new HashMap<String, List<DeptTeacherGradeInfo>>();
		Map<String, String> averageMap=new HashMap<String, String>();
		Map<String,String> gradeScoreMap=new HashMap<>();
		for (SysUser user : sysUserList) {
			List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchProssFlowRec(user.getUserFlow());
			Float sum=0f;
			Float average=0f;
			if (gradeInfoList!=null && gradeInfoList.size()>0) {
				for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
					Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
					Float zongFen= Float.parseFloat(gradeMap.get("totalScore").toString());
					gradeScoreMap.put(gradeInfo.getRecFlow(),gradeMap.get("totalScore").toString());
					sum=sum+zongFen;
				}
				average=sum/gradeInfoList.size();
			}
			BigDecimal bd=new BigDecimal(average).setScale(1, RoundingMode.UP);
			averageMap.put(user.getUserFlow(), bd.toString());//平均分
			studentListMap.put(user.getUserFlow(),gradeInfoList);
		}

		final Map<String, String> averageMapTemp = averageMap;
		Collections.sort(sysUserList,new Comparator<SysUser>() {
			@Override
			public int compare(SysUser o1, SysUser o2) {
				String	o1Key=o1.getUserFlow();
				String	o2Key=o2.getUserFlow();
				String s1=averageMapTemp.get(o1Key);
				String s2=averageMapTemp.get(o2Key);
				if(StringUtil.isBlank(s1))
				{
					s1="0";
				}
				if(StringUtil.isBlank(s2))
				{
					s2="0";
				}
				Float f1=0f;
				Float f2=0f;
				try {
					f1=Float.valueOf(s1);
					f2=Float.valueOf(s2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Float result=f2-f1;
				return result>0?1:result==0?0:-1;
			}
		});
//		resultMap.put("averageMap", averageMap);
//		resultMap.put("gradeScoreMap", gradeScoreMap);
//		resultMap.put("studentListMap", studentListMap);
//		resultMap.put("sysUserList", sysUserList);
		resultMap.put("dataCount", PageHelper.total);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != sysUserList && sysUserList.size()>0){
			for (SysUser user:sysUserList) {
				Map<String,Object> userMap = new HashMap<>();
				String stuFlow = user.getUserFlow();
				userMap.put("userFlow",stuFlow);
				userMap.put("userName",user.getUserName());
				userMap.put("avg",null == averageMap ? "" : null == averageMap.get(stuFlow) ? "" : averageMap.get(stuFlow).toString());
				List<Map<String,String>> gradeMapList = new ArrayList<>();
				List<DeptTeacherGradeInfo> infoList = studentListMap.get(stuFlow);
				if(null != infoList && infoList.size()>0){
					for (DeptTeacherGradeInfo info:infoList) {
						Map<String,String> gradeMap = new HashMap<>();
						gradeMap.put("recFlow",info.getRecFlow());
						gradeMap.put("docName",info.getOperUserName());
						gradeMap.put("docFlow",info.getOperUserFlow());
						gradeMap.put("gradeScore",StringUtil.isBlank(gradeScoreMap.get(info.getRecFlow())) ? "" : gradeScoreMap.get(info.getRecFlow()));
						gradeMapList.add(gradeMap);
					}
				}
				userMap.put("gradeMapList",gradeMapList);
				resultMapList.add(userMap);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}
	/**
	 * 科室评价
	 * @return
	 */
	@RequestMapping(value="/deptGrade",method=RequestMethod.POST)
	@ResponseBody
	public Object deptGrade(String userFlow,String deptFlow) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(deptFlow)){
			return ResultDataThrow("科室标识符为空");
		}
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}

		//评分模板
		List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
		String cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		ResAssessCfg assessCfg = jswjwBiz.getGradeTemplate(cfgCodeId);
//		resultMap.put("assessCfg", assessCfg);
		Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if (titleElementList != null && !titleElementList.isEmpty()) {
			for (Element te : titleElementList) {
				ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				List<Element> itemElementList = te.elements("item");
				List<ResAssessCfgItemForm> itemFormList = null;
				int score=0;
				if (itemElementList != null && !itemElementList.isEmpty()) {
					itemFormList = new ArrayList<ResAssessCfgItemForm>();
					for (Element ie : itemElementList) {
						ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
						itemForm.setId(ie.attributeValue("id"));
						itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
						itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
						if(ResAssessScoreTypeEnum.Nine.getId().equals(assessCfg.getAssessTypeId()))
						{
							score +=9;
						}else {
							if (StringUtil.isNotBlank(itemForm.getScore())) {
								score += Integer.valueOf(itemForm.getScore());
							}
						}
						itemFormList.add(itemForm);
					}
				}
				titleForm.setItemList(itemFormList);
				titleForm.setScore(String.valueOf(score));
				titleFormList.add(titleForm);
			}
//			resultMap.put("titleFormList", titleFormList);
		}
		String isOpen = gradeBiz.getCfgByCode("res_permit_open_doc");
		List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchDeptFlowRec(deptFlow,isOpen);
		Float sum=0f;
		Map<String, Float> heJiMap=new HashMap<String, Float>();
		if (gradeInfoList!=null && gradeInfoList.size()>0) {
			for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
				Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
				for (String key: gradeMap.keySet()) {
					Object o=gradeMap.get(key);
					if (o instanceof String) {
						continue;
					}
					Map<String,String> scoreMap=(Map<String,String>)o;
					Float value=heJiMap.get(key);
					if (value==null) {
						value=0f;
					}
					Float deValue= Float.parseFloat(scoreMap.get("score"));
					value=value+deValue;
					heJiMap.put(key, value);
				}
			}
			for (String key: heJiMap.keySet()) {
				Float value=heJiMap.get(key);
				value=value/gradeInfoList.size();
				BigDecimal bd=new BigDecimal(value).setScale(1, BigDecimal.ROUND_HALF_UP);
				heJiMap.put(key, bd.floatValue());
				sum=sum+bd.floatValue();
			}
		}
//		resultMap.put("heJiMap", heJiMap);
		BigDecimal ii=new BigDecimal(sum).setScale(1, BigDecimal.ROUND_HALF_UP);
		sum=ii.floatValue();
		resultMap.put("average", sum.toString());

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		double allScore = 0.0;
		if(null != titleFormList && titleFormList.size()>0){
			for (ResAssessCfgTitleForm form:titleFormList) {
				Map<String,Object> titleMap = new HashMap<>();
				titleMap.put("inputId",form.getId());
				titleMap.put("label",form.getName());
				titleMap.put("score",form.getScore());
				allScore += Double.parseDouble(form.getScore());
				titleMap.put("inputType","title");
				List<ResAssessCfgItemForm> items = form.getItemList();
				List<Map<String,String>> itemMapList = new ArrayList<>();
				if(null != items && items.size()>0){
					for (ResAssessCfgItemForm item:items) {
						Map<String,String> itemMap = new HashMap<>();
						itemMap.put("inputId",item.getId());
						itemMap.put("label",item.getName());
						itemMap.put("tip",item.getName());
						itemMap.put("inputType","item");
						itemMap.put("score", "Nine".equals(assessCfg.getAssessTypeId()) ? "9" : item.getScore());
						itemMap.put("evlScore",null == heJiMap ? "" : heJiMap.get(item.getId()).toString());
						itemMapList.add(itemMap);
					}
				}
				titleMap.put("items",itemMapList);
				resultMapList.add(titleMap);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		resultMap.put("allScore",Double.toString(allScore));

		return resultMap;
	}
	/**
	 * 科室评分学员list
	 * @return
	 */
	@RequestMapping(value="/deptGradeList",method=RequestMethod.POST)
	@ResponseBody
	public Object deptGradeList(String userFlow,String deptFlow,Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(deptFlow)){
			return ResultDataThrow("科室标识符为空");
		}
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}

		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		String isOpen = gradeBiz.getCfgByCode("res_permit_open_doc");
		PageHelper.startPage(pageIndex, pageSize);
		List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchDeptFlowRec(deptFlow,isOpen);

		Map<String,String> gradeScoreMap=new HashMap<>();
		if (gradeInfoList!=null && gradeInfoList.size()>0) {
			for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
				Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
				gradeScoreMap.put(gradeInfo.getRecFlow(),gradeMap.get("totalScore").toString());
			}
		}
		final Map<String,String> map=gradeScoreMap;
		Collections.sort(gradeInfoList,new Comparator<DeptTeacherGradeInfo>() {
			@Override
			public int compare(DeptTeacherGradeInfo o1, DeptTeacherGradeInfo o2) {
				String	o1Key=o1.getRecFlow();
				String	o2Key=o2.getRecFlow();
				String s1=map.get(o1Key);
				String s2=map.get(o2Key);
				Float f1=0f;
				Float f2=0f;
				try {
					f1=Float.valueOf(s1);
					f2=Float.valueOf(s2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Float result=f2-f1;
				return result>0?1:result==0?0:-1;
			}
		});
//		resultMap.put("gradeScoreMap", gradeScoreMap);
//		resultMap.put("gradeInfoList", gradeInfoList);
		resultMap.put("dataCount", PageHelper.total);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		int count = 1;
		if(null != gradeInfoList && gradeInfoList.size()>0){
			for (DeptTeacherGradeInfo info:gradeInfoList) {
				Map<String,Object> infoMap = new HashMap<>();
				infoMap.put("count",count);
				infoMap.put("recFlow",info.getRecFlow());
				infoMap.put("docName",info.getOperUserName());
				infoMap.put("docFlow",info.getOperUserFlow());
				infoMap.put("gradeScore",gradeScoreMap.get(info.getRecFlow()));
				resultMapList.add(infoMap);
				count++;
			}
		}
		resultMap.put("resultMapList",resultMapList);

		return resultMap;
	}
	/**
	 * 带教评分详情
	 * @return
	 */
	@RequestMapping(value="/gradeDetail",method=RequestMethod.POST)
	@ResponseBody
	public Object gradeDetail(String userFlow,String recFlow,String gradeType) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if (StringUtil.isBlank(recFlow)) {
			return ResultDataThrow("评价标识符为空");
		}
		if (StringUtil.isBlank(gradeType)) {
			return ResultDataThrow("评价类型为空");
		}
        if (!com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(gradeType) && !com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(gradeType)) {
			return ResultDataThrow("评价类型只能是TeacherGrade或DeptGrade");
		}
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		//评分内容
		DeptTeacherGradeInfo gradeInfo = gradeBiz.readByFlow(recFlow);
		Map<String,Object> gradeMap = null;
		if (gradeInfo != null && StringUtil.isNotBlank(gradeInfo.getRecContent())) {
			gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
//			resultMap.put("gradeMap",gradeMap);
		}
		//评分模板
		List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
		String cfgCodeId =null;
        if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(gradeType)) {
			cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
        } else if (com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(gradeType)) {
			cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		}
		ResAssessCfg assessCfg = jswjwBiz.getGradeTemplate(cfgCodeId);
//		resultMap.put("assessCfg", assessCfg);
		Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if (titleElementList != null && !titleElementList.isEmpty()) {
			for (Element te : titleElementList) {
				ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				List<Element> itemElementList = te.elements("item");
				List<ResAssessCfgItemForm> itemFormList = null;
				int score=0;
				if (itemElementList != null && !itemElementList.isEmpty()) {
					itemFormList = new ArrayList<ResAssessCfgItemForm>();
					for (Element ie : itemElementList) {
						ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
						itemForm.setId(ie.attributeValue("id"));
						itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
						itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
						if(ResAssessScoreTypeEnum.Nine.getId().equals(assessCfg.getAssessTypeId()))
						{
							score +=9;
						}else {
							if (StringUtil.isNotBlank(itemForm.getScore())) {
								score += Integer.valueOf(itemForm.getScore());
							}
						}
						itemFormList.add(itemForm);
					}
				}
				titleForm.setItemList(itemFormList);
				titleForm.setScore(String.valueOf(score));
				titleFormList.add(titleForm);
			}
//			resultMap.put("titleFormList", titleFormList);
			resultMap.put("totalScore", gradeMap.get("totalScore"));

			List<Map<String,Object>> resultMapList = new ArrayList<>();
			double allScore = 0.0;
			if(null != titleFormList && titleFormList.size()>0){
				for (ResAssessCfgTitleForm form:titleFormList) {
					Map<String,Object> titleMap = new HashMap<>();
					titleMap.put("inputId",form.getId());
					titleMap.put("label",form.getName());
					titleMap.put("score",form.getScore());
					allScore += Double.parseDouble(form.getScore());
					titleMap.put("inputType","title");
					List<ResAssessCfgItemForm> items = form.getItemList();
					List<Map<String,String>> itemMapList = new ArrayList<>();
					if(null != items && items.size()>0){
						for (ResAssessCfgItemForm item:items) {
							Map<String,String> itemMap = new HashMap<>();
							itemMap.put("inputId",item.getId());
							itemMap.put("label",item.getName());
							itemMap.put("tip",item.getName());
							itemMap.put("inputType","item");
							itemMap.put("score", "Nine".equals(assessCfg.getAssessTypeId()) ? "9" : item.getScore());
							Map<String,Object> map = (Map<String,Object>)gradeMap.get(item.getId());
							itemMap.put("evlScore",null == map ? "" : (String)map.get("score"));
							itemMapList.add(itemMap);
						}
					}
					titleMap.put("items",itemMapList);
					resultMapList.add(titleMap);
				}
			}
			resultMap.put("resultMapList",resultMapList);
			resultMap.put("allScore",Double.toString(allScore));
		}
		return resultMap;
	}

	public static List<Map<String, String>> getTimes(String startDate,String endDate,String processFlow ) throws ParseException {
		List<Map<String, String>> list = null;
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			list = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar c = Calendar.getInstance();
			Date date = sdf.parse(startDate);
			c.setTime(date);
			String startMonth= sdf.format(c.getTime());
			date = sdf.parse(endDate);
			c.setTime(date);
			String endMonth= sdf.format(c.getTime());
			while (startMonth.compareTo(endMonth) <= 0) {
				Map<String, String> newTime = new HashMap<>();
				newTime.put("evlMonth", startMonth);
				newTime.put("processFlow", processFlow);
				//获取开始与结束时间
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c1 = Calendar.getInstance();
				date = sdf.parse(startMonth);
				c1.setTime(date);
				c1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
				String  monthFirstDay = format.format(c1.getTime());
				if(monthFirstDay.compareTo(startDate)<=0)
				{
					monthFirstDay=startDate;
				}
				c1.add(Calendar.MONTH, 1);
				c1.add(Calendar.DATE,-1);
				String endTime=format.format(c1.getTime());
				if(endTime.compareTo(endDate)>=0)
				{
					endTime=endDate;
				}
				newTime.put("startTime", monthFirstDay);
				newTime.put("endTime", endTime);
				list.add(newTime);

				//开始时间加1个自然月
				date = sdf.parse(startMonth);
				c.setTime(date);
				c.add(Calendar.MONTH, 1);
				startMonth=sdf.format(c.getTime());
			}

			Collections.sort(list, new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> f1, Map<String, String> f2) {
					String order1 = f1.get("evlMonth");
					String order2 = f2.get("evlMonth");
					if (order1 == null) {
						return -1;
					} else if (order2 == null) {
						return 1;
					} else if (order1 != null && order2 != null) {
						return order1.compareTo(order2);
					}
					return 0;
				}
			});
		}
		return list;
	}

	@RequestMapping(value = {"/evalStudentList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object evalStudentList(String userFlow,String roleId,String seachStr, String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,
								  Integer pageIndex,Integer pageSize) throws DocumentException, ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow( "每页条数为空");
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
//		schArrangeResultMap.put("deptFlow", userinfo.getDeptFlow());
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.schDoctorSchProcessHead(schArrangeResultMap);

		Map<String,List<Map<String, Object>>> evalMap=new HashMap<String,List<Map<String, Object>>>();
		for (Map<String, String> map : resDoctorSchProcess) {
			SchArrangeResult schArrangeResult=jswjwTeacherBiz.readSchArrangeResult(map.get("schResultFlow"));
			String processFlow=map.get("processFlow");
			List<Map<String, String>> times=new ArrayList<>();
			if (schArrangeResult!=null) {
				String startDate=schArrangeResult.getSchStartDate();
				String endDate=schArrangeResult.getSchEndDate();
				times=getTimes(startDate,endDate,processFlow);
			}
			List<Map<String, Object>> evals=new ArrayList<>();
			if(times!=null&&times.size()>0)
			{
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("teacherUserFlow", userFlow);
				params.put("times", times);
				evals=jswjwTeacherBiz.findProcessEvals(params);
			}
			evalMap.put(processFlow,evals);
		}
//		resultMap.put("evalMap", evalMap);
//		resultMap.put("list",resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != resDoctorSchProcess && resDoctorSchProcess.size()>0){
			for (Map<String,String> bean:resDoctorSchProcess) {
				Map<String,Object> map = new HashMap<>();
//				map.put("currDate",DateUtil.getCurrDate());
				String processFlow = bean.get("processFlow");
				map.put("userName",bean.get("userName"));
				map.put("schStartDate",bean.get("schStartDate"));
				map.put("schEndDate",bean.get("schEndDate"));
				map.put("userHeadImg",bean.get("userHeadImg"));
//				map.put("docFlow",bean.get("userFlow"));
//				map.put("schResultFlow",bean.get("schResultFlow"));
//				map.put("resultFlow",bean.get("resultFlow"));
				map.put("sessionNumber",bean.get("sessionNumber"));
				map.put("speName",bean.get("speName"));
//				map.put("teacherUserName",bean.get("teacherUserName"));
//				map.put("processFlow",processFlow);
//				map.put("orgFlow",bean.get("orgFlow"));
//				map.put("orgName",bean.get("orgName"));
//				map.put("deptFlow",bean.get("deptFlow"));
//				map.put("deptName",bean.get("deptName"));
//				map.put("schDeptFlow",bean.get("schDeptFlow"));
//				map.put("schDeptName",bean.get("schDeptName"));
//				map.put("schType",bean.get("statusId"));
//				map.put("schStatus",bean.get("statusName"));

				List<Map<String,Object>> evalMapList = new ArrayList<>();
				List<Map<String, Object>> evalList = evalMap.get(processFlow);
				String doctorFlow = bean.get("userFlow");
				if(null != evalList && evalList.size()>0){
					for (Map<String, Object> m:evalList) {
						Map<String,Object> eMap = new HashMap<>();
						eMap.put("processFlow",m.get("processFlow"));
						eMap.put("doctorFlow",doctorFlow);
						eMap.put("evalMonth",m.get("evalMonth"));
						eMap.put("startTime",m.get("startTime"));
						eMap.put("endTime",m.get("endTime"));
						String recordFlow = null == m.get("recordFlow") ? "" : (String)m.get("recordFlow");
						eMap.put("recordFlow",recordFlow);
						if(StringUtil.isBlank(recordFlow)){
							eMap.put("statusId","NotAudit");
							eMap.put("statusName","待考评");
						}else{
							eMap.put("statusId","Audited");
							eMap.put("statusName","已考评");
						}
						evalMapList.add(eMap);
					}
				}
				map.put("evals",evalMapList);
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		return resultMap;
	}
	/**
	 * 月度考核表列表
	 * @param userFlow
	 * @param processFlow
	 * @param schResultFlow
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value="/monthEvalList",method = {RequestMethod.POST})
	@ResponseBody
	public Object monthEvalList(String userFlow,String doctorFlow,String processFlow,String schResultFlow) throws ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(doctorFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		if(StringUtil.isBlank(schResultFlow)){
			return ResultDataThrow("排班标识符为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		//拆分学员的培训时间
		SchArrangeResult schArrangeResult=jswjwTeacherBiz.readSchArrangeResult(schResultFlow);
		List<Map<String, String>> times=new ArrayList<>();
		if (schArrangeResult!=null) {
			String startDate=schArrangeResult.getSchStartDate();
			String endDate=schArrangeResult.getSchEndDate();
			times=getTimes(startDate,endDate,processFlow);
		}
		List<Map<String, Object>> evals=null;
		if(times!=null&&times.size()>0)
		{
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("teacherUserFlow", userFlow);
			params.put("times", times);
			evals=jswjwTeacherBiz.findProcessEvals(params);
		}
		resultMap.put("evals",evals);
		resultMap.put("processFlow",processFlow);
		resultMap.put("doctorFlow",doctorFlow);
		return resultMap;
	}

	@RequestMapping(value="/showMonthEval",method=RequestMethod.POST)
	@ResponseBody
	public Object showMonthEval(String userFlow,String doctorFlow,String processFlow,String recordFlow,String evalMonth) throws ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(doctorFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		if(StringUtil.isBlank(evalMonth)){
			return ResultDataThrow("考核时间不能为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		ResDoctorSchProcessEvalWithBLOBs eval=jswjwBiz.getDoctorProcessEvalByFlow(recordFlow);
		if(eval==null)
			eval=jswjwBiz.getDoctorProcessEval(processFlow,evalMonth);
		List<FromTitle> titleList=null;
		String configXml="";
		String configFlow="";
        String IsForm = com.pinde.core.common.GlobalConstant.FLAG_N;
		Map<String, Object> valueMap = null;
		if(eval!=null) {
			IsForm=eval.getIsForm();
			configXml=eval.getFormCfg();
			//已经保存的分数
			valueMap = jswjwBiz.parseScoreXml(eval.getEvalResult());
//			resultMap.put("valueMap", valueMap);
		}else{
			//评分配置
			ResDoctorProcessEvalConfig config=jswjwBiz.getProcessEvalConfig(userinfo.getOrgFlow());
			if(config!=null) {
				configXml = config.getFormCfg();
                IsForm = com.pinde.core.common.GlobalConstant.FLAG_Y;
				configFlow=config.getConfigFlow();
			}
		}
//		resultMap.put("isAudit",eval!=null);
		titleList=jswjwBiz.parseFromXmlForList(configXml);
		if(titleList!=null&&titleList.size()>0){
            IsForm = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}else{
            IsForm = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
//		resultMap.put("titleList",titleList);
//		resultMap.put("IsForm",IsForm);
		resultMap.put("eval",eval);
		resultMap.put("configFlow",configFlow);
		resultMap.put("processFlow",processFlow);
		resultMap.put("doctorFlow",doctorFlow);
		resultMap.put("recordFlow",recordFlow);

        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(IsForm) && null != titleList && titleList.size() > 0) {
			List<Map<String,Object>> titleMapList = new ArrayList<>();
			for (FromTitle title:titleList) {
				Map<String,Object> titelMap = new HashMap<>();
				String count = "1";
				titelMap.put("inputId","group"+count);
				titelMap.put("inputType","group");
				titelMap.put("lable",title.getName()+"(" + title.getScore() + ")分");
				List<Map<String,Object>> itemMapList = new ArrayList<>();
				List<FromItem> itemList = title.getItems();
				if(null != itemList && itemList.size()>0){
					for (FromItem item:itemList) {
						Map<String,Object> itemMap = new HashMap<>();
						itemMap.put("inputId",item.getId());
						itemMap.put("inputType","text");
						itemMap.put("lable",item.getName()+"(" + item.getScore() + ")分");
						itemMap.put("tip",item.getName());
						itemMap.put("value",null == valueMap ? "" : null == valueMap.get(item.getId()) ? "" : valueMap.get(item.getId()));
						itemMapList.add(itemMap);
					}
				}
				titelMap.put("inputs",itemMapList);
				titleMapList.add(titelMap);
			}
			resultMap.put("titleMapList",titleMapList);
		}

		return resultMap;
	}

	@RequestMapping(value="/ownerInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object ownerInfo(String userFlow,Model model) throws ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		model.addAttribute("userinfo",userinfo);
		List<SysUserRole> userRoles = jswjwBiz.getSysUserRole(userFlow);
		if(userRoles!=null&&userRoles.size()>0) {
			//获取当前配置的老师角色
			String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
			//获取当前配置的科主任角色
			String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
			//获取当前配置的科秘角色
			String seretaryRole = jswjwBiz.getCfgCode("res_secretary_role_flow");

			List<Map<String,String>> roles=new ArrayList<>();
			for (SysUserRole sur : userRoles) {
				Map<String,String> map=new HashMap<>();
				String ur = sur.getRoleFlow();
				if (headRole.equals(ur)) {
					map.put("roleId", "Head");
					map.put("roleName", "科主任");
					roles.add(map);
				}
				if (teacherRole.equals(ur)) {
					map.put("roleId", "Teacher");
					map.put("roleName", "老师");
					roles.add(map);
				}
				if (seretaryRole.equals(ur)) {
					map.put("roleId", "Seretary");
					map.put("roleName", "教秘");
					roles.add(map);
				}
			}
			resultMap.put("roles",roles);
		}
		return resultMap;
	}

	@RequestMapping(value="/saveOwnerInfo",method=RequestMethod.POST)
	@ResponseBody
	public Object saveOwnerInfo(SysUser user) throws ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(user.getUserFlow())){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isNotBlank(user.getSexId())) {
			if (!"Man".equals(user.getSexId()) && !"Woman".equals(user.getSexId())) {
				return ResultDataThrow("性别ID不正确");
			}
			if ("Man".equals(user.getSexId())) {
				user.setSexName("男");
			}
			if ("Woman".equals(user.getSexId())) {
				user.setSexName("女");
			}
		}
		int result=jswjwBiz.saveUserInfo(user);
		if(result==0)
		{
			return ResultDataThrow("保存失败");
		}
		return resultMap;
	}

	/**
	 * 培训管理统计
	 *
	 * @return
	 */
	@RequestMapping(value = {"/resManage"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object resManage(String userFlow, String yearMonth, HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if (StringUtil.isBlank(yearMonth)) {
			return ResultDataThrow("日期为空");
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(yearMonth);
		} catch (Exception e) {
			return ResultDataThrow("yearMonth格式有误");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}

		Map<String, String> param=new HashMap<String, String>();
		param.put("headFlow", userFlow);
		param.put("yearMonth", yearMonth);
		param.put("typeId", "monthCurrent");
		int monthCurrent=jswjwKzrBiz.schDoctorSchProcessHeadCount(param);
//		resultMap.put("monthCurrent", monthCurrent);

		param.put("typeId", "monthSch");
		int monthSch=jswjwKzrBiz.schDoctorSchProcessHeadCount(param);
//		resultMap.put("monthSch", monthSch);

		param.put("typeId", "errorSch");
		int errorSch=jswjwKzrBiz.schDoctorSchProcessHeadCount(param);
//		resultMap.put("errorSch", errorSch);

		yearMonth=DateUtil.addMonth(yearMonth,1);
		param.put("yearMonth",yearMonth);
		param.put("typeId", "waitSch");
		int waitSch=jswjwKzrBiz.schDoctorSchProcessHeadCount(param);
//		resultMap.put("waitSch", waitSch);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("order","1");
		map.put("typeName","本月轮转学员");
		map.put("typeId","monthCurrent");
		map.put("qty",monthCurrent);
		resultMapList.add(map);
		map = new HashMap<>();
		map.put("order","2");
		map.put("typeName","本月计划出科学员");
		map.put("typeId","monthSch");
		map.put("qty",monthSch);
		resultMapList.add(map);
		map = new HashMap<>();
		map.put("order","3");
		map.put("typeName","计划入科学员");
		map.put("typeId","waitSch");
		map.put("qty",waitSch);
		resultMapList.add(map);
		map = new HashMap<>();
		map.put("order","4");
		map.put("typeName","逾期未出科学员");
		map.put("typeId","errorSch");
		map.put("qty",errorSch);
		resultMapList.add(map);
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}
	/**
	 * 科教通知
	 *
	 * @return
	 */
	@RequestMapping(value = {"/getSysNotice"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object getSysNotice(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}

		if (pageIndex == null) {
			return ResultDataThrow("当前页码为空");
		}
		if (pageSize == null) {
			return ResultDataThrow("每页条数为空");
		}
		String orgFlow="";
		PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, String>> infos = this.noticeBiz.searchInfoByOrgBeforeDate(orgFlow, null, com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID, userFlow, null);
//		resultMap.put("infoList",infos);

		List<Map<String,String>> resultMapList = new ArrayList<>();
//		Map<String,Object> isReadMap=new HashMap<>();
		if(infos!=null&&infos.size()>0) {
			for(Map<String,String> info:infos) {
				Map<String,String> map = new HashMap<>();
				map.put("infoFlow",info.get("infoFlow"));
				map.put("resNoticeTitle",info.get("infoTitle"));
				map.put("resNoticeContent",info.get("infoContent"));
				map.put("noticePicPath",info.get("titleImg"));
				map.put("createTime",DateUtil.transDate(info.get("infoTime")));

				ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(info.get("infoFlow"),userFlow);
//				isReadMap.put(info.get("infoFlow"),resReadInfo);
                map.put("isRead", null == resReadInfo ? com.pinde.core.common.GlobalConstant.FLAG_N : com.pinde.core.common.GlobalConstant.FLAG_Y);
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList", resultMapList);

		//查询所有未读消息数
		Integer noReadNum = 0;
        List<Map<String, String>> notReadList = noticeBiz.searchInfoByOrgNotRead(orgFlow, com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
		if(null != notReadList && notReadList.size()>0){
			noReadNum = notReadList.size();
		}
		resultMap.put("noReadNum",noReadNum.toString());

		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/no-pic.png";
		resultMap.put("hostUrl",hostUrl);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		resultMap.put("dataCount", PageHelper.total);
		return resultMap;
	}

	@RequestMapping(value={"/sysNoticeDetail"},method = {RequestMethod.POST})
	@ResponseBody
	public Object sysNoticeDetail(String userFlow,String infoFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(infoFlow)){
			return ResultDataThrow("住培动态标识符为空");
		}
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		if(info==null)
		{
			return ResultDataThrow("住培动态不存在，请刷新列表页面");
		}
		ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(infoFlow,userFlow);
		if(resReadInfo==null)
		{
			resReadInfo=new ResReadInfo();
			resReadInfo.setInfoFlow(infoFlow);
			resReadInfo.setUserFlow(userFlow);
			inxInfoBiz.saveReadInfo(userFlow,resReadInfo);
		}
//		HttpServletRequest httpRequest =(HttpServletRequest) request;
//		String httpurl=httpRequest.getRequestURL().toString();
//		String servletPath=httpRequest.getServletPath();
//		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/showSysNotice.jsp?recordFlow="+infoFlow;
//		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/showSysNotice2.jsp?recordFlow="+infoFlow;
//		resultMap.put("iosDetailUrl",hostUrl);
//		resultMap.put("androidDetailUrl",androidimgurl);
		return resultMap;
	}

	@RequestMapping(value = {"/activityList"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object activityList(String userFlow
			,String isOwner,String yearMonth
			,Integer pageIndex,Integer pageSize,String roleId) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(isOwner)){
			return ResultDataThrow("isOwner为空");
		}
        if (!isOwner.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) && !isOwner.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
			return ResultDataThrow("isOwner只能是Y或N");
		}
		if (StringUtil.isBlank(yearMonth)) {
			return ResultDataThrow("日期为空");
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(yearMonth);
		} catch (Exception e) {
			return ResultDataThrow("yearMonth格式有误");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}

		if (pageIndex == null) {
			return ResultDataThrow("当前页码为空");
		}
		if (pageSize == null) {
			return ResultDataThrow("每页条数为空");
		}
		Map<String,String> param=new HashMap<>();
		param.put("isOwner",isOwner);
		param.put("yearMonth",yearMonth);
		param.put("roleFlag","head");
		param.put("userFlow",userinfo.getUserFlow());
		resultMap.put("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		param.put("orgFlow", userinfo.getOrgFlow());
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		List<SysUserRole> userRoleList = jswjwBiz.getSysUserRole(userFlow); //获取该用户下的所有角色信息
		for (Map<String,Object> obj: list) {
			for (SysUserRole role:userRoleList) {
				if(obj.containsKey("auditRole")) {
					if (obj.get("auditRole").toString().contains(role.getRoleFlow())) {
                        obj.put("audit", com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
			}
		}
		Map<String,Object> activityMap=new HashMap<>();
		if(list!=null) {
			for (Map<String,Object> info:list )
			{
				List<Map<String,Object>>  results=activityBiz.readActivityResults((String) info.get("activityFlow"),"");
				activityMap.put((String) info.get("activityFlow"),results);
			}
		}
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
//		resultMap.put("list", list);
//		resultMap.put("activityMap", activityMap);
		List<Map<String, Object>> activityList = activityBiz.findActivityList(param);
		if (activityList != null) {
			resultMap.put("dataCount", activityList.size());
		} else {
			resultMap.put("dataCount", PageHelper.total);
		}
		resultMap.put("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));

		Map<String,Object> activityTypeMap=activityBiz.findActivityTypeMap(param);
//		resultMap.put("activityTypeMap", activityTypeMap);
		String key="jsres_"+userinfo.getOrgFlow()+"_org_activity_code_type";
		String cfgv=jswjwBiz.getJsResCfgCode(key);
		resultMap.put("codeType", cfgv);
		resultMap.put("activityCodeTime", "15");

		List<Map<String,Object>> activityTypeList = new ArrayList<>();
		Map<String,Object> typeMap = new HashMap<>();
		typeMap.put("activityTypeId","1");
		typeMap.put("activityTypeName","教学查房");
		typeMap.put("qty",null == activityTypeMap.get("1") ? "0" : activityTypeMap.get("1").toString());
		activityTypeList.add(typeMap);
//		typeMap = new HashMap<>();
//		typeMap.put("activityTypeId","2");
//		typeMap.put("activityTypeName","疑难病例讨论");
//		typeMap.put("qty",null == activityTypeMap.get("2") ? "0" : activityTypeMap.get("2").toString());
//		activityTypeList.add(typeMap);
//		typeMap = new HashMap<>();
//		typeMap.put("activityTypeId","3");
//		typeMap.put("activityTypeName","危重病例讨论");
//		typeMap.put("qty",null == activityTypeMap.get("3") ? "0" : activityTypeMap.get("3").toString());
//		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","4");
		typeMap.put("activityTypeName","临床小讲课");
		typeMap.put("qty",null == activityTypeMap.get("4") ? "0" : activityTypeMap.get("4").toString());
		activityTypeList.add(typeMap);
//		typeMap = new HashMap<>();
//		typeMap.put("activityTypeId","5");
//		typeMap.put("activityTypeName","死亡病例讨论");
//		typeMap.put("qty",null == activityTypeMap.get("5") ? "0" : activityTypeMap.get("5").toString());
//		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","6");
		typeMap.put("activityTypeName","入轮转科室教育");
		typeMap.put("qty",null == activityTypeMap.get("6") ? "0" : activityTypeMap.get("6").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","7");
		typeMap.put("activityTypeName","出科考核");
		typeMap.put("qty",null == activityTypeMap.get("7") ? "0" : activityTypeMap.get("7").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","8");
		typeMap.put("activityTypeName","技能培训");
		typeMap.put("qty",null == activityTypeMap.get("8") ? "0" : activityTypeMap.get("8").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","9");
		typeMap.put("activityTypeName","教学阅片");
		typeMap.put("qty",null == activityTypeMap.get("9") ? "0" : activityTypeMap.get("9").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","10");
		typeMap.put("activityTypeName","门诊教学");
		typeMap.put("qty",null == activityTypeMap.get("10") ? "0" : activityTypeMap.get("10").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","11");
		typeMap.put("activityTypeName","教学病例讨论");
		Integer num1 = null == activityTypeMap.get("2") ? 0 : Integer.parseInt (activityTypeMap.get("2").toString());
		Integer num2 = null == activityTypeMap.get("3") ? 0 : Integer.parseInt (activityTypeMap.get("3").toString());
		Integer num3 = null == activityTypeMap.get("5") ? 0 : Integer.parseInt (activityTypeMap.get("5").toString());
//		Integer num4 = null == activityTypeMap.get("11") ? 0 : (Integer)activityTypeMap.get("11");
		Integer num4 = 0;
		if (null != activityTypeMap.get("11") ){
			num4 = Integer.parseInt(activityTypeMap.get("11").toString());
		}
		typeMap.put("qty",num1+num2+num3+num4+"");
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","12");
		typeMap.put("activityTypeName","临床操作技能床旁教学");
		typeMap.put("qty",null == activityTypeMap.get("12") ? "0" : activityTypeMap.get("12").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","13");
		typeMap.put("activityTypeName","住院病历书写指导教学");
		typeMap.put("qty",null == activityTypeMap.get("13") ? "0" : activityTypeMap.get("13").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","14");
		typeMap.put("activityTypeName","手术操作指导教学");
		typeMap.put("qty",null == activityTypeMap.get("14") ? "0" : activityTypeMap.get("14").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","15");
		typeMap.put("activityTypeName","影像诊断报告书写指导教学");
		typeMap.put("qty",null == activityTypeMap.get("15") ? "0" : activityTypeMap.get("15").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","16");
		typeMap.put("activityTypeName","临床文献研读会");
		typeMap.put("qty",null == activityTypeMap.get("16") ? "0" : activityTypeMap.get("16").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","17");
		typeMap.put("activityTypeName","入院教育");
		typeMap.put("qty",null == activityTypeMap.get("17") ? "0" : activityTypeMap.get("17").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","18");
		typeMap.put("activityTypeName","入专业基地教育");
		typeMap.put("qty",null == activityTypeMap.get("18") ? "0" : activityTypeMap.get("18").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","19");
		typeMap.put("activityTypeName","晨间报告");
		typeMap.put("qty",null == activityTypeMap.get("19") ? "0" : activityTypeMap.get("19").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","20");
		typeMap.put("activityTypeName","报告单分析");
		typeMap.put("qty",null == activityTypeMap.get("20") ? "0" : activityTypeMap.get("20").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","21");
		typeMap.put("activityTypeName","教学上机");
		typeMap.put("qty",null == activityTypeMap.get("21") ? "0" : activityTypeMap.get("21").toString());
		activityTypeList.add(typeMap);
		typeMap = new HashMap<>();
		typeMap.put("activityTypeId","22");
		typeMap.put("activityTypeName","上机演示");
		typeMap.put("qty",null == activityTypeMap.get("22") ? "0" : activityTypeMap.get("22").toString());
		activityTypeList.add(typeMap);

		resultMap.put("activityTypeList",activityTypeList);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != list && list.size()>0){
			for (Map<String,Object> info:list) {
				Map<String,Object> map = new HashMap<>();
				map.put("activityFlow",info.get("activityFlow"));
				map.put("speakerFlow",info.get("speakerFlow"));
				map.put("activityName",info.get("activityName"));
				map.put("activityTypeName",info.get("activityTypeName"));
				map.put("activityAddress",info.get("activityAddress"));
				map.put("activityRemark",info.get("activityRemark"));
				map.put("activityStatus",info.get("activityStatus"));
				map.put("audit",info.get("audit"));
				map.put("opinion",info.get("opinion"));
				map.put("resultFlow",info.get("resultFlow"));
				map.put("userName",info.get("userName"));
				map.put("deptName",info.get("deptName"));
				map.put("isRegiest",info.get("isRegiest"));
				map.put("isScan",info.get("isScan"));
				map.put("isScan2",info.get("isScan2"));
				map.put("startTime",info.get("startTime"));
				map.put("endTime",info.get("endTime"));
				map.put("evalScore",null == info.get("evalScore") ? "0" : info.get("evalScore").toString());
				String code1 = "func://funcFlow=activitySigin&activityFlow=" + info.get("activityFlow");
				String code2 = "func://funcFlow=activityOutSigin&activityFlow=" + info.get("activityFlow");
				map.put("qrCode1",code1);
				map.put("qrCode2",code2);
				List<Map<String,Object>>  results = activityBiz.readActivityResults((String) info.get("activityFlow"),"");
				if(null == results || results.size()==0){
					map.put("operId","del");
					map.put("operName","删除");
				}
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/qrCode"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object qrCode(String userFlow,String activityFlow,String roleId) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(activityFlow)){
			return ResultDataThrow("活动标识符为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
        if (info == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
		{
			return ResultDataThrow("活动信息不存在");
		}
		String url = "func://funcFlow=activitySigin&activityFlow="+activityFlow;
		String url2 = "func://funcFlow=activityOutSigin&activityFlow="+activityFlow;
		resultMap.put("url", url);
		resultMap.put("url2", url2);
		return resultMap;
	}

	@RequestMapping(value = {"/delActivity"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object delActivity(String userFlow,String activityFlow,String roleId) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(activityFlow)){
			return ResultDataThrow("活动标识符为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		if(activity==null)
		{
			return ResultDataThrow("活动信息不存在");
		}

        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.getRecordStatus()))
		{
			return ResultDataThrow("活动信息已被删除，请刷新列表！");
		}

		List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow,"");
		if(results!=null&&results.size()>0)
		{
			return ResultDataThrow("此活动已有学员扫码，无法删除！");
		}
        activity.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		resultMap.put("activity", activity);
		int c=activityBiz.saveActivityInfo(activity,userinfo);
		if(c==0)
		{
			return ResultDataThrow("删除失败！");
		}else if(c==-1){
			return ResultDataThrow("该角色无法发起教学活动，请联系基地管理员！");
		}
		return resultMap;
	}

	@RequestMapping(value = {"/showActivity"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object showActivity(String userFlow,String activityFlow,String roleId) throws DocumentException, ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		resultMap.put("roleFlag", roleId);
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(activityFlow)){
			return ResultDataThrow("活动标识符为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus"))) {
			return ResultDataThrow("活动信息不存在");
		}
		resultMap.put("activity", activity);
        resultMap.put("isUpload", activity.get("speakerFlow").equals(userinfo.getUserFlow()) && StringUtil.isNotBlank((String) activity.get("activityFlow")) ? com.pinde.core.common.GlobalConstant.FLAG_Y : com.pinde.core.common.GlobalConstant.FLAG_N);
//		resultMap.put("user",userinfo);
//		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
//		resultMap.put("depts",depts);
//		Map<String,List<SysUser>> deptUserMap=new HashMap<>();
//		if(depts!=null) {
//			String teacherRoleFlow=jswjwBiz.getCfgCode("res_teacher_role_flow");
//			String headRoleFlow = jswjwBiz.getCfgCode("res_head_role_flow");
//			for(SysDept d:depts) {
//				List<SysUser> users=jswjwKzrBiz.readDeptTeachAndHead(d.getDeptFlow(),teacherRoleFlow,headRoleFlow);
//				deptUserMap.put(d.getDeptFlow(),users);
//			}
//		}
//		resultMap.put("deptUserMap",deptUserMap);

		//查询附件
		List<PubFile> fileList = pubFileBiz.findFileByTypeFlow("activity",activityFlow);
		if (null!=fileList && fileList.size()>0){
			String baseUrl = InitConfig.getSysCfg("upload_base_url");
			for (PubFile file : fileList) {
				file.setFilePath(baseUrl+file.getFilePath());
			}
		}
		resultMap.put("activityFile",fileList);
		String is_hide_resSpeaker = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_activity_teach_show");
        if (null != is_hide_resSpeaker && StringUtil.isNotBlank(is_hide_resSpeaker) && is_hide_resSpeaker.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            is_hide_resSpeaker = com.pinde.core.common.GlobalConstant.FLAG_N;
		}else {
            is_hide_resSpeaker = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		resultMap.put("is_hide_resSpeaker", is_hide_resSpeaker);  //Y显示  N 不显示实际主讲人

        List<Map<String, Object>> results = activityBiz.readActivityResultsByType(activityFlow, "", com.pinde.core.common.GlobalConstant.FLAG_N);
		if (null!= results && !results.isEmpty()){
			//是否支持学员签到、签退后修改主讲人、实际主讲人
			String teachEdit = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_activity_teach");
			String realEdit = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_activity_kzr");
			TeachingActivityInfo activityInfo = activityBiz.readActivityInfo(activityFlow);
            if (null != teachEdit && StringUtil.isNotBlank(teachEdit) && teachEdit.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
				String teachDay = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_activity_teach_day");
                teachEdit = DateUtil.dayCompore(com.pinde.core.common.GlobalConstant.FLAG_Y, activityInfo.getStartTime(), teachDay);

			}else {
                teachEdit = com.pinde.core.common.GlobalConstant.FLAG_N;
			}
            if (null != realEdit && StringUtil.isNotBlank(realEdit) && realEdit.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
				String kzrDay = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_activity_kzr_day");
                realEdit = DateUtil.dayCompore(com.pinde.core.common.GlobalConstant.FLAG_Y, activityInfo.getStartTime(), kzrDay);

			}else {
                realEdit = com.pinde.core.common.GlobalConstant.FLAG_N;
			}
            if (teachEdit.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
				resultMap.put("teachEdit", true);
			}else {
				resultMap.put("teachEdit", false);
			}
            if (realEdit.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
				resultMap.put("realEdit", true);
			}else {
				resultMap.put("realEdit", false);
			}
		}
		return resultMap;

//		if(activity!=null) {
//			if (activity.containsKey("activityStatus") && "pass".equals(activity.get("activityStatus"))) {
//				return "res/jswjw/kzr/showPassActivity";
//
//			} else {
//				return "res/jswjw/kzr/showActivity";
//			}
//		}

//		int imgCount=0;
//		if(activity!=null)
//		{
//			String content= (String) activity.get("imageUrl");
//			List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
//			resultMap.put("imageList", imageList);
//			if(imageList!=null)
//			{
//				imgCount=imageList.size();
//			}
//		}
//		resultMap.put("imgCount",imgCount+"张");
//		return resultMap;
	}

	@RequestMapping(value = {"/activityEval"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object activityEval(String userFlow,String activityFlow,String roleId) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(activityFlow)){
			return ResultDataThrow("活动标识符为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			return ResultDataThrow("活动信息不存在");
		}
		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
//		resultMap.put("targets",targets);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != targets && targets.size()>0){
			for (Map<String,Object> info:targets) {
				Map<String,Object> map = new HashMap<>();
				map.put("targetName",info.get("targetName"));
				map.put("isText",info.get("isText"));
				map.put("evalScore", null == info.get("evalScore") ? "0" : info.get("evalScore").toString());
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/activityEvalList"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object activityEvalList(String userFlow,String activityFlow,String searchStr, Integer pageIndex,
								   Integer pageSize,String roleId) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(activityFlow)){
			return ResultDataThrow("活动标识符为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			return ResultDataThrow("活动信息不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("起始页为空");
		}
		if(pageSize==null){
			return ResultDataThrow("页面大小为空");
		}
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
//		resultMap.put("activity",info);
		//评价人员
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow,searchStr);
//		resultMap.put("results",results);
//		resultMap.put("user",userinfo);
		resultMap.put("dataCount", PageHelper.total);

		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
//		resultMap.put("targets",targets);
		//评价人员评分详情
		Map<String,Object> evalDetailMap=new HashMap<>();
		Map<String,Object> evalRemarksMap=new HashMap<>();
		if(results!=null) {
			for(Map<String,Object> r:results) {
				List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(String.valueOf(r.get("resultFlow")));
				if(evals!=null) {
					for(TeachingActivityEval e:evals) {
						evalDetailMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalScore());
						evalRemarksMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalRemarks());
					}
				}
			}
		}
//		resultMap.put("evalDetailMap",evalDetailMap);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != results && results.size()>0){
			for (Map<String,Object> m:results) {
				Map<String,Object> map = new HashMap<>();
				map.put("userName",m.get("userName"));
				map.put("sessionNumber",m.get("sessionNumber"));
				map.put("speName",m.get("speName"));
				map.put("siginTime",m.get("siginTime"));
				map.put("siginTime2",m.get("siginTime2"));
				map.put("resultFlow",m.get("resultFlow"));
				map.put("evalScore",null == m.get("evalScore") ? "" : m.get("evalScore").toString());
				map.put("isEffective",m.get("isEffective"));
				if(null != targets && targets.size()>0){
					List<Map<String,Object>> targetList = new ArrayList<>();
					for (Map<String,Object> t:targets) {
						String key = (String)m.get("resultFlow") + (String)t.get("targetFlow");
						Map<String,Object> evalMap = new HashMap<>();
						evalMap.put("targetName",t.get("targetName"));
						evalMap.put("isText",t.get("isText"));
						evalMap.put("evalScore", null == evalDetailMap ? "0" : null == evalDetailMap.get(key) ? "0" : evalDetailMap.get(key).toString());
						evalMap.put("evalRemarks",null == evalRemarksMap ? "" : null == evalRemarksMap.get(key) ? "" : evalRemarksMap.get(key).toString());
						targetList.add(evalMap);
					}
					map.put("targets",targetList);
				}
                if (activity.get("speakerFlow").equals(userinfo.getUserFlow()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(activity.get("IS_EFFECTIVE"))) {
                    map.put("operId", com.pinde.core.common.GlobalConstant.FLAG_Y.equals(m.get("isEffective")) ? com.pinde.core.common.GlobalConstant.FLAG_N : com.pinde.core.common.GlobalConstant.FLAG_Y);
                    map.put("operName", com.pinde.core.common.GlobalConstant.FLAG_Y.equals(m.get("isEffective")) ? "不认可" : "认可");
				}
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/activityStuList"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object activityStuList(String userFlow,String activityFlow,String searchStr,
								  Integer pageIndex,String typeId,
								  Integer pageSize,String roleId) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(activityFlow)){
			return ResultDataThrow("活动标识符为空");
		}
		if(StringUtil.isBlank(typeId)){
			return ResultDataThrow("typeId标识符为空");
		}
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(typeId) && !com.pinde.core.common.GlobalConstant.FLAG_N.equals(typeId)) {
			return ResultDataThrow("typeId只能是Y或N");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			return ResultDataThrow("活动信息不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("起始页为空");
		}
		if(pageSize==null){
			return ResultDataThrow("页面大小为空");
		}
		//评价人员
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> results=activityBiz.readActivityResultsByType(activityFlow,searchStr,typeId);
//		resultMap.put("results",results);
//		resultMap.put("user",userinfo);
		resultMap.put("dataCount", PageHelper.total);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != results && results.size()>0){
			for (Map<String,Object> info:results) {
				Map<String,Object> map = new HashMap<>();
				map.put("userName",info.get("userName"));
				map.put("sessionNumber",info.get("sessionNumber"));
				map.put("speName",info.get("speName"));
				map.put("regiestTime",info.get("regiestTime"));
				map.put("siginTime",info.get("siginTime"));
				map.put("siginTime2",info.get("siginTime2"));
				map.put("resultFlow",info.get("resultFlow"));
				map.put("evalScore", info.get("evalScore"));
				map.put("isEffective",info.get("isEffective"));
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/effectiveResult"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object effectiveResult(String userFlow,  String activityFlow,
								  String resultFlow,  String isEffective, String roleId) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(activityFlow)){
			return ResultDataThrow("活动标识符为空");
		}
		if(StringUtil.isBlank(resultFlow)){
			return ResultDataThrow("resultFlow为空");
		}
		if(StringUtil.isBlank(isEffective)){
			return ResultDataThrow("isEffective为空");
		}
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isEffective) && !com.pinde.core.common.GlobalConstant.FLAG_N.equals(isEffective))
		{
			return ResultDataThrow("isEffective只能是Y或N");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			return ResultDataThrow("活动信息不存在");
		}
		TeachingActivityResult info=activityBiz.readResult(resultFlow);
		if(info==null) {
			return ResultDataThrow("学员参加活动结果信息不存在，请刷新列表页面！");
		}
		info.setIsEffective(isEffective);
		int c=activityBiz.saveResult(info,userFlow);
		if(c==0){
			return ResultDataThrow("修改失败");
		}
		return resultMap;
	}

	@RequestMapping(value = {"/addActivity"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object addActivity(String userFlow,String roleId) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		resultMap.put("roleFlag", roleId);
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrg(userinfo.getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			return ResultDataThrow("暂未配置活动指标，请联系基地管理员");
		}
		resultMap.put("user",userinfo);
		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
//		resultMap.put("depts",depts);
//		Map<String,List<SysUser>> deptUserMap=new HashMap<>();
		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(depts!=null) {
			String teacherRoleFlow=jswjwBiz.getCfgCode("res_teacher_role_flow");
			String headRoleFlow = jswjwBiz.getCfgCode("res_head_role_flow");
			for(SysDept d:depts) {
				Map<String,Object> map = new HashMap<>();
				List<SysUser> users=jswjwKzrBiz.readDeptTeachAndHead(d.getDeptFlow(),teacherRoleFlow,headRoleFlow);
//				deptUserMap.put(d.getDeptFlow(),users);
				map.put("deptFlow",d.getDeptFlow());
				map.put("deptName",d.getDeptName());

				if(null != users && users.size()>0){
					List<Map<String,String>> userList = new ArrayList<>();
					for (SysUser su:users) {
						Map<String,String> userMap = new HashMap<>();
						userMap.put("userFlow",su.getUserFlow());
						userMap.put("userName",su.getUserName());
						userMap.put("userPhone",su.getUserPhone());
						userList.add(userMap);
					}
					map.put("userList",userList);
				}
				resultMapList.add(map);
			}
		}
//		resultMap.put("deptUserMap",deptUserMap);
		resultMap.put("resultMapList",resultMapList);

		List<Map<String,String>> activityList = new ArrayList<>();
		List<ActivityTypeEnum> list = Arrays.asList(ActivityTypeEnum.values());
		for (ActivityTypeEnum ae:list) {
			Map<String,String> dict = new HashMap<>();
			dict.put("activityTypeId",ae.getId());
			dict.put("activityTypeName",ae.getName());
			activityList.add(dict);
		}
		resultMap.put("activityTypeList", activityList);
		resultMap.put("prefabricateFlow", PkUtil.getUUID());
		String is_hide_resSpeaker = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_activity_teach_show");
        if (null != is_hide_resSpeaker && StringUtil.isNotBlank(is_hide_resSpeaker) && is_hide_resSpeaker.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            is_hide_resSpeaker = com.pinde.core.common.GlobalConstant.FLAG_N;
		}else {
            is_hide_resSpeaker = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		resultMap.put("is_hide_resSpeaker", is_hide_resSpeaker);  //Y显示  N 不显示实际主讲人
		return resultMap;
	}

	@RequestMapping(value = {"/saveActivity"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object saveActivity(String userFlow,TeachingActivityInfo activity,String roleId,String recordFlow,String imageXML) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(activity.getSpeakerFlow())){
			return ResultDataThrow("主讲人标识符为空");
		}
		if(StringUtil.isBlank(activity.getDeptFlow())){
			return ResultDataThrow("科室标识符为空");
		}
		if(StringUtil.isBlank(activity.getActivityTypeId())){
			return ResultDataThrow("活动形式为空");
		}
		if(StringUtil.isBlank(activity.getActivityName())){
			return ResultDataThrow("活动名称为空");
		}
		if(StringUtil.isBlank(activity.getActivityAddress())){
			return ResultDataThrow("活动地点为空");
		}
		if(StringUtil.isBlank(activity.getSpeakerPhone())){
			return ResultDataThrow("联系方式为空");
		}
		if(StringUtil.isBlank(activity.getStartTime())){
			return ResultDataThrow("活动开始时间为空");
		}

		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getStartTime());
		} catch (Exception e) {
			return ResultDataThrow("活动开始时间格式有误");
		}
		if(StringUtil.isBlank(activity.getEndTime())){
			return ResultDataThrow("活动结束时间为空");
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getEndTime());
		} catch (Exception e) {
			return ResultDataThrow("活动结束时间格式有误");
		}
		if (StringUtil.isBlank(activity.getActivityFlow())){
			String currDateTime = DateUtil.getCurrDateTime2();
			if (currDateTime.compareTo(activity.getStartTime())>0){
				return ResultDataThrow( "开始时间不得小于当前时间");
			}
		}
		if(activity.getStartTime().compareTo(activity.getEndTime())>=0)
		{
			return ResultDataThrow("开始时间不得大于等于结束时间");
		}
		if(StringUtil.isBlank(activity.getActivityRemark())){
			return ResultDataThrow("活动简介为空");
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		resultMap.put("user",userinfo);
//		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrg(userinfo.getOrgFlow());
		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrgNew(activity.getActivityTypeId(),userinfo.getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			return ResultDataThrow("暂未配置活动指标，请联系基地管理员");
		}
		//校验活动时间是否重复
		int count=checkTime(activity);
		if(count>0 && (!"unpass".equals(activity.getActivityStatus())))
		{
			return ResultDataThrow("该主讲人在时间段内已开展其他活动");
		}
		if(StringUtil.isNotBlank(activity.getActivityTypeId()))
		{
			activity.setActivityTypeName(ActivityTypeEnum.getNameById(activity.getActivityTypeId()));
		}
		activity.setOrgFlow(userinfo.getOrgFlow());
		activity.setRoleId(roleId);
		if (StringUtil.isNotBlank(recordFlow)){
			activity.setImageUrl(imageXML);
		}
		int c= activityBiz.addActivityInfo2(activity,userinfo,targets, null,recordFlow);
		if(c==0)
		{
			return ResultDataThrow("保存失败");
		}else if(c==-1){
			return ResultDataThrow("该角色无法发起教学活动，请联系基地管理员！");
		}else if(c==-2){
			String keyOfDay = "jsres_" + userinfo.getOrgFlow() + "_org_activity_add_day";
			String keyOfTime = "jsres_" + userinfo.getOrgFlow() + "_org_activity_add_time";
			String day = activityBiz.jsresPowerCfgMap(keyOfDay);
			String time = activityBiz.jsresPowerCfgMap(keyOfTime);
			return ResultDataThrow(  "请在活动开始前" + day + "天" + time + "点前设置活动!");
		}
		return resultMap;
	}
	private int checkTime(TeachingActivityInfo activity) {
		return activityBiz.checkTime(activity);
	}

	@RequestMapping(value={"/viewActivityImage"},method={RequestMethod.POST})
	@ResponseBody
	public Object viewActivityImage(String userFlow,String activityFlow,String roleId,String recordFlow,String imageXML) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}

		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			return ResultDataThrow( "用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow( "用户不存在");
		}
		if (StringUtil.isNotBlank(recordFlow) && StringUtil.isBlank(activityFlow)){
			if (StringUtil.isNotBlank(imageXML)){
				List<Map<String, String>> imageList=activityBiz.parseImageXml(imageXML);
				resultMap.put("imageList", imageList);
			}
		}else {
			if(StringUtil.isBlank(activityFlow)){
				return ResultDataThrow( "活动标识符为空");
			}
			Map<String, Object> activity=activityBiz.readActivity(activityFlow);
            if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
			{
				return ResultDataThrow( "活动信息不存在");
			}
			if(activity!=null)
			{
				String content= (String) activity.get("imageUrl");
				List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
				resultMap.put("imageList", imageList);
			}
			if(userFlow.equals(activity.get("speakerFlow")))
			{
                resultMap.put("canAdd", com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
		}
		return resultMap;
	}

	@RequestMapping(value={"/addActivityImage"},method={RequestMethod.POST})
	@ResponseBody
	public Object addActivityImage(ActivityImageFileForm form, HttpServletRequest request,HttpServletResponse response,String recordFlow,String imageXML) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isEmpty(form.getUserFlow())){
			return ResultDataThrow("用户标识符为空");
		}

		if(StringUtil.isEmpty(form.getFileName())){
			return ResultDataThrow("文件名为空");
		}
		if(form.getImageContent()==null && StringUtil.isBlank(form.getImageContent())){
			return ResultDataThrow("上传文件不能为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(form.getUserFlow());
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}

		/*Map<String, Object> activity=activityBiz.readActivity(form.getActivityFlow());
		if(activity==null||!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			return ResultDataThrow("活动信息不存在");
		}*/
		String imageInfo = jswjwBiz.addActivityImage3(form, userinfo, imageXML, recordFlow);
		resultMap.put("imageXML",imageInfo);
		return resultMap;
	}

	@RequestMapping(value={"/deleteActivityImage"},method={RequestMethod.POST})
	@ResponseBody
	public Object deleteActivityImage(String userFlow,String activityFlow,String imageFlow, HttpServletRequest request,String recordFlow,String imageXML) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isNotBlank(recordFlow) && StringUtil.isNotBlank(imageXML)){
			Document document = DocumentHelper.parseText(imageXML);
			Element elem = document.getRootElement();
			Node delNode = elem.selectSingleNode("image[@imageFlow='" + imageFlow + "']");
			if (delNode != null) {
				delNode.detach();
				imageXML=document.asXML();
			}
			resultMap.put("imageXML",imageXML);
		}else {
			if(StringUtil.isEmpty(userFlow)){
				return ResultDataThrow("用户标识符为空");
			}
			if(StringUtil.isEmpty(activityFlow)){
				return ResultDataThrow("活动标识符为空");
			}
			if(StringUtil.isEmpty(imageFlow)){
				return ResultDataThrow("图片标识符为空");
			}
			//验证用户是否存在
			SysUser userinfo = jswjwBiz.readSysUser(userFlow);
			if (userinfo == null) {
				return ResultDataThrow("用户不存在");
			}
			Map<String, Object> activity=activityBiz.readActivity(activityFlow);
            if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
			{
				return ResultDataThrow("活动信息不存在");
			}
			jswjwBiz.deleteActivityImage(userinfo,activityFlow,imageFlow);
		}
		return resultMap;
	}

	/**
	 * @Author shengl
	 * @Description // 请假审批列表
	 * @Date  2021-01-12
	 **/
	@RequestMapping(value={"/leaveVerifyList"},method={RequestMethod.POST})
	@ResponseBody
	public Object leaveVerifyList(String roleFlag, ResDoctorKq kq, Integer pageIndex, Integer pageSize, String userFlow,String agreeFlag){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isEmpty(roleFlag)){
			return ResultDataThrow("角色标识符为空");
		}
		if(StringUtil.isEmpty(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		SysUser currUser = jswjwBiz.readSysUser(userFlow);
		if(null == currUser){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("起始页为空");
		}
		if(pageSize==null){
			return ResultDataThrow("页面大小为空");
		}
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("doctorName",kq.getDoctorName());
		paramMap.put("typeId",kq.getTypeId());
		paramMap.put("startDate",kq.getStartDate());
		paramMap.put("endDate",kq.getEndDate());
		paramMap.put("roleFlag",roleFlag);
		paramMap.put("agreeFlag",agreeFlag);//已审核的记录
		String auditStatusId = kq.getAuditStatusId();
		if(StringUtil.isNotBlank(auditStatusId)) {
			List<String> auditStatusIds = Arrays.asList(auditStatusId.split(","));
			List<String> auditStatusIdList = new ArrayList<String>(new HashSet<String>(auditStatusIds));
			paramMap.put("auditStatusIdList", auditStatusIdList);
		}
		boolean auditRoleFlag = ResDoctorKqStatusEnum.Auditing.getId().equals(auditStatusId);
		if("Teacher".equals(roleFlag)){//带教
			paramMap.put("teacherFlow",userFlow);
			if(auditRoleFlag){
				//待带教审核
//				paramMap.put("auditStatusId",ResDoctorKqStatusEnum.Auditing.getId());
			}
		}
		if("Head".equals(roleFlag)){
			paramMap.put("headFlow",userFlow);
			if(auditRoleFlag){
				//待科主任审核
//				paramMap.put("auditStatusId",ResDoctorKqStatusEnum.HeadAuditing.getId());
			}
		}
		if("Admin".equals(roleFlag)){
			paramMap.put("orgFlow",currUser.getOrgFlow());
			paramMap.put("managerFlow",currUser.getUserFlow());
			if(auditRoleFlag){
				//待管理员审核
//				paramMap.put("auditStatusId",ResDoctorKqStatusEnum.ManagerAuditing.getId());
			}
		}
		List<String> notStatus=new ArrayList<>();
		notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
		notStatus.add(ResDoctorKqStatusEnum.BackLeave.getId());
		paramMap.put("notStatus",notStatus);
		PageHelper.startPage(pageIndex,pageSize);
		List<ResDoctorKq> kqList = ieaveAppBiz.searchAuditResDoctorKq(paramMap);
		resultMap.put("kqList", kqList);
		resultMap.put("dataCount", PageHelper.total);
		resultMap.put("roleFlag", roleFlag);

		int auditing = 0;
		int audited = 0;
		List<Map<String,Object>> countMap = ieaveAppBiz.queryResDoctorKqCountNew(paramMap);
		if (countMap != null && countMap.size() >0) {
			for (Map<String,Object> map : countMap) {
				String type = (String)map.get("TYPE");
				int scount = Integer.parseInt(map.get("SCOUNT")+"");
				if("audited".equals(type)){
					audited = scount;
				} else if("auditing".equals(type)){
					auditing = scount;
				}
			}
		}
		resultMap.put("audited", audited);
		resultMap.put("auditing", auditing);
		return resultMap;
	}

	/**
	 * @Author shengl
	 * @Description // 请假审核
	 * @Date  2021-01-13
	 **/
	@RequestMapping(value="/saveIeaveInfo",method = {RequestMethod.POST})
	@ResponseBody
	public Object saveIeaveInfo(String recordFlow,String auditInfo,String status,String roleFlag,String userFlow) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "审核成功");
		if(StringUtil.isEmpty(roleFlag)){
			return ResultDataThrow("角色标识符为空");
		}
		if(StringUtil.isEmpty(recordFlow)){
			return ResultDataThrow("数据标识符为空");
		}
		if(StringUtil.isEmpty(status)){
			return ResultDataThrow("状态标识符为空");
		}
		if(StringUtil.isEmpty(roleFlag)){
			return ResultDataThrow("角色标识符为空");
		}
		if(StringUtil.isEmpty(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(null == user){
			return ResultDataThrow("用户不存在");
		}
		boolean isTeacher = "Teacher".equals(roleFlag);
		boolean isHead = "Head".equals(roleFlag);
		boolean isManager = "Admin".equals(roleFlag);

		ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
		if (kq == null) {
			return ResultDataThrow("数据标识符错误");
		}
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sdf0.format(new Date());
		if (isTeacher) {
			if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag())) {
				return ResultDataThrow("此请假信息已审核，请刷新列表页");
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(status)) {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.HeadAuditing.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.HeadAuditing.getName());
				if ("-".equals(kq.getHeadName())) {
					if ("-".equals(kq.getManagerName())) {
						//不需要科主任及管理员审核，带教审核结果为最终审核结果
						kq.setAuditRoleNow("Pass");
						kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
					}else {
						//待科主任审核
                        kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN);
					}
				}else {
                    kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD);
					// 判断带教和科主任是否同一人审核，如果审批通过依次通过
					String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
					if (teacherFlow.equals(kq.getHeadFlow())) {
						kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerAuditing.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerAuditing.getName());
						if ("-".equals(kq.getManagerName())) {
							kq.setAuditRoleNow("Pass");
							kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
							kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
						} else {
                            kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN);
						}
						kq.setHeadAuditTime(time);
						kq.setHeadAgreeFlag(status);
					}
				}
			} else {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
				kq.setAuditRoleNow("UnPass");
			}
			kq.setTeacherAuditTime(time);
			kq.setTeacherAgreeFlag(status);
			kq.setTeacherAuditInfo(auditInfo);

		} else if (isHead) {
			if (StringUtil.isNotBlank(kq.getHeadAgreeFlag())) {
				return ResultDataThrow("此请假信息已审核，请刷新列表页");
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(status)) {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerAuditing.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerAuditing.getName());
				if ("-".equals(kq.getManagerName())) {
					kq.setAuditRoleNow("Pass");
					kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
					kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
				}else {
                    kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN);
				}
			} else {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
				kq.setAuditRoleNow("UnPass");
			}
			kq.setHeadAuditTime(time);
			kq.setHeadAgreeFlag(status);
			kq.setHeadAuditInfo(auditInfo);

		}else if (isManager) {
			if (StringUtil.isNotBlank(kq.getManagerAgreeFlag())) {
				return ResultDataThrow("此请假信息已审核，请刷新列表页");
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(status)) {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
				kq.setAuditRoleNow("Pass");
			} else {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
				kq.setAuditRoleNow("UnPass");
			}
			kq.setManagerAuditTime(time);
			kq.setManagerAgreeFlag(status);
			kq.setManagerAuditInfo(auditInfo);

		} else {
			return ResultDataThrow("该数据无法审核！");
		}
		int count = ieaveAppBiz.editResDoctorKqNew(kq,user);
		if(count != 0) {
			//保存审批记录
			ResDoctorKqLog kqLog = new ResDoctorKqLog();
			kqLog.setKqRecordFlow(recordFlow);

			if(isTeacher){
				kqLog.setRoleId("teacher");
				kqLog.setRoleName("带教");
				kqLog.setAuditUserFlow(kq.getTeacherFlow());
				kqLog.setAuditUserName(kq.getTeacherName());
                if (kq.getTeacherAgreeFlag().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.TeacherPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.TeacherPass.getName());
					kqLog.setAuditRemake(kq.getTeacherAuditInfo());
				}else{
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.TeacherUnPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.TeacherUnPass.getName());
					kqLog.setAuditRemake(kq.getTeacherAuditInfo());
				}
				kqLog.setAuditTime(time);
				kqLog.setTypeId("Leave");
				kqLog.setTypeName("请假申请");
				ieaveAppBiz.saveKqLog(kqLog,user);
				// 判断带教和科主任是否同一人审核，如果审批通过依次通过，记录审批
				String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
				if (teacherFlow.equals(kq.getHeadFlow())) {
					kqLog.setRecordFlow(null);
					kqLog.setRoleId("teacher");
					kqLog.setRoleName("带教");
					kqLog.setAuditUserFlow(kq.getTeacherFlow());
					kqLog.setAuditUserName(kq.getTeacherName());
                    if (kq.getTeacherAgreeFlag().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
						kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
						kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadPass.getName());
						kqLog.setAuditRemake(kq.getTeacherAuditInfo());
					}else{
						kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadUnPass.getId());
						kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadUnPass.getName());
						kqLog.setAuditRemake(kq.getTeacherAuditInfo());
					}
					ieaveAppBiz.saveKqLog(kqLog,user);
				}
			}

			if(isHead) {
				kqLog.setRoleId("head");
				kqLog.setRoleName("科主任");
				kqLog.setAuditUserFlow(kq.getHeadFlow());
				kqLog.setAuditUserName(kq.getHeadName());
                if (kq.getHeadAgreeFlag().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadPass.getName());
					kqLog.setAuditRemake(kq.getHeadAuditInfo());
				} else {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadUnPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadUnPass.getName());
					kqLog.setAuditRemake(kq.getHeadAuditInfo());
				}
				kqLog.setAuditTime(time);
				kqLog.setTypeId("Leave");
				kqLog.setTypeName("请假申请");
				ieaveAppBiz.saveKqLog(kqLog,user);
			}

			if(isManager) {
				kqLog.setRoleId("manager");
				kqLog.setRoleName("管理员");
				kqLog.setAuditUserFlow(kq.getManagerFlow());
				kqLog.setAuditUserName(kq.getManagerName());
                if (kq.getManagerAgreeFlag().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
					kqLog.setAuditRemake(kq.getManagerAuditInfo());
				} else {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
					kqLog.setAuditRemake(kq.getManagerAuditInfo());
				}
				kqLog.setAuditTime(time);
				kqLog.setTypeId("Leave");
				kqLog.setTypeName("请假申请");
				ieaveAppBiz.saveKqLog(kqLog,user);
			}
			return resultMap;
		}
		return ResultDataThrow("审核失败");
	}

	/**
	 * @Description // 销假审核
	 **/
	@RequestMapping(value="/saveCancelLeaveInfo")
	@ResponseBody
	public Object saveCancelLeaveInfo(String recordFlow,String auditInfo,String status,String roleFlag,String userFlow) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空！");
		}
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if (null == user) {
			return ResultDataThrow("用户不存在！");
		}
		if (StringUtil.isBlank(recordFlow)) {
			return ResultDataThrow("数据标识符为空！");
		}
		if (StringUtil.isBlank(status)) {
			return ResultDataThrow("状态标识符为空！");
		}
		if (StringUtil.isBlank(roleFlag)) {
			return ResultDataThrow("角色标识符为空！");
		}

		boolean isTeacher = "Teacher".equals(roleFlag);
		boolean isHead = "Head".equals(roleFlag);
		boolean isManager = "Admin".equals(roleFlag);

		ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
		if (kq == null) {
			return ResultDataThrow("数据标识符错误");
		}
		SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sdf0.format(new Date());
		if (isTeacher) {
			if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag())) {
				return ResultDataThrow("此销假信息已审核，请刷新列表页");
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(status)) {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadAuditing.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadAuditing.getName());
				if ("-".equals(kq.getHeadName())) {
					if ("-".equals(kq.getManagerName())) {
						//不需要科主任及管理员审核，带教审核结果为最终审核结果
						kq.setAuditRoleNow("Pass");
						kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
					}else {
						//待科主任审核
                        kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN);
					}
				}else {
                    kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD);
					// 判断带教和科主任是否同一人审核，如果审批通过依次通过
					String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
					if (teacherFlow.equals(kq.getHeadFlow())) {
						kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerAuditing.getId());
						kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerAuditing.getName());
						if ("-".equals(kq.getManagerName())) {
							kq.setAuditRoleNow("Pass");
							kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
							kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
						} else {
                            kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN);
						}
						kq.setHeadAuditTime(time);
						kq.setHeadAgreeFlag(status);
					}
				}
			} else {
				kq.setAuditRoleNow("Pass");
			}
			kq.setTeacherAuditTime(time);
			kq.setTeacherAgreeFlag(status);
			kq.setTeacherAuditInfo(auditInfo);

		} else if (isHead) {
			if (StringUtil.isNotBlank(kq.getHeadAgreeFlag())) {
				return ResultDataThrow("此销假信息已审核，请刷新列表页");
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(status)) {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerAuditing.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerAuditing.getName());
				if ("-".equals(kq.getManagerName())) {
					kq.setAuditRoleNow("Pass");
					kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
					kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
				}else {
                    kq.setAuditRoleNow(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN);
				}
			} else {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerUnPass.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerUnPass.getName());
				kq.setAuditRoleNow("UnPass");
			}
			kq.setHeadAuditTime(time);
			kq.setHeadAgreeFlag(status);
			kq.setHeadAuditInfo(auditInfo);

		}else if (isManager) {
			if (StringUtil.isNotBlank(kq.getManagerAgreeFlag())) {
				return ResultDataThrow("此请假信息已审核，请刷新列表页");
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(status)) {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
				kq.setAuditRoleNow("Pass");
			} else {
				kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerUnPass.getId());
				kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerUnPass.getName());
				kq.setAuditRoleNow("UnPass");
			}
			kq.setManagerAuditTime(time);
			kq.setManagerAgreeFlag(status);
			kq.setManagerAuditInfo(auditInfo);

		} else {
			return ResultDataThrow("该数据无法审核！");
		}
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(status)) {
			// 销假审核不通过，状态变更为请假审核通过
			kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
			kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
			// 根据考勤主键与审核类型查询
			List<ResDoctorKqLog> kqLogs = ieaveAppBiz.searchKqLogList(recordFlow, "Leave");
			if (null != kqLogs && kqLogs.size() > 0) {
				for (ResDoctorKqLog log : kqLogs) {
					if (log.getRoleId().equals("manager")) {
						kq.setManagerAuditTime(log.getAuditTime());
						kq.setManagerAuditInfo(log.getAuditRemake());
					}
					if (log.getRoleId().equals("head")) {
						kq.setHeadAuditTime(log.getAuditTime());
						kq.setHeadAuditInfo(log.getAuditRemake());
					}
					if (log.getRoleId().equals("teacher")) {
						kq.setTeacherAuditTime(log.getAuditTime());
						kq.setTeacherAuditInfo(log.getAuditRemake());
					}
				}
			}
		}
        kq.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		kq.setModifyTime(DateUtil.getCurrDateTime());
		kq.setModifyUserFlow(user.getUserFlow());
		int count = ieaveAppBiz.updateResDoctorKq(kq);
		if(count != 0) {
			//保存审批记录
			ResDoctorKqLog kqLog = new ResDoctorKqLog();
			kqLog.setKqRecordFlow(recordFlow);

			if(isTeacher){
				kqLog.setRoleId("teacher");
				kqLog.setRoleName("带教");
				kqLog.setAuditUserFlow(kq.getTeacherFlow());
				kqLog.setAuditUserName(kq.getTeacherName());
                if (kq.getTeacherAgreeFlag().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeTeacherPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeTeacherPass.getName());
					kqLog.setAuditRemake(auditInfo);
				}else{
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeTeacherUnPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeTeacherUnPass.getName());
					kqLog.setAuditRemake(auditInfo);
				}
				kqLog.setAuditTime(time);
				kqLog.setTypeId("Cancel");
				kqLog.setTypeName("销假申请");
				ieaveAppBiz.saveKqLog(kqLog,user);
				// 判断带教和科主任是否同一人审核，如果审批通过依次通过，记录审批
				String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
				if (teacherFlow.equals(kq.getHeadFlow())) {
					kqLog.setRecordFlow(null);
					kqLog.setRoleId("teacher");
					kqLog.setRoleName("带教");
					kqLog.setAuditUserFlow(kq.getTeacherFlow());
					kqLog.setAuditUserName(kq.getTeacherName());
                    if (kq.getTeacherAgreeFlag().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
						kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadPass.getId());
						kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadPass.getName());
						kqLog.setAuditRemake(auditInfo);
					}else{
						kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadUnPass.getId());
						kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadUnPass.getName());
						kqLog.setAuditRemake(auditInfo);
					}
					ieaveAppBiz.saveKqLog(kqLog,user);
				}
			}

			if(isHead) {
				kqLog.setRoleId("head");
				kqLog.setRoleName("科主任");
				kqLog.setAuditUserFlow(kq.getHeadFlow());
				kqLog.setAuditUserName(kq.getHeadName());
                if (kq.getHeadAgreeFlag().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadPass.getName());
					kqLog.setAuditRemake(auditInfo);
				} else {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadUnPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadUnPass.getName());
					kqLog.setAuditRemake(auditInfo);
				}
				kqLog.setAuditTime(time);
				kqLog.setTypeId("Cancel");
				kqLog.setTypeName("销假申请");
				ieaveAppBiz.saveKqLog(kqLog,user);
			}

			if(isManager) {
				kqLog.setRoleId("manager");
				kqLog.setRoleName("管理员");
				kqLog.setAuditUserFlow(kq.getManagerFlow());
				kqLog.setAuditUserName(kq.getManagerName());
                if (kq.getManagerAgreeFlag().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
					kqLog.setAuditRemake(auditInfo);
				} else {
					kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerUnPass.getId());
					kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerUnPass.getName());
					kqLog.setAuditRemake(auditInfo);
				}
				kqLog.setAuditTime(time);
				kqLog.setTypeId("Cancel");
				kqLog.setTypeName("销假申请");
				ieaveAppBiz.saveKqLog(kqLog,user);
			}
		}
		return resultMap;
	}

	/**
	 * 临时出科查询
	 */
	@RequestMapping(value="/temporaryOutSearch")
	@ResponseBody
	public Object temporaryOutSearch(String userFlow,String roleId,String seachStr,
									 String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,
									 Integer pageIndex,Integer pageSize, String temporaryAuditStatusId) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			resultMap.put("resultId", "3011101");
			resultMap.put("resultType", "用户标识符为空");
			return resultMap;
		}
		if(StringUtil.isBlank(roleId)){
			resultMap.put("resultId", "3011101");
			resultMap.put("resultType", "用户角色ID为空");
			return resultMap;
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			resultMap.put("resultId", "3011101");
			resultMap.put("resultType", "用户角色ID与角色不符");
			return resultMap;
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			resultMap.put("resultId", "3010103");
			resultMap.put("resultType", "用户不存在");
			return resultMap;
		}
		if(pageIndex==null){
			resultMap.put("resultId", "30905");
			resultMap.put("resultType", "当前页码为空");
			return resultMap;
		}
		if(pageSize==null){
			resultMap.put("resultId", "30906");
			resultMap.put("resultType", "每页条数为空");
			return resultMap;
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		schArrangeResultMap.put("temporaryAuditStatusId", temporaryAuditStatusId);
		schArrangeResultMap.put("roleId", roleId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.temporaryOutList(schArrangeResultMap);

		resultMap.put("list",resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);
		return resultMap;
	}

	/**
	 * 临时出科审核查询
	 */
	@RequestMapping(value="/temporaryOutAuditSearch")
	@ResponseBody
	public Object temporaryOutAuditSearch(String userFlow,String roleId,String seachStr,
										  String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,
										  Integer pageIndex,Integer pageSize) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			resultMap.put("resultId", "3011101");
			resultMap.put("resultType", "用户标识符为空");
			return resultMap;
		}
		if(StringUtil.isBlank(roleId)){
			resultMap.put("resultId", "3011101");
			resultMap.put("resultType", "用户角色ID为空");
			return resultMap;
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")&&!roleId.equals("TeachingHead")&&!roleId.equals("TeachingSeretary")){
			resultMap.put("resultId", "3011101");
			resultMap.put("resultType", "用户角色ID与角色不符");
			return resultMap;
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			resultMap.put("resultId", "3010103");
			resultMap.put("resultType", "用户不存在");
			return resultMap;
		}
		if(pageIndex==null){
			resultMap.put("resultId", "30905");
			resultMap.put("resultType", "当前页码为空");
			return resultMap;
		}
		if(pageSize==null){
			resultMap.put("resultId", "30906");
			resultMap.put("resultType", "每页条数为空");
			return resultMap;
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.temporaryOutAuditList(schArrangeResultMap);

		resultMap.put("list",resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);
		return resultMap;
	}

	/**
	 * 临时出科审核
	 */
	@RequestMapping(value="/temporaryOutAudit")
	@ResponseBody
	public Object temporaryOutAudit(String processFlow, String auditStatusId,HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(processFlow)){
			resultMap.put("resultId", "3011101");
			resultMap.put("resultType", "ProcessFlow为空");
			return resultMap;
		}
		if(StringUtil.isBlank(auditStatusId)){
			resultMap.put("resultId", "3011101");
			resultMap.put("resultType", "审核状态为空");
			return resultMap;
		}
		int i = jswjwBiz.temporaryOutAudit(processFlow,auditStatusId);
		if(i>0){
			resultMap.put("resultId", "200");
			resultMap.put("resultType", "提交成功！");
		}else{
			resultMap.put("resultId", "200");
			resultMap.put("resultType", "提交失败！");
		}
		return resultMap;
	}



	@RequestMapping(value = {"/addActivityFile"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object addActivityFile(ImageFileForm form, HttpServletRequest request, HttpServletResponse response,String activityFlow) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(form.getUserFlow())) {
			return ResultDataThrow("用户标识符为空");
		}
		if (StringUtil.isNotBlank(activityFlow)){
			form.setRecordFlow(activityFlow);
		}
		if (StringUtil.isBlank(form.getImageContent())) {
			return ResultDataThrow("图片内容为空");
		}
		if (StringUtil.isBlank(form.getFileName())) {
			return ResultDataThrow("图片名称为空");
		}
		String baseDir = ieaveAppBiz.getCfgByCode("upload_base_dir");
		if (StringUtil.isBlank(baseDir)) {
			return ResultDataThrow("请联系管理员，设置上传图片路径！");
		}

		//定义上传路径
		String dateString = DateUtil.getCurrDate2();
		String newDir = baseDir + File.separator + "activityFile" + File.separator + dateString + File.separator + form.getRecordFlow();
		File fileDir = new File(newDir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		//重命名上传后的文件名
		String originalFilename = "";
		originalFilename = PkUtil.getUUID() + form.getFileName().substring(form.getFileName().lastIndexOf("."));
		try {
			generateImage(form.getImageContent(), fileDir + File.separator + originalFilename);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存文件失败！");
		}
		String filePath = File.separator + "activityFile" + File.separator + dateString + File.separator + form.getRecordFlow() + File.separator + originalFilename;

		PubFile pubFile = new PubFile();
		pubFile.setFileFlow(PkUtil.getUUID());
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		pubFile.setFilePath(filePath);
		pubFile.setFileName(form.getFileName());
		pubFile.setFileSuffix(form.getFileName().substring(form.getFileName().lastIndexOf(".")));
		pubFile.setProductType("activity");
		pubFile.setProductFlow(form.getRecordFlow());
		pubFileBiz.addFile(pubFile);
		resultMap.put("pubFile", pubFile);
		String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		return resultMap;
	}

	@RequestMapping(value = {"/addActivitykJ"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object addActivitykJ(ImageFileForm form, HttpServletRequest request, HttpServletResponse response,String activityFlow) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isNotBlank(activityFlow)) {
			form.setRecordFlow(activityFlow);
		}
		if (StringUtil.isBlank(form.getUserFlow())) {
			return ResultDataThrow("用户标识符为空");
		}
		if (StringUtil.isBlank(form.getImageContent())) {
			return ResultDataThrow("图片内容为空");
		}
		if (StringUtil.isBlank(form.getFileName())) {
			return ResultDataThrow("图片名称为空");
		}
		String baseDir = ieaveAppBiz.getCfgByCode("upload_base_dir");
		if (StringUtil.isBlank(baseDir)) {
			return ResultDataThrow("请联系管理员，设置上传图片路径！");
		}

		//定义上传路径
		String dateString = DateUtil.getCurrDate2();
		String newDir = baseDir + File.separator + "activityFile" + File.separator + dateString + File.separator + form.getRecordFlow();
		File fileDir = new File(newDir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		//重命名上传后的文件名
		String originalFilename = "";
		originalFilename = PkUtil.getUUID() + form.getFileName().substring(form.getFileName().lastIndexOf("."));
		try {
			generateImage(form.getImageContent(), fileDir + File.separator + originalFilename);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存文件失败！");
		}
		String filePath = File.separator + "activityFile" + File.separator + dateString + File.separator + form.getRecordFlow() + File.separator + originalFilename;

		PubFile pubFile = new PubFile();
		pubFile.setFileFlow(PkUtil.getUUID());
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		pubFile.setFilePath(filePath);
		pubFile.setFileName(form.getFileName());
		pubFile.setFileSuffix(form.getFileName().substring(form.getFileName().lastIndexOf(".")));
		pubFile.setProductType("activity");
		pubFile.setProductFlow(form.getRecordFlow());
		pubFile.setFileUpType("kj");
		pubFileBiz.addFile(pubFile);
		resultMap.put("pubFile", pubFile);
		String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		return resultMap;
	}

	@RequestMapping(value = {"/addActivityzp"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object addActivityzp(ImageFileForm form, HttpServletRequest request, HttpServletResponse response,String activityFlow) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(form.getUserFlow())) {
			return ResultDataThrow("用户标识符为空");
		}
		if (StringUtil.isBlank(form.getRecordFlow())) {
			return ResultDataThrow("数据标识符为空");
		}
		if (StringUtil.isBlank(form.getImageContent())) {
			return ResultDataThrow("图片内容为空");
		}
		if (StringUtil.isBlank(form.getFileName())) {
			return ResultDataThrow("图片名称为空");
		}
		String baseDir = ieaveAppBiz.getCfgByCode("upload_base_dir");
		if (StringUtil.isBlank(baseDir)) {
			return ResultDataThrow("请联系管理员，设置上传图片路径！");
		}

		//定义上传路径
		String dateString = DateUtil.getCurrDate2();
		String newDir = baseDir + File.separator + "activityFile" + File.separator + dateString + File.separator + form.getRecordFlow();
		File fileDir = new File(newDir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		//重命名上传后的文件名
		String originalFilename = "";
		originalFilename = PkUtil.getUUID() + form.getFileName().substring(form.getFileName().lastIndexOf("."));
		try {
			generateImage(form.getImageContent(), fileDir + File.separator + originalFilename);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("保存文件失败！");
		}
		String filePath = File.separator + "activityFile" + File.separator + dateString + File.separator + form.getRecordFlow() + File.separator + originalFilename;

		PubFile pubFile = new PubFile();
		pubFile.setFileFlow(PkUtil.getUUID());
		pubFile.setProductFlow(activityFlow);
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		pubFile.setFilePath(filePath);
		pubFile.setFileName(form.getFileName());
		pubFile.setFileSuffix(form.getFileName().substring(form.getFileName().lastIndexOf(".")));
		pubFile.setProductType("activity");
		pubFile.setProductFlow(form.getRecordFlow());
		pubFile.setFileUpType("zp");
		pubFileBiz.addFile(pubFile);
		resultMap.put("pubFile", pubFile);
		String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		return resultMap;
	}


	@RequestMapping(value = {"/deleteActivityFile"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object deleteImage(String fileFlow) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "删除成功");
		if (StringUtil.isEmpty(fileFlow)) {
			return ResultDataThrow("附件标识符为空");
		}
		jswjwBiz.deleteLeaveImage(fileFlow);
		return resultMap;
	}


	//base64字符串转化成图片
	public static boolean generateImage(String imgStr, String savePath) {
		//对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) //图像数据为空
		{
			return false;
		}

		String newDir = savePath.substring(0, savePath.lastIndexOf(File.separator));
		File fileDir = new File(newDir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			//Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据
					b[i] += 256;
				}
			}
			//生成jpeg图片//新生成的图片
			File imageFile = new File(savePath);
			OutputStream out = new FileOutputStream(imageFile);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}

