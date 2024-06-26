package com.pinde.sci.biz.edc;

import com.pinde.sci.model.mo.*;

import java.util.List;
import java.util.Map;


public interface IInspectBiz {

	void addEdcQuery(EdcQuery query, EdcVisitDataEvent dataEvent);

	List<EdcQuery> searchEdcQuery(EdcQueryExample example);

	List<EdcVisitDataEvent> searchEdcDataVisitEvent(
			EdcVisitDataEventExample data);

	EdcQuery readEdcQuery(String queryFlow);

	void modifyQuery(EdcQuery query);

	List<EdcQuery> searchUnSolveSdvQuery(String recordFlow);

	void addVisitDataEvent(EdcVisitDataEvent dataEvent);

	void modifyDataEvent(EdcVisitDataEvent dataEvent);

	void addEdcQuery(EdcQuery query, List<EdcVisitDataEvent> dataEventList);

	void addDcf(EdcDcf dcf);

	Integer readDcfSeq(String projFlow, String orgFlow);

	Integer searchQuerySeq(PubPatient patient);

	List<EdcDcf> searchEdcDcf(EdcDcfExample example);

	EdcDcf readEdcDcf(String dcfFlow);

	Map<String, Integer> searchEdcQuery(String projFlow);

	Map<String, String> searchSdvStatus(String projFlow);


	
 
}  
  