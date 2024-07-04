package com.pinde.res.ctrl.zsyk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/res/zsyk")
public class ZsykTestController {

private static Logger logger = LoggerFactory.getLogger(ZsykTestController.class);
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request,Model model) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		model.addAttribute("resultId", "500");
		model.addAttribute("resultType", "后台出错了");
		return "res/zsyk/500";
    }

	@RequestMapping(value={"/student/test"},method={RequestMethod.GET})
	public String test(){
		return "res/zsyk/student/test";
	}

	@RequestMapping(value={"/student/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String schDeptFlow,String cataFlow,String funcTypeId , String funcFlow , String dataFlow , HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("schDeptFlow", schDeptFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/zsyk/student/test";
	}
	
	@RequestMapping(value={"/teacher/test"},method={RequestMethod.GET})
	public String teachertest(){
		return "res/zsyk/teacher/test";
	}

	@RequestMapping(value={"/teacher/remember"},method={RequestMethod.GET})
	public String teacherremember(String userFlow,String doctorFlow,String cataFlow,String funcTypeId , String funcFlow , String dataFlow , HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("doctorFlow", doctorFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/zsyk/teacher/test";
	}
	@RequestMapping(value={"/secretarie/test"},method={RequestMethod.GET})
	public String secretarietest(){
		return "res/zsyk/secretarie/test";
	}

	@RequestMapping(value={"/secretarie/remember"},method={RequestMethod.GET})
	public String secretarieremember(String userFlow,String inProcessType,String indexType,String roleId , String funcFlow , String dataFlow , HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("inProcessType", inProcessType);
		session.setAttribute("indexType", indexType);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("roleId", roleId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/zsyk/secretarie/test";
	}
}
