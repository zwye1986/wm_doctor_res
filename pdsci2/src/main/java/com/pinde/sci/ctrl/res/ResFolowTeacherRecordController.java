package com.pinde.sci.ctrl.res;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResFolowTeacherRecordBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.res.DiscipleStatusEnum;
import com.pinde.sci.model.mo.ResDiscipleRecordInfo;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResStudentDiscipleTeacher;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.res.ResDoctorExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 跟师记录Controller
 * Created by pdkj on 2016/10/12.
 */
@Controller
@RequestMapping("/res/folowTeacherRecord")
public class ResFolowTeacherRecordController extends GeneralController {
    @Autowired
    private IResFolowTeacherRecordBiz resFolowTeacherRecordBiz;

    @Autowired
    private IResDoctorBiz doctorBiz;
    /**
     * 学员查询跟师记录
     * @param model
     * @param currentPage
     * @param request
     * @return
     */
    @RequestMapping(value="showFollowTeacherRecord")
    public String showFollowTeacherRecord(Model model, Integer currentPage, HttpServletRequest request,String doctorFlow,String roleId,Integer teacherCurrentPage,String teacherFlow){
        SysUser sysUser = GlobalContext.getCurrentUser();
        List<ResDiscipleRecordInfo> resDiscipleRecordInfos = null;
        ResDiscipleRecordInfo resDiscipleRecordInfo = new ResDiscipleRecordInfo();
        if (StringUtil.isNotBlank(doctorFlow)) {
            resDiscipleRecordInfo.setDoctorFlow(doctorFlow);
        }else {
            resDiscipleRecordInfo.setDoctorFlow(sysUser.getUserFlow());
        }
        if("teacher".equals(roleId)){
            resDiscipleRecordInfo.setTeacherFlow(sysUser.getUserFlow());
            resDiscipleRecordInfo.setAuditStatusId(DiscipleStatusEnum.PendingAudit.getId());
            resDiscipleRecordInfo.setAuditStatusName(DiscipleStatusEnum.PendingAudit.getName());
            model.addAttribute("doctorFlow",doctorFlow);
            model.addAttribute("teacherCurrentPage",teacherCurrentPage);
        }
        if("see".equals(roleId)||"adminSee".equals(roleId)){
            if("teacher".equals(teacherFlow)){
                resDiscipleRecordInfo.setTeacherFlow(sysUser.getUserFlow());
            }
            resDiscipleRecordInfo.setAuditStatusId(DiscipleStatusEnum.Submit.getId());
            resDiscipleRecordInfo.setAuditStatusName(DiscipleStatusEnum.Submit.getName());
            model.addAttribute("doctorFlow",doctorFlow);
            model.addAttribute("teacherCurrentPage",teacherCurrentPage);
        }
        if("globalSee".equals(roleId)){
            if("teacher".equals(teacherFlow)){
                resDiscipleRecordInfo.setTeacherFlow(sysUser.getUserFlow());
            }
            resDiscipleRecordInfo.setAuditStatusId(DiscipleStatusEnum.Qualified.getId());
            resDiscipleRecordInfo.setAuditStatusName(DiscipleStatusEnum.Qualified.getName());
            model.addAttribute("doctorFlow",doctorFlow);
            model.addAttribute("teacherCurrentPage",teacherCurrentPage);
        }
        PageHelper.startPage(currentPage,getPageSize(request));
        resDiscipleRecordInfos = resFolowTeacherRecordBiz.searchFolowTeacherRecord(resDiscipleRecordInfo);
        if("teacher".equals(roleId)&&(resDiscipleRecordInfos==null||resDiscipleRecordInfos.size()==0)){
            return "redirect:/res/folowTeacherRecord/auditFollowTeacherRecord";
        }
        model.addAttribute("resDiscipleRecordInfos",resDiscipleRecordInfos);
        if(roleId==null){
            roleId="";
        }
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("roleId",roleId);
        return "res/disciple/followTeacherRecord";
    }

    /**
     * 显示保存编辑跟师记录界面
     * @param model
     * @param recordFlow
     * @return
     */
    @RequestMapping(value="showSaveFollowTeacherRecord"  )
    public String showSaveFollowTeacherRecord(Model model,String recordFlow){
        if(StringUtil.isNotBlank(recordFlow)){
            ResDiscipleRecordInfo resDiscipleRecordInfo = new ResDiscipleRecordInfo();
            resDiscipleRecordInfo.setRecordFlow(recordFlow);
            resDiscipleRecordInfo = resFolowTeacherRecordBiz.searchFolowTeacherRecord(resDiscipleRecordInfo).get(0);
            model.addAttribute("resDiscipleRecordInfo",resDiscipleRecordInfo);
        }
        return "res/disciple/followTeacherRecordAdd";
    }

    /**
     * 保存跟师记录
     * @param model
     * @param resDiscipleRecordInfo
     * @return
     */
    @RequestMapping(value="saveFollowTeacherRecord"  )
    public @ResponseBody String saveFollowTeacherRecord(Model model, ResDiscipleRecordInfo resDiscipleRecordInfo){
        SysUser sysUser = GlobalContext.getCurrentUser();
        ResDoctor resdoctor=doctorBiz.readDoctor(sysUser.getUserFlow());
            resDiscipleRecordInfo.setDoctorFlow(sysUser.getUserFlow());
            resDiscipleRecordInfo.setDoctorName(sysUser.getUserName());
            resDiscipleRecordInfo.setTeacherFlow(resdoctor.getDiscipleTeacherFlow());
            resDiscipleRecordInfo.setTeacherName(resdoctor.getDiscipleTeacherName());
            resDiscipleRecordInfo.setAuditStatusId(DiscipleStatusEnum.PendingAudit.getId());
            resDiscipleRecordInfo.setAuditStatusName(DiscipleStatusEnum.PendingAudit.getName());
            resDiscipleRecordInfo.setRecordYear(resDiscipleRecordInfo.getDiscipleDate().substring(0,4));
            int count=resFolowTeacherRecordBiz.saveResDiscipleRecordInfo(resDiscipleRecordInfo);
            if(count==0)
            {
                return  "0";
            }
            return "1";
    }

    /**
     * 删除跟师记录
     * @param recordFlow
     * @return
     */
    @RequestMapping(value="removeFollowTeacherRecord")
    public @ResponseBody String removeFollowTeacherRecord(String recordFlow){
        ResDiscipleRecordInfo resDiscipleRecordInfo = new ResDiscipleRecordInfo();
        resDiscipleRecordInfo.setRecordFlow(recordFlow);
        resDiscipleRecordInfo = resFolowTeacherRecordBiz.searchFolowTeacherRecord(resDiscipleRecordInfo).get(0);
        if(StringUtil.isNotBlank(recordFlow)){
            resDiscipleRecordInfo.setRecordStatus(GlobalConstant.FLAG_N);
            int count =resFolowTeacherRecordBiz.saveResDiscipleRecordInfo(resDiscipleRecordInfo);
            if(count==0)
            {
                return  GlobalConstant.DELETE_FAIL;
            }
            return GlobalConstant.OPERATE_SUCCESSED;
        }else {
            return GlobalConstant.DELETE_FAIL;
        }
    }

    /**
     * 展示师承老师审核跟师记录
     * @param model
     * @param currentPage
     * @param request
     * @return
     */
    @RequestMapping(value="auditFollowTeacherRecord")
    public String auditFollowTeacherRecord(Model model, String sessionNumber,String speId, Integer currentPage, HttpServletRequest request,
                                           String doctorName, String doctorCategoryId,String [] datas)throws Exception{
        String dataStr = "";
        List<String> docTypeList = new ArrayList<>();
        if(datas!=null&&datas.length>0){
            docTypeList = Arrays.asList(datas);
            for(String d : datas){
                dataStr += d+",";
            }
        }
        model.addAttribute("dataStr",dataStr);
        SysUser sysUser = GlobalContext.getCurrentUser();
        List<ResDoctorExt> resStudentDiscipleTeachers = null;
        ResStudentDiscipleTeacher resStudentDiscipleTeacher = new ResStudentDiscipleTeacher();
        resStudentDiscipleTeacher.setTeacherFlow(sysUser.getUserFlow());
        resStudentDiscipleTeacher.setDoctorName(doctorName);
//        PageHelper.startPage(currentPage,getPageSize(request));
//        Map<String,String> beMap = new HashMap<>();
//        if(StringUtil.isNotBlank(doctorName)){
//            doctorName = java.net.URLDecoder.decode(doctorName,"UTF-8");
//            model.addAttribute("doctorName",doctorName);
//            beMap.put("doctorName",doctorName);
//        }
//        beMap.put("teacherFlow",sysUser.getUserFlow());
        Map<String,Object> param=new HashMap<>();
        param.put("teacherFlow",sysUser.getUserFlow());
        param.put("sessionNumber",sessionNumber);
        param.put("speId",speId);
        param.put("doctorCategoryId",doctorCategoryId);
        param.put("docName",doctorName);
        param.put("docTypeList",docTypeList);
//        param.put("auditId",DiscipleStatusEnum.Submit.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
//        List<ResDoctorExt> students=doctorBiz.searchDocByDiscipleTea(param);
        resStudentDiscipleTeachers = resFolowTeacherRecordBiz.searchDisciplePendingAudit(param);
        model.addAttribute("resStudentDiscipleTeachers",resStudentDiscipleTeachers);
        model.addAttribute("doctorName",doctorName);
        return "res/disciple/followTeacherRecordView";
    }

    /**
     * 审核跟师记录
     * @param recordFlowsStr
     * @param statuId
     * @return
     */
    @RequestMapping(value="/modifySelected" )
    public
    @ResponseBody
    String modifySelected(String recordFlowsStr, String statuId, String batch, String doctorFlow, String teacherFlow, String roleId) {
        SysUser sysUser = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(recordFlowsStr)) {
            String[] recordFlows = recordFlowsStr.split(",");
            for (int i = 0; i < recordFlows.length; i++) {
                ResDiscipleRecordInfo resDiscipleRecordInfo = new ResDiscipleRecordInfo();
                resDiscipleRecordInfo.setRecordFlow(recordFlows[i]);
                resDiscipleRecordInfo = resFolowTeacherRecordBiz.searchFolowTeacherRecord(resDiscipleRecordInfo).get(0);
                if ("1".equals(statuId)) {
                    resDiscipleRecordInfo.setAuditStatusId(DiscipleStatusEnum.Qualified.getId());
                    resDiscipleRecordInfo.setAuditStatusName(DiscipleStatusEnum.Qualified.getName());
                } else {
                    resDiscipleRecordInfo.setAuditStatusId(DiscipleStatusEnum.UnQualified.getId());
                    resDiscipleRecordInfo.setAuditStatusName(DiscipleStatusEnum.UnQualified.getName());
                }
                resDiscipleRecordInfo.setAuditUserFlow(sysUser.getUserFlow());
                resDiscipleRecordInfo.setAuditUserName(sysUser.getUserName());
                resFolowTeacherRecordBiz.saveResDiscipleRecordInfo(resDiscipleRecordInfo);
            }
            return GlobalConstant.OPERATE_SUCCESSED;
        } else {
            //批量通过
            if ("Y".equals(batch)) {

                Map<String, String> paramMap = new HashMap<>();
                SysUser currentUser = GlobalContext.getCurrentUser();
                paramMap.put("doctorFlow", doctorFlow);
                if ("see".equals(roleId)) {
                    //师承老师审核自己的学生
                    paramMap.put("teacherFlow", currentUser.getUserFlow());
                } else if ("adminSee".equals(roleId)) {
                    //医院管理员审核该医院下该学生的所有记录
                }
                paramMap.put("auditUserFlow", currentUser.getUserFlow());
                paramMap.put("auditUserName", currentUser.getUserName());
                paramMap.put("oldAuditStatusId", DiscipleStatusEnum.PendingAudit.getId());
                paramMap.put("newAuditStatusId", DiscipleStatusEnum.Qualified.getId());
                paramMap.put("newAuditStatusName", DiscipleStatusEnum.Qualified.getName());
                int i = resFolowTeacherRecordBiz.batchAgreeResDiscipleRecordInfo(paramMap);
                if (i != 0) {
                    return GlobalConstant.OPRE_SUCCESSED;
                } else {
                    return "该学员没有待审核记录！";
                }
            }
            return GlobalConstant.OPERATE_FAIL;
        }
    }

}
