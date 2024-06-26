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
@RequestMapping("/erp/crm/billPlan")
public class CrmBillController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IDeptBiz deptBiz;

    @Autowired
    private IErpCrmChargeUserChangeBiz changeBiz;
    @Autowired
    private IErpContractBiz contractBiz;
    @Autowired
    private IErpContractBillPlanBiz billPlanBiz;
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
        return "erp/crm/bill/main";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/billList")
    public String billList(Integer currentPage, ErpCrmContract contract, ErpCrmCustomer customer,String waitJH,String waitTracke,
                          String bigRegionTypeId, ContractTimeForm timeForm, String maintainDue, String type,String waitAudit,String KPBH,
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
        paramMap.put("waitJH", waitJH);//待开票计划合同
        paramMap.put("waitAudit", waitAudit);//待开票确认合同
        paramMap.put("waitKP", waitKP);//待开票执行合同
        paramMap.put("KPBH", KPBH);//开票执行驳回合同
        paramMap.put("waitTracke", waitTracke);//待开快递合同

        PageHelper.startPage(currentPage, getPageSize(request));
        List<ErpCrmContractExt> contractListExt = this.contractBiz.searchErpContractListBillNew(paramMap);
        Map<String,Object> payFundMap=new HashMap<>();
        if(contractListExt!=null)
        {
            for(ErpCrmContractExt e:contractListExt)
            {
                Integer sum=  billPlanBiz.sumBillPayFundByFlow(e.getContractFlow());
                if(sum==null)
                    sum=0;
                payFundMap.put(e.getContractFlow(),sum);
            }
        }
        model.addAttribute("payFundMap",payFundMap);
        model.addAttribute("contractList",contractListExt);
        model.addAttribute("type",type);
        Map<String,Object> auditMap=contractBiz.waitBillAuditMap(paramMap);
        model.addAttribute("auditMap",auditMap);
        return "erp/crm/bill/list";
    }
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/billPlanList")
    public String billPlanList(String contractFlow,String type, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        model.addAttribute("type",type);

        //查询开票计划
        ErpCrmContractBillPlan billPlan=new ErpCrmContractBillPlan();
        billPlan.setContractFlow(contractFlow);
        List<ErpCrmContractBillPlan> billPlanList=this.billPlanBiz.searchContractBillPlanList(billPlan);

        Map<String,Integer> balanceMoneyMap=new HashMap<>();
        Integer allBalanceMoney=0;
        //查询回款到账金额
        ErpCrmContractBillBalance billBalance = new ErpCrmContractBillBalance();
        billBalance.setContractFlow(contractFlow);
        List<ErpCrmContractBillBalanceExt> billBalanceList = billBalanceBiz.searchBalanceList(billBalance);
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
                //开票总金额
                Integer m=balanceMoneyMap.get(planFlow);
                if(m==null)
                    m=0;
                m+=bal.getBillFund().intValue();
                balanceMoneyMap.put(planFlow,m);
                allBalanceMoney+=bal.getBillFund().intValue();
            }
        }
        model.addAttribute("billPlanList",billPlanList);
        model.addAttribute("allBalanceMoney",allBalanceMoney);
        model.addAttribute("billBalanceMap",billBalanceMap);
        model.addAttribute("balanceMoneyMap",balanceMoneyMap);
        return "erp/crm/bill/billPlanList";
    }
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addBillPlan")
    public String addBillPlan(String billPlanFlow,String contractFlow, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        ErpCrmContractBillPlan billPlan=billPlanBiz.readPayPlan(billPlanFlow);
        model.addAttribute("billPlan",billPlan);
        Integer sum=  billPlanBiz.sumBillPayFundByFlow(contractFlow);
        if(sum==null)
            sum=0;
        model.addAttribute("sum",sum);

        //查询开票计划
        ErpCrmContractBillPlan billPlan2=new ErpCrmContractBillPlan();
        billPlan2.setContractFlow(contractFlow);
        List<ErpCrmContractBillPlan> billPlanList=this.billPlanBiz.searchContractBillPlanList(billPlan2);

        //查询回款到账金额
        ErpCrmContractBillBalance billBalance = new ErpCrmContractBillBalance();
        billBalance.setContractFlow(contractFlow);
        List<ErpCrmContractBillBalanceExt> billBalanceList = billBalanceBiz.searchBalanceList(billBalance);
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
        return "erp/crm/bill/addBillPlan";
    }

    /**
     * 新增开票
     * @param billPlanFlow
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/addBillIn")
    public String addBillIn(String billPlanFlow,String contractFlow,String recordFlow, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        ErpCrmContractBillPlan billPlan=billPlanBiz.readPayPlan(billPlanFlow);
        model.addAttribute("billPlan",billPlan);
        ErpCrmContractBillBalance balance=billBalanceBiz.readBillBalance(recordFlow);
        model.addAttribute("balance",balance);

        List<SysUser> users=userBiz.getUserByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("users", users);
        return "erp/crm/bill/addBillIn";
    }

    /**
     * 保存到账
     * @param balance
     * @return
     */
    @RequestMapping(value="/saveBalance")
    @ResponseBody
    public  String saveBalance(ErpCrmContractBillBalance balance){
        ErpCrmContractBillPlan billPlan=billPlanBiz.readPayPlan(balance.getBillPlanFlow());
        if(billPlan==null||GlobalConstant.RECORD_STATUS_N.equals(billPlan.getRecordStatus()))
        {
            return "开票计划信息不存在，请刷新页面后重试！";
        }
        ErpCrmContract contract=contractBiz.readContract(balance.getContractFlow());
        if(contract==null)
        {
            return "合同信息不存在，请刷新页面后重试！";
        }
        Integer sum=billBalanceBiz.sumBalancePayFundByFlow(balance.getContractFlow());

        if(StringUtil.isBlank(balance.getRecordFlow()))//新增
        {
            if(sum>=contract.getContractFund().intValue())
            {
                return "开票总金额已大于等于合同总金额，无法新增开票信息";
            }

        }else{
            //修改
            ErpCrmContractBillBalance old=billBalanceBiz.readBillBalance(balance.getRecordFlow());
            if(PlanBalanceStatusEnum.AuditPass.getId().equals(old.getStatusId()))
            {
                return "开票信息已确认，无法修改";
            }
            sum=sum-old.getBillFund().intValue();
            if(sum>=contract.getContractFund().intValue())
            {
                return "开票总金额已大于等于合同总金额，无法修改开票信息";
            }
        }
        sum+=balance.getBillFund().intValue();
        if(sum>contract.getContractFund().intValue())
        {
            return "开票总金额大于合同总金额，无法保存开票信息";
        }
        balance.setStatusId(PlanBalanceStatusEnum.Auditing.getId());
        balance.setStatusName(PlanBalanceStatusEnum.Auditing.getName());
        int result = billBalanceBiz.save(balance);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    /**
     *校验开票开票信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkPlanBalance")
    @ResponseBody
    public  String checkPlanBalance(String billPlanFlow,String contractFlow, Model model) {
        ErpCrmContractBillPlan billPlan=billPlanBiz.readPayPlan(billPlanFlow);
        if(billPlan==null||GlobalConstant.RECORD_STATUS_N.equals(billPlan.getRecordStatus()))
        {
            return "开票计划信息不存在，请刷新页面后重试！";
        }
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        if(contract==null)
        {
            return "合同信息不存在，请刷新页面后重试！";
        }
        Integer sum=billBalanceBiz.sumBalancePayFundByFlow(contractFlow);
        if(sum>=contract.getContractFund().intValue())
        {
            return "开票总金额已大于等于合同总金额，无法添加开票信息";
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    /**
     * 审核开票信息
     * @param model
     * @return
     */
    @RequestMapping(value = "/audit")
    public  String audit(String recordFlow,String contractFlow, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        ErpCrmContractBillBalance old=billBalanceBiz.readBillBalance(recordFlow);
        model.addAttribute("balance",old);
        return "erp/crm/bill/audit";
    }

    /**
     * 显示编辑快递页面
     * @param recordFlow
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editTracke")
    public  String editTracke(String recordFlow,String contractFlow, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        ErpCrmContractBillBalance old=billBalanceBiz.readBillBalance(recordFlow);
        model.addAttribute("balance",old);
        return "erp/crm/bill/editTracke";
    }
    @RequestMapping(value = "/saveTracke")
    @ResponseBody
    public  String saveTracke(ErpCrmContractBillBalance balance, Model model) {
        if(StringUtil.isBlank(balance.getRecordFlow()))
        {
            return "请选择需要填写快递的开票信息";
        }
        if(StringUtil.isBlank(balance.getBillTrackeNumber()))
        {
            return "请填写快递号";
        }
        if(StringUtil.isBlank(balance.getBillTrackeContent()))
        {
            return "请填写寄送地址";
        }

        ErpCrmContractBillBalance old=billBalanceBiz.readBillBalance(balance.getRecordFlow());
        if(old==null)
        {
            return "开票信息不存在，无法审核";
        }
        if(!PlanBalanceStatusEnum.AuditPass.getId().equals(old.getStatusId()))
        {
            return "开票信息未确认，无法保存";
        }
        balance.setTrackeTime(DateUtil.getCurrDateTime(DateUtil.defDtPtn02));
        SysUser user= GlobalContext.getCurrentUser();
        balance.setTrackeUserFlow(user.getUserFlow());
        balance.setTrackeUserName(user.getUserName());
        int result = billBalanceBiz.save(balance);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return GlobalConstant.OPRE_FAIL_FLAG;
    }
    @RequestMapping(value = "/auditBalance")
    @ResponseBody
    public  String auditBalance(String recordFlow,String statusId,String auditReason, Model model) {
        if(StringUtil.isBlank(recordFlow))
        {
            return "请选择需要审核的开票信息";
        }
        if(StringUtil.isBlank(statusId))
        {
            return "请选择审核状态";
        }
        if(!PlanBalanceStatusEnum.AuditBack.getId().equals(statusId)&&!PlanBalanceStatusEnum.AuditPass.getId().equals(statusId))
        {
            return "请选择正确审核状态";
        }
        ErpCrmContractBillBalance old=billBalanceBiz.readBillBalance(recordFlow);
        if(old==null)
        {
            return "开票信息不存在，无法审核";
        }
        if(!PlanBalanceStatusEnum.Auditing.getId().equals(old.getStatusId()))
        {
            return "开票信息已审核，无法重复审核";
        }

        old.setAuditTime(DateUtil.getCurrDateTime(DateUtil.defDtPtn02));
        old.setAuditReason(auditReason);
        old.setStatusId(statusId);
        old.setStatusName(PlanBalanceStatusEnum.getNameById(statusId));
        SysUser user= GlobalContext.getCurrentUser();
        old.setAuditUserFlow(user.getUserFlow());
        old.setAuditUserName(user.getUserName());
        int result = billBalanceBiz.saveBillBalance(old);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return GlobalConstant.OPRE_FAIL_FLAG;
    }
    /**
     *删除开票计划
     * @param model
     * @return
     */
    @RequestMapping(value = "/delPlan")
    @ResponseBody
    public  String delPlan(String billPlanFlow, Model model) {
        ErpCrmContractBillPlan billPlan=billPlanBiz.readPayPlan(billPlanFlow);
        if(billPlan==null)
        {
            return "开票计划信息不存在，请刷新页面！";
        }
        ErpCrmContractBillBalance billBalance = new ErpCrmContractBillBalance();
        billBalance.setContractFlow(billPlan.getContractFlow());
        billBalance.setBillPlanFlow(billPlan.getBillPlanFlow());
        List<ErpCrmContractBillBalanceExt> billBalanceList = billBalanceBiz.searchBalanceList(billBalance);
        if(billBalanceList!=null&&!billBalanceList.isEmpty())
        {
            return "该开票计划已有开票信息，无法删除！";
        }
        billPlan.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int count=billPlanBiz.save(billPlan);
        if(count==GlobalConstant.ZERO_LINE)
        {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    /**
     *保存开票计划
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveBillPlan")
    @ResponseBody
    public  String saveBillPlan(String jsondata,String contractFlow2, Model model) {
        if(StringUtil.isBlank(jsondata))
        {
            return "开票计划信息为空，无法保存，请确认后提交！";
        }
        try {
            ErpCrmContract contract=contractBiz.readContract(contractFlow2);
            if(contract==null)
            {
                return "合同信息不存在，无法保存，请刷新页面后重试！";
            }
            ContractForm contractForm = JSON.parseObject(jsondata, ContractForm.class);
            List<ErpCrmContractBillPlan> billPlanList = contractForm.getBillPlanList();
            if(billPlanList==null||billPlanList.isEmpty())
            {
                return  "开票计划信息为空，无法保存！";
            }
            int sum=0;
            for(ErpCrmContractBillPlan billPlan:billPlanList)
            {
                sum+=billPlan.getBillPayFund().intValue();
                if(StringUtil.isNotBlank(billPlan.getBillPlanFlow()))
                {
                    ErpCrmContractBillPlan old=billPlanBiz.readPayPlan(billPlan.getBillPlanFlow());
                    if(old!=null&&!old.getBillPayFund().equals(billPlan.getBillPayFund()))
                    {
                        //校验是否已有开票回款
                        ErpCrmContractBillBalance balance = new ErpCrmContractBillBalance();
                        balance.setContractFlow(billPlan.getContractFlow());
                        balance.setBillPlanFlow(billPlan.getBillPlanFlow());
                        List<ErpCrmContractBillBalanceExt> balanceList = billBalanceBiz.searchBalanceList(balance);
                        if(balanceList!=null&&!balanceList.isEmpty())
                        {
                            return "开票日期为："+billPlan.getBillPlanDate()+",计划开票金额为："+billPlan.getBillPayFund()+"的开票计划已有开票信息，无法修改！";
                        }
                    }
                }
            }
            //数据库中的计划总金额已经大于合同金额时，提示无法保存
            Integer baseSum=  billPlanBiz.sumBillPayFundByFlow(contractFlow2);
            if(baseSum>=contract.getContractFund().intValue())
            {
                return "计划总金额已经大于或等于合同金额，无法保存！";
            }
            if(sum>contract.getContractFund().intValue())
            {
                return "计划总金额大于合同金额，无法保存！";
            }
            //保存该合同的回款计划
            if(billPlanList!=null && !billPlanList.isEmpty()){
                String result=this.billPlanBiz.saveContractBillPlan(billPlanList,contractFlow2);
                if(!GlobalConstant.SAVE_SUCCESSED.equals(result)){
                    return GlobalConstant.SAVE_FAIL;
                }
            }
            return GlobalConstant.SAVE_SUCCESSED;
        }catch (Exception e)
        {
            return "开票计划信息格式错误，无法保存！";
        }
    }
}