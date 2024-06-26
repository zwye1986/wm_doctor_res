package com.pinde.sci.ctrl.osca;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaFormCfgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.OscaFrom;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.osca.OscaFromCfgExt;
import com.pinde.sci.model.osca.OscaFromCfgItemExt;
import com.pinde.sci.model.osca.OscaFromCfgTitleExt;
import org.apache.tools.ant.types.resources.selectors.Date;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.kie.api.definition.rule.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/osca/formCfg")
public class OscaFormController extends GeneralController{
	@Autowired
	IOscaFormCfgBiz formCfgBiz;

	@RequestMapping("/list/{roleFlag}")
	public String list(OscaFrom from, Integer currentPage, HttpServletRequest request, Model model,@PathVariable String roleFlag){
		model.addAttribute("roleFlag",roleFlag);
		if(roleFlag.equals("province")){
			from.setOrgFlow("jsst");
			PageHelper.startPage(currentPage,getPageSize(request));
			List<OscaFrom> froms = formCfgBiz.searchForm(from);
			model.addAttribute("froms",froms);
		}
		if(roleFlag.equals("hospital")){
			from.setIsReleased("Y");
			PageHelper.startPage(currentPage,getPageSize(request));
			List<OscaFrom> froms = formCfgBiz.searchHospitalFrom(from);
			model.addAttribute("froms",froms);
		}
		return "osca/formCfg/list";
	}

	@RequestMapping("/edit/{roleFlag}")
	@ResponseBody
	public int edit(OscaFrom from,String type,@PathVariable String roleFlag){
		if("hospital".equals(roleFlag)){
			from.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			from.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		}
		if("province".equals(roleFlag)){
			from.setOrgFlow("jsst");
			from.setOrgName("江苏省厅");
		}
		if("disable".equals(type)){
			from.setIsReleased("N");
		}
		if("delete".equals(type)){
			from.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		}
		if("undisable".equals(type)){
			from.setIsReleased("Y");
		}
		return formCfgBiz.editForm(from);
	}

	//新增表单
	@RequestMapping("/newForm/{roleFlag}")
	public String newForm(@PathVariable String roleFlag,String fromFlow,Model model){
		if(StringUtil.isNotBlank(fromFlow)){
			OscaFrom from = formCfgBiz.readFrom(fromFlow);
			model.addAttribute("from",from);
		}
		model.addAttribute("roleFlag",roleFlag);
		return "/osca/formCfg/newForm";
	}

	/**
	 * 复制表单页面
	 * @param fromFlow	表单主键
	 * @param fromName	表单名称
	 * @param model
	 * @return
	 */
	@RequestMapping("/copyForm")
	public String copyForm(String fromFlow,String fromName,String type,Model model){

		for (int i = 1; i < 10000; i++) {
			if (formCfgBiz.checkFromName(fromName+i).equals("Y")){
				fromName=fromName+i;
				break;
			}
		}

		model.addAttribute("fromFlow",fromFlow);
		model.addAttribute("fromName",fromName);
		model.addAttribute("type",type);
		return "/osca/formCfg/copyForm";
	}


	/**
	 * 复制表单
	 * @param fromName
	 * @param fromFlow
	 * @return
	 */
	@RequestMapping("/toCopyFrom")
	@ResponseBody
	public String toCopyFrom(String fromName,String fromFlow,String type){
		OscaFrom oscaFrom = formCfgBiz.readFrom(fromFlow);
		oscaFrom.setFromFlow("");
		oscaFrom.setFromName(fromName);
		oscaFrom.setModifyTime("");
		oscaFrom.setModifyUserFlow("");
		oscaFrom.setCreateTime(DateUtil.getCurrDateTime2());
		oscaFrom.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		//固定表单
		if (StringUtil.isNotBlank(type)){
			if (type.equals("province")){
				oscaFrom.setOrgFlow("jsst");
				oscaFrom.setOrgName("江苏省厅");
			}else if (type.equals("hospital")){
				oscaFrom.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
				oscaFrom.setOrgName(GlobalContext.getCurrentUser().getOrgName());
				oscaFrom.setIsReleased("Y");
			}
		}
		if (formCfgBiz.editForm(oscaFrom)>0){
			return GlobalConstant.OPRE_SUCCESSED;
		}else {
			return GlobalConstant.OPRE_FAIL;
		}
	}


	//打开编辑表单页面
	@RequestMapping("/editFrom")
	public String editFrom(String fromFlow,Model model,String flag) throws Exception{
		model.addAttribute("flag",flag);
		OscaFrom from = formCfgBiz.readFrom(fromFlow);
		model.addAttribute("from",from);
		if(from.getRromContent()!=null){
		Document dom = DocumentHelper.parseText(from.getRromContent());
		String titleXpath = "//title";
		List<Element> titleElementList = dom.selectNodes(titleXpath);
		if(titleElementList != null && !titleElementList.isEmpty()){
			List<OscaFromCfgTitleExt> titleFormList = new ArrayList<OscaFromCfgTitleExt>();
			int titleSum = 0;
			for(Element te :titleElementList) {
				OscaFromCfgTitleExt titleForm = new OscaFromCfgTitleExt();
				titleForm.setId(te.attributeValue("id"));
				titleForm.setName(te.attributeValue("name"));
				titleForm.setTypeName(te.attributeValue("typeName"));
				titleForm.setOrderNum(te.attributeValue("orderNum"));
				List<Element> itemElementList = te.elements("item");
				List<OscaFromCfgItemExt> itemFormList = null;
				if (itemElementList != null && !itemElementList.isEmpty()) {
					itemFormList = new ArrayList<OscaFromCfgItemExt>();
					for (Element ie : itemElementList) {
						OscaFromCfgItemExt itemForm = new OscaFromCfgItemExt();
						itemForm.setId(ie.attributeValue("id"));
						itemForm.setName(ie.element("name") == null ? "" : StringUtil.toHtml(ie.element("name").getTextTrim()));
						itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
						itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
						itemFormList.add(itemForm);
					}
				}
				titleForm.setItemList(itemFormList);
				titleFormList.add(titleForm);
				titleSum++;
			}
			model.addAttribute("titleSum", titleSum);
			model.addAttribute("titleFormList", titleFormList);
		}}
		return "/osca/formCfg/editFrom";
	}


	// 保存评分表单标题
	@RequestMapping("/saveOscaFormTitle")
	@ResponseBody
	String saveOscaFormTitle(OscaFrom from,OscaFromCfgTitleExt title,String titleSum) throws Exception{
		if(StringUtil.isNotBlank(titleSum)){
		int sum = Integer.parseInt(titleSum);
		while (sum>0){
			int result = formCfgBiz.editFromCfgTitle(from, title);
			if(GlobalConstant.ZERO_LINE  == result){
				return GlobalConstant.SAVE_FAIL;
			}
			sum--;
		}}else{
			int result = formCfgBiz.editFromCfgTitle(from, title);
			if(GlobalConstant.ZERO_LINE  == result){
				return GlobalConstant.SAVE_FAIL;
			}
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	//删除考核指标标题
	@RequestMapping(value="/deleteTitle")
	@ResponseBody
	public String deleteTitle(String cfgFlow, String id) throws Exception{
			int result = formCfgBiz.deleteTitle(cfgFlow, id);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		return GlobalConstant.DELETE_FAIL;
	}

	//保存考核指标列表
	@RequestMapping(value="/saveOscaFromItemList")
	@ResponseBody
	public String saveOscaFromItemList(@RequestBody OscaFromCfgExt form) throws Exception{
			int result = formCfgBiz.saveFromItemList(form);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		return GlobalConstant.SAVE_FAIL;
	}

	//修改考试指标
	@RequestMapping(value="/modifyItem")
	@ResponseBody
	public String modifyItem(String cfgFlow, OscaFromCfgItemExt itemForm) throws Exception{
			int result = formCfgBiz.modifyItem(cfgFlow, itemForm);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		return GlobalConstant.SAVE_FAIL;
	}

	//删除考核指标
	@RequestMapping(value="/deleteItem")
	@ResponseBody
	public String deleteItem(String cfgFlow, String id) throws Exception{
			int result = formCfgBiz.deleteItem(cfgFlow, id);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		return GlobalConstant.DELETE_FAIL;
	}
}

