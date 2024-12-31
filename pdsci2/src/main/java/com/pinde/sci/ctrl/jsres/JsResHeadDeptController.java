package com.pinde.sci.ctrl.jsres;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinde.core.common.enums.ResAssessTypeEnum;
import com.pinde.core.common.sci.dao.JsresPowerCfgMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jsres.IDeptBasicInfoBiz;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchAndStandardDeptCfgBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.FileUtil;
import com.pinde.sci.form.jsres.BaseSpeDept.BaseSpeDeptExtForm;
import com.pinde.sci.form.jsres.BaseSpeDept.BaseSpeDeptForm;
import com.pinde.core.model.ResAssessCfgItemForm;
import com.pinde.core.model.ResAssessCfgTitleForm;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorSchProcess;
import com.pinde.core.model.ResInprocessInfo;
import com.pinde.core.model.ResInprocessInfoMember;
import com.pinde.core.model.ResRec;
import com.pinde.core.model.ResScore;
import com.pinde.core.model.ResTeacherTraining;
import com.pinde.core.model.SchAndStandardDeptCfg;
import com.pinde.core.model.SchArrangeResult;
import com.pinde.core.model.SchRotationDept;
import com.pinde.core.model.SysUserRole;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/jsres/kzr")
public class JsResHeadDeptController extends GeneralController{
	final static String Jxcf = "1";
	final static String Ynbltl = "2"; final static String Wzbltl = "3";final static String Swbltl = "5";final static String Jxbltl = "11";
	final static String Xsjz = "4";  final static String Rkjy = "6";
	final static String Ckks = "7"; final static String Jnpx = "8"; final static String Yph = "9";
	final static String Jxhz = "10";
	final static String Lcczjnzd = "12"; final static String Lcblsxzd = "13";
	final static String Ssczzd = "14"; final static String Yxzdbgsxzd = "15"; final static String Lcwxyd = "16";
	final static String Ryjy = "17"; final static String Rzyjdjy = "18"; final static String Cjbg = "19";
	final static String Bgdfx = "20";final static String Jxsj = "21";final static String Sjys = "22";
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IUserBiz iUserBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private ISchRotationDeptBiz iSchRotationDeptBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResInprocessInfoBiz resInprocessInfoBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IOrgBiz orgBiz;
    @Autowired
    private JsresPowerCfgMapper jsresPowerCfgMapper;
    @Autowired
	private IDeptBasicInfoBiz deptBasicInfoBiz;
    @Autowired
	private ISchAndStandardDeptCfgBiz schAndStandardDeptCfgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IUserBiz userBiz;

    private static Logger logger = LoggerFactory.getLogger(JsResHeadDeptController.class);

	/**
	 * 科主任主界面
	 */
	@RequestMapping(value="/index")
	public String index(Model model,Integer currentPage,HttpServletRequest request){
		InxInfo info = new InxInfo();
		SysUser sysUser=GlobalContext.getCurrentUser();
		model.addAttribute("deptFlow",sysUser.getDeptFlow());
/*		String deptFlow=sysUser.getDeptFlow();
		deptFlow=StringUtil.defaultIfEmpty(deptFlow, "");
		int currStudentHe=resDoctorProcessBiz.schProcessStudentDistinctQuery(deptFlow,sysUser.getUserFlow());
		int studentNum=resDoctorProcessBiz.schProcessStudentQuery(deptFlow,sysUser.getUserFlow());
		int ComingStudentNum=resDoctorProcessBiz.schProcessComingStudentQuery(deptFlow,sysUser.getUserFlow());*/
		PageHelper.startPage(currentPage,getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
/*		model.addAttribute("ComingStudentNum",ComingStudentNum);
		model.addAttribute("currStudentHe",currStudentHe);
		model.addAttribute("studentNum",studentNum);*/
		model.addAttribute("infos",infos);
		String orgFlow = sysUser.getOrgFlow();
		JsresPowerCfg read = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_guocheng");
		if(null != read){
			model.addAttribute("cfgValue",read.getCfgValue());
		}else{
            model.addAttribute("cfgValue", com.pinde.core.common.GlobalConstant.FLAG_N);
		}
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE, com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD);
		return "jsres/kzr/index";
	}

	@RequestMapping(value="/indexInfo")
	@ResponseBody
	public Map<String,Object> indexInfo(){
		HashMap<String, Object> map = new HashMap<>();
		SysUser sysUser=GlobalContext.getCurrentUser();
		String deptFlow=sysUser.getDeptFlow();
		deptFlow=StringUtil.defaultIfEmpty(deptFlow, "");
		int currStudentHe=resDoctorProcessBiz.schProcessStudentDistinctQuery(deptFlow,sysUser.getUserFlow());
		int studentNum=resDoctorProcessBiz.schProcessStudentQuery(deptFlow,sysUser.getUserFlow());
		int ComingStudentNum=resDoctorProcessBiz.schProcessComingStudentQuery(deptFlow,sysUser.getUserFlow());
		map.put("currStudentHe",currStudentHe);
		map.put("studentNum",studentNum);
		map.put("ComingStudentNum",ComingStudentNum);
		return map;
	}
	/**
	 * 评价查询
	 */
	@RequestMapping(value="/evaluateSearch")
	public String evaluateSearch(Model model,String roleType,String doctorName,String startDate,String endDate,String deptFlow) throws Exception{
		SysUser sysUser=GlobalContext.getCurrentUser();
		String cfgTeacher=InitConfig.getSysCfg("res_teacher_role_flow");
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
			List<SysUser> sysUserList=iUserBiz.teacherRoleCheckUser(sysUser.getDeptFlow(),cfgTeacher,doctorName,sysUser.getUserFlow());
			Map<String, Integer> studentNumMap=new HashMap<String, Integer>();
			Map<String, String> averageMap=new HashMap<String, String>();
			for (SysUser user : sysUserList) {
				String currKey="curr"+user.getUserFlow();
				String studentKey="student"+user.getUserFlow();
				int currStudentHe=resDoctorProcessBiz.jsresSchDoctorSchProcessDistinctQuery(user.getUserFlow());
				int studentNum=resDoctorProcessBiz.jsresSchDoctorSchProcessTeacherQuery(user.getUserFlow());
				studentNumMap.put(currKey, currStudentHe);
				studentNumMap.put(studentKey, studentNum);
				List<DeptTeacherGradeInfo> gradeInfoList=resGradeBiz.searchProssFlowRec(user.getUserFlow(),startDate,endDate, null);
				Float sum=0f;
				Float average=0f;
				if (gradeInfoList!=null && gradeInfoList.size()>0) {
					for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
						Map<String,Object> gradeMap = resRecBiz.parseGradeXml(gradeInfo.getRecContent());
						Float zongFen= Float.parseFloat(gradeMap.get("totalScore").toString());
						sum=sum+zongFen;
					}
					average=sum/gradeInfoList.size();
				}
				BigDecimal bd=new BigDecimal(average).setScale(1, RoundingMode.UP);
				averageMap.put(user.getUserFlow(), bd.toString());
			}
			final Map<String, String> averageMapTemp = averageMap;
			Collections.sort(sysUserList,new Comparator<SysUser>() {
				@Override
				public int compare(SysUser o1, SysUser o2) {
					String	o1Key=o1.getUserFlow();
					String	o2Key=o2.getUserFlow();
					String s1=averageMapTemp.get(o1Key);
					String s2=averageMapTemp.get(o2Key);
					Float f1=0f;
					Float f2=0f;
					try {
						if (s1 != null && s2 != null) {
							f1 = Float.valueOf(s1);
							f2 = Float.valueOf(s2);
						}
					} catch (Exception e) {
                        logger.error("", e);
					}
					Float result=f2-f1;
					return result>0?1:result==0?0:-1;
				}
			});
			model.addAttribute("averageMap", averageMap);
			model.addAttribute("studentNumMap", studentNumMap);
			model.addAttribute("sysUserList", sysUserList);
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {
			model.addAttribute("sysUser",sysUser);
			//查询出当前用户所在所有科室
			List<SysUserDept> deptList = resGradeBiz.searDeptNames(sysUser.getUserFlow());
			model.addAttribute("deptList",deptList);
			if(StringUtil.isEmpty(deptFlow)){
				deptFlow = sysUser.getDeptFlow();
			}
			List<DeptTeacherGradeInfo> gradeInfoList=resGradeBiz.searchDeptFlowRec(deptFlow,startDate,endDate);
			Float sum=0f;
			Map<String, Float> heJiMap=new HashMap<String, Float>();
			if (gradeInfoList!=null && gradeInfoList.size()>0) {
				for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
					Map<String,Object> gradeMap = resRecBiz.parseGradeXml(gradeInfo.getRecContent());
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
						 Float deValue= 0f;
						 try {
							 if(StringUtil.isNotBlank(scoreMap.get("score"))){
								 deValue= Float.parseFloat(scoreMap.get("score"));
							 }
						 } catch (NumberFormatException e) {
                             logger.error("", e);
						 }
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
			String cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
			ResAssessCfg search = new ResAssessCfg();
			search.setCfgCodeId(cfgCodeId);
//			search.setOrgFlow(currUser.getOrgFlow());
			List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
			if(assessCfgList != null && !assessCfgList.isEmpty()){
				ResAssessCfg assessCfg = assessCfgList.get(0);
				model.addAttribute("assessCfg",assessCfg);
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
					for(Element te :titleElementList){
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for(Element ie : itemElementList){
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
			model.addAttribute("heJiMap", heJiMap);
			BigDecimal ii=new BigDecimal(sum).setScale(1, BigDecimal.ROUND_HALF_UP);
			sum=ii.floatValue();
			model.addAttribute("average", sum);
		}
		return "jsres/kzr/evaluateSearch";
	}

	/**
	 * 查看详情
	 */
	@RequestMapping(value="/checkDetails")
	public String checkDetails(Model model,String roleType,String userFlow,String startDate,String endDate,String deptFlow) throws Exception{
			SysUser sysUser=GlobalContext.getCurrentUser();
			model.addAttribute("sysUser",sysUser);
			Map<String, String> map=new HashMap<String, String>();
			Map<String, Object> heJiMap=new HashMap<String, Object>();
			List<Map<String, String>> processRecList=null;
			Map<String,Map<String, Object>> gradeMaps=new HashMap<String, Map<String,Object>>();
			if (StringUtil.isNotBlank(startDate)) {
				startDate = DateUtil.getDate(startDate)+"000000";
				map.put("startDate",startDate);
			}
			if (StringUtil.isNotBlank(endDate)) {
				endDate = DateUtil.getDate(endDate)+"235959";
				map.put("endDate",endDate);
			}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
				 map.put("teacherFlow",userFlow);
				 processRecList=resGradeBiz.processJsresRecRecTeacherMap(map);
			}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {
				if(StringUtil.isEmpty(deptFlow)){
					deptFlow=sysUser.getDeptFlow();
				}
				map.put("deptFlow", deptFlow);
				processRecList=resGradeBiz.processJsresRecShMap(map);
			}
			for (Map<String, String> recMap : processRecList) {
				Map<String,Object> gradeMap = resRecBiz.parseGradeXml(recMap.get("content"));
				gradeMaps.put(recMap.get("processFlow"), gradeMap);
				heJiMap.put(recMap.get("processFlow"),gradeMap.get("totalScore"));
			}

			final Map<String, Object> heJiMapTemp = heJiMap;
			Collections.sort(processRecList,new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> o1,Map<String, String> o2) {
					String o1Key=o1.get("processFlow");
					String o2Key=o2.get("processFlow");
					Object s1=heJiMapTemp.get(o1Key);
					Object s2=heJiMapTemp.get(o2Key);
					Float f1=0f;
					Float f2=0f;
					try {
						f1=Float.valueOf(s1.toString());
						f2=Float.valueOf(s2.toString());
					} catch (Exception e) {
                        logger.error("", e);
					}
					Float result=f2-f1;
					return result>0?1:result==0?0:-1;
				}
			});
			model.addAttribute("processRecList", processRecList);
			List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
			String cfgCodeId =null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
				cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {
				cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
			}
			ResAssessCfg search = new ResAssessCfg();
			search.setCfgCodeId(cfgCodeId);
			List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
			if(assessCfgList != null && !assessCfgList.isEmpty()){
				ResAssessCfg assessCfg = assessCfgList.get(0);
				model.addAttribute("assessCfg",assessCfg);
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					for(Element te :titleElementList){
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for(Element ie : itemElementList){
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
			Map<String,Object> scoreSumMap=new HashMap<String, Object>();
			for (Map<String, String> recMap : processRecList) {
				String operUserFlow = recMap.get("userFlow");
				int scoreSum=0;
				for (ResAssessCfgTitleForm resAssessCfgTitleForm : titleFormList) {
					if(null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size()>0) {
						for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
							int s = Integer.parseInt(resAssessCfgItemForm.getScore());
							scoreSum += s;
						}
					}
				}
				scoreSumMap.put(operUserFlow, scoreSum);
			}
		model.addAttribute("scoreSumMap", scoreSumMap);
		model.addAttribute("gradeMaps", gradeMaps);
		model.addAttribute("heJiMap", heJiMap);
		return "jsres/kzr/checkDetails";
	}
	/**
	 * 培训数据查询
	 */
	@RequestMapping(value="/recruitAuditSearch")
	public String recruitAuditSearch(Model model,Integer currentPage,HttpServletRequest request,SchArrangeResult arrangeResult, String datas[]){
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.jsresSchDoctorSchProcessHead(schArrangeResultMap);

		model.addAttribute("schArrangeResult", schArrangeResult);
		return "jsres/kzr/recruitAuditSearch";
	}



	@RequestMapping(value="/afterAuditSearch")
	public String afterAuditSearch(Model model,Integer currentPage,HttpServletRequest request,SchArrangeResult arrangeResult, String roleId, String biaoJi, String datas[]){
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		model.addAttribute("roleId", roleId);
		model.addAttribute("biaoJi", biaoJi);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
        schArrangeResultMap.put("isAfter", com.pinde.core.common.GlobalConstant.FLAG_Y);
		schArrangeResultMap.put("biaoJi", biaoJi);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.jsresSchDoctorSchProcessHead(schArrangeResultMap);
		Map<String,ResSchProcessExpress> expressMap=new HashMap<>();
		Map<String, Object> schRotationDeptMap=new HashMap<String, Object>();
		for(Map<String, String> result:schArrangeResult)
		{
			String processFlow=result.get("processFlow");
            List<ResSchProcessExpress> recs = expressBiz.searchByProcessFlowAndRecTypeIdClob(processFlow, com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
			if(recs!=null&&recs.size()>0)
			{
				expressMap.put(processFlow,recs.get(0));
			}
			SchArrangeResult result1=schArrangeResultBiz.readSchArrangeResult(result.get("schResultFlow"));
			if (result1!=null) {
				SchRotationDept schRotationDept=iSchRotationDeptBiz.searchGroupFlowAndStandardDeptIdQuery(result1.getStandardGroupFlow(),result1.getStandardDeptId());
				if(schRotationDept!=null){
					schRotationDeptMap.put(result.get("processFlow"), schRotationDept);
				}
			}
		}
		model.addAttribute("schRotationDeptMap", schRotationDeptMap);
		model.addAttribute("resRecMap", expressMap);
		model.addAttribute("schArrangeResult", schArrangeResult);
		return "jsres/kzr/afterAuditSearch";
	}

	/**
	 * 学员考评
	 */
	@RequestMapping(value="/docProcessEvalMain")
	public String docProcessEval(Model model,Integer currentPage,HttpServletRequest request,SchArrangeResult arrangeResult){
		return "jsres/kzr/docProcessEval/main";
	}

	/**
	 * 入科教育管理（医院管理员角色）
	 */
	@RequestMapping(value = "/admissionEduDeptList")
	public String admissionEduDeptList(Model model, SysDept dept, Integer currentPage, HttpServletRequest request) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		dept.setOrgFlow(currentUser.getOrgFlow());
        dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> list = deptBiz.searchDeptByUnion(dept, "");
		Map<String,Object> infoMap = new HashMap<>();
		if(list!=null&&list.size()>0){
			for(Map<String,String> tempMap : list){
				String deptFlow = tempMap.get("deptFlow");
				String orgFlow = currentUser.getOrgFlow();
				ResInprocessInfo info=resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow,orgFlow);
				infoMap.put(deptFlow,info);
			}
		}
		model.addAttribute("infos", list);
		model.addAttribute("infoMap", infoMap);
		return "jsres/admissionEdu/admissionEduDeptList";
	}
	@RequestMapping(value = "/doctorDeptList")
	public String doctorDeptList(Model model, SysDept dept, Integer currentPage, HttpServletRequest request) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		ResDoctor doctor=resDoctorBiz.readDoctor(currentUser.getUserFlow());
		if(doctor!=null) {
			PageHelper.startPage(currentPage, getPageSize(request));
			List<SysDept> depts = schArrangeResultBiz.findDeptsByDoctor(doctor.getDoctorFlow());
			Map<String, Object> infoMap = new HashMap<>();
			if (depts != null && depts.size() > 0) {
				for (SysDept tempMap : depts) {
					String deptFlow = tempMap.getDeptFlow();
					String orgFlow = tempMap.getOrgFlow();
					ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow, orgFlow);
					infoMap.put(deptFlow, info);
				}
			}
			model.addAttribute("infos", depts);
			model.addAttribute("infoMap", infoMap);
		}
		return "jsres/admissionEdu/doctorDeptList";
	}

	/**
	 * 入科教育管理标签页
	 */
	@RequestMapping(value = "/admissionEducationMain")
	public String admissionEducationMain(Model model, Integer currentPage, HttpServletRequest request, SchArrangeResult arrangeResult) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		List<SysUserDept> userDepts = iUserBiz.searchUserDeptByUser(currentUser.getUserFlow());
		model.addAttribute("userDepts", userDepts);
		return "jsres/admissionEdu/admissionEduTap";
	}

	/**
	 * 入科教育管理
	 */
	@RequestMapping(value = "/admissionEducationManage")
	public String admissionEducationManage(Model model, String role, String deptFlow, String orgFlow) {
		model.addAttribute("role", role);
		SysUser currentUser = GlobalContext.getCurrentUser();
		if(StringUtil.isBlank(orgFlow))
		 	orgFlow = currentUser.getOrgFlow();
		SysDept dept = deptBiz.readSysDept(deptFlow);
		model.addAttribute("dept", dept);
		ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow, orgFlow);
		model.addAttribute("info", info);
		if (info != null) {
			List<ResInprocessInfoMember> members = resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
			model.addAttribute("members", members);
			List<PubFile> files = pubFileBiz.searchByProductFlow(info.getRecordFlow());
			model.addAttribute("files", files);
			String arrange = info.getTeachingArrangement();
			if (StringUtil.isNotBlank(arrange)) {
				Map<String, Object> arrangeMap = new HashMap<>();
				arrangeMap = paressXml(arrange);
				model.addAttribute("arrangeMap", arrangeMap);
			}
		}
		if("doctor".equals(role))
		{
			return "jsres/admissionEdu/showEduInfo";
		}
		return "jsres/admissionEdu/admissionEduInfo";
	}

	@RequestMapping(value = {"/saveAdmissionEducation"}, method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String saveAdmissionEducation(String jsonData, ResInprocessInfo info, String recordFlow, HttpServletRequest request) throws IOException {
		if (StringUtil.isBlank(recordFlow)) {
			if (StringUtil.isNotBlank(info.getRecordFlow())) {
				recordFlow = info.getRecordFlow();
			} else {
				recordFlow = PkUtil.getUUID();
			}
		}
		info.setRecordFlow(recordFlow);
		jsonData = java.net.URLDecoder.decode(jsonData, "UTF-8");
		//校验上传文件大小及格式
		String checkResult = checkFiles(request);
		if (!"1".equals(checkResult)) {
			return checkResult;
		}
		Map<String, Object> mp = JSON.parseObject(jsonData, Map.class);


		//科室成员
		List<JSONObject> jsonObjects = (List<JSONObject>) mp.get("members");
		List<ResInprocessInfoMember> members = new ArrayList<>();
		List<String> memberFlows = new ArrayList<>();
		if (jsonObjects != null && jsonObjects.size() > 0) {
			for (JSONObject jo : jsonObjects) {
				ObjectMapper objectMapper = new ObjectMapper();
				ResInprocessInfoMember m = objectMapper.readValue(jo.toString(), ResInprocessInfoMember.class);
				m.setInfoRecordFlow(recordFlow);
				members.add(m);
				if (StringUtil.isNotBlank(m.getRecordFlow())) {
					memberFlows.add(m.getRecordFlow());
				}
			}
		}
		//删除不在新的保存数据中的成员
		resInprocessInfoBiz.deleteMemberNotInFlow(recordFlow, memberFlows);
		//保存科室成员信息
		saveMembers(members);

		//上传文件的流水号
		List<String> fileFlows = (List<String>) mp.get("fileFlows");
		//处理不在本次保存中的文件
		upadteFileInfo(recordFlow, fileFlows);
		//处理上传文件
		addUploadFile(recordFlow, request);

		//科室固定学术及业务活动安排
		Map<String, Object> teaching = (Map<String, Object>) mp.get("teaching");
		if (teaching != null) {
			String arrange = getXml(teaching);
			info.setTeachingArrangement(arrange);
		}
		SysDept dept = deptBiz.readSysDept(info.getDeptFlow());
		info.setDeptName(dept.getDeptName());
		info.setOrgFlow(dept.getOrgFlow());
		SysOrg org = orgBiz.readSysOrg(dept.getOrgFlow());
		info.setOrgName(org.getOrgName());
		int count = resInprocessInfoBiz.saveInfo(info);
		if (count == 0)
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	//下载附件
	@RequestMapping(value = {"/fileDown"}, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception {
		PubFile file = this.pubFileBiz.readFile(fileFlow);
		pubFileBiz.downPubFile(file, response);
	}
	/**
	 * 保存成员信息
	 *
	 * @param members
	 */
	private void saveMembers(List<ResInprocessInfoMember> members) {
		if (members != null && members.size() > 0) {
			for (ResInprocessInfoMember m : members) {
				resInprocessInfoBiz.saveMember(m);
			}
		}
	}
	@RequestMapping(value="/docProcessEvalList")
	public String docProcessEvalList(Model model,Integer currentPage ,HttpServletRequest request,
							String trainingTypeId,String trainingSpeId,String datas[],
							String sessionNumber,String graduationYear,
							String userName,String idNo
	) throws ParseException {
		SysUser currentUser = GlobalContext.getCurrentUser();
		//查询条件
		Map<String,Object> param=new HashMap<>();
		List<String>docTypeList=new ArrayList<String>();//人员类型
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		param.put("deptFlow", currentUser.getDeptFlow());
		param.put("userFlow", currentUser.getUserFlow());
		param.put("docTypeList",docTypeList);
        param.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("userName",userName);
		param.put("idNo",idNo);
		param.put("role","head");
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list=iResDoctorProcessBiz.jsresSchDoctorSchProcessEval(param);
		Map<String,Integer> evalSizeMap=new HashMap<>();
		Map<String,	List<Map<String, Object>>> evalMap=new HashMap<>();
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> process:list)
			{
				String startDate= (String) process.get("schStartDate");
				String endDate= (String) process.get("schEndDate");
				String processFlow= (String) process.get("processFlow");
				List<Map<String, String>> times=getTimes(startDate,endDate,processFlow);
				evalSizeMap.put(processFlow,times.size());
				if(times.size()>0)
				{
					Map<String, Object> params=new HashMap<String, Object>();
					params.put("times", times);
					List<Map<String, Object>> evals=iResDoctorProcessBiz.findOneProcessEvals(params);
					evalMap.put(processFlow,evals);
				}
			}
		}
		model.addAttribute("list",list);
		model.addAttribute("evalSizeMap",evalSizeMap);
		model.addAttribute("evalMap",evalMap);
		return "jsres/kzr/docProcessEval/docProcessEvalList";
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

	/**
	 * 江苏西医--培训数据查询
	 * @param doctorFlow
	 * @param processFlow
	 * @param recTypeId
	 * @param model
	 * @return
     * @throws Exception
     */
	@RequestMapping(value="/details",method={RequestMethod.GET,RequestMethod.POST})
	public String details(String doctorFlow,String processFlow,String recTypeId,Model model) throws Exception{
		if (StringUtil.isBlank(recTypeId)) {
			return "jsres/kzr/registrationRecord";
		}
		Map<String, Map<String,Object>> resRecMap=new HashMap<String, Map<String,Object>>();
		List<ResRec> resRecList = null;
		List<ResSchProcessExpress> expressList = null;
        if (recTypeId.equals(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId())) {
			expressList = expressBiz.searchResRecExpressWithBLOBs(recTypeId,processFlow);
			if (expressList!=null&&expressList.size()>0) {
				for (ResSchProcessExpress express : expressList){
					String recContent = express.getRecContent();
					Map<String,Object> formDataMap = resRecBiz.parseRecContent(recContent);
					resRecMap.put(express.getRecFlow(), formDataMap);
				}
			}
			model.addAttribute("resRecList", expressList);
			model.addAttribute("resRecMap", resRecMap);
            JsresPowerCfg powerCfg = jsresPowerCfgMapper.selectByPrimaryKey("jsres_doctor_app_menu_" + doctorFlow);
            if (powerCfg != null) {
                model.addAttribute("statusId", powerCfg.getCheckStatusId());
            }
		}else{
            if (com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId().equals(recTypeId))
			{

				List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
				List<Map<String,Object>> list=resRecBiz.queryJoinActivityAndEditList(doctorFlow,recTypeId,processFlow);
				if(list!=null)
				{
					for(Map<String,Object> rec:list)
					{
						Map<String, Object> formDataMap=new HashMap<>();
						if(rec.get("recTypeId")!=null&&StringUtil.isNotBlank(String.valueOf(rec.get("recTypeId")))) {
							String recContent = String.valueOf(rec.get("recContent"));
							formDataMap = resRecBiz.parseRecContent(recContent);
						}else{
							formDataMap.put("activity_way", rec.get("activityTypeName")==null?"":String.valueOf(rec.get("activityTypeName")));
							formDataMap.put("activity_date", rec.get("startTime")==null?"":String.valueOf(rec.get("startTime")));
							formDataMap.put("activity_period", "1");
							formDataMap.put("activity_speaker", rec.get("speakerName")==null?"":String.valueOf(rec.get("speakerName")));
							String activity_content=rec.get("activityName")==null?"":String.valueOf(rec.get("activityName"))+
									rec.get("activityRemark")==null?"":String.valueOf(rec.get("activityRemark"));
							formDataMap.put("activity_content", activity_content);
							formDataMap.put("activity_address", rec.get("activityAddress")==null?"":String.valueOf(rec.get("activityAddress")));
                            formDataMap.put("isJoin", com.pinde.core.common.GlobalConstant.FLAG_Y);
						}
						formDataMap.put("recFlow", String.valueOf(rec.get("recFlow")));
						dataList.add(formDataMap);
					}
				}
				model.addAttribute("resRecList", dataList);
			}else {
				resRecList = resRecBiz.searchResRecWithBLOBs(recTypeId, processFlow, doctorFlow);
				if (resRecList != null && resRecList.size() > 0) {
					for (ResRec resRec : resRecList) {
						String recContent = resRec.getRecContent();
						Map<String, Object> formDataMap = resRecBiz.parseRecContent(recContent);
						resRecMap.put(resRec.getRecFlow(), formDataMap);
					}
				}
				model.addAttribute("resRecList", resRecList);
				model.addAttribute("resRecMap", resRecMap);
			}
		}


        if (recTypeId.equals(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId())) {
			String operUserFlow=doctorFlow;
			Map<String, Object> dataMap = new HashMap<String, Object>();
			Map<String,Object> processPerMap=resRecBiz.getRecProgressIn(operUserFlow,processFlow,null,null);
			if(processPerMap == null){
				processPerMap = new HashMap<String, Object>();
			}
			//获取不同类型并定义接受
			if(processPerMap!=null) {
                String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
                String diseaseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
                String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
                String operationRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();

				String caseRegistry = (String) processPerMap.get(processFlow + caseRegistryId);
				String caseRegistryReqNum = (String) processPerMap.get(processFlow + caseRegistryId + "ReqNum");
				String caseRegistryFinished = (String) processPerMap.get(processFlow + caseRegistryId + "Finished");

				String diseaseRegistry = (String) processPerMap.get(processFlow + diseaseRegistryId);
				String diseaseRegistryReqNum = (String) processPerMap.get(processFlow + diseaseRegistryId + "ReqNum");
				String diseaseRegistryFinished = (String) processPerMap.get(processFlow + diseaseRegistryId + "Finished");

				String skillRegistry = (String) processPerMap.get(processFlow + skillRegistryId);
				String skillRegistryReqNum = (String) processPerMap.get(processFlow + skillRegistryId + "ReqNum");
				String skillRegistryFinished = (String) processPerMap.get(processFlow + skillRegistryId + "Finished");

				String skillAndOperationRegistry = (String) processPerMap.get(processFlow + operationRegistryId);
				String skillAndOperationRegistryReqNum = (String) processPerMap.get(processFlow + operationRegistryId + "ReqNum");
				String skillAndOperationRegistryFinished = (String) processPerMap.get(processFlow + operationRegistryId + "Finished");

                String recTypeIdt = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
//				int teachingRounds = 0;
//				int difficult = 0;
//				int lecture = 0;
//				int death = 0;
				int jxcf = 0;  int xjk = 0;
				int rkjy = 0;  int ckkh = 0; int jnpx = 0;
				int yph = 0; int jxhz = 0; int jxbltl = 0; int lcczjnzd = 0;
				int lcblsxzd = 0; int ssczzd = 0; int yxzdbgsxzd = 0; int lcwxyd = 0;
				int ryjy = 0; int rzyjdjy = 0; int cjbg = 0;
				int ynbltl=0;	int wzbltl=0; int swbltl=0;
				int bgdfx=0;	int jxsj=0;	int sjys=0;
				List<String> recTypes = new ArrayList<String>();
				recTypes.add(recTypeIdt);
				List<ResRec> recs = resRecBiz.searchRecByProcessWithBLOBs(recTypes, processFlow, operUserFlow);
//				List<ResSchProcessExpress> recs = expressBiz.searchRecByProcessWithBLOBs(recTypes, processFlow, operUserFlow);

				for (ResRec resRec : recs) {
					String content = resRec.getRecContent();
					Document document = DocumentHelper.parseText(content);
					Element root = document.getRootElement();
					Element ec = root.element("activity_way");
					if (ec != null) {
						String text = ec.attributeValue("id");
						if(Jxcf.equals(text)){
							jxcf++;
						}else if(Ynbltl.equals(text)){
							ynbltl++;
						}else if(Wzbltl.equals(text)){
							wzbltl++;
						}else if(Xsjz.equals(text)){
							xjk++;
						}else if(Swbltl.equals(text)){
							swbltl++;
						}else if(Rkjy.equals(text)){
							rkjy++;
						}else if(Ckks.equals(text)){
							ckkh++;
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
				List<TeachingActivityInfo> infos=resRecBiz.searchJoinActivityByProcessFlow(processFlow);
				if(infos!=null&&infos.size()>0)
				{
					for(TeachingActivityInfo info:infos)
					{
						String text=info.getActivityTypeId();
						if(Jxcf.equals(text)){
							jxcf++;
						}else if(Ynbltl.equals(text)){
							ynbltl++;
						}else if(Wzbltl.equals(text)){
							wzbltl++;
						}else if(Xsjz.equals(text)){
							xjk++;
						}else if(Swbltl.equals(text)){
							swbltl++;
						}else if(Rkjy.equals(text)){
							rkjy++;
						}else if(Ckks.equals(text)){
							ckkh++;
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
				dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
				dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
				dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

				dataMap.put("diseaseRegistry", StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
				dataMap.put("diseaseRegistryReqNum", StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
				dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

				dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
				dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
				dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

				dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
				dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
				dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

				dataMap.put("jxcf",String.valueOf(jxcf));
//			dataMap.put("ynbltl",String.valueOf(ynbltl));
//			dataMap.put("wzbltl",String.valueOf(wzbltl));
				dataMap.put("xjk",String.valueOf(xjk));
//			dataMap.put("swbltl",String.valueOf(swbltl));
				dataMap.put("rkjy",String.valueOf(rkjy));
				dataMap.put("ckkh",String.valueOf(ckkh));
				dataMap.put("jnpx",String.valueOf(jnpx));
				dataMap.put("yph",String.valueOf(yph));
				dataMap.put("jxhz",String.valueOf(jxhz));
				dataMap.put("jxbltl",String.valueOf(jxbltl));
				dataMap.put("wzbltl",String.valueOf(wzbltl));
				dataMap.put("swbltl",String.valueOf(swbltl));
				dataMap.put("ynbltl",String.valueOf(ynbltl));
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
				model.addAttribute("dataMap", dataMap);
			}
		}


        if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(recTypeId)) {
			if (expressList!=null && expressList.size()>0) {
				ResSchProcessExpress rec=expressList.get(0);
				if (rec!=null) {
					Map<String, Object> formDataMap=resRecMap.get(rec.getRecFlow());
					model.addAttribute("formDataMap", formDataMap);
				}
				model.addAttribute("rec", rec);
				boolean f = false;
				f=resDoctorBiz.getCkkPower(doctorFlow);
				if (f) {
					ResScore outScore = resScoreBiz.getScoreByProcess(processFlow);
					model.addAttribute("outScore",outScore);
				}
				model.addAttribute("ckk", f);

			}
		}

        if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId) || com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
			String cfgCodeId = null;
            if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
			}
			if (resRecList!=null && resRecList.size()>0) {
				ResRec rec=resRecList.get(0);
				if (rec!=null) {
					model.addAttribute("rec",rec);
					Map<String,Object> gradeMap = resRecBiz.parseGradeXml(rec.getRecContent());
					model.addAttribute("gradeMap",gradeMap);
				}
			}
			ResAssessCfg search = new ResAssessCfg();
			search.setCfgCodeId(cfgCodeId);
//			search.setOrgFlow(currUser.getOrgFlow());
			List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
			if(assessCfgList != null && !assessCfgList.isEmpty()){
				ResAssessCfg assessCfg = assessCfgList.get(0);
				model.addAttribute("assessCfg",assessCfg);
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
					for(Element te :titleElementList){
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for(Element ie : itemElementList){
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
		}

		return "jsres/kzr/registrationRecord";
	}

	private Map<String, Object> paressXml(String content) {
		Map<String, Object> formDataMap = null;
		if (StringUtil.isNotBlank(content)) {
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();
				for (Element element : elements) {
					formDataMap.put(element.getName(), element.getText());
				}
			} catch (DocumentException e) {
                logger.error("", e);
			}
		}
		return formDataMap;
	}

	private String getXml(Map<String, Object> teaching) {
		String xml = "";
		if (teaching != null && teaching.size() > 0) {
			Document doc = DocumentHelper.createDocument();
			Element root = doc.addElement("root");
			for (String key : teaching.keySet()) {
				Element item = root.addElement(key);
				item.setText(String.valueOf(teaching.get(key)));
			}
			xml = doc.asXML();
		}
		return xml;
	}

	//处理文件
	private void upadteFileInfo(String recordFlow, List<String> fileFlows) {
		//查询出不在本次保存中的文件信息
		List<PubFile> files = pubFileBiz.searchByProductFlowAndNotInFileFlows(recordFlow, fileFlows);
		//删除服务器中相应的文件
		if (files != null && files.size() > 0) {
			String basePath = InitConfig.getSysCfg("upload_base_dir");
			for (PubFile pubFile : files) {
				if (StringUtil.isNotBlank(pubFile.getFilePath())) {
					String filePath = basePath + pubFile.getFilePath();
					FileUtil.deletefile(filePath);
				}
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				pubFileBiz.editFile(pubFile);
			}
		}
	}

	//保存上传的附件
	private void addUploadFile(String recordFlow, HttpServletRequest request) {
		//以下为多文件上传********************************************
		//创建一个通用的多部分解析器
		List<PubFile> pubFiles = new ArrayList<>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				//记录上传过程起始时的时间，用来计算上传时间
				//int pre = (int) System.currentTimeMillis();
				//取得上传文件
				List<MultipartFile> files = multiRequest.getFiles(iter.next());
				if (files != null && files.size() > 0) {
					for (MultipartFile file : files) {
						//保存附件
						PubFile pubFile = new PubFile();
						//取得当前上传文件的文件名称
						String oldFileName = file.getOriginalFilename();
						//如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (StringUtil.isNotBlank(oldFileName)) {
							//定义上传路径
							String dateString = DateUtil.getCurrDate2();
							String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "inProcessFile" + File.separator + dateString + File.separator + recordFlow;
							File fileDir = new File(newDir);
							if (!fileDir.exists()) {
								fileDir.mkdirs();
							}
							//重命名上传后的文件名
							String originalFilename = "";
							originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
							File newFile = new File(fileDir, originalFilename);
							try {
								file.transferTo(newFile);
							} catch (Exception e) {
                                logger.error("", e);
								throw new RuntimeException("保存文件失败！");
							}
							String filePath = File.separator + "inProcessFile" + File.separator + dateString + File.separator + recordFlow + File.separator + originalFilename;
                            pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
							pubFile.setFilePath(filePath);
							pubFile.setFileName(oldFileName);
							pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
							pubFile.setProductType("inProcessFile");
							pubFile.setProductFlow(recordFlow);
							pubFiles.add(pubFile);
						}
					}
				}
				//记录上传该文件后的时间
				//int finaltime = (int) System.currentTimeMillis();
			}
		}
		if (pubFiles.size() > 0) {
			pubFileBiz.saveFiles(pubFiles);
		}
	}

	private String checkFiles(HttpServletRequest request) {
		String result = "1";
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			List<String> fileSuffix = new ArrayList<>();
			fileSuffix.add(".DOC");
			fileSuffix.add(".DOCX");
			fileSuffix.add(".PPT");
			fileSuffix.add(".PDF");
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				//记录上传过程起始时的时间，用来计算上传时间
				//int pre = (int) System.currentTimeMillis();
				//取得上传文件
				List<MultipartFile> files = multiRequest.getFiles(iter.next());
				if (files != null && files.size() > 0) {
					for (MultipartFile file : files) {
						if(file.getSize()>0) {
							//取得当前上传文件的文件名称
							String fileName = file.getOriginalFilename();
							String suffix = fileName.substring(fileName.lastIndexOf(".")).toUpperCase();
							if (!fileSuffix.contains(suffix)) {
								return "入科教育文档【" + fileName + "】的文件格式不正确，只能上传word,ppt,pdf三种格式的文件。";
							}
							if (file.getSize() > 10 * 1024 * 1024) {
								return "入科教育文档【" + fileName + "】的大小超过10M，不得保存";
							}
						}
					}
				}
			}
		}
		return result;
	}

	@RequestMapping("/main")
	public String main(Model model,String deptFlow){
		model.addAttribute("deptFlow", deptFlow);
		//查看基地是否付费 如果基地是非付费，科室不可以填写数据
		JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_baseInfo_maintenance_" + GlobalContext.getCurrentUser().getOrgFlow());
        if (null == cfg || StringUtil.isBlank(cfg.getCfgValue()) || !cfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
            model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		// 查询用户科室信息
		List<SysUserDept> userDeptList = userBiz.searchUserDeptByUser(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("userDeptList", userDeptList);
		return "jsres/kzr/mainInfo/main";
	}

	/**
	 * 查找信息
	 * @param deptFlow
	 * @param baseInfoName
	 * @param editFlag	是否可编辑
	 * @param viewFlag	是否查看
	 * @param sessionNumber	年份
	 * @param orgFlow
	 * @param isJoin	控制页面上下滑动
	 * @param speFlow
	 * @param onlyRead	基地查看
	 * @return
	 */
		@RequestMapping("/findAllBaseInfo")
	public ModelAndView findAllBaseInfo(String deptFlow, String baseInfoName, String editFlag, String viewFlag,String sessionNumber,
										String orgFlow,String isJoin,String speFlow,String onlyRead,String isglobal){

		ModelAndView mav = new ModelAndView();
		if (StringUtil.isEmpty(orgFlow)){
			orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
		}
		if (StringUtil.isEmpty(speFlow)){
			speFlow=GlobalContext.getCurrentUser().getResTrainingSpeId();
		}

		//省厅或者客服角色，只能查看
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		if (null!=sysUserRoleList && sysUserRoleList.size()>0){
			for (SysUserRole userRole : sysUserRoleList) {
				if (StringUtil.isNotBlank(userRole.getRoleFlow()) &&(
						userRole.getRoleFlow().equals(InitConfig.getSysCfg("res_global_role_flow")) ||
								userRole.getRoleFlow().equals(InitConfig.getSysCfg("res_maintenance_role_flow")))){
                    isglobal = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    viewFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
			}
		}
		mav.addObject("isglobal", isglobal);
		mav.addObject("viewFlag", viewFlag);
		mav.addObject("orgFlow", orgFlow);
		mav.addObject("isJoin", isJoin);
		mav.addObject("deptFlow", deptFlow);
		mav.addObject("speFlow", speFlow);

		//指导医师情况
            if (com.pinde.core.common.GlobalConstant.GUIDING_PHYSICIAN.equals(baseInfoName)) {
			List<ResTeacherTraining> list = deptBasicInfoBiz.searchByOrgAndDept(orgFlow, deptFlow);
			for (ResTeacherTraining teacherTraining : list) {
				int year = Integer.parseInt(DateUtil.getYear());
				int moYear = Integer.parseInt(teacherTraining.getModifyTime().substring(0, 4));
				int num=year- moYear;
				if (StringUtil.isNotBlank(teacherTraining.getDoctorAge())){
					teacherTraining.setDoctorAge(String.valueOf(Integer.parseInt(teacherTraining.getDoctorAge())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getOfficeYear())){
					teacherTraining.setOfficeYear(String.valueOf(Integer.parseInt(teacherTraining.getOfficeYear())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getWorkYear())){
					teacherTraining.setWorkYear(String.valueOf(Integer.parseInt(teacherTraining.getWorkYear())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getInternYear())){
					teacherTraining.setInternYear(String.valueOf(Integer.parseInt(teacherTraining.getInternYear())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getHosYear())){
					teacherTraining.setHosYear(String.valueOf(Integer.parseInt(teacherTraining.getHosYear())+num));
				}
			}
			mav.addObject("list", list);
			mav.setViewName("jsres/kzr/mainInfo/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			return mav;
		}

		if (StringUtil.isEmpty(sessionNumber)){
            sessionNumber = com.pinde.core.util.DateUtil.getYear();
		}
		Integer year=Integer.parseInt(sessionNumber)-1;
		mav.addObject("sessionNumber", sessionNumber);
		boolean isPay=false;	//基地是否付费
			String hospitalAdmin = InitConfig.getSysCfg("res_admin_role_flow");
			List<String> currRoleList = (List<String>) getSessionAttribute("currRoleList");
			if(currRoleList == null || !currRoleList.contains(hospitalAdmin)) {
				JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_baseInfo_maintenance_" + orgFlow);
				//基地是付费用户，科主任可以填写信息，如果不是就不可以填写，只能由基地填写
                if (null != cfg && StringUtil.isNotBlank(cfg.getCfgValue()) && cfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					isPay = true;    //是付费用户
                    viewFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
				} else {
                    viewFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
			}else {
				isPay = true;
			}




		//诊疗疾病范围、医疗设备仪器
            if (com.pinde.core.common.GlobalConstant.DIAG_DISEASE.equals(baseInfoName) || com.pinde.core.common.GlobalConstant.EQUIPMENT_INSTRUMENTS.equals(baseInfoName)) {
			if(StringUtils.isNotEmpty(speFlow)) {
				Map<String, String> paramMap=new HashMap<>();
				paramMap.put("orgFlow",orgFlow);
				paramMap.put("speFlow",speFlow);
				paramMap.put("stype",baseInfoName);

                if (com.pinde.core.common.GlobalConstant.EQUIPMENT_INSTRUMENTS.equals(baseInfoName)) {
					paramMap.put("standardDeptId",speFlow);
				}else {
					SchAndStandardDeptCfg schAndStandardDeptCfg = schAndStandardDeptCfgBiz.readBySchDeptFlowAndOrgFlow(deptFlow, orgFlow);
					if (null!=schAndStandardDeptCfg){
						paramMap.put("standardDeptId",schAndStandardDeptCfg.getStandardDeptId());
					}
				}
				paramMap.put("dtype","dept");
				paramMap.put("deptFlow",deptFlow);
				paramMap.put("sessionNumber",sessionNumber);
				List<Map<String, String>> infoList = deptBasicInfoBiz.searchResBaseSpeDeptInfoData(paramMap);
				//如果没有数据，就将前一年的数据显示出来，方便用户填写
				paramMap.put("infoType",baseInfoName);
				int size = deptBasicInfoBiz.countResBaseSpeDeptInfoData(paramMap);
				if (isPay && size==0){
					paramMap.put("sessionNumber",year.toString());
					infoList = deptBasicInfoBiz.searchResBaseSpeDeptInfoData(paramMap);
                    editFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;

				}
				if (null!=infoList && infoList.size()>0){
					mav.addObject("tableAllArrNum", infoList.get(0).get("arrNum"));
				}

				mav.addObject("infoList", infoList);
                if ("3500".equals(speFlow) && com.pinde.core.common.GlobalConstant.EQUIPMENT_INSTRUMENTS.equals(baseInfoName)) {
					paramMap.put("standardDeptId","350001");
					List<Map<String, String>> infoList2 = deptBasicInfoBiz.searchResBaseSpeDeptInfoData(paramMap);
					if (null!=infoList2 && infoList2.size()>0){
						mav.addObject("infoList2", infoList2);
						mav.addObject("tableAllArrNum2", infoList2.get(0).get("arrNum"));
					}
				}
			}
		}


		ResBaseSpeDept baseSpeDept = deptBasicInfoBiz.readByOrgAndDept(orgFlow, deptFlow,sessionNumber);
		boolean dataIsNull=false;
		if (isPay && null==baseSpeDept){
			//如果没有数据，就将前一年的数据显示出来，方便用户填写
			baseSpeDept=deptBasicInfoBiz.readByOrgAndDept(orgFlow, deptFlow,year.toString());
			dataIsNull=true;
		}
		mav.addObject("baseSpeDept", baseSpeDept);
		if (baseSpeDept != null) {
			String Xml=baseSpeDept.getBaseInfo();
			if (StringUtil.isNotBlank(Xml)) {
				BaseSpeDeptExtForm speDeptExtForm= JaxbUtil.converyToJavaBean(Xml, BaseSpeDeptExtForm.class);
                if (com.pinde.core.common.GlobalConstant.DEPT_BASIC_INFO.equals(baseInfoName)) {    //科室基本信息
					//如果查询年份的数据为空，就显示去年的数据（方便用户填写）
					if (null==speDeptExtForm.getDeptBasicInfoForm() && dataIsNull==false && isPay){		//如果查询年份的数据为空，就显示前一年的数据
						baseSpeDept=deptBasicInfoBiz.readByOrgAndDept(orgFlow, deptFlow,year.toString());
						if (null!=baseSpeDept && StringUtil.isNotBlank(baseSpeDept.getBaseInfo())){
							mav.addObject("deptBasicInfoForm",JaxbUtil.converyToJavaBean(baseSpeDept.getBaseInfo(), BaseSpeDeptExtForm.class).getDeptBasicInfoForm());
						}
                        editFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
					}else {
						mav.addObject("deptBasicInfoForm", speDeptExtForm.getDeptBasicInfoForm());
					}
                } else if (com.pinde.core.common.GlobalConstant.TRAINING.equals(baseInfoName)) {    //培训情况
					//如果查询年份的数据为空，就显示去年的数据（方便用户填写）
					if (null==speDeptExtForm.getTrainingForm() && dataIsNull==false && isPay){		//如果查询年份的数据为空，就显示前一年的数据
						baseSpeDept=deptBasicInfoBiz.readByOrgAndDept(orgFlow, deptFlow,year.toString());
						if (null!=baseSpeDept && StringUtil.isNotBlank(baseSpeDept.getBaseInfo())){
							mav.addObject("trainingForm",JaxbUtil.converyToJavaBean(baseSpeDept.getBaseInfo(), BaseSpeDeptExtForm.class).getTrainingForm());
						}
                        editFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
					}else {
						mav.addObject("trainingForm", speDeptExtForm.getTrainingForm());
					}

                } else if (com.pinde.core.common.GlobalConstant.DEPARTMENT_HEAD.equals(baseInfoName)) {    //科室负责人信息
					//如果查询年份的数据为空，就显示去年的数据（方便用户填写）
					if (null==speDeptExtForm.getDepartmentHeadForm() && dataIsNull==false && isPay){		//如果查询年份的数据为空，就显示前一年的数据
						baseSpeDept=deptBasicInfoBiz.readByOrgAndDept(orgFlow, deptFlow,year.toString());
						if (null!=baseSpeDept && StringUtil.isNotBlank(baseSpeDept.getBaseInfo())){
							mav.addObject("departmentHeadForm",JaxbUtil.converyToJavaBean(baseSpeDept.getBaseInfo(), BaseSpeDeptExtForm.class).getDepartmentHeadForm());
						}
                        editFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
					}else {
						mav.addObject("departmentHeadForm", speDeptExtForm.getDepartmentHeadForm());
					}
				}
			}
			//查询附件
			if(StringUtil.isNotBlank(deptFlow)&&StringUtil.isNotBlank(baseInfoName)) {
				List<PubFile> files=new ArrayList<>();
                if (com.pinde.core.common.GlobalConstant.TRAINING.equals(baseInfoName)) {//查询附件
                    files = pubFileBiz.findFileByTypeFlow(com.pinde.core.common.GlobalConstant.TRAINING, orgFlow + deptFlow);
				}
                if (com.pinde.core.common.GlobalConstant.EQUIPMENT_INSTRUMENTS.equals(baseInfoName)) {//查询附件
                    files = pubFileBiz.findFileByTypeFlow(com.pinde.core.common.GlobalConstant.EQUIPMENT_INSTRUMENTS, orgFlow + deptFlow);
				}
				Map<String,PubFile> fileMap=new HashMap<>();
				if(files!=null&&files.size()>0)
				{
					for(PubFile f:files)
					{
						fileMap.put(f.getFileUpType(),f);
					}
				}
				mav.addObject("fileMap", fileMap);
				mav.addObject("files", files);
			}
            if (StringUtil.isNotBlank(onlyRead) && onlyRead.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {        //基地查看
                viewFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
			}
            if ((com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag)) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewFlag)) {
				mav.setViewName("jsres/kzr/mainInfo/edit"+baseInfoName);
			}else{
				mav.addObject("baseInfoName", baseInfoName);
				mav.setViewName("jsres/kzr/mainInfo/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			}
		}else{//无记录
            if (StringUtil.isNotBlank(onlyRead) && onlyRead.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {        //基地查看
                viewFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewFlag)) {
				mav.setViewName("jsres/kzr/mainInfo/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			}else{
				mav.setViewName("jsres/kzr/mainInfo/edit"+baseInfoName);
			}
		}
		return mav;
	}

	@RequestMapping("/guidingPhysicianList")
	public String guidingPhysicianList(String deptFlow, Integer currentPage ,HttpServletRequest request,Model model,String orgFlow){
		if (StringUtil.isEmpty(orgFlow)){
			orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
		}
//		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResTeacherTraining> list = deptBasicInfoBiz.searchByOrgAndDept(orgFlow, deptFlow);
		for (ResTeacherTraining teacherTraining : list) {
			int year = Integer.parseInt(DateUtil.getYear());
			int moYear = Integer.parseInt(teacherTraining.getModifyTime().substring(0, 4));
			int num=year- moYear;
			if (StringUtil.isNotBlank(teacherTraining.getDoctorAge())){
				teacherTraining.setDoctorAge(String.valueOf(Integer.parseInt(teacherTraining.getDoctorAge())+num));
			}
			if (StringUtil.isNotBlank(teacherTraining.getOfficeYear())){
				teacherTraining.setOfficeYear(String.valueOf(Integer.parseInt(teacherTraining.getOfficeYear())+num));
			}
			if (StringUtil.isNotBlank(teacherTraining.getWorkYear())){
				teacherTraining.setWorkYear(String.valueOf(Integer.parseInt(teacherTraining.getWorkYear())+num));
			}
			if (StringUtil.isNotBlank(teacherTraining.getInternYear())){
				teacherTraining.setInternYear(String.valueOf(Integer.parseInt(teacherTraining.getInternYear())+num));
			}
			if (StringUtil.isNotBlank(teacherTraining.getHosYear())){
				teacherTraining.setHosYear(String.valueOf(Integer.parseInt(teacherTraining.getHosYear())+num));
			}
		}
		model.addAttribute("list", list);
		return "jsres/kzr/mainInfo/guidingPhysicianList";
	}

	/**
	 * 保存基本信息
	 * @param baseSpeDeptForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveBase")
	@ResponseBody
	public String saveBase(String flag, BaseSpeDeptForm baseSpeDeptForm, String index, String type,
						   String fileFlows[],String orgFlow,
						   HttpServletRequest request) throws Exception{
		int  result =  deptBasicInfoBiz.saveBaseInfo(flag, baseSpeDeptForm, index,type,fileFlows,request,"dept");
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping("/saveNum")
	@ResponseBody
	public String saveNum(String infoFlow,String orgFlow,String info,String infoTwo,String deptFlow,String type,
						  String speFlow,String sessionNumber,String infoType) throws Exception{
		ResBaseSpeDeptData deptData = new ResBaseSpeDeptData();
		deptData.setInfoFlow(infoFlow);
		deptData.setOrgFlow(orgFlow);
		deptData.setOrgName(orgBiz.readSysOrg(orgFlow).getOrgName());
		deptData.setSessionNumber(sessionNumber);
		deptData.setInfoType(infoType);
		String speName="";
		if (StringUtil.isEmpty(speFlow)){
			speFlow=GlobalContext.getCurrentUser().getResTrainingSpeId();
			speName=GlobalContext.getCurrentUser().getResTrainingSpeName();
		}else {
			SysDict dict = dictBiz.readDict("DoctorTrainingSpe", speFlow);
			if (null!=dict){
				speName=dict.getDictName();
			}
		}

		deptData.setSpeFlow(speFlow);
		deptData.setSpeName(speName);
		deptData.setDeptFlow(deptFlow);
		SysDept sysDept = deptBiz.readSysDept(deptFlow);
		if(sysDept != null) {
			deptData.setDeptName(sysDept.getDeptName());
		}

		deptData.setType(type);
//		if (StringUtil.isNotBlank(info)){
			deptData.setInfo(info);
//		}
//		if (StringUtil.isNotBlank(infoTwo)){
			deptData.setInfoTwo(infoTwo);
//		}

		if (deptBasicInfoBiz.saveResBaseSpeDeptInfoData(deptData)<=0){
            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	//临时出科查询
	@RequestMapping(value="/temporaryOutSearch")
	public String temporaryOutSearch(Model model,Integer currentPage,HttpServletRequest request,SchArrangeResult arrangeResult, String roleId, String temporaryAuditStatusId, String biaoJi, String datas[]){
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		schArrangeResultMap.put("temporaryAuditStatusId", temporaryAuditStatusId);
		schArrangeResultMap.put("roleId", roleId);
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		model.addAttribute("roleId", roleId);
		model.addAttribute("biaoJi", biaoJi);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
        schArrangeResultMap.put("isAfter", com.pinde.core.common.GlobalConstant.FLAG_Y);
		schArrangeResultMap.put("biaoJi", biaoJi);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.temporaryOutSearch(schArrangeResultMap);
		model.addAttribute("schArrangeResult", schArrangeResult);
		return "jsres/kzr/temporaryOutSearch";
	}


	//临时出科导出
	@RequestMapping(value="/exportTemporaryOut")
	public void exportTemporaryOut(Model model,HttpServletResponse response,SchArrangeResult arrangeResult, String roleId, String temporaryAuditStatusId, String biaoJi, String datas[]) throws Exception {
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		schArrangeResultMap.put("temporaryAuditStatusId", temporaryAuditStatusId);
		schArrangeResultMap.put("roleId", roleId);
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		model.addAttribute("roleId", roleId);
		model.addAttribute("biaoJi", biaoJi);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
        schArrangeResultMap.put("isAfter", com.pinde.core.common.GlobalConstant.FLAG_Y);
		schArrangeResultMap.put("biaoJi", biaoJi);
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.temporaryOutSearch(schArrangeResultMap);
		String fileName = "临时出科信息.xls";
		String[] titles = new String[]{
				"userName:姓名",
				"doctorTypeName:人员类型",
				"schDeptName:轮转科室",
				"schStartDate:轮转开始时间",
				"schEndDate:轮转结束时间",
				"temporaryAuditStatusName:审核状态"
		};
//		fileName = URLEncoder.encode(fileName, "UTF-8");
		fileName = new String(fileName.getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, schArrangeResult, response.getOutputStream());
	}


	//临时出科审核查询
	@RequestMapping(value="/temporaryOutAuditSearch")
	public String temporaryOutAuditSearch(Model model,Integer currentPage,HttpServletRequest request,SchArrangeResult arrangeResult, String roleId, String biaoJi, String datas[]){
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysUser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysUser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		List<String>docTypeList=new ArrayList<String>();
		model.addAttribute("datas", datas);
		model.addAttribute("roleId", roleId);
		model.addAttribute("biaoJi", biaoJi);
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}else{
            datas = new String[com.pinde.core.common.enums.ResDocTypeEnum.values().length];
			int i=0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values())
			{
				docTypeList.add(e.getId());
				datas[i++]=e.getId();
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
        schArrangeResultMap.put("isAfter", com.pinde.core.common.GlobalConstant.FLAG_Y);
		schArrangeResultMap.put("biaoJi", biaoJi);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> schArrangeResult=iResDoctorProcessBiz.temporaryOutAuditSearch(schArrangeResultMap);
		model.addAttribute("schArrangeResult", schArrangeResult);
		return "jsres/kzr/temporaryOutAuditSearch";
	}

	//临时出科审核
	@RequestMapping(value="/temporaryOutAudit")
	@ResponseBody
	public String temporaryOutAudit(ResDoctorSchProcess proces){
		int count = iResDoctorProcessBiz.temporaryOutAudit(proces);
		if(count>0){
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
	}

}

