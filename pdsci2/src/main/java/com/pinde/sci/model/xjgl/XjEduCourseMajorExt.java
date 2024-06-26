package com.pinde.sci.model.xjgl;

import com.pinde.sci.model.mo.EduCourse;
import com.pinde.sci.model.mo.EduCourseMajor;
import com.pinde.sci.model.mo.EduStudentCourse;


public class XjEduCourseMajorExt extends EduCourseMajor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -369192078582875002L;
	
	private EduCourse course;
	private EduStudentCourse studentCourse;
	private String gyFlag;
	

	public EduStudentCourse getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(EduStudentCourse studentCourse) {
		this.studentCourse = studentCourse;
	}

	public EduCourse getCourse() {
		return course;
	}

	public void setCourse(EduCourse course) {
		this.course = course;
	}

	public String getGyFlag() {
		return gyFlag;
	}

	public void setGyFlag(String gyFlag) {
		this.gyFlag = gyFlag;
	}

}
