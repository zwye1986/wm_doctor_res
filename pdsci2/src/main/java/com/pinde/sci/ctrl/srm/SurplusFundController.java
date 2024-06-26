package com.pinde.sci.ctrl.srm;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.srm.*;
import com.pinde.sci.biz.sys.ISysUserRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.srm.AchStatusEnum;
import com.pinde.sci.enums.srm.ProjFundTypeEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.srm.BalanceFundCollectForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.PersonalFundInfoExt;
import com.pinde.sci.model.srm.PubProjExt;
import com.pinde.sci.model.srm.UserSurplusInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-06-21.
 */
@Controller
@RequestMapping("/srm/surplus")
public class SurplusFundController extends GeneralController {
    @Autowired
    private IFundBiz fundBiz;
    @Autowired
    private IPubProjBiz pubProjBiz;
    @Autowired
    private IFundInfoDetailBiz detailBiz;
    @Autowired
    private IFundInfoBiz fundInfoBiz;
    @Autowired
    private IPaymentBiz paymentBiz;
    @Autowired
    private IUserBiz userBiz;
    @RequestMapping(value = "/surplusList/{projListScope}")
    public String surplusList(Model model, String userName, String deptFlow, @PathVariable(value = "projListScope") String projListScope, Integer currentPage, HttpServletRequest request) {
        setSessionAttribute("projListScope",projListScope);
        Map<String,String> map = new HashMap<>();
        map.put("userName",userName);
        map.put("deptFlow",deptFlow);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<UserSurplusInfo> surplusInfoList = fundBiz.surplusInfoList(map);
        model.addAttribute("surplusInfoList",surplusInfoList);
        return "srm/fund/surplusList";
    }

    @RequestMapping(value = "/surplusProjInfo/{projListScope}")
    public String surplusProjInfo(Model model,PubProjExt projExt , @PathVariable(value = "projListScope") String projListScope, Integer currentPage, HttpServletRequest request) {
        setSessionAttribute("projListScope",projListScope);

        PageHelper.startPage(currentPage,getPageSize(request));
        List<PubProjExt> pubProjExtList = fundBiz.surplusProjInfoList(projExt);
        model.addAttribute("pubProjExtList",pubProjExtList);
        return "srm/fund/surplusProjList";
    }

    /**
     * 个人经费查询
     * @param model
     * @param userName
     * @param deptFlow
     * @param projListScope
     * @param currentPage
     * @param request
     * @return
     */
    @RequestMapping(value = "/personalList/{projListScope}")
    public String personalList(Model model, String userName, String deptFlow, @PathVariable(value = "projListScope") String projListScope, Integer currentPage, HttpServletRequest request) {
        setSessionAttribute("projListScope",projListScope);
        Map<String,String> map = new HashMap<>();
        map.put("userName",userName);
        map.put("deptFlow",deptFlow);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<PersonalFundInfoExt> fundInfoList = fundBiz.personalFundList(map);
        model.addAttribute("fundInfoList",fundInfoList);
        return "srm/fund/personalFundList";
    }
    @RequestMapping(value = "/hospitalList/{projListScope}")
    public String hospitalList(Model model, @PathVariable(value = "projListScope") String projListScope) {
        setSessionAttribute("projListScope",projListScope);
        SrmProjFundInfo fundInfoTemp = new SrmProjFundInfo();
        fundInfoTemp.setProjFlow(GlobalContext.getCurrentUser().getOrgFlow());
        List<SrmProjFundInfo> fundInfoList = fundInfoBiz.searchFundInfo(fundInfoTemp);
        model.addAttribute("fundInfoList",fundInfoList);
        return "srm/fund/hospitalFundList";
    }


    /**
     * 个人经费管理
     * @return
     */
    @RequestMapping("/personalFund/{projListScope}")
    public String personalFund(Model model,String userFlow, @PathVariable(value = "projListScope") String projListScope){
        setSessionAttribute("projListScope",projListScope);
        if(GlobalConstant.USER_LIST_PERSONAL.equals(projListScope)){
            userFlow = GlobalContext.getCurrentUser().getUserFlow();
        }
        if(StringUtil.isNotBlank(userFlow)){
            SrmProjFundInfo fundInfoTemp = new SrmProjFundInfo();
            fundInfoTemp.setProjFlow(userFlow);//个人科研经费项目流水号 使用 userFlow
            List<SrmProjFundInfo> personFundInfos = fundInfoBiz.searchFundInfo(fundInfoTemp);
            if(personFundInfos != null && personFundInfos.size() > 0){
                SrmProjFundInfo personFundInfo = personFundInfos.get(0);
                model.addAttribute("personFundInfo",personFundInfo);
                SrmProjFundDetail detailTemp = new SrmProjFundDetail();
                detailTemp.setFundFlow(personFundInfo.getFundFlow());
                detailTemp.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
                List<SrmProjFundDetail> reimburseDetailList = detailBiz.searchFundDetail(detailTemp);
                model.addAttribute("reimburseDetailList",reimburseDetailList);
            }
            SysUser user = userBiz.findByFlow(userFlow);
            model.addAttribute("user",user);
        }
        return "srm/fund/personalAccount";
    }
    /**
     * 医院经费管理
     * @return
     */
    @RequestMapping("/hospitalFund/{projListScope}")
    public String hospitalFund(Model model,String fundFlow, @PathVariable(value = "projListScope") String projListScope){
        setSessionAttribute("projListScope",projListScope);

        if(StringUtil.isNotBlank(fundFlow)){
            SrmProjFundInfo fundInfoTemp = new SrmProjFundInfo();
            fundInfoTemp.setFundFlow(fundFlow);
            List<SrmProjFundInfo> hospitalFundInfos = fundInfoBiz.searchFundInfo(fundInfoTemp);
            if(hospitalFundInfos != null && hospitalFundInfos.size() > 0){
                SrmProjFundInfo hospitalFundInfo = hospitalFundInfos.get(0);
                model.addAttribute("hospitalFundInfo",hospitalFundInfo);
                SrmProjFundDetail detailTemp = new SrmProjFundDetail();
                detailTemp.setFundFlow(hospitalFundInfo.getFundFlow());
                detailTemp.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
                List<SrmProjFundDetail> reimburseDetailList = detailBiz.searchFundDetail(detailTemp);
                model.addAttribute("reimburseDetailList",reimburseDetailList);
            }
        }
        return "srm/fund/hospitalAccount";
    }
    /**
     * 添加个人经费报销
     * @return
     */
    @RequestMapping("/addReimburse")
    public String addReimburse(Model model,String fundFlow){
        if(StringUtil.isNotBlank(fundFlow)){
                SrmProjFundInfo fundInfo = fundInfoBiz.getFundInfoByFlow(fundFlow);
                model.addAttribute("fundInfo",fundInfo);
        }
        return "srm/payment/surplusReimburse";
    }
    /**
     * 保存个人经费报销
     * @return
     */
    @RequestMapping("/saveReimburse")
    @ResponseBody
    public String saveReimburse(SrmProjFundDetail detail){
        if(StringUtil.isNotBlank(detail.getFundFlow()) && detail.getRealmoney() != null) {
            detail.setRealmoney(detail.getRealmoney().divide(new BigDecimal(10000)));
            SrmFundProcess process=new SrmFundProcess();
            process.setOperStatusId(AchStatusEnum.SecondAudit.getId());
            process.setOperStatusName(AchStatusEnum.SecondAudit.getName());
            process.setFundFlow(detail.getFundFlow());
            process.setOperateUserFlow(detail.getFundFlow());
            process.setOperateUserName(detail.getFundFlow());
            process.setOperateTime(DateUtil.getCurrDateTime());
            process.setFundProcessFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(process, true);

            detail.setFundTypeId(ProjFundTypeEnum.Reimburse.getId());
            detail.setFundTypeName(ProjFundTypeEnum.Reimburse.getName());
            detail.setOperStatusId(AchStatusEnum.SecondAudit.getId());
            detail.setOperStatusName(AchStatusEnum.SecondAudit.getName());
            paymentBiz.updateDetailStatus(detail,process);
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    /**
     * 项目剩余经费管理
     * @return
     */
    @RequestMapping("/surplusFund")
    public String surplusFund(Model model,String userFlow){
        if(StringUtil.isNotBlank(userFlow)){
            PubProjExt projExt = new PubProjExt();
            projExt.setApplyUserFlow(userFlow);
            projExt.setProjStageId("Archive");
            projExt.setProjStatusId("Archive");
            List<String> statuIds = new ArrayList<String>();
            statuIds.add(AchStatusEnum.FirstAudit.getId());
            //statuIds.add(AchStatusEnum.SecondAudit.getId());
            projExt.setStatusids(statuIds);
            List<PubProjExt> pubProjExtList = this.fundInfoBiz.searchPubProjExtList(projExt);
            model.addAttribute("pubProjExtList",pubProjExtList);
            SrmProjFundInfo fundInfoTemp = new SrmProjFundInfo();
            fundInfoTemp.setProjFlow(userFlow);//个人科研经费项目流水号 使用 userFlow
            List<SrmProjFundInfo> personFundInfos = fundInfoBiz.searchFundInfo(fundInfoTemp);
            if(personFundInfos != null && personFundInfos.size() > 0){
                SrmProjFundInfo personFundInfo = personFundInfos.get(0);
                model.addAttribute("personFundInfo",personFundInfo);
            }
            if(pubProjExtList != null && pubProjExtList.size() > 0){
                List<String> fundFlowList = new ArrayList<>();
                for(PubProjExt pubProjExt : pubProjExtList){
                    if(pubProjExt.getProjFundInfo() != null) {
                        if (!fundFlowList.contains(pubProjExt.getProjFundInfo().getFundFlow())) {
                            fundFlowList.add(pubProjExt.getProjFundInfo().getFundFlow());
                        }
                    }
                }

                SrmProjFundDetail detailTemp = new SrmProjFundDetail();
                detailTemp.setFundTypeId(ProjFundTypeEnum.Surplus.getId());
                List<SrmProjFundDetail> surplusDetailList = detailBiz.searchFundDetail(detailTemp,null,fundFlowList,null,null,null,null);
                model.addAttribute("surplusDetailList",surplusDetailList);
            }
            SysUser user = userBiz.findByFlow(userFlow);
            model.addAttribute("user",user);
        }
        return "srm/fund/surplusEdit";
    }

    /**
     * 经费结余记录查询
     * @return
     */
    @RequestMapping("/surplusHistory")
    public String surplusHistory(Model model,String fundFlow){
        if(StringUtil.isNotBlank(fundFlow)){
                SrmProjFundDetail detailTemp = new SrmProjFundDetail();
                detailTemp.setFundFlow(fundFlow);
                detailTemp.setFundTypeId(ProjFundTypeEnum.Surplus.getId());
                List<SrmProjFundDetail> surplusDetailList = detailBiz.searchFundDetail(detailTemp);
                model.addAttribute("surplusDetailList",surplusDetailList);
            }
        return "srm/fund/surplusHistory";
    }

    /**
     * 结余经费
     * @return
     */
    @RequestMapping("/surplusBalance")
    @ResponseBody
    public String surplusBalance(@RequestBody String[] fundFlows){

        if(fundFlows != null && fundFlows.length>0) {
            for (int i = 0; i < fundFlows.length; i++) {
                String fundFlow = fundFlows[i];
                SysUser currentUser = GlobalContext.getCurrentUser();
                if (StringUtil.isNotBlank(fundFlow)) {
                    SrmProjFundInfo fundInfo = fundInfoBiz.getFundInfoByFlow(fundFlow);
                    PubProj pubProj = pubProjBiz.readProject(fundInfo.getProjFlow());

                    SrmProjFundInfo fundInfoTemp = new SrmProjFundInfo();
                    fundInfoTemp.setProjFlow(pubProj.getApplyUserFlow());//个人科研经费项目流水号 使用 userFlow

                    List<SrmProjFundInfo> personFundInfos = fundInfoBiz.searchFundInfo(fundInfoTemp);
                    SrmProjFundInfo personFundInfo;
                    if (personFundInfos != null && personFundInfos.size() > 0) {
                        personFundInfo = personFundInfos.get(0);
                    } else {
                        personFundInfo = new SrmProjFundInfo();
                    }

                    List<SrmProjFundInfo> hospitalFundInfos = new ArrayList<>();
                    if (pubProj != null && StringUtil.isNotBlank(pubProj.getProjTypeId())) {
                        if ("jsszyy.kyxm".equals(pubProj.getProjTypeId())) {
                            fundInfoTemp.setAccountTypeId("2");
                        } else if ("jsszyy.ywxm".equals(pubProj.getProjTypeId())) {
                            fundInfoTemp.setAccountTypeId("3");
                        }
                        fundInfoTemp.setProjFlow(currentUser.getOrgFlow());//医院科研经费 项目流水号 使用 orgFlow
                        hospitalFundInfos = fundInfoBiz.searchFundInfo(fundInfoTemp);
                    }
                    SrmProjFundInfo hospitalFundInfo;
                    if (hospitalFundInfos != null && hospitalFundInfos.size() > 0) {
                        hospitalFundInfo = hospitalFundInfos.get(0);
                    } else {
                        hospitalFundInfo = new SrmProjFundInfo();
                    }
                    hospitalFundInfo.setProjFlow(currentUser.getOrgFlow());
                    hospitalFundInfo.setProjName(currentUser.getOrgName());
                    SysUser user = userBiz.findByFlow(pubProj.getApplyUserFlow());
                    if (null != user) {
                        personFundInfo.setProjFlow(user.getUserFlow());
                        personFundInfo.setProjName(user.getUserName());
                        if (StringUtil.isNotBlank(user.getAccountNo())) {
                            personFundInfo.setAccountNo(user.getAccountNo());
                        }
                    }
                    personFundInfo.setAccountTypeId("1");
                    personFundInfo.setAccountTypeName("个人科研账户");
            /*医院有两个账号，K001和K001-1，
            功能与个人经费查询一样，院外项目的结转按照10%到K001账号，90%到个人账号的比例结转，
            院内项目结转按照80%到K001-1账号，20%到个人账号的比例结转*/
                    BigDecimal hospitalMoney = new BigDecimal(0);
                    BigDecimal personMoney = new BigDecimal(0);
                    if ("jsszyy.kyxm".equals(pubProj.getProjTypeId())) {
                        hospitalMoney = fundInfo.getRealityBalance().multiply(new BigDecimal("0.8"));
                        personMoney = fundInfo.getRealityBalance().multiply(new BigDecimal("0.2"));

                        hospitalFundInfo.setAccountTypeId("2");
                        hospitalFundInfo.setAccountTypeName("院内项目账号");
                        hospitalFundInfo.setAccountNo("K001-1");

                        hospitalFundInfo.setRealityAmount((hospitalFundInfo.getRealityAmount() == null ? new BigDecimal(0) : hospitalFundInfo.getRealityAmount()).add(hospitalMoney));
                        hospitalFundInfo.setRealityBalance(hospitalFundInfo.getRealityAmount().subtract(hospitalFundInfo.getYetPaymentAmount() == null ? new BigDecimal(0) : hospitalFundInfo.getYetPaymentAmount()));

                        personFundInfo.setRealityAmount((personFundInfo.getRealityAmount() == null ? new BigDecimal(0) : personFundInfo.getRealityAmount()).add(personMoney));
                        personFundInfo.setRealityBalance(personFundInfo.getRealityAmount().subtract(personFundInfo.getYetPaymentAmount() == null ? new BigDecimal(0) : personFundInfo.getYetPaymentAmount()));

                    } else if ("jsszyy.ywxm".equals(pubProj.getProjTypeId())) {
                        hospitalMoney = fundInfo.getRealityBalance().multiply(new BigDecimal("0.1"));
                        personMoney = fundInfo.getRealityBalance().multiply(new BigDecimal("0.9"));

                        hospitalFundInfo.setAccountTypeId("3");
                        hospitalFundInfo.setAccountTypeName("院外项目账号");
                        hospitalFundInfo.setAccountNo("K001");

                        hospitalFundInfo.setRealityAmount((hospitalFundInfo.getRealityAmount() == null ? new BigDecimal(0) : hospitalFundInfo.getRealityAmount()).add(hospitalMoney));
                        hospitalFundInfo.setRealityBalance(hospitalFundInfo.getRealityAmount().subtract(hospitalFundInfo.getYetPaymentAmount() == null ? new BigDecimal(0) : hospitalFundInfo.getYetPaymentAmount()));

                        personFundInfo.setRealityAmount((personFundInfo.getRealityAmount() == null ? new BigDecimal(0) : personFundInfo.getRealityAmount()).add(personMoney));
                        personFundInfo.setRealityBalance(personFundInfo.getRealityAmount().subtract(personFundInfo.getYetPaymentAmount() == null ? new BigDecimal(0) : personFundInfo.getYetPaymentAmount()));

                    }


                    SrmProjFundDetail detail = new SrmProjFundDetail();

                    detail.setFundTypeId(ProjFundTypeEnum.Surplus.getId());
                    detail.setFundTypeName(ProjFundTypeEnum.Surplus.getName());
                    detail.setFundFlow(fundInfo.getFundFlow());
                    detail.setReimburseId(pubProj.getProjFlow());
                    detail.setReimburseName(pubProj.getProjName());
                    detail.setFundOperator(currentUser.getUserName());
                    detail.setOperStatusId(AchStatusEnum.SecondAudit.getId());
                    detail.setOperStatusId(AchStatusEnum.SecondAudit.getName());
                    detail.setFundOperator(currentUser.getUserName());

                    detail.setContent("结余至个人账户");
                    detail.setMoney(personMoney);
                    detail.setRealmoney(personMoney);

                    SrmFundProcess process = new SrmFundProcess();
                    process.setOperStatusId(ProjFundTypeEnum.Surplus.getId());
                    process.setOperStatusName(ProjFundTypeEnum.Surplus.getName());
                    process.setOperateUserFlow(currentUser.getUserFlow());
                    process.setOperateUserName(currentUser.getUserName());
                    process.setFundFlow(fundInfo.getFundFlow());
                    process.setContent(pubProj.getProjName() + "，剩余经费汇入个人科研账户。");
                    fundInfoBiz.saveSurplusInfo(personFundInfo, detail, process);

                    detail.setFundFlow(fundInfo.getFundFlow());
                    detail.setFundDetailFlow("");
                    detail.setContent("结余至" + hospitalFundInfo.getAccountTypeName());
                    detail.setMoney(hospitalMoney);

                    process.setFundFlow(fundInfo.getFundFlow());
                    process.setContent(pubProj.getProjName() + "，剩余经费汇入" + hospitalFundInfo.getAccountTypeName() + "。");
                    fundInfoBiz.saveSurplusInfo(hospitalFundInfo, detail, process);

                    fundInfo.setSurplusFund(fundInfo.getSurplusFund().add(fundInfo.getRealityBalance()));
                    fundInfo.setRealityBalance(new BigDecimal(0));
                    fundInfoBiz.updateFundInfo(fundInfo);
                }
            }
        }
        return GlobalConstant.OPRE_SUCCESSED;
    }
    @RequestMapping("/exportSurplusDetails")
    public void exportSurplusDetails(PubProjExt projExt, HttpServletResponse response) throws Exception{
        List<PubProjExt> pubProjExtList = fundBiz.surplusProjInfoList(projExt);
        Map<String,List<String>> userProjFundFlowsMap = new HashMap<>();
        for(PubProjExt pubProjExt : pubProjExtList){
            if(pubProjExt.getProjFundInfo() != null){
                if(userProjFundFlowsMap.containsKey(pubProjExt.getApplyUserFlow())){
                    userProjFundFlowsMap.get(pubProjExt.getApplyUserFlow()).add(pubProjExt.getProjFundInfo().getFundFlow());
                }else{
                    List<String> fundFlows = new ArrayList<>();
                    fundFlows.add(pubProjExt.getProjFundInfo().getFundFlow());
                    userProjFundFlowsMap.put(pubProjExt.getApplyUserFlow(),fundFlows);
                }
            }
        }
        List<BalanceFundCollectForm> collectFormList = new ArrayList<>();
        for (Object obj : userProjFundFlowsMap.keySet()) {
            String key= (String) obj;
            List<String> fundFlowList = userProjFundFlowsMap.get(key);
            BalanceFundCollectForm fundCollectForm = fundBiz.getFundCollectForm(fundFlowList);
            if(fundCollectForm != null) {
                SysUser user = userBiz.readSysUser(key);
                fundCollectForm.setUserName(user.getUserName());
                collectFormList.add(fundCollectForm);
            }
        }
        List<SysDict> dictList= DictTypeEnum.sysListDictMap.get("ProjFundAccountsType");
        int size = 1;
        if(dictList != null){
            size += dictList.size();
        }

        String[] titles = new String[size];
        titles[0] = "userName:负责人";

        for (int i = 0; i < dictList.size(); i++) {
            titles[i + 1] = "balanceFundMapBySourceId." + dictList.get(i).getDictId() + ":" + dictList.get(i).getDictName();
        }
        ExcleUtile.exportSimpleExcleByObjs(titles, collectFormList, response.getOutputStream());
        String fileName = "剩余经费汇总.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
}
