package com.pinde.res.ctrl.xnres;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.res.biz.common.IResPowerCfgBiz;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.stdp.*;
import com.pinde.res.biz.xnres.*;
import com.pinde.res.enums.xnres.*;
import com.pinde.res.enums.stdp.ResScoreTypeEnum;
import com.pinde.res.enums.stdp.ResultEnum;
import com.pinde.res.enums.stdp.UserSexEnum;
import com.pinde.res.model.xnres.mo.AnnualAssessmentExt;
import com.pinde.res.model.xnres.mo.ResGraduationAssessmentExt;
import com.pinde.res.model.xnres.mo.UploadFileForm;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.PasswordHelper;
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
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/res/xnres/student")
public class XnresStudentController {
    private static Logger logger = LoggerFactory.getLogger(XnresStudentController.class);

    @Autowired
    private IXnresStudentBiz studentBiz;
    @Autowired
    private IXnresAppBiz appBiz;
    @Autowired
    private IResOrgTimeBiz timeBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private ISchAndStandardDeptCfgBiz deptCfgBiz;
    @Autowired
    private ResPaperBiz paperBiz;
    @Autowired
    private IFileBiz fileBiz;

    @Autowired
    private IXnresResLiveTrainingBiz resLiveTrainingBiz;
    @Autowired
    private IResPowerCfgBiz resPowerCfgBiz;
    @Autowired
    private INoticeBiz noticeBiz;

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/xnres/500";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/xnres/student/test";
    }

    @RequestMapping(value = {"/remember"}, method = {RequestMethod.GET})
    public String remember(String userFlow, String deptFlow, String cataFlow, String dataFlow, String funcTypeId, String funcFlow, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userFlow", userFlow);
        session.setAttribute("deptFlow", deptFlow);
        session.setAttribute("cataFlow", cataFlow);
        session.setAttribute("dataFlow", dataFlow);
        session.setAttribute("funcTypeId", funcTypeId);
        session.setAttribute("funcFlow", funcFlow);
        return "/res/xnres/student/test";
    }

    @RequestMapping(value = {"/deptList"}, method = {RequestMethod.GET})
    public String deptList(String userFlow, String searchData, Integer pageIndex, Integer pageSize, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3020101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/student/deptList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "3020102");
            model.addAttribute("resultType", "起始页为空");
            return "res/xnres/student/deptList";
        }

        if (pageSize == null) {
            model.addAttribute("resultId", "3020103");
            model.addAttribute("resultType", "页面大小为空");
            return "res/xnres/student/deptList";
        }

        //查询条件
        //Entering(已入科) , NotEntered(未入科) , Exited(已出科)
        String statusId = "";
        String deptName = "";
        if (StringUtil.isNotBlank(searchData)) {
            try {
                //为json字符串转码
                searchData = new String(searchData.getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //转换json字符串为map对象
            Map<String, String> searchMap = (Map<String, String>) JSON.parse(searchData);
            statusId = searchMap.get("statusId");
            deptName = searchMap.get("deptName");
        }

//		if(StringUtil.isBlank(statusId)){
//			statusId = DeptStatusEnum.Entering.getId();
//		}

        //组织查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("doctorFlow", userFlow);
        paramMap.put("statusId", statusId);
        paramMap.put("deptName", deptName);
        ResDoctor doctor = appBiz.readResDoctor(userFlow);
        paramMap.put("rotationFlow", doctor.getRotationFlow());
        paramMap.put("secondRotationFlow", doctor.getSecondRotationFlow());
        if("Action2".equals(appBiz.getCfgByCode("res_sch_action_type")))
        {
            paramMap.put("isAction2","Y");
        }else{
            paramMap.put("isAction2","N");
        }

        //按条件查询轮转数据
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> results = studentBiz.searchResult(paramMap);

        if (results != null && !results.isEmpty()) {
            //轮转计划单位
            String unit = appBiz.getCfgByCode("res_rotation_unit");

            //默认按月计算
            int step = 30;
            if (SchUnitEnum.Week.getId().equals(unit)) {
                //如果是周按7天算/没配置或者选择月按30天
                step = 7;
            }

            //循环计算实际轮转月份
            for (Map<String, Object> map : results) {
                //获取实际的开始时间与结束时间
                String startDate = (String) map.get("schStartDate");
                String endDate = (String) map.get("schEndDate");
                //轮转中，结束时间为当前时间
                String rotationStatus = (String) map.get("rotationStatus");
//				if(RotationStatusEnum.Rounding.getId().equals(rotationStatus)) {
//					endDate = DateUtil.getCurrDate();
//					map.put("endDate",endDate);
//				}
                BigDecimal realMonth = BigDecimal.valueOf(0);
                if (StringUtil.isNotBlank(startDate)) {
                    //如果还未出科则没有实际出科时间便取当前时间
//					if(!StringUtil.isNotBlank(endDate)){
//						endDate = DateUtil.getCurrDate();
//						map.put("endDate",endDate);
//					}
                    //计算实际入科时间和实际结束时间之间的天数
                    long realDays = DateUtil.signDaysBetweenTowDate(endDate, startDate)+1;
                    if (realDays != 0) {
                        //计算实际轮转的月/周数
                        double realMonthF = (realDays / (step * 1.0));
                        realMonth = BigDecimal.valueOf(realMonthF);
                        realMonth = realMonth.setScale(1, BigDecimal.ROUND_HALF_UP);
                    }
                }
                map.put("realMonth", realMonth);

                String standardDeptName = (String) map.get("standardDeptName");
                if (StringUtil.isNotBlank(standardDeptName)) {
                    String[] standardDeptNames = standardDeptName.split("\\.");
                    int len = standardDeptNames.length;
                    String subDeptName = standardDeptNames[len - 1];
                    map.put("standardDeptName", subDeptName);
                }

                String isCurrentFlag = (String) map.get("isCurrentFlag");
                String schFlag = (String) map.get("schFlag");
                if (!GlobalConstant.FLAG_Y.equals(isCurrentFlag) && !GlobalConstant.FLAG_Y.equals(schFlag)) {
                    //String rotationStatus = (String)map.get("rotationStatus");
                    String currDate = DateUtil.getCurrDate();
                    String schStartDate = (String) map.get("schStartDate");
//					if(StringUtil.isNotBlank(schStartDate) && currDate.compareTo(schStartDate)>=0){
                    if (RotationStatusEnum.Rounding.getId().equals(rotationStatus)) {
                        String processFlow = (String) map.get("processFlow");
                        ResDoctorSchProcess process = new ResDoctorSchProcess();
                        process.setProcessFlow(processFlow);
                        process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
                        int result = studentBiz.updateProcess(process);
                        if (result != 0) {
                            map.put("isCurrentFlag", GlobalConstant.FLAG_Y);
                        }
                    }
                }
            }
        }
        model.addAttribute("results", results);

        //百分比算法
        Map<String, Object> deptPerMap = studentBiz.getRegPer(0, userFlow);
        model.addAttribute("deptPerMap", deptPerMap);

        //System.out.println("deptPerMap:"+ JSON.toJSONString(deptPerMap));
        //数据量
        model.addAttribute("dataCount", PageHelper.total);

        return "res/xnres/student/deptList";
    }

    @RequestMapping(value = {"/funcList"}, method = {RequestMethod.GET})
    public String funcList(String userFlow, String deptFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", ResultEnum.Success.getId());
        model.addAttribute("resultType", ResultEnum.Success.getName());
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3020201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/student/funcList";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "3020202");
            model.addAttribute("resultType", "科室标识符为空");
            return "res/xnres/student/funcList";
        }

        ResDoctorSchProcess process = studentBiz.getProcessByResult(deptFlow);
        model.addAttribute("process", process);
        model.addAttribute("disabled", process == null);
        model.addAttribute("disabledTip", "该项暂时无法填写数据！");

        if (process != null) {
            //百分比算法
            Map<String, Object> perMap = studentBiz.getRegPer(0, userFlow, deptFlow, null, null, true);
            model.addAttribute("perMap", perMap);
        }

        List<ResSignin> signins = appBiz.getSignin(userFlow, deptFlow);
        if (signins != null && !signins.isEmpty()) {
            ResSignin signin = signins.get(0);
            if (signin != null) {
                model.addAttribute("lastSignin", signin.getSignDate());
            }
        }
        //读取这个用户的医师信息
        ResDoctor doctor = appBiz.readResDoctor(userFlow);
        //学员开通web权限
        String isRotationFlag ="";
//		后台配置是否校验权限时间
        String isPermitOpen = appBiz.getCfgByCode("res_permit_open_doc");
        String isAppFlag = "";
        if (GlobalConstant.FLAG_Y.equals(isPermitOpen)) {
//			当前的登录时间
            Map<String, String> webParamMap = new HashMap();
            String webCfgCode = "res_doctor_web_" + userFlow;
            webParamMap.put("cfgCode", webCfgCode);
            webParamMap.put("loginDate", DateUtil.getCurrDate());
            isRotationFlag = resPowerCfgBiz.getResPowerCfg(webParamMap);
            //学员开通app登录权限
            Map<String, String> appParamMap = new HashMap();
            String appCfgCode = "res_doctor_app_login_" + userFlow;
            appParamMap.put("cfgCode", appCfgCode);
            appParamMap.put("loginDate", DateUtil.getCurrDate());
            isAppFlag = resPowerCfgBiz.getResPowerCfg(appParamMap);
        } else {
//			如果不校验校验权限时间，并且开通APP权限，则开通APP
            Map<String, String> webParamMap = new HashMap();
            String webCfgCode = "res_doctor_web_" + userFlow;
            webParamMap.put("cfgCode", webCfgCode);
            isRotationFlag = resPowerCfgBiz.getResPowerCfg(webParamMap);
            //学员开通app登录权限
            Map<String, String> appParamMap = new HashMap();
            String appCfgCode = "res_doctor_app_login_" + userFlow;
            appParamMap.put("cfgCode", appCfgCode);
            isAppFlag = resPowerCfgBiz.getResPowerCfg(appParamMap);
        }

        model.addAttribute("isRotationFlag", isRotationFlag);
        model.addAttribute("isAppFlag", isAppFlag);
        //出科考核表
        if (process != null) {
            //
            String haveMonth = "N";
            SchDept schDept = appBiz.readSchDept(process.getSchDeptFlow());
            if (schDept != null) {
                String monthFlag = appBiz.getCfgByCode("jswjw_" + schDept.getOrgFlow() + "_M001");
                if (GlobalConstant.FLAG_Y.equals(monthFlag)) {
                    haveMonth = "Y";
                    ResSchProcessExpress rec = expressBiz.getExpressByRecType(process.getProcessFlow(), ResRecTypeEnum.MonthlyAssessment_clinic.getId());
                    model.addAttribute("clinicRec", rec);
                    rec = expressBiz.getExpressByRecType(process.getProcessFlow(), ResRecTypeEnum.MonthlyAssessment_inpatientArea.getId());
                    model.addAttribute("areaRec", rec);
                }
            }
            ResSchProcessExpress rec = expressBiz.getExpressByRecType(process.getProcessFlow(), ResRecTypeEnum.AfterEvaluation.getId());
            model.addAttribute("afterEva", rec);
            model.addAttribute("haveMonth", haveMonth);
            //出科考核对接判断
            boolean ckk = false;
            String regScore = appBiz.getCfgByCode("res_doc_reg_score");
            if (GlobalConstant.FLAG_Y.equals(regScore)) {
                String switchFlag = appBiz.getCfgByCode("res_after_test_switch");
                String urlCfg = appBiz.getCfgByCode("res_mobile_after_url_cfg");
//				String orgTestSwitch=appBiz.getCfgByCode("jswjw_"+process.getOrgFlow()+"_P004");
//				String docTestSwitch=appBiz.getCfgByCode("doc_test_switch_"+process.getOrgFlow()+"_"+process.getUserFlow());
                //学员开通出科考试权限
                Map<String, String> paramMap = new HashMap();
                String cfgCode = "res_doctor_ckks_" + userFlow;
                paramMap.put("cfgCode", cfgCode);
                String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
                if (GlobalConstant.FLAG_Y.equals(switchFlag) && GlobalConstant.FLAG_Y.equals(isCkksFlag)
                        && StringUtil.isNotBlank(urlCfg)) {
                    ckk = true;
                }

            }
            model.addAttribute("ckk", ckk);
        }
        return "res/xnres/student/funcList";
    }

    @RequestMapping(value={"/userCenter"},method={RequestMethod.POST})
    public String userCenter(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/student/userCenter";
        }
        //验证用户是否存在
        SysUser userinfo = appBiz.readSysUser(userFlow);
        model.addAttribute("userinfo",userinfo);
        //读取是否开启自主增加轮转计划开关 res_custom_result_flag
        String isManualFlag = appBiz.getCfgByCode("res_custom_result_flag");
        if(!GlobalConstant.FLAG_Y.equals(isManualFlag)){
            isManualFlag = GlobalConstant.FLAG_N;
        }
        model.addAttribute("manualRotationFlag",isManualFlag);

        //读取这个用户的医师信息
        ResDoctor doctor = appBiz.readResDoctor(userFlow);
        model.addAttribute("doctor", doctor);
        if(doctor==null){
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/student/userCenter";
        }
        //出科考核对接判断
        boolean ckk=false;
        String regScore=appBiz.getCfgByCode("res_doc_reg_score");
        if(GlobalConstant.FLAG_Y.equals(regScore))
        {
            String switchFlag=appBiz.getCfgByCode("res_after_test_switch");
            String urlCfg=appBiz.getCfgByCode("res_mobile_after_url_cfg");
            //学员开通出科考试权限
            Map<String,String> paramMap = new HashMap();
            String cfgCode = "res_doctor_ckks_" + userFlow;
            paramMap.put("cfgCode",cfgCode);
            String isCkksFlag = resPowerCfgBiz.getResPowerCfg(paramMap);
            if(GlobalConstant.FLAG_Y.equals(switchFlag) && GlobalConstant.FLAG_Y.equals(isCkksFlag)
                    && StringUtil.isNotBlank(urlCfg))
            {
                ckk=true;
            }

        }
        model.addAttribute("isCkk",ckk);
        //获取该用户的入科记录的第一次入科时间和最后一次出科时间
        Map<String,Object> processAreaMap = appBiz.getDocProcessArea(userFlow);
        if(processAreaMap!=null){
            String minDate = (String)processAreaMap.get("minDate");
            model.addAttribute("minDate",minDate);
            doctor.setInHosDate(minDate);
            appBiz.editResDoctor(doctor);
            long schDays = 0;
            if(StringUtil.isNotBlank(minDate)){
                String currDate = DateUtil.getCurrDate();
                model.addAttribute("maxDate",currDate);

                //获取该医师的已轮转天数
                schDays = DateUtil.signDaysBetweenTowDate(currDate,minDate)+1;
                model.addAttribute("schDays",schDays);

                long trainingDays = 365;
                if((TrainYearEnum.OneYear.getId()+"").equals(doctor.getTrainingYears())){
                    trainingDays*=1;
                }else if((TrainYearEnum.TwoYear.getId()+"").equals(doctor.getTrainingYears())){
                    trainingDays*=2;
                }else if((TrainYearEnum.ThreeYear.getId()+"").equals(doctor.getTrainingYears())){
                    trainingDays*=3;
                }else{
                    trainingDays*=0;
                }
                model.addAttribute("trainingDays",trainingDays);
            }
        }
        List<Map<String,String>> infos = this.noticeBiz.searchInfoByOrgNotRead(doctor.getOrgFlow(), GlobalConstant.RES_NOTICE_TYPE5_ID, GlobalConstant.RES_NOTICE_SYS_ID, userFlow);
        if(infos!=null)
        {
            model.addAttribute("hasNotReadInfo",infos.size());
        }else{
            model.addAttribute("hasNotReadInfo",0);
        }

        HttpServletRequest httpRequest =(HttpServletRequest) request;
        String httpurl=httpRequest.getRequestURL().toString();
        String servletPath=httpRequest.getServletPath();
        String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/xnres/disciple/explain.png";
        model.addAttribute("explainUrl",hostUrl);
        return "res/xnres/student/userCenter";
    }
    @Autowired
    private IExamResultsBiz examResultsBiz;
    @RequestMapping(value = {"/joinExam"}, method = {RequestMethod.POST})
    public String joinExam(String userFlow, String deptFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "40401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/joinExam";
        }
        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "40402");
            model.addAttribute("resultType", "轮转计划标识符为空");
            return "res/xnres/joinExam";
        }
        //考试地址
        String testUrl = appBiz.getCfgByCode("res_mobile_after_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            model.addAttribute("resultId", "40403");
            model.addAttribute("resultType", "请联系管理员维护考试地址!");
            return "res/xnres/joinExam";
        }
        SysUser user = appBiz.readSysUser(userFlow);
        ResDoctorSchProcess process = studentBiz.getProcessByResult(deptFlow);
        if (process == null) {
            model.addAttribute("resultId", "40404");
            model.addAttribute("resultType", "当前轮转信息获取失败!");
            return "res/xnres/joinExam";
        }
        SchAndStandardDeptCfg cfg = deptCfgBiz.readBySchDeptFlow(process.getSchDeptFlow());
        if (cfg == null) {
            model.addAttribute("resultId", "40406");
            model.addAttribute("resultType", "请基地管理员维护出科考核标准科室!");
            return "res/xnres/joinExam";
        }
        String TestNum="";
        ResPowerCfg powerCfg = resPowerCfgBiz.read("out_test_limit_" + process.getOrgFlow());
        if(powerCfg!=null&&StringUtil.isNotBlank(powerCfg.getCfgValue())) {
            TestNum=powerCfg.getCfgValue();
            int c=0;
            List<ExamResults> examResultsList = examResultsBiz.getByProcessFlow(process.getProcessFlow());
            if (examResultsList != null)
                c=examResultsList.size();
            if(c>=Integer.valueOf(powerCfg.getCfgValue()))
            {
                model.addAttribute("resultId", "40404");
                model.addAttribute("resultType", "当前考试次数已经达到"+powerCfg.getCfgValue()+",无法再次考试!");
                return "res/jszy/joinExam";
            }
        }
        //试卷id
        String examSoluId = "0";
        //时间戳
        String Date = "0";
        //标准科室
        String standardDeptId = cfg.getStandardDeptId();
        ResPaper paper = paperBiz.getPaperByOrgStandardDeptId(process.getOrgName(), standardDeptId);
        if(paper==null){
            paper = paperBiz.getPaperByStandardDeptId(standardDeptId);
        }
        if (paper != null) {
            examSoluId = paper.getPaperFlow();
        }
        if ("0".equals(examSoluId)) {
            model.addAttribute("resultId", "40407");
            model.addAttribute("resultType", "该科室暂无试卷信息!");
            return "res/xnres/joinExam";
        }
        //创建分数数据
        ResScore score = appBiz.getScoreByProcess(process.getProcessFlow());
        if (score == null) {
            score = new ResScore();
            score.setDoctorFlow(userFlow);
            score.setScoreTypeId(ResScoreTypeEnum.DeptScore.getId());
            score.setScoreTypeName(ResScoreTypeEnum.DeptScore.getName());
            score.setResultFlow(deptFlow);
            score.setProcessFlow(process.getProcessFlow());
            score.setSchDeptFlow(process.getSchDeptFlow());
            score.setSchDeptName(process.getSchDeptName());
        }

        score.setPaperFlow(examSoluId);

        int saveResult = appBiz.saveScore(score, user);
        if (GlobalConstant.ZERO_LINE >= saveResult) {
            model.addAttribute("resultId", "40408");
            model.addAttribute("resultType", "分数信息创建出错!");
            return "res/xnres/joinExam";
        }
        testUrl = testUrl + "?Action=ChuKeMobileExam&ExamSoluID=" + examSoluId + "&CardID=" + user.getUserCode() + "&ProcessFlow=" + process.getProcessFlow() + "&TestNum=" + TestNum+ "&Date=" + Date;
        model.addAttribute("testUrl", testUrl);
        return "res/xnres/joinExam";
    }

    @RequestMapping(value = {"/standardDeptList"}, method = {RequestMethod.GET})
    public String standardDeptList(String userFlow, String searchData, Integer pageIndex, Integer pageSize, HttpServletRequest request, Model model) throws ParseException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3020201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/student/standardDeptList";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "3020202");
            model.addAttribute("resultType", "当前页数为空");
            return "res/xnres/student/standardDeptList";
        }

        if (pageSize == null) {
            model.addAttribute("resultId", "3020203");
            model.addAttribute("resultType", "每页数据条数为空");
            return "res/xnres/student/standardDeptList";
        }

        ResDoctor doctor = appBiz.readResDoctor(userFlow);
        if (doctor == null || StringUtil.isEmpty(doctor.getRotationFlow())) {
            model.addAttribute("resultId", "3020204");
            model.addAttribute("resultType", "读取医师信息出错");
            return "res/xnres/student/standardDeptList";
        }

        //包装查询条件
        Map<String, Object> paramMap = new HashMap<String, Object>();
        //解析查询条件json字符串  科室名称
        if (StringUtil.isNotBlank(searchData)) {
            try {
                //为json字符串转码
                searchData = new String(searchData.getBytes("ISO8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //转换json字符串为map对象
            Map<String, String> searchMap = (Map<String, String>) JSON.parse(searchData);
            if (searchMap != null && !searchMap.isEmpty()) {
                paramMap.put("deptName ", searchMap.get("deptName "));
            }
        }

        String trainingType = doctor.getTrainingTypeId();
        String sessionNumber = doctor.getSessionNumber();
        String trainingYears = doctor.getTrainingYears();
//        boolean isReduction = JszyTrainCategoryEnum.ChineseMedicine.getId().equals(trainingType)||JszyTrainCategoryEnum.TCMGeneral.getId().equals(trainingType);
//        isReduction = isReduction && "2015".compareTo(sessionNumber)<=0;
//        isReduction = isReduction && (JszyResTrainYearEnum.OneYear.getId().equals(trainingYears) || JszyResTrainYearEnum.TwoYear.getId().equals(trainingYears));
//        paramMap.put("isReduction",isReduction?GlobalConstant.FLAG_Y:GlobalConstant.FLAG_N);

        //读取医师的轮转方案
        paramMap.put("rotationFlow", doctor.getRotationFlow());
        paramMap.put("secondRotationFlow", doctor.getSecondRotationFlow());
        paramMap.put("orgFlow",doctor.getOrgFlow());
        paramMap.put("doctorFlow",doctor.getDoctorFlow());
        paramMap.put("sessionNumber",doctor.getSessionNumber());
        String isAction2="N";
        if("Action2".equals(appBiz.getCfgByCode("res_sch_action_type")))
        {
            isAction2="Y";
        }
        paramMap.put("isAction2",isAction2);

        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String, Object>> resultMaps = studentBiz.getDoctorRotationDept(paramMap);
        model.addAttribute("resultMaps", resultMaps);

        model.addAttribute("dataCount", PageHelper.total);
        //查询标准科室下相应的轮转科室信息
        //组织查询条件
        Map<String, Object> paramMap2 = new HashMap<String, Object>();
        paramMap2.put("doctorFlow", userFlow);
        //读取医师的轮转方案
        paramMap2.put("rotationFlow", doctor.getRotationFlow());
        paramMap2.put("secondRotationFlow", doctor.getSecondRotationFlow());
        paramMap2.put("isAction2",isAction2);

        //按条件查询轮转数据
        List<Map<String, Object>> results = studentBiz.searchResult(paramMap2);
        if (results != null && !results.isEmpty()) {

            Map<String, List<Map<String, Object>>> resultMap = new HashMap<String, List<Map<String, Object>>>();
            //轮转计划单位
            String unit = appBiz.getCfgByCode("res_rotation_unit");
            //默认按月计算
            int step = 30;
            if (SchUnitEnum.Week.getId().equals(unit)) {
                //如果是周按7天算/没配置或者选择月按30天
                step = 7;
            }

            //循环计算实际轮转月份
            for (Map<String, Object> map : results) {
                //获取实际的开始时间与结束时间
                String startDate = (String) map.get("schStartDate");
                String endDate = (String) map.get("schEndDate");

                BigDecimal realMonth = BigDecimal.valueOf(0);
                if (StringUtil.isNotBlank(startDate)) {
                    if (SchUnitEnum.Week.getId().equals(unit)) {
                        //如果是周按7天算/没配置或者选择月按30天
                        step = 7;
                        long realDays = DateUtil.signDaysBetweenTowDate(endDate,startDate)+1;
                        if (realDays != 0) {
                            //计算实际轮转的月/周数
                            double realMonthF = (realDays / (step * 1.0));
                            realMonth = BigDecimal.valueOf(realMonthF);
                            realMonth = realMonth.setScale(1, BigDecimal.ROUND_HALF_UP);
                        }
                    }else{
                        Map<String,String> map2= new HashMap<>();
                        map2.put("startDate",startDate);
                        map2.put("endDate",endDate);
                        Double month = TimeUtil.getMonthsBetween(map2);
                        realMonth=new BigDecimal(month).setScale(1, BigDecimal.ROUND_HALF_UP);
                    }
                }
                map.put("realMonth", realMonth);

                String standardDeptName = (String) map.get("standardDeptName");
                if (StringUtil.isNotBlank(standardDeptName)) {
                    String[] standardDeptNames = standardDeptName.split("\\.");
                    int len = standardDeptNames.length;
                    String subDeptName = standardDeptNames[len - 1];
                    map.put("standardDeptName", subDeptName);
                }

                String isCurrentFlag = (String) map.get("isCurrentFlag");
                String schFlag = (String) map.get("schFlag");
                // 是否让学员填写轮转计划
                if(GlobalConstant.FLAG_Y.equals(appBiz.getCfgByCode("res_custom_result_flag"))||
                        (!GlobalConstant.FLAG_Y.equals(appBiz.getCfgByCode("res_custom_result_flag"))&&
                                GlobalConstant.FLAG_Y.equals(appBiz.getCfgByCode("res_doc_in_by_self"))
                        )){
                    if (!GlobalConstant.FLAG_Y.equals(isCurrentFlag) && !GlobalConstant.FLAG_Y.equals(schFlag)) {
                        String rotationStatus = (String) map.get("rotationStatus");
                        if (RotationStatusEnum.Rounding.getId().equals(rotationStatus)) {
                            String processFlow = (String) map.get("processFlow");
                            ResDoctorSchProcess process = new ResDoctorSchProcess();
                            process.setProcessFlow(processFlow);
                            process.setIsCurrentFlag(GlobalConstant.FLAG_Y);
                            int result = studentBiz.updateProcess(process);
                            if (result != 0) {
                                map.put("isCurrentFlag", GlobalConstant.FLAG_Y);
                            }
                        }
                    }
                }
                String key = (String) map.get("rotationDeptFlow");
                List<Map<String, Object>> resultList = resultMap.get(key);
                if (resultList == null) {
                    resultList = new ArrayList<Map<String, Object>>();
                    resultMap.put(key, resultList);
                }
                resultList.add(map);
            }
            model.addAttribute("resultMap", resultMap);
        }
        model.addAttribute("results", results);

        //百分比算法
        Map<String, Object> deptPerMap = studentBiz.getRegPer(0, userFlow);
        model.addAttribute("deptPerMap", deptPerMap);

        return "res/xnres/student/standardDeptList";
    }

    @RequestMapping(value = {"/addRotationDept"}, method = {RequestMethod.POST})
    public String addRotationDept(
            String userFlow,
            String standardDeptFlow,
            String schDeptFlow,
            String schStartDate,
            String schEndDate,
            String teacherUserFlow,
            String headUserFlow,
            HttpServletRequest request,
            Model model
    ) throws ParseException{
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3020301");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/student/addRotationDept";
        }

        if (StringUtil.isEmpty(standardDeptFlow)) {
            model.addAttribute("resultId", "3020302");
            model.addAttribute("resultType", "标准科室标识符为空");
            return "res/xnres/student/addRotationDept";
        }

        if (StringUtil.isEmpty(schDeptFlow)) {
            model.addAttribute("resultId", "3020303");
            model.addAttribute("resultType", "轮转科室标识符为空");
            return "res/xnres/student/addRotationDept";
        }

        if (StringUtil.isEmpty(schStartDate)) {
            model.addAttribute("resultId", "3020304");
            model.addAttribute("resultType", "开始时间标识符为空");
            return "res/xnres/student/addRotationDept";
        }

        if (StringUtil.isEmpty(schEndDate)) {
            model.addAttribute("resultId", "3020305");
            model.addAttribute("resultType", "结束时间标识符为空");
            return "res/xnres/student/addRotationDept";
        }

        if (StringUtil.isEmpty(teacherUserFlow)) {
            model.addAttribute("resultId", "3020306");
            model.addAttribute("resultType", "带教老师标识符为空");
            return "res/xnres/student/addRotationDept";
        }

        if (StringUtil.isEmpty(headUserFlow)) {
            model.addAttribute("resultId", "3020307");
            model.addAttribute("resultType", "科主任标识符为空");
            return "res/xnres/student/addRotationDept";
        }

        if (schEndDate.compareTo(schStartDate) < 0) {
            model.addAttribute("resultId", "3020308");
            model.addAttribute("resultType", "结束时间小于开始时间");
            return "res/xnres/student/addRotationDept";
        }

        ResDoctor doctor = appBiz.readResDoctor(userFlow);
        if (doctor == null || StringUtil.isEmpty(doctor.getRotationFlow())) {
            model.addAttribute("resultId", "3020309");
            model.addAttribute("resultType", "读取医师信息失败");
            return "res/xnres/student/addRotationDept";
        }
        String secondRotationFlow=doctor.getSecondRotationFlow();
        List<SchArrangeResult> resultList = studentBiz.checkResultDate(userFlow, schStartDate, schEndDate, null, doctor.getRotationFlow(),secondRotationFlow);
        if (resultList != null && !resultList.isEmpty()) {
            model.addAttribute("resultId", "3020310");
            model.addAttribute("resultType", "轮转时间与其他科室重叠");
            return "res/xnres/student/addRotationDept";
        }

        studentBiz.editDoctorResult(userFlow, standardDeptFlow, schDeptFlow, schStartDate, schEndDate, teacherUserFlow, headUserFlow, null);

        return "res/xnres/student/addRotationDept";
    }

    @RequestMapping(value = {"/modRotationDept"}, method = {RequestMethod.POST})
    public String modRotationDept(
            String userFlow,
            String standardDeptFlow,
            String schDeptFlow,
            String schStartDate,
            String schEndDate,
            String teacherUserFlow,
            String headUserFlow,
            String deptFlow,
            HttpServletRequest request,
            Model model
    ) throws ParseException{
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3020401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/student/modRotationDept";
        }

        if (StringUtil.isEmpty(standardDeptFlow)) {
            model.addAttribute("resultId", "3020402");
            model.addAttribute("resultType", "标准科室标识符为空");
            return "res/xnres/student/modRotationDept";
        }

        if (StringUtil.isEmpty(schDeptFlow)) {
            model.addAttribute("resultId", "3020403");
            model.addAttribute("resultType", "轮转科室标识符为空");
            return "res/xnres/student/modRotationDept";
        }

        if (StringUtil.isEmpty(schStartDate)) {
            model.addAttribute("resultId", "3020404");
            model.addAttribute("resultType", "开始时间标识符为空");
            return "res/xnres/student/modRotationDept";
        }

        if (StringUtil.isEmpty(schEndDate)) {
            model.addAttribute("resultId", "3020405");
            model.addAttribute("resultType", "结束时间标识符为空");
            return "res/xnres/student/modRotationDept";
        }

        if (StringUtil.isEmpty(teacherUserFlow)) {
            model.addAttribute("resultId", "3020406");
            model.addAttribute("resultType", "带教老师标识符为空");
            return "res/xnres/student/modRotationDept";
        }

        if (StringUtil.isEmpty(headUserFlow)) {
            model.addAttribute("resultId", "3020407");
            model.addAttribute("resultType", "科主任标识符为空");
            return "res/xnres/student/modRotationDept";
        }

        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "3020408");
            model.addAttribute("resultType", "轮转计划标识符为空");
            return "res/xnres/student/modRotationDept";
        }

        if (schEndDate.compareTo(schStartDate) < 0) {
            model.addAttribute("resultId", "3020409");
            model.addAttribute("resultType", "结束时间小于开始时间");
            return "res/xnres/student/modRotationDept";
        }

        ResDoctor doctor = appBiz.readResDoctor(userFlow);
        if (doctor == null || StringUtil.isEmpty(doctor.getRotationFlow())) {
            model.addAttribute("resultId", "3020410");
            model.addAttribute("resultType", "读取医师信息失败");
            return "res/xnres/student/modRotationDept";
        }

        List<SchArrangeResult> resultList = studentBiz.checkResultDate(userFlow, schStartDate, schEndDate, deptFlow, doctor.getRotationFlow(),doctor.getSecondRotationFlow());
        if (resultList != null && !resultList.isEmpty()) {
            model.addAttribute("resultId", "3020411");
            model.addAttribute("resultType", "轮转时间与其他科室重叠");
            return "res/xnres/student/modRotationDept";
        }

        studentBiz.editDoctorResult(userFlow, standardDeptFlow, schDeptFlow, schStartDate, schEndDate, teacherUserFlow, headUserFlow, deptFlow);

        return "res/xnres/student/modRotationDept";
    }

    @RequestMapping(value = {"/deleteRotationDept"}, method = {RequestMethod.POST})
    public String deleteRotationDept(String userFlow, String deptFlow, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3020501");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/student/deleteRotationDept";
        }

        if (StringUtil.isEmpty(deptFlow)) {
            model.addAttribute("resultId", "3020502");
            model.addAttribute("resultType", "轮转计划标识符为空");
            return "res/xnres/student/deleteRotationDept";
        }

        studentBiz.delDoctorResult(deptFlow);

        return "res/xnres/student/deleteRotationDept";
    }

    /**
     * 最新讲座查询
     */
    @RequestMapping("/getNewLectures")
    public String getNewLectures(Model model, String userFlow, Integer pageIndex, Integer pageSize) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/getNewLectures";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/getNewLectures";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/getNewLectures";
        }
        SysUser currUser = appBiz.readSysUser(userFlow);
        if (currUser == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/getNewLectures";
        }
        String orgFlow = currUser.getOrgFlow();
        if (StringUtil.isBlank(orgFlow)) {
            ResDoctor doctor = appBiz.readResDoctor(currUser.getUserFlow());
            orgFlow = doctor.getOrgFlow();
        }
        //获取当前配置的医师角色
        String doctorRole = appBiz.getCfgByCode("res_doctor_role_flow");
        //获取当前配置的老师角色
        String teacherRole = appBiz.getCfgByCode("res_teacher_role_flow");
        //获取当前配置的科主任角色
        String headRole = appBiz.getCfgByCode("res_head_role_flow");
        //获取当前配置的科秘角色
        String secretaryRole = appBiz.getCfgByCode("res_secretary_role_flow");
        String roleFlow=doctorRole;
        PageHelper.startPage(pageIndex, pageSize);
        List<ResLectureInfo> lectureInfos = appBiz.SearchNewLectures(orgFlow,"Student",roleFlow);
        model.addAttribute("lectureInfos", lectureInfos);
        model.addAttribute("dataCount", PageHelper.total);
        Map<String, ResLectureScanRegist> registMap = new HashMap<>();
        if (lectureInfos != null && lectureInfos.size() > 0) {
            for (ResLectureInfo lectureInfo : lectureInfos) {
                String lectureFlow = lectureInfo.getLectureFlow();
                ResLectureScanRegist lectureScanRegist = appBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
                registMap.put(lectureFlow, lectureScanRegist);
            }
            model.addAttribute("registMap", registMap);
        }
        return "res/xnres/getNewLectures";
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
            return "res/xnres/getHistoryLectures";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/getHistoryLectures";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/getHistoryLectures";
        }
        PageHelper.startPage(pageIndex, pageSize);

        Map<String, ResLectureEvaDetail> evaDetailMap = new HashMap<>();
        Map<String, Integer> evaMap = new HashMap<>();
        Map<String, String> scanMap = new HashMap<>();
        List<Map<String, String>> newList = appBiz.getHistoryLecture(userFlow);
        if (newList != null && !newList.isEmpty()) {
            String currDateTime = DateUtil.getCurrDateTime();
            String currDate = currDateTime.substring(0, 4) + "-" + currDateTime.substring(4, 6) + "-" + currDateTime.substring(6, 8);
            String currTime = currDateTime.substring(8, 10) + ":" + currDateTime.substring(10, 12);
            for (Map<String, String> bean : newList) {
                String isScan = bean.get("isCan");
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
                if (SchUnitEnum.Hour.getId().equals(unitID)) {
                    step = Integer.parseInt(period);
                }
                if (SchUnitEnum.Day.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24;
                }
                if (SchUnitEnum.Week.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24 * 7;
                }
                if (SchUnitEnum.Month.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24 * 30;
                }
                if (SchUnitEnum.Year.getId().equals(unitID)) {
                    step = Integer.parseInt(period) * 24 * 365;
                }
                String endDate = DateUtil.addHour(startDate, step);
                String currentDate = DateUtil.getCurrDateTime();
                int dateFlag = endDate.compareTo(currentDate);
                //判断结束
                if ((lectureEndTime.compareTo(currTime) < 0 && lectureTrainDate.compareTo(currDate) == 0) || (lectureTrainDate.compareTo(currDate) < 0)) {
                    scanMap.put(lectureFlow, isScan);
                    evaMap.put(lectureFlow, dateFlag);
                }
                List<ResLectureEvaDetail> lectureEvaDetails = appBiz.searchByUserFlowLectureFlow(userFlow, lectureFlow);
                if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
                    ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
                    evaDetailMap.put(lectureFlow, lectureEvaDetail);
                }
            }
        }
        model.addAttribute("scanMap", scanMap);
        model.addAttribute("evaMap", evaMap);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("evaDetailMap", evaDetailMap);
        model.addAttribute("lectureInfos", newList);
        return "res/xnres/getHistoryLectures";
    }

    /**
     * 报名讲座
     */
    @RequestMapping("/lectureRegist")
    public synchronized String lectureRegist(String lectureFlow, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "报名成功！");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/lectureRegist";
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "讲座流水号为空");
            return "res/xnres/lectureRegist";
        }
        SysUser currUser = appBiz.readSysUser(userFlow);
        ResLectureScanRegist regist = appBiz.searchByUserFlowAndLectureFlow(userFlow, lectureFlow);
        if (regist != null && StringUtil.isNotBlank(regist.getIsRegist())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "已经报过名了！！");
            return "res/xnres/lectureRegist";
        }
        int count = appBiz.editLectureScanRegist(lectureFlow, currUser, regist);
        if (count == 1) {
            ResLectureInfo lectureInfo = appBiz.read(lectureFlow);
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
        return "res/xnres/lectureRegist";
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
            return "res/xnres/evaluate";
        }
        if (StringUtil.isEmpty(lectureFlow)) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "讲座流水号为空");
            return "res/xnres/evaluate";
        }
        model.addAttribute("lectureFlow", lectureFlow);
        SysUser currUser = appBiz.readSysUser(userFlow);
        List<ResLectureEvaDetail> lectureEvaDetails = appBiz.searchByUserFlowLectureFlow(userFlow, lectureFlow);
        if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
            ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
            if (resLectureEvaDetail != null) {
                model.addAttribute("resLectureEvaDetail", resLectureEvaDetail);
            }
        }
        return "res/xnres/evaluate";
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
            return "res/xnres/saveEvaluate";
        }
        if (StringUtil.isEmpty(resLectureEvaDetail.getLectureFlow())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "讲座流水号为空");
            return "res/xnres/saveEvaluate";
        }
        if (StringUtil.isEmpty(resLectureEvaDetail.getEvaContent())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "评价内容为空");
            return "res/xnres/saveEvaluate";
        }
        if (StringUtil.isEmpty(resLectureEvaDetail.getEvaScore())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "评价分数为空");
            return "res/xnres/saveEvaluate";
        }
        SysUser currUser = appBiz.readSysUser(userFlow);
        String userName = currUser.getUserName();
        if (StringUtil.isNotBlank(userFlow)) {
            resLectureEvaDetail.setOperUserFlow(userFlow);
        }
        if (StringUtil.isNotBlank(userName)) {
            resLectureEvaDetail.setOperUserName(userName);
        }
        ResLectureScanRegist lectureScanRegists = appBiz.searchByUserFlowAndLectureFlow(userFlow, resLectureEvaDetail.getLectureFlow());
        if (lectureScanRegists == null) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "报名信息与扫码信息为空");
            return "res/xnres/saveEvaluate";
        }
        if (StringUtil.isBlank(lectureScanRegists.getIsScan())) {
            model.addAttribute("resultId", "32301");
            model.addAttribute("resultType", "扫码信息为空,不得评价");
            return "res/xnres/saveEvaluate";
        }
        List<ResLectureEvaDetail> lectureEvaDetails = appBiz.searchByUserFlowLectureFlow(userFlow, resLectureEvaDetail.getLectureFlow());
        if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
            model.addAttribute("resultId", "32302");
            model.addAttribute("resultType", "已经评价过讲座信息！请刷新页面后重试！");
            return "res/xnres/saveEvaluate";
        }
        int count = appBiz.editResLectureEvaDetail(resLectureEvaDetail, userFlow);
        if (count == 0) {
            model.addAttribute("resultId", "32302");
            model.addAttribute("resultType", "保存评价失败！");
        }
        return "res/xnres/saveEvaluate";
    }

    @RequestMapping(value = "/suggestions")
    public String suggestions(String userFlow, Integer pageIndex, Integer pageSize, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/suggestions";
        }

        if (pageIndex == null) {
            model.addAttribute("resultId", "30905");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/suggestions";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30906");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/suggestions";
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<ResTrainingOpinion> trainingOpinions = resLiveTrainingBiz.readByOpinionUserFlow(userFlow);
        model.addAttribute("trainingOpinions", trainingOpinions);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/xnres/suggestions";
    }

    @RequestMapping(value = "/delOpinions")
    public String delOpinions(String trainingOpinionFlow, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/success";
        }
        if (StringUtil.isEmpty(trainingOpinionFlow)) {
            model.addAttribute("resultId", "32602");
            model.addAttribute("resultType", "意见反馈流水号为空");
            return "res/xnres/success";
        }
        ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
        if (trainingOpinion == null) {
            model.addAttribute("resultId", "32603");
            model.addAttribute("resultType", "意见反馈不存在");
            return "res/xnres/success";
        }
        trainingOpinion.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int count = resLiveTrainingBiz.edit(trainingOpinion, userFlow);
        if (count == 0) {
            model.addAttribute("resultId", "32601");
            model.addAttribute("resultType", "删除失败");
        }
        return "res/xnres/success";
    }

    @RequestMapping(value = "/opinionsDetail")
    public String opinionsDetail(String trainingOpinionFlow, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/success";
        }
        if (StringUtil.isEmpty(trainingOpinionFlow)) {
            model.addAttribute("resultId", "32602");
            model.addAttribute("resultType", "意见反馈流水号为空");
            return "res/xnres/success";
        }
        ResTrainingOpinion trainingOpinion = resLiveTrainingBiz.read(trainingOpinionFlow);
        List<ResTrainingOpinion> trainingOpinions = new ArrayList<>();
        trainingOpinions.add(trainingOpinion);
        model.addAttribute("trainingOpinions", trainingOpinions);
        return "res/xnres/suggestions";
    }

    @RequestMapping(value = "/saveOpinions")
    public String saveOpinions(ResTrainingOpinion trainingOpinion, String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "31801");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/success";
        }

        if (StringUtil.isEmpty(trainingOpinion.getOpinionUserContent())) {
            model.addAttribute("resultId", "32602");
            model.addAttribute("resultType", "意见反馈信息为空");
            return "res/xnres/success";
        } else {
            try {
                String content = URLDecoder.decode(trainingOpinion.getOpinionUserContent(), "UTF-8");
                trainingOpinion.setOpinionUserContent(content);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        SysUser currentUser = appBiz.readSysUser(userFlow);
        String userName = currentUser.getUserName();
        if (StringUtil.isNotBlank(userName)) {
            trainingOpinion.setOpinionUserName(userName);
        }
        trainingOpinion.setOpinionUserFlow(userFlow);
        ResDoctor doctor = appBiz.readResDoctor(userFlow);
        String orgFlow = doctor.getOrgFlow();
        String orgName = doctor.getOrgName();
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
        return "res/xnres/success";
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = {"/getZhupeiNotices"})
    public String getZhupeiNotices(String userFlow, Integer pageIndex, Integer pageSize, String noticeTitle, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/noticeList";
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/noticeList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/noticeList";
        }
        SysUser currUser = appBiz.readSysUser(userFlow);
        String orgFlow = currUser.getOrgFlow();
        ResDoctor doctor = appBiz.readResDoctor(userFlow);
        if (StringUtil.isBlank(orgFlow)) {
            orgFlow = doctor.getOrgFlow();
        }
        if (pageIndex == null) {
            pageIndex = 1;
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<ResTarinNotice> tarinNotices = null;
        tarinNotices = resLiveTrainingBiz.searchByTitleOrgFlow(noticeTitle, orgFlow);
        model.addAttribute("tarinNotices", tarinNotices);
        model.addAttribute("dataCount", PageHelper.total);	//获取访问路径前缀
        String uploadBaseUrl = appBiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl",uploadBaseUrl);

        HttpServletRequest httpRequest =(HttpServletRequest) request;
        String httpurl=httpRequest.getRequestURL().toString();
        String servletPath=httpRequest.getServletPath();
        String hostUrl=httpurl.substring(0,httpurl.indexOf(servletPath))+"/jsp/res/xnres/showNotice/no-pic.png";
        model.addAttribute("hostUrl",hostUrl);
        return "res/xnres/noticeList";
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
            e.printStackTrace();
        }
        String href = null;

        Matcher matcher = pattern.matcher("<p>指南，加附件</p><p style=\"line-height: 16px;\"><img src=\"/pdsci/ueditor/dialogs/attachment/fileTypeImages/icon_doc.gif\"/><a href=\"http://192.168.2.17:7070/pdsciupload/file/20160907/18811473233002126.docx\">【住院医师过程管理3.5】V3.5.0.69测试记录.docx</a><a href=\"http://192.168.2.17:7070/pdsciupload/file/20160907/18811473233002126.docx\">【住院医师过程管理3.5】V3.5.0.69测试记录.docx</a></p><p><br/></p>");

        if (matcher.find()) {
            href = matcher.group(1);
        }

        //System.out.println(href);
    }

    @RequestMapping(value = {"/zpNoticeDetail"})
    public String zpNoticeDetail(String userFlow, String recordFlow, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/zpNoticeDetail";
        }
        if (StringUtil.isEmpty(recordFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "住培指南标识符为空");
            return "res/xnres/zpNoticeDetail";
        }
        ResTarinNotice tarinNotices = resLiveTrainingBiz.readNotice(recordFlow);
        if (tarinNotices == null) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "住培指南标不存在，请刷新列表页面");
            return "res/xnres/zpNoticeDetail";
        }
        model.addAttribute("title", tarinNotices.getResNoticeTitle());
        model.addAttribute("content", tarinNotices.getResNoticeContent());
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String httpurl = httpRequest.getRequestURL().toString();
        String servletPath = httpRequest.getServletPath();
        String hostUrl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/xnres/showNotice/showNoticeDetail.jsp?recordFlow=" + recordFlow;
        String androidimgurl = httpurl.substring(0, httpurl.indexOf(servletPath)) + "/jsp/res/xnres/showNotice/showNoticeDetail2.jsp?recordFlow=" + recordFlow;
        model.addAttribute("iosDetailUrl", hostUrl);
        model.addAttribute("androidDetailUrl", androidimgurl);
        return "res/xnres/zpNoticeDetail";
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
            return "res/xnres/studentSignIn";
        }
        SysUser user = appBiz.readSysUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/studentSignIn";
        }
        ResDoctor resDoctor = appBiz.readResDoctor(userFlow);
        if (resDoctor == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/studentSignIn";
        }
        String nowDay = DateUtil.getCurrDate();
        List<JsresAttendanceDetail> list = appBiz.getAttendanceDetailList(nowDay, userFlow);
        int count = 0;
        if (list != null) {
            count = list.size();
        }
        model.addAttribute("count", count);
        model.addAttribute("list", list);
        model.addAttribute("nowDay", nowDay);
        String[] timeN = nowDay.split("-");
        String timeInfoN = timeN[0] + "年" + timeN[1] + "月" + timeN[2] + "日";
        model.addAttribute("chinessNowDay", timeInfoN);
        model.addAttribute("resdoctor", resDoctor);
        List<ResOrgAddress> orgAddresses=timeBiz.readOrgAddress(resDoctor.getOrgFlow());
        model.addAttribute("orgAddresses", orgAddresses);
        List<ResOrgTime> times=timeBiz.readOrgTime(resDoctor.getOrgFlow());
        model.addAttribute("times", times);
        return "res/xnres/studentSignIn";
    }

    @RequestMapping(value = {"/saveSignIn"}, method = {RequestMethod.POST})
    public String saveSignIn(String userFlow, String date, String time, String local, String remark, HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/success";
        }
        if (StringUtil.isBlank(date)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "签到日期为空");
            return "res/xnres/success";
        }
        if (StringUtil.isBlank(time)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "签到时间为空");
            return "res/xnres/success";
        }
        if (StringUtil.isBlank(local)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "签到地点为空");
            return "res/xnres/success";
        }
        SysUser user = appBiz.readSysUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/success";
        }
        ResDoctor resDoctor = appBiz.readResDoctor(userFlow);
        if (resDoctor == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/success";
        }
        List<ResOrgTime> times=timeBiz.readOrgTime(resDoctor.getOrgFlow());
        if(times!=null&&times.size()>0)
        {
            boolean f=false;
            for(ResOrgTime t:times)
            {
                if(t.getStartTime().compareTo(time)<=0&&t.getEndTime().compareTo(time)>=0)
                {
                    f=true;
                }
            }
            if(!f)
            {
                model.addAttribute("resultId", "3011102");
                model.addAttribute("resultType", "当前时间不在签到时间范围内，无法签到！");
                return "res/xnres/success";
            }
        }
        String nowDay = DateUtil.getCurrDate();
        JsresAttendance attendance = appBiz.getJsresAttendance(nowDay, userFlow);
        String attendanceFlow = PkUtil.getUUID();
        if (attendance != null) {
            attendanceFlow = attendance.getAttendanceFlow();
        }
        if (attendance == null) {
            attendance = new JsresAttendance();
            attendance.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            attendance.setAttendanceFlow(attendanceFlow);
            attendance.setDoctorFlow(userFlow);
            attendance.setDoctorName(user.getUserName());
            attendance.setAttendDate(nowDay);
            attendance.setCreateTime(DateUtil.getCurrDateTime());
            attendance.setCreateUserFlow(userFlow);
            attendance.setModifyTime(DateUtil.getCurrDateTime());
            attendance.setModifyUserFlow(userFlow);
            appBiz.addJsresAttendance(attendance);
        }
        JsresAttendanceDetail detail = new JsresAttendanceDetail();
        detail.setRecordFlow(PkUtil.getUUID());
        detail.setAttendanceFlow(attendanceFlow);
        detail.setAttendDate(nowDay);
        detail.setAttendTime(time);
        detail.setAttendLocal(local);
        detail.setSelfRemarks(remark);
        detail.setDoctorFlow(userFlow);
        detail.setDoctorName(user.getUserName());
        detail.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        detail.setCreateTime(DateUtil.getCurrDateTime());
        detail.setCreateUserFlow(userFlow);
        detail.setModifyTime(DateUtil.getCurrDateTime());
        detail.setModifyUserFlow(userFlow);
        int count = 0;
        count = appBiz.addJsresAttendanceDetail(detail);
        if (count != 1) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "签到失败！");
        }
        return "res/xnres/success";
    }
    @RequestMapping(value = {"/modfiyPass"}, method = {RequestMethod.POST})
    public String modfiyPass(String userFlow, String oldPass, String newPass,String reNewPass,HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/success";
        }
        SysUser user = appBiz.readSysUser(userFlow);
        if (user == null) {
            model.addAttribute("resultId", "3011102");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/success";
        }
        if (StringUtil.isBlank(oldPass)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "原密码不能为空");
            return "res/xnres/success";
        }
        if (StringUtil.isBlank(newPass)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "新密码不能为空");
            return "res/xnres/success";
        }
        if (StringUtil.isBlank(reNewPass)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "确认密码不能为空");
            return "res/xnres/success";
        }
        if(!newPass.equals(reNewPass))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "再次密码不一致");
            return "res/xnres/success";
        }
        String oldPassword= PasswordHelper.encryptPassword(userFlow,oldPass);
        if(!oldPassword.equals(user.getUserPasswd()))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "原密码输入错误");
            return "res/xnres/success";
        }
        if(oldPass.equals(newPass))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "密码不能与原密码一致");
            return "res/xnres/success";
        }
        String newPassWord= PasswordHelper.encryptPassword(userFlow,newPass);
        user.setUserPasswd(newPassWord);
        int c=appBiz.editUser(user);
        if(c==0)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "修改密码失败！");
            return "res/xnres/success";
        }
        return "res/xnres/success";
    }
    //双向评价（详情页，已存在，对应评价科室/老师）
    @RequestMapping(value={"/gradeDeptList"},method={RequestMethod.POST})
    public String gradeDeptList(String userFlow,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/student/gradeDeptList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/student/gradeDeptList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/student/gradeDeptList";
        }
        //组织查询条件
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("doctorFlow",userFlow);
        if("Action2".equals(appBiz.getCfgByCode("res_sch_action_type")))
        {
            paramMap.put("isAction2","Y");
        }else{
            paramMap.put("isAction2","N");
        }
        //按条件查询轮转数据
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> results = studentBiz.searchResult(paramMap);
        model.addAttribute("results", results);
        model.addAttribute("dataCount", PageHelper.total);

        List<DeptTeacherGradeInfo> gradeList = appBiz.searchAllGrade(userFlow);
        Map<String,String> gradeMap = appBiz.getNewGradeMap(gradeList);
        model.addAttribute("gradeMap", gradeMap);
        return "res/xnres/student/gradeDeptList";
    }

    //出科考试：各科室出科考试列表(已存在“开始考试”，对应出科考核)
    @RequestMapping(value={"/allAfterDept"},method={RequestMethod.POST})
    public String allAfterDept(String userFlow,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/student/allAfterDept";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/student/allAfterDept";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/student/allAfterDept";
        }
        //考试地址
        String testUrl = appBiz.getCfgByCode("res_mobile_after_url_cfg");
        if(!StringUtil.isNotBlank(testUrl)){
            model.addAttribute("resultId", "30403");
            model.addAttribute("resultType", "请联系管理员维护考试地址!");
            return "res/xnres/student/allAfterDept";
        }
        //组织查询条件
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("doctorFlow",userFlow);
        if("Action2".equals(appBiz.getCfgByCode("res_sch_action_type")))
        {
            paramMap.put("isAction2","Y");
        }else{
            paramMap.put("isAction2","N");
        }
        //按条件查询轮转数据
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> results = studentBiz.searchResult(paramMap);
        model.addAttribute("results", results);
        model.addAttribute("dataCount", PageHelper.total);

        Map<String, ResScore> scoreMap = new HashMap<String, ResScore>();
        Map<String,String> countMap=new HashMap<>();
        List<String> orgFlows=new ArrayList<>();
        for(Map<String,Object> result:results) {
            ResDoctorSchProcess process=appBiz.getProcessByResultFlow((String) result.get("resultFlow"));
            if(process!=null)
            {
                ResScore score = appBiz.readScoreByProcessFlow(process.getProcessFlow());
                scoreMap.put((String) result.get("resultFlow"), score);
                List<ExamResults> examResultsList=examResultsBiz.getByProcessFlow(process.getProcessFlow());
                if(examResultsList!=null)
                    countMap.put(process.getProcessFlow(), examResultsList.size()+"");
            }
            if(!orgFlows.contains(result.get("orgFlow"))) {
                ResPowerCfg powerCfg = resPowerCfgBiz.read("out_test_limit_" + result.get("orgFlow"));
                if (powerCfg != null) {
                    countMap.put((String) result.get("orgFlow"), powerCfg.getCfgValue());
                }
                orgFlows.add((String) result.get("orgFlow"));
            }

        }
        String currDate = DateUtil.getCurrDate();
        model.addAttribute("currDate", currDate);
        model.addAttribute("scoreMap", scoreMap);
        model.addAttribute("countMap", countMap);
        return "res/xnres/student/allAfterDept";
    }
    @RequestMapping(value={"/discipleIndex"},method={RequestMethod.POST})
    public String discipleIndex(String userFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/discipleIndex";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/discipleIndex";
        }

        ResDoctor resDoctor=appBiz.readResDoctor(userFlow);
        model.addAttribute("user",user);
        model.addAttribute("resDoctor",resDoctor);
        ResDiscipleInfo info=studentBiz.readResDiscipleInfo(userFlow);
        model.addAttribute("resDiscipleInfo",info);
        String teacherNames=studentBiz.getTeacherName(userFlow);
        model.addAttribute("teacherNames",teacherNames);
        return "res/xnres/disciple/discipleIndex";
    }
    /**
     * 保存手册封面
     * @param bean
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/saveDiscipleInfo")
    public String saveDiscipleInfo(ResDiscipleInfo bean, Model model,String userFlow) throws Exception{
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/discipleIndex";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(bean.getWorkOrgName()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "选派单位为空！");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(bean.getDiscipleStartDate()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "跟师开始时间为空！");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(bean.getDiscipleEndDate()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "跟师结束时间为空！");
            return "res/xnres/disciple/success";
        }
        if(bean.getDiscipleStartDate().compareTo(bean.getDiscipleEndDate())>0)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "跟师开始时间不得大于跟师结束时间！");
            return "res/xnres/disciple/success";
        }
        bean.setSpeName(doctor.getTrainingSpeName());
        bean.setSpeId(doctor.getTrainingSpeId());
        bean.setOrgFlow(doctor.getOrgFlow());
        bean.setOrgName(doctor.getOrgName());
        bean.setDoctorFlow(doctor.getDoctorFlow());
        bean.setDoctorName(user.getUserName());

        ResDiscipleInfo info=studentBiz.readResDiscipleInfo(userFlow);
        if(info!=null)
        {
            bean.setDiscipleFlow(info.getDiscipleFlow());
        }
        int count=studentBiz.savaResDiscipleInfo(bean,user);
        if(count!=1)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "保存失败！");
            return "res/xnres/disciple/success";
        }
        return "res/xnres/disciple/success";
    }
    @RequestMapping(value="/discipleTeacherIndex")
    public String discipleTeacherIndex(Model model,String userFlow) throws Exception{
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/discipleTeacherIndex";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/discipleTeacherIndex";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/discipleTeacherIndex";
        }

        ResDiscipleTeacherInfo info = studentBiz.readResDiscipleTeacherInfo(userFlow);
        model.addAttribute("teacherInfo", info);
        model.addAttribute("doctor",doctor);
        model.addAttribute("user",user);
        return "res/xnres/disciple/discipleTeacherIndex";
    }
    @RequestMapping(value="/saveDiscipleTeacherInfo")
    public String saveDiscipleTeacherInfo(ResDiscipleTeacherInfo bean, Model model,String userFlow) throws Exception{
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/discipleIndex";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isNotBlank(bean.getSexId())) {
            if(!UserSexEnum.Man.getId().equals(bean.getSexId())&&!UserSexEnum.Woman.getId().equals(bean.getSexId()))
            {
                model.addAttribute("resultId", "30401");
                model.addAttribute("resultType", "性别id错误，只能是Man或Woman");
                return "res/xnres/disciple/success";
            }
            bean.setSexName(UserSexEnum.getNameById(bean.getSexId()));
        }else{
            bean.setSexName("");
        }
        bean.setDoctorFlow(userFlow);
        bean.setDoctorName(user.getUserName());
        ResDiscipleTeacherInfo info = studentBiz.readResDiscipleTeacherInfo(userFlow);
        if(info!=null)
        {
            bean.setRecordFlow(info.getRecordFlow());
        }
        int count=studentBiz.savaResDiscipleTeacherInfo(bean,user);
        if(count!=1)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "保存失败！");
            return "res/xnres/disciple/success";
        }
        return "res/xnres/disciple/success";
    }

    @RequestMapping(value={"/showFollowTeacherRecord"},method={RequestMethod.POST})
    public String showFollowTeacherRecord(String userFlow,String typeId,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/showFollowTeacherRecord";
        }
        if(StringUtil.isEmpty(typeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表类型为空");
            return "res/xnres/disciple/showFollowTeacherRecord";
        }
        if(!"All".equals(typeId)&&!"NotAudit".equals(typeId)&&!"Audited".equals(typeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表类型只能是All或NotAudit或Audited");
            return "res/xnres/disciple/showFollowTeacherRecord";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/disciple/showFollowTeacherRecord";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/disciple/showFollowTeacherRecord";
        }
        Map<String,Object> compMap=studentBiz.folowTeacherRecordFinishMap(userFlow);
        //按条件查询轮转数据
        PageHelper.startPage(pageIndex, pageSize);
        List<ResDiscipleRecordInfo> resDiscipleRecordInfos  = studentBiz.searchFolowTeacherRecordByType(userFlow,typeId);
        model.addAttribute("compMap", compMap);
        model.addAttribute("results", resDiscipleRecordInfos);
        model.addAttribute("dataCount", PageHelper.total);

        return "res/xnres/disciple/showFollowTeacherRecord";
    }
    @RequestMapping(value={"/addFollowTeacherRecord"},method={RequestMethod.POST})
    public String addFollowTeacherRecord(String userFlow,String recordFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/addFollowTeacherRecord";
        }
        ResDiscipleRecordInfo resDiscipleRecordInfo = studentBiz.readFollowTeacherRecord(recordFlow);
        model.addAttribute("info",resDiscipleRecordInfo);
        return "res/xnres/disciple/addFollowTeacherRecord";
    }


    /**
     * 保存跟师记录
     * @param model
     * @param resDiscipleRecordInfo
     * @return
     */
    @RequestMapping(value="saveFollowTeacherRecord"  )
    public String saveFollowTeacherRecord(Model model,String userFlow, ResDiscipleRecordInfo resDiscipleRecordInfo){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(resDiscipleRecordInfo.getDiscipleDate()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "出诊日期为空！");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(resDiscipleRecordInfo.getStartTime()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "出诊开始时间为空！");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(resDiscipleRecordInfo.getEndTime()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "出诊结束时间为空！");
            return "res/xnres/disciple/success";
        }
        if(resDiscipleRecordInfo.getStartTime().compareTo(resDiscipleRecordInfo.getEndTime())>0)
        {

            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "出诊开始时间不得大于出诊结束时间！");
            return "res/xnres/disciple/success";
        }
        resDiscipleRecordInfo.setDoctorFlow(user.getUserFlow());
        resDiscipleRecordInfo.setDoctorName(user.getUserName());
        resDiscipleRecordInfo.setTeacherFlow(doctor.getDiscipleTeacherFlow());
        resDiscipleRecordInfo.setTeacherName(doctor.getDiscipleTeacherName());
        resDiscipleRecordInfo.setAuditStatusId(DiscipleStatusEnum.PendingAudit.getId());
        resDiscipleRecordInfo.setAuditStatusName(DiscipleStatusEnum.PendingAudit.getName());
        resDiscipleRecordInfo.setRecordYear(resDiscipleRecordInfo.getDiscipleDate().substring(0,4));
        int count=studentBiz.saveResDiscipleRecordInfo(resDiscipleRecordInfo,user);
        if(count==0)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "保存失败");
            return "res/xnres/disciple/success";
        }
        return "res/xnres/disciple/success";
    }

    /**
     * 删除跟师记录
     * @param recordFlow
     * @return
     */
    @RequestMapping(value="delFollowTeacherRecord")
    public  String delFollowTeacherRecord(Model model,String userFlow,String recordFlow){

        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isEmpty(recordFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/xnres/disciple/success";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDiscipleRecordInfo resDiscipleRecordInfo = studentBiz.readFollowTeacherRecord(recordFlow);
        if(resDiscipleRecordInfo!=null){
            if(!(DiscipleStatusEnum.PendingAudit.getId().equals(resDiscipleRecordInfo.getAuditStatusId())
                        ||DiscipleStatusEnum.UnQualified.getId().equals(resDiscipleRecordInfo.getAuditStatusId())
                ))
            {
                model.addAttribute("resultId", "30401");
                model.addAttribute("resultType", "此记录已审核，无法删除！");
                return "res/xnres/disciple/success";
            }
            resDiscipleRecordInfo.setRecordStatus(GlobalConstant.FLAG_N);
            int count =studentBiz.saveResDiscipleRecordInfo(resDiscipleRecordInfo,user);
            if(count==0)
            {
                model.addAttribute("resultId", "30401");
                model.addAttribute("resultType", "删除失败");
                return "res/xnres/disciple/success";
            }
            model.addAttribute("userFlow", userFlow);
            model.addAttribute("noteTypeId",NoteTypeEnum.FollowTeacherRecord.getId());

            return "redirect:/res/xnres/student/reFreshComMap";
        }else {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据不存在，请刷新页面！");
            return "res/xnres/disciple/success";
        }
    }

    @RequestMapping(value={"/showDiscipleNoteInfo"},method={RequestMethod.POST})
    public String showDiscipleNoteInfo(String userFlow,String typeId,String noteTypeId,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/showDiscipleNoteInfo";
        }
        if(StringUtil.isEmpty(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型为空");
            return "res/xnres/disciple/showDiscipleNoteInfo";
        }
        if(!"Note".equals(noteTypeId)&&!"Experience".equals(noteTypeId)&&!"BookExperience".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型只能是Note或Experience或BookExperience");
            return "res/xnres/disciple/showDiscipleNoteInfo";
        }
        if(StringUtil.isEmpty(typeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表类型为空");
            return "res/xnres/disciple/showDiscipleNoteInfo";
        }
        if(!"All".equals(typeId)&&!"Saved".equals(typeId)&&!"NotAudit".equals(typeId)&&!"Audited".equals(typeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表类型只能是All或Saved或NotAudit或Audited");
            return "res/xnres/disciple/showDiscipleNoteInfo";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/disciple/showDiscipleNoteInfo";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/disciple/showDiscipleNoteInfo";
        }
        Map<String,Object> compMap=studentBiz.discipleNoteInfoFinishMap(userFlow,noteTypeId);


        ResDiscipleNoteInfo discipleNoteInfo = new ResDiscipleNoteInfo();
        discipleNoteInfo.setDoctorFlow(userFlow);
        discipleNoteInfo.setNoteTypeId(noteTypeId);

        List<String> auditStatusList = new ArrayList<>();
        if("Saved".equals(typeId))
        {
            auditStatusList.add(DiscipleStatusEnum.Apply.getId());
        }
        if("NotAudit".equals(typeId))
        {
            auditStatusList.add(DiscipleStatusEnum.Submit.getId());
        }
        if("Audited".equals(typeId))
        {
            auditStatusList.add(DiscipleStatusEnum.Qualified.getId());
            auditStatusList.add(DiscipleStatusEnum.UnQualified.getId());
        }
        //按条件查询轮转数据
        PageHelper.startPage(pageIndex, pageSize);
        List<ResDiscipleNoteInfo> resDiscipleRecordInfos  = studentBiz.findResDiscipleNoteInfo(discipleNoteInfo, auditStatusList);
        model.addAttribute("compMap", compMap);
        model.addAttribute("results", resDiscipleRecordInfos);
        model.addAttribute("dataCount", PageHelper.total);

        return "res/xnres/disciple/showDiscipleNoteInfo";
    }
    @RequestMapping(value={"/addDiscipleNoteInfo"},method={RequestMethod.POST})
    public String addDiscipleNoteInfo(String userFlow,String recordFlow,String noteTypeId,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/noteView";
        }
        if(StringUtil.isEmpty(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型为空");
            return "res/xnres/disciple/noteView";
        }
        if(!"Note".equals(noteTypeId)&&!"Experience".equals(noteTypeId)&&!"BookExperience".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型只能是Note或Experience或BookExperience");
            return "res/xnres/disciple/noteView";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/noteView";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/noteView";
        }
        model.addAttribute("user", user);
        model.addAttribute("doctor", doctor);
        String teacherFLow=doctor.getDiscipleTeacherFlow();
        if(StringUtil.isNotBlank(recordFlow)) {
            ResDiscipleNoteInfoWithBLOBs noteInfoWithBLOBs = studentBiz.findResDiscipleNoteInfoWithBLOBs(recordFlow);
            model.addAttribute("info", noteInfoWithBLOBs);
            //查询附件
            List<PubFile> discipleFiles = fileBiz.searchByProductFlow(recordFlow);
            changeImgToBase64(discipleFiles);
            model.addAttribute("discipleFiles",discipleFiles);
            if(noteInfoWithBLOBs!=null) {
                teacherFLow = noteInfoWithBLOBs.getTeacherFlow();
            }
        }else{
            recordFlow=PkUtil.getUUID();
        }
        model.addAttribute("recordFlow", recordFlow);
        SysUser tea=appBiz.readSysUser(teacherFLow);
        model.addAttribute("tea", tea);
        setSignPhoto(teacherFLow,model, "siginUrl");

        model.addAttribute("doctor",doctor);
        model.addAttribute("user",user);
        return "res/xnres/disciple/noteView";
    }


    @RequestMapping(value={"/saveDiscipleNoteInfo"},method = {RequestMethod.POST})
    public  String saveDiscipleNoteInfo(String userFlow,String recordFlow,String jsonData,ResDiscipleNoteInfoWithBLOBs discipleNoteInfo,
                                        String flag,String noteTypeId, Model model)
    {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(noteTypeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "noteTypeId为空");
            return "res/xnres/disciple/success";
        }
        if(!"Note".equals(noteTypeId)&&!"Experience".equals(noteTypeId)&&!"BookExperience".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型只能是Note或Experience或BookExperience");
            return "res/xnres/disciple/success";
        }
        ResDiscipleNoteInfoWithBLOBs old=studentBiz.findResDiscipleNoteInfoWithBLOBs(recordFlow);
        if(old!=null)
        {
            if(DiscipleStatusEnum.UnQualified.equals(old.getAuditStatusId())||DiscipleStatusEnum.UnQualified.equals(old.getAuditStatusId()))
            {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "该记录已审核，无法保存修改内容！");
                return "res/xnres/disciple/success";
            }
        }

        List<String> fileFlows = new ArrayList<>();
        List<UploadFileForm> members = new ArrayList<>();
        if(StringUtil.isNotBlank(jsonData))
        {

            List<JSONObject> jsonObjects=null;
            try {
                jsonObjects = JSON.parseObject(jsonData, List.class);
            } catch (Exception e) {
                model.addAttribute("resultId", "30401");
                model.addAttribute("resultType", "附件列表上传数据格式错误");
                return "res/xnres/disciple/noteView";
            }
            if (jsonObjects != null && jsonObjects.size() > 0) {
                for (JSONObject jo : jsonObjects) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    UploadFileForm m = null;
                    try {
                        m = objectMapper.readValue(jo.toString(), UploadFileForm.class);
                    } catch (IOException e) {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表上传数据格式错误");
                        return "res/xnres/disciple/noteView";
                    }
                    if(StringUtil.isBlank(m.getFileName()))
                    {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表存在附件名为空");
                        return "res/xnres/disciple/noteView";
                    }
                    if(StringUtil.isBlank(m.getContent())&&StringUtil.isBlank(m.getFileFlow()))
                    {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表存在空附件信息");
                        return "res/xnres/disciple/noteView";
                    }
                    members.add(m);
                    if (StringUtil.isNotBlank(m.getFileFlow())) {
                        fileFlows.add(m.getFileFlow());
                    }
                }
            }
        }
        if ("am".equals(discipleNoteInfo.getStudyTimeId())) {
            discipleNoteInfo.setStudyTimeNmae("上午");
        }
        if ("pm".equals(discipleNoteInfo.getStudyTimeId())) {
            discipleNoteInfo.setStudyTimeNmae("下午");
        }
        if ("allDay".equals(discipleNoteInfo.getStudyTimeId())) {
            discipleNoteInfo.setStudyTimeNmae("全天");
        }
        ResDiscipleNoteInfo resDiscipleNoteInfo = new ResDiscipleNoteInfo();
        if((NoteTypeEnum.Experience.getId().equals(noteTypeId) || NoteTypeEnum.Note.getId().equals(noteTypeId))){
            resDiscipleNoteInfo.setNoteTypeId(noteTypeId);
            resDiscipleNoteInfo.setStudyStartDate(discipleNoteInfo.getStudyStartDate());
            resDiscipleNoteInfo.setDoctorFlow(discipleNoteInfo.getDoctorFlow());
            List<String> status = new ArrayList<>();
            status.add(DiscipleStatusEnum.Submit.getId());
            status.add(DiscipleStatusEnum.Apply.getId());
            status.add(DiscipleStatusEnum.Qualified.getId());
            List<ResDiscipleNoteInfo> resDiscipleNoteInfoList = studentBiz.findResDiscipleNoteInfo(resDiscipleNoteInfo,status);
            if(null != resDiscipleNoteInfoList && resDiscipleNoteInfoList.size()>0){
                if(resDiscipleNoteInfoList.size() ==1){
                    if(!discipleNoteInfo.getRecordFlow().equals(resDiscipleNoteInfoList.get(0).getRecordFlow())){
                        model.addAttribute("resultId", "3011101");
                        model.addAttribute("resultType", "已存在该时间段的记录，不可重复添加！");
                        return "res/xnres/disciple/success";
                    }
                }else {
                    model.addAttribute("resultId", "3011101");
                    model.addAttribute("resultType", "已存在该时间段的记录，不可重复添加！");
                    return "res/xnres/disciple/success";
                }
            }
        }

        discipleNoteInfo.setNoteTypeId(noteTypeId);
        discipleNoteInfo.setNoteTypeName(NoteTypeEnum.getNameById(noteTypeId));
        if (GlobalConstant.FLAG_Y.equals(flag)) {
            discipleNoteInfo.setAuditStatusId(DiscipleStatusEnum.Submit.getId());
            discipleNoteInfo.setAuditStatusName(DiscipleStatusEnum.Submit.getName());
        } else {
            discipleNoteInfo.setAuditStatusId(DiscipleStatusEnum.Apply.getId());
            discipleNoteInfo.setAuditStatusName(DiscipleStatusEnum.Apply.getName());
        }
        SysUser user=appBiz.readSysUser(userFlow);
        int i = studentBiz.updateResDiscipleNoteInfoWithBLOBs(discipleNoteInfo,user,old,fileFlows,members);
        if(i==0)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "保存失败！");
            return "res/xnres/disciple/success";
        }
        return "res/xnres/disciple/success";
    }
    @RequestMapping(value={"/delDiscipleNoteInfo"},method = {RequestMethod.POST})
    public String delDiscipleNoteInfo(String recordFlow,String userFlow, HttpServletRequest request,Model model) {

        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/xnres/disciple/success";
        }
        ResDiscipleNoteInfo info=studentBiz.findResDiscipleNoteInfoWithBLOBs(recordFlow);
        if(info==null)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据信息不存在，请刷新页面！");
            return "res/xnres/disciple/success";
        }
        if(!(DiscipleStatusEnum.Apply.getId().equals(info.getAuditStatusId())
                ||DiscipleStatusEnum.UnQualified.getId().equals(info.getAuditStatusId())
        ))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "信息状态不是保存状态，无法删除！");
            return "res/xnres/disciple/success";
        }
        if (StringUtil.isNotBlank(recordFlow)) {
            int delResult = studentBiz.delResDiscipleNoteInfo(recordFlow);
            if (delResult == 0) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "删除失败！");
                return "res/xnres/disciple/success";
            }
            model.addAttribute("userFlow", userFlow);
            model.addAttribute("noteTypeId",info.getNoteTypeId());

            return "redirect:/res/xnres/student/reFreshComMap";
        }
        return "res/xnres/disciple/success";
    }
    @RequestMapping(value={"/imageList"},method = {RequestMethod.POST})
    public  String imageList(String userFlow,String recordFlow,String noteTypeId,Integer pageIndex,Integer pageSize, Model model)
    {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/imageList";
        }
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/xnres/disciple/imageList";
        }
        String baseDir=appBiz.getCfgByCode("upload_base_dir");
        if(StringUtil.isBlank(baseDir))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请联系管理员，设置上传图片路径！");
            return "res/xnres/disciple/imageList";
        }

        if(pageIndex==null){
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/disciple/imageList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/disciple/imageList";
        }

        if(StringUtil.isBlank(noteTypeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "noteTypeId为空");
            return "res/xnres/disciple/imageList";
        }
        if(!"Note".equals(noteTypeId)&&!"Experience".equals(noteTypeId)&&!"BookExperience".equals(noteTypeId)
                &&!"TypicalCases".equals(noteTypeId)&&!"AnnualAssessment".equals(noteTypeId)&&!"GraduationAssessment".equals(noteTypeId)
                ){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型只能是Note或Experience或BookExperience或TypicalCases或AnnualAssessment或GraduationAssessment");
            return "res/xnres/disciple/imageList";
        }
        if("Note".equals(noteTypeId)||"Experience".equals(noteTypeId)||"BookExperience".equals(noteTypeId))
        {
            ResDiscipleNoteInfo info=studentBiz.findResDiscipleNoteInfoWithBLOBs(recordFlow);
            model.addAttribute("info",info);
        }
        if("GraduationAssessment".equals(noteTypeId))
        {
            ResGraduationAssessment info=studentBiz.findResGraduationAssessment(recordFlow);
            model.addAttribute("info",info);
        }
        if("AnnualAssessment".equals(noteTypeId))
        {
            ResAnnualAssessment info=studentBiz.findResAnnualAssessment(recordFlow);
            model.addAttribute("info",info);
        }
        if("TypicalCases".equals(noteTypeId))
        {

            ResTypicalCases info=studentBiz.findTypicalCases(recordFlow);
            model.addAttribute("info",info);
        }
        //按条件查询轮转数据
        PageHelper.startPage(pageIndex, pageSize);
        List<PubFile> list=fileBiz.searchByProductFlow(recordFlow);
        changeImgToBase64(list);
        model.addAttribute("discipleFiles", list);
        model.addAttribute("dataCount", PageHelper.total);
        String path=StringUtil.defaultString(appBiz.getCfgByCode("upload_base_url"));
        model.addAttribute("uploadPath",path);
        return "res/xnres/disciple/imageList";
    }

    @RequestMapping(value={"/addImage"},method = {RequestMethod.POST})
    public  String addImage(String userFlow, String recordFlow, String imageContent, HttpServletRequest request,String fileName, String noteTypeId, MultipartFile imageContent2, Model model)
    {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        //System.out.println("============"+imageContent);
        //System.out.println("============"+request.getParameter("imageContent2"));
        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(noteTypeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "noteTypeId为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(imageContent)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片内容为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(fileName)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片名称为空");
            return "res/xnres/disciple/success";
        }
        if(!"Note".equals(noteTypeId)&&!"Experience".equals(noteTypeId)&&!"BookExperience".equals(noteTypeId)
                &&!"TypicalCases".equals(noteTypeId)&&!"AnnualAssessment".equals(noteTypeId)&&!"GraduationAssessment".equals(noteTypeId)
                ){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型只能是Note或Experience或BookExperience或TypicalCases或AnnualAssessment或GraduationAssessment");
            return "res/xnres/disciple/success";
        }
        String baseDir=appBiz.getCfgByCode("upload_base_dir");
        if(StringUtil.isBlank(baseDir))
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请联系管理员，设置上传签名图片路径！");
            return "res/xnres/disciple/success";
        }

        //定义上传路径
        String dateString = DateUtil.getCurrDate2();
        String newDir = baseDir+ File.separator + "discipleFiles" + File.separator + noteTypeId + File.separator + dateString+ File.separator+recordFlow;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //重命名上传后的文件名
        String originalFilename = "";
        originalFilename = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
        try {
            generateImage(imageContent,fileDir+  File.separator +originalFilename);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存文件失败！");
        }
        String filePath = File.separator + "discipleFiles" +  File.separator + noteTypeId + File.separator + dateString + File.separator+recordFlow+ File.separator + originalFilename;

        PubFile pubFile = new PubFile();
        pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        pubFile.setFilePath(filePath);
        pubFile.setFileName(fileName);
        pubFile.setFileSuffix(fileName.substring(fileName.lastIndexOf(".")));
        pubFile.setProductType(noteTypeId);
        pubFile.setProductFlow(recordFlow);
        fileBiz.addFile(pubFile);
        return "res/xnres/disciple/success";
    }

    @RequestMapping(value={"/delImage"},method = {RequestMethod.POST})
    public  String delImage(String userFlow,String fileFlow, Model model)
    {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(fileFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片标识符为空");
            return "res/xnres/disciple/success";
        }
        PubFile pubFile = fileBiz.readFile(fileFlow);
        if(pubFile==null)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片信息不存在为空");
            return "res/xnres/disciple/success";
        }
        pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        fileBiz.addFile(pubFile);

        String basePath=appBiz.getCfgByCode("upload_base_dir");
        String filePath = basePath + pubFile.getFilePath();
//        deletefile(filePath);
        return "res/xnres/disciple/success";
    }

    public static boolean deletefile(String delpath) {
        boolean delSuccess = false;
        try {
            File file = new File(delpath);
            if (!file.isDirectory()) {
                //System.out.println("1");
                delSuccess = file.delete();
            } else if (file.isDirectory()) {
                //System.out.println("2");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File delfile = new File(delpath + "\\" + filelist[i]);
                    if (!delfile.isDirectory()) {
                        delSuccess = delfile.delete();
                    } else if (delfile.isDirectory()) {
                        deletefile(delpath + "\\" + filelist[i]);
                    }
                }
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            delSuccess = false;
        }
        return delSuccess;
    }
    //base64字符串转化成图片
    public static boolean generateImage(String imgStr,String savePath) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
        {
            return false;
        }

        String newDir = savePath.substring(0,savePath.lastIndexOf("/"));
        File fileDir = new File(newDir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //生成jpeg图片//新生成的图片
            File imageFile = new File(savePath);
            OutputStream out = new FileOutputStream(imageFile);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private void setSignPhoto(String userFlow, Model model, String key) {
        String isHave="N";
        String siginUrl="";
        if(StringUtil.isNotBlank(userFlow))
        {
            SysUserMapper quserBiz= SpringUtil.getBean(SysUserMapper.class);
            SysUser user=quserBiz.selectByPrimaryKey(userFlow);
            String basePath=StringUtil.defaultString(appBiz.getCfgByCode("upload_base_dir"));
            if(user!=null&&StringUtil.isNotBlank(basePath))
            {
                String photoPath= File.separator+"singinPhoto"+File.separator+user.getUserCode()+".png";
                basePath+=photoPath;
                File imgFile = new File(basePath);
                if(imgFile.exists()){
                    String path=StringUtil.defaultString(appBiz.getCfgByCode("upload_base_url"));
                    if(StringUtil.isNotBlank(path))
                    {
                        isHave="Y";
                        siginUrl=path+photoPath;
                    }
                }
            }
        }
        String path=StringUtil.defaultString(appBiz.getCfgByCode("upload_base_url"));
        model.addAttribute("uploadPath",path);
        model.addAttribute(key+"isHave",isHave);
        model.addAttribute(key,siginUrl);
    }

    @RequestMapping(value={"/bookRecordList"},method={RequestMethod.POST})
    public String bookRecordList(String userFlow,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/bookRecordList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/disciple/bookRecordList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/disciple/bookRecordList";
        }
        //按条件查询轮转数据
        PageHelper.startPage(pageIndex, pageSize);
        List<ResBookStudyRecord> list=studentBiz.getBookStudyRecords(userFlow,"");
        model.addAttribute("list",list);
        model.addAttribute("dataCount", PageHelper.total);

        return "res/xnres/disciple/bookRecordList";
    }
    @RequestMapping(value={"/editBookRecord"},method={RequestMethod.POST})
    public String editBookRecord(String userFlow,String recordFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/editBookRecord";
        }

        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/editBookRecord";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/editBookRecord";
        }
        ResBookStudyRecord record=studentBiz.getBookStudyRecord(recordFlow);
        model.addAttribute("record",record);
        if(record!=null)
        {
            model.addAttribute("recordFlow",record.getRecordFlow());
        }else{
            model.addAttribute("recordFlow",PkUtil.getUUID());
        }
        model.addAttribute("user",user);
        model.addAttribute("doctor",doctor);
        return "res/xnres/disciple/editBookRecord";
    }
    @RequestMapping(value={"/saveBookRecord"},method={RequestMethod.POST})
    public String saveBookRecord(String userFlow,ResBookStudyRecord record,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(record.getStudyStartTime()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "学习开始时间不能为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(record.getStudyEndTime()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "学习结束时间不能为空");
            return "res/xnres/disciple/success";
        }
        if(record.getStudyStartTime().compareTo(record.getStudyEndTime())>0)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "学习开始时间不能大于学习结束时间");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(record.getStudyActionId()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "学习方式不能为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(record.getStudyContent()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "学习书目内容不能为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(record.getRemark()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "备注不能为空");
            return "res/xnres/disciple/success";
        }

        if(StringUtil.isNotBlank(record.getStudyActionId())&& "JIZHONG".equals(record.getStudyActionId())) {
            record.setStudyActionName("集中");
        }
        if(StringUtil.isNotBlank(record.getStudyActionId())&& "ZIXUE".equals(record.getStudyActionId())) {
            record.setStudyActionName("自学");
        }
        if(StringUtil.isBlank(record.getRecordFlow()))
        {
            record.setDoctorFlow(userFlow);
            record.setDoctorName(user.getUserName());
            record.setTeacherFlow(doctor.getDiscipleTeacherFlow());
            record.setTeacherName(doctor.getDiscipleTeacherName());
        }
        record.setRecordYear(record.getStudyStartTime().substring(0,4));
        int count=studentBiz.savaRecord(record,user);
        if(count==0)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "保存失败");
            return "res/xnres/disciple/success";

        }
        return "res/xnres/disciple/success";
    }

    @RequestMapping(value={"/showTypicalCases"},method={RequestMethod.POST})
    public String showTypicalCases(String userFlow,String typeId,String noteTypeId,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/showTypicalCases";
        }
        if(StringUtil.isEmpty(typeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表类型为空");
            return "res/xnres/disciple/showTypicalCases";
        }
        if(StringUtil.isEmpty(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型为空");
            return "res/xnres/disciple/showTypicalCases";
        }
        if(!"TypicalCases".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型只能是TypicalCases");
            return "res/xnres/disciple/showTypicalCases";
        }
        if(!"All".equals(typeId)&&!"Saved".equals(typeId)&&!"NotAudit".equals(typeId)&&!"Audited".equals(typeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表类型只能是All或Saved或NotAudit或Audited");
            return "res/xnres/disciple/showTypicalCases";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/disciple/showTypicalCases";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/disciple/showTypicalCases";
        }
        Map<String,Object> compMap=studentBiz.typicalCasesFinishMap(userFlow);

        List<String> auditStatusList = new ArrayList<>();
        if("Saved".equals(typeId))
        {
            auditStatusList.add(DiscipleStatusEnum.Apply.getId());
        }
        if("NotAudit".equals(typeId))
        {
            auditStatusList.add(DiscipleStatusEnum.PendingAudit.getId());
        }
        if("Audited".equals(typeId))
        {
            auditStatusList.add(DiscipleStatusEnum.Qualified.getId());
            auditStatusList.add(DiscipleStatusEnum.UnQualified.getId());
        }
        ResTypicalCases resTypicalCases = new ResTypicalCases();
        resTypicalCases.setDoctorFlow(userFlow);
        //按条件查询轮转数据
        PageHelper.startPage(pageIndex, pageSize);
        List<ResTypicalCases> resDiscipleRecordInfos  = studentBiz.searchTypicalCases(resTypicalCases, auditStatusList);
        model.addAttribute("compMap", compMap);
        model.addAttribute("results", resDiscipleRecordInfos);
        model.addAttribute("dataCount", PageHelper.total);

        return "res/xnres/disciple/showTypicalCases";
    }
    @RequestMapping(value={"/addTypicalCases"},method={RequestMethod.POST})
    public String addTypicalCases(String userFlow,String recordFlow,String noteTypeId,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/noteView";
        }
        if(StringUtil.isEmpty(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型为空");
            return "res/xnres/disciple/noteView";
        }
        if(!"TypicalCases".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型只能是TypicalCases");
            return "res/xnres/disciple/noteView";
        }

        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/noteView";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/noteView";
        }

        model.addAttribute("doctor",doctor);
        model.addAttribute("user",user);
        String teacherFLow=doctor.getDiscipleTeacherFlow();
        if(StringUtil.isNotBlank(recordFlow)) {
            ResTypicalCases info = studentBiz.findTypicalCases(recordFlow);
            model.addAttribute("info",info);
            //查询附件
            List<PubFile> discipleFiles = fileBiz.searchByProductFlow(recordFlow);
            changeImgToBase64(discipleFiles);
            model.addAttribute("discipleFiles",discipleFiles);
            if(info!=null) {
                teacherFLow = info.getTeacherFlow();
                String times = info.getStudentSignTime();
                times=times.substring(0,4)+"年"+times.substring(4,6)+"月"+times.substring(6,8)+"日";
                if(StringUtil.isNotBlank(info.getAuditTime())){
                    String timea = info.getAuditTime();
                    timea=timea.substring(0,4)+"年"+timea.substring(4,6)+"月"+timea.substring(6,8)+"日";
                    model.addAttribute("auditTime",timea);
                }
                model.addAttribute("signTime",times);
            }
        }else{
            recordFlow=PkUtil.getUUID();
        }
        model.addAttribute("recordFlow", recordFlow);
        SysUser tea=appBiz.readSysUser(teacherFLow);
        model.addAttribute("tea", tea);
        setSignPhoto(teacherFLow,model, "siginUrl");

        String currDate=DateUtil.getCurrDateTime("yyyy")+"年"+DateUtil.getCurrDateTime("MM")+"月"+DateUtil.getCurrDateTime("dd")+"日";
        model.addAttribute("currDate",currDate);
        return "res/xnres/disciple/noteView";
    }

    @RequestMapping(value={"/saveTypicalCases"},method = {RequestMethod.POST})
    public  String saveTypicalCases(String userFlow,String recordFlow,ResTypicalCases info,
                                        String jsonData,String flag,String noteTypeId, Model model)
    {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(noteTypeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "noteTypeId为空");
            return "res/xnres/disciple/success";
        }
        if(!"TypicalCases".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型只能是TypicalCases");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(info.getPeopleName()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "患者姓名为空!");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(info.getSexId()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "性别为空!");
            return "res/xnres/disciple/success";
        }
        if(!UserSexEnum.Man.getId().equals(info.getSexId())&&!UserSexEnum.Woman.getId().equals(info.getSexId()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "性别选择错误只能是男或女!");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(info.getBirthDate()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "出生日期为空!");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(info.getVisitDate()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "就诊日期为空!");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(info.getVisitActionId()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "初诊、复诊为空!");
            return "res/xnres/disciple/success";
        }
        if(!"chuzhen".equals(info.getVisitActionId())&&!"fuzhen".equals(info.getVisitActionId()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "初诊、复诊选择错误!");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(info.getSolarTerms()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "发病节气为空!");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(info.getMainSuit()))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "主诉为空!");
            return "res/xnres/disciple/success";
        }
        String currentDate=DateUtil.getCurrDate();
        if(info.getBirthDate().compareTo(currentDate)>0){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "出生日期不能晚于当前日期!");
            return "res/xnres/disciple/success";
        }
        if(info.getVisitDate().compareTo(currentDate)>0){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "就诊日期不能晚于当前日期!");
            return "res/xnres/disciple/success";
        }
        if(info.getVisitDate().compareTo(info.getBirthDate())<0){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "就诊日期不能早于出生日期!");
            return "res/xnres/disciple/success";
        }
        List<String> fileFlows = new ArrayList<>();
        List<UploadFileForm> members = new ArrayList<>();
        if(StringUtil.isNotBlank(jsonData))
        {

            List<JSONObject> jsonObjects=null;
            try {
                jsonObjects = JSON.parseObject(jsonData, List.class);
            } catch (Exception e) {
                model.addAttribute("resultId", "30401");
                model.addAttribute("resultType", "附件列表上传数据格式错误");
                return "res/xnres/disciple/noteView";
            }
            if (jsonObjects != null && jsonObjects.size() > 0) {
                for (JSONObject jo : jsonObjects) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    UploadFileForm m = null;
                    try {
                        m = objectMapper.readValue(jo.toString(), UploadFileForm.class);
                    } catch (IOException e) {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表上传数据格式错误");
                        return "res/xnres/disciple/noteView";
                    }
                    if(StringUtil.isBlank(m.getFileName()))
                    {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表存在附件名为空");
                        return "res/xnres/disciple/noteView";
                    }
                    if(StringUtil.isBlank(m.getContent())&&StringUtil.isBlank(m.getFileFlow()))
                    {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表存在空附件信息");
                        return "res/xnres/disciple/noteView";
                    }
                    members.add(m);
                    if (StringUtil.isNotBlank(m.getFileFlow())) {
                        fileFlows.add(m.getFileFlow());
                    }
                }
            }
        }
        info.setRecordFlow(recordFlow);
        ResTypicalCases old = studentBiz.findResTypicalCases(recordFlow);
        ResTypicalCases newCase=doSave(info,old,user,doctor);
        if (GlobalConstant.FLAG_Y.equals(flag)) {
            newCase.setAuditStatusId(DiscipleStatusEnum.PendingAudit.getId());
            newCase.setAuditStatusName(DiscipleStatusEnum.PendingAudit.getName());
        } else {
            newCase.setAuditStatusId(DiscipleStatusEnum.Apply.getId());
            newCase.setAuditStatusName(DiscipleStatusEnum.Apply.getName());
        }
        int i = studentBiz.saveResTypicalCases(newCase,user,old,fileFlows,members);
        if(i==0)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "保存失败！");
            return "res/xnres/disciple/success";
        }
        return "res/xnres/disciple/success";
    }

    public ResTypicalCases doSave(ResTypicalCases resTypicalCases,ResTypicalCases old,SysUser user,ResDoctor doctor){
        ResTypicalCases newCase = null;
        if(old!=null){
            newCase=old;
            newCase.setPeopleName(resTypicalCases.getPeopleName());
            newCase.setSexId(resTypicalCases.getSexId());
            newCase.setBirthDate(resTypicalCases.getBirthDate());
            newCase.setVisitDate(resTypicalCases.getVisitDate());
            newCase.setVisitActionId(resTypicalCases.getVisitActionId());
            newCase.setSolarTerms(resTypicalCases.getSolarTerms());
            newCase.setMainSuit(resTypicalCases.getMainSuit());
            newCase.setPresentDiseaseHistory(resTypicalCases.getPresentDiseaseHistory());
            newCase.setPreviousDiseaseHistory(resTypicalCases.getPreviousDiseaseHistory());
            newCase.setAllergicHistory(resTypicalCases.getAllergicHistory());
            newCase.setPhysicalExamination(resTypicalCases.getPhysicalExamination());
            newCase.setAccessoryExamination(resTypicalCases.getAccessoryExamination());
            newCase.setTcmDiagnosis(resTypicalCases.getTcmDiagnosis());
            newCase.setSyndromeDiagnosis(resTypicalCases.getSyndromeDiagnosis());
            newCase.setWesternDiagnosis(resTypicalCases.getWesternDiagnosis());
            newCase.setTherapy(resTypicalCases.getTherapy());
            newCase.setPrescription(resTypicalCases.getPrescription());
            newCase.setReturnVisit(resTypicalCases.getReturnVisit());
            newCase.setExperienceContent(resTypicalCases.getExperienceContent());
        }else {
            newCase=resTypicalCases;
            newCase.setAuditStatusId(DiscipleStatusEnum.Apply.getId());
            newCase.setAuditStatusName(DiscipleStatusEnum.Apply.getName());
            newCase.setDoctorFlow(user.getUserFlow());
            newCase.setDoctorName(user.getUserName());
            newCase.setTeacherFlow(doctor.getDiscipleTeacherFlow());
            newCase.setTeacherName(doctor.getDiscipleTeacherName());
            newCase.setStudentSignTime(DateUtil.getCurrDateTime());
            newCase.setRecordYear(DateUtil.getYear());
        }
        if("Man".equals(newCase.getSexId())){
            newCase.setSexId(UserSexEnum.Man.getId());
            newCase.setSexName(UserSexEnum.Man.getName());
        }else {
            newCase.setSexId(UserSexEnum.Woman.getId());
            newCase.setSexName(UserSexEnum.Woman.getName());
        }
        if("chuzhen".equals(newCase.getVisitActionId())){
            newCase.setVisitActionName("初诊");
        }else {
            newCase.setVisitActionName("复诊");
        }
        return newCase;
    }
    @RequestMapping(value={"/delTypicalCases"},method={RequestMethod.POST})
    public String delTypicalCases(String userFlow,String recordFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/success";
        }
        ResTypicalCases info = studentBiz.findTypicalCases(recordFlow);
        if(info==null){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医案信息不存在，请刷新列表页面！");
            return "res/xnres/disciple/success";
        }
        if(!(DiscipleStatusEnum.Apply.getId().equals(info.getAuditStatusId())
                ||DiscipleStatusEnum.UnQualified.getId().equals(info.getAuditStatusId())
        ))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "该医案信息状态不是保存状态，无法删除！");
            return "res/xnres/disciple/success";
        }
        info.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int count=studentBiz.delResTypicalCases(info,user, info);
        if(count==0)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "删除失败！");
            return "res/xnres/disciple/success";
        }
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("noteTypeId",NoteTypeEnum.TypicalCases.getId());

        return "redirect:/res/xnres/student/reFreshComMap";
    }


    @RequestMapping(value={"/annualAssessmentList"},method={RequestMethod.POST})
    public String annualAssessmentList(String userFlow,String noteTypeId,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/annualAssessmentList";
        }
        if(StringUtil.isEmpty(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型为空");
            return "res/xnres/disciple/annualAssessmentList";
        }
        if(!"AnnualAssessment".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型只能是AnnualAssessment");
            return "res/xnres/disciple/annualAssessmentList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return "res/xnres/disciple/annualAssessmentList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return "res/xnres/disciple/annualAssessmentList";
        }
        ResAnnualAssessment assessment = new ResAnnualAssessment();
        assessment.setDoctorFlow(userFlow);
        //按条件查询轮转数据
        PageHelper.startPage(pageIndex, pageSize);
        List<ResAnnualAssessment>  assessmentList = studentBiz.findAnnualAssessmentList(assessment,null);
        model.addAttribute("results", assessmentList);
        model.addAttribute("dataCount", PageHelper.total);

        return "res/xnres/disciple/annualAssessmentList";
    }
    @RequestMapping(value={"/addAnnualAssessment"},method={RequestMethod.POST})
    public String addAnnualAssessment(String userFlow,String noteTypeId,String recordFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/noteView";
        }
        if(StringUtil.isEmpty(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型为空");
            return "res/xnres/disciple/noteView";
        }
        if(!"AnnualAssessment".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型只能是AnnualAssessment");
            return "res/xnres/disciple/noteView";
        }
        if(StringUtil.isBlank(recordFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/xnres/disciple/noteView";
        }

        ResAnnualAssessment assessment = new ResAnnualAssessment();
            assessment.setDoctorFlow(userFlow);
            assessment.setRecordFlow(recordFlow);
        AnnualAssessmentExt annualAssessmentExt = studentBiz.initAnnualAssessmentExt(assessment);
        model.addAttribute("recordFlow", recordFlow);//查询附件
        List<PubFile> discipleFiles = fileBiz.searchByProductFlow(recordFlow);
        changeImgToBase64(discipleFiles);
        model.addAttribute("discipleFiles",discipleFiles);
        model.addAttribute("info",annualAssessmentExt);

        String path=StringUtil.defaultString(appBiz.getCfgByCode("upload_base_url"));
        model.addAttribute("uploadPath",path);
        if(annualAssessmentExt==null)
        {
            annualAssessmentExt=new AnnualAssessmentExt();
        }else if(StringUtil.isNotBlank(annualAssessmentExt.getAssessmentImgUrl())){
            annualAssessmentExt.setAssessmentImgUrl(annualAssessmentExt.getAssessmentImgUrl().replaceAll("\\\\","/"));
        }
        setSignPhoto(annualAssessmentExt.getTeacherFlow(),model,"siginUrl");
        setSignPhoto(annualAssessmentExt.getAdminUserFlow(),model,"siginUrl2");


        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/noteView";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/noteView";
        }

        model.addAttribute("doctor",doctor);
        model.addAttribute("user",user);
        String teacherFLow=doctor.getDiscipleTeacherFlow();
        if(StringUtil.isNotBlank(recordFlow)) {
            if(annualAssessmentExt!=null) {
                teacherFLow = annualAssessmentExt.getTeacherFlow();
            }
        }else{
            recordFlow=PkUtil.getUUID();
        }
        model.addAttribute("recordFlow", recordFlow);
        SysUser tea=appBiz.readSysUser(teacherFLow);
        model.addAttribute("tea", tea);
        return "res/xnres/disciple/noteView";
    }
    @RequestMapping(value={"/selectAnnualTime"},method={RequestMethod.POST})
    public String selectAnnualTime(String userFlow,String noteTypeId,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/selectAnnualTime";
        }
        if(StringUtil.isEmpty(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型为空");
            return "res/xnres/disciple/selectAnnualTime";
        }
        if(!"AnnualAssessment".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型只能是AnnualAssessment");
            return "res/xnres/disciple/selectAnnualTime";
        }
        return "res/xnres/disciple/selectAnnualTime";
    }
    @RequestMapping(value={"/saveAnnualTime"},method={RequestMethod.POST})
    public String saveAnnualTime(String userFlow,String noteTypeId,String annualYear,
                                 String startTime,String endTime,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/noteView";
        }

        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/noteView";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/noteView";
        }
        if(StringUtil.isEmpty(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型为空");
            return "res/xnres/disciple/noteView";
        }
        if(StringUtil.isEmpty(annualYear)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "年度为空");
            return "res/xnres/disciple/noteView";
        }

        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy");
            dateFormat2.parse(annualYear);
        } catch (Exception e) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "年度时间格式有误");
            return "res/xnres/disciple/noteView";
        }
        if(StringUtil.isEmpty(startTime)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "跟师开始日期为空");
            return "res/xnres/disciple/noteView";
        }
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat2.parse(startTime);
        } catch (Exception e) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "跟师开始日期时间格式有误");
            return "res/xnres/disciple/noteView";
        }
        if(StringUtil.isEmpty(endTime)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "跟师结束日期为空");
            return "res/xnres/disciple/noteView";
        }
        try {
            SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat2.parse(endTime);
        } catch (Exception e) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "跟师结束日期时间格式有误");
            return "res/xnres/disciple/noteView";
        }
        if(!"AnnualAssessment".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型只能是AnnualAssessment");
            return "res/xnres/disciple/noteView";
        }
        int c=studentBiz.checkAnnualDate(userFlow,startTime,endTime);
        if(c>0)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "跟师时间存在冲突");
            return "res/xnres/disciple/noteView";
        }

        ResAnnualAssessment assessment = new ResAnnualAssessment();
        assessment.setDoctorFlow(userFlow);
        assessment.setRecordYear(annualYear);
        assessment.setStudyStartDate(startTime);
        assessment.setStudyEndDate(endTime);
        AnnualAssessmentExt annualAssessmentExt = studentBiz.initAnnualAssessmentExt(assessment);
        model.addAttribute("info",annualAssessmentExt);
        String path=StringUtil.defaultString(appBiz.getCfgByCode("upload_base_url"));
        model.addAttribute("uploadPath",path);
        annualAssessmentExt=new AnnualAssessmentExt();
        setSignPhoto(annualAssessmentExt.getTeacherFlow(),model,"siginUrl");
        setSignPhoto(annualAssessmentExt.getAdminUserFlow(),model,"siginUrl2");


        model.addAttribute("doctor",doctor);
        model.addAttribute("user",user);
        String teacherFLow=doctor.getDiscipleTeacherFlow();
        model.addAttribute("recordFlow", PkUtil.getUUID());
        SysUser tea=appBiz.readSysUser(teacherFLow);
        model.addAttribute("tea", tea);
        return "res/xnres/disciple/noteView";
    }

    @RequestMapping(value={"/saveAnnualAssessment"},method = {RequestMethod.POST})
    public  String saveAnnualAssessment(String userFlow,String recordFlow,ResAnnualAssessmentWithBLOBs assessmentWithBLOBs,
                                        String flag, String jsonData,String noteTypeId, Model model)
    {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(noteTypeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "noteTypeId为空");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(assessmentWithBLOBs.getRecordYear())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "年度为空");
            return "res/xnres/disciple/success";
        }
        if(!"AnnualAssessment".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型只能是AnnualAssessment");
            return "res/xnres/disciple/success";
        }
        List<String> fileFlows = new ArrayList<>();
        List<UploadFileForm> members = new ArrayList<>();
        if(StringUtil.isNotBlank(jsonData))
        {

            List<JSONObject> jsonObjects=null;
            try {
                jsonObjects = JSON.parseObject(jsonData, List.class);
            } catch (Exception e) {
                model.addAttribute("resultId", "30401");
                model.addAttribute("resultType", "附件列表上传数据格式错误");
                return "res/xnres/disciple/success";
            }
            if (jsonObjects != null && jsonObjects.size() > 0) {
                for (JSONObject jo : jsonObjects) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    UploadFileForm m = null;
                    try {
                        m = objectMapper.readValue(jo.toString(), UploadFileForm.class);
                    } catch (IOException e) {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表上传数据格式错误");
                        return "res/xnres/disciple/success";
                    }
                    if(StringUtil.isBlank(m.getFileName()))
                    {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表存在附件名为空");
                        return "res/xnres/disciple/success";
                    }
                    if(StringUtil.isBlank(m.getContent())&&StringUtil.isBlank(m.getFileFlow()))
                    {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表存在空附件信息");
                        return "res/xnres/disciple/success";
                    }
                    members.add(m);
                    if (StringUtil.isNotBlank(m.getFileFlow())) {
                        fileFlows.add(m.getFileFlow());
                    }
                }
            }
        }
        if (GlobalConstant.FLAG_Y.equals(flag)) {
            ResAnnualAssessment assessment = new ResAnnualAssessment();
            assessment.setDoctorFlow(userFlow);
            assessment.setRecordYear(assessmentWithBLOBs.getRecordYear());
            List<String> statusList = new ArrayList<>();
            statusList.add(DiscipleStatusEnum.DiscipleAudit.getId());
            statusList.add(DiscipleStatusEnum.AdminAudit.getId());
            statusList.add(DiscipleStatusEnum.Submit.getId());
            List<ResAnnualAssessment> assessmentList = studentBiz.findAnnualAssessmentList(assessment, statusList);
            if(assessmentList!=null&&assessmentList.size()>0)
            {
                model.addAttribute("resultId", "30401");
                model.addAttribute("resultType", assessmentWithBLOBs.getRecordYear()+"已存在年度考核表，无法再次提交");
                return "res/xnres/disciple/success";

            }
            assessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.Submit.getId());
            assessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.Submit.getName());
        } else {
            assessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.Apply.getId());
            assessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.Apply.getName());
        }
        ResAnnualAssessment old=studentBiz.findResAnnualAssessment(assessmentWithBLOBs.getRecordFlow());
        if(old==null)
        {
            assessmentWithBLOBs.setDoctorFlow(user.getUserFlow());
            assessmentWithBLOBs.setDoctorName(user.getUserName());
            assessmentWithBLOBs.setTeacherFlow(doctor.getDiscipleTeacherFlow());
            assessmentWithBLOBs.setTeacherName(doctor.getDiscipleTeacherName());
        }
        int i = studentBiz.editAnnualAssessment(assessmentWithBLOBs,user,old,fileFlows,members);
        if(i==0)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "保存失败！");
            return "res/xnres/disciple/success";
        }
        return "res/xnres/disciple/success";
    }
    @RequestMapping(value={"/delAnnualAssessment"},method={RequestMethod.POST})
    public String delAnnualAssessment(String userFlow,String recordFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/success";
        }
        ResAnnualAssessment info = studentBiz.findResAnnualAssessment(recordFlow);
        if(info==null){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "年度考核信息不存在，请刷新列表页面！");
            return "res/xnres/disciple/success";
        }
        if(!(DiscipleStatusEnum.Apply.getId().equals(info.getAuditStatusId())
        ||DiscipleStatusEnum.AdminBack.getId().equals(info.getAuditStatusId())
                ||DiscipleStatusEnum.DiscipleBack.getId().equals(info.getAuditStatusId())))
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "该年度考核信息状态不是保存或不通过状态，无法删除！");
            return "res/xnres/disciple/success";
        }
        int delResult = studentBiz.delAnnualAssessment(recordFlow);
        if(delResult==0)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "删除失败！");
            return "res/xnres/disciple/success";
        }
        return "res/xnres/disciple/success";
    }

    @RequestMapping(value={"/addGraduationAssessment"},method={RequestMethod.POST})
    public String addGraduationAssessment(String userFlow,String noteTypeId,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/noteView";
        }
        if(StringUtil.isEmpty(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型为空");
            return "res/xnres/disciple/noteView";
        }
        if(!"GraduationAssessment".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型只能是GraduationAssessment");
            return "res/xnres/disciple/noteView";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/noteView";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/noteView";
        }

        ResGraduationAssessmentExt ext=studentBiz.getDocGraduationAssessment(userFlow);
        model.addAttribute("info",ext);
        model.addAttribute("doctor",doctor);
        model.addAttribute("user",user);
        //查询附件
        List<PubFile> discipleFiles =fileBiz.searchByProductFlow(ext.getRecordFlow());
        changeImgToBase64(discipleFiles);
        model.addAttribute("discipleFiles",discipleFiles);

        String teacherFLow=doctor.getDiscipleTeacherFlow();
        String path=StringUtil.defaultString(appBiz.getCfgByCode("upload_base_url"));
        model.addAttribute("uploadPath",path);
        if(ext==null)
        {
            ext=new ResGraduationAssessmentExt();
        }else if(StringUtil.isNotBlank(ext.getAssessmentImgUrl())){
            ext.setAssessmentImgUrl(ext.getAssessmentImgUrl().replaceAll("\\\\","/"));
            teacherFLow = ext.getTeacherFlow();
        }
        setSignPhoto(ext.getTeacherFlow(),model,"siginUrl");
        setSignPhoto(ext.getAdminUserFlow(),model,"siginUrl2");

        SysUser tea=appBiz.readSysUser(teacherFLow);
        model.addAttribute("tea", tea);
        return "res/xnres/disciple/noteView";
    }
    @RequestMapping(value={"/saveGraduationAssessment"},method = {RequestMethod.POST})
    public  String saveGraduationAssessment(String userFlow,String recordFlow,ResGraduationAssessmentWithBLOBs assessmentWithBLOBs,
                                        String flag, String jsonData,String noteTypeId, Model model)
    {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if(StringUtil.isBlank(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/success";
        }
        SysUser user=appBiz.readSysUser(userFlow);
        if(user==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户不存在");
            return "res/xnres/disciple/success";
        }
        ResDoctor doctor=appBiz.readResDoctor(userFlow);
        if(doctor==null)
        {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "医师信息不存在");
            return "res/xnres/disciple/success";
        }
        if(StringUtil.isBlank(noteTypeId)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "noteTypeId为空");
            return "res/xnres/disciple/success";
        }
        if(!"GraduationAssessment".equals(noteTypeId)){
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "列表数据类型只能是GraduationAssessment");
            return "res/xnres/disciple/success";
        }
        if (GlobalConstant.FLAG_Y.equals(flag)) {
            assessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.Submit.getId());
            assessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.Submit.getName());
        }
        ResGraduationAssessment old=studentBiz.findResGraduationAssessment(assessmentWithBLOBs.getRecordFlow());
        if(old==null)
        {
            old=studentBiz.findResGraduationAssessmentByDoctorFlow(userFlow);
        }
        if(old==null)
        {
            assessmentWithBLOBs.setDoctorFlow(user.getUserFlow());
            assessmentWithBLOBs.setDoctorName(user.getUserName());
            assessmentWithBLOBs.setTeacherFlow(doctor.getDiscipleTeacherFlow());
            assessmentWithBLOBs.setTeacherName(doctor.getDiscipleTeacherName());
        }else{
            assessmentWithBLOBs.setRecordFlow(old.getRecordFlow());
        }
        List<String> fileFlows = new ArrayList<>();
        List<UploadFileForm> members = new ArrayList<>();
        if(StringUtil.isNotBlank(jsonData))
        {

            List<JSONObject> jsonObjects=null;
            try {
                jsonObjects = JSON.parseObject(jsonData, List.class);
            } catch (Exception e) {
                model.addAttribute("resultId", "30401");
                model.addAttribute("resultType", "附件列表上传数据格式错误");
                return "res/xnres/disciple/noteView";
            }
            if (jsonObjects != null && jsonObjects.size() > 0) {
                for (JSONObject jo : jsonObjects) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    UploadFileForm m = null;
                    try {
                        m = objectMapper.readValue(jo.toString(), UploadFileForm.class);
                    } catch (IOException e) {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表上传数据格式错误");
                        return "res/xnres/disciple/noteView";
                    }
                    if(StringUtil.isBlank(m.getFileName()))
                    {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表存在附件名为空");
                        return "res/xnres/disciple/noteView";
                    }
                    if(StringUtil.isBlank(m.getContent())&&StringUtil.isBlank(m.getFileFlow()))
                    {
                        model.addAttribute("resultId", "30401");
                        model.addAttribute("resultType", "附件列表存在空附件信息");
                        return "res/xnres/disciple/noteView";
                    }
                    members.add(m);
                    if (StringUtil.isNotBlank(m.getFileFlow())) {
                        fileFlows.add(m.getFileFlow());
                    }
                }
            }
        }
        int i = studentBiz.save(assessmentWithBLOBs,user,fileFlows,members);
        if(i==0)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "保存失败！");
            return "res/xnres/disciple/success";
        }
        return "res/xnres/disciple/success";
    }
    @RequestMapping(value={"/reFreshComMap"},method={RequestMethod.POST,RequestMethod.GET})
    public String reFreshComMap(String userFlow,String noteTypeId,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/xnres/disciple/reFreshComMap";
        }
        if (StringUtil.isEmpty(noteTypeId)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型为空");
            return "res/xnres/disciple/reFreshComMap";
        }
        if (!"FollowTeacherRecord".equals(noteTypeId)&&!"Note".equals(noteTypeId)&&!"Experience".equals(noteTypeId)&&!"BookExperience".equals(noteTypeId)
                &&!"TypicalCases".equals(noteTypeId)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "数据类型只能是FollowTeacherRecord或Note或Experience或BookExperience或TypicalCases");
            return "res/xnres/disciple/reFreshComMap";
        }
        Map<String,Object> compMap=null;
        if ("FollowTeacherRecord".equals(noteTypeId))
        {
            compMap =studentBiz.folowTeacherRecordFinishMap(userFlow);
        }
        if ("TypicalCases".equals(noteTypeId))
        {
            compMap =studentBiz.typicalCasesFinishMap(userFlow);
        }
        if ("Note".equals(noteTypeId)||"Experience".equals(noteTypeId)||"BookExperience".equals(noteTypeId))
        {
            compMap =studentBiz.discipleNoteInfoFinishMap(userFlow,noteTypeId);
        }
        model.addAttribute("compMap", compMap);
        return "res/xnres/disciple/reFreshComMap";
    }

    private void changeImgToBase64(List<PubFile> discipleFiles) {
        if(discipleFiles!=null&&discipleFiles.size()>0)
        {
            String baseDir=appBiz.getCfgByCode("upload_base_dir");
            for(PubFile file:discipleFiles)
            {
//                String filePath=baseDir+File.separator+file.getFilePath();
//                file.setFileRemark(getImageStr(filePath));
                file.setFilePath(file.getFilePath().replaceAll("\\\\","/"));
            }
        }
    }
    public String getImageStr(String imgFile) {
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 加密
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    @RequestMapping(value = {"/checkGraduationFile"}, method = {RequestMethod.POST})
    public String checkGraduationFile(String userFlow,  HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");

        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "01000301");
            model.addAttribute("resultType", "用户流水号为空");
            return "res/xnres/checkGraduationFile";
        }

        String baseDirs = appBiz.getCfgByCode("upload_base_dir");
        if (StringUtil.isBlank(baseDirs)) {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", "文件保存路径未配置，请联系管理员！");
            return "res/xnres/checkGraduationFile";
        }
        PubFile search=new PubFile();
        search.setCreateUserFlow(userFlow);
        search.setProductType("GRADUATION_FILE");
        PubFile pf=fileBiz.readDocGraduationFile(search);
        model.addAttribute("file",pf);
        if(pf==null)
        {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", "暂未上传论文附件！");
            return "res/xnres/checkGraduationFile";
        }  String filePath =baseDirs+ File.separator + pf.getFilePath();
        File f = new File(filePath);
        if (!f.exists()) {
            model.addAttribute("resultId", "01000304");
            model.addAttribute("resultType", "暂未上传论文附件，请联系管理员！");
            return "res/xnres/checkGraduationFile";
        }
        return "res/xnres/checkGraduationFile";
    }

    @RequestMapping(value = {"/downFile"}, method = {RequestMethod.POST,RequestMethod.GET})
    public synchronized void downFile(String userFlow, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {

        byte[] data = null;
        long dataLength = 0;
        String baseDirs = appBiz.getCfgByCode("upload_base_dir");
        PubFile search=new PubFile();
        search.setCreateUserFlow(userFlow);
        search.setProductType("GRADUATION_FILE");
        PubFile pf=fileBiz.readDocGraduationFile(search);
        if(pf!=null)
        {
            String filePath =baseDirs+ File.separator + pf.getFilePath();
            File f = new File(filePath);
        /*文件是否存在*/
            File downLoadFile = new File(filePath);
		/*文件是否存在*/
            if (downLoadFile.exists()) {
                InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
                data = new byte[fis.available()];
                dataLength = downLoadFile.length();
                fis.read(data);
                fis.close();
            }
        }
        String fileName = pf.getFileName();
        fileName = new String(fileName.getBytes("gbk"),"ISO8859-1" );
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
}

