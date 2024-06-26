package com.pinde.sci.ctrl.erp;

import com.alibaba.fastjson.JSON;
import com.pinde.core.config.WorkStation;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpContractBiz;
import com.pinde.sci.biz.erp.IErpContractProductBiz;
import com.pinde.sci.biz.erp.IErpCustomerBiz;
import com.pinde.sci.biz.erp.IErpOaLicKeyBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.erp.AccreditTypeEnum;
import com.pinde.sci.enums.erp.ContractStatusEnum;
import com.pinde.sci.enums.erp.DevelopLanguageEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.model.mo.*;
import org.bouncycastle.openpgp.PGPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("erp/lic")
public class LicKeyController extends GeneralController{
   
	@Autowired
	private IErpOaLicKeyBiz erpOaLicKeyBiz;
	@Autowired
	private IErpCustomerBiz customerBiz;
	@Autowired
	private IErpContractBiz contractBiz;
	@Autowired
	private IErpContractProductBiz contractProductBiz;
	@Autowired
	private IUserBiz sysUserBiz;
	@Autowired
	private IDeptBiz deptBiz;
	
	/**
	 * 授权文件编辑页面
	 * @param licFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/edit")
	public String edit(String licFlow,Model model){
		List<WorkStation> javaWorkStationList=(List<WorkStation>) GlobalContext.getRequest().getServletContext().getAttribute("allWorkStation");
		if(StringUtil.isNotBlank(licFlow)){
			ErpOaLicKey erpOaLicKey=this.erpOaLicKeyBiz.readLicKey(licFlow);
			model.addAttribute("lic", erpOaLicKey);
		}
		model.addAttribute("javaWorkStationList", javaWorkStationList);
		List<WorkStation> netWorkStationList=new ArrayList<WorkStation>();
		WorkStation netRes=new WorkStation();
		netRes.setWorkStationId("netres");
		netRes.setWorkStationName("net住院医师过程管理系统");
		WorkStation netExam=new WorkStation();
		netExam.setWorkStationId("netexam");
		netExam.setWorkStationName("net考试系统3.1");
		netWorkStationList.add(netExam);
		netWorkStationList.add(netRes);
		model.addAttribute("netWorkStationList", netWorkStationList);
		//查询部门
		List<SysDept> deptList=searchDeptList();
		model.addAttribute("deptList", deptList);
		return "erp/lic/edit";
	}
	
	/**
	 * 授权文件列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String list(ErpOaLicKey erpOaLicKey,Integer currentPage,HttpServletRequest request,Model model){
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ErpOaLicKey> licList=this.erpOaLicKeyBiz.searchLicList(erpOaLicKey);
		model.addAttribute("licList", licList);
		Map<String,List<String>> workStationNameMap=new HashMap<String, List<String>>();
		Map<String,List<String>> productTypeNameMap=new HashMap<String, List<String>>();
		if(licList!=null && !licList.isEmpty()){
			for(ErpOaLicKey lic:licList){
				if(StringUtil.isNotBlank(lic.getWorkStationName())){
					workStationNameMap.put(lic.getLicFlow(), Arrays.asList(lic.getWorkStationName().split("，"))); 
				}
				if(StringUtil.isNotBlank(lic.getProductTypeName())){
					productTypeNameMap.put(lic.getLicFlow(), Arrays.asList(lic.getProductTypeName().split("，"))); 
				}
			}
		}
		model.addAttribute("workStationNameMap", workStationNameMap);
		model.addAttribute("productTypeNameMap", productTypeNameMap);
		return "erp/lic/licList";
	}
	
	/**
	 * 保存lic信息并生成lic文件
	 * @param erpOaLicKey
	 * @return
	 * @throws PGPException 
	 * @throws IOException 
	 */
	@RequestMapping(value="/saveLicKey")
	@ResponseBody
	public String saveLicKey(ErpOaLicKey erpOaLicKey) throws Exception{
		repenishLic(erpOaLicKey);
		String result=this.erpOaLicKeyBiz.saveAndSendLicFile(erpOaLicKey);
		return result;
	}

	@RequestMapping(value="/time")
	@ResponseBody
	public String time(String type) {
		String time="";
		String date =DateUtil.getCurrDate();
		if(AccreditTypeEnum.Develop.getId().equals(type)){
			time=DateUtil.addDate(date, 30);
		}else if(AccreditTypeEnum.Probation.getId().equals(type)){
			time=DateUtil.addDate(date, 180);
		}else if(AccreditTypeEnum.Show.getId().equals(type)){
			time=DateUtil.addDate(date, 90);
		}
		return time;
	}

	@RequestMapping(value = "/addTime")
	@ResponseBody
	public String addTime(String type) {
		String time = "";
		String date = DateUtil.getCurrDate();
		if ("1".equals(type)) {
			time = DateUtil.addMonthForArrange(date, "1", false);
		} else if ("3".equals(type)) {
			time = DateUtil.addMonthForArrange(date, "3", false);
		} else if ("6".equals(type)) {
			time = DateUtil.addMonthForArrange(date, "6", false);
		} else if ("12".equals(type)) {
			time = DateUtil.addMonthForArrange(date, "12", false);
		} else if ("24".equals(type)) {
			time = DateUtil.addMonthForArrange(date, "24", false);
		} else if ("200".equals(type)) {
			time = DateUtil.addDate(date, 200);
		} else if ("500".equals(type)) {
			time = DateUtil.addDate(date, 500);
		} else {
			time = "0";
		}
		return time;
	}
	
	/**
	 * 发送Lic文件
	 * @param licFlow
	 * @return
	 */
	@RequestMapping(value="/sendLicKey")
	@ResponseBody
	public String sendLicKey(String licFlow){
		ErpOaLicKey erpOaLicKey=this.erpOaLicKeyBiz.readLicKey(licFlow);
		if(erpOaLicKey!=null){
			String applyUserFlow=erpOaLicKey.getApplyUserFlow();
			SysUser user=this.sysUserBiz.readSysUser(applyUserFlow);
			if(user!=null && StringUtil.isNotBlank(user.getUserEmail())){
				int result=this.erpOaLicKeyBiz.sendLicFile(erpOaLicKey,user.getUserEmail());
				if(result==GlobalConstant.ONE_LINE){
					return GlobalConstant.SEND_SUCCESSED;
				}
			}
		}
		return GlobalConstant.SEND_FAIL;
	}
	
	/**
	 * 重新生成Lic文件
	 * @param licFlow
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/reCreateLicFile")
	@ResponseBody
	public String reCreateLicFile(String licFlow) throws Exception{
		ErpOaLicKey erpOaLicKey=this.erpOaLicKeyBiz.readLicKey(licFlow);
		if(erpOaLicKey!=null){
			this.erpOaLicKeyBiz.createLicFile(erpOaLicKey);
			int result=this.erpOaLicKeyBiz.save(erpOaLicKey);
			if(result==GlobalConstant.ONE_LINE){
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
	
	/**
	 * 组织lic信息内容
	 * @param erpOaLicKey
	 * @return
	 */
	public ErpOaLicKey repenishLic(ErpOaLicKey erpOaLicKey){
		if(erpOaLicKey!=null){
			//组织客户信息
			if(StringUtil.isNotBlank(erpOaLicKey.getCustomerFlow()) && !GlobalConstant.PD_ORG_FLOW.equals(erpOaLicKey.getCustomerFlow())){
				ErpCrmCustomer customer=this.customerBiz.readCustomer(erpOaLicKey.getCustomerFlow());
				if(customer!=null){
					erpOaLicKey.setCustomerName(customer.getCustomerName());
					erpOaLicKey.setAliasName(customer.getAliasName());
				}
			}else if(GlobalConstant.PD_ORG_FLOW.equals(erpOaLicKey.getCustomerFlow())){
				erpOaLicKey.setCustomerName(GlobalConstant.PD_ORG_NAME);
				erpOaLicKey.setAliasName(GlobalConstant.PD_ORG_NAME);
			}
			//组织合同信息
			//CrmContractProduct examPro=null;
			if(StringUtil.isNotBlank(erpOaLicKey.getContractFlow())){
				ErpCrmContract contract=this.contractBiz.readContract(erpOaLicKey.getContractFlow());
				if(contract!=null){
					erpOaLicKey.setContractNo(contract.getContractNo());
				}
				ErpCrmContractProduct product=new ErpCrmContractProduct();
				product.setContractFlow(erpOaLicKey.getContractFlow());
				List<ErpCrmContractProduct> productList=this.contractProductBiz.searchContactProductList(product);
				if(productList!=null && !productList.isEmpty()){
					String productTypeId="";
					String productTypeName="";
					for(ErpCrmContractProduct pro:productList){
						/*if("netExam".equals(pro.getProductTypeId())){
							examPro=pro;
						}*/
						productTypeId+=pro.getProductTypeId();
						productTypeName+=pro.getProductTypeName();
						if(productList.indexOf(pro)!=productList.size()-1){
							productTypeId+=",";
							productTypeName+="，";
						}
					}
					erpOaLicKey.setProductTypeId(productTypeId);
					erpOaLicKey.setProductTypeName(productTypeName);
				}
			}
			//组织工作站名称
			if(StringUtil.isNotBlank(erpOaLicKey.getWorkStationId())){
				Map<String,String> workStationMap=getAllWorkStationMap();
				String [] workStationArray=erpOaLicKey.getWorkStationId().split(",");
				String workStationName="";
				if(workStationArray!=null && workStationArray.length>0){
					for(int i=0;i<workStationArray.length;i++){
						if(workStationMap.containsKey(workStationArray[i])){
							workStationName+=workStationMap.get(workStationArray[i]);
						}
						if(i!=workStationArray.length-1){
							workStationName+="，";
						}
					}
				}
				erpOaLicKey.setWorkStationName(workStationName);
			}
			//组织lic文件发行日期
			erpOaLicKey.setIssueDate(DateUtil.getCurrDate());
			//组织开发语言
			if(StringUtil.isNotBlank(erpOaLicKey.getDevelopLanguage())){
				erpOaLicKey.setDevelopLanguage(DevelopLanguageEnum.getNameById(erpOaLicKey.getDevelopLanguage()));
			}
			//组织加密关键字
			/*if(StringUtil.isNotBlank(erpOaLicKey.getEncryptFlag()) && examPro!=null){
				erpOaLicKey.setEncryptName(examPro.getRegFileClientName());
			}*/
		}
		return erpOaLicKey;
	}
	
	/**
	 * 加载客户合同
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/loadContractByCustomer")
	public String loadContractByCustomer(String customerFlow,Model model){
		ErpCrmCustomer customer=this.customerBiz.readCustomer(customerFlow);
		model.addAttribute("customer", customer);
		ErpCrmContract contract=new ErpCrmContract();
		contract.setCustomerFlow(customerFlow);
		List<String> statuIds=new ArrayList<>();
		statuIds.add(ContractStatusEnum.Auditing.getId());
		statuIds.add(ContractStatusEnum.AuditBack.getId());
		List<ErpCrmContract> contractList=this.contractBiz.searchErpContractList(contract,statuIds);
		model.addAttribute("contractList", contractList);
		Map<String,Object> productMap=new HashMap<String, Object>();
		if(contractList!=null && !contractList.isEmpty()){
			ErpCrmContractProduct product=null;
			for(ErpCrmContract con:contractList){
				product=new ErpCrmContractProduct();
				product.setContractFlow(con.getContractFlow());
				List<ErpCrmContractProduct> productList=this.contractProductBiz.searchContactProductList(product);
				productMap.put(con.getContractFlow(), productList);
			}
		}
		model.addAttribute("productMap", productMap);
		return "erp/lic/contracts";
	}
	
	/**
	 * 查询当前部门的人员信息
	 * @return
	 */
	@RequestMapping(value="/searchDeptUserJson")
	@ResponseBody
	public List<SysUser> searchDeptUserJson(String deptFlow){
		if(StringUtil.isNotBlank(deptFlow)){
			SysUser sysUser=new SysUser();
			sysUser.setDeptFlow(deptFlow);
			sysUser.setStatusId(UserStatusEnum.Activated.getId());
			List<SysUser> userList=this.sysUserBiz.searchUser(sysUser);
			System.err.println("=============="+JSON.toJSONString(userList));
			return userList;
		}
		return null;
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
	
	public Map<String,String> getAllWorkStationMap(){
		Map<String,String> workStationMap=new HashMap<String, String>();
		List<WorkStation> javaWorkStationList=(List<WorkStation>) GlobalContext.getRequest().getServletContext().getAttribute("allWorkStation");
		List<WorkStation> netWorkStationList=new ArrayList<WorkStation>();
		WorkStation netRes=new WorkStation();
		netRes.setWorkStationId("netRes");
		netRes.setWorkStationName("net住院医师过程管理系统");
		WorkStation netExam=new WorkStation();
		netExam.setWorkStationId("netexam");
		netExam.setWorkStationName("net考试系统3.1");
		netWorkStationList.add(netExam);
		netWorkStationList.add(netRes);
		if(javaWorkStationList!=null && !javaWorkStationList.isEmpty()){
			for(WorkStation ws:javaWorkStationList){
				workStationMap.put(ws.getWorkStationId(), ws.getWorkStationName());
			}
		}
		if(netWorkStationList!=null && !netWorkStationList.isEmpty()){
			for(WorkStation ws:netWorkStationList){
				workStationMap.put(ws.getWorkStationId(), ws.getWorkStationName());
			}
		}
		return workStationMap;
	}
}
