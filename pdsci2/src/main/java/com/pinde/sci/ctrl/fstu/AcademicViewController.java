package com.pinde.sci.ctrl.fstu;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IAcademicBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.FstuAcademicActivity;
import com.pinde.sci.model.mo.SysUser;
import com.pinde.sci.model.mo.SysUserDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fstu/academicView")
public class AcademicViewController extends GeneralController{
	@Autowired
	private IAcademicBiz academicBiz;
	@Autowired
	private IDeptBiz deptBiz;

	@RequestMapping(value = "/list/{role}")
	public String list(FstuAcademicActivity academicActivity,String startingTime,String endingTime,@PathVariable String role,
					   Model model,HttpServletRequest request,Integer currentPage){
		model.addAttribute("role",role);
		List<String> deptFlows = new ArrayList<>();
		if("deptMaster".equals(role)){
			SysUser currUser = GlobalContext.getCurrentUser();
			String userFlow = currUser.getUserFlow();
			String dept = currUser.getDeptFlow();
			if(StringUtil.isNotBlank(dept)){
				deptFlows.add(dept);
			}
			List<SysUserDept> userDepts = deptBiz.searchByUserFlow(userFlow);
			if(userDepts!=null&&userDepts.size()>0){
				for(SysUserDept userDept:userDepts){
					String deptFlow = userDept.getDeptFlow();
					deptFlows.add(deptFlow);
				}
			}
			if(deptFlows.size()==0){
				academicActivity.setApplyUserFlow(userFlow);
			}
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<FstuAcademicActivity> academicActivities = academicBiz.search(academicActivity,startingTime,endingTime,deptFlows);
		model.addAttribute("academicActivities",academicActivities);
		//图表数据
		String thisYear=Integer.parseInt(DateUtil.getYear())+10+"";
		String beforeYear= Integer.parseInt(thisYear)-20+"" ;
		model.addAttribute("thisYear",thisYear);
		model.addAttribute("beforeYear",beforeYear);
		Map<String,Map<String,Integer>> resultMap = new HashMap<>();
		Integer year = Integer.parseInt(thisYear);
		Integer byear = Integer.parseInt(beforeYear);
		for(int i=byear;i<=year;i++){
			String y = String.valueOf(i);
			Map<String,Integer> feeMap = academicBiz.calculation(y,deptFlows);
			resultMap.put(y,feeMap);
		}
		model.addAttribute("resultMap",resultMap);
		return "fstu/acdm/list";
	}

	@RequestMapping(value = "/export/{role}")
	public void export(FstuAcademicActivity academicActivity, @PathVariable String role, String startingTime, String endingTime, HttpServletResponse response) throws Exception {
		List<String> deptFlows = new ArrayList<>();
		if("deptMaster".equals(role)){
			SysUser currUser = GlobalContext.getCurrentUser();
			String userFlow = currUser.getUserFlow();
			String dept = currUser.getDeptFlow();
			if(StringUtil.isNotBlank(dept)){
				deptFlows.add(dept);
			}
			List<SysUserDept> userDepts = deptBiz.searchByUserFlow(userFlow);
			if(userDepts!=null&&userDepts.size()>0){
				for(SysUserDept userDept:userDepts){
					String deptFlow = userDept.getDeptFlow();
					deptFlows.add(deptFlow);
				}
			}
			if(deptFlows.size()==0){
				academicActivity.setApplyUserFlow(userFlow);
			}
		}
		List<FstuAcademicActivity> academicActivities = academicBiz.search(academicActivity,startingTime,endingTime,deptFlows);
		String headLines[] = {"学术项目统计清单"};
		String[] titles = new String[]{
				"applyUserName:申请人",
				"applyTime:申请时间",
				"applyDeptName:申请部门",
				"academicName:学术名称",
				"academicTypeName:学术类型",
				"placeCity:学术地点(市)",
				"beginTime:学术开始时间",
				"endTime:学术结束时间",
				"takeDay:天数",
				"auditStatusName:审核状态",
				"expenseStatusName:报销状态",
				"holdUnit:主办单位",
				"meetingFee:会议费",
				"trafficFee:交通费",
				"foodFee:食宿费",
				"otherFee:其他费",
				"costumeFee:服装费",
				"materialFee:资料费",
				"subsidyFee:补贴费",
				"sumFee:总量费用"
		};
		String fileName = "学术项目统计清单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleWithHeadlin(headLines, titles, academicActivities, response.getOutputStream());
	}
}