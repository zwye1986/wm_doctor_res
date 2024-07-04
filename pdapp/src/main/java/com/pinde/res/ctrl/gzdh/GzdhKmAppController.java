package com.pinde.res.ctrl.gzdh;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.hbres.IResInprocessInfoBiz;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.jswjw.IJswjwTeacherBiz;
import com.pinde.res.biz.jswjw.IResDoctorProcessBiz;
import com.pinde.res.biz.stdp.INoticeBiz;
import com.pinde.res.biz.stdp.IResGradeBiz;
import com.pinde.res.biz.stdp.IResSchProcessExpressBiz;
import com.pinde.res.biz.gzdh.IGzdhAppBiz;
import com.pinde.res.biz.gzdh.IGzdhKzrBiz;
import com.pinde.res.biz.gzdh.IGzdhStudentBiz;
import com.pinde.res.biz.gzdh.IGzdhTeacherBiz;
import com.pinde.res.enums.hbres.ResAssessScoreTypeEnum;
import com.pinde.res.enums.lcjn.DictTypeEnum;
import com.pinde.res.enums.stdp.RegistryTypeEnum;
import com.pinde.res.enums.stdp.ResAssessTypeEnum;
import com.pinde.res.enums.stdp.ResRecTypeEnum;
import com.pinde.res.model.jswjw.mo.FromTitle;
import com.pinde.res.model.jswjw.mo.ResAssessCfgItemForm;
import com.pinde.res.model.jswjw.mo.ResAssessCfgTitleForm;
import com.pinde.sci.model.mo.*;
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
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/res/gzdh/km")
public class GzdhKmAppController {
	private static Logger logger = LoggerFactory.getLogger(GzdhKmAppController.class);
	
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/gzdh/500";
    }
	
	@Autowired
	private IJswjwBiz jswjwBiz;
	@Autowired
	private IGzdhAppBiz appBiz;
	@Autowired
	private IGzdhStudentBiz studentBiz;
	@Autowired
	private IGzdhTeacherBiz teacherBiz;
	@Autowired
	private IResGradeBiz gradeBiz;
	@Autowired
	private IJswjwTeacherBiz jswjwTeacherBiz;
	@Autowired
	private IGzdhKzrBiz kzrBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IResInprocessInfoBiz resInprocessInfoBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private INoticeBiz noticeBiz;

	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/gzdh/km/test";
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
		return "/res/gzdh/km/test";
	}
	@RequestMapping(value={"/index"})
	public String index(String userFlow,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/index";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/index";
		}
		model.addAttribute("userInfo",userinfo);
		//培训学员总数
		String isOpen = appBiz.getCfgByCode("res_permit_open_doc");
		int conut=kzrBiz.schProcessStudentDistinctQuery(userinfo.getDeptFlow(),userFlow,isOpen);
		model.addAttribute("count",conut);
		List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgNotRead(userinfo.getOrgFlow(), GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
		if(infos!=null)
		{
			model.addAttribute("hasNotReadInfo",infos.size());
		}else{
			model.addAttribute("hasNotReadInfo",0);
		}

		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userFlow",userFlow);
		paramMap.put("isOpen",isOpen);
		List<ResDoctorSchProcess> processList = kzrBiz.searchProcessByDoctorNew(paramMap);
		if(processList!=null) {
			model.addAttribute("auditSize", processList.size());
		}else{
			model.addAttribute("auditSize", 0);
		}
		int kqSize=kzrBiz.findNeedAuditAbsences(userinfo);
		model.addAttribute("kqSize", kqSize);
		List<SysDict> dicts=appBiz.getDictListByDictId(DictTypeEnum.DoctorTrainingSpe.getId());
		model.addAttribute("dicts", dicts);
		List<SchDept> schDepts=kzrBiz.readSchDeptList(userFlow);
		model.addAttribute("schDepts", schDepts);
		return "res/gzdh/km/index";
	}
	/**
	 * 学员列表
	 *
	 * @param userFlow
	 * @param studentName
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @param biaoJi
	 * @return
	 */
	@RequestMapping(value = {"/userList"}, method = {RequestMethod.POST})
	public String userList(String userFlow, String studentName, String speId, String sessionNumber,String isAfter,String isError,String typeId, Integer pageIndex, Integer pageSize, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/userList";
		}

		if (StringUtil.isBlank(typeId)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "类型标识符为空");
			return "res/gzdh/km/userList";
		}
		if (!"deptStudent".equals(typeId)&&!"cycleStudent".equals(typeId)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "类型标识符错误");
			return "res/gzdh/km/userList";
		}

		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/userList";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/userList";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/userList";
		}

		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", userinfo.getDeptFlow());
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("speId", speId);
		schArrangeResultMap.put("userFlow", userFlow);
		schArrangeResultMap.put("typeId", typeId);
		schArrangeResultMap.put("userName", studentName);
		schArrangeResultMap.put("isAfter", isAfter);
		schArrangeResultMap.put("isError", isError);
		String isOpen = appBiz.getCfgByCode("res_permit_open_doc");
		schArrangeResultMap.put("isOpen", isOpen);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=kzrBiz.schDoctorSchProcessHeadUserList(schArrangeResultMap);
		//获取访问路径前缀
		String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("list", resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/km/userList";
	}

	@RequestMapping(value = {"/getBiLiHao"}, method = {RequestMethod.POST})
	public String getBiLiHao(String blh, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(blh)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "病历号为空");
			return "res/gzdh/km/getBiLiHao";
		}

//		javax.xml.namespace.QName SERVICE_NAME = new javax.xml.namespace.QName("http://transfer.pd.com/", "InfoTransferService");
//		InfoTransferService ss = new InfoTransferService(wsdlURL, SERVICE_NAME);
//		InfoTransfer port = ss.getInfoTransferPort();
//			//System.out.println("Invoking getResrecData...");
//			String _getResrecData__return = port.getResrecData(blh, "");
//			//System.out.println("getResrecData.result=" + _getResrecData__return);

			model.addAttribute("resultXml", GzdhResUtil.getBingLiHao(blh));
//			model.addAttribute("_getResrecData__return",_getResrecData__return);

		return "res/gzdh/km/getBiLiHao";
	}

	/**
	 * 请假待审核 列表
	 * @param userFlow
	 * @param studentName
	 * @param speId
	 * @param sessionNumber
	 * @param pageIndex
	 * @param pageSize
     * @param model
     * @return
     */
	@RequestMapping(value = {"/absenceList"}, method = {RequestMethod.POST})
	public String absenceList(String userFlow, String studentName, String speId, String sessionNumber, Integer pageIndex, Integer pageSize, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/absenceList";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/absenceList";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/absenceList";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/absenceList";
		}

		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("orgFlow", userinfo.getOrgFlow());
		schArrangeResultMap.put("userFlow", userFlow);
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("speId", speId);
		schArrangeResultMap.put("userFlow", userFlow);
		schArrangeResultMap.put("userName", studentName);
		schArrangeResultMap.put("isAudit", "Y");//待审核
		String isOpen = appBiz.getCfgByCode("res_permit_open_doc");
		schArrangeResultMap.put("isOpen", isOpen);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=kzrBiz.getAbsenceList(schArrangeResultMap);
		//获取访问路径前缀
		String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("list", resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/km/absenceList";
	}
	@RequestMapping(value = {"/repealAbsenceList"}, method = {RequestMethod.POST})
	public String repealAbsenceList(String userFlow, String studentName, String speId, String sessionNumber, Integer pageIndex, Integer pageSize, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/absenceList";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/absenceList";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/absenceList";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/absenceList";
		}

		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("orgFlow", userinfo.getOrgFlow());
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("speId", speId);
		schArrangeResultMap.put("userFlow", userFlow);
		schArrangeResultMap.put("userName", studentName);
		schArrangeResultMap.put("isAbsence", "Y");//待销假审核
		String isOpen = appBiz.getCfgByCode("res_permit_open_doc");
		schArrangeResultMap.put("isOpen", isOpen);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=kzrBiz.getAbsenceList(schArrangeResultMap);
		//获取访问路径前缀
		String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("list", resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/km/absenceList";
	}

	/**
	 * 已审核列表
	 * @param userFlow
	 * @param studentName
	 * @param speId
	 * @param sessionNumber
	 * @param pageIndex
	 * @param pageSize
     * @param model
     * @return
     */
	@RequestMapping(value = {"/auditAbsenceList"}, method = {RequestMethod.POST})
	public String auditAbsenceList(String userFlow, String studentName, String speId, String sessionNumber, Integer pageIndex, Integer pageSize, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/absenceList";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/absenceList";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/absenceList";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/absenceList";
		}

		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("orgFlow", userinfo.getOrgFlow());
		schArrangeResultMap.put("sessionNumber", sessionNumber);
		schArrangeResultMap.put("speId", speId);
		schArrangeResultMap.put("userFlow", userFlow);
		schArrangeResultMap.put("userName", studentName);
		schArrangeResultMap.put("Audited", "Y");//已审核
		String isOpen = appBiz.getCfgByCode("res_permit_open_doc");
		schArrangeResultMap.put("isOpen", isOpen);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=kzrBiz.getAbsenceList(schArrangeResultMap);
		//获取访问路径前缀
		String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("list", resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/km/absenceList";
	}
	@RequestMapping(value = {"/absenceUserList"}, method = {RequestMethod.POST})
	public String absenceUserList(String userFlow, String yearMonth, String userName, String sessionNumber, Integer pageIndex, Integer pageSize, Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/absenceUserList";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/absenceUserList";
		}

		if (pageIndex == null) {
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/absenceUserList";
		}
		if (pageSize == null) {
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/absenceUserList";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> deptStudents=appBiz.absenceUserList(userName,userFlow,"Secretary");
		Map<String,Object> countDetailMap=new HashMap<>();
		if(deptStudents!=null)
		{
			if(StringUtil.isBlank(yearMonth))
				yearMonth=DateUtil.getMonth();
			String startDate=yearMonth+"-01";
			Calendar c = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(startDate);
			c.setTime(date);
			c.add(Calendar.MONTH, 1);
			c.add(Calendar.DATE, -1);
			String  endDate = sdf.format(c.getTime());
			for(Map<String,Object> m:deptStudents)
			{
				String flow= String.valueOf(m.get("userFlow"));
				Map<String,Object> r=kzrBiz.absenceCountDetail(startDate,endDate,flow);
				int count=kzrBiz.absenceCount(startDate,endDate,flow);
				countDetailMap.put(flow,r);
				countDetailMap.put(flow+"QJ",count);
			}
		}

		String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
		model.addAttribute("uploadBaseUrl", uploadBaseUrl);
		model.addAttribute("list",deptStudents);
		model.addAttribute("countDetailMap",countDetailMap);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/km/absenceUserList";
	}
	@RequestMapping(value = {"/absenceUserDetail"}, method = {RequestMethod.POST})
	public String absenceUserDetail(String userFlow, String yearMonth, String doctorFlow,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/absenceUserDetail";
		}
		if (StringUtil.isBlank(doctorFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzdh/km/absenceUserDetail";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/absenceUserDetail";
		}
		if (StringUtil.isBlank(yearMonth))
			yearMonth = DateUtil.getMonth();
		String startDate = yearMonth + "-01";
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse(startDate);
		c.setTime(date);
		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		String endDate = sdf.format(c.getTime());
		Map<String, Object> r = kzrBiz.absenceCountDetail(startDate, endDate, doctorFlow);
		int count = kzrBiz.absenceCount(startDate, endDate, doctorFlow);
		SysUser docUser=appBiz.readSysUser(doctorFlow);
		ResDoctor doctor=appBiz.readResDoctor(doctorFlow);
		model.addAttribute("docUser", docUser);
		model.addAttribute("doctor", doctor);
		model.addAttribute("countDetailMap", r);
		model.addAttribute("dataCount", count);
		return "res/gzdh/km/absenceUserDetail";
	}

	/**
	 * 请假审核 销假审核 详情
	 * @param userFlow
	 * @param absenceFlow
     * @param model
     * @return
     */
	@RequestMapping(value = {"/auditAbsenceDetail"}, method = {RequestMethod.POST})
	public synchronized String auditAbsenceDetail(String userFlow, String absenceFlow,String typeId, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/auditAbsenceDetail";
		}
		if (StringUtil.isBlank(absenceFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请假标识符为空");
			return "res/gzdh/km/auditAbsenceDetail";
		}

		if (StringUtil.isBlank(typeId)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "类型标识符为空");
			return "res/gzdh/km/auditAbsenceDetail";
		}
		if (!"AuditAbsence".equals(typeId)&&!"RepealAbsence".equals(typeId)&&!"AuditDetail".equals(typeId)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "类型标识符错误");
			return "res/gzdh/km/auditAbsenceDetail";
		}

		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/auditAbsenceDetail";
		}
		SchDoctorAbsence absence=kzrBiz.readAbsence(absenceFlow);
		if(absence==null)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "请假信息不存在，请刷新页面！");
			return "res/gzdh/km/auditAbsenceDetail";
		}
		if("AuditAbsence".equals(typeId)) {
			if (StringUtil.isNotBlank(absence.getTeacherAgreeFlag())) {
				model.addAttribute("resultId", "3010103");
				model.addAttribute("resultType", "请假信息已审核，请刷新页面！");
				return "res/gzdh/km/auditAbsenceDetail";
			}
		}
		if("RepealAbsence".equals(typeId)) {
			if (StringUtil.isNotBlank(absence.getRepealAbsenceDay())) {
				model.addAttribute("resultId", "3010103");
				model.addAttribute("resultType", "销假申请已审核，请刷新页面！");
				return "res/gzdh/km/auditAbsenceDetail";
			}
		}
		String baseDirs = appBiz.getCfgByCode("upload_base_url");
		if(StringUtil.isNotBlank(absence.getMakingFile()))
		{
			PubFile file=appBiz.readFile(absence.getMakingFile());
			model.addAttribute("file", file);
			if(file!=null&&StringUtil.isNotBlank(file.getFilePath()))
			{
				String img=(baseDirs+file.getFilePath()).replaceAll("\\\\","/");
				model.addAttribute("img", img);
			}
		}
		SysUser user = appBiz.readSysUser(absence.getDoctorFlow());

		model.addAttribute("baseDirs", baseDirs);
		model.addAttribute("absence", absence);
		model.addAttribute("docUser", user);
		return "res/gzdh/km/auditAbsenceDetail";
	}

	@RequestMapping(value = {"/checkFile"}, method = {RequestMethod.POST})
	public String checkFile(String userFlow, String fileFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "01000301");
			model.addAttribute("resultType", "用户流水号为空");
			return "res/gzdh/km/checkFile";
		}

		if (StringUtil.isEmpty(fileFlow)) {
			model.addAttribute("resultId", "01000302");
			model.addAttribute("resultType", "文件流水号为空");
			return "res/gzdh/km/checkFile";
		}
		PubFile file=appBiz.readFile(fileFlow);
		if(file==null)
		{
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "请假文件不存在，请刷新页面！");
			return "res/gzdh/km/checkFile";
		}
		String baseDirs = appBiz.getCfgByCode("upload_base_dir");
		if (StringUtil.isBlank(baseDirs)) {
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "文件保存路径未配置，请联系管理员！");
			return "res/gzdh/km/checkFile";
		}
		String filePath = baseDirs + File.separator + file.getFilePath();
		File f = new File(filePath);
		if (!f.exists()) {
			model.addAttribute("resultId", "01000304");
			model.addAttribute("resultType", "请假文件不存在，请刷新页面！");
			return "res/gzdh/km/checkFile";
		}
		return "res/gzdh/km/checkFile";
	}

	@RequestMapping(value = {"/downFile"}, method = {RequestMethod.POST})
	public synchronized void downFile(String userFlow, String fileFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		PubFile file=appBiz.readFile(fileFlow);
		String baseDirs = appBiz.getCfgByCode("upload_base_dir");
		String filePath = baseDirs + File.separator +file.getFilePath();

        /*文件是否存在*/
		File downLoadFile = new File(filePath);
		byte[] data = null;
		long dataLength = 0;
		/*文件是否存在*/
		if (downLoadFile.exists()) {
			InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
			data = new byte[fis.available()];
			dataLength = downLoadFile.length();
			fis.read(data);
			fis.close();
		}
		String fileName=filePath.substring(filePath.lastIndexOf(File.separator)+1);
		fileName = new String(fileName.getBytes(), "ISO-8859-1");
		try {
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
			response.addHeader("Content-Length", "" + dataLength);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			if (data != null) {
				outputStream.write(data);
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException e) {

		}
	}

	@RequestMapping(value={"/saveAuditAbsence"},method ={ RequestMethod.GET,RequestMethod.POST})
	public String saveAuditAbsence(String userFlow, String absenceFlow,String teacherAgreeFlag,String teacherAuditInfo,String repealDate,String typeId, HttpServletRequest request,HttpServletResponse response, Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isBlank(userFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/success";
		}
		if (StringUtil.isBlank(absenceFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "请假标识符为空");
			return "res/gzdh/km/success";
		}
		if (StringUtil.isBlank(typeId)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "类型标识符为空");
			return "res/gzdh/km/success";
		}
		if (!"AuditAbsence".equals(typeId)&&!"RepealAbsence".equals(typeId)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "类型标识符错误");
			return "res/gzdh/km/success";
		}
		if (StringUtil.isBlank(teacherAuditInfo)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "审核意见为空");
			return "res/gzdh/km/success";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/success";
		}
		SchDoctorAbsence absence=kzrBiz.readAbsence(absenceFlow);
		if(absence==null)
		{
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "请假信息不存在，请刷新页面！");
			return "res/gzdh/km/success";
		}

		if("AuditAbsence".equals(typeId)) {
			if (StringUtil.isBlank(teacherAgreeFlag)) {
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "审核状态为空");
				return "res/gzdh/km/success";
			}
			if (!"Y".equals(teacherAgreeFlag)&&!"N".equals(teacherAgreeFlag)) {
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "审核状态标识符错误");
				return "res/gzdh/km/success";
			}
			if (StringUtil.isNotBlank(absence.getTeacherAgreeFlag())) {
				model.addAttribute("resultId", "3010103");
				model.addAttribute("resultType", "请假信息已审核，请刷新页面！");
				return "res/gzdh/km/success";
			}
			absence.setTeacherAgreeFlag(teacherAgreeFlag);
			absence.setTeacherAuditInfo(teacherAuditInfo);
			absence.setTeacherAuditTime(DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:ss"));
			int c = kzrBiz.saveAuditAbsence(absence);
			if (c == 0) {
				model.addAttribute("resultId", "3010103");
				model.addAttribute("resultType", "保存失败！");
				return "res/gzdh/km/success";
			}
		}

		if("RepealAbsence".equals(typeId)) {
			if (StringUtil.isNotBlank(absence.getRepealAbsenceDay())) {
				model.addAttribute("resultId", "3010103");
				model.addAttribute("resultType", "销假申请已审核，请刷新页面！");
				return "res/gzdh/km/success";
			}

			if (StringUtil.isBlank(repealDate)) {
				model.addAttribute("resultId", "3010103");
				model.addAttribute("resultType", "实际销假时间为空！");
				return "res/gzdh/km/success";
			}
			if(absence.getStartDate().compareTo(repealDate)>0||absence.getEndDate().compareTo(repealDate)<0) {
				model.addAttribute("resultId", "3010103");
				model.addAttribute("resultType", "实际销假时间必须在请假时间范围内");
				return "res/gzdh/km/success";
			}
			absence.setRepealAbsenceDay(repealDate);
			absence.setRepealAbsenceInfo(teacherAuditInfo);
			absence.setRepealAbsenceAuditTime(DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm:ss"));
			int c = kzrBiz.saveAuditRepealAbsence(absence);
			if (c == 0) {
				model.addAttribute("resultId", "3010103");
				model.addAttribute("resultType", "保存失败！");
				return "res/gzdh/km/success";
			}
		}
		return "res/gzdh/km/success";
	}
	/**
	 * 学员轮转情况列表
	 * @param userFlow
	 * @param roleId
	 * @param studentName
	 * @param pageIndex
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/studentList"},method = {RequestMethod.POST})
	public String studentList(String userFlow,String roleId,String seachStr,String doctorFlow,String docTypeId,
							  String studentName, String teaName, String speName,String sessionNumber,String statusId,
							  String typeId,String yearMonth,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/studentList";
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID为空");
			return "res/gzdh/km/studentList";
		}
		if(!"waitSch".equals(typeId)&&!"monthCurrent".equals(typeId)&&!"monthSch".equals(typeId))
		{
			if (StringUtil.isBlank(doctorFlow)) {
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "学员标识符为空");
				return "res/gzdh/km/studentList";
			}
		}
		if(!roleId.equals("Secretary")){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户角色ID与角色不符");
			return "res/gzdh/km/studentList";
		}
		if("waitSch".equals(typeId)&&StringUtil.isBlank(yearMonth))
		{
			yearMonth=DateUtil.addMonth(DateUtil.getMonth(),1);
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/studentList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/studentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/studentList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", userinfo.getDeptFlow());
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		schArrangeResultMap.put("typeId", typeId);
		schArrangeResultMap.put("docTypeId", docTypeId);
		schArrangeResultMap.put("yearMonth", yearMonth);
		schArrangeResultMap.put("doctorFlow", doctorFlow);
		String isOpen = appBiz.getCfgByCode("res_permit_open_doc");
		schArrangeResultMap.put("isOpen", isOpen);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=kzrBiz.schDoctorSchProcessHead(schArrangeResultMap);

		Map<String, Object> schRotationDeptMap=new HashMap<String, Object>();
		Map<String,Object> readSchRotationGroupMap=new HashMap<String,Object>();
		Map<String,Object> resRecMap=new HashMap<String,Object>();
		Map<String,Integer> resRecCountMap=new HashMap<String,Integer>();
		for (Map<String, String> map : resDoctorSchProcess) {
			Map<String,Object> perMap = studentBiz.getRegPer(0, map.get("userFlow"), map.get("resultFlow"),null);
			if(perMap!=null) {
				Object o=perMap.get(map.get("resultFlow"));
				String per=(null==o)?"0":String.valueOf(perMap.get(map.get("resultFlow")));
				map.put("per", per);
			}
			SchArrangeResult schArrangeResult=jswjwTeacherBiz.readSchArrangeResult(map.get("resultFlow"));
			if (schArrangeResult!=null) {
				SchRotationDept schRotationDept=appBiz.searchGroupFlowAndStandardDeptIdQuery(schArrangeResult.getStandardGroupFlow(),schArrangeResult.getStandardDeptId());
				if(schRotationDept!=null){
					schRotationDeptMap.put(map.get("processFlow"), schRotationDept);
					if (StringUtil.isNotBlank(schRotationDept.getGroupFlow())) {
						SchRotationGroup readSchRotationGroup=jswjwTeacherBiz.readSchRotationGroup(schRotationDept.getGroupFlow());
						readSchRotationGroupMap.put(map.get("processFlow"), readSchRotationGroup);
					}
				}
				//返回出科异常异常原因
				ResDoctorSchProcess process = kzrBiz.searchByResultFlow(schArrangeResult.getResultFlow());
				if(process!=null) {
					String time = DateUtil.getCurrDate();
					if (!GlobalConstant.FLAG_Y.equals(process.getSchFlag()) && StringUtil.isNotBlank(process.getSchEndDate()) && compare_date(time, process.getSchEndDate()) > 0) {
						ResSchProcessExpress rec = expressBiz.queryResRec(process.getProcessFlow(), ResRecTypeEnum.AfterSummary.getId());
						if (null == rec) {
							map.put("abnormalCause", "学生未提交出科小结");
						} else {
							String teaAudit = rec.getAuditStatusId();
							String headAudit = rec.getHeadAuditStatusId();
							if (StringUtil.isEmpty(teaAudit)) {
								map.put("abnormalCause", "带教老师未审核出科小结");
							} else if (StringUtil.isEmpty(headAudit)) {
								map.put("abnormalCause", "科主任未审核出科小结");
							}
						}
					}
				}
			}

		}
		model.addAttribute("resRecMap", resRecMap);
		model.addAttribute("resRecCountMap", resRecCountMap);
		model.addAttribute("schRotationDeptMap", schRotationDeptMap);
		model.addAttribute("readSchRotationGroupMap", readSchRotationGroupMap);
		model.addAttribute("list",resDoctorSchProcess);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/km/studentList";
	}
	@RequestMapping(value = {"/cycleDetail"},method = {RequestMethod.POST})
	public String cycleDetail(String userFlow,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/cycleDetail";
		}
		String yearMonth=DateUtil.addMonth(DateUtil.getMonth(),1);
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/cycleDetail";
		}
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", userinfo.getDeptFlow());
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("yearMonth", yearMonth);
		String isOpen = appBiz.getCfgByCode("res_permit_open_doc");
		schArrangeResultMap.put("isOpen", isOpen);

		schArrangeResultMap.put("typeId", "monthCurrent");
		int monthCurrent=kzrBiz.schDoctorSchProcessHeadCount(schArrangeResultMap);
		schArrangeResultMap.put("typeId", "monthSch");
		int monthSch=kzrBiz.schDoctorSchProcessHeadCount(schArrangeResultMap);
		schArrangeResultMap.put("typeId", "waitSch");
		int waitSch=kzrBiz.schDoctorSchProcessHeadCount(schArrangeResultMap);
		schArrangeResultMap.put("typeId", "errorSch");
		int errorSch=kzrBiz.schDoctorSchProcessHeadCount(schArrangeResultMap);
		model.addAttribute("monthCurrent",monthCurrent);
		model.addAttribute("monthSch",monthSch);
		model.addAttribute("waitSch",waitSch);
		model.addAttribute("errorSch",errorSch);
		return "res/gzdh/km/cycleDetail";
	}
	private static int compare_date(String DATE1, String DATE2) {

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				//System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				//System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return 0;
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
			return "res/gzdh/km/deptList";
		}
		SysUser user= appBiz.readSysUser(userFlow);
		List<SysDept> depts=appBiz.getHeadDeptList(userFlow,user.getDeptFlow());
		Map<String, ResInprocessInfo> inprocessInfoMap = new HashMap<String, ResInprocessInfo>();
		for(SysDept result:depts) {
			ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(result.getDeptFlow(), result.getOrgFlow());
			inprocessInfoMap.put(result.getDeptFlow(), info);
		}
		model.addAttribute("depts", depts);
		model.addAttribute("inprocessInfoMap", inprocessInfoMap);
		return "res/gzdh/km/deptList";
	}

	@RequestMapping(value={"/inProcessInfo"},method={RequestMethod.POST})
	public String inProcessInfo(String userFlow,String deptFlow,HttpServletRequest request,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/inProcessInfo";
		}
		if(StringUtil.isEmpty(deptFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzdh/km/inProcessInfo";
		}
		SysDept dept=appBiz.readSysDept(deptFlow);
		if(dept==null)
		{
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "科室信息不存在");
			return "res/gzdh/km/inProcessInfo";
		}
		String orgFlow = dept.getOrgFlow();
		ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow, orgFlow);
		model.addAttribute("info", info);
		if (info != null) {
			List<ResInprocessInfoMember> members = resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
			model.addAttribute("members", members);
			List<PubFile> files = fileBiz.searchByProductFlow(info.getRecordFlow());
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
			return "res/gzdh/km/inProcessInfo";
		}
		return "res/gzdh/km/inProcessInfo";
	}
	@RequestMapping(value={"/funcList"},method={RequestMethod.POST})
	public String funcList(String userFlow,String doctorFlow,String processFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3020201");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/funcList";
		}
		if(StringUtil.isEmpty(processFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzdh/km/funcList";
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzdh/km/funcList";
		}
		ResDoctorSchProcess process=iResDoctorProcessBiz.read(processFlow);
		if(process==null){
			model.addAttribute("resultId", "3020202");
			model.addAttribute("resultType", "轮转信息不存在");
			return "res/gzdh/km/funcList";
		}
		ResDoctor resDoctor = appBiz.readResDoctor(process.getUserFlow());
		model.addAttribute("process",process);
		model.addAttribute("resDoctor",resDoctor);
		Map<String,Object> resRecMap=new HashMap<String,Object>();
		List<String> recTypeIds=new ArrayList<>();
		recTypeIds.add(ResRecTypeEnum.DOPS.getId());//4种出科分类
		recTypeIds.add(ResRecTypeEnum.Mini_CEX.getId());
		recTypeIds.add(ResRecTypeEnum.AfterEvaluation.getId());
		recTypeIds.add(ResRecTypeEnum.AfterSummary.getId());
		recTypeIds.add(ResRecTypeEnum.CaseRegistry.getId());//5种登记数据
		recTypeIds.add(ResRecTypeEnum.DiseaseRegistry.getId());
		recTypeIds.add(ResRecTypeEnum.SkillRegistry.getId());
		recTypeIds.add(ResRecTypeEnum.OperationRegistry.getId());
		recTypeIds.add(ResRecTypeEnum.CampaignRegistry.getId());
		List<ResSchProcessExpress> expressList = expressBiz.getDocexpressList(processFlow, recTypeIds);
		for(ResSchProcessExpress express:expressList){
			String key =express.getProcessFlow()+express.getRecTypeId();
			resRecMap.put(key, express);
		}
		model.addAttribute("resRecMap", resRecMap);
		return "res/gzdh/km/funcList";
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
			return "res/gzdh/km/resRecList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/gzdh/km/resRecList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzdh/km/resRecList";
		}
		//rec类型转换一下
		String recTypeId=getRecTypeId(recType);
		model.addAttribute("recTypeId",recTypeId);
		if(StringUtil.isBlank(recTypeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/gzdh/km/resRecList";
		}
		Map<String, Object> deptPerMap = studentBiz.getRegPer(0,doctorFlow);
		model.addAttribute("processPerMap", deptPerMap);

		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/resRecList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/resRecList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/resRecList";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<ResRec> recList=jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow,doctorFlow,recTypeId,"");
		List<Map<String, String>> dataList = new ArrayList<Map<String,String>>();
		for(ResRec rec : recList){
			String recContent = rec.getRecContent();
			Map<String, String> formDataMap = parseRecContent(recContent);
			formDataMap.put("dataFlow", rec.getRecFlow());
			formDataMap.put("auditId", rec.getAuditStatusId());
			formDataMap.put("operTime", DateUtil.transDate(rec.getOperTime()));
			dataList.add(formDataMap);
		}
		model.addAttribute("dataList", dataList);
		model.addAttribute("dataCount", PageHelper.total);
		List<ResRec> noAuditList=jswjwTeacherBiz.searchRecByProcessAndRecType(processFlow,doctorFlow,recTypeId,"Y");
		int count=0;
		if(noAuditList!=null){
			count=noAuditList.size();
		}
		model.addAttribute("notAuditNum",count);
		return "res/gzdh/km/resRecList";
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
			return "res/gzdh/km/resRecDetail";
		}
		if(StringUtil.isBlank(recFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据标识符为空");
			return "res/gzdh/km/resRecDetail";
		}
		if(StringUtil.isBlank(recType)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "数据类型为空");
			return "res/gzdh/km/resRecDetail";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/resRecDetail";
		}
		ResRec rec = appBiz.getRecByRecFlow(recFlow);
		String recContent = rec.getRecContent();
		Map<String, String> formDataMap = parseRecContent(recContent);
		formDataMap.put("auditId",rec.getAuditStatusId());
		model.addAttribute("resultData", formDataMap);
		model.addAttribute("isOther", GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
		return "res/gzdh/km/resRecDetail";
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
				recTypeId = RegistryTypeEnum.CampaignNoItemRegistry.getId();
				break;
			case "summary":
				recTypeId = ResRecTypeEnum.AfterSummary.getId();
				break;
			case "dops":
				recTypeId = ResRecTypeEnum.DOPS.getId();
				break;
			case "miniCex":
				recTypeId = ResRecTypeEnum.Mini_CEX.getId();
				break;
			case "after":
				recTypeId = ResRecTypeEnum.AfterEvaluation.getId();
				break;
			default:
				break;
		}
		return recTypeId;
	}
	public Map<String,String> parseRecContent(String content) {
		Map<String,String> formDataMap = null;
		if(StringUtil.isNotBlank(content)){
			formDataMap = new HashMap<String, String>();
			try {
				Document document = DocumentHelper.parseText(content);
				Element rootElement = document.getRootElement();
				List<Element> elements = rootElement.elements();;
				for(Element element : elements){
					List<Node> valueNodes = element.selectNodes("value");
					if(valueNodes != null && !valueNodes.isEmpty()){
						String value = "";
						for(Node node : valueNodes){
							if(StringUtil.isNotBlank(value)){
								value+=",";
							}
							value += node.getText();
						}
						formDataMap.put(element.getName(), value);
					}else {
						if(StringUtil.isNotBlank(element.attributeValue("id"))){
							formDataMap.put(element.getName()+"_id", element.attributeValue("id"));
						}
						formDataMap.put(element.getName(), element.getText());
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return formDataMap;
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
			return "res/gzdh/km/teacherGradeList";
		}
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/teacherGradeList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/teacherGradeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/teacherGradeList";
		}
		String cfgTeacher=appBiz.getCfgCode("res_teacher_role_flow");
		PageHelper.startPage(pageIndex, pageSize);
		List<SysUser> sysUserList=kzrBiz.teacherRoleCheckUser(userinfo.getDeptFlow(),cfgTeacher,teaName,userFlow);

		Map<String, List<DeptTeacherGradeInfo>> studentListMap=new HashMap<String, List<DeptTeacherGradeInfo>>();
		Map<String, String> averageMap=new HashMap<String, String>();
		Map<String,String> gradeScoreMap=new HashMap<>();
		for (SysUser user : sysUserList) {
			List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchProssFlowRec(user.getUserFlow());
			Float sum=0f;
			Float average=0f;
			if (gradeInfoList!=null && gradeInfoList.size()>0) {
				for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
					Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(gradeInfo.getRecContent());
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
		Collections.sort(sysUserList,new Comparator<SysUser>() {
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
		return "res/gzdh/km/teacherGradeList";
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
			return "res/gzdh/km/deptGrade";
		}
		if(StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzdh/km/deptGrade";
		}
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/deptGrade";
		}

		//评分模板
		List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
		String cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		ResAssessCfg assessCfg = gradeBiz.getGradeTemplate(cfgCodeId);
		model.addAttribute("assessCfg", assessCfg);
		Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if (titleElementList != null && !titleElementList.isEmpty()) {
			for (Element te : titleElementList) {
				ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				titleForm.setEvalTypeId(te.attributeValue("evalTypeId")== null ? "" : te.attributeValue("evalTypeId"));
				titleForm.setEvalTypeName(te.attributeValue("evalTypeName")== null ? "" : te.attributeValue("evalTypeName"));
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
				Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(gradeInfo.getRecContent());
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
		return "res/gzdh/km/deptGrade";
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
			return "res/gzdh/km/deptGradeList";
		}
		if(StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "科室标识符为空");
			return "res/gzdh/km/deptGradeList";
		}
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/deptGradeList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/deptGradeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/deptGradeList";
		}
		String isOpen = gradeBiz.getCfgByCode("res_permit_open_doc");
		PageHelper.startPage(pageIndex, pageSize);
		List<DeptTeacherGradeInfo> gradeInfoList=gradeBiz.searchDeptFlowRec(deptFlow,isOpen);

		Map<String,String> gradeScoreMap=new HashMap<>();
		if (gradeInfoList!=null && gradeInfoList.size()>0) {
			for (DeptTeacherGradeInfo gradeInfo : gradeInfoList) {
				Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(gradeInfo.getRecContent());
				gradeScoreMap.put(gradeInfo.getRecFlow(),gradeMap.get("totalScore").toString());
			}
		}
		final		Map<String,String> map=gradeScoreMap;
		Collections.sort(gradeInfoList,new Comparator<DeptTeacherGradeInfo>() {
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
		return "res/gzdh/km/deptGradeList";
	}
	/**
	 * 学员评分list
	 * @return
	 */
	@RequestMapping(value="/studentGradeList",method=RequestMethod.POST)
	public String studentGradeList(String userFlow,String stuName,String sessionNumber,String schDeptFlow,String speId,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/studentGradeList";
		}
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/studentGradeList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/studentGradeList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/studentGradeList";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=kzrBiz.getCycleStudents(userFlow,stuName,sessionNumber,speId,schDeptFlow);
		model.addAttribute("list", list);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/km/studentGradeList";
	}
	/**
	 * 学员评分list
	 * @return
	 */
	@RequestMapping(value="/docSchDeptList",method=RequestMethod.POST)
	public String docSchDeptList(String userFlow,String doctorFlow,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/docSchDeptList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzdh/km/docSchDeptList";
		}
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/docSchDeptList";
		}

		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/docSchDeptList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/docSchDeptList";
		}
		PageHelper.startPage(pageIndex, pageSize);
		List<Map<String,Object>> list=kzrBiz.docSchDeptList(userFlow,doctorFlow);

		for (Map<String, Object> map : list) {
			Map<String, Object> perMap = studentBiz.getRegPer(0, String.valueOf(map.get("userFlow")), String.valueOf(map.get("resultFlow")), null);
			if (perMap != null) {
				Object o = perMap.get(map.get("resultFlow"));
				String per = (null == o) ? "0" : String.valueOf(perMap.get(map.get("resultFlow")));
				map.put("per", per);
			}
		}
		model.addAttribute("list", list);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/km/docSchDeptList";
	}
	@RequestMapping(value="/docEvalFuncList",method=RequestMethod.POST)
	public String docEvalFuncList(String userFlow,String processFlow,String resultFlow,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/docEvalFuncList";
		}
		if(StringUtil.isBlank(resultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "计划标识符为空");
			return "res/gzdh/km/docEvalFuncList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzdh/km/docEvalFuncList";
		}
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/docEvalFuncList";
		}

		List<Map<String,Object>> list=new ArrayList<>();
		ResDoctorSchProcess process=appBiz.readSchProcess(processFlow);
		ResDoctor doctor=appBiz.readResDoctor(process.getUserFlow());
		Map<String,Object> data=new HashMap<>();
		if(doctor!=null&&"2017".compareTo(doctor.getSessionNumber())<=0) {
			//出科考核表

			ResSchProcessExpress rec = expressBiz.getExpressByRecType(processFlow, ResRecTypeEnum.AfterEvaluation.getId());
			if (rec != null) {
				data.put("recFlow", rec.getRecFlow());
				data.put("typeId", ResRecTypeEnum.AfterEvaluation.getId());
				data.put("resultFlow", resultFlow);
				data.put("openId", "Y");
				data.put("statusId", "Audit");
				data.put("statusName", "带教已审核");
				Map<String, String> formDataMap = appBiz.parseRecContent(rec.getRecContent());
				if (formDataMap != null) {
					data.put("totalScore", formDataMap.get("totalScore"));
				} else {
					data.put("totalScore", "暂无");
				}
			} else {
				data.put("recFlow", "");
				data.put("typeId", ResRecTypeEnum.AfterEvaluation.getId());
				data.put("resultFlow", resultFlow);
				data.put("statusId", "NotAudit");
				data.put("openId", "N");
				data.put("statusName", "带教未审核");
				data.put("totalScore", "暂无");
			}
			data.put("evalName", "带教老师");
			list.add(data);
		}else{
			//带教老师
			DeptTeacherGradeInfo info=gradeBiz.getRecByRecType(processFlow,ResAssessTypeEnum.TeacherDoctorAssess.getId());
			data=new HashMap<>();
			if(info!=null)
			{
				data.put("recFlow",info.getRecFlow());
				data.put("typeId",ResAssessTypeEnum.TeacherDoctorAssess.getId());
				data.put("resultFlow",resultFlow);
				data.put("openId", "Y");
				data.put("statusId", "Audit");
				data.put("statusName", "带教已审核");
				Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(info.getRecContent());
				if(gradeMap!=null)
				{
					data.put("totalScore", gradeMap.get("totalScore"));
				}else {
					data.put("totalScore", "暂无");
				}
			}else{
				data.put("recFlow","");
				data.put("typeId",ResAssessTypeEnum.TeacherDoctorAssess.getId());
				data.put("resultFlow", resultFlow);
				data.put("statusId", "NotAudit");
				data.put("openId", "N");
				data.put("statusName", "带教未审核");
				data.put("totalScore", "暂无");
			}
			data.put("evalName", "带教老师");
			list.add(data);
		}
		//规培秘书
		DeptTeacherGradeInfo info=gradeBiz.getRecByRecType(processFlow,ResAssessTypeEnum.SecretaryDoctorAssess.getId());
		data=new HashMap<>();
		if(info!=null)
		{
			data.put("recFlow",info.getRecFlow());
			data.put("typeId",ResAssessTypeEnum.SecretaryDoctorAssess.getId());
			data.put("resultFlow",resultFlow);
			data.put("openId","Y");
			data.put("statusId","Audit");
			data.put("statusName","规培秘书已审核");
			Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(info.getRecContent());
			if(gradeMap!=null)
			{
				data.put("totalScore", gradeMap.get("totalScore"));
			}else {
				data.put("totalScore", "暂无");
			}
		}else{
			data.put("recFlow","");
			data.put("typeId",ResAssessTypeEnum.SecretaryDoctorAssess.getId());
			data.put("resultFlow",resultFlow);
			data.put("openId","Y");
			data.put("statusId","NotAudit");
			data.put("statusName","规培秘书未审核");
			data.put("totalScore","暂无");
		}
		data.put("evalName", "规培秘书");
		list.add(data);
		//护士
		List<DeptTeacherGradeInfo> infos=gradeBiz.getRecListByRecType(processFlow,ResAssessTypeEnum.NurseDoctorAssess.getId());
		data=new HashMap<>();
		if(infos!=null&&infos.size()>0)
		{
			data.put("recFlow","");
			data.put("typeId",ResAssessTypeEnum.NurseDoctorAssess.getId());
			data.put("resultFlow",resultFlow);
			data.put("openId","Y");
			data.put("statusId","Audit");
			data.put("statusName","护士已审核");
			double totalScore=0;
			for(DeptTeacherGradeInfo gradeInfo:infos)
			{
				Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(gradeInfo.getRecContent());
				if(gradeMap!=null&&StringUtil.isNotBlank((String) gradeMap.get("totalScore")))
				{
					totalScore+=Double.valueOf((String) gradeMap.get("totalScore"));
				}
			}
			totalScore=totalScore/infos.size();
			if(totalScore!=0)
			{
				data.put("totalScore", new BigDecimal(totalScore).setScale(1,BigDecimal.ROUND_HALF_UP));
			}else {
				data.put("totalScore", "暂无");
			}
		}else{
			data.put("recFlow","");
			data.put("typeId",ResAssessTypeEnum.NurseDoctorAssess.getId());
			data.put("resultFlow",resultFlow);
			data.put("openId","Y");
			data.put("statusId","NotAudit");
			data.put("statusName","护士未审核");
			data.put("totalScore","暂无");
		}
		data.put("evalName", "护士");
		list.add(data);
		//患者
		infos=gradeBiz.getRecListByRecType(processFlow,ResAssessTypeEnum.PatientDoctorAssess.getId());
		data=new HashMap<>();
		if(infos!=null&&infos.size()>0)
		{
			data.put("recFlow","");
			data.put("typeId",ResAssessTypeEnum.PatientDoctorAssess.getId());
			data.put("resultFlow",resultFlow);
			data.put("openId","Y");
			data.put("statusId","Audit");
			data.put("statusName","护士已审核");
			double totalScore=0;
			for(DeptTeacherGradeInfo gradeInfo:infos)
			{
				Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(gradeInfo.getRecContent());
				if(gradeMap!=null&&StringUtil.isNotBlank((String) gradeMap.get("totalScore")))
				{
					totalScore+=Double.valueOf((String) gradeMap.get("totalScore"));
				}
			}
			totalScore=totalScore/infos.size();
			if(totalScore!=0)
			{
				data.put("totalScore", new BigDecimal(totalScore).setScale(1,BigDecimal.ROUND_HALF_UP));
			}else {
				data.put("totalScore", "暂无");
			}
		}else{
			data.put("recFlow","");
			data.put("typeId",ResAssessTypeEnum.PatientDoctorAssess.getId());
			data.put("resultFlow",resultFlow);
			data.put("openId","Y");
			data.put("statusId","NotAudit");
			data.put("statusName","护士未审核");
			data.put("totalScore","暂无");
		}
		data.put("evalName", "患者");
		list.add(data);
		model.addAttribute("list",list);
		return "res/gzdh/km/docEvalFuncList";
	}

	//带教老师评分详情实际为出科考核表
	@RequestMapping(value="/teacherDetail",method={RequestMethod.POST})
	public String teacherDetail(String userFlow, String resultFlow, String typeId, String recFlow, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "3010601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/showRegistryForm";
		}
		if (StringUtil.isEmpty(typeId)) {
			model.addAttribute("resultId", "3010604");
			model.addAttribute("resultType", "数据类型标识为空");
			return "res/gzdh/km/showRegistryForm";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "3010606");
			model.addAttribute("resultType", "计划标识符为空");
			return "res/gzdh/km/showRegistryForm";
		}
		//当前轮转计划信息
		SchArrangeResult result = studentBiz.searcheDocResult(null, resultFlow);
		model.addAttribute("result", result);

		//获取当前这条登记信息
		ResSchProcessExpress rec = null;
		if (StringUtil.isNotBlank(recFlow)) {
			rec = expressBiz.getExpressByRecFlow(recFlow);
		} else {
			String processFlow = "";
			ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
			if (process != null) {
				//登记表单的科室
				processFlow = process.getProcessFlow();
			}
			rec = expressBiz.getExpressByRecType(processFlow, typeId);
		}
		String version="2.0";
		if (rec != null) {
			String content = rec.getRecContent();
			//解析登记信息的xml
			Object formDataMap = null;
			formDataMap = appBiz.parseRecContent(content);
			model.addAttribute("formDataMap", formDataMap);
			//如果读取到数据就使用数据本身的funcFlow
			typeId = rec.getRecTypeId();
			version = rec.getRecVersion();
		}else{
			model.addAttribute("resultId", "3010606");
			model.addAttribute("resultType", "带教暂未打分");
			return "res/gzdh/km/showRegistryForm";
		}
		if (typeId.equals(com.pinde.res.enums.hbres.ResRecTypeEnum.AfterEvaluation.getId())) {
			ResDoctorSchProcess process = appBiz.readSchProcessByResultFlow(resultFlow);
			if (process != null) {
				String res_after_test_switch = appBiz.getCfgByCode("res_after_test_switch");
				String res_after_url_cfg = appBiz.getCfgByCode("res_after_url_cfg");
				//学员开通出科考试权限
				Map<String, String> paramMap = new HashMap();
				String cfgCode = "res_doctor_ckks_" + process.getUserFlow();
				paramMap.put("cfgCode", cfgCode);
				boolean testTypeFlag = false;
				if (GlobalConstant.FLAG_Y.equals(res_after_test_switch) && StringUtil.isNotBlank(res_after_url_cfg)) {
					testTypeFlag = true;
				}
				model.addAttribute("testTypeFlag", testTypeFlag);
				ResScore score = appBiz.getScoreByProcess(process.getProcessFlow());
				model.addAttribute("outScore", score);
			}
		}
		if(ResRecTypeEnum.AfterEvaluation.getId().equals(typeId))
		{
			typeId=typeId+"_"+version;
		}
		model.addAttribute("funcFlow", typeId);
		return "res/gzdh/km/showRegistryForm";
	}
	@RequestMapping(value="/evalInfoList",method=RequestMethod.POST)
	public String evalInfoList(String userFlow,String processFlow,String typeId,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/evalInfoList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzdh/km/evalInfoList";
		}
		if(StringUtil.isBlank(typeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型为空");
			return "res/gzdh/km/evalInfoList";
		}
		if(!ResAssessTypeEnum.NurseDoctorAssess.getId().equals(typeId)&&!ResAssessTypeEnum.PatientDoctorAssess.getId().equals(typeId))
		{

			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型错误，只能是NurseDoctorAssess或PatientDoctorAssess");
			return "res/gzdh/km/evalInfoList";
		}
		if(pageIndex==null)
			pageIndex=1;
		if(pageSize==null)
			pageSize=10;
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/evalInfoList";
		}

		PageHelper.startPage(pageIndex, pageSize);
		List<DeptTeacherGradeInfo> infos=gradeBiz.getRecListByRecType(processFlow,typeId);
		Map<String,Object> scoreMap=new HashMap<>();
		for(DeptTeacherGradeInfo gradeInfo:infos)
		{
			Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(gradeInfo.getRecContent());
			if(gradeMap!=null&&StringUtil.isNotBlank((String) gradeMap.get("totalScore")))
			{
				scoreMap.put(gradeInfo.getRecFlow()+"Score",gradeMap.get("totalScore"));
				scoreMap.put(gradeInfo.getRecFlow()+"EvalName",gradeMap.get("evalName"));
			}
		}
		model.addAttribute("scoreMap",scoreMap);
		model.addAttribute("list",infos);
		model.addAttribute("dataCount", PageHelper.total);
		return "res/gzdh/km/evalInfoList";
	}

	@RequestMapping(value="/evalInfoDetail",method=RequestMethod.POST)
	public String evalInfoDetail(String userFlow,String processFlow,String typeId,String recFlow,Model model) throws DocumentException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/evalInfoDetail";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzdh/km/evalInfoDetail";
		}
		if(StringUtil.isBlank(typeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型为空");
			return "res/gzdh/km/evalInfoDetail";
		}
		if(!ResAssessTypeEnum.TeacherDoctorAssess.getId().equals(typeId)&&!ResAssessTypeEnum.NurseDoctorAssess.getId().equals(typeId)&&!ResAssessTypeEnum.PatientDoctorAssess.getId().equals(typeId)&&!ResAssessTypeEnum.SecretaryDoctorAssess.getId().equals(typeId))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型错误，只能是TeacherDoctorAssess或NurseDoctorAssess或PatientDoctorAssess或SecretaryDoctorAssess");
			return "res/gzdh/km/evalInfoDetail";
		}
		DeptTeacherGradeInfo info=gradeBiz.readByFlow(recFlow);
		if(info==null&&ResAssessTypeEnum.SecretaryDoctorAssess.getId().equals(typeId))
		{
			info=gradeBiz.getRecByRecType(processFlow,typeId);
		}
		boolean isAudit=false;
		if(info!=null)
			isAudit=true;
		model.addAttribute("isAudit",isAudit);
		model.addAttribute("typeId",typeId);
		//读取评分配置
		ResAssessCfg assessCfg = gradeBiz.getGradeTemplate(typeId);
		model.addAttribute("assessCfg",assessCfg);
		if(assessCfg!=null){
			String content = assessCfg.getCfgBigValue();

			List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
			Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if (titleElementList != null && !titleElementList.isEmpty()) {
				for (Element te : titleElementList) {
					ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					titleForm.setEvalTypeId(te.attributeValue("evalTypeId")== null ? "" : te.attributeValue("evalTypeId"));
					titleForm.setEvalTypeName(te.attributeValue("evalTypeName")== null ? "" : te.attributeValue("evalTypeName"));
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
			//解析评分xml
			List<Map<String,Object>> assessMaps = gradeBiz.parseAssessCfg(content);
			//System.out.println("====="+JSON.toJSONString(assessMaps));
			model.addAttribute("assessMaps",assessMaps);
		}
		if(info!=null&&StringUtil.isNotBlank(info.getRecContent()))
		{
			//解析登记信息的xml
			Map<String,Object> formDataMap = gradeBiz.parseGradeInfoXml(info.getRecContent());
			model.addAttribute("gradeMap",formDataMap);
		}

		return "res/gzdh/km/evalInfoDetail";
	}

	@RequestMapping(value={"/saveData"},method={RequestMethod.POST})
	public String saveData(String userFlow,
						   String roleId,
						   String processFlow,
						   String resultFlow,
						   String typeId,
						   String recFlow,
						   HttpServletRequest request,
						   Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");

		if(StringUtil.isEmpty(userFlow)){
			model.addAttribute("resultId", "3010901");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/saveData";
		}

		if(StringUtil.isEmpty(roleId)){
			model.addAttribute("resultId", "3010902");
			model.addAttribute("resultType", "用户角色为空");
			return "res/gzdh/saveData";
		}

		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzdh/saveData";
		}
		if(StringUtil.isBlank(resultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转记录标识符为空");
			return "res/gzdh/saveData";
		}
		if(StringUtil.isBlank(typeId)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型为空");
			return "res/gzdh/saveData";
		}
		if(!ResAssessTypeEnum.NurseDoctorAssess.getId().equals(typeId)&&!ResAssessTypeEnum.PatientDoctorAssess.getId().equals(typeId)&&!ResAssessTypeEnum.SecretaryDoctorAssess.getId().equals(typeId))
		{
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型错误，只能是NurseDoctorAssess或PatientDoctorAssess或SecretaryDoctorAssess");
			return "res/gzdh/saveData";
		}
		DeptTeacherGradeInfo info=gradeBiz.readByFlow(recFlow);
		if(info==null&&ResAssessTypeEnum.SecretaryDoctorAssess.getId().equals(typeId))
		{
			info=gradeBiz.getRecByRecType(processFlow,typeId);
		}
		if(info!=null)
		{
			recFlow=info.getRecFlow();
		}
		gradeBiz.editKmGradeInfo(recFlow, userFlow,resultFlow,processFlow, typeId, request,GlobalConstant.RES_DGDH_DEFAULT_FORM);

		return "res/gzdh/saveData";
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
			return "res/gzdh/km/gradeDetail";
		}
		if (StringUtil.isBlank(recFlow)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价标识符为空");
			return "res/gzdh/km/gradeDetail";
		}
		if (StringUtil.isBlank(gradeType)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型为空");
			return "res/gzdh/km/gradeDetail";
		}
		if (!ResRecTypeEnum.TeacherGrade.getId().equals(gradeType) && !ResRecTypeEnum.DeptGrade.getId().equals(gradeType)) {
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "评价类型只能是TeacherGrade或DeptGrade");
			return "res/gzdh/km/gradeDetail";
		}
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if (userinfo == null) {
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/gradeDetail";
		}
		//评分内容
		DeptTeacherGradeInfo gradeInfo = gradeBiz.readByFlow(recFlow);
		if (gradeInfo != null && StringUtil.isNotBlank(gradeInfo.getRecContent()))
		{
			Map<String,Object> gradeMap = gradeBiz.parseGradeInfoXml(gradeInfo.getRecContent());
			model.addAttribute("gradeMap",gradeMap);
		}
		//评分模板
		List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
		String cfgCodeId =null;
		if(ResRecTypeEnum.TeacherGrade.getId().equals(gradeType) ){
			cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
		}else if(ResRecTypeEnum.DeptGrade.getId().equals(gradeType) ){
			cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		}
		ResAssessCfg assessCfg = gradeBiz.getGradeTemplate(cfgCodeId);
		model.addAttribute("assessCfg", assessCfg);
		Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if (titleElementList != null && !titleElementList.isEmpty()) {
			for (Element te : titleElementList) {
				ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				titleForm.setEvalTypeId(te.attributeValue("evalTypeId")== null ? "" : te.attributeValue("evalTypeId"));
				titleForm.setEvalTypeName(te.attributeValue("evalTypeName")== null ? "" : te.attributeValue("evalTypeName"));
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
		return "res/gzdh/km/gradeDetail";
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
	public String evalStudentList(String userFlow,String roleId,String seachStr,
							  Integer pageIndex,Integer pageSize,Model model) throws DocumentException, ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/evalStudentList";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/evalStudentList";
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30905");
			model.addAttribute("resultType", "当前页码为空");
			return "res/gzdh/km/evalStudentList";
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30906");
			model.addAttribute("resultType", "每页条数为空");
			return "res/gzdh/km/evalStudentList";
		}
		List<Map<String,String>> resDoctorSchProcess=null;
		Map<String, String> schArrangeResultMap=new HashMap<String, String>();
		schArrangeResultMap.put("deptFlow", userinfo.getDeptFlow());
		schArrangeResultMap.put("headFlow", userFlow);
		schArrangeResultMap.put("seachStr", seachStr);
		PageHelper.startPage(pageIndex, pageSize);
		resDoctorSchProcess=kzrBiz.schDoctorSchProcessHead(schArrangeResultMap);

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
		return "res/gzdh/km/evalStudentList";
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
			return "res/gzdh/km/monthEvalList";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/gzdh/km/monthEvalList";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzdh/km/monthEvalList";
		}
		if(StringUtil.isBlank(schResultFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/gzdh/km/monthEvalList";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/monthEvalList";
		}
		//拆分学员的培训时间
		SchArrangeResult schArrangeResult=studentBiz.searcheDocResult(null,schResultFlow);
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
		return "res/gzdh/km/monthEvalList";
	}
	@RequestMapping(value="/showMonthEval",method=RequestMethod.POST)
	public String showMonthEval(String userFlow,String doctorFlow,String processFlow,String recordFlow,String evalMonth,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/showMonthEval";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学生标识符为空");
			return "res/gzdh/km/showMonthEval";
		}
		if(StringUtil.isBlank(processFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "轮转标识符为空");
			return "res/gzdh/km/showMonthEval";
		}
		if(StringUtil.isBlank(evalMonth)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "考核时间不能为空");
			return "res/gzdh/km/showMonthEval";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/showMonthEval";
		}
		ResDoctorSchProcessEvalWithBLOBs eval=jswjwBiz.getDoctorProcessEvalByFlow(recordFlow);
		if(eval==null)
			eval=jswjwBiz.getDoctorProcessEval(processFlow,evalMonth);
		List<FromTitle> titleList=null;
		String configXml="";
		String configFlow="";
		String IsForm="N";
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
				IsForm="Y";
				configFlow=config.getConfigFlow();
			}
		}
		model.addAttribute("isAudit",eval!=null);
		titleList=jswjwBiz.parseFromXmlForList(configXml);
		if(titleList!=null&&titleList.size()>0){
			IsForm="Y";
		}else{
			IsForm="N";
		}
		model.addAttribute("titleList",titleList);
		model.addAttribute("IsForm",IsForm);
		model.addAttribute("eval",eval);
		model.addAttribute("configFlow",configFlow);
		model.addAttribute("processFlow",processFlow);
		model.addAttribute("doctorFlow",doctorFlow);
		model.addAttribute("recordFlow",recordFlow);
		return "res/gzdh/km/showMonthEval";
	}
	@RequestMapping(value="/ownerInfo",method=RequestMethod.POST)
	public String ownerInfo(String userFlow,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/ownerInfo";
		}
		//验证用户是否存在
		SysUser userinfo = appBiz.readSysUser(userFlow);
		if(userinfo==null){
			model.addAttribute("resultId", "3010103");
			model.addAttribute("resultType", "用户不存在");
			return "res/gzdh/km/ownerInfo";
		}
		model.addAttribute("userinfo",userinfo);
		List<SysUserRole> userRoles = appBiz.getSysUserRole(userFlow);
		if(userRoles!=null&&userRoles.size()>0) {
			//获取当前配置的老师角色
			String teacherRole = appBiz.getCfgCode("res_teacher_role_flow");
			//获取当前配置的科主任角色
			String headRole = appBiz.getCfgCode("res_head_role_flow");
			//获取当前配置的教学秘书角色
			String secretaryRole = appBiz.getCfgCode("res_secretary_role_flow");
			List<Map<String,String>> roles=new ArrayList<>();
			for (SysUserRole sur : userRoles) {
				Map<String,String> map=new HashMap<>();
				String ur = sur.getRoleFlow();
				if (headRole.equals(ur)) {
					map.put("roleId", "Head");
					map.put("roleName", "教学主任");
					roles.add(map);
				}
				if (secretaryRole.equals(ur)) {
					map.put("roleId", "Secretary");
					map.put("roleName", "教学秘书");
					roles.add(map);
				}
				if (teacherRole.equals(ur)) {
					map.put("roleId", "Teacher");
					map.put("roleName", "带教老师");
					roles.add(map);
				}
			}
			model.addAttribute("roles",roles);
		}
		return "res/gzdh/km/ownerInfo";
	}
	@RequestMapping(value="/saveOwnerInfo",method=RequestMethod.POST)
	public String saveOwnerInfo(SysUser user,Model model) throws ParseException {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(user.getUserFlow())){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/success";
		}
		if(StringUtil.isNotBlank(user.getSexId())) {
			if (!"Man".equals(user.getSexId()) && !"Woman".equals(user.getSexId())) {
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "性别ID不正确");
				return "res/gzdh/success";
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
			return "res/gzdh/success";
		}
		return "res/gzdh/success";
	}

	//出科审核学员列表
	@RequestMapping(value="/afterFormAudit",method={RequestMethod.POST})
	public String afterFormAudit(String userFlow,String doctorFlow,String sessionNumber,String userName,Integer pageIndex,Integer pageSize,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/afterFormAudit";
		}
		if(StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "3011101");
			model.addAttribute("resultType", "学员标识符为空");
			return "res/gzdh/km/afterFormAudit";
		}
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userFlow",userFlow);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("doctorFlow",doctorFlow);
		paramMap.put("userName",userName);
		String isOpen = appBiz.getCfgByCode("res_permit_open_doc");//权限期间是否开通
		paramMap.put("isOpen",isOpen);
		PageHelper.startPage(pageIndex, pageSize);
		List<ResDoctorSchProcess> processList = kzrBiz.searchProcessByDoctorNew(paramMap);
		model.addAttribute("dataList", processList);
		model.addAttribute("dataCount", PageHelper.total);
		if(processList!=null && processList.size()>0){
			List<String> userFlows = new ArrayList<String>();
			for(ResDoctorSchProcess rdsp : processList){
				if(!userFlows.contains(rdsp.getUserFlow())){
					userFlows.add(rdsp.getUserFlow());
				}
			}
			List<SysUser> userList = kzrBiz.searchSysUserByuserFlows(userFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser su : userList){
					userMap.put(su.getUserFlow(),su);
				}
				model.addAttribute("userMap",userMap);
			}
			List<ResDoctor> doctorList = kzrBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
				for(ResDoctor rd : doctorList){
					doctorMap.put(rd.getDoctorFlow(),rd);
				}
				model.addAttribute("doctorMap",doctorMap);
			}
		}
		return "res/gzdh/km/afterFormAudit";
	}
	//出科分类表详情
	@RequestMapping(value="/showRegistryForm",method={RequestMethod.GET})
	public String showRegistryForm(String userFlow, String resultFlow, String recTypeId, String recFlow, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "3010601");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/showRegistryForm";
		}
		if (StringUtil.isEmpty(recTypeId)) {
			model.addAttribute("resultId", "3010604");
			model.addAttribute("resultType", "数据类型标识为空");
			return "res/gzdh/km/showRegistryForm";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "3010606");
			model.addAttribute("resultType", "计划标识符为空");
			return "res/gzdh/km/showRegistryForm";
		}
		//当前轮转计划信息
		SchArrangeResult result = studentBiz.searcheDocResult(null, resultFlow);
		model.addAttribute("result", result);
		SysUser tea = appBiz.readSysUser(userFlow);
		model.addAttribute("tea", tea);

		ResDoctor doctor=appBiz.readResDoctor(result.getDoctorFlow());
		model.addAttribute("doctor", doctor);
		//获取当前这条登记信息
		ResSchProcessExpress rec = null;
		if (StringUtil.isNotBlank(recFlow)) {
			rec = expressBiz.getExpressByRecFlow(recFlow);
		} else {
			String processFlow = "";
			ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
			if (process != null) {
				//登记表单的科室
				processFlow = process.getProcessFlow();
			}
			rec = expressBiz.getExpressByRecType(processFlow, recTypeId);
		}
		boolean isAudit=false;
		String version="2.0";
		if (rec != null) {
			String content = rec.getRecContent();
			//解析登记信息的xml
			Object formDataMap = null;
			formDataMap = appBiz.parseRecContent(content);
			model.addAttribute("formDataMap", formDataMap);
			//如果读取到数据就使用数据本身的funcFlow
			recTypeId = rec.getRecTypeId();
			version = rec.getRecVersion();
			if(StringUtil.isNotBlank(rec.getHeadAuditStatusId()))
			{
				isAudit=true;
			}
		}
		//轮转科室是否为本科室
		int count=appBiz.checkIsDeptStudent(resultFlow,userFlow,tea.getDeptFlow());
		model.addAttribute("isDept", count>0);
		model.addAttribute("isAudit", isAudit);
		if (recTypeId.equals(com.pinde.res.enums.hbres.ResRecTypeEnum.AfterEvaluation.getId())) {
			ResDoctorSchProcess process = appBiz.readSchProcessByResultFlow(resultFlow);
			if (process != null) {
				String res_after_test_switch = appBiz.getCfgByCode("res_after_test_switch");
				String res_after_url_cfg = appBiz.getCfgByCode("res_after_url_cfg");
				//学员开通出科考试权限
				Map<String, String> paramMap = new HashMap();
				String cfgCode = "res_doctor_ckks_" + process.getUserFlow();
				paramMap.put("cfgCode", cfgCode);
				String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
				boolean testTypeFlag = false;
				if (GlobalConstant.FLAG_Y.equals(res_after_test_switch) && StringUtil.isNotBlank(res_after_url_cfg)) {
					testTypeFlag = true;
				}
				model.addAttribute("testTypeFlag", testTypeFlag);
				ResScore score = appBiz.getScoreByProcess(process.getProcessFlow());
				model.addAttribute("outScore", score);
				model.addAttribute("doctorSchProcess", process);
				model.addAttribute("currRegProcess", process);

				//百分比算法
				Map<String, Object> perMap = studentBiz.getRegPer(0, process.getUserFlow(), resultFlow, null, null, true);
				model.addAttribute("perMap", perMap);
				//logger.debug("===================" + JSON.toJSON(perMap));
				//参加活动的信息
				Map<String, Object> capData = new HashMap<>();
				List<Map<String, Object>> capDatas = teacherBiz.getDocCapDatas(RegistryTypeEnum.CampaignNoItemRegistry.getId(), process.getProcessFlow());
				for (Map<String, Object> m : capDatas) {
					capData.put((String) m.get("INFO_KEY"), m);
				}
				model.addAttribute("capData", capData);
			}
		}
		model.addAttribute("funcFlow", recTypeId);
		return "res/gzdh/km/showRegistryForm";
	}



	//保存出科分类表
	@RequestMapping(value="/saveRegistryForm",method={RequestMethod.POST})
	public String saveRegistryForm(String userFlow, String resultFlow, String recTypeId, String dataFlow, HttpServletRequest request,Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if (StringUtil.isEmpty(userFlow)) {
			model.addAttribute("resultId", "3010901");
			model.addAttribute("resultType", "用户标识符为空");
			return "res/gzdh/km/saveRegistryForm";
		}
		if (StringUtil.isEmpty(resultFlow)) {
			model.addAttribute("resultId", "3010904");
			model.addAttribute("resultType", "排班标识符为空");
			return "res/gzdh/km/saveRegistryForm";
		}
		if (StringUtil.isEmpty(recTypeId)) {
			model.addAttribute("resultId", "3010906");
			model.addAttribute("resultType", "功能标识为空");
			return "res/gzdh/km/saveRegistryForm";
		}
		SysUser user = appBiz.readSysUser(userFlow);
		model.addAttribute("user", user);
		//获取当前操作人
		String operUserFlow = "";
		//如果是11查出dataFlow
		ResSchProcessExpress rec = null;
		if (StringUtil.isEmpty(dataFlow)) {
			String processFlow = "";
			ResDoctorSchProcess process = studentBiz.getProcessByResult(resultFlow);
			if (process != null) {
				//登记表单的科室
				processFlow = process.getProcessFlow();
				operUserFlow = process.getUserFlow();
			}
			rec = expressBiz.getExpressByRecType(processFlow, recTypeId);
			if (rec != null) {
				dataFlow = rec.getRecFlow();
			}else{
				model.addAttribute("resultId", "3010907");
				model.addAttribute("resultType", "该出科记录不存在");
				return "res/gzdh/km/saveRegistryForm";
			}
		} else {
			rec = expressBiz.getExpressByRecFlow(dataFlow);
		}
		String content="";

		String version="";
		if (rec != null) {
			recTypeId = rec.getRecTypeId();
			version=rec.getRecVersion();
			content=rec.getRecContent();
		}
		request.setAttribute("recTypeId",recTypeId);
		dataFlow = expressBiz.editZseyExpress(dataFlow, operUserFlow, resultFlow, recTypeId, GlobalConstant.RES_DGDH_DEFAULT_FORM, request,GlobalConstant.RES_ROLE_SCOPE_HEAD,content,version);
		//审核数据
		kzrBiz.auditDate(userFlow, dataFlow);
		return "res/gzdh/km/saveRegistryForm";
	}
}

