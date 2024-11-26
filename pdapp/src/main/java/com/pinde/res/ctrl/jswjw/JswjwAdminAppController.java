package com.pinde.res.ctrl.jswjw;

import com.pinde.core.common.enums.*;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.jswjw.IJswjwAdminBiz;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IResLiveTrainingBiz;
import com.pinde.res.model.jswjw.mo.JsResDoctorOrgHistoryExt;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/res/jswjw/admin")
public class JswjwAdminAppController {
	private static Logger logger = LoggerFactory.getLogger(JswjwAdminAppController.class);


	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/jswjw/500";
    }

	@Autowired
	private IJswjwBiz jswjwBiz;
	@Autowired
	private IResLiveTrainingBiz resLiveTrainingBiz;
	@Autowired
	private IJswjwAdminBiz jswjwAdminBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/jswjw/admin/test";
	}
	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String deptFlow,String cataFlow,String funcTypeId , String funcFlow , String dataFlow , HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("deptFlow", deptFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/jswjw/admin/test";
	}


	/**
	 * 科室列表
	 * @param userFlow
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/deptList"},method={RequestMethod.POST})
	public String deptList(String userFlow,String deptName,HttpServletRequest request,HttpServletResponse response,
						   Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/deptList";
		}
		SysUser user=jswjwBiz.readSysUser(userFlow);
		PageHelper.startPage(pageIndex, pageSize);
		List<SysDept> depts=jswjwBiz.getOrgDeptList(user.getOrgFlow(),deptName);
		Map<String,Object> deptInfo=new HashMap<>();
		if(depts!=null&&depts.size()>0)
		{
			for(SysDept dept:depts) {
				List<Map<String, String>> resDoctorSchProcess = null;
				Map<String, String> schArrangeResultMap = new HashMap<String, String>();
				schArrangeResultMap.put("deptFlow", dept.getDeptFlow());
				//本月轮转的学员
				schArrangeResultMap.put("typeId", "monthCurrent");
				resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
				if (resDoctorSchProcess != null && !resDoctorSchProcess.isEmpty()) {
					deptInfo.put(dept.getDeptFlow()+"monthCurrent", resDoctorSchProcess.size());
				} else {
					deptInfo.put(dept.getDeptFlow()+"monthCurrent", 0);
				}
				//待出科学员
				schArrangeResultMap.put("typeId", "monthSch");
				resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
				if (resDoctorSchProcess != null && !resDoctorSchProcess.isEmpty()) {
					deptInfo.put(dept.getDeptFlow()+"monthSch", resDoctorSchProcess.size());
				} else {
					deptInfo.put(dept.getDeptFlow()+"monthSch", 0);
				}
				//待入科学员
				schArrangeResultMap.put("typeId", "waitSch");
				schArrangeResultMap.put("yearMonth", DateUtil.addMonth(DateUtil.getMonth(), 1));
				resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
				if (resDoctorSchProcess != null && !resDoctorSchProcess.isEmpty()) {
					deptInfo.put(dept.getDeptFlow()+"waitSch", resDoctorSchProcess.size());
				} else {
					deptInfo.put(dept.getDeptFlow()+"waitSch", 0);
				}
			}
		}
		model.addAttribute("deptInfo", deptInfo);
		model.addAttribute("depts", depts);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/admin/deptList";
	}

	/**
	 * 科室人员信息
	 * @param userFlow
	 * @param deptFlow
	 * @param model
     * @return
     */
	@RequestMapping(value={"/deptStuDetail"},method={RequestMethod.POST})
	public String deptStuDetail(String userFlow,String deptFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/deptStuDetail";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jswjw/admin/deptStuDetail";
		}
		SysUser user=jswjwBiz.readSysUser(userFlow);

		List<Map<String,String>> resDoctorSchProcess=null;

		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", deptFlow);

		//本月轮转的学员
		schArrangeResultMap.put("typeId", "monthCurrent");
		resDoctorSchProcess=jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
		if(resDoctorSchProcess!=null&&!resDoctorSchProcess.isEmpty())
		{
			model.addAttribute("monthCurrent",resDoctorSchProcess.size());
		}else{
			model.addAttribute("monthCurrent",0);
		}
		//待出科学员
		schArrangeResultMap.put("typeId", "monthSch");
		resDoctorSchProcess=jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
		if(resDoctorSchProcess!=null&&!resDoctorSchProcess.isEmpty())
		{
			model.addAttribute("monthSch",resDoctorSchProcess.size());
		}else{
			model.addAttribute("monthSch",0);
		}
		//待入科学员
		schArrangeResultMap.put("typeId", "waitSch");
		schArrangeResultMap.put("yearMonth", DateUtil.addMonth(DateUtil.getMonth(),1));
		resDoctorSchProcess=jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
		if(resDoctorSchProcess!=null&&!resDoctorSchProcess.isEmpty())
		{
			model.addAttribute("waitSch",resDoctorSchProcess.size());
		}else{
			model.addAttribute("waitSch",0);
		}
		return "res/jswjw/admin/deptStuDetail";
	}

	/**
	 * 学员列表
	 * @param userFlow
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/studentList"},method = {RequestMethod.POST})
	public String studentList(String userFlow,String searchStr,String deptFlow,
							  String typeId,String yearMonth,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/studentList";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jswjw/admin/studentList";
		}
		if(StringUtil.isBlank(typeId))
		{
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "学员列表类型为空");
			return "res/jswjw/admin/studentList";
		}
		if(!"waitSch".equals(typeId)&&!"monthCurrent".equals(typeId)&&!"monthSch".equals(typeId))
		{
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "学员列表类型不正确,只能是monthCurrent，monthSch，waitSch");
			return "res/jswjw/admin/studentList";
		}
		if("waitSch".equals(typeId))
		{
			yearMonth=DateUtil.addMonth(DateUtil.getMonth(),1);
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/admin/studentList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/admin/studentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/admin/studentList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", deptFlow);
		schArrangeResultMap.put("searchStr", searchStr);
		schArrangeResultMap.put("typeId", typeId);
		schArrangeResultMap.put("yearMonth", yearMonth);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		addUploadUrl(model);
		return "res/jswjw/admin/studentList";
	}

	/**
	 * 出科异常学员列表
	 * @param userFlow
	 * @param pageIndex
	 * @param pageSize
	 * @param model
     * @return
     * @throws DocumentException
     */
	@RequestMapping(value = {"/errorStudentList"},method = {RequestMethod.POST})
	public String errorStudentList(String userFlow,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/errorStudentList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/admin/errorStudentList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/admin/errorStudentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/admin/errorStudentList";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<SysDept> depts=jswjwAdminBiz.getErrorSchDepts(userinfo.getOrgFlow());
		Map<String,List<Map<String, String>>> processMap=new HashMap<>();
		for (SysDept dept:depts) {
			List<Map<String, String>> resDoctorSchProcess = null;
			Map<String, String> schArrangeResultMap = new HashMap<String, String>();
			schArrangeResultMap.put("deptFlow", dept.getDeptFlow());
			schArrangeResultMap.put("typeId", "errorSch");
			resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
			processMap.put(dept.getDeptFlow(),resDoctorSchProcess);
		}
		model.addAttribute("depts",depts);
		model.addAttribute("processMap",processMap);
		model.addAttribute("dataCount", PageHelper.total);
		addUploadUrl(model);
		return "res/jswjw/admin/errorStudentList";
	}

	/**
	 * 发送通知
	 * @param userFlow
	 * @param request
	 * @param model
     * @return
     */
	@RequestMapping(value={"/saveSchErrorNotice"},method={RequestMethod.POST})
	public String saveSchErrorNotice(String userFlow,String doctorFlow,String content,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(doctorFlow.isEmpty()){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "学员标识符列表为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(content)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "消息内容不能为空");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		List<String> doctorFlowss=Arrays.asList(doctorFlow.split(","));
		List<String> doctorFlows = new ArrayList<String>(new HashSet<String>(doctorFlowss));
		jswjwAdminBiz.sendErrorSchNotice(doctorFlows,content,user);
		return "res/jswjw/success";
	}

	/**
	 * 统计查询首页
	 * @param userFlow
	 * @param sessionNumber
	 * @param request
	 * @param model
     * @return
     */
	@RequestMapping(value={"/statisticalQuery"},method={RequestMethod.POST})
	public String statisticalQuery(String userFlow,String sessionNumber,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/statisticalQuery";
		}
		if(StringUtil.isBlank(sessionNumber)){
			sessionNumber=DateUtil.getYear();
			sessionNumber=Integer.parseInt(sessionNumber)- 1 +"";
		}
		model.addAttribute("sessionNumber",sessionNumber);
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/admin/statisticalQuery";
		}
		List<String> changeStatusIdList = new ArrayList<String>();
		Map<String,Object> param=new HashMap<>();
		param.put("sessionNumber",sessionNumber);
		param.put("orgFlow",user.getOrgFlow());
		param.put("changeTypeId", JsResChangeTypeEnum.SpeChange.getId());
		changeStatusIdList.add(JsResChangeApplySpeEnum.GlobalAuditPass.getId());
		param.put("changeStatusIdList",changeStatusIdList);
		//变更专业学员数量
		List<JsResDoctorOrgHistoryExt> historyExtList=jswjwAdminBiz.getSpeChangeList(param);
		if(historyExtList!=null)
		{
			model.addAttribute("SpeChange",historyExtList.size());
		}else{
			model.addAttribute("SpeChange",0);
		}
		//变更基地
		changeStatusIdList.clear();
		param.put("changeTypeId", JsResChangeTypeEnum.BaseChange.getId());
		changeStatusIdList.add(JsResChangeApplyStatusEnum.InApplyPass.getId());
		param.put("changeStatusIdList",changeStatusIdList);
		historyExtList=jswjwAdminBiz.getSpeChangeList(param);
		if(historyExtList!=null)
		{
			model.addAttribute("BaseChange",historyExtList.size());
		}else{
			model.addAttribute("BaseChange",0);
		}
		//延期学员
		param.put("typeId", ResRecTypeEnum.Delay.getId());
		List<ResDocotrDelayTeturn>  resRecList = jswjwAdminBiz.getOrgDelayReturnInfo(param,null);
		if(resRecList!=null)
		{
			model.addAttribute("Delay",resRecList.size());
		}else{
			model.addAttribute("Delay",0);
		}
		//退培学员
		param.put("typeId", ResRecTypeEnum.ReturnTraining.getId());

		List<String> flags = new ArrayList<>();
		flags.add("1");
		resRecList = jswjwAdminBiz.getOrgDelayReturnInfo(param,flags);
		if(resRecList!=null)
		{
			model.addAttribute("ReturnTraining",resRecList.size());
		}else{
			model.addAttribute("ReturnTraining",0);
		}
		//基地所开通的专业
		List<ResOrgSpe> orgSpes=jswjwAdminBiz.getOrgSpes(user.getOrgFlow());
		model.addAttribute("orgSpes",orgSpes);
		Integer max=0;
		if(orgSpes!=null&&!orgSpes.isEmpty()) {
			Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
			Integer totalCount = 0;//每家基地培训学员的总数
			List<Map<String, Object>> doctorCountList = jswjwAdminBiz.getOrgSpeDocNum(user.getOrgFlow(), sessionNumber);
			if(doctorCountList!=null&&!doctorCountList.isEmpty()){
				for(Map<String,Object> en:doctorCountList){
					Object key=en.get("key");
					Object value= en.get("value");
					if(value != null){
						totalCount += Integer.parseInt(value.toString());
					}
					if(max<Integer.parseInt(value.toString()))
					{
						max=Integer.parseInt(value.toString());
					}
					totalCountMap.put(key, value);
				}
			}
			model.addAttribute("max",max);
			model.addAttribute("totalCount",totalCount);
			model.addAttribute("totalCountMap",totalCountMap);
		}
		//学员信息统计
		int count= jswjwAdminBiz.getOrgDocCount(user.getOrgFlow(),sessionNumber,"20");
		model.addAttribute("TrainingNum",count);
		count= jswjwAdminBiz.getOrgDocCount(user.getOrgFlow(),sessionNumber,"21");//结业
		model.addAttribute("GraduationNum",count);
		count= jswjwAdminBiz.getOrgDocCount(user.getOrgFlow(),sessionNumber,"22");//已考核待结业
		model.addAttribute("WaitGraduationNum",count);

		model.addAttribute("trainingTypes", TrainCategoryEnum.values());
		model.addAttribute("doctorTypes", JsRecDocTypeEnum.values());

		HashMap<String,Object> dictMap=new HashMap<>();
		dictMap.put(TrainCategoryEnum.DoctorTrainingSpe.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.DoctorTrainingSpe.getId()));
		dictMap.put(TrainCategoryEnum.WMFirst.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.WMFirst.getId()));
		dictMap.put(TrainCategoryEnum.WMSecond.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.WMSecond.getId()));
		dictMap.put(TrainCategoryEnum.AssiGeneral.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.AssiGeneral.getId()));
		model.addAttribute("dictMap", dictMap);
		return "res/jswjw/admin/statisticalQuery";
	}

	@RequestMapping(value = {"/orgDocList"},method = {RequestMethod.POST})
	public String orgDocList(String userFlow,String statusId,String sessionNumber,String trainingTypeId,String trainingSpeId,String userName,
							 String doctorTypeId, Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/orgDocList";
		}
		if(StringUtil.isBlank(statusId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "列表类型不能为空");
			return "res/jswjw/admin/orgDocList";
		}
		if(!"Training".equals(statusId)&&!"Graduation".equals(statusId)&&!"WaitGraduation".equals(statusId))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "列表类型不正确，只能是Training或Graduation或WaitGraduation");
			return "res/jswjw/admin/orgDocList";
		}
		if(StringUtil.isBlank(sessionNumber)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择年级");
			return "res/jswjw/admin/orgDocList";
		}

		if("Training".equals(statusId))
		{
			statusId="20";
		}
		if("Graduation".equals(statusId))
		{
			statusId="21";
		}
		if("WaitGraduation".equals(statusId))
		{
			statusId="22";
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/admin/orgDocList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/admin/orgDocList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/admin/orgDocList";
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		param.put("statusId",statusId);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("userName",userName);
		param.put("sessionNumber",sessionNumber);
		param.put("doctorTypeId",doctorTypeId);
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,String>> list=jswjwAdminBiz.getOrgDocList(param);
		model.addAttribute("list",list);
		model.addAttribute("dataCount", PageHelper.total);
		addUploadUrl(model);
		return "res/jswjw/admin/orgDocList";
	}
	/**
	 * 基地变更学员列表
	 * @param userFlow
	 * @param typeId
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value = {"/baseChangeList"},method = {RequestMethod.POST})
	public String baseChangeList(String userFlow,String typeId,String sessionNumber,
								Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/baseChangeList";
		}
		if(StringUtil.isBlank(typeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "转入转出类型不能为空");
			return "res/jswjw/admin/baseChangeList";
		}
		if(!"changeIn".equals(typeId)&&!"changeOut".equals(typeId))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "转入转出类型不正确，只能是changeIn或changeOut");
			return "res/jswjw/admin/baseChangeList";
		}
		if(StringUtil.isBlank(sessionNumber)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择年级");
			return "res/jswjw/admin/baseChangeList";
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/admin/baseChangeList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/admin/baseChangeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/admin/baseChangeList";
		}
		List<String> changeStatusIdList = new ArrayList<String>();
		Map<String,Object> param=new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		param.put("typeId",typeId);
		param.put("changeTypeId", JsResChangeTypeEnum.BaseChange.getId());
		changeStatusIdList.add(JsResChangeApplyStatusEnum.InApplyPass.getId());
		param.put("changeStatusIdList",changeStatusIdList);
		param.put("sessionNumber",sessionNumber);
		//基地变更学员数量
		PageHelper.startPage(pageIndex, pageSize);
		List<JsResDoctorOrgHistoryExt> historyExtList=jswjwAdminBiz.getSpeChangeList(param);
		model.addAttribute("list",historyExtList);
		model.addAttribute("dataCount", PageHelper.total);
		addUploadUrl(model);
		return "res/jswjw/admin/baseChangeList";
	}

	/**
	 * 专业变更
	 * @param userFlow
	 * @param pageIndex
	 * @param pageSize
	 * @param model
     * @return
     */
	@RequestMapping(value = {"/speChangeList"},method = {RequestMethod.POST})
	public String speChangeList(String userFlow,String sessionNumber,
								Integer pageIndex,Integer pageSize,Model model)  {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/speChangeList";
		}
		if(StringUtil.isBlank(sessionNumber)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择年级");
			return "res/jswjw/admin/speChangeList";
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/admin/speChangeList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/admin/speChangeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/admin/speChangeList";
		}
		List<String> changeStatusIdList = new ArrayList<String>();
		Map<String,Object> param=new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		param.put("changeTypeId", JsResChangeTypeEnum.SpeChange.getId());
		changeStatusIdList.add(JsResChangeApplySpeEnum.GlobalAuditPass.getId());
		param.put("changeStatusIdList",changeStatusIdList);
		param.put("sessionNumber",sessionNumber);
		//变更专业学员数量
		PageHelper.startPage(pageIndex, pageSize);
		List<JsResDoctorOrgHistoryExt> historyExtList=jswjwAdminBiz.getSpeChangeList(param);
		model.addAttribute("list",historyExtList);
		model.addAttribute("dataCount", PageHelper.total);
		addUploadUrl(model);
		return "res/jswjw/admin/speChangeList";
	}
	/**
	 * 延期学员
	 * @param userFlow
	 * @param pageIndex
	 * @param pageSize
	 * @param model
     * @return
     */
	@RequestMapping(value = {"/delayList"},method = {RequestMethod.POST})
	public String delayList(String userFlow,String sessionNumber,
								Integer pageIndex,Integer pageSize,Model model)  {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/delayList";
		}
		if(StringUtil.isBlank(sessionNumber)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择年级");
			return "res/jswjw/admin/delayList";
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/admin/delayList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/admin/delayList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/admin/delayList";
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		param.put("typeId", ResRecTypeEnum.Delay.getId());
		param.put("sessionNumber",sessionNumber);
		//变更专业学员数量
		PageHelper.startPage(pageIndex, pageSize);
		List<ResDocotrDelayTeturn>  resRecList = jswjwAdminBiz.getOrgDelayReturnInfo(param,null);
		Map<String,SysUser> userMap=new HashMap<>();
		if(resRecList!=null)
		{
			for(ResDocotrDelayTeturn r:resRecList)
			{
				userMap.put(r.getDoctorFlow(),jswjwBiz.readSysUser(r.getDoctorFlow()));
			}
		}
		model.addAttribute("userMap",userMap);
		model.addAttribute("list",resRecList);
		model.addAttribute("dataCount", PageHelper.total);
		addUploadUrl(model);
		return "res/jswjw/admin/delayList";
	}

	@RequestMapping(value = {"/returnList"},method = {RequestMethod.POST})
	public String returnList(String userFlow,String sessionNumber,
								Integer pageIndex,Integer pageSize,Model model)  {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/returnList";
		}
		if(StringUtil.isBlank(sessionNumber)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请选择年级");
			return "res/jswjw/admin/returnList";
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/admin/returnList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/admin/returnList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/admin/returnList";
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		param.put("typeId", ResRecTypeEnum.ReturnTraining.getId());
		param.put("sessionNumber",sessionNumber);

		List<String> flags = new ArrayList<>();
		flags.add("1");
		PageHelper.startPage(pageIndex, pageSize);
		List<ResDocotrDelayTeturn>  resRecList = jswjwAdminBiz.getOrgDelayReturnInfo(param,flags);
		Map<String,SysUser> userMap=new HashMap<>();
		if(resRecList!=null)
		{
			for(ResDocotrDelayTeturn r:resRecList)
			{
				userMap.put(r.getDoctorFlow(),jswjwBiz.readSysUser(r.getDoctorFlow()));
			}
		}
		model.addAttribute("userMap",userMap);
		model.addAttribute("list",resRecList);
		model.addAttribute("dataCount", PageHelper.total);
		addUploadUrl(model);
		return "res/jswjw/admin/returnList";
	}

	@RequestMapping(value = {"/trainingOpinions"},method = {RequestMethod.POST})
	public String trainingOpinions(String userFlow,
								Integer pageIndex,Integer pageSize,Model model)  {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/admin/trainingOpinions";
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/admin/trainingOpinions";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/admin/trainingOpinions";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/admin/trainingOpinions";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResTrainingOpinion>  resRecList = resLiveTrainingBiz.searchByOpinionUserContentAndReplayStatus("","",user.getOrgFlow());
		Map<String,SysUser> userMap=new HashMap<>();
		if(resRecList!=null)
		{
			for(ResTrainingOpinion r:resRecList)
			{
				userMap.put(r.getOpinionUserFlow(),jswjwBiz.readSysUser(r.getOpinionUserFlow()));
			}
		}
		model.addAttribute("userMap",userMap);
		model.addAttribute("list",resRecList);
		model.addAttribute("dataCount", PageHelper.total);
		addUploadUrl(model);
		return "res/jswjw/admin/trainingOpinions";
	}

	@RequestMapping(value = {"/saveOpinionReply"},method = {RequestMethod.POST})
	public String saveOpinionReply(String userFlow,Model model,String trainingOpinionFlow,String replayContent){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(trainingOpinionFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "意见反馈标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(replayContent)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "回复内容不能为空");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		ResTrainingOpinion trainingOpinion=resLiveTrainingBiz.read(trainingOpinionFlow);
		if(trainingOpinion==null)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "反馈信息不存在");
			return "res/jswjw/success";
		}
		if(StringUtil.isNotBlank(trainingOpinion.getReplyTime()))
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "已回复该意见反馈，请刷新页面！");
			return "res/jswjw/success";
		}
		trainingOpinion.setOpinionReplyUserFlow(userFlow);
		trainingOpinion.setOpinionReplyName(user.getUserName());
		String currTime = DateUtil.getCurrDateTime();
		trainingOpinion.setReplyTime(currTime);
		trainingOpinion.setOpinionReplyContent(replayContent);
		resLiveTrainingBiz.edit(trainingOpinion,userFlow);
		return "res/jswjw/success";
	}
	public  void addUploadUrl(Model model)
	{
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
	}
}

