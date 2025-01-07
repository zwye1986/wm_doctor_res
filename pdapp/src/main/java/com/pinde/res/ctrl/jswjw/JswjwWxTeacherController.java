package com.pinde.res.ctrl.jswjw;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GeneralController;
import com.pinde.app.common.InitConfig;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.ActivityTypeEnum;
import com.pinde.core.common.enums.CheckStatusEnum;
import com.pinde.core.common.enums.RecStatusEnum;
import com.pinde.core.common.enums.RegistryTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.eval.IEvalAppBiz;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.jswjw.IIeaveAppBiz;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IJswjwTeacherBiz;
import com.pinde.res.biz.jswjw.IResDoctorProcessBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.core.model.FromItem;
import com.pinde.core.model.FromTitle;
import com.pinde.core.common.sci.dao.JsresPowerCfgMapper;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/jswjw/wx/teacher")
public class JswjwWxTeacherController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(JswjwWxTeacherController.class);

	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IInxInfoBiz inxInfoBiz;
	
	@Autowired
	private IJswjwBiz jswjwBiz;
	@Autowired
	private IJswjwTeacherBiz jswjwTeacherBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IResActivityBiz activityBiz;
	@Autowired
	private IResActivityTargetBiz activityTargeBiz;
	@Autowired
	private ResPaperBiz paperBiz;
	@Autowired
	private IEvalAppBiz evalAppBiz;
	@Autowired
	private JsresPowerCfgMapper jsresPowerCfgMapper;
	@Autowired
	IIeaveAppBiz ieaveAppBiz;
	@Autowired
	private IFileBiz pubFileBiz;

	@RequestMapping(value = {"/index"},method = {RequestMethod.POST})
	@ResponseBody
	public Object index(String userFlow,String roleId,String deptFlow) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		userinfo.setUserName(userinfo.getUserName()+"老师");
		resultMap.put("userinfo",userinfo);

		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		if(userinfo != null && StringUtil.isNotBlank(userinfo.getUserHeadImg())) {
			resultMap.put("userHeadImage", uploadBaseUrl + "/" + userinfo.getUserHeadImg());
		}else{
			resultMap.put("userHeadImage", "");
		}

		int currStudentHe=jswjwTeacherBiz.schDoctorSchProcessDistinctQuery(userinfo.getUserFlow(),"");//轮转学员数为轮转时间在计划时间的学员数
		int studentNum=jswjwTeacherBiz.schDoctorSchProcessTeacherQuery(userinfo.getUserFlow(),"");//所有已出科学员数量

		resultMap.put("studentNum",studentNum);
		resultMap.put("isCurrent",currStudentHe);
		resultMap.put("noAuditNumber","");

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

//		HashMap<String,Object> dictMap=new HashMap<>();
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

	@RequestMapping(value={"/doctorList"},method={RequestMethod.POST})
	@ResponseBody
	public Object doctorList(String userFlow, String searchData, Integer pageIndex, Integer pageSize, HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		
		if(StringUtil.isEmpty(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(pageIndex==null){
			return ResultDataThrow("起始页为空");
		}
		if(pageSize==null){
			return ResultDataThrow("页面大小为空");
		}
		
		//包装筛选条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherUserFlow",userFlow);
		
		//解析查询条件json字符串  statusId:Entering(已入科) , NotEntered(未入科) , Exited(已出科) doctorName:
		Map<String,String> searchMap = null;
		String statusId = "";
		String doctorName = "";
		if(StringUtil.isNotBlank(searchData)){
			try {
				//为json字符串转码
				searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			} catch (UnsupportedEncodingException e) {
                logger.error("", e);
			}
			//转换json字符串为map对象
			searchMap = (Map<String,String>)JSON.parse(searchData);
			if(searchMap!=null && !searchMap.isEmpty()){
				statusId = searchMap.get("statusId");
				doctorName = searchMap.get("doctorName");
				paramMap.put("statusId",statusId);
				paramMap.put("doctorName",doctorName);
			}
		}
		
		//筛选出该教师带过的所有学员
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> doctorMapList = jswjwBiz.getDocListByTeacher(paramMap);
		resultMap.put("doctorMapList",doctorMapList);
		
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl",uploadBaseUrl);
		
		//数据数量
		resultMap.put("dataCount", PageHelper.total);
		
		return resultMap;
	}

	@RequestMapping(value={"/funcList"},method={RequestMethod.POST})
	@ResponseBody
	public Object funcList(String userFlow,String doctorFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(doctorFlow)){
			return ResultDataThrow("学员标识符为空");
		}
		return resultMap;
	}

	@RequestMapping(value={"/dataList"},method={RequestMethod.POST})
	@ResponseBody
	public Object dataList(String userFlow,String doctorFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(doctorFlow)){
			return ResultDataThrow("学员标识符为空");
		}
		return resultMap;
	}
	@RequestMapping(value={"/auditData"},method={RequestMethod.POST})
	@ResponseBody
	public Object auditData(String userFlow,String doctorFlow,String dataFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(doctorFlow)){
			return ResultDataThrow("学员标识符为空");
		}
		if(StringUtil.isEmpty(dataFlow)){
			return ResultDataThrow("数据标识符为空");
		}
		
		jswjwBiz.auditDate(userFlow,dataFlow);
		return resultMap;
	}
	
	@RequestMapping(value={"/auditAllData"},method={RequestMethod.POST})
	@ResponseBody
	public Object auditAllData(String userFlow,String doctorFlow,String dataFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		return resultMap;
	}

	@RequestMapping(value={"/viewSummary"},method={RequestMethod.POST})
	@ResponseBody
	public Object viewSummary(String userFlow,String doctorFlow,String subDeptFlow,String dataFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(doctorFlow)){
			return ResultDataThrow("学员标识符为空");
		}
		if(StringUtil.isEmpty(subDeptFlow)){
			return ResultDataThrow("轮转科室标识符为空");
		}
		return resultMap;
	}

	@RequestMapping(value={"/saveSummary"},method={RequestMethod.POST})
	@ResponseBody
	public Object saveSummary(String userFlow,String doctorFlow,String subDeptFlow,HttpServletRequest request){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(doctorFlow)){
			return ResultDataThrow("学员标识符为空");
		}
		if(StringUtil.isEmpty(subDeptFlow)){
			return ResultDataThrow("轮转科室标识符为空");
		}
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		_putAll(paramMap, request);
		
		jswjwBiz.saveSummary(userFlow,doctorFlow,subDeptFlow,paramMap);
		
		return resultMap;
	}

	private void _putAll(Map<String,Object> paramMap,HttpServletRequest request){
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()){
			String paramName = paramNames.nextElement();
//			System.err.println("paramName="+paramName);
			String paramValue = request.getParameter(paramName);
			paramMap.put(paramName, paramValue);
		}
	}

	@RequestMapping(value = {"/aboutUs"},method = {RequestMethod.POST})
	@ResponseBody
	public Object aboutUs(String userFlow,HttpServletRequest request,HttpServletResponse response) {
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
		resultMap.put("userinfo",userinfo);
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/about/about.jsp";
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/about/about2.jsp";
		resultMap.put("imgurl",hostUrl);
		resultMap.put("androidimgurl",androidimgurl);

		return "res/jswjw/teacher/aboutUs";
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
		resultMap.put("resultMapList",resultMapList);

		//查询所有未读消息数
		Integer noReadNum = 0;
        List<Map<String, String>> notReadList = noticeBiz.searchInfoByOrgNotRead(orgFlow, com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
		if(null != notReadList && notReadList.size()>0){
			noReadNum = notReadList.size();
		}
		resultMap.put("noReadNum",noReadNum.toString());
//		resultMap.put("isReadMap", isReadMap);
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/no-pic.png";
		resultMap.put("hostUrl",hostUrl);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url")+"/titleImages/";
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
		if(info==null) {
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
//		resultMap.put("info",resReadInfo);
		return resultMap;
	}
	/**
	 * 数据审核学员列表
	 * @param userFlow
	 * @param roleId
	 * @param studentName
	 * @param pageIndex
	 * @param pageSize
     * @param biaoJi
     * @return
     */
	@RequestMapping(value = {"/studentList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object studentList(String userFlow,String roleId,String deptFlow,String doctorFlow,String studentName,String dataType,Integer pageIndex,Integer pageSize,String biaoJi) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if (StringUtil.isBlank(roleId)) {
			return ResultDataThrow("用户角色ID为空");
		}
		if (StringUtil.isBlank(doctorFlow)) {
			return ResultDataThrow("学员标识符为空");
		}
		if (!roleId.equals("Teacher")) {
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}

		List<Map<String, String>> resDoctorSchProcess = null;
		Map<String, String> schArrangeResultMap = new HashMap<String, String>();
		schArrangeResultMap.put("teacherUserFlow", userFlow);
		schArrangeResultMap.put("doctorFlow", doctorFlow);
//		schArrangeResultMap.put("deptFlow", deptFlow);
		if (StringUtil.isNotBlank(studentName)) {
			try {
				studentName = URLDecoder.decode(studentName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				studentName = "";
			}
		}
		schArrangeResultMap.put("userName", studentName);
		schArrangeResultMap.put("biaoJi", biaoJi);//如果不为空就是查询带审核的学员列表
		List<Map<String,String>> recTypeList = new ArrayList<>();
		if (StringUtil.isBlank(dataType) || dataType.equals("fiveData")) {
			Map<String,String> recMap = new HashMap<>();
			recMap.put("typeId","mr");
			recMap.put("typeName","大病历");
			recTypeList.add(recMap);
			recMap = new HashMap<>();
			recMap.put("typeId","disease");
			recMap.put("typeName","病种");
			recTypeList.add(recMap);
			recMap = new HashMap<>();
			recMap.put("typeId","skill");
			recMap.put("typeName","操作技能");
			recTypeList.add(recMap);
			recMap = new HashMap<>();
			recMap.put("typeId","operation");
			recMap.put("typeName","手术");
			recTypeList.add(recMap);
			recMap = new HashMap<>();
			recMap.put("typeId","activity");
			recMap.put("typeName","参加活动");
			recTypeList.add(recMap);
			if(StringUtil.isBlank(dataType)){
				recMap = new HashMap<>();
				recMap.put("typeId","DOPS");
				recMap.put("typeName","临床操作技能评估量化表");
				recTypeList.add(recMap);
				recMap = new HashMap<>();
				recMap.put("typeId","MINI-CEX");
				recMap.put("typeName","迷你临床演练评估量化表");
				recTypeList.add(recMap);
				recMap = new HashMap<>();
				recMap.put("typeId","AfterEvaluation");
				recMap.put("typeName","出科考核表");
				recTypeList.add(recMap);
				recMap = new HashMap<>();
				recMap.put("typeId","MonthEval");
				recMap.put("typeName","月度考评表");
				recTypeList.add(recMap);
			}
//				PageHelper.startPage(pageIndex, pageSize);
			resDoctorSchProcess = jswjwTeacherBiz.schDoctorSchProcessQuery(schArrangeResultMap);
		} else if ("dayAttend".equals(dataType)) {
//				PageHelper.startPage(pageIndex, pageSize);
			resDoctorSchProcess = jswjwTeacherBiz.schDoctorSchProcessDayAttend(schArrangeResultMap);
		} else if ("docEval".equals(dataType)) {
			Map<String,String> recMap = new HashMap<>();
			recMap.put("typeId","MonthEval");
			recMap.put("typeName","月度考评表");
			recTypeList.add(recMap);
//				PageHelper.startPage(pageIndex, pageSize);
			resDoctorSchProcess = jswjwTeacherBiz.schDoctorSchProcessEvalList(schArrangeResultMap);
		} else if (dataType.equals("skill") || dataType.equals("after")) {
			if( dataType.equals("after")) {
				Map<String, String> recMap = new HashMap<>();
				recMap.put("typeId", "AfterEvaluation");
				recMap.put("typeName", "出科考核表");
				recTypeList.add(recMap);
			}
			//		String	recTypeId=getRecTypeId(dataType);
			schArrangeResultMap.put("recTypeId", dataType);
			//		PageHelper.startPage(pageIndex, pageSize);
			resDoctorSchProcess = jswjwTeacherBiz.schDoctorSchProcessInfoQuery(schArrangeResultMap);
		} else {
			return ResultDataThrow("dataType错误");
		}

		boolean f = false;
		f = jswjwBiz.getCkkPower(doctorFlow);
		Map<String, SchRotationDept> schRotationDeptMap = new HashMap<String, SchRotationDept>();
		Map<String, Object> readSchRotationGroupMap = new HashMap<String, Object>();
		Map<String, ResSchProcessExpress> resRecMap = new HashMap<String, ResSchProcessExpress>();
		Map<String, Integer> resRecCountMap = new HashMap<String, Integer>();
		Map<String, String> schScoreMap = new HashMap<String, String>();
		if (resDoctorSchProcess != null && resDoctorSchProcess.size() > 0) {
			for (Map<String, String> map : resDoctorSchProcess) {

				Map<String, Object> perMap = jswjwBiz.getRecProgressIn(map.get("userFlow"), map.get("processFlow"), null, null);
				if (perMap != null) {
					Object o = perMap.get(map.get("processFlow"));
					String per = (null == o) ? "0" : String.valueOf(perMap.get(map.get("processFlow")));
					map.put("per", per);
				}
				if (StringUtil.isNotBlank(biaoJi)) {
					List<ResRec> resRecList = jswjwTeacherBiz.searchRecByProcess(map.get("processFlow"), map.get("userFlow"));
					if (resRecList != null && resRecList.size() > 0) {
						for (ResRec resRec : resRecList) {
							String k = map.get("processFlow") + resRec.getRecTypeId();
							String k2 = k + "notAudit";
							Integer i = resRecCountMap.get(k);
							if (i == null) {
								i = 0;
							}
							i++;
							resRecCountMap.put(k, i);
							if (!StringUtil.isNotBlank(resRec.getAuditStatusId())) {
								Integer j = resRecCountMap.get(k2);
								if (j == null) {
									j = 0;
								}
								j++;
								resRecCountMap.put(k2, j);
							}
						}
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
								schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
							}
						}
						String key = express.getProcessFlow() + express.getRecTypeId();
						resRecMap.put(key, express);
					}
				}

				if (f) {
					ResScore outScore = jswjwTeacherBiz.readScoreByProcessFlow(map.get("processFlow"));
					if (outScore != null) {
						String score = "0";
						if (null != outScore.getTheoryScore()) {
							score = outScore.getTheoryScore().toString();
						}
						schScoreMap.put(map.get("processFlow") + "schScore", score);
					}
				}
				SchArrangeResult schArrangeResult = jswjwTeacherBiz.readSchArrangeResult(map.get("schResultFlow"));
				if (schArrangeResult != null) {
					SchRotationDept schRotationDept = jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(), schArrangeResult.getStandardDeptId());
					if (schRotationDept != null) {
						schRotationDeptMap.put(map.get("processFlow"), schRotationDept);
					}
				}
			}
		}
//		resultMap.put("resRecMap", resRecMap);
//		resultMap.put("resRecCountMap", resRecCountMap);
//		resultMap.put("schScoreMap", schScoreMap);
//		resultMap.put("schRotationDeptMap", schRotationDeptMap);
//		resultMap.put("readSchRotationGroupMap", readSchRotationGroupMap);
//		resultMap.put("list", resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);
//		resultMap.put("biaoJi", biaoJi);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != resDoctorSchProcess && resDoctorSchProcess.size()>0){
			for (Map<String, String> m : resDoctorSchProcess) {
				Map<String,Object> map = new HashMap<>();
				String dopsKey = m.get("processFlow")+"DOPS";
				String miniKey = m.get("processFlow")+"Mini_CEX";
				String scoreKey = m.get("processFlow")+"schScore";
				map.put("userName",m.get("userName"));
				map.put("schStartDate",m.get("schStartDate"));
				map.put("schEndDate",m.get("schEndDate"));
				map.put("schScore",StringUtil.isBlank(schScoreMap.get(scoreKey)) ? "暂无" : schScoreMap.get(scoreKey));
				map.put("docFlow",m.get("userFlow"));
				map.put("schResultFlow",m.get("schResultFlow"));
				map.put("processFlow",m.get("processFlow"));
				map.put("orgFlow",m.get("orgFlow"));
				map.put("orgName",m.get("orgName"));
				map.put("deptFlow",m.get("deptFlow"));
				map.put("deptName",m.get("deptName"));
				map.put("schDeptFlow",m.get("schDeptFlow"));
				map.put("schDeptName",m.get("schDeptName"));
				map.put("afterRecFlow", null == resRecMap.get(m.get("processFlow")) ? "" : resRecMap.get(m.get("processFlow")).getRecFlow());
				map.put("afterStatusId",null == resRecMap.get(m.get("processFlow")) ? "notAudit" : "isAudit");
				map.put("afterStatusName",null == resRecMap.get(m.get("processFlow")) ? "未审核" : "已审核");
				map.put("dopsRecFlow",null == resRecMap.get(dopsKey) ? "" : resRecMap.get(dopsKey).getRecFlow());
				map.put("dopsStatusId",null == resRecMap.get(dopsKey) ? "notAudit" : "isAudit");
				map.put("dopsStatusName",null == resRecMap.get(dopsKey) ? "未审核" : "已审核");
				map.put("miniCexRecFlow",null == resRecMap.get(miniKey) ? "" : resRecMap.get(miniKey).getRecFlow());
				map.put("miniCexStatusId",null == resRecMap.get(miniKey) ? "notAudit" : "isAudit");
				map.put("miniCexStatusName",null == resRecMap.get(miniKey) ? "未审核" : "已审核");
				map.put("recordFlow",null == schRotationDeptMap.get(m.get("processFlow")) ? "" : schRotationDeptMap.get(m.get("processFlow")).getRecordFlow());
				String currDate = DateUtil.getCurrDate();
				map.put("currDate", currDate);
				map.put("per", StringUtil.isBlank(m.get("per")) ? "0" : m.get("per"));
				if(StringUtil.isNotBlank(biaoJi)){
					String preTrainMapKey = m.get("processFlow");
					String notAuditKey = preTrainMapKey+"CaseRegistrynotAudit";
					map.put("mrNotAudit", resRecCountMap.get(notAuditKey));
					notAuditKey = preTrainMapKey+"DiseaseRegistrynotAudit";
					map.put("diseaseNotAudit", resRecCountMap.get(notAuditKey));
					notAuditKey = preTrainMapKey+"SkillRegistrynotAudit";
					map.put("skillNotAudit", resRecCountMap.get(notAuditKey));
					notAuditKey = preTrainMapKey+"OperationRegistrynotAudit";
					map.put("operationNotAudit", resRecCountMap.get(notAuditKey));
					notAuditKey = preTrainMapKey+"CampaignRegistrynotAudit";
					map.put("activityNotAudit", resRecCountMap.get(notAuditKey));
				}
				String schStartDate = m.get("schStartDate");
				ResSchProcessExpress express = resRecMap.get(m.get("processFlow"));
				if(schStartDate.compareTo(currDate)<=0 &&
						!(null != express &&
								(StringUtil.isBlank(express.getManagerAuditUserFlow()) || StringUtil.isNotBlank(express.getManagerAuditUserFlow()) && StringUtil.isNotBlank(express.getHeadAuditStatusId())))
				){
					map.put("schType","isCurrent");
					map.put("schStatus","轮转中");
				}
				if(null != express &&
								(StringUtil.isBlank(express.getManagerAuditUserFlow()) || StringUtil.isNotBlank(express.getManagerAuditUserFlow()) && StringUtil.isNotBlank(express.getHeadAuditStatusId()))
				){
					map.put("schType","isSch");
					map.put("schStatus","已出科");
				}
				if(currDate.compareTo(schStartDate)<0 && null == express){
					map.put("schType","notCurrent");
					map.put("schStatus","未入科");
				}
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		resultMap.put("recTypeList",recTypeList);
		return resultMap;
	}

	@RequestMapping(value="/fiveDataNoAudit",method=RequestMethod.POST)
	@ResponseBody
	public Object fiveDataNoAudit(String userFlow,String roleId,String docFlow,String processFlow,String schResultFlow){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(docFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		if(StringUtil.isBlank(schResultFlow)){
			return ResultDataThrow("排班标识符为空");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		Map<String, Object> schRotationDeptMap=new HashMap<String, Object>();
		Map<String,Object> readSchRotationGroupMap=new HashMap<String,Object>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		SchArrangeResult schArrangeResult=jswjwTeacherBiz.readSchArrangeResult(schResultFlow);
		if (schArrangeResult!=null) {
			SchRotationDept schRotationDept=jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(),schArrangeResult.getStandardDeptId());
			if(schRotationDept!=null){
				schRotationDeptMap.put(processFlow, schRotationDept);
				if (StringUtil.isNotBlank(schRotationDept.getGroupFlow())) {
					SchRotationGroup readSchRotationGroup=jswjwTeacherBiz.readSchRotationGroup(schRotationDept.getGroupFlow());
					readSchRotationGroupMap.put(processFlow, readSchRotationGroup);
				}
			}
		}
		List<ResRec> resRecList=jswjwTeacherBiz.searchRecByProcess(processFlow,docFlow);
		for (ResRec resRec : resRecList) {
			String k = processFlow+resRec.getRecTypeId();
			String k2 = k+"notAudit";
			Integer i = resRecCountMap.get(k);
			if(i==null){
				i=0;
			}
			i++;
			resRecCountMap.put(k,i);
			if(!StringUtil.isNotBlank(resRec.getAuditStatusId())){
				Integer j = resRecCountMap.get(k2);
				if(j==null){
					j=0;
				}
				j++;
				resRecCountMap.put(k2,j);
			}
		}
//		resultMap.put("resRecCountMap", resRecCountMap);
		List<Map<String,Object>> resultMapList = new ArrayList<>();
		Map<String,Object> map = null;
		String notAuditKey = processFlow + "CaseRegistrynotAudit";
		if(null != resRecCountMap.get(notAuditKey)) {
			map = new HashMap<>();
			map.put("recType", "mr");
			map.put("typeName", "大病历");
			map.put("notAuditNum", resRecCountMap.get(notAuditKey));
			resultMapList.add(map);
		}

		notAuditKey = processFlow + "DiseaseRegistrynotAudit";
		if(null != resRecCountMap.get(notAuditKey)) {
			map = new HashMap<>();
			map.put("recType", "disease");
			map.put("typeName", "病种");
			map.put("notAuditNum", resRecCountMap.get(notAuditKey));
			resultMapList.add(map);
		}

		notAuditKey = processFlow + "SkillRegistrynotAudit";
		if(null != resRecCountMap.get(notAuditKey)) {
			map = new HashMap<>();
			map.put("recType", "skill");
			map.put("typeName", "操作技能");
			map.put("notAuditNum", resRecCountMap.get(notAuditKey));
			resultMapList.add(map);
		}

		notAuditKey = processFlow + "OperationRegistrynotAudit";
		if(null != resRecCountMap.get(notAuditKey)) {
			map = new HashMap<>();
			map.put("recType", "operation");
			map.put("typeName", "手术");
			map.put("notAuditNum", resRecCountMap.get(notAuditKey));
			resultMapList.add(map);
		}

		notAuditKey = processFlow + "CampaignRegistrynotAudit";
		if(null != resRecCountMap.get(notAuditKey)) {
			map = new HashMap<>();
			map.put("recType", "activity");
			map.put("typeName", "参加活动");
			map.put("notAuditNum", resRecCountMap.get(notAuditKey));
			resultMapList.add(map);
		}
		resultMap.put("processFlow",processFlow);
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value="/skillNoAudit",method=RequestMethod.POST)
	@ResponseBody
	public Object skillNoAudit(String userFlow,String roleId,String docFlow,String biaoJi,String processFlow,String schResultFlow){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(docFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		if(StringUtil.isBlank(schResultFlow)){
			return ResultDataThrow("排班标识符为空");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}

		List<String> typeId=new ArrayList<>();
        typeId.add(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId());
        typeId.add(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId());
		Map<String,Object> param=new HashMap<>();
		param.put("teaFlow",userFlow);
		param.put("docFlow",docFlow);
		param.put("processFlow",processFlow);
		param.put("recTypes",typeId);
		param.put("biaoJi",biaoJi);
		List<Map<String,Object>> list=jswjwTeacherBiz.findSkillNoAudit(param);
		for(Map<String,Object>map:list) {
            map.put("recTypeName", com.pinde.core.common.enums.ResRecTypeEnum.getNameById((String) map.get("recTypeId")));
		}
		resultMap.put("list",list);
		return resultMap;
	}

	@RequestMapping(value="/dayAttendList",method=RequestMethod.POST)
	@ResponseBody
	public Object dayAttendList(String userFlow,String roleId,String docFlow,
								Integer pageIndex,Integer pageSize,
								String attendType,
								String processFlow,String schResultFlow,String biaoJi) throws ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(docFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		if(StringUtil.isBlank(schResultFlow)){
			return ResultDataThrow("排班标识符为空");
		}
		if(StringUtil.isBlank(attendType)){
			return ResultDataThrow("attendType为空");
		}
		if(!attendType.equals("1")&&!attendType.equals("0")&&!attendType.equals("-1")){
			return ResultDataThrow("attendType只能是1(出勤)或0(缺勤)或-1(轮休)");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		Map<String,Object> param=new HashMap<>();
		param.put("teaFlow",userFlow);
		param.put("docFlow",docFlow);
		param.put("processFlow",processFlow);
		param.put("attendType",attendType);
		param.put("biaoJi",biaoJi);
		param.put("nowDate",DateUtil.getCurrDate());
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> list=jswjwTeacherBiz.dayAttendList(param);
		resultMap.put("list",list);
		resultMap.put("dataCount",PageHelper.total);
		param.put("attendType","");
        int count =jswjwTeacherBiz.dayAttendListCount(param);
        resultMap.put("allDataCount",count);
		return resultMap;
	}

	/**
	 * 一键审核通过
	 * @param userFlow
	 * @param roleId
	 * @param docFlow
	 * @param attendType
	 * @param processFlow
	 * @param schResultFlow
     * @return
     * @throws ParseException
     */
	@RequestMapping(value="/batchAttendAudit",method=RequestMethod.POST)
	@ResponseBody
	public Object batchAttendAudit(String userFlow,String roleId,String docFlow,
								String attendType,
								String processFlow,String schResultFlow) throws ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(docFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		if(StringUtil.isBlank(schResultFlow)){
			return ResultDataThrow("排班标识符为空");
		}
		if(StringUtil.isBlank(attendType)){
			return ResultDataThrow("attendType为空");
		}
		if(!attendType.equals("1")&&!attendType.equals("0")&&!attendType.equals("-1")){
			return ResultDataThrow("attendType只能是1(出勤)或0(缺勤)或-1(轮休)");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		Map<String,Object> param=new HashMap<>();
		param.put("teaFlow",userFlow);
		param.put("docFlow",docFlow);
		param.put("processFlow",processFlow);
		param.put("attendType",attendType);
        param.put("biaoJi", com.pinde.core.common.GlobalConstant.FLAG_Y);
		param.put("nowDate",DateUtil.getCurrDate());
		List<Map<String,Object>> list=jswjwTeacherBiz.dayAttendList(param);
		if(list==null||list.isEmpty())
		{
			return ResultDataThrow("无待审核数据");
		}
		int count=0;

		SysUser docUser = jswjwBiz.readSysUser(docFlow);
		for(Map<String,Object> b:list) {
			JsresAttendance attendance = null;
			String attendanceFlow = (String)( b.get("attendanceFlow")==null?"":b.get("attendanceFlow"));
			String attendDate = (String)( b.get("dateDay")==null?"":b.get("dateDay"));
			attendance = jswjwBiz.getJsresAttendanceByFlow(attendanceFlow);
			if(attendance==null)
				attendance=jswjwBiz.getJsresAttendance(attendDate,userFlow);
			boolean isNew=false;
			if(attendance==null)
			{
				isNew=true;
				attendance=new JsresAttendance();
				attendanceFlow= PkUtil.getUUID();
				attendance.setAttendanceFlow(attendanceFlow);
				attendance.setDoctorFlow(docFlow);
				attendance.setDoctorFlow(docFlow);
                attendance.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				attendance.setDoctorName(docUser.getUserName());
				attendance.setAttendDate(attendDate);
				attendance.setTeacherFlow(userinfo.getUserFlow());
				attendance.setTeacherName(userinfo.getUserName());
				attendance.setCreateTime(DateUtil.getCurrDateTime());
				attendance.setCreateUserFlow(userinfo.getUserFlow());
			}
			attendance.setAttendStatus(attendType);
			if(attendType.equals("1")){
				attendance.setAttendStatusName("出勤");
			}
			if(attendType.equals("0")){
				attendance.setAttendStatusName("缺勤");
			}
			if(attendType.equals("-1")){
				attendance.setAttendStatusName("轮休");
			}
			attendance.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
			attendance.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
			attendance.setAuditTime(DateUtil.getCurrDateTime());
			attendance.setAuditUserFlow(userinfo.getUserFlow());
			attendance.setAuditUserName(userinfo.getUserName());
			attendance.setModifyTime(DateUtil.getCurrDateTime());
			attendance.setModifyUserFlow(userinfo.getUserFlow());
			if(isNew)
				count+=jswjwBiz.addJsresAttendance(attendance);
			else
				count+=jswjwBiz.editJsresAttendance(attendance);

		}
		return resultMap;
	}

	/**
	 * 带教老师修改出勤状态或添加备注
	 * @param attendanceFlow
	 * @param remarks
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = {"/modifyAttendance"},method = {RequestMethod.POST})
	@ResponseBody
	public Object  modifyAttendance(String attendanceFlow,
									String userFlow,
									String roleId,
									String docFlow,
									String dateDay,
									String attendType, String remarks)throws Exception {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(docFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(dateDay)){
			return ResultDataThrow("签到日期为空");
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat2.parse(dateDay);
		} catch (Exception e) {
			return ResultDataThrow("签到日期格式有误");
		}
		if(StringUtil.isBlank(attendType)){
			return ResultDataThrow("attendType为空");
		}
		if(!attendType.equals("1")&&!attendType.equals("0")&&!attendType.equals("-1")){
			return ResultDataThrow("attendType只能是1(出勤)或0(缺勤)或-1(轮休)");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

		//验证用户是否存在
		SysUser sysUser = jswjwBiz.readSysUser(userFlow);
		if(sysUser==null){
			return ResultDataThrow("用户不存在");
		}
		SysUser docUser = jswjwBiz.readSysUser(docFlow);
		JsresAttendance jsresAttendance = jswjwBiz.getJsresAttendanceByFlow(attendanceFlow);
		if(jsresAttendance==null)
			jsresAttendance=jswjwBiz.getJsresAttendance(dateDay,userFlow);
		if(jsresAttendance!=null&&!"Auditing".equals(jsresAttendance.getAuditStatusId()))
		{
			return ResultDataThrow("该签到记录已经被审核，无法修改签到状态，请刷新列表！");
		}
		boolean isNew=false;
		if(jsresAttendance==null)
		{
			isNew=true;
			jsresAttendance=new JsresAttendance();
			 attendanceFlow= PkUtil.getUUID();
			jsresAttendance.setAttendanceFlow(attendanceFlow);
            jsresAttendance.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			jsresAttendance.setDoctorFlow(docFlow);
			jsresAttendance.setDoctorFlow(docFlow);
			jsresAttendance.setDoctorName(docUser.getUserName());
			jsresAttendance.setAttendDate(dateDay);
			jsresAttendance.setTeacherFlow(sysUser.getUserFlow());
			jsresAttendance.setTeacherName(sysUser.getUserName());
			jsresAttendance.setCreateTime(DateUtil.getCurrDateTime());
			jsresAttendance.setCreateUserFlow(sysUser.getUserFlow());
			jsresAttendance.setModifyTime(DateUtil.getCurrDateTime());
			jsresAttendance.setModifyUserFlow(sysUser.getUserFlow());
		}
			jsresAttendance.setAttendStatus(attendType);
			if(attendType.equals("1")){
				jsresAttendance.setAttendStatusName("出勤");
			}
			if(attendType.equals("0")){
				jsresAttendance.setAttendStatusName("缺勤");
			}
			if(attendType.equals("-1")){
				jsresAttendance.setAttendStatusName("轮休");
			}
			jsresAttendance.setTeacherRemarks(remarks);

		int count=0;
		if(isNew)
			count+=jswjwBiz.addJsresAttendance(jsresAttendance);
		else
			count+=jswjwBiz.editJsresAttendance(jsresAttendance);

		if(count!=1){
			return ResultDataThrow("修改失败！");
		}
		return resultMap;
	}

	@RequestMapping(value="/monthEvalList",method=RequestMethod.POST)
	@ResponseBody
	public Object monthEvalList(String userFlow,String roleId,String docFlow,String processFlow,String schResultFlow,String biaoJi) throws ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(docFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		if(StringUtil.isBlank(schResultFlow)){
			return ResultDataThrow("排班标识符为空");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(docFlow);
		SysUser docUser = jswjwBiz.readSysUser(docFlow);

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
			params.put("biaoJi", biaoJi);
			params.put("times", times);
			evals=jswjwTeacherBiz.findProcessEvals(params);
		}
//		resultMap.put("evals",evals);
//		resultMap.put("docUser",docUser);
//		resultMap.put("result",schArrangeResult);
//		resultMap.put("doctor",doctor);
//		resultMap.put("processFlow",processFlow);
//		resultMap.put("doctorFlow",docFlow);
		
		Map<String,String> userInfoMap = new HashMap<>();
		if(schArrangeResult!=null){
			userInfoMap.put("schStartDate",schArrangeResult.getSchStartDate());
			userInfoMap.put("schEndDate",schArrangeResult.getSchEndDate());
			userInfoMap.put("schDeptName",schArrangeResult.getSchDeptName());
		}
		userInfoMap.put("sessionNumber",doctor.getSessionNumber());
		userInfoMap.put("doctorName",docUser.getUserName());
		resultMap.put("userInfoMap",userInfoMap);
		
		List<Map<String,Object>> evalMapList = new ArrayList<>();
		if(null != evals && evals.size()>0){
			for (Map<String, Object> eval:evals) {
				Map<String,Object> map = new HashMap<>();
				map.put("processFlow",eval.get("processFlow"));
				map.put("doctorFlow",docFlow);
				map.put("evalMonth",eval.get("evalMonth"));
				map.put("startTime",eval.get("startTime"));
				map.put("endTime",eval.get("endTime"));
				map.put("recordFlow",eval.get("recordFlow"));
				String statusId = "";
				String statusName = "";
				if(null == eval.get("recordFlow")){
					statusId = "NotAudit";
					statusName = "待考评";
				}else{
					statusId = "Audited";
					statusName = "已考评";
				}
				map.put("statusId",statusId);
				map.put("statusName",statusName);
				evalMapList.add(map);
			}
		}
		resultMap.put("evalMapList",evalMapList);
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
		if(eval!=null)
		{
			IsForm=eval.getIsForm();
			configXml=eval.getFormCfg();
			//已经保存的分数
			valueMap=jswjwBiz.parseScoreXml(eval.getEvalResult());
//			resultMap.put("valueMap", map);
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

	@RequestMapping(value="/saveMonthEval",method=RequestMethod.POST)
	@ResponseBody
	public Object saveMonthEval(String userFlow,
								String haveForm,String configFlow,ResDoctorSchProcessEvalWithBLOBs eval2,
								HttpServletRequest request) throws ParseException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(eval2.getDoctorFlow())){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(eval2.getProcessFlow())){
			return ResultDataThrow("轮转标识符为空");
		}
		if(StringUtil.isBlank(eval2.getEvalMonth())){
			return ResultDataThrow("考核时间不能为空");
		}
		if(StringUtil.isBlank(haveForm)) {
			return ResultDataThrow("是否表单标识为空");
		}
		if(StringUtil.isBlank(eval2.getEvalContent())){
			return ResultDataThrow("评语不能为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		ResDoctorSchProcessEvalWithBLOBs eval=jswjwBiz.getDoctorProcessEval(eval2.getProcessFlow(),eval2.getEvalMonth());
		if(eval!=null) {
			return ResultDataThrow("已打过月度考评表，不得重复评分");
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(haveForm)) {
            eval2.setIsForm(com.pinde.core.common.GlobalConstant.FLAG_Y);
			if(StringUtil.isBlank(configFlow)) {
				return ResultDataThrow("表单标识符为空");
			}

			if(null==eval2.getEvalScore()) {
				return ResultDataThrow("总得分为空");
			}

			//评分配置
			ResDoctorProcessEvalConfig config=jswjwBiz.getProcessEvalConfigByFlow(configFlow);
			if(config==null) {
				return ResultDataThrow("评分配置信息不存在");
			}
			eval2.setFormCfg(config.getFormCfg());

			List<FromTitle> titleList=jswjwBiz.parseFromXmlForList(config.getFormCfg());
			//创建xmldom对象和根节点
			Document doc = DocumentHelper.createDocument();
			if(titleList!=null&&titleList.size()>0)
			{
				Element root = doc.addElement("evalCfg");
				for(FromTitle title:titleList )
				{
					if(title.getItems()!=null) {
						for (FromItem item : title.getItems())
						{
							Element items=root.addElement("score");
							items.addAttribute("id",item.getId());
							items.addAttribute("name",item.getName());
							if(StringUtil.isBlank((String) request.getParameter(item.getId()))){
								return ResultDataThrow( item.getName()+"项未打分，无法提交");
							}
							items.setText((String) request.getParameter(item.getId()));
						}
					}
				}
			}
			eval2.setEvalResult(doc.asXML());
		}else{
            eval2.setIsForm(com.pinde.core.common.GlobalConstant.FLAG_N);
		}
		ResDoctorSchProcess process=iResDoctorProcessBiz.read(eval2.getProcessFlow());
		if (process!=null) {
			List<Map<String, String>> times=new ArrayList<>();
			String startDate=process.getSchStartDate();
			String endDate=process.getSchEndDate();
			times=getTimes(startDate,endDate,eval2.getProcessFlow());
			for(Map<String,String> map:times)
			{
				if(eval2.getEvalMonth().equals(map.get("evlMonth")))
				{
					eval2.setStartTime(map.get("startTime"));
					eval2.setEndTime(map.get("endTime"));
					break;
				}
			}
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(eval2.getDoctorFlow());
		eval2.setDoctorName(doctor.getDoctorName());
		String curTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		eval2.setEvalTime(curTime);
		eval2.setEvalUserFlow(userFlow);
		eval2.setEvalUserName(userinfo.getUserName());
		int count=jswjwBiz.saveProcessEval(eval2,userinfo);
		if(count==0) {
			return ResultDataThrow("保存失败");
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
	/**
	 * 一键审核
	 * @param
	 * @param docFlow
	 * @param processFlow
	 * @param recType
     * @return
     */
	@RequestMapping(value="/batchAudit",method=RequestMethod.POST)
	@ResponseBody
	public Object batchAudit(String userFlow,String roleId,String docFlow,String processFlow,String recType){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "审核成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(docFlow)){
			return ResultDataThrow("学生标识符为空");
		}
		if(StringUtil.isBlank(processFlow)){
			return ResultDataThrow("轮转标识符为空");
		}
		//rec类型转换一下
		recType=getRecTypeId(recType);
		if(StringUtil.isBlank(recType)){
			return ResultDataThrow("数据类型为空");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}

        List<ResRec> recList = jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow, docFlow, recType, com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(null != recList && recList.size() > 0){
            ResRec resRec = recList.get(0);
            String appMenu = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_app_menu_" + resRec.getOperUserFlow());
            if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(appMenu)) {
				return ResultDataThrow("无操作权限，请联系基地管理员！");
            }
			int i = 0;
			for (ResRec rec : recList) {
					ResRec re=jswjwBiz.readResRec(rec.getRecFlow());
					String time=DateUtil.getCurrDateTime();
					SysUser sysUser=jswjwBiz.readSysUser(userFlow);
					re.setAuditTime(time);
					re.setAuditUserFlow(sysUser.getUserFlow());
					re.setAuditUserName(sysUser.getUserName());
					re.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
					re.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
					i+=jswjwBiz.editResRec(re,sysUser,recType);
			}
			if (i==0) {
				return ResultDataThrow("批量审核失败");
			}
		}else{
			return ResultDataThrow("无审核数据");
		}
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

	/**
	 * 单条数据审核
	 * @param
	 */
	@RequestMapping(value="/oneAudit",method=RequestMethod.POST)
	@ResponseBody
	public Object oneAudit(String userFlow,String roleId,String recFlow){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "审核成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(recFlow)){
			return ResultDataThrow("数据标识符为空");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		ResRec re =jswjwBiz.readResRec(recFlow);
		if(re == null){
			return ResultDataThrow("数据不存在");
        }
        String appMenu = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_app_menu_" + re.getOperUserFlow());
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(appMenu)) {
			return ResultDataThrow("无操作权限，请联系基地管理员！");
        }
		if(StringUtil.isNotBlank(re.getAuditStatusId())){
			return ResultDataThrow("数据已审核");
		}
		String time=DateUtil.getCurrDateTime();
		SysUser sysUser=jswjwBiz.readSysUser(userFlow);
		re.setAuditTime(time);
		re.setAuditUserFlow(sysUser.getUserFlow());
		re.setAuditUserName(sysUser.getUserName());
		re.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
		re.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
		int i=jswjwBiz.editResRec(re,sysUser,re.getRecTypeId());
		if (i ==0) {
			return ResultDataThrow("审核失败");
		}
		return resultMap;
	}
	/**
	 * o数据列表
	 * @param
	 * @param docFlow
	 * @param processFlow
	 * @param recType
     * @return
     */
	@RequestMapping(value="/resRecList",method=RequestMethod.POST)
	@ResponseBody
	public Object resRecList(String userFlow,String roleId,String docFlow,String processFlow,String recType,String biaoJi,Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(docFlow)){
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
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
//		ResDoctor doctor = jswjwBiz.readResDoctor(docFlow);
//		if(doctor==null||StringUtil.isBlank(doctor.getRotationFlow())){
//			model.addAttribute("resultId", "30204");
//			model.addAttribute("resultType", "该学员未设置轮转方案!");
//			return "res/jswjw/teacher/resRecList";
//		}
		Map<String, Object> processPerMap = jswjwBiz.getRecProgressIn(docFlow, processFlow,null,null);
//		resultMap.put("processPerMap", processPerMap);

		System.err.println(JSON.toJSON(processPerMap));
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
		PageHelper.startPage(pageIndex, pageSize);
		List<ResRec> recList=jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow,docFlow,recTypeId,biaoJi);
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		for(ResRec rec : recList){
			String recContent = rec.getRecContent();
			Map<String, Object> formDataMap = parseRecContent(recContent);
			formDataMap.put("dataFlow", rec.getRecFlow());
			formDataMap.put("auditId", rec.getAuditStatusId());
			formDataMap.put("operTime", DateUtil.transDate(rec.getOperTime()));
			dataList.add(formDataMap);
			System.err.println(JSON.toJSON(formDataMap));
		}
//		resultMap.put("dataList", dataList);
		resultMap.put("dataCount", PageHelper.total);
        List<ResRec> noAuditList = jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow, docFlow, recTypeId, com.pinde.core.common.GlobalConstant.FLAG_Y);
		int count=0;
		if(noAuditList!=null){
			count=noAuditList.size();
		}
		resultMap.put("notAuditNum",count);
		String finishkey = processFlow+recTypeId+"Finished";
		resultMap.put("finishNum",processPerMap!=null? processPerMap.get(finishkey):0);
		String reqkey = processFlow+recTypeId+"ReqNum";
		resultMap.put("reqNum",processPerMap!=null?processPerMap.get(reqkey):0);

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
	public Object resRecDeatil(String userFlow,String roleId,String recFlow,String deptFlow,String cataFlow,String recType){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow("用户角色ID为空");
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
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
//		ResRec rec = jswjwBiz.readResRecByDataType(recFlow, recType);
		ResRec rec = jswjwBiz.readResRec(recFlow);
		String recContent = rec.getRecContent();
		_inputList(userFlow, deptFlow, recType,cataFlow, resultMap);
		Map<String, Object> formDataMap = parseRecContent(recContent);
		formDataMap.put("auditId",rec.getAuditStatusId());
		resultMap.put("resultData", formDataMap);
        resultMap.put("isOther", com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
		return resultMap;
	}
	private void _inputList(String userFlow,String deptFlow,String dataType,String cataFlow ,Map<String,Object> resultMap){
		switch (dataType) {
			case "mr":
				break;
			case "disease":
				List<Map<String,Object>> diseaseDiagNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType, cataFlow);
				resultMap.put("diseaseDiagNameList", diseaseDiagNameList);
				break;
			case "skill":
				List<Map<String,Object>> skillOperNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType,cataFlow);
				resultMap.put("skillOperNameList", skillOperNameList);
				break;
			case "operation":
				List<Map<String,Object>> operationOperNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType,cataFlow);
				resultMap.put("operationOperNameList", operationOperNameList);
				break;
			case "activity":
				break;
			case "summary":
				break;
			default:
				break;
		}
	}
	public Map<String,Object> parseRecContent(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();;
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
							if (StringUtil.isNotBlank(element.attributeValue("id"))) {
								formDataMap.put(element.getName() + "_id", element.attributeValue("id"));
							}
							formDataMap.put(element.getName(), element.getText());
						}
					}
				}
			} catch (DocumentException e) {
                logger.error("", e);
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
	final static String Bgdfx = "20";final static String Jxsj = "21";final static String Sjys = "22";

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
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
		ResDoctor doctor=null;
		SysUser operUser=null;
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
//		resultMap.put("currUser",currUser);

		ResDoctorSchProcess p=iResDoctorProcessBiz.read(processFlow);
		if(StringUtil.isNotBlank(p.getSchStartDate())&&StringUtil.isNotBlank(p.getSchEndDate())) {
			resultMap.put("attendance",DateUtil.signDaysBetweenTowDate(p.getSchEndDate(),p.getSchStartDate())+1);
		}

		//是否审核过出科考核表，不管是不是退回过的

		String key ="jsres_"+currUser.getOrgFlow()+"_org_process_eval" ;
		String cfgv=jswjwBiz.getJsResCfgCode(key);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
			ResSchProcessExpress express=expressBiz.getExpressByRecTypeNoStatus(processFlow,recTypeId);
			if(express==null) {
				int c = iResDoctorProcessBiz.checkProcessEval(processFlow);
				if (c > 0) {
					return ResultDataThrow("请先完成此科室的月度考评");
				}
			}
		}
        String appMenu = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_app_menu_" + docFlow);
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(appMenu)) {
			return ResultDataThrow("无操作权限，请联系基地管理员！");
        }
		// 出科时培训数据必须审核
		String trainingAuditKey ="jsres_"+currUser.getOrgFlow()+"_org_outDept_trainingAudit" ;
		String trainingAudit = jswjwBiz.getJsResCfgCode(trainingAuditKey);
		if(GlobalConstant.FLAG_Y.equals(trainingAudit)){
			// 查询是否存在未审核培训数据
			int count = jswjwBiz.countNotAuditResRec(processFlow, docFlow);
			if(count > 0){
				return ResultDataThrow("该学员还有未审核培训数据");
			}
		}
		if(StringUtil.isNotBlank(docFlow)){
			doctor  = jswjwBiz.readResDoctor(docFlow);
//			resultMap.put("doctor", doctor);
			operUser  = jswjwBiz.readSysUser(docFlow);
//			resultMap.put("operUser",operUser);
		}
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
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
		//填写百分比限制
		JsresPowerCfg jsresPowerCfg1= jswjwBiz.readPowerCfg("out_filling_check_" + p.getOrgFlow());
		if(null != jsresPowerCfg1){
			resultMap.put("checkProcess",jsresPowerCfg1.getCfgValue());
		}else{
			resultMap.put("checkProcess","0");
		}
		JsresPowerCfg jsresPowerCfg= jswjwBiz.readPowerCfg("out_test_check_" + p.getOrgFlow());
		ResScore outScore = null;
		if(f) {
			outScore = jswjwTeacherBiz.readScoreByProcessFlow(processFlow);
//			resultMap.put("outScore", outScore);
			// 如果有出科成绩 出科试卷flow不为空则查询试卷对应的及格分
			if(null != outScore && StringUtil.isNotBlank(outScore.getPaperFlow())){
				// 查询对应试卷的及格分
				TestPaper testPaper = paperBiz.readTestPaper(outScore.getPaperFlow());
//				resultMap.put("testPaper", testPaper);
				resultMap.put("passScore", null == testPaper ? "" : null == testPaper.getPassScore() ? "" : testPaper.getPassScore());
				// 是否校验出科理论成绩
//				String theoreticalCfg = jswjwBiz.getJsResCfgCode("theoretical_qualified_flag_" + currUser.getOrgFlow());
//				model.addAttribute("theoreticalCfg", theoreticalCfg);
				//禅道201  修改
				//查询科室是否配置出科设置
//				if(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(jsresPowerCfg.getCfgValue())) {
//					//查询科室是否配置
//					JsresDeptConfig deptConfig = jswjwBiz.searchDeptCfg(p.getOrgFlow(), p.getSchDeptFlow());
//					String theoryScorePass = null;
//					if (null != deptConfig) {
//						theoryScorePass = deptConfig.getScorePass();
//					} else {
//						deptConfig = jswjwBiz.searchBaseDeptConfig(p.getOrgFlow());
//						theoryScorePass = deptConfig.getScorePass();
//					}
//					model.addAttribute("theoryScorePass", theoryScorePass);
//				}
			}
		}
//		resultMap.put("f",f);

		String theoryScorePass = "";
        String teacherWrite = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(jsresPowerCfg.getCfgValue())) {
			//查询科室是否配置
			JsresDeptConfig deptConfig = jswjwBiz.searchDeptCfg(p.getOrgFlow(), p.getSchDeptFlow());
			if (null != deptConfig) {
				theoryScorePass = deptConfig.getScorePass();
                if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(deptConfig.getTeacherWrite())) {
                    teacherWrite = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
			} else {
				deptConfig = jswjwBiz.searchBaseDeptConfig(p.getOrgFlow());
				theoryScorePass = deptConfig.getScorePass();
                if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(deptConfig.getTeacherWrite())) {
                    teacherWrite = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
			}
		}
        resultMap.put("teacherWrite", StringUtil.isBlank(teacherWrite) ? com.pinde.core.common.GlobalConstant.FLAG_N : teacherWrite);
		resultMap.put("theoryScorePass", theoryScorePass);

		SysDept dept=jswjwBiz.readSysDept(p.getDeptFlow());
		String cksh = jswjwBiz.getJsResCfgCode("jsres_"+dept.getOrgFlow()+"_org_cksh");
		if(StringUtil.isBlank(cksh)) {
            cksh = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
//		resultMap.put("cksh",cksh);
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
			int rkjy = 0;   int jnpx=0;
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
			String roleFlag = "teacher";
			resultMap.put("roleFlag", roleFlag);

            boolean showEdit = (roleFlag.equals("teacher") && (null == rec || StringUtil.isBlank(rec.getAuditStatusId())) || (roleFlag.equals("teacher") && (!cksh.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) || ((null == rec || StringUtil.isBlank(rec.getManagerAuditStatusId()))))))
                    || (roleFlag.equals("Head") || roleFlag.equals("Seretary")) && (cksh.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) || (null != rec && StringUtil.isNotBlank(rec.getManagerAuditStatusId())));
			boolean showSave = false;
			if(null == rec){
				if(roleFlag.equals("teacher")){
					showSave = true;
				}
			}else{
				if(roleFlag.equals("teacher") && StringUtil.isBlank(rec.getManagerAuditUserFlow())){
					showSave = true;
				}
				if(roleFlag.equals("Head") && StringUtil.isNotBlank(rec.getManagerAuditUserFlow())){
					showSave = true;
				}
				if(roleFlag.equals("Seretary") && StringUtil.isNotBlank(rec.getManagerAuditUserFlow())){
					showSave = true;
				}
			}

			boolean readonly = false;
			if(null == rec){
                if (roleFlag.equals("teacher") && cksh.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
					readonly = true;
				}
			}else{
				if(roleFlag.equals("teacher") && StringUtil.isNotBlank(rec.getManagerAuditUserFlow())){
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
			resultMap.put("headAuditStatusId","");
			resultMap.put("teacherName", null == rec ? currUser.getUserName() : formDataMap.get("teacherName"));
			resultMap.put("teacherDate", null == formDataMap ? DateUtil.getCurrDate() : null == formDataMap.get("teacherDate") ? DateUtil.getCurrDate() : formDataMap.get("teacherDate"));
			resultMap.put("directorName", null == formDataMap ? "" : null == formDataMap.get("directorName") ? "" : formDataMap.get("directorName"));
			resultMap.put("directorDate", null == formDataMap ? "" : null == formDataMap.get("directorDate") ? "" : formDataMap.get("directorDate"));

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
			cjhdMap = new HashMap<>();
			cjhdMap.put("inputId", "jnpx");
			cjhdMap.put("label", "技能培训");
			if(null == formDataMap || null == formDataMap.get("jnpx") || formDataMap.get("jnpx").equals("0")){
				cjhdMap.put("value",null == dataMap ? "0" : null == dataMap.get("jnpx") ? "0" : dataMap.get("jnpx"));
			}else{
				cjhdMap.put("value",formDataMap.get("jnpx"));
			}
			activityList.add(cjhdMap);
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
			String score = (null == formDataMap ? "": (null == formDataMap.get("theoreResult") ? "" : (String)formDataMap.get("theoreResult")));
			if(f && (null == formDataMap || StringUtil.isBlank((String) formDataMap.get("theoreResult")))){
				score = (null == outScore ? "" : (null == outScore.getTheoryScore() ? "" : outScore.getTheoryScore().toString()));
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

			if (null !=doctor){
				map = new HashMap<>();
				map.put("inputId", "teacherComment");
				map.put("label", "带教老师评价");
				map.put("value",null == rec ? "" : rec.getTeacherComment());

				//学员出科是否需要带教老师填写出科评价
				String departure = "jsres_departure_evaluation_" + doctor.getOrgFlow();
				JsresPowerCfg departureCfg = jsresPowerCfgMapper.selectByPrimaryKey(departure);
				if (departureCfg != null) {
					map.put("isSub", departureCfg.getCfgValue());	//是否必填   必填
				}else {
                    map.put("isSub", com.pinde.core.common.GlobalConstant.FLAG_N);
				}
				resultMapList.add(map);
			}

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

			map = new HashMap<>();
			map.put("inputId", "directorName");
			map.put("label", "主任签名");
			map.put("value", null == formDataMap ? "" : null == formDataMap.get("directorName") ? "" : formDataMap.get("directorName"));
			resultMapList.add(map);

			map = new HashMap<>();
			map.put("inputId", "directorDate");
			map.put("label", "日期");
			map.put("value", null == formDataMap ? "" : null == formDataMap.get("directorDate") ? "" : formDataMap.get("directorDate"));
			resultMapList.add(map);

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
	public Object saveRegistryForm(String formFileName,String recFlow,String operUserFlow,String roleFlag,String teacherComment,
								   String cksh,String processFlow,String recordFlow,String userFlow,HttpServletRequest req) throws UnsupportedEncodingException {
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
		if(!roleFlag.equals("teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}

		ResSchProcessExpress resRec=expressBiz.searResRecZhuZhi(formFileName,recFlow,operUserFlow,roleFlag,processFlow,recordFlow,userFlow,cksh,req);
		if (StringUtil.isNotBlank(teacherComment)){
			teacherComment=teacherComment.replaceAll("\n","\\\\n");
			teacherComment=teacherComment.replaceAll("\r","\\\\r");
			resRec.setTeacherComment(teacherComment);
		}
		int i=expressBiz.editResRec(resRec,user);
		if (i==0) {
			return ResultDataThrow("保存失败");
		}
		return resultMap;


	}
	//
	/**
	 * 迷你临床演练评估量化表
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mini_cex",method = {RequestMethod.POST})
	@ResponseBody
	public Object mini_cex(String userFlow,
								String docFlow,
								String processFlow,
								String recFlow,
								String roleId,
								String deptFlow) throws Exception{
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
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
//		resultMap.put("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
//			resultMap.put("formDataMap", formDataMap);
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(docFlow);

//		resultMap.put("doctor", doctor);
//		resultMap.put("formFileName", recTypeId);
//		resultMap.put("rec", rec);
		resultMap.put("roleFlag", "teacher");

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("inputId","formFileName");
		map.put("label","表单名称");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",recTypeId);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","schDeptFlow");
		map.put("label","轮转科室流水号");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",deptFlow);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","operUserFlow");
		map.put("label","学员流水号");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",docFlow);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","roleFlag");
		map.put("label","角色ID");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",roleId);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","recFlow");
		map.put("label","迷你临床演练评估量化表流水号");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",recFlow);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","processFlow");
		map.put("label","过程流水号");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",processFlow);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","ZongHe");
		map.put("label","综合");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("ZongHe") ? "" : formDataMap.get("ZongHe"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","studentName");
		map.put("label","学员姓名");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",doctor.getDoctorName());
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","grade");
		map.put("label","年级");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",doctor.getSessionNumber());
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","professional");
		map.put("label","专业");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",doctor.getTrainingSpeName());
		resultMapList.add(map);

		List<Map<String,String>> optionList = new ArrayList<>();
		Map<String,String> optionMap = null;

		map = new HashMap<>();
		map.put("inputId","studentType");
		map.put("label","学员类型");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? doctor.getDoctorCategoryName() : null == formDataMap.get("studentType") ? doctor.getDoctorCategoryName() : formDataMap.get("studentType"));

		optionMap = new HashMap<>();
		optionMap.put("optionId","实习生");
		optionMap.put("optionDesc","实习生");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","住院医师");
		optionMap.put("optionDesc","住院医师");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","研究生");
		optionMap.put("optionDesc","研究生");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","八年制二级学科");
		optionMap.put("optionDesc","八年制二级学科");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","进修生");
		optionMap.put("optionDesc","进修生");
		optionList.add(optionMap);
		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","teacherType");
		map.put("label","教师类型");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("teacherType") ? "" : formDataMap.get("teacherType"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","高级职称");
		optionMap.put("optionDesc","高级职称");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","中级职称");
		optionMap.put("optionDesc","中级职称");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","初级职称");
		optionMap.put("optionDesc","初级职称");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","assessmentDate");
		map.put("label","评估日期");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("assessmentDate") ? "" : formDataMap.get("assessmentDate"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","assessmentPlace");
		map.put("label","评估地点");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("assessmentPlace") ? "" : formDataMap.get("assessmentPlace"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","病房");
		optionMap.put("optionDesc","病房");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","门诊");
		optionMap.put("optionDesc","门诊");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","急诊");
		optionMap.put("optionDesc","急诊");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","手术室");
		optionMap.put("optionDesc","手术室");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","patientName");
		map.put("label","病人姓名");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("patientName") ? "" : formDataMap.get("patientName"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","age");
		map.put("label","年龄");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("age") ? "" : formDataMap.get("age"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","sex");
		map.put("label","性别");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("sex") ? "" : formDataMap.get("sex"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","男");
		optionMap.put("optionDesc","男");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","女");
		optionMap.put("optionDesc","女");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","patientSource");
		map.put("label","病人来源");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("patientSource") ? "" : formDataMap.get("patientSource"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","门诊病人");
		optionMap.put("optionDesc","门诊病人");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","住院病人");
		optionMap.put("optionDesc","住院病人");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","patientSourceType");
		map.put("label","病人类型");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("patientSourceType") ? "" : formDataMap.get("patientSourceType"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","新病人");
		optionMap.put("optionDesc","新病人");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","复诊病人");
		optionMap.put("optionDesc","复诊病人");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","diagnosis");
		map.put("label","诊断");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("diagnosis") ? "" : formDataMap.get("diagnosis"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","severity");
		map.put("label","病情严重情况");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("severity") ? "" : formDataMap.get("severity"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","轻");
		optionMap.put("optionDesc","轻");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","中");
		optionMap.put("optionDesc","中");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","重");
		optionMap.put("optionDesc","重");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","diagnosisKeynote");
		map.put("label","诊治重点");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("diagnosisKeynote") ? "" : formDataMap.get("diagnosisKeynote"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","病史采集");
		optionMap.put("optionDesc","病史采集");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","诊断");
		optionMap.put("optionDesc","诊断");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","治疗");
		optionMap.put("optionDesc","治疗");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","健康宣传");
		optionMap.put("optionDesc","健康宣传");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","medicalInterview");
		map.put("label","医疗面谈(称呼病人/自我介绍/病人陈述病史/适当提问/适当回应)");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("medicalInterview") ? "" : formDataMap.get("medicalInterview"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","physicalExamination");
		map.put("label","体格检查(告知检查目的/重点检查/操作正确/处理病人的不适)");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("physicalExamination") ? "" : formDataMap.get("physicalExamination"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","humanisticCare");
		map.put("label","人文关怀(尊重和关心/建立良好关系/满足病人适当的需求)");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("humanisticCare") ? "" : formDataMap.get("humanisticCare"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","clinicalJudgment");
		map.put("label","临床判断(归纳病史/判读检查结果/鉴别诊断/诊疗思维/预判治疗情况)");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("clinicalJudgment") ? "" : formDataMap.get("clinicalJudgment"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","communicationSkills");
		map.put("label","沟通技能(解释检查、治疗理由/解释检查和临床相关性/健康宣教和咨询)");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("communicationSkills") ? "" : formDataMap.get("communicationSkills"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","organization");
		map.put("label","组织效能(能按合理顺序处理，及时且适时，历练而简洁)");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("organization") ? "" : formDataMap.get("organization"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","holisticCare");
		map.put("label","整体关怀(综合评价受试者的表现)");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("holisticCare") ? "" : formDataMap.get("holisticCare"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","observationTime");
		map.put("label","评审观察时间");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("observationTime") ? "" : formDataMap.get("observationTime"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","feedbackTime");
		map.put("label","指导反馈时间");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("feedbackTime") ? "" : formDataMap.get("feedbackTime"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","degreeSatisfaction");
		map.put("label","教师对学员测评满意程度");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("degreeSatisfaction") ? "" : formDataMap.get("degreeSatisfaction"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","teacherComment");
		map.put("label","教师的评语");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("teacherComment") ? "" : formDataMap.get("teacherComment"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","studentDegreeSatisfaction");
		map.put("label","学生对此次测评满意程度");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("studentDegreeSatisfaction") ? "" : formDataMap.get("studentDegreeSatisfaction"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","teacherSign");
		map.put("label","教师签字");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value", null == rec ? currUser.getUserName() : StringUtil.isBlank(rec.getAuditStatusId()) ? currUser.getUserName() : formDataMap.get("teacherSign"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","studentSign");
		map.put("label","学生签字");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value", null == formDataMap ? "" : null == formDataMap.get("studentSign") ? "" : formDataMap.get("studentSign"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","studentSign1");
		map.put("label","学生签字");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value", null == formDataMap ? "" : null == formDataMap.get("studentSign") ? "" : formDataMap.get("studentSign"));
		resultMapList.add(map);

		resultMap.put("resultMapList",resultMapList);
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
					   String docFlow,
					   String processFlow,
					   String recFlow,
					   String roleId,
					   String deptFlow) throws Exception{
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
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
//		resultMap.put("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
//			resultMap.put("formDataMap", formDataMap);
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(docFlow);

//		resultMap.put("doctor", doctor);
//		resultMap.put("formFileName", recTypeId);
//		resultMap.put("rec", rec);
		resultMap.put("roleFlag", "teacher");

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		Map<String,Object> map = new HashMap<>();
		map.put("inputId","formFileName");
		map.put("label","表单名称");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",recTypeId);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","schDeptFlow");
		map.put("label","轮转科室流水号");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",deptFlow);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","recFlow");
		map.put("label","临床操作技能评估量化表流水号");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",recFlow);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","roleFlag");
		map.put("label","角色ID");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",roleId);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","operUserFlow");
		map.put("label","学员流水号");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",docFlow);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","processFlow");
		map.put("label","过程流水号");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",processFlow);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","ZongHe");
		map.put("label","综合");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("ZongHe") ? "" : formDataMap.get("ZongHe"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","studentName");
		map.put("label","学员姓名");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",doctor.getDoctorName());
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","grade");
		map.put("label","年级");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",doctor.getSessionNumber());
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","professional");
		map.put("label","专业");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",doctor.getTrainingSpeName());
		resultMapList.add(map);

		List<Map<String,String>> optionList = new ArrayList<>();
		Map<String,String> optionMap = null;

		map = new HashMap<>();
		map.put("inputId","studentType");
		map.put("label","学员类型");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("studentType") ? "" : formDataMap.get("studentType"));

		optionMap = new HashMap<>();
		optionMap.put("optionId","实习生");
		optionMap.put("optionDesc","实习生");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","住院医师");
		optionMap.put("optionDesc","住院医师");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","研究生");
		optionMap.put("optionDesc","研究生");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","八年制二级学科");
		optionMap.put("optionDesc","八年制二级学科");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","进修生");
		optionMap.put("optionDesc","进修生");
		optionList.add(optionMap);
		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","teacherType");
		map.put("label","教师类型");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("teacherType") ? "" : formDataMap.get("teacherType"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","高级职称");
		optionMap.put("optionDesc","高级职称");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","中级职称");
		optionMap.put("optionDesc","中级职称");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","初级职称");
		optionMap.put("optionDesc","初级职称");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","assessmentDate");
		map.put("label","评估日期");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("assessmentDate") ? "" : formDataMap.get("assessmentDate"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","assessmentPlace");
		map.put("label","评估地点");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("assessmentPlace") ? "" : formDataMap.get("assessmentPlace"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","病房");
		optionMap.put("optionDesc","病房");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","门诊");
		optionMap.put("optionDesc","门诊");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","急诊");
		optionMap.put("optionDesc","急诊");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","手术室");
		optionMap.put("optionDesc","手术室");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","patientName");
		map.put("label","病人姓名");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("patientName") ? "" : formDataMap.get("patientName"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","age");
		map.put("label","年龄");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("age") ? "" : formDataMap.get("age"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","sex");
		map.put("label","性别");
		map.put("required",false);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("sex") ? "" : formDataMap.get("sex"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","男");
		optionMap.put("optionDesc","男");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","女");
		optionMap.put("optionDesc","女");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","patientSource");
		map.put("label","病人来源");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("patientSource") ? "" : formDataMap.get("patientSource"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","门诊病人");
		optionMap.put("optionDesc","门诊病人");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","住院病人");
		optionMap.put("optionDesc","住院病人");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","patientSourceType");
		map.put("label","病人类型");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("patientSourceType") ? "" : formDataMap.get("patientSourceType"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","新病人");
		optionMap.put("optionDesc","新病人");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","复诊病人");
		optionMap.put("optionDesc","复诊病人");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","patientDiagnosis");
		map.put("label","病人主要诊断");
		map.put("required",false);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("patientDiagnosis") ? "" : formDataMap.get("patientDiagnosis"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","operatingSkills");
		map.put("label","操作技能");
		map.put("required",false);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("operatingSkills") ? "" : formDataMap.get("operatingSkills"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","studentSkillNum");
		map.put("label","评估前学员执行临床技能操作例数");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("studentSkillNum") ? "" : formDataMap.get("studentSkillNum"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","0");
		optionMap.put("optionDesc","0");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","1-3");
		optionMap.put("optionDesc","1-3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","skillComplexDegree");
		map.put("label","技能复杂程度");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("skillComplexDegree") ? "" : formDataMap.get("skillComplexDegree"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","低度");
		optionMap.put("optionDesc","低度");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","中度");
		optionMap.put("optionDesc","中度");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","高度");
		optionMap.put("optionDesc","高度");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","skillLevel");
		map.put("label","对临床技能适应证、相关解剖结构的了解及操作步骤的熟练程度");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("skillLevel") ? "" : formDataMap.get("skillLevel"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","consentForm");
		map.put("label","能详细告知病人并取得同意书");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("consentForm") ? "" : formDataMap.get("consentForm"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","readyToWork");
		map.put("label","执行临床操作前的准备工作");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("readyToWork") ? "" : formDataMap.get("readyToWork"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","painAndStabilization");
		map.put("label","能给予病人适当的止痛和镇定");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("painAndStabilization") ? "" : formDataMap.get("painAndStabilization"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","SkillAbility");
		map.put("label","执行临床操作的技术能力");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("SkillAbility") ? "" : formDataMap.get("SkillAbility"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","asepticTechnique");
		map.put("label","无菌技术");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("asepticTechnique") ? "" : formDataMap.get("asepticTechnique"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","seekAssistance");
		map.put("label","能视需要寻求协助");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("seekAssistance") ? "" : formDataMap.get("seekAssistance"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","relatedDisposal");
		map.put("label","执行临床操作后的相关处置");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("relatedDisposal") ? "" : formDataMap.get("relatedDisposal"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","communicationSkills");
		map.put("label","与病人沟通的技巧");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("communicationSkills") ? "" : formDataMap.get("communicationSkills"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","feelProfessionalDegree");
		map.put("label","能否顾忌病人感受和专业程度");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("feelProfessionalDegree") ? "" : formDataMap.get("feelProfessionalDegree"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","overallPerformance");
		map.put("label","执行临床操作技能的整体表现");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("overallPerformance") ? "" : formDataMap.get("overallPerformance"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","observationTime");
		map.put("label","评审观察时间");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("observationTime") ? "" : formDataMap.get("observationTime"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","feedbackTime");
		map.put("label","指导反馈时间");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("feedbackTime") ? "" : formDataMap.get("feedbackTime"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","degreeSatisfaction");
		map.put("label","教师对学员测评满意程度");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("degreeSatisfaction") ? "" : formDataMap.get("degreeSatisfaction"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","teacherComment");
		map.put("label","教师的评语");
		map.put("required",false);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("teacherComment") ? "" : formDataMap.get("teacherComment"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","studentDegreeSatisfaction");
		map.put("label","学生对此次测评满意程度");
		map.put("required",true);
		map.put("inputType","checkbox");
		map.put("isHidden",false);
		map.put("value",null == formDataMap ? "" : null == formDataMap.get("studentDegreeSatisfaction") ? "" : formDataMap.get("studentDegreeSatisfaction"));

		optionList = new ArrayList<>();
		optionMap = new HashMap<>();
		optionMap.put("optionId","1");
		optionMap.put("optionDesc","1");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","2");
		optionMap.put("optionDesc","2");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","3");
		optionMap.put("optionDesc","3");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","4");
		optionMap.put("optionDesc","4");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","5");
		optionMap.put("optionDesc","5");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","6");
		optionMap.put("optionDesc","6");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","7");
		optionMap.put("optionDesc","7");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","8");
		optionMap.put("optionDesc","8");
		optionList.add(optionMap);

		optionMap = new HashMap<>();
		optionMap.put("optionId","9");
		optionMap.put("optionDesc","9");
		optionList.add(optionMap);

		map.put("optionList",optionList);
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","teacherSign");
		map.put("label","教师签字");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value", null == rec ? currUser.getUserName() : StringUtil.isBlank(rec.getAuditStatusId()) ? currUser.getUserName() : formDataMap.get("teacherSign"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","studentSign");
		map.put("label","学生签字");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",true);
		map.put("value", null == formDataMap ? "" : null == formDataMap.get("studentSign") ? "" : formDataMap.get("studentSign"));
		resultMapList.add(map);

		map = new HashMap<>();
		map.put("inputId","studentSign1");
		map.put("label","学生签字");
		map.put("required",true);
		map.put("inputType","text");
		map.put("isHidden",false);
		map.put("value", null == formDataMap ? "" : null == formDataMap.get("studentSign") ? "" : formDataMap.get("studentSign"));
		resultMapList.add(map);

		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/userList"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object userList(String userFlow, String studentName, String deptFlow,String doctorTypeId,String trainingTypeId,String trainingSpeId, String sessionNumber,  String dataType,Integer pageIndex,Integer pageSize,String biaoJi) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
//		if (StringUtil.isBlank(deptFlow)) {
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "科室标识符为空");
//			return "res/jswjw/teacher/userList";
//		}
		if (StringUtil.isBlank(dataType)&&StringUtil.isNotBlank(biaoJi)) {
			return ResultDataThrow("dataType为空");
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

		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
//		schArrangeResultMap.put("schDeptFlow", schDeptFlow);
		schArrangeResultMap.put("teacherUserFlow", userFlow);
		schArrangeResultMap.put("userName", studentName);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("dataType", dataType);
		schArrangeResultMap.put("biaoJi", biaoJi);

		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwTeacherBiz.schDoctorSchProcessUserList(schArrangeResultMap);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		resultMap.put("uploadBaseUrl", uploadBaseUrl);
		resultMap.put("list", resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);
		return resultMap;
	}

	@RequestMapping(value = {"/activityList"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object activityList(String userFlow
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
		if(!roleId.equals("Teacher")){
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
		param.put("roleFlag","teach");
		param.put("userFlow",userinfo.getUserFlow());
//		resultMap.put("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		param.put("orgFlow", userinfo.getOrgFlow());
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		List<SysUserRole> userRoleList = evalAppBiz.getSysUserRole(userFlow); //获取该用户下的所有角色信息
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
		resultMap.put("dataCount", PageHelper.total);
		resultMap.put("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		String key="jsres_"+userinfo.getOrgFlow()+"_org_activity_code_type";
		String cfgv=jswjwBiz.getJsResCfgCode(key);
		resultMap.put("codeType", cfgv);
		resultMap.put("activityCodeTime", "15");

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
				map.put("evalScore",null == info.get("evalScore") ? 0 : Integer.valueOf(info.get("evalScore").toString()));
				map.put("userName",info.get("userName"));
				map.put("deptName",info.get("deptName"));
				map.put("startTime",info.get("startTime"));
				map.put("endTime",info.get("endTime"));
				map.put("fileFlow",info.get("fileFlow"));
				map.put("fileName",info.get("fileName"));
				map.put("isRegiest",info.get("isRegiest"));
				map.put("isScan",info.get("isScan"));
				map.put("isScan2",info.get("isScan2"));
				map.put("isScan",info.get("isScan"));
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
		if(StringUtil.isBlank(roleId)) {
			return ResultDataThrow("用户角色ID为空");
		}
		if(StringUtil.isBlank(activityFlow)){
			return ResultDataThrow("活动标识符为空");
		}
		if(!roleId.equals("Teacher")){
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
		if(!roleId.equals("Teacher")){
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
		}
		return resultMap;
	}

	@RequestMapping(value = {"/showActivity"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object showActivity(String userFlow,String activityFlow,String roleId) throws DocumentException {
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
		if(!roleId.equals("Teacher")){
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
		if(null == activity.get("opinion")){
			activity.put("opinion","");
		}
		//解决序列化问题，删除不可序列化的属性
		activity.remove("FILE_FLOWS");

		resultMap.put("activity", activity);
        resultMap.put("isUpload", activity.get("speakerFlow").equals(userinfo.getUserFlow()) && StringUtil.isNotBlank((String) activity.get("activityFlow")) ? com.pinde.core.common.GlobalConstant.FLAG_Y : com.pinde.core.common.GlobalConstant.FLAG_N);
//		resultMap.put("user",userinfo);
//		List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow,"");
//		if(!(results!=null&&results.size()>0))
//		{
//			List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
//			resultMap.put("depts",depts);
//			if(activity!=null) {
//				if (activity.containsKey("activityStatus") && "pass".equals(activity.get("activityStatus"))) {
//					return resultMap;
//				} else {
//					return resultMap;
//				}
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
		return resultMap;
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
		if(!roleId.equals("Teacher")){
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
				map.put("evalScore", null == info.get("evalScore") ? 0 : Integer.valueOf(info.get("evalScore").toString()));
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);

		return resultMap;
	}

	@RequestMapping(value = {"/activityEvalList"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object activityEvalList(String userFlow,String activityFlow,String searchStr,
								   Integer pageIndex,
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
		if(!roleId.equals("Teacher")){
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
		if(results!=null)
		{
			for(Map<String,Object> r:results)
			{
				List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(String.valueOf(r.get("resultFlow")));
				if(evals!=null)
				{
					for(TeachingActivityEval e:evals)
					{
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
				map.put("evalScore", null == m.get("evalScore") ? "" : Integer.valueOf(m.get("evalScore").toString()));
				map.put("isEffective",m.get("isEffective"));
				if(null != targets && targets.size()>0){
					List<Map<String,Object>> targetList = new ArrayList<>();
					for (Map<String,Object> t:targets) {
						String key = (String)m.get("resultFlow") + (String)t.get("targetFlow");
						Map<String,Object> evalMap = new HashMap<>();
						evalMap.put("targetName",t.get("targetName"));
						evalMap.put("isText",t.get("isText"));
						evalMap.put("evalScore", null == evalDetailMap ? null : null == evalDetailMap.get(key) ? null : Integer.valueOf(evalDetailMap.get(key).toString()));
						evalMap.put("evalRemarks", null == evalRemarksMap ? null : null == evalRemarksMap.get(key) ? "" : evalRemarksMap.get(key).toString());
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
		if(!roleId.equals("Teacher")){
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
			return ResultDataThrow( "resultFlow为空");
		}
		if(StringUtil.isBlank(isEffective)){
			return ResultDataThrow( "isEffective为空");
		}
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isEffective) && !com.pinde.core.common.GlobalConstant.FLAG_N.equals(isEffective))
		{
			return ResultDataThrow( "isEffective只能是Y或N");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow( "用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow( "用户不存在");
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			return ResultDataThrow( "活动信息不存在");
		}
		TeachingActivityResult info=activityBiz.readResult(resultFlow);
		if(info==null) {
			return ResultDataThrow( "学员参加活动结果信息不存在，请刷新列表页面！");
		}
		info.setIsEffective(isEffective);
		int c=activityBiz.saveResult(info,userFlow);
		if(c==0){
			return ResultDataThrow( "修改失败");
		}
		return resultMap;
	}

	@RequestMapping(value = {"/addActivity"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object addActivity(String userFlow,String roleId) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)) {
			return ResultDataThrow("用户角色ID为空");
		}
		if(!roleId.equals("Teacher")){
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

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(depts!=null) {
			for(SysDept d:depts) {
				Map<String,Object> map = new HashMap<>();
				map.put("deptFlow",d.getDeptFlow());
				map.put("deptName",d.getDeptName());
				resultMapList.add(map);
			}
		}
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
			return ResultDataThrow( "用户标识符为空");
		}
		if(StringUtil.isBlank(roleId)){
			return ResultDataThrow( "用户角色ID为空");
		}
		if(StringUtil.isBlank(activity.getSpeakerFlow())){
			return ResultDataThrow( "主讲人标识符为空");
		}
		if(StringUtil.isBlank(activity.getDeptFlow())){
			return ResultDataThrow( "科室标识符为空");
		}
		if(StringUtil.isBlank(activity.getActivityTypeId())){
			return ResultDataThrow( "活动形式为空");
		}
		if(StringUtil.isBlank(activity.getActivityName())){
			return ResultDataThrow( "活动名称为空");
		}
		if(StringUtil.isBlank(activity.getActivityAddress())){
			return ResultDataThrow( "活动地点为空");
		}
		if(StringUtil.isBlank(activity.getSpeakerPhone())){
			return ResultDataThrow( "联系方式为空");
		}
		if(StringUtil.isBlank(activity.getStartTime())){
			return ResultDataThrow( "活动开始时间为空");
		}

		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getStartTime());
		} catch (Exception e) {
			return ResultDataThrow( "活动开始时间格式有误");
		}
		if(StringUtil.isBlank(activity.getEndTime())){
			return ResultDataThrow( "活动结束时间为空");
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getEndTime());
		} catch (Exception e) {
			return ResultDataThrow( "活动结束时间格式有误");
		}
		if (StringUtil.isBlank(activity.getActivityFlow())){
			String currDateTime = DateUtil.getCurrDateTime2();
			if (currDateTime.compareTo(activity.getStartTime())>0){
				return ResultDataThrow( "开始时间不得小于当前时间");
			}
		}
		if(activity.getStartTime().compareTo(activity.getEndTime())>=0)
		{
			return ResultDataThrow( "开始时间不得大于等于结束时间");
		}
//		if(StringUtil.isBlank(activity.getRealitySpeaker())){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "实际主讲人为空");
//			return "res/jswjw/success";
//		}
		if(StringUtil.isBlank(activity.getActivityRemark())){
			return ResultDataThrow( "活动简介为空");
		}
		if(!roleId.equals("Teacher")){
			return ResultDataThrow( "用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow( "用户不存在");
		}
		resultMap.put("user",userinfo);
//		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrg(userinfo.getOrgFlow());
		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrgNew(activity.getActivityTypeId(),userinfo.getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			return ResultDataThrow( "暂未配置活动指标，请联系基地管理员");
		}
		//校验活动时间是否重复
		int count=checkTime(activity);
		if(count>0)
		{
			return ResultDataThrow( "该主讲人在时间段内已开展其他活动");
		}
		if (StringUtil.isNotBlank(recordFlow)){
			activity.setImageUrl(imageXML);
		}
		if(StringUtil.isNotBlank(activity.getActivityTypeId()))
		{
			activity.setActivityTypeName(ActivityTypeEnum.getNameById(activity.getActivityTypeId()));
		}
		activity.setOrgFlow(userinfo.getOrgFlow());
		int c= activityBiz.addActivityInfo2(activity,userinfo,targets, null,recordFlow);
		if(c==0) {
			 return ResultDataThrow(  "保存失败");
		}else if(c==-1){
			return ResultDataThrow(  "该角色无法发起教学活动，请联系基地管理员！");
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
		if(!roleId.equals("Teacher")){
			return ResultDataThrow("用户角色ID与角色不符");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}
		if (StringUtil.isNotBlank(recordFlow) && StringUtil.isBlank(activityFlow)){
			if (StringUtil.isNotBlank(imageXML)){
				List<Map<String, String>> imageList=activityBiz.parseImageXml(imageXML);
				resultMap.put("imageList", imageList);
			}
		}else {
			if(StringUtil.isBlank(activityFlow)){
				return ResultDataThrow("活动标识符为空");
			}
			Map<String, Object> activity=activityBiz.readActivity(activityFlow);
            if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
			{
				return ResultDataThrow("活动信息不存在");
			}
			if(activity!=null)
			{
				String content= (String) activity.get("imageUrl");
				List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
				resultMap.put("imageList", imageList);
			}
		}
		return resultMap;
	}

	@RequestMapping(value={"/addActivityImage"},method={RequestMethod.POST})
	@ResponseBody
	public Object addActivityImage(ActivityImageFileForm form, HttpServletRequest request,HttpServletResponse response,String recordFlow,String imageXML){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isEmpty(form.getUserFlow())){
			return ResultDataThrow("用户标识符为空");
		}
	/*	if(StringUtil.isEmpty(form.getActivityFlow())) {
			return ResultDataThrow("教学活动标识符为空");
		}*/
		if(StringUtil.isEmpty(form.getFileName())){
			return ResultDataThrow("文件名为空");
		}
		if(StringUtil.isBlank(form.getImageContent())){
			return ResultDataThrow("上传文件不能为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(form.getUserFlow());
		if (userinfo == null) {
			return ResultDataThrow("用户不存在");
		}

	/*	Map<String, Object> activity=activityBiz.readActivity(form.getActivityFlow());
		if(activity==null||!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			return ResultDataThrow("活动信息不存在");
		}*/
//		jswjwBiz.addActivityImage2(form,userinfo);
		String imageInfo = jswjwBiz.addActivityImage3(form, userinfo, imageXML, recordFlow);
		resultMap.put("imageXML",imageInfo);
		return resultMap;
	}

	@RequestMapping(value={"/deleteActivityImage"},method={RequestMethod.POST})
	@ResponseBody
	public Object deleteActivityImage(String userFlow,String activityFlow,String imageFlow, HttpServletRequest request,HttpServletResponse response,String recordFlow,String imageXML) throws DocumentException {
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
		}else{
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

	@RequestMapping(value = {"/addActivitykJ"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object addActivitykJ(ImageFileForm form, HttpServletRequest request, HttpServletResponse response,String recordFlow,String activityFlow) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isNotBlank(recordFlow)) {
			form.setRecordFlow(recordFlow);
		}
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
            logger.error("", e);
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

	@RequestMapping(value = {"/addActivityFile"}, method = {RequestMethod.POST})
	@ResponseBody
	public Object addActivityFile(ImageFileForm form, HttpServletRequest request, HttpServletResponse response,String recordFlow,String activityFlow) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if (StringUtil.isBlank(form.getUserFlow())) {
			return ResultDataThrow("用户标识符为空");
		}
		if (StringUtil.isNotBlank(recordFlow)){
			form.setRecordFlow(recordFlow);
		}
		if (StringUtil.isNotBlank(activityFlow)) {
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
            logger.error("", e);
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



	@RequestMapping(value={"/userCenter"},method={RequestMethod.POST})
	@ResponseBody
	public Object userCenter(String userFlow, HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "success");

		List<String> list = jswjwBiz.studentList(userFlow,null);

		if(list!=null){
			resultMap.put("teachNumCount", list.size());
		}

		List<String> rotatinglist = jswjwBiz.studentList(userFlow,"true");

		if(rotatinglist!=null){
			resultMap.put("teachNum", rotatinglist.size());
		}

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

