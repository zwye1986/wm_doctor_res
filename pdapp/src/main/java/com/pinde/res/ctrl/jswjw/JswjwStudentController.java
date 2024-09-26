package com.pinde.res.ctrl.jswjw;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.commom.enums.RecDocCategoryEnum;
import com.pinde.core.commom.enums.ResDoctorKqStatusEnum;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.jswjw.IIeaveAppBiz;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.enums.lcjn.DictTypeEnum;
import com.pinde.res.model.jswjw.mo.ResDoctorKqExt;
import com.pinde.sci.dao.base.ResDoctorMapper;
import com.pinde.sci.dao.base.ResDoctorSchProcessMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @ClassName JswjwStudentController
 * @Deacription APP学员功能Controller
 * @Author shengl
 * @Date 2021-01-12 15:13
 * @Version 1.0
 **/
@Controller
@RequestMapping("/res/jswjw/student")
public class JswjwStudentController {

    private static String successUrl = "res/jswjw/success";

    private static Logger logger = LoggerFactory.getLogger(JswjwStudentController.class);

    @Autowired
    IIeaveAppBiz ieaveAppBiz;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ResDoctorMapper doctorMapper;
    @Autowired
    private ResDoctorSchProcessMapper resDoctorProcessMapper;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IJswjwBiz jswjwBiz;

    /**
     * @Author shengl
     * @Description // 请假列表
     * @Date 2021-01-12
     **/
    @RequestMapping(value = {"/leaveAndAppealList"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String leaveAndAppealList(ResDoctorKq kq, String userFlow, String statusId, Integer pageIndex, Integer pageSize,
                                     HttpServletRequest request, HttpServletResponse response, Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return successUrl;
        }
        if (StringUtil.isEmpty(kq.getKqTypeId())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "kqTypeId类型标识符为空");
            return successUrl;
        }
        if (StringUtil.isNotBlank(statusId)) {
            if (!"Passed".equals(statusId) && !"Auditing".equals(statusId) && !"UnPassed".equals(statusId) && !"Revoke".equals(statusId)) {
                model.addAttribute("resultId", "30401");
                model.addAttribute("resultType", "状态标识符不正确,Auditing，UnPassed，Passed，Revoke");
                return successUrl;
            }
        }
        kq.setDoctorFlow(userFlow);
        if (!DictTypeEnum.LeaveType.getId().equals(kq.getKqTypeId()) && !DictTypeEnum.AppealType.getId().equals(kq.getKqTypeId())) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "类型标识符不正确,LeaveType，AppealType");
            return successUrl;
        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "30202");
            model.addAttribute("resultType", "当前页码为空");
            return successUrl;
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "30203");
            model.addAttribute("resultType", "每页条数为空");
            return successUrl;
        }
        List<String> status = new ArrayList<>();
        if ("Passed".equals(statusId)) {
            status.add(ResDoctorKqStatusEnum.TeacherPass.getId());
            status.add(ResDoctorKqStatusEnum.HeadPass.getId());
            status.add(ResDoctorKqStatusEnum.TutorPass.getId());
            status.add(ResDoctorKqStatusEnum.ManagerPass.getId());
        }
        if ("Auditing".equals(statusId)) {
            status.add(ResDoctorKqStatusEnum.Auditing.getId());

        }
        if ("Revoke".equals(statusId)) {
            status.add(ResDoctorKqStatusEnum.Revoke.getId());

        }
        if ("UnPassed".equals(statusId)) {
            status.add(ResDoctorKqStatusEnum.TeacherUnPass.getId());
            status.add(ResDoctorKqStatusEnum.HeadUnPass.getId());
            status.add(ResDoctorKqStatusEnum.TutorUnPass.getId());
            status.add(ResDoctorKqStatusEnum.ManagerUnPass.getId());
        }

        PageHelper.startPage(pageIndex, pageSize);
        List<ResDoctorKq> list = ieaveAppBiz.leaveAndAppealList(status, kq);
        model.addAttribute("list", list);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/jswjw/leave/leaveAndAppealList";
    }

    /**
     * @Author shengl
     * @Description // 请假相关信息
     * @Date 2021-01-12
     **/
    @RequestMapping(value = {"/leaveAndAppealDict"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String leaveAndAppealDict(String userFlow, Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return successUrl;
        }
        List<SysDict> leaveTypes = ieaveAppBiz.getDictListByDictId(DictTypeEnum.LeaveType.getId());
        model.addAttribute("leaveTypes", leaveTypes);
        List<SysDict> appealTypes = ieaveAppBiz.getDictListByDictId(DictTypeEnum.AppealType.getId());
        model.addAttribute("appealTypes", appealTypes);
        List<ResDoctorSchProcess> processes = ieaveAppBiz.searchProcessByDoctor(userFlow);
        model.addAttribute("processes", processes);
        return "res/jswjw/leave/leaveAndAppealDict";
    }

    /**
     * @Author shengl
     * @Description //新增请假页面
     * @Date 2021-01-12
     **/
    @RequestMapping(value = {"/addLeaveOrAppeal"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String addLeaveOrAppeal(String userFlow, String kqTypeId, Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return successUrl;
        }
        if (StringUtil.isEmpty(kqTypeId) || !DictTypeEnum.LeaveType.getId().equals(kqTypeId)) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "类型标识符不正确,LeaveType");
            return successUrl;
        }
        ResDoctor doctor = doctorMapper.selectByPrimaryKey(userFlow);
        if (doctor == null ) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "医师信息为空");
            return successUrl;
        }
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "暂未参加基地培训");
            return successUrl;
        }
        if (DictTypeEnum.LeaveType.getId().equals(kqTypeId)) {
            List<ResKgCfg> cfgs = ieaveAppBiz.readKqCfgList(doctor.getOrgFlow(), RecDocCategoryEnum.Doctor.getId());
            model.addAttribute("cfgs", cfgs);
            if (cfgs == null || cfgs.size() <= 0) {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "请联系基地管理员维护请假审核流程");
                return successUrl;
            }

            ResKgCfg less = null;
            ResKgCfg greater = null;
            ResKgCfg midd = null;
            Integer allDays = null;
            Integer intervalDays = null;
            Integer intervalDays2 = null;
            for (ResKgCfg cfg : cfgs) {
                if ("All".equals(cfg.getLessOrGreater())) {
                    allDays = Integer.valueOf(cfg.getAllDays());
                    if (allDays > 0) {
                        // 默认一天8小时计算
                        allDays = allDays * 8;
                    }
                    intervalDays = Integer.valueOf(cfg.getIntervalDays());
                    if (intervalDays > 0) {
                        intervalDays = intervalDays * 8;
                    }
                    intervalDays2 = Integer.valueOf(cfg.getIntervalDays2());
                    if (intervalDays2 > 0) {
                        intervalDays2 = intervalDays2 * 8;
                    }
                } else if ("Less".equals(cfg.getLessOrGreater())) {
                    less = cfg;
                } else if ("Greater".equals(cfg.getLessOrGreater())) {
                    greater = cfg;
                } else if ("Midd".equals(cfg.getLessOrGreater())) {
                    midd = cfg;
                }
            }
            model.addAttribute("allDays", allDays);
            model.addAttribute("intervalDays", intervalDays);
            model.addAttribute("intervalDays2", intervalDays2);
            model.addAttribute("less", less);
            model.addAttribute("greater", greater);
            model.addAttribute("midd", midd);
            if (allDays == null || intervalDays == null || less == null || greater == null || midd == null) {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "请联系基地管理员维护请假审核流程");
                return successUrl;
            }
        }
        model.addAttribute("recordFlow", PkUtil.getUUID());
        String endDate = DateUtil.getCurrDate();
        String startDate = DateUtil.addDate(DateUtil.getCurrDate(), -6);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        String basePath = ieaveAppBiz.getCfgByCode("upload_base_dir");
        if (StringUtil.isBlank(basePath)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "请联系系统管理员维护文件保存地址！");
            return successUrl;
        }
        return "res/jswjw/leave/addLeaveOrAppeal";
    }

    /**
     * @Author shengl
     * @Description // 保存请假
     * @Date 2021-01-12
     **/
    @RequestMapping(value = {"/saveLeaveOrAppeal"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String saveLeaveOrAppeal(String userFlow, ResDoctorKq kq, Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return successUrl;
        }
        if (StringUtil.isEmpty(kq.getKqTypeId())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "kqTypeId类型标识符为空");
            return successUrl;
        }
        if (!DictTypeEnum.LeaveType.getId().equals(kq.getKqTypeId()) ) {
            model.addAttribute("resultId", "30401");
            model.addAttribute("resultType", "类型标识符不正确,LeaveType");
            return successUrl;
        }
        ResDoctor doctor = doctorMapper.selectByPrimaryKey(userFlow);
        if (doctor == null ) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "医师信息为空");
            return successUrl;
        }
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "暂未参加基地培训");
            return successUrl;
        }
        String sessionNumber = doctor.getSessionNumber();
        if (StringUtil.isBlank(sessionNumber)) {
            model.addAttribute("resultId", "30208");
            model.addAttribute("resultType", "当前学员未维护届别信息，无法请假！");
            return successUrl;
        }
        SysUser sysUser = userMapper.selectByPrimaryKey(userFlow);
        if (StringUtil.isBlank(kq.getDoctorFlow())) {
            kq.setDoctorFlow(sysUser.getUserFlow());
            kq.setDoctorName(sysUser.getUserName());
            kq.setOrgFlow(doctor.getOrgFlow());
            kq.setOrgName(doctor.getOrgName());
            kq.setSessionNumberNow(sessionNumber);
        }
        if (DictTypeEnum.LeaveType.getId().equals(kq.getKqTypeId())) {
            List<ResKgCfg> cfgs = ieaveAppBiz.readKqCfgList(doctor.getOrgFlow(), RecDocCategoryEnum.Doctor.getId());
            if (cfgs == null || cfgs.size() == 0) {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "请联系基地管理员维护请假审核流程");
                return successUrl;
            }
            ResKgCfg less = null;
            ResKgCfg greater = null;
            ResKgCfg midd = null;
            Integer allDays = null;
            Integer intervalDays = null;
            Integer intervalDays2 = null;
            for (ResKgCfg cfg : cfgs) {
                if ("All".equals(cfg.getLessOrGreater())) {
                    allDays = Integer.valueOf(cfg.getAllDays());
                    if (allDays > 0) {
                        // 默认一天8小时计算
                        allDays = allDays * 8;
                    }
                    intervalDays = Integer.valueOf(cfg.getIntervalDays());
                    if (intervalDays > 0) {
                        intervalDays = intervalDays * 8;
                    }
                    intervalDays2 = Integer.valueOf(cfg.getIntervalDays2());
                    if (intervalDays2 > 0) {
                        intervalDays2 = intervalDays2 * 8;
                    }
                } else if ("Less".equals(cfg.getLessOrGreater())) {
                    less = cfg;
                } else if ("Greater".equals(cfg.getLessOrGreater())) {
                    greater = cfg;
                } else if ("Midd".equals(cfg.getLessOrGreater())) {
                    midd = cfg;
                }
            }
            if (allDays == null || intervalDays == null || less == null || greater == null || midd == null) {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "请联系基地管理员维护请假审核流程");
                return successUrl;
            }

            if (StringUtil.isBlank(kq.getIntervalDays())) {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "请输入请假时长");
                return successUrl;
            }
            Map<String,String> daysMap = new HashMap<String, String>();
            daysMap.put("recordFlow",kq.getRecordFlow());
            daysMap.put("doctorFlow",kq.getDoctorFlow());
            daysMap.put("sessionNumber",sessionNumber);
            daysMap.put("id",DictTypeEnum.LeaveType.getId());
            // 学员请假天数
            double userHour = Double.valueOf(kq.getIntervalDays());
            double days = ieaveAppBiz.readAllIntervalDays(daysMap);
            if ((days + userHour) > allDays) {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "请假总时间数大于" + allDays + ",总时间为" + (days + userHour) + ",你已请假" + days + "本次请假" + kq.getIntervalDays());
                return successUrl;
            }
            ResDoctorSchProcess process = resDoctorProcessMapper.selectByPrimaryKey(kq.getProcessFlow());
            if (process == null) {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "请选择轮转科室");
                return successUrl;
            }
            // 判断请假时间范围
            String startDate = kq.getStartDate();
            String endDate = kq.getEndDate();
            if(endDate.compareTo(startDate) < 0 ){
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "开始时间不得大于结束时间！");
                return successUrl;
            }
            if (StringUtil.isNotBlank(endDate) && endDate.length() > 10 && startDate.length() > 10) {
                String schStartDate = process.getSchStartDate();
                String schEndDate = process.getSchEndDate();
                startDate = endDate.substring(0,10);
                endDate = endDate.substring(0,10);
                if(endDate.compareTo(schEndDate) > 0 || startDate.compareTo(schStartDate) < 0){
                    model.addAttribute("resultId", "30201");
                    model.addAttribute("resultType", "请假结束时间需在所选择科室轮转时间范围内");
                    return successUrl;
                }
            }else {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "时间参数有误");
                return successUrl;
            }
            // 判断请假时长 对应审核流程
            if(intervalDays >= userHour){
                setKqAuditInfo(less, kq, process, doctor);
            } else if(intervalDays2 < userHour){
                setKqAuditInfo(greater, kq, process, doctor);
            }else {
                setKqAuditInfo(midd, kq, process, doctor);
            }

        }
        ResDoctorKq old = ieaveAppBiz.readResDoctorKq(kq.getRecordFlow());
        if (old != null) {
            if (!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId())) {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "此请假信息已被审核，请刷新列表页");
                return successUrl;
            }
        }
        int c = ieaveAppBiz.checkTime(kq.getRecordFlow(), kq.getDoctorFlow(), kq.getStartDate(), kq.getEndDate(), kq.getKqTypeId(),sessionNumber);
        if (c > 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "在当前请假时间内已有请假信息");
            return successUrl;
        }
        if (DictTypeEnum.LeaveType.getId().equals(kq.getKqTypeId())) {
            List<SysDict> leaveTypes = ieaveAppBiz.getDictListByDictId(DictTypeEnum.LeaveType.getId());
            if (leaveTypes != null) {
                for (SysDict dict : leaveTypes) {
                    if (dict.getDictId().equals(kq.getTypeId())) {
                        kq.setTypeName(dict.getDictName());
                        break;
                    }
                }
            }
        }
        kq.setAuditStatusId(ResDoctorKqStatusEnum.Auditing.getId());
        kq.setAuditStatusName(ResDoctorKqStatusEnum.Auditing.getName());
        int n = ieaveAppBiz.editResDoctorKq(kq, sysUser, old);
        if (n == 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "保存失败");
            return successUrl;
        }
        return successUrl;
    }

    private void setKqAuditInfo(ResKgCfg greater, ResDoctorKq kq, ResDoctorSchProcess process, ResDoctor doctor) {
        if (greater != null) {

            String auditRoleNow  = "";
            kq.setTeacherFlow(process.getTeacherUserFlow());
            if ("Y".equals(greater.getTeacherFlag())) {
                // teacher 带教
                auditRoleNow  = GlobalConstant.RES_ROLE_SCOPE_TEACHER;
                kq.setTeacherName(process.getTeacherUserName());
            } else {
                kq.setTeacherName("-");
            }
            kq.setHeadFlow(process.getHeadUserFlow());
            if ("Y".equals(greater.getHeadFlag())) {
                if (StringUtil.isBlank(auditRoleNow)) {
                    auditRoleNow  = GlobalConstant.RES_ROLE_SCOPE_HEAD;
                }
                kq.setHeadName(process.getHeadUserName());
            } else {
                kq.setHeadName("-");
            }
            kq.setTutorFlow(doctor.getTutorFlow());
            if ("Y".equals(greater.getTutorFlag())) {
                kq.setTutorName(doctor.getTutorName());
            } else {
                kq.setTutorName("-");
            }
            String adminFlow = ieaveAppBiz.getCfgByCode("res_admin_role_flow");
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("wsId", "res");
            paramMap.put("roleFlow", adminFlow);
            paramMap.put("orgFlow", doctor.getOrgFlow());
            List<SysUser> sysUserList = ieaveAppBiz.searchUserWithRole(paramMap);
            SysUser admin = null;
            if (sysUserList != null && sysUserList.size() > 0) {
                admin = sysUserList.get(0);
            }
            if (admin != null) {
                kq.setManagerFlow(admin.getUserFlow());
                if ("Y".equals(greater.getManagerFlag())) {
                    if (StringUtil.isBlank(auditRoleNow)) {
                        auditRoleNow  = GlobalConstant.RES_ROLE_SCOPE_ADMIN;
                    }
                    kq.setManagerName(admin.getUserName());
                } else {
                    kq.setManagerName("-");
                }
            }
            kq.setAuditRoleNow(auditRoleNow);
            kq.setSchDeptFlow(process.getSchDeptFlow());
            kq.setSchDeptName(process.getSchDeptName());
            kq.setSchDeptStartDate(process.getSchStartDate());
            kq.setSchDeptEndDate(process.getSchEndDate());
        }
    }

    /**
     * @return java.lang.String
     * @Author shengl
     * @Description // 上传图片
     * @Date 2021-01-13
     **/
    @RequestMapping(value = {"/addImage"}, method = {RequestMethod.POST})
    public String addImage(String userFlow, String recordFlow, String imageContent, HttpServletRequest request, String fileName, MultipartFile imageContent2, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/sctcm120/addImage";
        }
        if (StringUtil.isBlank(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/sctcm120/addImage";
        }
        if (StringUtil.isBlank(imageContent)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片内容为空");
            return "res/sctcm120/addImage";
        }
        if (StringUtil.isBlank(fileName)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片名称为空");
            return "res/sctcm120/addImage";
        }
        String baseDir = ieaveAppBiz.getCfgByCode("upload_base_dir");
        if (StringUtil.isBlank(baseDir)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请联系管理员，设置上传图片路径！");
            return "res/sctcm120/addImage";
        }

        //定义上传路径
        String dateString = DateUtil.getCurrDate2();
        String newDir = baseDir + File.separator + "ResDoctorKqFile" + File.separator + dateString + File.separator + recordFlow;
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //重命名上传后的文件名
        String originalFilename = "";
        originalFilename = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
        try {
            generateImage(imageContent, fileDir + File.separator + originalFilename);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存文件失败！");
        }
        String filePath = File.separator + "ResDoctorKqFile" + File.separator + dateString + File.separator + recordFlow + File.separator + originalFilename;

        PubFile pubFile = new PubFile();
        pubFile.setFileFlow(PkUtil.getUUID());
        pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        pubFile.setFilePath(filePath);
        pubFile.setFileName(fileName);
        pubFile.setFileSuffix(fileName.substring(fileName.lastIndexOf(".")));
        pubFile.setProductType("ResDoctorKqFile");
        pubFile.setProductFlow(recordFlow);
        fileBiz.addFile(pubFile);
        model.addAttribute("pubFile", pubFile);
        String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        return "res/sctcm120/addImage";
    }

    //base64字符串转化成图片
    public static boolean generateImage(String imgStr, String savePath) {
        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
        {
            return false;
        }

        String newDir = savePath.substring(0, savePath.lastIndexOf(File.separator));
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
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

    /**
     * @Author shengl
     * @Description // 查看请假详情
     * @Date 2021-01-13
     **/
    @RequestMapping(value = {"/showLeaveOrAppeal"}, method = {RequestMethod.POST})
    public String showLeaveOrAppeal(String userFlow, String recordFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/sctcm120/student/showLeaveOrAppeal";
        }
        if (StringUtil.isBlank(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/sctcm120/student/showLeaveOrAppeal";
        }

        ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if (kq == null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为错误");
            return "res/sctcm120/student/showLeaveOrAppeal";
        }
        model.addAttribute("kq", kq);
        List<PubFile> files = fileBiz.findFileByTypeFlow("ResDoctorKqFile", recordFlow);
        model.addAttribute("files", files);

        //获取访问路径前缀
        String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);

        return "res/sctcm120/student/showLeaveOrAppeal";
    }

    /**
     * @Author shengl
     * @Description // 撤销请假
     * @Date 2021-01-15
     **/
    @RequestMapping(value = {"/revokeLeaveOrAppeal"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String revokeLeaveOrAppeal(String userFlow, ResDoctorKq kq, Model model) throws DocumentException {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "用户标识符为空");
            return successUrl;
        }
        if (StringUtil.isEmpty(kq.getRecordFlow())) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "数据标识符为空");
            return successUrl;
        }
        ResDoctorKq old = ieaveAppBiz.readResDoctorKq(kq.getRecordFlow());
//        ResDoctorKq old=sctcm120StudentBiz.readResDoctorKq(kq.getRecordFlow());
        if (old != null) {
            if (!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId())) {
                model.addAttribute("resultId", "30201");
                model.addAttribute("resultType", "此信息已被审核，请刷新列表页");
                return successUrl;
            }
        } else {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "数据标识符错误");
            return successUrl;
        }
        SysUser sysUser = userMapper.selectByPrimaryKey(userFlow);
        old.setAuditStatusId(ResDoctorKqStatusEnum.Revoke.getId());
        old.setAuditStatusName(ResDoctorKqStatusEnum.Revoke.getName());
        old.setModifyTime(DateUtil.getCurrDateTime());
        old.setModifyUserFlow(sysUser.getUserFlow());
        int count = ieaveAppBiz.updateResDoctorKq(old);
        /*int n=sctcm120StudentBiz.editResDoctorKq(kq,sysUser,kq);*/
        if (count == 0) {
            model.addAttribute("resultId", "30201");
            model.addAttribute("resultType", "撤销失败");
            return successUrl;
        }
        return successUrl;
    }

    /**
     * @Author shengl
     * @Description // 删除附件
     **/
    @RequestMapping(value = {"/delImage"}, method = {RequestMethod.POST})
    public String delImage(String userFlow, String fileFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "删除成功");

        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return successUrl;
        }
        if (StringUtil.isBlank(fileFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片标识符为空");
            return successUrl;
        }
        PubFile pubFile = fileBiz.readFile(fileFlow);
        if (pubFile == null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片信息不存在为空");
            return successUrl;
        }
        pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        fileBiz.editFile(pubFile);
        return successUrl;
    }

    @RequestMapping(value = {"/delLeaveOrAppeal"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String delLeaveOrAppeal(Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "暂无删除功能");
        return successUrl;
    }

    @RequestMapping(value = "/doctorleaveVerifyMain",method = {RequestMethod.POST})
    public String doctorleaveVerifyMain(String userFlow, Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/leave/doctorleaveVerifyMain";
        }
        //学员轮转科室
        List<ResDoctorSchProcess> deptList = new ArrayList<>();
        ResDoctorSchProcess process = new ResDoctorSchProcess();
        process.setProcessFlow("");
        process.setSchStartDate("");
        process.setSchEndDate("");
        process.setSchDeptFlow("");
        process.setSchDeptName("全部");
        deptList.add(process);
        List<ResDoctorSchProcess> deptList2 = ieaveAppBiz.searchProcessByDoctorFlow(userFlow);
        if(null != deptList2 && deptList2.size()>0){
            deptList.addAll(deptList2);
        }
        model.addAttribute("deptList",deptList);
        //是否可以新增请假
        String isAddLeave = "N";
        if(null != deptList && deptList.size() > 0){
            isAddLeave = "Y";
        }
        model.addAttribute("isAddLeave",isAddLeave);
        //请假类型
        List<Map<String,Object>> typeList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("typeId","");
        map.put("typeName","全部");
        typeList.add(map);
        List<Map<String,Object>> typeList2 = ieaveAppBiz.searchDictList(DictTypeEnum.LeaveType.getId());
        if(null != typeList2 && typeList2.size()>0){
            typeList.addAll(typeList2);
        }
        model.addAttribute("typeList",typeList);
        return "res/jswjw/leave/doctorleaveVerifyMain";
    }

    /**
     * @Description //学员请假列表
     **/
    @RequestMapping(value = "/doctorleaveVerifyList",method = {RequestMethod.POST})
    public String doctorleaveVerifyList(ResDoctorKq kq, String userFlow,Integer pageIndex, Integer pageSize, Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/jswjw/leave/doctorleaveVerifyList";
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if(null == user){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户不存在");
            return "res/jswjw/leave/doctorleaveVerifyList";
        }
//        if(StringUtil.isBlank(kq.getOrgFlow())){
//            return ResultDataThrow("基地标识符为空");
//        }
        if (pageIndex == null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "当前页码为空");
            return "res/jswjw/leave/doctorleaveVerifyList";
        }
        if (pageSize == null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "每页条数为空");
            return "res/jswjw/leave/doctorleaveVerifyList";
        }
        kq.setDoctorFlow(userFlow);
        kq.setOrgFlow(user.getOrgFlow());
        kq.setKqTypeId(DictTypeEnum.LeaveType.getId());
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,String>> kqList = ieaveAppBiz.searchDoctorKqList(kq);
        if(null != kqList && kqList.size()>0){
            for (Map<String,String> m:kqList) {
                String operId = "";
                String operName = "";
                String auditStatusId = m.get("auditStatusId");
                if(!("ManagerPass".equals(auditStatusId)) && !("ManagerUnPass".equals(auditStatusId)) &&
                        !("RevokeManagerPass".equals(auditStatusId)) && !("BackLeave".equals(auditStatusId))
                ){
                    operId = "backLeave";
                    operName = "撤回";
                }
                if("ManagerPass".equals(auditStatusId)){
                    operId = "cancelLeave";
                    operName = "销假";
                }
                m.put("operId",operId);
                m.put("operName",operName);
            }
        }
        model.addAttribute("kqList",kqList);
        model.addAttribute("dataCount", PageHelper.total);
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        model.addAttribute("headImage",StringUtil.isBlank(user.getUserHeadImg()) ? "" : uploadBaseUrl + "/" +user.getUserHeadImg());
        return "res/jswjw/leave/doctorleaveVerifyList";
    }

    /**
     * @Description //查看附件
     **/
    @RequestMapping(value = "/showFilws",method = {RequestMethod.POST})
    public String showFilws(String recordFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/jswjw/leave/showFilws";
        }
        List<PubFile> files=fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
        model.addAttribute("files",files);
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        return "res/jswjw/leave/showFilws";
    }

    /**
     * @Description //学员请假新增
     **/
    @RequestMapping(value = "/addLeave",method = {RequestMethod.POST})
    public String editLeave(String recordFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(recordFlow)){
            model.addAttribute("recordFlow", PkUtil.getUUID());
        }
        String nowDate = DateUtil.getCurrDate();
        model.addAttribute("nowDate",nowDate);
        return "res/jswjw/leave/addLeave";
    }

    /**
     * @Description //计算请假天数（只计算工作日）
     **/
    @RequestMapping(value="/intervalDays",method = {RequestMethod.POST})
    public String intervalDays(String startDate,String endDate,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(startDate)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "开始时间为空");
            return successUrl;
        }
        if (StringUtil.isEmpty(endDate)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "结束时间为空");
            return successUrl;
        }
        int result = 0;
        //两日期相差天数
        int days = (int)DateUtil.signDaysBetweenTowDate(endDate,startDate);
        if(days == 0){
            //判断是否为周末
            String weekOfDay = DateUtil.getWeekFromDate(startDate,"3");
            if(!(weekOfDay.equals("星期六")) && !(weekOfDay.equals("星期日"))){
                result = 1;
            }
        }else{
            for (int i = 0; i <= days; i++) {
                String date = DateUtil.addDate(startDate,i);
                String weekOfDay = DateUtil.getWeekFromDate(date,"3");
                if(!(weekOfDay.equals("星期六")) && !(weekOfDay.equals("星期日"))){
                    result += 1;
                }
            }
        }
        if(0 == result){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请假时间为周末，不需提交申请！");
            return successUrl;
        }
        model.addAttribute("intervalDays",String.valueOf(result));
        return successUrl;
    }

    @RequestMapping(value = {"/addLeaveImage"}, method = {RequestMethod.POST})
    public String addLeaveImage(ImageFileForm form, HttpServletRequest request, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(form.getUserFlow())) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空");
            return "res/sctcm120/addImage";
        }
        if (StringUtil.isBlank(form.getRecordFlow())) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空");
            return "res/sctcm120/addImage";
        }
        if (StringUtil.isBlank(form.getImageContent())) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片内容为空");
            return "res/sctcm120/addImage";
        }
        if (StringUtil.isBlank(form.getFileName())) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "图片名称为空");
            return "res/sctcm120/addImage";
        }
        String baseDir = ieaveAppBiz.getCfgByCode("upload_base_dir");
        if (StringUtil.isBlank(baseDir)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请联系管理员，设置上传图片路径！");
            return "res/sctcm120/addImage";
        }

        //定义上传路径
        String dateString = DateUtil.getCurrDate2();
        String newDir = baseDir + File.separator + "ResDoctorKqFile" + File.separator + dateString + File.separator + form.getRecordFlow();
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        //重命名上传后的文件名
        String originalFilename = "";
        originalFilename = PkUtil.getUUID() + form.getFileName().substring(form.getFileName().lastIndexOf("."));
        try {
            generateImage(form.getImageContent(), fileDir + File.separator + originalFilename);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("保存文件失败！");
        }
        String filePath = File.separator + "ResDoctorKqFile" + File.separator + dateString + File.separator + form.getRecordFlow() + File.separator + originalFilename;

        PubFile pubFile = new PubFile();
        pubFile.setFileFlow(PkUtil.getUUID());
        pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        pubFile.setFilePath(filePath);
        pubFile.setFileName(form.getFileName());
        pubFile.setFileSuffix(form.getFileName().substring(form.getFileName().lastIndexOf(".")));
        pubFile.setProductType("ResDoctorKqFile");
        pubFile.setProductFlow(form.getRecordFlow());
        fileBiz.addFile(pubFile);
        model.addAttribute("pubFile", pubFile);
        String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);
        return "res/sctcm120/addImage";
    }

    @RequestMapping(value = {"/deleteImage"}, method = {RequestMethod.POST})
    public String deleteImage(String fileFlow,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "删除成功");
        if (StringUtil.isEmpty(fileFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "附件标识符为空！");
            return successUrl;
        }
        jswjwBiz.deleteLeaveImage(fileFlow);
        return successUrl;
    }

    /**
     * @Description //保存新增请假申请
     **/
    @RequestMapping(value = "/saveAddLeave",method = {RequestMethod.POST})
    public String saveAddLeave(ResDoctorKqExt kqExt, HttpServletRequest request,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "保存成功");
        if(null == kqExt){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "参数格式错误！");
            return successUrl;
        }
        if(StringUtil.isBlank(kqExt.getUserFlow())){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空！");
            return successUrl;
        }
        ResDoctor doctor = jswjwBiz.readDoctor(kqExt.getUserFlow());
        if(doctor==null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "医师信息不存在！");
            return successUrl;
        }
        if(StringUtil.isBlank(doctor.getOrgFlow())) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "未进入基地参加培训！");
            return successUrl;
        }
        SysUser user = jswjwBiz.readSysUser(kqExt.getUserFlow());
        if(null == user){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户不存在！");
            return successUrl;
        }
        if(StringUtil.isBlank(kqExt.getDoctorFlow())){
            kqExt.setDoctorFlow(user.getUserFlow());
            kqExt.setDoctorName(user.getUserName());
        }
        kqExt.setSessionNumberNow(doctor.getSessionNumber());
        kqExt.setOrgFlow(doctor.getOrgFlow());
        kqExt.setOrgName(doctor.getOrgName());
        List<ResKgCfg> cfgs = ieaveAppBiz.readKqCfgList(doctor.getOrgFlow(),doctor.getDoctorCategoryId());
        if(cfgs==null||cfgs.size()==0) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请联系基地管理员维护请假审核流程！");
            return successUrl;
        }
        ResKgCfg less=null;
        ResKgCfg greater=null;
        ResKgCfg midd=null;
        Integer allDays=null;
        Integer intervalDays=null;
        Integer intervalDays2=null;
        for(ResKgCfg cfg:cfgs) {
            if("All".equals(cfg.getLessOrGreater())) {
                allDays=Integer.valueOf(cfg.getAllDays());
                intervalDays=Integer.valueOf(cfg.getIntervalDays());
                intervalDays2=Integer.valueOf(cfg.getIntervalDays2());
            }
            if("Less".equals(cfg.getLessOrGreater()))
            {
                less=cfg;
            }
            if("Greater".equals(cfg.getLessOrGreater()))
            {
                greater=cfg;
            }
            if("Midd".equals(cfg.getLessOrGreater()))
            {
                midd=cfg;
            }
        }
        if(allDays==null||intervalDays==null||intervalDays2==null||less==null||greater==null||midd==null)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请联系基地管理员维护请假审核流程！");
            return successUrl;
        }
        ResDoctorKq old = ieaveAppBiz.readResDoctorKq(kqExt.getRecordFlow());
        if(old!=null) {
            if(!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此请假信息已被审核，请刷新列表页！");
                return successUrl;
            }
        }
        int c = ieaveAppBiz.checkLeaveTime(kqExt.getRecordFlow(),kqExt.getDoctorFlow(),kqExt.getStartDate(),kqExt.getEndDate(),DictTypeEnum.LeaveType.getId());
        if(c>0) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "在当前请假时间内已有请假信息！");
            return successUrl;
        }
        if(StringUtil.isBlank(kqExt.getIntervalDays())) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请输入请假天数！");
            return successUrl;
        }

        ResDoctorSchProcess process = jswjwBiz.readSchProcess(kqExt.getProcessFlow());
        if(process==null)
        {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请选择轮转科室！");
            return successUrl;
        }
        if(intervalDays2<Double.valueOf(kqExt.getIntervalDays()))
        {
            setKqAuditInfo(greater,kqExt,process,doctor);
        }else if(intervalDays2>=Double.valueOf(kqExt.getIntervalDays())&&intervalDays<Double.valueOf(kqExt.getIntervalDays())){
            setKqAuditInfo(midd,kqExt,process,doctor);
        }else{
            setKqAuditInfo(less,kqExt,process,doctor);
        }

        kqExt.setKqTypeId(DictTypeEnum.LeaveType.getId());
        kqExt.setKqTypeName(DictTypeEnum.LeaveType.getName());
        kqExt.setTypeName(DictTypeEnum.getDictName(DictTypeEnum.LeaveType, kqExt.getTypeId()));
        kqExt.setAuditStatusId(ResDoctorKqStatusEnum.Auditing.getId());
        kqExt.setAuditStatusName(ResDoctorKqStatusEnum.Auditing.getName());
        int n = ieaveAppBiz.addResDoctorKq(kqExt,user);
        if(n==0) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "保存失败！");
            return successUrl;
        }
        return successUrl;
    }

    /**
     * @Description //查看审核进度
     **/
    @RequestMapping(value = "/searchAudit",method = {RequestMethod.POST})
    public String searchAudit(String recordFlow,String roleFlag, Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isEmpty(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请假标识符为空！");
            return "res/jswjw/leave/searchAudit";
        }

        ResDoctorKq resDoctorKq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if(null == resDoctorKq){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请假记录不存在！");
            return "res/jswjw/leave/searchAudit";
        }
        model.addAttribute("kq",resDoctorKq);

        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        model.addAttribute("uploadBaseUrl", uploadBaseUrl);

        List<PubFile> files = fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
        model.addAttribute("files",files);

        String teacherName = resDoctorKq.getTeacherName();
        String headName = resDoctorKq.getHeadName();
        String manageName = resDoctorKq.getManagerName();

        List<Map<String,Object>> leaveMapList = new ArrayList<>();
        Map<String,Object> logMap = null;
        if(null != resDoctorKq && resDoctorKq.getAuditStatusId().equals("BackLeave")){
            logMap = new HashMap<>();
            logMap.put("roleId","doctor");
            logMap.put("roleName","学员");
            logMap.put("userName","Student".equals(roleFlag) ? "我" : resDoctorKq.getDoctorName());
            logMap.put("auditName","已撤销");
            logMap.put("auditInfo", "");
            logMap.put("auditTime",resDoctorKq.getModifyTime());
            leaveMapList.add(logMap);
        }
        if(null != resDoctorKq && !resDoctorKq.getAuditStatusId().equals("BackLeave")){
            logMap = new HashMap<>();
            logMap.put("roleId","doctor");
            logMap.put("roleName","学员");
            logMap.put("userName","Student".equals(roleFlag) ? "我" : resDoctorKq.getDoctorName());
            logMap.put("auditName","发起请假申请");
            logMap.put("auditInfo", "");
            logMap.put("auditTime",resDoctorKq.getCreateTime());
            leaveMapList.add(logMap);
        }
        if(!"-".equals(teacherName) && !resDoctorKq.getAuditStatusId().equals("BackLeave")){
            logMap = new HashMap<>();
            logMap.put("roleId","teacher");
            logMap.put("roleName","带教");
            logMap.put("userName","Teacher".equals(roleFlag) ? "我" : resDoctorKq.getTeacherName());
            ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Leave","teacher");
            logMap.put("auditName",null == log ? "待审核" : log.getAuditStatusName());
            logMap.put("auditInfo",null == log ? "" : log.getAuditRemake());
            logMap.put("auditTime",null == log ? "" : log.getCreateTime());
            leaveMapList.add(logMap);
        }
        if(!"-".equals(headName) && !resDoctorKq.getAuditStatusId().equals("BackLeave")){
            logMap = new HashMap<>();
            logMap.put("roleId","head");
            logMap.put("roleName","科主任");
            logMap.put("userName","Head".equals(roleFlag) ? "我" : resDoctorKq.getHeadName());
            ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Leave","head");
            logMap.put("auditName",null == log ? "待审核" : log.getAuditStatusName());
            logMap.put("auditInfo",null == log ? "" : log.getAuditRemake());
            logMap.put("auditTime",null == log ? "" : log.getCreateTime());
            leaveMapList.add(logMap);
        }
        if(!"-".equals(manageName) && !resDoctorKq.getAuditStatusId().equals("BackLeave")){
            logMap = new HashMap<>();
            logMap.put("roleId","manager");
            logMap.put("roleName","管理员");
            logMap.put("userName","Admin".equals(roleFlag) ? "我" : resDoctorKq.getManagerName());
            ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Leave","manager");
            logMap.put("auditName",null == log ? "待审核" : log.getAuditStatusName());
            logMap.put("auditInfo",null == log ? "" : log.getAuditRemake());
            logMap.put("auditTime",null == log ? "" : log.getCreateTime());
            leaveMapList.add(logMap);
        }

        List<ResDoctorKqLog> logList = ieaveAppBiz.searchKqLogList(recordFlow,"Cancel");
        if(null != logList && logList.size()>0){
            logMap = new HashMap<>();
            logMap.put("roleId","doctor");
            logMap.put("roleName","学员");
            logMap.put("userName","Student".equals(roleFlag) ? "我" : resDoctorKq.getDoctorName());
            logMap.put("auditName","发起销假申请");
            logMap.put("auditInfo", "");
            logMap.put("auditTime",resDoctorKq.getModifyTime());
            leaveMapList.add(logMap);
            if(!"-".equals(teacherName)) {
                logMap = new HashMap<>();
                ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Cancel","teacher");
                logMap.put("roleId", "teacher");
                logMap.put("roleName", "带教");
                logMap.put("userName", "Teacher".equals(roleFlag) ? "我" : teacherName);
                logMap.put("auditName", null == log ? "待审核" : log.getAuditStatusName());
                logMap.put("auditInfo", null == log ? "" :log.getAuditRemake());
                logMap.put("auditTime", null == log ? "" :log.getCreateTime());
                leaveMapList.add(logMap);
            }
            if(!"-".equals(headName)) {
                logMap = new HashMap<>();
                ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Cancel","head");
                logMap.put("roleId", "head");
                logMap.put("roleName", "科主任");
                logMap.put("userName", "Head".equals(roleFlag) ? "我" : headName);
                logMap.put("auditName", null == log ? "待审核" : log.getAuditStatusName());
                logMap.put("auditInfo", null == log ? "" : log.getAuditRemake());
                logMap.put("auditTime", null == log ? "" : log.getCreateTime());
                leaveMapList.add(logMap);
            }
            if(!"-".equals(manageName)) {
                logMap = new HashMap<>();
                ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Cancel","manager");
                logMap.put("roleId", "manager");
                logMap.put("roleName", "管理员");
                logMap.put("userName", "Admin".equals(roleFlag) ? "我" : manageName);
                logMap.put("auditName", null == log ? "待审核" : log.getAuditStatusName());
                logMap.put("auditInfo", null == log ? "" : log.getAuditRemake());
                logMap.put("auditTime", null == log ? "" : log.getCreateTime());
                leaveMapList.add(logMap);
            }
        }

        if(null != resDoctorKq && !resDoctorKq.getAuditStatusId().equals("BackLeave") && resDoctorKq.getAuditStatusId().equals("RevokeAuditing")){
            logMap = new HashMap<>();
            logMap.put("roleId","doctor");
            logMap.put("roleName","学员");
            logMap.put("userName","Student".equals(roleFlag) ? "我" : resDoctorKq.getDoctorName());
            logMap.put("auditName","发起销假申请");
            logMap.put("auditInfo", "");
            logMap.put("auditTime",resDoctorKq.getModifyTime());
            leaveMapList.add(logMap);
        }
        if(!"-".equals(teacherName) && !resDoctorKq.getAuditStatusId().equals("BackLeave") && resDoctorKq.getAuditStatusId().equals("RevokeAuditing")){
            logMap = new HashMap<>();
            logMap.put("roleId","teacher");
            logMap.put("roleName","带教");
            logMap.put("userName","Teacher".equals(roleFlag) ? "我" : resDoctorKq.getTeacherName());
//            ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Cancel","teacher");
            logMap.put("auditName","待审核");
            logMap.put("auditInfo","");
            logMap.put("auditTime","");
            leaveMapList.add(logMap);
        }
        if(!"-".equals(headName) && !resDoctorKq.getAuditStatusId().equals("BackLeave") && resDoctorKq.getAuditStatusId().equals("RevokeAuditing")){
            logMap = new HashMap<>();
            logMap.put("roleId","head");
            logMap.put("roleName","科主任");
            logMap.put("userName","Head".equals(roleFlag) ? "我" : resDoctorKq.getHeadName());
//            ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Cancel","head");
            logMap.put("auditName","待审核");
            logMap.put("auditInfo","" );
            logMap.put("auditTime","" );
            leaveMapList.add(logMap);
        }
        if(!"-".equals(manageName) && !resDoctorKq.getAuditStatusId().equals("BackLeave") && resDoctorKq.getAuditStatusId().equals("RevokeAuditing")){
            logMap = new HashMap<>();
            logMap.put("roleId","manager");
            logMap.put("roleName","管理员");
            logMap.put("userName","Admin".equals(roleFlag) ? "我" : resDoctorKq.getManagerName());
//            ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Cancel","manager");
            logMap.put("auditName","待审核" );
            logMap.put("auditInfo", "" );
            logMap.put("auditTime", "" );
            leaveMapList.add(logMap);
        }

        model.addAttribute("leaveMapList",leaveMapList);
        return "res/jswjw/leave/searchAudit";
    }

    /**
     * @Description //销假申请
     **/
    @RequestMapping("/cancelLeave")
    public String cancelLeave(String recordFlow,String userFlow, Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空！");
            return successUrl;
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if(null == user){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户不存在！");
            return successUrl;
        }
        if (StringUtil.isBlank(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请假标识符为空！");
            return successUrl;
        }

        ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if (ResDoctorKqStatusEnum.ManagerUnPass.getId().equals(kq.getAuditStatusId())) {
            return "该条申请审核未通过，无法操作！";
        }
        if (!ResDoctorKqStatusEnum.ManagerPass.getId().equals(kq.getAuditStatusId())) {
            return "该条申请审核进行中，无法操作！";
        }
        kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeAuditing.getId());
        kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeAuditing.getName());
        kq.setTeacherAgreeFlag("");
        kq.setTeacherAuditTime("");
        kq.setTeacherAuditInfo("");
        kq.setHeadAgreeFlag("");
        kq.setHeadAuditTime("");
        kq.setHeadAuditInfo("");
        kq.setManagerAgreeFlag("");
        kq.setManagerAuditTime("");
        kq.setManagerAuditInfo("");
        kq.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        kq.setModifyTime(DateUtil.getCurrDateTime());
        kq.setModifyUserFlow(user.getUserFlow());
        ieaveAppBiz.updateResDoctorKq(kq);
        return successUrl;
    }

    /**
     * @Description // 销假审核
     **/
    @RequestMapping(value="/saveCancelLeaveInfo")
    public String saveCancelLeaveInfo(String recordFlow,String auditInfo,String status,String roleFlag,String userFlow, Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空！");
            return successUrl;
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (null == user) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户不存在！");
            return successUrl;
        }
        if (StringUtil.isBlank(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空！");
            return successUrl;
        }
        if (StringUtil.isBlank(status)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "状态标识符为空！");
            return successUrl;
        }
        if (StringUtil.isBlank(roleFlag)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "角色标识符为空！");
            return successUrl;
        }

        boolean isTeacher = "Teacher".equals(roleFlag);
        boolean isHead = "Head".equals(roleFlag);
        boolean isManager = "Admin".equals(roleFlag);

        ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if (kq == null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符错误！");
            return successUrl;
        }
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf0.format(new Date());
        if (isTeacher) {
            if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此销假信息已审核，请刷新列表页");
                return successUrl;
            }
            if ("Y".equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadAuditing.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadAuditing.getName());
                if ("-".equals(kq.getHeadName())) {
                    if ("-".equals(kq.getManagerName())) {
                        //不需要科主任及管理员审核，带教审核结果为最终审核结果
                        kq.setAuditRoleNow("Pass");
                        kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                        kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                    }else {
                        //待科主任审核
                        kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                    }
                }else {
                    kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_HEAD);
                    // 判断带教和科主任是否同一人审核，如果审批通过依次通过
                    String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
                    if (teacherFlow.equals(kq.getHeadFlow())) {
                        kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerAuditing.getId());
                        kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerAuditing.getName());
                        if ("-".equals(kq.getManagerName())) {
                            kq.setAuditRoleNow("Pass");
                            kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                            kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                        } else {
                            kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                        }
                        kq.setHeadAuditTime(time);
                        kq.setHeadAgreeFlag(status);
                    }
                }
            } else {
                kq.setAuditRoleNow("Pass");
            }
            kq.setTeacherAuditTime(time);
            kq.setTeacherAgreeFlag(status);
            kq.setTeacherAuditInfo(auditInfo);

        } else if (isHead) {
            if (StringUtil.isNotBlank(kq.getHeadAgreeFlag())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此销假信息已审核，请刷新列表页");
                return successUrl;
            }
            if ("Y".equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerAuditing.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerAuditing.getName());
                if ("-".equals(kq.getManagerName())) {
                    kq.setAuditRoleNow("Pass");
                    kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                    kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                }else {
                    kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                }
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setHeadAuditTime(time);
            kq.setHeadAgreeFlag(status);
            kq.setHeadAuditInfo(auditInfo);

        }else if (isManager) {
            if (StringUtil.isNotBlank(kq.getManagerAgreeFlag())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此销假信息已审核，请刷新列表页");
                return successUrl;
            }
            if ("Y".equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                kq.setAuditRoleNow("Pass");
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setManagerAuditTime(time);
            kq.setManagerAgreeFlag(status);
            kq.setManagerAuditInfo(auditInfo);

        } else {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "该数据无法审核");
            return successUrl;
        }
        if(!"Y".equals(status)) {
            // 销假审核不通过，状态变更为请假审核通过
            kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
            kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
            // 根据考勤主键与审核类型查询
            List<ResDoctorKqLog> kqLogs = ieaveAppBiz.searchKqLogList(recordFlow, "Leave");
            if (null != kqLogs && kqLogs.size() > 0) {
                for (ResDoctorKqLog log : kqLogs) {
                    if (log.getRoleId().equals("manager")) {
                        kq.setManagerAuditTime(log.getAuditTime());
                        kq.setManagerAuditInfo(log.getAuditRemake());
                    }
                    if (log.getRoleId().equals("head")) {
                        kq.setHeadAuditTime(log.getAuditTime());
                        kq.setHeadAuditInfo(log.getAuditRemake());
                    }
                    if (log.getRoleId().equals("teacher")) {
                        kq.setTeacherAuditTime(log.getAuditTime());
                        kq.setTeacherAuditInfo(log.getAuditRemake());
                    }
                }
            }
        }
        kq.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        kq.setModifyTime(DateUtil.getCurrDateTime());
        kq.setModifyUserFlow(user.getUserFlow());
        int count = ieaveAppBiz.updateResDoctorKq(kq);
        if(count != 0) {
            //保存审批记录
            ResDoctorKqLog kqLog = new ResDoctorKqLog();
            kqLog.setKqRecordFlow(recordFlow);

            if(isTeacher){
                kqLog.setRoleId("teacher");
                kqLog.setRoleName("带教");
                kqLog.setAuditUserFlow(kq.getTeacherFlow());
                kqLog.setAuditUserName(kq.getTeacherName());
                if(kq.getTeacherAgreeFlag().equals(GlobalConstant.FLAG_Y)){
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeTeacherPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeTeacherPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                }else{
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeTeacherUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeTeacherUnPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Cancel");
                kqLog.setTypeName("销假申请");
                ieaveAppBiz.saveKqLog(kqLog,user);
                // 判断带教和科主任是否同一人审核，如果审批通过依次通过，记录审批
                String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
                if (teacherFlow.equals(kq.getHeadFlow())) {
                    kqLog.setRecordFlow(null);
                    kqLog.setRoleId("teacher");
                    kqLog.setRoleName("带教");
                    kqLog.setAuditUserFlow(kq.getTeacherFlow());
                    kqLog.setAuditUserName(kq.getTeacherName());
                    if(kq.getTeacherAgreeFlag().equals(GlobalConstant.FLAG_Y)){
                        kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadPass.getId());
                        kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadPass.getName());
                        kqLog.setAuditRemake(auditInfo);
                    }else{
                        kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadUnPass.getId());
                        kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadUnPass.getName());
                        kqLog.setAuditRemake(auditInfo);
                    }
                    ieaveAppBiz.saveKqLog(kqLog,user);
                }
            }

            if(isHead) {
                kqLog.setRoleId("head");
                kqLog.setRoleName("科主任");
                kqLog.setAuditUserFlow(kq.getHeadFlow());
                kqLog.setAuditUserName(kq.getHeadName());
                if (kq.getHeadAgreeFlag().equals(GlobalConstant.FLAG_Y)) {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                } else {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeHeadUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeHeadUnPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Cancel");
                kqLog.setTypeName("销假申请");
                ieaveAppBiz.saveKqLog(kqLog,user);
            }

            if(isManager) {
                kqLog.setRoleId("manager");
                kqLog.setRoleName("管理员");
                kqLog.setAuditUserFlow(kq.getManagerFlow());
                kqLog.setAuditUserName(kq.getManagerName());
                if (kq.getManagerAgreeFlag().equals(GlobalConstant.FLAG_Y)) {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                } else {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.RevokeManagerUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.RevokeManagerUnPass.getName());
                    kqLog.setAuditRemake(auditInfo);
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Cancel");
                kqLog.setTypeName("销假申请");
                ieaveAppBiz.saveKqLog(kqLog,user);
            }
        }
        return successUrl;
    }

    /**
     * @Description //撤回请假申请、销假申请
     **/
    @RequestMapping(value = "/backLeave",method = {RequestMethod.POST})
    public String backLeave(String recordFlow,String auditStatusId,String userFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "操作成功");
        if (StringUtil.isEmpty(recordFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "请假标识符为空！");
            return successUrl;
        }
        if (StringUtil.isEmpty(auditStatusId)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "状态标识符为空！");
            return successUrl;
        }
        if (StringUtil.isEmpty(userFlow)) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空！");
            return successUrl;
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if(null == user){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户不存在！");
            return successUrl;
        }
        ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if (ResDoctorKqStatusEnum.ManagerPass.getId().equals(kq.getAuditStatusId()) ||
                ResDoctorKqStatusEnum.RevokeManagerPass.getId().equals(kq.getAuditStatusId())) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "该条申请记录已审核，无法操作！");
            return successUrl;
        }
        if(!auditStatusId.startsWith("Revoke")){
            // 请假申请撤回,请假状态变更已撤销
            kq.setAuditStatusId(ResDoctorKqStatusEnum.BackLeave.getId());
            kq.setAuditStatusName(ResDoctorKqStatusEnum.BackLeave.getName());
        }
        if(auditStatusId.startsWith("Revoke")){
            // 销假申请撤回，状态变更为请假审核通过
            kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
            kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
            // 根据考勤主键与审核类型查询
            List<ResDoctorKqLog> kqLogs = ieaveAppBiz.searchKqLogList(recordFlow,"Leave");
            if(null != kqLogs && kqLogs.size()>0){
                for (ResDoctorKqLog log:kqLogs) {
                    if(log.getRoleId().equals("manager")){
                        kq.setManagerAuditTime(log.getAuditTime());
                        kq.setManagerAuditInfo(log.getAuditRemake());
                        kq.setManagerAgreeFlag(GlobalConstant.FLAG_Y);
                    }
                    if(log.getRoleId().equals("head")){
                        kq.setHeadAuditTime(log.getAuditTime());
                        kq.setHeadAuditInfo(log.getAuditRemake());
                        kq.setHeadAgreeFlag(GlobalConstant.FLAG_Y);
                    }
                    if(log.getRoleId().equals("teacher")){
                        kq.setTeacherAuditTime(log.getAuditTime());
                        kq.setTeacherAuditInfo(log.getAuditRemake());
                        kq.setTeacherAgreeFlag(GlobalConstant.FLAG_Y);
                    }
                }
            }
            //撤回销假，删除销假审批记录
            ieaveAppBiz.updateKqLogsRecordStatusN(recordFlow,"Cancel");
        }
        ieaveAppBiz.editResDoctorKqNew(kq,user);
        return successUrl;
    }

    /**
     * @Description // 请假审批列表
     **/
    @RequestMapping(value={"/leaveVerifyList"},method={RequestMethod.POST})
    public String leaveVerifyList(String roleFlag, ResDoctorKq kq, Integer pageIndex, Integer pageSize, String userFlow,String agreeFlag,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "角色标识符为空！");
            return "res/jswjw/leave/leaveVerifyList";
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空！");
            return "res/jswjw/leave/leaveVerifyList";
        }
        SysUser currUser = jswjwBiz.readSysUser(userFlow);
        if(null == currUser){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户不存在！");
            return "res/jswjw/leave/leaveVerifyList";
        }
        if(pageIndex==null){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "起始页为空！");
            return "res/jswjw/leave/leaveVerifyList";
        }
        if(pageSize==null){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "页面大小为空！");
            return "res/jswjw/leave/leaveVerifyList";
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("doctorName",kq.getDoctorName());
        paramMap.put("typeId",kq.getTypeId());
        paramMap.put("startDate",kq.getStartDate());
        paramMap.put("endDate",kq.getEndDate());
        paramMap.put("roleFlag",roleFlag);
        paramMap.put("agreeFlag",agreeFlag);//已审核的记录
//        String auditStatusId = kq.getAuditStatusId();
//        paramMap.put("auditStatusId",auditStatusId);
        String auditStatusId = kq.getAuditStatusId();
        if(StringUtil.isNotBlank(auditStatusId)) {
            List<String> auditStatusIds = Arrays.asList(auditStatusId.split(","));
            List<String> auditStatusIdList = new ArrayList<String>(new HashSet<String>(auditStatusIds));
            paramMap.put("auditStatusIdList", auditStatusIdList);
        }
        boolean auditRoleFlag = ResDoctorKqStatusEnum.Auditing.getId().equals(auditStatusId);
        if("Teacher".equals(roleFlag)){//带教
            paramMap.put("teacherFlow",userFlow);
            if(auditRoleFlag){
                //待带教审核
//                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.Auditing.getId());
            }
        }
        if("Head".equals(roleFlag)){
            paramMap.put("headFlow",userFlow);
            if(auditRoleFlag){
                //待科主任审核
//                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.HeadAuditing.getId());
            }
        }
        if("Admin".equals(roleFlag)){
            paramMap.put("orgFlow",currUser.getOrgFlow());
            paramMap.put("managerFlow",currUser.getUserFlow());
            if(auditRoleFlag){
                //待管理员审核
//                paramMap.put("auditStatusId",ResDoctorKqStatusEnum.ManagerAuditing.getId());
            }
        }
        List<String> notStatus=new ArrayList<>();
        notStatus.add(ResDoctorKqStatusEnum.Revoke.getId());
        notStatus.add(ResDoctorKqStatusEnum.BackLeave.getId());
        paramMap.put("notStatus",notStatus);
        PageHelper.startPage(pageIndex,pageSize);
        List<ResDoctorKq> kqList = ieaveAppBiz.searchAuditResDoctorKq(paramMap);
        model.addAttribute("kqList", kqList);
        model.addAttribute("dataCount", PageHelper.total);
        model.addAttribute("roleFlag", roleFlag);

        int auditing = 0;
        int audited = 0;
        List<Map<String,Object>> countMap = ieaveAppBiz.queryResDoctorKqCountNew(paramMap);
        if (countMap != null && countMap.size() >0) {
            for (Map<String,Object> map : countMap) {
                String type = (String)map.get("TYPE");
                int scount = Integer.parseInt(map.get("SCOUNT")+"");
                if("audited".equals(type)){
                    audited = scount;
                } else if("auditing".equals(type)){
                    auditing = scount;
                }
            }
        }
        model.addAttribute("audited", audited);
        model.addAttribute("auditing", auditing);
        return "res/jswjw/leave/leaveVerifyList";
    }

    /**
     * @Description // 请假审核
     **/
    @RequestMapping(value="/saveIeaveInfo",method = {RequestMethod.POST})
    public String saveIeaveInfo(String recordFlow,String auditInfo,String status,String roleFlag,String userFlow,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "审核成功");
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "角色标识符为空！");
            return successUrl;
        }
        if(StringUtil.isEmpty(recordFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符为空！");
            return successUrl;
        }
        if(StringUtil.isEmpty(status)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "状态标识符为空！");
            return successUrl;
        }
        if(StringUtil.isEmpty(roleFlag)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "角色标识符为空！");
            return successUrl;
        }
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户标识符为空！");
            return successUrl;
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if(null == user){
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "用户不存在！");
            return successUrl;
        }
        boolean isTeacher = "Teacher".equals(roleFlag);
        boolean isHead = "Head".equals(roleFlag);
        boolean isManager = "Admin".equals(roleFlag);

        ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if (kq == null) {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "数据标识符错误！");
            return successUrl;
        }
        SimpleDateFormat sdf0 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = sdf0.format(new Date());
        if (isTeacher) {
            if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此请假信息已审核，请刷新列表页！");
                return successUrl;
            }
            if ("Y".equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.HeadAuditing.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.HeadAuditing.getName());
                if ("-".equals(kq.getHeadName())) {
                    if ("-".equals(kq.getManagerName())) {
                        //不需要科主任及管理员审核，带教审核结果为最终审核结果
                        kq.setAuditRoleNow("Pass");
                        kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                        kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                    }else {
                        //待科主任审核
                        kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                    }
                }else {
                    kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_HEAD);
                    // 判断带教和科主任是否同一人审核，如果审批通过依次通过
                    String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
                    if (teacherFlow.equals(kq.getHeadFlow())) {
                        kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerAuditing.getId());
                        kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerAuditing.getName());
                        if ("-".equals(kq.getManagerName())) {
                            kq.setAuditRoleNow("Pass");
                            kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                            kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                        } else {
                            kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                        }
                        kq.setHeadAuditTime(time);
                        kq.setHeadAgreeFlag(status);
                    }
                }
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setTeacherAuditTime(time);
            kq.setTeacherAgreeFlag(status);
            kq.setTeacherAuditInfo(auditInfo);

        } else if (isHead) {
            if (StringUtil.isNotBlank(kq.getHeadAgreeFlag())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此请假信息已审核，请刷新列表页！");
                return successUrl;
            }
            if ("Y".equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerAuditing.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerAuditing.getName());
                if ("-".equals(kq.getManagerName())) {
                    kq.setAuditRoleNow("Pass");
                    kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                    kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                }else {
                    kq.setAuditRoleNow(GlobalConstant.RES_ROLE_SCOPE_ADMIN);
                }
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setHeadAuditTime(time);
            kq.setHeadAgreeFlag(status);
            kq.setHeadAuditInfo(auditInfo);

        }else if (isManager) {
            if (StringUtil.isNotBlank(kq.getManagerAgreeFlag())) {
                model.addAttribute("resultId", "3011101");
                model.addAttribute("resultType", "此请假信息已审核，请刷新列表页！");
                return successUrl;
            }
            if ("Y".equals(status)) {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                kq.setAuditRoleNow("Pass");
            } else {
                kq.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
                kq.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
                kq.setAuditRoleNow("UnPass");
            }
            kq.setManagerAuditTime(time);
            kq.setManagerAgreeFlag(status);
            kq.setManagerAuditInfo(auditInfo);

        } else {
            model.addAttribute("resultId", "3011101");
            model.addAttribute("resultType", "该数据无法审核！");
            return successUrl;
        }
        int count = ieaveAppBiz.editResDoctorKqNew(kq,user);
        if(count != 0) {
            //保存审批记录
            ResDoctorKqLog kqLog = new ResDoctorKqLog();
            kqLog.setKqRecordFlow(recordFlow);

            if(isTeacher){
                kqLog.setRoleId("teacher");
                kqLog.setRoleName("带教");
                kqLog.setAuditUserFlow(kq.getTeacherFlow());
                kqLog.setAuditUserName(kq.getTeacherName());
                if(kq.getTeacherAgreeFlag().equals(GlobalConstant.FLAG_Y)){
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.TeacherPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.TeacherPass.getName());
                    kqLog.setAuditRemake(kq.getTeacherAuditInfo());
                }else{
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.TeacherUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.TeacherUnPass.getName());
                    kqLog.setAuditRemake(kq.getTeacherAuditInfo());
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Leave");
                kqLog.setTypeName("请假申请");
                ieaveAppBiz.saveKqLog(kqLog,user);
                // 判断带教和科主任是否同一人审核，如果审批通过依次通过，记录审批
                String teacherFlow = kq.getTeacherFlow() == null ? "" : kq.getTeacherFlow();
                if (teacherFlow.equals(kq.getHeadFlow())) {
                    kqLog.setRecordFlow(null);
                    kqLog.setRoleId("teacher");
                    kqLog.setRoleName("带教");
                    kqLog.setAuditUserFlow(kq.getTeacherFlow());
                    kqLog.setAuditUserName(kq.getTeacherName());
                    if(kq.getTeacherAgreeFlag().equals(GlobalConstant.FLAG_Y)){
                        kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
                        kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadPass.getName());
                        kqLog.setAuditRemake(kq.getTeacherAuditInfo());
                    }else{
                        kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadUnPass.getId());
                        kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadUnPass.getName());
                        kqLog.setAuditRemake(kq.getTeacherAuditInfo());
                    }
                    ieaveAppBiz.saveKqLog(kqLog,user);
                }
            }

            if(isHead) {
                kqLog.setRoleId("head");
                kqLog.setRoleName("科主任");
                kqLog.setAuditUserFlow(kq.getHeadFlow());
                kqLog.setAuditUserName(kq.getHeadName());
                if (kq.getHeadAgreeFlag().equals(GlobalConstant.FLAG_Y)) {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadPass.getName());
                    kqLog.setAuditRemake(kq.getHeadAuditInfo());
                } else {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.HeadUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.HeadUnPass.getName());
                    kqLog.setAuditRemake(kq.getHeadAuditInfo());
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Leave");
                kqLog.setTypeName("请假申请");
                ieaveAppBiz.saveKqLog(kqLog,user);
            }

            if(isManager) {
                kqLog.setRoleId("manager");
                kqLog.setRoleName("管理员");
                kqLog.setAuditUserFlow(kq.getManagerFlow());
                kqLog.setAuditUserName(kq.getManagerName());
                if (kq.getManagerAgreeFlag().equals(GlobalConstant.FLAG_Y)) {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.ManagerPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.ManagerPass.getName());
                    kqLog.setAuditRemake(kq.getManagerAuditInfo());
                } else {
                    kqLog.setAuditStatusId(ResDoctorKqStatusEnum.ManagerUnPass.getId());
                    kqLog.setAuditStatusName(ResDoctorKqStatusEnum.ManagerUnPass.getName());
                    kqLog.setAuditRemake(kq.getManagerAuditInfo());
                }
                kqLog.setAuditTime(time);
                kqLog.setTypeId("Leave");
                kqLog.setTypeName("请假申请");
                ieaveAppBiz.saveKqLog(kqLog,user);
            }
            return successUrl;
        }
        model.addAttribute("resultId", "3011101");
        model.addAttribute("resultType", "审核失败！");
        return successUrl;
    }

}
