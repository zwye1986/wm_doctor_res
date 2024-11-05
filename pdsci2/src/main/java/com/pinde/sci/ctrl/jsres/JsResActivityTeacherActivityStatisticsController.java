package com.pinde.sci.ctrl.jsres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sch.ActivityTypeEnum;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysUser;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/jsres/teacherActivityStatistics")
public class JsResActivityTeacherActivityStatisticsController extends GeneralController {
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
	@RequestMapping(value="/main")
	public String main(Model model,String  roleFlag, HttpServletRequest request){
		if("head".equals(roleFlag))
		{
			List<Map<String, Object>> depts=deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
			model.addAttribute("depts",depts);
		}else {
			List<SysDept> depts = deptBiz.searchDeptByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("depts", depts);
		}
		return "jsres/activity/teacherActivityStatistics/main";
	}
	@RequestMapping(value="/list")
	public String list(Model model,Integer currentPage,
					   String userName,String deptFlow,String  roleFlag,String deptName,
					   String startTime,String endTime, HttpServletRequest request){
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		List<String> roles=new ArrayList<>();
		param.put("userName",userName);
		param.put("deptFlow",deptFlow);
		param.put("deptName",deptName);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("roleFlag",roleFlag);
		param.put("userFlow",curUser.getUserFlow());
		param.put("orgFlow",curUser.getOrgFlow());
		String teacherFlow=InitConfig.getSysCfg("res_teacher_role_flow");
		if(StringUtil.isNotBlank(teacherFlow))
		{
			roles.add(teacherFlow);
		}
		String headFlow=InitConfig.getSysCfg("res_head_role_flow");
		if(StringUtil.isNotBlank(headFlow))
		{
			roles.add(headFlow);
		}
		param.put("roleList",roles);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> list=activityBiz.getTeacherActivityStatistics(param);
		Map<String,Object> map=new HashMap<>();
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> u:list)
			{
				map.put((String)u.get("userFlow")+u.get("deptFlow"),activityBiz.getTeacherActivityStatisticsMap((String)u.get("deptFlow"),(String)u.get("userFlow"),startTime,endTime));
			}
		}
		model.addAttribute("map",map);
		model.addAttribute("list",list);
		return "jsres/activity/teacherActivityStatistics/list";
	}
	@RequestMapping(value="/exportList")
	public void exportList(Model model,Integer currentPage,
						   String userName,String deptFlow,String roleFlag,
						   String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		List<String> roles=new ArrayList<>();
		param.put("userName",userName);
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("roleFlag",roleFlag);
		param.put("userFlow",curUser.getUserFlow());
		param.put("orgFlow",curUser.getOrgFlow());
		String teacherFlow=InitConfig.getSysCfg("res_teacher_role_flow");
		if(StringUtil.isNotBlank(teacherFlow))
		{
			roles.add(teacherFlow);
		}
		String headFlow=InitConfig.getSysCfg("res_head_role_flow");
		if(StringUtil.isNotBlank(headFlow))
		{
			roles.add(headFlow);
		}
		param.put("roleList",roles);
//		List<Map<String,Object>> list=activityBiz.getTeacherActivityStatistics(param);
		List<Map<String,Object>> list=activityBiz.getTeacherActivityStatistics2(param);
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

		Map<Integer, Integer> columnWidth = new HashMap<>();

		List<String> titles=new ArrayList<>();
		titles.add("主讲人");
		titles.add("实际主讲人");
		titles.add("科室名称");
		titles.add("带教人数");
		titles.add("横向合计活动总数");
		for(ActivityTypeEnum e:ActivityTypeEnum.values())
		{
			titles.add(e.getName());
		}

		HSSFCell cell = null;
		for(int i = 0 ; i<titles.size() ; i++){
			cell = row.createCell(i);
			cell.setCellValue(titles.get(i));
			cell.setCellStyle(style);
			ExcleUtile.setColumnWidth(titles.get(i).toString().getBytes().length, i, columnWidth);
		}
		if(list!=null){
			if(list!=null&&list.size()>0)
			{
				int k=1;
				for(int i=0; i<list.size() ; i++)
				{
					row = sheet.createRow((int)k++);
					Map<String,Object> u=list.get(i);
					String deptFlow2= (String) u.get("deptFlow");
					String userFlow= (String) u.get("userFlow");
					String realitSpeaker="";
					Map<String,Object>map=activityBiz.getTeacherActivityStatisticsMap(deptFlow2,userFlow,startTime,endTime);
					if (null != u && StringUtil.isNotBlank(u.get("userFlow").toString())
							&& StringUtil.isNotBlank(u.get("deptFlow").toString())){
						 realitSpeaker = activityBiz.getRealitSpeaker2(u.get("userFlow").toString(), u.get("deptFlow").toString(), u.get("orgFlow").toString(),startTime,endTime);
					}
					int j=0;

					String userName2= u.get("userName")==null? "": (String)u.get("userName");
					String deptName= u.get("deptName")==null? "": (String)u.get("deptName");
					String countNum="0";
					String num=u.get("num")==null?"0":u.get("num").toString(); //带教人数
					row.createCell(j).setCellValue(userName2);
					ExcleUtile.setColumnWidth(userName2.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(realitSpeaker);
					ExcleUtile.setColumnWidth(realitSpeaker.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(deptName);
					ExcleUtile.setColumnWidth(deptName.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(num);
					ExcleUtile.setColumnWidth(num.getBytes().length, j++, columnWidth);
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
							String result= String.valueOf(map.get(deptFlow2+userFlow+ e.getId()));
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
			if(width * 2 * 150>150*256)
			{
				width=150*256;
			}else{
				width=width * 2 * 150;
			}
			sheet.setColumnWidth(key, width);
		}
		String fileName = new String("师资活动统计.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@RequestMapping(value="/exportReport")
	public void exportReport(String userName,String deptFlow,String startTime,String endTime,HttpServletResponse response) throws Exception {
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		param.put("userName",userName);
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("orgFlow",curUser.getOrgFlow());
		List<Map<String, String>> list = activityBiz.getTeacherActivityStatisticsReport(param);

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFSheet sheet = wb.createSheet("师资活动统计报表");//sheet名称
		sheet.setColumnWidth(0,2000);
		sheet.setColumnWidth(1,4000);
		sheet.setColumnWidth(2,8000);
		sheet.setColumnWidth(3,8000);
		sheet.setColumnWidth(4,4000);
		sheet.setColumnWidth(5,4000);
		sheet.setColumnWidth(6,4000);
		sheet.setColumnWidth(7,4000);
		sheet.setColumnWidth(8,4000);
		HSSFRow row0 = sheet.createRow(0);	//第一行
		String[] titles0 = new String[]{
				"开始时间：",
				startTime,
				"结束时间",
				endTime,
				"",
				"",
				"",
				"",
				""
		};
		HSSFCell cellTitle0 = null;
		for (int i = 0; i < titles0.length; i++) {
			cellTitle0 = row0.createCell(i);
			cellTitle0.setCellValue(titles0[i]);
			cellTitle0.setCellStyle(styleCenter);
			cellTitle0.setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		HSSFRow row1 = sheet.createRow(1);	//第二行
		String[] titles1 = new String[]{
				"序号：",
				"主讲人",
				"所在基地",
				"所在科室",
				"活动数量",
				"活动评价均分",
				"活动评价最高分",
				"活动评价最低分",
				"参与评价人数"
		};
		HSSFCell cellTitle1 = null;
		for (int i = 0; i < titles1.length; i++) {
			cellTitle1 = row1.createCell(i);
			cellTitle1.setCellValue(titles1[i]);
			cellTitle1.setCellStyle(styleCenter);
			cellTitle1.setCellType(HSSFCell.CELL_TYPE_STRING);
		}
		String orgName = GlobalContext.getCurrentUser().getOrgName();
		if (null!=list && !list.isEmpty()){
			for (int i = 0; i < list.size(); i++) {
				HSSFRow row = sheet.createRow(i+2);	//第一行
				String[] titles = new String[]{
						String.valueOf(i + 1),
						list.get(i).get("userName"),
						orgName,
						list.get(i).get("deptName"),
						String.valueOf(list.get(i).get("activityNum")),
						String.valueOf(list.get(i).get("avgScore")),
						String.valueOf(list.get(i).get("maxScore")),
						String.valueOf(list.get(i).get("minScore")),
						String.valueOf(list.get(i).get("evaNum"))
				};
				HSSFCell cellTitle = null;
				for (int j = 0; j < titles.length; j++) {
					cellTitle = row.createCell(j);
					cellTitle.setCellValue(titles[j]);
					cellTitle.setCellStyle(styleCenter);
					cellTitle.setCellType(HSSFCell.CELL_TYPE_STRING);
				}
			}
		}
		String fileName = URLEncoder.encode("师资活动统计报表.xls", "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}
}
