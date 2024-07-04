package com.pinde.res.ctrl.njmu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.shiro.web.session.HttpServletSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/res/njmu")
public class NjmuTestController{    
	
	private static Logger logger = LoggerFactory.getLogger(NjmuTestController.class);
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request,Model model) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		model.addAttribute("resultId", "500");
		model.addAttribute("resultType", "后台出错了");
		return "res/njmu/500";
    }

	@RequestMapping(value={"/","","/test"},method={RequestMethod.GET})
	public String test(){
		return "res/njmu/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String deptFlow,String cataFlow,String dataFlow,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("deptFlow", deptFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		return "/res/njmu/test";
	}
	 
}

