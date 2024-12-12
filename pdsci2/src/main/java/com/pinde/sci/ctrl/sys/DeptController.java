package com.pinde.sci.ctrl.sys;

import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysOrg;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/sys/dept")
public class DeptController extends GeneralController{
	
	private static Logger logger=LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	private IDeptBiz deptBiz;
	
	@Autowired
	private IOrgBiz orgBiz;
	
	@RequestMapping(value={"/edit"},method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value="deptFlow",required=false)String deptFlow){
		ModelAndView mav=new ModelAndView("sys/dept/edit");
		if(StringUtil.isNotBlank(deptFlow)){
			SysDept sysDept=deptBiz.readSysDept(deptFlow);
			mav.addObject("sysDept",sysDept);
		}
		SysOrg sysOrg=new SysOrg();
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<SysOrg> orgList=orgBiz.searchOrg(sysOrg);
		mav.addObject("orgList",orgList);
		return mav;
	}
	
	@RequestMapping(value = "/list/{deptListScope}",method={RequestMethod.POST,RequestMethod.GET})
	public String list(@PathVariable String deptListScope,SysDept dept,Model model,HttpServletRequest request){
        setSessionAttribute(com.pinde.core.common.GlobalConstant.DEPT_LIST_SCOPE, deptListScope);
        if (com.pinde.core.common.GlobalConstant.DEPT_LIST_LOCAL.equals(deptListScope)) {
			dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			if(StringUtil.isNotBlank(dept.getOrgFlow())){
				List<SysDept> deptList=deptBiz.searchDept(dept);
				model.addAttribute("deptList",deptList);				
			}			
		}
        if (com.pinde.core.common.GlobalConstant.DEPT_LIST_GLOBAL.equals(deptListScope)) {
			if(StringUtil.isNotBlank(dept.getOrgFlow())){
				List<SysDept> deptList=deptBiz.searchDept(dept);
				model.addAttribute("deptList",deptList);			
			}			
		}
		return "sys/dept/list";
	}
	
	@RequestMapping(value={"/save"},method=RequestMethod.POST)
	public @ResponseBody  String save(SysDept dept,HttpServletRequest request){
		deptBiz.saveDept2(dept,request);
		InitConfig._loadDept(request.getServletContext());
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public @ResponseBody String delete(SysDept dept){
//		dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		deptBiz.saveDept(dept);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	@RequestMapping(value="/saveOrder",method=RequestMethod.POST)
	@ResponseBody
	public String saveOrder(String [] deptFlow,Model model,HttpServletRequest request) throws Exception{
		deptBiz.saveOrder(deptFlow);
		InitConfig._loadDept(request.getServletContext());
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		
	}

	/**
	 * @Department：研发部
	 * @Description 根据基地flow查询科室列表
	 * @Author fengxf
	 * @Date 2020/10/15
	 */
	@ResponseBody
	@RequestMapping(value={"/searchSysDeptList"},method=RequestMethod.GET)
	public List<SysDept> searchSysDeptListByOrgFlow(String orgFlow){
		return deptBiz.searchDeptByOrg(orgFlow);
	}
}
