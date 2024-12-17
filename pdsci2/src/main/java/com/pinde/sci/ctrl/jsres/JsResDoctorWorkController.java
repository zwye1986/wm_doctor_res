package com.pinde.sci.ctrl.jsres;


import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.common.enums.RegistryTypeEnum;
import com.pinde.core.model.ResSchProcessExpress;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDoctorBiz;
import com.pinde.sci.biz.jsres.IJsResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sch.impl.SchRotationGroupBizImpl;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SchRotationDeptMapper;
import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResJointOrg;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/jsres/docWork")
public class JsResDoctorWorkController extends GeneralController {
	@Autowired
	private IJsResDoctorBiz jsResDoctorBiz;
	@Autowired
	private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private SchRotationGroupBizImpl rotationGroupBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private OrgBizImpl orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private IResDoctorProcessBiz processBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorOrgHistoryBiz resDoctorOrgHistoryBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private ISchRotationBiz rotationBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchRotationDeptBiz rotationDeptBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private ISchRotationGroupBiz groupBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;

	/**
	 * 省厅角色和市局角色共用  住院医师
	 * @param model
	 * @param roleFlag
     * @return
     */
	@RequestMapping(value="/docWorkQuery")
	public String docWorkQuery(Model model,String roleFlag){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		SysOrg sysorg = new SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		//市局角色
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			sysorg.setOrgCityId(org.getOrgCityId());
		}
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("orgs", orgs);
		if(StringUtil.isNotBlank(roleFlag)){
			model.addAttribute("roleFlag",roleFlag);
		}
		return  "jsres/global/docWork/workQuery";
	}

	/**
	 * 省厅角色和市局角色共用  助理全科
	 * @param model
	 * @param roleFlag
	 * @return
	 */
	@RequestMapping(value="/docWorkQueryAcc")
	public String docWorkQueryAcc(Model model,String roleFlag){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		model.addAttribute("org",org);
		SysOrg sysorg =new  SysOrg();
		sysorg.setOrgProvId(org.getOrgProvId());
		//市局角色
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			sysorg.setOrgCityId(org.getOrgCityId());
		}
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
		List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
		List<String> orgFlowList=new ArrayList<String>();
		if(jointOrgs!=null&&!jointOrgs.isEmpty()){
			for(ResJointOrg jointOrg:jointOrgs){
				orgFlowList.add(jointOrg.getOrgFlow());
			}
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("orgs", orgs);
		if(StringUtil.isNotBlank(roleFlag)){
			model.addAttribute("roleFlag",roleFlag);
		}
		return  "jsres/global/docWork/workQueryAcc";
	}
	/**
	 * 医师工作量查询
	 * */
	@RequestMapping(value = {"/workList" })
	public String workList(String orgLevel,String orgFlow,String[] datas,String jointOrgFlag,String roleFlag,
						   String trainingTypeId,String trainingSpeId,String sessionNumber,String sort,
						   String userName,String idNo,String graduationYear,Integer currentPage,HttpServletRequest request,Model model){
		SysUser sysuser=GlobalContext.getCurrentUser();
		SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
		if(currentPage==null){
			currentPage=1;
		}
		List<String> recTypeIds = new ArrayList<String>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
				recTypeIds.add(regType.getId());
			}
		}

		List<String>docTypeList=new ArrayList<String>();
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers!=null&&numbers.length>0){
				sessionNumbers=Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("orgLevel", orgLevel);
		parMp.put("orgFlow", orgFlow);
		parMp.put("docTypeList",docTypeList);
		parMp.put("jointOrgFlag", jointOrgFlag);
		parMp.put("typeId", trainingTypeId);
		parMp.put("speId", trainingSpeId);
		parMp.put("sessionNumber", sessionNumber);
		parMp.put("sessionNumbers", sessionNumbers);
		parMp.put("sort", sort);
		parMp.put("userName", userName);
		parMp.put("idNo", idNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("recTypeIds",recTypeIds);
        if (StringUtil.isNotBlank(roleFlag) && com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			parMp.put("orgCityId",org.getOrgCityId());
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> rltLst = processBiz.workList(parMp);
		model.addAttribute("rltLst", rltLst);
		return  "jsres/global/docWork/workList";
	}
	/**
	 * 医师工作量详情查询
	 * */
	@RequestMapping(value = {"/docWorkDetailSearch" })
	public String docWorkDetail (String userFlow,Model model,String recruitFlow) throws DocumentException {
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("userFlow", userFlow);
		parMp.put("recruitFlow", recruitFlow);
		ResDoctor resDoctor=resDoctorBiz.findByFlow(userFlow);
		com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
		parMp.put("orgFlow", recruit.getOrgFlow());
		//住院医师缩减调整
		boolean isReduction = false;
        if (com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(recruit.getCatSpeId())) {
			String sessionNumber = recruit.getSessionNumber();
			String trainingYears = recruit.getTrainYear();
            isReduction = com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(recruit.getCatSpeId());
			isReduction = isReduction && "2015".compareTo(sessionNumber) <= 0;
            isReduction = isReduction && (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(trainingYears) || com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(trainingYears));
		}
		String rotation_flow=recruit.getRotationFlow();
		if(StringUtil.isNotBlank(rotation_flow)) {
			parMp.put("rotationFlow", rotation_flow);
			if(isReduction)
			{
				int count=doctorDeptBiz.countSchDoctorDeptIgnoreStatus(userFlow,rotation_flow,recruit.getOrgFlow());
				if(count==0)
					isReduction=false;
			}
            parMp.put("isReduction", isReduction ? com.pinde.core.common.GlobalConstant.FLAG_Y : com.pinde.core.common.GlobalConstant.FLAG_N);
			List<Map<String, Object>> rltLst = processBiz.workDetail(parMp);
			if(rltLst!=null&&rltLst.size()>0)
			{
				Map<String,String> afterMap=new HashMap<>();
				Map<String,Object> countMap=new HashMap<>();
				for(Map<String,Object> map:rltLst) {
					String recordFlow = (String) map.get("recordFlow");
					Integer count= (Integer) countMap.get(recordFlow);
					if(count==null)
						count=1;
					else
						count++;
					countMap.put(recordFlow, count);
                    afterMap.put(recordFlow, com.pinde.core.common.GlobalConstant.FLAG_N);
					List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
//					ResRec rec = resRecBiz.queryResRec(recordFlow, userFlow, AfterRecTypeEnum.AfterSummary.getId());
					ResSchProcessExpress rec = expressBiz.queryResRec(recordFlow, userFlow, AfterRecTypeEnum.AfterSummary.getId());
					String content = null == rec ? "" : rec.getRecContent();
					if (StringUtil.isNotBlank(content)) {
						Document doc = DocumentHelper.parseText(content);
						Element root = doc.getRootElement();
						List<Element> imageEles = root.elements();
						if (imageEles != null && imageEles.size() > 0) {
							for (Element image : imageEles) {
								Map<String, Object> recContent = new HashMap<String, Object>();
								String imageFlow = image.attributeValue("imageFlow");
								List<Element> elements = image.elements();
								for (Element attr : elements) {
									String attrName = attr.getName();
									String attrValue = attr.getText();
									recContent.put(attrName, attrValue);
								}
								imagelist.add(recContent);
							}
						}
					}
					if (imagelist.size() > 0) {
                        afterMap.put(recordFlow, com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
				model.addAttribute("afterMap",afterMap);
				model.addAttribute("countMap",countMap);
			}
			model.addAttribute("rltLst", rltLst);
		}
		return "jsres/global/docWork/workDetail";
	}
}
