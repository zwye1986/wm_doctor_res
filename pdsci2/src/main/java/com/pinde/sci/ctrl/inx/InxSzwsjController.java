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
/**
 * 
 * @author tiger
 *
 */

@Controller
@RequestMapping("/inx/szwsj")
public class InxSzwsjController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(InxSzwsjController.class);
	@Autowired
	private IInxInfoBiz inxInfoBiz;
	@Autowired
	private IinxColumnManageBiz columnBiz;
	@Autowired
	private IinxInfoManageBiz infoManageBiz;


	/**
	 * 首页
	 * @param column
	 * @param model
	 * @return
	 */
	@RequestMapping(value="",method={RequestMethod.GET})
	public String queryAllData(InxColumn column, Model model){
		return "inx/szwsj/index";
	}

	/**
	 * 加载首页资讯列表
	 * @param columnId
	 * @param jsp
	 * @param model
	 * @param endIndex
	 * @return
	 */
	@RequestMapping(value="/queryData",method={RequestMethod.GET})
	public String queryData(String columnId,String jsp, Model model,String endIndex,String imgUrl){
		InxInfoForm form = new InxInfoForm();
		form.setColumnId(columnId);
		PageHelper.startPage(1, Integer.parseInt(endIndex));
		List<InxInfo> infoList = inxInfoBiz.getList(form);
		model.addAttribute("infoList", infoList);
		if(GlobalConstant.FLAG_Y.equals(imgUrl)){
			//图片路径
			String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
			model.addAttribute("imageBaseUrl", imageBaseUrl);
		}
		return jsp;
	}	
	
	/**
	 * 加载新闻图片
	 * @param columnId
	 * @param jsp
	 * @param model
	 * @param endIndex
	 * @return
	 */
	@RequestMapping(value="/queryImgNews",method={RequestMethod.GET})
	public String queryImgNews(String columnId,String jsp,Model model,String endIndex){
		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();//图片url根路径
		model.addAttribute("imageBaseUrl", imageBaseUrl);
		InxInfoForm form = new InxInfoForm();
		form.setColumnId(columnId);
		form.setHasImage(GlobalConstant.FLAG_Y);
		PageHelper.startPage(1, Integer.parseInt(endIndex));
		List<InxInfo> imgNewsList = inxInfoBiz.getList(form);
		model.addAttribute("imgNewsList", imgNewsList);
		return jsp;
	}

	/**
	 * 查看一条资讯
	 * @param infoFlow
	 * @param model
     * @return
     */
	@RequestMapping(value="/getByInfoFlow",method={RequestMethod.GET})
	public String getByInfoFlow(String infoFlow, Model model){
		if(StringUtil.isNotBlank(infoFlow)){
			InxInfo info = inxInfoBiz.getByInfoFlow(infoFlow);
			model.addAttribute("info", info);
			//浏览量
			if(info != null){
				Long viewNum = info.getViewNum();
				if(viewNum == null){
					viewNum = Long.valueOf(0);
				}
				viewNum ++;
				InxInfo update = new InxInfo();
				update.setInfoFlow(infoFlow);
				update.setViewNum(viewNum);
				inxInfoBiz.modifyInxInfo(update);
			}
		}
		return "inx/szwsj/news";
	}
	
	
	/**
	 * 搜索
	 * @param search
	 * @param currentPage
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/doSearch",method={RequestMethod.POST})
	public ModelAndView doSearch(String search, Integer currentPage,HttpServletRequest request){
		PageHelper.startPage(currentPage, getPageSize(request));
		InxInfoForm form = new InxInfoForm();
		form.setInfoKeyword(search);
		form.setInfoTitle(search);
		form.setIsWithBlobs(GlobalConstant.FLAG_Y);
		form.setOrderByViewNum(GlobalConstant.FLAG_N);
		List<InxInfo> infoList = this.inxInfoBiz.getList(form);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("inx/szwsj/news");
		mav.addObject("infoList", infoList);
		return mav;
		
	}

	/**
	 * 加载分类
	 * @param columnId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/queryByColumnId",method={RequestMethod.GET})
	public ModelAndView queryByColumnId(String columnId, InxInfoForm form, Model model){
		ModelAndView mav = new ModelAndView();
		if(StringUtil.isNotBlank(columnId)){
			//分类
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("columnId", columnId);
			List<InxColumn> classifyList = columnBiz.searchInxColumnList(paramMap);
			mav.addObject("classifyList", classifyList);
		}
		mav.setViewName("inx/szwsj/index-news");
		
		form.setOrderByViewNum(GlobalConstant.FLAG_Y);
		PageHelper.startPage(1, 10);
		List<InxInfo> infoList = inxInfoBiz.getList(form);
		mav.addObject("infoList", infoList);
		return mav;
	}
	
	/**
	 * 加载资讯列表
	 * @param columnId
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="loadInfoList",method={RequestMethod.GET})
	public ModelAndView loadInfoList(String columnId, Integer currentPage, HttpServletRequest request, String isWithBlobs, Model model){
		ModelAndView mav = new ModelAndView();
		InxInfoForm form = new InxInfoForm();
		form.setColumnId(columnId);
		if(GlobalConstant.FLAG_Y.equals(isWithBlobs)){
			form.setIsWithBlobs(GlobalConstant.FLAG_Y);
		}
		List<InxInfo> infoList2 = new ArrayList<InxInfo>();
		PageHelper.startPage(currentPage, getPageSize(request));
		if(columnId.length() > 4){//分类
			infoList2 = this.inxInfoBiz.queryClassifyList(form);
		}else{
			infoList2 = this.inxInfoBiz.getList(form);
		}
		mav.addObject("infoList2", infoList2);
		//首条资讯内容
		/*if(infoList2 != null &&  !infoList2.isEmpty()){
			InxInfo firstInfo = inxInfoBiz.getByInfoFlow(infoList2.get(0).getInfoFlow());
			mav.addObject("firstInfo", firstInfo);
		}*/
		//图片路径
		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
		mav.addObject("imageBaseUrl", imageBaseUrl);
		mav.setViewName("inx/szwsj/infoList");
		return mav;
	}
		
}
