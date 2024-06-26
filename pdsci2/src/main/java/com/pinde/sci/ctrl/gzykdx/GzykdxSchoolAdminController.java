package com.pinde.sci.ctrl.gzykdx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxAuditManageBiz;
import com.pinde.sci.biz.gzykdx.IGzykdxSchoolAdminBiz;
import com.pinde.sci.biz.gzykdx.IGzykdxTeaAndDocBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.form.gzykdx.GzykdxRecruitTargetPlanForm;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/gzykdx/school")
public class GzykdxSchoolAdminController extends GeneralController {

	@Autowired
	private IGzykdxSchoolAdminBiz schoolAdminBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IOrgBiz sysOrgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IGzykdxAuditManageBiz auditManageBiz;
	@Autowired
	private IGzykdxTeaAndDocBiz teaAndDocBiz;

	/**
	 * 招生指标计划
	 */
	@RequestMapping(value="/recruitTargetPlan" )
	public String recruitTargetPlan(String recruitYear, Model model) {
		List<RecruitTargetMain> recruitTargetList = schoolAdminBiz.queryRecruitTargetList(recruitYear);
		model.addAttribute("dataList",recruitTargetList);
		return "gzykdx/school/recruitPlan";
	}
	@RequestMapping(value="/addRecruitPlan")
	public String addRecruitPlan(String rargetFlow, Model model){
		if(StringUtil.isNotBlank(rargetFlow)){
			RecruitTargetMain recTarget = schoolAdminBiz.queryRecTargetByFlow(rargetFlow);
			model.addAttribute("recTarget",recTarget);
		}
		return "/gzykdx/school/addRecruitPlan";
	}
	@RequestMapping("/saveRecruitPlan")
	@ResponseBody
	public String saveRecruitPlan(RecruitTargetMain recTarget){
		int num = schoolAdminBiz.saveRecruitPlan(recTarget);
		if (num > 0) {
			return GlobalConstant.SAVE_SUCCESSED;
		}else if(num == -1){
			return "该年招生计划已维护！";
		}
		return GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping(value="/allocateDetail")
	public String allocateDetail(String rargetFlow, String orgFlow, Model model){
		RecruitTargetMain recTarget = schoolAdminBiz.queryRecTargetByFlow(rargetFlow);
		model.addAttribute("recTarget",recTarget);
		List<SysUser> userList = schoolAdminBiz.queryOrgAdminPowerList(orgFlow);
		model.addAttribute("dataList",userList);
		Map<String,RecruitTargetMainDetail> orgTargetMap = new HashMap<>();
		List<RecruitTargetMainDetail> detailList = schoolAdminBiz.queryRecDetailByFlow(rargetFlow);
		if(null != detailList && detailList.size() >0 ){
			for(RecruitTargetMainDetail detail : detailList){
				if(!orgTargetMap.containsKey(detail.getOrgFlow())){
					orgTargetMap.put(detail.getOrgFlow(),detail);
				}
			}
			model.addAttribute("orgTargetMap",orgTargetMap);
			List<RecruitTargetMainDetailLog> detailLogs = schoolAdminBiz.queryDetailLogs();
			model.addAttribute("detailLogList",detailLogs);
		}
		return "/gzykdx/school/allocateDetail";
	}
	@RequestMapping("/saveOrgTarget")
	@ResponseBody
	public String saveOrgTarget(RecruitTargetMainDetail detail){
		int num = schoolAdminBiz.saveOrgTarget(detail);
		if (num > 0) {
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}
	@RequestMapping("/searchUseTarget")
	@ResponseBody
	public int searchUseTarget(String recruitYear, String orgFlow, String degreeTypeId){
		return schoolAdminBiz.targetNumOfOrgByYear(recruitYear,orgFlow,degreeTypeId);
	}
	@RequestMapping("/saveChangeOrgTarget")
	@ResponseBody
	public String saveChangeOrgTarge(GzykdxRecruitTargetPlanForm form){//因detail/log表有相同字段而设计
		int num = schoolAdminBiz.saveChangeOrgTarge(form.getDetail(),form.getLog());
		if (num > 0) {
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}
	@RequestMapping("/searchUseTargetSum")
	@ResponseBody
	public Map<String, Object> searchUseTargetSum(String rargetFlow){
		return schoolAdminBiz.searchUseTargetSum(rargetFlow);
	}


	@RequestMapping("/releasedInfo")
	@ResponseBody
	public String releasedInfo(String rargetFlow){
		int num = schoolAdminBiz.releasedInfo(rargetFlow);
		if (num > 0) {
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 招生目录发布
	 */
	@RequestMapping(value="/recruitCatalogRelease" )
	public String d(TeacherTargetApply target, Integer currentPage, Model model) {
		PageHelper.startPage(currentPage, 10);
		List<Map<String,Object>> dataList = schoolAdminBiz.passedTeacherApplyList(target);
		model.addAttribute("currentYear",new SimpleDateFormat("yyyy").format(new Date()));
		model.addAttribute("dataList",dataList);
		model.addAttribute("currentPage",currentPage);
		return "gzykdx/school/recruitCatalog";
	}
	@RequestMapping("/releasedTarget")
	@ResponseBody
	public String releasedTarget(String recruitYear){
		int num = schoolAdminBiz.releasedPassedTarget(recruitYear);
		if (num > 0) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 二级机构管理
	 */
	@RequestMapping(value="/affiliatedOrgManage" )
	public String affiliatedOrgManage(String orgFlow, Integer currentPage, Model model) {
		PageHelper.startPage(currentPage, 10);
		List<SysUser> userList = schoolAdminBiz.queryOrgAdminList(orgFlow);
		model.addAttribute("dataList",userList);
		return "gzykdx/school/affiliatedOrg";
	}
	@RequestMapping(value="/addAffiliatedOrg")
	public String addAffiliatedOrg(String userFlow, Model model){
		if(StringUtil.isNotBlank(userFlow)){
			SysUser sysUser = userBiz.findByFlow(userFlow);
			model.addAttribute("sysUser",sysUser);
		}
		return "/gzykdx/school/addAffiliatedOrg";
	}
	@RequestMapping("/saveAffiliatedOrg")
	@ResponseBody
	public String saveAffiliatedOrg(SysUser user){
		int num = schoolAdminBiz.saveAffiliatedOrg(user);
		if (num > 0) {
			return GlobalConstant.SAVE_SUCCESSED;
		}else if(num == -1){
			return "此用户名已存在！";
		}else if(num == -2){
			return "此二级机构已存在管理员账户！";
		}else if(num == -3){
			return "系统二级机构角色未设置！";
		}
		return GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping("/resetPwd")
	@ResponseBody
	public String resetPwd(String userFlow){
		int num = schoolAdminBiz.resetOrgAdminPwd(userFlow);
		if (num > 0) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 权限设置
	 */
	@RequestMapping(value="/powerManage" )
	public String powerManage(String orgFlow, Model model) {
		List<SysUser> userList = schoolAdminBiz.queryOrgAdminList(orgFlow);
		model.addAttribute("dataList",userList);
		return "gzykdx/school/powerManage";
	}
	@RequestMapping(value="/settingOption")
	public String settingOption(String orgFlow, Model model){
		List<SysUser> userList = schoolAdminBiz.queryTeachersInOrg(orgFlow,InitConfig.getSysCfg("gzykdx_teacher_role_flow"),null);
		model.addAttribute("dataList",userList);
		return "/gzykdx/school/settingOption";
	}
	@RequestMapping("/changePower")
	@ResponseBody
	public String changePower(String target, String targetFlow, String powerFlag){
		int num = schoolAdminBiz.updateDeclarePower(target,targetFlow,powerFlag);
		if (num > 0) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping(value="/searchByName" )
	public String searchByName(String orgFlow, String userName, Model model) {
		List<SysUser> userList = schoolAdminBiz.queryTeachersInOrg(orgFlow,InitConfig.getSysCfg("gzykdx_teacher_role_flow"),userName);
		model.addAttribute("dataList",userList);
		return "gzykdx/school/settingOption";
	}

	/**
	 * 招录设置
	 */
	@RequestMapping(value="/recruitSetting" )
	public String recruitSetting() {
		return "gzykdx/school/recruitSetting";
	}
	@RequestMapping("/updateDate")
	@ResponseBody
	public String updateDate(String [] cfgCode, String [] cfgValue, HttpServletRequest request){
		List<SysCfg> cfgList = new ArrayList<>();
		List<String> codeList = Arrays.asList(cfgCode);
		for(int i=0; i<codeList.size(); i++){
			SysCfg cfg = new SysCfg();
			cfg.setCfgCode(codeList.get(i));
			cfg.setCfgValue(cfgValue.length==0?"":cfgValue[i]);
			cfg.setWsId("gzykdx");
			cfg.setWsName("广州医科大学研究生招录配置");
			cfgList.add(cfg);
		}
		int num = schoolAdminBiz.saveSysCfg(cfgList);
		if (num > 0) {
			InitConfig._loadSysCfg(request.getServletContext());
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 字典管理
	 */
	@RequestMapping(value="/dictManage" )
	public ModelAndView selectDictList(SysDict sysDict) {
		ModelAndView mav = new ModelAndView("gzykdx/school/dictManage");
		if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
			List<SysDict> dictList = this.dictBiz.searchDictList(sysDict);
			mav.addObject("dictList",dictList);
		}
		return mav;
	}
	@RequestMapping(value="/edit")
	public ModelAndView edit(@RequestParam(value="dictFlow",required=false) String dictFlow) {
		ModelAndView mav = new ModelAndView("gzykdx/school/editDict");
		if(StringUtil.isNotBlank(dictFlow)){
			SysDict dict = this.dictBiz.readDict(dictFlow);
			mav.addObject("dict" , dict);
		}
		return mav;
	}
	@RequestMapping(value="/save" , method= RequestMethod.POST)
	public @ResponseBody
	String saveDict(SysDict dict) {
		dict.setDictTypeName(DictTypeEnum.valueOf(dict.getDictTypeId()).getName());
		if(StringUtil.isBlank(dict.getDictFlow())){
			//新增字典时判断该类型字典代码是否存在
			SysDict sysDict=dictBiz.readDict(dict.getDictTypeId(),dict.getDictId(),dict.getOrgFlow());
			if(null!=sysDict){
				return GlobalConstant.OPRE_FAIL_FLAG;
			}
			this.dictBiz.addDict(dict);
		}else{
			//修改字典时，字典代码可以与自身相同，可以是新ID，但不能与其他字典相同
			List<SysDict> dictList=dictBiz.searchDictListByDictTypeIdNotIncludeSelf(dict);
			for(SysDict sysDict:dictList){
				if(dict.getDictId().equals(sysDict.getDictId())){
					return GlobalConstant.OPRE_FAIL_FLAG;
				}
			}
			this.dictBiz.modeDictAndSubDict(dict);
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/doRefresh" , method=RequestMethod.GET)
	@ResponseBody
	public String doRefresh(HttpServletRequest request) {
		InitConfig.refreshDict(request.getServletContext());
		return "刷新成功！";
	}

	/**
	 * 机构维护
     */
	@RequestMapping(value="/list",method={RequestMethod.POST,RequestMethod.GET})
	public String list(SysOrg org, Integer currentPage, String flag, HttpServletRequest request, Model model){
		if(StringUtil.isNotBlank(flag) && flag.equals("second")){
			org.setIsSecondFlag(GlobalConstant.RECORD_STATUS_Y);//二级机构标识
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysOrg> sysList=sysOrgBiz.searchOrg(org);
		model.addAttribute("sysList",sysList);
		return "gzykdx/school/list";
	}
	@RequestMapping(value="/jgEdit",method={RequestMethod.GET})
	public String edit(String orgFlow,Model model){
		if(StringUtil.isNotBlank(orgFlow)){
			SysOrg sysOrg=sysOrgBiz.readSysOrg(orgFlow);
			model.addAttribute("sysOrg", sysOrg);
		}
		return "gzykdx/school/edit";
	}
	@RequestMapping(value={"/jgSave"},method=RequestMethod.POST)
	@ResponseBody
	public String save(SysOrg org,HttpServletRequest request) throws Exception{
		if (StringUtil.isNotBlank(org.getOrgTypeId())) {
			org.setOrgTypeName(OrgTypeEnum.getNameById(org.getOrgTypeId()));
		}
		if(StringUtil.isNotBlank(org.getOrgLevelId())){
			org.setOrgLevelName(OrgLevelEnum.getNameById(org.getOrgLevelId()));
		}
		if(StringUtil.isBlank(org.getOrgFlow())){
			org.setIdentifyNo(getRandomCharAndNumr(6));
		}
		sysOrgBiz.saveOrg(org);
		InitConfig._loadOrg(request.getServletContext());
		return GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/jgDelete",method=RequestMethod.GET)
	@ResponseBody
	public String delete(SysOrg org,HttpServletRequest request){
		sysOrgBiz.saveOrg(org);
		InitConfig._loadOrg(request.getServletContext());
		return GlobalConstant.OPERATE_SUCCESSED;
	}

	/**
	 * 生成数字字母组合
	 * @param length
	 * @return
     */
	public String getRandomCharAndNumr(Integer length) {
		String str = "";
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			boolean b = random.nextBoolean();
			if (b) { // 字符串
				// int choice = random.nextBoolean() ? 65 : 97; 取得65大写字母还是97小写字母
				str += (char) (65 + random.nextInt(26));// 取得大写字母
			} else { // 数字
				str += String.valueOf(random.nextInt(10));
			}
		}
		return str;
	}

	/**
	 * 招生目录修改
	 * @param year
	 * @param applyFlow
	 * @param orgFlow
	 * @param type
	 * @param val
     * @return
     */
	@RequestMapping(value="/modifyRecruitPlan",method=RequestMethod.POST)
	@ResponseBody
	public String modifyRecruitPlan(String year,String applyFlow,String orgFlow,String type,String val){
		TeacherTargetApply apply=new TeacherTargetApply();
		apply.setApplyFlow(applyFlow);
		RecruitLog log=new RecruitLog();
		int surplus=0;
		String oldData="";
		Map<String,String> map1=new HashMap<>();
		map1.put("orgFlow",orgFlow);
		map1.put("recruitYear",year);
		List<Map<String,String>> recruitNumList=auditManageBiz.selectRecruitNum(map1);//机构总指标
		map1.put("applyFlow",applyFlow);
		if("academicNum".equals(type)){
			apply.setAcademicNum(val);
			map1.put("isAcademic",GlobalConstant.RECORD_STATUS_Y);
			int academicSum=auditManageBiz.selectAcademicRecruitSum(map1);//学术型已用指标
			if(recruitNumList!=null&&recruitNumList.size()>0){
				String academicNum=recruitNumList.get(0).get("ACADEMIC_NUM");
				surplus=Integer.parseInt(academicNum)-academicSum;
			}
		}
		if("specializedNum".equals(type)){
			apply.setSpecializedNum(val);
			map1.put("isSpecialized",GlobalConstant.RECORD_STATUS_Y);
			int specializedSum=auditManageBiz.selectSpecializedRecruitSum(map1);//专业型已用指标
			if(recruitNumList!=null&&recruitNumList.size()>0){
				String specializedNum=recruitNumList.get(0).get("SPECIALIZED_NUM");
				surplus=Integer.parseInt(specializedNum)-specializedSum;
			}
		}
		if(StringUtil.isNotBlank(val)){
			if(Integer.parseInt(val)>surplus){
				return GlobalConstant.NULL;
			}else{
				List<TeacherTargetApply> applyList=teaAndDocBiz.selectTargetApplyList(apply);
				if(applyList!=null&&applyList.size()>0&&applyList.get(0)!=null){
					if("academicNum".equals(type)){
						oldData=applyList.get(0).getAcademicNum();
					}
					if("specializedNum".equals(type)){
						oldData=applyList.get(0).getSpecializedNum();
					}
				}
				GeneralMethod.setRecordInfo(apply,false);
				teaAndDocBiz.editTeacherTargetApply(apply);
				log.setApplyFlow(applyFlow);
				log.setChangeItem(type);
				log.setChangeTime(DateUtil.getCurrDateTime());
				log.setChangeUserCode(GlobalContext.getCurrentUser().getUserCode());
				log.setChangeUserFlow(GlobalContext.getCurrentUser().getUserFlow());
				log.setNewData(val);
				log.setOldData(oldData);
				log.setWsId(GlobalContext.getCurrentWsId());
				log.setWsName(InitConfig.getWorkStationName(GlobalContext.getCurrentWsId()));
				log.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				schoolAdminBiz.saveRecruitLog(log);
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/recruitLogList",method={RequestMethod.POST,RequestMethod.GET})
	public String recruitLogList(String recruitYear,String userName,String startChangeTime,String endChangeTime,Integer currentPage,HttpServletRequest request, Model model){
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> logs=schoolAdminBiz.searchRecruitLogList(recruitYear,userName,startChangeTime,endChangeTime);
		model.addAttribute("logs",logs);
		return "/gzykdx/school/recruitModifyLog";
	}
}