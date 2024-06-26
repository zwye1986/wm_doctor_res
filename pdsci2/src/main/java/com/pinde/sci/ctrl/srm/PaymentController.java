package com.pinde.sci.ctrl.srm;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.common.*;
import com.pinde.sci.common.util.NumTrans;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.ProjFundAccountsTypeEnum;
import com.pinde.sci.enums.srm.ProjFundTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.FundDetailAndSchemeExt;
import com.pinde.sci.model.srm.FundInfoExt;
import com.pinde.sci.model.srm.ProjFundDetailExt;
import com.pinde.sci.model.srm.PubProjExt;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.Map.Entry;

@Controller
@RequestMapping("/srm/payment")

public class PaymentController extends GeneralController {

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
    private IFundInfoBiz fundInfoBiz;

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(PubProj proj, Integer currentPage, Model model, HttpServletRequest request) {
        //  proj.setProjCategoryId(ProjCategroyEnum.Ky.getId());
        SysUser currUser = GlobalContext.getCurrentUser();
        PubProjExt projExt = new PubProjExt();
        if (StringUtil.isNotBlank(proj.getProjName())) {
            projExt.setProjName(proj.getProjName());
        }
        projExt.setApplyUserFlow(currUser.getUserFlow());
        List<String> statuIds = new ArrayList<String>();
        statuIds.add(AchStatusEnum.FirstAudit.getId());
        // statuIds.add(AchStatusEnum.SecondAudit.getId());
        projExt.setStatusids(statuIds);
        //  projExt.setProjCategoryId(ProjCategroyEnum.Ky.getId());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<PubProjExt> PubProjExtList = this.fundInfoBiz.searchPubProjExtList(projExt);

        model.addAttribute("PubProjExtList", PubProjExtList);

        //List<FundInfoExt> fundInfoList = paymentBiz.queryPaymentList(proj);
        // model.addAttribute("fundInfoList", fundInfoList);
//        Map<String, Integer> noApproveMap = new HashMap<String, Integer>();
//        SrmProjFundDetail fundDetailFirstBack = new SrmProjFundDetail();
//        fundDetailFirstBack.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
//        fundDetailFirstBack.setOperStatusId(AchStatusEnum.FirstBack.getId());
//
//        SrmProjFundDetail fundDetailSecondBack = new SrmProjFundDetail();
//        fundDetailSecondBack.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
//        fundDetailSecondBack.setOperStatusId(AchStatusEnum.SecondBack.getId());
        //查询该项目经费下 报销审核没通过的

//        for (FundInfoExt fundInfoExt : fundInfoList) {
//            SrmProjFundInfo spfi = fundInfoExt.getFund();
//            fundDetailFirstBack.setFundFlow(spfi.getFundFlow());
//            fundDetailSecondBack.setFundFlow(spfi.getFundFlow());
//            int countFirstBack = this.paymentBiz.searchFundDetailNoApproveCount(fundDetailFirstBack);
//            int countSecondBack = this.paymentBiz.searchFundDetailNoApproveCount(fundDetailSecondBack);
//            System.out.println(countFirstBack + "," + countSecondBack + "," + spfi.getFundFlow());
//            noApproveMap.put(spfi.getFundFlow(), countFirstBack + countSecondBack);
//        }
//        model.addAttribute("noApproveMap", noApproveMap);
        return "/srm/payment/paymentList";
    }

    @RequestMapping("/getDetailByFundFlow")
    public String getDetailByFundFlow(String fundFlow, String schemeFlow, String projFlow, Model model) {
        PubProj proj = this.projBiz.readProject(projFlow);
        model.addAttribute("proj", proj);
        List<SrmProjFundDetail> fundDetailList = paymentBiz.getDetailByFundFlow(fundFlow);
        model.addAttribute("fundFlow", fundFlow);
        //将fundDetail根据项目方案中的预算项分组
        Map<String, List<SrmProjFundDetail>> fundDetailMap = new HashMap<String, List<SrmProjFundDetail>>();
        List<SrmProjFundDetail> list = null;
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
        }
        model.addAttribute("fundDetailMap", fundDetailMap);
        //查询报销项目
        List<SrmFundSchemeDetail> schemeDetailList = paymentBiz.getSchemeDetailBySchemeFlow(schemeFlow);
        model.addAttribute("schemeDetailList", schemeDetailList);
        //查询经费预算
        SrmProjFundDetail fundDtl = new SrmProjFundDetail();
        fundDtl.setFundFlow(fundFlow);
        fundDtl.setFundTypeId(ProjFundTypeEnum.Budget.getId());
        List<SrmProjFundDetail> fundBudgetDtlList = fundInfoDetailBiz.searchFundDetail(fundDtl);
        Map<String, SrmProjFundDetail> budgetFundDtlMap = new HashMap<String, SrmProjFundDetail>();
        Map<String, BigDecimal> budgetMoneyMap = new HashMap<String, BigDecimal>();
        Map<String, BigDecimal> realMoneyMap = new HashMap<String, BigDecimal>();
        for (SrmProjFundDetail fundBudgetDtl : fundBudgetDtlList) {
            budgetFundDtlMap.put(fundBudgetDtl.getItemFlow(), fundBudgetDtl);
        }
        model.addAttribute("budgetFundDtlMap", budgetFundDtlMap);
        //计算已报销比例
        Map<String, Double> yetPaymentMap = new HashMap<String, Double>();
        Set<Entry<String, List<SrmProjFundDetail>>> entrySet = fundDetailMap.entrySet();
        Iterator<Entry<String, List<SrmProjFundDetail>>> iterator = entrySet.iterator();
        while (iterator.hasNext()) {
            Entry<String, List<SrmProjFundDetail>> entry = iterator.next();
            String key = entry.getKey();
            List<SrmProjFundDetail> value = entry.getValue();
            yetPaymentMap.put(key, new Double("0"));
            for (SrmProjFundDetail spfd : value) {
                //if(AchStatusEnum.FirstAudit.getId().equals(spfd.getOperStatusId())){
                if (null == spfd.getMoney()) {
                    continue;
                }
                Double amount = yetPaymentMap.get(key);
                BigDecimal addAfterCount = spfd.getMoney().add(new BigDecimal(amount));
                yetPaymentMap.put(key, addAfterCount.doubleValue());
                //}
            }
            if (budgetFundDtlMap.containsKey(key)) {
                if (null != yetPaymentMap.get(key) && null != budgetFundDtlMap.get(key).getMoney()) {
                    BigDecimal currentAmount = new BigDecimal(yetPaymentMap.get(key));
                    BigDecimal subAfter = currentAmount.subtract(budgetFundDtlMap.get(key).getMoney());
                    yetPaymentMap.put(key, subAfter.doubleValue());
                }
            }

        }
        model.addAttribute("yetPaymentMap", yetPaymentMap);
        return "/srm/payment/detail";
    }

    @RequestMapping("/getDetailByFundFlow1")
    public String getDetailByFundFlow1(String fundFlow, String schemeFlow, String projFlow, Model model) {
        PubProj proj = this.projBiz.readProject(projFlow);
        model.addAttribute("proj", proj);
        //查询报销项目
        List<SrmProjFundDetail> fundDetailList = paymentBiz.getDetailByFundFlow(fundFlow);
        Map<String, List<SrmProjFundDetail>> map = new HashMap<>();
        for (SrmProjFundDetail detail : fundDetailList) {
            String key = detail.getItemFlow();
            if (map.containsKey(key)) {
                map.get(key).add(detail);

            } else {
                List<SrmProjFundDetail> detailList = new ArrayList<>();
                detailList.add(detail);
                map.put(key, detailList);
            }
        }
        model.addAttribute("fundDetailMap", map);
        model.addAttribute("fundFlow", fundFlow);
        //将fundDetail根据项目方案中的预算项分组

        //查询经费预算
        Map<String,Object> searchMap = new HashMap<>();
        searchMap.put("fundFlow",fundFlow);
        searchMap.put("fundTypeId",ProjFundTypeEnum.Budget.getId());
        List<FundDetailAndSchemeExt> fundBudgetDtlList = fundInfoDetailBiz.selectFundDetailExt(searchMap);
        Map<String, SrmProjFundDetail> budgetFundDtlMap = new HashMap<>();

        for (FundDetailAndSchemeExt fundBudgetDtl : fundBudgetDtlList) {
            String key = fundBudgetDtl.getItemFlow();
//            if(StringUtil.isNotBlank(fundBudgetDtl.getItemPid())){
//                key=fundBudgetDtl.getItemPid()+fundBudgetDtl.getItemId();
//            }
            budgetFundDtlMap.put(key, fundBudgetDtl);
        }
        model.addAttribute("budgetFundDtlMap", budgetFundDtlMap);

        return "/srm/payment/detail";
    }

    /**
     * 报销
     *
     * @param fundDetail
     * @return
     */
    @RequestMapping(value = "/reimburse")
    public String reimburse(SrmProjFundDetail fundDetail, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        PubFile pubFile = null;
        if (file != null && StringUtil.isNotBlank(file.getOriginalFilename())) {
            pubFile = new PubFile();
            pubFile.setFileName(file.getOriginalFilename());
            pubFile.setProductType(ProjFundTypeEnum.Reimburse.getId());
            String[] nameArray = file.getOriginalFilename().split("\\.");
            pubFile.setFileSuffix(nameArray[nameArray.length - 1]);
            pubFile.setFileType(nameArray[nameArray.length - 1]);
            pubFile.setFileUpType("1");
            String dateString = DateUtil.getCurrDate2();
            String filePath = StringUtil.defaultString(InitConfig.getSysCfg("srm_apply_file")) + File.separator + "srmReimburseFile" + File.separator + dateString;
            File fileDir = new File(filePath);
            String saveFileName = PkUtil.getUUID() + "." + nameArray[nameArray.length - 1];
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File srmFundFile = new File(fileDir, saveFileName);
            file.transferTo(srmFundFile);
            String uploadFile = File.separator + "srmReimburseFile" + File.separator + dateString + File.separator + saveFileName;
            pubFile.setFilePath(uploadFile);
        }
        this.paymentBiz.reimburse(fundDetail, pubFile);
        SrmProjFundInfo fundInfo = this.paymentBiz.getFundInfoByFundFlow(fundDetail.getFundFlow());
        if (InitConfig.getSysCfg("srm_local_type").equals("Y")) {
            return "redirect:/srm/paymentXZ/getDetailByGroup?fundFlow=" + fundDetail.getFundFlow() + "&schemeFlow=" + fundInfo.getSchemeFlow() + "&formFlow=" + fundDetail.getFundFormFlow();
        }
        return "redirect:/srm/payment/getDetailByFundFlow?fundFlow=" + fundDetail.getFundFlow() + "&schemeFlow=" + fundInfo.getSchemeFlow();
    }


    @RequestMapping("/addReimburse")
    public String addReimburse(Model model,String fundFlow){
        if(StringUtil.isNotBlank(fundFlow)){
            SrmProjFundInfo fundInfo = fundInfoBiz.getFundInfoByFlow(fundFlow);
            PubProj pubProj = projBiz.readProject(fundInfo.getProjFlow());

            model.addAttribute("fundInfo",fundInfo);
            model.addAttribute("pubProj",pubProj);
        }
        return "srm/payment/addPayment";
    }
    /**
     * （财务）保存报销
     *
     * @param fundDetail
     * @return
     */
    @RequestMapping(value = "/saveReimburse")
    @ResponseBody
    public String saveReimburse(SrmProjFundDetail fundDetail)  {
        if(StringUtil.isNotBlank(fundDetail.getFundFlow()) && fundDetail.getRealmoney() != null) {
            SrmFundProcess process=new SrmFundProcess();
            process.setOperStatusId(AchStatusEnum.SecondAudit.getId());
            process.setOperStatusName(AchStatusEnum.SecondAudit.getName());
            process.setFundFlow(fundDetail.getFundFlow());
            process.setOperateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            process.setOperateUserName(GlobalContext.getCurrentUser().getUserName());
            process.setOperateTime(DateUtil.getCurrDateTime());
            process.setFundProcessFlow(PkUtil.getUUID());

            GeneralMethod.setRecordInfo(process, true);
            fundDetail.setMoney(fundDetail.getRealmoney().divide(new BigDecimal(10000)));
            fundDetail.setRealmoney(fundDetail.getRealmoney().divide(new BigDecimal(10000)));
            fundDetail.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
            fundDetail.setFundTypeName(ProjFundTypeEnum.Reimburse.getName());
            fundDetail.setOperStatusId(AchStatusEnum.SecondAudit.getId());
            fundDetail.setOperStatusName(AchStatusEnum.SecondAudit.getName());
            if(StringUtil.isNotBlank(fundDetail.getRealityTypeId())){
                fundDetail.setRealityTypeName(ProjFundAccountsTypeEnum.getNameById(fundDetail.getRealityTypeId()));
            }
            if(StringUtil.isNotBlank(fundDetail.getFundSourceId())){
                fundDetail.setFundSourceName(DictTypeEnum.ProjFundAccountsType.getDictNameById(fundDetail.getFundSourceId()));
            }
            paymentBiz.updateDetailStatus(fundDetail,process);
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    @RequestMapping(value = {"/saveDetailList"}, method = RequestMethod.POST)
    @ResponseBody
    public String saveDetailList(String fundFlow, @RequestBody List<SrmProjFundDetail> detailList, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        for (SrmProjFundDetail detail : detailList) {
            //经费类型-->报销
            detail.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
            detail.setFundTypeName(ProjFundTypeEnum.Reimburse.getName());
            //操作-->提交
            detail.setOperStatusId(AchStatusEnum.Submit.getId());
            detail.setOperStatusName(AchStatusEnum.Submit.getName());
        }

        //封装过程对象
        SrmFundProcess fundProcess = new SrmFundProcess();
        fundProcess.setFundFlow(fundFlow);

        fundProcess.setOperStatusId(AchStatusEnum.Submit.getId());
        fundProcess.setOperStatusName(AchStatusEnum.Submit.getName());

        fundProcess.setOperateUserFlow(currUser.getUserFlow());
        fundProcess.setOperateUserName(currUser.getUserName());

        paymentBiz.saveDetailList(fundFlow, detailList, fundProcess);

        return GlobalConstant.OPRE_SUCCESSED;
    }

    //报销审核列表
    @RequestMapping("/auditList/{scope}")
    public String auditList(@PathVariable String scope,String expandFundFlow,PubProjExt projExt,String reimburseStatusId, Model model, Boolean isAudit,Integer currentPage,HttpServletRequest request ) {
        model.addAttribute("scope", scope);
        model.addAttribute("expandFundFlow",expandFundFlow);
        SysUser currUser = GlobalContext.getCurrentUser();
        //是否是徐州中心医院
        String isXzzxyy = InitConfig.getSysCfg("srm_local_type");
        if (isXzzxyy.equals("Y")) {
            return "redirect:/srm/paymentXZ/auditList/" + scope;
        }

        List<String> statusIds = new ArrayList<String>();
        if(StringUtils.isNotBlank(reimburseStatusId)) {
            statusIds.add(reimburseStatusId);
        }else{
            if (GlobalConstant.USER_LIST_FINANCE.equals(scope)) {
                if (null != isAudit && isAudit) {
                    statusIds.add(AchStatusEnum.FirstAudit.getId());
                } else {
                    statusIds.add(AchStatusEnum.SecondAudit.getId());
                    statusIds.add(AchStatusEnum.FirstAudit.getId());
                }
            } else {
                if (null != isAudit && isAudit) {
                    statusIds.add(AchStatusEnum.Submit.getId());
                } else {
                    statusIds.add(AchStatusEnum.Submit.getId());
                    statusIds.add(AchStatusEnum.FirstAudit.getId());
                    statusIds.add(AchStatusEnum.SecondAudit.getId());
                    statusIds.add(AchStatusEnum.SecondBack.getId());
                }
            }
        }
        projExt.setStatusids(statusIds);
        projExt.setApplyOrgFlow(currUser.getOrgFlow());
        //报销审核状态为statusIds的所有本机构项目
        PageHelper.startPage(currentPage, getPageSize(request));
        List<PubProjExt> pubProjExtList = paymentBiz.queryProjFundInfoList(projExt);
        //报销审核状态为statusIds所有本机构报销详细信息
        List<SrmProjFundDetail> srmProjFundDetails = paymentBiz.getReimburseDetail(null,statusIds);
        Map<String,List<SrmProjFundDetail>> fundDetailMap = new HashMap<>();
        for(SrmProjFundDetail fundDetail:srmProjFundDetails){
            if(fundDetailMap.containsKey(fundDetail.getFundFlow())){
                fundDetailMap.get(fundDetail.getFundFlow()).add(fundDetail);
            }else{
                List<SrmProjFundDetail> fundDetailList = new ArrayList<>();
                fundDetailList.add(fundDetail);
                fundDetailMap.put(fundDetail.getFundFlow(),fundDetailList);
            }
        }

        model.addAttribute("pubProjExtList", pubProjExtList);
        model.addAttribute("fundDetailMap", fundDetailMap);
        return "/srm/payment/auditList";
    }

    /**
     * 江苏省中报销审核（不需要单位审核、只查询待审核）
     * @param fundDetailExt
     * @param model
     * @param currentPage
     * @param request
     * @return
     */
    @RequestMapping("/reimburseAuditList")
    public String reimburseAuditList(ProjFundDetailExt fundDetailExt, Model model, Integer currentPage,HttpServletRequest request ) {
        model.addAttribute("fundDetailExt",fundDetailExt);
        SysUser currUser = GlobalContext.getCurrentUser();
        List<String> statusIds = new ArrayList<>();
        statusIds.add(AchStatusEnum.Submit.getId());
        statusIds.add(AchStatusEnum.FirstAudit.getId());
//        fundDetailExt.setOperStatusId(AchStatusEnum.Submit.getId());
        fundDetailExt.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        fundDetailExt.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
        fundDetailExt.setOperStatusIds(statusIds);
        if(null == fundDetailExt.getProj()) {
            fundDetailExt.setProj(new PubProj());
        }
        //报销审核状态为statusIds的所有本机构项目
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ProjFundDetailExt> fundDetailExtList = paymentBiz.selectProjFundDetailList(fundDetailExt);
        model.addAttribute("fundDetailExtList", fundDetailExtList);
        return "/srm/payment/reimburseAuditList";
    }

    @RequestMapping("/reimburseDiary")
    public String reimburseDiary(PubProjExt projExt,String startTime,String endTime,String startMoney,String endMoney,Integer currentPage,HttpServletRequest request,Model model){
        SysUser currUser = GlobalContext.getCurrentUser();
//        List<String> statusIds = new ArrayList<String>();
//        statusIds.add(AchStatusEnum.SecondAudit.getId());
//        statusIds.add(AchStatusEnum.FirstAudit.getId());
//        projExt.setStatusids(statusIds);
        projExt.setApplyOrgFlow(currUser.getOrgFlow());
        //报销审核状态为statusIds的所有本机构项目

        List<PubProjExt> pubProjExtList = paymentBiz.queryProjFundInfoList(projExt);
        SrmProjFundDetail detail = new SrmProjFundDetail();
        detail.setOperStatusId(AchStatusEnum.SecondAudit.getId());
        List<String> fundTypeId = new ArrayList<>();
        fundTypeId.add(ProjFundTypeEnum.Reimburse.getId());
        fundTypeId.add(ProjFundTypeEnum.Income.getId());
        fundTypeId.add(ProjFundTypeEnum.Surplus.getId());
        List<String> fundFlowList = new ArrayList<>();
        Map<String,PubProjExt> pubProjExtMap = new HashMap<>();
        for(PubProjExt pubProjExt : pubProjExtList){
            pubProjExtMap.put(pubProjExt.getProjFundInfo().getFundFlow(),pubProjExt);
            //fundFlowList.add(pubProjExt.getProjFundInfo().getFundFlow());
        }

        Map<String,Object> map = new HashMap<>();
        if(StringUtil.isNotBlank(startTime)){
            map.put("startTime",DateUtil.getDateTime(startTime));
        }
        if(StringUtil.isNotBlank(endTime)){
            map.put("endTime",DateUtil.getDateTime(endTime));
        }
        if(StringUtil.isNotBlank(startMoney)){

            map.put("startMoney",startMoney);
        }
        if(StringUtil.isNotBlank(endMoney)){
            map.put("endMoney",endMoney);
        }
        if(projExt != null) {
            if (StringUtil.isNotBlank(projExt.getProjDeclarerFlow())) {
                map.put("projDeclarerFlow", projExt.getProjDeclarerFlow());
            }
            if (StringUtil.isNotBlank(projExt.getApplyDeptFlow())) {
                map.put("applyDeptFlow", projExt.getApplyDeptFlow());
            }
            if (StringUtil.isNotBlank(projExt.getAccountNo())) {
                map.put("accountNo", projExt.getAccountNo());
            }
            if (StringUtil.isNotBlank(projExt.getProjNo())) {
                map.put("projNo", projExt.getProjNo());
            }
            if (StringUtil.isNotBlank(projExt.getApplyUserName())) {
                map.put("applyUserName", projExt.getApplyUserName());
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
//        List<SrmProjFundDetail> srmProjFundDetails = fundInfoDetailBiz.searchFundDetail(detail,fundTypeId,null,startTime,endTime,startMoney,endMoney);
        List<SrmProjFundDetail> srmProjFundDetails = fundInfoDetailBiz.searchFundDetailByProjAndFund(map);
        Map<String,List<SrmProjFundDetail>> fundDetailMap = new HashMap<>();
        List<String> list=new ArrayList<>();
        for(SrmProjFundDetail fundDetail:srmProjFundDetails){
            if(fundDetailMap.containsKey(fundDetail.getFundFlow())){
                fundDetailMap.get(fundDetail.getFundFlow()).add(fundDetail);
            }else{
                List<SrmProjFundDetail> fundDetailList = new ArrayList<>();
                fundDetailList.add(fundDetail);
                fundDetailMap.put(fundDetail.getFundFlow(),fundDetailList);
                if(!list.contains(fundDetail.getFundFlow()))
                {
                    list.add(fundDetail.getFundFlow());
                }
            }
        }

        model.addAttribute("list", list);
        model.addAttribute("pubProjExtMap", pubProjExtMap);
        model.addAttribute("fundDetailMap", fundDetailMap);
        model.addAttribute("srmProjFundDetails", srmProjFundDetails);
        return "/srm/payment/reimburseDiaryList";
    }

    @RequestMapping("/audit/{scope}")
    public String audit(@PathVariable String scope, String fundDetailFlow, Model model) {
        model.addAttribute("scope", scope);
        ProjFundDetailExt detailExt = this.fundInfoDetailBiz.selectProjFundDetailExt(fundDetailFlow);
        if(detailExt != null) {
       /*查询该项目下同经费方案项已报销总额*/
            SrmProjFundDetail budget = this.paymentBiz.searchBudgetDetail(detailExt.getFundFlow(), detailExt.getItemFlow());
            SrmProjFundDetail fundDtl = new SrmProjFundDetail();
            fundDtl.setOperStatusId(AchStatusEnum.SecondAudit.getId());
            //如果是徐州中心医院
            if (InitConfig.getSysCfg("srm_local_type").equals("Y")) {
                fundDtl.setOperStatusId(AchStatusEnum.FifthAudit.getId());
            }
            fundDtl.setFundFlow(detailExt.getFundFlow());
            fundDtl.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
            fundDtl.setItemFlow(detailExt.getItemFlow());
            List<SrmProjFundDetail> fundReimbursetDtlList = fundInfoDetailBiz.searchFundDetail(fundDtl);
            BigDecimal reimburseMoney = new BigDecimal("0");
            BigDecimal allocateMoney = new BigDecimal("0");
            BigDecimal matchingMoney = new BigDecimal("0");
            for (SrmProjFundDetail projFundDetail : fundReimbursetDtlList) {
                if (projFundDetail.getRealmoney() != null) {
                    reimburseMoney = reimburseMoney.add(projFundDetail.getRealmoney());
                    if (ProjFundAccountsTypeEnum.Allocate.getId().equals(projFundDetail.getRealityTypeId())) {
                        allocateMoney = allocateMoney.add(projFundDetail.getRealmoney());
                    }
                    if (ProjFundAccountsTypeEnum.Matching.getId().equals(projFundDetail.getRealityTypeId())) {
                        matchingMoney = matchingMoney.add(projFundDetail.getRealmoney());
                    }
                }
            }
            model.addAttribute("reimburseMoney", reimburseMoney);
            model.addAttribute("allocateMoney", allocateMoney);
            model.addAttribute("matchingMoney", matchingMoney);
            model.addAttribute("detailExt", detailExt);
            model.addAttribute("budget", budget);
        }
        return "/srm/payment/audit";
    }


    @RequestMapping(value = "/saveAudit/{scope}", method = {RequestMethod.POST})
    @ResponseBody
    public String saveAudit(@PathVariable String scope,String agreeFlag, SrmProjFundDetail projFundDetail, String content, Model model) {
        SrmProjFundDetail fundDetail = paymentBiz.getDetailByDetailFlow(projFundDetail.getFundDetailFlow());
        SrmFundProcess fundProcess = new SrmFundProcess();
        if (agreeFlag.equals(GlobalConstant.FLAG_Y)) {
            if (fundDetail.getOperStatusId().equals(AchStatusEnum.FirstAudit.getId()) || GlobalConstant.USER_LIST_FINANCE.equals(scope)) {
                /*操作id(财务审核通过)*/
                fundDetail.setOperStatusId(AchStatusEnum.SecondAudit.getId());
                fundDetail.setOperStatusName(AchStatusEnum.SecondAudit.getName());
                /*到账类型*/
                fundDetail.setRealityTypeId(projFundDetail.getRealityTypeId());
                /*到账方式*/
                fundDetail.setRealityTypeName(ProjFundAccountsTypeEnum.getNameById(projFundDetail.getRealityTypeId()));

                if(StringUtil.isNotBlank(fundDetail.getFundSourceId())){
                    fundDetail.setFundSourceName(DictTypeEnum.ProjFundAccountsType.getDictNameById(fundDetail.getFundSourceId()));
                }

                fundDetail.setFundRetype(projFundDetail.getFundRetype());
                /*添加实报金额*/
                fundDetail.setRealmoney(projFundDetail.getRealmoney().divide(new BigDecimal(10000)));
                fundProcess.setOperStatusId(AchStatusEnum.SecondAudit.getId());
                fundProcess.setOperStatusName(AchStatusEnum.SecondAudit.getName());
            } else {
                    /*承担单位审核通过*/
                fundDetail.setOperStatusId(AchStatusEnum.FirstAudit.getId());
                fundDetail.setOperStatusName(AchStatusEnum.FirstAudit.getName());
                fundProcess.setOperStatusId(AchStatusEnum.FirstAudit.getId());
                fundProcess.setOperStatusName(AchStatusEnum.FirstAudit.getName());
            }
        } else {
            if (fundDetail.getOperStatusId().equals(AchStatusEnum.FirstAudit.getId()) || GlobalConstant.USER_LIST_FINANCE.equals(scope)) {
                fundDetail.setOperStatusId(AchStatusEnum.SecondBack.getId());
                fundDetail.setOperStatusName(AchStatusEnum.SecondBack.getName());
                fundProcess.setOperStatusId(AchStatusEnum.SecondBack.getId());
                fundProcess.setOperStatusName(AchStatusEnum.SecondBack.getName());
            } else {

                fundDetail.setOperStatusId(AchStatusEnum.FirstBack.getId());
                fundDetail.setOperStatusName(AchStatusEnum.FirstBack.getName());
                fundProcess.setOperStatusId(AchStatusEnum.FirstBack.getId());
                fundProcess.setOperStatusName(AchStatusEnum.FirstBack.getName());
            }
        }
        fundProcess.setFundProcessFlow(PkUtil.getUUID());
        fundProcess.setFundFlow(fundDetail.getFundFlow());
        GeneralMethod.setRecordInfo(fundProcess, true);

        fundProcess.setOperateTime(DateUtil.getCurrDateTime());
        fundProcess.setContent(content);
        SysUser currUser = GlobalContext.getCurrentUser();
        fundProcess.setOperateUserFlow(currUser.getUserFlow());
        fundProcess.setOperateUserName(currUser.getUserName());

        paymentBiz.updateDetailStatus(fundDetail, fundProcess);

        return GlobalConstant.OPERATE_SUCCESSED;

    }

    /**
     * 更新报销详细
     *
     * @param detail
     * @return
     */
    @RequestMapping(value = "/updateDetail")
    @ResponseBody
    public String updateDetail(SrmProjFundDetail detail) {
        if (detail != null) {
            this.fundInfoDetailBiz.updateFundDetail(detail);
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 删除报销信息（财务已审核通过）
     *
     * @param fundDetailFlow
     * @return
     */
    @RequestMapping(value = "/deleteDetail")
    @ResponseBody
    public String deleteDetail(String fundDetailFlow) {
        if (StringUtil.isNotBlank(fundDetailFlow)) {
            int deleteResult = this.fundInfoDetailBiz.deleteFundDetail(fundDetailFlow);
            pubFileBiz.deleteFileByTypeFlow(ProjFundTypeEnum.Reimburse.getId(), fundDetailFlow);
            if (deleteResult == 1) {
                return GlobalConstant.DELETE_SUCCESSED;
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 删除报销明细
     *
     * @param fundDetailFlow
     * @return
     */
    @RequestMapping(value = "/deleteReimburse")
    @ResponseBody
    public String deleteReimburse(String fundDetailFlow) {
        if (StringUtil.isNotBlank(fundDetailFlow)) {
            try {
                int deleteResult = paymentBiz.deleteReimburse(fundDetailFlow);
                if (deleteResult == 1) {
                    return GlobalConstant.DELETE_SUCCESSED;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping(value = "/showPaymentAudit")
    public String showPaymentAudit(SrmFundProcess process, Model model) {

        List<SrmFundProcess> processList = this.funProcessBiz.searchFundProcesses(process);
        List<SrmFundProcess> funtProcessList = new ArrayList<SrmFundProcess>();
        for (SrmFundProcess fundProcess : processList) {
            if (fundProcess.getFundDetailFlow() == null) {
                funtProcessList.add(fundProcess);
            }
        }
        if (process.getFundFlow() != null) {
            model.addAttribute("processList", funtProcessList);
            return "/srm/payment/processInfo";
        }
        model.addAttribute("processList", processList);
        return "/srm/payment/processInfo";
    }

    /**
     * 打印支出信息（网页打印）
     *
     * @param model
     * @param fundDetailFlow
     * @return
     */
    @RequestMapping("/print")
    public String printFundDetail(Model model, String fundDetailFlow) {
        if (StringUtil.isNotBlank(fundDetailFlow)) {
            ProjFundDetailExt detailExt = this.fundInfoDetailBiz.selectProjFundDetailExt(fundDetailFlow);
            //预算总额
            SrmProjFundDetail budgetDetail = this.paymentBiz.searchBudgetDetail(detailExt.getFundFlow(), detailExt.getItemFlow());
            //已报销金额
            BigDecimal reimburseMoney = new BigDecimal("0");
            SrmProjFundDetail fundDtl = new SrmProjFundDetail();
            fundDtl.setFundFlow(detailExt.getFundFlow());
            fundDtl.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
            fundDtl.setOperStatusId(AchStatusEnum.SecondAudit.getId());
            List<SrmProjFundDetail> fundReimburseDtlList = fundInfoDetailBiz.searchFundDetail(fundDtl);
            for (SrmProjFundDetail reimburseFundDetail : fundReimburseDtlList) {
                if (reimburseFundDetail.getRealmoney() != null && !reimburseFundDetail.getRealmoney().equals("")) {
                    BigDecimal reimburse = reimburseFundDetail.getRealmoney();
                    reimburseMoney = reimburseMoney.add(reimburse);
                }
            }
            //经费操作过程(财务审核通过)
            SrmFundProcess process = new SrmFundProcess();
            process.setFundDetailFlow(fundDetailFlow);
            process.setOperStatusId(AchStatusEnum.SecondAudit.getId());
            List<SrmFundProcess> processList = this.funProcessBiz.searchFundProcesses(process);
            if (processList.size() > 0) {
                model.addAttribute("financeUserName", processList.get(0).getOperateUserName());
            }
            //经费操作过程（单位审核通过）
            process.setOperStatusId(AchStatusEnum.FirstAudit.getId());
            //审核人
            String localUserName = fundProcessUserName(process);

            if (detailExt.getMoney() != null && !detailExt.getMoney().equals("")) {
                //将预算报销金额转换为大写
                String CNmoney = NumTrans.NumberToCN(new BigDecimal(detailExt.getMoney().doubleValue() * 10000));
                model.addAttribute("CNmoney", CNmoney);
            }
            if (detailExt.getRealmoney() != null && !detailExt.getRealmoney().equals("")) {
                //将实际报销金额转换为大写
                String CNbudgetMoney = NumTrans.NumberToCN(new BigDecimal(detailExt.getRealmoney().doubleValue() * 10000));
                model.addAttribute("CNbudgetMoney", CNbudgetMoney);
            }
            //将已报销金额转换为大写
            //String CNreimburseMoney = NumTrans.NumberToCN(new BigDecimal(reimburseMoney.doubleValue()*10000));

            //  model.addAttribute("CNreimburseMoney",CNreimburseMoney);
            model.addAttribute("localUserName", localUserName);
            model.addAttribute("reimburseMoney", reimburseMoney);
            model.addAttribute("detailExt", detailExt);
            model.addAttribute("budgetDetail", budgetDetail);
            return "/srm/payment/print";
        }
        return GlobalConstant.OPRE_FAIL;
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

    /**
     * 根据方案子项编号查询预算、已报销金额
     *
     * @param itemFlow
     * @param fundFlow
     * @return
     */
    @RequestMapping("/schemeFundMessage")
    public
    @ResponseBody
    Map<String, Object> schemeFundMessage(String fundDetailFlow) {
        SrmProjFundDetail fundDtl = paymentBiz.getDetailByDetailFlow(fundDetailFlow);
        //预算金额
        BigDecimal budgetMoney = new BigDecimal("0.0000");
        SrmProjFundDetail fundDtlTemp = new SrmProjFundDetail();
        //已报销金额
        fundDtlTemp.setFundFlow(fundDtl.getFundFlow());
        fundDtlTemp.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
        fundDtlTemp.setOperStatusId(AchStatusEnum.SecondAudit.getId());
        if (InitConfig.getSysCfg("srm_local_type").equals("Y")) {
            fundDtlTemp.setOperStatusId(AchStatusEnum.FifthAudit.getId());
        }
        List<SrmProjFundDetail> fundReimburseDtlList = fundInfoDetailBiz.searchFundDetail(fundDtlTemp);
        BigDecimal count = new BigDecimal("0.0000");
        BigDecimal allocateMoney = new BigDecimal("0.0000");
        BigDecimal matchingMoney = new BigDecimal("0.0000");
        for (SrmProjFundDetail fundReimburse : fundReimburseDtlList) {
            if(fundReimburse.getItemFlow().equals(fundDtl.getItemFlow())){
                BigDecimal realMoney = new BigDecimal("0.0000");
                if (fundReimburse.getRealmoney() != null) {
                    realMoney = fundReimburse.getRealmoney();
                }
                if (ProjFundAccountsTypeEnum.Allocate.getId().equals(fundReimburse.getRealityTypeId())) {
                    allocateMoney = allocateMoney.add(realMoney);
                }
                if (ProjFundAccountsTypeEnum.Matching.getId().equals(fundReimburse.getRealityTypeId())) {
                    matchingMoney = matchingMoney.add(realMoney);
                }
                count = count.add(realMoney);
            }
            /*else if (StringUtil.isNotBlank(fundReimburse.getItemId()) && fundReimburse.getItemId().equals(fundDtl.getItemId())) {
                BigDecimal realMoney = new BigDecimal("0.0000");
                if (fundReimburse.getRealmoney() != null) {
                    realMoney = fundReimburse.getRealmoney();
                }
                if (fundReimburse.getAllocateMoney() != null) {
                    allocateMoney = allocateMoney.add(fundReimburse.getAllocateMoney());
                }
                if (fundReimburse.getMatchingMoney() != null) {
                    matchingMoney = matchingMoney.add(fundReimburse.getMatchingMoney());
                }
                count = count.add(realMoney);
            }*/
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("realMoney", count);
        map.put("allocateMoney", allocateMoney);
        map.put("matchingMoney", matchingMoney);
        map.put("budgetMoney", fundDtl.getMoney());
        return map;
        //return JSON.toJSON(map).toString();
    }
}
