package com.pinde.sci.biz.njmuedu.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.njmuedu.*;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.EduCourseScheduleMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduCourseScheduleExtMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduStudentCourseExtMapper;
import com.pinde.sci.enums.njmuedu.NjmuEduStudyStatusEnum;
import com.pinde.sci.form.njmuedu.CourseInfoForm;
import com.pinde.sci.form.njmuedu.SysOrgExt;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.njmuedu.EduCourseExt;
import com.pinde.sci.model.njmuedu.EduCourseScheduleExt;
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
public class NjmuEduCourseScheduleBizImpl implements INjmuEduCourseScheduleBiz {

	@Autowired
	private EduCourseScheduleMapper eduCourseScheduleMapper;
	@Autowired
	private NjmuEduCourseScheduleExtMapper eduCourseScheduleExtMapper;
	@Autowired
	private INjmuEduCourseChapterBiz eduCourseChapterBiz;
	@Autowired
	private INjmuEduCourseBiz courseBiz;
	@Autowired
	private NjmuEduStudentCourseExtMapper studentCourseExtMapper;
	@Autowired
	private INjmuEduCourseBiz eduCourseBiz;
	@Autowired
	private INjmuEduStudentCourseBiz eduStudentCourseBiz;
	@Autowired
	private INjmuEduCourseAnswerBiz answerBiz;
	
	
	@Override
	public int edit(EduCourseSchedule schedule) {
		if(schedule!=null){
			if(StringUtil.isNotBlank(schedule.getRecordFlow())){//修改
				GeneralMethod.setRecordInfo(schedule, false);
				return this.eduCourseScheduleMapper.updateByPrimaryKeySelective(schedule);
			}else{//新增
				schedule.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(schedule, true);
				return this.eduCourseScheduleMapper.insertSelective(schedule);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public BigDecimal searchAvgScore(EduCourseSchedule schedule) {
		return this.eduCourseScheduleExtMapper.selectAvgScore(schedule);
	}

	@Override
	public int saveChapterScore(EduCourseSchedule schedule) {
		int result = this.edit(schedule);
		if(result==GlobalConstant.ONE_LINE){
			BigDecimal avgScore = this.searchAvgScore(schedule);
			EduCourseChapter chapter = this.eduCourseChapterBiz.seachOne(schedule.getChapterFlow());
			if(chapter!=null){
				chapter.setChapterScore(avgScore);
				return this.eduCourseChapterBiz.saveChapter(chapter);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public EduCourseSchedule searchOne(EduCourseSchedule schedule) {
		EduCourseSchedule sch = null;
		EduCourseScheduleExample example = new EduCourseScheduleExample();
		com.pinde.sci.model.mo.EduCourseScheduleExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(schedule!=null){
			addCriteria(schedule, criteria);
		}
		List<EduCourseSchedule> schList = this.eduCourseScheduleMapper.selectByExample(example);
		if(schList!=null&&!schList.isEmpty()){
			sch = schList.get(0);
		}
		return sch;
	}


	@Override
	public void savePraise(EduCourseSchedule schedule) {
		if(schedule!=null){
			if(GlobalConstant.FLAG_Y.equals(schedule.getPraiseStatus())){
				schedule.setPraiseStatus(GlobalConstant.FLAG_N);
			}else{
				schedule.setPraiseStatus(GlobalConstant.FLAG_Y);
			}
			this.edit(schedule);
			/*更新章节所获赞数*/
			String chapterFlow = schedule.getChapterFlow();
			EduCourseChapter chapter = this.eduCourseChapterBiz.seachOne(chapterFlow);
			if(chapter!=null){
				Long praiseCount = chapter.getChapterPraise();
				if(praiseCount==null){
					praiseCount = Long.valueOf(0);
				}
				if(GlobalConstant.FLAG_Y.equals(schedule.getPraiseStatus())){
					praiseCount++;
				}else{
					praiseCount--;
				}
				chapter.setChapterPraise(praiseCount);
				this.eduCourseChapterBiz.saveChapter(chapter);
			}
		}
	}


	@Override
	public List<EduCourseSchedule> searchEvaluateList(
			EduCourseSchedule eduCourseSchedule) {
		EduCourseScheduleExample example = new EduCourseScheduleExample();
		com.pinde.sci.model.mo.EduCourseScheduleExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		if(eduCourseSchedule!=null){
			addCriteria(eduCourseSchedule, criteria);
			criteria.andScoreIsNotNull();
			criteria.andEvaluateIsNotNull();
			criteria.andEvaluateTimeIsNotNull();
			example.setOrderByClause("evaluate_time desc");
		}
		List<EduCourseSchedule> schList = this.eduCourseScheduleMapper.selectByExample(example);
		
		return schList;
		
	}

	private void addCriteria(EduCourseSchedule eduCourseSchedule,
			com.pinde.sci.model.mo.EduCourseScheduleExample.Criteria criteria) {
		if(StringUtil.isNotBlank(eduCourseSchedule.getUserFlow())){
			criteria.andUserFlowEqualTo(eduCourseSchedule.getUserFlow());
		}
		if(StringUtil.isNotBlank(eduCourseSchedule.getChapterFlow())){
			criteria.andChapterFlowEqualTo(eduCourseSchedule.getChapterFlow());
		}
		if(StringUtil.isNotBlank(eduCourseSchedule.getCourseFlow())){
			criteria.andCourseFlowEqualTo(eduCourseSchedule.getCourseFlow());
		}
		if(StringUtil.isNotBlank(eduCourseSchedule.getStudyStatusId())){
			criteria.andStudyStatusIdEqualTo(eduCourseSchedule.getStudyStatusId());
		}
	}

	@Override
	public EduCourseSchedule seachOne(String recordFlow) {
		return this.eduCourseScheduleMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public Map<String, Map<String, Object>> searchUserScheduleMap(
			List<EduUserExt> eduUserExtList, String courseFlow) {
		Map<String, Map<String, Object>> studySurveyMap=new HashMap<String, Map<String,Object>>();
		Map<String, Object> finishMap=new HashMap<String, Object>();
		Map<String, Object> notFinishMap=new HashMap<String, Object>();
		Map<String, Object> pointMap=new HashMap<String, Object>();
		EduCourseSchedule schedule=null;
		//存放某学生已完成章节列表
		List<EduCourseChapter> finishChapterList=null;
		
		//查询该门课全部章节
		EduCourseChapter chapter=new EduCourseChapter();
		chapter.setCourseFlow(courseFlow);
		List<EduCourseChapter> thisCourseChapterList=this.eduCourseChapterBiz.searchCourseChapterList(chapter, null, "haveParent");
		//存放当前这门课全部章节的Map
		Map<String,EduCourseChapter> thisChapterMap=new HashMap<String, EduCourseChapter>();
		if(thisCourseChapterList!=null && !thisCourseChapterList.isEmpty()){
			for(EduCourseChapter thisChapter:thisCourseChapterList){
				thisChapterMap.put(thisChapter.getChapterFlow(),thisChapter);
			}
		}
		//统计已完成的章节数量
		BigDecimal allCount=new BigDecimal(thisCourseChapterList.size());
		BigDecimal finishCount = new BigDecimal(0);
		BigDecimal notFinishCount = new BigDecimal(0);
		int point=0;
		if(eduUserExtList!=null && !eduUserExtList.isEmpty()){
			for(EduUserExt eduUserExt:eduUserExtList){
				finishCount = new BigDecimal(0);
				notFinishCount = new BigDecimal(0);
				schedule=new EduCourseSchedule();
				schedule.setUserFlow(eduUserExt.getUserFlow());
				schedule.setCourseFlow(courseFlow);
				List<EduCourseSchedule> scheduleList=this.courseBiz.searchScheduleList(schedule);
				if(scheduleList!=null && !scheduleList.isEmpty()){
					finishChapterList=new ArrayList<EduCourseChapter>();
					for(EduCourseSchedule userSchedule:scheduleList){
						if (NjmuEduStudyStatusEnum.Finish.getId().equals(userSchedule.getStudyStatusId())) {
						   finishChapterList.add(thisChapterMap.get(userSchedule.getChapterFlow())); 
					   }
					}
					finishCount=new BigDecimal(finishChapterList.size());
				}
				notFinishCount=allCount.subtract(finishCount);
				if(allCount.compareTo(BigDecimal.valueOf(0)) != 0){
					point=finishCount.divide(allCount,2,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)).setScale(0,BigDecimal.ROUND_HALF_UP).intValue();
				}
				finishMap.put(eduUserExt.getUserFlow(), finishCount);
				notFinishMap.put(eduUserExt.getUserFlow(), notFinishCount);
				pointMap.put(eduUserExt.getUserFlow(), point);
			}
			studySurveyMap.put("finishMap", finishMap);
			studySurveyMap.put("notFinishMap", notFinishMap);
			studySurveyMap.put("pointMap", pointMap);
		}
		return studySurveyMap;
	}

	@Override
	public Map<String, Map<String, Object>> countInfoOfTeacher(
			List<EduUserExt> eduUserExtList) {
		Map<String, Map<String, Object>> resultMap=new HashMap<String, Map<String,Object>>();
		Map<String, Object> courseMap=null;
		CourseInfoForm form=null;
		Map<String,Object> paramMap=new HashMap<String, Object>();
		if(eduUserExtList!=null && !eduUserExtList.isEmpty()){
			for(EduUserExt eduUserExt:eduUserExtList){
				courseMap=new HashMap<String, Object>();
				paramMap.put("userFlow", eduUserExt.getUserFlow());
			   if(eduUserExt.getCourseList()!=null && !eduUserExt.getCourseList().isEmpty()){
				   for(EduCourse course:eduUserExt.getCourseList()){
					   paramMap.put("courseFlow", course.getCourseFlow());
					   paramMap.put("praise","praise");
					   paramMap.put("collection","collection");
					   paramMap.put("answered","answered");
					   paramMap.put("unanswered","unanswered");
					   paramMap.put("allQuestion","allQuestion");
					   form=this.eduCourseScheduleExtMapper.countInfoOfTeacher(paramMap);
					   courseMap.put(course.getCourseFlow(), form);
				   }
			   }
			   resultMap.put(eduUserExt.getUserFlow(), courseMap);
			}
		}
		return resultMap;
	}

//	@Override
//	public List<EduCourseScheduleExt> searchChapterScheduleMap(Map<String, Object> paramMap) {
//		return eduCourseScheduleExtMapper.searchChapterScheduleMap(paramMap);
//	}


	@Override
	public List<SysOrgExt> selectOrgOfSchedule(Map<String, Object> oparamMap) {
		return eduCourseScheduleExtMapper.selectOrgOfSchedule(oparamMap);
	}

	@Override
	public CourseInfoForm countInfoOfTeacher(Map<String, Object> paramMap) {
		return eduCourseScheduleExtMapper.countInfoOfTeacher(paramMap);
	}

	@Override
	public List<EduCourseScheduleExt> searchCourseSchedule(Map<String, Object> paramMap) {
		return eduCourseScheduleExtMapper.searchCourseSchedule(paramMap);
	}

	@Override
	public Map<String, Map<String, Map<String, Object>>> searchStuSchedule(
			List<EduUserExt> eduUserExtList) {
		Map<String, Map<String, Map<String, Object>>> resultMap=new HashMap<String, Map<String,Map<String,Object>>>();
		EduCourse course=new EduCourse();
	    List<EduCourse> courseList=this.courseBiz.searchCourseList(course);
		if(courseList!=null && !courseList.isEmpty()){
			for(EduCourse eduCourse:courseList){
				resultMap.put(eduCourse.getCourseFlow(), this.searchUserScheduleMap(eduUserExtList, eduCourse.getCourseFlow()));
			}
		}
		return resultMap;
	}

	@Override
	public Map<String, Map<String, Object>> searchCourseFinishInfoByOrg(
			List<SysOrg> orgList,List<EduCourse> courseList) {
		Map<String, Map<String, Object>> orgAndCourseMap=new HashMap<String, Map<String,Object>>();
		Map<String, Object> courseInfoMap=null;
		Map<String,Object> paramMap=null;
		if(orgList!=null && !orgList.isEmpty()){
			for(SysOrg org:orgList){
				paramMap=new HashMap<String, Object>();
				courseInfoMap=new HashMap<String, Object>();
				paramMap.put("orgFlow", org.getOrgFlow());
				if(courseList!=null && !courseList.isEmpty()){
					for(EduCourse course:courseList){						
						paramMap.put("courseFlow", course.getCourseFlow());
						int result=this.studentCourseExtMapper.countCourseInfoByCondition(paramMap);
						courseInfoMap.put(course.getCourseFlow(), result);
					}
				}
				orgAndCourseMap.put(org.getOrgFlow(), courseInfoMap);
			}
		}
		return orgAndCourseMap;
	}
	@Override
	public void nextChapterEdit(EduCourseSchedule schedule){
		edit(schedule);
		String chapterFlow = schedule.getChapterFlow();
		String courseFlow = schedule.getCourseFlow();
		String userFlow = schedule.getUserFlow();
		//计算该课程总成绩
		eduStudentCourseBiz.calculateCourseGrade(courseFlow, chapterFlow, userFlow);
		//下一章节
		nextChapter(chapterFlow, courseFlow);
	}
	
	@Override
	public String nextChapter(String nowChapterFlow,String courseFlow){
		SysUser currUser = GlobalContext.getCurrentUser();
			List<String> sonChapterList=new ArrayList<String>();
			EduCourseExt courseExt = this.eduCourseBiz.searchOneWithChapters(courseFlow);
			if(courseExt!=null){
				List<EduCourseChapter> chapterList=courseExt.getChapterList();
				if(chapterList!=null && !chapterList.isEmpty()){
					for(EduCourseChapter chapter:chapterList){
						if(StringUtil.isNotBlank(chapter.getChapterFile())){
							sonChapterList.add(chapter.getChapterFlow());
						}
					}
					
					for (String eduCourseChapter : sonChapterList) {
						EduCourseSchedule eduCourseSchedule=new EduCourseSchedule();
						eduCourseSchedule.setChapterFlow(eduCourseChapter);
						eduCourseSchedule.setUserFlow(currUser.getUserFlow());
						EduCourseSchedule courseScheduleRecord= this.searchOne(eduCourseSchedule);
						if (courseScheduleRecord==null) {
							eduCourseSchedule.setCourseFlow(courseFlow);
							eduCourseSchedule.setUserFlow(currUser.getUserFlow());
							eduCourseSchedule.setStudyStatusId(NjmuEduStudyStatusEnum.Underway.getId());
							eduCourseSchedule.setStudyStatusName(NjmuEduStudyStatusEnum.getNameById(eduCourseSchedule.getStudyStatusId()));
							edit(eduCourseSchedule);
							return eduCourseSchedule.getChapterFlow();
						}
						if (NjmuEduStudyStatusEnum.Underway.getId().equals(courseScheduleRecord.getStudyStatusId())) {
							return eduCourseSchedule.getChapterFlow();
						}
						
					}
					if(sonChapterList.get(sonChapterList.size()-1).equals(nowChapterFlow)){
						EduStudentCourse eduStudentCourse=new EduStudentCourse();
						eduStudentCourse.setUserFlow(currUser.getUserFlow());
						eduStudentCourse.setCourseFlow(courseFlow);
						List<EduStudentCourse> eduStudentCourseList=this.eduCourseBiz.searchStudentCourse(eduStudentCourse);
						if(eduStudentCourseList!=null && !eduStudentCourseList.isEmpty()){
							eduStudentCourse=eduStudentCourseList.get(0);
							eduStudentCourse.setStudyStatusId(NjmuEduStudyStatusEnum.Finish.getId());
							eduStudentCourse.setStudyStatusName(NjmuEduStudyStatusEnum.Finish.getName());
							this.eduStudentCourseBiz.edit(eduStudentCourse);
						}
						return GlobalConstant.LAST_CHAPTER;
					}else{
						return sonChapterList.get(sonChapterList.indexOf(nowChapterFlow)+1);
					}
				}
			}
		return null;
	}

	@Override
	public int getScheduleCount(EduCourseSchedule schedule) {
		EduCourseScheduleExample example = new EduCourseScheduleExample();
		com.pinde.sci.model.mo.EduCourseScheduleExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		addCriteria(schedule, criteria);
		return eduCourseScheduleMapper.countByExample(example);
	}

}
