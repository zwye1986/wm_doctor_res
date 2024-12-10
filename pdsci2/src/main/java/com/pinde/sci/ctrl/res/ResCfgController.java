package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.AchScoreEnum;
import com.pinde.core.common.enums.ResAssessScoreTypeEnum;
import com.pinde.core.common.enums.ResAssessTypeEnum;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResAssessCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResPowerCfgBiz;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.res.ResAssessCfgForm;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResDoctorExt;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
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
@RequestMapping("/res/cfg")
public class ResCfgController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResCfgController.class);
	
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
	private IResPowerCfgBiz resPowerCfgBiz;



	@RequestMapping(value = "/list")
	public String list(ResAssessCfg resAssessCfg, Model model, Integer currentPage, HttpServletRequest request) throws DocumentException, IllegalAccessException, IntrospectionException, InvocationTargetException {
		List<ResAssessTypeEnum> resAssessTypeEnums = new ArrayList<>();
		for (ResAssessTypeEnum value : ResAssessTypeEnum.values()) {
			if (value.name().contains("360")) {
				resAssessTypeEnums.add(value);
			}
		}
		model.addAttribute("resAssessTypeEnums", resAssessTypeEnums);
		List<AchScoreEnum> achScoreEnums = new ArrayList<>();
		for (AchScoreEnum value : AchScoreEnum.values()) {
			achScoreEnums.add(value);
		}
		model.addAttribute("achScoreEnums", achScoreEnums);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResAssessCfg> resAssessCfgs = assessCfgBiz.searchAssessCfgList(resAssessCfg);
		model.addAttribute("resAssessCfgs",resAssessCfgs);
		model.addAttribute("currentPage",currentPage);
		Map<String,Integer> resAssessCfgMaps = new HashMap<>();
		for (ResAssessCfg assessCfg : resAssessCfgs) {
			Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if (titleElementList != null && !titleElementList.isEmpty()) {
				List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
				for (Element te : titleElementList) {
					ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					titleForm.setEvalTypeId(te.attributeValue("evalTypeId") == null ? "" : te.attributeValue("evalTypeId"));
					titleForm.setEvalTypeName(te.attributeValue("evalTypeName") == null ? "" : te.attributeValue("evalTypeName"));
					List<Element> itemElementList = te.elements("item");
					List<ResAssessCfgItemForm> itemFormList = null;
					if (itemElementList != null && !itemElementList.isEmpty()) {
						itemFormList = new ArrayList<ResAssessCfgItemForm>();
						int sum = 0;
						for (Element ie : itemElementList) {
							ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
							itemForm.setId(ie.attributeValue("id"));
							itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
							String score = ie.element("score") == null ? "" : ie.element("score").getTextTrim();
							itemForm.setScore(score);
							itemFormList.add(itemForm);
							if (StringUtil.isNotBlank(score)) {
								sum += Integer.parseInt(score);
							}
							titleForm.setScore(String.valueOf(sum));
						}
					}
					titleForm.setItemList(itemFormList);
					titleFormList.add(titleForm);
				}

				int count = 0;
				if (titleFormList.size() > 0) {
					for (ResAssessCfgTitleForm resAssessCfgTitleForm : titleFormList) {
						if (resAssessCfgTitleForm.getItemList() != null) {
							count += resAssessCfgTitleForm.getItemList().size();
						}
					}
				}
				resAssessCfgMaps.put(assessCfg.getCfgFlow(), count);
			}
		}
		model.addAttribute("resAssessCfgMaps", resAssessCfgMaps);
		return "res/cfg/list";
	}

	@RequestMapping("/editFormStatus")
	@ResponseBody
	public String editFormStatus(ResAssessCfg resAssessCfg){
		if (resAssessCfg != null) {
			int result;
			if (AchScoreEnum.Enable.getId().equals(resAssessCfg.getFormStatusId())) {
				resAssessCfg.setFormStatusId(AchScoreEnum.Disable.getId());
				resAssessCfg.setFormStatusName(AchScoreEnum.Disable.getName());
				result = assessCfgBiz.updateByPrimaryKeySelective(resAssessCfg);
			} else {
				resAssessCfg.setFormStatusId(AchScoreEnum.Enable.getId());
				resAssessCfg.setFormStatusName(AchScoreEnum.Enable.getName());
				List<ResAssessCfg> resAssessCfgList = assessCfgBiz.selectByExample(resAssessCfg);
				if (resAssessCfgList.size() == 1) {
					return "已启用一种该类型的表单，不可启用多种";
				} else {
					result = assessCfgBiz.updateByPrimaryKeySelective(resAssessCfg);
				}
			}
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}


	/**
	 * 跳转至考核指标配置
	 *
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/assessCfg")
	public String assessCfg(String cfgFlow, Model model) throws Exception {
		SysUser currUser = GlobalContext.getCurrentUser();
		if (currUser != null && StringUtil.isNotBlank(cfgFlow)) {
			ResAssessCfg assessCfg = assessCfgBiz.readResAssessCfg(cfgFlow);
			Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if (titleElementList != null && !titleElementList.isEmpty()) {
				List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
				for (Element te : titleElementList) {
					ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
					titleForm.setType(te.attributeValue("type"));
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					titleForm.setRow(te.attributeValue("row"));
					titleForm.setEvalTypeId(te.attributeValue("evalTypeId") == null ? "" : te.attributeValue("evalTypeId"));
					titleForm.setEvalTypeName(te.attributeValue("evalTypeName") == null ? "" : te.attributeValue("evalTypeName"));
					List<Element> itemElementList = te.elements("item");
					List<ResAssessCfgItemForm> itemFormList = null;
					if (itemElementList != null && !itemElementList.isEmpty()) {
						itemFormList = new ArrayList<ResAssessCfgItemForm>();
						int sum = 0;
						for (Element ie : itemElementList) {
							ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
							itemForm.setId(ie.attributeValue("id"));
							itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
							itemForm.setType(ie.element("type") == null ? "" : ie.element("type").getTextTrim());
							itemForm.setRow(ie.element("row") == null ? "" : ie.element("row").getTextTrim());
							String score = ie.element("score") == null ? "" : ie.element("score").getTextTrim();
							itemForm.setScore(score);
							itemFormList.add(itemForm);
							if (StringUtil.isNotBlank(score)) {
								sum += Integer.parseInt(score);
							}
							titleForm.setScore(String.valueOf(sum));
						}
					}
					titleForm.setItemList(itemFormList);
					titleFormList.add(titleForm);
				}
				model.addAttribute("titleFormList", titleFormList);
			}
			model.addAttribute("assessCfg", assessCfg);
		}
		return "res/cfg/assessCfg";
	}

	@RequestMapping("/insertForm")
	public String insertForm(Model model) {
		List<ResAssessTypeEnum> resAssessTypeEnums = new ArrayList<>();
		for (ResAssessTypeEnum value : ResAssessTypeEnum.values()) {
			if (value.name().contains("360")) {
				resAssessTypeEnums.add(value);
			}
		}
		model.addAttribute("resAssessTypeEnums", resAssessTypeEnums);
		return "res/cfg/insertForm";
	}

	@RequestMapping("/saveFrom")
	@ResponseBody
	public String insertFrom(ResAssessCfg resAssessCfg) {
		Document dom = DocumentHelper.createDocument();
		Element root = dom.addElement("assessCfg");
		resAssessCfg.setAssessTypeId(ResAssessScoreTypeEnum.Percentile.getId());
		resAssessCfg.setAssessTypeName(ResAssessScoreTypeEnum.Percentile.getName());
		resAssessCfg.setCfgBigValue(dom.asXML());
		resAssessCfg.setFormStatusId(AchScoreEnum.Disable.getId());
		resAssessCfg.setFormStatusName(AchScoreEnum.Disable.getName());
		resAssessCfg.setCfgCodeName(ResAssessTypeEnum.getNameById(resAssessCfg.getCfgCodeId()));
		int result = assessCfgBiz.saveAssessCfg(resAssessCfg);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}


	/**
	 * 保存考核指标标题
	 * @param assessCfg
	 * @param titleForm
	 * @return
	 * @throws Exception
     */
	@RequestMapping(value="/saveAssessTitle")
	@ResponseBody
	public String saveAssessTitle(ResAssessCfg assessCfg, ResAssessCfgTitleForm titleForm) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = assessCfgBiz.editAssessCfgTitle(assessCfg, titleForm);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 删除考核指标标题
	 * @param cfgFlow
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteTitle")
	@ResponseBody
	public String deleteTitle(String cfgFlow, String id) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = assessCfgBiz.deleteTitle(cfgFlow, id);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}
	
	//*********************************************************************
	
	/**
	 * 保存考核指标列表
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveAssessItemList")
	@ResponseBody
	public String saveAssessItemList(@RequestBody ResAssessCfgForm form) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = assessCfgBiz.saveAssessItemList(form);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 修改考试指标
	 * @param cfgFlow
	 * @param itemForm
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/modifyItem")
	@ResponseBody
	public String modifyItem(String cfgFlow, ResAssessCfgItemForm itemForm) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = assessCfgBiz.modifyItem(cfgFlow, itemForm);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 删除考试指标
	 * @param cfgFlow
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/deleteItem")
	@ResponseBody
	public String deleteItem(String cfgFlow, String id) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser != null){
			int result = assessCfgBiz.deleteItem(cfgFlow, id);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}


	/**
	 * 学员赋权页面
	 * @param rde
	 * @param model
	 * @param req
	 * @param currentPage
     * @return
     */
	@RequestMapping(value="/personPermission")
	public String personPermission(ResDoctorExt rde, Model model, HttpServletRequest req,Integer currentPage){
		PageHelper.startPage(currentPage,getPageSize(req));
		List<ResDoctorExt> docs = doctorBiz.searchDocUser(rde, "");
		model.addAttribute("doctorExtList",docs);
		return "res/cfg/personPermission";
	}

	@RequestMapping(value="/cfgSwitchUseCount")
	@ResponseBody
	public Map<String,Integer> cfgSwitchUseCount(String[] cfgCodePrefix,String orgFlow){
		Map<String,Integer> results = null;
		if(cfgCodePrefix!=null && cfgCodePrefix.length>0){
			results = new HashMap<String, Integer>();
			Map<String,Object> paramMap = new HashMap<String,Object>();
			for(String code : cfgCodePrefix){
				paramMap.put("cfgCodePre",code+orgFlow);
//				paramMap.put("cfgCodeSuf",code);
				int useCount = cfgBiz.getOpenSwitchCount(paramMap);
				results.put(code,useCount);
			}
		}
		return results;
	}

	@RequestMapping(value="/deptFormCfgPage")
	public String deptFormCfgPage(String orgFlow,String rotationFlow,Model model){
        List<SysOrg> orgList = orgBiz.searchSysOrg();
        model.addAttribute("orgList",orgList);

        if(StringUtil.isNotBlank(orgFlow)){
            List<SchDept> deptList = schDeptBiz.searchSchDeptList(orgFlow);
            model.addAttribute("deptList",deptList);

            if(StringUtil.isNotBlank(rotationFlow)){
                Map<String,Object> paramMap = new HashMap<String,Object>();
                String cfgCodePre = "form_dept_cfg_"+rotationFlow+orgFlow;
                paramMap.put("cfgCodePre",cfgCodePre);
                List<SysCfg> cfgs = cfgBiz.searchByPreAndSuf(paramMap);
                if(cfgs!=null && !cfgs.isEmpty()){
                    Map<String,String> cfgMap = new HashMap<String, String>();
                    for(SysCfg cfg : cfgs){
                        cfgMap.put(cfg.getCfgCode(),cfg.getCfgValue());
                    }
                    model.addAttribute("cfgMap",cfgMap);
                }
            }
        }
		return "sch/template/deptFormEdit";
	}
	/**
	 * 教学活动配置
	 */
	@RequestMapping(value="/activityCfg")
	public String activityCfg(){
		return "res/cfg/activityCfg";
	}

	@RequestMapping(value="/saveActivityCfg",method= RequestMethod.POST)
	@ResponseBody
	public String saveActivityCfg(HttpServletRequest request){
		String [] cfgCodes =request.getParameterValues("cfgCode");
		if(cfgCodes!=null){
			List<ResPowerCfg> sysCfgList = new ArrayList<ResPowerCfg>();
			for(String cfgCode : cfgCodes){
				String sysCfgValue=request.getParameter(cfgCode);
				String sysCfgDesc=request.getParameter(cfgCode+"_desc");
				ResPowerCfg cfg=new ResPowerCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(sysCfgValue);
				cfg.setCfgDesc(sysCfgDesc);
                cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				sysCfgList.add(cfg);
			}
			resPowerCfgBiz.saveList(sysCfgList);
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
}
