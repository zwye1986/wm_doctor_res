package com.pinde.res.ctrl.stdp;

import com.pinde.core.commom.enums.ResultEnum;
import com.pinde.core.util.StringUtil;
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
@RequestMapping("/res/stdp/teacher")
public class StdpTeacherController{    
	private static Logger logger = LoggerFactory.getLogger(StdpTeacherController.class);
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/stdp/500";
    }
	
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/stdp/teacher/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String doctorFlow,String cataFlow,String dataFlow,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("doctorFlow", doctorFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		return "/res/stdp/teacher/test";
	}
	
	@RequestMapping(value={"/doctorList"},method={RequestMethod.GET})
	public String doctorList(String userFlow,String searchData, Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/stdp/teacher/doctorList";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isEmpty(userFlow)){
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
		
		return result;
	}
	
	@RequestMapping(value={"/funcList"},method={RequestMethod.GET})
	public String funcList(String userFlow,String doctorFlow,HttpServletRequest request,Model model){
		String result = "res/stdp/teacher/funcList";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isEmpty(doctorFlow)){
			model.addAttribute("resultId", "30202");
			model.addAttribute("resultType", "学员标识符为空");
			return result;
		}
		return result;
	}
	
}

