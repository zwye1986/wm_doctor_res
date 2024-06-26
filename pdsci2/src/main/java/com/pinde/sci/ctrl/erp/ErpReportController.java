package com.pinde.sci.ctrl.erp;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractBiz;
import com.pinde.sci.biz.erp.IErpCustomerBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.core.util.DateUtil;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.erp.InputReportForm;
import com.pinde.sci.model.erp.ErpCrmContractExt;
import com.pinde.sci.model.erp.ErpCrmContractLicExt;
import com.pinde.sci.model.mo.ErpCrmContract;
import com.pinde.sci.model.mo.ErpCrmCustomerUser;
import com.pinde.sci.model.mo.ErpOaLicKey;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("erp/report")
public class ErpReportController extends GeneralController{ 
	@Autowired
	private IErpCustomerBiz customerBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IErpContractBiz contractBiz;
	
	//客户录入情况统计
	@RequestMapping(value="/crmInputReport")
	public String crmInputReport(String startDate,String endDate,String pickType,Model model){
		if (StringUtil.isBlank(startDate)) {
			startDate = DateUtil.getCurrDate2();
		}
		if (StringUtil.isBlank(endDate)) {
			endDate = DateUtil.getCurrDate2();
		}
		startDate = DateUtil.getDate(startDate) + "000000";
		endDate = DateUtil.getDate(endDate) + "235959";
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		List<InputReportForm> formList = customerBiz.searchCrmInput(paramMap);
		model.addAttribute("formList", formList);
		model.addAttribute("startDate", DateUtil.transDate(startDate));
		model.addAttribute("endDate", DateUtil.transDate(endDate));
		model.addAttribute("pickType", StringUtil.defaultString(pickType));
		
		return "erp/report/crmInput";
	}
	
	@RequestMapping(value="/crmInputReportPick")
	public String crmInputReportPick(String pickType,Model model){
		String startDate = DateUtil.getCurrDate();
		String endDate = DateUtil.getCurrDate();
		if (StringUtil.isNotBlank(pickType)) {
			if ("week".equals(pickType)) {
				startDate = DateUtil.addDate(startDate, -6);
			} else if ("month".equals(pickType)) {
				startDate = DateUtil.newDateOfAddMonths(startDate, -1);
			}
		}
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("pickType", pickType);
		return "redirect:/erp/report/crmInputReport";
	}
	@RequestMapping(value="/crmInputDetail")
	public String crmInputDetail(String userFlow,String type,String startDate,String endDate,Model model){
		List<Map<String,Object>> list=new ArrayList<>();
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("userFlow", userFlow);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		if("Customer".equals(type)){//客户
			list=customerBiz.crmInputDetailCustomer(paramMap);
		}else if("CustomerUser".equals(type)){//联系人
			list=customerBiz.crmInputDetailCustomerUser(paramMap);
		}
		model.addAttribute("type",type);
		model.addAttribute("list",list);
		return "erp/report/crmInputDetail";
	}
	@RequestMapping(value="/customerUserBirday")
	public String customerUserBirday(Model model){
		List<ErpCrmCustomerUser> list=customerBiz.customerUserBirday();
		model.addAttribute("list",list);
		return "erp/report/customerUserBirdy";
	}

	@RequestMapping(value="/saveAddFeedBack")
	@ResponseBody
	public String saveAddFeedBack(Model model,ErpCrmContract contract){
		ErpCrmContract old=contractBiz.readContract(contract.getContractFlow());
		if(old!=null&&StringUtil.isNotBlank(old.getFeedBackTime()))
		{
			return "该合同已经跟进信息，无法再次添加！";
		}
		SysUser user = GlobalContext.getCurrentUser();
		contract.setFeedBackUserFlow(user.getUserFlow());
		contract.setFeedBackUserName(user.getUserName());
		int count=contractBiz.addFeedBack(contract);
		if(count!=GlobalConstant.ONE_LINE)
		{
			return GlobalConstant.SAVE_FAIL;
		}
		return GlobalConstant.SAVE_SUCCESSED;

	}

	@RequestMapping(value="/check")
	@ResponseBody
	public String check(Model model,String contractFlow){
		ErpCrmContract contract=contractBiz.readContract(contractFlow);
		if(contract!=null&&StringUtil.isBlank(contract.getFeedBackTime()))
		{
			return "2";
		}
		return "1";
	}
	@RequestMapping(value="/addFeedBack")
	public String addFeedBack(Model model,String contractFlow){

		return "/erp/report/addFeedBack";
	}

	//合同维护到期日提醒
	@RequestMapping(value="/contractDateRemind")
	public String contractDateRemind(String startDate,String endDate,String pickType,String role,Integer currentPage,HttpServletRequest request,Model model){
		if (StringUtil.isBlank(pickType)) {
			String currDate = DateUtil.getCurrDate();
			startDate = DateUtil.addDate(currDate, -7);
			endDate = DateUtil.addDate(currDate, 7);
		}
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("currDate", DateUtil.getCurrDate());
		paramMap.put("Implementing", "1");
		if("local".equals(role))
		{
			SysUser user = GlobalContext.getCurrentUser();
			paramMap.put("signDeptFlow", user.getDeptFlow());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ErpCrmContractExt> contractList = contractBiz.searchContracts(paramMap);
		model.addAttribute("contractList", contractList);
		model.addAttribute("startDate", DateUtil.transDate(startDate));
		model.addAttribute("endDate", DateUtil.transDate(endDate));
		model.addAttribute("pickType", StringUtil.defaultString(pickType));
		
		return "erp/report/contractDateRemind";
	}
	
	@RequestMapping(value="/contractDatePick")
	public String contractDatePick(String pickType,String role,Model model){
		String currDate = DateUtil.getCurrDate();
		String startDate = "";
		String endDate = "";
		if (StringUtil.isNotBlank(pickType)) {
			if ("week".equals(pickType)) {
				startDate = DateUtil.addDate(currDate, -7);
				endDate = DateUtil.addDate(currDate, 7);
			} else if ("month".equals(pickType)) {
				startDate = DateUtil.newDateOfAddMonths(currDate, -1);
				endDate = DateUtil.newDateOfAddMonths(currDate, 1);
			} else if ("season".equals(pickType)) {
				startDate = DateUtil.newDateOfAddMonths(currDate, -3);
				endDate = DateUtil.newDateOfAddMonths(currDate, 3);
			}else if ("halfYear".equals(pickType)) {
				startDate = DateUtil.newDateOfAddMonths(currDate, -6);
				endDate = DateUtil.newDateOfAddMonths(currDate, 6);
			}
		}
		model.addAttribute("role", role);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("pickType", pickType);
		return "redirect:/erp/report/contractDateRemind";
	}

	//注册文件到期日提醒
	@RequestMapping(value="/licDateRemind")
	public String licDateRemind(String startDate,String endDate,String pickType,String role,Integer currentPage,HttpServletRequest request,Model model){
		if (StringUtil.isBlank(pickType)) {
			String currDate = DateUtil.getCurrDate();
			startDate = DateUtil.addDate(currDate, -7);
			endDate = DateUtil.addDate(currDate, 7);
		}
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("currDate", DateUtil.getCurrDate());
		paramMap.put("Implementing", "1");
		if("local".equals(role))
		{
			SysUser user = GlobalContext.getCurrentUser();
			paramMap.put("signDeptFlow", user.getDeptFlow());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ErpCrmContractLicExt> contractList = contractBiz.searchContractLics(paramMap);

		Map<String,List<String>> workStationNameMap=new HashMap<String, List<String>>();
		if(contractList!=null && !contractList.isEmpty()){
			for(ErpCrmContractLicExt ext:contractList){
				ErpOaLicKey lic=ext.getLicKey();
				if(lic!=null) {
					if (StringUtil.isNotBlank(lic.getWorkStationName())) {
						workStationNameMap.put(lic.getLicFlow(), Arrays.asList(lic.getWorkStationName().split("，")));
					}
				}
			}
		}
		model.addAttribute("workStationNameMap", workStationNameMap);
		model.addAttribute("contractList", contractList);
		model.addAttribute("startDate", DateUtil.transDate(startDate));
		model.addAttribute("endDate", DateUtil.transDate(endDate));
		model.addAttribute("pickType", StringUtil.defaultString(pickType));

		return "erp/report/licDateRemind";
	}

	@RequestMapping(value="/licDatePick")
	public String licDatePick(String pickType,String role,Model model){
		String currDate = DateUtil.getCurrDate();
		String startDate = "";
		String endDate = "";
		if (StringUtil.isNotBlank(pickType)) {
			if ("week".equals(pickType)) {
				startDate = DateUtil.addDate(currDate, -7);
				endDate = DateUtil.addDate(currDate, 7);
			} else if ("month".equals(pickType)) {
				startDate = DateUtil.newDateOfAddMonths(currDate, -1);
				endDate = DateUtil.newDateOfAddMonths(currDate, 1);
			} else if ("season".equals(pickType)) {
				startDate = DateUtil.newDateOfAddMonths(currDate, -3);
				endDate = DateUtil.newDateOfAddMonths(currDate, 3);
			}else if ("halfYear".equals(pickType)) {
				startDate = DateUtil.newDateOfAddMonths(currDate, -6);
				endDate = DateUtil.newDateOfAddMonths(currDate, 6);
			}
		}
		model.addAttribute("role", role);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		model.addAttribute("pickType", pickType);
		return "redirect:/erp/report/licDateRemind";
	}
}

