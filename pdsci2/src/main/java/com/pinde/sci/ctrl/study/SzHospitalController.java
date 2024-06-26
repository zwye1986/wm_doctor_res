package com.pinde.sci.ctrl.study;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.study.IHospitalManageBiz;
import com.pinde.sci.biz.study.IStudySubjectDetailBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.StudySubject;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xieshihai
 */
@Controller
@RequestMapping("/study/hospital")
public class SzHospitalController extends GeneralController {

    @Autowired
    private IHospitalManageBiz hospitalManageBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IStudySubjectDetailBiz subjectDetailBiz;

    @RequestMapping("/doctorManage")
    public String studentList(String userName,String sessionNumber,String idNo,Model model,Integer currentPage,
                              String trainingTypeId,String trainingSpeId, String statusId,HttpServletRequest request){
        SysUser currentUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("statusId",statusId);
        paramMap.put("userName",userName);
        paramMap.put("trainingTypeId",trainingTypeId);
        paramMap.put("trainingSpeId",trainingSpeId);
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("idNo",idNo);
        String flow= InitConfig.getSysCfg("study_doctor_role_flow");
        if(StringUtil.isNotBlank(flow)) {
            paramMap.put("flow",flow);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String, Object>> resultMapList = hospitalManageBiz.searchStudents(paramMap);
            model.addAttribute("resultMapList", resultMapList);
        }
        model.addAttribute("flow",flow);
        return "study/hospital/doctorList";
    }

    //打新增或编辑页面
    @RequestMapping("/editStudent")
    public String editStudent(String userFlow,Model model){
        SysUser user = userBiz.readSysUser(userFlow);
        model.addAttribute("user",user);
        if(user!=null){
            ResDoctor doctor = doctorBiz.readDoctor(user.getUserFlow());
            model.addAttribute("doctor",doctor);
        }
        List<SysOrg> orgList = orgBiz.queryAllSysOrg(null);
        model.addAttribute("orgList",orgList);
        model.addAttribute("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
        return "study/hospital/doctorInfo";
    }
    //编辑学员信息
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(SysUser user,ResDoctor doctor){
        if(StringUtil.isNotBlank(user.getUserFlow())){
            String userFlow = user.getUserFlow();
            //是否已注册
            String userEmail = user.getUserEmail().trim();
            SysUser sysUser = null;
            sysUser = userBiz.findByUserEmailNotSelf(userFlow,userEmail);
            if(sysUser != null){
                return GlobalConstant.USER_EMAIL_REPETE;
            }
            //判断用户身份证号是否重复
            sysUser = userBiz.findByIdNoNotSelf(userFlow,user.getIdNo());
            if (sysUser != null) {
                return  GlobalConstant.USER_ID_NO_REPETE;
            }
            //判断用户手机号是否重复
            sysUser = userBiz.findByUserPhoneNotSelf(userFlow,user.getUserPhone());
            if (sysUser != null) {
                return GlobalConstant.USER_PHONE_REPETE;
            }
            userBiz.edit(user);
            doctor.setDoctorFlow(user.getUserFlow());
            doctorBiz.editDoctor(doctor);
            return "操作成功";
        }else{
            //是否已注册
            String userEmail = user.getUserEmail().trim();
            SysUser sysUser = null;
            sysUser = userBiz.findByUserEmail(userEmail);
            if(sysUser != null){
                return GlobalConstant.USER_EMAIL_REPETE;
            }
            sysUser = userBiz.findByUserCode(userEmail);
            if(sysUser != null){
                return GlobalConstant.USER_EMAIL_REPETE;
            }
            //判断用户身份证号是否重复
            sysUser = userBiz.findByIdNo(user.getIdNo());
            if (sysUser != null) {
                return GlobalConstant.USER_ID_NO_REPETE;
            }
            //判断用户手机号是否重复
            sysUser = userBiz.findByUserPhone(user.getUserPhone());
            if (sysUser != null) {
                return GlobalConstant.USER_PHONE_REPETE;
            }
            hospitalManageBiz.saveUser(user,doctor);
        }
        return "操作成功";
    }

    //导出
    //打开学员管理列表
    @RequestMapping("/exportStudents")
    public void exportStudents(String userName,String sessionNumber,String idNo,Model model,HttpServletResponse response,
                               String trainingTypeId,String trainingSpeId, String statusId,HttpServletRequest request) throws Exception {
        SysUser currentUser = GlobalContext.getCurrentUser();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("statusId",statusId);
        paramMap.put("userName",userName);
        paramMap.put("trainingTypeId",trainingTypeId);
        paramMap.put("trainingSpeId",trainingSpeId);
        paramMap.put("sessionNumber",sessionNumber);
        paramMap.put("idNo",idNo);
        String flow= InitConfig.getSysCfg("study_doctor_role_flow");
        List<Map<String,Object>> resultMapList =null;

        if(StringUtil.isNotBlank(flow)) {
            paramMap.put("flow", flow);
            resultMapList = hospitalManageBiz.searchStudents(paramMap);
        }
        String[] titles = new String[]{
                "USER_NAME:姓名",
                "ORG_NAME:培训基地",
                "TRAINING_TYPE_NAME:培训类型",
                "TRAINING_SPE_NAME:培训专业",
                "SESSION_NUMBER:培训年级",
                "ID_NO:证件号码",
                "USER_EMAIL:邮箱",
                "USER_PHONE:手机号码",
                "WORK_ORG_NAME:所在单位"
        };
        String fileName = "学员信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, resultMapList, response.getOutputStream());
    }

    /**
     * 人员导入
     * @param file
     * @return
     */
    @RequestMapping(value="/importUsersFromExcel")
    @ResponseBody
    public String importUsersFromExcel(MultipartFile file){
        if(file.getSize() > 0){
            try{
                int result = hospitalManageBiz.importUserFromExcel(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }
    @RequestMapping("/subjectManage")
    public String subjectManage(Model model, StudySubject subject, Integer currentPage, HttpServletRequest request) {
        int pageSize = getPageSize(request);
        if(currentPage==null) currentPage=1;
        PageHelper.startPage(currentPage, pageSize);
        List<StudySubject> list = this.hospitalManageBiz.subjectList(subject);
        model.addAttribute("list", list);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageSize", pageSize);
        return "study/hospital/subjectList";
    }

    @RequestMapping("/exportSubjects")
    public void exportSubjects( StudySubject subject,HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<StudySubject> list = this.hospitalManageBiz.subjectList(subject);
        String[] titles = new String[]{
                "subjectFlow:序号",
                "subjectYear:课程年份",
                "subjectName:课程名称",
                "subjectStartTime:预约开始时间",
                "subjectEndTime:预约结束时间",
                "reservationsNum:预约容量",
                "subjectType:课程类型"
        };
        if(list!=null)
        {
            int i=1;
            for(StudySubject s:list)
            {
                s.setSubjectFlow(i+"");
                i++;
                if(StringUtil.isNotBlank(s.getSubjectType())){
                    s.setSubjectType(DictTypeEnum.CourseType.getDictNameById(s.getSubjectType()));
                }
            }
        }
        String fileName = "课程信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcle(titles, list,StudySubject.class, response.getOutputStream());
    }
    @RequestMapping("/subjectDetail")
    public String subjectDetail(String subjectFlow,String isEdit,Model model){
        StudySubject subject=hospitalManageBiz.readSubject(subjectFlow);
        model.addAttribute("subject",subject);
        return "study/hospital/subjectAdd";
    }
    @RequestMapping("/showSubject")
    public String showSubject(String subjectFlow,String isEdit,Model model)  throws Exception {
        StudySubject subject=hospitalManageBiz.readSubject(subjectFlow);
        int passNum = subjectDetailBiz.selectCountByAuditStatusId(subjectFlow);
        model.addAttribute("subject",subject);
        model.addAttribute("passNum",passNum);
        return "study/hospital/showSubject";
    }

    @RequestMapping("/detailInfoManage")
    public String detailInfoManage(String subjectFlow,String isEdit,Model model) {
        StudySubject subject=hospitalManageBiz.readSubject(subjectFlow);
        model.addAttribute("subject",subject);
        return "study/hospital/detailInfoManage";
    }

    @RequestMapping("/detailList")
    public String detailList(Integer currentPage1,String auditStatusId,String doctorName,String year,
                                  String subjectFlow,HttpServletRequest request,Model model,String isLocal) throws IOException {
        Map<String,String> param = new HashMap<>();
        param.put("auditStatusId",auditStatusId);
        param.put("doctorName",doctorName);
        param.put("subjectFlow",subjectFlow);
        PageHelper.startPage(currentPage1,getPageSize(request));
        List<Map<String,String>> list = hospitalManageBiz.detailList(param);
        model.addAttribute("list",list);
        return "study/hospital/detailList";
    }

    @RequestMapping("/exportDetailList")
    public void exportDetailList(String auditStatusId,String doctorName,String year,
                                 String subjectFlow,HttpServletRequest request,HttpServletResponse response) throws Exception {
        Map<String,String> param = new HashMap<>();
        param.put("auditStatusId",auditStatusId);
        param.put("doctorName",doctorName);
        param.put("subjectFlow",subjectFlow);
        List<Map<String,String>> list = hospitalManageBiz.detailList(param);
        String[] titles = new String[]{
                "userName:姓名",
                "idNo:证件号码",
                "sexName:性别",
                "sessionNumber:培训届别",
                "orgName:培训基地",
                "trainingTypeName:培训专业",
                "userPhone:联系方式",
                "orderTime:学员预约时间",
                "auditStatusName:状态"
        };
        String fileName = "预约学员信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, list, response.getOutputStream());
    }
    /**
     * 发布课程
     */
    @RequestMapping(value = "/saveSubject", method = RequestMethod.POST)
    public @ResponseBody String save(StudySubject subject) throws Exception {
        if(StringUtil.isNotBlank(subject.getSubjectFlow()))
        {
            StudySubject old=hospitalManageBiz.readSubject(subject.getSubjectFlow());
            if(old==null)
            {
                return "课程信息不存在，无法编辑，请刷新列表！";
            }
            if("N".equals(old.getRecordStatus()))
            {
                return "课程信息已删除，无法编辑，请刷新列表！";
            }
            if("Y".equals(old.getPostStatus()))
            {
                return "课程信息已发布，无法编辑，请刷新列表！";
            }
        }
        int result = hospitalManageBiz.addSubject(subject);
        if (GlobalConstant.ZERO_LINE != result) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
            return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping(value = "/delSubject", method = RequestMethod.POST)
    public @ResponseBody String delSubject(StudySubject subject) throws Exception {
        if(StringUtil.isNotBlank(subject.getSubjectFlow()))
        {
            StudySubject old=hospitalManageBiz.readSubject(subject.getSubjectFlow());
            if(old==null)
            {
                return "课程信息不存在，无法删除，请刷新列表！";
            }
            if("N".equals(old.getRecordStatus()))
            {
                return "课程信息已删除，无法删除，请刷新列表！";
            }
            if("Y".equals(old.getPostStatus()))
            {
                return "课程信息已发布，无法删除，请刷新列表！";
            }
        }
        subject.setRecordStatus("N");
        int result = hospitalManageBiz.addSubject(subject);
        if (GlobalConstant.ZERO_LINE != result) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
            return GlobalConstant.DELETE_FAIL;
    }
    @RequestMapping(value = "/release", method = RequestMethod.POST)
    public @ResponseBody String release(StudySubject subject) throws Exception {
        if(StringUtil.isNotBlank(subject.getSubjectFlow()))
        {
            StudySubject old=hospitalManageBiz.readSubject(subject.getSubjectFlow());
            if(old==null)
            {
                return "课程信息不存在，无法发布，请刷新列表！";
            }
            if("N".equals(old.getRecordStatus()))
            {
                return "课程信息已删除，无法发布，请刷新列表！";
            }
            if("Y".equals(old.getPostStatus()))
            {
                return "课程信息已发布，无法再次发布，请刷新列表！";
            }
        }
        subject.setPostStatus("Y");
        int result = hospitalManageBiz.addSubject(subject);
        if (GlobalConstant.ZERO_LINE != result) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
            return "发布失败";
    }

    @RequestMapping(value = {"/auditBack"},method = RequestMethod.POST)
    @ResponseBody
    public String auditBack(@RequestBody List<String> detailFlows) throws Exception {
        if(detailFlows.size()<=0)
        {
            return "请勾选预约学员信息!";
        }
        hospitalManageBiz.auditBack(detailFlows);
        return GlobalConstant.OPRE_SUCCESSED;
    }
    @RequestMapping(value = {"/auditPassed"},method = RequestMethod.POST)
    @ResponseBody
    public String auditPassed(@RequestBody List<String> detailFlows) throws Exception {
        if(detailFlows.size()<=0)
        {
            return "请勾选预约学员信息!";
        }
        hospitalManageBiz.auditPassed(detailFlows);
        return GlobalConstant.OPRE_SUCCESSED;
    }
    @RequestMapping(value = {"/auditUnPassed"},method = RequestMethod.POST)
    @ResponseBody
    public String auditUnPassed(@RequestBody List<String> detailFlows) throws Exception {
        if(detailFlows.size()<=0)
        {
            return "请勾选预约学员信息!";
        }
        hospitalManageBiz.auditUnPassed(detailFlows);
        return GlobalConstant.OPRE_SUCCESSED;
    }
}
