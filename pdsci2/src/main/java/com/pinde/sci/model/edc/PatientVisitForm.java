package com.pinde.sci.model.edc;

import com.pinde.sci.model.mo.EdcPatientVisit;
import com.pinde.sci.model.mo.PubPatientVisit;

import java.io.Serializable;

public class PatientVisitForm implements Serializable{
	
	private static final long serialVersionUID = -762701198587448019L;
	
	private PubPatientVisit patientVisit;
	private EdcPatientVisit edcPatientVisit;
	public PubPatientVisit getPatientVisit() {
		return patientVisit;
	}
	public void setPatientVisit(PubPatientVisit patientVisit) {
		this.patientVisit = patientVisit;
	}
	public EdcPatientVisit getEdcPatientVisit() {
		return edcPatientVisit;
	}
	public void setEdcPatientVisit(EdcPatientVisit edcPatientVisit) {
		this.edcPatientVisit = edcPatientVisit;
	}

}
