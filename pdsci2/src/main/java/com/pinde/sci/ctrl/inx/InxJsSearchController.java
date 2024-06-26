package com.pinde.sci.ctrl.inx;

import com.pinde.sci.common.GeneralController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 江苏证书查询控制层
 */
@Controller
@RequestMapping("/inx/search")
public class InxJsSearchController extends GeneralController{
	/**
	 * 首页
	 * @return
     */
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(Model model){
		return "inx/jsres/search/index";
	}

}