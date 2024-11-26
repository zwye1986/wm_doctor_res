package com.pinde.res.ctrl.jswjw;

import com.pinde.app.common.GeneralController;
import com.pinde.app.common.InitConfig;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.*;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.jswjw.IJswjwAdminBiz;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IResLiveTrainingBiz;
import com.pinde.res.model.jswjw.mo.JsResDoctorOrgHistoryExt;
import com.pinde.sci.dao.base.SysDeptMapper;
import com.pinde.sci.dao.base.TeachingActivitySpeakerMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.pinde.sci.util.DateTimeUtil.getMonthsByTwoMonth;
import static com.pinde.sci.util.DateTimeUtil.getWeeksByTwoDate;

@Controller
@RequestMapping("/res/jswjw/wx/admin")
public class JswjwWxAdminController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(JswjwWxAdminController.class);

	@Autowired
	private IJswjwBiz jswjwBiz;
	@Autowired
	private IResLiveTrainingBiz resLiveTrainingBiz;
	@Autowired
	private IJswjwAdminBiz jswjwAdminBiz;
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private TeachingActivitySpeakerMapper activitySpeakerMapper;

	/**
	 * 科室列表
	 * @param userFlow
	 * @return
	 */
	@RequestMapping(value={"/deptList"},method={RequestMethod.POST})
	@ResponseBody
	public Object deptList(String userFlow,String deptName, Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		SysUser user=jswjwBiz.readSysUser(userFlow);
		PageHelper.startPage(pageIndex, pageSize);
		List<SysDept> depts=jswjwBiz.getOrgDeptList(user.getOrgFlow(),deptName);
		resultMap.put("dataCount", PageHelper.total);

		List<Map<String,Object>> resultMapList = new ArrayList<>();

		if(depts!=null&&depts.size()>0) {
			for(SysDept dept:depts) {
				Map<String,Object> deptInfo=new HashMap<>();
				deptInfo.put("deptFlow",dept.getDeptFlow());
				deptInfo.put("deptName",dept.getDeptName());
				List<Map<String, String>> resDoctorSchProcess = null;
				Map<String, String> schArrangeResultMap = new HashMap<String, String>();
				schArrangeResultMap.put("deptFlow", dept.getDeptFlow());
				//本月轮转的学员
				schArrangeResultMap.put("typeId", "monthCurrent");
				resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
				if (resDoctorSchProcess != null && !resDoctorSchProcess.isEmpty()) {
//					deptInfo.put(dept.getDeptFlow()+"monthCurrent", resDoctorSchProcess.size());
					deptInfo.put("monthCurrent", resDoctorSchProcess.size());
				} else {
//					deptInfo.put(dept.getDeptFlow()+"monthCurrent", 0);
					deptInfo.put("monthCurrent", 0);
				}
				//待出科学员
				schArrangeResultMap.put("typeId", "monthSch");
				resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
				if (resDoctorSchProcess != null && !resDoctorSchProcess.isEmpty()) {
//					deptInfo.put(dept.getDeptFlow()+"monthSch", resDoctorSchProcess.size());
					deptInfo.put("monthSch", resDoctorSchProcess.size());
				} else {
//					deptInfo.put(dept.getDeptFlow()+"monthSch", 0);
					deptInfo.put("monthSch", 0);
				}
				//待入科学员
				schArrangeResultMap.put("typeId", "waitSch");
				schArrangeResultMap.put("yearMonth", DateUtil.addMonth(DateUtil.getMonth(), 1));
				resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
				if (resDoctorSchProcess != null && !resDoctorSchProcess.isEmpty()) {
//					deptInfo.put(dept.getDeptFlow()+"waitSch", resDoctorSchProcess.size());
					deptInfo.put("waitSch", resDoctorSchProcess.size());
				} else {
//					deptInfo.put(dept.getDeptFlow()+"waitSch", 0);
					deptInfo.put("waitSch", 0);
				}
				resultMapList.add(deptInfo);
			}
		}
//		resultMap.put("deptInfo", deptInfo);
//		resultMap.put("depts", depts);

		resultMap.put("resultMapList", resultMapList);
		return resultMap;
	}

	/**
	 * 科室人员信息
	 * @param userFlow
	 * @param deptFlow
	 * @return
	 */
	@RequestMapping(value={"/deptStuDetail"},method={RequestMethod.POST})
	@ResponseBody
	public Object deptStuDetail(String userFlow,String deptFlow){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isEmpty(deptFlow)){
			return ResultDataThrow("科室标识符为空");
		}
//		SysUser user=jswjwBiz.readSysUser(userFlow);

		List<Map<String,String>> resDoctorSchProcess=null;

		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", deptFlow);

		//本月轮转的学员
		schArrangeResultMap.put("typeId", "monthCurrent");
		resDoctorSchProcess=jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
		if(resDoctorSchProcess!=null&&!resDoctorSchProcess.isEmpty())
		{
			resultMap.put("monthCurrent",resDoctorSchProcess.size());
		}else{
			resultMap.put("monthCurrent",0);
		}
		//待出科学员
		schArrangeResultMap.put("typeId", "monthSch");
		resDoctorSchProcess=jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
		if(resDoctorSchProcess!=null&&!resDoctorSchProcess.isEmpty())
		{
			resultMap.put("monthSch",resDoctorSchProcess.size());
		}else{
			resultMap.put("monthSch",0);
		}
		//待入科学员
		schArrangeResultMap.put("typeId", "waitSch");
		schArrangeResultMap.put("yearMonth", DateUtil.addMonth(DateUtil.getMonth(),1));
		resDoctorSchProcess=jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
		if(resDoctorSchProcess!=null&&!resDoctorSchProcess.isEmpty())
		{
			resultMap.put("waitSch",resDoctorSchProcess.size());
		}else{
			resultMap.put("waitSch",0);
		}
		return resultMap;
	}

	/**
	 * 学员列表
	 * @param userFlow
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = {"/studentList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object studentList(String userFlow,String searchStr,String deptFlow,
							  String typeId,String yearMonth,
							  Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		if(StringUtil.isEmpty(deptFlow)){
			return ResultDataThrow("科室标识符为空");
		}
		if(StringUtil.isBlank(typeId)) {
			return ResultDataThrow("学员列表类型为空");
		}
		if(!"waitSch".equals(typeId)&&!"monthCurrent".equals(typeId)&&!"monthSch".equals(typeId)) {
			return ResultDataThrow("学员列表类型不正确,只能是monthCurrent，monthSch，waitSch");
		}
		if("waitSch".equals(typeId)) {
			yearMonth=DateUtil.addMonth(DateUtil.getMonth(),1);
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}

		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", deptFlow);
		schArrangeResultMap.put("searchStr", searchStr);
		schArrangeResultMap.put("typeId", typeId);
		schArrangeResultMap.put("yearMonth", yearMonth);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
		resultMap.put("list",resDoctorSchProcess);
		resultMap.put("dataCount", PageHelper.total);
		addUploadUrl(resultMap);
		return resultMap;
	}

	/**
	 * 出科异常学员列表
	 * @param userFlow
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value = {"/errorStudentList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object errorStudentList(String userFlow, Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<SysDept> depts=jswjwAdminBiz.getErrorSchDepts(userinfo.getOrgFlow());
//		Map<String,List<Map<String, String>>> processMap=new HashMap<>();

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		for (SysDept dept:depts) {
			Map<String,Object> deptMap = new HashMap<>();
			deptMap.put("deptFlow",dept.getDeptFlow());
			deptMap.put("deptName",dept.getDeptName());

			List<Map<String, String>> resDoctorSchProcess = null;
			Map<String, String> schArrangeResultMap = new HashMap<String, String>();
			schArrangeResultMap.put("deptFlow", dept.getDeptFlow());
			schArrangeResultMap.put("typeId", "errorSch");
			resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);
//			processMap.put(dept.getDeptFlow(),resDoctorSchProcess);
			List<Map<String,Object>> studentList = new ArrayList<>();
			if(null != resDoctorSchProcess && resDoctorSchProcess.size()>0){
				for (Map<String, String> map:resDoctorSchProcess) {
					Map<String,Object> studentMap = new HashMap<>();
					studentMap.put("userName",map.get("userName"));
					studentMap.put("userHeadImg",map.get("userHeadImg"));
					studentMap.put("docFlow",map.get("userFlow"));
					studentMap.put("deptFlow",map.get("deptFlow"));
					studentList.add(studentMap);
				}
			}
			deptMap.put("studentList",studentList);
			resultMapList.add(deptMap);
		}
//		resultMap.put("depts",depts);
		resultMap.put("resultMapList",resultMapList);
		resultMap.put("dataCount", PageHelper.total);
		addUploadUrl(resultMap);
		return resultMap;
	}

	/**
	 * 发送通知
	 * @param userFlow
	 * @return
	 */
	@RequestMapping(value={"/saveSchErrorNoticeNew"},method={RequestMethod.POST})
	@ResponseBody
	public Object saveSchErrorNoticeNew(String userFlow, String deptFlow,String doctorFlow,String content,String isAll){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			return ResultDataThrow("用户不存在");
		}
		if(StringUtil.isBlank(content)){
			return ResultDataThrow("消息内容不能为空");
		}
		if(StringUtil.isNotBlank(isAll)){
			//是全选
			List<SysDept> depts=jswjwAdminBiz.getErrorSchDepts(userinfo.getOrgFlow());
			for (SysDept dept:depts) {
				List<Map<String, String>> resDoctorSchProcess = null;
				Map<String, String> schArrangeResultMap = new HashMap<String, String>();
				schArrangeResultMap.put("deptFlow", dept.getDeptFlow());
				schArrangeResultMap.put("typeId", "errorSch");
				resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);

				List<String> doctorFlows = new ArrayList<>();
				if(null != resDoctorSchProcess && resDoctorSchProcess.size()>0){
					for (Map<String, String> map:resDoctorSchProcess) {
						if(!doctorFlows.contains(map.get("userFlow"))) {
							doctorFlows.add(map.get("userFlow"));
						}
					}
				}
				jswjwAdminBiz.sendErrorSchNotice(doctorFlows,content,userinfo);
			}
		}else{
			if(StringUtil.isNotBlank(deptFlow)){
				//科室全选
				List<String> deptFlowss = Arrays.asList(deptFlow.split(","));
				for (String flow:deptFlowss) {
					List<Map<String, String>> resDoctorSchProcess = null;
					Map<String, String> schArrangeResultMap = new HashMap<String, String>();
					schArrangeResultMap.put("deptFlow", flow);
					schArrangeResultMap.put("typeId", "errorSch");
					resDoctorSchProcess = jswjwAdminBiz.schDeptDoctorSchProcess(schArrangeResultMap);

					List<String> doctorFlows = new ArrayList<>();
					if(null != resDoctorSchProcess && resDoctorSchProcess.size()>0){
						for (Map<String, String> map:resDoctorSchProcess) {
							if(!doctorFlows.contains(map.get("userFlow"))) {
								doctorFlows.add(map.get("userFlow"));
							}
						}
					}
					jswjwAdminBiz.sendErrorSchNotice(doctorFlows,content,userinfo);
				}
			}else {
				//选择学员
				if(StringUtil.isNotBlank(doctorFlow)){
					List<String> doctorFlowss=Arrays.asList(doctorFlow.split(","));
					List<String> doctorFlows = new ArrayList<String>(new HashSet<String>(doctorFlowss));
					jswjwAdminBiz.sendErrorSchNotice(doctorFlows,content,userinfo);
				}else{
					return ResultDataThrow("学员标识符不能为空");
				}
			}
		}
		return resultMap;
	}

	/**
	 * 发送通知
	 * @param userFlow
	 * @return
	 */
	@RequestMapping(value={"/saveSchErrorNotice"},method={RequestMethod.POST})
	@ResponseBody
	public Object saveSchErrorNotice(String userFlow, String docFlow,String content){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(docFlow)){
			return ResultDataThrow("学员标识符列表为空");
		}
		if(StringUtil.isBlank(content)){
			return ResultDataThrow("消息内容不能为空");
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
		}
		List<String> doctorFlowss=Arrays.asList(docFlow.split(","));
		List<String> doctorFlows = new ArrayList<String>(new HashSet<String>(doctorFlowss));
		jswjwAdminBiz.sendErrorSchNotice(doctorFlows,content,user);
		return resultMap;
	}

	/**
	 * 统计查询首页
	 * @param userFlow
	 * @param sessionNumber
	 * @return
	 */
	@RequestMapping(value={"/statisticalQuery"},method={RequestMethod.POST})
	@ResponseBody
	public Object statisticalQuery(String userFlow,String sessionNumber){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(sessionNumber)){
			sessionNumber=DateUtil.getYear();
			sessionNumber=Integer.parseInt(sessionNumber)- 1 +"";
		}
		resultMap.put("sessionNumber",sessionNumber);
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
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
			resultMap.put("SpeChange",historyExtList.size());
		}else{
			resultMap.put("SpeChange",0);
		}
		//变更基地
		changeStatusIdList.clear();
		param.put("changeTypeId", JsResChangeTypeEnum.BaseChange.getId());
		changeStatusIdList.add(JsResChangeApplyStatusEnum.InApplyPass.getId());
		param.put("changeStatusIdList",changeStatusIdList);
		historyExtList=jswjwAdminBiz.getSpeChangeList(param);
		if(historyExtList!=null)
		{
			resultMap.put("BaseChange",historyExtList.size());
		}else{
			resultMap.put("BaseChange",0);
		}
		//延期学员
		param.put("typeId", ResRecTypeEnum.Delay.getId());
		List<ResDocotrDelayTeturn>  resRecList = jswjwAdminBiz.getOrgDelayReturnInfo(param,null);
		if(resRecList!=null)
		{
			resultMap.put("Delay",resRecList.size());
		}else{
			resultMap.put("Delay",0);
		}
		//退培学员
		param.put("typeId", ResRecTypeEnum.ReturnTraining.getId());

		List<String> flags = new ArrayList<>();
		flags.add("1");
		resRecList = jswjwAdminBiz.getOrgDelayReturnInfo(param,flags);
		if(resRecList!=null)
		{
			resultMap.put("ReturnTraining",resRecList.size());
		}else{
			resultMap.put("ReturnTraining",0);
		}
		//基地所开通的专业
		List<ResOrgSpe> orgSpes=jswjwAdminBiz.getOrgSpes(user.getOrgFlow());
//		resultMap.put("orgSpes",orgSpes);
		Integer max=0;
		Map<Object, Object> totalCountMap=new HashMap<Object,Object>();//保存每家基地的培训记录总数
		if(orgSpes!=null&&!orgSpes.isEmpty()) {
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
			resultMap.put("max",max);
			resultMap.put("totalCount",totalCount);
//			resultMap.put("totalCountMap",totalCountMap);
		}
		//学员信息统计
		int count= jswjwAdminBiz.getOrgDocCount(user.getOrgFlow(),sessionNumber,"20");
		resultMap.put("TrainingNum",count);
		count= jswjwAdminBiz.getOrgDocCount(user.getOrgFlow(),sessionNumber,"21");//结业
		resultMap.put("GraduationNum",count);
		count= jswjwAdminBiz.getOrgDocCount(user.getOrgFlow(),sessionNumber,"22");//已考核待结业
		resultMap.put("WaitGraduationNum",count);

//		resultMap.put("trainingTypes", TrainCategoryEnum.values());
//		resultMap.put("doctorTypes", JsRecDocTypeEnum.values());

//		HashMap<String,Object> dictMap=new HashMap<>();
//		dictMap.put(TrainCategoryEnum.DoctorTrainingSpe.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.DoctorTrainingSpe.getId()));
//		dictMap.put(TrainCategoryEnum.WMFirst.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.WMFirst.getId()));
//		dictMap.put(TrainCategoryEnum.WMSecond.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.WMSecond.getId()));
//		dictMap.put(TrainCategoryEnum.AssiGeneral.getId(),jswjwBiz.getDictListByDictId(TrainCategoryEnum.AssiGeneral.getId()));
//		resultMap.put("dictMap", dictMap);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != orgSpes && orgSpes.size()>0){
			for (ResOrgSpe spe:orgSpes) {
				Map<String,Object> speMap = new HashMap<>();
				String key = spe.getSpeTypeId()+spe.getSpeId();
				speMap.put("speTypeId",spe.getSpeTypeId());
				speMap.put("speTypeName",spe.getSpeTypeName());
				speMap.put("speId",spe.getSpeId());
				speMap.put("speName",spe.getSpeName());
				speMap.put("count",null == totalCountMap ? "0" : null == totalCountMap.get(key) ? "0" : totalCountMap.get(key));
				resultMapList.add(speMap);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/orgDocList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object orgDocList(String userFlow,String statusId,String sessionNumber,String trainingTypeId,String trainingSpeId,String userName,
							 String doctorTypeId, Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(statusId)){
			return ResultDataThrow("列表类型不能为空");
		}
		if(!"Training".equals(statusId)&&!"Graduation".equals(statusId)&&!"WaitGraduation".equals(statusId)) {
			return ResultDataThrow("列表类型不正确，只能是Training或Graduation或WaitGraduation");
		}
		if(StringUtil.isBlank(sessionNumber)){
			return ResultDataThrow("请选择年级");
		}

		if("Training".equals(statusId)) {
			statusId="20";
		}
		if("Graduation".equals(statusId)) {
			statusId="21";
		}
		if("WaitGraduation".equals(statusId)) {
			statusId="22";
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
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
//		resultMap.put("list",list);
		resultMap.put("dataCount", PageHelper.total);
		addUploadUrl(resultMap);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != list && list.size()>0){
			for (Map<String,String> bean:list) {
				Map<String,Object> map = new HashMap<>();
				map.put("userFlow",bean.get("userFlow"));
				map.put("userName",bean.get("userName"));
				map.put("userHeadImg",bean.get("userHeadImg"));
				map.put("trainingSpeName",bean.get("speName"));
				map.put("trainingTypeName",bean.get("catSpeName"));
				map.put("doctorTypeName",bean.get("doctorTypeName"));
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}
	/**
	 * 基地变更学员列表
	 * @param userFlow
	 * @param typeId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws DocumentException
	 */
	@RequestMapping(value = {"/baseChangeList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object baseChangeList(String userFlow,String typeId,String sessionNumber,
								 Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(typeId)){
			return ResultDataThrow("转入转出类型不能为空");
		}
		if(!"changeIn".equals(typeId)&&!"changeOut".equals(typeId)) {
			return ResultDataThrow("转入转出类型不正确，只能是changeIn或changeOut");
		}
		if(StringUtil.isBlank(sessionNumber)){
			return ResultDataThrow("请选择年级");
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
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
//		resultMap.put("list",historyExtList);
		resultMap.put("dataCount", PageHelper.total);
		addUploadUrl(resultMap);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != historyExtList && historyExtList.size()>0){
			for (JsResDoctorOrgHistoryExt ext:historyExtList) {
				Map<String,Object> map = new HashMap<>();
				map.put("userName",ext.getSysUser().getUserName());
				map.put("userHeadImg",ext.getSysUser().getUserHeadImg());
				map.put("historyTrainingSpeName",ext.getHistoryTrainingSpeName());
				map.put("trainingSpeName",ext.getTrainingSpeName());
				map.put("orgFlow",ext.getOrgFlow());
				map.put("orgName",ext.getOrgName());
				map.put("historyOrgFlow",ext.getHistoryOrgFlow());
				map.put("historyOrgName",ext.getHistoryOrgName());
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	/**
	 * 专业变更
	 * @param userFlow
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = {"/speChangeList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object speChangeList(String userFlow,String sessionNumber, Integer pageIndex,Integer pageSize)  {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(sessionNumber)){
			return ResultDataThrow("请选择年级");
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
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
//		resultMap.put("list",historyExtList);
		resultMap.put("dataCount", PageHelper.total);
		addUploadUrl(resultMap);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != historyExtList && historyExtList.size()>0){
			for (JsResDoctorOrgHistoryExt ext:historyExtList) {
				Map<String,Object> map = new HashMap<>();
				map.put("userName",ext.getSysUser().getUserName());
				map.put("userHeadImg",ext.getSysUser().getUserHeadImg());
				map.put("historyTrainingSpeName",ext.getHistoryTrainingSpeName());
				map.put("trainingSpeName",ext.getTrainingSpeName());
				resultMapList.add(map);
			}
		}
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}
	/**
	 * 延期学员
	 * @param userFlow
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = {"/delayList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object delayList(String userFlow,String sessionNumber, Integer pageIndex,Integer pageSize)  {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(sessionNumber)){
			return ResultDataThrow("请选择年级");
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		param.put("typeId", ResRecTypeEnum.Delay.getId());
		param.put("sessionNumber",sessionNumber);
		//变更专业学员数量
		PageHelper.startPage(pageIndex, pageSize);
		List<ResDocotrDelayTeturn>  resRecList = jswjwAdminBiz.getOrgDelayReturnInfo(param,null);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(resRecList!=null) {
			for(ResDocotrDelayTeturn r:resRecList) {
				Map<String,Object> userMap=new HashMap<>();
				SysUser docUser = jswjwBiz.readSysUser(r.getDoctorFlow());
				userMap.put("userName",docUser.getUserName());
				userMap.put("userHeadImg",docUser.getUserHeadImg());
				userMap.put("trainingTypeName",r.getTrainingTypeName());
				userMap.put("trainingSpeName",r.getTrainingSpeName());
				userMap.put("sessionNumber",r.getSessionNumber());
				userMap.put("graduationYear",r.getGraduationYear());
				userMap.put("delayreason",r.getDelayreason());
				resultMapList.add(userMap);
			}
		}
//		resultMap.put("userMap",userMap);
//		resultMap.put("list",resRecList);
		resultMap.put("dataCount", PageHelper.total);
		addUploadUrl(resultMap);
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/returnList"},method = {RequestMethod.POST})
	@ResponseBody
	public Object returnList(String userFlow,String sessionNumber, Integer pageIndex,Integer pageSize)  {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(sessionNumber)){
			return ResultDataThrow("请选择年级");
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		Map<String,Object> param=new HashMap<>();
		param.put("orgFlow",user.getOrgFlow());
		param.put("typeId", ResRecTypeEnum.ReturnTraining.getId());
		param.put("sessionNumber",sessionNumber);

		List<String> flags = new ArrayList<>();
		flags.add("1");
		PageHelper.startPage(pageIndex, pageSize);
		List<ResDocotrDelayTeturn>  resRecList = jswjwAdminBiz.getOrgDelayReturnInfo(param,flags);

		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(null != resRecList && resRecList.size()>0){
			for(ResDocotrDelayTeturn r:resRecList) {
				Map<String,Object> userMap=new HashMap<>();
				SysUser docUser = jswjwBiz.readSysUser(r.getDoctorFlow());
//				userMap.put(r.getDoctorFlow(),jswjwBiz.readSysUser(r.getDoctorFlow()));
				userMap.put("userName", null == docUser ? "" : docUser.getUserName());
				userMap.put("userHeadImg", null == docUser ? "" : docUser.getUserHeadImg());
				userMap.put("trainingTypeName", r.getTrainingTypeName());
				userMap.put("trainingSpeName", r.getTrainingSpeName());
				userMap.put("sessionNumber", r.getSessionNumber());
				userMap.put("graduationYear", r.getGraduationYear());
				userMap.put("reason", r.getReasonName() + (StringUtil.isBlank(r.getReason()) ? "" : "【" + r.getReason() + "】"));
				resultMapList.add(userMap);
			}
		}
//		resultMap.put("userMap",userMap);
//		resultMap.put("list",resRecList);
		resultMap.put("dataCount", PageHelper.total);
		addUploadUrl(resultMap);
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/trainingOpinions"},method = {RequestMethod.POST})
	@ResponseBody
	public Object trainingOpinions(String userFlow, Integer pageIndex,Integer pageSize)  {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResTrainingOpinion>  resRecList = resLiveTrainingBiz.searchByOpinionUserContentAndReplayStatus("","",user.getOrgFlow());
		List<Map<String,Object>> resultMapList = new ArrayList<>();
		if(resRecList!=null) {
			for(ResTrainingOpinion r:resRecList) {
				Map<String,Object> userMap=new HashMap<>();
				SysUser docUser = jswjwBiz.readSysUser(r.getOpinionUserFlow());
				userMap.put("userName",docUser.getUserName());
				userMap.put("userPhone",docUser.getUserPhone());
				userMap.put("userHeadImg",docUser.getUserHeadImg());
				userMap.put("trainingOpinionFlow",r.getTrainingOpinionFlow());
				userMap.put("opinionUserName",r.getOpinionUserName());
				userMap.put("opinionUserFlow",r.getOpinionUserFlow());
				userMap.put("opinionUserContent",r.getOpinionUserContent());
				userMap.put("opinionReplyUserFlow",r.getOpinionReplyUserFlow());
				userMap.put("opinionReplyName",r.getOpinionReplyName());
				userMap.put("opinionReplyContent",r.getOpinionReplyContent());
				userMap.put("opinionReplyContent",r.getOpinionReplyContent());
				userMap.put("evaTime",DateUtil.transDate(r.getEvaTime()));
				userMap.put("replyTime",StringUtil.isNotBlank(r.getReplyTime()) ? DateUtil.transDate(r.getReplyTime()) : "");
				userMap.put("statusId",StringUtil.isNotBlank(r.getReplyTime()) ? "Repalyed" : "NotRepaly");
				userMap.put("statusName",StringUtil.isNotBlank(r.getReplyTime()) ? "已回复" : "未回复");
				resultMapList.add(userMap);
			}
		}
//		resultMap.put("userMap",userMap);
//		resultMap.put("list",resRecList);
		resultMap.put("dataCount", PageHelper.total);
		addUploadUrl(resultMap);
		resultMap.put("resultMapList",resultMapList);
		return resultMap;
	}

	@RequestMapping(value = {"/saveOpinionReply"},method = {RequestMethod.POST})
	@ResponseBody
	public Object saveOpinionReply(String userFlow,String trainingOpinionFlow,String replayContent){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}
		if(StringUtil.isBlank(trainingOpinionFlow)){
			return ResultDataThrow("意见反馈标识符为空");
		}
		if(StringUtil.isBlank(replayContent)){
			return ResultDataThrow("回复内容不能为空");
		}
		//验证用户是否存在
		SysUser user = jswjwBiz.readSysUser(userFlow);
		if(user==null){
			return ResultDataThrow("用户不存在");
		}
		ResTrainingOpinion trainingOpinion=resLiveTrainingBiz.read(trainingOpinionFlow);
		if(trainingOpinion==null)
		{
			return ResultDataThrow("反馈信息不存在");
		}
		if(StringUtil.isNotBlank(trainingOpinion.getReplyTime()))
		{
			return ResultDataThrow("已回复该意见反馈，请刷新页面！");
		}
		trainingOpinion.setOpinionReplyUserFlow(userFlow);
		trainingOpinion.setOpinionReplyName(user.getUserName());
		String currTime = DateUtil.getCurrDateTime();
		trainingOpinion.setReplyTime(currTime);
		trainingOpinion.setOpinionReplyContent(replayContent);
		resLiveTrainingBiz.edit(trainingOpinion,userFlow);
		return resultMap;
	}

	public  void addUploadUrl(Map<String,Object> map)
	{
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		map.put("uploadBaseUrl", uploadBaseUrl);
	}

	// 获取住院医师培训专业
	@RequestMapping(value="/getTrainingSpe",method = {RequestMethod.GET})
	@ResponseBody
	public Object getTrainingSpe() {
		List<SysDict> sysDictList = jswjwBiz.getDictListByDictId(TrainCategoryEnum.DoctorTrainingSpe.getId());
		for (int i = 0; i < sysDictList.size(); i++) {
			if (sysDictList.get(i).getDictId().equals("50")) {
				sysDictList.remove(i);
				i--;
			}
		}
		SysDict dict = new SysDict();
		dict.setDictId("");
		dict.setDictName("全部");
		sysDictList.add(0, dict);
		return sysDictList;
	}

	// 招录学员统计,与web端保持一致
	@RequestMapping(value="/zltjOrgLocalList",method = {RequestMethod.POST})
	@ResponseBody
	public Object zltjOrgLocalList(String roleFlag, String userFlow, String sessionNumber, String orgFlow, String trainingTypeId, String trainingSpeId, String datas, String statusId	) {

		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}
		SysOrg sysOrg = jswjwBiz.readSysOrg(sysuser.getOrgFlow());

		List<String> sessionNumbers=new ArrayList<String>();//年级多选
		if(StringUtil.isNotBlank(sessionNumber)){
			String[] numbers=sessionNumber.split(",");
			if(numbers.length > 0){
				sessionNumbers= Arrays.asList(numbers);
				sessionNumber="";
			}
		}
		List<String> docTypeList=new ArrayList<String>();
		if(StringUtil.isNotBlank(datas)){
			String[] s=datas.split(",");
			if(s.length > 0){
				docTypeList= Arrays.asList(s);
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
		param.put("orgTypeId",OrgTypeEnum.Hospital.getId());
		param.put("statusId",statusId);

		List<String> jointOrgFlowList=new ArrayList<String>();
		if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)&&StringUtil.isBlank(orgFlow)) {
			jointOrgFlowList.add(sysOrg.getOrgFlow());
			if(OrgLevelEnum.CountryOrg.getId().equals(sysOrg.getOrgLevelId()))
			{
				List<SysOrg> joinOrgs=jswjwBiz.searchJointOrgsByOrg(sysOrg.getOrgFlow());
				if(joinOrgs!=null&&joinOrgs.size()>0)
				{
					for(SysOrg joinOrg:joinOrgs)
						jointOrgFlowList.add(joinOrg.getOrgFlow());
				}
			}
		}
		param.put("jointOrgFlowList",jointOrgFlowList);
		List<ResJointOrg> jointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
		if (null == jointOrgList || jointOrgList.size()==0){
			param.put("orgFlow1",orgFlow);
		}else {
			param.put("orgFlow1",jointOrgList.get(0).getOrgFlow());
            param.put("isJointOrg", GlobalConstant.FLAG_Y);
		}
//		List<Map<String,Object>> list=recruitDoctorInfoBiz.zltjOrgLocalList(param);
		List<Map<String,Object>> list=jswjwBiz.zltjOrgLocalListNew(param);

		List<SysDict> sysDictList = jswjwBiz.getDictListByDictId(TrainCategoryEnum.DoctorTrainingSpe.getId());
		for (int i = 0; i < sysDictList.size(); i++) {
			if (sysDictList.get(i).getDictId().equals("50")) {
				sysDictList.remove(i);
				i--;
			}
			if (StringUtil.isNotBlank(trainingSpeId)) {
				if (!sysDictList.get(i).getDictId().equals(trainingSpeId)) {
					sysDictList.remove(i);
					i--;
				}
			}
		}

		Map<String,Object> typeSpeNumMap=new HashMap<>();
		Map<String,Integer> speNumMap=new HashMap<>();
		Map<String,Integer> typeNumMap=new HashMap<>();
		List<List<String>> numList = new ArrayList<>();
		for (SysDict sysDict : sysDictList) {
			List<String> s = new ArrayList<>();
			s.add(sysDict.getDictName());
			s.add("0");
			s.add("0");
			s.add("0");
			s.add("0");
			if(list!=null) {
				for(Map<String,Object> map:list) {

					if (map.get("speId").equals(sysDict.getDictId())) {
						if (map.get("typeId").equals("Company")) {
							s.remove(1);
							s.add(1, String.valueOf( map.get("num")));
						}
						if (map.get("typeId").equals("CompanyEntrust")) {
							s.remove(2);
							s.add(2, String.valueOf( map.get("num")));
						}
						if (map.get("typeId").equals("Social")) {
							s.remove(3);
							s.add(3, String.valueOf( map.get("num")));
						}
						if (map.get("typeId").equals("Graduate")) {
							s.remove(4);
							s.add(4, String.valueOf( map.get("num")));
						}
					}
				}
			}
			numList.add(s);
		}
		if(list!=null) {
			for(Map<String,Object> map:list) {

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

		resultMap.put("typeSpeNumMap",typeSpeNumMap);
		resultMap.put("speNumMap",speNumMap);
		resultMap.put("typeNumMap",typeNumMap);
		resultMap.put("numList",numList);

		return  resultMap;
	}

	// 获取基地以及协同基地列表
	@RequestMapping(value="/getJointOrg")
	@ResponseBody
	public Object getJointOrg(String userFlow) {
		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		List<SysOrg> orgList=new ArrayList<>();
		SysOrg doctorOrg=new SysOrg();
		doctorOrg.setOrgFlow(sysuser.getOrgFlow());
		doctorOrg.setOrgName(sysuser.getOrgName());
		SysOrg doctorOrg1=new SysOrg();
		doctorOrg1.setOrgFlow("");
		doctorOrg1.setOrgName("全部");
		orgList = jswjwBiz.searchJointOrgsByOrg(sysuser.getOrgFlow());
		orgList.add(0, doctorOrg1);
		orgList.add(1, doctorOrg);
		return orgList;
	}

	// 获取基地科室
	@RequestMapping(value="/getOrgDept")
	@ResponseBody
	public Object getOrgDept(String userFlow, String orgFlow) {
		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		String searchFlow="";
		if(StringUtil.isBlank(orgFlow)){
			searchFlow=sysuser.getOrgFlow();
		}else{
			searchFlow=orgFlow;
		}
		List<SysDept> deptList = jswjwBiz.searchDeptByOrg(searchFlow);
		SysDept sysDept = new SysDept();
		sysDept.setDeptFlow("");
		sysDept.setDeptName("全部");
		deptList.add(0, sysDept);
		return deptList;
	}

	// 获取基地轮转科室
	@RequestMapping(value="/getSchDept")
	@ResponseBody
	public Object getOrgDept(String userFlow) {
		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		SysDeptExample example=new SysDeptExample();
		SysDeptExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andOrgFlowEqualTo(sysuser.getOrgFlow());
		List<SysDept> allDeptList=sysDeptMapper.selectByExample(example);
		SysDept sysDept = new SysDept();
		sysDept.setDeptFlow("");
		sysDept.setDeptName("全部");
		allDeptList.add(0, sysDept);
		return allDeptList;
	}

	// 获取基地教学活动类型
	@RequestMapping(value="/getActivityTypes")
	@ResponseBody
	public Object getActivityTypes() {
		List<Map<String, String>> resultList = new ArrayList<>();
		for (ActivityTypeEnum activityType : ActivityTypeEnum.values()) {
			HashMap<String, String> map = new HashMap<>();
			map.put("ActivityId", activityType.getId());
			map.put("ActivityName", activityType.getName());
			resultList.add(map);
		}
		resultList.sort(Comparator.comparingInt(o -> o.get("ActivityId").hashCode()));
		HashMap<String, String> map = new HashMap<>();
		map.put("ActivityId", "");
		map.put("ActivityName", "全部");
		resultList.add(0, map);
		return resultList;
	}

	// 学员考勤统计,与web端保持一致
	@RequestMapping(value="/attendanceSearchTab",method = {RequestMethod.POST})
	@ResponseBody
	public Object attendanceSearchTab(String userFlow, String roleId, String schStartDate,
									  String schEndDate, String deptFlow, Integer pageIndex, Integer pageSize,
									  String trainingTypeId, String sessionNumber, String datas,
									  String trainingSpeId, String studentName, String searchType, String orgFlow, String baseFlag){

		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}

		//查询本基地的协同基地
		List<SysOrg> orgList=new ArrayList<>();
		SysOrg doctorOrg=new SysOrg();
		doctorOrg.setOrgFlow(sysuser.getOrgFlow());
		doctorOrg.setOrgName(sysuser.getOrgName());
		if (!GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			orgList = jswjwBiz.searchJointOrgsByOrg(sysuser.getOrgFlow());
			resultMap.put("JointOrgCount", orgList.size());
		}
		orgList.add(0,doctorOrg);
		List<String> orgFlowList=new ArrayList<>();
		if(orgList!=null&&orgList.size()>0){
			for (SysOrg so:orgList) {
				orgFlowList.add(so.getOrgFlow());
			}
		}

		String searchFlow="";
		if(StringUtil.isBlank(orgFlow)){
			searchFlow=sysuser.getOrgFlow();
		}else{
			searchFlow=orgFlow;
		}
		resultMap.put("searchFlow",searchFlow);
		List<SysDept> deptList = new ArrayList<>();
		Map<String,Object> beMap=new HashMap<String,Object>();
		if (!GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			deptList = jswjwBiz.searchDeptByOrg(searchFlow);
			if(StringUtil.isNotBlank(deptFlow)){
				beMap.put("deptFlow",deptFlow);
			}
		} else {
			List<SysUserDept> userDeptList = jswjwBiz.getUserDept(sysuser);
			if(StringUtil.isNotBlank(deptFlow)){
				beMap.put("deptFlow",deptFlow);
			} else {
				if(CollectionUtils.isNotEmpty(userDeptList)){
					beMap.put("deptFlows",userDeptList.stream().map(SysUserDept::getDeptFlow).collect(Collectors.toList()));
				}else{
					beMap.put("deptFlow", sysuser.getDeptFlow());
				}
			}
			for (SysUserDept sysUserDept : userDeptList) {
				SysDept sysDept = jswjwBiz.readSysDept(sysUserDept.getDeptFlow());
				deptList.add(sysDept);
			}
		}
		resultMap.put("deptList",deptList);
		if (GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			beMap.put("teacherFlow", sysuser.getUserFlow());
		}
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getLastDateOfCurrMonth();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.getFirstDayOfMonth();
		}

		List<String>docTypeList=new ArrayList<String>();
		if(StringUtil.isNotBlank(datas)){
			String[] s=datas.split(",");
			if(s.length > 0){
				docTypeList= Arrays.asList(s);
			}
		}
		beMap.put("docTypeList",docTypeList);
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("studentName",studentName);
		beMap.put("trainingTypeId", trainingTypeId);
		beMap.put("trainingSpeId",trainingSpeId);
		beMap.put("sessionNumber",sessionNumber);
		if(!"teacher".equals(roleId)){
			if(StringUtil.isNotBlank(orgFlow)){//平台优化11.17 二
				beMap.put("orgFlow",orgFlow);
			}else{
				beMap.put("orgFlowList",orgFlowList);
			}
		}
		if(StringUtil.isNotBlank(baseFlag)){
			beMap.put("baseFlag",baseFlag);
		}
		if (GlobalConstant.USER_LIST_SPELOCAL.equals(roleId) || GlobalConstant.USER_LIST_SPELOCALSECRETARY.equals(roleId)) {
			trainingSpeId = sysuser.getResTrainingSpeId();
			beMap.put("trainingSpeId", trainingSpeId);
		}
		System.out.println("baseFlag-------------:"+baseFlag);
		List<Map<String,String>> jsResAttendanceExts = new ArrayList<>();

		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);

		if (!GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleId) && !GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleId)) {
			jsResAttendanceExts=jswjwBiz.attendanceList2(beMap);
		} else {
			jsResAttendanceExts=jswjwBiz.attendanceList3(beMap);
		}
		resultMap.put("jsResAttendanceExts",jsResAttendanceExts);
		resultMap.put("roleId",roleId);
		resultMap.put("schEndDate",schEndDate);
		resultMap.put("schStartDate",schStartDate);
		resultMap.put("orgList",orgList);
		resultMap.put("baseFlag",baseFlag);
		resultMap.put("total",PageHelper.total);
		return resultMap;
	}

	/**
	 * @Author shengl
	 * @Description //学员请假统计 住院医师
	 **/
	@RequestMapping(value = "/kqStatisticsList",method = {RequestMethod.POST})
	@ResponseBody
	public Object kqStatisticsList(String userFlow, String roleFlag, String startDate, String endDate, String doctorName, Integer pageIndex,Integer pageSize) {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("startDate", startDate);
		paramMap.put("doctorName", doctorName);
		paramMap.put("endDate", endDate);
		paramMap.put("dictList", jswjwBiz.searchDictListByDictTypeId("LeaveType"));
		paramMap.put("orgFlow", sysuser.getOrgFlow());
		paramMap.put("trainingTypeId",TrainCategoryEnum.DoctorTrainingSpe.getId());
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String, Object>> resultMapList = jswjwBiz.getKqStatistics(paramMap);
		resultMap.put("resultMapList", resultMapList);
		resultMap.put("roleFlag", roleFlag);
		resultMap.put("total", PageHelper.total);
		return resultMap;
	}

	/**
	 * @Author shengl
	 * @Description //查看请假详情
	 **/
	@RequestMapping("/kqStatisticsDetail")
	@ResponseBody
	public Object kqStatisticsDetail(String userFlow, ResDoctorKq kq, Integer pageIndex, Integer pageSize){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}
		kq.setOrgFlow(sysuser.getOrgFlow());
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResDoctorKq> kqList = jswjwBiz.kqStatisticsDetail(kq);
		resultMap.put("kqList",kqList);
		resultMap.put("total", PageHelper.total);
		return resultMap;
	}

	//临时出科查询 住院医师
	@RequestMapping(value="/temporaryOutSearch",method = {RequestMethod.POST})
	@ResponseBody
	public Object temporaryOutSearch(String userFlow, Integer pageIndex, Integer pageSize, SchArrangeResult arrangeResult, String roleId, String temporaryAuditStatusId, String biaoJi, String datas){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}

		Map<String, Object> schArrangeResultMap=new HashMap<String, Object>();
		schArrangeResultMap.put("deptFlow", sysuser.getDeptFlow());
		schArrangeResultMap.put("userFlow", sysuser.getUserFlow());
		schArrangeResultMap.put("doctorName", arrangeResult.getDoctorName());
		schArrangeResultMap.put("schStartDate", arrangeResult.getSchStartDate());
		schArrangeResultMap.put("schEndDate", arrangeResult.getSchEndDate());
		schArrangeResultMap.put("temporaryAuditStatusId", temporaryAuditStatusId);
		schArrangeResultMap.put("roleId", roleId);
		if("org".equals(roleId)){
			schArrangeResultMap.put("orgFlow", sysuser.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		if(StringUtil.isNotBlank(datas)){
			String[] s=datas.split(",");
			if(s.length > 0){
				docTypeList= Arrays.asList(s);
			}
		}else{
			for (JsResDocTypeEnum type:JsResDocTypeEnum.values()) {
				docTypeList.add(type.getId());
			}
		}
		schArrangeResultMap.put("docTypeList", docTypeList);
        schArrangeResultMap.put("isAfter", GlobalConstant.FLAG_Y);
		schArrangeResultMap.put("biaoJi", biaoJi);
		schArrangeResultMap.put("trainingTypeId", TrainCategoryEnum.DoctorTrainingSpe.getId());
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String, String>> schArrangeResult=jswjwBiz.temporaryOutSearch(schArrangeResultMap);
		resultMap.put("schArrangeResult", schArrangeResult);
		resultMap.put("total", PageHelper.total);
		return resultMap;
	}

	// 出科信息查询
	@RequestMapping("/doctorRecruitResultDetail")
	@ResponseBody
	public Object doctorRecruitResultDetail(String userFlow, String doctorFlow, String roleId){

		Map<String,Object> resultMaps = new HashMap<>();
		resultMaps.put("resultId", "200");
		resultMaps.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}

		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = jswjwBiz.readDoctor(doctorFlow);
			Map<String,String> pMap = new HashMap<>();
			if("kzr".equals(roleId)){
				pMap.put("deptFlow",sysuser.getDeptFlow());
				pMap.put("kzrFlow",sysuser.getUserFlow());
				pMap.put("doctorFlow",doctorFlow);
			}else {
				pMap.put("doctorFlow",doctorFlow);
			}
			List<SchArrangeResult> arrResultList = jswjwBiz.searchSchArrangeResultByMap(pMap);
			Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
			Map<String, ResDoctorSchProcess> resultMap = new HashMap<>();
			Map<String,Map<String,Object>> skillMap = new HashMap<>();
			Map<String,String> DOPSFlowMap = new HashMap<>();
			Map<String,String> MiniFlowMap = new HashMap<>();
			Map<String,String> AfterFlowMap = new HashMap<>();
			Map<String,String> AfterSummFlowMap = new HashMap<>();
			if(arrResultList!=null&&arrResultList.size()>0){
				for(SchArrangeResult schArrangeResult:arrResultList){
					String resultFlow = schArrangeResult.getResultFlow();

					String standardDeptId = schArrangeResult.getStandardDeptId();
					String standarGroupFlow = schArrangeResult.getStandardGroupFlow();
					SchRotationDept schRotationDept = deptMap.get(standarGroupFlow + standardDeptId);
					if (schRotationDept == null)
						schRotationDept = jswjwBiz.searchGroupFlowAndStandardDeptIdQuery(standarGroupFlow, standardDeptId);
					deptMap.put(standarGroupFlow + standardDeptId, schRotationDept);

					ResDoctorSchProcess doctorSchProcess = jswjwBiz.searchByResultFlow(resultFlow);
					resultMap.put(resultFlow,doctorSchProcess);
					if(doctorSchProcess!=null){
						String processFlow = doctorSchProcess.getProcessFlow();
						List<ResSchProcessExpress> resRecs = jswjwBiz.searchByProcessFlowClob(processFlow);
						if(resRecs!=null&&resRecs.size()>0)
						{
							for(ResSchProcessExpress r:resRecs)
							{
								if(AfterRecTypeEnum.DOPS.getId().equals(r.getRecTypeId()))
								{
									DOPSFlowMap.put(processFlow,r.getRecFlow());
								}
								if(AfterRecTypeEnum.Mini_CEX.getId().equals(r.getRecTypeId()))
								{
									MiniFlowMap.put(processFlow,r.getRecFlow());
								}
								if(AfterRecTypeEnum.AfterSummary.getId().equals(r.getRecTypeId()))
								{
									AfterSummFlowMap.put(processFlow,r.getRecFlow());
								}
								if(AfterRecTypeEnum.AfterEvaluation.getId().equals(r.getRecTypeId()))
								{
									AfterFlowMap.put(processFlow,r.getRecFlow());
									String recContent = r.getRecContent();
									skillMap.put(processFlow, jswjwBiz.parseRecContent(recContent));
								}
							}
						}
					}
				}
			}
			List<Map<String, Object>> list = new ArrayList<>();
			if(arrResultList!=null&&arrResultList.size()>0){
				for (SchArrangeResult result : arrResultList) {
					Map<String, Object> map = new HashMap<>();
					String key = "";
					if (resultMap.get(result.getResultFlow()) != null) {
						key = resultMap.get(result.getResultFlow()).getProcessFlow();
						map.put("deptFlow", resultMap.get(result.getResultFlow()).getDeptFlow());
						map.put("teacherName", resultMap.get(result.getResultFlow()).getTeacherUserName());
					}
					String key2 = result.getStandardGroupFlow() + result.getStandardDeptId();
					map.put("deptName", result.getSchDeptName());
					map.put("docFlow", result.getDoctorFlow());
					map.put("processFlow", key);
					map.put("schTime", result.getSchStartDate() + "~" + result.getSchEndDate());
					if (skillMap != null && skillMap.size() > 0) {
						if (skillMap.containsKey(key) && skillMap.get(key).get("theoreResult") != null) {
							map.put("theoreResult", skillMap.get(key).get("theoreResult"));
						} else {
							map.put("theoreResult", resultMap.get(result.getResultFlow()).getSchScore());
						}
					}
					if (skillMap != null && skillMap.size() > 0 && skillMap.containsKey(key)) {
						map.put("skillName", skillMap.get(key).get("skillName"));
						map.put("skillScore", skillMap.get(key).get("score"));
					}
					if (DOPSFlowMap != null && DOPSFlowMap.size() > 0 && DOPSFlowMap.containsKey(key) && DOPSFlowMap.get(key) != null) {
						map.put("DOPS", "查看");
					}
					if (MiniFlowMap != null && MiniFlowMap.size() > 0 && MiniFlowMap.containsKey(key) && MiniFlowMap.get(key) != null) {
						map.put("Mini-cex", "查看");
					}
					if (AfterFlowMap != null && AfterFlowMap.size() > 0 && AfterFlowMap.containsKey(key) && AfterFlowMap.get(key) != null) {
						map.put("After", "查看");
						map.put("recFlow", AfterFlowMap.get(key));
					}
					if (result.getHaveAfterPic().equals(GlobalConstant.FLAG_Y)) {
						map.put("AfterPic", "查看");
						map.put("recordFlow", deptMap.get(key2).getRecordFlow());
					}
					list.add(map);
				}
			}
			resultMaps.put("list",list);
			resultMaps.put("doctorName",doctor.getDoctorName());
			resultMaps.put("orgName",doctor.getOrgName());
			resultMaps.put("rotationName",doctor.getRotationName());
		}
		return resultMaps;
	}

	// 查看出科考核表附件
	@RequestMapping(value="/viewFile")
	@ResponseBody
	public Object viewFile(String recordFlow,String userFlow) throws DocumentException{

		ResSchProcessExpress express = new ResSchProcessExpress();
		SysUser user = jswjwBiz.readSysUser(userFlow);
		String recTypeId=ResRecTypeEnum.AfterSummary.getId();
		String recTypeName=ResRecTypeEnum.AfterSummary.getName();
		ResSchProcessExpress rec = jswjwBiz.queryResRec(recordFlow,user.getUserFlow(),recTypeId);
		if (rec==null) {
			express.setOrgFlow(user.getOrgFlow());
			express.setOrgName(user.getOrgName());
			express.setRecTypeId(recTypeId);
			express.setRecTypeName(recTypeName);
			String year=DateUtil.getCurrDateTime("yyyyMMddHHmmssSSS");
			express.setOperTime(year);
			express.setOperUserFlow(user.getUserFlow());
			express.setOperUserName(user.getUserName());
			express.setSchRotationDeptFlow(recordFlow);
			Document dom = DocumentHelper.createDocument();
			Element root= dom.addElement("AfterSummary");
			express.setRecContent(root.asXML());
			jswjwBiz.edit(express);
			rec= express;
		}
		String content=rec.getRecContent();
		List<Map<String, String>> imageList=jswjwBiz.parseImageXml(content);
		return imageList;
	}

	//出科查询
	@RequestMapping(value = "/doctorRecruitResult",method = {RequestMethod.POST})
	@ResponseBody
	public Object doctorRecruitResult(String userFlow, Integer pageIndex,Integer pageSize, ResDoctor doctor,SysUser user,String datas,String roleId,String baseFlag){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}
		SysOrg org=jswjwBiz.readSysOrg(sysuser.getOrgFlow());

		resultMap.put("org",org);
		List<SysOrg> orgs = new ArrayList<>();
		if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
			List<SysOrg> joinOrgs = jswjwBiz.searchJointOrgsByOrg(org.getOrgFlow());
			orgs.addAll(joinOrgs);
			orgs.add(org);
		}
		resultMap.put("orgs", orgs);
		List<String> jointOrgFlowList=new ArrayList<String>();
		if(StringUtil.isBlank(doctor.getOrgFlow())){
			jointOrgFlowList.add(sysuser.getOrgFlow());
			List<ResJointOrg> resJointOrgList=jswjwBiz.searchResJointByOrgFlow(sysuser.getOrgFlow());
			if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
				for(ResJointOrg jointOrg:resJointOrgList){
					jointOrgFlowList.add(jointOrg.getJointOrgFlow());
				}
			}
		}else {
			jointOrgFlowList.add(doctor.getOrgFlow());
		}
		List<String>docTypeList=new ArrayList<String>();
		if(StringUtil.isNotBlank(datas)){
			String[] s=datas.split(",");
			if(s.length > 0){
				docTypeList= Arrays.asList(s);
			}
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		if("kzr".equals(roleId)){
			List<SysUserDept> userDeptList = jswjwBiz.getUserDept(sysuser);

			if(CollectionUtils.isNotEmpty(userDeptList)){
				paramMap.put("deptList",userDeptList);
			}else{
				paramMap.put("deptFlow",sysuser.getDeptFlow());
			}
			paramMap.put("kzrFlow",sysuser.getUserFlow());
		}else {
			paramMap.put("jointOrgFlowList",jointOrgFlowList);
		}
		paramMap.put("userName",user.getUserName());
//		paramMap.put("trainingTypeId",TrainCategoryEnum.DoctorTrainingSpe.getId());
		paramMap.put("trainingTypeId",doctor.getTrainingTypeId());
		paramMap.put("trainingSpeId",doctor.getTrainingSpeId());
		paramMap.put("sessionNumber",doctor.getSessionNumber());
		paramMap.put("docTypeList",docTypeList);
		paramMap.put("graduationYear",doctor.getGraduationYear());
		paramMap.put("baseFlag",baseFlag);
		if("speAdmin".equals(roleId)) {
			paramMap.put("trainingSpeId",sysuser.getResTrainingSpeId());
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> docResultsList = jswjwBiz.searchDocResultsListNew(paramMap);
		resultMap.put("docResultsList",docResultsList);
		resultMap.put("total", PageHelper.total);
		return resultMap;
	}

	/**
	 * 科室轮转查询  住院医师
	 * */
	@RequestMapping(value = {"/doc/schDept" },method = {RequestMethod.POST})
	@ResponseBody
	public Object schDept (String startDate,String endDate,String schDeptFlow,String datas, String sessionNumber, String userFlow, Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}

		List<String> titleDate = null;
		boolean isWeek = SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if(!StringUtil.isNotBlank(startDate)){
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate,-8);
			resultMap.put("startDate",startDate);
			resultMap.put("endDate",endDate);
		}
		if(StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)){
			if(isWeek){
				titleDate = getWeeksByTwoDate(startDate,endDate);
			}else{
				String schStartMonth = startDate.substring(0,7);
				String schEndMonth = endDate.substring(0,7);
				titleDate = getMonthsByTwoMonth(schStartMonth,schEndMonth);
			}
			resultMap.put("titleDate",titleDate);
//			List<SchDept> schDeptList = jswjwBiz.searchSchDeptList(sysuser.getOrgFlow());
//			resultMap.put("schDeptList",schDeptList);
			SysDeptExample example=new SysDeptExample();
			SysDeptExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.FLAG_Y).andOrgFlowEqualTo(sysuser.getOrgFlow());
			List<SysDept> allDeptList=sysDeptMapper.selectByExample(example);
//			resultMap.put("allDeptList",allDeptList);
			if(StringUtil.isNotBlank(schDeptFlow))
			{
				criteria.andDeptFlowEqualTo(schDeptFlow);
			}
			List<String>docTypeList=new ArrayList<String>();
			//人员类型
			if(StringUtil.isNotBlank(datas)){
				String[] s=datas.split(",");
				if(s.length > 0){
					docTypeList= Arrays.asList(s);
				}
			}else{
				for (JsResDocTypeEnum type:JsResDocTypeEnum.values()) {
					docTypeList.add(type.getId());
				}
			}
			if(pageIndex==null){
				return ResultDataThrow("当前页码为空");
			}
			if(pageSize==null){
				return ResultDataThrow("每页条数为空");
			}
			PageHelper.startPage(pageIndex, pageSize);//分页,必须放在分页sql前
			List<SysDept> deptList=sysDeptMapper.selectByExample(example);
			resultMap.put("deptList",deptList);
			sessionNumber = "".equals(sessionNumber)?null:sessionNumber;
			Map<String,Object> parmMap=new HashMap<>();
			parmMap.put("schStartDate",startDate);
			parmMap.put("SchEndDate",endDate);
			parmMap.put("orgFlow",sysuser.getOrgFlow());
			parmMap.put("docTypeList",docTypeList);
			parmMap.put("doctorStatusId",20);		//在培
			parmMap.put("trainingSpeId",TrainCategoryEnum.DoctorTrainingSpe.getId());  //住院医师
//			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());
			List<SchArrangeResult> arrResultList = jswjwBiz.searchArrangeResultByDateAndOrg1(parmMap);
			if(arrResultList != null && arrResultList.size()>0 ){
				Map<String,List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for(SchArrangeResult sar : arrResultList){
					if(null != sessionNumber &&StringUtil.isNotBlank(sessionNumber)&& !sessionNumber.equals(sar.getSessionNumber())){//sessionNumber届别过滤
						continue;
					}
					if(null!=schDeptFlow &&StringUtil.isNotBlank(schDeptFlow)&& !schDeptFlow.equals(sar.getSchDeptFlow())) {//轮转科室过滤
						continue;
					}
					String schDeptFlow2 = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if(StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)){
						if(isWeek){
							List<String> weeks = getWeeksByTwoDate(resultStartDate,resultEndDate);
							if(weeks!=null){
								for(String week : weeks){
									String key = schDeptFlow2+week;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}else{
							resultStartDate = resultStartDate.substring(0,7);
							resultEndDate = resultEndDate.substring(0,7);
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate,resultEndDate);
							if(months!=null){
								for(String month : months){
									String key = schDeptFlow2+month;
									if(resultListMap.get(key)==null){
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key,sarList);
									}else{
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}
				resultMap.put("resultListMap",resultListMap);
			}
//			resultMap.put("datas",datas);
			resultMap.put("total", PageHelper.total);
		}
		return resultMap;
	}

	// 教学活动查询
	@RequestMapping(value="/activityQuery",method = {RequestMethod.POST})
	@ResponseBody
	public Object list(String activityName,String  roleFlag,String  isCurrent,String orderByClo,String orderByFall,
					   String userName,String activityTypeId,String deptFlow,String deptName,String isNew,String isEval,String isEffective,
					   String startTime,String endTime,String activityStatus, String userFlow, Integer pageIndex,Integer pageSize) throws DocumentException {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}
		SysOrg currentOrg = jswjwBiz.readSysOrg(sysuser.getOrgFlow());
		Map<String,String> param=new HashMap<>();
		param.put("activityName",activityName);
		param.put("userName",userName);
		param.put("activityTypeId",activityTypeId);
		param.put("deptFlow",deptFlow);
		param.put("deptName",deptName);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		param.put("isNew",isNew);//最新活动
		param.put("isEval",isEval);//活动评价
		param.put("roleFlag",roleFlag);
		param.put("isCurrent",isCurrent);
		param.put("activityStatus",activityStatus);
		param.put("orderByClo",orderByClo);
		param.put("orderByFall",orderByFall);
		param.put("userFlow",sysuser.getUserFlow());
		// 是否有效 1：是 0 ：否
		param.put("isEffective",isEffective);
		resultMap.put("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		resultMap.put("orderByClo", orderByClo);
		resultMap.put("orderByFall", orderByFall);
		if("doctor".equals(roleFlag)) {
			String orgFlow="";
			ResDoctor doctor=jswjwBiz.readDoctor(sysuser.getUserFlow());
			if(doctor!=null) {
				if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
					orgFlow = doctor.getSecondOrgFlow();
				} else {
					orgFlow = doctor.getOrgFlow();
				}
			}
			param.put("orgFlow", orgFlow);
			param.put("activityStatus","pass");//yuh20200513 学员查询教学活动只看已通过
		}else {
			param.put("orgFlow", sysuser.getOrgFlow());
		}
		List<SysUserRole> userRoleList = jswjwBiz.getByUserFlow(sysuser.getUserFlow()); //获取该用户下的所有角色信息
		if("university".equals(roleFlag) ){
			if(StringUtil.isNotEmpty(sysuser.getSchool())){
				param.put("school", sysuser.getSchool());
			}
			param.put("sendSchoolId", currentOrg.getSendSchoolId());
			param.put("sendSchoolName", currentOrg.getSendSchoolName());
		}
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=jswjwBiz.findActivityList(param);
		for (Map<String,Object> obj: list) {
			for (SysUserRole role:userRoleList) {
				if(obj.containsKey("auditRole")) {
					if (obj.get("auditRole").toString().contains(role.getRoleFlow())) {
                        obj.put("audit", GlobalConstant.FLAG_Y);
					}
				}
			}
		}
		Map<String,Object> result = new HashMap<>();
		if(list!=null) {
			for (Map<String,Object> info:list )
			{
                info.put("HaveImg", GlobalConstant.FLAG_N);
				String imageUrl= (String) info.get("imageUrl");
				List<Map<String, String>> imageList = new ArrayList<>();
				if(StringUtil.isNotBlank(imageUrl))
				{
					Document document= DocumentHelper.parseText(imageUrl);
					Element elem=document.getRootElement();
					List<Element> ec = elem.elements("image");
					for (Element element : ec) {
						Element imageUrl1 = element.element("imageUrl");
						Map<String, String> image = new HashMap<>();
						image.put("imageUrl", imageUrl1.getText());
						imageList.add(image);
					}
					if(ec!=null&&ec.size()>0)
					{
                        info.put("HaveImg", GlobalConstant.FLAG_Y);
					}
				}
				info.put("imageList", imageList);
				if(!"doctor".equals(roleFlag))
				{
					List<Map<String,Object>>  results = jswjwBiz.readActivityResults((String) info.get("activityFlow"));
					result.put((String) info.get("activityFlow"),results);
				}
			}
		}
		resultMap.put("result",result);
		resultMap.put("list",list);
		resultMap.put("total",PageHelper.total);
		return resultMap;
	}

	// 查看教学活动详情
	@RequestMapping(value="/activityDetail")
	@ResponseBody
	public Object activityDetail(String activityFlow,String userFlow){

		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}

		Map<String,Object> activity=jswjwBiz.readActivity(activityFlow);
		resultMap.put("activity",activity);
		//查询附件
		List<PubFile> fileList = jswjwBiz.findFileByTypeFlow("activity",activityFlow);
		for (PubFile pubFile : fileList) {
			pubFile.setFilePath(InitConfig.getSysCfg("upload_base_url")+pubFile.getFilePath());
		}
		resultMap.put("fileList",fileList);

		TeachingActivitySpeakerExample example = new TeachingActivitySpeakerExample();
		TeachingActivitySpeakerExample.Criteria criteria = example.createCriteria();
		criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andActivityFlowEqualTo(activityFlow);
		example.setOrderByClause("CREATE_TIME DESC");
		List<TeachingActivitySpeaker> list = activitySpeakerMapper.selectByExample(example);
		if (null!=list && list.size()>0){
			resultMap.put("lastSpeakerName",list.get(0).getSpeakerName());
		}
		TeachingActivityInfo activityInfo = jswjwBiz.readActivityInfo(activityFlow);
		if (StringUtil.isBlank(activityInfo.getIsLook())){
			activityInfo.setIsLook(GlobalConstant.FLAG_Y);
			jswjwBiz.saveActivity(activityInfo,userFlow);
		}
		return resultMap;
	}

	// 认可或不认可教学活动
	@RequestMapping(value="/effectiveActivity")
	@ResponseBody
	public String effectiveActivity(String userFlow, String activityFlow,String isAccept, String reasonForDisagreement){
		if(StringUtil.isNotBlank(activityFlow))
		{
			if(StringUtil.isBlank(isAccept))
			{
				return "请选择需要审核的类型！";
			}
			if(!GlobalConstant.FLAG_Y.equals(isAccept)&&!GlobalConstant.FLAG_N.equals(isAccept))
			{
				return "请选择【认可】还是【不认可】！";
			}
			TeachingActivityInfo info=jswjwBiz.readActivityInfo(activityFlow);
			if(info==null)
				return "活动信息不存在，请刷新列表页面！";
			info.setIsEffective(isAccept);
			if (GlobalConstant.FLAG_Y.equals(isAccept)) {
				info.setReasonForDisagreement("");
			}
			if (GlobalConstant.FLAG_N.equals(isAccept)) {
				info.setReasonForDisagreement(reasonForDisagreement);
			}
			int c=jswjwBiz.saveActivity(info, userFlow);
			if(c==0)
				return "审核失败";
			return "审核成功";
		}else
			return "请选择需要审核的活动！";

	}

	// 下载教学活动附件
	@RequestMapping(value = {"/downFile" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile file = this.jswjwBiz.readFile(fileFlow);
		downPubFile(file,response);
	}

	public void downPubFile(PubFile file, HttpServletResponse response) throws Exception {
		/*文件是否存在*/
		Boolean fileExists = false;
		if(file !=null){
			byte[] data=null;
			long dataLength = 0;
			/*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
			if(StringUtil.isNotBlank(file.getFilePath())&&StringUtil.isNotBlank(InitConfig.getSysCfg("upload_base_dir"))){
				/*获取文件物理路径*/
				String filePath =InitConfig.getSysCfg("upload_base_dir")+file.getFilePath();
				File downLoadFile = new File(filePath);
				/*文件是否存在*/
				if(downLoadFile.exists()){
					InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
					data = new byte[fis.available()];
					dataLength = downLoadFile.length();
					fis.read(data);
					fis.close();
					fileExists = true;
				}
			}
			if(fileExists) {
				try {

					String fileName = file.getFileName();
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" )  + "\"");
					response.addHeader("Content-Length", "" + dataLength);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
					if (data != null) {
						outputStream.write(data);
					}
					outputStream.flush();
					outputStream.close();
				}catch (IOException e){
					fileExists = false;
				}
			}
		}else {
			fileExists = false;
		}
		if(!fileExists){
			/*设置页面编码为UTF-8*/
			response.setHeader("Content-Type","text/html;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}

	//科室活动统计
	@RequestMapping(value="/deptActivityStatistics",method = {RequestMethod.POST})
	@ResponseBody
	public Object list(String deptFlow,String notNull, String startTime,String endTime, String userFlow, Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}

		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);

		List<Map<String, Object>> deptList = jswjwBiz.getDeptCountActivityStatisticsList(sysuser.getOrgFlow(), deptFlow, startTime, endTime,notNull);
		Map<String,Object> map=new HashMap<>();
		if(deptList!=null&&deptList.size()>0)
		{
			for (Map<String, Object> deptMap : deptList) {
				map.put(deptMap.get("deptFlow").toString(),jswjwBiz.getDeptActivityStatisticsMap(deptMap.get("deptFlow").toString(),startTime,endTime));
			}
		}
		Map<String, Object> param=new HashMap<>();
		param.put("orgFlow",sysuser.getOrgFlow());
		param.put("deptFlow",deptFlow);
		param.put("startTime",startTime);
		param.put("endTime",endTime);
		List<Map<String,String>> numList= jswjwBiz.schProcessStudentDistinctQuery2(param);
		HashMap<String, String> countMap = new HashMap<>();
		if (null != numList && !numList.isEmpty()){
			for (Map<String, String> m : numList) {
				countMap.put(m.get("deptFlow"),m.get("num"));
			}
			resultMap.put("countMap",countMap);
		}

		for (Map<String, Object> dept : deptList) {
			Map<String, Object> activityNum = new HashMap<>();
			for (ActivityTypeEnum activityType : ActivityTypeEnum.values()) {
				activityNum.put(activityType.getName(), "0");
			}
			activityNum.put("轮转人数", "0");
			Set<String> keys = countMap.keySet();
			for (String key : keys) {
				if (key.equals(dept.get("deptFlow"))) {
					activityNum.put("轮转人数", countMap.get(key));
				}
			}
			Set<String> keySet = map.keySet();
			for (String key : keySet) {
				if (dept.get("deptFlow").equals(key)) {
					Map o = (Map)map.get(key);
					Set set = o.keySet();
					for (Object key1 : set) {
						for (ActivityTypeEnum activityType : ActivityTypeEnum.values()) {
							if (key1.equals(key + activityType.getId())) {
								activityNum.put(activityType.getName(), o.get(key1));
							}
						}
					}
				}
			}
			dept.put("resultMap", activityNum);
		}

//		resultMap.put("map",map);
		resultMap.put("list",deptList);
		resultMap.put("total", PageHelper.total);
		return resultMap;
	}

	/**
	 * 绩效-学员填写量列表 住院医师
	 */
	@RequestMapping(value = "/doctorDataList",method = {RequestMethod.POST})
	@ResponseBody
	public Object doctorDataList(String roleFlag,String orgFlow,String userName,String idNo,String speId,String sessionNumber
			,String datas, String userFlow, Integer pageIndex,Integer pageSize){
		List<String> docTypeList=new ArrayList<String>();
		if(StringUtil.isNotBlank(datas)){
			String[] s=datas.split(",");
			if(s.length > 0){
				docTypeList= Arrays.asList(s);
			}
		}

		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}

		Map<String,Object> param = new HashMap<>();
		param.put("userName",userName);
		param.put("idNo",idNo);
		param.put("speId",speId);
		param.put("sessionNumber",sessionNumber);
		param.put("docTypeList",docTypeList);
		SysOrg searchOrg = new SysOrg();
		SysOrg s = jswjwBiz.readSysOrg(sysuser.getOrgFlow());
		searchOrg.setOrgProvId(s.getOrgProvId());
		searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		if(roleFlag.equals(GlobalConstant.USER_LIST_LOCAL)) {
			param.put("orgFlow", sysuser.getOrgFlow());
		}else if(roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)) {
			searchOrg.setOrgCityId(s.getOrgCityId());
			param.put("orgFlow",orgFlow);
		}else if(roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL)){
			param.put("orgFlow",orgFlow);
		}
		param.put("searchOrg",searchOrg);
//		param.put("trainingTypeId","DoctorTrainingSpe");	//住院医师
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
//		List<Map<String,Object>> list = resStatisticBiz.searchDoctorData(param);
		//List<Map<String,Object>> list = resStatisticBiz.searchDoctorDataNew(param);
		List<Map<String,Object>> list = jswjwBiz.searchDoctorDataNew2(param);
		resultMap.put("list",list);
		resultMap.put("roleFlag", roleFlag);
		resultMap.put("datas", datas);
		resultMap.put("total", PageHelper.total);
		return resultMap;
	}

	/**
	 * 绩效-带教审核量列表
	 */
	@RequestMapping(value = "/teacherAuditList",method = {RequestMethod.POST})
	@ResponseBody
	public Object teacherAuditList(String roleFlag,String teacherName,String deptFlow,String orgFlow, String userFlow, Integer pageIndex,Integer pageSize){

		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}

		Map<String,Object> param = new HashMap<>();
		param.put("teacherName",teacherName);
		param.put("deptFlow",deptFlow);
		param.put("roleFlow",InitConfig.getSysCfg("res_teacher_role_flow"));
		SysOrg searchOrg = new SysOrg();
		SysOrg s = jswjwBiz.readSysOrg(sysuser.getOrgFlow());
		searchOrg.setOrgProvId(s.getOrgProvId());
		searchOrg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
		if(roleFlag.equals(GlobalConstant.USER_LIST_LOCAL)) {
			param.put("orgFlow", sysuser.getOrgFlow());
		}else if(roleFlag.equals(GlobalConstant.USER_LIST_CHARGE)) {
			searchOrg.setOrgCityId(s.getOrgCityId());
			param.put("orgFlow",orgFlow);
		}else if(roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL)) {
			param.put("orgFlow",orgFlow);
		}
		param.put("searchOrg",searchOrg);
		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list = jswjwBiz.searchTeacherUserList(param);
		if (null != list && list.size() > 0) {
			List<String> teacherFlows = new ArrayList<>();
			for (Map<String, Object> tmap : list) {
				teacherFlows.add((String)tmap.get("USER_FLOW"));
			}
			param.put("teacherFlows",teacherFlows);
			List<Map<String,Object>> auditList = jswjwBiz.searchTeacherAuditList(param);
			for (Map<String, Object> tmap : list) {
				String teacherFlow = (String)tmap.get("USER_FLOW");
				tmap.put("notAudited", "0");
				tmap.put("isAudited", "0");
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
		resultMap.put("list",list);
		resultMap.put("roleFlag", roleFlag);
		resultMap.put("total", PageHelper.total);
		return resultMap;
	}

	/**
	 * 住院医师首考
	 * @return
	 */
	@RequestMapping(value="/auditList",method = {RequestMethod.POST})
	@ResponseBody
	public Object auditList(String roleFlag, String orgFlow,String trainingTypeId,
							String trainingSpeId,String datas[], String sessionNumber, String graduationYear,String qualificationMaterialId,
							String passFlag, String userName,String idNo, String completeBi,String auditBi,String auditStatusId,String testId,
							String isNotMatch,String applyYear,String tabTag,String joinOrgFlow,String isPostpone, String userFlow, Integer pageIndex,Integer pageSize){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser sysuser = jswjwBiz.readSysUser(userFlow);
		if(sysuser==null){
			return ResultDataThrow("用户不存在");
		}
		SysOrg currentOrg = jswjwBiz.readSysOrg(sysuser.getOrgFlow());

		//查询条件
		Map<String,Object> param=new HashMap<>();
		List<String> orgFlowList = new ArrayList();
		List<String> jointOrgFlowList = new ArrayList();
		SysOrg org = jswjwBiz.readSysOrg(orgFlow);
		if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())){
			orgFlowList.add(orgFlow);
			if(StringUtil.isNotBlank(joinOrgFlow) && orgFlow.equals(joinOrgFlow)) {
				joinOrgFlow = "isNull";
			}
		}else{
			List<ResJointOrg> jointOrgList = jswjwBiz.selectByJointOrgFlow(orgFlow);
			if(null != jointOrgList && jointOrgList.size()>0){
				if("DoctorTrainingSpe".equals(trainingTypeId)) {
					orgFlowList.add(jointOrgList.get(0).getOrgFlow());
					joinOrgFlow = jointOrgList.get(0).getJointOrgFlow();
				}else{
					orgFlowList.add(jointOrgList.get(0).getJointOrgFlow());
					joinOrgFlow = jointOrgList.get(0).getJointOrgFlow();
				}
			}
		}
//        param.put("jointOrgFlowList",jointOrgFlowList);//协同基地
		List<String>docTypeList=new ArrayList<String>();//人员类型
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		param.put("docTypeList",docTypeList);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("graduationYear",graduationYear);
		param.put("qualificationMaterialId",qualificationMaterialId);
		param.put("passFlag",passFlag);
		param.put("userName",userName);
		param.put("idNo",idNo);
		param.put("testId",testId);
		param.put("isNotMatch",isNotMatch);
		param.put("completeBi",completeBi);
		param.put("auditBi",auditBi);
		param.put("auditStatusId",auditStatusId);
		param.put("applyYear",applyYear);
		param.put("roleFlag",roleFlag);
		param.put("tabTag",tabTag);

		param.put("isPostpone",isPostpone);
		//判断是否是协同基地
        String isJointOrg = GlobalConstant.FLAG_N;
		List<ResJointOrg> tempJoinOrgs = jswjwBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
		if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            isJointOrg = GlobalConstant.FLAG_Y;
		}else{
			if(null != orgFlowList && orgFlowList.size()==0){
				orgFlowList.add(sysuser.getOrgFlow());
			}
		}
		param.put("orgFlowList",orgFlowList);//培训基地
		param.put("jointOrgFlow",joinOrgFlow);
		param.put("isJointOrg",isJointOrg);

		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);

		//禅道2143 主基地查询数据不包含协同基地助理全科
		List<Map<String,Object>> list=jswjwBiz.chargeQueryApplyList2(param);
//        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
//        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyListNew(param);
		if(StringUtil.isNotBlank(roleFlag)){
			resultMap.put("roleFlag",roleFlag);
		}
		resultMap.put("list",list);
       /* String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate=InitConfig.getSysCfg("local_submit_start_time");
        String endDate=InitConfig.getSysCfg("local_submit_end_time");*/
        String f = GlobalConstant.FLAG_N;
		List<ResTestConfig> testConfigList = jswjwBiz.findLocalEffective(DateUtil.getCurrDateTime2());
		if (testConfigList.size() > 0) {
            f = GlobalConstant.FLAG_Y;
		}
        /*if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
            {
                f=GlobalConstant.FLAG_Y;
            }
        }*/
		resultMap.put("f",f);
		resultMap.put("currentUser",sysuser);
		resultMap.put("roleFlag",roleFlag);
		return resultMap;
	}

	@RequestMapping(value="/signList",method = {RequestMethod.POST})
	@ResponseBody
	public Object signList(String roleFlag,String typeId, String userFlow, Integer pageIndex,Integer pageSize,
						   String signupYear, String orgFlow, String trainingTypeId,String trainingYears, String trainingSpeId, String datas[],
						   String sessionNumber,  String userName, String idNo, String auditStatusId, String cityId,String signUpTypeId,
						   String testId,String jointOrgFlag,String tabTag, String joinOrgFlow,String isPostpone){
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultId", "200");
		resultMap.put("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			return ResultDataThrow("用户标识符为空");
		}

		SysUser currentUser = jswjwBiz.readSysUser(userFlow);
		if(currentUser==null){
			return ResultDataThrow("用户不存在");
		}
		//查询条件
		Map<String,Object> param=new HashMap<>();
		List<String> orgFlowList=new ArrayList<String>();
        String f = GlobalConstant.FLAG_N;
		if(GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
			SysOrg currentOrg = jswjwBiz.readSysOrg(currentUser.getOrgFlow());
			orgFlowList.add(currentOrg.getOrgFlow());
			if(OrgLevelEnum.CountryOrg.getId().equals(currentOrg.getOrgLevelId())) {
				List<SysOrg> joinOrgs=jswjwBiz.searchJointOrgsByOrg(currentOrg.getOrgFlow());
				if(joinOrgs!=null)
				{
					for(SysOrg sysOrg:joinOrgs)
					{
						orgFlowList.add(sysOrg.getOrgFlow());
					}
				}
			}
			List<ResTestConfig> localEffective = jswjwBiz.findLocalEffective(DateUtil.getCurrDateTime2());
			if (localEffective.size() > 0) {
				if (DateUtil.getYear().equals(signupYear)) {
                    f = GlobalConstant.FLAG_Y;
				}
			}
		}else if(GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
			List<ResTestConfig> chargeEffective = jswjwBiz.findChargeEffective(DateUtil.getCurrDateTime2());
			if (chargeEffective.size() > 0) {
				if (DateUtil.getYear().equals(signupYear)) {
                    f = GlobalConstant.FLAG_Y;
				}
			}
		}else if(GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
			List<ResTestConfig> globalEffective = jswjwBiz.findGlobalEffective(DateUtil.getCurrDateTime2());
			if (globalEffective.size() > 0) {
				if (DateUtil.getYear().equals(signupYear)) {
                    f = GlobalConstant.FLAG_Y;
				}
			}
		}

		// bug 市局账号 地区数据权限限制有误，与省厅查询到一样数据
		param.put("orgFlowList",orgFlowList);//培训基地list
		List<String>docTypeList=new ArrayList<String>();//人员类型
		if(datas!=null&&datas.length>0){
			for(String s:datas){
				docTypeList.add(s);
			}
		}
		param.put("typeId",typeId);
		param.put("docTypeList",docTypeList);
		param.put("trainingTypeId",trainingTypeId);
		param.put("trainingSpeId",trainingSpeId);
		param.put("sessionNumber",sessionNumber);
		param.put("trainingYears",trainingYears);
		param.put("signupYear",signupYear);
		param.put("userName",userName);
		param.put("idNo",idNo);
		param.put("auditStatusId",auditStatusId);
		param.put("signUpTypeId",signUpTypeId);
		param.put("testId",testId);
//        param.put("orgFlow",orgFlow);
		param.put("roleFlag",roleFlag);
		param.put("tabTag",tabTag);
		param.put("jointOrgFlow",joinOrgFlow);
		param.put("isPostpone",isPostpone);
		List<String> speIds=getSpeIds(currentUser);
		if(speIds!=null && speIds.size() > 0)
		{
			param.put("speIds", speIds);
		}

		if(pageIndex==null){
			return ResultDataThrow("当前页码为空");
		}
		if(pageSize==null){
			return ResultDataThrow("每页条数为空");
		}
		PageHelper.startPage(pageIndex, pageSize);

		List<Map<String,Object>> list=jswjwBiz.queryExamSignUpList(param);
		Map<String,String> trainMap = new HashMap<>();
		if (list.size() > 0) {
			for (Map<String, Object> stringObjectMap : list) {
				ResDoctorRecruit recruit = new ResDoctorRecruit();
				recruit.setDoctorFlow((String) stringObjectMap.get("doctorFlow"));
				recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				List<ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
				if (recruitList.size() > 0) {
					recruit = recruitList.get(0);
					String endTime = "";
					String startTime = "";
					//开始时间
					String recruitDate = recruit.getRecruitDate();
					//培养年限
					String trianYear = recruit.getTrainYear();
					String graudationYear = recruit.getGraduationYear();
					if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(graudationYear)) {
						try {
							int year = 0;
							year = Integer.valueOf(graudationYear) - Integer.valueOf(recruitDate.substring(0, 4));
							if (year != 0) {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date date = sdf.parse(recruitDate);
								startTime = recruitDate;
								//然后使用Calendar操作日期
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(date);
								calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
								calendar.add(Calendar.DATE, -1);
								//把得到的日期格式化成字符串类型的时间
								endTime = sdf.format(calendar.getTime());
							}
						} catch (Exception e) {
							endTime = "";
						}
					}
					//如果没有结业考核年份，按照届别与培养年限计算
					if (StringUtil.isNotBlank(recruitDate) && StringUtil.isNotBlank(trianYear) && StringUtil.isBlank(endTime)) {
						int year = 0;
						if (JsResTrainYearEnum.OneYear.getId().equals(trianYear)) {
							year = 1;
						}
						if (JsResTrainYearEnum.TwoYear.getId().equals(trianYear)) {
							year = 2;
						}
						if (JsResTrainYearEnum.ThreeYear.getId().equals(trianYear)) {
							year = 3;
						}
						if (year != 0) {
							try {
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								Date date = sdf.parse(recruitDate);
								startTime = recruitDate;
								//然后使用Calendar操作日期
								Calendar calendar = Calendar.getInstance();
								calendar.setTime(date);
								calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
								calendar.add(Calendar.DATE, -1);
								//把得到的日期格式化成字符串类型的时间
								endTime = sdf.format(calendar.getTime());

							} catch (Exception e) {

							}
						}
					}
					trainMap.put((String)stringObjectMap.get("signupFlow")+"startDate",startTime);
					trainMap.put((String)stringObjectMap.get("signupFlow")+"endTime",endTime);
				}
			}
		}
		resultMap.put("trainMap", trainMap);
		resultMap.put("typeId", typeId);
		resultMap.put("list", list);
		resultMap.put("f", f);
		resultMap.put("roleFlag", roleFlag);
		return resultMap;
	}

	public  List<String> getSpeIds(SysUser user)
	{
		List<String> speIds=new ArrayList<>();

		speIds.add("0700"); //住院医师的 全科
		speIds.add("51");//一阶段 全科方向（中医）
		speIds.add("52");//一阶段 全科方向（西医）
		speIds.add("18");//助理全科 全科方向（中医）
		speIds.add("50");//助理全科 全科方向（西医）
		if("stqky".equals(user.getUserCode()))
		{
			return  speIds;
		}else if("stzyy".equals(user.getUserCode())){
			List<String> newSpeIds=new ArrayList<>();
			List<SysDict> sysDictList = jswjwBiz.searchDictListByDictTypeId(DictTypeEnum.DoctorTrainingSpe.getId()); //住院医师的
			if(sysDictList!=null){
				for(SysDict dict:sysDictList)
				{
					if(!speIds.contains(dict.getDictId()))
					{
						newSpeIds.add(dict.getDictId());
					}
				}
			}
			sysDictList = jswjwBiz.searchDictListByDictTypeId(DictTypeEnum.WMFirst.getId()); //一阶段
			if(sysDictList!=null){
				for(SysDict dict:sysDictList)
				{
					if(!speIds.contains(dict.getDictId()))
					{
						newSpeIds.add(dict.getDictId());
					}
				}
			}
			sysDictList = jswjwBiz.searchDictListByDictTypeId(DictTypeEnum.WMSecond.getId()); //二阶段
			if(sysDictList!=null){
				for(SysDict dict:sysDictList)
				{
					if(!speIds.contains(dict.getDictId()))
					{
						newSpeIds.add(dict.getDictId());
					}
				}
			}
			sysDictList = jswjwBiz.searchDictListByDictTypeId(DictTypeEnum.AssiGeneral.getId()); //助理全科
			if(sysDictList!=null){
				for(SysDict dict:sysDictList)
				{
					if(!speIds.contains(dict.getDictId()))
					{
						newSpeIds.add(dict.getDictId());
					}
				}
			}
			return newSpeIds;
		}
		return null;
	}
}

