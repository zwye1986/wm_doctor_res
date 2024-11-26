package com.pinde.res.model.jswjw.mo;

import com.pinde.core.model.TeachingActivityEval;

import java.util.List;

public class TeachingActivityEvalExt extends TeachingActivityEval {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5675231490936611610L;

	private String resultFlow;
	private String userFlow;
	private String evalArr;
	private List<TeachingActivityEval> evals;

	@Override
	public String getResultFlow() {
		return resultFlow;
	}

	@Override
	public void setResultFlow(String resultFlow) {
		this.resultFlow = resultFlow;
	}

	@Override
	public String getUserFlow() {
		return userFlow;
	}

	@Override
	public void setUserFlow(String userFlow) {
		this.userFlow = userFlow;
	}

	public List<TeachingActivityEval> getEvals() {
		return evals;
	}

	public void setEvals(List<TeachingActivityEval> evals) {
		this.evals = evals;
	}

	public String getEvalArr() {
		return evalArr;
	}

	public void setEvalArr(String evalArr) {
		this.evalArr = evalArr;
	}
}
