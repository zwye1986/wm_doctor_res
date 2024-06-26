package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResFolowTeacherRecordBiz;
import com.pinde.sci.biz.res.IResTypicalCasesBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.FileUtil;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.res.DiscipleStatusEnum;
import com.pinde.sci.enums.res.NoteTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.apache.commons.lang.StringUtils;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by pdkj on 2016/10/14.
 */
@Controller
@RequestMapping("/res/typicalCases")
public class ResTypicalCasesController extends GeneralController {

    @Autowired
    private IResTypicalCasesBiz resTypicalCasesBiz;
    @Autowired
    private IResFolowTeacherRecordBiz resFolowTeacherRecordBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    /**
     * 学员展示跟师医案页面
     * @param model
     * @param caseFlow
     * @param roleId
     * @param doctorFlow
     * @param currentPage
     * @return
     */
    @RequestMapping(value="/showTypicalCases",method={RequestMethod.POST,RequestMethod.GET})
    public String showTypicalCases(Model model,String caseFlow,String roleId,String doctorFlow,String currentPage,String teacherCurrentPage,String teacherFlow){
        SysUser sysUser = GlobalContext.getCurrentUser();
        List<ResTypicalCases> resTypicalCasesList = null;
        ResTypicalCases resTypicalCases = new ResTypicalCases();
        resTypicalCases.setDoctorFlow(sysUser.getUserFlow());
        if("teacher".equals(roleId)){
            resTypicalCases.setDoctorFlow(doctorFlow);
            resTypicalCases.setTeacherFlow(sysUser.getUserFlow());
            resTypicalCases.setAuditStatusId(DiscipleStatusEnum.PendingAudit.getId());
            resTypicalCases.setAuditStatusName(DiscipleStatusEnum.PendingAudit.getName());
            model.addAttribute("currentPage",currentPage);
            model.addAttribute("doctorFlow",doctorFlow);
        }
        if("see".equals(roleId)||"adminSee".equals(roleId)){
            resTypicalCases.setDoctorFlow(doctorFlow);
            if("teacher".equals(teacherFlow)){
                resTypicalCases.setTeacherFlow(sysUser.getUserFlow());
            }
            resTypicalCases.setAuditStatusId(DiscipleStatusEnum.Submit.getId());
            resTypicalCases.setAuditStatusName(DiscipleStatusEnum.Submit.getName());
            model.addAttribute("doctorFlow",doctorFlow);
            model.addAttribute("teacherCurrentPage",teacherCurrentPage);
        }
        if("globalSee".equals(roleId)){
            resTypicalCases.setDoctorFlow(doctorFlow);
            if("teacher".equals(teacherFlow)){
                resTypicalCases.setTeacherFlow(sysUser.getUserFlow());
            }
            resTypicalCases.setAuditStatusId(DiscipleStatusEnum.Qualified.getId());
            resTypicalCases.setAuditStatusName(DiscipleStatusEnum.Qualified.getName());
            model.addAttribute("doctorFlow",doctorFlow);
            model.addAttribute("teacherCurrentPage",teacherCurrentPage);
        }
        if(StringUtil.isBlank(roleId)){
            roleId="";
        }
        model.addAttribute("roleId",roleId);
        resTypicalCasesList = resTypicalCasesBiz.searchTypicalCases(resTypicalCases);
        if("teacher".equals(roleId)&&(resTypicalCasesList==null||resTypicalCasesList.size()==0)){
            return "redirect:/res/typicalCases/teacherMain";
        }
        List<ResTypicalCases> casesList = new ArrayList<>();
        for (ResTypicalCases cases: resTypicalCasesList) {
//            cases.setCreateTime(DateUtil.transDateTime(cases.getCreateTime()));
            casesList.add(cases);
        }
        if(StringUtil.isBlank(caseFlow)&&resTypicalCasesList != null && resTypicalCasesList.size()>0){
            caseFlow = resTypicalCasesList.get(0).getRecordFlow();
        }
        if(StringUtil.isNotBlank(caseFlow)){
            ResTypicalCases caseDetail = resTypicalCasesBiz.findResTypicalCases(caseFlow);
            model.addAttribute("seeFlag",caseDetail.getAuditStatusId());
            model.addAttribute("resTypicalCases",caseDetail);
            String times = caseDetail.getStudentSignTime();
            times=times.substring(0,4)+"年"+times.substring(4,6)+"月"+times.substring(6,8)+"日";
            if(StringUtil.isNotBlank(caseDetail.getAuditTime())){
                String timea = caseDetail.getAuditTime();
                timea=timea.substring(0,4)+"年"+timea.substring(4,6)+"月"+timea.substring(6,8)+"日";
                model.addAttribute("auditTime",timea);
            }
            model.addAttribute("signTime",times);
            //查询附件
            List<PubFile> discipleFiles = pubFileBiz.searchByProductFlow(caseFlow);
            model.addAttribute("discipleFiles",discipleFiles);
        }
        String temp = DateUtil.getCurrDate();
        String currDate=DateUtil.getCurrDateTime("yyyy")+"年"+DateUtil.getCurrDateTime("MM")+"月"+DateUtil.getCurrDateTime("dd")+"日";
        model.addAttribute("resTypicalCasesList",casesList);
        model.addAttribute("sysUser",sysUser);
        model.addAttribute("currDate",currDate);
        model.addAttribute("resStudentDiscipleTeacher",sysUser.getUserName());
        if("globalSee".equals(roleId)){
            return "res/disciple/typicalCasesView4Global";
        }
        return "res/disciple/typicalCasesView";
    }

    @RequestMapping("/exportDoc")
    public void exportDoc(String caseFlow, HttpServletRequest request, HttpServletResponse response)throws Exception{
        List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        ResTypicalCases caseDetail = resTypicalCasesBiz.findResTypicalCases(caseFlow);
        dataMap = getClassFieldMap(caseDetail);
        String times = caseDetail.getStudentSignTime();
        times=times.substring(0,4)+"年"+times.substring(4,6)+"月"+times.substring(6,8)+"日";
        if(StringUtil.isNotBlank(caseDetail.getAuditTime())){
            String timea = caseDetail.getAuditTime();
            timea=timea.substring(0,4)+"年"+timea.substring(4,6)+"月"+timea.substring(6,8)+"日";
            dataMap.put("auditTime",timea);
        }
        dataMap.put("signTime",times);
        WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
        String path = "";
        String name = "";
        path = "/jsp/res/disciple/export/typicalCasesViewTemp.docx";//模板
        name = "跟师医案.docx";

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

    //获取属性名和值
    private Map<String,Object> getClassFieldMap(Object obj){
        Map<String,Object> filedMap = null;
        if(obj!=null){
            try{
                filedMap = new HashMap<String, Object>();
                String stringClassName = String.class.getSimpleName();
                Class<?> objClass = obj.getClass();
                Field[] fileds = objClass.getDeclaredFields();
                for(Field f : fileds){
                    String typeName = f.getType().getSimpleName();
                    if(stringClassName.equals(typeName)){
                        String attrName = f.getName();
                        String firstLetter = attrName.substring(0,1).toUpperCase();
                        String methedName = "get"+firstLetter+attrName.substring(1);
                        Method getMethod = objClass.getMethod(methedName);
                        String value = (String)getMethod.invoke(obj);
                        filedMap.put(attrName, StringUtils.defaultString(value));
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return filedMap;
    }

    /**
     * 修改医案
     * @param resTypicalCases
     * @param recordFlow
     * @return
     */
    @RequestMapping(value="editTypicalCase")
    public @ResponseBody String editTypicalCase(ResTypicalCases resTypicalCases,String recordFlow, String jsonData,
                                                HttpServletRequest request) throws UnsupportedEncodingException {
        //学员端多图片上传
        //校验上传文件大小及格式
        String checkResult = checkFiles(request);
        if (!"1".equals(checkResult)) {
            return checkResult;
        }
        ResTypicalCases newCase = doSave(resTypicalCases,recordFlow);
        int count = resTypicalCasesBiz.saveResTypicalCases(newCase);
        jsonData = java.net.URLDecoder.decode(jsonData, "UTF-8");
        Map<String, Object> mp = JSON.parseObject(jsonData, Map.class);
        //学员端多图片上传
        //上传文件的流水号
        List<String> fileFlows = (List<String>) mp.get("fileFlows");
        //处理不在本次保存中的文件
        upadteFileInfo(newCase.getRecordFlow(), fileFlows);
        //处理上传文件
        addUploadFile(newCase.getRecordFlow(), request, "TypicalCases");
        if( count==0){
            return GlobalConstant.OPERATE_FAIL;
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }
    public ResTypicalCases doSave(ResTypicalCases resTypicalCases,String recordFlow){
        ResTypicalCases newCase = new ResTypicalCases();
        if(StringUtil.isNotBlank(recordFlow)){
            newCase=resTypicalCasesBiz.findResTypicalCases(recordFlow);
            newCase.setPeopleName(resTypicalCases.getPeopleName());
            newCase.setSexId(resTypicalCases.getSexId());
            newCase.setBirthDate(resTypicalCases.getBirthDate());
            newCase.setVisitDate(resTypicalCases.getVisitDate());
            newCase.setVisitActionId(resTypicalCases.getVisitActionId());
            newCase.setSolarTerms(resTypicalCases.getSolarTerms());
            newCase.setMainSuit(resTypicalCases.getMainSuit());
            newCase.setPresentDiseaseHistory(resTypicalCases.getPresentDiseaseHistory());
            newCase.setPreviousDiseaseHistory(resTypicalCases.getPreviousDiseaseHistory());
            newCase.setAllergicHistory(resTypicalCases.getAllergicHistory());
            newCase.setPhysicalExamination(resTypicalCases.getPhysicalExamination());
            newCase.setAccessoryExamination(resTypicalCases.getAccessoryExamination());
            newCase.setTcmDiagnosis(resTypicalCases.getTcmDiagnosis());
            newCase.setSyndromeDiagnosis(resTypicalCases.getSyndromeDiagnosis());
            newCase.setWesternDiagnosis(resTypicalCases.getWesternDiagnosis());
            newCase.setTherapy(resTypicalCases.getTherapy());
            newCase.setPrescription(resTypicalCases.getPrescription());
            newCase.setReturnVisit(resTypicalCases.getReturnVisit());
            newCase.setExperienceContent(resTypicalCases.getExperienceContent());
        }else {
            newCase=resTypicalCases;
            SysUser sysUser = GlobalContext.getCurrentUser();
            ResDoctor resdoctor=doctorBiz.readDoctor(sysUser.getUserFlow());
            newCase.setAuditStatusId(DiscipleStatusEnum.Apply.getId());
            newCase.setAuditStatusName(DiscipleStatusEnum.Apply.getName());
            newCase.setDoctorFlow(sysUser.getUserFlow());
            newCase.setDoctorName(sysUser.getUserName());
            newCase.setTeacherFlow(resdoctor.getDiscipleTeacherFlow());
            newCase.setTeacherName(resdoctor.getDiscipleTeacherName());
            newCase.setStudentSignTime(DateUtil.getCurrDateTime());
            newCase.setRecordYear(DateUtil.getYear());
        }
        if("Man".equals(newCase.getSexId())){
            newCase.setSexId(UserSexEnum.Man.getId());
            newCase.setSexName(UserSexEnum.Man.getName());
        }else {
            newCase.setSexId(UserSexEnum.Woman.getId());
            newCase.setSexName(UserSexEnum.Woman.getName());
        }
        if("chuzhen".equals(newCase.getVisitActionId())){
            newCase.setVisitActionName("初诊");
        }else {
            newCase.setVisitActionName("复诊");
        }
        return newCase;
    }

    /**
     * 删除
     * @param recordFlow
     * @return
     */
    @RequestMapping(value="/removeTypicalCase")
    public @ResponseBody String removeTypicalCase(String recordFlow){
        ResTypicalCases resTypicalCases = new ResTypicalCases();
        resTypicalCases.setRecordFlow(recordFlow);
        resTypicalCases = resTypicalCasesBiz.findResTypicalCases(recordFlow);
        if(StringUtil.isNotBlank(recordFlow)){
            resTypicalCases.setRecordStatus(GlobalConstant.FLAG_N);
            resTypicalCasesBiz.saveResTypicalCases(resTypicalCases);
            return GlobalConstant.OPERATE_SUCCESSED;
        }else {
            return GlobalConstant.DELETE_FAIL;
        }
    }

    /**
     * 提交
     * @param recordFlow
     * @param cases
     * @return
     */
    @RequestMapping(value="/subTypicalCase")
    public @ResponseBody String subTypicalCase(String recordFlow,ResTypicalCases cases, String jsonData, HttpServletRequest request) throws UnsupportedEncodingException {
        //学员端多图片上传
        //校验上传文件大小及格式
        String checkResult = checkFiles(request);
        if (!"1".equals(checkResult)) {
            return checkResult;
        }
        ResTypicalCases resTypicalCases = new ResTypicalCases();
        resTypicalCases.setRecordFlow(recordFlow);
        resTypicalCases = resTypicalCasesBiz.findResTypicalCases(recordFlow);
        if(resTypicalCases!=null){
            cases=doSave(cases,recordFlow);
//            cases.setAuditStatusId(DiscipleStatusEnum.PendingAudit.getId());
//            cases.setAuditStatusName(DiscipleStatusEnum.PendingAudit.getName());
//            resTypicalCasesBiz.saveResTypicalCases(cases);
//            return GlobalConstant.OPERATE_SUCCESSED;
        }else {
            cases=doSave(cases,"");
//            return GlobalConstant.OPERATE_SUCCESSED;
        }
        cases.setAuditStatusId(DiscipleStatusEnum.PendingAudit.getId());
        cases.setAuditStatusName(DiscipleStatusEnum.PendingAudit.getName());
        cases.setAuditContent("");
        int count = resTypicalCasesBiz.saveResTypicalCases(cases);
        jsonData = java.net.URLDecoder.decode(jsonData, "UTF-8");
        Map<String, Object> mp = JSON.parseObject(jsonData, Map.class);
        //学员端多图片上传
        //上传文件的流水号
        List<String> fileFlows = (List<String>) mp.get("fileFlows");
        //处理不在本次保存中的文件
        upadteFileInfo(cases.getRecordFlow(), fileFlows);
        //处理上传文件
        addUploadFile(cases.getRecordFlow(), request, "TypicalCases");
        if( count==0){
            return GlobalConstant.OPERATE_FAIL;
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 老师展示学员列表
     * @param model
     * @param currentPage
     * @param request
     * @param doctorName
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/teacherMain")
    public String teacherMain(Model model,String sessionNumber,String speId, Integer currentPage, HttpServletRequest request,
                              String doctorName, String doctorCategoryId,String [] datas) throws Exception {
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
//        Map<String,String> beMap = new HashMap<>();
//        if(StringUtil.isNotBlank(doctorName)){
//            doctorName = java.net.URLDecoder.decode(doctorName,"UTF-8");
//            model.addAttribute("doctorName",doctorName);
//            beMap.put("doctorName",doctorName);
//        }
        Map<String,Object> param=new HashMap<>();
        param.put("teacherFlow",sysUser.getUserFlow());
        param.put("sessionNumber",sessionNumber);
        param.put("speId",speId);
        param.put("doctorCategoryId",doctorCategoryId);
        param.put("docName",doctorName);
        param.put("docTypeList",docTypeList);
        PageHelper.startPage(currentPage,getPageSize(request));
//        beMap.put("teacherFlow",sysUser.getUserFlow());
        resStudentDiscipleTeachers = resTypicalCasesBiz.searchCasePendingAudit(param);
        model.addAttribute("resStudentDiscipleTeachers",resStudentDiscipleTeachers);
        model.addAttribute("doctorName",doctorName);
        return "res/disciple/typicalCasesTeacherMain";
    }

    /**
     * 老师审核
     * @param recordFlow
     * @param cases
     * @return
     */
    @RequestMapping(value="/adminTypicalCase")
    public @ResponseBody String adminTypicalCase(String recordFlow,ResTypicalCases cases,String statuId){
        SysUser teacher = GlobalContext.getCurrentUser();
        ResTypicalCases resTypicalCases = new ResTypicalCases();
        resTypicalCases.setRecordFlow(recordFlow);
        resTypicalCases = resTypicalCasesBiz.findResTypicalCases(recordFlow);
        if(StringUtil.isNotBlank(recordFlow)){
            if("1".equals(statuId)){
                resTypicalCases.setAuditStatusId(DiscipleStatusEnum.Qualified.getId());
                resTypicalCases.setAuditStatusName(DiscipleStatusEnum.Qualified.getName());
            }else {
                resTypicalCases.setAuditStatusId(DiscipleStatusEnum.UnQualified.getId());
                resTypicalCases.setAuditStatusName(DiscipleStatusEnum.UnQualified.getName());
            }
            resTypicalCases.setAuditUserFlow(teacher.getUserFlow());
            resTypicalCases.setAuditUserName(teacher.getUserName());
            resTypicalCases.setAuditTime(DateUtil.getCurrentTime());
            resTypicalCases.setAuditContent(cases.getAuditContent());
            int count =resTypicalCasesBiz.saveResTypicalCases(resTypicalCases);
            if(count==0){
                return GlobalConstant.DELETE_FAIL;
            }
            return GlobalConstant.OPERATE_SUCCESSED;
        }else {
            return GlobalConstant.DELETE_FAIL;
        }
    }

    @RequestMapping(value = "/agreeRecordBatch/{roleScope}", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String agreeRecordBatch(@PathVariable String roleScope, String doctorFlow) {

        Map<String, String> paramMap = new HashMap<>();
        SysUser currentUser = GlobalContext.getCurrentUser();
        paramMap.put("doctorFlow", doctorFlow);
        if ("see".equals(roleScope)) {
            //师承老师审核自己的学生
            paramMap.put("teacherFlow", currentUser.getUserFlow());
        } else if ("adminSee".equals(roleScope)) {
            //医院管理员审核该医院下该学生的所有记录
        }
        paramMap.put("auditUserFlow", currentUser.getUserFlow());
        paramMap.put("auditUserName", currentUser.getUserName());
        paramMap.put("oldAuditStatusId", DiscipleStatusEnum.PendingAudit.getId());
        paramMap.put("newAuditStatusId", DiscipleStatusEnum.Qualified.getId());
        paramMap.put("newAuditStatusName", DiscipleStatusEnum.Qualified.getName());
        paramMap.put("auditContent", "该学员对本病有大致的了解，能较好的进行辨病辨证，对相关的经典理论也有一定的涉猎。");
        int i = resTypicalCasesBiz.agreeRecordBatch(paramMap);
        if (i != 0) {
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return "该学员没有待审核记录！";
        }
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
                                e.printStackTrace();
                                throw new RuntimeException("保存文件失败！");
                            }
                            String filePath = File.separator + "discipleFiles" +  File.separator + noteTypeId + File.separator + dateString + File.separator+recordFlow+ File.separator + originalFilename;
                            pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
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
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
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
                        if(StringUtil.isNotBlank(fileName)){
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
        }
        return result;
    }
}
