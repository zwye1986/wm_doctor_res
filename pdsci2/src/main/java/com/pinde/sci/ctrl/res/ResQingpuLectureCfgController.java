package com.pinde.sci.ctrl.res;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResAssessCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResQingpuLectureCfgBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.res.QingpuLectureCfgExt;
import com.pinde.sci.form.res.QingpuLectureCfgItemExt;
import com.pinde.sci.form.res.QingpuLectureCfgTitleExt;
import com.pinde.sci.model.mo.QingpuLectureEvalCfg;
import com.pinde.sci.model.mo.SysUser;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/res/qingpuLectureCfg")
public class ResQingpuLectureCfgController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResQingpuLectureCfgController.class);
	
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private ICfgBiz cfgBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private ISchDeptBiz schDeptBiz;
	@Autowired
	private IResQingpuLectureCfgBiz lectureCfgBiz;

	/**
	 * 跳转至考核指标配置
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/lectureCfg")
	public String lectureCfg(String typeId,Model model) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null && StringUtil.isNotBlank(typeId)){
			QingpuLectureEvalCfg searchCfg = new QingpuLectureEvalCfg();
			searchCfg.setTypeId(typeId);
			searchCfg.setOrgFlow(currUser.getOrgFlow());
			List<QingpuLectureEvalCfg> lectureEvalCfgList = lectureCfgBiz.searchLectureEvalCfgList(searchCfg);
			if(lectureEvalCfgList != null && !lectureEvalCfgList.isEmpty()){
				QingpuLectureEvalCfg lectureEvalCfg = lectureEvalCfgList.get(0);
				Document dom = DocumentHelper.parseText(lectureEvalCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<QingpuLectureCfgTitleExt> titleFormList = new ArrayList<QingpuLectureCfgTitleExt>();
					for(Element te :titleElementList){
						QingpuLectureCfgTitleExt titleForm = new QingpuLectureCfgTitleExt();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						titleForm.setOrder(te.attributeValue("order"));
						List<Element> itemElementList = te.elements("item");
						List<QingpuLectureCfgItemExt> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<QingpuLectureCfgItemExt>();
							int sum=0;
							for(Element ie : itemElementList){
								QingpuLectureCfgItemExt itemForm = new QingpuLectureCfgItemExt();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								String score=ie.element("score") == null ? "" : ie.element("score").getTextTrim();
								itemForm.setScore(score);
								itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
								itemFormList.add(itemForm);
								if(StringUtil.isNotBlank(score))
								{
									sum+=Double.parseDouble(score);
								}
							}
							titleForm.setScore(String.valueOf(sum));
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
				model.addAttribute("lectureEvalCfg", lectureEvalCfg);
			}
		}
		return "res/cfg/qingpuLectureCfg";
	}

	/**
	 * 保存考核指标标题
	 * @param lectureEvalCfg
	 * @param titleForm
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="/saveLectureCfgTitle")
	@ResponseBody
	public String saveLectureCfgTitle(QingpuLectureEvalCfg lectureEvalCfg, QingpuLectureCfgTitleExt titleForm) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = lectureCfgBiz.editLectureEvalCfgTitle(lectureEvalCfg, titleForm);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 删除考核指标标题
	 * @param recordFlow
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteTitle")
	@ResponseBody
	public String deleteTitle(String recordFlow, String id) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = lectureCfgBiz.deleteTitle(recordFlow, id);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}
	
	//*********************************************************************
	
	/**
	 * 保存考核指标列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveLectureCfgItemList")
	@ResponseBody
	public String saveLectureCfgItemList(@RequestBody QingpuLectureCfgExt form) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = lectureCfgBiz.saveLectureCfgItemList(form);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 修改考试指标
	 * @param recordFlow
	 * @param itemForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/modifyItem")
	@ResponseBody
	public String modifyItem(String recordFlow, QingpuLectureCfgItemExt itemForm) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = lectureCfgBiz.modifyItem(recordFlow, itemForm);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 删除考试指标
	 * @param recordFlow
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteItem")
	@ResponseBody
	public String deleteItem(String recordFlow, String id) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = lectureCfgBiz.deleteItem(recordFlow, id);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}
}
