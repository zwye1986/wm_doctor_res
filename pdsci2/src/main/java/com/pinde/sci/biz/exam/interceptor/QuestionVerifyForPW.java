package com.pinde.sci.biz.exam.interceptor;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.exam.IQuestionManageBiz;
import com.pinde.sci.biz.exam.impl.QuestionManageBizImpl;
import com.pinde.sci.model.exam.QuestionFileVerifyInfo;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionVerifyForPW extends QuestionVerifyInterceptorAdapter{

	@Override
	public QuestionFileVerifyInfo handle(Object questionInfo,
			Integer questionNo, String questionTypeId , Object handler) {
		QuestionFileVerifyInfo verifyInfo = new QuestionFileVerifyInfo();
		Map<Integer , String> questionData = (Map<Integer , String>)questionInfo;
		verifyInfo.setQuestionTypeId(questionTypeId);
		verifyInfo.setQuestionNo(questionNo);
		int subQuestionCount = questionData.size()-1;
		
		Set<Entry<Integer , String>> questionEntrySet = questionData.entrySet();
		Iterator<Entry<Integer , String>> iterator = questionEntrySet.iterator();
		int i = 0 ;
		while(iterator.hasNext()){
			Entry<Integer , String> iteratorEntry = iterator.next();
			if(subQuestionCount%2!=0){
				verifyInfo.setLineNum(iteratorEntry.getKey());
				verifyInfo.setMsg(verifyInfo.getMsg()+",可能缺少子题内容或子题答案");
				return verifyInfo;
			}
			if(i==0){
				if(StringUtil.isBlank(iteratorEntry.getValue())){
					verifyInfo.setLineNum(iteratorEntry.getKey());
					verifyInfo.setMsg(verifyInfo.getMsg()+",大题的题目内容不能为空");
					return verifyInfo; 
				}else{
					if(verifyQuestionOption(iteratorEntry.getValue())){
						verifyInfo.setLineNum(iteratorEntry.getKey());
						verifyInfo.setMsg(verifyInfo.getMsg()+",题目内容有误(选型中包含大写字母加点)");
						return verifyInfo;
					}
				}
			}else{
				String subQuestionContent = iteratorEntry.getValue();
				if(StringUtil.isBlank(subQuestionContent)){
					verifyInfo.setLineNum(iteratorEntry.getKey());
					verifyInfo.setMsg(verifyInfo.getMsg()+",子题的题目内容不能为空");
					return verifyInfo; 
				}
				if(!iterator.hasNext()){
					verifyInfo.setLineNum(iteratorEntry.getKey());
					verifyInfo.setMsg(verifyInfo.getMsg()+",子题的题目答案不能为空");
					return verifyInfo; 
				}else{
					iterator.next();
				}
			}
			i++;
		}
		
		return null;
	}
	
	private boolean verifyQuestionOption(String optionStr){
		IQuestionManageBiz questionBiz = new QuestionManageBizImpl();
		String replaceAfterOptionStr = questionBiz.replaceQuestionStr(optionStr);
	    Pattern p2 = Pattern.compile("@@\\s*@@");
        Matcher m2 = p2.matcher(replaceAfterOptionStr);
        return m2.find();
	}

}
