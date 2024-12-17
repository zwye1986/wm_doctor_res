package com.pinde.sci.ctrl.res;

import com.pinde.core.model.ResBookStudyRecord;
import com.pinde.core.model.SysUser;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResBookStudyRecordBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.core.model.ResDoctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 
 * @author tiger
 *
 */
@Controller
@RequestMapping("/res/bookStudyRecord")
public class ResBookStudyRecordController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResBookStudyRecordController.class);
	

	@Autowired
	private IResBookStudyRecordBiz bookStudyRecordBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	/**
	 * 跟师指导老师简况
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/list")
	public String list(Integer currentPage, HttpServletRequest request,String doctorFlow,String role, Model model) throws Exception{
		if (currentPage == null) {
			currentPage = 1;
		}
		String teacherFlow="";
		SysUser currUser = GlobalContext.getCurrentUser();
		//role 为空 说明是当前学员登录
		if(StringUtil.isBlank(role))
		{
			doctorFlow=currUser.getUserFlow();
		}else{
			//role 不为空 说明是老师或管理员
			if(role.equals("teacher"))
			{
				teacherFlow=currUser.getUserFlow();
			}
		}

		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResBookStudyRecord> list=bookStudyRecordBiz.getBookStudyRecords(doctorFlow,teacherFlow);
		model.addAttribute("list",list);
		return "res/disciple/bookRecordMain";
	}
	/**
	 * 新增页面
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/editRecord")
	public String editRecord(String recordFlow, Model model) throws Exception{
		ResBookStudyRecord record=bookStudyRecordBiz.getBookStudyRecord(recordFlow);
		model.addAttribute("record",record);
		SysUser currUser = GlobalContext.getCurrentUser();
		model.addAttribute("user",currUser);
		ResDoctor doctor=doctorBiz.readDoctor(currUser.getUserFlow());
		model.addAttribute("doctor",doctor);
		return "res/disciple/editBookRecord";
	}
	/**
	 * 保存
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/saveBookRecord")
	@ResponseBody
	public String saveBookRecord(ResBookStudyRecord record, Model model) throws Exception{
		if(StringUtil.isNotBlank(record.getStudyActionId())&&record.getStudyActionId().equals("JIZHONG")) {
			record.setStudyActionName("集中");
		}
		if(StringUtil.isNotBlank(record.getStudyActionId())&&record.getStudyActionId().equals("ZIXUE")) {
			record.setStudyActionName("自学");
		}
		record.setRecordYear(record.getStudyStartTime().substring(0,4));
		int count=bookStudyRecordBiz.savaRecord(record);
		if(count==1)
		{
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}
}
