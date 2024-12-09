package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.DictTypeEnum;
import com.pinde.core.common.enums.OrgTypeEnum;
import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IAnnualAssessmentBiz;
import com.pinde.sci.biz.res.IDiscipleBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.core.common.enums.DiscipleStatusEnum;
import com.pinde.core.common.enums.NoteTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorDiscioleExt;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 学习心得（体会，笔记）
 */
@Controller
@RequestMapping("res/discipleNote")
public class ResDiscipleNoteController extends GeneralController {
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IDiscipleBiz discipleBiz;
    @Autowired
    private IAnnualAssessmentBiz assessmentBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private SysOrgExtMapper sysOrgExtMapper;

    /**
     * 展示跟师笔记（心得、体会）
     *
     * @param scope
     * @param model
     * @param operaFlag 查询标识（'Y'表示查询待审核）
     * @return
     */
    @RequestMapping(value = "/showDiscipleNoteInfo/{roleScope}/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public String showDiscipleNoteInfo(@PathVariable String roleScope, @PathVariable String scope, String doctorFlow, String recordFlow, String operaFlag, Model model) {
        model.addAttribute("scope", scope);
        model.addAttribute("roleScope", roleScope);
        model.addAttribute("operaFlag", operaFlag);
        SysUser currUser = GlobalContext.getCurrentUser();
        ResDiscipleNoteInfo discipleNoteInfo = new ResDiscipleNoteInfo();
        discipleNoteInfo.setDoctorFlow(doctorFlow);
        discipleNoteInfo.setNoteTypeId(scope);
        //如果是学员
        if (currUser != null && roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR)) {
            ResDoctor resDoctor = doctorBiz.readDoctor(currUser.getUserFlow());
            model.addAttribute("resDoctor", resDoctor);
        }

        List<String> auditStatusList = new ArrayList<>();
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleScope)) {
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(operaFlag)) {
                auditStatusList.add(DiscipleStatusEnum.Qualified.getId());
                auditStatusList.add(DiscipleStatusEnum.UnQualified.getId());
            }
            auditStatusList.add(DiscipleStatusEnum.Submit.getId());
            model.addAttribute("doctorFlow", doctorFlow);
        }
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleScope)) {
            auditStatusList.add(DiscipleStatusEnum.Qualified.getId());
            model.addAttribute("doctorFlow", doctorFlow);
        }
        List<ResDiscipleNoteInfo> resDiscipleNoteInfoList = discipleBiz.findResDiscipleNoteInfo(discipleNoteInfo, auditStatusList);
        model.addAttribute("resDiscipleNoteInfoList", resDiscipleNoteInfoList);
        if (StringUtil.isBlank(recordFlow) && resDiscipleNoteInfoList != null && resDiscipleNoteInfoList.size() > 0) {
            recordFlow = resDiscipleNoteInfoList.get(0).getRecordFlow();
        }
        if (StringUtil.isNotBlank(recordFlow)) {
            ResDiscipleNoteInfoWithBLOBs noteInfoWithBLOBs = discipleBiz.findResDiscipleNoteInfoWithBLOBs(recordFlow);
            model.addAttribute("noteInfoWithBLOBs", noteInfoWithBLOBs);
            //查询附件
            List<PubFile> discipleFiles = pubFileBiz.searchByProductFlow(recordFlow);
            model.addAttribute("discipleFiles",discipleFiles);
        }
        if (NoteTypeEnum.Note.getId().equals(scope)) {
            return "res/disciple/doctor/discipleNoteInfo";//跟师学习笔记
        }
        if (NoteTypeEnum.Experience.getId().equals(scope)) {
            return "res/disciple/doctor/discipleExperienceInfo";//跟师心得
        }
        if (NoteTypeEnum.BookExperience.getId().equals(scope)) {
            return "res/disciple/doctor/discipleBookExperienceInfo";//医籍学习体会
        }
        return "";
    }

    @RequestMapping("/exportDoc")
    public void exportDoc(String recordFlow, String scope, HttpServletRequest request, HttpServletResponse response)throws Exception{
        List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        ResDiscipleNoteInfoWithBLOBs noteInfoWithBLOBs = discipleBiz.findResDiscipleNoteInfoWithBLOBs(recordFlow);
        String doctorName = noteInfoWithBLOBs.getDoctorName();
        String studyStartDate = noteInfoWithBLOBs.getStudyStartDate();
        String studyEndDate = noteInfoWithBLOBs.getStudyEndDate();
        String teacherName = noteInfoWithBLOBs.getTeacherName();
        String studyContent = noteInfoWithBLOBs.getStudyContent();
        String bookContent = noteInfoWithBLOBs.getBookContent();
        String experienceContent = noteInfoWithBLOBs.getExperienceContent();
        String studentSignTime = DateUtil.transDate(noteInfoWithBLOBs.getStudentSignTime());
        String auditContent = noteInfoWithBLOBs.getAuditContent();
        String auditTime = StringUtil.isBlank(DateUtil.transDate(noteInfoWithBLOBs.getAuditTime()))?DateUtil.getCurrDate():DateUtil.transDate(noteInfoWithBLOBs.getAuditTime());
        String studyTimeId = noteInfoWithBLOBs.getStudyTimeId();
        dataMap.put("doctorName",doctorName);
        dataMap.put("studyStartDate",studyStartDate);
        dataMap.put("studyEndDate",studyEndDate);
        dataMap.put("teacherName",teacherName);
        dataMap.put("studyContent",studyContent);
        dataMap.put("bookContent",bookContent);
        dataMap.put("experienceContent",experienceContent);
        dataMap.put("studentSignTime",studentSignTime);
        dataMap.put("auditContent",auditContent);
        dataMap.put("auditTime",auditTime);
        if("am".equals(studyTimeId)){
            studyTimeId="上午";
        }else if("pm".equals(studyTimeId)){
            studyTimeId="下午";
        }else if("allDay".equals(studyTimeId)){
            studyTimeId="全天";
        }
        dataMap.put("studyTimeId",studyTimeId);
        WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
        String path = "";
        String name = "";
        if(NoteTypeEnum.Experience.getId().equals(scope)){
            path = "/jsp/res/disciple/export/discipleExperienceInfoTemp.docx";//模板
            name = "跟师心得.docx";
        }else if(NoteTypeEnum.BookExperience.getId().equals(scope)){
            path = "/jsp/res/disciple/export/discipleBookExperienceInfoTemp.docx";//模板
            name = "医籍学习体会.docx";
        }else if(NoteTypeEnum.Note.getId().equals(scope)) {
            path = "/jsp/res/disciple/export/discipleNoteTemp.docx";//模板
            name = "跟师学习笔记.docx";
        }
        ServletContext context = request.getServletContext();
        String watermark = "";
        temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
        addTemplates.add(temeplete1);

        WordprocessingMLPackage temeplete = Docx4jUtil.mergeDocx(addTemplates);
        if(temeplete!=null){
            response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
            response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream ();
            (new SaveToZipFile(temeplete)).save (out);
            out.flush ();
        }
    }

    /**
     * 保存(审核)跟师笔记（心得、体会）
     *
     * @param scope
     * @return
     */
    @RequestMapping(value = "/saveDiscipleNoteInfo/{roleScope}/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String saveDiscipleNoteInfo(@PathVariable String roleScope, @PathVariable String scope, String jsonData,
                                       ResDiscipleNoteInfoWithBLOBs discipleNoteInfo, String flag,String editAppendixFlag, HttpServletRequest request) throws UnsupportedEncodingException {
//        学员端多图片上传
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR)) {
            //校验上传文件大小及格式
            String checkResult = checkFiles(request);
            if (!"1".equals(checkResult)) {
                return checkResult;
            }
        }


        if ("am".equals(discipleNoteInfo.getStudyTimeId())) {
            discipleNoteInfo.setStudyTimeNmae("上午");
        }
        if ("pm".equals(discipleNoteInfo.getStudyTimeId())) {
            discipleNoteInfo.setStudyTimeNmae("下午");
        }
        if ("allDay".equals(discipleNoteInfo.getStudyTimeId())) {
            discipleNoteInfo.setStudyTimeNmae("全天");
        }
        ResDiscipleNoteInfo resDiscipleNoteInfo = new ResDiscipleNoteInfo();
        if(StringUtil.isNotBlank(discipleNoteInfo.getStudyStartDate()) && (NoteTypeEnum.Experience.getId().equals(scope) || NoteTypeEnum.Note.getId().equals(scope))){
            resDiscipleNoteInfo.setNoteTypeId(scope);
            resDiscipleNoteInfo.setStudyStartDate(discipleNoteInfo.getStudyStartDate());
            resDiscipleNoteInfo.setDoctorFlow(discipleNoteInfo.getDoctorFlow());
            List<String> status = new ArrayList<>();
            status.add(DiscipleStatusEnum.Submit.getId());
            status.add(DiscipleStatusEnum.Apply.getId());
            status.add(DiscipleStatusEnum.Qualified.getId());
            List<ResDiscipleNoteInfo> resDiscipleNoteInfoList = discipleBiz.findResDiscipleNoteInfo(resDiscipleNoteInfo,status);
            if(null != resDiscipleNoteInfoList && resDiscipleNoteInfoList.size()>0){
                if(resDiscipleNoteInfoList.size() ==1){
                    if(StringUtil.isBlank(discipleNoteInfo.getRecordFlow())){
                        return "已存在该时间段的记录，不可重复添加";
                    }
                }else {
                    return "已存在该时间段的记录，不可重复添加";
                }
            }
        }
        discipleNoteInfo.setNoteTypeId(scope);
        discipleNoteInfo.setNoteTypeName(NoteTypeEnum.getNameById(scope));
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR)) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
                discipleNoteInfo.setAuditStatusId(DiscipleStatusEnum.Submit.getId());
                discipleNoteInfo.setAuditStatusName(DiscipleStatusEnum.Submit.getName());
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editAppendixFlag)) {
                // 不更改状态
            } else {
                discipleNoteInfo.setAuditStatusId(DiscipleStatusEnum.Apply.getId());
                discipleNoteInfo.setAuditStatusName(DiscipleStatusEnum.Apply.getName());
            }
        }
        int i = discipleBiz.updateResDiscipleNoteInfoWithBLOBs(discipleNoteInfo);
//        学员端多图片上传
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR)) {
            jsonData = java.net.URLDecoder.decode(jsonData, "UTF-8");
            Map<String, Object> mp = JSON.parseObject(jsonData, Map.class);
            //上传文件的流水号
            List<String> fileFlows = (List<String>) mp.get("fileFlows");
            //处理不在本次保存中的文件
            upadteFileInfo(discipleNoteInfo.getRecordFlow(), fileFlows);
            //处理上传文件
            addUploadFile(discipleNoteInfo.getRecordFlow(), request, scope);
        }
        if (i != 0) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        } else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }

    /**
     * 师承老师和管理员一键审核（跟师学习笔记、跟师心得、经典医籍学习体会）功能
     * @param roleScope  角色
     * @param noteTypeId 类型
     * @param doctorFlow 学员流水号
     * @return
     */
    @RequestMapping(value = "/agreeRecordBatch/{roleScope}/{noteTypeId}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String agreeRecordBatch(@PathVariable String roleScope, @PathVariable String noteTypeId, String doctorFlow){

        Map<String, String> paramMap = new HashMap<>();
        SysUser currentUser = GlobalContext.getCurrentUser();
        paramMap.put("doctorFlow", doctorFlow);
        if ("see".equals(roleScope)) {
            //师承老师审核自己的学生
            paramMap.put("teacherFlow", currentUser.getUserFlow());
        } else if ("adminSee".equals(roleScope)) {
            //医院管理员审核该医院下该学生的所有记录
        }
        paramMap.put("noteTypeId", noteTypeId);
        paramMap.put("auditUserFlow", currentUser.getUserFlow());
        paramMap.put("auditUserName", currentUser.getUserName());
        paramMap.put("oldAuditStatusId", DiscipleStatusEnum.Submit.getId());
        paramMap.put("newAuditStatusId", DiscipleStatusEnum.Qualified.getId());
        paramMap.put("newAuditStatusName", DiscipleStatusEnum.Qualified.getName());
        paramMap.put("auditContent", "该学员对本病有大致的了解，能较好的进行辨病辨证，对相关的经典理论也有一定的涉猎。");
        int i = discipleBiz.agreeRecordBatch(paramMap);
        if (i != 0) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        } else {
            return "该学员没有待审核记录！";
        }
    }

    /**
     * 删除跟师笔记（心得、体会）
     *
     * @param recordFlow
     * @param request
     * @return
     */
    @RequestMapping("/delDiscipleNoteInfo")
    @ResponseBody
    public String delDiscipleNoteInfo(String recordFlow, HttpServletRequest request) {
        if (StringUtil.isNotBlank(recordFlow)) {
            int delResult = discipleBiz.delResDiscipleNoteInfo(recordFlow);
            if (delResult == 1) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping("/saveAnnualAssessment/{roleScope}")
    @ResponseBody
    public String saveAnnualAssessment(@PathVariable String roleScope, ResAnnualAssessmentWithBLOBs assessmentWithBLOBs, String flag,
                                       HttpServletRequest request, String editAppendixFlag,String jsonData) throws UnsupportedEncodingException {
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR)) {
            //        学员端多图片上传
            if (roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR)) {
                //校验上传文件大小及格式
                String checkResult = checkFiles(request);
                if (!"1".equals(checkResult)) {
                    return checkResult;
                }
            }
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editAppendixFlag)) {
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
                    assessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.Submit.getId());
                    assessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.Submit.getName());
                } else {
                    assessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.Apply.getId());
                    assessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.Apply.getName());
                }
            }
        }
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN)) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
                assessmentWithBLOBs.setAdminTime(DateUtil.getCurrDateTime());
                assessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.AdminAudit.getId());
                assessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.AdminAudit.getName());
            } else {
                assessmentWithBLOBs.setAdminTime(DateUtil.getCurrDateTime());
                //assessmentWithBLOBs.setExperienceSignTime(DateUtil.getCurrDateTime());
                assessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.AdminBack.getId());
                assessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.AdminBack.getName());
            }
        }
        int i = assessmentBiz.editAnnualAssessment(assessmentWithBLOBs);
//        学员端多图片上传
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR)) {
            jsonData = java.net.URLDecoder.decode(jsonData, "UTF-8");
            Map<String, Object> mp = JSON.parseObject(jsonData, Map.class);
            //上传文件的流水号
            List<String> fileFlows = (List<String>) mp.get("fileFlows");
            //处理不在本次保存中的文件
            upadteFileInfo(assessmentWithBLOBs.getRecordFlow(), fileFlows);
            //处理上传文件
            addUploadFile(assessmentWithBLOBs.getRecordFlow(), request, "AnnualAssessment");
        }
        if (i != 0) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
        } else {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
        }
    }

    @RequestMapping("/delAnnualAssessment")
    public
    @ResponseBody
    String delAnnualAssessment(String recordFlow) {
        if (StringUtil.isNotBlank(recordFlow)) {
            int delResult = assessmentBiz.delAnnualAssessment(recordFlow);
            if (delResult == 1) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping("/auditUserList/{roleScope}/{scope}")
    public String auditUserList(@PathVariable String roleScope, @PathVariable String scope, ResDoctor doctor,String[] doctorTypeIdList,
                                HttpServletRequest request,String orgFlow,Integer currentPage, Model model) {
        Map<String, Object> map = new HashMap<>();
        SysUser currUser = GlobalContext.getCurrentUser();
        model.addAttribute("roleScope", roleScope);
        model.addAttribute("scope", scope);
        map.put("doctorName", doctor.getDoctorName());
        if(StringUtil.isNotBlank(doctor.getSessionNumber())){
            map.put("sessionNumber",doctor.getSessionNumber());
        }
        if(StringUtil.isNotBlank(doctor.getDiscipleTeacherName())){
            map.put("discipleTeacherName",doctor.getDiscipleTeacherName());
        }
        if(StringUtil.isNotBlank(doctor.getTrainingSpeId())){
            map.put("trainingSpeId",doctor.getTrainingSpeId());
        }
        if(StringUtil.isNotBlank(doctor.getDoctorCategoryId())){
            map.put("doctorCategoryId",doctor.getDoctorCategoryId());
        }
        if(StringUtil.isNotBlank(doctor.getSessionNumber())){
            map.put("sessionNumber",doctor.getSessionNumber());
        }
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN)) {
            map.put("auditStatusId", DiscipleStatusEnum.DiscipleAudit.getId());
            SysUser user=GlobalContext.getCurrentUser();
            map.put("orgFlow",user.getOrgFlow());
           /* if (NoteTypeEnum.Note.getId().equals(scope) || NoteTypeEnum.Experience.getId().equals(scope) || NoteTypeEnum.BookExperience.getId().equals(scope)) {
                map.put("noteTypeId", scope);
            }*/
        }
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL)) {
            //查询所有医院
            SysOrg org = new SysOrg();
            org.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
            List<SysOrg> orgs = orgBiz.searchOrg(org);
            model.addAttribute("orgs",orgs);
            map.put("auditStatusId", DiscipleStatusEnum.AdminAudit.getId());
            map.put("orgFlow",orgFlow);
        }
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY)) {
            String currentOrgFlow = currUser.getOrgFlow();
            if(StringUtil.isNotBlank(currentOrgFlow)){
                SysOrg org = orgBiz.readSysOrg(currentOrgFlow);
                String workOrgId = org.getSendSchoolId();
                List<SysOrg> orgs = sysOrgExtMapper.searchOrgs4hbUniversity(workOrgId);
                model.addAttribute("orgs",orgs);
                map.put("workOrgId", workOrgId);
            }
            map.put("auditStatusId", DiscipleStatusEnum.AdminAudit.getId());
            map.put("orgFlow",orgFlow);
        }
        List<String> doctorTypeIds = null;
        if(doctorTypeIdList != null && doctorTypeIdList.length > 0){
            doctorTypeIds = new ArrayList<>();
            for(String temp : doctorTypeIdList){
                doctorTypeIds.add(temp);
            }
        }
        //复选框勾选标识
        Map<String,String> doctorTypeSelectMap = new HashMap<>();
        SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<SysDict> dictList = dictBiz.searchDictList(sysDict);
        if(dictList!=null&&dictList.size()>0&&doctorTypeIds!=null&&doctorTypeIds.size()>0){
            for (SysDict dict:dictList){
                if(doctorTypeIds.contains(dict.getDictId())){
                    doctorTypeSelectMap.put(dict.getDictId(),"checked");
                }
            }
        }
        map.put("doctorTypeIds", doctorTypeIds);
        model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDoctorDiscioleExt> doctorList = discipleBiz.searchAuditDoctorList(map);
        model.addAttribute("doctorList", doctorList);
        if (roleScope.equals(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL) || roleScope.equals(com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY)) {
            return "res/disciple/auditUserList4Global";
        }
        return "res/disciple/auditUserList";
    }

    @RequestMapping("/annualAssessmentIsSubmit")
    public
    @ResponseBody
    String annualAssessmentIsSubmit(String doctorFlow, String year) {
        ResAnnualAssessment assessment = new ResAnnualAssessment();
        assessment.setDoctorFlow(doctorFlow);
        assessment.setRecordYear(year);
        List<String> statusList = new ArrayList<>();
        statusList.add(DiscipleStatusEnum.DiscipleAudit.getId());
        statusList.add(DiscipleStatusEnum.AdminAudit.getId());
        statusList.add(DiscipleStatusEnum.Submit.getId());
        List<ResAnnualAssessment> assessmentList = assessmentBiz.findAnnualAssessmentList(assessment, statusList);
        if (null != assessmentList && assessmentList.size()>0) {
            return com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        return com.pinde.core.common.GlobalConstant.FLAG_N;
    }
    @RequestMapping("/uploadImg")
    @ResponseBody
    public String uploadImg(@RequestParam(value = "file", required = false) MultipartFile file,String url,String recordFlow) throws IOException {
        if (file != null && StringUtil.isNotBlank(file.getOriginalFilename())) {
            String result = checkFile(file);
            if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(result)) {
                return result;
            }
            // String fileName = file.getOriginalFilename();
            String oldUrl =StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url")) +url;
            File oldImg = new File(oldUrl);
            if(oldImg.exists()){
                oldImg.delete();
            }
            String[] nameArray = file.getOriginalFilename().split("\\.");
            String dateString = DateUtil.getCurrDate2();
            String filePath = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "resAssessment" + File.separator + dateString;
            File fileDir = new File(filePath);
            String saveFileName = PkUtil.getUUID() + "." + nameArray[nameArray.length - 1];
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File srmFundFile = new File(fileDir, saveFileName);
            file.transferTo(srmFundFile);
            String uploadFile = File.separator + "resAssessment" + File.separator + dateString + File.separator + saveFileName;
            ResAnnualAssessmentWithBLOBs assessment = new ResAnnualAssessmentWithBLOBs();
            assessment.setAssessmentImgUrl(uploadFile);
            assessment.setRecordFlow(recordFlow);
            assessmentBiz.editAnnualAssessment(assessment);
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
        }
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
    }
    /**
     * 检查文件大小及类型
     * @param file
     */
    private String checkFile(MultipartFile file) {
        List<String> mimeList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
            mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
        }
        List<String> suffixList = new ArrayList<String>();
        if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
            suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
        }

        String fileType = file.getContentType();//MIME类型;
        String fileName = file.getOriginalFilename();//文件名
        String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();//后缀名
        if(!(mimeList.contains(fileType) &&  suffixList.contains(suffix))){
            return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
        }
        long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
        if(file.getSize() > limitSize*1024*1024){
            return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M";
        }
        return com.pinde.core.common.GlobalConstant.FLAG_Y;//可执行保存
    }

    //保存上传的附件
    private void addUploadFile(String recordFlow, HttpServletRequest request, String noteTypeId) {
        //以下为多文件上传********************************************
        //创建一个通用的多部分解析器
        List<PubFile> pubFiles=new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                if(files != null&&files.size()>0){
                    for(MultipartFile file:files) {
                        //保存附件
                        PubFile pubFile = new PubFile();
                        //取得当前上传文件的文件名称
                        String oldFileName = file.getOriginalFilename();
                        //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (StringUtil.isNotBlank(oldFileName)) {
                            //定义上传路径
                            String dateString = DateUtil.getCurrDate2();
                            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "discipleFiles" + File.separator + noteTypeId + File.separator + dateString+ File.separator+recordFlow;
                            File fileDir = new File(newDir);
                            if (!fileDir.exists()) {
                                fileDir.mkdirs();
                            }
                            //重命名上传后的文件名
                            String originalFilename = "";
                            originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                            File newFile = new File(fileDir, originalFilename);
                            try {
                                file.transferTo(newFile);
                            } catch (Exception e) {
                                logger.error("", e);
                                throw new RuntimeException("保存文件失败！");
                            }
                            String filePath = File.separator + "discipleFiles" +  File.separator + noteTypeId + File.separator + dateString + File.separator+recordFlow+ File.separator + originalFilename;
                            pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                            pubFile.setFilePath(filePath);
                            pubFile.setFileName(oldFileName);
                            pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
                            pubFile.setProductType(noteTypeId);
                            pubFile.setProductFlow(recordFlow);
                            pubFiles.add(pubFile);
                        }
                    }
                }
                //记录上传该文件后的时间
                //int finaltime = (int) System.currentTimeMillis();
            }
        }
        if(pubFiles.size()>0)
        {
            pubFileBiz.saveFiles(pubFiles);
        }
    }

    //处理文件
    private void upadteFileInfo(String recordFlow, List<String> fileFlows) {
        //查询出不在本次保存中的文件信息
        List<PubFile> files=pubFileBiz.searchByProductFlowAndNotInFileFlows(recordFlow,fileFlows);
        //删除服务器中相应的文件
        if(files!=null&&files.size()>0)
        {
            String basePath = InitConfig.getSysCfg("upload_base_dir");
            for (PubFile pubFile : files) {
//                if (StringUtil.isNotBlank(pubFile.getFilePath())) {
//                    String filePath = basePath + pubFile.getFilePath();
//                    FileUtil.deletefile(filePath);
//                }
                pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
                pubFileBiz.editFile(pubFile);
            }
        }
    }
    private String checkFiles(HttpServletRequest request) {
        String result="1";
        ServletContext application = request.getServletContext();
        Map<String, String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
        String imageSuffixStr = sysCfgMap.get("inx_image_support_suffix");
        String[] imageSuffixArr = imageSuffixStr.split(",");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            List<String> fileSuffix=new ArrayList<>();
            fileSuffix.addAll(Arrays.asList(imageSuffixArr));
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                if(files != null&&files.size()>0){
                    for(MultipartFile file:files) {

                        //取得当前上传文件的文件名称
                        String fileName = file.getOriginalFilename();
                        String suffix=fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                        if(!fileSuffix.contains(suffix))
                        {
                            return fileName + "的文件格式不正确，只能上传" + imageSuffixStr + "图片格式的文件。";
                        }
                        if (file.getSize() > 10 * 1024 * 1024) {
                            return fileName+ "的大小超过10M，不得保存";
                        }
                    }
                }
            }
        }
        return result;
    }

    private static Logger logger = LoggerFactory.getLogger(ResDiscipleNoteController.class);

}
