package com.pinde.sci.model.test;

import com.pinde.sci.model.mo.TestPaper;
import com.pinde.sci.model.mo.TestResult;

import java.util.List;

public class TestPaperExt extends TestPaper{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4786768184345081119L;
	
    private List<TestResult> resultList;

	public List<TestResult> getResultList() {
		return resultList;
	}

	public void setResultList(List<TestResult> resultList) {
		this.resultList = resultList;
	}
    
    
}
