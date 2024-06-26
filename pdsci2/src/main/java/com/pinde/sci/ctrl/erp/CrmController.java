package com.pinde.sci.ctrl.erp;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.ISysUserRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.enums.erp.*;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.erp.ContractForm;
import com.pinde.sci.form.erp.ContractTimeForm;
import com.pinde.sci.form.erp.CustomerUserForm;
import com.pinde.sci.form.jsres.BackTrainForm;
import com.pinde.sci.model.erp.*;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/erp/crm")
public class CrmController extends GeneralController {
    @Autowired
    private IErpCustomerBiz customerBiz;
    @Autowired
    private IErpCustomerUserBiz customerUserBiz;
    @Autowired
    private IErpContractBiz contractBiz;
    @Autowired
    private IErpCrmContractBillBalanceBiz billBalanceBiz;
    @Autowired
    private IErpContractProductBiz productBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IErpContractPowerBiz powerBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private IErpContractPayPlanBiz payPlanBiz;
    @Autowired
    private IErpCrmContractPayBalanceBiz payBalanceBiz;
    @Autowired
    private IErpContractRefBiz refBiz;
    @Autowired
    private IDeptBiz deptBiz;
    @Autowired
    private IUserBiz sysUserBiz;
    @Autowired
    private IErpContractUserBiz contractUserBiz;
    @Autowired
    private IErpContractUserRefBiz userRefBiz;
    @Autowired
    private IErpUserRegionPopedomBiz popedomBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IErpOAWorkOrderBiz workOrderBiz;
    @Autowired
    private IErpContactOrderBiz contactOrderBiz;
    @Autowired
    private IErpContractBillPlanBiz billPlanBiz;

    /**
     * 跳转至编辑客户页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/addCustomer/{userListScope}")
    public String addCustomer(@PathVariable String userListScope, Model model) {
        setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, userListScope);
        SysUser user = GlobalContext.getCurrentUser();
        List<ErpUserRegionPopedom> erpUserRegionPopedomList = popedomBiz.getErpUserRegionByUserFlow(user.getUserFlow());
        model.addAttribute("erpUserRegionPopedomList", erpUserRegionPopedomList);
        return "erp/crm/customer/add";
    }

    /**
     * 保存客户及联系人
     *
     * @param form
     * @return
     */
    @RequestMapping(value = "/saveCustomerAndUser")
    @ResponseBody
    public String saveCustomerAndUser(@RequestBody CustomerUserForm form) {
        int result = customerBiz.saveCustomerAndUser(form);
        if (result != GlobalConstant.ZERO_LINE) {
            return form.getCustomer().getCustomerFlow();
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 客户查询
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchCustomer/{userListScope}")
    public String searchCustomer(@PathVariable String userListScope, ErpCrmCustomer customer,
                                 String customerPhone,String bigRegionValue, Integer currentPage, String checkedFlow,
                                 String hosGradeFlag, HttpServletRequest request, Model model) {
        setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, userListScope);
        model.addAttribute("checkedFlow", checkedFlow);
        SysUser currUser = GlobalContext.getCurrentUser();
        //医院类型
        List<String> hospitalTypeIdList = new ArrayList<String>();
        if (StringUtil.isNotBlank(customer.getHospitalTypeId())) {
            String[] typeIds = customer.getHospitalTypeId().split(",");
            hospitalTypeIdList = Arrays.asList(typeIds);
            model.addAttribute("hospitalTypeIdList", hospitalTypeIdList);
            //重新组织医院类型
            if (typeIds != null && typeIds.length > 0) {
                String hospitalTypeId = "";
                for (int i = 0; i < typeIds.length; i++) {
                    hospitalTypeId += "%" + typeIds[i] + "%";
                }
                customer.setHospitalTypeId(hospitalTypeId);
            }
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customer", customer);
        paramMap.put("currUser", currUser);
        paramMap.put("userListScope", userListScope);
        paramMap.put("bigRegionValue", bigRegionValue);
        paramMap.put("customerPhone", customerPhone);
        paramMap.put("hosGradeFlag", StringUtil.defaultString(hosGradeFlag));
        List<ErpCrmCustomer> customerList = customerBiz.searchCustomerList(paramMap);
        model.addAttribute("customerList", customerList);

        List<ErpUserRegionPopedom> erpUserRegionPopedomList = popedomBiz.getErpUserRegionByUserFlow(currUser.getUserFlow());
        model.addAttribute("erpUserRegionPopedomList", erpUserRegionPopedomList);
        return "erp/crm/customer/list";
    }

    /**
     * 保存客户备注
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/modCustomerRemark")
    @ResponseBody
    public String modCustomerRemark(ErpCrmCustomer customer) {
        int result = customerBiz.saveCustomer(customer);
        if (result != GlobalConstant.ZERO_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 查看客户信息
     */
    @RequestMapping(value = "/customerInfo")
    public String customerInfo() {
        return "erp/crm/customer/view";
    }

    /**
     * 加载客户
     *
     * @param customerFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/customer")
    public String customer(String customerFlow, Model model) {
        if (StringUtil.isNotBlank(customerFlow)) {
            ErpCrmCustomer customer = customerBiz.readCustomer(customerFlow);
            model.addAttribute("customer", customer);
        }
        return "erp/crm/customer/customer";
    }

    /**
     * 加载联系人信息
     */
    @RequestMapping(value = "/customerUserList")
    public String customerUserList(ErpCrmCustomerUser customerUser, Model model) {
        if (StringUtil.isNotBlank(customerUser.getCustomerFlow())) {
            List<ErpCrmCustomerUser> customerUserList = customerUserBiz.searchCustomerUserList(customerUser);
            model.addAttribute("customerUserList", customerUserList);
            //技服只能编辑人员类别为“技术负责人”的联系人
            if (GlobalConstant.USER_LIST_PERSONAL.equals(getSessionAttribute(GlobalConstant.USER_LIST_SCOPE))) {
                Map<String, Boolean> userCategoryMap = new HashMap<String, Boolean>();
                ErpCrmContractUser temp = new ErpCrmContractUser();
                if (customerUserList != null && customerUserList.size() > 0) {
                    for (ErpCrmCustomerUser user : customerUserList) {
                        temp.setUserFlow(user.getUserFlow());
                        temp.setUserCategoryId(UserCategoryEnum.Technical.getId());
                        List<ErpCrmContractUser> userList = contractUserBiz.searchContractUserList(temp);
                        if (userList != null && userList.size() > 0) {
                            userCategoryMap.put(user.getUserFlow(), true);
                        } else {
                            userCategoryMap.put(user.getUserFlow(), false);
                        }
                    }
                }
                model.addAttribute("userCategoryMap", userCategoryMap);
            }
        }
        return "erp/crm/customer/customerUser";
    }

    /**
     * 跳转至编辑客户信息
     *
     * @param customerFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editCustomer")
    public String editCustomer(String customerFlow, Model model) {
        if (StringUtil.isNotBlank(customerFlow)) {
            ErpCrmCustomer customer = customerBiz.readCustomer(customerFlow);
            if (customer != null && StringUtil.isNotBlank(customer.getHospitalTypeId())) {
                String hospitalTypeId = customer.getHospitalTypeId();
                String[] hospitalTypeIds = hospitalTypeId.split(",");
                List<String> hospitalTypeIdList = Arrays.asList(hospitalTypeIds);
                model.addAttribute("hospitalTypeIdList", hospitalTypeIdList);
            }
            model.addAttribute("customer", customer);

            SysUser user = GlobalContext.getCurrentUser();
            List<ErpUserRegionPopedom> erpUserRegionPopedomList = popedomBiz.getErpUserRegionByUserFlow(user.getUserFlow());
            model.addAttribute("erpUserRegionPopedomList", erpUserRegionPopedomList);
        }
        return "erp/crm/customer/editCustomer";
    }

    /**
     * 跳转至编辑联系人信息列表
     *
     * @param customerFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editCustomerUserList")
    public String editCustomerUserList(String customerFlow, Model model) {
        if (StringUtil.isNotBlank(customerFlow)) {
            ErpCrmCustomerUser customerUser = new ErpCrmCustomerUser();
            customerUser.setCustomerFlow(customerFlow);
            customerUser.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<ErpCrmCustomerUser> customerUserList = customerUserBiz.searchCustomerUserList(customerUser);
            model.addAttribute("customerUserList", customerUserList);
        }
        model.addAttribute("customerFlow", customerFlow);
        return "erp/crm/customer/editCustomerUserList";
    }

    /**
     * 保存客户
     *
     * @param customer
     * @return
     */
    @RequestMapping(value = "/saveCustomer")
    @ResponseBody
    public String saveCustomer(ErpCrmCustomer customer) {
        int result = customerBiz.EditCustomer(customer);
        if (result != GlobalConstant.ZERO_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 保存联系人列表
     *
     * @param form
     * @param customerFlow
     * @return
     */
    @RequestMapping(value = "/saveCustomerUserList")
    @ResponseBody
    public String saveCustomerUserList(@RequestBody CustomerUserForm form, String customerFlow) {
        List<ErpCrmCustomerUser> userList = form.getCustomerUserList();
        int result = customerUserBiz.saveCustomerUserList(userList, customerFlow);
        if (result != GlobalConstant.ZERO_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 校验客户联系人姓名重复问题
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/customerUserNameConfirm")
    @ResponseBody
    public String customerUserNameConfirm(ErpCrmCustomerUser user) {
        String customerFlow = user.getCustomerFlow();
        List<ErpCrmCustomerUser> userList = customerBiz.searchCustomerUsers(customerFlow, user.getUserName());
        if (userList != null) {
            for (ErpCrmCustomerUser temp : userList) {
                if (!temp.getUserFlow().equals(user.getUserFlow())) {
                    return GlobalConstant.OPRE_FAIL;
                }
            }
        }
        return GlobalConstant.OPRE_SUCCESSED;
    }

    /**
     * 校验合同联系人姓名重复问题
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/contractUserNameConfirm")
    @ResponseBody
    public String contractUserNameConfirm(ErpCrmContractUser user) {
        //验证合同联系人是否已存在
        String contractFlow = user.getContractFlow();
        List<ErpCrmContractUser> contractUserList = contractUserBiz.searchContractUsers(contractFlow, user.getUserName());
        if (contractUserList != null) {
            for (ErpCrmContractUser temp : contractUserList) {
                if (!temp.getRecordFlow().equals(user.getRecordFlow())) {
                    return "contractExsit";
                }
            }
        }
        //验证客户联系人是否已存在
        ErpCrmContract contract = contractBiz.readContract(contractFlow);
        String customerFlow = contract.getCustomerFlow();
        List<ErpCrmCustomerUser> userList = customerBiz.searchCustomerUsers(customerFlow, user.getUserName());
        if (userList != null) {
            for (ErpCrmCustomerUser temp : userList) {
                if (!temp.getUserFlow().equals(user.getUserFlow()) && !temp.getUserFlow().equals(user.getRecordFlow())) {
                    return "customerExsit";
                }
            }
        }
        return GlobalConstant.OPRE_SUCCESSED;
    }

    /**
     * 保存单个联系人
     *
     * @param customerUser
     * @return
     */
    @RequestMapping(value = "/saveCustomerUser")
    @ResponseBody
    public String saveCustomerUser(ErpCrmCustomerUser customerUser) {
        if (StringUtil.isNotBlank(customerUser.getSexId())) {
            customerUser.setSexName(UserSexEnum.getNameById(customerUser.getSexId()));
        } else {
            customerUser.setSexName("");
        }
        int result = customerUserBiz.saveCustomerUser(customerUser);
        if (result != GlobalConstant.ZERO_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 删除单个联系人
     *
     * @param customerUser
     * @return
     */
    @RequestMapping(value = "/deleteCustomerUser")
    @ResponseBody
    public String deleteCustomerUser(String userFlow) {
        return this.customerUserBiz.deleteCustomerUser(userFlow);
    }


    /**
     * 停用、启用联系人
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/disableUser")
    @ResponseBody
    public String disableUser(String userFlow, String recordStatus) {
        if (StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(recordStatus)) {
            ErpCrmCustomerUser customerUser = new ErpCrmCustomerUser();
            customerUser.setUserFlow(userFlow);
            customerUser.setRecordStatus(recordStatus);
            int result = customerUserBiz.updateCustomerUser(customerUser);
            if (result != GlobalConstant.ZERO_LINE) {
                return GlobalConstant.OPERATE_SUCCESSED;
            }
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 联系人详细信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/editCustomerUser")
    public String editCustomerUser(String userFlow, Model model) {
        if (StringUtil.isNotBlank(userFlow)) {
            ErpCrmCustomerUser customerUser = customerUserBiz.readCustomerUser(userFlow);
            model.addAttribute("customerUser", customerUser);
        }
        return "erp/crm/customer/editCustomerUser";
    }

    /**
     * 验证客户唯一
     *
     * @param customer
     * @param userFlow
     * @return
     */
    @RequestMapping(value = "/checkCustomer")
    @ResponseBody
    public String checkCustomer(ErpCrmCustomer customer) {
        ErpCrmCustomer existCustomer = null;
        String customerName = customer.getCustomerName();
        if (StringUtil.isNotBlank(customerName)) {
            existCustomer = customerBiz.findCustomerByCustomerName(customerName);
            if (existCustomer != null) {
                String customerFlow = customer.getCustomerFlow();
                if (StringUtil.isNotBlank(customerFlow)) {
                    if (!existCustomer.getCustomerFlow().equals(customerFlow)) {
                        return GlobalConstant.CRM_CUSTOMER_NAME_EXIST;
                    }
                } else {
                    return GlobalConstant.CRM_CUSTOMER_NAME_EXIST;
                }
            }
        }
        return GlobalConstant.FLAG_Y;
    }

    /**
     * 删除客户
     *
     * @param customerFlow
     * @return
     */
    @RequestMapping(value = "/deleteCustomer")
    @ResponseBody
    public String deleteCustomer(String customerFlow) {
        if (StringUtil.isNotBlank(customerFlow)) {
            ErpCrmContract contract = new ErpCrmContract();
            contract.setCustomerFlow(customerFlow);
            List<ErpCrmContract> contractList = contractBiz.searchErpContractList(contract,null);
            if (contractList != null && !contractList.isEmpty()) {
                return GlobalConstant.FLAG_N;
            } else {
                ErpCrmCustomer customer = new ErpCrmCustomer();
                customer.setCustomerFlow(customerFlow);
                customer.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
                int result = customerBiz.saveCustomer(customer);
                if (result != GlobalConstant.ZERO_LINE) {
                    return GlobalConstant.DELETE_SUCCESSED;
                }
            }
        }
        return GlobalConstant.DELETE_FAIL;
    }

    //*************************************************************************************

    /**
     * 合同新增页面
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/editContract")
    public String editContract(Model model) {
        List<SysDept> deptList = searchDeptList();
        model.addAttribute("deptList", deptList);
        List<ErpCrmCustomer> consumerList = searchCustomerJsonByType();

//        <option value="f48a5060931147daa467eadbb5885629">总经办</option>
//        <option value="a8a0aeef43c846c4a976589184b4ed8b">销售一部</option>
//        <option value="a02a8190fc58440f8635b7c5cfcd1949">销售二部</option>
//        <option value="d7f1afc58416446ba414d3c69bb204e2">销售三部</option>
//        <option value="5b42e66a13e742d3a796f17d8104e02d">销售四部</option>
//        <option value="1f56f216c3fd4dc4a1b23e7dda645c0b">销售五部</option>
        List<String> deptFlows=new ArrayList<>();
        deptFlows.add("f48a5060931147daa467eadbb5885629");
        deptFlows.add("a8a0aeef43c846c4a976589184b4ed8b");
        deptFlows.add("a02a8190fc58440f8635b7c5cfcd1949");
        deptFlows.add("d7f1afc58416446ba414d3c69bb204e2");
        deptFlows.add("5b42e66a13e742d3a796f17d8104e02d");
        deptFlows.add("1f56f216c3fd4dc4a1b23e7dda645c0b");
        List<SysUser> users=userBiz.getUserByDeptFlows(deptFlows);
        model.addAttribute("users", users);
        model.addAttribute("consumerList", consumerList);
        return "erp/crm/contract/edit";
    }


    @RequestMapping(value = "/updateToCustomerUser")
    @ResponseBody
    public String updateToCustomerUser(String recordFlow, String customerFlow) {
        ErpCrmContractUser contractUser = this.contractUserBiz.readContractUser(recordFlow);
        if (contractUser != null) {
            ErpCrmCustomerUser customerUser = new ErpCrmCustomerUser();
            customerUser.setUserFlow(contractUser.getRecordFlow());
            customerUser.setUserName(contractUser.getUserName());
            customerUser.setCustomerFlow(customerFlow);
            if (StringUtil.isNotBlank(contractUser.getSexId())) {
                customerUser.setSexName(UserSexEnum.getNameById(contractUser.getSexId()));
            }
            customerUser.setIdNo(contractUser.getIdNo());
            customerUser.setBirthday(contractUser.getBirthday());
            customerUser.setDeptName(contractUser.getDeptName());
            customerUser.setPostName(contractUser.getPostName());
            customerUser.setUserTelphone(contractUser.getUserTelphone());
            customerUser.setUserCelphone(contractUser.getUserCelphone());
            customerUser.setUserEmail(contractUser.getUserEmail());
            customerUser.setUserQq(contractUser.getUserQq());
            customerUser.setRemark(contractUser.getRemark());
            customerUser.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            int result = this.customerUserBiz.saveCustomerUser(customerUser);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.OPERATE_SUCCESSED;
            }
        }
        return GlobalConstant.OPRE_FAIL;
    }

    /**
     * 查询类型为企业的客户
     *
     * @param userFlows
     * @return
     */
    @RequestMapping(value = "/searchCustomerJsonByType")
    @ResponseBody
    public List<ErpCrmCustomer> searchCustomerJsonByType() {
        ErpCrmCustomer customer = new ErpCrmCustomer();
        customer.setCustomerTypeId(CustomerTypeEnum.Enterprise.getId());
        List<ErpCrmCustomer> customerList = this.customerBiz.searchCustomer(customer);
        return customerList;
    }


    /**
     * 关联客户联系人页面
     *
     * @param customerFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchCustomerLinkManList")
    public String searchCustomerLinkManList(String customerFlow, Model model) {
        if (StringUtil.isNotBlank(customerFlow)) {
            ErpCrmCustomerUser customerUser = new ErpCrmCustomerUser();
            customerUser.setCustomerFlow(customerFlow);
            customerUser.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            List<ErpCrmCustomerUser> customerUserList = customerUserBiz.searchCustomerUserList(customerUser);
            model.addAttribute("customerUserList", customerUserList);
        }
        return "erp/crm/contract/customerUserList";
    }

    /**
     * 根据联系人流水号查询客户联系人
     *
     * @param userFlows
     * @return
     */
    @RequestMapping(value = "/searchCustomerUserByUserFlows")
    @ResponseBody
    public List<ErpCrmCustomerUser> searchCustomerUserByUserFlows(String userFlows) {
        List<ErpCrmCustomerUser> userList = this.customerUserBiz.searchUsersByUserFlows(userFlows);
        return userList;
    }

    /**
     * 保存封装过的合同联系人
     *
     * @param userFlows
     * @param contractFlow
     * @return
     */
    @RequestMapping(value = "/searchAndSaveCustomerUserByUserFlows")
    @ResponseBody
    public String searchAndSaveCustomerUserByUserFlows(String userFlows, String contractFlow) {
        List<ErpCrmCustomerUser> userList = this.customerUserBiz.searchUsersByUserFlows(userFlows);
        String result = this.contractUserBiz.saveCustomerAndContractUser(userList, contractFlow);
        if (!GlobalConstant.SAVE_SUCCESSED.equals(result)) {
            return GlobalConstant.SAVE_FAIL;
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    /**
     * 查询当前登录者所在机构的部门
     *
     * @param model
     * @return
     */
    public List<SysDept> searchDeptList() {
        SysDept dept = new SysDept();
        dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDept> deptList = this.deptBiz.searchDept(dept);
        return deptList;
    }

    @RequestMapping(value={"/savePowerFile"})
    @ResponseBody
    public Object savePowerFile(MultipartFile file,String fileFlow,String isTemp) throws Exception{
        Map<String,String> map=new HashMap<>();
        String result = fileBiz.addContractPowerFile(file,fileFlow,isTemp);
        if(result != "fail"){
            map.put("result",GlobalConstant.UPLOAD_SUCCESSED);
            map.put("fileFlow",result);
        }else{
            map.put("result",GlobalConstant.UPLOAD_FAIL);
            map.put("fileFlow",result);
        }
        return map;
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
        ContractForm contractForm = JSON.parseObject(jsondata, ContractForm.class);
        ErpCrmContract contract = contractForm.getContract();
        List<ErpCrmContractProduct> productList = contractForm.getProductList();
        List<ErpCrmCustomerUser> userList = contractForm.getUserList();
        List<ErpCrmContractPayPlan> payPlanList = contractForm.getPayPlanList();
        List<ErpCrmContractBillPlan> billPlanList = contractForm.getBillPlanList();
        List<ErpCrmContractRef> refList = contractForm.getRefList();
        List<ErpCrmContractPower> powerList = contractForm.getPowerList();
        List<ErpCrmContractOtherPower> otherPowerList = contractForm.getOtherPowerList();
        if (StringUtil.isNotBlank(refType) && GlobalConstant.RECORD_STATUS_Y.equals(refType)) {
            String result = this.refBiz.updateOneContractRef(contract.getContractFlow());
            if (!GlobalConstant.SAVE_SUCCESSED.equals(result)) {
                return GlobalConstant.SAVE_FAIL;
            }
        }
        String contractFlow = this.contractBiz.saveContractInfo(replenishContract(contract), replenishContractProduct(productList), replenishCustomerUser(userList), file,
                payPlanList, refList, billPlanList,powerList,otherPowerList);
        return contractFlow;
    }

    //保存权限
    @RequestMapping(value = "/saveContractPowerFiles")
    @ResponseBody
    public String saveContractPowerFiles(String jsondata,String contractFlow,  HttpServletRequest request) throws IOException {

        ContractForm contractForm = JSON.parseObject(jsondata, ContractForm.class);
        List<ErpCrmContractPowerExt> powerList = contractForm.getPowerExtList();
        this.powerBiz.saveContractPowerFiles(powerList,contractFlow);
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = "/saveContractUsers")
    @ResponseBody
    public String saveContractUsers(String jsondata, HttpServletRequest request) {
        ContractForm contractForm = JSON.parseObject(jsondata, ContractForm.class);
        ErpCrmContract contract = contractForm.getContract();
        List<ErpCrmCustomerUser> userList = contractForm.getUserList();
        return this.contractUserBiz.saveCustomerAndContractUser(replenishCustomerUser(userList), contract.getContractFlow());
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
    public List<ErpCrmCustomerUser> replenishCustomerUser(List<ErpCrmCustomerUser> userList) {
        if (userList != null && !userList.isEmpty()) {
            for (ErpCrmCustomerUser contractUser : userList) {
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

    /**
     * 查询所有客户信息
     *
     * @return
     */
    @RequestMapping(value = "/searchCustomerJson")
    @ResponseBody
    public List<ErpCrmCustomer> searchCustormerJson() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return this.customerBiz.searchCustomerList(paramMap);
    }

    /**
     * 查询所有客户信息，过滤地区权限
     *
     * @return
     */
    @RequestMapping(value = "/searchCustomerForAreaJson")
    @ResponseBody
    public List<ErpCrmCustomer> searchCustomerForAreaJson() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("currUser", GlobalContext.getCurrentUser());
        return this.customerBiz.searchCustomerList(paramMap);
    }

    /**
     * 查询所有派工单
     *
     * @return
     */
    @RequestMapping(value = "/searchWorkNoJson")
    @ResponseBody
    public List<ErpOaWorkOrder> searchWorkNoJson() {
        return this.workOrderBiz.searchWorkOrderList(new ErpOaWorkOrder());
    }

    /**
     * 查询所有合同信息
     *
     * @return
     */
    @RequestMapping(value = "/searchContractJson")
    @ResponseBody
    public List<ErpCrmContract> searchContractJson() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return this.contractBiz.searchContractsList(paramMap);
    }

    /**
     * 查询所有合同信息
     *
     * @return
     */
    @RequestMapping(value = "/searchOrderContractJson")
    @ResponseBody
    public List<ErpOrderCustomerExt> searchOrderContractJson() {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        return this.contactOrderBiz.searchContactOrderList(paramMap);
    }

    /**
     * 查询当前部门的人员信息
     *
     * @return
     */
    @RequestMapping(value = "/searchDeptUserJson")
    @ResponseBody
    public List<SysUser> searchDeptUserJson(String deptFlow) {
        if (StringUtil.isNotBlank(deptFlow)) {
            SysUser sysUser = new SysUser();
            sysUser.setDeptFlow(deptFlow);
            sysUser.setStatusId(UserStatusEnum.Activated.getId());
            List<SysUser> userList = this.sysUserBiz.searchUser(sysUser);
            return userList;
        }
        return null;
    }
    /**
     * 查询当前部门的人员信息包括离职人员
     *
     * @return
     */
    @RequestMapping(value = "/searchDeptUserJsonNew")
    @ResponseBody
    public List<SysUser> searchDeptUserJsonNew(String deptFlow) {
        if (StringUtil.isNotBlank(deptFlow)) {
            SysUser sysUser = new SysUser();
            sysUser.setDeptFlow(deptFlow);
            List<SysUser> userList = this.sysUserBiz.searchUser(sysUser);
            return userList;
        }
        return null;
    }

    @RequestMapping(value = "/searchPopdom")
    @ResponseBody
    public List<String> searchPopdom() {
        SysUser currUser = GlobalContext.getCurrentUser();
        ErpUserRegionPopedom popdom = new ErpUserRegionPopedom();
        popdom.setUserFlow(currUser.getUserFlow());
        List<String> provIdList = new ArrayList<String>();
        List<ErpUserRegionPopedom> popdomList = this.popedomBiz.searchRegionPopList(popdom);
        if (popdomList != null && !popdomList.isEmpty()) {
            for (ErpUserRegionPopedom pop : popdomList) {
                provIdList.add(pop.getProvId());
            }
        }
        return provIdList;
    }

    /**
     * 查询合同列表
     *
     * @param currentPage
     * @param contract
     * @param customerName
     * @param model
     * @return
     */
    @RequestMapping(value = "/searchContract/{userListScope}")
    public String searchContract(@PathVariable String userListScope, Integer currentPage, ErpCrmContract contract, ErpCrmCustomer customer,
                                 String bigRegionTypeId, ContractTimeForm timeForm, String noSecond, String maintainDue,String createDateEnd,
                                 String createDateStart,String notCG,
                                 HttpServletRequest request, Model model, String isLike, String orderAction, String orderByCase) {
        setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, userListScope);
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
        if (GlobalConstant.USER_LIST_GLOBAL.equals(userListScope) || GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {
            List<SysDept> deptList = searchDeptList();
            model.addAttribute("deptList", deptList);
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) {
            contract.setSignDeptFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        if (GlobalConstant.USER_LIST_PERSONAL.equals(userListScope)) {
            contract.setChargeUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            contract.setChargeUser2Flow(GlobalContext.getCurrentUser().getUserFlow());
            paramMap.put("owner", "owner");
        }
        paramMap.put("contract", contract);
        paramMap.put("createDateEnd", createDateEnd);
        paramMap.put("createDateStart", createDateStart);
        paramMap.put("customer", customer);
        paramMap.put("noSecond", StringUtil.defaultString(noSecond));
        paramMap.put("maintainDue", StringUtil.defaultString(maintainDue));
        paramMap.put("currDate", DateUtil.getCurrDate());
        paramMap.put("isLike", isLike);
        paramMap.put("notCG", notCG);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ErpCrmContractExt> contractListExt = this.contractBiz.searchErpContractListByCondition(paramMap);
        BigDecimal allmoney = this.contractBiz.searchAllMoney(paramMap);
        if (allmoney == null) {
            model.addAttribute("allMoney", 0);
        } else {
            model.addAttribute("allMoney", allmoney);
        }
        model.addAttribute("contractListExt", contractListExt);
        model.addAttribute("noSecond", StringUtil.defaultString(noSecond));
        model.addAttribute("maintainDue", StringUtil.defaultString(maintainDue));
        model.addAttribute("isLike", isLike);
        model.addAttribute("notCG", notCG);

        SysUser sysUser = GlobalContext.getCurrentUser();
        String userFlow = sysUser.getUserFlow();
        String isCaiWu = GlobalConstant.FLAG_N;
        String wsid = GlobalConstant.ERP_WS_ID;
        List<SysUserRole> userRoles = userRoleBiz.getByUserFlowAndWsid(userFlow, wsid);
        if (userRoles != null && userRoles.size() > 0) {
            for (SysUserRole userRole : userRoles) {
                String roleFlow = userRole.getRoleFlow();
                SysRole role = userRoleBiz.read(roleFlow);
                if(roleFlow.equals("0fc9ef67ac294fcf9bf61d2fa5db769b")||roleFlow.equals("06bc9451af734b379b66abb4976837f0")){
                    isCaiWu = GlobalConstant.FLAG_Y;
                }
            }
        }
        model.addAttribute("isCaiWu", isCaiWu);
        return "erp/crm/contract/list";
    }

    /**
     * 导出
     *
     * @param currentPage
     * @param contract
     * @param customerName
     * @param model
     * @return
     */
    @RequestMapping(value = "/exportExcel/{userListScope}")
    public void exportExcel(@PathVariable String userListScope, ErpCrmContract contract, ErpCrmCustomer customer,
                            String bigRegionTypeId, ContractTimeForm timeForm, String noSecond, String maintainDue,
                            HttpServletRequest request, Model model, String isLike, HttpServletResponse res) {
        setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, userListScope);
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
        if (GlobalConstant.USER_LIST_GLOBAL.equals(userListScope) || GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {
            List<SysDept> deptList = searchDeptList();
            model.addAttribute("deptList", deptList);
        }
        if (GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) {
            contract.setSignDeptFlow(GlobalContext.getCurrentUser().getDeptFlow());
        }
        if (GlobalConstant.USER_LIST_PERSONAL.equals(userListScope)) {
            contract.setSignDeptFlow(GlobalContext.getCurrentUser().getDeptFlow());
            contract.setChargeUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        }
        paramMap.put("contract", contract);
        paramMap.put("customer", customer);
        paramMap.put("noSecond", StringUtil.defaultString(noSecond));
        paramMap.put("maintainDue", StringUtil.defaultString(maintainDue));
        paramMap.put("currDate", DateUtil.getCurrDate());
        paramMap.put("isLike", isLike);
        List<ErpCrmContractExt> contractListExt = this.contractBiz.searchErpContractListByCondition(paramMap);
        List<Map<String, Object>> resultList = new ArrayList<>();
        if (contractListExt != null && contractListExt.size() > 0) {
            for (ErpCrmContractExt ext : contractListExt) {
                Map<String, Object> map = new HashMap<>();
                map.put("contractNo", ext.getContractNo());//合同号
                map.put("contractArchivesNo", ext.getContractArchivesNo());//合同档案号
                map.put("customerName", ext.getCustomer().getCustomerName());//客户名称
                map.put("contractName", ext.getContractName());//合同名称
                map.put("contractCategoryName", ext.getContractCategoryName());//合同类别
                map.put("contractTypeName", ext.getContractTypeName());//合同类型
                map.put("signDeptName", ext.getSignDeptName());//负责部门
                map.put("chargeUserName", ext.getChargeUserName());//合同负责人
                map.put("contractStatusName", ext.getContractStatusName());//合同状态
                map.put("signDate", ext.getSignDate());//合同签订日期
                map.put("createTime", DateUtil.transDateTime(ext.getCreateTime(), "yyyyMMddHHmmss", "yyyy-MM-dd"));//合同创建日期
                SysUser user = sysUserBiz.readSysUser(ext.getCreateUserFlow());
                if (user != null) {
                    map.put("createName", user.getUserName());//合同创建人
                } else {
                    map.put("createName", "");//合同创建人
                }
                map.put("contractDueDate", ext.getContractDueDate());//合同到期日
                map.put("maintainDueDate", ext.getMaintainDueDate());//维护到期日
                map.put("contractFund", String.valueOf(ext.getContractFund().doubleValue()));//合同金额

                String contractFlow = ext.getContractFlow();
                if (StringUtil.isNotBlank(contractFlow)) {
                    //合同已开票金额	=	合同已开发票金额小计
                    //合同未开票金额	=	合同金额	-	合同已开票金额小计
                    //逾期到款天数	=	实际到款日期	-	计划到款日期
                    //逾期金额	=	合同实际到款金额	-	合同计划到款金额
                    //合同应收余额	=	合同金额	-	合同实际到款金额小计
                    //财务应收余额	=	合同已开票金额小计	-	合同实际到款金额小计

                    //合同已开票金额
                    BigDecimal billMoney = billBalanceBiz.getAllBillMoneyByContractFlow(contractFlow);
                    map.put("billMoney", String.valueOf(billMoney));

                    //合同未开票金额
                    BigDecimal contractFund = ext.getContractFund();
                    map.put("notBillMoney", String.valueOf(contractFund.subtract(billMoney).doubleValue()));

                    //逾期到款天数
                    ErpCrmContractPayPlan payPlan = new ErpCrmContractPayPlan();
                    payPlan.setContractFlow(contractFlow);
                    int count = 0;
                    //到款计划
                    List<ErpCrmContractPayPlan> payPlenList = payPlanBiz.searchContractPayPlanList(payPlan);
                    if (payPlenList != null && payPlenList.size() > 0) {
                        for (ErpCrmContractPayPlan payPlan1 : payPlenList) {
                            //每个计划对应多个实际到款
                            ErpCrmContractPayBalance balance = new ErpCrmContractPayBalance();
                            balance.setContractFlow(contractFlow);
                            balance.setPlanFlow(payPlan1.getPlanFlow());
                            List<ErpCrmContractPayBalanceExt> balanceList = payBalanceBiz.searchBalanceList(balance);
                            String jihuaTime = payPlan1.getPlanDate();
                            String shijiTime = "0000-00-00";
                            for (ErpCrmContractPayBalance payBalance : balanceList) {
                                if (payBalance.getPayDate().compareTo(shijiTime) > 0) {
                                    shijiTime = payBalance.getPayDate();
                                }
                            }
                            try {
                                if(!shijiTime.equals("0000-00-00")) {
                                    Calendar calendar = Calendar.getInstance();
                                    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
                                    Date sday = formatDate.parse(shijiTime);
                                    Date eday = formatDate.parse(jihuaTime);
                                    count += getDay(sday, eday);
                                }
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    map.put("yuqiDayCount", String.valueOf(count));
                    //逾期金额
                    BigDecimal shijiDaokuan = payBalanceBiz.getContractBalanceMoney(contractFlow);//合同实际到款金额
                    BigDecimal jihuaDaokuan = payBalanceBiz.getContractPlanMoney(contractFlow);//合同计划到款金额
                    map.put("yuqiMoney", String.valueOf(shijiDaokuan.subtract(jihuaDaokuan).doubleValue()));
                    //合同应收余额
                    map.put("contractYEMoney", String.valueOf(contractFund.subtract(shijiDaokuan).doubleValue()));
                    //财务应收余额
                    map.put("caiwuYEMoney", String.valueOf(billMoney.subtract(shijiDaokuan).doubleValue()));
                }
                resultList.add(map);
            }
        }
        String fileName = "财务汇总及明细.xls";
        createExcle(res, fileName, resultList);
    }

    public void createExcle(HttpServletResponse response, String fileName, List<Map<String, Object>> resultList){
        //创建工作簿
        HSSFWorkbook wb = new HSSFWorkbook();
        // 为工作簿添加sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        //定义将用到的样式
        HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
        styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
        styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
        stylevwc.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        stylevwc.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        try {
            //列宽自适应
            HSSFRow rowDep = sheet.createRow(0);

            String[] titles = new String[]{
                    "合同号",
                    "合同档案号",
                    "客户名称",
                    "合同名称",
                    "合同类别",
                    "合同类型",
                    "负责部门",
                    "合同负责人",
                    "合同状态",
                    "合同签订日期",
                    "合同创建日期",
                    "合同创建人",
                    "合同到期日",
                    "合同维护到期日",
                    "合同金额",
                    "合同已开票金额",
                    "合同未开票金额",
                    "逾期到款天数",
                    "逾期金额",
                    "合同应收余额",
                    "财务应收余额"
            };
            HSSFCell cellTitle = null;
            for (int i = 0; i < titles.length; i++) {
                cellTitle = rowDep.createCell(i);
                cellTitle.setCellValue(titles[i]);
                cellTitle.setCellStyle(styleCenter);
            }
            int rownum = 1;
            if (resultList != null && !resultList.isEmpty()) {
                for (Map<String, Object> map : resultList) {
                    HSSFRow rowDepts = sheet.createRow(rownum);
                    HSSFCell cell = rowDepts.createCell(0);
                    cell.setCellValue((String) map.get("contractNo"));
                    cell.setCellStyle(styleCenter);
                    HSSFCell cell1 = rowDepts.createCell(1);
                    cell1.setCellValue((String) map.get("contractArchivesNo"));
                    cell1.setCellStyle(styleCenter);
                    HSSFCell cell2 = rowDepts.createCell(2);
                    cell2.setCellValue((String) map.get("customerName"));
                    cell2.setCellStyle(styleCenter);
                    HSSFCell cell3 = rowDepts.createCell(3);
                    cell3.setCellValue((String) map.get("contractName"));
                    cell3.setCellStyle(styleCenter);
                    HSSFCell cell4 = rowDepts.createCell(4);
                    cell4.setCellValue((String) map.get("contractCategoryName"));
                    cell4.setCellStyle(styleCenter);
                    HSSFCell cell5 = rowDepts.createCell(5);
                    cell5.setCellValue((String) map.get("contractTypeName"));
                    cell5.setCellStyle(styleCenter);
                    HSSFCell cell6 = rowDepts.createCell(6);
                    cell6.setCellValue((String) map.get("signDeptName"));
                    cell6.setCellStyle(styleCenter);
                    HSSFCell cell7 = rowDepts.createCell(7);
                    cell7.setCellValue((String) map.get("chargeUserName"));
                    cell7.setCellStyle(styleCenter);
                    HSSFCell cell8 = rowDepts.createCell(8);
                    cell8.setCellValue((String) map.get("contractStatusName"));
                    cell8.setCellStyle(styleCenter);
                    HSSFCell cell9 = rowDepts.createCell(9);
                    cell9.setCellValue((String) map.get("signDate"));
                    cell9.setCellStyle(styleCenter);
                    HSSFCell cell10 = rowDepts.createCell(10);
                    cell10.setCellValue((String) map.get("createTime"));
                    cell10.setCellStyle(styleCenter);
                    HSSFCell cell11 = rowDepts.createCell(11);
                    cell11.setCellValue((String) map.get("createName"));
                    cell11.setCellStyle(styleCenter);
                    HSSFCell cell12 = rowDepts.createCell(12);
                    cell12.setCellValue((String) map.get("contractDueDate"));
                    cell12.setCellStyle(styleCenter);
                    HSSFCell cell13 = rowDepts.createCell(13);
                    cell13.setCellValue((String) map.get("maintainDueDate"));
                    cell13.setCellStyle(styleCenter);
                    HSSFCell cell14 = rowDepts.createCell(14);
                    cell14.setCellValue((String) map.get("contractFund"));
                    cell14.setCellStyle(styleCenter);
                    HSSFCell cell15 = rowDepts.createCell(15);
                    cell15.setCellValue((String) map.get("billMoney"));
                    cell15.setCellStyle(styleCenter);
                    HSSFCell cell16 = rowDepts.createCell(16);
                    cell16.setCellValue((String) map.get("notBillMoney"));
                    cell16.setCellStyle(styleCenter);
                    HSSFCell cell17 = rowDepts.createCell(17);
                    cell17.setCellValue((String) map.get("yuqiDayCount"));
                    cell17.setCellStyle(styleCenter);
                    HSSFCell cell18 = rowDepts.createCell(18);
                    cell18.setCellValue((String) map.get("yuqiMoney"));
                    cell18.setCellStyle(styleCenter);
                    HSSFCell cell19 = rowDepts.createCell(19);
                    cell19.setCellValue((String) map.get("contractYEMoney"));
                    cell19.setCellStyle(styleCenter);
                    HSSFCell cell20 = rowDepts.createCell(20);
                    cell20.setCellValue((String) map.get("caiwuYEMoney"));
                    cell20.setCellStyle(styleCenter);
                    rownum++;
                }
            }
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
            response.setContentType("application/octet-stream;charset=UTF-8");
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 一天中的天数
     */
    public static long millionSecondsOfDay = 86400000;
    /**
     * 一天中的小时
     */
    public static long millionSecondsOfHour = 3600000;

    /**
     * 得到两个日期之间的天数,两头不算,取出数据后，可以根据需要再加
     *
     * @param date1
     * @param date2
     * @return
     * @author lihengjun
     */
    public static int getDay(Date date1, Date date2) {
        Long d2 = date2.getTime();
        Long d1 = date1.getTime();
        return (int) ((d2 - d1) / millionSecondsOfDay);
    }

    /**
     * 查询单个合同详细信息
     *
     * @param contractFlow
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/contractInfo")
    public String contractInfo(String contractFlow, ErpCrmCustomerUser user, Model model) {
        Map<String, Object> resultMap = this.contractBiz.readContractExt(contractFlow, user);
        model.addAttribute("resultMap", resultMap);
        //判断是否有资格看注册文件有效期
        String isShow = GlobalConstant.FLAG_N;
        String isCaiWu = GlobalConstant.FLAG_N;
        String isShangWu = GlobalConstant.FLAG_N;
        List roles = new ArrayList();
        roles.add("总经理");
        roles.add("商务主管");
        roles.add("技术部经理");
        roles.add("服务部经理主管");
        SysUser sysUser = GlobalContext.getCurrentUser();
        String userFlow = sysUser.getUserFlow();
        String wsid = GlobalConstant.ERP_WS_ID;
        List<SysUserRole> userRoles = userRoleBiz.getByUserFlowAndWsid(userFlow, wsid);
        if (userRoles != null && userRoles.size() > 0) {
            for (SysUserRole userRole : userRoles) {
                String roleFlow = userRole.getRoleFlow();
                SysRole role = userRoleBiz.read(roleFlow);
                String roleName = role.getRoleName();
                if (roles.contains(roleName)) {
                    isShow = GlobalConstant.FLAG_Y;
                }
                if("商务主管".equals(roleName))
                {
                    isShangWu = GlobalConstant.FLAG_Y;
                }
                if(roleFlow.equals("750f3982705e478f99cc9fe49e78893d")||roleFlow.equals("edbc86503ed34af8aa62b7f1d8813526")){
                    isCaiWu = GlobalConstant.FLAG_Y;
                }
            }
        }
        model.addAttribute("isShangWu", isShangWu);
        model.addAttribute("isShow", isShow);
        model.addAttribute("isCaiWu", isCaiWu);
        return "erp/crm/contract/contractInfo";
    }

    //下载附件
    @RequestMapping(value = {"/downFile" }, method = RequestMethod.GET)
    public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
        PubFile file = this.fileBiz.readFile(fileFlow);
        downPubFile(file,response);
    }

    public void downPubFile(PubFile file, HttpServletResponse response) throws Exception {
		/*文件是否存在*/
        Boolean fileExists = false;
        if(file !=null){
            byte[] data=null;
            long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
            if(StringUtil.isNotBlank(file.getFilePath())&&StringUtil.isNotBlank(InitConfig.getSysCfg("upload_base_dir"))){
                /*获取文件物理路径*/
                String filePath =InitConfig.getSysCfg("upload_base_dir")+file.getFilePath();
                File downLoadFile = new File(filePath);
                /*文件是否存在*/
                if(downLoadFile.exists()){
                    InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
                    data = new byte[fis.available()];
                    dataLength = downLoadFile.length();
                    fis.read(data);
                    fis.close();
                    fileExists = true;
                }
            }
            if(fileExists) {
                try {

                    String fileName = file.getFileName();
                    response.reset();
                    response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" )  + "\"");
                    response.addHeader("Content-Length", "" + dataLength);
                    response.setContentType("application/octet-stream;charset=UTF-8");
                    OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
                    if (data != null) {
                        outputStream.write(data);
                    }
                    outputStream.flush();
                    outputStream.close();
                }catch (IOException e){
                    fileExists = false;
                }
            }
        }else {
            fileExists = false;
        }
        if(!fileExists){
            /*设置页面编码为UTF-8*/
            response.setHeader("Content-Type","text/html;charset=UTF-8");
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
            outputStream.flush();
            outputStream.close();
        }
    }
    /**
     * 编辑合同信息
     *
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editContractInfo")
    public String editContractInfo(String contractFlow, Model model) {
        List<SysDept> deptList = searchDeptList();
        model.addAttribute("deptList", deptList);
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
            //查询关联主合同信息
            List<ErpCrmContractRefExt> mainRefExtList = this.refBiz.searchContractListByRef(contractFlow, null);
            model.addAttribute("refExtList", mainRefExtList);
            List<ErpCrmCustomer> customerList = new ArrayList<ErpCrmCustomer>();
            List<String> customerFlowList = new ArrayList<String>();
            Map<String, Object> contractMap = new HashMap<String, Object>();
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
            List<ErpCrmCustomer> consumerList = searchCustomerJsonByType();
            model.addAttribute("consumerList", consumerList);
        }
        return "erp/crm/contract/editContractInfo";
    }

    /**
     * 列表页面子合同
     *
     * @param contractFlow
     * @param checkContractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/appendList")
    public String appendList(String contractFlow, String checkContractFlow, String signDeptFlow, Model model) {
        List<ErpCrmContractRefExt> refExtList = this.refBiz.searchContractListByRef(null, contractFlow);
        List<String> refContractFlowList = new ArrayList<String>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (refExtList != null && !refExtList.isEmpty()) {
            for (ErpCrmContractRefExt ref : refExtList) {
                refContractFlowList.add(ref.getContract().getContractFlow());
            }
        }
        paramMap.put("refContractFlowList", refContractFlowList);
        List<ErpCrmContractExt> contractExtList = this.contractBiz.searchErpContractListByCondition(paramMap);
        model.addAttribute("contractExtList", contractExtList);
        model.addAttribute("contractFlow", contractFlow);
        model.addAttribute("checkContractFlow", checkContractFlow);
        model.addAttribute("signDeptFlow", signDeptFlow);

        return "erp/crm/contract/appendList";
    }

    /**
     * 编辑合同产品
     *
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editProduct")
    public String editProduct(String contractFlow, Model model) {
        ErpCrmContractProduct product = new ErpCrmContractProduct();
        product.setContractFlow(contractFlow);
        List<ErpCrmContractProduct> productList = this.productBiz.searchContactProductList(product);
        model.addAttribute("productList", productList);
        ErpCrmContract contract = this.contractBiz.readContract(contractFlow);
        model.addAttribute("contract", contract);
        return "erp/crm/contract/editProduct";
    }

    /**
     * 删除合同产品
     *
     * @param productFlows
     * @param model
     * @return
     */
    @RequestMapping(value = "/delProduct")
    @ResponseBody
    public String delProduct(String productFlows, Model model) {
        return this.productBiz.deleteContractProduct(productFlows);
    }

    /**
     * 编辑合同联系人信息
     *
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editLinkMan")
    public String editLinkMan(String contractFlow, Model model) {
        ErpCrmContract contract = this.contractBiz.readContract(contractFlow);
        ErpCrmContractUser contractUser = new ErpCrmContractUser();
        contractUser.setContractFlow(contractFlow);
        contractUser.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<ErpCrmContractUser> userList = this.contractUserBiz.searchContractUserList(contractUser);
        Map<String, Object> customerUserMap = new HashMap<String, Object>();
        List<String> contractUserFlowList = new ArrayList<String>();
        if (userList != null && !userList.isEmpty()) {
            for (ErpCrmContractUser user : userList) {
                contractUserFlowList.add(user.getUserFlow());
            }
        }
        List<ErpCrmCustomerUser> customerUsers = this.customerUserBiz.searchCustomerUserList(contractUserFlowList);
        if (customerUsers != null && !customerUsers.isEmpty()) {
            for (ErpCrmCustomerUser user : customerUsers) {
                customerUserMap.put(user.getUserFlow(), user);
            }
        }
        model.addAttribute("customerFlow", contract.getCustomerFlow());
        model.addAttribute("customerUserMap", customerUserMap);
        model.addAttribute("userList", userList);
        return "erp/crm/contract/editLinkMan";
    }

    /**
     * 跳转合同单个联系人编辑页面
     *
     * @param userFlow
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/readLinkMan")
    public String readLinkMan(String recordFlow, String contractFlow, Model model) {
        ErpCrmContractUser contractUser = this.contractUserBiz.readContractUser(recordFlow);
        if (contractUser != null) {
            ErpCrmCustomerUser customerUser = this.customerUserBiz.readCustomerUser(contractUser.getUserFlow());
            model.addAttribute("customerUser", customerUser);
            String userCategoryId = contractUser.getUserCategoryId();
            List<String> userCategoryList = new ArrayList<String>();
            if (StringUtil.isNotBlank(userCategoryId)) {
                String[] categoryArray = userCategoryId.split(",");
                for (String categoryId : categoryArray) {
                    userCategoryList.add(categoryId);
                }
            }
            model.addAttribute("userCategoryList", userCategoryList);
        }
        model.addAttribute("contractUser", contractUser);
        model.addAttribute("contractFlow", contractFlow);
        return "erp/crm/contract/userDetail";
    }

    /**
     * 保存合同单个联系人信息
     *
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/saveLinkMan")
    @ResponseBody
    public String saveLinkMan(ErpCrmContractUser contractUser, ErpCrmCustomerUser customerUser, Model model) {
        if (StringUtil.isNotBlank(contractUser.getSexId())) {
            contractUser.setSexName(UserSexEnum.getNameById(contractUser.getSexId()));
        }
        if (StringUtil.isNotBlank(customerUser.getSexId())) {
            customerUser.setSexName(UserSexEnum.getNameById(contractUser.getSexId()));
        }
        int result = this.contractUserBiz.saveOneContractUser(contractUser, customerUser);
        if (result == GlobalConstant.ONE_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 编辑合同回款计划
     *
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editPayPlan")
    public String editPayPlan(String contractFlow, Model model) {
        ErpCrmContractPayPlan payPlan = new ErpCrmContractPayPlan();
        payPlan.setContractFlow(contractFlow);
        List<ErpCrmContractPayPlan> payPlanList = this.payPlanBiz.searchContractPayPlanList(payPlan);
        model.addAttribute("payPlanList", payPlanList);
        return "erp/crm/contract/editPayPlan";
    }

    /**
     * 编辑合同开票计划
     *
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editBillPlan")
    public String editBillPlan(String contractFlow, Model model) {
        ErpCrmContractBillPlan payPlan = new ErpCrmContractBillPlan();
        payPlan.setContractFlow(contractFlow);
        List<ErpCrmContractBillPlan> payPlanList = this.billPlanBiz.searchContractBillPlanList(payPlan);
        model.addAttribute("payPlanList", payPlanList);
        return "erp/crm/contract/editBillPlan";
    }
    /**
     * 编辑合同权限开通情况
     *
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/editPowerList")
    public String editPowerList(String contractFlow, Model model) {

        ErpCrmContractPower power=new ErpCrmContractPower();
        power.setContractFlow(contractFlow);
        List<ErpCrmContractPower> powerList=this.powerBiz.searchContractPowerList(power);
        Map<String,PubFile> fileMap=new HashMap<>();
        if(powerList!=null&&!powerList.isEmpty())
        {
            for(ErpCrmContractPower p:powerList)
            {
                fileMap.put(p.getPowerFlow(),fileBiz.readFile(p.getFileFlow()));
            }
        }
        model.addAttribute("powerList", powerList);
        model.addAttribute("fileMap", fileMap);
        return "erp/crm/contract/editPowerList";
    }

    /**
     * 改变合同联系人状态
     *
     * @param userFlow
     * @param flag
     * @return
     */
    @RequestMapping(value = "/changeContractUserStatus")
    @ResponseBody
    public String changeContractUserStatus(String recordFlow, String flag) {
        ErpCrmContractUser user = this.contractUserBiz.readContractUser(recordFlow);
        if (user != null) {
            user.setRecordStatus(flag);
            int result = this.contractUserBiz.saveContractUser(user);
            if (result == GlobalConstant.ONE_LINE) {
                return GlobalConstant.OPERATE_SUCCESSED;
            }
        }
        return GlobalConstant.OPRE_FAIL;
    }

    @RequestMapping(value = "/deleteContract")
    @ResponseBody
    public String deleteContractUser(String contractFlow) {
        int result = this.contractBiz.deleteContract(contractFlow);
        if (result == GlobalConstant.ONE_LINE) {
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    //合同新增：关联主合同
    @RequestMapping(value = "/searchMainContact")
    @ResponseBody
    public Map<String, Object> searchMainContact(String customerFlow, String contractFlow) {
        Map<String, Object> map = new HashMap<String, Object>();
        ErpCrmCustomer customer = this.customerBiz.readCustomer(customerFlow);
        List<ErpCrmContract> contractList = this.contractBiz.searchMainContact(customerFlow, contractFlow);
        map.put("customer", customer);
        map.put("contractList", contractList);
        return map;
    }

    /**
     * 关联主合同-查询主合同及其产品列表
     *
     * @param contractFlow
     * @return
     */
    @RequestMapping(value = "/searchContactProduct")
    @ResponseBody
    public Map<String, Object> searchContactProduct(String contractFlow) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ErpCrmContractProduct product = new ErpCrmContractProduct();
        product.setContractFlow(contractFlow);
        List<ErpCrmContractProduct> productList = this.productBiz.searchContactProductList(product);
        ErpCrmContract contract = this.contractBiz.readContract(contractFlow);
        resultMap.put("contract", contract);
        resultMap.put("productList", productList);
        return resultMap;
    }


    /**
     * 查询客户的所有联系人信息
     *
     * @param customerFlow
     * @return
     */
    @RequestMapping(value = "/searchCustomerUserJson")
    @ResponseBody
    public List<ErpCrmCustomerUser> searchCustomerUserJson(String customerFlow) {
        List<ErpCrmCustomerUser> customerUserList = null;
        if (StringUtil.isNotBlank(customerFlow)) {
            ErpCrmCustomerUser user = new ErpCrmCustomerUser();
            user.setCustomerFlow(customerFlow);
            user.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            customerUserList = customerUserBiz.searchCustomerUserList(user);
        }
        return customerUserList;
    }


    /**
     * 查询合同产品
     *
     * @param contractFlow
     * @return
     */
    @RequestMapping(value = "/searchContractProductJson")
    @ResponseBody
    public List<ErpCrmContractProduct> searchContractProductJson(String contractFlow) {
        ErpCrmContractProduct product = new ErpCrmContractProduct();
        product.setContractFlow(contractFlow);
        return this.productBiz.searchContactProductList(product);
    }

    @RequestMapping(value = "/delContractUsers")
    @ResponseBody
    public String delContractUsers(@RequestBody String[] recordFlows) {
        if (recordFlows != null && recordFlows.length > 0) {
            return this.contractUserBiz.updateContractUsers(recordFlows);
        }
        return GlobalConstant.DELETE_FAIL;
    }

    @RequestMapping(value = "/markUser")
    public String markUser(String userFlow, String customerFlow, Model model) {
        ErpCrmCustomerUser customerUser = customerUserBiz.readCustomerUser(userFlow);
        model.addAttribute("customerUser", customerUser);

        ErpCrmContractUser user = new ErpCrmContractUser();
        user.setUserFlow(userFlow);
        List<ErpCrmContractUser> contractUserList = contractUserBiz.searchContractUserList(user);
        Map<String, Object> contractUserMap = new HashMap<String, Object>();
        if (contractUserList != null && !contractUserList.isEmpty()) {
            for (ErpCrmContractUser contractUser : contractUserList) {
                contractUserMap.put(contractUser.getContractFlow(), contractUser);
            }
        }
        model.addAttribute("contractUserMap", contractUserMap);

        ErpCrmContract temp = new ErpCrmContract();
        temp.setCustomerFlow(customerFlow);
        List<ErpCrmContract> contractList = contractBiz.searchErpContractList(temp,null);
        model.addAttribute("contractList", contractList);
        Map<String, ErpCrmContract> contractMap = new HashMap<String, ErpCrmContract>();
        Map<String, List<ErpCrmContractProduct>> productMap = new HashMap<String, List<ErpCrmContractProduct>>();
        ErpCrmContractProduct product = new ErpCrmContractProduct();
        if (contractList != null && contractList.size() > 0) {
            for (ErpCrmContract contract : contractList) {
                String contractFlow = contract.getContractFlow();
                contractMap.put(contractFlow, contract);
                //查询产品信息
                product.setContractFlow(contractFlow);
                List<ErpCrmContractProduct> productList = this.productBiz.searchContactProductList(product);
                productMap.put(contractFlow, productList);
            }
        }
        model.addAttribute("contractMap", contractMap);
        model.addAttribute("productMap", productMap);
        //人员类别和产品的关联
        ErpCrmContractUserRef tem = new ErpCrmContractUserRef();
        tem.setUserFlow(userFlow);
        List<ErpCrmContractUserRef> userRefList = userRefBiz.searchContractUserRefList(tem);
        Map<String, String> userRefMap = new HashMap<String, String>();
        if (userRefList != null && userRefList.size() > 0) {
            for (ErpCrmContractUserRef ref : userRefList) {
                userRefMap.put(ref.getUserRecordFlow() + "_" + ref.getUserCategoryId() + "_" + ref.getProductFlow(), ref.getRecordFlow());
            }
        }
        model.addAttribute("userRefMap", userRefMap);
        return "erp/crm/customer/markUser";
    }

    @RequestMapping(value = "/updateUser")
    @ResponseBody
    public String updateUser(String userCategory, String operFlag, String isSingle,
                             ErpCrmContractUserRef userRef, Model model) {
        String userFlow = userRef.getUserFlow();
        String contractFlow = userRef.getContractFlow();
        ErpCrmContractUser user = null;
        ErpCrmContractUser userTemp = new ErpCrmContractUser();
        userTemp.setUserFlow(userFlow);
        userTemp.setContractFlow(contractFlow);
        List<ErpCrmContractUser> contractUserList = this.contractUserBiz.searchContractUserList(userTemp);
        if (contractUserList != null && !contractUserList.isEmpty()) {
            user = contractUserList.get(0);
        }
        Boolean newUser = false;
        if (user == null) {
            newUser = true;
            user = new ErpCrmContractUser();
            user.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(user, true);
            user.setUserFlow(userFlow);
            user.setContractFlow(contractFlow);
        }
        if (user != null) {
            ErpCrmContractUserRef contractUserRef = null;
            List<ErpCrmContractUserRef> userRefList = null;
            if (GlobalConstant.FLAG_Y.equals(operFlag)) {//增加人员类别
                String categoryId = StringUtil.defaultString(user.getUserCategoryId());
                String categoryName = StringUtil.defaultString(user.getUserCategoryName());
                if (StringUtil.isNotBlank(categoryId)) {
                    categoryId += ",";
                    categoryName += ",";
                }
                user.setUserCategoryId(categoryId + userCategory);
                user.setUserCategoryName(categoryName + UserCategoryEnum.getNameById(userCategory));
                //使用人：若只有一个产品/项目，则默认选中该产品/项目
                if (UserCategoryEnum.User.getId().equals(userCategory) && GlobalConstant.FLAG_Y.equals(isSingle)) {
                    contractUserRef = new ErpCrmContractUserRef();
                    contractUserRef.setUserRecordFlow(user.getRecordFlow());
                    contractUserRef.setUserFlow(StringUtil.defaultString(user.getUserFlow()));
                    contractUserRef.setUserCategoryId(userCategory);
                    contractUserRef.setUserCategoryName(UserCategoryEnum.getNameById(userCategory));
                    contractUserRef.setContractFlow(user.getContractFlow());
                    ErpCrmContractProduct productTemp = new ErpCrmContractProduct();
                    productTemp.setContractFlow(user.getContractFlow());
                    List<ErpCrmContractProduct> productList = productBiz.searchContactProductList(productTemp);
                    if (productList != null && productList.size() > 0) {
                        ErpCrmContractProduct product = productList.get(0);
                        contractUserRef.setProductFlow(product.getProductFlow());
                        contractUserRef.setProductTypeId(StringUtil.defaultString(product.getProductTypeId()));
                        contractUserRef.setProductTypeName(StringUtil.defaultString(product.getProductTypeName()));
                    }
                }
            } else {//删除人员类别
                String userCategoryId = StringUtil.defaultString(user.getUserCategoryId());
                String userCategoryName = StringUtil.defaultString(user.getUserCategoryName());
                String userCateName = UserCategoryEnum.getNameById(userCategory);
                if (StringUtil.contains(userCategoryId, userCategory + ",")) {
                    userCategoryId = userCategoryId.replace(userCategory + ",", "");
                    userCategoryName = userCategoryName.replace(userCateName + ",", "");
                } else if (StringUtil.contains(userCategoryId, userCategory)) {
                    userCategoryId = userCategoryId.replace(userCategory, "");
                    userCategoryName = userCategoryName.replace(userCateName, "");
                    if (StringUtil.isNotBlank(userCategoryId)) {
                        userCategoryId = userCategoryId.substring(0, userCategoryId.length() - 1);
                        userCategoryName = userCategoryName.substring(0, userCategoryName.length() - 1);
                    }
                }
                user.setUserCategoryId(userCategoryId);
                user.setUserCategoryName(userCategoryName);
                //使用人：删除关联的产品/项目
                if (UserCategoryEnum.User.getId().equals(userCategory)) {
                    ErpCrmContractUserRef temp = new ErpCrmContractUserRef();
                    temp.setUserRecordFlow(user.getRecordFlow());
                    temp.setUserCategoryId(UserCategoryEnum.User.getId());
                    userRefList = userRefBiz.searchContractUserRefList(temp);
                }
            }
            this.contractUserBiz.updateUser(user, newUser, contractUserRef, userRefList);
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = "/updateUserRef")
    @ResponseBody
    public String updateUserRef(ErpCrmContractUserRef userRef, String operFlag, Model model) {
        ErpCrmContractUser user = new ErpCrmContractUser();
        user.setUserFlow(userRef.getUserFlow());
        user.setContractFlow(userRef.getContractFlow());
        List<ErpCrmContractUser> contractUserList = this.contractUserBiz.searchContractUserList(user);
        if (contractUserList != null && !contractUserList.isEmpty()) {
            user = contractUserList.get(0);
        }
        if (user != null) {
            ErpCrmContractProduct product = productBiz.readContractProduct(userRef.getProductFlow());
            if (GlobalConstant.FLAG_Y.equals(operFlag)) {//增加关联产品/项目
                userRef.setUserRecordFlow(user.getRecordFlow());
                userRef.setUserFlow(StringUtil.defaultString(user.getUserFlow()));
                userRef.setUserCategoryName(UserCategoryEnum.getNameById(userRef.getUserCategoryId()));
                userRef.setContractFlow(user.getContractFlow());
                if (product != null) {
                    userRef.setProductTypeId(StringUtil.defaultString(product.getProductTypeId()));
                    userRef.setProductTypeName(StringUtil.defaultString(product.getProductTypeName()));
                }
            } else {//删除关联产品/项目
                if (StringUtil.isBlank(userRef.getRecordFlow())) {
                    ErpCrmContractUserRef ref = new ErpCrmContractUserRef();
                    ref.setUserFlow(user.getUserFlow());
                    ref.setContractFlow(user.getContractFlow());
                    ref.setProductFlow(product.getProductFlow());
                    ref.setUserCategoryId(UserCategoryEnum.User.getId());
                    List<ErpCrmContractUserRef> refList = this.userRefBiz.searchContractUserRefList(ref);
                    if (refList != null && refList.isEmpty()) {
                        userRef = refList.get(0);
                    }
                }
                userRef.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
            }
            this.userRefBiz.saveContractUserRef(userRef);
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }

    @RequestMapping(value = "/listRegion", method = RequestMethod.GET)
    public String listRegion(SysUser user, Model model, HttpServletRequest request) {
        SysUser sysUser = userBiz.readSysUser(user.getUserFlow());
        model.addAttribute("sysUser", sysUser);
        List<ErpUserRegionPopedom> erpUserRegionPopedomList = popedomBiz.getErpUserRegionByUserFlow(user.getUserFlow());
        model.addAttribute("erpUserRegionPopedomList", erpUserRegionPopedomList);
        return "sys/user/listRegion";
    }

    @RequestMapping(value = "/delRegion", method = RequestMethod.GET)
    @ResponseBody
    public String delRegion(String recordFlow, Model model, HttpServletRequest request) {
        popedomBiz.delErpUserRegion(recordFlow);
        return GlobalConstant.OPERATE_SUCCESSED;
    }

    @RequestMapping(value = "/allotRegion", method = RequestMethod.GET)
    public String allotRegion(SysUser user, Model model, HttpServletRequest request) {
        SysUser sysUser = userBiz.readSysUser(user.getUserFlow());
        model.addAttribute("sysUser", sysUser);
        List<ErpUserRegionPopedom> erpUserRegionPopedomList = popedomBiz.getErpUserRegionByUserFlow(user.getUserFlow());
        model.addAttribute("erpUserRegionPopedomList", erpUserRegionPopedomList);
        return "sys/user/allotRegion";
    }

    @RequestMapping(value = "/saveRegion", method = RequestMethod.POST)
    public
    @ResponseBody
    String saveRegion(@RequestParam(value = "userFlow", required = true) String userFlow,
                      @RequestParam(value = "provId", required = false) String[] provIds,
                      @RequestParam(value = "provName", required = false) String[] provNames,
                      Model model, HttpServletRequest request) {
        popedomBiz.saveRegion(userFlow, provIds, provNames);
        return GlobalConstant.SAVE_SUCCESSED;
    }
}