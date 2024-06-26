package com.pinde.sci.ctrl.srm;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.ProjFundTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.srm.FundDetailExtForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.FundDetailAndSchemeExt;
import com.pinde.sci.model.srm.PubProjExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/srm/fund/scheme")
public class FundSchemeController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(FundSchemeController.class);
    @Autowired
    private IFundSchemeBiz schemeBiz;
    @Autowired
    private IFundSchemeDetailBiz detailBiz;
    @Autowired
    private IProjMineBiz projMineBiz;
    @Autowired
    private IFundInfoBiz fundInfoBiz;
    @Autowired
    private IFundInfoDetailBiz fundInfoDetailBiz;
    @Autowired
    private IPubProjBiz pubProjBiz;
    @Autowired
    private IFundProcessBiz processBiz;

    /**
     * 跳转到经费方案列表页面
     *
     * @return
     */
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    public String list(SrmProjSourceScheme sourceScheme,Integer currentPage, Model model,HttpServletRequest request) {
        Map<String,List<SrmProjSourceScheme>> map = new HashMap<>();
        List<SrmProjSourceScheme> sourceSchemeList = schemeBiz.searchSourceScheme(sourceScheme);
        List<String> schemeFlowList = new ArrayList<>();
        for(SrmProjSourceScheme srmProjSourceScheme : sourceSchemeList){
            if(map.containsKey(srmProjSourceScheme.getSchemeFlow())){
                map.get(srmProjSourceScheme.getSchemeFlow()).add(srmProjSourceScheme);
            }else{
                schemeFlowList.add(srmProjSourceScheme.getSchemeFlow());
                List<SrmProjSourceScheme> sourceSchemeList2 = new ArrayList<>();
                sourceSchemeList2.add(srmProjSourceScheme);
                map.put(srmProjSourceScheme.getSchemeFlow(),sourceSchemeList2);
            }
        }
        if(StringUtil.isBlank(sourceScheme.getProjFirstSourceId())){
            schemeFlowList.removeAll(schemeFlowList);
        }
        SrmFundScheme scheme = new SrmFundScheme();
        scheme.setSchemeName(sourceScheme.getSchemeName());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<SrmFundScheme> schemeList = schemeBiz.searchFundSchemeByFlows(scheme,schemeFlowList);
        model.addAttribute("schemeList", schemeList);

//        sourceScheme.setSchemeFlow();

        model.addAttribute("map",map);
        return "srm/fund/scheme/list";
    }

    /**
     * 添加经费方案信息界面
     *
     * @return
     */
    @RequestMapping(value = "/editProjSource", method = {RequestMethod.POST, RequestMethod.GET})
    public String editProjSource(Model model, String schemeFlow) {
        if (StringUtil.isNotBlank(schemeFlow)) {
            SrmFundScheme srmFundScheme = schemeBiz.readFundScheme(schemeFlow);
            model.addAttribute("srmFundScheme", srmFundScheme);
            SrmProjSourceScheme projSourceScheme = new SrmProjSourceScheme();
            projSourceScheme.setSchemeFlow(schemeFlow);
            List<SrmProjSourceScheme> sourceSchemeList = schemeBiz.searchSourceScheme(projSourceScheme);
            model.addAttribute("sourceSchemeList", sourceSchemeList);
        }
        return "srm/fund/scheme/editProjSource";
    }

    /**
     * 保存经费方案信息
     *
     * @param scheme
     * @return
     */
    @RequestMapping(value = "/saveProjSourceScheme", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String saveProjSourceScheme(String schemeFlow,@RequestBody List<SrmProjSourceScheme> projSourceSchemes) {
        int result = 0;
        if(StringUtil.isNotBlank(schemeFlow)){
            result = schemeBiz.saveSourceScheme(projSourceSchemes,schemeFlow);
        }
        if(result > 0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    /**
     * 跳转到经费项列表
     *
     * @return
     */
    @RequestMapping(value = "/itemList", method = {RequestMethod.POST, RequestMethod.GET})
    public String list2(String schemeFlow, Model model) {
        SrmFundSchemeDetail schemeDtl = new SrmFundSchemeDetail();
        schemeDtl.setSchemeFlow(schemeFlow);
        schemeDtl.setRecordStatus(GlobalConstant.FLAG_Y);
        if (StringUtil.isNotBlank(schemeFlow)) {
            List<SrmFundSchemeDetail> schemeDtlList = detailBiz.searchFundSchemeDetail(schemeDtl);
            Map<String, SrmFundSchemeDetail> schemeDetailMap = new HashMap<>();
            for (SrmFundSchemeDetail schemeDetail : schemeDtlList) {
                if (StringUtil.isNotBlank(schemeDetail.getItemPid())) {
                    schemeDetailMap.put(schemeDetail.getItemPid() + schemeDetail.getItemId(), schemeDetail);
                } else {
                    schemeDetailMap.put(schemeDetail.getItemId(), schemeDetail);
                }
            }

            model.addAttribute("schemeDetailMap", schemeDetailMap);
        }
        model.addAttribute("schemeFlow", schemeFlow);
        return "srm/fund/scheme/itemList";
    }

    /**
     * 添加经费方案信息界面
     *
     * @return
     */
    @RequestMapping(value = "/edit", method = {RequestMethod.POST, RequestMethod.GET})
    public String editItem(Model model, String schemeFlow) {
        SrmFundScheme scheme = new SrmFundScheme();
        List<SrmFundScheme> schemeList = schemeBiz.searchFundScheme(scheme);
        List<SysDict> usableProjTypeList = new ArrayList<SysDict>();
        List<SysDict> projTypeList = DictTypeEnum.sysListDictMap.get(DictTypeEnum.ProjType.getId());
        for (SysDict dict : projTypeList) {
            boolean usable = true;
            for (SrmFundScheme existScheme : schemeList) {
                if (dict.getDictId().equals(existScheme.getProjTypeId())) {
                    usable = false;
                }
            }
            if (usable) {
                usableProjTypeList.add(dict);
            }
        }
        if (StringUtil.isNotBlank(schemeFlow)) {
            SrmFundScheme srmFundScheme = schemeBiz.readFundScheme(schemeFlow);
            model.addAttribute("srmFundScheme", srmFundScheme);
        }
        model.addAttribute("existProjTypeIds", usableProjTypeList);
        return "srm/fund/scheme/edit";
    }

    /**
     * 保存经费方案信息
     *
     * @param scheme
     * @return
     */
    @RequestMapping(value = "/saveScheme", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String saveScheme(SrmFundScheme scheme) {
        scheme.setProjTypeName(DictTypeEnum.ProjType.getDictNameById(scheme.getProjTypeId()));
        if ("jsszyy.kyxm".equals(scheme.getProjTypeId())) {
            scheme.setProjTypeName("江苏省中医院科研项目");
        }
        schemeBiz.saveFundScheme(scheme);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 修改经费方案信息
     *
     * @param scheme
     * @return
     */
    @RequestMapping(value = "/updateScheme", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String updateScheme(SrmFundScheme scheme) {

        scheme.setProjTypeName(DictTypeEnum.ProjType.getDictNameById(scheme.getProjTypeId()));
        scheme.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        schemeBiz.saveFundScheme(scheme);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping(value = "/updateDetail", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String updateDetail(@RequestBody List<SrmFundSchemeDetail> schemeDtlList) {
        if (null != schemeDtlList && schemeDtlList.size() > 0) {
            SrmFundScheme scheme = schemeBiz.readFundScheme(schemeDtlList.get(0).getSchemeFlow());
            SrmFundSchemeDetail schemeDetail = new SrmFundSchemeDetail();
            schemeDetail.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            schemeDetail.setSchemeFlow(scheme.getSchemeFlow());
            detailBiz.updateFundSchemeDetail(schemeDetail);
            List<String> itemFlowList = new ArrayList<>();
            for (int i = 0; i < schemeDtlList.size(); i++) {
                SrmFundSchemeDetail schemeDtl = schemeDtlList.get(i);
                schemeDtl.setSchemeName(scheme.getSchemeName());
                detailBiz.saveFundSchemeDetail(schemeDtl);
                itemFlowList.add(schemeDtl.getItemFlow());
            }
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 启用经费方案
     *
     * @param schemeFlow
     * @return
     */
    @RequestMapping(value = "/startScheme", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String startScheme(String schemeFlow) {
        SrmFundScheme scheme = schemeBiz.readFundScheme(schemeFlow);
        List<SrmFundScheme> schemeList = schemeBiz.searchStartScheme(scheme.getProjTypeId());
        if (schemeList.size() > 0) {
            return GlobalConstant.NOT_START;
        }
        scheme.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        schemeBiz.deleteFundScheme(scheme);
        return GlobalConstant.OPERATE_SUCCESSED;
    }


    /**
     * 停用经费方案
     *
     * @param schemeFlow
     * @return
     */
    @RequestMapping(value = "/stopScheme", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String stopScheme(String schemeFlow) {
        SrmFundScheme scheme = schemeBiz.readFundScheme(schemeFlow);
        scheme.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        schemeBiz.deleteFundScheme(scheme);
        return GlobalConstant.OPERATE_SUCCESSED;
    }


    /**
     * 申请预算列表--项目负责人适用
     *
     * @param proj
     * @param model
     * @return
     */
    @RequestMapping(value = "/applyList/{projCateScope}", method = {RequestMethod.POST, RequestMethod.GET})
    public String applyList(@PathVariable String projCateScope, PubProj proj, Integer currentPage, HttpServletRequest request, Model model) {
        setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
        setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL);
        SysUser currUser = GlobalContext.getCurrentUser();
        proj.setApplyUserFlow(currUser.getUserFlow());
        proj.setProjCategoryId(projCateScope);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<PubProj> projList = projMineBiz.searchProjListForBudget(proj);
        HashMap<String, SrmProjFundInfo> map = new HashMap<String, SrmProjFundInfo>();
        for (int i = 0; i < projList.size(); i++) {
            SrmProjFundInfo fundInfo = new SrmProjFundInfo();
            fundInfo.setProjFlow(projList.get(i).getProjFlow());
            List<SrmProjFundInfo> projFundInfoList = fundInfoBiz.searchFundInfo(fundInfo);
            if (projFundInfoList.size() == 1) {
                map.put(fundInfo.getProjFlow(), projFundInfoList.get(0));
            }
        }
//        SrmFundScheme scheme = new SrmFundScheme();
//        List<SrmFundScheme> schemeList = schemeBiz.searchFundScheme(scheme);
//        model.addAttribute("schemeList", schemeList);
        model.addAttribute("fundMap", map);
        model.addAttribute("projList", projList);
        return "srm/fund/scheme/applyList";
    }

    @RequestMapping(value = "/applyEdit", method = {RequestMethod.POST, RequestMethod.GET})
    public String applyEdit(String projFlow, Model model, String look) {
        //查询该项目是否已申请预算，如果已申请，查出该预算方案及其预算项信息
        model.addAttribute("projFlow", projFlow);
        SrmProjFundInfo fundInfo = new SrmProjFundInfo();
        fundInfo.setProjFlow(projFlow);
        List<SrmProjFundInfo> fundInfoList = fundInfoBiz.searchFundInfo(fundInfo);
        PubProj proj = pubProjBiz.readProject(projFlow);
        SrmFundSchemeDetail schemeDtl = new SrmFundSchemeDetail();
        if (fundInfoList.size() > 0) {
            SrmProjFundInfo fundInfoTemp = fundInfoList.get(0);
            model.addAttribute("fundInfo", fundInfoTemp);
            if (fundInfoTemp.getBudgetAmount() != null && !fundInfoTemp.getBudgetAmount().equals("")) {
                String schemeFlow = fundInfoList.get(0).getSchemeFlow();
                SrmFundScheme scheme = null;
                if (StringUtil.isNotBlank(schemeFlow)) {
                    //查询该项目经费的预算方案信息及其预算项信息
                   scheme = schemeBiz.readFundScheme(schemeFlow);
                }else{
                    SrmFundScheme fundScheme = new SrmFundScheme();
                    fundScheme.setProjTypeId(proj.getProjTypeId());
                    List<SrmFundScheme> schemeList= schemeBiz.searchFundScheme(fundScheme);
                    if(schemeList != null && schemeList.size() > 0){
                        scheme = schemeList.get(0);
                    }
                }
                if(scheme != null){
                    model.addAttribute("scheme", scheme);
                    schemeDtl.setSchemeFlow(scheme.getSchemeFlow());
                    List<SrmFundSchemeDetail> schemeDtlList = detailBiz.searchFundSchemeDetailSee(schemeDtl);
                    model.addAttribute("schemeDtlList", schemeDtlList);
                }
            }
            //先从经费表详细中查经费明细，如果空则从预算项明细中查
            SrmProjFundDetail fundDtl = new SrmProjFundDetail();
            fundDtl.setFundFlow(fundInfoTemp.getFundFlow());
            fundDtl.setFundTypeId(ProjFundTypeEnum.Budget.getId());
            List<SrmProjFundDetail> fundDtlList = fundInfoDetailBiz.searchFundDetail(fundDtl);
            if (fundDtlList.size() > 0) {
//                model.addAttribute("fundDtlList", fundDtlList);
                HashMap<String, SrmProjFundDetail> map = new HashMap<String, SrmProjFundDetail>();
                if (fundDtlList.size() > 0) {
                    for (SrmProjFundDetail fundDetail:fundDtlList) {
                        if(StringUtil.isNotBlank(fundDetail.getItemPid())) {
                            map.put(fundDetail.getItemPid() + fundDetail.getItemId(), fundDetail);
                        }else {
                            map.put(fundDetail.getItemId(), fundDetail);
                        }
                    }
                    model.addAttribute("fundDetailMap", map);
                }
            }
        }

        model.addAttribute("proj", proj);
        model.addAttribute("look", look);
        return "srm/fund/scheme/applyEdit";
    }

    @RequestMapping(value = "/changeScheme", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public Object changeScheme(String schemeFlow, Model model) {
        //根据预算方案号查询预算
        SrmFundSchemeDetail schemeDtl = new SrmFundSchemeDetail();
        schemeDtl.setSchemeFlow(schemeFlow);
        List<SrmFundSchemeDetail> schemeDtlList = detailBiz.searchFundSchemeDetailSee(schemeDtl);
        return schemeDtlList;
    }

    @RequestMapping(value = "/saveFundInfoDetail", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String saveFundInfoDetail(@RequestBody List<SrmProjFundDetail> fundDtlList, SrmProjFundInfo fundInfo) {
        if (fundInfo != null) {
            //创建经费信息，设置相关属性
            PubProj proj = pubProjBiz.readProject(fundInfo.getProjFlow());
            fundInfo.setProjName(proj.getProjName());
            fundInfo.setBudgetStatusId(AchStatusEnum.Submit.getId());
            fundInfo.setBudgetStatusName(AchStatusEnum.Submit.getName());
//            BigDecimal budgetAllValue = new BigDecimal("0.0000");
//            for (int i = 0; i < fundDtlList.size(); i++) {
//                budgetAllValue = budgetAllValue.add(fundDtlList.get(i).getMoney());
//            }

//            fundInfo.setBudgetAmount(budgetAllValue);
            //创建过程信息
            SrmFundProcess process = new SrmFundProcess();
            SysUser currUser = GlobalContext.getCurrentUser();
            process.setOperateUserFlow(currUser.getUserFlow());
            process.setOperateUserName(currUser.getUserName());
            process.setOperStatusId(AchStatusEnum.Submit.getId());
            process.setOperStatusName(AchStatusEnum.Submit.getName());
            fundInfoBiz.saveFundInfo(fundInfo, fundDtlList, process);
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;

    }


    @RequestMapping(value = "/auditList/{projListScope}/{projCateScope}", method = {RequestMethod.POST, RequestMethod.GET})
    public String auditList(@PathVariable String projListScope, @PathVariable String projCateScope,String projFlow, String applyDeptName,String applyDeptFlow,PubProjExt projExt, SrmProjFundInfo fundInfo, Model model, Integer currentPage ,HttpServletRequest request) {
        setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, projCateScope);
        SysUser currUser = GlobalContext.getCurrentUser();
        SrmFundScheme scheme = new SrmFundScheme();
        List<SrmFundScheme> schemeList = schemeBiz.searchFundScheme(scheme);
        model.addAttribute("scope", projListScope);
        if (GlobalConstant.PROJ_STATUS_SCOPE_GLOBAL.equals(projListScope)) {
            return "srm/fund/scheme/auditListGlobal";
        } else if (GlobalConstant.PROJ_STATUS_SCOPE_CHARGE.equals(projListScope)) {
            return "srm/fund/scheme/auditListCharge";
        } else {
            projExt.setApplyOrgFlow(currUser.getOrgFlow());
           /* List<String> statuIds = new ArrayList<String>();
            if (null != isAudit && isAudit) {
                statuIds.add(AchStatusEnum.Submit.getId());
            } else {
                statuIds.add(AchStatusEnum.Submit.getId());
                statuIds.add(AchStatusEnum.FirstAudit.getId());
            }
            projExt.setStatusids(statuIds);*/

            projExt.setProjFundInfo(fundInfo);
            projExt.setProjFlow(projFlow);
            projExt.setApplyDeptName(applyDeptName);
            projExt.setApplyDeptFlow(applyDeptFlow);
            projExt.setProjCategoryId(ProjCategroyEnum.Ky.getId());
            if(ProjCategroyEnum.Gl.getId().equals(projCateScope)){
                projExt.setProjCategoryId(ProjCategroyEnum.Gl.getId());
            }
            PageHelper.startPage(currentPage, getPageSize(request));
            List<PubProjExt> fundInfoList = this.fundInfoBiz.searchBudgetAuditList(projExt);
            model.addAttribute("schemeList", schemeList);
            model.addAttribute("fundInfoList", fundInfoList);
            return "srm/fund/scheme/auditListLocal";
        }

    }

    @RequestMapping(value = "/audit", method = {RequestMethod.POST, RequestMethod.GET})
    public String audit(String fundFlow, Model model) {
        //查询经费下所有明细
        SrmProjFundDetail fundDtl = new SrmProjFundDetail();
        fundDtl.setFundFlow(fundFlow);
        fundDtl.setFundTypeId(ProjFundTypeEnum.Budget.getId());
        List<SrmProjFundDetail> fundDtlList = fundInfoDetailBiz.searchFundDetail(fundDtl);
        SrmProjFundInfo fundInfo = this.fundInfoBiz.getFundInfoByFlow(fundFlow);
        PubProj proj = pubProjBiz.readProject(fundInfo.getProjFlow());
//        if (StringUtil.isNotBlank(fundInfo.getSchemeFlow())) {

            SrmFundScheme scheme = null;
            if (StringUtil.isNotBlank(fundInfo.getSchemeFlow())) {
                //查询该项目经费的预算方案信息及其预算项信息
                scheme = schemeBiz.readFundScheme(fundInfo.getSchemeFlow());
            }else{
                SrmFundScheme fundScheme = new SrmFundScheme();
                fundScheme.setProjTypeId(proj.getProjTypeId());
                List<SrmFundScheme> schemeList= schemeBiz.searchFundScheme(fundScheme);
                if(schemeList != null && schemeList.size() > 0){
                    scheme = schemeList.get(0);
                }
            }
            if(scheme != null){
                model.addAttribute("scheme", scheme);
                SrmFundSchemeDetail schemeDtl = new SrmFundSchemeDetail();
                schemeDtl.setSchemeFlow(scheme.getSchemeFlow());
                List<SrmFundSchemeDetail> schemeDtlList = detailBiz.searchFundSchemeDetailSee(schemeDtl);
                model.addAttribute("schemeDtlList", schemeDtlList);
            }
//            schemeDtl.setSchemeFlow(fundInfo.getSchemeFlow());
//            List<SrmFundSchemeDetail> schemeDtlList = detailBiz.searchFundSchemeDetailSee(schemeDtl);
//            model.addAttribute("schemeDtlList", schemeDtlList);
//        }
        HashMap<String, SrmProjFundDetail> map = new HashMap<String, SrmProjFundDetail>();
        if (fundDtlList.size() > 0) {
            for (SrmProjFundDetail fundDetail:fundDtlList) {
                if(StringUtil.isNotBlank(fundDetail.getItemPid())) {
                    map.put(fundDetail.getItemPid() + fundDetail.getItemId(), fundDetail);
                }else {
                    map.put(fundDetail.getItemId(), fundDetail);
                }
            }
            model.addAttribute("fundDetailMap", map);
        }
        /*查询管理员保存记录*/
        SrmFundProcess process = new SrmFundProcess();
        process.setFundFlow(fundFlow);
        process.setOperStatusId(AchStatusEnum.Save.getId());
        process = processBiz.readFundProcess(process);
        model.addAttribute("process", process);
       // model.addAttribute("fundFlow", fundFlow);
        model.addAttribute("fundInfo", fundInfo);
        return "srm/fund/scheme/audit";
    }

    @RequestMapping(value = "/saveAudit", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String saveAudit(@RequestBody FundDetailExtForm fundDetailExtForm, String agreeFlag) {
        SysUser currUser = GlobalContext.getCurrentUser();

        SrmProjFundInfo fundInfo = fundDetailExtForm.getFundInfo();
        List<SrmProjFundDetail> fundDtlList = fundDetailExtForm.getFundDetailList();
        if(StringUtil.isNotBlank(fundInfo.getFundFlow())) {
            this.fundInfoDetailBiz.updateRecordStatusByFundFlow(fundInfo.getFundFlow(), GlobalConstant.RECORD_STATUS_N);
        }
        for (SrmProjFundDetail fundDetail : fundDtlList) {
            if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
                fundDetail.setOperStatusId(AchStatusEnum.FirstAudit.getId());
                fundDetail.setOperStatusName(AchStatusEnum.FirstAudit.getName());
            } else if(agreeFlag.equals(GlobalConstant.FLAG_N)){
                fundDetail.setOperStatusId(AchStatusEnum.FirstBack.getId());
                fundDetail.setOperStatusName(AchStatusEnum.FirstBack.getName());
            }
            if(StringUtil.isNotBlank(fundDetail.getFundDetailFlow())){
                fundDetail.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                GeneralMethod.setRecordInfo(fundDetail, false);
                fundInfoDetailBiz.updateFundDetail(fundDetail);
            }else{
                fundDetail.setFundTypeId(ProjFundTypeEnum.Budget.getId());
                fundDetail.setFundTypeName(ProjFundTypeEnum.Budget.getName());
                fundDetail.setFundFlow(fundInfo.getFundFlow());
                fundDetail.setFundDetailFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(fundDetail, true);
                fundInfoDetailBiz.saveFundDetail(fundDetail);
            }
        }

        SrmFundProcess readProcess = fundDetailExtForm.getProcess();
       // readProcess.setContent(content);
        if (StringUtil.isNotBlank(fundInfo.getFundFlow())) {
            readProcess.setFundFlow(fundInfo.getFundFlow());
            readProcess.setOperateTime(DateUtil.getCurrDateTime());
          //  SrmProjFundInfo projFundInfo = fundInfoList.get(0);
            if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
                fundInfo.setBudgetStatusId(AchStatusEnum.FirstAudit.getId());
                fundInfo.setBudgetStatusName(AchStatusEnum.FirstAudit.getName());
                readProcess.setOperStatusId(AchStatusEnum.FirstAudit.getId());
                readProcess.setOperStatusName(AchStatusEnum.FirstAudit.getName());
            } else if(agreeFlag.equals(GlobalConstant.FLAG_N)) {
                fundInfo.setBudgetStatusId(AchStatusEnum.FirstBack.getId());
                fundInfo.setBudgetStatusName(AchStatusEnum.FirstBack.getName());
                readProcess.setOperStatusId(AchStatusEnum.FirstBack.getId());
                readProcess.setOperStatusName(AchStatusEnum.FirstBack.getName());
            }else{
                readProcess.setOperStatusId(AchStatusEnum.Save.getId());
                readProcess.setOperStatusName(AchStatusEnum.Save.getName());
            }
            readProcess.setOperateUserFlow(currUser.getUserFlow());
            readProcess.setOperateUserName(currUser.getUserName());
            fundInfoBiz.budgetAudit(fundInfo, readProcess);
        }

        return GlobalConstant.OPERATE_SUCCESSED;
    }
    @RequestMapping(value = "/detailBudgetJson", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String detailBudgetJson(String fundFlow, Model model) {
            SrmProjFundDetail fundDtl = new SrmProjFundDetail();
            fundDtl.setFundFlow(fundFlow);
            fundDtl.setFundTypeId(ProjFundTypeEnum.Budget.getId());
            List<SrmProjFundDetail> fundDtlList = fundInfoDetailBiz.searchFundDetail(fundDtl);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        //  sb.append("{\"id\":0, \"pId\":-1, \"name\":\"学科代码\",\"open\":true,\"t\":\"根节点\"},");

        for(SrmProjFundDetail detail: fundDtlList){
            if(StringUtil.isBlank(detail.getItemName())){//老数据
                SrmFundSchemeDetail schemeDtl = detailBiz.read(detail.getItemFlow());
                detail.setItemName(schemeDtl.getItemName());
                detail.setItemId(schemeDtl.getItemId());
                detail.setItemPid(schemeDtl.getItemPid());
            }
            if(detail.getMoney() != null && detail.getMoney().compareTo(new BigDecimal(0)) > 0) {
                sb.append("{");
                sb.append("\"id\":").append("\"" + detail.getItemId() + "\"").append(",");
                sb.append("\"pId\":").append(StringUtil.isNotBlank(detail.getItemPid()) ? "\"" + detail.getItemPid() + "\"" : 0).append(",");
                sb.append("\"name\":").append("\"").append(detail.getItemName()).append("\",");
                sb.append("\"detailFlow\":").append("\"").append(detail.getFundDetailFlow()).append("\",");
                sb.append("\"allocateMoney\":").append("\"").append(detail.getAllocateMoney()).append("\",");
                sb.append("\"matchingMoney\":").append("\"").append(detail.getMatchingMoney()).append("\",");
                sb.append("\"itemFlow\":").append("\"").append(detail.getItemFlow()).append("\",");
                sb.append("\"money\":").append("\"").append(detail.getMoney()).append("\"");
                // sb.append("\"t\":").append("\""+"预算金额："+detail.getMoney()+"\"");
                sb.append("},");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    @RequestMapping(value = "/viewDetailBudget", method = {RequestMethod.POST, RequestMethod.GET})
    public String viewDetailBudget(String projFlow, Model model) {
        SrmProjFundInfo fundInfo = new SrmProjFundInfo();
        fundInfo.setProjFlow(projFlow);
        List<SrmProjFundInfo> fundInfoList = fundInfoBiz.searchFundInfo(fundInfo);
        if (fundInfoList.size() > 0) {
            SrmProjFundInfo fundInfoTemp = fundInfoList.get(0);
            model.addAttribute("fundInfo", fundInfoTemp);
            if (fundInfoTemp.getBudgetAmount() != null && !fundInfoTemp.getBudgetAmount().equals("")) {
                String schemeFlow = fundInfoList.get(0).getSchemeFlow();
                if (StringUtil.isNotBlank(schemeFlow)) {
                    //查询该项目经费的预算方案信息及其预算项信息
                    SrmFundScheme scheme = schemeBiz.readFundScheme(schemeFlow);
                    model.addAttribute("scheme", scheme);
                }
            }
            Map<String,Object> map = new HashMap<>();
            map.put("fundFlow",fundInfoTemp.getFundFlow());
            map.put("fundTypeId",ProjFundTypeEnum.Budget.getId());
            List<FundDetailAndSchemeExt> fundDtlList = fundInfoDetailBiz.selectFundDetailExt(map);
            model.addAttribute("fundDtlList", fundDtlList);
        }
        PubProj proj = pubProjBiz.readProject(projFlow);
        model.addAttribute("proj", proj);
        return "srm/fund/scheme/viewDetailBudget";
    }
    @RequestMapping("/initFundScheme")
    @ResponseBody
    public List<SrmFundScheme> initFundScheme(String firstSourceId,String secondSourceId){
        if(StringUtil.isNotBlank(firstSourceId) && StringUtil.isNotBlank(secondSourceId)){
            SrmProjSourceScheme sourceScheme = new SrmProjSourceScheme();
            sourceScheme.setProjFirstSourceId(firstSourceId);
            sourceScheme.setProjSecondSourceId(secondSourceId);
            List<SrmProjSourceScheme> sourceSchemeList = schemeBiz.searchSourceScheme(sourceScheme);
            List<String> schemeFlowList = new ArrayList<>();
            for(SrmProjSourceScheme scheme : sourceSchemeList){
                if(!schemeFlowList.contains(scheme.getSchemeFlow())){
                    schemeFlowList.add(scheme.getSchemeFlow());
                }
            }
            if(schemeFlowList.size()==0){
                schemeFlowList.add("");
            }
            SrmFundScheme srmFundScheme = new SrmFundScheme();
            srmFundScheme.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<SrmFundScheme> schemeList = schemeBiz.searchFundSchemeByFlows(srmFundScheme,schemeFlowList);

            return schemeList;
        }
        return null;
    }
}
