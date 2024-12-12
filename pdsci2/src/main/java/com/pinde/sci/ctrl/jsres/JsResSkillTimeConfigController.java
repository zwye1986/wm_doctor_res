package com.pinde.sci.ctrl.jsres;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.enums.osca.AuditStatusEnum;
import com.pinde.core.common.enums.osca.DoctorScoreEnum;
import com.pinde.core.common.enums.osca.SignStatusEnum;
import com.pinde.core.model.ResDoctorRecruit;
import com.pinde.core.model.SysDict;
import com.pinde.core.model.SysOrg;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.pdf.DocumentVo;
import com.pinde.core.pdf.PdfDocumentGenerator;
import com.pinde.core.pdf.utils.ResourceLoader;
import com.pinde.core.util.*;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.osca.IOscaBaseBiz;
import com.pinde.sci.biz.osca.IOscaDoctorOrderdeBiz;
import com.pinde.sci.biz.osca.IOscaDoctorScoreBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.osca.OscaCheckInfoExt;
import com.pinde.sci.model.osca.OscaSkillsAssessmentExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/jsres/skillTimeConfig")
public class JsResSkillTimeConfigController extends GeneralController {
    @Autowired
    private IResSkillTimeConfigBiz skillTimeConfigBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResSkillConfigBiz skillConfigBiz;
    @Autowired
    private IOscaBaseBiz baseBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IOscaDoctorOrderdeBiz oscaDoctorOrderdeBiz;
    @Autowired
    private IOscaDoctorScoreBiz oscaDoctorScoreBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IJsResBaseBiz resbaseBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IJsResGraduationApplyBiz jsresGraduationApplyBiz;

    private static Logger logger = LoggerFactory.getLogger(JsResSkillTimeConfigController.class);


    @RequestMapping("/main")
    public String main(Model model) {
        List<ResSkillTimeConfig> skillTimeConfigs = skillTimeConfigBiz.findAll();
        Map<String, Boolean> map = new HashMap<>();
        String currDateTime = DateUtil.getCurrDateTime2();
        for (ResSkillTimeConfig skillTimeConfig : skillTimeConfigs) {
            if (currDateTime.compareTo(skillTimeConfig.getTestEndTime()) > 0) {
                map.put(skillTimeConfig.getSkillTimeFlow(), true);
            } else {
                map.put(skillTimeConfig.getSkillTimeFlow(), false);
            }
        }
        model.addAttribute("map", map);
        model.addAttribute("resSkillTimeConfigs", skillTimeConfigs);
        return "jsres/completeCourse/skillTimeConfig/main";
    }

    @RequestMapping("/addTest")
    public String addTest(Model model, String skillTimeFlow, String flag, HttpServletRequest request) {
        if (StringUtil.isNotBlank(skillTimeFlow)) {
            ResSkillTimeConfig resSkillTimeConfig = skillTimeConfigBiz.findOne(skillTimeFlow);
            String[] split = resSkillTimeConfig.getCitysId().split(",");
            List<String> cityList = Arrays.asList(split);
            model.addAttribute("cityList", cityList);
            model.addAttribute("resSkillTimeConfig", resSkillTimeConfig);
        }
        if (StringUtil.isNotBlank(flag)) {
            model.addAttribute("flag", flag);
        }
        List<Map<String, String>> citys = skillTimeConfigBiz.getAllCitys();
        model.addAttribute("citys", citys);
        return "jsres/completeCourse/skillTimeConfig/addTest";
    }

    @RequestMapping("/insertTest")
    @ResponseBody
    public String insertTest(ResSkillTimeConfig resSkillTimeConfig) {
        if (skillTimeConfigBiz.checkTestExist(resSkillTimeConfig)) {
            if (skillTimeConfigBiz.insert(resSkillTimeConfig) == com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
            } else {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }
        } else {
            return "当前时间段有进行中的考试，请修改考试时间";
        }
    }

    @RequestMapping("/closeTest")
    @ResponseBody
    public String closeTest(String skillTimeFlow) {
        if (StringUtil.isNotBlank(skillTimeFlow)) {
            if (skillTimeConfigBiz.closeTest(skillTimeFlow) == com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
            } else {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
            }
        } else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }

    @RequestMapping("/skillManage")
    public String skillManage(){
        return "jsres/city/skillConfig/skillMain";
    }

    @RequestMapping("/skillList")
    public String skillList(Model model, String testName, String testId, HttpServletRequest request) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        String cityId = org.getOrgCityId();
        model.addAttribute("cityId",cityId);
        Map<String,String> param = new HashMap<>();
        param.put("cityId",cityId);
        param.put("testName",testName);
        param.put("testId",testId);
        List<ResSkillConfig> skillConfigs = skillConfigBiz.searchSkillList(param);
        model.addAttribute("skillConfigs",skillConfigs);
        //查询当天省厅技能考核配置
        ResSkillTimeConfig skillTimeConfig = skillTimeConfigBiz.findOneByCurrDate(DateUtil.getCurrDateTime2());
        model.addAttribute("skillTimeConfig",skillTimeConfig);
        if(null == skillTimeConfig){
            model.addAttribute("addFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
        }else{
            String[] split = skillTimeConfig.getCitysId().split(",");
            List<String> cityList = Arrays.asList(split);
            if(cityList.contains(cityId)) {
                model.addAttribute("addFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }else{
                model.addAttribute("addFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
        }
        return "jsres/city/skillConfig/skillList";
    }

    @RequestMapping("/addSkillConfig")
    public String addSkillConfig(Model model, String skillFlow,String type, HttpServletRequest request) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        String cityId = org.getOrgCityId();
        model.addAttribute("cityId",cityId);
        //查询当天省厅技能考核配置
        ResSkillTimeConfig skillTimeConfig = skillTimeConfigBiz.findOneByCurrDate(DateUtil.getCurrDateTime2());
        model.addAttribute("skillTimeConfig",skillTimeConfig);
        if(null == skillTimeConfig){
            model.addAttribute("flag", com.pinde.core.common.GlobalConstant.FLAG_N);
        }else{
            String[] split = skillTimeConfig.getCitysId().split(",");
            List<String> cityList = Arrays.asList(split);
            if(cityList.contains(cityId)) {
                model.addAttribute("flag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }else{
                model.addAttribute("flag", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
        }
        if (StringUtil.isNotBlank(skillFlow)) {
            ResSkillConfig resSkillConfig = skillConfigBiz.findOneSkillConfig(skillFlow);
            List<ResSkillOrg> orgList = skillConfigBiz.searchSkillOrgs(skillFlow);
            List<String> orgFlows = new ArrayList<>();
            if(null != orgList && orgList.size() > 0){
                for (ResSkillOrg rso:orgList) {
                    if(!orgFlows.contains(rso.getOrgFlow())){
                        orgFlows.add(rso.getOrgFlow());
                    }
                }
            }
            model.addAttribute("orgList", orgFlows);
            model.addAttribute("resSkillConfig", resSkillConfig);
        }

        SysOrg org2 = new SysOrg();
        org2.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        org2.setOrgCityId(cityId);
        List<SysOrg> orgs = orgBiz.searchOrg(org2);
        model.addAttribute("orgs", orgs);
        if(type.equals("edit")) {
            return "jsres/city/skillConfig/editSkillConfig";
        }
        return "jsres/city/skillConfig/addSkillConfig";
    }

    @RequestMapping("/insertSkillConfig")
    @ResponseBody
    public String insertSkillConfig(ResSkillConfig skillConfig,String[] orgFlows){
        int num = skillConfigBiz.saveResSkillConfig(skillConfig,orgFlows);
        if(num == -1){
            return "当前时间段有进行中的考试，请修改考试时间";
        }
        if(num > 0) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping("/deleteSkillConfig")
    @ResponseBody
    public String deleteSkillConfig(String skillFlow) {
        if (StringUtil.isNotBlank(skillFlow)) {
            if (skillConfigBiz.deleteSkillConfig(skillFlow) == com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
            } else {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
            }
        } else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }

    @RequestMapping("/doctorList")
    public String doctorList(Model model, String doctorName, String idNo,Integer currentPage, HttpServletRequest request) {
        Map<String,String> param = new HashMap<>();
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        String cityId = org.getOrgCityId();
        model.addAttribute("cityId",cityId);
        param.put("cityId",cityId);
        param.put("doctorName",doctorName);
        param.put("idNo",idNo);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<ResDoctorSkill> doctorSkillList = skillConfigBiz.searchDoctorSkillList(param);
        model.addAttribute("doctorSkillList",doctorSkillList);

        //查询当天省厅技能考核配置
        ResSkillTimeConfig skillTimeConfig = skillTimeConfigBiz.findOneByCurrDate(DateUtil.getCurrDateTime2());
        if(null == skillTimeConfig){
            model.addAttribute("flag",'N');
        }else{
            String[] split = skillTimeConfig.getCitysId().split(",");
            List<String> cityList = Arrays.asList(split);
            if(cityList.contains(cityId)) {
                model.addAttribute("flag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }else{
                model.addAttribute("flag", com.pinde.core.common.GlobalConstant.FLAG_N);
            }
        }
        return  "jsres/city/skillConfig/doctorList";
    }

    @RequestMapping(value = "/showImportDoc")
    public String showImportDoc(Model model) {
        return "jsres/city/skillConfig/importDocSkill";
    }

    @RequestMapping("/importDocSkill")
    @ResponseBody
    public Object importDocSkill(MultipartFile file) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        String cityId = org.getOrgCityId();
        //查询当天省厅技能考核配置
        ResSkillTimeConfig skillTimeConfig = skillTimeConfigBiz.findOneByCurrDate(DateUtil.getCurrDateTime2());
        if(null == skillTimeConfig){
            return "暂未设置技能考核时间,无法导入！";
        }
        if (file.getSize() > 0) {
            try {
                int result = skillTimeConfigBiz.importDocSkillExcel(file,cityId,skillTimeConfig.getTestId());
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED;
                } else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }

    @RequestMapping("/exportDocSkill")
    public void exportDocSkill(String doctorName, String idNo, HttpServletResponse response) throws Exception{
        Map<String,String> param = new HashMap<>();
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        String cityId = org.getOrgCityId();
        param.put("cityId",cityId);
        param.put("doctorName",doctorName);
        param.put("idNo",idNo);
        List<ResDoctorSkill> doctorSkillList = skillConfigBiz.searchDoctorSkillList(param);

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

        HSSFRow rowThree = sheet.createRow(0);
        String []titles = new String[]{
                "考试编号",
                "姓名",
                "证件号码",
                "培训专业",
                "准考证号",
                "考试地点",
                "考试时间",
                "准考证标题",
                "考点联系电话",
                "注意事项"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowThree.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 1 * 156);
        }

        int rowNum = 1;
        String[] dataList = null;
        if (doctorSkillList != null && !doctorSkillList.isEmpty()) {
            for (int i = 0; i < doctorSkillList.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                dataList = new String[]{
                        doctorSkillList.get(i).getTestId(),
                        doctorSkillList.get(i).getDoctorName(),
                        doctorSkillList.get(i).getIdNo(),
                        doctorSkillList.get(i).getSpeName(),
                        doctorSkillList.get(i).getTicketNumber(),
                        doctorSkillList.get(i).getSkillOrgName(),
                        doctorSkillList.get(i).getSkillTime(),
                        doctorSkillList.get(i).getTestName(),
                        doctorSkillList.get(i).getSkillOrgPhone(),
                        doctorSkillList.get(i).getSkillNote()
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(dataList[j]);
                }
            }
        }
        String fileName = "技能考核导入学员表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @RequestMapping("/skillTestManage")
    public String skillTestManage(Model model,String testId,String clinicalName,String speId,Integer currentPage, HttpServletRequest request) {
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        //查询当天市局技能考核配置
        ResSkillConfig skillConfig = skillConfigBiz.findOneByCurrDate(DateUtil.getCurrDateTime2(),org.getOrgCityId());
        if(null == skillConfig){
            model.addAttribute("addFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
        }else{
            //如果是导入方式  则市局下所有基地都能新增技能考核
            if("export".equals(skillConfig.getSkillWay())){
                model.addAttribute("addFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
            }else {
                ResSkillOrg skillOrg = skillConfigBiz.searchSkillOrg(skillConfig.getSkillFlow(), orgFlow);
                if (null == skillOrg) {
                    model.addAttribute("addFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
                } else {
                    model.addAttribute("addFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
                }
            }
        }
        Map<String,Object> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("testId",testId);
        param.put("clinicalName",clinicalName);
        param.put("speId",speId);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> dataList = baseBiz.queryDataList(param);
        model.addAttribute("dataList",dataList);
        return "jsres/hospital/skillManage/skillTestList";
    }

    @RequestMapping(value="/addCheckInfo")
    public String addCheckInfo(String clinicalFlow,String flag,String addFlag, Model model){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        //查询当天市局技能考核配置
        ResSkillConfig skillConfig = skillConfigBiz.findOneByCurrDate(DateUtil.getCurrDateTime2(),org.getOrgCityId());
        model.addAttribute("skillConfig",skillConfig);
        if(StringUtil.isNotBlank(clinicalFlow)){
            OscaSkillsAssessment osa = baseBiz.queryDataByFlow(clinicalFlow);
            List<OscaSkillsAssessmentTime> times=baseBiz.queryOsaTimeList(clinicalFlow);
            List<String> flowList=baseBiz.queryDoctorFlowList(clinicalFlow,null);
            model.addAttribute("osa",osa);
            model.addAttribute("times",times);
            model.addAttribute("passedNum",flowList==null?0:flowList.size());
        }
        model.addAttribute("flag",flag);
        model.addAttribute("addFlag",addFlag);
        return "jsres/hospital/skillManage/addCheckInfo";
    }

    @RequestMapping(value="/querySpeRelation")
    @ResponseBody
    public List<OscaSubjectMain> querySpeRelation(String speId, String actionTypeId){
        return baseBiz.querySpeRelation(speId,actionTypeId);
    }

    @RequestMapping(value="/queryInitSpe")
    @ResponseBody
    public List<OscaOrgSpe> queryInitSpe(){
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        return baseBiz.queryInitSpe(orgFlow);
    }

    @RequestMapping("/saveCheckInfo")
    @ResponseBody
    public String saveCheckInfo(OscaSkillsAssessment osa, String jsondata){
        List<OscaSkillsAssessmentTime> times=new ArrayList<>();
        Map<String,Object> mp = JSON.parseObject(jsondata,Map.class);
        List<Map<String,String>> timeList=(List<Map<String,String>>)mp.get("timeList");
        if(timeList!=null&&timeList.size()>0){
            for (Map<String,String> tempMap:timeList) {
                OscaSkillsAssessmentTime t=new OscaSkillsAssessmentTime();
                t.setRecrodFlow(tempMap.get("recrodFlow"));
                t.setExamStartTime(tempMap.get("examStartTime"));
                t.setExamEndTime(tempMap.get("examEndTime"));
                t.setTestNumber(tempMap.get("testNumber"));
                times.add(t);
            }
        }
        int num = baseBiz.saveCheckInfoNew(osa,times);
        if (num == com.pinde.core.common.GlobalConstant.ONE_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping("/checkInfoManage")
    public String checkInfoManage(Integer currentPage1, String auditStatusId, String appointDoctorName, String year,
                                  String clinicalFlow, String clinicalName, String speName, String sessionId,
                                  String initFlag, HttpServletRequest request, Model model, String isLocal) throws IOException {
        Map<String,String> param = new HashMap<>();
        param.put("auditStatusId",auditStatusId);
        param.put("doctorName",appointDoctorName);
        param.put("clinicalFlow",clinicalFlow);
        param.put("sessionId",sessionId);
        param.put("year", year);
        clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
        speName = java.net.URLDecoder.decode(speName,"UTF-8");
        PageHelper.startPage(currentPage1,getPageSize(request));
        List<OscaCheckInfoExt> appointList = baseBiz.queryAppointList(param);
        model.addAttribute("appointList",appointList);
        model.addAttribute("clinicalName",clinicalName);
        model.addAttribute("speName",speName);
        model.addAttribute("isLocal",isLocal);
        model.addAttribute("year",year);
        OscaSkillsAssessment oscaSkillsAssessment = baseBiz.queryDataByFlow(clinicalFlow);
        model.addAttribute("isGradeReleased",oscaSkillsAssessment.getIsGradeReleased());
        if (StringUtil.isNotBlank(initFlag) && initFlag.equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)) {
            return "jsres/hospital/skillManage/checkInfoManage";
        }
        return "jsres/hospital/skillManage/appointManage";
    }

    @RequestMapping(value="/toImportStudents")
    public String toImportStudents(String clinicalFlow,Model model){
        //查询考核时间段
        List<OscaSkillsAssessmentTime> skillsAssessmentTimes = baseBiz.getAssessmentTimes(clinicalFlow);
        model.addAttribute("skillsAssessmentTimes",skillsAssessmentTimes);
        return "jsres/hospital/skillManage/importStudent";
    }

    @RequestMapping(value="/importStudents")
    @ResponseBody
    public String importStudents(MultipartFile file, String clinicalFlow, String time, String startTime, String endTime, String timeFlow){
        String examStartTime = "";
        String examEndTime = "";
        String isNewTime = "";
        if(StringUtil.isNotBlank(time)&&(!"add".equals(time))){
            examStartTime = time.substring(0,16);
            examEndTime = time.substring(17,33);
            isNewTime = timeFlow;
        }else{
            examStartTime = startTime;
            examEndTime = endTime;
            isNewTime = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        if(file.getSize() > 0){
            try{
                int result = baseBiz.importStudentsNew(file,clinicalFlow,examStartTime,examEndTime,isNewTime);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
                }else{
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
            }
        }
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
    }

    @RequestMapping("/roomManage")
    public String roomManage(String clinicalFlow,String clinicalName,String speName,Model model) throws IOException{
        clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
        speName = java.net.URLDecoder.decode(speName,"UTF-8");
        Map<String,Map<String,Object>> stationMap = new LinkedHashMap<>();
        List<Map<String,Object>> roomList = baseBiz.queryRoomList(clinicalFlow);
        if(null!=roomList && roomList.size()>0){
            for(Map<String,Object> room : roomList){
                String key = (String)room.get("STATION_FLOW");
                if(!stationMap.containsKey(key)){
                    stationMap.put(key,room);
                }
            }
            model.addAttribute("stationMap",stationMap);
        }
        model.addAttribute("roomList",roomList);
        model.addAttribute("clinicalName",clinicalName);
        model.addAttribute("speName",speName);
        return "jsres/hospital/skillManage/roomManage";
    }

    @RequestMapping(value="/addRoom")
    public String addRoom(String clinicalFlow,String clinicalName,String subjectFlow,String recordFlow,String flag,String stationFlow,Model model) throws IOException{
        clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
        if(StringUtil.isNotBlank(recordFlow)){
            OscaSkillRoom osr = baseBiz.queryRoomByFlow(recordFlow);
            model.addAttribute("osr",osr);
            List<OscaSkillRoomTea> teaList = baseBiz.queryRoomTeaList(recordFlow);
            model.addAttribute("teaList",teaList);
        }
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId("ExamRoom");
        sysDict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> examRoomList = dictBiz.searchDictList(sysDict);
        List<OscaSubjectStation> stationList = baseBiz.queryStationList(subjectFlow);
        model.addAttribute("examRoomList",examRoomList);
        model.addAttribute("stationList",stationList);
        model.addAttribute("clinicalFlow",clinicalFlow);
        model.addAttribute("clinicalName",clinicalName);
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(flag)) {
            model.addAttribute("stationFlow",stationFlow);
            return "jsres/hospital/skillManage/addRoomNew";
        }
        return "jsres/hospital/skillManage/addRoom";
    }

    @RequestMapping(value="/roomTeacher")
    public String roomTeacher(String userName,String stationName,String [] exitTeaLst,Model model)throws IOException{
        stationName = java.net.URLDecoder.decode(stationName,"UTF-8");
        List<SysUser> teaList = baseBiz.queryTeaList(userName);
        model.addAttribute("teaList",teaList);
        model.addAttribute("stationName",stationName);
        model.addAttribute("exitTeaLst",exitTeaLst==null?exitTeaLst:Arrays.asList(exitTeaLst));
        return "jsres/hospital/skillManage/roomTeacher";
    }

    @RequestMapping(value = "/downTicket")
    public void downTicket(String clinicalFlow,String[] recordFlows, final HttpServletResponse response,HttpServletRequest request) throws Exception {
        if(null != recordFlows && recordFlows.length > 0) {
            String outputFileClass = ResourceLoader.getPath("");
            String directoryName = new File(outputFileClass) + "/ticket/" + clinicalFlow + "/" ;
            List<String> recordFlowList = Arrays.asList(recordFlows);
            for (String recordFlow : recordFlowList) {
                OscaDoctorAssessment oda = oscaDoctorOrderdeBiz.selectDoctorAssessmentByRecordFlow(recordFlow);
                String doctorFlow = oda.getDoctorFlow();
                final String fileName = doctorFlow;
                String outputFile = directoryName + fileName + ".pdf";
                File file = new File(outputFile);
                if (file != null && !file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }
                //root 储存的数据
                final Map<String, Object> root = new HashMap<String, Object>();

                Map<String, String> doctorMap = new HashMap<>();
                doctorMap.put("doctorFlow", doctorFlow);
                doctorMap.put("clinicalFlow", clinicalFlow);
                List<OscaSkillsAssessmentExt> oscaSkillsAssessmentExt = oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
                String signUrl = "";
                OscaSkillsAssessmentExt osaExt = new OscaSkillsAssessmentExt();
                if (oscaSkillsAssessmentExt != null && oscaSkillsAssessmentExt.size() > 0) {
                    if (oscaSkillsAssessmentExt.get(0) != null && oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment() != null) {
                        signUrl = qrCode(clinicalFlow, oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment().getCodeInfo(), doctorFlow);
                        osaExt = oscaSkillsAssessmentExt.get(0);
                    }
                }
                String baseUrl = InitConfig.getSysCfg("upload_base_url");
                root.put("signUrl", baseUrl + "/" + signUrl);
                root.put("oscaSkillsAssessmentExt", osaExt);
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("clinicalFlow", clinicalFlow);
                List<OscaSubjectStation> subjectStations = oscaDoctorOrderdeBiz.getStations(paramMap);
                List<String> partFlows = new ArrayList<>();
                if (subjectStations != null && subjectStations.size() > 0) {
                    for (OscaSubjectStation station : subjectStations) {
                        String partFlow = station.getPartFlow();
                        if (!partFlows.contains(partFlow)) {
                            partFlows.add(partFlow);
                        }
                    }
                }
                root.put("subjectStations", subjectStations);
                root.put("partFlows", partFlows);
                DecimalFormat df = new DecimalFormat("#0.0%");
                double size = subjectStations.size();
                double w = 1 / size;
                String width = df.format(w);
                root.put("width", width);
                if(com.pinde.core.util.StringUtil.isBlank(osaExt.getSysUser().getUserHeadImg())){
                    root.put("headImg", baseUrl + "up-pic.jpg");
                }else {
                    root.put("headImg", baseUrl + osaExt.getSysUser().getUserHeadImg());
                }
                // 模板数据
                DocumentVo vo = new DocumentVo() {
                    @Override
                    public String findPrimaryKey() {
                        return fileName;
                    }

                    @Override
                    public Map<String, Object> fillDataMap() {
                        return root;
                    }
                };

                String template = "jsres/doctorTicketNew.html";
                PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
                // 生成pdf
                pdfGenerator.generate(template, vo, outputFile);
            }

            File directory = new File(directoryName);
            File zipFlie = new File("准考证.zip");
            String zipFolderName = "";
            ZipUtil.makeDirectoryToZip(directory, zipFlie, zipFolderName, 7);
            //4.输出
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFlie));
            byte[] dt = new byte[bis.available()];
            int len = 2048;
            byte[] b = new byte[len];
            response.reset();
            response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(zipFlie.getName().getBytes("gbk"), "ISO8859-1") + "\"");
            response.addHeader("Content-Length", "" + dt.length);
            response.setContentType("application/octet-stream;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            while ((len = bis.read(b)) != -1) {
                outputStream.write(b, 0, len);
            }
            bis.close();
            outputStream.flush();
            outputStream.close();
            if(directory.exists()&&directory.isFile()) {
                directory.delete();
            }
        }
    }

    private String qrCode(String clinicalFlow,String text,String doctorFlow) {
        int width = 140; // 二维码图片的宽
        int height = 140; // 二维码图片的高
        String format = "png"; // 二维码图片的格式
        String realPath = InitConfig.getSysCfg("upload_base_dir");
        File file = new File(realPath + File.separator + "clinicalFlow");
        if (!file.exists()) {
            file.mkdirs();
        }
        // 绝对路径
        String basePath = "clinicalFlow" + File.separator + doctorFlow;
        String path = realPath + File.separator + basePath + "." + format;
        try {
            QRCodeUtil.generateQRCode(text, width, height, format, path);
        } catch (Exception e) {
            logger.error("", e);
        }
        return basePath + "." + format;
    }

    @RequestMapping("/list")
    public String doctorOrderedList(String flag,OscaSkillsAssessmentExt oscaSkillsAssessmentExt,String isLocal, String liId,
                                    Integer currentPage, HttpServletRequest request,Model model){
        String orgName="";
        String searchNotFull="";
        String searchFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
        if(oscaSkillsAssessmentExt!=null){
            orgName=oscaSkillsAssessmentExt.getOrgName();
            searchNotFull=oscaSkillsAssessmentExt.getSearchNotFull();
        }
        List<String> speIdList=new ArrayList<>();
        List<String> speNameList=new ArrayList<>();
        String speId="";
        String orgFlow="";
        String graduationYear="";
        String trainingSpeName ="";
        ResDoctor resDoctor = resDoctorBiz.findByFlow(GlobalContext.getCurrentUser().getUserFlow());
        if(resDoctor!=null){
            Map<String,Object> map=new HashMap<>();
            //一阶段学员
            if("WMFirst".equals(resDoctor.getTrainingTypeId())){
                trainingSpeName=resDoctor.getTrainingSpeName();
                switch(trainingSpeName)
                {
                    case "医学检验科":
                        speNameList.add("检验医学科");
                        break;
                    case "医学影像科":
                        speNameList.add("放射科");
                        speNameList.add("放射肿瘤科");
                        speNameList.add("核医学科");
                        speNameList.add("超声医学科");
                        break;
                    case "病理科":
                        speNameList.add("临床病理科");
                        break;
                    case "皮肤性病科":
                        speNameList.add("皮肤科");
                        break;
                    case "口腔科":
                        speNameList.add("口腔全科");
                        break;
                    case "全科方向（西医）":
                        speNameList.add("全科");
                        break;
                    case "助理全科":
                        speNameList.add("助理全科");
                        break;
                    default:
                        speNameList.add(trainingSpeName);
                }
                map.put("speNameList",speNameList);
                speIdList=oscaDoctorOrderdeBiz.searchTrainingSpeList(map);
                if(speNameList.size()==0){
                    speIdList=new ArrayList<>();
                }
            }else if(StringUtil.isNotBlank(resDoctor.getTrainingSpeName())
                    &&"助理全科".equals(resDoctor.getTrainingSpeName())){
                // 修改 助理全科只查助理全科 信息  需求人：徐开宏 修改时间：2020年7月17日
                speNameList.add("助理全科");
                map.put("speNameList",speNameList);
                speIdList=oscaDoctorOrderdeBiz.searchTrainingSpeList(map);
            }else {
                speId=resDoctor.getTrainingSpeId();
            }
            if(speIdList.size()==0){
                speId=resDoctor.getTrainingSpeId();
            }
            orgFlow=resDoctor.getOrgFlow();
        }
        List<com.pinde.core.model.ResDoctorRecruit> resDoctorRecruits = oscaDoctorOrderdeBiz.selectDoctorGraduationYear(GlobalContext.getCurrentUser().getUserFlow());
        if(resDoctorRecruits!=null&&resDoctorRecruits.size()>0&&resDoctorRecruits.get(0)!=null){
            graduationYear=resDoctorRecruits.get(0).getGraduationYear();
        }
        if(StringUtil.isNotBlank(graduationYear)){
            graduationYear=graduationYear.substring(0,4);
        }
        Map<String, Object> map=new HashMap<>();
        map.put("orgName",orgName);
        map.put("speId",speId);
        map.put("speIdList",speIdList);
        map.put("isLocal",isLocal);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isLocal)) {
            map.put("orgFlow",orgFlow);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<OscaSkillsAssessmentExt> skillsAssessmentList=oscaDoctorOrderdeBiz.skillsAssessmentList(map);
        if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0){
            OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
            for (OscaSkillsAssessmentExt osae:skillsAssessmentList) {
                if(osae!=null){
                    oscaDoctorAssessment.setClinicalFlow(osae.getClinicalFlow());
                    int doctorCount=oscaDoctorOrderdeBiz.countDoctorAssessment(oscaDoctorAssessment);
                    int overplus=osae.getAppointNum()-doctorCount;
                    osae.setOverplus(overplus+"");
                    oscaDoctorAssessment.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
                    List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(oscaDoctorAssessment);
                    if(doctorList!=null&&doctorList.size()>0){
                        osae.setOscaDoctorAssessment(doctorList.get(0));
                    }
                    Map<String,String> timeList=baseBiz.queryOsaTime(osae.getClinicalFlow());
                    if(timeList!=null){
                        osae.setExamStartTimeList(timeList.get("EXAM_START_TIME"));
                        osae.setExamEndTimeList(timeList.get("EXAM_END_TIME"));
                    }
                }
            }
            if("on".equals(searchNotFull)){
                searchFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
                List<OscaSkillsAssessmentExt> skillsAssessmentListTemp=new ArrayList<>();
                for (OscaSkillsAssessmentExt osae:skillsAssessmentList) {
                    if(osae!=null&&osae.getOverplus()!=null&&Integer.parseInt(osae.getOverplus())>0){
                        skillsAssessmentListTemp.add(osae);
                    }
                }
                skillsAssessmentList=skillsAssessmentListTemp;
            }
        }

        OscaDoctorAssessment doctor=new OscaDoctorAssessment();
        doctor.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
        List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(doctor);
        OscaDoctorAssessment resultDoctor=null;
        if(doctorList!=null&&doctorList.size()>0){
            resultDoctor=doctorList.get(0);
        }
        Map<String, String> doctorMap=new HashMap<>();
        doctorMap.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        OscaSkillsAssessmentExt doctorAssessmentInfo=null;
        List<OscaSkillsAssessmentExt> skillsAssessments=oscaDoctorOrderdeBiz.selectDoctorAssessmentInfo(doctorMap);
        if(skillsAssessments!=null&&skillsAssessments.size()>0){
            doctorAssessmentInfo=skillsAssessments.get(0);
        }
        String clinicalFlow="";
        String ticketNumber="";
        List<OscaSkillsAssessmentExt> osaTemp1=oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
        if(osaTemp1!=null&&osaTemp1.size()>0){
            if(osaTemp1.get(0)!=null&&osaTemp1.get(0).getOscaDoctorAssessment()!=null){
                ticketNumber=osaTemp1.get(0).getOscaDoctorAssessment().getTicketNumber();
            }
        }
        if(doctorAssessmentInfo!=null&&doctorAssessmentInfo.getOscaDoctorAssessment()!=null){
            clinicalFlow=doctorAssessmentInfo.getClinicalFlow();
        }
        Map<String,Object> param = new HashMap<>();
        param.put("clinicalFlow",clinicalFlow);
        param.put("auditStatusId",AuditStatusEnum.Passed.getId());
        param.put("signStatusId", SignStatusEnum.SignIn.getId());
        param.put("ticketNumber", ticketNumber);
        param.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryGradeList(param);
        int doctorScore=0;
        if(gradeList!=null&&gradeList.size()>0&&gradeList.get(0)!=null&&gradeList.get(0).get("EXAM_SCORE")!=null){
            String examScore=gradeList.get(0).get("EXAM_SCORE").toString();
            String[] scores=examScore.split(",");
            for (int i=0;i<scores.length;i++){
                if(scores[i]!=null&&!"*".equals(scores[i])){
                    doctorScore+=(int)(Double.parseDouble(scores[i])+0.5);
                }
            }
        }
        if(resDoctor==null||StringUtil.isBlank(resDoctor.getTrainingSpeId())|| StringUtil.isBlank(resDoctor.getTrainingSpeId())){
            skillsAssessmentList=new ArrayList<>();
        }
        int lastGraduationYear=0;
        if(StringUtil.isNotBlank(graduationYear)){
            lastGraduationYear=Integer.parseInt(graduationYear)-1;
        }
        //取近两年结业考核理论成绩
        List<ResScore> resScoreList=oscaDoctorOrderdeBiz.selectResScore(GlobalContext.getCurrentUser().getUserFlow(),graduationYear);
        List<ResScore> resScoreList1=oscaDoctorOrderdeBiz.selectResScore(GlobalContext.getCurrentUser().getUserFlow(),lastGraduationYear+"");
        if(StringUtil.isNotBlank(graduationYear)&&resScoreList!=null&&resScoreList.size()>0){
            model.addAttribute("resScore",resScoreList.get(0));
        }else{
            model.addAttribute("resScore",new ResScore());
        }
        if(lastGraduationYear!=0&&resScoreList1!=null&&resScoreList1.size()>0){
            model.addAttribute("resScore1",resScoreList1.get(0));
        }else{
            model.addAttribute("resScore1",new ResScore());
        }

        String hegeScore="60";
        //根据年份从res_pass_score_cfg取数据
        ResPassScoreCfg cfg = new ResPassScoreCfg();
        cfg.setCfgYear(graduationYear);
        ResPassScoreCfg resPassScoreCfg = resbaseBiz.readResPassScoreCfg(cfg);
        if(resPassScoreCfg!=null){
            hegeScore = resPassScoreCfg.getCfgPassScore();
            if(StringUtil.isBlank(hegeScore)){
                hegeScore="60";
            }
        }
        model.addAttribute("hegeScore",hegeScore);
        model.addAttribute("doctorScore",doctorScore+"");
        model.addAttribute("doctorAssessmentInfo",doctorAssessmentInfo);
        model.addAttribute("resultDoctor",resultDoctor);
        model.addAttribute("skillsAssessmentList",skillsAssessmentList);
        model.addAttribute("searchFlag",searchFlag);
        model.addAttribute("skillsAssessments",skillsAssessments);
        model.addAttribute("isLocal",isLocal);
        model.addAttribute("liId",liId);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("graduationYear",graduationYear);
        String jspPath ="jsres/doctor/graduation/orderedTitle_kh";
        if("mine".equals(flag)){
            jspPath =  "jsres/doctor/graduation/orderedTitle_mine";
        }
        return jspPath;
    }

    @RequestMapping(value = {"/ordered"})
    @ResponseBody
    public String ordered(String clinicalFlow,String doctorFlow,String appointNum,String flag){
        doctorFlow = GlobalContext.getCurrentUser().getUserFlow();
        String isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;//结业资格审核
        com.pinde.core.model.ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME DESC");
        //在系统中是否有资格审核记录
        String signupFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
        if (recruitList != null && recruitList.size() > 0) {
            ResDoctorRecruit resDoctorRecruit = recruitList.get(0);
            JsresGraduationApply apply = jsresGraduationApplyBiz.searchByRecruitFlow(resDoctorRecruit.getRecruitFlow(), "");
            if (apply == null) {
                isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
            }else{
                //如果不是当前年的资格审核记录，则需要当前年补考审核记录
                if(!DateUtil.getYear().equals(apply.getApplyYear())){
                    isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_N;
                    signupFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
                }
            }
        }
        //当前年补考审核记录
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply) && com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(signupFlag)) {
            JsresExamSignup examSignup = new JsresExamSignup();
            examSignup.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            examSignup.setSignupYear(DateUtil.getYear());
            examSignup.setSignupTypeId("skill");//技能考核
            examSignup.setDoctorFlow(doctorFlow);
            examSignup.setAuditStatusId("GlobalPassed");//省厅审核通过
            JsresExamSignup signup = skillTimeConfigBiz.findExamSignup(examSignup);
            if(null != signup){
                isAllowApply = com.pinde.core.common.GlobalConstant.FLAG_Y;
                signupFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(isAllowApply) || com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(signupFlag)) {
            return com.pinde.core.common.GlobalConstant.RECORD_STATUS_N;
        }
        OscaDoctorAssessment odaTemp=new OscaDoctorAssessment();
        odaTemp.setClinicalFlow(clinicalFlow);
        int doctorCount=oscaDoctorOrderdeBiz.countDoctorAssessment(odaTemp);
        int overplus=Integer.parseInt(appointNum)-doctorCount;
        if(overplus>0){
            OscaSkillsAssessment oscaSkillsAssessment=oscaDoctorOrderdeBiz.selectSkillsAssessmentByClinicalFlow(clinicalFlow);
            OscaDoctorAssessment oda=new OscaDoctorAssessment();
            oda.setClinicalFlow(clinicalFlow);
            oda.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
            List<OscaDoctorAssessment> odaListTemp = oscaDoctorOrderdeBiz.selectDoctorAssessment(oda);
            if(oscaSkillsAssessment!=null){
                OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
                if(odaListTemp!=null&&odaListTemp.size()>0){
                    oscaDoctorAssessment=odaListTemp.get(0);
                    oscaDoctorAssessment.setAppointTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                    oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passing.getId());
                    oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passing.getName());
                    GeneralMethod.setRecordInfo(oscaDoctorAssessment,false);
                    oscaDoctorOrderdeBiz.updateDoctorAssessment(oscaDoctorAssessment);
                    if("passed".equals(flag)){
                        oscaDoctorAssessment.setAppointTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                        oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passed.getId());
                        oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passed.getName());
                        GeneralMethod.setRecordInfo(oscaDoctorAssessment,false);
                        oscaDoctorOrderdeBiz.updateDoctorAssessment(oscaDoctorAssessment);
                        baseBiz.auditAppoint(oscaDoctorAssessment.getRecordFlow(),AuditStatusEnum.Passed.getId(),null);
                    }
                }else{
                    oscaDoctorAssessment.setRecordFlow(PkUtil.getUUID());
                    oscaDoctorAssessment.setClinicalFlow(clinicalFlow);
                    oscaDoctorAssessment.setClinicalName(oscaSkillsAssessment.getClinicalName());
                    oscaDoctorAssessment.setClinicalYear(oscaSkillsAssessment.getClinicalYear());
                    oscaDoctorAssessment.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
                    oscaDoctorAssessment.setDoctorName(GlobalContext.getCurrentUser().getUserName());
                    oscaDoctorAssessment.setAppointTime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
                    oscaDoctorAssessment.setCreateTime(com.pinde.core.util.DateUtil.getCurrentTime());
                    oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passing.getId());
                    oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passing.getName());
                    oscaDoctorAssessment.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                    oscaDoctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                    oscaDoctorAssessment.setIsPass(DoctorScoreEnum.Miss.getId());
                    oscaDoctorAssessment.setIsPassName(DoctorScoreEnum.Miss.getName());
                    oscaDoctorAssessment.setIsAdminAudit(com.pinde.core.common.GlobalConstant.FLAG_N);
                    oscaDoctorOrderdeBiz.insertDoctorAssessment(oscaDoctorAssessment);
                    if("passed".equals(flag)){
                        oscaDoctorAssessment.setAuditStatusId(AuditStatusEnum.Passed.getId());
                        oscaDoctorAssessment.setAuditStatusName(AuditStatusEnum.Passed.getName());
                        oscaDoctorOrderdeBiz.updateDoctorAssessment(oscaDoctorAssessment);
                        baseBiz.auditAppoint(oscaDoctorAssessment.getRecordFlow(),AuditStatusEnum.Passed.getId(),null);
                    }
                }
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
            }else {
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
            }
        }else{
            return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
        }
    }

    @RequestMapping(value = "/searchOrderedRecord", method = {RequestMethod.GET, RequestMethod.POST})
    public String searchOrderedRecord(String flag,String isLocal,String clinicalFlow,Integer currentPage, HttpServletRequest request,Model model){
        List<OscaSkillsAssessmentExt> skillsAssessmentList=new ArrayList<>();
        Map<String, String> map=new HashMap<>();
        map.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        map.put("isLocal",isLocal);
        PageHelper.startPage(currentPage,getPageSize(request));
        skillsAssessmentList=oscaDoctorOrderdeBiz.selectDoctorAssessmentList(map);
        if(skillsAssessmentList!=null&&skillsAssessmentList.size()>0){
            OscaDoctorAssessment oscaDoctorAssessment=new OscaDoctorAssessment();
            for (OscaSkillsAssessmentExt osae:skillsAssessmentList) {
                if(osae!=null){
                    oscaDoctorAssessment.setClinicalFlow(osae.getClinicalFlow());
                    int doctorCount=oscaDoctorOrderdeBiz.countDoctorAssessment(oscaDoctorAssessment);
                    int overplus=osae.getAppointNum()-doctorCount;
                    osae.setOverplus(overplus+"");
                    oscaDoctorAssessment.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
                    List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(oscaDoctorAssessment);
                    if(doctorList!=null&&doctorList.size()>0){
                        osae.setOscaDoctorAssessment(doctorList.get(0));
                    }
                }
            }
        }

        //预约信息详情展示
        OscaDoctorAssessment doctor=new OscaDoctorAssessment();
        doctor.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
        if(com.pinde.core.util.StringUtil.isNotBlank(clinicalFlow)){
            doctor.setClinicalFlow(clinicalFlow);
        }
        List<OscaDoctorAssessment> doctorList=oscaDoctorOrderdeBiz.selectDoctorAssessment(doctor);
        OscaDoctorAssessment resultDoctor=null;
        if(doctorList!=null&&doctorList.size()>0){
            resultDoctor=doctorList.get(0);
        }
        Map<String, String> doctorMap=new HashMap<>();
        doctorMap.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        if(com.pinde.core.util.StringUtil.isNotBlank(clinicalFlow)){
            doctorMap.put("clinicalFlow",clinicalFlow);
        }
        OscaSkillsAssessmentExt doctorAssessmentInfo=null;
        List<OscaSkillsAssessmentExt> skillsAssessments=oscaDoctorOrderdeBiz.selectDoctorAssessmentInfo(doctorMap);
        if(skillsAssessments!=null&&skillsAssessments.size()>0){
            doctorAssessmentInfo=skillsAssessments.get(0);
        }
        clinicalFlow="";
        String ticketNumber="";
        List<OscaSkillsAssessmentExt> osaTemp1=oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
        if(osaTemp1!=null&&osaTemp1.size()>0){
            if(osaTemp1.get(0)!=null&&osaTemp1.get(0).getOscaDoctorAssessment()!=null){
                ticketNumber=osaTemp1.get(0).getOscaDoctorAssessment().getTicketNumber();
            }
        }
        if(doctorAssessmentInfo!=null&&doctorAssessmentInfo.getOscaDoctorAssessment()!=null){
            clinicalFlow=doctorAssessmentInfo.getClinicalFlow();
        }
        Map<String,Object> param = new HashMap<>();
        param.put("clinicalFlow",clinicalFlow);
        param.put("auditStatusId",AuditStatusEnum.Passed.getId());
        param.put("signStatusId", SignStatusEnum.SignIn.getId());
        param.put("ticketNumber", ticketNumber);
        param.put("doctorFlow",GlobalContext.getCurrentUser().getUserFlow());
        List<Map<String,Object>> gradeList = oscaDoctorScoreBiz.queryGradeList(param);
        int doctorScore=0;
        if(gradeList!=null&&gradeList.size()>0&&gradeList.get(0)!=null&&gradeList.get(0).get("EXAM_SCORE")!=null){
            String examScore=gradeList.get(0).get("EXAM_SCORE").toString();
            String[] scores=examScore.split(",");
            for (int i=0;i<scores.length;i++){
                if(scores[i]!=null){
//                    doctorScore+=(int)(Double.parseDouble(scores[i])+0.5);
                }
            }
        }
        model.addAttribute("skillsAssessmentList",skillsAssessmentList);
        model.addAttribute("doctorScore",doctorScore+"");
        model.addAttribute("doctorAssessmentInfo",doctorAssessmentInfo);
        model.addAttribute("resultDoctor",resultDoctor);
        model.addAttribute("isLocal",isLocal);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("clinicalFlow",clinicalFlow);
        return "jsres/doctor/graduation/orderedTitle_mine";
    }

    @RequestMapping(value = {"/showTicket"})
    public String showTicket(String doctorFlow,String clinicalFlow,Model model){
        Map<String, String> doctorMap=new HashMap<>();
        doctorMap.put("doctorFlow",doctorFlow);
        if(com.pinde.core.util.StringUtil.isNotBlank(clinicalFlow)){
            doctorMap.put("clinicalFlow",clinicalFlow);
        }
        List<OscaSkillsAssessmentExt> oscaSkillsAssessmentExt=oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
        String signUrl="";
        OscaSkillsAssessmentExt osaExt=new OscaSkillsAssessmentExt();
        if(oscaSkillsAssessmentExt!=null&&oscaSkillsAssessmentExt.size()>0){
            if(oscaSkillsAssessmentExt.get(0)!=null&&oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment()!=null){
                signUrl=oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment().getCodeInfo();
                osaExt=oscaSkillsAssessmentExt.get(0);
            }
        }
        model.addAttribute("signUrl",signUrl);
        model.addAttribute("oscaSkillsAssessmentExt",osaExt);
        Map<String, Object> paramMap=new HashMap<>();
        paramMap.put("clinicalFlow",clinicalFlow);
        List<OscaSubjectStation> subjectStations = oscaDoctorOrderdeBiz.getStations(paramMap);
        List<String> partFlows = new ArrayList<>();
        if(subjectStations!=null&&subjectStations.size()>0){
            for(OscaSubjectStation station:subjectStations){
                String partFlow = station.getPartFlow();
                if(!partFlows.contains(partFlow)){
                    partFlows.add(partFlow);
                }
            }
        }
        model.addAttribute("subjectStations",subjectStations);
        model.addAttribute("partFlows",partFlows);
        DecimalFormat df = new DecimalFormat("#0.0%");
        double size = subjectStations.size();
        double w = 1/size;
        String width = df.format(w);
        model.addAttribute("width",width);
        return "jsres/doctor/graduation/doctorTicket";
    }

    @RequestMapping("/showStationInfo")
    public String showStationInfo(String clinicalFlow,Model model){
        if(com.pinde.core.util.StringUtil.isNotBlank(clinicalFlow)){
            List<OscaSubjectStation> subjectStations = oscaDoctorOrderdeBiz.searchSubjectsForDoctor(clinicalFlow);
            model.addAttribute("subjectStations",subjectStations);
        }
        return "jsres/doctor/graduation/subjectsInfo";
    }
}
