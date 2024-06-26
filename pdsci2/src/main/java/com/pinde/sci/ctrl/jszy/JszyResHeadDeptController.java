package com.pinde.sci.ctrl.jszy;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.res.IResAssessCfgBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/jszy/kzr")
public class JszyResHeadDeptController extends GeneralController{
	final static String JXCF = "1";
	final static String YN = "2";
	final static String WZBLTL = "3";
	final static String XSJZ = "4";
	final static String SWBLTL = "5";
	@Autowired
	private INoticeBiz noticeBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;	
	@Autowired
	private IUserBiz iUserBiz;
	@Autowired
	private IResDoctorProcessBiz resDoctorProcessBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	
	/**
	 * 科主任主界面
	 */
	@RequestMapping(value="/index")
	public String index(Model model,Integer currentPage,HttpServletRequest request){
		InxInfo info = new InxInfo();
		SysUser sysUser=GlobalContext.getCurrentUser();
		String deptFlow=sysUser.getDeptFlow();
		deptFlow=StringUtil.defaultIfEmpty(deptFlow, "");
		int currStudentHe=resDoctorProcessBiz.schProcessStudentDistinctQuery(deptFlow,sysUser.getUserFlow());
		int studentNum=resDoctorProcessBiz.schProcessStudentQuery(deptFlow,sysUser.getUserFlow());
		int ComingStudentNum=resDoctorProcessBiz.schProcessComingStudentQuery(deptFlow,sysUser.getUserFlow());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		model.addAttribute("ComingStudentNum",ComingStudentNum);
		model.addAttribute("currStudentHe",currStudentHe);
		model.addAttribute("studentNum",studentNum);
		model.addAttribute("infos",infos);
		return "jszy/kzr/index";
	}

}

