package com.pinde.sci.model.res;

import com.pinde.core.model.ResGradeBorderline;

import java.util.List;

public class GradeStepStatistics implements java.io.Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ResGradeBorderline gradeBorderline;
	
	private List<GradeStep> gradeSteps;

	public ResGradeBorderline getGradeBorderline() {
		return gradeBorderline;
	}

	public void setGradeBorderline(ResGradeBorderline gradeBorderline) {
		this.gradeBorderline = gradeBorderline;
	}

	public List<GradeStep> getGradeSteps() {
		return gradeSteps;
	}

	public void setGradeSteps(List<GradeStep> gradeSteps) {
		this.gradeSteps = gradeSteps;
	}
	
	
}
