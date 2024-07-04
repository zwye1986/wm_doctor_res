package com.pinde.res.ctrl.mbgl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.mbgl.IMbglBiz;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

@Controller
@RequestMapping("/res/mbgl")
public class MbglAppController {
	private static Logger logger = LoggerFactory.getLogger(MbglAppController.class);

	@Autowired
	private IMbglBiz appBiz;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);

		return "res/mbgl/500";
    }
	@RequestMapping(value={"/version"},method={RequestMethod.GET})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/mbgl/version";
	}
	@RequestMapping(value={"/test"},method={RequestMethod.GET})
	public String test(HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/mbgl/test";
	}
	@RequestMapping(value={"/login"},method={RequestMethod.POST})
	public String login(String userCode,String userPasswd,HttpServletRequest request,HttpServletResponse response,Model model){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isEmpty(userCode)){
			model.addAttribute("resultId", "30101");
			model.addAttribute("resultType", "用户名为空");
			return "res/mbgl/login";
		}
		if(StringUtil.isEmpty(userPasswd)){
			model.addAttribute("resultId", "30102");
			model.addAttribute("resultType", "密码为空");
			return "res/mbgl/login";
		}
		Map<String,Object> userinfo = appBiz.findByUserCode(userCode);
		if(userinfo==null){
			model.addAttribute("resultId", "30199");
			model.addAttribute("resultType", "用户不存在");
		}else{
			boolean userFlag = false;
			if(userPasswd.equalsIgnoreCase((String) userinfo.get("mm"))){
					model.addAttribute("userinfo", userinfo);
				userFlag = true;
			}else{
				model.addAttribute("resultId", "30199");
				model.addAttribute("resultType", "用户名或密码错误");
				return "res/mbgl/login";
			}
			BigDecimal type1= (BigDecimal) userinfo.get("type");
			BigDecimal xb1= (BigDecimal) userinfo.get("xb");
			if(type1==null)
			{
				model.addAttribute("resultId", "30199");
				model.addAttribute("resultType", "用户无角色");
				return "res/mbgl/login";
			}
			String type=type1.toString();
			String xb="";
			if(xb1!=null) xb=xb1.toString();
			//类型 1：管理员 2：医生 3：患者
			if(!"1".equals(type)&&!"2".equals(type)&&!"3".equals(type))
			{
				model.addAttribute("resultId", "30199");
				model.addAttribute("resultType", "用户无角色");
				return "res/mbgl/login";
			}
			if("1".equals(xb))
			{
				model.addAttribute("userSex", "男");
			}
			if("2".equals(xb))
			{
				model.addAttribute("userSex", "女");
			}
			if("1".equals(type))
			{
				model.addAttribute("roleName", "管理员");
			}
			if("2".equals(type))
			{
				model.addAttribute("roleName", "医生");
			}
			if("3".equals(type))
			{
				model.addAttribute("roleName", "患者");
			}
			List<Map<String,Object>> users=appBiz.readOtherUsers(userCode);
			model.addAttribute("users", users);

		}
		return "res/mbgl/login";
	}

}

