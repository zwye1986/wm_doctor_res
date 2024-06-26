package com.pinde.sci.biz.sch;

import com.pinde.sci.model.mo.SchDeptExternalRel;

import java.util.List;



public interface ISchDeptExternalRelBiz {
	int editSchDeptExtRel(SchDeptExternalRel schDeptExtRel);

//	SchDeptExternalRel readSchDeptExtRel(String recordFlow);
	
	SchDeptExternalRel readSchDeptExtRelBySchDept(String schDeptFlow);
	List<SchDeptExternalRel> readSchDeptExtRelByDept(String deptFlow);
	SchDeptExternalRel readSchDeptExtRelByDeptAndRelOrgFlow(String deptFlow,String relOrgFlow);

	List<SchDeptExternalRel> readSchDeptExtRelByRelOrgFlow(String relOrgFlow);

	List<SchDeptExternalRel> searchSchDeptExtRel(String orgFlow);

	int delSchDeptRel(String schDeptFlow);

	/**
	 * 获取该科室对几个机构公开
	 * @param deptFlow
	 * @return
	 */
//	int getExternalOrgNum(String deptFlow);

	/**
	 * 获取该用户是否存在公开的部门
	 * @param userFlow
	 * @return
	 */
	int getExternalNum(String userFlow);
}
