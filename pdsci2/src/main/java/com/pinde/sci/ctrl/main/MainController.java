package com.pinde.sci.ctrl.main;

import com.pinde.core.config.WorkStation;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController extends GeneralController {
	
	private static Logger logger = LoggerFactory.getLogger(MainController.class);

	@Autowired
	private IResDoctorBiz resDoctorBiz;
	/**
	 * 显示主页面，未选定工作站和模块
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/main" })
	public String main(HttpServletRequest request,Model model) {
		//清空当前选中的工作站和模块
		setSessionAttribute(GlobalConstant.CURRENT_WS_ID, null);
		setSessionAttribute(GlobalConstant.CURRENT_MODULE_ID, null);
		setSessionAttribute("currModuleView", null);	
		setSessionAttribute("mainFrameSrc", null);
		
		SysUser user = GlobalContext.getCurrentUser();
		List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST_BACKUP);
		if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
			if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() == 1) {
				return "redirect:/main/"+currUserWorkStationIdList.get(0);
			}
		}
		
		List<WorkStation> workStationList = (List<WorkStation>) request.getServletContext().getAttribute("workStationList");
		if (GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
			model.addAttribute("currUserWorkStationList", workStationList);
		} else {
			if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() >0) {
				//重新组织当前用户有权限的工作站
				List<WorkStation> currUserWorkStationList = new ArrayList<WorkStation>();
				if (workStationList != null && workStationList.size() > 0) {
					for (WorkStation station:workStationList) {
						if (currUserWorkStationIdList.indexOf(station.getWorkStationId())>-1) {
							currUserWorkStationList.add(station);
						}
					}
				}
				model.addAttribute("currUserWorkStationList", currUserWorkStationList);
			}
		}
		
		return "station";
	}
	
	/**
	 * 显示主页面，未选定工作站和模块
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/main/select" })
	public String select(HttpServletRequest request,Model model) {
		//清空当前选中的工作站和模块
//		setSessionAttribute(GlobalConstant.CURRENT_WS_ID, null);
//		setSessionAttribute(GlobalConstant.CURRENT_MODULE_ID, null);
//		setSessionAttribute("currModuleView", null);	
//		setSessionAttribute("mainFrameSrc", null);
		
		SysUser user = GlobalContext.getCurrentUser();
		List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST_BACKUP);
		List<WorkStation> workStationList = (List<WorkStation>) request.getServletContext().getAttribute("workStationList");
		if (GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())) {
			model.addAttribute("currUserWorkStationList", workStationList);
		} else {
			if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() >0) {
				//重新组织当前用户有权限的工作站
				List<WorkStation> currUserWorkStationList = new ArrayList<WorkStation>();
				if (workStationList != null && workStationList.size() > 0) {
					for (WorkStation station:workStationList) {
						if (currUserWorkStationIdList.indexOf(station.getWorkStationId())>-1) {
							currUserWorkStationList.add(station);
						}
					}
				}
				model.addAttribute("currUserWorkStationList", currUserWorkStationList);
			}
		}
		return "select";
	}
	
	/**
	 * 显示主页面
	 * @param workStationId 选中的工作ID
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/main/{workStationId}" })
	public String main(@PathVariable String workStationId,HttpServletRequest request,Model model) {
		setSessionAttribute(GlobalConstant.CURRENT_WS_ID, workStationId);
		setSessionAttribute(GlobalConstant.CURRENT_MODULE_ID, null);
		setSessionAttribute("currModuleView", null);	
		setSessionAttribute("mainFrameSrc", null);
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, null);
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, null);

		//当学员类型为研究生时，才显示【学员选科】功能
		SysUser user = GlobalContext.getCurrentUser();
		if(user!=null)
		{
			ResDoctor resDoctor=resDoctorBiz.readDoctor(user.getUserFlow());
			model.addAttribute("resDoctor",resDoctor);
		}
		String cfg13= InitConfig.getSysCfg("jswjw_"+user.getOrgFlow()+"_P013");
		model.addAttribute("cfg13",cfg13);
		return "main";
	}

	/**
	 * 验证用户信息是否齐全(是否需要补全用户信息)
	 * @return
     */
	private boolean checkUser(SysUser user){
		if(StringUtil.isBlank(user.getIdNo())){
			return true;
		}else if(StringUtil.isBlank(user.getSexId())){
			return true;
		}else if(StringUtil.isBlank(user.getUserPhone())){
			return true;
		}else if(StringUtil.isBlank(user.getUserEmail())){
			return true;
		}else if(StringUtil.isBlank(user.getUserBirthday())){
			return true;
		}else if(StringUtil.isBlank(user.getEducationId())){
			return true;
		}else if(StringUtil.isBlank(user.getDegreeId())){
			return true;
		}else if(StringUtil.isBlank(user.getTitleId())){
			return true;
		}else if(StringUtil.isBlank(user.getAccountNo())){
			return true;
		}else if(StringUtil.isBlank(user.getWorkCode())){
			return true;
		}else if(StringUtil.isBlank(user.getPostId())){
			return true;
		}else if(StringUtil.isBlank(user.getDeptFlow())){
			return true;
		}
		return false;
	}

	/**
	 * 显示主页面
	 * @param workStationId 选中的工作ID
	 * @param moduleId 选中的模块ID
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value = {"/main/{workStationId}/{moduleId}" })
	public String main(@PathVariable String workStationId,@PathVariable String moduleId,HttpServletRequest request,Model model,String menuId) {
		//换模块后，主页设置为空
		if(!moduleId.equals(getSessionAttribute(GlobalConstant.CURRENT_MODULE_ID))){
//			setSessionAttribute("currModuleView", null);				
		}
		setSessionAttribute(GlobalConstant.CURRENT_WS_ID, workStationId);
		setSessionAttribute(GlobalConstant.CURRENT_MODULE_ID, moduleId);
		setSessionAttribute("mainFrameSrc", null);	
		setSessionAttribute(GlobalConstant.PROJ_LIST_SCOPE, null);
		setSessionAttribute(GlobalConstant.PROJ_CATE_SCOPE, null);

		//如果是学籍工作站且配置的南医大项目LOGO,根据要求切换LOGO文字
		ServletContext application = request.getServletContext();
		Map<String,String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
		String logoImg = sysCfgMap.get("sys_login_img");
		if("nfykdx".equals(logoImg)){
			if("cmis".equals(workStationId)){
				model.addAttribute("extAddress","nfykdx_xjgl.png");
			}
			if("res".equals(workStationId)){
				model.addAttribute("extAddress","nfykdx_gcgl.png");
			}
		}



		//当学员类型为研究生时，才显示【学员选科】功能
		SysUser user = GlobalContext.getCurrentUser();
		if(user!=null)
		{
			ResDoctor resDoctor=resDoctorBiz.readDoctor(user.getUserFlow());
			model.addAttribute("resDoctor",resDoctor);
		}
		//如果是科研工作站
		if("srm".equals(workStationId)){
			if(checkUser(user)){
				model.addAttribute("finishUserInfoFlag","Y");
			}
		}
		String cfg13= InitConfig.getSysCfg("jswjw_"+user.getOrgFlow()+"_P013");
		model.addAttribute("cfg13",cfg13);
		return "main";
	}
	
}
