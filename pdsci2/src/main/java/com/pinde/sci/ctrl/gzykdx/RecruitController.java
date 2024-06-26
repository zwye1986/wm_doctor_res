package com.pinde.sci.ctrl.gzykdx;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxRecruitBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.gzykdx.GzykdxDegreeTypeEnum;
import com.pinde.sci.form.gzykdx.GzykdxRecruitExtInfoForm;
import com.pinde.sci.form.gzykdx.GzykdxTeacherTargetApplyExportForm;
import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.TeacherTargetApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LittleSheep
 *
 */
@Controller
@RequestMapping("/gzykdx/recruit")
public class RecruitController extends GeneralController {
    @Autowired
    IGzykdxRecruitBiz recruitBiz;
    @Autowired
    IUserBiz userBiz;
    @Autowired
    IPubUserResumeBiz pubUserResumeBiz;
    @Autowired
    IOrgBiz orgBiz;
    @Autowired
    IDictBiz dictBiz;

    @RequestMapping("/reexamineList")
    public String reexamineList(SysUser user, Integer currentPage, HttpServletRequest request, Model model,
                                String speId,String researchAreaName,String degreeTypeId,String year){
        Map<String,String> paramMap = new HashMap<>();
        if(user!=null){
            paramMap.put("userCode",user.getUserCode());
            paramMap.put("userName",user.getUserName());
            paramMap.put("isOwnerStu",user.getIsOwnerStu());
        }
        paramMap.put("speId",speId);
        paramMap.put("researchAreaName",researchAreaName);
        paramMap.put("degreeTypeId",degreeTypeId);
        if(StringUtil.isBlank(year)){
            year = DateUtil.getYear();
            model.addAttribute("thisYear",year);
        }
        paramMap.put("year",year);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> reexamineList = recruitBiz.searchReexamineStudent(paramMap);
        Map<String,String> globMap = new HashMap<>();
        Map<String,String> phoneMap = new HashMap<>();
        Map<String,String> fshMap = new HashMap<>();
        if(reexamineList!=null&&reexamineList.size()>0){
            for(Map<String,Object> student:reexamineList){
                String userFlow = (String)student.get("userFlow");
                String xml = (String)student.get("userResume");
                if(StringUtil.isNotBlank(xml)) {
                    GzykdxRecruitExtInfoForm recruitExtInfoForm = JaxbUtil.converyToJavaBean(xml, GzykdxRecruitExtInfoForm.class);
                    if (recruitExtInfoForm != null) {
                        String bydw = recruitExtInfoForm.getBydw();
                        String yddh = recruitExtInfoForm.getYddh();
                        String fsh = recruitExtInfoForm.getFsh();
                        globMap.put(userFlow, bydw);
                        phoneMap.put(userFlow, yddh);
                        fshMap.put(userFlow, fsh);
                    }
                }
            }
        }
        model.addAttribute("reexamineList",reexamineList);
        model.addAttribute("globMap",globMap);
        model.addAttribute("phoneMap",phoneMap);
        model.addAttribute("fshMap",fshMap);
        return "gzykdx/recruitManage/reexamineList";
    }

    //学员信息导入
    @RequestMapping(value="/importReexamStudentExcel")
    @ResponseBody
    public String importStudentExcel(MultipartFile file){
        if(file.getSize() > 0){
            try{
                int result = recruitBiz.importReexamStudentExcel(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re) {
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    //打开复试考生详情
    @RequestMapping(value="/getDetail")
    public String getDetail(String userFlow,Model model){
        SysUser user = userBiz.readSysUser(userFlow);
        model.addAttribute("user",user);
        PubUserResume userResume = pubUserResumeBiz.readPubUserResume(userFlow);
        String xml = userResume.getUserResume();
        GzykdxRecruitExtInfoForm recruitExtInfoForm = null;
        if(StringUtil.isNotBlank(xml)) {
            recruitExtInfoForm = JaxbUtil.converyToJavaBean(xml, GzykdxRecruitExtInfoForm.class);
        }
        model.addAttribute("extForm", recruitExtInfoForm);
        return "gzykdx/recruitManage/reexamineStudentDetail";
    }

    //录取缺额信息查询
    @RequestMapping("/recruitInfoList")
    public String recruitInfoList(String year,String org,Model model,Integer currentPage,HttpServletRequest request){
        Map<String,String> paramMap = new HashMap<>();
        String thisYear = DateUtil.getYear();
        if(!StringUtil.isNotBlank(year)){
            year = thisYear;
        }
        paramMap.put("year",year);
        paramMap.put("orgFlow",org);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> recruitInfoList = recruitBiz.vacanciesQuery(paramMap);
        Map<String,Integer> academicSumMap = new HashMap<>();
        Map<String,Integer> specializedSumMap = new HashMap<>();
        if(recruitInfoList!=null&&recruitInfoList.size()>0){
            for(Map<String,Object> info:recruitInfoList){
                String orgFlow = (String)info.get("ORG_FLOW");
                TeacherTargetApply teacherTargetApply = new TeacherTargetApply();
                teacherTargetApply.setOrgFlow(orgFlow);
                List<TeacherTargetApply> teacherTargetApplyList = recruitBiz.searchTeacherTargetApply(teacherTargetApply);
                int academicSum = 0;
                int specializedSum = 0;
                if(teacherTargetApplyList!=null&&teacherTargetApplyList.size()>0){
                    for(TeacherTargetApply teacherTargetApply1 :teacherTargetApplyList){
                        String academicNum = StringUtil.defaultIfEmpty(teacherTargetApply1.getAcademicRecruitNum(),"0");
                        academicSum += Integer.parseInt(academicNum);
                        String specializedNum = StringUtil.defaultIfEmpty(teacherTargetApply1.getSpecializedRecruitNum(),"0");
                        specializedSum += Integer.parseInt(specializedNum);
                    }
                }
                academicSumMap.put(orgFlow,academicSum);
                specializedSumMap.put(orgFlow,specializedSum);
            }
        }
        model.addAttribute("academicSumMap",academicSumMap);
        model.addAttribute("specializedSumMap",specializedSumMap);

        //查询所有二级机构
        SysOrg org1 = new SysOrg();
        org1.setIsSecondFlag("Y");
        List<SysOrg> orgList = orgBiz.searchOrg(org1);
        model.addAttribute("recruitInfoList",recruitInfoList);
        model.addAttribute("thisYear",thisYear);
        model.addAttribute("orgList",orgList);
        return "gzykdx/recruitManage/recruitInfoList";
    }

    //录取详情
    @RequestMapping("/recruitDetail")
    public String recruitDetail(String orgFlow,String degreeTypeId,String year,Model model,
                                Integer currentPage,HttpServletRequest request){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("year",year);
        paramMap.put("orgFlow",orgFlow);
        paramMap.put("degreeTypeId",degreeTypeId);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> recruitDetailList = recruitBiz.teacherRecruitInfo(paramMap);
        model.addAttribute("recruitDetailList",recruitDetailList);
        return "gzykdx/recruitManage/recruitDetail";
    }


    //缺额详情
    @RequestMapping("/vacanciesDetail")
    public String vacanciesDetail(String orgFlow,String degreeTypeId,String year,Model model,
                                Integer currentPage,HttpServletRequest request){
        TeacherTargetApply teacherTargetApply = new TeacherTargetApply();
        teacherTargetApply.setRecruitYear(year);
        teacherTargetApply.setOrgFlow(orgFlow);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<TeacherTargetApply> teacherTargetApplyList =  recruitBiz.searchTeacherTargetApply(teacherTargetApply);
        model.addAttribute("teacherTargetApplyList",teacherTargetApplyList);
        model.addAttribute("degreeTypeId",degreeTypeId);
        return "gzykdx/recruitManage/vacanciesDetail";
    }

    //二级单位招生计划详情
    @RequestMapping("/secondaryUnitsRecruitInfo")
    public String secondaryUnitsRecruitInfo(String year,Model model,Integer currentPage,HttpServletRequest request){
        Map<String,String> paramMap = new HashMap<>();
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        String thisYear = DateUtil.getYear();
        if(!StringUtil.isNotBlank(year)){
            year = thisYear;
        }
        paramMap.put("year",year);
        paramMap.put("orgFlow",orgFlow);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> secondaryUnitsRecruitInfoList = recruitBiz.searchSecondaryRecriutInfo(paramMap);
        model.addAttribute("thisYear",thisYear);
        model.addAttribute("secondaryUnitsRecruitInfoList",secondaryUnitsRecruitInfoList);
        return "gzykdx/recruitManage/secondaryUnitsRecruitInfoList";
    }

    //导师招生目录查询
    @RequestMapping("/teacherTargetApplyList")
    public String teacherApplyList(TeacherTargetApply teacherTargetApply,String degreeTypeId,Model model,Integer currentPage,HttpServletRequest request){
        String thisYear = DateUtil.getYear();
        if(!StringUtil.isNotBlank(teacherTargetApply.getRecruitYear())){
            teacherTargetApply.setRecruitYear(thisYear);
        }
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        teacherTargetApply.setOrgFlow(orgFlow);
        if(GzykdxDegreeTypeEnum.AcademicType.getId().equals(degreeTypeId)){
            teacherTargetApply.setIsAcademic("Y");
        }
        if(GzykdxDegreeTypeEnum.ProfessionalType.getId().equals(degreeTypeId)){
            teacherTargetApply.setIsSpecialized("Y");
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<TeacherTargetApply> teacherTargetApplyList = recruitBiz.searchTeacherTargetApply(teacherTargetApply);
        Map<String,String> courseMap = new HashMap<>();
        if(teacherTargetApplyList!=null&&teacherTargetApplyList.size()>0){
            for(TeacherTargetApply teacherTargetApply1:teacherTargetApplyList){
                String applyFlow = teacherTargetApply1.getApplyFlow();
                String desc = "";
                if(dictBiz.readDict("GzykdxSpe",teacherTargetApply1.getSpeId())!=null){
                    desc = dictBiz.readDict("GzykdxSpe",teacherTargetApply1.getSpeId()).getDictDesc();
                }
                courseMap.put(applyFlow,desc);
            }
        }
        model.addAttribute("courseMap",courseMap);
        model.addAttribute("thisYear",thisYear);
        model.addAttribute("teacherTargetApplyList",teacherTargetApplyList);
        return "gzykdx/recruitManage/teacherTargetApplyList";
    }

    //招生目录查询导出
    @RequestMapping("/exportTeacherTargetApplyList")
    public void exportTeacherTargetApplyList(TeacherTargetApply teacherTargetApply, String degreeTypeId,
                      Integer currentPage, HttpServletRequest request, HttpServletResponse response)throws Exception{
        String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
        teacherTargetApply.setOrgFlow(orgFlow);
        if(GzykdxDegreeTypeEnum.AcademicType.getId().equals(degreeTypeId)){
            teacherTargetApply.setIsAcademic("Y");
        }
        if(GzykdxDegreeTypeEnum.ProfessionalType.getId().equals(degreeTypeId)){
            teacherTargetApply.setIsSpecialized("Y");
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        List<TeacherTargetApply> teacherTargetApplyList = recruitBiz.searchTeacherTargetApply(teacherTargetApply);
        List<GzykdxTeacherTargetApplyExportForm> gzykdxTeacherTargetApplyExportForms = new ArrayList<>();
        if(teacherTargetApplyList!=null&&teacherTargetApplyList.size()>0){
            for(TeacherTargetApply teacherTargetApply1:teacherTargetApplyList){
                GzykdxTeacherTargetApplyExportForm gzykdxTeacherTargetApplyExportForm = new GzykdxTeacherTargetApplyExportForm();
                String desc = "";
                if(dictBiz.readDict("GzykdxSpe",teacherTargetApply1.getSpeId())!=null){
                    desc = dictBiz.readDict("GzykdxSpe",teacherTargetApply1.getSpeId()).getDictDesc();
                }
                String degreeTypeName = "";
                if(StringUtil.isNotBlank(teacherTargetApply1.getIsAcademic())){
                    degreeTypeName += "学术型";
                }
                if(StringUtil.isNotBlank(teacherTargetApply1.getIsSpecialized())){
                    degreeTypeName += "专业型";
                }
                gzykdxTeacherTargetApplyExportForm.setCourseList(desc);
                gzykdxTeacherTargetApplyExportForm.setTeacherTargetApply(teacherTargetApply1);
                gzykdxTeacherTargetApplyExportForm.setDegreeTypeName(degreeTypeName);
                gzykdxTeacherTargetApplyExportForms.add(gzykdxTeacherTargetApplyExportForm);
            }
        }
        String[] titles = new String[]{
                "teacherTargetApply.recruitYear:年份",
                "teacherTargetApply.speId:专业代码",
                "teacherTargetApply.speName:专业名称",
                "teacherTargetApply.researchDirection:研究方向",
                "teacherTargetApply.userName:导师",
                "courseList:考试科目",
                "teacherTargetApply.academicNum:学术学位拟招生人数",
                "teacherTargetApply.specializedNum:专业学位拟招生人数",
                "degreeTypeName:学位类型"
        };
        String fileName = "招生目录.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        ExcleUtile.exportSimpleExcleByObjs(titles, gzykdxTeacherTargetApplyExportForms, response.getOutputStream());
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
