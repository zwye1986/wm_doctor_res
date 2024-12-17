package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.DeptActivityItemTypeEnum;
import com.pinde.core.common.enums.DeptActivityStatusEnum;
import com.pinde.core.common.enums.DeptActivityTypeEnum;
import com.pinde.core.common.enums.DeptActivityUserTypeEnum;
import com.pinde.core.model.QingpuLectureEvalCfg;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDeptActivityBiz;
import com.pinde.sci.biz.res.IResQingpuLectureCfgBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.form.res.ResDeptPlanForm;
import com.pinde.sci.model.mo.SysDeptMonthExamInfo;
import com.pinde.sci.model.mo.SysDeptMonthPlan;
import com.pinde.sci.model.mo.SysDeptMonthPlanItem;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/deptActivity")
public class ResDeptActivityController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResDeptActivityController.class);

	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IResDeptActivityBiz deptActivityBiz;
	@Autowired
	private IResQingpuLectureCfgBiz lectureCfgBiz;

	@RequestMapping("/info/{role}")
	public String info( @PathVariable String role,Model model){
		model.addAttribute("role",role);
		SysUser user = GlobalContext.getCurrentUser();
		String orgFlow = user.getOrgFlow();
		if("head".equals(role)){
			List<Map<String, Object>> deptList = deptBiz.queryDeptListByFlow(user.getUserFlow());
			model.addAttribute("deptList",deptList);
		}else{
			List<SysDept> deptList=deptBiz.searchDeptByOrg(orgFlow);
			model.addAttribute("deptList",deptList);
		}

		return "res/deptActivity/main";
	}

	@RequestMapping(value="/list/{role}")
	public String list(Model model, @PathVariable String role,Integer currentPage ,HttpServletRequest request,
					   String deptFlow,String planDate,String planTypeId){

		model.addAttribute("role",role);
		SysUser sysuser=GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		param.put("userFlow",sysuser.getUserFlow());
		param.put("orgFlow",sysuser.getOrgFlow());
		param.put("role",role);
		param.put("deptFlow",deptFlow);
		param.put("planDate",planDate);
		param.put("planTypeId",planTypeId);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list=deptActivityBiz.searchList(param);
		model.addAttribute("list",list);
		return "res/deptActivity/list";
	}

	@RequestMapping(value = "/delPlan")
	@ResponseBody
	public  String delPlan(String planFlow, Model model) {
		SysDeptMonthPlan plan=deptActivityBiz.readPlan(planFlow);
		if(plan==null)
		{
			return "科室活动信息不存在，请刷新页面！";
		}
        plan.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		int count=deptActivityBiz.save(plan);
        if (count == com.pinde.core.common.GlobalConstant.ZERO_LINE)
		{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	@RequestMapping(value = "/subPlan")
	@ResponseBody
	public  String subPlan(String planFlow, Model model) {
		SysDeptMonthPlan plan=deptActivityBiz.readPlan(planFlow);
		if(plan==null)
		{
			return "科室活动信息不存在，请刷新页面！";
		}
		if(!DeptActivityStatusEnum.Save.getId().equals(plan.getAuditStatusId()))
		{
			return "活动信息不是保存状态，无法提交，请刷新页面！！";
		}
		plan.setAuditStatusId(DeptActivityStatusEnum.Submit.getId());
		plan.setAuditStatusName(DeptActivityStatusEnum.Submit.getName());
		int count=deptActivityBiz.save(plan);
        if (count == com.pinde.core.common.GlobalConstant.ZERO_LINE)
		{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	@RequestMapping(value = "/reportPlan")
	@ResponseBody
	public  String reportPlan(String planFlow, Model model) {
		SysDeptMonthPlan plan=deptActivityBiz.readPlan(planFlow);
		if(plan==null)
		{
			return "科室活动信息不存在，请刷新页面！";
		}
		if(!DeptActivityStatusEnum.Pass.getId().equals(plan.getAuditStatusId()))
		{
			return "活动信息不是审核通过状态，无法发布，请刷新页面！！";
		}
		String typeCfg = InitConfig.getSysCfg("dept_activity_type");
		Map<String,QingpuLectureEvalCfg> cfgMap=new HashMap<>();
		if(DeptActivityTypeEnum.Dept.getId().equals(plan.getPlanTypeId()))
		{
			for(DeptActivityItemTypeEnum e:DeptActivityItemTypeEnum.values())
			{
                if (e.getIsCfg().equals(com.pinde.core.common.GlobalConstant.FLAG_Y))
				{
					QingpuLectureEvalCfg searchCfg = new QingpuLectureEvalCfg();
					searchCfg.setTypeId(e.getId());
					searchCfg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
					List<QingpuLectureEvalCfg> lectureEvalCfgList = lectureCfgBiz.searchLectureEvalCfgList(searchCfg);
					if(lectureEvalCfgList==null||lectureEvalCfgList.size()==0)
					{
						return "请维护【"+e.getName()+"】活动评价指标";
					}
					cfgMap.put(e.getId(),lectureEvalCfgList.get(0));
				}
			}
        } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(typeCfg)) {
			for(DeptActivityItemTypeEnum e:DeptActivityItemTypeEnum.values())
			{
                if (e.getIsCfg().equals(com.pinde.core.common.GlobalConstant.FLAG_N) && !DeptActivityItemTypeEnum.CKKHAP.getId().equals(e.getId())
						&&!DeptActivityItemTypeEnum.DSBGHAP.getId().equals(e.getId())
						&&!DeptActivityItemTypeEnum.JYSKHAP.getId().equals(e.getId()))
				{
					QingpuLectureEvalCfg searchCfg = new QingpuLectureEvalCfg();
					searchCfg.setTypeId(e.getId());
					searchCfg.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
					List<QingpuLectureEvalCfg> lectureEvalCfgList = lectureCfgBiz.searchLectureEvalCfgList(searchCfg);
					if(lectureEvalCfgList==null||lectureEvalCfgList.size()==0)
					{
						return "请维护【"+e.getName()+"】活动评价指标";
					}
					cfgMap.put(e.getId(),lectureEvalCfgList.get(0));
				}
			}
		}
        plan.setIsReport(com.pinde.core.common.GlobalConstant.FLAG_Y);
		int count=deptActivityBiz.reportPlan(plan,cfgMap);
        if (count == com.pinde.core.common.GlobalConstant.ZERO_LINE)
		{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	@RequestMapping(value = "/auditPlan")
	@ResponseBody
	public  String auditPlan(String planFlow,String auditStatusId,String auditReason, Model model) {
		SysDeptMonthPlan plan=deptActivityBiz.readPlan(planFlow);
		if(plan==null)
		{
			return "科室活动信息不存在，请刷新页面！";
		}
		if(!DeptActivityStatusEnum.Submit.getId().equals(plan.getAuditStatusId()))
		{
			return "活动信息不是待审核状态，无法审核，请刷新页面！！";
		}
		if(StringUtil.isBlank(auditStatusId))
		{
			return "请选择审核状态！！";
		}
		if(!DeptActivityStatusEnum.UnPass.getId().equals(auditStatusId)&&!DeptActivityStatusEnum.Pass.getId().equals(auditStatusId))
		{
			return "请选择通过或不通过！！";
		}
		if(DeptActivityStatusEnum.UnPass.getId().equals(auditStatusId)&&StringUtil.isBlank(auditReason))
		{
			return "请填写审核意见！！";
		}
		SysUser sysuser=GlobalContext.getCurrentUser();
		plan.setAuditReason(auditReason);
		plan.setAuditStatusId(auditStatusId);
		plan.setAuditStatusName(DeptActivityStatusEnum.getNameById(auditStatusId));
		plan.setAuditUserFlow(sysuser.getUserFlow());
		int count=deptActivityBiz.save(plan);
        if (count == com.pinde.core.common.GlobalConstant.ZERO_LINE)
		{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}
	@RequestMapping(value = "/showPlan")
	public  String showPlan(String planFlow,String isAudit, Model model) {
		SysDeptMonthPlan plan=deptActivityBiz.readPlan(planFlow);
		model.addAttribute("plan",plan);
		if(plan!=null)
		{
			SysDept dept=deptBiz.readSysDept(plan.getDeptFlow());
			model.addAttribute("dept",dept);
			//主任列表
			String headRoleFlow= InitConfig.getSysCfg("res_head_role_flow");
			List<SysUser> heads=userBiz.searchUserByDeptAndRole(plan.getDeptFlow(),headRoleFlow);
			//教秘列表
			String secretaryRoleFlow= InitConfig.getSysCfg("res_secretary_role_flow");
			List<SysUser> secretarys=userBiz.searchUserByDeptAndRole(plan.getDeptFlow(),secretaryRoleFlow);
			model.addAttribute("heads",heads);
			model.addAttribute("secretarys",secretarys);
		}
		List<SysDeptMonthPlanItem> items=deptActivityBiz.readPlanItems(planFlow);
		Map<String ,List<SysDeptMonthPlanItem>>itemMap=new HashMap<>();
		List<String> userFlows=new ArrayList<>();
		if(items!=null)
		{
			for(SysDeptMonthPlanItem item:items)
			{
				List<SysDeptMonthPlanItem> temp=itemMap.get(item.getItemTypeId());
				if(temp==null) temp=new ArrayList<>();
				temp.add(item);
				itemMap.put(item.getItemTypeId(),temp);
				if(!userFlows.contains(item.getJoinUserFlow()))
					userFlows.add(item.getJoinUserFlow());
			}
		}
		List<SysDeptMonthExamInfo> examInfos=deptActivityBiz.readPlanExamInfos(planFlow);
		Map<String ,List<SysDeptMonthExamInfo>>examInfoMap=new HashMap<>();
		if(examInfos!=null)
		{
			for(SysDeptMonthExamInfo item:examInfos)
			{
				List<SysDeptMonthExamInfo> temp=examInfoMap.get(item.getUserTypeId());
				if(temp==null) temp=new ArrayList<>();
				temp.add(item);
				examInfoMap.put(item.getUserTypeId(),temp);
			}
		}
		List<SysUser> users=userBiz.searchSysUserByuserFlows(userFlows);
		if(users!=null)
		{
			Map<String ,SysUser> userMap=new HashMap<>();
			for(SysUser u:users)
			{
				userMap.put(u.getUserFlow(),u);
			}
			model.addAttribute("userMap",userMap);
		}
		model.addAttribute("isAudit",isAudit);
		model.addAttribute("itemMap",itemMap);
		model.addAttribute("examInfoMap",examInfoMap);
		return "/res/deptActivity/showPlan";
	}
	@RequestMapping(value = "/printPlan")
	public  void printPlan(String planFlow, HttpServletResponse response, Model model) throws  Exception {

		SysDeptMonthPlan plan=deptActivityBiz.readPlan(planFlow);
		model.addAttribute("plan",plan);

		String fileName = "暂无信息.xls";
		if(plan!=null)
		{
			if(DeptActivityTypeEnum.Dept.getId().equals(plan.getPlanTypeId()))
			{
				printDeptPlan(plan,response);
			}
			if(DeptActivityTypeEnum.Scientific.getId().equals(plan.getPlanTypeId()))
			{
				printScientificPlan(plan,response);

			}
		}else {
			Workbook wb = new HSSFWorkbook();
			fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			wb.write(response.getOutputStream());
		}

	}

	private void printDeptPlan(SysDeptMonthPlan plan, HttpServletResponse response) throws Exception {
		SysDept dept=deptBiz.readSysDept(plan.getDeptFlow());
		//主任列表
		String headRoleFlow= InitConfig.getSysCfg("res_head_role_flow");
		List<SysUser> heads=userBiz.searchUserByDeptAndRole(plan.getDeptFlow(),headRoleFlow);
		//教秘列表
		String secretaryRoleFlow= InitConfig.getSysCfg("res_secretary_role_flow");
		List<SysUser> secretarys=userBiz.searchUserByDeptAndRole(plan.getDeptFlow(),secretaryRoleFlow);

		List<SysDeptMonthPlanItem> items=deptActivityBiz.readPlanItems(plan.getPlanFlow());
		Map<String ,List<SysDeptMonthPlanItem>>itemMap=new HashMap<>();
		List<String> userFlows=new ArrayList<>();
		if(items!=null)
		{
			for(SysDeptMonthPlanItem item:items)
			{
				List<SysDeptMonthPlanItem> temp=itemMap.get(item.getItemTypeId());
				if(temp==null) temp=new ArrayList<>();
				temp.add(item);
				itemMap.put(item.getItemTypeId(),temp);
				if(!userFlows.contains(item.getJoinUserFlow()))
					userFlows.add(item.getJoinUserFlow());
			}
		}
		List<SysDeptMonthExamInfo> examInfos=deptActivityBiz.readPlanExamInfos(plan.getPlanFlow());
		Map<String ,List<SysDeptMonthExamInfo>>examInfoMap=new HashMap<>();
		if(examInfos!=null)
		{
			for(SysDeptMonthExamInfo item:examInfos)
			{
				List<SysDeptMonthExamInfo> temp=examInfoMap.get(item.getUserTypeId());
				if(temp==null) temp=new ArrayList<>();
				temp.add(item);
				examInfoMap.put(item.getUserTypeId(),temp);
			}
		}
		Map<String ,SysUser> userMap=new HashMap<>();
		List<SysUser> users=userBiz.searchSysUserByuserFlows(userFlows);
		if(users!=null)
		{
			for(SysUser u:users)
			{
				userMap.put(u.getUserFlow(),u);
			}
		}
		String fileName=dept.getDeptName()+"科室"+plan.getPlanTypeName()+plan.getPlanDate()+"月教学工作安排.xls";

		Workbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		Sheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);


		Font fontTwo = wb.createFont();
		fontTwo.setFontHeightInPoints((short) 12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//

		Map<Integer,Integer>  columnWidth=new HashMap<>();

		OutputStream os=response.getOutputStream();

		Integer rowNum=0;

		Row row =null;
		Cell cell = null;
		rowNum=createBaseTable(sheet,rowNum,cell,style,styleTwo,columnWidth,dept,heads,secretarys,plan);


		String title="教学查房安排（2周/次）带组主任负责";
		rowNum=createItemTable(sheet,rowNum,cell,style,styleTwo,columnWidth,itemMap,userMap,title,DeptActivityItemTypeEnum.JXCFAP.getId());

		title="病例讨论安排（2周/次）高级职称主持";
		rowNum=createItemTable(sheet,rowNum,cell,style,styleTwo,columnWidth,itemMap,userMap,title,DeptActivityItemTypeEnum.BLTLAP.getId());

		title="小讲课安排（2周/次）主治/住院医师负责";
		rowNum=createItemTable(sheet,rowNum,cell,style,styleTwo,columnWidth,itemMap,userMap,title,DeptActivityItemTypeEnum.XJKAP.getId());

		title="其他活动";
		rowNum=createItemTable(sheet,rowNum,cell,style,styleTwo,columnWidth,itemMap,userMap,title,DeptActivityItemTypeEnum.QTHD.getId());

		title="读书报告会安排（1月/次）";
		rowNum=createItemTable(sheet,rowNum,cell,style,styleTwo,columnWidth,itemMap,userMap,title,DeptActivityItemTypeEnum.DSBGHAP.getId());

		title="出科考核安排（加地点）";
		rowNum=createCkTable(sheet,rowNum,cell,style,styleTwo,columnWidth,examInfoMap,userMap,title,plan);


		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("备注");
		cell.setCellStyle(styleTwo);
		ExcleUtile.setColumnWidth("备注".getBytes().length,0, columnWidth);
		cell = row.createCell(1);
		cell.setCellValue(plan.getPlanDemo()==null?"":plan.getPlanDemo());
		cell.setCellStyle(styleTwo);
		ExcleUtile.setColumnWidth((plan.getPlanDemo()==null?"":plan.getPlanDemo()).getBytes().length,1, columnWidth);
		sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 4));//合并单元格

		fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	private Integer createBaseTable(Sheet sheet, Integer rowNum, Cell cell, CellStyle style, CellStyle styleTwo, Map<Integer, Integer> columnWidth,
									SysDept dept, List<SysUser> heads, List<SysUser> secretarys, SysDeptMonthPlan plan) {
		String title=dept.getDeptName()+"科室"+plan.getPlanTypeName()+plan.getPlanDate()+"月教学工作安排";
		Row row = sheet.createRow(rowNum++);

		cell = row.createCell(0);
		cell.setCellValue(title);
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth(title.getBytes().length,0, columnWidth);
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));//合并单元格

		//主任
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("教学主任");
		cell.setCellStyle(styleTwo);
		if(heads!=null&&heads.size()>0)
		{
			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum+heads.size()-2, 0, 0));//合并单元格
			rowNum--;
			for(int j=0;j<heads.size();j++)
			{
				SysUser su=heads.get(j);
				if(j!=0)
					row = sheet.createRow(rowNum++);
				else
					rowNum++;
				cell = row.createCell(1);
				cell.setCellValue(su.getUserName());
				cell.setCellStyle(styleTwo);
				ExcleUtile.setColumnWidth(su.getUserName().getBytes().length,1, columnWidth);
				sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 4));//合并单元格
			}
		}
		//宽度自适应
		ExcleUtile.setColumnWidth("教学主任".getBytes().length,0, columnWidth);
		//教学秘书
		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("教学秘书");
		cell.setCellStyle(styleTwo);
		if(secretarys!=null&&secretarys.size()>0)
		{
			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum+secretarys.size()-2, 0, 0));//合并单元格
			rowNum--;
			for(int j=0;j<secretarys.size();j++)
			{
				SysUser su=secretarys.get(j);
				if(j!=0)
					row = sheet.createRow(rowNum++);
				else
					rowNum++;
				cell = row.createCell(1);
				cell.setCellValue(su.getUserName());
				cell.setCellStyle(styleTwo);
				ExcleUtile.setColumnWidth(su.getUserName().getBytes().length,1, columnWidth);
				sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 4));//合并单元格
			}
		}
		//宽度自适应
		ExcleUtile.setColumnWidth("教学秘书".getBytes().length,0, columnWidth);
		return  rowNum;
	}

	private Integer createCkTable(Sheet sheet, Integer rowNum, Cell cell, CellStyle style, CellStyle styleTwo, Map<Integer, Integer> columnWidth,
								  Map<String, List<SysDeptMonthExamInfo>> examInfoMap, Map<String, SysUser> userMap,
								  String title, SysDeptMonthPlan plan) {

		Row	row = sheet.createRow(rowNum++);
		cell = null;
		cell = row.createCell(0);
		cell.setCellValue(title);
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth(title.getBytes().length,0, columnWidth);
		sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 0, 4));//合并单元格
		String chargeName="";
		String leaderName="";
		//负责人
		List<SysDeptMonthExamInfo> charges=examInfoMap.get(DeptActivityUserTypeEnum.Charge.getId());
		if(charges!=null&&charges.size()>0)
		{
			SysDeptMonthExamInfo charge=charges.get(0);

			SysUser su=userMap.get(charge.getExamUserFlow());
			if(su!=null)
			{
				chargeName=su.getUserName();
			}
		}
		rowNum=createChargeTr(sheet,rowNum,cell,style,columnWidth,chargeName,plan.getTheoryExamTime());
		//监考人
		rowNum=createCkUserTable(sheet,rowNum,cell,styleTwo,columnWidth,examInfoMap,userMap,DeptActivityUserTypeEnum.Invigilator.getId());
		//组长
		List<SysDeptMonthExamInfo> leaders=examInfoMap.get(DeptActivityUserTypeEnum.GroupLeader.getId());
		if(leaders!=null&&leaders.size()>0)
		{
			SysDeptMonthExamInfo leader=leaders.get(0);

			SysUser su=userMap.get(leader.getExamUserFlow());
			if(su!=null)
			{
				leaderName=su.getUserName();
			}
		}
		rowNum=createLeaderTr(sheet,rowNum,cell,style,columnWidth,leaderName,plan.getSkillExamTime());
		//考核组成员
		rowNum=createCkUserTable(sheet,rowNum,cell,styleTwo,columnWidth,examInfoMap,userMap,DeptActivityUserTypeEnum.Member.getId());
		return rowNum;
	}

	private Integer createLeaderTr(Sheet sheet, Integer rowNum, Cell cell, CellStyle style, Map<Integer, Integer> columnWidth, String leaderName, String skillExamTime) {
		Row	row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("操作考");
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth("操作考".getBytes().length,0, columnWidth);

		cell = row.createCell(1);
		cell.setCellValue(skillExamTime==null?"":skillExamTime);
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth((skillExamTime==null?"":skillExamTime).getBytes().length,1, columnWidth);

		cell = row.createCell(2);
		cell.setCellValue("组长");
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth("组长".getBytes().length,2, columnWidth);

		cell = row.createCell(3);
		cell.setCellValue(leaderName==null?"":leaderName);
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth((leaderName==null?"":leaderName).getBytes().length,3, columnWidth);
		sheet.addMergedRegion(new CellRangeAddress(rowNum-1,rowNum-1, 3, 4));//合并单元格

		return rowNum;
	}

	private Integer createCkUserTable(Sheet sheet, Integer rowNum, Cell cell, CellStyle styleTwo, Map<Integer, Integer> columnWidth, Map<String, List<SysDeptMonthExamInfo>> examInfoMap, Map<String, SysUser> userMap, String id) {

		String title=(DeptActivityUserTypeEnum.Member.getId().equals(id))?"考核组成员":"监考人";
		Row	row = sheet.createRow(rowNum++);
		cell = null;
		cell = row.createCell(0);
		cell.setCellValue(title);
		cell.setCellStyle(styleTwo);
		//宽度自适应
		ExcleUtile.setColumnWidth(title.getBytes().length,0, columnWidth);

		List<SysDeptMonthExamInfo> itemList=examInfoMap.get(id);
		if(itemList!=null&&itemList.size()>0)
		{
			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum+itemList.size()-2, 0, 0));//合并单元格
			rowNum--;
			for(int j=0;j<itemList.size();j++)
			{
				SysDeptMonthExamInfo item=itemList.get(j);
				if(j!=0)
					row = sheet.createRow(rowNum++);
				else
					rowNum++;
				cell = row.createCell(1);
				String userName="";
				SysUser su=userMap.get(item.getExamUserFlow());
				if(su!=null)
				{
					userName=su.getUserName();
				}
				cell.setCellValue(userName);
				cell.setCellStyle(styleTwo);
				ExcleUtile.setColumnWidth(userName.getBytes().length,1, columnWidth);
				sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 4));//合并单元格
			}
		}else{
			title="暂未安排";
			row = sheet.createRow(rowNum++);
			cell = null;
			cell = row.createCell(1);
			cell.setCellValue(title);
			cell.setCellStyle(styleTwo);
			//宽度自适应
			ExcleUtile.setColumnWidth(title.getBytes().length,1, columnWidth);
			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 4));//合并单元格
		}

		return rowNum;
	}

	private Integer createChargeTr(Sheet sheet, Integer rowNum, Cell cell, CellStyle style, Map<Integer, Integer> columnWidth, String chargeName, String theoryExamTime) {
		Row	row = sheet.createRow(rowNum++);
		cell = null;
		cell = row.createCell(0);
		cell.setCellValue("理论考（系统考就无此项）");
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth("理论考（系统考就无此项）".getBytes().length,0, columnWidth);

		cell = row.createCell(1);
		cell.setCellValue(theoryExamTime==null?"":theoryExamTime);
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth((theoryExamTime==null?"":theoryExamTime).getBytes().length,1, columnWidth);

		cell = row.createCell(2);
		cell.setCellValue("负责人");
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth("负责人".getBytes().length,2, columnWidth);

		cell = row.createCell(3);
		cell.setCellValue(chargeName==null?"":chargeName);
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth((chargeName==null?"":chargeName).getBytes().length,3, columnWidth);

		cell = row.createCell(4);
		cell.setCellValue("15人以上2人监考");
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth("15人以上2人监考".getBytes().length,4, columnWidth);
		return rowNum;
	}

	private Integer createItemTable(Sheet sheet, Integer rowNum, Cell cell, CellStyle style, CellStyle styleTwo, Map<Integer, Integer> columnWidth, Map<String, List<SysDeptMonthPlanItem>> itemMap, Map<String, SysUser> userMap, String title, String id) {

		Row	row = sheet.createRow(rowNum++);
		cell = null;
		cell = row.createCell(0);
		cell.setCellValue(title);
		cell.setCellStyle(style);
		//宽度自适应
		ExcleUtile.setColumnWidth(title.getBytes().length,0, columnWidth);
		sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 0, 4));//合并单元格

		rowNum=createTableHead(sheet,rowNum,cell,style,columnWidth,id);

		List<SysDeptMonthPlanItem> itemList=itemMap.get(id);
		if(itemList!=null&&itemList.size()>0)
		{
			for(SysDeptMonthPlanItem item:itemList)
			{
				if(DeptActivityItemTypeEnum.JTBKAP.getId().equals(id))
				{
					row = sheet.createRow(rowNum++);
					cell = null;
					cell = row.createCell(0);
					cell.setCellValue(item.getPlanTime());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getPlanTime().getBytes().length, 0, columnWidth);
					String userName = "";
					String userCode = "";
					SysUser su = userMap.get(item.getJoinUserFlow());
					if (su != null) {
						userCode = su.getUserCode();
						userName = su.getUserName();
					}
					cell = row.createCell(1);
					cell.setCellValue(userName);
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(userName.getBytes().length, 1, columnWidth);

					cell = row.createCell(2);
					cell.setCellValue(userCode);
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(userCode.getBytes().length, 2, columnWidth);

					cell = row.createCell(3);
					cell.setCellValue(item.getContent());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getContent().getBytes().length, 3, columnWidth);
					sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 3, 4));//合并单元格
				}else if(DeptActivityItemTypeEnum.TKAP.getId().equals(id))
				{

					row = sheet.createRow(rowNum++);
					cell = null;
					cell = row.createCell(0);
					cell.setCellValue(item.getPlanTime());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getPlanTime().getBytes().length, 0, columnWidth);
					String userName = "";
					String userCode = "";
					SysUser su = userMap.get(item.getJoinUserFlow());
					if (su != null) {
						userCode = su.getUserCode();
						userName = su.getUserName();
					}
					cell = row.createCell(1);
					cell.setCellValue(userName);
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(userName.getBytes().length, 1, columnWidth);

					cell = row.createCell(2);
					cell.setCellValue(userCode);
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(userCode.getBytes().length, 2, columnWidth);

					cell = row.createCell(3);
					cell.setCellValue(item.getContent());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getContent().getBytes().length, 3, columnWidth);

					cell = row.createCell(4);
					cell.setCellValue(item.getTitle());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getTitle().getBytes().length, 4, columnWidth);
				}else if(DeptActivityItemTypeEnum.DDAP.getId().equals(id))
				{

					row = sheet.createRow(rowNum++);
					cell = null;
					cell = row.createCell(0);
					cell.setCellValue(item.getPlanTime());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getPlanTime().getBytes().length, 0, columnWidth);
					String userName = "";
					String userCode = "";
					SysUser su = userMap.get(item.getJoinUserFlow());
					if (su != null) {
						userCode = su.getUserCode();
						userName = su.getUserName();
					}
					cell = row.createCell(1);
					cell.setCellValue(item.getContent());
					cell.setCellStyle(styleTwo);
					//宽度自适应
						ExcleUtile.setColumnWidth(item.getContent().getBytes().length, 1, columnWidth);

					sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 2));//合并单元格

					cell = row.createCell(3);
					cell.setCellValue(userName);
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(userName.getBytes().length,3, columnWidth);
					sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 3, 4));//合并单元格

				}else if(DeptActivityItemTypeEnum.SQTHD.getId().equals(id))
				{

					row = sheet.createRow(rowNum++);
					cell = null;
					cell = row.createCell(0);
					cell.setCellValue(item.getPlanTime());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getPlanTime().getBytes().length, 0, columnWidth);
					String userName = "";
					String userCode = "";
					SysUser su = userMap.get(item.getJoinUserFlow());
					if (su != null) {
						userCode = su.getUserCode();
						userName = su.getUserName();
					}
					cell = row.createCell(1);
					cell.setCellValue(userName);
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(userName.getBytes().length, 1, columnWidth);

					sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 2));//合并单元格

					cell = row.createCell(3);
					cell.setCellValue(userCode);
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(userCode.getBytes().length,3, columnWidth);
					sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 3, 4));//合并单元格

				}else {
					row = sheet.createRow(rowNum++);
					cell = null;
					cell = row.createCell(0);
					cell.setCellValue(item.getPlanTime());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getPlanTime().getBytes().length, 0, columnWidth);
					String userName = "";
					String userCode = "";
					SysUser su = userMap.get(item.getJoinUserFlow());
					if (su != null) {
						userCode = su.getUserCode();
						userName = su.getUserName();
					}
					cell = row.createCell(1);
					cell.setCellValue(userName);
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(userName.getBytes().length, 1, columnWidth);

					cell = row.createCell(2);
					cell.setCellValue(userCode);
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(userCode.getBytes().length, 2, columnWidth);

					cell = row.createCell(3);
					cell.setCellValue(item.getContent());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getContent().getBytes().length, 3, columnWidth);

					cell = row.createCell(4);
					cell.setCellValue(item.getAddress());
					cell.setCellStyle(styleTwo);
					//宽度自适应
					ExcleUtile.setColumnWidth(item.getAddress().getBytes().length, 4, columnWidth);
				}
			}

		}else{
			title="暂未安排";
			row = sheet.createRow(rowNum++);
			cell = null;
			cell = row.createCell(0);
			cell.setCellValue(title);
			cell.setCellStyle(styleTwo);
			//宽度自适应
			ExcleUtile.setColumnWidth(title.getBytes().length,0, columnWidth);
			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 0, 4));//合并单元格
		}

		return rowNum;
	}

	private Integer createTableHead(Sheet sheet, Integer rowNum, Cell cell, CellStyle style, Map<Integer, Integer> columnWidth, String id) {


		Row row = null;
		if(DeptActivityItemTypeEnum.JTBKAP.getId().equals(id))
		{
			row = sheet.createRow(rowNum++);
			cell = null;
			cell = row.createCell(0);
			cell.setCellValue("日期");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("日期".getBytes().length, 0, columnWidth);

			cell = row.createCell(1);
			cell.setCellValue("主持人");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("主持人".getBytes().length, 1, columnWidth);

			cell = row.createCell(2);
			cell.setCellValue("工号");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("工号".getBytes().length, 2, columnWidth);

			cell = row.createCell(3);
			cell.setCellValue("内容");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("内容".getBytes().length, 3, columnWidth);
			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 3, 4));//合并单元格
		}else if(DeptActivityItemTypeEnum.TKAP.getId().equals(id))
		{
			row = sheet.createRow(rowNum++);
			cell = null;
			cell = row.createCell(0);
			cell.setCellValue("日期");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("日期".getBytes().length, 0, columnWidth);

			cell = row.createCell(1);
			cell.setCellValue("听课人");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("听课人".getBytes().length, 1, columnWidth);

			cell = row.createCell(2);
			cell.setCellValue("工号");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("工号".getBytes().length, 2, columnWidth);

			cell = row.createCell(3);
			cell.setCellValue("主讲人");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("主讲人".getBytes().length, 3, columnWidth);

			cell = row.createCell(4);
			cell.setCellValue("授课题目");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("授课题目".getBytes().length, 4, columnWidth);
		}else if(DeptActivityItemTypeEnum.DDAP.getId().equals(id))
		{

			row = sheet.createRow(rowNum++);
			cell = null;
			cell = row.createCell(0);
			cell.setCellValue("日期");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("日期".getBytes().length, 0, columnWidth);

			cell = row.createCell(1);
			cell.setCellValue("督导内容");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("督导内容".getBytes().length, 1, columnWidth);

			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 2));//合并单元格

			cell = row.createCell(3);
			cell.setCellValue("督导人");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("督导人".getBytes().length,3, columnWidth);
			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 3, 4));//合并单元格

		}else if(DeptActivityItemTypeEnum.SQTHD.getId().equals(id))
		{
			row = sheet.createRow(rowNum++);
			cell = null;
			cell = row.createCell(0);
			cell.setCellValue("日期");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("日期".getBytes().length, 0, columnWidth);

			cell = row.createCell(1);
			cell.setCellValue("主持人");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("主持人".getBytes().length, 1, columnWidth);

			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 2));//合并单元格

			cell = row.createCell(3);
			cell.setCellValue("工号");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("工号".getBytes().length,3, columnWidth);
			sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 3, 4));//合并单元格

		}else {
			row = sheet.createRow(rowNum++);
			cell = row.createCell(0);
			cell.setCellValue("日期");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("日期".getBytes().length, 0, columnWidth);

			cell = row.createCell(1);
			cell.setCellValue("主持人");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("主持人".getBytes().length, 1, columnWidth);

			cell = row.createCell(2);
			cell.setCellValue("工号");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("工号".getBytes().length, 2, columnWidth);

			cell = row.createCell(3);
			cell.setCellValue("内容");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("内容".getBytes().length, 3, columnWidth);

			cell = row.createCell(4);
			cell.setCellValue("地点");
			cell.setCellStyle(style);
			//宽度自适应
			ExcleUtile.setColumnWidth("地点".getBytes().length, 4, columnWidth);
		}
		return rowNum;
	}

	private void printScientificPlan(SysDeptMonthPlan plan, HttpServletResponse response) throws Exception {
		SysDept dept=deptBiz.readSysDept(plan.getDeptFlow());
		//主任列表
		String headRoleFlow= InitConfig.getSysCfg("res_head_role_flow");
		List<SysUser> heads=userBiz.searchUserByDeptAndRole(plan.getDeptFlow(),headRoleFlow);
		//教秘列表
		String secretaryRoleFlow= InitConfig.getSysCfg("res_secretary_role_flow");
		List<SysUser> secretarys=userBiz.searchUserByDeptAndRole(plan.getDeptFlow(),secretaryRoleFlow);

		List<SysDeptMonthPlanItem> items=deptActivityBiz.readPlanItems(plan.getPlanFlow());
		Map<String ,List<SysDeptMonthPlanItem>>itemMap=new HashMap<>();
		List<String> userFlows=new ArrayList<>();
		if(items!=null)
		{
			for(SysDeptMonthPlanItem item:items)
			{
				List<SysDeptMonthPlanItem> temp=itemMap.get(item.getItemTypeId());
				if(temp==null) temp=new ArrayList<>();
				temp.add(item);
				itemMap.put(item.getItemTypeId(),temp);
				if(!userFlows.contains(item.getJoinUserFlow()))
					userFlows.add(item.getJoinUserFlow());
			}
		}
		List<SysDeptMonthExamInfo> examInfos=deptActivityBiz.readPlanExamInfos(plan.getPlanFlow());
		Map<String ,List<SysDeptMonthExamInfo>>examInfoMap=new HashMap<>();
		if(examInfos!=null)
		{
			for(SysDeptMonthExamInfo item:examInfos)
			{
				List<SysDeptMonthExamInfo> temp=examInfoMap.get(item.getUserTypeId());
				if(temp==null) temp=new ArrayList<>();
				temp.add(item);
				examInfoMap.put(item.getUserTypeId(),temp);
			}
		}
		Map<String ,SysUser> userMap=new HashMap<>();
		List<SysUser> users=userBiz.searchSysUserByuserFlows(userFlows);
		if(users!=null)
		{
			for(SysUser u:users)
			{
				userMap.put(u.getUserFlow(),u);
			}
		}
		String fileName=dept.getDeptName()+"科室"+plan.getPlanTypeName()+plan.getPlanDate()+"月教学工作安排.xls";

		Workbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		Sheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short

		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);

		Font fontTwo = wb.createFont();
		fontTwo.setFontHeightInPoints((short) 12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//
		Map<Integer,Integer>  columnWidth=new HashMap<>();

		OutputStream os=response.getOutputStream();
		Integer rowNum=0;
		Row row =null;
		Cell cell = null;
		rowNum=createBaseTable(sheet,rowNum,cell,style,styleTwo,columnWidth,dept,heads,secretarys,plan);

		String title="集体备课安排（月/次）";
		rowNum=createItemTable(sheet,rowNum,cell,style,styleTwo,columnWidth,itemMap,userMap,title,DeptActivityItemTypeEnum.JTBKAP.getId());

		title="听课安排（月/次）";
		rowNum=createItemTable(sheet,rowNum,cell,style,styleTwo,columnWidth,itemMap,userMap,title,DeptActivityItemTypeEnum.TKAP.getId());

		title="督导安排（月/次）教学秘书和主任各一次，交叉"+plan.getPlanTypeName();
		rowNum=createItemTable(sheet,rowNum,cell,style,styleTwo,columnWidth,itemMap,userMap,title,DeptActivityItemTypeEnum.DDAP.getId());

		title="其他活动";
		rowNum=createItemTable(sheet,rowNum,cell,style,styleTwo,columnWidth,itemMap,userMap,title,DeptActivityItemTypeEnum.SQTHD.getId());



		 title=plan.getPlanTypeName()+"考核安排";
		rowNum=createCkTable(sheet,rowNum,cell,style,styleTwo,columnWidth,examInfoMap,userMap,title,plan);

		row = sheet.createRow(rowNum++);
		cell = row.createCell(0);
		cell.setCellValue("备注");
		cell.setCellStyle(styleTwo);
		ExcleUtile.setColumnWidth("备注".getBytes().length,0, columnWidth);
		cell = row.createCell(1);
		cell.setCellValue(plan.getPlanDemo()==null?"":plan.getPlanDemo());
		cell.setCellStyle(styleTwo);
		ExcleUtile.setColumnWidth((plan.getPlanDemo()==null?"":plan.getPlanDemo()).getBytes().length,1, columnWidth);
		sheet.addMergedRegion(new CellRangeAddress(rowNum-1, rowNum-1, 1, 4));//合并单元格

		fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@RequestMapping(value = "/addPlan")
	public  String addPlan(String planFlow, Model model) {
		if(StringUtil.isNotBlank(planFlow)) {
			SysDeptMonthPlan plan = deptActivityBiz.readPlan(planFlow);
			model.addAttribute("plan", plan);
			if (plan != null) {
				SysDept dept = deptBiz.readSysDept(plan.getDeptFlow());
				model.addAttribute("dept", dept);
				//主任列表
				String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
				List<SysUser> heads = userBiz.searchUserByDeptAndRole(plan.getDeptFlow(), headRoleFlow);
				//教秘列表
				String secretaryRoleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
				List<SysUser> secretarys = userBiz.searchUserByDeptAndRole(plan.getDeptFlow(), secretaryRoleFlow);
				model.addAttribute("heads", heads);
				model.addAttribute("secretarys", secretarys);

				//带教列表 主任列表
				String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
				String trainTeacherRoleFlow = InitConfig.getSysCfg("res_train_teacher_role_flow");
				if(DeptActivityTypeEnum.Dept.getId().equals(plan.getPlanTypeId())) {
					List<SysUser> members = userBiz.readDeptTeachAndHead(plan.getDeptFlow(), teacherRoleFlow, headRoleFlow,trainTeacherRoleFlow);
					model.addAttribute("members", members);
				}

				if(DeptActivityTypeEnum.Scientific.getId().equals(plan.getPlanTypeId())) {
					//全院师资
					List<SysUser> members = userBiz.readOrgTeas(dept.getOrgFlow(), teacherRoleFlow, headRoleFlow,secretaryRoleFlow,trainTeacherRoleFlow);
					model.addAttribute("members", members);
					//其他科室的主任 或教秘
					List<SysUser> otherMembers=new ArrayList<>();
					List<String> flows=new ArrayList<>();

					List<SysUser> otherHeads = userBiz.searchOtherUserByDeptAndRole(dept.getOrgFlow(), plan.getDeptFlow(), headRoleFlow);
					List<SysUser> otherSecretarys = userBiz.searchOtherUserByDeptAndRole(dept.getOrgFlow(), plan.getDeptFlow(), secretaryRoleFlow);
					if(otherHeads!=null)
					{
						for(SysUser su:otherHeads)
						{
							if(!flows.contains(su.getUserFlow()))
							{
								flows.add(su.getUserFlow());
								otherMembers.add(su);
							}
						}
					}
					if(otherSecretarys!=null)
					{
						for(SysUser su:otherSecretarys)
						{
							if(!flows.contains(su.getUserFlow()))
							{
								flows.add(su.getUserFlow());
								otherMembers.add(su);
							}
						}
					}
					model.addAttribute("otherMembers", otherMembers);

				}
				model.addAttribute("planDate", plan.getPlanDate());
				model.addAttribute("deptFlow", plan.getDeptFlow());
				model.addAttribute("planTypeId", plan.getPlanTypeId());
			}
			List<SysDeptMonthPlanItem> items = deptActivityBiz.readPlanItems(planFlow);
			Map<String, List<SysDeptMonthPlanItem>> itemMap = new HashMap<>();
			List<String> userFlows = new ArrayList<>();
			if (items != null) {
				for (SysDeptMonthPlanItem item : items) {
					List<SysDeptMonthPlanItem> temp = itemMap.get(item.getItemTypeId());
					if (temp == null) temp = new ArrayList<>();
					temp.add(item);
					itemMap.put(item.getItemTypeId(), temp);
					if (!userFlows.contains(item.getJoinUserFlow()))
						userFlows.add(item.getJoinUserFlow());
				}
			}
			List<SysDeptMonthExamInfo> examInfos = deptActivityBiz.readPlanExamInfos(planFlow);
			Map<String, List<SysDeptMonthExamInfo>> examInfoMap = new HashMap<>();
			if (examInfos != null) {
				for (SysDeptMonthExamInfo item : examInfos) {
					List<SysDeptMonthExamInfo> temp = examInfoMap.get(item.getUserTypeId());
					if (temp == null) temp = new ArrayList<>();
					temp.add(item);
					examInfoMap.put(item.getUserTypeId(), temp);
				}
			}
			List<SysUser> users = userBiz.searchSysUserByuserFlows(userFlows);
			if (users != null) {
				Map<String, SysUser> userMap = new HashMap<>();
				for (SysUser u : users) {
					userMap.put(u.getUserFlow(), u);
				}
				model.addAttribute("userMap", userMap);
			}
			model.addAttribute("itemMap", itemMap);
			model.addAttribute("examInfoMap", examInfoMap);
			return "res/deptActivity/editPlan";
		}else{
			SysUser user=GlobalContext.getCurrentUser();
			List<Map<String, Object>> deptList = deptBiz.queryDeptListByFlow(user.getUserFlow());
			model.addAttribute("deptList",deptList);
		}
		return "res/deptActivity/addPlan";
	}
	@RequestMapping(value = "/newPlan")
	public  String newPlan(String deptFlow,String planDate,String planTypeId, Model model) {
		SysDept dept = deptBiz.readSysDept(deptFlow);
		model.addAttribute("dept", dept);
		//带教列表 主任列表
		String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
		String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
		String trainTeacherRoleFlow = InitConfig.getSysCfg("res_train_teacher_role_flow");
		//主任列表
		List<SysUser> heads = userBiz.searchUserByDeptAndRole(deptFlow, headRoleFlow);
		//教秘列表
		String secretaryRoleFlow = InitConfig.getSysCfg("res_secretary_role_flow");
		List<SysUser> secretarys = userBiz.searchUserByDeptAndRole(deptFlow, secretaryRoleFlow);
		model.addAttribute("heads", heads);
		model.addAttribute("secretarys", secretarys);
		if(DeptActivityTypeEnum.Dept.getId().equals(planTypeId)) {
			List<SysUser> members = userBiz.readDeptTeachAndHead(deptFlow, teacherRoleFlow, headRoleFlow,trainTeacherRoleFlow);
			model.addAttribute("members", members);
		}

		if(DeptActivityTypeEnum.Scientific.getId().equals(planTypeId)) {
			//全院师资
			List<SysUser> members = userBiz.readOrgTeas(dept.getOrgFlow(), teacherRoleFlow, headRoleFlow,secretaryRoleFlow,trainTeacherRoleFlow);
			model.addAttribute("members", members);
			//其他科室的主任 或教秘
			List<SysUser> otherMembers=new ArrayList<>();
			List<String> flows=new ArrayList<>();
			List<SysUser> otherHeads = userBiz.searchOtherUserByDeptAndRole(dept.getOrgFlow(), deptFlow, headRoleFlow);
			List<SysUser> otherSecretarys = userBiz.searchOtherUserByDeptAndRole(dept.getOrgFlow(),deptFlow, secretaryRoleFlow);
			if(otherHeads!=null)
			{
				for(SysUser su:otherHeads)
				{
					if(!flows.contains(su.getUserFlow()))
					{
						flows.add(su.getUserFlow());
						otherMembers.add(su);
					}
				}
			}
			if(otherSecretarys!=null)
			{
				for(SysUser su:otherSecretarys)
				{
					if(!flows.contains(su.getUserFlow()))
					{
						flows.add(su.getUserFlow());
						otherMembers.add(su);
					}
				}
			}
			model.addAttribute("otherMembers", otherMembers);
		}
		if(StringUtil.isNotBlank(planTypeId)){
            model.addAttribute("planTypeName", com.pinde.core.common.enums.DictTypeEnum.DeptActivityType.getDictNameById(planTypeId));
		}
		model.addAttribute("planDate", planDate);
		model.addAttribute("deptFlow", deptFlow);
		model.addAttribute("planTypeId", planTypeId);
		return "res/deptActivity/editPlan";
	}

	@RequestMapping(value = "/checkPlan")
	@ResponseBody
	public  String checkPlan(String deptFlow,String planDate,String planTypeId, Model model) {
		if(StringUtil.isBlank(deptFlow))
		{
			return "请选择科室！";
		}
		if(StringUtil.isBlank(planTypeId))
		{
			return "请选择科室类型！";
		}
		if(StringUtil.isBlank(planDate))
		{
			return "请选择月度！";
		}

		SysDept dept=deptBiz.readSysDept(deptFlow);
		if(dept==null)
		{
			return "科室信息不存在，请重新选择！";
		}
		SysDeptMonthPlan plan = deptActivityBiz.findPlan(deptFlow,planDate,planTypeId);
		if(plan!=null)
		{

			return dept.getDeptName()+"科室"+plan.getPlanTypeName()+planDate+"月教学工作安排已存在";
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	@RequestMapping(value="/savePlan")
	@ResponseBody
	public String savePlan(@RequestBody ResDeptPlanForm planForm, Model model) throws Exception{
		if(StringUtil.isBlank(planForm.getDeptFlow()))
		{
			return "请选择科室！";
		}
		if(StringUtil.isBlank(planForm.getPlanTypeId()))
		{
			return "请选择科室类型！";
		}
		if(StringUtil.isBlank(planForm.getPlanDate()))
		{
			return "请选择月度！";
		}

		SysDept dept=deptBiz.readSysDept(planForm.getDeptFlow());
		if(dept==null)
		{
			return "科室信息不存在，请重新选择！";
		}
		if(StringUtil.isBlank(planForm.getPlanFlow())) {
			SysDeptMonthPlan plan = deptActivityBiz.findPlan(planForm.getDeptFlow(), planForm.getPlanDate(), planForm.getPlanTypeId());
			if (plan != null) {
				return dept.getDeptName() + "科室" + plan.getPlanTypeName() + planForm.getPlanDate() + "月教学工作安排已存在";
			}
		}
		int result = deptActivityBiz.savePlan(planForm);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}
}
