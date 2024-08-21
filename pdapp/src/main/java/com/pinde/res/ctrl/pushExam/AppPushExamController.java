package com.pinde.res.ctrl.pushExam;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.pushExam.IPushExamBiz;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/res/appPushExam")
public class AppPushExamController {
	private static Logger logger = LoggerFactory.getLogger(AppPushExamController.class);


	@Autowired
	private IPushExamBiz pushExamBiz;

	@ExceptionHandler(Throwable.class)
    public String exception(Throwable e, HttpServletRequest request) {
		logger.error(this.getClass().getCanonicalName()+" some error happened",e);
		return "res/stdp/500";
    }

	@RequestMapping(value = {"/test"}, method = {RequestMethod.GET})
	public String test(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		return "res/pushExam/test";
	}
	@RequestMapping(value={"/getCfg"})
	public String version(HttpServletRequest request,HttpServletResponse response,Model model,String userFlow){
		model.addAttribute("resultId", "200");
		model.addAttribute("resultType", "交易成功");
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user=pushExamBiz.readUserByFlow(userFlow);
			String thisDay= DateUtil.getCurrDate();
			//是否每日第一天登录
			String isFirst="N";
			if(StringUtil.isBlank(user.getAppLoginTime())||!thisDay.equals(user.getAppLoginTime()))
			{
				isFirst="Y";
				user.setAppLoginTime(thisDay);
				pushExamBiz.updateUser(user);
			}
			model.addAttribute("isFirst",isFirst);
			//是否推送【i-医考】
			String app_push_iexam=pushExamBiz.getCfgCode("app_push_iexam");
			model.addAttribute("IsPush",app_push_iexam);

			//推送图片地址：
			String app_push_iexam_url=pushExamBiz.getCfgCode("app_push_iexam_url");
			model.addAttribute("PushImgUrl",app_push_iexam_url);

			//安卓【i-医考】下载链接：
			String android_iexam_download_url=pushExamBiz.getCfgCode("android_iexam_download_url");
			model.addAttribute("AndroidDownUrl",android_iexam_download_url);

			//IOS【i-医考】下载链接：
			String ios_iexam_download_url=pushExamBiz.getCfgCode("ios_iexam_download_url");
			model.addAttribute("IosDownUrl",ios_iexam_download_url);

			//推送类型：
			String app_push_iexam_type=pushExamBiz.getCfgCode("app_push_iexam_type");
			model.addAttribute("PushType",app_push_iexam_type);
		}
		return "res/pushExam/getCfg";
	}

	
}

