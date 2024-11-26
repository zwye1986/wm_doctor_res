package com.pinde.sci.ctrl.sys;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.ISpePracticeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.model.mo.JsresSpeContrastPractice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/spePractice")
public class TrainSpePracticeController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(TrainSpePracticeController.class);

	@Autowired
	private ISpePracticeBiz spePracticeBiz;

	@RequestMapping(value = {"/main" }, method = RequestMethod.GET)
	public String main (Model model) throws Exception{
		return "sys/trainSpeContrastPractice/main";
	}
	@RequestMapping(value = {"/list" })
	public String list (Model model,HttpServletRequest request,
							String trainingTypeId,String trainingSpeId	) throws Exception{
		if(StringUtil.isNotBlank(trainingTypeId)&&StringUtil.isNotBlank(trainingSpeId))
		{
			List<JsresSpeContrastPractice> list=spePracticeBiz.getSpePracticeBySpeId(trainingSpeId);
			if(list!=null&&list.size()>0)
			{
				Map<String,Object> map=new HashMap<>();
				for(JsresSpeContrastPractice b:list)
				{
					map.put(b.getTypeId()+b.getPracticeId(),b);
				}
				model.addAttribute("spePracticeMap",map);
			}
            model.addAttribute("canCheck", GlobalConstant.FLAG_Y);
		}else{
            model.addAttribute("canCheck", GlobalConstant.FLAG_N);
		}
		return "sys/trainSpeContrastPractice/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request, JsresSpeContrastPractice speContrastPractice) {
		int result = spePracticeBiz.save(speContrastPractice);
		if (GlobalConstant.ZERO_LINE != result) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
}

