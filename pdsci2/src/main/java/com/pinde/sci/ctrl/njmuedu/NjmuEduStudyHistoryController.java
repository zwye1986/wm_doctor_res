package com.pinde.sci.ctrl.njmuedu;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.njmuedu.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.njmuedu.NjmuEduStudyStatusEnum;
import com.pinde.sci.enums.njmuedu.NjmuQATypeEnum;
import com.pinde.sci.form.njmuedu.EduQuestionForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.njmuedu.EduAnswerExt;
import com.pinde.sci.model.njmuedu.EduQuestionExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/njmuedu/studyHistory")
public class NjmuEduStudyHistoryController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(NjmuEduStudyHistoryController.class);
	private static Integer DEFAULT_START_PAGE_INDEX = 1;//默认起始分页索引
	@Autowired
	private INjmuEduCourseQuestionBiz eduCourseQuestionBiz;
	@Autowired
	private INjmuEduCourseBiz eduCourseBiz;
	@Autowired
	private INjmuEduStudyHistoryBiz eduStudyHistoryBiz;
	@Autowired
	private INjmuEduCourseScheduleBiz courseScheduleBiz;
	@Autowired
	private INjmuEduCourseChapterBiz eduCourseChapterBiz;
	@Autowired
	private INjmuEduCourseAnswerBiz eduCourseAnswerBiz;

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.format("%.1f",d);
	}

	/**
	 * 学习记录
	 * @param model
	 */
	@RequestMapping(value="/showHistory",method={RequestMethod.GET,RequestMethod.POST})
	public String showHistory(String currentPage,String isLoad, Model model){
		currentPage = StringUtil.defaultIfEmpty(currentPage, String.valueOf(DEFAULT_START_PAGE_INDEX));
		int pageSize = 4;
		if(String.valueOf(DEFAULT_START_PAGE_INDEX).equals(currentPage)){
			pageSize = 8;
		}
		PageHelper.startPage(Integer.parseInt(currentPage), pageSize);
		List<EduStudyHistory> historyList = this.eduStudyHistoryBiz.searchList();
		Map<String,Object> dataMap = this.eduStudyHistoryBiz.searchExtData(historyList);
		model.addAttribute("historyList", historyList);
		model.addAttribute("dataMap", dataMap);
		if(GlobalConstant.FLAG_Y.equals(isLoad)){
			return "njmuedu/user/student/studyHistoryLoad";
		}
		return "njmuedu/user/student/studyHistory";
	}

	/**
	 * 我的问答
	 * @param form
	 * @param model
	 */
	@RequestMapping(value="/questionList",method={RequestMethod.GET,RequestMethod.POST})
	public String questionList( EduQuestionForm form , Model model){
		EduQuestion question = new EduQuestion();
		question.setQuesTypeId(NjmuQATypeEnum.QA.getId());
		question.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		form.setQuestion(question);
		List<EduQuestionExt> qList = this.eduCourseQuestionBiz.searchExtList(form);
		model.addAttribute("qList", qList);
		return "njmuedu/user/student/questionList";
	}

	/**
	 * 问题的所有回复
	 * @param questionFlow
	 * @param model
	 */
	@RequestMapping(value="/answerDetail",method=RequestMethod.GET)
	public String answerDetail(String questionFlow,Model model){
		if(StringUtil.isNotBlank(questionFlow)){
			List<EduAnswerExt> answerList = this.eduCourseQuestionBiz.searchAnswers(questionFlow);
			EduQuestion question = this.eduCourseQuestionBiz.readEduQuestion(questionFlow);
			model.addAttribute("answerList", answerList);
			model.addAttribute("question", question);
		}
		return "njmuedu/user/student/answerDetail";
	}

	/**
	 * 我的学分
	 * @param model
	 */
	@RequestMapping(value="/myCredit",method=RequestMethod.GET)
	public String myCredit(Model model){
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		List<EduCourse> courseList = this.eduCourseBiz.searchStudentCreditCourses(userFlow);
		model.addAttribute("courseList", courseList);
		if(courseList != null && !courseList.isEmpty()){
			Map<String, Integer> videoFinishNumMap = new HashMap<String, Integer>();
			Map<String, Integer> allChapterNumMap = new HashMap<String, Integer>();
			Map<String, String> videoScoreMap = new HashMap<String, String>();
			Map<String, Integer> workFinishNumMap = new HashMap<String, Integer>();
			Map<String, Integer> workNumMap = new HashMap<String, Integer>();
			Map<String, String> workScoreMap = new HashMap<String, String>();
			Map<String, String> courseGradeMap = new HashMap<String, String>();
			for(EduCourse ec :courseList){
				String courseFlow = ec.getCourseFlow();
				//1、该课程视频已完成章节数
				EduCourseSchedule finish = new EduCourseSchedule();
				finish.setUserFlow(userFlow);
				finish.setCourseFlow(courseFlow);
				finish.setStudyStatusId(NjmuEduStudyStatusEnum.Finish.getId());
				int videoFinish = courseScheduleBiz.getScheduleCount(finish);

				//2、该课程已完成章节数/该课程总章节数*40
				double videoScore = 0 ;
				EduCourseChapter chapter = new EduCourseChapter();
				chapter.setCourseFlow(courseFlow);
				int allChapterCount = eduCourseChapterBiz.getChapterCount(chapter);//该课程总章节数
				if(allChapterCount != 0){
					BigDecimal finishVal = new BigDecimal(videoFinish);
					BigDecimal allVal = new BigDecimal(allChapterCount);
					double result = finishVal.divide(allVal, 2, BigDecimal.ROUND_HALF_UP).doubleValue(); //保留两位
					videoScore = result * 40;
				}

				double courseGrade = 0;
				EduQuestion question = new EduQuestion();
				question.setCourseFlow(courseFlow);
				question.setQuesTypeId(NjmuQATypeEnum.Work.getId());
				List<EduQuestion> workList = eduCourseQuestionBiz.searchEduQuestionsList(question);//该课程全部作业
				List<String> questionFlowList = new ArrayList<String>();
				if(workList != null && !workList.isEmpty()){
					for(EduQuestion q : workList){
						if(!questionFlowList.contains(q.getQuestionFlow())){
							questionFlowList.add(q.getQuestionFlow());
						}
					}
				}
				int workFinish = 0;
				double workScore = 0;
				if(!questionFlowList.isEmpty()){
					//3、 完成作业数
					EduAnswer answer = new EduAnswer();
					answer.setAnswerUserFlow(userFlow);
					workFinish = eduCourseAnswerBiz.getWorkCount(answer, questionFlowList);
					//4、该课程的全部作业平均分 * 60%
					double answerSum = eduCourseAnswerBiz.getAnsGradeSum(questionFlowList, userFlow);
					BigDecimal sumVal = new BigDecimal(answerSum);
					BigDecimal sizeVal = new BigDecimal(workList.size());
					double result =   sumVal.divide(sizeVal, 2, BigDecimal.ROUND_HALF_UP).doubleValue(); //保留两位
					workScore = result * 0.6;
				}
				courseGrade = videoScore + workScore;
				courseGrade = (double) (Math.round(courseGrade*100)/100.0);
				videoFinishNumMap.put(courseFlow, videoFinish);
				allChapterNumMap.put(courseFlow, allChapterCount);
				videoScoreMap.put(courseFlow, _doubleTrans(videoScore));
				workFinishNumMap.put(courseFlow, workFinish);
				workNumMap.put(courseFlow, workList.size());
				workScoreMap.put(courseFlow,_doubleTrans(workScore));
				courseGradeMap.put(courseFlow, _doubleTrans(courseGrade));
				model.addAttribute("videoFinishNumMap", videoFinishNumMap);
				model.addAttribute("allChapterNumMap", allChapterNumMap);
				model.addAttribute("videoScoreMap", videoScoreMap);
				model.addAttribute("workFinishNumMap", workFinishNumMap);
				model.addAttribute("workNumMap", workNumMap);
				model.addAttribute("workScoreMap", workScoreMap);
				model.addAttribute("courseGradeMap", courseGradeMap);
			}
		}
		return "njmuedu/user/student/myCredit";
	}
	
	@RequestMapping(value="/myTest",method=RequestMethod.GET)
	public String myTest(Model model){
		return "njmuedu/user/student/myTest";
	}
}
