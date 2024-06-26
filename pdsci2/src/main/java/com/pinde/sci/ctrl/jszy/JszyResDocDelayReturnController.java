package com.pinde.sci.ctrl.jszy;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDoctorDelayTeturnBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.res.ResBaseStatusEnum;
import com.pinde.sci.enums.res.ResRecTypeEnum;
import com.pinde.sci.enums.sys.OrgTypeEnum;
import com.pinde.sci.model.mo.*;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by pdkj on 2017/10/27.
 * 退培延期功能的控制层
 */
@Controller
@RequestMapping("/jszy/delayReturn")
public class JszyResDocDelayReturnController extends GeneralController {
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IResDoctorDelayTeturnBiz doctorDelayTeturnBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IResDoctorRecruitBiz doctorRecruitBiz;
    @Autowired
    private IFileBiz fileBiz;

    @RequestMapping(value = {"/delayTab"})
    public String delayTab(String roleId, Model model) {
        //应产品要求，延期流程由需要省厅审核，改为基地管理员直接延期不经省厅审核
        model.addAttribute("roleId", roleId);
        return "jszy/delay/tab";
    }

    @RequestMapping(value = {"/delayManage"})
    public String delayManage(Integer currentPage, ResDoctor doctor, HttpServletRequest request,String [] datas,
                              Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType, String roleId) {
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if(datas!=null&&datas.length>0){
            docTypeList = Arrays.asList(datas);
            for(String d : datas){
                dataStr += d+",";
            }
        }
        model.addAttribute("dataStr",dataStr);
        //应产品要求，延期流程由需要省厅审核，改为基地管理员直接延期不经省厅审核
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> delayStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //用于查询
        ResDocotrDelayTeturn delayDoc = new ResDocotrDelayTeturn();
        delayDoc.setTypeId(ResRecTypeEnum.Delay.getId());
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();
        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //省里所有医院
            orgs = orgBiz.searchOrg(sysorg4Search);
            model.addAttribute("orgs", orgs);
            if (orgs != null && orgs.size() > 0 && StringUtil.isBlank(doctor.getOrgFlow())) {
                for (SysOrg tempOrg : orgs) {
                    orgFlowList.add(tempOrg.getOrgFlow());
                }
            }
//            if ("isQuery".equals(operType)) {
//                //查询
//                if ("pass".equals(statusFlag)) {
//                    //省厅审核通过
//                    delayStatusIdList.add(ResBaseStatusEnum.GlobalPassed.getId());
//                } else if ("notPass".equals(statusFlag)) {
//                    //省厅审核不通过
//                    delayStatusIdList.add(ResBaseStatusEnum.GlobalNotPassed.getId());
//                } else if ("all".equals(statusFlag)) {
//                    //省厅查询全部转出
//                    delayStatusIdList.add(ResBaseStatusEnum.GlobalPassed.getId());
//                    delayStatusIdList.add(ResBaseStatusEnum.GlobalNotPassed.getId());
//                }
//            } else if ("isCheck".equals(operType)) {
//                //审核
//                delayStatusIdList.add(ResBaseStatusEnum.Auditing.getId());
//            }
        }
        paramMap.put("delayDoc", delayDoc);
//        paramMap.put("delayStatusIdList", delayStatusIdList);
        paramMap.put("doctor", doctor);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        paramMap.put("docTypeList",docTypeList);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> delayDocs = doctorDelayTeturnBiz.searchDelayInfoByParamMap(paramMap);
        model.addAttribute("delayDocs", delayDocs);
        model.addAttribute("operType", operType);
        model.addAttribute("roleId", roleId);
        return "jszy/delay/delayInfo4global";
    }

    @RequestMapping(value = {"/delayQuery4Local"})
    public String delayQuery4Local(Integer currentPage, ResDoctor doctor, HttpServletRequest request,String [] datas,
                                   Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag) {
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if(datas!=null&&datas.length>0){
            docTypeList = Arrays.asList(datas);
            for(String d : datas){
                dataStr += d+",";
            }
        }
        model.addAttribute("dataStr",dataStr);
        //应产品要求，延期流程由需要省厅审核，改为基地管理员直接延期不经省厅审核
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> delayStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //用于查询
        ResDocotrDelayTeturn delayDoc = new ResDocotrDelayTeturn();
        delayDoc.setTypeId(ResRecTypeEnum.Delay.getId());
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();
        //如果含有协同基地也要查询
        orgFlowList = searchJointOrgList(currentUser.getOrgFlow());
        orgFlowList.add(currentUser.getOrgFlow());
        //省厅审核状态；ResBaseStatusEnum
//        if ("pass".equals(statusFlag)) {
//            //省厅审核通过
//            delayStatusIdList.add(ResBaseStatusEnum.GlobalPassed.getId());
//        } else if ("notPass".equals(statusFlag)) {
//            //省厅审核不通过
//            delayStatusIdList.add(ResBaseStatusEnum.GlobalNotPassed.getId());
//        } else if ("all".equals(statusFlag)) {
//            //省厅查询全部
//            delayStatusIdList.add(ResBaseStatusEnum.GlobalPassed.getId());
//            delayStatusIdList.add(ResBaseStatusEnum.GlobalNotPassed.getId());
//            //待审核
//            delayStatusIdList.add(ResBaseStatusEnum.Auditing.getId());
//        }
        paramMap.put("delayDoc", delayDoc);
        paramMap.put("docTypeList", docTypeList);
//        paramMap.put("delayStatusIdList", delayStatusIdList);
        paramMap.put("doctor", doctor);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> delayDocs = doctorDelayTeturnBiz.searchDelayInfoByParamMap(paramMap);
        model.addAttribute("delayDocs", delayDocs);
        model.addAttribute("roleId", GlobalConstant.USER_LIST_LOCAL);


        //页面跳转控制
        return "jszy/delay/delayInfo4Local";
    }

    @RequestMapping(value = {"/showCheckdelayInfo"})
    public String showCheckdelayInfo(String recordFlow, String roleId, Model model) {
        ResDocotrDelayTeturn delayInfo = doctorDelayTeturnBiz.readInfo(recordFlow);
        ResDoctorRecruit recruit = doctorRecruitBiz.readResDoctorRecruit(delayInfo.getRecruitFlow());
        model.addAttribute("recruit",recruit);
        model.addAttribute("fileName", "globalCheckFile");
        model.addAttribute("agree", ResBaseStatusEnum.GlobalPassed.getId());
        model.addAttribute("disAgree", ResBaseStatusEnum.GlobalNotPassed.getId());
        model.addAttribute("roleId", roleId);
        model.addAttribute("delayInfo", delayInfo);
        return "jszy/delay/checkDelayInfo";
    }

    @RequestMapping(value = {"/checkDelayInfo"})
    @ResponseBody
    public String checkDelayInfo(String delayStatusId, String recordFlow, String auditOpinion,
                                 MultipartFile globalCheckFile,String graduationYear) {
        ResDocotrDelayTeturn delayInfo = doctorDelayTeturnBiz.readInfo(recordFlow);
        delayInfo.setAuditStatusId(delayStatusId);
        delayInfo.setAuditStatusName(ResBaseStatusEnum.getNameById(delayStatusId));
        if(StringUtil.isNotBlank(graduationYear)){
            delayInfo.setGraduationYear(graduationYear);
        }
        if(StringUtil.isNotBlank(auditOpinion)){
            delayInfo.setAuditOpinion(auditOpinion);
        }
        if (globalCheckFile != null && !globalCheckFile.isEmpty()){
            String fileResult = saveCheckFile("globalCheckFile", globalCheckFile, delayInfo);
            if (GlobalConstant.VALIDATE_FILE_FAIL.equals(fileResult)) {
                return GlobalConstant.VALIDATE_FILE_FAIL;
            }
        }
        int result = doctorDelayTeturnBiz.checkDelayInfo(delayInfo);
        if (result > 0) {
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return GlobalConstant.OPRE_FAIL;
        }
    }

    private String saveCheckFile(String filePropertyName, MultipartFile file, ResDocotrDelayTeturn delayInfo) {
        String fileResult = doctorDelayTeturnBiz.checkFile(file);
        if (!GlobalConstant.FLAG_Y.equals(fileResult)) {
            return GlobalConstant.VALIDATE_FILE_FAIL;
        } else {
            String resultPath = doctorDelayTeturnBiz.saveCheckFileToDirs(file, "changeRecruitFile", delayInfo.getTypeId());
            if ("globalCheckFile".equals(filePropertyName)) {
                delayInfo.setGlobalCheckFile(resultPath);
            }
            return resultPath;
        }
    }
    @RequestMapping(value = {"/backTrainTab"})
    public String backTrainTab(String roleId, Model model) {
        model.addAttribute("roleId", roleId);
        return "jszy/backTrain/tab";
    }

    @RequestMapping(value = {"/backTrainManage"})
    public String backTrainManage(Integer currentPage, ResDoctor doctor, HttpServletRequest request,ResDocotrDelayTeturn backTrainDoc,String [] datas,
                                  Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String operType, String roleId) {
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if(datas!=null&&datas.length>0){
            docTypeList = Arrays.asList(datas);
            for(String d : datas){
                dataStr += d+",";
            }
        }
        model.addAttribute("dataStr",dataStr);
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> backTrainStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //用于查询
        backTrainDoc.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();
        List<SysOrg> orgs = new ArrayList<>();
        if (GlobalConstant.USER_LIST_GLOBAL.equals(roleId)) {
            SysOrg sysorg4Search = new SysOrg();
            sysorg4Search.setOrgProvId(currentOrg.getOrgProvId());
            sysorg4Search.setOrgTypeId(OrgTypeEnum.Hospital.getId());
            //省里所有医院
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
                    backTrainStatusIdList.add(ResBaseStatusEnum.GlobalPassed.getId());
                } else if ("notPass".equals(statusFlag)) {
                    //省厅审核不通过
                    backTrainStatusIdList.add(ResBaseStatusEnum.GlobalNotPassed.getId());
                } else if ("all".equals(statusFlag)) {
                    //省厅查询全部转出
                    backTrainStatusIdList.add(ResBaseStatusEnum.GlobalPassed.getId());
                    backTrainStatusIdList.add(ResBaseStatusEnum.GlobalNotPassed.getId());
                }
            } else if ("isCheck".equals(operType)) {
                //审核
                backTrainStatusIdList.add(ResBaseStatusEnum.Auditing.getId());
            }
        }
        paramMap.put("backTrainStatusIdList", backTrainStatusIdList);
        paramMap.put("doctor", doctor);
        paramMap.put("docTypeList", docTypeList);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        paramMap.put("backTrainDoc",backTrainDoc);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> backTrainDocs = doctorDelayTeturnBiz.searchBackTrainInfoByParamMap(paramMap);
        Map<String,Object> fileMaps = new HashMap<String, Object>();
        if(backTrainDocs!=null&&backTrainDocs.size()>0){
            for (Map<String, String> back: backTrainDocs) {
                List<PubFile> files = fileBiz.searchByProductFlow(back.get("recordFlow"));
                if(files!=null&&files.size()>0){
                    fileMaps.put(back.get("recordFlow"),files);
                }
            }
        }
        model.addAttribute("fileMaps",fileMaps);
        model.addAttribute("backTrainDocs", backTrainDocs);
        model.addAttribute("operType", operType);
        model.addAttribute("roleId", roleId);
        return "jszy/backTrain/backTrainInfo4global";
    }

    @RequestMapping(value = {"/backTrainQuery4Local"})
    public String backTrainQuery4Local(Integer currentPage, ResDoctor doctor, HttpServletRequest request,ResDocotrDelayTeturn backTrainDoc,String [] datas,
                                       Model model, @RequestParam(value = "statusFlag", required = false, defaultValue = "all") String statusFlag, String roleId) {
        List<String> docTypeList = new ArrayList<>();
        String dataStr = "";
        if(datas!=null&&datas.length>0){
            docTypeList = Arrays.asList(datas);
            for(String d : datas){
                dataStr += d+",";
            }
        }
        model.addAttribute("dataStr",dataStr);
        //当前用户
        SysUser currentUser = GlobalContext.getCurrentUser();
        //当前机构
        SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
        //查询时用于存放审核状态
        List<String> backTrainStatusIdList = new ArrayList<String>();
        //查询时用于存放机构流水号
        List<String> orgFlowList = new ArrayList<String>();
        //用于查询
        backTrainDoc.setTypeId(ResRecTypeEnum.ReturnTraining.getId());
        //查询时用于存放条件的map
        Map<String, Object> paramMap = new HashMap<>();
        List<SysOrg> orgs = new ArrayList<>();
        //如果含有协同基地也要查询
        orgFlowList = searchJointOrgList(currentUser.getOrgFlow());
        orgFlowList.add(currentUser.getOrgFlow());
        if ("pass".equals(statusFlag)) {
            //省厅审核通过
            backTrainStatusIdList.add(ResBaseStatusEnum.GlobalPassed.getId());
        } else if ("notPass".equals(statusFlag)) {
            //省厅审核不通过
            backTrainStatusIdList.add(ResBaseStatusEnum.GlobalNotPassed.getId());
        } else if ("all".equals(statusFlag)) {
            //省厅查询全部
            backTrainStatusIdList.add(ResBaseStatusEnum.Auditing.getId());
            backTrainStatusIdList.add(ResBaseStatusEnum.GlobalPassed.getId());
            backTrainStatusIdList.add(ResBaseStatusEnum.GlobalNotPassed.getId());
        }
        paramMap.put("backTrainDoc", backTrainDoc);
        paramMap.put("backTrainStatusIdList", backTrainStatusIdList);
        paramMap.put("doctor", doctor);
        paramMap.put("docTypeList", docTypeList);
        if (!orgFlowList.isEmpty()) {
            paramMap.put("orgFlowList", orgFlowList);
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String, String>> backTrainDocs = doctorDelayTeturnBiz.searchBackTrainInfoByParamMap(paramMap);
        Map<String,Object> fileMaps = new HashMap<String, Object>();
        if(backTrainDocs!=null&&backTrainDocs.size()>0){
            for (Map<String, String> back: backTrainDocs) {
                List<PubFile> files = fileBiz.searchByProductFlow(back.get("recordFlow"));
                if(files!=null&&files.size()>0){
                    fileMaps.put(back.get("recordFlow"),files);
                }
            }
        }
        model.addAttribute("fileMaps",fileMaps);
        model.addAttribute("backTrainDocs", backTrainDocs);
        model.addAttribute("roleId", roleId);


        //页面跳转控制
        return "jszy/backTrain/backTrainInfo4Local";
    }

    @RequestMapping(value = {"/showCheckBackTrainInfo"})
    public String showCheckBackTrainInfo(String recordFlow, String roleId, Model model) {
        ResDocotrDelayTeturn backTrainInfo = doctorDelayTeturnBiz.readInfo(recordFlow);
        ResDoctorRecruit recruit = doctorRecruitBiz.readResDoctorRecruit(backTrainInfo.getRecruitFlow());
        model.addAttribute("recruit",recruit);
        model.addAttribute("fileName", "globalCheckFile");
        model.addAttribute("agree", ResBaseStatusEnum.GlobalPassed.getId());
        model.addAttribute("disAgree", ResBaseStatusEnum.GlobalNotPassed.getId());
        model.addAttribute("roleId", roleId);
        model.addAttribute("backTrainInfo", backTrainInfo);
        return "jszy/backTrain/checkBackTrainInfo";
    }

    @RequestMapping(value = {"/checkBackTrain"})
    @ResponseBody
    public String checkBackTrain(String backTrainStatusId, String recordFlow, String auditOpinion,
                                 MultipartFile globalCheckFile) throws DocumentException {
        ResDocotrDelayTeturn backTrainInfo = doctorDelayTeturnBiz.readInfo(recordFlow);
        backTrainInfo.setAuditStatusId(backTrainStatusId);
        backTrainInfo.setAuditStatusName(ResBaseStatusEnum.getNameById(backTrainStatusId));
        if(StringUtil.isNotBlank(auditOpinion)){
            backTrainInfo.setAuditOpinion(auditOpinion);
        }
        if (globalCheckFile != null && !globalCheckFile.isEmpty()){
            String fileResult = saveCheckFile("globalCheckFile", globalCheckFile, backTrainInfo);
            if (GlobalConstant.VALIDATE_FILE_FAIL.equals(fileResult)) {
                return GlobalConstant.VALIDATE_FILE_FAIL;
            }
        }
        ResDoctorRecruit recruit = doctorRecruitBiz.readResDoctorRecruit(backTrainInfo.getRecruitFlow());
        int result = doctorDelayTeturnBiz.checkBackTrain(backTrainInfo,recruit);
        if (result > 0) {
            return GlobalConstant.OPRE_SUCCESSED;
        } else {
            return GlobalConstant.OPRE_FAIL;
        }
    }

    /**
     * 查询当前基地下所有协同基地
     *
     * @param Flow
     * @return
     */
    public List<String> searchJointOrgList(String Flow) {
        List<String> jointOrgFlowList = new ArrayList<String>();
        List<ResJointOrg> resJointOrgList = jointOrgBiz.searchResJointByOrgFlow(Flow);
        if (resJointOrgList != null && !resJointOrgList.isEmpty()) {
            for (ResJointOrg jointOrg : resJointOrgList) {
                jointOrgFlowList.add(jointOrg.getJointOrgFlow());
            }
        }
        return jointOrgFlowList;
    }
}
