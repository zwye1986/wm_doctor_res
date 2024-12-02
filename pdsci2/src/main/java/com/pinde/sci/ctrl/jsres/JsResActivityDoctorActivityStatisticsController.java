package com.pinde.sci.ctrl.jsres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.common.enums.sch.ActivityTypeEnum;
import com.pinde.sci.model.mo.ResJointOrg;
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
import java.util.stream.Collectors;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/jsres/doctorActivityStatistics")
public class JsResActivityDoctorActivityStatisticsController extends GeneralController {
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
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@RequestMapping(value="/main")
	public String main(String  roleFlag,Model model, String  baseFlag){
		if("head".equals(roleFlag))
		{
			return "jsres/activity/doctorStatistics/main2";
		}else {
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			List<SysOrg> orgs = null;
			List<String> orgFlowList = null;
			if(null != currentOrg && "University".equals(currentOrg.getOrgTypeId())){
				orgs=orgBiz.getUniOrgs(currentOrg.getSendSchoolId(),currentOrg.getSendSchoolName());
			}else{
				List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
				orgs = new ArrayList<>();
				orgs.add(currentOrg);
				if (joinOrgs != null && !joinOrgs.isEmpty()) {
					orgFlowList = new ArrayList<>();
					orgFlowList.add(currentOrg.getOrgFlow());
					for (ResJointOrg jointOrg : joinOrgs) {
						SysOrg joOrg = orgBiz.readSysOrg(jointOrg.getJointOrgFlow());
						orgs.add(joOrg);
					}
				}
				//是否含有协同基地
				if (joinOrgs != null && joinOrgs.size() > 0) {
					model.addAttribute("hasJointOrg", "1");
				}
			}

			model.addAttribute("currentOrg", currentOrg);
			model.addAttribute("orgFlowList", orgFlowList);
			model.addAttribute("orgs", orgs);
			if("university".equals(roleFlag)){
				model.addAttribute("roleFlag", roleFlag);
				return "jsres/activity/doctorStatistics/main3";
			}
			return "jsres/activity/doctorStatistics/main";
		}
	}

	@RequestMapping(value="/mainAcc")
	public String mainAcc(String  roleFlag,Model model, String  baseFlag){
		if("head".equals(roleFlag))
		{
			return "jsres/activity/doctorStatistics/main2";
		}else {
			SysUser currentUser = GlobalContext.getCurrentUser();
			SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
			List<SysOrg> orgs = null;
			List<String> orgFlowList = null;
			if(null != currentOrg && "University".equals(currentOrg.getOrgTypeId())){
				orgs=orgBiz.getUniOrgs(currentOrg.getSendSchoolId(),currentOrg.getSendSchoolName());
			}else{
				List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
				orgs = new ArrayList<>();
				orgs.add(currentOrg);
				if (joinOrgs != null && !joinOrgs.isEmpty()) {
					orgFlowList = new ArrayList<>();
					orgFlowList.add(currentOrg.getOrgFlow());
					for (ResJointOrg jointOrg : joinOrgs) {
						SysOrg joOrg = orgBiz.readSysOrg(jointOrg.getJointOrgFlow());
						orgs.add(joOrg);
					}
				}
				//是否含有协同基地
				if (joinOrgs != null && joinOrgs.size() > 0) {
					model.addAttribute("hasJointOrg", "1");
				}
			}

			model.addAttribute("currentOrg", currentOrg);
			model.addAttribute("orgFlowList", orgFlowList);
			model.addAttribute("orgs", orgs);
			if("university".equals(roleFlag)){
				model.addAttribute("roleFlag", roleFlag);
				return "jsres/activity/doctorStatistics/main3";
			}
			return "jsres/activity/doctorStatistics/mainAcc";
		}
	}

	@RequestMapping(value = {"/list" })
	public String list (String trainingTypeId,String trainingSpeId,String sessionNumber,String orgFlow, String trainingYear, String studentStatus,
						String roleFlag,String isDept,String userName,String idNo,String graduationYear,
						String[] datas,String startTime,String endTime,String school,
						   Integer currentPage,HttpServletRequest request,Model model,String baseFlag){
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg currentOrg=orgBiz.readSysOrg(currentUser.getOrgFlow());
		String userOrgFlow = "";
		if(currentUser != null){
			userOrgFlow = currentUser.getOrgFlow();
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
				for(String s:datas){
					docTypeList.add(s);
				}
		}

		String[] sessionNumberArr = sessionNumber.split(",");
		List<String> sessionNumbers = new ArrayList<>(Arrays.asList(sessionNumberArr));

		//是否有协同基地
		List<String> orgFlowList = new ArrayList<>();
		if("head".equals(roleFlag))
		{
			//orgFlowList添加当前基地Flow
			orgFlowList.add(currentOrg.getOrgFlow());
		}else if("university".equals(roleFlag)){
			if(StringUtil.isBlank(orgFlow)){
				//orgFlowList添加当前基地Flow
				List<SysOrg> orgs = orgBiz.getUniOrgs(currentOrg.getSendSchoolId(),currentOrg.getSendSchoolName());
				orgFlowList = orgs.stream().map(SysOrg::getOrgFlow).collect(Collectors.toList());
			}else{
				orgFlowList.add(orgFlow);
			}

		}else{
			List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
			if(joinOrgs!=null&&!joinOrgs.isEmpty()){
				model.addAttribute("hasJointOrg","1");
				//筛选条件orgFlow是否为空
				if(StringUtil.isBlank(orgFlow)){
					//orgFlowList添加当前基地Flow
					orgFlowList.add(currentOrg.getOrgFlow());
					//orgFlowList添加协同基地Flow
					for(ResJointOrg jointOrg:joinOrgs){
						orgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}else {
					//如果筛选条件orgFlow非空，orgFlowList添加orgFlow
					orgFlowList.add(orgFlow);
				}
			}else {
				//无协同基地则只需添加当前基地
				orgFlowList.add(currentOrg.getOrgFlow());
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		if ("university".equals(roleFlag) && StringUtil.isNotEmpty(currentUser.getSchool())){
			parMp.put("school",currentUser.getSchool());
		}
		parMp.put("orgFlowList", orgFlowList);
		parMp.put("docTypeList",docTypeList);
        parMp.put("typeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		parMp.put("speId", trainingSpeId);
		parMp.put("sessionNumbers", sessionNumbers);
		parMp.put("userName", userName);
		parMp.put("roleFlag", roleFlag);
		parMp.put("userFlow", currentUser.getUserFlow());
		parMp.put("idNo", idNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("startTime",startTime);
		parMp.put("endTime",endTime);
		parMp.put("isDept",isDept);
		parMp.put("baseFlag",baseFlag);
		parMp.put("userOrgFlow",userOrgFlow);
		parMp.put("trainingYear",trainingYear);
		parMp.put("studentStatus",studentStatus);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list = activityBiz.getDoctorActivityStatistics(parMp);
		Map<String,Object> map=new HashMap<>();
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> u:list)
			{
				map.put((String)u.get("doctorFlow"),activityBiz.getDoctorActivityStatisticsMap((String)u.get("doctorFlow"),startTime,endTime,isDept, currentUser.getUserFlow()));
			}
		}
		model.addAttribute("map",map);
		model.addAttribute("list", list);
		return "jsres/activity/doctorStatistics/list";
	}
	@RequestMapping(value="/exportList")
	public void exportList(String trainingTypeId,String trainingSpeId,String sessionNumber,String orgFlow,String isDept,String roleFlag,String baseFlag,String school, String trainingYear, String studentStatus,
						   String userName,String idNo,String graduationYear,String[] datas,String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg currentOrg=orgBiz.readSysOrg(currentUser.getOrgFlow());
		String userOrgFlow = currentUser.getOrgFlow();
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}

		String[] sessionNumberArr = sessionNumber.split(",");
		List<String> sessionNumbers = new ArrayList<>(Arrays.asList(sessionNumberArr));

		//是否有协同基地
		List<String> orgFlowList = new ArrayList<>();
		if("head".equals(roleFlag))
		{
			//orgFlowList添加当前基地Flow
			orgFlowList.add(currentOrg.getOrgFlow());
		}else if("university".equals(roleFlag)){
			if(StringUtil.isBlank(orgFlow)){
				//orgFlowList添加当前基地Flow
				List<SysOrg> orgs = orgBiz.getUniOrgs(currentOrg.getSendSchoolId(),currentOrg.getSendSchoolName());
				orgFlowList = orgs.stream().map(SysOrg::getOrgFlow).collect(Collectors.toList());
			}else{
				orgFlowList.add(orgFlow);
			}

		}else{
			List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
			if(joinOrgs!=null&&!joinOrgs.isEmpty()){
				//筛选条件orgFlow是否为空
				if(StringUtil.isBlank(orgFlow)){
					//orgFlowList添加当前基地Flow
					orgFlowList.add(currentOrg.getOrgFlow());
					//orgFlowList添加协同基地Flow
					for(ResJointOrg jointOrg:joinOrgs){
						orgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}else {
					//如果筛选条件orgFlow非空，orgFlowList添加orgFlow
					orgFlowList.add(orgFlow);
				}
			}else {
				//无协同基地则只需添加当前基地
				orgFlowList.add(currentOrg.getOrgFlow());
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		if ("university".equals(roleFlag) && StringUtil.isNotEmpty(currentUser.getSchool())){
			orgFlowList.add(currentUser.getSchool());
			parMp.put("school", currentUser.getSchool());
		}
		parMp.put("orgFlowList", orgFlowList);
		parMp.put("docTypeList",docTypeList);
        parMp.put("typeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		parMp.put("speId", trainingSpeId);
		parMp.put("sessionNumbers", sessionNumbers);
		parMp.put("userName", userName);
		parMp.put("roleFlag", roleFlag);
		parMp.put("userFlow", currentUser.getUserFlow());
		parMp.put("idNo", idNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("startTime",startTime);
		parMp.put("endTime",endTime);
		parMp.put("isDept",isDept);
		parMp.put("baseFlag",baseFlag);
		parMp.put("userOrgFlow",userOrgFlow);
		parMp.put("trainingYear",trainingYear);
		parMp.put("studentStatus",studentStatus);
		List<Map<String,Object>> list = activityBiz.getDoctorActivityStatistics(parMp);
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

		Map<Integer, Integer> columnWidth = new HashMap<>();

		List<String> titles=new ArrayList<>();
		titles.add("姓名");
		titles.add("培训类型");
		titles.add("专业");
		titles.add("年级");
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
					String doctorFlow= (String) u.get("doctorFlow");
					Map<String,Object>map=activityBiz.getDoctorActivityStatisticsMap(doctorFlow,startTime,endTime, isDept, currentUser.getUserFlow());
					int j=0;
					String doctorName= u.get("doctorName")==null? "": (String)u.get("doctorName");
					String trainingTypeName= u.get("trainingTypeName")==null? "": (String)u.get("trainingTypeName");
					String trainingSpeName= u.get("trainingSpeName")==null? "": (String)u.get("trainingSpeName");
					String sessionNumber2= u.get("sessionNumber")==null? "": (String)u.get("sessionNumber");
					String countNum="0";
					row.createCell(j).setCellValue(doctorName);
					ExcleUtile.setColumnWidth(doctorName.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(trainingTypeName);
					ExcleUtile.setColumnWidth(trainingTypeName.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(trainingSpeName);
					ExcleUtile.setColumnWidth(trainingSpeName.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(sessionNumber2);
					ExcleUtile.setColumnWidth(sessionNumber2.getBytes().length, j++, columnWidth);

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
							String result= String.valueOf(map.get(doctorFlow+ e.getId()));
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
		wb.write(response.getOutputStream());
		String fileName = new String("学员活动统计.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
}
