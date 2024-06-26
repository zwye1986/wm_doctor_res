package com.pinde.sci.ctrl.res;

import com.pinde.core.util.SpringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.srm.IPubProjBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.PubProjMapper;
import com.pinde.sci.dao.base.PubProjRecMapper;
import com.pinde.sci.model.mo.*;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * 公告维护
 * @author shenzhen
 *
 */
@Controller
@RequestMapping("/res/notice")
public class ResNoticeController extends GeneralController{

	@Autowired
	private INoticeBiz noticeBiz;
	
	@ResponseBody
	@RequestMapping("/save")
	public String saveNotice(String infoFlow , String title , String roleFlow , String content){
		InxInfo info = new InxInfo();
		info.setInfoFlow(infoFlow);
		info.setInfoTitle(title);
		info.setInfoContent(content);
		info.setRoleFlow(roleFlow);
		noticeBiz.save(info);
		return GlobalConstant.OPERATE_SUCCESSED;
	}

	@RequestMapping("/findnoticebyflow")
	@ResponseBody
	public Object findNoticeByFlow(String infoFlow){
		return this.noticeBiz.findNoticByFlow(infoFlow);
	}
	
	@RequestMapping(value="/view")
	public String message(Model model,String infoFlow) throws Exception{
		model.addAttribute("msg", noticeBiz.findNoticByFlow(infoFlow));
		return "res/notice/recruitMessage";
	}
	
	@ResponseBody
	@RequestMapping("/delnotice")
	public String delNotice(String infoFlow){
		this.noticeBiz.delNotice(infoFlow);
		return GlobalConstant.DELETE_SUCCESSED;
	}
}
