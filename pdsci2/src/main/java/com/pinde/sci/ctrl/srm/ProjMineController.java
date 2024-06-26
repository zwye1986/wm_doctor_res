package com.pinde.sci.ctrl.srm;

import com.pinde.core.jspform.Page;
import com.pinde.core.jspform.PageGroup;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.EnumUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.JspFormUtil;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 用于我的项目中的所有功能模块
 * @author 沈振
 *
 */
@Controller
@RequestMapping("/srm/proj/mine")
public class ProjMineController extends GeneralController{

	@Autowired
	private IProjMineBiz projMineBiz;
	@Autowired
	private IPubProjBiz projBiz;
	@Autowired
	private IProjPageBiz projPageBiz;
	@Autowired
	private IProjRecBiz projRecBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IProjProcessBiz projProcessBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IExpertGroupProjBiz evalSettingBiz;
	@Autowired
	private IExpertGroupBiz expertGroupBiz;
	@Autowired
	private IExpertProjBiz expertProjBiz;
	@Autowired
	private IProjApproveBiz projApproveBiz;
	@Autowired
	private IAidProjBiz aidProjBiz;
	@Autowired
	private IDeptBiz deptBiz;

	/**
	 * 显示当前用户的项目列表(只针对项目负责人)
	 * @param proj 封装查询条件
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/projList/{projCateScope}")
	public String projList(PubProj proj ,@PathVariable String projCateScope,Integer currentPage ,String startYear,String endYear,String defaultTerm,HttpServletRequest request, Model model){
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
		//设置默认条件
		if(StringUtil.isNotBlank(defaultTerm)){
			model.addAttribute("defaultTerm",defaultTerm);
			proj.setProjYear(DateUtil.getYear());
		}
		SysUser currUser = GlobalContext.getCurrentUser();
		PageHelper.startPage(currentPage, getPageSize(request));
		proj.setApplyUserFlow(currUser.getUserFlow());
		List<PubProj> projList = this.projMineBiz.searchProjList(proj,startYear,endYear);
		List<String> projFlowList = new ArrayList<String>();
		if(projList!=null && !projList.isEmpty()){
			for(PubProj pp:projList){
				projFlowList.add(pp.getProjFlow());
			}
			Map<String , List<PubProjProcess>> projAndProcessListMap = new HashMap<String , List<PubProjProcess>>();
			List<PubProjProcess> projProcessList = this.projProcessBiz.searchProjProcessByProjFlowList(projFlowList);
			List<PubProjProcess> val = null;
			for(String projFlow:projFlowList){
				if(projAndProcessListMap.containsKey(projFlow)){
					val = projAndProcessListMap.get(projFlow);
				}else{
					val = new ArrayList<PubProjProcess>();
				}

				for(PubProjProcess ppp:projProcessList){
					if(projFlow.equals(ppp.getProjFlow())){
						val.add(ppp);
					}
				}
				projAndProcessListMap.put(projFlow, val);

			}
			model.addAttribute("projAndProcessListMap" , projAndProcessListMap);
		}

		model.addAttribute("projList",projList);
		return "srm/proj/mine/projList_"+projCateScope;
	}

	/**
	 * 添加新的项目基本信息 界面显示
	 * @return
	 */
	@RequestMapping(value="/addProjInfo")
	public String addProjInfo(Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		String projCateScope = StringUtil.defaultString((String)getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE));
		String projTypeId = projCateScope+".info";
		PageGroup projPage = this.projPageBiz.getPageGroup(ProjRecTypeEnum.Info.getId(), projTypeId);
		List<SysDept> depts = deptBiz.searchDeptByOrg(currUser.getOrgFlow());
		model.addAttribute("depts", depts);
		return projPage.getFirstPageJsp();
	}

	/**
	 * 用于编辑项目基本信息 界面和数据显示
	 * @param projFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editProjInfo")
	public String editProjInfo(@RequestParam(value="projFlow",required=true)String projFlow,
			@RequestParam(value="pageName",required=false)String pageName, Model model){

		String projCateScope = StringUtil.defaultString((String)getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE));
		String projTypeId = projCateScope+".info";
		PageGroup pageGroup = this.projPageBiz.getPageGroup(ProjRecTypeEnum.Info.getId(), projTypeId);
		//默认编辑第一个页面
		if(StringUtil.isBlank(pageName)){
			pageName = pageGroup.getFirstPage();
		}
		Page page = this.projPageBiz.getPage(ProjRecTypeEnum.Info.getId(), projTypeId , pageName);
		PubProj proj =  this.projBiz.readProject(projFlow);
		String info = proj.getProjInfo();
		Map<String , Object> projInfoMap = JspFormUtil.parseXmlStr(info , page);
		model.addAttribute("projInfoMap" , projInfoMap);
		model.addAttribute("proj" , proj);
		//获取附件列表
		Map<String , PubFile> pageFileMap = this.projBiz.getFile(projInfoMap);
		if(pageFileMap!=null){
			model.addAttribute("pageFileMap" , pageFileMap);
		}
		List<SysDept> depts = deptBiz.searchDeptByOrg(proj.getApplyOrgFlow());
		model.addAttribute("depts", depts);
		return page.getJsp();
	}
	@RequestMapping(value="/savePubProj")
	public String savePubProj(PubProj proj){
		if(StringUtil.isNotBlank(proj.getProjFlow())){
			this.projBiz.modProject(proj);
		}else{
			projMineBiz.addProjInfo(proj);
		}
		return "redirect:process?projFlow="+proj.getProjFlow()+"&order="+ProjStageEnum.Apply.getId();
	}
	/**
	 * 保存编辑的项目基本信息 proj 项目基本信息的对象  files 附件上传的数组
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/saveProjInfo")
	public String saveProjInfo(HttpServletRequest request){
		String projCateScope = StringUtil.defaultString((String)getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE));
		String projTypeId = projCateScope+".info";
		//组织参数
		Map<String , String[]> datasMap = JspFormUtil.getParameterMap(request);
		String pageName = datasMap.get("pageName")[0];
		Page page = this.projPageBiz.getPage(ProjRecTypeEnum.Info.getId(),projTypeId,  pageName);

		String projFlow = datasMap.get("projFlow")[0];
		PubProj proj = null;
		String projInfo = "";
		if(StringUtil.isBlank(projFlow)){
			proj = new PubProj();
			convertProj(datasMap,proj);
			ProjCategroyEnum projCategroyEnum = EnumUtil.getRequiredById(projCateScope, ProjCategroyEnum.class);
			proj.setProjCategoryId(projCategroyEnum.getId());
			proj.setProjCategoryName(projCategroyEnum.getName());
			projInfo = JspFormUtil.updateXmlStr(projInfo,page , datasMap);
			proj.setProjInfo(projInfo);
			projMineBiz.addProjInfo(proj);
		}else{
			proj = projBiz.readProject(projFlow);
			convertProj(datasMap,proj);
			projInfo = proj.getProjInfo();
			projInfo = JspFormUtil.updateXmlStr(projInfo,page, datasMap);
			proj.setProjInfo(projInfo);
			this.projBiz.modProject(proj);
		}
		return "redirect:process?projFlow="+proj.getProjFlow()+"&order="+ProjStageEnum.Apply.getId();
	}

	private void convertProj(Map<String , String[]> datasMap,PubProj proj){
		String projCateScope = StringUtil.defaultString((String)getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE));
		String [] projNames = datasMap.get("projName");
		if(projNames!=null&&projNames.length>0){
			proj.setProjName(projNames[0]);
		}
		String [] applyDeptFlow = datasMap.get("applyDeptFlow");
		if(applyDeptFlow!=null&&applyDeptFlow.length>0){
			proj.setApplyDeptFlow(applyDeptFlow[0]);
		}
		String [] applyDeptName = datasMap.get("applyDeptName");
		if(applyDeptName!=null&&applyDeptName.length>0){
			proj.setApplyDeptName(applyDeptName[0]);
		}
		String [] subjectCode = datasMap.get("subjectCode");
		if(subjectCode!=null&&subjectCode.length>0){
			proj.setSubjId(subjectCode[0]);
		}
		String [] subjectName = datasMap.get("subjectName");
		if(subjectName!=null&&subjectName.length>0){
			proj.setSubjName(subjectName[0]);
		}
		String[] projStartAndEndTime = datasMap.get("projStartTime");
		if(projStartAndEndTime!=null && projStartAndEndTime.length>0){
			proj.setProjStartTime(projStartAndEndTime[0]);
		}
		projStartAndEndTime = datasMap.get("projEndTime");
		if(projStartAndEndTime!=null && projStartAndEndTime.length>0){
			proj.setProjEndTime(projStartAndEndTime[0]);
		}
		String[] projDeclarerFlow = datasMap.get("projDeclarerFlow");
		if(projDeclarerFlow!=null && projDeclarerFlow.length>0){
			proj.setProjDeclarerFlow(projDeclarerFlow[0]);
		}
		String[] projDeclarer = datasMap.get("projDeclarer");
		if(projDeclarer!=null && projDeclarer.length>0){
			proj.setProjDeclarer(projDeclarer[0]);
		}
		String[] projSecondSourceId = datasMap.get("projSecondSourceId");
		if(projSecondSourceId!=null && projSecondSourceId.length>0){
			proj.setProjSecondSourceId(projSecondSourceId[0]);
		}
		String[] projSecondSourceName = datasMap.get("projSecondSourceName");
		if(projSecondSourceName!=null && projSecondSourceName.length>0){
			proj.setProjSecondSourceName(projSecondSourceName[0]);
		}
		String[] applyUserPhone = datasMap.get("applyUserPhone");
		if(applyUserPhone!=null && applyUserPhone.length>0){
			proj.setApplyUserPhone(applyUserPhone[0]);
		}
		String[] planTypeId = datasMap.get("planTypeId");
		if(planTypeId!=null&&planTypeId.length>0){
			proj.setPlanTypeId(planTypeId[0]);
			proj.setPlanTypeName(DictTypeEnum.PlanCategory.getDictNameById(planTypeId[0]));
		}
		String [] projTypes = datasMap.get("projType");
		if(projTypes!=null&&projTypes.length>0){
			proj.setProjTypeId(projTypes[0]);
			if(ProjCategroyEnum.Ky.getId().equals(projCateScope)){
				proj.setProjTypeName(DictTypeEnum.ProjType.getDictNameById(projTypes[0]));
			}else if(ProjCategroyEnum.Qw.getId().equals(projCateScope)){
				proj.setProjTypeName(DictTypeEnum.EdusType.getDictNameById(projTypes[0]));
			}else if(ProjCategroyEnum.Xk.getId().equals(projCateScope)){
				proj.setProjTypeName(DictTypeEnum.SubjType.getDictNameById(projTypes[0]));
			}else if(ProjCategroyEnum.Rc.getId().equals(projCateScope)){
				proj.setProjTypeName(DictTypeEnum.TalentType.getDictNameById(projTypes[0]));
			}else if(ProjCategroyEnum.Bj.getId().equals(projCateScope)){
				proj.setProjTypeName(DictTypeEnum.AwardType.getDictNameById(projTypes[0]));
			}else if(ProjCategroyEnum.Gl.getId().equals(projCateScope)){
				proj.setProjTypeName(DictTypeEnum.ManageType.getDictNameById(projTypes[0]));
			}


		}



	}
	/**
	 * 查看审核列表
	 * @param projFlow
	 * @return
	 */
	@RequestMapping("/auditList")
	public String auditList(@RequestParam(value="projFlow" , required=true)String projFlow , Model model){
		PubProj proj =  this.projBiz.readProject(projFlow);
		model.addAttribute("proj", proj);
		PubProjProcess projProcess = new PubProjProcess();
		projProcess.setProjFlow(projFlow);
		String orderByClause = "CREATE_TIME";
		List<PubProjProcess> projProcessList = this.projProcessBiz.searchAuditProjProcess(projProcess,orderByClause);
		model.addAttribute("projProcessList", projProcessList);
		return "srm/proj/mine/auditList";
	}

	/**
	 * 在项目列表中点击进入操作 显示界面和数据
	 * @param projFlow
	 * @param order
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/process"})
	public  String  process(@RequestParam(value="projFlow" , required=true) String projFlow, String order , Model model) {
		PubProj proj = projBiz.readProject(projFlow);
		model.addAttribute("proj",proj);
		String projTypeId = proj.getProjTypeId();
        if(projTypeId.split("\\.")[0].equals("xzzxyy")){
            projTypeId = "xzzxyy";
        }
		//选项卡的显示
		String tab = _showTab(order ,proj.getProjStageId());
		model.addAttribute("tab", tab);

		//是否有申报书
		String pageGroupName = InitConfig.configMap.get(ProjRecTypeEnum.Apply.getId()).get(projTypeId);
		if(StringUtil.isNotBlank(pageGroupName)){
			//存在申报书
			model.addAttribute("applyExists" , GlobalConstant.FLAG_Y);
		}

		//立项管理
		//评审信息
		SrmExpertProjEval srmExpertGroupProj = new SrmExpertProjEval();
		srmExpertGroupProj.setRecordStatus(GlobalConstant.FLAG_Y);
		srmExpertGroupProj.setProjFlow(projFlow);
		List<SrmExpertProjEval> evalSettingList = this.evalSettingBiz.searchSrmExpertGroupProj(srmExpertGroupProj);
		if(!evalSettingList.isEmpty()){
			srmExpertGroupProj = evalSettingList.get(0);
		}
		if(StringUtil.isNotBlank(srmExpertGroupProj.getEvalSetFlow())){
			//如果是会议评审的话，会评时间在专家组表中
			if(EvaluationWayEnum.MeetingWay.getId().equals(srmExpertGroupProj.getEvaluationWayId())){
				SrmExpertGroup expertGroup = this.expertGroupBiz.readSrmExpertGroup(srmExpertGroupProj.getGroupFlow());
				model.addAttribute("expertGroup",expertGroup);
			}
			model.addAttribute("srmExpertGroupProj" , srmExpertGroupProj);
			//参加评审的专家
			//SrmExpertProj expertProj = new SrmExpertProj();
			//expertProj.setEvalSetFlow(srmExpertGroupProj.getEvalSetFlow());
			//List<SysUserExt> joinEvalSetExpertList = this.expertProjBiz.searchJoinEvalSetExpertList(expertProj);
			//model.addAttribute("joinEvalSetExpertList" , joinEvalSetExpertList);
		}
		PubProjProcess projProcess = new PubProjProcess();
		projProcess.setProjFlow(projFlow);

		List<PubProjProcess> projApproveProcessList = null;
		if(ProjCategroyEnum.Bj.getId().equals(proj.getProjCategoryId())){
			//科技报奖
			projProcess.setProjStageId(ProjStageEnum.Award.getId());
			projApproveProcessList = this.projProcessBiz.searchApproveProcess(projProcess);
			for(PubProjProcess pp:projApproveProcessList) {
				if (ProjApproveStatusEnum.AwardPrizes.getId().equals(pp.getProjStatusId()) || ProjApproveStatusEnum.NoPrizes.getId().equals(pp.getProjStatusId())) {
					projProcess = pp;
					break;
				}
			}
		}else {
			//立项信息
			projProcess.setProjStageId(ProjStageEnum.Approve.getId());
			projApproveProcessList = this.projProcessBiz.searchApproveProcess(projProcess);
			projProcess = null;
			for (PubProjProcess pp : projApproveProcessList) {
				if (ProjApproveStatusEnum.Approve.getId().equals(pp.getProjStatusId()) || ProjApproveStatusEnum.NotApprove.getId().equals(pp.getProjStatusId())) {
					projProcess = pp;
					break;
				}
			}
		}
		model.addAttribute("process" , projProcess);

		//下拨或者经费预算
		if(StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use") , "global")){
			//下拨
			PubProjProcess projFundPlanProcess = null;
			for(PubProjProcess pp:projApproveProcessList){
				if(ProjApproveStatusEnum.Confirm.getId().equals(pp.getProjStatusId())){
					projFundPlanProcess = pp;
					break;
				}
			}
			if(projFundPlanProcess!=null){
				PubProjFundPlan projFindPlan = new PubProjFundPlan();
				projFindPlan.setProjFlow(projFlow);
				List<PubProjFundPlan> projFundPlanList = this.projApproveBiz.searchProjFundPlanList(projFindPlan);
				model.addAttribute("projFundPlanList",projFundPlanList);
			}

		}else if(StringUtil.isEquals(InitConfig.getSysCfg("srm_for_use") , "local")){
			//预算审核
		}

		//是否有合同
		pageGroupName = InitConfig.configMap.get(ProjRecTypeEnum.Contract.getId()).get(proj.getProjTypeId());
		if(StringUtil.isNotBlank(pageGroupName)){
			//存在合同
			model.addAttribute("contractExists" , GlobalConstant.FLAG_Y);
			PubProjRec contractRec = this.projRecBiz.selectProjRecByProjFlowAndRecType(projFlow, ProjRecTypeEnum.Contract.getId());
			model.addAttribute("contractRec" , contractRec);
		}

		//是否存在进展报告
		pageGroupName = InitConfig.configMap.get(ProjRecTypeEnum.ScheduleReport.getId()).get(proj.getProjTypeId());
		System.err.println(InitConfig.configMap);
		System.err.println(InitConfig.configMap.get(ProjRecTypeEnum.ScheduleReport.getId()));
		String chargePageGroupName = InitConfig.configMap.get(ProjRecTypeEnum.ChangeReport.getId()).get(proj.getProjTypeId());
		String delayPageGroupName = InitConfig.configMap.get(ProjRecTypeEnum.DelayReport.getId()).get(proj.getProjTypeId());
		if(StringUtil.isNotBlank(pageGroupName) || StringUtil.isNotBlank(chargePageGroupName) || StringUtil.isNotBlank(delayPageGroupName)){
			//季报 , 延迟 ， 变更列表
			model.addAttribute("scheduleExists" , GlobalConstant.FLAG_Y);
			List<PubProjRec> recList =  projMineBiz.searchScheduleReport(projFlow);
			model.addAttribute("scheduleReport",recList);
		}
		//如果变更申请，验收报告，终止报告不存在
		String terminateGroupName = InitConfig.configMap.get(ProjRecTypeEnum.TerminateReport.getId()).get(proj.getProjTypeId());
		String completeGroupName = InitConfig.configMap.get(ProjRecTypeEnum.CompleteReport.getId()).get(proj.getProjTypeId());
		if(StringUtil.isNotBlank(chargePageGroupName)){
			model.addAttribute("chargeReportExists" , GlobalConstant.FLAG_Y);
		}
		if(StringUtil.isNotBlank(delayPageGroupName)){
			model.addAttribute("delayReportExists" , GlobalConstant.FLAG_Y);
		}
        if(StringUtil.isNotBlank(completeGroupName)){
            model.addAttribute("completeReportExists" , GlobalConstant.FLAG_Y);
        }
        if(StringUtil.isNotBlank(terminateGroupName)){
            model.addAttribute("terminateReportExists" , GlobalConstant.FLAG_Y);
        }
		//项目状态（终止还是验收 省中医院 终止阶段）
		boolean terminateFlag = false;
		//如果项目的当前阶段是完成阶段或是归档阶段，则查询rec中的验收报告和中止报告
		 if(ProjStageEnum.Complete.getId().equals(proj.getProjStageId()) || ProjStageEnum.Archive.getId().equals(proj.getProjStageId())){
			 List<String> recTypeList=new ArrayList<String>();
			 recTypeList.add(ProjRecTypeEnum.CompleteReport.getId());
			 recTypeList.add(ProjRecTypeEnum.TerminateReport.getId());
			 List<PubProjRec> projRecList=projRecBiz.selectProjRecByProjFlowAndRecList(projFlow, recTypeList);
		     if(null!=projRecList && !projRecList.isEmpty()){
		    	 PubProjRec projRec=projRecList.get(0);
				 if(ProjRecTypeEnum.TerminateReport.getId().equals(projRec.getRecTypeId())){
					 terminateFlag = true;
				 }
		    	 model.addAttribute("projRec", projRec);
		     }
		 }
		 Map<String , String> icnMap = showIcnByStageId(proj.getProjStageId());
		if(terminateFlag){
			icnMap.put("5","2");
		}
		 model.addAttribute("icnMap" , icnMap);
		 String projCateScope = (String) getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE);
		 return "srm/proj/mine/process_"+projCateScope;
	}

	@RequestMapping("/projView")
	public String projView(String projFlow , String recTypeId , String recFlow , Model model){
		PubProj proj = this.projBiz.readProject(projFlow);
		//生成菜单
		PubProjRec projRec = new PubProjRec();
		projRec.setProjFlow(projFlow);
		List<PubProjRec> recList = this.projRecBiz.searchProjRec(projRec);
		//审核者只能看到送审后的
		//负责人可以看到他填写的
		model.addAttribute("recList" , recList);
		model.addAttribute("proj" , proj);
		return "srm/proj/view/proj";
	}

	@RequestMapping("/auditProjView")
	public String auditProjView(String projFlow , String recTypeId , String recFlow ,String expertFlag,String isExpert,Model model){
		PubProj proj = this.projBiz.readProject(projFlow);
		//生成菜单
		PubProjRec projRec = new PubProjRec();
		projRec.setProjFlow(projFlow);
        List<PubProjRec> recList;
        if(InitConfig.getSysCfg("srm_rec_view_apply").equals(GlobalConstant.FLAG_Y)) {
            recList = this.projRecBiz.searchProjRec(projRec);
        }else{
            recList = this.projRecBiz.searchProjRecNotInApply(projRec);
        }
		//审核者只能看到送审后的
		//负责人可以看到他填写的
		model.addAttribute("recList" , recList);
		model.addAttribute("proj" , proj);
        model.addAttribute("isExpert",isExpert);
		setSessionAttribute("expertFlag", expertFlag);
		return "srm/proj/view/proj";
	}

	@RequestMapping("/view")
	public String view(String projFlow , String recTypeId , String recFlow , String isExpert , Model model){
		PubProj proj =  this.projBiz.readProject(projFlow);
		model.addAttribute("proj" , proj);
        String projTypeId = proj.getProjTypeId();
        if(projTypeId.split("\\.")[0].equals("xzzxyy")){
            projTypeId = "xzzxyy";
        }
		model.addAttribute("isExpert",isExpert);
		if(StringUtil.isNotBlank(recFlow)){
			PubProjRec rec = this.projRecBiz.readProjRec(recFlow);
			if(StringUtil.isNotBlank(rec.getPageGroupName())){
				projTypeId = rec.getPageGroupName();
			}
			PageGroup pageGroup = this.projPageBiz.getPageGroup(rec.getRecTypeId(), projTypeId);
			if(rec!=null){
				String content = rec.getRecContent();
				if(StringUtil.isNotBlank(content)){
					Map<String , Object> resultMap = JspFormUtil.parseXmlStr(content);
					model.addAttribute("resultMap" , resultMap);

					//获取附件列表
					List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
					model.addAttribute("fileFlows" , fileFlows);
					Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
					if(pageFileMap!=null){
						model.addAttribute("pageFileMap" , pageFileMap);
					}
				}
			}
            String[] projType = {"jsswst.yxzdrc","jsswst.yxcx","jsswst.yxzdxk","wxwsj.kjcgtg","wxwsj.zdxm","wxwsj.msxm","wxwsj.qnxm",
                    "wxwsj.lcyxzx","wxwsj.qnyxrc","wxwsj.yxjcrc","wxwsj.yxzdxk","wxwsj.yxljrc","wxwsj.yxzdrc","wxwsj.yxcxpt"};
            List<String> projTypeList =new ArrayList<>(Arrays.asList(projType));
            //当前项目类型在列表中 专家评审只允许看指定内容
            if(projTypeList.contains(proj.getProjTypeId()) && GlobalConstant.FLAG_Y.equals(isExpert)){
                String page = pageGroup.getView().substring(0,pageGroup.getView().lastIndexOf("/"));
                String newPage =page + "/expertView";
                return newPage;
            }
			return pageGroup.getView();
		}

		if(StringUtil.isBlank(recTypeId)){
			recTypeId = ProjRecTypeEnum.Info.getId();
		}

		//String projTypeId = proj.getProjTypeId();
		if(ProjRecTypeEnum.Info.getId().equals(recTypeId)){
			projTypeId = proj.getProjCategoryId()+".info";
		}
		PageGroup pageGroup = this.projPageBiz.getPageGroup(recTypeId, projTypeId);
		if(ProjRecTypeEnum.Info.getId().equals(recTypeId)){
			String info = proj.getProjInfo();
			Map<String , Object> projInfoMap = JspFormUtil.parseXmlStr(info);
			model.addAttribute("projInfoMap" , projInfoMap);
			//获取附件列表
			List<String> fileFlows = this.projBiz.getFileFlows(projInfoMap);
			model.addAttribute("fileFlows" , fileFlows);
			Map<String , PubFile> pageFileMap = this.projBiz.getFile(projInfoMap);
			if(pageFileMap!=null){
				model.addAttribute("pageFileMap" , pageFileMap);
			}
		}else{
			PubProjRec projContractRec = this.projRecBiz.selectProjRecWithContentByProjFlowAndRecType(projFlow, recTypeId);
			if(projContractRec!=null){
				String content = projContractRec.getRecContent();
				if(StringUtil.isNotBlank(content)){
					Map<String , Object> resultMap = JspFormUtil.parseXmlStr(content);
					model.addAttribute("resultMap" , resultMap);
					//获取附件列表
					List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
					model.addAttribute("fileFlows" , fileFlows);
					Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
					if(pageFileMap!=null){
						model.addAttribute("pageFileMap" , pageFileMap);
					}

				}
			}
		}
		//如果是重点专科类型(基本信息没有表单)
		if(proj.getProjCategoryId().equals(ProjCategroyEnum.Zk.getId()) || pageGroup == null){
			pageGroup = new PageGroup();
			pageGroup.setView("srm/proj/add/addContract_view");
		}
		return pageGroup.getView();
	}

	/**
	 *
	 * @param projFlow
	 * @param recTypeId
	 * @param pageName
	 * @param recFlow
	 * @param isAdd
	 * @param projPageGroupName 江苏省中医院科研院外项目进展报告是否填写表单
	 * @param request
     * @param model
     * @return
     */
	@RequestMapping(value = {"/showStep" },method={RequestMethod.GET , RequestMethod.POST})
	public String showStep(@RequestParam(value="projFlow" , required=true)String projFlow,
			@RequestParam(value="recTypeId" , required=true)String recTypeId ,
			String pageName , String recFlow , String isAdd , String projPageGroupName,HttpServletRequest request , Model model){
		PubProj proj = this.projBiz.readProject(projFlow);
		String projTypeId = proj.getProjTypeId();
        if(projTypeId.split("\\.")[0].equals("xzzxyy")){
            projTypeId = "xzzxyy";
        }
		if(StringUtil.isNotBlank(projPageGroupName)){//填写其它表单（不是xml中对应的表单  江苏省中科研进展报告）
			projTypeId = projPageGroupName;
			model.addAttribute("projPageGroupName",projPageGroupName);
		}
		/*---------------加载项目基本信息------------------*/
		String info = proj.getProjInfo();
		Map<String , Object> projInfoMap = JspFormUtil.parseXmlStr(info);
		model.addAttribute("projInfoMap" , projInfoMap);

		String applyUserFlow = proj.getApplyUserFlow();
		SysUser user = this.userBiz.readSysUser(applyUserFlow);
		model.addAttribute("user" , user);

		String orgFlow =user.getOrgFlow();
		SysOrg org = this.orgBiz.readSysOrg(orgFlow);
		model.addAttribute("org" , org);
		model.addAttribute("proj" , proj);
		/*--------------------------------------------*/
		/*------------------加载申报书信息--------------------------*/
		if(!ProjStageEnum.Apply.getId().equals(proj.getProjStageId())){
			PubProjRec projApplyRec = this.projRecBiz.selectProjRecWithContentByProjFlowAndRecType(proj.getProjFlow() , ProjRecTypeEnum.Apply.getId());
			if(projApplyRec!=null && StringUtil.isNotBlank(projApplyRec.getRecContent())){
				Map<String , Object> applyMap = JspFormUtil.parseXmlStr(projApplyRec.getRecContent());
				model.addAttribute("applyMap" , applyMap);
			}
		}
		/*---------------加载项目合同信息------------------*/
		if(ProjStageEnum.Schedule.getId().equals(proj.getProjStageId()) || ProjStageEnum.Complete.getId().equals(proj.getProjStageId())){
			PubProjRec projContractRec = this.projRecBiz.selectProjRecWithContentByProjFlowAndRecType(projFlow, ProjRecTypeEnum.Contract.getId());
			if(projContractRec!=null){
				String contractContent = projContractRec.getRecContent();
				if(StringUtil.isNotBlank(contractContent)){
					Map<String , Object> contractMap = JspFormUtil.parseXmlStr(contractContent);
					model.addAttribute("contractMap" , contractMap);
				}
			}
		}
		/*-------------------合同是否显示下拨经费-----------------------*/
		if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("srm_contract_use_fundPlan"))){
			PubProjFundPlan  projFundPlan = new PubProjFundPlan();
			projFundPlan.setProjFlow(projFlow);
			List<PubProjFundPlan> projFundPlanList = this.projApproveBiz.searchProjFundPlanList(projFundPlan);
			model.addAttribute("projFundPlanList" , projFundPlanList);
		}
		PageGroup pageGroup = this.projPageBiz.getPageGroup(recTypeId, projTypeId);
		//默认编辑第一个页面
		if(StringUtil.isBlank(pageName)){
			pageName = pageGroup.getFirstPage();
		}
		Page page =_getPage(projTypeId , pageName , recTypeId);

		if(StringUtil.isBlank(recFlow)){
			if(!GlobalConstant.FLAG_Y.equals(isAdd)){
				PubProjRec projRec = this.projRecBiz.selectProjRecByProjFlowAndRecType(projFlow, recTypeId);
				recFlow = projRec == null ? "": projRec.getRecFlow();
			}

		}
        /*江苏中医药局科研项目 需要引用申报书其它页内容 此处加载所有申报书内容*/
        if(	ProjStageEnum.Apply.getId().equals(proj.getProjStageId())
				&& (proj.getProjTypeId().equals("jszyyj.zdxm") ||
				proj.getProjTypeId().equals("jszyyj.ybxm") ||
				proj.getProjTypeId().equals("jszyyj.jdxm") ||
				proj.getProjTypeId().equals("jszyyj.fyxm"))
		){
            PubProjRec projApplyRec = this.projRecBiz.selectProjRecWithContentByProjFlowAndRecType(proj.getProjFlow() , ProjRecTypeEnum.Apply.getId());
            if(projApplyRec!=null && StringUtil.isNotBlank(projApplyRec.getRecContent())){
                Map<String , Object> jszyyjApplyMap = JspFormUtil.parseXmlStr(projApplyRec.getRecContent());
                model.addAttribute("jszyyjApplyMap" , jszyyjApplyMap);
            }
        }
		//从对应的数据库加载到数据，放到模型供显示
		if(StringUtil.isNotBlank(recFlow)){
			Map<String , Object> resultMap = new HashMap<String , Object>();
			PubProjRec projRec = this.projRecBiz.readProjRec(recFlow);
			model.addAttribute("projRec" , projRec);
			if(projRec !=null){
				if(StringUtil.isNotBlank(projRec.getPageGroupName())) {//如果表单类型不为空 则获取当前表单页面
					pageGroup = this.projPageBiz.getPageGroup(recTypeId, projRec.getPageGroupName());
					if (StringUtil.isBlank(pageName)) {
						pageName = pageGroup.getFirstPage();
					}
					page = _getPage(projRec.getPageGroupName(), pageName, recTypeId);
				}
			resultMap = JspFormUtil.parseXmlStr(projRec.getRecContent() , page);
			model.addAttribute("resultMap", resultMap);
			}

			//获取附件列表
			List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
			model.addAttribute("fileFlows" , fileFlows);
			Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
			if(pageFileMap!=null){
				model.addAttribute("pageFileMap" , pageFileMap);
			}
		}

		//return this.projPageBiz.getJspPath(page, null);
		model.addAttribute("jspPath" , this.projPageBiz.getJspPath(page, null));
	    return "srm/proj/form/formCommon";

	}

	/**
	 * 保存步骤
	 * pageName         当前步骤名称
	 * nextPageName     下一步骤名称
	 * projFlow         项目流水号
	 * projRecFlow      项目操作记录流水号
	 * recTypeId        记录类型
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/saveStep" },method={RequestMethod.POST,RequestMethod.GET})
	public RedirectView saveStep(HttpServletRequest request, HttpServletResponse response , Model model){
		Map<String , String[]> dataMap = JspFormUtil.getParameterMap(request);
		String pageName = dataMap.get("pageName")[0];
		String nextPageName = dataMap.get("nextPageName")[0];
		String projFlow = dataMap.get("projFlow")[0];
		String recFlow = dataMap.get("recFlow")[0];
		String recTypeId = dataMap.get("recTypeId")[0];
		String pageGroupName = null;
		if(null != dataMap.get("pageGroupName") && dataMap.get("pageGroupName").length>0) {
			pageGroupName = dataMap.get("pageGroupName")[0];
		}
        //原附件file_flow
        String[] fileFlows = dataMap.get("file_flow");
        //新上传附件file_flow
        String[] newFileFlows =dataMap.get("file");
        if(fileFlows != null && newFileFlows != null) {//已经存在文件，而且新上传了文件
            List<String> filelist = new ArrayList<>(Arrays.asList(fileFlows));//原文件list
            List<String> newfilelist =new ArrayList<>(Arrays.asList(newFileFlows));//新文件list
            filelist.removeAll(newfilelist);
            fileBiz.deleteFile(filelist);//把原文件删除
        }else if(fileFlows != null && newFileFlows == null){//新上传附件为null（没有附件）
            List<String> filelist = new ArrayList<>(Arrays.asList(fileFlows));
            fileBiz.deleteFile(filelist);
        }
		//根据项目流水号获取该项目的详细信息
		PubProj proj = this.projBiz.readProject(projFlow);
		//获取项目类型Id
		String projTypeId = proj.getProjTypeId();
        if(projTypeId.split("\\.")[0].equals("xzzxyy")){
            projTypeId = "xzzxyy";
        }
		if(StringUtil.isNotBlank(pageGroupName)){//江苏省中科研院外项目进展报告
			projTypeId = pageGroupName;
		}
		//根据项目类型Id,当前步骤名称,记录类型Id
		Page page =_getPage(projTypeId , pageName , recTypeId);
		Page nextPage = _getPage(projTypeId , nextPageName , recTypeId);
		String[] data = _getStageAndStatusByRecTypeId(recTypeId);
		//申报书 , 合同 , 验收 ， 中止 个数校验
		if((ProjRecTypeEnum.Apply.getId().equals(recTypeId) ||
			ProjRecTypeEnum.Contract.getId().equals(recTypeId) ||
			ProjRecTypeEnum.CompleteReport.getId().equals(recTypeId) ||
			ProjRecTypeEnum.TerminateReport.getId().equals(recTypeId)) &&
			StringUtil.isBlank(recFlow)){
			PubProjRec existsRec = this.projRecBiz.selectProjRecByProjFlowAndRecType(projFlow, recTypeId);
			recFlow = existsRec == null ? "": existsRec.getRecFlow();
		}
		if(StringUtil.isBlank(recFlow)){
			//新增
			String xmlStr = JspFormUtil.updateXmlStr("",page, dataMap);
			PubProjRec projRec = new PubProjRec();
			projRec.setProjFlow(projFlow);
			projRec.setProjStageId(data[0]);
			projRec.setProjStageName(data[1]);
			projRec.setProjStatusId(data[2]);
			projRec.setProjStatusName(data[3]);
			projRec.setRecContent(xmlStr);
			if(StringUtil.isNotBlank(pageGroupName)) {
				projRec.setPageGroupName(pageGroupName);
			}
			projRec.setRecTypeId(recTypeId);
			projRec.setRecTypeName(ProjRecTypeEnum.valueOf(recTypeId).getName());

			this.projMineBiz.addRecAndProcess(projRec);
			recFlow = projRec.getRecFlow();
		}else{
			this.projPageBiz.modProjRecContentItem(recFlow, page, dataMap);
		}
//		response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		if("finish".equals(nextPageName)){
			String order = _showTab(null , data[0]);
//			return "redirect:/srm/proj/mine/process?projFlow="+projFlow+"&order="+order;
			String WsUrlConf = "/srm/proj/mine/process?projFlow="+projFlow+"&order="+order;
			RedirectView rv =new RedirectView(WsUrlConf,true,false,false);//最后的参数为false代表以post方式提交请求
			rv.setStatusCode(HttpStatus.SEE_OTHER);
			return rv;
		}else if("zkFinish".equals(nextPageName)){//'zkFinish'重点专科合同表单结束
			String WsUrlConf = "/srm/proj/add/projContractList/zk";
			RedirectView rv =new RedirectView(WsUrlConf,true,false,false);//最后的参数为false代表以post方式提交请求
			rv.setStatusCode(HttpStatus.SEE_OTHER);
			return rv;
		}else if("localFinish".equals(nextPageName)){//医院管理员编辑页面结束
			String WsUrlConf = "/srm/proj/search/recList?projFlow="+projFlow;
			RedirectView rv =new RedirectView(WsUrlConf,true,false,false);//最后的参数为false代表以post方式提交请求
			rv.setStatusCode(HttpStatus.SEE_OTHER);
			return rv;
		}else{
//			return "redirect:showStep?pageName="+nextPage.getName()+"&projFlow="+projFlow+"&recFlow="+recFlow+"&recTypeId="+recTypeId;
			String WsUrlConf = "showStep?pageName="+nextPage.getName()+"&projFlow="+projFlow+"&recFlow="+recFlow+"&recTypeId="+recTypeId;
			RedirectView rv =new RedirectView(WsUrlConf,true,false,false);//最后的参数为false代表以post方式提交请求
			rv.setStatusCode(HttpStatus.SEE_OTHER);
			return rv;
		}

	}

	//用于步骤组的显示
		@RequestMapping(value="/showPageGroupStep" , method=RequestMethod.GET)
		public String showPageGroupStep(String pageName , String projFlow , String recFlow , String itemGroupName , String itemGroupFlow , Model model){
			PubProj proj = this.projBiz.readProject(projFlow);
			model.addAttribute("proj" , proj);
			String projTypeId = proj.getProjTypeId();
            if(projTypeId.split("\\.")[0].equals("xzzxyy")){
                projTypeId = "xzzxyy";
            }
			//获取recTypeId
			PubProjRec projRec = this.projRecBiz.readProjRec(recFlow);
			model.addAttribute("projRec" , projRec);
			String recTypeId = projRec.getRecTypeId();
			Page page = _getPage(projTypeId , pageName , recTypeId);
			//如果projRecFlow flow 不为空的话 表示编辑数据 此时应该先查询 放入模型中 用于页面显示
			Map<String , Object> resultMap = this.projPageBiz.getItemGroupDataMap(recFlow, page, itemGroupFlow);
			model.addAttribute("resultMap" , resultMap);
			//获取附件列表
			List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
			model.addAttribute("fileFlows" , fileFlows);
			Map<String , PubFile> pageFileMap = this.projBiz.getFile(resultMap);
			if(pageFileMap!=null){
				model.addAttribute("pageFileMap" , pageFileMap);
			}
			return this.projPageBiz.getJspPath(page, itemGroupName);

		}

		//用于步骤组的保存
		@RequestMapping(value="/savePageGroupStep" , method=RequestMethod.POST)
		@ResponseBody
		public String savePageGroupStep(HttpServletRequest request , Model model){
			boolean isMultipare = JspFormUtil.isMultipart(request);
			String msg = "";
				Map<String , String[]> dataMap = JspFormUtil.getParameterMap(request);
				String recFlow = dataMap.get("recFlow")[0];
				String pageName = dataMap.get("pageName")[0];
				String projFlow = dataMap.get("projFlow")[0];
				String itemGroupName = dataMap.get("itemGroupName")[0];
				String itemGroupFlow = dataMap.get("itemGroupFlow")[0];

				PubProj proj = this.projBiz.readProject(projFlow);
				String projTypeId = proj.getProjTypeId();
                if(projTypeId.split("\\.")[0].equals("xzzxyy")){
                    projTypeId = "xzzxyy";
                }
				PubProjRec projRec = this.projRecBiz.readProjRec(recFlow);
				String recTypeId = projRec.getRecTypeId();
				Page page = _getPage(projTypeId , pageName , recTypeId);
				this.projPageBiz.modProjRecContentItemGroup(recFlow, page, dataMap, itemGroupFlow, itemGroupName, GlobalConstant.FLAG_Y);
				if(isMultipare){
					msg = "<script>parent.callBack('"+GlobalConstant.OPRE_SUCCESSED_FLAG+"');</script>";
				}else{
					msg = GlobalConstant.SAVE_SUCCESSED;
				}
			return msg;
		}


		//用于步骤组的删除
		@RequestMapping(value="/delPageGroupStep" , method=RequestMethod.POST)
		@ResponseBody
		public String delPageGroupStep(String pageName , String projFlow , String recFlow , String itemGroupName , String itemGroupFlow , HttpServletRequest request , Model model){
			PubProj proj = this.projBiz.readProject(projFlow);
			String projTypeId = proj.getProjTypeId();
            if(projTypeId.split("\\.")[0].equals("xzzxyy")){
                projTypeId = "xzzxyy";
            }
			//获取recTypeId
			PubProjRec projRec = this.projRecBiz.readProjRec(recFlow);
			String recTypeId = projRec.getRecTypeId();
			Page page = _getPage(projTypeId , pageName , recTypeId);
			this.projPageBiz.modProjRecContentItemGroup(recFlow, page, null , itemGroupFlow, itemGroupName, GlobalConstant.FLAG_N);
			return GlobalConstant.DELETE_SUCCESSED;
		}

		//用于附件清单的删除
		@RequestMapping(value="/delFileItemGroup" , method=RequestMethod.POST)
		@ResponseBody
		public String delFileItemGroup(String pageName , String projFlow , String recFlow , String itemGroupName , String itemGroupFlow , HttpServletRequest request , String delFileFlag){
			PubProj proj = this.projBiz.readProject(projFlow);
			String projTypeId = proj.getProjTypeId();
            if(projTypeId.split("\\.")[0].equals("xzzxyy")){
                projTypeId = "xzzxyy";
            }
			//获取recTypeId
			PubProjRec projRec = this.projRecBiz.readProjRec(recFlow);
			String recTypeId = projRec.getRecTypeId();
			Page page = _getPage(projTypeId , pageName , recTypeId);
			this.projPageBiz.delFileItemGroup(recFlow, page, null , itemGroupFlow, itemGroupName, delFileFlag);
			return GlobalConstant.DELETE_SUCCESSED;
		}

	/**
	 * 用于删除进展报告 ， 季报 ， 延迟申请报告 ， 变更报告
	 * @param recFlow
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/delReport" },method={RequestMethod.GET})
	@ResponseBody
	public  String  delReport(@RequestParam(value="recFlow" , required=true) String recFlow, Model model,HttpServletRequest request) {
		PubProjRec rec = new PubProjRec();
		rec.setRecFlow(recFlow);
		this.projRecBiz.delProjRec(rec);
		return GlobalConstant.DELETE_SUCCESSED;
	}

	/**
	 * 根据项目阶段返回用于选项卡的显示
	 * @param order
	 * @param projStageId
	 * @return
	 */
	private String _showTab(String order , String projStageId){
		String tab = "";
		if(StringUtil.isBlank(order)){
			//根据项目所处阶段计算个order
			if(ProjStageEnum.Apply.getId().equals(projStageId)){
				tab = ProjStageEnum.Apply.getId();
			}else if(ProjStageEnum.Approve.getId().equals(projStageId)){
				tab = ProjStageEnum.Approve.getId();
			}else if(ProjStageEnum.Schedule.getId().equals(projStageId)){
				tab = ProjStageEnum.Schedule.getId();
			}else if(ProjStageEnum.Complete.getId().equals(projStageId)){
				tab = ProjStageEnum.Complete.getId();
			}else if(ProjStageEnum.Contract.getId().equals(projStageId)){
				tab = ProjStageEnum.Contract.getId();
			}else if(ProjStageEnum.Archive.getId().equals(projStageId)){
				tab = ProjStageEnum.Complete.getId();
			}
		}else{
			tab = order;
		}
		return tab;
	}

	/**
	 * 清空recContent
	 * @return
	 */
	@RequestMapping("/delRecContent")
	@ResponseBody
	public String delRecContent(PubProjRec rec){
		String recFlow = rec.getRecFlow();
		String projFlow = rec.getProjFlow();
		String recTypeId = rec.getRecTypeId();
		if(StringUtil.isNotBlank(recFlow)){
			PubProjRec oldRec = this.projRecBiz.readProjRec(recFlow);
			oldRec.setRecContent("");
			this.projRecBiz.modProjRecWithXml(oldRec);
			return GlobalConstant.OPRE_SUCCESSED;
		}else if(StringUtil.isNotBlank(projFlow) && StringUtil.isNotBlank(recTypeId)){
			this.projMineBiz.delRecContent(projFlow, recTypeId);
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
}

	/**
	 * 送审
	 * @param projFlow
	 * @param recTypeId
	 * @param recFlow
	 * @param model
     * @return
     */
	@RequestMapping(value="/prepareReview")
	@ResponseBody
	public String prepareReview(@RequestParam(value="projFlow" , required=true)String projFlow ,
			@RequestParam(value="recTypeId" , required=false)String recTypeId  ,
			@RequestParam(value="recFlow" , required=false)String recFlow , Model model){

		String msg = GlobalConstant.OPRE_FAIL_FLAG;//默认操作失败
		PubProjRec projRec = null;
		//如果要送审的是申报书的recId
		if(StringUtil.isNotBlank(recTypeId) && ProjRecTypeEnum.Apply.getId().equals(recTypeId)){
			//判断是否有申报书
			PubProj proj = this.projBiz.readProject(projFlow);
            String projTypeId = proj.getProjTypeId();
            if(projTypeId.split("\\.")[0].equals("xzzxyy")){
                projTypeId = "xzzxyy";
            }
			String pageGroupName = InitConfig.configMap.get(ProjRecTypeEnum.Apply.getId()).get(projTypeId);
			if(StringUtil.isBlank(pageGroupName)){
				//没有申报书 直接送审项目
				proj.setProjStatusId(ProjApplyStatusEnum.Submit.getId());
				proj.setProjStatusName(ProjApplyStatusEnum.Submit.getName());
				this.projMineBiz.prepareReview(proj);
				msg = GlobalConstant.OPRE_SUCCESSED_FLAG;
				return msg;
			}
		}

		if(StringUtil.isNotBlank(recFlow)){
			projRec = this.projRecBiz.readProjRec(recFlow);
		}else if(StringUtil.isNotBlank(projFlow) && StringUtil.isNotBlank(recTypeId)){
			//先查询rec
			projRec = this.projRecBiz.selectProjRecByProjFlowAndRecType(projFlow, recTypeId);
		}

		if(projRec!=null){
			if(StringUtil.isBlank(projRec.getRecContent())){
				msg = GlobalConstant.OPRE_FAIL_FLAG;
				return msg;
			}
			String[] data = _getStageAndStatusByRecTypeId(projRec.getRecTypeId());
			projRec.setProjStatusId(data[4]);
			projRec.setProjStatusName(data[5]);
			try{
				this.projMineBiz.prepareReview(projRec);
				msg = GlobalConstant.OPRE_SUCCESSED_FLAG;
			}catch(Exception e){
				e.printStackTrace();
				msg = GlobalConstant.OPRE_FAIL_FLAG;
			}

		}

		return msg;
	}

	@RequestMapping("/del")
	@ResponseBody
	public String del(String projFlow){
		//首先要判断该项目是否可以删除 当时申报阶段 填写状态的时候才可以删除
		PubProj proj = this.projBiz.readProject(projFlow);
		if(ProjStageEnum.Apply.getId().equals(proj.getProjStageId()) && (ProjApplyStatusEnum.Apply.getId().equals(proj.getProjStatusId()) || ProjApplyStatusEnum.Submit.getId().equals(proj.getProjStatusId()) )){
			proj.setRecordStatus(GlobalConstant.FLAG_N);
			this.projBiz.modProject(proj);
			return GlobalConstant.DELETE_SUCCESSED;
		}else{
			return "该项目暂不能删除";
		}
	}


	private Page _getPage(String projTypeId, String pageName , String recTypeId){
		return this.projPageBiz.getPage(recTypeId , projTypeId , pageName );
	}

	private String[] _getStageAndStatusByRecTypeId(String recTypeId){
		String[] data = new String[6];
		if(ProjRecTypeEnum.Apply.getId().equals(recTypeId)){
			data[0] = ProjStageEnum.Apply.getId();
			data[1] = ProjStageEnum.Apply.getName();
			data[2] = ProjApplyStatusEnum.Apply.getId();
			data[3] = ProjApplyStatusEnum.Apply.getName();
			data[4] = ProjApplyStatusEnum.Submit.getId();
			data[5] = ProjApplyStatusEnum.Submit.getName();
		}else if(ProjRecTypeEnum.Contract.getId().equals(recTypeId)){
			data[0] = ProjStageEnum.Contract.getId();
			data[1] = ProjStageEnum.Contract.getName();
			data[2] = ProjContractStatusEnum.Apply.getId();
			data[3] = ProjContractStatusEnum.Apply.getName();
			data[4] = ProjContractStatusEnum.Submit.getId();
			data[5] = ProjContractStatusEnum.Submit.getName();
		}else if(ProjRecTypeEnum.ScheduleReport.getId().equals(recTypeId) ||
				 ProjRecTypeEnum.ChangeReport.getId().equals(recTypeId) ||
				 ProjRecTypeEnum.DelayReport.getId().equals(recTypeId)
				){
			data[0] = ProjStageEnum.Schedule.getId();
			data[1] = ProjStageEnum.Schedule.getName();
			data[2] = ProjScheduleStatusEnum.Apply.getId();
			data[3] = ProjScheduleStatusEnum.Apply.getName();
			data[4] = ProjScheduleStatusEnum.Submit.getId();
			data[5] = ProjScheduleStatusEnum.Submit.getName();
		}else if(ProjRecTypeEnum.CompleteReport.getId().equals(recTypeId) ||
				 ProjRecTypeEnum.TerminateReport.getId().equals(recTypeId)
				){
			data[0] = ProjStageEnum.Complete.getId();
			data[1] = ProjStageEnum.Complete.getName();
			data[2] = ProjCompleteStatusEnum.Apply.getId();
			data[3] = ProjCompleteStatusEnum.Apply.getName();
			data[4] = ProjCompleteStatusEnum.Submit.getId();
			data[5] = ProjCompleteStatusEnum.Submit.getName();
		}

		return data;
	}

	/**
	 * 根据阶段id在项目进入页面显示不同的图片
	 * key分别为0,1,2,3,4这5个位置
	 * value 分别为1,2 这2个class
	 * @param stageId
	 * @return
	 */
	public Map<String , String> showIcnByStageId(String stageId){
		//所有位置默认为 2
		Map<String , String> icnMap = new HashMap<String, String>();
		for(int i=0 ; i<5 ; i++){
			icnMap.put(String.valueOf(i), "1");
		}
		if(ProjStageEnum.Apply.getId().equals(stageId)){
			icnMap.put("0", "2");
			return icnMap;
		}else if(ProjStageEnum.Approve.getId().equals(stageId)){
			icnMap.put("0", "2");
			icnMap.put("1", "2");
			return icnMap;
		}else if(ProjStageEnum.Award.getId().equals(stageId)){
			icnMap.put("0", "2");
			icnMap.put("1", "2");
			return icnMap;
		}else if(ProjStageEnum.Contract.getId().equals(stageId)){
			icnMap.put("0", "2");
			icnMap.put("1", "2");
			icnMap.put("2", "2");
			return icnMap;
		}else if(ProjStageEnum.Schedule.getId().equals(stageId)){
			icnMap.put("0", "2");
			icnMap.put("1", "2");
			icnMap.put("2", "2");
			icnMap.put("3", "2");
			return icnMap;
		}else if(ProjStageEnum.Complete.getId().equals(stageId)){
			icnMap.put("0", "2");
			icnMap.put("1", "2");
			icnMap.put("2", "2");
			icnMap.put("3", "2");
			icnMap.put("4", "2");
			return icnMap;
		}else if(ProjStageEnum.Archive.getId().equals(stageId)){
			icnMap.put("0", "2");
			icnMap.put("1", "2");
			icnMap.put("2", "2");
			icnMap.put("3", "2");
			icnMap.put("4", "2");
			return icnMap;
		}
		return icnMap;

	}


	/**
	 * 负责人视图
	 * @param
	 * @return
	 */
	@RequestMapping("/index")
	public String index(Model model){
		//默认查科研
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, ProjCategroyEnum.Ky.getId());
		SysUser currUser = GlobalContext.getCurrentUser();
		PubProj proj = new PubProj();
		proj.setApplyUserFlow(currUser.getUserFlow());
		List<PubProj> projList = this.projMineBiz.searchProjList(proj);
		model.addAttribute("projCount",projList.size());
		Map<String,Integer> projTypeCountMap = new HashMap<String, Integer>();
		Integer scheduleCount = 0;
		Integer completeCount = 0;
		Integer archiveCount = 0;
		for(PubProj temp : projList){
			if(StringUtil.isNotBlank(temp.getProjTypeName())){
				Integer count = projTypeCountMap.get(temp.getProjTypeName());
				if(count== null){
					count = 0;
				}
				count++;
				projTypeCountMap.put(temp.getProjTypeName(), count);
			}
			if(ProjStageEnum.Archive.getId().equals(temp.getProjStageId())){
				archiveCount++;
			}
			if(ProjStageEnum.Schedule.getId().equals(temp.getProjStageId())){
				scheduleCount++;
			}
			if(ProjStageEnum.Complete.getId().equals(temp.getProjStageId())){
				completeCount++;
			}
		}
		model.addAttribute("scheduleCount",scheduleCount);
		model.addAttribute("completeCount",completeCount);
		model.addAttribute("archiveCount",archiveCount);
		model.addAttribute("projTypeCountMap",projTypeCountMap);
		return "srm/view/personal";
	}


	/**
	 * 项目申请限制
	 * @param userFlow
	 * @param applyLimit
	 * @return
	 */
	@RequestMapping(value="/queryProjApplyLimit")
	@ResponseBody
	public String queryProjApplyLimit(String userFlow, Integer applyLimit, String projCategoryId){
		if(StringUtil.isNotBlank(userFlow) && applyLimit != null){
			PubProj proj = new PubProj();
			proj.setProjYear(DateUtil.getYear());
			proj.setApplyUserFlow(userFlow);
			proj.setProjCategoryId(projCategoryId);
			int projCount = projMineBiz.searchProjCount(proj);
			if(projCount < applyLimit){
				return GlobalConstant.FLAG_Y;
			}else{
				return GlobalConstant.FLAG_N;
			}
		}
		return null;
	}

	/**
	 * 检查文件大小
	 * @param file
	 * @return
	 */
	@RequestMapping(value="/checkFile")
	@ResponseBody
	public String checkFile(@RequestParam("fileEdit_file") MultipartFile file) {
		if(file.getSize() >= 0){
			int limitSize = 5;//文件大小限制
			if(file.getSize()>limitSize*1024*1024){
				return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M" ;
			}
			return GlobalConstant.FLAG_Y;//可执行保存
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
}
