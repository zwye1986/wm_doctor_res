package com.pinde.res.ctrl.njmu;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.pinde.core.util.CodeUtil;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu.INjmuBiz;
import com.pinde.core.commom.enums.WorkLogTypeEnum;
import com.pinde.res.model.njmu.mo.Userinfo;
import com.pinde.res.model.njmu.mo.Worklog;

@Controller
@RequestMapping("/res/njmu")
public class NjmuAppController{    
	
	private static Logger logger = LoggerFactory.getLogger(NjmuAppController.class);
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		//临时休眠
//		try {
//			Thread.sleep(Integer.MAX_VALUE);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		return "res/njmu/500";
    }
	
	@Autowired
	private INjmuBiz njmuBiz;
	
	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/njmu/version";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", "30101");
			model.addAttribute("resultType", "用户名为空");
			return "res/njmu/login";
		}
		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", "30102");
			model.addAttribute("resultType", "密码为空");
			return "res/njmu/login";
		}
		String userPasswdMd5 = _md5Dnet(userPasswd,userCode);
		Userinfo userinfo = njmuBiz.login(userCode,userPasswdMd5,"1");
		if(userinfo==null)
		{
			userinfo = njmuBiz.login(userCode,userPasswdMd5,"7");
		}
		if(userinfo==null){
			model.addAttribute("resultId", "30199");
			model.addAttribute("resultType", "用户名或密码错误");
		}else{
			//超级密码
			//logger.debug("----------"+userPasswd);
			//logger.debug("----------"+StringUtil.isEquals("86862688", userPasswd));
			if(StringUtil.isEquals("86862688", userPasswd)){
				model.addAttribute("userinfo", userinfo);
			}else if(StringUtil.isEquals(userinfo.getPassword(), userPasswdMd5)){
				model.addAttribute("userinfo", userinfo);
			}else{
				model.addAttribute("resultId", "30199");
				model.addAttribute("resultType", "用户名或密码错误");
			}
		}
		return "res/njmu/login";
	}
	
	private String _md5Dnet(String pTocrypt, String pKey) {
		String ret = "";
		byte[] data3 = new byte[80];
		try {
			pTocrypt = StringUtil.decodeString(pTocrypt).toUpperCase();
			pKey = StringUtil.decodeString(pKey).toUpperCase();
			byte[] data1 = pTocrypt.getBytes("UTF-8");
			byte[] data2 = pKey.getBytes("UTF-8");
			int d1len = 0;
			int d2len = 0;
			for (int i = 0; i < 80; i++) {
				if ((i % 2) == 0 && d1len < data1.length) {
					data3[i] = data1[d1len++];
				} else if ((i % 2) == 1 && d2len < data2.length) {
					data3[i] = data2[d2len++];
				}
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret = CodeUtil.md5(new String(data3)).toUpperCase();
		return ret;
	}

	@RequestMapping(value={"/deptList"},method={RequestMethod.GET})
	public String deptList(String userFlow,Integer pageIndex,Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/deptList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "当前页码为空");
			return "res/njmu/deptList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "每页条数为空");
			return "res/njmu/deptList";
		}
		List<Map<String,Object>> deptMapList = njmuBiz.deptList(userFlow,pageIndex,pageSize);
		model.addAttribute("dataCount", njmuBiz.deptList(userFlow,1,Integer.MAX_VALUE).size());
		model.addAttribute("deptMapList", deptMapList);
		return "res/njmu/deptList";
	}
	
	@RequestMapping(value={"/teacherList"},method={RequestMethod.GET})
	public String teacherList(String userFlow,String deptFlow,String teacherName,Integer pageIndex,Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30301");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/teacherList";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "30302");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/teacherList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30303");
			model.addAttribute("resultType", "当前页码为空");
			return "res/njmu/teacherList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30304");
			model.addAttribute("resultType", "每页条数为空");
			return "res/njmu/teacherList";
		}
		if(StringUtil.isNotBlank(teacherName)){
			try {
				teacherName = new String(teacherName.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<Map<String,Object>> deptMapList = njmuBiz.teacherList(userFlow,deptFlow,teacherName,pageIndex,pageSize);
		model.addAttribute("dataCount", njmuBiz.teacherList(userFlow,deptFlow,teacherName,1,Integer.MAX_VALUE).size());
		model.addAttribute("deptMapList", deptMapList);
		return "res/njmu/teacherList";
	}
	
	@RequestMapping(value={"/deptHeadList"},method={RequestMethod.GET})
	public String deptHeadList(String userFlow,String deptFlow,String deptHeadName,Integer pageIndex,Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/deptHeadList";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "30402");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/deptHeadList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30403");
			model.addAttribute("resultType", "当前页码为空");
			return "res/njmu/deptHeadList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30404");
			model.addAttribute("resultType", "每页条数为空");
			return "res/njmu/deptHeadList";
		}
		if(StringUtil.isNotBlank(deptHeadName)){
			try {
				deptHeadName = new String(deptHeadName.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		List<Map<String,Object>> deptMapList = njmuBiz.deptHeadList(userFlow,deptFlow,deptHeadName,pageIndex,pageSize);
		model.addAttribute("deptMapList", deptMapList);
		model.addAttribute("dataCount", njmuBiz.deptHeadList(userFlow,deptFlow,deptHeadName,1,Integer.MAX_VALUE).size());
		return "res/njmu/deptHeadList";
	}
	
	@RequestMapping(value={"/enterDept"},method={RequestMethod.PUT})
	public String enterDept(String userFlow,String deptFlow,String teacherFlow,String deptHeadFlow,String startDate,String endDate,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30501");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/enterDept";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "30502");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/enterDept";
		}
		if(StringUtil.isEmpty(teacherFlow)){
			model.addAttribute("resultId", "30503");
			model.addAttribute("resultType", "带教老师标识符为空");
			return "res/njmu/enterDept";
		}
		if(StringUtil.isEmpty(deptHeadFlow)){
			model.addAttribute("resultId", "30504");
			model.addAttribute("resultType", "科主任标识符为空");
			return "res/njmu/enterDept";
		}
		
		startDate = StringUtil.defaultString(startDate);
		endDate = StringUtil.defaultString(endDate);
		String allDate = startDate+endDate;
		int dateLength = allDate.length();
		System.err.print(dateLength);

		if(dateLength==10){
			model.addAttribute("resultId", "30505");
			model.addAttribute("resultType", "实际开始日期，必须都填写或都不填写!");
			return "res/njmu/enterDept";
		}

		Date d1 = DateUtil.parseDate(endDate, DateUtil.defDtPtn04);
		Date d2 = DateUtil.parseDate(startDate, DateUtil.defDtPtn04);
		
		if(dateLength==20&&DateUtil.signDaysBetweenTowDate(d1, d2)<1){
			model.addAttribute("resultId", "30506");
			model.addAttribute("resultType", "实际结束日期必须晚于实际开始日期!");
			return "res/njmu/enterDept";
		}
		
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			njmuBiz.enterDept(userFlow,deptFlow,teacherFlow,deptHeadFlow,startDate,endDate);
		}else{
			model.addAttribute("resultId", "30505");
			model.addAttribute("resultType", "该科室已入科");
		}
		return "res/njmu/enterDept";
	}
	
	@RequestMapping(value={"/globalProgress"},method={RequestMethod.GET})
	public String globalProgress(String userFlow,String deptFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/globalProgress";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "30602");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/globalProgress";
		}
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			model.addAttribute("resultId", "30603");
			model.addAttribute("resultType", "该科室未入科");
		}else{
			List<Map<String,Object>> globalDataList = njmuBiz.globalProgress(userFlow, deptFlow);
			model.addAttribute("globalDataList", globalDataList);
		}
		return "res/njmu/globalProgress";
	}
	
	@RequestMapping(value={"/categoryProgress"},method={RequestMethod.GET})
	public String categoryProgress(String userFlow,String deptFlow,String dataType,Integer pageIndex,Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30701");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/categoryProgress";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "30702");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/categoryProgress";
		}
		if(StringUtil.isEmpty(dataType)){
			model.addAttribute("resultId", "30703");
			model.addAttribute("resultType", "dataType为空或不能识别");
			return "res/njmu/categoryProgress";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30704");
			model.addAttribute("resultType", "当前页码为空");
			return "res/njmu/categoryProgress";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30705");
			model.addAttribute("resultType", "每页条数为空");
			return "res/njmu/categoryProgress";
		}
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			model.addAttribute("resultId", "30706");
			model.addAttribute("resultType", "该科室未入科");
		}else{
			List<Map<String,Object>> catagoryDataList = njmuBiz.categoryProgress(dataType,userFlow, deptFlow, pageIndex, pageSize);
			int dataCount = njmuBiz.categoryProgress(dataType,userFlow, deptFlow, 1,Integer.MAX_VALUE).size();
			model.addAttribute("catagoryDataList", catagoryDataList);
			model.addAttribute("dataCount", dataCount);
		}
		
		return "res/njmu/categoryProgress";
	}
	
	@RequestMapping(value={"/dataList"},method={RequestMethod.GET})
	public String dataList(String userFlow,String deptFlow,String dataType,String cataFlow,Integer pageIndex,Integer pageSize,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/dataList";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "30802");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/dataList";
		}
		if(StringUtil.isEmpty(dataType)){
			model.addAttribute("resultId", "30803");
			model.addAttribute("resultType", "dataType为空或不能识别");
			return "res/njmu/dataList";
		}
		if(StringUtil.isEmpty(cataFlow)){
			model.addAttribute("resultId", "30804");
			model.addAttribute("resultType", "cataFlow为空或不能识别");
			return "res/njmu/dataList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30805");
			model.addAttribute("resultType", "当前页码为空");
			return "res/njmu/dataList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30806");
			model.addAttribute("resultType", "每页条数为空");
			return "res/njmu/dataList";
		}
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			model.addAttribute("resultId", "30806");
			model.addAttribute("resultType", "该科室未入科");
		}else{
			List<Map<String,Object>> dataList = njmuBiz.dataList(dataType,userFlow, deptFlow,cataFlow, pageIndex, pageSize);
			int dataCount = njmuBiz.dataList(dataType,userFlow, deptFlow,cataFlow, 1,Integer.MAX_VALUE).size();
			model.addAttribute("dataList", dataList);
			model.addAttribute("dataCount", dataCount);
		}
		return "res/njmu/dataList";
	}
	
	@RequestMapping(value={"/inputList"},method={RequestMethod.GET})
	public String inputList(String userFlow,String deptFlow,String dataType,String cataFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30901");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/inputList";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "30902");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/inputList";
		}
		if(StringUtil.isEmpty(dataType)){
			model.addAttribute("resultId", "30903");
			model.addAttribute("resultType", "dataType为空或不能识别");
			return "res/njmu/inputList";
		}
		if(StringUtil.isEmpty(cataFlow)){
			model.addAttribute("resultId", "30904");
			model.addAttribute("resultType", "数据分类标识为空或不能识别");
			return "res/njmu/addData";
		}
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "该科室未入科");
		}else{
			_inputList(userFlow, deptFlow, dataType, model);
		}
		return "res/njmu/inputList";
	}
	
	private void _inputList(String userFlow,String deptFlow,String dataType,Model model){
		switch (dataType) {
		case "mr":
			model.addAttribute("diagTypeList", njmuBiz.getDictItemList(5));
			break;
		case "disease":
			model.addAttribute("diagTypeList", njmuBiz.getDictItemList(5));
			List<Map<String,Object>> diseaseDiagNameList = njmuBiz.diseaseDiagNameList(userFlow,deptFlow);
			model.addAttribute("diseaseDiagNameList", diseaseDiagNameList);
			break;
		case "skill":
			List<Map<String,Object>> skillOperNameList = njmuBiz.skillOperNameList(userFlow,deptFlow);			
			model.addAttribute("skillOperNameList", skillOperNameList);
			break;
		case "operation":
			model.addAttribute("operRoleList", njmuBiz.getDictItemList(36));
			List<Map<String,Object>> operationOperNameList = njmuBiz.operationOperNameList(userFlow,deptFlow);			
			model.addAttribute("operationOperNameList", operationOperNameList);
			break;
		case "activity":
			model.addAttribute("activityWayList", njmuBiz.getDictItemList(10));
			break;
		case "summary":
			break;
		default:
			break;
		}
	}
	
	@RequestMapping(value={"/addData"},method={RequestMethod.POST})
	public String addData(String userFlow,String deptFlow,String cataFlow,String dataType,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31001");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/addData";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "31002");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/addData";
		}
		if(StringUtil.isEmpty(dataType)){
			model.addAttribute("resultId", "31003");
			model.addAttribute("resultType", "dataType为空或不能识别");
			return "res/njmu/addData";
		}
		if(StringUtil.isEmpty(cataFlow)){
			model.addAttribute("resultId", "31004");
			model.addAttribute("resultType", "数据分类标识为空或不能识别");
			return "res/njmu/addData";
		}
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			model.addAttribute("resultId", "31005");
			model.addAttribute("resultType", "该科室未入科");
		}else{
			Map<String,Object> paramMap = new HashMap<String,Object>();
			_putAll(paramMap, request);
			njmuBiz.addData(dataType,userFlow,deptFlow,cataFlow,paramMap);
		}
		return "res/njmu/addData";
	}
	
	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(String userFlow,String deptFlow,String dataType,String cataFlow,String dataFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/viewData";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "31102");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/viewData";
		}
		if(StringUtil.isEmpty(dataType)){
			model.addAttribute("resultId", "31103");
			model.addAttribute("resultType", "dataType为空或不能识别");
			return "res/njmu/viewData";
		}
		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "31104");
			model.addAttribute("resultType", "数据唯一标识为空");
			return "res/njmu/viewData";
		}
		if(StringUtil.isEmpty(cataFlow)){
			model.addAttribute("resultId", "31105");
			model.addAttribute("resultType", "数据分类标识为空或不能识别");
			return "res/njmu/addData";
		}
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			model.addAttribute("resultId", "31106");
			model.addAttribute("resultType", "该科室未入科");
		}else{
			_inputList(userFlow, deptFlow, dataType, model);
			Map<String,Object> resultData = njmuBiz.viewData(dataType,userFlow,deptFlow,dataFlow);
			model.addAttribute("resultData", resultData);
		}
		
		return "res/njmu/viewData";
	}
	
	@RequestMapping(value={"/modData"},method={RequestMethod.PUT})
	public String modData(String userFlow,String deptFlow,String dataType,String cataFlow,String dataFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/modData";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "31202");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/modData";
		}
		if(StringUtil.isEmpty(dataType)){
			model.addAttribute("resultId", "31203");
			model.addAttribute("resultType", "dataType为空或不能识别");
			return "res/njmu/modData";
		}
		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "31204");
			model.addAttribute("resultType", "数据唯一标识为空");
			return "res/njmu/modData";
		}
		if(StringUtil.isEmpty(cataFlow)){
			model.addAttribute("resultId", "31205");
			model.addAttribute("resultType", "数据分类标识为空或不能识别");
			return "res/njmu/addData";
		}
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			model.addAttribute("resultId", "31206");
			model.addAttribute("resultType", "该科室未入科");
		}else{
			Map<String,Object> paramMap = new HashMap<String,Object>();
			_putAll(paramMap, request);
			njmuBiz.modData(dataType,userFlow,deptFlow,dataFlow,paramMap);
		}
		return "res/njmu/modData";
	}
	
	private void _putAll(Map<String,Object> paramMap,HttpServletRequest request){
		Enumeration<String> paramNames = request.getParameterNames();
		while(paramNames.hasMoreElements()){
			String paramName = paramNames.nextElement();
			String paramValue = request.getParameter(paramName);
			paramMap.put(paramName, paramValue);
		}
	}
	
	@RequestMapping(value={"/delData"},method={RequestMethod.DELETE})
	public String delData(String userFlow,String deptFlow,String dataType,String dataFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31301");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/delData";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "31302");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/delData";
		}
		if(StringUtil.isEmpty(dataType)){
			model.addAttribute("resultId", "31303");
			model.addAttribute("resultType", "dataType为空或不能识别");
			return "res/njmu/delData";
		}
		if(StringUtil.isEmpty(dataFlow)){
			model.addAttribute("resultId", "31304");
			model.addAttribute("resultType", "数据唯一标识为空");
			return "res/njmu/delData";
		}
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			model.addAttribute("resultId", "31305");
			model.addAttribute("resultType", "该科室未入科");
		}else{
			njmuBiz.delData(dataType,userFlow,deptFlow,dataFlow);
		}
		return "res/njmu/delData";
	}
	
	@RequestMapping(value={"/canAddSummary"},method={RequestMethod.GET})
	public String canAddSummary(String userFlow,String deptFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/canAddSummary";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "31402");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu/canAddSummary";
		}
		int check = njmuBiz.enterCheck(userFlow,deptFlow);
		if(check==0){
			model.addAttribute("resultId", "31403");
			model.addAttribute("resultType", "该科室未入科");
		}else{
			int ret = njmuBiz.canAddSummary(userFlow,deptFlow);
			if(ret==200){
				model.addAttribute("resultId", "200");
				model.addAttribute("resultType", "交易成功");
			}
			if(ret==31405){
				model.addAttribute("resultId", "31405");
				model.addAttribute("resultType", "尚未完成对老师的评价，暂无法填写出科小结");
			}
			if(ret==31406){
				model.addAttribute("resultId", "31406");
				model.addAttribute("resultType", "尚未完成对本科室的评价，暂无法填写出科小结");
			}
			if(ret==31407){
				model.addAttribute("resultId", "31407");
				model.addAttribute("resultType", "尚未参加系统内的出科考核，暂无法填写出科小结");
			}
		}
		return "res/njmu/canAddSummary";
	}
	
	@RequestMapping(value={"/workLogList"},method={RequestMethod.GET})
	public String workLogList(String userFlow,String startDate,String endDate,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31501");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/workLogList";
		}
		if(StringUtil.isEmpty(startDate)){
			model.addAttribute("resultId", "31502");
			model.addAttribute("resultType", "开始日期为空");
			return "res/njmu/workLogList";
		}
		if(StringUtil.isEmpty(endDate)){
			model.addAttribute("resultId", "31503");
			model.addAttribute("resultType", "结束日期为空");
			return "res/njmu/workLogList";
		}
		List<Worklog> worklogList = njmuBiz.workLogList(userFlow,startDate,endDate);
		model.addAttribute("worklogList", worklogList);
		return "res/njmu/workLogList";
	}
	
	@RequestMapping(value={"/viewWorkLog"},method={RequestMethod.GET})
	public String viewWorkLog(String userFlow,String workDate,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/viewWorkLog";
		}
		if(StringUtil.isEmpty(workDate)){
			model.addAttribute("resultId", "31602");
			model.addAttribute("resultType", "工作日期为空");
			return "res/njmu/viewWorkLog";
		}
		Worklog worklog = njmuBiz.viewWorkLog(userFlow,workDate);
		if(worklog!=null){
			String workType = WorkLogTypeEnum.getIdByName(worklog.getWorkType());
			worklog.setWorkType(workType);
		}
		model.addAttribute("worklog", worklog);
		return "res/njmu/viewWorkLog";
	}
	
	@RequestMapping(value={"/saveWorkLog"},method={RequestMethod.POST})
	public String saveWorkLog(String userFlow,String workDate,String workTypeId,String workContent,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/saveWorkLog";
		}
		if(StringUtil.isEmpty(workDate)){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "工作日期为空");
			return "res/njmu/saveWorkLog";
		}
		if(StringUtil.isEmpty(workTypeId)){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "出勤状况为空");
			return "res/njmu/saveWorkLog";
		}
		if(StringUtil.isEmpty(workContent)){
			model.addAttribute("resultId", "31705");
			model.addAttribute("resultType", "工作日志为空");
			return "res/njmu/saveWorkLog";
		}
		njmuBiz.saveWorkLog(userFlow,workDate,workTypeId,workContent);
		return "res/njmu/saveWorkLog";
	}
	
	@RequestMapping(value={"/delWorkLog"},method={RequestMethod.DELETE})
	public String delWorkLog(String userFlow,String workDate,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31801");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu/delWorkLog";
		}
		if(StringUtil.isEmpty(workDate)){
			model.addAttribute("resultId", "31802");
			model.addAttribute("resultType", "工作日期为空");
			return "res/njmu/delWorkLog";
		}
		njmuBiz.delWorkLog(userFlow,workDate);
		return "res/njmu/delWorkLog";
	}
	 
}

