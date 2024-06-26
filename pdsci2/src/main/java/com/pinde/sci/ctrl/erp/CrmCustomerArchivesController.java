package com.pinde.sci.ctrl.erp;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.erp.ContactOrderStatusEnum;
import com.pinde.sci.enums.erp.ContractStatusEnum;
import com.pinde.sci.form.erp.ContactOrderForm;
import com.pinde.sci.model.erp.ErpOrderCustomerExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/erp/crm/customerArchives")
public class CrmCustomerArchivesController extends GeneralController {
    @Autowired
    private IErpCustomerBiz customerBiz;
    @Autowired
    private IErpCustomerUserBiz customerUserBiz;
    @Autowired
    private IErpCustomerVisitBiz customerVisitBiz;
    @Autowired
    private IErpContractBiz contractBiz;
    @Autowired
    private IErpContractUserBiz contractUserBiz;
    @Autowired
    private IErpOAWorkOrderBiz workOrderBiz;
    @Autowired
    private IErpContactOrderBiz contactOrderBiz;
    @Autowired
    private IErpUserRegionPopedomBiz userRegionPopedomBiz;

    /**
     * 客户查询
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/main")
    public String main(HttpServletRequest request, Model model) {
        //当前用户地区权限区域
        SysUser currUser = GlobalContext.getCurrentUser();
        List<ErpUserRegionPopedom> areaList = userRegionPopedomBiz.getUserRegionArea(currUser.getUserFlow());
        System.out.println(areaList.size());
        model.addAttribute("areaList", areaList);
        return "erp/crm/customerArchives/main";
    }
    /**
     * 客户查询
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String searchCustomer(ErpCrmCustomer customer,
                                 String customerPhone,String bigRegionTypeId, Integer currentPage,
                                 String hosGradeFlag, HttpServletRequest request, Model model) {
        List<String> bigRegionTypeIdList = new ArrayList<String>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
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
        paramMap.put("customer", customer);
        paramMap.put("currUser", currUser);
        paramMap.put("customerPhone", customerPhone);
        paramMap.put("hosGradeFlag", StringUtil.defaultString(hosGradeFlag));
        List<ErpCrmCustomer> customerList = customerBiz.searchCustomerListByContract(paramMap);
        model.addAttribute("customerList", customerList);
        return "erp/crm/customerArchives/list";
    }

    /**
     * 查看客户信息
     */
    @RequestMapping(value = "/customerInfo")
    public String customerInfo(ErpCrmCustomerUser customerUser, Model model) {

        if (StringUtil.isNotBlank(customerUser.getCustomerFlow())) {
            ErpCrmCustomer customer = customerBiz.readCustomer(customerUser.getCustomerFlow());
            model.addAttribute("customer", customer);

            List<ErpCrmCustomerUser> customerUserList = customerUserBiz.searchCustomerUserList(customerUser);
            model.addAttribute("customerUserList", customerUserList);
            ErpCrmContract contract=new ErpCrmContract();
            contract.setCustomerFlow(customerUser.getCustomerFlow());
            List<String> statuIds=new ArrayList<>();
                statuIds.add(ContractStatusEnum.Auditing.getId());
                statuIds.add(ContractStatusEnum.AuditBack.getId());
            //合同信息
            Map<String, Object> paramMap = new HashMap<String, Object>();
            ErpCrmCustomer customer2=new ErpCrmCustomer();
            customer2.setCustomerFlow(customerUser.getCustomerFlow());
            paramMap.put("contract", contract);
            paramMap.put("customer", customer2);
            paramMap.put("notInStatus", statuIds);
            List<Map<String,Object>> contracts=contractBiz.yszcx(paramMap);
//            System.err.println("==========================="+JSON.toJSONString(contracts));
            model.addAttribute("contracts",contracts);

            //回访信息
            ErpCrmCustomerVisit visit = new ErpCrmCustomerVisit();
            visit.setVisitRefFlow(customerUser.getCustomerFlow());
            visit.setVisitType("1");
            List<ErpCrmCustomerVisit> visitList = customerVisitBiz.searchCustomerVisit(visit);
            model.addAttribute("visitList", visitList);

        }

        return "erp/crm/customerArchives/view";
    }

    /**
     * 合同联系单
     * @param contractFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/contractOrderInfo")
    public String contractOrderInfo(String contractFlow,String customerFlow, Model model) {

        Map<String,Object> paramMap=new HashMap<String, Object>();
        ErpOaContactOrder contactOrder=new ErpOaContactOrder();
        contactOrder.setContractFlow(contractFlow);
        List<String> contactStatusIdList = new ArrayList<String>();
        for(ContactOrderStatusEnum status:ContactOrderStatusEnum.values()){
            contactStatusIdList.add(status.getId());
        }
        paramMap.put("contactStatusIdList", contactStatusIdList);
        List<String> contactStatusNameNotList = new ArrayList<String>();
        contactStatusNameNotList.add(ContactOrderStatusEnum.Save.getName());
        paramMap.put("contactStatusNameNotList", contactStatusNameNotList);
        paramMap.put("customerFlow", customerFlow);
        paramMap.put("contactOrder", contactOrder);

        List<ErpOrderCustomerExt> contactOrderList=this.contactOrderBiz.searchContactOrderList(paramMap);
        model.addAttribute("contactOrderList", contactOrderList);

        return "erp/crm/customerArchives/orderList";
    }

    /**
     * 联系单详情页面
     * @param contactFlow
     * @param model
     * @return
     */
    @RequestMapping(value="/contactOrderInfo")
    public String contactOrderInfo(String contactFlow,Model model){

        model.addAttribute("contactFlow", contactFlow);
        return "erp/crm/customerArchives/detailInfo";
    }

    @RequestMapping(value="/contactInfo")
    public String contactInfo(String contactFlow,Model model) throws DocumentException {
        ErpOaContactOrder contactOrder=this.contactOrderBiz.readContactOrder(contactFlow);
        model.addAttribute("contactOrder", contactOrder);
        if(contactOrder!=null){
            Map<String,String> userCategoryMap=this.contractUserBiz.searchUserCategoryMap(contactOrder.getContractFlow());
            model.addAttribute("userCategoryMap", userCategoryMap);
        }
        //判断是否显示审核记录
        int auditCount = 0;
        if(contactOrder!=null){
            String content = contactOrder.getContactContent();
            if (StringUtil.isBlank(content)) {
                content = "<info/>";
            }
            Document dom = DocumentHelper.parseText(content);
            Element auditsElement=(Element) dom.selectSingleNode("/info/audits");
            if (auditsElement != null) {
                //销售意见/需求
                List<Element> salePassedElements = auditsElement.selectNodes("audit/auditResult[@name='"+ContactOrderStatusEnum.SalePassed.getName()+"']");
                List<Element> saleUnPassedElements = auditsElement.selectNodes("audit/auditResult[@name='"+ContactOrderStatusEnum.SaleUnPassed.getName()+"']");
                if (salePassedElements != null) {
                    auditCount = salePassedElements.size();
                }
                if (saleUnPassedElements != null) {
                    auditCount += saleUnPassedElements.size();
                }
            }
            ContactOrderForm form=this.contactOrderBiz.xmlToContactOrder(contactOrder.getContactContent());
            model.addAttribute("contactOrderForm", form);
        }
        model.addAttribute("auditCount", auditCount);
        return "erp/crm/customerArchives/orderInfo";
    }

    /**
     * 派工单派工列表
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/assignList")
    public String assignList(ErpOaWorkOrder workOrder, Model model) throws Exception{
        if(StringUtil.isNotBlank(workOrder.getContactFlow())){
            List<ErpOaWorkOrder> workOrderList = workOrderBiz.searchWorkOrderList(workOrder);
            model.addAttribute("workOrderList", workOrderList);
        }
        return "erp/crm/customerArchives/workOrderList";
    }

    @RequestMapping(value="/historyContact")
    public String historyContact(String contactFlow,Model model){
        ErpOaContactOrder contactOrder=this.contactOrderBiz.readContactOrder(contactFlow);
        if(contactOrder!=null){
            String customerFlow=contactOrder.getCustomerFlow();
            ErpOaContactOrder contact=new ErpOaContactOrder();
            contact.setCustomerFlow(customerFlow);
            contact.setContactStatusId(ContactOrderStatusEnum.Save.getId());
            List<ErpOaContactOrder> contactOrderList=this.contactOrderBiz.searchHistoryContactOrderList(contact,null);
            model.addAttribute("contactOrderList", contactOrderList);
        }
        return "erp/crm/customerArchives/historyContactList";
    }
}