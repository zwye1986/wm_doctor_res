package com.pinde.sci.ctrl.srm;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.pub.ProjCategroyEnum;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.ProjFundTypeEnum;
import com.pinde.sci.enums.srm.ProjStageEnum;
import com.pinde.sci.form.srm.FundDetailCalculateForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.FundInfoExt;
import com.pinde.sci.model.srm.FundSum;
import com.pinde.sci.model.srm.PubProjExt;
import com.pinde.sci.model.srm.UserSurplusInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目经费
 */
@Controller
@RequestMapping("/srm/fund")
public class FundController extends GeneralController {
    private static Logger logger = LoggerFactory.getLogger(FundController.class);
    @Autowired
    private IFundBiz fundBiz;
    @Autowired
    private IPubProjBiz pubProjBiz;
    @Autowired
    private IFundInfoDetailBiz fundInfoDetailBiz;
    @Autowired
    private IFundSchemeBiz schemeBiz;
    @Autowired
    private IFundInfoBiz fundInfoBiz;
    @Autowired
    private IFundSchemeDetailBiz detailBiz;
    /**
     * 到账列表
     *
     * @param proj
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/accountsList/{projListScope}")
    public String accountsList(PubProjExt projExt, Model model, @PathVariable(value = "projListScope") String projListScope, Integer currentPage, Boolean isAudit, HttpServletRequest request) {
       if(isAudit == null){
           isAudit = false;
       }
        setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
        //setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, ProjCategroyEnum.Ky.getId());//暂时加上的

        SysUser currUser = GlobalContext.getCurrentUser();
        if (GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL.equals(projListScope)) {
            projExt.setApplyUserFlow(currUser.getUserFlow());
        }
        projExt.setApplyOrgFlow(currUser.getOrgFlow());

        List<String> statuIds = new ArrayList<String>();
        statuIds.add(AchStatusEnum.FirstAudit.getId());
        //statuIds.add(AchStatusEnum.SecondAudit.getId());
        projExt.setStatusids(statuIds);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<PubProjExt> PubProjExtList = this.fundInfoBiz.searchPubProjExtList(projExt);

        model.addAttribute("PubProjExtList", PubProjExtList);


        return "srm/fund/accountsList";
    }

    /**
     * 管理费扣除一级
     */
    @RequestMapping(value = "/feeList/{projListScope}")
    public String feeList(PubProj proj, Model model, @PathVariable(value = "projListScope") String projListScope, Integer currentPage,Boolean isAudit,HttpServletRequest request) {
        if(isAudit == null){
            isAudit = false;
        }
        PubProjExt projExt =new PubProjExt();
        setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
      //  setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, ProjCategroyEnum.Ky.getId());//暂时加上的
        SysUser currUser = GlobalContext.getCurrentUser();
        if (GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL.equals(projListScope)) {
            projExt.setApplyUserFlow(currUser.getUserFlow());
        }

        projExt.setApplyOrgFlow(currUser.getOrgFlow());

        if(StringUtil.isNotBlank(proj.getProjName())){
            projExt.setProjName(proj.getProjName());
        }
        //projExt.setApplyUserFlow(currUser.getUserFlow());
        List<String> statuIds = new ArrayList<String>();
        statuIds.add(AchStatusEnum.FirstAudit.getId());
        // statuIds.add(AchStatusEnum.SecondAudit.getId());
        projExt.setStatusids(statuIds);
        //  projExt.setProjCategoryId(ProjCategroyEnum.Ky.getId());
        PageHelper.startPage(currentPage,getPageSize(request));
        List<PubProjExt> PubProjExtList = this.fundInfoBiz.searchPubProjExtList(projExt);
        model.addAttribute("PubProjExtList", PubProjExtList);

        return "srm/fund/feeList";
    }

    /**
     * 经费列表
     *
     * @param proj
     * @param flag
     * @param model
     * @param currentPage
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/list/{projListScope}")
    public String list(PubProjExt projExt, @PathVariable(value = "projListScope") String projListScope, Model model, Integer currentPage,HttpServletRequest request,String startTime,String endTime) {
        setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, projListScope);
        SysUser currUser = GlobalContext.getCurrentUser();

        List<String> projStageIdNotInList =null;
        if(StringUtil.isBlank(projExt.getProjStatusId())){
            if(ProjStageEnum.Approve.getId().equals(projExt.getProjStageId())){
                projStageIdNotInList = new ArrayList<>();
                //非申报阶段
                projStageIdNotInList.add(ProjStageEnum.Apply.getId());
            }else if(ProjStageEnum.Contract.getId().equals(projExt.getProjStageId())){
                projStageIdNotInList = new ArrayList<>();
                //非申报、立项阶段
                projStageIdNotInList.add(ProjStageEnum.Apply.getId());
                projStageIdNotInList.add(ProjStageEnum.Approve.getId());
            }else if(ProjStageEnum.Schedule.getId().equals(projExt.getProjStageId())){
                projStageIdNotInList = new ArrayList<>();
                //非申报、立项、合同阶段
                projStageIdNotInList.add(ProjStageEnum.Apply.getId());
                projStageIdNotInList.add(ProjStageEnum.Approve.getId());
                projStageIdNotInList.add(ProjStageEnum.Contract.getId());
            }else if(ProjStageEnum.Complete.getId().equals(projExt.getProjStageId())){
                projStageIdNotInList = new ArrayList<>();
                //非申报、立项、合同、进展阶段
                projStageIdNotInList.add(ProjStageEnum.Apply.getId());
                projStageIdNotInList.add(ProjStageEnum.Approve.getId());
                projStageIdNotInList.add(ProjStageEnum.Contract.getId());
                projStageIdNotInList.add(ProjStageEnum.Schedule.getId());
            }
            //清除状态
            projExt.setProjStageId("");
            projExt.setProjStageIds(projStageIdNotInList);
        }
       /* if (GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL.equals(projListScope)) {
            proj.setApplyUserFlow(currUser.getUserFlow());
        } else if (GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(projListScope)) {
            proj.setApplyOrgFlow(currUser.getOrgFlow());
        }
        List<FundInfoExt> funds = this.fundBiz.getList(proj);
        FundSum fundSum = this.fundBiz.getFundSum(funds);//经费总计
        model.addAttribute("funds", funds);
        model.addAttribute("fundSum", fundSum);*/
        if (GlobalConstant.PROJ_STATUS_SCOPE_PERSONAL.equals(projListScope)) {
            projExt.setApplyUserFlow(currUser.getUserFlow());
        } else if (GlobalConstant.PROJ_STATUS_SCOPE_LOCAL.equals(projListScope)) {
            projExt.setApplyOrgFlow(currUser.getOrgFlow());
        }
        List<String> statuIds = new ArrayList<String>();
        statuIds.add(AchStatusEnum.FirstAudit.getId());
        statuIds.add(AchStatusEnum.SecondAudit.getId());
        projExt.setStatusids(statuIds);
        List<PubProjExt> allPubProjExtList = this.fundInfoBiz.searchPubProjExtList(projExt);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<PubProjExt> pubProjExtList = this.fundInfoBiz.searchPubProjExtList(projExt);
        SrmProjFundDetail fundDetail = new SrmProjFundDetail();
        fundDetail.setRecordStatus("Y");
        fundDetail.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
        fundDetail.setOperStatusId(AchStatusEnum.SecondAudit.getId());
        if(InitConfig.getSysCfg("srm_local_type").equals("Y")){//徐州中心医院
            fundDetail.setOperStatusId(AchStatusEnum.FifthAudit.getId());
        }
        List<SrmProjFundDetail> detailList = fundInfoDetailBiz.searchFundDetail(fundDetail,null,null,startTime,endTime,null,null);
        for(PubProjExt pubProjExt : pubProjExtList){
            BigDecimal realMoney = new BigDecimal("0");
            for(SrmProjFundDetail detail : detailList){
                if(detail.getFundFlow().equals(pubProjExt.getProjFundInfo().getFundFlow())){
                    realMoney = realMoney.add(detail.getRealmoney()!=null?detail.getRealmoney():new BigDecimal(0));
                }
            }
            pubProjExt.getProjFundInfo().setYetPaymentAmount(realMoney);
        }
        FundSum fundSum = this.fundBiz.getFundSum(pubProjExtList);//当前页经费总计
        FundSum allFundSum = this.fundBiz.getFundSum(allPubProjExtList);//当前页经费总计
        model.addAttribute("fundSum", fundSum);
        model.addAttribute("allFundSum", allFundSum);
        model.addAttribute("pubProjExtList", pubProjExtList);



        return "srm/fund/fundList";
    }

    /**
     * 到账明细
     *
     * @param fundFlow 经费流水号
     * @param model
     * @return
     */
    @RequestMapping(value = "/details")
    public String details(String fundFlow, String projFlow, Model model) {
        if (StringUtil.isNotBlank(fundFlow)) {
            List<SrmProjFundDetail> details = this.fundBiz.getDetails(fundFlow);
            model.addAttribute("details", details);
            PubProj proj = this.pubProjBiz.readProject(projFlow);
            model.addAttribute("proj", proj);

        }
        return "srm/fund/accountsDetails";
    }

    /**
     * 管理费扣除二级
     */
    @RequestMapping(value = "/feeDetails")
    public String feeDetails(String fundFlow, String projFlow, String view,Model model) {
        model.addAttribute("view",view);
        if (StringUtil.isNotBlank(fundFlow)) {
            SrmProjFundInfo fund = fundBiz.getFund(fundFlow);
            model.addAttribute("fund",fund);
            List<SrmProjFundDetail> details = this.fundBiz.getFeeDetail(fundFlow);
            model.addAttribute("details", details);
            //已经扣除管理费
            BigDecimal deductFee = new BigDecimal("0.0000");
            for(SrmProjFundDetail fundDetail : details){
                if(fundDetail.getOperStatusId().equals(AchStatusEnum.SecondAudit.getId())){
                    if(fundDetail.getMoney() == null){
                        fundDetail.setMoney(new BigDecimal("0"));
                    }
                    deductFee = deductFee.add(fundDetail.getMoney());
                    model.addAttribute("deductFee",deductFee);
                }
            }
            PubProj proj = this.pubProjBiz.readProject(projFlow);
            model.addAttribute("proj", proj);
            SrmProjFundDetail detail = new SrmProjFundDetail();
            detail.setFundFlow(fundFlow);
            detail.setFundTypeId(ProjFundTypeEnum.Budget.getId());
            detail.setItemId("guanlifei");
            List<SrmProjFundDetail> detailList = fundInfoDetailBiz.searchFundDetail(detail);
            if(detailList != null && detailList.size() >0){
                model.addAttribute("manageFee",detailList.get(0));
            }
            /*if (fundInfoList.size() > 0) {
                String schemeFlow = fundInfoList.get(0).getSchemeFlow();
                if (StringUtil.isNotBlank(schemeFlow)) {
                    //查询该项目经费的预算方案信息及其预算项信息
                    List<SrmFundSchemeDetail> schemeDetailList = paymentBiz.getSchemeDetailBySchemeFlow(schemeFlow);
                    for(SrmFundSchemeDetail schemeDetail:schemeDetailList){
                        //选择预算方案项id为'guanlifei'的方案子项
                        if(schemeDetail.getItemId().equals("guanlifei")){
                            model.addAttribute("schemeDetail", schemeDetail);
                        }
                    }
                }
            }*/
        }
        return "srm/fund/feeDetails";
    }

    /**
     * 财务审核
     * ****
     */
    @RequestMapping(value = "/auditFundDetail")
    @ResponseBody
    public String auditFundDetail(SrmProjFundDetail srmProjFundDetail, SrmFundProcess pro,String rejectreason) {
        if (srmProjFundDetail != null && StringUtil.isNotBlank(srmProjFundDetail.getOperStatusId())) {
            srmProjFundDetail.setOperStatusName(AchStatusEnum.getNameById(srmProjFundDetail.getOperStatusId()));
        }
        if(StringUtil.isNotBlank(rejectreason)){
            pro.setContent(rejectreason);
        }
        this.fundBiz.editFundDetail(srmProjFundDetail, pro);
        if(srmProjFundDetail.getOperStatusId().equals(AchStatusEnum.SecondAudit.getId())){
                 /*审核通过更新项目经费*/
            this.fundBiz.saveDetail(srmProjFundDetail);
        }
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    /**
     * 经费到账导入
     * @return
     */
    @RequestMapping(value="/importIncomes")
    public String importIncomes(){
        return "srm/fund/import/importIncomes";
    }

    /**
     * 经费到账导入
     * @param file
     * @return
     */
    @RequestMapping(value="/importIncomesFromExcel")
    @ResponseBody
    public String importUsersFromExcel(MultipartFile file){
        if(file.getSize() > 0){
            try{
                int result = fundBiz.importIncomesFromExcel(file);
                if(GlobalConstant.ZERO_LINE != result){
                    return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
                }else{
                    return GlobalConstant.UPLOAD_FAIL;
                }
            }catch(RuntimeException re){
                re.printStackTrace();
                return re.getMessage();
            }
        }
        return GlobalConstant.UPLOAD_FAIL;
    }

    /**
     * 保存到账明细
     *
     * @param details
     * @return
     */
    @RequestMapping(value = "/saveDetail")
    public
    @ResponseBody
    String saveDetail(@RequestBody List<SrmProjFundDetail> details) {
        if (details != null) {
            for (int i = 0; i < details.size(); i++) {
                this.fundBiz.saveDetail(details.get(i));
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = "/delDetail")
    @ResponseBody
    public String delDetail(String fundDetailFlow) {
        this.fundBiz.delDetailByFundDetailFlow(fundDetailFlow);
        return GlobalConstant.DELETE_SUCCESSED;
    }

    /**
     * 经费管理查看
     *
     * @param fundFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/getDetail")
    public String getDetail(String fundFlow,String startTime,String endTime, Model model) {
        SrmProjFundInfo fundInfo = this.fundBiz.getFund(fundFlow);
        PubProj proj = this.pubProjBiz.readProject(fundInfo.getProjFlow());
        List<FundDetailCalculateForm> calculateFormList = this.fundBiz.calculateFundDetail(fundFlow,startTime,endTime);
        model.addAttribute("fundInfo", fundInfo);
        model.addAttribute("proj", proj);
        model.addAttribute("calculateFormList", calculateFormList);
        return "srm/fund/fundDetail";
    }
    @RequestMapping("/paymentList")
    public String paymentList(String fundDetailFlow,String type,Model model){
        if(StringUtil.isNotBlank(fundDetailFlow)) {
            SrmProjFundDetail detail = fundBiz.readSrmProjFundDetail(fundDetailFlow);
            SrmProjFundDetail detailTemp = new SrmProjFundDetail();
            detailTemp.setFundFlow(detail.getFundFlow());
            detailTemp.setItemFlow(detail.getItemFlow());
            detailTemp.setItemId(detail.getItemId());
            detailTemp.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
            if(StringUtil.isNotBlank(type)) {
                detailTemp.setRealityTypeId(type);
            }
            List<SrmProjFundDetail> detailList= fundBiz.paymentDetails(detailTemp);
            model.addAttribute("detailList",detailList);
            model.addAttribute("detail",detail);
        }
        return "srm/fund/paymentList";
    }
}
