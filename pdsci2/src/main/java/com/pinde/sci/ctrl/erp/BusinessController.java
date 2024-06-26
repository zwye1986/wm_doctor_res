package com.pinde.sci.ctrl.erp;

import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("erp/business")
public class BusinessController extends GeneralController{
	@Autowired
	private IDeptBiz deptBiz;
	
	//开票审核列表
	@RequestMapping(value="/auditBillList/{userListScope}")
	public String auditBillList(@PathVariable String userListScope,Model model){
		setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, userListScope);
		return "erp/business/audit/auditBillList";
	}
	//开票审核详情
	@RequestMapping(value="/auditBill")
	public String auditBill(Model model){
		return "erp/business/audit/auditBill";
	}
	//到账确认列表
	@RequestMapping(value="/auditPayList/{userListScope}")
	public String auditPayList(@PathVariable String userListScope,Model model){
		setSessionAttribute(GlobalConstant.USER_LIST_SCOPE, userListScope);
		return "erp/business/audit/auditPayList";
	}
	
	//到账确认详情
    @RequestMapping(value="/auditPay")
    public String auditPay(Model model){
		return "erp/business/audit/auditPay";
	}
		
	@RequestMapping(value="/auditFinishContactOrders")
	public String auditFinishContactOrders(Model model){
		return "erp/business/audit/auditFinishContactOrder";
	}
	
	@RequestMapping(value="/payPlanInfo")
	public String payPlanInfo(Model model){
		return "erp/business/audit/payPlanInfo";
	}
	
	@RequestMapping(value="/payPlanList")
	public String payPlanList(Model model){
		return "erp/contract/payPlanList";
	}
	//以上原型
	
	//以下功能
	@RequestMapping(value="/bill/list")
	public String billList(Model model){
		return "erp/business/bill/list";
	}
	
	@RequestMapping(value="/bill/edit")
	public String billEdit(Model model){
		//查询部门
		List<SysDept> deptList=searchDeptList();
		model.addAttribute("deptList", deptList);
		return "erp/business/bill/edit";
	}
	
	@RequestMapping(value="/saveBill")
	@ResponseBody
	public String saveContactOrder() throws Exception{
		return GlobalConstant.SAVE_SUCCESSED;
	}
	
	/**
	 * 查询当前登录者所在机构的部门
	 * @param model
	 * @return
	 */
	public List<SysDept> searchDeptList(){
		SysDept dept=new SysDept();
		dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		dept.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> deptList=this.deptBiz.searchDept(dept);
		return deptList;
	}
	
}

