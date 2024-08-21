package com.pinde.sci.form.study;

import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.StudySubject;

import java.io.Serializable;

public class StudySubDocDetailForm implements Serializable {
	/**
	 * @author Administrator
	 */
	private static final long serialVersionUID = 3265544507795331566L;

	private StudySubject studySubject;
	private ResDoctor resDoctor;

    
	public StudySubject getStudySubject() {
		return studySubject;
	}
	public void setStudySubject(StudySubject studySubject) {
		this.studySubject = studySubject;
	}
	public ResDoctor getResDoctor() {
		return resDoctor;
	}
	public void setResDoctor(ResDoctor resDoctor) {
		this.resDoctor = resDoctor;
	}

}
