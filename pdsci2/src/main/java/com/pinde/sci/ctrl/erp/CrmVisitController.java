package com.pinde.sci.ctrl.erp;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpCustomerBiz;
import com.pinde.sci.biz.erp.IErpCustomerVisitBiz;
import com.pinde.sci.biz.erp.IErpUserRegionPopedomBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralErpMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.ErpCrmCustomer;
import com.pinde.sci.model.mo.ErpCrmCustomerVisit;
import com.pinde.sci.model.mo.ErpUserRegionPopedom;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("/erp/crm/customerVisit")
public class CrmVisitController extends GeneralController {

    @Autowired
    private IErpCustomerBiz customerBiz;
    @Autowired
    private IErpCustomerVisitBiz customerVisitBiz;
    @Autowired
    private IErpUserRegionPopedomBiz userRegionPopedomBiz;

    /**
     * 客户回访查询
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
        return "erp/crm/customerVisit/main";
    }

    /**
     * 客户查询
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String searchCustomer(ErpCrmCustomer customer,
                                 String customerPhone, String bigRegionTypeId, Integer currentPage,
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
        List<ErpCrmCustomer> customerList = customerBiz.searchCustomerList(paramMap);
        model.addAttribute("customerList", customerList);
        return "erp/crm/customerVisit/list";
    }

    /**
     * 加载客户回访信息
     * @param customerFlow
     * @param model
     * @return
     */
    @RequestMapping(value = "/add")
    public String add(String customerFlow, Model model) {
        if (StringUtil.isNotBlank(customerFlow)) {
            ErpCrmCustomerVisit visit = new ErpCrmCustomerVisit();
            visit.setVisitRefFlow(customerFlow);
            visit.setVisitType("1");
            List<ErpCrmCustomerVisit> visitList = customerVisitBiz.searchCustomerVisit(visit);
            model.addAttribute("visitList", visitList);
        }
        return "erp/crm/customerVisit/add";
    }

    /**
     * 保存回访
     * @param customerVisit
     * @return
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    public String saveCustomerVisit(ErpCrmCustomerVisit customerVisit) {
        customerVisit.setVisitTime(DateUtil.getCurrDateTime());
        int result = customerVisitBiz.saveCustomerVisit(customerVisit);
        if (result != GlobalConstant.ZERO_LINE) {
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
}