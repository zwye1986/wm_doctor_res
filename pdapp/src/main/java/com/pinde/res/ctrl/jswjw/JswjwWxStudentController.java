package com.pinde.res.ctrl.jswjw;

import com.pinde.app.common.GeneralController;
import com.pinde.core.common.enums.RecDocCategoryEnum;
import com.pinde.core.common.enums.RecStatusEnum;
import com.pinde.core.common.enums.ResDoctorKqStatusEnum;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.hbres.IFileBiz;
import com.pinde.res.biz.jswjw.IIeaveAppBiz;
import com.pinde.res.biz.jswjw.IJswjwBiz;
import com.pinde.res.biz.stdp.IResGradeBiz;
import com.pinde.core.model.ResDoctorKqExt;
import com.pinde.core.common.sci.dao.DeptTeacherGradeInfoMapper;
import com.pinde.core.common.sci.dao.ResDoctorMapper;
import com.pinde.core.common.sci.dao.ResDoctorSchProcessMapper;
import org.dom4j.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName JswjwStudentController
 * @Deacription APP学员功能Controller
 * @Author shengl
 * @Date 2021-01-12 15:13
 * @Version 1.0
 **/
@Controller
@RequestMapping("/res/jswjw/wx/student")
public class JswjwWxStudentController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(JswjwWxStudentController.class);

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
    @Autowired
    private IResGradeBiz gradeBiz;
    @Autowired
    private DeptTeacherGradeInfoMapper gradeInfoMapper;

    /**
     * @Author shengl
     * @Description // 请假列表
     * @Date 2021-01-12
     **/
    @RequestMapping(value = {"/leaveAndAppealList"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object leaveAndAppealList(ResDoctorKq kq, String userFlow, String statusId, Integer pageIndex, Integer pageSize,
                                     HttpServletRequest request, HttpServletResponse response) throws DocumentException {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(kq.getKqTypeId())) {
            return ResultDataThrow("kqTypeId类型标识符为空");
        }
        if (StringUtil.isNotBlank(statusId)) {
            if (!"Passed".equals(statusId) && !"Auditing".equals(statusId) && !"UnPassed".equals(statusId) && !"Revoke".equals(statusId)) {
                return ResultDataThrow("状态标识符不正确,Auditing，UnPassed，Passed，Revoke");
            }
        }
        kq.setDoctorFlow(userFlow);
        if (!com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId().equals(kq.getKqTypeId()) && !com.pinde.core.common.enums.DictTypeEnum.AppealType.getId().equals(kq.getKqTypeId())) {
            return ResultDataThrow("类型标识符不正确,LeaveType，AppealType");
        }
        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
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
        resultMap.put("list", list);
        resultMap.put("dataCount", PageHelper.total);
        return resultMap;
    }

    /**
     * @Author shengl
     * @Description // 请假相关信息
     * @Date 2021-01-12
     **/
    @RequestMapping(value = {"/leaveAndAppealDict"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object leaveAndAppealDict(String userFlow) throws DocumentException {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }

        List<Map<String,String>> leaveTypeList = new ArrayList<>();
        List<SysDict> leaveTypes = ieaveAppBiz.getDictListByDictId(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
        if(null != leaveTypes && leaveTypes.size()>0){
            for (SysDict dict:leaveTypes) {
                Map<String,String> map = new HashMap<>();
                map.put("typeId",dict.getDictId());
                map.put("typeName",dict.getDictName());
                leaveTypeList.add(map);
            }
        }
        resultMap.put("leaveTypeList",leaveTypeList);

        List<SysDict> appealTypes = ieaveAppBiz.getDictListByDictId(com.pinde.core.common.enums.DictTypeEnum.AppealType.getId());
        resultMap.put("appealTypes", appealTypes);
        List<ResDoctorSchProcess> processes = ieaveAppBiz.searchProcessByDoctor(userFlow);
        resultMap.put("processes", processes);
        return resultMap;
    }

    /**
     * @Author shengl
     * @Description //新增请假页面
     * @Date 2021-01-12
     **/
    @RequestMapping(value = {"/addLeaveOrAppeal"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object addLeaveOrAppeal(String userFlow, String kqTypeId) throws DocumentException {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(kqTypeId) || !com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId().equals(kqTypeId)) {
            return ResultDataThrow("类型标识符不正确,LeaveType");
        }
        ResDoctor doctor = doctorMapper.selectByPrimaryKey(userFlow);
        if (doctor == null ) {
            return ResultDataThrow("医师信息为空");
        }
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            return ResultDataThrow("暂未参加基地培训");
        }
        if (com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId().equals(kqTypeId)) {
            List<ResKgCfg> cfgs = ieaveAppBiz.readKqCfgList(doctor.getOrgFlow(), RecDocCategoryEnum.Doctor.getId());
            resultMap.put("cfgs", cfgs);
            if (cfgs == null || cfgs.size() <= 0) {
                return ResultDataThrow("请联系基地管理员维护请假审核流程");
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
            resultMap.put("allDays", allDays);
            resultMap.put("intervalDays", intervalDays);
            resultMap.put("intervalDays2", intervalDays2);
            resultMap.put("less", less);
            resultMap.put("greater", greater);
            resultMap.put("midd", midd);
            if (allDays == null || intervalDays == null || less == null || greater == null || midd == null) {
                return ResultDataThrow("请联系基地管理员维护请假审核流程");
            }
        }
        resultMap.put("recordFlow", PkUtil.getUUID());
//        String endDate = DateUtil.getCurrDate();
//        String startDate = DateUtil.addDate(DateUtil.getCurrDate(), -6);
//        resultMap.put("startDate", startDate);
//        resultMap.put("endDate", endDate);

        String basePath = ieaveAppBiz.getCfgByCode("upload_base_dir");
        if (StringUtil.isBlank(basePath)) {
            return ResultDataThrow("请联系系统管理员维护文件保存地址！");
        }
        return resultMap;
    }

    /**
     * @Author shengl
     * @Description // 保存请假
     * @Date 2021-01-12
     **/
    @RequestMapping(value = {"/saveLeaveOrAppeal"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object saveLeaveOrAppeal(String userFlow, ResDoctorKq kq) throws DocumentException {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(kq.getKqTypeId())) {
            return ResultDataThrow("kqTypeId类型标识符为空");
        }
        if (!com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId().equals(kq.getKqTypeId())) {
            return ResultDataThrow("类型标识符不正确,LeaveType");
        }
        ResDoctor doctor = doctorMapper.selectByPrimaryKey(userFlow);
        if (doctor == null ) {
            return ResultDataThrow("医师信息为空");
        }
        if (StringUtil.isBlank(doctor.getOrgFlow())) {
            return ResultDataThrow("暂未参加基地培训");
        }
        String sessionNumber = doctor.getSessionNumber();
        if (StringUtil.isBlank(sessionNumber)) {
            return ResultDataThrow("当前学员未维护届别信息，无法请假！");
        }
        SysUser sysUser = userMapper.selectByPrimaryKey(userFlow);
        if (StringUtil.isBlank(kq.getDoctorFlow())) {
            kq.setDoctorFlow(sysUser.getUserFlow());
            kq.setDoctorName(sysUser.getUserName());
            kq.setOrgFlow(doctor.getOrgFlow());
            kq.setOrgName(doctor.getOrgName());
            kq.setSessionNumberNow(sessionNumber);
        }
        if (com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId().equals(kq.getKqTypeId())) {
            List<ResKgCfg> cfgs = ieaveAppBiz.readKqCfgList(doctor.getOrgFlow(), RecDocCategoryEnum.Doctor.getId());
            if (cfgs == null || cfgs.size() == 0) {
                return ResultDataThrow("请联系基地管理员维护请假审核流程");
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
                return ResultDataThrow("请联系基地管理员维护请假审核流程");
            }

            if (StringUtil.isBlank(kq.getIntervalDays())) {
                return ResultDataThrow("请输入请假时长");
            }
            Map<String,String> daysMap = new HashMap<String, String>();
            daysMap.put("recordFlow",kq.getRecordFlow());
            daysMap.put("doctorFlow",kq.getDoctorFlow());
            daysMap.put("sessionNumber",sessionNumber);
            daysMap.put("id", com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
            // 学员请假天数
            double userHour = Double.valueOf(kq.getIntervalDays());
            double days = ieaveAppBiz.readAllIntervalDays(daysMap);
            if ((days + userHour) > allDays) {
                return ResultDataThrow("请假总时间数大于" + allDays + ",总时间为" + (days + userHour) + ",你已请假" + days + "本次请假" + kq.getIntervalDays());
            }
            ResDoctorSchProcess process = resDoctorProcessMapper.selectByPrimaryKey(kq.getProcessFlow());
            if (process == null) {
                return ResultDataThrow("请选择轮转科室");
            }
            // 判断请假时间范围
            String startDate = kq.getStartDate();
            String endDate = kq.getEndDate();
            if(endDate.compareTo(startDate) < 0 ){
                return ResultDataThrow("开始时间不得大于结束时间！");
            }
            if (StringUtil.isNotBlank(endDate) && endDate.length() > 10 && startDate.length() > 10) {
                String schStartDate = process.getSchStartDate();
                String schEndDate = process.getSchEndDate();
                startDate = endDate.substring(0,10);
                endDate = endDate.substring(0,10);
                if(endDate.compareTo(schEndDate) > 0 || startDate.compareTo(schStartDate) < 0){
                    return ResultDataThrow("请假结束时间需在所选择科室轮转时间范围内");
                }
            }else {
                return ResultDataThrow("时间参数有误");
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
                return ResultDataThrow("此请假信息已被审核，请刷新列表页");
            }
        }
        int c = ieaveAppBiz.checkTime(kq.getRecordFlow(), kq.getDoctorFlow(), kq.getStartDate(), kq.getEndDate(), kq.getKqTypeId(),sessionNumber);
        if (c > 0) {
            return ResultDataThrow("在当前请假时间内已有请假信息");
        }
        if (com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId().equals(kq.getKqTypeId())) {
            List<SysDict> leaveTypes = ieaveAppBiz.getDictListByDictId(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
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
            return ResultDataThrow("保存失败");
        }
        return resultMap;
    }

    private void setKqAuditInfo(ResKgCfg greater, ResDoctorKq kq, ResDoctorSchProcess process, ResDoctor doctor) {
        if (greater != null) {

            String auditRoleNow  = "";
            kq.setTeacherFlow(process.getTeacherUserFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(greater.getTeacherFlag())) {
                // teacher 带教
                auditRoleNow = com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER;
                kq.setTeacherName(process.getTeacherUserName());
            } else {
                kq.setTeacherName("-");
            }
            kq.setHeadFlow(process.getHeadUserFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(greater.getHeadFlag())) {
                if (StringUtil.isBlank(auditRoleNow)) {
                    auditRoleNow = com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD;
                }
                kq.setHeadName(process.getHeadUserName());
            } else {
                kq.setHeadName("-");
            }
            kq.setTutorFlow(doctor.getTutorFlow());
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(greater.getTutorFlag())) {
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
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(greater.getManagerFlag())) {
                    if (StringUtil.isBlank(auditRoleNow)) {
                        auditRoleNow = com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN;
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
    @ResponseBody
    public Object addImage(String userFlow, String recordFlow, String imageContent, HttpServletRequest request, String fileName, MultipartFile imageContent2) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(recordFlow)) {
            return ResultDataThrow("数据标识符为空");
        }
        if (StringUtil.isBlank(imageContent)) {
            return ResultDataThrow("图片内容为空");
        }
        if (StringUtil.isBlank(fileName)) {
            return ResultDataThrow("图片名称为空");
        }
        String baseDir = ieaveAppBiz.getCfgByCode("upload_base_dir");
        if (StringUtil.isBlank(baseDir)) {
            return ResultDataThrow("请联系管理员，设置上传图片路径！");
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
            logger.error("", e);
            throw new RuntimeException("保存文件失败！");
        }
        String filePath = File.separator + "ResDoctorKqFile" + File.separator + dateString + File.separator + recordFlow + File.separator + originalFilename;

        PubFile pubFile = new PubFile();
        pubFile.setFileFlow(PkUtil.getUUID());
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        pubFile.setFilePath(filePath);
        pubFile.setFileName(fileName);
        pubFile.setFileSuffix(fileName.substring(fileName.lastIndexOf(".")));
        pubFile.setProductType("ResDoctorKqFile");
        pubFile.setProductFlow(recordFlow);
        fileBiz.addFile(pubFile);
        resultMap.put("pubFile", pubFile);
        String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
        resultMap.put("uploadBaseUrl", uploadBaseUrl);
        return resultMap;
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
    @ResponseBody
    public Object showLeaveOrAppeal(String userFlow, String recordFlow) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(recordFlow)) {
            return ResultDataThrow("数据标识符为空");
        }

        ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if (kq == null) {
            return ResultDataThrow("数据标识符为错误");
        }
        resultMap.put("kq", kq);
        List<PubFile> files = fileBiz.findFileByTypeFlow("ResDoctorKqFile", recordFlow);
        resultMap.put("files", files);

        //获取访问路径前缀
        String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
        resultMap.put("uploadBaseUrl", uploadBaseUrl);

        List<Map<String,String>> logList = new ArrayList<>();
        Map<String,String> logMap = new HashMap<>();
        logMap.put("isShow", com.pinde.core.common.GlobalConstant.FLAG_Y);
        logMap.put("statusId","Revoke".equals(kq.getAuditStatusId()) ? "Revoke" : "Start");
        logMap.put("statusName","Revoke".equals(kq.getAuditStatusId()) ? "已撤销" : "发起审核");
        logMap.put("operUserName",kq.getDoctorName());
        logMap.put("operTime",DateUtil.transDateTime(kq.getCreateTime()));
        logMap.put("auditInfo","");
        logList.add(logMap);

        if(!"Revoke".equals(kq.getAuditStatusId())){
            logMap = new HashMap<>();
            logMap.put("isShow", "-".equals(kq.getTeacherName()) ? com.pinde.core.common.GlobalConstant.FLAG_N : com.pinde.core.common.GlobalConstant.FLAG_Y);
            logMap.put("statusId", StringUtil.isBlank(kq.getTeacherAgreeFlag()) ? "Auditing" : com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTeacherAgreeFlag()) ? "Passed" : "UnPassed");
            logMap.put("statusName", StringUtil.isBlank(kq.getTeacherAgreeFlag()) ? "待审核" : com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTeacherAgreeFlag()) ? "审核通过" : "审核不通过");
            logMap.put("operUserName",kq.getTeacherName());
            logMap.put("operTime",kq.getTeacherAuditTime());
            logMap.put("auditInfo",kq.getTeacherAuditInfo());
            logList.add(logMap);
        }

        boolean audit = false;
        if(!"-".equals(kq.getHeadName())){
            if(StringUtil.isNotBlank(kq.getHeadAgreeFlag())){
                audit = true;
            }else{
                if("-".equals(kq.getTeacherName())){
                    audit = true;
                }else if(!"-".equals(kq.getTeacherName())){
                    if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTeacherAgreeFlag())) {
                        audit = true;
                    }
                    if(StringUtil.isBlank(kq.getTeacherAgreeFlag())){
                        audit = true;
                    }
                }
            }
        }
        if(audit){
            logMap = new HashMap<>();
            logMap.put("isShow", "-".equals(kq.getHeadName()) ? com.pinde.core.common.GlobalConstant.FLAG_N : com.pinde.core.common.GlobalConstant.FLAG_Y);
            logMap.put("statusId", StringUtil.isBlank(kq.getHeadAgreeFlag()) ? "Auditing" : com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getHeadAgreeFlag()) ? "Passed" : "UnPassed");
            logMap.put("statusName", StringUtil.isBlank(kq.getHeadAgreeFlag()) ? "待审核" : com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getHeadAgreeFlag()) ? "审核通过" : "审核不通过");
            logMap.put("operUserName",kq.getHeadName());
            logMap.put("operTime",kq.getHeadAuditTime());
            logMap.put("auditInfo",kq.getHeadAuditInfo());
            logList.add(logMap);
        }

        audit = false;
        if(!"-".equals(kq.getManagerName())){
            if(StringUtil.isNotBlank(kq.getManagerAgreeFlag())){
                audit = true;
            }else{
                if("-".equals(kq.getTeacherName()) && "-".equals(kq.getHeadName()) && "-".equals(kq.getTutorName())){
                    audit = true;
                }
                if("-".equals(kq.getHeadName()) && "-".equals(kq.getTutorName()) && !"-".equals(kq.getTeacherName())){
                    if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTeacherAgreeFlag())) {
                        audit = true;
                    }
                    if(StringUtil.isBlank(kq.getTeacherAgreeFlag())){
                        audit = true;
                    }
                }
                if("-".equals(kq.getTeacherName()) && "-".equals(kq.getTutorName()) && !"-".equals(kq.getHeadName())){
                    if (StringUtil.isNotBlank(kq.getHeadAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getHeadAgreeFlag())) {
                        audit = true;
                    }
                    if(StringUtil.isBlank(kq.getHeadAgreeFlag())){
                        audit = true;
                    }
                }
                if("-".equals(kq.getTeacherName()) && "-".equals(kq.getHeadName()) && !"-".equals(kq.getTutorName())){
                    if (StringUtil.isNotBlank(kq.getTutorAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTutorAgreeFlag())) {
                        audit = true;
                    }
                    if(StringUtil.isBlank(kq.getTutorAgreeFlag())){
                        audit = true;
                    }
                }
                if(!"-".equals(kq.getTeacherName()) && !"-".equals(kq.getHeadName()) && !"-".equals(kq.getTutorName())){
                    if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTeacherAgreeFlag())) {
                        if (StringUtil.isNotBlank(kq.getHeadAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getHeadAgreeFlag())) {
                            if (StringUtil.isNotBlank(kq.getTutorAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTutorAgreeFlag())) {
                                audit = true;
                            }
                            if(StringUtil.isBlank(kq.getTutorAgreeFlag())){
                                audit = true;
                            }
                        }
                        if(StringUtil.isBlank(kq.getHeadAgreeFlag())){
                            audit = true;
                        }
                    }
                    if(StringUtil.isBlank(kq.getTeacherAgreeFlag())){
                        audit = true;
                    }
                }
                if("-".equals(kq.getTutorName()) && !"-".equals(kq.getHeadName()) && !"-".equals(kq.getTeacherName())){
                    if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTeacherAgreeFlag())) {
                        if (StringUtil.isNotBlank(kq.getHeadAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getHeadAgreeFlag())) {
                            audit = true;
                        }
                        if(StringUtil.isBlank(kq.getHeadAgreeFlag())){
                            audit = true;
                        }
                    }
                    if(StringUtil.isBlank(kq.getTeacherAgreeFlag())){
                        audit = true;
                    }
                }
                if("-".equals(kq.getHeadName()) && !"-".equals(kq.getTutorName()) && !"-".equals(kq.getTeacherName())){
                    if (StringUtil.isNotBlank(kq.getTeacherAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTeacherAgreeFlag())) {
                        if (StringUtil.isNotBlank(kq.getTutorAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTutorAgreeFlag())) {
                            audit = true;
                        }
                        if(StringUtil.isBlank(kq.getTutorAgreeFlag())){
                            audit = true;
                        }
                    }
                    if(StringUtil.isBlank(kq.getTeacherAgreeFlag())){
                        audit = true;
                    }
                }
                if("-".equals(kq.getTeacherName()) && !"-".equals(kq.getTutorName()) && !"-".equals(kq.getHeadName())){
                    if (StringUtil.isNotBlank(kq.getHeadAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getHeadAgreeFlag())) {
                        if (StringUtil.isNotBlank(kq.getTutorAgreeFlag()) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getTutorAgreeFlag())) {
                            audit = true;
                        }
                        if(StringUtil.isBlank(kq.getTutorAgreeFlag())){
                            audit = true;
                        }
                    }
                    if(StringUtil.isBlank(kq.getHeadAgreeFlag())){
                        audit = true;
                    }
                }
            }
        }
        if(audit){
            logMap = new HashMap<>();
            logMap.put("isShow", "-".equals(kq.getManagerName()) ? com.pinde.core.common.GlobalConstant.FLAG_N : com.pinde.core.common.GlobalConstant.FLAG_Y);
            logMap.put("statusId", StringUtil.isBlank(kq.getManagerAgreeFlag()) ? "Auditing" : com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getManagerAgreeFlag()) ? "Passed" : "UnPassed");
            logMap.put("statusName", StringUtil.isBlank(kq.getManagerAgreeFlag()) ? "待审核" : com.pinde.core.common.GlobalConstant.FLAG_Y.equals(kq.getManagerAgreeFlag()) ? "审核通过" : "审核不通过");
            logMap.put("operUserName",kq.getManagerName());
            logMap.put("operTime",kq.getManagerAuditTime());
            logMap.put("auditInfo",kq.getManagerAuditInfo());
            logList.add(logMap);
        }
        resultMap.put("logList",logList);
        return resultMap;
    }

    /**
     * @Author shengl
     * @Description // 撤销请假
     * @Date 2021-01-15
     **/
    @RequestMapping(value = {"/revokeLeaveOrAppeal"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object revokeLeaveOrAppeal(String userFlow, ResDoctorKq kq) throws DocumentException {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isEmpty(kq.getRecordFlow())) {
            return ResultDataThrow("数据标识符为空");
        }
        ResDoctorKq old = ieaveAppBiz.readResDoctorKq(kq.getRecordFlow());
//        ResDoctorKq old=sctcm120StudentBiz.readResDoctorKq(kq.getRecordFlow());
        if (old != null) {
            if (!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId())) {
                return ResultDataThrow("此信息已被审核，请刷新列表页");
            }
        } else {
            return ResultDataThrow("数据标识符错误");
        }
        SysUser sysUser = userMapper.selectByPrimaryKey(userFlow);
        old.setAuditStatusId(ResDoctorKqStatusEnum.Revoke.getId());
        old.setAuditStatusName(ResDoctorKqStatusEnum.Revoke.getName());
        old.setModifyTime(DateUtil.getCurrDateTime());
        old.setModifyUserFlow(sysUser.getUserFlow());
        int count = ieaveAppBiz.updateResDoctorKq(old);
        /*int n=sctcm120StudentBiz.editResDoctorKq(kq,sysUser,kq);*/
        if (count == 0) {
            return ResultDataThrow( "撤销失败");
        }
        return resultMap;
    }

    /**
     * @Author shengl
     * @Description // 删除附件
     **/
    @RequestMapping(value = {"/delImage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object delImage(String userFlow, String fileFlow) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "删除成功");

        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(fileFlow)) {
            return ResultDataThrow("图片标识符为空");
        }
        PubFile pubFile = fileBiz.readFile(fileFlow);
        if (pubFile == null) {
            return ResultDataThrow("图片信息不存在为空");
        }
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        fileBiz.editFile(pubFile);
        return resultMap;
    }

    @RequestMapping(value = {"/delLeaveOrAppeal"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object delLeaveOrAppeal() {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "暂无删除功能");
        return resultMap;
    }

    /**
     * @Description //学员请假列表
     **/
    @RequestMapping(value = "/doctorleaveVerifyList",method = {RequestMethod.POST})
    @ResponseBody
    public Object doctorleaveVerifyList(ResDoctorKq kq, String userFlow,Integer pageIndex, Integer pageSize, HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if(null == user){
            return ResultDataThrow("用户不存在");
        }
//        if(StringUtil.isBlank(kq.getOrgFlow())){
//            return ResultDataThrow("基地标识符为空");
//        }
        if (pageIndex == null) {
            return ResultDataThrow("当前页码为空");
        }
        if (pageSize == null) {
            return ResultDataThrow("每页条数为空");
        }
        kq.setDoctorFlow(userFlow);
        kq.setOrgFlow(user.getOrgFlow());
        kq.setKqTypeId(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
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
        resultMap.put("kqList",kqList);
        resultMap.put("dataCount", PageHelper.total);
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("headImage",StringUtil.isBlank(user.getUserHeadImg()) ? "" : uploadBaseUrl + "/" +user.getUserHeadImg());
        return resultMap;
    }

    @RequestMapping(value = "/doctorleaveVerifyMain",method = {RequestMethod.POST})
    @ResponseBody
    public Object doctorleaveVerifyMain(String userFlow){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
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
        resultMap.put("deptList",deptList);
        //是否可以新增请假
        String isAddLeave = com.pinde.core.common.GlobalConstant.FLAG_N;
        if(null != deptList && deptList.size() > 0){
            isAddLeave = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        resultMap.put("isAddLeave",isAddLeave);
        //请假类型
        List<Map<String,Object>> typeList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        map.put("typeId","");
        map.put("typeName","全部");
        typeList.add(map);
        List<Map<String, Object>> typeList2 = ieaveAppBiz.searchDictList(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
        if(null != typeList2 && typeList2.size()>0){
            typeList.addAll(typeList2);
        }
        resultMap.put("typeList",typeList);
        return resultMap;
    }

    /**
     * @Description //查看附件
     **/
    @RequestMapping(value = "/showFilws",method = {RequestMethod.POST})
    @ResponseBody
    public Object showFilws(String recordFlow){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(recordFlow)) {
            return ResultDataThrow("请假标识符为空");
        }
        List<PubFile> files=fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
        resultMap.put("files",files);
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("uploadBaseUrl", uploadBaseUrl);
        return resultMap;
    }

    /**
     * @Description //学员请假新增
     **/
    @RequestMapping(value = "/addLeave",method = {RequestMethod.POST})
    @ResponseBody
    public Object editLeave(String recordFlow){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if(StringUtil.isBlank(recordFlow)){
            resultMap.put("recordFlow", PkUtil.getUUID());
        }
        String nowDate = DateUtil.getCurrDate();
        resultMap.put("nowDate",nowDate);
        return resultMap;
    }

    /**
     * @Description //计算请假天数（只计算工作日）
     **/
    @RequestMapping(value="/intervalDays",method = {RequestMethod.POST})
    @ResponseBody
    public Object intervalDays(String startDate,String endDate){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(startDate)) {
            return ResultDataThrow("开始时间为空");
        }
        if (StringUtil.isEmpty(endDate)) {
            return ResultDataThrow("结束时间为空");
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
            return ResultDataThrow("请假时间为周末，不需提交申请！");
        }
        resultMap.put("intervalDays",String.valueOf(result));
        return resultMap;
    }

    /**
     * @Description //保存新增请假申请
     **/
    @RequestMapping(value = "/saveAddLeave",method = {RequestMethod.POST})
    @ResponseBody
    public Object saveAddLeave(ResDoctorKqExt kqExt, HttpServletRequest request){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "保存成功");
        if(null == kqExt){
            return ResultDataThrow("参数格式错误！");
        }
        if(StringUtil.isBlank(kqExt.getUserFlow())){
            return ResultDataThrow("用户标识符为空！");
        }
        ResDoctor doctor = jswjwBiz.readDoctor(kqExt.getUserFlow());
        if(doctor==null) {
            return ResultDataThrow("医师信息不存在！");
        }
        if(StringUtil.isBlank(doctor.getOrgFlow())) {
            return ResultDataThrow("未进入基地参加培训！");
        }
        SysUser user = jswjwBiz.readSysUser(kqExt.getUserFlow());
        if(null == user){
            return ResultDataThrow("用户不存在！");
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
            return ResultDataThrow("请联系基地管理员维护请假审核流程！");
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
            return ResultDataThrow("请联系基地管理员维护请假审核流程！");
        }
        ResDoctorKq old = ieaveAppBiz.readResDoctorKq(kqExt.getRecordFlow());
        if(old!=null) {
            if(!ResDoctorKqStatusEnum.Auditing.getId().equals(old.getAuditStatusId())) {
                return ResultDataThrow("此请假信息已被审核，请刷新列表页！");
            }
        }
        int c = ieaveAppBiz.checkLeaveTime(kqExt.getRecordFlow(), kqExt.getDoctorFlow(), kqExt.getStartDate(), kqExt.getEndDate(), com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
        if(c>0) {
            return ResultDataThrow("在当前请假时间内已有请假信息！");
        }
        if(StringUtil.isBlank(kqExt.getIntervalDays())) {
            return ResultDataThrow("请输入请假天数");
        }

        ResDoctorSchProcess process = jswjwBiz.readSchProcess(kqExt.getProcessFlow());
        if(process==null)
        {
            return ResultDataThrow("请选择轮转科室");
        }
        if(intervalDays2<Double.valueOf(kqExt.getIntervalDays()))
        {
            setKqAuditInfo(greater,kqExt,process,doctor);
        }else if(intervalDays2>=Double.valueOf(kqExt.getIntervalDays())&&intervalDays<Double.valueOf(kqExt.getIntervalDays())){
            setKqAuditInfo(midd,kqExt,process,doctor);
        }else{
            setKqAuditInfo(less,kqExt,process,doctor);
        }

        kqExt.setKqTypeId(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getId());
        kqExt.setKqTypeName(com.pinde.core.common.enums.DictTypeEnum.LeaveType.getName());
        kqExt.setTypeName(com.pinde.core.common.enums.DictTypeEnum.getDictName(com.pinde.core.common.enums.DictTypeEnum.LeaveType, kqExt.getTypeId()));
        kqExt.setAuditStatusId(ResDoctorKqStatusEnum.Auditing.getId());
        kqExt.setAuditStatusName(ResDoctorKqStatusEnum.Auditing.getName());
        int n = ieaveAppBiz.addResDoctorKq(kqExt,user);
        if(n==0) {
            return ResultDataThrow("保存失败");
        }
        return resultMap;
    }

    @RequestMapping(value = {"/addLeaveImage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object addLeaveImage(ImageFileForm form, HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(form.getUserFlow())) {
            return ResultDataThrow("用户标识符为空");
        }
        if (StringUtil.isBlank(form.getRecordFlow())) {
            return ResultDataThrow("数据标识符为空");
        }
        if (StringUtil.isBlank(form.getImageContent())) {
            return ResultDataThrow("图片内容为空");
        }
        if (StringUtil.isBlank(form.getFileName())) {
            return ResultDataThrow("图片名称为空");
        }
        String baseDir = ieaveAppBiz.getCfgByCode("upload_base_dir");
        if (StringUtil.isBlank(baseDir)) {
            return ResultDataThrow("请联系管理员，设置上传图片路径！");
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
            logger.error("", e);
            throw new RuntimeException("保存文件失败！");
        }
        String filePath = File.separator + "ResDoctorKqFile" + File.separator + dateString + File.separator + form.getRecordFlow() + File.separator + originalFilename;

        PubFile pubFile = new PubFile();
        pubFile.setFileFlow(PkUtil.getUUID());
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        pubFile.setFilePath(filePath);
        pubFile.setFileName(form.getFileName());
        pubFile.setFileSuffix(form.getFileName().substring(form.getFileName().lastIndexOf(".")));
        pubFile.setProductType("ResDoctorKqFile");
        pubFile.setProductFlow(form.getRecordFlow());
        fileBiz.addFile(pubFile);
        resultMap.put("pubFile", pubFile);
        String uploadBaseUrl = ieaveAppBiz.getCfgByCode("upload_base_url");
        resultMap.put("uploadBaseUrl", uploadBaseUrl);
        return resultMap;
    }

    @RequestMapping(value = {"/deleteImage"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object deleteImage(String fileFlow) {
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "删除成功");
        if (StringUtil.isEmpty(fileFlow)) {
            return ResultDataThrow("附件标识符为空");
        }
        jswjwBiz.deleteLeaveImage(fileFlow);
        return resultMap;
    }

    /**
     * @Description //查看审核进度
     **/
    @RequestMapping(value = "/searchAudit",method = {RequestMethod.POST})
    @ResponseBody
    public Object searchAudit(String recordFlow,String roleFlag){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isEmpty(recordFlow)) {
            return ResultDataThrow("请假标识符为空");
        }
        String uploadBaseUrl = jswjwBiz.getCfgCode("upload_base_url");
        resultMap.put("uploadBaseUrl", uploadBaseUrl);
        ResDoctorKq resDoctorKq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if(null == resDoctorKq){
            return ResultDataThrow("请假记录不存在");
        }
        resultMap.put("resDoctorKq",resDoctorKq);
        List<PubFile> files = fileBiz.findFileByTypeFlow("ResDoctorKqFile",recordFlow);
        resultMap.put("files",files);
//        List<ResDoctorKqLog> leaveLogs = ieaveAppBiz.searchKqLogList(recordFlow,"Leave");
//        resultMap.put("leaveLogs",leaveLogs);
//        List<ResDoctorKqLog> cancelLogs = ieaveAppBiz.searchKqLogList(recordFlow,"Cancel");
//        resultMap.put("cancelLogs",cancelLogs);

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
//        resultMap.put("leaveMapList",leaveMapList);

//        List<Map<String,Object>> cancelMapList = new ArrayList<>();

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
            logMap.put("auditName", "待审核");
            logMap.put("auditInfo", "" );
            logMap.put("auditTime", "" );
            leaveMapList.add(logMap);
        }
        if(!"-".equals(headName) && !resDoctorKq.getAuditStatusId().equals("BackLeave") && resDoctorKq.getAuditStatusId().equals("RevokeAuditing")){
            logMap = new HashMap<>();
            logMap.put("roleId","head");
            logMap.put("roleName","科主任");
            logMap.put("userName","Head".equals(roleFlag) ? "我" : resDoctorKq.getHeadName());
//            ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Cancel","head");
            logMap.put("auditName", "待审核" );
            logMap.put("auditInfo", "" );
            logMap.put("auditTime", "" );
            leaveMapList.add(logMap);
        }
        if(!"-".equals(manageName) && !resDoctorKq.getAuditStatusId().equals("BackLeave") && resDoctorKq.getAuditStatusId().equals("RevokeAuditing")){
            logMap = new HashMap<>();
            logMap.put("roleId","manager");
            logMap.put("roleName","管理员");
            logMap.put("userName","Admin".equals(roleFlag) ? "我" : resDoctorKq.getManagerName());
//            ResDoctorKqLog log = ieaveAppBiz.searchKqLog(recordFlow,"Cancel","manager");
            logMap.put("auditName", "待审核" );
            logMap.put("auditInfo", "" );
            logMap.put("auditTime", "" );
            leaveMapList.add(logMap);
        }
        resultMap.put("leaveMapList",leaveMapList);
        return resultMap;
    }

    /**
     * @Description //销假申请
     **/
    @RequestMapping(value = "/cancelLeave",method = {RequestMethod.POST})
    @ResponseBody
    public Object cancelLeave(String recordFlow,String userFlow){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "交易成功");
        if (StringUtil.isBlank(userFlow)) {
            return ResultDataThrow("用户标识符为空！");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if(null == user){
            return ResultDataThrow("用户不存在！");
        }
        if (StringUtil.isBlank(recordFlow)) {
            return ResultDataThrow("请假标识符为空！");
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
        kq.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        kq.setModifyTime(DateUtil.getCurrDateTime());
        kq.setModifyUserFlow(user.getUserFlow());
        ieaveAppBiz.updateResDoctorKq(kq);
        return resultMap;
    }

    /**
     * @Description //撤回请假申请、销假申请
     **/
    @RequestMapping(value = "/backLeave",method = {RequestMethod.POST})
    @ResponseBody
    public Object backLeave(String recordFlow,String auditStatusId,String userFlow){
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("resultId", "200");
        resultMap.put("resultType", "操作成功");
        if (StringUtil.isEmpty(recordFlow)) {
            return ResultDataThrow("请假标识符为空");
        }
        if (StringUtil.isEmpty(auditStatusId)) {
            return ResultDataThrow("状态标识符为空");
        }
        if (StringUtil.isEmpty(userFlow)) {
            return ResultDataThrow("用户标识符为空");
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if(null == user){
            return ResultDataThrow("用户不存在");
        }
        ResDoctorKq kq = ieaveAppBiz.readResDoctorKq(recordFlow);
        if (ResDoctorKqStatusEnum.ManagerPass.getId().equals(kq.getAuditStatusId()) ||
                ResDoctorKqStatusEnum.RevokeManagerPass.getId().equals(kq.getAuditStatusId())) {
            return ResultDataThrow("该条申请记录已审核，无法操作！");
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
                        kq.setManagerAgreeFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                    if(log.getRoleId().equals("head")){
                        kq.setHeadAuditTime(log.getAuditTime());
                        kq.setHeadAuditInfo(log.getAuditRemake());
                        kq.setHeadAgreeFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                    if(log.getRoleId().equals("teacher")){
                        kq.setTeacherAuditTime(log.getAuditTime());
                        kq.setTeacherAuditInfo(log.getAuditRemake());
                        kq.setTeacherAgreeFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                    }
                }
            }
            //撤回销假，删除销假审批记录
            ieaveAppBiz.updateKqLogsRecordStatusN(recordFlow,"Cancel");
        }
        ieaveAppBiz.editResDoctorKqNew(kq,user);
        return resultMap;
    }

    @RequestMapping(value = "/getProcessByUser")
    @ResponseBody
    public Object getProcessByUser(String userFlow) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(userFlow)){
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        List<ResDoctorSchProcess> resDoctorSchProcesses = jswjwBiz.searchSchProcess(userFlow, "");
        resultMap.put("resultId", "200");
        resultMap.put("resDoctorSchProcesses", resDoctorSchProcesses);
        return resultMap;
    }

    @RequestMapping(value = "/patientevaluation")
    @ResponseBody
    public Object patientevaluation(String userFlow) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(userFlow)) {
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        resultMap.put("resultId", "200");
        resultMap.put("userFlow", userFlow);
        String wx_patient_login_url = jswjwBiz.getCfgCode("wx_patient_login_url");
        if (null==wx_patient_login_url || StringUtil.isBlank(wx_patient_login_url)){
            resultMap.put("resultId", "202");
            resultMap.put("resultType", "微信端病人评价学员扫码访问的页面地址为空");//http://192.168.2.217:8081/#/patientevaluation
            return resultMap;
        }
        wx_patient_login_url=wx_patient_login_url+"?userFlow="+userFlow;
        resultMap.put("resultUrl", wx_patient_login_url);
        return resultMap;
    }



    @RequestMapping(value = "/assessCfg")
    @ResponseBody
    public Object assessCfg(String userFlow) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(userFlow)){
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (null==user){
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "用户为空");
            return resultMap;
        }
        resultMap.put("resultUser", user);
        List<ResAssessCfg> resAssessCfgList = gradeBiz.getAssCfg(com.pinde.core.common.enums.ResRecTypeEnum.PatientDoctorAssess360.getId());
        if (null == resAssessCfgList || resAssessCfgList.isEmpty()) {
            resultMap.put("resultId", "202");
            resultMap.put("resultType", "无对应的评价表单");
            return resultMap;
        }
        ResAssessCfg assessCfg = resAssessCfgList.get(0);
        Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
        String titleXpath = "//title";
        List<Element> titleElementList = dom.selectNodes(titleXpath);
        if (titleElementList != null && !titleElementList.isEmpty()) {
            List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
            for (Element te : titleElementList) {
                ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
                titleForm.setType(te.attributeValue("type"));
                titleForm.setId(te.attributeValue("id"));
                titleForm.setName(te.attributeValue("name"));
                titleForm.setRow(te.attributeValue("row"));
                titleForm.setEvalTypeId(te.attributeValue("evalTypeId") == null ? "" : te.attributeValue("evalTypeId"));
                titleForm.setEvalTypeName(te.attributeValue("evalTypeName") == null ? "" : te.attributeValue("evalTypeName"));
                List<Element> itemElementList = te.elements("item");
                List<ResAssessCfgItemForm> itemFormList = null;
                if (itemElementList != null && !itemElementList.isEmpty()) {
                    itemFormList = new ArrayList<ResAssessCfgItemForm>();
                    int sum = 0;
                    for (Element ie : itemElementList) {
                        ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
                        itemForm.setId(ie.attributeValue("id"));
                        itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
                        itemForm.setType(ie.element("type") == null ? "" : ie.element("type").getTextTrim());
                        itemForm.setRow(ie.element("row") == null ? "" : ie.element("row").getTextTrim());
                        String score = ie.element("score") == null ? "" : ie.element("score").getTextTrim();
                        itemForm.setScore(score);
                        itemFormList.add(itemForm);
                        if (StringUtil.isNotBlank(score)) {
                            sum += Integer.parseInt(score);
                        }
                        titleForm.setScore(String.valueOf(sum));
                    }
                }
                titleForm.setItemList(itemFormList);
                titleFormList.add(titleForm);
            }
            resultMap.put("titleFormList", titleFormList);
        }
        resultMap.put("assessCfg",assessCfg);
        resultMap.put("resultId", "200");
        List<ResDoctorSchProcess> resDoctorSchProcesses = jswjwBiz.searchSchProcess(userFlow, "");
        if (null==resAssessCfgList || resDoctorSchProcesses.isEmpty()){
            resultMap.put("resultId", "203");
            resultMap.put("resultType", "无轮转计划信息");
            return resultMap;
        }
        resultMap.put("processList", resDoctorSchProcesses);
        return resultMap;
    }

    /**
     *
     * @param userFlow  用户主键
     * @param processFlow   轮转计划主键
     * @param patientName   病人名称
     * @param allScore  总分数
     * @param cfgFlow   评分表单主键
     * @param request
     * @return
     */
    @RequestMapping(value = {"/saveGradePatient"}, method = {RequestMethod.POST})
    @ResponseBody
    public Object saveGradeTwo(String userFlow, String processFlow,String patientName,String allScore, String cfgFlow, HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isBlank(userFlow)){
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "userFlow为空");
            return resultMap;
        }
        SysUser user = jswjwBiz.readSysUser(userFlow);
        if (null==user){
            resultMap.put("resultId", "201");
            resultMap.put("resultType", "评价的医师不存在");
            return resultMap;
        }
        if (StringUtil.isBlank(processFlow)){
            resultMap.put("resultId", "202");
            resultMap.put("resultType", "processFlow为空");
            return resultMap;
        }
        ResDoctorSchProcess resDoctorSchProcess = jswjwBiz.readSchProcess(processFlow);
        if (null==resDoctorSchProcess){
            resultMap.put("resultId", "203");
            resultMap.put("resultType", "轮转计划为空");
            return resultMap;
        }
        if (StringUtil.isBlank(cfgFlow)){
            resultMap.put("resultId", "204");
            resultMap.put("resultType", "cfgFlow为空");
            return resultMap;
        }
        ResAssessCfg assessCfg = jswjwBiz.readResAssessCfg(cfgFlow);
        if (null==assessCfg){
            resultMap.put("resultId", "204");
            resultMap.put("resultType", "无对应的评价表单");
            return resultMap;
        }
        DeptTeacherGradeInfo gradeInfo=new DeptTeacherGradeInfo();
        gradeInfo.setRecFlow(PkUtil.getUUID());
        gradeInfo.setOrgFlow(user.getOrgFlow());
        gradeInfo.setOrgName(user.getOrgName());
        gradeInfo.setOperUserFlow(userFlow);
        gradeInfo.setOperUserName(user.getUserName());
        gradeInfo.setOperTime(DateUtil.getCurrDateTime());
        gradeInfo.setTeacherNameOne(patientName);   //病人姓名
        gradeInfo.setProcessFlow(processFlow);
        gradeInfo.setDeptFlow(resDoctorSchProcess.getDeptFlow());
        gradeInfo.setDeptName(resDoctorSchProcess.getDeptName());
        gradeInfo.setSchRotationDeptFlow(resDoctorSchProcess.getSchDeptFlow());
        gradeInfo.setSchDeptFlow(resDoctorSchProcess.getSchDeptFlow());
        gradeInfo.setSchDeptName(resDoctorSchProcess.getSchDeptName());
        gradeInfo.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.PatientDoctorAssess360.getId());
        gradeInfo.setRecTypeName(com.pinde.core.common.enums.ResRecTypeEnum.PatientDoctorAssess360.getName());
        gradeInfo.setAllScore(allScore);
        gradeInfo.setCfgFlow(cfgFlow);
        String content="";
        String gradeContent = assessCfg.getCfgBigValue();
        List<Map<String,Object>> assessMaps = parseAssessCfg(gradeContent);
        if(assessMaps!=null && !assessMaps.isEmpty()){
            String checkResult=checkGradeScore(assessMaps,request);
            if(StringUtil.isNotBlank(checkResult))
            {
                resultMap.put("resultId", "205");
                resultMap.put("errorMsg:", checkResult);
                return resultMap;
            }
            content = getGradeXmlTwo(assessMaps,request);
        }
        gradeInfo.setRecContent(content);
        gradeInfo.setStatusId(RecStatusEnum.Submit.getId());
        gradeInfo.setStatusName(RecStatusEnum.Submit.getName());
        gradeInfo.setCreateTime(DateUtil.getCurrDateTime());
        gradeInfo.setCreateUserFlow(userFlow);
        gradeInfo.setModifyTime(DateUtil.getCurrDateTime());
        gradeInfo.setModifyUserFlow(userFlow);
        gradeInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        int count = gradeInfoMapper.insertSelective(gradeInfo);
        if (count>0){
            resultMap.put("resultId", "200");
            resultMap.put("resultType", "保存成功");
        }
        return resultMap;
    }

    //解析评分模板
    public List<Map<String,Object>> parseAssessCfg(String content){
        if(StringUtil.isNotBlank(content)){
            try {
                Document dom = DocumentHelper.parseText(content);
                if(dom!=null){
                    Element root = dom.getRootElement();
                    if(root!=null){
                        //获取根节点下的所有title节点
                        List<Element> titles = root.elements();
                        if(titles!=null && !titles.isEmpty()){
                            List<Map<String,Object>> dataMaps = new ArrayList<Map<String,Object>>();
                            for(Element i : titles){
                                Map<String,Object> dataMap = new HashMap<String, Object>();
                                //获取title对象的所有属性,属性名为key,属性值为value
                                putAttrToMap(i,dataMap);
                                List<Element> items = i.elements();
                                if(items!=null && !items.isEmpty()){
                                    List<Map<String,Object>> itemMaps = new ArrayList<Map<String,Object>>();
                                    for(Element si : items){
                                        Map<String,Object> itemMap = new HashMap<String, Object>();
                                        //获取节点的所有属性包装进Map
                                        putAttrToMap(si,itemMap);
                                        //以节点名称为key将节点文本包装进map
                                        putTextToMap(si,itemMap);
                                        itemMaps.add(itemMap);
                                    }
                                    dataMap.put("items",itemMaps);
                                }
                                dataMaps.add(dataMap);
                            }
                            return dataMaps;
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("", e);
            }
        }
        return null;
    }

    //获取节点的所有属性包装进Map
    private <T> void putAttrToMap(Element e,Map<String,T> map){
        if(e!=null && map!=null){
            List<?> attrs = e.attributes();
            if(attrs!=null && !attrs.isEmpty()){
                int attrSize = attrs.size();
                for(int index = 0 ; index < attrSize ; index++){
                    Attribute attr = (Attribute)attrs.get(index);
                    if(attr!=null){
                        String name = attr.getName();
                        String val = attr.getValue();
                        map.put(name,(T)val);
                    }
                }
            }
        }
    }

    //以节点名称为key将节点文本包装进map
    private <T> void putTextToMap(Element e,Map<String,T> map){
        if(e!=null && map!=null){
            List<Element> es = e.elements();
            if(es!=null && !es.isEmpty()){
                for(Element se : es){
                    String eleName = se.getName();
                    String eleText = se.getText();
                    map.put(eleName,(T)eleText);
                }
            }
        }
    }

    //根据request获取评分表单的评分校验
    private String checkGradeScore(List<Map<String,Object>> assessMaps,HttpServletRequest request){
        String res = "";
        if(request!=null){
            if(assessMaps!=null && !assessMaps.isEmpty()){
                //遍历配置获取用户填写的值
                for(Map<String,Object> map : assessMaps){
                    if(map!=null){
                        //标题内的子项
                        List<Map<String,Object>> items = (List<Map<String, Object>>)map.get("items");
                        if(items!=null && !items.isEmpty()){
                            for(Map<String,Object> item : items){
                                String itemId = (String)item.get("id");
                                String name = (String)item.get("name");
                                Float score = Float.valueOf((String) item.get("score"));
                                if(StringUtil.isNotBlank(itemId)){
                                    //获取分数并统计
                                    String evalScore = request.getParameter(itemId+"_score");
                                    if(StringUtil.isBlank(evalScore))
                                    {
                                        return "请为【"+name+"】评分项，进行打分";
                                    }
                                    Float scf = Float.valueOf(evalScore);
                                    if(scf>score)
                                    {
                                        return "【"+name+"】评分项最大分为"+score+"，不得超过此分数";
                                    }
                                }
                            }
                        }
                    }
                }
                return res;
            }
        }
        return res;
    }

    //根据request获取评分表单的xml
    private String getGradeXmlTwo(List<Map<String,Object>> assessMaps,HttpServletRequest request){
        if(request!=null){
            String rootName = "gradeInfo";
            if(assessMaps!=null && !assessMaps.isEmpty()){
                //创建xmldom对象和根节点
                Document doc = DocumentHelper.createDocument();
                Element root = doc.addElement(rootName);

                //计算总分
                Float totalScore = 0f;
                //遍历配置获取用户填写的值
                for(Map<String,Object> map : assessMaps){
                    if(map!=null){
                        //标题内的子项
                        List<Map<String,Object>> items = (List<Map<String, Object>>)map.get("items");
                        if(items!=null && !items.isEmpty()){
                            for(Map<String,Object> item : items){
                                String itemId = (String)item.get("id");
                                if(StringUtil.isNotBlank(itemId)){
                                    Element grade = root.addElement("grade");
                                    grade.addAttribute("assessId",itemId);

                                    //获取分数并统计
                                    String score = request.getParameter(itemId+"_score");
                                    Element scoreEle = grade.addElement("score");
                                    if(StringUtil.isNotBlank(score)){
                                        scoreEle.setText(score);
                                        Float scf = Float.valueOf(score);
                                        totalScore+=scf;
                                    }

//                                    String lostReason = request.getParameter(itemId+"_lostReason");
//                                    Element lostReasonEle = grade.addElement("lostReason");
//                                    if(StringUtil.isNotBlank(lostReason)){
//                                        lostReasonEle.setText(lostReason);
//                                    }
                                }
                            }
                        }
                    }
                }

                Element totalScoreEle = root.addElement("totalScore");
                totalScoreEle.setText(totalScore.toString());
                Element lostReasonEle = root.addElement("lostReason");
                if(null != request.getParameter("lostReason")){
                    lostReasonEle.setText(request.getParameter("lostReason"));
                }

                return doc.asXML();
            }
        }
        return null;
    }
}
