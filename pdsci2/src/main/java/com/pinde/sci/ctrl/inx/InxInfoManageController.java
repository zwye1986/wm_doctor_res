package com.pinde.sci.ctrl.inx;


import com.pinde.core.common.enums.sys.RoleLevelEnum;
import com.pinde.core.model.InxInfo;
import com.pinde.core.model.InxInfoExt;
import com.pinde.core.model.SysRole;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IGateUserManageBiz;
import com.pinde.sci.biz.inx.IinxInfoManageBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.form.inx.InxInfoForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/inx/manage/info")
public class InxInfoManageController extends GeneralController {
    private static final Logger logger = LoggerFactory.getLogger(InxInfoManageController.class);
	@Autowired
	private IinxInfoManageBiz infoManageBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IGateUserManageBiz gateUserManageBiz;
	
	/**
	 * 显示新增界面
	 * @return
	 */
	@RequestMapping(value="/add")
	public String showAdd(Model model){
		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
		model.addAttribute("imageBaseUrl", imageBaseUrl);
		SysRole sysRole = new SysRole();
        sysRole.setWsId((String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID));
		sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		model.addAttribute("sysRoleList",sysRoleList);
		Map<String,Object> roleFlows=gateUserManageBiz.getUserRoles(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("roleFlows",roleFlows);
		return "inx/manage/editInfo";
	}
	/**
	 * 显示编辑界面
	 * @param infoFlow
	 * @return
	 */
	@RequestMapping(value="/showEdit")
	public ModelAndView showEdit(String infoFlow,String flag){
		InxInfoExt info = null;
		if(StringUtil.isNotBlank(infoFlow)){
			info = this.infoManageBiz.getExtByFlow(infoFlow);
		}
		SysRole sysRole = new SysRole();
        sysRole.setWsId((String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID));
		sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		Map<String,Object> roleFlows=gateUserManageBiz.getUserRoles(GlobalContext.getCurrentUser().getUserFlow());
		ModelAndView mav = new ModelAndView("inx/manage/editInfo");
		if("show".equals(flag)||"pass".equals(flag)){
			mav.setViewName("inx/manage/viewInfo");
			mav.addObject("flag", flag);
			mav.addObject("roleFlows",roleFlows);
		}
		mav.addObject("roleFlows",roleFlows);
		mav.addObject("sysRoleList",sysRoleList);
		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
		mav.addObject("imageBaseUrl", imageBaseUrl);
		mav.addObject("info", info);
		
		return mav;
	}
	
	/**
	 * 保存资讯
	 * @param info 资讯
	 * @return
	 */
	@RequestMapping(value="/save")
	public @ResponseBody String save(InxInfo info){
		if(StringUtil.isNotBlank(info.getInfoFlow())){//更新
			return updateInfo(info);
		}
		return addInfo(info);
	}
	/**
	 * 新增资讯
	 * @param info
	 * @return
	 */
	public  String addInfo(InxInfo info){
		if(checkInput(info)){
            info.setIsTop(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
			int saveResult = this.infoManageBiz.save(info);
            if (saveResult == com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}
	/**
	 * 修改资讯
	 * @param info
	 * @return
	 */
	public  String updateInfo(InxInfo info){
		if(checkUpdateInput(info)){
			int updateResult = this.infoManageBiz.update(info);
            if (updateResult == com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}
	
	/**
	 * 图片上传
	 * @param file 文件
	 * @return
	 */
	@RequestMapping(value="/uploadImg")
	public @ResponseBody String uploadImg(HttpServletRequest request){
		return this.infoManageBiz.uploadImg(request, "imageFile");
	}
	
	/**
	 * 校验前端输入
	 * @param info
	 * @return
	 */
	private boolean checkInput(InxInfo info){
		if(info==null){
			return false;
		}
		if(StringUtil.isEmpty(info.getInfoTitle())||StringUtil.isBlank(info.getInfoTitle())){
			return false;
		}
		if(StringUtil.isEmpty(info.getColumnId())||StringUtil.isBlank(info.getColumnId())){
			return false;
		}
        return !StringUtil.isEmpty(info.getInfoTime()) && !StringUtil.isBlank(info.getInfoTime());
    }
	/**
	 * 校验前端输入
	 * @param info
	 * @return
	 */
	private boolean checkUpdateInput(InxInfo info){
		if(info==null){
			return false;
		}
		if(StringUtil.isEmpty(info.getInfoFlow())||StringUtil.isBlank(info.getInfoFlow())){
			return false;
		}
		if(StringUtil.isEmpty(info.getInfoTitle())||StringUtil.isBlank(info.getInfoTitle())){
			return false;
		}
		if(StringUtil.isEmpty(info.getColumnId())||StringUtil.isBlank(info.getColumnId())){
			return false;
		}
        return !StringUtil.isEmpty(info.getInfoTime()) && !StringUtil.isBlank(info.getInfoTime());
    }
	/**
	 * 资讯列表
	 * @param currentPage 当前页索引
	 * @param form 参数封装
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String getInfoList(Integer currentPage,InxInfoForm form,HttpServletRequest request,Model model){
		SysRole sysRole = new SysRole();
        sysRole.setWsId((String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID));
		sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		model.addAttribute("sysRoleList",sysRoleList);
		Map<String,Object> roleFlows=gateUserManageBiz.getUserRoles(GlobalContext.getCurrentUser().getUserFlow());
		model.addAttribute("roleFlows",roleFlows);
		PageHelper.startPage(currentPage, getPageSize(request));
		if(StringUtil.isBlank(form.getRoleFlow()))
		{
            if (com.pinde.core.common.GlobalConstant.ROOT_USER_CODE.equals(GlobalContext.getCurrentUser().getUserCode())) {
				if(sysRoleList!=null&&sysRoleList.size()>0)
				{
					List<String> flows=new ArrayList<>();
					for(SysRole r:sysRoleList)
					{
						flows.add(r.getRoleFlow());
					}
					form.setRoleFlows(flows);
				}
			}else {
				if (roleFlows != null && StringUtil.isNotBlank(String.valueOf(roleFlows.get("roleName"))))
				{
					List<String> flows=Arrays.asList(String.valueOf(roleFlows.get("roleName")).split(","));
					form.setRoleFlows(flows);
				}
			}
		}
		if("inx/zsey".equals(InitConfig.getSysCfg("sys_index_url"))) {
			if (StringUtil.isBlank(form.getColumnId())) {
				List<String> ids = new ArrayList<>();
				ids.add("LM01");
				ids.add("LM02");
				ids.add("LM03");
				ids.add("LM04");
				form.setColumnIds(ids);
			}
		}
		List<InxInfo> infoList =  this.infoManageBiz.getList(form);
		model.addAttribute("infoList", infoList);

		return "inx/manage/infoList";
	}
	/**
	 * 更新记录状态
	 * @param flows
	 * @return
	 */
	@RequestMapping(value="/updateStatus")
	public @ResponseBody String updateStatus(String [] flows,String infoStatusId){
		if(flows!=null&&flows.length>0){
			List<String> infoFlows = Arrays.asList(flows);
			int delResult = this.infoManageBiz.updateInfoStatus(infoFlows,infoStatusId);
            if (delResult > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		  }
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}
	/**
	 * 删除标题图片
	 * @param infoFlow
	 * @return
	 */
	@RequestMapping(value="/deleteImg")
	public @ResponseBody String deleteTitleImg(String infoFlow){
		if(StringUtil.isNotBlank(infoFlow)){
		  int delResult = this.infoManageBiz.deleteTitleImg(infoFlow);
            if (delResult == com.pinde.core.common.GlobalConstant.ONE_LINE) {
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
		  }
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}
	/**
	 * 资讯失效
	 * @param info
	 * @return
	 */
	@RequestMapping(value="/updateRecordStatus")
	public @ResponseBody String updateRecordStatus(InxInfo info){
		int updateResult =  this.infoManageBiz.update(info);
        if (updateResult == com.pinde.core.common.GlobalConstant.ONE_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		  }
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}
	
	/**
	 * 修改资讯
	 * @param info
	 * @return
	 */
	@RequestMapping(value="/modifyInxInfo")
	public @ResponseBody String modifyInxInfo(InxInfo info){
		int result =  infoManageBiz.modifyInxInfo(info);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}
}
