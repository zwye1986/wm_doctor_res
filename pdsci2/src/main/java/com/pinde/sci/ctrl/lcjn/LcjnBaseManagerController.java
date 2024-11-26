package com.pinde.sci.ctrl.lcjn;

import com.alibaba.fastjson.JSON;
import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnBaseManagerBiz;
import com.pinde.sci.biz.lcjn.ILcjnDoctorOrderInfoBiz;
import com.pinde.sci.biz.lcjn.ILcjnDoctorSignBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/lcjn/base")
public class LcjnBaseManagerController extends GeneralController {

	@Autowired
	private ILcjnBaseManagerBiz baseManagerBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ILcjnDoctorSignBiz lcjnDoctorSignBiz;
	@Autowired
	private ILcjnDoctorOrderInfoBiz lcjnDoctorOrderInfoBiz;

	/**
	 * 培训信息管理查询
	 */
	@RequestMapping("/courseTrainList")
	public String courseTrainList(Integer currentPage, String courseName, String trainStartDate, String trainEndDate, String isReleased, HttpServletRequest request, Model model){
		Map<String,String> param = new HashMap<>();
		param.put("courseName",courseName);
		param.put("trainStartDate",trainStartDate);
		param.put("trainEndDate",trainEndDate);
		param.put("isReleased",isReleased);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> dataList = baseManagerBiz.queryCourseList(param);
		model.addAttribute("dataList",dataList);
		return "lcjn/base/courseTrainList";
	}

	/**
	 * 新增/编辑课程信息列表界面
	 */
	@RequestMapping(value="/addCourseTrain")
	public String addCourseTrain(String courseFlow, String flag, Model model){
		if(StringUtil.isNotBlank(courseFlow)){
			LcjnCourseInfo course = baseManagerBiz.queryCourseByFlow(courseFlow);
			List<LcjnCourseSkill> skillsList = baseManagerBiz.queryCourseSkillsByFlow(courseFlow);
			List<LcjnCourseTime> timeList = baseManagerBiz.queryCourseTimeByFlow(courseFlow);
			List<LcjnCourseTea> teacherList = baseManagerBiz.queryCourseTeachersByFlow(courseFlow);
			model.addAttribute("course",course);
			model.addAttribute("skillsList",skillsList);
			model.addAttribute("timeList",timeList);
			model.addAttribute("teacherList",teacherList);
		}
		return "lcjn/base/addCourseTrain";
	}

	/**
	 * 新增培训课程所需技能查询
	 */
	@RequestMapping(value="/selectSkills")
	public String selectSkills(String courseFlow, Model model) {
		List<LcjnSkillCfg> skillsList = baseManagerBiz.queryCourseSkillsList();
		if(StringUtil.isNotBlank(courseFlow)){
			List<String> exitSkillsLst = new ArrayList<>();
			List<LcjnCourseSkill>  lscList = baseManagerBiz.queryCourseSkillsByFlow(courseFlow);
			if(null != lscList && lscList.size() > 0){
				for(int i=0; i< lscList.size(); i++){
					exitSkillsLst.add(lscList.get(i).getSkillFlow());
				}
			}
			model.addAttribute("exitSkillsLst",exitSkillsLst);
		}
		model.addAttribute("skillsList",skillsList);
		return "lcjn/base/courseSkills";
	}

	/**
	 * 新增培训课程所需任课老师查询
	 */
	@RequestMapping(value="/selectTeachers")
	public String selectTeachers(String courseFlow, Model model) {
		List<SysUser> teachersList = baseManagerBiz.queryCourseTeachersList();
		if(StringUtil.isNotBlank(courseFlow)){
			List<String> exitTeachersLst = new ArrayList<>();
			Map<String,String> teaMap = new HashMap<>();
			List<LcjnCourseTea>  lctList = baseManagerBiz.queryCourseTeachersByFlow(courseFlow);
			if(null != lctList && lctList.size() > 0){
				for(int i=0; i< lctList.size(); i++){
					exitTeachersLst.add(lctList.get(i).getUserFlow());
					teaMap.put(lctList.get(i).getUserFlow(),lctList.get(i).getTeachingCost());
				}
			}
			model.addAttribute("exitTeachersLst",exitTeachersLst);
			model.addAttribute("teaMap",teaMap);
		}
		model.addAttribute("teachersList",teachersList);
		return "lcjn/base/courseTeachers";
	}

	/**
	 * 新增编辑培训课程保存操作
	 */
	@RequestMapping("/saveCourseTrain")
	@ResponseBody
	public String saveCourseTrain(LcjnCourseInfo course, String [] skillRecordList, String [] skillFlowList, String [] skillNameList, String [] trainTimeRecordList, String [] trainStartDate, String []startTime, String [] trainEndDate, String [] endTime, String [] userRecordList, String [] userFlowList, String [] userNameList, String [] courseFee){
		Map<String,Object> param = new HashMap<>();
		param.put("course",course);
		param.put("skillRecordList", skillRecordList==null?null:Arrays.asList(skillRecordList));//新增时为null
		param.put("skillFlowList", Arrays.asList(skillFlowList));
		param.put("skillNameList", Arrays.asList(skillNameList));
		param.put("trainTimeRecordList", trainTimeRecordList==null?null:Arrays.asList(trainTimeRecordList));//新增时为null
		param.put("trainStartDate", Arrays.asList(trainStartDate));
		param.put("startTime", Arrays.asList(startTime));
		param.put("trainEndDate", Arrays.asList(trainEndDate));
		param.put("endTime", Arrays.asList(endTime));
		param.put("userRecordList", userRecordList==null?null:Arrays.asList(userRecordList));//新增时为null
		param.put("userFlowList", Arrays.asList(userFlowList));
		param.put("userNameList", Arrays.asList(userNameList));
		param.put("courseFee", Arrays.asList(courseFee.length == 0?new String[]{""}:courseFee));
		//验证：同一老师同一时间段不能添加多个课程
		String resultInfo = baseManagerBiz.validateTeaTimeCourse(param);
		if(!GlobalConstant.FLAG_Y.equals(resultInfo)){
			return resultInfo;
		}
		int num = baseManagerBiz.saveCourseTrain(param);
		if (num > 0) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 培训信息管理切换操作
	 */
	@RequestMapping("/courseTrainView")
	public String courseTrainView(String date, Model model) throws ParseException {//date:yyyy年MM月
		Map<String,Integer> firstDayMap = new HashMap<>();//key:fisrtDay，value:第几个工作日（周日开始）
		Map<String,String> lastDayMap = new HashMap<>();//key:lastDay，value:第几号
		Calendar c = Calendar.getInstance();
		if(StringUtil.isNotBlank(date)){
			c.setTime(new SimpleDateFormat("yyyy年MM月").parse(date));
		}else{
			date = new SimpleDateFormat("yyyy年MM月").format(c.getTime());
		}
		c.set(Calendar.DAY_OF_MONTH,c.getActualMinimum(Calendar.DAY_OF_MONTH));
		firstDayMap.put("firstDay",c.get(c.DAY_OF_WEEK));
		c.set(Calendar.DAY_OF_MONTH,c.getActualMaximum(Calendar.DAY_OF_MONTH));
		lastDayMap.put("lastDay",new SimpleDateFormat("dd").format(c.getTime()));
		String time = date.replaceAll("[\\u4e00-\\u9fa5]","-");
		List<LcjnCourseTime> timeList = baseManagerBiz.queryCourseTimeList(time);
		model.addAttribute("firstDayMap",firstDayMap);
		model.addAttribute("lastDayMap",lastDayMap);
		model.addAttribute("timeList",timeList);
		model.addAttribute("date",date);
		model.addAttribute("year",time.split("-")[0]);
		model.addAttribute("month",time.split("-")[1]);
		return "lcjn/base/courseTrainView";
	}

	/**
	 * 课程信息发布操作
	 */
	@RequestMapping("/releasedInfo")
	@ResponseBody
	public String releasedInfo(String courseFlow){
		int num = baseManagerBiz.releasedInfo(courseFlow);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 课程信息删除操作
	 */
	@RequestMapping("/delCourseTrain")
	@ResponseBody
	public String delCourseTrain(String courseFlow,String isReleased){
		int num = baseManagerBiz.delCourseTrain(courseFlow,isReleased);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.DELETE_SUCCESSED;
		}else if(num == -1){
			return "此课程已在培训中，无法删除！";
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 课程信息跳转页面（默认学员信息标签页）
	 */
	@RequestMapping("/courseTrainManage")
	public String checkInfoManage(Integer currentPage1,String auditStatusId,String courseFlow,String courseName,String initFlag,HttpServletRequest request,Model model) throws IOException {
		Map<String,String> param = new HashMap<>();
		param.put("auditStatusId",auditStatusId);
		param.put("courseFlow",courseFlow);
		courseName = java.net.URLDecoder.decode(courseName,"UTF-8");
		PageHelper.startPage(currentPage1,getPageSize(request));
		//学员信息标签页功能代码
		List<Map<String,Object>> doctorOrderInfoList=lcjnDoctorOrderInfoBiz.selectDoctorOrderInfoList(param);
		model.addAttribute("doctorOrderInfoList",doctorOrderInfoList);
		model.addAttribute("courseFlow",courseFlow);
		model.addAttribute("courseName",courseName);
		if(StringUtil.isNotBlank(initFlag) && initFlag.equals(GlobalConstant.RECORD_STATUS_Y)){
			return "lcjn/base/courseTrainManage";
		}
		return "lcjn/doctorInfoManage/doctorOrderedInfo";
	}

	/**
	 * 师资管理
	 */
	@RequestMapping(value="/teacherList")
	public String teacherList(Integer currentPage, SysUser user, Model model) {
		PageHelper.startPage(currentPage,10);
		List<SysUser> userList = baseManagerBiz.querySysUser(user);
		model.addAttribute("userList",userList);
		return "lcjn/base/teacherList";
	}

	/**
	 * 新增/编辑老师信息界面
	 */
	@RequestMapping(value="/editTeaInfo")
	public String editTeaInfo(String userFlow, Model model){
		if(StringUtil.isNotBlank(userFlow)){
			SysUser sysUser = userBiz.readSysUser(userFlow);
			model.addAttribute("sysUser",sysUser);
		}
		return "lcjn/base/editTeaInfo";
	}

	/**
	 * 新增/编辑老师信息保存操作
	 */
	@RequestMapping("/saveTeaInfo")
	@ResponseBody
	public String saveTeaInfo(SysUser user){
		int num = baseManagerBiz.saveTeaInfo(user);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.SAVE_SUCCESSED;
		}else if(num == -1){
			return "此用户名已维护过！";
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 师资信息导入
	 */
	@RequestMapping(value="/importTeacherExcel")
	@ResponseBody
	public String importTeacherExcel(MultipartFile file) throws Exception{
		if(file.getSize() > 0){
			try{
				int result = baseManagerBiz.importTeacherExcel(file);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(Exception re){
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 师资信息导出操作
	 */
	@RequestMapping(value="/expTeacher", method=RequestMethod.POST)
	public void expTeacher(SysUser user, HttpServletResponse response) throws Exception {
		List<SysUser> usertList = baseManagerBiz.querySysUser(user);
		String[] titles = new String[]{
				"userName:姓名",
				"sexName:性别",
				"titleName:职称",
				"userPhone:联系方式",
				"workOrgName:所在单位"
		};
		String fileName = "师资信息.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcle(titles,usertList,SysUser.class,response.getOutputStream());
	}

	/**
	 * 启用/停用老师操作
	 */
	@RequestMapping(value="/teacherOption")
	@ResponseBody
	public String teacherOption(String userFlow, String recordStatus) {
        int num = baseManagerBiz.teacherOption(userFlow, recordStatus.equals(GlobalConstant.FLAG_Y) ? GlobalConstant.FLAG_N : GlobalConstant.FLAG_Y);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 一键停用老师操作
	 */
	@RequestMapping("/stopTeaOption")
	@ResponseBody
	public String stopTeaOption(String jsonData){
		Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
		List<String> recordLst = (List<String>)mp.get("recordLst");
		String recordStatus = (String)mp.get("recordStatus");
		int num = 0;
		if(null != recordLst && recordLst.size() > 0){
			for (int i = 0; i < recordLst.size(); i++) {
				num += baseManagerBiz.teacherOption(recordLst.get(i),recordStatus);
			}
		}
		if (num > 0) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 技能配置管理
	 */
	@RequestMapping(value="/skillsConfigList")
	public String skillsConfigList(Integer currentPage, Model model) {
		PageHelper.startPage(currentPage,10);
		List<Map<String,Object>> dataList = baseManagerBiz.querySkillConfigList();
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId("SkillMaterial");//耗材
		sysDict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDict> materialLst = baseManagerBiz.searchStartDictList(sysDict);
		sysDict.setDictTypeId("SkileFixedAssets");//固定资产
		List<SysDict> fixedAssetsLst = baseManagerBiz.searchStartDictList(sysDict);
		model.addAttribute("dataList",dataList);
		model.addAttribute("materialLst",materialLst);
		model.addAttribute("fixedAssetsLst",fixedAssetsLst);
		return "lcjn/base/skillsConfigList";
	}

	/**
	 * 新增/编辑技能配置界面
	 */
	@RequestMapping(value="/addSkillOption")
	public String addSkillOption(String skillFlow, Model model) {
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId("LcjnSkill");//课程技能
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysDict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDict> skillList = dictBiz.searchDictList(sysDict);
		if(StringUtil.isNotBlank(skillFlow)){
			LcjnSkillCfg lsc = baseManagerBiz.querySkillByFlow(skillFlow);
			model.addAttribute("lsc",lsc);
			List<Map<String,Object>>  detailList = baseManagerBiz.queryConfigDetailByFlow(skillFlow);
			model.addAttribute("detailList",detailList);

		}
		model.addAttribute("skillList",skillList);
		return "lcjn/base/addSkillConfig";
	}

	/**
	 * 新增技能所需耗材及固定资产查询
	 */
	@RequestMapping(value="/selectMaterial")
	public String selectMaterial(String skillFlow, String flag, Model model) {
		List<LcjnSupplies> suppliesList = baseManagerBiz.querySuppliesList();
		List<LcjnFixedAssets> assetsList = baseManagerBiz.queryFixedAssetsList();
		SysDict sysDict = new SysDict();
		sysDict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		sysDict.setDictTypeId("SkillMaterial");
		List<SysDict> startSupplies = this.dictBiz.searchDictList(sysDict);//追加需求：排除停用的耗材
		sysDict.setDictTypeId("SkileFixedAssets");
		List<SysDict> startAssets = this.dictBiz.searchDictList(sysDict);//追加需求：排除停用的固定资产
		List<String> suppliesLst = new ArrayList<>();
		List<String> assetsLst = new ArrayList<>();
		if(null != startSupplies && startSupplies.size() > 0){
			for(int i=0; i< startSupplies.size(); i++){
				if(!suppliesLst.contains(startSupplies.get(i).getDictId())){
					suppliesLst.add(startSupplies.get(i).getDictId());
				}
			}
		}
		if(null != startAssets && startAssets.size() > 0){
			for(int i=0; i< startAssets.size(); i++){
				if(!assetsLst.contains(startAssets.get(i).getDictId())){
					assetsLst.add(startAssets.get(i).getDictId());
				}
			}
		}
		if(StringUtil.isNotBlank(skillFlow)){
			Map<String,String> materialMap = new HashMap<>();
			List<String> exitMaterialLst = new ArrayList<>();
			List<LcjnSkillCfgDetail>  lscdList = baseManagerBiz.querySkillDetailByFlow(skillFlow);
			if(null != lscdList && lscdList.size() > 0){
				for(int i=0; i< lscdList.size(); i++){
					exitMaterialLst.add(lscdList.get(i).getMateriaFlow());
					materialMap.put(lscdList.get(i).getMateriaFlow(),lscdList.get(i).getUseNum());
				}
			}
			model.addAttribute("exitMaterialLst",exitMaterialLst);
			model.addAttribute("materialMap",materialMap);
		}
		model.addAttribute("suppliesList",suppliesList);
		model.addAttribute("startSupplies",suppliesLst);
		model.addAttribute("assetsList",assetsList);
		model.addAttribute("startAssets",assetsLst);
		return "lcjn/base/skillsDetail";
	}

	/**
	 * 新增技能配置保存操作
	 */
	@RequestMapping("/saveSkillConfig")
	@ResponseBody
	public String saveSkillConfig(String skillFlow, String skillId, String skillName, String [] suppliesRecordList, String [] suppliesList, String [] suppliesNum, String [] assetsRecordList, String [] assetsList, String [] assetsNum){
		Map<String,Object> param = new HashMap<>();
		param.put("skillFlow",skillFlow);
		param.put("skillId",skillId);
		param.put("skillName",skillName);
		param.put("suppliesRecordList", suppliesRecordList==null?null:Arrays.asList(suppliesRecordList));//新增时为null
		param.put("suppliesList", Arrays.asList(suppliesList));
		param.put("suppliesNumList", Arrays.asList(suppliesNum.length == 0?new String[]{""}:suppliesNum));
		param.put("assetsRecordList", assetsRecordList==null?null:Arrays.asList(assetsRecordList));//新增时为null
		param.put("assetsList", Arrays.asList(assetsList));
		param.put("assetsNumList", Arrays.asList(assetsNum.length == 0?new String[]{""}:assetsNum));
		int num = baseManagerBiz.saveSkillConfig(param);
		if (num > 0) {
			return GlobalConstant.SAVE_SUCCESSED;
		}else if(num == -1){
			return "该技能已配置过！";
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 技能配置信息删除操作
	 */
	@RequestMapping("/delSkillsConfig")
	@ResponseBody
	public String delSkillsConfig(String skillFlow){
		int num = baseManagerBiz.delSkillsConfig(skillFlow);
		if (num > 0) {
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 字典管理
	 */
	@RequestMapping(value="/dictList" )
	public ModelAndView selectDictList(SysDict sysDict) {
		ModelAndView mav = new ModelAndView("lcjn/base/dictManager");
		if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
			sysDict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			sysDict.setOrgName(GlobalContext.getCurrentUser().getOrgName());
			List<SysDict> dictList = this.dictBiz.searchDictList(sysDict);
			mav.addObject("dictList",dictList);
		}
		return mav;
	}
	@RequestMapping(value="/edit")
	public ModelAndView edit(@RequestParam(value="dictFlow",required=false) String dictFlow) {
		ModelAndView mav = new ModelAndView("lcjn/base/editDict");
		if(StringUtil.isNotBlank(dictFlow)){
			SysDict dict = this.dictBiz.readDict(dictFlow);
			mav.addObject("dict" , dict);
		}
		return mav;
	}
	@RequestMapping(value="/save" , method=RequestMethod.POST)
	public @ResponseBody String saveDict(SysDict dict) {
		dict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		dict.setOrgName(GlobalContext.getCurrentUser().getOrgName());
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
		InitConfig.refresh(request.getServletContext());
		return "刷新成功！";
	}

	@RequestMapping(value="/importDictExcel")
	@ResponseBody
	public String importDictExcel(MultipartFile file,String dictTypeId){
		if(file.getSize() > 0){
			try{
				int result = dictBiz.importDict(file,dictTypeId);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re) {
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}
}