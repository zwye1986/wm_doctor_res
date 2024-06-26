package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.model.mo.InxInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/inx/jsdxyxy")
public class InxJsdxyxyResController extends GeneralController{
	
	@Autowired
	private INoticeBiz noticeBiz;
	
	@RequestMapping(value="",method={RequestMethod.GET})
	public String index(Model model){
		InxInfo info = new InxInfo();
		PageHelper.startPage(1,2);
		List<InxInfo> infos = this.noticeBiz.searchNotice(info);
		model.addAttribute("infos",infos);
		return "inx/jsdxyxy/index";
	}
	
	@RequestMapping(value="/noticeView")
	public String message(Model model,String infoFlow) throws Exception{
		model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
		return "inx/message";
	}
	
	@RequestMapping("/noticelist")
	public String noticeList(Integer currentPage ,HttpServletRequest request, Model model){
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,10);
		
		InxInfo info = new InxInfo();
		List<InxInfo> infos = this.noticeBiz.searchNotice(info);
		model.addAttribute("infos",infos);
		return "inx/noticeList";
	}
}
