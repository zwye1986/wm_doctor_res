package com.pinde.sci.ctrl.inx;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IInxInfoBiz;
import com.pinde.sci.biz.inx.IinxColumnManageBiz;
import com.pinde.sci.biz.inx.IinxInfoManageBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.form.inx.InxInfoForm;
import com.pinde.sci.model.mo.InxColumn;
import com.pinde.sci.model.mo.InxInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inx/cdedudoor")
public class InxCdEduDoorController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(InxCdEduDoorController.class);
	@Autowired
	private IInxInfoBiz inxInfoBiz;
	@Autowired
	private IinxColumnManageBiz columnBiz;
	@Autowired
	private IinxInfoManageBiz infoManageBiz;
	
	/**
	 * 加载首页资讯列表
	 * @param columnId
	 * @param jsp
	 * @param model
	 * @param endIndex
	 * @return
	 */
	@RequestMapping(value="/queryData",method={RequestMethod.GET})
	public String queryData(String columnId,String jsp, Model model,String endIndex){
		InxInfoForm form = new InxInfoForm();
		form.setColumnId(columnId);
		form.setIsWithBlobs(GlobalConstant.FLAG_Y);
		PageHelper.startPage(1, Integer.parseInt(endIndex));
		List<InxInfo> infoList = inxInfoBiz.getList(form);
		model.addAttribute("columnId", columnId);
		model.addAttribute("infoList", infoList);
		//访问图片路径
		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
		model.addAttribute("imageBaseUrl", imageBaseUrl);
		return jsp;
	}
	
	/**
	 * 首页
	 * @param column
	 * @param model
	 * @return
	 */
	@RequestMapping(value="",method={RequestMethod.GET})
	public String queryAllData(InxColumn column, Model model){
		return "inx/cdedudoor/index";
	}
	
	
	/**
	 * 查看一条资讯
	 * @param infoFlow
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getByInfoFlow",method={RequestMethod.GET})
	public String getByInfoFlow(String infoFlow, Model model, InxInfoForm form){
		if(StringUtil.isNotBlank(infoFlow)){
			InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
			model.addAttribute("info", info);
			form.setColumnId(info.getColumnId());
			List<InxInfo> infoList = inxInfoBiz.getList(form);
			for (int i = 0; i < infoList.size(); i++) {
				String flow = infoList.get(i).getInfoFlow();
				if(flow.equals(infoFlow)){
					if(i >=1 ){
						model.addAttribute("beforeInfo", infoList.get(i-1));
					}
					if(i < infoList.size()-1){
						model.addAttribute("afterInfo", infoList.get(i+1));
					}
				}
			}
			//浏览量
			if(info != null){
				Long viewNum = info.getViewNum()==null?Long.valueOf(0):info.getViewNum();
				viewNum ++;
				InxInfo update = new InxInfo();
				update.setInfoFlow(infoFlow);
				update.setViewNum(viewNum);
				inxInfoBiz.modifyInxInfo(update);
			}
		}
		return "inx/cdedudoor/news";
	}

	/**
	 * 加载分类资讯
	 * @param columnId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryByColumnId",method={RequestMethod.GET,RequestMethod.POST})
	public ModelAndView queryByColumnId(String columnId,  Integer currentPage, InxInfoForm form, Model model){
		ModelAndView mav = new ModelAndView();
		mav.addObject("columnName", columnBiz.getById(columnId).getColumnName());
		mav.setViewName("inx/cdedudoor/index-navNews");
		form.setColumnId(columnId);
		PageHelper.startPage(currentPage==null?1:currentPage, 15);
		List<InxInfo> infoList = inxInfoBiz.getList(form);
		mav.addObject("infoList", infoList);
		return mav;
	}
}
