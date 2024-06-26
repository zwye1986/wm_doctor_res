package com.pinde.sci.ctrl.erp;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.erp.*;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.erp.ContractForm;
import com.pinde.sci.form.erp.ContractTimeForm;
import com.pinde.sci.model.erp.*;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/erp/crm/contractWH")
public class CrmContractWHController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IDeptBiz deptBiz;

    @Autowired
    private IErpCrmChargeUserChangeBiz changeBiz;
    @Autowired
    private IErpContractBiz contractBiz;
    @Autowired
    private IErpContractProcessBiz processBiz;
    @Autowired
    private IErpContractPayPlanBiz payPlanBiz;
    @Autowired
    private IErpContractBillPlanBiz billPlanBiz;
    @Autowired
    private IErpCrmContractPayBalanceBiz payBalanceBiz;
    @Autowired
    private IErpCrmContractBillBalanceBiz billBalanceBiz;
    @Autowired
    private IErpCustomerBiz customerBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IErpContractRefBiz refBiz;
    @Autowired
    private IErpContractPowerBiz powerBiz;
    @Autowired
    private IErpContractProductBiz productBiz;
    @Autowired
    private IErpContractOtherPowerBiz otherPowerBiz;
    @Autowired
    private IErpContractUserBiz contractUserBiz;
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/main")
    public String main( String type, Model model) {
        model.addAttribute("type",type);
        List<SysDept> deptList = searchDeptList();
        model.addAttribute("deptList", deptList);

        List<SysUser> users=userBiz.getUserByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("users", users);
        return "erp/crm/contractWH/main";
    }
    @RequestMapping(value = "/updateNextDate")
    public String updateNextDate( String contractFlow, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        return "erp/crm/contractWH/updateNextDate";
    }

    /**
     * 编辑安装信息
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editBuildInfo")
    public String editBuildInfo( String contractFlow, Model model) {
        ErpCrmContract contract=contractBiz.readContract(contractFlow);
        model.addAttribute("contract",contract);
        ErpCrmProductBuildInfo info=contractBiz.getBuildInfoByFlow(contractFlow);
        model.addAttribute("info",info);
        SysDept dept = new SysDept();
        dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDept> deptList = this.deptBiz.searchDept(dept);
        model.addAttribute("deptList",deptList);
        if(info!=null)
        {
            SysUser sysUser = new SysUser();
            sysUser.setDeptFlow(info.getDeptFlow());
            sysUser.setStatusId(UserStatusEnum.Activated.getId());
            List<SysUser> userList = this.userBiz.searchUser(sysUser);
            model.addAttribute("userList",userList);
        }

        return "erp/crm/contractWH/editBuildInfo";
    }
    @RequestMapping(value="/saveBuildInfo")
    @ResponseBody
    public  String saveBuildInfo(ErpCrmProductBuildInfo info){
        if(StringUtil.isNotBlank(info.getDeptFlow()))
        {
            SysDept dept=deptBiz.readSysDept(info.getDeptFlow());
            if(dept!=null)
            {
                info.setDeptName(dept.getDeptName());
            }
        }
        if(StringUtil.isNotBlank(info.getBuildingUserFlow()))
        {
            SysUser user=userBiz.readSysUser(info.getBuildingUserFlow());
            if(user!=null)
            {
                info.setBuildingUserName(user.getUserName());
            }
        }
        int result = contractBiz.saveBuilding(info);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping(value = "/editContract")
    public String editContract( String contractFlow, Model model) {
        if(StringUtil.isNotBlank(contractFlow)) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            ErpCrmContract contract = new ErpCrmContract();
            contract.setContractFlow(contractFlow);
            paramMap.put("contract", contract);
            List<ErpCrmContractExt> contractList = this.contractBiz.searchContracts(paramMap);
            if (contractList != null && !contractList.isEmpty()) {
                if (ContractCategoryEnum.Sell.getId().equals(contractList.get(0).getContractCategoryId())) {
                    ErpCrmCustomer userCustomer = this.customerBiz.readCustomer(contractList.get(0).getCustomerFlow());
                    String useCustomerName = null;
                    if (userCustomer != null) {
                        useCustomerName = userCustomer.getCustomerName();
                    }
                    String tempFlow = contractList.get(0).getCustomerFlow();
                    String tempName = useCustomerName;
                    contractList.get(0).setCustomerFlow(contractList.get(0).getConsumerFlow());
                    contractList.get(0).getCustomer().setCustomerFlow(contractList.get(0).getConsumerFlow());
                    contractList.get(0).getCustomer().setCustomerName(contractList.get(0).getConsumerName());
                    contractList.get(0).setConsumerFlow(tempFlow);
                    contractList.get(0).setConsumerName(tempName);
                }
                model.addAttribute("contractExt", contractList.get(0));
                PubFile file = this.fileBiz.readFile(contractList.get(0).getContractFileFlow());
                model.addAttribute("file", file);

                List<ErpCrmCustomer> customerList = new ArrayList<ErpCrmCustomer>();
                List<String> customerFlowList = new ArrayList<String>();
                Map<String, Object> contractMap = new HashMap<String, Object>();
                //查询关联主合同信息
                List<ErpCrmContractRefExt> mainRefExtList = this.refBiz.searchContractListByRef(contractFlow, null);
                model.addAttribute("refExtList", mainRefExtList);
                if (mainRefExtList != null && !mainRefExtList.isEmpty()) {
                    for (ErpCrmContractRefExt refExt : mainRefExtList) {
                        ErpCrmCustomer customer = this.customerBiz.readCustomer(refExt.getContract().getCustomerFlow());
                        if (!customerFlowList.contains(customer.getCustomerFlow())) {
                            customerList.add(customer);
                            customerFlowList.add(customer.getCustomerFlow());
                        }
                        List<ErpCrmContract> contracts = this.contractBiz.searchMainContact(customer.getCustomerFlow(), contractFlow);
                        contractMap.put(customer.getCustomerFlow(), contracts);
                    }
                }
                model.addAttribute("customerList", customerList);
                model.addAttribute("contractMap", contractMap);
                //查询该合同是否被其它合同关联
                ErpCrmContractRef ref = new ErpCrmContractRef();
                ref.setContractFlow(contractFlow);
                List<ErpCrmContractRef> refList = this.refBiz.searchRefList(ref);
                if (refList != null && !refList.isEmpty()) {
                    model.addAttribute("refFlag", GlobalConstant.FLAG_Y);
                }
                //开通权限信息
                ErpCrmContractPower power = new ErpCrmContractPower();
                power.setContractFlow(contractFlow);
                List<ErpCrmContractPower> powerList = this.powerBiz.searchContractPowerList(power);
                Map<String, PubFile> fileMap = new HashMap<>();
                if (powerList != null && !powerList.isEmpty()) {
                    for (ErpCrmContractPower p : powerList) {
                        fileMap.put(p.getPowerFlow(), fileBiz.readFile(p.getFileFlow()));
                    }
                }
                model.addAttribute("powerList", powerList);
                model.addAttribute("fileMap", fileMap);
                //查询产品信息
                ErpCrmContractProduct product = new ErpCrmContractProduct();
                product.setContractFlow(contractFlow);
                List<ErpCrmContractProduct> productList = this.productBiz.searchContactProductList(product);
                model.addAttribute("productList", productList);
                //其他权限
                ErpCrmContractOtherPower otherPower = new ErpCrmContractOtherPower();
                otherPower.setContractFlow(contractFlow);
                List<ErpCrmContractOtherPower> otherPowerList = this.otherPowerBiz.searchContractPowerList(otherPower);
                model.addAttribute("otherPowerList", otherPowerList);

                Map<String, Object> paramUserMap = new HashMap<String, Object>();
                paramUserMap.put("contractFlow", contractFlow);
                List<ErpCrmContractUserExt> userList = this.contractUserBiz.searchContractUserExtList(paramUserMap);
                model.addAttribute("userList", userList);
            }
        }

        List<ErpCrmCustomer> consumerList = searchCustomerJsonByType();
        model.addAttribute("consumerList", consumerList);


        List<SysDept> deptList = searchDeptList();
        model.addAttribute("deptList", deptList);
        List<SysUser> users2=userBiz.getUserByOrg(GlobalContext.getCurrentUser().getOrgFlow());
        model.addAttribute("users2", users2);

        List<String> deptFlows=new ArrayList<>();
        deptFlows.add("f48a5060931147daa467eadbb5885629");
        deptFlows.add("a8a0aeef43c846c4a976589184b4ed8b");
        deptFlows.add("a02a8190fc58440f8635b7c5cfcd1949");
        deptFlows.add("d7f1afc58416446ba414d3c69bb204e2");
        deptFlows.add("5b42e66a13e742d3a796f17d8104e02d");
        deptFlows.add("1f56f216c3fd4dc4a1b23e7dda645c0b");
        List<SysUser> users=userBiz.getUserByDeptFlows(deptFlows);
        model.addAttribute("users", users);
        return "erp/crm/contractWH/editContract";
    }

    /**
     * 保存合同及其相关信息
     *
     * @param jsondata
     * @param file
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/saveContractInfo")
    @ResponseBody
    public String saveContract(String jsondata, String refType, @RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) throws IOException {
        if (!checkFileSize(file)) {
            return GlobalConstant.FILE_BEYOND_LIMIT;
        }
        ContractForm contractForm =null;
        try {
            contractForm=JSON.parseObject(jsondata, ContractForm.class);
        }catch (Exception e){
            return "保存数据格式不正确！";
        }
        if(contractForm!=null) {

            ErpCrmContract contract = contractForm.getContract();

            ErpCrmContract old = contractBiz.readContract(contract.getContractFlow());
            if (old != null&&!ContractStatusEnum.AuditBack.getId().equals(old.getContractStatusId())
                    && !ContractStatusEnum.Auditing.getId().equals(old.getContractStatusId())) {
                return "该合同状态不是待审核或审核退回，无法编辑！";
            }
            List<ErpCrmContractProduct> productList = contractForm.getProductList();
            List<ErpCrmCustomerUserExt> userList = contractForm.getUserExtList();
            List<ErpCrmContractRef> refList = contractForm.getRefList();
            List<ErpCrmContractPowerExt> powerList = contractForm.getPowerExtList();
            List<ErpCrmContractOtherPower> otherPowerList = contractForm.getOtherPowerList();
            if (StringUtil.isNotBlank(contract.getContractFlow()) &&StringUtil.isNotBlank(refType) && GlobalConstant.RECORD_STATUS_Y.equals(refType)) {
                String result = this.refBiz.updateOneContractRef(contract.getContractFlow());
                if (!GlobalConstant.SAVE_SUCCESSED.equals(result)) {
                    return GlobalConstant.SAVE_FAIL;
                }
            }
            String contractFlow = this.contractBiz.saveContractInfoNew(replenishContract(contract), replenishContractProduct(productList), replenishCustomerUser(userList), file, refList, powerList, otherPowerList);
            if(!GlobalConstant.SAVE_FAIL.equals(contractFlow))
            {
                return "1";
            }
            return contractFlow;
        }
        return "保存数据为空";
    }

    /**
     * 设置合同中需要枚举的字段name
     *
     * @param contract
     * @return
     */
    public ErpCrmContract replenishContract(ErpCrmContract contract) {
        if (contract != null) {
            if (StringUtil.isNotBlank(contract.getConsumerFlow())) {
                ErpCrmCustomer customer = this.customerBiz.readCustomer(contract.getConsumerFlow());
                contract.setConsumerName(customer.getCustomerName());
            }
            if (StringUtil.isNotBlank(contract.getContractCategoryId())) {
                contract.setContractOwnName(CompanyNameEnum.getNameById(contract.getContractOwnId()));
            }
            if (StringUtil.isNotBlank(contract.getContractCategoryId())) {
                contract.setContractCategoryName(ContractCategoryEnum.getNameById(contract.getContractCategoryId()));
                //如果合同类别是经销合同则调换经销商和客户
                if (!ContractCategoryEnum.Sell.getId().equals(contract.getContractCategoryId())) {
                    contract.setConsumerFlow("");
                    contract.setConsumerName("");
                } else {
                    ErpCrmCustomer userCustomer = this.customerBiz.readCustomer(contract.getCustomerFlow());
                    String useCustomerName = null;
                    if (userCustomer != null) {
                        useCustomerName = userCustomer.getCustomerName();
                    }
                    String tempFlow = contract.getCustomerFlow();
                    String tempName = useCustomerName;
                    contract.setCustomerFlow(contract.getConsumerFlow());
                    contract.setConsumerFlow(tempFlow);
                    contract.setConsumerName(tempName);
                }
            }
            if (StringUtil.isNotBlank(contract.getContractStatusId())) {
                contract.setContractStatusName(ContractStatusEnum.getNameById(contract.getContractStatusId()));
            }
            if (StringUtil.isNotBlank(contract.getContractTypeId())) {
                contract.setContractTypeName(ContractTypeEnum.getNameById(contract.getContractTypeId()));
            }
        }
        return contract;
    }

    /**
     * 设置合同产品中需要枚举的字段name
     *
     * @param productList
     * @return
     */
    public List<ErpCrmContractProduct> replenishContractProduct(List<ErpCrmContractProduct> productList) {
        if (productList != null && !productList.isEmpty()) {
            for (ErpCrmContractProduct product : productList) {
                if (StringUtil.isNotBlank(product.getProductTypeId())) {
                    product.setProductTypeName(DictTypeEnum.ProductType.getDictNameById(product.getProductTypeId()));
                } else {
                    product.setProductTypeId("");
                }
            }
        }
        return productList;
    }

    /**
     * 设置合同联系人中需要枚举的字段name
     *
     * @param userList
     * @return
     */
    public List<ErpCrmCustomerUserExt> replenishCustomerUser(List<ErpCrmCustomerUserExt> userList) {
        if (userList != null && !userList.isEmpty()) {
            for (ErpCrmCustomerUserExt contractUser : userList) {
                if (StringUtil.isNotBlank(contractUser.getSexId())) {
                    contractUser.setSexName(UserSexEnum.getNameById(contractUser.getSexId()));
                }
            }
        }
        return userList;
    }
    /**
     * 校验文件大小
     *
     * @param file
     * @return
     */
    public Boolean checkFileSize(MultipartFile file) {
        long fileLength = 100 * 1024 * 1024;
        if (file != null) {
            if (file.getSize() >= fileLength) {
                return false;
            }
        }
        return true;
    }
    public List<SysDept> searchDeptList() {
        SysDept dept = new SysDept();
        dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDept> deptList = this.deptBiz.searchDept(dept);
        return deptList;
    }

    public List<ErpCrmCustomer> searchCustomerJsonByType() {
        ErpCrmCustomer customer = new ErpCrmCustomer();
        customer.setCustomerTypeId(CustomerTypeEnum.Enterprise.getId());
        List<ErpCrmCustomer> customerList = this.customerBiz.searchCustomer(customer);
        return customerList;
    }
    @RequestMapping(value="/saveNextDate")
    @ResponseBody
    public  String saveNextDate(ErpCrmContract contract){
        if(StringUtil.isBlank(contract.getContractFlow()))
        {
            return "请选择需要更新下次维护日期的合同！";
        }
        if(StringUtil.isBlank(contract.getNextMaintainDate()))
        {
            return "请填写下次维护日期！";
        }
        int result = contractBiz.updateContract(contract);
        if(GlobalConstant.ZERO_LINE != result){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping(value="/checkContractStatus")
    @ResponseBody
    public String checkContractStatus(String contractFlow){
        if(StringUtil.isNotBlank(contractFlow)) {
            ErpCrmContract contract = contractBiz.readContract(contractFlow);
            if (contract == null) {
                return "合同信息不存在，请刷新后重试！";
            }
            if (!ContractStatusEnum.AuditBack.getId().equals(contract.getContractStatusId())
                    && !ContractStatusEnum.Auditing.getId().equals(contract.getContractStatusId())) {
                return "该合同状态不是待审核或审核退回，无法编辑！";
            }
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Integer currentPage, ErpCrmContract contract, ErpCrmCustomer customer,
                          String bigRegionTypeId, ContractTimeForm timeForm, String maintainDue, String type,String waitAudit,
                          HttpServletRequest request, Model model,  String orderAction, String orderByCase) {
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
        paramMap.put("waitAudit", waitAudit);
        paramMap.put("contract", contract);
        paramMap.put("customer", customer);
        paramMap.put("maintainDue", StringUtil.defaultString(maintainDue));
        paramMap.put("currDate", DateUtil.getCurrDate());
        paramMap.put("isLike", "1");
        if("wh".equals(type))
        {
            contract.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            return wh(request,paramMap,currentPage,model);
        }else if("sh".equals(type))
        {
            return sh(request,paramMap,currentPage,model);
        }
        return "erp/crm/contractWH/list";
    }

    private String wh( HttpServletRequest request,Map<String, Object> paramMap, Integer currentPage, Model model) {
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> contractListExt = this.contractBiz.wh(paramMap);
        Map<String,String> btnMap=new HashMap<>();
        for(Map<String,Object> c:contractListExt)
        {
            String contractFlow= (String) c.get("contractFlow");
            ErpCrmProductBuildInfo info=contractBiz.getEmptyBuildInfoByFlow(contractFlow);
            if(info==null)
            {
                btnMap.put(contractFlow,"Y");
            }else {
                btnMap.put(contractFlow,"N");
            }
        }
        model.addAttribute("btnMap",btnMap);
        model.addAttribute("contractList",contractListExt);
        return "erp/crm/contractWH/wh";
    }
    private String sh( HttpServletRequest request,Map<String, Object> paramMap, Integer currentPage, Model model) {
        PageHelper.startPage(currentPage, getPageSize(request));
        List<Map<String,Object>> contractListExt = this.contractBiz.wh(paramMap);
        model.addAttribute("contractList",contractListExt);
        return "erp/crm/contractWH/sh";
    }
    @RequestMapping(value = "/auditContract")
    public String auditContract(String contractFlow, ErpCrmCustomerUser user, Model model) {
        Map<String, Object> resultMap = this.contractBiz.readContractExt(contractFlow, user);
        model.addAttribute("resultMap", resultMap);
        return "erp/crm/contractWH/auditContract";
    }
    @RequestMapping(value = "/saveAuditContract")
    @ResponseBody
    public  String saveAuditContract(String contractFlow,String statusId,String auditReason, Model model) {
        if(StringUtil.isBlank(contractFlow))
        {
            return "请选择需要审核的合同信息";
        }
        if(StringUtil.isBlank(statusId))
        {
            return "请选择审核状态";
        }
        if(!"Y".equals(statusId)&&!"N".equals(statusId))
        {
            return "请选择正确审核状态";
        }
        ErpCrmContract old=contractBiz.readContract(contractFlow);
        if(old==null)
        {
            return "合同信息不存在，无法审核";
        }
        if(!ContractStatusEnum.Auditing.getId().equals(old.getContractStatusId()))
        {
            return "合同信息已审核，无法重复审核";
        }
        ErpCrmContractProcess process = new ErpCrmContractProcess();
        if("Y".equals(statusId))
        {
            old.setContractStatusId(ContractStatusEnum.Implement.getId());
            old.setContractStatusName(ContractStatusEnum.Implement.getName());
            process.setStatusId(ContractStatusEnum.Implement.getId());
            process.setStatusName(ContractStatusEnum.Implement.getName());
        }else if("N".equals(statusId))
        {
            old.setContractStatusId(ContractStatusEnum.AuditBack.getId());
            old.setContractStatusName(ContractStatusEnum.AuditBack.getName());
            process.setStatusId(ContractStatusEnum.AuditBack.getId());
            process.setStatusName(ContractStatusEnum.AuditBack.getName());
        }
        old.setAuditReason(auditReason);
        SysUser user= GlobalContext.getCurrentUser();
        old.setAuditUserFlow(user.getUserFlow());
        old.setAuditUserName(user.getUserName());

        process.setContractFlow(contractFlow);
        process.setAuditUserFlow(user.getUserFlow());
        process.setAuditUserName(user.getUserName());
        process.setAuditReason(auditReason);
        process.setAuditTime(DateUtil.getCurrDateTime(DateUtil.defDtPtn02));
        int result = processBiz.auditContract(old, process);
        if (result != GlobalConstant.ONE_LINE) {
            return GlobalConstant.OPRE_FAIL_FLAG;
        }
        return GlobalConstant.OPRE_SUCCESSED_FLAG;
    }
}