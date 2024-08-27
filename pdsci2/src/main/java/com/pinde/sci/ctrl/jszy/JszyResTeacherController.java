package com.pinde.sci.ctrl.jszy;

import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.inx.INoticeBiz;
import com.pinde.sci.biz.jszy.IJszyResDoctorBiz;
import com.pinde.sci.biz.res.IResAttendanceBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorProcessBiz;
import com.pinde.sci.biz.res.IResRecBiz;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.sci.biz.sch.ISchRotationDeptBiz;
import com.pinde.sci.biz.sch.ISchRotationGroupBiz;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/jszy/teacher")
public class JszyResTeacherController extends GeneralController{

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
	private ISchRotationDeptBiz iSchRotationDeptBiz;
	@Autowired
	private IResRecBiz iResRecBiz;
	@Autowired
	private ISchRotationGroupBiz iSchRotationGroupBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IJszyResDoctorBiz iJsResDoctorBiz;
	@Autowired
	private IResDoctorProcessBiz iResDoctorProcessBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IResAttendanceBiz resAttendanceBiz;
	@Autowired
	private IOrgBiz orgBiz;
	/**
	 * 带教老师主界面
	 */
	@RequestMapping(value="/index")
	public String index(Model model,Integer currentPage,HttpServletRequest request){
		InxInfo info = new InxInfo();
		PageHelper.startPage(currentPage,getPageSize(request));
		List<InxInfo> infos = this.noticeBiz.findNotice(info);
		SysUser sysUser=GlobalContext.getCurrentUser();

		int noAuditNumber=iResDoctorProcessBiz.schDoctorSchProcessWaitingExamineQuery(sysUser.getUserFlow());
		int currStudentHe=iResDoctorProcessBiz.schDoctorSchProcessDistinctQuery(sysUser.getUserFlow());
		int studentNum=iResDoctorProcessBiz.schDoctorSchProcessTeacherQuery(sysUser.getUserFlow());
		/*schArrangeResultBiz.roundRobinStudents(sysUser.getDeptFlow());*/
		model.addAttribute("studentNum",studentNum);
		model.addAttribute("dShenHe",currStudentHe);
		model.addAttribute("noAuditNumber",noAuditNumber);
		model.addAttribute("infos",infos);
		return "jszy/teacher/index";
	}

        
}
