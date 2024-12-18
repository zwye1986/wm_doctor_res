package com.pinde.sci.form.study;

import com.pinde.core.model.ResDoctor;
import com.pinde.core.model.StudySubject;

public class StudySubDocDetailForm implements java.io.Serializable {
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
