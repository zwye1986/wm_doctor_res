package com.pinde.sci.ctrl.res;


import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/res/teacherActivityStatistics")
public class ResActivityTeacherActivityStatisticsController extends GeneralController {
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
	private IOrgBiz orgBiz;
	@Autowired
	private SysOrgExtMapper orgExtMapper;

	@RequestMapping(value="/main")
	public String main(Model model,String  role, String orgFlow, HttpServletRequest request){
		if(GlobalConstant.USER_LIST_GLOBAL.equals(role)){
			//查询所有医院
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgs = orgBiz.searchOrg(org);
			model.addAttribute("orgs",orgs);
		}else if(GlobalConstant.USER_LIST_LOCAL.equals(role)){
			orgFlow=GlobalContext.getCurrentUser().getOrgFlow();
			List<SysDept> depts=deptBiz.searchDeptByOrg(orgFlow);
			model.addAttribute("depts",depts);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			model.addAttribute("orgs",orgs);
		}
		model.addAttribute("roleFlag",role);
		return "res/activity/teacherActivityStatistics/main";
	}
	@RequestMapping(value="/list")
	public String list(Model model,Integer currentPage,String roleFlag,
					   String userName,String orgFlow,String deptFlow,
					   String startTime,String endTime, HttpServletRequest request){
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		List<String> roles=new ArrayList<>();
		param.put("userName",userName);
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){

		}else if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			orgFlow=curUser.getOrgFlow();
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//查询高校下所有医院
			SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			List<String> orgFlowList = new ArrayList<>();
			if(orgs!=null&&orgs.size()>0){
				for(SysOrg org:orgs){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
			param.put("orgFlowList",orgFlowList);
		}
		param.put("orgFlow",orgFlow);
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
		String professionalBaseAdminRoleFlow = InitConfig.getSysCfg("res_professionalBase_admin_role_flow");
		if(StringUtil.isNotBlank(professionalBaseAdminRoleFlow))
		{
			roles.add(professionalBaseAdminRoleFlow);
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
		return "res/activity/teacherActivityStatistics/list";
	}
	@RequestMapping(value="/exportList")
	public void exportList(Model model,String roleFlag,
						   String userName,String orgFlow,String deptFlow,
						   String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		List<String> roles=new ArrayList<>();
		param.put("userName",userName);
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){

		}else if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			orgFlow=curUser.getOrgFlow();
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			//查询高校下所有医院
			SysOrg currentOrg = orgBiz.readSysOrg(curUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			List<SysOrg> orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			List<String> orgFlowList = new ArrayList<>();
			if(orgs!=null&&orgs.size()>0){
				for(SysOrg org:orgs){
					String flow = org.getOrgFlow();
					orgFlowList.add(flow);
				}
			}
			param.put("orgFlowList",orgFlowList);
		}
		param.put("orgFlow",orgFlow);
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
		String professionalBaseAdminRoleFlow = InitConfig.getSysCfg("res_professionalBase_admin_role_flow");
		if(StringUtil.isNotBlank(professionalBaseAdminRoleFlow))
		{
			roles.add(professionalBaseAdminRoleFlow);
		}
		param.put("roleList",roles);
		List<Map<String,Object>> list=activityBiz.getTeacherActivityStatistics(param);
		List<SysDict> dictList= DictTypeEnum.sysListDictMap.get(DictTypeEnum.ActivityType.getId());
		if(dictList==null)
		{
			dictList=new ArrayList<>();
		}
		String []titles = new String[dictList.size()+3];
		titles[0]="主讲人";
		titles[1]="基地名称";
		titles[2]="科室名称";
		for(int i=3;i<dictList.size()+3;i++)
		{
			titles[i]=dictList.get(i-3).getDictName();
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
		for(int i = 0 ; i<titles.length ; i++){
			cell = row.createCell(i);
			cell.setCellValue(titles[i]);
			cell.setCellStyle(style);
			ExcleUtile.setColumnWidth(titles[i].toString().getBytes().length, i, columnWidth);
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
					Map<String,Object>map=activityBiz.getTeacherActivityStatisticsMap(deptFlow2,userFlow,startTime,endTime);
					int j=0;

					String userName2= u.get("userName")==null? "": (String)u.get("userName");
					String orgName= u.get("orgName")==null? "": (String)u.get("orgName");
					String deptName= u.get("deptName")==null? "": (String)u.get("deptName");
					row.createCell(j).setCellValue(userName2);
					ExcleUtile.setColumnWidth(userName2.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(orgName);
					ExcleUtile.setColumnWidth(deptName.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(deptName);
					ExcleUtile.setColumnWidth(deptName.getBytes().length, j++, columnWidth);

					if(map!=null) {
						for(SysDict dict:dictList)
						{
							String result= String.valueOf(map.get(deptFlow2+userFlow+ dict.getDictId()));
							if(StringUtil.isBlank(result)||"null".equals(result)) result="0";
							row.createCell(j).setCellValue(result);
							ExcleUtile.setColumnWidth(result.getBytes().length, j++, columnWidth);
						}

					}else{
						for(SysDict dict:dictList)
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
		String fileName = new String("师资活动统计.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
}
