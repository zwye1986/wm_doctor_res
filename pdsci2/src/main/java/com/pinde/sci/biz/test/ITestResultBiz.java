package com.pinde.sci.biz.test;

import com.pinde.sci.model.mo.TestResult;

import java.util.List;

public interface ITestResultBiz {
	/**
	 * 编辑考试成绩
	 * @param result
	 * @return
	 */
	int edit(TestResult result);
	
	/**
	 * 
	 * @param userFlow
	 * @param result
	 * @return
	 */
	List<TestResult> searchResult(String userFlow,TestResult result);
	
}
