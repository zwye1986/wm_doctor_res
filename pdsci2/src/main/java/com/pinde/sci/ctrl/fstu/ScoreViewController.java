package com.pinde.sci.ctrl.fstu;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IAcaScoreBiz;
import com.pinde.sci.biz.fstu.IAcademicBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.edu.MajorCourseForm;
import com.pinde.sci.form.fstu.ScoreManageForm;
import com.pinde.sci.model.FstuScoreMainExt;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/fstu/score")
public class ScoreViewController extends GeneralController{
	@Autowired
	private IAcaScoreBiz scoreBiz;

	@RequestMapping(value = "/scoreView")
	public String scoreView(String role,FstuScoreMain scoreMain,Model model,Integer currentPage,HttpServletRequest request,
	String startScore,String endScore,String orgName,String year,String typeId){
		List<FstuScoreConfig> scoreConfigs = scoreBiz.searchScoreCfgList(null);
		List<String> projTypeNames = new ArrayList<>();
		if(scoreConfigs.size()>0){
			for(FstuScoreConfig scoreConfig:scoreConfigs ){
				String projTypeName = scoreConfig.getProjTypeName();
				if(!projTypeNames.contains(projTypeName)){
					projTypeNames.add(projTypeName);
				}
			}
		}
		if(StringUtil.isNotBlank(scoreMain.getFirstProjTypeId())){
			String val = scoreMain.getFirstProjTypeId().split(",")[1];
			scoreMain.setFirstProjTypeId(val);
		}
		model.addAttribute("projTypeNames",projTypeNames);

		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("sessionNumber",scoreMain.getSessionNumber());
		paramMap.put("userName",scoreMain.getUserName());
		paramMap.put("userCode",scoreMain.getUserCode());
		paramMap.put("orgName",orgName);
		paramMap.put("startScore",startScore);
		paramMap.put("endScore",endScore);
		paramMap.put("userDeptName",scoreMain.getUserDeptName());
		paramMap.put("firstProjTypeName",scoreMain.getFirstProjTypeName());
		paramMap.put("firstProjTypeId",scoreMain.getFirstProjTypeId());
		paramMap.put("auditStatusId",scoreMain.getAuditStatusId());
		paramMap.put("firstScoreTypeId",scoreMain.getFirstScoreTypeId());
		if("header".equals(role)){
			paramMap.put("deptFlow",GlobalContext.getCurrentUser().getDeptFlow());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> scoreMains = scoreBiz.scoreMains(paramMap);
		model.addAttribute("scoreMains",scoreMains);

		//图表数据
//		if(StringUtil.isBlank(year)){
//			year = DateUtil.getYear();
//			model.addAttribute("year",year);
//		}

		Map<String,Double> resultMap = scoreBiz.calculation(year,typeId);
		model.addAttribute("resultMap",resultMap);
		return "fstu/scom/list";
	}

	@RequestMapping(value = "/export")
	public void export(FstuScoreMain scoreMain,HttpServletResponse response,String startScore,String endScore,String orgName,String year,String typeId) throws Exception {
		if(StringUtil.isNotBlank(scoreMain.getFirstProjTypeId())){
			String val = scoreMain.getFirstProjTypeId().split(",")[1];
			scoreMain.setFirstProjTypeId(val);
		}
		Map<String,String> paramMap = new HashMap<>();
		paramMap.put("sessionNumber",scoreMain.getSessionNumber());
		paramMap.put("userName",scoreMain.getUserName());
		paramMap.put("orgName",orgName);
		paramMap.put("startScore",startScore);
		paramMap.put("endScore",endScore);
		paramMap.put("userDeptName",scoreMain.getUserDeptName());
		paramMap.put("firstProjTypeName",scoreMain.getFirstProjTypeName());
		paramMap.put("firstProjTypeId",scoreMain.getFirstProjTypeId());
		paramMap.put("auditStatusId",scoreMain.getAuditStatusId());
		paramMap.put("firstScoreTypeId",scoreMain.getFirstScoreTypeId());
		List<Map<String,Object>> scoreMains = scoreBiz.scoreMains(paramMap);
		List<FstuScoreMainExt> scoreMainExts = new ArrayList<>();
		if(scoreMains!=null&&scoreMains.size()>0){
			for(Map<String,Object> score:scoreMains){
				FstuScoreMainExt scoreMainExt = new FstuScoreMainExt();
				scoreMainExt.setSessionNumber((String)score.get("sessionNumber"));
				scoreMainExt.setUserName((String)score.get("userName"));
				scoreMainExt.setOrgName((String)score.get("orgName"));
				scoreMainExt.setUserDeptName((String)score.get("userDeptName"));
				scoreMainExt.setFirstScoreTypeName((String)score.get("firstScoreTypeName"));
				scoreMainExt.setFirstProjTypeName((String)score.get("firstProjTypeName"));
				scoreMainExt.setFirstScoreItemName((String)score.get("firstScoreItemName"));
				scoreMainExt.setFirstExecutionName((String)score.get("firstExecutionName"));
				scoreMainExt.setMyScore((String)score.get("myScore"));
				scoreMainExt.setYearScore((String)score.get("yearScore"));
				scoreMainExts.add(scoreMainExt);
			}
		}
		String headLines[] = {"学分项目统计清单"};
		String[] titles = new String[]{
				"sessionNumber:年份",
				"userName:学员姓名",
				"orgName:单位",
				"userDeptName:部门/科室",
				"firstScoreTypeName:学分类别",
				"firstProjTypeName:项目大类",
				"firstScoreItemName:评分项",
				"firstExecutionName:完成情况",
				"myScore:单项最终分值",
				"yearScore:年度总分"
		};
		String fileName = "学分项目统计清单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, scoreMainExts, response.getOutputStream());
	}
}