package com.pinde.sci.biz.res;

import com.pinde.core.model.ResPaper;
import com.pinde.core.model.TestPaper;


public interface ResPaperBiz {
	/**
	 * 根据方案和规则获取一张试卷
	 * @param speId
	 * @param standardDeptId
	 * @return
	 */
	ResPaper getPaperByRotationAndDept(String speId,String standardDeptId);

	/**
	 * 根据标准科室获取一张试卷
	 * @param standardDeptId
	 * @return
	 */
	ResPaper getPaperByStandardDeptId(String standardDeptId);

	/**
	 * 获取一张标准试卷
	 * @param paperFlow
	 * @return
	 */
	TestPaper readTestPaper(String paperFlow);

	ResPaper getPaperByOrgStandardDeptId(String orgName, String standardDeptId);

	/**
	 * 结业考试试卷
	 * @param standardDeptId
	 * @return
     */
	ResPaper getPaperBySpeId(String standardDeptId);
}
