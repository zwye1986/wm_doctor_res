package com.pinde.sci.ctrl.inx;

import com.pinde.sci.common.GeneralController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 江苏学习平台控制层
 */
@Controller
@RequestMapping("/inx/jsedu")
public class InxJsEduController extends GeneralController{
	/**
	 * 首页
	 * @return
     */
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(){
		return "inx/jsedu/index";
	}

}