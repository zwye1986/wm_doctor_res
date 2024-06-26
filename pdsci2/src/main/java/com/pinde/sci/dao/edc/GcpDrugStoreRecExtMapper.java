package com.pinde.sci.dao.edc;

import com.pinde.sci.model.edc.RandomMinMaxAssignForm;

import java.util.List;
import java.util.Map;


public interface GcpDrugStoreRecExtMapper {
	List<RandomMinMaxAssignForm> selectAssignDate(String projFlow); 
	Integer selectMaxOrd(Map<String,String> drugStoreRec);
}