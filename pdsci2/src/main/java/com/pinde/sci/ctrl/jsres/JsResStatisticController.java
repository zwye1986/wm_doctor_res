package com.pinde.sci.ctrl.jsres;

import com.alibaba.fastjson.JSON;
import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.inx.impl.InxBizImpl;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.jsres.IJsResStatisticBiz;
import com.pinde.sci.biz.jsres.IResOrgSpeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.res.ISysMonthlyStatisticsBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.*;
import com.pinde.core.common.PasswordHelper;
import com.pinde.sci.ctrl.cfg.JsresPowerCfgController;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.core.common.enums.jsres.JsResTeacherLevelEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.CertificateTypeEnum;
import com.pinde.sci.model.jsres.JsDoctorInfoExt;
import com.pinde.sci.model.mo.*;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author PPBear
 *
 */
@Controller
@RequestMapping("/jsres/statistic")
public class JsResStatisticController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(JsResStatisticController.class);

	/**
	 * @author PPBear
	 */
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResJointOrgBiz resJointOrgBiz;
	@Autowired
	private IResOrgSpeBiz resOrgSpeBiz;
	@Autowired
	private IJsResStatisticBiz resStatisticBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ISysMonthlyStatisticsBiz sysMonthlyStatisticsBiz;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;

	@RequestMapping("/statisticCountryOrg")
	public String statisticCountryOrg(Model model,String sessionNumber,String orgLevel,String[] datas){
		String trainTypeId="DoctorTrainingSpe";	//住院医师
		Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
		Map<Object, Object> joingCountMap=new HashMap<Object,Object>();//保存每家基地的协同基地的每个专业的总数
		Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
		List<String>orgFlowList=null;
		List<String>docTypeList=new ArrayList<String>();
		List<String>jointOrgFlowList=null;
		ResDoctor doctor=new ResDoctor();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if (month < 9) {
				year = cal.get(Calendar.YEAR) - 1;
			} else {
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = year + "";
			model.addAttribute("sessionNumber", sessionNumber);
		}else {
			model.addAttribute("sessionNumber", sessionNumber);
		}
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		//查询所有国家基地
		List<SysOrg> sysOrgList=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(s.getOrgCityId());
		}
		if(StringUtil.isNotBlank(orgLevel)){
			org.setOrgLevelId(orgLevel);
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			sysOrgList=orgBiz.searchAllSysOrg(org);
		}else{
				sysOrgList=resJointOrgBiz.searchCouAndProList(org);
			}
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg o:sysOrgList){
				orgFlowList=new ArrayList<String>();
				jointOrgFlowList=new ArrayList<String>();
				ResOrgSpe resOrgSpe=new ResOrgSpe();
				resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
				List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
				if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家主基地的专业
					for(ResOrgSpe r:orgSpeList){
                        orgSpeFlagMap.put(o.getOrgFlow() + r.getSpeTypeId() + r.getSpeId(), com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
				orgFlowList.add(o.getOrgFlow());//查询每家基地及其协同基地的总数
				List<ResJointOrg>jointOrgList=resJointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					for(ResJointOrg resJointOrg:jointOrgList){
						jointOrgFlowList.add(resJointOrg.getJointOrgFlow());
						orgFlowList.add(resJointOrg.getJointOrgFlow());
					}
					List<Map<String, Object>>jointCountList=resStatisticBiz.statisticJointCount(recruit, jointOrgFlowList,doctor,docTypeList);
					if(jointCountList!=null&&!jointCountList.isEmpty()){
						for(Map<String,Object> en:jointCountList){
							Object key=o.getOrgFlow()+en.get("key");
							Object value= en.get("value");
							joingCountMap.put(key, value);
						}
					}
				}
				//每家基地每个专业的医师培训记录总数 
				List<Map<String,Object>> doctorCountList=resStatisticBiz.statisticJointCount(recruit,orgFlowList,doctor,docTypeList);
				if(doctorCountList!=null&&!doctorCountList.isEmpty()){
					for(Map<String,Object> en:doctorCountList){
						Object key=o.getOrgFlow()+en.get("key");
						Object value= en.get("value");
						totalCountMap.put(key, value);
					}
				}
			}
		}
		model.addAttribute("joingCountMap", joingCountMap);
		model.addAttribute("datas", datas);
		model.addAttribute("totalCountMap", totalCountMap);
		model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);
		model.addAttribute("sysOrgList", sysOrgList);


		/***************报表统计****************/
		//图1 国家基地 住院医师人员统计 区分专硕和非专硕
		List<Map<String,String>> list1=resStatisticBiz.findCountryDocCount(sessionNumber);
		Map<String,Map<String,String>> map=new HashMap<>();
		List<String> orgFlows=new ArrayList<>();
		if(list1!=null&&list1.size()>0){
			for(Map<String,String> m:list1) {
				String orgFlow=m.get("orgFlow");
				map.put(orgFlow + m.get("doctorTypeId"), m);
				map.put(orgFlow, m);
				if(!orgFlows.contains(orgFlow)){
					orgFlows.add(orgFlow);
				}
			}
		}
		model.addAttribute("orgFlows",orgFlows);
		model.addAttribute("countoryMap",map);
		//图2 省级基地 住院医师人员统计 区分专硕和非专硕
		List<Map<String,String>> ProList=resStatisticBiz.findProDocCount(sessionNumber);
		Map<String,Map<String,String>> ProMap=new HashMap<>();
		List<String> ProorgFlows=new ArrayList<>();
		if(ProList!=null&&ProList.size()>0){
			for(Map<String,String> m:ProList) {
				String orgFlow=m.get("orgFlow");
				ProMap.put(orgFlow + m.get("doctorTypeId"), m);
				ProMap.put(orgFlow, m);
				if(!ProorgFlows.contains(orgFlow)){
					ProorgFlows.add(orgFlow);
				}
			}
		}
		model.addAttribute("ProorgFlows",ProorgFlows);
		model.addAttribute("ProMap",ProMap);
		//图3 国家基地 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> listSpe1=resStatisticBiz.findCountrySpeDocCount(sessionNumber);
		Map<String,Map<String,String>> mapSpe=new HashMap<>();
		List<String> SpeIds=new ArrayList<>();
		if(listSpe1!=null&&listSpe1.size()>0){
			for(Map<String,String> m:listSpe1) {
				String speId=m.get("speId");
				mapSpe.put(speId + m.get("doctorTypeId"), m);
				mapSpe.put(speId, m);
				if(!SpeIds.contains(speId)){
					SpeIds.add(speId);
				}
			}
		}
		model.addAttribute("SpeIds",SpeIds);
		model.addAttribute("mapSpe",mapSpe);
		//图4 省级基地 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> ProSpeList=resStatisticBiz.findProSpeDocCount(sessionNumber);
		Map<String,Map<String,String>> ProSpeMap=new HashMap<>();
		List<String> ProSpeIds=new ArrayList<>();
		if(ProSpeList!=null&&ProSpeList.size()>0){
			for(Map<String,String> m:ProSpeList) {
				String speId=m.get("speId");
				ProSpeMap.put(speId + m.get("doctorTypeId"), m);
				ProSpeMap.put(speId, m);
				if(!ProSpeIds.contains(speId)){
					ProSpeIds.add(speId);
				}
			}
		}
		model.addAttribute("ProSpeIds",ProSpeIds);
		model.addAttribute("ProSpeMap",ProSpeMap);
		//图5 按人员类型区分
		List<Map<String,String>> typeList=resStatisticBiz.findDocTypeCount(sessionNumber);
		Map<String,Map<String,String>> pieMap=new HashMap<>();
		if(typeList!=null&&typeList.size()>0){
			for(Map<String,String> m:typeList) {
				String speId=m.get("doctorTypeId");
				pieMap.put(speId, m);
			}
		}
		model.addAttribute("pieMap",pieMap);
		//图6 按基地类型区分
		List<Map<String,String>> orgTypeList=resStatisticBiz.findOrgTypeCount(sessionNumber);
		Map<String,Map<String,String>> orgPieMap=new HashMap<>();
		if(orgTypeList!=null&&orgTypeList.size()>0){
			for(Map<String,String> m:orgTypeList) {
				String speId=m.get("orgLevelId");
				orgPieMap.put(speId, m);
			}
		}
		model.addAttribute("orgPieMap",orgPieMap);

		//图7 地市 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> CityList=resStatisticBiz.findCityDocCount(sessionNumber);
		Map<String,Map<String,String>> CityMap=new HashMap<>();
		List<String> CityIds=new ArrayList<>();
		if(CityList!=null&&CityList.size()>0){
			for(Map<String,String> m:CityList) {
				String speId=m.get("orgCityId");
				CityMap.put(speId + m.get("doctorTypeId"), m);
				CityMap.put(speId, m);
				if(!CityIds.contains(speId)){
					CityIds.add(speId);
				}
			}
		}
		model.addAttribute("CityIds",CityIds);
		model.addAttribute("CityMap",CityMap);
		//图8  各基地助理全科人员统计 不区分专硕和非专硕
		List<Map<String,String>> OrgAssiList=resStatisticBiz.findOrgAssiCount(sessionNumber);
		Map<String,Map<String,String>> OrgAssiMap=new HashMap<>();
		List<String> OrgIds=new ArrayList<>();
		if(OrgAssiList!=null&&OrgAssiList.size()>0){
			for(Map<String,String> m:OrgAssiList) {
				String speId=m.get("orgFlow");
				OrgAssiMap.put(speId, m);
				if(!OrgIds.contains(speId)){
					OrgIds.add(speId);
				}
			}
		}
		model.addAttribute("OrgIds",OrgIds);
		model.addAttribute("OrgAssiMap",OrgAssiMap);
		//图9  各专业在校专硕人员统计
		List<Map<String,String>> SpeGraduateList=resStatisticBiz.findSpeGraduateCount(sessionNumber);
		Map<String,Map<String,String>> SpeGraduateMap=new HashMap<>();
		List<String> SpeGraduateIds=new ArrayList<>();
		if(SpeGraduateList!=null&&SpeGraduateList.size()>0){
			for(Map<String,String> m:SpeGraduateList) {
				String speId=m.get("speId");
				SpeGraduateMap.put(speId, m);
				if(!SpeGraduateIds.contains(speId)){
					SpeGraduateIds.add(speId);
				}
			}
		}
		model.addAttribute("SpeGraduateIds",SpeGraduateIds);
		model.addAttribute("SpeGraduateMap",SpeGraduateMap);
		//图10  各基地在校专硕人员统计
		List<Map<String,String>> OrgGraduateList=resStatisticBiz.findOrgGraduateCount(sessionNumber);
		Map<String,Map<String,String>> OrgGraduateMap=new HashMap<>();
		List<String> OrgGraduateIds=new ArrayList<>();
		if(OrgGraduateList!=null&&OrgGraduateList.size()>0){
			for(Map<String,String> m:OrgGraduateList) {
				String speId=m.get("orgFlow");
				OrgGraduateMap.put(speId, m);
				if(!OrgGraduateIds.contains(speId)){
					OrgGraduateIds.add(speId);
				}
			}
		}
		model.addAttribute("OrgGraduateIds",OrgGraduateIds);
		model.addAttribute("OrgGraduateMap",OrgGraduateMap);
		/***************报表统计****************/


		return "/jsres/global/statistics/statisticCountryOrg";
	}

	@RequestMapping("/statisticCountryOrgAcc")
	public String statisticCountryOrgAcc(Model model,String sessionNumber,String orgLevel,String[] datas,String trainTypeId){
		trainTypeId="AssiGeneral";	//助理全科
		Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
		Map<Object, Object> joingCountMap=new HashMap<Object,Object>();//保存每家基地的协同基地的每个专业的总数
		Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
		List<String>orgFlowList=null;
		List<String>docTypeList=new ArrayList<String>();
		List<String>jointOrgFlowList=null;
		ResDoctor doctor=new ResDoctor();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if (month < 9) {
				year = cal.get(Calendar.YEAR) - 1;
			} else {
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = year + "";
			model.addAttribute("sessionNumber", sessionNumber);
		}else {
			model.addAttribute("sessionNumber", sessionNumber);
		}
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		//查询所有国家基地
		List<SysOrg> sysOrgList=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(s.getOrgCityId());
		}
		if(StringUtil.isNotBlank(orgLevel)){
			org.setOrgLevelId(orgLevel);
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			sysOrgList=orgBiz.searchAllSysOrg(org);
		}else{
			sysOrgList=resJointOrgBiz.searchCouAndProList(org);
		}
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg o:sysOrgList){
				orgFlowList=new ArrayList<String>();
				jointOrgFlowList=new ArrayList<String>();
				ResOrgSpe resOrgSpe=new ResOrgSpe();
				resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
				List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
				if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家主基地的专业
					for(ResOrgSpe r:orgSpeList){
                        orgSpeFlagMap.put(o.getOrgFlow() + r.getSpeTypeId() + r.getSpeId(), com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
				orgFlowList.add(o.getOrgFlow());//查询每家基地及其协同基地的总数
				List<ResJointOrg>jointOrgList=resJointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					for(ResJointOrg resJointOrg:jointOrgList){
						jointOrgFlowList.add(resJointOrg.getJointOrgFlow());
						orgFlowList.add(resJointOrg.getJointOrgFlow());
					}
					List<Map<String, Object>>jointCountList=resStatisticBiz.statisticJointCount(recruit, jointOrgFlowList,doctor,docTypeList);
					if(jointCountList!=null&&!jointCountList.isEmpty()){
						for(Map<String,Object> en:jointCountList){
							Object key=o.getOrgFlow()+en.get("key");
							Object value= en.get("value");
							joingCountMap.put(key, value);
						}
					}
				}
				//每家基地每个专业的医师培训记录总数
				List<Map<String,Object>> doctorCountList=resStatisticBiz.statisticJointCount(recruit,orgFlowList,doctor,docTypeList);
				if(doctorCountList!=null&&!doctorCountList.isEmpty()){
					for(Map<String,Object> en:doctorCountList){
						Object key=o.getOrgFlow()+en.get("key");
						Object value= en.get("value");
						totalCountMap.put(key, value);
					}
				}
			}
		}
		model.addAttribute("joingCountMap", joingCountMap);
		model.addAttribute("datas", datas);
		model.addAttribute("totalCountMap", totalCountMap);
		model.addAttribute("orgSpeFlagMap", orgSpeFlagMap);
		model.addAttribute("sysOrgList", sysOrgList);


		/***************报表统计****************/
		//图1 国家基地 住院医师人员统计 区分专硕和非专硕
		List<Map<String,String>> list1=resStatisticBiz.findCountryDocCount(sessionNumber);
		Map<String,Map<String,String>> map=new HashMap<>();
		List<String> orgFlows=new ArrayList<>();
		if(list1!=null&&list1.size()>0){
			for(Map<String,String> m:list1) {
				String orgFlow=m.get("orgFlow");
				map.put(orgFlow + m.get("doctorTypeId"), m);
				map.put(orgFlow, m);
				if(!orgFlows.contains(orgFlow)){
					orgFlows.add(orgFlow);
				}
			}
		}
		model.addAttribute("orgFlows",orgFlows);
		model.addAttribute("countoryMap",map);
		//图2 省级基地 住院医师人员统计 区分专硕和非专硕
		List<Map<String,String>> ProList=resStatisticBiz.findProDocCount(sessionNumber);
		Map<String,Map<String,String>> ProMap=new HashMap<>();
		List<String> ProorgFlows=new ArrayList<>();
		if(ProList!=null&&ProList.size()>0){
			for(Map<String,String> m:ProList) {
				String orgFlow=m.get("orgFlow");
				ProMap.put(orgFlow + m.get("doctorTypeId"), m);
				ProMap.put(orgFlow, m);
				if(!ProorgFlows.contains(orgFlow)){
					ProorgFlows.add(orgFlow);
				}
			}
		}
		model.addAttribute("ProorgFlows",ProorgFlows);
		model.addAttribute("ProMap",ProMap);
		//图3 国家基地 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> listSpe1=resStatisticBiz.findCountrySpeDocCount(sessionNumber);
		Map<String,Map<String,String>> mapSpe=new HashMap<>();
		List<String> SpeIds=new ArrayList<>();
		if(listSpe1!=null&&listSpe1.size()>0){
			for(Map<String,String> m:listSpe1) {
				String speId=m.get("speId");
				mapSpe.put(speId + m.get("doctorTypeId"), m);
				mapSpe.put(speId, m);
				if(!SpeIds.contains(speId)){
					SpeIds.add(speId);
				}
			}
		}
		model.addAttribute("SpeIds",SpeIds);
		model.addAttribute("mapSpe",mapSpe);
		//图4 省级基地 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> ProSpeList=resStatisticBiz.findProSpeDocCount(sessionNumber);
		Map<String,Map<String,String>> ProSpeMap=new HashMap<>();
		List<String> ProSpeIds=new ArrayList<>();
		if(ProSpeList!=null&&ProSpeList.size()>0){
			for(Map<String,String> m:ProSpeList) {
				String speId=m.get("speId");
				ProSpeMap.put(speId + m.get("doctorTypeId"), m);
				ProSpeMap.put(speId, m);
				if(!ProSpeIds.contains(speId)){
					ProSpeIds.add(speId);
				}
			}
		}
		model.addAttribute("ProSpeIds",ProSpeIds);
		model.addAttribute("ProSpeMap",ProSpeMap);
		//图5 按人员类型区分
		List<Map<String,String>> typeList=resStatisticBiz.findDocTypeCount(sessionNumber);
		Map<String,Map<String,String>> pieMap=new HashMap<>();
		if(typeList!=null&&typeList.size()>0){
			for(Map<String,String> m:typeList) {
				String speId=m.get("doctorTypeId");
				pieMap.put(speId, m);
			}
		}
		model.addAttribute("pieMap",pieMap);
		//图6 按基地类型区分
		List<Map<String,String>> orgTypeList=resStatisticBiz.findOrgTypeCount(sessionNumber);
		Map<String,Map<String,String>> orgPieMap=new HashMap<>();
		if(orgTypeList!=null&&orgTypeList.size()>0){
			for(Map<String,String> m:orgTypeList) {
				String speId=m.get("orgLevelId");
				orgPieMap.put(speId, m);
			}
		}
		model.addAttribute("orgPieMap",orgPieMap);

		//图7 地市 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> CityList=resStatisticBiz.findCityDocCount(sessionNumber);
		Map<String,Map<String,String>> CityMap=new HashMap<>();
		List<String> CityIds=new ArrayList<>();
		if(CityList!=null&&CityList.size()>0){
			for(Map<String,String> m:CityList) {
				String speId=m.get("orgCityId");
				CityMap.put(speId + m.get("doctorTypeId"), m);
				CityMap.put(speId, m);
				if(!CityIds.contains(speId)){
					CityIds.add(speId);
				}
			}
		}
		model.addAttribute("CityIds",CityIds);
		model.addAttribute("CityMap",CityMap);
		//图8  各基地助理全科人员统计 不区分专硕和非专硕
		List<Map<String,String>> OrgAssiList=resStatisticBiz.findOrgAssiCount(sessionNumber);
		Map<String,Map<String,String>> OrgAssiMap=new HashMap<>();
		List<String> OrgIds=new ArrayList<>();
		if(OrgAssiList!=null&&OrgAssiList.size()>0){
			for(Map<String,String> m:OrgAssiList) {
				String speId=m.get("orgFlow");
				OrgAssiMap.put(speId, m);
				if(!OrgIds.contains(speId)){
					OrgIds.add(speId);
				}
			}
		}
		model.addAttribute("OrgIds",OrgIds);
		model.addAttribute("OrgAssiMap",OrgAssiMap);
		//图9  各专业在校专硕人员统计
		List<Map<String,String>> SpeGraduateList=resStatisticBiz.findSpeGraduateCount(sessionNumber);
		Map<String,Map<String,String>> SpeGraduateMap=new HashMap<>();
		List<String> SpeGraduateIds=new ArrayList<>();
		if(SpeGraduateList!=null&&SpeGraduateList.size()>0){
			for(Map<String,String> m:SpeGraduateList) {
				String speId=m.get("speId");
				SpeGraduateMap.put(speId, m);
				if(!SpeGraduateIds.contains(speId)){
					SpeGraduateIds.add(speId);
				}
			}
		}
		model.addAttribute("SpeGraduateIds",SpeGraduateIds);
		model.addAttribute("SpeGraduateMap",SpeGraduateMap);
		//图10  各基地在校专硕人员统计
		List<Map<String,String>> OrgGraduateList=resStatisticBiz.findOrgGraduateCount(sessionNumber);
		Map<String,Map<String,String>> OrgGraduateMap=new HashMap<>();
		List<String> OrgGraduateIds=new ArrayList<>();
		if(OrgGraduateList!=null&&OrgGraduateList.size()>0){
			for(Map<String,String> m:OrgGraduateList) {
				String speId=m.get("orgFlow");
				OrgGraduateMap.put(speId, m);
				if(!OrgGraduateIds.contains(speId)){
					OrgGraduateIds.add(speId);
				}
			}
		}
		model.addAttribute("OrgGraduateIds",OrgGraduateIds);
		model.addAttribute("OrgGraduateMap",OrgGraduateMap);
		/***************报表统计****************/


		return "/jsres/global/statistics/statisticCountryOrgAcc";
	}

	@RequestMapping("/chartsInfo")
	public String chartsInfo(Model model,String sessionNumber){
		//图1 国家基地 住院医师人员统计 区分专硕和非专硕
		List<Map<String,String>> list1=resStatisticBiz.findCountryDocCount(sessionNumber);
		Map<String,Map<String,String>> map=new HashMap<>();
		List<String> orgFlows=new ArrayList<>();
		if(list1!=null&&list1.size()>0){
			for(Map<String,String> m:list1) {
				String orgFlow=m.get("orgFlow");
				map.put(orgFlow + m.get("doctorTypeId"), m);
				map.put(orgFlow, m);
				if(!orgFlows.contains(orgFlow)){
					orgFlows.add(orgFlow);
				}
			}
		}
		model.addAttribute("orgFlows",orgFlows);
		model.addAttribute("countoryMap",map);
		//图2 省级基地 住院医师人员统计 区分专硕和非专硕
		List<Map<String,String>> ProList=resStatisticBiz.findProDocCount(sessionNumber);
		Map<String,Map<String,String>> ProMap=new HashMap<>();
		List<String> ProorgFlows=new ArrayList<>();
		if(ProList!=null&&ProList.size()>0){
			for(Map<String,String> m:ProList) {
				String orgFlow=m.get("orgFlow");
				ProMap.put(orgFlow + m.get("doctorTypeId"), m);
				ProMap.put(orgFlow, m);
				if(!ProorgFlows.contains(orgFlow)){
					ProorgFlows.add(orgFlow);
				}
			}
		}
		model.addAttribute("ProorgFlows",ProorgFlows);
		model.addAttribute("ProMap",ProMap);
		//图3 国家基地 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> listSpe1=resStatisticBiz.findCountrySpeDocCount(sessionNumber);
		Map<String,Map<String,String>> mapSpe=new HashMap<>();
		List<String> SpeIds=new ArrayList<>();
		if(listSpe1!=null&&listSpe1.size()>0){
			for(Map<String,String> m:listSpe1) {
				String speId=m.get("speId");
				mapSpe.put(speId + m.get("doctorTypeId"), m);
				mapSpe.put(speId, m);
				if(!SpeIds.contains(speId)){
					SpeIds.add(speId);
				}
			}
		}
		model.addAttribute("SpeIds",SpeIds);
		model.addAttribute("mapSpe",mapSpe);
		//图4 省级基地 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> ProSpeList=resStatisticBiz.findProSpeDocCount(sessionNumber);
		Map<String,Map<String,String>> ProSpeMap=new HashMap<>();
		List<String> ProSpeIds=new ArrayList<>();
		if(ProSpeList!=null&&ProSpeList.size()>0){
			for(Map<String,String> m:ProSpeList) {
				String speId=m.get("speId");
				ProSpeMap.put(speId + m.get("doctorTypeId"), m);
				ProSpeMap.put(speId, m);
				if(!ProSpeIds.contains(speId)){
					ProSpeIds.add(speId);
				}
			}
		}
		model.addAttribute("ProSpeIds",ProSpeIds);
		model.addAttribute("ProSpeMap",ProSpeMap);
		//图5 按人员类型区分
		List<Map<String,String>> typeList=resStatisticBiz.findDocTypeCount(sessionNumber);
		Map<String,Map<String,String>> pieMap=new HashMap<>();
		if(typeList!=null&&typeList.size()>0){
			for(Map<String,String> m:typeList) {
				String speId=m.get("doctorTypeId");
				pieMap.put(speId, m);
			}
		}
		model.addAttribute("pieMap",pieMap);
		//图6 按基地类型区分
		List<Map<String,String>> orgTypeList=resStatisticBiz.findOrgTypeCount(sessionNumber);
		Map<String,Map<String,String>> orgPieMap=new HashMap<>();
		if(orgTypeList!=null&&orgTypeList.size()>0){
			for(Map<String,String> m:orgTypeList) {
				String speId=m.get("orgLevelId");
				orgPieMap.put(speId, m);
			}
		}
		model.addAttribute("orgPieMap",orgPieMap);

		//图7 地市 各专业人员统计 区分专硕和非专硕
		List<Map<String,String>> CityList=resStatisticBiz.findCityDocCount(sessionNumber);
		Map<String,Map<String,String>> CityMap=new HashMap<>();
		List<String> CityIds=new ArrayList<>();
		if(CityList!=null&&CityList.size()>0){
			for(Map<String,String> m:CityList) {
				String speId=m.get("orgCityId");
				CityMap.put(speId + m.get("doctorTypeId"), m);
				CityMap.put(speId, m);
				if(!CityIds.contains(speId)){
					CityIds.add(speId);
				}
			}
		}
		model.addAttribute("CityIds",CityIds);
		model.addAttribute("CityMap",CityMap);
		//图8  各基地助理全科人员统计 不区分专硕和非专硕
		List<Map<String,String>> OrgAssiList=resStatisticBiz.findOrgAssiCount(sessionNumber);
		Map<String,Map<String,String>> OrgAssiMap=new HashMap<>();
		List<String> OrgIds=new ArrayList<>();
		if(OrgAssiList!=null&&OrgAssiList.size()>0){
			for(Map<String,String> m:OrgAssiList) {
				String speId=m.get("orgFlow");
				OrgAssiMap.put(speId, m);
				if(!OrgIds.contains(speId)){
					OrgIds.add(speId);
				}
			}
		}
		model.addAttribute("OrgIds",OrgIds);
		model.addAttribute("OrgAssiMap",OrgAssiMap);
		//图9  各专业在校专硕人员统计
		List<Map<String,String>> SpeGraduateList=resStatisticBiz.findSpeGraduateCount(sessionNumber);
		Map<String,Map<String,String>> SpeGraduateMap=new HashMap<>();
		List<String> SpeGraduateIds=new ArrayList<>();
		if(SpeGraduateList!=null&&SpeGraduateList.size()>0){
			for(Map<String,String> m:SpeGraduateList) {
				String speId=m.get("speId");
				SpeGraduateMap.put(speId, m);
				if(!SpeGraduateIds.contains(speId)){
					SpeGraduateIds.add(speId);
				}
			}
		}
		model.addAttribute("SpeGraduateIds",SpeGraduateIds);
		model.addAttribute("SpeGraduateMap",SpeGraduateMap);
		//图10  各基地在校专硕人员统计
		List<Map<String,String>> OrgGraduateList=resStatisticBiz.findOrgGraduateCount(sessionNumber);
		Map<String,Map<String,String>> OrgGraduateMap=new HashMap<>();
		List<String> OrgGraduateIds=new ArrayList<>();
		if(OrgGraduateList!=null&&OrgGraduateList.size()>0){
			for(Map<String,String> m:OrgGraduateList) {
				String speId=m.get("orgFlow");
				OrgGraduateMap.put(speId, m);
				if(!OrgGraduateIds.contains(speId)){
					OrgGraduateIds.add(speId);
				}
			}
		}
		model.addAttribute("OrgGraduateIds",OrgGraduateIds);
		model.addAttribute("OrgGraduateMap",OrgGraduateMap);
		return "/jsres/global/statistics/chartsInfo";
	}

	@RequestMapping("/statisticJointOrg")
	public String statisticJointOrg(Model model,ResDoctorRecruit recruit ,ResDoctor doctor,String orgLevel,String[] datas){
		String trainTypeId="DoctorTrainingSpe";		//住院医师
		Map<Object, Object> jointOrgListMap= new HashMap<Object, Object>();//每个符合添加的org的协同基地的map
		Map<Object, Object> jointOrgSpeMap = new HashMap<Object,Object>();//协同基地的spe
		Map<Object, Object> jointOrgDocCountMap = new HashMap<Object,Object>();//协同基地每个soe的总数
		List<SysOrg> sysOrgList=new ArrayList<SysOrg>();//存放所有的符合条件的sysOrg
		List<String> jointOrgFlowList=new ArrayList<String>();//存放所有的协同基地的orglist
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(ss.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(ss.getOrgCityId());
		}
		if(StringUtil.isBlank(orgLevel)){
			sysOrgList=resJointOrgBiz.searchCouAndProList(org);
		}else{
			org.setOrgLevelId(orgLevel);
			sysOrgList=orgBiz.searchAllSysOrg(org);
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		String sessionNumber = recruit.getSessionNumber();
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if (month < 9) {
				year = cal.get(Calendar.YEAR) - 1;
			} else {
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = year + "";
			recruit.setSessionNumber(sessionNumber);
			model.addAttribute("sessionNumber", sessionNumber);
		}else {
			model.addAttribute("sessionNumber", sessionNumber);
		}
		List<ResJointOrg> jointOrgList = new ArrayList<>();
		List<ResJointOrg> allJointOrgList = new ArrayList<>();
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg s:sysOrgList ){//国家基地和省级基地的list
				jointOrgList=resJointOrgBiz.searchResJointByOrgFlow(s.getOrgFlow());//分别查出每个基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					int c=0;
					for(ResJointOrg re:jointOrgList){//循环协同基地
						String flag="";
						if(c==0){
                            flag = com.pinde.core.common.GlobalConstant.FLAG_Y;
						}else if(c==1){
                            flag = com.pinde.core.common.GlobalConstant.FLAG_F;
						}else if(c==2){
                            flag = com.pinde.core.common.GlobalConstant.FLAG_N;
						}//标记
						jointOrgListMap.put(s.getOrgFlow()+flag, re);//以当前的国家基地标记key   Map
						jointOrgFlowList.add(re.getJointOrgFlow());
						allJointOrgList.add(re);
						c++;
					}
				}
			}
		}
		if(jointOrgFlowList!=null &&!jointOrgFlowList.isEmpty()){
			ResOrgSpe resOrgSpe=new ResOrgSpe();
			for(String s:jointOrgFlowList){
				resOrgSpe.setOrgFlow(s);//添加条件查询每个协同基地的专业
				String type="";
				if(StringUtil.isNotBlank(trainTypeId)){
					resOrgSpe.setSpeTypeId(trainTypeId);
				}else if(StringUtil.isNotBlank(recruit.getSessionNumber())){
                    type = com.pinde.core.common.GlobalConstant.FLAG_Y;
					if(Integer.parseInt(recruit.getSessionNumber())<2015){
                        resOrgSpe.setSpeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
					}else{
                        resOrgSpe.setSpeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId());
					}
				}
				List<ResOrgSpe> speList=resOrgSpeBiz.searchSpeByCondition(resOrgSpe,type);
				jointOrgSpeMap.put(s, speList);
				
			}
			recruit.setCatSpeId(trainTypeId);
			List<Map<String,Object>> doctorSpeList=resStatisticBiz.statisticDocCouByType(recruit, jointOrgFlowList, doctor,docTypeList);
			Map<String,Integer> orgJointDocSumMap = new HashMap<>();
			for(Map<String,Object> en:doctorSpeList){
				Object key= en.get("key");
				Object value= en.get("value");
				jointOrgDocCountMap.put(key, value);// Map 已协同基地的flow家伙是哪个spe作为key

				//计算每家基地的协同基地的人数总计
				for(ResJointOrg re:allJointOrgList){
					if(key.toString().contains(re.getJointOrgFlow())){
						Integer count = orgJointDocSumMap.get(re.getOrgFlow());
						if(count == null){
							count = 0;
						}
						count += Integer.parseInt(value.toString());
						orgJointDocSumMap.put(re.getOrgFlow(),count);
						continue;
					}
				}
			}
			model.addAttribute("orgJointDocSumMap", orgJointDocSumMap);
		}
		model.addAttribute("datas", datas);
		model.addAttribute("jointOrgDocCountMap", jointOrgDocCountMap);
		model.addAttribute("jointOrgListMap", jointOrgListMap);
		model.addAttribute("jointOrgSpeMap", jointOrgSpeMap);
		model.addAttribute("sysOrgList", sysOrgList);
		return "/jsres/global/statistics/statisticJointOrgInfo";
	}

	@RequestMapping("/statisticJointOrgAcc")
	public String statisticJointOrgAcc(Model model,ResDoctorRecruit recruit ,ResDoctor doctor,String orgLevel,String[] datas,String  trainTypeId){
		trainTypeId="AssiGeneral";		//住院医师
		Map<Object, Object> jointOrgListMap= new HashMap<Object, Object>();//每个符合添加的org的协同基地的map
		Map<Object, Object> jointOrgSpeMap = new HashMap<Object,Object>();//协同基地的spe
		Map<Object, Object> jointOrgDocCountMap = new HashMap<Object,Object>();//协同基地每个soe的总数
		List<SysOrg> sysOrgList=new ArrayList<SysOrg>();//存放所有的符合条件的sysOrg
		List<String> jointOrgFlowList=new ArrayList<String>();//存放所有的协同基地的orglist
		List<String>docTypeList=new ArrayList<String>();
		SysOrg org=new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(ss.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(ss.getOrgCityId());
		}
		if(StringUtil.isBlank(orgLevel)){
			sysOrgList=resJointOrgBiz.searchCouAndProList(org);
		}else{
			org.setOrgLevelId(orgLevel);
			sysOrgList=orgBiz.searchAllSysOrg(org);
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		String sessionNumber = recruit.getSessionNumber();
		if(StringUtil.isBlank(sessionNumber)){
			int year = 0;
			Calendar cal = Calendar.getInstance();
			int month = cal.get(Calendar.MONTH) + 1;
			if (month < 9) {
				year = cal.get(Calendar.YEAR) - 1;
			} else {
				year = cal.get(Calendar.YEAR);
			}
			sessionNumber = year + "";
			recruit.setSessionNumber(sessionNumber);
			model.addAttribute("sessionNumber", sessionNumber);
		}else {
			model.addAttribute("sessionNumber", sessionNumber);
		}
		List<ResJointOrg> jointOrgList = new ArrayList<>();
		List<ResJointOrg> allJointOrgList = new ArrayList<>();
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg s:sysOrgList ){//国家基地和省级基地的list
				jointOrgList=resJointOrgBiz.searchResJointByOrgFlow(s.getOrgFlow());//分别查出每个基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					int c=0;
					for(ResJointOrg re:jointOrgList){//循环协同基地
						String flag="";
						if(c==0){
                            flag = com.pinde.core.common.GlobalConstant.FLAG_Y;
						}else if(c==1){
                            flag = com.pinde.core.common.GlobalConstant.FLAG_F;
						}else if(c==2){
                            flag = com.pinde.core.common.GlobalConstant.FLAG_N;
						}//标记
						jointOrgListMap.put(s.getOrgFlow()+flag, re);//以当前的国家基地标记key   Map
						jointOrgFlowList.add(re.getJointOrgFlow());
						allJointOrgList.add(re);
						c++;
					}
				}
			}
		}
		if(jointOrgFlowList!=null &&!jointOrgFlowList.isEmpty()){
			ResOrgSpe resOrgSpe=new ResOrgSpe();
			for(String s:jointOrgFlowList){
				resOrgSpe.setOrgFlow(s);//添加条件查询每个协同基地的专业
				String type="";
				if(StringUtil.isNotBlank(trainTypeId)){
					resOrgSpe.setSpeTypeId(trainTypeId);
				}else if(StringUtil.isNotBlank(recruit.getSessionNumber())){
                    type = com.pinde.core.common.GlobalConstant.FLAG_Y;
					if(Integer.parseInt(recruit.getSessionNumber())<2015){
                        resOrgSpe.setSpeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
					}else{
                        resOrgSpe.setSpeTypeId(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId());
					}
				}
				List<ResOrgSpe> speList=resOrgSpeBiz.searchSpeByCondition(resOrgSpe,type);
				jointOrgSpeMap.put(s, speList);

			}
			recruit.setCatSpeId(trainTypeId);
			List<Map<String,Object>> doctorSpeList=resStatisticBiz.statisticDocCouByType(recruit, jointOrgFlowList, doctor,docTypeList);
			Map<String,Integer> orgJointDocSumMap = new HashMap<>();
			for(Map<String,Object> en:doctorSpeList){
				Object key= en.get("key");
				Object value= en.get("value");
				jointOrgDocCountMap.put(key, value);// Map 已协同基地的flow家伙是哪个spe作为key

				//计算每家基地的协同基地的人数总计
				for(ResJointOrg re:allJointOrgList){
					if(key.toString().contains(re.getJointOrgFlow())){
						Integer count = orgJointDocSumMap.get(re.getOrgFlow());
						if(count == null){
							count = 0;
						}
						count += Integer.parseInt(value.toString());
						orgJointDocSumMap.put(re.getOrgFlow(),count);
						continue;
					}
				}
			}
			model.addAttribute("orgJointDocSumMap", orgJointDocSumMap);
		}
		model.addAttribute("datas", datas);
		model.addAttribute("jointOrgDocCountMap", jointOrgDocCountMap);
		model.addAttribute("jointOrgListMap", jointOrgListMap);
		model.addAttribute("jointOrgSpeMap", jointOrgSpeMap);
		model.addAttribute("sysOrgList", sysOrgList);
		return "/jsres/global/statistics/statisticJointOrgInfoAcc";
	}

	@RequestMapping("/exportExcel")
	public void exportExcel(String sessionNumber,String trainTypeId,String orgLevel,String[] datas,HttpServletResponse response)throws Exception{
		List<SysOrg> sysOrgList=new ArrayList<SysOrg>();//存放所有的符合条件的sysOrg
		List<SysDict> typeSpeList=new ArrayList<SysDict>();//存放每个培训类别的专业
		Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
		Map<Object, Object> joingCountMap=new HashMap<Object,Object>();//保存每家基地的协同基地的每个专业的总数
		Map<Object, Object> orgSpeFlagMap=new HashMap<Object,Object>();//基地专业标志的的map
		Map<Object, Object> zongjiMap=new HashMap<Object, Object>();//统计小计
		List<String>orgFlowList=null;
		List<String>docTypeList=new ArrayList<String>();
		List<String>jointOrgFlowList=null;
		ResDoctor doctor=new ResDoctor();
		if(StringUtil.isNotBlank(trainTypeId)){
			typeSpeList=dictBiz.searchDictListByDictTypeId(trainTypeId);//每个培训类别对应的专业
		}
		SysOrg org=new SysOrg();
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(ss.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(ss.getOrgCityId());
		}
		if(StringUtil.isBlank(orgLevel)){
			sysOrgList=resJointOrgBiz.searchCouAndProList(org);//查询符合要求的基地
		}else{
			org.setOrgLevelId(orgLevel);
			sysOrgList=orgBiz.searchAllSysOrg(org);//查询符合要求的 基地
		}
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		recruit.setSessionNumber(sessionNumber);
		recruit.setCatSpeId(trainTypeId);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg o:sysOrgList){
				orgFlowList=new ArrayList<String>();
				jointOrgFlowList=new ArrayList<String>();
				ResOrgSpe resOrgSpe=new ResOrgSpe();
				resOrgSpe.setOrgFlow(o.getOrgFlow());
                resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
				List<ResOrgSpe> orgSpeList=resOrgSpeBiz.searchResOrgSpeList(resOrgSpe);
				if(orgSpeList!=null&&!orgSpeList.isEmpty()){//每家主基地的专业
					for(ResOrgSpe r:orgSpeList){
                        orgSpeFlagMap.put(o.getOrgFlow() + r.getSpeTypeId() + r.getSpeId(), com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
				orgFlowList.add(o.getOrgFlow());//查询每家基地及其协同基地的总数
				List<ResJointOrg>jointOrgList=resJointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());//查询每家基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					for(ResJointOrg resJointOrg:jointOrgList){
						jointOrgFlowList.add(resJointOrg.getJointOrgFlow());
						orgFlowList.add(resJointOrg.getJointOrgFlow());
					}
					List<Map<String, Object>>jointCountList=resStatisticBiz.statisticJointCount(recruit, jointOrgFlowList,doctor,docTypeList);
					if(jointCountList!=null&&!jointCountList.isEmpty()){
						for(Map<String,Object> en:jointCountList){
							Object key=o.getOrgFlow()+en.get("key");
							Object value= en.get("value");
							joingCountMap.put(key, value);
						}
					}
				}
				//每家基地每个专业的医师培训记录总数 
				List<Map<String,Object>> doctorCountList=resStatisticBiz.statisticJointCount(recruit,orgFlowList,doctor,docTypeList);
				if(doctorCountList!=null&&!doctorCountList.isEmpty()){
					for(Map<String,Object> en:doctorCountList){
						Object key=o.getOrgFlow()+en.get("key");
						Object value= en.get("value");
						totalCountMap.put(key, value);
					}
				}
			}
		}
		resStatisticBiz.export(sysOrgList, typeSpeList, trainTypeId, totalCountMap, zongjiMap, orgSpeFlagMap, joingCountMap, response);
	}
	@RequestMapping("/statisticsAppUser")
	public String statisticsAppUser(Model model,String orgFlow,String startTime,String endTime,String catSpeId,String sessionNumber,String graduate){
		Map<Object, Object> percentMap=new HashMap<Object, Object>(); //存有百分比的map
		Map<Object, Object> countMap=new HashMap<Object, Object>(); //存有具体数值的map
		List<ResJointOrg> resJointOrgs = null;
		List<String> timeGapMon=new ArrayList<String>();//存放横坐标
		SysUser currUser=GlobalContext.getCurrentUser();
		ResRec resRec=new ResRec();
		ResDoctorRecruit recruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(orgFlow)){
			recruit.setOrgFlow(orgFlow);
			resRec.setOrgFlow(orgFlow);
			resJointOrgs = resJointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
		}else{
			resRec.setOrgFlow(currUser.getOrgFlow());
			recruit.setOrgFlow(currUser.getOrgFlow());
			resJointOrgs = resJointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
		}
		if(CollectionUtils.isNotEmpty(resJointOrgs)){
			//协同基地
			recruit.setJointOrgFlow(recruit.getOrgFlow());
			recruit.setOrgFlow("");
		}
		if(StringUtil.isBlank(sessionNumber)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				sessionNumber=InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
			}
		}
		if(StringUtil.isBlank(startTime)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_reg_date"))&&StringUtil.isNotBlank(sessionNumber)){
				String endDate = Integer.parseInt(sessionNumber)-1+"-"+InitConfig.getSysCfg("res_reg_date");
				String[] date=DateUtil.addDate(endDate, 1).split("-");
				startTime = date[0]+"-"+date[1];
			}else{
				startTime="2015-09";//开始时间
			}
		}
		if(StringUtil.isBlank(endTime)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_reg_date"))&&StringUtil.isNotBlank(sessionNumber)){
//				String endDate = Integer.parseInt(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))+"-"+InitConfig.getSysCfg("res_reg_date");
//				String[] date=endDate.split("-");
//				endTime = date[0]+date[1]+date[2];
//			}else{
				endTime= DateUtil.getCurrMonth();//结束时间
			}
		}
		model.addAttribute("sessionNumber",sessionNumber);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		if(StringUtil.isNotBlank(sessionNumber)) {
			recruit.setSessionNumber(sessionNumber);
		}
		if(StringUtil.isNotBlank(catSpeId)){
			recruit.setCatSpeId(catSpeId);
		}
		List<String> delTypeList = new ArrayList<String>();
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.DoctorAuth.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
		List<Map<String, Object>> docCountByOrg = resStatisticBiz.statisticDocCountByOrg(recruit,null,graduate);//每家医院的人数总数
		List<Map<String, Object>> appCountByOrg = resStatisticBiz.statisticAppCountByOrg(recruit,resRec,endTime,startTime,delTypeList,graduate);//统计每家医院实际使用app的人数

		Object orgTotal=0;//每家基地的总数
		if(docCountByOrg!=null&&!docCountByOrg.isEmpty()){
			orgTotal=docCountByOrg.get(0).get("value");
		}
		Map<String,Object> appUseCountMap=new HashMap<>();

		for(Map<String, Object> en :appCountByOrg){
			appUseCountMap.put((String) en.get("key"),en.get("value"));
			Object key=en.get("key");
			Object value=en.get("value");
			percentMap.put(key, (Double.parseDouble(value+"")/Double.parseDouble(orgTotal+"")));
			countMap.put(key, value);
		}
		List<String> months= TimeUtil.getMonthsByTwoMonth(startTime,endTime);//算出两个时间相差的月份
		if(months!=null&&months.size()>0)
		{
			for(String m:months)
			{
				if(appUseCountMap.get(m)==null){//查询中没有这条记录
					Object key= m;
					Object value=0;
					percentMap.put(key, Double.parseDouble(value+""));
					countMap.put(key, value);
				}
			}
		}
		System.err.println(JSON.toJSONString(months));
		model.addAttribute("timeGapMon", months);
		model.addAttribute("percentMap", percentMap);
		model.addAttribute("countMap", countMap);
		return "/jsres/hospital/statistics/statisticsAppUser";
	}

	@RequestMapping("/statisticsAppUserAcc")
	public String statisticsAppUserAcc(Model model,String orgFlow,String startTime,String endTime,String catSpeId,String sessionNumber,String graduate){
		Map<Object, Object> percentMap=new HashMap<Object, Object>(); //存有百分比的map
		Map<Object, Object> countMap=new HashMap<Object, Object>(); //存有具体数值的map
		List<ResJointOrg> resJointOrgs = null;
		List<String> timeGapMon=new ArrayList<String>();//存放横坐标
		SysUser currUser=GlobalContext.getCurrentUser();
		ResRec resRec=new ResRec();
		ResDoctorRecruit recruit= new ResDoctorRecruit();
		if(StringUtil.isNotBlank(orgFlow)){
			recruit.setOrgFlow(orgFlow);
			resRec.setOrgFlow(orgFlow);
			resJointOrgs = resJointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
		}else{
			resRec.setOrgFlow(currUser.getOrgFlow());
			recruit.setOrgFlow(currUser.getOrgFlow());
			resJointOrgs = resJointOrgBiz.searchResJointByJointOrgFlow(currUser.getOrgFlow());
		}
		if(CollectionUtils.isNotEmpty(resJointOrgs)){
			//协同基地
			recruit.setJointOrgFlow(recruit.getOrgFlow());
			recruit.setOrgFlow("");
		}
		if(StringUtil.isBlank(sessionNumber)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				sessionNumber=InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
			}
		}
		if(StringUtil.isBlank(startTime)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_reg_date"))&&StringUtil.isNotBlank(sessionNumber)){
				String endDate = Integer.parseInt(sessionNumber)-1+"-"+InitConfig.getSysCfg("res_reg_date");
				String[] date=DateUtil.addDate(endDate, 1).split("-");
				startTime = date[0]+"-"+date[1];
			}else{
				startTime="2015-09";//开始时间
			}
		}
		if(StringUtil.isBlank(endTime)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_reg_date"))&&StringUtil.isNotBlank(sessionNumber)){
//				String endDate = Integer.parseInt(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))+"-"+InitConfig.getSysCfg("res_reg_date");
//				String[] date=endDate.split("-");
//				endTime = date[0]+date[1]+date[2];
//			}else{
				endTime= DateUtil.getCurrMonth();//结束时间
			}
		}
		model.addAttribute("sessionNumber",sessionNumber);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		if(StringUtil.isNotBlank(sessionNumber)) {
			recruit.setSessionNumber(sessionNumber);
		}
		if(StringUtil.isNotBlank(catSpeId)){
			recruit.setCatSpeId(catSpeId);
		}
		List<String> delTypeList = new ArrayList<String>();
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.DoctorAuth.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
		List<Map<String, Object>> docCountByOrg = resStatisticBiz.statisticDocCountByOrg(recruit,null,graduate);//每家医院的人数总数
		List<Map<String, Object>> appCountByOrg = resStatisticBiz.statisticAppCountByOrg(recruit,resRec,endTime,startTime,delTypeList,graduate);//统计每家医院实际使用app的人数

		Object orgTotal=0;//每家基地的总数
		if(docCountByOrg!=null&&!docCountByOrg.isEmpty()){
			orgTotal=docCountByOrg.get(0).get("value");
		}
		Map<String,Object> appUseCountMap=new HashMap<>();

		for(Map<String, Object> en :appCountByOrg){
			appUseCountMap.put((String) en.get("key"),en.get("value"));
			Object key=en.get("key");
			Object value=en.get("value");
			percentMap.put(key, (Double.parseDouble(value+"")/Double.parseDouble(orgTotal+"")));
			countMap.put(key, value);
		}
		List<String> months= TimeUtil.getMonthsByTwoMonth(startTime,endTime);//算出两个时间相差的月份
		if(months!=null&&months.size()>0)
		{
			for(String m:months)
			{
				if(appUseCountMap.get(m)==null){//查询中没有这条记录
					Object key= m;
					Object value=0;
					percentMap.put(key, Double.parseDouble(value+""));
					countMap.put(key, value);
				}
			}
		}
		System.err.println(JSON.toJSONString(months));
		model.addAttribute("timeGapMon", months);
		model.addAttribute("percentMap", percentMap);
		model.addAttribute("countMap", countMap);
		return "/jsres/hospital/statistics/statisticsAppUserAcc";
	}

	@RequestMapping("/statisticsNoAppUser")
	public String statisticsNoAppUser(Model model,String orgFlow,String catSpeId,Integer currentPage,HttpServletRequest request,String startDate,String endDate,String sessionNumber,String graduate){
		SysUser currUser = GlobalContext.getCurrentUser();
		List<ResJointOrg> resJointOrgs = null;
		if(StringUtil.isBlank(orgFlow)){
			orgFlow = currUser.getOrgFlow();
		}
		if(StringUtil.isNotEmpty(orgFlow)){
			resJointOrgs = resJointOrgBiz.searchResJointByJointOrgFlow(orgFlow);
		}
		ResRec resRec = new ResRec();
		resRec.setOrgFlow(orgFlow);
		ResDoctorRecruit recruit = new ResDoctorRecruit();
		recruit.setOrgFlow(orgFlow);
		if(StringUtil.isBlank(sessionNumber)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
				sessionNumber=InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
			}
		}else {
			recruit.setSessionNumber(sessionNumber);
		}
		if(StringUtil.isNotBlank(catSpeId)){
			recruit.setCatSpeId(catSpeId);
		}
		if(CollectionUtils.isNotEmpty(resJointOrgs)){
			//协同基地
			recruit.setJointOrgFlow(recruit.getOrgFlow());
			recruit.setOrgFlow("");
		}
		model.addAttribute("sessionNumber",sessionNumber);
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		List<String> delTypeList = new ArrayList<String>();
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.DoctorAuth.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<JsDoctorInfoExt> docInfoExtsList =resStatisticBiz.statisticNoAppUser(recruit, resRec, delTypeList,startDate,endDate,graduate);
		model.addAttribute("docInfoExtsList", docInfoExtsList);
		return "/jsres/hospital/statistics/noAppUsers";
	}
	@RequestMapping("/statisticsAppUserForOrg")
	public String statisticsAppUserForOrg(Model model,String startTime,String endTime,String catSpeId,String sessionNumber){
		catSpeId="DoctorTrainingSpe";
		List<SysOrg> sysOrgList =new ArrayList<SysOrg>();//所有国家基地和省级基地
		List<String> jointOrgFlowList=new ArrayList<String>();//存放所有的协同基地的orglist
		List<String> orgFlowList=new ArrayList<String>();//主基地的orglist
		Map<Object, Object> percentMap=new HashMap<Object,Object>();//存放百分比的map
		Map<Object, Object> jointPercentMap=new HashMap<Object,Object>();//xietong存放百分比的map
		Map<Object, Object> jointOrgListMap= new HashMap<Object, Object>();//每个符合添加的org的协同基地的map
		SysOrg org=new SysOrg();
		SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(ss.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(ss.getOrgCityId());
		}
		sysOrgList=resJointOrgBiz.searchCouAndProList(org);//省级基地和国家基地
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg s:sysOrgList ){//国家基地和省级基地的list
				List<ResJointOrg> jointOrgList=resJointOrgBiz.searchResJointByOrgFlow(s.getOrgFlow());//分别查出每个基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					jointOrgListMap.put(s.getOrgFlow(), jointOrgList);//以当前的国家基地标记key存放一个list   Map
					for(ResJointOrg re:jointOrgList){//循环协同基地
						jointOrgFlowList.add(re.getJointOrgFlow());
					}
				}
				orgFlowList.add(s.getOrgFlow());//存放主基地的orgFlow
			}
		}
//		if(StringUtil.isBlank(startTime)){
//			startTime="201509";
//		}if(StringUtil.isBlank(endTime)){
//			endTime=DateUtil.getCurrDate2().substring(0,6);
//		}
		String month = DateUtil.getCurrMonth();
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		if(StringUtil.isBlank(sessionNumber)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
				sessionNumber=InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
			}
		}else {
			recruit.setSessionNumber(sessionNumber);
		}
		model.addAttribute("sessionNumber",sessionNumber);
		if(StringUtil.isNotBlank(catSpeId)){
			recruit.setCatSpeId(catSpeId);
		}
		List<String> delTypeList = new ArrayList<String>();
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.DoctorAuth.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		List<Map<String, Object>> docCountByOrg = resStatisticBiz.statisticDocCountByOrgForTime(null, orgFlowList, startTime, endTime,recruit);//主基地每家医院的人数总数
		List<Map<String, Object>> appCountByOrg = resStatisticBiz.statisticRealAppCount(delTypeList,recruit,endTime, startTime, orgFlowList, null,month);//统计主基地每家医院实际使用app的人数
		DecimalFormat df = new DecimalFormat("0.00%");
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg s:sysOrgList){
				double total=0;double real=0;
				for(Map<String, Object> en:docCountByOrg){
					if(s.getOrgFlow().equals(en.get("key"))){
						total=Double.parseDouble(en.get("value")+"");
					}
				}
				for(Map<String, Object> en:appCountByOrg){
					if(s.getOrgFlow().equals(en.get("key"))){
						real=Double.parseDouble(en.get("value")+"");
					}
				}
				double average=real/total;
				if(total==0||average==0||real==0){
					percentMap.put(s.getOrgFlow(),0+"%");
				}else{
					percentMap.put(s.getOrgFlow(),df.format(average));
				}
			}
		}
		if(jointOrgFlowList!=null &&!jointOrgFlowList.isEmpty()){//所有的协同基地
            List<Map<String, Object>> jointDocCountByOrg = resStatisticBiz.statisticDocCountByOrgForTime(com.pinde.core.common.GlobalConstant.FLAG_Y, orgFlowList, startTime, endTime, recruit);//协同基地基地每家医院的人数总数
            List<Map<String, Object>> JointAppCountByOrg = resStatisticBiz.statisticRealAppCount(delTypeList, recruit, endTime, startTime, orgFlowList, com.pinde.core.common.GlobalConstant.FLAG_Y, month);//统计协同基地每家医院实际使用app的人数
			for(String s:jointOrgFlowList){//循环list
				double t=0;double r=0;
				for(Map<String, Object> en:jointDocCountByOrg){
					if(s.equals(en.get("key"))){
						t=Double.parseDouble(en.get("value")+"");
					}
				}
				for(Map<String, Object> en:JointAppCountByOrg){
					if(s.equals(en.get("key"))){
						r=Double.parseDouble(en.get("value")+"");
					}
				}
				double a=r/t;
				if(t==0||a==0){
					jointPercentMap.put(s,0+"%");
				}else{
					jointPercentMap.put(s,df.format(a));
				}
			}
		}
		model.addAttribute("sysOrgList", sysOrgList);
		model.addAttribute("percentMap", percentMap);
		model.addAttribute("jointPercentMap", jointPercentMap);
		model.addAttribute("jointOrgListMap", jointOrgListMap);
		return "/jsres/global/statistics/statisticsAppUserForOrg";
	}

	@RequestMapping("/statisticsAppUserForOrgAcc")
	public String statisticsAppUserForOrgAcc(Model model,String startTime,String endTime,String catSpeId,String sessionNumber){
		catSpeId="AssiGeneral";
		List<SysOrg> sysOrgList =new ArrayList<SysOrg>();//所有国家基地和省级基地
		List<String> jointOrgFlowList=new ArrayList<String>();//存放所有的协同基地的orglist
		List<String> orgFlowList=new ArrayList<String>();//主基地的orglist
		Map<Object, Object> percentMap=new HashMap<Object,Object>();//存放百分比的map
		Map<Object, Object> jointPercentMap=new HashMap<Object,Object>();//xietong存放百分比的map
		Map<Object, Object> jointOrgListMap= new HashMap<Object, Object>();//每个符合添加的org的协同基地的map
		SysOrg org=new SysOrg();
		SysOrg ss=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(ss.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(ss.getOrgCityId());
		}
		sysOrgList=resJointOrgBiz.searchCouAndProList(org);//省级基地和国家基地
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg s:sysOrgList ){//国家基地和省级基地的list
				List<ResJointOrg> jointOrgList=resJointOrgBiz.searchResJointByOrgFlow(s.getOrgFlow());//分别查出每个基地的协同基地
				if(jointOrgList!=null&&!jointOrgList.isEmpty()){
					jointOrgListMap.put(s.getOrgFlow(), jointOrgList);//以当前的国家基地标记key存放一个list   Map
					for(ResJointOrg re:jointOrgList){//循环协同基地
						jointOrgFlowList.add(re.getJointOrgFlow());
					}
				}
				orgFlowList.add(s.getOrgFlow());//存放主基地的orgFlow
			}
		}
//		if(StringUtil.isBlank(startTime)){
//			startTime="201509";
//		}if(StringUtil.isBlank(endTime)){
//			endTime=DateUtil.getCurrDate2().substring(0,6);
//		}
		String month = DateUtil.getCurrMonth();
		ResDoctorRecruit recruit=new ResDoctorRecruit();
		if(StringUtil.isBlank(sessionNumber)){
			if(StringUtil.isNotBlank(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"))){
				recruit.setSessionNumber(InitConfig.getSysCfg("jsres_doctorCount_sessionNumber"));
				sessionNumber=InitConfig.getSysCfg("jsres_doctorCount_sessionNumber");
			}
		}else {
			recruit.setSessionNumber(sessionNumber);
		}
		model.addAttribute("sessionNumber",sessionNumber);
		if(StringUtil.isNotBlank(catSpeId)){
			recruit.setCatSpeId(catSpeId);
		}
		List<String> delTypeList = new ArrayList<String>();
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.DoctorAuth.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
        delTypeList.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
        recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
		List<Map<String, Object>> docCountByOrg = resStatisticBiz.statisticDocCountByOrgForTime(null, orgFlowList, startTime, endTime,recruit);//主基地每家医院的人数总数
		List<Map<String, Object>> appCountByOrg = resStatisticBiz.statisticRealAppCount(delTypeList,recruit,endTime, startTime, orgFlowList, null,month);//统计主基地每家医院实际使用app的人数
		DecimalFormat df = new DecimalFormat("0.00%");
		if(sysOrgList!=null&&!sysOrgList.isEmpty()){
			for(SysOrg s:sysOrgList){
				double total=0;double real=0;
				for(Map<String, Object> en:docCountByOrg){
					if(s.getOrgFlow().equals(en.get("key"))){
						total=Double.parseDouble(en.get("value")+"");
					}
				}
				for(Map<String, Object> en:appCountByOrg){
					if(s.getOrgFlow().equals(en.get("key"))){
						real=Double.parseDouble(en.get("value")+"");
					}
				}
				double average=real/total;
				if(total==0||average==0||real==0){
					percentMap.put(s.getOrgFlow(),0+"%");
				}else{
					percentMap.put(s.getOrgFlow(),df.format(average));
				}
			}
		}
		if(jointOrgFlowList!=null &&!jointOrgFlowList.isEmpty()){//所有的协同基地
            List<Map<String, Object>> jointDocCountByOrg = resStatisticBiz.statisticDocCountByOrgForTime(com.pinde.core.common.GlobalConstant.FLAG_Y, orgFlowList, startTime, endTime, recruit);//协同基地基地每家医院的人数总数
            List<Map<String, Object>> JointAppCountByOrg = resStatisticBiz.statisticRealAppCount(delTypeList, recruit, endTime, startTime, orgFlowList, com.pinde.core.common.GlobalConstant.FLAG_Y, month);//统计协同基地每家医院实际使用app的人数
			for(String s:jointOrgFlowList){//循环list
				double t=0;double r=0;
				for(Map<String, Object> en:jointDocCountByOrg){
					if(s.equals(en.get("key"))){
						t=Double.parseDouble(en.get("value")+"");
					}
				}
				for(Map<String, Object> en:JointAppCountByOrg){
					if(s.equals(en.get("key"))){
						r=Double.parseDouble(en.get("value")+"");
					}
				}
				double a=r/t;
				if(t==0||a==0){
					jointPercentMap.put(s,0+"%");
				}else{
					jointPercentMap.put(s,df.format(a));
				}
			}
		}
		model.addAttribute("sysOrgList", sysOrgList);
		model.addAttribute("percentMap", percentMap);
		model.addAttribute("jointPercentMap", jointPercentMap);
		model.addAttribute("jointOrgListMap", jointOrgListMap);
		return "/jsres/global/statistics/statisticsAppUserForOrgAcc";
	}

	@RequestMapping("statistics2App")
	public String statistics2App(String endMonth,Model model) throws ParseException {
		String startMonth = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date nowDate = new Date();
		Calendar c = Calendar.getInstance();
		//1个月前的月份
		c.setTime(sdf.parse(sdf.format(nowDate)));
		c.add(Calendar.MONTH, -1);
		String lastMonth = sdf.format(c.getTime());
		model.addAttribute("lastMonth",lastMonth);
		if(StringUtil.isBlank(endMonth)){
			endMonth = lastMonth;
		}
		//12个月前的月份
		c.setTime(sdf.parse(endMonth));
		c.add(Calendar.MONTH, -11);
		startMonth = sdf.format(c.getTime());
		//2个月前的月份
		Calendar c2 = Calendar.getInstance();
		c2.setTime(sdf.parse(endMonth));
		c2.add(Calendar.MONTH, -1);
		String startMonth2 = sdf.format(c2.getTime());
		model.addAttribute("startMonth2",startMonth2);
		model.addAttribute("endMonth2",endMonth);

		List<String> months = new ArrayList<>();

		Calendar min = Calendar.getInstance();
		Calendar max = Calendar.getInstance();

		min.setTime(sdf.parse(startMonth));
		min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

		max.setTime(sdf.parse(endMonth));
		max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

		Calendar curr = min;
		while (curr.before(max)) {
			months.add(sdf.format(curr.getTime()));
			curr.add(Calendar.MONTH, 1);
		}
		model.addAttribute("months",months);

		//图1
		List<SysMonthlyStatistics> statisticsList = sysMonthlyStatisticsBiz.searchStatistics(startMonth,endMonth,null);

		Map<String,Object> resultMapAll = new HashMap<>();
		Map<String,Object> resultMapGraduate = new HashMap<>();
		Map<String,Object> resultMapNotGraduate = new HashMap<>();
		int total1 = 0;
		for(String month:months){//初始化所有MAP
			resultMapAll.put(month,0);
			resultMapGraduate.put(month,0);
			resultMapNotGraduate.put(month,0);
		}

		if(statisticsList!=null&&statisticsList.size()>0){
			for(SysMonthlyStatistics monthlyStatistics:statisticsList){
				String dateMonth = monthlyStatistics.getDateMonth();
				if("All".equals(monthlyStatistics.getDoctorTypeId())){
					int allNum = monthlyStatistics.getAllNum();
					resultMapAll.put(dateMonth, (int)resultMapAll.get(dateMonth)+allNum);
					total1+=allNum;
				}
				if("Graduate".equals(monthlyStatistics.getDoctorTypeId())){
					int allNum = monthlyStatistics.getAllNum();
					resultMapGraduate.put(dateMonth, (int)resultMapGraduate.get(dateMonth)+allNum);
				}
				if("NotGraduate".equals(monthlyStatistics.getDoctorTypeId())){
					int allNum = monthlyStatistics.getAllNum();
					resultMapNotGraduate.put(dateMonth, (int)resultMapNotGraduate.get(dateMonth)+allNum);
				}
			}
		}
		model.addAttribute("resultMapAll",resultMapAll);
		model.addAttribute("resultMapGraduate",resultMapGraduate);
		model.addAttribute("resultMapNotGraduate",resultMapNotGraduate);
		model.addAttribute("total1",total1);

		//图2&图3&图4&图5
		List<SysMonthlyStatistics> statisticsList2 = statisticsList;
		Map<String,Object> resultMapOrg0 = new HashMap<>();
		Map<String,Object> resultMapOrgGraduate0 = new HashMap<>();
		Map<String,Object> resultMapOrgNotGraduate0 = new HashMap<>();
		Map<String,Object> resultMapOrgAvg0 = new HashMap<>();
		Map<String,Object> resultMapOrgAvgGraduate0 = new HashMap<>();
		Map<String,Object> resultMapOrgAvgNotGraduate0 = new HashMap<>();
		Map<String,Object> resultMapOrgPrev0 = new HashMap<>();
		Map<String,Object> resultMapOrgPrevPrev0 = new HashMap<>();
		Map<String,Object> resultMapOrgPrevAvg0 = new HashMap<>();
		int prevAllNum = 0;//前一个月填写总量
		if(statisticsList2!=null&&statisticsList2.size()>0) {
			for (SysMonthlyStatistics monthlyStatistics : statisticsList2) {
				if("All".equals(monthlyStatistics.getDoctorTypeId())){//总量
					String orgFlow = monthlyStatistics.getOrgFlow();
					int allNum = monthlyStatistics.getAllNum();
					int avgNum = monthlyStatistics.getAvgNum();
					if(resultMapOrg0.get(orgFlow)==null){
						resultMapOrg0.put(orgFlow,allNum);
					}else {
						resultMapOrg0.put(orgFlow, (int)resultMapOrg0.get(orgFlow)+allNum);
					}

					if(resultMapOrgAvg0.get(orgFlow)==null){
						resultMapOrgAvg0.put(orgFlow,avgNum);
					}else {
						resultMapOrgAvg0.put(orgFlow, (int)resultMapOrgAvg0.get(orgFlow)+avgNum);
					}

					if(startMonth2.equals(monthlyStatistics.getDateMonth())){
						if(resultMapOrgPrevPrev0.get(orgFlow)==null){
							resultMapOrgPrevPrev0.put(orgFlow,allNum);
						}else {
							resultMapOrgPrevPrev0.put(orgFlow, (int)resultMapOrgPrevPrev0.get(orgFlow)+allNum);
						}
					}

					if(endMonth.equals(monthlyStatistics.getDateMonth())){//前一个月
						if(resultMapOrgPrev0.get(orgFlow)==null){
							resultMapOrgPrev0.put(orgFlow,allNum);
						}else {
							resultMapOrgPrev0.put(orgFlow, (int)resultMapOrgPrev0.get(orgFlow)+allNum);
						}

						if(resultMapOrgPrevAvg0.get(orgFlow)==null){
							resultMapOrgPrevAvg0.put(orgFlow,avgNum);
						}else {
							resultMapOrgPrevAvg0.put(orgFlow, (int)resultMapOrgPrevAvg0.get(orgFlow)+avgNum);
						}
						prevAllNum+=allNum;
					}
				}

				if("Graduate".equals(monthlyStatistics.getDoctorTypeId())){//专硕
					String orgFlow = monthlyStatistics.getOrgFlow();
					int allNum = monthlyStatistics.getAllNum();
					int avgNum = monthlyStatistics.getAvgNum();
					if(resultMapOrgGraduate0.get(orgFlow)==null){
						resultMapOrgGraduate0.put(orgFlow,allNum);
					}else {
						resultMapOrgGraduate0.put(orgFlow, (int)resultMapOrgGraduate0.get(orgFlow)+allNum);
					}

					if(resultMapOrgAvgGraduate0.get(orgFlow)==null){
						resultMapOrgAvgGraduate0.put(orgFlow,avgNum);
					}else {
						resultMapOrgAvgGraduate0.put(orgFlow, (int)resultMapOrgAvgGraduate0.get(orgFlow)+avgNum);
					}
				}

				if("NotGraduate".equals(monthlyStatistics.getDoctorTypeId())){//非专硕
					String orgFlow = monthlyStatistics.getOrgFlow();
					int allNum = monthlyStatistics.getAllNum();
					int avgNum = monthlyStatistics.getAvgNum();
					if(resultMapOrgNotGraduate0.get(orgFlow)==null){
						resultMapOrgNotGraduate0.put(orgFlow,allNum);
					}else {
						resultMapOrgNotGraduate0.put(orgFlow, (int)resultMapOrgNotGraduate0.get(orgFlow)+allNum);
					}

					if(resultMapOrgAvgNotGraduate0.get(orgFlow)==null){
						resultMapOrgAvgNotGraduate0.put(orgFlow,avgNum);
					}else {
						resultMapOrgAvgNotGraduate0.put(orgFlow, (int)resultMapOrgAvgNotGraduate0.get(orgFlow)+avgNum);
					}
				}
			}
		}

		Map<String,Object> nationalOrgMap0 = new HashMap<>();//所有协同基地关系
		List<ResJointOrg> resJointOrgList= resJointOrgBiz.searchJointOrgAll();
		if(resJointOrgList!=null&&resJointOrgList.size()>0){
			for(ResJointOrg resJointOrg:resJointOrgList){
				String orgFlow = resJointOrg.getOrgFlow();
				String jointOrgFlow = resJointOrg.getJointOrgFlow();
				if(nationalOrgMap0.get(orgFlow)==null){
					List<String> jointOrgFlowList = new ArrayList<>();
					jointOrgFlowList.add(jointOrgFlow);
					nationalOrgMap0.put(orgFlow,jointOrgFlowList);
				}else{
					List<String> jointOrgFlowList = (List<String>)nationalOrgMap0.get(orgFlow);
					jointOrgFlowList.add(jointOrgFlow);
					nationalOrgMap0.put(orgFlow,jointOrgFlowList);
				}
			}
		}

		Map<String,Object> nationalOrgMap = new HashMap<>();//国家基地MAP
		SysOrg sysOrg=new SysOrg();
        sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		sysOrg.setOrgProvId("320000");
		//所有国家基地
		List<SysOrg> sysOrgs=orgBiz.searchAllSysOrg(sysOrg);
		if(sysOrgs!=null&&sysOrgs.size()>0){
			for(SysOrg org:sysOrgs){
				nationalOrgMap.put(org.getOrgFlow(),nationalOrgMap0.get(org.getOrgFlow()));
			}
		}

		//国家基地名称MAP
		Map<String,String> orgNameMap = new HashMap<>();
		for(SysOrg org:sysOrgs){
			String orgFlow = org.getOrgFlow();
			String orgName = org.getOrgName();
			orgNameMap.put(orgFlow,orgName);
		}
		model.addAttribute("orgNameMap",orgNameMap);

		Map<String,Integer> resultMapOrg = new HashMap<>();//国家基地总量数据统计
		Map<String,Integer> resultMapOrgGraduate = new HashMap<>();//国家基地专硕总量数据统计
		Map<String,Integer> resultMapOrgNotGraduate = new HashMap<>();//国家基地非专硕总量数据统计
		Map<String,Integer> resultMapOrgAvg = new HashMap<>();//国家基地平均数据统计
		Map<String,Integer> resultMapOrgAvgGraduate = new HashMap<>();//国家基地专硕平均数据统计
		Map<String,Integer> resultMapOrgAvgNotGraduate = new HashMap<>();//国家基地非专硕平均数据统计
		Map<String,Integer> resultMapOrgPrev = new HashMap<>();//国家基地前月总量数据统计
		Map<String,Integer> resultMapOrgPrevPrev = new HashMap<>();//国家基地前前月总量数据统计
		Map<String,Integer> resultMapOrgPrevAvg = new HashMap<>();//国家基地前前月各基地平均数据统计
		int orgPrevAvg = prevAllNum/sysOrgs.size();//国家基地前前月所有基地总平均数据
		model.addAttribute("orgPrevAvg",orgPrevAvg);

		for(String key:nationalOrgMap.keySet()){
			String orgFlow = key;
			List<String> jointOrgFlowList = (List<String>)nationalOrgMap.get(key);
			if(resultMapOrg.get(key)==null){//总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrg0.get(jointOrgFlow)==null?0:(int)resultMapOrg0.get(jointOrgFlow);
					}
				}
				resultMapOrg.put(key,(resultMapOrg0.get(orgFlow)==null?0:(int)resultMapOrg0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrg0.get(jointOrgFlow)==null?0:(int)resultMapOrg0.get(jointOrgFlow);
					}
				}
				resultMapOrg.put(key,resultMapOrg.get(key)+jointAllNum);
			}

			if(resultMapOrgGraduate.get(key)==null){//专硕总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgGraduate.put(key,(resultMapOrgGraduate0.get(orgFlow)==null?0:(int)resultMapOrgGraduate0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgGraduate.put(key,resultMapOrgGraduate.get(key)+jointAllNum);
			}

			if(resultMapOrgNotGraduate.get(key)==null){//非专硕总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgNotGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgNotGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgNotGraduate.put(key,(resultMapOrgNotGraduate0.get(orgFlow)==null?0:(int)resultMapOrgNotGraduate0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgNotGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgNotGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgNotGraduate.put(key,resultMapOrgNotGraduate.get(key)+jointAllNum);
			}

			if(resultMapOrgAvg.get(key)==null){//平均量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvg0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvg0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvg.put(key,(resultMapOrgAvg0.get(orgFlow)==null?0:(int)resultMapOrgAvg0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvg0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvg0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvg.put(key,resultMapOrgAvg.get(key)+jointAllNum);
			}

			if(resultMapOrgAvgGraduate.get(key)==null){//专硕平均量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvgGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvgGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvgGraduate.put(key,(resultMapOrgAvgGraduate0.get(orgFlow)==null?0:(int)resultMapOrgAvgGraduate0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvgGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvgGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvgGraduate.put(key,resultMapOrgAvgGraduate.get(key)+jointAllNum);
			}

			if(resultMapOrgAvgNotGraduate.get(key)==null){//非专硕平均量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvgNotGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvgNotGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvgNotGraduate.put(key,(resultMapOrgAvgNotGraduate0.get(orgFlow)==null?0:(int)resultMapOrgAvgNotGraduate0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgAvgNotGraduate0.get(jointOrgFlow)==null?0:(int)resultMapOrgAvgNotGraduate0.get(jointOrgFlow);
					}
				}
				resultMapOrgAvgNotGraduate.put(key,resultMapOrgAvgNotGraduate.get(key)+jointAllNum);
			}

			if(resultMapOrgPrev.get(key)==null){//前月总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrev0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrev0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrev.put(key,(resultMapOrgPrev0.get(orgFlow)==null?0:(int)resultMapOrgPrev0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrev0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrev0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrev.put(key,resultMapOrgPrev.get(key)+jointAllNum);
			}

			if(resultMapOrgPrevPrev.get(key)==null){//前前月总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevPrev0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevPrev0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevPrev.put(key,(resultMapOrgPrevPrev0.get(orgFlow)==null?0:(int)resultMapOrgPrevPrev0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevPrev0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevPrev0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevPrev.put(key,resultMapOrgPrevPrev.get(key)+jointAllNum);
			}

			if(resultMapOrgPrevAvg.get(key)==null){//前月平均量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevAvg0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevAvg0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevAvg.put(key,(resultMapOrgPrevAvg0.get(orgFlow)==null?0:(int)resultMapOrgPrevAvg0.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevAvg0.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevAvg0.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevAvg.put(key,resultMapOrgPrevAvg.get(key)+jointAllNum);
			}


		}
		Map<String, Integer> resultMapTree = new TreeMap<String, Integer>(resultMapOrg);
		Map<String, Integer> resultMapGraduateTree = new TreeMap<String, Integer>(resultMapOrgGraduate);
		Map<String, Integer> resultMapNotGraduateTree = new TreeMap<String, Integer>(resultMapOrgNotGraduate);
		Map<String, Integer> resultMapAvgTree = new TreeMap<String, Integer>(resultMapOrgAvg);
		Map<String, Integer> resultMapOrgPrevTree = new TreeMap<String, Integer>(resultMapOrgPrev);
		Map<String, Integer> resultMapOrgPrevPrevTree = new TreeMap<String, Integer>(resultMapOrgPrevPrev);
		Map<String, Integer> resultMapOrgPrevAvgTree = new TreeMap<String, Integer>(resultMapOrgPrevAvg);
		model.addAttribute("resultMapOrgPrevAvgTree",resultMapOrgPrevAvgTree);
		// 降序比较器
		Comparator<Map.Entry<String, Integer>> valueComparator = new Comparator<Map.Entry<String,Integer>>() {
			@Override
			public int compare(Map.Entry<String, Integer> o1,
							   Map.Entry<String, Integer> o2) {
				return o2.getValue()-o1.getValue();
			}
		};

		// map转换成list进行排序
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String,Integer>>(resultMapTree.entrySet());
		List<Map.Entry<String, Integer>> list2 = new ArrayList<Map.Entry<String,Integer>>(resultMapAvgTree.entrySet());
		List<Map.Entry<String, Integer>> list3 = new ArrayList<Map.Entry<String,Integer>>(resultMapOrgPrevTree.entrySet());
		List<Map.Entry<String, Integer>> list4 = new ArrayList<Map.Entry<String,Integer>>(resultMapOrgPrevPrevTree.entrySet());
		List<Map.Entry<String, Integer>> list5 = new ArrayList<Map.Entry<String,Integer>>(resultMapOrgPrevAvgTree.entrySet());

		// 排序
		Collections.sort(list,valueComparator);
		Collections.sort(list2,valueComparator);
		List<Map.Entry<String, Integer>> resultMapOrgList = list.subList(0, 10);
		Map<String,Object> graduateMap = new HashMap<>();
		for(Map.Entry<String, Integer> map:resultMapOrgList){
			String orgFlow = map.getKey();
			Integer num = resultMapGraduateTree.get(orgFlow);
			graduateMap.put(orgFlow,num);
		}
		Map<String,Object> notGraduateMap = new HashMap<>();
		for(Map.Entry<String, Integer> map:resultMapOrgList){
			String orgFlow = map.getKey();
			Integer num = resultMapNotGraduateTree.get(orgFlow);
			notGraduateMap.put(orgFlow,num);
		}
		List<Map.Entry<String, Integer>> resultMapOrgList2 = list2.subList(0, 10);
		Map<String,Object> avgGraduateMap = new HashMap<>();
		for(Map.Entry<String, Integer> map:resultMapOrgList2){
			String orgFlow = map.getKey();
			Integer num = resultMapOrgAvgGraduate.get(orgFlow);
			avgGraduateMap.put(orgFlow,num);
		}
		Map<String,Object> avgNotGraduateMap = new HashMap<>();
		for(Map.Entry<String, Integer> map:resultMapOrgList2){
			String orgFlow = map.getKey();
			Integer num = resultMapOrgAvgNotGraduate.get(orgFlow);
			avgNotGraduateMap.put(orgFlow,num);
		}
		model.addAttribute("resultMapOrgList",resultMapOrgList);
		model.addAttribute("graduateMap",graduateMap);
		model.addAttribute("notGraduateMap",notGraduateMap);
		model.addAttribute("resultMapOrgList2",resultMapOrgList2);
		model.addAttribute("avgGraduateMap",avgGraduateMap);
		model.addAttribute("avgNotGraduateMap",avgNotGraduateMap);
		model.addAttribute("list2",list2);//不排序和截取直接传前台
		model.addAttribute("list3",list3);//不排序和截取直接传前台
		model.addAttribute("list4",list4);//不排序和截取直接传前台
		model.addAttribute("list5",list5);//不排序和截取直接传前台
		//图6
		List<SysMonthlyAppStatistics> appStatisticsList = sysMonthlyStatisticsBiz.searchAppStatistics(startMonth,endMonth,null);
		Map<String,Object> resultMapOrgPrev0App = new HashMap<>();
		Map<String,Object> resultMapOrgPrev0AppUser = new HashMap<>();
		Map<String,Object> resultMapOrgPrevPrev0App = new HashMap<>();
		Map<String,Object> resultMapOrgPrevPrev0AppUser = new HashMap<>();
		if(statisticsList!=null&&statisticsList.size()>0) {
			for (SysMonthlyAppStatistics monthlyStatistics : appStatisticsList) {
				if("All".equals(monthlyStatistics.getDoctorTypeId())){//总人数
					String orgFlow = monthlyStatistics.getOrgFlow();
					int allNum = monthlyStatistics.getAllNum();
					int userNum = monthlyStatistics.getUserNum();
					if(startMonth2.equals(monthlyStatistics.getDateMonth())){
						if(resultMapOrgPrevPrev0App.get(orgFlow)==null){
							resultMapOrgPrevPrev0App.put(orgFlow,allNum);
						}else {
							resultMapOrgPrevPrev0App.put(orgFlow, (int)resultMapOrgPrevPrev0App.get(orgFlow)+allNum);
						}

						if(resultMapOrgPrevPrev0AppUser.get(orgFlow)==null){
							resultMapOrgPrevPrev0AppUser.put(orgFlow,userNum);
						}else {
							resultMapOrgPrevPrev0AppUser.put(orgFlow, (int)resultMapOrgPrevPrev0AppUser.get(orgFlow)+userNum);
						}
					}
					if(endMonth.equals(monthlyStatistics.getDateMonth())){//前一个月总人数
						if(resultMapOrgPrev0App.get(orgFlow)==null){
							resultMapOrgPrev0App.put(orgFlow,allNum);
						}else {
							resultMapOrgPrev0App.put(orgFlow, (int)resultMapOrgPrev0App.get(orgFlow)+allNum);
						}

						if(resultMapOrgPrev0AppUser.get(orgFlow)==null){
							resultMapOrgPrev0AppUser.put(orgFlow,userNum);
						}else {
							resultMapOrgPrev0AppUser.put(orgFlow, (int)resultMapOrgPrev0AppUser.get(orgFlow)+userNum);
						}
					}
				}
			}
		}

		Map<String,Integer> resultMapOrgPrevApp = new HashMap<>();//国家基地前月总人数数据统计
		Map<String,Integer> resultMapOrgPrevAppUser = new HashMap<>();//使用人数
		Map<String,Integer> resultMapOrgPrevPrevApp = new HashMap<>();//国家基地前前月总人数数据统计
		Map<String,Integer> resultMapOrgPrevPrevAppUser = new HashMap<>();//使用人数

		for(String key:nationalOrgMap.keySet()){
			String orgFlow = key;
			List<String> jointOrgFlowList = (List<String>)nationalOrgMap.get(key);
			if(resultMapOrgPrevApp.get(key)==null){//前月总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrev0App.get(jointOrgFlow)==null?0:(int)resultMapOrgPrev0App.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevApp.put(key,(resultMapOrgPrev0App.get(orgFlow)==null?0:(int)resultMapOrgPrev0App.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrev0App.get(jointOrgFlow)==null?0:(int)resultMapOrgPrev0App.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevApp.put(key,resultMapOrgPrevApp.get(key)+jointAllNum);
			}

			if(resultMapOrgPrevAppUser.get(key)==null){//前月使用人数
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrev0AppUser.get(jointOrgFlow)==null?0:(int)resultMapOrgPrev0AppUser.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevAppUser.put(key,(resultMapOrgPrev0AppUser.get(orgFlow)==null?0:(int)resultMapOrgPrev0AppUser.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrev0AppUser.get(jointOrgFlow)==null?0:(int)resultMapOrgPrev0AppUser.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevAppUser.put(key,resultMapOrgPrevAppUser.get(key)+jointAllNum);
			}

			if(resultMapOrgPrevPrevApp.get(key)==null){//前前月总量
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevPrev0App.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevPrev0App.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevPrevApp.put(key,(resultMapOrgPrevPrev0App.get(orgFlow)==null?0:(int)resultMapOrgPrevPrev0App.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevPrev0App.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevPrev0App.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevPrevApp.put(key,resultMapOrgPrevPrevApp.get(key)+jointAllNum);
			}

			if(resultMapOrgPrevPrevAppUser.get(key)==null){//前前月使用人数
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevPrev0AppUser.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevPrev0AppUser.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevPrevAppUser.put(key,(resultMapOrgPrevPrev0AppUser.get(orgFlow)==null?0:(int)resultMapOrgPrevPrev0AppUser.get(orgFlow))+jointAllNum);
			}else{
				int jointAllNum = 0;
				if(jointOrgFlowList!=null&&jointOrgFlowList.size()>0){
					for(String jointOrgFlow:jointOrgFlowList){
						jointAllNum+=resultMapOrgPrevPrev0AppUser.get(jointOrgFlow)==null?0:(int)resultMapOrgPrevPrev0AppUser.get(jointOrgFlow);
					}
				}
				resultMapOrgPrevPrevAppUser.put(key,resultMapOrgPrevPrevAppUser.get(key)+jointAllNum);
			}
		}

		Map<String,Object> resultPrevMap = new HashMap<>();
		Map<String,Object> resultPrevPrevMap = new HashMap<>();
		for(String key:resultMapOrgPrevApp.keySet()){
			int userNum = resultMapOrgPrevAppUser.get(key);
			int allNum = resultMapOrgPrevApp.get(key);
			DecimalFormat df = new DecimalFormat("0");
			String result;
			if(allNum==0){
				result = "0";
			}else {
				result = df.format((double)userNum*100/allNum);
			}

			resultPrevMap.put(key,result);
		}
		for(String key:resultMapOrgPrevPrevApp.keySet()){
			int userNum = resultMapOrgPrevPrevAppUser.get(key);
			int allNum = resultMapOrgPrevPrevApp.get(key);
			DecimalFormat df = new DecimalFormat("0");
			String result;
			if(allNum==0){
				result = "0";
			}else {
				result = df.format((double)userNum*100/allNum);
			}
			resultPrevPrevMap.put(key,result);
		}
		model.addAttribute("resultPrevMap",resultPrevMap);
		model.addAttribute("resultPrevPrevMap",resultPrevPrevMap);
		return "/jsres/global/statistics/statistics2App";
	}

	@RequestMapping("/searchTeachTrainMain")
	public String searchTeachTrainMain(Model model,String roleFlag){
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			orgs.add(s);
		}else {
			org.setOrgProvId(s.getOrgProvId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
		}
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/global/statistics/searchTeachTrainMain";
	}

	@RequestMapping("/auditTeacherApplicationMain")
	public String auditTeacherApplicationMain(Model model, String roleFlag, String deptFlow){
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("deptFlow", deptFlow);
		return "jsres/global/statistics/auditTeacherApplicationMain";
	}

	@RequestMapping("/searchTeachTrain")
	public String searchTeachTrain(Model model,ResTeacherTraining teacherTraining,HttpServletRequest request,Integer currentPage,String roleFlag,String dataFlag){
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			teacherTraining.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			orgs.add(s);
		}else {
			org.setOrgProvId(s.getOrgProvId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
		}
		model.addAttribute("orgs", orgs);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResTeacherTraining> teacherTrainingList =resStatisticBiz.searchTeacherInfo2(teacherTraining,dataFlag);
		model.addAttribute("teacherTrainingList", teacherTrainingList);
		model.addAttribute("roleFlag", roleFlag);
		return "/jsres/global/statistics/searchTeachTrainList";
	}

	@RequestMapping("/auditTeacherApplication")
	public String auditTeacherApplication(Model model,ResTeacherTraining teacherTraining,HttpServletRequest request,Integer currentPage, String roleFlag, String dataFlag){
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			teacherTraining.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResTeacherTraining> teacherTrainingList =resStatisticBiz.searchTeacherInfo3(teacherTraining,dataFlag);
		model.addAttribute("teacherTrainingList", teacherTrainingList);
		model.addAttribute("roleFlag", roleFlag);
		return "/jsres/global/statistics/auditTeacherApplicationList";
	}

	@RequestMapping("/auditDetails")
	public String auditDetails(Model model, String roleFlag, String recordFlow){
		ResTeacherTraining teacherTraining = resStatisticBiz.searchTeacherInfoByPK(recordFlow);
		model.addAttribute("teacher", teacherTraining);
		model.addAttribute("roleFlag", roleFlag);
		return "/jsres/global/statistics/auditDetails";
	}

	@RequestMapping("/saveAuditDetails")
	@ResponseBody
	public String saveAuditDetails(String roleFlag, ResTeacherTraining teacherTraining){
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			if (StringUtil.isBlank(teacherTraining.getApplicationAuditStatus())) {
				return "请选择审核通过或者不通过！";
			} else {
                if (teacherTraining.getApplicationAuditStatus().equals(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId())) {
					teacherTraining = resStatisticBiz.searchTeacherInfoByPK(teacherTraining.getRecordFlow());
					teacherTraining.setTeacherLevelId(teacherTraining.getApplicationTeacherLevelId());
					teacherTraining.setTeacherLevelName(JsResTeacherLevelEnum.getNameById(teacherTraining.getApplicationTeacherLevelId()));
                    teacherTraining.setApplicationAuditStatus(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
				}
				resStatisticBiz.save(teacherTraining);
				List<String> allDeptFlows = new ArrayList<String>();
				if(StringUtil.isNotBlank(teacherTraining.getDeptFlow())&&!allDeptFlows.contains(teacherTraining.getDeptFlow())){
					allDeptFlows.add(teacherTraining.getDeptFlow());
				}
				SysUser user = userBiz.readSysUser(teacherTraining.getRecordFlow());
				if(allDeptFlows.size()>0){
					userBiz.addUserDept(user,allDeptFlows);
				}else {
					userBiz.disUserDept(user);
				}
				ResDoctor resDoctor = resDoctorBiz.findByFlow(teacherTraining.getRecordFlow());
				if (resDoctor != null) {
                    resDoctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
					resDoctorBiz.editDoctor(resDoctor);
				}
				//打开app权限
				String cfgCode = "jsres_teacher_app_login_"+teacherTraining.getRecordFlow();
                String cfgValue = com.pinde.core.common.GlobalConstant.FLAG_Y;
				String cfgDesc = "是否开放带教app权限";
				JsresPowerCfg cfg = new JsresPowerCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(cfgValue);
				cfg.setCfgDesc(cfgDesc);
                cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				jsResPowerCfgBiz.save(cfg);
				SysRole sysRole1 = userRoleBiz.getByRoleName("学员");
				SysUserRole sysUserRole1 = userRoleBiz.readUserRole(teacherTraining.getRecordFlow(), sysRole1.getRoleFlow());
				if (sysUserRole1 != null) {
                    sysUserRole1.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
					userRoleBiz.saveSysUserRole(sysUserRole1);
				}
				SysRole sysRole = userRoleBiz.getByRoleName("带教老师");
				SysUserRole sysUserRole = userRoleBiz.readUserRole(teacherTraining.getRecordFlow(), sysRole.getRoleFlow());
				if (sysUserRole == null) {
					List<String> allRoleFlows = new ArrayList<String>();
					allRoleFlows.add(sysRole.getRoleFlow());
					for (String roleFlow : allRoleFlows) {
                        userRoleBiz.saveSysUserRole(teacherTraining.getRecordFlow(), roleFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
					}
				}
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		} else if (StringUtil.isNotBlank(roleFlag) && "head".equals(roleFlag)) {
			if (StringUtil.isBlank(teacherTraining.getApplicationAuditStatus())) {
				return "请选择审核通过或者不通过！";
			} else {
				resStatisticBiz.save(teacherTraining);
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		} else {
			return "角色未赋权！";
		}
	}

	@RequestMapping("/batchAuditTeacherApplication")
	public String batchAuditTeacherApplication(Model model, String[] recordFlowList, String roleFlag){
		List<String> recordFlows = Arrays.asList(recordFlowList);
		model.addAttribute("recordFlowList", recordFlows);
		model.addAttribute("roleFlag", roleFlag);
		return "/jsres/global/statistics/batchAuditTeacherApplication";
	}

	@RequestMapping("/saveBatchAudit")
	@ResponseBody
	public String saveBatchAudit(String[] recordFlowList, String applicationAuditStatus, String applicationAuditMessage, String roleFlag){
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			if (StringUtil.isBlank(applicationAuditStatus)) {
				return "请选择审核通过或者不通过！";
			} else {
                if (applicationAuditStatus.equals(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId())) {
					for (String recordFlow : recordFlowList) {
						recordFlow = recordFlow.replace("[", "").replace("]", "");
						ResTeacherTraining teacherTraining = resStatisticBiz.searchTeacherInfoByPK(recordFlow);
						teacherTraining.setTeacherLevelId(teacherTraining.getApplicationTeacherLevelId());
						teacherTraining.setTeacherLevelName(JsResTeacherLevelEnum.getNameById(teacherTraining.getApplicationTeacherLevelId()));
                        teacherTraining.setApplicationAuditStatus(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
						resStatisticBiz.save(teacherTraining);
						List<String> allDeptFlows = new ArrayList<String>();
						if(StringUtil.isNotBlank(teacherTraining.getDeptFlow())&&!allDeptFlows.contains(teacherTraining.getDeptFlow())){
							allDeptFlows.add(teacherTraining.getDeptFlow());
						}
						SysUser user = userBiz.readSysUser(recordFlow);
						if(allDeptFlows.size()>0){
							userBiz.addUserDept(user,allDeptFlows);
						}else {
							userBiz.disUserDept(user);
						}
						ResDoctor resDoctor = resDoctorBiz.findByFlow(teacherTraining.getRecordFlow());
						if (resDoctor != null) {
                            resDoctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
							resDoctorBiz.editDoctor(resDoctor);
						}
						//打开app权限
						String cfgCode = "jsres_teacher_app_login_"+teacherTraining.getRecordFlow();
                        String cfgValue = com.pinde.core.common.GlobalConstant.FLAG_Y;
						String cfgDesc = "是否开放带教app权限";
						JsresPowerCfg cfg = new JsresPowerCfg();
						cfg.setCfgCode(cfgCode);
						cfg.setCfgValue(cfgValue);
						cfg.setCfgDesc(cfgDesc);
                        cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
						jsResPowerCfgBiz.save(cfg);
						SysRole sysRole1 = userRoleBiz.getByRoleName("学员");
						SysUserRole sysUserRole1 = userRoleBiz.readUserRole(teacherTraining.getRecordFlow(), sysRole1.getRoleFlow());
						if (sysUserRole1 != null) {
                            sysUserRole1.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
							userRoleBiz.saveSysUserRole(sysUserRole1);
						}
						SysRole sysRole = userRoleBiz.getByRoleName("带教老师");
						SysUserRole sysUserRole = userRoleBiz.readUserRole(teacherTraining.getRecordFlow(), sysRole.getRoleFlow());
						if (sysUserRole == null) {
							List<String> allRoleFlows = new ArrayList<String>();
							allRoleFlows.add(sysRole.getRoleFlow());
							for (String roleFlow : allRoleFlows) {
                                userRoleBiz.saveSysUserRole(teacherTraining.getRecordFlow(), roleFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
							}
						}
					}
				} else {
					for (String recordFlow : recordFlowList) {
						recordFlow = recordFlow.replace("[", "").replace("]", "");
						ResTeacherTraining teacherTraining = resStatisticBiz.searchTeacherInfoByPK(recordFlow);
                        teacherTraining.setApplicationAuditStatus(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getId());
						teacherTraining.setApplicationAuditMessage(applicationAuditMessage);
						resStatisticBiz.save(teacherTraining);
					}
				}
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		} else if (StringUtil.isNotBlank(roleFlag) && "head".equals(roleFlag)) {
			if (StringUtil.isBlank(applicationAuditStatus)) {
				return "请选择审核通过或者不通过！";
			} else {
				for (String recordFlow : recordFlowList) {
					recordFlow = recordFlow.replace("[", "").replace("]", "");
					ResTeacherTraining teacherTraining = resStatisticBiz.searchTeacherInfoByPK(recordFlow);
					teacherTraining.setApplicationAuditStatus(applicationAuditStatus);
					teacherTraining.setApplicationAuditMessage(applicationAuditMessage);
					resStatisticBiz.save(teacherTraining);
				}
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		} else {
			return "角色未赋权！";
		}
	}

	@RequestMapping("/searchChargeTeachTrainMain")
	public String searchChargeTeachTrainMain(Model model,String roleFlag){
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgCityId(s.getOrgCityId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/global/statistics/searchChargeTeachTrainMain";
	}

	@RequestMapping("/searchChargeTeachTrainList")
	public String searchChargeTeachTrainList(Model model,ResTeacherTraining teacherTraining,HttpServletRequest request,Integer currentPage,String roleFlag,String dataFlag){
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgCityId(s.getOrgCityId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		List<String> orgFlows = new ArrayList<>();
		if(null != orgs && orgs.size()>0){
			for (SysOrg so:orgs) {
				orgFlows.add(so.getOrgFlow());
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResTeacherTraining> teacherTrainingList =resStatisticBiz.searchTeacherInfoByCharge(teacherTraining,dataFlag,orgFlows);
		model.addAttribute("teacherTrainingList", teacherTrainingList);
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/global/statistics/searchTeachTrainList";
	}

	@RequestMapping("/searchOldTeachTrain")
	public String searchOldTeachTrain(Model model,ResTeacherTraining teacherTraining,HttpServletRequest request,Integer currentPage,String roleFlag,String dataFlag){
		dataFlag = "oldData";
		teacherTraining.setOrgFlow(null);
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			teacherTraining.setOrgName(s.getOrgName());
			orgs.add(s);
		}else {
			org.setOrgProvId(s.getOrgProvId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
		}
		model.addAttribute("orgs", orgs);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResTeacherTraining> teacherTrainingList =resStatisticBiz.searchTeacherInfo2(teacherTraining,dataFlag);
		model.addAttribute("teacherTrainingList", teacherTrainingList);
		model.addAttribute("roleFlag", roleFlag);
		return "/jsres/global/statistics/searchTeachTrain";
	}

	@RequestMapping("/searchChargeOldTeachTrain")
	public String searchChargeOldTeachTrain(Model model,ResTeacherTraining teacherTraining,HttpServletRequest request,Integer currentPage,String roleFlag,String dataFlag){
		dataFlag = "oldData";
		teacherTraining.setOrgFlow(null);
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgCityId(s.getOrgCityId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		List<String> orgNames = new ArrayList<>();
		if(null != orgs && orgs.size()>0){
			for (SysOrg so:orgs) {
				orgNames.add(so.getOrgName());
			}
		}
		model.addAttribute("orgs", orgs);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResTeacherTraining> teacherTrainingList =resStatisticBiz.searchTeacherInfoByCharge2(teacherTraining,dataFlag,orgNames);
		model.addAttribute("teacherTrainingList", teacherTrainingList);
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/global/statistics/searchChargeTeachTrain";
	}

	@RequestMapping("/leadTo")
	public String leadTo(Model model,String roleFlag){
		model.addAttribute("roleFlag",roleFlag);
		return "/jsres/global/statistics/teacherDaoRu";
	}

	@RequestMapping("/importTeacherExcel")
	@ResponseBody
	public String importTeacherExcel(MultipartFile file,String roleFlag) {
		String message = "";
		if (file != null && file.getSize() > 0) {
			try {
				int result = resStatisticBiz.importTeacherExcelNew(file,roleFlag);
				if(result<0) {
					message ="导入文件内容为空！请确认后导入！";
					return message;
				}
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    message = com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                message = e.getMessage();
			}
		}
		return message;
	}

	@RequestMapping("/deleteInfo")
	@ResponseBody
	public String deleteInfo(String recordFlow){
		if(StringUtil.isNotBlank(recordFlow)) {
			ResTeacherTraining teacherTraining = resStatisticBiz.searchTeacherInfoByPK(recordFlow);
			if(teacherTraining!=null) {
                teacherTraining.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
				int count = resStatisticBiz.editTeacherInfo(teacherTraining);
                if (count == com.pinde.core.common.GlobalConstant.ONE_LINE) {
                    return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}

	@RequestMapping("/saveTeacher")
	@ResponseBody
	public String saveTeacher(ResTeacherTraining teacherTraining){
		int count=resStatisticBiz.save(teacherTraining);
        if (count == com.pinde.core.common.GlobalConstant.ONE_LINE) {
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping("/checkUserPhone")
	@ResponseBody
	public String checkUserPhone(String userPhone, String recordFlow){
		//判断手机号是否重复
		if (StringUtil.isNotBlank(recordFlow)) {
			SysUserExample example=new SysUserExample();
            example.createCriteria().andUserPhoneEqualTo(userPhone).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
			if(sysUserList != null && !sysUserList.isEmpty()){
				if (sysUserList.get(0).getUserFlow().equals(recordFlow)) {
                    return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
				} else {
					return "手机号码重复，请确认！";
				}
			} else {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		} else {
			SysUserExample example=new SysUserExample();
            example.createCriteria().andUserPhoneEqualTo(userPhone).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SysUser> sysUserList = sysUserMapper.selectByExample(example);
			if(sysUserList != null && !sysUserList.isEmpty()){
				return "手机号码重复，请确认！";
			}
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
	}

	@RequestMapping(value = "/saveTeacherTrainingInfo")
	public String saveTeacherTrainingInfo(ResTeacherTraining teacherTraining,SysUser sysUser, String roleId, MultipartFile uploadFile,MultipartFile uploadFileOne,String certificateUrl,String achievementUrl, Model model) {
		SysUser user = GlobalContext.getCurrentUser();
		if (StringUtil.isNotBlank(teacherTraining.getTeacherLevelId())) {
			teacherTraining.setTeacherLevelName(JsResTeacherLevelEnum.getNameById(teacherTraining.getTeacherLevelId()));
		}
		if (uploadFile != null) {
			//判断证书编号是否重复
			ResTeacherTraining training = resStatisticBiz.searchTeacherInfoByCertificateNoNotSelf(teacherTraining.getCertificateNo(),teacherTraining.getDoctorName());
			if(null == training){
				String resultPath = resStatisticBiz.saveFileToDirs("", uploadFile, "teacherTrain");
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(resultPath)) {
					teacherTraining.setCertificateUrl(certificateUrl);
				}else {
					teacherTraining.setCertificateUrl(resultPath);
				}
				resStatisticBiz.save(teacherTraining);
			}else{
				List<SysOrg> orgs=new ArrayList<SysOrg>();
				SysOrg org=new SysOrg();
				SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
				teacherTraining.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
				orgs.add(s);
				List<SysDept> deptList=deptBiz.searchDeptByOrg(teacherTraining.getOrgFlow());
				model.addAttribute("deptList",deptList);
				model.addAttribute("teacher",teacherTraining);
                model.addAttribute("certificateNoFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
			}
		}
		if (uploadFileOne != null) {
			String resultPath = resStatisticBiz.saveFileToDirs("", uploadFileOne, "teacherTrain");
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(resultPath)) {
				teacherTraining.setAchievementUrl(achievementUrl);
			}else {
				teacherTraining.setAchievementUrl(resultPath);
			}
			resStatisticBiz.save(teacherTraining);
		}

		user.setUserName(teacherTraining.getDoctorName());
		user.setUserPhone(teacherTraining.getUserPhone());
		user.setSexName(teacherTraining.getSexName());
		user.setIdNo(sysUser.getIdNo());
		user.setUserEmail(sysUser.getUserEmail());
		SysDept dept =deptBiz.readSysDept(teacherTraining.getDeptFlow());
		if(dept!=null)
		{
			user.setDeptName(dept.getDeptName());
			user.setDeptFlow(teacherTraining.getDeptFlow());
		}
		user.setOrgFlow(teacherTraining.getOrgFlow());
		user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
		userBiz.saveUser(user);
		if ("student".equals(roleId)) {
			return "redirect:/jsres/doctor/index";
		} else {
            model.addAttribute("pageFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
			return "jsres/teacher/index";
		}
	}

	@RequestMapping(value = "/teacherApplication")
	public String teacherApplication(String recordFlow, String roleId, Model model) {
		ResTeacherTraining teacherTraining = resStatisticBiz.searchTeacherInfoByPK(recordFlow);
		model.addAttribute("roleId", roleId);
		model.addAttribute("teacher", teacherTraining);
		if ("HeadAuditing".equals(teacherTraining.getApplicationAuditStatus()) || "HeadPassed".equals(teacherTraining.getApplicationAuditStatus())) {
            model.addAttribute("applicationFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
		}
		return "jsres/global/statistics/teacherApplication";
	}

	@RequestMapping(value = "/saveTeacherApplication")
	public String saveTeacherApplication(ResTeacherTraining teacherTraining, MultipartFile uploadFile, Model model) {
		String keyOfHeadAudit = "jsres_" + GlobalContext.getCurrentUser().getOrgFlow() + "_head_audit";
		String isHeadAudit = JsresPowerCfgController.jsresPowerCfgMap(keyOfHeadAudit);
        if (StringUtil.isNotBlank(isHeadAudit) && isHeadAudit.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
			teacherTraining.setApplicationAuditStatus("HeadAuditing");
		}
        if (StringUtil.isBlank(isHeadAudit) || isHeadAudit.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
			teacherTraining.setApplicationAuditStatus("HeadPassed");
		}
		if (uploadFile != null) {
			String resultPath = resStatisticBiz.saveFileToDirs("", uploadFile, "teacherTrain");
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(resultPath)) {
				teacherTraining.setApplicationProvalUrl(null);
			} else {
				teacherTraining.setApplicationProvalUrl(resultPath);
			}
			resStatisticBiz.save(teacherTraining);
		}
        model.addAttribute("pageFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		return "jsres/global/statistics/teacherApplication";
	}

	@RequestMapping(value = "/applicationStatus")
	public String applicationStatus(String recordFlow, Model model) {
		ResTeacherTraining teacherTraining = resStatisticBiz.searchTeacherInfoByPK(recordFlow);
		model.addAttribute("teacher", teacherTraining);
		return "jsres/global/statistics/applicationStatus";
	}

	@RequestMapping(value="/checkUploadFile",method={RequestMethod.POST})
	public String checkUploadFile(ResTeacherTraining teacherTraining, MultipartFile uploadFile,MultipartFile uploadFileOne,String certificateUrl,String achievementUrl,String roleFlag,Model model){

		if (StringUtil.isNotBlank(teacherTraining.getTeacherLevelId())) {
			teacherTraining.setTeacherLevelName(JsResTeacherLevelEnum.getNameById(teacherTraining.getTeacherLevelId()));
		}
		if (uploadFile != null) {
			//判断证书编号是否重复
			ResTeacherTraining training = resStatisticBiz.searchTeacherInfoByCertificateNoNotSelf(teacherTraining.getCertificateNo(),teacherTraining.getDoctorName());
			if(null == training){
				String resultPath = resStatisticBiz.saveFileToDirs("", uploadFile, "teacherTrain");
                model.addAttribute("result", com.pinde.core.common.GlobalConstant.FLAG_Y);
				model.addAttribute("filePath", resultPath);
                if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(resultPath)) {
					teacherTraining.setCertificateUrl(certificateUrl);
				}else {
					teacherTraining.setCertificateUrl(resultPath);
				}
				resStatisticBiz.save(teacherTraining);
			}else{
				List<SysOrg> orgs=new ArrayList<SysOrg>();
				SysOrg org=new SysOrg();
				SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
                if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
					teacherTraining.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
					orgs.add(s);
				}else {
					org.setOrgProvId(s.getOrgProvId());
                    org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
					orgs=orgBiz.searchAllSysOrg(org);
				}
				model.addAttribute("orgs", orgs);
				List<SysDept> deptList=deptBiz.searchDeptByOrg(teacherTraining.getOrgFlow());
				model.addAttribute("deptList",deptList);
				model.addAttribute("teacher",teacherTraining);
                model.addAttribute("certificateNoFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
			}
		}else{
            model.addAttribute("result", com.pinde.core.common.GlobalConstant.FLAG_N);
		}
		if (uploadFileOne != null) {
			String resultPath = resStatisticBiz.saveFileToDirs("", uploadFileOne, "teacherTrain");
            model.addAttribute("result", com.pinde.core.common.GlobalConstant.FLAG_Y);
			model.addAttribute("filePath", resultPath);
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(resultPath)) {
				teacherTraining.setAchievementUrl(achievementUrl);
			}else {
				teacherTraining.setAchievementUrl(resultPath);
			}
			resStatisticBiz.save(teacherTraining);
		}else{
            model.addAttribute("result", com.pinde.core.common.GlobalConstant.FLAG_N);
		}

		SysUser user = new SysUser();
		user.setUserFlow(teacherTraining.getRecordFlow());
		user.setUserCode(teacherTraining.getUserPhone());
		SysUser sysUser = userBiz.readSysUser(teacherTraining.getRecordFlow());
		if (sysUser == null) {
			user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitConfig.getJxInitPassWord()));
			user.setStatusId(UserStatusEnum.Activated.getId());
			user.setStatusDesc(UserStatusEnum.Activated.getName());
			user.setCretTypeId(CertificateTypeEnum.Shenfenzheng.getId());
			user.setCretTypeName(CertificateTypeEnum.Shenfenzheng.getName());
			GeneralMethod.setRecordInfo(user, true);
			sysUserMapper.insert(user);
		}
		user = userBiz.readSysUser(teacherTraining.getRecordFlow());
		user.setUserName(teacherTraining.getDoctorName());
		user.setUserPhone(teacherTraining.getUserPhone());
		user.setSexName(teacherTraining.getSexName());
		SysDept dept =deptBiz.readSysDept(teacherTraining.getDeptFlow());
		if(dept!=null)
		{
			user.setDeptName(dept.getDeptName());
			user.setDeptFlow(teacherTraining.getDeptFlow());
		}
		user.setOrgFlow(teacherTraining.getOrgFlow());
		user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
		userBiz.saveUser(user);
		List<String> allDeptFlows = new ArrayList<String>();
		if(StringUtil.isNotBlank(user.getDeptFlow())&&!allDeptFlows.contains(user.getDeptFlow())){
			allDeptFlows.add(user.getDeptFlow());
		}
		if(allDeptFlows.size()>0){
			userBiz.addUserDept(user,allDeptFlows);
		}else {
			userBiz.disUserDept(user);
		}
		//打开app权限
		String cfgCode = "jsres_teacher_app_login_"+user.getUserFlow();
        String cfgValue = com.pinde.core.common.GlobalConstant.FLAG_Y;
		String cfgDesc = "是否开放带教app权限";
		JsresPowerCfg cfg = new JsresPowerCfg();
		cfg.setCfgCode(cfgCode);
		cfg.setCfgValue(cfgValue);
		cfg.setCfgDesc(cfgDesc);
        cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		jsResPowerCfgBiz.save(cfg);

		SysRole sysRole = userRoleBiz.getByRoleName("带教老师");
		SysUserRole sysUserRole = userRoleBiz.readUserRole(user.getUserFlow(), sysRole.getRoleFlow());
		if (sysUserRole == null) {
			List<String> allRoleFlows = new ArrayList<String>();
			allRoleFlows.add(sysRole.getRoleFlow());
			for (String roleFlow : allRoleFlows) {
                userRoleBiz.saveSysUserRole(user.getUserFlow(), roleFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
			}
		}

		return "jsres/global/statistics/editTeacherInfo";
	}

	@RequestMapping("/deleteCerfiticateImg")
	@ResponseBody
	public String deleteCerfiticateImg(String recordFlow){
		if(StringUtil.isNotBlank(recordFlow)){
			int result = resStatisticBiz.deleteCerfiticateImg(recordFlow);
            if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}

	@RequestMapping("/deleteAchievementImg")
	@ResponseBody
	public String deleteAchievementImg(String recordFlow){
		if(StringUtil.isNotBlank(recordFlow)){
			int result = resStatisticBiz.deleteAchievementImg(recordFlow);
            if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}

	@RequestMapping("/editInfo")
	public String editInfo(String recordFlow,String roleFlag,Model model){
		if(StringUtil.isNotBlank(recordFlow)) {
			ResTeacherTraining teacherTraining=resStatisticBiz.searchTeacherInfoByPK(recordFlow);
			if (null!=teacherTraining){
				int year = Integer.parseInt(DateUtil.getYear());
				int moYear = Integer.parseInt(teacherTraining.getModifyTime().substring(0, 4));
				int num=year- moYear;
				if (StringUtil.isNotBlank(teacherTraining.getDoctorAge())){
					teacherTraining.setDoctorAge(String.valueOf(Integer.parseInt(teacherTraining.getDoctorAge())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getOfficeYear())){
					teacherTraining.setOfficeYear(String.valueOf(Integer.parseInt(teacherTraining.getOfficeYear())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getWorkYear())){
					teacherTraining.setWorkYear(String.valueOf(Integer.parseInt(teacherTraining.getWorkYear())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getInternYear())){
					teacherTraining.setInternYear(String.valueOf(Integer.parseInt(teacherTraining.getInternYear())+num));
				}
				if (StringUtil.isNotBlank(teacherTraining.getHosYear())){
					teacherTraining.setHosYear(String.valueOf(Integer.parseInt(teacherTraining.getHosYear())+num));
				}
			}
			model.addAttribute("teacher",teacherTraining);
			List<SysDept> deptList=deptBiz.searchDeptByOrg(teacherTraining.getOrgFlow());
			model.addAttribute("deptList",deptList);
		}
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			orgs.add(s);
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			org.setOrgProvId(s.getOrgProvId());
			org.setOrgCityId(s.getOrgCityId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs = orgBiz.searchAllSysOrg(org);
		}else {
			org.setOrgProvId(s.getOrgProvId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs = orgBiz.searchAllSysOrg(org);
		}
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("orgs", orgs);
		return "/jsres/global/statistics/editTeacherInfo";
	}

	@RequestMapping("/statisticsTeachTrainMain")
	public String statisticsTeachTrainMain(Model model,String roleFlag){
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			orgs.add(s);
		}else {
			org.setOrgProvId(s.getOrgProvId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
		}
		model.addAttribute("orgs", orgs);
		return "jsres/global/statistics/statisticsTeachTrainMain";
	}

	@RequestMapping("/statisticsTeachTrainList")
	public String statisticsTeachTrainList(Model model,ResTeacherTraining teacherTraining,HttpServletRequest request,Integer currentPage,String roleFlag){
		List<SysOrg> orgs=new ArrayList<SysOrg>();
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			teacherTraining.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			orgs.add(s);
		}else {
			org.setOrgProvId(s.getOrgProvId());
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			orgs=orgBiz.searchAllSysOrg(org);
		}
		model.addAttribute("orgs", orgs);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResTeacherTraining> teacherTrainingList =resStatisticBiz.searchTeacherInfo2(teacherTraining,"newData");
		model.addAttribute("teacherTrainingList", teacherTrainingList);
		return "/jsres/global/statistics/statisticsTeachTrainList";
	}

	@RequestMapping("/statisticsChargeTeachTrainMain")
	public String statisticsChargeTeachTrainMain(Model model,String roleFlag){
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgCityId(s.getOrgCityId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/global/statistics/statisticsChargeTeachTrainMain";
	}

	@RequestMapping("/statisticsChargeTeachTrainList")
	public String statisticsChargeTeachTrainList(Model model,ResTeacherTraining teacherTraining,HttpServletRequest request,Integer currentPage,String roleFlag){
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgCityId(s.getOrgCityId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		List<String> orgFlows = new ArrayList<>();
		if(null != orgs && orgs.size()>0){
			for (SysOrg so:orgs) {
				orgFlows.add(so.getOrgFlow());
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResTeacherTraining> teacherTrainingList = resStatisticBiz.searchTeacherInfoByCharge(teacherTraining,"newData",orgFlows);
		model.addAttribute("teacherTrainingList", teacherTrainingList);
		return "/jsres/global/statistics/statisticsChargeTeachTrainList";
	}

	@RequestMapping("/exportTeachTrainList")
	public void exportTeachTrainList(ResTeacherTraining teacherTraining,HttpServletResponse response,String roleFlag) throws Exception{
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			teacherTraining.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		List<ResTeacherTraining> teacherTrainingList = resStatisticBiz.searchTeacherInfo2(teacherTraining,"newData");
		if (null!=teacherTrainingList && !teacherTrainingList.isEmpty()){
			for (ResTeacherTraining training : teacherTrainingList) {
                if (StringUtil.isNotBlank(training.getIsTrain()) && training.getIsTrain().equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)) {
					training.setIsTrain("有");
				}else {
					training.setIsTrain("无");
				}
			}
		}
		String[] titles = new String[]{
				"doctorName:姓名",
				"doctorAge:年龄",
				"sexName:性别",
				"userPhone:手机号码",
				"technicalTitle:技术职称",
				"graduationSchool:毕业学校",
				"graduationTime:毕业时间",
				"doctorEdu:学历",
				"orgName:基地名称",
				"deptName:科室",
				"speName:专业",
				"trainingYear:培训年份",
				"workingTime:工作时间",
				"meetingName:师资培训会议名称",
				"certificateNo:证书编号",
				"certificateLevel:证书级别",
				"certificateTime:证书时间",
				"officeYear:任现职务年限",
				"workYear:从事本专业临床工作年限",
				"internYear:带实习生年限",
				"threeInternYear:带实习生近3年累计人数",
				"hosYear:带住院医师年限",
				"threeHosYear:带住院医师近3年累计人数",
				"isTrain:参加省级及以上住院医师规范化培训师资培训"
		};
		String fileName = "师资培训信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsNew(titles, teacherTrainingList, response.getOutputStream());
	}

	@RequestMapping("/exportChargeTeachTrainList")
	public void exportChargeTeachTrainList(ResTeacherTraining teacherTraining,HttpServletResponse response,String roleFlag) throws Exception{
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgCityId(s.getOrgCityId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		List<String> orgFlows = new ArrayList<>();
		if(null != orgs && orgs.size()>0){
			for (SysOrg so:orgs) {
				orgFlows.add(so.getOrgFlow());
			}
		}
		List<ResTeacherTraining> teacherTrainingList = resStatisticBiz.searchTeacherInfoByCharge(teacherTraining,"newData",orgFlows);
		String[] titles = new String[]{
				"doctorName:姓名",
				"sexName:性别",
				"technicalTitle:技术职称",
				"orgName:基地名称",
				"deptName:科室",
				"speName:专业",
				"trainingYear:培训年份",
				"certificateNo:证书编号"
		};
		String fileName = "师资培训信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, teacherTrainingList, response.getOutputStream());
	}

	@RequestMapping("/exportOldTeachTrainList")
	public void exportOldTeachTrainList(ResTeacherTraining teacherTraining,HttpServletResponse response,String roleFlag) throws Exception{
		teacherTraining.setOrgFlow(null);
		SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			teacherTraining.setOrgName(s.getOrgName());
		}
		List<ResTeacherTraining> teacherTrainingList = resStatisticBiz.searchTeacherInfo2(teacherTraining,"oldData");
		String[] titles = new String[]{
				"doctorName:姓名",
				"sexName:性别",
				"technicalTitle:技术职称",
				"orgName:基地名称",
				"certificateNo:证书编号"
		};
		String fileName = "师资培训信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, teacherTrainingList, response.getOutputStream());
	}

	@RequestMapping("/exportChargeOldTeachTrainList")
	public void exportChargeOldTeachTrainList(ResTeacherTraining teacherTraining,HttpServletResponse response,String roleFlag) throws Exception{
		teacherTraining.setOrgFlow(null);
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
		org.setOrgCityId(s.getOrgCityId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		List<String> orgNames = new ArrayList<>();
		if(null != orgs && orgs.size()>0){
			for (SysOrg so:orgs) {
				orgNames.add(so.getOrgName());
			}
		}
		List<ResTeacherTraining> teacherTrainingList = resStatisticBiz.searchTeacherInfoByCharge2(teacherTraining,"oldData",orgNames);
		String[] titles = new String[]{
				"doctorName:姓名",
				"sexName:性别",
				"technicalTitle:技术职称",
				"orgName:基地名称",
				"certificateNo:证书编号"
		};
		String fileName = "师资培训信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, teacherTrainingList, response.getOutputStream());
	}

	@RequestMapping(value = {"/downImg" }, method = RequestMethod.GET)
	public void downImg (String recordFlow, final HttpServletResponse response) throws Exception{
		ResTeacherTraining training = resStatisticBiz.searchTeacherInfoByPK(recordFlow);
		resStatisticBiz.downImg(training,response);
	}

	/**
	* 绩效-学员填写量 住院医师
	*/
	@RequestMapping("/doctorDataMain")
	public String doctorDataMain(Model model,String roleFlag){
		model.addAttribute("roleFlag", roleFlag);
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(s.getOrgCityId());
		}
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs",orgs);
		return "jsres/hospital/performance/doctorDataMain";
	}

	/**
	 * 绩效-学员填写量 助理全科
	 */
	@RequestMapping("/doctorDataMainAcc")
	public String doctorDataMainAcc(Model model,String roleFlag){
		model.addAttribute("roleFlag", roleFlag);
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(s.getOrgCityId());
		}
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs",orgs);
		return "jsres/hospital/performance/doctorDataMainAcc";
	}

	/**
	 * 绩效-学员填写量列表 住院医师
	 */
	@RequestMapping("/doctorDataList")
	public String doctorDataList(Model model,String roleFlag,String orgFlow,String userName,String idNo,String speId,String sessionNumber, String graduationYear, String fillInPercentLow, String fillInPercentHigh
										,String[] datas,Integer currentPage,HttpServletRequest request){
		List<String> docTypeList = new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param = new HashMap<>();
		param.put("userName",userName);
		param.put("idNo",idNo);
		param.put("speId",speId);
		param.put("sessionNumber",sessionNumber);
		param.put("docTypeList",docTypeList);
		param.put("graduationYear",graduationYear);
		if (StringUtil.isNotBlank(fillInPercentLow)) {
			param.put("fillInPercentLow",new BigDecimal(fillInPercentLow).divide(new BigDecimal(100)).toString());
		}
		if (StringUtil.isNotBlank(fillInPercentHigh)) {
			param.put("fillInPercentHigh",new BigDecimal(fillInPercentHigh).divide(new BigDecimal(100)).toString());
		}

		SysOrg searchOrg = new SysOrg();
		SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		searchOrg.setOrgProvId(s.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			searchOrg.setOrgCityId(s.getOrgCityId());
			param.put("orgFlow",orgFlow);
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
			param.put("orgFlow",orgFlow);
		}
		param.put("searchOrg",searchOrg);
//		param.put("trainingTypeId","DoctorTrainingSpe");	//住院医师
		PageHelper.startPage(currentPage, getPageSize(request));
//		List<Map<String,Object>> list = resStatisticBiz.searchDoctorData(param);
		//List<Map<String,Object>> list = resStatisticBiz.searchDoctorDataNew(param);
		List<Map<String,Object>> list = resStatisticBiz.searchDoctorDataNew2(param);
		model.addAttribute("list",list);
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("datas", datas);
		return "jsres/hospital/performance/doctorDataList";
	}

	/**
	 * 绩效-学员填写量列表 助理全科
	 */
	@RequestMapping("/doctorDataListAcc")
	public String doctorDataListAcc(Model model,String roleFlag,String orgFlow,String userName,String idNo,String speId,String sessionNumber
			,String[] datas,Integer currentPage,HttpServletRequest request){
		List<String> docTypeList = new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param = new HashMap<>();
		param.put("userName",userName);
		param.put("idNo",idNo);
		param.put("speId",speId);
		param.put("sessionNumber",sessionNumber);
		param.put("docTypeList",docTypeList);
		SysOrg searchOrg = new SysOrg();
		SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		searchOrg.setOrgProvId(s.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			searchOrg.setOrgCityId(s.getOrgCityId());
			param.put("orgFlow",orgFlow);
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
			param.put("orgFlow",orgFlow);
		}
		param.put("searchOrg",searchOrg);
		param.put("trainingTypeId","AssiGeneral");	//助理全科
		PageHelper.startPage(currentPage, getPageSize(request));
//		List<Map<String,Object>> list = resStatisticBiz.searchDoctorData(param);
		//List<Map<String,Object>> list = resStatisticBiz.searchDoctorDataNew(param);
		List<Map<String,Object>> list = resStatisticBiz.searchDoctorDataNew2(param);
		model.addAttribute("list",list);
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("datas", datas);
		return "jsres/hospital/performance/doctorDataListAcc";
	}

	/**
	 * 绩效-学员填写量导出 住院医师
	 */
	@RequestMapping("/expDoctorDataList")
	public void expDoctorDataList(String roleFlag,String orgFlow,String userName,String idNo,String speId,String sessionNumber, String graduationYear, String fillInPercentLow, String fillInPercentHigh
			,String[] datas,HttpServletResponse response) throws Exception {
		List<String> docTypeList = new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param = new HashMap<>();
		param.put("userName",userName);
		param.put("idNo",idNo);
		param.put("speId",speId);
		param.put("sessionNumber",sessionNumber);
		param.put("docTypeList",docTypeList);
		param.put("graduationYear",graduationYear);
		if (StringUtil.isNotBlank(fillInPercentLow)) {
			param.put("fillInPercentLow",new BigDecimal(fillInPercentLow).divide(new BigDecimal(100)).toString());
		}
		if (StringUtil.isNotBlank(fillInPercentHigh)) {
			param.put("fillInPercentHigh",new BigDecimal(fillInPercentHigh).divide(new BigDecimal(100)).toString());
		}
		SysOrg searchOrg = new SysOrg();
		SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		searchOrg.setOrgProvId(s.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			searchOrg.setOrgCityId(s.getOrgCityId());
			param.put("orgFlow",orgFlow);
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
			param.put("orgFlow",orgFlow);
		}
		param.put("searchOrg",searchOrg);
//		param.put("trainingTypeId","DoctorTrainingSpe");	//住院医师
//		List<Map<String,Object>> list = resStatisticBiz.searchDoctorData(param);
		//List<Map<String,Object>> list = resStatisticBiz.searchDoctorDataNew(param);
		List<Map<String,Object>> list = resStatisticBiz.searchDoctorDataNew2(param);
		if(CollectionUtils.isNotEmpty(list)){
			for (Map<String, Object> stringObjectMap : list) {
				String reqNum = (String) stringObjectMap.get("reqNum");
				String completeNum = (String) stringObjectMap.get("completeNum");
				stringObjectMap.put("notNum",Integer.parseInt(reqNum) - Integer.parseInt(completeNum));
				stringObjectMap.put("percent", GeneralMethod.getPercentByString(completeNum,reqNum));

			}
		}
		String[] titles = new String[]{
				"userName:学员姓名",
				"userPhone:手机号码",
				"idNo:证件号",
				"sessionNumber:年级",
				"graduationYear:结业年份",
				"speName:专业",
				"doctorTypeName:人员类型",
				"reqNum:应填写量",
				"completeNum:已填写量",
				"notNum:未填写量",
				"percent:填写率"
		};
		String fileName = "学员填写量导出.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, list, response.getOutputStream());
	}


	/**
	 * 绩效-学员填写量导出 助理全科
	 */
	@RequestMapping("/expDoctorDataListAcc")
	public void expDoctorDataListAcc(String roleFlag,String orgFlow,String userName,String idNo,String speId,String sessionNumber
			,String[] datas,HttpServletResponse response) throws Exception {
		List<String> docTypeList = new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		Map<String,Object> param = new HashMap<>();
		param.put("userName",userName);
		param.put("idNo",idNo);
		param.put("speId",speId);
		param.put("sessionNumber",sessionNumber);
		param.put("docTypeList",docTypeList);
		SysOrg searchOrg = new SysOrg();
		SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		searchOrg.setOrgProvId(s.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			searchOrg.setOrgCityId(s.getOrgCityId());
			param.put("orgFlow",orgFlow);
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
			param.put("orgFlow",orgFlow);
		}
		param.put("searchOrg",searchOrg);
		param.put("trainingTypeId","AssiGeneral");	//助理全科
//		List<Map<String,Object>> list = resStatisticBiz.searchDoctorData(param);
		//List<Map<String,Object>> list = resStatisticBiz.searchDoctorDataNew(param);
		List<Map<String,Object>> list = resStatisticBiz.searchDoctorDataNew2(param);
		if(CollectionUtils.isNotEmpty(list)){
			for (Map<String, Object> stringObjectMap : list) {
				String reqNum = (String) stringObjectMap.get("reqNum");
				String completeNum = (String) stringObjectMap.get("completeNum");
				stringObjectMap.put("notNum",Integer.parseInt(reqNum) - Integer.parseInt(completeNum));
				stringObjectMap.put("percent", GeneralMethod.getPercentByString(completeNum,reqNum));

			}
		}
		String[] titles = new String[]{
				"userName:学员姓名",
				"userPhone:手机号码",
				"idNo:证件号",
				"sessionNumber:年级",
				"speName:专业",
				"doctorTypeName:人员类型",
				"reqNum:应填写量",
				"completeNum:已填写量",
				"notNum:未填写量",
				"percent:填写率"
		};
		String fileName = "学员填写量导出.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, list, response.getOutputStream());
	}

	/**
	 * 绩效-带教审核量
	 */
	@RequestMapping("/teacherAuditMain")
	public String teacherAuditMain(Model model,String roleFlag){
		model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		SysOrg org=new SysOrg();
		SysOrg s=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		org.setOrgProvId(s.getOrgProvId());
        org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			org.setOrgCityId(s.getOrgCityId());
		}
		List<SysOrg> orgs = orgBiz.searchAllSysOrg(org);
		model.addAttribute("orgs", orgs);
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/hospital/performance/teacherAuditMain";
	}

	/**
	 * 查询基地下科室
	 */
	@RequestMapping(value = "/searchDeptListByOrg",method={RequestMethod.POST})
	@ResponseBody
	public List<SysDept> searchDeptListByOrg(String orgFlow){
		List<SysDept> deptList = new ArrayList<>();
		if(StringUtil.isNotBlank(orgFlow)){
			deptList = deptBiz.searchDeptByOrg(orgFlow);
		}
		return deptList;
	}

	/**
	 * 绩效-带教审核量列表
	 */
	@RequestMapping("/teacherAuditList")
	public String teacherAuditList(Model model,String roleFlag,String teacherName,String deptFlow,Integer currentPage,String orgFlow,
								   HttpServletRequest request){
		Map<String,Object> param = new HashMap<>();
		param.put("teacherName",teacherName);
		param.put("deptFlow",deptFlow);
		param.put("roleFlow",InitConfig.getSysCfg("res_teacher_role_flow"));
		SysOrg searchOrg = new SysOrg();
		SysOrg s = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		searchOrg.setOrgProvId(s.getOrgProvId());
        searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
			param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			searchOrg.setOrgCityId(s.getOrgCityId());
			param.put("orgFlow",orgFlow);
        } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
			param.put("orgFlow",orgFlow);
		}
		param.put("searchOrg",searchOrg);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> list = resStatisticBiz.searchTeacherUserList(param);
		if (null != list && list.size() > 0) {
			List<String> teacherFlows = new ArrayList<>();
			for (Map<String, Object> tmap : list) {
				teacherFlows.add((String)tmap.get("USER_FLOW"));
			}
			param.put("teacherFlows",teacherFlows);
			List<Map<String,Object>> auditList = resStatisticBiz.searchTeacherAuditList(param);
			for (Map<String, Object> tmap : list) {
				String teacherFlow = (String)tmap.get("USER_FLOW");
				if (null != auditList && auditList.size() > 0) {
					for (Map<String, Object> tempMap : auditList) {
						if (teacherFlow.equals(tempMap.get("TEACHER_USER_FLOW"))) {
							tmap.put("notAudited", tempMap.get("NOT_AUDITED"));
							tmap.put("isAudited", tempMap.get("IS_AUDITED"));
							continue;
						}
					}
				}
			}
		}
		model.addAttribute("list",list);
		model.addAttribute("roleFlag", roleFlag);
		return "jsres/hospital/performance/teacherAuditList";
	}
}
