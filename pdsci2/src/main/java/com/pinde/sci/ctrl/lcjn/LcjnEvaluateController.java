package com.pinde.sci.ctrl.lcjn;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.lcjn.ILcjnEvaluateBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/lcjn/evaluate")
public class LcjnEvaluateController extends GeneralController {
	@Autowired
	private ILcjnEvaluateBiz evaluateBiz;

	@RequestMapping("/coursEvaluation")
	public String coursEvaluation(String courseFlow, Model model, Integer currentPage, HttpServletRequest request) {
 		PageHelper.startPage(currentPage, getPageSize(request));
		List<LcjnCourseEvaluate> courseEvaluates = evaluateBiz.searchByCourseFlow(courseFlow);
		model.addAttribute("courseEvaluates", courseEvaluates);
		Map<String, String> scoreMap = new HashMap<>();
		if (courseEvaluates != null && courseEvaluates.size() > 0) {
			for (LcjnCourseEvaluate courseEvaluate : courseEvaluates) {
				String evaluateFlow = courseEvaluate.getEvaluateFlow();
				List<LcjnCourseEvaluateDetail> courseEvaluateDetails = evaluateBiz.searchDetailByEvaluateFlow(evaluateFlow);
				if (courseEvaluateDetails != null && courseEvaluateDetails.size() > 0) {
					for (LcjnCourseEvaluateDetail courseEvaluateDetail : courseEvaluateDetails) {
						String dictId = courseEvaluateDetail.getDictId();
						if (StringUtil.isNotBlank(dictId)) {
							scoreMap.put(dictId + evaluateFlow, courseEvaluateDetail.getEvalScore());
						}
					}
				}
			}
		}
		model.addAttribute("scoreMap", scoreMap);
		return "/lcjn/evaluate/coursEvaluation";
	}

	@RequestMapping("/teacherEvalutionList")
	public String teacherEvalutionList(String courseFlow,Model model){
		List<LcjnCourseTea> courseTeas = evaluateBiz.searchTeaByCourseFlow(courseFlow);
		model.addAttribute("courseTeas",courseTeas);
		return "/lcjn/evaluate/teacherEvalutionList";
	}

	@RequestMapping("/teacherEvaluation")
	public String teacherEvaluation(String courseFlow, String teacherFLow, Model model, Integer currentPage, HttpServletRequest request) {
		PageHelper.startPage(currentPage, getPageSize(request));
		List<LcjnTeaEvaluate> teaEvaluates = evaluateBiz.searchByTeacher(courseFlow,teacherFLow);
		model.addAttribute("teaEvaluates", teaEvaluates);
		Map<String, String> scoreMap = new HashMap<>();
		if (teaEvaluates != null && teaEvaluates.size() > 0) {
			for (LcjnTeaEvaluate teaEvaluate : teaEvaluates) {
				String evaluateFlow = teaEvaluate.getTeaEvaluateFlow();
				List<LcjnTeaEvaluateDetail> teaEvaluateDetails = evaluateBiz.searchTeaDetailByEvaluateFlow(evaluateFlow);
				if (teaEvaluateDetails != null && teaEvaluateDetails.size() > 0) {
					for (LcjnTeaEvaluateDetail teaEvaluateDetail : teaEvaluateDetails) {
						String dictId = teaEvaluateDetail.getDictId();
						if (StringUtil.isNotBlank(dictId)) {
							scoreMap.put(dictId + evaluateFlow, teaEvaluateDetail.getEvalScore());
						}
					}
				}
			}
		}
		model.addAttribute("scoreMap", scoreMap);
		return "/lcjn/evaluate/teacherEvaluation";
	}
}