package com.pinde.sci.ctrl.jsres;

import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/jsres/km")
public class JsResKmDeptController extends GeneralController{
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
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResInprocessInfoBiz resInprocessInfoBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IOrgBiz orgBiz;
	/**
	 * 科秘主界面
	 */
	@RequestMapping(value="/index")
	public String index(Model model,Integer currentPage,HttpServletRequest request){
		SysUser sysUser=GlobalContext.getCurrentUser();
		String deptFlow=sysUser.getDeptFlow();
		model.addAttribute("deptFlow",deptFlow);
		InxInfo info = new InxInfo();
		/*SysUser sysUser=GlobalContext.getCurrentUser();
		String deptFlow=sysUser.getDeptFlow();
		deptFlow=StringUtil.defaultIfEmpty(deptFlow, "");
		int currStudentHe=resDoctorProcessBiz.schProcessStudentDistinctQuery(deptFlow,sysUser.getUserFlow());
		int studentNum=resDoctorProcessBiz.schProcessStudentQuery(deptFlow,sysUser.getUserFlow());
		int ComingStudentNum=resDoctorProcessBiz.schProcessComingStudentQuery(deptFlow,sysUser.getUserFlow());*/
		PageHelper.startPage(currentPage,getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
	/*	model.addAttribute("ComingStudentNum",ComingStudentNum);
		model.addAttribute("currStudentHe",currStudentHe);
		model.addAttribute("studentNum",studentNum);*/
		model.addAttribute("infos",infos);
        GlobalContext.setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE, com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SECRETARY);
		return "jsres/km/index";
	}

	@RequestMapping(value="/indexInfo")
	@ResponseBody
	public Map<String,Object> indexInfo(){
		HashMap<String, Object> map = new HashMap<>();
		SysUser sysUser=GlobalContext.getCurrentUser();
		String deptFlow=sysUser.getDeptFlow();
		deptFlow=StringUtil.defaultIfEmpty(deptFlow, "");
		int currStudentHe=resDoctorProcessBiz.schProcessStudentDistinctQuery(deptFlow,sysUser.getUserFlow());
		int studentNum=resDoctorProcessBiz.schProcessStudentQuery(deptFlow,sysUser.getUserFlow());
		int ComingStudentNum=resDoctorProcessBiz.schProcessComingStudentQuery(deptFlow,sysUser.getUserFlow());
		map.put("currStudentHe",currStudentHe);
		map.put("studentNum",studentNum);
		map.put("ComingStudentNum",ComingStudentNum);
		return map;
	}
}

