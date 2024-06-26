package com.pinde.sci.biz.njmuedu;

import com.pinde.sci.model.mo.EduAnswer;
import com.pinde.sci.model.mo.EduCourseChapter;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.njmuedu.EduAnswerExt;

import java.util.List;

public interface INjmuEduCourseAnswerBiz {
   
	/**
	 * 保存一条答案
	 * @param eduAnswer
	 * @return
	 */
	public int saveAnswer(EduAnswer eduAnswer);
	/**
	 * 根据问题流水号查询相应答案
	 * @param questionFlowList
	 * @return
	 */
	List<EduAnswer> searchAnswerList(String questionFlow);
	/**
	 * 查询回复
	 * @param answerFlow
	 * @return
	 */
	EduAnswerExt searchAnswerExt(String answerFlow);

	/**
	 * 查询该课程的作业平均分
	 * @param courseFlow
	 * @return
	 */
	double getAnsGradeSum(List<String> questionFlowList, String answerUserFlow);
	/**
	 * 保存作业
	 * @param eduAnswer
	 * @return
	 */
	int saveStudentWork(EduAnswer eduAnswer);
	
	
	/**
	 * 查询作业
	 * @param answer
	 * @return
	 */
	public List<EduAnswerExt> searchStudentWorkAnswerList(EduAnswer answer, SysUser sysUser);
	
	/**
	 * 保存作业成绩
	 * @param eduAnswer
	 * @return
	 */
	int saveWorkAndGrade(EduAnswer eduAnswer, EduCourseChapter courseChapter);
	
	/**
	 * 根据作业流水号查询我的作业
	 * @param questionFlowList
	 * @return
	 */
	List<EduAnswer> searchMyWorkAnswerList(EduAnswer answer, List<String> questionFlowList);
	
	/**
	 * 查询记数
	 * @param answer
	 * @param questionFlowList
	 * @return
	 */
	int getWorkCount(EduAnswer answer, List<String> questionFlowList);
	
}
