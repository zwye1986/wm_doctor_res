package com.pinde.sci.biz.edc;

import com.pinde.sci.form.edc.EdcVisitForm;
import com.pinde.sci.form.edc.ElementSerialSeqForm;
import com.pinde.sci.model.mo.*;

import java.util.List;



public interface IVisitBiz {
	List<EdcVisit> searchVisitList(EdcVisitExample example);

	void addVisit(String projFlow);

	List<EdcVisit> searchVisitList(String projFlow);
	List<EdcVisit> searchVisitList(String projFlow, String isVisit);

	EdcVisit readVisit(String visitFlow);
 
	void modify(EdcVisit visit);

	List<EdcVisitModule> searchVisitModule(EdcVisitModuleExample vmExample);

	void addVisitModule(EdcVisitModule vm);
 

	void delVisitEleAttrCode(EdcVisitModule edcVisitModule,String projFlow, String visitFlow, 
			String moduleCode);
 
	List<EdcVisitElement> searchVisitElement(EdcVisitElementExample example);

	List<EdcVisitAttribute> searchVisitAttribute(
			EdcVisitAttributeExample attrExample);

	void saveOrUpdateEdcElementAttr(List<EdcVisitElement> addEleList,
			List<EdcVisitElement> delEleList,
			List<EdcVisitAttribute> addAttrList,
			List<EdcVisitAttribute> delAttrList);

	void delVisit(String visitFlow);

	void addVisit(EdcVisit visit); 

	EdcVisitElement readVisitElement(String projFlow, String visitFlow,
			String moduleCode, String oprateEleCode);

	void addVisitElement(EdcVisitElement visitElement);

	void onOffVisitElement(EdcVisitElement visitElement, String recordStatusN); 

	void delVisitEleAttr(String projFlow, String visitFlow, String moduleCode,
			String oprateEleCode);

	void saveVisitAttr(List<EdcVisitAttribute> addVisitAttrList,
			List<EdcVisitAttribute> modVisitAttrList,
			List<EdcVisitAttribute> delVisitAttrList);      
	

	
	void savePageModuleOrder(String visitFlow,String [] moduleCode);

	
	void savePageElementOrder(String visitFlow,String [] elementCode);

	
	void savePageAttributeOrder(String visitFlow,String [] attrCode);

	EdcProjParam readProjParam(String projFlow);

	List<PubPatientVisit> searchPubPatientVisits(PubPatientVisitExample example);

	List<EdcIe> searchIeList(String projFlow,String ieType);

	List<EdcIe> searchIeList(EdcIeExample example);

	EdcIe readEdcIe(String ieFlow);

	void modifyEdcIe(EdcIe ie);

	void addEdcIe(EdcIe ie);

	int impEdcIE(String projFlow, String icEleVarName,
			String ieeEleVarName);

	void addIEList(String projFlow, String typeId, String eleVarName,
			String passValueFlag);

	List<PubPatientVisit> searchPatientVisit(String projFlow, String orgFlow);

//	List<EdcPatientVisitData> searchPatientVisitDataByModuleCode(
//			String projFlow, String modleCode);

	List<ElementSerialSeqForm> searchElementSerialSeq(String patientFlow,
			String visitFlow);

	List<PubPatientVisit> searchPatientVisitByModule(String projFlow,
			String moduleCode, String inputOperStatusId);

	List<EdcVisitAttrCode> searchVisitAttrCode(
			EdcVisitAttrCodeExample attrCodeExample);

	void saveVisitAttrCode(List<EdcVisitAttrCode> addVisitAttrCodeList,
			List<EdcVisitAttrCode> modVisitAttrCodeList,
			List<EdcVisitAttrCode> delVisitAttrCodeList);

	void delVisitEleAttrCode(String projFlow, String visitFlow,
			String moduleCode, String elementCode);

	void addVisitModule_Element_Attr_Code(EdcVisitModule vm,
			List<EdcVisitElement> veList,
			List<EdcVisitAttribute> visitAttrList,
			List<EdcVisitAttrCode> visitAttrCodeList);

	void _addVisitAttrCode(List<EdcVisitAttrCode> visitAttrCodeList);

	EdcVisitModule searchVisitModule(String visitFlow, String moduleCode);

	List<EdcVisit> searchVisits(String groupFlow);

//	List<EdcVisit> searchCommonVisits(String projFlow);

//	List<EdcVisit> sortVisitByOrdinal(List<EdcVisit> visitList);

	List<EdcVisit> searchVisitsByGroupFlow(String projFlow, String groupFlow,
			String isVisit);

	void saveEdcAttributeOrder(String projFlow, String moduleCode,
			String[] attrCode);

	List<EdcVisitForm> searchVisitsByModuleCode(String moduleCode);

	List<EdcVisitForm> searchVisitsByElementCode(String elementCode);

	List<PubPatientVisit> searchPatientVisit(PubPatientVisit patientVisit);

	int savePatientVisit(PubPatientVisit patientVisit);

	PubPatientVisit readPatientVisit(String recordFlow);

	int updatePatientWindow(String recordFlow);

	EdcVisit searchBaseline(String projFlow, String groupFlow);

	List<EdcPatientVisitData> searchPatientVisitDataByAttrCode(String projFlow,
			String attrCode);

	List<EdcPatientVisitData> searchVisitDataDistinct(String projFlow,
			String attrCode, String attrDataTypeId);

	void savePageModuleOrder2(String visitFlow, String moduleCode,
			Integer ordinal);

} 
 