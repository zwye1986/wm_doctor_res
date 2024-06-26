package com.pinde.sci.ctrl.xjgl;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.xjgl.IXjEduStudentCourseBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.XjImpTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduStudentCourseExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangs
 * @Copyright njpdxx.com
 * @since 2018/1/15
 */
@Controller
@RequestMapping("/xjgl/assumeOrg")
public class XjglAssumeOrgController extends GeneralController {
    @Autowired
    private IXjEduStudentCourseBiz studentCourseBiz;
    //成绩管理
    @RequestMapping(value = "/gradeAuditCddw")
    public String gradeAuditCddw(Integer currentPage, HttpServletRequest request, String courseSession, String courseFlow, Model model) {
        Map<String,String> parMp = new HashMap<>();
        parMp.put("courseSession",courseSession);
        parMp.put("courseFlow",courseFlow);
        parMp.put("assumeOrgFlow", GlobalContext.getCurrentUser().getOrgFlow());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> dataList = studentCourseBiz.getGradeAuditsCddw(parMp);
        model.addAttribute("dataList",dataList);
        model.addAttribute("assumeOrgFlow",GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("roleFlag","cddw");
        return "xjgl/assumeOrg/gradeAuditCddw";
    }
    //一键审核通过
    @RequestMapping(value="/auditAll")
    @ResponseBody
    public String auditAll(String [] courseCodeList){
        int count = 0;
        List<String> records = Arrays.asList(courseCodeList);
        for(int i=0;i<records.size();i++){
            count += studentCourseBiz.auditSigleGroupGradeCddw(records.get(i));
        }
        if(count > 0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return  GlobalConstant.OPERATE_FAIL;
    }

    @RequestMapping(value = "/selectGradeInfoList")
    public String selectGradeInfoList(Integer currentPage, EduUser eduUser, SysUser sysUser, ResDoctor doctor, EduStudentCourse studentCourse,String flag,
                                                Model model, HttpServletRequest request, String from,String scoreSum,String degreeGrade,String courseTypeScore) {
        PageHelper.startPage(currentPage, getPageSize(request));
        SysUser user = GlobalContext.getCurrentUser();

        List<XjEduStudentCourseExt> recordList = studentCourseBiz.searchGradeInfos(sysUser, eduUser, doctor, studentCourse,scoreSum,degreeGrade,courseTypeScore,user.getOrgFlow());
        model.addAttribute("recordList", recordList);
//        PubImportRecord importRecord = new PubImportRecord();
//        importRecord.setImpTypeId(XjImpTypeEnum.EduStudentCourse.getId());
//        importRecord.setRoleFlag("admin");
//        List<PubImportRecord> importRecords = importRecordBiz.searchImportRecordList(importRecord);
//        model.addAttribute("importRecords", importRecords);
//        List<SysOrg> orgList = orgBiz.searchHbresOrgList();
//        model.addAttribute("orgList", orgList);
        return "xjgl/assumeOrg/gradeInfoList";

    }
}
