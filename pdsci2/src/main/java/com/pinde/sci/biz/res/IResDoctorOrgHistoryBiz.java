package com.pinde.sci.biz.res;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.ResDoctorOrgHistory;

import java.util.List;

public interface IResDoctorOrgHistoryBiz {
	int editDocOrgHistory(ResDoctorOrgHistory docOrgHistory);
	
	ResDoctorOrgHistory readDocOrgHistory(String recordFlow);
	
	List<ResDoctorOrgHistory> searchIsInDocByDocOrgHis(ResDoctorOrgHistory docOrgHistory);
	List<ResDoctorOrgHistory> searchOrgFlowByhistoryOrgFlow(ResDoctorOrgHistory docOrgHistory);
	List<ResDoctorOrgHistory> searchHistoryByDoctorFlows(
			List<String> doctorFlows);

	int editDocOrgHistoryAndDoc(ResDoctorOrgHistory docOrgHistory,
			ResDoctor doctor);
}
