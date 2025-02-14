package com.pinde.res.ctrl.jswjw;

import com.alibaba.fastjson.JSON;
import com.pinde.app.common.InitConfig;
import com.pinde.core.common.form.UserResumeExtInfoForm;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.*;
import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.core.common.enums.osca.DoctorScoreEnum;
import com.pinde.core.common.enums.osca.ScanDocStatusEnum;
import com.pinde.core.common.enums.osca.SignStatusEnum;
import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.res.biz.common.impl.DictBizImpl;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.hbres.IResInprocessInfoBiz;
import com.pinde.res.biz.jswjw.*;
import com.pinde.res.biz.osca.IOscaAppBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.dao.jswjw.ext.JsResPowerCfgExtMapper;
import com.pinde.res.dao.jswjw.ext.JsResUserBalckListExtMapper;
import com.pinde.res.dao.jswjw.ext.TempMapper;
import com.pinde.sci.util.DateTimeUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.bouncycastle.util.encoders.Hex;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/res/jswjw")
public class JswjwAppController {
    private static Logger logger = LoggerFactory.getLogger(JswjwAppController.class);

    private static String regex = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_.!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_.!@#$%^&*`~()-+=]+$)(?![0-9\\W_.!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_.!@#$%^&*`~()-+=]{8,20}$";

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        //临时休眠
//		try {
//			Thread.sleep(Integer.MAX_VALUE);
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
        return "res/jswjw/500";
    }

    @Autowired
    private IResLiveTrainingBiz resLiveTrainingBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private IJswjwBiz jswjwBiz;
    @Autowired
    private IResOrgTimeBiz timeBiz;
    @Autowired
    private IResGradeBiz gradeBiz;
    @Autowired
    private IJswjwStudentBiz jswjwStudentBiz;
    @Autowired
    private IOscaAppBiz oscaAppBiz;
    @Autowired
    private INoticeBiz noticeBiz;
    @Autowired
    private IInxInfoBiz inxInfoBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ISchExamScoreQueryBiz scoreQueryBiz;
    @Autowired
    private ISchExamCfgBiz examCfgBiz;

    @Autowired
    private JsResUserBalckListExtMapper balcklistExtMapper;

    @Autowired
    private IJswjwTeacherBiz jswjwTeacherBiz;
    @Autowired
    private ISchAndStandardDeptCfgBiz deptCfgBiz;
    @Autowired
    private ResPaperBiz paperBiz;
    @Autowired
    private IResInprocessInfoBiz resInprocessInfoBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private IResActivityBiz activityBiz;
    @Autowired
    private IResActivityTargetBiz activityTargeBiz;
    @Autowired
    private IExamResultsBiz examResultsBiz;
    @Autowired
    private SysCfgMapper cfgMapper;
    @Autowired
    private SysDictMapper dictMapper;
    @Autowired
    private TempMapper tempMapper;
    @Autowired
    private VerificationCodeRecordMapper verificationCodeRecordMapper;
    @Autowired
    private SysLogMapper logMapper;
    @Autowired
    private DictBizImpl dictBiz;
    @Autowired
    private JsResPowerCfgExtMapper powerCfgExtMapper;
    @Resource
    private ResDoctorSchProcessMapper processMapper;
    @Resource
    private SchArrangeResultMapper resultMapper;
    @Autowired
    private JsresPowerCfgMapper powerCfgMapper;
    @Autowired    private ResOrgCkxzMapper resOrgCkxzMapper;

    private final String versionIosNumber = "2.0.32";
    private final String versionAndroidNumber = "2.0.41";
    @RequestMapping(value = {"/version"}, method = {RequestMethod.GET})
    public String version(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        model.addAttribute("versionIosNumber", versionIosNumber);
        model.addAttribute("versionAndroidNumber", versionAndroidNumber);
        Integer onlineCountNum = (Integer) request.getServletContext().getAttribute("onlineCountNum");
        if (onlineCountNum == null || onlineCountNum == 0) {
            model.addAttribute("onlineCountNum", 0);
        } else {
            model.addAttribute("onlineCountNum", onlineCountNum);
        }
        return "res/jswjw/version";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        return "res/jswjw/test";
    }

    @RequestMapping(value = {"/remember"}, method = {RequestMethod.GET})
    public String remember(String userFlow, String deptFlow, String cataFlow, String funcTypeId, String funcFlow, String dataFlow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userFlow", userFlow);
        session.setAttribute("deptFlow", deptFlow);
        session.setAttribute("cataFlow", cataFlow);
        session.setAttribute("dataFlow", dataFlow);
        session.setAttribute("funcTypeId", funcTypeId);
        session.setAttribute("funcFlow", funcFlow);
        return "/res/jswjw/test";
    }

    public boolean isChargeOrg(String userFlow) {
//		ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
//		if(doctor!=null){
//			return com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jswjwBiz.getCfgCode("jswjw_"+doctor.getOrgFlow()+"_P001"));
//		}else {
//			return false;
//		}
        if (StringUtil.isNotBlank(userFlow)) {
            return com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jswjwBiz.getJsResCfgAppMenu(userFlow));
        } else {
            return false;
        }
    }

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
//    public String login(String systemName, String versionNumber, String userCode, String userPasswd,String check, String uuid, HttpServletRequest request, HttpServletResponse response, Model model) {
    public String login(String systemName, String versionNumber, String userCode, String userPasswd, String uuid, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
//        if(StringUtil.isNotBlank(userCode) && StringUtil.isNotBlank(check)){
//            String currDate = DateUtil.getCurrDate();
//            String checkPc = userCode+currDate;
//            String s = PasswordHelper.MD5(checkPc);
//            if(StringUtil.isNotBlank(s) && !check.equals(s)){
//                model.addAttribute("resultId", "200");
//                model.addAttribute("resultType", "密钥不匹配,拒绝访问");
//                return "res/jswjw/login";
//            }
//        }
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

//		long startTime = System.currentTimeMillis();
//		System.err.println("验证用户是否有角色 开始时间======"+startTime);
		/*if(StringUtil.isEmpty(systemName)){
			model.addAttribute("resultId", "30100");
			model.addAttribute("resultType", "当前不是最新版本，请下载最新版本App！");
			return "res/jswjw/login";
		}
		if(StringUtil.isEmpty(versionNumber)){
			model.addAttribute("resultId", "30100");
			model.addAttribute("resultType", "当前不是最新版本，请下载最新版本App！");
			return "res/jswjw/login";
		}
		if ("ios".equals(systemName)){
			if (!versionIosNumber.equals(versionNumber)){
				model.addAttribute("resultId", "30100");
				model.addAttribute("resultType", "目前不是最新版本，请下载最新版本App！");
				return "res/jswjw/login";
			}
		}
		else if ("android".equals(systemName)){
			if (!versionAndroidNumber.equals(versionNumber)){
				model.addAttribute("resultId", "30100");
				model.addAttribute("resultType", "目前不是最新版本，请下载最新版本App！");
				return "res/jswjw/login";
			}
		}*/
        if (StringUtil.isEmpty(userCode)) {
            model.addAttribute("resultId", "30101");
            model.addAttribute("resultType", "用户名为空");
            return "res/jswjw/login";
        }
        if (StringUtil.isEmpty(userPasswd)) {
            model.addAttribute("resultId", "30102");
            model.addAttribute("resultType", "密码为空");
            return "res/jswjw/login";
        }
        SysUser userinfo = jswjwBiz.findByUserCode(userCode);
        if (userinfo == null) {
            userinfo = jswjwBiz.findByUserPhone(userCode);
        }
        if (userinfo == null) {
            model.addAttribute("resultId", "30199");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/login";
        }
        ServletContext application = request.getServletContext();
        if (application.getAttribute("onlineCountNum") == null) {
            application.setAttribute("onlineCountNum", 1);
        } else {
            application.setAttribute("onlineCountNum", (Integer) application.getAttribute("onlineCountNum") + 1);
        }
        return getUserInfo(userinfo, model, userPasswd, uuid, request);
    }

    /**
     * 校验学员数据填写：限制登录
     *
     * @param model
     * @param doctor
     * @return
     */
    private boolean checkDoctorDataFilling(Model model, ResDoctor doctor) {
        String message = "由于您长时间未填写培训数据，现无法继续使用APP，请联系管理员！";
        String lockStatus = doctor.getLockStatus() == null ? "" : doctor.getLockStatus();
        if ("Lock".equals(lockStatus)) {
            model.addAttribute("resultId", "30299");
            model.addAttribute("resultType", message);
            return true;
        }
        String loginLimit = jswjwBiz.getCfgCode("student_timeout_login_limit");
        if (StringUtil.isNotBlank(loginLimit) && !com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(loginLimit)) {
            String doctorFlow = doctor.getDoctorFlow();
            // 当前时间
            String currentTime = DateUtil.getCurrDate();
            int continuousMonth = 0;
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(loginLimit);
            if (isNum.matches()) {
                continuousMonth = Integer.parseInt(loginLimit);
            }
            if ("UnLock".equals(lockStatus)) {
                Boolean flag = false;
                if (continuousMonth > 0) {
                    Map<String, Object> queryMap = new HashMap<>();
                    queryMap.put("doctorFlow", doctorFlow);
                    List<DoctorUntiedRecording> RecordingList = jswjwBiz.queryDoctorUnLockDate(queryMap);
                    if (RecordingList != null && RecordingList.size() > 0) {
                        DoctorUntiedRecording doctorUntiedRecording = RecordingList.get(0);
                        String untiedDate = doctorUntiedRecording.getUntiedDate() == null ? "" : doctorUntiedRecording.getUntiedDate();
                        if (StringUtil.isNotBlank(untiedDate)) {
                            // 解锁时间推+后n月份时间
                            String dayTime = DateTimeUtil.getAfterDate(untiedDate, continuousMonth, "month");
                            try {
                                int num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), dayTime);
                                if (num == 1) {
                                    Map<String, Object> paramMap = new HashMap<>();
                                    paramMap.put("startTime", untiedDate.replace("-", "") + "000000");
                                    paramMap.put("endTime", dayTime.replace("-", "") + "235959");
                                    paramMap.put("doctorFlow", doctorFlow);
                                    // 查询学员填写数据记录
                                    int dateCount = jswjwBiz.queryStudentsFilldDta(paramMap);
                                    if (!(dateCount > 0)) {
                                        flag = true;
                                    }
                                }
                            } catch (ParseException e) {
                                logger.error("", e);
                            }
                        } else {
                            flag = true;
                        }

                    } else {
                        flag = true;
                    }
                }

                if (flag) {
                    // 锁定账号
                    doctor.setLockStatus("Lock");
                    int count = doctorBiz.editDoctor(doctor);
                    if (count > 0) {
                        // 锁定记录
                        DoctorUntiedRecording recording = new DoctorUntiedRecording();
                        recording.setLockStatus("Lock");
                        recording.setDoctorFlow(doctorFlow);
                        recording.setLockDate(currentTime);
                        jswjwBiz.updateRecording(recording);
                    }
                    model.addAttribute("resultId", "30299");
                    model.addAttribute("resultType", message);
                    return true;
                }
            } else {
                if (continuousMonth > 0) {
                    // 新用户 n个月内不校验
                    String createTime = doctor.getCreateTime() == null ? "" : doctor.getCreateTime();
                    if (StringUtil.isNotBlank(createTime) && createTime.length() >= 8) {
                        try {
                            String substring = createTime.substring(0, 4) + "-" + createTime.substring(4, 6) + "-" + createTime.substring(6, 8);
                            String month = DateTimeUtil.getAfterDate(substring, continuousMonth, "month");
                            int month2 = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), month);
                            if (month2 == -1) {
                                return false;
                            }
                        } catch (ParseException e) {
                            logger.error("", e);
                        }

                    }
					/*SysLog sysLog = new SysLog();
					sysLog.setUserFlow(doctorFlow);
					sysLog.setOperId("AppLogin");
					int loginCount = jswjwBiz.queryStudenLoginLog(sysLog);
					if(loginCount == 0){
						return false;
					}*/
                    // 获取时间范围
                    String startTime = DateTimeUtil.getAfterDate(currentTime, -continuousMonth, "month");
                    Map<String, Object> paramMap = new HashMap<>();
                    paramMap.put("startTime", startTime.replace("-", "") + "000000");
                    paramMap.put("endTime", currentTime.replace("-", "") + "235959");
                    paramMap.put("doctorFlow", doctorFlow);
                    // 查询学员填写数据记录
                    int dateCount = jswjwBiz.queryStudentsFilldDta(paramMap);

                    if (!(dateCount > 0)) {
                        // 锁定账号
                        doctor.setLockStatus("Lock");
                        int count = doctorBiz.editDoctor(doctor);
                        if (count > 0) {
                            // 锁定记录
                            DoctorUntiedRecording recording = new DoctorUntiedRecording();
                            recording.setLockStatus("Lock");
                            recording.setDoctorFlow(doctorFlow);
                            recording.setLockDate(currentTime);
                            jswjwBiz.updateRecording(recording);
                        }
                        model.addAttribute("resultId", "30299");
                        model.addAttribute("resultType", message);
                        return true;
                    }
                } else {
                    logger.error("已启用限制登录APP但配置月份信息有误，检查表【SYS_CFG】中 CFG_CODE = 'student_timeout_login_limit_value' 配置月份值是否大于0！");
                }
            }
        }
        return false;
    }

//    @RequestMapping(value = {"/planUserMsgList"}, method = {RequestMethod.POST})
//    public String planUserMsgList(String userFlow, Integer pageIndex, Integer pageSize,Model model,String planFlow,String certificateNo,String startTime,String endTime,  HttpServletRequest request, HttpServletResponse response) {
//        model.addAttribute("resultId", "200");
//        model.addAttribute("resultType", "交易成功");
//        Map<String,Object> paramMap = new HashMap<>();
//        if (StringUtil.isEmpty(userFlow)) {
//            model.addAttribute("resultId", "31601");
//            model.addAttribute("resultType", "userFlow为空");
//            return "res/jswjw/planUserMsgList";
//        }
//        if (pageIndex == null) {
//            model.addAttribute("resultId", "30202");
//            model.addAttribute("resultType", "当前页码为空");
//            return "res/jswjw/planUserMsgList";
//        }
//        if (pageSize == null) {
//            model.addAttribute("resultId", "30203");
//            model.addAttribute("resultType", "每页条数为空");
//            return "res/jswjw/planUserMsgList";
//        }
//        paramMap.put("planFlow",planFlow);
//        paramMap.put("certificateNo",certificateNo);
//        paramMap.put("gainCertificateId",com.pinde.core.common.GlobalConstant.FLAG_Y);
//        paramMap.put("sendCertificateId",com.pinde.core.common.GlobalConstant.FLAG_Y);
//        paramMap.put("startTime",startTime);
//        paramMap.put("endTime",endTime);
//        List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("CertificateTermValidity");
//        if (null !=dictList && dictList.size()>0){
//            model.addAttribute("dayNum", dictList.get(0).getDictId());
//            paramMap.put("dayNum",dictList.get(0).getDictId());
//        }
//        paramMap.put("userFlow",userFlow);
//        PageHelper.startPage(pageIndex, pageSize);
//        List<Map<String, Object>> doctorList = jswjwBiz.phyAssDoctorList(paramMap);
//        model.addAttribute("doctorList", doctorList);
//        model.addAttribute("dataCount", PageHelper.total);
//        return "res/jswjw/planUserMsgList";
//    }

    @RequestMapping(value = {"/rotationList"}, method = {RequestMethod.POST})
    public String rotationList(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/rotationList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/rotationList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/rotationList";
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.getRecruitList(userFlow);
        if (null == recruitList || recruitList.size() == 0) {
            model.addAttribute("resultId", "30204");
            model.addAttribute("resultType", "该学员暂未培训信息！");
            return "res/jswjw/rotationList";
        }
        model.addAttribute("recruitList", recruitList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/jswjw/rotationList";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/deptList"}, method = {RequestMethod.GET})
    public String deptList(String userFlow, String rotationFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/deptList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/deptList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/deptList";
        }

        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
//		if(doctor==null||StringUtil.isBlank(doctor.getRotationFlow())){
        if (doctor == null || StringUtil.isBlank(rotationFlow)) {
            model.addAttribute("resultId", "30204");
            model.addAttribute("resultType", "该学员未设置轮转方案!");
            return "res/jswjw/deptList";
        }

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("doctorFlow", doctor.getDoctorFlow());
//		paramMap.put("rotationFlow",doctor.getRotationFlow());
        paramMap.put("rotationFlow", rotationFlow);

        String trainingType = doctor.getTrainingTypeId();
        String sessionNumber = doctor.getSessionNumber();
        String trainingYears = doctor.getTrainingYears();
        boolean isReduction = com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType);
        isReduction = isReduction && "2015".compareTo(sessionNumber) <= 0;
        isReduction = isReduction && (TrainYearEnum.OneYear.getId().equals(trainingYears) || TrainYearEnum.TwoYear.getId().equals(trainingYears));
        paramMap.put("isReduction", isReduction ? com.pinde.core.common.GlobalConstant.FLAG_Y : com.pinde.core.common.GlobalConstant.FLAG_N);

        Integer deptCount = jswjwBiz.searchSchRotationDeptCount(paramMap);
        model.addAttribute("dataCount", deptCount);
//        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> deptList = jswjwBiz.searchSchRotationDept(paramMap);

        //查看轮转计划排班设置是否开启
        JsresPowerCfg cfg = jswjwBiz.readPowerCfg("jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_N");
        if (null != cfg && StringUtil.isNotBlank(cfg.getCfgValue()) && cfg.getCfgValue().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            //查看轮转计划排班最短时间
            JsresPowerCfg cfgTime = jswjwBiz.readPowerCfg("jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_time");
            if (null!=cfgTime && StringUtil.isNotBlank(cfgTime.getCfgValue())){
                Integer cfgValue = Integer.parseInt(cfgTime.getCfgValue());//轮转计划排班最短时间

                int years=3;
                //查看学员的减免年份
                if (null == trainingYears || StringUtil.isEmpty(trainingYears)){
                    years=3;
                }else if (trainingYears.equals("ThreeYear")){
                    years=3;
                } else if (trainingYears.equals("TwoYear")){
                    years=2;
                } else if (trainingYears.equals("OneYear")){
                    years=1;
                }
                //提醒所有学员（除2020级学员，2021级减免学员，2022级减免2年学员）需要在 2023 年7月1日前设置完最少一年的轮转记录,否则将无法填写轮转数据,添加轮转记录
                boolean flag=true;
                if (Integer.parseInt(doctor.getSessionNumber())<=2020){
                    flag=false;
                }else if (doctor.getSessionNumber().equals("2020")){
                    flag=false;
                }else if (doctor.getSessionNumber().equals("2021") && years >=1){
                    flag=false;
                }else if (doctor.getSessionNumber().equals("2022") && years==2){
                    flag=false;
                }else if (Integer.parseInt(doctor.getSessionNumber())<=2021 && doctor.getTrainingTypeId().equals("AssiGeneral")){
                    flag=false; //21年和21年之前的助理全科都不提示
                }
                //只要有待审核的，就提示，并且不给填写数据
                //逻辑变动先保留
                List<SchArrangeResult> results = jswjwBiz.searchSchArrangeResultPassing(doctor.getDoctorFlow(), rotationFlow);
                if (results!=null && results.size()>0){
                    model.addAttribute("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                    model.addAttribute("cfgValue","尚有需要审核的轮转科室数据！");
                    flag=false;
                }
                if (flag){
                    //如果学员减免年份小于基地设置的时间，默认轮转最短时间为减免时间
                    if (cfgValue>=years){
                        cfgValue=years;
                    }
                    //需求：入科后，要求学员把至少n年(基地设置)的科室轮转情况填写完成，到时间后，继续要求学员填写下一阶段的轮转情况，包括轮转科室、轮转时间
                    String monthNum = jswjwBiz.searchByDoctorAndRotationFlow(userFlow, rotationFlow);
                    if (null == monthNum || StringUtil.isEmpty(monthNum)){ //没有填写，提示需要填写数据
                        model.addAttribute("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                        model.addAttribute("cfgValue","请至少填写"+cfgValue+"年的科室轮转情况");
                    }else {
                        double num = Double.parseDouble(monthNum);
                        //基地最短时间只能选择1年、2年、3年
                        //判断学员填写的总月份是否超过基地设置的时间
                        if (num<cfgValue*12){
                            model.addAttribute("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                            model.addAttribute("cfgValue","请至少填写未来"+cfgValue+"年的科室轮转情况");
                        }else {
                            //超过了基地设置的时间
                            com.pinde.core.model.ResDoctorRecruit recruit = jswjwBiz.getNewRecruit(userFlow);
                            //获取学员入培时间和现在时间相差的月份
                            long month = DateUtil.signMonthBetweenTwoMonth(recruit.getRecruitDate(), DateUtil.getCurrDate());
                            //如果相差一年，说明就是现在就是第二年（第二次），否则就是第三次
                            if (month>cfgValue*12){
                                if (month>12 && month <24){
                                    if (num<cfgValue*24){   //学员填写的轮转计划总月份小于 基地设置的轮转最短时间
                                        model.addAttribute("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                                        cfgValue=years-1<cfgValue?cfgValue=years-1:cfgValue;
                                        model.addAttribute("cfgValue", "请至少填写未来"+cfgValue+"年的科室轮转情况");
                                    }
                                }else {
                                    if (num<cfgValue*36){   //学员填写的轮转计划总月份小于 基地设置的轮转最短时间
                                        model.addAttribute("edit", com.pinde.core.common.GlobalConstant.FLAG_N);
                                        cfgValue=years-2<cfgValue?cfgValue=years-2:cfgValue;
                                        model.addAttribute("cfgValue", "请至少填写未来"+cfgValue+"年的科室轮转情况");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        for (Map<String, Object> map : deptList) {
//            //处理deptName
//            String deptName = (String) map.get("deptName");
//            if (deptName.indexOf(".") > -1) {
//                map.put("deptName", deptName.substring(deptName.lastIndexOf(".") + 1, deptName.length()));
//            }
//        }
        model.addAttribute("rotationDeptMap", deptList);
        String isChargeOrg = jswjwBiz.getJsResCfgAppMenu(doctor.getDoctorFlow());
        boolean f = false;
        //派送单位或者学校
//		if ("Graduate".equals(doctor.getDoctorTypeId())) {
//			String isJDZSGC = jswjwBiz.getCfgCode("jswjw_" + doctor.getOrgFlow() + "_P008");
//			if (StringUtil.isNotBlank(doctor.getWorkOrgId())) {
//				String isSendFlag = jswjwBiz.getCfgCode("jswjw_sendSchool_" + doctor.getWorkOrgId() + "_P007");
//				if (!(com.pinde.core.common.GlobalConstant.FLAG_N.equals(isSendFlag) && (null == isJDZSGC || com.pinde.core.common.GlobalConstant.FLAG_N.equals(isJDZSGC)))) {//如果学校未开且基地专硕未开
//					f=true;
//				}
//			}
//			if(!f) {
//				f=false;
//				String isJDGC = jswjwBiz.getCfgCode("jswjw_" + doctor.getOrgFlow() + "_P001");
//				if ((com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJDGC) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJDZSGC))) {
//					f=true;
//				}
//			}
//		}else if(StringUtil.isNotBlank(isChargeOrg)&&com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isChargeOrg)){
//			f=true;
//		}
        if (StringUtil.isNotBlank(isChargeOrg) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isChargeOrg) && "Passed".equals(doctor.getCheckStatusId())) {
            f = true;
        }

//		model.addAttribute("isChargeOrg",isChargeOrg);
        if (f) {
            Map<String, String> progressMap = jswjwBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow());
            model.addAttribute("progressMap", progressMap);
        }
        return "res/jswjw/deptList";
    }

    @RequestMapping(value = {"/afterDeptList"}, method = {RequestMethod.POST})
    public String afterDeptList(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/afterDeptList";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "30402");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/afterDeptList";
        }

        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            model.addAttribute("resultId", "30403");
            model.addAttribute("resultType", "请联系管理员维护考试地址!");
            return "res/jswjw/afterDeptList";
        }
        List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, pageIndex, pageSize);
        model.addAttribute("resultList", resultList);
        model.addAttribute("dataCount", PageHelper.total);
        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            if (process != null) {
                processMap.put(result.getResultFlow(), process);
                ResScore score = jswjwTeacherBiz.readScoreByProcessFlow(process.getProcessFlow());
                scoreMap.put(result.getResultFlow(), score);
            }
        }
        String currDate = DateUtil.getCurrDate();
        model.addAttribute("currDate", currDate);
        model.addAttribute("processMap", processMap);
        model.addAttribute("scoreMap", scoreMap);
        return "res/jswjw/afterDeptList";
    }


    /**
     * 查看错题
     *
     * @param model
     * @return
     */
    @RequestMapping(value = {"/viewError"}, method = {RequestMethod.GET})
    public String viewError(String processFlow, Model model) {
        String result = "res/jswjw/viewError";
        model.addAttribute("resultId", ResultEnum.Success.getId());
        model.addAttribute("resultType", ResultEnum.Success.getName());
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "轮转科室标识符为空");
            return result;
        }
        List<ExamResults> results = examResultsBiz.getByProcessFlow(processFlow);
        model.addAttribute("results", results);
        return "res/jswjw/viewError";
    }

    @RequestMapping(value = {"/viewErrorDetail"}, method = {RequestMethod.GET})
    public String viewErrorDetail(String processFlow, String resultsId, Model model) {
        String result = "res/jswjw/viewErrorDetail";
        model.addAttribute("resultId", ResultEnum.Success.getId());
        model.addAttribute("resultType", ResultEnum.Success.getName());
        if (StringUtil.isBlank(processFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "轮转科室标识符为空");
            return result;
        }

        if (!StringUtil.isNotBlank(resultsId)) {
            model.addAttribute("resultId", "30402");
            model.addAttribute("resultType", "当前考试试卷ID为空");
            return result;
        }

        //错题查看地址
        String testUrl = jswjwBiz.getCfgCode("res_after_wrong_exam_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            model.addAttribute("resultId", "30403");
            model.addAttribute("resultType", "请联系管理员维护错题查看地址");
            return result;
        }
        ExamResults results = examResultsBiz.getByFlow(resultsId);

        if (results == null) {
            model.addAttribute("resultId", "30403");
            model.addAttribute("resultType", "当前考试记录信息获取失败");
            return result;
        }

        testUrl = testUrl + "?RequestType=pc&ProcessFlow=" + processFlow + "&SoluID=" + results.getSoluId() + "&ResultID=" + resultsId;
        model.addAttribute("testUrl", testUrl);
        return result;
    }

    @RequestMapping(value = {"/allAfterDept"}, method = {RequestMethod.POST})
    public String allAfterDept(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/allAfterDept";
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            model.addAttribute("resultId", "30403");
            model.addAttribute("resultType", "请联系管理员维护考试地址!");
            return "res/jswjw/allAfterDept";
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (null == doctor) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不能为空");
            return "res/jswjw/allAfterDept";
        }
        List<SchArrangeResult> resultList = jswjwBiz.getSchArrangeResult(userFlow, doctor.getOrgFlow(), pageIndex, pageSize, doctor.getRotationFlow());
        model.addAttribute("resultList", resultList);
        model.addAttribute("dataCount", PageHelper.total);
        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();

        Map<String, String> countMap = new HashMap<>();
        List<String> orgFlows = new ArrayList<>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            if (process != null) {
                processMap.put(result.getResultFlow(), process);
                ResScore score = jswjwTeacherBiz.readScoreByProcessFlow(process.getProcessFlow());
                scoreMap.put(result.getResultFlow(), score);
                List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
                if (examResultsList != null)
                    countMap.put(process.getProcessFlow(), examResultsList.size() + "");
            }

            if (!orgFlows.contains(result.getOrgFlow())) {
                String powerCfg = jswjwBiz.getJsResCfgCode("out_test_limit_" + result.getOrgFlow());
                if (powerCfg != null && StringUtil.isNotBlank(powerCfg)) {
                    countMap.put((String) result.getOrgFlow(), powerCfg);
                }
                orgFlows.add((String) result.getOrgFlow());
            }

        }
        model.addAttribute("countMap", countMap);
        String currDate = DateUtil.getCurrDate();
        model.addAttribute("currDate", currDate);
        model.addAttribute("processMap", processMap);
        model.addAttribute("scoreMap", scoreMap);
        return "res/jswjw/allAfterDept";
    }

    @RequestMapping(value = {"/joinExam"}, method = {RequestMethod.POST})
    public String joinExam(String userFlow, String schDeptFlow, String resultFlow, String processFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/joinExam";
        }
        if (StringUtil.isEmpty(schDeptFlow)) {
            model.addAttribute("resultId", "40402");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/joinExam";
        }

        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "40403");
            model.addAttribute("resultType", "轮转计划标识符为空");
            return "res/jswjw/joinExam";
        }
        if (StringUtil.isEmpty(processFlow)) {
            model.addAttribute("resultId", "40404");
            model.addAttribute("resultType", "请重新维护轮转信息");
            return "res/jswjw/joinExam";
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "请联系管理员维护考试地址!");
            return "res/jswjw/joinExam";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);

        SchAndStandardDeptCfg cfg = deptCfgBiz.readBySchDeptFlow(schDeptFlow);
        if (cfg == null) {
            model.addAttribute("resultId", "40406");
            model.addAttribute("resultType", "请基地管理员维护出科考核标准科室!");
            return "res/jswjw/joinExam";
        }
        ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(resultFlow);
        if (process == null) {
            model.addAttribute("resultId", "40404");
            model.addAttribute("resultType", "当前轮转信息获取失败!");
            return "res/jswjw/joinExam";
        }
        String TestNum = "";
//        String powerCfg = jswjwBiz.getJsResCfgCode("out_test_limit_" + process.getOrgFlow());
//        if (powerCfg != null && StringUtil.isNotBlank(powerCfg)) {
//            TestNum = powerCfg;
//            int c = 0;
//            List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
//            if (examResultsList != null)
//                c = examResultsList.size();
//            if (c >= Integer.valueOf(powerCfg)) {
//                model.addAttribute("resultId", "40404");
//                model.addAttribute("resultType", "当前考试次数已经达到" + powerCfg + ",无法再次考试!");
//                return "res/gzzyyy/joinExam";
//            }
//        }
        //禅道201 修改
        JsresPowerCfg jsresPowerCfg = jswjwBiz.readPowerCfg("out_test_check_" + process.getOrgFlow());
        if (jsresPowerCfg != null && StringUtil.isNotBlank(jsresPowerCfg.getCfgValue()) &&
                com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(jsresPowerCfg.getCfgValue())) {
            //查询科室是否单独配置出科考核参数
            JsresDeptConfig deptConfig = jswjwBiz.searchDeptCfg(process.getOrgFlow(), process.getSchDeptFlow());
            if (null != deptConfig) {
                if (StringUtil.isNotBlank(deptConfig.getTestNum())) {
                    TestNum = deptConfig.getTestNum();
                    int c = 0;
                    List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
                    if (examResultsList != null)
                        c = examResultsList.size();
                    if (c >= Integer.valueOf(TestNum)) {
                        model.addAttribute("resultId", "40404");
                        model.addAttribute("resultType", "当前考试次数已经达到" + deptConfig.getTestNum() + ",无法再次考试!");
                        return "res/gzzyyy/joinExam";
                    }
                }
                //是否允许学员轮转时间结束后考试
                if (StringUtil.isNotBlank(deptConfig.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(deptConfig.getIsTestOut())) {
                    //不允许  则判断时间是否在轮转时间内
                    String currDate = DateUtil.getCurrDate();
                    String endDate = process.getSchEndDate();
                    try {
                        int num = this.compareDate(endDate, currDate);
                        if (num > 0) {
                            model.addAttribute("resultId", "40404");
                            model.addAttribute("resultType", "轮转时间结束,无法参加考试!");
                            return "res/gzzyyy/joinExam";
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                } else if (StringUtil.isNotBlank(deptConfig.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(deptConfig.getIsTestOut())) {
                    String currDate = DateUtil.getCurrDate();
                    String endDate = process.getSchEndDate();
                    if (StringUtil.isNotBlank(deptConfig.getTestOutTime())) {
                        String testOutDate = deptConfig.getTestOutTime();
                        String newDate = DateUtil.addDate(endDate, Integer.parseInt(testOutDate));
                        try {
                            int num = this.compareDate(newDate, currDate);
                            if (num > 0) {
                                model.addAttribute("resultId", "40404");
                                model.addAttribute("resultType", "超出轮转时间结束后" + testOutDate + "天,无法参加考试!");
                                return "res/gzzyyy/joinExam";
                            }
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                    }
                }
            } else {
                //未单独配置  取全局配置
                JsresDeptConfig config = jswjwBiz.searchBaseDeptConfig(process.getOrgFlow());
                if (null != config) {
                    if (StringUtil.isNotBlank(config.getTestNum())) {
                        TestNum = config.getTestNum();
                        int c = 0;
                        List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
                        if (examResultsList != null)
                            c = examResultsList.size();
                        if (c >= Integer.valueOf(TestNum)) {
                            model.addAttribute("resultId", "40404");
                            model.addAttribute("resultType", "当前考试次数已经达到" + config.getTestNum() + ",无法再次考试!");
                            return "res/gzzyyy/joinExam";
                        }
                    }
                    //是否允许学员轮转时间结束后考试
                    if (StringUtil.isNotBlank(config.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(config.getIsTestOut())) {
                        //不允许  则判断时间是否在轮转时间内
                        String currDate = DateUtil.getCurrDate();
                        String endDate = process.getSchEndDate();
                        try {
                            int num = this.compareDate(endDate, currDate);
                            if (num > 0) {
                                model.addAttribute("resultId", "40404");
                                model.addAttribute("resultType", "轮转时间结束,无法参加考试!");
                                return "res/gzzyyy/joinExam";
                            }
                        } catch (Exception e) {
                            logger.error("", e);
                        }
                    } else if (StringUtil.isNotBlank(config.getIsTestOut()) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(config.getIsTestOut())) {
                        String currDate = DateUtil.getCurrDate();
                        String endDate = process.getSchEndDate();
                        if (StringUtil.isNotBlank(config.getTestOutTime())) {
                            String testOutDate = config.getTestOutTime();
                            String newDate = DateUtil.addDate(endDate, Integer.parseInt(testOutDate));
                            try {
                                int num = this.compareDate(newDate, currDate);
                                if (num > 0) {
                                    model.addAttribute("resultId", "40404");
                                    model.addAttribute("resultType", "超出轮转时间结束后" + testOutDate + "天,无法参加考试!");
                                    return "res/gzzyyy/joinExam";
                                }
                            } catch (Exception e) {
                                logger.error("", e);
                            }
                        }
                    }
                }
            }
        }
        //试卷id
        String ExamSoluID = "0";
        //时间戳
        String Date = "0";
        //标准科室
        String standardDeptId = cfg.getStandardDeptId();
        ResPaper paper = paperBiz.getPaperByOrgStandardDeptId(process.getOrgName(), standardDeptId);
        if (paper == null) {
            paper = paperBiz.getPaperByStandardDeptId(standardDeptId);
        }
        if (paper != null) {
            ExamSoluID = paper.getPaperFlow();
        }
        if ("0".equals(ExamSoluID)) {
            model.addAttribute("resultId", "40407");
            model.addAttribute("resultType", "该科室暂无试卷信息!");
            return "res/jswjw/joinExam";
        }
        SchArrangeResult result = jswjwTeacherBiz.readSchArrangeResult(resultFlow);
        //创建分数数据
        ResScore score = jswjwTeacherBiz.readScoreByProcessFlow(processFlow);
        if (score == null) {
            score = new ResScore();
            score.setDoctorFlow(userFlow);
            score.setScoreTypeId(com.pinde.core.common.enums.ResScoreTypeEnum.DeptScore.getId());
            score.setScoreTypeName(com.pinde.core.common.enums.ResScoreTypeEnum.DeptScore.getName());
            score.setResultFlow(resultFlow);
            score.setProcessFlow(processFlow);
            score.setSchDeptFlow(result.getSchDeptFlow());
            score.setSchDeptName(result.getSchDeptName());
        }

        score.setPaperFlow(ExamSoluID);

        int saveResult = jswjwBiz.saveScore(score, user);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
            model.addAttribute("resultId", "40408");
            model.addAttribute("resultType", "分数信息创建出错!");
            return "res/jswjw/joinExam";
        }
        testUrl = testUrl + "?Action=ChuKeMobileExam&ExamSoluID=" + ExamSoluID + "&CardID=" + URLEncoder.encode(user.getUserCode(), "utf-8") + "&ProcessFlow=" + processFlow + "&TestNum=" + TestNum + "&Date=" + Date;
        model.addAttribute("testUrl", testUrl);
        return "res/jswjw/joinExam";
    }

    private int compareDate(String Date, String sysDate) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date1 = sdf.parse(Date);
        Date date2 = sdf.parse(sysDate);
        //获取毫秒
        long time = date1.getTime();
        long sysTime = date2.getTime();
        //小于系统时间
        if (time < sysTime) {
            return 1;
        } else {
            return -1;
        }
    }

    @RequestMapping(value = {"/lectureExam"}, method = {RequestMethod.POST})
    public String lectureExam(String userFlow, Model model) throws UnsupportedEncodingException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/joinExam";
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "请联系管理员维护考试地址!");
            return "res/jswjw/joinExam";
        }
        //是否开启考试
        String lectureTest = jswjwBiz.getCfgCode("res_lecture_test");
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(lectureTest)) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "暂未开启考试!");
            return "res/jswjw/joinExam";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        //是否开启考试
        String lectureTestNo = jswjwBiz.getCfgCode("res_lecture_test_no");
        if (StringUtil.isBlank(lectureTestNo)) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "请联系管理员先配置考试试题!");
            return "res/jswjw/joinExam";
        }
        String lectureTestNum = jswjwBiz.getCfgCode("res_lecture_test_num");
        if (StringUtil.isBlank(lectureTestNum)) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "请联系管理员先配置考试次数!");
            return "res/jswjw/joinExam";
        }
        //试卷id
        String ExamSoluID = lectureTestNo;
        //时间戳
        String Date = "0";
        //考试类型
        String UnifyExamTimeID = "3";
        //创建分数数据
        ResScore score = new ResScore();
        score = new ResScore();
        score.setDoctorFlow(userFlow);
        score.setScoreTypeName("天津讲座考试");
        score.setPaperFlow(ExamSoluID);
        int saveResult = jswjwBiz.saveScore(score, user);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
            model.addAttribute("resultId", "40408");
            model.addAttribute("resultType", "分数信息创建出错!");
            return "res/jswjw/joinExam";
        }
        testUrl = testUrl + "?Action=ChuKeMobileExam&ExamSoluID=" + ExamSoluID + "&CardID=" + URLEncoder.encode(user.getUserCode(), "utf-8") + "&ProcessFlow=1&TestNum=" + lectureTestNum + "&Date=" + Date;
        model.addAttribute("testUrl", testUrl);
        return "res/jswjw/joinExam";
    }

    @RequestMapping(value = {"/toGraduationTest"}, method = {RequestMethod.POST})
    public String toGraduationTest(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws UnsupportedEncodingException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/toGraduationTest";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/toGraduationTest";
        }
        //权限
        String cfgv = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_graduation_exam_" + userFlow);
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "无权限参加考试!");
            return "res/jswjw/toGraduationTest";
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "请联系管理员维护考试地址!");
            return "res/jswjw/toGraduationTest";
        }
        String userCode = user.getUserCode();
        //用户的医师信息
        ResDoctor doctor = doctorBiz.readDoctor(userFlow);
        if (doctor == null) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "无医师信息，无法参加考试!");
            return "res/jswjw/toGraduationTest";
        }
        if (StringUtil.isBlank(doctor.getTrainingSpeId())) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "医师无培训专业信息，无法参加考试!");
            return "res/jswjw/toGraduationTest";
        }
        //获取考试参数
        //考试结果记录流水号
        String recordFlow = PkUtil.getUUID();

        //试卷id
        String ExamSoluID = "0";
        //试卷类型
        String CardType = "0";
        //时间戳
        String Date = "0";

        if (doctor != null) {
            //专业
            String speId = doctor.getTrainingSpeId();
            ResPaper paper = paperBiz.getPaperBySpeId(speId);

            if (paper != null) {
                ExamSoluID = paper.getPaperFlow();
            }
            if ("0".equals(ExamSoluID)) {
                model.addAttribute("resultId", "40405");
                model.addAttribute("resultType", "该专业下暂无试卷信息!");
                return "res/jswjw/toGraduationTest";
            }

            TestPaper standardPaper = paperBiz.readTestPaper(ExamSoluID);
            if (standardPaper != null) {
                CardType = standardPaper.getPaperTypeId();
            }
        }//创建考核结果
        ResDoctorGraduationExam result = new ResDoctorGraduationExam();
        result.setExamFlow(recordFlow);
        result.setDoctorFlow(userFlow);
        result.setExamYear(DateUtil.getYear());
        int saveResult = examCfgBiz.saveGraduationExam(result);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
            model.addAttribute("resultId", "40405");
            model.addAttribute("resultType", "考核结果信息创建出错!");
            return "res/jswjw/toGraduationTest";
        }
        testUrl = testUrl + "?Action=ChuKeMobileExam&ExamSoluID=" + ExamSoluID + "&CardID=" + URLEncoder.encode(user.getUserCode(), "utf-8") + "&ProcessFlow=" + recordFlow + "&TestNum=" + "&Date=" + Date;
        model.addAttribute("testUrl", testUrl);
        return "res/jswjw/toGraduationTest";
    }


    @RequestMapping(value = {"/globalProgress"}, method = {RequestMethod.GET})
    public String globalProgress(String userFlow, String deptFlow, String resultFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30301");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/globalProgress";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "30302");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/globalProgress";
        }

//		boolean isChargeOrg = isChargeOrg(userFlow);

        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
//		String isChargeOrg= jswjwBiz.getCfgCode("jswjw_"+doctor.getOrgFlow()+"_P001");
//		boolean f=false;
//		//派送单位或者学校
//		if ("Graduate".equals(doctor.getDoctorTypeId())) {
//			String isJDZSGC = jswjwBiz.getCfgCode("jswjw_" + doctor.getOrgFlow() + "_P008");
//			if (StringUtil.isNotBlank(doctor.getWorkOrgId())) {
//				String isSendFlag = jswjwBiz.getCfgCode("jswjw_sendSchool_" + doctor.getWorkOrgId() + "_P007");
//				if (!(com.pinde.core.common.GlobalConstant.FLAG_N.equals(isSendFlag) && (null == isJDZSGC || com.pinde.core.common.GlobalConstant.FLAG_N.equals(isJDZSGC)))) {//如果学校未开且基地专硕未开
//					f=true;
//				}
//			}
//			if(!f) {
//				f=false;
//				String isJDGC = jswjwBiz.getCfgCode("jswjw_" + doctor.getOrgFlow() + "_P001");
//				if ((com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJDGC) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJDZSGC))) {
//					f=true;
//				}
//			}
//		}else if(StringUtil.isNotBlank(isChargeOrg)&&com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isChargeOrg)) {
//			f = true;
//		}
        String isChargeOrg = jswjwBiz.getJsResCfgAppMenu(doctor.getDoctorFlow());
        String orgFlow = "";
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            orgFlow = doctor.getSecondOrgFlow();
        } else {
            orgFlow = doctor.getOrgFlow();
        }
        String addActivity = jswjwBiz.getJsResCfgCode("jsres_" + orgFlow + "_org_stu_add_activity");
        boolean f = false;
        if (StringUtil.isNotBlank(isChargeOrg) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isChargeOrg)) {
            f = true;
        }
        List<Map<String, Object>> globalDataList = jswjwBiz.globalProgress(userFlow, deptFlow, f);
        model.addAttribute("globalDataList", globalDataList);
        model.addAttribute("addActivity", addActivity);

        return "res/jswjw/globalProgress";
    }

    @RequestMapping(value = {"/inProcessInfoList"}, method = {RequestMethod.POST})
    public String inProcessInfoList(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/inProcessInfoList";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/inProcessInfoList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, pageIndex, pageSize);
        model.addAttribute("resultList", resultList);
        model.addAttribute("dataCount", PageHelper.total);
        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();
        Map<String, ResInprocessInfo> inprocessInfoMap = new HashMap<String, ResInprocessInfo>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            if (process != null) {
                processMap.put(result.getResultFlow(), process);
                ResScore score = jswjwTeacherBiz.readScoreByProcessFlow(process.getProcessFlow());
                scoreMap.put(result.getResultFlow(), score);
            }
            ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlow(result.getSchDeptFlow());
            inprocessInfoMap.put(result.getResultFlow(), info);
        }
        String currDate = DateUtil.getCurrDate();
        model.addAttribute("currDate", currDate);
        model.addAttribute("processMap", processMap);
        model.addAttribute("inprocessInfoMap", inprocessInfoMap);
        model.addAttribute("scoreMap", scoreMap);
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        return "res/jswjw/inProcessInfoList";
    }

    @RequestMapping(value = {"/inProcessInfo"}, method = {RequestMethod.POST})
    public String inProcessInfo(String userFlow, String schDeptFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", ResultEnum.Success.getId());
        model.addAttribute("resultType", ResultEnum.Success.getName());
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3020201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/inProcessInfo";
        }
        if (StringUtil.isEmpty(schDeptFlow)) {
            model.addAttribute("resultId", "3020202");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/inProcessInfo";
        }
        SysDept dept = jswjwBiz.readSysDept(schDeptFlow);
        if (dept == null) {
            model.addAttribute("resultId", "3020202");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/inProcessInfo";
        }
        String orgFlow = dept.getOrgFlow();
        ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(schDeptFlow, orgFlow);
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
        } else {
            model.addAttribute("resultId", "3020202");
            model.addAttribute("resultType", "科室暂未添加入科教育信息");
            return "res/jswjw/inProcessInfo";
        }
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);

        return "res/jswjw/inProcessInfo";
    }

    private Map<String, Object> paressXml(String content) {
        Map<String, Object> formDataMap = null;
        if (StringUtil.isNotBlank(content)) {
            formDataMap = new HashMap<String, Object>();
            try {
                Document document = DocumentHelper.parseText(content);
                Element rootElement = document.getRootElement();
                List<Element> elements = rootElement.elements();
                for (Element element : elements) {
                    formDataMap.put(element.getName(), element.getText());
                }
            } catch (DocumentException e) {
                logger.error("", e);
            }
        }
        return formDataMap;
    }

    @RequestMapping(value = {"/subDeptList"}, method = {RequestMethod.GET})
    public String subDeptList(String userFlow, String deptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/subDeptList";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "30402");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/subDeptList";
        }

        boolean isChargeOrg = isChargeOrg(userFlow);
//		model.addAttribute("isChargeOrg", isChargeOrg);
        //学员app放开选择带教与科主任
        model.addAttribute("isChargeOrg", true);
        List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, pageIndex, pageSize);
        model.addAttribute("resultList", resultList);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("pageIndex", pageIndex);
        model.addAttribute("pageSize", pageSize);

        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        String ckxz = jswjwBiz.getJsResCfgCode("jsres_" + doctor.getOrgFlow() + "_org_ckxz");
        if (null != ckxz && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckxz)) {
            ResOrgCkxzExample example = new ResOrgCkxzExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                    .andOrgFlowEqualTo(doctor.getOrgFlow())
                    .andSessionGradeEqualTo(doctor.getSessionNumber());
            List<ResOrgCkxz> list = resOrgCkxzMapper.selectByExample(example);
            if (list.size()>0){
                ckxz = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }else{
                ckxz = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        model.addAttribute("ckxz", ckxz);

        String cksh = jswjwBiz.getJsResCfgCode("jsres_" + doctor.getOrgFlow() + "_org_cksh");

        SchRotationDept rotationDept = jswjwBiz.readSchRotationDept(deptFlow);
        model.addAttribute("rotationDept", rotationDept);
        if (true) {
//		if(isChargeOrg){
            List<ResDoctorSchProcess> processList = jswjwBiz.searchSchProcess(userFlow, deptFlow);
            Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
            for (ResDoctorSchProcess process : processList) {
                process.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
                //判断是否已经出科 借用recordStatus字段
                ResSchProcessExpress rec = expressBiz.getExpressByRecType(process.getProcessFlow(), "AfterEvaluation");
                if(null != rec){
                    if (StringUtil.isNotBlank(cksh) && cksh.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                        if (StringUtil.isNotBlank(rec.getManagerAuditUserFlow()) && StringUtil.isNotBlank(rec.getHeadAuditStatusId())) {
                            process.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }
                    } else {
                        if (StringUtil.isNotBlank(rec.getAuditStatusId()) && rec.getAuditStatusId().equals("TeacherAuditY")) {
                            process.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }
                    }
                }
                processMap.put(process.getSchResultFlow(), process);
            }
            model.addAttribute("processMap", processMap);

            List<DeptTeacherGradeInfo> gradeList = jswjwBiz.searchNewGrade(userFlow, deptFlow);
            Map<String, String> gradeMap = jswjwBiz.getNewGradeMap(gradeList);
            model.addAttribute("gradeMap", gradeMap);

        }
        return "res/jswjw/subDeptList";
    }

    @RequestMapping(value = {"/gradeDeptList"}, method = {RequestMethod.POST})
    public String gradeDeptList(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/gradeDeptList";
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (null == doctor) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不能为空");
            return "res/jswjw/gradeDeptList";
        }
        List<SchArrangeResult> resultList = jswjwBiz.getSchArrangeResult(userFlow, doctor.getOrgFlow(), pageIndex, pageSize, doctor.getRotationFlow());
        model.addAttribute("resultList", resultList);
        model.addAttribute("dataCount", PageHelper.total);

        List<DeptTeacherGradeInfo> gradeList = jswjwBiz.searchAllGrade(userFlow);
        Map<String, String> gradeMap = jswjwBiz.getNewGradeMap(gradeList);
        model.addAttribute("gradeMap", gradeMap);

        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            processMap.put(result.getResultFlow(), process);
            SchRotationDept dept = jswjwBiz.searchGroupFlowAndStandardDeptIdQuery(result.getStandardGroupFlow(), result.getStandardDeptId());
            deptMap.put(result.getResultFlow(), dept);
        }
        model.addAttribute("processMap", processMap);
        model.addAttribute("deptMap", deptMap);
        return "res/jswjw/gradeDeptList";
    }

    //学员评带教
    @RequestMapping(value = {"/gradeDeptListTwo"}, method = {RequestMethod.POST})
    public String gradeDeptListTwo(String userFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/gradeDeptList";
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (null == doctor) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不能为空");
            return "res/jswjw/gradeDeptList";
        }
        List<SchArrangeResult> resultList = jswjwBiz.getSchArrangeResult(userFlow, doctor.getOrgFlow(), pageIndex, pageSize, doctor.getRotationFlow());
        model.addAttribute("resultList", resultList);
        model.addAttribute("dataCount", PageHelper.total);

        List<DeptTeacherGradeInfo> gradeList = jswjwBiz.searchAllGradeTwo(userFlow);
        Map<String, String> gradeMap = jswjwBiz.getNewGradeMap(gradeList);
        model.addAttribute("gradeMap", gradeMap);

        Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
        Map<String, SchRotationDept> deptMap = new HashMap<String, SchRotationDept>();
        for (SchArrangeResult result : resultList) {
            ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
            processMap.put(result.getResultFlow(), process);
            SchRotationDept dept = jswjwBiz.searchGroupFlowAndStandardDeptIdQuery(result.getStandardGroupFlow(), result.getStandardDeptId());
            deptMap.put(result.getResultFlow(), dept);
        }
        model.addAttribute("processMap", processMap);
        model.addAttribute("deptMap", deptMap);
        return "res/jswjw/doctorGradeList";
    }

    //评学员
    @RequestMapping(value = "/evaluation", method = {RequestMethod.POST})
    public String evaluation(String userFlow, String roleFlag, Model model, ResDoctor doctor, String assessStatusId,
                                    String recTypeId, Integer pageIndex, Integer pageSize, String[] datas, HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/subDeptSel";
        }
//        String roleFlog = "teacher";
        String dataStr = "";
        List<String> docTypeList = new ArrayList<>();
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("dataStr", dataStr);
        model.addAttribute("roleFlag", roleFlag);
        SysUser user = jswjwBiz.readSysUser(userFlow);
        model.addAttribute("datas", datas);
        if (StringUtil.isNotBlank(recTypeId)) {
            model.addAttribute("recTypeId", recTypeId);
        }
        //权限期间是否开通
        String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
        List<ResDoctorSchProcess> processList = null;
        Map<String, Object> param = new HashMap<>();
        param.put("assessStatusId",assessStatusId);
        param.put("roleFlog",roleFlag);
        if("Teacher".equals(roleFlag)){
            param.put("teacherUserFlow", userFlow);
        }
        param.put("isOpen", isOpen);
        param.put("doctor", doctor);
        //护士多科室
        List<SysUserDept> userDeptList = jswjwBiz.getUserDept(user);
        if(CollectionUtils.isNotEmpty(userDeptList)){
            param.put("deptFlows",userDeptList.stream().map(SysUserDept::getDeptFlow).collect(Collectors.toList()));
        }else{
            param.put("deptFlow",  user.getDeptFlow());
        }
//        param.put("deptFlow", user.getDeptFlow());
        param.put("orgFlow", user.getOrgFlow());
        param.put("docTypeList", docTypeList);
        param.put("userFlow",userFlow);
        PageHelper.startPage(pageIndex, pageSize);
        processList = jswjwBiz.selectProcessByDoctorNew(param);
        model.addAttribute("dataCount", PageHelper.total);

        if (processList != null && processList.size() > 0) {
            model.addAttribute("processList", processList);
            List<String> userFlows = new ArrayList<String>();
            Map<String, SchArrangeResult> resultMapMap = new HashMap<String, SchArrangeResult>();
            Map<String, PubFile> fileMap = new HashMap<String, PubFile>();
            for (ResDoctorSchProcess rdsp : processList) {
                if (!userFlows.contains(rdsp.getUserFlow()))
                    userFlows.add(rdsp.getUserFlow());

                String resultFlow = rdsp.getSchResultFlow();
                SchArrangeResult result = jswjwBiz.readSchArrangeResult(resultFlow);
                resultMapMap.put(rdsp.getProcessFlow(), result);
                List<String> recTypeIds = new ArrayList<String>();
                if("Teacher".equals(roleFlag)){
                    recTypeIds.add("TeacherDoctorAssess");
                    recTypeIds.add("TeacherDoctorAssessTwo");
                }
                if("Nurse".equals(roleFlag)){
                    recTypeIds.add("NurseDoctorGrade");
                }
                Map<String, Object> paramMap = new HashMap();
                paramMap.put("recTypeIds", recTypeIds);
                paramMap.put("processFlow", rdsp.getProcessFlow());
                paramMap.put("currentUserFlow", user.getUserFlow());
                List<DeptTeacherGradeInfo> gradeInfoList = gradeBiz.searchResGradeByItems(paramMap);
                if (gradeInfoList != null && gradeInfoList.size() > 0) {
                    for (DeptTeacherGradeInfo tempGradeInfo : gradeInfoList) {
                        String typeId = tempGradeInfo.getRecTypeId();
                        model.addAttribute(rdsp.getProcessFlow()+typeId, tempGradeInfo);
                        Map<String, Object> gradeMap = jswjwBiz.parseGradeXmlTwo(tempGradeInfo.getRecContent());
                        model.addAttribute(typeId + rdsp.getProcessFlow(), gradeMap);
                    }
                }
            }

            model.addAttribute("resultMap", resultMapMap);
            model.addAttribute("fileMap", fileMap);

            List<SysUser> userList = jswjwBiz.searchSysUserByuserFlows(userFlows);
            if (userList != null && userList.size() > 0) {
                Map<String, SysUser> userMap = new HashMap<String, SysUser>();
                for (SysUser su : userList) {
                    userMap.put(su.getUserFlow(), su);
                }
                model.addAttribute("userMap", userMap);
            }
            List<ResDoctor> doctorList = jswjwBiz.searchDoctorByuserFlow(userFlows);
            if (doctorList != null && doctorList.size() > 0) {
                Map<String, ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
                for (ResDoctor rd : doctorList) {
                    doctorMap.put(rd.getDoctorFlow(), rd);
                }
                model.addAttribute("doctorMap", doctorMap);
            }
        }
        return "res/jswjw/evaluationList";
    }

    @RequestMapping(value = {"/subDeptSel"}, method = {RequestMethod.POST})
    public String subDeptSel(String userFlow, String searchStr, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/subDeptSel";
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        String orgFlow = "";
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            orgFlow = doctor.getSecondOrgFlow();
        } else {
            orgFlow = doctor.getOrgFlow();
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<SysDept> sysDeptList = jswjwBiz.searchSysDeptList(orgFlow, searchStr);

        model.addAttribute("sysDeptList", sysDeptList);
        model.addAttribute("dataCount", PageHelper.total);

        return "res/jswjw/subDeptSel";
    }

    @RequestMapping(value = {"/subDeptTeacherSel"}, method = {RequestMethod.POST})
    public String subDeptTeacherSel(String userFlow, String searchStr, String sysDeptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/subDeptTeacherSel";
        }
        if (StringUtil.isEmpty(sysDeptFlow)) {
            model.addAttribute("resultId", "30402");
            model.addAttribute("resultType", "轮转科室不能为空");
            return "res/jswjw/subDeptTeacherSel";
        }
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        PageHelper.startPage(pageIndex, pageSize);
        List<SysUser> teacherList = jswjwBiz.searchTeacherList(sysDeptFlow, teacherRole, searchStr);
        model.addAttribute("teacherList", teacherList);
        model.addAttribute("dataCount", PageHelper.total);

        return "res/jswjw/subDeptTeacherSel";
    }

    @RequestMapping(value = {"/subDeptHeadSel"}, method = {RequestMethod.POST})
    public String subDeptHeadSel(String userFlow, String searchStr, String sysDeptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/subDeptHeadSel";
        }
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        PageHelper.startPage(pageIndex, pageSize);
        List<SysUser> headList = jswjwBiz.searchTeacherList(sysDeptFlow, headRole, searchStr);

        model.addAttribute("headList", headList);
        model.addAttribute("dataCount", PageHelper.total);

        return "res/jswjw/subDeptHeadSel";
    }


    @RequestMapping(value = {"/addSubDept"}, method = {RequestMethod.POST})
    public String addSubDept(String userFlow, String deptFlow, String sysDeptFlow, String headFlow, String teacherFlow, String subDeptName, String startDate, String endDate, Model model, String schMonth) throws ParseException {
        System.out.println("轮转月份schMonth:" + schMonth);
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30501");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/addSubDept";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "30502");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/addSubDept";
        }
        if (StringUtil.isEmpty(startDate)) {
            model.addAttribute("resultId", "30504");
            model.addAttribute("resultType", "开始日期为空");
            return "res/jswjw/addSubDept";
        }
        if (StringUtil.isEmpty(endDate)) {
            model.addAttribute("resultId", "30505");
            model.addAttribute("resultType", "结束日期为空");
            return "res/jswjw/addSubDept";
        }

        if (startDate.compareTo(endDate) > 0) {
            model.addAttribute("resultId", "30506");
            model.addAttribute("resultType", "轮转开始时间必须早于结束时间");
            return "res/jswjw/addSubDept";
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        //检测时间是否重叠
        List<SchArrangeResult> results = jswjwBiz.checkResultDate(userFlow, startDate, endDate, null, doctor.getRotationFlow());
        if (results != null && !results.isEmpty()) {
            model.addAttribute("resultId", "30507");
            model.addAttribute("resultType", "轮转时间与其他科室重叠");
            return "res/jswjw/addSubDept";
        }

        //检查所选轮转时间内该带教老师有无剩余带教名额
        int nowTeachingDoctor = 0;
        ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andSchStartDateBetween(startDate, endDate).andSchEndDateGreaterThanOrEqualTo(endDate).andTeacherUserFlowEqualTo(teacherFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorSchProcess> list = processMapper.selectByExample(example);
        ResDoctorSchProcessExample example1 = new ResDoctorSchProcessExample();
        example1.createCriteria().andSchEndDateBetween(startDate, endDate).andSchStartDateLessThanOrEqualTo(startDate).andTeacherUserFlowEqualTo(teacherFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorSchProcess> list1 = processMapper.selectByExample(example1);
        ResDoctorSchProcessExample example2 = new ResDoctorSchProcessExample();
        example2.createCriteria().andSchStartDateLessThanOrEqualTo(startDate).andSchEndDateGreaterThanOrEqualTo(endDate).andTeacherUserFlowEqualTo(teacherFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorSchProcess> list2 = processMapper.selectByExample(example2);
        ResDoctorSchProcessExample example3 = new ResDoctorSchProcessExample();
        example3.createCriteria().andSchStartDateBetween(startDate, endDate).andSchEndDateBetween(startDate, endDate).andTeacherUserFlowEqualTo(teacherFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorSchProcess> list3 = processMapper.selectByExample(example3);
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        HashMap<String,ResDoctorSchProcess> map = new HashMap<>();
        for (ResDoctorSchProcess resDoctorSchProcess : list) {
            map.put(resDoctorSchProcess.getProcessFlow(),resDoctorSchProcess);
        }
        if (map.size() > 0) {
            list = new ArrayList<>();
            Set<String> keySet = map.keySet();
            for (String s : keySet) {
                list.add(map.get(s));
            }
        }
        nowTeachingDoctor = list.size();
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        String key = "jsres_" + sysUser.getOrgFlow() + "_student_number_limit";
        //        SysCfg sysCfg = cfgBiz.read(key);
        JsresPowerCfg sysCfg = powerCfgMapper.selectByPrimaryKey(key);
        if (sysCfg != null && StringUtil.isNotBlank(sysCfg.getCfgValue())) {
            if (nowTeachingDoctor >= Integer.parseInt(sysCfg.getCfgValue())) {
                model.addAttribute("resultId", "30508");
                model.addAttribute("resultType", "该带教老师在所选轮转时间内已有至少" + Integer.parseInt(sysCfg.getCfgValue()) + "名学员，请选择其他带教老师");
                return "res/jswjw/addSubDept";
            }
        }

        // 获取当前入科时间填写系统限制 add shengl
        if (CheckRotationTime(userFlow, deptFlow, startDate, endDate, model, "1", "", schMonth)) {
            return "res/jswjw/addSubDept";
        }

//        String orgFlow = "";

//        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
//            orgFlow = doctor.getSecondOrgFlow();
//        } else {
//            orgFlow = doctor.getOrgFlow();
//        }
//        String ckxz = jswjwBiz.getJsResCfgCode("jsres_" + orgFlow + "_org_ckxz");
//        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckxz)) {
//            List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResults(userFlow, doctor.getRotationFlow(), startDate);
//            if (resultList != null) {
//                //xianzhi
//                for (SchArrangeResult result : resultList) {
//                    ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
//
//                    ResSchProcessExpress rec = expressBiz.getExpressByRecType(process.getProcessFlow(), "AfterEvaluation");
//                    if (rec == null) {
//                        model.addAttribute("resultId", "30506");
//                        model.addAttribute("resultType", process.getSchDeptName() + "[" + process.getSchStartDate() + "~" + process.getSchEndDate() + "]带教还未审核出科考核表");
//                        return "res/jswjw/addSubDept";
//                    }
//                    if (StringUtil.isNotBlank(rec.getManagerAuditUserFlow()) && StringUtil.isBlank(rec.getHeadAuditStatusId())) {
//                        model.addAttribute("resultId", "30506");
//                        model.addAttribute("resultType", process.getSchDeptName() + "[" + process.getSchStartDate() + "~" + process.getSchEndDate() + "]主任还未审核出科考核表");
//                        return "res/jswjw/addSubDept";
//                    }
//                }
//            }
//        }


//		if(isChargeOrg(userFlow)){
        if (true) {
            if (StringUtil.isEmpty(sysDeptFlow)) {
                model.addAttribute("resultId", "30504");
                model.addAttribute("resultType", "请选择轮转科室");
                return "res/jswjw/addSubDept";
            }
//            if (StringUtil.isEmpty(headFlow)) {
//                model.addAttribute("resultId", "30504");
//                model.addAttribute("resultType", "请选择科主任");
//                return "res/jswjw/addSubDept";
//            }
//            if (StringUtil.isEmpty(teacherFlow)) {
//                model.addAttribute("resultId", "30504");
//                model.addAttribute("resultType", "请选择带教老师");
//                return "res/jswjw/addSubDept";
//            }
            jswjwBiz.addSubDept(userFlow, deptFlow, sysDeptFlow, headFlow, teacherFlow, startDate, endDate);
        } else {
            if (StringUtil.isEmpty(subDeptName)) {
                model.addAttribute("resultId", "30503");
                model.addAttribute("resultType", "科室名称为空");
                return "res/jswjw/addSubDept";
            }
            jswjwBiz.addSubDept(userFlow, deptFlow, subDeptName, startDate, endDate);
        }
        return "res/jswjw/addSubDept";
    }

    /**
     * 填写轮转计划时校验时间是否超过轮转计划规定时间
     *
     * @param userFlow
     * @param deptFlow
     * @param startDate
     * @param endDate
     * @param model
     * @return
     * @throws ParseException
     */
    private boolean CheckRotationTime(String userFlow, String deptFlow, String startDate, String endDate, Model model, String type, String subDeptFlow, String schMonth) throws ParseException {
        String isProcess = jswjwBiz.getCfgCode("jsres_is_process");
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(isProcess)) {
            return false;
        }
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            logger.info("系統設置：科室轮转时间不允許超过规定的月份,校验用户填写时间 Start");
            if (StringUtil.isNotBlank(schMonth)) {
                // 学员填写时间间隔自然月
                int month = DateTimeUtil.getMonth(startDate, endDate);
                // 填写时间间隔天数
                int distanceDay = DateTimeUtil.getDistanceDay(startDate, endDate);
                List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, null, null);
                // 第一次填写，月份按自然月计算
                if (resultList == null || resultList.size() == 0) {
                    // 用户填写时间超过规定范围
                    if ("0.5".equals(schMonth)) {
                        if (distanceDay > 15) {
                            model.addAttribute("resultId", "30508");
                            model.addAttribute("resultType", "当前填写时间超过规定的时间范围");
                            return true;
                        }
                    } else if (month > Double.parseDouble(schMonth)) {
                        model.addAttribute("resultId", "30508");
                        model.addAttribute("resultType", "当前填写时间超过规定的时间范围");
                        return true;
                    }
                }
                // 该轮转已有超过一条记录，月份限制按31天计算
                else {
                    int sumTime = 0;
                    for (SchArrangeResult schArrangeResult : resultList) {
                        String resultFlow = schArrangeResult.getResultFlow();
                        // 已有记录，修改当前唯一一条数据，月份按自然月计算
                        if (resultList.size() == 1 && subDeptFlow.equals(resultFlow)) {
                            if ("0.5".equals(schMonth)) {
                                if (distanceDay > 15) {
                                    model.addAttribute("resultId", "30508");
                                    model.addAttribute("resultType", "当前填写时间超过规定的时间范围");
                                    return true;
                                }
                            } else if (month > Double.parseDouble(schMonth)) {
                                model.addAttribute("resultId", "30508");
                                model.addAttribute("resultType", "当前填写时间超过规定的时间范围");
                                return true;
                            } else if (month == Double.parseDouble(schMonth)) {
                                return false;
                            }
                        }
                        String schStartDate = schArrangeResult.getSchStartDate();
                        String schEndDate = schArrangeResult.getSchEndDate();
                        if (StringUtil.isNotBlank(schStartDate) && StringUtil.isNotBlank(schEndDate)) {
                            // 新增或者编辑（编辑排除当前操作记录的起始时间）
                            if (("1".equals(type)) || ("2".equals(type) && !subDeptFlow.equals(resultFlow))) {
                                sumTime += DateTimeUtil.getDistanceDay(schStartDate, schEndDate);
                            }
                        }
                    }
                    // 用户填写时间范围间隔天数 + 已有轮转记录时间总和超过规定的时间范围
                    if ("0.5".equals(schMonth)) {
                        if (distanceDay > 15) {
                            model.addAttribute("resultId", "30508");
                            model.addAttribute("resultType", "当前填写时间超过规定的时间范围");
                            return true;
                        }
                    } else if ((sumTime + distanceDay) > Double.parseDouble(schMonth) * 31) {
                        model.addAttribute("resultId", "30508");
                        model.addAttribute("resultType", "当前填写时间超过规定的时间范围");
                        return true;
                    }
                }

            } else {
                logger.info("CheckRotationTime 未查到轮转月份信息！");
            }

        }
        return false;
    }

    @RequestMapping(value = {"/modSubDept"}, method = {RequestMethod.POST})
    public String modSubDept(String userFlow, String deptFlow, String subDeptFlow, String subDeptName, String sysDeptFlow, String headFlow, String teacherFlow, String startDate,
                             String endDate, Model model, String schMonth) throws ParseException {
        System.out.println("轮转月份schMonth:" + schMonth);
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/modSubDept";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "30602");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/modSubDept";
        }
        if (StringUtil.isEmpty(startDate)) {
            model.addAttribute("resultId", "30604");
            model.addAttribute("resultType", "开始日期为空");
            return "res/jswjw/modSubDept";
        }
        if (StringUtil.isEmpty(endDate)) {
            model.addAttribute("resultId", "30605");
            model.addAttribute("resultType", "结束日期为空");
            return "res/jswjw/modSubDept";
        }

        if (startDate.compareTo(endDate) > 0) {
            model.addAttribute("resultId", "30606");
            model.addAttribute("resultType", "轮转开始时间必须早于结束时间");
            return "res/jswjw/modSubDept";
        }

        //检测时间是否重叠
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);

        System.err.println("subDeptFlow=" + subDeptFlow);
        List<SchArrangeResult> results = jswjwBiz.checkResultDate(userFlow, startDate, endDate, subDeptFlow, doctor.getRotationFlow());
        if (results != null && !results.isEmpty()) {
            model.addAttribute("resultId", "30607");
            model.addAttribute("resultType", "轮转时间与其他科室重叠");
            return "res/jswjw/modSubDept";
        }

        //检查所选轮转时间内该带教老师有无剩余带教名额
        int nowTeachingDoctor = 0;
        ResDoctorSchProcessExample example = new ResDoctorSchProcessExample();
        example.createCriteria().andSchStartDateBetween(startDate, endDate).andSchEndDateGreaterThanOrEqualTo(endDate).andTeacherUserFlowEqualTo(teacherFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorSchProcess> list = processMapper.selectByExample(example);
        ResDoctorSchProcessExample example1 = new ResDoctorSchProcessExample();
        example1.createCriteria().andSchEndDateBetween(startDate, endDate).andSchStartDateLessThanOrEqualTo(startDate).andTeacherUserFlowEqualTo(teacherFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorSchProcess> list1 = processMapper.selectByExample(example1);
        ResDoctorSchProcessExample example2 = new ResDoctorSchProcessExample();
        example2.createCriteria().andSchStartDateLessThanOrEqualTo(startDate).andSchEndDateGreaterThanOrEqualTo(endDate).andTeacherUserFlowEqualTo(teacherFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorSchProcess> list2 = processMapper.selectByExample(example2);
        ResDoctorSchProcessExample example3 = new ResDoctorSchProcessExample();
        example3.createCriteria().andSchStartDateBetween(startDate, endDate).andSchEndDateBetween(startDate, endDate).andTeacherUserFlowEqualTo(teacherFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorSchProcess> list3 = processMapper.selectByExample(example3);
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        HashMap<String,ResDoctorSchProcess> map = new HashMap<>();
        for (ResDoctorSchProcess resDoctorSchProcess : list) {
            map.put(resDoctorSchProcess.getProcessFlow(),resDoctorSchProcess);
        }
        if (map.size() > 0) {
            list = new ArrayList<>();
            Set<String> keySet = map.keySet();
            for (String s : keySet) {
                list.add(map.get(s));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserFlow().equals(userFlow)) {
                list.remove(i);
                i--;
            }
        }
        nowTeachingDoctor = list.size();
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        String key = "jsres_" + sysUser.getOrgFlow() + "_student_number_limit";
//        SysCfg sysCfg = cfgBiz.read(key);
        JsresPowerCfg sysCfg = powerCfgMapper.selectByPrimaryKey(key);
        if (sysCfg != null && StringUtil.isNotBlank(sysCfg.getCfgValue())) {
            if (nowTeachingDoctor >= Integer.parseInt(sysCfg.getCfgValue())) {
                model.addAttribute("resultId", "30508");
                model.addAttribute("resultType", "该带教老师在所选轮转时间内已有至少" + Integer.parseInt(sysCfg.getCfgValue()) + "名学员，请选择其他带教老师");
                return "res/jswjw/addSubDept";
            }
        }

        String cksh = jswjwBiz.getJsResCfgCode("jsres_" + doctor.getOrgFlow() + "_org_cksh");
        if (StringUtil.isNotBlank(subDeptFlow)) {
            SchArrangeResult schArrangeResult = jswjwBiz.readSchArrangeResult(subDeptFlow);
            ResDoctorSchProcess process = jswjwBiz.readSchProcessByResultFlow(schArrangeResult.getResultFlow());
            ResSchProcessExpress rec = null;
            if (process != null) {
                rec = expressBiz.getExpressByRecType(process.getProcessFlow(), "AfterEvaluation");
            }

            if (rec != null) {
                if (StringUtil.isNotBlank(cksh) && cksh.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                    if (StringUtil.isNotBlank(rec.getManagerAuditUserFlow()) && StringUtil.isNotBlank(rec.getHeadAuditStatusId()) && rec.getHeadAuditStatusId().equals(RecStatusEnum.HeadAuditY.getId())) {
                        if (!process.getTeacherUserFlow().equals(teacherFlow)) {
                            model.addAttribute("resultId", "30509");
                            model.addAttribute("resultType", "您已在该轮转科室完成出科，不可再修改您的带教老师！");
                            return "res/jswjw/addSubDept";
                        }
                    }
                } else {
                    if (StringUtil.isNotBlank(rec.getAuditStatusId()) && rec.getAuditStatusId().equals("TeacherAuditY")) {
                        if (!process.getTeacherUserFlow().equals(teacherFlow)) {
                            model.addAttribute("resultId", "30509");
                            model.addAttribute("resultType", "您已在该轮转科室完成出科，不可再修改您的带教老师！");
                            return "res/jswjw/addSubDept";
                        }
                    }
                }
            }
        }

        // 获取当前入科时间填写系统限制 add shengl
        if (CheckRotationTime(userFlow, deptFlow, startDate, endDate, model, "2", subDeptFlow, schMonth)) {
            return "res/jswjw/addSubDept";
        }

//		if(isChargeOrg(userFlow)){
        if (true) {
            if (StringUtil.isEmpty(sysDeptFlow)) {
                model.addAttribute("resultId", "30504");
                model.addAttribute("resultType", "请选择轮转科室");
                return "res/jswjw/modSubDept";
            }
//            if (StringUtil.isEmpty(headFlow)) {
//                model.addAttribute("resultId", "30504");
//                model.addAttribute("resultType", "请选择科主任");
//                return "res/jswjw/modSubDept";
//            }
//            if (StringUtil.isEmpty(teacherFlow)) {
//                model.addAttribute("resultId", "30504");
//                model.addAttribute("resultType", "请选择带教老师");
//                return "res/jswjw/modSubDept";
//            }
            jswjwBiz.modSubDept(userFlow, deptFlow, subDeptFlow, sysDeptFlow, headFlow, teacherFlow, startDate, endDate);
        } else {
            if (StringUtil.isEmpty(subDeptName)) {
                model.addAttribute("resultId", "30603");
                model.addAttribute("resultType", "科室名称为空");
                return "res/jswjw/modSubDept";
            }
            jswjwBiz.modSubDept(userFlow, deptFlow, subDeptFlow, subDeptName, startDate, endDate);
        }
        return "res/jswjw/modSubDept";
    }

    @RequestMapping(value = {"/deleteSubDept"}, method = {RequestMethod.GET})
    public String deleteSubDept(String userFlow, String deptFlow, String subDeptFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30701");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/deleteSubDept";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "30702");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/deleteSubDept";
        }
        if (StringUtil.isEmpty(subDeptFlow)) {
            model.addAttribute("resultId", "30703");
            model.addAttribute("resultType", "轮转科室流水号为空");
            return "res/jswjw/deleteSubDept";
        }


        int count1 = jswjwBiz.searchCount(subDeptFlow,userFlow);
        if (count1 > 0) {
            model.addAttribute("resultId", "30703");
            model.addAttribute("resultType", "该轮转科室无法删除!");
            return "res/jswjw/deleteSubDept";
        }

        int count = jswjwBiz.searchRecCount(subDeptFlow);
        if (count > 0) {
            model.addAttribute("resultId", "30703");
            model.addAttribute("resultType", "该轮转科室已有数据保存，无法删除!");
            return "res/jswjw/deleteSubDept";
        }

        jswjwBiz.deleteSubDept(userFlow, deptFlow, subDeptFlow);
        return "res/jswjw/deleteSubDept";
    }


    @RequestMapping(value = {"/categoryProgress"}, method = {RequestMethod.GET})
    public String categoryProgress(String userFlow, String deptFlow, String dataType, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/categoryProgress";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "30802");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/categoryProgress";
        }
        if (StringUtil.isEmpty(dataType)) {
            model.addAttribute("resultId", "30803");
            model.addAttribute("resultType", "dataType为空或不能识别");
            return "res/jswjw/categoryProgress";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30804");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/categoryProgress";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30805");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/categoryProgress";
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        List<Map<String, Object>> catagoryDataList = jswjwBiz.categoryProgress(dataType, userFlow, deptFlow, doctor.getRotationFlow(), pageIndex, pageSize);
        model.addAttribute("catagoryDataList", catagoryDataList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/jswjw/categoryProgress";
    }

    @RequestMapping(value = {"/dataList"}, method = {RequestMethod.GET})
    public String dataList(String userFlow, String deptFlow, String dataType, String cataFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30901");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/dataList";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "30902");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/dataList";
        }
        if (StringUtil.isEmpty(dataType)) {
            model.addAttribute("resultId", "30903");
            model.addAttribute("resultType", "dataType为空或不能识别");
            return "res/jswjw/dataList";
        }
        if (StringUtil.isEmpty(cataFlow)) {
            model.addAttribute("resultId", "30904");
            model.addAttribute("resultType", "cataFlow为空或不能识别");
            return "res/jswjw/dataList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/dataList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/dataList";
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> dataList = jswjwBiz.dataList(dataType, userFlow, deptFlow, cataFlow);
        model.addAttribute("dataList", dataList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/jswjw/dataList";
    }

    @RequestMapping(value = {"/inputList"}, method = {RequestMethod.GET})
    public String inputList(String userFlow, String deptFlow, String dataType, String cataFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31001");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/inputList";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31002");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/inputList";
        }
        if (StringUtil.isEmpty(dataType)) {
            model.addAttribute("resultId", "31003");
            model.addAttribute("resultType", "dataType为空或不能识别");
            return "res/jswjw/inputList";
        }
//		if(StringUtil.isEmpty(cataFlow)){
//			model.addAttribute("resultId", "31004");
//			model.addAttribute("resultType", "数据分类标识为空或不能识别");
//			return "res/jswjw/addData";
//		}
//
//		if(isChargeOrg(userFlow)){
        if (true) {
            List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, null, null);
            model.addAttribute("resultList", resultList);
        }
        _inputList(userFlow, deptFlow, dataType, cataFlow, model);
        model.addAttribute("isChargeOrg", com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jswjwBiz.getJsResCfgAppMenu(userFlow)));
        model.addAttribute("isOther", com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));
        return "res/jswjw/inputList";
    }

    private void _inputList(String userFlow, String deptFlow, String dataType, String cataFlow, Model model) {
        switch (dataType) {
            case "mr":
                break;
            case "disease":
                List<Map<String, Object>> diseaseDiagNameList = jswjwBiz.commReqOptionNameList(userFlow, deptFlow, dataType, cataFlow);
                model.addAttribute("diseaseDiagNameList", diseaseDiagNameList);
                break;
            case "skill":
                List<Map<String, Object>> skillOperNameList = jswjwBiz.commReqOptionNameList(userFlow, deptFlow, dataType, cataFlow);
                model.addAttribute("skillOperNameList", skillOperNameList);
                break;
            case "operation":
                List<Map<String, Object>> operationOperNameList = jswjwBiz.commReqOptionNameList(userFlow, deptFlow, dataType, cataFlow);
                model.addAttribute("operationOperNameList", operationOperNameList);
                break;
            case "languageTeaching":
                List<Map<String, Object>> languageTeachingNameList = jswjwBiz.commReqOptionNameList(userFlow, deptFlow, dataType, cataFlow);
                model.addAttribute("languageTeachingNameList", languageTeachingNameList);
                break;
            case "activity":
                break;
            case "summary":
                break;
            default:
                break;
        }
    }

    @RequestMapping(value = {"/addData"}, method = {RequestMethod.POST})
    public String addData(String userFlow, String deptFlow, String cataFlow, String dataType, HttpServletRequest request, HttpServletResponse response, Model model, String json) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/addData";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31102");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/addData";
        }
        if (StringUtil.isEmpty(dataType)) {
            model.addAttribute("resultId", "31103");
            model.addAttribute("resultType", "dataType为空或不能识别");
            return "res/jswjw/addData";
        }
        if (StringUtil.isEmpty(cataFlow)) {
            model.addAttribute("resultId", "31104");
            model.addAttribute("resultType", "数据分类标识为空或不能识别");
            return "res/jswjw/addData";
        }

        //判断所选科室是否有科主任带教
//		boolean isChargeOrg = isChargeOrg(userFlow);
        boolean isChargeOrg = true;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        _putAll(paramMap, request);
        paramMap.put("userFlow", userFlow);
        if (studentFillInDataCheck(model, paramMap)) {
            return "res/jswjw/addData";
        }
        if(null != paramMap.get("resultFlow")){
            ResDoctorSchProcess process = jswjwBiz.readSchProcessByResultFlow(paramMap.get("resultFlow").toString());
            if(null != process){
                if(StringUtil.isEmpty(process.getTeacherUserFlow()) || StringUtil.isEmpty(process.getHeadUserFlow())){
                    model.addAttribute("resultId", "31104");
                    model.addAttribute("resultType", "请先维护当前科室的带教老师和科主任！");
                    return "res/jswjw/addData";
                }
            }
            ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
            List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, null, null);
            String limitMonth = "";
            //禅道3300当设置为是时,不论是否出科，学员都可以添加多个轮转科室,但是只有当前科室出科后,才可以添加下个轮转科室的轮转数据；
            String ckxz = jswjwBiz.getJsResCfgCode("jsres_" + doctor.getOrgFlow() + "_org_ckxz");
            if (null != ckxz && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckxz)) {
                ResOrgCkxzExample example = new ResOrgCkxzExample();
                example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                        .andOrgFlowEqualTo(doctor.getOrgFlow())
                        .andSessionGradeEqualTo(doctor.getSessionNumber());
                List<ResOrgCkxz> list = resOrgCkxzMapper.selectByExample(example);
                if (list.size()>0){
                    ckxz = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    limitMonth = list.get(0).getSessionYear();
                }else{
                    ckxz = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ckxz)) {
                List<SchArrangeResult> resultListNew = jswjwBiz.searchSchArrangeResults(userFlow, doctor.getRotationFlow(), process.getSchStartDate());
                if (resultListNew != null) {
                    for (SchArrangeResult result : resultListNew) {
                        ResDoctorSchProcess process1 = jswjwBiz.getProcessByResultFlow(result.getResultFlow());
                        if(null != process1 && StringUtil.isNotBlank(limitMonth) && process1.getSchEndDate().substring(0, 7).compareTo(limitMonth) >= 0){
                            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(process1.getTemporaryOut()) || (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(process1.getTemporaryOut()) && !"Passed".equals(process1.getTemporaryAuditStatusId()))) {
                                ResSchProcessExpress rec = expressBiz.getExpressByRecType(process1.getProcessFlow(), "AfterEvaluation");
                                if (rec == null) {
                                    model.addAttribute("resultId", "30506");
                                    model.addAttribute("resultType", "当前有未出科科室,无法填写当前科室数据");
                                    return "res/jswjw/addData";
                                }else{
                                    if (StringUtil.isNotBlank(rec.getManagerAuditUserFlow()) && StringUtil.isBlank(rec.getHeadAuditStatusId())) {
                                        model.addAttribute("resultId", "30506");
                                        model.addAttribute("resultType", "当前有未出科科室,无法填写当前科室数据");
                                        return "res/jswjw/addData";
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        jswjwBiz.addData(dataType, userFlow, deptFlow, cataFlow, paramMap, isChargeOrg, json);
        return "res/jswjw/addData";
    }

    /**
     * 学员补填数据 增加超时校验 shengl
     *
     * @param model
     * @param paramMap
     * @return
     */
    private boolean studentFillInDataCheck(Model model, Map<String, Object> paramMap) {
        String inDataType = jswjwBiz.getCfgCode("student_fill_in_data");
        String userFlow = (String) paramMap.get("userFlow");
        SysUser user = jswjwBiz.readSysUser(userFlow);
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        String inDataValueNew = jswjwBiz.getCfgCode("student_fill_in_data_time_" + user.getOrgFlow());
        String doctorTypeValue = jswjwBiz.getCfgCode("student_doctorType_" + user.getOrgFlow());
        List<String> result = new ArrayList<>();
        if (StringUtil.isNotBlank(doctorTypeValue)) {
            result.addAll(Arrays.asList(doctorTypeValue.split(",")));
        }
        String resultFlow = (String) paramMap.get("resultFlow");
        if (StringUtil.isNotBlank(inDataType) && StringUtil.isNotBlank(resultFlow)) {
            SchArrangeResult schArrangeResult = jswjwTeacherBiz.readSchArrangeResult(resultFlow);
            if (schArrangeResult != null) {
                int num = 0;
                String schEndDate = schArrangeResult.getSchEndDate();
                if (StringUtil.isNotBlank(schEndDate)) {
                    if (StringUtil.isNotBlank(inDataValueNew)) {
                        if (StringUtil.isNotBlank(doctorTypeValue) && result.contains(doctor.getDoctorTypeId())) {
                            if (inDataValueNew.compareTo(schEndDate) > 0) {
                                model.addAttribute("resultId", "31109");
                                model.addAttribute("resultType", "已超出补填时间，不允许补填数据，请与基地管理员联系！");
                                return true;
                            }
                        }
                    }
                    try {
                        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(inDataType)) {
                            num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), schEndDate);
                        } else {
                            String inDataValue = jswjwBiz.getCfgCode("student_fill_in_data_value");
                            if (StringUtil.isNotBlank(inDataValue)) {
                                Pattern pattern = Pattern.compile("[0-9]*");
                                Matcher isNum = pattern.matcher(inDataValue);
                                if (isNum.matches()) {
                                    int SettingTime = Integer.parseInt(inDataValue);
                                    if (SettingTime > 0) {
                                        String afterTime = "";
                                        String timeType = jswjwBiz.getCfgCode("student_fill_in_data_type");
                                        if (com.pinde.core.common.GlobalConstant.PARAM_FLAG_ONE.equals(timeType)) {
                                            afterTime = DateTimeUtil.getAfterDate(schEndDate, SettingTime, "day");
                                        } else {
                                            afterTime = DateTimeUtil.getAfterDate(schEndDate, SettingTime, "month");
                                        }
                                        if (StringUtil.isNotBlank(afterTime)) {
                                            num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), afterTime);
                                        }
                                    }
                                }
                            }
                        }
                        if (num == 1) {
                            model.addAttribute("resultId", "31109");
                            model.addAttribute("resultType", "已超过出科时间限制,无法补填数据！");
                            return true;
                        }
                    } catch (Exception e) {
                        logger.error("", e);
                    }
                }

            }
        }
        return false;
    }

    @RequestMapping(value = {"/viewData"}, method = {RequestMethod.GET})
    public String viewData(String userFlow, String deptFlow, String dataType, String cataFlow, String dataFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/viewData";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31202");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/viewData";
        }
        if (StringUtil.isEmpty(dataType)) {
            model.addAttribute("resultId", "31203");
            model.addAttribute("resultType", "dataType为空或不能识别");
            return "res/jswjw/viewData";
        }
        if (StringUtil.isEmpty(dataFlow)) {
            model.addAttribute("resultId", "31204");
            model.addAttribute("resultType", "数据唯一标识为空");
            return "res/jswjw/viewData";
        }

//		if(isChargeOrg(userFlow)){
        if (true) {
            List<SchArrangeResult> resultList = jswjwBiz.searchSchArrangeResult(userFlow, deptFlow, null, null);
            model.addAttribute("resultList", resultList);
        }

        _inputList(userFlow, deptFlow, dataType, cataFlow, model);
        Map<String, Object> resultData = jswjwBiz.viewData(dataType, userFlow, deptFlow, dataFlow);
        model.addAttribute("resultData", resultData);

        model.addAttribute("isChargeOrg", com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jswjwBiz.getJsResCfgAppMenu(userFlow)));
        model.addAttribute("isOther", com.pinde.core.common.GlobalConstant.RES_REQ_OTHER_ITEM_ID.equals(cataFlow));

        return "res/jswjw/viewData";
    }

    @RequestMapping(value = {"/modData"}, method = {RequestMethod.POST})
    public String modData(String userFlow, String deptFlow, String dataType, String cataFlow, String dataFlow, HttpServletRequest request, HttpServletResponse response, Model model, String json) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31301");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/modData";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31302");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/modData";
        }
        if (StringUtil.isEmpty(dataType)) {
            model.addAttribute("resultId", "31303");
            model.addAttribute("resultType", "dataType为空或不能识别");
            return "res/jswjw/modData";
        }
        if (StringUtil.isEmpty(dataFlow)) {
            model.addAttribute("resultId", "31304");
            model.addAttribute("resultType", "数据唯一标识为空");
            return "res/jswjw/modData";
        }

//		boolean isChargeOrg = isChargeOrg(userFlow);
        boolean isChargeOrg = true;

        Map<String, Object> paramMap = new HashMap<String, Object>();
        _putAll(paramMap, request);
        paramMap.put("userFlow", userFlow);
        if (studentFillInDataCheck(model, paramMap)) {
            return "res/jswjw/modData";
        }
        jswjwBiz.modData(dataType, userFlow, deptFlow, dataFlow, cataFlow, paramMap, isChargeOrg, json);
        return "res/jswjw/modData";
    }

    private void _putAll(Map<String, Object> paramMap, HttpServletRequest request) {
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
//			System.err.println("paramName="+paramName);
            String paramValue = request.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }
    }

    @RequestMapping(value = {"/delData"}, method = {RequestMethod.GET})
    public String delData(String userFlow, String deptFlow, String dataType, String dataFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/delData";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31402");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/delData";
        }
        if (StringUtil.isEmpty(dataType)) {
            model.addAttribute("resultId", "31403");
            model.addAttribute("resultType", "dataType为空或不能识别");
            return "res/jswjw/delData";
        }
        if (StringUtil.isEmpty(dataFlow)) {
            model.addAttribute("resultId", "31404");
            model.addAttribute("resultType", "数据唯一标识为空");
            return "res/jswjw/delData";
        }
//        ResRec rec = jswjwBiz.readResRecByDataType(dataFlow, dataType);
        ResRec rec = jswjwBiz.readResRec(dataFlow);
        if (StringUtil.isNotBlank(rec.getAuditStatusId())) {
            model.addAttribute("resultId", "31404");
            model.addAttribute("resultType", "数据已审核,无法删除");
            return "res/jswjw/delData";
        }
        jswjwBiz.delData(dataType, userFlow, deptFlow, dataFlow);
        return "res/jswjw/delData";
    }

    @RequestMapping(value = {"/viewImage"}, method = {RequestMethod.GET})
    public String viewImage(String userFlow, String deptFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31501");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/viewImage";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31502");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/viewImage";
        }
        List<Map<String, String>> dataList = jswjwBiz.viewImage(userFlow, deptFlow);
        model.addAttribute("dataList", dataList);
        return "res/jswjw/viewImage";
    }

    @RequestMapping(value = {"/addImage"}, method = {RequestMethod.POST})
    public String addImage(ImageFileForm form, HttpServletRequest request, HttpServletResponse response, Model model) {
//		System.err.println(form);
//		System.err.println(form.getUploadFileData());
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(form.getUserFlow())) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/viewImage";
        }
        if (StringUtil.isEmpty(form.getDeptFlow())) {
            model.addAttribute("resultId", "31602");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/viewImage";
        }
        if (StringUtil.isEmpty(form.getFileName())) {
            model.addAttribute("resultId", "31603");
            model.addAttribute("resultType", "文件名为空");
            return "res/jswjw/viewImage";
        }
        if (form.getUploadFile() == null && StringUtil.isBlank(form.getUploadFileData())) {
            model.addAttribute("resultId", "31703");
            model.addAttribute("resultType", "上传文件不能为空");
            return "res/jswjw/viewImage";
        }
        jswjwBiz.addImage(form);
        return "res/jswjw/addImage";
    }

    @RequestMapping(value = {"/addCaseImage"}, method = {RequestMethod.POST})
    public String addCaseImage(ImageFileForm form, HttpServletRequest request, HttpServletResponse response, Model model) {
//		System.err.println(form);
//		System.err.println(form.getUploadFileData());
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(form.getUserFlow())) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/addCaseImage";
        }
        if (StringUtil.isEmpty(form.getFileName())) {
            model.addAttribute("resultId", "31603");
            model.addAttribute("resultType", "文件名为空");
            return "res/jswjw/addCaseImage";
        }
        if (form.getUploadFile() == null && StringUtil.isBlank(form.getUploadFileData())) {
            model.addAttribute("resultId", "31703");
            model.addAttribute("resultType", "上传文件不能为空");
            return "res/jswjw/addCaseImage";
        }
        Map<String, String> imgUrlMap = jswjwBiz.addCaseImage(form);
        if (imgUrlMap == null) {
            model.addAttribute("resultId", "31703");
            model.addAttribute("resultType", "上传文件失败");
            return "res/jswjw/addCaseImage";
        }
        model.addAttribute("imgUrlMap", imgUrlMap);
        return "res/jswjw/addCaseImage";
    }

    @RequestMapping(value = {"/deleteImage"}, method = {RequestMethod.GET})
    public String deleteImage(String userFlow, String deptFlow, String imageFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31701");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/viewImage";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31702");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/jswjw/viewImage";
        }
        if (StringUtil.isEmpty(imageFlow)) {
            model.addAttribute("resultId", "31702");
            model.addAttribute("resultType", "图片标识符为空");
            return "res/jswjw/viewImage";
        }
        jswjwBiz.deleteImage(userFlow, deptFlow, imageFlow);
        return "res/jswjw/deleteImage";
    }

    @RequestMapping(value = {"/userCenter"}, method = {RequestMethod.GET})
    public String userCenter(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/userCenter";
        }
        SysUser userinfo = jswjwBiz.getUserBaseInfo(userFlow);
        model.addAttribute("userinfo", userinfo);

        Map<String, Object> doctorInfoMap = jswjwBiz.getDoctorExtInfo(userFlow);
        if (doctorInfoMap != null) {
            model.addAttribute("doctorInfoMap", doctorInfoMap);
        } else {
            model.addAttribute("resultId", "31802");
            model.addAttribute("resultType", "登录失败,学员数据出错!");
            return "res/jswjw/userCenter";
        }
       /* String doctorExam = jswjwBiz.getJsResCfgCode("jsres_doctor_exam_" + userFlow);
        boolean isCkk = com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equalsIgnoreCase(doctorExam);
//        boolean isCkk = jswjwBiz.getCkkPower(userFlow);
        model.addAttribute("isCkk", isCkk);
        boolean isPxsc = jswjwBiz.getPxscPower(userFlow);
        model.addAttribute("isPxsc", isPxsc);//培训手册*/
//        SysUser user = jswjwBiz.readSysUser(userFlow);
//        boolean monthFirst = jswjwBiz.saveLoginLog(userinfo);
        boolean monthFirst = false;
        model.addAttribute("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
        if (monthFirst) {
            model.addAttribute("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            String loginLimit = jswjwBiz.getCfgCode("student_timeout_login_limit");
            String msgMonth = "1";
            if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(loginLimit) && StringUtil.isNotBlank(loginLimit)) {
                msgMonth = loginLimit;
            }
            model.addAttribute("tipContent", "学员需每" + msgMonth + "个月至少填写一次培训数据，否则将无法继续使用APP。");
        }

        //消息提醒
        int noticesCount = jswjwStudentBiz.countResErrorNotices(userFlow, NoticeStatusEnum.NoRead.getId());
        model.addAttribute("noticeCount", noticesCount);
//        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        ResDoctor doctor = (ResDoctor) doctorInfoMap.get("doctor");
        String trainingYears = doctor.getTrainingYears();
        //提醒所有学员（除2020级学员，2021级减免学员，2022级减免2年学员）需要在 2023 年7月1日前设置完最少一年的轮转记录,否则将无法填写轮转数据,添加轮转记录
        boolean flag=true;
        int years=3;
        //查看学员的减免年份
        if (null == trainingYears || StringUtil.isEmpty(trainingYears)){
            years=3;
        }else if (trainingYears.equals("ThreeYear")){
            years=3;
        } else if (trainingYears.equals("TwoYear")){
            years=2;
        } else if (trainingYears.equals("OneYear")){
            years=1;
        }
        if (StringUtil.isEmpty(doctor.getSessionNumber())){
            flag=false;
        }else if (Integer.parseInt(doctor.getSessionNumber())<=2020){
            flag=false;
        }else if (doctor.getSessionNumber().equals("2020")){
            flag=false;
        }else if (doctor.getSessionNumber().equals("2021") && years >=1){
            flag=false;
        }else if (doctor.getSessionNumber().equals("2022") && years==2){
            flag=false;
        }else if (Integer.parseInt(doctor.getSessionNumber())<=2021 && doctor.getTrainingTypeId().equals("AssiGeneral")){
            flag=false; //21年和21年之前的助理全科都不提示
        }
        String nowDate = DateUtil.getCurrDate();
        if (nowDate.compareTo("2023-07-01")<0 && flag==true){
            //根据基地参数配置（轮转计划排版设置）提示不同信息
            //设置为不开启时，学员APP端不弹出任何提示
            List<String> codes = new ArrayList<>();
            String code1 = "jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_N";
            String code2 = "jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_org";
            String code3 = "jsres_"+doctor.getOrgFlow()+"_org_process_scheduling_user";
            codes.add(code1);
            codes.add(code2);
            codes.add(code3);
            List<JsresPowerCfg> cfgs = powerCfgExtMapper.searchPowerCfg(codes);
            // 组装成map
            Map<String, String> codeMap = cfgs.stream().collect(Collectors.toMap(JsresPowerCfg::getCfgCode, JsresPowerCfg::getCfgValue));
            if (codeMap != null) {
                boolean code2Flag = com.pinde.core.common.GlobalConstant.FLAG_Y.equals(codeMap.get(code2));
                boolean code3Flag = com.pinde.core.common.GlobalConstant.FLAG_Y.equals(codeMap.get(code3));
                //设置为基地配置时
                if (code2Flag) {
                    model.addAttribute("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                    model.addAttribute("tipContent", "您好，请联系基地管理员提前导入最少一年的科室轮转计划，如未导入轮转记录，后续将无法填写轮转数据；");
                }
                //设置为学员自行配置时
                if (code3Flag) {
                    model.addAttribute("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                    model.addAttribute("tipContent", "您好！请预先填写完未来最少一年的科室轮转记录，如未添加轮转记录，将无法填写轮转数据");
                }
                //设置为基地配置和学员自行配置都勾选时
                if (code2Flag && code3Flag) {
                    model.addAttribute("tipFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                    model.addAttribute("tipContent", "您好！请预先填写完未来最少一年的科室轮转记录，如未添加轮转记录，将无法填写轮转数据");
                }
            }
        }

        String orgFlow = "";

        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            orgFlow = doctor.getSecondOrgFlow();
        } else {
            orgFlow = doctor.getOrgFlow();
        }
        String key = "jsres_hospital_ndkhsz_" + orgFlow;
        String isNdks = jswjwBiz.getJsResCfgCode(key);
        model.addAttribute("isNdks", isNdks);
        // 查询医师权限
        jswjwBiz.getDoctorAuthorityInfo(model, userFlow, orgFlow);
        if (doctor != null && com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(doctor.getTrainingTypeId())) {
            model.addAttribute("isPxsc", true);
        }
        return "res/jswjw/userCenter";
    }

    /**
     *
     */
    @RequestMapping("/resErrorNotices")
    public String resErrorNotices(Model model, String userFlow, Integer pageIndex, Integer pageSize) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/resErrorNotices";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/resErrorNotices";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/resErrorNotices";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/resErrorNotices";
        }
        Map<String, Object> param = new HashMap<>();
        param.put("userFlow", userFlow);
        //消息提醒
        PageHelper.startPage(pageIndex, pageSize);
//        List<ResErrorSchNotice> notices = jswjwStudentBiz.getResErrorNotices(param);
        List<ResErrorSchNotice> notices = new ArrayList<>();
        model.addAttribute("notices", notices);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/jswjw/resErrorNotices";
    }

    @RequestMapping("/saveReadNotice")
    public String saveReadNotice(Model model, String userFlow, String recordFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(recordFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "信息标识符为空");
            return "res/jswjw/success";
        }
        ResErrorSchNotice notice = jswjwStudentBiz.readErrorSchNotice(recordFlow);
        if (notice == null) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "信息不存在");
            return "res/jswjw/success";
        }
        notice.setStatusId(NoticeStatusEnum.IsRead.getId());
        notice.setStatusName(NoticeStatusEnum.IsRead.getName());
        SysUser user = jswjwBiz.readSysUser(userFlow);
        jswjwStudentBiz.editErrorNotice(notice, user);
        return "res/jswjw/success";
    }

    @RequestMapping(value = {"/grade"}, method = {RequestMethod.GET})
    public String grade(String recFlow, String assessType, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(assessType)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "评价类型不能为空!");
            return "res/jswjw/viewGrade";
        }
        DeptTeacherGradeInfo rec = gradeBiz.getRec(recFlow);
        ResAssessCfg assessCfg = null;
        if (rec == null) {
            List<ResAssessCfg> resAssessCfgList = gradeBiz.getAssCfg(assessType);
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                assessCfg = resAssessCfgList.get(0);
            } else {
                model.addAttribute("resultId", "31801");
                model.addAttribute("resultType", "未查到配置评分表,请联系管理员");
                return "res/jswjw/viewGrade";
            }
        }
        if (assessCfg != null) {
            String content = assessCfg.getCfgBigValue();
            //解析评分xml
            List<Map<String, Object>> assessMaps = jswjwBiz.parseAssessCfg(content);
            model.addAttribute("assessMaps", assessMaps);
        }
        if (rec != null) {
            Map<String,Object> gradeMap = jswjwBiz.parseGradeXmlNew(rec.getRecContent());
            model.addAttribute("gradeMap",gradeMap);
            assessCfg = jswjwBiz.readResAssessCfg(rec.getCfgFlow());
            getForm(model,assessCfg);


        }
        model.addAttribute("rec", rec);
        model.addAttribute("assessCfg", assessCfg);
        return "res/jswjw/grade";
    }

    @RequestMapping(value = {"/viewGrade"}, method = {RequestMethod.GET})
    public String viewGrade(String userFlow, String deptFlow, String subDeptFlow, String assessType, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/viewGrade";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "标准科室不能为空");
            return "res/jswjw/viewGrade";
        }
        if (StringUtil.isEmpty(subDeptFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "轮转科室标识符为空");
            return "res/jswjw/viewGrade";
        }
        if (StringUtil.isEmpty(assessType)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "评价类型不能为空!TeacherAssess | DeptAssess");
            return "res/jswjw/viewGrade";
        }
        DeptTeacherGradeInfo rec = null;
        ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(subDeptFlow);
        if (process == null) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "轮转科室未关联，请重新选择轮转科室.");
            return "res/jswjw/viewGrade";
        }


        if ("TeacherAssess".equals(assessType)) {
            rec = gradeBiz.getGradeRec(userFlow, deptFlow, process.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        } else if ("DeptAssess".equals(assessType)) {
            rec = gradeBiz.getGradeRec(userFlow, deptFlow, process.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
        }
        ResAssessCfg assessCfg;
        if (rec != null) {
            assessCfg = jswjwBiz.readCfgAssess(rec.getCfgFlow());
        } else {
            ResAssessCfg search = new ResAssessCfg();
            search.setCfgCodeId(assessType);
            search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
            List<ResAssessCfg> resAssessCfgList = jswjwBiz.selectResByExampleWithBLOBs(search);
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                assessCfg = resAssessCfgList.get(0);
            } else {
                model.addAttribute("resultId", "31801");
                model.addAttribute("resultType", "未查到配置评分表,请联系管理员");
                return "res/jswjw/viewGrade";
            }
        }
        model.addAttribute("assessCfg", assessCfg);
        model.addAttribute("cfgFlow", assessCfg.getCfgFlow());
        if (assessCfg != null) {
            String content = assessCfg.getCfgBigValue();
            //解析评分xml
            List<Map<String, Object>> assessMaps = jswjwBiz.parseAssessCfg(content);
            model.addAttribute("assessMaps", assessMaps);
        }

        if (rec != null) {
            String content = rec.getRecContent();
            //解析登记信息的xml
            Object formDataMap = jswjwBiz.parseDocGradeXml(content);
            model.addAttribute("formDataMap", formDataMap);
        }
        return "res/jswjw/viewGrade";
    }

    @RequestMapping(value = {"/saveGrade"}, method = {RequestMethod.POST})
    public String saveGrade(String cfgFlow, String userFlow, String deptFlow, String subDeptFlow, String assessType, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/saveGrade";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "标准科室不能为空");
            return "res/jswjw/saveGrade";
        }
        if (StringUtil.isEmpty(subDeptFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "轮转科室标识符为空");
            return "res/jswjw/saveGrade";
        }

        if (StringUtil.isEmpty(assessType)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "评价类型不能为空!TeacherAssess | DeptAssess");
            return "res/jswjw/saveGrade";
        }

        DeptTeacherGradeInfo rec = null;
        ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(subDeptFlow);
        if (process == null) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "轮转科室未关联，请重新选择轮转科室.");
            return "res/jswjw/saveGrade";
        }
        if ("TeacherAssess".equals(assessType)) {
            rec = gradeBiz.getGradeRec(userFlow, deptFlow, process.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        } else if ("DeptAssess".equals(assessType)) {
            rec = gradeBiz.getGradeRec(userFlow, deptFlow, process.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
        }
        ResAssessCfg assessCfg;
        if (rec != null) {
            assessCfg = jswjwBiz.readCfgAssess(rec.getCfgFlow());
        } else {
            ResAssessCfg search = new ResAssessCfg();
            search.setCfgCodeId(assessType);
            search.setFormStatusId(com.pinde.core.common.GlobalConstant.FLAG_Y);
            List<ResAssessCfg> resAssessCfgList = jswjwBiz.selectResByExampleWithBLOBs(search);
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                assessCfg = resAssessCfgList.get(0);
            } else {
                model.addAttribute("resultId", "31801");
                model.addAttribute("resultType", "未查到配置评分表,请联系管理员");
                return "res/jswjw/saveGrade";
            }
        }

        String result = gradeBiz.saveGrade1(assessCfg, cfgFlow, userFlow, deptFlow, subDeptFlow, assessType, request);
        if (result != null && result.startsWith("error:")) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", result.split(":")[1]);
            return "res/jswjw/saveGrade";
        }
        return "res/jswjw/saveGrade";
    }

    @RequestMapping(value = {"/saveGradeTwo"}, method = {RequestMethod.POST})
    public String saveGradeTwo(String userFlow,String recFlow, String deptFlow, String resultFlow,String assessType, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/saveGrade";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "标准科室不能为空");
            return "res/jswjw/saveGrade";
        }

        if (StringUtil.isEmpty(assessType)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "评价类型不能为空!");
            return "res/jswjw/saveGrade";
        }

        DeptTeacherGradeInfo rec = gradeBiz.getRec(recFlow);
        ResDoctorSchProcess process = jswjwBiz.getProcessByResultFlow(resultFlow);
        if (process == null) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "轮转科室未关联，请重新选择轮转科室.");
            return "res/jswjw/saveGrade";
        }
        ResAssessCfg assessCfg;
        if (rec != null) {
            assessCfg = jswjwBiz.readCfgAssess(rec.getCfgFlow());
        } else {
            List<ResAssessCfg> resAssessCfgList = gradeBiz.getAssCfg(assessType);
            if (resAssessCfgList != null && resAssessCfgList.size() > 0) {
                assessCfg = resAssessCfgList.get(0);
            } else {
                model.addAttribute("resultId", "31801");
                model.addAttribute("resultType", "未查到配置评分表,请联系管理员");
                return "res/jswjw/saveGrade";
            }
        }

        String result = gradeBiz.saveGradeTwo(assessCfg, assessCfg.getCfgFlow(), userFlow, deptFlow, resultFlow, assessType, request);
        if (result != null && result.startsWith("error:")) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", result.split(":")[1]);
            return "res/jswjw/saveGrade";
        }
        return "res/jswjw/saveGrade";
    }
    /**
     * 最新讲座查询
     */
    @RequestMapping("/getNewLectures")
    public String getNewLectures(Model model, String userFlow, String roleId, Integer pageIndex, Integer pageSize) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/getNewLectures";
        }
        if (StringUtil.isEmpty(roleId)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "roleId标识符为空");
            return "res/jswjw/getNewLectures";
        }
        if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "roleId标识符不正确");
            return "res/jswjw/getNewLectures";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/getNewLectures";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/getNewLectures";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/getNewLectures";
        }
        String orgFlow = currUser.getOrgFlow();
        //获取当前配置的医师角色
        String doctorRole = jswjwBiz.getCfgCode("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        //获取当前配置的科秘角色
        String secretaryRole = jswjwBiz.getCfgCode("res_secretary_role_flow");
        String roleFlow = "";
        if ("Student".equals(roleId)) {
            ResDoctor doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
            } else {
                orgFlow = doctor.getOrgFlow();
            }
            roleFlow = doctorRole;
        }


        if ("Teacher".equals(roleId)) {
            roleFlow = teacherRole;
        }
        if ("Head".equals(roleId)) {
            roleFlow = headRole;
        }
        if ("Seretary".equals(roleId)) {
            roleFlow = secretaryRole;
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<ResLectureInfo> lectureInfos = jswjwBiz.SearchNewLectures(orgFlow, roleId, roleFlow);
        model.addAttribute("lectureInfos", lectureInfos);
        model.addAttribute("dataCount", PageHelper.total);
        Map<String, ResLectureScanRegist> registMap = new HashMap<>();
        Map<String, Integer> registNumMap = new HashMap<>();
        if (lectureInfos != null && lectureInfos.size() > 0) {
            for (ResLectureInfo lectureInfo : lectureInfos) {
                String lectureFlow = lectureInfo.getLectureFlow();
                ResLectureScanRegist lectureScanRegist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
                registMap.put(lectureFlow, lectureScanRegist);
                List<ResLectureScanRegist> resLectureScanRegists = jswjwBiz.searchIsRegists(lectureFlow);
                if (lectureScanRegist != null) {
                    registNumMap.put(lectureFlow, resLectureScanRegists.size());
                } else {
                    registNumMap.put(lectureFlow, 0);
                }
            }
            model.addAttribute("registMap", registMap);
        }
        return "res/jswjw/getNewLectures";
    }

    /**
     * 历史讲座查询
     */
    @RequestMapping("/getHistoryLectures")
    public String getHistoryLectures(Model model, String userFlow, Integer pageIndex, Integer pageSize) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/getHistoryLectures";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/getHistoryLectures";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/getHistoryLectures";
        }
        PageHelper.startPage(pageIndex, pageSize);

        Map<String, ResLectureEvaDetail> evaDetailMap = new HashMap<>();
        Map<String, Integer> evaMap = new HashMap<>();
        Map<String, String> scanMap = new HashMap<>();
        Map<String, String> scan2Map = new HashMap<>();
        List<Map<String, String>> newList = jswjwBiz.getHistoryLecture(userFlow);
        if (newList != null && !newList.isEmpty()) {
            String currDateTime = DateUtil.getCurrDateTime();
            String currDate = currDateTime.substring(0, 4) + "-" + currDateTime.substring(4, 6) + "-" + currDateTime.substring(6, 8);
            String currTime = currDateTime.substring(8, 10) + ":" + currDateTime.substring(10, 12);
            for (Map<String, String> bean : newList) {
                String isScan = bean.get("isCan");
                String isScan2 = bean.get("isCan2");
                String lectureFlow = bean.get("lectureFlow");
                String lectureEndTime = bean.get("lectureEndTime");
                String lectureTrainDate = bean.get("lectureTrainDate");
                //判断是否到评价期限
                String date = bean.get("lectureTrainDate");
                String time = bean.get("lectureEndTime");
                String unitID = bean.get("lectureUnitId");
                String period = bean.get("lectureEvaPeriod");
                String startDate = date.substring(0, 4) + date.substring(5, 7) + date.substring(8, 10) + time.substring(0, 2) + time.substring(3, 5) + "00";
                int step = 0;
                if (com.pinde.core.common.enums.SchUnitEnum.Hour.getId().equals(unitID)) {
                    step = Integer.parseInt(period);
                }
                if (com.pinde.core.common.enums.SchUnitEnum.Day.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24;
                }
                if (com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24 * 7;
                }
                if (com.pinde.core.common.enums.SchUnitEnum.Month.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24 * 30;
                }
                if (com.pinde.core.common.enums.SchUnitEnum.Year.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24 * 365;
                }
                String endDate = DateUtil.addHour(startDate, step);
                String currentDate = DateUtil.getCurrDateTime();
                int dateFlag = endDate.compareTo(currentDate);
                //判断结束
                if ((lectureEndTime.compareTo(currTime) < 0 && lectureTrainDate.compareTo(currDate) == 0) || (lectureTrainDate.compareTo(currDate) < 0)) {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan)) {
                        scanMap.put(lectureFlow, com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isScan2)) {
                        scan2Map.put(lectureFlow, com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                    evaMap.put(lectureFlow, dateFlag);
                }
                List<ResLectureEvaDetail> lectureEvaDetails = jswjwBiz.searchByUserFlowLectureFlow(userFlow, lectureFlow);
                if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
                    ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                    evaDetailMap.put(lectureFlow, lectureEvaDetail);
                }
            }
        }
        model.addAttribute("scanMap", scanMap);
        model.addAttribute("scan2Map", scan2Map);
        model.addAttribute("evaMap", evaMap);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("evaDetailMap", evaDetailMap);
        model.addAttribute("lectureInfos", newList);
        return "res/jswjw/getHistoryLectures";
    }

    /**
     * 报名讲座
     */
    @RequestMapping("/lectureRegist")
    public synchronized String lectureRegist(String lectureFlow, String roleId, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "报名成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/lectureRegist";
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "讲座流水号为空");
            return "res/jswjw/lectureRegist";
        }
        if (StringUtil.isEmpty(roleId)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "roleId标识符为空");
            return "res/jswjw/lectureRegist";
        }
        if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "roleId标识符不正确");
            return "res/jswjw/lectureRegist";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/lectureRegist";
        }
        String orgFlow = currUser.getOrgFlow();
        //获取当前配置的医师角色
        String doctorRole = jswjwBiz.getCfgCode("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        String roleFlow = "";
        ResDoctor doctor = null;
        if ("Student".equals(roleId)) {
            doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
            orgFlow = doctor.getOrgFlow();
            roleFlow = doctorRole;
        }
        if ("Teacher".equals(roleId)) {
            roleFlow = teacherRole;
        }
        if ("Head".equals(roleId)) {
            roleFlow = headRole;
        }
        ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
        if (regist != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsRegist())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "已经报过名了！！");
            return "res/jswjw/lectureRegist";
        }

        List<ResLectureScanRegist> resLectureScanRegists = jswjwBiz.searchIsRegists(lectureFlow);
        ResLectureInfo lectureInfo = jswjwBiz.read(lectureFlow);
        if (StringUtil.isBlank(lectureInfo.getLimitNum()) || resLectureScanRegists == null || resLectureScanRegists.size() < Integer.valueOf(lectureInfo.getLimitNum())) {

            List<ResLectureInfo> infos = jswjwBiz.checkJoinList(lectureFlow, userFlow);
            if (infos != null && infos.size() > 0) {
                ResLectureInfo resLectureInfo = infos.get(0);
                model.addAttribute("resultId", "30111013");
                model.addAttribute("resultType", "已报名同一时间【" + resLectureInfo.getLectureContent() + "】，不能报名！");
                return "res/jswjw/lectureRegist";
            }
            int count = jswjwBiz.editLectureScanRegist(lectureFlow, currUser, regist, doctor);
            if (count < 0) {
                model.addAttribute("resultId", "30111013");
                model.addAttribute("resultType", "该讲座报名人数已满，请刷新列表页面！");
                return "res/jswjw/lectureRegist";
            }
            if (count == 0) {
                model.addAttribute("resultId", "30111013");
                model.addAttribute("resultType", "报名失败，请刷新列表页面！");
                return "res/jswjw/lectureRegist";
            }
            if (count == 1) {
                String lectureTrainDate = lectureInfo.getLectureTrainDate();
                String lectureStartTime = lectureInfo.getLectureStartTime();
                String year = lectureTrainDate.substring(0, 4);
                model.addAttribute("year", year);
                String month = lectureTrainDate.substring(5, 7);
                model.addAttribute("month", month);
                String day = lectureTrainDate.substring(8, 10);
                model.addAttribute("day", day);
                String hour = lectureStartTime.substring(0, 2);
                model.addAttribute("hour", hour);
                String min = lectureStartTime.substring(3, 5);
                model.addAttribute("min", min);
            } else {
                model.addAttribute("resultId", "32302");
                model.addAttribute("resultType", "报名失败！");
            }
        } else {
            model.addAttribute("resultId", "32302");
            model.addAttribute("resultType", "该讲座报名人数已满，请刷新列表页面！");
        }
        return "res/jswjw/lectureRegist";
    }

    /**
     * 取消报名讲座
     */
    @RequestMapping("/lectureCannelRegist")
    public synchronized String lectureCannelRegist(String lectureFlow, String roleId, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "取消成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "讲座流水号为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(roleId)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "roleId标识符为空");
            return "res/jswjw/success";
        }
        if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "roleId标识符不正确");
            return "res/jswjw/success";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/success";
        }
        String orgFlow = currUser.getOrgFlow();
        //获取当前配置的医师角色
        String doctorRole = jswjwBiz.getCfgCode("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        String roleFlow = "";
        ResDoctor doctor = null;
        if ("Student".equals(roleId)) {
            doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
            orgFlow = doctor.getOrgFlow();
            roleFlow = doctorRole;
        }
        if ("Teacher".equals(roleId)) {
            roleFlow = teacherRole;
        }
        if ("Head".equals(roleId)) {
            roleFlow = headRole;
        }
        ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
        if (regist == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "你未报名，无法取消报名信息！");
            return "res/jswjw/success";
        }
        if (StringUtil.isBlank(regist.getIsRegist())) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "你未报名，无法取消报名信息");
            return "res/jswjw/success";
        }
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsRegist())) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "你已取消报名！");
            return "res/jswjw/success";
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan())) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "你已扫码签到，无法取消报名信息！");
            return "res/jswjw/success";
        }
        regist.setIsRegist(com.pinde.core.common.GlobalConstant.FLAG_N);
        int c = jswjwBiz.saveRegist(regist);
        if (c == 0) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "取消失败！");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }

    /**
     * 查看页面
     */
    @RequestMapping("/evaluate")
    public String evaluate(String lectureFlow, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/evaluate";
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "讲座流水号为空");
            return "res/jswjw/evaluate";
        }
        model.addAttribute("lectureFlow", lectureFlow);
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        List<ResLectureEvaDetail> lectureEvaDetails = jswjwBiz.searchByUserFlowLectureFlow(userFlow, lectureFlow);
        if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
            ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
            if (resLectureEvaDetail != null) {
                model.addAttribute("resLectureEvaDetail", resLectureEvaDetail);
            }
        }
        return "res/jswjw/evaluate";
    }

    /**
     * 查看页面
     */
    @RequestMapping("/evaluateNew")
    public String evaluateNew(String lectureFlow, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/evaluateNew";
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "讲座流水号为空");
            return "res/jswjw/evaluateNew";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        // 查询评价人信息
        ResLectureScanRegist scanRegist = jswjwBiz.searchByUserFlowAndLectureFlow(currUser.getUserFlow(), lectureFlow);
        model.addAttribute("scanRegist", scanRegist);
        // 查询评价指标信息
        List<LectureInfoTarget> lectureTargetList = jswjwBiz.searchLectureInfoTargetList(lectureFlow);
        model.addAttribute("lectureTargetList", lectureTargetList);
        // 查询当前用户评价信息
        Map<String, String> param = new HashMap<String, String>();
        param.put("lectureFlow", lectureFlow);
        param.put("userFlow", currUser.getUserFlow());
        List<ResLectureEvaDetail> lectureEvaDetailList = jswjwBiz.searchUserEvalList(param);
        //评价人员评分详情
        Map<String, Object> evalDetailMap = new HashMap<>();
        if (null != lectureEvaDetailList && 0 < lectureEvaDetailList.size()) {
            for (ResLectureEvaDetail evaDetail : lectureEvaDetailList) {
                evalDetailMap.put(scanRegist.getRecordFlow() + evaDetail.getTargetFlow(), evaDetail.getEvaScore());
            }
        }
        model.addAttribute("evalDetailMap", evalDetailMap);
        return "res/jswjw/evaluateNew";
    }

    /**
     * 保存评价
     */
    @RequestMapping("/saveEvaluate")
    public String saveEvaluate(ResLectureEvaDetail resLectureEvaDetail, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/saveEvaluate";
        }
        if (StringUtil.isEmpty(resLectureEvaDetail.getLectureFlow())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "讲座流水号为空");
            return "res/jswjw/saveEvaluate";
        }
        if (StringUtil.isEmpty(resLectureEvaDetail.getEvaContent())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "评价内容为空");
            return "res/jswjw/saveEvaluate";
        }
        if (StringUtil.isEmpty(resLectureEvaDetail.getEvaScore())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "评价分数为空");
            return "res/jswjw/saveEvaluate";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        String userName = currUser.getUserName();
        if (StringUtil.isNotBlank(userFlow)) {
            resLectureEvaDetail.setOperUserFlow(userFlow);
        }
        if (StringUtil.isNotBlank(userName)) {
            resLectureEvaDetail.setOperUserName(userName);
        }
        ResLectureScanRegist lectureScanRegists = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, resLectureEvaDetail.getLectureFlow());
        if (lectureScanRegists == null) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "报名信息与扫码信息为空");
            return "res/jswjw/saveEvaluate";
        }
        if (StringUtil.isBlank(lectureScanRegists.getIsScan())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "扫码信息为空,不得评价");
            return "res/jswjw/saveEvaluate";
        }
        List<ResLectureEvaDetail> lectureEvaDetails = jswjwBiz.searchByUserFlowLectureFlow(userFlow, resLectureEvaDetail.getLectureFlow());
        if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
            model.addAttribute("resultId", "32302");
            model.addAttribute("resultType", "已经评价过讲座信息！请刷新页面后重试！");
            return "res/jswjw/saveEvaluate";
        }
        int count = jswjwBiz.editResLectureEvaDetail(resLectureEvaDetail, userFlow);
        if (count == 0) {
            model.addAttribute("resultId", "32302");
            model.addAttribute("resultType", "保存评价失败！");
        }
        return "res/jswjw/saveEvaluate";
    }

    /**
     * 保存评价
     */
    @RequestMapping("/saveEvaluateNew")
    public String saveEvaluateNew(String evals, String recordFlow, BigDecimal avgScore, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/saveEvaluate";
        }
        if (StringUtil.isEmpty(recordFlow)) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "讲座流水号为空");
            return "res/jswjw/saveEvaluate";
        }
        // 查询当前用户报名签到信息
        ResLectureScanRegist scanRegist = jswjwBiz.getLectureScanRegistInfoByFlow(recordFlow);
        if (scanRegist == null) {
            model.addAttribute("resultId", "31901");
            model.addAttribute("resultType", "未参加该活动，无法评价");
            return "res/jswjw/saveEvaluate";
        }
        if (scanRegist.getEvalScore() != null) {
            model.addAttribute("resultId", "32001");
            model.addAttribute("resultType", "活动已评价");
            return "res/jswjw/saveEvaluate";
        }
        // 讲座活动评分信息
        scanRegist.setEvalScore(avgScore);
        if (StringUtil.isBlank(evals)) {
            model.addAttribute("resultId", "33001");
            model.addAttribute("resultType", "未选择评分！");
            return "res/jswjw/saveEvaluate";
        }
        List<ResLectureEvaDetail> lectureEvaDetailList = JSON.parseArray(evals, ResLectureEvaDetail.class);
        if (null == lectureEvaDetailList || 0 == lectureEvaDetailList.size()) {
            model.addAttribute("resultId", "34001");
            model.addAttribute("resultType", "未选择评分！");
            return "res/jswjw/saveEvaluate";
        }
        // 保存评分用到的参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        paramMap.put("recordStatus", com.pinde.core.common.GlobalConstant.FLAG_Y);
        paramMap.put("operUserFlow", currUser.getUserFlow());
        paramMap.put("operUserName", currUser.getUserName());
        paramMap.put("createTime", DateUtil.getCurrDateTime());
        paramMap.put("lectureEvaDetailList", lectureEvaDetailList);
        String isSuccess = jswjwBiz.saveLectureEval(scanRegist, paramMap);
        if (com.pinde.core.common.GlobalConstant.SAVE_FAIL.equals(isSuccess)) {
            model.addAttribute("resultId", "35001");
            model.addAttribute("resultType", "保存异常！");
            return "res/jswjw/saveEvaluate";
        }
        return "res/jswjw/saveEvaluate";
    }

    @RequestMapping(value = "/suggestions")
    public String suggestions(String userFlow, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/suggestions";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/suggestions";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/suggestions";
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<ResTrainingOpinion> trainingOpinions = resLiveTrainingBiz.readByOpinionUserFlow(userFlow);
        model.addAttribute("trainingOpinions", trainingOpinions);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/jswjw/suggestions";
    }

    @RequestMapping(value = "/delOpinions")
    public String delOpinions(String trainingOpinionFlow, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(trainingOpinionFlow)) {
            model.addAttribute("resultId", "32602");
            model.addAttribute("resultType", "意见反馈流水号为空");
            return "res/jswjw/success";
        }
        ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
        if (trainingOpinion == null) {
            model.addAttribute("resultId", "32603");
            model.addAttribute("resultType", "意见反馈不存在");
            return "res/jswjw/success";
        }
        trainingOpinion.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        int count = resLiveTrainingBiz.edit(trainingOpinion, userFlow);
        if (count == 0) {
            model.addAttribute("resultId", "32601");
            model.addAttribute("resultType", "删除失败");
        }
        return "res/jswjw/success";
    }

    @RequestMapping(value = "/opinionsDetail")
    public String opinionsDetail(String trainingOpinionFlow, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(trainingOpinionFlow)) {
            model.addAttribute("resultId", "32602");
            model.addAttribute("resultType", "意见反馈流水号为空");
            return "res/jswjw/success";
        }
        ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
        List<ResTrainingOpinion> trainingOpinions = new ArrayList<>();
        trainingOpinions.add(trainingOpinion);
        model.addAttribute("trainingOpinions", trainingOpinions);
        return "res/jswjw/suggestions";
    }

    @RequestMapping(value = "/saveOpinions")
    public String saveOpinions(ResTrainingOpinion trainingOpinion, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }

        if (StringUtil.isEmpty(trainingOpinion.getOpinionUserContent())) {
            model.addAttribute("resultId", "32602");
            model.addAttribute("resultType", "意见反馈信息为空");
            return "res/jswjw/success";
        } else {
            try {
                String content = URLDecoder.decode(trainingOpinion.getOpinionUserContent(), "UTF-8");
                trainingOpinion.setOpinionUserContent(content);
            } catch (UnsupportedEncodingException e) {
                logger.error("", e);
            }
        }
        SysUser currentUser = jswjwBiz.readSysUser(userFlow);
        String userName = currentUser.getUserName();
        if (StringUtil.isNotBlank(userName)) {
            trainingOpinion.setOpinionUserName(userName);
        }
        trainingOpinion.setOpinionUserFlow(userFlow);

        trainingOpinion.setOpinionUserFlow(userFlow);
        String orgFlow = "";
        String orgName = "";
        if (StringUtil.isBlank(orgFlow)) {
            ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
                orgName = doctor.getSecondOrgName();
            } else {
                orgFlow = doctor.getOrgFlow();
                orgName = doctor.getOrgName();
            }
        }
        if (StringUtil.isNotBlank(orgFlow)) {
            trainingOpinion.setOrgFlow(orgFlow);
        }
        if (StringUtil.isNotBlank(orgName)) {
            trainingOpinion.setOrgName(orgName);
        }
        String currTime = DateUtil.getCurrDateTime();
        trainingOpinion.setEvaTime(currTime);
        int count = resLiveTrainingBiz.edit(trainingOpinion, userFlow);
        if (count == 0) {
            model.addAttribute("resultId", "32601");
            model.addAttribute("resultType", "保存失败");
        }
        return "res/jswjw/success";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/getZhupeiNotices"})
    public String getZhupeiNotices(String userFlow, String roleId, Integer pageIndex, Integer pageSize, String noticeTitle, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/noticeList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/noticeList";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色为空");
            return "res/jswjw/noticeList";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        String orgFlow = currUser.getOrgFlow();
        String doctorTypeId = "";
        ResDoctor doctor = null;
        if ("Student".equals(roleId)) {
            doctor = jswjwBiz.readResDoctor(userFlow);
            if (doctor == null) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "医师信息不存在");
                return "res/jswjw/noticeList";
            }
            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
            } else {
                orgFlow = doctor.getOrgFlow();
            }
            doctorTypeId = doctor.getDoctorTypeId();
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<ResTarinNotice> tarinNotices = resLiveTrainingBiz.searchByTitleOrgFlowAndWorkOrgId(noticeTitle, orgFlow, doctor);
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);

        String httpurl = request.getRequestURL().toString();
        String servletPath = request.getServletPath();
        String hostUrl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/jswjw/showNotice/no-pic.png";
        model.addAttribute("hostUrl", hostUrl);
        model.addAttribute("tarinNotices", tarinNotices);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/jswjw/noticeList";
    }

    @RequestMapping(value = {"/showSysNotice"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String showSysNotice(String infoFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
        map.put("info", info);
        return JSON.toJSONString(map);
    }

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("<a\\s+[^<>]*\\s+href=\"([^<>\"]*)\"[^<>]*>");
        ////System.out.println(pattern);
        String str = "实习生";
        try {
            String encode = URLEncoder.encode(str, "UTF-8");
            //System.out.println(encode);
            encode = URLDecoder.decode("%E4%B8%AD%E7%BA%A7%E8%81%8C%E7%A7%B0", "UTF-8");
            //System.out.println(encode);

        } catch (UnsupportedEncodingException e) {
            logger.error("", e);
        }
        String href = null;

        Matcher matcher = pattern.matcher("<p>指南，加附件</p><p style=\"line-height: 16px;\"><img src=\"/pdsci/ueditor/dialogs/attachment/fileTypeImages/icon_doc.gif\"/><a href=\"http://192.168.2.17:7070/pdsciupload/file/20160907/18811473233002126.docx\">【住院医师过程管理3.5】V3.5.0.69测试记录.docx</a><a href=\"http://192.168.2.17:7070/pdsciupload/file/20160907/18811473233002126.docx\">【住院医师过程管理3.5】V3.5.0.69测试记录.docx</a></p><p><br/></p>");

        if (matcher.find()) {
            href = matcher.group(1);
        }

        //System.out.println(href);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date;
        String startDate = "2018-01-10 15:20";
        //System.out.println(startDate);
        try {
            date = simpleDateFormat.parse(startDate);
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            calender.add(Calendar.MINUTE, -10);

            startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
        }
        //System.out.println(startDate);
    }

    @RequestMapping(value = {"/zpNoticeDetail"})
    public String zpNoticeDetail(String userFlow, String recordFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/zpNoticeDetail";
        }
        if (StringUtil.isEmpty(recordFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "住培指南标识符为空");
            return "res/jswjw/zpNoticeDetail";
        }
        ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
        if (tarinNotices == null) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "住培指南标不存在，请刷新列表页面");
            return "res/jswjw/zpNoticeDetail";
        }
        model.addAttribute("title", tarinNotices.getResNoticeTitle());
        model.addAttribute("content", tarinNotices.getResNoticeContent());
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String httpurl = httpRequest.getRequestURL().toString();
        if (!httpurl.contains("https")) {
            httpurl = "https" + httpurl.substring(4);
        }
        String servletPath = httpRequest.getServletPath();
        request.getServerName();
        String hostUrl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/jswjw/showNotice/showNoticeDetail.jsp?recordFlow=" + recordFlow;
        String androidimgurl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/jswjw/showNotice/showNoticeDetail2.jsp?recordFlow=" + recordFlow;
        model.addAttribute("iosDetailUrl", hostUrl);
        model.addAttribute("androidDetailUrl", androidimgurl);
//		ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
//
//		List<Map<String,String>> maps=getElements(tarinNotices.getResNoticeContent(),"a","href");
//		if(maps!=null&&maps.size()>0)
//		{
//			model.addAttribute("files", JSON.toJSON(maps));
//		}
//		List<String> imgs = match(tarinNotices.getResNoticeContent(), "img", "src");
//		if(imgs!=null&&imgs.size()>0)
//		{
//			model.addAttribute("imgs", JSON.toJSON(imgs));
//		}
//		tarinNotices.setResNoticeContent(StringUtil.Html2Text(tarinNotices.getResNoticeContent()));
//		model.addAttribute("tarinNotices",tarinNotices);
        return "res/jswjw/zpNoticeDetail";
    }

    @RequestMapping(value = {"/showZPdetail"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public String showZPdetail(String recordFlow, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
        if (tarinNotices != null) {
            map.put("title", "住培指南详情——【" + tarinNotices.getResNoticeTitle() + "】");
            map.put("content", tarinNotices.getResNoticeContent());
        } else {
            map.put("title", "无详细信息");
            map.put("content", "住培指南详情");
        }
        return JSON.toJSONString(map);
    }

    @RequestMapping(value = {"/studentSignIn"}, method = {RequestMethod.POST})
    public String studentSignIn(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/studentSignIn";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/studentSignIn";
        }
        ResDoctor resDoctor = jswjwBiz.readResDoctor(userFlow);
        if (resDoctor == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/jswjw/studentSignIn";
        }
        String nowDay = DateUtil.getCurrDate();
        List<JsresAttendanceDetail> list = jswjwBiz.getAttendanceDetailList(nowDay, userFlow);
        int count = 0;
        String workingDaySign = com.pinde.core.common.GlobalConstant.FLAG_Y;
        if (list != null && list.size() > 0) {
            count = list.size();
            JsresAttendance attendance = jswjwBiz.getJsresAttendance(nowDay, userFlow);
            if ("-1".equals(attendance.getAttendStatus())) {
                workingDaySign = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        model.addAttribute("count", count);
        model.addAttribute("list", list);
        model.addAttribute("nowDay", nowDay);
        //告诉app端上一次签到是否为工作日签到，saveSignIn须有app端传workingDaySign值
        model.addAttribute("workingDaySign", workingDaySign);
        String timeN[] = nowDay.split("-");
        String timeInfoN = timeN[0] + "年" + timeN[1] + "月" + timeN[2] + "日";
        model.addAttribute("chinessNowDay", timeInfoN);
        model.addAttribute("resdoctor", resDoctor);
        List<ResOrgAddress> orgAddresses = timeBiz.readOrgAddress(resDoctor.getOrgFlow());
        model.addAttribute("orgAddresses", orgAddresses);
        List<ResOrgTime> times = timeBiz.readOrgTime(resDoctor.getOrgFlow());
        model.addAttribute("times", times);
        return "res/jswjw/studentSignIn";
    }

    @RequestMapping(value = {"/saveSignIn"}, method = {RequestMethod.POST})
    public String saveSignIn(String userFlow, String local, String remark, String workingDaySign, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isBlank(local)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "签到地点为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isBlank(workingDaySign)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "未选择是否工作日签到");
            return "res/jswjw/success";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/success";
        }
        ResDoctor resDoctor = jswjwBiz.readResDoctor(userFlow);
        if (resDoctor == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/jswjw/success";
        }
        // 优化：江苏西医未使用打卡时间区间功能，判断暂时注释
        /*List<ResOrgTime> times = timeBiz.readOrgTime(resDoctor.getOrgFlow());
        if (times != null && times.size() > 0) {
            boolean f = false;
            for (ResOrgTime t : times) {
                if (t.getStartTime().compareTo(time) <= 0 && t.getEndTime().compareTo(time) >= 0) {
                    f = true;
                }
            }
            if (!f) {
                model.addAttribute("resultId", "3011102");
                model.addAttribute("resultType", "当前时间不在签到时间范围内，无法签到！");
                return "res/jswjw/success";
            }
        }*/
        String nowDay = DateUtil.getCurrDate();
        JsresAttendance attendance = jswjwBiz.getJsresAttendance(nowDay, userFlow);
        String attendanceFlow = PkUtil.getUUID();
        if (attendance != null)
            attendanceFlow = attendance.getAttendanceFlow();
        if (attendance == null) {
            attendance = new JsresAttendance();
            attendance.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            attendance.setAttendanceFlow(attendanceFlow);
            attendance.setDoctorFlow(userFlow);
            attendance.setDoctorName(user.getUserName());
            attendance.setAttendDate(nowDay);
            attendance.setCreateTime(DateUtil.getCurrDateTime());
            attendance.setCreateUserFlow(userFlow);
            attendance.setModifyTime(DateUtil.getCurrDateTime());
            attendance.setModifyUserFlow(userFlow);
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(workingDaySign)) {
                attendance.setAttendStatus("-1");
                attendance.setAttendStatusName("轮休");
            } else {
                attendance.setAttendStatus("1");
                attendance.setAttendStatusName("出勤");
            }
            jswjwBiz.addJsresAttendance(attendance);
        }
        JsresAttendanceDetail detail = new JsresAttendanceDetail();
        detail.setRecordFlow(PkUtil.getUUID());
        detail.setAttendanceFlow(attendanceFlow);
        detail.setAttendDate(nowDay);
        // 禅道bug 学员app考勤签到，修改手机时间后打卡时间应该取服务器时间
        detail.setAttendTime(DateFormatUtils.format(System.currentTimeMillis(), "HH:mm"));
        detail.setAttendLocal(local);
        detail.setSelfRemarks(remark);
        detail.setDoctorFlow(userFlow);
        detail.setDoctorName(user.getUserName());
        detail.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        detail.setCreateTime(DateUtil.getCurrDateTime());
        detail.setCreateUserFlow(userFlow);
        detail.setModifyTime(DateUtil.getCurrDateTime());
        detail.setModifyUserFlow(userFlow);
        int count = 0;
        count = jswjwBiz.addJsresAttendanceDetail(detail);
        if (count != 1) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "签到失败！");
        }
        return "res/jswjw/success";
    }

    @RequestMapping(value = {"/qrCode"}, method = {RequestMethod.POST})
    public synchronized String qrCode(String userFlow,
                                      String roleId,
                                      String funcTypeId,
                                      String funcFlow,
                                      String codeInfo,
                                      String scanTime,
                                      HttpServletRequest request,
                                      Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/qrCode";
        }

        if (StringUtil.isEmpty(funcTypeId)) {
            model.addAttribute("resultId", "3011103");
            model.addAttribute("resultType", "功能类型为空");
            return "res/jswjw/qrCode";
        }

        if (StringUtil.isNotEquals("qrCode", funcTypeId)) {
            model.addAttribute("resultId", "3011104");
            model.addAttribute("resultType", "功能类型错误");

            return "res/jswjw/qrCode";
        }
//		if(StringUtil.isEmpty(funcFlow)){
//			model.addAttribute("resultId", "3011105");
//			model.addAttribute("resultType", "功能标识为空");
//			return "res/jswjw/qrCode";
//		}
        Map<String, String> paramMap = new HashMap<String, String>();
        transCodeInfo(paramMap, codeInfo);
        funcFlow = paramMap.get("funcFlow");
        //讲座签到
        if (StringUtil.isEquals(funcFlow, "lectureSignin"))
            return lectureSignin(userFlow, roleId, funcFlow, scanTime, model, paramMap);
        else if (StringUtil.isEquals(funcFlow, "lectureOutSignin"))
            return lectureOutSignin(userFlow, roleId, scanTime, model, paramMap);
        else if (StringUtil.isEquals(funcFlow, "queryQrCode")) {//技能考核签到
            return queryQrCode(userFlow, model, paramMap);
        } else if (StringUtil.isEquals(funcFlow, "activitySigin")) {//教学活动签到

            return activitySigin(userFlow, scanTime, model, paramMap);
        } else if (StringUtil.isEquals(funcFlow, "activityOutSigin")) {//教学活动签退

            return activityOutSigin(userFlow, scanTime, model, paramMap);
        } else if (StringUtil.isEquals(funcFlow, "osceDocJoinTea")) {//学员扫码考官

            return osceDocJoinTea(userFlow, roleId, model, paramMap);
        } else if (StringUtil.isEquals(funcFlow, "randomSignIn")) {//讲座随机签到
            String randomFlow = paramMap.get("randomFlow");
            if (StringUtil.isBlank(randomFlow)) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "无效二维码");
                return "res/jswjw/qrCode";
            }
            ResLectureRandomSign sign = jswjwBiz.readLectureRandomSign(randomFlow);
            if (sign == null) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "随机签到信息不存在");
                return "res/jswjw/qrCode";
            }
            ResLectureInfo lectureInfo = jswjwBiz.read(sign.getLectureFlow());
            if (lectureInfo == null) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "讲座信息不存在");
                return "res/jswjw/qrCode";
            }
            String nowDate = DateUtil.getCurrDate();
            if (!nowDate.equals(lectureInfo.getLectureTrainDate())) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "讲座日期不是当前日期");
                return "res/jswjw/qrCode";
            }
            //学员信息
            SysUser docUser = oscaAppBiz.readSysUser(userFlow);
            if (docUser == null) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "用户信息不存在");
                return "res/jswjw/qrCode";
            }
            ResDoctor doctor = oscaAppBiz.readResDoctor(userFlow);
			/*if(doctor==null){
				model.addAttribute("resultId", "3011101");
				model.addAttribute("resultType", "医师信息不存在");
				return "res/jswjw/qrCode";
			}*/
            ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, sign.getLectureFlow());
            if (regist == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "讲座未签到无法进行随机签到");
                return "res/jswjw/qrCode";
            }
            ResLectureRandomScan scan = jswjwBiz.readLectureRandomScan(userFlow, randomFlow);
            if (scan != null) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此次随机签到，你已完成！");
                return "res/jswjw/qrCode";
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(sign.getCodeStatusType())) {
                String signTime = paramMap.get("time");
                if (StringUtil.isBlank(signTime)) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "无效二维码");
                    return "res/jswjw/qrCode";
                }
                //5秒钟有效
                if (StringUtil.isBlank(scanTime)) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "缺少参数");
                    return "res/jswjw/qrCode";
                }
                if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                    model.addAttribute("resultId", "3011106");
                    model.addAttribute("resultType", "二维码已失效，请重新扫码！");
                    return "res/jswjw/qrCode";
                }
            }
            String nowTime = DateUtil.transDateTime(scanTime, "yyyyMMddHHmmss", "HH:mm:ss");
            if (nowTime.compareTo(sign.getCodeStartTime()) < 0 || nowTime.compareTo(sign.getCodeEndTime()) > 0) {
                model.addAttribute("resultId", "3011106");
                model.addAttribute("resultType", "扫码时间不在签到时间范围内！");
                return "res/jswjw/qrCode";
            }
            scan = new ResLectureRandomScan();
            scan.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
            scan.setScanTime(DateUtil.getCurrDateTime());
            scan.setRandomFlow(randomFlow);
            scan.setLectureFlow(lectureInfo.getLectureFlow());
            scan.setOperUserFlow(userFlow);
            scan.setCreateUserFlow(userFlow);
            scan.setCreateTime(DateUtil.getCurrDateTime());
            scan.setModifyUserFlow(userFlow);
            scan.setModifyTime(DateUtil.getCurrDateTime());
            scan.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            if (doctor != null) {
                String session = doctor.getSessionNumber();
                String trainingTypeId = doctor.getTrainingTypeId();
                String trainingTypeName = doctor.getTrainingTypeName();
                String trainingSpeId = doctor.getTrainingSpeId();
                String trainingSpeName = doctor.getTrainingSpeName();
                if (StringUtil.isNotBlank(session)) {
                    scan.setSessionNumber(session);
                }
                if (StringUtil.isNotBlank(trainingTypeId)) {
                    scan.setTrainingTypeId(trainingTypeId);
                }
                if (StringUtil.isNotBlank(trainingTypeName)) {
                    scan.setTrainingTypeName(trainingTypeName);
                }
                if (StringUtil.isNotBlank(trainingSpeId)) {
                    scan.setTrainingSpeId(trainingSpeId);
                }
                if (StringUtil.isNotBlank(trainingSpeName)) {
                    scan.setTrainingSpeName(trainingSpeName);
                }
            }
            int c = jswjwBiz.saveLectureRandomScan(scan);
            if (c == 0) {
                model.addAttribute("resultId", "3011106");
                model.addAttribute("resultType", "随机签到失败！");
                return "res/jswjw/qrCode";
            }
            return "res/jswjw/qrCode";
        } else {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "无效二维码");
            return "res/jswjw/qrCode";
        }
    }

    private String osceDocJoinTea(String userFlow, String roleId, Model model, Map<String, String> paramMap) {
        String teaFlow = paramMap.get("teaFlow");
        if (StringUtil.isBlank(teaFlow)) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "二维码错误");
            return "res/jswjw/qrCode";
        }

        OscaDoctorAssessment doctorAssessment = jswjwStudentBiz.getAuditOscaDocInfo(userFlow);
        //如果有查询有几站并且查询有哪些房间及相应信息
        if (doctorAssessment == null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "你暂未预约考核信息，无法参加考核！");
            return "res/jswjw/qrCode";
        }

        String clinicalFlow = doctorAssessment.getClinicalFlow();
        String doctorFlow = userFlow;
        paramMap.put("doctorFlow", doctorFlow);
        paramMap.put("clinicalFlow", clinicalFlow);
        paramMap.put("userFlow", teaFlow);
        paramMap.put("roleId", roleId);
        if (StringUtil.isNotBlank(clinicalFlow) && StringUtil.isNotBlank(doctorFlow)) {
            //考核信息
            OscaSkillsAssessment skillsAssessment = oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
            if (skillsAssessment == null) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "考核信息不存在");
                return "res/jswjw/qrCode";
            }
            String sigin = doctorAssessment.getSiginStatusId();
            if (StringUtil.isBlank(sigin) || SignStatusEnum.NoSignIn.getId().equals(sigin)) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "该考生尚未考核签到，无法进行考核，请学员联系管理员签到");
                return "res/jswjw/qrCode";
            }
            //学员信息
            SysUser docUser = oscaAppBiz.readSysUser(doctorFlow);
            if (docUser == null) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "用户信息不存在");
                return "res/jswjw/qrCode";
            }
            ResDoctor doctor = oscaAppBiz.readResDoctor(doctorFlow);
            if (doctor == null) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "医师信息不存在");
                return "res/jswjw/qrCode";
            }
            if (StringUtil.isBlank(doctorAssessment.getExamStartTime()) || StringUtil.isBlank(doctorAssessment.getExamEndTime())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "管理员未维护考核时间");
                return "res/jswjw/qrCode";
            }
            //查询当前考官可以考核学员哪些站点
            List<OscaSubjectStation> stations = oscaAppBiz.getTeaDocStation(paramMap);
            if (stations == null || stations.size() <= 0) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "你不在该考官负责的考核范围内，无法参加考核哦，换一个考官扫一扫吧~");
                return "res/jswjw/qrCode";
            }
            //先获取考官是否已经扫过学员的二维码了
            OscaTeaScanDoc b = oscaAppBiz.getOscaTeaScanDoc(paramMap);
            if (b != null) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "你扫过码了，请等待考核！");
                return "res/jswjw/qrCode";
            }
            //记录在哪个考场扫码的
            String examRoomFlow = "";
            for (int i = 0; i < stations.size(); i++) {
                OscaSubjectStation s = stations.get(i);
                Map<String, Object> bean = new HashMap<>();
                //查询当前站点考官可以考核学员的考场
                Map<String, String> param = new HashMap<>();
                param.put("userFlow", teaFlow);
                param.put("doctorFlow", doctorFlow);
                param.put("stationFlow", s.getStationFlow());
                param.put("clinicalFlow", clinicalFlow);
                //该站点考官可以考核的考场
                List<OscaSkillRoomTea> roomTeas = oscaAppBiz.getTeaRooms(param);
                OscaSkillRoomTea roomTea = null;
                if (roomTeas != null && roomTeas.size() > 0) {
                    roomTea = roomTeas.get(0);
                }
                String roomRecordFlow = roomTea.getRoomRecordFlow();
                bean.put("roomRecordFlow", roomRecordFlow);
                bean.put("roomFlow", roomTea.getRoomFlow());
                bean.put("roomName", roomTea.getRoomName());
                bean.put("examStatusId", com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
                bean.put("examStatusName", com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
                if (i == 0) {
                    examRoomFlow = roomRecordFlow;
                }
                //当前站点信息学员排队的考场
                OscaSkillRoomDoc docStation = oscaAppBiz.getOscaSkillRoomDocByDoc(param);
                if (docStation != null) {
                    roomRecordFlow = docStation.getRoomRecordFlow();
                    if (com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId().equals(docStation.getExamStatusId())) {
                        examRoomFlow = roomRecordFlow;
                    }
                }
            }
            //看看考场
            OscaSkillRoom room = oscaAppBiz.getOscaskillRoomByFlow(examRoomFlow);
            //添加考官扫码学员的信息
            SysUser user = oscaAppBiz.readSysUser(teaFlow);
            b = new OscaTeaScanDoc();
            b.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            b.setClinicalFlow(clinicalFlow);
            b.setClinicalName(skillsAssessment.getClinicalName());
            b.setDoctorFlow(doctorFlow);
            b.setDoctorName(docUser.getUserName());
            b.setExamTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
            b.setRoomRecordFlow(examRoomFlow);
            b.setRoomFlow(room.getRoomFlow());
            b.setRoomName(room.getRoomName());
            if (StringUtil.isBlank(b.getStatusId())) {
                b.setStatusId(ScanDocStatusEnum.StayAssessment.getId());
                b.setStatusName(ScanDocStatusEnum.StayAssessment.getName());
            }
            b.setPartnerFlow(teaFlow);
            b.setPartnerName(user.getUserName());
            b.setCodeInfo(doctorAssessment.getCodeInfo());//保存学员二维码信息
            int num = oscaAppBiz.editTeaScanDoc(b, user);

            return "res/jswjw/qrCode";
        } else {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "无效二维码");
            return "res/jswjw/qrCode";
        }
    }

    private String activityOutSigin(String userFlow, String scanTime, Model model, Map<String, String> paramMap) {
        String activityFlow = paramMap.get("activityFlow");
        if (StringUtil.isBlank(activityFlow)) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "二维码错误");
            return "res/jswjw/qrCode";
        }
        TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
        if (info == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus())) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/jswjw/qrCode";
        }
        String signTime = paramMap.get("time");
        String key = "jsres_" + info.getOrgFlow() + "_org_activity_code_type";
        String cfgv = jswjwBiz.getJsResCfgCode(key);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
            if (StringUtil.isBlank(signTime)) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "无效二维码");
                return "res/jswjw/qrCode";
            }
            //5秒钟有效
            if (StringUtil.isBlank(scanTime)) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "缺少参数");
                return "res/jswjw/qrCode";
            }
            if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                model.addAttribute("resultId", "3011106");
                model.addAttribute("resultType", "二维码已失效，请重新扫码！");
                return "res/jswjw/qrCode";
            }
        }
        String key2 = jswjwBiz.getJsResCfgCode("jsres_" + info.getOrgFlow() + "_org_activity_end_time");
        int endTime = 10;
        if (StringUtil.isNotBlank(key2)) {
            endTime = Integer.valueOf(key2);
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date;
        String startDate = info.getEndTime();
        String endDate = info.getEndTime();
        try {
            date = simpleDateFormat.parse(info.getEndTime());
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);
            calender.add(Calendar.MINUTE, -endTime);
            startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

            calender.add(Calendar.MINUTE, endTime * 2);
            endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            startDate = info.getEndTime();
            endDate = info.getEndTime();
        }

        TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
        if (result == null) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "你未参加该活动，无法进行签退！");
            return "res/jswjw/qrCode";
        }
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsScan())) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "你未签到参加该活动，无法进行签退！");
            return "res/jswjw/qrCode";
        }
        String nowDate = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
        if (nowDate.compareTo(startDate) < 0) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动签退暂未开始，无法签退！");
            return "res/jswjw/qrCode";
        }
        if (nowDate.compareTo(endDate) > 0) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动签退已结束，无法签退！");
            return "res/jswjw/qrCode";
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsScan2())) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "你已扫码签退成功！");
            return "res/jswjw/qrCode";
        }
        result.setIsEffective(com.pinde.core.common.GlobalConstant.FLAG_Y);
        result.setIsScan2(com.pinde.core.common.GlobalConstant.FLAG_Y);
        result.setScanTime2(DateUtil.getCurrDateTime());
        int c = activityBiz.saveResult(result, userFlow);
        if (c == 0) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "签退失败！");
            return "res/jswjw/qrCode";
        }
        return "res/jswjw/qrCode";
    }

    private String activitySigin(String userFlow, String scanTime, Model model, Map<String, String> paramMap) {
        String signTime = paramMap.get("time");
        String activityFlow = paramMap.get("activityFlow");

        if (StringUtil.isBlank(activityFlow)) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "二维码错误");
            return "res/jswjw/qrCode";
        }
        TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
        if (info == null || !com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(info.getRecordStatus())) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动信息不存在");
            return "res/jswjw/qrCode";
        }
        //活动不是已通过、不能扫码yuh20200518
        if (!"pass".equals(info.getActivityStatus())) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "此活动未被审核");
            return "res/jswjw/qrCode";
        }
        String key = "jsres_" + info.getOrgFlow() + "_org_activity_code_type";
        String cfgv = jswjwBiz.getJsResCfgCode(key);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
            if (StringUtil.isBlank(signTime)) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "无效二维码");
                return "res/jswjw/qrCode";
            }
            //5秒钟有效
            if (StringUtil.isBlank(scanTime)) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "缺少参数");
                return "res/jswjw/qrCode";
            }
            if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                model.addAttribute("resultId", "3011106");
                model.addAttribute("resultType", "二维码已失效，请重新扫码！");
                return "res/jswjw/qrCode";
            }
        }
        String key1 = jswjwBiz.getJsResCfgCode("jsres_" + info.getOrgFlow() + "_org_activity_start_time");
        int startTime = 10;
        if (StringUtil.isNotBlank(key1)) {
            startTime = Integer.valueOf(key1);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date;
        String startDate = info.getStartTime();
        String endDate = info.getStartTime();
        try {
            date = simpleDateFormat.parse(info.getStartTime());
            Calendar calender = Calendar.getInstance();
            calender.setTime(date);

            calender.add(Calendar.MINUTE, -startTime);
            startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
            calender.add(Calendar.MINUTE, startTime << 1);
            endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
        } catch (ParseException e) {
            startDate = info.getStartTime();
            endDate = info.getStartTime();
        }
        String nowStr = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
        if (nowStr.compareTo(startDate) < 0) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动签到暂未开始，无法参加活动");
            return "res/jswjw/qrCode";
        }
        if (nowStr.compareTo(endDate) > 0) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "活动签到已结束，无法参加活动");
            return "res/jswjw/qrCode";
        }
        int count = activityBiz.checkJoin2(activityFlow, userFlow);
        if (count > 0) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "该时间段，你已报名参加其他教学活动，无法参加");
            return "res/jswjw/qrCode";
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (doctor == null) {
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/jswjw/qrCode";
        }
        String orgFlow = "";
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            orgFlow = doctor.getSecondOrgFlow();
        } else {
            orgFlow = doctor.getOrgFlow();
        }
        Map<String, String> param = new HashMap<>();
        param.put("roleFlag", "doctor");
        param.put("userFlow", userFlow);
        param.put("infoOrgFlow", info.getOrgFlow());
        param.put("orgFlow", orgFlow);
        param.put("deptFlow", info.getDeptFlow());
//            List<Map<String, Object>> list = activityBiz.findActivityList(param);
//            if (list == null || list.size() <= 0) {
//                model.addAttribute("resultId", "3011107");
//                model.addAttribute("resultType", "该教学活动你无权限参加");
//                return "res/jswjw/qrCode";
//            }
        TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
        if (result != null) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsScan())) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "你已扫码签到成功！");
                return "res/jswjw/qrCode";
            }
            result.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
            result.setScanTime(DateUtil.getCurrDateTime());

            int c = activityBiz.saveResult(result, userFlow);
            if (c == 0) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "签到失败！");
                return "res/jswjw/qrCode";
            }
        } else {


            List<TeachingActivityInfo> infos = activityBiz.checkJoinList(activityFlow, userFlow);
            if (infos != null && infos.size() > 0) {
                String msg = "";
                for (TeachingActivityInfo activityInfo : infos) {
                    msg += "【" + activityInfo.getActivityName() + "】,";
                }
                model.addAttribute("resultId", "30111013");
                model.addAttribute("resultType", "当前活动与" + msg + "时间段重叠，请取消重叠时间段的活动！");
                return "res/jswjw/qrCode";
            }
            result = new TeachingActivityResult();
            result.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
            result.setScanTime(DateUtil.getCurrDateTime());
            result.setActivityFlow(activityFlow);
            result.setUserFlow(userFlow);
            result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            int c = activityBiz.saveResult(result, userFlow);
            if (c == 0) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "签到失败！");
                return "res/jswjw/qrCode";
            }
        }
        return "res/jswjw/qrCode";
    }

    private String queryQrCode(String userFlow, Model model, Map<String, String> paramMap) {
        String recordFlow = paramMap.get("recordFlow");
        if (StringUtil.isNotBlank(recordFlow)) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "无效二维码");
            return "res/jswjw/qrCode";
        }
        String clinicalFlow = paramMap.get("clinicalFlow");
        if (StringUtil.isBlank(clinicalFlow)) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "技能考核信息无效");
            return "res/jswjw/qrCode";
        }
        OscaSkillsAssessment skillsAssessment = oscaAppBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
        if (skillsAssessment == null) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "技能考核信息不存在");
            return "/res/jswjw/qrCode";
        }
        //查询预约信息
        OscaDoctorAssessment oscaDoctorAssessment = jswjwStudentBiz.getOscaDoctorAssessment(userFlow, clinicalFlow);
        if (oscaDoctorAssessment == null) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "你未预约该场考核");
            return "res/jswjw/qrCode";
        }
        if (!AuditStatusEnum.Passed.getId().equals(oscaDoctorAssessment.getAuditStatusId())) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "你的预约信息暂未审核通过");
            return "res/jswjw/qrCode";
        }
        if (SignStatusEnum.SignIn.getId().equals(oscaDoctorAssessment.getSiginStatusId())) {
            model.addAttribute("resultId", "3011107");
            model.addAttribute("resultType", "你已签到成功");
            return "res/jswjw/qrCode";
        }
        oscaDoctorAssessment.setSiginTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
        oscaDoctorAssessment.setSiginStatusId(SignStatusEnum.SignIn.getId());
        oscaDoctorAssessment.setSiginStatusName(SignStatusEnum.SignIn.getName());
        if (StringUtil.isNotBlank(oscaDoctorAssessment.getIsPass()) && DoctorScoreEnum.Miss.getId().equals(oscaDoctorAssessment.getIsPass())) {
            oscaDoctorAssessment.setIsPass(DoctorScoreEnum.PendingEnter.getId());
            oscaDoctorAssessment.setIsPassName(DoctorScoreEnum.PendingEnter.getName());
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        int count = jswjwStudentBiz.editOscaDoctorAssessment(oscaDoctorAssessment, user);

        List<OscaSubjectStation> stations = oscaAppBiz.getOscaSubjectStations(skillsAssessment.getSubjectFlow());
        List<OscaSkillDocStation> docStations = new ArrayList<>();
        for (OscaSubjectStation station : stations) {
            OscaSkillDocStation docStation = oscaAppBiz.getDocSkillStation(station.getStationFlow(), user.getUserFlow(), clinicalFlow);
            if (docStation == null)
                docStation = new OscaSkillDocStation();
            if (!com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId().equals(docStation.getExamStatusId()) &&
                    !com.pinde.core.common.enums.ExamStatusEnum.Assessment.getId().equals(docStation.getExamStatusId())) {
                docStation.setClinicalFlow(clinicalFlow);
                docStation.setClinicalName(skillsAssessment.getClinicalName());
                docStation.setStationFlow(station.getStationFlow());
                docStation.setStationName(station.getStationName());
                docStation.setDoctorFlow(userFlow);
                docStation.setDoctorName(user.getUserName());
                String date = DateUtil.getCurrDateTime2();
                docStation.setHoukaoTime(date);
                docStation.setWaitingTime(date);
                docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
                docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
                docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                docStations.add(docStation);
            }
        }
        oscaAppBiz.saveDocSkillStations(docStations, user);

        return "res/jswjw/qrCode";
    }

    private String lectureOutSignin(String userFlow, String roleId, String scanTime, Model model, Map<String, String> paramMap) {
        String lectureFlow = paramMap.get("lectureFlow");
        if (StringUtil.isBlank(lectureFlow)) {
            model.addAttribute("resultId", "3011106");
            model.addAttribute("resultType", "讲座信息不存在！");
            return "res/jswjw/qrCode";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011106");
            model.addAttribute("resultType", "roleId为空！");
            return "res/jswjw/qrCode";
        }
        if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "roleId标识符不正确");
            return "res/jswjw/qrCode";
        }

        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/qrCode";
        }
        ResDoctor doctor = null;
        String orgFlow = currUser.getOrgFlow();
        //获取当前配置的医师角色
        String doctorRole = jswjwBiz.getCfgCode("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        String roleFlow = "";
        if ("Student".equals(roleId)) {
            doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
            if (doctor == null) {
                model.addAttribute("resultId", "30906");
                model.addAttribute("resultType", "学员医师信息不存在");
                return "res/jswjw/qrCode";
            }
            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
            } else {
                orgFlow = doctor.getOrgFlow();
            }
            roleFlow = doctorRole;
        }
        if ("Teacher".equals(roleId)) {
            roleFlow = teacherRole;
        }
        if ("Head".equals(roleId)) {
            roleFlow = headRole;
        }
        ResLectureInfo info = jswjwBiz.read(lectureFlow);
        if (info != null) {

            String signTime = paramMap.get("time");
            String key = "jsres_" + info.getOrgFlow() + "_org_jiangzuo_code_type";
            String cfgv = jswjwBiz.getJsResCfgCode(key);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
                if (StringUtil.isBlank(signTime)) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "无效二维码");
                    return "res/jswjw/qrCode";
                }
                //5秒钟有效
                if (StringUtil.isBlank(scanTime)) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "缺少参数");
                    return "res/jswjw/qrCode";
                }
                if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                    model.addAttribute("resultId", "3011106");
                    model.addAttribute("resultType", "二维码已失效，请重新扫码！");
                    return "res/jswjw/qrCode";
                }
            }
            String key2 = jswjwBiz.getJsResCfgCode("jsres_" + info.getOrgFlow() + "_org_jiangzuo_end_time");
            int endTime = 10;
            if (StringUtil.isNotBlank(key2)) {
                endTime = Integer.valueOf(key2);
            }
            //扫码报名
            ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
            if (regist != null) {
                if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan())) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "你未签到参加该讲座，无法进行签退！");
                    return "res/jswjw/qrCode";
                }
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan2())) {
                    model.addAttribute("resultId", "3011109");
                    model.addAttribute("resultType", "已经扫过码了！");
                    return "res/jswjw/qrCode";
                }
                String startDate = info.getLectureTrainDate() + " " + info.getLectureStartTime();
                String endDate = info.getLectureTrainDate() + " " + info.getLectureEndTime();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date;
                try {
                    date = simpleDateFormat.parse(endDate);
                    Calendar calender = Calendar.getInstance();
                    calender.setTime(date);
                    calender.add(Calendar.MINUTE, -endTime);
                    startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

                    calender.add(Calendar.MINUTE, endTime * 2);
                    endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
                } catch (ParseException e) {
                }
                String nowDate = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
               /* if (nowDate.compareTo(startDate) < 0) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
                    return "res/jswjw/qrCode";
                }
                if (nowDate.compareTo(endDate) > 0) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
                    return "res/jswjw/qrCode";
                }*/
                regist.setIsScan2(com.pinde.core.common.GlobalConstant.FLAG_Y);
                regist.setScan2Time(DateUtil.getCurrDateTime());
            } else {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "你未参加该讲座，无法进行签退！");
                return "res/jswjw/qrCode";
            }
            int count = jswjwBiz.scanRegist(regist);
            if (count != 1) {
                model.addAttribute("resultId", "3011110");
                model.addAttribute("resultType", "扫码失败，请稍后再试！");
                return "res/jswjw/qrCode";
            }
            return "res/jswjw/qrCode";
        } else {
            model.addAttribute("resultId", "3011108");
            model.addAttribute("resultType", "二维码已失效！讲座信息不存在！");

        }

        return "res/jswjw/qrCode";
    }

    private String lectureSignin(String userFlow, String roleId, String funcFlow, String scanTime, Model model, Map<String, String> paramMap) {
        String lectureFlow = paramMap.get("lectureFlow");
        if (StringUtil.isBlank(lectureFlow)) {
            model.addAttribute("resultId", "3011106");
            model.addAttribute("resultType", "讲座信息不存在！");
            return "res/jswjw/qrCode";
        }
        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011106");
            model.addAttribute("resultType", "roleId为空！");
            return "res/jswjw/qrCode";
        }
        if (!"Student".equals(roleId) && !"Teacher".equals(roleId) && !"Head".equals(roleId) && !"Seretary".equals(roleId)) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "roleId标识符不正确");
            return "res/jswjw/qrCode";
        }

        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if (currUser == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/qrCode";
        }
        ResDoctor doctor = null;
        String orgFlow = currUser.getOrgFlow();
        //获取当前配置的医师角色
        String doctorRole = jswjwBiz.getCfgCode("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
        //获取当前配置的科秘角色
        String seretaryRole = jswjwBiz.getCfgCode("res_segcretary_role_flow");

        String roleFlow = "";
        if ("Student".equals(roleId)) {
            doctor = jswjwBiz.readResDoctor(currUser.getUserFlow());
            if (doctor == null) {
                model.addAttribute("resultId", "30906");
                model.addAttribute("resultType", "学员医师信息不存在");
                return "res/jswjw/qrCode";
            }
            if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                orgFlow = doctor.getSecondOrgFlow();
            } else {
                orgFlow = doctor.getOrgFlow();
            }
            roleFlow = doctorRole;
        }
        if ("Teacher".equals(roleId)) {
            roleFlow = teacherRole;
        }
        if ("Head".equals(roleId)) {
            roleFlow = headRole;
        }

        String currDate = DateUtil.getCurrDate();
        // 教学活动增加权限判断
        if ("Student".equals(roleId) && ("activitySigin".equals(funcFlow) || "activityOutSigin".equals(funcFlow))) {
            String isActivity = jswjwBiz.getJsResCfgCodeNew("jsres_doctor_activity_" + userFlow);
            //查询基地是否有时间配置
            SysOrg org = jswjwBiz.getOrg(orgFlow);
            JsresPowerCfg startCfg = jswjwBiz.readPowerCfg("jsres_payPower_startDate_" + orgFlow);
            JsresPowerCfg endCfg = jswjwBiz.readPowerCfg("jsres_payPower_endDate_" + orgFlow);
            if(null != org && !"Passed".equals(org.getCheckStatusId())){
                startCfg = null;
                endCfg = null;
            }

            if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(isActivity)) {
                model.addAttribute("resultId", "3010113");
                model.addAttribute("resultType", "无操作权限，请联系基地管理员！");
                return "res/jswjw/success";
            }else{
                if(null != startCfg && null != endCfg){
                    if(currDate.compareTo(startCfg.getCfgValue()) < 0 || currDate.compareTo(endCfg.getCfgValue()) > 0){
                        model.addAttribute("resultId", "3010113");
                        model.addAttribute("resultType", "无操作权限，请联系基地管理员！");
                        return "res/jswjw/success";
                    }
                }
            }
        }

        ResLectureScanRegist lectureScanRegist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
        if (lectureScanRegist == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(lectureScanRegist.getIsRegist())) {
            model.addAttribute("resultId", "3011209");
            model.addAttribute("resultType", "您还没有报名，请先报名！");
            return "res/jswjw/qrCode";
        }
        ResLectureInfo info = jswjwBiz.read(lectureFlow);
        if (info != null) {
            String signTime = paramMap.get("time");
            String key = "jsres_" + info.getOrgFlow() + "_org_jiangzuo_code_type";
            String cfgv = jswjwBiz.getJsResCfgCode(key);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfgv)) {
                if (StringUtil.isBlank(signTime)) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "无效二维码");
                    return "res/jswjw/qrCode";
                }
                //5秒钟有效
                String currTime = DateUtil.getCurrDateTime();
                if (StringUtil.isBlank(scanTime)) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "缺少参数");
                    return "res/jswjw/qrCode";
                }
                if (DateUtil.signSecondsBetweenTowDate(DateUtil.transDateTime(scanTime), DateUtil.transDateTime(signTime)) > 15) {
                    model.addAttribute("resultId", "3011106");
                    model.addAttribute("resultType", "二维码已失效，请重新扫码！");
                    return "res/jswjw/qrCode";
                }
            }
            String key1 = jswjwBiz.getJsResCfgCode("jsres_" + info.getOrgFlow() + "_org_jiangzuo_start_time");
            int startTime = 10;
            if (StringUtil.isNotBlank(key1)) {
                startTime = Integer.valueOf(key1);
            }
            //扫码报名
            ResLectureScanRegist regist = jswjwBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
            if (regist != null) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(regist.getIsScan())) {
                    model.addAttribute("resultId", "3011109");
                    model.addAttribute("resultType", "已经扫过码了！");
                    return "res/jswjw/qrCode";
                }
                String startDate = info.getLectureTrainDate() + " " + info.getLectureStartTime();
                String endDate = info.getLectureTrainDate() + " " + info.getLectureEndTime();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date;
                try {
                    date = simpleDateFormat.parse(startDate);
                    Calendar calender = Calendar.getInstance();
                    calender.setTime(date);
                    calender.add(Calendar.MINUTE, -startTime);
                    startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

                    calender.add(Calendar.MINUTE, startTime * 2);
                    endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
                } catch (ParseException e) {
                }
                String nowDate = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
                if (nowDate.compareTo(startDate) < 0) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
                    return "res/jswjw/qrCode";
                }
                if (nowDate.compareTo(endDate) > 0) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
                    return "res/jswjw/qrCode";
                }
                if (nowDate.compareTo(endDate) > 0) {
                    model.addAttribute("resultId", "30111013");
                    model.addAttribute("resultType", "讲座已经结束！");
                    return "res/jswjw/qrCode";
                }
                regist.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
                regist.setScanTime(DateUtil.getCurrDateTime());
            } else {
                String startDate = info.getLectureTrainDate() + " " + info.getLectureStartTime();
                String endDate = info.getLectureTrainDate() + " " + info.getLectureEndTime();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date;
                try {
                    date = simpleDateFormat.parse(startDate);
                    Calendar calender = Calendar.getInstance();
                    calender.setTime(date);
                    calender.add(Calendar.MINUTE, -startTime);
                    startDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");

                    calender.add(Calendar.MINUTE, startTime * 2);
                    endDate = DateUtil.formatDate(calender.getTime(), "yyyy-MM-dd HH:mm");
                } catch (ParseException e) {
                }
                String nowDate = DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm");
                if (nowDate.compareTo(startDate) < 0) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
                    return "res/jswjw/qrCode";
                }
                if (nowDate.compareTo(endDate) > 0) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "不在扫码时间范围内，无法扫码！");
                    return "res/jswjw/qrCode";
                }
                List<ResLectureInfo> infos = jswjwBiz.checkJoinList(lectureFlow, userFlow);
                if (infos != null && infos.size() > 0) {
                    String msg = "";
                    for (ResLectureInfo resLectureInfo : infos) {
                        msg += "【" + resLectureInfo.getLectureContent() + "】,";
                    }
                    model.addAttribute("resultId", "30111013");
                    model.addAttribute("resultType", "当前讲座与" + msg + "时间段重叠，请取消重叠时间段讲座！");
                    return "res/jswjw/qrCode";
                }
                regist = new ResLectureScanRegist();
                regist.setIsScan(com.pinde.core.common.GlobalConstant.FLAG_Y);
                regist.setScanTime(DateUtil.getCurrDateTime());
                regist.setLectureFlow(lectureFlow);
                regist.setOperUserFlow(userFlow);
                regist.setOperUserName(currUser.getUserName());
                if (doctor != null) {
                    regist.setTrainingTypeId(doctor.getTrainingTypeId());
                    regist.setTrainingTypeName(doctor.getTrainingTypeName());
                    regist.setTrainingSpeId(doctor.getTrainingSpeId());
                    regist.setTrainingSpeName(doctor.getTrainingSpeName());
                    regist.setSessionNumber(doctor.getSessionNumber());
                }
            }
            int count = jswjwBiz.scanRegist(regist);
            if (count != 1) {
                model.addAttribute("resultId", "3011110");
                model.addAttribute("resultType", "扫码失败，请稍后再试！");
                return "res/jswjw/qrCode";
            }
            return "res/jswjw/qrCode";
        } else {
            model.addAttribute("resultId", "3011108");
            model.addAttribute("resultType", "二维码已失效！讲座信息不存在！");

        }

        return "res/jswjw/qrCode";
    }

    //解析二维码字符串为map
    private void transCodeInfo(Map<String, String> paramMap, String codeInfo) {
        String[] params = StringUtil.split(codeInfo, "&");
        for (String paramStr : params) {
            if (paramStr.indexOf("=") > 0) {
                String key = paramStr.substring(0, paramStr.indexOf("="));
                String value = paramStr.substring(paramStr.indexOf("=") + 1, paramStr.length());
                paramMap.put(key, value);
            }
        }
    }

    /**
     * 获取指定HTML标签的指定属性的值
     *
     * @param source  要匹配的源文本
     * @param element 标签名称
     * @param attr    标签的属性名称
     * @return 属性值列表
     */
    public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    /**
     * 获取指定的HTML标签及内容
     *
     * @param source
     * @param elemnt
     * @return
     */
    public static List<Map<String, String>> getAllElements(String source, String elemnt) {
        List<Map<String, String>> result = null;
        if (StringUtil.isNotBlank(source)) {
            result = new ArrayList<>();
            Pattern p = Pattern.compile("<" + elemnt + "[^>]*>([^<]*)</" + elemnt + ">");
            Matcher m = p.matcher(source);
            Map<String, String> map = new HashMap<>();
            while (m.find()) {
                map = new HashMap<>();
                map.put("source", m.group());
                map.put("innerHtml", m.group(1));
                result.add(map);
            }
        }
        return result;
    }

    public List<Map<String, String>> getElements(String html, String element, String attr) {
        List<Map<String, String>> list = getAllElements(html, element);
        for (Map<String, String> map : list) {
            String sourse = map.get("source");
            List<String> lists = match(sourse, element, attr);
            if (lists != null && lists.size() > 0) {
                String href = lists.get(0);
                map.put(attr, href);
                long count = 0;
                if (StringUtil.isNotBlank(href)) {
                    String baseDir = StringUtil.defaultString(jswjwBiz.getCfgCode("upload_base_dir"));
                    String baseUrl = StringUtil.defaultString(jswjwBiz.getCfgCode("upload_base_url"));
                    if (StringUtil.isNotBlank(baseDir) && StringUtil.isNotBlank(baseUrl)) {
                        String fileUrl = href.substring(baseUrl.length());
                        fileUrl = baseDir + fileUrl;
                        File file = new File(fileUrl);
                        if (file.isFile() && file.exists()) {
                            count = file.length();
                        }
                    }
                }
                map.put("size", String.valueOf(count));
            }
        }
        return list;
    }

    @RequestMapping(value = {"/osca"}, method = {RequestMethod.POST})
    public String osca(String userFlow, String roleId,
                       HttpServletRequest request,
                       Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/osca";
        }

        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色为空");
            return "res/jswjw/osca";
        }
        if (!roleId.equals("Student")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户与角色不符");
            return "res/jswjw/osca";
        }
        //查询是否是有成功  预约考核信息
        OscaDoctorAssessment oscaDoctorAssessment = jswjwStudentBiz.getAuditOscaDocInfo(userFlow);
        model.addAttribute("oscaDoctorAssessment", oscaDoctorAssessment);
        String canClick = com.pinde.core.common.GlobalConstant.FLAG_Y;
        //如果有查询有几站并且查询有哪些房间及相应信息
        if (oscaDoctorAssessment != null) {
            OscaSkillsAssessment oscaSkillsAssessment = jswjwStudentBiz.getOscaSkillsAssessmentByFlow(oscaDoctorAssessment.getClinicalFlow());
            model.addAttribute("oscaSkillsAssessment", oscaSkillsAssessment);
            if (oscaSkillsAssessment == null) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "考核信息不存在");
                return "res/jswjw/osca";
            }
            String sigin = oscaDoctorAssessment.getSiginStatusId();
            if (StringUtil.isBlank(sigin) || SignStatusEnum.NoSignIn.getId().equals(sigin)) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "技能考核暂未签到，无法查看考核信息");
                return "res/jswjw/osca";
            }
            OscaSubjectMain main = jswjwStudentBiz.getOscaSubjectMain(oscaSkillsAssessment.getSubjectFlow());
            model.addAttribute("main", main);
            List<OscaSubjectStation> stations = jswjwStudentBiz.getOscaSubjectStations(oscaSkillsAssessment.getSubjectFlow());
            if (stations != null && stations.size() > 0) {
                model.addAttribute("stations", stations);
                Map<String, Object> stationRoomMap = new HashMap<>();
                for (OscaSubjectStation s : stations) {
                    String key = s.getStationFlow();
                    Map<String, Object> roomMap = new HashMap<>();
                    //查看站点下是否有考场
                    List<OscaSkillRoom> rooms = jswjwStudentBiz.getRooms(s.getStationFlow(), oscaSkillsAssessment.getClinicalFlow());
                    roomMap.put("rooms", rooms);
                    roomMap.put("roomSize", rooms.size());
                    if (rooms != null && rooms.size() > 0) {
                        //获取学员排队或已经考核的考场相应信息
                        OscaSkillRoomExt roomExt = jswjwStudentBiz.getDocRoom(userFlow, s.getStationFlow(), oscaSkillsAssessment.getClinicalFlow());
                        if (roomExt == null) {
                            //站点考场排队人数最少的一个考场
                            roomExt = jswjwStudentBiz.getStationBestRoom(s.getStationFlow(), oscaSkillsAssessment.getClinicalFlow());
                        } else {
                            if (roomExt.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId()) || roomExt.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId())) {
                                canClick = com.pinde.core.common.GlobalConstant.FLAG_N;
                            }
                        }
                        roomMap.put("roomExt", roomExt);
                    }
                    stationRoomMap.put(key, roomMap);
                }
                model.addAttribute("stationRoomMap", stationRoomMap);
            }
        } else {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "暂无考核信息");
            return "res/jswjw/osca";
        }
        model.addAttribute("canClick", canClick);
        return "res/jswjw/osca";
    }

    @RequestMapping(value = {"/lineUp"}, method = {RequestMethod.POST})
    public String lineUp(String userFlow, String roleId,
                         String roomRecordFlow, String clinicalFlow, String stationFlow, String waitingFlag,
                         HttpServletRequest request,
                         Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }

        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isBlank(roomRecordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "考场流水号为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isBlank(clinicalFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "考核信息流水号为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isBlank(stationFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "站点流水号为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isBlank(waitingFlag)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排队标识符为空");
            return "res/jswjw/success";
        }
        if (!waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_Y) && !waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "排队状态只能是排队或取消排队");
            return "res/jswjw/success";
        }
        if (!roleId.equals("Student")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户与角色不符");
            return "res/jswjw/success";
        }
        //校验是否已经在其他站排队
        Map<String, Object> param = new HashMap<>();
        param.put("userFlow", userFlow);
        param.put("roomRecordFlow", roomRecordFlow);
        param.put("clinicalFlow", clinicalFlow);
        param.put("stationFlow", stationFlow);
        if (waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
            //校验是否已经在排队 或正在考核中
            int count = jswjwStudentBiz.checkIsWait(param);
            if (count > 0) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "你正在其他站点或考场排队，请取消后再进行排队！");
                return "res/jswjw/success";
            }
        } else {
            //校验当前站点是否正在考核或已考核
            int count = jswjwStudentBiz.checkIsAssess(param);
            if (count > 0) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "当前站点正在考核或已考核，不得取消排队！");
                return "res/jswjw/success";
            }
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        //考场信息
        OscaSkillRoom room = jswjwStudentBiz.getRoomByFlow(roomRecordFlow);
        //考核信息
        OscaSkillsAssessment skillsAssessment = jswjwStudentBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
        //站点信息
        OscaSubjectStation station = jswjwStudentBiz.getOscaSubjectStationsByFlow(stationFlow);
        //当前站点信息
        OscaSkillRoomDoc docStation = jswjwStudentBiz.getOscaSkillRoomDocByDoc(param);
        if (docStation == null) {
            docStation = new OscaSkillRoomDoc();
            docStation.setDoctorFlow(userFlow);
            docStation.setDoctorName(user.getUserName());
        }
        if (room != null) {
            docStation.setRoomRecordFlow(room.getRecordFlow());
            docStation.setRoomFlow(room.getRoomFlow());
            docStation.setRoomName(room.getRoomName());
        } else {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "读取考场信息失败！");
            return "res/jswjw/success";
        }
        if (skillsAssessment != null) {
            docStation.setClinicalFlow(skillsAssessment.getClinicalFlow());
            docStation.setClinicalName(skillsAssessment.getClinicalName());
        } else {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "读取考核信息失败！");
            return "res/jswjw/success";
        }
        if (station != null) {
            docStation.setStationFlow(station.getStationFlow());
            docStation.setStationName(station.getStationName());
        } else {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "读取站点信息失败！");
            return "res/jswjw/success";
        }
        if (waitingFlag.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
            docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId());
            docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getName());
            docStation.setWaitingTime(DateUtil.getCurrentTime());
        } else {
            docStation.setExamStatusId(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getId());
            docStation.setExamStatusName(com.pinde.core.common.enums.ExamStatusEnum.StayAssessment.getName());
            docStation.setWaitingTime("");
        }
        int count = jswjwStudentBiz.updateOscaSkillRoomDoc(docStation, user);
        if (count == 0) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "操作失败！");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }

    @RequestMapping(value = {"/stationRooms"}, method = {RequestMethod.POST})
    public String stationRooms(String userFlow, String roleId, String clinicalFlow, String stationFlow,
                               HttpServletRequest request,
                               Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/stationRooms";
        }

        if (StringUtil.isBlank(roleId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户角色为空");
            return "res/jswjw/stationRooms";
        }
        if (StringUtil.isBlank(clinicalFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "考核信息流水号为空");
            return "res/jswjw/stationRooms";
        }
        if (StringUtil.isBlank(stationFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "站点流水号为空");
            return "res/jswjw/stationRooms";
        }
        if (!roleId.equals("Student")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户与角色不符");
            return "res/jswjw/stationRooms";
        }
        Map<String, Object> param = new HashMap<>();
        param.put("userFlow", userFlow);
        param.put("clinicalFlow", clinicalFlow);
        param.put("stationFlow", stationFlow);
        //当前站点信息 是否有考核或排阶段或已考核的考场信息
        //获取学员排队或已经考核的考场相应信息
        OscaSkillRoomExt roomExt = jswjwStudentBiz.getDocRoom(userFlow, stationFlow, clinicalFlow);
        OscaSubjectStation station = jswjwStudentBiz.getOscaSubjectStationsByFlow(stationFlow);
        model.addAttribute("station", station);
        List<OscaSkillRoomDoc> skillRoomDocs = jswjwStudentBiz.getDocAllStation(param);
        String canClick = com.pinde.core.common.GlobalConstant.FLAG_Y;
        if (skillRoomDocs != null) {
            for (OscaSkillRoomDoc rd : skillRoomDocs) {
                if (rd.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.Waiting.getId()) || rd.getExamStatusId().equals(com.pinde.core.common.enums.ExamStatusEnum.AssessIng.getId())) {
                    canClick = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
        }
        String roomRecordFlow = "";
        if (roomExt != null) {
            roomRecordFlow = roomExt.getRecordFlow();
            model.addAttribute("isSelect", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        model.addAttribute("canClick", canClick);
        param.put("roomRecordFlow", roomRecordFlow);
        //考核信息
        OscaSkillsAssessment skillsAssessment = jswjwStudentBiz.getOscaSkillsAssessmentByFlow(clinicalFlow);
        model.addAttribute("skillsAssessment", skillsAssessment);
        List<OscaSkillRoomExt> list = jswjwStudentBiz.getStationAllRoom(param);
        model.addAttribute("list", list);
        model.addAttribute("roomExt", roomExt);
        return "res/jswjw/stationRooms";
    }

    @RequestMapping(value = {"/addPaperOrPart"}, method = {RequestMethod.POST})
    public String addPaperOrPart(String userFlow, String type, String recordFlow,
                                 HttpServletRequest request,
                                 Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/addPaperOrPart";
        }

        if (StringUtil.isBlank(type)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "添加数据类型为空");
            return "res/jswjw/addPaperOrPart";
        }
        if (!type.equals("Paper") && !type.equals("Part")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型选择错误");
            return "res/jswjw/addPaperOrPart";
        }
        if (type.equals("Paper") && StringUtil.isNotBlank(recordFlow)) {
            JsresDoctorPaper paper = jswjwStudentBiz.readJsresDoctorPaperByFlow(recordFlow);
            model.addAttribute("resultData", paper);
        }
        if (type.equals("Part") && StringUtil.isNotBlank(recordFlow)) {
            JsresDoctorParticipation paper = jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByFlow(recordFlow);
            model.addAttribute("resultData", paper);

        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        model.addAttribute("doctor", doctor);
        return "res/jswjw/addPaperOrPart";
    }

    @RequestMapping(value = {"/savePaperOrPart"}, method = {RequestMethod.POST})
    public String savePaperOrPart(String userFlow, String type, String recordFlow, JsresDoctorPaper paper, JsresDoctorParticipation part,
                                  HttpServletRequest request,
                                  Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }

        if (StringUtil.isBlank(type)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "添加数据类型为空");
            return "res/jswjw/success";
        }
        if (!type.equals("Paper") && !type.equals("Part")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型选择错误");
            return "res/jswjw/success";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (type.equals("Paper")) {
            if (paper == null) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "论文信息为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(paper.getPaperDate())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "发表日期为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(paper.getPaperTitle())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "论文题目为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(paper.getPaperTypeId())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "论文类型为空");
                return "res/jswjw/success";
            } else {
                String typeId = paper.getPaperTypeId();
                if ("1".equals(typeId)) {
                    paper.setPaperTypeName("专业期刊发表的文章");
                }
                if ("2".equals(typeId)) {
                    paper.setPaperTypeName("学术会议交流论文");
                }
                if ("3".equals(typeId)) {
                    paper.setPaperTypeName("院（所）级学时会议交流文章");
                }
                if ("4".equals(typeId)) {
                    paper.setPaperTypeName("提交文章");
                }
            }
            if (StringUtil.isBlank(paper.getPublishedJournals())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "论文信息为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(paper.getAuthor())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "论文信息为空");
                return "res/jswjw/success";
            }
            paper.setDoctorFlow(userFlow);
            paper.setDoctorName(user.getUserName());
            int count = jswjwStudentBiz.editJsresDoctorPaper(paper, user);
            if (count == 0) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "保存失败");
            }
            return "res/jswjw/success";
        }
        if (type.equals("Part")) {

            if (part == null) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "科研记录为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(part.getParticipationDate())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "日期为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(part.getParticipationRoom())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "所在实验室为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(part.getParticipationAuthor())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "负责人为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(part.getParticipationTitle())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "题目为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(part.getParticipationRole())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "参与角色为空");
                return "res/jswjw/success";
            }
            if (StringUtil.isBlank(part.getParticipationComplete())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "完成情况为空");
                return "res/jswjw/success";
            }
            part.setDoctorFlow(userFlow);
            part.setDoctorName(user.getUserName());
            int count = jswjwStudentBiz.editJsresDoctorPart(part, user);
            if (count == 0) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "保存失败");
            }
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }

    @RequestMapping(value = {"/deleteData"}, method = {RequestMethod.POST})
    public String deleteData(String userFlow, String recordFlow, String type,
                             HttpServletRequest request,
                             Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }

        if (StringUtil.isBlank(type)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isBlank(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据流水号为空");
            return "res/jswjw/success";
        }
        if (!type.equals("Paper") && !type.equals("Part")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型选择错误");
            return "res/jswjw/success";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (type.equals("Paper") && StringUtil.isNotBlank(recordFlow)) {
            JsresDoctorPaper paper = jswjwStudentBiz.readJsresDoctorPaperByFlow(recordFlow);
            model.addAttribute("resultData", paper);
        }
        if (type.equals("Part") && StringUtil.isNotBlank(recordFlow)) {
            JsresDoctorParticipation paper = jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByFlow(recordFlow);
            model.addAttribute("resultData", paper);

        }
        if (type.equals("Paper")) {
            jswjwStudentBiz.deleteJsresDoctorPaperByFlow(recordFlow);
        }
        if (type.equals("Part")) {
            jswjwStudentBiz.deleteJsresDoctorParticipationByFlow(recordFlow);
        }
        return "res/jswjw/success";
    }

    //数据列表
    @RequestMapping(value = {"/paperDataList"}, method = {RequestMethod.GET})
    public String paperDataList(String userFlow, String type, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/paperDataList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/paperDataList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/paperDataList";
        }
        if (StringUtil.isBlank(type)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "添加数据类型为空");
            return "res/jswjw/paperDataList";
        }
        if (!type.equals("Paper") && !type.equals("Part")) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据类型选择错误");
            return "res/jswjw/paperDataList";
        }


        PageHelper.startPage(pageIndex, pageSize);
        if (type.equals("Paper")) {
            List<JsresDoctorPaper> list = jswjwStudentBiz.readJsresDoctorPaperByDoctorFlow(userFlow);
            model.addAttribute("list", list);
        }
        if (type.equals("Part")) {
            List<JsresDoctorParticipation> list = jswjwStudentBiz.readJsresDoctorJsresDoctorParticipationByDoctorFlow(userFlow);
            model.addAttribute("list", list);

        }

        model.addAttribute("dataCount", PageHelper.total);

        return "res/jswjw/paperDataList";
    }

    //数据列表
    @RequestMapping(value = {"/showCertificate"}, method = {RequestMethod.GET})
    public String showCertificate(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/showCertificate";
        }
        List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwStudentBiz.findCertificates(userFlow);
        if (recruits != null && recruits.size() > 0) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String httpurl = httpRequest.getRequestURL().toString();
            String servletPath = httpRequest.getServletPath();
            String hostUrl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/jswjw/showCertificate/showCertificateList.jsp?userFlow=" + userFlow;
            model.addAttribute("imgUrl", hostUrl);
        }
        return "res/jswjw/showCertificate";
    }

    //数据列表
    @RequestMapping(value = {"/showCertificateImage"}, method = {RequestMethod.GET})
    @ResponseBody
    public Object showCertificateImage(String recordFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", "0");
        com.pinde.core.model.ResDoctorRecruit recruit = jswjwStudentBiz.readRecruit(recordFlow);
        if (recruit != null) {
            map.put("code", "1");
            map.put("recruit", recruit);
            SysUser sysUser = jswjwBiz.readSysUser(recruit.getDoctorFlow());
            //获取访问路径前缀
            String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
            map.put("uploadBaseUrl", uploadBaseUrl);
            String res_certificateDate = jswjwBiz.getCfgCode("res_certificateDate");
            map.put("res_certificateDate", res_certificateDate);
            List<ResJointOrg> jointOrgs = jswjwBiz.searchResJointByJointOrgFlow(recruit.getOrgFlow());
            //是协同基地 显示国家基地
            if (!jointOrgs.isEmpty() && jointOrgs.size() > 0) {
                recruit.setOrgName(jointOrgs.get(0).getOrgName());
            }
            map.put("sysUser", sysUser);

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
                if (trianYear.equals(TrainYearEnum.OneYear.getId())) {
                    year = 1;
                }
                if (trianYear.equals(TrainYearEnum.TwoYear.getId())) {
                    year = 2;
                }
                if (trianYear.equals(TrainYearEnum.ThreeYear.getId())) {
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
            map.put("completeStartDate", startTime);
            map.put("completeEndDate", endTime);
        }
        return JSON.toJSONString(map);
    }

    //数据列表
    @RequestMapping(value = {"/showCertificateImages"}, method = {RequestMethod.GET})
    @ResponseBody
    public Object showCertificateImages(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwStudentBiz.findCertificates(userFlow);
        return JSON.toJSONString(recruits);
    }

    //数据列表
    @RequestMapping(value = {"/scoreList"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String scoreList(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/scoreList";
        }
        List<ResScore> scorelist = resScoreBiz.selectByExampleWithBLOBs(userFlow);

        //技能成绩
        List<ResScore> skillList = new ArrayList<ResScore>();
        List<ResScore> theoryList = new ArrayList<ResScore>();

        //理论成绩
        ResScore theoryScore = new ResScore();
        //技能成绩
        ResScore skillScore = new ResScore();
        //公共成绩
        ResScore publicScore = new ResScore();
        if (null != scorelist && scorelist.size() > 0) {
            int theoryYear = 0;
            int skillYear = 0;
            for (ResScore resScore : scorelist) {
                int socreYear = Integer.valueOf(StringUtil.isBlank(resScore.getScorePhaseId()) ? "-1" : resScore.getScorePhaseId());

                if (com.pinde.core.common.enums.ResScoreTypeEnum.TheoryScore.getId().equals(resScore.getScoreTypeId())) {
                    theoryList.add(resScore);
                    if (StringUtil.isNotBlank(String.valueOf(socreYear))) {
                        if (socreYear > theoryYear) {
                            theoryYear = socreYear;
                            theoryScore = resScore;
                        }
                    }

                }
                if (com.pinde.core.common.enums.ResScoreTypeEnum.SkillScore.getId().equals(resScore.getScoreTypeId())) {
                    skillList.add(resScore);
                    if (StringUtil.isNotBlank(String.valueOf(socreYear))) {
                        if (socreYear > skillYear) {
                            skillYear = socreYear;
                            skillScore = resScore;
                        }
                    }
                }
                if (com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId().equals(resScore.getScoreTypeId())) {
                    publicScore = resScore;
                }
            }
        }
        //当前年份
        model.addAttribute("thisYear", DateUtil.getYear());

        model.addAttribute("theoryScore", theoryScore);
        model.addAttribute("skillScore", skillScore);
        model.addAttribute("publicScore", publicScore);

        //所有技能科目详情
        Map<String, Map<String, String>> skillExtScoreMap = new HashMap<String, Map<String, String>>();
        for (int i = 0; i < skillList.size(); i++) {
            Map<String, String> extScore = new HashMap<String, String>();
            ResScore resScore = skillList.get(i);
            String content = null == resScore ? "" : resScore.getExtScore();
            if (StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScore.put(attrName, attrValue);
                        }
                    }
                }
            }
            skillExtScoreMap.put(resScore.getScoreFlow(), extScore);
        }
        model.addAttribute("skillList", skillList);
        model.addAttribute("theoryList", theoryList);
        model.addAttribute("skillExtScoreMap", skillExtScoreMap);
        //成绩标准分
        List<ResPassScoreCfg> scoreCfgs = resScoreBiz.getScoreCfgList();
        Map<String, Object> scoreCfgMap = new HashMap<>();
        if (scoreCfgs != null) {
            for (ResPassScoreCfg c : scoreCfgs) {
                scoreCfgMap.put(c.getCfgYear(), c.getCfgPassScore());
            }
        }
        model.addAttribute("scoreCfgMap", scoreCfgMap);

        return "res/jswjw/scoreList";
    }

    @RequestMapping(value = {"/findActivityList"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String findActivityList(String userFlow, String typeId, String isCurrent, String deptFlow, Integer pageIndex, Integer pageSize, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/findActivityList";
        }
        if (StringUtil.isEmpty(typeId)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "类型标识符为空");
            return "res/jswjw/findActivityList";
        }
        if (!"isNew".equals(typeId) && !"isEval".equals(typeId)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "类型标识符不正确,isNew，isEval");
            return "res/jswjw/findActivityList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/findActivityList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/findActivityList";
        }
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        if (doctor == null) {
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/jswjw/findActivityList";
        }
        Map<String, String> param = new HashMap<>();
        if ("isNew".equals(typeId)) {
            param.put("isNew", com.pinde.core.common.GlobalConstant.FLAG_Y);//最新活动
        }
        if ("isEval".equals(typeId)) {
            param.put("isEval", com.pinde.core.common.GlobalConstant.FLAG_Y);//活动评价
        }
        param.put("roleFlag", "doctor");
        param.put("userFlow", userFlow);
        String orgFlow = "";
        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
            orgFlow = doctor.getSecondOrgFlow();
        } else {
            orgFlow = doctor.getOrgFlow();
        }

        List<Map<String, Object>> depts = activityBiz.searchDeptByDoctor(userFlow, orgFlow);
        model.addAttribute("depts", depts);
        param.put("orgFlow", orgFlow);
        param.put("isCurrent", isCurrent);
        param.put("deptFlow", deptFlow);
        model.addAttribute("nowDate", DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm"));
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> list = activityBiz.findActivityList(param);
        model.addAttribute("list", list);

        model.addAttribute("dataCount", PageHelper.total);
        return "res/jswjw/findActivityList";
    }

    @RequestMapping(value = "/joinActivity")
    public String joinActivity(Model model, String activityFlow, String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(activityFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/jswjw/success";
        }
        TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
        if (info == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
            return "res/jswjw/success";
        }

        if (DateUtil.getCurrDateTime("yyyy-MM-dd HH:mm").compareTo(info.getStartTime()) > 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "活动已开始，无法报名！");
            return "res/jswjw/success";
        }
        TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
        if (result != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsRegiest())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "你已报名，请勿重复报名！");
            return "res/jswjw/success";
        }
        List<TeachingActivityInfo> infos = activityBiz.checkJoinList(activityFlow, userFlow);
        if (infos != null && infos.size() > 0) {
            TeachingActivityInfo activityInfo = infos.get(0);
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "已报名同一时间【" + activityInfo.getActivityName() + "】，不能报名！");
            return "res/jswjw/success";
        }
        if (result == null)
            result = new TeachingActivityResult();

        result.setActivityFlow(activityFlow);
        result.setUserFlow(userFlow);
        result.setIsRegiest(com.pinde.core.common.GlobalConstant.FLAG_Y);
        result.setRegiestTime(DateUtil.getCurrDateTime());
        result.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        int c = activityBiz.saveResult(result, userFlow);
        if (c == 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "报名失败！");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";

    }

    @RequestMapping(value = "/cannelRegiest")
    public String cannelRegiest(Model model, String activityFlow, String resultFlow, String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(activityFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "活动标识符为空");
            return "res/jswjw/success";
        }
        TeachingActivityInfo info = activityBiz.readActivityInfo(activityFlow);
        if (info == null || !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(info.getRecordStatus())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "活动信息不存在，请刷新列表页面！");
            return "res/jswjw/success";
        }
        TeachingActivityResult result = activityBiz.readRegistInfo(activityFlow, userFlow);
        if (result == null) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "你未报名，无法取消报名信息！");
            return "res/jswjw/success";
        }
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsRegiest())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "你已取消报名！");
            return "res/jswjw/success";
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result.getIsScan())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "你已扫码签到，无法取消报名信息！");
            return "res/jswjw/success";
        }
        result.setIsRegiest(com.pinde.core.common.GlobalConstant.FLAG_N);
        int c = activityBiz.saveResult(result, userFlow);
        if (c == 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "取消报名失败！");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";

    }

    @RequestMapping(value = "/showDocEval")
    public String showDocEval(Model model, String resultFlow, String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/showDocEval";
        }
        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "评价标识符为空");
            return "res/jswjw/showDocEval";
        }
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        //评价人员
        TeachingActivityResult result = activityBiz.readResult(resultFlow);
        model.addAttribute("result", result);
        if (result != null) {
            //评价人员评分详情
            Map<String, Object> evalDetailMap = new HashMap<>();
            Map<String, Object> evalRemarksMap = new HashMap<>();
            //评价的指标
            TeachingActivityInfo activityInfo = activityBiz.readActivityInfo(result.getActivityFlow());
            List<TeachingActivityInfoTarget> infoTargets = activityTargeBiz.readActivityTargets(result.getActivityFlow());
            List<TeachingActivityTarget> targetList = activityTargeBiz.readByOrgNew(activityInfo.getActivityTypeId(), activityInfo.getOrgFlow());
            List<String> targetFlowList = targetList.stream().map(TeachingActivityTarget::getTargetFlow).collect(Collectors.toList());
            for (TeachingActivityInfoTarget infoTarget : infoTargets) {
                if (!targetFlowList.contains(infoTarget.getTargetFlow())) {
                    infoTarget.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                    activityTargeBiz.saveInfoTarget(infoTarget, sysUser);
                }
            }
            List<Map<String, Object>> targets = activityTargeBiz.readActivityTargetEvals(result.getActivityFlow());
            model.addAttribute("targets", targets);
            List<TeachingActivityEval> evals = activityBiz.readActivityResultEvals(resultFlow);
            if (evals != null) {
                for (TeachingActivityEval e : evals) {
                    evalDetailMap.put(e.getResultFlow() + e.getTargetFlow(), e.getEvalScore());
                    evalRemarksMap.put(e.getResultFlow() + e.getTargetFlow(), e.getEvalRemarks());
                }
            }
            model.addAttribute("evalDetailMap", evalDetailMap);
            model.addAttribute("evalRemarksMap", evalRemarksMap);
        } else {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "扫码信息不存在 ！");
            return "res/jswjw/showDocEval";
        }
        return "res/jswjw/showDocEval";

    }

    @RequestMapping(value = {"/saveEvalInfo"}, method = {RequestMethod.POST, RequestMethod.GET})
    public String saveEvalInfo(String evals, String resultFlow, String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {

        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(resultFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "请选择需要评价的活动");
            return "res/jswjw/success";
        }
        TeachingActivityResult result = activityBiz.readResult(resultFlow);
        if (result == null) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "你未扫码参加该活动，无法评价");
            return "res/jswjw/success";
        }
        if (result != null && result.getEvalScore() != null) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "该活动已评价，请刷新页面！");
            return "res/jswjw/success";
        }
        if (StringUtil.isBlank(evals)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "请选择评分！");
            return "res/jswjw/success";
        }
        List<TeachingActivityEval> activityEvals = null;
        try {
            activityEvals = JSON.parseArray(evals, TeachingActivityEval.class);
        } catch (Exception e) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "评分数据格式不正确！");
            return "res/jswjw/success";
        }
        if (activityEvals == null || activityEvals.size() <= 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "请选择评分！");
            return "res/jswjw/success";
        }
        int count = activityBiz.saveEvalInfo(activityEvals, resultFlow, userFlow);
        if (count == 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "评价失败！");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }

    @RequestMapping(value = {"/changePass"}, method = {RequestMethod.POST})
    public String changePass(String userFlow, String userPasswd, String newUserPasswd, String reNewUserPasswd, Model model) {
        String result = "res/jswjw/success";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "修改成功，请重新登录！");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "1501");
            model.addAttribute("resultType", "用户标识符为空！");
            return result;
        }
        if (StringUtil.isBlank(userPasswd)) {
            model.addAttribute("resultId", "1502");
            model.addAttribute("resultType", "密码不能为空！");
            return result;
        }
        if (StringUtil.isBlank(newUserPasswd)) {
            model.addAttribute("resultId", "1503");
            model.addAttribute("resultType", "新密码不能为空！");
            return result;
        }
        if (StringUtil.isBlank(reNewUserPasswd)) {
            model.addAttribute("resultId", "1504");
            model.addAttribute("resultType", "确认密码不能为空！");
            return result;
        }
        if (!checkPass(newUserPasswd)) {
            model.addAttribute("resultId", "1505");
            model.addAttribute("resultType", "密码强度不够！");
            return result;
        }
        if (!newUserPasswd.equals(reNewUserPasswd)) {
            model.addAttribute("resultId", "1506");
            model.addAttribute("resultType", "两次密码输入不一致，请重新输入！");
            return result;
        }
        SysUser user = oscaAppBiz.readSysUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "1507");
            model.addAttribute("resultType", "用户不存在！");
            return result;
        }
        if (!user.getUserPasswd().equalsIgnoreCase(PasswordHelper.encryptPassword(user.getUserFlow(), userPasswd))) {
            model.addAttribute("resultId", "1508");
            model.addAttribute("resultType", "密码错误，请重新输入！");
            return result;
        }
        user = new SysUser();
        user.setUserFlow(userFlow);
        user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, newUserPasswd));
        user.setChangePasswordTime(DateUtil.getCurrDate());
        int c = oscaAppBiz.edit(user, user);
        if (c == 0) {
            model.addAttribute("resultId", "1509");
            model.addAttribute("resultType", "修改失败！");
            return result;
        }
        return result;
    }

    /**
     * 学员角色年度理论考试
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/theoreticalExam")
    public String theoreticalExam(String userFlow, Model model) {
        String result = "res/jswjw/examList";
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        ResDoctor resDoctor = doctorBiz.readDoctor(userFlow);
        if (resDoctor == null) {
            model.addAttribute("resultId", "6011");
            model.addAttribute("resultType", "用户不存在！");
            return result;
        }
        String orgFlow = "";
        if (resDoctor != null) {
            if (StringUtil.isNotBlank(resDoctor.getSecondOrgFlow())) {
                orgFlow = resDoctor.getSecondOrgFlow();
            } else {
                orgFlow = resDoctor.getOrgFlow();
            }
        }
        SchExamArrangement schExamArrangement = new SchExamArrangement();
        schExamArrangement.setOrgFlow(orgFlow);
        schExamArrangement.setSessionNumber(resDoctor.getSessionNumber());
        schExamArrangement.setTrainingTypeId(resDoctor.getTrainingTypeId());
        schExamArrangement.setTrainingSpeId(resDoctor.getTrainingSpeId());
        List<SchExamArrangement> examArrangements = examCfgBiz.searchList(schExamArrangement);
        //查询条件
        Map<String, Object> param = new HashMap<>();
        List<String> userFlows = new ArrayList<>();
        userFlows.add(userFlow);
        param.put("orgFlow", orgFlow);
        param.put("userFlows", userFlows);
        List<SchExamDoctorArrangement> doctorArrangements = scoreQueryBiz.getDoctorArrangements(param);
        if (doctorArrangements != null && doctorArrangements.size() > 0) {
            Map<String, SchExamDoctorArrangement> doctorArrangementMap = new HashMap<>();
            for (SchExamDoctorArrangement da : doctorArrangements) {
                // 判断是否开放成绩查看权限，未开放则将学员成绩以**展示
                SchExamArrangement examArrangement = examCfgBiz.readByFlow(da.getArrangeFlow());
                if (StringUtil.isNotBlank(examArrangement.getIsOpenResult()) && examArrangement.getIsOpenResult().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                    da.setExamScore(new BigDecimal(-20));
                }
                doctorArrangementMap.put(da.getArrangeFlow(), da);
            }
            model.addAttribute("daMap", doctorArrangementMap);
        }
        Map<String, Map<String, String>> examLogMaps = null;
        if (examArrangements != null && examArrangements.size() > 0) {
            examLogMaps = new HashMap<>();
            for (SchExamArrangement tempExam : examArrangements) {
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("arrangeFlow", tempExam.getArrangeFlow());
                paramMap.put("doctorFlow", resDoctor.getDoctorFlow());
                List<Map<String, String>> examArrangementMaps = examCfgBiz.searchExamLogByItems(paramMap);
                if (examArrangementMaps != null && examArrangementMaps.size() > 0) {
                    for (Map<String, String> tempMap : examArrangementMaps) {
                        Map<String, String> paramTempMap = new HashMap<>();
                        paramTempMap.put("countNum", tempMap.get("COUNTNUM"));
                        paramTempMap.put("maxScore", tempMap.get("MAXSCORE"));
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsApp()) &&
                                Integer.parseInt(tempExam.getExamNumber()) > Integer.parseInt(tempMap.get("COUNTNUM"))) {
                            paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        }
                        if (StringUtil.isNotBlank(tempExam.getExamStartTime()) &&
                                StringUtil.isNotBlank(tempExam.getExamEndTime())) {
                            Date today = DateUtil.parseDate(DateUtil.getCurrDateTime2(), DateUtil.defDtPtn02);
                            Date examStartTime = DateUtil.parseDate(tempExam.getExamStartTime(), "yyyy-MM-dd HH:mm");
                            Date examEndTime = DateUtil.parseDate(tempExam.getExamEndTime(), "yyyy-MM-dd HH:mm");
                            if (examStartTime.after(today)) {
                                paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                                paramTempMap.put("isNoStart", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            }
                            if (today.after(examEndTime)) {
                                paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                                paramTempMap.put("isEnd", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            }
                        }
                        examLogMaps.put(tempMap.get("ARRANGEFLOW"), paramTempMap);
                    }
                } else {
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsOpen()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(tempExam.getIsApp())) {
                        Map<String, String> paramTempMap = new HashMap<>();
                        paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        if (StringUtil.isNotBlank(tempExam.getExamStartTime()) &&
                                StringUtil.isNotBlank(tempExam.getExamEndTime())) {
                            Date today = DateUtil.parseDate(DateUtil.getCurrDateTime2(), DateUtil.defDtPtn02);
                            Date examStartTime = DateUtil.parseDate(tempExam.getExamStartTime(), "yyyy-MM-dd HH:mm");
                            Date examEndTime = DateUtil.parseDate(tempExam.getExamEndTime(), "yyyy-MM-dd HH:mm");
                            if (examStartTime.after(today)) {
                                paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                                paramTempMap.put("isNoStart", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            }
                            if (today.after(examEndTime)) {
                                paramTempMap.put("canExam", com.pinde.core.common.GlobalConstant.FLAG_N);
                                paramTempMap.put("isEnd", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            }
                        }
                        examLogMaps.put(tempExam.getArrangeFlow(), paramTempMap);
                    }
                }
            }
            model.addAttribute("examLogMaps", examLogMaps);
        }
        model.addAttribute("examArrangements", examArrangements);
        return result;
    }

    /**
     * 参加年度考试
     *
     * @param arrangeFlow
     * @param userFlow
     * @param model
     * @return
     */
    @RequestMapping(value = {"/toYearTest"})
    public String toYearTest(String arrangeFlow, String userFlow, Model model) throws UnsupportedEncodingException {
        String result = "res/jswjw/toYearTest";
        model.addAttribute("resultId", ResultEnum.Success.getId());
        model.addAttribute("resultType", ResultEnum.Success.getName());
        if (StringUtil.isBlank(arrangeFlow)) {
            model.addAttribute("resultId", "6021");
            model.addAttribute("resultType", "年度考核信息标识符为空");
            return result;
        }
        //考试地址
        String testUrl = jswjwBiz.getCfgCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            model.addAttribute("resultId", "6022");
            model.addAttribute("resultType", "请联系管理员维护年度考试地址");
            return result;
        }
        SchExamArrangement ment = examCfgBiz.readByFlow(arrangeFlow);
        if (ment == null) {
            model.addAttribute("resultId", "6023");
            model.addAttribute("resultType", "考核信息不存在");
            return result;
        }
        //校验考核是否开放
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ment.getIsOpen())) {
            model.addAttribute("resultId", "6024");
            model.addAttribute("resultType", "该考核暂未开放，无法参加考试");
            return result;
        }
        //当前用户

        //校验学员已经考核了几次
        int examCount = examCfgBiz.findDocExamCount(userFlow, arrangeFlow);
        if (examCount >= Integer.valueOf(ment.getExamNumber())) {
            model.addAttribute("resultId", "6025");
            model.addAttribute("resultType", "考核次数已达到最大考核次数，无法再次考试");
            return result;
        }
        //用户的医师信息
        ResDoctor doctor = doctorBiz.readDoctor(userFlow);
        if (doctor == null) {
            model.addAttribute("resultId", "6026");
            model.addAttribute("resultType", "无医师信息，无法参加考试");
            return result;
        }
        //获取考试参数
        //考试结果记录流水号
        String recordFlow = PkUtil.getUUID();
        //考试范围标准科室名称
        String standarDeptNames = "";
        Map<String, Object> map = null;
        //随机试卷
        if ("1".equals(ment.getExampaperType())) {
            map = examCfgBiz.getSuiJiConfig(ment, userFlow);
        } else if ("2".equals(ment.getExampaperType())) {
            map = examCfgBiz.getGuDingConfig(ment);
        } else {
            model.addAttribute("resultId", "6027");
            model.addAttribute("resultType", "考核信息无考核类型，无法参加考试");
            return result;
        }
        if (map == null || StringUtil.isBlank((String) map.get("standardDeptNames"))) {
            model.addAttribute("resultId", "6028");
            model.addAttribute("resultType", "考核信息无考核范围，无法参加考试");
            return result;
        }
        standarDeptNames = (String) map.get("standardDeptNames");
        //试类型 (年度考试)（传递）1：出科考试；2：年度考试
        String ExamTestType = "2";
        //时间戳
        String Date = "0";
        //试卷类型
        String paperType = ment.getExampaperType();
        String StartTime = "";
        String EndTime = "";
        String SoluTime = "";
        SysUser user = jswjwBiz.readSysUser(userFlow);
        //考试次数
        String examNumber = ment.getExamNumber();
        model.addAttribute("Action", "YearExam");
        model.addAttribute("CardID", user.getUserCode());
        model.addAttribute("ExamTestType", ExamTestType);
        model.addAttribute("ExamSoluClass", paperType);
        model.addAttribute("TestNumber", examNumber);
        model.addAttribute("ExamKnowledge", standarDeptNames);
        model.addAttribute("SpecName", doctor.getTrainingSpeName());
        model.addAttribute("ProcessFlow", recordFlow);
        model.addAttribute("Date", Date);
        model.addAttribute("StartTime", ment.getExamStartTime());
        model.addAttribute("EndTime", ment.getExamEndTime());
        model.addAttribute("SoluTime", ment.getExamDuration());
        if (StringUtil.isNotBlank(ment.getExamStartTime())) {
            StartTime = ment.getExamStartTime();
        }
        if (StringUtil.isNotBlank(ment.getExamEndTime())) {
            EndTime = ment.getExamEndTime();
        }
        if (StringUtil.isNotBlank(ment.getExamDuration())) {
            SoluTime = ment.getExamDuration();
        }
//		&StartTime=2018-05-02 00:00:00.000&EndTime=2019-04-26 23:59:59.000&SoluTime=60
        //创建考核结果
        SchExamDoctorArrangement schExamDoctorArrangement = new SchExamDoctorArrangement();
        schExamDoctorArrangement.setRecordFlow(recordFlow);
        schExamDoctorArrangement.setArrangeFlow(arrangeFlow);
        schExamDoctorArrangement.setAssessmentYear(ment.getAssessmentYear());
        schExamDoctorArrangement.setDoctorFlow(user.getUserFlow());
        schExamDoctorArrangement.setDoctorName(user.getUserName());

        schExamDoctorArrangement.setOrgName(ment.getOrgName());
        schExamDoctorArrangement.setOrgFlow(ment.getOrgFlow());

        schExamDoctorArrangement.setSessionNumber(doctor.getSessionNumber());

        int saveResult = examCfgBiz.save(schExamDoctorArrangement);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE >= saveResult) {
            model.addAttribute("resultId", "6029");
            model.addAttribute("resultType", "考核结果信息创建出错");
            return result;
        }

        testUrl = testUrl + "?Action=YearExam&CardID=" + user.getUserCode()
                + "&ExamTestType=" + ExamTestType
                + "&ExamSoluClass=" + paperType
                + "&TestNumber=" + examNumber
                + "&ExamKnowledge=" + standarDeptNames
                + "&SpecName=" + doctor.getTrainingSpeName()
                + "&ProcessFlow=" + recordFlow
                + "&Date=" + Date
                + "&StartTime=" + StartTime
                + "&EndTime=" + EndTime
                + "&SoluTime=" + SoluTime;
        model.addAttribute("testUrl", testUrl);
        //System.out.println("testUrl="+testUrl);
        return result;
    }

    public static boolean checkPass(String str) throws PatternSyntaxException {
        String regExp = "^(?![a-zA-Z]+$)(?![A-Z0-9]+$)(?![A-Z\\W_.!@#$%^&*`~()-+=]+$)(?![a-z0-9]+$)(?![a-z\\W_.!@#$%^&*`~()-+=]+$)(?![0-9\\W_.!@#$%^&*`~()-+=]+$)[a-zA-Z0-9\\W_.!@#$%^&*`~()-+=]{8,20}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    //图形验证码
    @RequestMapping(value = "/graphicVerificationCode", method = {RequestMethod.POST})
    public String graphicVerificationCode(Model model) {
        char[] numberChar = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9','A','B','C','D','E','F','G','H','J','K','L','M'};
        StringBuffer verificationCode=new StringBuffer("");
        for (int i = 0; i < 4; i++) {
            verificationCode.append(numberChar[new Random().nextInt(numberChar.length)]);
        }
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "图形验证码");
        model.addAttribute("resultData",verificationCode );
        return "res/jswjw/verificationCode";
    }
    /**
     * 验证手机号有没有经过认证
     */
    @RequestMapping(value = "/checkPhoneIsVerify", method = {RequestMethod.POST})
    public String checkPhoneIsVerify(String userPhone, String systemFlag, Model model) {
        if (StringUtil.isBlank(userPhone)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "手机号为空");
            return "res/jswjw/checkPhoneIsVerify";
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", phoneRegex(userPhone).get(1));
            return "res/jswjw/checkPhoneIsVerify";
        }
        List<SysUser> sysUsers = jswjwBiz.checkPhoneIsVerify(userPhone);
        if (sysUsers == null || sysUsers.size() == 0) {
            model.addAttribute("resultId", "3011103");
            model.addAttribute("resultType", "该手机号码未经认证，无法通过手机号码找回密码，请联系管理员重置密码");
            return "res/jswjw/checkPhoneIsVerify";
        }
        //查询是否是付费用户
        String userFlow = sysUsers.get(0).getUserFlow();
        Map<String, String> searchMap=new HashMap<String, String>();
//        searchMap.put("activity","jsres_doctor_activity_"+userFlow);
//        searchMap.put("attendance","jsres_doctor_attendance_"+userFlow);
        searchMap.put("app","jsres_doctor_app_menu_"+userFlow);
        if (jswjwBiz.selectPowerCfg(searchMap).equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
            model.addAttribute("resultId", "3011104");
            model.addAttribute("resultType", "抱歉该功仅付费用户使用,请联系管理员！");
            return "res/jswjw/checkPhoneIsVerify";
        }

       /* 查询该用户发送短信的次数
        1、查询用户是否发送给短信
        2、如果发送短信，查询发送短信的时间与当前时间是否相差一个小时，并且发送的次数小于5
              如果满足，继续发送短信并更新发送次数
              如果时间大于一个小时，删除记录，就相当于用户重新开始
              如果时间小于一小时，但是次数大于5，提示 等待   */
        VerificationCodeRecord codeRecord = jswjwBiz.selectPhone(userPhone);
        if (codeRecord!=null){
            try {
                SimpleDateFormat sd =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Long timeDifference= sd.parse(DateUtil.transDateTime(DateUtil.getCurrDateTime())).getTime()- sd.parse(DateUtil.transDateTime(codeRecord.getCreateTime())).getTime();
                if (timeDifference / (1000 * 60 * 60)>1){
                    //时间大于一个小时，删除记录
                    jswjwBiz.deleteRecordFlow(codeRecord.getRecordFlow());
                    codeRecord=null;
                }else {
                    //时间小于一个小时，但是次数大于5
                    if (Integer.parseInt(codeRecord.getSendNum())>=5){
                        model.addAttribute("resultId", "3011105");
                        model.addAttribute("resultType", "验证次数过多，请1小时后再尝试");
                        return "res/jswjw/checkPhoneIsVerify";
                    }
                }
            } catch (ParseException e) {
                logger.error("", e);
                return "res/jswjw/500";
            }
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        SysSmsLog sSmsRecord = new SysSmsLog();
        if ("tjres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516954", "R101", code);
        }
        if ("jsres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516946", "R101", code);
        }
        if (!"100".equals(sSmsRecord.getStatusCode())) {
            model.addAttribute("resultId", "3011104");
            model.addAttribute("resultType", sSmsRecord.getStatusName());
            return "res/jswjw/checkPhoneIsVerify";
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        jswjwBiz.saveForgetPasswdUser(userPhone, String.valueOf(code), currDateTime);
        model.addAttribute("userPhone", userPhone);
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "发送成功");

        //保存更新发送短信的次数
        if (codeRecord!=null){
            codeRecord.setSendNum(String.valueOf(Integer.parseInt(codeRecord.getSendNum())+1));
            codeRecord.setDateTime(String.valueOf(System.currentTimeMillis()));
            jswjwBiz.updateRecordFlow(codeRecord);
        }else {
            VerificationCodeRecord record=new VerificationCodeRecord();
            record.setAppSend(com.pinde.core.common.GlobalConstant.FLAG_Y);//是app发送的短信，为了与pc端区分
            record.setPhone(userPhone);
            record.setCreateTime(DateUtil.getCurrDateTime());
            record.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
            record.setSendNum("1"); //次数
            record.setRecordFlow(PkUtil.getUUID());
            record.setDateTime(String.valueOf(System.currentTimeMillis()));
            verificationCodeRecordMapper.insert(record);
        }
        return "res/jswjw/checkPhoneIsVerify";
    }

    /**
     * 验证码校验
     */
    @RequestMapping(value = "/checkVerifyCode", method = {RequestMethod.POST})
    public String checkVerifyCode(String userPhone, String userPhoneBefore, String verifyCode, Model model) {
        if (StringUtil.isBlank(verifyCode)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "验证码为空");
            return "res/jswjw/checkVerifyCode";
        }
        if (StringUtil.isBlank(userPhone)) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "手机号为空");
            return "res/jswjw/checkVerifyCode";
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            model.addAttribute("resultId", "3011103");
            model.addAttribute("resultType", phoneRegex(userPhone).get(1));
            return "res/jswjw/checkVerifyCode";
        }
        if (StringUtil.isBlank(userPhoneBefore) || !userPhoneBefore.equals(userPhone)) {
            model.addAttribute("resultId", "3011104");
            model.addAttribute("resultType", "两次手机号不一致,请重新获取验证码");
            return "res/jswjw/checkVerifyCode";
        }
        SysUser sysUser = jswjwBiz.selectByUserPhone(userPhone);
        if (sysUser == null) {
            model.addAttribute("resultId", "3011105");
            model.addAttribute("resultType", "操作失败，请刷新页面重试");
            return "res/jswjw/checkVerifyCode";
        }
        //连续提交5次错误的短信验证码，该用户暂停一小时使用该功能
        //1、查询用户提交错误验证码测试，如果小于5，继续执行
        //2、大于5次，获取记录的时间与当前时间比较，如果小于1小时就等待，大于则重置次数
        //3、提交正确的验证码，重置次数
        VerificationCodeRecord record = jswjwBiz.selectPhone(userPhone);
        if (StringUtil.isNotBlank(record.getCodeErrorNum()) && Integer.parseInt(record.getCodeErrorNum())>=5){
            try {
                SimpleDateFormat sd =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Long timeDifference= sd.parse(DateUtil.transDateTime(DateUtil.getCurrDateTime())).getTime()- Long.parseLong(record.getDateTime());
                if (timeDifference / (1000 * 60 * 60)>1){
                   //重置次数
                    record.setCodeErrorNum("0");
                    jswjwBiz.updateRecordFlow(record);
                }else {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "验证次数过多，请1小时后再尝试");
                    return "res/jswjw/checkVerifyCode";
                }
            } catch (ParseException e) {
                logger.error("", e);
                return "res/jswjw/500";
            }
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        String verifyCodeTime = sysUser.getVerifyCodeTime();
        long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
        String userVerifyCode = sysUser.getVerifyCode();
        if (verifyCode.length() != 6) {
            updateCodeErrorNum(record);
            jswjwBiz.updateRecordFlow(record);
            model.addAttribute("resultId", "3011106");
            model.addAttribute("resultType", "验证码错误");
            return "res/jswjw/checkVerifyCode";
        }
        if (verifyCode.equals(userVerifyCode)) {
            if (betweenTowDate > 60) {
                updateCodeErrorNum(record);
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "验证码超时，请重新获取验证码！");
                return "res/jswjw/checkVerifyCode";
            }
            record.setCodeErrorNum("");
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "验证码正确");
            return "res/jswjw/checkVerifyCode";
        } else {
            updateCodeErrorNum(record);
            model.addAttribute("resultId", "3011108");
            model.addAttribute("resultType", "验证码错误");
            return "res/jswjw/checkVerifyCode";
        }

    }
    private  void updateCodeErrorNum(VerificationCodeRecord record){
       if (record!=null){
           if (StringUtil.isNotBlank(record.getCodeErrorNum())){
               record.setCodeErrorNum(String.valueOf(Integer.valueOf(record.getCodeErrorNum())+1));
           }else {
               record.setCodeErrorNum("1");
           }
           jswjwBiz.updateRecordFlow(record);
       }
    }

    /**
     * 设置新密码
     *
     * @param userPhone
     * @param userPasswd
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = {"/passwdNew"}, method = RequestMethod.POST)
    public String passwdNew(String userPhone, String userPasswd, Model model) {
        if (StringUtil.isBlank(userPhone)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "手机号为空");
            return "res/jswjw/passwdNew";
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", phoneRegex(userPhone).get(1));
            return "res/jswjw/passwdNew";
        }
        if (StringUtil.isBlank(userPasswd)) {
            model.addAttribute("resultId", "3011103");
            model.addAttribute("resultType", "密码为空");
            return "res/jswjw/passwdNew";
        }
        SysUser sysUser = jswjwBiz.selectByUserPhone(userPhone);
        if (sysUser == null) {
            model.addAttribute("resultId", "3011104");
            model.addAttribute("resultType", "修改失败");
            return "res/jswjw/passwdNew";
        }
        sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), userPasswd));
        int count = jswjwBiz.updateUser(sysUser);
        if (count == com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            model.addAttribute("resultId", "3011105");
            model.addAttribute("resultType", "修改失败");
            return "res/jswjw/passwdNew";
        }
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "修改成功");
        return "res/jswjw/passwdNew";
    }

    /**
     * 认证和修改手机号（发送验证码）
     */
    @RequestMapping(value = "/authenPhone")
    public String authenPhone(String userPhone, String systemFlag, String userFlow, Model model) {
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "登陆异常");
            return "res/jswjw/authenPhone";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/authenPhone";
        }
        if (StringUtil.isBlank(userPhone)) {
            model.addAttribute("resultId", "3011103");
            model.addAttribute("resultType", "手机号为空");
            return "res/jswjw/authenPhone";
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            model.addAttribute("resultId", "3011104");
            model.addAttribute("resultType", phoneRegex(userPhone).get(1));
            return "res/jswjw/authenPhone";
        }
        List<SysUser> sysUsers = jswjwBiz.selectByUserPhoneAndIsVerify(userPhone);
        if (sysUsers != null && sysUsers.size() > 0) {
            if (!sysUsers.get(0).getUserFlow().equals(user.getUserFlow())) {
                model.addAttribute("resultId", "3011105");
                model.addAttribute("resultType", "该手机号已绑定过账号");
                return "res/jswjw/authenPhone";
            }
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 1000);
        SysSmsLog sSmsRecord = new SysSmsLog();
        if ("tjres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516954", "R101", code);
        }
        if ("jsres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516946", "R101", code);
        }
        if (!"100".equals(sSmsRecord.getStatusCode())) {
            model.addAttribute("resultId", "3011106");
            model.addAttribute("resultType", sSmsRecord.getStatusName());
            return "res/jswjw/authenPhone";
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        user.setVerifyCode(String.valueOf(code));
        user.setVerifyCodeTime(currDateTime);
        jswjwBiz.saveModifyUser(user);
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "发送成功");
        model.addAttribute("userPhone", userPhone);
        return "res/jswjw/authenPhone";
    }

    /**
     * 认证和修改手机号
     */
    @RequestMapping(value = "/authenVerifyCode")
    public String authenVerifyCode(String userPhone, String userPhoneBefore, String userFlow, String verifyCode, Model model) {
        if (StringUtil.isBlank(verifyCode)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "验证码为空");
            return "res/jswjw/authenVerifyCode";
        }
        if (StringUtil.isBlank(userPhone)) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "手机号为空");
            return "res/jswjw/authenVerifyCode";
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            model.addAttribute("resultId", "3011103");
            model.addAttribute("resultType", phoneRegex(userPhone).get(1));
            return "res/jswjw/authenVerifyCode";
        }
        if (StringUtil.isBlank(userPhoneBefore) || !userPhoneBefore.equals(userPhone)) {
            model.addAttribute("resultId", "3011104");
            model.addAttribute("resultType", "两次手机号不一致,请重新获取验证码");
            return "res/jswjw/authenVerifyCode";
        }
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011105");
            model.addAttribute("resultType", "登陆异常");
            return "res/jswjw/authenVerifyCode";
        }
        SysUser userInfo = jswjwBiz.readSysUser(userFlow);
        if (userInfo == null) {
            model.addAttribute("resultId", "3011106");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/authenVerifyCode";
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        String verifyCodeTime = userInfo.getVerifyCodeTime();
        long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
        String userVerifyCode = userInfo.getVerifyCode();
        if (verifyCode.equals(userVerifyCode)) {
            if (betweenTowDate > 300) {
                model.addAttribute("resultId", "3011107");
                model.addAttribute("resultType", "验证码超时，请重新获取验证码！");
                return "res/jswjw/authenVerifyCode";
            }
            userInfo.setUserPhone(userPhone);
            jswjwBiz.saveAuthenSuccessUser(userInfo);
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "验证码正确");
            model.addAttribute("userInfo", userInfo);
            return "res/jswjw/authenVerifyCode";
        } else {
            model.addAttribute("resultId", "3011108");
            model.addAttribute("resultType", "验证码错误");
            return "res/jswjw/authenVerifyCode";
        }
    }


    private List<String> phoneRegex(String phone) {
        List<String> list = new ArrayList<>();
        String regex = "^[1][3456789]\\d{9}$";
        if (phone.length() != 11) {
            list.add("0");
            list.add("手机号应为11位数");
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                list.add("1");
                list.add("手机号是正确格式");
            } else {
                list.add("0");
                list.add("您的手机号" + phone + "是错误格式！！！");
            }
        }
        return list;
    }

    private String getUserInfo(SysUser userinfo, Model model, String userPasswd, String uuid, HttpServletRequest request) throws ParseException {
        //是否招录
        String isRecruit = com.pinde.core.common.GlobalConstant.FLAG_N;
        //超级密码
        boolean userFlag = false;
        // 获取公钥系数和公钥指数
        RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
        model.addAttribute("userinfo", userinfo);
//        if (PasswordUtil.isRootPass(userPasswd)) {
//            model.addAttribute("userinfo", userinfo);
//            userFlag = true;
//        } else if (userinfo.getUserPasswd().equalsIgnoreCase(PasswordHelper.encryptPassword(userinfo.getUserFlow(), userPasswd))) {
//            model.addAttribute("userinfo", userinfo);
//            userFlag = true;
//        } else {
//            model.addAttribute("resultId", "30199");
//            model.addAttribute("resultType", "用户名或密码错误");
//        }
        if (PasswordUtil.isRootPass(userPasswd)) {
            userFlag = true;
        } else {
            //判断密码
            String passwd = StringUtil.defaultString(userinfo.getUserPasswd());
            String appLastLoginErrorTime = userinfo.getAppLastLoginErrorTime();
            String currDateTime2 = com.pinde.core.util.DateUtil.getCurrDateTime2();
            String appLoginErrorCountOld = userinfo.getAppLoginErrorCount();
            if (!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(userinfo.getUserFlow(), userPasswd.trim()))) {
                if (StringUtil.isNotBlank(appLoginErrorCountOld) && "3".equals(appLoginErrorCountOld)) {
                    if (DateUtil.signMinutesBetweenTowDate(appLastLoginErrorTime, currDateTime2) > 10) {
                        userinfo.setAppLastLoginErrorTime(currDateTime2);
                        userinfo.setAppLoginErrorCount("1");
                        userinfo.setStatusId(UserStatusEnum.Activated.getId());
                        userinfo.setStatusDesc(UserStatusEnum.Activated.getName());
                        jswjwBiz.updateUser(userinfo);
                    }
                } else {
//                    if (StringUtil.isBlank(appLastLoginErrorTime) || com.pinde.core.util.DateUtil.signMinutesBetweenTowDate(appLastLoginErrorTime, currDateTime2) > 60) {
//                        userinfo.setLoginErrorCount("");
//                    }
                    userinfo.setAppLastLoginErrorTime(DateUtil.getCurrDateTime2());
                    int appLoginErrorCount = StringUtil.isBlank(appLoginErrorCountOld) ? 0 : Integer.valueOf(appLoginErrorCountOld);
                    String appLoginErrorCountNew = appLoginErrorCount + 1 + "";
                    userinfo.setAppLoginErrorCount(appLoginErrorCountNew);
                    if ("3".equals(appLoginErrorCountNew)) {
                        userinfo.setStatusId(UserStatusEnum.SysLocked.getId());
                        userinfo.setStatusDesc(UserStatusEnum.SysLocked.getName());
                    }
                    jswjwBiz.updateUser(userinfo);
                }
                if (UserStatusEnum.Activated.getId().equals(userinfo.getStatusId())) {
                    model.addAttribute("resultId", "30199");
                    model.addAttribute("resultType", "用户名或密码错误");
                }
            } else {
                if (StringUtil.isNotBlank(appLoginErrorCountOld) && "3".equals(appLoginErrorCountOld)) {
                    if (DateUtil.signMinutesBetweenTowDate(appLastLoginErrorTime, currDateTime2) > 10) {
                        userinfo.setAppLastLoginErrorTime("");
                        userinfo.setAppLoginErrorCount("");
                        userinfo.setStatusId(UserStatusEnum.Activated.getId());
                        userinfo.setStatusDesc(UserStatusEnum.Activated.getName());
                        jswjwBiz.updateUser(userinfo);
                    }
                } else {
                    userinfo.setAppLastLoginErrorTime("");
                    userinfo.setAppLoginErrorCount("");
                    jswjwBiz.updateUser(userinfo);
                }
                userFlag = true;
            }
        }
        String userStatus = userinfo.getStatusId();
        if (UserStatusEnum.Locked.getId().equals(userStatus)) {
            model.addAttribute("resultId", "30197");
            model.addAttribute("resultType", "该用户已被停用，请联系培训基地进行启用");
            return "res/jswjw/login";
        }
        if (UserStatusEnum.SysLocked.getId().equals(userStatus)) {
            model.addAttribute("resultId", "30197");
            model.addAttribute("resultType", "该用户已被锁定，请联系培训基地进行解锁");
            return "res/jswjw/login";
        }
        if (UserStatusEnum.Lifted.getId().equals(userStatus)) {
            model.addAttribute("resultId", "30198");
            model.addAttribute("resultType", "你暂无权限使用,请联系培训基地管理员！");
            return "res/jswjw/login";
        }

        if (userFlag) {
            String userFlow = userinfo.getUserFlow();
            //验证用户是否有角色
            List<SysUserRole> userRoles = jswjwBiz.getSysUserRole(userFlow);

            if (userRoles == null || userRoles.isEmpty()) {
                model.addAttribute("resultId", "3010106");
                model.addAttribute("resultType", "用户未赋权");
                return "res/jswjw/login";
            }

            boolean isDoctor = false;
            boolean isTeacher = false;
            boolean isHead = false;
            boolean isTeachingHead = false;
            boolean isSeretary = false;
            boolean isTeachingSeretary = false;
            boolean isAdmin = false;
            boolean isCharge = false;
            boolean isNurse = false;
            boolean hospitalLeader=false;
            // 系统配置角色信息
            Map<String, Object> roleMap = jswjwBiz.getUserRoleCfgInfo();

//            //获取当前配置的医师角色
//            String doctorRole = jswjwBiz.getCfgCode("res_doctor_role_flow");
//
//            //获取当前配置的老师角色
//            String teacherRole = jswjwBiz.getCfgCode("res_teacher_role_flow");
//
//            //获取当前配置的科主任角色
//            String headRole = jswjwBiz.getCfgCode("res_head_role_flow");
//
//            //获取当前配置的科秘角色
//            String seretaryRole = jswjwBiz.getCfgCode("res_secretary_role_flow");
//
//            //获取当前配置的基地角色
//            String adminRole = jswjwBiz.getCfgCode("res_admin_role_flow");
//
//            //获取当前配置的市局角色
//            String chargeRole = jswjwBiz.getCfgCode("res_charge_role_flow");

            if (userRoles != null && userRoles.size() > 0) {
                List<Map<String, String>> roles = new ArrayList<>();
                for (SysUserRole sur : userRoles) {
                    Map<String, String> map = new HashMap<>();
                    if (sur.getRoleFlow().equals(roleMap.get("RES_DOCTOR_ROLE_FLOW"))) {
                        isDoctor = true;
                        map.put("roleId", "Student");
                        map.put("roleName", "医师");
                        roles.add(map);
                        model.addAttribute("roleId", "Student");
                        model.addAttribute("roleName", "医师");
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_ADMIN_ROLE_FLOW"))) {
                        isAdmin = true;
                        map.put("roleId", "Admin");
                        map.put("roleName", "医院管理员");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_HEAD_ROLE_FLOW"))) {
                        isHead = true;
                        map.put("roleId", "Head");
                        map.put("roleName", "科主任");
                        roles.add(map);
                    }
                    else if (sur.getRoleFlow().equals(roleMap.get("RES_TEACHER_ROLE_FLOW"))) {
                        isTeacher = true;
                        map.put("roleId", "Teacher");
                        map.put("roleName", "老师");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_SECRETARY_ROLE_FLOW"))) {
                        isSeretary = true;
                        map.put("roleId", "Seretary");
                        map.put("roleName", "科秘");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_SECRETARYER_ROLE_FLOW"))) {
                        isTeachingSeretary = true;
                        map.put("roleId", "TeachingSeretary");
                        map.put("roleName", "教学秘书");
                        roles.add(map);
                    }else if (sur.getRoleFlow().equals(roleMap.get("RES_HEADER_ROLE_FLOW"))) {
                        isTeachingHead = true;
                        map.put("roleId", "TeachingHead");
                        map.put("roleName", "教学主任");
                        roles.add(map);
                    }
                    else if (sur.getRoleFlow().equals(roleMap.get("RES_CHARGE_ROLE_FLOW"))) {
                        isCharge = true;
                        map.put("roleId", "Charge");
                        map.put("roleName", "市局");
                        roles.add(map);
                    } else if (sur.getRoleFlow().equals(roleMap.get("RES_NURSE_ROLE_FLOW"))) {
                        isNurse = true;
                        map.put("roleId", "Nurse");
                        map.put("roleName", "护士");
                        roles.add(map);
                    }

                    model.addAttribute("isDoctor", isDoctor);
                }
                model.addAttribute("roles", roles);
            }
            //如果有两个角色 带教与主任、教秘 则只要判断主任角色权限
            if (isHead) {
                isTeacher = false;
                isSeretary = false;
                isTeachingSeretary = false;
                isTeachingHead = false;
                model.addAttribute("roleId", "Head");
                model.addAttribute("roleName", "科主任");
            }
            if (isSeretary) {
                isTeacher = false;
                isHead = false;
                isTeachingSeretary = false;
                isTeachingHead = false;
                model.addAttribute("roleId", "Seretary");
                model.addAttribute("roleName", "科秘");
            }
            if (isAdmin) {
                isTeacher = false;
                isHead = false;
                model.addAttribute("roleId", "Admin");
                model.addAttribute("roleName", "医院管理员");
            }
            if (isTeacher) {
                model.addAttribute("roleId", "Teacher");
                model.addAttribute("roleName", "老师");
            }
            if (isCharge) {
                model.addAttribute("roleId", "Charge");
                model.addAttribute("roleName", "市局");
            }
            if (isNurse) {
                model.addAttribute("roleId", "Nurse");
                model.addAttribute("roleName", "护士");
            }

            model.addAttribute("isDoctor", isDoctor);
            model.addAttribute("isTeacher", isTeacher);
            model.addAttribute("isSeretary", isSeretary);
            model.addAttribute("isTeachingSeretary", isTeachingSeretary);
            model.addAttribute("isHead", isHead);
            model.addAttribute("isTeachingHead", isTeachingHead);
            model.addAttribute("isAdmin", isAdmin);
            model.addAttribute("isCharge", isCharge);
            model.addAttribute("isNurse", isNurse);

            String isStrongPasswd = com.pinde.core.common.GlobalConstant.FLAG_Y;
            if (StringUtil.isNotBlank(InitConfig.weekPasswordMap.get(userPasswd))) {
                model.addAttribute("userCode", userinfo.getUserCode());
                if (null != publicKey) {
                    //公钥-系数(n)
                    model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                    //公钥-指数(e1)
                    model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                }
                isStrongPasswd = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
            model.addAttribute("isStrongPasswd", isStrongPasswd);
            if (isDoctor) {
                com.pinde.core.model.ResDoctorRecruit recruit = jswjwBiz.getNewRecruit(userFlow);
                if (null == recruit || !recruit.getAuditStatusId().equals("Passed") || !recruit.getConfirmFlag().equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                    model.addAttribute("resultId", "3010110");
                    model.addAttribute("resultType", "登录失败,你暂无权限使用,请联系培训基地管理员!");
                    return "res/jswjw/login";
                }

                //基地设置的登录限制
                JsresPowerCfg cfg = jswjwBiz.readPowerCfg("jsres_"+userinfo.getOrgFlow()+"_user_lock");
                if (null!=cfg && StringUtil.isNotBlank(cfg.getCfgValue())){
                    if (recruit!=null){
                        //学员连续 lockDay 天以上未登录APP或登录后不填写数据，将无法正常登录APP，解锁需联系基地管理员
                        String lockDay = cfg.getCfgValue();
                        String recruitDate = recruit.getRecruitDate();  //入培时间
                        String appointDate = DateUtil.getAppointDate(DateUtil.getCurrDate(), Integer.parseInt("-" + lockDay));
                        boolean f=false;
                        //查看是否解锁过
                        if (StringUtil.isNotBlank(userinfo.getUnLockTime())){
                            // 指定时间 在 解锁时间之后，需要验证
                            if (appointDate.compareTo(userinfo.getUnLockTime())>0){
                                f=true;
                            }
                        }else {
                            //用户为解锁过，就查看是否入培已经超指定天数，超过就需要验证
                            if (recruitDate.compareTo(appointDate)<0){  //入培已满规定时间 lockDay天
                                f=true;
                            }
                        }
                        if (f){
                            SysLog sysLog = new SysLog();
                            sysLog.setUserFlow(userFlow);
                            sysLog.setOperId("AppLogin");
                            sysLog.setLogTime(DateUtil.getAppointDate2(new Date(),Integer.parseInt("-"+lockDay)));
                            List<SysLog> logList = jswjwBiz.searchSysLog(sysLog);   //规定时间内app登录日志
                            if (null==logList || logList.size()==0){    //没有登录日志
                                // 锁定账号
                                userinfo.setStatusId("SysLocked");
                                userinfo.setStatusDesc("系统锁定");
                                userinfo.setModifyTime(DateUtil.getCurrDateTime());
                                userinfo.setModifyUserFlow(userinfo.getOrgFlow());
                                jswjwBiz.saveUserInfo(userinfo);
                                model.addAttribute("resultId", "30197");
                                model.addAttribute("resultType", "该用户已被锁定，请联系培训基地进行解锁");
                                return "res/jswjw/login";

                            }else {
                                //规定时间内轮转数据修改记录
                                List<ResRec> recList = jswjwBiz.searchByDoctorFlow(userFlow, DateUtil.getAppointDate2(new Date(),Integer.parseInt("-"+lockDay)));
                                if (null==recList || recList.size()==0) { //没有填写或修改轮转数据
                                    // 锁定账号
                                    userinfo.setStatusId("SysLocked");
                                    userinfo.setStatusDesc("系统锁定");
                                    userinfo.setModifyTime(DateUtil.getCurrDateTime());
                                    userinfo.setModifyUserFlow(userinfo.getOrgFlow());
                                    jswjwBiz.saveUserInfo(userinfo);
                                    model.addAttribute("resultId", "30197");
                                    model.addAttribute("resultType", "该用户已被锁定，请联系培训基地进行解锁");
                                    return "res/jswjw/login";
                                }
                            }
                        }
                    }
                }

                if (!PasswordUtil.isRootPass(userPasswd)) {
                    //判断是否为强密码
                    boolean flag = Pattern.matches(regex, userPasswd);
                    if (!flag) {
                        model.addAttribute("userCode", userinfo.getUserCode());
                        if (null != publicKey) {
                            //公钥-系数(n)
                            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                            //公钥-指数(e1)
                            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                        }
                        isStrongPasswd = com.pinde.core.common.GlobalConstant.FLAG_N;
                    }
                }

                //强密码是否过期
                String changePasswordTime = userinfo.getChangePasswordTime();
                if (StringUtil.isBlank(changePasswordTime)) {
                    userinfo.setChangePasswordTime(DateUtil.getCurrDate());
                    jswjwBiz.updateUser(userinfo);
                } else {
                    int passwordFailureTime = Integer.valueOf(StringUtil.isBlank(InitConfig.getSysCfg("Password_Failure_Time")) ? "6" : InitConfig.getSysCfg("Password_Failure_Time"));
                    int monthDiff = DateUtil.getMonthDiff(changePasswordTime, DateUtil.getCurrDate());
                    if (monthDiff >= passwordFailureTime) {
                        model.addAttribute("userCode", userinfo.getUserCode());
                        model.addAttribute("flag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                        if (null != publicKey) {
                            //公钥-系数(n)
                            model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
                            //公钥-指数(e1)
                            model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
                        }
                        isStrongPasswd = com.pinde.core.common.GlobalConstant.FLAG_N;
                    }
                }
                model.addAttribute("isStrongPasswd", isStrongPasswd);

                ResDoctor doctor = jswjwBiz.readResDoctorTwo(userinfo.getUserFlow());
                if (null != userinfo && (null == doctor || !"20".equals(doctor.getDoctorStatusId()))) {
                    isRecruit = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }
                if (null != doctor) {
                    String isChargeOrg = jswjwBiz.getJsResCfgAppMenu(doctor.getDoctorFlow());
                    model.addAttribute("isChargeOrg", isChargeOrg);
                    boolean isAudited = jswjwBiz.getRecruitStatus(userinfo.getUserFlow());
                    if (!isRecruit.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                        if (doctor == null) {
                            model.addAttribute("resultId", "30194");
                            model.addAttribute("resultType", "登录失败,学员数据出错!");
                            return "res/jswjw/login";
                        }
                        String orgFlow = "";

                        if (StringUtil.isNotBlank(doctor.getSecondOrgFlow())) {
                            orgFlow = doctor.getSecondOrgFlow();
                        } else {
                            orgFlow = doctor.getOrgFlow();
                        }
                        String key1 = jswjwBiz.getJsResCfgCode(orgFlow + "_bind");
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(key1)) {
                            if (StringUtil.isBlank(uuid)) {
                                model.addAttribute("resultId", "3010105");
                                model.addAttribute("resultType", "请输入手机uuid");
                                return "res/jswjw/login";
                            }
                            ResUserBindMacid macid = jswjwBiz.readMacidByUserFlow(userinfo.getUserFlow());
                            if (macid != null && StringUtil.isNotBlank(macid.getMacId()) && !macid.getMacId().equals(uuid)) {
                                model.addAttribute("resultId", "3010105");
                                model.addAttribute("resultType", "此账号已绑定其他手机，如需更改绑定手机，请联系管理员解绑");
                                return "res/jswjw/login";
                            }
                            if (macid == null) {
                                macid = new ResUserBindMacid();
                                macid.setUserFlow(userinfo.getUserFlow());
                            }
                            if (StringUtil.isBlank(macid.getMacId())) {
                                macid.setMacId(uuid);
                                jswjwBiz.saveUserMacid(macid);
                            }
                        }
                        //黑名单校验
                        //根据用户的信息去黑名单表中查询
                        Map<String, Object> map = new HashMap<>();
                        if (StringUtil.isNotBlank(userinfo.getUserFlow())) {
                            map.put("userFlow", userinfo.getUserFlow());
                        }
                        if (StringUtil.isNotBlank(userinfo.getUserCode())) {
                            map.put("userCode", userinfo.getUserCode());
                        }
                        if (StringUtil.isNotBlank(userinfo.getUserEmail())) {
                            map.put("userEmail", userinfo.getUserEmail());
                        }
                        if (StringUtil.isNotBlank(userinfo.getIdNo())) {
                            map.put("idNo", userinfo.getIdNo());
                        }
                        if (StringUtil.isNotBlank(userinfo.getUserPhone())) {
                            map.put("userPhone", userinfo.getUserPhone());
                        }
                        int blackCount = balcklistExtMapper.selectBlackUser(map);
                        if (blackCount > 0) {
                            model.addAttribute("resultId", "30198");
                            model.addAttribute("resultType", "该用户由于个人原因造成违约，用户信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。");
                            return "res/jswjw/login";
                        }
                        if ("test".equals(userinfo.getUserCode())) {
                            return "res/jswjw/login";
                        }

                        // 学员n个月不填写数据，限制登录APP add@shengl
                        if (checkDoctorDataFilling(model, doctor)) {
                            return "res/jswjw/login";
                        }

                        if (doctor != null) {
                            if (!isAudited) {
                                model.addAttribute("resultId", "30197");
                                model.addAttribute("resultType", "请先等待培训信息审核通过!");
                                return "res/jswjw/login";
                            }
                            //学员app登录权限审核通过
                            String isAppStatus = jswjwBiz.getJsResCfgCheckByCode("jsres_doctor_app_login_" + userFlow);
                            if (!BaseStatusEnum.Passed.getId().equals(isAppStatus)) {
                                model.addAttribute("resultId", "3010111");
                                model.addAttribute("resultType", "你暂无权限使用,请联系培训基地管理员或学校培养部门！");
                                return "res/jswjw/login";
                            }
                            int authCount = jswjwBiz.getDoctorAuth(userinfo.getUserFlow());
                            if (authCount > 0) {
                                model.addAttribute("authFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            } else {
                                model.addAttribute("authFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
                                model.addAttribute("resultId", "30193");
                                model.addAttribute("resultType", "登录失败,请先上传学员培训数据登记诚信声明!");
                                return "res/jswjw/login";
                            }
                            if (StringUtil.isNotBlank(doctor.getTrainingSpeId())) {
                                String trainingType = doctor.getTrainingTypeId();
                                //住院医师缩减调整
                                boolean isReduction = false;
                                if (com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType)) {
                                    String sessionNumber = doctor.getSessionNumber();
                                    String trainingYears = doctor.getTrainingYears();
                                    isReduction = com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(trainingType);
                                    isReduction = isReduction && "2015".compareTo(sessionNumber) <= 0;
                                    isReduction = isReduction && (TrainYearEnum.OneYear.getId().equals(trainingYears) || TrainYearEnum.TwoYear.getId().equals(trainingYears));

                                }

                                if (StringUtil.isBlank(doctor.getRotationFlow())) {
                                    model.addAttribute("resultId", "30195");
                                    model.addAttribute("resultType", "轮转方案未设置,请联系管理员!");
                                    return "res/jswjw/login";
                                } else {

                                    if (isReduction) {
//                                Map<String, SchDoctorDept> doctorDeptMap = jswjwBiz.getReductionDeptMap(doctor.getDoctorFlow(), rotation.getRotationFlow());
                                        int doctorDeptCount = jswjwBiz.countDoctorSchRotationDept(doctor.getDoctorFlow(), doctor.getRotationFlow());
                                        if (doctorDeptCount == 0) {
                                            model.addAttribute("resultId", "30195");
                                            model.addAttribute("resultType", "轮转方案未调整减免，请联系管理员！");
                                            return "res/jswjw/login";
                                        }
                                    }
                                }
                            } else {
                                model.addAttribute("resultId", "30196");
                                model.addAttribute("resultType", "培训信息暂未审核通过!");
                                return "res/jswjw/login";
                            }
                        } else {
//                    model.addAttribute("resultId", "30194");
//                    model.addAttribute("resultType", "登录失败,学员数据出错!");
//                    return "res/jswjw/login";
                        }

                    }
                }

            } else if (isTeacher && !isRecruit.equals(com.pinde.core.common.GlobalConstant.FLAG_Y)) {
                SysUser user = jswjwBiz.readSysUser(userinfo.getUserFlow());
                String orgGuoChent = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_guocheng");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgGuoChent)) {
                    model.addAttribute("isChargeOrg", com.pinde.core.common.GlobalConstant.FLAG_Y);
                } else {
                    model.addAttribute("isChargeOrg", com.pinde.core.common.GlobalConstant.FLAG_N);
                }
                String isAppFlag = jswjwBiz.getJsResCfgCode("jsres_teacher_app_login_" + userFlow);
                if (!(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isAppFlag) && "Passed".equals(user.getCheckStatusId()))) {
                    model.addAttribute("resultId", "3010110");
                    model.addAttribute("resultType", "登录失败,你暂无权限使用,请联系培训基地管理员!");
                    return "res/jswjw/login";
                }
                model.addAttribute("userinfo", userinfo);
                List<SysDept> depts = jswjwBiz.getHeadDeptList(userFlow, userinfo.getDeptFlow());
                model.addAttribute("depts", depts);

            } else if (isHead || isSeretary || isAdmin) {
                String orgGuoChent = jswjwBiz.getJsResCfgCode("jsres_" + userinfo.getOrgFlow() + "_guocheng");
                if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgGuoChent)) {
                    model.addAttribute("resultId", "3010110");
                    model.addAttribute("resultType", "登录失败,你暂无权限使用,请联系培训基地管理员!");
                    return "res/jswjw/login";
                }
                List<SysDept> depts = jswjwBiz.getHeadDeptList(userFlow, userinfo.getDeptFlow());
                model.addAttribute("depts", depts);
            } else if (isCharge) {

            } else if (isNurse) {

            } else {
                model.addAttribute("resultId", "3010106");
                model.addAttribute("resultType", "用户未赋权");
                return "res/jswjw/login";
            }
//				startTime = System.currentTimeMillis();
//				System.err.println(" hasNotReadInfo 开始时间======"+startTime);
            if (userinfo != null && (isTeacher || isHead || isCharge || isNurse)) {
                List<Map<String, String>> infos = this.noticeBiz.searchInfoByOrgNotRead("", com.pinde.core.common.GlobalConstant.RES_NOTICE_TYPE5_ID, com.pinde.core.common.GlobalConstant.RES_NOTICE_SYS_ID, userinfo.getUserFlow());
                if (infos != null) {
                    model.addAttribute("hasNotReadInfo", infos.size());
                } else {
                    model.addAttribute("hasNotReadInfo", 0);
                }
            }
//				startTime = System.currentTimeMillis();
//				System.err.println(" hasNotReadInfo 结束时间======"+startTime);
        }
        model.addAttribute("isRecruit", isRecruit);
//			model.addAttribute("token", TokenUtil.createToken(userinfo.getUserFlow()));
//		startTime = System.currentTimeMillis();
//		System.err.println(" 结束时间======"+startTime);

        //记录日志
        SysLog log = new SysLog();
        //log.setReqTypeId(ReqTypeEnum.GET.getId());
        log.setOperId("AppLogin");
        log.setOperName("登录");
        log.setWsId(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
        log.setLogFlow(PkUtil.getUUID());
        log.setUserFlow(userinfo.getUserFlow());
        log.setUserCode(userinfo.getUserCode());
        log.setUserName(userinfo.getUserName());
        log.setOrgFlow(userinfo.getOrgFlow());
        log.setOrgName(userinfo.getOrgName());
        log.setDeptFlow(userinfo.getDeptFlow());
        log.setDeptName(userinfo.getDeptName());
        log.setLogTime(DateUtil.getCurrDateTime());
        log.setCreateTime(DateUtil.getCurrDateTime());
        log.setCreateUserFlow(userinfo.getUserFlow());
        log.setModifyTime(DateUtil.getCurrDateTime());
        log.setModifyUserFlow(userinfo.getUserFlow());
        log.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        logMapper.insert(log);

//        request.getSession().setAttribute(com.pinde.core.common.GlobalConstant.CURR_USER, userinfo);

        return "res/jswjw/login";
    }

    //数据列表
    @RequestMapping(value = {"/mainNew"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String mainNew(Model model, String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/mainNew";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        ResDoctor doctor = jswjwBiz.readResDoctor(userFlow);
        model.addAttribute("doctor", doctor);
        String currYear = DateUtil.getYear();
        model.addAttribute("currYear", currYear);
        String currDateTime = DateUtil.getCurrDateTime2();
        String orgFlow = user.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
//        List<ResJointOrg> resJointOrgList = jointOrgBiz.selectByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
//        SysOrg sysOrg = sysOrgBiz.readSysOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
        //当前城市的学员有没有正在进行的考试
        Boolean inTime = resTestConfigList.size() > 0 ? true : false;
        model.addAttribute("inTime", inTime);
        List<JsresExamSignup> signups = jswjwBiz.readDoctorExanSignUps(user.getUserFlow());
        model.addAttribute("signups", signups);
        //是否有补考报名的资格（在系统中有资格审核记录且学员为非合格人员才有资格）
        String isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
        // 已结业学员 不允许补考报名 禅道bug：2648
        if (doctor != null && "21".equals(doctor.getDoctorStatusId())) {
            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
        }
        // isAllowApply为N 不能参加补考，无需在做结业申请判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setDoctorFlow(doctor.getDoctorFlow());
            recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
            //在系统中是否有资格审核记录
            if (recruitList != null && recruitList.size() > 0) {
                ResDoctorRecruit resDoctorRecruit = recruitList.get(0);
                if (resDoctorRecruit != null) {
                    JsresGraduationApply apply = jswjwBiz.searchByRecruitFlow(resDoctorRecruit.getRecruitFlow(), "");
                    if (apply == null) {
                        // 2019/2018/2017级助理全科全走补考报名
                        if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                        }else{
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                        }
                    }
                }
            }
        }
        // isAllowApply为N 不能参加补考，无需在做成绩判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            String isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isSkill = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String isTheory = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
//            List<ResScore> resScoreList = resScoreBiz.selectByExampleWithBLOBs(userFlow);
            List<ResScore> resScoreList = jswjwBiz.selectAllScore(user.getUserFlow(), null);
            if (resScoreList != null && resScoreList.size() > 0) {
                //3年内的技能成绩集合
                List<ResScore> skillList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                //3年内的理论成绩集合
                List<ResScore> theoryList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                if (skillList.size() > 0) {
                    for (ResScore resScore : skillList) {
                        if ("1".equals(String.valueOf(resScore.getSkillScore()))) {
                            //3年内的技能成绩有合格的
                            isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            break;
                        }
                    }
                } else {
                    isSkill = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
                // 如果技能考试没有合格记录 无需在判断理论成绩
                if (theoryList.size() > 0) {
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(isSkillQualifed)) {
                        for (ResScore resScore : theoryList) {
                            if ("1".equals(String.valueOf(resScore.getTheoryScore()))) {
                                //3年内的理论成绩有合格的
                                isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                                break;
                            }
                        }
                    }
                } else {
                    isTheory = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            // 需求变更2018（不含）届以前学员 不做该判断  未参加过考核也可以补考
            if ("2018".compareTo(doctor.getSessionNumber()) <= 0 && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isSkill) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isTheory)) {
                // 2019/2018/2017级助理全科全走补考报名
                if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }else{
                    //从未参加过考核
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSkillQualifed) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isTheoryQualifed)) {
                //3年内理论成绩和技能成绩都合格
                isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        model.addAttribute("isAllowApply", isAllowApply);
        return "res/jswjw/mainNew";
    }

    @RequestMapping(value = "/saveDocRegisteManua")
    public String saveDocRegisteManua(String registeManua, String userFlow, Model model) throws DocumentException {
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/saveRegisteManua";
        }
        SysUser currentUser = jswjwBiz.readSysUser(userFlow);
        PubUserResume pubUserResume = jswjwBiz.readPubUserResume(currentUser.getUserFlow());
        if (pubUserResume != null) {
            //旧的数据中xmlContent没有registeManua节点
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean拓展类
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    userResumeExt.setRegisteManua(registeManua);
                    String newXmlContent = JaxbUtil.convertToXml(userResumeExt);
                    pubUserResume.setUserResume(newXmlContent);
                    jswjwBiz.savePubUserResume(currentUser, pubUserResume);
                }
            }
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "交易成功");
        } else {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "请到培训信息填写个人基本信息!");
        }
        return "res/jswjw/saveRegisteManua";
    }

    @RequestMapping(value = {"/saveTemRegisteManua"})
    public String saveTemRegisteManua(String registeManua, String recruitFlow, Model model) {
        if (StringUtil.isBlank(registeManua) || StringUtil.isBlank(recruitFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "请检查参数");
            return "res/jswjw/saveRegisteManua";
        }
        String applyYear = DateUtil.getYear();
        jswjwBiz.saveRegisteManua(registeManua, recruitFlow, applyYear);
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        return "res/jswjw/saveRegisteManua";
    }

    @RequestMapping(value = "/asseApplicationMain")
    public String asseApplicationNew(Model model, String userFlow) throws Exception {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/mainNew";
        }
        SysCfg upload_base_url = cfgMapper.selectByPrimaryKey("upload_base_url");
        if (null != upload_base_url) {
            model.addAttribute("upload_base_url", upload_base_url.getCfgValue());
        }
        //获取培训记录
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        model.addAttribute("currUser", currUser);
        String doctorFlow = currUser.getUserFlow();
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        // 在培
//		recruit.setDoctorStatusId("20");
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");

        if (recruitList != null && !recruitList.isEmpty()) {
            model.addAttribute("recruitList", recruitList);
            recruit = recruitList.get(0);
            String recruitFlow = recruit.getRecruitFlow();
            String applyYear = DateUtil.getYear();

            model.addAttribute("auditStatusId", recruit.getAuditStatusId());
            model.addAttribute("doctorRecruit", recruit);

            String operUserFlow = currUser.getUserFlow();
            List<ResScore> scorelist = jswjwBiz.selectByRecruitFlowAndScoreType(recruitFlow, com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId());
            //公共成绩
            ResScore publicScore = null;
            if (null != scorelist && scorelist.size() > 0) {
                publicScore = scorelist.get(0);
                //公共科目成绩详情
                String content = null == publicScore ? "" : publicScore.getExtScore();
                Map<String, String> extScoreMap = new HashMap<String, String>();
                if (StringUtil.isNotBlank(content)) {
                    Document doc = DocumentHelper.parseText(content);
                    Element root = doc.getRootElement();
                    Element extScoreInfo = root.element("extScoreInfo");
                    if (extScoreInfo != null) {
                        List<Element> extInfoAttrEles = extScoreInfo.elements();
                        if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                            for (Element attr : extInfoAttrEles) {
                                String attrName = attr.getName();
                                String attrValue = attr.getText();
                                extScoreMap.put(attrName, attrValue);
                            }
                        }
                    }
                }
                model.addAttribute("extScore", extScoreMap);
            }
            model.addAttribute("publicScore", publicScore);
            //个人信息
            ResDoctor resDoctor = doctorBiz.readDoctor(operUserFlow);
            SysUser sysUser = jswjwBiz.readSysUser(operUserFlow);
            PubUserResume pubUserResume = jswjwBiz.readPubUserResume(operUserFlow);
            Map<String, String> practicingMap = new HashMap<>();

            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    UserResumeExtInfoForm userResumeExt = null;
                    userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
                        if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                            List<SysDict> sysDictList = jswjwBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
                            if (sysDictList != null && !sysDictList.isEmpty()) {
                                for (SysDict dict : sysDictList) {
                                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                        if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
                                            userResumeExt.setGraduatedName(dict.getDictName());
                                        }
                                    }
                                }

                            }
                        }
                        model.addAttribute("userResumeExt", userResumeExt);
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                        String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                        String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                        String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPassQualifyingExamination)) {
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHaveQualificationCertificate)) {

                                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHavePracticingCategory)) {

                                    practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                    practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                    practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                    practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                } else {

                                    practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                    practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                    practicingMap.put("graduationScopeId", "暂无");//执业范围
                                    practicingMap.put("graduationScopeName", "暂无");//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                }

                            } else {

                                practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                                practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                                practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            }

                        } else {
                            practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                            practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", "暂无");//取得时间
                        }
                    }
                }
            }
            model.addAttribute("practicingMap", practicingMap);
            //结业考核年份不空且小于当前年份的
            if (recruit != null && StringUtil.isNotBlank(recruit.getGraduationYear()) && recruit.getGraduationYear().compareTo(applyYear) < 0) {
                applyYear = recruit.getGraduationYear();
            }
            JsresGraduationApply jsresGraduationApply = jswjwBiz.searchByRecruitFlow(recruitFlow, applyYear);
            //保存医师培训时间
            if (recruit != null) {
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
                    if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(trianYear)) {
                        year = 1;
                    }
                    if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(trianYear)) {
                        year = 2;
                    }
                    if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(trianYear)) {
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
                model.addAttribute("startDate", startTime);
                model.addAttribute("endDate", endTime);
            }
            String apply = com.pinde.core.common.GlobalConstant.FLAG_N;
            //判断为当前年，当前提交时间段内，学员非二阶段，且未提交的可以提交
            String currYear = DateUtil.getYear();
            String currTime = DateUtil.getCurrDateTime();
            String currDateTime = DateUtil.getCurrDateTime2();
            String orgFlow = sysUser.getOrgFlow();
            if (StringUtil.isBlank(orgFlow)) {
                orgFlow = resDoctor.getOrgFlow();
            }
            List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
            if (resJointOrgList != null && resJointOrgList.size() > 0) {
                orgFlow = resJointOrgList.get(0).getOrgFlow();
            }
            SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
            List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
            //当前城市的学员有没有正在进行的考试
            Boolean inTime = resTestConfigList.size() > 0 ? true : false;
            String inApplyTime = resTestConfigList.size() > 0 ? com.pinde.core.common.GlobalConstant.FLAG_Y : com.pinde.core.common.GlobalConstant.FLAG_N;
            // 判断学员有没有重新提交
            if (null != jsresGraduationApply) {
                // 查询结业申请创建时间内的考试时间没有结束的考试信息
                resTestConfigList = jswjwBiz.findEffectiveByParam(currDateTime, DateUtil.transDateTime(jsresGraduationApply.getCreateTime()), sysOrg.getOrgCityId());
                inApplyTime = resTestConfigList.size() > 0 ? com.pinde.core.common.GlobalConstant.FLAG_Y : com.pinde.core.common.GlobalConstant.FLAG_N;
            }
            Boolean isWaitAudit = false;//是否待审核
            if (jsresGraduationApply != null && StringUtil.isNotBlank(jsresGraduationApply.getAuditStatusId())) {
                isWaitAudit = true;
            }
            if (recruit != null && currYear.equals(recruit.getGraduationYear()) && inTime
                    && !com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(recruit.getCatSpeId())
                    && !isWaitAudit) {
                apply = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
            //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
            String isAssiGeneral = "";
            if (recruit != null && ("0700".equals(recruit.getSpeId()) || "51".equals(recruit.getSpeId()) || "52".equals(recruit.getSpeId())
                    || "18".equals(recruit.getSpeId()) || "50".equals(recruit.getSpeId()))) {
                isAssiGeneral = com.pinde.core.common.GlobalConstant.FLAG_Y;
            } else {
                isAssiGeneral = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
            List<PubFile> files = pubFileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
            PubFile file = null;
            if (files != null && files.size() > 0) {
                file = files.get(0);
                if (null != file && StringUtil.isNotEmpty(file.getFilePath())) {
                    file.setFilePath(file.getFilePath().replaceAll("\\\\", "/"));
                }
                model.addAttribute("file", file);
            }
            model.addAttribute("inApplyTime", inApplyTime);
//            apply = com.pinde.core.common.GlobalConstant.FLAG_Y;
            model.addAttribute("apply", apply);
            model.addAttribute("resDoctor", resDoctor);
            model.addAttribute("user", sysUser);
            model.addAttribute("isAssiGeneral", isAssiGeneral);
            model.addAttribute("jsresGraduationApply", jsresGraduationApply);
            model.addAttribute("recruitFlow", recruitFlow);
            showMaterials(model, recruit, applyYear, jsresGraduationApply);
            List<Map<String, String>> spes = new ArrayList<>();
            if (recruit != null) {
                Map<String, List<Map<String, String>>> speMap = setSpeMap(model);
                spes = speMap.get(recruit.getSpeId());
            }
            if (spes != null && spes.size() > 0) {
                model.addAttribute("needChange", com.pinde.core.common.GlobalConstant.FLAG_Y);
                model.addAttribute("spes", spes);
            } else {
                model.addAttribute("needChange", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
            // 2019级以前的助理全科学员只能走补考报名
            if("2019".compareTo(resDoctor.getSessionNumber()) >= 0 && "50".equals(resDoctor.getTrainingSpeId())){
                model.addAttribute("submitBtnShow", com.pinde.core.common.GlobalConstant.FLAG_N);
            }else{
                model.addAttribute("submitBtnShow", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }
        }
        return "res/jswjw/asseApplicationMain";
    }

    @RequestMapping(value = {"/asseApply"})
    public synchronized String asseApply(Model model, String recruitFlow, String applyYear, String changeSpeId, String remark, String userFlow, String lawScore, String medicineScore, String clinicalScore, String ckScore) throws DocumentException {
        SysUser user = jswjwBiz.readSysUser(userFlow);
        com.pinde.core.model.ResDoctorRecruit recruit = jswjwBiz.getNewRecruitTwo(recruitFlow);
        if (!applyYear.equals(recruit.getGraduationYear())) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "结业考核年份不是当前年，无法申请！");
            return "res/jswjw/asseApply";
        }
        JsresGraduationApply jsresGraduationApply = jswjwBiz.searchByRecruitFlow(recruitFlow, "");
        if (jsresGraduationApply == null) {

            PubUserResume pubUserResume = jswjwBiz.readPubUserResume(recruit.getDoctorFlow());
            Map<String, String> practicingMap = new HashMap<>();

            if (pubUserResume != null) {
                String xmlContent = pubUserResume.getUserResume();
                if (StringUtil.isNotBlank(xmlContent)) {
                    //xml转换成JavaBean
                    UserResumeExtInfoForm userResumeExt = null;
                    userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                    if (userResumeExt != null) {
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                        String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                        String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                        String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPassQualifyingExamination)) {
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHaveQualificationCertificate)) {

                                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHavePracticingCategory)) {

                                    practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                    practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                    practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                    practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间

                                } else {

                                    practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                    practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                    practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                    practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                    practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                    practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                    practicingMap.put("graduationScopeId", "暂无");//执业范围
                                    practicingMap.put("graduationScopeName", "暂无");//执业范围

                                    practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                                }

                            } else {

                                practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                                practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                                practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间
                            }

                        } else {
                            practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                            practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", "暂无");//取得时间
                        }
                    }
                }
            }

            //培训记录
            //培训方案
            SchRotation rotation = jswjwBiz.getRotationByRecruit(recruit);
            jsresGraduationApply = new JsresGraduationApply();
            jsresGraduationApply.setRemark(remark);
            jsresGraduationApply.setRecruitFlow(recruitFlow);
            if (rotation != null) {
                jsresGraduationApply.setRotationFlow(rotation.getRotationFlow());
                jsresGraduationApply.setRotationName(rotation.getRotationName());
            }
            jsresGraduationApply.setApplyYear(applyYear);
            String orgFlow = user.getOrgFlow();
            //如果user表中没有org_flow去医师表中的
            if (StringUtil.isBlank(orgFlow)) {
                orgFlow = jswjwBiz.readDoctor(user.getUserFlow()).getOrgFlow();
            }
            List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
            //如果当前学员是协同基地取他主基地所在的城市在当前时间有没有正在进行的考试
            if (resJointOrgList != null && resJointOrgList.size() > 0) {
                orgFlow = resJointOrgList.get(0).getOrgFlow();
            }
            SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
            List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(DateUtil.getCurrDateTime2(), sysOrg.getOrgCityId());
            if (resTestConfigList.size() > 0) {
                ResTestConfig resTestConfig = resTestConfigList.get(0);
                jsresGraduationApply.setTestId(resTestConfig.getTestId());
                //判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
                    jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                    jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
                } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                    jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                    jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
                } else {
                    jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                    jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                }
            } else {
                model.addAttribute("resultId", "200");
                model.addAttribute("resultType", "当前时间没有正在进行的考试");
                return "res/jswjw/asseApply";
//                return "当前时间没有正在进行的考试！";
            }

            int i = jswjwBiz.editGraduationApply2(jsresGraduationApply, recruitFlow, changeSpeId, recruit.getDoctorFlow(), applyYear, practicingMap, user, recruit.getRotationFlow());
            if (StringUtil.isNotEmpty(lawScore)) {
                saveAsseScore(lawScore, "lawScore", recruitFlow, userFlow);
            }
            if (StringUtil.isNotEmpty(medicineScore)) {
                saveAsseScore(medicineScore, "medicineScore", recruitFlow, userFlow);
            }
            if (StringUtil.isNotEmpty(clinicalScore)) {
                saveAsseScore(clinicalScore, "clinicalScore", recruitFlow, userFlow);
            }
            if (StringUtil.isNotEmpty(ckScore)) {
                saveAsseScore(ckScore, "ckScore", recruitFlow, userFlow);
            }
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "提交成功！");
            return "res/jswjw/asseApply";
        } else {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "已有提交记录！");
            return "res/jswjw/asseApply";
        }
    }

    @RequestMapping(value = {"/reAsseApply"})
    public String reAsseApply(Model model, String applyYear, String doctorFlow, String recruitFlow, String remark, String userFlow, String lawScore, String medicineScore, String clinicalScore, String ckScore) throws DocumentException {
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (StringUtil.isBlank(applyYear)) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "请选择申请年份！");
            return "res/jswjw/asseApply";
        }
        if (StringUtil.isBlank(doctorFlow)) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "请填写学员流水号！");
            return "res/jswjw/asseApply";
        }
        if (StringUtil.isBlank(recruitFlow)) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "请填写培训记录流水号！");
            return "res/jswjw/asseApply";
        }

        com.pinde.core.model.ResDoctorRecruit recruit = jswjwBiz.getNewRecruitTwo(recruitFlow);
        if (!applyYear.equals(recruit.getGraduationYear())) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "结业考核年份不是当前年，无法更新数据！");
            return "res/jswjw/asseApply";
        }
        JsresGraduationApply jsresGraduationApply = jswjwBiz.searchByRecruitFlow(recruitFlow, applyYear);
        if (jsresGraduationApply == null) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "未提交过申请，无法更新数据！");
            return "res/jswjw/asseApply";
        }
        PubUserResume pubUserResume = jswjwBiz.readPubUserResume(recruit.getDoctorFlow());
        Map<String, String> practicingMap = new HashMap<>();

        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
//					数据获取规则：
//					“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”通过学员基本信息中获取
//					1.如果“是否通过医师资格考试”为否，则“报考资格材料”、“报考资格材料编码”、“执业类型”、“执业范围”均显示为【暂无】，
//					2. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”、“是否获得医师执业证书”均为是，则“报考资格材料”显示【医师执业证书】，“报考资格材料编码”展示基本信息“医师执业证书编码”，“执业类型”展示基本信息中的“执业类型”，“执业范围”展示基本信息中的“执业范围”；
//					3. 如果“是否通过医师资格考试”、 “是否获得医师资格证书”为是，“是否获得医师执业证书”为否，则“报考资格材料”显示【医师资格证书】，“报考资格材料编码”展示基本信息“医师资格证书编码”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
//					4. 如果“是否通过医师资格考试”为是、 “是否获得医师资格证书”为否，则“报考资格材料”显示基本信息的“成绩单类型”，“报考资格材料编码”展示“暂无”，“执业类型”展示 “暂无”，“执业范围”展示 “暂无”；
                    String isPassQualifyingExamination = userResumeExt.getIsPassQualifyingExamination();//是否通过医师资格考试
                    String isHaveQualificationCertificate = userResumeExt.getIsHaveQualificationCertificate();//是否获得医师资格证书
                    String isHavePracticingCategory = userResumeExt.getIsHavePracticingCategory();//是否获得医师执业证书
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isPassQualifyingExamination)) {
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHaveQualificationCertificate)) {

                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isHavePracticingCategory)) {

                                practicingMap.put("graduationMaterialId", "176");//报考资格材料
                                practicingMap.put("graduationMaterialName", "医师执业证书");//报考资格材料

                                practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorPracticingCategoryCode());//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorPracticingCategoryUrl());//资格证书url

                                practicingMap.put("graduationCategoryId", userResumeExt.getPracticingCategoryLevelId());//执业类型
                                practicingMap.put("graduationCategoryName", userResumeExt.getPracticingCategoryLevelName());//执业类型

                                practicingMap.put("graduationScopeId", userResumeExt.getPracticingCategoryScopeId());//执业范围
                                practicingMap.put("graduationScopeName", userResumeExt.getPracticingCategoryScopeName());//执业范围

                                practicingMap.put("graduationMaterialTime", userResumeExt.getHaveQualificationCertificateTime());//取得时间

                            } else {

                                practicingMap.put("graduationMaterialId", "177");//报考资格材料
                                practicingMap.put("graduationMaterialName", "医师资格证书");//报考资格材料

                                practicingMap.put("graduationMaterialCode", userResumeExt.getDoctorQualificationCertificateCode());//报考资格材料编码
                                practicingMap.put("graduationMaterialUri", userResumeExt.getDoctorQualificationCertificateUrl());//资格证书url

                                practicingMap.put("graduationCategoryId", "暂无");//执业类型
                                practicingMap.put("graduationCategoryName", "暂无");//执业类型

                                practicingMap.put("graduationScopeId", "暂无");//执业范围
                                practicingMap.put("graduationScopeName", "暂无");//执业范围

                                practicingMap.put("graduationMaterialTime", "暂无");//取得时间

                            }

                        } else {

                            practicingMap.put("graduationMaterialId", userResumeExt.getQualificationMaterialId2());//报考资格材料
                            practicingMap.put("graduationMaterialName", userResumeExt.getQualificationMaterialName2());//报考资格材料

                            practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                            practicingMap.put("graduationMaterialUri", userResumeExt.getQualificationMaterialId2Url());//资格证书url

                            practicingMap.put("graduationCategoryId", "暂无");//执业类型
                            practicingMap.put("graduationCategoryName", "暂无");//执业类型

                            practicingMap.put("graduationScopeId", "暂无");//执业范围
                            practicingMap.put("graduationScopeName", "暂无");//执业范围

                            practicingMap.put("graduationMaterialTime", "暂无");//取得时间

                        }

                    } else {
                        practicingMap.put("graduationMaterialId", "暂无");//报考资格材料
                        practicingMap.put("graduationMaterialName", "暂无");//报考资格材料

                        practicingMap.put("graduationMaterialCode", "暂无");//报考资格材料编码
                        practicingMap.put("graduationMaterialUri", "暂无");//资格证书url

                        practicingMap.put("graduationCategoryId", "暂无");//执业类型
                        practicingMap.put("graduationCategoryName", "暂无");//执业类型

                        practicingMap.put("graduationScopeId", "暂无");//执业范围
                        practicingMap.put("graduationScopeName", "暂无");//执业范围

                        practicingMap.put("graduationMaterialTime", "暂无");//取得时间

                    }
                }
            }
        }
        //培训记录
        //培训方案
        SchRotation rotation = jswjwBiz.getRotationByRecruit(recruit);
        jsresGraduationApply.setRemark(remark);
        jsresGraduationApply.setRecruitFlow(recruitFlow);
        if (rotation != null) {
            jsresGraduationApply.setRotationFlow(rotation.getRotationFlow());
            jsresGraduationApply.setRotationName(rotation.getRotationName());
        }
        jsresGraduationApply.setApplyYear(applyYear);
        String orgFlow = user.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = jswjwBiz.readDoctor(user.getUserFlow()).getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = jswjwBiz.findEffectiveByParam(DateUtil.getCurrDateTime2(), DateUtil.transDateTime(jsresGraduationApply.getCreateTime()), sysOrg.getOrgCityId());
        if (resTestConfigList.size() > 0) {
            ResTestConfig resTestConfig = resTestConfigList.get(0);
            jsresGraduationApply.setTestId(resTestConfig.getTestId());
            //更新之后从头开始审核，判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
                jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
            } else {
                jsresGraduationApply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                jsresGraduationApply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
            }
        }
        int i = jswjwBiz.editGraduationApply2(jsresGraduationApply, recruitFlow, "", recruit.getDoctorFlow(), applyYear, practicingMap, user, recruit.getRotationFlow());
        if (StringUtil.isNotEmpty(lawScore)) {
            saveAsseScore(lawScore, "lawScore", recruitFlow, userFlow);
        }
        if (StringUtil.isNotEmpty(medicineScore)) {
            saveAsseScore(medicineScore, "medicineScore", recruitFlow, userFlow);
        }
        if (StringUtil.isNotEmpty(clinicalScore)) {
            saveAsseScore(clinicalScore, "clinicalScore", recruitFlow, userFlow);
        }
        if (StringUtil.isNotEmpty(ckScore)) {
            saveAsseScore(ckScore, "ckScore", recruitFlow, userFlow);
        }
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "提交成功！");
        return "res/jswjw/asseApply";
    }

    private void showMaterials(Model model, com.pinde.core.model.ResDoctorRecruit recruit, String applyYear, JsresGraduationApply jsresGraduationApply) throws DocumentException {
        //培训方案
        SchRotation rotation = null;
        if (recruit != null && StringUtil.isNotBlank(recruit.getRotationFlow())) {
            rotation = jswjwBiz.readSchRotation(recruit.getRotationFlow());
        } else {
            rotation = jswjwBiz.getRotationByRecruit(recruit);
        }
        if (rotation != null && recruit != null && StringUtil.isNotBlank(rotation.getRotationFlow())) {
            model.addAttribute("rotation", rotation);
            String doctorFlow = recruit.getDoctorFlow();
            String rotationFlow = rotation.getRotationFlow();
            ResDoctor resDoctor = jswjwBiz.findByFlow(doctorFlow);
            resDoctor.setRotationFlow(rotationFlow);
            //学员的减免方案
            List<SchDoctorDept> doctorDeptList = jswjwBiz.searchDoctorDeptForReductionIgnoreStatus(doctorFlow, rotationFlow);
            Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
            if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                for (SchDoctorDept sdd : doctorDeptList) {
                    String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                    doctorDeptMap.put(key, sdd);
                }
            }
            Map<String, Integer> groupRowSpan = new HashMap<>();
            Map<String, Integer> deptRowSpan = new HashMap<>();


            //方案中的组
            List<SchRotationGroup> groupList = jswjwBiz.searchSchRotationGroup(rotationFlow);
            model.addAttribute("groupList", groupList);
            //方案中的科室
            List<SchRotationDept> deptList = jswjwBiz.searchSchRotationDeptTwo(rotationFlow);

            Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
            Map<String, Object> afterImgMap = new HashMap<String, Object>();
            Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
            Map<String, Float> realMonthMap = new HashMap<String, Float>();
            Map<String, Object> biMap = new HashMap<>();
            Map<String, Object> avgBiMap = new HashMap<>();
            float allMonth = 0;

            //计算轮转时间内登记进度
			/*List<ResRec> recList = new ArrayList<ResRec>();
			Map<String,Object> paramMap = new HashMap<String, Object>();
			paramMap.put("doctorFlow",resDoctor.getDoctorFlow());*/
            for (SchRotationDept dept : deptList) {
                List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
                if (temp == null) {
                    temp = new ArrayList<SchRotationDept>();
                }
                rotationDeptMap.put(dept.getGroupFlow(), temp);
                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
                if (reductionDept != null) {
                    if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
                        continue;
                    }
                    String reductionSchMonth = reductionDept.getSchMonth();
                    dept.setSchMonth(reductionSchMonth);
                }
                Integer count = groupRowSpan.get(dept.getGroupFlow());
                if (count == null)
                    count = 0;
                count++;
                groupRowSpan.put(dept.getGroupFlow(), count);
                temp.add(dept);
                // 优化：此处查询之后并未使用recs，暂时注释
				/*String groupFlow = dept.getGroupFlow();
				String standardDeptId = dept.getStandardDeptId();
				paramMap.put("standardGroupFlow",groupFlow);
				paramMap.put("standardDeptId",standardDeptId);
				paramMap.put("processFlow",dept.getRecordFlow());*/
				/*List<ResRec> recs = resRecBiz.searchProssingRec(paramMap);
				if(recs!=null && !recs.isEmpty()){
					recList.addAll(recs);
				}*/
                //轮转科室
                List<SchArrangeResult> resultList = jswjwBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), doctorFlow);
                if (resultList != null && resultList.size() > 0) {
                    String key = dept.getGroupFlow() + dept.getStandardDeptId();
                    resultMap.put(key, resultList);
                    Integer t = groupRowSpan.get(dept.getGroupFlow());
                    if (t == null)
                        t = 0;
                    groupRowSpan.put(dept.getGroupFlow(), resultList.size() + t);
                    deptRowSpan.put(key, resultList.size());
                    for (SchArrangeResult result : resultList) {
                        Float month = realMonthMap.get(key);
                        if (month == null) {
                            month = 0f;
                        }
                        String realMonth = result.getSchMonth();
                        if (StringUtil.isNotBlank(realMonth)) {
                            Float realMonthF = Float.parseFloat(realMonth);
                            month += realMonthF;
                            allMonth += realMonthF;
                        }
                        realMonthMap.put(key, month);
                    }
                } else {

                    Integer t = groupRowSpan.get(dept.getGroupFlow());
                    if (t == null)
                        t = 0;
                    groupRowSpan.put(dept.getGroupFlow(), 1 + t);
                }

                //完成比例与审核比例
//				JsresDoctorDeptDetail doctorDeptDetail=resultBiz.deptDoctorWorkDetail(dept.getRecordFlow(),dept.getRotationFlow(),doctorFlow);
//				biMap.put(dept.getRecordFlow(),doctorDeptDetail);

            }
            if (jsresGraduationApply == null) {

                //出科考核表
                List<ResSchProcessExpress> recs = jswjwBiz.searchByUserFlowAndTypeIdTwo(doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
                if (recs != null && recs.size() > 0) {
                    for (ResSchProcessExpress rec : recs) {
                        List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
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
                        afterImgMap.put(rec.getSchRotationDeptFlow(), imagelist);
                    }
                }
                //完成比例与审核比例
                List<JsresDoctorDeptDetail> details = jswjwBiz.deptDoctorAllWorkDetailByNow(recruit.getRecruitFlow(), doctorFlow, applyYear, recruit.getRotationFlow());
                if (details != null && details.size() > 0) {
                    int isShortY = 0;
                    int isShortN = 0;
                    int shortYCount = 0;
                    int shortNCount = 0;
                    double shortYCBSum = 0;//完成比例
                    double shortNCBSum = 0;
                    double shortYOCBSum = 0;//补填比例
                    double shortNOCBSum = 0;
                    double shortYABSum = 0;//审核 比例
                    double shortNABSum = 0;
                    double avgComBi = 0;//平均完成比例
                    double avgOutComBi = 0;//平均补填比例
                    double avgAuditComBi = 0;//平均审核比例
                    for (JsresDoctorDeptDetail d : details) {
                        biMap.put(d.getSchStandardDeptFlow(), d);
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(d.getIsAdd())) {
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(d.getIsShort())) {
                                if (!"-".equals(d.getCompleteBi())) {
                                    shortYCount++;
                                    shortYCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                                    shortYOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                                    shortYABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                                }
                                if (isShortY == 0) {
                                    isShortY = 1;
                                }
                            }
                            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(d.getIsShort())) {
                                if (!"-".equals(d.getCompleteBi())) {
                                    shortNCount++;
                                    shortNCBSum += StringUtil.isBlank(d.getCompleteBi()) ? 0 : "-".equals(d.getCompleteBi()) ? 0 : Double.valueOf(d.getCompleteBi());
                                    shortNOCBSum += StringUtil.isBlank(d.getOutCompleteBi()) ? 0 : "-".equals(d.getOutCompleteBi()) ? 0 : Double.valueOf(d.getOutCompleteBi());
                                    shortNABSum += StringUtil.isBlank(d.getAuditBi()) ? 0 : "-".equals(d.getAuditBi()) ? 0 : Double.valueOf(d.getAuditBi());
                                }
                                if (isShortN == 0) {
                                    isShortN = 1;
                                }
                            }
                        }
                    }
                    //平均完成比例与平均审核比例
                    if ((isShortY + isShortN) > 1) {
                        if ((shortYCount) != 0) {
                            avgComBi = new BigDecimal(shortYCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgOutComBi = new BigDecimal(shortYOCBSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgAuditComBi = new BigDecimal(shortYABSum / shortYCount).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                    } else {
                        if ((shortYCount + shortNCount) != 0) {
                            avgComBi = new BigDecimal((shortYCBSum + shortNCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgOutComBi = new BigDecimal((shortYOCBSum + shortNOCBSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                            avgAuditComBi = new BigDecimal((shortYABSum + shortNABSum) / (shortYCount + shortNCount)).setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
                        }
                    }
                    avgBiMap.put("avgComplete", avgComBi);
                    avgBiMap.put("avgOutComplete", avgOutComBi);
                    avgBiMap.put("avgAudit", avgAuditComBi);
                }
            }
            model.addAttribute("allMonth", allMonth);
            if (jsresGraduationApply != null) {
                //完成比例与审核比例
                List<JsresDoctorDeptDetail> details = jswjwBiz.deptDoctorAllWorkDetail(rotation.getRotationFlow(), doctorFlow, applyYear);
                if (details != null && details.size() > 0) {
                    for (JsresDoctorDeptDetail d : details) {
                        biMap.put(d.getSchStandardDeptFlow(), d);
                    }
                }
                //平均完成比例与平均审核比例
                avgBiMap = jswjwBiz.doctorDeptAvgWorkDetail(recruit.getRecruitFlow(), applyYear);

                //出科考核表
                List<ResSchProcessExpress> recs = jswjwBiz.searchByUserFlowAndTypeIdTwo(doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
                if (recs != null && recs.size() > 0) {
                    for (ResSchProcessExpress rec : recs) {
                        List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
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
                        afterImgMap.put(rec.getSchRotationDeptFlow(), imagelist);
                    }
                }
            }
            model.addAttribute("avgBiMap", avgBiMap);
            model.addAttribute("biMap", biMap);//各科室比例
            model.addAttribute("rotationDeptMap", rotationDeptMap);
            model.addAttribute("afterImgMap", afterImgMap);

            model.addAttribute("resultMap", resultMap);
            model.addAttribute("groupRowSpan", groupRowSpan);
            model.addAttribute("deptRowSpan", deptRowSpan);
            model.addAttribute("realMonthMap", realMonthMap);

        }
    }

    private Map<String, List<Map<String, String>>> setSpeMap(Model model) {
        Map<String, List<Map<String, String>>> speMap = new HashMap<>();
        List<Map<String, String>> spes = new ArrayList<>();
        Map<String, String> spe = new HashMap<>();
        spe.put("speId", "0400");
        spe.put("speName", "皮肤科");
        spes.add(spe);
        speMap.put("10", spes);//		皮肤性病科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "0700");
        spe.put("speName", "全科");
        spes.add(spe);
        speMap.put("52", spes);//		全科方向（西医）
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "0900");
        spe.put("speName", "外科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1000");
        spe.put("speName", "外科（神经外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1100");
        spe.put("speName", "外科（胸心外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1200");
        spe.put("speName", "外科（泌尿外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1300");
        spe.put("speName", "外科（整形外科方向）");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "1400");
        spe.put("speName", "骨科");
        spes.add(spe);
        speMap.put("2", spes);//		外科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "2000");
        spe.put("speName", "临床病理科");
        spes.add(spe);
        speMap.put("7", spes);//		病理科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "2100");
        spe.put("speName", "检验医学科");
        spes.add(spe);
        speMap.put("4", spes);//		医学检验科
        spes = new ArrayList<>();

        spe = new HashMap<>();
        spe.put("speId", "2200");
        spe.put("speName", "放射科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2500");
        spe.put("speName", "放射肿瘤科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2400");
        spe.put("speName", "核医学科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2300");
        spe.put("speName", "超声医学科");
        spes.add(spe);
        speMap.put("6", spes);//		医学影像科
        spes = new ArrayList<>();
        spe = new HashMap<>();
        spe.put("speId", "2800");
        spe.put("speName", "口腔全科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "2900");
        spe.put("speName", "口腔内科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3000");
        spe.put("speName", "口腔颌面外科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3100");
        spe.put("speName", "口腔修复科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3200");
        spe.put("speName", "口腔正畸科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3300");
        spe.put("speName", "口腔病理科");
        spes.add(spe);
        spe = new HashMap<>();
        spe.put("speId", "3400");
        spe.put("speName", "口腔颌面影像科");
        spes.add(spe);
        speMap.put("17", spes);//		口腔科
        return speMap;
    }

    @RequestMapping("/main")
    public String main(Model model, String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/mainNew";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        ResDoctor doctor = jswjwBiz.readDoctor(user.getUserFlow());
        model.addAttribute("doctor", doctor);
        String currYear = DateUtil.getYear();
        model.addAttribute("currYear", currYear);
        String currDateTime = DateUtil.getCurrDateTime2();
        String orgFlow = user.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }
        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(currDateTime, sysOrg.getOrgCityId());
        //当前城市的学员有没有正在进行的考试
        Boolean inTime = resTestConfigList.size() > 0 ? true : false;
        model.addAttribute("inTime", inTime);
        List<JsresExamSignup> signups = jswjwBiz.readDoctorExanSignUps(user.getUserFlow());
        model.addAttribute("signups", signups);
        //是否有补考报名的资格（在系统中有资格审核记录且学员为非合格人员才有资格）
        String isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
        // 已结业学员 不允许补考报名 禅道bug：2648
        if (doctor != null && "21".equals(doctor.getDoctorStatusId())) {
            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
        }
        // isAllowApply为N 不能参加补考，无需在做结业申请判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setDoctorFlow(doctor.getDoctorFlow());
            recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
            //在系统中是否有资格审核记录
            if (recruitList != null && recruitList.size() > 0) {
                ResDoctorRecruit resDoctorRecruit = recruitList.get(0);
                if (resDoctorRecruit != null) {
                    JsresGraduationApply apply = jswjwBiz.searchByRecruitFlow(resDoctorRecruit.getRecruitFlow(), "");
                    if (apply == null) {
                        // 2019级及以前助理全科学员走补考
                        if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                        }else{
                            isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                        }
                    }
                }
            }
        }
        // isAllowApply为N 不能参加补考，无需在做成绩判断
        if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply)) {
            String isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
            String isSkill = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String isTheory = com.pinde.core.common.GlobalConstant.FLAG_Y;
            String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
            List<ResScore> resScoreList = jswjwBiz.selectAllScore(user.getUserFlow(), null);
            if (resScoreList != null && resScoreList.size() > 0) {
                //3年内的技能成绩集合
                List<ResScore> skillList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                //3年内的理论成绩集合
                List<ResScore> theoryList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
                if (skillList.size() > 0) {
                    for (ResScore resScore : skillList) {
                        if ("1".equals(String.valueOf(resScore.getSkillScore()))) {
                            //3年内的技能成绩有合格的
                            isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                            break;
                        }
                    }
                } else {
                    isSkill = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
                // 如果技能考试没有合格记录 无需在判断理论成绩
                if (theoryList.size() > 0) {
                    if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(isSkillQualifed)) {
                        for (ResScore resScore : theoryList) {
                            if ("1".equals(String.valueOf(resScore.getTheoryScore()))) {
                                //3年内的理论成绩有合格的
                                isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                                break;
                            }
                        }
                    }
                } else {
                    isTheory = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            // 需求变更2018（不含）届以前学员 不做该判断  未参加过考核也可以补考
            if ("2018".compareTo(doctor.getSessionNumber()) <= 0 && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isSkill) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(isTheory)) {
                // 2019/2018/2017级助理全科全走补考报名
                if("2019".compareTo(doctor.getSessionNumber()) >= 0 && doctor.getTrainingSpeId().equals("50")){
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }else{
                    //从未参加过考核
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSkillQualifed) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isTheoryQualifed)) {
                //3年内理论成绩和技能成绩都合格
                isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
            }
        }
        model.addAttribute("isAllowApply", isAllowApply);
//        model.addAttribute("isAllowApply", com.pinde.core.common.GlobalConstant.FLAG_Y);
        return "res/jswjw/signupMain";
    }

    @RequestMapping(value = "/signUp")
    public String signUp(Model model, String typeId, String userFlow) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/mainNew";
        }
        String currYear = DateUtil.getYear();
        model.addAttribute("currYear", currYear);
        SysUser user = jswjwBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        ResDoctor doctor = jswjwBiz.readDoctor(user.getUserFlow());
        model.addAttribute("doctor", doctor);

        ResDoctorRecruit resDoctorRecruit = null;
        if (doctor != null) {
            resDoctorRecruit = jswjwBiz.readResDoctorRecruitBySessionNumber(doctor.getDoctorFlow(), doctor.getSessionNumber());
        }
        ;
        if (resDoctorRecruit != null) {
            Map<String, List<Map<String, String>>> speMap = setSpeMap(model);
            List<Map<String, String>> spes = speMap.get(resDoctorRecruit.getSpeId());
            if (spes != null && spes.size() > 0) {
                model.addAttribute("needChange", com.pinde.core.common.GlobalConstant.FLAG_Y);
                model.addAttribute("spes", spes);
            } else {
                model.addAttribute("needChange", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
        }
        return "res/jswjw/signUp";
    }

    @RequestMapping(value = "/saveSignUp")
    public String saveSignUp(Model model, String theory, String skill, JsresExamSignup signup, String userFlow) {
        if(StringUtil.isBlank(theory) && StringUtil.isBlank(skill)){
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "请选择补考类型！");
            return "res/jswjw/saveSignUp";
        }
        List<String> signupTypeIds = new ArrayList<>();
        SysUser currentUser = jswjwBiz.readSysUser(userFlow);
        ResDoctor doctor = jswjwBiz.readDoctor(currentUser.getUserFlow());
        signup.setDoctorFlow(doctor.getDoctorFlow());
        signup.setDoctorTypeId(doctor.getDoctorTypeId());
        signup.setDoctorTypeName(doctor.getDoctorTypeName());
        String orgFlow = currentUser.getOrgFlow();
        //如果user表中没有org_flow去医师表中的
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        //如果当前学员是协同基地取他主基地所在的城市
        List<ResJointOrg> resJointOrgList = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
        if (resJointOrgList != null && resJointOrgList.size() > 0) {
            orgFlow = resJointOrgList.get(0).getOrgFlow();
        }

        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        List<ResTestConfig> resTestConfigList = jswjwBiz.findEffective(DateUtil.getCurrDateTime2(), sysOrg.getOrgCityId());
        if (resTestConfigList.size() > 0) {
            ResTestConfig resTestConfig = resTestConfigList.get(0);
            signup.setTestId(resTestConfig.getTestId());
            //判断需不需要基地审核，需要则是待基地审核，不要要再判断需不需要市局审核，需要则是待市局审核，都不需要则是待省厅审核
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
                signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
            } else {
                signup.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                signup.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
            }
        } else {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "当前没有正在进行的考试");
            return "res/jswjw/saveSignUp";
        }
        String isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
        String isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResScore> resScoreList = jswjwBiz.selectAllScore(currentUser.getUserFlow(), null);
        String validYear = String.valueOf(Integer.valueOf(DateUtil.getYear()) - 3);
        if (resScoreList.size() > 0) {
            //3年内的技能成绩集合
            List<ResScore> skillList = resScoreList.stream().filter(resScore -> "SkillScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
            //3年内的理论成绩集合
            List<ResScore> theoryList = resScoreList.stream().filter(resScore -> "TheoryScore".equals(resScore.getScoreTypeId()) && StringUtil.isNotBlank(resScore.getScorePhaseId()) && validYear.compareTo(resScore.getScorePhaseId()) < 0).collect(Collectors.toList());
            if (skillList.size() > 0) {
                for (ResScore resScore : skillList) {
                    if ("1".equals(String.valueOf(resScore.getSkillScore()))) {
                        //3年内的技能成绩有合格的
                        isSkillQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    }
                }
            }
            if (theoryList.size() > 0) {
                for (ResScore resScore : theoryList) {
                    if ("1".equals(String.valueOf(resScore.getTheoryScore()))) {
                        //3年内的理论成绩有合格的
                        isTheoryQualifed = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    }
                }
            }
        }
        int c = 0;
        if (StringUtil.isNotEmpty(theory)) {
            signupTypeIds.add(theory);
        }
        if (StringUtil.isNotEmpty(skill)) {
            signupTypeIds.add(skill);
        }
//        List<JsresExamSignup> signupLists = jswjwBiz.readDoctorExanSignUps(doctor.getDoctorFlow());
//        if(CollectionUtils.isNotEmpty(signupLists) && signupLists.size() == 2){
//            model.addAttribute("resultId", "30201");
//            model.addAttribute("resultType", "您已考核超过三次，无法进行补考申请。");
//            return "res/jswjw/saveSignUp";
//        }
        for (String signupTypeId : signupTypeIds) {
            List<JsresExamSignup> signupList = jswjwBiz.getSignUpListByYear(DateUtil.getYear(), doctor.getDoctorFlow(), signupTypeId);
            if (signupList != null && signupList.size() > 0) {
                for (JsresExamSignup jsresExamSignup : signupList) {
                    //如果有补考记录状态是待审核或者审核通过不允许多次提交
                    if (StringUtil.isNotBlank(jsresExamSignup.getAuditStatusId()) &&
                            ("Auditing".equals(jsresExamSignup.getAuditStatusId()) ||
                                    "WaitChargePass".equals(jsresExamSignup.getAuditStatusId()) ||
                                    "WaitGlobalPass".equals(jsresExamSignup.getAuditStatusId()) ||
                                    "GlobalPassed".equals(jsresExamSignup.getAuditStatusId()))) {
                        if ("Theory".equals(signupTypeId)) {
                            model.addAttribute("resultId", "30201");
                            model.addAttribute("resultType", "您已提交过补考报名，请勿重复提交。");
                            return "res/jswjw/saveSignUp";
                        }
                        if ("Skill".equals(signupTypeId)) {
                            model.addAttribute("resultId", "30201");
                            model.addAttribute("resultType", "您已提交过补考报名，请勿重复提交。");
                            return "res/jswjw/saveSignUp";
                        }
                    }
                }
            }
        }
        for (String signupTypeId : signupTypeIds) {
            signup.setSignupFlow("");
            signup.setSignupTypeId(signupTypeId);
            if ("Theory".equals(signupTypeId)) {
                signup.setSignupTypeName("理论补考");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isTheoryQualifed)) {
                    model.addAttribute("resultId", "30201");
                    model.addAttribute("resultType", "您的历史成绩中，有报名科目的合格记录，请勿重复报名");
                    return "res/jswjw/saveSignUp";
                }
            }
            if ("Skill".equals(signupTypeId)) {
                signup.setSignupTypeName("技能补考");
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isSkillQualifed)) {
                    model.addAttribute("resultId", "30201");
                    model.addAttribute("resultType", "您的历史成绩中，有报名科目的合格记录，请勿重复报名");
                    return "res/jswjw/saveSignUp";
                }
            }
            c += jswjwBiz.saveSignUp(signup, currentUser);
        }
        if (c == 0) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "报名失败！");
            return "res/jswjw/saveSignUp";
        }
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "报名成功！");
        return "res/jswjw/saveSignUp";
    }

    public void saveAsseScore(String score, String scoreType, String recruitFlow, String userFlow) throws DocumentException {
        SysUser currentUser = jswjwBiz.readSysUser(userFlow);
        ResScore resScore = new ResScore();
        if (StringUtil.isNotBlank(scoreType)) {
            resScore.setDoctorFlow(currentUser.getUserFlow());//医师流水号
            resScore.setScoreTypeId(com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId());
            resScore.setScoreTypeName(com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getName());
            resScore.setRecruitFlow(recruitFlow);
            if (scoreType.equals("theoryScore")) {
                if (StringUtil.isNotBlank(score)) {
                    BigDecimal bd = new BigDecimal(score);
                    resScore.setTheoryScore(bd);
                }
            } else if (scoreType.equals("skillScore")) {
                if (StringUtil.isNotBlank(score)) {
                    BigDecimal bd = new BigDecimal(score);
                    resScore.setSkillScore(bd);
                }
            } else {
                List<ResScore> scorelist = jswjwBiz.selectByRecruitFlowAndScoreType(recruitFlow, com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId());
                //公共成绩
                Map<String, String> extScoreMap = new HashMap<String, String>();
                extScoreMap.put("lawScore", "");
                extScoreMap.put("medicineScore", "");
                extScoreMap.put("clinicalScore", "");
                extScoreMap.put("ckScore", "");
                ResScore publicScore = null;
                if (null != scorelist && scorelist.size() > 0) {
                    publicScore = scorelist.get(0);
                    //公共科目成绩详情
                    String content = null == publicScore ? "" : publicScore.getExtScore();
                    if (StringUtil.isNotBlank(content)) {
                        Document doc = DocumentHelper.parseText(content);
                        Element root = doc.getRootElement();
                        Element extScoreInfo = root.element("extScoreInfo");
                        if (extScoreInfo != null) {
                            List<Element> extInfoAttrEles = extScoreInfo.elements();
                            if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                                for (Element attr : extInfoAttrEles) {
                                    String attrName = attr.getName();
                                    String attrValue = attr.getText();
                                    extScoreMap.put(attrName, attrValue);
                                }
                            }
                        }
                    }
                }
                if (StringUtil.isNotBlank(scoreType)) {
                    extScoreMap.put(scoreType, score);
                }
                String extScore = null;
                try {
                    extScore = jswjwBiz.convertMapToXml(extScoreMap, resScore);
                } catch (Exception e) {
                    logger.error("", e);
                }
                if (StringUtil.isNotBlank(extScore)) {
                    resScore.setExtScore(extScore);

                }
            }
        }
        int i = 0;
        jswjwBiz.savePublic(resScore, currentUser);
//        if(i == 1){
//            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
//        }else {
//            return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
//        }
    }

    @RequestMapping(value = {"/uploadApplicationFile"})
    public String uploadApplicationFile(MultipartFile file, String recruitFlow, String userFlow, Model model) throws Exception {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "上传成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (file != null && !file.isEmpty()) {
            String checkResult = jswjwBiz.checkImg(file);
            String resultPath = "";
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkResult)) {
                model.addAttribute("resultId", "31601");
                model.addAttribute("resultType", checkResult);
                return "res/jswjw/success";
            } else {
                resultPath = jswjwBiz.saveFileToDirs("", file, "jsresImages" + File.separator + "asseApplication");
                List<PubFile> files = pubFileBiz.findFileByTypeFlow("asseApplication", recruitFlow);
                PubFile pubFile = null;
                if (files != null && files.size() > 0) {
                    pubFile = files.get(0);
                } else {
                    pubFile = new PubFile();
                }
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                pubFile.setProductFlow(recruitFlow);
                pubFile.setFilePath(resultPath);
                pubFile.setFileName(file.getOriginalFilename());
                pubFile.setProductType("asseApplication");
                pubFileBiz.editFile(pubFile);
                if (StringUtil.isNotEmpty(resultPath)) {
                    SysCfg upload_base_url = cfgMapper.selectByPrimaryKey("upload_base_url");
                    model.addAttribute("resultPath", upload_base_url.getCfgValue() + "/" + resultPath.replaceAll("\\\\", "/"));
                }
            }
        }
        return "res/jswjw/success";
    }

    /**
     * 认证和修改手机号（发送验证码）
     */
    @RequestMapping(value = "/authenPhone2")
    public String authenPhone2(String userPhone, String systemFlag, Model model, HttpServletRequest request) {
        if (StringUtil.isBlank(userPhone)) {
            model.addAttribute("resultId", "3011103");
            model.addAttribute("resultType", "手机号为空");
            return "res/jswjw/authenPhone";
        }
        if ("0".equals(phoneRegex(userPhone).get(0))) {
            model.addAttribute("resultId", "3011104");
            model.addAttribute("resultType", phoneRegex(userPhone).get(1));
            return "res/jswjw/authenPhone";
        }
        SysUser sysUser = jswjwBiz.findByUserCode(userPhone);
        if (sysUser != null) {
            model.addAttribute("resultId", "3011105");
            model.addAttribute("resultType", "该手机号已绑定过账号");
            return "res/jswjw/authenPhone";
        }
        SMSUtil smsUtil = new SMSUtil(userPhone);
        int code = (int) ((Math.random() * 9 + 1) * 100000);
        model.addAttribute("userVerifyCode", code);
        HttpSession session = request.getSession();
        session.setAttribute("userVerifyCode", code);
        String currDateTime2 = DateUtil.getCurrDateTime2();
        model.addAttribute("verifyCodeTime", currDateTime2);
        session.setAttribute("verifyCodeTime", currDateTime2);
        SysSmsLog sSmsRecord = new SysSmsLog();
        if ("tjres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516954", "R101", code);
        }
        if ("jsres".equals(systemFlag)) {
            sSmsRecord = smsUtil.send("10001", "516946", "R101", code);
        }
        if (!"100".equals(sSmsRecord.getStatusCode())) {
            model.addAttribute("resultId", "3011106");
            model.addAttribute("resultType", sSmsRecord.getStatusName());
            return "res/jswjw/authenPhone";
        }
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "发送成功");
        model.addAttribute("userPhone", userPhone);
        return "res/jswjw/authenPhone";
    }

    /**
     * 判断是否在黑名单中
     */
    @RequestMapping(value = "/checkBlack")
    public String checkBlack(String idNo, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "");
        model.addAttribute("pageIndex", 0);
        if (StringUtil.isEmpty(idNo)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "idNo为空");
            return "res/jswjw/success";
        }
        String checkErrorMessage = "";
        int i = balcklistExtMapper.selectBlacklistByIdNo(idNo);
        if (i > 0) {
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "您的信息已被纳入我省医务人员诚信系统，5年内不得进入我省培训基地接受住院医师规范化培训。如有相关疑问，请与相关管理部门联系。");
            model.addAttribute("pageIndex", 1);
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }

    @RequestMapping(value = {"/registerNew"}, method = {RequestMethod.POST})
    public String registerNew(String userPhone, String userPasswd, String code, Model model, HttpServletRequest request) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "注册成功！");
        if (StringUtil.isEmpty(userPhone)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "userPhone为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(userPasswd)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "userPasswd为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(code)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "code为空");
            return "res/jswjw/success";
        }
        String currDateTime = DateUtil.getCurrDateTime2();
        HttpSession session = request.getSession();
        if (null != session.getAttribute("verifyCodeTime") && null != session.getAttribute("userVerifyCode")) {
            String verifyCodeTime = (String) session.getAttribute("verifyCodeTime");
            long betweenTowDate = DateUtil.signSecondsBetweenTowDate(currDateTime, verifyCodeTime);
            String userVerifyCode = session.getAttribute("userVerifyCode").toString();
            if (code.length() != 6) {
                model.addAttribute("resultId", "3011106");
                model.addAttribute("resultType", "验证码错误");
                return "res/jswjw/checkVerifyCode";
            }
            if (code.equals(userVerifyCode)) {
                if (betweenTowDate > 300) {
                    model.addAttribute("resultId", "3011107");
                    model.addAttribute("resultType", "验证码超时，请重新获取验证码！");
                    return "res/jswjw/checkVerifyCode";
                }
            } else {
                model.addAttribute("resultId", "3011108");
                model.addAttribute("resultType", "验证码错误");
                return "res/jswjw/checkVerifyCode";
            }
        }

        SysUser sysUser = jswjwBiz.findByUserCode(userPhone);
        if (sysUser == null) {
            jswjwBiz.saveUserInfo2(userPhone, userPasswd, code);
        } else {
            model.addAttribute("resultId", "3011105");
            model.addAttribute("resultType", "该手机号已绑定过账号");
            return "res/jswjw/success";
        }
        return "res/jswjw/success";
    }

    /**
     * 个人基本信息
     *
     * @param model
     * @return
     * @throws DocumentException
     */
    @RequestMapping(value = "/doctorInfo")
    public String doctorInfo(String userFlow, String viewFlag, Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "查询成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        if(null == sysUser){
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/success";
        }
        ResDoctor resDoctor = jswjwBiz.readDoctor(sysUser.getUserFlow());
        if (resDoctor != null) {
            if (StringUtil.isNotBlank(resDoctor.getGraduatedId())) {
                List<SysDict> sysDictList = jswjwBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
                if (sysDictList != null && !sysDictList.isEmpty()) {
                    for (SysDict dict : sysDictList) {
                        if (dict.getDictId().equals(resDoctor.getGraduatedId())) {
                            resDoctor.setGraduatedName(dict.getDictName());
                        }
                    }
                }
            }
            if (StringUtil.isNotBlank(resDoctor.getDoctorTypeId()) && "Graduate".equals(resDoctor.getDoctorTypeId())) {
                if (StringUtil.isNotBlank(resDoctor.getWorkOrgId())) {
                    List<SysDict> sysDictList = jswjwBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.SendSchool.getId());
                    if (sysDictList != null && !sysDictList.isEmpty()) {
                        for (SysDict dict : sysDictList) {
                            if (dict.getDictId().equals(resDoctor.getWorkOrgId())) {
                                resDoctor.setWorkOrgName(dict.getDictName());
                            }
                        }
                    }
                }
            }
        }
        model.addAttribute("user", sysUser);
        model.addAttribute("doctor", resDoctor);
        PubUserResume pubUserResume = jswjwBiz.readPubUserResume(userFlow);
        if (pubUserResume != null) {
            String xmlContent = pubUserResume.getUserResume();
            if (StringUtil.isNotBlank(xmlContent)) {
                //xml转换成JavaBean
                UserResumeExtInfoForm userResumeExt = null;
                userResumeExt = jswjwBiz.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if (userResumeExt != null) {
                    if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                        List<SysDict> sysDictList = jswjwBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
                        if (sysDictList != null && !sysDictList.isEmpty()) {
                            for (SysDict dict : sysDictList) {
                                if (StringUtil.isNotBlank(userResumeExt.getGraduatedId())) {
                                    if (dict.getDictId().equals(userResumeExt.getGraduatedId())) {
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
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setGraduationYear(DateUtil.getYear());
        recruit.setDoctorFlow(sysUser.getUserFlow());
        recruit.setAuditStatusId("Passed");
        List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
        String canSave = com.pinde.core.common.GlobalConstant.FLAG_Y;
        if (recruits != null && recruits.size() > 0) {
            String recruitFlow = recruits.get(0).getRecruitFlow();
            JsresGraduationApply apply = jswjwBiz.searchByRecruitFlow(recruitFlow, recruits.get(0).getGraduationYear());
            if (apply != null) {
                if ("WaitChargePass".equals(apply.getAuditStatusId())) {
                    canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                } else if ("WaitGlobalPass".equals(apply.getAuditStatusId())) {
                    canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                } else if ("GlobalPassed".equals(apply.getAuditStatusId())) {
                    canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
        }
        boolean isPassed = jswjwBiz.getRecruitStatus(sysUser.getUserFlow());
        model.addAttribute("isPassed", isPassed);
        model.addAttribute("canSave", canSave);
        String editFlag = InitConfig.getSysCfg("assess_doctor_edit_info");
//        //学员保存个人信息放开
//        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewFlag)) {
//            return "jsres/doctorInfo";
//        }
//        //com.pinde.core.common.GlobalConstant.FLAG_N.equals(canSave) 含义为有一条培训记录，结业考核年份是当前年，并且结业资格审查省厅通过的。学员无法修改个人信息 并且审核期间学员是否修改个人信息为否时
//        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag) && com.pinde.core.common.GlobalConstant.FLAG_N.equals(canSave)) {
//            model.addAttribute("isDoctor", com.pinde.core.common.GlobalConstant.FLAG_Y);
//            return "jsres/doctorInfo";
//        }
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        model.addAttribute("upload_base_url", uploadBaseUrl);
        return "/res/jswjw/editDoctorInfo";
    }


    /**
     * 保存个人基本信息
     *
     * @param doctorInfoForm
     * @return
     */
    @RequestMapping(value = "/saveDoctorInfo")
    public String saveDoctorInfo(JsResDoctorInfoForm doctorInfoForm, String qtCountry, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if (null == doctorInfoForm) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "参数错误");
            return "res/jswjw/success";
        }
        SysUser sysUser = doctorInfoForm.getUser();
        String checkResult = checkUserUnique(sysUser, sysUser.getUserFlow());
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkResult)) {
            model.addAttribute("resultId", "3011105");
            model.addAttribute("resultType", checkResult);
            return "res/jswjw/success";
        }
        ResDoctor doctor = doctorInfoForm.getDoctor();
        UserResumeExtInfoForm userResumeExt = doctorInfoForm.getUserResumeExt();
        String isMasterHaveDiploma = userResumeExt.getIsMasterHaveDiploma();
        //qtCountry:如果不是中国，设置地区
        int result = jswjwBiz.saveDoctorInfo(sysUser, doctor, userResumeExt,qtCountry);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setGraduationYear(DateUtil.getYear());
            recruit.setDoctorFlow(sysUser.getUserFlow());
            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
            List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwBiz.readDoctorRecruits(recruit);
            String canSave = com.pinde.core.common.GlobalConstant.FLAG_Y;
            if (recruits != null && recruits.size() > 0) {
                String recruitFlow = recruits.get(0).getRecruitFlow();
                JsresGraduationApply apply = jswjwBiz.searchByRecruitFlow(recruitFlow, recruits.get(0).getGraduationYear());
                if (apply != null) {
                    if (com.pinde.core.common.enums.JsResAsseAuditListEnum.WaitChargePass.getId().equals(apply.getAuditStatusId())) {
                        canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                    } else if (com.pinde.core.common.enums.JsResAsseAuditListEnum.WaitGlobalPass.getId().equals(apply.getAuditStatusId())) {
                        canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                    } else if (com.pinde.core.common.enums.JsResAsseAuditListEnum.GlobalPassed.getId().equals(apply.getAuditStatusId())) {
                        canSave = com.pinde.core.common.GlobalConstant.FLAG_N;
                    }
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(canSave)) {
                        tempMapper.updateRecruitAsseInfoByApplyYear(apply.getApplyFlow(), sysUser.getUserFlow());
                    }
                }
            }
            return "res/jswjw/success";
        }
        model.addAttribute("resultId", "30403");
        model.addAttribute("resultType", "保存失败！");
        return "res/jswjw/success";
    }


    public String checkUserUnique(SysUser sysUser, String userFlow) {
        SysUser exitUser = null;
        //身份证号唯一
        String idNo = sysUser.getIdNo().toUpperCase();////身份证X大写
        if (StringUtil.isNotBlank(idNo)) {
            exitUser = jswjwBiz.findByIdNo(idNo.trim());
            if (exitUser != null) {
                if (StringUtil.isNotBlank(userFlow)) {
                    if (!exitUser.getUserFlow().equals(userFlow)) {
                        return "该身份证号已经被注册！";
                    }
                } else {
                    return "该身份证号已经被注册！";
                }
            }
        }
        //手机号唯一
        String userPhone = sysUser.getUserPhone();
        if (StringUtil.isNotBlank(userPhone)) {
            exitUser = jswjwBiz.findByUserPhone(userPhone.trim());
            if (exitUser != null) {
                if (StringUtil.isNotBlank(userFlow)) {
                    if (!exitUser.getUserFlow().equals(userFlow)) {
                        return "该手机号已经被注册！";
                    }
                } else {
                    return "该手机号已经被注册！";
                }
            }
        }
        return com.pinde.core.common.GlobalConstant.FLAG_Y;
    }

    /**
     * 招录计划
     */
    @RequestMapping(value="/doctorPlan")
    public String doctorPlan(String assignYear , Integer pageIndex, Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String,Object> paramMap = new HashMap<>();
        if(StringUtil.isBlank(assignYear)){
            //默认查询正在进行的招录计划
            assignYear = DateUtil.getYear();
            paramMap.put("currDate",DateUtil.getCurrDate());
        }
        paramMap.put("assignYear",assignYear);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> resultMapList = jswjwBiz.searchAssignInfoListNew(paramMap);
        model.addAttribute("resultMapList",resultMapList);
        model.addAttribute("dataCount", resultMapList.size());
        return "/res/jswjw/doctorPlanList";
    }

    @RequestMapping(value="/doctorPlanInfo")
    public String doctorPlanInfo(String assignYear,String userFlow,String orgFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "/res/jswjw/doctorPlanView";
        }
        SysOrg sysOrg = jswjwBiz.getOrg(orgFlow);
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("orgFlow", orgFlow);
        paramMap.put("assignYear", assignYear);
        model.addAttribute("assignYear", assignYear);
        // 查询报考专业信息
        List<Map<String, String>> orgSpeList = jswjwBiz.searchAssignOrgSpeListNew(paramMap);
        model.addAttribute("orgSpeList", orgSpeList);
        model.addAttribute("sysOrg", sysOrg);
        //判断招录计划是否开始
        String signupFlag = com.pinde.core.common.GlobalConstant.FLAG_N;//不可报名
        if(null != orgSpeList && orgSpeList.size()>0){
            String currDate = DateUtil.getCurrDate();
            Map<String,String> map = orgSpeList.get(0);
            String startTime = map.get("START_TIME");
            String endTime = map.get("END_TIME");
            if(startTime.compareTo(currDate)>0 || currDate.compareTo(endTime)>0){
                model.addAttribute("signupMsg", "招录时间未到！");
                model.addAttribute("signupBtnFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
            }else{
                signupFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(signupFlag)) {
            // 判断学员是否可以报名
            String signupMsg = jswjwBiz.doctorSignupFlagNew(userFlow);
            if (StringUtil.isNotBlank(signupMsg)) {
                model.addAttribute("signupMsg", signupMsg);
                model.addAttribute("signupBtnFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
            } else {
                model.addAttribute("signupBtnFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }
        }
        String speFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        List<Map<String,String>> docSpeList = jswjwBiz.searchDoctorSpe();
        if(null != docSpeList && docSpeList.size()>0){
            for (Map<String,String> spe:docSpeList) {
                String idNo = spe.get("ID_NO");
                String doctorTypeId = spe.get("DOCTOR_TYPE_ID");
                if(idNo.equals(sysUser.getIdNo()) && !"Graduate".equals(doctorTypeId)){
                    speFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    break;
                }
            }
        }
        model.addAttribute("speFlag",speFlag);
        return "/res/jswjw/doctorPlanView";
    }


    @RequestMapping("/showPlanSpeDesc")
    public String showPlanSpeDesc(String assignFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        // 查询招生计划信息
        ResOrgSpeAssign resOrgSpeAssign = jswjwBiz.getResOrgSpeAssignInfo(assignFlow);
        model.addAttribute("assign", resOrgSpeAssign);
        return "/res/jswjw/planSpeDescView";
    }

    @RequestMapping("/editDoctorRecruit")
    public String editDoctorRecruit(String recruitFlow, String speId, String orgFlow, String userFlow, String assignYear, Model model,HttpServletRequest request) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        String doctorFlow = userFlow;
        model.addAttribute("userFlow", userFlow);
        ResDoctorRecruit doctorRecruit = null;
        doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
        model.addAttribute("doctorRecruit", doctorRecruit);
        model.addAttribute("addRecord", com.pinde.core.common.GlobalConstant.FLAG_Y);//添加新的培训记录标识
        //根据专业ID speId ,机构流水号 orgFlow 查询对应的名称
        SysOrgExample example = new SysOrgExample();
        SysOrgExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        criteria.andOrgFlowEqualTo(orgFlow);
        example.setOrderByClause("ORDINAL");
        List<SysOrg> orgList = jswjwBiz.searchOrgByExample(example);

        //根据机构ID和年份查询 专业
        List<ResOrgSpeAssign> speAssignList = jswjwBiz.searchSpeAssign(orgFlow, assignYear);
        String speName = "";
        if (speAssignList != null && speAssignList.size() > 0) {
            for (ResOrgSpeAssign rosa : speAssignList) {
                if (rosa.getSpeId().equals(speId)) {
                    speName = rosa.getSpeName();
                }
            }
        }
        //根据协同基地ID 查询 主基地与协同基地的关联表，确认当前报考的是否是主基地
        //表示如果查到的为空 表示当前选中的基地是主基地 反之为协同基地
        List<ResJointOrg> joinOrgs = jswjwBiz.searchResJointByJointOrgFlow(orgFlow);
        //协同基地ID 名称 是否在协同基地培训
        String jointOrgFlow = "";
        String jointOrgName = "";
        String inJointOrgTrain = "";
        String orgName = "";
        String placeId = "";
        String placeName = "";
        if (joinOrgs.size() == 0) {
            jointOrgFlow = "";
            jointOrgName = "";
            inJointOrgTrain = com.pinde.core.common.GlobalConstant.FLAG_N;
            if (orgList.size() == 0){
                placeId  = "";
                placeName  = "";
            }
            else {
                placeId = orgList.get(0).getOrgCityId();
                placeName = orgList.get(0).getOrgCityName();
                orgFlow = orgList.get(0).getOrgFlow();
                orgName = orgList.get(0).getOrgName();
            }
        } else {
            jointOrgFlow = joinOrgs.get(0).getJointOrgFlow();
            jointOrgName = joinOrgs.get(0).getJointOrgName();
            //从关联表中查询主基地名称与ID
            orgFlow = joinOrgs.get(0).getOrgFlow();
            orgName = joinOrgs.get(0).getOrgName();
            inJointOrgTrain = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }

        model.addAttribute("speId", speId);
        model.addAttribute("speName", speName);
        model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("jointOrgFlow", jointOrgFlow);
        model.addAttribute("jointOrgName", jointOrgName);
        model.addAttribute("inJointOrgTrain", inJointOrgTrain);
        //从关联表中查询主基地名称与ID
        model.addAttribute("orgName",orgName);
        model.addAttribute("placeId", placeId);
        model.addAttribute("placeName", placeName);
        model.addAttribute("assignYear", assignYear);
        //已审核通过
        ResDoctorRecruit passedRec = new ResDoctorRecruit();
        passedRec.setDoctorFlow(doctorFlow);
        passedRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        passedRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<com.pinde.core.model.ResDoctorRecruit> passedRecruitList = jswjwBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
        //其中一阶段、住院医师审核通过（选二阶段使用）
        List<com.pinde.core.model.ResDoctorRecruit> prevPassedList = new ArrayList<com.pinde.core.model.ResDoctorRecruit>();
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            model.addAttribute("passedRecruitList", passedRecruitList);
            //记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
            List<String> catSpeIdList = new ArrayList<String>();
            for (ResDoctorRecruit rec : passedRecruitList) {
                String catSpeId = rec.getCatSpeId();
                if (!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())) {
                    catSpeIdList.add(catSpeId);
                }
                if (StringUtil.isBlank(rec.getSpeId())) {//首条为二阶段
                    model.addAttribute("firstRecIsWMSecond", true);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())) {
                    prevPassedList.add(rec);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())) {//二阶段审核通过
                    model.addAttribute("isWMSecondRecPassed", true);
                }
            }
            model.addAttribute("catSpeIdList", catSpeIdList);
        }
        if (prevPassedList != null && !prevPassedList.isEmpty()) {
            model.addAttribute("prevPassedList", prevPassedList);
            if (StringUtil.isNotBlank(recruitFlow)) {//回显上一阶段  结业证书
                model.addAttribute("latestPrevPassed", prevPassedList.get(0));
            }
        }
        return "/res/jswjw/doctorPlanInfoRecruit";

    }

    @RequestMapping(value="/saveResDoctorRecruit", method={RequestMethod.POST})
    public String saveResDoctorRecruit(ResDoctorRecruitInfo resDoctorRecruitInfo,
                                       ResDoctorRecruitWithBLOBs docRecWithBLOBs, String prevRecruitFlow,
                                       String prevCompleteFileUrl, String prevCompleteCertNo, Model model, String userFlow) throws ParseException, DocumentException {

        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "报名成功");
        String doctorFlow = docRecWithBLOBs.getDoctorFlow();
        docRecWithBLOBs.setSignupWay("DoctorSignup");//学员报名方式 （DoctorSend：报送，DoctorSignup:报名）
        //查询学员提交报名次数，最多报名3次
        int signupCount = jswjwBiz.findSignupCount(doctorFlow,docRecWithBLOBs.getRecruitYear());
        if(signupCount >= 3){
            model.addAttribute("resultId", "30403");
            model.addAttribute("resultType", "操作失败！");
            return "res/jswjw/success";
        }
        //设置 报名状态是待审核 医师状态待审核 begin
        docRecWithBLOBs.setAuditStatusId(BaseStatusEnum.Auditing.getId());
        docRecWithBLOBs.setAuditStatusName(BaseStatusEnum.Auditing.getName());
        //报名为协同基地 则协同基地审核后主基地审核
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(docRecWithBLOBs.getInJointOrgTrain())) {
            docRecWithBLOBs.setDoctorStatusId(BaseStatusEnum.Auditing.getId());
            docRecWithBLOBs.setDoctorStatusName(BaseStatusEnum.Auditing.getName());
            docRecWithBLOBs.setJointOrgAudit(BaseStatusEnum.Auditing.getId());
        }else {
            docRecWithBLOBs.setDoctorStatusId("OrgAuditing");
            docRecWithBLOBs.setDoctorStatusName("待主基地审核");
            docRecWithBLOBs.setJointOrgAudit(BaseStatusEnum.Passed.getId());
        }

        docRecWithBLOBs.setTrainYear("ThreeYear");
        //设置 报名状态是待审核 医师状态待审核 end at 2020.6.7
        if (!com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(docRecWithBLOBs.getCatSpeId())) {
            docRecWithBLOBs.setCompleteFileUrl("");
            docRecWithBLOBs.setCompleteCertNo("");
            if("21".equals(docRecWithBLOBs.getDoctorStatusId())){
                //由于页面字段展示问题现将非二阶段的该条记录的结业附件
                // name = prevCompleteFileUrl 存入special_file_url（该条记录的结业附件）中
                docRecWithBLOBs.setSpecialFileUrl(prevCompleteFileUrl);
                docRecWithBLOBs.setSpecialCertNo(prevCompleteCertNo);
            }else {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }

            if(StringUtil.isBlank(docRecWithBLOBs.getCatSpeId())){
                ResOrgSpe serach = new ResOrgSpe();
                serach.setOrgFlow(docRecWithBLOBs.getOrgFlow());
                serach.setSpeId(docRecWithBLOBs.getSpeId());
                serach.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                List<ResOrgSpe> resBaseList = jswjwBiz.searchResOrgSpeList(serach);
                if(resBaseList != null && resBaseList.size()>0){
                    ResOrgSpe resOrgSpe = resBaseList.get(0);
                    docRecWithBLOBs.setCatSpeId(resOrgSpe.getSpeTypeId());
                    docRecWithBLOBs.setCatSpeName(com.pinde.core.common.enums.TrainCategoryEnum.getNameById(resOrgSpe.getSpeTypeId()));
                }
            }

        }else {
            docRecWithBLOBs.setCompleteFileUrl(prevCompleteFileUrl);
            docRecWithBLOBs.setCompleteCertNo(prevCompleteCertNo);
            if(!"21".equals(docRecWithBLOBs.getDoctorStatusId())){
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        }
        try {
            ResDoctor resDoctor = jswjwBiz.findByFlow(docRecWithBLOBs.getDoctorFlow());
            resDoctor.setDoctorStatusId("Auditing");
            resDoctor.setDoctorStatusName("报名审核中");
            jswjwBiz.editDoctor(resDoctor);
        } catch (Exception e) {
            logger.error("", e);
        }
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        //查询是否重培（有退培记录且审核通过为重培） 默认否
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(docRecWithBLOBs.getDoctorFlow());
        docotrDelayTeturn.setTypeId("ReturnTraining");
        List<String> flags = new ArrayList<>();
        flags.add("1");
        List<ResDocotrDelayTeturn> resRecList = jswjwBiz.searchInfo(docotrDelayTeturn,null,flags,null);
        if(resRecList.size()>0){
            docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        }else{
            //非结业记录 判断入培时间 + 培训年限 + 3年  如果没结业 则为重培
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchRecruitList(docRecWithBLOBs.getDoctorFlow());
            if(CollectionUtils.isNotEmpty(recruitList)){
                for (ResDoctorRecruit resDoctorRecruit : recruitList) {
                    //20 在培 21结业
                    int trainYear = 0;
                    if(StringUtil.isNotEmpty(resDoctorRecruit.getDoctorStatusId()) && !"21".equals(resDoctorRecruit.getDoctorStatusId())){
                        //培训年限
                        if(StringUtil.isNotEmpty(resDoctorRecruit.getTrainYear())){
//                    'OneYear' then '一年' 'TwoYear' then '两年' 'ThreeYear' then '三年'
                            if("OneYear".equals(resDoctorRecruit.getTrainYear())){
                                trainYear = 1;
                            }else if("TwoYear".equals(resDoctorRecruit.getTrainYear())){
                                trainYear = 2;
                            }else if("ThreeYear".equals(resDoctorRecruit.getTrainYear())){
                                trainYear = 3;
                            }
                        }
                        if(StringUtil.isNotEmpty(resDoctorRecruit.getRecruitDate())){
                            //培训起始时间
                            String recruitDate = resDoctorRecruit.getRecruitDate();
                            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cd=Calendar.getInstance();
                            try{
                                cd.setTime(sdf.parse(recruitDate));
                            }catch(ParseException e){
                                logger.error("", e);
                            }
                            cd.add(Calendar.YEAR,trainYear+3);//增加n年
                            String format = sdf.format(cd.getTime());
                            String currDate = DateUtil.getCurrDate();

                            //当前时间
                            Date fromDate1 = sdf.parse(currDate);
                            Date toDate1 = sdf.parse(format);
                            Date d1 = new Date(fromDate1.getTime());
                            Date d2 = new Date(toDate1.getTime());
                            int num = d1.compareTo(d2);
                            //条件满足 为重培
                            if(num>0){
                                docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                            }
                        }
                    }
                }
            }
        }
        int result = jswjwBiz.saveDoctorRecruit(docRecWithBLOBs);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return "res/jswjw/success";
        }
        model.addAttribute("resultId", "30403");
        model.addAttribute("resultType", "保存失败！");
        return "res/jswjw/success";
    }

    @RequestMapping(value="/doctorRegister")
    public String doctorRegister(String assignYear , String userFlow ,String orgFlow , Integer pageIndex, Integer pageSize, Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        if(null == sysUser){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/success";
        }
        //构建查询对象 填充属性 begin
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        PageHelper.startPage(pageIndex, pageSize);
        recruit.setDoctorFlow(userFlow);
        recruit.setRecruitYear(assignYear);
        recruit.setOrgFlow(orgFlow);
        //构建查询对象end
        //根据当前用户的 userFlow  查询属于自己的报名信息
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "");
        model.addAttribute("recruitList",recruitList);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("userName",sysUser.getUserName());
        return "/res/jswjw/doctorRegister";
    }

    @RequestMapping(value="/detailRegister")
    public String detailRegister(String recruitFlow,String userFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if(null == currUser){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/success";
        }
        String doctorFlow = currUser.getUserFlow();
        //构建报名信息类的对象 用于操作数据库
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        ResDoctorRecruitWithBLOBs docRecWithBLOBs = new ResDoctorRecruitWithBLOBs();
        docRecWithBLOBs.setDoctorFlow(doctorFlow);
        docRecWithBLOBs.setRecruitFlow(recruitFlow);
        List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwBiz.searchResDoctorRecruitList(docRecWithBLOBs, "");
        model.addAttribute("currUser",currUser);
        ResDoctorRecruit resDoctorRecruit = new ResDoctorRecruit();
        if (recruits.size() > 0){
            resDoctorRecruit = recruits.get(0);
        }
        model.addAttribute("recruit",resDoctorRecruit);
        List<JsResDoctorRecruitExt> recruitExtList = jswjwBiz.resDoctorRecruitExtList3New(resDoctorRecruit);
        Map<String,String> rankNumMap = new HashMap<>();
        if(null != recruitExtList && recruitExtList.size()>0){
            for (JsResDoctorRecruitExt ext:recruitExtList) {
                rankNumMap.put(ext.getRecruitFlow()+ext.getDoctorFlow(),ext.getRankNum());
            }
        }
        model.addAttribute("rankNumMap", rankNumMap);
        return "/res/jswjw/doctorRegisterDetail";
    }

    @RequestMapping("/editDoctorRecruitNew")
    public String editDoctorRecruitNew(String recruitFlow,String userFlow, String doctorStatus, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if(null == currUser){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/success";
        }
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit doctorRecruit = null;
        String addFlag = "";
        if (StringUtil.isNotBlank(recruitFlow)) {
            addFlag = "edit";
            doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
            model.addAttribute("catSpeId", doctorRecruit.getCatSpeId());
            model.addAttribute("catSpeName", doctorRecruit.getCatSpeName());
            model.addAttribute("doctorRecruit", doctorRecruit);
            model.addAttribute("addRecord", com.pinde.core.common.GlobalConstant.FLAG_Y);//添加新的培训记录标识
        }
        //已审核通过
        ResDoctorRecruit passedRec = new ResDoctorRecruit();
        passedRec.setDoctorFlow(doctorFlow);
        passedRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        passedRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<com.pinde.core.model.ResDoctorRecruit> passedRecruitList = jswjwBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
        //其中一阶段、住院医师审核通过（选二阶段使用）
        List<com.pinde.core.model.ResDoctorRecruit> prevPassedList = new ArrayList<com.pinde.core.model.ResDoctorRecruit>();
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            model.addAttribute("passedRecruitList", passedRecruitList);
            //记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
            List<String> catSpeIdList = new ArrayList<String>();
            for (ResDoctorRecruit rec : passedRecruitList) {
                String catSpeId = rec.getCatSpeId();
                if (!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())) {
                    catSpeIdList.add(catSpeId);
                }
                if (StringUtil.isBlank(rec.getSpeId())) {//首条为二阶段
                    model.addAttribute("firstRecIsWMSecond", true);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())) {
                    prevPassedList.add(rec);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())) {//二阶段审核通过
                    model.addAttribute("isWMSecondRecPassed", true);
                }
            }
            model.addAttribute("catSpeIdList", catSpeIdList);
        }
        if (prevPassedList != null && !prevPassedList.isEmpty()) {
            model.addAttribute("prevPassedList", prevPassedList);
            if (StringUtil.isNotBlank(recruitFlow)) {//回显上一阶段  结业证书
                model.addAttribute("latestPrevPassed", prevPassedList.get(0));
            }
        }
        SysCfg startDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_start");
        SysCfg endDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_end");
        model.addAttribute("startDate",startDate.getCfgValue());
        model.addAttribute("endDate",endDate.getCfgValue());
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        model.addAttribute("upload_base_url", uploadBaseUrl);
        if ("report".equals(doctorStatus)) {
            return "/res/jswjw/editReportInfo";
        } else {
            return "/res/jswjw/editReportInfo";
        }
    }

    @RequestMapping(value="/doRegister")
    public String doRegister(com.pinde.core.model.ResDoctorRecruit recruit, String userFlow, String prevRecruitFlow, String prevCompleteFileUrl, String prevCompleteCertNo, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "报到成功！");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if(null == currUser){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/success";
        }
        String doctorFlow = currUser.getUserFlow();
        //构建报名信息类的对象 用于操作数据库
//        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        ResDoctorRecruitWithBLOBs docRecWithBLOBs = new ResDoctorRecruitWithBLOBs();
        docRecWithBLOBs.setDoctorFlow(doctorFlow);
        docRecWithBLOBs.setRecruitFlow(recruit.getRecruitFlow());
        docRecWithBLOBs.setProveFileUrl(recruit.getProveFileUrl());//减免附件
        if("21".equals(docRecWithBLOBs.getDoctorStatusId())){
            docRecWithBLOBs.setSpecialFileUrl(prevCompleteFileUrl);
            docRecWithBLOBs.setSpecialCertNo(prevCompleteCertNo);
        }

        //确认报到之前，先查询该条记录是否已被基地录取
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(docRecWithBLOBs, "");
        boolean recruitFlag = false;
        if(null != recruitList && recruitList.size() >= 0){
            recruitFlag = com.pinde.core.common.GlobalConstant.FLAG_Y.equalsIgnoreCase(recruitList.get(0).getRecruitFlag()) ? true : false;
            if (!recruitFlag) {
                model.addAttribute("resultId", "30404");
                model.addAttribute("resultType", "需要等待基地确认录取，学员方可确认报到");
                return "res/jswjw/success";
            }
            boolean confirmFlag = com.pinde.core.common.GlobalConstant.FLAG_Y.equalsIgnoreCase(recruitList.get(0).getConfirmFlag()) ? true : false;
            if (confirmFlag) {
                model.addAttribute("resultId", "30404");
                model.addAttribute("resultType", "已经报到过了，无需重复确认");
                return "res/jswjw/success";
            }
        }
        //confirmFlag字段 当学员确认报到 置为Y 表示学员已报到
        docRecWithBLOBs.setConfirmFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);

        //查询专业轮转方案 设置学员轮转方案
        String catSpeId = recruit.getCatSpeId();
        SchRotation rotation = new SchRotation();
        // 此处不能直接使用住院医师类型，助理全科无法查到培训方案
        rotation.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId());
        if(RecDocCategoryEnum.AssiGeneral.getId().equals(catSpeId)){
            rotation.setDoctorCategoryId(catSpeId);
        }
        String speId = recruit.getSpeId();
        rotation.setSpeId(speId);
        rotation.setPublishFlag(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SchRotation> rotationList = jswjwBiz.searchOrgStandardRotation(rotation);
        if(null != rotationList && rotationList.size()>0){
            // 判断如果是全科 使用2020年新培训方案 需求1453
            if("50".equals(speId)){
                for (SchRotation schRotation: rotationList ) {
                    if(schRotation.getRotationName().contains("2020西医助理")){
                        docRecWithBLOBs.setRotationFlow(schRotation.getRotationFlow());
                        docRecWithBLOBs.setRotationName(schRotation.getRotationName());
                        break;
                    }
                }
                if (StringUtil.isBlank(docRecWithBLOBs.getRotationFlow())) {
                    model.addAttribute("resultId", "30404");
                    model.addAttribute("resultType", "助理全科暂停使用旧方案，请维护助理全科专业2020西医助理全科培训方案");
                    return "res/jswjw/success";
                }
            }else{
                docRecWithBLOBs.setRotationFlow(rotationList.get(0).getRotationFlow());
                docRecWithBLOBs.setRotationName(rotationList.get(0).getRotationName());
            }
        }
        docRecWithBLOBs.setCurrDegreeCategoryId(recruit.getCurrDegreeCategoryId());
        docRecWithBLOBs.setRecruitDate(recruit.getRecruitDate());
        docRecWithBLOBs.setSessionNumber(recruit.getSessionNumber());
        docRecWithBLOBs.setTrainYear(recruit.getTrainYear());
        docRecWithBLOBs.setYetTrainYear(recruit.getYetTrainYear());
        docRecWithBLOBs.setDoctorStatusId(recruit.getDoctorStatusId());
        docRecWithBLOBs.setDoctorStrikeId(recruit.getDoctorStrikeId());
        docRecWithBLOBs.setCatSpeId(recruit.getCatSpeId());
        docRecWithBLOBs.setCatSpeName(recruit.getCatSpeName());
        //医师状态
        if(StringUtil.isNotBlank(recruit.getDoctorStatusId())){
            docRecWithBLOBs.setDoctorStatusName(com.pinde.core.common.enums.DictTypeEnum.DoctorStatus.getDictNameById(recruit.getDoctorStatusId()));
        }
        //医师走向
        if(StringUtil.isNotBlank(recruit.getDoctorStrikeId())){
            docRecWithBLOBs.setDoctorStrikeName(com.pinde.core.common.enums.DictTypeEnum.DoctorStrike.getDictNameById(recruit.getDoctorStrikeId()));
        }
        if(StringUtil.isNotBlank(recruit.getCurrDegreeCategoryId())){
            docRecWithBLOBs.setCurrDegreeCategoryName(com.pinde.core.common.enums.JsResDegreeCategoryEnum.getNameById(recruit.getCurrDegreeCategoryId()));
        }

        //结业审核年份
        int year=0;
        if(StringUtil.isNotBlank(docRecWithBLOBs.getSessionNumber())&&StringUtil.isNotBlank(docRecWithBLOBs.getTrainYear())){
            int num=0;
            if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(docRecWithBLOBs.getTrainYear())) {
                num=1;
            }
            if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(docRecWithBLOBs.getTrainYear())) {
                num=2;
            }
            if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(docRecWithBLOBs.getTrainYear())) {
                num=3;
            }
            year = Integer.parseInt(docRecWithBLOBs.getSessionNumber())+num;
            docRecWithBLOBs.setGraduationYear(year+"");
        }
        //报名通道确认报到后进去报到审核
        docRecWithBLOBs.setAuditStatusId("Auditing");
        docRecWithBLOBs.setAuditStatusName("待报到审核");
        if(StringUtil.isNotBlank(recruitList.get(0).getJointOrgFlow())){
            docRecWithBLOBs.setJointOrgAudit("Auditing");
            docRecWithBLOBs.setOrgAudit("Auditing");
        }else{
            docRecWithBLOBs.setJointOrgAudit("Passed");
            docRecWithBLOBs.setOrgAudit("Auditing");
        }
        int result = jswjwBiz.saveDoctorRecruit(docRecWithBLOBs);
        if(result > 0){
            return "res/jswjw/success";
        }
        model.addAttribute("resultId", "30403");
        model.addAttribute("resultType", "交易失败！");
        return "res/jswjw/success";
    }

    @RequestMapping(value = {"/uploadDoctorFile"})
    public String uploadDoctorFile(MultipartFile file, String userFlow,String fileType, Model model) throws Exception {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "上传成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (file != null && !file.isEmpty()) {
            String checkResult = "";
            if(StringUtil.isNotEmpty(fileType)){
                if("idCard".equals(fileType)){
                    //身份证图片校验
                    checkResult = jswjwBiz.checkUserHeader(file);
                }else{
                    checkResult = jswjwBiz.checkImg(file);
                }

            }
            String resultPath = "";
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(checkResult)) {
                model.addAttribute("resultId", "31601");
                model.addAttribute("resultType", checkResult);
                return "res/jswjw/success";
            } else {
                if(StringUtil.isNotEmpty(fileType)){
                    if("idCard".equals(fileType)){
                        //身份证
                        resultPath = jswjwBiz.saveFileToDirsNew("", file, "idCard",fileType);
                    }else if("userImages".equals(fileType)){
                        //头像
                        resultPath = jswjwBiz.saveFileToDirsNew("", file, "userImages",fileType);
                    }else{
                        resultPath = jswjwBiz.saveFileToDirsNew("", file, "jsresImages",fileType);
                    }
                }
                if (StringUtil.isNotEmpty(resultPath)) {
                    SysCfg upload_base_url = cfgMapper.selectByPrimaryKey("upload_base_url");
                    model.addAttribute("showPath",  upload_base_url.getCfgValue()+ "/" +resultPath.replaceAll("\\\\", "/"));
                    model.addAttribute("resultPath",  resultPath.replaceAll("\\\\", "/"));
                }
            }
        }else{
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "文件为空！");
        }
        return "res/jswjw/success";
    }

    @RequestMapping(value = "/doctorMain")
    public String doctorMain(String userFlow,Model model) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        SysCfg sysCfg = cfgMapper.selectByPrimaryKey("jsres_is_train");
        model.addAttribute("jsres_is_train",sysCfg.getCfgValue());
        String isRetrain = com.pinde.core.common.GlobalConstant.FLAG_N;
        String isDoctorSend = com.pinde.core.common.GlobalConstant.FLAG_N;
        //获取培训记录
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        String doctorFlow = currUser.getUserFlow();
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
        if (recruitList != null && !recruitList.isEmpty()) {
            List<com.pinde.core.model.ResDoctorRecruit> doctorSend = recruitList.stream().filter(r -> StringUtil.isNotEmpty(r.getSignupWay()) && r.getSignupWay().equals("DoctorSend")).collect(Collectors.toList());
            if(CollectionUtils.isNotEmpty(doctorSend)){
                String recruitFlow = doctorSend.get(0).getRecruitFlow();
                model.addAttribute("recruitFlow",recruitFlow);
                isDoctorSend = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
            model.addAttribute("recruitList", recruitList);
            for (ResDoctorRecruit rec : recruitList) {
                if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotSubmit.getId().equals(rec.getAuditStatusId())
                        || com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId().equals(rec.getAuditStatusId())
                        || com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getId().equals(rec.getAuditStatusId())) {
                    model.addAttribute("unPassed", true);
                }
                //20 在培 21结业
                int trainYear = 0;
                if (StringUtil.isNotEmpty(rec.getDoctorStatusId()) && !"21".equals(rec.getDoctorStatusId())) {
                    //培训年限
                    if (StringUtil.isNotEmpty(rec.getTrainYear())) {
//                    'OneYear' then '一年' 'TwoYear' then '两年' 'ThreeYear' then '三年'
                        if ("OneYear".equals(rec.getTrainYear())) {
                            trainYear = 1;
                        } else if ("TwoYear".equals(rec.getTrainYear())) {
                            trainYear = 2;
                        } else if ("ThreeYear".equals(rec.getTrainYear())) {
                            trainYear = 3;
                        }
                    }
                    if (StringUtil.isNotEmpty(rec.getRecruitDate())) {
                        //培训起始时间
                        String recruitDate = rec.getRecruitDate();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cd = Calendar.getInstance();
                        try {
                            cd.setTime(sdf.parse(recruitDate));
                        } catch (ParseException e) {
                            logger.error("", e);
                        }
                        cd.add(Calendar.YEAR, trainYear + 3);//增加n年
                        String format = sdf.format(cd.getTime());
                        String currDate = DateUtil.getCurrDate();

                        //当前时间
                        Date fromDate1 = sdf.parse(currDate);
                        Date toDate1 = sdf.parse(format);
                        Date d1 = new Date(fromDate1.getTime());
                        Date d2 = new Date(toDate1.getTime());
                        int num = d1.compareTo(d2);
                        //条件满足 为重培
                        if (num > 0) {
                            isRetrain = com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
                        } else {
                            isRetrain = com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
                        }
                    }
                } else {
                    isRetrain = com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
                }

            }
        }
        ResDocotrDelayTeturn docotrBackTeturn = new ResDocotrDelayTeturn();
        docotrBackTeturn.setDoctorFlow(doctorFlow);
        docotrBackTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.ReturnTraining.getId());
        List<ResDocotrDelayTeturn> backList = jswjwBiz.searchInfo(docotrBackTeturn, null, null, null);
        model.addAttribute("resRecList", backList);
        model.addAttribute("isDoctorSend", isDoctorSend);
        //如果有退赔记录 则不根据时间判断
        if (CollectionUtils.isNotEmpty(backList)) {
            for (ResDocotrDelayTeturn resDocotrDelayTeturn : backList) {
                if (StringUtil.isNotEmpty(resDocotrDelayTeturn.getAuditStatusName()) && "审核通过".equals(resDocotrDelayTeturn.getAuditStatusName())) {
                    List<com.pinde.core.model.ResDoctorRecruit> collect = recruitList.stream().filter(ResDoctorRecruit -> ResDoctorRecruit.getDoctorStatusId().equals("20")).collect(Collectors.toList());
                    if (CollectionUtils.isEmpty(collect)) {
                        isRetrain = com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y;
                    }
                }
            }
        }
        model.addAttribute("isRetrain", isRetrain);
        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(doctorFlow);
        docotrDelayTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
        List<ResDocotrDelayTeturn> delayList = jswjwBiz.searchInfo(docotrDelayTeturn, null, null, null);
        model.addAttribute("delayList", delayList);
        SysCfg startDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_start");
        SysCfg endDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_end");
        model.addAttribute("startDate",startDate.getCfgValue());
        model.addAttribute("endDate",endDate.getCfgValue());
        return "res/jswjw/doctorMain";
    }

    @RequestMapping(value = "/searchOrgListNew", method = {RequestMethod.GET})
    public String searchOrgListNew(SysOrg sysOrg, String catSpeId,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        List<SysOrg> orgList = null;
        sysOrg.setOrgTypeId("Hospital");
        if ("DoctorTrainingSpe".equals(catSpeId)) {
            List<String> orgLevelList = new ArrayList<String>();
            orgLevelList.add("CountryOrg");
            orgList = jswjwBiz.searchOrgNotJointOrg(sysOrg, orgLevelList);
        } else {
            sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            orgList = jswjwBiz.searchOrg(sysOrg);
        }
        model.addAttribute("orgList",orgList);
        return "res/jswjw/showOrgList";
    }

    /**
     * 加载基地专业
     *
     * @return
     */
    @RequestMapping(value = "/searchResOrgSpeListNew", method = {RequestMethod.GET})
    public String searchResOrgSpeListNew(String userFlow, String sessionNumber, String trainCategoryTypeId, ResOrgSpe resOrgSpe, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        List<ResOrgSpe> speList = null;
        Map<String, List> resultMap = new HashMap<>();
        resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
        String speFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        List<Map<String, String>> docSpeList = jswjwBiz.searchDoctorSpe();
        if (null != docSpeList && docSpeList.size() > 0) {
            for (Map<String, String> spe : docSpeList) {
                String idNo = spe.get("ID_NO");
                String doctorTypeId = spe.get("DOCTOR_TYPE_ID");
                if (idNo.equals(sysUser.getIdNo()) && !"Graduate".equals(doctorTypeId)) {
                    speFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
                    break;
                }
            }
        }

        if (StringUtil.isNotBlank(sessionNumber)) {
            int year = Integer.parseInt(sessionNumber);
            if (year >= 2015) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(speFlag)) {
                    speList = jswjwBiz.searchResOrgSpeListNew(resOrgSpe, trainCategoryTypeId, speFlag);
                } else {
                    speList = jswjwBiz.searchResOrgSpeListNew(resOrgSpe, trainCategoryTypeId, speFlag);
                }
            } else {
                String speTypeId = resOrgSpe.getSpeTypeId();
                if (StringUtil.isNotBlank(speTypeId)) {
                    Map<String, String> speMap = InitConfig.getDictNameMap(speTypeId);
                    speList = new ArrayList<ResOrgSpe>();
                    ResOrgSpe orgSpe = null;
                    for (Map.Entry<String, String> map : speMap.entrySet()) {
                        orgSpe = new ResOrgSpe();
                        orgSpe.setSpeId(map.getValue());
                        orgSpe.setSpeName(map.getKey());
                        speList.add(orgSpe);
                    }
                }
            }

            List<ResJointOrg> jointOrgs = jswjwBiz.searchResJointByOrgFlow(resOrgSpe.getOrgFlow());
            resultMap.put("main", speList);
            if (jointOrgs != null && !jointOrgs.isEmpty()) {
                resultMap.put("joint", jointOrgs);
            }
            model.addAttribute("resultMap",resultMap);
        }
        return "res/jswjw/showOrgAndSpe";
    }

    /**
     * 判断同一个级别是否存在相同的记录
     * @param model
     * @return
     */
    @RequestMapping(value="/validate")
    public String validate(Model model,String doctorFlow,String sessionNumber){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        if (StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        ResDoctor doctor = jswjwBiz.readDoctor(doctorFlow);
        if(null == doctor){
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "请补全基本信息");
            model.addAttribute("isPass", com.pinde.core.common.GlobalConstant.FLAG_N);
            return "res/jswjw/success";
        }
        if(StringUtil.isNotBlank(doctorFlow) && StringUtil.isNotBlank(sessionNumber)){
            com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
            recruit.setSessionNumber(sessionNumber);
            recruit.setDoctorFlow(doctorFlow);
            recruit.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
            List<com.pinde.core.model.ResDoctorRecruit> recruits = jswjwBiz.readDoctorRecruits(recruit);
            if(recruit!=null && (!recruits.isEmpty())){
                model.addAttribute("resultId", "31602");
                model.addAttribute("resultType", "您在当前届别，已添加过培训记录，无法继续添加！");
                model.addAttribute("isPass", com.pinde.core.common.GlobalConstant.FLAG_N);
                return "res/jswjw/success";
            }else{
                model.addAttribute("isPass", com.pinde.core.common.GlobalConstant.FLAG_Y);
                return "res/jswjw/success";
            }
        }
        model.addAttribute("isPass", com.pinde.core.common.GlobalConstant.FLAG_Y);
        return "res/jswjw/success";
    }


    @RequestMapping(value = "/saveResDoctorRecruitNew", method = {RequestMethod.POST})
    public String saveResDoctorRecruitNew(ResDoctorRecruitWithBLOBs docRecWithBLOBs, String prevRecruitFlow, String prevCompleteFileUrl, String prevCompleteCertNo, Model model, String userFlow) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功");
        docRecWithBLOBs.setSignupWay("DoctorSend");//学员报名方式 （DoctorSend：报送，DoctorSignup:报名）
        ResDoctorRecruitWithBLOBs prevDocRec = new ResDoctorRecruitWithBLOBs();//选择的结业阶段
        prevDocRec.setRecruitFlow(prevRecruitFlow);
        prevDocRec.setCompleteFileUrl(prevCompleteFileUrl);
        prevDocRec.setCompleteCertNo(prevCompleteCertNo);
        //住院医师需要协同基地审核后主基地审核，助理全科只需要选择的基地审核
        if ("DoctorTrainingSpe".equals(docRecWithBLOBs.getCatSpeId())) {
            if (StringUtil.isNotBlank(docRecWithBLOBs.getJointOrgFlow())) {
                docRecWithBLOBs.setJointOrgAudit("Auditing");
            } else {
                docRecWithBLOBs.setJointOrgAudit("Passed");
            }
            docRecWithBLOBs.setOrgAudit("Auditing");
        } else {
            //判断是否是协同基地
            List<ResJointOrg> tempJoinOrgs = jswjwBiz.searchResJointByJointOrgFlow(docRecWithBLOBs.getOrgFlow());
            if (!tempJoinOrgs.isEmpty() && tempJoinOrgs.size() > 0) {//是协同基地
                docRecWithBLOBs.setJointOrgAudit("Auditing");
                docRecWithBLOBs.setOrgAudit("Auditing");
                docRecWithBLOBs.setJointOrgFlow(docRecWithBLOBs.getOrgFlow());
                docRecWithBLOBs.setJointOrgName(docRecWithBLOBs.getOrgName());
            } else {
                docRecWithBLOBs.setJointOrgAudit("Passed");
                docRecWithBLOBs.setOrgAudit("Auditing");
                docRecWithBLOBs.setJointOrgFlow(docRecWithBLOBs.getOrgFlow());
                docRecWithBLOBs.setJointOrgName(docRecWithBLOBs.getOrgName());
            }
        }

        if (!com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(docRecWithBLOBs.getCatSpeId())) {
            docRecWithBLOBs.setCompleteFileUrl("");
            docRecWithBLOBs.setCompleteCertNo("");
            if ("21".equals(docRecWithBLOBs.getDoctorStatusId())) {
                //由于页面字段展示问题现将非二阶段的该条记录的结业附件
                // name = prevCompleteFileUrl 存入special_file_url（该条记录的结业附件）中
                docRecWithBLOBs.setSpecialFileUrl(prevCompleteFileUrl);
                docRecWithBLOBs.setSpecialCertNo(prevCompleteCertNo);
            } else {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        } else {
            docRecWithBLOBs.setCompleteFileUrl(prevCompleteFileUrl);
            docRecWithBLOBs.setCompleteCertNo(prevCompleteCertNo);
            if (!"21".equals(docRecWithBLOBs.getDoctorStatusId())) {
                docRecWithBLOBs.setSpecialFileUrl("");
                docRecWithBLOBs.setSpecialCertNo("");
            }
        }
        //查询专业轮转方案
        SchRotation rotation = new SchRotation();
        rotation.setDoctorCategoryId(RecDocCategoryEnum.Doctor.getId()); // 此处不能直接使用住院医师类型，助理全科无法查到培训方案
        rotation.setSpeId(docRecWithBLOBs.getSpeId());
        String catSpeId = docRecWithBLOBs.getCatSpeId();
        if (com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId().equals(catSpeId) || RecDocCategoryEnum.WMFirst.getId().equals(catSpeId)) {
            rotation.setDoctorCategoryId(catSpeId);
        }
        rotation.setPublishFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
        List<SchRotation> rotationList = jswjwBiz.searchOrgStandardRotation(rotation);
        if (null != rotationList && rotationList.size() > 0) {
            docRecWithBLOBs.setRotationFlow(rotationList.get(0).getRotationFlow());
            docRecWithBLOBs.setRotationName(rotationList.get(0).getRotationName());
        }
        ResDoctor doctor = jswjwBiz.readDoctor(docRecWithBLOBs.getDoctorFlow());
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        //查询是否重培（有退培记录且审核通过为重培） 默认否
        docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        ResDocotrDelayTeturn docotrDelayTeturn = new ResDocotrDelayTeturn();
        docotrDelayTeturn.setDoctorFlow(docRecWithBLOBs.getDoctorFlow());
        docotrDelayTeturn.setTypeId("ReturnTraining");
        List<String> flags = new ArrayList<>();
        flags.add("1");
        List<ResDocotrDelayTeturn> resRecList = jswjwBiz.searchInfo(docotrDelayTeturn, null, flags, null);
        if (resRecList.size() > 0) {
            docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        } else {
            //非结业记录 判断入培时间 + 培训年限 + 3年  如果没结业 则为重培
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchRecruitList(docRecWithBLOBs.getDoctorFlow());
            if (CollectionUtils.isNotEmpty(recruitList)) {
                for (ResDoctorRecruit resDoctorRecruit : recruitList) {
                    //20 在培 21结业
                    int trainYear = 0;
                    if (StringUtil.isNotEmpty(resDoctorRecruit.getDoctorStatusId()) && !"21".equals(resDoctorRecruit.getDoctorStatusId())) {
                        //培训年限
                        if (StringUtil.isNotEmpty(resDoctorRecruit.getTrainYear())) {
//                    'OneYear' then '一年' 'TwoYear' then '两年' 'ThreeYear' then '三年'
                            if ("OneYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 1;
                            } else if ("TwoYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 2;
                            } else if ("ThreeYear".equals(resDoctorRecruit.getTrainYear())) {
                                trainYear = 3;
                            }
                        }
                        if (StringUtil.isNotEmpty(resDoctorRecruit.getRecruitDate())) {
                            //培训起始时间
                            String recruitDate = resDoctorRecruit.getRecruitDate();
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cd = Calendar.getInstance();
                            try {
                                cd.setTime(sdf.parse(recruitDate));
                            } catch (ParseException e) {
                                logger.error("", e);
                            }
                            cd.add(Calendar.YEAR, trainYear + 3);//增加n年
                            String format = sdf.format(cd.getTime());
                            String currDate = DateUtil.getCurrDate();

                            //当前时间
                            Date fromDate1 = sdf.parse(currDate);
                            Date toDate1 = sdf.parse(format);
                            Date d1 = new Date(fromDate1.getTime());
                            Date d2 = new Date(toDate1.getTime());
                            int num = d1.compareTo(d2);
                            //条件满足 为重培
                            if (num > 0) {
                                docRecWithBLOBs.setIsRetrain(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                            }
                        }
                    }
                }
            }
        }
        int result = jswjwBiz.editResDoctorRecruit(docRecWithBLOBs, prevDocRec);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return "res/jswjw/success";
        }
        model.addAttribute("resultId", "31603");
        model.addAttribute("resultType", "保存失败");
        return "res/jswjw/success";
    }


    @RequestMapping("/getDoctorRecruit")
    public String getDoctorRecruit(String recruitFlow, String doctorFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        if (StringUtil.isEmpty(doctorFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(recruitFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "recruitFlow为空");
            return "res/jswjw/success";
        }
        boolean isLatest = false;//是否最新记录
        JsresRecruitDocInfo jsresRecruit = jswjwBiz.readRecruit(recruitFlow);
        model.addAttribute("jsresRecruitDocInfo", jsresRecruit);
        ResDocotrDelayTeturn ret = jswjwBiz.findTeturnInfo(recruitFlow);
        model.addAttribute("ret", ret);
        if (ret != null) {
            model.addAttribute("haveReturn", com.pinde.core.common.GlobalConstant.FLAG_Y);
        }
        String applyYear = "";
        if (StringUtil.isNotBlank(recruitFlow)) {
            ResDoctorRecruit doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
            SysUser sysUser = jswjwBiz.readSysUser(doctorFlow);
            model.addAttribute("user", sysUser);
            if (doctorRecruit != null) {
                applyYear = doctorRecruit.getGraduationYear();
                model.addAttribute("doctorRecruit", doctorRecruit);
                if (StringUtil.isNotBlank(doctorRecruit.getDoctorFlow()) && !StringUtil.isNotBlank(doctorRecruit.getProveFileUrl())) {
                    ResDoctor doctor = jswjwBiz.readDoctor(doctorRecruit.getDoctorFlow());
                    if (doctor != null) {
                        model.addAttribute("doctor", doctor);
                        String degreeType = doctor.getDegreeCategoryId();
                        if (com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicMaster.getId().equals(degreeType) || com.pinde.core.common.enums.JsResDegreeCategoryEnum.ClinicDoctor.getId().equals(degreeType)) {
                            PubUserResume resume = jswjwBiz.readPubUserResume(doctorRecruit.getDoctorFlow());
                            String content = resume.getUserResume();
                            if (StringUtil.isNotBlank(content)) {
                                UserResumeExtInfoForm userResumeExt = JaxbUtil.converyToJavaBean(content, UserResumeExtInfoForm.class);
                                model.addAttribute("userResumeExt", userResumeExt);
                                if (userResumeExt != null) {
                                    doctorRecruit.setProveFileUrl(userResumeExt.getDegreeUri());
                                }
                            }
                        }
                    }
                }
            }

            ResDoctorRecruit lastRecruit = new ResDoctorRecruit();
            lastRecruit.setDoctorFlow(doctorFlow);
            lastRecruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(lastRecruit, "CREATE_TIME DESC");
            if (recruitList != null && !recruitList.isEmpty()) {
                lastRecruit = recruitList.get(0);
                if (lastRecruit.getRecruitFlow().equals(recruitFlow)) {
                    isLatest = true;
                }
            }
            List<DoctorAuth> doctorAuths = jswjwBiz.searchAuthsByOperUserFlow(doctorFlow);
            if (doctorAuths != null & !doctorAuths.isEmpty()) {
                model.addAttribute("resRec", doctorAuths.get(0));
            }
            //最新记录 && 审核通过
            if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId())) {
                ResDoctorOrgHistory docOrgHistory = new ResDoctorOrgHistory();
                docOrgHistory.setDoctorFlow(doctorFlow);
                docOrgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.BaseChange.getId());
                docOrgHistory.setRecruitFlow(recruitFlow);
                //List<String> changeStatusIdList = new ArrayList<String>();
                //changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.OutApplyWaiting.getId());//待转出审核
                //changeStatusIdList.add(com.pinde.core.common.enums.JsResChangeApplyStatusEnum.InApplyWaiting.getId());//待转入审核
                List<ResDoctorOrgHistory> docOrgHistoryList = jswjwBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
                if (docOrgHistoryList != null && !docOrgHistoryList.isEmpty()) {
                    model.addAttribute("docOrgHistoryList", docOrgHistoryList);
                    model.addAttribute("latestDocOrgHistory", docOrgHistoryList.get(0));//最新变更记录
                }
                docOrgHistory.setChangeTypeId(com.pinde.core.common.enums.JsResChangeTypeEnum.SpeChange.getId());
                List<ResDoctorOrgHistory> changeSpeList = jswjwBiz.searchWaitingChangeOrgHistoryList(docOrgHistory, null);
                if (changeSpeList != null && !changeSpeList.isEmpty()) {
                    model.addAttribute("changeSpeList", changeSpeList);
                    model.addAttribute("lastChangeSpe", changeSpeList.get(0));
                }
            }
        }
        //判断是否申请结业考核资格
        Boolean applyFlag = false;
        JsresGraduationApply jsresGraduationApply = jswjwBiz.searchByRecruitFlow(recruitFlow, applyYear);
        if (jsresGraduationApply == null) {
            applyFlag = true;
        }
        if (jsresGraduationApply != null) {
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.LocalNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())
                    || com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())
                    || com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())
                    || com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(jsresGraduationApply.getAuditStatusId())) {
                applyFlag = true;
            }
        }
        model.addAttribute("isLatest", isLatest);
        model.addAttribute("applyFlag", applyFlag);
        model.addAttribute("sessionNumber", cfgMapper.selectByPrimaryKey("jsres_doctorCount_sessionNumber").getCfgValue());
        SimpleDateFormat date = new SimpleDateFormat("yyyy");
        String year = date.format(new Date());
        model.addAttribute("nowYear", year);
        SysCfg sysCfg = cfgMapper.selectByPrimaryKey("jsres_is_train");
        SysCfg sysCfg1 = cfgMapper.selectByPrimaryKey("upload_base_url");
        model.addAttribute("jsres_is_train",sysCfg.getCfgValue());
        model.addAttribute("upload_base_url",sysCfg1.getCfgValue());
        return "res/jswjw/trainInfo";

    }

    /**
     * 提交培训信息
     *
     * @param recruitWithBLOBs
     * @param model
     * @return
     */
    @RequestMapping(value = "/submitResDoctorRecruit", method = {RequestMethod.POST})
    public String submitResDoctorRecruit(ResDoctorRecruitWithBLOBs recruitWithBLOBs, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        String auditStatusId = recruitWithBLOBs.getAuditStatusId();
        if (StringUtil.isNotBlank(auditStatusId)) {
            recruitWithBLOBs.setAuditStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.getNameById(auditStatusId));
        }
        recruitWithBLOBs.setRecruitFlag(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        recruitWithBLOBs.setConfirmFlag(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        int result = jswjwBiz.saveDoctorRecruit(recruitWithBLOBs);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return "res/jswjw/success";
        }
        model.addAttribute("resultId", "31601");
        model.addAttribute("resultType", "提交失败！");
        return "res/jswjw/success";
    }
    @RequestMapping(value="/delDoctorRecruit")
    public String delDoctorRecruit(String recruitFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "删除成功！");
        ResDoctorRecruitWithBLOBs recruit= (ResDoctorRecruitWithBLOBs) jswjwBiz.readResDoctorRecruit(recruitFlow);
        if(recruit!=null){
            recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
            int result=jswjwBiz.saveDoctorRecruit(recruit);
            if (result == com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return  "res/jswjw/success";
            }
        }
        model.addAttribute("resultId", "30120");
        model.addAttribute("resultType", "删除失败！");
        return  "res/jswjw/success";
    }

    @RequestMapping(value = "/saveGraduationInfo", method = {RequestMethod.POST})
    public String saveGraduationInfo(String recruitFlow, String specialFileUrl, String specialCertNo,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if (StringUtil.isEmpty(recruitFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "flow为空");
            return "res/jswjw/success";
        }
        ResDoctorRecruitWithBLOBs recruitBlob = null;
        if (StringUtil.isNotBlank(recruitFlow)) {
            recruitBlob = jswjwBiz.readRecruitNew(recruitFlow);
            if (recruitBlob != null) {
                recruitBlob = new ResDoctorRecruitWithBLOBs();
                recruitBlob.setRecruitFlow(recruitFlow);
                recruitBlob.setSpecialCertNo(specialCertNo);
                recruitBlob.setSpecialFileUrl(specialFileUrl);
            } else {
                model.addAttribute("resultId", "30120");
                model.addAttribute("resultType", "保存失败！");
                return  "res/jswjw/success";
            }
        } else {
            model.addAttribute("resultId", "30120");
            model.addAttribute("resultType", "保存失败！");
            return  "res/jswjw/success";
        }
        int result = jswjwBiz.saveDoctorRecruit(recruitBlob);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return  "res/jswjw/success";
        }
        model.addAttribute("resultId", "30120");
        model.addAttribute("resultType", "保存失败！");
        return  "res/jswjw/success";
    }

    //报送列表
    @RequestMapping(value="/doctorSendList")
    public String doctorSendList( String userFlow , Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
        if(null == sysUser){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/success";
        }
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(userFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jswjwBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
        List<com.pinde.core.model.ResDoctorRecruit> doctorSend = new ArrayList<>();
        //筛选报送记录
        if(CollectionUtils.isNotEmpty(recruitList)){
            doctorSend = recruitList.stream().filter(rd -> StringUtil.isNotEmpty(rd.getSignupWay()) && rd.getSignupWay().equals("DoctorSend")).collect(Collectors.toList());
        }
        model.addAttribute("recruitList",doctorSend);
        model.addAttribute("dataCount",recruitList.size());
        model.addAttribute("userName",sysUser.getUserName());
        return "/res/jswjw/doctorRegister";
    }

    @RequestMapping("/editDoctorRecruitNew2")
    public String editDoctorRecruitNew2(String recruitFlow,String userFlow, String doctorStatus, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功！");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/success";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if(null == currUser){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/success";
        }
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit doctorRecruit = null;
        String addFlag = "";
        if (StringUtil.isNotBlank(recruitFlow)) {
            addFlag = "edit";
            doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
            model.addAttribute("catSpeId", doctorRecruit.getCatSpeId());
            model.addAttribute("catSpeName", doctorRecruit.getCatSpeName());
            model.addAttribute("doctorRecruit", doctorRecruit);
            model.addAttribute("addRecord", com.pinde.core.common.GlobalConstant.FLAG_Y);//添加新的培训记录标识
        }
        //已审核通过
        ResDoctorRecruit passedRec = new ResDoctorRecruit();
        passedRec.setDoctorFlow(doctorFlow);
        passedRec.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        passedRec.setAuditStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId());
        List<com.pinde.core.model.ResDoctorRecruit> passedRecruitList = jswjwBiz.searchResDoctorRecruitList(passedRec, "MODIFY_TIME DESC");
        //其中一阶段、住院医师审核通过（选二阶段使用）
        List<com.pinde.core.model.ResDoctorRecruit> prevPassedList = new ArrayList<com.pinde.core.model.ResDoctorRecruit>();
        if (passedRecruitList != null && !passedRecruitList.isEmpty()) {
            model.addAttribute("passedRecruitList", passedRecruitList);
            //记录审核通过的培训类别(不包含首条为二阶段自动生成的一阶段)
            List<String> catSpeIdList = new ArrayList<String>();
            for (ResDoctorRecruit rec : passedRecruitList) {
                String catSpeId = rec.getCatSpeId();
                if (!catSpeIdList.contains(catSpeId) && StringUtil.isNotBlank(rec.getSpeId())) {
                    catSpeIdList.add(catSpeId);
                }
                if (StringUtil.isBlank(rec.getSpeId())) {//首条为二阶段
                    model.addAttribute("firstRecIsWMSecond", true);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMFirst.getId().equals(rec.getCatSpeId()) || com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId().equals(rec.getCatSpeId())) {
                    prevPassedList.add(rec);
                }
                if (com.pinde.core.common.enums.TrainCategoryEnum.WMSecond.getId().equals(rec.getCatSpeId())) {//二阶段审核通过
                    model.addAttribute("isWMSecondRecPassed", true);
                }
            }
            model.addAttribute("catSpeIdList", catSpeIdList);
        }
        if (prevPassedList != null && !prevPassedList.isEmpty()) {
            model.addAttribute("prevPassedList", prevPassedList);
            if (StringUtil.isNotBlank(recruitFlow)) {//回显上一阶段  结业证书
                model.addAttribute("latestPrevPassed", prevPassedList.get(0));
            }
        }
        SysCfg startDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_start");
        SysCfg endDate = cfgMapper.selectByPrimaryKey("jsres_is_apply_end");
        model.addAttribute("startDate",startDate.getCfgValue());
        model.addAttribute("endDate",endDate.getCfgValue());
        //获取访问路径前缀
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        model.addAttribute("upload_base_url", uploadBaseUrl);
        return "/res/jswjw/editReportInfoNew";
    }

    @RequestMapping("/getReductionInfo")
    public String getReductionInfo(String recruitFlow, String doctorFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功！");
        if (StringUtil.isNotBlank(recruitFlow)) {
            ResDoctorRecruit doctorRecruit = jswjwBiz.readResDoctorRecruit(recruitFlow);
            if (doctorRecruit != null) {
                model.addAttribute("doctorRecruit", doctorRecruit);
                if (StringUtil.isNotBlank(doctorRecruit.getDoctorFlow())) {
                    ResDoctor doctor = jswjwBiz.readDoctor(doctorRecruit.getDoctorFlow());
                    if (doctor != null) {
                        model.addAttribute("doctor", doctor);
                    }
                }
            }
            ResDoctorReduction reduction = jswjwBiz.findReductionByRecruitFlow(recruitFlow);
            if (reduction != null) {
                List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", reduction.getRecordFlow());
                model.addAttribute("reductionFiles", reductionFiles);
            }
            //如果审核状态是空则可以编辑或者审核不通过
            if (reduction == null || StringUtil.isBlank(reduction.getAuditStatusId())
                    || JszyBaseStatusEnum.LocalUnPassed.getId().equals(reduction.getAuditStatusId())
                    || JszyBaseStatusEnum.GlobalUnPassed.getId().equals(reduction.getAuditStatusId())) {
                model.addAttribute("canEdit", com.pinde.core.common.GlobalConstant.FLAG_Y);
            } else {
                model.addAttribute("canEdit", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
            //1.助理全科2.已经减免的不可以申请减免3.只有审核通过的记录才可以减免
            //1.招录信息审核通过展示减免
            if (com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId())) {
                //2.助理全科不展示减免
                /*if (com.pinde.core.common.enums.JsResTrainYearEnum.TCMAssiGeneral.getId().equals(doctorRecruit.getCatSpeId())) {
                    model.addAttribute("showReduction", com.pinde.core.common.GlobalConstant.FLAG_N);
                } else {
                    model.addAttribute("showReduction", com.pinde.core.common.GlobalConstant.FLAG_Y);
                }*/
                model.addAttribute("showReduction", com.pinde.core.common.GlobalConstant.FLAG_Y);
            } else {
                model.addAttribute("showReduction", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
            model.addAttribute("reduction", reduction);
        }
        return "/res/jswjw/reductionInfo";
    }

    @RequestMapping(value = "/saveOutLock", method = {RequestMethod.POST})
    public String saveOutLock(String userFlow, String outDate,Model model) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "申请成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "userFlow为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(outDate)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "outDate为空");
            return "res/jswjw/success";
        }
        int i = jswjwBiz.saveOutLock(outDate, userFlow);
        if(i>0){
            return  "res/jswjw/success";
        }
        model.addAttribute("resultId", "31601");
        model.addAttribute("resultType", "保存失败！");
        return  "res/jswjw/success";
    }

    public  String dayAddAndSub(String outTime,int day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sdf.parse(outTime));
        calendar.add(Calendar.DATE, day);
        Date startDate = calendar.getTime();

        return sdf.format(startDate);

    }

    @RequestMapping(value = {"/checkOutTime"}, method = {RequestMethod.GET})
    public String checkOutTime(String userFlow, String processFlow, Model model) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "校验成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "userFlow为空");
            return "res/jswjw/success";
        }
        if (StringUtil.isEmpty(processFlow)) {
            model.addAttribute("resultId", "31601");
            model.addAttribute("resultType", "processFlow为空");
            return "res/jswjw/success";
        }
        //超时
        model.addAttribute("isOutTime", com.pinde.core.common.GlobalConstant.FLAG_N);
        ResDoctor doctor = jswjwBiz.readDoctor(userFlow);
        String orgFlow = doctor.getOrgFlow();
        String isChargeOrg = jswjwBiz.getJsResCfgAppMenu(doctor.getDoctorFlow());
        if(StringUtil.isNotEmpty(processFlow)){
            ResDoctorSchProcess resDoctorSchProcess = jswjwBiz.readSchProcessByResultFlow(processFlow);
            if(null != resDoctorSchProcess && (StringUtil.isNotEmpty(resDoctorSchProcess.getOutDate()) || StringUtil.isNotEmpty(resDoctorSchProcess.getSchEndDate()))){
                //申请解锁记录
                List<ResOutOfficeLock> resOutOfficeLockList = jswjwBiz.readResOutOfficeLock(userFlow,"");
                if(CollectionUtils.isNotEmpty(resOutOfficeLockList)){
                    model.addAttribute("resOutOfficeLockList",resOutOfficeLockList.get(0));
                }else{
                    resOutOfficeLockList = new ArrayList<>();
                }
                //基地限制收费用户时间
                String outDay = jswjwBiz.getJsResCfgCode("out_day_check_" + orgFlow);
                //后台收费用户限制时间
                String outDayOne = jswjwBiz.getCfgCode("res_out_day_one_cfg");
                //后台免费用户限制时间
                String outDayTwo = jswjwBiz.getCfgCode("res_out_day_two_cfg");
                if (StringUtil.isNotEmpty(isChargeOrg)) {
                    if(StringUtil.isNotEmpty(outDay) && !"-1".equals(outDay)){
                        //出科时间 + outDay > 当前时间 提示超出outDay时间不可填写数据
                        if(StringUtil.isEmpty(resDoctorSchProcess.getOutDate())){
                            return "res/jswjw/checkProgress";
                        }
                        String outDate = resDoctorSchProcess.getOutDate();
                        String outDateTwo = "";
                        int day = Integer.parseInt(outDay);
                        //审核通过 审核时间+5天
                        List<ResOutOfficeLock> passList = resOutOfficeLockList.stream().filter(resOutOfficeLock -> "Passed".equals(resOutOfficeLock.getAuditStatusId())).sorted(Comparator.comparing(ResOutOfficeLock::getCreateTime))
                                .collect(Collectors.toList());
                        if(CollectionUtils.isNotEmpty(passList)){
                            ResOutOfficeLock resOutOfficeLock = passList.get(0);
                            String modifyTime = resOutOfficeLock.getModifyTime();
                            outDateTwo = dayAddAndSub(modifyTime,5);
                        }else {
                            outDateTwo = dayAddAndSub(outDate,day);
                        }
                        String currDate = DateUtil.getCurrDate();
                        int days = differentDays(outDate, currDate);
                        int num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), outDateTwo);
                        if(num > 0){
                            //超时
                            model.addAttribute("isOutTime", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            //出科时间
                            model.addAttribute("outDate",outDate);
                            //超出出科时间天数
                            model.addAttribute("checkDay",days);
                        }
                    }else{
                        if(StringUtil.isNotEmpty(outDayOne) && !"-1".equals(outDayOne)){
                            //出科时间 + outDayOne > 当前时间 提示超出outDayOne时间不可填写数据
                            if(StringUtil.isEmpty(resDoctorSchProcess.getOutDate())){
                                return "res/jswjw/checkProgress";
                            }
                            String outDate = resDoctorSchProcess.getOutDate();
                            String outDateTwo = "";
                            int day = Integer.parseInt(outDayOne);
                            //审核通过 审核时间+5天
                            List<ResOutOfficeLock> passList = resOutOfficeLockList.stream().filter(resOutOfficeLock -> "Passed".equals(resOutOfficeLock.getAuditStatusId())).sorted(Comparator.comparing(ResOutOfficeLock::getCreateTime))
                                    .collect(Collectors.toList());
                            if(CollectionUtils.isNotEmpty(passList)){
                                ResOutOfficeLock resOutOfficeLock = passList.get(0);
                                String modifyTime = resOutOfficeLock.getModifyTime();
                                outDateTwo = dayAddAndSub(modifyTime,5);
                            }else {
                                outDateTwo = dayAddAndSub(outDate,day);
                            }
                            String currDate = DateUtil.getCurrDate();
                            int days = differentDays(outDate, currDate);
                            int num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), outDateTwo);
                            if(num > 0){
                                //超时
                                model.addAttribute("isOutTime", com.pinde.core.common.GlobalConstant.FLAG_Y);
                                //出科时间
                                model.addAttribute("outDate",outDate);
                                //超出出科时间天数
                                model.addAttribute("checkDay",days);
                            }
                        }
                    }

                }else{
                    //免费用户
                    if(StringUtil.isNotEmpty(outDayTwo) && !"-1".equals(outDayTwo)){
                        //出科时间 + outDayTwo > 当前时间 提示超出outDayTwo时间不可填写数据
//                        String outDate = resDoctorSchProcess.getOutDate();
                        String outDate = resDoctorSchProcess.getSchEndDate();
                        String outDateTwo = "";
                        int day = Integer.parseInt(outDayTwo);
                        //审核通过 审核时间+5天
                        List<ResOutOfficeLock> passList = resOutOfficeLockList.stream().filter(resOutOfficeLock -> "Passed".equals(resOutOfficeLock.getAuditStatusId())).sorted(Comparator.comparing(ResOutOfficeLock::getCreateTime))
                                .collect(Collectors.toList());
                        if(CollectionUtils.isNotEmpty(passList)){
                            ResOutOfficeLock resOutOfficeLock = passList.get(0);
                            String modifyTime = resOutOfficeLock.getModifyTime();
                            outDateTwo = dayAddAndSub(modifyTime,5);
                        }else {
                            outDateTwo = dayAddAndSub(outDate,day);
                        }
                        String currDate = DateUtil.getCurrDate();
                        int days = differentDays(outDate, currDate);
                        int num = DateTimeUtil.contrastDate(DateUtil.getCurrDate(), outDateTwo);
                        if(num > 0){
                            //超时
                            model.addAttribute("isOutTime", com.pinde.core.common.GlobalConstant.FLAG_Y);
                            //出科时间
                            model.addAttribute("outDate",outDate);
                            //超出出科时间天数
                            model.addAttribute("checkDay",days);
                        }
                    }
                }
            }
        }
        return "res/jswjw/checkProgress";
    }

    public int differentDays(String date1,String date2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date3 = sdf.parse(date1);
        Date date4 = sdf.parse(date2);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date3);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date4);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {//同一年
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else {// 不同年
            return day2 - day1;
        }
    }

//    /**
//     * 入科教育管理
//     */
//    @RequestMapping(value = "/admissionEducationManage")
//    public String admissionEducationManage(Model model, String userFlow, String deptFlow) {
//        model.addAttribute("resultId", "200");
//        model.addAttribute("resultType", "交易成功！");
//        if (StringUtil.isEmpty(userFlow)) {
//            model.addAttribute("resultId", "31601");
//            model.addAttribute("resultType", "userFlow为空");
//            return "res/jswjw/success";
//        }
//        if (StringUtil.isEmpty(deptFlow)) {
//            model.addAttribute("resultId", "31601");
//            model.addAttribute("resultType", "deptFlow为空");
//            return "res/jswjw/success";
//        }
//        SysUser sysUser = jswjwBiz.readSysUser(userFlow);
//        String orgFlow = sysUser.getOrgFlow();
//        SysDept dept = jswjwBiz.readSysDept(deptFlow);
//        model.addAttribute("dept", dept);
//        ResInprocessInfo info = resInprocessInfoBiz.readByDeptFlowAndOrgFlow(deptFlow, orgFlow);
//        model.addAttribute("info", info);
//        if (info != null) {
//            List<ResInprocessInfoMember> members = resInprocessInfoBiz.readMembersByRecordFlow(info.getRecordFlow());
//            model.addAttribute("members", members);
//            List<PubFile> files = pubFileBiz.searchByProductFlow(info.getRecordFlow());
//            model.addAttribute("files", files);
//            String arrange = info.getTeachingArrangement();
//            if (StringUtil.isNotBlank(arrange)) {
//                Map<String, Object> arrangeMap = new HashMap<>();
//                arrangeMap = paressXml(arrange);
//                model.addAttribute("arrangeMap", arrangeMap);
//            }
//        }
//        return "res/jswjw/admissionEduInfo";
//    }


    private void getForm(Model model, ResAssessCfg assessCfg) throws DocumentException {
        model.addAttribute("assessCfg", assessCfg);
        Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
        String titleXpath = "//title";
        List<Element> titleElementList = dom.selectNodes(titleXpath);
        if (titleElementList != null && !titleElementList.isEmpty()) {
            List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
            for (Element te : titleElementList) {
                ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                titleForm.setType(te.attributeValue("type"));
                titleForm.setRow(te.attributeValue("row"));
                titleForm.setId(te.attributeValue("id"));
                titleForm.setName(te.attributeValue("name"));
                List<Element> itemElementList = te.elements("item");
                List<ResAssessCfgItemForm> itemFormList = null;
                if (itemElementList != null && !itemElementList.isEmpty()) {
                    itemFormList = new ArrayList<ResAssessCfgItemForm>();
                    for (Element ie : itemElementList) {
                        ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                        itemForm.setId(ie.attributeValue("id"));
                        itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                        itemForm.setType(ie.element("type") == null ? "" : ie.element("type").getTextTrim());
                        itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
                        itemForm.setRow(ie.element("row") == null ? "" : ie.element("row").getTextTrim());
                        itemFormList.add(itemForm);
                    }
                }
                titleForm.setItemList(itemFormList);
                titleFormList.add(titleForm);
            }
            if(CollectionUtils.isNotEmpty(titleFormList)){
                for (ResAssessCfgTitleForm resAssessCfgTitleForm : titleFormList) {
                    List<ResAssessCfgItemForm> itemList = resAssessCfgTitleForm.getItemList();
                    if(CollectionUtils.isNotEmpty(itemList)){
                        model.addAttribute("itemList", itemList);
                    }
                }
            }
            model.addAttribute("titleFormList", titleFormList);

        }
    }

    @RequestMapping(value = {"/temporaryOutFamily"}, method = {RequestMethod.GET})
    public String temporaryOutFamily(String processFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (StringUtil.isEmpty(processFlow)) {
            model.addAttribute("resultId", "31702");
            model.addAttribute("resultType", "请检查是否已维护好科主任带教");
            return "res/jswjw/viewImage";
        }
        ResDoctorSchProcess resDoctorSchProcess = jswjwBiz.readSchProcess(processFlow);
        if(StringUtil.isEmpty(resDoctorSchProcess.getHeadUserFlow()) || StringUtil.isEmpty(resDoctorSchProcess.getTeacherUserFlow())){
            model.addAttribute("resultId", "31702");
            model.addAttribute("resultType", "请检查是否已维护好科主任带教");
            return "res/jswjw/viewImage";
        }
        int i = jswjwBiz.temporaryOutFamily(processFlow);
        if(i>0){
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "提交成功！");
        }else{
            model.addAttribute("resultId", "200");
            model.addAttribute("resultType", "提交失败！");
        }
        return "res/jswjw/deleteImage";
    }
}

