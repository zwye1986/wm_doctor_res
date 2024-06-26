package com.pinde.sci.ctrl.jsres;

import com.alibaba.fastjson.JSON;
import com.itextpdf.text.DocumentException;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.ZipUtil;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.res.IFundsBiz;
import com.pinde.sci.biz.res.IResOrgSpeAssignBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.DateUtil;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.ctrl.sch.plan.util.StringUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sch.ActivityTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.sys.SysOrgExt;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/jsres/supervisio")
public class JsresSupervisioController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(JsresSupervisioController.class);

    @Autowired
    private IJsResSupervisioBiz supervisioBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private ISysSupervisioUserBiz supervisioUserBiz;
    @Autowired
    private IJsResSupervisioFileBiz supervisioFileBiz;
    @Autowired
    private IResScheduleScoreBiz resScheduleScoreBiz;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private SysOrgExtMapper sysOrgExtMapper;
    @Autowired
    private IResOrgSpeAssignBiz speAssignBiz;
    @Autowired
    private ResSupervisioSubjectRecordsMapper subjectRecordsMapper;
    @Autowired
    private ResSupervisioReportMapper reportMapper;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IJsResActivityBiz activityBiz;
    @Autowired
    private ResHospSupervSubjectMapper hospSupervSubjectMapper;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private TeachingActivityInfoMapper activityInfoMapper;
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private HospSelfAssessmentMapper assessmentMapper;
    @Autowired
    private HospSelfAssessmentCfgMapper assessmentCfgMapper;


    @RequestMapping(value = "/index/{role}")
    public String index(@PathVariable String role, Model model) {
        model.addAttribute("role", role);
        return "jsres/hospital/supervisio/expertIndex";
    }

    //省级督导
    @RequestMapping(value = "/supervisioMain")
    public String supervisioMain(Model model,String suAoth) {
        model.addAttribute("suAoth", suAoth);
        return "jsres/hospital/supervisio/expertMain";
    }

    //院级督导
    @RequestMapping(value = "/hospitalLeaderMain")
    public String hospitalLeaderMain(Model model) {
        List<SysDept> deptList = supervisioBiz.selectDeptByOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("deptList",deptList);
        return "jsres/hospital/supervisio/hospitalLeaderMain";
    }


    /**
     * 省级督导   专家管理
     */
    @RequestMapping(value = "/personList")
    public String personList(Model model, String roleFlag, Integer currentPage, HttpServletRequest request,String suAoth,
                             String userName, String userPhone, String userLevelId, String trainingSpeId) {
        //查询条件
        Map<String, Object> param = new HashMap<>();
        param.put("userName", userName);
        param.put("userPhone", userPhone);
        param.put("userLevelId", userLevelId);
        param.put("speId", trainingSpeId);
        if (StringUtil.isNotBlank(suAoth) && suAoth.equals("Y")){
            SysUser currentUser = GlobalContext.getCurrentUser();
            param.put("orgFlow",currentUser.getOrgFlow());
            param.put("suAoth",suAoth);
        }
        List<String> userLevelList = new ArrayList();
        userLevelList.add("management");
        userLevelList.add("expertLeader");
        userLevelList.add("baseExpert");
        userLevelList.add("basePer");
        param.put("userLevel", userLevelList);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysUser> list = supervisioUserBiz.selectSupervisioUserList(param);
        model.addAttribute("list", list);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("suAoth",suAoth);
        return "jsres/hospital/supervisio/personList";
    }

    //院级督导 专家管理
    @RequestMapping(value = "/hospitalLeaderList")
    public String hospitalLeaderList(Model model, Integer currentPage, HttpServletRequest request,
                                     String userName, String userPhone, String trainingSpeId,String deptFlow) {
        //查询条件
        Map<String, Object> param = new HashMap<>();
        param.put("userName", userName);
        param.put("userPhone", userPhone);
        param.put("deptFlow",deptFlow);

        if (StringUtil.isNotBlank(trainingSpeId)){
            List<String> speList = Arrays.asList(trainingSpeId.split(","));
            param.put("speId",speList);
        }
        param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        List<String> userLevelList = new ArrayList();
        userLevelList.add("hospitalLeader");
        param.put("userLevel", userLevelList);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysUser> list = supervisioUserBiz.selectSysUserList(param);
        model.addAttribute("list", list);
        return "jsres/hospital/supervisio/hospitalLeaderList";
    }




    @RequestMapping(value = "/chooseLeaderList")
    public String chooseLeaderList(Model model,String userName, String userPhone, String trainingSpeId,String userFlows) {
        //查询条件
        Map<String, Object> param = new HashMap<>();
        param.put("userName", userName);
        param.put("userPhone", userPhone);

        if (StringUtil.isNotBlank(trainingSpeId)){
            List<String> speList = Arrays.asList(trainingSpeId.split(","));
            param.put("speId",speList);
        }
        param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        List<String> userLevelList = new ArrayList();
        userLevelList.add("hospitalLeader");
        param.put("userLevel", userLevelList);
        List<SysUser> list = supervisioUserBiz.selectSysUserList(param);
        model.addAttribute("list", list);
        model.addAttribute("userFlows",userFlows);
        return "jsres/hospital/supervisio/chooseLeaderList";
    }

    /**
     * 省级督导    专家新增、编辑
     */
    @RequestMapping(value = "/editSupervisioUser")
    public String editSupervisioUser(Model model, HttpServletRequest request, String userFlow, String type,String suAoth,String roleFlag) {
        if ("edit".equals(type) && StringUtil.isNotBlank(userFlow)) {
            SysUser user = userBiz.readSysUser(userFlow);
            model.addAttribute("user", user);
        }
        model.addAttribute("type", type);
        model.addAttribute("suAoth",suAoth);
        model.addAttribute("roleFlag",roleFlag);
        return "jsres/hospital/supervisio/editSupervisioUser";
    }



    //院级督导    专家新增、编辑
    @RequestMapping(value = "/editHospitalLeader")
    public String editHospitalLeader(Model model, String userFlow, String type) throws IOException {
        SysUser user = userBiz.readSysUser(userFlow);
        List<SysDept> deptList = supervisioBiz.selectDeptByOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("user", user);
        model.addAttribute("deptList",deptList);
        return "jsres/hospital/supervisio/editHospitalLeader";
    }

    @RequestMapping(value = "/editSupervisioUserTwo")
    public String editSupervisioUserTwo(Model model, String userFlow, String type) {
        SysUser user = userBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        return "jsres/hospital/supervisio/editHospitalLeaderTwo";
    }


    //院级督导    专家删除   数据状态为N
    @RequestMapping(value = "/delSupervisioUser")
    @ResponseBody
    public String delSupervisioUser(String userFlow) {
        SysUser user = userBiz.readSysUser(userFlow);
        user.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int num = supervisioUserBiz.editSupervisioUser(user);
        if (num > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }


    /**
     * 省级督导  专家保存
     */
    @RequestMapping(value = {"/saveUser"}, method = RequestMethod.POST)
    @ResponseBody
    public String saveUser(SysUser user,String suAoth) {
        //新增用户是判断
        if (StringUtil.isBlank(user.getUserFlow())) {
            //判断是否是基地创建的专家
            if (StringUtil.isNotBlank(suAoth) && suAoth.equals("Y")){
                SysUser currUser = GlobalContext.getCurrentUser();
                user.setOrgFlow(currUser.getOrgFlow());
                user.setOrgName(currUser.getOrgName());
            }
            String userFlow = PkUtil.getUUID();
            user.setUserFlow(userFlow);
            user.setUserCode(user.getUserPhone());
            user.setStatusId(UserStatusEnum.Activated.getId());
            user.setStatusDesc(UserStatusEnum.Activated.getName());
            user.setResTrainingSpeId(user.getResTrainingSpeId().replace(",",""));
            user.setBankAccountNumber(user.getBankAccountNumber());
            user.setBankOfDeposit(user.getBankOfDeposit());
            //新增人员默认初始密码 123456
            user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, "JSzp123456#"));
            //判断电话号码是否重复
            if (StringUtil.isNotBlank(user.getUserPhone())) {
                List<SysUser> userList = supervisioUserBiz.findByUserPhoneAndNotSelf(userFlow, user.getUserPhone());
                if (null != userList && userList.size() > 0) {
                    return GlobalConstant.OPERATE_FAIL;
                }
            }
            //自动刷角色
            if (user.getUserLevelId().equals("management")){
                SysUserRole sysUserRole=new SysUserRole();
                sysUserRole.setUserFlow(user.getUserFlow());
                sysUserRole.setRecordFlow(PkUtil.getUUID());
                sysUserRole.setRecordStatus(GlobalConstant.FLAG_Y);
                sysUserRole.setCreateTime(com.pinde.core.util.DateUtil.getCurrDateTime2());
                sysUserRole.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                sysUserRole.setRoleFlow(InitConfig.getSysCfg("res_management_role_flow"));
                if (sysUserRoleMapper.insert(sysUserRole)<=0){
                    return GlobalConstant.OPERATE_FAIL;
                }
            }else if (user.getUserLevelId().equals("expertLeader")){
                SysUserRole sysUserRole=new SysUserRole();
                sysUserRole.setUserFlow(user.getUserFlow());
                sysUserRole.setRecordFlow(PkUtil.getUUID());
                sysUserRole.setRecordStatus(GlobalConstant.FLAG_Y);
                sysUserRole.setCreateTime(com.pinde.core.util.DateUtil.getCurrDateTime2());
                sysUserRole.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
                sysUserRole.setRoleFlow(InitConfig.getSysCfg("res_expertLeader_role_flow"));
                if (sysUserRoleMapper.insert(sysUserRole)<=0){
                    return GlobalConstant.OPERATE_FAIL;
                }
            }

            int num = supervisioUserBiz.addSupervisioUser(user);
            if (num > 0) {
                return GlobalConstant.SAVE_SUCCESSED;
            }
        } else {
            //判断电话号码是否重复
            if (StringUtil.isNotBlank(user.getUserPhone())) {
                //jsp中有两个name相同的select,参数传递可能存在两个，需要去除逗号和去除查询的speId
                user.setResTrainingSpeId(user.getResTrainingSpeId().replace(",",""));
                if (user.getResTrainingSpeId().length()>=8){
                    user.setResTrainingSpeId(user.getResTrainingSpeId().substring(0,4));
                }
                List<SysUser> userList = supervisioUserBiz.findByUserPhoneAndNotSelf(user.getUserFlow(), user.getUserPhone());
                if (null != userList && userList.size() > 0) {
                    return GlobalConstant.OPERATE_FAIL;
                }
            }
            int num = supervisioUserBiz.editSupervisioUser(user);
            if (num > 0) {
                return GlobalConstant.UPDATE_SUCCESSED;
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //院级督导    保存专家
    @RequestMapping(value = {"/saveHospitalLeader"}, method = RequestMethod.POST)
    @ResponseBody
    public String saveHospitalLeader(SysUser user) {
        if (StringUtil.isBlank(user.getUserFlow())){
            //判断电话号码是否重复
            List<SysUser> userList = supervisioUserBiz.findByUserPhoneForLeader(user.getUserFlow(),user.getUserPhone());
            if (null != userList && userList.size() > 0) {
                for (SysUser sysUser : userList) {
                    if (StringUtil.isNotBlank(sysUser.getUserLevelName()) && StringUtil.isNotBlank(sysUser.getUserLevelId())) {
                        if (sysUser.getUserLevelName().equals("评分专家") && sysUser.getUserLevelId().equals("hospitalLeader")) {
                            return GlobalConstant.OPERATE_FAIL;
                        }
                    }
                }
            }
            String userFlow = PkUtil.getUUID();
            user.setUserFlow(userFlow);
            SysUser sysUser = GlobalContext.getCurrentUser();
            user.setOrgFlow(sysUser.getOrgFlow());
            user.setOrgName(sysUser.getOrgName());
            user.setUserCode(user.getUserPhone());
            user.setUserPasswd(PasswordHelper.encryptPassword(userFlow, "Njpd@2022!!!"));
            user.setRecordStatus(GlobalConstant.FLAG_Y);
            user.setCreateTime(com.pinde.core.util.DateUtil.getCurrDateTime2());
            user.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            user.setUserLevelId("hospitalLeader");
            user.setUserLevelName("评分专家");
            user.setStatusId(UserStatusEnum.Activated.getId());
            user.setStatusDesc(UserStatusEnum.Activated.getName());
            int num = supervisioUserBiz.addSupervisioUser(user);
            if (num > 0) {
                return GlobalConstant.SAVE_SUCCESSED;
            }
        }else {
            if (StringUtil.isNotBlank(user.getUserPhone())) {
                List<SysUser> userList = supervisioUserBiz.findByUserPhoneForLeader("",user.getUserPhone());
                if (null != userList && userList.size() > 0) {
                    for (SysUser sysUser : userList) {
                        if (!sysUser.getUserName().equals(user.getUserName())) {
                            return GlobalConstant.OPERATE_FAIL;
                        }
                    }
                }
                int num = supervisioUserBiz.editSupervisioUser(user);
                if (num > 0) {
                    return GlobalConstant.UPDATE_SUCCESSED;
                }
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }


    /**
     * 省级督导  专家导入
     */
    @RequestMapping("/importExcelUser")
    public String importExcelUser() {
        return "jsres/hospital/supervisio/importSupervisioUser";
    }
    //院级督导  专家导入
    @RequestMapping("/importHospitalLeaderExcel")
    public String importHospitalLeaderExcel(HttpServletResponse response) throws Exception {
        return "jsres/hospital/supervisio/importHospitalLeader";
    }

    //省级督导   导入专家信息
    @RequestMapping(value = {"/importSupervisioUser"}, method = {RequestMethod.POST})
    @ResponseBody
    public String importSupervisioUser(MultipartFile file ) {
        if (file.getSize() > 0) {
            int count = 0;
            try {
                count = supervisioUserBiz.importSupervisioUser(file);
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
            if (count != 0) {
                return GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    //院级督导   导入专家信息
    @RequestMapping(value = {"/importHospitalLeader"}, method = {RequestMethod.POST})
    @ResponseBody
    public String importHospitalLeader(MultipartFile file ) {
        if (file.getSize() > 0) {
            int count = 0;
            try {
                count = supervisioUserBiz.importHospitalLeader(file);
            } catch (Exception e) {
                e.printStackTrace();
                return e.getMessage();
            }
            if (count != 0) {
                return GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
    /**
     * 省级督导    专家导出
     */
    @RequestMapping(value = "/exportSupervisioUser")
    public void exportSupervisioUser(HttpServletResponse response, Model model, HttpServletRequest request, String userName,
                                     String userPhone, String userLevelId, String trainingSpeId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("userName", userName);
        param.put("userPhone", userPhone);
        param.put("userLevelId", userLevelId);
        param.put("speId", trainingSpeId);
        List<String> userLevelList = new ArrayList();
        userLevelList.add("management");
        userLevelList.add("expertLeader");
        userLevelList.add("baseExpert");
        userLevelList.add("basePer");
        param.put("userLevel", userLevelList);
        List<SysUser> list = supervisioUserBiz.selectSupervisioUserList(param);
        String[] titles = null;
        titles = new String[]{
                "userName:专家名称",
                "userPhone:手机号码",
                "userLevelName:专家分类",
                "resTrainingSpeName:专业",
                "bankOfDeposit:开户行",
                "bankAccountNumber:银行卡账号"
        };
        String fileName = "专家人员列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }


    //院级督导   导出专家信息
    @RequestMapping(value = "/exportHospitalLeader")
    public void exportHospitalLeader(HttpServletResponse response, Model model, HttpServletRequest request, String userName,
                                     String userPhone, String userLevelId, String trainingSpeId) throws Exception {
        Map<String, Object> param = new HashMap<>();
        param.put("userName", userName);
        param.put("userPhone", userPhone);

        if (StringUtil.isNotBlank(trainingSpeId)){
            List<String> speList = Arrays.asList(trainingSpeId.split(","));
            param.put("speId",speList);
        }
        param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        List<String> userLevelList = new ArrayList();
        userLevelList.add("hospitalLeader");
        param.put("userLevel", userLevelList);
        List<SysUser> list = supervisioUserBiz.selectSysUserList(param);
        String[] titles = null;
        titles = new String[]{
                "userName:专家名称",
                "userPhone:手机号码",
                "resTrainingSpeName:专业",
                "deptName:科室"
        };
        String fileName = "专家人员列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    //院级督导 项目导出
    @RequestMapping(value = "/exportHospitalSubject")
    public void exportHospitalSubject(HttpServletResponse response, String subjectName, String speId,
                                      String orderAction,String startTime,String endTime) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("subjectName",subjectName);
        map.put("orderAction",orderAction);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        SysUser user = GlobalContext.getCurrentUser();
        map.put("orgFlow",user.getOrgFlow());
        if (StringUtil.isNotBlank(speId)){
            map.put("speId", speId.split(","));
        }
        List<ResHospSupervSubject> list = supervisioUserBiz.selectHospSuperList(map);
        if (null != list && list.size()>0){
            List<SysDict> dictList = dictBiz.searchDictListByDictTypeId("InspectionType");
            for (ResHospSupervSubject subject : list) {
                if (subject.getOrderAction().equals("appoint")){
                    subject.setOrderAction("指定专家");
                }else {
                    subject.setOrderAction("自选专家");
                }
                for(ActivityTypeEnum e:ActivityTypeEnum.values()){
                    if (subject.getInspectionType().equals(e.getId())){
                        subject.setInspectionType(e.getName());
                    }
                }
            }
        }
        String[] titles = null;
        titles = new String[]{
                "speName:专业",
                "subjectName:活动名称",
                "orderAction:评审方式",
                "activityStartTime:开始时间",
                "activityEndTime:结束时间",
                "inspectionType:活动形式"
        };
        String fileName = "项目信息列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }


    //院级督导 项目导出
    @RequestMapping(value = "/exportHospitalStatistics")
    public void exportHospitalStatistics(HttpServletResponse response, String activityName, String speId) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("activityName",activityName);
        SysUser user = GlobalContext.getCurrentUser();
        map.put("orgFlow",user.getOrgFlow());
        if (StringUtil.isNotBlank(speId)){
            map.put("speId", speId.split(","));
        }
        List<ResHospSupervSubject> list = supervisioUserBiz.selectHospSuperList(map);
        List<Map<String, String>> mapList=new ArrayList<>();
        if (null != list && list.size()>0){
            ArrayList<String> subjectFlows = new ArrayList<>();
            for (ResHospSupervSubject subject : list) {
                subjectFlows.add(subject.getSubjectFlow());
                if (subject.getOrderAction().equals("appoint")){
                    subject.setOrderAction("指定专家");
                }else {
                    subject.setOrderAction("自选专家");
                }
                for(ActivityTypeEnum e:ActivityTypeEnum.values()){
                    if (subject.getInspectionType().equals(e.getId())){
                        subject.setInspectionType(e.getName());
                    }
                }
            }
            mapList = supervisioUserBiz.searchEvaluationIndicators(subjectFlows); //总表中所有项目的指标
        }
        List<ResEvaluationIndicators> indicatorsList = supervisioBiz.searchAll();//所以表的指标
        //导出总表
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFCellStyle titleStyle = wb.createCellStyle();//水平垂直居中
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFSheet sheet = wb.createSheet("院级督导项目统计表");//sheet名称
        HSSFRow row0 = sheet.createRow(0);
        String[]   title0 = new String[]{
                "专业",
                "活动名称",
                "科室名称",
                "评审方式",
                "活动形式",
                "带教老师",
                "专家1",
                "评分",
                "专家2",
                "评分",
                "平均分",
                "评审开始时间",
                "评审结束时间",
                "活动开始时间",
                "活动结束时间",
                "专家1评分开始时间",
                "专家1评分结束时间",
                "专家2评分开始时间",
                "专家2评分结束时间"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < title0.length; i++) {
            cellTitle = row0.createCell(i);
            cellTitle.setCellValue(title0[i]);
            cellTitle.setCellStyle(titleStyle);
            cellTitle.setCellType(HSSFCell.CELL_TYPE_STRING);
        }
        if (null != list && list.size()>0) {
            HashMap<String, Integer> nameMap = new HashMap<String, Integer>();//防止sheet名称重复，重复则 +1
            for (int i = 0; i < list.size(); i++) {
                HSSFRow rows = sheet.createRow(i+1);
                ResHospSupervSubject subject = list.get(i);
                String[] titles = new String[]{
                        subject.getSpeName(),
                        subject.getSubjectName(),
                        subject.getDeptName(),
                        subject.getOrderAction(),
                        subject.getInspectionType(),
                        subject.getTeachName(),
                        subject.getLeaderOneName(),
                        subject.getLeaderOneScore(),
                        subject.getLeaderTwoName(),
                        subject.getLeaderTwoScore(),
                        subject.getAvgScore(),
                        subject.getStartTime(),
                        subject.getActivityStartTime(),
                        subject.getActivityEndTime(),
                        subject.getLeaderOneStartTime(),
                        subject.getLeaderOneEndTime(),
                        subject.getLeaderTwoStartTime(),
                        subject.getLeaderTwoEndTime()
                };
                for (int j = 0; j < titles.length; j++) {
                    cellTitle = rows.createCell(j);
                    cellTitle.setCellValue(titles[j]);
                    cellTitle.setCellStyle(titleStyle);
                    cellTitle.setCellType(HSSFCell.CELL_TYPE_STRING);
                }
                //如果两个专家评分都未提交，就不导出
                if (StringUtil.isNotBlank(subject.getLeaderOneScore()) || StringUtil.isNotBlank(subject.getLeaderTwoScore())){
                    String subjectFlow = subject.getSubjectFlow();

                    List<Map<String, String>> scoreList=new ArrayList<>();//该项目的评分指标（要导出的数据）
                    HashMap<String, HashMap<String, String>> mapHashMap = new HashMap<>();
                    String pathName="";
                    for (Map<String, String> info : mapList) {
                        HashMap<String, String> score=new HashMap<>();
                        if (info.get("subjectFlow").equals(subjectFlow)){
                            if (StringUtil.isNotBlank(subject.getLeaderOneFlow())){
                                if (subject.getLeaderOneFlow().equals(info.get("userFlow1"))){
                                    score.put("score1",info.get("score1"));
                                }else {
                                    score.put("score1",info.get("score2"));
                                }
                            }
                            if (StringUtil.isNotBlank(subject.getLeaderTwoFlow())){
                                if (subject.getLeaderTwoFlow().equals(info.get("userFlow1"))){
                                    score.put("score2",info.get("score1"));
                                }else {
                                    score.put("score2",info.get("score2"));
                                }
                            }
                            score.put("avg",info.get("avg"));
                            score.put("fullScore",info.get("fullScore"));
                            score.put("indicators",info.get("indicators"));
                            score.put("tablePathName",info.get("tablePathName"));
                            mapHashMap.put(info.get("itemId"),score);
                            pathName=info.get("tablePathName");
                        }
                    }
                    List<ResEvaluationIndicators> arrayList =new ArrayList<>();//这个项目的所有指标
                    for (ResEvaluationIndicators indicators : indicatorsList) {
                        if (indicators.getTablePathName().equals(pathName)){
                            arrayList.add(indicators);
                        }
                    }
                    //可能存在没有评分的指标，比如 一票否决（非必填），要将没有评分的指标导出
                    for (ResEvaluationIndicators indicators : arrayList) {
                        HashMap<String, String> score = mapHashMap.get(indicators.getItemId());
                        if (null !=score){
                            scoreList.add(score);
                        }else {
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("indicators",indicators.getIndicators());
                            hashMap.put("fullScore",indicators.getFullScore());
                            scoreList.add(hashMap);
                        }
                    }

                    String sheetName=subject.getSubjectName();
                    if (null != nameMap.get(sheetName)){	//sheet名称是否重复
                        Integer repeatNum=nameMap.get(sheetName);	//获取重复次数
                        repeatNum++;
                        nameMap.put(sheetName,repeatNum);
                        sheetName=sheetName+repeatNum;
                    }else {
                        nameMap.put(sheetName,0);	//未出现重复，重复次数默认0
                    }
                    HSSFSheet sheet1 = wb.createSheet(sheetName);
                    sheet1.setColumnWidth(0,20000);
                    sheet1.setColumnWidth(1,4000);
                    sheet1.setColumnWidth(2,4000);
                    sheet1.setColumnWidth(3,4000);
                    sheet1.setColumnWidth(4,4000);

                    HSSFRow row1 = sheet1.createRow(0);
                    String[] titles1 = new String[]{
                            subject.getSubjectName()+"评分指标详情",
                            "满分",
                            "专家打分1",
                            "专家打分2",
                            "均分"
                    };
                    HSSFCell cellTitle2 = null;
                    for (int k = 0; k < titles1.length; k++) {
                        cellTitle2 = row1.createCell(k);
                        cellTitle2.setCellValue(titles1[k]);
                        cellTitle2.setCellStyle(titleStyle);
                        cellTitle2.setCellType(HSSFCell.CELL_TYPE_STRING);
                    }

                    for (int j = 0; j < scoreList.size(); j++) {
                        HSSFRow rowInfo = sheet1.createRow(j+1);
                        Map<String, String> info = scoreList.get(j);
                        String[] infoTitle = new String[]{
                                info.get("indicators"),
                                info.get("fullScore"),
                                info.get("score1"),
                                info.get("score2"),
                                info.get("avg")
                        };
                        HSSFCell infoCell = null;
                        for (int k = 0; k < infoTitle.length; k++) {
                            infoCell = rowInfo.createCell(k);
                            if (k!=0){
                                if (StringUtil.isNotBlank(infoTitle[k])){
                                    infoCell.setCellValue(Double.parseDouble(infoTitle[k]));
                                    infoCell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
                                }else {
                                    infoCell.setCellValue("");
                                    infoCell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                }
                            }else {
                                infoCell.setCellStyle(styleLeft);
                                String s = infoTitle[k];
                                infoCell.setCellValue(s.trim());
                                infoCell.setCellType(HSSFCell.CELL_TYPE_STRING);
                            }
                        }
                    }
                }

            }
        }
        String fileName = "院级督导项目统计表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.setCookie(response);
        wb.write(response.getOutputStream());
    }

    /**
     * 项目管理
     */
    @RequestMapping(value = "/subjectMain")
    public String subjectMain(Model model, String roleFlag) {
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId("320000");
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("currentTime", com.pinde.core.util.DateUtil.getYear());
        return "jsres/hospital/supervisio/subjectMain";
    }

    @RequestMapping(value = "/hospitalSubjectMain")
    public String hospitalSubjectMain(Model model) {
        List<SysDept> deptList = supervisioBiz.selectDeptByOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("deptList",deptList);
        return "jsres/hospital/supervisio/hospitalSubjectMain";
    }

    @RequestMapping(value = "/hospitalStatisticsMain")
    public String hospitalStatisticsMain(Model model) {
        List<SysDept> deptList = supervisioBiz.selectDeptByOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("deptList",deptList);
        return "jsres/hospital/supervisio/hospitalStatisticsMain";
    }



    /**
     *省级督导 项目新增、编辑
     */
    @RequestMapping(value = "/editSubject")
    public String editSubject(Model model, String subjectFlow, String type) {
        if ("edit".equals(type) && StringUtil.isNotBlank(subjectFlow)) {
            ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
            model.addAttribute("subject", subject);
            List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSupervisioUserListByFlowAndUserLevelID(subjectFlow);
            model.addAttribute("userList", userList);
        }
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId("320000");
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
  /*      List<SysOrg> sysOrgs = orgBiz.searchAllBaseCode();  //查询拥有基地代码的基地信息
        model.addAttribute("sysOrgs", sysOrgs);*/
        model.addAttribute("orgs", orgs);
        model.addAttribute("type", type);
        return "jsres/hospital/supervisio/editSubject";
    }

    @RequestMapping(value = "/chooseActivity")
    public String chooseActivity(Model model, String speName,String activityFlows) {
        SysDept dept = new SysDept();
        dept.setDeptName(speName);
        dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<Map<String, String>> speList = deptBiz.searchDeptByUnion(dept, "Y");

        if (null != speList && speList.size()>0){
            model.addAttribute("speList",speList);  //科室列表
            model.addAttribute("speOnly","Y");  //标识，表明是否有关联的科室
        }
        model.addAttribute("speName",speName);
        model.addAttribute("activityFlows",activityFlows);
        return "jsres/hospital/supervisio/chooseActivity";
    }

    @RequestMapping(value = "/activityList")
    public String activityList(Model model,String activityName,String userName,String startTime,String endTime,String activityFlows,
                               String deptFlow,String speName,String speOnly) {
        if (StringUtil.isNotBlank(speOnly) && speOnly.equals("Y")){
            Map<String,Object> param=new HashMap<>();
            SysUser curUser = GlobalContext.getCurrentUser();
            param.put("activityTypeId","1");
            param.put("activityStatus","pass");
            param.put("userFlow",curUser.getUserFlow());
            param.put("orgFlow", curUser.getOrgFlow());
            param.put("roleFlag","local");
            param.put("activityName",activityName);
            param.put("userName",userName);
            param.put("startTime",startTime);
            param.put("endTime",endTime);
            param.put("nowdate",DateUtil.getCurrDateTime2());
            if (StringUtil.isNotBlank(deptFlow)){
                String[] deptFlows = deptFlow.split(",");
                param.put("deptFlow",deptFlows);
            }else {
                SysDept dept = new SysDept();
                dept.setDeptName(speName);
                dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
                List<Map<String, String>> speList = deptBiz.searchDeptByUnion(dept, "Y");
                if (null != speList && speList.size()>0){
                    ArrayList<String> list = new ArrayList<>();
                    for (Map<String, String> map : speList) {
                        list.add(map.get("deptFlow"));
                    }
                    param.put("deptFlow",list);
                }
            }
            List<Map<String,Object>> list=activityBiz.supervisioFindActivityList(param);
            model.addAttribute("list",list);
        }
        model.addAttribute("activityFlows",activityFlows);
        return "jsres/hospital/supervisio/activityList";
    }

    @RequestMapping(value = "/chooseLeader")
    public String chooseLeader(String userFlows,Model model) {
        model.addAttribute("userFlows",userFlows);
        return "jsres/hospital/supervisio/chooseLeader";
    }


    /**
     * 院级督导  项目新增
     */
    @RequestMapping(value = "/addHospitalSubject")
    public String addHospitalSubject(Model model) {
        return "jsres/hospital/supervisio/editHospitalSubject";
    }

    /**
     * 院级督导  项目修改
     */
    @RequestMapping(value = "/editHospitalSubject")
    public String editHospitalSubject(Model model, String subjectFlow) {
        ResHospSupervSubject subject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        model.addAttribute("subject",subject);
        model.addAttribute("edit","Y");
        return "jsres/hospital/supervisio/editHospitalSubject";
    }

    @ResponseBody
    @RequestMapping(value = "/chooseTable")
    public List<ResHospScoreTable> chooseTable(String speId,String inspectionType) {
        //sql会查询 SPE_ID = '0000'，是通用表单
        return supervisioBiz.chooseTable(speId,inspectionType);
    }




    /*  *
     * 项目新建 查询基地代码或者基地
     * @param baseCode  根据基地代码查询基地
     * @param orgFlow   根据基地查询基地代码
     * @param model
     * @return*/
    @RequestMapping(value = "/searchBaseInfo")
    @ResponseBody
    public String searchBaseInfo(String baseCode,String orgFlow,Model model){
        if (baseCode!=null){
            List<SysOrgExt> sysOrgExts = sysOrgExtMapper.searchBase(baseCode);
            if (sysOrgExts.size()>0){
                //return orgList.get(0).getOrgFlow()+"_"+orgList.get(0).getOrgName();
                return sysOrgExts.get(0).getOrgFlow();
            }else {
                return GlobalConstant.OPERATE_FAIL;
            }
        }else if (orgFlow!=null){
            SysOrgExt orgExts = sysOrgExtMapper.searchOrgFlow(orgFlow);
            if (orgExts!=null||orgExts.getBaseCode()!=null){
                return orgExts.getBaseCode();
            }
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 新建督导项目，选专业或基地
     * @param orgFlow
     * @param type
     * @param subjectEdit
     * @param speId
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectSpeByOrgFlow")
    public String selectSpeByOrgFlow(String orgFlow, String type, String subjectEdit, String speId, Model model) {
        String currYear = com.pinde.core.util.DateUtil.getYear();
        //按基地新建项目
        if ("orgSubject".equals(subjectEdit)) {
            //基地下当前年所有专业
            if (StringUtil.isNotBlank(currYear) && StringUtil.isNotBlank(orgFlow)) {
//                List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(orgFlow, currYear);
                List<ResOrgSpeAssign> speAssignList = supervisioBiz.searchSpeAll();
                if (null != speAssignList && speAssignList.size() > 0) {
                    for (int i = 0; i < speAssignList.size(); i++) {
                        //3700是重症医学科，没有表单
                        if (speAssignList.get(i).getSpeId().equals("3700")) {
                            speAssignList.remove(i);
                        }
                    }
                    model.addAttribute("speList", speAssignList);
                }
            }
        }
        //按专业新建项目
        if ("speSubject".equals(subjectEdit)) {
            //基地下当前年所有专业
            if (StringUtil.isNotBlank(currYear) && StringUtil.isNotBlank(speId)) {
//                if (speId.equals("4000")) {
                List<SysOrgExt> orgExtList = sysOrgExtMapper.searchOrgList();
                if (null != orgExtList && orgExtList.size() > 0) {
                    model.addAttribute("orgList", orgExtList);
                }
//                } else {
//                    SysOrg sysorg = new SysOrg();
//                    sysorg.setOrgProvId("320000");
//                    sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
//                    List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
//                    model.addAttribute("orgList", orgs);
//
//                  /*  List<Map<String, Object>> speAssignList = supervisioUserBiz.searchSpeAssignBySpeIdAndYear(speId, currYear);
//                    if (null != speAssignList && speAssignList.size() > 0) {
//                        model.addAttribute("orgList", speAssignList);
//                    }*/
//                }
            }
        }
        if ("edit".equals(type)) {
            //基地当前年已勾选专业
            List<ResSupervisioSubject> speSubjectList = supervisioUserBiz.selectSubjectListByOrgFlow(orgFlow, currYear);
            model.addAttribute("oldSpeList", speSubjectList);
            //专业当前年已勾选基地
            List<ResSupervisioSubject> orgSubjectList = supervisioUserBiz.selectSubjectListBySpeId(speId, currYear);
            model.addAttribute("oldOrgList", orgSubjectList);
        }
        model.addAttribute("subjectEdit", subjectEdit);
        return "jsres/hospital/supervisio/selectSpe";
    }

    /**
     * 项目新增、编辑查询专业专家
     */
    @RequestMapping(value = "/selectUser")
    public String selectUser(String userName,String identification,Model model) {
        model.addAttribute("identification",identification);
        return "jsres/hospital/supervisio/subjectUser";
    }


    /**
     * 督导-新建项目选专家
     * @param userName
     * @param subjectFlow
     * @param trainingSpeId
     * @param userLevelId
     * @param model
     * @param currentPage
     * @param identification
     * @param request
     * @return
     */
    @RequestMapping(value = "/searchSubjectUser")
    public String searchSubjectUser(String userName, String subjectFlow, String trainingSpeId, String userLevelId,
                                    Model model, Integer currentPage,String identification, HttpServletRequest request) {
        Map<String, Object> param = new HashMap<>();
        param.put("speId", trainingSpeId);
        param.put("userName", userName);
        param.put("userLevelId", userLevelId);
        List<String> userLevelList = new ArrayList();
        userLevelList.add("management");
        userLevelList.add("expertLeader");
        userLevelList.add("baseExpert");
        userLevelList.add("basePer");
        param.put("userLevel", userLevelList);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<SysUser> userList = supervisioUserBiz.selectSupervisioUserList(param);
        model.addAttribute("userList", userList);
        model.addAttribute("identification",identification);
        model.addAttribute("subjectFlow", subjectFlow);
        if (StringUtil.isNotBlank(subjectFlow)) {
            List<String> subjectUserFlowList = supervisioUserBiz.selectSupervisioUserFlowListByFlow(subjectFlow);
            model.addAttribute("subjectUserList", subjectUserFlowList == null ? subjectUserFlowList : Arrays.asList(subjectUserFlowList));
        }
        return "jsres/hospital/supervisio/subjectUserList";
    }

    /**
     * 保存项目
     */
    @RequestMapping(value = {"/saveSubjectNew"}, method = RequestMethod.POST)
    @ResponseBody
    public String saveSubjectNew(ResSupervisioSubject subject, String[] speIds, String[] userFlows, String[] orgFlows, String subjectEdit) {
        if (Integer.parseInt(supervisioUserBiz.searchSubjectNameNum(subject.getSubjectName()))>=1){
            return GlobalConstant.CRM_CUSTOMER_NAME_EXIST;
        }
        subject.setSubjectEdit(subjectEdit);
        List<String> userFlowList=new ArrayList<>();
        if (userFlows!=null){
            userFlowList = Arrays.asList(userFlows);
        }
        if ("allSubject".equals(subject.getSubjectEdit())){
            SysOrg sysorg = new SysOrg();
            sysorg.setOrgProvId("320000");
            sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
            for (SysOrg org : orgs) {
                List<ResOrgSpeAssign> speAssignList = speAssignBiz.searchSpeAssign(org.getOrgFlow(), com.pinde.core.util.DateUtil.getYear());
                if (speAssignList!=null && speAssignList.size()>0){
                    String subjectActivitiFlows = PkUtil.getUUID();
                    for (ResOrgSpeAssign assign : speAssignList) {
                        if (assign.getSpeId().equals("3700")){
                            continue;
                        }
                        String subjectFlow = PkUtil.getUUID();
                        subject.setSubjectFlow(subjectFlow);
                        subject.setSpeId(assign.getSpeId());
                        subject.setSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(assign.getSpeId()));
                        //保存督导
                        if (null != userFlowList && userFlowList.size() > 0) {
                            for (String userFlow : userFlows) {
                                SysSupervisioUser user = supervisioUserBiz.findByUserFlow(userFlow);
                                ResSupervisioSubjectUser subjectUser = new ResSupervisioSubjectUser();
                                subjectUser.setSubjectFlow(subjectFlow);
                                subjectUser.setSubjectName(subject.getSubjectName());
                                subjectUser.setUserFlow(userFlow);
                                subjectUser.setUserName(user.getUserName());
                                subjectUser.setSubjectActivitiFlows(subjectActivitiFlows);
                                supervisioUserBiz.saveSubjectUser(subjectUser);
                            }
                        }
                        //专业基地管理员
                        ResSupervisioSubjectUser subjectUser = new ResSupervisioSubjectUser();
                        subjectUser.setSubjectActivitiFlows(subjectActivitiFlows);
                        subjectUser.setSubjectFlow(subjectFlow);
                        subjectUser.setSubjectName(subject.getSubjectName());
                        SysUser user = supervisioUserBiz.findUserByUserCode(org.getOrgFlow()+assign.getSpeId());
                        subjectUser.setUserFlow(user.getUserFlow());
                        subjectUser.setUserName(user.getUserName());

                        supervisioUserBiz.saveSubjectUser(subjectUser);

                        int peopleNum = userFlowList.size();
                        subject.setEvaluationNum(peopleNum + "");
                        subject.setSubjectActivitiFlows(subjectActivitiFlows);
                        SysOrgExt orgExt = sysOrgExtMapper.searchOrgFlow(org.getOrgFlow());
                        subject.setBaseCode(orgExt.getBaseCode());
                        subject.setOrgName(org.getOrgName());
                        subject.setOrgFlow(org.getOrgFlow());
                        supervisioUserBiz.insertSubject(subject);
                    }
                }
            }
            return GlobalConstant.SAVE_SUCCESSED;
        }
        if ("orgSubject".equals(subject.getSubjectEdit())) {
            String subjectActivitiFlows = PkUtil.getUUID();
            String baseCode = sysOrgExtMapper.searchOrgFlow(subject.getOrgFlow()).getBaseCode();//该基地的基地代码
            List<String> speIdList = Arrays.asList(speIds);
            if (null != speIdList && speIdList.size() > 0) {
                //管理专家或者专业专家
                for (String speId : speIdList) {
                    logger.info("正在创建"+speId+"的项目");
                    String subjectFlow = PkUtil.getUUID();
                    subject.setSubjectFlow(subjectFlow);
                    subject.setSpeId(speId);
                    subject.setSpeName(DictTypeEnum.DoctorTrainingSpe.getDictNameById(speId));
                    //保存督导
                    boolean hasExpertLeader = false;
                    if (null != userFlowList && userFlowList.size() > 0) {
                        for (String userFlow : userFlows) {
                            SysUser user = userBiz.readSysUser(userFlow);

                            if("expertLeader".equals(user.getUserLevelId())) {
                                hasExpertLeader = true;
                            }

                            ResSupervisioSubjectUser subjectUser = new ResSupervisioSubjectUser();
                            subjectUser.setSubjectFlow(subjectFlow);
                            subjectUser.setSubjectName(subject.getSubjectName());
                            subjectUser.setUserFlow(userFlow);
                            subjectUser.setUserName(user.getUserName());
                            subjectUser.setSubjectActivitiFlows(subjectActivitiFlows);
                            supervisioUserBiz.saveSubjectUser(subjectUser);
                        }
                    }

                    if(!hasExpertLeader) {
                        return GlobalConstant.NOT_BASE_EXPERT;
                    }
                    //专业基地管理员
                    /*ResSupervisioSubjectUser subjectUser = new ResSupervisioSubjectUser();
                    subjectUser.setSubjectActivitiFlows(subjectActivitiFlows);
                    subjectUser.setSubjectFlow(subjectFlow);
                    subjectUser.setSubjectName(subject.getSubjectName());
                    SysUser user = supervisioUserBiz.findUserByUserCode(baseCode + speId); //查询专业基地专家
                    if (user==null){
                        return GlobalConstant.NOT_BASE_EXPERT;
                    }
                    subjectUser.setUserFlow(user.getUserFlow());
                    subjectUser.setUserName(user.getUserName());
                    supervisioUserBiz.saveSubjectUser(subjectUser);*/

                    int peopleNum = userFlowList.size();
                    subject.setEvaluationNum(peopleNum + "");
                    subject.setSubjectActivitiFlows(subjectActivitiFlows);
                    supervisioUserBiz.insertSubject(subject);
                    logger.info(speId+"的项目创建完成");
                }
                return GlobalConstant.SAVE_SUCCESSED;
            }
            return GlobalConstant.SAVE_FAIL;
        }
        if ("speSubject".equals(subject.getSubjectEdit())) {
            List<String> orgFlowList = Arrays.asList(orgFlows);
            if (null != orgFlowList && orgFlowList.size() > 0) {
                for (String orgFlow : orgFlowList) {
                    String subjectActivitiFlows = PkUtil.getUUID();
                    String subjectFlow = PkUtil.getUUID();
                    subject.setSubjectFlow(subjectFlow);
                    subject.setOrgFlow(orgFlow);
                    SysOrgExt orgExt = sysOrgExtMapper.searchOrgFlow(orgFlow);
                    subject.setOrgName(orgExt.getOrgName());
                    if (orgExt.getBaseCode()!=null){
                        subject.setBaseCode(orgExt.getBaseCode());
                    }else {
                        subject.setBaseCode(null);
                    }
                    //保存督导
                    if (null != userFlowList && userFlowList.size() > 0) {
                        for (String userFlow : userFlows) {
                            SysUser user = userBiz.readSysUser(userFlow);
                            ResSupervisioSubjectUser subjectUser = new ResSupervisioSubjectUser();
                            subjectUser.setSubjectFlow(subjectFlow);
                            subjectUser.setSubjectName(subject.getSubjectName());
                            subjectUser.setUserFlow(userFlow);
                            subjectUser.setSubjectActivitiFlows(subjectActivitiFlows);
                            subjectUser.setUserName(user.getUserName());
                            supervisioUserBiz.saveSubjectUser(subjectUser);
                        }
                    }
                    //专业基地管理员
                    ResSupervisioSubjectUser subjectUser = new ResSupervisioSubjectUser();
                    subjectUser.setSubjectActivitiFlows(subjectActivitiFlows);
                    subjectUser.setSubjectFlow(subjectFlow);
                    subjectUser.setSubjectName(subject.getSubjectName());
                    SysUser user = supervisioUserBiz.findUserByUserCode(orgExt.getBaseCode()+subject.getSpeId());
                    subjectUser.setUserFlow(user.getUserFlow());
                    subjectUser.setUserName(user.getUserName());
                    supervisioUserBiz.saveSubjectUser(subjectUser);
                    int peopleNum = userFlowList.size();
                    subject.setEvaluationNum(peopleNum + "");
                    subject.setSubjectActivitiFlows(subjectActivitiFlows);
                    supervisioUserBiz.insertSubject(subject);
                }
                return GlobalConstant.SAVE_SUCCESSED;
            }
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //院级督导  保存、修改项目
    @RequestMapping(value = {"/saveHospitalSubject"}, method = RequestMethod.POST)
    @ResponseBody
    public String saveHospitalSubject(ResHospSupervSubject subject,String userName,String userFlow,String fileRoute) {
        ResHospSupervSubject hospSupervSubject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subject.getSubjectFlow());
        //指定专家
        if (StringUtil.isNotBlank(subject.getOrderAction()) && subject.getOrderAction().equals("appoint")){
            if (StringUtil.isNotBlank(userName) && StringUtil.isNotBlank(userFlow)){
                String[] userFlows = userFlow.split(",");
                if (userFlows.length==4){   //必须是两个专家
                    SysUser user1 = userBiz.readSysUser(userFlows[0]);
                    hospSupervSubject.setLeaderOneFlow(user1.getUserFlow());
                    hospSupervSubject.setLeaderOneName(user1.getUserName());
                    SysUser user2 = userBiz.readSysUser(userFlows[1]);
                    hospSupervSubject.setLeaderTwoFlow(user2.getUserFlow());
                    hospSupervSubject.setLeaderTwoName(user2.getUserName());
                    SysUser user3 = userBiz.readSysUser(userFlows[2]);
                    hospSupervSubject.setLeaderThreeFlow(user3.getUserFlow());
                    hospSupervSubject.setLeaderThreeName(user3.getUserName());
                    SysUser user4 = userBiz.readSysUser(userFlows[3]);
                    hospSupervSubject.setLeaderFourFlow(user4.getUserFlow());
                    hospSupervSubject.setLeaderFourName(user4.getUserName());
                }else if (userFlows.length==3){
                    SysUser user1 = userBiz.readSysUser(userFlows[0]);
                    hospSupervSubject.setLeaderOneFlow(user1.getUserFlow());
                    hospSupervSubject.setLeaderOneName(user1.getUserName());
                    SysUser user2 = userBiz.readSysUser(userFlows[1]);
                    hospSupervSubject.setLeaderTwoFlow(user2.getUserFlow());
                    hospSupervSubject.setLeaderTwoName(user2.getUserName());
                    SysUser user3 = userBiz.readSysUser(userFlows[2]);
                    hospSupervSubject.setLeaderThreeFlow(user3.getUserFlow());
                    hospSupervSubject.setLeaderThreeName(user3.getUserName());
                    hospSupervSubject.setLeaderFourFlow("");
                    hospSupervSubject.setLeaderFourName("");
                }else if (userFlows.length==2){   //必须是两个专家
                    SysUser user1 = userBiz.readSysUser(userFlows[0]);
                    hospSupervSubject.setLeaderOneFlow(user1.getUserFlow());
                    hospSupervSubject.setLeaderOneName(user1.getUserName());
                    SysUser user2 = userBiz.readSysUser(userFlows[1]);
                    hospSupervSubject.setLeaderTwoFlow(user2.getUserFlow());
                    hospSupervSubject.setLeaderTwoName(user2.getUserName());
                    hospSupervSubject.setLeaderThreeFlow("");
                    hospSupervSubject.setLeaderThreeName("");
                    hospSupervSubject.setLeaderFourFlow("");
                    hospSupervSubject.setLeaderFourName("");
                }else if (userFlows.length==1){
                    SysUser user1 = userBiz.readSysUser(userFlows[0]);
                    hospSupervSubject.setLeaderOneFlow(user1.getUserFlow());
                    hospSupervSubject.setLeaderOneName(user1.getUserName());
                    hospSupervSubject.setLeaderTwoFlow("");
                    hospSupervSubject.setLeaderTwoName("");
                    hospSupervSubject.setLeaderThreeFlow("");
                    hospSupervSubject.setLeaderThreeName("");
                    hospSupervSubject.setLeaderFourFlow("");
                    hospSupervSubject.setLeaderFourName("");
                }
            }
        }else {
            hospSupervSubject.setLeaderOneFlow("");
            hospSupervSubject.setLeaderTwoFlow("");
            hospSupervSubject.setLeaderThreeFlow("");
            hospSupervSubject.setLeaderFourFlow("");
        }
        hospSupervSubject.setOrderAction(subject.getOrderAction());
        hospSupervSubject.setSpeId(subject.getSpeId());
        hospSupervSubject.setSpeName(subject.getSpeName());
        hospSupervSubject.setScoreTable(fileRoute);
        hospSupervSubject.setModifyTime(DateUtil.getCurrDateTime2());
        hospSupervSubject.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        hospSupervSubject.setMatching("已匹配");
        int num = supervisioUserBiz.updateHospSupervisioBySubjectFlow(hospSupervSubject);
        if (num==1){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 修改项目
     */
    @RequestMapping(value = {"/updateSubject"}, method = RequestMethod.POST)
    @ResponseBody
    public String updateSubject(ResSupervisioSubject subject, String[] userFlows, String subjectEditFlag,String suAoth) {
        ResSupervisioSubject oldSubject = supervisioUserBiz.selectSubjectByFlow(subject.getSubjectFlow());
        subject.setSubjectName(oldSubject.getSubjectName());
        subject.setOrgFlow(oldSubject.getOrgFlow());
        subject.setOrgName(oldSubject.getOrgName());
        subject.setSpeId(oldSubject.getSpeId());
        subject.setSpeName(oldSubject.getSpeName());
        subject.setBaseCode(oldSubject.getBaseCode());
        subject.setSubjectActivitiFlows(oldSubject.getSubjectActivitiFlows());
        subject.setSubjectEdit(subjectEditFlag);
        subject.setRecordStatus(GlobalConstant.FLAG_Y);
        //基地自己的项目修改
        if (StringUtil.isNotBlank(suAoth) && suAoth.equals("Y")){
            subject.setSupervisioAuthority(GlobalConstant.FLAG_Y);
        }
        List<String> userFlowList=new ArrayList<>();
        if (userFlows!=null){
            userFlowList = Arrays.asList(userFlows);
        }
        //删除原记录
        List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSupervisioUserListByFlow(subject.getSubjectFlow());
        if (null != userList && userList.size() > 0) {
            supervisioUserBiz.delSubjectUserBySubjectFlow(subject.getSubjectFlow());
        }
        //保存督导
        if (null != userFlowList && userFlowList.size() > 0) {
            for (String userFlow : userFlows) {
                SysUser user = userBiz.readSysUser(userFlow);
                ResSupervisioSubjectUser subjectUser = new ResSupervisioSubjectUser();
                subjectUser.setSubjectFlow(subject.getSubjectFlow());
                subjectUser.setSubjectName(subject.getSubjectName());
                subjectUser.setUserFlow(userFlow);
                subjectUser.setSubjectActivitiFlows(oldSubject.getSubjectActivitiFlows());
                subjectUser.setUserName(user.getUserName());
                supervisioUserBiz.saveSubjectUser(subjectUser);
            }
        }
        int peopleNum = userFlowList.size();
        subject.setEvaluationNum(peopleNum + "");
        int num = supervisioUserBiz.updateSubject(subject);
        //专业基地管理员
        ResSupervisioSubjectUser subjectUser = new ResSupervisioSubjectUser();
        subjectUser.setSubjectActivitiFlows(subject.getSubjectActivitiFlows());
        subjectUser.setSubjectFlow(subject.getSubjectFlow());
        subjectUser.setSubjectName(subject.getSubjectName());
        SysUser user = supervisioUserBiz.findUserByUserCode(subject.getBaseCode() + subject.getSpeId());
        if (user==null){
            return GlobalConstant.NOT_BASE_EXPERT;
        }
        subjectUser.setUserFlow(user.getUserFlow());
        subjectUser.setUserName(user.getUserName());
        int userNum = supervisioUserBiz.saveSubjectUser(subjectUser);
        if (num > 0 && userNum>0) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 删除项目
     */
    @RequestMapping(value = {"/deleteSubject"}, method = RequestMethod.POST)
    @ResponseBody
    public String deleteSubject(String subjectFlow) {
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        //删除原记录
        List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSupervisioUserListByFlow(subject.getSubjectFlow());
        if (null != userList && userList.size() > 0) {
            supervisioUserBiz.delSubjectUserBySubjectFlow(subject.getSubjectFlow());
        }
        subject.setRecordStatus(GlobalConstant.FLAG_N);
        int num = supervisioUserBiz.updateSubject(subject);
        if (num > 0) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 管理员项目管理-查看评分
     */
    @RequestMapping(value = "/showPlanInfoMian")
    public String showPlanInfoMian(Model model, String speId, String userFlow, String subjectFlow, String roleFlag,
                                   String subjectActivitiFlows,String manageUserFlow) {
        //所有专家
        List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSupervisioUserListByFlow(subjectFlow);
        //管理专家或卫健委查看评分，可以查看所有的专家评分
        if (roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL)|| roleFlag.equals("management")){
            for (int i = 0; i < userList.size(); i++) {
                SysUser user = userBiz.readSysUser(userList.get(i).getUserFlow());
                userList.get(i).setUserName(user.getUserName());
            }
        }
        //如果是专业专家和基地查看评分，只能看专业表
        if ((!roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL))&& (!roleFlag.equals("management"))){
            for (int i = 0; i < userList.size(); i++) {
                SysUser user = userBiz.readSysUser(userList.get(i).getUserFlow());
                if (!user.getUserLevelId().equals("expertLeader") && !user.getUserLevelId().equals("baseExpert")){
                    userList.remove(i);
                    i--;
                    continue;
                }
                userList.get(i).setUserName(user.getUserName());
            }
        }
        if (manageUserFlow.equals("undefined")){
            manageUserFlow=null;
        }
        if (roleFlag.equals("local")){
            ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
            SysUser sysUser = supervisioUserBiz.findUserByUserCode(subject.getBaseCode() + subject.getSpeId());
            if (sysUser!=null){
                userFlow=sysUser.getUserFlow();
                ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(sysUser.getUserFlow(), subjectFlow);
                model.addAttribute("userSubmit",subjectUser.getEvaluationDate()==null?"Y":"N");
            }
        }
        ResSupervisioSubject resSupervisioSubject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        model.addAttribute("baseManageSubmit",resSupervisioSubject.getBaseManageSubmit()==null?"Y":"N");  //基地自己评审的管理表是否提交
        model.addAttribute("userList", userList);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("speId", speId);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("manageUserFlow",manageUserFlow);
        model.addAttribute("subjectActivitiFlows",subjectActivitiFlows);
        return "jsres/hospital/supervisio/searchPlanInfoMain";
    }


    @RequestMapping(value = "/hospitalShowPlanInfo")
    public String hospitalShowPlanInfo(Model model, String subjectFlow){
        ResHospSupervSubject subject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        if (StringUtil.isNotBlank(subject.getLeaderOneFlow())){
            model.addAttribute("leaderOneFlow",subject.getLeaderOneFlow());
            model.addAttribute("leaderOneName",subject.getLeaderOneName());
            if (StringUtil.isNotBlank(subject.getLeaderOneScore())){
                model.addAttribute("leaderOneScore",subject.getLeaderOneScore());
            }
        }
        if (StringUtil.isNotBlank(subject.getLeaderTwoFlow())){
            model.addAttribute("leaderTwoFlow",subject.getLeaderTwoFlow());
            model.addAttribute("leaderTwoName",subject.getLeaderTwoName());
            if (StringUtil.isNotBlank(subject.getLeaderTwoScore())){
                model.addAttribute("leaderTwoScore",subject.getLeaderTwoScore());
            }
        }
        if (StringUtil.isNotBlank(subject.getLeaderThreeFlow())){
            model.addAttribute("leaderThreeFlow",subject.getLeaderThreeFlow());
            model.addAttribute("leaderThreeName",subject.getLeaderThreeName());
            if (StringUtil.isNotBlank(subject.getLeaderThreeScore())){
                model.addAttribute("leaderThreeScore",subject.getLeaderThreeScore());
            }
        }
        if (StringUtil.isNotBlank(subject.getLeaderFourFlow())){
            model.addAttribute("leaderFourFlow",subject.getLeaderFourFlow());
            model.addAttribute("leaderFourName",subject.getLeaderFourName());
            if (StringUtil.isNotBlank(subject.getLeaderFourScore())){
                model.addAttribute("leaderFourScore",subject.getLeaderFourScore());
            }
        }
        model.addAttribute("speId",subject.getSpeId());
        model.addAttribute("fileRoute",subject.getSpeId()+"_jx");
        model.addAttribute("subjectFlow",subjectFlow);
        return "jsres/hospital/supervisio/hospitalSearchPlanInfoMain";
    }

    /**
     * 管理员项目管理-查看评分详情
     */
    @RequestMapping(value = "/searchPlanInfo")
    public String searshowPlanInfoMianchPlanInfo(Model model, String speId, String userFlow, String subjectFlow, String roleFlag,String subjectActivitiFlows,
                                                 String manageUserFlow,String isLocalManage ) {
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        SysOrg org = orgBiz.readSysOrg(subject.getOrgFlow());
        model.addAttribute("orgName", org.getOrgName());
        model.addAttribute("orgCityName", org.getOrgCityName());
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("isRead", GlobalConstant.RECORD_STATUS_Y);
        model.addAttribute("subjectFlow", subjectFlow);
        //基地管理表
        if (StringUtil.isNotBlank(isLocalManage) && isLocalManage.equals("Y")){
            ResEvaluationScore searchLocalScore = new ResEvaluationScore();
            searchLocalScore.setSubjectFlow(subject.getSubjectActivitiFlows());
            searchLocalScore.setOrgFlow(subject.getOrgFlow());
            searchLocalScore.setSpeUserFlow(subject.getOrgFlow());
            searchLocalScore.setEvaluationYear(subject.getSubjectYear());
            List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchLocalScore);
            model.addAttribute("localEvaluationScoreList", ownerEvaluationScoreList);
            model.addAttribute("editFlag", "N");
            model.addAttribute("isLocalManage",isLocalManage);
            return "jsres/assess/evaluationInfo_4000";
        }

        //管理总表评分情况
        if (StringUtil.isNotBlank(manageUserFlow)){
            //将管理专家的userflow转换成list
            List<String> manageUserFlowList= new ArrayList<>(Arrays.asList(manageUserFlow.split(",")));
            //去除未提交分数的专家
            for (int i = 0; i < manageUserFlowList.size(); i++) {
                List<ResSupervisioSubjectUser> subjectUserList = supervisioUserBiz.selectSubjectUserBysubjectActivitiFlows(manageUserFlowList.get(i), subjectActivitiFlows);
                if (subjectUserList!=null ){
                    if (StringUtil.isBlank(subjectUserList.get(0).getEvaluationDate())){
                        manageUserFlowList.remove(manageUserFlowList.get(i));
                        i--;        //将元素移除，需要i--，否则出现下标越界问题
                    }
                }
            }
            HashMap<String, String> scoreMap = new HashMap<>();
            //遍历查询每一个管理专家的各项评分情况
            for (int i = 0; i < manageUserFlowList.size(); i++) {
                ResEvaluationScore searchScore=new  ResEvaluationScore();
                searchScore.setSubjectFlow(subjectActivitiFlows);
                searchScore.setSpeUserFlow(manageUserFlowList.get(i));
                List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
                for (ResEvaluationScore resEvaluationScore:ownerEvaluationScoreList) {
                    //判断itemID是否存在，如果存在需要累计score，不存在就直接添加
                    if (scoreMap.containsKey(resEvaluationScore.getItemId())){
                        String itemSocre =scoreMap.get(resEvaluationScore.getItemId());
                        BigDecimal oldScore = new BigDecimal(itemSocre);
                        scoreMap.put(resEvaluationScore.getItemId(),String.valueOf(oldScore.add(new BigDecimal(resEvaluationScore.getSpeScore()))));
                    }else {
                        scoreMap.put(resEvaluationScore.getItemId(),resEvaluationScore.getSpeScore());
                    }
                }
            }
            List<ResEvaluationScore> ownerScoreList=new ArrayList<>();
            for (Map.Entry<String, String> enrty:scoreMap.entrySet()) {
                ResEvaluationScore evaluationScore=new ResEvaluationScore();
                evaluationScore.setItemId(enrty.getKey());
                evaluationScore.setSpeScore(String.valueOf(new BigDecimal(enrty.getValue()).divide(new BigDecimal(manageUserFlowList.size()))));
                ownerScoreList.add(evaluationScore);
            }
            ResEvaluationScore searchScore = new ResEvaluationScore();
            searchScore.setOrgFlow(subject.getOrgFlow());
            searchScore.setEvaluationYear(subject.getSubjectYear());
            searchScore.setSubjectFlow(subjectActivitiFlows);
            searchScore.setSpeUserFlow(subject.getOrgFlow());
            List<ResEvaluationScore> localEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
            if (null != localEvaluationScoreList && localEvaluationScoreList.size() > 0) {
                model.addAttribute("localEvaluationScoreList", localEvaluationScoreList);
            }

            model.addAttribute("ownerScoreList", ownerScoreList);
            model.addAttribute("editFlag", "N");
            //总表的标识，为了区分是总表还是单个专家的表，以便打印
            model.addAttribute("manageUserFlow",manageUserFlow);
            return "jsres/assess/evaluationInfo_4000";
        }

        List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSupervisioUserListByFlow(subjectFlow);
        model.addAttribute("userList", userList);
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("speId", speId);


        //查询专家评分是否提交  提交不能编辑
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        model.addAttribute("subjectUser", subjectUser);
        if (null != subjectUser && StringUtil.isNotBlank(subjectUser.getEvaluationDate())) {
            model.addAttribute("editFlag", "N");
            model.addAttribute("evaluationDate", com.pinde.core.util.DateUtil.parseDate(subjectUser.getEvaluationDate(),"yyyy-MM-dd"));
            if (!roleFlag.equals("baseExpert")){
                //查询专家签名
                SysUser user = userBiz.readSysUser(userFlow);
                if (null !=user){
                    if (null !=user.getUserSignUrl()){
                        model.addAttribute("speSignUrl", user.getUserSignUrl());
                    }
                    model.addAttribute("user", user);
                }
            }
        }

        ResEvaluationScore searchScore = new ResEvaluationScore();
        searchScore.setOrgFlow(subject.getOrgFlow());
        searchScore.setEvaluationYear(subject.getSubjectYear());
        searchScore.setSpeUserFlow(userFlow);

        //如果是管理专家，应该查询管理表信息
        SysUser supervisioUser = userBiz.readSysUser(userFlow);
        if (supervisioUser!=null && supervisioUser.getUserLevelId().equals("management")){
            //查询管理评分  管理表不分专业，使用不需要SpeId，并且subjectFlow变成了subjectActivitiFlows
            searchScore.setSubjectFlow(subjectActivitiFlows);
            List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
            if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
                model.addAttribute("ownerScoreList", ownerEvaluationScoreList);
            }
            searchScore.setSpeUserFlow(subject.getOrgFlow());
            List<ResEvaluationScore> localEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
            if (null != localEvaluationScoreList && localEvaluationScoreList.size() > 0) {
                model.addAttribute("localEvaluationScoreList", localEvaluationScoreList);
            }
            return "jsres/assess/evaluationInfo_4000";
        }
        searchScore.setSpeId(subject.getSpeId());
        searchScore.setSubjectFlow(subjectFlow);
        //查询专家评分
        searchScore.setSpeScore("speScore");
        List<ResEvaluationScore> speEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
        if (null != speEvaluationScoreList && speEvaluationScoreList.size() > 0) {
            model.addAttribute("speScoreList", speEvaluationScoreList);
        }
        //查看专业专家已提交的人数
        String subExpertNum = supervisioUserBiz.searchSubExpertNum(subjectFlow);
        model.addAttribute("subExpertNum",subExpertNum);
        //查询自评分
        searchScore.setSpeUserFlow("Owner");
        searchScore.setSpeScore("");
        List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
        if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
            model.addAttribute("ownerScoreList", ownerEvaluationScoreList);
        }
        //为了显示存在问题详情
        ResSupervisioSubjectUser subjectUserInfo = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        model.addAttribute("subjectUser", subjectUserInfo);
     /*   List<JsresSupervisioFile> supervisioFileList = supervisioUserBiz.searchSubjectFile(subject.getSubjectYear(), subjectFlow, speId);
        if (supervisioFileList.size() > 0) {
            model.addAttribute("supervisioFileList", supervisioFileList);
        }*/
        return "jsres/assess/evaluationInfo_" + subject.getSpeId();
    }

    /**
     * 管理员项目管理-导出专业专家信息
     */
    @RequestMapping(value = "/exportSupervisioUserInfo")
    public void exportSupervisioUserInfo(HttpServletResponse response, Model model, HttpServletRequest request, String userFlow,String subjectName) throws Exception {
        List<String> userList = Arrays.asList(userFlow.split(","));
        List<SysUser> list = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            SysUser supervisioUser = userBiz.readSysUser(userList.get(i));
            list.add(supervisioUser);
        }
        String[] titles = null;
        titles = new String[]{
                "userName:专家名称",
                "userPhone:手机号码",
                "userLevelName:专家分类",
                "resTrainingSpeName:专业",
                "bankOfDeposit:开户行",
                "bankAccountNumber:银行卡账号"
        };
        String fileName = subjectName+"专家人员列表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream(), new String[]{"0"});
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    @RequestMapping(value = "/revokeScoreMain")
    public String revokeScoreMain(String subjectActivitiFlows,String subjectFlow, Model model) {
        List<SysUser> userList = supervisioUserBiz.selectUserBysubjectActivitiFlows(subjectFlow);
        model.addAttribute("userList",userList);
        model.addAttribute("subjectFlow",subjectFlow);
        model.addAttribute("subjectActivitiFlows",subjectActivitiFlows);
        return "jsres/hospital/supervisio/revokeScore";
    }

    /**
     *  撤销评分，但是各项评分保留 ----只有卫健委拥有此权限
     * @param subjectActivitiFlows
     * @param userFlow
     * @return
     */
    @RequestMapping(value = "/revokeUserScore")
    @ResponseBody
    public String revokeUserScore(String subjectActivitiFlows, String userFlow,String subjectFlow) {
        //撤销评分
        int num=0;
        List<String> userFlows= new ArrayList<>(Arrays.asList(userFlow.split(",")));
        for (int i = 0; i < userFlows.size(); i++) {
            //判断是哪一类专家
            SysUser user = userBiz.readSysUser(userFlows.get(i));
            if (user.getUserLevelId().equals("expertLeader")){
                ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSysSupervisioUserByUserFlowAndSubjectFlow(userFlows.get(i), subjectFlow);
                if (subjectUser!=null){
                    subjectUser.setEvaluationDate("");
                    subjectUser.setSpeScoreTotal("");
                    supervisioUserBiz.saveSubjectUser(subjectUser);
                    num++;
                }
            }else {
                List<ResSupervisioSubjectUser> subjectUserList = supervisioUserBiz.selectSubjectUserBysubjectActivitiFlows(userFlows.get(i), subjectActivitiFlows);
                if (subjectUserList.size()>0){
                    for (int j = 0; j < subjectUserList.size(); j++) {
                        ResSupervisioSubjectUser subjectUser = subjectUserList.get(j);
                        subjectUser.setEvaluationDate("");
                        subjectUser.setSpeScoreTotal("");
                        supervisioUserBiz.saveSubjectUser(subjectUser);
                        num++;
                    }
                }
            }
        }
        if (num>=userFlows.size()){
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     *
     * 专业评分-搜索栏
     *
     * @param model
     * @param speId
     * @param orgFlow
     * @param userFlow
     * @param subjectFlow  项目flow
     * @param roleFlag
     * @param subjectYear
     * @param subjectActivitiFlows   同一批创建的项目标识
     * @return
     */
    @RequestMapping(value = "/searchPlanScore")
    public String searchPlanScore(Model model, String speId, String orgFlow, String userFlow, String subjectFlow, String roleFlag,
                                  String subjectYear,String subjectActivitiFlows,String isLocalManage,String suAoth) throws ParseException {
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("suAoth", suAoth);
        model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("speId", speId);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("subjectActivitiFlows", subjectActivitiFlows);
        model.addAttribute("evaluationDate", com.pinde.core.util.DateUtil.parseDate(com.pinde.core.util.DateUtil.getCurrDate(), "yyyy-MM-dd"));

        //基地管理表
        if ((StringUtil.isNotBlank(isLocalManage) && isLocalManage.equals("Y") && roleFlag.equals("local"))
                ||(roleFlag.equals(GlobalConstant.USER_LIST_LOCAL)  && StringUtil.isNotBlank(suAoth) && suAoth.equals("Y"))){
            model.addAttribute("isLocalManage",isLocalManage);
            ResEvaluationScore searchLocalScore = new ResEvaluationScore();
            searchLocalScore.setSubjectFlow(subjectActivitiFlows);
            searchLocalScore.setOrgFlow(orgFlow);
            searchLocalScore.setSpeUserFlow(orgFlow);
            searchLocalScore.setEvaluationYear(subjectYear);
            List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchLocalScore);
            if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
                model.addAttribute("localEvaluationScoreList", ownerEvaluationScoreList);
            }
            if (StringUtil.isNotBlank(suAoth) && suAoth.equals("Y")){
                model.addAttribute("orgName", GlobalContext.getCurrentUser().getOrgName());
            }
            ResSupervisioSubject resSupervisioSubject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
            if (resSupervisioSubject != null){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date nowTime = sdf.parse(com.pinde.core.util.DateUtil.getCurrDateTime2());
                Date devTime = sdf.parse(resSupervisioSubject.getDevTimeClose());
                if ((StringUtil.isNotBlank(suAoth) && suAoth.equals("Y"))){
                    model.addAttribute("editFlag",  resSupervisioSubject.getSupervisioResults()==null?"":GlobalConstant.FLAG_N);
                }else {
                    if (nowTime.compareTo(devTime)<0){
                        model.addAttribute("editFlag", "");
                    }else {
                        model.addAttribute("editFlag", resSupervisioSubject.getBaseManageSubmit());
                    }
                }
            }
            model.addAttribute("userFlow",GlobalContext.getCurrentUser().getUserFlow());
            return "jsres/assess/evaluationInfo_4000";
        }

        model.addAttribute("userFlow", userFlow);
        if (!GlobalConstant.USER_LIST_LOCAL.equals(roleFlag) && !"baseExpert".equals(roleFlag)) {
            //查询专家签名
            SysUser user = userBiz.readSysUser(userFlow);
            model.addAttribute("speSignUrl", user.getUserSignUrl());
            model.addAttribute("user", user);
        }

        ResEvaluationScore searchScore = new ResEvaluationScore();
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        searchScore.setEvaluationYear(subject.getSubjectYear());
        searchScore.setOrgFlow(orgFlow);
        //判断当前用户：专业专家对专业表评分，管理专家对管理表评分
        if (roleFlag.equals("management")){
            searchScore.setSubjectFlow(subjectActivitiFlows);
            searchScore.setSpeUserFlow(userFlow);
            List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
            if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
                model.addAttribute("ownerScoreList", ownerEvaluationScoreList);
                //查询专家评分是否提交  提交不能编辑
                List<ResSupervisioSubjectUser> subjectUserList = supervisioUserBiz.selectSubjectUserBysubjectActivitiFlows(userFlow, subjectActivitiFlows);
                if (subjectUserList.size()>0){
                    for (ResSupervisioSubjectUser subjectUser:subjectUserList) {
                        if (subjectUser.getUserFlow().equals(userFlow)&& StringUtil.isNotBlank(subjectUser.getEvaluationDate())){
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date nowTime = sdf.parse(com.pinde.core.util.DateUtil.getCurrDateTime2());
                            Date devTime = sdf.parse(subject.getClosedTime());
                            if (nowTime.compareTo(devTime)>0){
                                model.addAttribute("editFlag", "N");
                            }
                            model.addAttribute("subjectUser", subjectUser);
                            break;
                        }
                    }
                }
            }
            searchScore.setSpeUserFlow(subject.getOrgFlow());
            List<ResEvaluationScore> localEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
            if (null != localEvaluationScoreList && localEvaluationScoreList.size() > 0) {
                model.addAttribute("localEvaluationScoreList", localEvaluationScoreList);
            }
            return "jsres/assess/evaluationInfo_4000";
        }
        searchScore.setSubjectFlow(subjectFlow);
        searchScore.setSpeId(speId);
        searchScore.setSpeUserFlow("Owner");
        List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
        if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
            model.addAttribute("ownerScoreList", ownerEvaluationScoreList);
        }
        searchScore.setSpeUserFlow(userFlow);
        List<ResEvaluationScore> speEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);

        //有评分
        if (null != speEvaluationScoreList && speEvaluationScoreList.size() > 0) {
            model.addAttribute("speScoreList", speEvaluationScoreList);
            if (!GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                //查询专家评分是否提交  提交不能编辑
                ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
                if (null != subjectUser && StringUtil.isNotBlank(subjectUser.getEvaluationDate())) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date nowTime = sdf.parse(com.pinde.core.util.DateUtil.getCurrDateTime2());
                    Date devTime = sdf.parse(subject.getClosedTime());
                    if (nowTime.compareTo(devTime)>0){
                        model.addAttribute("editFlag", "N");
                        model.addAttribute("isRead", "Y");
                    }
                }
                model.addAttribute("subjectUser", subjectUser);
            } else {
                ResSupervisioSubject resSupervisioSubject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
                if (StringUtil.isNotBlank(resSupervisioSubject.getReqedit())) {
                    model.addAttribute("editFlag", "N");
                } else {
                    model.addAttribute("editFlag", "Y");
                }
            }
        }

        //为了显示存在问题详情
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        model.addAttribute("subjectUser", subjectUser);
        List<JsresSupervisioFile> supervisioFileList = supervisioFileBiz.searchSubjectFile(subjectYear, subjectFlow, speId);
        if (supervisioFileList.size() > 0) {
            model.addAttribute("supervisioFileList", supervisioFileList);
        }
        SysOrg org = orgBiz.readSysOrg(orgFlow);
        model.addAttribute("orgName", org.getOrgName());
        model.addAttribute("orgCityName", org.getOrgCityName());
        //提前查看附件的总分（多个附表总分一起核算，如5-1、5-2）
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            resScheduleScore.setGrade(GlobalConstant.USER_LIST_LOCAL);
        } else if (roleFlag.equals("expertLeader") || roleFlag.equals("expert")) {
            resScheduleScore.setGrade("expert");
        }
        resScheduleScore.setScoreType("Totalled");
        resScheduleScore.setItemName("evaluationInfo_" + subject.getSpeId());
        List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleListTotalled(resScheduleScore);
        if (scoreList.size() > 0) {
            for (int i = 0; i < scoreList.size(); i++) {
                model.addAttribute(scoreList.get(i).getItemId(), scoreList.get(i).getScore());
            }
        }
        return "jsres/assess/evaluationInfo_" + subject.getSpeId();
    }

    /**
     *
     * 管理表、评分表评分保存
     */
    @RequestMapping(value = "/saveSpeScore")
    public @ResponseBody String saveSpeScore(String itemId, String itemName, String score, String orgFlow, String speId, String subjectFlow,
                                             String userFlow,String indicatorsNum,String subjectActivitiFlows,String coreIndicators,String isLocalManage) {
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        ResEvaluationScore searchScore = new ResEvaluationScore();
        //不为空表明是管理表，为空是专业表
        if (indicatorsNum!=null){
            searchScore.setSubjectFlow(subjectActivitiFlows);
            if (StringUtil.isNotBlank(isLocalManage) && isLocalManage.equals("Y")){
                searchScore.setSpeUserFlow(orgFlow);
            }else {
                searchScore.setSpeUserFlow(userFlow);
            }
        }else {
            searchScore.setSubjectFlow(subjectFlow);
            searchScore.setSpeUserFlow(userFlow);
        }
        searchScore.setItemId(itemId);
        searchScore.setOrgFlow(orgFlow);
        searchScore.setEvaluationYear(subject.getSubjectYear());

        ResEvaluationScore evaluationScore = supervisioUserBiz.searchEvaluationOwnerScoreByItemId(searchScore);
        if (evaluationScore == null) {
            evaluationScore = new ResEvaluationScore();
            evaluationScore.setSubjectName(subject.getSubjectName());
            evaluationScore.setItemId(itemId);
            evaluationScore.setOrgFlow(orgFlow);
            evaluationScore.setSpeId(speId);
            evaluationScore.setEvaluationYear(subject.getSubjectYear());
            if (StringUtil.isNotBlank(isLocalManage) && isLocalManage.equals("Y")){
                evaluationScore.setSpeUserFlow(orgFlow);
            }else {
                evaluationScore.setSpeUserFlow(userFlow);
            }
            evaluationScore.setSpeUserName(GlobalContext.getCurrentUser().getUserName());
            evaluationScore.setItemName(itemName);
            evaluationScore.setSpeScore(score);
            evaluationScore.setCoreIndicators(coreIndicators);
            //不为空表明是管理表，为空是专业表
            if (indicatorsNum!=null){
                //将subjectActivitiFlows赋值给SubjectFlow，查询直接以subjectFlow(也就是subjectActivitiFlows)查询即可
                evaluationScore.setSubjectFlow(subjectActivitiFlows);
            }else {
                searchScore.setSpeId(speId);
                evaluationScore.setSubjectFlow(subjectFlow);
            }
        } else {
            evaluationScore.setItemId(itemId);
            evaluationScore.setItemName(itemName);
            evaluationScore.setSpeScore(score);
            evaluationScore.setCoreIndicators(coreIndicators);
        }
        supervisioUserBiz.saveScore(evaluationScore);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping(value = "/saveSpeScoreTotal")
    public @ResponseBody
    String saveSpeScoreTotal(String userFlow, String subjectFlow, String speScoreTotal, String evaluationDate, String roleFlag,
                             String subjectActivitiFlows,String isLocalManage,String suAoth,String selfTotalled) {
        //基地（自己创建的项目）的管理表
        if (StringUtil.isNotBlank(suAoth) && suAoth.equals("Y")){
            //将评审结果换算等级保存到 subject表中 --督导结果
            ResEvaluationScore baseSearchScoreOwner=new ResEvaluationScore();
            baseSearchScoreOwner.setSubjectFlow(subjectActivitiFlows);
            baseSearchScoreOwner.setCoreIndicators("1");
            baseSearchScoreOwner.setSpeUserFlow(userFlow);
            List<ResEvaluationScore> baseEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(baseSearchScoreOwner);
            if (baseEvaluationScoreList!=null) {
                Integer ownerIndicatorsNum = baseEvaluationScoreList.size();
                String supervisioOwberResults = "";
                Float ownerSpeScoreTotal = Float.parseFloat(speScoreTotal);
                if (ownerSpeScoreTotal >= 80 && ownerIndicatorsNum >= 14) {
                    //合格
                    supervisioOwberResults = "qualified";
                } else if (ownerSpeScoreTotal < 80 && ownerSpeScoreTotal >= 70 && ownerIndicatorsNum >= 11) {
                    //基本合格
                    supervisioOwberResults = "basically";
                } else if ((ownerSpeScoreTotal < 70 && ownerSpeScoreTotal >= 60) || (ownerIndicatorsNum <= 10 && ownerIndicatorsNum > 7)) {
                    //限时整改（黄牌）
                    supervisioOwberResults = "yellowCard";
                } else if (ownerIndicatorsNum < 7 || ownerSpeScoreTotal < 60) {
                    //撤销资格（红牌）
                    supervisioOwberResults = "redCard";
                }
                List<ResSupervisioSubject> subjectList = supervisioUserBiz.selectBySubjectActivitiFlows(subjectActivitiFlows);
                if (subjectList.size()>0){
                    int successNum=0;
                    for (ResSupervisioSubject subject:subjectList) {
                        subject.setAvgScore(ownerSpeScoreTotal + "");
                        subject.setSupervisioResults(supervisioOwberResults);
                        supervisioUserBiz.saveSubject(subject);
                        successNum++;
                    }
                    if (successNum==subjectList.size()){
                        return GlobalConstant.SAVE_SUCCESSED;
                    }
                }
            }
            return GlobalConstant.SAVE_FAIL;
        }

        //基地（卫健委创建的项目）的管理表
        if (StringUtil.isNotBlank(isLocalManage) && isLocalManage.equals("Y")){
            ResSupervisioSubject resSupervisioSubject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
            List<ResSupervisioSubject> subjectList = supervisioUserBiz.selectBySubjectActivitiFlows(resSupervisioSubject.getSubjectActivitiFlows());
            if (subjectList!=null && subjectList.size()>0){
                int num=0;
                for (ResSupervisioSubject subject:subjectList) {
                    subject.setBaseManageScore(speScoreTotal);
                    subject.setBaseManageSubmit(GlobalConstant.FLAG_N);
                    supervisioUserBiz.updateSubject(subject);
                    num++;
                }
                //添加提交记录
                supervisioUserBiz.insertRecores(subjectActivitiFlows,userFlow,"local");

                if (num==subjectList.size()){
                    return GlobalConstant.SAVE_SUCCESSED;
                }
            }
            return GlobalConstant.SAVE_FAIL;
        }
        //基地-专业表
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            ResSupervisioSubject resSupervisioSubject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
            //未提交
            if (StringUtil.isBlank(resSupervisioSubject.getReqedit())) {
                resSupervisioSubject.setReqedit(GlobalConstant.RECORD_STATUS_Y);
                int i = supervisioUserBiz.saveSubject(resSupervisioSubject);
                if (i > 0) {
                    return GlobalConstant.SAVE_SUCCESSED;
                } else {
                    return GlobalConstant.SAVE_FAIL;
                }
            }
        }
        /**
         * 管理专家
         * 1、首先将自己的评审结果按照总分100和核心指标合格数换算督导结果，在专业评分中显示：ResSupervisioSubjectUser表中
         * 2、查询subjectActivitiFlows相同的并且已经提交的评分结果，换算督导结果，在项目管理中显示：ResSupervisioSubject表中
         */
        if (roleFlag!=null){
            //管理专家
            if (roleFlag.equals("management")){
                List<ResSupervisioSubjectUser> subjectUserList = supervisioUserBiz.selectSubjectUserBysubjectActivitiFlows(userFlow, subjectActivitiFlows);
                //将该专家评审的总分保存到数据库
                if (subjectUserList.size()>0){
                    //将自己的评审结果换算等级保存到 subject表中（为了在专业评分中查看）--督导结果
                    ResEvaluationScore searchScoreOwner=new ResEvaluationScore();
                    searchScoreOwner.setSubjectFlow(subjectActivitiFlows);
                    searchScoreOwner.setCoreIndicators("1");
                    searchScoreOwner.setSpeUserFlow(userFlow);
                    List<ResEvaluationScore> evaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScoreOwner);
                    if (evaluationScoreList!=null){
                        Integer ownerIndicatorsNum=evaluationScoreList.size();
                        String  supervisioOwberResults="";
                        Float ownerSpeScoreTotal=Float.parseFloat(speScoreTotal);
                        if (ownerSpeScoreTotal>=80 && ownerIndicatorsNum>=14){
                            //合格
                            supervisioOwberResults="qualified";
                        }else if (ownerSpeScoreTotal<80 && ownerSpeScoreTotal>=70 && ownerIndicatorsNum>=11){
                            //基本合格
                            supervisioOwberResults="basically";
                        }else if((ownerSpeScoreTotal<70 &&ownerSpeScoreTotal>=60) || (ownerIndicatorsNum<=10 && ownerIndicatorsNum>7)){
                            //限时整改（黄牌）
                            supervisioOwberResults="yellowCard";
                        }else if (ownerIndicatorsNum<7 ||ownerSpeScoreTotal<60){
                            //撤销资格（红牌）
                            supervisioOwberResults="redCard";
                        }
                        for (ResSupervisioSubjectUser subjectUser:subjectUserList) {
                            subjectUser.setSupervisioResults(supervisioOwberResults);
                            subjectUser.setSpeScoreTotal(speScoreTotal);
                            subjectUser.setEvaluationDate(evaluationDate);
                            supervisioUserBiz.saveSubjectUser(subjectUser);
                        }
                    }

                    //查询督导组员评分--管理专家
                    List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSubjectUserBySubjectActivitiFlows(subjectActivitiFlows);
                    //userList根据userflow去重，否则在计算平均分时候会造成分数不正确
                    List<String> disList = new ArrayList<>();
                    for (int i = 0; i < userList.size(); i++) {
                        if (disList.contains(userList.get(i).getUserFlow())){
                            userList.remove(i);
                            i--;
                            continue;
                        }
                        SysUser supervisioUser = userBiz.readSysUser(userList.get(i).getUserFlow());
                        if (supervisioUser.getUserLevelId().equals("expertLeader")){
                            userList.remove(i);
                            i--;
                            continue;
                        }
                        disList.add(userList.get(i).getUserFlow());
                        userList.get(i).setUserName(supervisioUser.getUserName());
                    }

                    BigDecimal scoreTotal = new BigDecimal(0);
                    BigDecimal IndicatorsNum = new BigDecimal(0);
                    int evaNum = 0;//已经提交的管理专家个数
                    if (null != userList && userList.size() > 0) {
                        //循环遍历，累加统计评审总分
                        for (ResSupervisioSubjectUser user : userList) {
                            if (StringUtil.isNotBlank(user.getEvaluationDate())&&user.getSpeScoreTotal() != null) {
                                scoreTotal=scoreTotal.add(new BigDecimal(user.getSpeScoreTotal()));
                                evaNum++;
                            }
                        }
                        //计算核心指标合格数（已提交的管理专家）
                        for (ResSupervisioSubjectUser user : userList) {
                            ResEvaluationScore searchScore=new ResEvaluationScore();
                            searchScore.setSubjectFlow(subjectActivitiFlows);
                            searchScore.setCoreIndicators("1");
                            searchScore.setSpeUserFlow(user.getUserFlow());
                            List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
                            if (ownerEvaluationScoreList!=null && ownerEvaluationScoreList.size()>0){
                                IndicatorsNum=IndicatorsNum.add(new BigDecimal(ownerEvaluationScoreList.size()));
                            }
                        }
                    }
                    //计算平均分并保存
                    BigDecimal total = scoreTotal;
                    BigDecimal num = new BigDecimal(evaNum);
                    BigDecimal result = total.divide(num, 2, BigDecimal.ROUND_HALF_UP);//平均分
                    IndicatorsNum=IndicatorsNum.divide(num, 2, BigDecimal.ROUND_HALF_UP);//核心指标合格的个数
                    String expertAvg="";
                    if (result.floatValue()>=80 && IndicatorsNum.floatValue()>=14){
                        //合格
                        expertAvg="qualified";
                    }else if (result.floatValue()<80 && result.floatValue()>=70 && IndicatorsNum.floatValue()>=11){
                        //基本合格
                        expertAvg="basically";
                    }else if((result.floatValue()<70 && result.floatValue()>=60) || (IndicatorsNum.floatValue()<=10 && IndicatorsNum.floatValue()>7)){
                        //限时整改（黄牌）
                        expertAvg="yellowCard";
                    }else if (IndicatorsNum.floatValue()<7 ||result.floatValue()<60){
                        //撤销资格（红牌）
                        expertAvg="redCard";
                    }
                    //查subjectActivitiFlows，设置subjectActivitiFlows相同的项目评分
                    List<ResSupervisioSubject> subjectList = supervisioUserBiz.selectBySubjectActivitiFlows(subjectActivitiFlows);
                    if (subjectList.size()>0){
                        int successNum=0;
                        for (ResSupervisioSubject subject:subjectList) {
                            subject.setAvgScore(result + "");
                            subject.setSupervisioResults(expertAvg);
                            //查看管理专家是否全部都已经提交表单
                            List<ResSupervisioSubjectUser> list = supervisioUserBiz.searchSubjectAndUserLevedId(subject.getSubjectFlow(), "management");
                            if (null!=list && list.size()>0){
                                int subNum=0;
                                for (int i = 0; i < list.size(); i++) {
                                    ResSupervisioSubjectUser subjectUser = list.get(i);
                                    if (StringUtil.isNotBlank(subjectUser.getEvaluationDate())){
                                        subNum++;
                                    }
                                }
                                if (num.compareTo(new BigDecimal(subNum))==0){
                                    subject.setManageAllSub("Y");
                                }
                            }
                            supervisioUserBiz.saveSubject(subject);
                            successNum++;
                        }
                        if (successNum==subjectList.size()){
                            supervisioUserBiz.insertRecores(subjectActivitiFlows,userFlow,roleFlag);
                            return GlobalConstant.SAVE_SUCCESSED;
                        }
                    }
                }
                return GlobalConstant.SAVE_FAIL;
            }
            //专业基地管理员
            if (roleFlag.equals("baseExpert")){
                ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
                if (subjectUser!=null){
                    subjectUser.setSpeScoreTotal(speScoreTotal);
                    subjectUser.setEvaluationDate(evaluationDate);
                    subjectUser.setSpeScoreTotal(selfTotalled);
                    supervisioUserBiz.saveSubjectUser(subjectUser);
                    //添加提交记录
                    ResSupervisioSubjectRecords records = new ResSupervisioSubjectRecords();
                    records.setRecordFlow(PkUtil.getUUID());
                    records.setRecordStatus("Y");
                    records.setCreateTime(com.pinde.core.util.DateUtil.getCurrDateTime());
                    records.setCreateUserFlow(userFlow);
                    records.setRoleFlag(roleFlag);
                    records.setUserFlow(userFlow);
                    records.setSubjectFlow(subjectFlow);
                    ResSupervisioSubjectRecords subjectRecords = supervisioUserBiz.selectRecords(subjectFlow,userFlow);
                    if (null!=subjectRecords){
                        records.setSubNum(String.valueOf(Integer.parseInt(subjectRecords.getSubNum())+1));
                    }else {
                        records.setSubNum("1");
                    }
                    subjectRecordsMapper.insert(records);
                    return GlobalConstant.SAVE_SUCCESSED;
                }
                return GlobalConstant.SAVE_FAIL;
            }
        }


        //专业专家
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        if (null != subjectUser) {
            subjectUser.setSpeScoreTotal(speScoreTotal);
            subjectUser.setEvaluationDate(evaluationDate);
            supervisioUserBiz.saveSubjectUser(subjectUser);
            //查询督导组员是否全部提交
            List<ResSupervisioSubjectUser> userList = supervisioUserBiz.selectSupervisioUserListByFlow(subjectFlow);
            String isSubmit = "Y";
            Integer scoreTotal = 0;
            int evaNum = userList.size();
            if (null != userList && userList.size() > 0) {
                for (ResSupervisioSubjectUser user : userList) {
                    if (StringUtil.isBlank(user.getEvaluationDate())) {
                        isSubmit = "N";
                        break;
                    } else {
                        if (user.getSpeScoreTotal() != null) {
                            scoreTotal += Integer.valueOf(user.getSpeScoreTotal());
                        }
                    }
                }
            } else {
                isSubmit = "N";
            }
            if (GlobalConstant.FLAG_Y.equals(isSubmit)) {
                //全部提交  计算平均分并保存
                BigDecimal total = new BigDecimal(scoreTotal);
                BigDecimal num = new BigDecimal(evaNum);
                BigDecimal result = total.divide(num, 2, BigDecimal.ROUND_HALF_UP);
                ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
                subject.setAvgScore(result + "");
                supervisioUserBiz.saveSubject(subject);
            }
            supervisioUserBiz.insertRecores(subjectFlow,userFlow,"expertLeader");
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value = "/saveSpeReason")
    public @ResponseBody
    String saveSpeReason(String itemId, String itemName, String reason, String orgFlow, String speId, String isLocalManage,
                         String subjectFlow, String userFlow,String roleFlag,String subjectActivitiFlows) throws Exception {
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        reason = java.net.URLDecoder.decode(reason, "UTF-8");
        reason=reason.replaceAll("\n","\\\\n");
//        reason = reason.replaceAll("(\r\n|\n)", "<br/>");
        ResEvaluationScore searchScore = new ResEvaluationScore();
        searchScore.setItemId(itemId);
        searchScore.setOrgFlow(orgFlow);
        searchScore.setEvaluationYear(subject.getSubjectYear());

        //管理表
        if (roleFlag!=null&& (roleFlag.equals("management") || roleFlag.equals("local"))){
            searchScore.setSubjectFlow(subjectActivitiFlows);
            if (StringUtil.isNotBlank(isLocalManage) && isLocalManage.equals("Y")){
                searchScore.setSpeUserFlow(orgFlow);
            }else {
                searchScore.setSpeUserFlow(userFlow);
            }
        }
        //专业表
        if (roleFlag==null){
            searchScore.setSubjectFlow(subjectFlow);
            searchScore.setSpeId(speId);
            searchScore.setSpeUserFlow(userFlow);
        }
        ResEvaluationScore evaluationScore = supervisioUserBiz.searchEvaluationOwnerScoreByItemId(searchScore);
        if (evaluationScore == null) {
            evaluationScore = new ResEvaluationScore();
            evaluationScore.setSubjectName(subject.getSubjectName());
            evaluationScore.setItemId(itemId);
            evaluationScore.setOrgFlow(orgFlow);
            evaluationScore.setEvaluationYear(subject.getSubjectYear());
            if (StringUtil.isNotBlank(isLocalManage) && isLocalManage.equals("Y")){
                evaluationScore.setSpeUserFlow(orgFlow);
            }else {
                evaluationScore.setSpeUserFlow(userFlow);
            }
            evaluationScore.setSpeUserName(GlobalContext.getCurrentUser().getUserName());
            evaluationScore.setItemName(itemName);
            evaluationScore.setSpeReason(reason);
            //管理表
            if (roleFlag!=null&& (roleFlag.equals("management") || roleFlag.equals("local"))){
                evaluationScore.setSubjectFlow(subjectActivitiFlows);
            }
            //专业表
            if (roleFlag==null){
                evaluationScore.setSubjectFlow(subjectFlow);
                evaluationScore.setSpeId(speId);
            }
        } else {
            evaluationScore.setItemId(itemId);
            evaluationScore.setItemName(itemName);
            evaluationScore.setSpeReason(reason);
        }
        supervisioUserBiz.saveScore(evaluationScore);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    //评估指标
    @RequestMapping(value = "/planMain")
    public String planMain(Model model, String roleFlag) {
        model.addAttribute("roleFlag", roleFlag);
        return "jsres/hospital/supervisio/planMain";
    }
    //评估指标-专业表单
    @RequestMapping(value = "/expertLeaderMain")
    public String expertLeaderMain(String roleFlag, Model model) {
        model.addAttribute("roleFlag", roleFlag);
        return "jsres/hospital/supervisio/expertLeaderMain";
    }

    @RequestMapping(value = "/managementMain")
    public String managementMain(String roleFlag, Model model) {
        model.addAttribute("roleFlag", roleFlag);
        return "jsres/hospital/supervisio/managementMain";
    }

    //评估指标-具体到哪一张表单
    @RequestMapping(value = "/searchPlan")
    public String searchPlan(Model model, String speId, String orgFlow, String userFlow, String subjectFlow, String roleFlag,String indicators) {
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("speId", speId);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("widthSize","Y");
        model.addAttribute("indicators",indicators);//表单样式问题 ：线框对齐
        return "jsres/assess/evaluationInfo_" + speId;
    }


    @RequestMapping(value = "/saveOwnerScore")
    public @ResponseBody
    String saveOwnerScore(String itemId, String itemName, String score, String orgFlow, String speId, String subjectFlow) {
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        ResEvaluationScore searchScore = new ResEvaluationScore();
        searchScore.setSubjectFlow(subjectFlow);
        searchScore.setItemId(itemId);
        searchScore.setOrgFlow(orgFlow);
        searchScore.setSpeId(speId);
        searchScore.setEvaluationYear(subject.getSubjectYear());
        searchScore.setSpeUserFlow("Owner");
        ResEvaluationScore evaluationScore = supervisioUserBiz.searchEvaluationOwnerScoreByItemId(searchScore);
        if (evaluationScore == null) {
            evaluationScore = new ResEvaluationScore();
            evaluationScore.setSubjectFlow(subjectFlow);
            evaluationScore.setSubjectName(subject.getSubjectName());
            evaluationScore.setItemId(itemId);
            evaluationScore.setItemName(itemName);
            evaluationScore.setOrgFlow(orgFlow);
            evaluationScore.setSpeId(speId);
            evaluationScore.setEvaluationYear(subject.getSubjectYear());
            evaluationScore.setOwnerScore(score);
        } else {
            evaluationScore.setItemId(itemId);
            evaluationScore.setItemName(itemName);
            evaluationScore.setOwnerScore(score);
        }
        supervisioUserBiz.saveScore(evaluationScore);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping("/saveSpeContent")
    @ResponseBody
    public String saveSpeContent(String userFlow, String subjectFlow, String speContent) throws Exception {
        speContent = java.net.URLDecoder.decode(speContent, "UTF-8");
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        if (null != subjectUser) {
            subjectUser.setSpeContent(speContent);
            supervisioUserBiz.saveSubjectUser(subjectUser);
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    @RequestMapping(value = "/scoreDownload")
    public String scoreDownload(HttpServletRequest request, HttpServletResponse response, String userFlow, String subjectFlow,
                                String roleFlag,String manageUserFlow,String isLocalManage) throws Exception {
        //定义数据容器
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //获取当前用户
        SysUser user = userBiz.readSysUser(userFlow);

        //新建word模板
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        //context获取路径
        ServletContext context = request.getServletContext();
        //水印
        String watermark = "";
        //文件名称
        String name = "";

        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        SysOrg org = orgBiz.readSysOrg(subject.getOrgFlow());
        dataMap.put("orgName", org.getOrgName());
        dataMap.put("orgCityName", org.getOrgCityName());

        //基地管理表
        if(StringUtil.isNotBlank(isLocalManage) && isLocalManage.equals("Y")){
            ResEvaluationScore searchLocalScore = new ResEvaluationScore();
            searchLocalScore.setSubjectFlow(subject.getSubjectActivitiFlows());
            searchLocalScore.setOrgFlow(subject.getOrgFlow());
            searchLocalScore.setSpeUserFlow(subject.getOrgFlow());
            searchLocalScore.setEvaluationYear(subject.getSubjectYear());
            List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchLocalScore);
            if (ownerEvaluationScoreList !=null && ownerEvaluationScoreList.size()>0){
                BigDecimal ownerScoreTotalA = new BigDecimal(0);
                BigDecimal ownerScoreTotalB = new BigDecimal(0);
                BigDecimal ownerScoreTotalAll = new BigDecimal(0);
                if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
                    for (ResEvaluationScore score : ownerEvaluationScoreList) {
                        dataMap.put(score.getItemId() + "ownerScore1", score.getSpeScore());
                        dataMap.put(score.getItemId() + "speReason", score.getSpeReason());
                        if (StringUtil.isNotBlank(score.getSpeScore())&&score.getItemId().startsWith("2022-6")) {
                            ownerScoreTotalA = ownerScoreTotalA.add(new BigDecimal(score.getSpeScore()));
                        }else if (StringUtil.isNotBlank(score.getSpeScore())){
                            ownerScoreTotalB = ownerScoreTotalB.add(new BigDecimal(score.getSpeScore()));
                        }
                    }
                }
                ownerScoreTotalAll=ownerScoreTotalA.add(ownerScoreTotalB);
                dataMap.put("localScoreTotalA", ownerScoreTotalA + "");
                dataMap.put("localScoreTotalB", ownerScoreTotalB + "");
                dataMap.put("localScoreTotalAll", ownerScoreTotalAll + "");


                String path = "/jsp/jsres/assess/evaluation_4000.docx";//模板
                name = "江苏省督导4000管理表.docx";

                temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
                if (temeplete != null) {
                    response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
                    response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                    ServletOutputStream out = response.getOutputStream();
                    (new SaveToZipFile(temeplete)).save(out);
                    out.flush();
                }
                return null;
            }
        }

        //管理总表打印
        if (StringUtil.isNotBlank(manageUserFlow)){
            //将管理专家的userflow转换成list
            List<String> manageUserFlowList= new ArrayList<>(Arrays.asList(manageUserFlow.split(",")));
            //去除未提交分数的专家
            String subjectActivitiFlows=supervisioUserBiz.selectSubjectByFlow(subjectFlow).getSubjectActivitiFlows();
            for (int i = 0; i < manageUserFlowList.size(); i++) {
                List<ResSupervisioSubjectUser> subjectUserList = supervisioUserBiz.selectSubjectUserBysubjectActivitiFlows(manageUserFlowList.get(i), subjectActivitiFlows);
                if (StringUtil.isBlank(subjectUserList.get(0).getEvaluationDate())){
                    manageUserFlowList.remove(manageUserFlowList.get(i));
                    i--;        //将元素移除，需要i--，否则出现下标越界问题
                }
            }
            HashMap<String, String> scoreMap = new HashMap<>();
            //遍历查询每一个管理专家的各项评分情况
            for (int i = 0; i < manageUserFlowList.size(); i++) {
                ResEvaluationScore searchScore=new ResEvaluationScore();
                searchScore.setSubjectFlow(subjectActivitiFlows);
                searchScore.setSpeUserFlow(manageUserFlowList.get(i));
                List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
                for (ResEvaluationScore resEvaluationScore:ownerEvaluationScoreList) {
                    //判断itemID是否存在，如果存在需要累计score，不存在就直接添加
                    if (scoreMap.containsKey(resEvaluationScore.getItemId())){
                        String itemSocre =scoreMap.get(resEvaluationScore.getItemId());
                        BigDecimal oldScore = new BigDecimal(itemSocre);
                        scoreMap.put(resEvaluationScore.getItemId(),String.valueOf(oldScore.add(new BigDecimal(resEvaluationScore.getSpeScore()))));
                    }else {
                        scoreMap.put(resEvaluationScore.getItemId(),resEvaluationScore.getSpeScore());
                    }
                }
            }
            List<ResEvaluationScore> ownerScoreList=new ArrayList<>();
            for (Map.Entry<String, String> enrty:scoreMap.entrySet()) {
                ResEvaluationScore evaluationScore=new ResEvaluationScore();
                evaluationScore.setItemId(enrty.getKey());
                evaluationScore.setSpeScore(String.valueOf(new BigDecimal(enrty.getValue()).divide(new BigDecimal(manageUserFlowList.size()))));
                ownerScoreList.add(evaluationScore);
            }
            BigDecimal ownerScoreTotalA = new BigDecimal(0);
            BigDecimal ownerScoreTotalB = new BigDecimal(0);
            BigDecimal ownerScoreTotalAll = new BigDecimal(0);
            if (null != ownerScoreList && ownerScoreList.size() > 0) {
                for (ResEvaluationScore score : ownerScoreList) {
                    dataMap.put(score.getItemId() + "ownerScore", score.getSpeScore());
                    dataMap.put(score.getItemId() + "speReason", score.getSpeReason());
                    if (StringUtil.isNotBlank(score.getSpeScore())&&score.getItemId().startsWith("2022-6")) {
                        ownerScoreTotalA = ownerScoreTotalA.add(new BigDecimal(score.getSpeScore()));
                    }else if (StringUtil.isNotBlank(score.getSpeScore())){
                        ownerScoreTotalB = ownerScoreTotalB.add(new BigDecimal(score.getSpeScore()));
                    }
                }
            }
            ownerScoreTotalAll=ownerScoreTotalA.add(ownerScoreTotalB);
            dataMap.put("ownerScoreTotalA", ownerScoreTotalA.setScale(2,BigDecimal.ROUND_HALF_UP) + "");
            dataMap.put("ownerScoreTotalB", ownerScoreTotalB.setScale(2,BigDecimal.ROUND_HALF_UP) + "");
            dataMap.put("ownerScoreTotalAll", ownerScoreTotalAll.setScale(2,BigDecimal.ROUND_HALF_UP) + "");
            ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
            dataMap.put("speContent", subjectUser.getSpeContent());
            dataMap.put("evaluationDate", subjectUser.getEvaluationDate());

            BigDecimal localScoreTotalA = new BigDecimal(0);
            BigDecimal localScoreTotalB = new BigDecimal(0);
            BigDecimal localScoreTotalAll = new BigDecimal(0);
            ResEvaluationScore searchScore = new ResEvaluationScore();
            searchScore.setEvaluationYear(subject.getSubjectYear());
            searchScore.setSubjectFlow(subject.getSubjectActivitiFlows());
            searchScore.setSpeUserFlow(subject.getOrgFlow());
            List<ResEvaluationScore> localEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
            if (null != localEvaluationScoreList && localEvaluationScoreList.size() > 0) {
                for (ResEvaluationScore score : localEvaluationScoreList) {
                    dataMap.put(score.getItemId() + "ownerScore1", score.getSpeScore());
                    if (StringUtil.isNotBlank(score.getSpeScore())&&score.getItemId().startsWith("2022-6")) {
                        localScoreTotalA = localScoreTotalA.add(new BigDecimal(score.getSpeScore()));
                    }else if (StringUtil.isNotBlank(score.getSpeScore())){
                        localScoreTotalB = localScoreTotalB.add(new BigDecimal(score.getSpeScore()));
                    }
                }
            }
            localScoreTotalAll=localScoreTotalA.add(localScoreTotalB);
            dataMap.put("localScoreTotalA", localScoreTotalA + "");
            dataMap.put("localScoreTotalB", localScoreTotalB + "");
            dataMap.put("localScoreTotalAll", localScoreTotalAll + "");

            String path = "/jsp/jsres/assess/evaluation_4000.docx";//模板
            name = "江苏省督导4000评分总表.docx";

            temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
            if (temeplete != null) {
                response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                ServletOutputStream out = response.getOutputStream();
                (new SaveToZipFile(temeplete)).save(out);
                out.flush();
            }
            return null;
        }

        //单个管理表单打印
        if (roleFlag.equals("management") || user.getUserLevelId().equals("management")){
            ResEvaluationScore searchScore = new ResEvaluationScore();
            searchScore.setEvaluationYear(subject.getSubjectYear());
//            searchScore.setOrgFlow(subject.getOrgFlow());
            searchScore.setSpeUserFlow(userFlow);
            searchScore.setSubjectFlow(subject.getSubjectActivitiFlows());
            BigDecimal ownerScoreTotalA = new BigDecimal(0);
            BigDecimal ownerScoreTotalB = new BigDecimal(0);
            BigDecimal ownerScoreTotalAll = new BigDecimal(0);
            BigDecimal localScoreTotalA = new BigDecimal(0);
            BigDecimal localScoreTotalB = new BigDecimal(0);
            BigDecimal localScoreTotalAll = new BigDecimal(0);
            List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
            searchScore.setSpeUserFlow(subject.getOrgFlow());
            List<ResEvaluationScore> localEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);

            if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
                for (ResEvaluationScore score : ownerEvaluationScoreList) {
                    dataMap.put(score.getItemId() + "ownerScore", score.getSpeScore());
                    dataMap.put(score.getItemId() + "speReason", score.getSpeReason());
                    if (StringUtil.isNotBlank(score.getSpeScore())&&score.getItemId().startsWith("2022-6")) {
                        ownerScoreTotalA = ownerScoreTotalA.add(new BigDecimal(score.getSpeScore()));
                    }else if (StringUtil.isNotBlank(score.getSpeScore())){
                        ownerScoreTotalB = ownerScoreTotalB.add(new BigDecimal(score.getSpeScore()));
                    }
                }
            }
            ownerScoreTotalAll=ownerScoreTotalA.add(ownerScoreTotalB);
            dataMap.put("ownerScoreTotalA", ownerScoreTotalA + "");
            dataMap.put("ownerScoreTotalB", ownerScoreTotalB + "");
            dataMap.put("ownerScoreTotalAll", ownerScoreTotalAll + "");

            if (null != localEvaluationScoreList && localEvaluationScoreList.size() > 0) {
                for (ResEvaluationScore score : localEvaluationScoreList) {
                    dataMap.put(score.getItemId() + "ownerScore1", score.getSpeScore());
                    if (StringUtil.isNotBlank(score.getSpeScore())&&score.getItemId().startsWith("2022-6")) {
                        localScoreTotalA = localScoreTotalA.add(new BigDecimal(score.getSpeScore()));
                    }else if (StringUtil.isNotBlank(score.getSpeScore())){
                        localScoreTotalB = localScoreTotalB.add(new BigDecimal(score.getSpeScore()));
                    }
                }
            }
            localScoreTotalAll=localScoreTotalA.add(localScoreTotalB);
            dataMap.put("localScoreTotalA", localScoreTotalA + "");
            dataMap.put("localScoreTotalB", localScoreTotalB + "");
            dataMap.put("localScoreTotalAll", localScoreTotalAll + "");

            ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
            dataMap.put("speContent", subjectUser.getSpeContent());
            dataMap.put("evaluationDate", subjectUser.getEvaluationDate());

            String speSignUrl = InitConfig.getSysCfg("upload_base_url") + "/" + user.getUserSignUrl();
            speSignUrl = "<img  src='" + speSignUrl + "' width='80' height='30'  alt='签名'/>";
            dataMap.put("speSignImg", speSignUrl);//签名

            String path = "/jsp/jsres/assess/evaluation_4000.docx";//模板
            name = "江苏省督导4000评分(" + subjectUser.getUserName() + ").docx";

            temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
            if (temeplete != null) {
                response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
                response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
                ServletOutputStream out = response.getOutputStream();
                (new SaveToZipFile(temeplete)).save(out);
                out.flush();
            }
            return null;
        }

        ResEvaluationScore searchScore = new ResEvaluationScore();
        searchScore.setSubjectFlow(subjectFlow);
        searchScore.setOrgFlow(subject.getOrgFlow());
        searchScore.setSpeId(subject.getSpeId());
        searchScore.setEvaluationYear(subject.getSubjectYear());
        searchScore.setSpeUserFlow(userFlow);
        //专家评分
        List<ResEvaluationScore> speEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
        BigDecimal speScoreTotal = new BigDecimal(0);
        if (null != speEvaluationScoreList && speEvaluationScoreList.size() > 0) {
            for (ResEvaluationScore score : speEvaluationScoreList) {
                dataMap.put(score.getItemId() + "speScore", score.getSpeScore());
                dataMap.put(score.getItemId() + "speReason", score.getSpeReason());
                if (StringUtil.isNotBlank(score.getSpeScore())) {
                    speScoreTotal = speScoreTotal.add(new BigDecimal(score.getSpeScore()));
                }
            }
        }
        dataMap.put("speScoreTotal", speScoreTotal + "");
        //查询自评分
        searchScore.setSpeUserFlow("Owner");
        BigDecimal ownerScoreTotal = new BigDecimal(0);
        List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
        if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
            for (ResEvaluationScore score : ownerEvaluationScoreList) {
                dataMap.put(score.getItemId() + "ownerScore", score.getOwnerScore());
                if (StringUtil.isNotBlank(score.getOwnerScore())) {
                    ownerScoreTotal = ownerScoreTotal.add(new BigDecimal(score.getOwnerScore()));
                }
            }
        }
        dataMap.put("ownerScoreTotal", ownerScoreTotal + "");
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(userFlow, subjectFlow);
        dataMap.put("speContent", subjectUser.getSpeContent());
        dataMap.put("evaluationDate", subjectUser.getEvaluationDate());

        if (!GlobalConstant.USER_LIST_LOCAL.equals(roleFlag) && !roleFlag.equals("baseExpert")) {
            String speSignUrl = InitConfig.getSysCfg("upload_base_url") + "/" + user.getUserSignUrl();
            speSignUrl = "<img  src='" + speSignUrl + "' width='80' height='30'  alt='签名'/>";
            dataMap.put("speSignImg", speSignUrl);//签名
        }
        String path = "/jsp/jsres/assess/evaluation_" + subject.getSpeId() + ".docx";//模板
//        String path = "/jsp/jsres/assess/evaluation_0100.docx";//模板
        name = "江苏省督导" + subject.getSpeId() + "评分(" + subjectUser.getUserName() + ").docx";

        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
        if (temeplete != null) {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
        return null;
    }


    //院级督导  教学查房导出
    @RequestMapping(value = "/printHospitalScore")
    public String printHospitalScore(HttpServletRequest request, HttpServletResponse response, String userFlow,
                                     String subjectFlow,String path) throws Exception {
        //定义数据容器
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //新建word模板
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        //context获取路径
        ServletContext context = request.getServletContext();
        //水印
        String watermark = "";
        //文件名称
        String name = "督导检查表.docx";
        if (StringUtil.isNotBlank(subjectFlow)){
            //获取当前用户
            SysUser user = userBiz.readSysUser(userFlow);   //督导专家信息
            dataMap.put("teacherName",user.getUserName());  //指导医师姓名
            ResHospSupervSubject hospSupervSubject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
            TeachingActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(hospSupervSubject.getActivityFlow());
            if (com.pinde.core.util.StringUtil.isNotBlank(activityInfo.getDeptFlow())){
                SysDept sysDept = deptMapper.selectByPrimaryKey(activityInfo.getDeptFlow());
                if (null !=sysDept){
                    dataMap.put("speSkillName",sysDept.getDeptName());  //专业技术职称
                }
            }
            SysOrg org = orgBiz.readSysOrg(hospSupervSubject.getOrgFlow());
            dataMap.put("deptName", hospSupervSubject.getDeptName());   //所在科室
            dataMap.put("cityName",org.getOrgProvName()+org.getOrgCityName());  //省市
            dataMap.put("orgName", org.getOrgName());   //培训基地（医院）
            dataMap.put("orgCityName", org.getOrgCityName());   //基地所在城市名称
            dataMap.put("speName",hospSupervSubject.getSpeName());  //专业基地
            dataMap.put("speAndDept",hospSupervSubject.getSpeName()+"/"+hospSupervSubject.getDeptName());  //专业基地/科室
            dataMap.put("activityStartTime",hospSupervSubject.getActivityStartTime());  //活动开始时间
            dataMap.put("activityEndTime",hospSupervSubject.getActivityEndTime());  //活动结束时间
            dataMap.put("activityUserName",hospSupervSubject.getTeachName());  //活动主讲人名称
            dataMap.put("activityDept",hospSupervSubject.getDeptName());  //活动科室名称
            SysUser activityUser = userBiz.readSysUser(hospSupervSubject.getTeachFlow());
            dataMap.put("activityUserDept",activityUser.getDeptName());  //活动主讲人专业
            dataMap.put("activityMinute",String.valueOf(DateUtil.signMinutesBetweenTowDate(hospSupervSubject.getActivityStartTime()+":00",hospSupervSubject.getActivityEndTime()+":00")));//教学时长
            int peopleNum = activityBiz.countByActivity(hospSupervSubject.getActivityFlow());
            dataMap.put("peopleNum",String.valueOf(peopleNum));  //参加教学活动的人数
            if (StringUtil.isNotBlank(subjectFlow)){
                dataMap.put("userName",user.getUserName()); //督导专家项目
            }
            String speSignUrl = InitConfig.getSysCfg("upload_base_url") + "/" + user.getUserSignUrl();
            if (StringUtil.isNotBlank(speSignUrl)){
                speSignUrl = "<img  src='" + speSignUrl + "' width='80' height='30'  alt='签名'/>";
                dataMap.put("speSignImg", speSignUrl);//签名
            }
            ResScheduleScore score=new ResScheduleScore();
            score.setSubjectFlow(subjectFlow);
            score.setGrade("hospitalLeader");
            score.setUserFlow(user.getUserFlow());
            List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleList(score);

            if (null!=scoreList && !scoreList.isEmpty()){
                Double allScore=0.0;
                for (ResScheduleScore scheduleScore : scoreList) {
                    String detailed = scheduleScore.getItemDetailed();
                    if (StringUtil.isNotBlank(detailed)){
                        detailed= detailed.replaceAll("<br/>", "\n");
                    }
                    if (StringUtil.isBlank(scheduleScore.getScore())){
                        dataMap.put(scheduleScore.getItemId()+"itemDetailed",detailed);
                    }else {
                        dataMap.put(scheduleScore.getItemId()+"score",scheduleScore.getScore());
                        dataMap.put(scheduleScore.getItemId()+"itemDetailed",detailed);
                        if (StringUtils.isNumeric(scheduleScore.getScore())){   //判断是否是数字
                            allScore=allScore+Double.parseDouble(scheduleScore.getScore());
                        }
                    }
                }
                dataMap.put("allScore",allScore.toString());
            }

            if (StringUtil.isNotBlank(hospSupervSubject.getLeaderOneFlow()) && hospSupervSubject.getLeaderOneFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(hospSupervSubject.getLeaderOneEndTime())){
                    dataMap.put("evaluationDate", hospSupervSubject.getLeaderOneEndTime().substring(0,10));
                }
            }else if (StringUtil.isNotBlank(hospSupervSubject.getLeaderTwoFlow()) && hospSupervSubject.getLeaderTwoFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(hospSupervSubject.getLeaderTwoEndTime())){
                    dataMap.put("evaluationDate", hospSupervSubject.getLeaderTwoEndTime().substring(0,10));
                }
            }
            name = hospSupervSubject.getActivityName()+"督导检查表.docx";
        }

        path = "/jsp/jsres/hospitalAssess/"+path+".docx";//模板
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
        if (temeplete != null) {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
        return null;
    }

    //院级督导  教学查房导出
    @RequestMapping(value = "/printHospitalScoreMany")
    public String printHospitalScoreMany(HttpServletRequest request, HttpServletResponse response, String userFlow,
                                         String subjectFlow,String path) throws Exception {
        //定义数据容器
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //新建word模板
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        //context获取路径
        ServletContext context = request.getServletContext();
        //水印
        String watermark = "";
        //文件名称
        String name = "督导检查表.docx";
        if (StringUtil.isNotBlank(subjectFlow)){
            //获取当前用户
            SysUser user = userBiz.readSysUser(userFlow);
            dataMap.put("teacherName",user.getUserName());
            ResHospSupervSubject hospSupervSubject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
            TeachingActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(hospSupervSubject.getActivityFlow());
            if (com.pinde.core.util.StringUtil.isNotBlank(activityInfo.getDeptFlow())){
                SysDept sysDept = deptMapper.selectByPrimaryKey(activityInfo.getDeptFlow());
                if (null !=sysDept){
                    dataMap.put("speSkillName",sysDept.getDeptName());
                }
            }
            SysOrg org = orgBiz.readSysOrg(hospSupervSubject.getOrgFlow());
            dataMap.put("deptName", hospSupervSubject.getDeptName());
            dataMap.put("orgName", org.getOrgName());
            dataMap.put("orgCityName", org.getOrgCityName());
            dataMap.put("speName",hospSupervSubject.getSpeName());
            if (StringUtil.isNotBlank(subjectFlow)){
                dataMap.put("userName",user.getUserName());
            }


            String speSignUrl = InitConfig.getSysCfg("upload_base_url") + "/" + user.getUserSignUrl();
            if (StringUtil.isNotBlank(speSignUrl)){
                speSignUrl = "<img  src='" + speSignUrl + "' width='80' height='30'  alt='签名'/>";
                dataMap.put("speSignImg", speSignUrl);//签名
            }

            ResScheduleScore score=new ResScheduleScore();
            score.setSubjectFlow(subjectFlow);
            score.setGrade("hospitalLeader");
            score.setUserFlow(user.getUserFlow());
            List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleList(score);

            if (null!=scoreList && !scoreList.isEmpty()){
                Double allScore=0.0;
                Double allScorek=0.0;
                for (ResScheduleScore scheduleScore : scoreList) {
                    if (StringUtil.isBlank(scheduleScore.getScore())){
                        dataMap.put(scheduleScore.getItemId()+"itemDetailed",scheduleScore.getItemDetailed());
                    }else {
                        dataMap.put(scheduleScore.getItemId()+"score",scheduleScore.getScore());
                        dataMap.put(scheduleScore.getItemId()+"itemDetailed",scheduleScore.getItemDetailed());
                        int count = 0;
                        for (int i = 0; i < scheduleScore.getItemId().length(); i++) {
                            if (scheduleScore.getItemId().charAt(i) == '.') {       //一个点的是得分，多个点是前面的评分（单项分）
                                count++;
                            }
                        }
                        if (count==1){
                            allScore=allScore+Double.parseDouble(scheduleScore.getScore());
                        }else {
                            allScorek=allScorek+Double.parseDouble(scheduleScore.getScore());
                        }
                    }
                }
                dataMap.put("allScore",allScore.toString());
                dataMap.put("allScorek",allScorek.toString());
            }
            if (StringUtil.isNotBlank(hospSupervSubject.getLeaderOneFlow()) && hospSupervSubject.getLeaderOneFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(hospSupervSubject.getLeaderOneEndTime())){
                    dataMap.put("evaluationDate", hospSupervSubject.getLeaderOneEndTime().substring(0,10));
                }
            }else if (StringUtil.isNotBlank(hospSupervSubject.getLeaderTwoFlow()) && hospSupervSubject.getLeaderTwoFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(hospSupervSubject.getLeaderTwoEndTime())){
                    dataMap.put("evaluationDate", hospSupervSubject.getLeaderTwoEndTime().substring(0,10));
                }
            }
            name = hospSupervSubject.getActivityName()+"督导检查表.docx";
        }

        path = "/jsp/jsres/hospitalAssess/"+path+".docx";//模板
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
        if (temeplete != null) {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
        return null;
    }


    //院级督导  教学查房评分表导出（得分扣分   与其他的教学查房表不一致）
    @RequestMapping(value = "/printHospitalScoreDK")
    public String printHospitalScoreDK(HttpServletRequest request, HttpServletResponse response, String userFlow,
                                       String subjectFlow,String path) throws Exception {
        //定义数据容器
        Map<String, Object> dataMap = new HashMap<String, Object>();
        //新建word模板
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        //context获取路径
        ServletContext context = request.getServletContext();
        //水印
        String watermark = "";
        //文件名称
        String name = "督导检查表.docx";

        if (StringUtil.isNotBlank(subjectFlow)){
            //获取当前用户
            SysUser user = userBiz.readSysUser(userFlow);
            dataMap.put("teacherName",user.getUserName());
            ResHospSupervSubject hospSupervSubject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
            dataMap.put("deptName", hospSupervSubject.getDeptName());
            TeachingActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(hospSupervSubject.getActivityFlow());
            if (com.pinde.core.util.StringUtil.isNotBlank(activityInfo.getDeptFlow())){
                SysDept sysDept = deptMapper.selectByPrimaryKey(activityInfo.getDeptFlow());
                if (null !=sysDept){
                    dataMap.put("speSkillName",sysDept.getDeptName());
                }
            }
            SysOrg org = orgBiz.readSysOrg(hospSupervSubject.getOrgFlow());
            dataMap.put("orgName", org.getOrgName());
            dataMap.put("orgCityName", org.getOrgCityName());
            dataMap.put("speName",hospSupervSubject.getSpeName());
            dataMap.put("userName",user.getUserName());

            String speSignUrl = InitConfig.getSysCfg("upload_base_url") + "/" + user.getUserSignUrl();
            if (StringUtil.isNotBlank(speSignUrl)){
                speSignUrl = "<img  src='" + speSignUrl + "' width='80' height='30'  alt='签名'/>";
                dataMap.put("speSignImg", speSignUrl);//签名
            }

            ResScheduleScore score=new ResScheduleScore();
            score.setSubjectFlow(subjectFlow);
            score.setGrade("hospitalLeader");
            score.setUserFlow(user.getUserFlow());
            List<ResScheduleScore> scoreListAll = resScheduleScoreBiz.queryScheduleList(score);
            score.setScoreType("K");
            List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleList(score);
            score.setScoreType("d");
            List<ResScheduleScore> scoreList1 = resScheduleScoreBiz.queryScheduleList(score);
            if (null!=scoreListAll && !scoreListAll.isEmpty()){
                for (ResScheduleScore scheduleScore : scoreListAll) {
                    if (StringUtil.isNotBlank(scheduleScore.getItemDetailed())){
                        dataMap.put(scheduleScore.getItemId()+"itemDetailed",scheduleScore.getItemDetailed());
                    }
                }
            }

            if (null!=scoreList && !scoreList.isEmpty()){
                Double allScoreK=0.0;
                for (ResScheduleScore scheduleScore : scoreList) {
                    dataMap.put(scheduleScore.getItemId()+"score",scheduleScore.getScore());
                    allScoreK=allScoreK+Double.parseDouble(scheduleScore.getScore());
                }
                dataMap.put("allScoreK",allScoreK.toString());
            }
            if (null!=scoreList1 && !scoreList1.isEmpty()){
                Double allScore=0.0;
                for (ResScheduleScore scheduleScore : scoreList1) {
                    dataMap.put(scheduleScore.getItemId()+"scored",scheduleScore.getScore());
                    allScore=allScore+Double.parseDouble(scheduleScore.getScore());
                }
                dataMap.put("allScore",allScore.toString());
            }

            if (StringUtil.isNotBlank(hospSupervSubject.getLeaderOneFlow()) && hospSupervSubject.getLeaderOneFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(hospSupervSubject.getLeaderOneEndTime())){
                    dataMap.put("evaluationDate", hospSupervSubject.getLeaderOneEndTime().substring(0,10));
                }
            }else if (StringUtil.isNotBlank(hospSupervSubject.getLeaderTwoFlow()) && hospSupervSubject.getLeaderTwoFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(hospSupervSubject.getLeaderTwoEndTime())){
                    dataMap.put("evaluationDate", hospSupervSubject.getLeaderTwoEndTime().substring(0,10));
                }
            }
            name = hospSupervSubject.getActivityName()+"督导检查表.docx";
        }


        path = "/jsp/jsres/hospitalAssess/"+path+".docx";//模板
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
        if (temeplete != null) {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
        return null;
    }


    /**
     * 跳转至上传扫描件
     *
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(Model model, String itemId, String itemName, String subjectYear,String subjectFlow, String speId) throws UnsupportedEncodingException {
        String tempStr ="";
        if (StringUtil.isNotBlank(itemName)){
            tempStr =java.net.URLDecoder.decode(itemName, "UTF-8");
        }
        model.addAttribute("itemId", itemId);
        model.addAttribute("itemName", tempStr);
        model.addAttribute("subjectYear", subjectYear);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("speId", speId);
        return "jsres/hospital/supervisio/uploadFile";
    }

    @RequestMapping(value = "/checkUploadFile", method = {RequestMethod.POST})
    public String checkUploadFile(String itemId, String itemName, String subjectYear,String subjectFlow, MultipartFile uploadFile, Model model, String speId) throws UnsupportedEncodingException {
        SysUser user = GlobalContext.getCurrentUser();
        String resultPath = "";
        if (uploadFile != null && !uploadFile.isEmpty()) {
            String fileResult = supervisioBiz.checkImg(uploadFile);
            if (!GlobalConstant.FLAG_Y.equals(fileResult)) {
                model.addAttribute("fileErrorMsg", fileResult);
            } else {
                resultPath = supervisioBiz.saveFileToDirs("", uploadFile, "jsresSupervisioFile", user.getOrgFlow(), "2022", itemId);
            }
            model.addAttribute("result", fileResult);
        }
        JsresSupervisioFile jsresSupervisioFile = new JsresSupervisioFile();
        jsresSupervisioFile.setItemId(itemId);
        String tempStr = java.net.URLDecoder.decode(itemName, "UTF-8");
        jsresSupervisioFile.setItemName(tempStr);
        jsresSupervisioFile.setPlanYear(DateUtil.getYear());
        jsresSupervisioFile.setCreateTime(DateUtil.getCurrentTime());
        jsresSupervisioFile.setFileUrl(resultPath);
        jsresSupervisioFile.setCreateUserFlow(user.getUserFlow());
        jsresSupervisioFile.setFileName(uploadFile.getOriginalFilename());
        jsresSupervisioFile.setSpeId(speId);
        jsresSupervisioFile.setOrgFlow(user.getOrgFlow());
        jsresSupervisioFile.setOrgName(user.getOrgName());
        jsresSupervisioFile.setPlanYear(subjectYear);
        jsresSupervisioFile.setSubjectFlow(subjectFlow);
        supervisioBiz.saveJsresSupervisioFile(jsresSupervisioFile);
        model.addAttribute("file", jsresSupervisioFile);
        return "jsres/hospital/supervisio/uploadFile";
    }

    /**
     * 删除文件
     *
     * @param recordFlow
     * @return
     */
    @RequestMapping("/removeFile")
    public @ResponseBody
    String removeFile(String recordFlow) throws DocumentException {
        supervisioBiz.deleteFileByPrimaryKey(recordFlow);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 下载评分附件
     *
     * @param response
     * @param recordFlow
     * @throws Exception
     */
    @RequestMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response, String recordFlow) throws Exception {
        JsresSupervisioFile jsresSupervisioFile = supervisioBiz.searchJsresSupervisioFileByRecordFlow(recordFlow);
        String fileName = jsresSupervisioFile.getFileName();
        String fileUrl = jsresSupervisioFile.getFileUrl();
        String fileDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + fileUrl;
        File file = new File(fileDir);
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        FileInputStream is = new FileInputStream(file);
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        byte[] b = new byte[(int) file.length()];
        int n;
        while ((n = is.read(b)) != -1) {
            os.write(b, 0, n);
        }
        os.flush();
        is.close();
        os.close();
    }

    /**
     * 单个附件跳转
     */
    @RequestMapping(value = "/Schedule")
    public String Schedule(String fileRoute, String orgName, String orgFlow, String subjectFlow, String isRead,
                           String userFlow, String speId, String roleFlag, Model model,String editFlag) {
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag) || roleFlag.equals("baseExpert")) {
            resScheduleScore.setGrade(GlobalConstant.USER_LIST_LOCAL);
        } else if (roleFlag.equals("expertLeader")
                || roleFlag.equals("management")
                || roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL)) {
            resScheduleScore.setGrade("expert");
        }
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setSubjectFlow(subjectFlow);
        //根据专家自己的userFlow查询附表评分详情
        if (StringUtil.isNotBlank(userFlow)) {
            resScheduleScore.setUserFlow(userFlow);
        } else {
            SysUser sysUser = GlobalContext.getCurrentUser();
            resScheduleScore.setUserFlow(sysUser.getUserFlow());
        }
        resScheduleScore.setSpeId(speId);
        List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleList(resScheduleScore);
        if (!GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            //查询专家签名
            SysUser user = null;
            if (StringUtil.isNotBlank(userFlow)) {
                user = userBiz.readSysUser(userFlow);
            } else {
                user = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
            }
            if (user != null) {
                model.addAttribute("speSignUrl", user.getUserSignUrl());
                model.addAttribute("user", user);
            }
        }
        //为了查看省市
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        if (sysOrg != null && (sysOrg.getOrgCityName() != null || sysOrg.getOrgProvName() != null))
            model.addAttribute("cityName", sysOrg.getOrgProvName() + sysOrg.getOrgCityName());
        model.addAttribute("scoreList", scoreList);
        model.addAttribute("orgName", orgName);
        model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("isRead", isRead);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("speId", speId);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("fileRoute", fileRoute);
        model.addAttribute("editFlag", editFlag);
        model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(com.pinde.core.util.DateUtil.getCurrDate(), "yyyy-MM-dd"));
        return "jsres/assess/" + fileRoute;
    }

    /**
     * 附件 保存得分
     * 适用于只有得分  扣分情况（两个分值相关）
     */
    @RequestMapping(value = "/savScheduleScore")
    @ResponseBody
    public String savScheduleScore(String itemId, String score, String itemName, String orgName, String orgFlow,
                                   String speId, String roleFlag, String subjectFlow, String num, String fileRoute) {
        ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        if (roleFlag.equals("baseExpert")) {
            resScheduleScore.setGrade(GlobalConstant.USER_LIST_LOCAL);
        } else if (roleFlag.equals("expertLeader")) {
            resScheduleScore.setGrade("expert");
        }
        SysUser sysUser = GlobalContext.getCurrentUser();
        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        resScheduleScore.setEvaluationYear(subject.getSubjectYear());
        String itemName2 = "";
        if (itemName.startsWith("d")) {
            itemName2 = "k" + itemName.substring(1);
        } else {
            itemName2 = "d" + itemName.substring(1);
        }
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore) <= 0) {
            return GlobalConstant.OPERATE_FAIL;
        }
        resScheduleScore.setItemName(itemName2);
        resScheduleScore.setScore(String.valueOf(new BigDecimal(num).subtract(new BigDecimal(score))));
        resScheduleScoreBiz.saveSchedule(resScheduleScore);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 附表 数据为有、无的方法
     */
    @RequestMapping(value = "/savScheduleHaveAndNo")
    @ResponseBody
    public String savScheduleHaveAndNo(String itemId, String score, String itemName, String orgName, String orgFlow,
                                       String speId, String roleFlag, String subjectFlow, String fileRoute) {
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        if (roleFlag.equals("baseExpert")) {
            resScheduleScore.setGrade(GlobalConstant.USER_LIST_LOCAL);
        } else if (roleFlag.equals("expertLeader")) {
            resScheduleScore.setGrade("expert");
        }
        //添加select的数据
        SysUser sysUser = GlobalContext.getCurrentUser();
        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore) > 0) {
            //添加该select后面的input的数据，以itemName区分
            resScheduleScore.setItemName("d" + itemName.substring(1));
            if (resScheduleScore.getScore().equals("有")) {
                resScheduleScore.setScore("");
            } else {
                resScheduleScore.setScore("√");
            }
            if (resScheduleScoreBiz.saveSchedule(resScheduleScore) > 0) {
                return GlobalConstant.OPERATE_SUCCESSED;
            }
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 附件-保存 实得分（一个分值）
     */
    @RequestMapping(value = "/saveScheduleMK")
    @ResponseBody
    public String saveScheduleMK(String itemId, String score, String orgName, String roleFlag,
                                 String orgFlow, String speId, String subjectFlow, String fileRoute ) {
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setOrgFlow(orgFlow);
        SysUser sysUser = GlobalContext.getCurrentUser();
        if (roleFlag.equals("baseExpert")) {    //省级督导  专业基地专家
            resScheduleScore.setGrade(GlobalConstant.USER_LIST_LOCAL);
        } else if (roleFlag.equals("expertLeader")) {   //专业专家
            resScheduleScore.setGrade("expert");
        }else if (roleFlag.equals("hospitalLeader")){   //院级督导
            resScheduleScore.setGrade("hospitalLeader");
            resScheduleScore.setOrgFlow(sysUser.getOrgFlow());
            ResHospSupervSubject subject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
            //评审开始时间
            if (StringUtil.isBlank(subject.getStartTime())){
                subject.setStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
            }
            //记录评分开始时间
            if (subject.getLeaderOneFlow().equals(sysUser.getUserFlow())){
                if (StringUtil.isBlank(subject.getLeaderOneStartTime())){
                    subject.setLeaderOneStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                    if (supervisioUserBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                        return GlobalConstant.OPERATE_FAIL;
                    }
                }
            }else {
                if (StringUtil.isBlank(subject.getLeaderTwoStartTime())){
                    subject.setLeaderTwoStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                    if (supervisioUserBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                        return GlobalConstant.OPERATE_FAIL;
                    }
                }
            }
        }

        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        resScheduleScore.setRecordStatus("Y");
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore) <= 0) {
            return GlobalConstant.OPERATE_FAIL;
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 保存 多个分值总和为一个
     * jsp中，子分值和总分值的itemid一样，但是itemName不一样，name也不一致
     * 子分值的js方法中需要上传总分的itemName和分数
     *
     * @param itemId
     * @param score
     * @param itemName    子分值的itemName
     * @param roleFlag
     * @param orgName
     * @param orgFlow
     * @param speId
     * @param itemMain    总分值的itemName
     * @param scoreAll    总分值的分值
     * @return
     */
    @RequestMapping(value = "/saveScheduleManyToAll")
    @ResponseBody
    public String saveScheduleManyToAll(String itemId, String score, String itemName, String roleFlag, String orgName,
                                        String orgFlow, String speId, String subjectFlow, String itemMain, String scoreAll, String fileRoute) {
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        SysUser sysUser = GlobalContext.getCurrentUser();
        if (roleFlag.equals("baseExpert")) {
            resScheduleScore.setGrade(GlobalConstant.USER_LIST_LOCAL);
        } else if (roleFlag.equals("expertLeader")) {
            resScheduleScore.setGrade("expert");
        }else if (roleFlag.equals("hospitalLeader")){   //院级督导
            resScheduleScore.setGrade("hospitalLeader");
            resScheduleScore.setOrgFlow(sysUser.getOrgFlow());
            ResHospSupervSubject subject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
            //评审开始时间
            if (StringUtil.isBlank(subject.getStartTime())){
                subject.setStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
            }
            //记录评分开始时间
            if (subject.getLeaderOneFlow().equals(sysUser.getUserFlow())){
                if (StringUtil.isBlank(subject.getLeaderOneStartTime())){
                    subject.setLeaderOneStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                    if (supervisioUserBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                        return GlobalConstant.OPERATE_FAIL;
                    }
                }
            }else {
                if (StringUtil.isBlank(subject.getLeaderTwoStartTime())){
                    subject.setLeaderTwoStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                    if (supervisioUserBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                        return GlobalConstant.OPERATE_FAIL;
                    }
                }
            }
        }

        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        //添加多的一方数据
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore) > 0) {
            //查询多的一方数据
            resScheduleScore.setItemName(itemMain);
            resScheduleScore.setScheduleFlow(null);
            int indexOf = resScheduleScore.getItemId().lastIndexOf(".");
            resScheduleScore.setItemId(resScheduleScore.getItemId().substring(0,indexOf));
            List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleListNotItemName(resScheduleScore);
            //累计分数
            BigDecimal itemScoreAll=new BigDecimal(0);
            BigDecimal decimal = new BigDecimal(scoreAll);
            if (scoreList.size() > 0) {
                for (ResScheduleScore scheduleScore : scoreList) {
                    BigDecimal scoreInfo=new BigDecimal(scheduleScore.getScore());
                    itemScoreAll = itemScoreAll.add(scoreInfo);
                }
                itemScoreAll=decimal.subtract(itemScoreAll);
            } else {
                BigDecimal scoreInfo=new BigDecimal(scoreAll);
                BigDecimal scoreAll2=new BigDecimal(scoreAll);
                itemScoreAll=scoreAll2.subtract(scoreInfo);
            }
            if (itemScoreAll.compareTo(BigDecimal.ZERO)==-1){
                itemScoreAll.setScale(0);
            }
            //添加少的一方数据
            resScheduleScore.setScore(String.valueOf(itemScoreAll));

            if (resScheduleScoreBiz.saveSchedule(resScheduleScore) > 0) {
                return GlobalConstant.OPERATE_SUCCESSED;
            }
        }
        return GlobalConstant.OPERATE_FAIL;
    }


    /* *//**
     * 保存文本信息，如扣分原因
     *
     * @param itemId
     * @param itemDetailed
     * @param roleFlag
     * @param orgName
     * @param orgFlow
     * @param speId
     * @return
     */
    @RequestMapping(value = "/saveScheduleDetailed")
    @ResponseBody
    public String saveScheduleDetailed(String itemId, String itemDetailed, String roleFlag, String orgName,
                                       String orgFlow, String speId, String subjectFlow, String fileRoute) throws Exception {
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        SysUser sysUser = GlobalContext.getCurrentUser();
        if (roleFlag.equals("baseExpert")) {
            resScheduleScore.setGrade(GlobalConstant.USER_LIST_LOCAL);
        } else if (roleFlag.equals("expertLeader")) {
            resScheduleScore.setGrade("expert");
        }else if (roleFlag.equals("hospitalLeader")){   //院级督导
            resScheduleScore.setGrade("hospitalLeader");
            ResHospSupervSubject subject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
            //评审项目评审开始时间
            if (StringUtil.isBlank(subject.getStartTime())){
                subject.setStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
            }
            //记录评分开始时间
            if (subject.getLeaderOneFlow().equals(sysUser.getUserFlow())){
                if (StringUtil.isBlank(subject.getLeaderOneStartTime())){
                    subject.setLeaderOneStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                    if (supervisioUserBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                        return GlobalConstant.OPERATE_FAIL;
                    }
                }
            }else {
                if (StringUtil.isBlank(subject.getLeaderTwoStartTime())){
                    subject.setLeaderTwoStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                    if (supervisioUserBiz.updateHospSupervisioBySubjectFlow(subject)!=1){
                        return GlobalConstant.OPERATE_FAIL;
                    }
                }
            }
        }


        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setFileRoute(fileRoute);
        if (StringUtil.isNotBlank(roleFlag) && roleFlag.equals("hospitalLeader")){
            resScheduleScore.setOrgFlow(sysUser.getOrgFlow());
        }else {
            resScheduleScore.setOrgFlow(orgFlow);
        }
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        itemDetailed = java.net.URLDecoder.decode(itemDetailed, "UTF-8");
        resScheduleScore.setItemDetailed(itemDetailed);

        if (resScheduleScoreBiz.saveScheduleDetailed(resScheduleScore) <= 0) {
            return GlobalConstant.OPERATE_FAIL;
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 多个附件统一展示
     *
     * @param orgName
     * @param orgFlow
     * @param roleFlag
     * @param speId
     * @param fileRoute
     * @param model
     * @return
     */
    @RequestMapping(value = "/scheduleMany")
    public String scheduleMany(String orgName, String orgFlow, String subjectFlow, String roleFlag, String speId,String userFlow,
                               String isRead, String fileRoute, Model model) {
        model.addAttribute("orgName", orgName);
        model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("isRead", isRead);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("speId", speId);
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(com.pinde.core.util.DateUtil.getCurrDate(), "yyyy-MM-dd"));
        return "jsres/assess/" + fileRoute;
    }

    /**
     * 附件  多个表单保存总分  以itemId区分
     *
     * @param itemIdOne    第一个的itemId
     * @param itemIdTwo    第二个的itemId
     * @param selfOneScore 第一个的分数
     * @param selfTwoScore 第二个的分数
     * @param itemName
     * @param orgName
     * @param roleFlag
     * @param orgFlow
     * @param speId
     * @return
     */
    @RequestMapping(value = "/saveManyScheduleTotalled")
    @ResponseBody
    public String saveManyScheduleTotalled(String itemIdOne, String itemIdTwo, String selfOneScore, String selfTwoScore,
                                           String itemName, String orgName, String roleFlag, String orgFlow, String speId,
                                           String subjectFlow, String fileRoute) {
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        if (roleFlag.equals("baseExpert")) {
            resScheduleScore.setGrade(GlobalConstant.USER_LIST_LOCAL);
        } else if (roleFlag.equals("expertLeader")) {
            resScheduleScore.setGrade("expert");
        }
        SysUser sysUser = GlobalContext.getCurrentUser();
        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemIdOne);
        resScheduleScore.setScore(selfOneScore);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        resScheduleScore.setScoreType("Totalled");
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore) > 0) {
            if (StringUtil.isNotBlank(selfTwoScore) && StringUtil.isNotBlank(itemIdTwo)) {
                resScheduleScore.setScore(selfTwoScore);
                resScheduleScore.setItemId(itemIdTwo);
                if (resScheduleScoreBiz.saveSchedule(resScheduleScore) > 0) {
                    return GlobalConstant.OPERATE_SUCCESSED;
                }
            } else {
                return GlobalConstant.OPERATE_SUCCESSED;
            }

        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 保存附表的总分
     */
    @RequestMapping(value = "/saveScheduleTotalled")
    @ResponseBody
    public String saveScheduleTotalled(String itemId, String score, String itemName, String orgName, String roleFlag,
                                       String orgFlow, String speId, String subjectFlow, String fileRoute) {
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        if (roleFlag.equals("baseExpert")) {
            resScheduleScore.setGrade(GlobalConstant.USER_LIST_LOCAL);
        } else if (roleFlag.equals("expertLeader")) {
            resScheduleScore.setGrade("expert");
        }
        SysUser sysUser = GlobalContext.getCurrentUser();
        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        resScheduleScore.setScoreType("Totalled");
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore) <= 0) {
            return GlobalConstant.OPERATE_FAIL;
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 专业专家专业评分查询
     */
    @RequestMapping(value = "/planScoreMain")
    public String planScoreMain(Model model, String roleFlag ) {
        model.addAttribute("roleFlag", roleFlag);
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId("320000");
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
            sysorg.setOrgFlow(orgFlow);
            model.addAttribute("orgFlow", orgFlow);
        }

        List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("currentTime", com.pinde.core.util.DateUtil.getYear());
        return "jsres/hospital/supervisio/planScoreMain";
    }


    @RequestMapping(value = "/hospitalPlanScoreMain")
    public String hospitalPlanScoreMain(Model model) {

        SysOrg sysorg = new SysOrg();
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        sysorg.setOrgFlow(orgFlow);
        model.addAttribute("orgFlow", orgFlow);
        List<SysDept> deptList = supervisioBiz.selectDeptByOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("deptList",deptList);
        model.addAttribute("currentTime", com.pinde.core.util.DateUtil.getYear());
        return "jsres/hospital/supervisio/hospitalPlanScoreMain";
    }

    /**
     * 专业专家专专业评分-专业列表
     */
    @RequestMapping(value = "/planScoreList")
    public String planScoreList(Model model, String roleFlag, Integer currentPage, HttpServletRequest request,String baseCode,String supervisionResults,
                                String subjectName, String orgFlow, String speId, String subjectYear) {
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("dateNow", com.pinde.core.util.DateUtil.formatDate(new Date(), com.pinde.core.util.DateUtil.defDtPtn02));
        //基地自己的专业审查
        Map<String, Object> param = new HashMap<>();
        param.put("subjectYear", subjectYear);
        param.put("speId", speId);
        param.put("supervisionResults",supervisionResults);
        List<ResSupervisioSubject> list=null;
        //查询条件
        param.put("subjectName", subjectName);
        param.put("orgFlow", orgFlow);
        param.put("baseCode", baseCode);
        if (StringUtil.isNotBlank(roleFlag) && !GlobalConstant.USER_LIST_LOCAL.equals(roleFlag) && !roleFlag.equals("baseExpert")) {
            String userFlow = GlobalContext.getCurrentUser().getUserFlow();
            param.put("userFlow", userFlow);
            model.addAttribute("userFlow", userFlow);
        }
        SysOrg org = new SysOrg();
        org.setOrgProvId("320000");
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        param.put("org", org);
        PageHelper.startPage(currentPage, getPageSize(request));
        if (roleFlag.equals(GlobalConstant.USER_LIST_LOCAL)){
            list= supervisioUserBiz.selectLocalSubjectListByParam(param);
        }else if("baseExpert".equals(roleFlag)){
            param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
            //专业基地专家只能看见自己专业的项目
            param.put("speId",GlobalContext.getCurrentUser().getResTrainingSpeId());
            list = supervisioUserBiz.selectLocalSubjectListByParam(param);
            String userFlow = GlobalContext.getCurrentUser().getUserFlow();
            model.addAttribute("userFlow", userFlow);
        } else {
            list = supervisioUserBiz.selectSubjectListByParam(param);
        }
        model.addAttribute("list", list);
        if (roleFlag.equals("management")){
            return "jsres/hospital/supervisio/managePlanScoreList";
        }
        return "jsres/hospital/supervisio/planScoreList";
    }


    @RequestMapping(value = "/hospitalPlanScoreList")
    public String hospitalPlanScoreList(Model model, Integer currentPage, HttpServletRequest request,
                                        String activityName, String deptFlow,String inspectionType) {
        Map<String, Object> param = new HashMap<>();
        param.put("activityName",activityName);
        param.put("deptFlow",deptFlow);
        param.put("inspectionType",inspectionType);
        param.put("userFlow",GlobalContext.getCurrentUser().getUserFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResHospSupervSubject> list = supervisioUserBiz.hospitalLeaderScoreList(param);
        model.addAttribute("list",list);
        model.addAttribute("nowTime",DateUtil.getCurrDateTime2());
        model.addAttribute("userName",GlobalContext.getCurrentUser().getUserName());
        return "jsres/hospital/supervisio/hospitalPlanScoreList";
    }

    /**
     * 专业基地专家自评分数
     */
    @RequestMapping(value = "/baseScore")
    public String baseScore(Model model, String speId, String orgFlow, String userFlow, String subjectFlow, String roleFlag,
                            String subjectYear,String subjectActivitiFlows) {
        model.addAttribute("speId", speId);
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("subjectFlow", subjectFlow);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("subjectActivitiFlows", subjectActivitiFlows);
        model.addAttribute("subjectYear", subjectYear);
        model.addAttribute("orgFlow", orgFlow);
        return "jsres/hospital/supervisio/baseScore";
    }


    /**
     * 专业专家签名图片
     */
    @RequestMapping(value = "/signMain")
    public String signMain(String userFlow, Model model,String roleFlag) {
        model.addAttribute("userFlow", userFlow);
        model.addAttribute("roleFlag", roleFlag);
        return "jsres/hospital/supervisio/signMain";
    }


    @RequestMapping(value = "/signHospitalLeaderMain")
    public String signHospitalLeaderMain(String userFlow, Model model) {
        model.addAttribute("userFlow", userFlow);
        return "jsres/hospital/supervisio/signHospitalLeaderMain";
    }


    /**
     * 专业专家签名图片查询
     */
    @RequestMapping(value = "/userSign")
    public String userSign(Model model, String userFlow) {
        SysUser user = userBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        return "jsres/hospital/supervisio/userSign";
    }

    @RequestMapping(value = "/hospitalLeaderSign")
    public String hospitalLeaderSign(Model model, String userFlow) {
        SysUser user = userBiz.readSysUser(userFlow);
        model.addAttribute("user", user);
        return "jsres/hospital/supervisio/hospitalLeaderSign";
    }

    /**
     * 专业专家上传签名图片
     */
    @RequestMapping(value = "/addSign")
    public String addSign(String userFlow, Model model) {
        model.addAttribute("userFlow", userFlow);
        return "jsres/hospital/supervisio/addSign";
    }

    /**
     * 保存专业专家签名图片
     */
    @RequestMapping(value = "/saveUploadFile", method = {RequestMethod.POST})
    public String saveUploadFile(String userFlow, MultipartFile uploadFile, Model model) {
        Map<String, MultipartFile> fileMap = new LinkedHashMap<String, MultipartFile>();
        fileMap.put("operType", uploadFile);
        if (uploadFile != null) {
            String resultPath = supervisioUserBiz.saveFileToDirs("", uploadFile, "supersivioSign");
            model.addAttribute("result", GlobalConstant.FLAG_Y);
            model.addAttribute("filePath", resultPath);
            SysUser user = userBiz.readSysUser(userFlow);
            user.setUserSignUrl(resultPath);
            supervisioUserBiz.editSupervisioUser(user);
        } else {
            model.addAttribute("result", GlobalConstant.FLAG_N);
        }
        return "jsres/hospital/supervisio/addSign";
    }

    /**
     * 上传督导反馈、专业反馈
     */
    @RequestMapping(value = "/addFeedback")
    public String addFeedback(String subjectActivitiFlows,String type,String subjectFlow, Model model) {
        model.addAttribute("subjectActivitiFlows", subjectActivitiFlows);
        model.addAttribute("type", type);
        model.addAttribute("subjectFlow", subjectFlow);
        return "jsres/hospital/supervisio/addFeedback";
    }

    /**
     * 保存督导反馈、专业反馈
     */
    @RequestMapping(value = "/saveFeedback", method = {RequestMethod.POST})
    public String saveFeedback(String subjectActivitiFlows,String subjectFlow, String type, MultipartFile file, Model model) {
        Map<String, MultipartFile> fileMap = new LinkedHashMap<String, MultipartFile>();
        fileMap.put("operType", file);
        if (file != null) {
            String resultPath = supervisioUserBiz.saveFileToDirs("", file, "subjectFeedback");
            model.addAttribute("result", GlobalConstant.FLAG_Y);
            model.addAttribute("filePath", resultPath);
            Map<String,Object> param = new HashMap<>();
            param.put("subjectActivitiFlows",subjectActivitiFlows);
            param.put("type",type);
            param.put("path",resultPath);
            param.put("subjectFlow",subjectFlow);
            supervisioUserBiz.updateSubjectFeedback(param);
        } else {
            model.addAttribute("subjectActivitiFlows",subjectActivitiFlows);
            model.addAttribute("subjectFlow",subjectFlow);
            model.addAttribute("type",type);
            model.addAttribute("result", GlobalConstant.FLAG_N);
            model.addAttribute("fileErrorMsg", GlobalConstant.SAVE_FAIL);
        }
        return "jsres/hospital/supervisio/addFeedback";
    }

    /**
     * 下载督导反馈、专业反馈附件
     */
    @RequestMapping("/downFeedbackFile")
    public void downFeedbackFile(HttpServletResponse response, String fileUrl,String type) throws Exception {
        String fileName = "";
        if(StringUtil.isNotBlank(type) && "sup".equals(type)){
            fileName = "督导反馈.docx";
        }else{
            fileName = "专业反馈.docx";
        }
        String fileDir = com.pinde.core.util.StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + fileUrl;
        File file = new File(fileDir);
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        FileInputStream is = new FileInputStream(file);
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        byte[] b = new byte[(int) file.length()];
        int n;
        while ((n = is.read(b)) != -1) {
            os.write(b, 0, n);
        }
        os.flush();
        is.close();
        os.close();
    }

    @RequestMapping(value = "/subjectList")
    public String subjectList(Model model, String roleFlag, Integer currentPage, HttpServletRequest request,String baseCode,String supervisionResults,String suAoth,
                              String subjectName, String orgFlow, String speId, String cityId, String subjectYear, String orderAvgScore,String localSubject) {
        model.addAttribute("suAoth",suAoth);
        //查询条件
        Map<String, Object> param = new HashMap<>();
        param.put("subjectName", subjectName);
        param.put("cityId", cityId);
        param.put("orgFlow", orgFlow);
        param.put("speId", speId);
        param.put("baseCode",baseCode);
        param.put("subjectName",subjectName);
        param.put("supervisionResults",supervisionResults);
        if (StringUtil.isNotBlank(subjectYear)) {
            param.put("subjectYear", subjectYear);
        }
        param.put("roleFlag", roleFlag);
        param.put("orderAvgScore", orderAvgScore);
        String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        if (StringUtil.isBlank(localSubject)){
            if ("expertLeader".equals(roleFlag)) {
                param.put("expertUserFlow", userFlow);
            }else if ("management".equals(roleFlag)){
                param.put("manageUserFlow", userFlow);
            }
        }
        SysOrg org = new SysOrg();
        org.setOrgProvId("320000");
        org.setOrgCityId(cityId);
        org.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        param.put("org", org);
        List<ResSupervisioSubject> list=new ArrayList<>();
        model.addAttribute("roleFlag", roleFlag);
        if ((StringUtil.isNotBlank(suAoth) && suAoth.equals("Y") )||(StringUtil.isNotBlank(localSubject)&& localSubject.equals("Y"))){
            if (StringUtil.isBlank(localSubject)){
                param.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
            }
            param.put("supervisioAuthority", GlobalConstant.FLAG_Y);
            PageHelper.startPage(currentPage, getPageSize(request));
            list = supervisioUserBiz.selectBaseSubjectList(param);
            model.addAttribute("list", list);
            return "hbres/supervisio/localBaseSubjectList";
        }else {
            param.put("supervisioAuthority", GlobalConstant.FLAG_N);
            if (currentPage == null) {
                currentPage = 1;
            }
            PageHelper.startPage(currentPage, getPageSize(request));
            if ("expertLeader".equals(roleFlag)) {
                param.put("euserFlow", userFlow);
            }else if ("management".equals(roleFlag)){
                param.put("muserFlow", userFlow);
            }
            if (roleFlag.equals("global")){
                list = supervisioUserBiz.selectGrobalSubjectList(param);
            }else {
                list = supervisioUserBiz.selectSubjectList(param);
            }
            model.addAttribute("list", list);
            return "jsres/hospital/supervisio/subjectList";
        }
    }




    //卫健委 一键导出项目信息
    @RequestMapping(value = "/allInfoExpert")
    public void allInfoExpert( HttpServletResponse response,HttpServletRequest request,ResSupervisioSubject subject) throws Exception {
        String filePath=PkUtil.getUUID();
        //查询项目
        List<ResSupervisioSubject> list = supervisioUserBiz.selectBySubject(subject);
        HashSet<String> activitiFlowsSet = new HashSet<>();
        for (ResSupervisioSubject resSupervisioSubject : list) {
            expertMajor(request,resSupervisioSubject,filePath); //导出专业基地自评反馈报告
            expertBase(request,resSupervisioSubject,filePath);  //导出专业评分表（专业基地专家）
            if (!activitiFlowsSet.contains(resSupervisioSubject.getSubjectActivitiFlows())){
                exportOrgReport(request,resSupervisioSubject,filePath);  //导出基地自评反馈报告
                expertManage(request,resSupervisioSubject,filePath);     //导出基地自评管理表
                activitiFlowsSet.add(resSupervisioSubject.getSubjectActivitiFlows());
            }
        }
        File directory =new File(System.getProperty("java.io.tmpdir") + File.separator+filePath);
        File zipFlie =new File(System.getProperty("java.io.tmpdir") + File.separator +"督导项目评分信息.zip");
        String zipFolderName = "";
        ZipUtil.makeDirectoryToZip(directory,zipFlie,zipFolderName,7);

        //6.输出
        BufferedInputStream bis =  new BufferedInputStream(new FileInputStream(zipFlie));
        byte[] data= new byte[bis.available()];
        int len = 2048;
        byte[] b = new byte[len];
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(zipFlie.getName().getBytes("gbk"),"ISO8859-1" ) + "\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        while ((len = bis.read(b)) != -1)
        {
            outputStream.write(b, 0, len);
        }
        bis.close();
        outputStream.flush();
        outputStream.close();
    }

    public void Docx4jUtilLocal(String path, Map<String, Object> dataMap,String docxRealPath)throws Exception {
        //新建word模板
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        //文件名称
        temeplete = Docx4jUtil.convert(new File(path), dataMap, "", true);
        List<File> fileList = new ArrayList<>();
        OutputStream out = null;
        try {
            if (temeplete != null) {
                File docxFile = new File(docxRealPath);
                File docxDir = docxFile.getParentFile();
                if (!docxDir.exists()) {
                    docxDir.mkdirs();
                }
                docxFile.createNewFile();
                out = new FileOutputStream(docxFile);
                temeplete.save(out);
                fileList.add(docxFile);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //导出专业基地专家评分到指定文件夹
    public void expertBase(HttpServletRequest request,ResSupervisioSubject subject,String filePath) throws Exception {
        String path = "/jsp/jsres/assess/evaluation_"+subject.getSpeId()+".docx";//模板
        Map<String, Object> dataMap = new HashMap<String, Object>();     //定义数据容器
        SysUser sysUser = supervisioUserBiz.findUserByUserCode(subject.getBaseCode() + subject.getSpeId());
        SysOrg org = orgBiz.readSysOrg(subject.getOrgFlow());
        dataMap.put("orgName", org.getOrgName());
        dataMap.put("orgCityName", org.getOrgCityName());
        ResEvaluationScore searchScore = new ResEvaluationScore();
        searchScore.setSubjectFlow(subject.getSubjectFlow());
        searchScore.setOrgFlow(subject.getOrgFlow());
        searchScore.setSpeId(subject.getSpeId());
        searchScore.setEvaluationYear(subject.getSubjectYear());
        searchScore.setSpeUserFlow(sysUser.getUserFlow());
        searchScore.setSpeUserFlow("Owner");
        BigDecimal ownerScoreTotal = new BigDecimal(0);
        List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchScore);
        if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
            for (ResEvaluationScore score : ownerEvaluationScoreList) {
                dataMap.put(score.getItemId() + "ownerScore", score.getOwnerScore());
                if (StringUtil.isNotBlank(score.getOwnerScore())) {
                    ownerScoreTotal = ownerScoreTotal.add(new BigDecimal(score.getOwnerScore()));
                }
            }
        }
        dataMap.put("ownerScoreTotal", ownerScoreTotal + "");
        ResSupervisioSubjectUser subjectUser = supervisioUserBiz.selectSubjectUserByFlow(sysUser.getUserFlow(), subject.getSubjectFlow());
        if (null != subjectUser){
            dataMap.put("speContent", subjectUser.getSpeContent());
            dataMap.put("evaluationDate", subjectUser.getEvaluationDate());
        }
        //导出word文件
        Docx4jUtilLocal(request.getServletContext().getRealPath(path),dataMap,
                System.getProperty("java.io.tmpdir") + File.separator+filePath+File.separator
                        +subject.getOrgName()+File.separator+subject.getSpeName()+File.separator
                        +subject.getOrgName()+"-"+subject.getSubjectName()+"-"
                        +subject.getSpeName()+"科室-专业评分表.docx");
    }

    //导出基地管理表到指定文件夹
    public void expertManage(HttpServletRequest request,ResSupervisioSubject subject,String filePath)throws Exception{
        String path = "/jsp/jsres/assess/evaluation_4000.docx";//模板
        Map<String, Object> dataMap = new HashMap<String, Object>();     //定义数据容器
        SysOrg org = orgBiz.readSysOrg(subject.getOrgFlow());
        dataMap.put("orgName", org.getOrgName());
        dataMap.put("orgCityName", org.getOrgCityName());
        ResEvaluationScore searchLocalScore = new ResEvaluationScore();
        searchLocalScore.setSubjectFlow(subject.getSubjectActivitiFlows());
        searchLocalScore.setOrgFlow(subject.getOrgFlow());
        searchLocalScore.setSpeUserFlow(subject.getOrgFlow());
        searchLocalScore.setEvaluationYear(subject.getSubjectYear());
        List<ResEvaluationScore> ownerEvaluationScoreList = supervisioUserBiz.searchEvaluationScore(searchLocalScore);
        if (ownerEvaluationScoreList !=null && ownerEvaluationScoreList.size()>0) {
            BigDecimal ownerScoreTotalA = new BigDecimal(0);
            BigDecimal ownerScoreTotalB = new BigDecimal(0);
            BigDecimal ownerScoreTotalAll = new BigDecimal(0);
            if (null != ownerEvaluationScoreList && ownerEvaluationScoreList.size() > 0) {
                for (ResEvaluationScore score : ownerEvaluationScoreList) {
                    dataMap.put(score.getItemId() + "ownerScore", score.getSpeScore());
                    dataMap.put(score.getItemId() + "speReason", score.getSpeReason());
                    if (StringUtil.isNotBlank(score.getSpeScore()) && score.getItemId().startsWith("2022-6")) {
                        ownerScoreTotalA = ownerScoreTotalA.add(new BigDecimal(score.getSpeScore()));
                    } else if (StringUtil.isNotBlank(score.getSpeScore())) {
                        ownerScoreTotalB = ownerScoreTotalB.add(new BigDecimal(score.getSpeScore()));
                    }
                }
            }
            ownerScoreTotalAll = ownerScoreTotalA.add(ownerScoreTotalB);
            dataMap.put("ownerScoreTotalA", ownerScoreTotalA + "");
            dataMap.put("ownerScoreTotalB", ownerScoreTotalB + "");
            dataMap.put("ownerScoreTotalAll", ownerScoreTotalAll + "");
        }
        //导出word文件
        Docx4jUtilLocal(request.getServletContext().getRealPath(path),dataMap,
                System.getProperty("java.io.tmpdir") + File.separator+filePath+File.separator
                        +subject.getOrgName()+File.separator
                        +subject.getOrgName()+"-"+subject.getSubjectName()+"-"
                        +subject.getSpeName()+"自评管理表.doc");
    }

    //导出专业基地自评反馈报告到指定文件夹
    public void expertMajor(HttpServletRequest request,ResSupervisioSubject subject,String filePath)throws Exception{
        String path = "/jsp/jsres/hospital/supervisio/expertReport.docx";//模板
        Map<String, Object> dataMap = new HashMap<String, Object>();     //定义数据容器
        dataMap.put("speName",subject.getSpeName());
        List<ResSupervisioReport> supervisioReportList = supervisioUserBiz.reportBySubjectList(subject.getSubjectFlow(), null, "5");   //项目自己的专业报告 表中的数据
        if (supervisioReportList!=null && supervisioReportList.size()>0){
            for (ResSupervisioReport report : supervisioReportList) {
                dataMap.put(report.getContentTitle(),report.getContentMas());
            }
        }
        //导出word文件
        Docx4jUtilLocal(request.getServletContext().getRealPath(path),dataMap,
                System.getProperty("java.io.tmpdir") + File.separator+filePath+File.separator
                        +subject.getOrgName()+File.separator+subject.getSpeName()+File.separator
                        +subject.getOrgName()+"-"+subject.getSubjectName()+"-"
                        +subject.getSpeName()+"科室-专业基地自评反馈报告.docx");
    }

    //导出基地自评反馈报告文件到指定文件夹中
    public void exportOrgReport(HttpServletRequest request,ResSupervisioSubject subject,String filePath )throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();     //定义数据容器
        List<ResSupervisioReport> supervisioReportList=new ArrayList<>();       //存放评分数据（表）
        String path = "/jsp/jsres/hospital/supervisio/report.docx";//模板

        List<ResSupervisioReport> reportList = supervisioUserBiz.selectAllName("1");    //基地报告的第一部分  原始数据
        List<ResSupervisioReport> reportList2 = supervisioUserBiz.selectAllName("2");   //基地报告的第二部分 工作亮点    原始数据
        List<ResSupervisioReport> reportList3 = supervisioUserBiz.selectAllName("4");   //基地报告的第三部分 改进方案及措施     原始数据
        List<ResSupervisioReport> subjectList = supervisioUserBiz.expertMajorBySubjectList(null, subject.getSubjectActivitiFlows());      //项目的数据
        //如果用户存在自己的数据就替换，否则使用原始数据
        for (int i = 0; i < reportList.size(); i++) {
            if (subjectList!=null && subjectList.size()>0){
                for (int j = 0; j < subjectList.size(); j++) {
                    if (subjectList.get(j).getPartofFlow().equals(reportList.get(i).getRecordFlow())){
                        reportList.get(i).setContentMas(subjectList.get(j).getContentMas());
                    }
                }
            }
        }
        if (subjectList!=null && subjectList.size()>0){
            for (int i = 0; i < subjectList.size(); i++) {
                if (subjectList.get(i).getPartofFlow().equals("6")){
                    reportList2.get(0).setContentMas(subjectList.get(i).getContentMas());
                }
            }
        }
        if (subjectList!=null && subjectList.size()>0){
            for (int i = 0; i < subjectList.size(); i++) {
                if (subjectList.get(i).getPartofFlow().equals("7")){
                    reportList3.get(0).setContentMas(subjectList.get(i).getContentMas());
                }
            }
        }
        reportList.add(reportList2.get(0));
        reportList.add(reportList3.get(0));
        supervisioReportList = supervisioUserBiz.reportBySubjectList(null, subject.getSubjectActivitiFlows(), "3");   //项目自己的基地报告 表中的数据
        for (ResSupervisioReport report : reportList) {
            dataMap.put(report.getContentTitle(),report.getContentMas());
        }
        if (supervisioReportList!=null && supervisioReportList.size()>0){
            for (ResSupervisioReport report : supervisioReportList) {
                dataMap.put(report.getContentTitle(),report.getContentMas());
            }
        }
        //导出word文件
        Docx4jUtilLocal(request.getServletContext().getRealPath(path),dataMap,
                System.getProperty("java.io.tmpdir") + File.separator+filePath+File.separator
                        +subject.getOrgName()+File.separator
                        +subject.getOrgName()+"-"+subject.getSubjectName()+"-"
                        +subject.getSpeName()+"基地自评反馈报告.doc");
    }


    /**
     * 查询院级督导项目列表
     * @param model
     * @param currentPage
     * @param request
     * @param activityName  活动名称
     * @param inspectionType    活动形式
     * @param teachName 主讲人/实际主讲人
     * @param deptFlow  科室主键
     * @param activityStartTime 活动开始时间
     * @param activityEndTime   活动结束时间
     * @return
     */
    @RequestMapping(value = "/hospitalsubjectList")
    public String hospitalsubjectList(Model model, Integer currentPage, HttpServletRequest request,String activityName,
                                      String inspectionType,String teachName,String deptFlow,String activityStartTime,String activityEndTime) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("activityName",activityName);
        map.put("inspectionType",inspectionType);
        map.put("teachName",teachName);
        map.put("deptFlow",deptFlow);
        map.put("activityStartTime",activityStartTime);
        map.put("activityEndTime",activityEndTime);
        SysUser user = GlobalContext.getCurrentUser();
        map.put("orgFlow",user.getOrgFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResHospSupervSubject> list = supervisioUserBiz.selectHospSuperList(map);
        model.addAttribute("list",list);
        model.addAttribute("nowData",DateUtil.getCurrDateTime2());
        return "jsres/hospital/supervisio/hospitalsubjectList";
    }


    @RequestMapping(value = "/delHospitalsubject")
    @ResponseBody
    public String delHospitalsubject(String subjectFlow) {
        if (supervisioUserBiz.delHospSupervisioBySubjectFlow(subjectFlow) == 1 ){
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }


    @RequestMapping(value = "/hospitalStatisticsList")
    public String hospitalStatisticsList(Model model, Integer currentPage, HttpServletRequest request,String activityName,
                                         String speId,String deptFlow,String inspectionType,String startTime,String endTime) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("activityName",activityName);
        map.put("deptFlow",deptFlow);
        map.put("inspectionType",inspectionType);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        map.put("matching","已匹配");
        SysUser user = GlobalContext.getCurrentUser();
        map.put("orgFlow",user.getOrgFlow());
        if (StringUtil.isNotBlank(speId)){
            map.put("speId", speId.split(","));
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResHospSupervSubject> list = supervisioUserBiz.selectHospSuperList(map);
        model.addAttribute("list",list);
        return "jsres/hospital/supervisio/hospitalStatisticsList";
    }




    /**
     * 基地自评反馈查询
     * @param model
     * @param roleFlag
     * @param suAoth
     * @return
     */
    @RequestMapping(value = "/planScoreMainReport")
    public String planScoreMainReport(Model model, String roleFlag,String suAoth) {
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("suAoth",suAoth);
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId("420000");
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
            sysorg.setOrgFlow(orgFlow);
            model.addAttribute("orgFlow", orgFlow);
        }
        List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("currentTime", com.pinde.core.util.DateUtil.getYear());
        return "jsres/hospital/supervisio/planScoreMainReport";
    }

    //基地专业反馈
    @RequestMapping(value = "/planReportList")
    public String planReportList(Model model, String roleFlag, Integer currentPage, HttpServletRequest request,String baseCode,
                                 String orgFlow, String subjectYear) {
        model.addAttribute("roleFlag", roleFlag);
        Map<String, Object> param = new HashMap<>();
        param.put("subjectYear", subjectYear);
        param.put("orgFlow", orgFlow);
        param.put("baseCode", baseCode);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResSupervisioSubject> list=supervisioUserBiz.selectSubjectActiveListByParam(param);
        model.addAttribute("list", list);
        return "jsres/hospital/supervisio/planReportList";
    }

    //专业基地自评汇总查询
    @RequestMapping(value = "/baseExpertSuperVisio")
    public String baseExpertSuperVisio(Model model, String roleFlag) {
        model.addAttribute("roleFlag", roleFlag);
        if (roleFlag.equals("local")){
            SysUser sysUser = GlobalContext.getCurrentUser();
            model.addAttribute("orgName",sysUser.getOrgName());
            model.addAttribute("orgFlow",sysUser.getOrgFlow());
        }else {
            SysOrg sysorg = new SysOrg();
            sysorg.setOrgProvId("320000");
            sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
            model.addAttribute("orgs", orgs);
        }
        return "jsres/hospital/supervisio/baseExpertSuperVisio";
    }

    /**
     *专业基地汇总列表
     */
    @RequestMapping(value = "/baseExpertSupervisioList")
    public String baseExpertSupervisioList( Integer currentPage, HttpServletRequest request,Model model, String roleFlag,String subjectYear,
                                            String orgFlow,String orgPaiXu,String speId,String supervisioResults,String minBaseScore,
                                            String maxbaseScore,String manageAllSub,String minManageScore,String maxManageScore,String basePaiXu,String zipx) {
        model.addAttribute("roleFlag", roleFlag);
        Map<String, Object> param = new HashMap<>();

        if (StringUtil.isNotBlank(orgFlow)){
            List<String> userflowList = Arrays.asList(orgFlow.split(","));
            param.put("userFlow",userflowList);
        }
        if (StringUtil.isNotBlank(speId)){
            List<String> speList = Arrays.asList(speId.split(","));
            param.put("speId",speList);
        }
        param.put("subjectYear",subjectYear);
        param.put("basePaiXu",basePaiXu);
        param.put("zipx",zipx);

        param.put("supervisioResults",supervisioResults);
        param.put("minBaseScore",minBaseScore);
        param.put("maxbaseScore",maxbaseScore);
        param.put("minManageScore",minManageScore);
        param.put("maxManageScore",maxManageScore);

        param.put("manageAllSub",manageAllSub);
        param.put("orgPaiXu",(StringUtil.isBlank("orgPaiXu") || orgPaiXu.equals(""))?null:orgPaiXu);

        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,String>> list = supervisioUserBiz.searchBaseExpertSupervisio(param);
        model.addAttribute("list",list);
        return "jsres/hospital/supervisio/baseExpertSupervisioList";
    }

    //专业评分-点击检查基地名称显示该项目的所有信息
    @RequestMapping(value = "/planScoreInfo")
    public String planScoreInfo(Model model,String subjectFlow,String roleFlag,String baseInfo) {
        //查询条件
        List<ResSupervisioSubject> list= supervisioUserBiz.selectBySubjectFlow(subjectFlow);
        model.addAttribute("list", list);
        model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("baseInfo",baseInfo);
        return "jsres/hospital/supervisio/planScoreInfo";
    }

    @RequestMapping(value = "/hospitalSubjectInfo")
    public String hospitalSubjectInfo(Model model,String subjectFlow) {
        //查询条件
        ResHospSupervSubject hospSupervSubject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        if (null != hospSupervSubject){
            model.addAttribute("subject",hospSupervSubject);
        }
        return "jsres/hospital/supervisio/hospitalScoreInfo";
    }


    @RequestMapping(value = "/hospitalSubjectInfoTime")
    public String hospitalSubjectInfoTime(Model model,String subjectFlow) {
        //查询条件
        ResHospSupervSubject hospSupervSubject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        if (null != hospSupervSubject){
            model.addAttribute("subject",hospSupervSubject);
        }
        return "jsres/hospital/supervisio/hospitalScoreInfoTime";
    }

    //查询提交记录
    @RequestMapping(value = "/showRecords")
    public String showRecords(Model model,String subjectFlow,String roleFlag,String subjectActivitiFlows) {
        //专业基地专家提交的记录
        List<ResSupervisioSubjectRecords> baseExpertList = supervisioUserBiz.selectRecordBySubjectFlowAndRoleFlag(subjectFlow,"baseExpert");
        if (null !=baseExpertList && baseExpertList.size()>0){
            model.addAttribute("list", baseExpertList);
        }

        //基地提交的记录
        if (null!=roleFlag && !roleFlag.equals("baseExpert")){
            ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
            List<ResSupervisioSubjectRecords> localList = supervisioUserBiz.selectRecordBySubjectFlowAndRoleFlag(subject.getSubjectActivitiFlows(),"local");
            if (null !=localList && localList.size()>0){
                model.addAttribute("localList", localList);
            }
        }
        if (null!=roleFlag && !roleFlag.equals("baseExpert") && !roleFlag.equals("local")){
            List<ResSupervisioSubjectRecords> expertList = supervisioUserBiz.selectRecordBySubjectFlowAndRoleFlag(subjectFlow,"expertLeader");
            if (null !=expertList && expertList.size()>0){
                model.addAttribute("expertList", expertList);
            }
        }
        if (null!=roleFlag && (roleFlag.equals("management")||roleFlag.equals(GlobalConstant.USER_LIST_GLOBAL))){
            List<ResSupervisioSubjectRecords> managementList = supervisioUserBiz.selectRecordBySubjectFlowAndRoleFlag(subjectActivitiFlows,"management");
            if (null !=managementList && managementList.size()>0){
                model.addAttribute("managementList", managementList);
            }
        }
        return "jsres/hospital/supervisio/subjectSubRecords";
    }

    @RequestMapping(value = "/expertMajor")
    public String expertMajor(Model model, String roleFlag,String suAoth,String subjectActivitiFlows,String subjectFlow,
                              String isRead,String type,String speName) throws ParseException {

        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("suAoth",suAoth);
        model.addAttribute("subjectFlow",subjectFlow);
        if(StringUtil.isNotBlank(subjectFlow)){
            ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
            if (null!=subject.getExpertReportSub()){
                isRead="Y";
            }
        }else if (StringUtil.isNotBlank(subjectActivitiFlows)){
            List<ResSupervisioSubject> list = supervisioUserBiz.selectBySubjectActivitiFlows(subjectActivitiFlows);
            if (list!=null && list.size()>0){
                if (null!=roleFlag && roleFlag.equals("local")){
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date nowTime = sdf.parse(com.pinde.core.util.DateUtil.getCurrDateTime2());
                    Date devTime = sdf.parse(list.get(0).getDevTimeClose());
                    if (nowTime.compareTo(devTime)>0){
                        isRead="Y";
                    }
                }else if (null!=roleFlag && roleFlag.equals("management")){
                    //当前时间超过督导组评审时间，报告是只读的
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date nowTime = sdf.parse(com.pinde.core.util.DateUtil.getCurrDateTime2());
                    Date devTime = sdf.parse(list.get(0).getClosedTime());
                    if (nowTime.compareTo(devTime)>0){
                        isRead="Y";
                    }
                }
            }
        }
        model.addAttribute("isRead", isRead);
        if (type!=null && type.equals("major")){
            model.addAttribute("subjectActivitiFlows",subjectActivitiFlows);
            //基地表的第一部分数据
            List<ResSupervisioReport> reportList = supervisioUserBiz.selectAllName("1");
            //基地表的第二部分数据
            List<ResSupervisioReport> reportList2 = supervisioUserBiz.selectAllName("2");
            //基地表的第三部分数据
            List<ResSupervisioReport> reportList3 = supervisioUserBiz.selectAllName("4");
            //该项目的基地表数据
            List<ResSupervisioReport> subjectList = supervisioUserBiz.expertMajorBySubjectList(subjectFlow, subjectActivitiFlows);
            //遍历，如果基地存在自己的数据，就替换原始数据，否则就使用原始数据
            for (int i = 0; i < reportList.size(); i++) {
                if (subjectList!=null && subjectList.size()>0){
                    for (int j = 0; j < subjectList.size(); j++) {
                        if (subjectList.get(j).getPartofFlow().equals(reportList.get(i))){
                            reportList.get(i).setContentMas(subjectList.get(j).getContentMas());
                        }
                    }
                }
            }
            if (subjectList!=null && subjectList.size()>0){
                for (int i = 0; i < subjectList.size(); i++) {
                    if (subjectList.get(i).getPartofFlow().equals("6")){
                        reportList2.get(0).setContentMas(subjectList.get(i).getContentMas());
                    }
                }
            }
            if (subjectList!=null && subjectList.size()>0){
                for (int i = 0; i < subjectList.size(); i++) {
                    if (subjectList.get(i).getPartofFlow().equals("8")){
                        reportList3.get(0).setContentMas(subjectList.get(i).getContentMas());
                    }
                }
            }
            model.addAttribute("reportList",reportList);
            model.addAttribute("reportList2",reportList2);
            model.addAttribute("reportList3",reportList3);
            return "jsres/hospital/supervisio/major";
        }else if (type!=null && type.equals("spe")){
            model.addAttribute("subjectActivitiFlows",subjectFlow);
            List<ResSupervisioReport> subjectList = supervisioUserBiz.expertMajorBySubjectList(subjectFlow, subjectActivitiFlows);
            if (subjectList!=null && subjectList.size()>0){
                for (int i = 0; i < subjectList.size(); i++) {
                    if (!subjectList.get(i).getPartofFlow().equals("9")){
                        subjectList.remove(i);
                        i--;
                    }
                }
            }
            model.addAttribute("speName",speName);
            model.addAttribute("subjectList",subjectList);
            return "jsres/hospital/supervisio/expertReport";
        }else if (type !=null && type.equals("management")){
            model.addAttribute("subjectActivitiFlows",subjectActivitiFlows);
            List<ResSupervisioReport> reportList1 = supervisioUserBiz.selectAllName("11");//第一部分
            List<ResSupervisioReport> reportList2 = supervisioUserBiz.selectAllName("12");//第二部分
            List<ResSupervisioReport> reportList3 = supervisioUserBiz.selectAllName("17");//第三部分
            model.addAttribute("reportList",reportList1);
            model.addAttribute("reportList2",reportList2);
            model.addAttribute("reportList3",reportList3);
            return "jsres/hospital/supervisio/manageReport";
        }else {
            return "jsres/hospital/supervisio/major";
        }
    }

    @RequestMapping(value = "/exportReport")
    public String exportReport(String subjectActivitiFlows,String subjectFlow,String type,String speName,
                               HttpServletResponse response,HttpServletRequest request)throws Exception {
        Map<String, Object> dataMap = new HashMap<String, Object>();     //定义数据容器
        List<ResSupervisioReport> supervisioReportList=new ArrayList<>();       //存放评分数据（表）
        String path = "/jsp/jsres/hospital/supervisio/report.docx";//模板
        if (type!=null && type.equals("spe")){
            path = "/jsp/jsres/hospital/supervisio/expertReport.docx";//模板
            supervisioReportList = supervisioUserBiz.reportBySubjectList(subjectFlow, subjectActivitiFlows, "5");   //项目自己的专业报告 表中的数据
            dataMap.put("speName",speName);
        }else if (null!=type && type.equals("major")){
            List<ResSupervisioReport> reportList = supervisioUserBiz.selectAllName("1");    //基地报告的第一部分  原始数据
            List<ResSupervisioReport> reportList2 = supervisioUserBiz.selectAllName("2");   //基地报告的第二部分 工作亮点    原始数据
            List<ResSupervisioReport> reportList3 = supervisioUserBiz.selectAllName("4");   //基地报告的第三部分 改进方案及措施     原始数据
            List<ResSupervisioReport> subjectList = supervisioUserBiz.expertMajorBySubjectList(subjectFlow, subjectActivitiFlows);      //项目的数据
            //如果用户存在自己的数据就替换，否则使用原始数据
            for (int i = 0; i < reportList.size(); i++) {
                if (subjectList!=null && subjectList.size()>0){
                    for (int j = 0; j < subjectList.size(); j++) {
                        if (subjectList.get(j).getPartofFlow().equals(reportList.get(i).getRecordFlow())){
                            reportList.get(i).setContentMas(subjectList.get(j).getContentMas());
                        }
                    }
                }
            }
            if (subjectList!=null && subjectList.size()>0){
                for (int i = 0; i < subjectList.size(); i++) {
                    if (subjectList.get(i).getPartofFlow().equals("6")){
                        reportList2.get(0).setContentMas(subjectList.get(i).getContentMas());
                    }
                }
            }
            if (subjectList!=null && subjectList.size()>0){
                for (int i = 0; i < subjectList.size(); i++) {
                    if (subjectList.get(i).getPartofFlow().equals("7")){
                        reportList3.get(0).setContentMas(subjectList.get(i).getContentMas());
                    }
                }
            }
            reportList.add(reportList2.get(0));
            reportList.add(reportList3.get(0));
            supervisioReportList = supervisioUserBiz.reportBySubjectList(subjectFlow, subjectActivitiFlows, "3");   //项目自己的基地报告 表中的数据
            for (ResSupervisioReport report : reportList) {
                dataMap.put(report.getContentTitle(),report.getContentMas());
            }
        }else if (null!=type && type.equals("management")){
            List<ResSupervisioSubject> list = supervisioUserBiz.selectBySubjectActivitiFlows(subjectActivitiFlows);
            if (null!=list && list.size()>0){
                dataMap.put("orgName",list.get(0).getOrgName());
                StringBuffer name=new StringBuffer();
                for (int i = 0; i < list.size(); i++) {
                    name.append(list.get(i).getSpeName()+"、");
                }
                //专业名
                dataMap.put("name",name.substring(0,name.lastIndexOf("、")).toString());
                //时间
                Date openTime = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(0).getOpenTime().substring(0,11));
                Date closedTime = new SimpleDateFormat("yyyy-MM-dd").parse(list.get(0).getClosedTime().substring(0,11));
                dataMap.put("openTime",new SimpleDateFormat("yyyy年MM月dd日").format(openTime));
                dataMap.put("closedTime",new SimpleDateFormat("yyyy年MM月dd日").format(closedTime));
                List<ResSupervisioReport> reportList1 = supervisioUserBiz.selectAllName("11"); //督导报告第一部分
                List<ResSupervisioReport> reportList2 = supervisioUserBiz.selectAllName("12"); //督导报告第二部分
                List<ResSupervisioReport> reportList3 = supervisioUserBiz.selectAllName("17"); //督导报告第三部分
                List<ResSupervisioReport> subjectList = supervisioUserBiz.expertMajorBySubjectList(subjectFlow, subjectActivitiFlows); //项目的数据
                //如果用户存在自己的数据就替换，否则使用原始数据
                if (subjectList!=null && subjectList.size()>0){
                    for (int i = 0; i < reportList2.size(); i++) {
                        for (int j = 0; j < subjectList.size(); j++) {
                            if (subjectList.get(j).getPartofFlow().equals(reportList2.get(i).getRecordFlow())){
                                reportList2.get(i).setContentMas(subjectList.get(j).getContentMas());
                                continue;
                            }
                        }
                    }
                }
                if (subjectList!=null && subjectList.size()>0){
                    for (int i = 0; i < subjectList.size(); i++) {
                        if (subjectList.get(i).getPartofFlow().equals("11")){
                            reportList1.get(0).setContentMas(subjectList.get(i).getContentMas());
                        }
                    }
                }
                if (subjectList!=null && subjectList.size()>0){
                    for (int i = 0; i < subjectList.size(); i++) {
                        if (subjectList.get(i).getPartofFlow().equals("17")){
                            reportList3.get(0).setContentMas(subjectList.get(i).getContentMas());
                        }
                    }
                }
                dataMap.put(reportList1.get(0).getContentTitle(),reportList1.get(0).getContentMas());
                dataMap.put(reportList3.get(0).getContentTitle(),reportList3.get(0).getContentMas());
                for (ResSupervisioReport report : reportList2) {
                    dataMap.put(report.getContentTitle(),report.getContentMas());
                }
            }
            path = "/jsp/jsres/hospital/supervisio/manageReport.docx";
        }

        if (supervisioReportList!=null && supervisioReportList.size()>0){
            for (ResSupervisioReport report : supervisioReportList) {
                dataMap.put(report.getContentTitle(),report.getContentMas());
            }
        }
        //新建word模板
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        //context获取路径
        ServletContext context = request.getServletContext();
        //水印
        String watermark = "";
        //文件名称
        String name = "报告.docx";
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
        if (temeplete != null) {
            response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream();
            (new SaveToZipFile(temeplete)).save(out);
            out.flush();
        }
        return null;
    }





    @RequestMapping(value = "/saveReportContentMas")
    @ResponseBody
    public String saveReportContentMas(Model model, String itemId,String contentMas,String subjectActivitiFlows,String subjectFlow,String type) {
        ResSupervisioReport report = new ResSupervisioReport();
        if (StringUtil.isNotBlank(subjectActivitiFlows)){
            report.setSubjectFlow(subjectActivitiFlows);
        }else {
            report.setSubjectFlow(subjectFlow);
        }
        report.setContentMas(contentMas);
        if (type!=null && type.equals("major")){
            report.setPartof("3");
        }else if (type!=null&& type.equals("spe") ){
            report.setPartof("5");
        }

        report.setContentTitle(itemId);
        ResSupervisioReport supervisioReport = supervisioUserBiz.selectReportContextMas(report);
        if (supervisioReport!=null){
            supervisioReport.setContentMas(contentMas);
            if (supervisioUserBiz.saveReport(supervisioReport)>0){
                return GlobalConstant.SAVE_SUCCESSED;
            }
        }else {
            report.setRecordFlow(PkUtil.getUUID());
            if (type!=null && type.equals("major")){
                report.setPartofFlow("8");
            }else if (type!=null&& type.equals("spe") ){
                report.setPartofFlow("9");
            }
            if (reportMapper.insert(report)>0){
                return GlobalConstant.SAVE_SUCCESSED;
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value = "/subReport")
    @ResponseBody
    public String subReport(String subjectActivitiFlows,String subjectFlow,String sub){
        if (StringUtil.isNotBlank(subjectFlow)){
            ResSupervisioSubject subject = supervisioUserBiz.selectSubjectByFlow(subjectFlow);
            subject.setExpertReportSub(sub);
            if (supervisioUserBiz.updateSubject(subject)==1){
                return GlobalConstant.OPRE_SUCCESSED;
            }
        }else if (StringUtil.isNotBlank(subjectActivitiFlows)){
            List<ResSupervisioSubject> subjectList = supervisioUserBiz.selectBySubjectActivitiFlows(subjectActivitiFlows);
            int num=0;
            for (int i = 0; i < subjectList.size(); i++) {
                subjectList.get(i).setMajorReportSub(sub);
                num=num+supervisioUserBiz.updateSubject(subjectList.get(i));
            }
            if (num==subjectList.size()){
                return GlobalConstant.OPRE_SUCCESSED;
            }
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     *
     *基地自评反馈中单项内容查询
     * 先查询报告的初始化内容，如果用户没有修改保存，显示的依旧是初始化内容，
     * 如果修改就会保存用户修改的内容，在查询时，替换对应的初始化内容
     * （初始化内容是数据库中recordFlow 1-20的数据（自定义的flow），不是自动生成的uuid）
     */
    @RequestMapping(value = "/getContent")
    @ResponseBody
    public String getContent(String recordFlow,String subjectActivitiFlows,String subjectFlow,String san,Model model) {
        if (StringUtil.isNotBlank(recordFlow) && recordFlow.equals("8")){
            List<ResSupervisioReport> reportList = supervisioUserBiz.reportBySubjectList(subjectFlow, subjectActivitiFlows, "3");
            HashMap<String, String> map = new HashMap<>();
            for (ResSupervisioReport report : reportList) {
                map.put(report.getContentTitle(),report.getContentMas());
            }
            return  JSON.toJSONString(map);
        }
        List<String> list = new ArrayList<>();
        ResSupervisioReport resScheduleReport=new ResSupervisioReport();
        if (StringUtil.isBlank(subjectActivitiFlows) && StringUtil.isBlank(subjectFlow)){
            resScheduleReport = supervisioUserBiz.expertMajorByrecordFlow(recordFlow);
            list.add(resScheduleReport.getRecordFlow());
        }else{
            resScheduleReport= supervisioUserBiz.expertMajorBySubject(subjectFlow,subjectActivitiFlows, recordFlow);
            if (resScheduleReport==null){
                resScheduleReport = supervisioUserBiz.expertMajorByrecordFlow(recordFlow);
            }
            list.add(resScheduleReport.getPartofFlow());
        }
        list.add(resScheduleReport.getContentMas());

        return JSON.toJSONString(list);
    }

    /**
     * 保存报告内容
     */
    @RequestMapping(value = "/saveReport")
    @ResponseBody
    public String saveReport(Model model, String recordFlow,String contentMas,String subjectActivitiFlows,String subjectFlow) {
        if (StringUtil.isBlank(subjectActivitiFlows) && StringUtil.isBlank(subjectFlow)){
            ResSupervisioReport resScheduleReport = supervisioUserBiz.expertMajorByrecordFlow(recordFlow);
            resScheduleReport.setContentMas(contentMas);
            supervisioUserBiz.saveReport(resScheduleReport);
        }

        ResSupervisioReport resSupervisioReport= supervisioUserBiz.expertMajorBySubject(subjectFlow,subjectActivitiFlows, recordFlow);
        if (resSupervisioReport==null){
            ResSupervisioReport report = supervisioUserBiz.expertMajorByrecordFlow(recordFlow);
            ResSupervisioReport supervisioReport=new ResSupervisioReport();
            supervisioReport.setContentTitle(report.getContentTitle());
            supervisioReport.setPartof(report.getPartof());
            if (StringUtil.isBlank(subjectActivitiFlows)){
                supervisioReport.setSubjectFlow(subjectFlow);
            }else {
                supervisioReport.setSubjectFlow(subjectActivitiFlows);
            }
            supervisioReport.setContentMas(contentMas);
            supervisioReport.setPartofFlow(report.getPartofFlow());
            supervisioReport.setRecordFlow(PkUtil.getUUID());
            supervisioUserBiz.addReport(supervisioReport);
        }else {
            resSupervisioReport.setContentMas(contentMas);
            supervisioUserBiz.saveReport(resSupervisioReport);
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = "/manageSuperVisio")
    public String manageSuperVisio(Model model, String roleFlag) {
        model.addAttribute("roleFlag", roleFlag);
        SysOrg sysorg = new SysOrg();
        sysorg.setOrgProvId("320000");
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        return "jsres/hospital/supervisio/manageSuperVisio";
    }

    @RequestMapping(value = "/manageSupervisioList")
    public String manageSupervisioList( Integer currentPage, HttpServletRequest request,Model model, String roleFlag,String subjectYear,
                                        String orgFlow,String orgPaiXu,String manageAllSub,String basePaiXu) {
        model.addAttribute("roleFlag", roleFlag);
        Map<String, Object> param = new HashMap<>();
        param.put("orgFlow",orgFlow);
        param.put("subjectYear",subjectYear);
        param.put("orgPaiXu",orgPaiXu);
        param.put("manageAllSub",manageAllSub);
        param.put("basePaiXu",basePaiXu);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> list = supervisioUserBiz.searchManegeSupervisioList(param);
        model.addAttribute("list",list);
        return "jsres/hospital/supervisio/manageSupervisioList";
    }

    //院级督导 预览表
    @RequestMapping(value = "/viewTable")
    public String viewTable(Model model, String fileRoute, String edit){
        model.addAttribute("edit",edit);
        return "jsres/hospitalAssess/"+fileRoute;
    }

    //院级督导
    @RequestMapping(value = "/hospitalSchedule")
    public String hospitalSchedule(Model model , String edit, String subjectFlow,String speId,String roleFlag,String userFlow){
        SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("cityName",org.getOrgProvName()+" "+org.getOrgCityName());
        model.addAttribute("orgName",org.getOrgName());
        model.addAttribute("subjectFlow",subjectFlow);
        model.addAttribute("speId",speId);
        model.addAttribute("roleFlag","hospitalLeader");
        model.addAttribute("edit",edit);
        String fileRoute="";
        //查询专家签名
        SysUser user=new SysUser();
        if (StringUtil.isNotBlank(roleFlag) && roleFlag.equals("local")){
            user = userBiz.readSysUser(userFlow);
        }else {
            user = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
        }
        if (null !=user){
            if (null !=user.getUserSignUrl()){
                model.addAttribute("speSignUrl", user.getUserSignUrl());
                model.addAttribute("specialistName", user.getUserName());
            }
        }
        if (StringUtil.isNotBlank(subjectFlow)){
            ResScheduleScore score=new ResScheduleScore();
            score.setSubjectFlow(subjectFlow);
            score.setSpeId(speId);
            score.setGrade("hospitalLeader");
            score.setUserFlow(user.getUserFlow());
            List<ResScheduleScore> scoreList = resScheduleScoreBiz.queryScheduleList(score);
            model.addAttribute("scoreList",scoreList);
            ResHospSupervSubject hospSupervSubject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
            model.addAttribute("deptName",hospSupervSubject.getDeptName());
            fileRoute=hospSupervSubject.getScoreTable();
            TeachingActivityInfo activityInfo = activityInfoMapper.selectByPrimaryKey(hospSupervSubject.getActivityFlow());
            if (com.pinde.core.util.StringUtil.isNotBlank(activityInfo.getDeptFlow())){
                SysDept sysDept = deptMapper.selectByPrimaryKey(activityInfo.getDeptFlow());
                if (null !=sysDept){
                    model.addAttribute("speSkillName",sysDept.getDeptName());
                }
            }
//            List<Map<String, Object>> depts=deptBiz.queryDeptListByFlow(GlobalContext.getCurrentUser().getUserFlow());
            model.addAttribute("speName",hospSupervSubject.getSpeName());

            ResHospSupervSubject subject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
            if (StringUtil.isNotBlank(subject.getLeaderOneFlow()) && subject.getLeaderOneFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(subject.getLeaderOneEndTime())){
                    model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(subject.getLeaderOneEndTime().substring(0,10).toString(),"yyyy-MM-dd"));
                }else {
                    model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(com.pinde.core.util.DateUtil.getCurrDate(), "yyyy-MM-dd"));
                }
            }else if (StringUtil.isNotBlank(subject.getLeaderTwoFlow()) && subject.getLeaderTwoFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(subject.getLeaderTwoEndTime())){
                    model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(subject.getLeaderTwoEndTime().substring(0,10).toString(),"yyyy-MM-dd"));
                }else {
                    model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(com.pinde.core.util.DateUtil.getCurrDate(), "yyyy-MM-dd"));
                }
            }else if (StringUtil.isNotBlank(subject.getLeaderThreeFlow()) && subject.getLeaderThreeFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(subject.getLeaderThreeEndTime())){
                    model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(subject.getLeaderThreeEndTime().substring(0,10).toString(),"yyyy-MM-dd"));
                }else {
                    model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(com.pinde.core.util.DateUtil.getCurrDate(), "yyyy-MM-dd"));
                }
            }else if (StringUtil.isNotBlank(subject.getLeaderFourFlow()) && subject.getLeaderFourFlow().equals(user.getUserFlow())){
                if (StringUtil.isNotBlank(subject.getLeaderFourEndTime())){
                    model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(subject.getLeaderFourEndTime().substring(0,10).toString(),"yyyy-MM-dd"));
                }else {
                    model.addAttribute("scheduleDate", com.pinde.core.util.DateUtil.parseDate(com.pinde.core.util.DateUtil.getCurrDate(), "yyyy-MM-dd"));
                }
            }
            int peopleNum = activityBiz.countByActivity(subject.getActivityFlow());
            model.addAttribute("peopleNum",peopleNum);  //参加教学活动的人数

            SysUser activityUser = userBiz.readSysUser(subject.getTeachFlow());
            model.addAttribute("activityUserDept",activityUser.getDeptName());  //主讲人专业
            model.addAttribute("activityUserName",subject.getTeachName());  //主讲人名称
            model.addAttribute("activityStartTime",subject.getActivityStartTime()); //活动开始时间
            model.addAttribute("activityEndTime",subject.getActivityEndTime()); //活动结束时间
            model.addAttribute("activityDept",subject.getDeptName()); //活动科室名称
            model.addAttribute("speAndDept",hospSupervSubject.getSpeName()+"/"+hospSupervSubject.getDeptName());  //专业基地/科室
            model.addAttribute("activityMinute",DateUtil.signMinutesBetweenTowDate(subject.getActivityStartTime()+":00",subject.getActivityEndTime()+":00"));//教学时长
        }
        model.addAttribute("userFlow",user.getUserFlow());
        model.addAttribute("fileRoute",fileRoute);
        model.addAttribute("teacherName",user.getUserName());   //专家名称
        return "jsres/hospitalAssess/"+fileRoute;
    }



    @RequestMapping(value = "/saveHospitalScore")
    @ResponseBody
    public String saveHospitalScore(String subjectFlow,String expertTotal) {
        SysUser user = GlobalContext.getCurrentUser();
        ResHospSupervSubject subject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        subject.setEndTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
        subject.setReviewConfig("N");
        //判断提交人是哪一位专家，保存分数
        if (subject.getLeaderOneFlow().equals(user.getUserFlow())){
            if (StringUtil.isBlank(subject.getLeaderOneScore())) {
                //已提交专家数量
                int value = 0;
                if (StringUtil.isNotBlank(subject.getLeaderSubNum())) {
                    value = Integer.parseInt(subject.getLeaderSubNum());
                }
                subject.setLeaderSubNum(String.valueOf(value + 1));
            }
            subject.setLeaderOneScore(expertTotal);
            subject.setLeaderOneEndTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
        }else if (subject.getLeaderTwoFlow().equals(user.getUserFlow())){
            if (StringUtil.isBlank(subject.getLeaderTwoScore())) {
                //已提交专家数量
                int value = 0;
                if (StringUtil.isNotBlank(subject.getLeaderSubNum())) {
                    value = Integer.parseInt(subject.getLeaderSubNum());
                }
                subject.setLeaderSubNum(String.valueOf(value + 1));
            }
            subject.setLeaderTwoScore(expertTotal);
            subject.setLeaderTwoEndTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
        }else if (subject.getLeaderThreeFlow().equals(user.getUserFlow())){
            if (StringUtil.isBlank(subject.getLeaderThreeScore())) {
                //已提交专家数量
                int value = 0;
                if (StringUtil.isNotBlank(subject.getLeaderSubNum())) {
                    value = Integer.parseInt(subject.getLeaderSubNum());
                }
                subject.setLeaderSubNum(String.valueOf(value + 1));
            }
            subject.setLeaderThreeScore(expertTotal);
            subject.setLeaderThreeEndTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
        }else if (subject.getLeaderFourFlow().equals(user.getUserFlow())){
            if (StringUtil.isBlank(subject.getLeaderFourScore())) {
                //已提交专家数量
                int value = 0;
                if (StringUtil.isNotBlank(subject.getLeaderSubNum())) {
                    value = Integer.parseInt(subject.getLeaderSubNum());
                }
                subject.setLeaderSubNum(String.valueOf(value + 1));
            }
            subject.setLeaderFourScore(expertTotal);
            subject.setLeaderFourEndTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
        }else {
            return GlobalConstant.SAVE_FAIL;
        }
        //四位专家都提交分数才能计算平均分
        if (StringUtil.isNotBlank(subject.getLeaderOneScore()) && StringUtil.isNotBlank(subject.getLeaderTwoScore()) && StringUtil.isNotBlank(subject.getLeaderThreeScore()) && StringUtil.isNotBlank(subject.getLeaderFourScore())){
            BigDecimal allScore = new BigDecimal(subject.getLeaderOneScore()).add(new BigDecimal(subject.getLeaderTwoScore())).add(new BigDecimal(subject.getLeaderThreeScore())).add(new BigDecimal(subject.getLeaderFourScore()));
            BigDecimal avgScore = allScore.divide(new BigDecimal(4));
            subject.setAvgScore(avgScore.toString());
        }
        if (supervisioUserBiz.updateHospSupervisioBySubjectFlow(subject)==1){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }



    @RequestMapping(value = "/saveHospitalScheduleScore")
    @ResponseBody
    public String saveHospitalScheduleScore(String itemId, String score, String itemName, String orgName, String orgFlow,
                                            String speId, String roleFlag, String subjectFlow, String num, String fileRoute) {
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setGrade("hospitalLeader");

        SysUser sysUser = GlobalContext.getCurrentUser();
        resScheduleScore.setUserFlow(sysUser.getUserFlow());
        resScheduleScore.setItemId(itemId);
        resScheduleScore.setScore(score);
        resScheduleScore.setItemName(itemName);
        resScheduleScore.setOrgFlow(sysUser.getOrgFlow());
        resScheduleScore.setOrgName(orgName);
        resScheduleScore.setFileRoute(fileRoute);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setSubjectFlow(subjectFlow);
        String itemName2 = "";
        resScheduleScore.setScoreType("K");
        if (itemName.startsWith("d")) {
            itemName2 = "k" + itemName.substring(1);

        } else {
            itemName2 = "d" + itemName.substring(1);
        }
        if (resScheduleScoreBiz.saveSchedule(resScheduleScore) <= 0) {
            return GlobalConstant.OPERATE_FAIL;
        }
        resScheduleScore.setItemName(itemName2);
        resScheduleScore.setScore(String.valueOf(new BigDecimal(num).subtract(new BigDecimal(score))));
        resScheduleScore.setScoreType("d");
        resScheduleScoreBiz.saveSchedule(resScheduleScore);
        ResHospSupervSubject hospSupervSubject = supervisioUserBiz.selectHospSupervisioBySubjectFlow(subjectFlow);
        //评审项目评审开始时间
        if (StringUtil.isBlank(hospSupervSubject.getStartTime())){
            hospSupervSubject.setStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime().substring(0,12)));
        }

        //记录评分开始时间
        if (hospSupervSubject.getLeaderOneFlow().equals(sysUser.getUserFlow())){
            if (StringUtil.isBlank(hospSupervSubject.getLeaderOneStartTime())){
                hospSupervSubject.setLeaderOneStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (supervisioUserBiz.updateHospSupervisioBySubjectFlow(hospSupervSubject)!=1){
                    return GlobalConstant.OPERATE_FAIL;
                }
            }
        }else {
            if (StringUtil.isBlank(hospSupervSubject.getLeaderTwoStartTime())){
                hospSupervSubject.setLeaderTwoStartTime(DateUtil.transDateTime(DateUtil.getCurrDateTime()));
                if (supervisioUserBiz.updateHospSupervisioBySubjectFlow(hospSupervSubject)!=1){
                    return GlobalConstant.OPERATE_FAIL;
                }
            }
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    // **********************************************************基地自评接口开始 ******************************************************

    //省厅配置基地自评时间
    @RequestMapping(value = "/assessmentCfgMain")
    public String assessmentCfgMain(){
        return "jsres/hospital/supervisio/assessmentCfgMain";
    }

    //省厅配置基地自评的列表
    @RequestMapping(value = "/assessmentCfgList")
    public String assessmentList(HospSelfAssessmentCfg cfg,Model model,Integer currentPage, HttpServletRequest request){
        PageHelper.startPage(currentPage, getPageSize(request));
        List<HospSelfAssessmentCfg> list = supervisioBiz.findAllCfg(cfg);
        model.addAttribute("list",list);
        model.addAttribute("nowTime",DateUtil.getCurrDateTime2());
        return "jsres/hospital/supervisio/assessmentCfgList";
    }

    //基地自评配置新增、修改页面
    @RequestMapping(value = "/editAssessmentCfg")
    public String editAssessmentCfg(String cfgFlow,String type,Model model){
        HospSelfAssessmentCfg cfg = assessmentCfgMapper.selectByPrimaryKey(cfgFlow);
        model.addAttribute("cfg",cfg);
        model.addAttribute("type",type);
        return "jsres/hospital/supervisio/editAssessmentCfg";
    }

    //基地自评配置保存、修改接口
    @RequestMapping(value = "/saveAssessmentCfg")
    @ResponseBody
    public String saveHospitalSelfAssessment(HospSelfAssessmentCfg cfg,String type){
        return supervisioBiz.saveAssessment(cfg,type);
    }

     //基地角色：医院基地自评
    @RequestMapping(value = "/hospitalSelfAssessment")
    public String hospitalSelfAssessment(){
        return "jsres/hospital/supervisio/hospitalSelfAssessmentMain";
    }

    //基地角色：医院基地自评列表
    @RequestMapping(value = "/hospitalSelfAssessmentList")
    public String hospitalSelfAssessmentList(HospSelfAssessmentCfg cfg,Model model,Integer currentPage, HttpServletRequest request){
        PageHelper.startPage(currentPage, getPageSize(request));
        List<HospSelfAssessmentCfg> list = supervisioBiz.findAllCfg(cfg);
        model.addAttribute("list",list);
        model.addAttribute("nowTime",DateUtil.getCurrDateTime2());
        model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        return "jsres/hospital/supervisio/hospitalSelfAssessmentList";
    }

    /**
     * 显示评分表单
     * @param cfgFlow 省厅配置项的主键
     * @param orgFlow
     * @param speId
     * @param sessionNumber 省厅配置项的自评年度
     * @param subjectType   评分类型：基地是org，专业是spe
     * @param type  是否可以评分
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/showAssessmentInfo")
    public String showAssessmentInfo(String cfgFlow,String orgFlow,String sessionNumber,String speId,String subjectType,String type,Model model) throws ParseException {
        //查询该年度的表单是否提交
        HospSelfAssessment assessment = supervisioBiz.findHospSelfAssessment(sessionNumber, orgFlow, speId, subjectType);

        model.addAttribute("type",type);
        ResEvaluationScore score =new ResEvaluationScore();
        score.setOrgFlow(orgFlow);
        score.setSubjectFlow(cfgFlow);
        score.setSpeId(speId);
        List<ResEvaluationScore> scoreList = supervisioBiz.findHospSelfAssessmentAllScore(score,subjectType);
        HashMap<String, String> scoreMap = new HashMap<String, String>();  //主表评的每一项分数
        HashMap<String, String> contentMap = new HashMap<String, String>();//主表评的每一项扣分原因
        HashMap<String, String> scheduleMap = new HashMap<String, String>();//每一张附表计算的总分值（换算成百分比的）
        ResScheduleScore resScheduleScore = new ResScheduleScore();
        resScheduleScore.setScoreType("Totalled");
        resScheduleScore.setSubjectFlow(cfgFlow);
        resScheduleScore.setOrgFlow(orgFlow);
        resScheduleScore.setSpeId(speId);
        resScheduleScore.setEvaluationYear(sessionNumber);
        List<ResScheduleScore> scheduleList = supervisioBiz.queryScheduleList(resScheduleScore);

        for (ResScheduleScore s : scheduleList) {
            if (StringUtil.isNotBlank(s.getScore())){
                scheduleMap.put(s.getItemId(),s.getScore());
            }                                                               
        }
        for (ResEvaluationScore s : scoreList) {
            if (StringUtil.isNotBlank(s.getOwnerScore())){
                scoreMap.put(s.getItemId(),s.getOwnerScore());
            }
            if (StringUtil.isNotBlank(s.getSpeContent())){
                if (StringUtil.isNotBlank(type)&& type.equals("Y")){
                    contentMap.put(s.getItemId(),s.getSpeContent().replaceAll("<br/>", "\n"));
                }else {
                    contentMap.put(s.getItemId(),s.getSpeContent());
                }
            }
        }

        //未提及或者评分显示当前人的签名
        if (null==assessment || StringUtil.isNotBlank(type)&& type.equals("Y")){
            SysUser user = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
            model.addAttribute("speSignUrl", user.getUserSignUrl());
        }else {
            //已经提交查看的，显示提交人的签名
            SysUser user = userBiz.readSysUser(assessment.getSubUserFlow());
            model.addAttribute("speSignUrl", user.getUserSignUrl());
        }

        model.addAttribute("scheduleMap",scheduleMap);
        model.addAttribute("scoreMap",scoreMap);
        model.addAttribute("contentMap",contentMap);
        SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
        model.addAttribute("orgName",sysOrg.getOrgName());
        model.addAttribute("cityName",sysOrg.getOrgProvName()+sysOrg.getOrgCityName());
        HospSelfAssessment modeAssessment = new HospSelfAssessment();
        modeAssessment.setRecordFlow(cfgFlow);
        modeAssessment.setOrgFlow(orgFlow);
        modeAssessment.setOrgName(sysOrg.getOrgName());
        modeAssessment.setSessionNumber(sessionNumber);
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId("DoctorTrainingSpe");
        sysDict.setDictId(speId);
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if (null!=dictList && dictList.size()>0){
            modeAssessment.setSpeId(speId);
            modeAssessment.setSpeName(dictList.get(0).getDictName());
        }
        model.addAttribute("assessment",modeAssessment);

        String timeInfo=DateUtil.getCurrDate();
        if (null!=assessment && StringUtil.isNotBlank(assessment.getSubTime())){
            timeInfo=assessment.getSubTime();
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeInfo);
        String subTime = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        model.addAttribute("subTime",subTime);
        JsresSupervisioFile supervisioFile = new JsresSupervisioFile();
        supervisioFile.setSubjectFlow(cfgFlow);
        supervisioFile.setOrgFlow(orgFlow);
        supervisioFile.setSpeId(speId);
        List<JsresSupervisioFile> supervisioFileList = supervisioBiz.findSupervisioFile(supervisioFile,subjectType);
        if (supervisioFileList.size() > 0) {
            model.addAttribute("supervisioFileList", supervisioFileList);
        }
        if (StringUtil.isNotBlank(subjectType) && subjectType.equals("spe")){
            return "jsres/hospital/supervisio/assessment/hospSelfAssessment"+speId;
        }
        return "jsres/hospital/supervisio/assessment/hospSelfAssessmentInfo";
    }


    //查看项目达标的核心指标
    //0达标的核心指标   1未达标的核心指标   2 不是核心指标
    @RequestMapping(value = "/findCoreIndicatorsNum")
    @ResponseBody
    public Integer findCoreIndicatorsNum(String cfgFlow,String orgFlow,String speId,String subjectType){
        return supervisioBiz.findCoreIndicatorsNum(cfgFlow,orgFlow,speId,subjectType);
    }

    //提交表单
    @RequestMapping(value = "/saveAssessmentAllScore")
    @ResponseBody
    public String saveAssessmentAllScore(HospSelfAssessment assessment){
        if (supervisioBiz.saveAssessmentAllScore(assessment)>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //专业基地：专业基地自评
    @RequestMapping(value = "/hospitalSpeSelfAssessment")
    public String hospitalSpeSelfAssessment(){
        return "jsres/hospital/supervisio/hospitalSpeSelfAssessmentMain";
    }

    //专业基地：专业基地自评列表
    @RequestMapping(value = "/hospitalSpeSelfAssessmentList")
    public String hospitalSpeSelfAssessmentList(HospSelfAssessmentCfg cfg,Model model,Integer currentPage, HttpServletRequest request){
        PageHelper.startPage(currentPage, getPageSize(request));
        List<HospSelfAssessmentCfg> list = supervisioBiz.findAllCfg(cfg);
        model.addAttribute("list",list);
        model.addAttribute("nowTime",DateUtil.getCurrDateTime2());
        model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("speId",GlobalContext.getCurrentUser().getResTrainingSpeId());
        return "jsres/hospital/supervisio/hospitalSpeSelfAssessmentList";
    }

    //保存评分的接口
    @RequestMapping(value = "/saveHospitalSelfAssessmentScore")
    @ResponseBody
    public String saveHospitalSelfAssessmentScore(ResEvaluationScore resEvaluationScore) throws UnsupportedEncodingException {
        if (supervisioBiz.saveHospSelfAssessmentScore(resEvaluationScore)>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //基地：专业基地自评
    @RequestMapping(value = "/selfAssessmentMain")
    public String selfAssessmentMain(){
        return "jsres/hospital/supervisio/selfAssessmentMain";
    }

    //基地：专业基地自评列表
    @RequestMapping(value = "/selfAssessmentList")
    public String selfAssessmentList(HospSelfAssessment assessment,Model model,Integer currentPage, HttpServletRequest request){
        assessment.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<HospSelfAssessment> list = supervisioBiz.findAllAssessmentBySpeAndYear(assessment);
        model.addAttribute("list",list);
        HospSelfAssessmentCfg cfg = supervisioBiz.findCfgBySessionNumber(assessment.getSessionNumber());
        model.addAttribute("cfg",cfg);
        return "jsres/hospital/supervisio/selfAssessmentList";
    }

    /**
     * 多个附表跳转
     */
    @RequestMapping(value = "/showAssessmentMany")
    public String showAssessmentMany(HospSelfAssessment assessment,String type, String fileRoute, Model model) {
        model.addAttribute("assessment", assessment);
        model.addAttribute("type", type);
        return "jsres/hospital/supervisio/assessment/"+fileRoute;
    }

    /**
     * 单个附表跳转
     */
    @RequestMapping(value = "/showAssessment")
    public String showAssessment(String fileRoute,String cfgFlow,String orgFlow,String speId,Model model,String type) throws ParseException {
        ResScheduleScore score=new ResScheduleScore();
        score.setSubjectFlow(cfgFlow);
        score.setOrgFlow(orgFlow);
        score.setSpeId(speId);
        score.setFileRoute(fileRoute);
        score.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<ResScheduleScore> scoreList = supervisioBiz.queryScheduleList(score);
        Double expertTotalled=0.0;
        Integer substandard=0;
        if (null!=scoreList && scoreList.size()>0){
            HashMap<String, String> scoreMap = new HashMap<>(); //得分
            HashMap<String, String> substandardMap = new HashMap<>();   //低于标准
            HashMap<String, String> detailedMap = new HashMap<>();  //扣分原因
            for (ResScheduleScore scheduleScore : scoreList) {
                if (StringUtil.isNotBlank(scheduleScore.getScore())){
                    scoreMap.put(scheduleScore.getItemId(),scheduleScore.getScore());
                    if (StringUtil.isNotBlank(scheduleScore.getScoreType()) && scheduleScore.getScoreType().equals("spe")
                            &&(isNumeric(scheduleScore.getScore()) || isNumeric(scheduleScore.getScore().substring(1,scheduleScore.getScore().length())))){
                        expertTotalled=expertTotalled+Double.parseDouble(scheduleScore.getScore());
                    }
                }
                if (StringUtil.isNotBlank(scheduleScore.getSubstandard())){
                    substandardMap.put(scheduleScore.getItemId(),scheduleScore.getSubstandard());
                    if (scheduleScore.getSubstandard().equals("√")){
                        substandard++;
                    }
                }
                if (StringUtil.isNotBlank(scheduleScore.getItemDetailed())){
                    if (StringUtil.isNotBlank(type)&& type.equals("Y")){
                        detailedMap.put(scheduleScore.getItemId(),scheduleScore.getItemDetailed().replaceAll("<br/>", "\n"));
                    }else {
                        detailedMap.put(scheduleScore.getItemId(),scheduleScore.getItemDetailed());
                    }
                }
            }
            model.addAttribute("scoreMap", scoreMap);
            model.addAttribute("detailedMap", detailedMap);
            model.addAttribute("substandardMap", substandardMap);
        }

        HospSelfAssessmentCfg cfg = assessmentCfgMapper.selectByPrimaryKey(cfgFlow);
        HospSelfAssessment assessment = supervisioBiz.findHospSelfAssessment(cfg.getSessionNumber(), orgFlow, speId, "spe");

        if (null==assessment || StringUtil.isNotBlank(type)&& type.equals("Y")){
            SysUser user = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
            model.addAttribute("speSignUrl", user.getUserSignUrl());
        }else {
            SysUser user = userBiz.readSysUser(assessment.getSubUserFlow());
            model.addAttribute("speSignUrl", user.getUserSignUrl());
        }
        String timeInfo=DateUtil.getCurrDate();
        if (null!=assessment && StringUtil.isNotBlank(assessment.getSubTime())){
            timeInfo=assessment.getSubTime();
        }
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(timeInfo);
        String subTime = new SimpleDateFormat("yyyy年MM月dd日").format(date);
        model.addAttribute("subTime",subTime);
        String orgName = orgBiz.readSysOrg(orgFlow).getOrgName();
        model.addAttribute("orgName", orgName);
        model.addAttribute("expertTotalled", expertTotalled);
        model.addAttribute("substandard", substandard);
        HospSelfAssessment modeAssessment = new HospSelfAssessment();
        modeAssessment.setRecordFlow(cfgFlow);
        modeAssessment.setOrgFlow(orgFlow);
        modeAssessment.setOrgName(orgName);
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId("DoctorTrainingSpe");
        sysDict.setDictId(speId);
        sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if (null!=dictList && dictList.size()>0){
            modeAssessment.setSpeId(speId);
            modeAssessment.setSpeName(dictList.get(0).getDictName());
            model.addAttribute("speAndDept", dictList.get(0).getDictName());
        }
        modeAssessment.setSessionNumber(cfg.getSessionNumber());
        model.addAttribute("assessment", modeAssessment);
        model.addAttribute("type", type);
        model.addAttribute("fileRoute", fileRoute);
        return "jsres/hospital/supervisio/assessment/"+fileRoute;
    }

    //保存附表单项评分
    @RequestMapping(value = "/saveAssessmengtScoreOne")
    @ResponseBody
    public String saveAssessmengtScoreOne(ResScheduleScore score) throws UnsupportedEncodingException {
        if (supervisioBiz.saveSchedule(score)>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //删除附表单项评分
    @RequestMapping(value = "/delAssessmengtScoreOne")
    @ResponseBody
    public String delAssessmengtScoreOne(ResScheduleScore score) {
        if (supervisioBiz.delSchedule(score)>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //保存附表总分
    @RequestMapping(value = "/saveManyAssessmentTotalled")
    @ResponseBody
    public String saveManyAssessmentTotalled(ResScheduleScore score,String selfOneScore,String selfTwoScore,String itemIdOne,String itemIdTwo) throws UnsupportedEncodingException {
        score.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        Integer allNum=0;
        Integer sucessNum=0;
        if (StringUtil.isNotBlank(selfOneScore)){
            allNum++;
            score.setItemId(itemIdOne);
            score.setScore(selfOneScore);
            sucessNum=sucessNum+supervisioBiz.saveSchedule(score);
        }

        if (StringUtil.isNotBlank(selfTwoScore)){
            allNum++;
            score.setItemId(itemIdTwo);
            score.setScore(selfTwoScore);
            sucessNum=sucessNum+supervisioBiz.saveSchedule(score);
        }
        if (allNum==sucessNum){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    //专业基地：签名管理
    @RequestMapping(value = "/userSignSelf")
    public String userSignSelf(Model model) {
        SysUser user = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
        model.addAttribute("user", user);
        return "jsres/hospital/supervisio/userSignSelf";
    }

    //基地：签名管理
    @RequestMapping(value = "/userSignAssessment")
    public String userSignAssessment(Model model) {
        SysUser user = userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
        model.addAttribute("user", user);
        return "jsres/hospital/supervisio/userSignAssessment";
    }



    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*\\.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    // **********************************************************基地自评接口结束 ******************************************************
}
