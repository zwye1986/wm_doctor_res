package com.pinde.res.ctrl.njmu2;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.pinde.core.commom.enums.SchUnitEnum;
import com.pinde.core.util.TimeUtil;
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
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.njmu2.impl.Njmu2AppBizImpl;
import com.pinde.res.biz.njmu2.impl.Njmu2StudentBizImpl;
import com.pinde.core.commom.enums.ResultEnum;
import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.ResSignin;

@Controller
@RequestMapping("/res/njmu2/student")
public class Njmu2StudentController{    
	private static Logger logger = LoggerFactory.getLogger(Njmu2StudentController.class);
	
	@Autowired
	private Njmu2StudentBizImpl njmu2StudentBiz;
	@Autowired
	private Njmu2AppBizImpl njmu2AppBiz;
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/stdp/500";
    } 
	
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/njmu2/student/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String deptFlow,String cataFlow,String dataFlow,String funcTypeId,String funcFlow,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("deptFlow", deptFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/njmu2/student/test";
	}

	@RequestMapping(value={"/deptList"},method={RequestMethod.GET})
	public String deptList(String userFlow,String searchData, Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3020101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu2/student/deptList";
		}
		
		if(pageIndex==null){
			model.addAttribute("resultId", "3020102");
			model.addAttribute("resultType", "起始页为空");
			return "res/njmu2/student/deptList";
		}
		
		if(pageSize==null){
			model.addAttribute("resultId", "3020103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/njmu2/student/deptList";
		}
		
		//查询条件
		//Entering(已入科) , NotEntered(未入科) , Exited(已出科) 
		String statusId = "";
		String deptName = "";
		if(StringUtil.isNotBlank(searchData)){
			try {
				//为json字符串转码
				searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			//转换json字符串为map对象
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			statusId = searchMap.get("statusId");
			deptName = searchMap.get("deptName");
		}
		
		//组织查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("doctorFlow",userFlow);
		paramMap.put("statusId",statusId);
		paramMap.put("deptName",deptName);
		if("Action2".equals(njmu2AppBiz.getCfgByCode("res_sch_action_type")))
		{
			paramMap.put("isAction2","Y");
		}else{
			paramMap.put("isAction2","N");
		}


		//按条件查询轮转数据
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> results = njmu2StudentBiz.searchResult(paramMap);
		
		if(results!=null && !results.isEmpty()){
			//轮转计划单位
			String unit = njmu2AppBiz.getCfgByCode("res_rotation_unit");
			
			//默认按月计算
			int step = 30;
			if(SchUnitEnum.Week.getId().equals(unit)){
				//如果是周按7天算/没配置或者选择月按30天
				step = 7;
			}
			
			//循环计算实际轮转月份
			for(Map<String,Object> map : results){
				//获取实际的开始时间与结束时间
				String startDate = (String)map.get("startDate");
				String endDate = (String)map.get("endDate");
				
				BigDecimal realMonth = BigDecimal.valueOf(0);
				if(StringUtil.isNotBlank(startDate)){
					if (SchUnitEnum.Week.getId().equals(unit)) {
						//如果是周按7天算/没配置或者选择月按30天
						step = 7;
						long realDays = DateUtil.signDaysBetweenTowDate(endDate,startDate)+1;
						if (realDays != 0) {
							//计算实际轮转的月/周数
							double realMonthF = (realDays / (step * 1.0));
							realMonth = BigDecimal.valueOf(realMonthF);
							realMonth = realMonth.setScale(1, BigDecimal.ROUND_HALF_UP);
						}
					}else{
						Map<String,String> map2= new HashMap<>();
						map2.put("startDate",startDate);
						map2.put("endDate",endDate);
						Double month = TimeUtil.getMonthsBetween(map2);
						realMonth=new BigDecimal(month).setScale(1, BigDecimal.ROUND_HALF_UP);
					}
				}
				map.put("realMonth",realMonth);
			}
		}
		model.addAttribute("results",results);
		
		//百分比算法
		Map<String,Object> deptPerMap = njmu2StudentBiz.getRegPer(0,userFlow);
		model.addAttribute("deptPerMap",deptPerMap);
		
		//数据量
		model.addAttribute("dataCount", PageHelper.total);
		
		return "res/njmu2/student/deptList";
	}
	
	@RequestMapping(value={"/funcList"},method={RequestMethod.GET})
	public String funcList(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/njmu2/student/funcList";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/njmu2/student/funcList";
		}
    	
		ResDoctorSchProcess process = njmu2StudentBiz.getProcessByResult(deptFlow);
		model.addAttribute("disabled",process==null);
		model.addAttribute("disabledTip","不能为未入科的科室填写数据！");

		if(process!=null){
			//百分比算法
			Map<String,Object> perMap = njmu2StudentBiz.getRegPer(0, userFlow, deptFlow,null,null,true);
			System.err.println(JSON.toJSON(perMap));
			model.addAttribute("perMap",perMap);
			List<ResSignin> signins = njmu2AppBiz.getSigninByDeptFlow(userFlow,process.getSchDeptFlow());
			if(signins!=null && !signins.isEmpty()){
				ResSignin signin = signins.get(0);
				if(signin!=null){
					model.addAttribute("lastSignin",signin.getSignDate());
				}
			}

		}
		return "res/njmu2/student/funcList";
	}
}

