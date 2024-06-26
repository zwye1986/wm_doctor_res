package com.pinde.sci.ctrl.srm;

import com.pinde.sci.biz.srm.IExpertBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IRoleBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.enums.srm.RegPageEnum;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/srm/user")
public class SrmUserController extends GeneralController{
	private static Logger logger=LoggerFactory.getLogger(SrmUserController.class);
	
	@Autowired
	private IUserBiz userBiz;	
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IExpertBiz expertBiz;

	@RequestMapping("/auditUI")
	public String userAuditUI(String userFlow , Model model) throws Exception{
		//根据用户流水号查询用户注册时的角色
		List<SysUserRole> userRoleList = this.userRoleBiz.getByUserFlow(userFlow);
		if(userRoleList!=null && userRoleList.size()==1){
			SysUserRole userRole = userRoleList.get(0);
			String roleFlow = userRole.getRoleFlow();
			SysRole role = this.roleBiz.read(roleFlow);
			String regPageId = role.getRegPageId();
			if(RegPageEnum.ExpertRegPage.getId().equals(regPageId)){
				//专家注册角色
				SysUser user = this.userBiz.readSysUser(userFlow);
				SrmExpert expert = this.expertBiz.readExpert(userFlow);
				model.addAttribute("user" , user);
				model.addAttribute("expert" , expert);
				return "sys/user/expertRegAudit";
				
			}else if(RegPageEnum.ProjRegPage.getId().equals(regPageId)){
				//项目负责人注册角色
				SysUser user = this.userBiz.readSysUser(userFlow);
				model.addAttribute("user" , user);
				return "sys/user/projUserRegAudit";
			}else if(RegPageEnum.ProjRegPage_yh.getId().equals(regPageId)){
				//项目负责人注册角色
				SysUser user = this.userBiz.readSysUser(userFlow);
				model.addAttribute("user" , user);
				return "sys/user/projUserRegAudit";
			}else if(RegPageEnum.orgRegPage.getId().equals(regPageId)){
				//承担单位注册角色
				SysUser user = this.userBiz.readSysUser(userFlow);
				SysOrg org = this.orgBiz.readSysOrg(user.getOrgFlow());
				String orgInfo = org.getOrgInfo();
				Document document = DocumentHelper.parseText(orgInfo);
				Element rootEle = document.getRootElement();
				Element filesEle = (Element) rootEle.selectSingleNode("files");
				Element orgFileEle = (Element)filesEle.selectSingleNode("./item[@name='orgFile']");
				if(orgFileEle!=null){
					String orgFileFlow = orgFileEle.selectSingleNode("value").getText();
					model.addAttribute("orgFileFlow" , orgFileFlow);
				}
				
				Element licenseFileEle = (Element)filesEle.selectSingleNode("./item[@name='licenseFile']");
				if(licenseFileEle!=null){
					String licenseFileFlow = licenseFileEle.selectSingleNode("value").getText();
					model.addAttribute("licenseFileFlow" , licenseFileFlow );
				}
			
				model.addAttribute("user" , user);
				model.addAttribute("org" , org);
				return "sys/user/orgUserRegAudit";
			}
		}
		return null;
	}

}
