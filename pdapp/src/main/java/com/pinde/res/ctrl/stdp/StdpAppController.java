package com.pinde.res.ctrl.stdp;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.enums.ResultEnum;
import com.pinde.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Controller
@RequestMapping("/res/stdp")
public class StdpAppController{    
	private static Logger logger = LoggerFactory.getLogger(StdpAppController.class);
	
	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/stdp/500";
    }
	
	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/stdp/version";
	}
	
	
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(){
		return "res/stdp/test";
	}

	@RequestMapping(value={"/remember"},method={RequestMethod.GET})
	public String remember(String userFlow,String roleId,String deptFlow,String doctorFlow,String cataFlow,String dataFlow,String funcTypeId,String funcFlow,HttpServletRequest request){
		HttpSession session = request.getSession();
		session.setAttribute("userFlow", userFlow);
		session.setAttribute("roleId", roleId);
		session.setAttribute("deptFlow", deptFlow);
		session.setAttribute("doctorFlow", doctorFlow);
		session.setAttribute("cataFlow", cataFlow);
		session.setAttribute("dataFlow", dataFlow);
		session.setAttribute("funcTypeId", funcTypeId);
		session.setAttribute("funcFlow", funcFlow);
		return "/res/stdp/test";
	}

	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,HttpServletRequest request,HttpServletResponse response,Model model){
		String result = "res/stdp/login";
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
		
		//
		if(!"student".equals(userCode)&&!"teacher".equals(userCode)){
			model.addAttribute("resultId",ResultEnum.ErrorUser.getId());
			model.addAttribute("resultType", ResultEnum.ErrorUser.getName());
			return result;
		}
		return result;
	}
	
	@RequestMapping(value={"/notice"},method={RequestMethod.GET})
	public String notice(String userFlow,String roleId,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/stdp/notice";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}
		return result;
	}
	
	@RequestMapping(value={"/noticeCount"},method={RequestMethod.GET})
	public String noticeCount(String userFlow,String roleId,HttpServletRequest request,Model model){
		String result = "res/stdp/noticeCount";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}
		return result;
	}
	
	@RequestMapping(value={"/qrCode"},method={RequestMethod.POST})
	public String signin(String userFlow,String roleId,String deptFlow,String doctorFlow,String codeInfo,String funcTypeId,String funcFlow,HttpServletRequest request,Model model){
		String result = "res/stdp/qrCode";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}
		if("Student".equals(roleId)&&StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "科室标识符为空");
			return result;
		}
		if("Teacher".equals(roleId)&&StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "学员标识符为空");
			return result;
		}
		if(StringUtil.isBlank(funcTypeId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能类型为空");
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能标识为空");
			return result;
		}
		return result;
	}
	
	//只有funcTypeId为dataInputNN时使用
	@RequestMapping(value={"/cataList"},method={RequestMethod.GET})
	public String cataList(String userFlow,String roleId,String deptFlow,String doctorFlow,String funcTypeId,String funcFlow,String searchData,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/stdp/cataList";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}
		if("Student".equals(roleId)&&StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "科室标识符为空");
			return result;
		}
		if("Teacher".equals(roleId)&&StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "学员标识符为空");
			return result;
		}
		if(StringUtil.isNotEquals(funcTypeId, "dataInputNN")){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能类型不匹配");
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能标识为空");
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
	
	@RequestMapping(value={"/dataList"},method={RequestMethod.GET})
	public String dataList(String userFlow,String roleId,String deptFlow,String doctorFlow,String cataFlow,String funcTypeId,String funcFlow,String searchData,Integer pageIndex,Integer pageSize,HttpServletRequest request,Model model){
		String result = "res/stdp/dataList";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}
		if("Student".equals(roleId)&&StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "科室标识符为空");
			return result;
		}
		if("Teacher".equals(roleId)&&StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "学员标识符为空");
			return result;
		}
		if(StringUtil.isBlank(funcTypeId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能类型为空");
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能标识为空");
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
		if(StringUtil.isEquals(funcTypeId, "dataInputNN")&&StringUtil.isBlank(cataFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "分类标示符为空");
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
	
	@RequestMapping(value={"/viewData"},method={RequestMethod.GET})
	public String viewData(String userFlow,String roleId,String deptFlow,String doctorFlow,String funcTypeId,String funcFlow,String dataFlow,HttpServletRequest request,Model model){
		String result = "res/stdp/viewData";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}
		if("Student".equals(roleId)&&StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "科室标识符为空");
			return result;
		}
		if("Teacher".equals(roleId)&&StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "学员标识符为空");
			return result;
		}
		if(StringUtil.isBlank(funcTypeId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能类型为空");
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能标识为空");
			return result;
		}
		return result;
	}
	
	@RequestMapping(value={"/saveData"},method={RequestMethod.POST})
	public String saveData(String userFlow,String roleId,String deptFlow,String doctorFlow,String funcTypeId,String funcFlow,String dataFlow,HttpServletRequest request,Model model){
		String result = "res/stdp/saveData";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}
		if("Student".equals(roleId)&&StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "科室标识符为空");
			return result;
		}
		if("Teacher".equals(roleId)&&StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "学员标识符为空");
			return result;
		}
		if(StringUtil.isBlank(funcTypeId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能类型为空");
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能标识为空");
			return result;
		}
		return result;
	}
	
	@RequestMapping(value={"/delData"},method={RequestMethod.GET})
	public String delData(String userFlow,String roleId,String deptFlow,String doctorFlow,String funcTypeId,String funcFlow,String dataFlow,HttpServletRequest request,Model model){
		String result = "res/stdp/delData";
		model.addAttribute("resultId", ResultEnum.Success.getId());
		model.addAttribute("resultType", ResultEnum.Success.getName());
		if(StringUtil.isBlank(userFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户标识符为空");
			return result;
		}
		if(StringUtil.isBlank(roleId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "用户角色为空");
			return result;
		}

		if("Student".equals(roleId)&&StringUtil.isBlank(deptFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "科室标识符为空");
			return result;
		}
		if("Teacher".equals(roleId)&&StringUtil.isBlank(doctorFlow)){
			model.addAttribute("resultId", "30203");
			model.addAttribute("resultType", "学员标识符为空");
			return result;
		}
		if(StringUtil.isBlank(funcTypeId)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能类型为空");
			return result;
		}
		if(StringUtil.isBlank(funcFlow)){
			model.addAttribute("resultId", "30201");
			model.addAttribute("resultType", "功能标识为空");
			return result;
		}
		return result;
	}
	
}

