package com.pinde.sci.ctrl.res;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResActivityTargetBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.TeachingActivityTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tiger
 *
 *
 */
@Controller
@RequestMapping("/res/activityTarget")
public class ResActivityTargetController extends GeneralController {
	@Autowired
	private IJsResActivityTargetBiz activityTargeBiz;

	/**
	 * 教学活动指标
	 */
	@RequestMapping(value="/manageMain")
	public String manageMain(Model model,Integer currentPage, HttpServletRequest request){
		return "res/activity/target/main";
	}
	@RequestMapping(value="/add")
	public String add(Model model, HttpServletRequest request,String targetFlow){

		TeachingActivityTarget target=activityTargeBiz.readByFlow(targetFlow);
		model.addAttribute("target",target);
		return "res/activity/target/add";
	}
	@RequestMapping(value="/list")
	public String list(Model model,Integer currentPage,String targetName, HttpServletRequest request){
		SysUser curUser=GlobalContext.getCurrentUser();
		Map<String,String> param=new HashMap<>();
		param.put("orgFlow",curUser.getOrgFlow());
		if(StringUtil.isNotBlank(targetName))
		{
			targetName=targetName.trim();
		}
		param.put("targetName",targetName);
		if(currentPage==null) currentPage=1;
		Integer currentPageSize=getPageSize(request);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("currentPageSize",currentPageSize);
		PageHelper.startPage(currentPage,currentPageSize);
		List<TeachingActivityTarget> targets=activityTargeBiz.list(param);
		model.addAttribute("targets",targets);
		return "res/activity/target/list";
	}
	@RequestMapping(value="/saveAdd")
	@ResponseBody
	public String saveAdd(Model model,String targetName,String targetFlow){
		if(StringUtil.isBlank(targetName))
		{
			return "请填写指标名称！";
		}
		SysUser curUser=GlobalContext.getCurrentUser();
		TeachingActivityTarget target=activityTargeBiz.readByName(curUser.getOrgFlow(),targetName.trim());
		if(StringUtil.isNotBlank(targetFlow))
		{
			if (target != null&&!target.getTargetFlow().equals(targetFlow)) {
				return "指标名称已存在，请修改后保存！！";
			}
			target= new TeachingActivityTarget();
			target.setTargetFlow(targetFlow);
			target.setTargetName(targetName.trim());
			target.setOrgFlow(curUser.getOrgFlow());
            target.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			int c = activityTargeBiz.saveTarget(target);
			if (c == 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
			}
		}else {
			if (target != null) {
				return "指标名称已存在，请修改后保存！！";
			}
			TeachingActivityTarget add = new TeachingActivityTarget();
			add.setTargetName(targetName.trim());
			add.setOrgFlow(curUser.getOrgFlow());
            add.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			int c = activityTargeBiz.saveTarget(add);
			if (c == 0) {
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/delTarget")
	@ResponseBody
	public String delTarget(Model model,String targetFlow){
		if(StringUtil.isBlank(targetFlow))
		{
			return "评价指标流水号为空";
		}
		int c=activityTargeBiz.delTarget(targetFlow);
		if(c==0)
		{
            return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
		}
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
	}
}
