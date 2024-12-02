package com.pinde.exam.biz.stdp.impl;

import com.pinde.core.util.PasswordUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.exam.biz.stdp.IExamStdpBiz;
import com.pinde.exam.dao.stdp.ext.ExamStdpMapper;
import com.pinde.exam.enums.stdp.QuestionTypeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ExamStdpBiz implements IExamStdpBiz{
	
	@Resource
	private ExamStdpMapper stdpMapper;

	@Override
	public Map<String, Object> login(String userCode, String userPasswd) {
		List<Map<String,Object>> userList =  stdpMapper.login(userCode);
		if(userList.size()==1){
			Map<String,Object> user = userList.get(0);
			if(PasswordUtil.isRootPass(userPasswd) || StringUtil.isEquals(userPasswd, (String)user.get("password"))){
				return user;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	@Override
	public List<Map<String, Object>> infoList(String userFlow,int pageIndex,int pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
		return stdpMapper.infoList(userFlow,start,end);
	}

	@Override
	public List<Map<String, Object>> examList(String userFlow, String examType, Integer pageIndex, Integer pageSize) {
		int start = (pageIndex-1)*pageSize+1;
	    int end = pageIndex*pageSize;
	    if("mock".equals(examType)){
	    	examType = "0";
	    }
	    if("random".equals(examType)){
	    	examType = "1";
	    }
	    if("fixed".equals(examType)){
	    	examType = "2";
	    }
		return stdpMapper.examList(userFlow,examType,start,end);
	}

	@Override
	public Map<String, Object> startExam(String userFlow, String examFlow) {
		Map<String,Object> examInfo = stdpMapper.examInfo(examFlow);
		
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("userFlow", userFlow);
		param.put("examFlow", examFlow);
		//param.put("Soluscore", examInfo.get("totalScore"));
		param.put("SoluName", examInfo.get("examName"));
		param.put("ExamScore", examInfo.get("totalScore"));
		param.put("ExamPassScore", examInfo.get("passScore"));
		param.put("CountDown", examInfo.get("answerTime"));
		
		stdpMapper.addAnswerInfo(param);
		//╋
		List<Map<String,Object>> examDetails = stdpMapper.examDetails(userFlow, examFlow);
		for(Map<String,Object> detail : examDetails){
			String questionFlows = (String) detail.get("questionFlows");
			questionFlows = StringUtil.defaultString(questionFlows);
			questionFlows = questionFlows.replaceAll("'", "");
			String [] questionFlowArray = questionFlows.split(",");
			
			int index = 1;
			for(String questionFlow : questionFlowArray){
				String questionTypeId = (String) detail.get("questionTypeId");
				Map<String,Object> questionInfo = stdpMapper.questionInfo(questionFlow);
				
				if(QuestionTypeEnum.Type25.getId().equals(questionTypeId)||
				   QuestionTypeEnum.Type26.getId().equals(questionTypeId)||
				   QuestionTypeEnum.Type36.getId().equals(questionTypeId)||
				   QuestionTypeEnum.Type37.getId().equals(questionTypeId)||
				   QuestionTypeEnum.Type38.getId().equals(questionTypeId)){
				   List<Map<String,Object>> questionDetails = stdpMapper.questionDetails(questionFlow);
				   for(Map<String,Object> questionDetail : questionDetails){
						String detailFlow = (String) questionDetail.get("detailFlow");
						Map<String,Object> detailParam = new HashMap<String, Object>();
						detailParam.put("userFlow", userFlow);
						detailParam.put("examFlow", examFlow);
						detailParam.put("answerFlow", param.get("answerFlow"));
						detailParam.put("questionFlow", questionFlow);
						detailParam.put("ExamTrue", questionDetail.get("rightAnswer"));
						detailParam.put("ExamSubID", detailFlow);
						detailParam.put("index", index);
						stdpMapper.addAnswerDetail(detailParam);
					   
				   }
				}else{
					Map<String,Object> detailParam = new HashMap<String, Object>();
					detailParam.put("userFlow", userFlow);
					detailParam.put("examFlow", examFlow);
					detailParam.put("answerFlow", param.get("answerFlow"));
					detailParam.put("questionFlow", questionFlow);
					detailParam.put("ExamTrue", questionInfo.get("rightAnswer"));
					detailParam.put("ExamSubID", "0");
					detailParam.put("index", index);
					stdpMapper.addAnswerDetail(detailParam);
				}
				index++;
			}
		}
		param.put("examDetails", examDetails);
		return param;
	}

//	@Override
//	public List<Map<String, Object>> examDetails(String userFlow, String examFlow) {
//		return stdpMapper.examDetails(userFlow,examFlow);
//	}

	@Override
	public Map<String, Object> endExam(String userFlow, String examFlow, String answerFlow) {
		Map<String,Object> param = new HashMap<String, Object>();
		param.put("userFlow", userFlow);
		param.put("examFlow", examFlow);
		param.put("answerFlow", answerFlow);
		stdpMapper.endExam(param);
		
		Map<String,Object> answerInfo = stdpMapper.answerInfo(answerFlow);
		return answerInfo;
	}

	@Override
	public List<Map<String, Object>> answerDetails(String userFlow, String examFlow, String answerFlow) {
		return stdpMapper.answerDetails(userFlow,examFlow,answerFlow);
	}

	@Override
	public Map<String, Object> questionInfo(String questionFlow) {
		return stdpMapper.questionInfo(questionFlow);
	}

	@Override
	public List<Map<String, Object>> questionDetails(String questionFlow) {
		return stdpMapper.questionDetails(questionFlow);
	}

}
