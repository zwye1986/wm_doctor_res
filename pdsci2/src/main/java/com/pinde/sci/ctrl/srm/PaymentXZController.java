package com.pinde.sci.ctrl.srm;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.ProjFundAccountsTypeEnum;
import com.pinde.sci.enums.srm.ProjFundTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.ProjFundFormExt;
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
@RequestMapping("/srm/paymentXZ")

public class PaymentXZController extends GeneralController {

    private static Logger logger = LoggerFactory.getLogger(AchCopyrightController.class);

    @Autowired
    private IPaymentBiz paymentBiz;
    @Autowired
    private IFundProcessBiz funProcessBiz;
    @Autowired
    private IPubProjBiz projBiz;
    @Autowired
    private IFundSchemeDetailBiz fundItemBiz;
    @Autowired
    private IFundInfoDetailBiz fundInfoDetailBiz;
    @Autowired
    private IFileBiz pubFileBiz;
    @Autowired
    private IFundFormBiz fundFormBiz;

    @RequestMapping("/fundDetailGroup")
    public String fundDetailGroup(String fundFlow, Model model, String projFlow) {
        PubProj proj = this.projBiz.readProject(projFlow);
        SrmProjFundInfo fundInfo = paymentBiz.getFundInfoByFundFlow(fundFlow);
        model.addAttribute("proj", proj);
        model.addAttribute("fundInfo", fundInfo);
        List<SrmProjFundForm> fundFormList = paymentBiz.getFormByFundFlow(fundFlow);
        model.addAttribute("fundFormList", fundFormList);
        return "/srm/payment/xzDetailGroupList";
    }

    @RequestMapping("/getDetailByGroup")
    public String getDetailByGroup(String fundFlow, String schemeFlow, String formFlow, Model model) {
        SrmProjFundInfo fundInfo = paymentBiz.getFundInfoByFundFlow(fundFlow);
        PubProj proj = this.projBiz.readProject(fundInfo.getProjFlow());
        model.addAttribute("proj", proj);
        model.addAttribute("fundInfo", fundInfo);
        if (StringUtil.isNotBlank(formFlow)) {
            SrmProjFundForm fundForm = paymentBiz.getFormByFormFlow(formFlow);
            List<SrmProjFundDetail> fundDetailList = paymentBiz.getDetailByGroup(formFlow, fundFlow);
            Map<String, List<SrmProjFundDetail>> fundDetailMap = new HashMap<String, List<SrmProjFundDetail>>();
            List<SrmProjFundDetail> list = null;
            Map<String, PubFile> fileMap = new HashMap<>();
            for (SrmProjFundDetail fundDetail : fundDetailList) {
                String itemFlow = fundDetail.getItemFlow();
                if (fundDetailMap.containsKey(itemFlow)) {
                    list = fundDetailMap.get(itemFlow);
                    list.add(fundDetail);
                } else {
                    list = new ArrayList<SrmProjFundDetail>();
                    list.add(fundDetail);
                    fundDetailMap.put(itemFlow, list);
                }
                //查询经费文件
                List<PubFile> fileList = pubFileBiz.findFileByTypeFlow(null, fundDetail.getFundDetailFlow());
                if (fileList.size() > 0) {
                    fileMap.put(fundDetail.getFundDetailFlow(), fileList.get(0));
                }
            }
            model.addAttribute("fundDetailMap", fundDetailMap);
            model.addAttribute("fileMap", fileMap);
            model.addAttribute("fundForm", fundForm);
        }
        //根据经费flow查询所有下拨已报销经费详情
        SrmProjFundDetail fundGove = new SrmProjFundDetail();
        fundGove.setFundFlow(fundFlow);
        fundGove.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
        fundGove.setOperStatusId(AchStatusEnum.FifthAudit.getId());
        fundGove.setRealityTypeId(ProjFundAccountsTypeEnum.Allocate.getId());
        List<SrmProjFundDetail> fundGovetlList = fundInfoDetailBiz.searchFundDetail(fundGove);
        BigDecimal allocateMoney = sumListMoney(fundGovetlList);
        //根据经费flow查询所有配套已报销经费详情
        SrmProjFundDetail fundOrg = new SrmProjFundDetail();
        fundOrg.setFundFlow(fundFlow);
        fundOrg.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
        fundOrg.setOperStatusId(AchStatusEnum.FifthAudit.getId());
        fundOrg.setRealityTypeId(ProjFundAccountsTypeEnum.Matching.getId());
        List<SrmProjFundDetail> fundOrgtlList = fundInfoDetailBiz.searchFundDetail(fundOrg);
        BigDecimal matchingMoney = sumListMoney(fundOrgtlList);
        model.addAttribute("allocateMoney", allocateMoney);
        model.addAttribute("matchingMoney", matchingMoney);
        //查询报销项目
        List<SrmFundSchemeDetail> schemeDetailList = paymentBiz.getSchemeDetailBySchemeFlow(schemeFlow);
        model.addAttribute("schemeDetailList", schemeDetailList);
        //查询经费预算
        SrmProjFundDetail fundDtl = new SrmProjFundDetail();
        fundDtl.setFundFlow(fundFlow);
        fundDtl.setFundTypeId(ProjFundTypeEnum.Budget.getId());
        List<SrmProjFundDetail> fundBudgetDtlList = fundInfoDetailBiz.searchFundDetail(fundDtl);
        Map<String, SrmProjFundDetail> budgetFundDtlMap = new HashMap<String, SrmProjFundDetail>();
        for (SrmProjFundDetail fundBudgetDtl : fundBudgetDtlList) {
            budgetFundDtlMap.put(fundBudgetDtl.getItemFlow(), fundBudgetDtl);
        }
        model.addAttribute("budgetFundDtlMap", budgetFundDtlMap);
        return "/srm/payment/xzDetail";
    }

    private BigDecimal sumListMoney(List<SrmProjFundDetail> list) {
        BigDecimal money = new BigDecimal("0.0000");
        if (list.size() > 0) {
            for (SrmProjFundDetail detail : list) {
                money = money.add(detail.getRealmoney());
            }
        }
        return money;
    }

    @RequestMapping("/saveFundDetailGroup")
    public
    @ResponseBody
    String saveFundDetailGroup(@RequestBody List<SrmProjFundDetail> fundDetailList, HttpServletRequest request) {
        if (fundDetailList.size() > 0) {
            BigDecimal money = new BigDecimal("0");
            for (SrmProjFundDetail detail : fundDetailList) {
                if (StringUtil.isNotBlank(detail.getFundDetailFlow())) {
                    detail.setOperStatusId(AchStatusEnum.Submit.getId());
                    detail.setOperStatusName(AchStatusEnum.Submit.getName());
                    this.fundInfoDetailBiz.updateFundDetail(detail);
                    money = money.add(detail.getMoney());
                }
            }
            SrmProjFundForm fundForm = new SrmProjFundForm();
            fundForm.setFundOperator(fundDetailList.get(0).getFundOperator());
            fundForm.setFundFormFlow(fundDetailList.get(0).getFundFormFlow());
            fundForm.setFundFlow(fundDetailList.get(0).getFundFlow());
            fundForm.setMoney(money);
            fundForm.setOperStatusId(AchStatusEnum.Submit.getId());
            fundForm.setOperStatusName(AchStatusEnum.Submit.getName());
            paymentBiz.submitReimburseForm(fundForm);
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }

    @RequestMapping("/auditList/{scope}")
    public String auditList(@PathVariable String scope, ProjFundFormExt fundFormExt, Model model) {
        List<String> currRoleList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_ROLE_LIST);
        List<String> statusIds = new ArrayList<String>();
        //获取科教科长角色流水号
        String chiefRoleFlow = InitConfig.getSysCfg("srm_local_xz_chief");
        //获取分管院长角色流水号
        String deanRoleFlow = InitConfig.getSysCfg("srm_local_xz_dean");
        if (GlobalConstant.USER_LIST_FINANCE.equals(scope)) {
            statusIds.add(AchStatusEnum.FifthAudit.getId());
            if (GlobalContext.getCurrentUser().getUserFlow().equals(GlobalConstant.ROOT_USER_FLOW) || currRoleList.contains(chiefRoleFlow)) {
                statusIds.add(AchStatusEnum.ThirdAudit.getId());
                statusIds.add(AchStatusEnum.FourthAudit.getId());
                model.addAttribute("isChief", "Y");
            }
            if (GlobalContext.getCurrentUser().getUserFlow().equals(GlobalConstant.ROOT_USER_FLOW) || currRoleList.contains(deanRoleFlow)) {
                statusIds.add(AchStatusEnum.FourthAudit.getId());
                model.addAttribute("isDean", "Y");
            }
        } else {
            statusIds.add(AchStatusEnum.Submit.getId());
            statusIds.add(AchStatusEnum.ThirdAudit.getId());
            statusIds.add(AchStatusEnum.FourthAudit.getId());
            statusIds.add(AchStatusEnum.FifthAudit.getId());
        }
        fundFormExt.setOperStatusIds(statusIds);
        List<ProjFundFormExt> fundFormExtList = paymentBiz.queryFundFormAuditList(fundFormExt);
        model.addAttribute("fundFormExtList", fundFormExtList);
        model.addAttribute("scope", scope);
        model.addAttribute("fundFormExt", fundFormExt);
        return "/srm/payment/xzAuditList";
    }

    @RequestMapping("/audit/{scope}")
    public String audit(@PathVariable String scope, String fundFormFlow, Model model) {
        model.addAttribute("scope", scope);
        ProjFundFormExt fundFormExt = paymentBiz.selectFundFormExtByFormFlow(fundFormFlow);
        List<SrmProjFundDetail> fundDetailList = paymentBiz.getDetailByGroup(fundFormFlow, fundFormExt.getFundFlow());

        Map<String, List<SrmProjFundDetail>> fundDetailMap = new HashMap<String, List<SrmProjFundDetail>>();
        List<SrmProjFundDetail> list = null;
        Map<String, PubFile> fileMap = new HashMap<>();
        for (SrmProjFundDetail fundDetail : fundDetailList) {
            String itemFlow = fundDetail.getItemFlow();
            if (fundDetailMap.containsKey(itemFlow)) {
                list = fundDetailMap.get(itemFlow);
                list.add(fundDetail);
            } else {
                list = new ArrayList<SrmProjFundDetail>();
                list.add(fundDetail);
                fundDetailMap.put(itemFlow, list);
            }
            //查询经费文件
            List<PubFile> fileList = pubFileBiz.findFileByTypeFlow(null, fundDetail.getFundDetailFlow());
            if (fileList.size() > 0) {
                fileMap.put(fundDetail.getFundDetailFlow(), fileList.get(0));
            }
        }
        List<SrmFundSchemeDetail> schemeDetailList = paymentBiz.getSchemeDetailBySchemeFlow(fundFormExt.getProjFundInfo().getSchemeFlow());
        model.addAttribute("schemeDetailList", schemeDetailList);
        //查询经费预算
        SrmProjFundDetail fundDtl = new SrmProjFundDetail();
        fundDtl.setFundFlow(fundFormExt.getProjFundInfo().getFundFlow());
        fundDtl.setFundTypeId(ProjFundTypeEnum.Budget.getId());
        List<SrmProjFundDetail> fundBudgetDtlList = fundInfoDetailBiz.searchFundDetail(fundDtl);
        Map<String, SrmProjFundDetail> budgetFundDtlMap = new HashMap<String, SrmProjFundDetail>();
        for (SrmProjFundDetail fundBudgetDtl : fundBudgetDtlList) {
            budgetFundDtlMap.put(fundBudgetDtl.getItemFlow(), fundBudgetDtl);
        }
        model.addAttribute("budgetFundDtlMap", budgetFundDtlMap);
        model.addAttribute("fileMap", fileMap);
        model.addAttribute("fundDetailMap", fundDetailMap);
        model.addAttribute("fundFormExt", fundFormExt);
        return "/srm/payment/xzAudit";
    }

    @RequestMapping(value = "/saveAudit", method = {RequestMethod.POST})
    @ResponseBody
    public String saveAudit(String jsondata, String agreeFlag, String content, Model model) {
        List<SrmProjFundDetail> fundDetailList = JSON.parseArray(jsondata, SrmProjFundDetail.class);
        SrmProjFundForm fundForm = paymentBiz.getFormByFormFlow(fundDetailList.get(0).getFundFormFlow());
        SrmFundProcess fundProcess = new SrmFundProcess();
        if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
            if (fundForm.getOperStatusId().equals(AchStatusEnum.ThirdAudit.getId())) {
                //操作id(科教科长审核通过)
                fundForm.setOperStatusId(AchStatusEnum.FourthAudit.getId());
                fundForm.setOperStatusName(AchStatusEnum.FourthAudit.getName());
                fundProcess.setOperStatusId(AchStatusEnum.FourthAudit.getId());
                fundProcess.setOperStatusName(AchStatusEnum.FourthAudit.getName());
                setFundDetailStatusId(fundDetailList, AchStatusEnum.FourthAudit.getId(), AchStatusEnum.FourthAudit.getName());
            } else if (fundForm.getOperStatusId().equals(AchStatusEnum.FourthAudit.getId())) {
                // 操作id(分管院长审核通过)
                fundForm.setOperStatusId(AchStatusEnum.FifthAudit.getId());
                fundForm.setOperStatusName(AchStatusEnum.FifthAudit.getName());
                fundProcess.setOperStatusId(AchStatusEnum.FifthAudit.getId());
                fundProcess.setOperStatusName(AchStatusEnum.FifthAudit.getName());
                setFundDetailStatusId(fundDetailList, AchStatusEnum.FifthAudit.getId(), AchStatusEnum.FifthAudit.getName());
            } else {
                //  操作id(科教科审核通过)
                fundForm.setOperStatusId(AchStatusEnum.ThirdAudit.getId());
                fundForm.setOperStatusName(AchStatusEnum.ThirdAudit.getName());
                fundProcess.setOperStatusId(AchStatusEnum.ThirdAudit.getId());
                fundProcess.setOperStatusName(AchStatusEnum.ThirdAudit.getName());
                setFundDetailStatusId(fundDetailList, AchStatusEnum.ThirdAudit.getId(), AchStatusEnum.ThirdAudit.getName());
            }
        } else {
            if (fundForm.getOperStatusId().equals(AchStatusEnum.ThirdAudit.getId())) {
                fundForm.setOperStatusId(AchStatusEnum.FourthBack.getId());
                fundForm.setOperStatusName(AchStatusEnum.FourthBack.getName());
                fundProcess.setOperStatusId(AchStatusEnum.FourthBack.getId());
                fundProcess.setOperStatusName(AchStatusEnum.FourthBack.getName());
                setFundDetailStatusId(fundDetailList, AchStatusEnum.FourthBack.getId(), AchStatusEnum.FourthBack.getName());
            } else if (fundForm.getOperStatusId().equals(AchStatusEnum.FourthAudit.getId())) {
                fundForm.setOperStatusId(AchStatusEnum.FifthBack.getId());
                fundForm.setOperStatusName(AchStatusEnum.FifthBack.getName());
                fundProcess.setOperStatusId(AchStatusEnum.FifthBack.getId());
                fundProcess.setOperStatusName(AchStatusEnum.FifthBack.getName());
                setFundDetailStatusId(fundDetailList, AchStatusEnum.FifthBack.getId(), AchStatusEnum.FifthBack.getName());
            } else {
                fundForm.setOperStatusId(AchStatusEnum.ThirdBack.getId());
                fundForm.setOperStatusName(AchStatusEnum.ThirdBack.getName());
                fundProcess.setOperStatusId(AchStatusEnum.ThirdBack.getId());
                fundProcess.setOperStatusName(AchStatusEnum.ThirdBack.getName());
                setFundDetailStatusId(fundDetailList, AchStatusEnum.ThirdBack.getId(), AchStatusEnum.ThirdBack.getName());
            }
        }
        fundProcess.setFundProcessFlow(PkUtil.getUUID());
        fundProcess.setFundFlow(fundForm.getFundFlow());
        GeneralMethod.setRecordInfo(fundProcess, true);

        fundProcess.setOperateTime(DateUtil.getCurrDateTime());
        fundProcess.setContent(content);
        SysUser currUser = GlobalContext.getCurrentUser();
        fundProcess.setOperateUserFlow(currUser.getUserFlow());
        fundProcess.setOperateUserName(currUser.getUserName());
        paymentBiz.updateFormStatus(fundDetailList, fundForm, fundProcess);

        return GlobalConstant.OPERATE_SUCCESSED;

    }

    private void setFundDetailStatusId(List<SrmProjFundDetail> fundDetailList, String enumId, String enumName) {
        if (fundDetailList.size() > 0) {
            for (SrmProjFundDetail detail : fundDetailList) {
                detail.setOperStatusId(enumId);
                detail.setOperStatusName(enumName);
            }
        }
    }

    /**
     * 查询操作人姓名
     *
     * @param process
     * @return
     */
    private String fundProcessUserName(SrmFundProcess process) {
        List<SrmFundProcess> localProcessList = this.funProcessBiz.searchFundProcesses(process);
        //审核人
        String userName = "";
        if (localProcessList.size() >= 1) {
            if (localProcessList.size() == 1) {
                userName = localProcessList.get(0).getOperateUserName();
            } else {
                long time = 0;
                for (SrmFundProcess localProcess : localProcessList) {
                    long operateTime = Long.parseLong(localProcess.getOperateTime());
                    if (operateTime > time) {
                        time = operateTime;
                        userName = localProcess.getOperateUserName();
                    }
                }
            }
        }
        return userName;
    }

    @RequestMapping("/print")
    public String printFundDetail(Model model, String fundFormFlow) {
        if (StringUtil.isNotBlank(fundFormFlow)) {
            SrmProjFundForm fundForm = paymentBiz.getFormByFormFlow(fundFormFlow);
            model.addAttribute("fundForm", fundForm);
            SrmFundProcess process = new SrmFundProcess();
            process.setFundDetailFlow(fundFormFlow);
            process.setOperStatusId(AchStatusEnum.ThirdAudit.getId());
            model.addAttribute("sciName", fundProcessUserName(process));
            process.setOperStatusId(AchStatusEnum.FourthAudit.getId());
            model.addAttribute("chiefName", fundProcessUserName(process));
            process.setOperStatusId(AchStatusEnum.FifthAudit.getId());
            model.addAttribute("deanName", fundProcessUserName(process));
            return "/srm/payment/xzPrint";
        }
        return GlobalConstant.OPRE_FAIL;
    }

    @RequestMapping("deleteFundForm")
    public
    @ResponseBody
    String deleteFundForm(Model model, String fundFormFlow,String fundInfoFlow) {
        if (StringUtil.isNotBlank(fundFormFlow)) {
            List<SrmProjFundDetail> delDetailList = paymentBiz.getDetailByGroup(fundFormFlow, fundInfoFlow);
            for (SrmProjFundDetail detail : delDetailList) {
                fundInfoDetailBiz.deleteFundDetail(detail.getFundDetailFlow());
                pubFileBiz.deleteFileByTypeFlow(ProjFundTypeEnum.Reimburse.getId(),detail.getFundDetailFlow());
            }
            fundFormBiz.deleteFundForm(fundFormFlow);
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }
}
