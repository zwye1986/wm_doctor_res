package com.pinde.sci.biz.njmuedu.impl;

import com.pinde.sci.biz.njmuedu.INjmuEduUserCenterBiz;
import com.pinde.sci.dao.njmuedu.NjmuEduCourseExtMapper;
import com.pinde.sci.enums.njmuedu.NjmuEduCourseTypeEnum;
import com.pinde.sci.enums.njmuedu.NjmuQATypeEnum;
import com.pinde.sci.model.mo.EduQuestion;
import com.pinde.sci.model.mo.EduUser;
import com.pinde.sci.model.njmuedu.EduCourseExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class NjmuEduUserCenterBizImpl implements INjmuEduUserCenterBiz{

	@Autowired
	private NjmuEduCourseExtMapper eduCourseExtMapper;
	
	@Override
	public Map<String, Object> countUserCourseByType(
			String userFlow) {
		Map<String, Object> resultMap=new HashMap<String, Object>();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		for (NjmuEduCourseTypeEnum type : NjmuEduCourseTypeEnum.values()) {
			paramMap.put("userFlow", userFlow);
			paramMap.put("courseTypeId",type.getId());
			int countCourse=this.eduCourseExtMapper.countUserCourseByType(paramMap);
		    	resultMap.put(type.getId(), countCourse);
		}
		return resultMap;
	}

	@Override
	public Map<String, Object> countTchCourse(String userFlow) {
		
		Map<String, Object> resultMap=new HashMap<String, Object>();
		Map<String, Object> paramMap=new HashMap<String, Object>();
		for (NjmuEduCourseTypeEnum type : NjmuEduCourseTypeEnum.values()) {
			paramMap.put("userFlow", userFlow);
			paramMap.put("courseTypeId",type.getId());
			int countCourse=this.eduCourseExtMapper.countTchCourse(paramMap);
		    	resultMap.put(type.getId(), countCourse);
		}
		return resultMap;
	}

	@Override
	public List<EduCourseExt> countRecommendCourseByChooseMost(EduUser user){
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("eduUser", user);
		paramMap.put("courseTypeId", NjmuEduCourseTypeEnum.Optional.getId());
		return eduCourseExtMapper.countRecommendCourseByChooseMost(paramMap);
	}

	@Override
	public List<EduCourseExt> countRecommendCourseByScoreMost(EduUser user) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("eduUser", user);
		paramMap.put("courseTypeId", NjmuEduCourseTypeEnum.Optional.getId());
		return eduCourseExtMapper.countRecommendCourseByScoreMost(paramMap);
	}

	@Override
	public List<EduCourseExt> searchCourseListByQuestionOrder(EduUser eduUser) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("eduUser", eduUser);
		EduQuestion eduQuestion = new EduQuestion();
		eduQuestion.setQuesTypeId(NjmuQATypeEnum.QA.getId());
		paramMap.put("eduQuestion", eduQuestion);
		return eduCourseExtMapper.searchCourseListByQuestionOrder(paramMap);
	}

	@Override
	public List<EduCourseExt> searchCourseListByScoreOrder(EduUser eduUser) {
		Map<String,Object> paramMap=new HashMap<String, Object>();
		paramMap.put("eduUser", eduUser);
		return eduCourseExtMapper.searchCourseListByScoreOrder(paramMap);
	}
	
}
