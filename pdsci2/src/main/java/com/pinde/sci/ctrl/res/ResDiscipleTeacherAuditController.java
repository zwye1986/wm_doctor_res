package com.pinde.sci.ctrl.res;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.res.IResDiscipleInfoBiz;
import com.pinde.sci.biz.res.IResDiscipleTeacherInfoBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResGraduationAssessmentBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.common.enums.DiscipleStatusEnum;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.res.ResDoctorExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 
 * @author tiger
 *
 */
@Controller
@RequestMapping("/res/discipleTeacherAudit")
public class ResDiscipleTeacherAuditController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResDiscipleTeacherAuditController.class);

	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IResDiscipleInfoBiz discipleInfoBiz;
	@Autowired
	private IResDiscipleTeacherInfoBiz teacherInfoBiz;
	@Autowired
	private IResGraduationAssessmentBiz graduationAssessmentBiz;
	/**
	 * 学员列表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/graduationList")
	public String main(Integer currentPage, HttpServletRequest request, Model model, String sessionNumber, String speId,
					   String doctorName, String doctorCategoryId, String [] datas) throws Exception{
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		SysUser currUser = GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		param.put("teacherFlow",currUser.getUserFlow());
		param.put("sessionNumber",sessionNumber);
		param.put("speId",speId);
		param.put("doctorCategoryId",doctorCategoryId);
		param.put("docName",doctorName);
		param.put("auditId",DiscipleStatusEnum.Submit.getId());
		param.put("docTypeList",docTypeList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResDoctorExt> students=doctorBiz.searchDocByDiscipleTea(param);
		model.addAttribute("list",students);
 		return "res/disciple/teacher/graduationList";
	}
	/**
	 * 学习情况查询学员列表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/studentList")
	public String studentList(Integer currentPage, HttpServletRequest request, Model model, String sessionNumber,
							  String speId, String doctorName, String doctorCategoryId,String [] datas) throws Exception{
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		SysUser currUser = GlobalContext.getCurrentUser();
		Map<String,Object> param=new HashMap<>();
		param.put("teacherFlow",currUser.getUserFlow());
		param.put("sessionNumber",sessionNumber);
		param.put("speId",speId);
		param.put("doctorCategoryId", doctorCategoryId);
		param.put("docName",doctorName);
		param.put("docTypeList",docTypeList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> resultList=new ArrayList<>();
		resultList=doctorBiz.teaQueryDocDisciple(param);
		model.addAttribute("list",resultList);
 		return "res/disciple/teacher/studentList";
	}
}
