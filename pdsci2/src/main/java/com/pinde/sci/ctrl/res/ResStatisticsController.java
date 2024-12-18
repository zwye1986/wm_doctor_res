package com.pinde.sci.ctrl.res;


import com.pinde.core.model.SysOrg;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResStatisticBiz;
import com.pinde.sci.biz.res.ISysMonthlyStatisticsBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.core.model.ResJointOrg;
import com.pinde.core.model.SysMonthlyStatistics;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/statistics")
public class ResStatisticsController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResStatisticsController.class);
	@Autowired
	private IResStatisticBiz resStatisticBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ISysMonthlyStatisticsBiz sysMonthlyStatisticsBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;

	@RequestMapping("/chartSelect")
	public String chartSelect(Model model){
		String sessionNumber ;
		int year = 0;
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		if(month<9){
			year = cal.get(Calendar.YEAR) - 1;
		}else{
			year = cal.get(Calendar.YEAR);
		}
		sessionNumber = String.valueOf(year);
		model.addAttribute("sessionNumber",sessionNumber);
		return "res/platform/statistics/chartSelect";
	}

	@RequestMapping("/cityStatistic")
	public String cityStatistic(String sessionNumber, Model model){
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if(month<9){
				year = cal.get(Calendar.YEAR) - 1;
			}else{
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = String.valueOf(year);
		}
		if("all".equals(sessionNumber)){
			sessionNumber = "";
		}
	//地市 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> CityList=resStatisticBiz.findCityDocCount(sessionNumber);
		Map<String,Integer> inGraduateMap = new HashMap<>();
		Map<String,Integer> noGraduateMap = new HashMap<>();
		List<String> CityIds=new ArrayList<>();
		Map<String,String> cityMap = new HashMap<>();
		int sum = 0;
		int inGraduateSum = 0;
		int noGraduateSum = 0;
		Boolean flag = false;
		if(CityList!=null&&CityList.size()>0){
			for(Map<String,String> m:CityList) {
				int num = Integer.parseInt(m.get("docNum"));
				sum+=num;
				String cityId=m.get("orgCityId");
				if(StringUtil.isNotBlank(cityId)){
					if(!CityIds.contains(cityId)){
						CityIds.add(cityId);
						cityMap.put(cityId,m.get("orgCityName"));
					}
				}else {
					flag=true;
				}
			}
			if(flag){
				CityIds.add("others");
				cityMap.put("others","其他");
			}
			if(CityIds!=null&&CityList.size()>0){
				for(String cityId:CityIds){
					for(Map<String,String> m:CityList) {
						int num = Integer.parseInt(m.get("docNum"));
						if(StringUtil.isNotBlank(m.get("orgCityId"))) {
							if (cityId.equals(m.get("orgCityId"))) {
								if ("Graduate".equals(m.get("doctorTypeId"))) {
									inGraduateSum += num;
									inGraduateMap.put(cityId, inGraduateSum);
								} else {
									noGraduateSum += num;
									inGraduateSum += num;
									noGraduateMap.put(cityId, noGraduateSum);
									inGraduateMap.put(cityId, inGraduateSum);
								}
							}
						}else{
							if ("Graduate".equals(m.get("doctorTypeId"))) {
								inGraduateSum += num;
								inGraduateMap.put("others", inGraduateSum);
							} else {
								noGraduateSum += num;
								inGraduateSum += num;
								noGraduateMap.put("others", noGraduateSum);
								inGraduateMap.put("others", inGraduateSum);
							}
						}
					}
					noGraduateSum = 0;
					inGraduateSum = 0;
				}
			}
		}
		model.addAttribute("CityIds",CityIds);
		model.addAttribute("cityMap",cityMap);
		model.addAttribute("noGraduateMap",noGraduateMap);
		model.addAttribute("inGraduateMap",inGraduateMap);
		model.addAttribute("sum",sum);
		model.addAttribute("title", "各地区住院医师人员统计");
		return "res/platform/statistics/cityStatistics";
	}

	@RequestMapping("/hospitalStatistic")
	public String hospitalStatistic(String sessionNumber, Model model){
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if(month<9){
				year = cal.get(Calendar.YEAR) - 1;
			}else{
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = String.valueOf(year);
		}
		if("all".equals(sessionNumber)){
			sessionNumber = "";
		}
		//基地 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> CityList=resStatisticBiz.findCountryDocCount(sessionNumber);
		Map<String,Integer> inGraduateMap = new HashMap<>();
		Map<String,Integer> noGraduateMap = new HashMap<>();
		List<String> orgFlows=new ArrayList<>();
		Map<String,String> cityMap = new HashMap<>();
		int sum = 0;
		int inGraduateSum = 0;
		int noGraduateSum = 0;
		Boolean flag = false;
		if(CityList!=null&&CityList.size()>0){
			for(Map<String,String> m:CityList) {
				int num = Integer.parseInt(m.get("docNum"));
				sum+=num;
				String orgFlow=m.get("orgFlow");
				if(StringUtil.isNotBlank(orgFlow)){
					if(!orgFlows.contains(orgFlow)){
						orgFlows.add(orgFlow);
						cityMap.put(orgFlow,m.get("orgName"));
					}
				}else {
					flag=true;
				}
			}
			if(flag){
				orgFlows.add("others");
				cityMap.put("others","其他");
			}
			if(orgFlows!=null&&CityList.size()>0){
				for(String orgFlow:orgFlows){
					for(Map<String,String> m:CityList) {
						int num = Integer.parseInt(m.get("docNum"));
						if(StringUtil.isNotBlank(m.get("orgFlow"))) {
							if (orgFlow.equals(m.get("orgFlow"))) {
								if ("Graduate".equals(m.get("doctorTypeId"))) {
									inGraduateSum += num;
									inGraduateMap.put(orgFlow, inGraduateSum);
								} else {
									noGraduateSum += num;
									inGraduateSum += num;
									noGraduateMap.put(orgFlow, noGraduateSum);
									inGraduateMap.put(orgFlow, inGraduateSum);
								}
							}
						}else{
							if ("Graduate".equals(m.get("doctorTypeId"))) {
								inGraduateSum += num;
								inGraduateMap.put("others", inGraduateSum);
							} else {
								noGraduateSum += num;
								inGraduateSum += num;
								noGraduateMap.put("others", noGraduateSum);
								inGraduateMap.put("others", inGraduateSum);
							}
						}
					}
					noGraduateSum = 0;
					inGraduateSum = 0;
				}
			}
		}
		model.addAttribute("CityIds",orgFlows);
		model.addAttribute("cityMap",cityMap);
		model.addAttribute("noGraduateMap",noGraduateMap);
		model.addAttribute("inGraduateMap",inGraduateMap);
		model.addAttribute("sum",sum);
		model.addAttribute("title", "国家基地（含协同）住院医师人员统计");
		return "res/platform/statistics/cityStatistics";
	}

	@RequestMapping("/speStatistic")
	public String speStatistic(String sessionNumber, Model model){
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if(month<9){
				year = cal.get(Calendar.YEAR) - 1;
			}else{
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = String.valueOf(year);
		}
		if("all".equals(sessionNumber)){
			sessionNumber = "";
		}
		//基地 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> CityList=resStatisticBiz.findCountrySpeDocCount(sessionNumber);
		Map<String,Integer> inGraduateMap = new HashMap<>();
		Map<String,Integer> noGraduateMap = new HashMap<>();
		List<String> speIds=new ArrayList<>();
		Map<String,String> cityMap = new HashMap<>();
		int sum = 0;
		int inGraduateSum = 0;
		int noGraduateSum = 0;
		Boolean flag = false;
		if(CityList!=null&&CityList.size()>0){
			for(Map<String,String> m:CityList) {
				int num = Integer.parseInt(m.get("docNum"));
				sum+=num;
				String speId=m.get("speId");
				if(StringUtil.isNotBlank(speId)){
					if(!speIds.contains(speId)){
						speIds.add(speId);
						cityMap.put(speId,m.get("speName"));
					}
				}else {
					flag=true;
				}
			}
			if(flag){
				speIds.add("others");
				cityMap.put("others","其他");
			}
			if(speIds!=null&&CityList.size()>0){
				for(String speId:speIds){
					for(Map<String,String> m:CityList) {
						int num = Integer.parseInt(m.get("docNum"));
						if(StringUtil.isNotBlank(m.get("speId"))) {
							if (speId.equals(m.get("speId"))) {
								if ("Graduate".equals(m.get("doctorTypeId"))) {
									inGraduateSum += num;
									inGraduateMap.put(speId, inGraduateSum);
								} else {
									noGraduateSum += num;
									inGraduateSum += num;
									noGraduateMap.put(speId, noGraduateSum);
									inGraduateMap.put(speId, inGraduateSum);
								}
							}
						}else{
							if ("Graduate".equals(m.get("doctorTypeId"))) {
								inGraduateSum += num;
								inGraduateMap.put("others", inGraduateSum);
							} else {
								noGraduateSum += num;
								inGraduateSum += num;
								noGraduateMap.put("others", noGraduateSum);
								inGraduateMap.put("others", inGraduateSum);
							}
						}
					}
					noGraduateSum = 0;
					inGraduateSum = 0;
				}
			}
		}
		model.addAttribute("CityIds",speIds);
		model.addAttribute("cityMap",cityMap);
		model.addAttribute("noGraduateMap",noGraduateMap);
		model.addAttribute("inGraduateMap",inGraduateMap);
		model.addAttribute("sum",sum);
		model.addAttribute("title", "国家基地（含协同）各专业住院医师人员统计");
		return "res/platform/statistics/cityStatistics";
	}

	private String getYear(){
		String sessionNumber;
		int year = 0;
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		if(month<9){
			year = cal.get(Calendar.YEAR) - 1;
		}else{
			year = cal.get(Calendar.YEAR);
		}
		sessionNumber = String.valueOf(year);
		return sessionNumber;
	}

	@RequestMapping("/chart2Select")
	public String chart2Select(Model model){
		model.addAttribute("sessionNumber",getYear());
		return "res/platform/statistics/chart2Select";
	}

	@RequestMapping("/masterSchool")
	public String masterSchool(String sessionNumber, String orgFlow,Model model){
		return "";
	}

	@RequestMapping("/orgRegistList")//报到学员统计-基地
	public String orgRegistList(Integer currentPage,String recruitYear, Model model, HttpServletRequest request){
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> orgRegistList = resStatisticBiz.registStatistic(recruitYear);

		List<Map<String,String>> orgRegistList2 = resStatisticBiz.registStatistic(recruitYear);//统计总计
		int onlineAll = 0;
		int underlineAll = 0;
		int graduateAll = 0;
		if(orgRegistList2!=null&&orgRegistList2.size()>0){
			for(Map<String,String> map:orgRegistList2){
				String online = map.get("ON_LINE");
				String underline = map.get("UNDER_LINE");
				String graduate = map.get("GRADUATE");
				onlineAll+=Integer.parseInt(online);
				underlineAll+=Integer.parseInt(underline);
				graduateAll+=Integer.parseInt(graduate);
			}
		}
		model.addAttribute("onlineAll",onlineAll);
		model.addAttribute("underlineAll",underlineAll);
		model.addAttribute("graduateAll",graduateAll);
		model.addAttribute("orgRegistList",orgRegistList);
		return "res/platform/statistics/orgRegistList";
	}

	//打印
	@RequestMapping("/exportExl")
	public void exportStudents(Integer currentPage,String recruitYear,HttpServletRequest request,HttpServletResponse response) throws Exception {
		List<Map<String,String>> orgRegistList = resStatisticBiz.registStatistic(recruitYear);
		int onlineAll = 0;
		int underlineAll = 0;
		int graduateAll = 0;
		if(orgRegistList!=null&&orgRegistList.size()>0){
			for(Map<String,String> map:orgRegistList){
				String online = map.get("ON_LINE");
				String underline = map.get("UNDER_LINE");
				String graduate = map.get("GRADUATE");
				onlineAll+=Integer.parseInt(online);
				underlineAll+=Integer.parseInt(underline);
				graduateAll+=Integer.parseInt(graduate);
			}
		}
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("学员报到统计表");
		//列宽自适应
		sheet.setDefaultColumnWidth((short)50);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));//合并单元格
		HSSFRow rowDep = sheet.createRow(0);//第一行
		rowDep.setHeightInPoints(20);
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("学员报到统计表-按基地");
		HSSFRow rowOne= sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"基地名称",
				"基地代码",
				"线上录取人数",
				"线下录取人数",
				"四证合一",
				"小计",
		};
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowOne.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
		String[] resultList = null;
		int rowNum = 2;
		if (orgRegistList != null && orgRegistList.size()>0) {
			for (int i = 0; i < orgRegistList.size(); i++, rowNum++) {
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				resultList = new String[]{
						orgRegistList.get(i).get("ORG_NAME"),
						orgRegistList.get(i).get("ORG_CODE"),
						orgRegistList.get(i).get("ON_LINE"),
						orgRegistList.get(i).get("UNDER_LINE"),
						orgRegistList.get(i).get("GRADUATE"),
						orgRegistList.get(i).get("ORG_ALL")
				};
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(resultList[j]);
				}
			}
		}
		HSSFRow rowLast = sheet.createRow(rowNum+1);//最后一行
		HSSFCell cell0 = rowLast.createCell(0);
		cell0.setCellStyle(styleCenter);
		cell0.setCellValue("总计");
		HSSFCell cell2 = rowLast.createCell(2);
		cell2.setCellStyle(styleCenter);
		cell2.setCellValue(onlineAll);
		HSSFCell cell3 = rowLast.createCell(3);
		cell3.setCellStyle(styleCenter);
		cell3.setCellValue(underlineAll);
		HSSFCell cell4 = rowLast.createCell(4);
		cell4.setCellStyle(styleCenter);
		cell4.setCellValue(graduateAll);
		HSSFCell cell5 = rowLast.createCell(5);
		cell5.setCellStyle(styleCenter);
		cell5.setCellValue(onlineAll+underlineAll);
		String fileName ="学员报到统计表-按基地.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	//报到学员统计-专业
	@RequestMapping("/speRegistList")
	public String speRegistList(Integer currentPage,String recruitYear, Model model, HttpServletRequest request){
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,String>> speRegistList = resStatisticBiz.registStatisticSpe(recruitYear);

		List<Map<String,String>> speRegistList2 = resStatisticBiz.registStatisticSpe(recruitYear);//统计总计
		int onlineAll = 0;
		int underlineAll = 0;
		int graduateAll = 0;
		if(speRegistList2!=null&&speRegistList2.size()>0){
			for(Map<String,String> map:speRegistList2){
				String online = map.get("ON_LINE");
				String underline = map.get("UNDER_LINE");
				String graduate = map.get("GRADUATE");
				onlineAll+=Integer.parseInt(online);
				underlineAll+=Integer.parseInt(underline);
				graduateAll+=Integer.parseInt(graduate);
			}
		}
		model.addAttribute("onlineAll",onlineAll);
		model.addAttribute("underlineAll",underlineAll);
		model.addAttribute("graduateAll",graduateAll);
		model.addAttribute("speRegistList",speRegistList);
		return "res/platform/statistics/speRegistList";
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





	@RequestMapping("/zpptbb")
	public String zpptbb(String endMonth,Model model) throws ParseException {
		String startMonth = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date nowDate = new Date();
		Calendar c = Calendar.getInstance();
		//1个月前的月份
		c.setTime(sdf.parse(sdf.format(nowDate)));
		c.add(Calendar.MONTH, -1);
		String lastMonth = sdf.format(c.getTime());
		model.addAttribute("lastMonth",lastMonth);
		if(StringUtil.isBlank(endMonth)){
			endMonth = lastMonth;
		}
		//12个月前的月份
		c.setTime(sdf.parse(endMonth));
		c.add(Calendar.MONTH, -11);
		startMonth = sdf.format(c.getTime());
		//2个月前的月份
		Calendar c2 = Calendar.getInstance();
		c2.setTime(sdf.parse(endMonth));
		c2.add(Calendar.MONTH, -1);
		String startMonth2 = sdf.format(c2.getTime());
		model.addAttribute("startMonth2",startMonth2);
		model.addAttribute("endMonth2",endMonth);

		List<String> months = new ArrayList<>();

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(startMonth));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(endMonth));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			months.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		model.addAttribute("months",months);
		//图1
		List<SysMonthlyStatistics> statisticsList = sysMonthlyStatisticsBiz.searchStatistics(startMonth,endMonth,null);

		Map<String,Object> resultMapAll = new HashMap<>();
		Map<String,Object> resultMapGraduate = new HashMap<>();
		Map<String,Object> resultMapNotGraduate = new HashMap<>();
		int total1 = 0;
		for(String month:months){//初始化所有MAP
			resultMapAll.put(month,0);
			resultMapGraduate.put(month,0);
			resultMapNotGraduate.put(month,0);
		}

		if(statisticsList!=null&&statisticsList.size()>0){
			for(SysMonthlyStatistics monthlyStatistics:statisticsList){
				String dateMonth = monthlyStatistics.getDateMonth();
				if("All".equals(monthlyStatistics.getDoctorTypeId())){
					int allNum = monthlyStatistics.getAllNum();
					resultMapAll.put(dateMonth, (int)resultMapAll.get(dateMonth)+allNum);
					total1+=allNum;
				}
				if("Graduate".equals(monthlyStatistics.getDoctorTypeId())){
					int allNum = monthlyStatistics.getAllNum();
						resultMapGraduate.put(dateMonth, (int)resultMapGraduate.get(dateMonth)+allNum);
				}
				if("NotGraduate".equals(monthlyStatistics.getDoctorTypeId())){
					int allNum = monthlyStatistics.getAllNum();
						resultMapNotGraduate.put(dateMonth, (int)resultMapNotGraduate.get(dateMonth)+allNum);
				}
			}
		}
		model.addAttribute("resultMapAll",resultMapAll);
		model.addAttribute("resultMapGraduate",resultMapGraduate);
		model.addAttribute("resultMapNotGraduate",resultMapNotGraduate);
		model.addAttribute("total1",total1);

		//图2&图3&图4&图5
		List<SysMonthlyStatistics> statisticsList2 = statisticsList;
		Map<String,Object> resultMapOrg0 = new HashMap<>();
		Map<String,Object> resultMapOrgGraduate0 = new HashMap<>();
		Map<String,Object> resultMapOrgNotGraduate0 = new HashMap<>();
		Map<String,Object> resultMapOrgAvg0 = new HashMap<>();
		Map<String,Object> resultMapOrgAvgGraduate0 = new HashMap<>();
		Map<String,Object> resultMapOrgAvgNotGraduate0 = new HashMap<>();
		Map<String,Object> resultMapOrgPrev0 = new HashMap<>();
		Map<String,Object> resultMapOrgPrevPrev0 = new HashMap<>();
		Map<String,Object> resultMapOrgPrevAvg0 = new HashMap<>();
		int prevAllNum = 0;//前一个月填写总量
		if(statisticsList2!=null&&statisticsList2.size()>0) {
			for (SysMonthlyStatistics monthlyStatistics : statisticsList2) {
				if("All".equals(monthlyStatistics.getDoctorTypeId())){//总量
					String orgFlow = monthlyStatistics.getOrgFlow();
					int allNum = monthlyStatistics.getAllNum();
					int avgNum = monthlyStatistics.getAvgNum();
					if(resultMapOrg0.get(orgFlow)==null){
						resultMapOrg0.put(orgFlow,allNum);
					}else {
						resultMapOrg0.put(orgFlow, (int)resultMapOrg0.get(orgFlow)+allNum);
					}

					if(resultMapOrgAvg0.get(orgFlow)==null){
						resultMapOrgAvg0.put(orgFlow,avgNum);
					}else {
						resultMapOrgAvg0.put(orgFlow, (int)resultMapOrgAvg0.get(orgFlow)+avgNum);
					}

					if(startMonth2.equals(monthlyStatistics.getDateMonth())){
						if(resultMapOrgPrevPrev0.get(orgFlow)==null){
							resultMapOrgPrevPrev0.put(orgFlow,allNum);
						}else {
							resultMapOrgPrevPrev0.put(orgFlow, (int)resultMapOrgPrevPrev0.get(orgFlow)+allNum);
						}
					}

					if(endMonth.equals(monthlyStatistics.getDateMonth())){//前一个月
						if(resultMapOrgPrev0.get(orgFlow)==null){
							resultMapOrgPrev0.put(orgFlow,allNum);
						}else {
							resultMapOrgPrev0.put(orgFlow, (int)resultMapOrgPrev0.get(orgFlow)+allNum);
						}

						if(resultMapOrgPrevAvg0.get(orgFlow)==null){
							resultMapOrgPrevAvg0.put(orgFlow,avgNum);
						}else {
							resultMapOrgPrevAvg0.put(orgFlow, (int)resultMapOrgPrevAvg0.get(orgFlow)+avgNum);
						}
						prevAllNum+=allNum;
					}
				}

				if("Graduate".equals(monthlyStatistics.getDoctorTypeId())){//专硕
					String orgFlow = monthlyStatistics.getOrgFlow();
					int allNum = monthlyStatistics.getAllNum();
					int avgNum = monthlyStatistics.getAvgNum();
					if(resultMapOrgGraduate0.get(orgFlow)==null){
						resultMapOrgGraduate0.put(orgFlow,allNum);
					}else {
						resultMapOrgGraduate0.put(orgFlow, (int)resultMapOrgGraduate0.get(orgFlow)+allNum);
					}

					if(resultMapOrgAvgGraduate0.get(orgFlow)==null){
						resultMapOrgAvgGraduate0.put(orgFlow,avgNum);
					}else {
						resultMapOrgAvgGraduate0.put(orgFlow, (int)resultMapOrgAvgGraduate0.get(orgFlow)+avgNum);
					}
				}

				if("NotGraduate".equals(monthlyStatistics.getDoctorTypeId())){//非专硕
					String orgFlow = monthlyStatistics.getOrgFlow();
					int allNum = monthlyStatistics.getAllNum();
					int avgNum = monthlyStatistics.getAvgNum();
					if(resultMapOrgNotGraduate0.get(orgFlow)==null){
						resultMapOrgNotGraduate0.put(orgFlow,allNum);
					}else {
						resultMapOrgNotGraduate0.put(orgFlow, (int)resultMapOrgNotGraduate0.get(orgFlow)+allNum);
					}

					if(resultMapOrgAvgNotGraduate0.get(orgFlow)==null){
						resultMapOrgAvgNotGraduate0.put(orgFlow,avgNum);
					}else {
						resultMapOrgAvgNotGraduate0.put(orgFlow, (int)resultMapOrgAvgNotGraduate0.get(orgFlow)+avgNum);
					}
				}
			}
		}

		Map<String,Object> nationalOrgMap0 = new HashMap<>();//所有协同基地关系
		List<ResJointOrg> resJointOrgList= jointOrgBiz.searchJointOrgAll();
		if(resJointOrgList!=null&&resJointOrgList.size()>0){
			for(ResJointOrg resJointOrg:resJointOrgList){
				String orgFlow = resJointOrg.getOrgFlow();
				String jointOrgFlow = resJointOrg.getJointOrgFlow();
				if(nationalOrgMap0.get(orgFlow)==null){
					List<String> jointOrgFlowList = new ArrayList<>();
					jointOrgFlowList.add(jointOrgFlow);
					nationalOrgMap0.put(orgFlow,jointOrgFlowList);
				}else{
					List<String> jointOrgFlowList = (List<String>)nationalOrgMap0.get(orgFlow);
					jointOrgFlowList.add(jointOrgFlow);
					nationalOrgMap0.put(orgFlow,jointOrgFlowList);
				}
			}
		}

		Map<String,Object> nationalOrgMap = new HashMap<>();//国家基地MAP
		SysOrg sysOrg=new SysOrg();
        sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		//所有国家基地
		List<SysOrg> sysOrgs=orgBiz.searchAllSysOrg(sysOrg);
		if(sysOrgs!=null&&sysOrgs.size()>0){
			for(SysOrg org:sysOrgs){
				nationalOrgMap.put(org.getOrgFlow(),nationalOrgMap0.get(org.getOrgFlow()));
			}
		}

		//国家基地名称MAP
		Map<String,String> orgNameMap = new HashMap<>();
		for(SysOrg org:sysOrgs){
			String orgFlow = org.getOrgFlow();
			String orgName = org.getOrgName();
			orgNameMap.put(orgFlow,orgName);
		}
		model.addAttribute("orgNameMap",orgNameMap);

		Map<String,Integer> resultMapOrg = new HashMap<>();//国家基地总量数据统计
		Map<String,Integer> resultMapOrgGraduate = new HashMap<>();//国家基地专硕总量数据统计
		Map<String,Integer> resultMapOrgNotGraduate = new HashMap<>();//国家基地非专硕总量数据统计
		Map<String,Integer> resultMapOrgAvg = new HashMap<>();//国家基地平均数据统计
		Map<String,Integer> resultMapOrgAvgGraduate = new HashMap<>();//国家基地专硕平均数据统计
		Map<String,Integer> resultMapOrgAvgNotGraduate = new HashMap<>();//国家基地非专硕平均数据统计
		Map<String,Integer> resultMapOrgPrev = new HashMap<>();//国家基地前月总量数据统计
		Map<String,Integer> resultMapOrgPrevPrev = new HashMap<>();//国家基地前前月总量数据统计
		Map<String,Integer> resultMapOrgPrevAvg = new HashMap<>();//国家基地前前月各基地平均数据统计
		int orgPrevAvg = prevAllNum/sysOrgs.size();//国家基地前前月所有基地总平均数据
		model.addAttribute("orgPrevAvg",orgPrevAvg);

		for(String key:nationalOrgMap.keySet()){
			String orgFlow = key;
			List<String> jointOrgFlowList = (List<String>)nationalOrgMap.get(key);
			if(resultMapOrg.get(key)==null){//总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrg0.get(jointOrgFlow)==null?0:(int)resultMapOrg0.get(jointOrgFlow);
					}
				}
				resultMapOrg.put(key,(resultMapOrg0.get(orgFlow)==null?0:(int)resultMapOrg0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrg0.get(jointOrgFlow)==null?0:(int)resultMapOrg0.get(jointOrgFlow);
					}
				}
				resultMapOrg.put(key,resultMapOrg.get(key)+jointAllNum);
			}

			if(resultMapOrgGraduate.get(key)==null){//专硕总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgGraduate.put(key,(resultMapOrgGraduate0.get(orgFlow)==null?0:(int)resultMapOrgGraduate0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgGraduate.put(key,resultMapOrgGraduate.get(key)+jointAllNum);
			}

			if(resultMapOrgNotGraduate.get(key)==null){//非专硕总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgNotGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgNotGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgNotGraduate.put(key,(resultMapOrgNotGraduate0.get(orgFlow)==null?0:(int)resultMapOrgNotGraduate0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgNotGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgNotGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgNotGraduate.put(key,resultMapOrgNotGraduate.get(key)+jointAllNum);
			}

			if(resultMapOrgAvg.get(key)==null){//平均量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvg0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvg0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvg.put(key,(resultMapOrgAvg0.get(orgFlow)==null?0:(int)resultMapOrgAvg0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvg0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvg0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvg.put(key,resultMapOrgAvg.get(key)+jointAllNum);
			}

			if(resultMapOrgAvgGraduate.get(key)==null){//专硕平均量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvgGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvgGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvgGraduate.put(key,(resultMapOrgAvgGraduate0.get(orgFlow)==null?0:(int)resultMapOrgAvgGraduate0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvgGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvgGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvgGraduate.put(key,resultMapOrgAvgGraduate.get(key)+jointAllNum);
			}

			if(resultMapOrgAvgNotGraduate.get(key)==null){//非专硕平均量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvgNotGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvgNotGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvgNotGraduate.put(key,(resultMapOrgAvgNotGraduate0.get(orgFlow)==null?0:(int)resultMapOrgAvgNotGraduate0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvgNotGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvgNotGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvgNotGraduate.put(key,resultMapOrgAvgNotGraduate.get(key)+jointAllNum);
			}

			if(resultMapOrgPrev.get(key)==null){//前月总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrev0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrev0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrev.put(key,(resultMapOrgPrev0.get(orgFlow)==null?0:(int)resultMapOrgPrev0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrev0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrev0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrev.put(key,resultMapOrgPrev.get(key)+jointAllNum);
			}

			if(resultMapOrgPrevPrev.get(key)==null){//前前月总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevPrev0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevPrev0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevPrev.put(key,(resultMapOrgPrevPrev0.get(orgFlow)==null?0:(int)resultMapOrgPrevPrev0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevPrev0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevPrev0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevPrev.put(key,resultMapOrgPrevPrev.get(key)+jointAllNum);
			}

			if(resultMapOrgPrevAvg.get(key)==null){//前月平均量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevAvg0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevAvg0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevAvg.put(key,(resultMapOrgPrevAvg0.get(orgFlow)==null?0:(int)resultMapOrgPrevAvg0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevAvg0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevAvg0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevAvg.put(key,resultMapOrgPrevAvg.get(key)+jointAllNum);
			}


		}
		Map<String, Integer> resultMapTree = new TreeMap<String, Integer>(resultMapOrg);
		Map<String, Integer> resultMapGraduateTree = new TreeMap<String, Integer>(resultMapOrgGraduate);
		Map<String, Integer> resultMapNotGraduateTree = new TreeMap<String, Integer>(resultMapOrgNotGraduate);
		Map<String, Integer> resultMapAvgTree = new TreeMap<String, Integer>(resultMapOrgAvg);
		Map<String, Integer> resultMapOrgPrevTree = new TreeMap<String, Integer>(resultMapOrgPrev);
		Map<String, Integer> resultMapOrgPrevPrevTree = new TreeMap<String, Integer>(resultMapOrgPrevPrev);
		Map<String, Integer> resultMapOrgPrevAvgTree = new TreeMap<String, Integer>(resultMapOrgPrevAvg);
		model.addAttribute("resultMapOrgPrevAvgTree",resultMapOrgPrevAvgTree);
		// 降序比较器
		Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				return o2.getValue()-o1.getValue();
			}
		};

		// map转换成list进行排序
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(resultMapTree.entrySet());
		List<Map.Entry<String, Integer>> list2 = new ArrayList<Map.Entry<String,Integer>>(resultMapAvgTree.entrySet());
		List<Map.Entry<String, Integer>> list3 = new ArrayList<Map.Entry<String,Integer>>(resultMapOrgPrevTree.entrySet());
		List<Map.Entry<String, Integer>> list4 = new ArrayList<Map.Entry<String,Integer>>(resultMapOrgPrevPrevTree.entrySet());
		List<Map.Entry<String, Integer>> list5 = new ArrayList<Map.Entry<String,Integer>>(resultMapOrgPrevAvgTree.entrySet());

		// 排序
		Collections.sort(list,valueComparator);
		Collections.sort(list2,valueComparator);
		List<Map.Entry<String, Integer>> resultMapOrgList = list.subList(0, 10);
		Map<String,Object> graduateMap = new HashMap<>();
		for(Map.Entry<String, Integer> map:resultMapOrgList){
			String orgFlow = map.getKey();
			Integer num = resultMapGraduateTree.get(orgFlow);
			graduateMap.put(orgFlow,num);
		}
		Map<String,Object> notGraduateMap = new HashMap<>();
		for(Map.Entry<String, Integer> map:resultMapOrgList){
			String orgFlow = map.getKey();
			Integer num = resultMapNotGraduateTree.get(orgFlow);
			notGraduateMap.put(orgFlow,num);
		}
		List<Map.Entry<String, Integer>> resultMapOrgList2 = list2.subList(0, 10);
		Map<String,Object> avgGraduateMap = new HashMap<>();
		for(Map.Entry<String, Integer> map:resultMapOrgList2){
			String orgFlow = map.getKey();
			Integer num = resultMapOrgAvgGraduate.get(orgFlow);
			avgGraduateMap.put(orgFlow,num);
		}
		Map<String,Object> avgNotGraduateMap = new HashMap<>();
		for(Map.Entry<String, Integer> map:resultMapOrgList2){
			String orgFlow = map.getKey();
			Integer num = resultMapOrgAvgNotGraduate.get(orgFlow);
			avgNotGraduateMap.put(orgFlow,num);
		}
		model.addAttribute("resultMapOrgList",resultMapOrgList);
		model.addAttribute("graduateMap",graduateMap);
		model.addAttribute("notGraduateMap",notGraduateMap);
		model.addAttribute("resultMapOrgList2",resultMapOrgList2);
		model.addAttribute("avgGraduateMap",avgGraduateMap);
		model.addAttribute("avgNotGraduateMap",avgNotGraduateMap);
		model.addAttribute("list2",list2);//不排序和截取直接传前台
		model.addAttribute("list3",list3);//不排序和截取直接传前台
		model.addAttribute("list4",list4);//不排序和截取直接传前台
		model.addAttribute("list5",list5);//不排序和截取直接传前台

		return "res/platform/zpptbb";
	}

}