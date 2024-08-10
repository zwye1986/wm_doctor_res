package com.pinde.res.ctrl.njmu2;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.sci.dao.base.SysUserDeptMapper;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu2.INjmu2AppBiz;
import com.pinde.res.biz.njmu2.INjmu2TeacherBiz;

@Controller
@RequestMapping("/res/njmu2/teacher")
public class Njmu2TeacherController{    
	private static Logger logger = LoggerFactory.getLogger(Njmu2TeacherController.class);
	
	@Autowired
	private INjmu2AppBiz njmu2AppBiz;
	@Autowired
	private INjmu2TeacherBiz teacherBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;

	@Autowired
	private SysUserDeptMapper sysUserDeptMapper;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/njmu2/500";
    }
	 
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/njmu2/teacher/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String doctorFlow,String cataFlow,String dataFlow,String funcTypeId,String funcFlow,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("doctorFlow", doctorFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/njmu2/teacher/test";
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
			return "res/njmu2/teacher/doctorList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "res/njmu2/teacher/doctorList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/njmu2/teacher/doctorList";
		}
		
		//包装筛选条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("teacherUserFlow",userFlow);
//		后台配置是否校验权限时间
		String isPermitOpen = njmu2AppBiz.getCfgByCode("res_permit_open_doc");
		paramMap.put("isPermitOpen",isPermitOpen);
		//解析查询条件json字符串  statusId:Entering(已入科) , NotEntered(未入科) , Exited(已出科) doctorName:
		Map<String,String> searchMap = null;
		String statusId = "";
		String doctorName = "";
		if(StringUtil.isNotBlank(searchData)){
			try {
				//为json字符串转码
				System.err.println(searchData);
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
		List<Map<String,Object>> doctorMapList = teacherBiz.getDocListByTeacher(paramMap);
		model.addAttribute("doctorMapList",doctorMapList);
		
		//获取访问路径前缀
		String uploadBaseUrl = njmu2AppBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		
		//数据数量
		model.addAttribute("dataCount", PageHelper.total);
		
		return "res/njmu2/teacher/doctorList";
	}
	@RequestMapping(value={"/funcList"},method={RequestMethod.GET})
	public String funcList(String userFlow,String doctorFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3030201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu2/teacher/funcList";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3030202");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/njmu2/teacher/funcList";
		}
		return "res/njmu2/teacher/funcList";
	}

	@RequestMapping(value={"/teaQrCode"},method={RequestMethod.POST})
	public String teaQrCode(String userFlow,
							 HttpServletRequest request,
							 Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3030101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu2/teacher/qrCode";
		}
		//验证用户是否存在
		SysUser userinfo = njmu2AppBiz.getUserByFlow(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
		}else {
			SysUserDeptExample example = new SysUserDeptExample();
			example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			List<SysUserDept> deptList = sysUserDeptMapper.selectByExample(example);
			List<String> deptFlows = new ArrayList<String>();
			String deptFlowsStr = "";
			for (SysUserDept dept : deptList) {
				deptFlows.add(dept.getDeptFlow());
				if (StringUtil.isNotBlank(deptFlowsStr)) {
					deptFlowsStr += ",";
				}
				deptFlowsStr += dept.getDeptFlow();
			}
			model.addAttribute("signUrl", "func://funcFlow=signin&teacherDeptFlows=" + deptFlowsStr);
		}

		return "res/njmu2/teacher/qrCode";
	}
	@RequestMapping(value="/signinData",method={RequestMethod.GET,RequestMethod.POST})
	public String signinData(Model model,String userFlow){

		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3030101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu2/teacher/signinData";
		}
		//验证用户是否存在
		SysUser userinfo = njmu2AppBiz.getUserByFlow(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
		}else {
			SysUserDeptExample example = new SysUserDeptExample();
			example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			List<SysUserDept> deptList = sysUserDeptMapper.selectByExample(example);
			List<String> deptFlows = new ArrayList<String>();
			for (SysUserDept dept : deptList) {
				deptFlows.add(dept.getDeptFlow());
			}
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("deptFlows", deptFlows);
			paramMap.put("signDate", DateUtil.getCurrDate());
			List<Map<String, Object>> signInMaps = njmu2AppBiz.searchSign(paramMap);
			model.addAttribute("signInMaps", signInMaps);
			//获取访问路径前缀
			String uploadBaseUrl = njmu2AppBiz.getCfgByCode("upload_base_url");
			model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		}
		return "res/njmu2/teacher/signinData";
	}
}

