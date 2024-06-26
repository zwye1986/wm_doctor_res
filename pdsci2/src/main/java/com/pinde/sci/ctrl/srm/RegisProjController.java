package com.pinde.sci.ctrl.srm;

import com.pinde.core.jspform.Page;
import com.pinde.core.jspform.PageGroup;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.JspFormUtil;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.*;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.srm.WxeysrmProjExportForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.RegisProjFundExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目登记（无锡二院）
 */
@Controller
@RequestMapping("/srm/regis/proj")
public class RegisProjController extends GeneralController {

    @Autowired
    private IPubProjBiz projBiz;
    @Autowired
    private IProjPageBiz projPageBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IProjProcessBiz processBiz;
    @Autowired
    private IFundInfoBiz fundInfoBiz;
    @Autowired
    private IFundInfoDetailBiz fundInfoDetailBiz;
    @Autowired
    private IRegisProjBiz regisProjBiz;
    @Autowired
    private IFundBiz fundBiz;
    @Autowired
    private IAchScoreBiz achScoreBiz;

    /**
     * 项目登记列表
     *
     * @param
     * @param currentPage
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(PubProj pubProj, Integer currentPage, HttpServletRequest request, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        PageHelper.startPage(currentPage, getPageSize(request));
        pubProj.setApplyUserFlow(currUser.getUserFlow());
        pubProj.setProjTypeId("wxdermyy.xmdj");
        if (StringUtil.isBlank(pubProj.getProjStatusId())) {
            pubProj.setProjStatusId(AidProjStatusEnum.NonSubmit.getId());
        }
        if (pubProj.getProjStatusId().equals("Y")) {
            pubProj.setProjStatusId("");
        }
        List<PubProj> pubProjList = this.projBiz.queryProjList(pubProj);
        model.addAttribute("pubProjList", pubProjList);
        return "srm/proj/regis/regisProjList";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        String projTypeId = "wxdermyy.xmdj";
        PageGroup projPage = this.projPageBiz.getPageGroup(ProjRecTypeEnum.Info.getId(), projTypeId);
        List<SysDept> deptList = getDept();
        model.addAttribute("deptList", deptList);
        return projPage.getFirstPageJsp();
    }

    /**
     * 科研项目登记
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"/saveStepForKy/{scope}"}, method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String saveStepForKy(@PathVariable String scope, HttpServletRequest request, String agreeFlag) {
        //String typeId = InitConfig.getSysCfg("srm_aidproj_ky");
        String categoryId = ProjCategroyEnum.Ky.getId();
        if ("apply".equals(scope)) {
            return saveStep(request, categoryId, GlobalConstant.FLAG_N, "");
        }
        if ("audit".equals(scope)) {
            return saveStep(request, categoryId, GlobalConstant.FLAG_Y, agreeFlag);
        }
        return "";
    }

    public String saveStep(HttpServletRequest request, String categoryId, String auditFlag, String agreeFlag) {
        //项目是否存在
        boolean projIsExist = true;
        SysUser currUser = GlobalContext.getCurrentUser();
        Map<String, String[]> dataMap = JspFormUtil.getParameterMap(request);
        String projTypeId = "wxdermyy.xmdj";
        String pageName = "";
        String nextPageName = "";
        if (dataMap.get("pageName") != null) {
            pageName = dataMap.get("pageName")[0];
        }
        if (dataMap.get("nextPageName") != null) {
            nextPageName = dataMap.get("nextPageName")[0];
        }
        Page page = _getPage(pageName, projTypeId);
        //nextPage可支持不同页面的下一步
        //Page nextPage = _getPage(nextPageName , typeId);
        PubProj pubProj = this.createRegisProj(dataMap, categoryId, projTypeId);

        String xmlStr = JspFormUtil.updateXmlStr("", page, dataMap);
        pubProj.setProjInfo(xmlStr);
        PubProjProcess process = new PubProjProcess();
        if (auditFlag.equals(GlobalConstant.FLAG_Y)) {
            if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
                pubProj.setProjStatusId(AidProjStatusEnum.Pass.getId());
                pubProj.setProjStatusName(AidProjStatusEnum.Pass.getName());
                process.setProjStatusId(AidProjStatusEnum.Pass.getId());
                process.setProjStatusName(AidProjStatusEnum.Pass.getName());
                process.setProcessRemark(AidProjStatusEnum.Pass.getName());
            } else {
                pubProj.setProjStatusId(AidProjStatusEnum.NonSubmit.getId());
                pubProj.setProjStatusName(AidProjStatusEnum.NonSubmit.getName());
                process.setProjStatusId(AidProjStatusEnum.Back.getId());
                process.setProjStatusName(AidProjStatusEnum.Back.getName());
                process.setProcessRemark(AidProjStatusEnum.Back.getName());
            }
            process.setProjFlow(pubProj.getProjFlow());
            process.setProcessFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(process, true);
            process.setOperTime(process.getCreateTime());
            process.setProjStageId(ProjStageEnum.Apply.getId());
            process.setProjStageName(ProjStageEnum.Apply.getName());

            process.setOperUserFlow(currUser.getUserFlow());
            process.setOperUserName(currUser.getUserName());
            process.setAuditContent(dataMap.get("auditContent")[0]);
            processBiz.addProcess(process);
//            projBiz.modProject(pubProj);
        } else {
            pubProj.setProjStatusId(AidProjStatusEnum.NonSubmit.getId());
            pubProj.setProjStatusName(AidProjStatusEnum.NonSubmit.getName());
            pubProj.setApplyUserFlow(currUser.getUserFlow());
            pubProj.setApplyOrgFlow(currUser.getOrgFlow());
            //pubProj.setApplyDeptFlow(currUser.getDeptFlow());
            pubProj.setApplyUserName(currUser.getUserName());
            pubProj.setApplyOrgName(currUser.getOrgName());
            // pubProj.setApplyDeptName(currUser.getDeptName());
        }

        SrmProjFundInfo fundInfo;
        //如果项目存在
        if (StringUtil.isNotBlank(pubProj.getProjFlow())) {
            SrmProjFundInfo fundInfoTemp = new SrmProjFundInfo();
            fundInfoTemp.setProjFlow(pubProj.getProjFlow());
            //查询该项目下的经费信息
            List<SrmProjFundInfo> fundInfoList = fundInfoBiz.searchFundInfo(fundInfoTemp);
            //经费信息存在
            if (null != fundInfoList && fundInfoList.size() > 0) {
                fundInfo = fundInfoList.get(0);
            } else {
                fundInfo = new SrmProjFundInfo();
                fundInfo.setProjFlow(pubProj.getProjFlow());
            }

        } else {
            projIsExist = false;
            String projFlow = PkUtil.getUUID();
            pubProj.setProjFlow(projFlow);
            fundInfo = new SrmProjFundInfo();
            fundInfo.setProjFlow(projFlow);

            process.setProjFlow(projFlow);
            process.setProcessFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(process, true);
            process.setOperTime(process.getCreateTime());
            process.setProjStageId(ProjStageEnum.Apply.getId());
            process.setProjStageName(ProjStageEnum.Apply.getName());
            process.setOperUserFlow(currUser.getUserFlow());
            process.setOperUserName(currUser.getUserName());
            process.setProjStatusId(pubProj.getProjStatusId());
            process.setProjStatusName(pubProj.getProjStatusName());

        }
        if (dataMap.get("fund_amount") != null) {
            fundInfo.setAmountFund(new BigDecimal(Double.parseDouble(dataMap.get("fund_amount")[0])));
        }
        if (dataMap.get("fund_xiabo") != null) {
            fundInfo.setGoveFund(new BigDecimal(Double.parseDouble(dataMap.get("fund_xiabo")[0])));
        }
        if (dataMap.get("fund_peitao") != null) {
            fundInfo.setOrgFund(new BigDecimal(Double.parseDouble(dataMap.get("fund_peitao")[0])));
        }
        PubProjAuthor projAuthor = new PubProjAuthor();
        if (auditFlag.equals(GlobalConstant.FLAG_Y)) {
            if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
                PubProj projTemp = projBiz.readProject(pubProj.getProjFlow());
                if (dataMap.get("realityAmount") != null) {
                    double realityAmount = Double.parseDouble(dataMap.get("realityAmount")[0]);
                    fundInfo.setRealityAmount(new BigDecimal(realityAmount));
                }
                if (dataMap.get("fundIncomeTime") != null) {
                    fundInfo.setFundIncomeTime(dataMap.get("fundIncomeTime")[0]);
                }

                if (dataMap.get("scoreFlow") != null) {
                    SrmAchScore score = new SrmAchScore();
                    score.setScoreFlow(dataMap.get("scoreFlow")[0]);
                    List<SrmAchScore> list = achScoreBiz.searchScoreSetList(score);
                    if (list != null && list.size() > 0 && list.get(0) != null) {
                        if (StringUtil.isNotBlank(projTemp.getProjFlow())) {
                            projAuthor.setProjFlow(projTemp.getProjFlow());
                        }
                        if (StringUtil.isNotBlank(projTemp.getApplyUserFlow())) {
                            projAuthor.setUserFlow(projTemp.getApplyUserFlow());
                        }
                        if (StringUtil.isNotBlank(projTemp.getApplyUserName())) {
                            projAuthor.setAuthorName(projTemp.getApplyUserName());
                        }
                        if (StringUtil.isNotBlank(projTemp.getApplyDeptFlow())) {
                            projAuthor.setDeptFlow(projTemp.getApplyDeptFlow());
                        }
                        if (StringUtil.isNotBlank(projTemp.getApplyDeptName())) {
                            projAuthor.setDeptName(projTemp.getApplyDeptName());
                        }
                        if (StringUtil.isNotBlank(projTemp.getBranchId())) {
                            projAuthor.setBranchId(projTemp.getBranchId());
                        }
                        if (StringUtil.isNotBlank(projTemp.getBranchName())) {
                            projAuthor.setBranchName(projTemp.getBranchName());
                        }
                        projAuthor.setScoreFlow(list.get(0).getScoreFlow());
                        projAuthor.setScoreName(list.get(0).getScoreName());
                        projAuthor.setAchScore(list.get(0).getScorePersonalValue().add(new BigDecimal(0)));
                        projAuthor.setAchScoreDept(list.get(0).getScoreDeptValue().add(new BigDecimal(0)));
                    }
                    if (dataMap.get("scoreName") != null) {
                        projAuthor.setScoreName(dataMap.get("scoreName")[0]);
                    }
                }

            }
        }
        List<SrmProjFundDetail> fundDetailList = createRegisFundInfo(dataMap);
        this.regisProjBiz.saveRegisProjInfo(pubProj, process, fundInfo, fundDetailList, projIsExist, projAuthor);
        //目前只针对单页面
        if ("finish".equals(nextPageName)) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        if (auditFlag.equals(GlobalConstant.FLAG_Y)) {
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return "";
    }

    private List<SrmProjFundDetail> createRegisFundInfo(Map<String, String[]> dataMap) {
        List<SrmProjFundDetail> fundDetailList = new ArrayList<>();
        int count = 0;
        if (dataMap.get("fundList_type") != null) {
            count = dataMap.get("fundList_type").length;
        }
        for (int i = 0; i < count; i++) {
            SrmProjFundDetail fundDetail = new SrmProjFundDetail();
            if (null != dataMap.get("fundList_flow")) {
                if (StringUtil.isNotBlank(dataMap.get("fundList_flow")[i])) {
                    fundDetail.setFundDetailFlow(dataMap.get("fundList_flow")[i]);
                }
            }
            if (null != dataMap.get("fundList_type")) {
                if (StringUtil.isNotBlank(dataMap.get("fundList_type")[i])) {
                    fundDetail.setReimburseName(dataMap.get("fundList_type")[i]);
                    //  fundDetail.setReimburseName(DictTypeEnum.WxeyFundType.getDictNameById(dataMap.get("fundList_type")[i]));
                }
            }
            if (null != dataMap.get("fundList_money")) {
                if (StringUtil.isNotBlank(dataMap.get("fundList_money")[i])) {
                    fundDetail.setMoney(new BigDecimal(Double.parseDouble(dataMap.get("fundList_money")[i])));
                }
            }
            /*if(StringUtil.isNotBlank(dataMap.get("fundList_scale")[i])){
                fundDetail.setFundDetailFlow(dataMap.get("fundList_scale")[i]);
            }*/
            if (null != dataMap.get("fundList_remark")) {
                if (StringUtil.isNotBlank(dataMap.get("fundList_remark")[i])) {
                    fundDetail.setRemark(dataMap.get("fundList_remark")[i]);
                }
            }

            fundDetailList.add(fundDetail);
        }

        return fundDetailList;
    }

    private PubProj createRegisProj(Map<String, String[]> dataMap, String projCategoryId, String projTypeId) {
        PubProj pubProj = new PubProj();
        if (dataMap.get("projFlow") != null) {
            pubProj.setProjFlow(dataMap.get("projFlow")[0]);
        }
        if (dataMap.get("projYear") != null) {
            pubProj.setProjYear(dataMap.get("projYear")[0]);
        }
        if (dataMap.get("projName") != null) {
            pubProj.setProjName(dataMap.get("projName")[0]);
        }
        if (dataMap.get("projStartTime") != null) {
            pubProj.setProjStartTime(dataMap.get("projStartTime")[0]);
        }
        if (dataMap.get("projEndTime") != null) {
            pubProj.setProjEndTime(dataMap.get("projEndTime")[0]);
        }
        if (dataMap.get("projSerialNum") != null) {
            pubProj.setProjNo(dataMap.get("projSerialNum")[0]);
        }
        if (dataMap.get("projSubTypeId") != null) {
            pubProj.setProjSubTypeId(dataMap.get("projSubTypeId")[0]);
            pubProj.setProjSubTypeName(DictTypeEnum.WxeyProjType.getDictNameById(dataMap.get("projSubTypeId")[0]));
        }
        if (dataMap.get("jobNumber") != null) {
            pubProj.setApplyUserEmpnum(dataMap.get("jobNumber")[0]);
        }
        if (dataMap.get("branchId") != null) {
            pubProj.setBranchId(dataMap.get("branchId")[0]);
            pubProj.setBranchName(DictTypeEnum.WxeyBranch.getDictNameById(dataMap.get("branchId")[0]));
        }
        if (dataMap.get("projDeclarerFlow") != null) {
            pubProj.setProjDeclarerFlow(dataMap.get("projDeclarerFlow")[0]);
            pubProj.setProjDeclarer(DictTypeEnum.WxeyProjSource.getDictNameById(dataMap.get("projDeclarerFlow")[0]));
        }
        if (dataMap.get("applyDeptFlow") != null) {
            pubProj.setApplyDeptFlow(dataMap.get("applyDeptFlow")[0]);
            pubProj.setApplyDeptName(InitConfig.getDeptNameByFlow(dataMap.get("applyDeptFlow")[0]));
        }


        pubProj.setProjCategoryId(projCategoryId);
        pubProj.setProjCategoryName(ProjCategroyEnum.getNameById(projCategoryId));

        pubProj.setProjTypeId(projTypeId);
        /*List<SysDict> dicts = DictTypeEnum.sysListDictMap.get(DictTypeEnum.AidProjType.getId());
        for(SysDict dict:dicts){
            if(dict.getDictId().equals(projTypeId)){
                pubProj.setProjTypeName(dict.getDictName());
            }
        }*/

        return pubProj;
    }

    @RequestMapping("/edit")
    public String edit(@RequestParam(required = true) String typeId, @RequestParam(required = true) String projFlow, Model model) {
        List<SysDept> deptList = getDept();
        model.addAttribute("deptList", deptList);
        PubProj pubProj = this.projBiz.readProject(projFlow);
        model.addAttribute("pubProj", pubProj);
        String pageGroupName = InitConfig.configMap.get("Info").get(typeId);
        PageGroup pageGroup = InitConfig.projInfoPageMap.get(pageGroupName);
        Page page = pageGroup.getPageMap().get(pageGroup.getFirstPage());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = JspFormUtil.parseXmlStr(pubProj.getProjInfo(), page);
        model.addAttribute("resultMap", resultMap);
        //获取附件列表
        List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
        model.addAttribute("fileFlows", fileFlows);
        Map<String, PubFile> pageFileMap = this.projBiz.getFile(resultMap);
        if (pageFileMap != null) {
            model.addAttribute("pageFileMap", pageFileMap);
        }
        return page.getJsp();
    }

    private List<SysDept> getDept() {
        List<SysDept> deptList = new ArrayList<>();
        SysUser currUser = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(currUser.getOrgFlow())) {
            SysDept dept = new SysDept();
            dept.setOrgFlow(currUser.getOrgFlow());
            deptList = deptBiz.searchDept(dept);
        }
        return deptList;
    }

    @RequestMapping("/view")
    public String view(@RequestParam(required = true) String typeId, @RequestParam(required = true) String projFlow, String auditFlag, Model model) {
        model.addAttribute("auditFlag", auditFlag);
        PubProj pubProj = this.projBiz.readProject(projFlow);
        model.addAttribute("pubProj", pubProj);
        List<SysDept> deptList = getDept();
        model.addAttribute("deptList", deptList);
        String pageGroupName = InitConfig.configMap.get("Info").get(typeId);
        PageGroup pageGroup = InitConfig.projInfoPageMap.get(pageGroupName);
        Page page = pageGroup.getPageMap().get(pageGroup.getFirstPage());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = JspFormUtil.parseXmlStr(pubProj.getProjInfo(), page);
        model.addAttribute("resultMap", resultMap);
        //获取附件列表
        List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
        model.addAttribute("fileFlows", fileFlows);
        Map<String, PubFile> pageFileMap = this.projBiz.getFile(resultMap);
        SrmProjFundInfo fundInfo = new SrmProjFundInfo();
        fundInfo.setProjFlow(projFlow);
        List<SrmProjFundInfo> fundInfoList = fundInfoBiz.searchFundInfo(fundInfo);
        if (null != fundInfoList && fundInfoList.size() > 0) {
            model.addAttribute("fundInfo", fundInfoList.get(0));
        }

        if (pageFileMap != null) {
            model.addAttribute("pageFileMap", pageFileMap);
        }
        return pageGroup.getView();
    }

    private Page _getPage(String pageName, String typeId) {
        String pageGroupName = InitConfig.configMap.get("Info").get(typeId);
        PageGroup pageGroup = InitConfig.projInfoPageMap.get(pageGroupName);
        Page page = pageGroup.getPageMap().get(pageName);
        return page;
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public String delete(String projFlow) {
        PubProj pubProj = new PubProj();
        pubProj.setProjFlow(projFlow);
        pubProj.setRecordStatus(GlobalConstant.FLAG_N);
        projBiz.modProject(pubProj);
        return GlobalConstant.DELETE_SUCCESSED;
    }

    @RequestMapping(value = "/submitAudit", method = {RequestMethod.GET})
    @ResponseBody
    public String submitAudit(@RequestParam(value = "projFlow", required = true) String projFlow, Model model) {
        if (StringUtil.isNotBlank(projFlow)) {
            PubProj proj = projBiz.readProject(projFlow);
            proj.setProjStatusId(AidProjStatusEnum.NonAudit.getId());
            proj.setProjStatusName(AidProjStatusEnum.NonAudit.getName());
            //processBiz
            PubProjProcess process = new PubProjProcess();
            process.setProcessFlow(PkUtil.getUUID());
            process.setProjStageId(ProjStageEnum.Apply.getId());
            process.setProjStageName(ProjStageEnum.Apply.getName());
            process.setProjStatusId(AidProjStatusEnum.NonAudit.getId());
            process.setProjStatusName(AidProjStatusEnum.NonAudit.getName());
            process.setProcessRemark(AidProjStatusEnum.NonAudit.getName());
            process.setOperTime(process.getCreateTime());
            SysUser currUser = GlobalContext.getCurrentUser();
            process.setOperUserFlow(currUser.getUserFlow());
            process.setOperUserName(currUser.getUserName());
            process.setProjFlow(projFlow);
            processBiz.addProcess(process);
            projBiz.modProject(proj);
            return GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return "";
    }
    /**
     * 查看审核列表
     * @param projFlow
     * @return
     */
    @RequestMapping("/auditList")
    public String auditList(@RequestParam(value="projFlow" , required=true)String projFlow , Model model){
        PubProj proj =  this.projBiz.readProject(projFlow);
        model.addAttribute("proj", proj);
        PubProjProcess projProcess = new PubProjProcess();
        projProcess.setProjFlow(projFlow);
        List<PubProjProcess> projProcessList = this.processBiz.searchProjProcess(projProcess);
        model.addAttribute("projProcessList", projProcessList);
        return "srm/proj/mine/auditList";
    }
    /**
     * 加载登记项目列表
     *
     * @param currentPage
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/auditRegisProjList/{scope}")
    public String auditRegisProjList(@PathVariable String scope, PubProj pubProj, Integer currentPage, HttpServletRequest request, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        if (GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)) {//无锡二院科主任
            if (StringUtil.isNotBlank(currUser.getDeptFlow())) {
                pubProj.setApplyDeptFlow(currUser.getDeptFlow());
            } else {
                return "srm/proj/regis/auditRegisProjList";
            }
        }
        List<PubProj> projList = new ArrayList<>();
        if (StringUtil.isNotBlank(currUser.getOrgFlow())) {
            SysDept dept = new SysDept();
            dept.setOrgFlow(currUser.getOrgFlow());
            List<SysDept> deptList = deptBiz.searchDept(dept);
            model.addAttribute("deptList", deptList);
            pubProj.setApplyOrgFlow(currUser.getOrgFlow());
            pubProj.setProjTypeId("wxdermyy.xmdj");
            if (StringUtil.isBlank(pubProj.getProjStatusId())) {
                pubProj.setProjStatusId(AidProjStatusEnum.NonAudit.getId());
            }
            if (pubProj.getProjStatusId().equals("Y")) {
                pubProj.setProjStatusId("");
            }
            PageHelper.startPage(currentPage, getPageSize(request));
            projList = projBiz.queryProjList(pubProj);
        }
        model.addAttribute("projList", projList);
        return "srm/proj/regis/auditRegisProjList";
    }

    @RequestMapping(value = "/auditRegisProj")
    public String auditRegisProj(String projFlow, Model model) {
        List<SrmAchScore> srmAchScoreList = achScoreBiz.searchScoreSetList(new SrmAchScore());
        model.addAttribute("srmAchScoreList", srmAchScoreList);
        SysUser currUser = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(currUser.getOrgFlow())) {
            SysDept dept = new SysDept();
            dept.setOrgFlow(currUser.getOrgFlow());
            List<SysDept> deptList = deptBiz.searchDept(dept);
            model.addAttribute("deptList", deptList);
        }
        PubProj pubProj = this.projBiz.readProject(projFlow);
        model.addAttribute("pubProj", pubProj);
        String pageGroupName = InitConfig.configMap.get("Info").get("wxdermyy.xmdj");
        PageGroup pageGroup = InitConfig.projInfoPageMap.get(pageGroupName);
        Page page = pageGroup.getPageMap().get(pageGroup.getFirstPage());
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = JspFormUtil.parseXmlStr(pubProj.getProjInfo(), page);
        model.addAttribute("resultMap", resultMap);
        //获取附件列表
        List<String> fileFlows = this.projBiz.getFileFlows(resultMap);
        model.addAttribute("fileFlows", fileFlows);
        Map<String, PubFile> pageFileMap = this.projBiz.getFile(resultMap);
        if (pageFileMap != null) {
            model.addAttribute("pageFileMap", pageFileMap);
        }
        model.addAttribute("editFlag", "audit");
        return "srm/proj/form/regis/regis";
    }

    @RequestMapping("/remburseList")
    public String remburseList(Integer currentPage, HttpServletRequest request, PubProj pubProj, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        //pubProj.setProjCategoryId("ky");
        //pubProj.setApplyUserFlow(currUser.getUserFlow());
        // pubProj.setProjTypeId("wxdermyy.xmdj");
        //pubProj.setProjStatusId(AidProjStatusEnum.Pass.getId());
        Map<String, String> map = new HashMap<>();
        map.put("applyUserFlow", currUser.getUserFlow());
        map.put("projCategoryId", "ky");
        map.put("projTypeId", "wxdermyy.xmdj");
        map.put("projStatusId", AidProjStatusEnum.Pass.getId());
        createMap(map, pubProj);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<RegisProjFundExt> regisProjFundExtList = regisProjBiz.searchRegisProjFundExt(map);
        model.addAttribute("regisProjFundExtList", regisProjFundExtList);
        return "srm/proj/regis/fund/reimburseList";
    }

    /**
     * 报销明细
     *
     * @param fundFlow 经费流水号
     * @param model
     * @return
     */
    @RequestMapping(value = "/details/{scope}")
    public String details(@PathVariable String scope, String fundFlow, String projFlow, Model model) {
        model.addAttribute("scope", scope);
        if (StringUtil.isNotBlank(fundFlow)) {
            SrmProjFundDetail detail = new SrmProjFundDetail();
            detail.setFundFlow(fundFlow);
            List<SrmProjFundDetail> details = this.fundInfoDetailBiz.searchFundDetail(detail);
            model.addAttribute("details", details);
            PubProj proj = this.projBiz.readProject(projFlow);
            model.addAttribute("proj", proj);
            SrmProjFundInfo fundInfo = new SrmProjFundInfo();
            fundInfo.setProjFlow(projFlow);
            List<SrmProjFundInfo> fundInfoList = fundInfoBiz.searchFundInfo(fundInfo);
            if (null != fundInfoList && fundInfoList.size() > 0) {
                model.addAttribute("fundInfo", fundInfoList.get(0));
            }
            // model.addAttribute("fundFlow", fundFlow);
        }
        return "srm/proj/regis/fund/reimburseDetails";
    }


    @RequestMapping(value = "/auditDetail/{scope}")
    @ResponseBody
    public String auditDetail(@PathVariable String scope, String fundDetailFlow, String agreeFlag, String fundFlow) {
        SysUser currUser = GlobalContext.getCurrentUser();
        SrmProjFundDetail fundDetail = new SrmProjFundDetail();
        SrmFundProcess fundProcess = new SrmFundProcess();
        if (StringUtil.isNotBlank(fundDetailFlow) && StringUtil.isNotBlank(fundFlow)) {
            fundDetail.setFundDetailFlow(fundDetailFlow);
            if ("apply".equals(scope)) {
                fundDetail.setOperStatusId(AchStatusEnum.Submit.getId());
                fundDetail.setOperStatusName(AchStatusEnum.Submit.getName());
                fundProcess.setOperStatusId(AchStatusEnum.Submit.getId());
                fundProcess.setOperStatusName(AchStatusEnum.Submit.getName());
            }
            if ("audit".equals(scope)) {
                if (StringUtil.isNotBlank(agreeFlag)) {
                    if (GlobalConstant.FLAG_Y.equals(agreeFlag)) {
                        fundDetail.setOperStatusId(AchStatusEnum.Pass.getId());
                        fundDetail.setOperStatusName(AchStatusEnum.Pass.getName());
                        fundProcess.setOperStatusId(AchStatusEnum.Pass.getId());
                        fundProcess.setOperStatusName(AchStatusEnum.Pass.getName());
                    }
                    if (GlobalConstant.FLAG_N.equals(agreeFlag)) {
                        fundDetail.setOperStatusId(AchStatusEnum.RollBack.getId());
                        fundDetail.setOperStatusName(AchStatusEnum.RollBack.getName());
                        fundProcess.setOperStatusId(AchStatusEnum.RollBack.getId());
                        fundProcess.setOperStatusName(AchStatusEnum.RollBack.getName());
                    }
                }
            }
            fundProcess.setFundProcessFlow(PkUtil.getUUID());
            fundProcess.setFundFlow(fundFlow);
            GeneralMethod.setRecordInfo(fundProcess, true);
            fundProcess.setOperateTime(DateUtil.getCurrDateTime());
            //   fundProcess.setContent(content);
            fundProcess.setOperateUserFlow(currUser.getUserFlow());
            fundProcess.setOperateUserName(currUser.getUserName());
            regisProjBiz.updateDetailStatus(fundDetail, fundProcess);
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 报销
     *
     * @param fundDetail
     * @return
     */
    @RequestMapping(value = "/reimburse/{scope}")
    public String reimburse(@PathVariable String scope, SrmProjFundDetail fundDetail, String projFlow) {
        if (fundDetail.getMoney() != null) {
            BigDecimal bigDecimal = new BigDecimal(10000);
            fundDetail.setMoney(fundDetail.getMoney().divide(bigDecimal, 6, BigDecimal.ROUND_UP));
        }
        if ("apply".equals(scope)) {
            fundDetail.setOperStatusId(AchStatusEnum.Apply.getId());
            fundDetail.setOperStatusName(AchStatusEnum.Apply.getName());
            fundDetail.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
            fundBiz.editFundDetail(fundDetail);
        }
        if ("audit".equals(scope)) {
            if (StringUtil.isNotBlank(fundDetail.getFundDetailFlow())) {
                fundBiz.editFundDetail(fundDetail);
            }

        }
        //  return "redirect:/srm/payment/getDetailByFundFlow?fundFlow=" + fundDetail.getFundFlow();
        return "redirect:/srm/regis/proj/details/" + scope + "?fundFlow=" + fundDetail.getFundFlow() + "&projFlow=" + projFlow;
    }

    @RequestMapping("/auditRemburseList/{scope}")
    public String auditRemburseList(@PathVariable String scope, Integer currentPage, HttpServletRequest request, PubProj pubProj, Model model) {
        model.addAttribute("scope",scope);
        SysUser currUser = GlobalContext.getCurrentUser();
        if (StringUtil.isNotBlank(currUser.getOrgFlow())) {
            SysDept dept = new SysDept();
            dept.setOrgFlow(currUser.getOrgFlow());
            List<SysDept> deptList = deptBiz.searchDept(dept);
            model.addAttribute("deptList", deptList);
            Map<String, String> map = new HashMap<>();
            map.put("applyOrgFlow", currUser.getOrgFlow());
            map.put("projCategoryId", "ky");
            map.put("projTypeId", "wxdermyy.xmdj");
            map.put("projStatusId", AidProjStatusEnum.Pass.getId());
            map.put("detailStatusId", AchStatusEnum.Submit.getId());
            map.put("applyUserName", pubProj.getApplyUserName());
            map.put("applyUserFlow", pubProj.getApplyUserFlow());
            createMap(map, pubProj);
            if (GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)) {//无锡二院科主任
                if (StringUtil.isNotBlank(currUser.getDeptFlow())) {
                    map.put("applyDeptFlow", currUser.getDeptFlow());
                } else {
                    return "srm/proj/regis/fund/auditReimburseList";
                }
            }
            PageHelper.startPage(currentPage, getPageSize(request));
            List<RegisProjFundExt> regisProjFundExtList = regisProjBiz.searchRegisProjFundExt(map);
            model.addAttribute("regisProjFundExtList", regisProjFundExtList);
        }
        return "srm/proj/regis/fund/auditReimburseList";
    }

    private void createMap(Map<String, String> map, PubProj pubProj) {
        map.put("projName", pubProj.getProjName());
        map.put("projNo", pubProj.getProjNo());
        map.put("projYear", pubProj.getProjYear());
        map.put("projSubTypeId", pubProj.getProjSubTypeId());
        map.put("projDeclarerFlow", pubProj.getProjDeclarerFlow());
        map.put("applyDeptFlow", pubProj.getApplyDeptFlow());
        map.put("branchId", pubProj.getBranchId());
        map.put("applyUserEmpnum", pubProj.getApplyUserEmpnum());
    }

    @RequestMapping("reimburseIdChange")
    @ResponseBody
    public Map<String, Object> reimburseIdChange(String detailFlow) {
        Map<String, Object> map = new HashMap<>();
        if (StringUtil.isNotBlank(detailFlow)) {
            SrmProjFundDetail fundDetail = fundBiz.readSrmProjFundDetail(detailFlow);
            SrmProjFundDetail fundDetailTemp = new SrmProjFundDetail();
            fundDetailTemp.setReimburseId(detailFlow);
            fundDetailTemp.setFundFlow(fundDetail.getFundFlow());
            fundDetailTemp.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
            fundDetailTemp.setOperStatusId(AchStatusEnum.Pass.getId());
            List<SrmProjFundDetail> fundDetailList = fundInfoDetailBiz.searchFundDetail(fundDetailTemp);
            BigDecimal count = new BigDecimal(0);
            if (null != fundDetailList && fundDetailList.size() > 0) {
                for (SrmProjFundDetail detail : fundDetailList) {
                    BigDecimal reimburse = new BigDecimal("0");
                    if (detail.getMoney() != null) {
                        reimburse = detail.getMoney();
                    }
                    count = count.add(reimburse);
                }
            }
            map.put("budgetMoney", fundDetail.getMoney());
            map.put("reimburseMoney", count);
        }
        return map;
    }

    /**
     * 导出项目
     *
     * @param response
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/exportRegisProjList/{scope}", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportRegisProjList(@PathVariable String scope, PubProj pubProj, HttpServletResponse response) throws Exception {
        boolean exportControl = true;
        SysUser currUser = GlobalContext.getCurrentUser();
        List<WxeysrmProjExportForm> projExportFormList = new ArrayList<>();
        if (StringUtil.isNotBlank(currUser.getOrgFlow())) {
            if (GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL.equals(scope)) {
                pubProj.setApplyUserFlow(currUser.getUserFlow());
            }
            if (GlobalConstant.PROJ_STATUS_SCOPE_WXEY_DIRECTOR.equals(scope)) {//科主任
                if (StringUtil.isNotBlank(currUser.getDeptFlow())) {
                    pubProj.setApplyDeptFlow(currUser.getDeptFlow());
                } else {
                    exportControl = false;//如果当前登录用户没有科室 导出空表
                }
            }
            pubProj.setApplyOrgFlow(currUser.getOrgFlow());
            pubProj.setProjTypeId("wxdermyy.xmdj");
            if (StringUtil.isBlank(pubProj.getProjStatusId())) {
                pubProj.setProjStatusId(AidProjStatusEnum.NonAudit.getId());
            }
            if (pubProj.getProjStatusId().equals("Y")) {
                pubProj.setProjStatusId("");
            }
            WxeysrmProjExportForm projExportForm;
            List<PubProj> projList = new ArrayList<>();
            if (exportControl) {
                projList = projBiz.queryProjList(pubProj);
            }
            if (projList != null && !projList.isEmpty()) {
                for (PubProj ppr : projList) {
                    projExportForm = new WxeysrmProjExportForm();
                    projExportForm.setProjYear(StringUtil.isNotBlank(ppr.getProjYear()) ? ppr.getProjYear() : "");
                    projExportForm.setProjNo(StringUtil.isNotBlank(ppr.getProjNo()) ? ppr.getProjNo() : "");
                    projExportForm.setProjName(StringUtil.isNotBlank(ppr.getProjName()) ? ppr.getProjName() : "");
                    projExportForm.setApplyUserName(StringUtil.isNotBlank(ppr.getApplyUserName()) ? ppr.getApplyUserName() : "");
                    projExportForm.setApplyDeptName(StringUtil.isNotBlank(ppr.getApplyDeptName()) ? ppr.getApplyDeptName() : "");
                    projExportForm.setProjSubTypeName(StringUtil.isNotBlank(ppr.getProjSubTypeName()) ? ppr.getProjSubTypeName() : "");
                    projExportForm.setStartAndEndTime((StringUtil.isNotBlank(ppr.getProjStartTime()) ? ppr.getProjStartTime() : "") + "~" + (StringUtil.isNotBlank(ppr.getProjEndTime()) ? ppr.getProjEndTime() : ""));

                    String content = ppr.getProjInfo();
                    if (StringUtil.isNotBlank(content)) {
                        Document dom = DocumentHelper.parseText(content);
                        Element root = dom.getRootElement();
                        Element step1 = root.element("step1");
                        if (null != step1) {
                            Element fund_amount = (Element) step1.selectSingleNode("//item[@name='fund_amount']");
                            if (fund_amount != null) {
                                Element projFund = fund_amount.element("value");
                                if (null != projFund) {
                                    if (StringUtil.isNotBlank(projFund.getText())) {
                                        projExportForm.setProjFund(projFund.getText());
                                    }
                                }
                            }
                            Element projRemark = (Element) step1.selectSingleNode("//item[@name='projRemark']");
                            if (projRemark != null) {
                                Element remark = projRemark.element("value");
                                if (null != remark) {
                                    if (StringUtil.isNotBlank(remark.getText())) {
                                        projExportForm.setRemark(remark.getText());
                                    }
                                }
                            }
                        }
                    }
                    projExportFormList.add(projExportForm);
                }
            }
        }
        String[] titles = new String[]{
                "projYear:年度",
                "projNo:项目编号",
                "projName:项目名称",
                "applyUserName:项目负责人",
                "applyDeptName:科室",
                "projSubTypeName:项目类别",
                "projFund:项目经费",
                "startAndEndTime:起止时间",
                "remark:备注"
        };

        ExcleUtile.exportSimpleExcleByObjs(titles, projExportFormList, response.getOutputStream());
        String fileName = "登记项目信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }


    /**
     * 导出报销
     *
     * @param response
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "/exportRegisFundList", method = {RequestMethod.POST, RequestMethod.GET})
    public void exportRegisFundList(PubProj pubProj, HttpServletResponse response) throws Exception {
        SysUser currUser = GlobalContext.getCurrentUser();
        List<RegisProjFundExt> regisProjFundExtList = new ArrayList<>();
        if (StringUtil.isNotBlank(currUser.getOrgFlow())) {
            Map<String, String> map = new HashMap<>();
            map.put("applyOrgFlow", pubProj.getApplyOrgFlow());
            map.put("projCategoryId", "ky");
            map.put("projTypeId", "wxdermyy.xmdj");
            map.put("projStatusId", AidProjStatusEnum.Pass.getId());
            map.put("detailStatusId", AchStatusEnum.Submit.getId());
            map.put("applyUserName", pubProj.getApplyUserName());
            map.put("applyUserFlow", pubProj.getApplyUserFlow());
            createMap(map, pubProj);
            // pubProj.setProjCategoryId("ky");
            //pubProj.setProjTypeId("wxdermyy.xmdj");
            // pubProj.setProjStatusId(AidProjStatusEnum.Pass.getId());
            regisProjFundExtList = regisProjBiz.searchRegisProjFundExt(map);
        }
        String[] titles = new String[]{
                "projYear:年度",
                "projNo:项目编号",
                "projName:项目名称",
                "projSubTypeName:项目类别",
                "projFundInfo.amountFund:项目奖金（元）",
                "applyUserName:项目签名",
                "applyDeptName:科室",
                "branchName:支部"
        };

        ExcleUtile.exportSimpleExcleByObjs(titles, regisProjFundExtList, response.getOutputStream());
        String fileName = "报销信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    @RequestMapping("/addRealityAmount")
    public String addRealityAmount(String projFlow, Model model) {
        List<SrmAchScore> srmAchScoreList = achScoreBiz.searchScoreSetList(new SrmAchScore());
        model.addAttribute("srmAchScoreList", srmAchScoreList);
        SrmProjFundInfo fundInfo = new SrmProjFundInfo();
        fundInfo.setProjFlow(projFlow);
        PubProj pubProj = projBiz.readProject(projFlow);
        model.addAttribute("pubProj", pubProj);
        List<SrmProjFundInfo> fundInfoList = fundInfoBiz.searchFundInfo(fundInfo);
        if (null != fundInfoList && fundInfoList.size() > 0) {
            model.addAttribute("fundInfo", fundInfoList.get(0));
        }
        return "srm/proj/form/regis/realityAmountFund";
    }

    @RequestMapping("/saveRealityAmount")
    @ResponseBody
    public String saveRealityAmount(SrmProjFundInfo fundInfo, PubProjAuthor projAuthor) {
        if (StringUtil.isNotBlank(fundInfo.getFundFlow())) {
            if (projAuthor.getScoreFlow() != null) {
                SrmAchScore score = new SrmAchScore();
                score.setScoreFlow(projAuthor.getScoreFlow());
                List<SrmAchScore> list = achScoreBiz.searchScoreSetList(score);
                projAuthor.setScoreFlow(list.get(0).getScoreFlow());
                projAuthor.setScoreName(list.get(0).getScoreName());
                projAuthor.setAchScore(list.get(0).getScorePersonalValue().add(new BigDecimal(0)));
                projAuthor.setAchScoreDept(list.get(0).getScoreDeptValue().add(new BigDecimal(0)));
            }
            regisProjBiz.saveRealityAmount(fundInfo, projAuthor);
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    @RequestMapping(value = "/exportDetails")
    public void exportDetails(String fundFlow, String projFlow, HttpServletResponse response, OutputStream os) throws IOException {
        if (StringUtil.isNotBlank(fundFlow) && StringUtil.isNotBlank(projFlow)) {
            SrmProjFundDetail detail = new SrmProjFundDetail();
            detail.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
            detail.setOperStatusId(AchStatusEnum.Pass.getId());
            detail.setFundFlow(fundFlow);
            List<SrmProjFundDetail> details = this.fundInfoDetailBiz.searchFundDetail(detail);
            PubProj proj = this.projBiz.readProject(projFlow);
            SrmProjFundInfo fundInfo = new SrmProjFundInfo();
            fundInfo.setProjFlow(projFlow);
            List<SrmProjFundInfo> fundInfoList = fundInfoBiz.searchFundInfo(fundInfo);
            if (null != fundInfoList && fundInfoList.size() > 0) {
                fundInfo = fundInfoList.get(0);
            }
            // model.addAttribute("fundFlow", fundFlow);
            HSSFWorkbook wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            HSSFSheet sheet = wb.createSheet("sheet1");
            CellRangeAddress cra1 = new CellRangeAddress(0, 0, 0, 4);//合并第1行1-5列单元格
            CellRangeAddress cra2 = new CellRangeAddress(1, 1, 1, 4);//合并第2行2-5列单元格
            CellRangeAddress cra3 = new CellRangeAddress(2, 2, 1, 4);//合并第3行2-5列单元格
            CellRangeAddress cra4 = new CellRangeAddress(3, 3, 0, 4);//合并第4行1-5列单元格
            sheet.addMergedRegion(cra1);
            sheet.addMergedRegion(cra2);
            sheet.addMergedRegion(cra3);
            sheet.addMergedRegion(cra4);
            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            HSSFRow row = sheet.createRow((int) 0);
            // 第四步，创建单元格，并设置值表头 设置表头居中
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式
            HSSFCell cell = null;
            cell = row.createCell(0); //第一行
            cell.setCellValue("到帐记录");
            cell.setCellStyle(style);

            row = sheet.createRow((int) 1);  //第二行
            row.createCell(0).setCellValue("到帐时间");
            cell = row.createCell(1);
            cell.setCellValue(fundInfo.getFundIncomeTime());
            cell.setCellStyle(style);

            row = sheet.createRow((int) 2);//第三行
            row.createCell(0).setCellValue("到帐经费");
            cell = row.createCell(1);
            cell.setCellValue(fundInfo.getRealityAmount().toString());
            cell.setCellStyle(style);

            row = sheet.createRow((int) 3);//第四行
            cell = row.createCell(0);
            cell.setCellValue("使用记录");
            cell.setCellStyle(style);

            row = sheet.createRow((int) 4); //第五行
            row.createCell(0).setCellValue("登记时间");
            row.createCell(1).setCellValue("项目类别");
            row.createCell(2).setCellValue("项目明细");
            row.createCell(3).setCellValue("项目金额");
            row.createCell(4).setCellValue("项目签名");
            if (details != null) {
                BigDecimal bigDecimal = new BigDecimal(10000);
                for (int i = 0; i < details.size(); i++) {
                    SrmProjFundDetail fundDetail = details.get(i);
                    row = sheet.createRow(i + 5);
                    row.createCell(0).setCellValue(DateUtil.transDate(fundDetail.getCreateTime()));
                    row.createCell(1).setCellValue(fundDetail.getReimburseName());
                    row.createCell(2).setCellValue(fundDetail.getContent());
                    row.createCell(3).setCellValue(fundDetail.getMoney().multiply(bigDecimal).toString() + "（元）");//单位装换（万元--->元）
                    row.createCell(4).setCellValue(proj.getApplyUserName());
                }
            }
            wb.write(os);
        }
        String fileName = "报销信息.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
