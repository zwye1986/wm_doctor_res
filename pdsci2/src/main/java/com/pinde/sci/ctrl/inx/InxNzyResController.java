package com.pinde.sci.ctrl.inx;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.inx.IInxBiz;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.dao.base.SysLogMapper;
import com.pinde.sci.model.mo.InxInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/inx/nzyres")
public class InxNzyResController extends GeneralController{
	@Autowired
	private INoticeBiz noticeBiz;
	
	/**
	 * 显示登陆界面
	 * @return
	 */
	@RequestMapping("")
	public String index(Model model){
		InxInfo info = new InxInfo();
		PageHelper.startPage(1,2);
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("infos",infos);
		return "inx/nzyres/login";
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
