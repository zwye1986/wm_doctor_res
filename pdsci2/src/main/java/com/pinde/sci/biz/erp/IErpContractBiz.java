package com.pinde.sci.biz.erp;

import com.pinde.sci.model.erp.ErpCrmContractExt;
import com.pinde.sci.model.erp.ErpCrmContractLicExt;
import com.pinde.sci.model.erp.ErpCrmContractPowerExt;
import com.pinde.sci.model.erp.ErpCrmCustomerUserExt;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface IErpContractBiz {
    /**
     * 
     * @param contract
     * @return
     */
	public List<ErpCrmContract> searchErpContractList(ErpCrmContract contract,List<String> statusIds);
	/**
	 * 根据条件查询合同
	 * @param paramMap
	 * @return
	 */
	public List<ErpCrmContractExt> searchErpContractListByCondition(Map<String,Object> paramMap);
	
	/**
	 * 保存合同相关信息
	 * @param contract
	 * @param productList
	 * @param userList
	 * @param file
	 * @return
	 */
	public String saveContractInfo(ErpCrmContract contract,List<ErpCrmContractProduct> productList,List<ErpCrmCustomerUser> userList,MultipartFile file,List<ErpCrmContractPayPlan> payPlanList,List<ErpCrmContractRef> refList
			,List<ErpCrmContractBillPlan> billPlanList,List<ErpCrmContractPower> powerList,List<ErpCrmContractOtherPower> otherPowerList);
	/**
	 * 保存合同
	 * @param contract
	 * @param file
	 * @return
	 */
	public String saveContract(ErpCrmContract contract,MultipartFile file);
	
	/**
	 * 查询主合同
	 * @param customerFlow
	 * @return
	 */
	List<ErpCrmContract> searchMainContact(String customerFlow,String contractFlow);
	/**
	 * 查询合同及其产品，联系人，回款，客户的详细信息
	 * @param contractFlow
	 * @return
	 */
     public Map<String,Object> readContractExt(String contractFlow,ErpCrmCustomerUser crmCustomerUser);
     /**
      * 查询合同详细信息
      * @param contractFlow
      * @return
      */
     public ErpCrmContract readContract(String contractFlow);
     /**
      * 修改合同的子合同数量
      * @param contractFlow
      * @return
      */
     public int updateContractSubCount(String contractFlow,boolean f);
     
	 List<ErpCrmContractExt> searchContracts(Map<String, Object> paramMap);
	 /**
	  * 删除合同
	  * @param contractFlow
	  * @return
	  */
	 public int deleteContract(String contractFlow);
	 
	    /**
	     * 查询合同列表，工作联系单专用
	     * @param contract
	     * @return
	     */
		public List<ErpCrmContract> searchErpContracts(ErpCrmContract contract,List<String> statusIds);

	public BigDecimal searchAllMoney(Map<String, Object> paramMap);

	List<ErpCrmContract> searchContractsList(Map<String, Object> paramMap);

	int addFeedBack(ErpCrmContract contract);

	List<ErpCrmContractLicExt> searchContractLics(Map<String, Object> paramMap);

	int updateContract(ErpCrmContract contract1);

	List<ErpCrmContractExt> searchErpContractListNew(Map<String, Object> paramMap);

	List<ErpCrmContractExt> searchErpContractListBillNew(Map<String, Object> paramMap);

	List<Map<String,Object>> yszcx(Map<String, Object> paramMap);

	List<Map<String,Object>> zxcx(Map<String, Object> paramMap);

	List<Map<String,Object>> cwyscx(Map<String, Object> paramMap);

	List<Map<String,Object>> dkcx(Map<String, Object> paramMap);

	List<Map<String,Object>> wh(Map<String, Object> paramMap);

	ErpCrmProductBuildInfo getEmptyBuildInfoByFlow(String contractFlow);

	ErpCrmProductBuildInfo getBuildInfoByFlow(String contractFlow);

	int saveBuilding(ErpCrmProductBuildInfo info);

	String saveContractInfoNew(ErpCrmContract contract, List<ErpCrmContractProduct> erpCrmContractProducts, List<ErpCrmCustomerUserExt> erpCrmCustomerUsers, MultipartFile file, List<ErpCrmContractRef> refList, List<ErpCrmContractPowerExt> powerList, List<ErpCrmContractOtherPower> otherPowerList);

	Map<String,Object> cwysFund(Map<String, Object> paramMap);

	Map<String,Object> searchErpContractListNewMap(Map<String, Object> paramMap);

	Map<String,Object> waitPayAuditMap(Map<String, Object> paramMap);
	Map<String,Object> waitBillAuditMap(Map<String, Object> paramMap);

	List<ErpCrmContract> searchErpContractList2(ErpCrmContract contract, List<String> statuIds);
}
