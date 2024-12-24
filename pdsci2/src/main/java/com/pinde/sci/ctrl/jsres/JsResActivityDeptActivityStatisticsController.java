package com.pinde.sci.ctrl.jsres;


import com.pinde.core.common.enums.ActivityTypeEnum;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/jsres/deptActivityStatistics")
public class JsResActivityDeptActivityStatisticsController extends GeneralController {
	@Autowired
	private IJsResActivityTargetBiz activityTargeBiz;
	@Autowired
	private IJsResActivityBiz activityBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@RequestMapping(value="/main")
	public String main(Model model,String  roleFlag, HttpServletRequest request){
		List<SysDept> depts=deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("depts",depts);
		return "jsres/activity/deptActivityStatistics/main";
	}
	@RequestMapping(value="/list")
	public String list(Model model,Integer currentPage,String deptFlow,String notNull,
					   String startTime,String endTime, HttpServletRequest request){
		SysUser curUser=GlobalContext.getCurrentUser();
	/*	SysDept dept=new SysDept();
		dept.setDeptFlow(deptFlow);
		dept.setOrgFlow(curUser.getOrgFlow());
		dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);*/
		PageHelper.startPage(currentPage, getPageSize(request));
		/*List<SysDept> deptList = deptBiz.searchDept(dept);*/
		List<Map<String, Object>> deptList = activityBiz.getDeptCountActivityStatisticsList(curUser.getOrgFlow(), deptFlow, startTime, endTime,notNull);
		Map<String,Object> map=new HashMap<>();
		if(deptList!=null&&deptList.size()>0)
		{
			for (Map<String, Object> deptMap : deptList) {
				map.put(deptMap.get("deptFlow").toString(),activityBiz.getDeptActivityStatisticsMap(deptMap.get("deptFlow").toString(),startTime,endTime));
			}
		}
		Map<String, Object> param=new HashMap<>();
		param.put("orgFlow",curUser.getOrgFlow());
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		List<Map<String,String>> numList= resDoctorProcessBiz.schProcessStudentDistinctQuery2(param);
		if (null != numList && !numList.isEmpty()){
			HashMap<String, String> countMap = new HashMap<>();
			for (Map<String, String> m : numList) {
				countMap.put(m.get("deptFlow"),m.get("num"));
			}
			model.addAttribute("countMap",countMap);
		}
		model.addAttribute("map",map);
		model.addAttribute("list",deptList);
		return "jsres/activity/deptActivityStatistics/list";
	}
	@RequestMapping(value="/exportList")
	public void exportList(Model model,Integer currentPage,String deptFlow,String notNull,
						   String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws IOException {
		SysUser curUser=GlobalContext.getCurrentUser();
		/*SysDept dept=new SysDept();
		dept.setDeptFlow(deptFlow);
		dept.setOrgFlow(curUser.getOrgFlow());
		dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);*/
//		List<SysDept> deptList = deptBiz.searchDept(dept);
		List<Map<String, Object>> deptList = activityBiz.getDeptCountActivityStatisticsList(curUser.getOrgFlow(), deptFlow, startTime, endTime,notNull);
		Map<String, Object> param=new HashMap<>();
		param.put("orgFlow",curUser.getOrgFlow());
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		HashMap<String, String> countMap = new HashMap<>();
		List<Map<String,String>> numList= resDoctorProcessBiz.schProcessStudentDistinctQuery2(param);
		if (null != numList && !numList.isEmpty()){
			for (Map<String, String> m : numList) {
				countMap.put(m.get("deptFlow"),m.get("num"));
			}
		}
		List<String> titles=new ArrayList<>();
		titles.add("科室名称");
		titles.add("轮转人数");
		titles.add("横向合计活动总数");
		for(ActivityTypeEnum e:ActivityTypeEnum.values())
		{
			titles.add(e.getName());
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

		Map<Integer, Integer> columnWidth = new HashMap<>();

		HSSFCell cell = null;
		for(int i = 0 ; i<titles.size() ; i++){
			cell = row.createCell(i);
			cell.setCellValue(titles.get(i));
			cell.setCellStyle(style);
			ExcleUtile.setColumnWidth(titles.get(i).toString().getBytes().length, i, columnWidth);
		}
		if(deptList!=null){
			if(deptList!=null&&deptList.size()>0)
			{
				int k=1;
				for(int i=0; i<deptList.size() ; i++)
				{
					row = sheet.createRow((int)k++);
//					SysDept sysDept=deptList.get(i);
					Map<String, Object> sysDeptMap = deptList.get(i);
					Map<String,Object>map=activityBiz.getDeptActivityStatisticsMap(sysDeptMap.get("deptFlow").toString(),startTime,endTime);
					int j=0;
					row.createCell(j).setCellValue(sysDeptMap.get("deptName").toString());
					ExcleUtile.setColumnWidth(sysDeptMap.get("deptName").toString().getBytes().length, j++, columnWidth);
					String schNum="0";
					if (StringUtil.isNotBlank(countMap.get(sysDeptMap.get("deptFlow")))){
						schNum=countMap.get(sysDeptMap.get("deptFlow"));
					}
					row.createCell(j).setCellValue(schNum);
					ExcleUtile.setColumnWidth(schNum.getBytes().length, j++, columnWidth);

					String countNum="0";
					if(map!=null) {
						int count=0;
						for (Object value : map.values()) {
							count=count+Integer.parseInt(value.toString());
						}
						countNum=String.valueOf(count);
						row.createCell(j).setCellValue(countNum);
						ExcleUtile.setColumnWidth(countNum.getBytes().length, j++, columnWidth);
						for(ActivityTypeEnum e:ActivityTypeEnum.values())
						{
							String result= String.valueOf(map.get(sysDeptMap.get("deptFlow").toString()+ e.getId()));
							if(StringUtil.isBlank(result)||"null".equals(result)) result="0";
							row.createCell(j).setCellValue(result);
							ExcleUtile.setColumnWidth(result.getBytes().length, j++, columnWidth);
						}
					}else{
						row.createCell(j).setCellValue(countNum);
						ExcleUtile.setColumnWidth(countNum.getBytes().length, j++, columnWidth);
						for(ActivityTypeEnum e:ActivityTypeEnum.values())
						{
							ExcleUtile.setColumnWidth("0".getBytes().length, j++, columnWidth);
						}
					}

				}
			}
		}
		Set<Integer> keys = columnWidth.keySet();
		for (Integer key : keys) {
			int width = columnWidth.get(key);
			if(width * 2 * 256>255*256)
			{
				width=255*256;
			}else{
				width=width * 2 * 256;
			}
			sheet.setColumnWidth(key, width);
		}
		String fileName = new String("科室活动统计.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
}
