package com.pinde.sci.biz.sch;

import com.pinde.core.model.SchAndStandardDeptCfg;
import com.pinde.core.model.SchAndStandardDeptCfgExample;

import java.util.List;
import java.util.Map;


public interface ISchAndStandardDeptCfgBiz {

	SchAndStandardDeptCfg readByFlow(String recordFlow);

	int save(SchAndStandardDeptCfg cfg);

	List<SchAndStandardDeptCfg> getListByOrgFlow(String orgFlow);

	SchAndStandardDeptCfg readBySchDeptFlow(String schDeptFlow);

	/**
	 * 查询符合条件的一个SchAndStandardDeptCfg
	 * @param paramMap
	 * @return
     */
	SchAndStandardDeptCfg searchByItems(Map<String, String> paramMap);

	List<Map<String,String>> searchByRequired(String rotationFlow,String isRequired);

	List<Map<String,String>> searchByLastRotation(String orgFlow);

	List<Map<String,String>> searchByLastRotationBySpe(String orgFlow,String speId,String isRequired);

	SchAndStandardDeptCfg readBySchDeptFlowAndOrgFlow(String schDeptFlow,String orgFlow);

	List<SchAndStandardDeptCfg> selectByStandardDeptId(String orgFlow,String standardDeptId);

	List<SchAndStandardDeptCfg> searchSchAndStandardDeptByExample(SchAndStandardDeptCfgExample example);

	List<SchAndStandardDeptCfg> getListByOrgFlow(String var1, List<String> var2);
}
