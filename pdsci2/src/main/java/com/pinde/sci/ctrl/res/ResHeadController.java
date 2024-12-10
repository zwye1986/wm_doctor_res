
package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.ResAssessTypeEnum;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.SchProcessExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/res/head")
public class ResHeadController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResHeadController.class);

	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private IResTrainingSpeDeptBiz trainingSpeDeptBiz;
	@Autowired
	private SysOrgExtMapper sysOrgExtMapper;
	/**
	 * 基地双向评分
	 * @param deptFlow
	 * @param userName
	 * @param operStartDate
	 * @param operEndDate
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/gradeSearch")
		public String gradeSearch(String role, String gradeRole, String deptFlow, String userName, String deptName,String orgFlow,
								  String operStartDate, String operEndDate, String sessionNumber, Integer currentPage, HttpServletRequest request, Model model){
		String workOrgId = "";
		List<SysOrg> orgList=new ArrayList<>();
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(role)) {
			SysOrg org = new SysOrg();
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgList = orgBiz.searchOrg(org);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
			String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			if(StringUtil.isNotBlank(currentOrgFlow)){
				SysOrg org = orgBiz.readSysOrg(currentOrgFlow);
				workOrgId = org.getSendSchoolId();
				orgList = sysOrgExtMapper.searchOrgs4hbUniversity(workOrgId);
			}
		}else{
			//医院管理员增加查协同基地学员轮转情况
			SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			orgList.add(sysOrg);
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for (SysOrg so:jointOrgList) {
					orgList.add(so);
				}
			}
		}
		model.addAttribute("orgList",orgList);
		if(StringUtil.isNotBlank(operStartDate)){
			operStartDate = DateUtil.getDate(operStartDate)+"000000";
		}
		if(StringUtil.isNotBlank(operEndDate)){
			operEndDate = DateUtil.getDate(operEndDate)+"235959";
		}

		//当前用户所在机构
		if(StringUtil.isBlank(orgFlow)){
            if ((com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(role) || com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) && orgList.size() > 0) {
				orgFlow = orgList.get(0).getOrgFlow();
			}else{
				orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			}
		}
		//专业基地管理员专业ID
		String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("operStartDate",operStartDate);
		paramMap.put("operEndDate",operEndDate);
		paramMap.put("userName",userName);
		paramMap.put("deptFlow",deptFlow);
		paramMap.put("deptName",deptName);
		paramMap.put("workOrgId",workOrgId);

		model.addAttribute("role",role);
		if("professionalBase".equals(role)){
			ResTrainingSpeDept search = new ResTrainingSpeDept();//查出当前专业基地的所有科室
			search.setOrgFlow(orgFlow);
			search.setTrainingSpeId(trainingSpeId);
			List<ResTrainingSpeDept> trainingSpeDeptList = trainingSpeDeptBiz.search(search);
			model.addAttribute("depts",trainingSpeDeptList);

			List<String> deptFlows = new ArrayList<>();
			if(trainingSpeDeptList!=null&&trainingSpeDeptList.size()>0){
				for(ResTrainingSpeDept resTrainingSpeDept:trainingSpeDeptList){
					String deptFLow = resTrainingSpeDept.getDeptFlow();
					deptFlows.add(deptFLow);
				}
			}
			paramMap.put("deptFlows",deptFlows);
		}else{
			List<SysDept> 	deptList =	deptBiz.searchDeptByOrg(orgFlow);//查出当前机构的所有科室
			model.addAttribute("depts",deptList);
		}

		List<String> keys = new ArrayList<String>();
		Object waitSort = null;
		Map<String, Object> scoreMap=new HashMap<String, Object>();
		if("teacher".equals(gradeRole)){
			//带教flow
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			paramMap.put("roleFlow",teacherRoleFlow);

            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList",assessCfgList);
			//查出当前机构的所有带教老师
			PageHelper.startPage(currentPage,getPageSize(request));
			List<SysUser> userList = resGradeBiz.getUserByRec(paramMap);
			model.addAttribute("datas",userList);
			waitSort = userList;
			if(userList!=null && !userList.isEmpty()){
				for(SysUser su : userList){
					keys.add(su.getUserFlow());
					int score=0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if (resAssessCfgTitleForm.getItemList()!=null && resAssessCfgTitleForm.getItemList().size()>0) {
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s=Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
								score+=s;
							}
						}
					}
					scoreMap.put(su.getUserFlow(), score);
				}
			}
			paramMap.put("teacherFlows",keys);
		}else if("head".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList",assessCfgList);
			PageHelper.startPage(currentPage,getPageSize(request));
			List<SysDept> depts = resGradeBiz.getDeptByRec(paramMap);
			model.addAttribute("datas",depts);
			waitSort = depts;
			if(depts!=null && !depts.isEmpty()){
				for(SysDept sd : depts){
					keys.add(sd.getDeptFlow());
					int score=0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if(null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size()>0){
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s=Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
								score+=s;
							}
						}
					}
					scoreMap.put(sd.getDeptFlow(), score);
				}
			}
			paramMap.put("deptFlows",keys);
		}

		//获取评分数据
		List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess(paramMap);

		if (StringUtil.isNotBlank(sessionNumber)) {
			paramMap.put("sessionNumber", sessionNumber);
			List<Map<String, String>> recDateList = resGradeBiz.getRecContentByProcess(paramMap);
			if(recDateList!=null && !recDateList.isEmpty()){
				Map<String,Float> recDateAvgMap=avg(recDateList);
				model.addAttribute("recDateAvgMap", recDateAvgMap);
			}
		}else{
			String date=DateUtil.getCurrDateTime("yyyy");
			model.addAttribute("currDate", date);
			paramMap.put("sessionNumber", date);
			List<Map<String, String>> recCurrDateList = resGradeBiz.getRecContentByProcess(paramMap);
			if(recCurrDateList!=null && !recCurrDateList.isEmpty()){
				Map<String,Float> recCurrDateAvgMap=avg(recCurrDateList);
				model.addAttribute("recCurrDateAvgMap", recCurrDateAvgMap);
			}
			int d=Integer.valueOf(date);
			d=d-1;
			date=String.valueOf(d);
			model.addAttribute("PreviouYearDate", date);
			paramMap.put("sessionNumber", date);
			List<Map<String, String>> recpreviouYearList = resGradeBiz.getRecContentByProcess(paramMap);
			if(recpreviouYearList!=null && !recpreviouYearList.isEmpty()){
				Map<String,Float> recpreviouYearAvgMap=avg(recpreviouYearList);
				model.addAttribute("recpreviouYearAvgMap", recpreviouYearAvgMap);
			}
			int s=Integer.valueOf(date);
			s=s-1;
			date=String.valueOf(s);
			model.addAttribute("FirstTwoYearsDate", date);
			paramMap.put("sessionNumber", date);
			List<Map<String, String>> recFirstTwoYearsList = resGradeBiz.getRecContentByProcess(paramMap);
			if(recFirstTwoYearsList!=null && !recFirstTwoYearsList.isEmpty()){
				Map<String,Float> recFirstTwoYearsAvgMap=avg(recFirstTwoYearsList);
				model.addAttribute("recFirstTwoYearsAvgMap", recFirstTwoYearsAvgMap);
			}
		}

		if(recList!=null && !recList.isEmpty()){
			Map<String,Float> avgMap=avg(recList);
			if(waitSort!=null){
				final Map<String,Float> scoreMapTemp = avgMap;
				if("teacher".equals(gradeRole)){
					List<SysUser> userList = (List<SysUser>) waitSort;
					Collections.sort(userList,new Comparator<SysUser>() {
						@Override
						public int compare(SysUser u1,SysUser u2) {
							String k1 = u1.getUserFlow();
							String k2 = u2.getUserFlow();
							Float s1 = scoreMapTemp.get(k1+"_Total");
							Float s2 = scoreMapTemp.get(k2+"_Total");
							if(s1==null){
								s1=0f;
							}
							if(s2==null){
								s2=0f;
							}
							Float result = s2-s1;

							return result>0?1:result==0?0:-1;
						}

					});
				}else if("head".equals(gradeRole)){
					List<SysDept> depts = (List<SysDept>) waitSort;
					Collections.sort(depts,new Comparator<SysDept>() {
						@Override
						public int compare(SysDept d1,SysDept d2) {
							String k1 = d1.getDeptFlow();
							String k2 = d2.getDeptFlow();
							Float s1 = scoreMapTemp.get(k1+"_Total");
							Float s2 = scoreMapTemp.get(k2+"_Total");
							if(s1==null){
								s1=0f;
							}
							if(s2==null){
								s2=0f;
							}
							Float result = s2-s1;

							return result>0?1:result==0?0:-1;
						}

					});
				}
			}
			model.addAttribute("avgMap", avgMap);
		}

		model.addAttribute("scoreMap", scoreMap);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(role)) {
			return "res/teacher/gradeSearch4Global";
		}
		return "res/teacher/gradeSearch";
	}


	/**
	 * 导出双向评价
	 * @param response
	 * @param role
	 * @param gradeRole
	 * @param deptFlow
	 * @param userName
	 * @param deptName
	 * @param operStartDate
	 * @param operEndDate
	 * @param sessionNumber
     * @throws Exception
     */
	@RequestMapping(value = "/exportEval")
	public void exportEval(HttpServletResponse response, String role, String gradeRole, String deptFlow, String userName, String deptName,
						   String orgFlow, String operStartDate, String operEndDate, String sessionNumber) throws Exception {
		if (StringUtil.isNotBlank(operStartDate)) {
			operStartDate = DateUtil.getDate(operStartDate) + "000000";
		}
		if (StringUtil.isNotBlank(operEndDate)) {
			operEndDate = DateUtil.getDate(operEndDate) + "235959";
		}

		if(StringUtil.isBlank(orgFlow)){
			//当前用户所在机构
			orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		}
		//专业基地管理员专业ID
		String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow", orgFlow);
		paramMap.put("operStartDate", operStartDate);
		paramMap.put("operEndDate", operEndDate);
		paramMap.put("userName", userName);
		paramMap.put("deptFlow", deptFlow);
		paramMap.put("deptName", deptName);

		if ("professionalBase".equals(role)) {
			ResTrainingSpeDept search = new ResTrainingSpeDept();//查出当前专业基地的所有科室
			search.setOrgFlow(orgFlow);
			search.setTrainingSpeId(trainingSpeId);
			List<ResTrainingSpeDept> trainingSpeDeptList = trainingSpeDeptBiz.search(search);

			List<String> deptFlows = new ArrayList<>();
			if (trainingSpeDeptList != null && trainingSpeDeptList.size() > 0) {
				for (ResTrainingSpeDept resTrainingSpeDept : trainingSpeDeptList) {
					String deptFLow = resTrainingSpeDept.getDeptFlow();
					deptFlows.add(deptFLow);
				}
			}
			paramMap.put("deptFlows", deptFlows);
		}

		List<String> keys = new ArrayList<String>();
		Object waitSort = null;
		Map<String, Object> scoreMap = new HashMap<String, Object>();
		if ("teacher".equals(gradeRole)) {
			//带教flow
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			paramMap.put("roleFlow", teacherRoleFlow);

            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			//查出当前机构的所有带教老师
			List<SysUser> userList = resGradeBiz.getUserByRec(paramMap);
			waitSort = userList;
			if (userList != null && !userList.isEmpty()) {
				for (SysUser su : userList) {
					keys.add(su.getUserFlow());
					int score = 0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if (resAssessCfgTitleForm.getItemList() != null && resAssessCfgTitleForm.getItemList().size() > 0) {
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s = Integer.parseInt("".equals(resAssessCfgItemForm.getScore()) ? "0" : resAssessCfgItemForm.getScore());
								score += s;
							}
						}
					}
					scoreMap.put(su.getUserFlow(), score);
				}
			}
			paramMap.put("teacherFlows", keys);
		} else if ("head".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId", recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			List<SysDept> depts = resGradeBiz.getDeptByRec(paramMap);
			waitSort = depts;
			if (depts != null && !depts.isEmpty()) {
				for (SysDept sd : depts) {
					keys.add(sd.getDeptFlow());
					int score = 0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s = Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
								score += s;
							}
						}
					}
					scoreMap.put(sd.getDeptFlow(), score);
				}
			}
			paramMap.put("deptFlows", keys);
		}

		//获取评分数据
		List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess(paramMap);

		Map<String, Float> recDateAvgMap = new HashMap<>();
		Map<String, Float> recCurrDateAvgMap = new HashMap<>();
		Map<String, Float> recpreviouYearAvgMap = new HashMap<>();
		Map<String, Float> recFirstTwoYearsAvgMap = new HashMap<>();
		if (StringUtil.isNotBlank(sessionNumber)) {
			paramMap.put("sessionNumber", sessionNumber);
			List<Map<String, String>> recDateList = resGradeBiz.getRecContentByProcess(paramMap);
			if (recDateList != null && !recDateList.isEmpty()) {
				recDateAvgMap = avg(recDateList);
			}
		} else {
			String date = DateUtil.getCurrDateTime("yyyy");
			paramMap.put("sessionNumber", date);
			List<Map<String, String>> recCurrDateList = resGradeBiz.getRecContentByProcess(paramMap);
			if (recCurrDateList != null && !recCurrDateList.isEmpty()) {
				recCurrDateAvgMap = avg(recCurrDateList);
			}
			int d = Integer.valueOf(date);
			d = d - 1;
			date = String.valueOf(d);
			paramMap.put("sessionNumber", date);
			List<Map<String, String>> recpreviouYearList = resGradeBiz.getRecContentByProcess(paramMap);
			if (recpreviouYearList != null && !recpreviouYearList.isEmpty()) {
				recpreviouYearAvgMap = avg(recpreviouYearList);
			}
			int s = Integer.valueOf(date);
			s = s - 1;
			date = String.valueOf(s);
			paramMap.put("sessionNumber", date);
			List<Map<String, String>> recFirstTwoYearsList = resGradeBiz.getRecContentByProcess(paramMap);
			if (recFirstTwoYearsList != null && !recFirstTwoYearsList.isEmpty()) {
				recFirstTwoYearsAvgMap = avg(recFirstTwoYearsList);
			}
		}

		Map<String, Float> avgMap = new HashMap<>();
		if (recList != null && !recList.isEmpty()) {
			avgMap = avg(recList);
			if (waitSort != null) {
				final Map<String, Float> scoreMapTemp = avgMap;
				if ("teacher".equals(gradeRole)) {
					List<SysUser> userList = (List<SysUser>) waitSort;
					Collections.sort(userList, new Comparator<SysUser>() {
						@Override
						public int compare(SysUser u1, SysUser u2) {
							String k1 = u1.getUserFlow();
							String k2 = u2.getUserFlow();
							Float s1 = scoreMapTemp.get(k1 + "_Total");
							Float s2 = scoreMapTemp.get(k2 + "_Total");
							if (s1 == null) {
								s1 = 0f;
							}
							if (s2 == null) {
								s2 = 0f;
							}
							Float result = s2 - s1;

							return result > 0 ? 1 : result == 0 ? 0 : -1;
						}

					});
				} else if ("head".equals(gradeRole)) {
					List<SysDept> depts = (List<SysDept>) waitSort;
					Collections.sort(depts, new Comparator<SysDept>() {
						@Override
						public int compare(SysDept d1, SysDept d2) {
							String k1 = d1.getDeptFlow();
							String k2 = d2.getDeptFlow();
							Float s1 = scoreMapTemp.get(k1 + "_Total");
							Float s2 = scoreMapTemp.get(k2 + "_Total");
							if (s1 == null) {
								s1 = 0f;
							}
							if (s2 == null) {
								s2 = 0f;
							}
							Float result = s2 - s1;

							return result > 0 ? 1 : result == 0 ? 0 : -1;
						}

					});
				}
			}
		}

		String[] headLines = null;
		headLines = new String[]{
				"双向评价记录表"
		};
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setWrapText(true);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);
		//列宽自适应
//		sheet.setDefaultColumnWidth((short)50);
		HSSFRow rowDep = sheet.createRow(0);//第一行
		rowDep.setHeightInPoints(20);
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("双向评价记录表");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = null;
		String currYear = DateUtil.getCurrDateTime("yyyy");
		String firstTwoYear = "";
		String previouYear = "";
		int d = Integer.valueOf(currYear);
		int e = d - 1;
		int f = d - 2;
		previouYear = String.valueOf(e);
		firstTwoYear = String.valueOf(f);
		if ("head".equals(gradeRole)) {
			if (StringUtil.isNotBlank(sessionNumber)) {
				titles = new String[]{
						"编号",
						"科室名称",
						sessionNumber + "级总均分",
						"标准分",
						"总均分"
				};
			} else {
				titles = new String[]{
						"编号",
						"科室名称",
						firstTwoYear + "级总均分",
						previouYear + "级总均分",
						currYear + "级总均分",
						"标准分",
						"总均分"
				};
			}
		}
		if ("teacher".equals(gradeRole)) {
			if (StringUtil.isNotBlank(sessionNumber)) {
				titles = new String[]{
						"编号",
						"老师姓名",
						"科室名称",
						sessionNumber + "级总均分",
						"标准分",
						"总均分"
				};
			} else {
				titles = new String[]{
						"编号",
						"老师姓名",
						"科室名称",
						firstTwoYear + "级总均分",
						previouYear + "级总均分",
						currYear + "级总均分",
						"标准分",
						"总均分"
				};
			}
		}
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
		if ("head".equals(gradeRole)) {
			if (StringUtil.isNotBlank(sessionNumber)) {
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));//合并单元格
			} else {
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));//合并单元格
			}
		}
		if ("teacher".equals(gradeRole)) {
			if (StringUtil.isNotBlank(sessionNumber)) {
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));//合并单元格
			} else {
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并单元格
			}
		}
		int rowNum = 2;
		String[] resultList = null;
		if (waitSort != null) {
			if ("head".equals(gradeRole)) {
				List<SysDept> depts = (List<SysDept>) waitSort;
				if (depts.size() > 0) {
					String key = null;
					String avgScoreKey = null;
					if (StringUtil.isNotBlank(sessionNumber)) {
						for (int i = 0; i < depts.size(); i++, rowNum++) {
							key = depts.get(i).getDeptFlow();
							avgScoreKey = key + "_Total";
							HSSFRow rowFour = sheet.createRow(rowNum);//第二行
							resultList = new String[]{
									i + 1 + "",
									depts.get(i).getDeptName(),
									recDateAvgMap.get(avgScoreKey) == null ? "" : recDateAvgMap.get(avgScoreKey).toString(),
									scoreMap.get(key).toString(),
									avgMap.get(avgScoreKey)==null?"":avgMap.get(avgScoreKey).toString()
							};
							for (int j = 0; j < titles.length; j++) {
								HSSFCell cellFirst = rowFour.createCell(j);
								cellFirst.setCellStyle(styleCenter);
								cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
							}

						}
					} else {
						for (int i = 0; i < depts.size(); i++, rowNum++) {
							key = depts.get(i).getDeptFlow();
							avgScoreKey = key + "_Total";
							HSSFRow rowFour = sheet.createRow(rowNum);//第二行
							resultList = new String[]{
									i + 1 + "",
									depts.get(i).getDeptName(),
									recFirstTwoYearsAvgMap.get(avgScoreKey) == null ? "" : recFirstTwoYearsAvgMap.get(avgScoreKey).toString(),
									recpreviouYearAvgMap.get(avgScoreKey) == null ? "" : recpreviouYearAvgMap.get(avgScoreKey).toString(),
									recCurrDateAvgMap.get(avgScoreKey) == null ? "" : recCurrDateAvgMap.get(avgScoreKey).toString(),
									scoreMap.get(key).toString(),
									avgMap.get(avgScoreKey)==null?"":avgMap.get(avgScoreKey).toString()
							};
							for (int j = 0; j < titles.length; j++) {
								HSSFCell cellFirst = rowFour.createCell(j);
								cellFirst.setCellStyle(styleCenter);
								cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
							}

						}
					}
				}
			}
			if ("teacher".equals(gradeRole)) {
				List<SysUser> userList = (List<SysUser>) waitSort;
				if (userList.size() > 0) {
					String key = null;
					String avgScoreKey = null;
					if (StringUtil.isNotBlank(sessionNumber)) {
						for (int i = 0; i < userList.size(); i++, rowNum++) {
							key = userList.get(i).getUserFlow();
							avgScoreKey = key + "_Total";
							HSSFRow rowFour = sheet.createRow(rowNum);//第二行
							resultList = new String[]{
									i + 1 + "",
									userList.get(i).getUserName(),
									userList.get(i).getDeptName(),
									recDateAvgMap.get(avgScoreKey) == null ? "" : recDateAvgMap.get(avgScoreKey).toString(),
									scoreMap.get(key).toString(),
									avgMap.get(avgScoreKey)==null?"":avgMap.get(avgScoreKey).toString()
							};
							for (int j = 0; j < titles.length; j++) {
								HSSFCell cellFirst = rowFour.createCell(j);
								cellFirst.setCellStyle(styleCenter);
								cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
							}

						}
					} else {
						for (int i = 0; i < userList.size(); i++, rowNum++) {
							key = userList.get(i).getUserFlow();
							avgScoreKey = key + "_Total";
							HSSFRow rowFour = sheet.createRow(rowNum);//第二行
							resultList = new String[]{
									i + 1 + "",
									userList.get(i).getUserName(),
									userList.get(i).getDeptName(),
									recFirstTwoYearsAvgMap.get(avgScoreKey) == null ? "" : recFirstTwoYearsAvgMap.get(avgScoreKey).toString(),
									recpreviouYearAvgMap.get(avgScoreKey) == null ? "" : recpreviouYearAvgMap.get(avgScoreKey).toString(),
									recCurrDateAvgMap.get(avgScoreKey) == null ? "" : recCurrDateAvgMap.get(avgScoreKey).toString(),
									scoreMap.get(key).toString(),
									avgMap.get(avgScoreKey)==null?"":avgMap.get(avgScoreKey).toString()
							};
							for (int j = 0; j < titles.length; j++) {
								HSSFCell cellFirst = rowFour.createCell(j);
								cellFirst.setCellStyle(styleCenter);
								cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
							}

						}
					}
				}
			}
		}

		String fileName = "双向评价记录表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
	/**
	 * 广医 学员对带教、科室、管理员评价
     * * @param role
	 * @param gradeRole
	 * @param deptFlow
	 * @param userName
	 * @param deptName
	 * @param operStartDate
	 * @param operEndDate
	 * @param sessionNumber
	 * @param currentPage
     * @param request
     * @param model
     * @return
     */
	@RequestMapping(value="/gradeSearchNew")
	public String gradeSearchNew(String role, String gradeRole, String deptFlow, String userName, String deptName,
							  String operStartDate, String operEndDate, String sessionNumber, Integer currentPage, HttpServletRequest request, Model model){
		if(StringUtil.isNotBlank(operStartDate)){
			operStartDate = DateUtil.getDate(operStartDate)+"000000";
		}
		if(StringUtil.isNotBlank(operEndDate)){
			operEndDate = DateUtil.getDate(operEndDate)+"235959";
		}

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		//专业基地管理员专业ID
		String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("operStartDate",operStartDate);
		paramMap.put("operEndDate",operEndDate);
		paramMap.put("userName",userName);
		paramMap.put("deptFlow",deptFlow);
		paramMap.put("deptName",deptName);

		model.addAttribute("role",role);
		if("professionalBase".equals(role)){
            //查出当前专业基地的所有科室
			ResTrainingSpeDept search = new ResTrainingSpeDept();
			search.setOrgFlow(orgFlow);
			search.setTrainingSpeId(trainingSpeId);
			List<ResTrainingSpeDept> trainingSpeDeptList = trainingSpeDeptBiz.search(search);
			model.addAttribute("depts",trainingSpeDeptList);

			List<String> deptFlows = new ArrayList<>();
			if(trainingSpeDeptList!=null&&trainingSpeDeptList.size()>0){
				for(ResTrainingSpeDept resTrainingSpeDept:trainingSpeDeptList){
					String deptFLow = resTrainingSpeDept.getDeptFlow();
					deptFlows.add(deptFLow);
				}
			}
			paramMap.put("deptFlows",deptFlows);
		}else{
            //查出当前机构的所有科室
			List<SysDept> 	deptList =	deptBiz.searchDeptByOrg(orgFlow);
			model.addAttribute("depts",deptList);
		}

		List<String> keys = new ArrayList<String>();
		Object waitSort = null;
		Map<String, Object> scoreMap=new HashMap<String, Object>();
		if("teacher".equals(gradeRole)){
			//带教flow
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			paramMap.put("roleFlow",teacherRoleFlow);

            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList",assessCfgList);
			//查出当前机构的所有带教老师
            if(null==currentPage){
               currentPage=1;
            }
			PageHelper.startPage(currentPage,getPageSize(request));
			List<SysUser> userList = resGradeBiz.getUserByRec(paramMap);
			model.addAttribute("datas",userList);
			waitSort = userList;
			if(userList!=null && !userList.isEmpty()){
				for(SysUser su : userList){
					keys.add(su.getUserFlow());
					int score=0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if (resAssessCfgTitleForm.getItemList()!=null && resAssessCfgTitleForm.getItemList().size()>0) {
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s=Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
								score+=s;
							}
						}
					}
					scoreMap.put(su.getUserFlow(), score);
				}
			}
			paramMap.put("teacherFlows",keys);
		}else if("head".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
			model.addAttribute("assessCfgList",assessCfgList);
			PageHelper.startPage(currentPage,getPageSize(request));
			List<SysDept> depts = resGradeBiz.getDeptByRec(paramMap);
			model.addAttribute("datas",depts);
			waitSort = depts;
			if(depts!=null && !depts.isEmpty()){
				for(SysDept sd : depts){
					keys.add(sd.getDeptFlow());
					int score=0;
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if(null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size()>0){
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s=Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
								score+=s;
							}
						}
					}
					scoreMap.put(sd.getDeptFlow(), score);
				}
			}
			paramMap.put("deptFlows",keys);
		}else if("admin".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.ManagerGrade.getId();
            paramMap.put("recTypeId",recType);

            cfgCode = ResAssessTypeEnum.ManagerAssess.getId();
            List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
            model.addAttribute("assessCfgList",assessCfgList);
            PageHelper.startPage(currentPage,getPageSize(request));
            List<SysOrg> orgList = resGradeBiz.getOrgByRec(paramMap);
            model.addAttribute("datas",orgList);

            int score=0;
            for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
                if(null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size()>0){
                    for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
                        int s=Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
                        score+=s;
                    }
                }
            }
            if(null!=orgList && orgList.size()>0){
                scoreMap.put(orgList.get(0).getOrgFlow(), score);
            }
        }
		//获取评分数据
		List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess(paramMap);

        //查询后台配置是否为进修过程管理页面
        String showflag = InitConfig.getSysCfg("is_show_jxres");
        model.addAttribute("showflag",showflag);
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(showflag)) {
            if (StringUtil.isNotBlank(sessionNumber)) {
                paramMap.put("sessionNumber", sessionNumber);
                List<Map<String, String>> recDateList = resGradeBiz.getRecContentByProcess(paramMap);
                if(recDateList!=null && !recDateList.isEmpty()){
                    Map<String,Float> recDateAvgMap=avg(recDateList);
                    model.addAttribute("recDateAvgMap", recDateAvgMap);
                }
            }else{
                String date=DateUtil.getCurrDateTime("yyyy");
                model.addAttribute("currDate", date);
                paramMap.put("sessionNumber", date);
                List<Map<String, String>> recCurrDateList = resGradeBiz.getRecContentByProcess(paramMap);
                if(recCurrDateList!=null && !recCurrDateList.isEmpty()){
                    Map<String,Float> recCurrDateAvgMap=avg(recCurrDateList);
                    model.addAttribute("recCurrDateAvgMap", recCurrDateAvgMap);
                }
                int d=Integer.valueOf(date);
                d=d-1;
                date=String.valueOf(d);
                model.addAttribute("PreviouYearDate", date);
                paramMap.put("sessionNumber", date);
                List<Map<String, String>> recpreviouYearList = resGradeBiz.getRecContentByProcess(paramMap);
                if(recpreviouYearList!=null && !recpreviouYearList.isEmpty()){
                    Map<String,Float> recpreviouYearAvgMap=avg(recpreviouYearList);
                    model.addAttribute("recpreviouYearAvgMap", recpreviouYearAvgMap);
                }
                int s=Integer.valueOf(date);
                s=s-1;
                date=String.valueOf(s);
                model.addAttribute("FirstTwoYearsDate", date);
                paramMap.put("sessionNumber", date);
                List<Map<String, String>> recFirstTwoYearsList = resGradeBiz.getRecContentByProcess(paramMap);
                if(recFirstTwoYearsList!=null && !recFirstTwoYearsList.isEmpty()){
                    Map<String,Float> recFirstTwoYearsAvgMap=avg(recFirstTwoYearsList);
                    model.addAttribute("recFirstTwoYearsAvgMap", recFirstTwoYearsAvgMap);
                }
            }

        }

		if(recList!=null && !recList.isEmpty()){
			Map<String,Float> avgMap=avg(recList);
			if(waitSort!=null){
				final Map<String,Float> scoreMapTemp = avgMap;
				if("teacher".equals(gradeRole)){
					List<SysUser> userList = (List<SysUser>) waitSort;
					Collections.sort(userList,new Comparator<SysUser>() {
						@Override
						public int compare(SysUser u1,SysUser u2) {
							String k1 = u1.getUserFlow();
							String k2 = u2.getUserFlow();
							Float s1 = scoreMapTemp.get(k1+"_Total");
							Float s2 = scoreMapTemp.get(k2+"_Total");
							if(s1==null){
								s1=0f;
							}
							if(s2==null){
								s2=0f;
							}
							Float result = s2-s1;

							return result>0?1:result==0?0:-1;
						}

					});
				}else if("head".equals(gradeRole)){
					List<SysDept> depts = (List<SysDept>) waitSort;
					Collections.sort(depts,new Comparator<SysDept>() {
						@Override
						public int compare(SysDept d1,SysDept d2) {
							String k1 = d1.getDeptFlow();
							String k2 = d2.getDeptFlow();
							Float s1 = scoreMapTemp.get(k1+"_Total");
							Float s2 = scoreMapTemp.get(k2+"_Total");
							if(s1==null){
								s1=0f;
							}
							if(s2==null){
								s2=0f;
							}
							Float result = s2-s1;

							return result>0?1:result==0?0:-1;
						}

					});
				}else if("admin".equals(gradeRole)){
                    List<SysOrg> orgList = (List<SysOrg>) waitSort;
                    Collections.sort(orgList,new Comparator<SysOrg>() {
                        @Override
                        public int compare(SysOrg so1,SysOrg so2) {
                            String k1 = so1.getOrgFlow();
                            String k2 = so2.getOrgFlow();
                            Float s1 = scoreMapTemp.get(k1+"_Total");
                            Float s2 = scoreMapTemp.get(k2+"_Total");
                            if(s1==null){
                                s1=0f;
                            }
                            if(s2==null){
                                s2=0f;
                            }
                            Float result = s2-s1;

                            return result>0?1:result==0?0:-1;
                        }
                    });
                }
			}
			model.addAttribute("avgMap", avgMap);
		}

		model.addAttribute("scoreMap", scoreMap);

		return "res/teacher/gradeSearchNew";
	}






    /**
     * 导出双向评价（广医进修过程）
     * @param response
     * @param role
     * @param gradeRole
     * @param deptFlow
     * @param userName
     * @param deptName
     * @param operStartDate
     * @param operEndDate
     * @param sessionNumber
     * @throws Exception
     */
    @RequestMapping(value = "/exportEvalNew")
    public void exportEvalNew(HttpServletResponse response, String role, String gradeRole, String deptFlow, String userName, String deptName,
                           String operStartDate, String operEndDate, String sessionNumber) throws Exception {
        if (StringUtil.isNotBlank(operStartDate)) {
            operStartDate = DateUtil.getDate(operStartDate) + "000000";
        }
        if (StringUtil.isNotBlank(operEndDate)) {
            operEndDate = DateUtil.getDate(operEndDate) + "235959";
        }

        //当前用户所在机构
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        //专业基地管理员专业ID
        String trainingSpeId = GlobalContext.getCurrentUser().getResTrainingSpeId();
        //登记类型
        String recType = "";
        //评分类型
        String cfgCode = "";
        //查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("operStartDate", operStartDate);
        paramMap.put("operEndDate", operEndDate);
        paramMap.put("userName", userName);
        paramMap.put("deptFlow", deptFlow);
        paramMap.put("deptName", deptName);

        if ("professionalBase".equals(role)) {
            //查出当前专业基地的所有科室
            ResTrainingSpeDept search = new ResTrainingSpeDept();
            search.setOrgFlow(orgFlow);
            search.setTrainingSpeId(trainingSpeId);
            List<ResTrainingSpeDept> trainingSpeDeptList = trainingSpeDeptBiz.search(search);

            List<String> deptFlows = new ArrayList<>();
            if (trainingSpeDeptList != null && trainingSpeDeptList.size() > 0) {
                for (ResTrainingSpeDept resTrainingSpeDept : trainingSpeDeptList) {
                    String deptFLow = resTrainingSpeDept.getDeptFlow();
                    deptFlows.add(deptFLow);
                }
            }
            paramMap.put("deptFlows", deptFlows);
        }

        List<String> keys = new ArrayList<String>();
        Object waitSort = null;
        Map<String, Object> scoreMap = new HashMap<String, Object>();
        if ("teacher".equals(gradeRole)) {
            //带教flow
            String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
            paramMap.put("roleFlow", teacherRoleFlow);

            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
            paramMap.put("recTypeId", recType);

            cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
            List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
            //查出当前机构的所有带教老师
            List<SysUser> userList = resGradeBiz.getUserByRec(paramMap);
            waitSort = userList;
            if (userList != null && !userList.isEmpty()) {
                for (SysUser su : userList) {
                    keys.add(su.getUserFlow());
                    int score = 0;
                    for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
                        if (resAssessCfgTitleForm.getItemList() != null && resAssessCfgTitleForm.getItemList().size() > 0) {
                            for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
                                int s = Integer.parseInt("".equals(resAssessCfgItemForm.getScore()) ? "0" : resAssessCfgItemForm.getScore());
                                score += s;
                            }
                        }
                    }
                    scoreMap.put(su.getUserFlow(), score);
                }
            }
            paramMap.put("teacherFlows", keys);
        } else if ("head".equals(gradeRole)) {
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
            paramMap.put("recTypeId", recType);

            cfgCode = ResAssessTypeEnum.DeptAssess.getId();
            List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
            List<SysDept> depts = resGradeBiz.getDeptByRec(paramMap);
            waitSort = depts;
            if (depts != null && !depts.isEmpty()) {
                for (SysDept sd : depts) {
                    keys.add(sd.getDeptFlow());
                    int score = 0;
                    for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
                        if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
                            for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
                                int s = Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
                                score += s;
                            }
                        }
                    }
                    scoreMap.put(sd.getDeptFlow(), score);
                }
            }
            paramMap.put("deptFlows", keys);
        }else if("admin".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.ManagerGrade.getId();
            paramMap.put("recTypeId",recType);

            cfgCode = ResAssessTypeEnum.ManagerAssess.getId();
            List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
            List<SysOrg> orgList = resGradeBiz.getOrgByRec(paramMap);

            waitSort = orgList;
            if (orgList != null && !orgList.isEmpty()) {
                for (SysOrg sd : orgList) {
                    keys.add(sd.getOrgFlow());
                    int score = 0;
                    for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
                        if (null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size() > 0) {
                            for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
                                int s = Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
                                score += s;
                            }
                        }
                    }
                    scoreMap.put(sd.getOrgFlow(), score);
                }
            }

        }

        //获取评分数据
        List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess(paramMap);

        Map<String, Float> recDateAvgMap = new HashMap<>();
        Map<String, Float> recCurrDateAvgMap = new HashMap<>();
        Map<String, Float> recpreviouYearAvgMap = new HashMap<>();
        Map<String, Float> recFirstTwoYearsAvgMap = new HashMap<>();
        if (StringUtil.isNotBlank(sessionNumber)) {
            paramMap.put("sessionNumber", sessionNumber);
            List<Map<String, String>> recDateList = resGradeBiz.getRecContentByProcess(paramMap);
            if (recDateList != null && !recDateList.isEmpty()) {
                recDateAvgMap = avg(recDateList);
            }
        } else {
            String date = DateUtil.getCurrDateTime("yyyy");
            paramMap.put("sessionNumber", date);
            List<Map<String, String>> recCurrDateList = resGradeBiz.getRecContentByProcess(paramMap);
            if (recCurrDateList != null && !recCurrDateList.isEmpty()) {
                recCurrDateAvgMap = avg(recCurrDateList);
            }
            int d = Integer.valueOf(date);
            d = d - 1;
            date = String.valueOf(d);
            paramMap.put("sessionNumber", date);
            List<Map<String, String>> recpreviouYearList = resGradeBiz.getRecContentByProcess(paramMap);
            if (recpreviouYearList != null && !recpreviouYearList.isEmpty()) {
                recpreviouYearAvgMap = avg(recpreviouYearList);
            }
            int s = Integer.valueOf(date);
            s = s - 1;
            date = String.valueOf(s);
            paramMap.put("sessionNumber", date);
            List<Map<String, String>> recFirstTwoYearsList = resGradeBiz.getRecContentByProcess(paramMap);
            if (recFirstTwoYearsList != null && !recFirstTwoYearsList.isEmpty()) {
                recFirstTwoYearsAvgMap = avg(recFirstTwoYearsList);
            }
        }

        Map<String, Float> avgMap = new HashMap<>();
        if (recList != null && !recList.isEmpty()) {
            avgMap = avg(recList);
            if (waitSort != null) {
                final Map<String, Float> scoreMapTemp = avgMap;
                if ("teacher".equals(gradeRole)) {
                    List<SysUser> userList = (List<SysUser>) waitSort;
                    Collections.sort(userList, new Comparator<SysUser>() {
                        @Override
                        public int compare(SysUser u1, SysUser u2) {
                            String k1 = u1.getUserFlow();
                            String k2 = u2.getUserFlow();
                            Float s1 = scoreMapTemp.get(k1 + "_Total");
                            Float s2 = scoreMapTemp.get(k2 + "_Total");
                            if (s1 == null) {
                                s1 = 0f;
                            }
                            if (s2 == null) {
                                s2 = 0f;
                            }
                            Float result = s2 - s1;

                            return result > 0 ? 1 : result == 0 ? 0 : -1;
                        }

                    });
                } else if ("head".equals(gradeRole)) {
                    List<SysDept> depts = (List<SysDept>) waitSort;
                    Collections.sort(depts, new Comparator<SysDept>() {
                        @Override
                        public int compare(SysDept d1, SysDept d2) {
                            String k1 = d1.getDeptFlow();
                            String k2 = d2.getDeptFlow();
                            Float s1 = scoreMapTemp.get(k1 + "_Total");
                            Float s2 = scoreMapTemp.get(k2 + "_Total");
                            if (s1 == null) {
                                s1 = 0f;
                            }
                            if (s2 == null) {
                                s2 = 0f;
                            }
                            Float result = s2 - s1;

                            return result > 0 ? 1 : result == 0 ? 0 : -1;
                        }

                    });
                }
            }
        }

        String[] headLines = null;
        headLines = new String[]{
                "双向评价记录表"
        };
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        styleCenter.setWrapText(true);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);
        //列宽自适应
//		sheet.setDefaultColumnWidth((short)50);
        HSSFRow rowDep = sheet.createRow(0);//第一行
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("双向评价记录表");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        String[] titles = null;
        String currYear = DateUtil.getCurrDateTime("yyyy");
        String firstTwoYear = "";
        String previouYear = "";
        int d = Integer.valueOf(currYear);
        int e = d - 1;
        int f = d - 2;
        previouYear = String.valueOf(e);
        firstTwoYear = String.valueOf(f);
        if ("head".equals(gradeRole)) {
            if (StringUtil.isNotBlank(sessionNumber)) {
                titles = new String[]{
                        "编号",
                        "科室名称",
//                        sessionNumber + "级总均分",
                        "标准分",
                        "总均分"
                };
            } else {
                titles = new String[]{
                        "编号",
                        "科室名称",
//                        firstTwoYear + "级总均分",
//                        previouYear + "级总均分",
//                        currYear + "级总均分",
                        "标准分",
                        "总均分"
                };
            }
        }
        if ("teacher".equals(gradeRole)) {
            if (StringUtil.isNotBlank(sessionNumber)) {
                titles = new String[]{
                        "编号",
                        "老师姓名",
                        "科室名称",
//                        sessionNumber + "级总均分",
                        "标准分",
                        "总均分"
                };
            } else {
                titles = new String[]{
                        "编号",
                        "老师姓名",
                        "科室名称",
//                        firstTwoYear + "级总均分",
//                        previouYear + "级总均分",
//                        currYear + "级总均分",
                        "标准分",
                        "总均分"
                };
            }
        }
        if ("admin".equals(gradeRole)) {
            titles = new String[]{
                    "编号",
                    "继续教育科",
                    "标准分",
                    "总均分"
            };
        }

        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowTwo.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        if ("head".equals(gradeRole)) {
            if (StringUtil.isNotBlank(sessionNumber)) {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));//合并单元格
            } else {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));//合并单元格
            }
        }
        if ("teacher".equals(gradeRole)) {
            if (StringUtil.isNotBlank(sessionNumber)) {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));//合并单元格
            } else {
                sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并单元格
            }
        }
        if ("admin".equals(gradeRole)) {
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));//合并单元格
        }
        int rowNum = 2;
        String[] resultList = null;
        if (waitSort != null) {
            if ("head".equals(gradeRole)) {
                List<SysDept> depts = (List<SysDept>) waitSort;
                if (depts.size() > 0) {
                    String key = null;
                    String avgScoreKey = null;
                    if (StringUtil.isNotBlank(sessionNumber)) {
                        for (int i = 0; i < depts.size(); i++, rowNum++) {
                            key = depts.get(i).getDeptFlow();
                            avgScoreKey = key + "_Total";
                            HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                            resultList = new String[]{
                                    i + 1 + "",
                                    depts.get(i).getDeptName(),
                                    recDateAvgMap.get(avgScoreKey) == null ? "" : recDateAvgMap.get(avgScoreKey).toString(),
                                    scoreMap.get(key).toString(),
                                    avgMap.get(avgScoreKey).toString()
                            };
                            for (int j = 0; j < titles.length; j++) {
                                HSSFCell cellFirst = rowFour.createCell(j);
                                cellFirst.setCellStyle(styleCenter);
                                cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                            }

                        }
                    } else {
                        for (int i = 0; i < depts.size(); i++, rowNum++) {
                            key = depts.get(i).getDeptFlow();
                            avgScoreKey = key + "_Total";
                            HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                            resultList = new String[]{
                                    i + 1 + "",
                                    depts.get(i).getDeptName(),
//                                    recFirstTwoYearsAvgMap.get(avgScoreKey) == null ? "" : recFirstTwoYearsAvgMap.get(avgScoreKey).toString(),
//                                    recpreviouYearAvgMap.get(avgScoreKey) == null ? "" : recpreviouYearAvgMap.get(avgScoreKey).toString(),
//                                    recCurrDateAvgMap.get(avgScoreKey) == null ? "" : recCurrDateAvgMap.get(avgScoreKey).toString(),
                                    scoreMap.get(key).toString(),
                                    avgMap.get(avgScoreKey).toString()
                            };
                            for (int j = 0; j < titles.length; j++) {
                                HSSFCell cellFirst = rowFour.createCell(j);
                                cellFirst.setCellStyle(styleCenter);
                                cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                            }

                        }
                    }
                }
            }
            if ("teacher".equals(gradeRole)) {
                List<SysUser> userList = (List<SysUser>) waitSort;
                if (userList.size() > 0) {
                    String key = null;
                    String avgScoreKey = null;
                    if (StringUtil.isNotBlank(sessionNumber)) {
                        for (int i = 0; i < userList.size(); i++, rowNum++) {
                            key = userList.get(i).getUserFlow();
                            avgScoreKey = key + "_Total";
                            HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                            resultList = new String[]{
                                    i + 1 + "",
                                    userList.get(i).getUserName(),
                                    userList.get(i).getDeptName(),
                                    recDateAvgMap.get(avgScoreKey) == null ? "" : recDateAvgMap.get(avgScoreKey).toString(),
                                    scoreMap.get(key).toString(),
                                    avgMap.get(avgScoreKey).toString()
                            };
                            for (int j = 0; j < titles.length; j++) {
                                HSSFCell cellFirst = rowFour.createCell(j);
                                cellFirst.setCellStyle(styleCenter);
                                cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                            }

                        }
                    } else {
                        for (int i = 0; i < userList.size(); i++, rowNum++) {
                            key = userList.get(i).getUserFlow();
                            avgScoreKey = key + "_Total";
                            HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                            resultList = new String[]{
                                    i + 1 + "",
                                    userList.get(i).getUserName(),
                                    userList.get(i).getDeptName(),
//                                    recFirstTwoYearsAvgMap.get(avgScoreKey) == null ? "" : recFirstTwoYearsAvgMap.get(avgScoreKey).toString(),
//                                    recpreviouYearAvgMap.get(avgScoreKey) == null ? "" : recpreviouYearAvgMap.get(avgScoreKey).toString(),
//                                    recCurrDateAvgMap.get(avgScoreKey) == null ? "" : recCurrDateAvgMap.get(avgScoreKey).toString(),
                                    scoreMap.get(key).toString(),
                                    avgMap.get(avgScoreKey).toString()
                            };
                            for (int j = 0; j < titles.length; j++) {
                                HSSFCell cellFirst = rowFour.createCell(j);
                                cellFirst.setCellStyle(styleCenter);
                                cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                            }

                        }
                    }
                }
            }

            if ("admin".equals(gradeRole)) {
                List<SysOrg> orgList = (List<SysOrg>) waitSort;
                if (orgList.size() > 0) {
                    String key = null;
                    String avgScoreKey = null;

                        for (int i = 0; i < orgList.size(); i++, rowNum++) {
                            key = orgList.get(i).getOrgFlow();
                            avgScoreKey = key + "_Total";
                            HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                            resultList = new String[]{
                                    i + 1 + "",
                                    orgList.get(i).getOrgName(),
//                                    recFirstTwoYearsAvgMap.get(avgScoreKey) == null ? "" : recFirstTwoYearsAvgMap.get(avgScoreKey).toString(),
//                                    recpreviouYearAvgMap.get(avgScoreKey) == null ? "" : recpreviouYearAvgMap.get(avgScoreKey).toString(),
//                                    recCurrDateAvgMap.get(avgScoreKey) == null ? "" : recCurrDateAvgMap.get(avgScoreKey).toString(),
                                    scoreMap.get(key).toString(),
                                    avgMap.get(avgScoreKey).toString()
                            };
                            for (int j = 0; j < titles.length; j++) {
                                HSSFCell cellFirst = rowFour.createCell(j);
                                cellFirst.setCellStyle(styleCenter);
                                cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                            }
                        }

                }
            }
        }

        String fileName = "双向评价记录表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }




	/**
	 * 双向评分详情
	 * @param gradeRole
	 * @param keyCode
	 * @param operStartDate
	 * @param operEndDate
	 * @param date
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/gradeSearchDoc"})
	public String gradeSearchDoc(
			String gradeRole,
			String keyCode,
			String operStartDate,
			String operEndDate,
			String date,
			String role,
			Model model
	){
		if(StringUtil.isNotBlank(operStartDate)){
			operStartDate = DateUtil.getDate(operStartDate)+"000000";
		}
		if(StringUtil.isNotBlank(operEndDate)){
			operEndDate = DateUtil.getDate(operEndDate)+"235959";
		}

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String,Object> scoreSumMap = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("sessionNumber",date.equals("null")?null:date);
		paramMap.put("operStartDate",operStartDate);
		paramMap.put("operEndDate",operEndDate);

        if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
			String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			if(StringUtil.isNotBlank(currentOrgFlow)){
				SysOrg org = orgBiz.readSysOrg(currentOrgFlow);
				String workOrgId = org.getSendSchoolId();
				paramMap.put("workOrgId",workOrgId);
			}
		}
		if("teacher".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();

			paramMap.put("teacherFlow",keyCode);
		}else if("head".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId",recType);

			cfgCode = ResAssessTypeEnum.DeptAssess.getId();

			paramMap.put("deptFlow",keyCode);
		}else if("admin".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.ManagerGrade.getId();
            paramMap.put("recTypeId",recType);
            cfgCode = ResAssessTypeEnum.ManagerAssess.getId();

        }
		List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
		//获取评分数据
		List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess(paramMap);
		if(recList!=null && !recList.isEmpty()){
			model.addAttribute("recList",recList);
			Map<String,Float> scoreMap = new HashMap<String, Float>();
			for(Map<String,String> map : recList){
				String operUserFlow = map.get("operUserFlow")+map.get("recFlow");
				int scoreSum=0;
				if(null != assessCfgList && assessCfgList.size()>0 ){
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
						if(null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size()>0){
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s=Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
								scoreSum+=s;
							}
						}
					}
				}
				scoreSumMap.put(operUserFlow, scoreSum);
				String content = map.get("content");

				Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
				if(gradeMap!=null && !gradeMap.isEmpty()){
					for(String gk : gradeMap.keySet()){
						Object o = gradeMap.get(gk);
						Float score = 0f;
						if(o instanceof Map){
							Map<String,String> dataMap = (Map<String, String>) o;
							if(dataMap!=null){
								try {
									String scoreS = dataMap.get("score");
									score = Float.valueOf(scoreS);
								} catch (Exception e) {
                                    logger.error("", e);
								}

								putMapVal(scoreMap,operUserFlow+gk,score);
							}
						}else{
							try {
								String scoreS = (String) gradeMap.get("totalScore");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
                                logger.error("", e);
							}

							putMapVal(scoreMap,operUserFlow,score);
						}
					}

				}
			}
			final Map<String,Float> scoreMapTemp = scoreMap;
			Collections.sort(recList,new Comparator<Map<String,String>>() {
				@Override
				public int compare(Map<String, String> m1,Map<String, String> m2) {
					String k1 = m1.get("operUserFlow")+m1.get("recFlow");
					String k2 = m2.get("operUserFlow")+m2.get("recFlow");
					Float s1 = scoreMapTemp.get(k1);
					Float s2 = scoreMapTemp.get(k2);
					Float result = s2-s1;

					return result>0?1:result==0?0:-1;
				}

			});
			model.addAttribute("scoreMap",scoreMap);
		}

		model.addAttribute("scoreSumMap",scoreSumMap);
		model.addAttribute("assessCfgList",assessCfgList);

		return "res/teacher/gradeSearchDoc";
	}

	@RequestMapping(value={"/exportGradeSearchDoc"})
	public void exportGradeSearchDoc(
			String gradeRole,
			String keyCode,
			String operStartDate,
			String operEndDate,
			String date,
			String recFlow,
			String role,
			HttpServletResponse response,HttpServletRequest request
	) throws Exception{
		boolean isOneFile = false;
		if(StringUtil.isNotBlank(operStartDate)){
			operStartDate = DateUtil.getDate(operStartDate)+"000000";
		}
		if(StringUtil.isNotBlank(operEndDate)){
			operEndDate = DateUtil.getDate(operEndDate)+"235959";
		}

		//当前用户所在机构
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();

		//登记类型
		String recType = "";
		//评分类型
		String cfgCode = "";
		//查询条件
		Map<String,Object> scoreSumMap = new HashMap<String, Object>();
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("sessionNumber",date);
		paramMap.put("operStartDate",operStartDate);
		paramMap.put("operEndDate",operEndDate);
		paramMap.put("recFlow",recFlow);
		if("teacher".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
			paramMap.put("recTypeId",recType);
			cfgCode = ResAssessTypeEnum.TeacherAssess.getId();
			paramMap.put("teacherFlow",keyCode);
		}else if("head".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
			paramMap.put("recTypeId",recType);
			cfgCode = ResAssessTypeEnum.DeptAssess.getId();
			paramMap.put("deptFlow",keyCode);
		}else if("admin".equals(gradeRole)){
            recType = com.pinde.core.common.enums.ResRecTypeEnum.ManagerGrade.getId();
			paramMap.put("recTypeId",recType);
			cfgCode = ResAssessTypeEnum.ManagerAssess.getId();

		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(role)) {
			String currentOrgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			if(StringUtil.isNotBlank(currentOrgFlow)){
				SysOrg org = orgBiz.readSysOrg(currentOrgFlow);
				String workOrgId = org.getSendSchoolId();
				paramMap.put("workOrgId",workOrgId);
			}
		}
		List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCode);
		//获取评分数据
		List<Map<String, String>> recList = resGradeBiz.getRecContentByProcess(paramMap);
		//导出结果集合
		List<List<Map<String, String>>> exportInfoList = new ArrayList<>();
		List<String> fileNames = new ArrayList<>();

		String [] titles = {
				"itemName:评分细则",
				"itemScore:标准分",
				"avgScore:总分"
		};
		int i=0;
		if(recList!=null && !recList.isEmpty()){
			if(recList.size() == 1){
				isOneFile = true;
			}
			Map<String,Float> scoreMap = new HashMap<String, Float>();
			for(Map<String,String> map : recList){
				i++;
				//单条结果导出集合
				List<Map<String, String>> oneInfoList = new ArrayList<>();

				String operUserFlow = map.get("operUserFlow")+map.get("recFlow");

				String content = map.get("content");
				Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
				if(gradeMap!=null && !gradeMap.isEmpty()){
					for(String gk : gradeMap.keySet()){
						Object o = gradeMap.get(gk);
						Float score = 0f;
						if(o instanceof Map){
							Map<String,String> dataMap = (Map<String, String>) o;
							if(dataMap!=null){
								try {
									String scoreS = dataMap.get("score");
									score = Float.valueOf(scoreS);
								} catch (Exception e) {
                                    logger.error("", e);
								}

								putMapVal(scoreMap,operUserFlow+gk,score);
							}
						}else{
							try {
								String scoreS = (String) gradeMap.get("totalScore");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
                                logger.error("", e);
							}

							putMapVal(scoreMap,operUserFlow,score);
						}
					}

				}
				int sum=0;
				for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
					Map<String,String> itemMap = new HashMap<>();
					itemMap.put("itemName",resAssessCfgTitleForm.getName());
					itemMap.put("itemScore","");
					itemMap.put("avgScore","");
					oneInfoList.add(itemMap);
					if(null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size()>0) {
						for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
							itemMap = new HashMap<>();
							itemMap.put("itemName",resAssessCfgItemForm.getName());
							itemMap.put("itemScore",resAssessCfgItemForm.getScore());
							itemMap.put("avgScore",(scoreMap.get(operUserFlow+resAssessCfgItemForm.getId())==null?"":scoreMap.get(operUserFlow+resAssessCfgItemForm.getId()))+"");
							oneInfoList.add(itemMap);
							sum+=StringUtil.isBlank(resAssessCfgItemForm.getScore())?0:Integer.valueOf(resAssessCfgItemForm.getScore());
						}
					}
				}
				Map<String,String> itemMap = new HashMap<>();
				itemMap.put("itemName","总分");
				itemMap.put("itemScore",sum+"");
				itemMap.put("avgScore",(scoreMap.get(operUserFlow)==null?"":scoreMap.get(operUserFlow))+"");
				oneInfoList.add(itemMap);
				String fileName = map.get("operUserName")+map.get("deptName")+"("+map.get("schStartDate")+"~"+map.get("schEndDate")+")";
				if(isOneFile){
					if(StringUtil.isBlank(fileName))
						fileName="双向评价明细导出.xls";
					else
						fileName+=".xls";
					fileName = URLEncoder.encode(fileName, "UTF-8");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					response.setContentType("application/octet-stream;charset=UTF-8");
					ExcleUtile.exportSimpleExcleByObjs(titles, oneInfoList, response.getOutputStream());
				}else {
					if(StringUtil.isBlank(fileName))
						fileName="双向评价明细导出-"+i;
						fileNames.add(fileName);
					exportInfoList.add(oneInfoList);
				}
			}
		}
		if(!isOneFile) {
			String fileName = "双向评价明细导出";
			response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName+".zip", "UTF-8") + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			ExcleUtile.exportExcel(fileName,fileNames, titles, exportInfoList, response.getOutputStream(), request);
		}

	}
	/**
	 * 评价查询
	 */
	@RequestMapping(value="/evaluateSearch")
	public String evaluateSearch(Model model,String roleType,String doctorName,String startDate,String endDate) throws Exception{
		SysUser sysUser=GlobalContext.getCurrentUser();
		String cfgTeacher=InitConfig.getSysCfg("res_teacher_role_flow");
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
			List<SysUser> sysUserList=userBiz.teacherRoleCheckUser(sysUser.getDeptFlow(),cfgTeacher,doctorName,sysUser.getUserFlow());
			Map<String, Integer> studentNumMap=new HashMap<String, Integer>();
			Map<String, String> averageMap=new HashMap<String, String>();
			for (SysUser user : sysUserList) {
				String currKey="curr"+user.getUserFlow();
				String studentKey="student"+user.getUserFlow();
				int currStudentHe=resDoctorProcessBiz.resSchDoctorSchProcessDistinctQuery(user.getUserFlow());
				int studentNum=resDoctorProcessBiz.resSchDoctorSchProcessTeacherQuery(user.getUserFlow());
				studentNumMap.put(currKey, currStudentHe);
				studentNumMap.put(studentKey, studentNum);
//				List<ResRec> recList=resRecBiz.resSearchProssFlowRec(user.getUserFlow(),startDate,endDate);
				List<DeptTeacherGradeInfo> recList=resGradeBiz.resSearchProssFlowRec(user.getUserFlow(),startDate,endDate);
				Float sum=0f;
				Float average=0f;
				if (recList!=null && recList.size()>0) {
					for (DeptTeacherGradeInfo resRec : recList) {
						Map<String,Object> gradeMap = resRecBiz.parseGradeXml(resRec.getRecContent());
						Float zongFen= Float.parseFloat(gradeMap.get("totalScore").toString());
						sum=sum+zongFen;
					}
					average=sum/recList.size();
				}
				BigDecimal bd=new BigDecimal(average).setScale(1, RoundingMode.UP);
				averageMap.put(user.getUserFlow(), bd.toString());

			}
			if(null != sysUserList && sysUserList.size() > 1){
				final Map<String, String> averageMapTemp = averageMap;
				java.util.Collections.sort(sysUserList,new Comparator<SysUser>() {
					@Override
					public int compare(SysUser o1, SysUser o2) {
						String	o1Key=o1.getUserFlow();
						String	o2Key=o2.getUserFlow();
						String s1=averageMapTemp.get(o1Key);
						String s2=averageMapTemp.get(o2Key);
						Float f1=0f;
						Float f2=0f;
						try {
							f1=Float.valueOf(s1);
							f2=Float.valueOf(s2);
						} catch (Exception e) {
                            logger.error("", e);
						}
						Float result=f2-f1;
						return result>0?1:result==0?0:-1;
					}
				});
			}
			model.addAttribute("averageMap", averageMap);
			model.addAttribute("studentNumMap", studentNumMap);
			model.addAttribute("sysUserList", sysUserList);
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {
			model.addAttribute("sysUser",sysUser);
//			List<ResRec> recList=resRecBiz.resSearchDeptFlowRec(sysUser.getDeptFlow(),startDate,endDate);
			List<DeptTeacherGradeInfo> recList=resGradeBiz.resSearchDeptFlowRec(sysUser.getDeptFlow(),startDate,endDate);
			Float sum=0f;
			Map<String, Float> heJiMap=new HashMap<String, Float>();
			if (recList!=null && recList.size()>0) {
				for (DeptTeacherGradeInfo resRec : recList) {
					Map<String,Object> gradeMap = resRecBiz.parseGradeXml(resRec.getRecContent());
					for (String key: gradeMap.keySet()) {
						Object o=gradeMap.get(key);
						if (o instanceof String) {
							continue;
						}
						Map<String,String> scoreMap=(Map<String,String>)o;
						Float value=heJiMap.get(key);
						if (value==null) {
							value=0f;
						}
						Float deValue= 0.0F;
						if(scoreMap.get("score") != null){
							deValue= Float.parseFloat(scoreMap.get("score"));
						}
						value=value+deValue;
						heJiMap.put(key, value);
					}
				}
				for (String key: heJiMap.keySet()) {
					Float value=heJiMap.get(key);
					value=value/recList.size();
					BigDecimal bd=new BigDecimal(value).setScale(1, BigDecimal.ROUND_HALF_UP);
					heJiMap.put(key, bd.floatValue());
					sum=sum+bd.floatValue();
				}
			}
			String cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
			ResAssessCfg search = new ResAssessCfg();
			search.setCfgCodeId(cfgCodeId);
			List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
			if(assessCfgList != null && !assessCfgList.isEmpty()){
				ResAssessCfg assessCfg = assessCfgList.get(0);
				model.addAttribute("assessCfg",assessCfg);
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
					for(Element te :titleElementList){
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for(Element ie : itemElementList){
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
			model.addAttribute("heJiMap", heJiMap);
			BigDecimal ii=new BigDecimal(sum).setScale(1, BigDecimal.ROUND_HALF_UP);
			sum=ii.floatValue();
			model.addAttribute("average", sum);
		}
		return "res/teacher/evaluateSearch";
	}

	/**
	 * 查看详情
	 */
	@RequestMapping(value="/checkDetails")
	public String checkDetails(Model model,String roleType,String userFlow,String startDate,String endDate) throws Exception{
		SysUser sysUser=GlobalContext.getCurrentUser();
		model.addAttribute("sysUser",sysUser);
		Map<String, String> map=new HashMap<String, String>();
		Map<String, Object> heJiMap=new HashMap<String, Object>();
		List<Map<String, String>> processRecList=null;
		Map<String,Map<String, Object>> gradeMaps=new HashMap<String, Map<String,Object>>();
		if (StringUtil.isNotBlank(startDate)) {
			startDate = DateUtil.getDate(startDate)+"000000";
			map.put("startDate",startDate);
		}
		if (StringUtil.isNotBlank(endDate)) {
			endDate = DateUtil.getDate(endDate)+"235959";
			map.put("endDate",endDate);
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
			map.put("teacherFlow",userFlow);
			processRecList=resGradeBiz.processRecRecTeacherMap(map);
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {
			map.put("deptFlow", sysUser.getDeptFlow());
			processRecList=resGradeBiz.processRecShMap(map);
		}
		final Map<String, Object> heJiMapTemp = heJiMap;
		if(null != processRecList && processRecList.size()>0){
			for (Map<String, String> recMap : processRecList) {
				Map<String,Object> gradeMap = resRecBiz.parseGradeXml(recMap.get("content"));
				gradeMaps.put(recMap.get("processFlow"), gradeMap);
				heJiMap.put(recMap.get("processFlow"),gradeMap.get("totalScore"));
			}
			java.util.Collections.sort(processRecList,new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> o1,Map<String, String> o2) {
					String o1Key=o1.get("processFlow");
					String o2Key=o2.get("processFlow");
					Object s1=heJiMapTemp.get(o1Key);
					Object s2=heJiMapTemp.get(o2Key);
					Float f1=0f;
					Float f2=0f;
					try {
						f1=Float.valueOf(s1.toString());
						f2=Float.valueOf(s2.toString());
					} catch (Exception e) {
                        logger.error("", e);
					}
					Float result=f2-f1;
					return result>0?1:result==0?0:-1;
				}
			});
		}
		model.addAttribute("processRecList", processRecList);
		List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
		String cfgCodeId =null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
			cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {
			cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		}
		ResAssessCfg search = new ResAssessCfg();
		search.setCfgCodeId(cfgCodeId);
		List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
		if(assessCfgList != null && !assessCfgList.isEmpty()){
			ResAssessCfg assessCfg = assessCfgList.get(0);
			model.addAttribute("assessCfg",assessCfg);
			Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if(titleElementList != null && !titleElementList.isEmpty()){
				for(Element te :titleElementList){
					ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					List<Element> itemElementList = te.elements("item");
					List<ResAssessCfgItemForm> itemFormList = null;
					if(itemElementList != null && !itemElementList.isEmpty()){
						itemFormList = new ArrayList<ResAssessCfgItemForm>();
						for(Element ie : itemElementList){
							ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
							itemForm.setId(ie.attributeValue("id"));
							itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
							itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
							itemFormList.add(itemForm);
						}
					}
					titleForm.setItemList(itemFormList);
					titleFormList.add(titleForm);
				}
				model.addAttribute("titleFormList", titleFormList);
			}
		}
		Map<String,Object> scoreSumMap=new HashMap<String, Object>();
		if(null !=  processRecList && processRecList.size()>0){
			for (Map<String, String> recMap : processRecList) {
				String operUserFlow = recMap.get("userFlow");
				int scoreSum=0;
				if(null != titleFormList && titleFormList.size()>0){
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : titleFormList) {
						if(null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size()>0){
							for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
								int s=Integer.parseInt("".equals(resAssessCfgItemForm.getScore())?"0":resAssessCfgItemForm.getScore());
								scoreSum+=s;
							}
						}
					}
				}
				scoreSumMap.put(operUserFlow, scoreSum);
			}
		}
		model.addAttribute("scoreSumMap", scoreSumMap);
		model.addAttribute("gradeMaps", gradeMaps);
		model.addAttribute("heJiMap", heJiMap);
		return "res/teacher/checkDetails";
	}

	@RequestMapping(value="/exportCheckDetails")
	public void exportCheckDetails(Model model,String recFlow,String roleType,
									 String userFlow,String startDate,String endDate,
									 HttpServletResponse response,HttpServletRequest request) throws Exception{
		SysUser sysUser=GlobalContext.getCurrentUser();
		model.addAttribute("sysUser",sysUser);
		Map<String, String> param=new HashMap<String, String>();
		Map<String, Object> heJiMap=new HashMap<String, Object>();
		List<Map<String, String>> processRecList=null;
		Map<String,Map<String, Object>> gradeMaps=new HashMap<String, Map<String,Object>>();
		if (StringUtil.isNotBlank(startDate)) {
			startDate = DateUtil.getDate(startDate)+"000000";
			param.put("startDate",startDate);
		}
		if (StringUtil.isNotBlank(endDate)) {
			endDate = DateUtil.getDate(endDate)+"235959";
			param.put("endDate",endDate);
		}
		if (StringUtil.isNotBlank(recFlow)) {
			param.put("recFlow",recFlow);
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
			param.put("teacherFlow",userFlow);
			processRecList=resGradeBiz.processRecRecTeacherMap(param);
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {
			param.put("deptFlow", sysUser.getDeptFlow());
			processRecList=resGradeBiz.processRecShMap(param);
		}
		model.addAttribute("processRecList", processRecList);
		String cfgCodeId =null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
			cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {
			cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		}
		//导出结果集合
		List<List<Map<String, String>>> exportInfoList = new ArrayList<>();
		List<String> fileNames = new ArrayList<>();

		String [] titles = {
				"itemName:            ",
				"itemScore:    ",
				"avgScore:     "
		};

		List<ResAssessCfgTitleForm> assessCfgList = assessCfgBiz.getParsedGrade(cfgCodeId);
		boolean isOneFile = false;
		int i=0;
		if(processRecList!=null && !processRecList.isEmpty()){
			if(processRecList.size() == 1){
				isOneFile = true;
			}
			Map<String,Float> scoreMap = new HashMap<String, Float>();
			for(Map<String,String> map : processRecList){
				i++;
				//单条结果导出集合
				List<Map<String, String>> oneInfoList = new ArrayList<>();

				String operUserFlow = map.get("operUserFlow")+map.get("recFlow");

                if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleType)) {
					Map<String,String> itemMap = new HashMap<>();
					itemMap.put("itemName","带教姓名");
					itemMap.put("itemScore",map.get("teacherUserName"));
					itemMap.put("avgScore","");
					oneInfoList.add(itemMap);
                } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleType)) {

					Map<String,String> itemMap = new HashMap<>();
					itemMap.put("itemName","科室名称");
					itemMap.put("itemScore",map.get("deptName"));
					itemMap.put("avgScore","");
					oneInfoList.add(itemMap);
				}
				Map<String,String> itemMap = new HashMap<>();
				itemMap.put("itemName","学员姓名");
				itemMap.put("itemScore",map.get("operUserName"));
				itemMap.put("avgScore","");
				oneInfoList.add(itemMap);
				String content = map.get("content");
				Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
				if(gradeMap!=null && !gradeMap.isEmpty()){
					for(String gk : gradeMap.keySet()){
						Object o = gradeMap.get(gk);
						Float score = 0f;
						if(o instanceof Map){
							Map<String,String> dataMap = (Map<String, String>) o;
							if(dataMap!=null){
								try {
									String scoreS = dataMap.get("score");
									score = Float.valueOf(scoreS);
								} catch (Exception e) {
                                    logger.error("", e);
								}

								putMapVal(scoreMap,operUserFlow+gk,score);
							}
						}else{
							try {
								String scoreS = (String) gradeMap.get("totalScore");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
                                logger.error("", e);
							}

							putMapVal(scoreMap,operUserFlow,score);
						}
					}

				}
				itemMap = new HashMap<>();
				itemMap.put("itemName","评分细则");
				itemMap.put("itemScore","标准分");
				itemMap.put("avgScore","总分");
				oneInfoList.add(itemMap);
				int sum=0;
				for (ResAssessCfgTitleForm resAssessCfgTitleForm : assessCfgList) {
					itemMap = new HashMap<>();
					itemMap.put("itemName",resAssessCfgTitleForm.getName());
					itemMap.put("itemScore","");
					itemMap.put("avgScore","");
					oneInfoList.add(itemMap);
					if(null != resAssessCfgTitleForm.getItemList() && resAssessCfgTitleForm.getItemList().size()>0) {
						for (ResAssessCfgItemForm resAssessCfgItemForm : resAssessCfgTitleForm.getItemList()) {
							itemMap = new HashMap<>();
							itemMap.put("itemName",resAssessCfgItemForm.getName());
							itemMap.put("itemScore",resAssessCfgItemForm.getScore());
							itemMap.put("avgScore",(scoreMap.get(operUserFlow+resAssessCfgItemForm.getId())==null?"":scoreMap.get(operUserFlow+resAssessCfgItemForm.getId()))+"");
							oneInfoList.add(itemMap);
							sum+=StringUtil.isBlank(resAssessCfgItemForm.getScore())?0:Integer.valueOf(resAssessCfgItemForm.getScore());
						}
					}
				}
				 itemMap = new HashMap<>();
				itemMap.put("itemName","总分");
				itemMap.put("itemScore",sum+"");
				itemMap.put("avgScore",(scoreMap.get(operUserFlow)==null?"":scoreMap.get(operUserFlow))+"");
				oneInfoList.add(itemMap);
				String fileName = map.get("operUserName")+map.get("deptName")+"("+map.get("schStartDate")+"~"+map.get("schEndDate")+")";
				if(isOneFile){
					if(StringUtil.isBlank(fileName))
						fileName="双向评价明细导出.xls";
					else
						fileName+=".xls";
					fileName = URLEncoder.encode(fileName, "UTF-8");
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
					response.setContentType("application/octet-stream;charset=UTF-8");
					ExcleUtile.exportSimpleExcleByObjs(titles, oneInfoList, response.getOutputStream());
				}else {
					if(StringUtil.isBlank(fileName))
						fileName="双向评价明细导出-"+i;
					fileNames.add(fileName);
					exportInfoList.add(oneInfoList);
				}
			}
		}
		if(!isOneFile) {
			String fileName = "双向评价明细导出";
			response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName+".zip", "UTF-8") + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			ExcleUtile.exportExcel(fileName,fileNames, titles, exportInfoList, response.getOutputStream(), request);
		}
	}

	private Map<String, Float> avg(List<Map<String,String>> recList){
		//均分Map
		Map<String,Float> avgMap = new HashMap<String, Float>();
		String count = "Count";
		String total = "Total";
		for(Map<String,String> map : recList){
			String key = map.get("key");

			putMapVal(avgMap,key+count,1f);

			String content = map.get("content");

			Map<String, Object> gradeMap = resRecBiz.parseGradeXml(content);
			if(gradeMap!=null && !gradeMap.isEmpty()){
				for(String gk : gradeMap.keySet()){
					Object o = gradeMap.get(gk);
					if(o instanceof Map){
						Map<String,String> dataMap = (Map<String, String>) o;
						if(dataMap!=null){
							Float score = 0f;
							try {
								String scoreS = dataMap.get("score");
								score = Float.valueOf(scoreS);
							} catch (Exception e) {
                                logger.error("", e);
							}

							putMapVal(avgMap,key+"_"+gk,score);
						}
					}else{
						Float score = 0f;
						try {
							String scoreS = (String)o;
							score = Float.valueOf(scoreS);
						} catch (Exception e) {
                            logger.error("", e);
						}

						putMapVal(avgMap,key+"_"+total,score);
					}
				}

			}
		}

		if(!avgMap.isEmpty()){
			Set<String> keySet = avgMap.keySet();
			for(String k : keySet){
				if(k!=null){
					int ki = k.indexOf("_");
					if(!(ki<0)){
						String dataKey = k.substring(0,ki);

						Float itemTotal = avgMap.get(k);
						if(itemTotal!=null){
							Float countUser = avgMap.get(dataKey+count);
							if(itemTotal!=0 && countUser!=0){
								Float result = itemTotal/countUser;
								result = new BigDecimal(result).setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
								avgMap.put(k,result);
							}
						}
					}
				}
			}
		}
		return  avgMap;
	}

	private void putMapVal(Map<String,Float> map,String key,Float val){
		if(map!=null){
			Float v = map.get(key);
			if(v==null){
				v = val;
			}else{
				v+=val;
			}
			v = new BigDecimal(v).setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
			map.put(key,v);
		}
	}



	/**
	 * 操作入科
	 * @param model
	 * @return
     */
	@RequestMapping("/inDept")
	public String inDept(String doctorFlow,String deptFlow,String doctorName,String deptName,String schStartDate,String schEndDate,Model model) throws IOException{

		List<SchProcessExt> processList = processBiz.searchProcessAndResultByDoctorFlow(doctorFlow,deptFlow);
		model.addAttribute("processList",processList);
		model.addAttribute("doctorFlow",doctorFlow);
		model.addAttribute("doctorName",java.net.URLDecoder.decode(doctorName,"UTF-8"));
		model.addAttribute("deptFlow",deptFlow);
		model.addAttribute("deptName",java.net.URLDecoder.decode(deptName,"UTF-8"));
		model.addAttribute("schStartDate",schStartDate);
		model.addAttribute("schEndDate",schEndDate);
		return "res/head/inDept";
	}

	/**
	 * 科秘操作 入科 新增
	 * @param model
	 * @return
	 */
	@RequestMapping("/addArrange")
	public String addArrange(String flag,String resultFlow,String doctorFlow, String deptFlow,String doctorName,String deptName,String schStartDate,String schEndDate,Model model) throws IOException{
		ResDoctorSchProcess process =null;
		if(StringUtil.isNotBlank(resultFlow)){
			process = processBiz.searchByResultFlow(resultFlow);
			model.addAttribute("resultFlow",resultFlow);
		}
		if(StringUtil.isNotBlank(flag)){
			model.addAttribute("flag",flag);
		}
		model.addAttribute("process",process);

		List<String> deptFlows = new ArrayList<>();
		deptFlows.add(deptFlow);
 		//获取指定医院科室下的轮转科室
		List<SchDept> schDeptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
		model.addAttribute("schDeptList",schDeptList);
		model.addAttribute("deptFlow",deptFlow);
		model.addAttribute("deptName",java.net.URLDecoder.decode(deptName, "UTF-8"));
		model.addAttribute("doctorFlow",doctorFlow);
		model.addAttribute("doctorName",java.net.URLDecoder.decode(doctorName,"UTF-8"));
		model.addAttribute("schStartDate",schStartDate);
		model.addAttribute("schEndDate",schEndDate);



		return "res/head/addArrange";
	}
	/**
	 * 获取带教老师和科主任
	 * @return
	 */
	@RequestMapping(value="/loadTeacherAndHead")
	@ResponseBody
	public Map<String,Object> loadTeacherAndHead(String deptFlow){
		Map<String,Object> data = null;
		data = new HashMap<String, Object>();
		if(StringUtil.isNotBlank(deptFlow)){
			String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
			if(StringUtil.isNotBlank(teacherRoleFlow)){
				List<SysUser> teacherList = userBiz.searchUserByDeptAndRole(deptFlow,teacherRoleFlow);
				data.put("teacherSeller",teacherList);
			}

			String headRoleFlow = InitConfig.getSysCfg("res_head_role_flow");
			if(StringUtil.isNotBlank(headRoleFlow)){
				List<SysUser> headList = userBiz.searchUserByDeptAndRole(deptFlow,headRoleFlow);
				data.put("headSeller",headList);
			}
		}
		return data;
	}

	/**
	 * 保存 科秘 操作学员入科的信息
	 * @return
     */
	@RequestMapping("/save")
	@ResponseBody
	public String save(SchArrangeResult arrangeResult,ResDoctorSchProcess process) throws ParseException{

		int count = schArrangeResultBiz.saveInDept(arrangeResult,process);

        if (count > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 删除 科秘 操作学员入科的信息
	 * @return
	 */
	@RequestMapping("/del")
	@ResponseBody
	public String del(String resultFlows){

		int count = schArrangeResultBiz.delInDept(resultFlows);
		if(count>0){
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}

	/**
	 * @return
	 */
	@RequestMapping("/loadDept")
	@ResponseBody
	public Object loadDept(String orgFlow){
		List<SysDept> deptList=deptBiz.searchDeptByOrg(orgFlow);
		return deptList;
	}
}
