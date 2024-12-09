package com.pinde.sci.ctrl.sch;

import com.pinde.core.model.SysUser;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResEnterOpenCfgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.poi.hssf.usermodel.*;

import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sch/arrangeImport")
public class SchArrangeImportController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(SchArrangeImportController.class);

	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ISchRotationCfgBiz rotationCfgBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchRotationGroupBiz schRotationtGroupBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IDeptBiz sysDeptBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResEnterOpenCfgBiz enterOpenCfgBiz;
	@Autowired
	private ISchDoctorSelectDeptBiz doctorDeptBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;


	@RequestMapping(value = "/main")
	public String main( HttpServletRequest request, Model model) {

		String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
		model.addAttribute("orgFlow",orgFlow);
		return "/sch/arrangeImport/main";
	}

	@RequestMapping(value = {"/students" })
	public String students (String doctorCategoryId,
							String sessionNumber,
							String trainingSpeId,
							String userName,
							Model model) throws Exception{
		SysUser user=GlobalContext.getCurrentUser();
		Map<String,String> params=new HashMap<>();
		params.put("doctorCategoryId",doctorCategoryId);
		params.put("sessionNumber",sessionNumber);
		params.put("trainingSpeId",trainingSpeId);
		params.put("userName",userName);
		params.put("orgFlow",user.getOrgFlow());
		List<ResDoctorExt> users=doctorBiz.getStudents(params);
		model.addAttribute("users",users);
		return "sch/arrangeImport/students";
	}
	@RequestMapping(value = {"/results"})
	public String results( String doctorFlow, Model model,String cycleTypeId,String selectYear) throws DocumentException {

		ResDoctor doctor=doctorBiz.readDoctor(doctorFlow);
		//查询学员选科信息
		List<SchArrangeResult> results=doctorDeptBiz.getCycleResults(doctorFlow,doctor.getSessionNumber(),doctor.getRotationFlow(),doctor.getOrgFlow());
		model.addAttribute("results",results);
		model.addAttribute("doctor",doctor);
		if(results!=null)
		{
			List<ResDoctorSchProcess> processList = processBiz.searchProcessByDoctor(doctorFlow);
			if(processList!=null && processList.size()>0){
				Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
				for(ResDoctorSchProcess process : processList){
					processMap.put(process.getSchResultFlow(),process);
				}
				model.addAttribute("processMap",processMap);
			}
		}
		return "sch/arrangeImport/results";
	}

	@RequestMapping(value = {"/showEditResult"})
	public String showEditResult(String doctorFlow,String resultFlow, Model model){
		ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
		if (doctor != null) {
			SchArrangeResult result=schArrangeResultBiz.readSchArrangeResult(resultFlow);
			model.addAttribute("result",result);
			List<SchRotationGroup> groups = new ArrayList<>();
			Map<String,List<SchRotationDept>> schDeptMap = new HashMap<>();
			if(StringUtil.isNotBlank(doctor.getRotationFlow())){
				String rotationFlow = doctor.getRotationFlow();
				List<SchRotationGroup> standardRotationGroupList = schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
				List<SchRotationDept> standardRotationDeptList = schRotationDeptBiz.searchSchRotationDept(rotationFlow);
				groups.addAll(standardRotationGroupList);
				if (standardRotationDeptList != null && standardRotationDeptList.size() > 0) {
					for(SchRotationDept tempDept : standardRotationDeptList){
						String key = tempDept.getGroupFlow();
						if(schDeptMap.get(key)==null){
							List<SchRotationDept> standardRotationDeptTempList = new ArrayList<SchRotationDept>();
							standardRotationDeptTempList.add(tempDept);
							schDeptMap.put(key,standardRotationDeptTempList);
						}else{
							schDeptMap.get(key).add(tempDept);
						}
					}
				}
				rotationFlow = doctor.getSecondRotationFlow();
				if(StringUtil.isNotBlank(rotationFlow)) {
					standardRotationGroupList = schRotationtGroupBiz.searchSchRotationGroup(rotationFlow);
					standardRotationDeptList = schRotationDeptBiz.searchSchRotationDept(rotationFlow);
					groups.addAll(standardRotationGroupList);
					if (standardRotationDeptList != null && standardRotationDeptList.size() > 0) {
						for (SchRotationDept tempDept : standardRotationDeptList) {
							String key = tempDept.getGroupFlow();
							if (schDeptMap.get(key) == null) {
								List<SchRotationDept> standardRotationDeptTempList = new ArrayList<SchRotationDept>();
								standardRotationDeptTempList.add(tempDept);
								schDeptMap.put(key, standardRotationDeptTempList);
							} else {
								schDeptMap.get(key).add(tempDept);
							}
						}
					}
				}
			}
			model.addAttribute("doctorFlow",doctorFlow);
			model.addAttribute("groups",groups);
			model.addAttribute("schDeptMap",schDeptMap);
			String orgFlow = "";
			orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
			List<SysDept> sysDeptList = sysDeptBiz.searchDeptByOrg(orgFlow);
			if(sysDeptList!=null && sysDeptList.size()>0){
				List<String> deptFlows = new ArrayList<String>();
				for(SysDept dept : sysDeptList){
					deptFlows.add(dept.getDeptFlow());
				}
				List<SchDept> deptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
				model.addAttribute("deptList",deptList);
			}
		}
		return "sch/arrangeImport/editResult";
	}

	@RequestMapping(value = {"/saveRostering"},method = RequestMethod.POST)
	@ResponseBody
	public String saveRostering(String doctorFlow,String resultFlow,String groupFlow,String standardDeptId,String standardDeptName,String schDeptFlow){
		if(StringUtil.isNotBlank(doctorFlow)){
			int result = doctorDeptBiz.saveRostering(doctorFlow,groupFlow,standardDeptId,standardDeptName,schDeptFlow,resultFlow);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	@RequestMapping(value="/updateResultStatus",method=RequestMethod.POST)
	@ResponseBody
	public String updateResultStatus(SchArrangeResult result){
		if(result!=null){
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(result.getRecordStatus())) {
				result.setSchStartDate("");
				result.setSchEndDate("");
			}
			String resultFlow = result.getResultFlow();
			ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);
			if (process != null) {
				//停用
                if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_D.equals(result.getRecordStatus())) {
					//若管理员移除了排班，则不再展示移除的科室；恢复后，学员需重新填写带教老师和科主任入科
					process.setTeacherUserFlow("");
					process.setTeacherUserName("");
					process.setHeadUserFlow("");
					process.setHeadUserName("");
                    process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
                    process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);
				}
				//删除
                if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(result.getRecordStatus())) {
                    process.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				}
				process.setSchStartDate(result.getSchStartDate());
				process.setSchEndDate(result.getSchEndDate());
				processBiz.saveProcess(process);
			}
			int resultFlag = schArrangeResultBiz.saveSchArrangeResult(result);
            if (resultFlag != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	@RequestMapping(value = "/sortAndCalculateTwo", method = RequestMethod.POST)
	@ResponseBody
	public String sortAndCalculateTwo(String[] resultFlow, String startDate, boolean clear, Integer resultNum, boolean addOneDay, String mustDate) {
        boolean ismust = com.pinde.core.common.GlobalConstant.FLAG_Y.equals(mustDate);

		if (resultFlow != null && resultFlow.length > 0) {
			String st = "";
			List<String> strlist = new ArrayList();
			for (String strings : resultFlow) {
				strlist.add(strings);
			}
			for (String strs : strlist) {
				if (!strs.equals("")) {
					st += strs + ",";
				}
			}
			resultFlow = st.split("\\,");

			List<SchArrangeResult> resultList = new ArrayList<SchArrangeResult>();
			List<String> resultFlows = new ArrayList<String>();
			int seq = resultNum - (resultFlow.length);
			for (String resultFlowTemp : resultFlow) {
				resultFlows.add(resultFlowTemp);
				SchArrangeResult result = new SchArrangeResult();
				result.setResultFlow(resultFlowTemp);
				result.setSchDeptOrder(BigDecimal.valueOf(seq++));
				resultList.add(result);
				if (clear) {
					result.setSchStartDate("");
					result.setSchEndDate("");
				}
			}
			if(clear){
				int result = schArrangeResultBiz.saveSchArrangeResults(resultList);
                if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
					schArrangeResultBiz.synchronizeProcessByResults(resultList);
                    return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
				}
			}
			if (StringUtil.isNotBlank(startDate)) {
				if (addOneDay) {
					startDate = DateUtil.addDate(startDate, 1);
				}
				List<SchArrangeResult> resultListTemp = schArrangeResultBiz.searchArrangeResultByResultFlow(resultFlows);
				Map<String, String> schMonthMap = new HashMap<String, String>();
				for (SchArrangeResult monthResult : resultListTemp) {
					schMonthMap.put(monthResult.getResultFlow(), monthResult.getSchMonth());
				}
				resultList = calculatePlanTwo(resultList, startDate, schMonthMap, ismust);
			}else {
				List<SchArrangeResult> resultListTemp = schArrangeResultBiz.searchArrangeResultByResultFlow(resultFlows);
				if(!clear){
					startDate = resultListTemp.get(0).getSchStartDate();
				}
				Map<String, String> schMonthMap = new HashMap<String, String>();
				for (SchArrangeResult monthResult : resultListTemp) {
					schMonthMap.put(monthResult.getResultFlow(), monthResult.getSchMonth());
				}
				resultList = calculatePlanTwo(resultList, startDate, schMonthMap, ismust);
			}
			int result = schArrangeResultBiz.saveSchArrangeResults(resultList);
            if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
				schArrangeResultBiz.synchronizeProcessByResults(resultList);
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}
	@RequestMapping(value="/saveDiyDate",method=RequestMethod.POST)
	@ResponseBody
	public String saveDiyDate(SchArrangeResult result){
		if(result!=null){
			int resultFlag = schArrangeResultBiz.saveSchArrangeResult(result);
            if (resultFlag != com.pinde.core.common.GlobalConstant.ZERO_LINE) {

				ResDoctorSchProcess process = processBiz.searchByResultFlow(result.getResultFlow());

				if (process != null) {
					process.setSchStartDate(result.getSchStartDate());
					process.setSchEndDate(result.getSchEndDate());
					process.setStartDate(result.getSchStartDate());
					process.setEndDate(result.getSchEndDate());
					processBiz.saveProcess(process);
				}
				if(StringUtil.isNotBlank(result.getSchEndDate())){
					return result.getSchEndDate();
				}else{
                    return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	private List<SchArrangeResult> calculatePlanTwo(List<SchArrangeResult> sortResult, String startDate, Map<String, String> schMonthMap, boolean ismust) {
		if (sortResult != null && sortResult.size() > 0) {
			for (SchArrangeResult result : sortResult) {
				String resultFlow = result.getResultFlow();
				ResDoctorSchProcess process = processBiz.searchByResultFlow(resultFlow);

                if (process != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(process.getIsExternal())) {
					//如果是外院轮转的开始时间和结束时间不可修改，但须记录开始和结束日期
					startDate = DateUtil.addDate(process.getSchEndDate(), 1);
				} else {
					//如果不是外院轮转的开始时间和结束时间可以修改
					String step = schMonthMap.get(result.getResultFlow());
					if(StringUtil.isNotBlank(step)&&Float.parseFloat(step)==0)
					{
						result.setSchStartDate("");
						result.setSchEndDate("");
					}else {
						result.setSchStartDate(startDate);
						String endDate = "";
						try {
                            if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"))) {
								endDate = calculateDate(startDate, step);
							} else {
								endDate = DateUtil.addMonthForArrange(startDate, step, ismust);
							}
						} catch (Exception e) {
							endDate = calculateDate(startDate, step);
						}
						result.setSchEndDate(endDate);
						startDate = DateUtil.addDate(endDate, 1);
					}
				}
			}
		}
		return sortResult;
	}

	private String calculateDate(String date, String step) {
		if (StringUtil.isNotBlank(date) && StringUtil.isNotBlank(step)) {
			float stepFloat = Float.parseFloat(step);
			int stepInt = (int) stepFloat;
			float stepHalf = stepInt != 0 ? stepFloat % stepInt : stepFloat;
            if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"))) {
				if (stepInt != 0) {
					date = DateUtil.addDate(date, stepInt * 7);
				}
				if (stepHalf > 0) {
					date = DateUtil.addDate(date, 3);
				}
			} else {
				if (stepInt != 0) {
					date = DateUtil.newDateOfAddMonths(date, stepInt);
				}
				if (stepHalf > 0) {
					date = DateUtil.addDate(date, 15);
				}
			}
			date = DateUtil.addDate(date, -1);
		}
		return date;
	}

	@RequestMapping(value = {"/downGroupInfo" })
	public void downGroupInfo (	HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

		List<SchRotation> rotationList =schRotationtBiz.searchSchRotationByIsPublish();

		createExcle2(response,rotationList);
	}
	private void createExcle2(HttpServletResponse response, List<SchRotation> rotationList) throws IOException {
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //居中
		styleRight.setAlignment(HorizontalAlignment.RIGHT);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);
		String[] titles1 = new String[]{
				"轮转组",
				"轮转组代码",
				"标准科室",
				"标准科室代码"
		};
		String[] titles2 = new String[]{
				"医院科室",
				"医院科室代码",
				"轮转科室",
				"轮转科室代码"
		};

		String orgFlow = "";
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> sysDeptList = sysDeptBiz.searchDeptByOrg(orgFlow);
		if(sysDeptList!=null && sysDeptList.size()>0){
			List<String> deptFlows = new ArrayList<String>();
			for(SysDept dept : sysDeptList){
				deptFlows.add(dept.getDeptFlow());
			}
			List<SchDept> deptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
			HSSFSheet sheet = wb.createSheet("科室信息");
			int rowNum=0;
			HSSFRow rowOne = sheet.createRow(rowNum++);//第一行

			HSSFCell cellTitle = null;
			for (int i = 0; i < titles2.length; i++) {
				cellTitle = rowOne.createCell(i);
				cellTitle.setCellValue(titles2[i]);
				cellTitle.setCellStyle(styleCenter);
			}
			if(deptList!=null)
			{
				for(SchDept dept:deptList)
				{
					rowOne = sheet.createRow(rowNum++);//第一行
					cellTitle = rowOne.createCell(0);
					cellTitle.setCellValue(dept.getDeptName().toString());
					cellTitle.setCellStyle(styleCenter);
					cellTitle = rowOne.createCell(1);
					cellTitle.setCellValue(dept.getDeptFlow().toString());
					cellTitle.setCellStyle(styleCenter);
					cellTitle = rowOne.createCell(2);
					cellTitle.setCellValue(dept.getSchDeptName().toString());
					cellTitle.setCellStyle(styleCenter);
					cellTitle = rowOne.createCell(3);
					cellTitle.setCellValue(dept.getSchDeptFlow().toString());
					cellTitle.setCellStyle(styleCenter);
				}
			}
		}
		if(rotationList!=null)
		{
			int n=0;
			for(SchRotation rotation:rotationList)
			{
				String sheetName=rotation.getRotationName()+"-"+rotation.getDoctorCategoryName()
						+"-"+rotation.getSpeName()+rotation.getRotationFlow();
				HSSFSheet sheet = wb.createSheet(rotation.getRotationName()+"-"+n++);
				int rowNum=0;

				HSSFRow head = sheet.createRow(rowNum++);//第一行
				HSSFCell cellTitle = head.createCell(0);
				cellTitle.setCellValue(sheetName);
				cellTitle.setCellStyle(styleCenter);
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));//合并单元格

				HSSFRow rowOne = sheet.createRow(rowNum++);//第一行
				 cellTitle = null;
				for (int i = 0; i < titles1.length; i++) {
					cellTitle = rowOne.createCell(i);
					cellTitle.setCellValue(titles1[i]);
					cellTitle.setCellStyle(styleCenter);
				}
				List<SchRotationGroup> groups=schRotationtGroupBiz.searchSchRotationGroup(rotation.getRotationFlow());
				if(groups!=null)
				{
					for(SchRotationGroup group:groups)
					{
						List<SchRotationDept> depts=schRotationtGroupBiz.readSchRotationDept(group.getGroupFlow());;
						if(depts!=null)
						{
							for(SchRotationDept dept:depts)
							{
								rowOne = sheet.createRow(rowNum++);//第一行
								cellTitle = rowOne.createCell(0);
								cellTitle.setCellValue(group.getGroupName().toString());
								cellTitle.setCellStyle(styleCenter);
								cellTitle = rowOne.createCell(1);
								cellTitle.setCellValue(group.getGroupFlow().toString());
								cellTitle.setCellStyle(styleCenter);
								cellTitle = rowOne.createCell(2);
								cellTitle.setCellValue(dept.getStandardDeptName().toString());
								cellTitle.setCellStyle(styleCenter);
								cellTitle = rowOne.createCell(3);
								cellTitle.setCellValue(dept.getStandardDeptId().toString());
								cellTitle.setCellStyle(styleCenter);
							}
						}
					}

				}
			}
		}
		String name = "轮转组与标准科室信息表.xls";
		response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
		response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}


	@RequestMapping(value="/importArrange")
	@ResponseBody
	public String importArrange(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) doctorDeptBiz.importArrange(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}

		}
		return "文件内容不能为空";
	}
	@RequestMapping(value = {"/downMoban" })
	public void downMoban (	String startDate,String endDate,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		List<String> title=new ArrayList<>();
		title.add("姓名");
		title.add("证件号");
		title.add("年级");
		title.add("培训专业");
		title.add("培养年限");
		if(StringUtil.isNotBlank(startDate)&&StringUtil.isNotBlank(endDate))
		{
			List<String> times= TimeUtil.getMonthsByTwoMonth(startDate,endDate);
			if(times!=null)
				title.addAll(times);
		}
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //居中
		styleRight.setAlignment(HorizontalAlignment.RIGHT);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFSheet sheet = wb.createSheet("ArrangeResult");
		int rowNum=0;
		HSSFRow rowOne = sheet.createRow(rowNum++);//第一行

		HSSFCell cellTitle = null;
		for (int i = 0; i < title.size(); i++) {
			cellTitle = rowOne.createCell(i);
			cellTitle.setCellValue(title.get(i));
			cellTitle.setCellStyle(styleCenter);
		}
		String name = "PbImportModel.xls";
		response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
		response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}
	@RequestMapping(value="/importArrange2")
	@ResponseBody
	public String importArrange2(String startDate,String endDate,MultipartFile file){
		if(StringUtil.isBlank(startDate))
		{
			return "请选择开始月份!";
		}
		if(StringUtil.isBlank(endDate))
		{
			return "请选择结束月份!";
		}
		if(startDate.compareTo(endDate)>0)
		{
			return "开始月份不得大于结束月份!";
		}
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) doctorDeptBiz.importArrange2(file,startDate,endDate);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}

		}
		return "文件内容不能为空";
	}

}


