package com.pinde.sci.ctrl.res;

import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResAttendanceBiz;
import com.pinde.sci.biz.res.IResOrgDateBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.JsresAttendanceDetail;
import com.pinde.sci.model.mo.ResOrgSigninDate;
import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SysUser;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/res/signin")
public class ResSigninController extends GeneralController{


	@Autowired
	private IResOrgDateBiz timebiz;

	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IResAttendanceBiz resAttendanceBiz;

	/**
	 * 设置签到时间
	 * @param model
	 * @return
	 */
	@RequestMapping("/signinSet")
	public String signinSet(Model model,HttpServletRequest request,Integer currentPage,
							String startDate,String endDate){

		String orgFlow =GlobalContext.getCurrentUser().getOrgFlow();

		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResOrgSigninDate> timeList = timebiz.readOrgTime(orgFlow,startDate,endDate);
		model.addAttribute("timeList",timeList);
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		return "res/signin/signinSet";
	}
	@RequestMapping("/addSigninDate")
	public String addSigninDate(Model model){
		return "res/signin/addSigninDate";
	}
	@RequestMapping("/delSigninDate")
	@ResponseBody
	public String delSigninDate(Model model,String recordFlow){
		ResOrgSigninDate date=new ResOrgSigninDate();
		date.setRecordFlow(recordFlow);
		date.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		int c=timebiz.saveOrgTime(date);
		if(c==0)
			return GlobalConstant.DELETE_FAIL;
		return GlobalConstant.DELETE_SUCCESSED;
	}
	@RequestMapping("/saveSigninDate")
	@ResponseBody
	public String saveSigninDate(Model model,String signinDate){
		String orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		ResOrgSigninDate date=timebiz.readOrgTimeByDate(orgFlow,signinDate);
		if(date!=null&&GlobalConstant.RECORD_STATUS_Y.equals(date.getRecordStatus()))
		{
			return "考勤日期【"+signinDate+"】在系统中已保存！";
		}
		if(date==null)
			date=new ResOrgSigninDate();
		date.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		date.setSigninDate(signinDate);
		date.setOrgFlow(orgFlow);
		int c=timebiz.saveOrgTime(date);
		if(c==0)
			return GlobalConstant.SAVE_FAIL;
		return GlobalConstant.SAVE_SUCCESSED;
	}
	/**
	 * 查看考勤信息
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param schStartDate
	 * @param schEndDate
	 * @param schDeptFlow
	 * @param teacherName
	 * @param statueId
	 * @param studentName
	 * @return
	 */
	@RequestMapping(value="signinlist")
	public String signinlist(Model model, Integer currentPage,
		 String sessionNumber, HttpServletRequest request, String schStartDate,
		 String schEndDate, String schDeptFlow, String teacherName,
		 String statueId, String studentName, String searchType,String [] datas){

		SysUser sysUser=GlobalContext.getCurrentUser();
		List<SchDept> deptList = schDeptBiz.searchSchDeptList(sysUser.getOrgFlow());
		model.addAttribute("deptList",deptList);

		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}else{
			List<SysDict> dicts=DictTypeEnum.DoctorType.getSysDictList();
			if(dicts!=null)
			{
				int i=0;
				datas=new String[dicts.size()];
				for(SysDict dict:dicts)
				{
					datas[i++]=dict.getDictId();
				}
				docTypeList = Arrays.asList(datas);
				for(String d : datas){
					dataStr += d+",";
				}
			}
		}
		model.addAttribute("dataStr",dataStr);
		model.addAttribute("docTypeList",docTypeList);
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getCurrDate();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.getFirstDayOfMonth();
		}
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("sessionNumber",sessionNumber);
		beMap.put("docTypeList",docTypeList);
		beMap.put("orgFlow",sysUser.getOrgFlow());
		if(StringUtil.isNotBlank(schDeptFlow)){
			beMap.put("schDeptFlow",schDeptFlow);
		}
		if(StringUtil.isNotBlank(teacherName)){
			beMap.put("teacherName",teacherName);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		if("1".equals(statueId)||"0".equals(statueId)){
			beMap.put("statueId",statueId);
		}

		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> signinlist=resAttendanceBiz.signinlist(beMap);
		Map<String,List> attendanceDetailMap = new HashMap<String,List>();
		for (Map<String,String> data : signinlist) {
			String jsResAttendanceFlow = data.get("attendanceFlow");
			if(StringUtil.isNotBlank(jsResAttendanceFlow)){
				List<JsresAttendanceDetail> JsresAttendanceDetails = resAttendanceBiz.searchJsresAttendanceDetailByAttendanceFlow(jsResAttendanceFlow);
				attendanceDetailMap.put(jsResAttendanceFlow,JsresAttendanceDetails);
			}
		}
		model.addAttribute("signinlist",signinlist);
		model.addAttribute("searchType",searchType);
		model.addAttribute("attendanceDetailMap",attendanceDetailMap);
		model.addAttribute("schEndDate",schEndDate);
		model.addAttribute("schStartDate",schStartDate);
		return "res/signin/signinlist";
	}
	/**
	 * 导出考勤表
	 * @param request
	 * @param response
	 * @param schStartDate
	 * @param schEndDate
	 * @param deptFlow
	 * @param teacherName
	 * @param statueId
	 * @param studentName
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportAttendance")
	public void signinlist(Model model,  HttpServletResponse response,
							 String sessionNumber, HttpServletRequest request, String schStartDate,
							 String schEndDate, String schDeptFlow, String teacherName,
							 String statueId, String studentName, String searchType,String [] datas) throws IOException {

		SysUser sysUser=GlobalContext.getCurrentUser();
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();

		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}else{
			List<SysDict> dicts=DictTypeEnum.DoctorType.getSysDictList();
			if(dicts!=null)
			{
				int i=0;
				datas=new String[dicts.size()];
				for(SysDict dict:dicts)
				{
					datas[i++]=dict.getDictId();
				}
				docTypeList = Arrays.asList(datas);
				for(String d : datas){
					dataStr += d+",";
				}
			}
		}
		model.addAttribute("dataStr",dataStr);
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getCurrDate();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.getFirstDayOfMonth();
		}
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("sessionNumber",sessionNumber);
		beMap.put("docTypeList",docTypeList);
		beMap.put("orgFlow",sysUser.getOrgFlow());
		if(StringUtil.isNotBlank(schDeptFlow)){
			beMap.put("schDeptFlow",schDeptFlow);
		}
		if(StringUtil.isNotBlank(teacherName)){
			beMap.put("teacherName",teacherName);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		if("1".equals(statueId)||"0".equals(statueId)){
			beMap.put("statueId",statueId);
		}
		List<Map<String,String>> signinlist=resAttendanceBiz.signinlist(beMap);
		String[] headLines = null;
		headLines = new String[]{
				"学员考勤记录表"
		};
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setWrapText(true);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);
		//列宽自适应
//		sheet.setDefaultColumnWidth((short)50);
		HSSFRow rowDep = sheet.createRow(0);//第一行
		rowDep.setHeightInPoints(20);
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("学员考勤记录表");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"编号",
				"日期",
				"姓名",
				"科室",
				"带教老师",
				"考勤记录",
				"出勤状态",
				"轮转状态",
				"学员备注"
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
		int rowNum = 2;
		String[] resultList = null;
		for (Map<String,String> data : signinlist) {
			HSSFRow rowFour = sheet.createRow(rowNum);//第二行
			String jsResAttendanceFlow = data.get("attendanceFlow");
			String attendanceDetailRecords = "";
			String attendanceSerfRemarks = "";
			if(StringUtil.isNotBlank(jsResAttendanceFlow)){
				List<JsresAttendanceDetail> JsresAttendanceDetails =
						resAttendanceBiz.searchJsresAttendanceDetailByAttendanceFlow(jsResAttendanceFlow);
				if(JsresAttendanceDetails != null && !JsresAttendanceDetails.isEmpty()){
					for (JsresAttendanceDetail detail: JsresAttendanceDetails) {
						if(StringUtil.isNotBlank(detail.getAttendTime())){
							attendanceDetailRecords += "\n"+detail.getAttendTime()+"  ";
						}
						if(StringUtil.isNotBlank(detail.getSelfRemarks())){
							attendanceSerfRemarks += "\n"+detail.getAttendTime() +" :" +detail.getSelfRemarks()+"  ";
						}
					}
				}
			}
			if(StringUtil.isBlank(attendanceDetailRecords.trim())){
				attendanceDetailRecords="暂无签到记录！";
			}
			if(StringUtil.isBlank(attendanceSerfRemarks.trim())){
				attendanceSerfRemarks="暂无备注！";
			}
			String signinDate=data.get("signinDate")==null?"":data.get("signinDate");
			String userName=data.get("userName")==null?"":data.get("userName");
			String schDeptName=data.get("schDeptName")==null?"":data.get("schDeptName");
			String teacherUserName=data.get("teacherUserName")==null?"":data.get("teacherUserName");
			String resultFlow=data.get("resultFlow")==null?"":data.get("resultFlow");
			String processFlow=data.get("processFlow")==null?"":data.get("processFlow");
			String schFlag=data.get("schFlag")==null?"":data.get("schFlag");
			String isCurrentFlag=data.get("isCurrentFlag")==null?"":data.get("isCurrentFlag");
			String statusName="缺勤";
			String schStatusName="--";
			String deptName="未安排";
			String teaName="--";
			if(StringUtil.isNotBlank(resultFlow))
			{
				deptName=schDeptName;
				if(StringUtil.isNotBlank(processFlow)&&StringUtil.isNotBlank(teacherUserName))
				{
					teaName=teacherUserName;
					if(GlobalConstant.FLAG_Y.equals(schFlag))
					{
						schStatusName="已出科";
					}else{
						if(GlobalConstant.FLAG_Y.equals(isCurrentFlag))
						{
							schStatusName="轮转中";
						}else{
							schStatusName="待入科";
						}
					}
				}else{
					schStatusName="待入科";
				}
			}
			if(StringUtil.isNotBlank(jsResAttendanceFlow))
			{
				statusName="出勤";
			}

			resultList = new String[]{
					rowNum-2+1+"",
					signinDate,
					userName,
					deptName,
					teaName,
					attendanceDetailRecords,
					statusName,
					schStatusName,
					attendanceSerfRemarks
			};
			for (int j = 0; j < titles.length; j++) {
				HSSFCell cellFirst = rowFour.createCell(j);
				cellFirst.setCellStyle(styleCenter);
				cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
			}
			rowNum++;
		}
		String fileName = "学员考勤记录表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

}
