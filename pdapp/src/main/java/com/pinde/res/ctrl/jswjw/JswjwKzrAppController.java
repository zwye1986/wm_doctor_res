package com.pinde.res.ctrl.jswjw;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.*;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.hbres.IResInprocessInfoBiz;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IJswjwKzrBiz;
import com.pinde.res.biz.jswjw.IJswjwTeacherBiz;
import com.pinde.res.biz.jswjw.IResDoctorProcessBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.res.model.jswjw.mo.ResAssessCfgItemForm;
import com.pinde.res.model.jswjw.mo.ResAssessCfgTitleForm;
import com.pinde.sci.dao.base.JsresPowerCfgMapper;
import org.dom4j.*;
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
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/jswjw/kzr")
public class JswjwKzrAppController {    
	private static Logger logger = LoggerFactory.getLogger(JswjwKzrAppController.class);
	
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/jswjw/500";
    }
	
	@Autowired
	private IJswjwBiz jswjwBiz;
	@Autowired
	private IResGradeBiz gradeBiz;
	@Autowired
	private IJswjwTeacherBiz jswjwTeacherBiz;
	@Autowired
	private IJswjwKzrBiz jswjwKzrBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IResInprocessInfoBiz resInprocessInfoBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private IInxInfoBiz inxInfoBiz;
	@Autowired
	private IResActivityBiz activityBiz;
	@Autowired
	private IResActivityTargetBiz activityTargeBiz;
	@Autowired
	private ResPaperBiz paperBiz;
	@Autowired
	private JsresPowerCfgMapper jsresPowerCfgMapper;

	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/jswjw/kzr/test";
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
		return "/res/jswjw/kzr/test";
	}
	@RequestMapping(value={"/index"})
	public String index(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/index";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/index";
		}
		model.addAttribute("userInfo",userinfo);
		//logger.debug("1========================================="+System.currentTimeMillis()+"===================");
		//培训学员总数
		int conut=iResDoctorProcessBiz.schProcessStudentDistinctQuery("",userFlow,"");
        int cconut = iResDoctorProcessBiz.schProcessStudentDistinctQuery("", userFlow, com.pinde.core.common.GlobalConstant.FLAG_Y);
		//logger.debug("2========================================="+System.currentTimeMillis()+"===================");
		model.addAttribute("count",conut);
		model.addAttribute("ccount",cconut);
		//所属基地是否付费
		String isChargeOrg=jswjwBiz.getJsResCfgCode("jsres_"+userinfo.getOrgFlow()+"_guocheng");
		model.addAttribute("isChargeOrg",isChargeOrg);

        model.addAttribute("trainingTypes", com.pinde.core.common.enums.TrainCategoryEnum.values());
        model.addAttribute("doctorTypes", com.pinde.core.common.enums.ResDocTypeEnum.values());

		HashMap<String,Object> dictMap=new HashMap<>();
        dictMap.put(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId(), jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId()));
        dictMap.put(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId(), jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId()));
        dictMap.put(com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId(), jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId()));
        dictMap.put(com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId(), jswjwBiz.getDictListByDictId(com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId()));
		model.addAttribute("dictMap", dictMap);

        List<Map<String, String>> infos = this.noticeBiz.searchInfoByOrgNotRead("", com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
		if(infos!=null)
		{
			model.addAttribute("hasNotReadInfo",infos.size());
		}else{
			model.addAttribute("hasNotReadInfo",0);
		}
        // 查询师资权限
        jswjwBiz.getTeacherAuthorityInfo(model,userFlow,userinfo.getOrgFlow());
		return "res/jswjw/kzr/index";
	}
	/**
	 * 学员列表
	 * @param userFlow
	 * @param roleId
	 * @param studentName
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/studentList"},method = {RequestMethod.POST})
	public String studentList(String userFlow,String roleId,String seachStr,String deptFlow,String doctorFlow,
							  String studentName, String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,String statusId,
							  String typeId,String yearMonth,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/studentList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "doctorFlow为空");
			return "res/jswjw/kzr/studentList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/studentList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/studentList";
		}
		if("waitSch".equals(typeId)&&StringUtil.isBlank(yearMonth))
		{
			yearMonth=DateUtil.addMonth(DateUtil.getMonth(),1);
		}
		if("waitSch".equals(typeId)&&StringUtil.isNotBlank(yearMonth))
		{
			yearMonth=DateUtil.addMonth(yearMonth,1);
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/studentList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/studentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/studentList";
		}
		if(StringUtil.isBlank(deptFlow))
		{
			deptFlow=userinfo.getDeptFlow();
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
//		schArrangeResultMap.put("deptFlow", deptFlow);
		schArrangeResultMap.put("doctorFlow", doctorFlow);
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("typeId", typeId);
		schArrangeResultMap.put("yearMonth", yearMonth);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
//		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.schDoctorSchProcessHead(schArrangeResultMap);

		Map<String, Object> schRotationDeptMap=new HashMap<String, Object>();
		Map<String,Object> resRecMap=new HashMap<String,Object>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		Map<String,String> schScoreMap=new HashMap<String,String>();
		if(resDoctorSchProcess!=null&&resDoctorSchProcess.size()>0) {
			for (Map<String, String> map : resDoctorSchProcess) {
				Map<String,Object> perMap = jswjwBiz.getRecProgressIn(map.get("userFlow"), map.get("processFlow"),null,null);
				if(perMap!=null) {
					Object o=perMap.get(map.get("processFlow"));
					String per=(null==o)?"0":String.valueOf(perMap.get(map.get("processFlow")));
					map.put("per", per);
				}
				SchArrangeResult schArrangeResult = jswjwTeacherBiz.readSchArrangeResult(map.get("schResultFlow"));
				if (schArrangeResult != null) {
					SchRotationDept schRotationDept = jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(), schArrangeResult.getStandardDeptId());
					if (schRotationDept != null) {
						schRotationDeptMap.put(map.get("processFlow"), schRotationDept);
					}
				}

				List<String> recTypeIds = new ArrayList<>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"), recTypeIds);

				if (expressList != null && expressList.size() > 0) {
					for (ResSchProcessExpress express : expressList) {

                        if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(express.getRecTypeId())) {
							resRecMap.put(map.get("processFlow"), express);
							Map<String, Object> formDataMap = null;
							if (express != null) {
								String recContent = express.getRecContent();
								formDataMap = jswjwTeacherBiz.parseRecContent(recContent);

								boolean f = false;
								f = jswjwBiz.getCkkPower(map.get("userFlow"));
								if (f) {
									ResScore outScore = jswjwTeacherBiz.readScoreByProcessFlow(map.get("processFlow"));
									if (outScore != null) {
										String score = "0";
										if (null != outScore.getTheoryScore()) {
											score = outScore.getTheoryScore().toString();
										}
										schScoreMap.put(map.get("processFlow") + "schScore", score);
									} else {
										schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
									}
								} else {
									schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
								}
							}
						}
						String key = express.getProcessFlow() + express.getRecTypeId();
						resRecMap.put(key, express);
					}
				}
			}
		}
		model.addAttribute("resRecMap", resRecMap);
		model.addAttribute("resRecCountMap", resRecCountMap);
		model.addAttribute("schRotationDeptMap", schRotationDeptMap);
		model.addAttribute("schScoreMap", schScoreMap);
		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/kzr/studentList";
	}
	@RequestMapping(value = {"/userList"},method = {RequestMethod.POST})
	public String userList(String userFlow,String roleId,String seachStr,String deptFlow,String doctorFlow,
							    String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,String statusId,
							  String typeId,String yearMonth,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/userList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/userList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/userList";
		}
		if (StringUtil.isBlank(yearMonth)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "日期为空");
			return "res/jswjw/kzr/userList";
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(yearMonth);
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "yearMonth格式有误");
			return "res/jswjw/kzr/userList";
		}
		if("waitSch".equals(typeId))
		{
			yearMonth=DateUtil.addMonth(yearMonth,1);
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/userList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/userList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/userList";
		}
		if(StringUtil.isBlank(deptFlow))
		{
			deptFlow=userinfo.getDeptFlow();
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
//		schArrangeResultMap.put("deptFlow", deptFlow);
		schArrangeResultMap.put("doctorFlow", doctorFlow);
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("typeId", typeId);
		schArrangeResultMap.put("yearMonth", yearMonth);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.schDoctorSchProcessHeadUserList(schArrangeResultMap);

		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		return "res/jswjw/kzr/userList";
	}
	@RequestMapping(value = {"/temporaryOutAuditSearch"},method = {RequestMethod.POST})
	public String temporaryOutAuditSearch(String userFlow,String roleId,String seachStr,
								String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,
								Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/afterUserList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/afterUserList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/afterUserList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.temporaryOutAuditList(schArrangeResultMap);

		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/kzr/afterUserList";
	}

	@RequestMapping(value = {"/temporaryOutSearch"},method = {RequestMethod.POST})
	public String temporaryOutSearch(String userFlow,String roleId,String seachStr,
										  String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,
										  Integer pageIndex,Integer pageSize, String temporaryAuditStatusId, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/afterUserList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/afterUserList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/afterUserList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		schArrangeResultMap.put("temporaryAuditStatusId", temporaryAuditStatusId);
		schArrangeResultMap.put("roleId", roleId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.temporaryOutList(schArrangeResultMap);

		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/kzr/afterUserList";
	}

	@RequestMapping(value = {"/temporaryOutAudit"}, method = {RequestMethod.GET})
	public String temporaryOutAudit(String processFlow, String auditStatusId,HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "ProcessFlow为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(StringUtil.isBlank(auditStatusId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "审核状态为空");
			return "res/jswjw/kzr/afterUserList";
		}
		int i = jswjwBiz.temporaryOutAudit(processFlow,auditStatusId);
		if(i>0){
			model.addAttribute("resultId", "200");
			model.addAttribute("resultType", "提交成功！");
		}else{
			model.addAttribute("resultId", "200");
			model.addAttribute("resultType", "提交失败！");
		}
		return "res/jswjw/deleteImage";
	}

	@RequestMapping(value = {"/afterUserList"},method = {RequestMethod.POST})
	public String afterUserList(String userFlow,String roleId,String seachStr,
							    String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/afterUserList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/afterUserList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/afterUserList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/afterUserList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.afterUserList(schArrangeResultMap);

		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		return "res/jswjw/kzr/afterUserList";
	}
	@RequestMapping(value = {"/afterAuditList"},method = {RequestMethod.POST})
	public String afterAuditList(String userFlow,String roleId,String doctorFlow,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/afterAuditList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/afterAuditList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员流水号为空");
			return "res/jswjw/kzr/afterAuditList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/afterAuditList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/afterAuditList";
		}

		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("doctorFlow", doctorFlow);
		resDoctorSchProcess=jswjwKzrBiz.afterAuditList(schArrangeResultMap);

		Map<String, Object> schRotationDeptMap=new HashMap<String, Object>();
		Map<String,Object> resRecMap=new HashMap<String,Object>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		Map<String,String> schScoreMap=new HashMap<String,String>();
		if(resDoctorSchProcess!=null&&resDoctorSchProcess.size()>0) {
			for (Map<String, String> map : resDoctorSchProcess) {
				Map<String,Object> perMap = jswjwBiz.getRecProgressIn(map.get("userFlow"), map.get("processFlow"),null,null);
				if(perMap!=null) {
					Object o=perMap.get(map.get("processFlow"));
					String per=(null==o)?"0":String.valueOf(perMap.get(map.get("processFlow")));
					map.put("per", per);
				}
				SchArrangeResult schArrangeResult = jswjwTeacherBiz.readSchArrangeResult(map.get("schResultFlow"));
				if (schArrangeResult != null) {
					SchRotationDept schRotationDept = jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(), schArrangeResult.getStandardDeptId());
					if (schRotationDept != null) {
						schRotationDeptMap.put(map.get("processFlow"), schRotationDept);
					}
				}

				List<String> recTypeIds = new ArrayList<>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"),  recTypeIds);

				if (expressList != null && expressList.size() > 0) {
					for (ResSchProcessExpress express : expressList) {

                        if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(express.getRecTypeId())) {
							resRecMap.put(map.get("processFlow"), express);
							Map<String, Object> formDataMap = null;
							if (express != null) {
								String recContent = express.getRecContent();
								formDataMap = jswjwTeacherBiz.parseRecContent(recContent);

								boolean f = false;
								f = jswjwBiz.getCkkPower(map.get("userFlow"));
								if (f) {
									ResScore outScore = jswjwTeacherBiz.readScoreByProcessFlow(map.get("processFlow"));
									if (outScore != null) {
										String score = "0";
										if (null != outScore.getTheoryScore()) {
											score = outScore.getTheoryScore().toString();
										}
										schScoreMap.put(map.get("processFlow") + "schScore", score);
									} else {
										schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
									}
								} else {
									schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
								}
							}
						}
						String key = express.getProcessFlow() + express.getRecTypeId();
						resRecMap.put(key, express);
					}
				}
			}
		}
		model.addAttribute("resRecMap", resRecMap);
		model.addAttribute("resRecCountMap", resRecCountMap);
		model.addAttribute("schRotationDeptMap", schRotationDeptMap);
		model.addAttribute("schScoreMap", schScoreMap);
		model.addAttribute("list",resDoctorSchProcess);
		return "res/jswjw/kzr/afterAuditList";
	}
	@RequestMapping(value = {"/attendList"},method = {RequestMethod.POST})
	public String attendList(String userFlow,String roleId,String seachStr,String yearMonth,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/attendList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/attendList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/attendList";
		}
		if (StringUtil.isBlank(yearMonth)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "日期为空");
			return "res/jswjw/kzr/attendList";
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(yearMonth);
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "yearMonth格式有误");
			return "res/jswjw/kzr/attendList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/attendList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/attendList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/attendList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("yearMonth", yearMonth);
		schArrangeResultMap.put("nowDate", DateUtil.getCurrDate());
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.attendList(schArrangeResultMap);

		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		return "res/jswjw/kzr/attendList";
	}
	@RequestMapping(value = {"/deptUsers"},method = {RequestMethod.POST})
	public String deptUsers(String userFlow,String deptFlow,String seachStr,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/deptUsers";
		}
//		if(StringUtil.isBlank(deptFlow)){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "科室标识符为空");
//			return "res/jswjw/kzr/deptUsers";
//		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/deptUsers";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/deptUsers";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/deptUsers";
		}
		String cfgTeacher=jswjwBiz.getCfgCode("res_teacher_role_flow");
		List<Map<String,String>> list=null;
		Map<String, String> param=new HashMap<String, String>();
//		param.put("deptFlow", deptFlow);
		param.put("userFlow", userFlow);
		param.put("seachStr", seachStr);
		param.put("roleFlow", cfgTeacher);
		PageHelper.startPage(pageIndex, pageSize);
		list=jswjwKzrBiz.deptUsers(param);
		model.addAttribute("list",list);
		model.addAttribute("dataCount", PageHelper.total);
		int count=jswjwKzrBiz.deptUsersDocCount(param);
		model.addAttribute("count", count);
		int tcount=jswjwKzrBiz.deptUsersCount(param);
		model.addAttribute("tcount", tcount);
		return "res/jswjw/kzr/deptUsers";
	}
	@RequestMapping(value = {"/deptTeacherUsers"},method = {RequestMethod.POST})
	public String deptTeacherUsers(String userFlow,String deptFlow,String teacherFlow,String seachStr,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/deptTeacherUsers";
		}
//		if(StringUtil.isBlank(deptFlow)){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "科室标识符为空");
//			return "res/jswjw/kzr/deptTeacherUsers";
//		}
//		if(StringUtil.isBlank(teacherFlow)){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "带教标识符为空");
//			return "res/jswjw/kzr/deptTeacherUsers";
//		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/deptTeacherUsers";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/deptTeacherUsers";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/deptTeacherUsers";
		}
		String cfgTeacher=jswjwBiz.getCfgCode("res_teacher_role_flow");
		List<Map<String,String>> list=null;
		Map<String, String> param=new HashMap<String, String>();
//		param.put("deptFlow", deptFlow);
		param.put("teacherFlow", teacherFlow);
		param.put("userFlow", userFlow);
		param.put("seachStr", seachStr);
		param.put("roleFlow", cfgTeacher);
		PageHelper.startPage(pageIndex, pageSize);
		list=jswjwKzrBiz.deptTeacherUsers(param);
		model.addAttribute("list",list);
		model.addAttribute("dataCount", PageHelper.total);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		return "res/jswjw/kzr/deptTeacherUsers";
	}
	@RequestMapping(value = {"/deptTeacherDocList"},method = {RequestMethod.POST})
	public String deptTeacherDocList(String userFlow,String doctorFlow,String teacherFlow,String seachStr,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/deptTeacherDocList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "doctorFlow为空");
			return "res/jswjw/kzr/deptTeacherDocList";
		}
//		if(StringUtil.isBlank(teacherFlow)){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "带教标识符为空");
//			return "res/jswjw/kzr/deptTeacherDocList";
//		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/deptTeacherDocList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/deptTeacherDocList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/deptTeacherDocList";
		}
		String cfgTeacher=jswjwBiz.getCfgCode("res_teacher_role_flow");
		List<Map<String,String>> list=null;
		Map<String, String> param=new HashMap<String, String>();
//		param.put("deptFlow", deptFlow);
		param.put("doctorFlow", doctorFlow);
		param.put("teacherFlow", teacherFlow);
		param.put("userFlow", userFlow);
		param.put("seachStr", seachStr);
		param.put("roleFlow", cfgTeacher);
		PageHelper.startPage(pageIndex, pageSize);
		list=jswjwKzrBiz.deptTeacherDocList(param);
		Map<String, Object> schRotationDeptMap=new HashMap<String, Object>();
		Map<String,Object> resRecMap=new HashMap<String,Object>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		Map<String,String> schScoreMap=new HashMap<String,String>();
		if(list!=null&&list.size()>0) {
			for (Map<String, String> map : list) {
				Map<String,Object> perMap = jswjwBiz.getRecProgressIn(map.get("userFlow"), map.get("processFlow"),null,null);
				if(perMap!=null) {
					Object o=perMap.get(map.get("resultFlow"));
					String per=(null==o)?"0":String.valueOf(perMap.get(map.get("resultFlow")));
					map.put("per", per);
				}
				SchArrangeResult schArrangeResult = jswjwTeacherBiz.readSchArrangeResult(map.get("schResultFlow"));
				if (schArrangeResult != null) {
					SchRotationDept schRotationDept = jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(), schArrangeResult.getStandardDeptId());
					if (schRotationDept != null) {
						schRotationDeptMap.put(map.get("processFlow"), schRotationDept);
					}
				}

				List<String> recTypeIds = new ArrayList<>();
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId());
                recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
				List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(map.get("processFlow"), recTypeIds);

				if (expressList != null && expressList.size() > 0) {
					for (ResSchProcessExpress express : expressList) {

                        if (com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(express.getRecTypeId())) {
							resRecMap.put(map.get("processFlow"), express);
							Map<String, Object> formDataMap = null;
							if (express != null) {
								String recContent = express.getRecContent();
								formDataMap = jswjwTeacherBiz.parseRecContent(recContent);

								boolean f = false;
								f = jswjwBiz.getCkkPower(map.get("userFlow"));
								if (f) {
									ResScore outScore = jswjwTeacherBiz.readScoreByProcessFlow(map.get("processFlow"));
									if (outScore != null) {
										String score = "0";
										if (null != outScore.getTheoryScore()) {
											score = outScore.getTheoryScore().toString();
										}
										schScoreMap.put(map.get("processFlow") + "schScore", score);
									} else {
										schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
									}
								} else {
									schScoreMap.put(map.get("processFlow") + "schScore", (String) formDataMap.get("theoreResult"));
								}
							}
						}
						String key = express.getProcessFlow() + express.getRecTypeId();
						resRecMap.put(key, express);
					}
				}
			}
		}
		model.addAttribute("resRecMap", resRecMap);
		model.addAttribute("resRecCountMap", resRecCountMap);
		model.addAttribute("schRotationDeptMap", schRotationDeptMap);
		model.addAttribute("schScoreMap", schScoreMap);
		model.addAttribute("list",list);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/kzr/deptTeacherDocList";
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
	public String deptList(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/deptList";
		}
		SysUser user=jswjwBiz.readSysUser(userFlow);
		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,user.getDeptFlow());
		Map<String, ResInprocessInfo> inprocessInfoMap = new HashMap<String, ResInprocessInfo>();
		for(SysDept result:depts) {
			ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(result.getDeptFlow(), result.getOrgFlow());
			inprocessInfoMap.put(result.getDeptFlow(), info);
		}
		model.addAttribute("depts", depts);
		model.addAttribute("inprocessInfoMap", inprocessInfoMap);
		return "res/jswjw/kzr/deptList";
	}

	@RequestMapping(value={"/inProcessInfo"},method={RequestMethod.POST})
	public String inProcessInfo(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/inProcessInfo";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jswjw/kzr/inProcessInfo";
		}
		SysDept dept=jswjwBiz.readSysDept(deptFlow);
		if(dept==null)
		{
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室信息不存在");
			return "res/jswjw/kzr/inProcessInfo";
		}
		String orgFlow = dept.getOrgFlow();
		ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow, orgFlow);
		model.addAttribute("info", info);
		if (info != null) {
			List<ResInprocessInfoMember> members = resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
			model.addAttribute("members", members);
			List<PubFile> files = pubFileBiz.searchByProductFlow(info.getRecordFlow());
			model.addAttribute("files", files);
			String arrange = info.getTeachingArrangement();
			if (StringUtil.isNotBlank(arrange)) {
				Map<String, Object> arrangeMap = new HashMap<>();
				arrangeMap = paressXml(arrange);
				model.addAttribute("arrangeMap", arrangeMap);

				Map<Integer, String> week = new HashMap<>();
				week.put(1, "周一");
				week.put(2, "周二");
				week.put(3, "周三");
				week.put(4, "周四");
				week.put(5, "周五");
				week.put(6, "周六");
				week.put(7, "周日");
				List<Map<String, String>> days = new ArrayList<>();
				for (int i = 1; i <= 7; i++) {
					String addressV = (String) arrangeMap.get("address" + i);
					String contentV = (String) arrangeMap.get("content" + i);
					if (StringUtil.isNotBlank(addressV) || StringUtil.isNotBlank(contentV)) {
						Map<String, String> m = new HashMap<>();
						m.put("address", addressV);
						m.put("content", contentV);
						m.put("dayName", week.get(i));
						days.add(m);
					}
				}
				model.addAttribute("days", days);
			}
		}else{
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室暂未添加入科教育信息");
			return "res/jswjw/kzr/inProcessInfo";
		}
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl",uploadBaseUrl);
		return "res/jswjw/kzr/inProcessInfo";
	}
	@RequestMapping(value={"/funcList"},method={RequestMethod.POST})
	public String funcList(String userFlow,String doctorFlow,String processFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/funcList";
		}
		if(StringUtil.isEmpty(processFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/kzr/funcList";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/kzr/funcList";
		}

		Map<String,Object> resRecMap=new HashMap<String,Object>();
		List<String> recTypeIds=new ArrayList<>();
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
		List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(processFlow, recTypeIds);
		for(ResSchProcessExpress express:expressList)
		{
			String key =express.getProcessFlow()+express.getRecTypeId();
			resRecMap.put(key, express);
		}
		ResDoctorSchProcess process=iResDoctorProcessBiz.read(processFlow);
		if(process==null){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "轮转信息不存在");
			return "res/jswjw/kzr/funcList";
		}
		SchArrangeResult schArrangeResult=jswjwTeacherBiz.readSchArrangeResult(process.getSchResultFlow());
		if (schArrangeResult!=null) {
			SchRotationDept schRotationDept=jswjwTeacherBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(),schArrangeResult.getStandardDeptId());
			model.addAttribute("schRotationDept",schRotationDept);
			List<Map<String,String>>  dataList =  jswjwBiz.viewImage(doctorFlow,schRotationDept.getRecordFlow());
			if(dataList!=null&&dataList.size()>0)
			{
                model.addAttribute("canViewImage", com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
		}
		model.addAttribute("resRecMap", resRecMap);
		return "res/jswjw/kzr/funcList";
	}

	/**
	 * o数据列表
	 * @param
	 * @param doctorFlow
	 * @param processFlow
	 * @param recType
	 * @return
	 */
	@RequestMapping(value="/resRecList",method=RequestMethod.GET)
	public String resRecList(String userFlow,String doctorFlow,String processFlow,String recType,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/resRecList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/kzr/resRecList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/kzr/resRecList";
		}
		//rec类型转换一下
		String recTypeId=getRecTypeId(recType);
		model.addAttribute("recTypeId",recTypeId);
		if(StringUtil.isBlank(recTypeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/jswjw/kzr/resRecList";
		}
		Map<String, Object> processPerMap = jswjwBiz.getRecProgressIn(doctorFlow, processFlow,null,null);
		model.addAttribute("processPerMap", processPerMap);

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/resRecList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/resRecList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/resRecList";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResRec> recList=jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow,doctorFlow,recTypeId,"");
		List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
		for(ResRec rec : recList){
			String recContent = rec.getRecContent();
			Map<String, Object> formDataMap = parseRecContent(recContent);
			formDataMap.put("dataFlow", rec.getRecFlow());
			formDataMap.put("auditId", rec.getAuditStatusId());
			formDataMap.put("operTime", DateUtil.transDate(rec.getOperTime()));
			dataList.add(formDataMap);
		}
		model.addAttribute("dataList", dataList);
		model.addAttribute("dataCount", PageHelper.total);
        List<ResRec> noAuditList = jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow, doctorFlow, recTypeId, com.pinde.core.common.GlobalConstant.FLAG_Y);
		int count=0;
		if(noAuditList!=null){
			count=noAuditList.size();
		}
		model.addAttribute("notAuditNum",count);
		return "res/jswjw/kzr/resRecList";
	}
	/**
	 * 单条数据详情
	 * @param
	 * @param recType
	 * @return
	 */
	@RequestMapping(value="/resRecDeatil",method=RequestMethod.GET)
	public String resRecDeatil(String userFlow,String recFlow,String deptFlow,String cataFlow,String recType,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/resRecDetail";
		}
		if(StringUtil.isBlank(recFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据标识符为空");
			return "res/jswjw/kzr/resRecDetail";
		}
		if(StringUtil.isBlank(recType)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/jswjw/kzr/resRecDetail";
		}
		if(StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室流水号为空");
			return "res/jswjw/kzr/resRecDetail";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/resRecDetail";
		}
		ResRec rec = jswjwBiz.readResRec(recFlow);
		String recContent = rec.getRecContent();
		_inputList(userFlow, deptFlow, recType,cataFlow, model);
		Map<String, Object> formDataMap = parseRecContent(recContent);
		formDataMap.put("auditId",rec.getAuditStatusId());
		model.addAttribute("resultData", formDataMap);
        model.addAttribute("isOther", com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
		return "res/jswjw/kzr/resRecDetail";
	}
	private String getRecTypeId(String recType) {
		String recTypeId = "";
		switch (recType) {
			case "mr":
				recTypeId = RegistryTypeEnum.CaseRegistry.getId();
				break;
			case "disease":
				recTypeId = RegistryTypeEnum.DiseaseRegistry.getId();
				break;
			case "skill":
				recTypeId = RegistryTypeEnum.SkillRegistry.getId();
				break;
			case "operation":
				recTypeId = RegistryTypeEnum.OperationRegistry.getId();
				break;
			case "activity":
				recTypeId = RegistryTypeEnum.CampaignRegistry.getId();
				break;
			case "summary":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId();
				break;
			case "dops":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId();
				break;
			case "miniCex":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId();
				break;
			case "after":
                recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
				break;
			default:
				break;
		}
		return recTypeId;
	}
	public Map<String,Object> parseRecContent(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();;
				for(Element element : elements){

					if ("imageList".equals(element.getName())) {
						List<Element> images = element.elements();
						if (images != null) {
							List<Map<String, String>> dataList = new ArrayList<Map<String, String>>();
							for (Element ele : images) {
								Map<String, String> map = new HashMap<String, String>();
								map.put("imageFlow", ele.attributeValue("imageFlow"));
								map.put("imageUrl", ele.elementText("imageUrl"));
								map.put("thumbUrl", ele.elementText("thumbUrl"));
								dataList.add(map);
							}
							formDataMap.put(element.getName(), dataList);
						}
					} else {
						List<Node> valueNodes = element.selectNodes("value");
						if (valueNodes != null && !valueNodes.isEmpty()) {
							String value = "";
							for (Node node : valueNodes) {
								if (StringUtil.isNotBlank(value)) {
									value += ",";
								}
								value += node.getText();
							}
							formDataMap.put(element.getName(), value);
						} else {
							if (StringUtil.isNotBlank(element.attributeValue("id"))) {
								formDataMap.put(element.getName() + "_id", element.attributeValue("id"));
							}
							formDataMap.put(element.getName(), element.getText());
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}
	private void _inputList(String userFlow,String deptFlow,String dataType,String cataFlow ,Model model){
		switch (dataType) {
			case "mr":
				break;
			case "disease":
				List<Map<String,Object>> diseaseDiagNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType, cataFlow);
				model.addAttribute("diseaseDiagNameList", diseaseDiagNameList);
				break;
			case "skill":
				List<Map<String,Object>> skillOperNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType,cataFlow);
				model.addAttribute("skillOperNameList", skillOperNameList);
				break;
			case "operation":
				List<Map<String,Object>> operationOperNameList = jswjwBiz.commReqOptionNameList(userFlow,deptFlow,dataType,cataFlow);
				model.addAttribute("operationOperNameList", operationOperNameList);
				break;
			case "activity":
				break;
			case "summary":
				break;
			default:
				break;
		}
	}
	/**
	 * 查看出科考核上传情况
	 * @param userFlow
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/viewImage"},method={RequestMethod.GET})
	public String viewImage(String userFlow,String doctorFlow,String recordFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31501");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/viewImage";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "31502");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/kzr/viewImage";
		}
		if(StringUtil.isEmpty(recordFlow)){
			model.addAttribute("resultId", "31502");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jswjw/kzr/viewImage";
		}
		List<Map<String,String>>  dataList =  jswjwBiz.viewImage(doctorFlow,recordFlow);
		model.addAttribute("dataList", dataList);
		return "res/jswjw/kzr/viewImage";
	}
	private Map<String,Object> paressXml(String content) {
		Map<String,Object> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, Object>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();
				for(Element element : elements){
					formDataMap.put(element.getName(), element.getText());
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
	}

	final static String Jxcf = "1";
	final static String Ynbltl = "2"; final static String Wzbltl = "3";final static String Swbltl = "5";final static String Jxbltl = "11";
	final static String Xsjz = "4";  final static String Rkjy = "6";
	final static String Ckks = "7"; final static String Jnpx = "8"; final static String Yph = "9";
	final static String Jxhz = "10";
	final static String Lcczjnzd = "12"; final static String Lcblsxzd = "13";
	final static String Ssczzd = "14"; final static String Yxzdbgsxzd = "15"; final static String Lcwxyd = "16";
	final static String Ryjy = "17"; final static String Rzyjdjy = "18"; final static String Cjbg = "19";
	final static String Bgdfx = "20";final static String Jxsj = "21";final static String Sjys = "22";
	/**
	 * 出科考核表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/afterEvaluation")
	public String evaluationSun(String userFlow,
								String docFlow,
								String processFlow,
								String recFlow,
								String roleId,
								String deptFlow,
								String recordFlow,
								Model model) throws Exception{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/evaluationSun";
		}
		if(StringUtil.isBlank(docFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/kzr/evaluationSun";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/evaluationSun";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/jswjw/kzr/evaluationSun";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/evaluationSun";
		}
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
		ResDoctor doctor=null;
		SysUser operUser=null;
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
		model.addAttribute("currUser",currUser);
		if(StringUtil.isNotBlank(docFlow)){
			doctor  = jswjwBiz.readResDoctor(docFlow);
			model.addAttribute("doctor", doctor);
			operUser  = jswjwBiz.readSysUser(docFlow);
			model.addAttribute("operUser",operUser);
		}
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		if(rec==null)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "带教还未审核");
			return "res/jswjw/kzr/evaluationSun";
		}
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();


		Map<String,Object> processPerMap=jswjwBiz.getRecProgressIn(docFlow,processFlow,null,null);
		if(processPerMap == null){
			processPerMap = new HashMap<String, Object>();
		}
		boolean f=false;
		f=jswjwBiz.getCkkPower(docFlow);
		//填写百分比限制
		JsresPowerCfg jsresPowerCfg1= jswjwBiz.readPowerCfg("out_filling_check_" + rec.getOrgFlow());
		if(null != jsresPowerCfg1){
			model.addAttribute("checkProcess",jsresPowerCfg1.getCfgValue());
		}else{
			model.addAttribute("checkProcess","0");
		}
		if(f) {
			ResScore outScore = jswjwTeacherBiz.readScoreByProcessFlow(processFlow);
			model.addAttribute("outScore", outScore);
			// 如果有出科成绩 出科试卷flow不为空则查询试卷对应的及格分
			if(null != outScore && StringUtil.isNotBlank(outScore.getPaperFlow())){
				// 查询对应试卷的及格分
				TestPaper testPaper = paperBiz.readTestPaper(outScore.getPaperFlow());
				model.addAttribute("testPaper", testPaper);
				// 是否校验出科理论成绩
				String theoreticalCfg = jswjwBiz.getJsResCfgCode("theoretical_qualified_flag_" + currUser.getOrgFlow());
				model.addAttribute("theoreticalCfg", theoreticalCfg);
			}
		}
		model.addAttribute("f",f);
		ResDoctorSchProcess p=iResDoctorProcessBiz.read(processFlow);
		if(StringUtil.isNotBlank(p.getSchStartDate())&&StringUtil.isNotBlank(p.getSchEndDate()))
		{
			model.addAttribute("attendance",DateUtil.signDaysBetweenTowDate(p.getSchEndDate(),p.getSchStartDate())+1);
		}
		SysDept dept=jswjwBiz.readSysDept(p.getDeptFlow());
		String cksh = jswjwBiz.getJsResCfgCode("jsres_"+dept.getOrgFlow()+"_org_cksh");
		if(StringUtil.isBlank(cksh))
		{
            cksh = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		model.addAttribute("cksh",cksh);
		//获取不同类型并定义接受
		if(processPerMap!=null){
            String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
            String diseaseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
            String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
            String operationRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();

			String caseRegistry=(String)processPerMap.get(processFlow+caseRegistryId);
			String caseRegistryReqNum=(String)processPerMap.get(processFlow+caseRegistryId+"ReqNum");
			String caseRegistryFinished=(String)processPerMap.get(processFlow+caseRegistryId+"Finished");

			String diseaseRegistry=(String)processPerMap.get(processFlow+diseaseRegistryId);
			String diseaseRegistryReqNum=(String)processPerMap.get(processFlow+diseaseRegistryId+"ReqNum");
			String diseaseRegistryFinished=(String)processPerMap.get(processFlow+diseaseRegistryId+"Finished");

			String skillRegistry=(String)processPerMap.get(processFlow+skillRegistryId);
			String skillRegistryReqNum=(String)processPerMap.get(processFlow+skillRegistryId+"ReqNum");
			String skillRegistryFinished=(String)processPerMap.get(processFlow+skillRegistryId+"Finished");

			String skillAndOperationRegistry=(String)processPerMap.get(processFlow+operationRegistryId);
			String skillAndOperationRegistryReqNum=(String)processPerMap.get(processFlow+operationRegistryId+"ReqNum");
			String skillAndOperationRegistryFinished=(String)processPerMap.get(processFlow+operationRegistryId+"Finished");

            String recTypeIdt = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
//			int teachingRounds=0;
//			int difficult=0;
//			int lecture=0;
//			int death=0;
			int jxcf = 0;  int xjk = 0;
			int rkjy = 0; int jnpx=0;
			int yph = 0; int jxhz = 0; int jxbltl = 0; int lcczjnzd = 0;
			int lcblsxzd = 0; int ssczzd = 0; int yxzdbgsxzd = 0; int lcwxyd = 0;
			int ryjy = 0; int rzyjdjy = 0; int cjbg = 0;
			int ynbltl=0;	int wzbltl=0; int swbltl=0;
			int bgdfx=0;	int jxsj=0;	int sjys=0;
			List<String> recTypes=new ArrayList<String>();
			recTypes.add(recTypeIdt);
			List<ResRec> recs= jswjwTeacherBiz.searchRecByProcessWithBLOBs(recTypes,processFlow,operUser.getUserFlow());
			for (ResRec resRec : recs) {
				String content=resRec.getRecContent();
				Document document=DocumentHelper.parseText(content);
				Element root=document.getRootElement();
				Element ec = root.element("activity_way");
				if (ec!=null) {
					String text=ec.attributeValue("id");
					if(Jxcf.equals(text)){
						jxcf++;
					}else if(Ynbltl.equals(text)){
						ynbltl++;
					}else if(Wzbltl.equals(text)){
						wzbltl++;
					}else if(Xsjz.equals(text)){
						xjk++;
					}else if(Swbltl.equals(text)){
						swbltl++;
					}else if(Rkjy.equals(text)){
						rkjy++;
					}else if(Ckks.equals(text)){
//						ckkh++;
					}else if(Jnpx.equals(text)){
						jnpx++;
					}else if(Yph.equals(text)){
						yph++;
					}else if(Jxhz.equals(text)){
						jxhz++;
					}else if(Jxbltl.equals(text)){
						jxbltl++;
					}else if(Lcczjnzd.equals(text)){
						lcczjnzd++;
					}else if(Lcblsxzd.equals(text)){
						lcblsxzd++;
					}else if(Ssczzd.equals(text)){
						ssczzd++;
					}else if(Yxzdbgsxzd.equals(text)){
						yxzdbgsxzd++;
					}else if(Lcwxyd.equals(text)){
						lcwxyd++;
					}else if(Ryjy.equals(text)){
						ryjy++;
					}else if(Rzyjdjy.equals(text)){
						rzyjdjy++;
					}else if(Cjbg.equals(text)){
						cjbg++;
					}else if (Bgdfx.equals(text)){
						bgdfx++;
					}else if(Jxsj.equals(text)){
						jxsj++;
					}else if (Sjys.equals(text)){
						sjys++;
					}
				}
			}
			List<TeachingActivityInfo> infos=new ArrayList<>();
			String orgFlow = currUser.getOrgFlow();
			JsresPowerCfg orgApprove = jsresPowerCfgMapper.selectByPrimaryKey("jsres_"+orgFlow+"_org_ctrl_approve_activity");//教学活动评价配置
			JsresPowerCfg approve = jsresPowerCfgMapper.selectByPrimaryKey("jsres_"+orgFlow+"_org_approve_activity");//教学活动评价配置评审类型
            if (null != orgApprove && null != approve && StringUtil.isNotNullAndEquala(approve.getCfgValue(), orgApprove.getCfgValue(), com.pinde.core.common.GlobalConstant.FLAG_Y)) {
				//开启必评
				infos=jswjwTeacherBiz.searchJoinActivityByProcessFlowNotScore(processFlow);
			}else{
				infos=jswjwTeacherBiz.searchJoinActivityByProcessFlow(processFlow);
			}

			if(infos!=null&&infos.size()>0)
			{
				for(TeachingActivityInfo info:infos)
				{
					String text=info.getActivityTypeId();
					if(Jxcf.equals(text)){
						jxcf++;
					}else if(Ynbltl.equals(text)){
						ynbltl++;
					}else if(Wzbltl.equals(text)){
						wzbltl++;
					}else if(Xsjz.equals(text)){
						xjk++;
					}else if(Swbltl.equals(text)){
						swbltl++;
					}else if(Rkjy.equals(text)){
						rkjy++;
					}else if(Ckks.equals(text)){
//						ckkh++;
					}else if(Jnpx.equals(text)){
						jnpx++;
					}else if(Yph.equals(text)){
						yph++;
					}else if(Jxhz.equals(text)){
						jxhz++;
					}else if(Jxbltl.equals(text)){
						jxbltl++;
					}else if(Lcczjnzd.equals(text)){
						lcczjnzd++;
					}else if(Lcblsxzd.equals(text)){
						lcblsxzd++;
					}else if(Ssczzd.equals(text)){
						ssczzd++;
					}else if(Yxzdbgsxzd.equals(text)){
						yxzdbgsxzd++;
					}else if(Lcwxyd.equals(text)){
						lcwxyd++;
					}else if(Ryjy.equals(text)){
						ryjy++;
					}else if(Rzyjdjy.equals(text)){
						rzyjdjy++;
					}else if(Cjbg.equals(text)){
						cjbg++;
					}else if (Bgdfx.equals(text)){
						bgdfx++;
					}else if(Jxsj.equals(text)){
						jxsj++;
					}else if (Sjys.equals(text)){
						sjys++;
					}
				}
			}
			dataMap.put("userName",operUser.getUserName());
			dataMap.put("sessionNumber",doctor.getSessionNumber());
			dataMap.put("trainingSpeName",doctor.getTrainingSpeName());

			dataMap.put("caseRegistry",StringUtil.defaultIfEmpty(caseRegistry, "0"));
			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

			if (dataMap.get("caseRegistryReqNum")==null||"0".equals(dataMap.get("caseRegistryReqNum"))) {
				dataMap.put("caseRegistryReqNum","0");
				dataMap.put("caseRegistry","100");
			}

			dataMap.put("diseaseRegistry",StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
			dataMap.put("diseaseRegistryReqNum",StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
			dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

			if (dataMap.get("diseaseRegistryReqNum")==null||"0".equals(dataMap.get("diseaseRegistryReqNum"))) {
				dataMap.put("diseaseRegistryReqNum","0");
				dataMap.put("diseaseRegistry","100");
			}

			dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

			if (dataMap.get("skillRegistryReqNum")==null||"0".equals(dataMap.get("skillRegistryReqNum"))) {
				dataMap.put("skillRegistryReqNum","0");
				dataMap.put("skillRegistry","100");
			}

			dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
			dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
			dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

			if (dataMap.get("operationRegistryReqNum")==null||"0".equals(dataMap.get("operationRegistryReqNum"))) {
				dataMap.put("operationRegistryReqNum","0");
				dataMap.put("operationRegistry","100");
			}

			dataMap.put("jxcf",String.valueOf(jxcf));
//			dataMap.put("ynbltl",String.valueOf(ynbltl));
//			dataMap.put("wzbltl",String.valueOf(wzbltl));
			dataMap.put("xjk",String.valueOf(xjk));
//			dataMap.put("swbltl",String.valueOf(swbltl));
			dataMap.put("rkjy",String.valueOf(rkjy));
//			dataMap.put("ckkh",String.valueOf(ckkh));
			dataMap.put("jnpx",String.valueOf(jnpx));
			dataMap.put("yph",String.valueOf(yph));
			dataMap.put("jxhz",String.valueOf(jxhz));
			dataMap.put("jxbltl",String.valueOf(jxbltl));
			dataMap.put("lcczjnzd",String.valueOf(lcczjnzd));
			dataMap.put("lcblsxzd",String.valueOf(lcblsxzd));
			dataMap.put("ssczzd",String.valueOf(ssczzd));
			dataMap.put("yxzdbgsxzd",String.valueOf(yxzdbgsxzd));
			dataMap.put("lcwxyd",String.valueOf(lcwxyd));
			dataMap.put("ryjy",String.valueOf(ryjy));
			dataMap.put("rzyjdjy",String.valueOf(rzyjdjy));
			dataMap.put("cjbg",String.valueOf(cjbg));
			dataMap.put("bgdfx",String.valueOf(bgdfx));
			dataMap.put("jxsj",String.valueOf(jxsj));
			dataMap.put("sjys",String.valueOf(sjys));
			dataMap.put("swbltl",String.valueOf(swbltl));
			dataMap.put("wzbltl",String.valueOf(wzbltl));
			dataMap.put("ynbltl",String.valueOf(ynbltl));

			ResDoctorSchProcess resDoctorSchProcess=iResDoctorProcessBiz.read(processFlow);
			model.addAttribute("resDoctorSchProcess", resDoctorSchProcess);
			model.addAttribute("dataMap", dataMap);
			model.addAttribute("rec", rec);
			model.addAttribute("processFlow",processFlow);
			model.addAttribute("formFileName",recTypeId);
			model.addAttribute("roleFlag", roleId);
		}
		return "res/jswjw/kzr/evaluationSun";
	}

	/**
	 * 保存出科考核表，临床操作技能评估量化表，迷你临床演练评估量化表
	 * @param formFileName
	 * @param recFlow
	 * @param operUserFlow
	 * @param roleFlag
	 * @param processFlow
	 * @param recordFlow
	 * @param userFlow
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveRegistryForm",method={RequestMethod.GET,RequestMethod.POST})
	public String saveRegistryForm(String formFileName,String recFlow,String operUserFlow,String roleFlag,String cksh,String processFlow,String recordFlow,String userFlow,HttpServletRequest req,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		SysUser user=jswjwBiz.readSysUser(userFlow);
		if(user==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(operUserFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleFlag)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/jswjw/success";
		}
		if(!roleFlag.equals("Head")&&!roleFlag.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}

		ResSchProcessExpress resRec=expressBiz.searResRecZhuZhi(formFileName,recFlow,operUserFlow,roleFlag,processFlow,recordFlow,userFlow,cksh,req);

		int i=expressBiz.editResRec(resRec,user);
		if (i==0) {
			model.addAttribute("resultId", "3031001");
			model.addAttribute("resultType", "保存失败");
		}
		return "res/jswjw/success";


	}
	/**
	 * 迷你临床演练评估量化表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/mini_cex")
	public String mini_cex(String userFlow,
						   String doctorFlow,
						   String processFlow,
						   String recFlow,
						   Model model) throws Exception{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/mini_cex";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/kzr/mini_cex";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/jswjw/kzr/mini_cex";
		}
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
		model.addAttribute("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.Mini_CEX.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(doctorFlow);

		model.addAttribute("doctor", doctor);
		model.addAttribute("formFileName", recTypeId);
		model.addAttribute("rec", rec);
		model.addAttribute("roleFlag", "teacher");
		return "res/jswjw/kzr/mini_cex";
	}
	/**
	 * 临床操作技能评估量化表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/DOPS")
	public String DOPS(String userFlow,
					   String doctorFlow,
					   String processFlow,
					   String recFlow,
					   Model model) throws Exception{
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/DOPS";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/jswjw/kzr/DOPS";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "过程标识符为空");
			return "res/jswjw/kzr/DOPS";
		}
		SysUser currUser=jswjwBiz.readSysUser(userFlow);
		model.addAttribute("currUser",currUser);
		ResSchProcessExpress rec=expressBiz.getExpressByRecFlow(recFlow);
        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DOPS.getId();
		if(rec==null)
			rec=expressBiz.getExpressByRecType(processFlow,recTypeId);
		Map<String,Object> formDataMap = null;
		if(rec!=null){
			String recContent = rec.getRecContent();
			formDataMap = jswjwTeacherBiz.parseRecContent(recContent);
			model.addAttribute("formDataMap", formDataMap);
		}
		ResDoctor doctor=jswjwBiz.readResDoctor(doctorFlow);

		model.addAttribute("doctor", doctor);
		model.addAttribute("formFileName", recTypeId);
		model.addAttribute("rec", rec);
		model.addAttribute("roleFlag", "teacher");
		return "res/jswjw/kzr/DOPS";
	}

	/**
	 * 带教评分list
	 * @return
	 */
	@RequestMapping(value="/teacherGradeList",method=RequestMethod.POST)
	public String teacherGradeList(String userFlow,String teaName,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/teacherGradeList";
		}
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/teacherGradeList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/teacherGradeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/teacherGradeList";
		}
		String cfgTeacher=jswjwBiz.getCfgCode("res_teacher_role_flow");
		PageHelper.startPage(pageIndex, pageSize);
		List<SysUser> sysUserList=jswjwKzrBiz.teacherRoleCheckUser(userinfo.getDeptFlow(),cfgTeacher,teaName,userFlow);

		Map<String, List<DeptTeacherGradeInfo>> studentListMap=new HashMap<String, List<DeptTeacherGradeInfo>>();
		Map<String, String> averageMap=new HashMap<String, String>();
		Map<String,String> gradeScoreMap=new HashMap<>();
		for (SysUser user : sysUserList) {
			List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchProssFlowRec(user.getUserFlow());
			Float sum=0f;
			Float average=0f;
			if (gradeInfoList!=null && gradeInfoList.size()>0) {
				for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
					Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
					Float zongFen= Float.parseFloat(gradeMap.get("totalScore").toString());
					gradeScoreMap.put(gradeInfo.getRecFlow(),gradeMap.get("totalScore").toString());
					sum=sum+zongFen;
				}
				average=sum/gradeInfoList.size();
			}
			BigDecimal bd=new BigDecimal(average).setScale(1, RoundingMode.UP);
			averageMap.put(user.getUserFlow(), bd.toString());//平均分
			studentListMap.put(user.getUserFlow(),gradeInfoList);
		}

		final Map<String, String> averageMapTemp = averageMap;
		java.util.Collections.sort(sysUserList,new Comparator<SysUser>() {
			@Override
			public int compare(SysUser o1, SysUser o2) {
				String	o1Key=o1.getUserFlow();
				String	o2Key=o2.getUserFlow();
				String s1=averageMapTemp.get(o1Key);
				String s2=averageMapTemp.get(o2Key);
				if(StringUtil.isBlank(s1))
				{
					s1="0";
				}
				if(StringUtil.isBlank(s2))
				{
					s2="0";
				}
				Float f1=0f;
				Float f2=0f;
				try {
					f1=Float.valueOf(s1);
					f2=Float.valueOf(s2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Float result=f2-f1;
				return result>0?1:result==0?0:-1;
			}
		});
		model.addAttribute("averageMap", averageMap);
		model.addAttribute("gradeScoreMap", gradeScoreMap);
		model.addAttribute("studentListMap", studentListMap);
		model.addAttribute("sysUserList", sysUserList);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/kzr/teacherGradeList";
	}
	/**
	 * 科室评价
	 * @return
	 */
	@RequestMapping(value="/deptGrade",method=RequestMethod.POST)
	public String deptGrade(String userFlow,String deptFlow,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/deptGrade";
		}
		if(StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jswjw/kzr/deptGrade";
		}
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/deptGrade";
		}

		//评分模板
		List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
		String cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		ResAssessCfg assessCfg = jswjwBiz.getGradeTemplate(cfgCodeId);
		model.addAttribute("assessCfg", assessCfg);
		Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if (titleElementList != null && !titleElementList.isEmpty()) {
			for (Element te : titleElementList) {
				ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				List<Element> itemElementList = te.elements("item");
				List<ResAssessCfgItemForm> itemFormList = null;
				int score=0;
				if (itemElementList != null && !itemElementList.isEmpty()) {
					itemFormList = new ArrayList<ResAssessCfgItemForm>();
					for (Element ie : itemElementList) {
						ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
						itemForm.setId(ie.attributeValue("id"));
						itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
						itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
						if(ResAssessScoreTypeEnum.Nine.getId().equals(assessCfg.getAssessTypeId()))
						{
							score +=9;
						}else {
							if (StringUtil.isNotBlank(itemForm.getScore())) {
								score += Integer.valueOf(itemForm.getScore());
							}
						}
						itemFormList.add(itemForm);
					}
				}
				titleForm.setItemList(itemFormList);
				titleForm.setScore(String.valueOf(score));
				titleFormList.add(titleForm);
			}
			model.addAttribute("titleFormList", titleFormList);
		}
		String isOpen = gradeBiz.getCfgByCode("res_permit_open_doc");
		List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchDeptFlowRec(deptFlow,isOpen);
		Float sum=0f;
		Map<String, Float> heJiMap=new HashMap<String, Float>();
		if (gradeInfoList!=null && gradeInfoList.size()>0) {
			for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
				Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
				for (String key: gradeMap.keySet()) {
					Object o=gradeMap.get(key);
					if (o instanceof String) {
						continue;
					}
					Map<String,String> scoreMap=(Map<String,String>)o;
					Float value=heJiMap.get(key);
					if (value==null) {
						value=0f;
					}
					Float deValue= Float.parseFloat(scoreMap.get("score"));
					value=value+deValue;
					heJiMap.put(key, value);
				}
			}
			for (String key: heJiMap.keySet()) {
				Float value=heJiMap.get(key);
				value=value/gradeInfoList.size();
				BigDecimal bd=new BigDecimal(value).setScale(1, BigDecimal.ROUND_HALF_UP);
				heJiMap.put(key, bd.floatValue());
				sum=sum+bd.floatValue();
			}
		}
		model.addAttribute("heJiMap", heJiMap);
		BigDecimal ii=new BigDecimal(sum).setScale(1, BigDecimal.ROUND_HALF_UP);
		sum=ii.floatValue();
		model.addAttribute("average", sum);
		return "res/jswjw/kzr/deptGrade";
	}
	/**
	 * 科室评分学员list
	 * @return
	 */
	@RequestMapping(value="/deptGradeList",method=RequestMethod.POST)
	public String deptGradeList(String userFlow,String deptFlow,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/deptGradeList";
		}
		if(StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jswjw/kzr/deptGradeList";
		}
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/deptGradeList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/deptGradeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/deptGradeList";
		}
		String isOpen = gradeBiz.getCfgByCode("res_permit_open_doc");
		PageHelper.startPage(pageIndex, pageSize);
		List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchDeptFlowRec(deptFlow,isOpen);

		Map<String,String> gradeScoreMap=new HashMap<>();
		if (gradeInfoList!=null && gradeInfoList.size()>0) {
			for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
				Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
				gradeScoreMap.put(gradeInfo.getRecFlow(),gradeMap.get("totalScore").toString());
			}
		}
		final		Map<String,String> map=gradeScoreMap;
		java.util.Collections.sort(gradeInfoList,new Comparator<DeptTeacherGradeInfo>() {
			@Override
			public int compare(DeptTeacherGradeInfo o1, DeptTeacherGradeInfo o2) {
				String	o1Key=o1.getRecFlow();
				String	o2Key=o2.getRecFlow();
				String s1=map.get(o1Key);
				String s2=map.get(o2Key);
				Float f1=0f;
				Float f2=0f;
				try {
					f1=Float.valueOf(s1);
					f2=Float.valueOf(s2);
				} catch (Exception e) {
					e.printStackTrace();
				}
				Float result=f2-f1;
				return result>0?1:result==0?0:-1;
			}
		});
		model.addAttribute("gradeScoreMap", gradeScoreMap);
		model.addAttribute("gradeInfoList", gradeInfoList);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/kzr/deptGradeList";
	}
	/**
	 * 带教评分详情
	 * @return
	 */
	@RequestMapping(value="/gradeDetail",method=RequestMethod.POST)
	public String gradeDetail(String userFlow,String recFlow,String gradeType,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/gradeDetail";
		}
		if (StringUtil.isBlank(recFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价标识符为空");
			return "res/jswjw/kzr/gradeDetail";
		}
		if (StringUtil.isBlank(gradeType)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型为空");
			return "res/jswjw/kzr/gradeDetail";
		}
        if (!com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(gradeType) && !com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(gradeType)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型只能是TeacherGrade或DeptGrade");
			return "res/jswjw/kzr/gradeDetail";
		}
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/gradeDetail";
		}
		//评分内容
		DeptTeacherGradeInfo gradeInfo = gradeBiz.readByFlow(recFlow);
		if (gradeInfo != null && StringUtil.isNotBlank(gradeInfo.getRecContent()))
		{
			Map<String,Object> gradeMap = jswjwBiz.parseGradeInfoXml(gradeInfo.getRecContent());
			model.addAttribute("gradeMap",gradeMap);
		}
		//评分模板
		List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
		String cfgCodeId =null;
        if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(gradeType)) {
			cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
        } else if (com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(gradeType)) {
			cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		}
		ResAssessCfg assessCfg = jswjwBiz.getGradeTemplate(cfgCodeId);
		model.addAttribute("assessCfg", assessCfg);
		Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if (titleElementList != null && !titleElementList.isEmpty()) {
			for (Element te : titleElementList) {
				ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				List<Element> itemElementList = te.elements("item");
				List<ResAssessCfgItemForm> itemFormList = null;
				int score=0;
				if (itemElementList != null && !itemElementList.isEmpty()) {
					itemFormList = new ArrayList<ResAssessCfgItemForm>();
					for (Element ie : itemElementList) {
						ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
						itemForm.setId(ie.attributeValue("id"));
						itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
						itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
						if(ResAssessScoreTypeEnum.Nine.getId().equals(assessCfg.getAssessTypeId()))
						{
							score +=9;
						}else {
							if (StringUtil.isNotBlank(itemForm.getScore())) {
								score += Integer.valueOf(itemForm.getScore());
							}
						}
						itemFormList.add(itemForm);
					}
				}
				titleForm.setItemList(itemFormList);
				titleForm.setScore(String.valueOf(score));
				titleFormList.add(titleForm);
			}
			model.addAttribute("titleFormList", titleFormList);
		}
		return "res/jswjw/kzr/gradeDetail";
	}

	public static List<Map<String, String>> getTimes(String startDate,String endDate,String processFlow ) throws ParseException {
		List<Map<String, String>> list = null;
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			list = new ArrayList<>();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Calendar c = Calendar.getInstance();
			Date date = sdf.parse(startDate);
			c.setTime(date);
			String startMonth= sdf.format(c.getTime());
			date = sdf.parse(endDate);
			c.setTime(date);
			String endMonth= sdf.format(c.getTime());
			while (startMonth.compareTo(endMonth) <= 0) {
				Map<String, String> newTime = new HashMap<>();
				newTime.put("evlMonth", startMonth);
				newTime.put("processFlow", processFlow);
				//获取开始与结束时间
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				Calendar c1 = Calendar.getInstance();
				date = sdf.parse(startMonth);
				c1.setTime(date);
				c1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
				String  monthFirstDay = format.format(c1.getTime());
				if(monthFirstDay.compareTo(startDate)<=0)
				{
					monthFirstDay=startDate;
				}
				c1.add(Calendar.MONTH, 1);
				c1.add(Calendar.DATE,-1);
				String endTime=format.format(c1.getTime());
				if(endTime.compareTo(endDate)>=0)
				{
					endTime=endDate;
				}
				newTime.put("startTime", monthFirstDay);
				newTime.put("endTime", endTime);
				list.add(newTime);

				//开始时间加1个自然月
				date = sdf.parse(startMonth);
				c.setTime(date);
				c.add(Calendar.MONTH, 1);
				startMonth=sdf.format(c.getTime());
			}

			Collections.sort(list, new Comparator<Map<String, String>>() {
				@Override
				public int compare(Map<String, String> f1, Map<String, String> f2) {
					String order1 = f1.get("evlMonth");
					String order2 = f2.get("evlMonth");
					if (order1 == null) {
						return -1;
					} else if (order2 == null) {
						return 1;
					} else if (order1 != null && order2 != null) {
						return order1.compareTo(order2);
					}
					return 0;
				}
			});
		}
		return list;
	}

	@RequestMapping(value = {"/evalStudentList"},method = {RequestMethod.POST})
	public String evalStudentList(String userFlow,String roleId,String seachStr, String doctorTypeId,String trainingTypeId, String trainingSpeId,String sessionNumber,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException, ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/evalStudentList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/evalStudentList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/evalStudentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/evalStudentList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
//		schArrangeResultMap.put("deptFlow", userinfo.getDeptFlow());
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("doctorTypeId", doctorTypeId);
		schArrangeResultMap.put("trainingSpeId", trainingSpeId);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("trainingTypeId", trainingTypeId);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=jswjwKzrBiz.schDoctorSchProcessHead(schArrangeResultMap);

		Map<String,Object> evalMap=new HashMap<String,Object>();
		for (Map<String, String> map : resDoctorSchProcess) {
			SchArrangeResult schArrangeResult=jswjwTeacherBiz.readSchArrangeResult(map.get("schResultFlow"));
			String processFlow=map.get("processFlow");
			List<Map<String, String>> times=new ArrayList<>();
			if (schArrangeResult!=null) {
				String startDate=schArrangeResult.getSchStartDate();
				String endDate=schArrangeResult.getSchEndDate();
				times=getTimes(startDate,endDate,processFlow);
			}
			List<Map<String, Object>> evals=new ArrayList<>();
			if(times!=null&&times.size()>0)
			{
				Map<String, Object> params=new HashMap<String, Object>();
				params.put("teacherUserFlow", userFlow);
				params.put("times", times);
				evals=jswjwTeacherBiz.findProcessEvals(params);
			}
			evalMap.put(processFlow,evals);
		}
		model.addAttribute("evalMap", evalMap);
		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/kzr/evalStudentList";
	}
	/**
	 * 月度考核表列表
	 * @param userFlow
	 * @param processFlow
	 * @param schResultFlow
	 * @param model
     * @return
     * @throws ParseException
     */
	@RequestMapping(value="/monthEvalList")
	public String monthEvalList(String userFlow,String doctorFlow,String processFlow,String schResultFlow,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/monthEvalList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/kzr/monthEvalList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/kzr/monthEvalList";
		}
		if(StringUtil.isBlank(schResultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/jswjw/kzr/monthEvalList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/monthEvalList";
		}
		//拆分学员的培训时间
		SchArrangeResult schArrangeResult=jswjwTeacherBiz.readSchArrangeResult(schResultFlow);
		List<Map<String, String>> times=new ArrayList<>();
		if (schArrangeResult!=null) {
			String startDate=schArrangeResult.getSchStartDate();
			String endDate=schArrangeResult.getSchEndDate();
			times=getTimes(startDate,endDate,processFlow);
		}
		List<Map<String, Object>> evals=null;
		if(times!=null&&times.size()>0)
		{
			Map<String, Object> params=new HashMap<String, Object>();
			params.put("teacherUserFlow", userFlow);
			params.put("times", times);
			evals=jswjwTeacherBiz.findProcessEvals(params);
		}
		model.addAttribute("evals",evals);
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("doctorFlow",doctorFlow);
		return "res/jswjw/kzr/monthEvalList";
	}
	@RequestMapping(value="/showMonthEval",method=RequestMethod.POST)
	public String showMonthEval(String userFlow,String doctorFlow,String processFlow,String recordFlow,String evalMonth,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/showMonthEval";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/jswjw/kzr/showMonthEval";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/jswjw/kzr/showMonthEval";
		}
		if(StringUtil.isBlank(evalMonth)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核时间不能为空");
			return "res/jswjw/kzr/showMonthEval";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/showMonthEval";
		}
		ResDoctorSchProcessEvalWithBLOBs eval=jswjwBiz.getDoctorProcessEvalByFlow(recordFlow);
		if(eval==null)
			eval=jswjwBiz.getDoctorProcessEval(processFlow,evalMonth);
		List<FromTitle> titleList=null;
		String configXml="";
		String configFlow="";
        String IsForm = com.pinde.core.common.GlobalConstant.FLAG_N;
		if(eval!=null)
		{
			IsForm=eval.getIsForm();
			configXml=eval.getFormCfg();
			//已经保存的分数
			Map<String, Object> map=jswjwBiz.parseScoreXml(eval.getEvalResult());
			model.addAttribute("valueMap", map);
		}else{
			//评分配置
			ResDoctorProcessEvalConfig config=jswjwBiz.getProcessEvalConfig(userinfo.getOrgFlow());
			if(config!=null) {
				configXml = config.getFormCfg();
                IsForm = com.pinde.core.common.GlobalConstant.FLAG_Y;
				configFlow=config.getConfigFlow();
			}
		}
		model.addAttribute("isAudit",eval!=null);
		titleList=jswjwBiz.parseFromXmlForList(configXml);
		if(titleList!=null&&titleList.size()>0){
            IsForm = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}else{
            IsForm = com.pinde.core.common.GlobalConstant.FLAG_N;
		}
		model.addAttribute("titleList",titleList);
		model.addAttribute("IsForm",IsForm);
		model.addAttribute("eval",eval);
		model.addAttribute("configFlow",configFlow);
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("doctorFlow",doctorFlow);
		model.addAttribute("recordFlow",recordFlow);
		return "res/jswjw/kzr/showMonthEval";
	}
	@RequestMapping(value="/ownerInfo",method=RequestMethod.POST)
	public String ownerInfo(String userFlow,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/ownerInfo";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/ownerInfo";
		}
		model.addAttribute("userinfo",userinfo);
		List<SysUserRole> userRoles = jswjwBiz.getSysUserRole(userFlow);
		if(userRoles!=null&&userRoles.size()>0) {
			//获取当前配置的老师角色
			String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
			//获取当前配置的科主任角色
			String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
			//获取当前配置的科秘角色
			String seretaryRole = jswjwBiz.getCfgCode("res_secretary_role_flow");

			List<Map<String,String>> roles=new ArrayList<>();
			for (SysUserRole sur : userRoles) {
				Map<String,String> map=new HashMap<>();
				String ur = sur.getRoleFlow();
				if (headRole.equals(ur)) {
					map.put("roleId", "Head");
					map.put("roleName", "科主任");
					roles.add(map);
				}
				if (teacherRole.equals(ur)) {
					map.put("roleId", "Teacher");
					map.put("roleName", "老师");
					roles.add(map);
				}
				if (seretaryRole.equals(ur)) {
					map.put("roleId", "Seretary");
					map.put("roleName", "教秘");
					roles.add(map);
				}
			}
			model.addAttribute("roles",roles);
		}
		return "res/jswjw/kzr/ownerInfo";
	}
	@RequestMapping(value="/saveOwnerInfo",method=RequestMethod.POST)
	public String saveOwnerInfo(SysUser user,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(user.getUserFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isNotBlank(user.getSexId())) {
			if (!"Man".equals(user.getSexId()) && !"Woman".equals(user.getSexId())) {
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "性别ID不正确");
				return "res/jswjw/success";
			}
			if ("Man".equals(user.getSexId())) {
				user.setSexName("男");
			}
			if ("Woman".equals(user.getSexId())) {
				user.setSexName("女");
			}
		}
		int result=jswjwBiz.saveUserInfo(user);
		if(result==0)
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "保存失败");
			return "res/jswjw/success";
		}
		return "res/jswjw/success";
	}

	/**
	 * 培训管理统计
	 *
	 * @return
	 */
	@RequestMapping(value = {"/resManage"}, method = {RequestMethod.POST})
	public String resManage(String userFlow, String yearMonth, HttpServletRequest request,Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/resManage";
		}
		if (StringUtil.isBlank(yearMonth)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "日期为空");
			return "res/jswjw/kzr/resManage";
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(yearMonth);
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "yearMonth格式有误");
			return "res/jswjw/kzr/resManage";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/resManage";
		}

		Map<String, String> param=new HashMap<String, String>();
		param.put("headFlow", userFlow);
		param.put("yearMonth", yearMonth);
		param.put("typeId", "monthCurrent");
		int monthCurrent=jswjwKzrBiz.schDoctorSchProcessHeadCount(param);
		model.addAttribute("monthCurrent", monthCurrent);

		param.put("typeId", "monthSch");
		int monthSch=jswjwKzrBiz.schDoctorSchProcessHeadCount(param);
		model.addAttribute("monthSch", monthSch);

		param.put("typeId", "errorSch");
		int errorSch=jswjwKzrBiz.schDoctorSchProcessHeadCount(param);
		model.addAttribute("errorSch", errorSch);

		yearMonth=DateUtil.addMonth(yearMonth,1);
		param.put("yearMonth",yearMonth);
		param.put("typeId", "waitSch");
		int waitSch=jswjwKzrBiz.schDoctorSchProcessHeadCount(param);
		model.addAttribute("waitSch", waitSch);
		return "res/jswjw/kzr/resManage";
	}
	/**
	 * 科教通知
	 *
	 * @return
	 */
	@RequestMapping(value = {"/getSysNotice"}, method = {RequestMethod.POST})
	public String getSysNotice(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request,Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/sysNotices/getSysNotice";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/sysNotices/getSysNotice";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/sysNotices/getSysNotice";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/sysNotices/getSysNotice";
		}
		String orgFlow="";
		PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, String>> infos = this.noticeBiz.searchInfoByOrgBeforeDate(orgFlow, null, com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID, userFlow, null);
		model.addAttribute("infoList",infos);

		Map<String,Object> isReadMap=new HashMap<>();
		if(infos!=null&&infos.size()>0)
		{
			for(Map<String,String> info:infos)
			{
				ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(info.get("infoFlow"),userFlow);
				isReadMap.put(info.get("infoFlow"),resReadInfo);
			}
		}
		model.addAttribute("isReadMap", isReadMap);
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/no-pic.png";
		model.addAttribute("hostUrl",hostUrl);
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/jswjw/sysNotices/getSysNotice";
	}

	@RequestMapping(value={"/sysNoticeDetail"})
	public String sysNoticeDetail(String userFlow,String infoFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/sysNotices/sysNoticeDetail";
		}
		if(StringUtil.isEmpty(infoFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态标识符为空");
			return "res/jswjw/sysNotices/sysNoticeDetail";
		}
		InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
		if(info==null)
		{
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "住培动态不存在，请刷新列表页面");
			return "res/jswjw/sysNotices/sysNoticeDetail";
		}
		ResReadInfo resReadInfo=inxInfoBiz.getReadInfoByFlow(infoFlow,userFlow);
		if(resReadInfo==null)
		{
			resReadInfo=new ResReadInfo();
			resReadInfo.setInfoFlow(infoFlow);
			resReadInfo.setUserFlow(userFlow);
			inxInfoBiz.saveReadInfo(userFlow,resReadInfo);
		}
		HttpServletRequest httpRequest =(HttpServletRequest) request;
		String httpurl=httpRequest.getRequestURL().toString();
		String servletPath=httpRequest.getServletPath();
		String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/showSysNotice.jsp?recordFlow="+infoFlow;
		String androidimgurl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/jswjw/sysNotices/showSysNotice2.jsp?recordFlow="+infoFlow;
		model.addAttribute("iosDetailUrl",hostUrl);
		model.addAttribute("androidDetailUrl",androidimgurl);
		return "res/jswjw/sysNotices/sysNoticeDetail";
	}

	@RequestMapping(value = {"/activityList"}, method = {RequestMethod.POST})
	public String activityList(String userFlow
			,String isOwner,String yearMonth
			,Integer pageIndex,Integer pageSize,Model model,String roleId) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/activityList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/activityList";
		}
		if(StringUtil.isBlank(isOwner)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isOwner为空");
			return "res/jswjw/kzr/activityList";
		}
        if (!isOwner.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) && !isOwner.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isOwner只能是Y或N");
			return "res/jswjw/kzr/activityList";
		}
		if (StringUtil.isBlank(yearMonth)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "日期为空");
			return "res/jswjw/kzr/activityList";
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM");
			dateFormat2.parse(yearMonth);
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "yearMonth格式有误");
			return "res/jswjw/kzr/activityList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/activityList";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/activityList";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/jswjw/kzr/activityList";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/jswjw/kzr/activityList";
		}
		Map<String,String> param=new HashMap<>();
		param.put("isOwner",isOwner);
		param.put("yearMonth",yearMonth);
		param.put("roleFlag","head");
		param.put("userFlow",userinfo.getUserFlow());
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
		param.put("orgFlow", userinfo.getOrgFlow());
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=activityBiz.findActivityList(param);
		List<SysUserRole> userRoleList = jswjwBiz.getSysUserRole(userFlow); //获取该用户下的所有角色信息
		for (Map<String,Object> obj: list) {
			for (SysUserRole role:userRoleList) {
				if(obj.containsKey("auditRole")) {
					if (obj.get("auditRole").toString().contains(role.getRoleFlow())) {
                        obj.put("audit", com.pinde.core.common.GlobalConstant.FLAG_Y);
					}
				}
			}
		}
		Map<String,Object> resultMap=new HashMap<>();
		if(list!=null) {
			for (Map<String,Object> info:list )
			{
				List<Map<String,Object>>  results=activityBiz.readActivityResults((String) info.get("activityFlow"),"");
				resultMap.put((String) info.get("activityFlow"),results);
			}
		}
		//获取访问路径前缀
		String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("list", list);
		model.addAttribute("resultMap", resultMap);
		List<Map<String, Object>> activityList = activityBiz.findActivityList(param);
		if (activityList != null) {
			model.addAttribute("dataCount", activityList.size());
		} else {
			model.addAttribute("dataCount", PageHelper.total);
		}
		model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));

		Map<String,Object> activityTypeMap=activityBiz.findActivityTypeMap(param);
		model.addAttribute("activityTypeMap", activityTypeMap);
		String key="jsres_"+userinfo.getOrgFlow()+"_org_activity_code_type";
		String cfgv=jswjwBiz.getJsResCfgCode(key);
		model.addAttribute("codeType", cfgv);
		return "res/jswjw/kzr/activityList";
	}

	@RequestMapping(value = {"/qrCode"}, method = {RequestMethod.POST})
	public String qrCode(String userFlow
			,String activityFlow,Model model,String roleId) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/qrCode";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/qrCode";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/kzr/qrCode";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/qrCode";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/qrCode";
		}
		TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
        if (info == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus()))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/kzr/qrCode";
		}
		String url = "func://funcFlow=activitySigin&activityFlow="+activityFlow;
		String url2 = "func://funcFlow=activityOutSigin&activityFlow="+activityFlow;
		model.addAttribute("url", url);
		model.addAttribute("url2", url2);
		return "res/jswjw/kzr/qrCode";
	}
	@RequestMapping(value = {"/delActivity"}, method = {RequestMethod.POST})
	public String delActivity(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}

		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		TeachingActivityInfo activity=activityBiz.readActivityInfo(activityFlow);
		if(activity==null)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/success";
		}

        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.getRecordStatus()))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息已被删除，请刷新列表！");
			return "res/jswjw/success";
		}

		List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow,"");
		if(results!=null&&results.size()>0)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "此活动已有学员扫码，无法删除！");
			return "res/jswjw/success";
		}
        activity.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		model.addAttribute("activity", activity);
		int c=activityBiz.saveActivityInfo(activity,userinfo);
		if(c==0)
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "删除失败！");
			return "res/jswjw/success";
		}else if(c==-1){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "该角色无法发起教学活动，请联系基地管理员！");
			return "res/jswjw/success";
		}
		return "res/jswjw/success";
	}
	@RequestMapping(value = {"/showActivity"}, method = {RequestMethod.POST})
	public String showActivity(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		model.addAttribute("roleFlag", roleId);
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/showActivity";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/showActivity";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/kzr/showActivity";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/showActivity";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/showActivity";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/kzr/showActivity";
		}
		model.addAttribute("activity", activity);
		model.addAttribute("user",userinfo);
		List<Map<String,Object>>  results=activityBiz.readActivityResults(activityFlow,"");
		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
		model.addAttribute("depts",depts);
		Map<String,List<SysUser>> deptUserMap=new HashMap<>();
		if(depts!=null)
		{
			String teacherRoleFlow=jswjwBiz.getCfgCode("res_teacher_role_flow");
			String headRoleFlow = jswjwBiz.getCfgCode("res_head_role_flow");
			for(SysDept d:depts)
			{

				List<SysUser> users=jswjwKzrBiz.readDeptTeachAndHead(d.getDeptFlow(),teacherRoleFlow,headRoleFlow);
				deptUserMap.put(d.getDeptFlow(),users);
			}
		}
		model.addAttribute("deptUserMap",deptUserMap);
			if(activity!=null) {
				if (activity.containsKey("activityStatus") && "pass".equals(activity.get("activityStatus"))) {
					return "res/jswjw/kzr/showPassActivity";
				} else {
					return "res/jswjw/kzr/showActivity";
				}
			}

		int imgCount=0;
		if(activity!=null)
		{
			String content= (String) activity.get("imageUrl");
			List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
			model.addAttribute("imageList", imageList);
			if(imageList!=null)
			{
				imgCount=imageList.size();
			}
		}
		model.addAttribute("imgCount",imgCount+"张");
		return "res/jswjw/kzr/showActivity";
	}
	@RequestMapping(value = {"/activityEval"}, method = {RequestMethod.POST})
	public String activityEval(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/activityEval";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/activityEval";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/kzr/activityEval";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/activityEval";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/activityEval";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/kzr/activityEval";
		}
		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
		model.addAttribute("targets",targets);
		return "res/jswjw/kzr/activityEval";
	}
	@RequestMapping(value = {"/activityEvalList"}, method = {RequestMethod.POST})
	public String activityEvalList(String userFlow,String activityFlow,String searchStr,
								   Integer pageIndex,
								   Integer pageSize,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/activityEvalList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/activityEvalList";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/kzr/activityEvalList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/activityEvalList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/activityEvalList";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/kzr/activityEvalList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "res/jswjw/kzr/activityEvalList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jswjw/kzr/activityEvalList";
		}TeachingActivityInfo info=activityBiz.readActivityInfo(activityFlow);
		model.addAttribute("activity",info);
		//评价人员
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> results=activityBiz.readActivityResults(activityFlow,searchStr);
		model.addAttribute("results",results);
		model.addAttribute("user",userinfo);
		model.addAttribute("dataCount", PageHelper.total);

		//评价的指标
		List<Map<String,Object>> targets=activityTargeBiz.readActivityTargetEvals(activityFlow);
		model.addAttribute("targets",targets);
		//评价人员评分详情
		Map<String,Object> evalDetailMap=new HashMap<>();
		Map<String,Object> evalRemarksMap=new HashMap<>();
		if(results!=null)
		{
			for(Map<String,Object> r:results)
			{
				List<TeachingActivityEval> evals=activityBiz.readActivityResultEvals(String.valueOf(r.get("resultFlow")));
				if(evals!=null)
				{
					for(TeachingActivityEval e:evals)
					{
						evalDetailMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalScore());
						evalRemarksMap.put(e.getResultFlow()+e.getTargetFlow(),e.getEvalRemarks());
					}
				}
			}
		}
		model.addAttribute("evalDetailMap",evalDetailMap);
		model.addAttribute("evalRemarksMap",evalRemarksMap);
		return "res/jswjw/kzr/activityEvalList";
	}
	@RequestMapping(value = {"/activityStuList"}, method = {RequestMethod.POST})
	public String activityStuList(String userFlow,String activityFlow,String searchStr,
								  Integer pageIndex,String typeId,
								  Integer pageSize,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/activityStuList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/activityStuList";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/kzr/activityStuList";
		}
		if(StringUtil.isBlank(typeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "typeId标识符为空");
			return "res/jswjw/kzr/activityStuList";
		}
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(typeId) && !com.pinde.core.common.GlobalConstant.FLAG_N.equals(typeId)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "typeId只能是Y或N");
			return "res/jswjw/kzr/activityStuList";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/activityStuList";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/activityStuList";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/kzr/activityStuList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "3030102");
			model.addAttribute("resultType", "起始页为空");
			return "res/jswjw/kzr/activityStuList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "3030103");
			model.addAttribute("resultType", "页面大小为空");
			return "res/jswjw/kzr/activityStuList";
		}
		//评价人员
		PageHelper.startPage(pageIndex,pageSize);
		List<Map<String,Object>> results=activityBiz.readActivityResultsByType(activityFlow,searchStr,typeId);
		model.addAttribute("results",results);
		model.addAttribute("user",userinfo);
		model.addAttribute("dataCount", PageHelper.total);

		return "res/jswjw/kzr/activityStuList";
	}
	@RequestMapping(value = {"/effectiveResult"}, method = {RequestMethod.POST})
	public String effectiveResult(String userFlow,  String activityFlow,
								  String resultFlow,  String isEffective,
								  Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(resultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "resultFlow为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(isEffective)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isEffective");
			return "res/jswjw/success";
		}
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isEffective) && !com.pinde.core.common.GlobalConstant.FLAG_N.equals(isEffective))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "isEffective只能是Y或N");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/success";
		}
		TeachingActivityResult info=activityBiz.readResult(resultFlow);
		if(info==null) {
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "学员参加活动结果信息不存在，请刷新列表页面！");
			return "res/jswjw/success";
		}
		info.setIsEffective(isEffective);
		int c=activityBiz.saveResult(info,userFlow);
		if(c==0){
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "修改失败");
			return "res/jswjw/success";
		}
		return "res/jswjw/success";
	}
	@RequestMapping(value = {"/addActivity"}, method = {RequestMethod.POST})
	public String addActivity(String userFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		model.addAttribute("roleFlag", roleId);
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/editActivity";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/editActivity";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/editActivity";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/editActivity";
		}
		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrg(userinfo.getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "暂未配置活动指标，请联系基地管理员");
			return "res/jswjw/kzr/editActivity";
		}
		model.addAttribute("user",userinfo);
		List<SysDept> depts=jswjwBiz.getHeadDeptList(userFlow,userinfo.getDeptFlow());
		model.addAttribute("depts",depts);
		Map<String,List<SysUser>> deptUserMap=new HashMap<>();
		if(depts!=null)
		{
			String teacherRoleFlow=jswjwBiz.getCfgCode("res_teacher_role_flow");
			String headRoleFlow = jswjwBiz.getCfgCode("res_head_role_flow");
			for(SysDept d:depts)
			{

				List<SysUser> users=jswjwKzrBiz.readDeptTeachAndHead(d.getDeptFlow(),teacherRoleFlow,headRoleFlow);
				deptUserMap.put(d.getDeptFlow(),users);
			}
		}
		model.addAttribute("deptUserMap",deptUserMap);
		return "res/jswjw/kzr/editActivity";
	}
	@RequestMapping(value = {"/saveActivity"}, method = {RequestMethod.POST})
	public String saveActivity(String userFlow,TeachingActivityInfo activity,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getSpeakerFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "主讲人标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getDeptFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getActivityTypeId())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动形式为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getActivityName())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动名称为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getActivityAddress())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动地点为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getSpeakerPhone())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "联系方式为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getStartTime())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动开始时间为空");
			return "res/jswjw/success";
		}

		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getStartTime());
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "活动开始时间格式有误");
			return "res/jswjw/success";
		}
		if(StringUtil.isBlank(activity.getEndTime())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动结束时间为空");
			return "res/jswjw/success";
		}
		try {
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			dateFormat2.parse(activity.getEndTime());
		} catch (Exception e) {
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "活动结束时间格式有误");
			return "res/jswjw/success";
		}
		if (StringUtil.isBlank(activity.getActivityFlow())){
			String currDateTime = DateUtil.getCurrDateTime2();
			if (currDateTime.compareTo(activity.getStartTime())>0){
				model.addAttribute("resultId", "30401");
				model.addAttribute("resultType", "开始时间不得小于当前时间");
				return "res/jswjw/success";
			}
		}
		if(activity.getStartTime().compareTo(activity.getEndTime())>=0)
		{
			model.addAttribute("resultId", "30401");
			model.addAttribute("resultType", "开始时间不得大于等于结束时间");
			return "res/jswjw/success";
		}
//		if(StringUtil.isBlank(activity.getRealitySpeaker())){
//			model.addAttribute("resultId", "3011101");
//			model.addAttribute("resultType", "实际主讲人为空");
//			return "res/jswjw/success";
//		}
		if(StringUtil.isBlank(activity.getActivityRemark())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动简介为空");
			return "res/jswjw/success";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		model.addAttribute("user",userinfo);
//		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrg(userinfo.getOrgFlow());
		List<TeachingActivityTarget> targets=activityTargeBiz.readByOrgNew(activity.getActivityTypeId(),userinfo.getOrgFlow());
		if(targets==null||targets.size()<=0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "暂未配置活动指标，请联系基地管理员");
			return "res/jswjw/success";
		}
		//校验活动时间是否重复
		int count=checkTime(activity);
		if(count>0 && (!"unpass".equals(activity.getActivityStatus())))
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "该主讲人在时间段内已开展其他活动");
			return "res/jswjw/success";
		}
		if(StringUtil.isNotBlank(activity.getActivityTypeId()))
		{
			activity.setActivityTypeName(ActivityTypeEnum.getNameById(activity.getActivityTypeId()));
		}
		activity.setOrgFlow(userinfo.getOrgFlow());
		activity.setRoleId(roleId);
		int c= activityBiz.addActivityInfo(activity,userinfo,targets, null);
		if(c==0)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "保存失败");
			return "res/jswjw/success";
		}else if(c==-1){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "该角色无法发起教学活动，请联系基地管理员！");
			return "res/jswjw/success";
		}else if(c==-2){
			String keyOfDay = "jsres_" + userinfo.getOrgFlow() + "_org_activity_add_day";
			String keyOfTime = "jsres_" + userinfo.getOrgFlow() + "_org_activity_add_time";
			String day = activityBiz.jsresPowerCfgMap(keyOfDay);
			String time = activityBiz.jsresPowerCfgMap(keyOfTime);
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "请在活动开始前" + day + "天" + time + "点前设置活动!");
			return "res/jswjw/success";
		}
		return "res/jswjw/success";
	}
	private int checkTime(TeachingActivityInfo activity) {
		return activityBiz.checkTime(activity);
	}
	@RequestMapping(value={"/viewActivityImage"},method={RequestMethod.POST})
	public String viewActivityImage(String userFlow,String activityFlow,Model model,String roleId) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/kzr/viewActivityImage";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/jswjw/kzr/viewActivityImage";
		}
		if(StringUtil.isBlank(activityFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/kzr/viewActivityImage";
		}
		if(!roleId.equals("Head")&&!roleId.equals("Seretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/jswjw/kzr/viewActivityImage";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/kzr/viewActivityImage";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/kzr/viewActivityImage";
		}
		if(activity!=null)
		{
			String content= (String) activity.get("imageUrl");
			List<Map<String, String>> imageList=activityBiz.parseImageXml(content);
			model.addAttribute("imageList", imageList);
		}
		if(userFlow.equals(activity.get("speakerFlow")))
		{
            model.addAttribute("canAdd", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		return "res/jswjw/kzr/viewActivityImage";
	}
	@RequestMapping(value={"/addActivityImage"},method={RequestMethod.POST})
	public String addActivityImage(ActivityImageFileForm form, HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(form.getUserFlow())){
			model.addAttribute("resultId", "31601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isEmpty(form.getActivityFlow())){
			model.addAttribute("resultId", "31602");
			model.addAttribute("resultType", "教学活动标识符为空");
			return "res/jswjw/success";
		}
		if(StringUtil.isEmpty(form.getFileName())){
			model.addAttribute("resultId", "31603");
			model.addAttribute("resultType", "文件名为空");
			return "res/jswjw/success";
		}
		if(form.getUploadFile()==null && StringUtil.isBlank(form.getUploadFileData())){
			model.addAttribute("resultId", "31703");
			model.addAttribute("resultType", "上传文件不能为空");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(form.getUserFlow());
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}

		Map<String, Object> activity=activityBiz.readActivity(form.getActivityFlow());
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/success";
		}
		jswjwBiz.addActivityImage(form,userinfo);
		return "res/jswjw/success";
	}

	@RequestMapping(value={"/deleteActivityImage"},method={RequestMethod.GET})
	public String deleteActivityImage(String userFlow,String activityFlow,String imageFlow, HttpServletRequest request,HttpServletResponse response,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "31701");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/jswjw/viewImage";
		}
		if(StringUtil.isEmpty(activityFlow)){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "活动标识符为空");
			return "res/jswjw/viewImage";
		}
		if(StringUtil.isEmpty(imageFlow)){
			model.addAttribute("resultId", "31702");
			model.addAttribute("resultType", "图片标识符为空");
			return "res/jswjw/success";
		}
		//验证用户是否存在
		SysUser userinfo = jswjwBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/jswjw/success";
		}
		Map<String, Object> activity=activityBiz.readActivity(activityFlow);
        if (activity == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(activity.get("recordStatus")))
		{
			model.addAttribute("resultId", "3011107");
			model.addAttribute("resultType", "活动信息不存在");
			return "res/jswjw/success";
		}
		jswjwBiz.deleteActivityImage(userinfo,activityFlow,imageFlow);
		return "res/jswjw/success";
	}
}

