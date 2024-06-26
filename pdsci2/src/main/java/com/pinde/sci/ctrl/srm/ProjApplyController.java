package com.pinde.sci.ctrl.srm;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.IApplyLimitBiz;
import com.pinde.sci.biz.srm.IProjApplyBiz;
import com.pinde.sci.biz.srm.IProjRecBiz;
import com.pinde.sci.biz.srm.IPubProjBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.srm.ProjApplyStatusEnum;
import com.pinde.sci.enums.srm.ProjStageEnum;
import com.pinde.sci.form.srm.ApplyLimitNumForm;
import com.pinde.sci.form.srm.SrmApplyLimitForm;
import com.pinde.sci.model.mo.*;

import java.util.Map;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/srm/proj/apply")
public class ProjApplyController extends GeneralController{    
	
	private static Logger logger = LoggerFactory.getLogger(ProjApplyController.class);
	
	@Autowired
	private IProjApplyBiz projApplyBiz; 
	@Autowired
	private IProjRecBiz projRecBiz; 
	@Autowired
	private IPubProjBiz pubProjBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IApplyLimitBiz applyLimitBiz;

	
	/**
	 * 显示审核列表
	 * @param projListScope
	 * @param recTypeId
	 * @param proj
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/list/{projListScope}/{projCateScope}",method={RequestMethod.POST,RequestMethod.GET})
	public String list(@PathVariable String projListScope,@PathVariable String projCateScope,String recTypeId, PubProj proj ,String startYear,String endYear,String defaultTerm, Integer currentPage , Model model,HttpServletRequest request) {
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
		
		SysUser currUser = GlobalContext.getCurrentUser();
		List<PubProj> projList = new ArrayList<PubProj>();
		//手动设置阶段 此时阶段是申报阶段
		proj.setProjStageId(ProjStageEnum.Apply.getId());
		//手动设置项目类型
		proj.setProjCategoryId(projCateScope);
		//设置默认条件
		if(StringUtil.isNotBlank(defaultTerm)){
			model.addAttribute("defaultTerm",defaultTerm);
			proj.setProjYear(DateUtil.getYear());
		}
		//用于查询机构列表
		//SysOrg sysOrg = new SysOrg();
		if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL .equals(projListScope)){
			proj.setProjStatusId(ProjApplyStatusEnum.Submit.getId());
			PageHelper.startPage(currentPage, getPageSize(request));
			projList = projApplyBiz.searchLocalProj(proj,recTypeId,startYear,endYear);
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE .equals(projListScope)){
			proj.setProjStatusId(ProjApplyStatusEnum.FirstAudit.getId());
			PageHelper.startPage(currentPage, getPageSize(request));
			projList = projApplyBiz.searchChargeProj(proj,recTypeId);
			//加载下级医院
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

			PageHelper.startPage(currentPage, getPageSize(request));
			projList = projApplyBiz.searchGlobalProj(proj,recTypeId);
			List<SysOrg> chargeOrgList = this.orgBiz.searchChargeOrg();
			model.addAttribute("chargeOrgList",chargeOrgList);
		}
		model.addAttribute("projList",projList);
		setSessionAttribute(GlobalConstant.PROJ_RECORD_TYPE, recTypeId);
		return "srm/proj/apply/list_"+projCateScope;
		//return "srm/proj/apply/test2";
	}
	
	@RequestMapping(value="/exportExcel/{projListScope}/{projCateScope}", method={RequestMethod.POST,RequestMethod.GET})
	public void exportExcel(@PathVariable String projListScope, @PathVariable String projCateScope, String recTypeId, PubProj proj, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
		SysUser currUser = GlobalContext.getCurrentUser();
		List<PubProj> projList = new ArrayList<PubProj>();
		//手动设置阶段 此时阶段是申报阶段
		proj.setProjStageId(ProjStageEnum.Apply.getId());
		//手动设置项目类型
		proj.setProjCategoryId(projCateScope);
		if(GlobalConstant.PROJ_STATUS_SCOPE_LOCAL .equals(projListScope)){
			proj.setProjStatusId(ProjApplyStatusEnum.Submit.getId());
			projList = projApplyBiz.searchLocalProj(proj,recTypeId,null,null);
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE .equals(projListScope)){
			proj.setProjStatusId(ProjApplyStatusEnum.FirstAudit.getId());
			projList = projApplyBiz.searchChargeProj(proj,recTypeId);
		}else if(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL .equals(projListScope)){
			projList = projApplyBiz.searchGlobalProj(proj,recTypeId);
		}
		String fileName = "项目导出表.xls";
	    fileName = URLEncoder.encode(fileName, "UTF-8");
	    ExcleUtile.exportSimpleExcle(titles, projList, PubProj.class, response.getOutputStream());
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");  
	    response.setContentType("application/octet-stream;charset=UTF-8");
	}
	
	
	/**
	 * 点击审核操作
	 * @param projFlow
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/audit"},method={RequestMethod.GET})
	public  String  audit( String projFlow, Model model,HttpServletRequest request) {
		String projCateScope = (String) getSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE);
		PubProj proj = pubProjBiz.readProject(projFlow);
		model.addAttribute("proj",proj);
		return "srm/proj/apply/audit_"+projCateScope;
	}

    /**
     * 保存审核
     * @param projListScope
     * @param projCateScope
     * @param agreeFlag
     * @param auditContent
     * @param acceptNumber 受理编号
     * @param projFlow
     * @param model
     * @param request
     * @return
     */
	@RequestMapping(value="/saveAudit/{projListScope}/{projCateScope}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String saveAudit(@PathVariable String projListScope,@PathVariable String projCateScope,String agreeFlag,String auditContent,String acceptNumber, String projFlow, Model model,HttpServletRequest request) {
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		this.projApplyBiz.applyAudit(projFlow,projListScope,agreeFlag,auditContent,acceptNumber);
		return GlobalConstant.OPRE_SUCCESSED;
	}
	
	/**
	 * 检查机构限报数
	 * @param projListScope
	 * @param agreeFlag
	 * @param projFlow
	 * @return
	 */
	@RequestMapping(value="/checkApplyLimit/{projListScope}",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public ApplyLimitNumForm checkApplyLimit(@PathVariable String projListScope, String agreeFlag, String projFlow) {
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		return applyLimitBiz.checkApplyLimit(projListScope, agreeFlag, projFlow);
	}
	/**
	 * 保存机构限制
	 * @param customerUser
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveOrgApplyLimitList")
	@ResponseBody
	public String saveOrgApplyLimitList(@RequestBody SrmApplyLimitForm form){
		List<SrmApplyLimit> applyLimitList = form.getApplyLimitList();
		int result = applyLimitBiz.saveApplyLimitList(applyLimitList);
		if(result != GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}


	/**
	 * 机构限制
	 * @param orgFlow
	 * @param model
	 * @return
	 */
	@RequestMapping("/editOrgApplyLimit")
	public String editOrgApplyLimit(String orgFlow, Model model){
		if(StringUtil.isNotBlank(orgFlow)){
			SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
			model.addAttribute("sysOrg", sysOrg);
			SrmApplyLimit applyLimit = new SrmApplyLimit();
			applyLimit.setOrgFlow(orgFlow);
			List<SrmApplyLimit> applyLimitList = applyLimitBiz.searchApplyLimitList(applyLimit);
			if(applyLimitList != null && !applyLimitList.isEmpty()){
				Map<String, SrmApplyLimit> applyLimitMap = new HashMap<String, SrmApplyLimit>();
				for(SrmApplyLimit al :applyLimitList){
					SrmApplyLimit temp = applyLimitMap.get(al.getProjTypeId());
					if(temp == null){
						temp = al;
					}
					applyLimitMap.put(al.getProjTypeId(), temp);
				}
				model.addAttribute("applyLimitMap", applyLimitMap);
			}
		}
		return "srm/proj/apply/cfg/editOrgApplyLimit";
	}



	@RequestMapping("/viewallorgapplylimitinfo")
	public String viewAllOrgApplyLimitInfo(Model model){
		SrmApplyLimit applyLimit = new SrmApplyLimit();
		List<SrmApplyLimit> applyLimitInfos = this.applyLimitBiz.searchApplyLimitList(applyLimit);
		Map<String , Map<String , String>> applyLimitInfoMap = new HashMap<String, Map<String,String>>();
		Map<String , String> orgs = new HashMap<String, String>();
		Map<String , String> limitNumMap = null;
		for(SrmApplyLimit sal:applyLimitInfos){
			String orgFlow = sal.getOrgFlow();
			orgs.put(orgFlow, sal.getOrgName());
			Short limitNum = sal.getLimitNum();
			String limitNumStr = "";
			if(limitNum!=null){
				limitNumStr = String.valueOf(limitNum);
			}
			if(applyLimitInfoMap.containsKey(orgFlow)){
				limitNumMap = applyLimitInfoMap.get(orgFlow);
				limitNumMap.put(sal.getProjTypeId(), limitNumStr);
			}else{
				limitNumMap = new HashMap<String, String>();
				limitNumMap.put(sal.getProjTypeId(), limitNumStr);
				applyLimitInfoMap.put(orgFlow, limitNumMap);
			}
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("applyLimitInfoMap" , applyLimitInfoMap);
		return "srm/proj/apply/cfg/allorgapplylimitinfo";
	}
}
