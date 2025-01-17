package com.pinde.sci.ctrl.portal;


import com.pinde.core.common.enums.InfoStatusEnum;
import com.pinde.core.common.enums.sys.RoleLevelEnum;
import com.pinde.core.common.sci.dao.PortalSuggestMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IGateUserManageBiz;
import com.pinde.sci.biz.portal.IPortalInfoManageBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.form.portal.PortalInfoForm;
import com.pinde.core.model.SysCfg;
import com.pinde.core.model.PortalInfoExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/portal/manage/info")
public class PortalInfoManageController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(PortalInfoManageController.class);
	@Autowired
	private IPortalInfoManageBiz infoManageBiz;
	@Autowired
	private IRoleBiz roleBiz;
    @Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IGateUserManageBiz gateUserManageBiz;
	@Autowired
	private ICfgBiz cfgBiz;
	@Autowired
	private PortalSuggestMapper portalSuggestMapper;
	
	/**
	 * 显示新增界面
	 * @return
	 */
	@RequestMapping(value="/add")
	public String showAdd(Model model){
		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
		model.addAttribute("imageBaseUrl", imageBaseUrl);
		return "portal/manage/editInfo";
	}
	/**
	 * 显示编辑界面
	 * @param infoFlow
	 * @return
	 */
	@RequestMapping(value="/showEdit")
	public ModelAndView showEdit(String infoFlow,String flag,String role){
		PortalInfoExt info = null;
		if(StringUtil.isNotBlank(infoFlow)){
			info = this.infoManageBiz.getExtByFlow(infoFlow);
		}
		SysRole sysRole = new SysRole();
        sysRole.setWsId((String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID));
		sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		Map<String,Object> roleFlows=gateUserManageBiz.getUserRoles(GlobalContext.getCurrentUser().getUserFlow());
		ModelAndView mav = new ModelAndView("portal/manage/editInfo");
		if("show".equals(flag)||"pass".equals(flag)){
			mav.setViewName("portal/manage/viewInfo");
			mav.addObject("flag", flag);
			mav.addObject("roleFlows",roleFlows);
		}
		mav.addObject("role",role);
		mav.addObject("roleFlows",roleFlows);
		mav.addObject("sysRoleList",sysRoleList);
		String imageBaseUrl = this.infoManageBiz.getImageBaseUrl();
		mav.addObject("imageBaseUrl", imageBaseUrl);
		mav.addObject("info", info);
		
		return mav;
	}

	@RequestMapping(value="/print")
	public ModelAndView print(String infoFlow,String flag,String role){
		PortalInfoExt info = null;
		if(StringUtil.isNotBlank(infoFlow)){
			info = this.infoManageBiz.getExtByFlow(infoFlow);
		}
		SysRole sysRole = new SysRole();
        sysRole.setWsId((String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID));
		sysRole.setRoleLevelId(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		Map<String,Object> roleFlows=gateUserManageBiz.getUserRoles(GlobalContext.getCurrentUser().getUserFlow());
		ModelAndView mav = new ModelAndView("portal/manage/editInfo");
		if("show".equals(flag)||"pass".equals(flag)){
			mav.setViewName("portal/manage/viewInfo-print");
			mav.addObject("flag", flag);
			mav.addObject("roleFlows",roleFlows);
		}
		mav.addObject("role",role);
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
	public @ResponseBody String save(PortalInfo info, String role) {
		if(StringUtil.isNotBlank(info.getInfoFlow())){//更新
			return updateInfo(info);
		}else{
			String cityId = GlobalContext.getCurrentUser().getNativePlaceCityId();
			String cityName = GlobalContext.getCurrentUser().getNativePlaceCityName();
			String weType = GlobalContext.getCurrentUser().getWeiXinId();
			info.setCityId(cityId);
			info.setCityName(cityName);
			info.setWestEaetType(weType);
			if(info.getColumnId().substring(0,4).equals("LM03")||info.getColumnId().substring(0,4).equals("LM08")){
				info.setInfoStatusId(InfoStatusEnum.Passed.getId());
				info.setInfoStatusName(InfoStatusEnum.Passed.getName());
			}
			if("city".equals(role)){
				info.setInfoStatusId(InfoStatusEnum.CityPassed.getId());
				info.setInfoStatusName(InfoStatusEnum.CityPassed.getName());
			}else if("admin".equals(role)){
				info.setInfoStatusId(InfoStatusEnum.Passed.getId());
				info.setInfoStatusName(InfoStatusEnum.Passed.getName());
			}
			return addInfo(info);
		}
	}
	/**
	 * 新增资讯
	 * @param info
	 * @return
	 */
	public  String addInfo(PortalInfo info){
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
	public  String updateInfo(PortalInfo info){
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
	private boolean checkInput(PortalInfo info){
		if(info==null){
			return false;
		}
		if(StringUtil.isEmpty(info.getInfoTitle())||StringUtil.isBlank(info.getInfoTitle())){
			return false;
		}
		if(StringUtil.isEmpty(info.getColumnId())||StringUtil.isBlank(info.getColumnId())){
			return false;
		}
		if(StringUtil.isEmpty(info.getInfoTime())||StringUtil.isBlank(info.getInfoTime())){
			return false;
		}
		return true;
	}
	/**
	 * 校验前端输入
	 * @param info
	 * @return
	 */
	private boolean checkUpdateInput(PortalInfo info){
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
		if(StringUtil.isEmpty(info.getInfoTime())||StringUtil.isBlank(info.getInfoTime())){
			return false;
		}
		return true;
	}
	/**
	 * 资讯列表
	 * @param currentPage 当前页索引
	 * @param form 参数封装
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/list")
	public String getInfoList(Integer currentPage,String role,PortalInfoForm form,String parentColumnName,
							  HttpServletRequest request,Model model,String type){
//        boolean zxsxFlag = false;
        SysUser currentUser = GlobalContext.getCurrentUser();
//        List<SysUserRole> userRoles = userRoleBiz.getByUserFlow(currentUser.getUserFlow());
//        for (SysUserRole urole :userRoles){
//            List<String> popedom = roleBiz.getPopedom(urole.getRoleFlow());
//            for(String menuId:popedom){
//                if("portals-mhgl-zxgl-zxsh".equals(menuId)){
//                    zxsxFlag =  true;
//		            model.addAttribute("zxsh", com.pinde.core.common.GlobalConstant.FLAG_Y);
//                    break;
//                }
//            }
//            if(zxsxFlag) break;
//        }
//        if(!zxsxFlag){ //非管理员只能查询自己创建的资讯
//            form.setUserFlow(currentUser.getUserFlow());
//        }
		List<String> statusList = new ArrayList<>();
		if (StringUtil.isBlank(parentColumnName)) {
			form.setColumnId("");
		}
		if("local".equals(role)){
			form.setUserFlow(currentUser.getUserFlow());
		}else if("city".equals(role)){
			String cityId = currentUser.getNativePlaceCityId();
			String weType = currentUser.getWeiXinId();
			form.setCityId(cityId);
			form.setWeType(weType);
		}else if("admin".equals(role)&&!"edit".equals(type)){
			statusList.add(InfoStatusEnum.Passing.getId());
			statusList.add(InfoStatusEnum.CityPassed.getId());
			statusList.add(InfoStatusEnum.NoPassed.getId());
			model.addAttribute("currentUserFlow",currentUser.getUserFlow());
		}
		if("edit".equals(type)){
			form.setUserFlow(currentUser.getUserFlow());
		}
		PageHelper.startPage(currentPage, getPageSize(request));
        List<PortalInfo> infoList =  this.infoManageBiz.getList(form,statusList);
		model.addAttribute("infoList", infoList);

        return "portal/manage/infoList";
	}
	@RequestMapping(value="/viewList")
	public String viewList(Integer currentPage,String role,PortalInfoForm form,String parentColumnName,
							  HttpServletRequest request,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		if (StringUtil.isBlank(parentColumnName)) {
			form.setColumnId("");
		}
		form.setInfoStatusId(InfoStatusEnum.Passed.getId());
		if("city".equals(role)){
			String cityId = currentUser.getNativePlaceCityId();
			String weType = currentUser.getWeiXinId();
			form.setCityId(cityId);
			form.setWeType(weType);
		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<PortalInfo> infoList =  this.infoManageBiz.getList(form,null);
		model.addAttribute("infoList", infoList);

		return "portal/manage/infoViewList";
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
	public @ResponseBody String updateRecordStatus(PortalInfo info){
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
	@RequestMapping(value="/modifyPortalInfo")
	public @ResponseBody String modifyInxInfo(PortalInfo info){
		int result =  infoManageBiz.modifyPortalInfo(info);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	//腾讯问卷
	@RequestMapping(value="/qqwj")
	public String qqwj(Model model){
		SysCfg cfg = cfgBiz.read("qqwj_url");
		if(cfg!=null) {
			model.addAttribute("cfg", cfg.getCfgValue());
		}
		return "portal/manage/qqwj";
	}

	//保存腾讯问卷
	@RequestMapping("/saveQqwj")
	@ResponseBody
	public int changAllowReduction(SysCfg cfg){
		SysCfg old = cfgBiz.read(cfg.getCfgCode());
		if(old==null){
			return cfgBiz.add(cfg);
		}else {
			return cfgBiz.mod(cfg);
		}
	}

	@RequestMapping("/portalFileList")
	public String portalFileList(PortalFile file, Integer currentPage, HttpServletRequest request, Model model) {
		PageHelper.startPage(currentPage, getPageSize(request));
		List<PortalFile> fileList = this.infoManageBiz.getFileList(file);
		model.addAttribute("fileList",fileList);
		return "portal/manage/fileList";
	}

	@RequestMapping("/addFile")
	public String addFile(){
		return "portal/manage/editFile";
	}

	@RequestMapping(value = "/uploadFile", method = {RequestMethod.POST})
	@ResponseBody
	public int checkUploadFile(MultipartFile uploadFile, Model model) {
		return infoManageBiz.uploadFile(uploadFile);
	}

	@RequestMapping("/delFile")
	@ResponseBody
	int delFile(PortalFile file){
        file.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
		return infoManageBiz.edit(file);
	}

	@RequestMapping("/portalSuggestList")
	public String portalSuggestList(PortalSuggest suggest, Integer currentPage, HttpServletRequest request, Model model) {
		PageHelper.startPage(currentPage, getPageSize(request));
		List<PortalSuggest> portalSuggestList = infoManageBiz.getSuggestList(suggest);
		model.addAttribute("portalSuggestList",portalSuggestList);
		return "portal/manage/suggestList";
	}

	@RequestMapping("/reply")
	public String reply(String recordFlow,Model model){
		PortalSuggest suggest = infoManageBiz.readSuggest(recordFlow);
		model.addAttribute("suggest",suggest);
		return "portal/manage/reply";
	}

	@RequestMapping("/editSuggest")
	@ResponseBody
	public int editSuggest(PortalSuggest suggest){
		return infoManageBiz.editSuggest(suggest);
	}

	@RequestMapping(value = "/addSuggest",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public int addSuggest(PortalSuggest suggest){
		String currentDate = DateUtil.getCurrDate();
		suggest.setSubmitTime(currentDate);
		PortalSuggestExample example = new PortalSuggestExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andUserPhoneEqualTo(suggest.getUserPhone()).andSubmitTimeEqualTo(currentDate);
		int count = portalSuggestMapper.countByExample(example);
		if(count>0){
			return -1;
		}
		return infoManageBiz.editSuggest(suggest);
	}

	@RequestMapping("/changeShowFlag")
	@ResponseBody
	public int changeShowFlag(PortalSuggest suggest){
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(suggest.getShowFlag())) {
			PortalSuggestExample example = new PortalSuggestExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andShowFlagEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
			int count = portalSuggestMapper.countByExample(example);
			if(count>=3){
				return -1;
			}
		}
		return infoManageBiz.editSuggest(suggest);
	}
}
