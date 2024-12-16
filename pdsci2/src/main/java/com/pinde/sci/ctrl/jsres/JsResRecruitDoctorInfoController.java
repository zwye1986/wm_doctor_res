package com.pinde.sci.ctrl.jsres;


import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResRecruitDoctorInfoBiz;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.jszy.IJszyDoctorAuthBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.SysCfgMapper;
import com.pinde.sci.form.jsres.JsresDoctorInfoExt;
import com.pinde.sci.form.jsres.UserResumeExtInfoForm;
import com.pinde.sci.model.jsres.JsResDoctorRecruitExt;
import com.pinde.core.model.PubUserResume;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.SysCfg;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/jsres/recruitDoctorInfo")
public class JsResRecruitDoctorInfoController extends GeneralController {
	@Autowired
	private IJsResRecruitDoctorInfoBiz recruitDoctorInfoBiz;
	@Autowired
	private IResOrgSpeBiz resOrgSpeBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private SysCfgMapper sysCfgMapper;
	@Autowired
	private IJszyDoctorAuthBiz doctorAuthBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private OrgBizImpl orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;

	/**
	 * 住院全科 招录学员审核
	 * @param model
	 * @param roleFlag
	 * @return
	 */
	@RequestMapping(value="/globalCheckMain")
	public String globalCheckMain(Model model,String roleFlag) {
		model.addAttribute("roleFlag",roleFlag);
		return  "jsres/recruit/globalAuditMain";
	}

	/**
	 * 助理全科 招录学员审核
	 * @param model
	 * @param roleFlag
	 * @return
	 */
	@RequestMapping(value="/globalCheckMainAcc")
	public String globalCheckMainAcc(Model model,String roleFlag) {
		model.addAttribute("roleFlag",roleFlag);
		return  "jsres/recruit/globalAuditMainAcc";
	}

	@RequestMapping(value="/auditMain")
	public String auditMain(Model model,String roleFlag,String source) throws UnsupportedEncodingException {
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		List<SysOrg> orgs=new ArrayList<>();
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			SysOrg sysorg = new SysOrg();
			sysorg.setOrgProvId(org.getOrgProvId());
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				sysorg.setOrgCityId(org.getOrgCityId());
				model.addAttribute("orgCityId", org.getOrgCityId());
			}
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			orgs = orgBiz.searchOrg(sysorg);
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("source",source);
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList",orgFlowList);
		return  "jsres/recruit/auditMain";
	}

	@RequestMapping(value="/auditMainAcc")
	public String auditMainAcc(Model model,String roleFlag,String source) throws UnsupportedEncodingException {
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		List<SysOrg> orgs=new ArrayList<>();
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			SysOrg sysorg = new SysOrg();
			sysorg.setOrgProvId(org.getOrgProvId());
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				sysorg.setOrgCityId(org.getOrgCityId());
				model.addAttribute("orgCityId", org.getOrgCityId());
			}
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			orgs = orgBiz.searchOrg(sysorg);
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("source",source);
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList",orgFlowList);
		return  "jsres/recruit/auditMainAcc";
	}

	@RequestMapping(value="/auditList")
	public String auditList(Model model, Integer currentPage,
					   String roleFlag, HttpServletRequest request,
					   String orgCityId, String orgLevel,
					   String orgFlow, String trainingTypeId,
					   String trainingSpeId, String sessionNumber,
					   String idNo,String doctorStatusId,
					   String userName,String[] datas,String doctorSignupFlag){

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param=new HashMap<>();
		SysOrg org = new SysOrg();
		org.setOrgProvId(sysOrg.getOrgProvId());
		if(StringUtil.isNotBlank(orgCityId)) {
			org.setOrgCityId(orgCityId);
		}
		if(StringUtil.isNotBlank(orgLevel)){
			org.setOrgLevelId(orgLevel);
		}
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("sysOrg",org);
		param.put("doctorStatusId",doctorStatusId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("idNo",idNo);
		param.put("userName",userName);
		param.put("docTypeList",docTypeList);
//		param.put("orgTypeId",com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("sessionNumbers",sessionNumbers);
		param.put("doctorSignupFlag",doctorSignupFlag);//报名方式
		param.put("orgFlow",orgFlow);
		List<JsResDoctorRecruitExt> recruitList=new ArrayList<>();
		SysCfg sysCfg = sysCfgMapper.selectByPrimaryKey("jsres_is_train");
		if (null !=sysCfg && StringUtil.isNotBlank(sysCfg.getCfgValue())){	//查看省厅是否开启招录
			String cfgValue = sysCfg.getCfgValue();
            if (cfgValue.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {        //省厅开启招录（关闭时无数据：院级招录流程优化 ）
				PageHelper.startPage(currentPage,getPageSize(request));
				recruitList = recruitDoctorInfoBiz.searchRecruitExtList(param);
				for (JsResDoctorRecruitExt recruitExt : recruitList) {
					if(StringUtil.isNotEmpty(recruitExt.getIsRetrain())){
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(recruitExt.getIsRetrain())) {
							recruitExt.setIsRetrain("是");
						}
                        if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(recruitExt.getIsRetrain())) {
							recruitExt.setIsRetrain("否");
						}
					}
				}
			}else {
				recruitList=null;
			}
		}
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("recruitList",recruitList);
		return  "jsres/recruit/auditList";
	}

	@RequestMapping(value="/mainGlobal")
	public String mainGlobal(Model model,String roleFlag) {
		model.addAttribute("roleFlag",roleFlag);
		return  "jsres/recruit/mainGlobal";
	}


	@RequestMapping(value="/mainGlobalAcc")
	public String mainGlobalAcc(Model model,String roleFlag) {
		model.addAttribute("roleFlag",roleFlag);
		return  "jsres/recruit/mainGlobalAcc";
	}


	@RequestMapping(value="/main")
	public String main(Model model,String roleFlag,String source) throws UnsupportedEncodingException {

		SysUser sysuser=GlobalContext.getCurrentUser();

		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		List<SysOrg> orgs=new ArrayList<>();
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			SysOrg sysorg = new SysOrg();
			sysorg.setOrgProvId(org.getOrgProvId());
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				sysorg.setOrgCityId(org.getOrgCityId());
				model.addAttribute("orgCityId", org.getOrgCityId());
			}
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			orgs = orgBiz.searchOrg(sysorg);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
			{
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				orgs.addAll(joinOrgs);
			}
		}

		//协同基地地市归属于主基地所在地市
//        for (SysOrg sysOrg : orgs) {
//            if(!com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())){
//                List<ResJointOrg> jointOrgs = jointOrgBiz.selectByJointOrgFlow(sysOrg.getOrgFlow());
//                if(jointOrgs != null && jointOrgs.size()>0){
//                    SysOrg sysOrgTemp = orgBiz.readSysOrg(jointOrgs.get(0).getOrgFlow());//查询主基地
//                    sysOrg.setOrgCityId(sysOrgTemp.getOrgCityId());
//                    sysOrg.setOrgCityName(sysOrgTemp.getOrgCityName());
//                }
//            }
//        }

		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("source",source);
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList",orgFlowList);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag) || com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
		    if("UnPassed".equals(source)){
                return  "jsres/recruit/mainGlobalunPassed";
            }else{
                return  "jsres/recruit/mainGlobalPassed";
            }
		}else{
			return  "jsres/recruit/main";
		}
	}

	@RequestMapping(value="/mainAcc")
	public String mainAcc(Model model,String roleFlag,String source) throws UnsupportedEncodingException {

		SysUser sysuser=GlobalContext.getCurrentUser();

		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		List<SysOrg> orgs=new ArrayList<>();
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			SysOrg sysorg = new SysOrg();
			sysorg.setOrgProvId(org.getOrgProvId());
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				sysorg.setOrgCityId(org.getOrgCityId());
				model.addAttribute("orgCityId", org.getOrgCityId());
			}
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			orgs = orgBiz.searchOrg(sysorg);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
			{
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				orgs.addAll(joinOrgs);
			}
		}

		//协同基地地市归属于主基地所在地市
//        for (SysOrg sysOrg : orgs) {
//            if(!com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())){
//                List<ResJointOrg> jointOrgs = jointOrgBiz.selectByJointOrgFlow(sysOrg.getOrgFlow());
//                if(jointOrgs != null && jointOrgs.size()>0){
//                    SysOrg sysOrgTemp = orgBiz.readSysOrg(jointOrgs.get(0).getOrgFlow());//查询主基地
//                    sysOrg.setOrgCityId(sysOrgTemp.getOrgCityId());
//                    sysOrg.setOrgCityName(sysOrgTemp.getOrgCityName());
//                }
//            }
//        }

		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("source",source);
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList",orgFlowList);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag) || com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			if("UnPassed".equals(source)){
				return  "jsres/recruit/mainGlobalunPassedAcc";
			}else{
				return  "jsres/recruit/mainGlobalPassedAcc";
			}
		}else{
			return  "jsres/recruit/mainAcc";
		}
	}

	@RequestMapping(value="/list")
	public String list(Model model, Integer currentPage,
					   String roleFlag, HttpServletRequest request,
					   String orgCityId, String orgLevel,
					   String orgFlow, String trainingTypeId,
					   String trainingSpeId, String sessionNumber,
					   String graduationYear, String idNo,String doctorStatusId,String signupWay,
					   String userName, String workOrgName, String[] datas, String[] yearDatas,
					   String jointOrgFlag,String auditStatusId,String studentType,String joinOrgFlow,String isArmy) throws DocumentException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		List<String> trainYearList=new ArrayList<String>();
		if(yearDatas!=null&&yearDatas.length>0){
			for(String s:yearDatas){
				trainYearList.add(s);
			}
		}

		Map<String,Object> param=new HashMap<>();
        if (StringUtil.isBlank(orgCityId) && com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			orgCityId=sysOrg.getOrgCityId();
		}
		model.addAttribute("orgCityId",orgCityId);
//		param.put("orgCityId", orgCityId);
		//禅道3386 改动   查询协同基地不分地市
//		if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
//			param.put("orgCityId", "");
//		}else{
//			param.put("orgCityId", orgCityId);
//		}
		if(StringUtil.isNotBlank(joinOrgFlow)){
			param.put("orgCityId", "");
		}else{
			param.put("orgCityId", orgCityId);
		}
		param.put("doctorStatusId",doctorStatusId);
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("idNo",idNo);
		param.put("orgFlow",orgFlow);
		param.put("userName",userName);
		param.put("workOrgName",workOrgName);
		param.put("docTypeList",docTypeList);
		param.put("trainYearList",trainYearList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("sessionNumbers",sessionNumbers);
		param.put("auditStatusId",auditStatusId);
		param.put("studentType",studentType);
//		if(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(orgLevel)){
//			orgLevel = "";
//		}

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

		List<String> jointOrgFlowList=new ArrayList<String>();
		if (StringUtil.isBlank(orgFlow)) {
            if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)
                    || com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
//				if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
					SysOrg searchOrg=new SysOrg();
					searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
					if(StringUtil.isNotBlank(orgCityId)){
						searchOrg.setOrgCityId(orgCityId);
					}
					if(StringUtil.isNotBlank(orgLevel)){
						searchOrg.setOrgLevelId(orgLevel);
					}
					List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
					for(SysOrg g:exitOrgs){
						List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlowNotSessionYear(g.getOrgFlow());
						if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
							for(ResJointOrg jointOrg:resJointOrgList){
								if(StringUtil.isNotBlank(orgCityId)) {
									String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
									if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
										jointOrgFlowList.add(jointOrg.getJointOrgFlow());
									}
								}else{
									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
								}
							}
						}
						jointOrgFlowList.add(g.getOrgFlow());
					}
//				}
			}
		}else{
			jointOrgFlowList.add(orgFlow);
			//选择主培训基地 直接查询主基地以及协同基地数据 2021/5/7修改
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlowNotSessionYear(orgFlow);
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()) {
				for (ResJointOrg jointOrg : resJointOrgList) {
					String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
					if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}else{
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
//			if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
//				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
//				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
//					for(ResJointOrg jointOrg:resJointOrgList){
//						//禅道3386 改动
////						if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
////							String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
////							if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
////								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
////							}
////						}else{
//							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
////						}
//					}
//				}
//			}
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			orgLevel="";
		}
		if(StringUtil.isNotBlank(joinOrgFlow)){
			orgLevel="";
		}
		param.put("orgLevel",orgLevel);
		param.put("jointOrgFlowList",jointOrgFlowList);
		param.put("joinOrgFlow",joinOrgFlow);
		param.put("signupWay",signupWay);
		param.put("isArmy",isArmy);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> doctorList = recruitDoctorInfoBiz.searchRecruitDoctorInfos(param);

//		for(Map<String,Object> map : doctorList){
//			Object userResume = map.get("userResume");
//			if(null != userResume){
//				UserResumeExtInfoForm userResumeExt = userResumeBiz.converyToJavaBean(userResume.toString(), UserResumeExtInfoForm.class);
//				// 是否军队人员
//				String armyType = userResumeExt.getArmyType();
//				String armyName = "否";
//				if (StringUtil.isNotBlank(armyType)) {
//					armyName = com.pinde.core.common.enums.ArmyTypeEnum.getNameById(armyType);
//				}
//				map.put("armyName",armyName);
//			}
//		}

		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("doctorList",doctorList);
		return  "jsres/recruit/doctorListZi";
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

	@RequestMapping(value={"/doctorPassedList"})
	public String doctorPassedList(Model model, String doctorFlow,
								   String recruitFlow){

		List<JsresRecruitDocInfo> recruitList = new ArrayList<>();
		JsresRecruitDocInfo recruit=recruitDoctorInfoBiz.readRecruit(recruitFlow);
		recruitList.add(recruit);
		model.addAttribute("recruitList", recruitList);

		return "/jsres/recruit/doctorMain";
	}

	/**
	 * 查看培训信息
	 * @return
	 */
	@RequestMapping("/getDoctorRecruit")
	public String getDoctorRecruit(String recruitFlow, String doctorFlow, Model model){
		JsresRecruitDocInfo jsresRecruit = recruitDoctorInfoBiz.readRecruit(recruitFlow);
		model.addAttribute("jsresRecruitDocInfo", jsresRecruit);
		List<DoctorAuth> doctorAuths = doctorAuthBiz.searchAuthsByOperUserFlow(doctorFlow);
		if (doctorAuths != null & !doctorAuths.isEmpty()) {
			model.addAttribute("resRec", doctorAuths.get(0));
		}
		return "jsres/recruit/trainInfo";
	}
	@RequestMapping(value="/doctorInfo")
	public String doctorInfo(String userFlow, String recruitFlow, String sessionNumber, Model model) throws DocumentException {
		SysUser sysUser = userBiz.readSysUser(userFlow);
		JsresRecruitInfo recruitInfo=recruitDoctorInfoBiz.getRecruitInfoBysessionNumber(userFlow,sessionNumber);
		ResDoctor resDoctor = resDoctorBiz.readDoctor(userFlow);
		if(resDoctor!=null){
			if(recruitInfo!=null)
			{
				resDoctor.setDoctorTypeId(recruitInfo.getDoctorTypeId());
				resDoctor.setDoctorTypeName(recruitInfo.getDoctorTypeName());
			}
			if(StringUtil.isNotBlank(resDoctor.getGraduatedId())){
                List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
				if(sysDictList!=null && !sysDictList.isEmpty()){
					for(SysDict dict:sysDictList){
						if(dict.getDictId().equals(resDoctor.getGraduatedId())){
							resDoctor.setGraduatedName(dict.getDictName());
						}
					}
				}
			}
            if (StringUtil.isNotBlank(resDoctor.getDoctorTypeId()) && com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(resDoctor.getDoctorTypeId())) {
				if(StringUtil.isNotBlank(resDoctor.getWorkOrgId())){
                    List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
					if(sysDictList!=null && !sysDictList.isEmpty()){
						for(SysDict dict:sysDictList){
							if(dict.getDictId().equals(resDoctor.getWorkOrgId())){
								resDoctor.setWorkOrgName(dict.getDictName());
							}
						}
					}
				}
			}
		}
		model.addAttribute("user", sysUser);
		model.addAttribute("doctor", resDoctor);
		PubUserResume pubUserResume = userResumeBiz.readPubUserResume(userFlow);
		if(pubUserResume != null){
			String xmlContent =  pubUserResume.getUserResume();
			if(StringUtil.isNotBlank(xmlContent)){
				//xml转换成JavaBean
				UserResumeExtInfoForm userResumeExt=null;
				userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,UserResumeExtInfoForm.class);
				if(userResumeExt!=null){
					if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
						if(sysDictList!=null && !sysDictList.isEmpty()){
							for(SysDict dict:sysDictList){
								if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
									if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
										userResumeExt.setGraduatedName(dict.getDictName());
									}
								}
							}

						}
					}
					model.addAttribute("userResumeExt", userResumeExt);
				}
			}
		}
		return "jsres/recruit/doctorInfo";
	}

	@RequestMapping(value="/zltjCityMain")
	public String zltjCityMain(Model model,String roleFlag,String tabId) throws UnsupportedEncodingException {
        model.addAttribute("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList",orgFlowList);
		return  "jsres/zltjCity/main";
	}

	@RequestMapping(value="/zltjCityMainAcc")
	public String zltjCityMainAcc(Model model,String roleFlag,String tabId) throws UnsupportedEncodingException {
        model.addAttribute("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList",orgFlowList);
		return  "jsres/zltjCity/mainAcc";
	}


	/**
	 * 地方人员类型统计
	 * @param model
	 * @param roleFlag

	 * @return
	 * @throws UnsupportedEncodingException
     */
	@RequestMapping(value="/zltjCity/doctorType")
	public String doctorType(Model model,String roleFlag
			,String sessionNumber,String orgCityId
			,String trainingTypeId,String trainingSpeId,HttpServletRequest request
			,String orgLevel, String[] datas, String jointOrgFlag	) throws UnsupportedEncodingException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());

		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgCityId",orgCityId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<String> jointOrgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId(sysOrg.getOrgProvId());
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			if(StringUtil.isNotBlank(orgCityId)){
				searchOrg.setOrgCityId(orgCityId);
			}
			if(StringUtil.isNotBlank(orgLevel)){
				searchOrg.setOrgLevelId(orgLevel);
			}
			List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
			for(SysOrg g:exitOrgs){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						if(StringUtil.isNotBlank(orgCityId))
						{
							String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
							if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}else{
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				jointOrgFlowList.add(g.getOrgFlow());
			}
			orgLevel="";
		}
		param.put("orgLevel",orgLevel);
		param.put("jointOrgFlowList",jointOrgFlowList);
		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjCityDoctorType(param);
		Map<String,Object> cityTypeNumMap=new HashMap<>();
		Map<String,Integer> cityNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String cityId= (String) map.get("cityId");
				String typeId= (String) map.get("typeId");
				cityTypeNumMap.put(cityId+typeId,map.get("num"));
				Integer sum=cityNumMap.get(cityId);
				if(sum==null)
					sum=0;
				sum+=(Integer) map.get("num");
				cityNumMap.put(cityId,sum);

				Integer sum2=typeNumMap.get(typeId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				typeNumMap.put(typeId,sum2);

			}
		}
		model.addAttribute("cityTypeNumMap",cityTypeNumMap);
		model.addAttribute("cityNumMap",cityNumMap);
		model.addAttribute("typeNumMap",typeNumMap);

		List<Map<String,String>> citys = getCitys();
		model.addAttribute("citys",citys);
		return  "jsres/zltjCity/doctorType";
	}

	public List<Map<String, String>> getCitys() {
		List<Map<String,String>> citys=new ArrayList<>();
		Map<String, String> city = new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		return citys;
	}

	public List<Map<String, String>> getAllCitysNew() {
		List<Map<String, String>> citys = new ArrayList<>();
		Map<String, String> city = new HashMap<>();
		city.put("cityId", "120100");
		city.put("cityName", "市辖区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120101");
		city.put("cityName", "和平区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120102");
		city.put("cityName", "河东区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120103");
		city.put("cityName", "河西区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120104");
		city.put("cityName", "南开区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120105");
		city.put("cityName", "河北区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120106");
		city.put("cityName", "红桥区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120110");
		city.put("cityName", "东丽区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120111");
		city.put("cityName", "西青区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120112");
		city.put("cityName", "津南区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120113");
		city.put("cityName", "北辰区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120114");
		city.put("cityName", "武清区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120115");
		city.put("cityName", "宝坻区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120116");
		city.put("cityName", "滨海新区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120117");
		city.put("cityName", "宁河区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120118");
		city.put("cityName", "静海区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120119");
		city.put("cityName", "蓟州区");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120200");
		city.put("cityName", "辖县");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120221");
		city.put("cityName", "宁河县");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120223");
		city.put("cityName", "静海县");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "120225");
		city.put("cityName", "蓟县");
		citys.add(city);
		return citys;
	}
	/**
	 * 地方人员类型统计导出
	 * @param model
	 * @param roleFlag

	 * @return
	 * @throws UnsupportedEncodingException
     */
	@RequestMapping(value="/exportZltjCity/doctorType")
	public void exportZltjCityDoctorType(Model model,String roleFlag
			,String sessionNumber,String orgCityId
			,String trainingTypeId,String trainingSpeId
			,String orgLevel, String[] datas, String jointOrgFlag,HttpServletResponse response	) throws IOException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());

		String titleYear="";
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			titleYear=sessionNumber.replace(",","_");
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgCityId",orgCityId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<String> jointOrgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId(sysOrg.getOrgProvId());
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			if(StringUtil.isNotBlank(orgCityId)){
				searchOrg.setOrgCityId(orgCityId);
			}
			if(StringUtil.isNotBlank(orgLevel)){
				searchOrg.setOrgLevelId(orgLevel);
			}
			List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
			for(SysOrg g:exitOrgs){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						if(StringUtil.isNotBlank(orgCityId))
						{
							String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
							if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}else{
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				jointOrgFlowList.add(g.getOrgFlow());
			}
			orgLevel="";
		}
		param.put("orgLevel",orgLevel);
		param.put("jointOrgFlowList",jointOrgFlowList);
		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjCityDoctorType(param);
		Map<String,Object> cityTypeNumMap=new HashMap<>();
		Map<String,Integer> cityNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String cityId= (String) map.get("cityId");
				String typeId= (String) map.get("typeId");
				cityTypeNumMap.put(cityId+typeId,map.get("num"));
				Integer sum=cityNumMap.get(cityId);
				if(sum==null)
					sum=0;
				sum+=(Integer) map.get("num");
				cityNumMap.put(cityId,sum);

				Integer sum2=typeNumMap.get(typeId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				typeNumMap.put(typeId,sum2);

			}
		}


		List<Map<String,String>> citys=new ArrayList<>();
		Map<String,String> city=new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		model.addAttribute("citys",citys);

		List<String> titleList=new ArrayList<>();
		titleList.add("地市");
        for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
			titleList.add(e.getName());
		}
		titleList.add("合计");
		String fileName = titleYear+"地市人员类型招收统计表.xls";
		fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream os = response.getOutputStream();
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);
		Font fontTwo = wb.createFont();
		fontTwo.setFontHeightInPoints((short) 12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//

		Map<Integer, Integer> columnWidth = new HashMap<>();
		Row row = sheet.createRow(0);
		Cell cell = null;
		for (int i = 0; i < titleList.size(); i++) {
			String title = titleList.get(i);
			cell = row.createCell(i);
			cell.setCellValue(title);
			cell.setCellStyle(style);
			//宽度自适应
			int nl = title.toString().getBytes().length;
			if (columnWidth.containsKey(i)) {
				Integer ol = columnWidth.get(i);
				if (ol < nl)
					columnWidth.put(i, nl);
			} else {
				columnWidth.put(i, nl);
			}
		}
		if (citys != null) {
			Cell rowCell = null;
			int rowNum = 1;
			for (int i = 0; i < citys.size(); i++) {
				Map<String, String> item = citys.get(i);
				String cityName = item.get("cityName");
				String cityId = item.get("cityId");
				if(StringUtil.isBlank(orgCityId)||orgCityId.equals(cityId)) {
					row = sheet.createRow(rowNum++);
					rowCell = row.createCell(0);
					rowCell.setCellStyle(styleTwo);
					rowCell.setCellValue(cityName);
					//宽度自适应
					ExcleUtile.setColumnWidth(cityName.toString().getBytes().length, 0, columnWidth);

					int k = 1;
					int sum = 0;
                    for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
						String key = cityId + e.getId();
						Integer num = (Integer) cityTypeNumMap.get(key);
						if (num == null)
							num = 0;
						sum += num;
						rowCell = row.createCell(k);
						rowCell.setCellStyle(styleTwo);
						rowCell.setCellValue(String.valueOf(num));
						//宽度自适应
						ExcleUtile.setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
						k++;
					}
					rowCell = row.createCell(k);
					rowCell.setCellStyle(styleTwo);
					rowCell.setCellValue(String.valueOf(sum));
					//宽度自适应
					ExcleUtile.setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);
				}
			}

			row = sheet.createRow(rowNum++);
			int k = 0;
			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
			rowCell.setCellValue("合计");
			//宽度自适应
			ExcleUtile.setColumnWidth("合计".toString().getBytes().length, k++, columnWidth);
			int hjsum = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				Integer num = (Integer) typeNumMap.get(e.getId());
				if (num == null)
					num = 0;
				hjsum += num;
				rowCell = row.createCell(k);
				rowCell.setCellStyle(styleTwo);
				rowCell.setCellValue(String.valueOf(num));
				//宽度自适应
				ExcleUtile.setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
				k++;
			}
			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
			rowCell.setCellValue(String.valueOf(hjsum));
			//宽度自适应
			ExcleUtile.setColumnWidth(String.valueOf(hjsum).toString().getBytes().length, k, columnWidth);
			Set<Integer> keys = columnWidth.keySet();
			for (Integer key : keys) {
				int width = columnWidth.get(key);
				sheet.setColumnWidth(key, width * 2 * 256);
			}
		}
		wb.write(os);
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	/**
	 * 地方人员专业统计
	 * @param model
	 * @param roleFlag
	 * @return
	 * @throws UnsupportedEncodingException
     */
	@RequestMapping(value="/zltjCity/speType")
	public String speType(Model model,String roleFlag
			,String sessionNumber,String orgCityId,HttpServletRequest request
			,String trainingTypeId,String trainingSpeId
			,String orgLevel, String[] datas, String jointOrgFlag) throws UnsupportedEncodingException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgCityId",orgCityId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<String> jointOrgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId(sysOrg.getOrgProvId());
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			if(StringUtil.isNotBlank(orgCityId)){
				searchOrg.setOrgCityId(orgCityId);
			}
			if(StringUtil.isNotBlank(orgLevel)){
				searchOrg.setOrgLevelId(orgLevel);
			}
			List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
			for(SysOrg g:exitOrgs){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						if(StringUtil.isNotBlank(orgCityId))
						{
							String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
							if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}else{
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				jointOrgFlowList.add(g.getOrgFlow());
			}
			orgLevel="";
		}
		param.put("orgLevel",orgLevel);
		param.put("jointOrgFlowList",jointOrgFlowList);
		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjCitySpeType(param);
		Map<String,Object> cityTypeNumMap=new HashMap<>();
		Map<String,Integer> cityNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String cityId= (String) map.get("cityId");
				String speId= (String) map.get("speId");
				cityTypeNumMap.put(cityId+speId,map.get("num"));
				Integer sum=cityNumMap.get(cityId);
				if(sum==null)
					sum=0;
				sum+=(Integer) map.get("num");
				cityNumMap.put(cityId,sum);

				Integer sum2=typeNumMap.get(speId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				typeNumMap.put(speId,sum2);

			}
		}
		model.addAttribute("cityTypeNumMap",cityTypeNumMap);
		model.addAttribute("cityNumMap",cityNumMap);
		model.addAttribute("typeNumMap",typeNumMap);

		List<Map<String,String>> citys = getCitys();
		model.addAttribute("citys",citys);
        List<SysDict> spes = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(trainingTypeId);
		model.addAttribute("spes",spes);
		return  "jsres/zltjCity/speType";
	}
	/**
	 * 地方人员专业统计导出
	 * @param model
	 * @param roleFlag
	 * @return
	 * @throws UnsupportedEncodingException
     */
	@RequestMapping(value="/exportZltjCity/speType")
	public void exportZltjCitySpeType(Model model,String roleFlag
			,String sessionNumber,String orgCityId
			,String trainingTypeId,String trainingSpeId
			,String orgLevel, String[] datas, String jointOrgFlag,HttpServletResponse response) throws IOException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
		String titleYear="";
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			titleYear=sessionNumber.replace(",","_");
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgCityId",orgCityId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<String> jointOrgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			SysOrg searchOrg=new SysOrg();
			searchOrg.setOrgProvId(sysOrg.getOrgProvId());
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			if(StringUtil.isNotBlank(orgCityId)){
				searchOrg.setOrgCityId(orgCityId);
			}
			if(StringUtil.isNotBlank(orgLevel)){
				searchOrg.setOrgLevelId(orgLevel);
			}
			List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
			for(SysOrg g:exitOrgs){
				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(g.getOrgFlow());
				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
					for(ResJointOrg jointOrg:resJointOrgList){
						if(StringUtil.isNotBlank(orgCityId))
						{
							String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
							if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}else{
							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
						}
					}
				}
				jointOrgFlowList.add(g.getOrgFlow());
			}
			orgLevel="";
		}
		param.put("orgLevel",orgLevel);
		param.put("jointOrgFlowList",jointOrgFlowList);
		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjCitySpeType(param);
		Map<String,Object> cityTypeNumMap=new HashMap<>();
		Map<String,Integer> cityNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String cityId= (String) map.get("cityId");
				String speId= (String) map.get("speId");
				cityTypeNumMap.put(cityId+speId,map.get("num"));
				Integer sum=cityNumMap.get(cityId);
				if(sum==null)
					sum=0;
				sum+=(Integer) map.get("num");
				cityNumMap.put(cityId,sum);

				Integer sum2=typeNumMap.get(speId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				typeNumMap.put(speId,sum2);

			}
		}
		model.addAttribute("cityTypeNumMap",cityTypeNumMap);
		model.addAttribute("cityNumMap",cityNumMap);
		model.addAttribute("typeNumMap",typeNumMap);

		List<Map<String,String>> citys=new ArrayList<>();
		Map<String,String> city=new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		model.addAttribute("citys",citys);
        List<SysDict> spes = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(trainingTypeId);
		model.addAttribute("spes",spes);
		int s=15;
		if(StringUtil.isNotBlank(orgCityId))
			s=3;
		String[] titles = new String[s];
		titles[0]="专业";
		titles[s-1]="合计";
		int tk=1;
		for (int i = 0; i < citys.size(); i++) {
			if(StringUtil.isBlank(orgCityId)||orgCityId.equals(citys.get(i).get("cityId")))
			{
				titles[tk++]=citys.get(i).get("cityName");
			}
		}
		String fileName = titleYear+"地市专业招生统计表.xls";
		fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream os = response.getOutputStream();
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);
		Font fontTwo = wb.createFont();
		fontTwo.setFontHeightInPoints((short) 12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//

		Map<Integer, Integer> columnWidth = new HashMap<>();
		Row row = sheet.createRow(0);
		Cell cell = null;
		for (int i = 0; i < titles.length; i++) {
			String title = titles[i];
			cell = row.createCell(i);
			cell.setCellValue(title);
			cell.setCellStyle(style);
			//宽度自适应
			int nl = title.toString().getBytes().length;
			if (columnWidth.containsKey(i)) {
				Integer ol = columnWidth.get(i);
				if (ol < nl)
					columnWidth.put(i, nl);
			} else {
				columnWidth.put(i, nl);
			}
		}
		if (citys != null) {
			Cell rowCell = null;
			int rowNum = 1;
			for(SysDict spe:spes)
			{
				if(StringUtil.isBlank(trainingSpeId)||trainingSpeId.equals(spe.getDictId())) {
					row = sheet.createRow(rowNum++);
					rowCell = row.createCell(0);
					rowCell.setCellStyle(styleTwo);
					rowCell.setCellValue(spe.getDictName());

					int k = 1;
					int sum = 0;
					//宽度自适应
					ExcleUtile.setColumnWidth(spe.getDictName().toString().getBytes().length, 0, columnWidth);
					for (int i = 0; i < citys.size(); i++) {
						Map<String, String> item = citys.get(i);
						String cityId = item.get("cityId");
						if (StringUtil.isBlank(orgCityId) || orgCityId.equals(cityId)) {
							String key = cityId + spe.getDictId();
							Integer num = (Integer) cityTypeNumMap.get(key);
							if (num == null)
								num = 0;
							sum += num;
							rowCell = row.createCell(k);
							rowCell.setCellStyle(styleTwo);
							rowCell.setCellValue(String.valueOf(num));
							//宽度自适应
							ExcleUtile.setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
							k++;
						}

					}
					rowCell = row.createCell(k);
					rowCell.setCellStyle(styleTwo);
					rowCell.setCellValue(String.valueOf(sum));
					//宽度自适应
					ExcleUtile.setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);
				}
			}


			row = sheet.createRow(rowNum++);
			int k = 0;
			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
			rowCell.setCellValue("合计");
			//宽度自适应
			ExcleUtile.setColumnWidth("合计".toString().getBytes().length, k++, columnWidth);
			int hjsum = 0;
			for (int i = 0; i < citys.size(); i++) {
				Map<String, String> item = citys.get(i);
				String cityId = item.get("cityId");
				if (StringUtil.isBlank(orgCityId) || orgCityId.equals(cityId)) {
					String key = cityId;
					Integer num = (Integer) cityNumMap.get(key);
					if (num == null)
						num = 0;
					hjsum += num;
					rowCell = row.createCell(k);
					rowCell.setCellStyle(styleTwo);
					rowCell.setCellValue(String.valueOf(num));
					//宽度自适应
					ExcleUtile.setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
					k++;
				}

			}
			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
			rowCell.setCellValue(String.valueOf(hjsum));
			//宽度自适应
			ExcleUtile.setColumnWidth(String.valueOf(hjsum).toString().getBytes().length, k, columnWidth);
			Set<Integer> keys = columnWidth.keySet();
			for (Integer key : keys) {
				int width = columnWidth.get(key);
				sheet.setColumnWidth(key, width * 2 * 256);
			}
		}
		wb.write(os);
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value="/zltjOrgMain")
	public String zltjOrgMain(Model model,String roleFlag,String tabId) throws UnsupportedEncodingException {
        model.addAttribute("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		List<SysOrg> orgs=new ArrayList<>();
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			SysOrg sysorg = new SysOrg();
			sysorg.setOrgProvId(org.getOrgProvId());
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				sysorg.setOrgCityId(org.getOrgCityId());
				model.addAttribute("orgCityId", org.getOrgCityId());
			}
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			orgs = orgBiz.searchOrg(sysorg);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
			{
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				orgs.addAll(joinOrgs);
			}
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList",orgFlowList);
		return  "jsres/zltjOrg/main";
	}


	@RequestMapping(value="/zltjOrgMainAcc")
	public String zltjOrgMainAcc(Model model,String roleFlag,String tabId) throws UnsupportedEncodingException {
        model.addAttribute("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		List<SysOrg> orgs=new ArrayList<>();
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			SysOrg sysorg = new SysOrg();
			sysorg.setOrgProvId(org.getOrgProvId());
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				sysorg.setOrgCityId(org.getOrgCityId());
				model.addAttribute("orgCityId", org.getOrgCityId());
			}
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			orgs = orgBiz.searchOrg(sysorg);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
			{
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				orgs.addAll(joinOrgs);
			}
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList",orgFlowList);
		return  "jsres/zltjOrg/mainAcc";
	}

	/**
	 * 地方人员类型统计
	 * @param model
	 * @param roleFlag
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/zltjOrg/doctorType")
	public String zltjOrgDoctorType(Model model,String roleFlag
			,String sessionNumber,String orgCityId,String firstFlag
			,String orgFlow,String trainingTypeId,String trainingSpeId
			,String orgLevel, String[] datas, String jointOrgFlag	) throws UnsupportedEncodingException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
        if (StringUtil.isBlank(orgCityId) && com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
		{
			orgCityId=sysOrg.getOrgCityId();
		}

		SysOrg searchOrg=new SysOrg();
		searchOrg.setOrgProvId(sysOrg.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if(StringUtil.isNotBlank(orgCityId)){
			searchOrg.setOrgCityId(orgCityId);
		}
		if(StringUtil.isNotBlank(orgLevel)){
			searchOrg.setOrgLevelId(orgLevel);
		}
		if(StringUtil.isNotBlank(orgFlow)){
			searchOrg.setOrgFlow(orgFlow);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
		for (SysOrg g : exitOrgs) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
				if (joinOrgs != null && !joinOrgs.isEmpty()) {
					for (SysOrg jointOrg : joinOrgs) {
						if (StringUtil.isNotBlank(orgCityId)) {
							String cityId = jointOrg.getOrgCityId();
							if (StringUtil.isNotBlank(cityId) && cityId.equals(orgCityId)) {
								jointOrgFlowList.add(jointOrg.getOrgFlow());
							}
						} else {
							jointOrgFlowList.add(jointOrg.getOrgFlow());
						}
					}
				}
			}
			if (StringUtil.isBlank(orgFlow))
				jointOrgFlowList.add(g.getOrgFlow());
		}
		if(StringUtil.isNotBlank(orgFlow))
			jointOrgFlowList.add(orgFlow);

		//满足条件的基地列表
		List<Map<String,Object>> orgList=recruitDoctorInfoBiz.orgListByOrgFlow(jointOrgFlowList);
		//按照国家基地 地市组装数据
		Map<String,List<Map<String,Object>>> cityOrgsMap=new HashMap<>();
		Map<String,List<Map<String,Object>>> jointOrgsMap=new HashMap<>();
		if(orgList!=null&&orgList.size()>0)
		{
			//国家基地的协同基地数据
			for(int i=0;i<orgList.size();i++)
			{
				Map<String,Object> org =orgList.get(i);
				String orgLevelId= (String) org.get("orgLevelId");
				if("JointOrg".equals(orgLevelId)) {
					String orgCityId2 = (String) org.get("orgCityId");
					String countryOrgCityId = (String) org.get("countryOrgCityId");
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag) || StringUtil.isBlank(orgFlow) && StringUtil.isBlank(orgLevel)) {
						if (StringUtil.isNotBlank(orgCityId) && orgCityId2.equals(countryOrgCityId)
								|| StringUtil.isBlank(orgCityId)) {
							String countryOrgFlow = (String) org.get("countryOrgFlow");
							List<Map<String, Object>> jointOrgs = jointOrgsMap.get(countryOrgFlow);
							if (jointOrgs == null)
								jointOrgs = new ArrayList<>();
							jointOrgs.add(org);
							jointOrgsMap.put(countryOrgFlow, jointOrgs);
							orgList.remove(i);
							i--;
						}
					}
				}
			}
			//地市的数据
			for(int i=0;i<orgList.size();i++)
			{
				Map<String,Object> org =orgList.get(i);
				String orgCityId2 = (String) org.get("orgCityId");
				List<Map<String,Object>> jointOrgs=cityOrgsMap.get(orgCityId2);
				if(jointOrgs==null)
					jointOrgs=new ArrayList<>();
				jointOrgs.add(org);
				cityOrgsMap.put(orgCityId2,jointOrgs);
			}
		}
		model.addAttribute("orgCityId",orgCityId);
		model.addAttribute("jointOrgsMap",jointOrgsMap);
		model.addAttribute("cityOrgsMap",cityOrgsMap);
		//统计数据信息
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgCityId",orgCityId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			orgLevel = "";
		}
		param.put("orgLevel",orgLevel);
		param.put("jointOrgFlowList",jointOrgFlowList);
		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjOrgDoctorType(param);
		Map<String,Object> cityOrgNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String cityId= (String) map.get("cityId");
				String jointOrgFlow= (String) map.get("jointOrgFlow");
				String orgFlow2= (String) map.get("orgFlow");
				String typeId= (String) map.get("typeId");
				//协同基地
				if(!orgFlow2.equals(jointOrgFlow)) {
					cityOrgNumMap.put(jointOrgFlow + typeId, map.get("num"));
				}
				//国家基地
				Integer sum2= (Integer) cityOrgNumMap.get(orgFlow2+typeId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				cityOrgNumMap.put(orgFlow2+typeId,sum2);

				Integer sum3=typeNumMap.get(typeId);
				if(sum3==null)
					sum3=0;
				sum3+=(Integer) map.get("num");
				typeNumMap.put(typeId,sum3);

			}
		}
		model.addAttribute("cityOrgNumMap",cityOrgNumMap);
		model.addAttribute("typeNumMap",typeNumMap);

		List<Map<String,String>> citys=new ArrayList<>();
		Map<String,String> city=new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		model.addAttribute("citys",citys);
		return  "jsres/zltjOrg/doctorType";
	}

	/**
	 * 地方人员类型统计导出
	 * @param model
	 * @param roleFlag

	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/exportZltjOrg/doctorType")
	public void exportZltjOrgDoctorType(Model model,String roleFlag
			,String sessionNumber,String orgCityId
			,String orgFlow,String trainingTypeId,String trainingSpeId
			,String orgLevel, String[] datas, String jointOrgFlag,HttpServletResponse response	) throws IOException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());

		String titleYear="";
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			titleYear=sessionNumber.replace(",","_");
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
        if (StringUtil.isBlank(orgCityId) && com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
		{
			orgCityId=sysOrg.getOrgCityId();
		}

		SysOrg searchOrg=new SysOrg();
		searchOrg.setOrgProvId(sysOrg.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if(StringUtil.isNotBlank(orgCityId)){
			searchOrg.setOrgCityId(orgCityId);
		}
		if(StringUtil.isNotBlank(orgLevel)){
			searchOrg.setOrgLevelId(orgLevel);
		}
		if(StringUtil.isNotBlank(orgFlow)){
			searchOrg.setOrgFlow(orgFlow);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
		for (SysOrg g : exitOrgs) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
				if (joinOrgs != null && !joinOrgs.isEmpty()) {
					for (SysOrg jointOrg : joinOrgs) {
						if (StringUtil.isNotBlank(orgCityId)) {
							String cityId = jointOrg.getOrgCityId();
							if (StringUtil.isNotBlank(cityId) && cityId.equals(orgCityId)) {
								jointOrgFlowList.add(jointOrg.getOrgFlow());
							}
						} else {
							jointOrgFlowList.add(jointOrg.getOrgFlow());
						}
					}
				}
			}
			if (StringUtil.isBlank(orgFlow))
				jointOrgFlowList.add(g.getOrgFlow());
		}
		if(StringUtil.isNotBlank(orgFlow))
			jointOrgFlowList.add(orgFlow);

		//满足条件的基地列表
		List<Map<String,Object>> orgList=recruitDoctorInfoBiz.orgListByOrgFlow(jointOrgFlowList);
		//按照国家基地 地市组装数据
		Map<String,List<Map<String,Object>>> cityOrgsMap=new HashMap<>();
		Map<String,List<Map<String,Object>>> jointOrgsMap=new HashMap<>();
		if(orgList!=null&&orgList.size()>0)
		{
			//国家基地的协同基地数据
			for(int i=0;i<orgList.size();i++)
			{
				Map<String,Object> org =orgList.get(i);
				String orgLevelId= (String) org.get("orgLevelId");
				if("JointOrg".equals(orgLevelId)) {
					String orgCityId2 = (String) org.get("orgCityId");
					String countryOrgCityId = (String) org.get("countryOrgCityId");
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag) || StringUtil.isBlank(orgFlow) && StringUtil.isBlank(orgLevel)) {
						if (StringUtil.isNotBlank(orgCityId) && orgCityId2.equals(countryOrgCityId) || StringUtil.isBlank(orgCityId)) {
							String countryOrgFlow = (String) org.get("countryOrgFlow");
							List<Map<String, Object>> jointOrgs = jointOrgsMap.get(countryOrgFlow);
							if (jointOrgs == null)
								jointOrgs = new ArrayList<>();
							jointOrgs.add(org);
							jointOrgsMap.put(countryOrgFlow, jointOrgs);
							orgList.remove(i);
							i--;
						}
					}
				}
			}
			//地市的数据
			for(int i=0;i<orgList.size();i++)
			{
				Map<String,Object> org =orgList.get(i);
				String orgCityId2 = (String) org.get("orgCityId");
				List<Map<String,Object>> jointOrgs=cityOrgsMap.get(orgCityId2);
				if(jointOrgs==null)
					jointOrgs=new ArrayList<>();
				jointOrgs.add(org);
				cityOrgsMap.put(orgCityId2,jointOrgs);
			}
		}
		model.addAttribute("orgCityId",orgCityId);
		model.addAttribute("jointOrgsMap",jointOrgsMap);
		model.addAttribute("cityOrgsMap",cityOrgsMap);
		//统计数据信息
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgCityId",orgCityId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		String orgLevel2=orgLevel;
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			orgLevel2 = "";
		}
		param.put("orgLevel",orgLevel2);
		param.put("jointOrgFlowList",jointOrgFlowList);
		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjOrgDoctorType(param);
		Map<String,Object> cityOrgNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String cityId= (String) map.get("cityId");
				String jointOrgFlow= (String) map.get("jointOrgFlow");
				String orgFlow2= (String) map.get("orgFlow");
				String typeId= (String) map.get("typeId");
				//协同基地
				if(!orgFlow2.equals(jointOrgFlow)) {
					cityOrgNumMap.put(jointOrgFlow + typeId, map.get("num"));
				}
				//国家基地
				Integer sum2= (Integer) cityOrgNumMap.get(orgFlow2+typeId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				cityOrgNumMap.put(orgFlow2+typeId,sum2);

				Integer sum3=typeNumMap.get(typeId);
				if(sum3==null)
					sum3=0;
				sum3+=(Integer) map.get("num");
				typeNumMap.put(typeId,sum3);

			}
		}
		model.addAttribute("cityOrgNumMap",cityOrgNumMap);
		model.addAttribute("typeNumMap",typeNumMap);

		List<Map<String,String>> citys=new ArrayList<>();
		Map<String,String> city=new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		model.addAttribute("citys",citys);

		List<String> titleList=new ArrayList<>();

        if (!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			titleList.add("地市");
		}
		titleList.add("基地名称");
        for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
			titleList.add(e.getName());
		}
		titleList.add("合计");
		String fileName = titleYear+"基地人员类型招收统计表.xls";
		fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream os = response.getOutputStream();
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("sheet1");
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);
		Font fontTwo = wb.createFont();
		fontTwo.setFontHeightInPoints((short) 12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//

		Map<Integer, Integer> columnWidth = new HashMap<>();
		Row row = sheet.createRow(0);
		Cell cell = null;
		for (int i = 0; i < titleList.size(); i++) {
			String title = titleList.get(i);
			cell = row.createCell(i);
			cell.setCellValue(title);
			cell.setCellStyle(style);
			//宽度自适应
			int nl = title.toString().getBytes().length;
			if (columnWidth.containsKey(i)) {
				Integer ol = columnWidth.get(i);
				if (ol < nl)
					columnWidth.put(i, nl);
			} else {
				columnWidth.put(i, nl);
			}
		}
		if (citys != null) {
			Cell rowCell = null;
			int rowNum = 1;
			for (int i = 0; i < citys.size(); i++) {
				Map<String, String> item = citys.get(i);
				String cityName = item.get("cityName");
				String cityId = item.get("cityId");
				List<Map<String,Object>> orgs = cityOrgsMap.get(cityId);
				if(orgs!=null&& orgs.size() > 0 &&StringUtil.isBlank(orgCityId)||orgCityId.equals(cityId)) {
					int rowspan=orgs.size();
					for(int n=0;n<orgs.size();n++)
					{
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag) || StringUtil.isBlank(orgLevel)) {
							List<Map<String,Object>>  jointOrgFlows=jointOrgsMap.get(orgs.get(n).get("orgFlow"));
							if(jointOrgFlows!=null)
								rowspan+=jointOrgFlows.size();
						}
					}
					for (int j = 0; j < orgs.size(); j++) {

						row = sheet.createRow(rowNum++);
						String orgFlow2 = (String) orgs.get(j).get("orgFlow");
						String orgName=(String) orgs.get(j).get("orgName");
						int n=0;
                        if (!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
						{
							if (j == 0) {
								rowCell = row.createCell(n++);
								rowCell.setCellStyle(styleTwo);
								rowCell.setCellValue(cityName);
								//宽度自适应
								ExcleUtile.setColumnWidth(cityName.toString().getBytes().length, n-1, columnWidth);
								sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowspan - 1+rowNum - 1, 0, 0));
							}else{
								n++;
							}
						}
						rowCell = row.createCell(n++);
						rowCell.setCellStyle(styleTwo);
						rowCell.setCellValue(orgName);
						//宽度自适应
						ExcleUtile.setColumnWidth(orgName.toString().getBytes().length, n-1, columnWidth);
						int k = n;
						int sum = 0;
                        for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
							String key = orgFlow2+ e.getId();
							Integer num = (Integer) cityOrgNumMap.get(key);
							if (num == null)
								num = 0;
							sum += num;
							rowCell = row.createCell(k);
							rowCell.setCellStyle(styleTwo);
							rowCell.setCellValue(String.valueOf(num));
							//宽度自适应
							ExcleUtile.setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
							k++;
						}
						rowCell = row.createCell(k);
						rowCell.setCellStyle(styleTwo);
						rowCell.setCellValue(String.valueOf(sum));
						//宽度自适应
						ExcleUtile.setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);
						List<Map<String,Object>>  jointOrgs=jointOrgsMap.get(orgFlow2);
                        if (jointOrgs != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag))
						{
							for(int m=0;m<jointOrgs.size();m++)
							{
								row = sheet.createRow(rowNum++);
								String jointOrgFlow = (String) jointOrgs.get(m).get("orgFlow");
								orgName=(String) jointOrgs.get(m).get("orgName")+"(协同基地)";
								int n2=0;
                                if (!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
								{
									n2=1;
								}
								rowCell = row.createCell(n2++);
								rowCell.setCellStyle(styleTwo);
								rowCell.setCellValue(orgName);
								//宽度自适应
								ExcleUtile.setColumnWidth(orgName.toString().getBytes().length, n2-1, columnWidth);
								int k2 = n2;
								int sum2 = 0;
                                for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
									String key = jointOrgFlow + e.getId();
									Integer num = (Integer) cityOrgNumMap.get(key);
									if (num == null)
										num = 0;
									sum2 += num;
									String	result=String.valueOf(num);
									rowCell = row.createCell(k2);
									rowCell.setCellStyle(styleTwo);
									rowCell.setCellValue(result);
									//宽度自适应
									ExcleUtile.setColumnWidth(result.toString().getBytes().length, k2, columnWidth);
									k2++;
								}
								rowCell = row.createCell(k2);
								rowCell.setCellStyle(styleTwo);
								rowCell.setCellValue(String.valueOf(sum2));
								//宽度自适应
								ExcleUtile.setColumnWidth(String.valueOf(sum2).toString().getBytes().length, k2, columnWidth);
							}
						}
					}
				}
			}
			row = sheet.createRow(rowNum++);
			int k = 0;
			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag) || StringUtil.isBlank(orgFlow) && StringUtil.isBlank(orgLevel))
			{
				rowCell.setCellValue("合计(已包含协同)");
				//宽度自适应
				ExcleUtile.setColumnWidth("合计(已包含协同)".toString().getBytes().length, k++, columnWidth);
			}else{
				rowCell.setCellValue("合计");
				//宽度自适应
				ExcleUtile.setColumnWidth("合计".toString().getBytes().length, k++, columnWidth);
			}
            if (!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowNum - 1, 0, 1));
				k++;
			}
			int sum = 0;
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				String key = e.getId();
				Integer num = (Integer) typeNumMap.get(key);
				if (num == null)
					num = 0;
				sum += num;
				String result=String.valueOf(num);
				rowCell = row.createCell(k);
				rowCell.setCellStyle(styleTwo);
				rowCell.setCellValue(result);
				//宽度自适应
				ExcleUtile.setColumnWidth(result.toString().getBytes().length, k, columnWidth);
				k++;
			}
			rowCell = row.createCell(k);
			rowCell.setCellStyle(styleTwo);
			rowCell.setCellValue(String.valueOf(sum));
			//宽度自适应
			ExcleUtile.setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);
		}
		Set<Integer> keys = columnWidth.keySet();
		for (Integer key : keys) {
			int width = columnWidth.get(key);
			sheet.setColumnWidth(key, width * 2 * 200);
		}

		wb.write(os);
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	/**
	 * 地方人员类型统计
	 * @param model
	 * @param roleFlag
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/zltjOrg/speType")
	public String zltjOrgSpeType(Model model,String roleFlag
			,String sessionNumber,String orgCityId
			,String orgFlow,String trainingTypeId,String trainingSpeId
			,String orgLevel, String[] datas, String jointOrgFlag	) throws UnsupportedEncodingException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
        if (StringUtil.isBlank(orgCityId) && com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
		{
			orgCityId=sysOrg.getOrgCityId();
		}

		SysOrg searchOrg=new SysOrg();
		searchOrg.setOrgProvId(sysOrg.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if(StringUtil.isNotBlank(orgCityId)){
			searchOrg.setOrgCityId(orgCityId);
		}
		if(StringUtil.isNotBlank(orgLevel)){
			searchOrg.setOrgLevelId(orgLevel);
		}
		if(StringUtil.isNotBlank(orgFlow)){
			searchOrg.setOrgFlow(orgFlow);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
		for (SysOrg g : exitOrgs) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
				if (joinOrgs != null && !joinOrgs.isEmpty()) {
					for (SysOrg jointOrg : joinOrgs) {
						if (StringUtil.isNotBlank(orgCityId)) {
							String cityId = jointOrg.getOrgCityId();
							if (StringUtil.isNotBlank(cityId) && cityId.equals(orgCityId)) {
								jointOrgFlowList.add(jointOrg.getOrgFlow());
							}
						} else {
							jointOrgFlowList.add(jointOrg.getOrgFlow());
						}
					}
				}
			}
			if (StringUtil.isBlank(orgFlow))
				jointOrgFlowList.add(g.getOrgFlow());
		}
		if(StringUtil.isNotBlank(orgFlow))
			jointOrgFlowList.add(orgFlow);

		//满足条件的基地列表
		List<Map<String,Object>> orgList=recruitDoctorInfoBiz.orgListByOrgFlow(jointOrgFlowList);
		//按照国家基地 地市组装数据
		Map<String,List<Map<String,Object>>> cityOrgsMap=new HashMap<>();
		Map<String,List<Map<String,Object>>> jointOrgsMap=new HashMap<>();
		if(orgList!=null&&orgList.size()>0)
		{
			//国家基地的协同基地数据
			for(int i=0;i<orgList.size();i++)
			{
				Map<String,Object> org =orgList.get(i);
				String orgLevelId= (String) org.get("orgLevelId");
				if("JointOrg".equals(orgLevelId)) {
					String orgCityId2 = (String) org.get("orgCityId");
					String countryOrgCityId = (String) org.get("countryOrgCityId");
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag) || StringUtil.isBlank(orgFlow) && StringUtil.isBlank(orgLevel)) {
						if (StringUtil.isNotBlank(orgCityId) && orgCityId2.equals(countryOrgCityId) || StringUtil.isBlank(orgCityId)) {
							String countryOrgFlow = (String) org.get("countryOrgFlow");
							List<Map<String, Object>> jointOrgs = jointOrgsMap.get(countryOrgFlow);
							if (jointOrgs == null)
								jointOrgs = new ArrayList<>();
							jointOrgs.add(org);
							jointOrgsMap.put(countryOrgFlow, jointOrgs);
							orgList.remove(i);
							i--;
						}
					}
				}
			}
			//地市的数据
			for(int i=0;i<orgList.size();i++)
			{
				Map<String,Object> org =orgList.get(i);
				String orgCityId2 = (String) org.get("orgCityId");
				List<Map<String,Object>> jointOrgs=cityOrgsMap.get(orgCityId2);
				if(jointOrgs==null)
					jointOrgs=new ArrayList<>();
				jointOrgs.add(org);
				cityOrgsMap.put(orgCityId2,jointOrgs);
			}
		}
		model.addAttribute("orgCityId",orgCityId);
		model.addAttribute("jointOrgsMap",jointOrgsMap);
		model.addAttribute("cityOrgsMap",cityOrgsMap);

		Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
		List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeListByFlows(jointOrgFlowList);
		if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家基地的专业
			for(ResOrgSpe r:orgSpeList){
                orgSpeFlagMap.put(r.getOrgFlow() + r.getSpeTypeId() + r.getSpeId(), com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
		}
		model.addAttribute("orgSpeFlagMap",orgSpeFlagMap);
		//统计数据信息
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgCityId",orgCityId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			orgLevel = "";
		}
		param.put("orgLevel",orgLevel);
		param.put("jointOrgFlowList",jointOrgFlowList);
		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjOrgSpeType(param);
		Map<String,Object> cityOrgNumMap=new HashMap<>();
		Map<String,Integer> cityNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String cityId= (String) map.get("cityId");
				String jointOrgFlow= (String) map.get("jointOrgFlow");
				String orgFlow2= (String) map.get("orgFlow");
				String speId= (String) map.get("speId");
				//协同基地
				if(!orgFlow2.equals(jointOrgFlow)) {
					cityOrgNumMap.put(jointOrgFlow + speId, map.get("num"));
				}
				//国家基地
				Integer sum2= (Integer) cityOrgNumMap.get(orgFlow2+speId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				cityOrgNumMap.put(orgFlow2+speId,sum2);

				Integer sum3=typeNumMap.get(speId);
				if(sum3==null)
					sum3=0;
				sum3+=(Integer) map.get("num");
				typeNumMap.put(speId,sum3);

			}
		}
		model.addAttribute("cityOrgNumMap",cityOrgNumMap);
		model.addAttribute("cityNumMap",cityNumMap);
		model.addAttribute("typeNumMap",typeNumMap);

		List<Map<String,String>> citys=new ArrayList<>();
		Map<String,String> city=new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		model.addAttribute("citys",citys);
		return  "jsres/zltjOrg/speType";
	}


	/**
	 * 统计导出
	 * @param model
	 * @param roleFlag

	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value="/exportZltjOrg/speType")
	public void exportZltjOrgSpeType(Model model,String roleFlag
			,String sessionNumber,String orgCityId
			,String orgFlow,String trainingTypeId,String trainingSpeId
			,String orgLevel, String[] datas, String jointOrgFlag,HttpServletResponse response	) throws IOException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());

		String titleYear="";
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			titleYear=sessionNumber.replace(",","_");
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
        if (StringUtil.isBlank(orgCityId) && com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
		{
			orgCityId=sysOrg.getOrgCityId();
		}

		SysOrg searchOrg=new SysOrg();
		searchOrg.setOrgProvId(sysOrg.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if(StringUtil.isNotBlank(orgCityId)){
			searchOrg.setOrgCityId(orgCityId);
		}
		if(StringUtil.isNotBlank(orgLevel)){
			searchOrg.setOrgLevelId(orgLevel);
		}
		if(StringUtil.isNotBlank(orgFlow)){
			searchOrg.setOrgFlow(orgFlow);
		}
		List<String> jointOrgFlowList=new ArrayList<String>();
		List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
		for (SysOrg g : exitOrgs) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(g.getOrgFlow());
				if (joinOrgs != null && !joinOrgs.isEmpty()) {
					for (SysOrg jointOrg : joinOrgs) {
						if (StringUtil.isNotBlank(orgCityId)) {
							String cityId = jointOrg.getOrgCityId();
							if (StringUtil.isNotBlank(cityId) && cityId.equals(orgCityId)) {
								jointOrgFlowList.add(jointOrg.getOrgFlow());
							}
						} else {
							jointOrgFlowList.add(jointOrg.getOrgFlow());
						}
					}
				}
			}
			if (StringUtil.isBlank(orgFlow))
				jointOrgFlowList.add(g.getOrgFlow());
		}
		if(StringUtil.isNotBlank(orgFlow))
			jointOrgFlowList.add(orgFlow);

		//满足条件的基地列表
		List<Map<String,Object>> orgList=recruitDoctorInfoBiz.orgListByOrgFlow(jointOrgFlowList);
		//按照国家基地 地市组装数据
		Map<String,List<Map<String,Object>>> cityOrgsMap=new HashMap<>();
		Map<String,List<Map<String,Object>>> jointOrgsMap=new HashMap<>();
		if(orgList!=null&&orgList.size()>0)
		{
			//国家基地的协同基地数据
			for(int i=0;i<orgList.size();i++)
			{
				Map<String,Object> org =orgList.get(i);
				String orgLevelId= (String) org.get("orgLevelId");
				if("JointOrg".equals(orgLevelId)) {
					String orgCityId2 = (String) org.get("orgCityId");
					String countryOrgCityId = (String) org.get("countryOrgCityId");
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag) || StringUtil.isBlank(orgFlow) && StringUtil.isBlank(orgLevel)) {
						if (StringUtil.isNotBlank(orgCityId) && orgCityId2.equals(countryOrgCityId) || StringUtil.isBlank(orgCityId)) {
							String countryOrgFlow = (String) org.get("countryOrgFlow");
							List<Map<String, Object>> jointOrgs = jointOrgsMap.get(countryOrgFlow);
							if (jointOrgs == null)
								jointOrgs = new ArrayList<>();
							jointOrgs.add(org);
							jointOrgsMap.put(countryOrgFlow, jointOrgs);
							orgList.remove(i);
							i--;
						}
					}
				}
			}
			//地市的数据
			for(int i=0;i<orgList.size();i++)
			{
				Map<String,Object> org =orgList.get(i);
				String orgCityId2 = (String) org.get("orgCityId");
				List<Map<String,Object>> jointOrgs=cityOrgsMap.get(orgCityId2);
				if(jointOrgs==null)
					jointOrgs=new ArrayList<>();
				jointOrgs.add(org);
				cityOrgsMap.put(orgCityId2,jointOrgs);
			}
		}
		model.addAttribute("orgCityId",orgCityId);
		model.addAttribute("jointOrgsMap",jointOrgsMap);
		model.addAttribute("cityOrgsMap",cityOrgsMap);

		Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
		List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeListByFlows(jointOrgFlowList);
		if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家基地的专业
			for(ResOrgSpe r:orgSpeList){
                orgSpeFlagMap.put(r.getOrgFlow() + r.getSpeTypeId() + r.getSpeId(), com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
		}
		model.addAttribute("orgSpeFlagMap",orgSpeFlagMap);
		//统计数据信息
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgCityId",orgCityId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			orgLevel = "";
		}
		param.put("orgLevel",orgLevel);
		param.put("jointOrgFlowList",jointOrgFlowList);
		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjOrgSpeType(param);
		Map<String,Object> cityOrgNumMap=new HashMap<>();
		Map<String,Integer> cityNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String cityId= (String) map.get("cityId");
				String jointOrgFlow= (String) map.get("jointOrgFlow");
				String orgFlow2= (String) map.get("orgFlow");
				String speId= (String) map.get("speId");
				//协同基地
				if(!orgFlow2.equals(jointOrgFlow)) {
					cityOrgNumMap.put(jointOrgFlow + speId, map.get("num"));
				}
				//国家基地
				Integer sum2= (Integer) cityOrgNumMap.get(orgFlow2+speId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				cityOrgNumMap.put(orgFlow2+speId,sum2);

				Integer sum3=typeNumMap.get(speId);
				if(sum3==null)
					sum3=0;
				sum3+=(Integer) map.get("num");
				typeNumMap.put(speId,sum3);
			}
		}
		model.addAttribute("cityOrgNumMap",cityOrgNumMap);
		model.addAttribute("cityNumMap",cityNumMap);
		model.addAttribute("typeNumMap",typeNumMap);

		List<Map<String,String>> citys=new ArrayList<>();
		Map<String,String> city=new HashMap<>();
		city.put("cityId", "320100");
		city.put("cityName", "南京市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320200");
		city.put("cityName", "无锡市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320300");
		city.put("cityName", "徐州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320400");
		city.put("cityName", "常州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320500");
		city.put("cityName", "苏州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320600");
		city.put("cityName", "南通市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320700");
		city.put("cityName", "连云港市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320800");
		city.put("cityName", "淮安市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "320900");
		city.put("cityName", "盐城市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321000");
		city.put("cityName", "扬州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321100");
		city.put("cityName", "镇江市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321200");
		city.put("cityName", "泰州市");
		citys.add(city);
		city = new HashMap<>();
		city.put("cityId", "321300");
		city.put("cityName", "宿迁市");
		citys.add(city);
		model.addAttribute("citys",citys);

		String fileName = titleYear+"基地专业招生统计表.xls";
		fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream os = response.getOutputStream();
		Workbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		Sheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		//HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);

		Font fontTwo = wb.createFont();
		fontTwo.setFontHeightInPoints((short) 12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//

		List<SysDict> dicts = new ArrayList<>();
		if (StringUtil.isNotBlank(trainingTypeId)) {
			dicts = dictBiz.searchDictListByDictTypeIdAndDictId(trainingTypeId,trainingSpeId);//每个培训类别对应的专业
			List<String> titleList=new ArrayList<>();
            if (!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				titleList.add("地市名称");
			}
			titleList.add("基地名称");
			if(dicts!=null&&dicts.size()>0)
			{
				for(SysDict d:dicts) {
					titleList.add(d.getDictName());
				}
			}
			titleList.add("小计");
			Map<Integer, Integer> columnWidth = new HashMap<>();
			Row row = sheet.createRow(0);
			Cell cell = null;
			for (int i = 0; i < titleList.size(); i++) {
				String title = titleList.get(i);
				cell = row.createCell(i);
				cell.setCellValue(title);
				cell.setCellStyle(style);
				//宽度自适应
				int nl = title.toString().getBytes().length;
				if (columnWidth.containsKey(i)) {
					Integer ol = columnWidth.get(i);
					if (ol < nl)
						columnWidth.put(i, nl);
				} else {
					columnWidth.put(i, nl);
				}
			}
			int rowNum=1;
			if (citys != null) {
				Cell rowCell = null;
				for (int i = 0; i < citys.size(); i++) {
					Map<String, String> item = citys.get(i);
					String cityName = item.get("cityName");
					String cityId = item.get("cityId");
					List<Map<String,Object>> orgs = cityOrgsMap.get(cityId);
					if (orgs != null && orgs.size() > 0) {
						int rowspan=orgs.size();
						for(int n=0;n<orgs.size();n++)
						{

                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
								List<Map<String,Object>> jointOrgFlows = jointOrgsMap.get(orgs.get(n).get("orgFlow"));
								if (jointOrgFlows != null)
									rowspan += jointOrgFlows.size();
							}
						}
						for (int j = 0; j < orgs.size(); j++) {
							row = sheet.createRow(rowNum++);
							String orgFlow2 = (String) orgs.get(j).get("orgFlow");
							String orgName = (String) orgs.get(j).get("orgName");
							int n=0;
                            if (!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
							{
								if (j == 0) {
									rowCell = row.createCell(n++);
									rowCell.setCellStyle(styleTwo);
									rowCell.setCellValue(cityName);
									//宽度自适应
									ExcleUtile.setColumnWidth(cityName.toString().getBytes().length, n-1, columnWidth);
									sheet.addMergedRegion(new CellRangeAddress(rowNum - 1, rowspan - 1+rowNum - 1, 0, 0));
								}else{
									n++;
								}
							}
							rowCell = row.createCell(n++);
							rowCell.setCellStyle(styleTwo);
							rowCell.setCellValue(orgName);
							//宽度自适应
							ExcleUtile.setColumnWidth(orgName.toString().getBytes().length, n-1, columnWidth);
							int k = n;
							int sum = 0;
							for (SysDict e : dicts) {
								String key =  orgFlow2 + e.getDictId();
								String flow =  orgFlow2 + trainingTypeId +e.getDictId();
								String result="--";
                                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgSpeFlagMap.get(flow)))
								{
									Integer num = (Integer) cityOrgNumMap.get(key);
									if (num == null)
										num = 0;
									sum += num;
									result=String.valueOf(num);
								}
								rowCell = row.createCell(k);
								rowCell.setCellStyle(styleTwo);
								rowCell.setCellValue(result);
								//宽度自适应
								ExcleUtile.setColumnWidth(result.toString().getBytes().length, k, columnWidth);
								k++;
							}
							rowCell = row.createCell(k);
							rowCell.setCellStyle(styleTwo);
							rowCell.setCellValue(String.valueOf(sum));
							//宽度自适应
							ExcleUtile.setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);

                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
								List<Map<String,Object>> jointOrgs = jointOrgsMap.get(orgFlow2);
								if (jointOrgs != null) {
									for (int m = 0; m < jointOrgs.size(); m++) {
										row = sheet.createRow(rowNum++);
										String jointOrgFlow = (String) jointOrgs.get(m).get("orgFlow");
										 orgName = (String) jointOrgs.get(m).get("orgName")+"(协同基地)";
										int n2=0;
                                        if (!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
										{
											n2=1;
										}
										rowCell = row.createCell(n2++);
										rowCell.setCellStyle(styleTwo);
										rowCell.setCellValue(orgName);
										//宽度自适应
										ExcleUtile.setColumnWidth(orgName.toString().getBytes().length, n2-1, columnWidth);
										int k2 = 2;
										int sum2 = 0;
										for (SysDict e : dicts) {
											String key =  jointOrgFlow + e.getDictId();
											String flow = jointOrgFlow + trainingTypeId + e.getDictId();
											String result = "--";
                                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgSpeFlagMap.get(flow))) {
												Integer num = (Integer) cityOrgNumMap.get(key);
												if (num == null)
													num = 0;
												sum2 += num;
												result = String.valueOf(num);
											}
											rowCell = row.createCell(k2);
											rowCell.setCellStyle(styleTwo);
											rowCell.setCellValue(result);
											//宽度自适应
											ExcleUtile.setColumnWidth(result.toString().getBytes().length, k2, columnWidth);
											k2++;
										}
										rowCell = row.createCell(k2);
										rowCell.setCellStyle(styleTwo);
										rowCell.setCellValue(String.valueOf(sum2));
										//宽度自适应
										ExcleUtile.setColumnWidth(String.valueOf(sum2).toString().getBytes().length, k2, columnWidth);
									}
								}
							}
						}
					}
				}

				row = sheet.createRow(rowNum++);
				int k = 0;
				rowCell = row.createCell(k);
				rowCell.setCellStyle(styleTwo);
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag) || StringUtil.isBlank(orgFlow) && StringUtil.isBlank(orgLevel)) {
					rowCell.setCellValue("合计(已包含协同)");
					//宽度自适应
					ExcleUtile.setColumnWidth("合计(已包含协同)".toString().getBytes().length, k++, columnWidth);
				}else{
					rowCell.setCellValue("合计");
					//宽度自适应
					ExcleUtile.setColumnWidth("合计".toString().getBytes().length, k++, columnWidth);
				}
                if (!com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag))
				{
					sheet.addMergedRegion(new CellRangeAddress(rowNum - 1,rowNum - 1, 0, 1));
					k++;
				}
				int sum = 0;
				for (SysDict e : dicts) {
					String key = e.getDictId();
					String result="--";
						Integer num = (Integer) typeNumMap.get(key);
						if (num == null)
							num = 0;
						sum += num;
						result=String.valueOf(num);
					rowCell = row.createCell(k);
					rowCell.setCellStyle(styleTwo);
					rowCell.setCellValue(result);
					//宽度自适应
					ExcleUtile.setColumnWidth(result.toString().getBytes().length, k, columnWidth);
					k++;
				}
				rowCell = row.createCell(k);
				rowCell.setCellStyle(styleTwo);
				rowCell.setCellValue(String.valueOf(sum));
				//宽度自适应
				ExcleUtile.setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);
			}
			Set<Integer> keys = columnWidth.keySet();
			for (Integer key : keys) {
				int width = columnWidth.get(key);
				sheet.setColumnWidth(key, width * 2 * 200);
			}
		}
		wb.write(os);
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value="/zltjOrgLocal")
	public String zltjOrgLocal(Model model,String roleFlag) throws UnsupportedEncodingException {
        model.addAttribute("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		List<SysOrg> orgs=new ArrayList<>();
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			SysOrg sysorg = new SysOrg();
			sysorg.setOrgProvId(org.getOrgProvId());
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				sysorg.setOrgCityId(org.getOrgCityId());
				model.addAttribute("orgCityId", org.getOrgCityId());
			}
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			orgs = orgBiz.searchOrg(sysorg);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
			{
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				orgs.addAll(joinOrgs);
			}
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		return  "jsres/zltjOrg/zltjOrgLocal";
	}

	/**
	 * @Author xieyh
	 * @Description 基地招录统计报表
	 * @Date  2024-09-09
	 **/
	@RequestMapping(value = {"/recruitStatisticsReport"})
	public String recruitStatisticsReport(String sessionNumber, String roleFlag, String speId, String statusId, String docType, String isLoad, Model model) {
		Map<String,Object> params = new HashMap<>();
		List<String> docTypeList = new ArrayList<>();
		if(StringUtil.isNotBlank(docType)){
			docTypeList.add(docType);
		}else{
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
			}
		}
		params.put("docTypeList",docTypeList);
		params.put("statusId",statusId);
		params.put("speId",speId);

		if (StringUtil.isBlank(sessionNumber)) {
			if (com.pinde.core.util.DateUtil.getCurrDate().compareTo(com.pinde.core.util.DateUtil.getYear() + "-09-30") > 0) {
				sessionNumber = com.pinde.core.util.DateUtil.getYear();
			} else {
				sessionNumber = String.valueOf(Integer.parseInt(DateUtil.getYear()) - 1);
			}
		}
		model.addAttribute("sessionNumber", sessionNumber);

		SysOrg org = null;
		if (StringUtil.isNotBlank(GlobalContext.getCurrentUser().getOrgFlow())) {
			org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		}

		model.addAttribute("speId",speId);

		// 对数据进行计算，初始化值
		// 住院医师
		HashMap<String, Integer> doctorTrainingMap = new HashMap<>();
		doctorTrainingMap.put("all", 0);
		// 在校专硕
		HashMap<String, Integer> graduateMap = new HashMap<>();
		graduateMap.put("all", 0);
		// 各专业所有学校总计
		Map<String,Integer> speAll = new HashMap<>();

		// 查询专业
		String searchOrgFlow = org.getOrgFlow() == null ? "" : org.getOrgFlow();
		// 查询人数信息
		params.put("orgFlow",searchOrgFlow);
		params.put("sessionNumber",sessionNumber);
		// 查询人员名单并处理数据
		Map<String,Integer> speInfos = new HashMap<>();
		speInfos.put("all", 0);
		List<Map<String,Object>> infos = recruitDoctorInfoBiz.getOrgRecruitSpeInfo(params);
		for (Map<String, Object> info : infos) {
			calculateRecruitStudentForReport(doctorTrainingMap, graduateMap, speAll, speInfos, info);
		}
		model.addAttribute("speInfos",speInfos);
		model.addAttribute("doctorTrainingMap",doctorTrainingMap);
		model.addAttribute("graduateMap",graduateMap);
		model.addAttribute("speAll",speAll);

		if (StringUtil.isNotBlank(isLoad)) {
			return "jsres/zltjOrg/statisticsLoad";
		} else {
			return "jsres/zltjOrg/recruitStudentList";
		}
	}

	/**
	 * @Description 招录学员统计计算各类型学员数据
	 * @param doctorTrainingMap 住院医师统计
	 * @param graduateMap 在校专硕统计
	 * @param speAll 按专业分总人数统计
	 * @param speInfos 每个学校各专业各类型人数统计
	 * @param info 查询数据库原始数据
	 * @Date  2024-09-09
	 * @Author xieyh
	 */
	private void calculateRecruitStudentForReport(HashMap<String, Integer> doctorTrainingMap, HashMap<String, Integer> graduateMap, Map<String, Integer> speAll, Map<String, Integer> speInfos, Map<String, Object> info) {
        if (com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId().equals(info.get("doctorTypeId"))) {
			// 做大数据在校专硕各项的分开统计
			graduateMap.put("all", graduateMap.get("all") + 1); // 在校专硕总人数+1
			// 此处的key用的是各学员在校状态的ID
			if (graduateMap.containsKey(info.get("doctorStatusId"))) {
				graduateMap.put((String) info.get("doctorStatusId"), graduateMap.get(info.get("doctorStatusId")) + 1);
			} else {
				graduateMap.put((String) info.get("doctorStatusId"), 1);
			}
		} else {
			// 做大数据住院医师各项的分开统计
			doctorTrainingMap.put("all", doctorTrainingMap.get("all") + 1);
			// 此处的key用的是各学员在校状态的ID
			if (doctorTrainingMap.containsKey(info.get("doctorStatusId"))) {
				doctorTrainingMap.put((String) info.get("doctorStatusId"), doctorTrainingMap.get(info.get("doctorStatusId")) + 1);
			} else {
				doctorTrainingMap.put((String) info.get("doctorStatusId"), 1);
			}
		}
		if (speInfos.containsKey(info.get("speId") + (String) info.get("doctorTypeId"))) {
			speInfos.put(info.get("speId") + (String) info.get("doctorTypeId"), speInfos.get(info.get("speId") + (String) info.get("doctorTypeId")) + 1);
		} else {
			speInfos.put(info.get("speId") + (String) info.get("doctorTypeId"), 1);
		}
		if (speInfos.containsKey(info.get("speId") + "all")) {
			speInfos.put(info.get("speId") + "all", speInfos.get(info.get("speId") + "all") + 1);
		} else {
			speInfos.put(info.get("speId") + "all", 1);
		}
		// 做每个类型以及总人数的统计
		if (speAll.containsKey(info.get("doctorTypeId"))) {
			speAll.put((String) info.get("doctorTypeId"), speAll.get(info.get("doctorTypeId")) + 1);
		} else {
			speAll.put((String) info.get("doctorTypeId"), 1);
		}
		if (speAll.containsKey("all")) {
			speAll.put("all", speAll.get("all") + 1);
		} else {
			speAll.put("all", 1);
		}
	}

	/**
	 * @Author xieyh
	 * @Description 导出招录学员统计数据
	 * @Date  2024-09-10
	 **/
	@RequestMapping(value = {"/exportRecruitStatistics"})
	public void exportRecruitStatistics(String sessionNumber, String speId, String statusId, String docType, HttpServletResponse response) throws IOException {

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

		//列宽自适应
		HSSFRow rowOne = sheet.createRow(0);//第1行

        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
		List<String> titles = new ArrayList<>();
		titles.add("专业基地");
		titles.add("本单位人");
		titles.add("委培单位人");
		titles.add("社会人");
		titles.add("在校专硕");
		titles.add("合计");
		// 设计表头
		HSSFCell cellTitleOne;
		for (int i = 0; i < titles.size(); i++) {
			cellTitleOne = rowOne.createCell(i);
			cellTitleOne.setCellValue(titles.get(i));
			cellTitleOne.setCellStyle(styleCenter);
		}

		Map<String,Object> params = new HashMap<>();
		List<String> docTypeList = new ArrayList<>();
		if(StringUtil.isNotBlank(docType)){
			docTypeList.add(docType);
		}else{
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				docTypeList.add(e.getId());
			}
		}
		params.put("docTypeList",docTypeList);
		params.put("statusId",statusId);
		params.put("speId",speId);

		SysOrg org = null;
		if (StringUtil.isNotBlank(GlobalContext.getCurrentUser().getOrgFlow())) {
			org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		}

		// 对数据进行计算，初始化值
		// 住院医师
		HashMap<String, Integer> doctorTrainingMap = new HashMap<>();
		doctorTrainingMap.put("all", 0);
		// 在校专硕
		HashMap<String, Integer> graduateMap = new HashMap<>();
		graduateMap.put("all", 0);
		// 各专业所有学校总计
		Map<String,Integer> speAll = new HashMap<>();

		// 查询专业
		String searchOrgFlow = org.getOrgFlow() == null ? "" : org.getOrgFlow();
		// 查询人数信息
		params.put("orgFlow",searchOrgFlow);
		params.put("sessionNumber",sessionNumber);
		// 查询人员名单并处理数据
		Map<String,Integer> speInfos = new HashMap<>();
		speInfos.put("all", 0);
		List<Map<String,Object>> infos = recruitDoctorInfoBiz.getOrgRecruitSpeInfo(params);
		for (Map<String, Object> info : infos) {
			calculateRecruitStudentForReport(doctorTrainingMap, graduateMap, speAll, speInfos, info);
		}

		for (int i = 0; i < sysDictList.size(); i++) {
			if (sysDictList.get(i).getDictId().equals("50")) {
				continue;
			}
			HSSFRow rowTwo = sheet.createRow(i + 1);
			HSSFCell cellTitle1 = rowTwo.createCell(0);
			cellTitle1.setCellValue(sysDictList.get(i).getDictName());
			cellTitle1.setCellStyle(styleCenter);
			HSSFCell cellTitle2 = rowTwo.createCell(1);
			cellTitle2.setCellValue(speInfos.getOrDefault(sysDictList.get(i).getDictId() + "Company", 0));
			cellTitle2.setCellStyle(styleCenter);
			HSSFCell cellTitle3 = rowTwo.createCell(2);
			cellTitle3.setCellValue(speInfos.getOrDefault(sysDictList.get(i).getDictId() + "CompanyEntrust", 0));
			cellTitle3.setCellStyle(styleCenter);
			HSSFCell cellTitle4 = rowTwo.createCell(3);
			cellTitle4.setCellValue(speInfos.getOrDefault(sysDictList.get(i).getDictId() + "Social", 0));
			cellTitle4.setCellStyle(styleCenter);
			HSSFCell cellTitle5 = rowTwo.createCell(4);
			cellTitle5.setCellValue(speInfos.getOrDefault(sysDictList.get(i).getDictId() + "Graduate", 0));
			cellTitle5.setCellStyle(styleCenter);
			HSSFCell cellTitle6 = rowTwo.createCell(5);
			cellTitle6.setCellValue(speInfos.getOrDefault(sysDictList.get(i).getDictId() + "all", 0));
			cellTitle6.setCellStyle(styleCenter);
		}



		// 最下方的统计
		HSSFRow rowThree = sheet.createRow(sysDictList.size() + 1);
		HSSFCell cell1 = rowThree.createCell(0);
		cell1.setCellStyle(styleCenter);
		cell1.setCellValue("合计");
		HSSFCell cell2 = rowThree.createCell(1);
		cell2.setCellStyle(styleCenter);
		cell2.setCellValue(speAll.getOrDefault("Company", 0));
		HSSFCell cell3 = rowThree.createCell(2);
		cell3.setCellStyle(styleCenter);
		cell3.setCellValue(speAll.getOrDefault("CompanyEntrust", 0));
		HSSFCell cell4 = rowThree.createCell(3);
		cell4.setCellStyle(styleCenter);
		cell4.setCellValue(speAll.getOrDefault("Social", 0));
		HSSFCell cell5 = rowThree.createCell(4);
		cell5.setCellStyle(styleCenter);
		cell5.setCellValue(speAll.getOrDefault("Graduate", 0));
		HSSFCell cell6 = rowThree.createCell(5);
		cell6.setCellStyle(styleCenter);
		cell6.setCellValue(speAll.getOrDefault("all", 0));


		String fileName = "招录统计报表.xls";
		if (StringUtil.isNotBlank(sessionNumber)) {
			fileName = sessionNumber + "年招录统计报表.xls";
		}
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	/**
	 * @Author xieyh
	 * @Description 基地招录统计分析图表
	 * @Date  2024-10-28
	 **/
	@RequestMapping(value = {"/recruitReport"})
	public String recruitReport(Model model) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		String filter = "('$state':(store:appState),meta:(alias:!n,disabled:!f,index:'',key:orgFlow.keyword,negate:!f" +
				",params:(query:'" + currentUser.getOrgFlow() + "'),type:phrase),query:(match_phrase:(orgFlow.keyword:'" + currentUser.getOrgFlow() + "')))";
//		String iframe = "<iframe src=\"https://restest.njpdxx.com:5650/app/dashboards?auth_provider_hint=anonymous1#/view/41e46fe1-80c5-4f1b-bdba-c00822929a4b?embed=true" +
//				"&_g=(filters:!(" + filter + "),refreshInterval%3A(pause%3A!t%2Cvalue%3A60000)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow))&hide-filter-bar=true\"  height=\"2100\" width=\"1460\"></iframe>";
		String iframe = "<iframe src=\"https://js.ezhupei.com:5601/app/dashboards?auth_provider_hint=anonymous1#/view/0525f6c0-e4dc-45d4-a25f-bc338e758934?embed=true" +
				"&_g=(filters:!(" + filter + "),refreshInterval%3A(pause%3A!t%2Cvalue%3A60000)%2Ctime%3A(from%3Anow-15m%2Cto%3Anow))&hide-filter-bar=true\" height=\"2100\" width=\"1460\"></iframe>";
		model.addAttribute("iframe", iframe);
		return "jsres/zltjOrg/recruitReport";
	}

	@RequestMapping(value="/zltjOrgLocalAcc")
	public String zltjOrgLocalAcc(Model model,String roleFlag) throws UnsupportedEncodingException {
        model.addAttribute("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		List<SysOrg> orgs=new ArrayList<>();
        if (!com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			SysOrg sysorg = new SysOrg();
			sysorg.setOrgProvId(org.getOrgProvId());
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
				sysorg.setOrgCityId(org.getOrgCityId());
				model.addAttribute("orgCityId", org.getOrgCityId());
			}
            sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			orgs = orgBiz.searchOrg(sysorg);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			orgs.add(org);
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
			{
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
				orgs.addAll(joinOrgs);
			}
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		//国家基地
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		return  "jsres/zltjOrg/zltjOrgLocalAcc";
	}

	@RequestMapping(value="/zltjOrgLocalList")
	public String zltjOrgLocalList(Model model,String roleFlag
			,String sessionNumber,String orgFlow,String trainingTypeId,String trainingSpeId
			,String[] datas,String statusId	) throws UnsupportedEncodingException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());

		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers= Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgFlow",orgFlow);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("statusId",statusId);

		List<String> jointOrgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag) && StringUtil.isBlank(orgFlow)) {
			jointOrgFlowList.add(sysOrg.getOrgFlow());
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(sysOrg.getOrgLevelId()))
			{
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(sysOrg.getOrgFlow());
				if(joinOrgs!=null&&joinOrgs.size()>0)
				{
					for(SysOrg joinOrg:joinOrgs)
					jointOrgFlowList.add(joinOrg.getOrgFlow());
				}
			}
		}
		param.put("jointOrgFlowList",jointOrgFlowList);
		if(StringUtil.isNotBlank(orgFlow)){
			List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
			if (null == jointOrgList || jointOrgList.size()==0){
				param.put("orgFlow1",orgFlow);
			}else {
				param.put("orgFlow1",jointOrgList.get(0).getOrgFlow());
                param.put("isJointOrg", com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
		}
//		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjOrgLocalList(param);
		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjOrgLocalListNew(param);
		Map<String,Object> typeSpeNumMap=new HashMap<>();
		Map<String,Integer> speNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String speId= (String) map.get("speId");
				String typeId= (String) map.get("typeId");
				typeSpeNumMap.put(typeId+ speId, map.get("num"));

				//国家基地
				Integer sum2= (Integer) speNumMap.get(speId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				speNumMap.put(speId,sum2);

				Integer sum3=typeNumMap.get(typeId);
				if(sum3==null)
					sum3=0;
				sum3+=(Integer) map.get("num");
				typeNumMap.put(typeId,sum3);

			}
		}
		model.addAttribute("typeSpeNumMap",typeSpeNumMap);
		model.addAttribute("speNumMap",speNumMap);
		model.addAttribute("typeNumMap",typeNumMap);

        List<SysDict> spes = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(trainingTypeId);
		model.addAttribute("spes",spes);
		return  "jsres/zltjOrg/zltjOrgLocalList";
	}
	@RequestMapping(value="/exportZltjOrgLocal")
	public void exportZltjOrgLocal(Model model,String roleFlag
			,String sessionNumber,String orgFlow,String trainingTypeId,String trainingSpeId
			,String[] datas	,HttpServletResponse response) throws IOException {

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());

		String titleYear="";
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			titleYear=sessionNumber.replace(",","_");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("orgFlow",orgFlow);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("sessionNumbers",sessionNumbers);
		param.put("docTypeList",docTypeList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());

		List<String> jointOrgFlowList=new ArrayList<String>();
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag) && StringUtil.isBlank(orgFlow)) {
			jointOrgFlowList.add(sysOrg.getOrgFlow());
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(sysOrg.getOrgLevelId()))
			{
				List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(sysOrg.getOrgFlow());
				if(joinOrgs!=null&&joinOrgs.size()>0)
				{
					for(SysOrg joinOrg:joinOrgs)
					jointOrgFlowList.add(joinOrg.getOrgFlow());
				}
			}
		}
		param.put("jointOrgFlowList",jointOrgFlowList);

		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjOrgLocalList(param);
		Map<String,Object> typeSpeNumMap=new HashMap<>();
		Map<String,Integer> speNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		if(list!=null)
		{
			for(Map<String,Object> map:list)
			{
				//每个城市有多个届别
				String speId= (String) map.get("speId");
				String typeId= (String) map.get("typeId");
				typeSpeNumMap.put(typeId+ speId, map.get("num"));

				//国家基地
				Integer sum2= (Integer) speNumMap.get(speId);
				if(sum2==null)
					sum2=0;
				sum2+=(Integer) map.get("num");
				speNumMap.put(speId,sum2);

				Integer sum3=typeNumMap.get(typeId);
				if(sum3==null)
					sum3=0;
				sum3+=(Integer) map.get("num");
				typeNumMap.put(typeId,sum3);

			}
		}
		model.addAttribute("typeSpeNumMap",typeSpeNumMap);
		model.addAttribute("speNumMap",speNumMap);
		model.addAttribute("typeNumMap",typeNumMap);

        List<SysDict> spes = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(trainingTypeId);
		model.addAttribute("spes",spes);

		String fileName = titleYear+"招录学员统计表.xls";
		fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		OutputStream os = response.getOutputStream();
		Workbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		Sheet sheet = wb.createSheet("sheet1");
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		//HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		CellStyle style = wb.createCellStyle();
		style.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		style.setFont(font);

		Font fontTwo = wb.createFont();
		fontTwo.setFontHeightInPoints((short) 12);

		CellStyle styleTwo = wb.createCellStyle();
		styleTwo.setVerticalAlignment(VerticalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
		styleTwo.setFont(fontTwo);
		//
		if (StringUtil.isNotBlank(trainingTypeId)) {
			List<String> titleList=new ArrayList<>();
			titleList.add("专业");
            for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
				titleList.add(e.getName());
			}
			titleList.add("小计");
			Map<Integer, Integer> columnWidth = new HashMap<>();
			Row row = sheet.createRow(0);
			Cell cell = null;
			for (int i = 0; i < titleList.size(); i++) {
				String title = titleList.get(i);
				cell = row.createCell(i);
				cell.setCellValue(title);
				cell.setCellStyle(style);
				//宽度自适应
				int nl = title.toString().getBytes().length;
				if (columnWidth.containsKey(i)) {
					Integer ol = columnWidth.get(i);
					if (ol < nl)
						columnWidth.put(i, nl);
				} else {
					columnWidth.put(i, nl);
				}
			}
			int rowNum=1;
			if (spes != null) {
				Cell rowCell = null;
				for(SysDict spe:spes)
				{
					if(StringUtil.isBlank(trainingSpeId)||trainingSpeId.equals(spe.getDictId())) {
						row = sheet.createRow(rowNum++);
						rowCell = row.createCell(0);
						rowCell.setCellStyle(styleTwo);
						rowCell.setCellValue(spe.getDictName());

						int k = 1;
						int sum = 0;
						//宽度自适应
						ExcleUtile.setColumnWidth(spe.getDictName().toString().getBytes().length, 0, columnWidth);

                        for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
							String id = e.getId();
								String key = id + spe.getDictId();
								Integer num = (Integer) typeSpeNumMap.get(key);
								if (num == null)
									num = 0;
								sum += num;
								rowCell = row.createCell(k);
								rowCell.setCellStyle(styleTwo);
								rowCell.setCellValue(String.valueOf(num));
								//宽度自适应
								ExcleUtile.setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
								k++;
						}
						rowCell = row.createCell(k);
						rowCell.setCellStyle(styleTwo);
						rowCell.setCellValue(String.valueOf(sum));
						//宽度自适应
						ExcleUtile.setColumnWidth(String.valueOf(sum).toString().getBytes().length, k, columnWidth);
					}
				}
				row = sheet.createRow(rowNum++);
				int k = 0;
				rowCell = row.createCell(k);
				rowCell.setCellStyle(styleTwo);
				rowCell.setCellValue("合计");
				//宽度自适应
				ExcleUtile.setColumnWidth("合计".toString().getBytes().length, k++, columnWidth);
				int hjsum = 0;

                for (com.pinde.core.common.enums.ResDocTypeEnum e : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
						String key = e.getId();
						Integer num = (Integer) typeNumMap.get(key);
						if (num == null)
							num = 0;
						hjsum += num;
						rowCell = row.createCell(k);
						rowCell.setCellStyle(styleTwo);
						rowCell.setCellValue(String.valueOf(num));
						//宽度自适应
						ExcleUtile.setColumnWidth(String.valueOf(num).toString().getBytes().length, k, columnWidth);
						k++;

				}
				rowCell = row.createCell(k);
				rowCell.setCellStyle(styleTwo);
				rowCell.setCellValue(String.valueOf(hjsum));
				//宽度自适应
				ExcleUtile.setColumnWidth(String.valueOf(hjsum).toString().getBytes().length, k, columnWidth);
				Set<Integer> keys = columnWidth.keySet();
				for (Integer key : keys) {
					int width = columnWidth.get(key);
					sheet.setColumnWidth(key, width * 2 * 256);
				}
			}
			Set<Integer> keys = columnWidth.keySet();
			for (Integer key : keys) {
				int width = columnWidth.get(key);
				sheet.setColumnWidth(key, width * 2 * 200);
			}
		}
		wb.write(os);
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	@RequestMapping(value="/exportForDetail")
	public void exportForDetail(String roleFlag,HttpServletRequest request,
								 String orgCityId, String orgLevel, HttpServletResponse response,
								 String orgFlow, String trainingTypeId,
								 String trainingSpeId, String sessionNumber,String signupWay,
								 String graduationYear, String idNo,String doctorStatusId,
								 String userName, String workOrgName,String[] datas, String[] yearDatas,
								 String jointOrgFlag,String joinOrgFlow,String isArmy)throws Exception{

		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg sysOrg=orgBiz.readSysOrg(sysuser.getOrgFlow());
		String titleYear="";
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			titleYear=sessionNumber.replace(",","_");
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		List<String> trainYearList=new ArrayList<String>();
		if(yearDatas!=null&&yearDatas.length>0){
			for(String s:yearDatas){
				trainYearList.add(s);
			}
		}

		Map<String,Object> param=new HashMap<>();
        if (StringUtil.isBlank(orgCityId) && com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			orgCityId=sysOrg.getOrgCityId();
		}
		if(StringUtil.isNotBlank(joinOrgFlow)){
			param.put("orgCityId", "");
		}else{
			param.put("orgCityId", orgCityId);
		}
//		param.put("orgCityId",orgCityId);
		param.put("doctorStatusId",doctorStatusId);
		param.put("orgProvId",sysOrg.getOrgProvId());
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("idNo",idNo);
		param.put("userName",userName);
		param.put("workOrgName",workOrgName);
		param.put("docTypeList",docTypeList);
		param.put("trainYearList",trainYearList);
        param.put("orgTypeId", com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		param.put("sessionNumbers",sessionNumbers);
		param.put("signupWay",signupWay);

		if(StringUtil.isNotBlank(workOrgName)){
            List<SysDict> sendSchools = com.pinde.core.common.enums.DictTypeEnum.sysListDictMap.get(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
			if(sendSchools!=null && sendSchools.size()>0){
				for(SysDict dict :sendSchools){
					if(workOrgName.equals(dict.getDictName())){
						param.put("workOrgId",dict.getDictId());
						break;
					}
				}
			}
		}

		List<String> jointOrgFlowList=new ArrayList<String>();
		if (StringUtil.isBlank(orgFlow)) {
            if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                jointOrgFlowList = searchJointOrgList(com.pinde.core.common.GlobalConstant.FLAG_Y, sysuser.getOrgFlow());
				jointOrgFlowList.add(sysuser.getOrgFlow());
			}
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)
                    || com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
//				if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
				SysOrg searchOrg=new SysOrg();
				searchOrg.setOrgProvId(sysOrg.getOrgProvId());
                searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
				if(StringUtil.isNotBlank(orgCityId)){
					searchOrg.setOrgCityId(orgCityId);
				}
				if(StringUtil.isNotBlank(orgLevel)){
					searchOrg.setOrgLevelId(orgLevel);
				}
				List<SysOrg>exitOrgs=orgBiz.searchOrg(searchOrg);
				for(SysOrg g:exitOrgs){
					List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlowNotSessionYear(g.getOrgFlow());
					if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
						for(ResJointOrg jointOrg:resJointOrgList){
							if(StringUtil.isNotBlank(orgCityId)) {
								String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
								if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
									jointOrgFlowList.add(jointOrg.getJointOrgFlow());
								}
							}else{
								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
							}
						}
					}
					jointOrgFlowList.add(g.getOrgFlow());
				}
//				}
			}
		}else{
			jointOrgFlowList.add(orgFlow);
			//选择主培训基地 直接查询主基地以及协同基地数据 2021/5/7修改
			List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlowNotSessionYear(orgFlow);
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()) {
				for (ResJointOrg jointOrg : resJointOrgList) {
					String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
					if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}else{
						jointOrgFlowList.add(jointOrg.getJointOrgFlow());
					}
				}
			}
//			if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
//				List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
//				if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
//					for(ResJointOrg jointOrg:resJointOrgList){
//						//禅道3386 改动
////						if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
////							String cityId = orgBiz.readSysOrg(jointOrg.getJointOrgFlow()).getOrgCityId();
////							if(StringUtil.isNotBlank(cityId)&&cityId.equals(orgCityId)){
////								jointOrgFlowList.add(jointOrg.getJointOrgFlow());
////							}
////						}else{
//							jointOrgFlowList.add(jointOrg.getJointOrgFlow());
////						}
//					}
//				}
//			}
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jointOrgFlag)) {
			orgLevel="";
		}
		if(StringUtil.isNotBlank(joinOrgFlow)){
			orgLevel="";
		}
		param.put("orgLevel",orgLevel);
		param.put("jointOrgFlowList",jointOrgFlowList);
		param.put("joinOrgFlow",joinOrgFlow);
		param.put("isArmy",isArmy);
//		List<JsRecruitDocInfoExt> doctorInfoExts= recruitDoctorInfoBiz.searchRecruitDoctorInfosByResume(param);
//		jsResDoctorBiz.exportForRecruitDetail(doctorInfoExts,titleYear, response);
		List<JsresDoctorInfoExt> doctorInfoExts= recruitDoctorInfoBiz.searchRecruitDoctorInfosByResume3(param);
		jsResDoctorBiz.exportToXlsx(request,response,titleYear+"学生信息一览表.xls",doctorInfoExts);
	}

	@RequestMapping(value="/queryJoinOrg")
	@ResponseBody
	public List<ResJointOrg> queryJoinOrg(String orgFlow){
		if(StringUtil.isBlank(orgFlow)) {
			orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		}
		return jsResDoctorBiz.queryJoinOrg(orgFlow);
	}
}
