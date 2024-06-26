package com.pinde.sci.ctrl.srm;

import com.alibaba.fastjson.JSON;
import com.pinde.core.jspform.Page;
import com.pinde.core.jspform.PageGroup;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.JspFormUtil;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.SrmExpertProjExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 立项阶段的controller
 * @author shenzhen
 *
 */
@Controller
@RequestMapping("/srm/proj/approve")
public class ProjApproveController extends GeneralController{
	
	@Autowired
	private IProjApproveBiz projApproveBiz;
	@Autowired
	private IPubProjBiz pubProjBiz;	
	@Autowired
	private IFundSchemeBiz fundSchemeBiz;
	@Autowired
	private IProjPageBiz projPageBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IExpertGroupProjBiz evalSetBiz;
	@Autowired
	private IExpertProjBiz expertProjBiz;
	@Autowired
	private IExpertGroupBiz experGroupBiz;
	@Autowired
	private IFundInfoBiz fundInfoBiz;
	@Autowired
	private IAchScoreBiz scoreBiz;
		
	/**
	 * 显示立项管理的项目列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list/{projListScope}/{projCateScope}")
	public String list(@PathVariable String projListScope,@PathVariable String projCateScope, PubProj proj ,String startYear,String endYear,String defaultTerm, Integer currentPage ,HttpServletRequest request,  Model model){
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		proj.setProjCategoryId(getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE).toString());
		proj.setProjStageId(ProjStageEnum.Approve.getId());//申报阶段
		proj.setProjStatusId(ProjApproveStatusEnum.Approving.getId());
		if(ProjCategroyEnum.Bj.getId().equals(projCateScope)){
			proj.setProjStageId(ProjStageEnum.Award.getId());//申报阶段
		}
		//设置默认条件
		if(StringUtil.isNotBlank(defaultTerm)){
			model.addAttribute("defaultTerm",defaultTerm);
			proj.setProjYear(DateUtil.getYear());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<PubProj> projList = projApproveBiz.searchApproveProjList(proj,startYear,endYear);
		model.addAttribute("projList" , projList);
		//申报单位
		if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){
			//加载所有同级单位
			List<SysOrg> orgList = this.orgBiz.searchOrgListByChargeOrgFlow(proj.getChargeOrgFlow());
			model.addAttribute("orgList",orgList);
		}else if(StringUtil.isNotBlank(proj.getChargeOrgFlow())){
			List<SysOrg> orgList = this.orgBiz.searchOrgListByChargeOrgFlow(proj.getChargeOrgFlow());
			model.addAttribute("orgList",orgList);
		}
		List<SysOrg> chargeOrgList = this.orgBiz.searchChargeOrg();
		model.addAttribute("chargeOrgList",chargeOrgList);
		
		return "srm/proj/approve/list_"+projCateScope;
	}
	
	@RequestMapping(value="/exportExcel/{projListScope}/{projCateScope}")
	public void exportExcel(@PathVariable String projListScope,@PathVariable String projCateScope, PubProj proj, HttpServletRequest request, HttpServletResponse response) throws Exception{
		String[] titles = new String[]{
			"projYear:年份",
			"projTypeName:项目类别",
			"projName:项目名称",
			"projNo:立项编号",
			"projStartTime:开始时间",
			"projEndTime:结束时间",
			"applyUserName:项目负责人",
			"projStageName:当前阶段",
			"projStatusName:当前状态",
			"applyOrgName:申报单位"
		};
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		proj.setProjCategoryId(getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE).toString());
		proj.setProjStageId(ProjStageEnum.Approve.getId());//申报阶段
		proj.setProjStatusId(ProjApproveStatusEnum.Approving.getId());
		List<PubProj> projList = projApproveBiz.searchApproveProjList(proj,null,null);
		String fileName = "项目导出表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcle(titles, projList, PubProj.class, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	
	
	/**
	 * 显示立项界面
	 * @return
	 */
	@RequestMapping("/setUp")
	public String setUp(@RequestParam(value="projFlow")String projFlow , Model model){
		PubProj proj = this.pubProjBiz.readProject(projFlow);
		model.addAttribute("proj" , proj);
		SysUser currUser = GlobalContext.getCurrentUser();
		List<SysDept> depts = deptBiz.searchDeptByOrg(currUser.getOrgFlow());
		model.addAttribute("depts", depts);
		//该项目类型立项是否需要表单
		String pageGroupName = InitConfig.configMap.get(ProjRecTypeEnum.SetUp.getId()).get(proj.getProjTypeId());
		PageGroup pageGroup = InitConfig.projSetUpPageMap.get(pageGroupName);
		if(pageGroup!=null){
			Page page = pageGroup.getPageMap().get(pageGroup.getFirstPage());
			String setupFormPath = page.getJsp();
			model.addAttribute("setupFormPath" , setupFormPath);
		}
		
		SrmExpertProjEval evalSet = this.evalSetBiz.searchSrmExpertGroupProjByProjFlowAndEvaluationId(projFlow, EvaluationEnum.ApproveEvaluation.getId());
		model.addAttribute("evalSet" , evalSet);
		if(evalSet!=null){
			if(EvaluationWayEnum.MeetingWay.getId().equals(evalSet.getEvaluationWayId())){
				//如果是会议评审 需要查询会议的信息
				SrmExpertGroup meeting = this.experGroupBiz.readSrmExpertGroup(evalSet.getGroupFlow());
				model.addAttribute("meeting" , meeting);
			}
			//根据evalSetFlow 查询评审涉及到的专家和得分
			String evalSetFlow = evalSet.getEvalSetFlow();
			SrmExpertProj expertProj = new SrmExpertProj();
			expertProj.setEvalSetFlow(evalSetFlow);
			List<SrmExpertProjExt> expertProjList = this.expertProjBiz.searchExpertProjExtAndUserExt(expertProj);
			Map<String,Integer> expertMap = new HashMap<>();
			//未评数量
			int noEvalCount = 0;
			for(SrmExpertProjExt expertProjExt : expertProjList){

				if(StringUtil.isNotBlank(expertProjExt.getScoreResultId())) {
					if (expertMap.containsKey(expertProjExt.getScoreResultId())) {
						int a = expertMap.get(expertProjExt.getScoreResultId());
						expertMap.put(expertProjExt.getScoreResultId(), a + 1);
					} else {
						expertMap.put(expertProjExt.getScoreResultId(), 1);
					}
				}else{
					noEvalCount++;
				}
			}
			expertMap.put("noEvalCount",noEvalCount);
			model.addAttribute("expertProjList" , expertProjList);
			model.addAttribute("expertMap" , expertMap);

		}
		String projCateScope = (String) getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE);
		if(ProjCategroyEnum.Gl.getId().equals(projCateScope)){//项目管理类型 查询经费方案
			SrmProjSourceScheme sourceScheme = new SrmProjSourceScheme();
			if(StringUtil.isNotBlank(proj.getProjDeclarerFlow()) && StringUtil.isNotBlank(proj.getProjSecondSourceId())){
				sourceScheme.setProjFirstSourceId(proj.getProjDeclarerFlow());
				sourceScheme.setProjSecondSourceId(proj.getProjSecondSourceId());
				List<SrmProjSourceScheme> sourceSchemeList = fundSchemeBiz.searchSourceScheme(sourceScheme);
				List<String> schemeFlowList = new ArrayList<>();
				for(SrmProjSourceScheme scheme : sourceSchemeList){
					if(!schemeFlowList.contains(scheme.getSchemeFlow())){
						schemeFlowList.add(scheme.getSchemeFlow());
					}
				}
				if(schemeFlowList.size()==0){
					schemeFlowList.add("");
				}
				SrmFundScheme srmFundScheme = new SrmFundScheme();
				srmFundScheme.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<SrmFundScheme> schemeList = fundSchemeBiz.searchFundSchemeByFlows(srmFundScheme,schemeFlowList);

				model.addAttribute("schemeList",schemeList);
			}
		}
		return "srm/proj/approve/setUp_"+projCateScope;
		
	}
	
	@RequestMapping("/saveSetUp")
	@ResponseBody
	public String saveSetUp(String jsondata, PubProj proj , String pageName , HttpServletRequest request , SrmProjFundInfo fundInfo){
		List<PubProjAuthor> authorList = JSON.parseArray(jsondata,PubProjAuthor.class);
		String projCateScope = StringUtil.defaultString((String)getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE));
		String remark = "";
		String result = request.getParameter("result");
		proj.setProjCategoryId(projCateScope);
		proj.setProjStageId(ProjStageEnum.Approve.getId());
		proj.setProjStageName(ProjStageEnum.Approve.getName());
		if(StringUtil.isNotBlank(proj.getPlanTypeId())){
			proj.setPlanTypeName(DictTypeEnum.getDictName(DictTypeEnum.PlanCategory,proj.getPlanTypeId()));
		}
		if(GlobalConstant.FLAG_Y.equals(result)){
			//检查立项编号是否唯一
			List<PubProj> projList = projApproveBiz.getPubProjByProjNo(proj.getProjNo());
			if(projList!=null && !projList.isEmpty()){
				return GlobalConstant.FLAG_N;
			}
			proj.setProjStatusId(ProjApproveStatusEnum.Approve.getId());
			proj.setProjStatusName(ProjApproveStatusEnum.Approve.getName());
			remark = ProcessRemarkEnum.ApproveAgree.getName();
			if(StringUtil.isNotBlank(proj.getProjCategoryId()) && ProjCategroyEnum.Bj.getId().equals(projCateScope)){//科技报奖无立项流程（确认发奖）
				proj.setProjStageId(ProjStageEnum.Award.getId());
				proj.setProjStageName(ProjStageEnum.Award.getName());
				proj.setProjStatusName(ProjApproveStatusEnum.AwardPrizes.getName());
				proj.setProjStatusId(ProjApproveStatusEnum.AwardPrizes.getId());
				remark = ProcessRemarkEnum.AwardPrizes.getName();
			}
		}else if(GlobalConstant.FLAG_N.equals(result)){
			proj.setProjStatusId(ProjApproveStatusEnum.NotApprove.getId());
			proj.setProjStatusName(ProjApproveStatusEnum.NotApprove.getName());
			remark = ProcessRemarkEnum.ApproveNotAgree.getName();
			if(StringUtil.isNotBlank(proj.getProjCategoryId()) && ProjCategroyEnum.Bj.getId().equals(projCateScope)){//科技报奖无立项流程（不发奖）
				proj.setProjStageId(ProjStageEnum.Award.getId());
				proj.setProjStageName(ProjStageEnum.Award.getName());
				proj.setProjStatusId(ProjApproveStatusEnum.NoPrizes.getId());
				proj.setProjStatusName(ProjApproveStatusEnum.NoPrizes.getName());
				remark = ProcessRemarkEnum.NoPrizes.getName();
			}
		}
		String sug = (String)request.getParameter("sug");
		
		String setUpXml = "";
		PubProj exitProj = this.pubProjBiz.readProject(proj.getProjFlow());
		Page page = _getPage(pageName , exitProj.getProjTypeId());
		if(page!=null){
			Map<String , String[]> dataMap = JspFormUtil.getParameterMap(request);
			setUpXml = JspFormUtil.updateXmlStr("",page, dataMap);
		}
		this.projApproveBiz.setUp(proj , remark , sug , setUpXml,fundInfo,authorList);


		return GlobalConstant.FLAG_Y;
	}
	
	private Page _getPage(String pageName , String typeId){
		Map<String , String> pageProjTypeMap =  InitConfig.configMap.get(ProjRecTypeEnum.SetUp.getId());
		if(pageProjTypeMap!=null && !pageProjTypeMap.isEmpty()){
			String pageGroupName = pageProjTypeMap.get(typeId);
			if(pageGroupName!=null){
				PageGroup pageGroup = InitConfig.projSetUpPageMap.get(pageGroupName);
				if(pageGroup!=null){
					Page page = pageGroup.getPageMap().get(pageName);
					return page;
				}
			}
		}
		return null;
		
	}
	
	/**
	 * 下拨列表
	 * @return
	 */
	@RequestMapping("/fundPlanList")
	public String fundPlanList(PubProj proj , Integer currentPage ,HttpServletRequest request, Model model){
		//SysUser currUser = GlobalContext.getCurrentUser();
		//SysOrg sysOrg = new SysOrg();
		//申报单位
		if(StringUtil.isNotBlank(proj.getApplyOrgFlow())){
		    //加载所有同级单位
			List<SysOrg> orgList = this.orgBiz.searchOrgListByChargeOrgFlow(proj.getChargeOrgFlow());
			model.addAttribute("orgList",orgList);
		}else if(StringUtil.isNotBlank(proj.getChargeOrgFlow())){
		    List<SysOrg> orgList = this.orgBiz.searchOrgListByChargeOrgFlow(proj.getChargeOrgFlow());
			model.addAttribute("orgList",orgList);
		}
		List<SysOrg> chargeOrgList = this.orgBiz.searchChargeOrg();
		model.addAttribute("chargeOrgList",chargeOrgList);
		
		PageHelper.startPage(currentPage, getPageSize(request));
		List<PubProj> projList =  this.projApproveBiz.searchFundPlanList(proj);
		model.addAttribute("projList" , projList);
		return "srm/proj/approve/fundPlanList";
	}
	
	/**
	 * 编辑拨款计划
	 * @param projFlow
	 * @param model
	 * @return
	 */
	@RequestMapping("/editFundPlan")
	public String editFundPlan(PubProjFundPlan  projFundPlan,  Model model){
		String projFlow = projFundPlan.getProjFlow();
		PubProj proj = pubProjBiz.readProject(projFlow);
		model.addAttribute("proj" , proj);
		List<PubProjFundPlan> projFundPlanList = this.projApproveBiz.searchProjFundPlanList(projFundPlan);
		if(projFundPlanList != null && !projFundPlanList.isEmpty()){
			List<PubProjFundPlan> yearAmountList = new ArrayList<PubProjFundPlan>();
			for(PubProjFundPlan fp : projFundPlanList){
				if(ProjFundPlanTypeEnum.SumAmount.getId().equals(fp.getPlanTypeId())){
					model.addAttribute("sumAmount", fp);
				}
				if(ProjFundPlanTypeEnum.MatchingAmount.getId().equals(fp.getPlanTypeId())){
					model.addAttribute("matchingAmount", fp);
				}
				if(ProjFundPlanTypeEnum.YearAmount.getId().equals(fp.getPlanTypeId())){
					yearAmountList.add(fp);
				}
			}
			model.addAttribute("yearAmountList", yearAmountList);
		}
		model.addAttribute("projFundPlanList" , projFundPlanList);
		return "srm/proj/approve/editFundPlan";
	}
	
	/**
	 * 保存项目拨款计划
	 * @param fundPlan
	 * @return
	 */
	@RequestMapping("/saveFundPlan")
	@ResponseBody
	public String saveFundPlan(HttpServletRequest request){
		String flag = request.getParameter("flag");
		List<PubProjFundPlan> fundPlans = new ArrayList<PubProjFundPlan>();
		PubProj proj = new PubProj();
		String projFlow = request.getParameter("projFlow");
		proj.setProjStageId(ProjStageEnum.Approve.getId());
		proj.setProjStageName(ProjStageEnum.Approve.getName());
		proj.setProjFlow(projFlow);
		if(ProjApproveStatusEnum.Save.getId().equals(flag)){
			proj.setProjStatusId(ProjApproveStatusEnum.Save.getId());
			proj.setProjStatusName(ProjApproveStatusEnum.Save.getName());
		}else if(ProjApproveStatusEnum.Confirm.getId().equals(flag)){
			proj.setProjStatusId(ProjApproveStatusEnum.Confirm.getId());
			proj.setProjStatusName(ProjApproveStatusEnum.Confirm.getName());
		}
		
		String sumAmount = request.getParameter("sumAmount");
		if(StringUtil.isBlank(sumAmount)){
			sumAmount = "0.0";
		}
		PubProjFundPlan sumFundPlan = new PubProjFundPlan();
		sumFundPlan.setProjFlow(projFlow);
		sumFundPlan.setAmount(new BigDecimal(sumAmount));
		sumFundPlan.setPlanTypeId(ProjFundPlanTypeEnum.SumAmount.getId());
		sumFundPlan.setPlanTypeName(ProjFundPlanTypeEnum.SumAmount.getName());
		fundPlans.add(sumFundPlan);
		
		String matchingAmount = request.getParameter("matchingAmount");
		if(StringUtil.isBlank(matchingAmount)){
			matchingAmount = "0.0";
		}
		PubProjFundPlan govFundPlan = new PubProjFundPlan();
		govFundPlan.setProjFlow(projFlow);
		govFundPlan.setAmount(new BigDecimal(matchingAmount));
		govFundPlan.setPlanTypeId(ProjFundPlanTypeEnum.MatchingAmount.getId());
		govFundPlan.setPlanTypeName(ProjFundPlanTypeEnum.MatchingAmount.getName());
		fundPlans.add(govFundPlan);
		
		String[] yearAmounts = request.getParameterValues("yearAmount");
		PubProjFundPlan yearFundPlan;
		for(int i =0 ; i<yearAmounts.length;i++){
			String yearAmount = yearAmounts[i];
			yearFundPlan = new PubProjFundPlan();
			yearFundPlan.setPlanTypeId(ProjFundPlanTypeEnum.YearAmount.getId());
			yearFundPlan.setPlanTypeName(ProjFundPlanTypeEnum.YearAmount.getName());
			yearFundPlan.setYear(String.valueOf(i));
			yearFundPlan.setProjFlow(projFlow);
			if(StringUtil.isBlank(yearAmount)){
				yearFundPlan.setAmount(null);
			}else{
				yearFundPlan.setAmount(new BigDecimal(yearAmount));
			}
			fundPlans.add(yearFundPlan);
		}
		this.projApproveBiz.addFundPlan(proj, fundPlans , flag);
		return GlobalConstant.OPRE_SUCCESSED;
	}
	
	@RequestMapping(value={"/checkProjNo"},method={RequestMethod.GET})
	@ResponseBody
	public String checkProjNo(String projNo,  Model model){
		if(StringUtil.isNotBlank(projNo)){
			List<PubProj> projList = projApproveBiz.getPubProjByProjNo(projNo);
			if(projList != null && !projList.isEmpty()){
				return GlobalConstant.FLAG_N;
			}else{
				return GlobalConstant.FLAG_Y;
			}
		}
		return null;
	}
	//卫计委通过后，撤销-申报重申
	@RequestMapping("/reAuditOption")
	@ResponseBody
	public String reAuditOption(String projFlow){
		if(StringUtil.isNotBlank(projFlow)){
			return projApproveBiz.reAuditOption(projFlow);
		}
		return GlobalConstant.OPRE_FAIL;
	}
}
