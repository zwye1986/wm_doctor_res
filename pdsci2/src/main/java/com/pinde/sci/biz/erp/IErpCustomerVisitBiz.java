package com.pinde.sci.biz.erp;

import com.pinde.sci.model.erp.ErpCrmContractExt;
import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 客户回访BIZ层
 *  @author 麻成双
 *  @since 2017-08-02
 */
public interface IErpCustomerVisitBiz {

	/**
	 * 客户回访查询
	 * @param customerVisit
	 * @return
     */
	public List<ErpCrmCustomerVisit> searchCustomerVisit(ErpCrmCustomerVisit customerVisit);

	/**
	 * 保存客户回访
	 * @param customerVisit
	 * @return
	 */
	public int saveCustomerVisit(ErpCrmCustomerVisit customerVisit);

     /**
      * 查询单条回访信息
      * @param visitFlow
      * @return
      */
     public ErpCrmCustomerVisit readCustomerVisit(String visitFlow);

	 /**
	  * 删除回访信息
	  * @param visitFlow
	  * @return
	  */
	 public int deleteCustomerVisit(String visitFlow);

}
