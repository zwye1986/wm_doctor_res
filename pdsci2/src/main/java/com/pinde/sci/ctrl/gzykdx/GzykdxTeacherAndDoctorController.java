package com.pinde.sci.ctrl.gzykdx;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxRecruitBiz;
import com.pinde.sci.biz.gzykdx.IGzykdxTeaAndDocBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.TeacherTargetApplyMapper;
import com.pinde.sci.enums.gzykdx.GzykdxAdmissionStatusEnum;
import com.pinde.sci.enums.gzykdx.GzykdxAuditStatusEnum;
import com.pinde.sci.enums.gzykdx.GzykdxDegreeTypeEnum;
import com.pinde.sci.enums.gzykdx.GzykdxThesisTypeEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.gzykdx.GzykdxRecruitExtInfoForm;
import com.pinde.sci.form.gzykdx.GzykdxTeacherExtInfoForm;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author wangshuai
 * @Copyright njpdxx.com
 * <p/>
 * 此类主要是实现 XXX 功能
 * @since 2017/3/8
 */

@Controller
@RequestMapping("/gzykdx/teaAndDoc")
public class GzykdxTeacherAndDoctorController extends GeneralController {

    @Autowired
    private IGzykdxTeaAndDocBiz teaAndDocBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IPubUserResumeBiz pubUserResumeBiz;
    @Autowired
    private IGzykdxRecruitBiz recruitBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IFileBiz pubFileBiz;

    @RequestMapping(value="/selectTeacherApplyList" )
    public String selectTeacherApplyList(String recruitYear,String applyType,String speId,Integer currentPage, HttpServletRequest request, Model model){
        if(StringUtil.isBlank(recruitYear)){
            recruitYear=DateUtil.getYear();
        }
        TeacherTargetApply teacherTargetApply=new TeacherTargetApply();
        teacherTargetApply.setRecruitYear(recruitYear);
        teacherTargetApply.setSpeId(speId);
        teacherTargetApply.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        if(StringUtil.isNotBlank(applyType)){
            if("isAcademic".equals(applyType)){
                teacherTargetApply.setIsAcademic(GlobalConstant.IS_EXAM_TEA_Y);
            }
            if("isSpecialized".equals(applyType)){
                teacherTargetApply.setIsSpecialized(GlobalConstant.IS_EXAM_TEA_Y);
            }
        }
        List<TeacherTargetApply> teacherTargetApplyList=teaAndDocBiz.selectTargetApplyList(teacherTargetApply);
        model.addAttribute("teacherTargetApplyList",teacherTargetApplyList);
        model.addAttribute("recruitYear",recruitYear);
        model.addAttribute("user",GlobalContext.getCurrentUser());
        return "/gzykdx/teacher/targetApply";
    }

    @RequestMapping(value = "/edit")
    public String edit(String detailFlag,String scope,String applyFlow,Integer currentPage, Model model) throws DocumentException {
        List<TeacherThesisDetail> teacherThesisList=null;
        List<TeacherThesisDetail> doctorThesisList=null;
        List<TeacherThesisDetail> researchProjectList=null;
        String userFlow="";
        List<Map<String,Object>> teacherThesis=new ArrayList<>();
        List<Map<String,Object>> doctorThesis=new ArrayList<>();
        List<Map<String,Object>> researchProject=new ArrayList<>();
        if(StringUtil.isNotBlank(applyFlow)){
            TeacherTargetApply teacherTargetApply=new TeacherTargetApply();
            teacherTargetApply.setApplyFlow(applyFlow);
            List<TeacherTargetApply> teacherTargetApplyList=teaAndDocBiz.selectTargetApplyList(teacherTargetApply);
            if(teacherTargetApplyList!=null&&teacherTargetApplyList.size()>0){
                userFlow=teacherTargetApplyList.get(0).getUserFlow();
                model.addAttribute("targetApply",teacherTargetApplyList.get(0));
            }
            PubFile fileTemp=new PubFile();
            TeacherThesisDetail detail=new TeacherThesisDetail();
            detail.setApplyFlow(applyFlow);
            detail.setThesisTypeId(GzykdxThesisTypeEnum.TeacherThesis.getId());
            teacherThesisList=teaAndDocBiz.detailList(detail);
            if(teacherThesisList!=null&&teacherThesisList.size()>0){
                for(TeacherThesisDetail ttd:teacherThesisList){
                    Map<String,Object> temp=new HashMap<>();
                    fileTemp=teaAndDocBiz.readPubFlie(ttd.getFileFlow());
                    temp.put("teacherThesis",ttd);
                    temp.put("file",fileTemp);
                    teacherThesis.add(temp);
                }
            }
            detail.setThesisTypeId(GzykdxThesisTypeEnum.DoctorThesis.getId());
            doctorThesisList=teaAndDocBiz.detailList(detail);
            if(doctorThesisList!=null&&doctorThesisList.size()>0){
                for(TeacherThesisDetail ttd:doctorThesisList){
                    Map<String,Object> temp=new HashMap<>();
                    fileTemp=teaAndDocBiz.readPubFlie(ttd.getFileFlow());
                    temp.put("doctorThesis",ttd);
                    temp.put("file",fileTemp);
                    doctorThesis.add(temp);
                }
            }
            detail.setThesisTypeId(GzykdxThesisTypeEnum.ResearchProject.getId());
            researchProjectList=teaAndDocBiz.detailList(detail);
            if(researchProjectList!=null&&researchProjectList.size()>0){
                for(TeacherThesisDetail ttd:researchProjectList){
                    Map<String,Object> temp=new HashMap<>();
                    fileTemp=teaAndDocBiz.readPubFlie(ttd.getFileFlow());
                    temp.put("researchProject",ttd);
                    temp.put("file",fileTemp);
                    researchProject.add(temp);
                }
            }
        }
        if("teacher".equals(scope)){
            userFlow=GlobalContext.getCurrentUser().getUserFlow();
        }
        List<SysOrg> orgList=teaAndDocBiz.searchOrgList();
        SysUser user=userBiz.readSysUser(userFlow);
        PubUserResume pubUserResume=pubUserResumeBiz.readPubUserResume(userFlow);
        if(pubUserResume!=null){
            String xmlContent =  pubUserResume.getUserResume();
            if(StringUtil.isNotBlank(xmlContent)){
                //xml转换成JavaBean
                GzykdxTeacherExtInfoForm extInfo=null;
                extInfo=pubUserResumeBiz.converyToJavaBean(xmlContent,GzykdxTeacherExtInfoForm.class);
                model.addAttribute("extInfo", extInfo);
            }
        }
        model.addAttribute("orgList",orgList);
        model.addAttribute("user",user);
        model.addAttribute("scope",scope);
        model.addAttribute("detailFlag",detailFlag);
        model.addAttribute("teacherThesisList",teacherThesis);
        model.addAttribute("doctorThesisList",doctorThesis);
        model.addAttribute("researchProjectList",researchProject);
        model.addAttribute("currentPage",currentPage);
        return "/gzykdx/teacher/applyForm";
    }
    /**
     * 查看附件
     * @param fileFlow
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = {"/fileDown" }, method = RequestMethod.GET)
    public void achDown(String fileFlow, final HttpServletResponse response) throws Exception{
        PubFile file = teaAndDocBiz.readPubFlie(fileFlow);
        teaAndDocBiz.downGzykdxFile(file,response);
    }

    @RequestMapping(value = {"/showWordOrPdf" })
    public void showWordOrPdf(String urlReal,HttpServletResponse response) throws Exception{
        BufferedInputStream bis = null;
        URL url = null;
        HttpURLConnection httpUrl = null; // 建立链接
        url = new URL(urlReal);
        httpUrl = (HttpURLConnection) url.openConnection();// 连接指定的资源
        httpUrl.connect();// 获取网络输入流
        bis = new BufferedInputStream(httpUrl.getInputStream());
        String bodyText = null;
        WordExtractor ex = new WordExtractor(bis);
        bodyText = ex.getText();
        response.getWriter().write(bodyText);
    }

    @RequestMapping(value = "/checkApplyOneYear")
    @ResponseBody
    public String checkApplyOneYear(){
        TeacherTargetApply apply=new TeacherTargetApply();
        apply.setRecruitYear(DateUtil.getYear());
        apply.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        List<TeacherTargetApply> list=teaAndDocBiz.selectTargetApplyList(apply);
        if(list!=null&&list.size()>0){
            return GlobalConstant.NOT_NULL;
        }
        return GlobalConstant.NULL;
    }

    @RequestMapping(value = {"/saveApply"})
    @ResponseBody
    public String saveApply(String jsondata, HttpServletRequest request) throws IOException {
        Map<String,Object> mp = JSON.parseObject(jsondata,Map.class);
        SysUser user=GlobalContext.getCurrentUser();
        GzykdxTeacherExtInfoForm teacherExtInfoForm=new GzykdxTeacherExtInfoForm();
        TeacherTargetApply teacherTargetApply=new TeacherTargetApply();
        teacherTargetApply.setIsAcademic("");
        teacherTargetApply.setIsSpecialized("");
        List<Map<String,String>> thesisList1=(List<Map<String,String>>)mp.get("thesisList1");
        List<Map<String,String>> thesisList2=(List<Map<String,String>>)mp.get("thesisList2");
        List<Map<String,String>> thesisList3=(List<Map<String,String>>)mp.get("thesisList3");
        Map<String,String> applyInfo=(Map<String,String>)mp.get("applyInfo");
        List<String> thesisFlowList=new ArrayList<>();
        if(applyInfo!=null){
            String thesisRecordList=applyInfo.get("thesisRecordList");
            String[] thesisFlows=null;
            if(StringUtil.isNotBlank(thesisRecordList)){
                thesisFlows=thesisRecordList.split(",");
                thesisFlowList= Arrays.asList(thesisFlows);
            }
            String orgName="";
            SysOrg org=orgBiz.readSysOrg(applyInfo.get("orgFlow"));
            if(org!=null){
                orgName=org.getOrgName();
            }
            user.setUserName(applyInfo.get("userName"));
            user.setUserBirthday(applyInfo.get("userBirthday"));
            user.setIdNo(applyInfo.get("idNo"));
            user.setUserPhone(applyInfo.get("userPhone"));
            user.setOrgFlow(applyInfo.get("orgFlow"));
            user.setOrgName(orgName);
            user.setSexId(applyInfo.get("sexId"));
            user.setSexName(UserSexEnum.getNameById(applyInfo.get("sexId")));
            user.setEducationId(applyInfo.get("educationId"));
            user.setEducationName(DictTypeEnum.HighestEducation.getDictNameById(applyInfo.get("educationId")));
            user.setDegreeId(applyInfo.get("degreeId"));
            user.setDegreeName(DictTypeEnum.HighestDegree.getDictNameById(applyInfo.get("degreeId")));
            user.setTitleId(applyInfo.get("titleId"));
            user.setTitleName(DictTypeEnum.GzykdxUserTitle.getDictNameById(applyInfo.get("titleId")));
            teacherExtInfoForm.setAge(applyInfo.get("age"));
            teacherExtInfoForm.setWorkAreaYear(applyInfo.get("workAreaYear"));
            teacherExtInfoForm.setWorkDate(applyInfo.get("workDate"));
            teacherExtInfoForm.setEducationSchool(applyInfo.get("educationSchool"));
            teacherExtInfoForm.setEducationDate(applyInfo.get("educationDate"));
            teacherExtInfoForm.setDegreeOrg(applyInfo.get("degreeOrg"));
            teacherExtInfoForm.setDegreeDate(applyInfo.get("degreeDate"));
            userBiz.updateUser(user);
            teaAndDocBiz.saveTeacherInfo(user,teacherExtInfoForm);
            teacherTargetApply.setUserFlow(user.getUserFlow());
            teacherTargetApply.setUserName(user.getUserName());
            teacherTargetApply.setOrgFlow(user.getOrgFlow());
            teacherTargetApply.setOrgName(user.getOrgName());
            teacherTargetApply.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            teacherTargetApply.setSpeId(applyInfo.get("speId"));
            teacherTargetApply.setSpeName(DictTypeEnum.GzykdxSpe.getDictNameById(applyInfo.get("speId")));
            teacherTargetApply.setRecruitYear(new SimpleDateFormat("yyyy").format(new Date()));
            teacherTargetApply.setIsAcademic(applyInfo.get("isAcademic")==null?"":applyInfo.get("isAcademic"));
            teacherTargetApply.setIsSpecialized(applyInfo.get("isSpecialized")==null?"":applyInfo.get("isSpecialized"));
            teacherTargetApply.setResearchDirection(applyInfo.get("researchDirection"));
//            teacherTargetApply.setResearchDirection(DictTypeEnum.ResearchArea.getDictNameById(applyInfo.get("researchDirectionId")));
            teacherTargetApply.setAuditStatusId(GzykdxAuditStatusEnum.Uncommitted.getId());
            teacherTargetApply.setAuditStatusName(GzykdxAuditStatusEnum.Uncommitted.getName());
            teacherTargetApply.setOrgAuditAdvice("");
            teacherTargetApply.setSchoolAuditAdvice("");
            if(StringUtil.isNotBlank(applyInfo.get("applyFlow"))){
                teacherTargetApply.setApplyFlow(applyInfo.get("applyFlow"));
                GeneralMethod.setRecordInfo(teacherTargetApply, false);
            }else {
                teacherTargetApply.setApplyFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(teacherTargetApply, true);
            }
        }
        if(thesisList1!=null&&thesisList1.size()>0){
            TeacherThesisDetail teacherThesisDetail=new TeacherThesisDetail();
            for (Map<String,String> map1:thesisList1) {
                teacherThesisDetail.setRecordFlow(map1.get("recordFlow"));
                teacherThesisDetail.setApplyFlow(teacherTargetApply.getApplyFlow());
                teacherThesisDetail.setThesisName(map1.get("thesisName"));
                teacherThesisDetail.setThesisTypeId(GzykdxThesisTypeEnum.TeacherThesis.getId());
                teacherThesisDetail.setThesisTypeName(GzykdxThesisTypeEnum.TeacherThesis.getName());
                teacherThesisDetail.setThesisTitle(map1.get("thesisTitle"));
                teacherThesisDetail.setAuthorRankingName(map1.get("authorRankingName"));
//                teacherThesisDetail.setAuthorRankingName(DictTypeEnum.AuthorRanking.getDictNameById(map1.get("authorRankingId")));
                teacherThesisDetail.setReportTime(map1.get("reportTime"));
                teacherThesisDetail.setSciPoint(map1.get("sciPoint"));
                String fileFlow=map1.get("fileFlow");
                if(StringUtil.isNotBlank(fileFlow)){
                    teacherThesisDetail.setFileFlow(fileFlow);
                    teaAndDocBiz.editTeacherThesisDetail(teacherThesisDetail);
                }else {
                    String returnMsg=doSaveThesis(teacherThesisDetail,fileFlow,request);
                    if(!GlobalConstant.SAVE_SUCCESSED.equals(returnMsg)){
                        return returnMsg;
                    }
                }
            }
        }
        if(thesisList2!=null&&thesisList2.size()>0){
            TeacherThesisDetail teacherThesisDetai2=new TeacherThesisDetail();
            for (Map<String,String> map2:thesisList2) {
                teacherThesisDetai2.setRecordFlow(map2.get("recordFlow"));
                teacherThesisDetai2.setApplyFlow(teacherTargetApply.getApplyFlow());
                teacherThesisDetai2.setThesisName(map2.get("thesisName"));
                teacherThesisDetai2.setThesisTypeId(GzykdxThesisTypeEnum.DoctorThesis.getId());
                teacherThesisDetai2.setThesisTypeName(GzykdxThesisTypeEnum.DoctorThesis.getName());
                teacherThesisDetai2.setThesisTitle(map2.get("thesisTitle"));
                teacherThesisDetai2.setAuthorRankingName(map2.get("authorRankingName"));
//                teacherThesisDetai2.setAuthorRankingName(DictTypeEnum.AuthorRanking.getDictNameById(map2.get("authorRankingId")));
                teacherThesisDetai2.setReportTime(map2.get("reportTime"));
                teacherThesisDetai2.setSciPoint(map2.get("sciPoint"));
                String fileFlow=map2.get("fileFlow");
                if(StringUtil.isNotBlank(fileFlow)){
                    teacherThesisDetai2.setFileFlow(fileFlow);
                    teaAndDocBiz.editTeacherThesisDetail(teacherThesisDetai2);
                }else {
                    String returnMsg=doSaveThesis(teacherThesisDetai2,fileFlow,request);
                    if(!GlobalConstant.SAVE_SUCCESSED.equals(returnMsg)){
                        return returnMsg;
                    }
                }
            }
        }
        if(thesisList3!=null&&thesisList3.size()>0){
            TeacherThesisDetail teacherThesisDetai3=new TeacherThesisDetail();
            for (Map<String,String> map3:thesisList3) {
                teacherThesisDetai3.setRecordFlow(map3.get("recordFlow"));
                teacherThesisDetai3.setApplyFlow(teacherTargetApply.getApplyFlow());
                teacherThesisDetai3.setThesisName(map3.get("thesisName"));
                teacherThesisDetai3.setThesisTypeId(GzykdxThesisTypeEnum.ResearchProject.getId());
                teacherThesisDetai3.setThesisTypeName(GzykdxThesisTypeEnum.ResearchProject.getName());
                teacherThesisDetai3.setAuthorRankingName(map3.get("authorRankingName"));
//                teacherThesisDetai3.setAuthorRankingName(DictTypeEnum.AuthorRanking.getDictNameById(map3.get("authorRankingId")));
                teacherThesisDetai3.setProductWorkName(map3.get("productWorkName"));
                teacherThesisDetai3.setReportTime(map3.get("reportTime"));
                teacherThesisDetai3.setProductAmount(map3.get("productAmount"));
                teacherThesisDetai3.setProductCode(map3.get("productCode"));
                String fileFlow=map3.get("fileFlow");
                if(StringUtil.isNotBlank(fileFlow)){
                    teacherThesisDetai3.setFileFlow(fileFlow);
                    teaAndDocBiz.editTeacherThesisDetail(teacherThesisDetai3);
                }else {
                    String returnMsg=doSaveThesis(teacherThesisDetai3,fileFlow,request);
                    if(!GlobalConstant.SAVE_SUCCESSED.equals(returnMsg)){
                        return returnMsg;
                    }
                }
            }
        }
        if(applyInfo!=null){
            if(StringUtil.isNotBlank(applyInfo.get("applyFlow"))){
                teaAndDocBiz.editTeacherTargetApply(teacherTargetApply);
                teaAndDocBiz.delThesisDetail(thesisFlowList);
            }else {
                teaAndDocBiz.addTeacherTargetApply(teacherTargetApply);
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 保存论文及其附件
     * @param detail
     * @param fileFlow
     * @param request
     * @return
     */
    public String doSaveThesis(TeacherThesisDetail detail,String fileFlow,HttpServletRequest request){
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if(multipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            Iterator<String> iter = multiRequest.getFileNames();
            int i=0;
            while(iter.hasNext()){
                i=1;
                //取得上传文件
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                if(files != null&&files.size()>0){
                    for(MultipartFile file:files) {
                        //保存附件
                        PubFile pubFile = new PubFile();
                        if (file.getSize() > 10 * 1024 * 1024) {
                            return GlobalConstant.UPLOAD_IMG_SIZE_ERROR + "10M";
                        }
                        //取得当前上传文件的文件名称
                        String oldFileName = file.getOriginalFilename();
                        //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (StringUtil.isNotBlank(oldFileName)) {
                            //定义上传路径
                            String dateString = DateUtil.getCurrDate2();
                            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "gzykdxApplyFile" + File.separator + dateString;
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
                                e.printStackTrace();
                                throw new RuntimeException("保存文件失败！");
                            }
                            String filePath = File.separator + "gzykdxApplyFile" + File.separator + dateString + File.separator + originalFilename;
                            pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                            pubFile.setFilePath(filePath);
                            pubFile.setFileName(oldFileName);
                            pubFile.setProductType("广医论文附件");
                            pubFile.setFileFlow(fileFlow);
                            pubFile.setFileUpType("1");
                            int count = teaAndDocBiz.editThesisDetail(detail,pubFile);
                            if(count==0){
                                return GlobalConstant.SAVE_FAIL;
                            }
                            files.remove(file);
                            break;
                        }
                    }
                }
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = {"/commitApply"})
    @ResponseBody
    public String commitApply(String applyFlow){
        if(StringUtil.isNotBlank(applyFlow)){
            TeacherTargetApply teacherTargetApply=new TeacherTargetApply();
            teacherTargetApply.setApplyFlow(applyFlow);
            teacherTargetApply.setAuditStatusId(GzykdxAuditStatusEnum.WaitingForOrg.getId());
            teacherTargetApply.setAuditStatusName(GzykdxAuditStatusEnum.WaitingForOrg.getName());
            teacherTargetApply.setTeacherApplyTime(DateUtil.getCurrDate());
            teaAndDocBiz.editTeacherTargetApply(teacherTargetApply);
            return GlobalConstant.PREPARE_APPROVAL_SUCCESSED;
        }else {
            return GlobalConstant.PREPARE_APPROVAL_FAIL;
        }
    }

    /**
     * 考生录取查询（导师）
     * @param
     * @param model
     * @return
     */
    @RequestMapping(value = {"/doctorAdmissionList"})
    public String doctorAdmissionList(String doctorCode,String doctorName,String speId,String schoolAuditStatusId,String recruitYear,
                                      String degreeTypeId,String researchDirectionName,Integer currentPage,HttpServletRequest request,Model model)throws DocumentException {
        if(StringUtil.isBlank(recruitYear)){
            recruitYear=new SimpleDateFormat("yyyy").format(new Date());
        }
        Map<String,String> map =new HashMap<>();
//        map.put("userIdNo",GlobalContext.getCurrentUser().getIdNo());
        map.put("userFlow",GlobalContext.getCurrentUser().getUserFlow());
        map.put("doctorCode",doctorCode);
        map.put("doctorName",doctorName);
        map.put("speId",speId);
        map.put("schoolAuditStatusId",schoolAuditStatusId);
        map.put("recruitYear",recruitYear);
        map.put("degreeTypeId",degreeTypeId);
        map.put("researchAreaName",researchDirectionName);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> doctorAdmissionList=teaAndDocBiz.selectDoctorAdmissionList(map);
        GzykdxRecruitExtInfoForm infoForm=new GzykdxRecruitExtInfoForm();
        if(doctorAdmissionList!=null&&doctorAdmissionList.size()>0){
            for (Map<String,Object> temp:doctorAdmissionList) {
                PubUserResume pubUserResume=pubUserResumeBiz.readPubUserResume((String)temp.get("DOCTOR_FLOW"));
                if(pubUserResume!=null){
                    String xmlContent =  pubUserResume.getUserResume();
                    if(StringUtil.isNotBlank(xmlContent)){
                        //xml转换成JavaBean
                        infoForm=pubUserResumeBiz.converyToJavaBean(xmlContent,GzykdxRecruitExtInfoForm.class);
                        temp.put("infoForm", infoForm);
                    }
                }
            }
        }
        model.addAttribute("recruitYear",recruitYear);
        model.addAttribute("doctorAdmissionList",doctorAdmissionList);
        return "/gzykdx/teacher/doctorInfo";
    }

    /**
     * 录取信息查询（考生）
     * @param doctorFlow
     * @return
     */
    @RequestMapping(value = {"/doctorAdmission"})
    public String doctorAdmission(String doctorFlow,Model model)throws DocumentException {
        if(StringUtil.isBlank(doctorFlow)){
            doctorFlow=GlobalContext.getCurrentUser().getUserFlow();
        }
        DoctorTeacherRecruit doctorTeacherRecruit=new DoctorTeacherRecruit();
        doctorTeacherRecruit.setDoctorFlow(GlobalContext.getCurrentUser().getUserFlow());
        doctorTeacherRecruit.setRecruitYear(new SimpleDateFormat("yyyy").format(new Date()));
        List<DoctorTeacherRecruit> list=teaAndDocBiz.searchDoctorTeacherRecruits(doctorTeacherRecruit);
        GzykdxRecruitExtInfoForm infoForm=new GzykdxRecruitExtInfoForm();
        PubUserResume pubUserResume=pubUserResumeBiz.readPubUserResume(doctorFlow);
        if(pubUserResume!=null){
            String xmlContent =  pubUserResume.getUserResume();
            if(StringUtil.isNotBlank(xmlContent)){
                //xml转换成JavaBean
                infoForm=pubUserResumeBiz.converyToJavaBean(xmlContent,GzykdxRecruitExtInfoForm.class);
                model.addAttribute("infoForm", infoForm);
            }
        }
        SysUser user=userBiz.readSysUser(doctorFlow);
        model.addAttribute("user",user);
        model.addAttribute("list",list);
        return "/gzykdx/doctor/doctorOwerInfo";
    }

    /**
     * 展示缺额二级机构
     * @return
     */
    @RequestMapping(value = {"/showNotFullOrgs"})
    public String showNotFullOrgs(String year,String orgFlow,String degreeTypeId,String stuSpeId,String flage,Model model){
        Map<String,String> paramMap = new HashMap<>();
        String thisYear = DateUtil.getYear();
        if(!StringUtil.isNotBlank(year)){
            year = thisYear;
        }
        if(StringUtil.isNotBlank(stuSpeId)){
            String subId=stuSpeId.substring(0,4);
            if("1051".equals(subId)){
                degreeTypeId="";
            }else if("1052".equals(subId) || "1053".equals(subId)
                    || "1055".equals(subId)){
                degreeTypeId=GzykdxDegreeTypeEnum.ProfessionalType.getId();
                flage=GzykdxDegreeTypeEnum.ProfessionalType.getId();
            }else {
                degreeTypeId=GzykdxDegreeTypeEnum.AcademicType.getId();
                flage=GzykdxDegreeTypeEnum.AcademicType.getId();
            }
        }
        paramMap.put("year",year);
        paramMap.put("orgFlow",orgFlow);
        List<Map<String,String>> recruitInfoList = teaAndDocBiz.notFullOrglist(orgFlow,degreeTypeId,year);
        //查询所有二级机构
        SysOrg org = new SysOrg();
        org.setIsSecondFlag("Y");
        List<SysOrg> orgList = orgBiz.searchOrg(org);
        model.addAttribute("recruitInfoList",recruitInfoList);
        model.addAttribute("thisYear",thisYear);
        model.addAttribute("orgList",orgList);
        model.addAttribute("degreeTypeId",degreeTypeId);
        model.addAttribute("flage",flage);
        model.addAttribute("stuSpeId",stuSpeId);
        return "/gzykdx/doctor/changeTeacherSchool";
    }

    /**
     * 展示调剂导师页面
     * @return
     */
    @RequestMapping(value = {"/showNotFullTeachers"})
    public String showNotFullTeachers(String degreeTypeId,String orgFlow,String userFlow,String userName,String speId,String researchDirectionId,String stuSpeId,Model model){
        String isAcademic="";
        String isSpecialized="";
        if(GzykdxDegreeTypeEnum.AcademicType.getId().equals(degreeTypeId)){
            isAcademic=GlobalConstant.IS_EXAM_TEA_Y;
        }
        if(GzykdxDegreeTypeEnum.ProfessionalType.getId().equals(degreeTypeId)){
            isSpecialized=GlobalConstant.IS_EXAM_TEA_Y;
        }
        if(StringUtil.isNotBlank(stuSpeId)){
            String subId=stuSpeId.substring(0,4);
            if("1051".equals(subId)){
                isAcademic="";
                isSpecialized="";
            }else if("1052".equals(subId) || "1053".equals(subId)
                    || "1055".equals(subId)){
                isAcademic="";
                isSpecialized=GlobalConstant.IS_EXAM_TEA_Y;
            }else {
                isAcademic=GlobalConstant.IS_EXAM_TEA_Y;
                isSpecialized="";
            }
        }
        Map<String,String> map=new HashMap<>();
        map.put("isAcademic",isAcademic);
        map.put("isSpecialized",isSpecialized);
        map.put("orgFlow",orgFlow);
        map.put("recruitYear",new SimpleDateFormat("yyyy").format(new Date()));
        map.put("userFlow",userFlow);
        map.put("userName",userName);
        map.put("speId",speId);
        map.put("researchDirectionId",researchDirectionId);
        List<Map<String,String>> teacherApplyList=teaAndDocBiz.searchTeacherNotFullNew(map);
        model.addAttribute("teacherApplyList",teacherApplyList);
        model.addAttribute("thisYear",new SimpleDateFormat("yyyy").format(new Date()));
        model.addAttribute("orgFlow",orgFlow);
        model.addAttribute("degreeTypeId",degreeTypeId);
        model.addAttribute("stuSpeId",stuSpeId);
        return "/gzykdx/doctor/changeTeacher";
    }
private TeacherTargetApplyMapper mapper;
    /**
     * 保存学员调剂
     * @param applyFlow
     * @return
     */
    @RequestMapping(value = {"/saveChose"})
    @ResponseBody
    public String saveChose(String applyFlow){
        String teacherFlow="";
        TeacherTargetApply apply=new TeacherTargetApply();
        apply.setApplyFlow(applyFlow);
        List<TeacherTargetApply> list=teaAndDocBiz.selectTargetApplyList(apply);
        if(list!=null&&list.size()>0){
            apply=list.get(0);
            teacherFlow=apply.getUserFlow();
        }
        SysUser teacher=userBiz.findByFlow(teacherFlow);
        String doctorFlow=GlobalContext.getCurrentUser().getUserFlow();
        //调剂前学员报考导师旧数据
        DoctorTeacherRecruit recruit=new DoctorTeacherRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecruitYear(new SimpleDateFormat("yyyy").format(new Date()));
        List<DoctorTeacherRecruit> recruits=teaAndDocBiz.searchDoctorTeacherRecruits(recruit);
        if(recruits!=null&&recruits.size()>0){
            recruit=recruits.get(0);
        }
        //旧数据插入调剂批次记录表
        DoctorTeacherRecruitBatch recruitBatch=new DoctorTeacherRecruitBatch();
        recruitBatch.setRefRecordFlow(recruit.getRecordFlow());
        recruitBatch.setUserFlow(recruit.getUserFlow());
        recruitBatch.setUserName(recruit.getUserName());
        recruitBatch.setDoctorFlow(recruit.getDoctorFlow());
        recruitBatch.setDoctorName(recruit.getDoctorName());
        recruitBatch.setOrgFlow(recruit.getOrgFlow());
        recruitBatch.setOrgName(recruit.getOrgName());
        recruitBatch.setSpeId(recruit.getSpeId());
        recruitBatch.setSpeName(recruit.getSpeName());
        recruitBatch.setRecruitYear(recruit.getRecruitYear());
        recruitBatch.setDegreeTypeId(recruit.getDegreeTypeId());
        recruitBatch.setDegreeTypeName(recruit.getDegreeTypeName());
        recruitBatch.setRecruitTypeId(recruit.getRecruitTypeId());
        recruitBatch.setRecruitTypeName(recruit.getRecruitTypeName());
        recruitBatch.setRecruitBatch(recruit.getRecruitBatch());
        recruitBatch.setAuditStatusId(recruit.getAuditStatusId());
        recruitBatch.setAuditStatusName(recruit.getAuditStatusName());
        recruitBatch.setSchoolAuditStatusId(recruit.getSchoolAuditStatusId());
        recruitBatch.setSchoolAuditStatusName(recruit.getSchoolAuditStatusName());
        recruitBatch.setOrgAuditMemo(recruit.getOrgAuditMemo());
        recruitBatch.setSchoolAuditMemo(recruit.getSchoolAuditMemo());
        recruitBatch.setResearchAreaId(recruit.getResearchAreaId());
        recruitBatch.setResearchAreaName(recruit.getResearchAreaName());
        recruitBatch.setUserIdNo(recruit.getUserIdNo());
        teaAndDocBiz.editDoctorTeacherRecruitBatch(recruitBatch);
        String batch=Integer.parseInt(recruit.getRecruitBatch())+1+"";
        //调剂后学员报考导师信息
        String degreeTypeId="";
        if(StringUtil.isNotBlank(apply.getIsAcademic())&&"Y".equals(apply.getIsAcademic())){
            degreeTypeId=GzykdxDegreeTypeEnum.AcademicType.getId();
        }
        if(StringUtil.isNotBlank(apply.getIsSpecialized())&&"Y".equals(apply.getIsSpecialized())){
            degreeTypeId=GzykdxDegreeTypeEnum.ProfessionalType.getId();
        }
        recruit.setUserFlow(apply.getUserFlow());
        recruit.setUserName(apply.getUserName());
        recruit.setOrgFlow(apply.getOrgFlow());
        recruit.setOrgName(apply.getOrgName());
        recruit.setSpeId(apply.getSpeId());
        recruit.setSpeName(apply.getSpeName());
        recruit.setRecruitYear(apply.getRecruitYear());
        recruit.setDegreeTypeId(degreeTypeId);
        recruit.setDegreeTypeName(GzykdxDegreeTypeEnum.getNameById(degreeTypeId));
        recruit.setResearchAreaId(apply.getResearchDirectionId());
        recruit.setResearchAreaName(apply.getResearchDirection());
        recruit.setRecruitBatch(batch);
        recruit.setUserIdNo(teacher==null?"":teacher.getIdNo());
        recruit.setAuditStatusId(GzykdxAdmissionStatusEnum.Passing.getId());
        recruit.setAuditStatusName(GzykdxAdmissionStatusEnum.Passing.getName());
        recruit.setSchoolAuditStatusId(GzykdxAdmissionStatusEnum.Passing.getId());
        recruit.setSchoolAuditStatusName(GzykdxAdmissionStatusEnum.Passing.getName());
        recruit.setOrgAuditMemo("");
        recruit.setSchoolAuditMemo("");
        recruit.setFinalUserFlow(apply.getUserFlow());
        recruit.setFinalUserName(apply.getUserName());
        recruit.setFinalSpeId(apply.getSpeId());
        recruit.setFinalSpeName(apply.getSpeName());
        recruit.setFinalResearchAreaId(apply.getResearchDirectionId());
        recruit.setFinalResearchAreaName(apply.getResearchDirection());
        recruit.setFinalOrgFlow(apply.getOrgFlow());
        recruit.setFinalOrgName(apply.getOrgName());
        recruit.setFinalDegreeTypeId(degreeTypeId);
        recruit.setFinalDegreeTypeName(GzykdxDegreeTypeEnum.getNameById(degreeTypeId));
        int num=teaAndDocBiz.editDoctorTeacherRecruits(recruit);
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 展示导师注册页面
     * @return
     */
    @RequestMapping(value = {"/showTeacherRegist"})
    public String showTeacherRegist(Model model){
        //查询所有二级机构
        SysOrg org = new SysOrg();
        org.setIsSecondFlag("Y");
        List<SysOrg> orgList = orgBiz.searchOrg(org);
        model.addAttribute("orgList",orgList);
        return "/gzykdx/teacher/registerTeacher";
    }

    /**
     * 导师注册校验
     * @param userCode
     * @param idNo
     * @return
     */
    @RequestMapping(value = {"/checkTeacherRegist"})
    @ResponseBody
    public String checkTeacherRegist(String userCode,String idNo){
        List<SysUser> list=new ArrayList<>();
        SysUser user=new SysUser();
        if(StringUtil.isNotBlank(userCode)){
            user.setUserCode(userCode);
            list=userBiz.searchUser(user);
            if(list!=null&&list.size()>0){
                return GlobalConstant.USER_CODE_REPETE;
            }
        }
        if(StringUtil.isNotBlank(idNo)){
            user.setIdNo(idNo);
            list=userBiz.searchUser(user);
            if(list!=null&&list.size()>0){
                return GlobalConstant.USER_ID_NO_REPETE;
            }
        }
        return GlobalConstant.NULL;
    }

    /**
     * 保存导师注册
     * @return
     */
    @RequestMapping(value = {"/registTeacher"})
    @ResponseBody
    public String registTeacher(String jsondata)throws IOException {
        jsondata = java.net.URLDecoder.decode(jsondata);
        Map<String,Object> mp = JSON.parseObject(jsondata,Map.class);
        SysUser user=new SysUser();
        GzykdxTeacherExtInfoForm teacherExtInfoForm=new GzykdxTeacherExtInfoForm();
        Map<String,String> applyInfo=(Map<String,String>)mp.get("applyInfo");
        int num=0;
        if(applyInfo!=null) {
            String orgName="";
            String orgIdentifyNo="";
            SysOrg org=orgBiz.readSysOrg(applyInfo.get("orgFlow"));
            if(org!=null){
                orgName=org.getOrgName();
                orgIdentifyNo=org.getIdentifyNo();
            }
            String identifyNo= applyInfo.get("identifyNo");
            if(StringUtil.isNotBlank(identifyNo)&&StringUtil.isNotBlank(orgIdentifyNo)){
                if(!(identifyNo.toUpperCase()).equals(orgIdentifyNo.toUpperCase())){
                    return GlobalConstant.NULL;
                }
            }
            user.setUserFlow(PkUtil.getUUID());
            user.setUserCode(applyInfo.get("userCode"));
            user.setUserName(applyInfo.get("userName"));
            user.setUserBirthday(applyInfo.get("userBirthday"));
            user.setIdNo(applyInfo.get("idNo"));
            user.setUserPhone(applyInfo.get("userPhone"));
            user.setOrgFlow(applyInfo.get("orgFlow"));
            user.setOrgName(orgName);
            user.setSexId(applyInfo.get("sexId"));
            user.setSexName(UserSexEnum.getNameById(applyInfo.get("sexId")));
            user.setEducationId(applyInfo.get("educationId"));
            user.setEducationName(DictTypeEnum.HighestEducation.getDictNameById(applyInfo.get("educationId")));
            user.setDegreeId(applyInfo.get("degreeId"));
            user.setDegreeName(DictTypeEnum.HighestDegree.getDictNameById(applyInfo.get("degreeId")));
            user.setTitleName(applyInfo.get("titleName"));
//            user.setTitleName(DictTypeEnum.GzykdxUserTitle.getDictNameById(applyInfo.get("titleId")));
            user.setReportingAuthority(GlobalConstant.RECORD_STATUS_Y);
            teacherExtInfoForm.setAge(applyInfo.get("age"));
            teacherExtInfoForm.setWorkAreaYear(applyInfo.get("workAreaYear"));
            teacherExtInfoForm.setWorkDate(applyInfo.get("workDate"));
            teacherExtInfoForm.setEducationSchool(applyInfo.get("educationSchool"));
            teacherExtInfoForm.setEducationDate(applyInfo.get("educationDate"));
            teacherExtInfoForm.setDegreeOrg(applyInfo.get("degreeOrg"));
            teacherExtInfoForm.setDegreeDate(applyInfo.get("degreeDate"));
            num=userBiz.addUser(user);
            teaAndDocBiz.saveTeacherInfo(user, teacherExtInfoForm);
            SysUserRole userRole = new SysUserRole();//插入学员角色
            userRole.setRecordFlow(PkUtil.getUUID());
            userRole.setWsId("gzykdx");
            userRole.setUserFlow(user.getUserFlow());
            userRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            userRole.setAuthTime(DateUtil.getCurrDateTime());
            userRole.setAuthUserFlow(user.getUserFlow());
            userRole.setCreateTime(DateUtil.getCurrDateTime());
            userRole.setCreateUserFlow(user.getUserFlow());
            userRole.setModifyTime(DateUtil.getCurrDateTime());
            userRole.setModifyUserFlow(user.getUserFlow());
            SysCfg cfg = cfgBiz.read("gzykdx_teacher_role_flow");
            String doctorRole ="";
            if (null != cfg) {
                doctorRole = cfg.getCfgValue();
                if(!StringUtil.isNotBlank(doctorRole)){
                    doctorRole = cfg.getCfgBigValue();
                }
            }
            if(StringUtil.isNotBlank(doctorRole)){
                userRole.setRoleFlow(doctorRole);
            }
            userRoleBiz.addSysUserRole(userRole);
            DoctorTeacherRecruit doctorTeacherRecruit=new DoctorTeacherRecruit();
            doctorTeacherRecruit.setUserFlow(user.getUserFlow());
            doctorTeacherRecruit.setUserIdNo(user.getIdNo());
            doctorTeacherRecruit.setOrgFlow(user.getOrgFlow());
            doctorTeacherRecruit.setOrgName(user.getOrgName());
            doctorTeacherRecruit.setFinalUserFlow(user.getUserFlow());
            doctorTeacherRecruit.setFinalOrgFlow(user.getOrgFlow());
            doctorTeacherRecruit.setFinalOrgName(user.getOrgName());
            teaAndDocBiz.editRecruitsByUserIdNo(doctorTeacherRecruit);
        }
        if(num>0){
            return GlobalConstant.USER_REG_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 考生录取审核记录
     * @param doctorFlow
     * @return
     */
    @RequestMapping(value = {"/auditStatusList"})
    public String auditStatusList(String doctorFlow,Model model){
        DoctorTeacherRecruit recruit=new DoctorTeacherRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecruitYear(DateUtil.getYear());
        List<DoctorTeacherRecruit> recruits=teaAndDocBiz.searchDoctorTeacherRecruits(recruit);
        DoctorTeacherRecruitBatch recruitBatch=new DoctorTeacherRecruitBatch();
        recruitBatch.setRecruitYear(DateUtil.getYear());
        recruitBatch.setDoctorFlow(doctorFlow);
        List<DoctorTeacherRecruitBatch> recruitBatches=teaAndDocBiz.searchDoctorTeacherRecruitBatchs(recruitBatch);
        model.addAttribute("recruits",recruits);
        model.addAttribute("recruitBatches",recruitBatches);
        return "/gzykdx/doctor/auditStatusList";
    }

    /**
     * 返回指标申请打印页面
     */
    @RequestMapping("/printApply")
    public String printApply(String applyFlow, Model model)throws DocumentException {
        List<TeacherThesisDetail> teacherThesisList=null;
        List<TeacherThesisDetail> doctorThesisList=null;
        List<TeacherThesisDetail> researchProjectList=null;
        String userFlow="";
        List<Map<String,Object>> teacherThesis=new ArrayList<>();
        List<Map<String,Object>> doctorThesis=new ArrayList<>();
        List<Map<String,Object>> researchProject=new ArrayList<>();
        if(StringUtil.isNotBlank(applyFlow)){
            TeacherTargetApply teacherTargetApply=new TeacherTargetApply();
            teacherTargetApply.setApplyFlow(applyFlow);
            List<TeacherTargetApply> teacherTargetApplyList=teaAndDocBiz.selectTargetApplyList(teacherTargetApply);
            if(teacherTargetApplyList!=null&&teacherTargetApplyList.size()>0){
                userFlow=teacherTargetApplyList.get(0).getUserFlow();
                model.addAttribute("targetApply",teacherTargetApplyList.get(0));
            }
            PubFile fileTemp=new PubFile();
            TeacherThesisDetail detail=new TeacherThesisDetail();
            detail.setApplyFlow(applyFlow);
            detail.setThesisTypeId(GzykdxThesisTypeEnum.TeacherThesis.getId());
            teacherThesisList=teaAndDocBiz.detailList(detail);
            if(teacherThesisList!=null&&teacherThesisList.size()>0){
                for(TeacherThesisDetail ttd:teacherThesisList){
                    Map<String,Object> temp=new HashMap<>();
                    fileTemp=teaAndDocBiz.readPubFlie(ttd.getFileFlow());
                    temp.put("teacherThesis",ttd);
                    temp.put("file",fileTemp);
                    teacherThesis.add(temp);
                }
            }
            detail.setThesisTypeId(GzykdxThesisTypeEnum.DoctorThesis.getId());
            doctorThesisList=teaAndDocBiz.detailList(detail);
            if(doctorThesisList!=null&&doctorThesisList.size()>0){
                for(TeacherThesisDetail ttd:doctorThesisList){
                    Map<String,Object> temp=new HashMap<>();
                    fileTemp=teaAndDocBiz.readPubFlie(ttd.getFileFlow());
                    temp.put("doctorThesis",ttd);
                    temp.put("file",fileTemp);
                    doctorThesis.add(temp);
                }
            }
            detail.setThesisTypeId(GzykdxThesisTypeEnum.ResearchProject.getId());
            researchProjectList=teaAndDocBiz.detailList(detail);
            if(researchProjectList!=null&&researchProjectList.size()>0){
                for(TeacherThesisDetail ttd:researchProjectList){
                    Map<String,Object> temp=new HashMap<>();
                    fileTemp=teaAndDocBiz.readPubFlie(ttd.getFileFlow());
                    temp.put("researchProject",ttd);
                    temp.put("file",fileTemp);
                    researchProject.add(temp);
                }
            }
        }
        userFlow=GlobalContext.getCurrentUser().getUserFlow();
        List<SysOrg> orgList=teaAndDocBiz.searchOrgList();
        SysUser user=userBiz.readSysUser(userFlow);
        PubUserResume pubUserResume=pubUserResumeBiz.readPubUserResume(userFlow);
        if(pubUserResume!=null){
            String xmlContent =  pubUserResume.getUserResume();
            if(StringUtil.isNotBlank(xmlContent)){
                //xml转换成JavaBean
                GzykdxTeacherExtInfoForm extInfo=null;
                extInfo=pubUserResumeBiz.converyToJavaBean(xmlContent,GzykdxTeacherExtInfoForm.class);
                model.addAttribute("extInfo", extInfo);
            }
        }
        model.addAttribute("orgList",orgList);
        model.addAttribute("user",user);
        model.addAttribute("teacherThesisList",teacherThesis);
        model.addAttribute("doctorThesisList",doctorThesis);
        model.addAttribute("researchProjectList",researchProject);
        return "/gzykdx/teacher/printApply";
    }
}
