package com.pinde.sci.ctrl.jszy;


import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.login.ILoginBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.sch.ISchRotationBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.*;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.model.mo.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/jszy/singup")
public class JszySingupController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(JszySingupController.class);

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private ILoginBiz loginBiz;
	@Autowired
	private ISchRotationBiz schRotationBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	//获取带教与科主任签名
	public static String getUserSingPhoto(String userFlow)
	{
		if(StringUtil.isNotBlank(userFlow))
		{
			SysUserMapper quserBiz= SpringUtil.getBean(SysUserMapper.class);
			SysUser user=quserBiz.selectByPrimaryKey(userFlow);
			String basePath=StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"));
			if(user!=null&&StringUtil.isNotBlank(basePath))
			{
				String photoPath=File.separator+"singinPhoto"+File.separator+user.getUserCode()+".png";
				basePath+=photoPath;
				File imgFile = new File(basePath);
				if(imgFile.exists()){
					String path=
//							"http://www.develop.com/";
							StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url"));
					if(StringUtil.isNotBlank(path))
					{
						return "<img src='"+path+photoPath+"' >";
					}else{
						return user.getUserName();
					}
				}else{
					return user.getUserName();
				}
			}
		}
		return "";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request,String userFlow,Model model){
		String errorLoginPage ="inx/jszy/login2";
		String successLoginPage = "redirect:/main?time="+new Date();
		SysUser user = userBiz.readSysUser(userFlow);
		//唯一登录
		if(!GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())&&GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("unique_login_flag"))){
			if(SessionData.sessionDataMap.containsKey(user.getUserFlow()) && 
					!SessionData.sessionDataMap.get(user.getUserFlow()).getIp().equals(request.getRemoteAddr())){
				model.addAttribute("loginErrorMessage",SpringUtil.getMessage("user.alreadyLogin"));
				return errorLoginPage;
			}
		}
		
		//设置当前用户
		setSessionAttribute(GlobalConstant.CURRENT_USER, user);
		setSessionAttribute(GlobalConstant.CURRENT_USER_NAME, user.getUserName());	
		setSessionAttribute(GlobalConstant.CURRENT_ORG, orgBiz.readSysOrg(user.getOrgFlow()));	
		//设置当前用户部门列表
		setSessionAttribute(GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(user));
		
		//加载系统权限
		loginBiz.loadSysRole(user.getUserFlow());
		
		if(GlobalConstant.ROOT_USER_CODE.equals(user.getUserCode())){
			return successLoginPage;
		}

		List<String> currUserWorkStationIdList = (List<String>) GlobalContext.getSessionAttribute(GlobalConstant.CURRENT_WORKSTATION_ID_LIST);
		if (currUserWorkStationIdList != null && currUserWorkStationIdList.size() > 0) {
			return successLoginPage;
		}else{
			model.addAttribute("loginErrorMessage", SpringUtil.getMessage("permissin.error"));
			return errorLoginPage;			
		}
	}
}
