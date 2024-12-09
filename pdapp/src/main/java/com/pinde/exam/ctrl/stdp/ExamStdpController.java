package com.pinde.exam.ctrl.stdp;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.StringUtil;
import com.pinde.exam.biz.stdp.IExamStdpBiz;
import com.pinde.exam.enums.stdp.QuestionTypeEnum;
import com.pinde.core.common.enums.ResultEnum;

@Controller
@RequestMapping("/exam/stdp")
public class ExamStdpController{    
	private static Logger logger = LoggerFactory.getLogger(ExamStdpController.class);
	
	@Autowired
    private IExamStdpBiz stdpBiz;
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "exam/stdp/500";
    }
	
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "exam/stdp/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String examType,String examFlow,String answerFlow,String questionFlow,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("examFlow", examFlow);
		session.setAttribute("questionFlow", questionFlow);
		session.setAttribute("examType", examType);
		session.setAttribute("answerFlow", answerFlow);
		return "/exam/stdp/test";
	}
	
	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "exam/stdp/version";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,HttpServletRequest request,HttpServletResponse response,Model model){
		String result = "exam/stdp/login";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userCode)){
			model.addAttribute("resultId",ResultEnum.EmptyUserCode.getId());
			model.addAttribute("resultType", ResultEnum.EmptyUserCode.getName());
			return result;
		}
		if(StringUtil.isBlank(userPasswd)){
			model.addAttribute("resultId", ResultEnum.EmptyPasswd.getId());
			model.addAttribute("resultType", ResultEnum.EmptyPasswd.getName());
			return result;
		}
		//login
		Map<String,Object> user = stdpBiz.login(userCode, userPasswd);
		if(user==null){
			model.addAttribute("resultId",ResultEnum.ErrorUser.getId());
			model.addAttribute("resultType", ResultEnum.ErrorUser.getName());
			return result;
		}
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		model.addAttribute("user" , user);
		return result;
	}
	
	@RequestMapping(value={"/infoList"},method={RequestMethod.GET})
	public String infoList(String userFlow,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "exam/stdp/infoList";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "起始页为空");
			return result;
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "页面大小为空");
			return result;
		}
		
		List<Map<String,Object>> infoList = stdpBiz.infoList(userFlow,pageIndex,pageSize);
		model.addAttribute("dataCount", stdpBiz.infoList(userFlow,1,Integer.MAX_VALUE).size());
		model.addAttribute("infoList" , infoList);
		return result;
	}
	
	@RequestMapping(value={"/examList"},method={RequestMethod.GET})
	public String dataList(String userFlow,String examType,String searchData,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "exam/stdp/examList";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(examType)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "卷子类型为空");
			return result;
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "起始页为空");
			return result;
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "页面大小为空");
			return result;
		}
//		if(StringUtil.isNotBlank(searchData)){
//			try {
//				searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
//			} catch (UnsupportedEncodingException e) {
//				 logger.error("",e);
//			}
//			@SuppressWarnings("unchecked")
//			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
//			System.err.println(searchMap);
//		}
		
		List<Map<String,Object>> examList = stdpBiz.examList(userFlow,examType,pageIndex,pageSize);
		model.addAttribute("dataCount", stdpBiz.examList(userFlow,examType,1,Integer.MAX_VALUE).size());
		model.addAttribute("examList" , examList);
		return result;
	}
	
	@RequestMapping(value={"/startExam"},method={RequestMethod.GET})
	public String startExam(String userFlow,String examFlow,HttpServletRequest request,Model model){
		String result = "exam/stdp/startExam";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(examFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "卷子标识符为空");
			return result;
		}
		Map<String,Object> answerInfo = stdpBiz.startExam(userFlow, examFlow);
		model.addAttribute("answerInfo", answerInfo);
		
		String allQuestionFlows = "";
		@SuppressWarnings("unchecked")
		List<Map<String,Object>> examDetails = (List<Map<String, Object>>) answerInfo.get("examDetails");
		for(Map<String,Object> detail : examDetails){
			String questionTypeId = (String) detail.get("questionTypeId");
			String questionTypeName = QuestionTypeEnum.getNameById(questionTypeId);
			detail.put("questionTypeName", questionTypeName);
			
			String questionFlows = (String) detail.get("questionFlows");
			questionFlows = StringUtil.defaultString(questionFlows).trim();
			if(StringUtil.isNotBlank(questionFlows)){
				allQuestionFlows += questionFlows+",";
			}
		}
		model.addAttribute("allQuestionFlows", allQuestionFlows);
		model.addAttribute("examDetails", examDetails);
		return result;
	}
	
	@RequestMapping(value={"/questionInfo"},method={RequestMethod.GET})
	public String questionInfo(String userFlow,String examFlow,String answerFlow,String questionFlow,HttpServletRequest request,Model model){
		String result = "exam/stdp/questionInfo";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(examFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "卷子标识符为空");
			return result;
		}
		if(StringUtil.isBlank(questionFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "题目标识符为空");
			return result;
		}
		if(StringUtil.isBlank(answerFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "答题标识符为空");
			return result;
		}
		
		Map<String,Object> questionInfo = stdpBiz.questionInfo(questionFlow);
		String questionTypeId = (String) questionInfo.get("questionTypeId");
		
		String questionTypeName = QuestionTypeEnum.getNameById(questionTypeId);
		questionInfo.put("questionTypeName", questionTypeName);
		model.addAttribute("questionInfo", questionInfo);
		model.addAttribute("questionTypeId", questionTypeId);
		//加载子题目
		if(QuestionTypeEnum.Type25.getId().equals(questionTypeId)||
		   QuestionTypeEnum.Type26.getId().equals(questionTypeId)||
		   QuestionTypeEnum.Type36.getId().equals(questionTypeId)||
		   QuestionTypeEnum.Type37.getId().equals(questionTypeId)||
		   QuestionTypeEnum.Type38.getId().equals(questionTypeId)){
		   List<Map<String,Object>> questionDetails = stdpBiz.questionDetails(questionFlow);
		   model.addAttribute("questionDetails", questionDetails);
		}
		List<String> allQuestionFlowList = new ArrayList<String>();
		Map<String,String> answerDetailMap = new HashMap<String,String>();
		List<Map<String,Object>> answerDetails = stdpBiz.answerDetails(userFlow, examFlow,answerFlow);
		for(Map<String,Object> answerDetail : answerDetails){
			String temp = (String) answerDetail.get("questionFlow");
			String detailFlow = (String) answerDetail.get("detailFlow");
			String rightAnswer = (String) answerDetail.get("rightAnswer");
			String userAnswer = (String) answerDetail.get("userAnswer");
			if(allQuestionFlowList.contains(temp)==false){
				allQuestionFlowList.add(temp);
			}
			answerDetailMap.put(temp+"_"+detailFlow+"_rightAnswer", rightAnswer);
			answerDetailMap.put(temp+"_"+detailFlow+"_userAnswer", userAnswer);
		}
		if(allQuestionFlowList.size()>0){
			model.addAttribute("lastFlow", allQuestionFlowList.get(allQuestionFlowList.size()-1));
		}
		
		int curOrder = allQuestionFlowList.indexOf(questionFlow);
		model.addAttribute("curOrder", curOrder);
		
		if(curOrder+1<=allQuestionFlowList.size()-1){
			model.addAttribute("nextFlow", allQuestionFlowList.get(curOrder+1));
		}
		model.addAttribute("allCount", allQuestionFlowList.size());
		
		return result;
	}
	
	@RequestMapping(value={"/submitAnswer"},method={RequestMethod.POST})
	public String submitAnswer(String userFlow,String examFlow,String answerFlow,String questionFlow,HttpServletRequest request,Model model){
		String result = "exam/stdp/submitAnswer";
		String values = request.getParameter("values");
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(examFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "卷子标识符为空");
			return result;
		}
		if(StringUtil.isBlank(questionFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "题目标识符为空");
			return result;
		}
		if(StringUtil.isBlank(answerFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "答题标识符为空");
			return result;
		}
		if(StringUtil.isBlank(values)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "答案为空");
			return result;
		}
		return result;
	}
	
	@RequestMapping(value={"/submitWrongQuestion"},method={RequestMethod.POST})
	public String submitWrongQuestion(String userFlow,String examFlow,String questionFlow,HttpServletRequest request,Model model){
		String result = "exam/stdp/submitWrongQuestion";
		String reason = request.getParameter("reason");
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(questionFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "题目标识符为空");
			return result;
		}
		if(StringUtil.isBlank(examFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "卷子标识符为空");
			return result;
		}
		if(StringUtil.isBlank(reason)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "错题原因为空");
			return result;
		}
		return result;
	}
	
	@RequestMapping(value={"/endExam"},method={RequestMethod.GET})
	public String endExam(String userFlow,String examFlow,String answerFlow,HttpServletRequest request,Model model){
		String result = "exam/stdp/endExam";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(examFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "卷子标识符为空");
			return result;
		}
		if(StringUtil.isBlank(answerFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "答题标识符为空");
			return result;
		}
		Map<String,Object> answerInfo = stdpBiz.endExam(userFlow, examFlow,answerFlow);
		model.addAttribute("answerInfo", answerInfo);
		
		List<Map<String,Object>> answerDetails = stdpBiz.answerDetails(userFlow, examFlow,answerFlow);
		String wrongQuestionFlows = "";
		for(Map<String,Object> detail : answerDetails){
			String rightAnswer = (String) detail.get("rightAnswer");
			String userAnswer = (String) detail.get("userAnswer");
			
			String questionFlow = (String) detail.get("questionFlow");
			questionFlow = StringUtil.defaultString(questionFlow).trim();
			questionFlow = "'"+questionFlow+"'";
			if(StringUtil.isNotEquals(userAnswer, rightAnswer)){
				wrongQuestionFlows += questionFlow+",";
			}
		}
		model.addAttribute("wrongQuestionFlows", wrongQuestionFlows);
		
		model.addAttribute("answerDetails", answerDetails);
		return result;
	}
	
	@RequestMapping(value={"/historyList"},method={RequestMethod.GET})
	public String historyList(String userFlow,String searchData,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "exam/stdp/historyList";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(pageIndex==null){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "起始页为空");
			return result;
		}
		if(pageSize==null){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "页面大小为空");
			return result;
		}
		if(StringUtil.isNotBlank(searchData)){
			try {
				searchData = new String(searchData.getBytes("ISO8859-1") , "UTF-8");
			} catch (UnsupportedEncodingException e) {
                logger.error("", e);
			}
			@SuppressWarnings("unchecked")
			Map<String , String> searchMap = (Map<String, String>) JSON.parse(searchData);
			System.err.println(searchMap);
		}
		return result;
	}
	
	@RequestMapping(value={"/viewExam"},method={RequestMethod.GET})
	public String viewExam(String userFlow,String examFlow,String answerFlow,HttpServletRequest request,Model model){
		String result = "exam/stdp/viewExam";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(examFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "卷子标识符为空");
			return result;
		}
		if(StringUtil.isBlank(answerFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "答题标识符为空");
			return result;
		}
		return result;
	}
}

