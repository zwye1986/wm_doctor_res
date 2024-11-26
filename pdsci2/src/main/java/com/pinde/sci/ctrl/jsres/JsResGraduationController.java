package com.pinde.sci.ctrl.jsres;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResGraduationBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResScoreBiz;
import com.pinde.sci.biz.res.ResPaperBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.jsres.JsResDocTypeEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.model.jsres.DoctorExamStatisticsExt;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/jsres/graduation")
public class JsResGraduationController extends GeneralController {
    int overCountGraduation = 0;
    int overCountDoctor = 0;

    @Autowired
    private IJsResGraduationBiz graduationBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private ResPaperBiz paperBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;

    @RequestMapping(value = "/queryMain")
    public String queryMain(Model model) {

        return "jsres/hospital/graduation/queryMain";
    }

    /**
     * 下载远程文件并保存到本地
     */

    @RequestMapping(value = "/downloadFile")
    public void downloadFile(String url, HttpServletResponse response) {
        URL urlfile = null;
        HttpURLConnection httpUrl = null;
        BufferedInputStream bis = null;
        try {
            urlfile = new URL(url);
            httpUrl = (HttpURLConnection) urlfile.openConnection();
            httpUrl.connect();
            bis = new BufferedInputStream(httpUrl.getInputStream());
            byte[] data = new byte[bis.available()];
            int len = 2048;
            byte[] b = new byte[len];
            String fileName = "年度考核试卷" + url.substring(url.lastIndexOf("."));
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"), "ISO8859-1") + "\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            while ((len = bis.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            bis.close();
            httpUrl.disconnect();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("无法下载相应试卷");
        } finally {
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @RequestMapping(value = "/list")
    public String list(Model model, Integer currentPage, HttpServletRequest request,
                       String trainingTypeId, String trainingSpeId,
                       String sessionNumber, String userName, String datas[]) {
        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        } else {
            datas = new String[JsResDocTypeEnum.values().length];
            int i = 0;
            for (JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
                datas[i++] = e.getId();
            }
        }
        SysUser sysuser = GlobalContext.getCurrentUser();
        //查询条件
        Map<String, Object> param = new HashMap<>();
        param.put("docTypeList", docTypeList);
        param.put("trainingTypeId", trainingTypeId);
        param.put("trainingSpeId", trainingSpeId);
        param.put("sessionNumber", sessionNumber);
        param.put("userName", userName);
        param.put("orgFlow", sysuser.getOrgFlow());

        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, Object>> list = graduationBiz.userList(param);
        model.addAttribute("list", list);
        if (list != null && list.size() > 0) {
            Map<String, Object> map = new HashMap<>();

            for (Map<String, Object> user : list) {
                int qN = Integer.parseInt(user.get("qualifiedNum").toString());
                int tN = Integer.parseInt(user.get("totalNum").toString());
                DecimalFormat df = new DecimalFormat("0");
                map.put((String) user.get("doctorFlow"), df.format((float) qN / tN * 100) + "%");
            }
            model.addAttribute("map", map);
        }
        return "jsres/hospital/graduation/list";
    }

    @RequestMapping(value = "searchScore")
    public String searchScore(String doctorFlow, String userName, String speName, Model model,
                              HttpServletRequest request, Integer currentPage) {
        return "jsres/hospital/graduation/graduationExamList";
    }

    @RequestMapping(value = "searchScore2")
    public String searchScore2(String doctorFlow, String userName, String speName, Model model,
                               HttpServletRequest request, Integer currentPage) {
        model.addAttribute("doctorName", userName);
        model.addAttribute("speName", speName);
        SysUser user = userBiz.readSysUser(doctorFlow);
        ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
        model.addAttribute("user", user);
        model.addAttribute("doctor", doctor);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, Object>> graduationExamList = graduationBiz.findExamBydoctorFlow(doctorFlow);
        model.addAttribute("graduationExamList", graduationExamList);
        return "jsres/hospital/graduation/examsults";
    }

    @RequestMapping(value = "/exportInfo")
    public void exportInfo(Model model, HttpServletRequest request, HttpServletResponse response,
                           String trainingTypeId, String trainingSpeId,
                           String sessionNumber, String userName, String datas[]) throws IOException {
        SysUser sysuser = GlobalContext.getCurrentUser();
        //查询条件
        Map<String, Object> param = new HashMap<>();
        param.put("trainingTypeId", trainingTypeId);
        param.put("trainingSpeId", trainingSpeId);
        param.put("sessionNumber", sessionNumber);
        param.put("userName", userName);
        param.put("orgFlow", sysuser.getOrgFlow());

        List<String> years = new ArrayList<>();

        List<String> docTypeList = new ArrayList<String>();
        model.addAttribute("datas", datas);
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        param.put("docTypeList", docTypeList);
        List<Map<String, Object>> list = graduationBiz.userList(param);
        model.addAttribute("list", list);

        graduationBiz.exportInfo(list, response);
    }

    @RequestMapping(value = "/toJYTest")
    public String toJYTest(String doctorFlow, Model model, HttpServletRequest request, Integer currentPage) {
        String errorPage = "jsres/doctor/graduation/graduationExamList";
        SysUser sysUser = userBiz.readSysUser(doctorFlow);
        if (sysUser == null) {
            model.addAttribute("errorMeg", "无个人信息，无法参加考试！");
            return errorPage;
        }
        //用户的医师信息
        ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
        if (doctor == null) {
            model.addAttribute("errorMeg", "无医师信息，无法参加考试！");
            return errorPage;
        }
        if (StringUtil.isBlank(doctor.getTrainingSpeId())) {
            model.addAttribute("errorMeg", "医师无培训专业信息，无法参加考试！");
            return errorPage;
        }
        //试卷id
        String ExamSoluID = "0";
        if (doctor != null) {
            //专业
            String speId = doctor.getTrainingSpeId();
            ResPaper paper = paperBiz.getPaperBySpeId(speId);

            if (paper != null) {
                ExamSoluID = paper.getPaperFlow();
            }
            if ("0".equals(ExamSoluID)) {
                model.addAttribute("errorMeg", "该专业下暂无试卷信息！");
                return errorPage;
            }
            model.addAttribute("paperName", paper.getPaperName());
        }
        model.addAttribute("sysUser", sysUser);
        return errorPage;
    }

    @RequestMapping(value = "/showErrorInfo")
    public String showErrorInfo(Model model, String resultId) {
        String errorPage = "jsres/doctor/graduation/graduationExamList";

        if (!StringUtil.isNotBlank(resultId)) {
            model.addAttribute("errorMeg", "当前考试试卷ID为空！");
            return errorPage;
        }

        //考试地址
        String testUrl = InitConfig.getSysCfg("res_after_wrong_exam_url_cfg");
        if (!StringUtil.isNotBlank(testUrl)) {
            model.addAttribute("errorMeg", "请联系管理员维护错题查看地址！");
            return errorPage;
        }

        GraduationExamResults results = graduationBiz.getResultByFlow(resultId);

        if (results == null) {
            model.addAttribute("errorMeg", "当前考试记录信息获取失败！");
            return errorPage;
        }

        //当前用户
//        SysUser user = userBiz.readSysUser(process.getUserFlow());
        model.addAttribute("RequestType", "pc");
//        model.addAttribute("ProcessFlow",process.getProcessFlow());
        model.addAttribute("SoluID", results.getSoluId());
        model.addAttribute("ResultID", resultId);
        return "redirect:" + testUrl;
    }

    /**
     * @Author xieyh
     * @Description 根据城市ID查询对应城市的主基地列表
     * @Date  2024-06-14
     */
    @RequestMapping("/getOrgListByCityId")
    @ResponseBody
    public List<SysOrg> getOrgListByCityId(String cityId) {
        if (StringUtil.isNotBlank(cityId)) {
            return orgBiz.searchOrgListByCityId(cityId);
        } else {
            List<String> orgLevels = new ArrayList<>();
            orgLevels.add(OrgLevelEnum.CountryOrg.getId());
            List<SysOrg> orgList = orgBiz.searchSysOrgOrder(orgLevels);
            orgList.sort(Comparator.comparingInt(o -> o.getOrgCode().hashCode()));
            return orgList;
        }
    }

    /**
     * @Author xieyh
     * @Description 省厅结业数据报表
     * @Date  2024-06-13
     */
    @RequestMapping("/finishStudyReport")
    public String finishStudyReport(Model model, String catSpeId, String graduationYear, String sessionNumber, String cityId, String orgFlow, String isJointOrgIn, String speId, String[] doctorType, String scoreType, String sortBy, String isLoad, HttpServletRequest request) {
        if (StringUtil.isBlank(graduationYear)) {
            if (DateUtil.getCurrDate().compareTo(DateUtil.getYear() + "-05-31") > 0) {
                graduationYear = DateUtil.getYear();
            } else {
                graduationYear = String.valueOf(Integer.parseInt(DateUtil.getYear()) - 1);
            }
        }
        if (StringUtil.isBlank(isJointOrgIn)) {
            isJointOrgIn = GlobalConstant.FLAG_Y;
        }
        model.addAttribute("graduationYear", graduationYear);

        Map<String, String[]> parameterMap = request.getParameterMap();
        doctorType = parameterMap.get("doctorType[]");

        Map<String,Object> params = new HashMap<>();
        List<String> docTypeList = new ArrayList<>();
        if(doctorType != null && doctorType.length > 0){
            docTypeList.addAll(Arrays.asList(doctorType));
        }else{
            for(JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
            }
        }

        List<String> sessionNumberList = new ArrayList<>();
        if (StringUtil.isNotBlank(sessionNumber)) {
            sessionNumberList.addAll(Arrays.asList(sessionNumber.split(",")));
        }

        List<SysOrg> orgList = new ArrayList<>();
        if (StringUtil.isNotBlank(cityId)) {
            orgList = getOrgListByCityId(cityId);
        } else {
            List<String> orgLevels = new ArrayList<>();
            orgLevels.add(OrgLevelEnum.CountryOrg.getId());
            // 查询基地/医院
            orgList = orgBiz.searchSysOrgOrder(orgLevels);
        }
        orgList.sort(Comparator.comparingInt(o -> o.getOrgCode().hashCode()));
        model.addAttribute("orgList",orgList);

        List<SysOrg> searchOrgList = new ArrayList<>();
        if (StringUtil.isNotBlank(orgFlow)) {
            searchOrgList.add(orgBiz.readSysOrg(orgFlow));
        } else {
            searchOrgList = orgList;
        }
        model.addAttribute("searchOrgList",searchOrgList);

        params.put("docTypeList",docTypeList);
        params.put("speId",speId);

        // 对数据进行计算，初始化值
        // 住院医师
        HashMap<String, Object> doctorTrainingMap = new HashMap<>();
        initGeneralData(doctorTrainingMap);
        // 在校专硕
        HashMap<String, Object> graduateMap = new HashMap<>();
        initGeneralData(graduateMap);
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> footMap = new HashMap<>();
        int overGraduation = 0;
        int overDoctor = 0;
        for (SysOrg sysOrg : searchOrgList) {
            String finalGraduationYear = graduationYear;
            String searchOrgFlow = sysOrg.getOrgFlow() == null ? "" : sysOrg.getOrgFlow();
            params.put("orgFlow",searchOrgFlow);
            params.put("sessionNumbers",sessionNumberList);
            params.put("graduationYear", finalGraduationYear);
            params.put("scoreType",scoreType);
            params.put("catSpeId",catSpeId);
            List<Map<String,Object>> countryOrgTotalDoctorList = graduationBiz.searchDoctorRecruit(params);
            if (StringUtil.isNotBlank(isJointOrgIn) && isJointOrgIn.equals(GlobalConstant.FLAG_Y)) {
                countryOrgTotalDoctorList.addAll(graduationBiz.searchJointOrgDoctorRecruit(params));
            }
            // 查询人员名单并处理数据
            Map<String,Object> orgConditionMap = new HashMap<>();
            orgConditionMap.put("orgCode", sysOrg.getOrgCode());
            orgConditionMap.put("orgName", sysOrg.getOrgName());
            orgConditionMap.put("orgCityId", sysOrg.getOrgCityId());
            orgConditionMap.put("orgCityName", sysOrg.getOrgCityName());
            overCountGraduation = 0;
            overCountDoctor = 0;
            for (Map<String, Object> doctor : countryOrgTotalDoctorList) {
                countForGraduationReport(scoreType, finalGraduationYear, doctorTrainingMap, graduateMap, orgConditionMap, footMap, doctor);
            }
            // 首考通过率计算
            calculateFirstExamPercent(orgConditionMap);
            // 补考通过率计算
            calculateSecondExamPercent(orgConditionMap);
            // 综合通过率计算
            calculateExamPercent(orgConditionMap);
            overGraduation += overCountGraduation;
            overDoctor += overCountDoctor;
            if (overCountGraduation > 0) {
                graduateMap.put("all", (Integer) graduateMap.get("all") - overCountGraduation / 2);
                graduateMap.put("realExam", (Integer) graduateMap.get("realExam") - overCountGraduation / 2);
                graduateMap.put("secondExam", (Integer) graduateMap.get("secondExam") - overCountGraduation / 2);
                orgConditionMap.put("all", (Integer) orgConditionMap.get("all") - overCountGraduation / 2);
                orgConditionMap.put("realExamTheory", (Integer) orgConditionMap.get("realExamTheory") - overCountGraduation / 2);
                orgConditionMap.put("realExamSkill", (Integer) orgConditionMap.get("realExamSkill") - overCountGraduation / 2);
                orgConditionMap.put("realExamAll", (Integer) orgConditionMap.get("realExamAll") - overCountGraduation / 2);
                orgConditionMap.put("realExamGraduationTheorySecond", (Integer) orgConditionMap.get("realExamGraduationTheorySecond") - overCountGraduation / 2);
                orgConditionMap.put("realExamGraduationSkillSecond", (Integer) orgConditionMap.get("realExamGraduationSkillSecond") - overCountGraduation / 2);
                orgConditionMap.put("secondExam", (Integer) orgConditionMap.get("secondExam") - overCountGraduation / 2);
            }
            if (overCountDoctor > 0) {
                doctorTrainingMap.put("all", (Integer) doctorTrainingMap.get("all") - overCountDoctor / 2);
                doctorTrainingMap.put("realExam", (Integer) doctorTrainingMap.get("realExam") - overCountDoctor / 2);
                doctorTrainingMap.put("secondExam", (Integer) doctorTrainingMap.get("secondExam") - overCountDoctor / 2);
                orgConditionMap.put("all", (Integer) orgConditionMap.get("all") - overCountDoctor / 2);
                orgConditionMap.put("realExamTheory", (Integer) orgConditionMap.get("realExamTheory") - overCountDoctor / 2);
                orgConditionMap.put("realExamSkill", (Integer) orgConditionMap.get("realExamSkill") - overCountDoctor / 2);
                orgConditionMap.put("realExamAll", (Integer) orgConditionMap.get("realExamAll") - overCountDoctor / 2);
                orgConditionMap.put("realExamDoctorTheorySecond", (Integer) orgConditionMap.get("realExamDoctorTheorySecond") - overCountDoctor / 2);
                orgConditionMap.put("realExamDoctorSkillSecond", (Integer) orgConditionMap.get("realExamDoctorSkillSecond") - overCountDoctor / 2);
                orgConditionMap.put("secondExam", (Integer) orgConditionMap.get("secondExam") - overCountDoctor / 2);
            }

            if (StringUtil.isNotBlank(scoreType) && scoreType.equals("Theory")) {
                orgConditionMap.put("Skill", 0);
            }
            if (StringUtil.isNotBlank(scoreType) && scoreType.equals("Skill")) {
                orgConditionMap.put("Theory", 0);
            }
            calculateMissExamData(orgConditionMap);
            resultList.add(orgConditionMap);
        }

        if ("firstExamPassPercent".equals(sortBy)) {
            resultList.sort((o1, o2) -> o2.get("firstPassPercent").hashCode() - o1.get("firstPassPercent").hashCode());
        }
        if ("secondExamPassPercent".equals(sortBy)) {
            resultList.sort((o1, o2) -> o2.get("secondPassPercent").hashCode() - o1.get("secondPassPercent").hashCode());
        }
        if ("examPassPercent".equals(sortBy)) {
            resultList.sort((o1, o2) -> o2.get("PassPercent").hashCode() - o1.get("PassPercent").hashCode());
        }

        calculateTotalPercent(graduateMap, doctorTrainingMap);
        calculateFootPercent(footMap);
        if (overGraduation > 0) {
            footMap.put("all", (Integer) footMap.get("all") - overGraduation / 2);
            footMap.put("realExamTheory", (Integer) footMap.get("realExamTheory") - overGraduation / 2);
            footMap.put("realExamSkill", (Integer) footMap.get("realExamSkill") - overGraduation / 2);
            footMap.put("realExamAll", (Integer) footMap.get("realExamAll") - overGraduation / 2);
            footMap.put("realExamGraduationTheorySecond", (Integer) footMap.get("realExamGraduationTheorySecond") - overGraduation / 2);
            footMap.put("realExamGraduationSkillSecond", (Integer) footMap.get("realExamGraduationSkillSecond") - overGraduation / 2);
            footMap.put("secondExam", (Integer) footMap.get("secondExam") - overGraduation / 2);
        }
        if (overDoctor > 0) {
            footMap.put("all", (Integer) footMap.get("all") - overDoctor / 2);
            footMap.put("realExamTheory", (Integer) footMap.get("realExamTheory") - overDoctor / 2);
            footMap.put("realExamSkill", (Integer) footMap.get("realExamSkill") - overDoctor / 2);
            footMap.put("realExamAll", (Integer) footMap.get("realExamAll") - overDoctor / 2);
            footMap.put("realExamDoctorTheorySecond", (Integer) footMap.get("realExamDoctorTheorySecond") - overDoctor / 2);
            footMap.put("realExamDoctorSkillSecond", (Integer) footMap.get("realExamDoctorSkillSecond") - overDoctor / 2);
            footMap.put("secondExam", (Integer) footMap.get("secondExam") - overDoctor / 2);
        }
        if (StringUtil.isNotBlank(scoreType) && scoreType.equals("Theory")) {
            footMap.put("Skill", 0);
        }
        if (StringUtil.isNotBlank(scoreType) && scoreType.equals("Skill")) {
            footMap.put("Theory", 0);
        }
        calculateMissExamData(footMap);
        model.addAttribute("resultList", resultList);
        model.addAttribute("graduateMap", graduateMap);
        model.addAttribute("doctorTrainingMap", doctorTrainingMap);
        model.addAttribute("footMap", footMap);

        if (StringUtil.isNotBlank(isLoad)) {
            return "jsres/global/finishStudyStatisticsLoad";
        } else {
            return "jsres/global/finishStudyStatistics";
        }
    }

    private void calculateMissExamData(Map<String, Object> orgConditionMap) {
        int theory = 0;
        int skill = 0;
        int all = 0;
        int realExamAll = 0;
        int realExamTheory = 0;
        int realExamSkill = 0;
        if (orgConditionMap.containsKey("Theory")) {
            theory = (Integer) orgConditionMap.get("Theory");
        }
        if (orgConditionMap.containsKey("Skill")) {
            skill = (Integer) orgConditionMap.get("Skill");
        }
        if (orgConditionMap.containsKey("all")) {
            all = (Integer) orgConditionMap.get("all");
        }
        if (orgConditionMap.containsKey("realExamAll")) {
            realExamAll = (Integer) orgConditionMap.get("realExamAll");
        }
        if (orgConditionMap.containsKey("realExamTheory")) {
            realExamTheory = (Integer) orgConditionMap.get("realExamTheory");
        }
        if (orgConditionMap.containsKey("realExamSkill")) {
            realExamSkill = (Integer) orgConditionMap.get("realExamSkill");
        }
        orgConditionMap.put("missTheory", theory - realExamTheory);
        orgConditionMap.put("missSkill", skill - realExamSkill);
        orgConditionMap.put("missAll", all - realExamAll);
    }

    private void calculateFootPercent(Map<String, Object> footMap) {
        calculateExamPercent(footMap);
        calculateFirstExamPercent(footMap);
        calculateSecondExamPercent(footMap);
    }

    private void calculateTotalPercent(HashMap<String, Object> graduateMap, HashMap<String, Object> doctorTrainingMap) {
        if (graduateMap.containsKey("realAll")) {
            BigDecimal realAll = new BigDecimal((Integer) graduateMap.get("realAll"));
            Integer pass = 0;
            if (graduateMap.containsKey("passAll")) {
                pass = (Integer) graduateMap.get("passAll");
            }
            BigDecimal passAll = new BigDecimal(pass);
            BigDecimal PassPercent = passAll.divide(realAll, 4, BigDecimal.ROUND_HALF_UP);
            graduateMap.put("PassPercent", PassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
        } else {
            graduateMap.put("PassPercent", 0);
        }
        if (doctorTrainingMap.containsKey("realAll")) {
            BigDecimal realAll = new BigDecimal((Integer) doctorTrainingMap.get("realAll"));
            Integer pass = 0;
            if (doctorTrainingMap.containsKey("passAll")) {
                pass = (Integer) doctorTrainingMap.get("passAll");
            }
            BigDecimal passAll = new BigDecimal(pass);
            BigDecimal PassPercent = passAll.divide(realAll, 4, BigDecimal.ROUND_HALF_UP);
            doctorTrainingMap.put("PassPercent", PassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
        } else {
            doctorTrainingMap.put("PassPercent", 0);
        }

        if (graduateMap.containsKey("realExamSecondExam")) {
            BigDecimal realExamSecondExam = new BigDecimal((Integer) graduateMap.get("realExamSecondExam"));
            Integer pass = 0;
            if (graduateMap.containsKey("realExamSecondExamPass")) {
                pass = (Integer) graduateMap.get("realExamSecondExamPass");
            }
            BigDecimal realExamSecondExamPass = new BigDecimal(pass);
            BigDecimal secondPassPercent = realExamSecondExamPass.divide(realExamSecondExam, 4, BigDecimal.ROUND_HALF_UP);
            graduateMap.put("secondPassPercent", secondPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
        } else {
            graduateMap.put("secondPassPercent", 0);
        }
        if (doctorTrainingMap.containsKey("realExamSecondExam")) {
            BigDecimal realExamSecondExam = new BigDecimal((Integer) doctorTrainingMap.get("realExamSecondExam"));
            Integer pass = 0;
            if (doctorTrainingMap.containsKey("realExamSecondExamPass")) {
                pass = (Integer) doctorTrainingMap.get("realExamSecondExamPass");
            }
            BigDecimal realExamSecondExamPass = new BigDecimal(pass);
            BigDecimal secondPassPercent = realExamSecondExamPass.divide(realExamSecondExam, 4, BigDecimal.ROUND_HALF_UP);
            doctorTrainingMap.put("secondPassPercent", secondPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
        } else {
            doctorTrainingMap.put("secondPassPercent", 0);
        }

        if (graduateMap.containsKey("realExamFirstExam")) {
            BigDecimal realExamFirstExam = new BigDecimal((Integer) graduateMap.get("realExamFirstExam"));
            Integer pass = 0;
            if (graduateMap.containsKey("realExamFirstExamPass")) {
                pass = (Integer) graduateMap.get("realExamFirstExamPass");
            }
            BigDecimal realExamFirstExamPass = new BigDecimal(pass);
            BigDecimal firstPassPercent = realExamFirstExamPass.divide(realExamFirstExam, 4, BigDecimal.ROUND_HALF_UP);
            graduateMap.put("firstPassPercent", firstPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
        } else {
            graduateMap.put("firstPassPercent", 0);
        }
        if (doctorTrainingMap.containsKey("realExamFirstExam")) {
            BigDecimal realExamFirstExam = new BigDecimal((Integer) doctorTrainingMap.get("realExamFirstExam"));
            Integer pass = 0;
            if (doctorTrainingMap.containsKey("realExamFirstExamPass")) {
                pass = (Integer) doctorTrainingMap.get("realExamFirstExamPass");
            }
            BigDecimal realExamFirstExamPass = new BigDecimal(pass);
            BigDecimal firstPassPercent = realExamFirstExamPass.divide(realExamFirstExam, 4, BigDecimal.ROUND_HALF_UP);
            doctorTrainingMap.put("firstPassPercent", firstPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
        } else {
            doctorTrainingMap.put("firstPassPercent", 0);
        }
    }

    private void calculateExamPercent(Map<String, Object> orgConditionMap) {
        // 住院医师理论综合
        if (orgConditionMap.containsKey("realExamDoctorTheory")) {
            BigDecimal realExamDoctorTheory = new BigDecimal((Integer) orgConditionMap.get("realExamDoctorTheory"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passDoctorTheory")) {
                pass = (Integer) orgConditionMap.get("passDoctorTheory");
            }
            BigDecimal passDoctorTheory = new BigDecimal(pass);
            BigDecimal doctorTheoryPassPercent = passDoctorTheory.divide(realExamDoctorTheory, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(doctorTheoryPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("doctorTheoryPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("doctorTheoryPassPercent", "0");
        }
        // 住院医师技能综合
        if (orgConditionMap.containsKey("realExamDoctorSkill")) {
            BigDecimal realExamDoctorTheory = new BigDecimal((Integer) orgConditionMap.get("realExamDoctorSkill"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passDoctorSkill")) {
                pass = (Integer) orgConditionMap.get("passDoctorSkill");
            }
            BigDecimal passDoctorSkill = new BigDecimal(pass);
            BigDecimal doctorSkillPassPercent = passDoctorSkill.divide(realExamDoctorTheory, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(doctorSkillPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("doctorSkillPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("doctorSkillPassPercent", "0");
        }
        // 在校专硕理论综合
        if (orgConditionMap.containsKey("realExamGraduationTheory")) {
            BigDecimal realExamDoctorTheory = new BigDecimal((Integer) orgConditionMap.get("realExamGraduationTheory"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passGraduationTheory")) {
                pass = (Integer) orgConditionMap.get("passGraduationTheory");
            }
            BigDecimal passGraduationTheory = new BigDecimal(pass);
            BigDecimal graduationTheoryPassPercent = passGraduationTheory.divide(realExamDoctorTheory, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(graduationTheoryPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("graduationTheoryPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("graduationTheoryPassPercent", "0");
        }
        // 在校专硕技能综合
        if (orgConditionMap.containsKey("realExamGraduationSkill")) {
            BigDecimal realExamDoctorSkill = new BigDecimal((Integer) orgConditionMap.get("realExamGraduationSkill"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passGraduationSkill")) {
                pass = (Integer) orgConditionMap.get("passGraduationSkill");
            }
            BigDecimal passDoctorSkill = new BigDecimal(pass);
            BigDecimal graduationSkillPassPercent = passDoctorSkill.divide(realExamDoctorSkill, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(graduationSkillPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("graduationSkillPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("graduationSkillPassPercent", "0");
        }
        // 合计综合
        if (orgConditionMap.containsKey("realAll")) {
            BigDecimal realAll = new BigDecimal((Integer) orgConditionMap.get("realAll"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passAll")) {
                pass = (Integer) orgConditionMap.get("passAll");
            }
            BigDecimal passAll = new BigDecimal(pass);
            BigDecimal PassPercent = passAll.divide(realAll, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(PassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("PassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("PassPercent", "0");
        }
    }

    private void calculateSecondExamPercent(Map<String, Object> orgConditionMap) {
        // 住院医师理论补考
        if (orgConditionMap.containsKey("realExamDoctorTheorySecond")) {
            BigDecimal realExamDoctorTheorySecond = new BigDecimal((Integer) orgConditionMap.get("realExamDoctorTheorySecond"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passDoctorTheorySecond")) {
                pass = (Integer) orgConditionMap.get("passDoctorTheorySecond");
            }
            BigDecimal passDoctorTheorySecond = new BigDecimal(pass);
            BigDecimal doctorTheorySecondPassPercent = passDoctorTheorySecond.divide(realExamDoctorTheorySecond, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(doctorTheorySecondPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("doctorTheorySecondPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("doctorTheorySecondPassPercent", "0");
        }
        // 住院医师技能补考
        if (orgConditionMap.containsKey("realExamDoctorSkillSecond")) {
            BigDecimal realExamDoctorSkillSecond = new BigDecimal((Integer) orgConditionMap.get("realExamDoctorSkillSecond"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passDoctorSkillSecond")) {
                pass = (Integer) orgConditionMap.get("passDoctorSkillSecond");
            }
            BigDecimal passDoctorSkillSecond = new BigDecimal(pass);
            BigDecimal doctorSkillSecondPassPercent = passDoctorSkillSecond.divide(realExamDoctorSkillSecond, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(doctorSkillSecondPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("doctorSkillSecondPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("doctorSkillSecondPassPercent", "0");
        }
        // 在校专硕理论补考
        if (orgConditionMap.containsKey("realExamGraduationTheorySecond")) {
            BigDecimal realExamGraduationTheorySecond = new BigDecimal((Integer) orgConditionMap.get("realExamGraduationTheorySecond"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passGraduationTheorySecond")) {
                pass = (Integer) orgConditionMap.get("passGraduationTheorySecond");
            }
            BigDecimal passGraduationTheorySecond = new BigDecimal(pass);
            BigDecimal graduationTheorySecondPassPercent = passGraduationTheorySecond.divide(realExamGraduationTheorySecond, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(graduationTheorySecondPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("graduationTheorySecondPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("graduationTheorySecondPassPercent", "0");
        }
        // 在校专硕技能补考
        if (orgConditionMap.containsKey("realExamGraduationSkillSecond")) {
            BigDecimal realExamGraduationSkillSecond = new BigDecimal((Integer) orgConditionMap.get("realExamGraduationSkillSecond"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passGraduationSkillSecond")) {
                pass = (Integer) orgConditionMap.get("passGraduationSkillSecond");
            }
            BigDecimal passGraduationSkillSecond = new BigDecimal(pass);
            BigDecimal graduationSkillSecondPassPercent = passGraduationSkillSecond.divide(realExamGraduationSkillSecond, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(graduationSkillSecondPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("graduationSkillSecondPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("graduationSkillSecondPassPercent", "0");
        }
        // 合计补考
        if (orgConditionMap.containsKey("realExamSecondExam")) {
            BigDecimal realExamSecondExam = new BigDecimal((Integer) orgConditionMap.get("realExamSecondExam"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("realExamSecondExamPass")) {
                pass = (Integer) orgConditionMap.get("realExamSecondExamPass");
            }
            BigDecimal realExamSecondExamPass = new BigDecimal(pass);
            BigDecimal secondPassPercent = realExamSecondExamPass.divide(realExamSecondExam, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(secondPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("secondPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("secondPassPercent", "0");
        }
    }

    private void calculateFirstExamPercent(Map<String, Object> orgConditionMap) {
        // 住院医师理论首考
        if (orgConditionMap.containsKey("realExamDoctorTheoryFirst")) {
            BigDecimal realExamDoctorTheoryFirst = new BigDecimal((Integer) orgConditionMap.get("realExamDoctorTheoryFirst"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passDoctorTheoryFirst")) {
                pass = (Integer) orgConditionMap.get("passDoctorTheoryFirst");
            }
            BigDecimal passDoctorTheoryFirst = new BigDecimal(pass);
            BigDecimal doctorTheoryFirstPassPercent = passDoctorTheoryFirst.divide(realExamDoctorTheoryFirst, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(doctorTheoryFirstPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("doctorTheoryFirstPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("doctorTheoryFirstPassPercent", "0");
        }
        // 住院医师技能首考
        if (orgConditionMap.containsKey("realExamDoctorSkillFirst")) {
            BigDecimal realExamDoctorSkillFirst = new BigDecimal((Integer) orgConditionMap.get("realExamDoctorSkillFirst"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passDoctorSkillFirst")) {
                pass = (Integer) orgConditionMap.get("passDoctorSkillFirst");
            }
            BigDecimal passDoctorSkillFirst = new BigDecimal(pass);
            BigDecimal doctorSkillFirstPassPercent = passDoctorSkillFirst.divide(realExamDoctorSkillFirst, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(doctorSkillFirstPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("doctorSkillFirstPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("doctorSkillFirstPassPercent", "0");
        }
        // 在校专硕理论首考
        if (orgConditionMap.containsKey("realExamGraduationTheoryFirst")) {
            BigDecimal realExamGraduationTheoryFirst = new BigDecimal((Integer) orgConditionMap.get("realExamGraduationTheoryFirst"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passGraduationTheoryFirst")) {
                pass = (Integer) orgConditionMap.get("passGraduationTheoryFirst");
            }
            BigDecimal passGraduationTheoryFirst = new BigDecimal(pass);
            BigDecimal graduationTheoryFirstPassPercent = passGraduationTheoryFirst.divide(realExamGraduationTheoryFirst, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(graduationTheoryFirstPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("graduationTheoryFirstPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("graduationTheoryFirstPassPercent", "0");
        }
        // 在校专硕技能首考
        if (orgConditionMap.containsKey("realExamGraduationSkillFirst")) {
            BigDecimal realExamGraduationSkillFirst = new BigDecimal((Integer) orgConditionMap.get("realExamGraduationSkillFirst"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("passGraduationSkillFirst")) {
                pass = (Integer) orgConditionMap.get("passGraduationSkillFirst");
            }
            BigDecimal passGraduationSkillFirst = new BigDecimal(pass);
            BigDecimal graduationSkillFirstPassPercent = passGraduationSkillFirst.divide(realExamGraduationSkillFirst, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(graduationSkillFirstPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("graduationSkillFirstPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("graduationSkillFirstPassPercent", "0");
        }
        // 合计首考
        if (orgConditionMap.containsKey("realExamFirstExam")) {
            BigDecimal realExamFirstExam = new BigDecimal((Integer) orgConditionMap.get("realExamFirstExam"));
            Integer pass = 0;
            if (orgConditionMap.containsKey("realExamFirstExamPass")) {
                pass = (Integer) orgConditionMap.get("realExamFirstExamPass");
            }
            BigDecimal realExamFirstExamPass = new BigDecimal(pass);
            BigDecimal firstPassPercent = realExamFirstExamPass.divide(realExamFirstExam, 4, BigDecimal.ROUND_HALF_UP);
            String s = String.valueOf(firstPassPercent.multiply(new BigDecimal(100)).divide(new BigDecimal(1), 2, BigDecimal.ROUND_HALF_UP));
            orgConditionMap.put("firstPassPercent", s.equals("0.00") ? "0" : s);
        } else {
            orgConditionMap.put("firstPassPercent", "0");
        }
    }

    private void countForGraduationReport(String scoreType, String graduationYear, HashMap<String, Object> doctorTrainingMap, HashMap<String, Object> graduateMap, Map<String, Object> orgConditionMap, Map<String, Object> footMap, Map<String, Object> doctor) {
        ResScore theoryScore = null;
        ResScore skillScore = null;
        if ("Theory".equals(scoreType)) {
            theoryScore = resScoreBiz.getScoreByDocFlowAndYearAndType((String) doctor.get("doctorFlow"), graduationYear, "TheoryScore");
        } else if ("Skill".equals(scoreType)) {
            skillScore = resScoreBiz.getScoreByDocFlowAndYearAndType((String) doctor.get("doctorFlow"), graduationYear, "SkillScore");
        } else {
            theoryScore = resScoreBiz.getScoreByDocFlowAndYearAndType((String) doctor.get("doctorFlow"), graduationYear, "TheoryScore");
            skillScore = resScoreBiz.getScoreByDocFlowAndYearAndType((String) doctor.get("doctorFlow"), graduationYear, "SkillScore");
        }
        if (JsResDocTypeEnum.Graduate.getId().equals(doctor.get("doctorTypeId"))) {
            graduateMap.put("all", (Integer) graduateMap.get("all") + 1);
            if (orgConditionMap.containsKey("all")) {
                orgConditionMap.put("all", (Integer) orgConditionMap.get("all") + 1);
            } else {
                orgConditionMap.put("all", 1);
            }
            if (footMap.containsKey("all")) {
                footMap.put("all", (Integer) footMap.get("all") + 1);
            } else {
                footMap.put("all", 1);
            }
            if (StringUtil.isNotBlank((String) doctor.get("signupFlow")) && theoryScore != null && skillScore != null) {
                overCountGraduation ++;
            }
            if (theoryScore != null || skillScore != null) {
                graduateMap.put("realExam", (Integer) graduateMap.get("realExam") + 1);
                if (orgConditionMap.containsKey("realExamAll")) {
                    orgConditionMap.put("realExamAll", (Integer) orgConditionMap.get("realExamAll") + 1);
                } else {
                    orgConditionMap.put("realExamAll", 1);
                }
                if (footMap.containsKey("realExamAll")) {
                    footMap.put("realExamAll", (Integer) footMap.get("realExamAll") + 1);
                } else {
                    footMap.put("realExamAll", 1);
                }
                if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                    graduateMap.put("firstExam", (Integer) graduateMap.get("firstExam") + 1);
                    if (orgConditionMap.containsKey("firstExam")) {
                        orgConditionMap.put("firstExam", (Integer) orgConditionMap.get("firstExam") + 1);
                    } else {
                        orgConditionMap.put("firstExam", 1);
                    }
                    if (footMap.containsKey("firstExam")) {
                        footMap.put("firstExam", (Integer) footMap.get("firstExam") + 1);
                    } else {
                        footMap.put("firstExam", 1);
                    }
                }
                if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                    graduateMap.put("secondExam", (Integer) graduateMap.get("secondExam") + 1);
                    if (orgConditionMap.containsKey("secondExam")) {
                        orgConditionMap.put("secondExam", (Integer) orgConditionMap.get("secondExam") + 1);
                    } else {
                        orgConditionMap.put("secondExam", 1);
                    }
                    if (footMap.containsKey("secondExam")) {
                        footMap.put("secondExam", (Integer) footMap.get("secondExam") + 1);
                    } else {
                        footMap.put("secondExam", 1);
                    }
                }
                if (theoryScore != null) {
                    if (orgConditionMap.containsKey("realAll")) {
                        orgConditionMap.put("realAll", (Integer) orgConditionMap.get("realAll") + 1);
                    } else {
                        orgConditionMap.put("realAll", 1);
                    }
                    if (footMap.containsKey("realAll")) {
                        footMap.put("realAll", (Integer) footMap.get("realAll") + 1);
                    } else {
                        footMap.put("realAll", 1);
                    }
                    if (graduateMap.containsKey("realAll")) {
                        graduateMap.put("realAll", (Integer) graduateMap.get("realAll") + 1);
                    } else {
                        graduateMap.put("realAll", 1);
                    }
                    if (orgConditionMap.containsKey("realExamGraduationTheory")) {
                        orgConditionMap.put("realExamGraduationTheory", (Integer) orgConditionMap.get("realExamGraduationTheory") + 1);
                    } else {
                        orgConditionMap.put("realExamGraduationTheory", 1);
                    }
                    if (footMap.containsKey("realExamGraduationTheory")) {
                        footMap.put("realExamGraduationTheory", (Integer) footMap.get("realExamGraduationTheory") + 1);
                    } else {
                        footMap.put("realExamGraduationTheory", 1);
                    }
                    if (orgConditionMap.containsKey("realExamTheory")) {
                        orgConditionMap.put("realExamTheory", (Integer) orgConditionMap.get("realExamTheory") + 1);
                    } else {
                        orgConditionMap.put("realExamTheory", 1);
                    }
                    if (footMap.containsKey("realExamTheory")) {
                        footMap.put("realExamTheory", (Integer) footMap.get("realExamTheory") + 1);
                    } else {
                        footMap.put("realExamTheory", 1);
                    }
                    if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                        if (orgConditionMap.containsKey("realExamGraduationTheoryFirst")) {
                            orgConditionMap.put("realExamGraduationTheoryFirst", (Integer) orgConditionMap.get("realExamGraduationTheoryFirst") + 1);
                        } else {
                            orgConditionMap.put("realExamGraduationTheoryFirst", 1);
                        }
                        if (footMap.containsKey("realExamGraduationTheoryFirst")) {
                            footMap.put("realExamGraduationTheoryFirst", (Integer) footMap.get("realExamGraduationTheoryFirst") + 1);
                        } else {
                            footMap.put("realExamGraduationTheoryFirst", 1);
                        }
                        if (orgConditionMap.containsKey("realExamFirstExam")) {
                            orgConditionMap.put("realExamFirstExam", (Integer) orgConditionMap.get("realExamFirstExam") + 1);
                        } else {
                            orgConditionMap.put("realExamFirstExam", 1);
                        }
                        if (footMap.containsKey("realExamFirstExam")) {
                            footMap.put("realExamFirstExam", (Integer) footMap.get("realExamFirstExam") + 1);
                        } else {
                            footMap.put("realExamFirstExam", 1);
                        }
                        if (graduateMap.containsKey("realExamFirstExam")) {
                            graduateMap.put("realExamFirstExam", (Integer) graduateMap.get("realExamFirstExam") + 1);
                        } else {
                            graduateMap.put("realExamFirstExam", 1);
                        }
                    }
                    if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                        if (orgConditionMap.containsKey("realExamGraduationTheorySecond")) {
                            orgConditionMap.put("realExamGraduationTheorySecond", (Integer) orgConditionMap.get("realExamGraduationTheorySecond") + 1);
                        } else {
                            orgConditionMap.put("realExamGraduationTheorySecond", 1);
                        }
                        if (footMap.containsKey("realExamGraduationTheorySecond")) {
                            footMap.put("realExamGraduationTheorySecond", (Integer) footMap.get("realExamGraduationTheorySecond") + 1);
                        } else {
                            footMap.put("realExamGraduationTheorySecond", 1);
                        }
                        if (orgConditionMap.containsKey("realExamSecondExam")) {
                            orgConditionMap.put("realExamSecondExam", (Integer) orgConditionMap.get("realExamSecondExam") + 1);
                        } else {
                            orgConditionMap.put("realExamSecondExam", 1);
                        }
                        if (footMap.containsKey("realExamSecondExam")) {
                            footMap.put("realExamSecondExam", (Integer) footMap.get("realExamSecondExam") + 1);
                        } else {
                            footMap.put("realExamSecondExam", 1);
                        }
                        if (graduateMap.containsKey("realExamSecondExam")) {
                            graduateMap.put("realExamSecondExam", (Integer) graduateMap.get("realExamSecondExam") + 1);
                        } else {
                            graduateMap.put("realExamSecondExam", 1);
                        }
                    }
                    if (("1").equals(String.valueOf(theoryScore.getTheoryScore()))) {
                        if (orgConditionMap.containsKey("passAll")) {
                            orgConditionMap.put("passAll", (Integer) orgConditionMap.get("passAll") + 1);
                        } else {
                            orgConditionMap.put("passAll", 1);
                        }
                        if (footMap.containsKey("passAll")) {
                            footMap.put("passAll", (Integer) footMap.get("passAll") + 1);
                        } else {
                            footMap.put("passAll", 1);
                        }
                        if (graduateMap.containsKey("passAll")) {
                            graduateMap.put("passAll", (Integer) graduateMap.get("passAll") + 1);
                        } else {
                            graduateMap.put("passAll", 1);
                        }
                        if (orgConditionMap.containsKey("passGraduationTheory")) {
                            orgConditionMap.put("passGraduationTheory", (Integer) orgConditionMap.get("passGraduationTheory") + 1);
                        } else {
                            orgConditionMap.put("passGraduationTheory", 1);
                        }
                        if (footMap.containsKey("passGraduationTheory")) {
                            footMap.put("passGraduationTheory", (Integer) footMap.get("passGraduationTheory") + 1);
                        } else {
                            footMap.put("passGraduationTheory", 1);
                        }
                        if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                            if (orgConditionMap.containsKey("passGraduationTheoryFirst")) {
                                orgConditionMap.put("passGraduationTheoryFirst", (Integer) orgConditionMap.get("passGraduationTheoryFirst") + 1);
                            } else {
                                orgConditionMap.put("passGraduationTheoryFirst", 1);
                            }
                            if (footMap.containsKey("passGraduationTheoryFirst")) {
                                footMap.put("passGraduationTheoryFirst", (Integer) footMap.get("passGraduationTheoryFirst") + 1);
                            } else {
                                footMap.put("passGraduationTheoryFirst", 1);
                            }
                            if (orgConditionMap.containsKey("realExamFirstExamPass")) {
                                orgConditionMap.put("realExamFirstExamPass", (Integer) orgConditionMap.get("realExamFirstExamPass") + 1);
                            } else {
                                orgConditionMap.put("realExamFirstExamPass", 1);
                            }
                            if (footMap.containsKey("realExamFirstExamPass")) {
                                footMap.put("realExamFirstExamPass", (Integer) footMap.get("realExamFirstExamPass") + 1);
                            } else {
                                footMap.put("realExamFirstExamPass", 1);
                            }
                            if (graduateMap.containsKey("realExamFirstExamPass")) {
                                graduateMap.put("realExamFirstExamPass", (Integer) graduateMap.get("realExamFirstExamPass") + 1);
                            } else {
                                graduateMap.put("realExamFirstExamPass", 1);
                            }
                        }
                        if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                            if (orgConditionMap.containsKey("passGraduationTheorySecond")) {
                                orgConditionMap.put("passGraduationTheorySecond", (Integer) orgConditionMap.get("passGraduationTheorySecond") + 1);
                            } else {
                                orgConditionMap.put("passGraduationTheorySecond", 1);
                            }
                            if (footMap.containsKey("passGraduationTheorySecond")) {
                                footMap.put("passGraduationTheorySecond", (Integer) footMap.get("passGraduationTheorySecond") + 1);
                            } else {
                                footMap.put("passGraduationTheorySecond", 1);
                            }
                            if (orgConditionMap.containsKey("realExamSecondExamPass")) {
                                orgConditionMap.put("realExamSecondExamPass", (Integer) orgConditionMap.get("realExamSecondExamPass") + 1);
                            } else {
                                orgConditionMap.put("realExamSecondExamPass", 1);
                            }
                            if (footMap.containsKey("realExamSecondExamPass")) {
                                footMap.put("realExamSecondExamPass", (Integer) footMap.get("realExamSecondExamPass") + 1);
                            } else {
                                footMap.put("realExamSecondExamPass", 1);
                            }
                            if (graduateMap.containsKey("realExamSecondExamPass")) {
                                graduateMap.put("realExamSecondExamPass", (Integer) graduateMap.get("realExamSecondExamPass") + 1);
                            } else {
                                graduateMap.put("realExamSecondExamPass", 1);
                            }
                        }
                    }
                }
                if (skillScore != null) {
                    if (orgConditionMap.containsKey("realAll")) {
                        orgConditionMap.put("realAll", (Integer) orgConditionMap.get("realAll") + 1);
                    } else {
                        orgConditionMap.put("realAll", 1);
                    }
                    if (footMap.containsKey("realAll")) {
                        footMap.put("realAll", (Integer) footMap.get("realAll") + 1);
                    } else {
                        footMap.put("realAll", 1);
                    }
                    if (graduateMap.containsKey("realAll")) {
                        graduateMap.put("realAll", (Integer) graduateMap.get("realAll") + 1);
                    } else {
                        graduateMap.put("realAll", 1);
                    }
                    if (orgConditionMap.containsKey("realExamGraduationSkill")) {
                        orgConditionMap.put("realExamGraduationSkill", (Integer) orgConditionMap.get("realExamGraduationSkill") + 1);
                    } else {
                        orgConditionMap.put("realExamGraduationSkill", 1);
                    }
                    if (footMap.containsKey("realExamGraduationSkill")) {
                        footMap.put("realExamGraduationSkill", (Integer) footMap.get("realExamGraduationSkill") + 1);
                    } else {
                        footMap.put("realExamGraduationSkill", 1);
                    }
                    if (orgConditionMap.containsKey("realExamSkill")) {
                        orgConditionMap.put("realExamSkill", (Integer) orgConditionMap.get("realExamSkill") + 1);
                    } else {
                        orgConditionMap.put("realExamSkill", 1);
                    }
                    if (footMap.containsKey("realExamSkill")) {
                        footMap.put("realExamSkill", (Integer) footMap.get("realExamSkill") + 1);
                    } else {
                        footMap.put("realExamSkill", 1);
                    }
                    if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                        if (orgConditionMap.containsKey("realExamGraduationSkillFirst")) {
                            orgConditionMap.put("realExamGraduationSkillFirst", (Integer) orgConditionMap.get("realExamGraduationSkillFirst") + 1);
                        } else {
                            orgConditionMap.put("realExamGraduationSkillFirst", 1);
                        }
                        if (footMap.containsKey("realExamGraduationSkillFirst")) {
                            footMap.put("realExamGraduationSkillFirst", (Integer) footMap.get("realExamGraduationSkillFirst") + 1);
                        } else {
                            footMap.put("realExamGraduationSkillFirst", 1);
                        }
                        if (orgConditionMap.containsKey("realExamFirstExam")) {
                            orgConditionMap.put("realExamFirstExam", (Integer) orgConditionMap.get("realExamFirstExam") + 1);
                        } else {
                            orgConditionMap.put("realExamFirstExam", 1);
                        }
                        if (footMap.containsKey("realExamFirstExam")) {
                            footMap.put("realExamFirstExam", (Integer) footMap.get("realExamFirstExam") + 1);
                        } else {
                            footMap.put("realExamFirstExam", 1);
                        }
                        if (graduateMap.containsKey("realExamFirstExam")) {
                            graduateMap.put("realExamFirstExam", (Integer) graduateMap.get("realExamFirstExam") + 1);
                        } else {
                            graduateMap.put("realExamFirstExam", 1);
                        }
                    }
                    if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                        if (orgConditionMap.containsKey("realExamGraduationSkillSecond")) {
                            orgConditionMap.put("realExamGraduationSkillSecond", (Integer) orgConditionMap.get("realExamGraduationSkillSecond") + 1);
                        } else {
                            orgConditionMap.put("realExamGraduationSkillSecond", 1);
                        }
                        if (footMap.containsKey("realExamGraduationSkillSecond")) {
                            footMap.put("realExamGraduationSkillSecond", (Integer) footMap.get("realExamGraduationSkillSecond") + 1);
                        } else {
                            footMap.put("realExamGraduationSkillSecond", 1);
                        }
                        if (orgConditionMap.containsKey("realExamSecondExam")) {
                            orgConditionMap.put("realExamSecondExam", (Integer) orgConditionMap.get("realExamSecondExam") + 1);
                        } else {
                            orgConditionMap.put("realExamSecondExam", 1);
                        }
                        if (footMap.containsKey("realExamSecondExam")) {
                            footMap.put("realExamSecondExam", (Integer) footMap.get("realExamSecondExam") + 1);
                        } else {
                            footMap.put("realExamSecondExam", 1);
                        }
                        if (graduateMap.containsKey("realExamSecondExam")) {
                            graduateMap.put("realExamSecondExam", (Integer) graduateMap.get("realExamSecondExam") + 1);
                        } else {
                            graduateMap.put("realExamSecondExam", 1);
                        }
                    }
                    if (("1").equals(String.valueOf(skillScore.getSkillScore()))) {
                        if (orgConditionMap.containsKey("passAll")) {
                            orgConditionMap.put("passAll", (Integer) orgConditionMap.get("passAll") + 1);
                        } else {
                            orgConditionMap.put("passAll", 1);
                        }
                        if (footMap.containsKey("passAll")) {
                            footMap.put("passAll", (Integer) footMap.get("passAll") + 1);
                        } else {
                            footMap.put("passAll", 1);
                        }
                        if (graduateMap.containsKey("passAll")) {
                            graduateMap.put("passAll", (Integer) graduateMap.get("passAll") + 1);
                        } else {
                            graduateMap.put("passAll", 1);
                        }
                        if (orgConditionMap.containsKey("passGraduationSkill")) {
                            orgConditionMap.put("passGraduationSkill", (Integer) orgConditionMap.get("passGraduationSkill") + 1);
                        } else {
                            orgConditionMap.put("passGraduationSkill", 1);
                        }
                        if (footMap.containsKey("passGraduationSkill")) {
                            footMap.put("passGraduationSkill", (Integer) footMap.get("passGraduationSkill") + 1);
                        } else {
                            footMap.put("passGraduationSkill", 1);
                        }
                        if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                            if (orgConditionMap.containsKey("passGraduationSkillFirst")) {
                                orgConditionMap.put("passGraduationSkillFirst", (Integer) orgConditionMap.get("passGraduationSkillFirst") + 1);
                            } else {
                                orgConditionMap.put("passGraduationSkillFirst", 1);
                            }
                            if (footMap.containsKey("passGraduationSkillFirst")) {
                                footMap.put("passGraduationSkillFirst", (Integer) footMap.get("passGraduationSkillFirst") + 1);
                            } else {
                                footMap.put("passGraduationSkillFirst", 1);
                            }
                            if (orgConditionMap.containsKey("realExamFirstExamPass")) {
                                orgConditionMap.put("realExamFirstExamPass", (Integer) orgConditionMap.get("realExamFirstExamPass") + 1);
                            } else {
                                orgConditionMap.put("realExamFirstExamPass", 1);
                            }
                            if (footMap.containsKey("realExamFirstExamPass")) {
                                footMap.put("realExamFirstExamPass", (Integer) footMap.get("realExamFirstExamPass") + 1);
                            } else {
                                footMap.put("realExamFirstExamPass", 1);
                            }
                            if (graduateMap.containsKey("realExamFirstExamPass")) {
                                graduateMap.put("realExamFirstExamPass", (Integer) graduateMap.get("realExamFirstExamPass") + 1);
                            } else {
                                graduateMap.put("realExamFirstExamPass", 1);
                            }
                        }
                        if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                            if (orgConditionMap.containsKey("passGraduationSkillSecond")) {
                                orgConditionMap.put("passGraduationSkillSecond", (Integer) orgConditionMap.get("passGraduationSkillSecond") + 1);
                            } else {
                                orgConditionMap.put("passGraduationSkillSecond", 1);
                            }
                            if (footMap.containsKey("passGraduationSkillSecond")) {
                                footMap.put("passGraduationSkillSecond", (Integer) footMap.get("passGraduationSkillSecond") + 1);
                            } else {
                                footMap.put("passGraduationSkillSecond", 1);
                            }
                            if (orgConditionMap.containsKey("realExamSecondExamPass")) {
                                orgConditionMap.put("realExamSecondExamPass", (Integer) orgConditionMap.get("realExamSecondExamPass") + 1);
                            } else {
                                orgConditionMap.put("realExamSecondExamPass", 1);
                            }
                            if (footMap.containsKey("realExamSecondExamPass")) {
                                footMap.put("realExamSecondExamPass", (Integer) footMap.get("realExamSecondExamPass") + 1);
                            } else {
                                footMap.put("realExamSecondExamPass", 1);
                            }
                            if (graduateMap.containsKey("realExamSecondExamPass")) {
                                graduateMap.put("realExamSecondExamPass", (Integer) graduateMap.get("realExamSecondExamPass") + 1);
                            } else {
                                graduateMap.put("realExamSecondExamPass", 1);
                            }
                        }
                    }
                }
            }
        } else {
            doctorTrainingMap.put("all", (Integer) doctorTrainingMap.get("all") + 1);
            if (orgConditionMap.containsKey("all")) {
                orgConditionMap.put("all", (Integer) orgConditionMap.get("all") + 1);
            } else {
                orgConditionMap.put("all", 1);
            }
            if (footMap.containsKey("all")) {
                footMap.put("all", (Integer) footMap.get("all") + 1);
            } else {
                footMap.put("all", 1);
            }
            if (StringUtil.isNotBlank((String) doctor.get("signupFlow")) && theoryScore != null && skillScore != null) {
                overCountDoctor ++;
            }
            if (theoryScore != null || skillScore != null) {
                doctorTrainingMap.put("realExam", (Integer) doctorTrainingMap.get("realExam") + 1);
                if (orgConditionMap.containsKey("realExamAll")) {
                    orgConditionMap.put("realExamAll", (Integer) orgConditionMap.get("realExamAll") + 1);
                } else {
                    orgConditionMap.put("realExamAll", 1);
                }
                if (footMap.containsKey("realExamAll")) {
                    footMap.put("realExamAll", (Integer) footMap.get("realExamAll") + 1);
                } else {
                    footMap.put("realExamAll", 1);
                }
                if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                    doctorTrainingMap.put("firstExam", (Integer) doctorTrainingMap.get("firstExam") + 1);
                    if (orgConditionMap.containsKey("firstExam")) {
                        orgConditionMap.put("firstExam", (Integer) orgConditionMap.get("firstExam") + 1);
                    } else {
                        orgConditionMap.put("firstExam", 1);
                    }
                    if (footMap.containsKey("firstExam")) {
                        footMap.put("firstExam", (Integer) footMap.get("firstExam") + 1);
                    } else {
                        footMap.put("firstExam", 1);
                    }
                }
                if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                    doctorTrainingMap.put("secondExam", (Integer) doctorTrainingMap.get("secondExam") + 1);
                    if (orgConditionMap.containsKey("secondExam")) {
                        orgConditionMap.put("secondExam", (Integer) orgConditionMap.get("secondExam") + 1);
                    } else {
                        orgConditionMap.put("secondExam", 1);
                    }
                    if (footMap.containsKey("secondExam")) {
                        footMap.put("secondExam", (Integer) footMap.get("secondExam") + 1);
                    } else {
                        footMap.put("secondExam", 1);
                    }
                }
                if (theoryScore != null) {
                    if (orgConditionMap.containsKey("realAll")) {
                        orgConditionMap.put("realAll", (Integer) orgConditionMap.get("realAll") + 1);
                    } else {
                        orgConditionMap.put("realAll", 1);
                    }
                    if (footMap.containsKey("realAll")) {
                        footMap.put("realAll", (Integer) footMap.get("realAll") + 1);
                    } else {
                        footMap.put("realAll", 1);
                    }
                    if (doctorTrainingMap.containsKey("realAll")) {
                        doctorTrainingMap.put("realAll", (Integer) doctorTrainingMap.get("realAll") + 1);
                    } else {
                        doctorTrainingMap.put("realAll", 1);
                    }
                    if (orgConditionMap.containsKey("realExamDoctorTheory")) {
                        orgConditionMap.put("realExamDoctorTheory", (Integer) orgConditionMap.get("realExamDoctorTheory") + 1);
                    } else {
                        orgConditionMap.put("realExamDoctorTheory", 1);
                    }
                    if (footMap.containsKey("realExamDoctorTheory")) {
                        footMap.put("realExamDoctorTheory", (Integer) footMap.get("realExamDoctorTheory") + 1);
                    } else {
                        footMap.put("realExamDoctorTheory", 1);
                    }
                    if (orgConditionMap.containsKey("realExamTheory")) {
                        orgConditionMap.put("realExamTheory", (Integer) orgConditionMap.get("realExamTheory") + 1);
                    } else {
                        orgConditionMap.put("realExamTheory", 1);
                    }
                    if (footMap.containsKey("realExamTheory")) {
                        footMap.put("realExamTheory", (Integer) footMap.get("realExamTheory") + 1);
                    } else {
                        footMap.put("realExamTheory", 1);
                    }
                    if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                        if (orgConditionMap.containsKey("realExamDoctorTheoryFirst")) {
                            orgConditionMap.put("realExamDoctorTheoryFirst", (Integer) orgConditionMap.get("realExamDoctorTheoryFirst") + 1);
                        } else {
                            orgConditionMap.put("realExamDoctorTheoryFirst", 1);
                        }
                        if (footMap.containsKey("realExamDoctorTheoryFirst")) {
                            footMap.put("realExamDoctorTheoryFirst", (Integer) footMap.get("realExamDoctorTheoryFirst") + 1);
                        } else {
                            footMap.put("realExamDoctorTheoryFirst", 1);
                        }
                        if (orgConditionMap.containsKey("realExamFirstExam")) {
                            orgConditionMap.put("realExamFirstExam", (Integer) orgConditionMap.get("realExamFirstExam") + 1);
                        } else {
                            orgConditionMap.put("realExamFirstExam", 1);
                        }
                        if (footMap.containsKey("realExamFirstExam")) {
                            footMap.put("realExamFirstExam", (Integer) footMap.get("realExamFirstExam") + 1);
                        } else {
                            footMap.put("realExamFirstExam", 1);
                        }
                        if (doctorTrainingMap.containsKey("realExamFirstExam")) {
                            doctorTrainingMap.put("realExamFirstExam", (Integer) doctorTrainingMap.get("realExamFirstExam") + 1);
                        } else {
                            doctorTrainingMap.put("realExamFirstExam", 1);
                        }
                    }
                    if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                        if (orgConditionMap.containsKey("realExamDoctorTheorySecond")) {
                            orgConditionMap.put("realExamDoctorTheorySecond", (Integer) orgConditionMap.get("realExamDoctorTheorySecond") + 1);
                        } else {
                            orgConditionMap.put("realExamDoctorTheorySecond", 1);
                        }
                        if (footMap.containsKey("realExamDoctorTheorySecond")) {
                            footMap.put("realExamDoctorTheorySecond", (Integer) footMap.get("realExamDoctorTheorySecond") + 1);
                        } else {
                            footMap.put("realExamDoctorTheorySecond", 1);
                        }
                        if (orgConditionMap.containsKey("realExamSecondExam")) {
                            orgConditionMap.put("realExamSecondExam", (Integer) orgConditionMap.get("realExamSecondExam") + 1);
                        } else {
                            orgConditionMap.put("realExamSecondExam", 1);
                        }
                        if (footMap.containsKey("realExamSecondExam")) {
                            footMap.put("realExamSecondExam", (Integer) footMap.get("realExamSecondExam") + 1);
                        } else {
                            footMap.put("realExamSecondExam", 1);
                        }
                        if (doctorTrainingMap.containsKey("realExamSecondExam")) {
                            doctorTrainingMap.put("realExamSecondExam", (Integer) doctorTrainingMap.get("realExamSecondExam") + 1);
                        } else {
                            doctorTrainingMap.put("realExamSecondExam", 1);
                        }
                    }
                    if (("1").equals(String.valueOf(theoryScore.getTheoryScore()))) {
                        if (orgConditionMap.containsKey("passAll")) {
                            orgConditionMap.put("passAll", (Integer) orgConditionMap.get("passAll") + 1);
                        } else {
                            orgConditionMap.put("passAll", 1);
                        }
                        if (footMap.containsKey("passAll")) {
                            footMap.put("passAll", (Integer) footMap.get("passAll") + 1);
                        } else {
                            footMap.put("passAll", 1);
                        }
                        if (doctorTrainingMap.containsKey("passAll")) {
                            doctorTrainingMap.put("passAll", (Integer) doctorTrainingMap.get("passAll") + 1);
                        } else {
                            doctorTrainingMap.put("passAll", 1);
                        }
                        if (orgConditionMap.containsKey("passDoctorTheory")) {
                            orgConditionMap.put("passDoctorTheory", (Integer) orgConditionMap.get("passDoctorTheory") + 1);
                        } else {
                            orgConditionMap.put("passDoctorTheory", 1);
                        }
                        if (footMap.containsKey("passDoctorTheory")) {
                            footMap.put("passDoctorTheory", (Integer) footMap.get("passDoctorTheory") + 1);
                        } else {
                            footMap.put("passDoctorTheory", 1);
                        }
                        if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                            if (orgConditionMap.containsKey("passDoctorTheoryFirst")) {
                                orgConditionMap.put("passDoctorTheoryFirst", (Integer) orgConditionMap.get("passDoctorTheoryFirst") + 1);
                            } else {
                                orgConditionMap.put("passDoctorTheoryFirst", 1);
                            }
                            if (footMap.containsKey("passDoctorTheoryFirst")) {
                                footMap.put("passDoctorTheoryFirst", (Integer) footMap.get("passDoctorTheoryFirst") + 1);
                            } else {
                                footMap.put("passDoctorTheoryFirst", 1);
                            }
                            if (orgConditionMap.containsKey("realExamFirstExamPass")) {
                                orgConditionMap.put("realExamFirstExamPass", (Integer) orgConditionMap.get("realExamFirstExamPass") + 1);
                            } else {
                                orgConditionMap.put("realExamFirstExamPass", 1);
                            }
                            if (footMap.containsKey("realExamFirstExamPass")) {
                                footMap.put("realExamFirstExamPass", (Integer) footMap.get("realExamFirstExamPass") + 1);
                            } else {
                                footMap.put("realExamFirstExamPass", 1);
                            }
                            if (doctorTrainingMap.containsKey("realExamFirstExamPass")) {
                                doctorTrainingMap.put("realExamFirstExamPass", (Integer) doctorTrainingMap.get("realExamFirstExamPass") + 1);
                            } else {
                                doctorTrainingMap.put("realExamFirstExamPass", 1);
                            }
                        }
                        if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                            if (orgConditionMap.containsKey("passDoctorTheorySecond")) {
                                orgConditionMap.put("passDoctorTheorySecond", (Integer) orgConditionMap.get("passDoctorTheorySecond") + 1);
                            } else {
                                orgConditionMap.put("passDoctorTheorySecond", 1);
                            }
                            if (footMap.containsKey("passDoctorTheorySecond")) {
                                footMap.put("passDoctorTheorySecond", (Integer) footMap.get("passDoctorTheorySecond") + 1);
                            } else {
                                footMap.put("passDoctorTheorySecond", 1);
                            }
                            if (orgConditionMap.containsKey("realExamSecondExamPass")) {
                                orgConditionMap.put("realExamSecondExamPass", (Integer) orgConditionMap.get("realExamSecondExamPass") + 1);
                            } else {
                                orgConditionMap.put("realExamSecondExamPass", 1);
                            }
                            if (footMap.containsKey("realExamSecondExamPass")) {
                                footMap.put("realExamSecondExamPass", (Integer) footMap.get("realExamSecondExamPass") + 1);
                            } else {
                                footMap.put("realExamSecondExamPass", 1);
                            }
                            if (doctorTrainingMap.containsKey("realExamSecondExamPass")) {
                                doctorTrainingMap.put("realExamSecondExamPass", (Integer) doctorTrainingMap.get("realExamSecondExamPass") + 1);
                            } else {
                                doctorTrainingMap.put("realExamSecondExamPass", 1);
                            }
                        }
                    }
                }
                if (skillScore != null) {
                    if (orgConditionMap.containsKey("realAll")) {
                        orgConditionMap.put("realAll", (Integer) orgConditionMap.get("realAll") + 1);
                    } else {
                        orgConditionMap.put("realAll", 1);
                    }
                    if (footMap.containsKey("realAll")) {
                        footMap.put("realAll", (Integer) footMap.get("realAll") + 1);
                    } else {
                        footMap.put("realAll", 1);
                    }
                    if (doctorTrainingMap.containsKey("realAll")) {
                        doctorTrainingMap.put("realAll", (Integer) doctorTrainingMap.get("realAll") + 1);
                    } else {
                        doctorTrainingMap.put("realAll", 1);
                    }
                    if (orgConditionMap.containsKey("realExamDoctorSkill")) {
                        orgConditionMap.put("realExamDoctorSkill", (Integer) orgConditionMap.get("realExamDoctorSkill") + 1);
                    } else {
                        orgConditionMap.put("realExamDoctorSkill", 1);
                    }
                    if (footMap.containsKey("realExamDoctorSkill")) {
                        footMap.put("realExamDoctorSkill", (Integer) footMap.get("realExamDoctorSkill") + 1);
                    } else {
                        footMap.put("realExamDoctorSkill", 1);
                    }
                    if (orgConditionMap.containsKey("realExamSkill")) {
                        orgConditionMap.put("realExamSkill", (Integer) orgConditionMap.get("realExamSkill") + 1);
                    } else {
                        orgConditionMap.put("realExamSkill", 1);
                    }
                    if (footMap.containsKey("realExamSkill")) {
                        footMap.put("realExamSkill", (Integer) footMap.get("realExamSkill") + 1);
                    } else {
                        footMap.put("realExamSkill", 1);
                    }
                    if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                        if (orgConditionMap.containsKey("realExamDoctorSkillFirst")) {
                            orgConditionMap.put("realExamDoctorSkillFirst", (Integer) orgConditionMap.get("realExamDoctorSkillFirst") + 1);
                        } else {
                            orgConditionMap.put("realExamDoctorSkillFirst", 1);
                        }
                        if (footMap.containsKey("realExamDoctorSkillFirst")) {
                            footMap.put("realExamDoctorSkillFirst", (Integer) footMap.get("realExamDoctorSkillFirst") + 1);
                        } else {
                            footMap.put("realExamDoctorSkillFirst", 1);
                        }
                        if (orgConditionMap.containsKey("realExamFirstExam")) {
                            orgConditionMap.put("realExamFirstExam", (Integer) orgConditionMap.get("realExamFirstExam") + 1);
                        } else {
                            orgConditionMap.put("realExamFirstExam", 1);
                        }
                        if (footMap.containsKey("realExamFirstExam")) {
                            footMap.put("realExamFirstExam", (Integer) footMap.get("realExamFirstExam") + 1);
                        } else {
                            footMap.put("realExamFirstExam", 1);
                        }
                        if (doctorTrainingMap.containsKey("realExamFirstExam")) {
                            doctorTrainingMap.put("realExamFirstExam", (Integer) doctorTrainingMap.get("realExamFirstExam") + 1);
                        } else {
                            doctorTrainingMap.put("realExamFirstExam", 1);
                        }
                    }
                    if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                        if (orgConditionMap.containsKey("realExamDoctorSkillSecond")) {
                            orgConditionMap.put("realExamDoctorSkillSecond", (Integer) orgConditionMap.get("realExamDoctorSkillSecond") + 1);
                        } else {
                            orgConditionMap.put("realExamDoctorSkillSecond", 1);
                        }
                        if (footMap.containsKey("realExamDoctorSkillSecond")) {
                            footMap.put("realExamDoctorSkillSecond", (Integer) footMap.get("realExamDoctorSkillSecond") + 1);
                        } else {
                            footMap.put("realExamDoctorSkillSecond", 1);
                        }
                        if (orgConditionMap.containsKey("realExamSecondExam")) {
                            orgConditionMap.put("realExamSecondExam", (Integer) orgConditionMap.get("realExamSecondExam") + 1);
                        } else {
                            orgConditionMap.put("realExamSecondExam", 1);
                        }
                        if (footMap.containsKey("realExamSecondExam")) {
                            footMap.put("realExamSecondExam", (Integer) footMap.get("realExamSecondExam") + 1);
                        } else {
                            footMap.put("realExamSecondExam", 1);
                        }
                        if (doctorTrainingMap.containsKey("realExamSecondExam")) {
                            doctorTrainingMap.put("realExamSecondExam", (Integer) doctorTrainingMap.get("realExamSecondExam") + 1);
                        } else {
                            doctorTrainingMap.put("realExamSecondExam", 1);
                        }
                    }
                    if (("1").equals(String.valueOf(skillScore.getSkillScore()))) {
                        if (orgConditionMap.containsKey("passAll")) {
                            orgConditionMap.put("passAll", (Integer) orgConditionMap.get("passAll") + 1);
                        } else {
                            orgConditionMap.put("passAll", 1);
                        }
                        if (footMap.containsKey("passAll")) {
                            footMap.put("passAll", (Integer) footMap.get("passAll") + 1);
                        } else {
                            footMap.put("passAll", 1);
                        }
                        if (doctorTrainingMap.containsKey("passAll")) {
                            doctorTrainingMap.put("passAll", (Integer) doctorTrainingMap.get("passAll") + 1);
                        } else {
                            doctorTrainingMap.put("passAll", 1);
                        }
                        if (orgConditionMap.containsKey("passDoctorSkill")) {
                            orgConditionMap.put("passDoctorSkill", (Integer) orgConditionMap.get("passDoctorSkill") + 1);
                        } else {
                            orgConditionMap.put("passDoctorSkill", 1);
                        }
                        if (footMap.containsKey("passDoctorSkill")) {
                            footMap.put("passDoctorSkill", (Integer) footMap.get("passDoctorSkill") + 1);
                        } else {
                            footMap.put("passDoctorSkill", 1);
                        }
                        if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
                            if (orgConditionMap.containsKey("passDoctorSkillFirst")) {
                                orgConditionMap.put("passDoctorSkillFirst", (Integer) orgConditionMap.get("passDoctorSkillFirst") + 1);
                            } else {
                                orgConditionMap.put("passDoctorSkillFirst", 1);
                            }
                            if (footMap.containsKey("passDoctorSkillFirst")) {
                                footMap.put("passDoctorSkillFirst", (Integer) footMap.get("passDoctorSkillFirst") + 1);
                            } else {
                                footMap.put("passDoctorSkillFirst", 1);
                            }
                            if (orgConditionMap.containsKey("realExamFirstExamPass")) {
                                orgConditionMap.put("realExamFirstExamPass", (Integer) orgConditionMap.get("realExamFirstExamPass") + 1);
                            } else {
                                orgConditionMap.put("realExamFirstExamPass", 1);
                            }
                            if (footMap.containsKey("realExamFirstExamPass")) {
                                footMap.put("realExamFirstExamPass", (Integer) footMap.get("realExamFirstExamPass") + 1);
                            } else {
                                footMap.put("realExamFirstExamPass", 1);
                            }
                            if (doctorTrainingMap.containsKey("realExamFirstExamPass")) {
                                doctorTrainingMap.put("realExamFirstExamPass", (Integer) doctorTrainingMap.get("realExamFirstExamPass") + 1);
                            } else {
                                doctorTrainingMap.put("realExamFirstExamPass", 1);
                            }
                        }
                        if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
                            if (orgConditionMap.containsKey("passDoctorSkillSecond")) {
                                orgConditionMap.put("passDoctorSkillSecond", (Integer) orgConditionMap.get("passDoctorSkillSecond") + 1);
                            } else {
                                orgConditionMap.put("passDoctorSkillSecond", 1);
                            }
                            if (footMap.containsKey("passDoctorSkillSecond")) {
                                footMap.put("passDoctorSkillSecond", (Integer) footMap.get("passDoctorSkillSecond") + 1);
                            } else {
                                footMap.put("passDoctorSkillSecond", 1);
                            }
                            if (orgConditionMap.containsKey("realExamSecondExamPass")) {
                                orgConditionMap.put("realExamSecondExamPass", (Integer) orgConditionMap.get("realExamSecondExamPass") + 1);
                            } else {
                                orgConditionMap.put("realExamSecondExamPass", 1);
                            }
                            if (footMap.containsKey("realExamSecondExamPass")) {
                                footMap.put("realExamSecondExamPass", (Integer) footMap.get("realExamSecondExamPass") + 1);
                            } else {
                                footMap.put("realExamSecondExamPass", 1);
                            }
                            if (doctorTrainingMap.containsKey("realExamSecondExamPass")) {
                                doctorTrainingMap.put("realExamSecondExamPass", (Integer) doctorTrainingMap.get("realExamSecondExamPass") + 1);
                            } else {
                                doctorTrainingMap.put("realExamSecondExamPass", 1);
                            }
                        }
                    }
                }
            }
        }
        if (StringUtil.isNotBlank((String) doctor.get("applyFlow"))) {
            if (orgConditionMap.containsKey("Theory")) {
                orgConditionMap.put("Theory", (Integer) orgConditionMap.get("Theory") + 1);
            } else {
                orgConditionMap.put("Theory", 1);
            }
            if (footMap.containsKey("Theory")) {
                footMap.put("Theory", (Integer) footMap.get("Theory") + 1);
            } else {
                footMap.put("Theory", 1);
            }
            if (orgConditionMap.containsKey("Skill")) {
                orgConditionMap.put("Skill", (Integer) orgConditionMap.get("Skill") + 1);
            } else {
                orgConditionMap.put("Skill", 1);
            }
            if (footMap.containsKey("Skill")) {
                footMap.put("Skill", (Integer) footMap.get("Skill") + 1);
            } else {
                footMap.put("Skill", 1);
            }
        }
        if (StringUtil.isNotBlank((String) doctor.get("signupFlow"))) {
            if ("Theory".equals(doctor.get("signupTypeId"))) {
                if (orgConditionMap.containsKey("Theory")) {
                    orgConditionMap.put("Theory", (Integer) orgConditionMap.get("Theory") + 1);
                } else {
                    orgConditionMap.put("Theory", 1);
                }
                if (footMap.containsKey("Theory")) {
                    footMap.put("Theory", (Integer) footMap.get("Theory") + 1);
                } else {
                    footMap.put("Theory", 1);
                }
            }
            if ("Skill".equals(doctor.get("signupTypeId"))) {
                if (orgConditionMap.containsKey("Skill")) {
                    orgConditionMap.put("Skill", (Integer) orgConditionMap.get("Skill") + 1);
                } else {
                    orgConditionMap.put("Skill", 1);
                }
                if (footMap.containsKey("Skill")) {
                    footMap.put("Skill", (Integer) footMap.get("Skill") + 1);
                } else {
                    footMap.put("Skill", 1);
                }
            }
        }
    }

    private void initGeneralData(HashMap<String, Object> map) {
        map.put("all", 0);
        map.put("realExam", 0);
        map.put("missExam", 0);
        map.put("firstExam", 0);
        map.put("secondExam", 0);
    }

    @RequestMapping("/exportFinishStudyReport")
    public void exportFinishStudyReport(String graduationYear, String sessionNumber, String cityId, String orgFlow, String isJointOrgIn, String speId, String[] doctorType, String scoreType, String sortBy, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

        //列宽自适应
        HSSFRow rowOne = sheet.createRow(0);//第1行
        HSSFRow rowTwo = sheet.createRow(1);//第2行
        HSSFRow rowThree = sheet.createRow(2);//第2行

        designTableTitle(sheet, styleCenter, rowOne, rowTwo, rowThree);

        if (StringUtil.isBlank(graduationYear)) {
            if (DateUtil.getCurrDate().compareTo(DateUtil.getYear() + "-05-31") > 0) {
                graduationYear = DateUtil.getYear();
            } else {
                graduationYear = String.valueOf(Integer.parseInt(DateUtil.getYear()) - 1);
            }
        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        doctorType = parameterMap.get("doctorType[]");

        Map<String,Object> params = new HashMap<>();
        List<String> docTypeList = new ArrayList<>();
        if(doctorType != null && doctorType.length > 0){
            docTypeList.addAll(Arrays.asList(doctorType));
        }else{
            for(JsResDocTypeEnum e : JsResDocTypeEnum.values()) {
                docTypeList.add(e.getId());
            }
        }

        List<String> sessionNumberList = new ArrayList<>();
        if (StringUtil.isNotBlank(sessionNumber)) {
            sessionNumberList.addAll(Arrays.asList(sessionNumber.split(",")));
        }

        List<SysOrg> orgList = new ArrayList<>();
        if (StringUtil.isNotBlank(cityId)) {
            orgList = getOrgListByCityId(cityId);
        } else {
            List<String> orgLevels = new ArrayList<>();
            orgLevels.add(OrgLevelEnum.CountryOrg.getId());
            // 查询基地/医院
            orgList = orgBiz.searchSysOrgOrder(orgLevels);
        }
        orgList.sort(Comparator.comparingInt(o -> o.getOrgCode().hashCode()));

        List<SysOrg> searchOrgList = new ArrayList<>();
        if (StringUtil.isNotBlank(orgFlow)) {
            searchOrgList.add(orgBiz.readSysOrg(orgFlow));
        } else {
            searchOrgList = orgList;
        }

        params.put("docTypeList",docTypeList);
        params.put("speId",speId);

        // 对数据进行计算，初始化值
        // 住院医师
        HashMap<String, Object> doctorTrainingMap = new HashMap<>();
        initGeneralData(doctorTrainingMap);
        // 在校专硕
        HashMap<String, Object> graduateMap = new HashMap<>();
        initGeneralData(graduateMap);
        List<Map<String, Object>> resultList = new ArrayList<>();
        Map<String, Object> footMap = new HashMap<>();
        int overGraduation = 0;
        int overDoctor = 0;
        for (SysOrg sysOrg : searchOrgList) {
            String searchOrgFlow = sysOrg.getOrgFlow() == null ? "" : sysOrg.getOrgFlow();
            params.put("orgFlow",searchOrgFlow);
            params.put("sessionNumbers",sessionNumberList);
            params.put("graduationYear", graduationYear);
            params.put("scoreType",scoreType);
            params.put("catSpeId","DoctorTrainingSpe");
            List<Map<String,Object>> countryOrgTotalDoctorList = graduationBiz.searchDoctorRecruit(params);
            if (StringUtil.isNotBlank(isJointOrgIn) && isJointOrgIn.equals(GlobalConstant.FLAG_Y)) {
                countryOrgTotalDoctorList.addAll(graduationBiz.searchJointOrgDoctorRecruit(params));
            }
            // 查询人员名单并处理数据
            Map<String,Object> orgConditionMap = new HashMap<>();
            orgConditionMap.put("orgCode", sysOrg.getOrgCode());
            orgConditionMap.put("orgName", sysOrg.getOrgName());
            orgConditionMap.put("orgCityId", sysOrg.getOrgCityId());
            orgConditionMap.put("orgCityName", sysOrg.getOrgCityName());
            overCountGraduation = 0;
            overCountDoctor = 0;
            for (Map<String, Object> doctor : countryOrgTotalDoctorList) {
                countForGraduationReport(scoreType, graduationYear, doctorTrainingMap, graduateMap, orgConditionMap, footMap, doctor);
            }
            // 首考通过率计算
            calculateFirstExamPercent(orgConditionMap);
            // 补考通过率计算
            calculateSecondExamPercent(orgConditionMap);
            // 综合通过率计算
            calculateExamPercent(orgConditionMap);
            overGraduation += overCountGraduation;
            overDoctor += overCountDoctor;
            if (overCountGraduation > 0) {
                graduateMap.put("all", (Integer) graduateMap.get("all") - overCountGraduation / 2);
                graduateMap.put("realExam", (Integer) graduateMap.get("realExam") - overCountGraduation / 2);
                graduateMap.put("secondExam", (Integer) graduateMap.get("secondExam") - overCountGraduation / 2);
                orgConditionMap.put("all", (Integer) orgConditionMap.get("all") - overCountGraduation / 2);
                orgConditionMap.put("realExamTheory", (Integer) orgConditionMap.get("realExamTheory") - overCountGraduation / 2);
                orgConditionMap.put("realExamSkill", (Integer) orgConditionMap.get("realExamSkill") - overCountGraduation / 2);
                orgConditionMap.put("realExamAll", (Integer) orgConditionMap.get("realExamAll") - overCountGraduation / 2);
                orgConditionMap.put("realExamGraduationTheorySecond", (Integer) orgConditionMap.get("realExamGraduationTheorySecond") - overCountGraduation / 2);
                orgConditionMap.put("realExamGraduationSkillSecond", (Integer) orgConditionMap.get("realExamGraduationSkillSecond") - overCountGraduation / 2);
                orgConditionMap.put("secondExam", (Integer) orgConditionMap.get("secondExam") - overCountGraduation / 2);
            }
            if (overCountDoctor > 0) {
                doctorTrainingMap.put("all", (Integer) doctorTrainingMap.get("all") - overCountDoctor / 2);
                doctorTrainingMap.put("realExam", (Integer) doctorTrainingMap.get("realExam") - overCountDoctor / 2);
                doctorTrainingMap.put("secondExam", (Integer) doctorTrainingMap.get("secondExam") - overCountDoctor / 2);
                orgConditionMap.put("all", (Integer) orgConditionMap.get("all") - overCountDoctor / 2);
                orgConditionMap.put("realExamTheory", (Integer) orgConditionMap.get("realExamTheory") - overCountDoctor / 2);
                orgConditionMap.put("realExamSkill", (Integer) orgConditionMap.get("realExamSkill") - overCountDoctor / 2);
                orgConditionMap.put("realExamAll", (Integer) orgConditionMap.get("realExamAll") - overCountDoctor / 2);
                orgConditionMap.put("realExamDoctorTheorySecond", (Integer) orgConditionMap.get("realExamDoctorTheorySecond") - overCountDoctor / 2);
                orgConditionMap.put("realExamDoctorSkillSecond", (Integer) orgConditionMap.get("realExamDoctorSkillSecond") - overCountDoctor / 2);
                orgConditionMap.put("secondExam", (Integer) orgConditionMap.get("secondExam") - overCountDoctor / 2);
            }
            if (StringUtil.isNotBlank(scoreType) && scoreType.equals("Theory")) {
                orgConditionMap.put("Skill", 0);
            }
            if (StringUtil.isNotBlank(scoreType) && scoreType.equals("Skill")) {
                orgConditionMap.put("Theory", 0);
            }
            calculateMissExamData(orgConditionMap);
            resultList.add(orgConditionMap);
        }

        if ("firstExamPassPercent".equals(sortBy)) {
            resultList.sort((o1, o2) -> o2.get("firstPassPercent").hashCode() - o1.get("firstPassPercent").hashCode());
        }
        if ("secondExamPassPercent".equals(sortBy)) {
            resultList.sort((o1, o2) -> o2.get("secondPassPercent").hashCode() - o1.get("secondPassPercent").hashCode());
        }
        if ("examPassPercent".equals(sortBy)) {
            resultList.sort((o1, o2) -> o2.get("PassPercent").hashCode() - o1.get("PassPercent").hashCode());
        }

        calculateFootPercent(footMap);
        if (overGraduation > 0) {
            footMap.put("all", (Integer) footMap.get("all") - overGraduation / 2);
            footMap.put("realExamTheory", (Integer) footMap.get("realExamTheory") - overGraduation / 2);
            footMap.put("realExamSkill", (Integer) footMap.get("realExamSkill") - overGraduation / 2);
            footMap.put("realExamAll", (Integer) footMap.get("realExamAll") - overGraduation / 2);
            footMap.put("realExamGraduationTheorySecond", (Integer) footMap.get("realExamGraduationTheorySecond") - overGraduation / 2);
            footMap.put("realExamGraduationSkillSecond", (Integer) footMap.get("realExamGraduationSkillSecond") - overGraduation / 2);
            footMap.put("secondExam", (Integer) footMap.get("secondExam") - overGraduation / 2);
        }
        if (overDoctor > 0) {
            footMap.put("all", (Integer) footMap.get("all") - overDoctor / 2);
            footMap.put("realExamTheory", (Integer) footMap.get("realExamTheory") - overDoctor / 2);
            footMap.put("realExamSkill", (Integer) footMap.get("realExamSkill") - overDoctor / 2);
            footMap.put("realExamAll", (Integer) footMap.get("realExamAll") - overDoctor / 2);
            footMap.put("realExamDoctorTheorySecond", (Integer) footMap.get("realExamDoctorTheorySecond") - overDoctor / 2);
            footMap.put("realExamDoctorSkillSecond", (Integer) footMap.get("realExamDoctorSkillSecond") - overDoctor / 2);
            footMap.put("secondExam", (Integer) footMap.get("secondExam") - overDoctor / 2);
        }

        if (StringUtil.isNotBlank(scoreType) && scoreType.equals("Theory")) {
            footMap.put("Skill", 0);
        }
        if (StringUtil.isNotBlank(scoreType) && scoreType.equals("Skill")) {
            footMap.put("Theory", 0);
        }
        calculateMissExamData(footMap);

        exportForBodyPart(sheet, styleCenter, resultList);

        exportForFootPart(sheet, styleCenter, searchOrgList, footMap);


        String fileName = "结业统计报表.xls";
        if (StringUtil.isNotBlank(graduationYear)) {
            fileName = graduationYear + "年结业统计报表.xls";
        }
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    private void exportForFootPart(HSSFSheet sheet, HSSFCellStyle styleCenter, List<SysOrg> searchOrgList, Map<String, Object> footMap) {
        HSSFRow rowFour = sheet.createRow(searchOrgList.size() + 3);
        CellRangeAddress cellRangeAddress = new CellRangeAddress(searchOrgList.size() + 3, searchOrgList.size() + 3, 0, 1);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle2 = rowFour.createCell(0);
        cellTitle2.setCellValue("总计");
        cellTitle2.setCellStyle(styleCenter);
        int j = 2;
        HSSFCell cell3 = rowFour.createCell(j++);
        cell3.setCellStyle(styleCenter);
        cell3.setCellValue((Integer) footMap.getOrDefault("Theory", 0));
        HSSFCell cell4 = rowFour.createCell(j++);
        cell4.setCellStyle(styleCenter);
        cell4.setCellValue((Integer) footMap.getOrDefault("Skill", 0));
        HSSFCell cell5 = rowFour.createCell(j++);
        cell5.setCellStyle(styleCenter);
        cell5.setCellValue((Integer) footMap.getOrDefault("all", 0));
        HSSFCell cell6 = rowFour.createCell(j++);
        cell6.setCellStyle(styleCenter);
        cell6.setCellValue((Integer) footMap.getOrDefault("realExamTheory", 0));
        HSSFCell cell7 = rowFour.createCell(j++);
        cell7.setCellStyle(styleCenter);
        cell7.setCellValue((Integer) footMap.getOrDefault("realExamSkill", 0));
        HSSFCell cell8 = rowFour.createCell(j++);
        cell8.setCellStyle(styleCenter);
        cell8.setCellValue((Integer) footMap.getOrDefault("realExamAll", 0));
        HSSFCell cell9 = rowFour.createCell(j++);
        cell9.setCellStyle(styleCenter);
        cell9.setCellValue((Integer) footMap.getOrDefault("realExamGraduationTheoryFirst", 0) + (Integer) footMap.getOrDefault("realExamDoctorTheoryFirst", 0));
        HSSFCell cell10 = rowFour.createCell(j++);
        cell10.setCellStyle(styleCenter);
        cell10.setCellValue((Integer) footMap.getOrDefault("realExamGraduationSkillFirst", 0) + (Integer) footMap.getOrDefault("realExamDoctorSkillFirst", 0));
        HSSFCell cell11 = rowFour.createCell(j++);
        cell11.setCellStyle(styleCenter);
        cell11.setCellValue((Integer) footMap.getOrDefault("firstExam", 0));
        HSSFCell cell12 = rowFour.createCell(j++);
        cell12.setCellStyle(styleCenter);
        cell12.setCellValue((Integer) footMap.getOrDefault("realExamGraduationTheorySecond", 0) + (Integer) footMap.getOrDefault("realExamDoctorTheorySecond", 0));
        HSSFCell cell13 = rowFour.createCell(j++);
        cell13.setCellStyle(styleCenter);
        cell13.setCellValue((Integer) footMap.getOrDefault("realExamGraduationSkillSecond", 0) + (Integer) footMap.getOrDefault("realExamDoctorSkillSecond", 0));
        HSSFCell cell14 = rowFour.createCell(j++);
        cell14.setCellStyle(styleCenter);
        cell14.setCellValue((Integer) footMap.getOrDefault("secondExam", 0));
        HSSFCell cell15 = rowFour.createCell(j++);
        cell15.setCellStyle(styleCenter);
        cell15.setCellValue((Integer) footMap.getOrDefault("missTheory", 0));
        HSSFCell cell16 = rowFour.createCell(j++);
        cell16.setCellStyle(styleCenter);
        cell16.setCellValue((Integer) footMap.getOrDefault("missSkill", 0));
        HSSFCell cell17 = rowFour.createCell(j++);
        cell17.setCellStyle(styleCenter);
        cell17.setCellValue((Integer) footMap.getOrDefault("missAll", 0));
        HSSFCell cell18 = rowFour.createCell(j++);
        cell18.setCellStyle(styleCenter);
        cell18.setCellValue(footMap.get("doctorTheoryFirstPassPercent") + "%");
        HSSFCell cell19 = rowFour.createCell(j++);
        cell19.setCellStyle(styleCenter);
        cell19.setCellValue(footMap.get("graduationTheoryFirstPassPercent") + "%");
        HSSFCell cell20 = rowFour.createCell(j++);
        cell20.setCellStyle(styleCenter);
        cell20.setCellValue(footMap.get("doctorSkillFirstPassPercent") + "%");
        HSSFCell cell21 = rowFour.createCell(j++);
        cell21.setCellStyle(styleCenter);
        cell21.setCellValue(footMap.get("graduationSkillFirstPassPercent") + "%");
        HSSFCell cell22 = rowFour.createCell(j++);
        cell22.setCellStyle(styleCenter);
        cell22.setCellValue((String) footMap.get("firstPassPercent") + "%");
        HSSFCell cell23 = rowFour.createCell(j++);
        cell23.setCellStyle(styleCenter);
        cell23.setCellValue((String) footMap.get("doctorTheorySecondPassPercent") + "%");
        HSSFCell cell24 = rowFour.createCell(j++);
        cell24.setCellStyle(styleCenter);
        cell24.setCellValue((String) footMap.get("graduationTheorySecondPassPercent") + "%");
        HSSFCell cell25 = rowFour.createCell(j++);
        cell25.setCellStyle(styleCenter);
        cell25.setCellValue((String) footMap.get("doctorSkillSecondPassPercent") + "%");
        HSSFCell cell26 = rowFour.createCell(j++);
        cell26.setCellStyle(styleCenter);
        cell26.setCellValue(footMap.get("graduationSkillSecondPassPercent") + "%");
        HSSFCell cell27 = rowFour.createCell(j++);
        cell27.setCellStyle(styleCenter);
        cell27.setCellValue(footMap.get("secondPassPercent") + "%");
        HSSFCell cell28 = rowFour.createCell(j++);
        cell28.setCellStyle(styleCenter);
        cell28.setCellValue(footMap.get("doctorTheoryPassPercent") + "%");
        HSSFCell cell29 = rowFour.createCell(j++);
        cell29.setCellStyle(styleCenter);
        cell29.setCellValue(footMap.get("graduationTheoryPassPercent") + "%");
        HSSFCell cell30 = rowFour.createCell(j++);
        cell30.setCellStyle(styleCenter);
        cell30.setCellValue(footMap.get("doctorSkillPassPercent") + "%");
        HSSFCell cell31 = rowFour.createCell(j++);
        cell31.setCellStyle(styleCenter);
        cell31.setCellValue(footMap.get("graduationSkillPassPercent") + "%");
        HSSFCell cell32 = rowFour.createCell(j++);
        cell32.setCellStyle(styleCenter);
        cell32.setCellValue(footMap.get("PassPercent") + "%");
    }

    private void exportForBodyPart(HSSFSheet sheet, HSSFCellStyle styleCenter, List<Map<String, Object>> resultList) {
        for (int i = 0; i < resultList.size(); i++) {
            int j = 0;
            HSSFRow rowFour = sheet.createRow(i + 3); //第4行
            HSSFCell cell1 = rowFour.createCell(j++);
            cell1.setCellStyle(styleCenter);
            cell1.setCellValue((String) resultList.get(i).get("orgCityName"));
            HSSFCell cell2 = rowFour.createCell(j++);
            cell2.setCellStyle(styleCenter);
            cell2.setCellValue((String) resultList.get(i).get("orgName"));
            HSSFCell cell3 = rowFour.createCell(j++);
            cell3.setCellStyle(styleCenter);
            cell3.setCellValue((Integer) resultList.get(i).getOrDefault("Theory", 0));
            HSSFCell cell4 = rowFour.createCell(j++);
            cell4.setCellStyle(styleCenter);
            cell4.setCellValue((Integer) resultList.get(i).getOrDefault("Skill", 0));
            HSSFCell cell5 = rowFour.createCell(j++);
            cell5.setCellStyle(styleCenter);
            cell5.setCellValue((Integer) resultList.get(i).getOrDefault("all", 0));
            HSSFCell cell6 = rowFour.createCell(j++);
            cell6.setCellStyle(styleCenter);
            cell6.setCellValue((Integer) resultList.get(i).getOrDefault("realExamTheory", 0));
            HSSFCell cell7 = rowFour.createCell(j++);
            cell7.setCellStyle(styleCenter);
            cell7.setCellValue((Integer) resultList.get(i).getOrDefault("realExamSkill", 0));
            HSSFCell cell8 = rowFour.createCell(j++);
            cell8.setCellStyle(styleCenter);
            cell8.setCellValue((Integer) resultList.get(i).getOrDefault("realExamAll", 0));
            HSSFCell cell9 = rowFour.createCell(j++);
            cell9.setCellStyle(styleCenter);
            cell9.setCellValue((Integer) resultList.get(i).getOrDefault("realExamGraduationTheoryFirst", 0) + (Integer) resultList.get(i).getOrDefault("realExamDoctorTheoryFirst", 0));
            HSSFCell cell10 = rowFour.createCell(j++);
            cell10.setCellStyle(styleCenter);
            cell10.setCellValue((Integer) resultList.get(i).getOrDefault("realExamGraduationSkillFirst", 0) + (Integer) resultList.get(i).getOrDefault("realExamDoctorSkillFirst", 0));
            HSSFCell cell11 = rowFour.createCell(j++);
            cell11.setCellStyle(styleCenter);
            cell11.setCellValue((Integer) resultList.get(i).getOrDefault("firstExam", 0));
            HSSFCell cell12 = rowFour.createCell(j++);
            cell12.setCellStyle(styleCenter);
            cell12.setCellValue((Integer) resultList.get(i).getOrDefault("realExamGraduationTheorySecond", 0) + (Integer) resultList.get(i).getOrDefault("realExamDoctorTheorySecond", 0));
            HSSFCell cell13 = rowFour.createCell(j++);
            cell13.setCellStyle(styleCenter);
            cell13.setCellValue((Integer) resultList.get(i).getOrDefault("realExamGraduationSkillSecond", 0) + (Integer) resultList.get(i).getOrDefault("realExamDoctorSkillSecond", 0));
            HSSFCell cell14 = rowFour.createCell(j++);
            cell14.setCellStyle(styleCenter);
            cell14.setCellValue((Integer) resultList.get(i).getOrDefault("secondExam", 0));
            HSSFCell cell15 = rowFour.createCell(j++);
            cell15.setCellStyle(styleCenter);
            cell15.setCellValue((Integer) resultList.get(i).getOrDefault("missTheory", 0));
            HSSFCell cell16 = rowFour.createCell(j++);
            cell16.setCellStyle(styleCenter);
            cell16.setCellValue((Integer) resultList.get(i).getOrDefault("missSkill", 0));
            HSSFCell cell17 = rowFour.createCell(j++);
            cell17.setCellStyle(styleCenter);
            cell17.setCellValue((Integer) resultList.get(i).getOrDefault("missAll", 0));
            HSSFCell cell18 = rowFour.createCell(j++);
            cell18.setCellStyle(styleCenter);
            cell18.setCellValue(resultList.get(i).get("doctorTheoryFirstPassPercent") + "%");
            HSSFCell cell19 = rowFour.createCell(j++);
            cell19.setCellStyle(styleCenter);
            cell19.setCellValue(resultList.get(i).get("graduationTheoryFirstPassPercent") + "%");
            HSSFCell cell20 = rowFour.createCell(j++);
            cell20.setCellStyle(styleCenter);
            cell20.setCellValue(resultList.get(i).get("doctorSkillFirstPassPercent") + "%");
            HSSFCell cell21 = rowFour.createCell(j++);
            cell21.setCellStyle(styleCenter);
            cell21.setCellValue(resultList.get(i).get("graduationSkillFirstPassPercent") + "%");
            HSSFCell cell22 = rowFour.createCell(j++);
            cell22.setCellStyle(styleCenter);
            cell22.setCellValue(resultList.get(i).get("firstPassPercent") + "%");
            HSSFCell cell23 = rowFour.createCell(j++);
            cell23.setCellStyle(styleCenter);
            cell23.setCellValue(resultList.get(i).get("doctorTheorySecondPassPercent") + "%");
            HSSFCell cell24 = rowFour.createCell(j++);
            cell24.setCellStyle(styleCenter);
            cell24.setCellValue(resultList.get(i).get("graduationTheorySecondPassPercent") + "%");
            HSSFCell cell25 = rowFour.createCell(j++);
            cell25.setCellStyle(styleCenter);
            cell25.setCellValue(resultList.get(i).get("doctorSkillSecondPassPercent") + "%");
            HSSFCell cell26 = rowFour.createCell(j++);
            cell26.setCellStyle(styleCenter);
            cell26.setCellValue(resultList.get(i).get("graduationSkillSecondPassPercent") + "%");
            HSSFCell cell27 = rowFour.createCell(j++);
            cell27.setCellStyle(styleCenter);
            cell27.setCellValue(resultList.get(i).get("secondPassPercent") + "%");
            HSSFCell cell28 = rowFour.createCell(j++);
            cell28.setCellStyle(styleCenter);
            cell28.setCellValue(resultList.get(i).get("doctorTheoryPassPercent") + "%");
            HSSFCell cell29 = rowFour.createCell(j++);
            cell29.setCellStyle(styleCenter);
            cell29.setCellValue(resultList.get(i).get("graduationTheoryPassPercent") + "%");
            HSSFCell cell30 = rowFour.createCell(j++);
            cell30.setCellStyle(styleCenter);
            cell30.setCellValue(resultList.get(i).get("doctorSkillPassPercent") + "%");
            HSSFCell cell31 = rowFour.createCell(j++);
            cell31.setCellStyle(styleCenter);
            cell31.setCellValue(resultList.get(i).get("graduationSkillPassPercent") + "%");
            HSSFCell cell32 = rowFour.createCell(j++);
            cell32.setCellStyle(styleCenter);
            cell32.setCellValue(resultList.get(i).get("PassPercent") + "%");
        }
    }

    private void designTableTitle(HSSFSheet sheet, HSSFCellStyle styleCenter, HSSFRow rowOne, HSSFRow rowTwo, HSSFRow rowThree) {
        // 表头设计
        CellRangeAddress cellRangeAddress;
        cellRangeAddress = new CellRangeAddress(0, 2, 0, 0);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle1 = rowOne.createCell(0);
        cellTitle1.setCellValue("城市划分");
        cellTitle1.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 2, 1, 1);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle2 = rowOne.createCell(1);
        cellTitle2.setCellValue("培训基地");
        cellTitle2.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 0, 2, 3);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle3 = rowOne.createCell(2);
        cellTitle3.setCellValue("应考人数");
        cellTitle3.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 2, 2);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle21 = rowTwo.createCell(2);
        cellTitle21.setCellValue("理论考试");
        cellTitle21.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 3, 3);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle22 = rowTwo.createCell(3);
        cellTitle22.setCellValue("技能考试");
        cellTitle22.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 2, 4, 4);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle4 = rowOne.createCell(4);
        cellTitle4.setCellValue("合计");
        cellTitle4.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 0, 5, 6);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle5 = rowOne.createCell(5);
        cellTitle5.setCellValue("实考人数");
        cellTitle5.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 5, 5);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle23 = rowTwo.createCell(5);
        cellTitle23.setCellValue("理论考试");
        cellTitle23.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 6, 6);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle24 = rowTwo.createCell(6);
        cellTitle24.setCellValue("技能考试");
        cellTitle24.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 2, 7, 7);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle6 = rowOne.createCell(7);
        cellTitle6.setCellValue("合计");
        cellTitle6.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 0, 8, 9);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle7 = rowOne.createCell(8);
        cellTitle7.setCellValue("首考人数");
        cellTitle7.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 8, 8);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle25 = rowTwo.createCell(8);
        cellTitle25.setCellValue("理论考试");
        cellTitle25.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 9, 9);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle26 = rowTwo.createCell(9);
        cellTitle26.setCellValue("技能考试");
        cellTitle26.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 2, 10, 10);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle8 = rowOne.createCell(10);
        cellTitle8.setCellValue("合计");
        cellTitle8.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 0, 11, 12);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle9 = rowOne.createCell(11);
        cellTitle9.setCellValue("补考人数");
        cellTitle9.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 11, 11);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle27 = rowTwo.createCell(11);
        cellTitle27.setCellValue("理论考试");
        cellTitle27.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 12, 12);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle28 = rowTwo.createCell(12);
        cellTitle28.setCellValue("技能考试");
        cellTitle28.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 2, 13, 13);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle10 = rowOne.createCell(13);
        cellTitle10.setCellValue("合计");
        cellTitle10.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 0, 14, 15);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle11 = rowOne.createCell(14);
        cellTitle11.setCellValue("缺考人数");
        cellTitle11.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 14, 14);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle29 = rowTwo.createCell(14);
        cellTitle29.setCellValue("理论考试");
        cellTitle29.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 2, 15, 15);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle30 = rowTwo.createCell(15);
        cellTitle30.setCellValue("技能考试");
        cellTitle30.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 2, 16, 16);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle12 = rowOne.createCell(16);
        cellTitle12.setCellValue("合计");
        cellTitle12.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 0, 17, 20);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle13 = rowOne.createCell(17);
        cellTitle13.setCellValue("首考通过率");
        cellTitle13.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 1, 17, 18);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle31 = rowTwo.createCell(17);
        cellTitle31.setCellValue("理论考试");
        cellTitle31.setCellStyle(styleCenter);
        HSSFCell cellTitle41 = rowThree.createCell(17);
        cellTitle41.setCellValue("住院医师");
        cellTitle41.setCellStyle(styleCenter);
        HSSFCell cellTitle42 = rowThree.createCell(18);
        cellTitle42.setCellValue("在校专硕");
        cellTitle42.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 1, 19, 20);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle32 = rowTwo.createCell(19);
        cellTitle32.setCellValue("技能考试");
        cellTitle32.setCellStyle(styleCenter);
        HSSFCell cellTitle43 = rowThree.createCell(19);
        cellTitle43.setCellValue("住院医师");
        cellTitle43.setCellStyle(styleCenter);
        HSSFCell cellTitle44 = rowThree.createCell(20);
        cellTitle44.setCellValue("在校专硕");
        cellTitle44.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 2, 21, 21);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle14 = rowOne.createCell(21);
        cellTitle14.setCellValue("平均通过率");
        cellTitle14.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 0, 22, 25);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle15 = rowOne.createCell(22);
        cellTitle15.setCellValue("补考通过率");
        cellTitle15.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 1, 22, 23);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle33 = rowTwo.createCell(22);
        cellTitle33.setCellValue("理论考试");
        cellTitle33.setCellStyle(styleCenter);
        HSSFCell cellTitle45 = rowThree.createCell(22);
        cellTitle45.setCellValue("住院医师");
        cellTitle45.setCellStyle(styleCenter);
        HSSFCell cellTitle46 = rowThree.createCell(23);
        cellTitle46.setCellValue("在校专硕");
        cellTitle46.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 1, 24, 25);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle34 = rowTwo.createCell(24);
        cellTitle34.setCellValue("技能考试");
        cellTitle34.setCellStyle(styleCenter);
        HSSFCell cellTitle47 = rowThree.createCell(24);
        cellTitle47.setCellValue("住院医师");
        cellTitle47.setCellStyle(styleCenter);
        HSSFCell cellTitle48 = rowThree.createCell(25);
        cellTitle48.setCellValue("在校专硕");
        cellTitle48.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 2, 26, 26);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle16 = rowOne.createCell(26);
        cellTitle16.setCellValue("平均通过率");
        cellTitle16.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 0, 27, 30);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle17 = rowOne.createCell(27);
        cellTitle17.setCellValue("综合通过率");
        cellTitle17.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 1, 27, 28);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle35 = rowTwo.createCell(27);
        cellTitle35.setCellValue("理论考试");
        cellTitle35.setCellStyle(styleCenter);
        HSSFCell cellTitle49 = rowThree.createCell(27);
        cellTitle49.setCellValue("住院医师");
        cellTitle49.setCellStyle(styleCenter);
        HSSFCell cellTitle50 = rowThree.createCell(28);
        cellTitle50.setCellValue("在校专硕");
        cellTitle50.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(1, 1, 29, 30);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle36 = rowTwo.createCell(29);
        cellTitle36.setCellValue("技能考试");
        cellTitle36.setCellStyle(styleCenter);
        HSSFCell cellTitle51 = rowThree.createCell(29);
        cellTitle51.setCellValue("住院医师");
        cellTitle51.setCellStyle(styleCenter);
        HSSFCell cellTitle52 = rowThree.createCell(30);
        cellTitle52.setCellValue("在校专硕");
        cellTitle52.setCellStyle(styleCenter);
        cellRangeAddress = new CellRangeAddress(0, 2, 31, 31);
        sheet.addMergedRegion(cellRangeAddress);
        HSSFCell cellTitle18 = rowOne.createCell(31);
        cellTitle18.setCellValue("平均通过率");
        cellTitle18.setCellStyle(styleCenter);
    }

    @RequestMapping("/examStatistics")
    public String examStatistics(Model model, String tabTag, String catSpeId) {
        model.addAttribute("tabTag", tabTag);
        model.addAttribute("catSpeId", catSpeId);
        return "jsres/global/examStatistics";
    }

    @RequestMapping("/examStatisticsList")
    @ResponseBody
    public List examStatisticsList(String yearDate, String tabTag, String catSpeId) {
        List<DoctorExamStatisticsExt> bList = new ArrayList<>();
        Map<String, Object> param = new HashMap<>();
        param.put("yearDate", yearDate);
        param.put("catSpeId", catSpeId);
        if ("Org".equals(tabTag)) {
            List<DoctorExamStatisticsExt> orgList = graduationBiz.searchExamStatisticsList(param);
            bList.addAll(orgList);
            List<DoctorExamStatisticsExt> jointOrgList = graduationBiz.searchExamStatisticsList2(param);
            bList.addAll(jointOrgList);
        } else if ("Spe".equals(tabTag)) {
            List<DoctorExamStatisticsExt> speList = graduationBiz.searchExamStatisticsListBySpe(param);
            bList.addAll(speList);
        } else if ("City".equals(tabTag)) {
            List<DoctorExamStatisticsExt> cityList = graduationBiz.searchExamStatisticsListByCity(param);
            List<DoctorExamStatisticsExt> cityList2 = graduationBiz.searchExamStatisticsListByCity2(param);
            Map<String,DoctorExamStatisticsExt> map = new HashMap<>();
            if(null != cityList2 && cityList2.size()>0){
                for (DoctorExamStatisticsExt ext:cityList2) {
                    map.put(ext.getId(),ext);
                }
            }
            if(null != cityList && cityList.size()>0){
                for (DoctorExamStatisticsExt ext:cityList) {
                    if(null != map.get(ext.getId())) {
                        Integer examTotal = Integer.parseInt(ext.getExamTotal());
                        Integer realTotal = Integer.parseInt(ext.getRealTotal());
                        Integer fristExamTotal = Integer.parseInt(ext.getFristExamTotal());
                        Integer secondExamTotal = Integer.parseInt(ext.getSecondExamTotal());
                        Integer missExamTotal = Integer.parseInt(ext.getMissExamTotal());

                        Integer examTotal2 = Integer.parseInt(map.get(ext.getId()).getExamTotal());
                        Integer realTotal2 = Integer.parseInt(map.get(ext.getId()).getRealTotal());
                        Integer fristExamTotal2 = Integer.parseInt(map.get(ext.getId()).getFristExamTotal());
                        Integer secondExamTotal2 = Integer.parseInt(map.get(ext.getId()).getSecondExamTotal());
                        Integer missExamTotal2 = Integer.parseInt(map.get(ext.getId()).getMissExamTotal());

                        ext.setExamTotal(Integer.toString(examTotal + examTotal2));
                        ext.setRealTotal(Integer.toString(realTotal + realTotal2));
                        ext.setFristExamTotal(Integer.toString(fristExamTotal + fristExamTotal2));
                        ext.setSecondExamTotal(Integer.toString(secondExamTotal + secondExamTotal2));
                        ext.setMissExamTotal(Integer.toString(missExamTotal + missExamTotal2));
                        bList.add(ext);
                    }
                }
            }else{
                bList.addAll(cityList);
            }
        }
        return bList;
    }
}
