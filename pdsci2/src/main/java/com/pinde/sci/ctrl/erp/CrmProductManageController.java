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
@RequestMapping("/erp/crm/productManage")
public class CrmProductManageController extends GeneralController {

    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IDeptBiz deptBiz;

    @Autowired
    private IErpProductBiz productBiz;
    @Autowired
    private IErpContractBiz contractBiz;
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/main")
    public String main( String type, Model model) {
        model.addAttribute("type",type);
        return "erp/crm/productManage/main";
    }
    /**
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(Integer currentPage,  String type,String productName,String customerName,
                       String etcTime,  String completeTime, String statusId, String approvalUserName,
                          HttpServletRequest request, Model model,  String approvalTime, String userName) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        SysUser user=GlobalContext.getCurrentUser();
        paramMap.put("productName", productName);
        paramMap.put("customerName", customerName);
        paramMap.put("approvalTime", approvalTime);
        paramMap.put("approvalUserName", approvalUserName);
        paramMap.put("userName", userName);
        paramMap.put("etcTime", etcTime);
        paramMap.put("completeTime", completeTime);
        paramMap.put("statusId", statusId);
        paramMap.put("user", user);
        if("add".equals(type))
        {
            return ownerProducts(request,paramMap,currentPage,model);
        }
        if("process".equals(type))
        {
            return process(request,paramMap,currentPage,model);
        }
        if("query".equals(type))
        {
            return query(request,paramMap,currentPage,model);
        }
        return "erp/crm/productManage/list";
    }

    private String ownerProducts(HttpServletRequest request, Map<String, Object> paramMap, Integer currentPage, Model model) {
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ErpProductManage> list=productBiz.ownerProducts(paramMap);
        Map<String,List<ErpProductManageUser>> userMap=new HashMap<>();
        if(list!=null&&list.size()>0){
            for(ErpProductManage m:list)
            {   List<ErpProductManageUser> users=productBiz.getProductManageUsers(m.getManageFlow());
                userMap.put(m.getManageFlow(),users);
            }
        }
        model.addAttribute("userMap",userMap);
        model.addAttribute("list",list);
        return "erp/crm/productManage/ownerProducts";
    }
    private String process(HttpServletRequest request, Map<String, Object> paramMap, Integer currentPage, Model model) {
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ErpProductManage> list=productBiz.process(paramMap);
        model.addAttribute("list",list);
        return "erp/crm/productManage/process";
    }
    private String query(HttpServletRequest request, Map<String, Object> paramMap, Integer currentPage, Model model) {
        PageHelper.startPage(currentPage, getPageSize(request));
        List<ErpProductManage> list=productBiz.query(paramMap);
        Map<String,List<ErpProductManageUser>> userMap=new HashMap<>();
        if(list!=null&&list.size()>0){
            for(ErpProductManage m:list)
            {   List<ErpProductManageUser> users=productBiz.getProductManageUsers(m.getManageFlow());
                userMap.put(m.getManageFlow(),users);
            }
        }
        model.addAttribute("userMap",userMap);
        model.addAttribute("list",list);
        return "erp/crm/productManage/query";
    }
    @RequestMapping(value = "/add")
    public String add(String manageFlow, Model model) {
        ErpProductManage manage=productBiz.readByFlow(manageFlow);
        model.addAttribute("manage",manage);

        List<ErpProductManageUser> manageUsers=productBiz.getProductManageUsers(manageFlow);
        if(manageUsers!=null&&manageUsers.size()>0){
            List<String> userFlows=new ArrayList<>();
            for(ErpProductManageUser u:manageUsers){
                userFlows.add(u.getUserFlow());
            }
            model.addAttribute("userFlows",userFlows);
        }
        model.addAttribute("manageUsers",manageUsers);

        SysUser sysUser = new SysUser();
        sysUser.setStatusId(UserStatusEnum.Activated.getId());
        sysUser.setOrgFlow(GlobalConstant.PD_ORG_FLOW);
        List<SysUser> userList = this.userBiz.searchUser(sysUser);
        model.addAttribute("userList",userList);

        return "erp/crm/productManage/add";
    }
    @RequestMapping(value = "/checkStatus")
    @ResponseBody
    public String checkStatus(String manageFlow, Model model) {
        ErpProductManage manage=productBiz.readByFlow(manageFlow);
        if(manage==null)
        {
            return "项目信息不存在，请刷新后重试！";
        }
        if(!"NotStart".equals(manage.getStatusId()))
        {
            return "项目已启动或已结束，请刷新后重试！";
        }
        return "1";
    }
    @RequestMapping(value = "/checkStatus2")
    @ResponseBody
    public String checkStatus2(String manageFlow, Model model) {
        ErpProductManage manage=productBiz.readByFlow(manageFlow);
        if(manage==null)
        {
            return "项目信息不存在，请刷新后重试！";
        }
        if(!"Processing".equals(manage.getStatusId()))
        {
            return "项目未启动或已结束，请刷新后重试！";
        }
        return "1";
    }
    @RequestMapping(value = "/manageStart")
    @ResponseBody
    public  String manageStart(String manageFlow, Model model) {
        ErpProductManage manage=productBiz.readByFlow(manageFlow);
        if(manage==null)
        {
            return "项目信息不存在，无法启动！";
        }
        if(!"NotStart".equals(manage.getStatusId()))
        {
            return "项目已启动或已结束，无法启动！";
        }
        manage.setStatusId("Processing");
        manage.setStatusName("进行中");
        int result=productBiz.updateManage(manage, "1", true, null);
        if(result==0)
        {
            return "项目启动失败！";
        }
        return "1";
    }
    @RequestMapping(value = "/delManage")
    @ResponseBody
    public  String delManage(String manageFlow, Model model) {
        ErpProductManage manage=productBiz.readByFlow(manageFlow);
        if(manage==null)
        {
            return "项目信息不存在，无法删除！";
        }
        if(!"NotStart".equals(manage.getStatusId()))
        {
            return "项目已启动或已结束，无法删除！";
        }
        manage.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        int result=productBiz.updateManage(manage, null, false, null);
        if(result==0)
        {
            return "项目删除失败！";
        }
        return "1";
    }
    @RequestMapping(value = "/saveManage")
    @ResponseBody
    public String saveManage(ErpProductManage manage,String[] userFlow, Model model) {
        SysUser user2= GlobalContext.getCurrentUser();
        manage.setApprovalUserFlow(user2.getUserFlow());
        manage.setApprovalUserName(user2.getUserName());
        manage.setStatusId("NotStart");
        manage.setStatusName("未开始");
        int result=productBiz.saveManage(manage, Arrays.asList(userFlow));
        if(result==0)
        {
            return "保存失败！";
        }
        return "1";
    }
    @RequestMapping(value = "/saveManageProcess")
    @ResponseBody
    public String saveManageProcess(ErpProductManageProcess manageProcess, Model model) {
        ErpProductManage manage=productBiz.readByFlow(manageProcess.getManageFlow());
        if(manage==null)
        {
            return "项目信息不存在，无法添加跟进内容！";
        }
        if(!"Processing".equals(manage.getStatusId()))
        {
            return "项目未启动或已结束，无法添加跟进内容！";
        }
        int result=productBiz.saveManageProcess(manageProcess);
        if(result==0)
        {
            return "保存失败！";
        }
        return "1";
    }
    @RequestMapping(value = "/productProcessList")
    public String productProcessList(String manageFlow, Model model) {
        List<ErpProductManageProcess> processList=productBiz.getProductProcessList(manageFlow);
        model.addAttribute("processList",processList);
        return "erp/crm/productManage/productProcessList";
    }
    @RequestMapping(value = "/completeProduct")
    public String completeProduct(String manageFlow,String type, Model model) {
        ErpProductManage manage=productBiz.readByFlow(manageFlow);
        SysUser user2= GlobalContext.getCurrentUser();
        model.addAttribute("user",user2);
        model.addAttribute("manage",manage);
        model.addAttribute("type",type);
        model.addAttribute("thisTime",DateUtil.getCurrDateTime(DateUtil.defDtPtn02));
        return "erp/crm/productManage/completeProduct";
    }
}