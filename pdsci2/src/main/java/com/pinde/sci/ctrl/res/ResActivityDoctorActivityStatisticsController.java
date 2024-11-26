package com.pinde.sci.ctrl.res;


import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityBiz;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.enums.res.RegistryTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
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
@RequestMapping("/res/doctorActivityStatistics")
public class ResActivityDoctorActivityStatisticsController extends GeneralController {
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
	private SysOrgExtMapper orgExtMapper;

	@RequestMapping(value="/main")
	public String main(Model model,String role){
		SysUser currentUser=GlobalContext.getCurrentUser();
		SysOrg currentOrg=orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<SysOrg> orgs = new ArrayList<>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(role)){
			//查询所有医院
			SysOrg org = new SysOrg();
			org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
			orgs = orgBiz.searchOrg(org);
			model.addAttribute("orgs",orgs);
		}else if(GlobalConstant.USER_LIST_LOCAL.equals(role)){

		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(role)){
			//查询高校下所有医院
			String workOrgId = currentOrg.getSendSchoolId();
			orgs = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			model.addAttribute("orgs",orgs);
		}
		model.addAttribute("currentOrg", currentOrg);
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag",role);
		return "res/activity/doctorStatistics/main";
	}
	@RequestMapping(value = {"/list" })
	public String list (String trainingTypeId,String trainingSpeId,String doctorCategoryId,String sessionNumber,String orgFlow,String roleFlag,
						   String userName,String idNo,String graduationYear,String[] datas,String startTime,String endTime,
						   Integer currentPage,HttpServletRequest request,Model model){
		SysUser currentUser=GlobalContext.getCurrentUser();
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){

		}else if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			orgFlow=currentUser.getOrgFlow();
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			SysOrg currentOrg=orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			parMp.put("workOrgId",workOrgId);
		}
		parMp.put("docTypeList",docTypeList);
		parMp.put("orgFlow",orgFlow);
		parMp.put("typeId", trainingTypeId);
		parMp.put("speId", trainingSpeId);
		parMp.put("sessionNumber", sessionNumber);
		parMp.put("doctorCategoryId", doctorCategoryId);
		parMp.put("userName", userName);
		parMp.put("idNo", idNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("startTime",startTime);
		parMp.put("endTime",endTime);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list = activityBiz.getResDoctorActivityStatistics(parMp);
		Map<String,Object> map=new HashMap<>();
		if(list!=null&&list.size()>0)
		{
			for(Map<String,Object> u:list)
			{
				map.put((String)u.get("doctorFlow"),activityBiz.getDoctorActivityStatisticsMap((String)u.get("doctorFlow"),startTime,endTime, null, null));
			}
		}
		model.addAttribute("map",map);
		model.addAttribute("list", list);
		return "res/activity/doctorStatistics/list";
	}
	@RequestMapping(value="/exportList")
	public void exportList(String trainingTypeId,String trainingSpeId,String sessionNumber,String orgFlow,String roleFlag,
						   String userName,String idNo,String graduationYear,String[] datas,String startTime,String endTime, HttpServletRequest request, HttpServletResponse response) throws Exception {
		SysUser currentUser=GlobalContext.getCurrentUser();
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
			if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_"+regType.getId()))){
				recTypeIds.add(regType.getId());
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)){

		}else if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)){
			orgFlow=currentUser.getOrgFlow();
		}else if(GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)){
			SysOrg currentOrg=orgBiz.readSysOrg(currentUser.getOrgFlow());
			String workOrgId = currentOrg.getSendSchoolId();
			parMp.put("workOrgId",workOrgId);
		}
		parMp.put("orgFlow",orgFlow);
		parMp.put("docTypeList",docTypeList);
		parMp.put("typeId", trainingTypeId);
		parMp.put("speId", trainingSpeId);
		parMp.put("sessionNumber", sessionNumber);
		parMp.put("userName", userName);
		parMp.put("idNo", idNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("recTypeIds",recTypeIds);
		parMp.put("startTime",startTime);
		parMp.put("endTime",endTime);
		List<Map<String,Object>> list = activityBiz.getResDoctorActivityStatistics(parMp);

		List<SysDict> dictList= DictTypeEnum.sysListDictMap.get(DictTypeEnum.ActivityType.getId());
		if(dictList==null)
		{
			dictList=new ArrayList<>();
		}
		String []titles = new String[dictList.size()+5];
		titles[0]="姓名";
		titles[1]="基地";
		titles[2]="培训类型";
		titles[3]="专业";
		titles[4]="年级";
		for(int i=5;i<dictList.size()+5;i++)
		{
			titles[i]=dictList.get(i-5).getDictName();
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
					String doctorFlow= (String) u.get("doctorFlow");
					Map<String,Object>map=activityBiz.getDoctorActivityStatisticsMap(doctorFlow,startTime,endTime, null, null);
					int j=0;
					String doctorName= u.get("doctorName")==null? "": (String)u.get("doctorName");
					String orgName= u.get("orgName")==null? "": (String)u.get("orgName");
					String doctorCategoryName= u.get("doctorCategoryName")==null? "": (String)u.get("doctorCategoryName");
					String trainingSpeName= u.get("trainingSpeName")==null? "": (String)u.get("trainingSpeName");
					String sessionNumber2= u.get("sessionNumber")==null? "": (String)u.get("sessionNumber");
					row.createCell(j).setCellValue(doctorName);
					ExcleUtile.setColumnWidth(doctorName.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(orgName);
					ExcleUtile.setColumnWidth(orgName.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(doctorCategoryName);
					ExcleUtile.setColumnWidth(doctorCategoryName.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(trainingSpeName);
					ExcleUtile.setColumnWidth(trainingSpeName.getBytes().length, j++, columnWidth);
					row.createCell(j).setCellValue(sessionNumber2);
					ExcleUtile.setColumnWidth(sessionNumber2.getBytes().length, j++, columnWidth);
					if(map!=null) {
						for(SysDict dict:dictList)
						{
							String result= String.valueOf(map.get(doctorFlow+ dict.getDictId()));
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
		String fileName = new String("学员活动统计.xls".getBytes(), "ISO-8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
}
