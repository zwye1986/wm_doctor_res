package com.pinde.sci.ctrl.res;

import com.pinde.core.model.ResOrgTime;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResOrgTimeBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.model.ResOrgTimeForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/res/signinTime")
public class ResTimeController extends GeneralController{

	private static Logger logger = LoggerFactory.getLogger(ResTimeController.class);

	@Autowired
	private IResOrgTimeBiz timebiz;

	@Autowired
	private IOrgBiz orgBiz;

	/**
	 * 设置单个签到地址
	 * @param model
	 * @return
	 */
	@RequestMapping("/oneTime")
	public String oneTime(Model model,String recordFlow){
		if(StringUtil.isNotBlank(recordFlow)) {
			ResOrgTime time = timebiz.readOrgTimeByFlow(recordFlow);
			model.addAttribute("time", time);
		}
		return "res/orgTime/oneTime";
	}

	/**
	 * 设置签到地址
	 * @param model
	 * @return
	 */
	@RequestMapping("/signinSet")
	public String signinSet(Model model){
		String orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		List<ResOrgTime> timeList = timebiz.readOrgTime(orgFlow);
		if(timeList!=null&&timeList.size()>0)
		{
			model.addAttribute("time", timeList.get(0));
		}
		model.addAttribute("timeList",timeList);
		return "res/orgTime/signinSet";
	}

	@RequestMapping("/saveSigninSet")
	@ResponseBody
	public String saveSigninSet(ResOrgTime orgTime){
		String orgFlow=orgTime.getOrgFlow();
		if(StringUtil.isBlank(orgTime.getOrgFlow()))
		{

			orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		}
		orgTime.setOrgFlow(orgFlow);
		ResOrgTime time = timebiz.readOrgOneTime(orgFlow);
		if(time!=null)
			orgTime.setRecordFlow(time.getRecordFlow());
		int result = timebiz.saveOrgTime(orgTime);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	@RequestMapping("/saveSigninSetList")
	@ResponseBody
	public String saveSigninSetList(@RequestBody ResOrgTimeForm bean){

		String orgFlow=bean.getOrgFlow();
		if(StringUtil.isBlank(bean.getOrgFlow()))
		{
			return "提交格式错误！";
		}
		if(!bean.getOrgFlow().equals(GlobalContext.getCurrentUser().getOrgFlow()))
		{
			return "当前登录人已变更，刷新页面！";
		}
		int result = timebiz.saveOrgTimes(bean);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

}

