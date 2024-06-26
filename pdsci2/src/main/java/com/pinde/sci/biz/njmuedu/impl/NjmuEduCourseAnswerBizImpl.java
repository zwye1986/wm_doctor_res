package com.pinde.sci.biz.njmuedu.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.njmuedu.INjmuEduCourseAnswerBiz;
import com.pinde.sci.biz.njmuedu.INjmuEduStudentCourseBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.EduAnswerMapper;
import com.pinde.sci.dao.base.EduQuestionMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduAnswerExtMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduCourseExtMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduQuestionExtMapper;
import com.pinde.sci.enums.njmuedu.NjmuEduQuestionStatusEnum;
import com.pinde.sci.enums.njmuedu.NjmuQATypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.EduAnswerExample.Criteria;
import com.pinde.sci.model.njmuedu.EduAnswerExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class NjmuEduCourseAnswerBizImpl implements INjmuEduCourseAnswerBiz{
    
	@Autowired
	private EduAnswerMapper eduAnswerMapper;
	@Autowired
	private NjmuEduAnswerExtMapper njmuEduAnswerExtMapper;
	@Autowired
	private EduQuestionMapper eduQuestionMapper;
	@Autowired
	private NjmuEduCourseExtMapper eduCourseExtMapper;
	@Autowired
	private NjmuEduQuestionExtMapper eduQuestionExtMapper;
	@Autowired
	private INjmuEduStudentCourseBiz studentCourseBiz;
	
	@Override
	public List<EduAnswer> searchAnswerList(String questionFlow) {
		List<EduAnswer> answerList=eduCourseExtMapper.searchEduAnswerList(questionFlow);
		return answerList;
	}

	@Override
	public int saveAnswer(EduAnswer eduAnswer) {
		//获取当前登录者权限列表
		List<String> currRoleList=(List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_ROLE_LIST); 
		//获取学生角色流水号
		String stuRoleFlow=InitConfig.getSysCfg("njmuedu_student_role_flow");
		eduAnswer.setAnswerFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(eduAnswer, true);
		EduQuestion question=eduQuestionMapper.selectByPrimaryKey(eduAnswer.getQuestionFlow());
		if(null!=question){
			if(GlobalConstant.RECORD_STATUS_Y.equals(question.getSubmitTeacher())){
				if(currRoleList!=null && !currRoleList.isEmpty()){
					if(!currRoleList.contains(stuRoleFlow)){
						question.setQuestionStatusId(NjmuEduQuestionStatusEnum.Answered.getId());
						question.setQuestionStatusName(NjmuEduQuestionStatusEnum.Answered.getName());
					}
				}
			}
			GeneralMethod.setRecordInfo(question, false);
		}
		int questionResult = eduQuestionMapper.updateByPrimaryKey(question);		
		int answerResult = eduAnswerMapper.insertSelective(eduAnswer);
		if(GlobalConstant.ONE_LINE == questionResult && GlobalConstant.ONE_LINE == answerResult){
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}

	@Override
	public EduAnswerExt searchAnswerExt(String answerFlow) {
		EduAnswerExt answerExt = null;
		if(StringUtil.isNotBlank(answerFlow)){
			answerExt = this.eduQuestionExtMapper.selectAnswerExt(answerFlow);
		}
		return answerExt;
	}

	
	@Override
	public double getAnsGradeSum(List<String> questionFlowList, String answerUserFlow) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("questionFlowList", questionFlowList);
		paramMap.put("answerUserFlow", answerUserFlow);
		return njmuEduAnswerExtMapper.getAnsGradeSum(paramMap);
	}

	@Override
	public int saveStudentWork(EduAnswer eduAnswer) {
		if(StringUtil.isNotBlank(eduAnswer.getAnswerFlow())){
			GeneralMethod.setRecordInfo(eduAnswer, false);
			return eduAnswerMapper.updateByPrimaryKeySelective(eduAnswer);
		}else{
			eduAnswer.setAnswerFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(eduAnswer, true);
			return eduAnswerMapper.insert(eduAnswer);
		}
	}

	@Override
	public List<EduAnswerExt> searchStudentWorkAnswerList(EduAnswer answer, SysUser sysUser) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("answer", answer);
		paramMap.put("sysUser", sysUser);
		return njmuEduAnswerExtMapper.searchStudentWorkAnswerList(paramMap);
	}

	@Override
	public int saveWorkAndGrade(EduAnswer eduAnswer, EduCourseChapter courseChapter) {
		String courseFlow = courseChapter.getCourseFlow();
		String chapterFlow = courseChapter.getChapterFlow();
		String userFlow = eduAnswer.getAnswerUserFlow();
		saveStudentWork(eduAnswer);
		double courseGrade = studentCourseBiz.calculateCourseGrade(courseFlow, chapterFlow, userFlow);
		if(courseGrade != 0){
			return GlobalConstant.ONE_LINE;
		}
		return GlobalConstant.ZERO_LINE;
	}
	

	@Override
	public List<EduAnswer> searchMyWorkAnswerList(EduAnswer answer, List<String> questionFlowList) {
		List<EduAnswer> answerList = null;
		if(questionFlowList != null && !questionFlowList.isEmpty()){
			EduAnswerExample example = new EduAnswerExample();
			Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
			criteria.andAnswerUserFlowEqualTo(answer.getAnswerUserFlow()).andQuestionFlowIn(questionFlowList).andAnsTypeIdEqualTo(NjmuQATypeEnum.Work.getId());
			answerList = eduAnswerMapper.selectByExample(example);
		}
		return answerList;
	}

	@Override
	public int getWorkCount(EduAnswer answer, List<String> questionFlowList) {
		EduAnswerExample example = new EduAnswerExample();
		Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		criteria.andAnswerUserFlowEqualTo(answer.getAnswerUserFlow()).andQuestionFlowIn(questionFlowList).andAnsTypeIdEqualTo(NjmuQATypeEnum.Work.getId());
		return eduAnswerMapper.countByExample(example);
	}

}
