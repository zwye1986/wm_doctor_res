package com.pinde.sci.ctrl.sch;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

@Controller
@RequestMapping("/sch/autoArrange")
public class SchAutoArrangeController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(SchAutoArrangeController.class);


	@Autowired
	private ISchAutoArrangeBiz autoArrangeBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;

	@RequestMapping(value = {"/scheduling" })
	public String scheduling (Model model) throws Exception{
		model.addAttribute("sessionNumber", DateUtil.getYear());
		return "sch/autoArrange/rostering";
	}
	@RequestMapping(value = {"/arrangeResult" })
	public String arrangeResult (Model model, String sessionNumber, String type, Integer currentPage, HttpServletRequest request) throws Exception{
		SysUser currUser =GlobalContext.getCurrentUser();
		String orgFlow=currUser.getOrgFlow();
		SchAutoArrangeCfg cfg=autoArrangeBiz.findSessionNumberStartDate(sessionNumber,orgFlow);
		if(cfg==null||StringUtil.isBlank(cfg.getStartDate()))
		{
			cfg.setStartDate(DateUtil.getCurrDate());
		}
		String startDate = cfg.getStartDate();
		String endDate = DateUtil.addYear(cfg.getStartDate(),3);
		List<String> titleDate = TimeUtil.getMonthsByTwoMonth(startDate.substring(0,7),endDate.substring(0,7));
		model.addAttribute("titles",titleDate);
		int need=autoArrangeBiz.getLastDoctorCount(orgFlow,sessionNumber);
		model.addAttribute("need",need);
		if("dept".equals(type))
		{
			PageHelper.startPage(currentPage,getPageSize(request));
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
			List<SchArrangeResult> arrResultList =autoArrangeBiz.getArrangeResult(startDate, endDate,orgFlow,sessionNumber, null);

			if(arrResultList != null && arrResultList.size()>0){
				Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for(SchArrangeResult sar : arrResultList){
					String schDeptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = TimeUtil.getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = schDeptFlow+month;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
					}
				}
				model.addAttribute("resultListMap",resultListMap);
			}
			model.addAttribute("schDeptList",schDeptList);
		}else{
			PageHelper.startPage(currentPage,getPageSize(request));
			List<SysUser> doctorList=autoArrangeBiz.getUserList(orgFlow,sessionNumber);
			if(doctorList!=null&&doctorList.size()>0)
			{
				List<String> doctorFlows=new ArrayList<>();
				for(SysUser u:doctorList)
				{
					doctorFlows.add(u.getUserFlow());
				}
				List<SchArrangeResult> arrResultList =autoArrangeBiz.getArrangeResult(startDate, endDate,"",sessionNumber,doctorFlows);

				if(arrResultList != null && arrResultList.size()>0){
					Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
					for(SchArrangeResult sar : arrResultList){
						String doctorFlow = sar.getDoctorFlow();
						String resultStartDate = sar.getSchStartDate();
						String resultEndDate = sar.getSchEndDate();
						if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = TimeUtil.getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = doctorFlow+month;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
					model.addAttribute("resultListMap",resultListMap);
				}
			}
			model.addAttribute("doctorList",doctorList);
		}
		return "sch/autoArrange/arrangeResult";
	}

	private void setColumnWidth(int length, int key, Map<Integer, Integer> columnWidth) {
		if(columnWidth.containsKey(key)) {
			Integer ol = columnWidth.get(key);
			if(ol<length)
				columnWidth.put(key,length);
		}else{
			columnWidth.put(key,length);
		}
	}
	@RequestMapping(value = {"/exportArrangeResult" })
	public void exportArrangeResult (Model model, String sessionNumber, String type, HttpServletResponse response, HttpServletRequest request) throws Exception{
		SysUser currUser =GlobalContext.getCurrentUser();
		String orgFlow=currUser.getOrgFlow();
		SchAutoArrangeCfg cfg=autoArrangeBiz.findSessionNumberStartDate(sessionNumber,orgFlow);
		if(cfg==null||StringUtil.isBlank(cfg.getStartDate()))
		{
			cfg.setStartDate(DateUtil.getCurrDate());
		}
		String startDate = cfg.getStartDate();
		String endDate = DateUtil.addYear(cfg.getStartDate(),3);

		List<String> titles = TimeUtil.getMonthsByTwoMonth(startDate.substring(0,7),endDate.substring(0,7));

		Workbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		Sheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		//HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		Font font =wb.createFont();
		font.setFontHeightInPoints((short)12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		style.setFont(font);
		Font fontTwo =wb.createFont();
		fontTwo.setFontHeightInPoints((short)12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		Map<Integer,Integer>  columnWidth=new HashMap<>();

		if("dept".equals(type))
		{
			Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
			List<SchArrangeResult> arrResultList =autoArrangeBiz.getArrangeResult(startDate, endDate,orgFlow,sessionNumber, null);
			if(arrResultList != null && arrResultList.size()>0){
				for(SchArrangeResult sar : arrResultList){
					String schDeptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = TimeUtil.getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = schDeptFlow+month;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
					}
				}
			}
			String fileName = "科室排班结果.xls";
			fileName = new String(fileName.getBytes("utf-8"),"ISO8859-1" );
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			OutputStream os=response.getOutputStream();
			Row row = sheet.createRow(0);
			Cell cell = null;
			cell = row.createCell(0);
			cell.setCellValue("科室");
			cell.setCellStyle(styleTwo);
			//宽度自适应
			setColumnWidth("科室".getBytes().length,0, columnWidth);
			for(int i = 0 ; i<titles.size() ; i++){
				String title = titles.get(i);
				cell = row.createCell(i+1);
				cell.setCellValue(title);
				cell.setCellStyle(styleTwo);
				//宽度自适应
				setColumnWidth(title.getBytes().length,i+1, columnWidth);
			}
			if(schDeptList!=null){
				Cell rowCell = null;
				int rowNum=1;
				for(int i=0; i<schDeptList.size() ; i++){
					SchDept schDept = schDeptList.get(i);

					int length=-1;
					for(int j=0;j<titles.size();j++) {
						String key = schDept.getSchDeptFlow() + titles.get(j);
						List<SchArrangeResult> t = resultListMap.get(key);
						if(t!=null)
						{
							if(length<t.size())
								length=t.size();
						}
					}
					if(length==-1)
						length=1;
					for(int m=0;m<length;m++)
					{
						row = sheet.createRow(rowNum++);
						if(m==0) {
							rowCell = row.createCell(0);
							rowCell.setCellValue(schDept.getSchDeptName());
							rowCell.setCellStyle(styleTwo);
							//宽度自适应
							setColumnWidth(schDept.getSchDeptName().getBytes().length, 0, columnWidth);
							sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, length - 1 + rowNum - 1, 0, 0));
						}
						for(int j=0;j<titles.size();j++)
						{
							String key =  schDept.getSchDeptFlow()+titles.get(j);
							List<SchArrangeResult> t=resultListMap.get(key);
							String result="";
							if(t!=null&&t.size()>0&&m<t.size())
							{
								SchArrangeResult r=t.get(m);
								result=r.getDoctorName()+"["+r.getSchStartDate()+"~"+r.getSchEndDate()+"]";
								//宽度自适应
								setColumnWidth(result.getBytes().length,j+1, columnWidth);
							}
							rowCell = row.createCell(j+1);
							rowCell.setCellValue(result);
							rowCell.setCellStyle(styleTwo);
						}
					}
				}
			}
			Set<Integer> keys=columnWidth.keySet();
			for(Integer key :keys)
			{
				int width=columnWidth.get(key);
				sheet.setColumnWidth(key,width*2*256);
			}
			wb.write(os);
			response.setContentType("application/octet-stream;charset=UTF-8");
		}else{
			Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
			List<SysUser> doctorList=autoArrangeBiz.getUserList(orgFlow,sessionNumber);
			if(doctorList!=null&&doctorList.size()>0)
			{
				List<String> doctorFlows=new ArrayList<>();
				for(SysUser u:doctorList)
				{
					doctorFlows.add(u.getUserFlow());
				}
				List<SchArrangeResult> arrResultList =autoArrangeBiz.getArrangeResult(startDate, endDate,"",sessionNumber,doctorFlows);

				if(arrResultList != null && arrResultList.size()>0){
					for(SchArrangeResult sar : arrResultList){
						String doctorFlow = sar.getDoctorFlow();
						String resultStartDate = sar.getSchStartDate();
						String resultEndDate = sar.getSchEndDate();
						if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = TimeUtil.getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = doctorFlow+month;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}
			}

			String fileName = "学员维度排班结果.xls";
			fileName = new String(fileName.getBytes("utf-8"),"ISO8859-1" );
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			OutputStream os=response.getOutputStream();
			Row row = sheet.createRow(0);
			Cell cell = null;
			cell = row.createCell(0);
			cell.setCellValue("姓名");
			cell.setCellStyle(style);
			//宽度自适应
			setColumnWidth("姓名".getBytes().length,0, columnWidth);
			for(int i = 0 ; i<titles.size() ; i++){
				String title = titles.get(i);
				cell = row.createCell(i+1);
				cell.setCellValue(title);
				cell.setCellStyle(style);
				//宽度自适应
				setColumnWidth(title.getBytes().length,0, columnWidth);
			}
			if(doctorList!=null){
				Cell rowCell = null;
				int rowNum=1;
				for(int i=0; i<doctorList.size() ; i++){
					SysUser sysUser = doctorList.get(i);
					int length=-1;
					for(int j=0;j<titles.size();j++) {
						String key = sysUser.getUserFlow() + titles.get(j);
						List<SchArrangeResult> t = resultListMap.get(key);
						if(t!=null)
						{
							if(length<t.size())
								length=t.size();
						}
					}
					if(length==-1)
						length=1;
					for(int m=0;m<length;m++)
					{
						row = sheet.createRow(rowNum++);
						if(m==0) {
							rowCell = row.createCell(0);
							rowCell.setCellValue(sysUser.getUserName());
							rowCell.setCellStyle(styleTwo);
							//宽度自适应
							setColumnWidth(sysUser.getUserName().getBytes().length, 0, columnWidth);
							sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, length - 1 + rowNum - 1, 0, 0));
						}
						for(int j=0;j<titles.size();j++)
						{
							String key = sysUser.getUserFlow()+titles.get(j);
							List<SchArrangeResult> t=resultListMap.get(key);
							String result="";
							if(t!=null&&t.size()>0&&m<t.size())
							{
								SchArrangeResult r=t.get(m);
								result=r.getSchDeptName()+"["+r.getSchStartDate()+"~"+r.getSchEndDate()+"]";
								//宽度自适应
								setColumnWidth(result.getBytes().length,j+1, columnWidth);
							}
							rowCell = row.createCell(j+1);
							rowCell.setCellValue(result);
							rowCell.setCellStyle(styleTwo);
						}
					}
				}
			}
			Set<Integer> keys=columnWidth.keySet();
			for(Integer key :keys)
				{
				int width=columnWidth.get(key);
				sheet.setColumnWidth(key,width*2*200);
			}
			wb.write(os);
			response.setContentType("application/octet-stream;charset=UTF-8");

		}
	}

	@RequestMapping(value = {"/checkStartTime" })
	@ResponseBody
	public String checkStartTime (Model model,String sessionNumber) throws Exception{
		if(StringUtil.isBlank(sessionNumber))
		{
			return "年级为空";
		}
		SysUser currUser =GlobalContext.getCurrentUser();
		String orgFlow=currUser.getOrgFlow();
		SchAutoArrangeCfg cfg=autoArrangeBiz.findSessionNumberStartDate(sessionNumber,orgFlow);
		if(cfg==null||StringUtil.isBlank(cfg.getStartDate()))
		{
			return "0";
		}
		return "1";
	}
	@RequestMapping(value = {"/saveStartDate" })
	@ResponseBody
	public String saveStartDate (Model model,SchAutoArrangeCfg cfg) throws Exception{
		if(StringUtil.isBlank(cfg.getSessionNumber()))
		{
			return "年级为空";
		}
		if(StringUtil.isBlank(cfg.getStartDate()))
		{
			return "开始时间为空";
		}
		SysUser currUser =GlobalContext.getCurrentUser();
		String orgFlow=currUser.getOrgFlow();
		cfg.setOrgFlow(orgFlow);
		cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		int c=autoArrangeBiz.saveSessionNumberStartDate(cfg);
		if(c==0)
		{
			return "设置失败，请重新设置！";
		}
		return "设置成功，开始排班";
	}
	@RequestMapping(value = {"/setStartTime" })
	public String setStartTime (Model model,String sessionNumber) throws Exception{
		SysUser currUser =GlobalContext.getCurrentUser();
		String orgFlow=currUser.getOrgFlow();
		SchAutoArrangeCfg cfg=autoArrangeBiz.findSessionNumberStartDate(sessionNumber,orgFlow);
		model.addAttribute("cfg",cfg);
		return "sch/autoArrange/setStartTime";
	}

	/**
	 * 校验前置条件
	 * 1、所有的轮转方案必须完成本地化
	 * 2、所有的学员必须有所属的轮转方案
	 * @param model
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value = {"/checkPrefixCondition" })
	public String checkPrefixCondition (Model model,String sessionNumber) throws Exception{
		if(StringUtil.isBlank(sessionNumber))
		{
			model.addAttribute("msg","请选择需要排班的年级！");
			return "sch/autoArrange/error";
		}
		SysUser currUser =GlobalContext.getCurrentUser();
		String orgFlow=currUser.getOrgFlow();
		//校验当前届别的是否已经初次排班过
		int arrangeCount=autoArrangeBiz.checkArrangeCount(orgFlow,sessionNumber);
		if(arrangeCount>0)
		{
			model.addAttribute("sessionNumber",sessionNumber);
			return "redirect:/sch/autoArrange/arrangeResult";
		}
		//校验当前是否有学员信息

		int c=autoArrangeBiz.checkOrgDoctorCount(orgFlow,sessionNumber, "");
		if(c<=0)
		{
			model.addAttribute("error","0");
			model.addAttribute("msg",c);
			return "sch/autoArrange/error";
		}
		//校验当前届别的所有轮转方案是否已经都完成本地化
		int rotationCount=autoArrangeBiz.checkRotationLocal(orgFlow,sessionNumber);
		if(rotationCount>0)
		{
			model.addAttribute("error","1");
			model.addAttribute("msg",rotationCount);
			return "sch/autoArrange/error";
		}
		//校验当前基地下学员是否都已分配轮转方案
		int doctorCount=autoArrangeBiz.checkDoctorCount(orgFlow,sessionNumber, "");
		if(doctorCount>0)
		{
			model.addAttribute("error","2");
			model.addAttribute("msg",doctorCount);
			return "sch/autoArrange/error";
		}
		//如果是中医的话需要校验二级轮转方案
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			 doctorCount=autoArrangeBiz.checkDoctorSecondCount(orgFlow,sessionNumber, "");
			if(doctorCount>0)
			{
				model.addAttribute("error","3");
				model.addAttribute("msg",doctorCount);
				return "sch/autoArrange/error";
			}
		}
		model.addAttribute("sessionNumber", DateUtil.getYear());
		int count=autoArrangeBiz.checkDoctorCycleCount(orgFlow,sessionNumber, "");
		if(count>0)
		{
			model.addAttribute("count",count);
		}
		SchAutoArrangeCfg cfg=autoArrangeBiz.findSessionNumberStartDate(sessionNumber,orgFlow);
		model.addAttribute("cfg",cfg);
		return "sch/autoArrange/start";
	}
	/**
	 * 校验前置条件
	 * 1、所有的轮转方案必须完成本地化
	 * 2、所有的学员必须有所属的轮转方案
	 * @param model
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value = {"/checkPrefixLastCondition" })
	@ResponseBody
	public String checkPrefixLastCondition (Model model,String sessionNumber) throws Exception{
		if(StringUtil.isBlank(sessionNumber))
		{
			return "请选择需要排班的年级！";
		}
		SysUser currUser =GlobalContext.getCurrentUser();
		String orgFlow=currUser.getOrgFlow();
		//校验当前是否有学员信息
		int c=autoArrangeBiz.checkOrgDoctorCount(orgFlow,sessionNumber,"Y");
		if(c<=0)
		{
			return "当前年级暂无需要排班的学员信息！";
		}
		//校验当前届别的所有轮转方案是否已经都完成本地化
		int rotationCount=autoArrangeBiz.checkRotationLocal(orgFlow,sessionNumber);
		if(rotationCount>0)
		{
			return "请完成方案配置后，再开始排班！";
		}
		//校验当前基地下学员是否都已分配轮转方案
		int doctorCount=autoArrangeBiz.checkDoctorCount(orgFlow,sessionNumber,"Y");
		if(doctorCount>0)
		{
			return "请为当前年级所有学员，分配方案后，再开始排班！";
		}
		//如果是中医的话需要校验二级轮转方案
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			doctorCount=autoArrangeBiz.checkDoctorSecondCount(orgFlow,sessionNumber,"Y");
			if(doctorCount>0)
			{
				return "请为当前年级所有学员，分配二级方案后，再开始排班！";
			}
		}
		int count=autoArrangeBiz.checkDoctorCycleCount(orgFlow,sessionNumber,"Y");
		if(count>0)
		{
			return count+"";
		}
		return "";
	}

	@RequestMapping(value = {"/startAutoArrange" })
	@ResponseBody
	public String startAutoArrange (Model model,String sessionNumber,String flag) throws Exception{
		String startTime=DateUtil.getCurrentTime();
		if(StringUtil.isBlank(sessionNumber))
		{
			return "未选择需要排班的年级！";
		}
		SysUser currUser =GlobalContext.getCurrentUser();
		String orgFlow=currUser.getOrgFlow();
		SchAutoArrangeCfg cfg=autoArrangeBiz.findSessionNumberStartDate(sessionNumber,orgFlow);
		if(cfg==null||StringUtil.isBlank(cfg.getStartDate()))
		{
			return "未配置排班开始时间！";
		}
		if(StringUtil.isBlank(flag)) {
			//校验当前届别的是否已经初次排班过
			int arrangeCount = autoArrangeBiz.checkArrangeCount(orgFlow, sessionNumber);
			if (arrangeCount > 0) {
				return "已为学员排班，请刷新页面！";
			}
		}
		//校验当前是否有学员信息
		int c=autoArrangeBiz.checkOrgDoctorCount(orgFlow,sessionNumber, flag);
		if(c<=0)
		{
			return "当前年级暂无学员信息，无法排班！";
		}
		//校验当前届别的所有轮转方案是否已经都完成本地化
		int rotationCount=autoArrangeBiz.checkRotationLocal(orgFlow,sessionNumber);
		if(rotationCount>0)
		{
			return "请完成方案配置后，再开始排班！";
		}
		//校验当前基地下学员是否都已分配轮转方案
		int doctorCount=autoArrangeBiz.checkDoctorCount(orgFlow,sessionNumber, flag);
		if(doctorCount>0)
		{
			return "请为当前年级所有学员，分配方案后，再开始排班！";
		}
		//如果是中医的话需要校验二级轮转方案
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			doctorCount=autoArrangeBiz.checkDoctorSecondCount(orgFlow,sessionNumber, flag);
			if(doctorCount>0)
			{
				return "请为当前年级所有学员，分配二级方案后，再开始排班！";
			}
		}
		String result=autoArrangeBiz.startAutoArrange(sessionNumber,orgFlow,cfg.getStartDate(),flag);
		if(StringUtil.isNotBlank(result))
		{
			return result;
		}
		String endTime=DateUtil.getCurrentTime();
		System.err.println(startTime);
		System.err.println(endTime);
		return "排班成功！";
	}
	@RequestMapping(value = {"/reStartAutoArrange" })
	@ResponseBody
	public String reStartAutoArrange (Model model,String sessionNumber) throws Exception{
		if(StringUtil.isBlank(sessionNumber))
		{
			return "未选择需要排班的年级！";
		}
		SysUser currUser =GlobalContext.getCurrentUser();
		String orgFlow=currUser.getOrgFlow();
		SchAutoArrangeCfg cfg=autoArrangeBiz.findSessionNumberStartDate(sessionNumber,orgFlow);
		//校验当前是否有学员信息
		int c=autoArrangeBiz.checkOrgDoctorCount(orgFlow,sessionNumber, "");
		if(c<=0)
		{
			return "当前年级暂无学员信息，无法排班！";
		}
		//校验当前届别的所有轮转方案是否已经都完成本地化
		int rotationCount=autoArrangeBiz.checkRotationLocal(orgFlow,sessionNumber);
		if(rotationCount>0)
		{
			return "请完成方案配置后，再开始排班！";
		}
		//校验当前基地下学员是否都已分配轮转方案
		int doctorCount=autoArrangeBiz.checkDoctorCount(orgFlow,sessionNumber, "");
		if(doctorCount>0)
		{
			return "请为当前年级所有学员，分配方案后，再开始排班！";
		}
		//如果是中医的话需要校验二级轮转方案
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			doctorCount=autoArrangeBiz.checkDoctorSecondCount(orgFlow,sessionNumber, "");
			if(doctorCount>0)
			{
				return "请为当前年级所有学员，分配二级方案后，再开始排班！";
			}
		}
		String result=autoArrangeBiz.reStartAutoArrange(sessionNumber,orgFlow,cfg.getStartDate());
		if(StringUtil.isNotBlank(result))
		{
			return result;
		}
		return "排班成功！";
	}
}

