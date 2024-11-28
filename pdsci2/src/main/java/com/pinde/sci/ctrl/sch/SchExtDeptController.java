package com.pinde.sci.ctrl.sch;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sch/schExtDept")
public class SchExtDeptController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(SchExtDeptController.class);
	
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExtRelBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchExternalDeptBiz schExternalDeptBiz;


	@RequestMapping(value = {"/main" }, method = RequestMethod.GET)
	public String setExternalDept (String schDeptFlow,String orgFlow,Model model) throws Exception{
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		
		return "sch/schExtDept/main";
	}
	@RequestMapping(value = {"/changRotation" })
	@ResponseBody
	public String changRotation (String speId,Model model) throws Exception{
		if(StringUtil.isBlank(speId))
		{
			return "[]";
		}
		List<SchRotation> rotations=schExternalDeptBiz.changRotation(speId);
		return JSON.toJSONString(rotations);
	}
	@RequestMapping(value = {"/searchDept" })
	public String searchDept (String rotationFlow,String sessionNumber,Model model) throws Exception{
		List<SchRotationDept> depts = schExternalDeptBiz.getSchRotationDepts(rotationFlow);
		model.addAttribute("depts",depts);
		return "sch/schExtDept/searchDept";
	}
	@RequestMapping(value = {"/schRotationDepts" })
	public String schRotationDepts (String recordFlow,Model model) throws Exception{
		SchRotationDept dept=schRotationDeptBiz.readSchRotationDept(recordFlow);
		SysUser user=GlobalContext.getCurrentUser();
		Map<String,String> params=new HashMap<>();
		params.put("standardDeptId",dept.getStandardDeptId());
		params.put("orgFlow",user.getOrgFlow());
		//获取对外开放的科室
		List<SchDept> schDepts=schExternalDeptBiz.getNotSelfSchDeptByDeptIdAndExternal(params);
		model.addAttribute("schDepts",schDepts);
		return "sch/schExtDept/schRotationDepts";
	}
	@RequestMapping(value = {"/schExternalDeptTimes" })
	public String schExternalDeptTimes (String schDeptFlow,String recordFlow,Model model) throws Exception{
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		SchRotationDept dept=schRotationDeptBiz.readSchRotationDept(recordFlow);
		List<SchExternalDept> depts=schExternalDeptBiz.getStandardSchDeptExtDepts(dept.getStandardDeptId(),schDeptFlow);
		model.addAttribute("extDepts",depts);
		Map<String,Object> peopleNumMap=new HashMap<>();
		for(SchExternalDept externalDept:depts)
		{
			Map<String,Object>time=new HashMap<>();
			time.put("startDate",externalDept.getStartDate());
			time.put("endDate",externalDept.getEndDate());
			time.put("schDeptFlow",externalDept.getSchDeptFlow());
			time.put("standardDeptId",dept.getStandardDeptId());
			int result=schExternalDeptBiz.isHaveSelect(time);
			peopleNumMap.put(externalDept.getRecordFlow(),result);
		}
		model.addAttribute("peopleNumMap",peopleNumMap);
		return "sch/schExtDept/schExternalDeptTimes";
	}

	/**
	 * 查询学员
	 * @param doctorCategoryId
	 * @param sessionNumber
	 * @param trainingSpeId
	 * @param rotationFlow
	 * @param model
	 * @return
     * @throws Exception
     */
	@RequestMapping(value = {"/students" })
	public String students (String doctorCategoryId,
							String sessionNumber,
							String trainingSpeId,
							String rotationFlow,
							Model model) throws Exception{
		SysUser user=GlobalContext.getCurrentUser();
		Map<String,String> params=new HashMap<>();
		params.put("doctorCategoryId",doctorCategoryId);
		params.put("sessionNumber",sessionNumber);
		params.put("trainingSpeId",trainingSpeId);
		params.put("rotationFlow",rotationFlow);
		params.put("orgFlow",user.getOrgFlow());
		List<ResDoctorExt> users=schExternalDeptBiz.getStudents(params);
		model.addAttribute("users",users);
		return "sch/schExtDept/students";
	}
	@RequestMapping(value = {"/getExtStudents" })
	public String getExtStudents (String doctorCategoryId,
									String sessionNumber,
									String trainingSpeId,Model model,
								  Integer currentPage,HttpServletRequest request) throws Exception{
		if(currentPage==null)
			currentPage=1;

		SysUser user=GlobalContext.getCurrentUser();
		Map<String,String> params=new HashMap<>();
		params.put("doctorCategoryId",doctorCategoryId);
		params.put("sessionNumber",sessionNumber);
		params.put("trainingSpeId",trainingSpeId);
		params.put("orgFlow",user.getOrgFlow());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> users=schExternalDeptBiz.getExtStudents(params);
		model.addAttribute("users",users);
		return "sch/schExtDept/extStudents";
	}
	@RequestMapping(value = {"/exportExtStudents" })
	public void exportExtStudents (String doctorCategoryId,
									String sessionNumber,
									String trainingSpeId, HttpServletResponse response
								  ) throws Exception{

		SysUser user=GlobalContext.getCurrentUser();
		Map<String,String> params=new HashMap<>();
		params.put("doctorCategoryId",doctorCategoryId);
		params.put("sessionNumber",sessionNumber);
		params.put("trainingSpeId",trainingSpeId);
		params.put("orgFlow",user.getOrgFlow());
		List<Map<String,Object>> users=schExternalDeptBiz.getExtStudents(params);

		SysOrg org=orgBiz.readSysOrg(user.getOrgFlow());
		String fileName = "学员外院轮转安排情况.xls";
		if(org!=null)
		{
			fileName="【"+org.getOrgName()+"】"+fileName;
		}
		String []titles = new String[]{
				"userName:学员姓名",
				"orgName:所在医院",
				"schDeptName:轮转科室",
				"schStartDate:开始时间",
				"schEndDate:结束时间",
				"schMonth:轮转时长（/个月）"
		};
		ExcleUtile.exportSimpleExcleByObjs(titles, users, response.getOutputStream());
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value = {"/delDoctorProcess" })
	@ResponseBody
	public synchronized String delDoctorProcess (String resultFlow,String processFlow) throws Exception{
		String result=schExternalDeptBiz.delDoctorProcess(resultFlow,processFlow);
		return result;
	}

	@RequestMapping(value = {"/addDoctorProcess" })
	@ResponseBody
	public synchronized String addDoctorProcess (String recordFlow,String schDeptFlow,String []recordFlows,String []doctorFlows) throws Exception{

		if(StringUtil.isBlank(recordFlow))
		{
			return "请选择标准科室！";
		}
		if(StringUtil.isBlank(schDeptFlow))
		{
			return "请选择轮转科室！";
		}
		if(recordFlows.length==0)
		{
			return "请选择轮转时间！";
		}
		if(doctorFlows.length ==0)
		{
			return "请选择需要安排的学员！";
		}
		String result=schExternalDeptBiz.addDoctorProcess(recordFlow,schDeptFlow,recordFlows,doctorFlows);
		return result;
	}


	@RequestMapping(value = {"/modifyProcessTime" })
	public String modifyProcessTime (String resultFlow,
									 String processFlow,Model mode) throws Exception{
		SchArrangeResult result=resultBiz.readSchArrangeResult(resultFlow);
		mode.addAttribute("result",result);
		ResDoctorSchProcess process=processBiz.read(processFlow);
		mode.addAttribute("process",process);
		return "sch/schExtDept/modifyProcessTime";
	}

	/**
	 * 修改轮转时间
	 * @param process
	 * @return
	 */
	@RequestMapping(value="/modifySchDate",method=RequestMethod.POST)
	@ResponseBody
	public String modifySchDate(ResDoctorSchProcess process) throws ParseException {
		ResDoctorSchProcess process2=processBiz.read(process.getProcessFlow());
			if (process2 != null) {
				SchArrangeResult sar = resultBiz.readSchArrangeResult(process.getSchResultFlow());
				if (sar != null) {
					sar.setSchStartDate(process.getSchStartDate());
					sar.setSchEndDate(process.getSchEndDate());
					//轮转计划单位
					String unit = InitConfig.getSysCfg("res_rotation_unit");
					//默认按月计算
					int step = 30;
                    if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(unit)) {
						//如果是周按7天算/没配置或者选择月按30天
						step = 7;
						BigDecimal realMonth = BigDecimal.valueOf(0);
						long realDays = DateUtil.signDaysBetweenTowDate(sar.getSchEndDate(), sar.getSchStartDate())+1;
						if (realDays != 0) {
							//计算实际轮转的月/周数
							double realMonthF = (realDays / (step * 1.0));
							realMonth = BigDecimal.valueOf(realMonthF);
							realMonth = realMonth.setScale(1, BigDecimal.ROUND_HALF_UP);
						}
						String schMonth = String.valueOf(realMonth.doubleValue());
						sar.setSchMonth(schMonth);
					}else{
						Map<String,String> map= new HashMap<>();
						map.put("startDate",sar.getSchStartDate());
						map.put("endDate",sar.getSchEndDate());
						Double month = TimeUtil.getMonthsBetween(map);
						String schMonth = String.valueOf(Double.parseDouble(month + ""));
						sar.setSchMonth(schMonth);
					}
					resultBiz.update(sar);
				}
			}
		int result = this.processBiz.edit(process);
        if (result == com.pinde.core.common.GlobalConstant.ONE_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
}

