package com.pinde.sci.biz.edc;

import com.pinde.sci.model.mo.EdcProjOrg;
import com.pinde.sci.model.mo.PubProjOrg;

import java.util.List;

public interface IProjOrgBiz {

	List<PubProjOrg> search(String projFlow);

	PubProjOrg read(String recordFlow);

	void add(PubProjOrg projOrg);

	void mod(PubProjOrg projOrg);

	void del(PubProjOrg projOrg);

	List<PubProjOrg> searchProjOrg(String projFlow);

	PubProjOrg readProjOrg(String projFlow, String orgFlow);

	EdcProjOrg readEdcProjOrg(String projFlow, String orgFlow);

	void addProjOrg(PubProjOrg projOrg);

	int saveProjOrgLock(EdcProjOrg edcProjOrg);

	/**
	 * 批量删除
	 * @param recordFlowList
	 * @return
	 */
	int delProjOrgByRecordFlows(List<String> recordFlowList);

	List<PubProjOrg> searchProjOrg(PubProjOrg projOrg);
	 
}
