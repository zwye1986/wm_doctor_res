package com.pinde.sci.ctrl.inx;

import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.common.GeneralController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/inx/shfxyy")
public class InxShfxyyController extends GeneralController{

	@Autowired
	private IInxBiz inxBiz;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(Model model){
		return "inx/shfxyy/index";
	}
}
