package com.pinde.sci.ctrl.sys;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.ISpePracticeBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.model.mo.JsresSpeContrastPractice;
import com.pinde.sci.model.mo.SysOrg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/sys/orgMonthCfg")
public class OrgMonthCfgController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(OrgMonthCfgController.class);

	@Autowired
	private ISpePracticeBiz spePracticeBiz;

	@Autowired
	private IOrgBiz orgBiz;

	@RequestMapping(value = {"/main" }, method = RequestMethod.GET)
	public String main (Model model) throws Exception{
		SysOrg sysOrg=new SysOrg();
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		List<SysOrg> allSysOrgList = orgBiz.searchOrgs(sysOrg,"");
		model.addAttribute("allSysOrgList", allSysOrgList);
		return "sys/orgMonthCfg/main";
	}
	@RequestMapping(value = {"/list" })
	public String list (Model model, HttpServletRequest request,
						SysOrg sysOrg, Integer currentPage) throws Exception{
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<SysOrg> sysOrgList = orgBiz.searchOrgs(sysOrg,"");
		model.addAttribute("sysOrgList", sysOrgList);
		return "sys/orgMonthCfg/list";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request, JsresSpeContrastPractice speContrastPractice) {
		int result = spePracticeBiz.save(speContrastPractice);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
}

