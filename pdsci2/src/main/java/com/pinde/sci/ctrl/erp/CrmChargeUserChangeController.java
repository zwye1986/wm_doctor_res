package com.pinde.sci.ctrl.erp;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
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
import com.pinde.sci.model.erp.*;
import com.pinde.sci.model.mo.*;
import org.apache.poi.hssf.usermodel.*;
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
@RequestMapping("/erp/crm/chargeUserChange")
public class CrmChargeUserChangeController extends GeneralController {

    @Autowired
    private IErpCustomerBiz customerBiz;
    @Autowired
    private IDeptBiz deptBiz;

    @Autowired
    private IErpCrmChargeUserChangeBiz changeBiz;
    @Autowired
    private IErpContractBiz contractBiz;

    /**
     * 负责人变更申请或审核首页
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/main/{role}")
    public String main(@PathVariable String role, Model model) {
        List<SysDept> deptList = searchDeptList();
        model.addAttribute("deptList", deptList);
        model.addAttribute("role",role);
        return "erp/crm/chargeUserChange/main";
    }

    public List<SysDept> searchDeptList() {
        SysDept dept = new SysDept();
        dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
        List<SysDept> deptList = this.deptBiz.searchDept(dept);
        return deptList;
    }

    /**
     * 负责人变更申请或审核列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/changeList")
    public String changeList(String role, Integer currentPage,
                             HttpServletRequest request, Model model,ErpCrmChargeUserChange change) {
        SysUser currUser = GlobalContext.getCurrentUser();
        if(!"global".equals(role)){
            change.setApplyUserFlow(currUser.getUserFlow());
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ErpCrmChargeUserChange> changes=changeBiz.searchByChange(change);
        model.addAttribute("changes",changes);
        return "erp/crm/chargeUserChange/list";
    }
    @RequestMapping(value = "/add")
    public String changeList(String changeFlow, Model model) {
        SysUser currUser = GlobalContext.getCurrentUser();
        ErpCrmChargeUserChange change=changeBiz.readByFlow(changeFlow);
        model.addAttribute("change",change);
        return "erp/crm/chargeUserChange/add";
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public Object save(Model model,ErpCrmChargeUserChange change) {
        Map<String ,String> result=new HashMap<>();
        ErpCrmContract contract=new ErpCrmContract();
        contract.setChargeUserFlow(change.getOldChargeUserFlow());
        contract.setSignDeptFlow(change.getOldSignDeptFlow());
        List<ErpCrmContract> crmContracts=contractBiz.searchErpContracts(contract,null);
        contract=new ErpCrmContract();
        contract.setChargeUser2Flow(change.getOldChargeUser2Flow());
        contract.setSignDeptFlow(change.getOldSignDeptFlow());
        List<ErpCrmContract> crmContract2s=contractBiz.searchErpContracts(contract,null);
        if((crmContracts==null||crmContracts.isEmpty())&&(crmContract2s==null||crmContract2s.isEmpty()))
        {
            result.put("code","0");
            result.put("msg","原合同负责人没有负责的合同，无法保存申请！");
            return result;
        }
        int checkNum=changeBiz.checkHave(change);
        if(checkNum>0)
        {
            result.put("code","0");
            result.put("msg","此申请信息已存在，无法再次申请！");
            return result;
        }
        SysUser currUser = GlobalContext.getCurrentUser();
        change.setStatusId(UserChangeStatusEnum.Applying.getId());
        change.setStatusName(UserChangeStatusEnum.Applying.getName());
        change.setApplyUserFlow(currUser.getUserFlow());
        change.setApplyUserName(currUser.getUserName());
        change.setApplyTime(DateUtil.getCurrDateTime(DateUtil.defDtPtn02));
        int count=changeBiz.save(change);
        if(count==0)
        {
            result.put("code","0");
        }else {
            result.put("code", "1");
        }
        return result;
    }
    @RequestMapping(value = "/audit")
    @ResponseBody
    public Object audit(Model model,String changeFlow,String statusId) {
        Map<String ,String> result=new HashMap<>();
        ErpCrmChargeUserChange change=changeBiz.readByFlow(changeFlow);
        if(change==null)
        {
            result.put("code","0");
            result.put("msg","申请变更信息不存在，无法审核，请刷新页面！");
            return result;
        }
        if(!UserChangeStatusEnum.Applying.getId().equals(change.getStatusId()))
        {
            result.put("code","0");
            result.put("msg","申请变更已被审核，请勿重复审核！");
            return result;
        }
        if(UserChangeStatusEnum.ApplyBack.getId().equals(statusId))
        {
            change.setStatusId(UserChangeStatusEnum.ApplyBack.getId());
            change.setStatusName(UserChangeStatusEnum.ApplyBack.getName());
        }else{
            change.setStatusId(UserChangeStatusEnum.ApplyPass.getId());
            change.setStatusName(UserChangeStatusEnum.ApplyPass.getName());
        }
        SysUser currUser = GlobalContext.getCurrentUser();
        change.setAuditUserFlow(currUser.getUserFlow());
        change.setAuditUserName(currUser.getUserName());
        change.setAuditTime(DateUtil.getCurrDateTime(DateUtil.defDtPtn02));
        int count=changeBiz.audit(change);
        if(count==0)
        {
            result.put("code","0");
        }else {
            result.put("code", "1");
        }
        return result;
    }
}