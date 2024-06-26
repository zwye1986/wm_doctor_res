package com.pinde.sci.ctrl.srm;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.JspFormUtil;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.form.srm.SrmExportExcel;
import com.pinde.sci.form.srm.YhXjsxxmExportForm;
import com.pinde.sci.form.srm.YhXjsxxmForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.ListToExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/srm/proj/search")
public class ProjSearchController extends GeneralController {
	@Autowired
	private IProjSearchBiz projSeeBiz;
	@Autowired
	private IPubProjBiz pubProjBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IProjRecBiz pubProjRecBiz;
	@Autowired
	private IFundInfoBiz fundInfoBiz;
	@Autowired
	private IProjProcessBiz projProcessBiz;

	@RequestMapping(value = "/list/{projListScope}/{projCateScope}")
	public String projList2(@PathVariable String projListScope,String startYear,String endYear,String defaultTerm,
			@PathVariable String projCateScope, PubProj proj,
			String flag,Integer currentPage ,HttpServletRequest request, Model model) {
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
		
		SysUser currUser = GlobalContext.getCurrentUser();
		List<PubProj> projList = new ArrayList<PubProj>();
		//手动设置阶段 此时阶段是申报阶段
		//手动设置项目类型
		proj.setProjCategoryId(projCateScope);
		if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL .equals(projListScope)){
			proj.setApplyOrgFlow(currUser.getOrgFlow());
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE .equals(projListScope)){
			proj.setChargeOrgFlow(currUser.getOrgFlow());
			List<SysOrg> orgList = this.orgBiz.searchOrgListByChargeOrgFlow(currUser.getOrgFlow());
			model.addAttribute("orgList",orgList);
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL .equals(projListScope)){
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
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(projListScope)){//科主任角色
			if(StringUtil.isNotBlank(currUser.getDeptFlow())){
				proj.setApplyDeptFlow(currUser.getDeptFlow());
			}else{
				proj.setApplyDeptFlow(" ");
			}
		}
		if(StringUtil.isNotBlank(defaultTerm)){//默认条件
			model.addAttribute("defaultTerm",defaultTerm);
			proj.setProjYear(DateUtil.getYear());
		}
		/*要求 项目查询功能前面阶段应该包含后一阶段的数据，比如查询条件设置为立项阶段，那只要是经过立项的数据都应该显示，
		而不是只显示当前处于立项阶段的数据，需要有一个往后包含的关系，项目状态仍然跟着项目阶段走 */
		List<String> stageIdNotInList = new ArrayList<>();
		if(StringUtil.isBlank(proj.getProjStatusId())){
			 if(ProjStageEnum.Approve.getId().equals(proj.getProjStageId())){
				 //非申报阶段
				 stageIdNotInList.add(ProjStageEnum.Apply.getId());
			}else if(ProjStageEnum.Contract.getId().equals(proj.getProjStageId())){
				 //非申报、立项阶段
				 stageIdNotInList.add(ProjStageEnum.Apply.getId());
				 stageIdNotInList.add(ProjStageEnum.Approve.getId());
			}else if(ProjStageEnum.Schedule.getId().equals(proj.getProjStageId())){
				//非申报、立项、合同阶段
				 stageIdNotInList.add(ProjStageEnum.Apply.getId());
				 stageIdNotInList.add(ProjStageEnum.Approve.getId());
				 stageIdNotInList.add(ProjStageEnum.Contract.getId());
			}else if(ProjStageEnum.Complete.getId().equals(proj.getProjStageId())){
				//非申报、立项、合同、进展阶段
				 stageIdNotInList.add(ProjStageEnum.Apply.getId());
				 stageIdNotInList.add(ProjStageEnum.Approve.getId());
				 stageIdNotInList.add(ProjStageEnum.Contract.getId());
				 stageIdNotInList.add(ProjStageEnum.Schedule.getId());
			}
			//清除状态
			proj.setProjStageId("");
		}
		if(StringUtil.isNotBlank(flag)){
			PageHelper.startPage(currentPage, getPageSize(request));
			projList = projSeeBiz.searchProj(proj,stageIdNotInList,null,startYear,endYear);
			//projList = projSeeBiz.searchProjByprojListScope(proj,projListScope);
			model.addAttribute("projList", projList);
		}
		return "srm/proj/search/list_" + projCateScope;
	}
	
	
	/**
	 * 导出Excel
	 * @param projListScope
	 * @param projCateScope
	 * @param proj
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("/exportExcel/{projListScope}/{projCateScope}")
	public void exportExcel(@PathVariable String projListScope, @PathVariable String projCateScope, PubProj proj, HttpServletResponse response) throws IOException, Exception{
		String[] titles = new String[]{
			"proj.projYear:年份",
			"proj.applyOrgName:单位",
			"proj.projName:项目名称",
			"proj.applyUserName:项目负责人",
			"proj.applyDeptName:科室",
			"proj.projStartTime:开始时间",
			"proj.projEndTime:结束时间",
			"proj.projTypeName:项目类别",
			"proj.projNo:立项编号",
			"proj.applyUserName:项目负责人",
			"projInfoForm.technologyLevel:技术水平",
			"projInfoForm.exampleCount1:外请专家完成，项目组人员协助例数",
			"projInfoForm.exampleCount2:专家现场指导，项目组独立完成例数",
			"projInfoForm.exampleCount3:项目组独立完成技术成熟例数",
			"projInfoForm.selfGradeAmount:自评分合计"
		};
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
		
		SysUser currUser = GlobalContext.getCurrentUser();
		List<PubProj> searchList = new ArrayList<PubProj>();
		proj.setProjCategoryId(projCateScope);
		if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL .equals(projListScope)){
			proj.setApplyOrgFlow(currUser.getOrgFlow());
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE .equals(projListScope)){
			proj.setChargeOrgFlow(currUser.getOrgFlow());
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL .equals(projListScope)){
		}
		//searchList = projSeeBiz.searchProj(proj);
		searchList = projSeeBiz.searchProjByprojListScope(proj,projListScope);
		List<YhXjsxxmExportForm> exportFormList = new ArrayList<YhXjsxxmExportForm>();
		if(searchList != null && !searchList.isEmpty()){
			for(PubProj p :searchList){
				//非申报阶段
				if(!ProjStageEnum.Apply.getId().equals(p.getProjStageId())){
					// 查询申报书REC记录
					PubProjRec projRec = new PubProjRec();
					projRec.setProjFlow(p.getProjFlow());
					projRec.setProjStageId(ProjStageEnum.Apply.getId());
					projRec = this.pubProjRecBiz.searchProjRecWithProjInfo(projRec);
					if(projRec != null){
						String recContent = projRec.getRecContent();
						if(StringUtil.isNotBlank(recContent)){
							YhXjsxxmExportForm exportForm = new YhXjsxxmExportForm();
							YhXjsxxmForm projInfoForm =  new YhXjsxxmForm();
							Map<String , Object> resultMap = JspFormUtil.parseXmlStr(recContent);
							String technologyLevel = (String) resultMap.get("technologyLevel");
							String exampleCount1 = (String) resultMap.get("exampleCount1");
							String exampleCount2 = (String) resultMap.get("exampleCount2");
							String exampleCount3 = (String) resultMap.get("exampleCount3");
							String selfGradeAmount = (String) resultMap.get("selfGradeAmount");
							projInfoForm.setTechnologyLevel(technologyLevel);
							projInfoForm.setExampleCount1(exampleCount1);
							projInfoForm.setExampleCount2(exampleCount2);
							projInfoForm.setExampleCount3(exampleCount3);
							projInfoForm.setSelfGradeAmount(selfGradeAmount);
							exportForm.setProj(p);
							exportForm.setProjInfoForm(projInfoForm);
							exportFormList.add(exportForm);
						}
					}
				}
			}
		}
		String fileName = "项目导出表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	    ExcleUtile.exportSimpleExcleByObjs(titles, exportFormList, response.getOutputStream());

	}

	/**
	 * 江苏科研项目信息导出Excel
	 * @param projListScope
	 * @param projCateScope
	 * @param proj
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	@RequestMapping("/exportExcelJs/{projListScope}/{projCateScope}")
	public void exportExcelJs(@PathVariable String projListScope, @PathVariable String projCateScope, PubProj proj,String startYear,String endYear, HttpServletResponse response) throws IOException, Exception{
		String[] titles;
		if("ky".equals(projCateScope)){
			titles = new String[]{
					"proj.projName:项目名称",
					"proj.projTypeName:项目类别",
					"proj.applyUserName:项目负责人",
					"proj.applyOrgName:申报单位",
					"proj.subjName:学科代码",
					"proj.acceptNumber:受理编号"
			};

		} else if("gl".equals(projCateScope)) {
			titles = new String[]{
					"proj.projYear:年份",
					"proj.projNo:课题编号",
					"proj.accountNo:课题账号",
					"proj.projTypeName:项目类别",
					"proj.applyUserName:项目负责人",
					"proj.applyDeptName:科室",
					"proj.projDeclarer:一级来源",
					"proj.projStartTime:起始时间",
					"proj.projEndTime:结束时间"

			};
		}else {
			titles = new String[]{
					"proj.projName:项目名称",
					"proj.projTypeName:项目类别",
					"proj.applyUserName:项目负责人",
					"proj.applyOrgName:申报单位",
					"projProcess.processRemark:审核意见"
			};
		}
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);

		SysUser currUser = GlobalContext.getCurrentUser();
		List<PubProj> searchList = new ArrayList<PubProj>();
		proj.setProjCategoryId(projCateScope);
		if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL .equals(projListScope)){
			proj.setApplyOrgFlow(currUser.getOrgFlow());
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE .equals(projListScope)){
			proj.setChargeOrgFlow(currUser.getOrgFlow());
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL .equals(projListScope)){
		}
		searchList = projSeeBiz.searchProj(proj,null,null,startYear,endYear);
		//searchList = projSeeBiz.searchProjByprojListScope(proj,projListScope);
		List<SrmExportExcel> exportExcelList = new ArrayList<SrmExportExcel>();
		if(searchList != null && !searchList.isEmpty()){
			for(PubProj p :searchList){
				SrmExportExcel exportExcel = new SrmExportExcel();
				PubProjProcess projProcess = new PubProjProcess();
				projProcess.setProjFlow(p.getProjFlow());
				String orderByClause = "CREATE_TIME";
				List<PubProjProcess> projProcessList = this.projProcessBiz.searchAuditProjProcess(projProcess,orderByClause);
				exportExcel.setProj(p);
				if(projProcessList.size()>0) {
					exportExcel.setProjProcess(projProcessList.get(projProcessList.size() - 1));
				}
				exportExcelList.add(exportExcel);
			}
		}
		String fileName = "项目导出表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportExcelList, response.getOutputStream());

	}

	@RequestMapping(value = "/recList")
	public String recList(String projFlow, Model model,
			HttpServletRequest request) {
		List<PubProjRec> recList = new ArrayList<PubProjRec>();
		PubProj proj = pubProjBiz.readProject(projFlow);
		// 查询该项目不在填写状态的rec记录
		PubProjRec projRec = new PubProjRec();
		projRec.setProjFlow(projFlow);
		//recList = pubProjRecBiz.searchProjRecNotInApply(projRec);
		List<String> status=new ArrayList<String>();
	    status.add(ProjApplyStatusEnum.Apply.getId());
	    status.add(ProjContractStatusEnum.Apply.getId());
	    status.add(ProjScheduleStatusEnum.Apply.getId());
	    status.add(ProjCompleteStatusEnum.Apply.getId());
	    List<PubProjRec> recs = this.pubProjRecBiz.searchProjRec(projRec);
		String contentIsExist = "N";
        if(recs!=null){
            if(InitConfig.getSysCfg("srm_rec_view_apply").equals(GlobalConstant.FLAG_Y)) {
                for (PubProjRec rec : recs) {
                    recList.add(rec);
					if(ProjRecTypeEnum.Contract.equals(rec.getRecTypeId())){
						contentIsExist="Y";
					}
                }
            }else {
                for(PubProjRec rec:recs){
                    if(!status.contains(rec.getProjStatusId())){
                        recList.add(rec);
                    }
					if(ProjRecTypeEnum.Contract.equals(rec.getRecTypeId())){
						contentIsExist="Y";
					}
                }
            }
            model.addAttribute("recCount" , recs.size());
        }
		model.addAttribute("contentIsExist",contentIsExist);
		model.addAttribute("proj", proj);
		model.addAttribute("recList", recList);
		model.addAttribute("recs",recs);

		// 查询实施阶段最新一条进展报告或变更报告
		List<String> recTypeList = new ArrayList<String>();
		recTypeList.add(ProjRecTypeEnum.ChangeReport.getId());
		recTypeList.add(ProjRecTypeEnum.ScheduleReport.getId());
		List<PubProjRec> scheduleRecList = pubProjRecBiz
				.selectProjRecByProjFlowAndRecList(projFlow, recTypeList);
		if (null != scheduleRecList && !scheduleRecList.isEmpty()) {
			PubProjRec scheduleRec = scheduleRecList.get(0);
			model.addAttribute("scheduleRec", scheduleRec);
		}

		// 查询实施阶段最新一条进展报告或变更报告
		recTypeList.removeAll(recTypeList);
		recTypeList.add(ProjRecTypeEnum.CompleteReport.getId());
		recTypeList.add(ProjRecTypeEnum.TerminateReport.getId());
		List<PubProjRec> completeRecList = pubProjRecBiz
				.selectProjRecByProjFlowAndRecList(projFlow, recTypeList);
		if (null != completeRecList && !completeRecList.isEmpty()) {
			PubProjRec completeRec = completeRecList.get(0);
			model.addAttribute("completeRec", completeRec);
		}

		SrmProjFundInfo fundInfo = new SrmProjFundInfo();
		fundInfo.setProjFlow(projFlow);
		fundInfo.setBudgetStatusId(AchStatusEnum.Submit.getId());
		List<SrmProjFundInfo> fundInfoList = fundInfoBiz
				.searchFundInfo(fundInfo);
		if (null != fundInfoList && !fundInfoList.isEmpty()) {
			model.addAttribute("projBudget", fundInfoList.get(0)
					.getBudgetStatusId());
		}

		return "srm/proj/search/recList";
	}

	@RequestMapping("/loadProjStatus")
	@ResponseBody
	public Object loadProjStatus(
			@RequestParam(value = "projStageId", required = true) String projStageId) {
		Map statusMap = null;
		if (ProjStageEnum.Apply.getId().equals(projStageId)) {
			statusMap = EnumUtil.toMap(ProjApplyStatusEnum.class);
			if (StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use"),
					"global")) {
				// 区域版
				statusMap.remove(ProjApplyStatusEnum.FirstBack.getId());
				statusMap.remove(ProjApplyStatusEnum.ThirdAudit.getId());
			} else {
				// 医院版
				statusMap.remove(ProjApplyStatusEnum.FirstAudit.getId());
				statusMap.remove(ProjApplyStatusEnum.FirstBack.getId());
				statusMap.remove(ProjApplyStatusEnum.SecondAudit.getId());
				statusMap.remove(ProjApplyStatusEnum.SecondBack.getId());
				statusMap.remove(ProjApplyStatusEnum.ThirdAudit.getId());
				statusMap.remove(ProjApplyStatusEnum.ThirdBack.getId());
			}

		} else if (ProjStageEnum.Approve.getId().equals(projStageId)) {
			statusMap = EnumUtil.toMap(ProjApproveStatusEnum.class);
			statusMap.remove(ProjApproveStatusEnum.Approve.getId());
			statusMap.remove(ProjApproveStatusEnum.Save.getId());
			statusMap.remove(ProjApproveStatusEnum.Confirm.getId());
			statusMap.remove(ProjApproveStatusEnum.NoPrizes.getId());
			statusMap.remove(ProjApproveStatusEnum.AwardPrizes.getId());
		} else if (ProjStageEnum.Contract.getId().equals(projStageId)) {
			statusMap = EnumUtil.toMap(ProjContractStatusEnum.class);
			if (StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use"),
					"global")) {
				// 区域版
				statusMap.remove(ProjContractStatusEnum.FirstBack.getId());
				statusMap.remove(ProjContractStatusEnum.ThirdAudit.getId());
			} else {
				// 医院版
				statusMap.remove(ProjContractStatusEnum.FirstBack.getId());
				statusMap.remove(ProjContractStatusEnum.SecondAudit.getId());
				statusMap.remove(ProjContractStatusEnum.SecondBack.getId());
				statusMap.remove(ProjContractStatusEnum.ThirdAudit.getId());
				statusMap.remove(ProjContractStatusEnum.ThirdBack.getId());
			}
		} else if (ProjStageEnum.Schedule.getId().equals(projStageId)) {
			statusMap = EnumUtil.toMap(ProjScheduleStatusEnum.class);
			if (StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use"),
					"global")) {
				// 区域版
				statusMap.remove(ProjScheduleStatusEnum.FirstBack.getId());
			} else {
				// 医院版
				statusMap.remove(ProjScheduleStatusEnum.FirstBack.getId());
				statusMap.remove(ProjScheduleStatusEnum.SecondAudit.getId());
				statusMap.remove(ProjScheduleStatusEnum.SecondBack.getId());
				statusMap.remove(ProjScheduleStatusEnum.ThirdAudit.getId());
				statusMap.remove(ProjScheduleStatusEnum.ThirdBack.getId());
			}
		} else if (ProjStageEnum.Complete.getId().equals(projStageId)) {
			statusMap = EnumUtil.toMap(ProjCompleteStatusEnum.class);
			if (StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use"),
					"global")) {
				// 区域版
				statusMap.remove(ProjCompleteStatusEnum.FirstBack.getId());
			} else {
				// 医院版
				statusMap.remove(ProjCompleteStatusEnum.FirstBack.getId());
				statusMap.remove(ProjCompleteStatusEnum.SecondAudit.getId());
				statusMap.remove(ProjCompleteStatusEnum.SecondBack.getId());
				statusMap.remove(ProjCompleteStatusEnum.ThirdAudit.getId());
				statusMap.remove(ProjCompleteStatusEnum.ThirdBack.getId());
			}
		} else if (ProjStageEnum.Archive.getId().equals(projStageId)) {
			statusMap = EnumUtil.toMap(ProjArchiveStatusEnum.class);
		}
        if(statusMap.containsKey((ProjCompleteStatusEnum.ThirdAudit.getId()))){
            statusMap.put(ProjCompleteStatusEnum.ThirdAudit.getId(),InitConfig.getSysCfg("top_org_level") + ProjCompleteStatusEnum.ThirdAudit.getName());
        }
        if(statusMap.containsKey((ProjCompleteStatusEnum.ThirdBack.getId()))){
            statusMap.put(ProjCompleteStatusEnum.ThirdBack.getId(),InitConfig.getSysCfg("top_org_level") + ProjCompleteStatusEnum.ThirdBack.getName());
        }
        if(statusMap.containsKey((ProjScheduleStatusEnum.ThirdAudit.getId()))){
            statusMap.put(ProjScheduleStatusEnum.ThirdAudit.getId(),InitConfig.getSysCfg("top_org_level") + ProjScheduleStatusEnum.ThirdAudit.getName());
        }
        if(statusMap.containsKey((ProjScheduleStatusEnum.ThirdBack.getId()))){
            statusMap.put(ProjScheduleStatusEnum.ThirdBack.getId(),InitConfig.getSysCfg("top_org_level") + ProjScheduleStatusEnum.ThirdBack.getName());
        }
        if(statusMap.containsKey((ProjContractStatusEnum.ThirdAudit.getId()))){
            statusMap.put(ProjContractStatusEnum.ThirdAudit.getId(),InitConfig.getSysCfg("top_org_level") + ProjContractStatusEnum.ThirdAudit.getName());
        }
        if(statusMap.containsKey((ProjContractStatusEnum.ThirdBack.getId()))){
            statusMap.put(ProjContractStatusEnum.ThirdBack.getId(),InitConfig.getSysCfg("top_org_level") + ProjContractStatusEnum.ThirdBack.getName());
        }
        if(statusMap.containsKey((ProjApplyStatusEnum.ThirdAudit.getId()))){
            statusMap.put(ProjApplyStatusEnum.ThirdAudit.getId(),InitConfig.getSysCfg("top_org_level") + ProjApplyStatusEnum.ThirdAudit.getName());
        }
        if(statusMap.containsKey((ProjApplyStatusEnum.ThirdBack.getId()))){
            statusMap.put(ProjApplyStatusEnum.ThirdBack.getId(),InitConfig.getSysCfg("top_org_level") + ProjApplyStatusEnum.ThirdBack.getName());
        }
		return statusMap;
	}

	@RequestMapping(value = "/toExcel/{projListScope}/{projCateScope}")
	public ModelAndView toExcel(@PathVariable String projListScope,
			@PathVariable String projCateScope, PubProj proj, SysOrg org,
			String flag, Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		List<SysOrg> currOrgChildList = orgBiz
				.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
		Map<String, List<SysOrg>> resultMap = orgBiz.searchChargeAndApply(org,
				projListScope);
		List<SysOrg> secondGradeOrgList = (List<SysOrg>) resultMap
				.get("secondGradeOrgList");
		List<PubProj> searchList = null;
		if (StringUtil.isNotBlank(flag)) {
			if (StringUtil.isBlank(org.getOrgFlow())
					&& StringUtil.isBlank(org.getChargeOrgFlow())) {
				searchList = projSeeBiz.searchProj(proj, currOrgChildList);
			}

			if (StringUtil.isNotBlank(org.getOrgFlow())) {
				proj.setApplyOrgFlow(org.getOrgFlow());
				searchList = projSeeBiz.searchProj(proj, null);
			}

			if (StringUtil.isBlank(org.getOrgFlow())
					&& StringUtil.isNotBlank(org.getChargeOrgFlow())) {
				if (null == secondGradeOrgList || secondGradeOrgList.isEmpty()) {
					SysOrg sysOrg = orgBiz.readSysOrg(org.getChargeOrgFlow());
					List<SysOrg> selfOrgList = new ArrayList<SysOrg>();
					selfOrgList.add(sysOrg);
					searchList = projSeeBiz.searchProj(proj, selfOrgList);
				} else {
					searchList = projSeeBiz
							.searchProj(proj, secondGradeOrgList);
				}
			}
			if (null != searchList && !searchList.isEmpty()) {
				Map<String, Object> objMap = new HashMap<String, Object>();
				objMap.put("projList", searchList);
				ListToExcel listToExcel = new ListToExcel();
				return new ModelAndView(listToExcel, objMap);
			}
		}

		return null;
	}

	//人才查询导出
	@RequestMapping("/rcExport")
	public void rcExport(HttpServletResponse response, PubProj proj) throws Exception {
		String[] titles = new String[]{
				"projYear:年份",
				"projName:人才名称",
				"projStartTime:开始时间",
				"projEndTime:结束时间",
				"applyUserName:人才负责人",
				"projStageName:当前阶段",
				"applyOrgName:申报单位"
		};
		proj.setProjCategoryId("rc");
		List<PubProj> projList = projSeeBiz.searchProj(proj);
		String fileName = "重点人才.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, projList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
}
