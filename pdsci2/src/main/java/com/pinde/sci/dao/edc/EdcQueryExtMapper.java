package com.pinde.sci.dao.edc;

import com.pinde.sci.model.mo.EdcQuery;

import java.util.List;



public interface EdcQueryExtMapper {
	List<EdcQuery> searchUnSolveSdvQuery(String dataRecordFlow);
}