package com.pinde.sci.model.edc;

import com.pinde.sci.model.mo.EdcVisit;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class VisitForm implements Serializable{
	
	private static final long serialVersionUID = 3495458349131640251L;
	
	List<EdcVisit> visitList = new ArrayList<EdcVisit>();

	public List<EdcVisit> getVisitList() {
		return visitList;
	}

	public void setVisitList(List<EdcVisit> visitList) {
		this.visitList = visitList;
	}
}
