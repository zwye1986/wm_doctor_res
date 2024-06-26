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
import com.pinde.sci.enums.erp.ContractStatusEnum;
import com.pinde.sci.enums.erp.PlanBalanceStatusEnum;
import com.pinde.sci.enums.erp.UserChangeStatusEnum;
import com.pinde.sci.form.erp.ContractForm;
import com.pinde.sci.form.erp.ContractTimeForm;
import com.pinde.sci.model.erp.ErpCrmContractExt;
import com.pinde.sci.model.erp.ErpCrmContractPayBalanceExt;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/erp/crm/payPlan")
public class CrmPayController extends GeneralController {

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
        return "erp/crm/pay/main";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/payList")
    public String payList(Integer currentPage, ErpCrmContract contract, ErpCrmCustomer customer,String waitJH,String KXBH,
                          String bigRegionTypeId, ContractTimeForm timeForm, String maintainDue, String type,String waitAudit,
                          HttpServletRequest request, Model model,  String orderAction, String orderByCase,String waitKP) {
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
        List<String> statusIds=new ArrayList<>();
        statusIds.add(ContractStatusEnum.Auditing.getId());
        statusIds.add(ContractStatusEnum.AuditBack.getId());
        paramMap.put("contract", contract);
        paramMap.put("customer", customer);
        paramMap.put("maintainDue", StringUtil.defaultString(maintainDue));
        paramMap.put("currDate", DateUtil.getCurrDate());
        paramMap.put("isLike", "1");
        paramMap.put("notInStatus", statusIds);
        paramMap.put("waitJH", waitJH);//待款项计划合同
        paramMap.put("waitKP", waitKP);//待款项执行合同
        paramMap.put("KXBH", KXBH);//款项执行驳回合同
        paramMap.put("waitAudit", waitAudit);//待款项确认合同

        PageHelper.startPage(currentPage, getPageSize(request));
        List<ErpCrmContractExt> contractListExt = this.contractBiz.searchErpContractListNew(paramMap);
        Map<String,Object> payFundMap=new HashMap<>();
        if(contractListExt!=null)
        {
            for(ErpCrmContractExt e:contractListExt)
            {
                Integer sum=  payPlanBiz.sumPayFundByFlow(e.getContractFlow());
                if(sum==null)
                    sum=0;
                payFundMap.put(e.getContractFlow(),sum);
            }
        }
        model.addAttribute("payFundMap",payFundMap);
        model.addAttribute("contractList",contractListExt);
        model.addAttribute("type",type);
        Map<String,Object> fundMap=contractBiz.searchErpContractListNewMap(paramMap);
        model.addAttribute("fundMap",fundMap);
        Map<String,Object> auditMap=contractBiz.waitPayAuditMap(paramMap);
        model.addAttribute("auditMap",auditMap);
        return "erp/crm/pay/list";
    }
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/payPlanList")
    public String payPlanList(String contractFlow,String type, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        model.addAttribute("type",type);

        //查询回款计划
        ErpCrmContractPayPlan payPlan=new ErpCrmContractPayPlan();
        payPlan.setContractFlow(contractFlow);
        List<ErpCrmContractPayPlan> payPlanList=this.payPlanBiz.searchContractPayPlanList(payPlan);
        //查询回款到账金额
        ErpCrmContractPayBalanceExt balance = new ErpCrmContractPayBalanceExt();
        balance.setContractFlow(contractFlow);
        List<ErpCrmContractPayBalanceExt> balanceList = payBalanceBiz.searchBalanceList(balance);
        Map<String,Integer> balanceMoneyMap=new HashMap<>();
        Integer allBalanceMoney=0;
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
                //到帐总金额
                Integer m=balanceMoneyMap.get(planFlow);
                if(m==null)
                    m=0;
                m+=bal.getPayFund().intValue();
                balanceMoneyMap.put(planFlow,m);
                allBalanceMoney+=bal.getPayFund().intValue();
            }
        }
        model.addAttribute("payPlanList",payPlanList);
        model.addAttribute("allBalanceMoney",allBalanceMoney);
        model.addAttribute("balanceMap",balanceMap);
        model.addAttribute("balanceMoneyMap",balanceMoneyMap);
        return "erp/crm/pay/payPlanList";
    }
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addPayPlan")
    public String addPayPlan(String planFlow,String contractFlow, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        ErpCrmContractPayPlan payPlan=payPlanBiz.readPayPlan(planFlow);
        model.addAttribute("payPlan",payPlan);
        Integer sum=  payPlanBiz.sumPayFundByFlow(contractFlow);
        if(sum==null)
            sum=0;
        model.addAttribute("sum",sum);

        //查询回款计划
        ErpCrmContractPayPlan payPlan2=new ErpCrmContractPayPlan();
        payPlan2.setContractFlow(contractFlow);
        List<ErpCrmContractPayPlan> payPlanList=this.payPlanBiz.searchContractPayPlanList(payPlan2);
        //查询回款到账金额
        ErpCrmContractPayBalanceExt balance = new ErpCrmContractPayBalanceExt();
        balance.setContractFlow(contractFlow);
        List<ErpCrmContractPayBalanceExt> balanceList = payBalanceBiz.searchBalanceList(balance);
        Map<String, List<ErpCrmContractPayBalanceExt>> balanceMap = null;
        if(balanceList != null && !balanceList.isEmpty()){
            balanceMap = new LinkedHashMap<String, List<ErpCrmContractPayBalanceExt>>();
            for(ErpCrmContractPayBalanceExt bal : balanceList){
                String planFlow2 = bal.getPlanFlow();
                //根据关联planFlow组织Map
                List<ErpCrmContractPayBalanceExt> temp = balanceMap.get(planFlow2);
                if(temp == null){
                    temp = new ArrayList<ErpCrmContractPayBalanceExt>();
                }
                temp.add(bal);
                balanceMap.put(planFlow2, temp);
            }
        }
        model.addAttribute("payPlanList",payPlanList);
        model.addAttribute("balanceMap",balanceMap);
        return "erp/crm/pay/addPayPlan";
    }

    /**
     * 新增到帐
     * @param planFlow
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/addPlanIn")
    public String addPlanIn(String planFlow,String contractFlow,String recordFlow, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        ErpCrmContractPayPlan payPlan=payPlanBiz.readPayPlan(planFlow);
        model.addAttribute("payPlan",payPlan);
        ErpCrmContractPayBalance balance=payBalanceBiz.readBalanceByFlow(recordFlow);
        model.addAttribute("balance",balance);

        List<SysUser> users=userBiz.getUserByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("users", users);
        return "erp/crm/pay/addPlanIn";
    }

    /**
     * 保存到账
     * @param balance
     * @return
     */
    @RequestMapping(value="/saveBalance")
    @ResponseBody
    public  String saveBalance(ErpCrmContractPayBalance balance){
        ErpCrmContractPayPlan payPlan=payPlanBiz.readPayPlan(balance.getPlanFlow());
        if(payPlan==null||GlobalConstant.RECORD_STATUS_N.equals(payPlan.getRecordStatus()))
        {
            return "款项计划信息不存在，请刷新页面后重试！";
        }
        ErpCrmContract contract=contractBiz.readContract(balance.getContractFlow());
        if(contract==null)
        {
            return "合同信息不存在，请刷新页面后重试！";
        }
        Integer sum=payBalanceBiz.sumBalancePayFundByFlow(balance.getContractFlow());

        if(StringUtil.isBlank(balance.getRecordFlow()))//新增
        {
            if(sum>=contract.getContractFund().intValue())
            {
                return "到帐总金额已大于等于合同总金额，无法新增到帐信息";
            }

        }else{
            //修改
            ErpCrmContractPayBalance old=payBalanceBiz.readBalanceByFlow(balance.getRecordFlow());
            if(PlanBalanceStatusEnum.AuditPass.getId().equals(old.getStatusId()))
            {
                return "到帐信息已确认，无法修改";
            }
            sum=sum-old.getPayFund().intValue();
            if(sum>=contract.getContractFund().intValue())
            {
                return "到帐总金额已大于等于合同总金额，无法修改到帐信息";
            }
        }
        sum+=balance.getPayFund().intValue();
        if(sum>contract.getContractFund().intValue())
        {
            return "到帐总金额大于合同总金额，无法保存到帐信息";
        }
        balance.setStatusId(PlanBalanceStatusEnum.Auditing.getId());
        balance.setStatusName(PlanBalanceStatusEnum.Auditing.getName());
        int result = payBalanceBiz.save(balance);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    /**
     *校验款项到帐信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkPlanBalance")
    @ResponseBody
    public  String checkPlanBalance(String planFlow,String contractFlow, Model model) {
        ErpCrmContractPayPlan payPlan=payPlanBiz.readPayPlan(planFlow);
        if(payPlan==null||GlobalConstant.RECORD_STATUS_N.equals(payPlan.getRecordStatus()))
        {
            return "款项计划信息不存在，请刷新页面后重试！";
        }
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        if(contract==null)
        {
            return "合同信息不存在，请刷新页面后重试！";
        }
        Integer sum=payBalanceBiz.sumBalancePayFundByFlow(contractFlow);
        if(sum>=contract.getContractFund().intValue())
        {
            return "到帐总金额已大于等于合同总金额，无法添加到帐信息";
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    /**
     * 审核到帐信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/auditBalance")
    @ResponseBody
    public  String auditBalance(String recordFlow,String statusId,String auditReason, Model model) {
        if(StringUtil.isBlank(recordFlow))
        {
            return "请选择需要审核的到帐信息";
        }
        if(StringUtil.isBlank(statusId))
        {
            return "请选择审核状态";
        }
        if(!PlanBalanceStatusEnum.AuditBack.getId().equals(statusId)&&!PlanBalanceStatusEnum.AuditPass.getId().equals(statusId))
        {
            return "请选择正确审核状态";
        }
        ErpCrmContractPayBalance old=payBalanceBiz.readBalanceByFlow(recordFlow);
        if(old==null)
        {
            return "到帐信息不存在，无法审核";
        }
        if(!PlanBalanceStatusEnum.Auditing.getId().equals(old.getStatusId()))
        {
            return "到帐信息已审核，无法重复审核";
        }
        old.setAuditTime(DateUtil.getCurrDateTime(DateUtil.defDtPtn02));
        old.setAuditReason(auditReason);
        old.setStatusId(statusId);
        old.setStatusName(PlanBalanceStatusEnum.getNameById(statusId));
        SysUser user=GlobalContext.getCurrentUser();
        old.setAuditUserFlow(user.getUserFlow());
        old.setAuditUserName(user.getUserName());
        int result = payBalanceBiz.saveBalance(old);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return GlobalConstant.OPRE_FAIL_FLAG;
    }
    /**
     * 审核到帐信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/audit")
    public  String audit(String recordFlow,String contractFlow, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        ErpCrmContractPayBalance old=payBalanceBiz.readBalanceByFlow(recordFlow);
        model.addAttribute("balance",old);
        return "erp/crm/pay/audit";
    }
    /**
     *删除款项计划
     * @param model
     * @return
     */
    @RequestMapping(value = "/delPlan")
    @ResponseBody
    public  String delPlan(String planFlow, Model model) {
        ErpCrmContractPayPlan payPlan=payPlanBiz.readPayPlan(planFlow);
        if(payPlan==null)
        {
            return "款项计划信息不存在，请刷新页面！";
        }
        //校验是否已有款项回款
        ErpCrmContractPayBalanceExt balance = new ErpCrmContractPayBalanceExt();
        balance.setContractFlow(payPlan.getContractFlow());
        balance.setPlanFlow(payPlan.getPlanFlow());
        List<ErpCrmContractPayBalanceExt> balanceList = payBalanceBiz.searchBalanceList(balance);
        if(balanceList!=null&&!balanceList.isEmpty())
        {
            return "该款项已有到帐信息，无法删除！";
        }
        payPlan.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int count=payPlanBiz.save(payPlan);
        if(count==GlobalConstant.ZERO_LINE)
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    /**
     *保存款项计划
     * @param model
     * @return
     */
    @RequestMapping(value = "/savePayPlan")
    @ResponseBody
    public  String savePayPlan(String jsondata,String contractFlow2, Model model) {
        if(StringUtil.isBlank(jsondata))
        {
            return "款项计划信息为空，无法保存，请确认后提交！";
        }
        try {
            ErpCrmContract contract=contractBiz.readContract(contractFlow2);
            if(contract==null)
            {
                return "合同信息不存在，无法保存，请刷新页面后重试！";
            }
            ContractForm contractForm = JSON.parseObject(jsondata, ContractForm.class);
            List<ErpCrmContractPayPlan> payPlanList = contractForm.getPayPlanList();
            if(payPlanList==null||payPlanList.isEmpty())
            {
                return  "款项计划信息为空，无法保存！";
            }
            int sum=0;
            for(ErpCrmContractPayPlan payPlan:payPlanList)
            {
                sum+=payPlan.getPayFund().intValue();
                if(StringUtil.isNotBlank(payPlan.getPlanFlow()))
                {
                    ErpCrmContractPayPlan old=payPlanBiz.readPayPlan(payPlan.getPlanFlow());
                    if(old!=null&&!old.getPayFund().equals(payPlan.getPayFund()))
                    {
                        //校验是否已有款项回款
                        ErpCrmContractPayBalanceExt balance = new ErpCrmContractPayBalanceExt();
                        balance.setContractFlow(payPlan.getContractFlow());
                        balance.setPlanFlow(payPlan.getPlanFlow());
                        List<ErpCrmContractPayBalanceExt> balanceList = payBalanceBiz.searchBalanceList(balance);
                        if(balanceList!=null&&!balanceList.isEmpty())
                        {
                            return "收款日为："+payPlan.getPlanDate()+",计划金额为："+payPlan.getPayFund()+"的款项计划已有到帐信息，无法修改！";
                        }
                    }
                }
            }
            //数据库中的计划总金额已经大于合同金额时，提示无法保存
            Integer baseSum=  payPlanBiz.sumPayFundByFlow(contractFlow2);
            if(baseSum>=contract.getContractFund().intValue())
            {
                return "计划总金额已经大于或等于合同金额，无法保存！";
            }
            if(sum>contract.getContractFund().intValue())
            {
                return "计划总金额大于合同金额，无法保存！";
            }
            //保存该合同的回款计划
            if(payPlanList!=null && !payPlanList.isEmpty()){
                String result=this.payPlanBiz.saveContractPayPlan(payPlanList,contractFlow2);
                if(!GlobalConstant.SAVE_SUCCESSED.equals(result)){
                    return GlobalConstant.SAVE_FAIL;
                }
            }
            return GlobalConstant.SAVE_SUCCESSED;
        }catch (Exception e)
        {
            return "款项计划信息格式错误，无法保存！";
        }
    }
}