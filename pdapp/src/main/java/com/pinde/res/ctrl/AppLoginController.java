package com.pinde.res.ctrl;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.memcached.MemCached;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author tiger
 *
 */
@Controller
@RequestMapping("/app")
public class AppLoginController {

	private Object checkLogin(String userCode, String userPasswd){
		String loginErrorMessage = "";
		 //登录码不能为空
		if (StringUtil.isBlank(userCode)){
			loginErrorMessage = "用户名不能为空";
			return loginErrorMessage;
		}
		//密码不能为空
		if (StringUtil.isBlank(userPasswd)){
			loginErrorMessage = SpringUtil.getMessage("userPasswd.isNull");
			return loginErrorMessage;
		}
		if(!"njpdxx".equals(userCode))
		{
			return "用户名错误";
		}
		if(!"njpdxx123".equals(userPasswd))
		{
			return "密码错误";
		}
		return true;
	}

	/**
	 * 登录
	 */
	@RequestMapping(value="/login")
	public String login(String userCode, String userPasswd,String errorLoginPage,Model model,HttpServletRequest request){

        Object obj = checkLogin(userCode, userPasswd);
        if(obj instanceof String){
        	String loginErrorMessage = (String) obj;
        	model.addAttribute("loginErrorMessage", loginErrorMessage);
			return "/login";
        }else{
			return "/index";
		}
	}
	/**
	 * 获取token令牌信息
	 */
	@RequestMapping(value="/getToken")
	public String getToken(String userFlow, Model model,HttpServletRequest request){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isBlank(userFlow))
		{
			model.addAttribute("resultId", "200");
			model.addAttribute("resultType", "交易成功");
			return "/getToken";
		}

		String tokenAccess="";
		tokenAccess= TokenUtil.getToken(userFlow);
		if(StringUtil.isBlank(tokenAccess))
		{
			tokenAccess=TokenUtil.createToken(userFlow);
		}
		model.addAttribute("tokenAccess",tokenAccess);
		return "/getToken";
	}
	@RequestMapping(value="/token")
	public String token(String resultId,String resultType, Model model,HttpServletRequest request){
		model.addAttribute("resultId", resultId);
		model.addAttribute("resultType", resultType);
		return "/token";
	}
}
