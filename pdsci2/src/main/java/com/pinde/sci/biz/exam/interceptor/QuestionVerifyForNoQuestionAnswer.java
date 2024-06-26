package com.pinde.sci.biz.exam.interceptor;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.model.exam.QuestionFileVerifyInfo;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class QuestionVerifyForNoQuestionAnswer extends QuestionVerifyInterceptorAdapter{

	@Override
	public QuestionFileVerifyInfo handle(Object questionInfo,
			Integer questionNo, String questionTypeId , Object handler) {
		QuestionFileVerifyInfo verifyInfo = new QuestionFileVerifyInfo();
		Map<Integer , String> questionData = (Map<Integer , String>)questionInfo;
		Set<Entry<Integer , String>> entrySet = questionData.entrySet();
		for(Entry<Integer , String> entry:entrySet){
			verifyInfo.setQuestionTypeId(questionTypeId);
			verifyInfo.setQuestionNo(questionNo);
			verifyInfo.setLineNum(entry.getKey());
			if(entry.getValue().startsWith("题目答案:")){
				String content = entry.getValue(); 
				String questionAnswer = content.substring(content.indexOf(":")+1);
				if(StringUtil.isNotBlank(questionAnswer)){
					verifyInfo.setMsg("该题型不需要有题目答案");
					return verifyInfo;
				}
				
			}
		}
		return null;
	}

}
