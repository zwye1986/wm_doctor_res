package com.pinde.sci.ctrl.jszy;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyAuditLogBiz;
import com.pinde.sci.biz.jszy.IJszyGraduationBiz;
import com.pinde.sci.biz.jszy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.enums.jszy.JszyBaseStatusEnum;
import com.pinde.sci.enums.jszy.JszyTrainCategoryEnum;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.enums.res.RecDocCategoryEnum;
import com.pinde.sci.enums.sys.OrgLevelEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pdkj on 2017/9/29.
 */
@Controller
@RequestMapping("/jszy/graduationManager")
public class JszyResGraduationManagerController extends GeneralController {
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IJszyResDoctorRecruitBiz jszyResDoctorRecruitBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IJszyGraduationBiz graduationBiz;
    @Autowired
    private IJszyAuditLogBiz auditLogBiz;
    @Autowired
    private IFileBiz pubFileBiz;

    @RequestMapping("/main")
    public String showMain(Model model, String roleFlag, String pageType, String tabType) {
        SysUser sysuser = GlobalContext.getCurrentUser();
        SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
        SysOrg sysorg = new SysOrg();
        sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        sysorg.setOrgProvId(org.getOrgProvId());
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());
        if (GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            List<SysOrg> orgs = new ArrayList<>();
            orgs.add(org);
            if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
            {
                List<SysOrg> jointOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                if(jointOrgs!=null&&jointOrgs.size()>0)
                {
                    orgs.addAll(jointOrgs);
                }
            }
            model.addAttribute("orgs", orgs);
        } else {
            List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
            model.addAttribute("orgs", orgs);
        }
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("pageType", pageType);
        model.addAttribute("tabType", tabType);
        model.addAttribute("currOrg", org);
        model.addAttribute("user", GlobalContext.getCurrentUser());
        return "jszy/graduationManager/global/query/main";
    }
    @RequestMapping("/doctorMain")
    public String doctorMain(Model model, String roleFlag, String pageType, String tabType) {
        SysUser sysuser = GlobalContext.getCurrentUser();
        ResDoctorGraduationInfo resDoctorGraduationInfo =new ResDoctorGraduationInfo();
        resDoctorGraduationInfo.setDoctorFlow(sysuser.getUserFlow());
        Map<String, Object> param = new HashMap<>();
        param.put("certificateNumber", "Y");
        param.put("order", "certificate_Number asc");
        param.put("resDoctorGraduationInfo", resDoctorGraduationInfo);
        List<ResDoctorGraduationInfo> graduationInfos = graduationBiz.searchGraduationInfoByMap(param);
        model.addAttribute("datas", graduationInfos);
        return "jszy/graduationManager/doctor/doctorMain";
    }
    @RequestMapping("/docQuery")
    public String docQuery(Model model, String recordFlow) {
        model.addAttribute("recordFlow",recordFlow);
        return "jszy/graduationManager/doctor/docQuery";
    }

    @RequestMapping(value = "/list/{roleFlag}/{pageType}/{tabType}")
    public String list(Model model, Integer currentPage, HttpServletRequest request,
                       String orgCityId,  String orgFlow, ResDoctorGraduationInfo resDoctorGraduationInfo,
                       String[] datas, @PathVariable String roleFlag, @PathVariable String pageType, @PathVariable
                       String tabType) {
        Map<String, Object> param = new HashMap<>();

        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        SysUser sysuser = GlobalContext.getCurrentUser();
        SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
        //省厅角色查询地区的时候用到orgCityId
        List<String> orgFlows = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            SysOrg org4Search = new SysOrg();
            org4Search.setOrgCityId(orgCityId);
            org4Search.setOrgProvId(org.getOrgProvId());
            org4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            org4Search.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<SysOrg> tempOrgs = orgBiz.searchOrg(org4Search);
            if (tempOrgs != null && tempOrgs.size() > 0) {
                for (SysOrg tempOrg : tempOrgs) {
                    orgFlows.add(tempOrg.getOrgFlow());
                }
            }
        } else if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            orgFlows.add(org.getOrgFlow());
            if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
            {
                List<SysOrg> jointOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                if(jointOrgs!=null&&jointOrgs.size()>0)
                {
                    for(SysOrg j:jointOrgs)
                    {
                        orgFlows.add(j.getOrgFlow());
                    }
                }
            }
        }

        model.addAttribute("org",org);

        if ("Audit".equals(pageType)) {
            if ("GlobalAudit".equals(tabType)) {
                resDoctorGraduationInfo.setCurrentAuditStatusId(JszyBaseStatusEnum.LocalPassed.getId());
            } else if ("LocalAudit".equals(tabType)) {
                resDoctorGraduationInfo.setCurrentAuditStatusId(JszyBaseStatusEnum.Auditing.getId());
            } else if ("GlobalAuditEd".equals(tabType)) {
                resDoctorGraduationInfo.setCurrentAuditStatusId(JszyBaseStatusEnum.GlobalPassed.getId());
            }
            param.put("order", "TRAINING_BASE_NAME,DOCTOR_NAME,create_time desc");
        } else if ("Query".equals(pageType)) {
            param.put("certificateNumber", "Y");
            param.put("order", "modify_Time asc");
        }

        param.put("resDoctorGraduationInfo", resDoctorGraduationInfo);
        param.put("orgFlows", orgFlows);
        param.put("orgFlow", orgFlow);
        param.put("docTypeList", docTypeList);

        PageHelper.startPage(currentPage, getPageSize(request));
        List<ResDoctorGraduationInfo> graduationInfos = graduationBiz.searchGraduationInfoByMap(param);
        model.addAttribute("roleFlag", roleFlag);
        model.addAttribute("pageType", pageType);
        model.addAttribute("tabType", tabType);
        model.addAttribute("graduationInfos", graduationInfos);
        model.addAttribute("datas", datas);
        return "jszy/graduationManager/global/query/list";
    }

    @RequestMapping(value = {"/checkDoctorGraduationInfo"})
    @ResponseBody
    public String checkDoctorGraduationInfo(String roleFlag, String auditStatusId, String recordFlow, String recordStatus) {
        ResDoctorGraduationInfo resDoctorGraduationInfo = graduationBiz.findGraduationInfo(recordFlow);
        if (StringUtil.isNotBlank(recordStatus)) {
            resDoctorGraduationInfo.setRecordStatus(recordStatus);
        } else {
            ResAuditLog resAuditLog = new ResAuditLog();
            if (JszyBaseStatusEnum.LocalPassed.getId().equals(auditStatusId)) {
                resDoctorGraduationInfo.setCurrentAuditStatusId(JszyBaseStatusEnum.LocalPassed.getId());
                resDoctorGraduationInfo.setCurrentAuditStatusName("基地已确认");
                resAuditLog.setParentRecordFlow(resDoctorGraduationInfo.getRecordFlow());
                resAuditLog.setAuditStatusId(JszyBaseStatusEnum.LocalPassed.getId());
                resAuditLog.setAuditStatusName("基地已确认");
                resAuditLog.setAuditRole(roleFlag);
                resAuditLog.setProductTypeId("jszyCheckGraduation");
                resAuditLog.setProductTypeName("江苏中医结业信息审核");
            } else if (JszyBaseStatusEnum.GlobalPassed.getId().equals(auditStatusId)) {
                resDoctorGraduationInfo.setCurrentAuditStatusId(JszyBaseStatusEnum.GlobalPassed.getId());
                resDoctorGraduationInfo.setCurrentAuditStatusName("省厅已确认");
                resAuditLog.setParentRecordFlow(resDoctorGraduationInfo.getRecordFlow());
                resAuditLog.setAuditStatusId(JszyBaseStatusEnum.GlobalPassed.getId());
                resAuditLog.setAuditStatusName("省厅已确认");
                resAuditLog.setAuditRole(roleFlag);
                resAuditLog.setProductTypeId("jszyCheckGraduation");
                resAuditLog.setProductTypeName("江苏中医结业信息审核");
            } else if ("Cancel".equals(auditStatusId)) {
                resDoctorGraduationInfo.setCurrentAuditStatusId(JszyBaseStatusEnum.Auditing.getId());
                resDoctorGraduationInfo.setCurrentAuditStatusName("待基地审核");
                resAuditLog.setParentRecordFlow(resDoctorGraduationInfo.getRecordFlow());
                resAuditLog.setAuditStatusId(JszyBaseStatusEnum.Auditing.getId());
                resAuditLog.setAuditStatusName("待基地审核");
                resAuditLog.setAuditRole(roleFlag);
                resAuditLog.setProductTypeId("jszyCheckGraduation");
                resAuditLog.setProductTypeName("江苏中医结业信息审核");
                resAuditLog.setRemarks("基地撤销");
            }
            auditLogBiz.saveAuditLog(resAuditLog);
        }

        int result = graduationBiz.editGraduationInfo(resDoctorGraduationInfo);
        if (result > 0) {
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return GlobalConstant.OPRE_FAIL;
        }
    }

    /**
     * 批量确认
     * @return
     */
    @RequestMapping(value="/modifySelected" )
    public
    @ResponseBody
    String modifySelected(String recordFlowsStr, String roleFlag, String auditStatusId) {
        ResDoctorGraduationInfo graduationInfo4Save = new ResDoctorGraduationInfo();
        SysUser sysUser = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(recordFlowsStr)) {
            String[] recordFlows = recordFlowsStr.split(",");
            List<String> graduationFlows = Arrays.asList(recordFlows);
            graduationInfo4Save.setCurrentAuditStatusId(auditStatusId);
            if(JszyBaseStatusEnum.GlobalPassed.getId().equals(auditStatusId)){
                graduationInfo4Save.setCurrentAuditStatusName("省厅已确认");
            }
            int result = graduationBiz.modifyBatch(graduationFlows,graduationInfo4Save);
            if(result > 0){
                if(graduationFlows != null && graduationFlows.size() > 0){
                    ResAuditLog resAuditLog = new ResAuditLog();
                    for(String tempStr : graduationFlows){
                        resAuditLog = new ResAuditLog();
                        resAuditLog.setParentRecordFlow(tempStr);
                        if(JszyBaseStatusEnum.GlobalPassed.getId().equals(auditStatusId)){
                            resAuditLog.setAuditStatusId(JszyBaseStatusEnum.GlobalPassed.getId());
                            resAuditLog.setAuditStatusName("省厅已确认");
                        }
                        resAuditLog.setAuditRole(roleFlag);
                        resAuditLog.setProductTypeId("jszyCheckGraduation");
                        resAuditLog.setProductTypeName("江苏中医结业信息审核");
                        auditLogBiz.saveAuditLog(resAuditLog);
                    }

                }
                return GlobalConstant.OPERATE_SUCCESSED;
            }else {
                return GlobalConstant.OPERATE_FAIL;
            }
        } else {
            return GlobalConstant.OPERATE_FAIL;
        }
    }

    @RequestMapping(value = "/showCertificate")
    public String showCertificate(String recordFlow,String isGlobal, Model model, String roleId) {
        ResDoctorGraduationInfo resDoctorGraduationInfo = graduationBiz.findGraduationInfo(recordFlow);
        SysUser sysUser = userBiz.readSysUser(resDoctorGraduationInfo.getDoctorFlow());
        model.addAttribute("info", resDoctorGraduationInfo);
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("certificate", "certificate");
        model.addAttribute("isGlobal", "isGlobal");
        model.addAttribute("flag", "view");
        return "jszy/graduationManager/info";
    }

    //base64字符串转化成图片
    public static boolean generateImage(String imgStr,String savePath) {   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) //图像数据为空
        {
            return false;
        }
        int lastIndex=savePath.lastIndexOf("/");
        if(lastIndex<=0)
            lastIndex=savePath.lastIndexOf("\\");
        String newDir = savePath.substring(0,lastIndex);
        File fileDir = new File(newDir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr.toString());
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
    @RequestMapping(value = "/showCertificate3")
    public void showCertificate3( HttpServletRequest request, HttpServletResponse response,
                                    String recordFlow, Model model, String img,MultipartFile myfile, String isDown, String fileName) throws Exception {

        String baseDir=InitConfig.getSysCfg("upload_base_dir");
        //定义上传路径
        String dateString = DateUtil.getCurrDate2();
        String newDir = baseDir+ File.separator + "jszyCertificateImg";
        File fileDir = new File(newDir);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        String originalFilename =recordFlow+ ".png";
        String filePath=fileDir+  File.separator +originalFilename;

        if("Y".equals(isDown))
        {
            byte[] data = null;
            long dataLength = 0;
            /*文件是否存在*/
            File downLoadFile = new File(filePath);
		    /*文件是否存在*/
            if (downLoadFile.exists()) {
                InputStream fis = new BufferedInputStream(new FileInputStream(downLoadFile));
                data = new byte[fis.available()];
                dataLength = downLoadFile.length();
                fis.read(data);
                fis.close();
            }
            fileName = new String(fileName.getBytes("gbk"),"ISO8859-1" );
            try {
                response.reset();
                response.setHeader("Content-Disposition", "attachment; filename=\""+fileName+"\"");
                response.addHeader("Content-Length", "" + dataLength);
                response.setContentType("application/octet-stream;charset=UTF-8");
                OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                if (data != null) {
                    outputStream.write(data);
                }
                outputStream.flush();
                outputStream.close();
            } catch (IOException e) {

            }

        }else{
            File newFile = new File(fileDir, originalFilename);
            myfile.transferTo(newFile);
        }


    }
    @RequestMapping(value = "/showCertificate2")
    public void showCertificate2(String recordFlow, Model model, String roleId,HttpServletRequest request, HttpServletResponse response) throws Exception {
        ResDoctorGraduationInfo info = graduationBiz.findGraduationInfo(recordFlow);
        SysUser sysUser = userBiz.readSysUser(info.getDoctorFlow());
        model.addAttribute("info", info);
        model.addAttribute("sysUser", sysUser);
        model.addAttribute("certificate", "certificate");
        model.addAttribute("flag", "view");
        Map<String,Object> dataMap=new HashMap<>();
        String baseUrl= InitConfig.getSysCfg("upload_base_url");
        String userHeadImg=baseUrl+"/orgCode/up-pic.jpg";
        if(StringUtil.isNotBlank(sysUser.getUserHeadImg()))
        {
            userHeadImg=baseUrl+"/"+sysUser.getUserHeadImg();
        }

        String value = "<img src='"+userHeadImg+"' width='120' height='300'  alt='头像'/>";
        dataMap.put("userHeadImg",value);

        String orgCode=baseUrl+"/orgCode/"+info.getOrgCode()+".png";
         value = "<img src='"+orgCode+"' width='50' height='30'  alt='头像'/>";
        dataMap.put("orgCode",value);

        String seal=baseUrl+"/orgCode/seal.png";
        value = "<img src='"+seal+"' width='80' height='90'  alt='红章'/>";
        dataMap.put("seal",value);
        dataMap.put("doctorName",info.getDoctorName());
        String certificateNumber=StringUtil.completeNoSplit(info.getCertificateNumber());
        dataMap.put("certificateNumber",certificateNumber);
        dataMap.put("trainingStartTime",info.getTrainingStartTime());
        dataMap.put("trainingEndTime",info.getTrainingEndTime());
        dataMap.put("nationalBaseName",info.getNationalBaseName());
        dataMap.put("trainingTypeName",info.getTrainingTypeName());
        String certificateDate=info.getGraduationYear();
        if("2017".equals(certificateDate))
        {
            certificateDate+="年 12月 31日";
        }else if("2018".equals(certificateDate))
        {
            certificateDate+="年 7 月 31 日";
        }
        dataMap.put("certificateDate",certificateDate);
        dataMap.put("certificateFlow","JS"+info.getCertificateFlow());
        String path = "/jsp/jszy/graduationManager/zhengshumoban.docx";//模板
        WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
        ServletContext context =  request.getServletContext();
        String watermark = GeneralMethod.getWatermark(null);
        temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
        String  name = "结业证书.docx";
        if(temeplete!=null){
            response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
            response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            ServletOutputStream out = response.getOutputStream ();
            (new SaveToZipFile(temeplete)).save (out);
            out.flush ();
        }
//        final String fileName = "结业证书";
//        String outputFileClass = ResourceLoader.getPath("/");
//        String outputFile = new File(outputFileClass) + "/load/" + fileName + ".pdf";
//        File file = new File(outputFile);
//        // 模板数据
//        DocumentVo vo = new DocumentVo() {
//            @Override
//            public String findPrimaryKey() {
//                return fileName;
//            }
//
//            @Override
//            public Map<String, Object> fillDataMap() {
//                return dataMap;
//            }
//        };
//        String template = "jszy/zhengshumoban.html";
//        PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
//        // 生成pdf
//        pdfGenerator.generate(template, vo, outputFile);
//
//        pubFileBiz.downFile(file, response);
    }

    /**
     * 导出excel
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/exportGraduationInfo/{roleFlag}/{pageType}/{tabType}")
    public void exportGraduationInfo(HttpServletResponse response, String orgCityId,String orgFlow, ResDoctorGraduationInfo resDoctorGraduationInfo,
                                     String[] datas, @PathVariable String roleFlag, @PathVariable String pageType, @PathVariable
                                     String tabType) throws Exception {
        Map<String, Object> param = new HashMap<>();

        List<String> docTypeList = new ArrayList<String>();
        if (datas != null && datas.length > 0) {
            for (String s : datas) {
                docTypeList.add(s);
            }
        }
        SysUser sysuser = GlobalContext.getCurrentUser();
        SysOrg org = orgBiz.readSysOrg(sysuser.getOrgFlow());
        //省厅角色查询地区的时候用到orgCityId
        List<String> orgFlows = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
            SysOrg org4Search = new SysOrg();
            org4Search.setOrgCityId(orgCityId);
            org4Search.setOrgProvId(org.getOrgProvId());
            org4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            org4Search.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<SysOrg> tempOrgs = orgBiz.searchOrg(org4Search);
            if (tempOrgs != null && tempOrgs.size() > 0) {
                for (SysOrg tempOrg : tempOrgs) {
                    orgFlows.add(tempOrg.getOrgFlow());
                }
            }
        } else if (GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
            orgFlows.add(org.getOrgFlow());
            if(OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
            {
                List<SysOrg> jointOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                if(jointOrgs!=null&&jointOrgs.size()>0)
                {
                    for(SysOrg j:jointOrgs)
                    {
                        orgFlows.add(j.getOrgFlow());
                    }
                }
            }
        }


        if ("Audit".equals(pageType)) {
            if ("GlobalAudit".equals(tabType)) {
                resDoctorGraduationInfo.setCurrentAuditStatusId(JszyBaseStatusEnum.LocalPassed.getId());
            } else if ("LocalAudit".equals(tabType)) {
                resDoctorGraduationInfo.setCurrentAuditStatusId(JszyBaseStatusEnum.Auditing.getId());
            } else if ("GlobalPassed".equals(tabType)) {
                resDoctorGraduationInfo.setCurrentAuditStatusId(JszyBaseStatusEnum.GlobalPassed.getId());
            }
            param.put("order", "create_time desc");
        } else if ("Query".equals(pageType)) {
            param.put("certificateNumber", "Y");
            param.put("order", "modify_Time asc");
        }

        param.put("resDoctorGraduationInfo", resDoctorGraduationInfo);
        param.put("orgFlows", orgFlows);
        param.put("orgFlow", orgFlow);
        param.put("docTypeList", docTypeList);

        List<ResDoctorGraduationInfo> graduationInfos = graduationBiz.searchGraduationInfoByMap(param);
        String[] head = new String[]{};
        String[] titles = null;
        if ("Query".equals(pageType)) {
            titles = new String[]{
                    "graduationYear:结业年份",
                    "doctorName:姓名",
                    "idNo:证件号码",
                    "trainingBaseName:培训基地",
                    "company:所在单位",
                    "trainingTypeName:培训专业",
                    "trainingStartTime:实际培训开始时间",
                    "trainingEndTime:实际培训结束时间",
                    "passTheoryAssessmentYear:理论考试通过年度",
                    "ifPassAnnualAssessment:年度考核是否通过",
                    "ifCompleteTrainingTime:培训时间是否完成",
                    "ifCompleteRegisterManual:登记手册是否完整",
                    "ifPassDiscipleAssessment:跟师考核是否合格",
                    "certificateNumber:证书编号",
            };
        } else {
            titles = new String[]{
                    "graduationYear:结业年份",
                    "doctorName:姓名",
                    "idNo:证件号码",
                    "trainingBaseName:培训基地",
                    "company:所在单位",
                    "trainingTypeName:培训专业",
                    "trainingStartTime:实际培训开始时间",
                    "trainingEndTime:实际培训结束时间",
                    "passTheoryAssessmentYear:理论考试通过年度",
                    "ifPassAnnualAssessment:年度考核是否通过",
                    "ifCompleteTrainingTime:培训时间是否完成",
                    "ifCompleteRegisterManual:登记手册是否完整",
                    "ifPassDiscipleAssessment:跟师考核是否合格",
            };
        }

        String fileName = "学员证书信息.xls";
        fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        //ExcleUtile.exportSimpleExcleWithHeadlin(head, titles, doctorList, response.getOutputStream());
        OutputStream os = response.getOutputStream();
        Workbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        Sheet sheet = wb.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        //HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        style.setFont(font);
        Font fontTwo = wb.createFont();
        fontTwo.setFontHeightInPoints((short) 12);

        CellStyle styleTwo = wb.createCellStyle();
        styleTwo.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式
        styleTwo.setFont(fontTwo);
        //

        Map<Integer, Integer> columnWidth = new HashMap<>();
        List<String> paramIds = new ArrayList<String>();
        Row row = sheet.createRow(0);
        Cell cell = null;
        for (int i = 0; i < titles.length; i++) {
            String[] title = titles[i].split(":");
            cell = row.createCell(i);
            cell.setCellValue(title[1]);
            cell.setCellStyle(style);
            paramIds.add(title[0]);
            //宽度自适应
            int nl = title[1].toString().getBytes().length;
            if (columnWidth.containsKey(i)) {
                Integer ol = columnWidth.get(i);
                if (ol < nl) {
                    columnWidth.put(i, nl);
                }
            } else {
                columnWidth.put(i, nl);
            }
        }
        if (graduationInfos != null) {
            Cell rowCell = null;
            for (int i = 0; i < graduationInfos.size(); i++) {
                Object item = graduationInfos.get(i);
                row = sheet.createRow(1 + i);
                Object result = null;
                for (int j = 0; j < paramIds.size(); j++) {
                    String paramId = paramIds.get(j);
                    if (StringUtil.isBlank(paramId)) {//序号
                        result = i + 1;
                    } else {
                        result = getValueByAttrs(paramId, item);
                    }
                    rowCell = row.createCell(j);
                    rowCell.setCellStyle(styleTwo);
                    rowCell.setCellValue(result.toString());
                    //宽度自适应
                    int nl = result.toString().getBytes().length;
                    if (columnWidth.containsKey(j)) {
                        Integer ol = columnWidth.get(j);
                        if (ol < nl) {
                            columnWidth.put(j, nl);
                        }
                    } else {
                        columnWidth.put(j, nl);
                    }
                }

            }
        }
        Set<Integer> keys = columnWidth.keySet();
        for (Integer key : keys) {
            int width = columnWidth.get(key);
            sheet.setColumnWidth(key, width * 2 * 256);
        }
        wb.write(os);
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    private static Object getValueByAttrs(String attrNames, Object obj) throws Exception {
        Object value = "";
        if (StringUtil.isNotBlank(attrNames)) {
            String proptyName = "";
            int pIndex = attrNames.indexOf(".");

            if (pIndex >= 0) {
                proptyName = attrNames.substring(0, pIndex);
            } else {
                proptyName = attrNames;
            }

            if (StringUtil.isNotBlank(proptyName) && obj != null) {
                Class clazz = obj.getClass();
                String firstStr = proptyName.substring(0, 1).toUpperCase();
                String secondStr = proptyName.substring(1);
                Method mt;
                Object result;
                if (proptyName.length() >= 4 && "get(".equals(proptyName.substring(0, 4))) {
                    int index = Integer.parseInt(proptyName.split("\\(")[1].split("\\)")[0]);
                    result = null;
                    if (((List) obj).size() > index) {
                        result = ((List) obj).get(index);
                    }
                } else if (obj instanceof Map) {
                    Map map = (Map) obj;
                    result = ((Map) obj).get(proptyName);
                } else {
                    mt = clazz.getMethod("get" + firstStr + secondStr);
                    result = mt.invoke(obj);
                }

                if (result != null) {
                    String stringClassName = String.class.getSimpleName();
                    String inegerClassName = Integer.class.getSimpleName();
                    String bigDecimalClassName = BigDecimal.class.getSimpleName();
                    String valueClassName = result.getClass().getSimpleName();
                    if (stringClassName.equals(valueClassName) || bigDecimalClassName.equals(valueClassName) || inegerClassName.equals(valueClassName)) {
                        value = result;
                    } else {
                        String surplusName = attrNames.substring(pIndex + 1);
                        value = getValueByAttrs(surplusName, result);
                    }
                }
            }
        }
        return value;
    }

    @RequestMapping(value = "/importGraduationPeople")
    @ResponseBody
    public String importGraduationPeople(MultipartFile file) {

        if (file.getSize() > 0) {
            try {
                ExcelUtile result = (ExcelUtile) jszyResDoctorRecruitBiz.importGraduationPeople(file);
                if (null != result) {
                    String code = (String) result.get("code");
                    int count = (Integer) result.get("count");
                    String msg = (String) result.get("msg");
                    if ("1".equals(code)) {
                        return GlobalConstant.UPLOAD_FAIL + msg;
                    } else {
                        if (GlobalConstant.ZERO_LINE != count) {
                            return GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
                        } else {
                            return GlobalConstant.UPLOAD_FAIL;
                        }
                    }
                } else {
                    return GlobalConstant.UPLOAD_FAIL;
                }
            } catch (RuntimeException re) {
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    @RequestMapping(value = "/editInfo")
    public String editInfo(String recordFlow, Model model) {
        ResDoctorGraduationInfo info = graduationBiz.findGraduationInfo(recordFlow);
        model.addAttribute("info", info);
        SysOrg sysorg = new SysOrg();
        sysorg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        sysorg.setOrgTypeId(OrgTypeEnum.Hospital.getId());

        List<SysOrg> orgs = orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        sysorg.setOrgLevelId(OrgLevelEnum.CountryOrg.getId());
        orgs = orgBiz.searchOrg(sysorg);
        model.addAttribute("countryOrgs", orgs);

        return "jszy/graduationManager/global/editInfo";
    }

    @RequestMapping(value = "/saveInfo")
    @ResponseBody
    public String saveInfo(ResDoctorGraduationInfo data) {
        SysUser u = null;
        ResDoctor resdoctor = null;
        if (StringUtil.isBlank(data.getRecordFlow())) {
            return "请选择需要保存的人员信息！";
        }
        if (StringUtil.isBlank(data.getGraduationYear())) {
            return "结业年份为空，请确认后保存！！";
        } else {
            if (!data.getGraduationYear().matches("^[1-9][0-9]{3}$")) {
                return "结业年份格式有误，请确认后保存！！";
            }
        }
        if (StringUtil.isBlank(data.getDoctorName())) {
            return "姓名为空，请确认后保存！！";
        }
        if (StringUtil.isBlank(data.getIdNo())) {
            return "身份证号为空，请确认后保存！！";
        }
        if (StringUtil.isBlank(data.getDoctorFlow())) {
            return "人员标志符为空，请确认后保存！！";
        }
        resdoctor = doctorBiz.findByFlow(data.getDoctorFlow());
        if (resdoctor == null) {
            return "证件号码为【" + data.getIdNo() + "】学员医师信息不存在，请确认后保存！！";
        }
        if (StringUtil.isBlank(resdoctor.getSessionNumber())) {
            return "证件号码为【" + data.getIdNo() + "】学员医师年级信息不存在，请确认后保存！！";
        }
        data.setSessionNumber(resdoctor.getSessionNumber());
        data.setTrainingSpeId(resdoctor.getTrainingSpeId());
        data.setTrainingSpeName(resdoctor.getTrainingSpeName());
        data.setDoctorTypeId(resdoctor.getDoctorTypeId());
        data.setDoctorTypeName(resdoctor.getDoctorTypeName());

        if (StringUtil.isBlank(data.getNationalBaseFlow())) {
            return "国家基地为空，请确认后保存！！";
        } else {
            SysOrg org = orgBiz.readSysOrg(data.getNationalBaseFlow());
            if (org == null) {
                return "国家基地在系统中不存在，请确认后保存！！";
            }
            if (StringUtil.isBlank(org.getOrgCode())) {
                return "国家基地编码为空，请联系系统管理员！！";
            }
            data.setNationalBaseName(org.getOrgName());
            data.setOrgCode(org.getOrgCode());
        }
        if (StringUtil.isBlank(data.getTrainingBaseFlow())) {
            return "培训基地为空，请确认后保存！！";
        } else {
            SysOrg org = orgBiz.readSysOrg(data.getTrainingBaseFlow());
            if (org == null) {
                return "培训基地在系统中不存在，请确认后保存！！";
            }
            data.setTrainingBaseName(org.getOrgName());
        }
        if (StringUtil.isBlank(data.getCompany())) {
            return "所在单位为空，请确认后保存！！";
        }
        if (StringUtil.isBlank(data.getTrainingTypeId())) {
            return "培训专业为空，请确认后保存！！";
        } else {
            if (!RecDocCategoryEnum.ChineseMedicine.getId().equals(data.getTrainingTypeId()) && !RecDocCategoryEnum.TCMGeneral.getId().equals(data.getTrainingTypeId())) {
                return "培训专业只能是【中医】或【中医全科】，请确认后保存！！";
            }
            if (RecDocCategoryEnum.ChineseMedicine.getId().equals(data.getTrainingTypeId())) {
                data.setTrainingTypeName(RecDocCategoryEnum.ChineseMedicine.getName());
            }
            if (RecDocCategoryEnum.TCMGeneral.getId().equals(data.getTrainingTypeId())) {
                data.setTrainingTypeName(RecDocCategoryEnum.TCMGeneral.getName());
            }
        }
        if (StringUtil.isBlank(data.getTrainingStartTime())) {
            return "实际培训开始时间为空，请确认后保存！！";
        } else {
            try {
                SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy年MM月");
                dateFormat2.parse(data.getTrainingStartTime());
            } catch (Exception e) {
                return "实际培训开始时间格式有误，请确认后保存！！";
            }
        }
        if (StringUtil.isBlank(data.getTrainingEndTime())) {
            return "实际培训结束时间为空，请确认后保存！！";
        } else {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月");
                dateFormat.parse(data.getTrainingEndTime());
            } catch (Exception e) {
                return "实际培训结束时间格式有误，请确认后保存！！";
            }
        }
        if (data.getTrainingEndTime().compareTo(data.getTrainingStartTime()) < 0) {
            return "实际培训开始时间大于结束时间，请确认后保存！！";
        }
        if (StringUtil.isBlank(data.getPassTheoryAssessmentYear())) {
            return "理论考核通过年份为空，请确认后保存！！";
        } else {
            if (!data.getPassTheoryAssessmentYear().matches("^[1-9][0-9]{3}$")) {
                return "理论考核通过年份格式有误，请确认后保存！！";
            }
        }
        if (StringUtil.isBlank(data.getIfPassAnnualAssessment())) {
            return "年度考核是否通过为空，请确认后保存！！";
        } else {
            if (!"是".equals(data.getIfPassAnnualAssessment()) && !"否".equals(data.getIfPassAnnualAssessment())) {
                return "年度考核是否通过只能是【是】或【否】，请确认后保存！！";
            }
        }
        if (StringUtil.isBlank(data.getIfCompleteTrainingTime())) {
            return "培训时间是否完成为空，请确认后保存！！";
        } else {
            if (!"是".equals(data.getIfCompleteTrainingTime()) && !"否".equals(data.getIfCompleteTrainingTime())) {
                return "培训时间是否完成只能是【是】或【否】，请确认后保存！！";
            }
        }
        if (StringUtil.isBlank(data.getIfCompleteRegisterManual())) {
            return "登记手册是否完成为空，请确认后保存！！";
        } else {
            if (!"是".equals(data.getIfCompleteRegisterManual()) && !"否".equals(data.getIfCompleteRegisterManual())) {
                return "登记手册是否完成只能是【是】或【否】，请确认后保存！！";
            }
        }
        if (StringUtil.isBlank(data.getIfPassDiscipleAssessment())) {
            return "跟师考核是否完成为空，请确认后保存！！";
        } else {
            if (!"是".equals(data.getIfPassDiscipleAssessment()) && !"否".equals(data.getIfPassDiscipleAssessment())) {
                return "跟师考核是否完成只能是【是】或【否】，请确认后保存！！";
            }
        }
        ResDoctorRecruit search = new ResDoctorRecruit();
        search.setAuditStatusId(AuditStatusEnum.Passed.getId());
        search.setDoctorFlow(resdoctor.getDoctorFlow());
        search.setSessionNumber(resdoctor.getSessionNumber());
        List<ResDoctorRecruitWithBLOBs> recruits = jszyResDoctorRecruitBiz.readDoctorRecruitBlobs(search);
        if (null == recruits || recruits.size() <= 0) {
            return "学员未添加过培训记录，请确认后保存！！";
        }
        data.setRecruitFlow(recruits.get(0).getRecruitFlow());
        int conut = graduationBiz.editGraduationInfo(data);
        if (conut == 0) {

            return "保存失败";
        }
        return "保存成功";
    }

    @RequestMapping(value = "/delInfo")
    @ResponseBody
    public String delInfo(ResDoctorGraduationInfo data) {
        if (StringUtil.isBlank(data.getRecordFlow())) {
            return "请选择需要删除的人员信息！";
        }
        ResDoctorGraduationInfo old = graduationBiz.findGraduationInfo(data.getRecordFlow());
        if (old == null) {
            return "结业人员信息不存在，请刷新列表！";
        }
        if (StringUtil.isNotBlank(old.getCertificateNumber())) {
            return "该人员已发证，不得删除，请刷新列表！";
        }
        data.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int conut = graduationBiz.editGraduationInfo(data);
        if (conut == 0) {

            return "删除失败";
        }
        return "删除成功";
    }

    @RequestMapping(value = "/createCertificate")
    @ResponseBody
    public String createCertificate(ResDoctorGraduationInfo data) {
        if (StringUtil.isBlank(data.getRecordFlow())) {
            return "请选择需要删除的人员信息！";
        }
        ResDoctorGraduationInfo old = graduationBiz.findGraduationInfo(data.getRecordFlow());
        if (old == null || GlobalConstant.RECORD_STATUS_N.equals(old.getRecordStatus())) {
            return "结业人员信息不存在，请刷新列表！";
        }
        if (StringUtil.isNotBlank(old.getCertificateNumber())) {
            return "该人员已发证，不得重复生成，请刷新列表！";
        }
        int conut = graduationBiz.createCertificate(old);
        if (conut == 0) {

            return "生成失败";
        }
        return "生成成功";
    }

    private String getShowCountryOrProvince(ResDoctorRecruit recruit) {
        String completeNo = "";
        String sessionNumber = recruit.getSessionNumber();
        if (StringUtil.isBlank(sessionNumber)) {
            return "";
        }
        //所有助理全科人员都只生成助理全科证书
        if (recruit.getCatSpeId().equals(JszyTrainCategoryEnum.TCMAssiGeneral.getId())) {
            completeNo = "AssiGeneral";
        } else {
            int sessionYear = Integer.valueOf(recruit.getSessionNumber());
            //2013年以前全部用江苏省证书
            if (sessionYear <= 2013) {
                //江苏省生成规则待定
                completeNo = getProvinceOrgNo(recruit);
            } else if (sessionYear == 2014) {
                SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
                //只有国家基地使用国家证书
                if (org.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
                    completeNo = "country";
                } else {
                    //江苏省生成规则待定
                    completeNo = getProvinceOrgNo(recruit);
                }
            } else {
                SysOrg org = orgBiz.readSysOrg(recruit.getOrgFlow());
                //国家基地使用国家证书
                if (OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId())) {
                    completeNo = "country";
                } else {
                    List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByJointOrgFlow(org.getOrgFlow());
                    if (jointOrgList != null && jointOrgList.size() > 0) {
                        ResJointOrg resJointOrg = jointOrgList.get(0);
                        SysOrg org2 = orgBiz.readSysOrg(resJointOrg.getOrgFlow());
                        //国家基地的协同基地也使用国家证书
                        if (org2.getOrgLevelId().equals(OrgLevelEnum.CountryOrg.getId())) {
                            completeNo = "country";
                        } else {
                            //江苏省生成规则待定
                            completeNo = getProvinceOrgNo(recruit);
                        }
                    } else {
                        //江苏省生成规则待定
                        completeNo = getProvinceOrgNo(recruit);
                    }
                }
            }
        }
        return completeNo;
    }

    public String getProvinceOrgNo(ResDoctorRecruit resDoctor) {
        String no = "";
        if (resDoctor.getSpeId().equals("51") || resDoctor.getSpeId().equals("52") || resDoctor.getSpeId().equals("0700")) {
            no = "provinceAll";
        } else {
            //非全科
            no = "provinceNoAll";
        }
        return no;
    }
}
