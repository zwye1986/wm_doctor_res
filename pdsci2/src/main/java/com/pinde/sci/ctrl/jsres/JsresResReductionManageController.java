package com.pinde.sci.ctrl.jsres;


import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jszy.IJszyDoctorReductionBiz;
import com.pinde.sci.biz.jszy.IJszyResDoctorRecruitBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.FileUtil;
import com.pinde.sci.ctrl.jszy.JszyResReductionManageController;
import com.pinde.sci.enums.jsres.JsResTrainYearEnum;
import com.pinde.sci.enums.jszy.JszyBaseStatusEnum;
import com.pinde.sci.enums.jszy.JszyResDoctorAuditStatusEnum;
import com.pinde.sci.enums.jszy.JszyTrainCategoryEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/jsres/reduction")
public class JsresResReductionManageController extends GeneralController {

    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IJszyResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IJszyDoctorReductionBiz reductionBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;

    private static Logger logger = LoggerFactory.getLogger(JszyResReductionManageController.class);

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
        List<ResDoctorRecruit> recruitList = jsResDoctorRecruitBiz.searchResDoctorRecruitList(recruit, "CREATE_TIME");
        if (recruitList != null && !recruitList.isEmpty()) {
            model.addAttribute("recruitList", recruitList);
        }
        return "jsres/reduction/recruitListTab";
    }

    /**
     * 查看减免信息
     *
     * @return
     */
    @RequestMapping("/getReductionInfo")
    public String getDoctorRecruit(String recruitFlow, String doctorFlow, Model model) {
        if (StringUtil.isNotBlank(recruitFlow)) {
            ResDoctorRecruit doctorRecruit = jsResDoctorRecruitBiz.readResDoctorRecruit(recruitFlow);
            if (doctorRecruit != null) {
                model.addAttribute("doctorRecruit", doctorRecruit);
                if (StringUtil.isNotBlank(doctorRecruit.getDoctorFlow())) {
                    ResDoctor doctor = resDoctorBiz.readDoctor(doctorRecruit.getDoctorFlow());
                    if (doctor != null) {
                        model.addAttribute("doctor", doctor);
                    }
                }
            }
            ResDoctorReduction reduction = reductionBiz.findReductionByRecruitFlow(recruitFlow);
            if (reduction != null) {
                List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", reduction.getRecordFlow());
                model.addAttribute("reductionFiles", reductionFiles);
            }
            //如果审核状态是空则可以编辑或者审核不通过
            if (reduction == null || StringUtil.isBlank(reduction.getAuditStatusId())
                    || JszyBaseStatusEnum.LocalUnPassed.getId().equals(reduction.getAuditStatusId())
                    || JszyBaseStatusEnum.GlobalUnPassed.getId().equals(reduction.getAuditStatusId())) {
                model.addAttribute("canEdit", "Y");
            } else {
                model.addAttribute("canEdit", "N");
            }
            //1.助理全科2.已经减免的不可以申请减免3.只有审核通过的记录才可以减免
            //1.招录信息审核通过展示减免
            if (JszyResDoctorAuditStatusEnum.Passed.getId().equals(doctorRecruit.getAuditStatusId())) {
                //2.助理全科不展示减免
                if (JszyTrainCategoryEnum.TCMAssiGeneral.getId().equals(doctorRecruit.getCatSpeId())) {
                    model.addAttribute("showReduction", "N");
                } else {
                    model.addAttribute("showReduction", "Y");
                }
            } else {
                model.addAttribute("showReduction", "N");
            }

            model.addAttribute("reduction", reduction);
        }
        return "jsres/reduction/reductionInfo";
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
        return "jsres/reduction/uploadFile";
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
    public String reductionApply(String recordFlow, String reduceYear, String afterReduceTrainYear) {
        ResDoctorReduction resDoctorReduction = reductionBiz.findReductionByPK(recordFlow);
        int reductionResult = 0;
        if (resDoctorReduction != null) {
            resDoctorReduction.setReduceYear(reduceYear);
            resDoctorReduction.setAfterReduceTrainYear(afterReduceTrainYear);
            resDoctorReduction.setAuditStatusId(JszyBaseStatusEnum.Auditing.getId());
            resDoctorReduction.setAuditStatusName(JszyBaseStatusEnum.Auditing.getName());
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
        return "jsres/reduction/tab";
    }

    @RequestMapping(value = {"/reductionTabAcc"})
    public String delayTabAcc(String roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "jsres/reduction/tabAcc";
    }

    @RequestMapping(value = {"/reductionManageMain"})
    public String reductionManageMain(Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType, String roleId) {
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());

        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //省里所有医院
            orgs=orgBiz.searchOrgListNew(sysorg4Search);
            model.addAttribute("orgs", orgs);
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            model.addAttribute("orgs",currentOrg);
        }
        model.addAttribute("operType", operType);
        model.addAttribute("roleId", roleId);
        model.addAttribute("statusFlag",statusFlag);
        //页面跳转控制
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4globalMain";
        } else if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4Local";
        }
        return "";
    }

    @RequestMapping(value = {"/reductionManage"})
    public String delayManage(Integer currentPage, ResDoctor doctor, HttpServletRequest request, SysUser user4Search, ResDoctorReduction reduction4Search, String[] datas,
                              Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType, String roleId,String cityId) {
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("search", user4Search);
        model.addAttribute("dataStr", dataStr);
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> reductionStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();

        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //省里所有医院
//            orgs = orgBiz.searchOrg(sysorg4Search);
            if(StringUtil.isNotBlank(cityId)){
                sysorg4Search.setOrgCityId(cityId);
                model.addAttribute("cityId",cityId);
            }
            orgs=orgBiz.searchOrgListNew(sysorg4Search);
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
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
            }
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            orgFlowList.add(currentUser.getOrgFlow());
            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部转出
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalUnPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.Auditing.getId());
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
            String[] numbers = doctor.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                doctor.setSessionNumber("");
            }
        }
        paramMap.put("sessionNumbers",sessionNumbers);
        paramMap.put("reduction", reduction4Search);
        paramMap.put("reductionStatusIdList", reductionStatusIdList);
        paramMap.put("doctor", doctor);
        paramMap.put("sysUser", user4Search);
        paramMap.put("orgFlowList", orgFlowList);
        paramMap.put("docTypeList", docTypeList);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        long startTime = System.currentTimeMillis(); //获取开始时间
        List<Map<String, String>> resDoctorReductions = reductionBiz.findReductionExtByMap(paramMap);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("查询列表运行时间：" + (endTime - startTime) + "ms");//输出程序运行时间
        model.addAttribute("resDoctorReductions", resDoctorReductions);
        model.addAttribute("operType", operType);
        model.addAttribute("roleId", roleId);
        Map<String, List<PubFile>> reductionFileMaps = null;
        if (resDoctorReductions != null && resDoctorReductions.size() > 0) {
            reductionFileMaps = new HashMap<>();
            long startTimeFile = System.currentTimeMillis(); //获取开始时间
            for (Map<String, String> tempReduction : resDoctorReductions) {
                String reductionRecordFlow = tempReduction.get("recordFlow");
                List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", reductionRecordFlow);
                reductionFileMaps.put(reductionRecordFlow, reductionFiles);
            }
            model.addAttribute("reductionFileMaps", reductionFileMaps);
            long endTimeFile = System.currentTimeMillis(); //获取结束时间
            logger.info("查询附件运行时间：" + (endTimeFile - startTimeFile) + "ms");//输出程序运行时间
        }
        //页面跳转控制
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4globalMain";
        } else if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4Local";
        }
        return "";
    }


    @RequestMapping(value = {"/reductionManageAcc"})
    public String delayManageAcc(Integer currentPage, ResDoctor doctor, HttpServletRequest request, SysUser user4Search, ResDoctorReduction reduction4Search, String[] datas,
                              Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType, String roleId,String cityId) {
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("search", user4Search);
        model.addAttribute("dataStr", dataStr);
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> reductionStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();

        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //省里所有医院
//            orgs = orgBiz.searchOrg(sysorg4Search);
            if(StringUtil.isNotBlank(cityId)){
                sysorg4Search.setOrgCityId(cityId);
                model.addAttribute("cityId",cityId);
            }
            orgs=orgBiz.searchOrgListNew(sysorg4Search);
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
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
            }
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            orgFlowList.add(currentUser.getOrgFlow());
            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部转出
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalUnPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.Auditing.getId());
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
            String[] numbers = doctor.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                doctor.setSessionNumber("");
            }
        }
        paramMap.put("sessionNumbers",sessionNumbers);
        paramMap.put("reduction", reduction4Search);
        paramMap.put("reductionStatusIdList", reductionStatusIdList);
        paramMap.put("doctor", doctor);
        paramMap.put("sysUser", user4Search);
        paramMap.put("orgFlowList", orgFlowList);
        paramMap.put("docTypeList", docTypeList);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        long startTime = System.currentTimeMillis(); //获取开始时间
        List<Map<String, String>> resDoctorReductions = reductionBiz.findReductionExtByMapAcc(paramMap);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("查询列表运行时间：" + (endTime - startTime) + "ms");//输出程序运行时间
        model.addAttribute("resDoctorReductions", resDoctorReductions);
        model.addAttribute("operType", operType);
        model.addAttribute("roleId", roleId);
        Map<String, List<PubFile>> reductionFileMaps = null;
        if (resDoctorReductions != null && resDoctorReductions.size() > 0) {
            reductionFileMaps = new HashMap<>();
            long startTimeFile = System.currentTimeMillis(); //获取开始时间
            for (Map<String, String> tempReduction : resDoctorReductions) {
                String reductionRecordFlow = tempReduction.get("recordFlow");
                List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", reductionRecordFlow);
                reductionFileMaps.put(reductionRecordFlow, reductionFiles);
            }
            model.addAttribute("reductionFileMaps", reductionFileMaps);
            long endTimeFile = System.currentTimeMillis(); //获取结束时间
            logger.info("查询附件运行时间：" + (endTimeFile - startTimeFile) + "ms");//输出程序运行时间
        }
        //页面跳转控制
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4globalMainAcc";
        } else if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4LocalAcc";
        }
        return "";
    }


    @RequestMapping(value = {"/reductionManageList"})
    public String reductionManageList(Integer currentPage, ResDoctor doctor, HttpServletRequest request, SysUser user4Search, ResDoctorReduction reduction4Search, String[] datas,
                                        Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType, String roleId,
                                        String jointOrgFlag,String cityId) {
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("search", user4Search);
        model.addAttribute("dataStr", dataStr);
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> reductionStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();
        List<String> jointOrgFlowList=new ArrayList<String>();

        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //省里所有医院
//            orgs = orgBiz.searchOrg(sysorg4Search);
            if(StringUtil.isNotBlank(cityId)){
                sysorg4Search.setOrgCityId(cityId);
            }
            orgs=orgBiz.searchOrgListNew(sysorg4Search);
            model.addAttribute("orgs", orgs);
            if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
                for (SysOrg tempOrg : orgs) {
                    if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                orgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                    }
                    orgFlowList.add(tempOrg.getOrgFlow());
                }
            }
            if(StringUtil.isNotBlank(doctor.getOrgFlow())){
                orgFlowList.add(doctor.getOrgFlow());
                if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            }

            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
            }
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            orgFlowList.add(currentUser.getOrgFlow());
            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部转出
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalUnPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.Auditing.getId());
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
            String[] numbers = doctor.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                doctor.setSessionNumber("");
            }
        }
        paramMap.put("sessionNumbers",sessionNumbers);
        paramMap.put("reduction", reduction4Search);
        paramMap.put("reductionStatusIdList", reductionStatusIdList);
        paramMap.put("doctor", doctor);
        paramMap.put("sysUser", user4Search);
        paramMap.put("orgFlowList", orgFlowList);
        paramMap.put("docTypeList", docTypeList);
        paramMap.put("cityId",cityId);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        long startTime = System.currentTimeMillis(); //获取开始时间
        List<Map<String, String>> resDoctorReductions = reductionBiz.findReductionExtByMap(paramMap);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("查询列表运行时间：" + (endTime - startTime) + "ms");//输出程序运行时间
        model.addAttribute("resDoctorReductions", resDoctorReductions);
        model.addAttribute("operType", operType);
        model.addAttribute("roleId", roleId);
        Map<String, List<PubFile>> reductionFileMaps = null;
        Map<String, String> reductionFilePaths = new HashMap<>();
        if (resDoctorReductions != null && resDoctorReductions.size() > 0) {
            reductionFileMaps = new HashMap<>();
            long startTimeFile = System.currentTimeMillis(); //获取开始时间
            for (Map<String, String> tempReduction : resDoctorReductions) {
                String reductionRecordFlow = tempReduction.get("recordFlow");
                String filePath = tempReduction.get("proveFilePath");
                if(StringUtil.isNotBlank(filePath)){
                    reductionFilePaths.put(reductionRecordFlow,filePath);
                }else {
                    List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", reductionRecordFlow);
                    reductionFileMaps.put(reductionRecordFlow, reductionFiles);
                }
            }
            model.addAttribute("reductionFileMaps", reductionFileMaps);
            model.addAttribute("reductionFilePaths", reductionFilePaths);
            long endTimeFile = System.currentTimeMillis(); //获取结束时间
            logger.info("查询附件运行时间：" + (endTimeFile - startTimeFile) + "ms");//输出程序运行时间
        }
        //页面跳转控制
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4globalList";
        } else if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4Local";
        }
        return "";
    }


    @RequestMapping(value = {"/reductionManageListAcc"})
    public String reductionManageListAcc(Integer currentPage, ResDoctor doctor, HttpServletRequest request, SysUser user4Search, ResDoctorReduction reduction4Search, String[] datas,
                                      Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType, String roleId,
                                      String jointOrgFlag,String cityId) {
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("search", user4Search);
        model.addAttribute("dataStr", dataStr);
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> reductionStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();
        List<String> jointOrgFlowList=new ArrayList<String>();

        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //省里所有医院
//            orgs = orgBiz.searchOrg(sysorg4Search);
            if(StringUtil.isNotBlank(cityId)){
                sysorg4Search.setOrgCityId(cityId);
            }
            orgs=orgBiz.searchOrgListNew(sysorg4Search);
            model.addAttribute("orgs", orgs);
            if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
                for (SysOrg tempOrg : orgs) {
                    if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                orgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                    }
                    orgFlowList.add(tempOrg.getOrgFlow());
                }
            }
            if(StringUtil.isNotBlank(doctor.getOrgFlow())){
                orgFlowList.add(doctor.getOrgFlow());
                if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            }

            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
            }
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            orgFlowList.add(currentUser.getOrgFlow());
            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部转出
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalUnPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.Auditing.getId());
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
            String[] numbers = doctor.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                doctor.setSessionNumber("");
            }
        }
        paramMap.put("sessionNumbers",sessionNumbers);
        paramMap.put("reduction", reduction4Search);
        paramMap.put("reductionStatusIdList", reductionStatusIdList);
        paramMap.put("doctor", doctor);
        paramMap.put("sysUser", user4Search);
        paramMap.put("orgFlowList", orgFlowList);
        paramMap.put("docTypeList", docTypeList);
        paramMap.put("cityId",cityId);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        long startTime = System.currentTimeMillis(); //获取开始时间
        List<Map<String, String>> resDoctorReductions = reductionBiz.findReductionExtByMapAcc(paramMap);
        long endTime = System.currentTimeMillis(); //获取结束时间
        logger.info("查询列表运行时间：" + (endTime - startTime) + "ms");//输出程序运行时间
        model.addAttribute("resDoctorReductions", resDoctorReductions);
        model.addAttribute("operType", operType);
        model.addAttribute("roleId", roleId);
        Map<String, List<PubFile>> reductionFileMaps = null;
        Map<String, String> reductionFilePaths = new HashMap<>();
        if (resDoctorReductions != null && resDoctorReductions.size() > 0) {
            reductionFileMaps = new HashMap<>();
            long startTimeFile = System.currentTimeMillis(); //获取开始时间
            for (Map<String, String> tempReduction : resDoctorReductions) {
                String reductionRecordFlow = tempReduction.get("recordFlow");
                String filePath = tempReduction.get("proveFilePath");
                if(StringUtil.isNotBlank(filePath)){
                    reductionFilePaths.put(reductionRecordFlow,filePath);
                }else {
                    List<PubFile> reductionFiles = pubFileBiz.findFileByTypeFlow("Reduction", reductionRecordFlow);
                    reductionFileMaps.put(reductionRecordFlow, reductionFiles);
                }
            }
            model.addAttribute("reductionFileMaps", reductionFileMaps);
            model.addAttribute("reductionFilePaths", reductionFilePaths);
            long endTimeFile = System.currentTimeMillis(); //获取结束时间
            logger.info("查询附件运行时间：" + (endTimeFile - startTimeFile) + "ms");//输出程序运行时间
        }
        //页面跳转控制
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4globalListAcc";
        } else if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            return "jsres/reduction/reductionInfo4LocalAcc";
        }
        return "";
    }

    @RequestMapping(value = {"/exportList"})
    public void exportList(ResDoctor doctor, HttpServletResponse response, SysUser user4Search, ResDoctorReduction reduction4Search, String[] datas,
                             Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType,
                             String roleId, String jointOrgFlag, String cityId) throws Exception{
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("search", user4Search);
        model.addAttribute("dataStr", dataStr);
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> reductionStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();
        List<String> jointOrgFlowList=new ArrayList<String>();

        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //省里所有医院
//            orgs = orgBiz.searchOrg(sysorg4Search);
            if(StringUtil.isNotBlank(cityId)){
                sysorg4Search.setOrgCityId(cityId);
            }
            orgs=orgBiz.searchOrgListNew(sysorg4Search);
            model.addAttribute("orgs", orgs);
            if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
                for (SysOrg tempOrg : orgs) {
                    if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                orgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                    }
                    orgFlowList.add(tempOrg.getOrgFlow());
                }
            }
            if(StringUtil.isNotBlank(doctor.getOrgFlow())){
                orgFlowList.add(doctor.getOrgFlow());
                if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            }

            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
            }
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            orgFlowList.add(currentUser.getOrgFlow());
            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部转出
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalUnPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.Auditing.getId());
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
            String[] numbers = doctor.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                doctor.setSessionNumber("");
            }
        }
        paramMap.put("sessionNumbers",sessionNumbers);
        paramMap.put("reduction", reduction4Search);
        paramMap.put("reductionStatusIdList", reductionStatusIdList);
        paramMap.put("doctor", doctor);
        paramMap.put("sysUser", user4Search);
        paramMap.put("orgFlowList", orgFlowList);
        paramMap.put("docTypeList", docTypeList);
        paramMap.put("cityId",cityId);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        List<Map<String, String>> list = reductionBiz.findReductionExtByMap(paramMap);

        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        styleCenter.setWrapText(true);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFRow rowDep = sheet.createRow(0);//第一行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并单元格
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("学员减免信息表");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        String[] titles = new String[]{
                "序号",
                "姓名",
                "证件号码",
                "基地",
                "培训专业",
                "基地审核意见",
                "省厅审核意见",
                "审核状态"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowTwo.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并单元格
        //行数
        int rowNum = 2;
        //存放在excel中的行数据
        String[] resultList = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                Map<String, String> map = list.get(i);
                resultList = new String[]{
                        i + 1 + "",
                        map.get("userName") == null ? "" : map.get("userName"),
                        map.get("idNo") == null ? "" : map.get("idNo"),
                        map.get("orgName") == null ? "" : map.get("orgName"),
                        map.get("trainingSpeName") == null ? "" : map.get("trainingSpeName"),
                        map.get("localAuditOpinion") == null ? "无" : map.get("localAuditOpinion"),
                        map.get("globalAuditOpinion") == null ? "无" : map.get("globalAuditOpinion"),
                        map.get("auditStatusName") == null ? "" : map.get("auditStatusName")
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                }
            }
        }
        String fileName = "学员减免信息表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }

    @RequestMapping(value = {"/exportListAcc"})
    public void exportListAcc(ResDoctor doctor, HttpServletResponse response, SysUser user4Search, ResDoctorReduction reduction4Search, String[] datas,
                           Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType,
                           String roleId, String jointOrgFlag, String cityId) throws Exception{
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if (datas != null && datas.length > 0) {
            docTypeList = Arrays.asList(datas);
            for (String d : datas) {
                dataStr += d + ",";
            }
        }
        model.addAttribute("search", user4Search);
        model.addAttribute("dataStr", dataStr);
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> reductionStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();
        List<String> jointOrgFlowList=new ArrayList<String>();

        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //省里所有医院
//            orgs = orgBiz.searchOrg(sysorg4Search);
            if(StringUtil.isNotBlank(cityId)){
                sysorg4Search.setOrgCityId(cityId);
            }
            orgs=orgBiz.searchOrgListNew(sysorg4Search);
            model.addAttribute("orgs", orgs);
            if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
                for (SysOrg tempOrg : orgs) {
                    if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(tempOrg.getOrgFlow());
                        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                            for (ResJointOrg jointOrg : resJointOrgList) {
                                orgFlowList.add(jointOrg.getJointOrgFlow());
                            }
                        }
                    }
                    orgFlowList.add(tempOrg.getOrgFlow());
                }
            }
            if(StringUtil.isNotBlank(doctor.getOrgFlow())){
                orgFlowList.add(doctor.getOrgFlow());
                if(GlobalConstant.FLAG_Y.equals(jointOrgFlag)){
                    List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(doctor.getOrgFlow());
                    if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
                        for (ResJointOrg jointOrg : resJointOrgList) {
                            orgFlowList.add(jointOrg.getJointOrgFlow());
                        }
                    }
                }
            }

            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
            }
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            orgFlowList.add(currentUser.getOrgFlow());
            if ("isQuery".equals(operType)) {
                //查询
                if ("pass".equals(statusFlag)) {
                    //省厅审核通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部转出
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.LocalUnPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalPassed.getId());
                    reductionStatusIdList.add(JszyBaseStatusEnum.GlobalUnPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                reductionStatusIdList.add(JszyBaseStatusEnum.Auditing.getId());
            }
        }
        List<String> sessionNumbers = new ArrayList<String>();//年级多选
        if (StringUtil.isNotBlank(doctor.getSessionNumber())) {
            String[] numbers = doctor.getSessionNumber().split(",");
            if (numbers != null && numbers.length > 0) {
                sessionNumbers = Arrays.asList(numbers);
                doctor.setSessionNumber("");
            }
        }
        paramMap.put("sessionNumbers",sessionNumbers);
        paramMap.put("reduction", reduction4Search);
        paramMap.put("reductionStatusIdList", reductionStatusIdList);
        paramMap.put("doctor", doctor);
        paramMap.put("sysUser", user4Search);
        paramMap.put("orgFlowList", orgFlowList);
        paramMap.put("docTypeList", docTypeList);
        paramMap.put("cityId",cityId);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        List<Map<String, String>> list = reductionBiz.findReductionExtByMapAcc(paramMap);

        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HorizontalAlignment.CENTER);
        styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
        styleCenter.setWrapText(true);
        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HorizontalAlignment.LEFT);
        styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HorizontalAlignment.CENTER);
        stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
        styleLeft.setWrapText(true);

        HSSFRow rowDep = sheet.createRow(0);//第一行
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并单元格
        rowDep.setHeightInPoints(20);
        HSSFCell cellOne = rowDep.createCell(0);
        cellOne.setCellStyle(styleCenter);
        cellOne.setCellValue("学员减免信息表");

        HSSFRow rowTwo = sheet.createRow(1);//第二行
        String[] titles = new String[]{
                "序号",
                "姓名",
                "证件号码",
                "基地",
                "培训专业",
                "基地审核意见",
                "省厅审核意见",
                "审核状态"
        };
        HSSFCell cellTitle = null;
        for (int i = 0; i < titles.length; i++) {
            cellTitle = rowTwo.createCell(i);
            cellTitle.setCellValue(titles[i]);
            cellTitle.setCellStyle(styleCenter);
            sheet.setColumnWidth(i, titles.length * 2 * 256);
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 7));//合并单元格
        //行数
        int rowNum = 2;
        //存放在excel中的行数据
        String[] resultList = null;
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++, rowNum++) {
                HSSFRow rowFour = sheet.createRow(rowNum);//第二行
                Map<String, String> map = list.get(i);
                resultList = new String[]{
                        i + 1 + "",
                        map.get("userName") == null ? "" : map.get("userName"),
                        map.get("idNo") == null ? "" : map.get("idNo"),
                        map.get("orgName") == null ? "" : map.get("orgName"),
                        map.get("trainingSpeName") == null ? "" : map.get("trainingSpeName"),
                        map.get("localAuditOpinion") == null ? "无" : map.get("localAuditOpinion"),
                        map.get("globalAuditOpinion") == null ? "无" : map.get("globalAuditOpinion"),
                        map.get("auditStatusName") == null ? "" : map.get("auditStatusName")
                };
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cellFirst = rowFour.createCell(j);
                    cellFirst.setCellStyle(styleCenter);
                    cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
                }
            }
        }
        String fileName = "学员减免信息表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        wb.write(response.getOutputStream());
    }


    @RequestMapping(value = {"/showCheckReductionInfo"})
    public String showCheckReductionInfo(String recordFlow, String roleId, Model model) {
        ResDoctorReduction reduction = reductionBiz.findReductionByPK(recordFlow);
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            model.addAttribute("agree", JszyBaseStatusEnum.LocalPassed.getId());
            model.addAttribute("disAgree", JszyBaseStatusEnum.LocalUnPassed.getId());
        } else {
            model.addAttribute("agree", JszyBaseStatusEnum.GlobalPassed.getId());
            model.addAttribute("disAgree", JszyBaseStatusEnum.GlobalUnPassed.getId());
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("reduction", reduction);
        return "jsres/reduction/checkReduction";
    }

    @RequestMapping(value = {"/checkReductionInfo"})
    @ResponseBody
    public String checkReductionInfo(String reductionAuditStatusId, String recordFlow, String auditOpinion, String roleId) {
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        ResDoctorReduction reduction = reductionBiz.findReductionByPK(recordFlow);
        reduction.setAuditStatusId(reductionAuditStatusId);
        reduction.setAuditStatusName(JszyBaseStatusEnum.getNameById(reductionAuditStatusId));
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            reduction.setLocalAuditTime(DateUtil.getCurrDateTime());
            reduction.setLocalAuditUserFlow(currentUser.getUserFlow());
            reduction.setLocalAuditOpinion(auditOpinion);
        }
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            reduction.setGlobalAuditTime(DateUtil.getCurrDateTime());
            reduction.setGlobalAuditUserFlow(currentUser.getUserFlow());
            reduction.setGlobalAuditOpinion(auditOpinion);
        }
        int result = reductionBiz.edit(reduction);
        if (JszyBaseStatusEnum.GlobalPassed.getId().equals(reductionAuditStatusId)) {
            ResDoctorRecruitWithBLOBs docRecWithBLOBs = jsResDoctorRecruitBiz.readRecruit(reduction.getRecruitFlow());
            ResDoctor doctor = resDoctorBiz.readDoctor(docRecWithBLOBs.getDoctorFlow());
            int year = 0;
            if ("1".equals(reduction.getAfterReduceTrainYear())) {
                docRecWithBLOBs.setTrainYear(JsResTrainYearEnum.OneYear.getId());
                year = 1;
            }
            if ("2".equals(reduction.getAfterReduceTrainYear())) {
                docRecWithBLOBs.setTrainYear(JsResTrainYearEnum.TwoYear.getId());
                year = 2;
            }
            year = Integer.parseInt(docRecWithBLOBs.getSessionNumber()) + year;
            docRecWithBLOBs.setGraduationYear(String.valueOf(year));
            doctor.setTrainingYears(docRecWithBLOBs.getTrainYear());
            doctor.setGraduationYear(docRecWithBLOBs.getGraduationYear());
            resDoctorBiz.editDoctor(doctor);
            jsResDoctorRecruitBiz.saveDoctorRecruit(docRecWithBLOBs);
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
//                            String newDir = StringUtil.defaultString("D:\\upload") + File.separator + productType + File.separator + dateString + File.separator + recordFlow;
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

    @RequestMapping(value = {"/showCheckReductionInfoList"})
    public String showCheckReductionInfoList(String recordFlowList, String roleId, Model model) {
        if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
            model.addAttribute("agree", JszyBaseStatusEnum.LocalPassed.getId());
            model.addAttribute("disAgree", JszyBaseStatusEnum.LocalUnPassed.getId());
        } else {
            model.addAttribute("agree", JszyBaseStatusEnum.GlobalPassed.getId());
            model.addAttribute("disAgree", JszyBaseStatusEnum.GlobalUnPassed.getId());
        }
        model.addAttribute("roleId", roleId);
        model.addAttribute("recordFlowList", recordFlowList);
        return "jsres/reduction/checkReductionList";
    }

    @RequestMapping(value = {"/checkReductionInfoList"})
    @ResponseBody
    public String checkReductionInfoList(String reductionAuditStatusId, String recordFlowList, String auditOpinion, String roleId) {
        //当前用户
        if (StringUtil.isNotBlank(recordFlowList)) {
            SysUser currentUser = GlobalContext.getCurrentUser();
            List<String> recordFlows = Arrays.asList(recordFlowList.split(","));
            for (String recordFlow : recordFlows) {
                ResDoctorReduction reduction = reductionBiz.findReductionByPK(recordFlow);
                reduction.setAuditStatusId(reductionAuditStatusId);
                reduction.setAuditStatusName(JszyBaseStatusEnum.getNameById(reductionAuditStatusId));
                if (GlobalConstant.USER_LIST_LOCAL.equals(roleId)) {
                    reduction.setLocalAuditTime(DateUtil.getCurrDateTime());
                    reduction.setLocalAuditUserFlow(currentUser.getUserFlow());
                    reduction.setLocalAuditOpinion(auditOpinion);
                }
                if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
                    reduction.setGlobalAuditTime(DateUtil.getCurrDateTime());
                    reduction.setGlobalAuditUserFlow(currentUser.getUserFlow());
                    reduction.setGlobalAuditOpinion(auditOpinion);
                }
                int result = reductionBiz.edit(reduction);
                if (JszyBaseStatusEnum.GlobalPassed.getId().equals(reductionAuditStatusId)) {
                    ResDoctorRecruitWithBLOBs docRecWithBLOBs = jsResDoctorRecruitBiz.readRecruit(reduction.getRecruitFlow());
                    ResDoctor doctor = resDoctorBiz.readDoctor(docRecWithBLOBs.getDoctorFlow());
                    int year = 0;
                    if ("1".equals(reduction.getAfterReduceTrainYear())) {
                        docRecWithBLOBs.setTrainYear(JsResTrainYearEnum.OneYear.getId());
                        year = 1;
                    }
                    if ("2".equals(reduction.getAfterReduceTrainYear())) {
                        docRecWithBLOBs.setTrainYear(JsResTrainYearEnum.TwoYear.getId());
                        year = 2;
                    }
                    year = Integer.parseInt(docRecWithBLOBs.getSessionNumber()) + year;
                    docRecWithBLOBs.setGraduationYear(String.valueOf(year));
                    doctor.setTrainingYears(docRecWithBLOBs.getTrainYear());
                    doctor.setGraduationYear(docRecWithBLOBs.getGraduationYear());
                    resDoctorBiz.editDoctor(doctor);
                    jsResDoctorRecruitBiz.saveDoctorRecruit(docRecWithBLOBs);
                }
            }
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }

}
