package com.pinde.sci.ctrl.jsres;


import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.jsres.IResTestConfigBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.IResOrgSpeAssignBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.ResScore;
import com.pinde.sci.model.mo.ResTestConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/jsres/certificateManage")
public class JsResCertificateManageController extends GeneralController {
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IResDoctorRecruitBiz doctorRecruitBiz;
	@Autowired
	private IResTestConfigBiz resTestConfigBiz;
	@Autowired
	private IResOrgSpeAssignBiz speAssignBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;

	@RequestMapping("/certificateMain")
	public String certificateMain(Model model, String roleFlag,String tabTag) {
		return "jsres/global/certificateManage/certificateMain";
	}

	@RequestMapping("/mainNew")
	public String mainNew(Model model, String roleFlag,String tabTag) {
		return "jsres/hospital/signManage/mainNew";
	}

	/**
	 * 显示Main.jsp
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String main(Model model, String roleFlag) {
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
		SysOrg sysorg = new SysOrg();
        sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
		{
			sysorg.setOrgCityId(org.getOrgCityId());
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))
		{
			List<SysOrg> orgs=new ArrayList<>();
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
				List<SysOrg> orgs2 = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				if(null!=orgs&&orgs2.size()>0)
				{
					orgs.addAll(orgs2);
				}
			}
			model.addAttribute("orgs", orgs);
		}else{

			List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
			model.addAttribute("orgs", orgs);
		}
		List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
		model.addAttribute("resTestConfigs", resTestConfigs);
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("currOrg", org);
		model.addAttribute("user",GlobalContext.getCurrentUser());
		model.addAttribute("year", DateUtil.getYear());
		return "/jsres/global/certificateManage/main";
	}

	@RequestMapping("/freeMain")
	public String freeMain(Model model, String roleFlag) {
		model.addAttribute("roleFlag", roleFlag);
		if("doctor".equals(roleFlag)){
			return "/jsres/global/certificateManage/docMain";
		}else {
			return "/jsres/global/certificateManage/freeMain";
		}
	}
	public List<String> searchJointOrgList(String flag,String Flow) {
		List<String> jointOrgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(Flow);
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}
		return jointOrgFlowList;
	}
	@RequestMapping(value="/list")
	public String list(Model model, Integer currentPage, String roleFlag,HttpServletRequest request,
					   String orgCityId,String orgFlow,String trainingTypeId,String orgLevel,
					   String trainingSpeId,String sessionNumber,String graduationYear,String idNo,
					   String userName,String graduationCertificateNo, String[] datas,String jointOrgFlag,
					   String workOrgName,String qualifiedId,String certificateIssuingStatus,String tabTag){
		Map<String,Object> param=new HashMap<>();

		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("orgProvId",org.getOrgProvId());

		List<String> orgFlowList=new ArrayList<String>();

        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))//基地
		{
			if (StringUtil.isNotBlank(orgFlow)) {
				orgFlowList.add(orgFlow);
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}else{
				orgFlowList.add(org.getOrgFlow());
                if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}else {
			if (StringUtil.isNotBlank(orgFlow)) {
				orgFlowList.add(orgFlow);
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			} else {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					SysOrg searchOrg = new SysOrg();
					searchOrg.setOrgProvId(org.getOrgProvId());
                    searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
					if (StringUtil.isNotBlank(orgLevel)) {
						searchOrg.setOrgLevelId(orgLevel);
					}
					List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
					for (SysOrg g : exitOrgs) {
						List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
							for (ResJointOrg jointOrg : resJointOrgList) {
								orgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						orgFlowList.add(g.getOrgFlow());
					}
				}
			}
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				param.put("orgLevel",orgLevel);
			}
			param.put("orgCityId",orgCityId);
		}
		param.put("docTypeList",docTypeList);
		param.put("trainingTypeId",trainingTypeId);
		param.put("orgFlowList",orgFlowList);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("idNo",idNo);
		param.put("userName",userName);
		param.put("graduationCertificateNo",graduationCertificateNo);
		param.put("workOrgName",workOrgName);
		//合格编号
		param.put("qualifiedId", qualifiedId);
		//证书发放状态
		param.put("certificateIssuingStatus", certificateIssuingStatus);
		param.put("tabTag", tabTag);

		List<JsResDoctorRecruitExt> doctorList=null;
		if(StringUtil.isNotBlank(workOrgName)){
            List<SysDict> sendSchools = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
			if(sendSchools!=null && sendSchools.size()>0){
				for(SysDict dict :sendSchools){
					if(workOrgName.equals(dict.getDictName())){
						param.put("workOrgId",dict.getDictId());
					}
				}
			}
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		doctorList = jsResDoctorRecruitBiz.searchDoctorCertificateList2(param);
		Map<String, String> testIdMap = new HashMap<>();
		for (JsResDoctorRecruitExt jsResDoctorRecruitExt : doctorList) {
			List<ResScore> resScoreList = doctorRecruitBiz.selectAllScore(null, jsResDoctorRecruitExt.getRecruitFlow());
			//过滤所有的成绩获取出技能成绩合格的集合
			List<ResScore> skillScoreList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) &&
					"1".equals((String.valueOf(resScore.getSkillScore())))).collect(Collectors.toList());
			//过滤所有的成绩获取出理论成绩合格的集合
			List<ResScore> theoryScoreList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) &&
					"1".equals(String.valueOf(resScore.getTheoryScore()))).collect(Collectors.toList());
			if (skillScoreList.size() > 0) {
				testIdMap.put("SkillScore" + jsResDoctorRecruitExt.getRecruitFlow(), skillScoreList.get(0).getTestId());
			}
			if (theoryScoreList.size() > 0) {
				testIdMap.put("TheoryScore" + jsResDoctorRecruitExt.getRecruitFlow(), theoryScoreList.get(0).getTestId());
			}
		}
		model.addAttribute("testIdMap", testIdMap);
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);
		return "jsres/global/certificateManage/list";
	}
	@RequestMapping(value="/freeList")
	public String freeList(Model model, String roleFlag,HttpServletRequest request,String idNo,
					   String userName) {
		Map<String, Object> param = new HashMap<>();
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("orgProvId", org.getOrgProvId());

		List<String> orgFlowList = new ArrayList<String>();
		SysOrg searchOrg = new SysOrg();
		searchOrg.setOrgProvId(org.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			searchOrg.setOrgCityId(org.getOrgCityId());
			param.put("orgCityId", org.getOrgCityId());
			List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
			for (SysOrg g : exitOrgs) {
				List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
				if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
					for (ResJointOrg jointOrg : resJointOrgList) {
						orgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
				orgFlowList.add(g.getOrgFlow());
			}
		}else{
			orgFlowList.add(sysuser.getOrgFlow());
		}
		param.put("notLikeIdNo", idNo);
		param.put("notLikeUserName", userName);
		if("doctor".equals(roleFlag))
		{
			param.put("doctorFlow", sysuser.getUserFlow());
			orgFlowList.clear();
		}
		param.put("orgFlowList", orgFlowList);
		List<JsResDoctorRecruitExt> doctorList =  jsResDoctorRecruitBiz.searchDoctorCertificateList(param);
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("doctorList", doctorList);
		if(doctorList!=null&&doctorList.size()>0)
		{
			if(doctorList.size()>1) {
				return "/jsres/global/certificateManage/certificateList";
			}else{
				model.addAttribute("recruitFlow",doctorList.get(0).getRecruitFlow());
				model.addAttribute("noDown","noDown");
				return "redirect:/jsres/certificateManage/showCertificate";
			}
		}else{
			return "/jsres/global/certificateManage/notHave";
		}
	}

	/**
	 * 导出excel
	 * @param
	 * @return
	 */
	@RequestMapping(value="/exportCertificateList")
	public void exportDoctor(Model model,  String roleFlag,HttpServletRequest request,HttpServletResponse response,
							 String orgCityId,String orgFlow,String trainingTypeId,String orgLevel,
							 String trainingSpeId,String sessionNumber,String graduationYear,String idNo,
							 String userName,String graduationCertificateNo, String[] datas,String jointOrgFlag,
							 String workOrgName,String qualifiedId,String certificateIssuingStatus,String tabTag)throws Exception{
		Map<String,Object> param=new HashMap<>();

		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("orgProvId",org.getOrgProvId());

		List<String> orgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag))//基地
		{
			if (StringUtil.isNotBlank(orgFlow)) {
				orgFlowList.add(orgFlow);
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}else{
				orgFlowList.add(org.getOrgFlow());
                if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(org.getOrgFlow());
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			}
		}else {
			if (StringUtil.isNotBlank(orgFlow)) {
				orgFlowList.add(orgFlow);
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
					if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
						for (ResJointOrg jointOrg : resJointOrgList) {
							orgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
			} else {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
					SysOrg searchOrg = new SysOrg();
					searchOrg.setOrgProvId(org.getOrgProvId());
                    searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
					if (StringUtil.isNotBlank(orgLevel)) {
						searchOrg.setOrgLevelId(orgLevel);
					}
					List<SysOrg> exitOrgs = orgBiz.searchOrg(searchOrg);
					for (SysOrg g : exitOrgs) {
						List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
						if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
							for (ResJointOrg jointOrg : resJointOrgList) {
								orgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
						orgFlowList.add(g.getOrgFlow());
					}
				}
			}
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				param.put("orgLevel", orgLevel);
			}
			param.put("orgCityId",orgCityId);
		}
		param.put("docTypeList",docTypeList);
		param.put("trainingTypeId",trainingTypeId);
		param.put("orgFlowList",orgFlowList);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("idNo",idNo);
		param.put("userName",userName);
		param.put("graduationCertificateNo",graduationCertificateNo);
		param.put("workOrgName",workOrgName);
		//合格编号
		param.put("qualifiedId", qualifiedId);
		//证书发放状态
		param.put("certificateIssuingStatus", certificateIssuingStatus);
		param.put("tabTag",tabTag);

		List<JsResDoctorRecruitExt> doctorList=null;
		if(StringUtil.isNotBlank(workOrgName)){
            List<SysDict> sendSchools = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
			if(sendSchools!=null && sendSchools.size()>0){
				for(SysDict dict :sendSchools){
					if(workOrgName.equals(dict.getDictName())){
						param.put("workOrgId",dict.getDictId());
					}
				}
			}
		}
//		doctorList = jsResDoctorRecruitBiz.searchDoctorCertificateList(param);
		doctorList = jsResDoctorRecruitBiz.searchDoctorCertificateList2(param);
		Map<String, String> testIdMap = new HashMap<>();
		for (JsResDoctorRecruitExt jsResDoctorRecruitExt : doctorList) {
			List<ResScore> resScoreList = doctorRecruitBiz.selectAllScore(null, jsResDoctorRecruitExt.getRecruitFlow());
			//过滤所有的成绩获取出技能成绩合格的集合
			List<ResScore> skillScoreList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) &&
					"1".equals((String.valueOf(resScore.getSkillScore())))).collect(Collectors.toList());
			//过滤所有的成绩获取出理论成绩合格的集合
			List<ResScore> theoryScoreList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) &&
					"1".equals(String.valueOf(resScore.getTheoryScore()))).collect(Collectors.toList());
			if (skillScoreList.size() > 0) {
				jsResDoctorRecruitExt.setSkillTestId(skillScoreList.get(0).getTestId());
			}
			if (theoryScoreList.size() > 0) {
				jsResDoctorRecruitExt.setTheoryTestId(theoryScoreList.get(0).getTestId());
			}
		}
		 String[] head=new String[]{};
		String[] titles = new String[]{
				"graduationYear:结业年份",
				"graduationYear:证书发放状态",
				"graduationCertificateNo:证书编号",
				"sysUser.userName:姓名",
				"sysUser.idNo:证件号码",
				"localReason:基地审核意见",
				"cityReason:市局审核意见",
				"globalReason:省厅审核意见",
				"theoryTestId:理论考试编号",
				"skillTestId:技能考试编号",
				"qualifiedId:合格批次",
				"sessionNumber:年级",
				"orgCityName:地市",
				"catSpeName:培训类别",
				"orgName:培训基地",
				"speName:培训专业",

		};
		String fileName = "学员证书信息.xls";
		fileName = new String(fileName.getBytes("utf-8"),"ISO8859-1" );
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		//ExcleUtile.exportSimpleExcleWithHeadlin(head, titles, doctorList, response.getOutputStream());
		OutputStream os=response.getOutputStream();
		Workbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		Sheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
//		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		Font font =wb.createFont();
		font.setFontHeightInPoints((short)12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);
		Font fontTwo =wb.createFont();
		fontTwo.setFontHeightInPoints((short)12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//表头
		Row rowNew = sheet.createRow(0);
		Cell cell1 = rowNew.createCell(0);
		cell1.setCellValue("住院医师规范化管理平台学员结业证书清单");
		cell1.setCellStyle(styleTwo);
		sheet.addMergedRegion(new org.apache.poi.ss.util.CellRangeAddress(0, (short) 0, 0, (short) 15));

		Map<Integer,Integer>  columnWidth=new HashMap<>();
		List<String> paramIds = new ArrayList<String>();
		Row row = sheet.createRow(1);
		Cell cell = null;
		for(int i = 0 ; i<titles.length ; i++){
			String[] title = titles[i].split(":");
			cell = row.createCell(i);
			cell.setCellValue(title[1]);
			cell.setCellStyle(style);
			 paramIds.add(title[0]);
			//宽度自适应
			int nl=title[1].toString().getBytes().length;
			if(columnWidth.containsKey(i)) {
				Integer ol = columnWidth.get(i);
				if(ol<nl)
					columnWidth.put(i,nl);
			}else{
				columnWidth.put(i,nl);
			}
		}
		if(doctorList!=null){
			Cell rowCell = null;
			for(int i=0; i<doctorList.size() ; i++){
				Object item = doctorList.get(i);
				row = sheet.createRow( 2 + i);
				Object result = null;
				for(int j = 0 ; j <paramIds.size();j++){
					String paramId = paramIds.get(j);
					if(StringUtil.isBlank(paramId)){//序号
						result = i+1;
					}else{
						result = getValueByAttrs(paramId,item);
					}
					rowCell=row.createCell(j);
					rowCell.setCellStyle(styleTwo);
					rowCell.setCellValue(result.toString());
					//宽度自适应
					int nl=result.toString().getBytes().length;
					if(columnWidth.containsKey(j)) {
						Integer ol = columnWidth.get(j);
						if(ol<nl)
							columnWidth.put(j,nl);
					}else{
						columnWidth.put(j,nl);
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
	}

	private static Object getValueByAttrs(String attrNames,Object obj) throws Exception{
		Object value = "";
		if(StringUtil.isNotBlank(attrNames)){
			String proptyName = "";
			int pIndex = attrNames.indexOf(".");

			if(pIndex>=0){
				proptyName = attrNames.substring(0,pIndex);
			}else{
				proptyName = attrNames;
			}

			if(StringUtil.isNotBlank(proptyName) && obj!=null){
				Class clazz = obj.getClass();
				String firstStr = proptyName.substring(0, 1).toUpperCase();
				String secondStr = proptyName.substring(1);
				Method mt;
				Object result;
				if(proptyName.length() >= 4 && "get(".equals(proptyName.substring(0, 4))){
					int index = Integer.parseInt(proptyName.split("\\(")[1].split("\\)")[0]);
					result = null;
					if(((List) obj).size()>index){
						result = ((List) obj).get(index);
					}
				}else if(obj instanceof Map){
					Map map = (Map) obj;
					result=((Map) obj).get(proptyName);
				} else{
					mt = clazz.getMethod("get"+firstStr+secondStr);
					result = mt.invoke(obj);
				}

				if(result!=null){
					String stringClassName = String.class.getSimpleName();
					String inegerClassName = Integer.class.getSimpleName();
					String bigDecimalClassName = BigDecimal.class.getSimpleName();
					String valueClassName = result.getClass().getSimpleName();
					if(stringClassName.equals(valueClassName)||bigDecimalClassName.equals(valueClassName)||inegerClassName.equals(valueClassName)){
						value = result;
					}else{
						String surplusName = attrNames.substring(pIndex+1);
						value = getValueByAttrs(surplusName,result);
					}
				}
			}
		}
		return value;
	}

	@RequestMapping(value="/importDoctorCertificateNoFromExcel")
	@ResponseBody
	public String importDoctorCertificateNoFromExcel(MultipartFile file){

		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) jsResDoctorRecruitBiz.importDoctorCertificateNoFromExcel(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	@RequestMapping(value="/importDoctorCertificateNoFromExcel2")
	@ResponseBody
	public String importDoctorCertificateNoFromExcel2(MultipartFile file){

		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) jsResDoctorRecruitBiz.importDoctorCertificateNoFromExcel2(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                            return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
						}else{
                            return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
						}
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	@RequestMapping(value = "/showCertificate")
	public String showCertificate(String recruitFlow,Model model) {
		com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
		if(recruit!=null&&StringUtil.isNotBlank(recruit.getGraduationCertificateNo())) {
			SysUser sysUser = userBiz.findByFlow(recruit.getDoctorFlow());
			String completeNo = "";
			completeNo = getShowCountryOrProvince(recruit);
			List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(recruit.getOrgFlow());
			//是协同基地 显示国家基地
			if (!jointOrgs.isEmpty() && jointOrgs.size() > 0) {
				recruit.setOrgName(jointOrgs.get(0).getOrgName());
			}
			model.addAttribute("completeNo", completeNo);
			model.addAttribute("recruit", recruit);
			model.addAttribute("sysUser", sysUser);

			String endTime="";
			String startTime="";
			//开始时间
			String recruitDate=recruit.getRecruitDate();
			//培养年限
			String trianYear=recruit.getTrainYear();
			String graudationYear=recruit.getGraduationYear();
			if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(graudationYear))
			{
				try {
					int year=0;
					year=Integer.valueOf(graudationYear)-Integer.valueOf(recruitDate.substring(0,4));
					if(year!=0) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(recruitDate);
						startTime = recruitDate;
						//然后使用Calendar操作日期
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
						calendar.add(Calendar.DATE, -1);
						//把得到的日期格式化成字符串类型的时间
						endTime = sdf.format(calendar.getTime());
					}
				} catch (Exception e) {
					endTime="";
				}
			}
			//如果没有结业考核年份，按照届别与培养年限计算
			if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(trianYear)&&StringUtil.isBlank(endTime))
			{
				int year=0;
                if (trianYear.equals(com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId()))
				{
					year=1;
				}
                if (trianYear.equals(com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId()))
				{
					year=2;
				}
                if (trianYear.equals(com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId()))
				{
					year=3;
				}
				if(year!=0) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(recruitDate);
						startTime = recruitDate;
						//然后使用Calendar操作日期
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
						calendar.add(Calendar.DATE,-1);

						//把得到的日期格式化成字符串类型的时间
						endTime = sdf.format(calendar.getTime());
					} catch (Exception e) {

					}
				}
			}
			model.addAttribute("completeStartDate",startTime);
			model.addAttribute("completeEndDate",endTime);
		}else{
            model.addAttribute("notHave", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		return "jsres/global/showCertificate/info";
	}

	private String getShowCountryOrProvince(com.pinde.core.model.ResDoctorRecruit recruit) {
		String completeNo="";
		String sessionNumber=recruit.getSessionNumber();
		if(StringUtil.isBlank(sessionNumber))
		{
			return "";
		}
		//所有助理全科人员都只生成助理全科证书
        if (recruit.getCatSpeId().equals(com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId()))
		{
			completeNo="AssiGeneral";
		}else {
			int sessionYear = Integer.valueOf(recruit.getSessionNumber());
			//2013年以前全部用江苏省证书
			if (sessionYear <= 2013) {
				//江苏省生成规则待定
				completeNo = getProvinceOrgNo(recruit);
			} else if (sessionYear == 2014) {
				SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
				//只有国家基地使用国家证书
                if (org.getOrgLevelId().equals(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId())) {
					completeNo = "country";
				} else {
					//江苏省生成规则待定
					completeNo = getProvinceOrgNo(recruit);
				}
			} else {
				SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
				//国家基地使用国家证书
                if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
					completeNo = "country";
				} else {
					List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByJointOrgFlow(org.getOrgFlow());
					if (jointOrgList != null && jointOrgList.size() > 0) {
						ResJointOrg resJointOrg = jointOrgList.get(0);
						SysOrg org2 = orgBiz.readSysOrg(resJointOrg.getOrgFlow());
						//国家基地的协同基地也使用国家证书
                        if (org2.getOrgLevelId().equals(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId())) {
							completeNo = "country";
						} else {
							//江苏省生成规则待定
//							completeNo = getProvinceOrgNo(recruit);
							completeNo = "province";
						}
					} else {
						//江苏省生成规则待定
//						completeNo = getProvinceOrgNo(recruit);
						completeNo = "province";
					}
				}
			}
		}
		return completeNo;
	}

	public String getProvinceOrgNo(ResDoctorRecruit resDoctor)
	{
		String no="";
		if(resDoctor.getSpeId().equals("51")||resDoctor.getSpeId().equals("52")||resDoctor.getSpeId().equals("0700"))
		{
			no="provinceAll";
		}
		else{
			//非全科
			no="provinceNoAll";
		}
		return no;
	}

	/**
	 * 修改证书发放状态
	 * @param recruitFlow
	 * @param certificateIssuingStatusId
	 * @return
	 */
	@RequestMapping("/chargeStatus")
	@ResponseBody
	public String chargeStatus(String recruitFlow, String certificateIssuingStatusId) {
		if (StringUtil.isNotBlank(recruitFlow)) {
			ResDoctorRecruitWithBLOBs resDoctorRecruitWithBLOBs = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
			if (resDoctorRecruitWithBLOBs != null) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(certificateIssuingStatusId)) {
					resDoctorRecruitWithBLOBs.setCertificateIssuingStatus("已发放");
					//证书发放  学员结业
					resDoctorRecruitWithBLOBs.setDoctorStatusId("21");
					resDoctorRecruitWithBLOBs.setDoctorStatusName("结业");
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(certificateIssuingStatusId)) {
					resDoctorRecruitWithBLOBs.setCertificateIssuingStatus("未发放");
					resDoctorRecruitWithBLOBs.setDoctorStatusId("20");
					resDoctorRecruitWithBLOBs.setDoctorStatusName("在培");
				}
				int count = jsResDoctorRecruitBiz.saveDoctorRecruit(resDoctorRecruitWithBLOBs);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != count) {
                    return com.pinde.core.common.GlobalConstant.UPDATE_SUCCESSED;
				}
                return com.pinde.core.common.GlobalConstant.UPDATE_FAIL;
			}
            return com.pinde.core.common.GlobalConstant.UPDATE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.UPDATE_FAIL;
	}

	@RequestMapping("/createMain")
	public String createMain(Model model, String roleFlag) {
		SysUser sysuser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
		SysOrg sysorg = new SysOrg();
        sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
		model.addAttribute("orgs", orgs);

		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("user",GlobalContext.getCurrentUser());
		model.addAttribute("year", DateUtil.getYear());
		return "jsres/global/certificateManage/createMain";
	}

	@RequestMapping(value="/createList")
	public String createList(Model model, Integer currentPage, String roleFlag,HttpServletRequest request,
								  String orgCityId,String orgFlow,String trainingTypeId, String trainingSpeId,String tabTag,
								  String sessionNumber,String graduationYear,String idNo, String userName, String[] datas){
		Map<String,Object> param=new HashMap<>();

		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("orgProvId",org.getOrgProvId());

		List<String> orgFlowList=new ArrayList<String>();
		if (StringUtil.isNotBlank(orgFlow)) {
			orgFlowList.add(orgFlow);
			List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
			if (jointOrgs.isEmpty() || jointOrgs.size() == 0) {
				jointOrgs = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
				if(null != jointOrgs && jointOrgs.size()>0){
					for (ResJointOrg rj:jointOrgs) {
						orgFlowList.add(rj.getJointOrgFlow());
					}
				}
			}
		}
		param.put("orgCityId",orgCityId);
		param.put("docTypeList",docTypeList);
		param.put("trainingTypeId",trainingTypeId);
		param.put("orgFlowList",orgFlowList);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("idNo",idNo);
		param.put("userName",userName);
		param.put("tabTag",tabTag);
		List<JsResDoctorRecruitExt> doctorList=null;
		PageHelper.startPage(currentPage,getPageSize(request));
		doctorList = jsResDoctorRecruitBiz.searchNotCertificateList(param);
		Map<String, String> testIdMap = new HashMap<>();
		for (JsResDoctorRecruitExt jsResDoctorRecruitExt : doctorList) {
			List<ResScore> resScoreList = doctorRecruitBiz.selectAllScore(null, jsResDoctorRecruitExt.getRecruitFlow());
			//过滤所有的成绩获取出技能成绩合格的集合
			List<ResScore> skillScoreList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) &&
					"1".equals((String.valueOf(resScore.getSkillScore())))).collect(Collectors.toList());
			//过滤所有的成绩获取出理论成绩合格的集合
			List<ResScore> theoryScoreList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) &&
					"1".equals(String.valueOf(resScore.getTheoryScore()))).collect(Collectors.toList());
			if (skillScoreList.size() > 0) {
				testIdMap.put("SkillScore" + jsResDoctorRecruitExt.getRecruitFlow(), skillScoreList.get(0).getTestId());
			}
			if (theoryScoreList.size() > 0) {
				testIdMap.put("TheoryScore" + jsResDoctorRecruitExt.getRecruitFlow(), theoryScoreList.get(0).getTestId());
			}
		}
		model.addAttribute("testIdMap", testIdMap);
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		model.addAttribute("datas",datas);
		return "jsres/global/certificateManage/createList";
	}

	@RequestMapping(value = "/createCertificate")
	@ResponseBody
	public String createCertificate(String recruitFlow,String tabTag) {
		if (StringUtil.isBlank(recruitFlow)) {
			return "请选择需要生成证书的人员信息！";
		}
		com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        if (recruit == null || com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recruit.getRecordStatus())) {
			return "培训信息不存在，请刷新列表！";
		}
		if (StringUtil.isNotBlank(recruit.getGraduationCertificateNo())) {
			return "该人员已发证，不得重复生成，请刷新列表！";
		}
		int conut = jsResDoctorRecruitBiz.createCertificate(recruit,tabTag);
		if (conut == 0) {
			return "生成失败";
		}else if(conut == -1){
			return "无法生成";
		}
		return "生成成功";
	}

	/**
	 * @Author shengl
	 * @Description //批量发放证书
	 * @Date  2020-08-26
	 * @Param [recordFlowsStr]
	 * @return java.lang.String
	 **/
	@RequestMapping(value = "/certificateAll")
	@ResponseBody
	public String certificateAll(String recruitFlowStr,String tabTag) {
		int conut = 0;
		if (StringUtil.isNotBlank(recruitFlowStr)) {
			String[] recordFlows = recruitFlowStr.split(",");
			List<String> recruitFlowList = Arrays.asList(recordFlows);
			if (recruitFlowList != null && recruitFlowList.size() > 0) {
				List<com.pinde.core.model.ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitListByFlows(recruitFlowList);
				if (recruitList != null && recruitList.size() > 0) {
					for (ResDoctorRecruit rdr: recruitList) {
						if (rdr != null && StringUtil.isBlank(rdr.getGraduationCertificateNo())) {
							// 发放证书
							conut = jsResDoctorRecruitBiz.createCertificate(rdr,tabTag);
						}
					}
				}
			} else {
				return "未勾选数据";
			}
		}
		if (conut == 0) {
			return "生成失败";
		}else if(conut == -1){
			return "无法生成";
		}
		return "生成成功";
	}

	@RequestMapping(value = "/showCertificateNew")
	public String showCertificateNew(String recruitFlow,String tabTag,Model model) {
		com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
		if(recruit!=null&&StringUtil.isNotBlank(recruit.getGraduationCertificateNo())) {
			SysUser sysUser = userBiz.findByFlow(recruit.getDoctorFlow());
			String completeNo = "";
			completeNo = getShowCountryOrProvince(recruit);
			List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(recruit.getOrgFlow());
			//是协同基地 显示国家基地
			if (!jointOrgs.isEmpty() && jointOrgs.size() > 0) {
				recruit.setOrgFlow(jointOrgs.get(0).getOrgFlow());
				recruit.setOrgName(jointOrgs.get(0).getOrgName());
			}
			//根据年份查询基地院长签名图片
			if(StringUtil.isNotEmpty(recruit.getGraduationYear())){
                List<JsresSign> signList = speAssignBiz.searchSignListByOrgFlowNew(recruit.getOrgFlow(), com.pinde.core.common.GlobalConstant.FLAG_Y, recruit.getGraduationYear());
				if(CollectionUtils.isEmpty(signList)){
                    signList = speAssignBiz.searchSignListByOrgFlow(recruit.getOrgFlow(), com.pinde.core.common.GlobalConstant.FLAG_Y);
				}
				if(null != signList && signList.size() > 0){
					model.addAttribute("signUrl",signList.get(0).getSignUrl());
				}
			}else{
				//查询基地院长前面图片
                List<JsresSign> signList = speAssignBiz.searchSignListByOrgFlow(recruit.getOrgFlow(), com.pinde.core.common.GlobalConstant.FLAG_Y);
				if(null != signList && signList.size() > 0){
					model.addAttribute("signUrl",signList.get(0).getSignUrl());
				}
			}
			//查询省厅红章
			PubFile file = fileBiz.readProductFile("jsst","globalSeal");
			if(null != file){
				model.addAttribute("filePath",file.getFilePath());
			}

			model.addAttribute("completeNo", completeNo);
			model.addAttribute("recruit", recruit);
			model.addAttribute("sysUser", sysUser);

			String endTime="";
			String startTime="";
			//开始时间
			String recruitDate=recruit.getRecruitDate();
			//培养年限
			String trianYear=recruit.getTrainYear();
			String graudationYear=recruit.getGraduationYear();
			if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(graudationYear))
			{
				try {
					int year=0;
					year=Integer.valueOf(graudationYear)-Integer.valueOf(recruitDate.substring(0,4));
					if(year!=0) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(recruitDate);
						startTime = recruitDate;
						//然后使用Calendar操作日期
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
						calendar.add(Calendar.DATE, -1);
						//把得到的日期格式化成字符串类型的时间
						endTime = sdf.format(calendar.getTime());
					}
				} catch (Exception e) {
					endTime="";
				}
			}
			//如果没有结业考核年份，按照届别与培养年限计算
			if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(trianYear)&&StringUtil.isBlank(endTime))
			{
				int year=0;
                if (trianYear.equals(com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId()))
				{
					year=1;
				}
                if (trianYear.equals(com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId()))
				{
					year=2;
				}
                if (trianYear.equals(com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId()))
				{
					year=3;
				}
				if(year!=0) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(recruitDate);
						startTime = recruitDate;
						//然后使用Calendar操作日期
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
						calendar.add(Calendar.DATE,-1);

						//把得到的日期格式化成字符串类型的时间
						endTime = sdf.format(calendar.getTime());
					} catch (Exception e) {

					}
				}
			}
			model.addAttribute("completeStartDate",startTime);
			model.addAttribute("completeEndDate",endTime);
		}else{
            model.addAttribute("notHave", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		return "jsres/global/showCertificate/infoNew";
	}

	@RequestMapping(value = "/downCertificate")
	public void downCertificate( HttpServletRequest request, HttpServletResponse response,
								  String recruitFlow, Model model, String img,MultipartFile myfile, String isDown, String fileName) throws Exception {

		String baseDir=InitConfig.getSysCfg("upload_base_dir");
		//定义上传路径
		String dateString = DateUtil.getCurrDate2();
		String newDir = baseDir+ File.separator + "jsresCertificateImg";
		File fileDir = new File(newDir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		String originalFilename =recruitFlow+ ".png";
		String filePath=fileDir+  File.separator +originalFilename;

        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isDown)) {
			byte[] data = null;
			long dataLength = 0;
			/*文件是否存在*/
			File downLoadFile = new File(filePath);
			/*文件是否存在*/
			if (downLoadFile.exists()) {
				InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
				data = new byte[fis.available()];
				dataLength = downLoadFile.length();
				fis.read(data);
				fis.close();
			}
			fileName = new String(fileName.getBytes("gbk"),"ISO8859-1" );
			try {
				response.reset();
				response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
				response.addHeader("Content-Length", "" + dataLength);
				response.setContentType("application/octet-stream;charset=UTF-8");
				OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
				if (data != null) {
					outputStream.write(data);
				}
				outputStream.flush();
				outputStream.close();
			} catch (IOException e) {

			}

		}else{
			File newFile = new File(fileDir, originalFilename);
			myfile.transferTo(newFile);
		}
	}

	@RequestMapping(value="/sealManage")
	public String sealManage(Model model){
		String userFlow = "jsst";
		PubFile file = fileBiz.readProductFile(userFlow,"globalSeal");
		model.addAttribute("file",file);
		model.addAttribute("userFlow",userFlow);
		return "jsres/global/showCertificate/globalSeal";
	}

	@RequestMapping(value={"/uploadSealFile"})
	@ResponseBody
	public String uploadApplicationFile(MultipartFile file, String productFlow) throws Exception{
		if(file!=null && !file.isEmpty()){
			String checkResult = jsResDoctorBiz.checkImg(file);
			String resultPath = "";
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkResult)) {
				return checkResult;
			}else{
				resultPath = jsResDoctorBiz.saveFileToDirs("", file, "jsresSeal/globalSeal");
				List<PubFile> files = fileBiz.findFileByTypeFlow("globalSeal",productFlow);
				PubFile pubFile = null;
				if(files != null && files.size() > 0){
					pubFile = files.get(0);
				}else {
					pubFile = new PubFile();
				}
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				pubFile.setProductFlow(productFlow);
				pubFile.setFilePath(resultPath);
				pubFile.setFileName(file.getOriginalFilename());
				pubFile.setProductType("globalSeal");
				fileBiz.editFile(pubFile);
				return resultPath;
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

    private static Logger logger = LoggerFactory.getLogger(JsResCertificateManageController.class);

}
