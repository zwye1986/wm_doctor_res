package com.pinde.sci.biz.exam.interceptor;

import com.pinde.sci.model.exam.QuestionFileVerifyInfo;

/**
 * 试题校验拦截器
 * @author Administrator
 *
 */
public interface QuestionVerifyInterceptor {

	boolean preHandle(Object questionInfo , Integer questionNo , Object handler);
	
	QuestionFileVerifyInfo handle(Object questionInfo , Integer questionNo , String questionTypeId , Object handler);
	
	void afterHandle(Object questionInfo , Integer questionNo ,  Object handler , Exception ex);
}
