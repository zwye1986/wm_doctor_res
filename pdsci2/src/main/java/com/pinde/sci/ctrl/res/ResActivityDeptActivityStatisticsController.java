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
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
@RequestMapping("/res/deptActivityStatistics")
public class ResActivityDeptActivityStatisticsController extends GeneralController {
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
	public String main(Model model,String  role,String  orgFlow,String  orgName, HttpServletRequest request){
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg currentOrg=orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<SysOrg> orgs = new ArrayList<>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(role)){
			//查询所有医院
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgs = orgBiz.searchOrg(org);
			if(orgs!=null&&orgs.size()>0){
				if(StringUtil.isBlank(orgFlow)){
					orgFlow=orgs.get(0).getOrgFlow();
					orgName=orgs.get(0).getOrgName();
				}
			}
			model.addAttribute("orgs",orgs);
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			String workOrgId = currentOrg.getSendSchoolId();
			orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			if(orgs!=null&&orgs.size()>0){
				if(StringUtil.isBlank(orgFlow)){
					orgFlow=orgs.get(0).getOrgFlow();
					orgName=orgs.get(0).getOrgName();
				}
			}
			model.addAttribute("orgs",orgs);
		}else if(GlobalConstant.USER_LIST_LOCAL.equals(role)){
			orgFlow=currentUser.getOrgFlow();
			orgName=currentUser.getOrgName();
		}
		List<SysDept> depts=deptBiz.searchDeptByOrg(orgFlow);
		model.addAttribute("depts",depts);
		model.addAttribute("roleFlag",role);
		model.addAttribute("orgName",orgName);
		model.addAttribute("orgFlow",orgFlow);
		return "res/activity/deptActivityStatistics/main";
	}
	@RequestMapping(value="/list")
	public String list(Model model,Integer currentPage,String orgFlow,String deptFlow,
					   String startTime,String endTime, HttpServletRequest request){
		SysUser curUser=GlobalContext.getCurrentUser();
		SysDept dept=new SysDept();
		dept.setDeptFlow(deptFlow);
		if(StringUtil.isBlank(orgFlow)){
			orgFlow=curUser.getOrgFlow();
		}
		dept.setOrgFlow(orgFlow);
		dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysDept> deptList = deptBiz.searchDept(dept);
		Map<String,Object> map=new HashMap<>();
		if(deptList!=null&&deptList.size()>0)
		{
			for(SysDept sysDept:deptList)
			{
				map.put(sysDept.getDeptFlow(),activityBiz.getDeptActivityStatisticsMap(sysDept.getDeptFlow(),startTime,endTime));
			}
		}
		model.addAttribute("map",map);
		model.addAttribute("list",deptList);
		return "res/activity/deptActivityStatistics/list";
	}
	@RequestMapping(value="/exportList")
	public void exportList(Model model,Integer currentPage,String orgFlow,String deptFlow,
						   String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws IOException {
		SysUser curUser=GlobalContext.getCurrentUser();
		SysDept dept=new SysDept();
		dept.setDeptFlow(deptFlow);
		if(StringUtil.isBlank(orgFlow)){
			orgFlow=curUser.getOrgFlow();
		}
		dept.setOrgFlow(orgFlow);
		dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> deptList = deptBiz.searchDept(dept);
		List<SysDict> dictList=DictTypeEnum.sysListDictMap.get(DictTypeEnum.ActivityType.getId());
		if(dictList==null)
		{
			dictList=new ArrayList<>();
		}
		String []titles = new String[dictList.size()+1];
		titles[0]="科室名称";
		for(int i=1;i<dictList.size()+1;i++)
		{
			titles[i]=dictList.get(i-1).getDictName();
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
		if(deptList!=null){
			if(deptList!=null&&deptList.size()>0)
			{
				int k=1;
				for(int i=0; i<deptList.size() ; i++)
				{
					row = sheet.createRow((int)k++);
					SysDept sysDept=deptList.get(i);
					Map<String,Object>map=activityBiz.getDeptActivityStatisticsMap(sysDept.getDeptFlow(),startTime,endTime);
					int j=0;
					row.createCell(j).setCellValue(sysDept.getDeptName());
					ExcleUtile.setColumnWidth(sysDept.getDeptName().getBytes().length, j++, columnWidth);
					if(map!=null) {
						for(SysDict dict:dictList)
						{
							String result= String.valueOf(map.get(sysDept.getDeptFlow()+ dict.getDictId()));
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
		wb.write(response.getOutputStream());
		String fileName = new String("科室活动统计.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	/**
	 * @return
	 */
	@RequestMapping(value="/searchDeptList", method={RequestMethod.GET})
	@ResponseBody
	public List<SysDept> searchDeptList(String orgFlow){
		List<SysDept> depts=new ArrayList<>();
		if(StringUtil.isNotBlank(orgFlow)){
			depts=deptBiz.searchDeptByOrg(orgFlow);
		}
		return depts;
	}
}
