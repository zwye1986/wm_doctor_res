package com.pinde.sci.ctrl.jsres;


import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.core.model.ResJointOrg;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/jsres/appUseInfo")
public class JsResAppUseInfoController extends GeneralController {
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResJointOrgBiz resJointOrgBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResOrgSpeBiz resBaseSpeBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private IJsResDoctorOrgHistoryBiz resDoctorOrgHistoryBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private IJsResRecBiz jsResRecBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IResDoctorRecruitBiz doctorRecruitBiz;

	/**
	 * 显示Main.jsp  住院医师
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String showMain(Model model, String userListScope) {

		return "/jsres/appUseInfo/main";
	}

	/**
	 * 显示Main.jsp
	 *
	 * @param model 助理全科
	 * @return
	 */
	@RequestMapping("/mainAcc")
	public String showMainAcc(Model model, String userListScope) {

		return "/jsres/appUseInfo/mainAcc";
	}

	/**
	 * 统计表查询	住院医师
	 * @param model
	 * @param userListScope
     * @return
     */
	@RequestMapping("/appUserTable")
	public String appUserMain(Model model, String userListScope,HttpServletRequest request) {
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) {//基地
			List<SysOrg> orgs = new ArrayList<>();
			SysUser user = GlobalContext.getCurrentUser();
			SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
				List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				if (orgList != null && orgList.size() > 0) {
					orgs.addAll(orgList);
				}
			}
			model.addAttribute("orgs", orgs);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {//市局
			List<SysOrg> orgs = new ArrayList<>();SysUser sysuser=GlobalContext.getCurrentUser();
			SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId("320000");
			searchOrg.setOrgCityId(org.getOrgCityId());
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<String> orgFlows = new ArrayList<>();
			List<SysOrg>exitOrgs =jointOrgBiz.searchCouAndProList(searchOrg);
			for(SysOrg g:exitOrgs){
				if (!orgFlows.contains(g.getOrgFlow())) {
					orgFlows.add(g.getOrgFlow());
					orgs.add(g);
                    if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(g.getOrgLevelId())) {
						List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
						if (orgList != null && orgList.size() > 0) {
							for (SysOrg j : orgList) {
								if (!orgFlows.contains(j.getOrgFlow())) {
									orgFlows.add(j.getOrgFlow());
									orgs.add(j);
								}
							}
						}
					}
				}
			}
			List<SysOrg> cityOrgs =orgBiz.searchAllSysOrg(searchOrg);
			for(SysOrg g:cityOrgs){
				if (!orgFlows.contains(g.getOrgFlow())) {
					orgFlows.add(g.getOrgFlow());
					orgs.add(g);
				}
			}
			model.addAttribute("orgs", orgs);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {//省厅
			List<SysOrg> orgs = new ArrayList<>();
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId("320000");
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<String> orgFlows = new ArrayList<>();
			List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
			for(SysOrg g:exitOrgs){
				if (!orgFlows.contains(g.getOrgFlow())) {
					orgFlows.add(g.getOrgFlow());
					orgs.add(g);
				}
			}
			model.addAttribute("orgs", orgs);
		}

		return "/jsres/appUseInfo/appUserMain";
	}

	/**
	 * 统计表查询
	 * @param model 助理全科
	 * @param userListScope
	 * @return
	 */
	@RequestMapping("/appUserTableAcc")
	public String appUserTableAcc(Model model, String userListScope,HttpServletRequest request) {
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) {//基地
			List<SysOrg> orgs = new ArrayList<>();
			SysUser user = GlobalContext.getCurrentUser();
			SysOrg org = orgBiz.readSysOrg(user.getOrgFlow());
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
				List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				if (orgList != null && orgList.size() > 0) {
					orgs.addAll(orgList);
				}
			}
			model.addAttribute("orgs", orgs);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {//市局
			List<SysOrg> orgs = new ArrayList<>();SysUser sysuser=GlobalContext.getCurrentUser();
			SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId("320000");
			searchOrg.setOrgCityId(org.getOrgCityId());
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<String> orgFlows = new ArrayList<>();
			List<SysOrg>exitOrgs =jointOrgBiz.searchCouAndProList(searchOrg);
			for(SysOrg g:exitOrgs){
				if (!orgFlows.contains(g.getOrgFlow())) {
					orgFlows.add(g.getOrgFlow());
					orgs.add(g);
                    if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(g.getOrgLevelId())) {
						List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
						if (orgList != null && orgList.size() > 0) {
							for (SysOrg j : orgList) {
								if (!orgFlows.contains(j.getOrgFlow())) {
									orgFlows.add(j.getOrgFlow());
									orgs.add(j);
								}
							}
						}
					}
				}
			}
			List<SysOrg> cityOrgs =orgBiz.searchAllSysOrg(searchOrg);
			for(SysOrg g:cityOrgs){
				if (!orgFlows.contains(g.getOrgFlow())) {
					orgFlows.add(g.getOrgFlow());
					orgs.add(g);
				}
			}
			model.addAttribute("orgs", orgs);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {//省厅
			List<SysOrg> orgs = new ArrayList<>();
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId("320000");
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<String> orgFlows = new ArrayList<>();
			List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
			for(SysOrg g:exitOrgs){
				if (!orgFlows.contains(g.getOrgFlow())) {
					orgFlows.add(g.getOrgFlow());
					orgs.add(g);
				}
			}
			model.addAttribute("orgs", orgs);
		}

		return "/jsres/appUseInfo/appUserMainAcc";
	}

	@RequestMapping("/appNotUseList")
	public String appNotUseList(Integer currentPage, HttpServletRequest request, Model model, String userListScope,
								String orgLevel, String orgFlow, String[] datas, String trainingTypeId, String trainingSpeId,
								String sessionNumber, String type,String startTime,String endTime) {
		Map<String, Object> param = new HashMap<>();
		List<ResJointOrg> resJointOrgs = null;
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		String startDate = DateUtil.getCurrMonth();
		String endDate = DateUtil.getCurrMonth();
		if ("twoMonth".equals(type)) {
			startDate = DateUtil.addMonth(startDate, -1);
			endDate = DateUtil.addMonth(endDate, -1);
		}
		if ("threeMonth".equals(type)) {
			startDate = DateUtil.addMonth(startDate, -3);
			endDate = DateUtil.addMonth(endDate, -1);
		}
		if("other".equals(type)){
			startDate = "";
			endDate = "";
			param.put("startTime",startTime);
			param.put("endTime",endTime);
		}
		param.put("orgLevel", orgLevel);
		param.put("orgFlow", orgFlow);
		param.put("docTypeList", docTypeList);
		param.put("trainingTypeId", trainingTypeId);
		param.put("trainingSpeId", trainingSpeId);
		param.put("sessionNumber", sessionNumber);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("userListScope", userListScope);

		List<String> orgFlows = new ArrayList<>();
		param.put("orgFlows", orgFlows);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) {//基地
			if (StringUtil.isBlank(orgFlow)) {
				orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			}
			param.put("orgFlow", orgFlow);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {//市局
			if (StringUtil.isBlank(orgFlow)) {
				SysUser sysuser=GlobalContext.getCurrentUser();
				SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId("320000");
				searchOrg.setOrgCityId(org.getOrgCityId());
                searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
				List<SysOrg>exitOrgs =jointOrgBiz.searchCouAndProList(searchOrg);
				for(SysOrg g:exitOrgs){
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
                        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(g.getOrgLevelId())) {
							List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
							if (orgList != null && orgList.size() > 0) {
								for (SysOrg j : orgList) {
									if (!orgFlows.contains(j.getOrgFlow())) {
										orgFlows.add(j.getOrgFlow());
									}
								}
							}
						}
					}
				}
				List<SysOrg> cityOrgs =orgBiz.searchAllSysOrg(searchOrg);
				for(SysOrg g:cityOrgs){
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
					}
				}
			}
			param.put("orgFlows", orgFlows);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {//省厅
			if (StringUtil.isBlank(orgFlow)) {
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId("320000");
                searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
				List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
				for(SysOrg g:exitOrgs){
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
					}
				}
			}
			param.put("orgFlows", orgFlows);
		}
		if(StringUtil.isNotEmpty(param.get("orgFlow").toString())){
			resJointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(param.get("orgFlow").toString());
			if(CollectionUtils.isNotEmpty(resJointOrgs)){
				param.put("jointOrgFlow",param.get("orgFlow").toString());
				param.put("orgFlow","");
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> list = jsResDoctorRecruitBiz.searchOrgNotUseAppDoc(param);
		model.addAttribute("list", list);
		return "/jsres/appUseInfo/appNotUseList";
	}



	@RequestMapping("/exportExcel")
	public void exportExcel(String userListScope,String orgLevel, String orgFlow, String[] datas,
							String trainingTypeId, String trainingSpeId, String sessionNumber,
							String type, HttpServletResponse response,String startTime,String endTime) throws Exception {
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, Object> param = new HashMap<>();
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		String startDate = DateUtil.getCurrMonth();
		String endDate = DateUtil.getCurrMonth();
		if ("twoMonth".equals(type)) {
			startDate = DateUtil.addMonth(startDate, -1);
			endDate = DateUtil.addMonth(endDate, -1);
		}
		if ("threeMonth".equals(type)) {
			startDate = DateUtil.addMonth(startDate, -3);
			endDate = DateUtil.addMonth(endDate, -1);
		}
		if("other".equals(type)){
			startDate = "";
			endDate = "";
			param.put("startTime",startTime);
			param.put("endTime",endTime);
		}
		param.put("orgLevel", orgLevel);
		param.put("orgFlow", orgFlow);
		param.put("docTypeList", docTypeList);
		param.put("trainingTypeId", trainingTypeId);
		param.put("trainingSpeId", trainingSpeId);
		param.put("sessionNumber", sessionNumber);
		param.put("startDate", startDate);
		param.put("endDate", endDate);
		param.put("userListScope", userListScope);
		List<String> orgFlows = new ArrayList<>();
		param.put("orgFlows", orgFlows);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) {//基地
			if (StringUtil.isBlank(orgFlow)) {
				orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			}
			param.put("orgFlow", orgFlow);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {//市局
			if (StringUtil.isBlank(orgFlow)) {
				SysUser sysuser=GlobalContext.getCurrentUser();
				SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId("320000");
				searchOrg.setOrgCityId(org.getOrgCityId());
                searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
				List<SysOrg>exitOrgs =jointOrgBiz.searchCouAndProList(searchOrg);
				for(SysOrg g:exitOrgs){
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
                        if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(g.getOrgLevelId())) {
							List<SysOrg> orgList = orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
							if (orgList != null && orgList.size() > 0) {
								for (SysOrg j : orgList) {
									if (!orgFlows.contains(j.getOrgFlow())) {
										orgFlows.add(j.getOrgFlow());
									}
								}
							}
						}
					}
				}
				List<SysOrg> cityOrgs =orgBiz.searchAllSysOrg(searchOrg);
				for(SysOrg g:cityOrgs){
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
					}
				}
			}
			param.put("orgFlows", orgFlows);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {//省厅
			if (StringUtil.isBlank(orgFlow)) {
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId("320000");
                searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
				List<SysOrg>exitOrgs =orgBiz.searchAllSysOrg(searchOrg);
				for(SysOrg g:exitOrgs){
					if (!orgFlows.contains(g.getOrgFlow())) {
						orgFlows.add(g.getOrgFlow());
					}
				}
			}
			param.put("orgFlows", orgFlows);
		}
		list = jsResDoctorRecruitBiz.searchOrgNotUseAppDoc(param);
		String fileName = "未使用APP学员信息.xls";
		String []titles = new String[]{
				"userName:学员姓名",
				"orgName:培训基地",
				"sessionNumber:年级",
				"speName:培训专业",
				"userPhone:手机号码",
				"idNo:证件号码"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream());
	}

}
