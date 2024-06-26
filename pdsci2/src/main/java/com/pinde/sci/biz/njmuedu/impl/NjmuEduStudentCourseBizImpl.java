package com.pinde.sci.biz.njmuedu.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.njmuedu.*;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduCourseExtMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduStudentCourseExtMapper;
import com.pinde.sci.enums.njmuedu.NjmuEduCourseTypeEnum;
import com.pinde.sci.enums.njmuedu.NjmuEduStudyStatusEnum;
import com.pinde.sci.enums.njmuedu.NjmuQATypeEnum;
import com.pinde.sci.form.njmuedu.OneCourseCreditForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduStudentCourseExample.Criteria;
import com.pinde.sci.model.njmuedu.EduStudentCourseExt;
import com.pinde.sci.model.njmuedu.EduUserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class NjmuEduStudentCourseBizImpl implements INjmuEduStudentCourseBiz{
	@Autowired
	private EduStudentCourseMapper studentCourseMapper;
	@Autowired
	private NjmuEduStudentCourseExtMapper studentCourseExtMapper;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private NjmuEduCourseExtMapper njmuEduCourseExtMapper;
	@Autowired
	private INjmuEduCourseChapterBiz eduCourseChapterBiz;
	@Autowired
	private INjmuEduCourseAnswerBiz answerBiz;
	@Autowired
	private INjmuEduCourseScheduleBiz courseScheduleBiz;
	@Autowired
	private INjmuEduCourseQuestionBiz questionBiz;

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@Override
	public int edit(EduStudentCourse eduStudentCourse) {
		if(StringUtil.isNotBlank(eduStudentCourse.getRecordFlow())){
			GeneralMethod.setRecordInfo(eduStudentCourse, false);
			return studentCourseMapper.updateByPrimaryKeySelective(eduStudentCourse);
		}else{
			eduStudentCourse.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(eduStudentCourse, true);
			return studentCourseMapper.insertSelective(eduStudentCourse);
		}
	}

	@Override
	public Map<String,Map<String, Object>> searchStudentChooseCourses(
			List<EduUserExt> eduUserExtList) {
		Map<String,Map<String, Object>> allCourseByUserMap=new HashMap<String, Map<String,Object>>();
		Map<String, Object> requiredCourseMap=new HashMap<String, Object>();
		Map<String, Object> optionalCourseMap=new HashMap<String, Object>();
		Map<String, Object> publicCourseMap=new HashMap<String, Object>();
		Map<String,Object> allCreditMap=new HashMap<String, Object>();
		Map<String,Object> actualCreditMap=new HashMap<String, Object>();
		if(eduUserExtList!=null && !eduUserExtList.isEmpty()){
			for(EduUserExt eduUserExt:eduUserExtList){
				double allCredit=0;
				double actualCredit=0;
				Map<String,Object> paramMap=new HashMap<String, Object>();
				paramMap.put("userFlow", eduUserExt.getUserFlow());
				paramMap.put("courseTypeId", NjmuEduCourseTypeEnum.Required.getId());
				List<EduStudentCourseExt> eduStudentCourseExtRequiredList=this.studentCourseExtMapper.searchEduStudentCourseExtByUserFlow(paramMap);
				paramMap.put("courseTypeId", NjmuEduCourseTypeEnum.Optional.getId());
				List<EduStudentCourseExt> eduStudentCourseExtOptionalList=this.studentCourseExtMapper.searchEduStudentCourseExtByUserFlow(paramMap);
				paramMap.put("courseTypeId", NjmuEduCourseTypeEnum.Public.getId());
				List<EduStudentCourseExt> eduStudentCourseExtPublicList=this.studentCourseExtMapper.searchEduStudentCourseExtByUserFlow(paramMap);
				requiredCourseMap.put(eduUserExt.getUserFlow(), eduStudentCourseExtRequiredList);
				optionalCourseMap.put(eduUserExt.getUserFlow(), eduStudentCourseExtOptionalList);
				publicCourseMap.put(eduUserExt.getUserFlow(), eduStudentCourseExtPublicList);
			    if(eduStudentCourseExtRequiredList!=null && !eduStudentCourseExtRequiredList.isEmpty()){
			    	for(EduStudentCourseExt eduStudentCourseExt:eduStudentCourseExtRequiredList){
			    		allCredit=allCredit+Double.parseDouble(eduStudentCourseExt.getCourse().getCourseCredit());
						if (NjmuEduStudyStatusEnum.Finish.getId().equals(eduStudentCourseExt.getStudyStatusId())
								&& GlobalConstant.RECORD_STATUS_Y.equals(eduStudentCourseExt.getAchieveCredit())){
			    			actualCredit=actualCredit+Double.parseDouble(eduStudentCourseExt.getCourse().getCourseCredit());
			    		}
			    	}
			    }
			    if(eduStudentCourseExtOptionalList!=null && !eduStudentCourseExtOptionalList.isEmpty()){
			    	for(EduStudentCourseExt eduStudentCourseExt:eduStudentCourseExtOptionalList){
			    		allCredit=allCredit+Double.parseDouble(eduStudentCourseExt.getCourse().getCourseCredit());
						if (NjmuEduStudyStatusEnum.Finish.getId().equals(eduStudentCourseExt.getStudyStatusId())
								&& GlobalConstant.RECORD_STATUS_Y.equals(eduStudentCourseExt.getAchieveCredit())){
			    			actualCredit=actualCredit+Double.parseDouble(eduStudentCourseExt.getCourse().getCourseCredit());
			    		}
			    	}
			    }
			    if(eduStudentCourseExtPublicList!=null && !eduStudentCourseExtPublicList.isEmpty()){
			    	for(EduStudentCourseExt eduStudentCourseExt:eduStudentCourseExtPublicList){
			    		allCredit=allCredit+Double.parseDouble(eduStudentCourseExt.getCourse().getCourseCredit());
						if (NjmuEduStudyStatusEnum.Finish.getId().equals(eduStudentCourseExt.getStudyStatusId())
								&& GlobalConstant.RECORD_STATUS_Y.equals(eduStudentCourseExt.getAchieveCredit())){
			    			actualCredit=actualCredit+Double.parseDouble(eduStudentCourseExt.getCourse().getCourseCredit());
			    		}
			    	}
			    }
			    allCreditMap.put(eduUserExt.getUserFlow(), allCredit);
			    actualCreditMap.put(eduUserExt.getUserFlow(), actualCredit);
			}
		}
		allCourseByUserMap.put("required", requiredCourseMap);
		allCourseByUserMap.put("optional", optionalCourseMap);
		allCourseByUserMap.put("allCreditMap", allCreditMap);
		allCourseByUserMap.put("actualCreditMap", actualCreditMap);
		return allCourseByUserMap;
	}

	@Override
	public Map<String,Object> searchCourseCreditForm(
			String courseFlow) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("courseFlow", courseFlow);
		List<OneCourseCreditForm> fromList=this.studentCourseExtMapper.searchCourseCreditForm(paramMap);
		Map<String,Object> userAndCourseCreditMap=new HashMap<String, Object>();
	    if(fromList!=null && !fromList.isEmpty()){
	    	for(OneCourseCreditForm form:fromList){
	    		userAndCourseCreditMap.put(form.getUserFlow(), form.getCourseCredit());
	    	}
	    }
		return userAndCourseCreditMap;
	}

	@Override
	public int insertRequireCourse(String userFlow) {
		SysUser user=this.userBiz.readSysUser(userFlow);
		if(user!=null){
			EduStudentCourse studentCourse=null;
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("courseTypeId", NjmuEduCourseTypeEnum.Required.getId());
			paramMap.put("userFlow", userFlow);
			List<EduCourse> courseList=this.njmuEduCourseExtMapper.searchNeedAddCourses(paramMap);
			if(courseList!=null && !courseList.isEmpty()){
				for(EduCourse eduCourse:courseList){
					studentCourse=new EduStudentCourse();
					studentCourse.setUserFlow(userFlow);
					studentCourse.setCourseFlow(eduCourse.getCourseFlow());
					studentCourse.setStudyStatusId(NjmuEduStudyStatusEnum.NotStarted.getId());
					studentCourse.setStudyStatusName(NjmuEduStudyStatusEnum.NotStarted.getName());
					studentCourse.setCourseTypeId(NjmuEduCourseTypeEnum.Required.getId());
					studentCourse.setCourseTypeName(NjmuEduCourseTypeEnum.Required.getName());
					studentCourse.setChooseTime(DateUtil.getCurrDateTime(DateUtil.defDtPtn02));
					studentCourse.setCourseCredit(eduCourse.getCourseCredit());
					studentCourse.setCoursePeriod(eduCourse.getCoursePeriod());
					int result=edit(studentCourse);
					if(GlobalConstant.ZERO_LINE==result){
						return GlobalConstant.ZERO_LINE;
					}
				}
			}
		}
		return GlobalConstant.ONE_LINE;
	}

	@Override
	public List<EduStudentCourse> searchStudentCourseList(EduStudentCourse studentCourse) {
		EduStudentCourseExample example = new EduStudentCourseExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(studentCourse.getUserFlow())){
			criteria.andUserFlowEqualTo(studentCourse.getUserFlow());
		}
		if(StringUtil.isNotBlank(studentCourse.getCourseFlow())){
			criteria.andCourseFlowEqualTo(studentCourse.getCourseFlow());
		}
		return studentCourseMapper.selectByExample(example);
	}
	
	@Override
	public double calculateCourseGrade(String courseFlow, String chapterFlow, String userFlow) {
		double courseGrade = 0;
		if(StringUtil.isNotBlank(courseFlow) && StringUtil.isNotBlank(chapterFlow) && StringUtil.isNotBlank(userFlow) ){
			//一、 该学生该课程已完成章节数/该课程总章节数*40
			double videoScore = 0 ;
			EduCourseSchedule finish = new EduCourseSchedule();
			finish.setUserFlow(userFlow);
			finish.setCourseFlow(courseFlow);
			finish.setStudyStatusId(NjmuEduStudyStatusEnum.Finish.getId());
			int finishChapterCount = courseScheduleBiz.getScheduleCount(finish);//该课程已完成章节数
			EduCourseChapter chapter = new EduCourseChapter();
			chapter.setCourseFlow(courseFlow);
			int allChapterCount = eduCourseChapterBiz.getChapterCount(chapter);//该课程总章节数
			if(allChapterCount != 0){
				BigDecimal finishVal = new BigDecimal(finishChapterCount);
				BigDecimal allVal = new BigDecimal(allChapterCount);
				double result = finishVal.divide(allVal, 2, BigDecimal.ROUND_HALF_UP).doubleValue(); //保留两位
				videoScore = result * 40;
			}

			//二、该课程的全部作业平均分 * 60%
			EduQuestion question = new EduQuestion();
			question.setCourseFlow(courseFlow);
			question.setQuesTypeId(NjmuQATypeEnum.Work.getId());
			List<EduQuestion> questionList = questionBiz.searchEduQuestionsList(question);//该课程全部作业
			List<String> questionFlowList = new ArrayList<String>();
			if(questionList != null && !questionList.isEmpty()){
				for(EduQuestion q : questionList){
					if(!questionFlowList.contains(q.getQuestionFlow())){
						questionFlowList.add(q.getQuestionFlow());
					}
				}
			}
			double answerScore = 0;
			if(!questionFlowList.isEmpty()){
				double answerSum = answerBiz.getAnsGradeSum(questionFlowList, userFlow);
				BigDecimal sumVal = new BigDecimal(answerSum);
				BigDecimal sizeVal = new BigDecimal(questionList.size());
				double result =   sumVal.divide(sizeVal, 2, BigDecimal.ROUND_HALF_UP).doubleValue(); //保留两位
				answerScore = result * 0.6;
			}
			courseGrade = videoScore + answerScore;
			courseGrade = (double) (Math.round(courseGrade*100)/100.0);
			String courseGradeVal = _doubleTrans(courseGrade);

			//StudentCourse回写成绩
			if(StringUtil.isNotBlank(userFlow) && StringUtil.isNotBlank(courseFlow)){
				EduStudentCourse studentCourse = new EduStudentCourse();
				studentCourse.setUserFlow(userFlow);
				studentCourse.setCourseFlow(courseFlow);
				List<EduStudentCourse> studentCourseList = searchStudentCourseList(studentCourse);
				if(studentCourseList != null && !studentCourseList.isEmpty()){
					studentCourse = studentCourseList.get(0);
					studentCourse.setCourseGrade(courseGradeVal);
					if(courseGrade > 60){
						studentCourse.setAchieveCredit(GlobalConstant.FLAG_Y);
					}else{
						studentCourse.setAchieveCredit(GlobalConstant.FLAG_N);
					}
					edit(studentCourse);
				}
			}
		}
		return courseGrade;
	}

	@Override
	public List<EduStudentCourse> searchStudentCourseList(EduStudentCourse studentCourse, List<String> courseFlowList) {
		EduStudentCourseExample example = new EduStudentCourseExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(studentCourse.getUserFlow())){
			criteria.andUserFlowEqualTo(studentCourse.getUserFlow());
		}
		if(courseFlowList != null && !courseFlowList.isEmpty()){
			criteria.andCourseFlowIn(courseFlowList);
		}
		return studentCourseMapper.selectByExample(example);
	}

	@Override
	public int getStudentCourseCount(String courseFlow, List<String> studyStatusIdList) {
		if(StringUtil.isNotBlank(courseFlow)){
			EduStudentCourseExample example = new EduStudentCourseExample();
			Criteria criteria = example.createCriteria().andCourseFlowEqualTo(courseFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			if(studyStatusIdList != null && !studyStatusIdList.isEmpty()){
				criteria.andStudyStatusIdIn(studyStatusIdList);
			}
			return studentCourseMapper.countByExample(example);
		}
		return 0;
	}
   
	
}
