package com.pinde.res.ctrl.gydxj;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gydxj.IGydxjAdminBiz;
import com.pinde.res.biz.gydxj.IGydxjAppBiz;
import com.pinde.res.biz.gydxj.IGydxjStudentBiz;
import com.pinde.res.biz.gydxj.IGydxjTeacherBiz;
import com.pinde.res.enums.gydxj.XjglCourseTypeEnum;
import com.pinde.res.enums.lcjn.DictTypeEnum;
import com.pinde.res.model.gydxj.mo.XjEduUserExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("/res/gydxj/admin")
public class GydxjAdminController {
    private static Logger logger = LoggerFactory.getLogger(GydxjAdminController.class);
    @Autowired
    private IGydxjAppBiz appBiz;
    @Autowired
    private IGydxjAdminBiz admBiz;
    @Autowired
    private IGydxjStudentBiz stuBiz;
    @Autowired
    private IGydxjTeacherBiz teaBiz;

    @ExceptionHandler(Throwable.class)
    public String exception(Throwable e) {
        logger.error(this.getClass().getCanonicalName() + " some error happened", e);
        return "res/gydxj/500";
    }

    @RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
    public String test() {
        return "res/gydxj/admin/test";
    }

    @RequestMapping("/eduUserList")
    public String eduUserList(String searchStr,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchStr", searchStr);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> eduUserList = admBiz.searchEduUser(paramMap);
        model.addAttribute("eduUserList", eduUserList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/admin/eduUserList";
    }

    @RequestMapping("/partStatusList")
    public String partStatusList(String userFlow,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        //信息确认状态
        List<EduUserInfoStatus> statusLst = appBiz.searchPartStatus(userFlow);
        Map<String,String> statusMap=new HashMap<>();
        if(null != statusLst && statusLst.size() > 0){
            for(EduUserInfoStatus infoStatus:statusLst){
                statusMap.put(infoStatus.getPartId(),infoStatus.getPartStatus());
            }
        }
        model.addAttribute("statusMap", statusMap);
        return "res/gydxj/admin/partStatusList";
    }

    @RequestMapping("/backConfirm")
    public String backConfirm(String userFlow,String partId,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(userFlow) || StringUtil.isBlank(partId)){
            model.addAttribute("resultId", "3010107");
            model.addAttribute("resultType", "所需参数出错");
            return "res/gydxj/backResult";
        }
        admBiz.backConfirm(userFlow,partId);
        return "res/gydxj/backResult";
    }

    @RequestMapping("/gradeList")
    public String gradeList(String searchStr,String roleId,String auditFlag,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchStr", searchStr);
        paramMap.put("roleId", roleId);
        paramMap.put("auditFlag", auditFlag);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> gradeList = admBiz.getGradeAuditStus(paramMap);
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/admin/gradeList";
    }

    @RequestMapping("/gradeDetail")
    public String gradeDetail(String recordFlow,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("recordFlow", recordFlow);
        List<Map<String,Object>> gradeList = admBiz.getGradeAuditStus(paramMap);
        if(null!=gradeList&&!gradeList.isEmpty()){
            model.addAttribute("grade", gradeList.get(0));
        }
        return "res/gydxj/admin/gradeDetail";
    }

    @RequestMapping("/auditGrade")
    public String auditGrade(String recordFlow,String auditStatusId,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        admBiz.auditGrade(recordFlow,auditStatusId);
        return "res/gydxj/backResult";
    }


    @RequestMapping("/courseList")
    public String courseList(String searchStr,String year,String gradationId,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchStr",searchStr);
        if(StringUtil.isBlank(year)){
            year = DateUtil.getYear();
            if(admBiz.courseByYear(year)<=0){
                year = Integer.valueOf(year)-1+"";
            }
        }
        paramMap.put("year",year);
        paramMap.put("gradationId",gradationId);
        PageHelper.startPage(pageIndex, pageSize);
        List<EduCourse> gradeList = admBiz.searchCourseList(paramMap);
        model.addAttribute("courseList", gradeList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/admin/courseList";
    }

    @RequestMapping(value={"/courseEdit"},method={RequestMethod.POST})
    public String courseEdit(String courseFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        EduCourse course = admBiz.searchCourseByFlow(courseFlow);
        model.addAttribute("course",course);
        return "res/gydxj/admin/courseEdit";
    }

    @RequestMapping(value={"/courseSave"},method={RequestMethod.POST})
    public String courseSave(EduCourse course,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isNotBlank(course.getGradationId())){
            course.setGradationName(DictTypeEnum.GyTrainType.getDictNameById(course.getGradationId()));
        }
        if(StringUtil.isNotBlank(course.getCourseMoudleId())){
            course.setCourseMoudleName(DictTypeEnum.GyXjCourseMoudle.getDictNameById(course.getCourseMoudleId()));
        }
        if(StringUtil.isNotBlank(course.getCourseTypeId())){
            course.setCourseTypeName(XjglCourseTypeEnum.getNameById(course.getCourseTypeId()));
        }
        admBiz.saveCourse(course);
        return "res/gydxj/backResult";
    }

    @RequestMapping("/eduTermList")
    public String eduTermList(String searchStr,String year,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchStr",searchStr);
        if(StringUtil.isBlank(year)){
            year = DateUtil.getYear();
            if(admBiz.termByYear(year)<=0){
                year = Integer.valueOf(year)-1+"";
            }
        }
        paramMap.put("year",year);
        PageHelper.startPage(pageIndex, pageSize);
        List<EduTerm> termList = admBiz.searchTermList(paramMap);
        model.addAttribute("termList", termList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/admin/eduTermList";
    }

    @RequestMapping("/dossierEmployList")
    public String dossierEmployList(String searchStr,String confirmFlag,String partId,Integer pageIndex,Integer pageSize,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchStr", searchStr);
        paramMap.put("confirmFlag", confirmFlag);
        paramMap.put("partId", partId);
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> eduUserList = admBiz.searchEduUser(paramMap);
        model.addAttribute("eduUserList", eduUserList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/admin/eduUserList";
    }

    @RequestMapping("/dossierEmployEdit")
    public String dossierEmployEdit(String userFlow,String roleId,Model model) {
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(userFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "用户流水号为空");
            return "res/gydxj/admin/dossierEmployEdit";
        }
        model.addAttribute("roleId",roleId);
        EduUser eduUser=appBiz.readEduUser(userFlow);
        model.addAttribute("eduUser", eduUser);
        String content = eduUser.getContent();
        XjEduUserExtInfoForm extInfoForm = stuBiz.parseExtInfoXml(content);
        model.addAttribute("extInfoForm", extInfoForm);
        SysUser sysUser=appBiz.readSysUser(userFlow);
        model.addAttribute("sysUser", sysUser);
        return "res/gydxj/admin/dossierEmployEdit";
    }

    @RequestMapping(value={"/tutorList"},method={RequestMethod.POST})
    public String tutorList(String searchStr,Integer pageIndex,Integer pageSize,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("searchStr", searchStr);
        PageHelper.startPage(pageIndex, pageSize);
        List<NydsOfficialDoctor> tutorList = admBiz.searchTutorList(paramMap);
        model.addAttribute("tutorList",tutorList);
        model.addAttribute("dataCount", PageHelper.total);
        return "res/gydxj/admin/tutorList";
    }

    @RequestMapping(value={"/tutorEdit"},method={RequestMethod.POST})
    public String tutorEdit(String doctorFlow,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        NydsOfficialDoctor tutor = admBiz.searchTutorByFlow(doctorFlow);
        model.addAttribute("tutor",tutor);
        return "res/gydxj/admin/tutorEdit";
    }

    @RequestMapping(value={"/tutorSave"},method={RequestMethod.POST})
    public String tutorSave(NydsOfficialDoctor tutor,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        admBiz.saveTutor(tutor);
        return "res/gydxj/backResult";
    }

    @RequestMapping(value={"/queryOutline"},method={RequestMethod.POST})
    public String queryOutline(String courseFlow,String typeId,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(courseFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "课程流水号为空");
            return "res/gydxj/admin/outLine";
        }
        model.addAttribute("typeId",typeId);
        model.addAttribute("eduCourse",appBiz.readEduCourse(courseFlow));
        return "res/gydxj/admin/outLine";
    }

    @RequestMapping(value={"/editOutline"},method={RequestMethod.POST})
    public String editOutline(String courseFlow,String typeId,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isEmpty(courseFlow)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "课程流水号为空");
            return "res/gydxj/admin/editOutline";
        }
        model.addAttribute("typeId",typeId);
        model.addAttribute("eduCourse",appBiz.readEduCourse(courseFlow));
        return "res/gydxj/admin/editOutLine";
    }

    @RequestMapping(value={"/saveOutline"},method={RequestMethod.POST})
    public String saveOutline(EduCourse course,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(course.getTeachingContent())&&StringUtil.isBlank(course.getOutlineContent())){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "内容不能为空");
            return "res/gydxj/backResult";
        }
        admBiz.saveCourse(course);
        return "res/gydxj/backResult";
    }

    @RequestMapping(value={"/qrCode"},method={RequestMethod.POST})
    public String qrCode(String barCode,String roleId,Model model){
        model.addAttribute("resultId", "200");
        model.addAttribute("resultType", "交易成功");
        if(StringUtil.isBlank(barCode)){
            model.addAttribute("resultId", "3010101");
            model.addAttribute("resultType", "条形码编号不存在");
            return "res/gydxj/backResult";
        }
        if(!"Admin".equals(roleId)){
            model.addAttribute("resultId", "3010102");
            model.addAttribute("resultType", "该角色无法调档");
            return "res/gydxj/backResult";
        }
        EduUser eduUser=teaBiz.searEduUser(barCode);
        if(null != eduUser){
            //变更接受状态
            EduUser user = new EduUser();
            user.setUserFlow(eduUser.getUserFlow());
            user.setReceiveFlag(GlobalConstant.FLAG_Y);
            appBiz.saveEduUser(user);
            String content = eduUser.getContent();
            XjEduUserExtInfoForm extInfoForm = stuBiz.parseExtInfoXml(content);
            model.addAttribute("extInfoForm", extInfoForm);
            SysUser sysUser=appBiz.readSysUser(eduUser.getUserFlow());
            eduUser.setReceiveFlag(GlobalConstant.FLAG_Y);
            model.addAttribute("eduUser", eduUser);
            model.addAttribute("sysUser", sysUser);
            return "res/gydxj/admin/dossierEmployEdit";
        }
        model.addAttribute("resultId", "3010103");
        model.addAttribute("resultType", "当前编码不存在");
        return "res/gydxj/backResult";
    }
}

