package com.pinde.sci.biz.erp;


import com.pinde.sci.model.mo.ErpProductManage;
import com.pinde.sci.model.mo.ErpProductManageProcess;
import com.pinde.sci.model.mo.ErpProductManageUser;

import java.util.List;
import java.util.Map;

public interface IErpProductBiz {

	List<ErpProductManage> ownerProducts(Map<String, Object> paramMap);

	List<ErpProductManageUser> getProductManageUsers(String manageFlow);

	List<ErpProductManageProcess> getProductProcessList(String manageFlow);

	ErpProductManage readByFlow(String manageFlow);

	/**
	 * 编辑项目信息，根据类型是否发送邮件提醒
	 * @param manage
	 * @param type  1：项目启动提醒  2：项目跟进提醒  3：项目完成提醒
	 * @param isSendEmail 是否发送邮件  true:是
	 * @param processMsg
	 * @return
	 */
	int updateManage(ErpProductManage manage, String type, boolean isSendEmail, String processMsg);

	int saveManage(ErpProductManage manage,List<String> userFlow);

	int saveManageUser(ErpProductManageUser manageUser);

	int saveManageProcess(ErpProductManageProcess manageProcess);

	List<ErpProductManage> process(Map<String, Object> paramMap);

	List<ErpProductManage> query(Map<String, Object> paramMap);
}
