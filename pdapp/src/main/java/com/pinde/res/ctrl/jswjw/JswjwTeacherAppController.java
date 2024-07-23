package com.pinde.res.ctrl.jswjw;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.app.common.PasswordUtil;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.eval.IEvalAppBiz;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IJswjwTeacherBiz;
import com.pinde.res.biz.jswjw.IResDoctorProcessBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.enums.jswjw.JsRecDocTypeEnum;
import com.pinde.res.enums.jswjw.TrainCategoryEnum;
import com.pinde.res.enums.stdp.*;
import com.pinde.res.model.jswjw.mo.FromItem;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.sci.dao.base.JsresPowerCfgMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.PasswordHelper;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/jswjw/teacher")
public class JswjwTeacherAppController{    
	private static Logger logger = LoggerFactory.getLogger(JswjwTeacherAppController.class);
	
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		//临时休眠
//		try {
//			Thread.sleep(Integer.MAX_VALUE);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		return "res/jswjw/500";
    }
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

	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/jswjw/teacher/version";
	}
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/jswjw/teacher/test";
	}
	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String deptFlow,String cataFlow,String funcTypeId , String funcFlow , String dataFlow , HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("deptFlow", deptFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/jswjw/teacher/test";
	}
	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.findByUserCode(userCode);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
		}else{
			String passwd = userinfo.getUserPasswd();
			String userFlow = userinfo.getUserFlow();
			
			//验证密码是否正确或者是否使用超级密码
			if(!PasswordUtil.isRootPass(userPasswd) &&
					!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userFlow,userPasswd))){
				model.addAttribute("resultId", "3010104");
				model.addAttribute("resultType", "用户名或密码错误");
				return "res/jswjw/teacher/login";
			}
			
			//验证用户是否锁定,锁定用户不能登录
			String userStatus = userinfo.getStatusId();
			if(UserStatusEnum.SysLocked.getId().equals(userStatus)){
				model.addAttribute("resultId", "3010105");
				model.addAttribute("resultType", "该用户已被锁定");
				return "res/jswjw/teacher/login";
			}
			if(UserStatusEnum.Locked.getId().equals(userStatus)){
				model.addAttribute("resultId", "3010105");
				model.addAttribute("resultType", "该用户已被停用");
				return "res/jswjw/teacher/login";
			}
			
			//验证用户是否有角色
			List<SysUserRole> userRoles = jswjwBiz.getSysUserRole(userFlow);
			if(userRoles==null || userRoles.isEmpty()){
				model.addAttribute("resultId", "3010106");
				model.addAttribute("resultType", "用户未赋权");
				return "res/jswjw/teacher/login";
			}
			
			boolean isTeacher = false;
			//获取当前配置的老师角色
			String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
			
			//获取最先匹配到的角色,认定该用户的角色为匹配到的角色
			for(SysUserRole sur : userRoles){
				String ur = sur.getRoleFlow();
				if(teacherRole.equals(ur)){
					isTeacher = true;
					model.addAttribute("roleId","Teacher");
					model.addAttribute("roleName","老师");
					break;
				}
			}
			if(!isTeacher){
				model.addAttribute("resultId", "3010106");
				model.addAttribute("resultType", "用户未赋权");
				return "res/jswjw/teacher/login";
			}else {
				model.addAttribute("userinfo",userinfo);
			}
		}
		return "res/jswjw/teacher/login";
	}
	@RequestMapping(value={"/doctorList"},method={RequestMethod.GET})
	public String doctorList(String userFlow,
			String searchData,
			Integer pageIndex,
			Integer pageSize,
			HttpServletRequest request,
			Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3030101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/doctorList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "res/jswjw/teacher/doctorList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jswjw/teacher/doctorList";
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
				e.printStackTrace();
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
		model.addAttribute("doctorMapList",doctorMapList);
		
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		
		//数据数量
		model.addAttribute("dataCount", PageHelper.total);
		
		return "res/jswjw/teacher/doctorList";
	}
	@RequestMapping(value={"/funcList"},method={RequestMethod.GET})
	public String funcList(String userFlow,String doctorFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3030201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/funcList";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3030202");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/teacher/funcList";
		}
		return "res/jswjw/teacher/funcList";
	}
	@RequestMapping(value={"/dataList"},method={RequestMethod.GET})
	public String dataList(String userFlow,String doctorFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3030201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/funcList";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3030202");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/teacher/funcList";
		}
		return "res/jswjw/teacher/dataList";
	}
	@RequestMapping(value={"/auditData"},method={RequestMethod.GET})
	public String auditData(String userFlow,String doctorFlow,String dataFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "审核成功");
		
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3030201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/funcList";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3030202");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/teacher/funcList";
		}
		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "3030202");
			model.addAttribute("resultType", "数据标识符为空");
			return "res/jswjw/teacher/funcList";
		}
		
		jswjwBiz.auditDate(userFlow,dataFlow);
		return "res/jswjw/teacher/auditData";
	}
	
	@RequestMapping(value={"/auditAllData"},method={RequestMethod.GET})
	public String auditAllData(String userFlow,String doctorFlow,String dataFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "审核成功");
		return "res/jswjw/teacher/auditAllData";
	}
	@RequestMapping(value={"/viewSummary"},method={RequestMethod.GET})
	public String viewSummary(String userFlow,String doctorFlow,String subDeptFlow,String dataFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3030201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/viewSummary";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3030202");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/teacher/viewSummary";
		}
		if(StringUtil.isEmpty(subDeptFlow)){
			model.addAttribute("resultId", "3030202");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/jswjw/teacher/viewSummary";
		}
		return "res/jswjw/teacher/viewSummary";
	}
	@RequestMapping(value={"/saveSummary"},method={RequestMethod.POST})
	public String saveSummary(String userFlow,String doctorFlow,String subDeptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3030201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/saveSummary";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3030202");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/teacher/saveSummary";
		}
		if(StringUtil.isEmpty(subDeptFlow)){
			model.addAttribute("resultId", "3030202");
			model.addAttribute("resultType", "轮转科室标识符为空");
			return "res/jswjw/teacher/saveSummary";
		}
		
		Map<String,Object> paramMap = new HashMap<String,Object>();
		_putAll(paramMap, request);
		
		jswjwBiz.saveSummary(userFlow,doctorFlow,subDeptFlow,paramMap);
		
		return "res/jswjw/teacher/saveSummary";
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

	@RequestMapping(value = {"/aboutUs"},method = {RequestMethod.GET})
	public String aboutUs(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/aboutUs";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/aboutUs";
		}
		model.addAttribute("userinfo",userinfo);
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/about/about.jsp";
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/about/about2.jsp";
		model.addAttribute("imgurl",hostUrl);
		model.addAttribute("androidimgurl",androidimgurl);

		return "res/jswjw/teacher/aboutUs";
	}
	@RequestMapping(value = {"/index"},method = {RequestMethod.POST})
	public String index(String userFlow,String roleId,Model model,String deptFlow)
	{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/index";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/index";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/index";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/index";
		}
		userinfo.setUserName(userinfo.getUserName()+"老师");
		model.addAttribute("userinfo",userinfo);
//		if(StringUtil.isBlank(deptFlow))
//		{
//			deptFlow=userinfo.getDeptFlow();
//		}
		//int noAuditNumber=jswjwTeacherBiz.schDoctorSchProcessWaitingExamineQuery(userinfo.getUserFlow());//待审核数据
		int currStudentHe=jswjwTeacherBiz.schDoctorSchProcessDistinctQuery(userinfo.getUserFlow(),"");//轮转学员数为轮转时间在计划时间的学员数
		int studentNum=jswjwTeacherBiz.schDoctorSchProcessTeacherQuery(userinfo.getUserFlow(),"");//所有已出科学员数量

		model.addAttribute("studentNum",studentNum);
		model.addAttribute("isCurrent",currStudentHe);
		model.addAttribute("noAuditNumber","");

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
			model.addAttribute("roles",roles);
		}
		model.addAttribute("trainingTypes", TrainCategoryEnum.values());
		model.addAttribute("doctorTypes", JsRecDocTypeEnum.values());

		HashMap<String,Object> dictMap=new HashMap<>();
		dictMap.put(TrainCategoryEnum.DoctorTrainingSpe.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.DoctorTrainingSpe.getId()));
		dictMap.put(TrainCategoryEnum.WMFirst.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.WMFirst.getId()));
		dictMap.put(TrainCategoryEnum.WMSecond.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.WMSecond.getId()));
		dictMap.put(TrainCategoryEnum.AssiGeneral.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.AssiGeneral.getId()));
		model.addAttribute("dictMap", dictMap);

		List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgNotRead("", GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
		if(infos!=null)
		{
			model.addAttribute("hasNotReadInfo",infos.size());
		}else{
			model.addAttribute("hasNotReadInfo",0);
		}
        // 查询师资权限
        jswjwBiz.getTeacherAuthorityInfo(model,userFlow,userinfo.getOrgFlow());
		return "res/jswjw/teacher/index";
	}

	/**
	 * 科教通知
	 *
	 * @return
	 */
	@RequestMapping(value = {"/getSysNotice"}, method = {RequestMethod.POST})
	public String getSysNotice(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request,Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/sysNotices/getSysNotice";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/sysNotices/getSysNotice";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/sysNotices/getSysNotice";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/sysNotices/getSysNotice";
		}
		String orgFlow="";
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgBeforeDate(orgFlow,null, GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow,null);
		model.addAttribute("infoList",infos);

		Map<String,Object> isReadMap=new HashMap<>();
		if(infos!=null&&infos.size()>0)
		{
			for(Map<String,String> info:infos)
			{
				ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(info.get("infoFlow"),userFlow);
				isReadMap.put(info.get("infoFlow"),resReadInfo);
			}
		}
		model.addAttribute("isReadMap", isReadMap);
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/sysNotices/getSysNotice";
	}

	@RequestMapping(value={"/sysNoticeDetail"})
	public String sysNoticeDetail(String userFlow,String infoFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/sysNotices/sysNoticeDetail";
		}
		if(StringUtil.isEmpty(infoFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态标识符为空");
			return "res/jswjw/sysNotices/sysNoticeDetail";
		}
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态不存在，请刷新列表页面");
			return "res/jswjw/sysNotices/sysNoticeDetail";
		}
		ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(infoFlow,userFlow);
		if(resReadInfo==null)
		{
			resReadInfo=new ResReadInfo();
			resReadInfo.setInfoFlow(infoFlow);
			resReadInfo.setUserFlow(userFlow);
			inxInfoBiz.saveReadInfo(userFlow,resReadInfo);
		}
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/showSysNotice.jsp?recordFlow="+infoFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/showSysNotice2.jsp?recordFlow="+infoFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/jswjw/sysNotices/sysNoticeDetail";
	}
	/**
	 * 数据审核学员列表
	 * @param userFlow
	 * @param roleId
	 * @param studentName
	 * @param pageIndex
	 * @param pageSize
	 * @param model
     * @param biaoJi
     * @return
     */
	@RequestMapping(value = {"/studentList"},method = {RequestMethod.GET})
	public String studentList(String userFlow,String roleId,String deptFlow,String doctorFlow,String studentName,String dataType,Integer pageIndex,Integer pageSize,Model model,String biaoJi) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/studentList";
		}
		if (StringUtil.isBlank(roleId)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/studentList";
		}
		if (StringUtil.isBlank(doctorFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/teacher/studentList";
		}
		if (!roleId.equals("Teacher")) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/studentList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/studentList";
		}

//		if(pageIndex==null){
//			model.addAttribute("resultId", "30905");
//			model.addAttribute("resultType", "当前页码为空");
//			return "res/jswjw/teacher/studentList";
//		}
//		if(pageSize==null){
//			model.addAttribute("resultId", "30906");
//			model.addAttribute("resultType", "每页条数为空");
//			return "res/jswjw/teacher/studentList";
//		}
//		if(StringUtil.isBlank(deptFlow))
//		{
//			deptFlow=userinfo.getDeptFlow();
//		}
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
		if (StringUtil.isBlank(dataType) || dataType.equals("fiveData")) {
//				PageHelper.startPage(pageIndex, pageSize);
			resDoctorSchProcess = jswjwTeacherBiz.schDoctorSchProcessQuery(schArrangeResultMap);
		} else if ("dayAttend".equals(dataType)) {
//				PageHelper.startPage(pageIndex, pageSize);
			resDoctorSchProcess = jswjwTeacherBiz.schDoctorSchProcessDayAttend(schArrangeResultMap);
		} else if ("docEval".equals(dataType)) {
//				PageHelper.startPage(pageIndex, pageSize);
			resDoctorSchProcess = jswjwTeacherBiz.schDoctorSchProcessEvalList(schArrangeResultMap);
		} else if (dataType.equals("skill") || dataType.equals("after")) {
			//		String	recTypeId=getRecTypeId(dataType);
			schArrangeResultMap.put("recTypeId", dataType);
			//		PageHelper.startPage(pageIndex, pageSize);
			resDoctorSchProcess = jswjwTeacherBiz.schDoctorSchProcessInfoQuery(schArrangeResultMap);
		} else {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "dataType错误");
			return "res/jswjw/teacher/studentList";
		}

		boolean f = false;
		f = jswjwBiz.getCkkPower(doctorFlow);
		Map<String, Object> schRotationDeptMap = new HashMap<String, Object>();
		Map<String, Object> readSchRotationGroupMap = new HashMap<String, Object>();
		Map<String, Object> resRecMap = new HashMap<String, Object>();
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
				recTypeIds.add(ResRecTypeEnum.DOPS.getId());
				recTypeIds.add(ResRecTypeEnum.Mini_CEX.getId());
				recTypeIds.add(ResRecTypeEnum.AfterEvaluation.getId());
				List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"),  recTypeIds);

				if (expressList != null && expressList.size() > 0) {
					for (ResSchProcessExpress express : expressList) {

						if (ResRecTypeEnum.AfterEvaluation.getId().equals(express.getRecTypeId())) {
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
		model.addAttribute("resRecMap", resRecMap);
		model.addAttribute("resRecCountMap", resRecCountMap);
		model.addAttribute("schScoreMap", schScoreMap);
		model.addAttribute("schRotationDeptMap", schRotationDeptMap);
		model.addAttribute("readSchRotationGroupMap", readSchRotationGroupMap);
		model.addAttribute("list", resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		model.addAttribute("biaoJi", biaoJi);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		return "res/jswjw/teacher/studentList";
	}

	@RequestMapping(value="/fiveDataNoAudit",method=RequestMethod.GET)
	public String fiveDataNoAudit(String userFlow,String roleId,String docFlow,String processFlow,String schResultFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/fiveDataNoAudit";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/fiveDataNoAudit";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/teacher/fiveDataNoAudit";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/teacher/fiveDataNoAudit";
		}
		if(StringUtil.isBlank(schResultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/jswjw/teacher/fiveDataNoAudit";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/fiveDataNoAudit";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/fiveDataNoAudit";
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
		model.addAttribute("resRecCountMap", resRecCountMap);
		model.addAttribute("processFlow",processFlow);
		return "res/jswjw/teacher/fiveDataNoAudit";
	}
	@RequestMapping(value="/skillNoAudit",method=RequestMethod.GET)
	public String skillNoAudit(String userFlow,String roleId,String docFlow,String biaoJi,String processFlow,String schResultFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/skillNoAudit";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/skillNoAudit";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/teacher/skillNoAudit";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/teacher/skillNoAudit";
		}
		if(StringUtil.isBlank(schResultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/jswjw/teacher/skillNoAudit";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/skillNoAudit";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/skillNoAudit";
		}

		List<String> typeId=new ArrayList<>();
		typeId.add(ResRecTypeEnum.DOPS.getId());
		typeId.add(ResRecTypeEnum.Mini_CEX.getId());
		Map<String,Object> param=new HashMap<>();
		param.put("teaFlow",userFlow);
		param.put("docFlow",docFlow);
		param.put("processFlow",processFlow);
		param.put("recTypes",typeId);
		param.put("biaoJi",biaoJi);
		List<Map<String,Object>> list=jswjwTeacherBiz.findSkillNoAudit(param);
		for(Map<String,Object>map:list)
		{
			map.put("recTypeName", ResRecTypeEnum.getNameById((String) map.get("recTypeId")));
		}
		model.addAttribute("list",list);
		return "res/jswjw/teacher/skillNoAudit";
	}

	@RequestMapping(value="/dayAttendList",method=RequestMethod.POST)
	public String dayAttendList(String userFlow,String roleId,String docFlow,
								Integer pageIndex,Integer pageSize,
								String attendType,
								String processFlow,String schResultFlow,String biaoJi,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/dayAttendList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/dayAttendList";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/teacher/dayAttendList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/teacher/dayAttendList";
		}
		if(StringUtil.isBlank(schResultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/jswjw/teacher/dayAttendList";
		}
		if(StringUtil.isBlank(attendType)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "attendType为空");
			return "res/jswjw/teacher/dayAttendList";
		}
		if(!attendType.equals("1")&&!attendType.equals("0")&&!attendType.equals("-1")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "attendType只能是1(出勤)或0(缺勤)或-1(轮休)");
			return "res/jswjw/teacher/dayAttendList";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/dayAttendList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/teacher/dayAttendList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/teacher/dayAttendList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/dayAttendList";
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
		model.addAttribute("list",list);
		model.addAttribute("dataCount",PageHelper.total);
		param.put("attendType","");
        int count =jswjwTeacherBiz.dayAttendListCount(param);
        model.addAttribute("allDataCount",count);
		return "res/jswjw/teacher/dayAttendList";
	}

	/**
	 * 一键审核通过
	 * @param userFlow
	 * @param roleId
	 * @param docFlow
	 * @param attendType
	 * @param processFlow
	 * @param schResultFlow
	 * @param model
     * @return
     * @throws ParseException
     */
	@RequestMapping(value="/batchAttendAudit",method=RequestMethod.POST)
	public String batchAttendAudit(String userFlow,String roleId,String docFlow,
								String attendType,
								String processFlow,String schResultFlow
			,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(schResultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(attendType)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "attendType为空");
			return "res/jswjw/success";
		}
		if(!attendType.equals("1")&&!attendType.equals("0")&&!attendType.equals("-1")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "attendType只能是1(出勤)或0(缺勤)或-1(轮休)");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		Map<String,Object> param=new HashMap<>();
		param.put("teaFlow",userFlow);
		param.put("docFlow",docFlow);
		param.put("processFlow",processFlow);
		param.put("attendType",attendType);
		param.put("biaoJi","Y");
		param.put("nowDate",DateUtil.getCurrDate());
		List<Map<String,Object>> list=jswjwTeacherBiz.dayAttendList(param);
		if(list==null||list.isEmpty())
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "无待审核数据");
			return "res/jswjw/success";
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
				attendance.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
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
		return "res/jswjw/success";
	}

	/**
	 * 带教老师修改出勤状态或添加备注
	 * @param attendanceFlow
	 * @param remarks
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyAttendance")
	public String  modifyAttendance(String attendanceFlow,
									String userFlow,
									String roleId,
									String docFlow,
									String dateDay,
									String attendType, String remarks,Model model)throws Exception {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(dateDay)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "签到日期为空");
			return "res/jswjw/success";
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
			dateFormat2.parse(dateDay);
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "签到日期格式有误");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(attendType)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "attendType为空");
			return "res/jswjw/success";
		}
		if(!attendType.equals("1")&&!attendType.equals("0")&&!attendType.equals("-1")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "attendType只能是1(出勤)或0(缺勤)或-1(轮休)");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}

		//验证用户是否存在
		SysUser sysUser = jswjwBiz.readSysUser(userFlow);
		if(sysUser==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		SysUser docUser = jswjwBiz.readSysUser(docFlow);
		JsresAttendance jsresAttendance = jswjwBiz.getJsresAttendanceByFlow(attendanceFlow);
		if(jsresAttendance==null)
			jsresAttendance=jswjwBiz.getJsresAttendance(dateDay,userFlow);
		if(jsresAttendance!=null&&!"Auditing".equals(jsresAttendance.getAuditStatusId()))
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "该签到记录已经被审核，无法修改签到状态，请刷新列表！");
			return "res/jswjw/success";
		}
		boolean isNew=false;
		if(jsresAttendance==null)
		{
			isNew=true;
			jsresAttendance=new JsresAttendance();
			 attendanceFlow= PkUtil.getUUID();
			jsresAttendance.setAttendanceFlow(attendanceFlow);
			jsresAttendance.setRecordStatus("Y");
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
			model.addAttribute("resultId", "3011102");
			model.addAttribute("resultType", "修改失败！");
		}
		return "res/jswjw/success";
	}
	@RequestMapping(value="/monthEvalList",method=RequestMethod.GET)
	public String monthEvalList(String userFlow,String roleId,String docFlow,String processFlow,String schResultFlow,String biaoJi,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/monthEvalList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/monthEvalList";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/teacher/monthEvalList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/teacher/monthEvalList";
		}
		if(StringUtil.isBlank(schResultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/jswjw/teacher/monthEvalList";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/monthEvalList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/monthEvalList";
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
		model.addAttribute("evals",evals);
		model.addAttribute("docUser",docUser);
		model.addAttribute("result",schArrangeResult);
		model.addAttribute("doctor",doctor);
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("doctorFlow",docFlow);
		return "res/jswjw/teacher/monthEvalList";
	}
	@RequestMapping(value="/showMonthEval",method=RequestMethod.POST)
	public String showMonthEval(String userFlow,String doctorFlow,String processFlow,String recordFlow,String evalMonth,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/showMonthEval";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/teacher/showMonthEval";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/teacher/showMonthEval";
		}
		if(StringUtil.isBlank(evalMonth)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核时间不能为空");
			return "res/jswjw/teacher/showMonthEval";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/showMonthEval";
		}
		ResDoctorSchProcessEvalWithBLOBs eval=jswjwBiz.getDoctorProcessEvalByFlow(recordFlow);
		if(eval==null)
			  eval=jswjwBiz.getDoctorProcessEval(processFlow,evalMonth);
		List<FromTitle> titleList=null;
		String configXml="";
		String configFlow="";
		String IsForm="N";
		if(eval!=null)
		{
			IsForm=eval.getIsForm();
			configXml=eval.getFormCfg();
			//已经保存的分数
			Map<String, Object> map=jswjwBiz.parseScoreXml(eval.getEvalResult());
			model.addAttribute("valueMap", map);
		}else{
			//评分配置
			ResDoctorProcessEvalConfig config=jswjwBiz.getProcessEvalConfig(userinfo.getOrgFlow());
			if(config!=null) {
				configXml = config.getFormCfg();
				IsForm="Y";
				configFlow=config.getConfigFlow();
			}
		}
		model.addAttribute("isAudit",eval!=null);
		titleList=jswjwBiz.parseFromXmlForList(configXml);
		if(titleList!=null&&titleList.size()>0){
			IsForm="Y";
		}else{
			IsForm="N";
		}
		model.addAttribute("titleList",titleList);
		model.addAttribute("IsForm",IsForm);
		model.addAttribute("eval",eval);
		model.addAttribute("configFlow",configFlow);
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("doctorFlow",doctorFlow);
		model.addAttribute("recordFlow",recordFlow);
		return "res/jswjw/teacher/showMonthEval";
	}

	@RequestMapping(value="/saveMonthEval",method=RequestMethod.POST)
	public String saveMonthEval(String userFlow,
								String haveForm,String configFlow,ResDoctorSchProcessEvalWithBLOBs eval2,
								HttpServletRequest request, Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/saveMonthEval";
		}
		if(StringUtil.isBlank(eval2.getDoctorFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/teacher/saveMonthEval";
		}
		if(StringUtil.isBlank(eval2.getProcessFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/teacher/saveMonthEval";
		}
		if(StringUtil.isBlank(eval2.getEvalMonth())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核时间不能为空");
			return "res/jswjw/teacher/saveMonthEval";
		}
		if(StringUtil.isBlank(haveForm))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "是否表单标识为空");
			return "res/jswjw/teacher/saveMonthEval";
		}
		if(StringUtil.isBlank(eval2.getEvalContent())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评语不能为空");
			return "res/jswjw/teacher/saveMonthEval";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/saveMonthEval";
		}
		ResDoctorSchProcessEvalWithBLOBs eval=jswjwBiz.getDoctorProcessEval(eval2.getProcessFlow(),eval2.getEvalMonth());
		if(eval!=null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "已打过月度考评表，不得重复评分");
			return "res/jswjw/teacher/saveMonthEval";
		}
		if("Y".equals(haveForm))
		{
			eval2.setIsForm("Y");
			if(StringUtil.isBlank(configFlow))
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "表单标识符为空");
				return "res/jswjw/teacher/saveMonthEval";
			}

			if(null==eval2.getEvalScore())
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "总得分为空");
				return "res/jswjw/teacher/saveMonthEval";
			}

			//评分配置
			ResDoctorProcessEvalConfig config=jswjwBiz.getProcessEvalConfigByFlow(configFlow);
			if(config==null)
			{
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "评分配置信息不存在");
				return "res/jswjw/teacher/saveMonthEval";
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
								model.addAttribute("resultId", "3011101");
								model.addAttribute("resultType", item.getName()+"项未打分，无法提交");
								return "res/jswjw/teacher/saveMonthEval";
							}
							items.setText((String) request.getParameter(item.getId()));
						}
					}
				}
			}
			eval2.setEvalResult(doc.asXML());
		}else{
			eval2.setIsForm("N");
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
		if(count==0)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "保存失败");
			return "res/jswjw/teacher/saveMonthEval";
		}
		return "res/jswjw/teacher/saveMonthEval";
	}

	public static void main(String args[]) throws ParseException {
		//System.out.println(JSON.toJSONString(getTimes("2016-01-05","2017-05-05","sdfsdfsf")));
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
	public String batchAudit(String userFlow,String roleId,String docFlow,String processFlow,String recType,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "审核成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/success";
		}//rec类型转换一下
		recType=getRecTypeId(recType);
		if(StringUtil.isBlank(recType)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}

		List<ResRec> recList=jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow,docFlow,recType,"Y");
		if(null != recList && recList.size() > 0){
            ResRec resRec = recList.get(0);
            String appMenu = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_app_menu_" + resRec.getOperUserFlow());
            if (!GlobalConstant.RECORD_STATUS_Y.equals(appMenu)) {
                model.addAttribute("resultId", "3010113");
                model.addAttribute("resultType", "无操作权限，请联系基地管理员！");
                return "res/jswjw/success";
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
				model.addAttribute("resultId", "3010103");
				model.addAttribute("resultType", "批量审核失败");
				return "res/jswjw/success";
			}
		}else{
			model.addAttribute("resultId", "200");
			model.addAttribute("resultType", "无审核数据");
			return "res/jswjw/success";
		}
		return "res/jswjw/success";
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
				recTypeId = ResRecTypeEnum.AfterSummary.getId();
				break;
			case "dops":
				recTypeId = ResRecTypeEnum.DOPS.getId();
				break;
			case "miniCex":
				recTypeId = ResRecTypeEnum.Mini_CEX.getId();
				break;
			case "after":
				recTypeId = ResRecTypeEnum.AfterEvaluation.getId();
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
	public String oneAudit(String userFlow,String roleId,String recFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "审核成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(recFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据标识符为空");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		ResRec re =jswjwBiz.readResRec(recFlow);
		if(re == null){
            model.addAttribute("resultId", "3010103");
            model.addAttribute("resultType", "数据不存在");
            return "res/jswjw/success";
        }
        String appMenu = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_app_menu_" + re.getOperUserFlow());
        if (!GlobalConstant.RECORD_STATUS_Y.equals(appMenu)) {
            model.addAttribute("resultId", "3010113");
            model.addAttribute("resultType", "无操作权限，请联系基地管理员！");
            return "res/jswjw/success";
        }
		if(StringUtil.isNotBlank(re.getAuditStatusId())){
			model.addAttribute("resultId", "200");
			model.addAttribute("resultType", "数据已审核");
			return "res/jswjw/success";
		}
		String time=DateUtil.getCurrDateTime();
		SysUser sysUser=jswjwBiz.readSysUser(userFlow);
		re.setAuditTime(time);
		re.setAuditUserFlow(sysUser.getUserFlow());
		re.setAuditUserName(sysUser.getUserName());
		re.setAuditStatusId(RecStatusEnum.TeacherAuditY.getId());
		re.setAuditStatusName(RecStatusEnum.TeacherAuditY.getName());
		int i=jswjwBiz.editResRec(re,sysUser, re.getRecTypeId());
		if (i ==0) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "审核失败");
			return "res/jswjw/success";
		}
		return "res/jswjw/success";
	}
	/**
	 * o数据列表
	 * @param
	 * @param docFlow
	 * @param processFlow
	 * @param recType
     * @return
     */
	@RequestMapping(value="/resRecList",method=RequestMethod.GET)
	public String resRecList(String userFlow,String roleId,String docFlow,String processFlow,String recType,String biaoJi,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/resRecList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/resRecList";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/teacher/resRecList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/teacher/resRecList";
		}
		//rec类型转换一下
		String recTypeId=getRecTypeId(recType);
		model.addAttribute("recTypeId",recTypeId);
		if(StringUtil.isBlank(recTypeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/jswjw/teacher/resRecList";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/resRecList";
		}
//		ResDoctor doctor = jswjwBiz.readResDoctor(docFlow);
//		if(doctor==null||StringUtil.isBlank(doctor.getRotationFlow())){
//			model.addAttribute("resultId", "30204");
//			model.addAttribute("resultType", "该学员未设置轮转方案!");
//			return "res/jswjw/teacher/resRecList";
//		}
		Map<String, Object> processPerMap = jswjwBiz.getRecProgressIn(docFlow, processFlow,null,null);
		model.addAttribute("processPerMap", processPerMap);

		System.err.println(JSON.toJSON(processPerMap));
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/resRecList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/teacher/resRecList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/teacher/resRecList";
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
		model.addAttribute("dataList", dataList);
		model.addAttribute("dataCount", PageHelper.total);
		List<ResRec> noAuditList=jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow,docFlow,recTypeId,"Y");
		int count=0;
		if(noAuditList!=null){
			count=noAuditList.size();
		}
		model.addAttribute("notAuditNum",count);
		return "res/jswjw/teacher/resRecList";
	}
	/**
	 * 单条数据详情
	 * @param
	 * @param recType
     * @return
     */
	@RequestMapping(value="/resRecDeatil",method=RequestMethod.GET)
	public String resRecDeatil(String userFlow,String roleId,String recFlow,String deptFlow,String cataFlow,String recType,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/resRecDetail";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/resRecDetail";
		}
		if(StringUtil.isBlank(recFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据标识符为空");
			return "res/jswjw/teacher/resRecDetail";
		}
		if(StringUtil.isBlank(recType)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/jswjw/teacher/resRecDetail";
		}
		if(StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室流水号为空");
			return "res/jswjw/teacher/resRecDetail";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/resRecDetail";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/resRecDetail";
		}
//		ResRec rec = jswjwBiz.readResRecByDataType(recFlow, recType);
		ResRec rec = jswjwBiz.readResRec(recFlow);
		String recContent = rec.getRecContent();
		_inputList(userFlow, deptFlow, recType,cataFlow, model);
		Map<String, Object> formDataMap = parseRecContent(recContent);
		formDataMap.put("auditId",rec.getAuditStatusId());
		model.addAttribute("resultData", formDataMap);
		model.addAttribute("isOther", GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
		return "res/jswjw/teacher/resRecDetail";
	}
	private void _inputList(String userFlow,String deptFlow,String dataType,String cataFlow ,Model model){
		switch (dataType) {
			case "mr":
				break;
			case "disease":
				List<Map<String,Object>> diseaseDiagNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType, cataFlow);
				model.addAttribute("diseaseDiagNameList", diseaseDiagNameList);
				break;
			case "skill":
				List<Map<String,Object>> skillOperNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType,cataFlow);
				model.addAttribute("skillOperNameList", skillOperNameList);
				break;
			case "operation":
				List<Map<String,Object>> operationOperNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType,cataFlow);
				model.addAttribute("operationOperNameList", operationOperNameList);
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
	final static String Bgdfx = "20";final static String Jxsj = "21";final static String Sjys = "22";
	/**
	 * 出科考核表
	 * @param model
     * @return
     * @throws Exception
     */
	@RequestMapping(value="/afterEvaluation")
	public String evaluationSun(String userFlow,
								String docFlow,
								String processFlow,
								String recFlow,
								String roleId,
								String deptFlow,
								String recordFlow,
								Model model) throws Exception{

		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/evaluationSun";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/teacher/evaluationSun";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/evaluationSun";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/jswjw/teacher/evaluationSun";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/evaluationSun";
		}

		String recTypeId=ResRecTypeEnum.AfterEvaluation.getId();
		ResDoctor doctor=null;
		SysUser operUser=null;
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
		model.addAttribute("currUser",currUser);

		ResDoctorSchProcess p=iResDoctorProcessBiz.read(processFlow);
		if(StringUtil.isNotBlank(p.getSchStartDate())&&StringUtil.isNotBlank(p.getSchEndDate()))
		{
			model.addAttribute("attendance",DateUtil.signDaysBetweenTowDate(p.getSchEndDate(),p.getSchStartDate())+1) ;
		}

		//是否审核过出科考核表，不管是不是退回过的

		String key ="jsres_"+currUser.getOrgFlow()+"_org_process_eval" ;
		String cfgv=jswjwBiz.getJsResCfgCode(key);
		if(GlobalConstant.FLAG_Y.equals(cfgv)) {
			ResSchProcessExpress express=expressBiz.getExpressByRecTypeNoStatus(processFlow,recTypeId);
			if(express==null) {
				int c = iResDoctorProcessBiz.checkProcessEval(processFlow);
				if (c > 0) {
					model.addAttribute("resultId", "3011101");
					model.addAttribute("resultType", "请先完成此科室的月度考评!");
					return "res/jswjw/teacher/evaluationSun";
				}
			}
		}
        String appMenu = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_app_menu_" + docFlow);
        if (!GlobalConstant.RECORD_STATUS_Y.equals(appMenu)) {
            model.addAttribute("resultId", "3010113");
            model.addAttribute("resultType", "无操作权限，请联系基地管理员！");
            return "res/jswjw/success";
        }
		if(StringUtil.isNotBlank(docFlow)){
			doctor  = jswjwBiz.readResDoctor(docFlow);
			model.addAttribute("doctor", doctor);
			operUser  = jswjwBiz.readSysUser(docFlow);
			model.addAttribute("operUser",operUser);
		}
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();


		Map<String,Object> processPerMap=jswjwBiz.getRecProgressIn(docFlow,processFlow,null,null);
		if(processPerMap == null){
			processPerMap = new HashMap<String, Object>();
		}
		boolean f=false;
		f=jswjwBiz.getCkkPower(docFlow);
		JsresPowerCfg jsresPowerCfg= jswjwBiz.readPowerCfg("out_test_check_" + p.getOrgFlow());
		//填写百分比限制
		JsresPowerCfg jsresPowerCfg1= jswjwBiz.readPowerCfg("out_filling_check_" + p.getOrgFlow());
		if(null != jsresPowerCfg1){
			model.addAttribute("checkProcess",jsresPowerCfg1.getCfgValue());
		}else{
			model.addAttribute("checkProcess","0");
		}
		if(f) {
			ResScore outScore = jswjwTeacherBiz.readScoreByProcessFlow(processFlow);
			model.addAttribute("outScore", outScore);
			// 如果有出科成绩 出科试卷flow不为空则查询试卷对应的及格分
			if(null != outScore && StringUtil.isNotBlank(outScore.getPaperFlow())){
				// 查询对应试卷的及格分
				TestPaper testPaper = paperBiz.readTestPaper(outScore.getPaperFlow());
				model.addAttribute("testPaper", testPaper);
				// 是否校验出科理论成绩
//				String theoreticalCfg = jswjwBiz.getJsResCfgCode("theoretical_qualified_flag_" + currUser.getOrgFlow());
//				model.addAttribute("theoreticalCfg", theoreticalCfg);
				//禅道201  修改
				//查询科室是否配置出科设置
//				if(GlobalConstant.RECORD_STATUS_Y.equals(jsresPowerCfg.getCfgValue())) {
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
		model.addAttribute("f",f);

		String theoryScorePass = "";
		String teacherWrite = "N";
		if(GlobalConstant.RECORD_STATUS_Y.equals(jsresPowerCfg.getCfgValue())) {
			//查询科室是否配置
			JsresDeptConfig deptConfig = jswjwBiz.searchDeptCfg(p.getOrgFlow(), p.getSchDeptFlow());
			if (null != deptConfig) {
				theoryScorePass = deptConfig.getScorePass();
				if (GlobalConstant.RECORD_STATUS_Y.equals(deptConfig.getTeacherWrite())) {
					teacherWrite = "Y";
				}
			} else {
				deptConfig = jswjwBiz.searchBaseDeptConfig(p.getOrgFlow());
				theoryScorePass = deptConfig.getScorePass();
				if (GlobalConstant.RECORD_STATUS_Y.equals(deptConfig.getTeacherWrite())) {
					teacherWrite = "Y";
				}
			}
		}
		model.addAttribute("teacherWrite", teacherWrite);
		model.addAttribute("theoryScorePass", theoryScorePass);

		SysDept dept=jswjwBiz.readSysDept(p.getDeptFlow());
		String cksh = jswjwBiz.getJsResCfgCode("jsres_"+dept.getOrgFlow()+"_org_cksh");
		if(StringUtil.isBlank(cksh))
		{
			cksh="N";
		}
		model.addAttribute("cksh",cksh);
		//获取不同类型并定义接受
		if(processPerMap!=null){
			String caseRegistryId=ResRecTypeEnum.CaseRegistry.getId();
			String diseaseRegistryId=ResRecTypeEnum.DiseaseRegistry.getId();
			String skillRegistryId=ResRecTypeEnum.SkillRegistry.getId();
			String operationRegistryId=ResRecTypeEnum.OperationRegistry.getId();

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

			String recTypeIdt=ResRecTypeEnum.CampaignRegistry.getId();
//			int teachingRounds=0;
//			int difficult=0;
//			int lecture=0;
//			int death=0;
			int jxcf = 0;  int xjk = 0;
			int rkjy = 0;	int jnpx=0;
			int yph = 0; int jxhz = 0; int jxbltl = 0; int lcczjnzd = 0;
			int lcblsxzd = 0; int ssczzd = 0; int yxzdbgsxzd = 0; int lcwxyd = 0;
			int ryjy = 0; int rzyjdjy = 0; int cjbg = 0;
			int ynbltl=0;	int wzbltl=0; int swbltl=0;
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
					}else if(Bgdfx.equals(text)){
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
			if (null!=orgApprove && null!=approve && StringUtil.isNotNullAndEquala(approve.getCfgValue(),orgApprove.getCfgValue(),"Y")) {        //开启必评
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
			dataMap.put("swbltl",String.valueOf(swbltl));
			dataMap.put("wzbltl",String.valueOf(wzbltl));
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
			ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
			model.addAttribute("resDoctorSchProcess", resDoctorSchProcess);
			model.addAttribute("dataMap", dataMap);
			model.addAttribute("rec", rec);
			model.addAttribute("processFlow",processFlow);
			model.addAttribute("formFileName",recTypeId);
			model.addAttribute("roleFlag", "teacher");
			if (null !=doctor){
				//学员出科是否需要带教老师填写出科评价
				String departure = "jsres_departure_evaluation_" + doctor.getOrgFlow();
				JsresPowerCfg departureCfg = jsresPowerCfgMapper.selectByPrimaryKey(departure);
				if (departureCfg != null) {
					model.addAttribute("subEvaluation", departureCfg.getCfgValue());
				}else {
					model.addAttribute("subEvaluation", "N");
				}
			}
		}
		return "res/jswjw/teacher/evaluationSun";
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
     * @param model
     * @return
     */
	@RequestMapping(value="/saveRegistryForm",method={RequestMethod.GET,RequestMethod.POST})
	public String saveRegistryForm(String formFileName,String recFlow,String operUserFlow,String roleFlag,String cksh,String teacherComment,
								   String processFlow,String recordFlow,String userFlow,HttpServletRequest req,Model model) throws UnsupportedEncodingException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		SysUser user=jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(operUserFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleFlag)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/jswjw/success";
		}
		if(!roleFlag.equals("teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}

		ResSchProcessExpress resRec=expressBiz.searResRecZhuZhi(formFileName,recFlow,operUserFlow,roleFlag,processFlow,recordFlow,userFlow,cksh,req);
		if (StringUtil.isNotBlank(teacherComment)){
			teacherComment=URLDecoder.decode(teacherComment,"UTF-8");
			teacherComment=teacherComment.replaceAll("\n","\\\\n");
			teacherComment=teacherComment.replaceAll("\r","\\\\r");
			resRec.setTeacherComment(teacherComment);
		}
		int i=expressBiz.editResRec(resRec,user);
		if (i==0) {
			model.addAttribute("resultId", "3031001");
			model.addAttribute("resultType", "保存失败");
		}
		return "res/jswjw/success";


	}
	//
	/**
	 * 迷你临床演练评估量化表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mini_cex")
	public String mini_cex(String userFlow,
								String docFlow,
								String processFlow,
								String recFlow,
								String roleId,
								String deptFlow,
								Model model) throws Exception{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/mini_cex";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/teacher/mini_cex";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/mini_cex";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/jswjw/teacher/mini_cex";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/mini_cex";
		}
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
		model.addAttribute("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
		String recTypeId=ResRecTypeEnum.Mini_CEX.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(docFlow);

		model.addAttribute("doctor", doctor);
		model.addAttribute("formFileName", recTypeId);
		model.addAttribute("rec", rec);
		model.addAttribute("roleFlag", "teacher");
		return "res/jswjw/teacher/mini_cex";
	}
	/**
	 * 临床操作技能评估量化表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DOPS")
	public String DOPS(String userFlow,
					   String docFlow,
					   String processFlow,
					   String recFlow,
					   String roleId,
					   String deptFlow,
					   Model model) throws Exception{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/DOPS";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/teacher/DOPS";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/DOPS";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/jswjw/teacher/DOPS";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/DOPS";
		}
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
		model.addAttribute("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
		String recTypeId=ResRecTypeEnum.DOPS.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(docFlow);

		model.addAttribute("doctor", doctor);
		model.addAttribute("formFileName", recTypeId);
		model.addAttribute("rec", rec);
		model.addAttribute("roleFlag", "teacher");
		return "res/jswjw/teacher/DOPS";
	}

	@RequestMapping(value = {"/userList"}, method = {RequestMethod.POST})
	public String userList(String userFlow, String studentName, String deptFlow,String doctorTypeId,String trainingTypeId,String trainingSpeId, String sessionNumber,  String dataType,Integer pageIndex,Integer pageSize,Model model,String biaoJi) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/userList";
		}
//		if (StringUtil.isBlank(deptFlow)) {
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "科室标识符为空");
//			return "res/jswjw/teacher/userList";
//		}
		if (StringUtil.isBlank(dataType)&&StringUtil.isNotBlank(biaoJi)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "dataType为空");
			return "res/jswjw/teacher/userList";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/userList";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/teacher/userList";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/teacher/userList";
		}

		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
//		schArrangeResultMap.put("deptFlow", deptFlow);
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
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("list", resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/teacher/userList";
	}

	@RequestMapping(value = {"/activityList"}, method = {RequestMethod.POST})
	public String activityList(String userFlow
			,Integer pageIndex,Integer pageSize,Model model,String roleId) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/activityList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/activityList";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/activityList";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/activityList";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/teacher/activityList";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/teacher/activityList";
		}
		Map<String,String> param=new HashMap<>();
		param.put("roleFlag","teach");
		param.put("userFlow",userinfo.getUserFlow());
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		param.put("orgFlow", userinfo.getOrgFlow());
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		List<SysUserRole> userRoleList = evalAppBiz.getSysUserRole(userFlow); //获取该用户下的所有角色信息
		for (Map<String,Object> obj: list) {
			if(obj.get("activityRemark") != null  ) obj.put("activityRemark", obj.get("activityRemark").toString().replaceAll("\"", "").replaceAll("\\\\","/"));
			for (SysUserRole role:userRoleList) {
				if(obj.containsKey("auditRole")) {
					if (obj.get("auditRole").toString().contains(role.getRoleFlow())) {
						obj.put("audit", "Y");
					}
				}
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		if(CollectionUtils.isNotEmpty(list)) {
			for (Map<String,Object> info: list){
				List<Map<String,Object>>  results=activityBiz.readActivityResults((String) info.get("activityFlow"),"");
				resultMap.put((String) info.get("activityFlow"),results);
			}
		}
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("list", list);
		model.addAttribute("resultMap", resultMap);
		model.addAttribute("dataCount", PageHelper.total);
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		String key="jsres_"+userinfo.getOrgFlow()+"_org_activity_code_type";
		String cfgv=jswjwBiz.getJsResCfgCode(key);
		model.addAttribute("codeType", cfgv);
		return "res/jswjw/teacher/activityList";
	}

	@RequestMapping(value = {"/qrCode"}, method = {RequestMethod.POST})
	public String qrCode(String userFlow
			,String activityFlow,Model model,String roleId) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/qrCode";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/qrCode";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/teacher/qrCode";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/qrCode";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/qrCode";
		}
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		if(info==null||!GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/teacher/qrCode";
		}
		String url = "func://funcFlow=activitySigin&activityFlow="+activityFlow;
		String url2 = "func://funcFlow=activityOutSigin&activityFlow="+activityFlow;
		model.addAttribute("url", url);
		model.addAttribute("url2", url2);
		return "res/jswjw/teacher/qrCode";
	}
	@RequestMapping(value = {"/delActivity"}, method = {RequestMethod.POST})
	public String delActivity(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		if(activity==null)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/success";
		}

		if(!GlobalConstant.RECORD_STATUS_Y.equals(activity.getRecordStatus()))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息已被删除，请刷新列表！");
			return "res/jswjw/success";
		}

		List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow,"");
		if(results!=null&&results.size()>0)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "此活动已有学员扫码，无法删除！");
			return "res/jswjw/success";
		}
		activity.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		model.addAttribute("activity", activity);
		int c=activityBiz.saveActivityInfo(activity,userinfo);
		if(c==0)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "删除失败！");
			return "res/jswjw/success";
		}
		return "res/jswjw/success";
	}
	@RequestMapping(value = {"/showActivity"}, method = {RequestMethod.POST})
	public String showActivity(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/showActivity";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/showActivity";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/teacher/showActivity";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/showActivity";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/showActivity";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/teacher/showActivity";
		}
		model.addAttribute("activity", activity);
		model.addAttribute("user",userinfo);
		List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow,"");
		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
		model.addAttribute("depts",depts);
		if(!(results!=null&&results.size()>0))
		{
			if(activity!=null) {
				if (activity.containsKey("activityStatus") && "pass".equals(activity.get("activityStatus"))) {
					return "res/jswjw/teacher/showPassActivity";
				} else {
					return "res/jswjw/teacher/showActivity";
				}
			}
			//return "res/jswjw/teacher/editActivity";
		}
		int imgCount=0;
		if(activity!=null)
		{
			String content= (String) activity.get("imageUrl");
			List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
			model.addAttribute("imageList", imageList);
			if(imageList!=null)
			{
				imgCount=imageList.size();
			}
		}
		model.addAttribute("imgCount",imgCount+"张");
		return "res/jswjw/teacher/showActivity";
	}
	@RequestMapping(value = {"/activityEval"}, method = {RequestMethod.POST})
	public String activityEval(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/activityEval";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/activityEval";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/teacher/activityEval";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/activityEval";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/activityEval";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/teacher/activityEval";
		}
		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
		model.addAttribute("targets",targets);
		return "res/jswjw/teacher/activityEval";
	}
	@RequestMapping(value = {"/activityEvalList"}, method = {RequestMethod.POST})
	public String activityEvalList(String userFlow,String activityFlow,String searchStr,
								   Integer pageIndex,
								   Integer pageSize,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/activityEvalList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/activityEvalList";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/teacher/activityEvalList";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/activityEvalList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/activityEvalList";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/teacher/activityEvalList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "res/jswjw/teacher/activityEvalList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jswjw/teacher/activityEvalList";
		}
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",info);
		//评价人员
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow,searchStr);
		model.addAttribute("results",results);
		model.addAttribute("user",userinfo);
		model.addAttribute("dataCount", PageHelper.total);

		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
		model.addAttribute("targets",targets);
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
		model.addAttribute("evalDetailMap",evalDetailMap);
		model.addAttribute("evalRemarksMap",evalRemarksMap);
		return "res/jswjw/teacher/activityEvalList";
	}
	@RequestMapping(value = {"/activityStuList"}, method = {RequestMethod.POST})
	public String activityStuList(String userFlow,String activityFlow,String searchStr,
								   Integer pageIndex,String typeId,
								   Integer pageSize,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/activityStuList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/activityStuList";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/teacher/activityStuList";
		}
		if(StringUtil.isBlank(typeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "typeId标识符为空");
			return "res/jswjw/teacher/activityStuList";
		}
		if(!"Y".equals(typeId)&&!"N".equals(typeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "typeId只能是Y或N");
			return "res/jswjw/teacher/activityStuList";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/activityStuList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/activityStuList";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/teacher/activityStuList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "res/jswjw/teacher/activityStuList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jswjw/teacher/activityStuList";
		}
		//评价人员
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> results=activityBiz.readActivityResultsByType(activityFlow,searchStr,typeId);
		model.addAttribute("results",results);
		model.addAttribute("user",userinfo);
		model.addAttribute("dataCount", PageHelper.total);

		return "res/jswjw/teacher/activityStuList";
	}
	@RequestMapping(value = {"/effectiveResult"}, method = {RequestMethod.POST})
	public String effectiveResult(String userFlow,  String activityFlow,
								  String resultFlow,  String isEffective,
								  Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(resultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "resultFlow为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(isEffective)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isEffective");
			return "res/jswjw/success";
		}
		if(!"Y".equals(isEffective)&&!"N".equals(isEffective))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isEffective只能是Y或N");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/success";
		}
		TeachingActivityResult info=activityBiz.readResult(resultFlow);
		if(info==null) {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "学员参加活动结果信息不存在，请刷新列表页面！");
			return "res/jswjw/success";
		}
		info.setIsEffective(isEffective);
		int c=activityBiz.saveResult(info,userFlow);
		if(c==0){
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "修改失败");
			return "res/jswjw/success";
		}
		return "res/jswjw/success";
	}
	@RequestMapping(value = {"/addActivity"}, method = {RequestMethod.POST})
	public String addActivity(String userFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/editActivity";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/editActivity";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/editActivity";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/editActivity";
		}
		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrg(userinfo.getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "暂未配置活动指标，请联系基地管理员");
			return "res/jswjw/success";
		}
		model.addAttribute("user",userinfo);
		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
		model.addAttribute("depts",depts);
		return "res/jswjw/teacher/editActivity";
	}
	@RequestMapping(value = {"/saveActivity"}, method = {RequestMethod.POST})
	public String saveActivity(String userFlow,TeachingActivityInfo activity,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getSpeakerFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "主讲人标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getDeptFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getActivityTypeId())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动形式为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getActivityName())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动名称为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getActivityAddress())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动地点为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getSpeakerPhone())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "联系方式为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getStartTime())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动开始时间为空");
			return "res/jswjw/success";
		}

		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getStartTime());
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "活动开始时间格式有误");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getEndTime())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动结束时间为空");
			return "res/jswjw/success";
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getEndTime());
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "活动结束时间格式有误");
			return "res/jswjw/success";
		}
		if (StringUtil.isBlank(activity.getActivityFlow())){
			String currDateTime = DateUtil.getCurrDateTime2();
			if (currDateTime.compareTo(activity.getStartTime())>0){
				model.addAttribute("resultId", "30401");
				model.addAttribute("resultType", "开始时间不得小于当前时间");
				return "res/jswjw/success";
			}
		}

		if(activity.getStartTime().compareTo(activity.getEndTime())>=0)
		{
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "开始时间不得大于等于结束时间");
			return "res/jswjw/success";
		}
//		if(StringUtil.isBlank(activity.getRealitySpeaker())){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "实际主讲人为空");
//			return "res/jswjw/success";
//		}
		if(StringUtil.isBlank(activity.getActivityRemark())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动简介为空");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		model.addAttribute("user",userinfo);
//		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrg(userinfo.getOrgFlow());
		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrgNew(activity.getActivityTypeId(),userinfo.getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "暂未配置活动指标，请联系基地管理员");
			return "res/jswjw/success";
		}
		//校验活动时间是否重复
		int count=checkTime(activity);
		if(count>0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "该主讲人在时间段内已开展其他活动");
			return "res/jswjw/success";
		}
		if(StringUtil.isNotBlank(activity.getActivityTypeId()))
		{
			activity.setActivityTypeName(ActivityTypeEnum.getNameById(activity.getActivityTypeId()));
		}
		activity.setOrgFlow(userinfo.getOrgFlow());
		int c= activityBiz.addActivityInfo(activity,userinfo,targets, null);
		if(c==0)
		 {
			 model.addAttribute("resultId", "3010103");
			 model.addAttribute("resultType", "保存失败");
			 return "res/jswjw/success";
		}else if(c==-1){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "该角色无法发起教学活动，请联系基地管理员！");
			return "res/jswjw/success";
		}else if(c==-2){
			String keyOfDay = "jsres_" + userinfo.getOrgFlow() + "_org_activity_add_day";
			String keyOfTime = "jsres_" + userinfo.getOrgFlow() + "_org_activity_add_time";
			String day = activityBiz.jsresPowerCfgMap(keyOfDay);
			String time = activityBiz.jsresPowerCfgMap(keyOfTime);
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "请在活动开始前" + day + "天" + time + "点前设置活动!");
			return "res/jswjw/success";
		}
		return "res/jswjw/success";
	}
	private int checkTime(TeachingActivityInfo activity) {
		return activityBiz.checkTime(activity);
	}
	@RequestMapping(value={"/viewActivityImage"},method={RequestMethod.POST})
	public String viewActivityImage(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/teacher/viewActivityImage";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/teacher/viewActivityImage";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/teacher/viewActivityImage";
		}
		if(!roleId.equals("Teacher")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/teacher/viewActivityImage";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/teacher/viewActivityImage";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/teacher/viewActivityImage";
		}
		if(activity!=null)
		{
			String content= (String) activity.get("imageUrl");
			List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
			model.addAttribute("imageList", imageList);
		}
		return "res/jswjw/teacher/viewActivityImage";
	}
	@RequestMapping(value={"/addActivityImage"},method={RequestMethod.POST})
	public String addActivityImage(ActivityImageFileForm form, HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(form.getUserFlow())){
			model.addAttribute("resultId", "31601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isEmpty(form.getActivityFlow())){
			model.addAttribute("resultId", "31602");
			model.addAttribute("resultType", "教学活动标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isEmpty(form.getFileName())){
			model.addAttribute("resultId", "31603");
			model.addAttribute("resultType", "文件名为空");
			return "res/jswjw/success";
		}
		if(form.getUploadFile()==null && StringUtil.isBlank(form.getUploadFileData())){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "上传文件不能为空");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(form.getUserFlow());
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}

		Map<String, Object> activity=activityBiz.readActivity(form.getActivityFlow());
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/success";
		}
		jswjwBiz.addActivityImage(form,userinfo);
		return "res/jswjw/success";
	}

	@RequestMapping(value={"/deleteActivityImage"},method={RequestMethod.GET})
	public String deleteActivityImage(String userFlow,String activityFlow,String imageFlow, HttpServletRequest request,HttpServletResponse response,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/viewImage";
		}
		if(StringUtil.isEmpty(activityFlow)){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/viewImage";
		}
		if(StringUtil.isEmpty(imageFlow)){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "图片标识符为空");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
		if(activity==null||!GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/success";
		}
		jswjwBiz.deleteActivityImage(userinfo,activityFlow,imageFlow);
		return "res/jswjw/success";
	}
}

