package com.pinde.sci.biz.sch;

import com.pinde.core.model.SchDeptRel;

import java.util.List;



public interface ISchDeptRelBiz {

	int editDeptRel(SchDeptRel deptRel);

//	SchDeptRel readDeptRel(String recordFlow);

	List<SchDeptRel> searchRelByStandard(String orgFlow, String standardDeptId);

	List<SchDeptRel> searchRelBySchDept(String schDeptFlow);

	List<SchDeptRel> searchRelByOrg(String orgFlow);

	List<SchDeptRel> searchRelBySchDepts(List<String> schDeptFlows);

	int depSchDeptRel(String schDeptFlow);

}
