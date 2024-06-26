package com.pinde.sci.biz.sys.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRegBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.common.util.WeixinQiYeUtil;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.SrmExpertMapper;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.dao.base.SysUserRoleMapper;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.enums.srm.RegPageEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.srm.UserRegForm;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class UserRegBizImpl implements IUserRegBiz {
	
	private static Logger logger = LoggerFactory.getLogger(UserRegBizImpl.class);
	
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;
	@Autowired
	private SrmExpertMapper srmExpertMapper;
	@Autowired
	private SysOrgMapper sysOrgMapper;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IUserBiz userBiz;
	
	@Override
	public void activatSysUser(SysUser sysUser) {
		sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), sysUser.getUserPasswd()));
		//修改用户信息
		sysUser.setStatusId(UserStatusEnum.Activated.getId());
		sysUser.setStatusDesc(UserStatusEnum.Activated.getName());
		GeneralMethod.setRecordInfo(sysUser, false);
	    sysUserMapper.updateByPrimaryKeySelective(sysUser);
		if(GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("sys_weixin_qiye_flag"))){
			sysUser = sysUserMapper.selectByPrimaryKey(sysUser.getUserFlow());
			boolean result = WeixinQiYeUtil.createUser(InitConfig.getSysCfg("sys_weixin_qiye_corp_id"), InitConfig.getSysCfg("sys_weixin_qiye_secret"), InitConfig.getSysCfg("sys_weixin_qiye_dept_id"), sysUser);
			logger.debug("wei xin qi ye createUser is "+result);
		}
	}

	@Override
	public void regUser(SysUser user, String roleFlow) {
		user.setUserFlow(PkUtil.getUUID());
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitPasswordUtil.getInitPass()));
		user.setStatusId(UserStatusEnum.Reged.getId());
		user.setStatusDesc(UserStatusEnum.Reged.getName());
		GeneralMethod.setRecordInfo(user, true);
		sysUserMapper.insert(user);
		
		SysUserRole insert = new SysUserRole();
		insert.setUserFlow(user.getUserFlow());
		insert.setOrgFlow(user.getOrgFlow());
		insert.setWsId(GlobalConstant.SRM_WS_ID);
		insert.setRoleFlow(roleFlow);
		insert.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		insert.setRecordFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(insert, true);
		sysUserRoleMapper.insert(insert);
	
	}

	@Override
	public void regUser(UserRegForm form , SysRole role) {
		 String regPageId = role.getRegPageId();
		 if(RegPageEnum.ExpertRegPage.getId().equals(regPageId)){
			 //专家注册
			 regExpert(form);
		 }else if(RegPageEnum.ProjRegPage.getId().equals(regPageId)){
			 //项目负责人注册
			 regProjUser(form);
		 }else if(RegPageEnum.ProjRegPage_yh.getId().equals(regPageId)){
			 //余杭项目负责人注册
			 regProjUser(form);
		 }else if(RegPageEnum.orgRegPage.getId().equals(regPageId)){
			 //机构负责人注册
			 orgReg(form);
		 }
		
		
	}
	
	/**
	 * 专家注册
	 * @param form
	 */
	private void regExpert(UserRegForm form){
		SysUser user = form.getUser();
		String roleFlow = form.getRoleFlow();
		SrmExpert expert = form.getExpert();
		
		user.setUserFlow(PkUtil.getUUID());
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId(UserStatusEnum.Reged.getId());
		user.setStatusDesc(UserStatusEnum.Reged.getName());
		user.setSrmExpertFlag(GlobalConstant.FLAG_Y);//先标记为专家
		user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		user.setOrgName(this.sysOrgMapper.selectByPrimaryKey(user.getOrgFlow()).getOrgName());
		user.setEducationName(DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
		user.setPostName(DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
		GeneralMethod.setRecordInfo(user, true);
		sysUserMapper.insert(user);
		
		expert.setUserFlow(user.getUserFlow());
		expert.setEducation(user.getEducationName());
		expert.setPost(user.getPostName());
		GeneralMethod.setRecordInfo(expert, true);
		this.srmExpertMapper.insertSelective(expert);
		
		SysUserRole insert = new SysUserRole();
		insert.setUserFlow(user.getUserFlow());
		insert.setOrgFlow(user.getOrgFlow());
		insert.setWsId(GlobalConstant.SRM_WS_ID);
		insert.setRoleFlow(roleFlow);
		insert.setRecordFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(insert, true);
		sysUserRoleMapper.insert(insert);
	}
	
	/**
	 * 项目负责人注册
	 * @param form
	 */
	private void regProjUser(UserRegForm form){
		SysUser user = form.getUser();
		String roleFlow = form.getRoleFlow();
		user.setUserFlow(PkUtil.getUUID());
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId(UserStatusEnum.Reged.getId());
		user.setStatusDesc(UserStatusEnum.Reged.getName());
		user.setOrgName(this.sysOrgMapper.selectByPrimaryKey(user.getOrgFlow()).getOrgName());
		user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		user.setEducationName(DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
		user.setDegreeName(DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
		user.setTitleName(DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
		user.setPostName(DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
		GeneralMethod.setRecordInfo(user, true);
		sysUserMapper.insert(user);
		
		SysUserRole insert = new SysUserRole();
		insert.setUserFlow(user.getUserFlow());
		insert.setOrgFlow(user.getOrgFlow());
		insert.setWsId(GlobalConstant.SRM_WS_ID);
		insert.setRoleFlow(roleFlow);
		insert.setRecordFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(insert, true);
		sysUserRoleMapper.insert(insert);
	}
	
	/**
	 * 机构管理员注册
	 * @param form
	 */
	private void orgReg(UserRegForm form){
		SysUser user = form.getUser();
		String roleFlow = form.getRoleFlow();
		//添加新用户
		user.setUserFlow(PkUtil.getUUID());
		user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), user.getUserPasswd()));
		user.setStatusId(UserStatusEnum.Reged.getId());
		user.setStatusDesc(UserStatusEnum.Reged.getName());
		user.setOrgName(this.sysOrgMapper.selectByPrimaryKey(user.getOrgFlow()).getOrgName());
		user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		GeneralMethod.setRecordInfo(user, true);
		sysUserMapper.insert(user);
		//上传文件
		List<String[]> fileFlowList = new ArrayList<String[]>();
		MultipartFile orgFile = form.getOrgFile();
		String[] orgFileAry = new String[2];
		String fileFlow = "";
		if(orgFile!=null && StringUtil.isNotBlank(orgFile.getOriginalFilename())){
			fileFlow = this.fileBiz.addFile(orgFile , user.getUserFlow());
			orgFileAry[0] = fileFlow;
			orgFileAry[1] = "orgFile";
			fileFlowList.add(orgFileAry);
		}
		MultipartFile licenseFile = form.getLicenseFile();
		if(licenseFile!=null && StringUtil.isNotBlank(licenseFile.getOriginalFilename())){
			fileFlow = this.fileBiz.addFile(licenseFile , user.getUserFlow());
			orgFileAry = new String[2];
			orgFileAry[0] = fileFlow;
			orgFileAry[1] = "licenseFile";
			fileFlowList.add(orgFileAry);
		}
		
		//更新组织机构
		String orgFlow = user.getOrgFlow();
		SysOrg org = form.getOrg();
		org.setOrgFlow(orgFlow);
		SysOrg oldOrg = this.sysOrgMapper.selectByPrimaryKey(orgFlow);
		String orgInfo = oldOrg.getOrgInfo();
		if(StringUtil.isNotBlank(orgInfo)){
			//追加
			try {
				Document document = DocumentHelper.parseText(orgInfo);
				Element rootElement = document.getRootElement();
				Element filesElement = rootElement.addElement("files");//文件节点
				for(int i=0 ; i<fileFlowList.size() ; i++ ){
					
					Element itemEle = filesElement.addElement("item");
					itemEle.addAttribute("name", fileFlowList.get(i)[1]);
					itemEle.addElement("value").setText(fileFlowList.get(i)[0]);
				}
				org.setOrgInfo(document.asXML());
			} catch (DocumentException e) {
				throw new RuntimeException("xml解析异常");
			}
		}else{
			//新建
			Document document=DocumentHelper.createDocument();
			Element rootElement=document.addElement("orgInfo");//根节点
			Element filesElement = rootElement.addElement("files");//文件节点
			for(int i=0 ; i<fileFlowList.size() ; i++ ){
				Element itemEle = filesElement.addElement("item");
				itemEle.addAttribute("name", fileFlowList.get(i)[1]);
				itemEle.addElement("value").setText(fileFlowList.get(i)[0]);
			}
			org.setOrgInfo(document.asXML());
		}
		this.sysOrgMapper.updateByPrimaryKeySelective(org);
		
		//给用户设置角色
		SysUserRole insert = new SysUserRole();
		insert.setUserFlow(user.getUserFlow());
		insert.setOrgFlow(user.getOrgFlow());
		insert.setWsId(GlobalConstant.SRM_WS_ID);
		insert.setRoleFlow(roleFlow);
		insert.setRecordFlow(PkUtil.getUUID());
		GeneralMethod.setRecordInfo(insert, true);
		sysUserRoleMapper.insert(insert);
	}

	@Override
	public String srmRegValidate(UserRegForm form, SysRole role) {
		/*
		 *江苏科研16家机构人员需要多个账号因此这些机构不做身份证唯一校验
		 *
		 */
		String[] removeOrgName = {
				"徐州医药高等职业学校（教研室）","镇江高等专科学校卫生护理学院（教研室）","徐州市卫生学校（教研室）","南京卫生学校（教研室）","江苏建康职业学院（教研室）",
				"江苏护理职业学院（教研室）","无锡卫生高等职业技术学校（教研室）","连云港中医药高等职业技术学校（教研室）","扬州职业大学医学院（教研室）","盐城卫生职业技术学院（教研室）",
				"泰州职业技术学院医学技术学院（教研室）","宿迁市卫生学校（教研室）","南通卫生高等职业技术学校（教研室）","常州卫生高等职业技术学校（教研室）","江苏省卫生职业教育研究室","苏州卫生职业技术学院（教研室）"
		};
		List<String> removeOrgNameList = Arrays.asList(removeOrgName);
		String eroMsg = "";
		SysUser sysUser = form.getUser();
		String regPageId = role.getRegPageId();
		//判断用户id是否重复
		SysUser old = userBiz.findByUserCode(sysUser.getUserCode());
		if(old!=null){
			eroMsg = GlobalConstant.USER_CODE_REPETE;
			return eroMsg;
		}
		//如果注册用户的注册机构不是上面的16家机构之一 需校验身份证
		if(!removeOrgNameList.contains(sysUser.getOrgName())) {
			//判断用户身份证号是否重复
			old = userBiz.findByIdNo(sysUser.getIdNo());
			if (old != null) {
				eroMsg = GlobalConstant.USER_ID_NO_REPETE;
				return eroMsg;
			}
			//判断用户手机号是否重复
			old = userBiz.findByUserPhone(sysUser.getUserPhone());
			if(old!=null){
				eroMsg = GlobalConstant.USER_PHONE_REPETE;
				return eroMsg;
			}
			//判断用户电子邮件是否重复
			old = userBiz.findByUserEmail(sysUser.getUserEmail());
			if(old!=null){
				eroMsg = GlobalConstant.USER_EMAIL_REPETE;
				return eroMsg;
			}
		}
		//文件校验
		if(RegPageEnum.orgRegPage.getId().equals(regPageId)){
			//注册机构管理员 验证文件
			eroMsg = this.checkFile(form.getOrgFile());
			if(StringUtil.isBlank(eroMsg)){
				eroMsg = this.checkFile(form.getLicenseFile());
			}
				
		}
		return eroMsg;
		
	}
	
	/**
	 * 校验  文件上传
	 * @param file
	 * @return
	 */
	private String checkFile(MultipartFile file){
		if(file==null || StringUtil.isBlank(file.getOriginalFilename())){
			return GlobalConstant.UPLOAD_FILE_NULL;
		}
		try{
			String fileType = file.getContentType();//MIME类型;
			String fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();//后缀名 转换成小写
			List<String> mimeList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
				mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
			}
			List<String> suffixList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
				suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
			}
			if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
				return GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
			}
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if(file.getSize()>limitSize*1024*1024){
				return GlobalConstant.UPLOAD_IMG_SIZE_ERROR +limitSize +"M" ;
			}
		}catch(Exception e){
			return GlobalConstant.VALIDATE_FILE_FAIL;
		}
		
		return "";//表示文件合格
	}
	

}
