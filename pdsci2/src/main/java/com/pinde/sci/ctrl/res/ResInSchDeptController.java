package com.pinde.sci.ctrl.res;

import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/res/inSchDept")
public class ResInSchDeptController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResInSchDeptController.class);



	/**
	 *  待入科学员
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showList/{roleFlag}")
	public String showView(@PathVariable String roleFlag,String schDeptFlow,Model model,String doctorName,
						   Integer currentPage,HttpServletRequest request){
		model.addAttribute("roleFlag", roleFlag);
		SysUser user = GlobalContext.getCurrentUser();
		if(GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag))//科主任
		{

		}else if (GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)){//管理员

		}
		return "res/teacher/view";
	}
}
