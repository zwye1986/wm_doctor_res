package com.pinde.sci.ctrl.erp;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.erp.ContractCategoryEnum;
import com.pinde.sci.enums.erp.ContractStatusEnum;
import com.pinde.sci.enums.erp.PlanBalanceStatusEnum;
import com.pinde.sci.form.erp.ContractForm;
import com.pinde.sci.form.erp.ContractTimeForm;
import com.pinde.sci.model.erp.ErpCrmContractBillBalanceExt;
import com.pinde.sci.model.erp.ErpCrmContractExt;
import com.pinde.sci.model.erp.ErpCrmContractPayBalanceExt;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/erp/crm/queryContract")
public class CrmQueryContractController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IDeptBiz deptBiz;

    @Autowired
    private IErpCrmChargeUserChangeBiz changeBiz;
    @Autowired
    private IErpContractBiz contractBiz;
    @Autowired
    private IErpContractPayPlanBiz payPlanBiz;
    @Autowired
    private IErpContractBillPlanBiz billPlanBiz;
    @Autowired
    private IErpCrmContractPayBalanceBiz payBalanceBiz;
    @Autowired
    private IErpCrmContractBillBalanceBiz billBalanceBiz;

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/main")
    public String main( String type, Model model) {
        model.addAttribute("type",type);
        return "erp/crm/queryContract/main";
    }

    @RequestMapping(value = "/planAndBillInfo")
    public String planAndBillInfo( String contractFlow, Model model) {
        //查询回款计划
        ErpCrmContractPayPlan payPlan=new ErpCrmContractPayPlan();
        payPlan.setContractFlow(contractFlow);
        List<ErpCrmContractPayPlan> payPlanList=this.payPlanBiz.searchContractPayPlanList(payPlan);
        //查询回款到账金额
        ErpCrmContractPayBalance balance = new ErpCrmContractPayBalance();
        balance.setContractFlow(contractFlow);
        List<ErpCrmContractPayBalanceExt> balanceList = payBalanceBiz.searchAuditPassBalanceList(balance);
        Map<String, List<ErpCrmContractPayBalanceExt>> balanceMap = null;
        if(balanceList != null && !balanceList.isEmpty()){
            balanceMap = new LinkedHashMap<String, List<ErpCrmContractPayBalanceExt>>();
            for(ErpCrmContractPayBalanceExt bal : balanceList){
                String planFlow = bal.getPlanFlow();
                //根据关联planFlow组织Map
                List<ErpCrmContractPayBalanceExt> temp = balanceMap.get(planFlow);
                if(temp == null){
                    temp = new ArrayList<ErpCrmContractPayBalanceExt>();
                }
                temp.add(bal);
                balanceMap.put(planFlow, temp);
            }
        }
        model.addAttribute("payPlanList",payPlanList);
        model.addAttribute("balanceMap",balanceMap);

        //查询开票计划
        ErpCrmContractBillPlan billPlan2=new ErpCrmContractBillPlan();
        billPlan2.setContractFlow(contractFlow);
        List<ErpCrmContractBillPlan> billPlanList=this.billPlanBiz.searchContractBillPlanList(billPlan2);

        //查询回款到账金额
        ErpCrmContractBillBalance billBalance = new ErpCrmContractBillBalance();
        billBalance.setContractFlow(contractFlow);
        List<ErpCrmContractBillBalanceExt> billBalanceList = billBalanceBiz.searchAuditPassBalanceList(billBalance);
        Map<String, List<ErpCrmContractBillBalanceExt>> billBalanceMap = null;
        if(billBalanceList != null && !billBalanceList.isEmpty()){
            billBalanceMap = new LinkedHashMap<String, List<ErpCrmContractBillBalanceExt>>();
            for(ErpCrmContractBillBalanceExt bal : billBalanceList){
                String planFlow = bal.getBillPlanFlow();
                //根据关联planFlow组织Map
                List<ErpCrmContractBillBalanceExt> temp = billBalanceMap.get(planFlow);
                if(temp == null){
                    temp = new ArrayList<ErpCrmContractBillBalanceExt>();
                }
                temp.add(bal);
                billBalanceMap.put(planFlow, temp);
            }
        }
        model.addAttribute("billPlanList",billPlanList);
        model.addAttribute("billBalanceMap",billBalanceMap);

        return "erp/crm/queryContract/planAndBillInfo";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Integer currentPage, ErpCrmContract contract, ErpCrmCustomer customer,String yqht,String notCG,
                          String bigRegionTypeId, ContractTimeForm timeForm, String maintainDue, String type,String dqtb,String[] tbsj,
                          HttpServletRequest request, Model model,  String orderAction, String orderByCase,String createDateEnd, String createDateStart,
                       String payStartDate,String payEndDate,String yuqi) {
        List<String> bigRegionTypeIdList = new ArrayList<String>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtil.isNotBlank(timeForm.getSignDateStart())) {
            paramMap.put("signDateStart", timeForm.getSignDateStart());
        }
        if (StringUtil.isNotBlank(timeForm.getSignDateEnd())) {
            paramMap.put("signDateEnd", timeForm.getSignDateEnd());
        }
        if (StringUtil.isNotBlank(timeForm.getMaintainDueDateStart())) {
            paramMap.put("maintainDueDateStart", timeForm.getMaintainDueDateStart());
        }
        if (StringUtil.isNotBlank(timeForm.getMaintainDueDateEnd())) {
            paramMap.put("maintainDueDateEnd", timeForm.getMaintainDueDateEnd());
        }
        if (StringUtil.isNotBlank(timeForm.getContractDueDateStart())) {
            paramMap.put("contractDueDateStart", timeForm.getContractDueDateStart());
        }
        if (StringUtil.isNotBlank(timeForm.getContractDueDateEnd())) {
            paramMap.put("contractDueDateEnd", timeForm.getContractDueDateEnd());
        }
        if (StringUtil.isNotBlank(orderAction)) {
            paramMap.put("orderAction", orderAction);
        }
        if (StringUtil.isNotBlank(orderByCase)) {
            paramMap.put("orderByCase", orderByCase);
        }
        if (StringUtil.isNotBlank(bigRegionTypeId) && !"00".equals(bigRegionTypeId)) {
            if ("01".equals(bigRegionTypeId)) {
                bigRegionTypeIdList.add("310000");
                bigRegionTypeIdList.add("320000");
                bigRegionTypeIdList.add("330000");
                bigRegionTypeIdList.add("340000");
                bigRegionTypeIdList.add("350000");
                bigRegionTypeIdList.add("360000");
                bigRegionTypeIdList.add("370000");
            } else if ("02".equals(bigRegionTypeId)) {
                bigRegionTypeIdList.add("440000");
                bigRegionTypeIdList.add("450000");
                bigRegionTypeIdList.add("460000");
            } else if ("03".equals(bigRegionTypeId)) {
                bigRegionTypeIdList.add("110000");
                bigRegionTypeIdList.add("120000");
                bigRegionTypeIdList.add("130000");
                bigRegionTypeIdList.add("140000");
                bigRegionTypeIdList.add("150000");
                bigRegionTypeIdList.add("210000");
                bigRegionTypeIdList.add("220000");
                bigRegionTypeIdList.add("230000");
            } else if ("04".equals(bigRegionTypeId)) {
                bigRegionTypeIdList.add("410000");
                bigRegionTypeIdList.add("420000");
                bigRegionTypeIdList.add("430000");
                bigRegionTypeIdList.add("500000");
                bigRegionTypeIdList.add("510000");
                bigRegionTypeIdList.add("520000");
                bigRegionTypeIdList.add("530000");
                bigRegionTypeIdList.add("540000");
                bigRegionTypeIdList.add("610000");
                bigRegionTypeIdList.add("620000");
                bigRegionTypeIdList.add("630000");
                bigRegionTypeIdList.add("640000");
                bigRegionTypeIdList.add("650000");
            }
            paramMap.put("bigRegionTypeIdList", bigRegionTypeIdList);
        }
        paramMap.put("contract", contract);
        paramMap.put("createDateEnd", createDateEnd);
        paramMap.put("createDateStart", createDateStart);
        paramMap.put("customer", customer);
        paramMap.put("maintainDue", StringUtil.defaultString(maintainDue));
        paramMap.put("currDate", DateUtil.getCurrDate());
        paramMap.put("isLike", "1");
        paramMap.put("yqht", yqht);//款项执行计划延期的合同
        paramMap.put("payStartDate", payStartDate);
        paramMap.put("payEndDate", payEndDate);
        paramMap.put("yuqi", yuqi);
        paramMap.put("notCG", notCG);

        paramMap.put("dqtb", dqtb);//到期脱保存
        if(tbsj!=null&&tbsj.length>0)
        {
            paramMap.put("tbsj", Arrays.asList(tbsj));//脱保时间
        }

        if("yszcx".equals(type)||"cwyscx".equals(type))
        {
           // List<String> notInType=new ArrayList<>();
            //notInType.add(ContractCategoryEnum.Purchase.getId());
            //paramMap.put("notInType", notInType);
        }
        if("yszcx".equals(type))
        {
            return yszcx(request,paramMap,currentPage,model);
        }else if("zxcx".equals(type))
        {
            return zxcx(request,paramMap,currentPage,model);
        }else if("cwyscx".equals(type))
        {
            return cwyscx(request,paramMap,currentPage,model);
        }else if("dkcx".equals(type))
        {
            return dkcx(request,paramMap,currentPage,model);
        }
        return "erp/crm/queryContract/list";
    }

    private String yszcx( HttpServletRequest request,Map<String, Object> paramMap, Integer currentPage, Model model) {

        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> contractListExt = this.contractBiz.yszcx(paramMap);
        model.addAttribute("contractList",contractListExt);
        Map<String,Object> fundMap=contractBiz.cwysFund(paramMap);
        model.addAttribute("fundMap",fundMap);
        return "erp/crm/queryContract/yszcx";
    }
    private String zxcx( HttpServletRequest request,Map<String, Object> paramMap, Integer currentPage, Model model) {

        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> contractListExt = this.contractBiz.zxcx(paramMap);
        model.addAttribute("contractList",contractListExt);
        return "erp/crm/queryContract/zxcx";
    }
    private String cwyscx( HttpServletRequest request,Map<String, Object> paramMap, Integer currentPage, Model model) {

        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> contractListExt = this.contractBiz.cwyscx(paramMap);
        model.addAttribute("contractList",contractListExt);
        Map<String,Object> fundMap=contractBiz.cwysFund(paramMap);
        model.addAttribute("fundMap",fundMap);
        return "erp/crm/queryContract/cwyscx";
    }
    private String dkcx( HttpServletRequest request,Map<String, Object> paramMap, Integer currentPage, Model model) {

        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> contractListExt = this.contractBiz.dkcx(paramMap);
        model.addAttribute("contractList",contractListExt);
        return "erp/crm/queryContract/dkcx";
    }
}