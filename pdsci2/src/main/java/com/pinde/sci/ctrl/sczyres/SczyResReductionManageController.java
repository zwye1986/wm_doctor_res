package com.pinde.sci.ctrl.sczyres;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sczyres.DoctorRecruitBiz;
import com.pinde.sci.biz.sczyres.DoctorReductionBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.FileUtil;
import com.pinde.sci.enums.sczyres.SczyBaseStatusEnum;
import com.pinde.sci.enums.sczyres.SczyResOrgLevelEnum;
import com.pinde.sci.enums.sczyres.SpeCatEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/sczy/reduction")
public class SczyResReductionManageController extends GeneralController {

    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private DoctorRecruitBiz resDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private DoctorReductionBiz reductionBiz;
    @Autowired
    private IResJointOrgBiz resJointBiz;
    @Autowired
    private ICfgBiz cfgBiz;
    /**
     * 学员减免申请主页面
     */
    @RequestMapping(value = "/reductionApplyMain")
    public String main(Model model) {
        //获取培训记录
        SysUser currUser = GlobalContext.getCurrentUser();
        String doctorFlow = currUser.getUserFlow();
        ResDoctorRecruit recruit = new ResDoctorRecruit();
        recruit.setDoctorFlow(doctorFlow);
        recruit.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        recruit.setConfirmFlag(GlobalConstant.RECORD_STATUS_Y);
        List<ResDoctorRecruit> recruitList = resDoctorRecruitBiz.findDoctorRecruit(recruit, "CREATE_TIME",null);
        if (recruitList != null && !recruitList.isEmpty()) {
            model.addAttribute("recruitList", recruitList);
        }
        return "sczyres/reduction/recruitListTab";
    }

    /**
     * 查看减免信息
     *
     * @return
     */
    @RequestMapping("/getReductionInfo")
    public String getDoctorRecruit(String recruitFlow, String doctorFlow, Model model) {
        if (StringUtil.isNotBlank(recruitFlow)) {
            ResDoctorRecruit doctorRecruit = resDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
            if (doctorRecruit != null) {
                model.addAttribute("doctorRecruit", doctorRecruit);
                if (StringUtil.isNotBlank(doctorRecruit.getDoctorFlow())) {
                    ResDoctor doctor = resDoctorBiz.readDoctor(doctorRecruit.getDoctorFlow());
                    if (doctor != null) {
                        model.addAttribute("doctor", doctor);
                    }
                }
                String catSpeId = doctorRecruit.getCatSpeId();
                String defaultYear = "";
                if(SpeCatEnum.TCMAssiGeneral.getId().equals(catSpeId)){
                    defaultYear = "2";
                }
                if(SpeCatEnum.TCMGeneral.getId().equals(catSpeId)){
                    defaultYear = "3";
                }
                if(SpeCatEnum.ChineseMedicine.getId().equals(catSpeId)){
                    defaultYear = "3";
                }
                model.addAttribute("defaultYear",defaultYear);
            }
            ResDoctorReduction reduction = reductionBiz.findReductionByRecruitFlow(recruitFlow);
            if (reduction != null) {
                List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", reduction.getRecordFlow());
                model.addAttribute("reductionFiles", reductionFiles);
            }
            //如果审核状态是空则可以编辑或者审核不通过
            if (reduction == null || StringUtil.isBlank(reduction.getAuditStatusId())
                    || SczyBaseStatusEnum.LocalUnPassed.getId().equals(reduction.getAuditStatusId())
                    || SczyBaseStatusEnum.XtLocalUnPassed.getId().equals(reduction.getAuditStatusId())
                    || SczyBaseStatusEnum.GlobalUnPassed.getId().equals(reduction.getAuditStatusId())
                    ) {
                model.addAttribute("canEdit", "Y");
            } else {
                model.addAttribute("canEdit", "N");
            }
            //1.助理全科2.已经减免的不可以申请减免3.只有审核通过的记录才可以减免
            //1.招录信息审核通过展示减免
            if ("Y".equals(doctorRecruit.getConfirmFlag())&&
                    (!SpeCatEnum.TCMAssiGeneral.getId().equals(doctorRecruit.getCatSpeId()))) {
                model.addAttribute("showReduction", "Y");
            } else {
                model.addAttribute("showReduction", "N");
            }
            model.addAttribute("reduction", reduction);
        }
        return "sczyres/reduction/reductionInfo";
    }

    /**
     * 跳转至上传附加
     *
     * @return
     */
    @RequestMapping("/showUploadReductionFile")
    public String showUploadReductionFile(ResDoctorReduction resDoctorReduction, Model model) {
        if (StringUtil.isNotBlank(resDoctorReduction.getRecordFlow())) {
            List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", resDoctorReduction.getRecordFlow());
            model.addAttribute("reductionFiles", reductionFiles);
        }
        model.addAttribute("reduction", resDoctorReduction);
        return "sczyres/reduction/uploadFile";
    }

    /**
     * 学员角色上传附件并保存减免信息ResDoctorReduction
     *
     * @param jsonData
     * @param request
     * @param resDoctorReduction
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/saveReductionAndFile", method = {RequestMethod.POST})
    @ResponseBody
    public String saveReductionAndFile(String jsonData, HttpServletRequest request, ResDoctorReduction resDoctorReduction) throws UnsupportedEncodingException {
        reductionBiz.edit(resDoctorReduction);
//        学员端减免多附件上传
        //校验上传文件大小及格式
        String checkResult = checkFiles(request);
        if (!"1".equals(checkResult)) {
            return checkResult;
        }
        int deleteFileResult = 0;
        int saveFileResult = 0;
        if (StringUtil.isNotBlank(jsonData)) {
            jsonData = java.net.URLDecoder.decode(jsonData, "UTF-8");
            Map<String, Object> mp = JSON.parseObject(jsonData, Map.class);
            //上传文件的流水号
            List<String> fileFlows = (List<String>) mp.get("fileFlows");
            //处理不在本次保存中的文件
            deleteFileResult = upadteFileInfo(resDoctorReduction.getRecordFlow(), fileFlows);
        }
        //处理上传文件
        saveFileResult = addUploadFile(resDoctorReduction.getRecordFlow(), request, "Reduction");
        if (deleteFileResult != 0 || saveFileResult != 0) {
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return GlobalConstant.OPRE_FAIL;
        }
    }

    /**
     * 学员角色提交减免信息ResDoctorReduction
     *
     * @param recordFlow
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/reductionApply", method = {RequestMethod.POST})
    @ResponseBody
    public String reductionApply(ResDoctorReduction resDoctorReduction) {
//        ResDoctorReduction resDoctorReduction = reductionBiz.findReductionByPK(recordFlow);
        int reductionResult = 0;
        if (resDoctorReduction != null) {
            //是否需要协同基地审核标识
            SysUser currentUser = GlobalContext.getCurrentUser();
            SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
            String currentOrgLevel = currentOrg.getOrgLevelId();
            if(SczyResOrgLevelEnum.Main.getId().equals(currentOrgLevel)){
                resDoctorReduction.setRequireXtAduit("N");
            }else if(SczyResOrgLevelEnum.Joint.getId().equals(currentOrgLevel)){
                resDoctorReduction.setRequireXtAduit("Y");
            }else {
                resDoctorReduction.setRequireXtAduit("N");
            }

            resDoctorReduction.setAuditStatusId(SczyBaseStatusEnum.Auditing.getId());
            resDoctorReduction.setAuditStatusName(SczyBaseStatusEnum.Auditing.getName());
            reductionResult = reductionBiz.edit(resDoctorReduction);
        }
        if (reductionResult != 0) {
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return GlobalConstant.OPRE_FAIL;
        }
    }

    @RequestMapping(value = {"/reductionTab"})
    public String delayTab(String roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "sczyres/reduction/tab";
    }

    @RequestMapping(value = {"/reductionManage"})
    public String delayManage(Integer currentPage, ResDoctor doctor, HttpServletRequest request, SysUser user4Search, ResDoctorReduction reduction4Search,
                              Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType, String roleId) {
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        String currentOrgLevel = currentOrg.getOrgLevelId();
        //查询时用于存放审核状态
        List<String> reductionStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();

        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //所有医院
            orgs = orgBiz.searchOrg(sysorg4Search);
            model.addAttribute("orgs", orgs);
            if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
                for (SysOrg tempOrg : orgs) {
                    orgFlowList.add(tempOrg.getOrgFlow());
                }
            }
            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(SczyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(SczyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部
                    reductionStatusIdList.add(SczyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(SczyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(SczyBaseStatusEnum.LocalPassed.getId());
            }
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            if(SczyResOrgLevelEnum.Main.getId().equals(currentOrgLevel)){
                List<ResJointOrg> jointOrgs = resJointBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
                if(jointOrgs!=null&&jointOrgs.size()>0){
                    for(ResJointOrg jointOrg:jointOrgs){
                        String jointOrgFlow = jointOrg.getJointOrgFlow();
                        orgFlowList.add(jointOrgFlow);
                    }
                }
            }
            orgFlowList.add(currentUser.getOrgFlow());
            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(SczyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(SczyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部转出
                    reductionStatusIdList.add(SczyBaseStatusEnum.LocalPassed.getId());
                    reductionStatusIdList.add(SczyBaseStatusEnum.LocalUnPassed.getId());
                    reductionStatusIdList.add(SczyBaseStatusEnum.XtLocalPassed.getId());
                    reductionStatusIdList.add(SczyBaseStatusEnum.XtLocalUnPassed.getId());
                    reductionStatusIdList.add(SczyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(SczyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                if(SczyResOrgLevelEnum.Main.getId().equals(currentOrgLevel)){
                    paramMap.put("mainCheckFlag", "Y");
                }
                if(SczyResOrgLevelEnum.Joint.getId().equals(currentOrgLevel)){
                    reductionStatusIdList.add(SczyBaseStatusEnum.Auditing.getId());
                }
            }
        }
        paramMap.put("reduction", reduction4Search);
        paramMap.put("reductionStatusIdList", reductionStatusIdList);
        paramMap.put("doctor", doctor);
        paramMap.put("sysUser", user4Search);
        paramMap.put("orgFlowList", orgFlowList);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> resDoctorReductions = reductionBiz.findReductionExtByMap(paramMap);
        model.addAttribute("resDoctorReductions", resDoctorReductions);
        model.addAttribute("operType", operType);
        model.addAttribute("roleId", roleId);
        Map<String, List<PubFile>> reductionFileMaps = null;
        if (resDoctorReductions != null && resDoctorReductions.size() > 0) {
            reductionFileMaps = new HashMap<>();
            for (Map<String, String> tempReduction : resDoctorReductions) {
                String reductionRecordFlow = tempReduction.get("recordFlow");
                List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", reductionRecordFlow);
                reductionFileMaps.put(reductionRecordFlow, reductionFiles);
            }
            model.addAttribute("reductionFileMaps", reductionFileMaps);
        }
        //页面跳转控制
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            return "sczyres/reduction/reductionInfo4global";
        } else if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            return "sczyres/reduction/reductionInfo4Local";
        }
        return "";
    }


    @RequestMapping(value = {"/showCheckReductionInfo"})
    public String showCheckReductionInfo(String recordFlow, String roleId, Model model) {
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        String currentOrgLevel = currentOrg.getOrgLevelId();

        ResDoctorReduction reduction = reductionBiz.findReductionByPK(recordFlow);
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            if(SczyResOrgLevelEnum.Joint.getId().equals(currentOrgLevel)){
                model.addAttribute("agree", SczyBaseStatusEnum.XtLocalPassed.getId());
                model.addAttribute("disAgree", SczyBaseStatusEnum.XtLocalUnPassed.getId());
            }else{
                model.addAttribute("agree", SczyBaseStatusEnum.LocalPassed.getId());
                model.addAttribute("disAgree", SczyBaseStatusEnum.LocalUnPassed.getId());
            }
        } else {
            model.addAttribute("agree", SczyBaseStatusEnum.GlobalPassed.getId());
            model.addAttribute("disAgree", SczyBaseStatusEnum.GlobalUnPassed.getId());
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("reduction", reduction);
        return "sczyres/reduction/checkReduction";
    }

    @RequestMapping(value = {"/checkReductionInfo"})
    @ResponseBody
    public String checkReductionInfo(String reductionAuditStatusId, String recordFlow, String auditOpinion, String roleId) {
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        String currentOrgLevel = currentOrg.getOrgLevelId();

        ResDoctorReduction reduction = reductionBiz.findReductionByPK(recordFlow);
        reduction.setAuditStatusId(reductionAuditStatusId);
        reduction.setAuditStatusName(SczyBaseStatusEnum.getNameById(reductionAuditStatusId));
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            if(SczyResOrgLevelEnum.Joint.getId().equals(currentOrgLevel)){
                reduction.setXtLocalAuditTime(DateUtil.getCurrDateTime());
                reduction.setXtLocalAuditUserFlow(currentUser.getUserFlow());
                reduction.setXtLocalAuditOpinion(auditOpinion);
            }else{
                reduction.setLocalAuditTime(DateUtil.getCurrDateTime());
                reduction.setLocalAuditUserFlow(currentUser.getUserFlow());
                reduction.setLocalAuditOpinion(auditOpinion);
            }
            SysCfg cfg = cfgBiz.read("scres_allow_reduction");
            if(cfg!=null){
                String allow = cfg.getCfgValue();
                if("Y".equals(allow)&&SczyBaseStatusEnum.LocalPassed.getId().equals(reductionAuditStatusId)){
                    reductionAuditStatusId=SczyBaseStatusEnum.GlobalPassed.getId();
                    reduction.setAuditStatusId(SczyBaseStatusEnum.GlobalPassed.getId());
                    reduction.setAuditStatusName(SczyBaseStatusEnum.GlobalPassed.getName());
                }
            }
        }
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            reduction.setGlobalAuditTime(DateUtil.getCurrDateTime());
            reduction.setGlobalAuditUserFlow(currentUser.getUserFlow());
            reduction.setGlobalAuditOpinion(auditOpinion);
        }
        int result = reductionBiz.edit(reduction);
        if (SczyBaseStatusEnum.GlobalPassed.getId().equals(reductionAuditStatusId)) {
            reductionBiz.updateRecruitAndDoctorInfo(reduction);
        }
        if (result > 0) {
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return GlobalConstant.OPRE_FAIL;
        }
    }

    //保存上传的附件
    private int addUploadFile(String recordFlow, HttpServletRequest request, String productType) {
        int result = 0;
        //以下为多文件上传********************************************
        //创建一个通用的多部分解析器
        List<PubFile> pubFiles = new ArrayList<>();
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                if (files != null && files.size() > 0) {
                    for (MultipartFile file : files) {
                        //保存附件
                        PubFile pubFile = new PubFile();
                        //取得当前上传文件的文件名称
                        String oldFileName = file.getOriginalFilename();
                        //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (StringUtil.isNotBlank(oldFileName)) {
                            //定义上传路径
                            String dateString = DateUtil.getCurrDate2();
                            String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + productType + File.separator + dateString + File.separator + recordFlow;
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
                            String filePath = File.separator + productType + File.separator + dateString + File.separator + recordFlow + File.separator + originalFilename;
                            pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                            pubFile.setFilePath(filePath);
                            pubFile.setFileName(oldFileName);
                            pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
                            pubFile.setProductType(productType);
                            pubFile.setProductFlow(recordFlow);
                            pubFiles.add(pubFile);
                        }
                        result++;
                    }
                }
                //记录上传该文件后的时间
                //int finaltime = (int) System.currentTimeMillis();
            }
        }
        if (pubFiles.size() > 0) {
            pubFileBiz.saveFiles(pubFiles);
        }
        return result;
    }

    //处理文件
    private int upadteFileInfo(String recordFlow, List<String> fileFlows) {
        int result = 0;
        //查询出不在本次保存中的文件信息
        List<PubFile> files = pubFileBiz.searchByProductFlowAndNotInFileFlows(recordFlow, fileFlows);
        //删除服务器中相应的文件
        if (files != null && files.size() > 0) {
            String basePath = InitConfig.getSysCfg("upload_base_dir");
            for (PubFile pubFile : files) {
                if (StringUtil.isNotBlank(pubFile.getFilePath())) {
                    String filePath = basePath + pubFile.getFilePath();
                    FileUtil.deletefile(filePath);
                }
                pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                pubFileBiz.editFile(pubFile);
                result++;
            }
        }
        return result;
    }

    private String checkFiles(HttpServletRequest request) {
        String result = "1";
        ServletContext application = request.getServletContext();
        Map<String, String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
        String imageSuffixStr = sysCfgMap.get("inx_image_support_suffix");
        String[] imageSuffixArr = imageSuffixStr.split(",");
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            List<String> fileSuffix = new ArrayList<>();
            fileSuffix.addAll(Arrays.asList(imageSuffixArr));
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //记录上传过程起始时的时间，用来计算上传时间
                //int pre = (int) System.currentTimeMillis();
                //取得上传文件
                List<MultipartFile> files = multiRequest.getFiles(iter.next());
                if (files != null && files.size() > 0) {
                    for (MultipartFile file : files) {

                        //取得当前上传文件的文件名称
                        String fileName = file.getOriginalFilename();
                        if (StringUtil.isNotBlank(fileName)) {
                            String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
                            if (!fileSuffix.contains(suffix)) {
                                return fileName + "的文件格式不正确，只能上传" + imageSuffixStr + "图片格式的文件。";
                            }
                            if (file.getSize() > 10 * 1024 * 1024) {
                                return fileName + "的大小超过10M，不得保存";
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
