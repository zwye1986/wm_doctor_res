 package com.pinde.sci.ctrl.srm;

 import com.pinde.core.util.DateUtil;
 import com.pinde.sci.biz.srm.IAidProjBiz;
 import com.pinde.sci.biz.srm.IProjSearchBiz;
 import com.pinde.sci.biz.srm.IPubCountBiz;
 import com.pinde.sci.biz.sys.IOrgBiz;
 import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.UserStatusEnum;
 import com.pinde.sci.enums.srm.*;
 import com.pinde.sci.form.srm.PubProjCount;
 import com.pinde.sci.model.mo.*;
 import com.pinde.sci.model.pub.PubProjCountExt;
import com.pinde.sci.model.srm.SrmAchCountExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
 import java.util.*;

 @Controller
@RequestMapping("/srm/view")
public class PubCountController extends GeneralController{

	@Autowired
	private IPubCountBiz pubCountBizImpl;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IAidProjBiz aidProjBiz;
	@Autowired
	private IProjSearchBiz projSeeBiz;
	
	@RequestMapping(value="/list/{projListScope}")
	private String view(@PathVariable String projListScope, Model model){

		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		List<Map<String, Integer>> resultMapList=null;
		Map<String,Integer> resultMap=new HashMap<String, Integer>();
		Map<String,Integer> resultMap_ky=null;
		Map<String,Integer> resultMap_xk=null;
		Map<String,Integer> resultMap_rc=null;
		Map<String,Integer> srmAchMap=null;
		SysOrg org = new SysOrg();
		SysUser currUser = GlobalContext.getCurrentUser();
		org.setChargeOrgFlow(currUser.getOrgFlow());
		//查询当前机构下及其子机构所有国家级项目
		int aidProjProvinceCount=countAidProj(AidProjCategoryEnum.Province.getId());
		model.addAttribute("aidProjProvinceCount", aidProjProvinceCount);
		//查询当前机构下及其子机构所有省级项目
		int aidProjCountryCount=countAidProj(AidProjCategoryEnum.Country.getId());
		model.addAttribute("aidProjCountryCount", aidProjCountryCount);
		//查询当前机构下及其子机构所有市厅级项目
		int aidProjCityCount=countAidProj(AidProjCategoryEnum.City.getId());
		model.addAttribute("aidProjCityCount", aidProjCityCount);
		//查询当前登录者所在机构的下一级不包括自己的所有机构
		List<SysOrg> orgList=orgBiz.searchChildrenOrgByOrgFlowNotIncludeSelf(currUser.getOrgFlow());
		
		//当前登录者是卫生局
		if(projListScope.equals(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL)){
			//待办事项的统计
			resultMapList=pubCountBizImpl.selectCountProjGlobal(org);
			resultMap_ky=resultMapList.get(0);
			resultMap_xk=resultMapList.get(1);
			resultMap_rc=resultMapList.get(2);
			//查询当前机构下待审核的重点人才申请
			int aidTalentsCount=countAidTalents(AidTalentsStatusEnum.LocalPassed.getId());
				//医院：如果待审核核通过的数量大于零，则存入Map
				if(aidTalentsCount>0){
					resultMap.put("aidTalentsCount", aidTalentsCount);
				}
            //调用方法查询当前机构可以审核的人员数量
			Map<String, Integer> regedUserMap = regedUserCount();
			resultMap.putAll(regedUserMap);
			if(null!=resultMap && !resultMap.isEmpty()){
				model.addAttribute("resultMap", resultMap);
			}
			
		    //统计当前机构评审与立项情况
			LinkedHashMap<String,String> projCountInfoMap=countApproveInfo();
			 
			//统计当前卫生局所辖机构承担的项目数量
			LinkedHashMap<String, String> orgCountInfoMap=countOrgProj();
			
			//统计当前卫生局所承担的成果类型及数量
			LinkedHashMap<String, String> srmAchCountMap=countSrmAch();
			model.addAttribute("resultMap_ky", resultMap_ky);
			model.addAttribute("resultMap_xk", resultMap_xk);
			model.addAttribute("resultMap_rc", resultMap_rc);
			model.addAttribute("srmAchCountMap", srmAchCountMap);
			model.addAttribute("orgCountInfoMap", orgCountInfoMap);
			model.addAttribute("projCountInfoMap", projCountInfoMap);
			return "srm/view/global";
		}
		//当前登录者是主管部门
		//待办事项的统计
		else if(projListScope.equals(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE)){
			
			resultMapList=pubCountBizImpl.selectCountProjCharge(org);
			resultMap_ky=resultMapList.get(0);
			resultMap_xk=resultMapList.get(1);
			resultMap_rc=resultMapList.get(2);
			
            if(null!=resultMap && !resultMap.isEmpty()){
            	model.addAttribute("resultMap", resultMap);
			}
            
          //调用方法查询当前机构可以审核的人员数量
			Map<String, Integer> regedUserMap = regedUserCount();
			resultMap.putAll(regedUserMap);
			if(null!=resultMap && !resultMap.isEmpty()){
				model.addAttribute("resultMap", resultMap);
			}
            
            //统计当前机构评审与立项情况
			LinkedHashMap<String,String> projCountInfoMap=countApproveInfo();
			
			//统计当前主管部门所辖机构承担的项目数量
			LinkedHashMap<String, String> orgCountInfoMap=countOrgProj();
			//统计当前主管部门所承担的成果类型及数量
			LinkedHashMap<String, String> srmAchCountMap=countSrmAch();
			model.addAttribute("resultMap_ky", resultMap_ky);
			model.addAttribute("resultMap_xk", resultMap_xk);
			model.addAttribute("resultMap_rc", resultMap_rc);
			model.addAttribute("srmAchCountMap", srmAchCountMap);
			model.addAttribute("orgCountInfoMap", orgCountInfoMap);
			model.addAttribute("projCountInfoMap", projCountInfoMap);
			return "srm/view/charge";
		}//当前登录者是财务部门
		//待办事项的统计
		else if(projListScope.equals(GlobalConstant.USER_LIST_FINANCE)){
			resultMapList=pubCountBizImpl.selectCountProjLocal(org);
			resultMap_ky=resultMapList.get(0);
			//院版
			if("local".equals(InitConfig.getSysCfg("srm_for_use"))){
				//查询管理费审核的数量
				Integer manageFeeAuditCount = this.pubCountBizImpl.findDealPaymentAuditCount(projListScope, ProjFundTypeEnum.ManageFee.getId());
				if(manageFeeAuditCount>0){
					resultMap_ky.put("manageFeeAuditCount" , manageFeeAuditCount);
				}

				//查询经费到账审核的数量
				Integer incomeAuditCount = this.pubCountBizImpl.findDealPaymentAuditCount(projListScope, ProjFundTypeEnum.Income.getId());
				if(incomeAuditCount>0){
					resultMap_ky.put("incomeAuditCount" , incomeAuditCount);
				}

				//查询报销审核的数量
				Integer dealPaymentAuditCount = this.pubCountBizImpl.findDealPaymentAuditCount(projListScope, ProjFundTypeEnum.Reimburse.getId());
				if(dealPaymentAuditCount>0){
					resultMap_ky.put("dealPaymentAuditCount" , dealPaymentAuditCount);
				}
				model.addAttribute("resultMap_ky", resultMap_ky);
			}
			return "srm/view/finance";
		}
		//当前登录者是医院
		//待办事项的统计
		else{
			resultMapList=pubCountBizImpl.selectCountProjLocal(org);
			resultMap_ky=resultMapList.get(0);
			resultMap_xk=resultMapList.get(1);
			resultMap_rc=resultMapList.get(2);
			
			//如果是院版的 需要提醒预算审核和报销审核
			if("local".equals(InitConfig.getSysCfg("srm_for_use"))){
				//查询预算审核
				Integer dealBudgetAuditCount = this.pubCountBizImpl.findDealBudgetAuditProjCount();
				if(dealBudgetAuditCount>0){
					resultMap_ky.put("dealBudgetAuditCount", dealBudgetAuditCount);	
				}
				//查询报销审核的数量
				Integer dealPaymentAuditCount = this.pubCountBizImpl.findDealPaymentAuditCount(projListScope,ProjFundTypeEnum.Reimburse.getId());
				if(dealPaymentAuditCount>0){
					resultMap_ky.put("dealPaymentAuditCount" , dealPaymentAuditCount);	
				}
				
			}
			
			//查询当前机构下待审核的专项资金申请
			int aidTalentsCount=countAidTalents(AidTalentsStatusEnum.Passing.getId());
				//医院：如果待审核核通过的数量大于零，则存入Map
				if(aidTalentsCount>0){
					resultMap_rc.put("aidTalentsCount", aidTalentsCount);
				}
			
			 //调用方法查询当前机构可以审核的人员数量
			Map<String, Integer> regedUserMap = regedUserCount();
			resultMap.putAll(regedUserMap);
			
            if(null!=resultMap && !resultMap.isEmpty()){
            	model.addAttribute("resultMap", resultMap);
			}
			srmAchMap=pubCountBizImpl.selectCountSrmAchLocal(org);
			if(null!=srmAchMap && !srmAchMap.isEmpty()){
            	model.addAttribute("srmAchMap", srmAchMap);
			}
			//遍历成果类型枚举，生成一个小写ID开头的Map
			Map<String,String> lowerAchTypeMap=new HashMap<String, String>();
			for(AchTypeEnum str:AchTypeEnum.values()){
				lowerAchTypeMap.put(str.getId(),str.getId().toLowerCase());	
			}
			model.addAttribute("lowerAchTypeMap", lowerAchTypeMap);
			//统计当前机构评审与立项情况
			LinkedHashMap<String,String> projCountInfoMap=countApproveInfo();
			
			//统计当前医院所承担的成果类型及数量
			LinkedHashMap<String, String> srmAchCountMap=countSrmAch();
			model.addAttribute("resultMap_ky", resultMap_ky);
			model.addAttribute("resultMap_xk", resultMap_xk);
			model.addAttribute("resultMap_rc", resultMap_rc);
			model.addAttribute("srmAchCountMap", srmAchCountMap);
			model.addAttribute("projCountInfoMap", projCountInfoMap);
			return "srm/view/local";
		}
		
	
	}

	@RequestMapping(value="/info/{projListScope}")
	private String info(@PathVariable String projListScope, Model model){
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
		List<Map<String, Integer>> resultMapList=null;
		Map<String,Integer> resultMap_ky=null;
		Map<String,Integer> resultMap_qw=null;
		List<PubProjCount> projCountList_ky ;
		List<PubProjCount> projCountList_qw ;
		String year = DateUtil.getYear();
		String lastYear = (Integer.parseInt(DateUtil.getYear())-1)+"";
		SysOrg org = new SysOrg();
		SysUser currUser = GlobalContext.getCurrentUser();
		org.setChargeOrgFlow(currUser.getOrgFlow());
		//查询当前登录者所在机构的下一级不包括自己的所有机构
		List<SysOrg> orgList=orgBiz.searchChildrenOrgByOrgFlowNotIncludeSelf(currUser.getOrgFlow());
		//当前登录者是卫生局
		if(projListScope.equals(GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL)){
			//待办事项的统计
			resultMapList=pubCountBizImpl.selectCountProjGlobal(org);
			resultMap_ky=resultMapList.get(0);
			resultMap_qw=resultMapList.get(1);
			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("projCategoryId","ky");
			paramMap.put("projYear", year);
			model.addAttribute("year_ky",year);
			projCountList_ky = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			if(null == projCountList_ky || projCountList_ky.size() == 0){
				paramMap.put("projYear", lastYear);
				model.addAttribute("year_ky",lastYear);
				projCountList_ky = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			}
			paramMap.clear();
			paramMap.put("projCategoryId","qw");
			paramMap.put("projYear", year);
			model.addAttribute("year_qw",year);
			projCountList_qw = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			if(null == projCountList_qw || projCountList_qw.size() == 0){
				paramMap.put("projYear", lastYear);
				projCountList_qw = pubCountBizImpl.statisticsProjByTypeId(paramMap);
				model.addAttribute("year_qw",lastYear);
			}
			model.addAttribute("projCountList_ky",projCountList_ky);
			model.addAttribute("projCountList_qw",projCountList_qw);
			model.addAttribute("resultMap_ky", resultMap_ky);
			model.addAttribute("resultMap_qw", resultMap_qw);
			return "srm/view/global_info";
		}
		//当前登录者是主管部门
		//待办事项的统计
		else if(projListScope.equals(GlobalConstant.PROJ_STATUS_SCOPE_CHARGE)){

			resultMapList=pubCountBizImpl.selectCountProjCharge(org);
			resultMap_ky=resultMapList.get(0);
			resultMap_qw=resultMapList.get(1);

			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("projCategoryId","ky");
//			paramMap.put("applyOrgFlow","ky");
			paramMap.put("chargeOrgFlow",currUser.getOrgFlow());
			paramMap.put("projYear", year);
			model.addAttribute("year_ky",year);
			projCountList_ky = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			if(null == projCountList_ky || projCountList_ky.size() == 0){
				paramMap.put("projYear", lastYear);
				model.addAttribute("year_ky",lastYear);
				projCountList_ky = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			}
			//paramMap.clear();
			paramMap.put("projCategoryId","qw");
			paramMap.put("projYear", year);
			projCountList_qw = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			model.addAttribute("year_qw",year);
			if(null == projCountList_qw || projCountList_qw.size() == 0){
				paramMap.put("projYear", lastYear);
				model.addAttribute("year_qw",lastYear);
				projCountList_qw = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			}

			model.addAttribute("projCountList_ky",projCountList_ky);
			model.addAttribute("projCountList_qw",projCountList_qw);
			model.addAttribute("resultMap_ky", resultMap_ky);
			model.addAttribute("resultMap_qw", resultMap_qw);
			return "srm/view/charge_info";
		}
		//当前登录者是医院
		//待办事项的统计
		else{
			resultMapList=pubCountBizImpl.selectCountProjLocal(org);
			resultMap_ky=resultMapList.get(0);
			resultMap_qw=resultMapList.get(1);

			Map<String,Object> paramMap = new HashMap<>();
			paramMap.put("projCategoryId","ky");
			paramMap.put("applyOrgFlow",currUser.getOrgFlow());
			paramMap.put("projYear", year);
			model.addAttribute("year_ky",year);
			projCountList_ky = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			if(null == projCountList_ky || projCountList_ky.size() == 0){
				paramMap.put("projYear", lastYear);
				model.addAttribute("year_ky",lastYear);
				projCountList_ky = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			}
			//paramMap.clear();
			paramMap.put("projCategoryId","qw");
			paramMap.put("projYear", year);
			projCountList_qw = pubCountBizImpl.statisticsProjByTypeId(paramMap);
			model.addAttribute("year_qw",year);
			if(null == projCountList_qw || projCountList_qw.size() == 0){
				paramMap.put("projYear", lastYear);
				projCountList_qw = pubCountBizImpl.statisticsProjByTypeId(paramMap);
				model.addAttribute("year_qw",lastYear);
			}

			model.addAttribute("projCountList_ky",projCountList_ky);
			model.addAttribute("projCountList_qw",projCountList_qw);

			model.addAttribute("resultMap_ky", resultMap_ky);
			model.addAttribute("resultMap_qw", resultMap_qw);

			return "srm/view/local_info";
		}
		//return "srm/view/global_info";
	}


	/**
	 * 统计当前机构待审核人员
	 * @return
	 */
	 private Map<String,Integer> regedUserCount(){
		 Map<String,Integer> regedUserMap=new HashMap<String, Integer>();
		 SysUser sysUser=new SysUser();
		 sysUser.setStatusId(UserStatusEnum.Reged.getId());
		 List<SysUser> userList=pubCountBizImpl.selectRegedUser(sysUser);
		 int regedUserCount=0;
		 if(null!=userList && !userList.isEmpty()){
			 for(SysUser user:userList){
				 regedUserCount++;
			 }
		 }
		 if(regedUserCount>0){
			 regedUserMap.put("regedUserCount", regedUserCount);
		 }
		 return regedUserMap;
	 }
	 /**
	  * 统计项目补填信息
	  * @param projCategoryId
	  * @return
	  */
	 private int countAidProj(String projCategoryId){
		 SysUser currUser = GlobalContext.getCurrentUser();
		//查询当前机构所有下属机构包含自身
			List<SysOrg> allOrgList=orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
			AidProj aidProj=new AidProj();
			//查询当前机构及其子机构下所有省级项目
			aidProj.setProjCategoryId(projCategoryId);
			List<AidProj> aidProjList=aidProjBiz.searchAidProjByChargeOrg(aidProj,allOrgList);
			int aidProjCount=0;
			if(null!=aidProjList && !aidProjList.isEmpty()){
				for(AidProj proj:aidProjList){
					aidProjCount++;
				 }
			}
			return aidProjCount;
	 }
	 /**
	  * 统计专项资金人才
	  * @return
	  */
	 private int countAidTalents(String statusId){
		 int aidTalentsCount=0;
		 AidTalents aidTalents=new AidTalents();
			aidTalents.setStatusId(statusId);
			List<AidTalents> aidTalentsList=pubCountBizImpl.selectAidTalentsByOrg(aidTalents);
			if(null!=aidTalentsList && !aidTalentsList.isEmpty()){
				for(AidTalents aidTalentss:aidTalentsList){
					aidTalentsCount++;
				}
			}
			return aidTalentsCount;
	 }
	 /**
	  * 统计评审与立项情况
	  * @return
	  */
	 private LinkedHashMap<String,String> countApproveInfo(){
		 LinkedHashMap<String,String> projCountInfoMap=new LinkedHashMap<String, String>();
			int countExpert=pubCountBizImpl.selectProjInExpertAll();
			projCountInfoMap.put("评审项目的数量", String.valueOf(countExpert));
			//统计当前医院所属通过立项的项目数量
			int countApprove=pubCountBizImpl.selectProjApproveAll();
			projCountInfoMap.put("立项项目数量", String.valueOf(countApprove));
			//统计当前医院所属立项不通过的项目数量
			int countNotApprove=pubCountBizImpl.selectProjNotApproveAll();
			projCountInfoMap.put("未立项项目数量", String.valueOf(countNotApprove));
			//统计当前医院所属的项目立项比例
			BigDecimal bigCountApprove=new BigDecimal(countApprove);
			BigDecimal bigCountApply=new BigDecimal(pubCountBizImpl.selectProjApply());
			if(bigCountApply.intValue()!=0){
			  BigDecimal countAppPp=bigCountApprove.divide(bigCountApply,2,RoundingMode.HALF_UP);
			  projCountInfoMap.put("立项比例", String.valueOf(countAppPp.doubleValue()*100));
			}else{
				 projCountInfoMap.put("立项比例","0");	
			}
			//统计当前医院验收项目的数量
			int countComplete=pubCountBizImpl.selectProjComplete();
			projCountInfoMap.put("验收项目数量", String.valueOf(countComplete));
			return projCountInfoMap;
	 }
	 /**
	  * 统计当前机构下辖成果数量
	  * @return
	  */
	 private LinkedHashMap<String,String> countSrmAch(){
		 List<SrmAchCountExt> srmAchCountList=pubCountBizImpl.selectSrmAchByOrg();
			LinkedHashMap<String, String> srmAchCountMap=new LinkedHashMap<String, String>();
			for(AchTypeEnum str:AchTypeEnum.values()){
			  for(SrmAchCountExt sc:srmAchCountList){
				if(str.getName().equals(sc.getAchTypeName()) && GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("srm_useAchType_"+str.getId()))){
					 srmAchCountMap.put(sc.getAchTypeName(), sc.getSrmAchCount());
				}
			  }
			}
			for(AchTypeEnum str:AchTypeEnum.values()){
				if(!srmAchCountMap.containsKey(str.getName()) && GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("srm_useAchType_"+str.getId()))){
					srmAchCountMap.put(str.getName(), "0");
				}
			}
		   return srmAchCountMap;
	 }
	 private LinkedHashMap<String,String> countOrgProj(){
		    SysUser currUser = GlobalContext.getCurrentUser();
		    //查询当前登录者所在机构的下一级不包括自己的所有机构
			List<SysOrg> orgList=orgBiz.searchChildrenOrgByOrgFlowNotIncludeSelf(currUser.getOrgFlow());
		    List<PubProjCountExt> projCountInfoList=pubCountBizImpl.selectProjGroupByOrg();
			LinkedHashMap<String, String> orgCountInfoMap=new LinkedHashMap<String, String>();
			for(PubProjCountExt ppce:projCountInfoList){
				orgCountInfoMap.put(ppce.getOrgName(),ppce.getProjCount());
			}
			for(SysOrg so:orgList){
				if(!orgCountInfoMap.containsKey(so.getOrgName())){
					orgCountInfoMap.put(so.getOrgName(), "0");
				}
			}
			return orgCountInfoMap;
	 }
}
