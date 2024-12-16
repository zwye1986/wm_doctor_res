package com.pinde.sci.ctrl.sys;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.jsres.JsResTeacherLevelEnum;
import com.pinde.core.common.enums.pub.UserSexEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.RoleLevelEnum;
import com.pinde.core.common.enums.sys.UserEmailStatusEnum;
import com.pinde.core.common.enums.sys.UserPhoneStatusEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.jsres.IJsResStatisticBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IMsgBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.RSAUtils;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.dao.base.ResTeacherTrainingMapper;
import com.pinde.sci.dao.base.SysUserSchoolMapper;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.core.model.PubUserResume;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResTeacherTraining;
import com.pinde.sci.model.mo.ResTeacherTrainingExample;
import com.pinde.sci.model.mo.SysUserRole;
import com.pinde.sci.model.mo.*;
import nl.captcha.Captcha;
import nl.captcha.backgrounds.TransparentBackgroundProducer;
import nl.captcha.gimpy.DropShadowGimpyRenderer;
import nl.captcha.text.producer.DefaultTextProducer;
import nl.captcha.text.renderer.DefaultWordRenderer;
import nl.captcha.text.renderer.WordRenderer;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.util.encoders.Hex;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.*;
import java.net.URLEncoder;
import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/sys/user")
public class UserController extends GeneralController{
	private static Logger logger=LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IRoleBiz roleBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IMsgBiz msgBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private SysUserSchoolMapper userSchoolMapper;
	@Autowired
	private ResTeacherTrainingMapper teacherTrainingMapper;
	@Autowired
	private IJsResStatisticBiz resStatisticBiz;

	@RequestMapping(value="/list/{userListScope}",method={RequestMethod.POST,RequestMethod.GET})
    public String list(@PathVariable String userListScope, Integer currentPage, SysUser search, String roleFlow, String deptFlow,
                       Model model, HttpServletRequest request) {

		long startTime = System.currentTimeMillis(); //获取开始时间
        setSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE, userListScope);
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		//角色列表
		SysRole sysRole = new SysRole();
		sysRole.setWsId(wsId);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		model.addAttribute("sysRoleList", sysRoleList);

		List<SysUser> sysUserList=null;//初始化查询结果
		//组织用户查询map
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("roleFlow",roleFlow);
		paramMap.put("wsId",wsId);
		if(search!=null){
			paramMap.put("orgName",search.getOrgName());
			paramMap.put("idNo",search.getIdNo());
			paramMap.put("userPhone",search.getUserPhone());
			paramMap.put("userEmail",search.getUserEmail());
			paramMap.put("userName",search.getUserName());
			paramMap.put("userCode",search.getUserCode());
			paramMap.put("statusId",search.getStatusId());
		}

        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(userListScope)) {
			SysUser currUser = GlobalContext.getCurrentUser();
			paramMap.put("orgFlow",currUser.getOrgFlow());
			paramMap.put("deptFlow",deptFlow);
			PageHelper.startPage(currentPage, getPageSize(request));
			sysUserList = userBiz.searchUserWithRole(paramMap);
			model.addAttribute("sysUserList",sysUserList);

//			if(com.pinde.core.common.GlobalConstant.ERP_WS_ID.equals(wsId)){
//				Map<String,List<ErpUserRegionPopedom>> erpUserRegionPopedomMap  = new HashMap<String, List<ErpUserRegionPopedom>>();
//				for(SysUser user : sysUserList){
//					String userFlow = user.getUserFlow();
//					List<ErpUserRegionPopedom> erpUserRegionPopedomList = userRoleBiz.getErpUserRegionByUserFlow(userFlow);
//					erpUserRegionPopedomMap.put(userFlow, erpUserRegionPopedomList);
//				}
//				model.addAttribute("erpUserRegionPopedomMap", erpUserRegionPopedomMap);
//			}

//			if(com.pinde.core.common.GlobalConstant.RES_WS_ID.equals(wsId)){
//				List<String> userFlows = new ArrayList<String>();
//				for(SysUser user : sysUserList){
//					userFlows.add(user.getUserFlow());
//				}
//				List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
//				if(doctorList!=null && doctorList.size()>0){
//					Map<String,ResDoctor> resDoctorMap = new HashMap<String, ResDoctor>();
//					for(ResDoctor doctor : doctorList){
//						resDoctorMap.put(doctor.getDoctorFlow(),doctor);
//					}
//					model.addAttribute("resDoctorMap", resDoctorMap);
//				}
//			}
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(userListScope)) {
			SysUser currUser = GlobalContext.getCurrentUser();
			List<String> orgFlows = new ArrayList<String>();
			List<SysOrg> orgList = this.orgBiz.searchChildrenOrgByOrgFlow(currUser.getOrgFlow());
			if(orgList!=null && !orgList.isEmpty()){
				for(SysOrg org:orgList){
					orgFlows.add(org.getOrgFlow());
				}
                model.addAttribute("orgList",orgList);
			}
			paramMap.put("orgFlows",orgFlows);
			PageHelper.startPage(currentPage, getPageSize(request));
			sysUserList=userBiz.searchUserWithRole(paramMap);
			model.addAttribute("sysUserList",sysUserList);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {
//			if(StringUtil.isNotBlank(search.getOrgFlow())){
			PageHelper.startPage(currentPage, getPageSize(request));
			sysUserList = userBiz.searchUserWithRole(paramMap);
			model.addAttribute("sysUserList",sysUserList);
//			}
		}
		// 禅道bug 4781 查询报错，未查到人员信息
        if (null == sysUserList || sysUserList.size() == 0) {
            return "sys/user/list";
        }

		List<String> userFlows = new ArrayList<>();
        for (SysUser sysUser : sysUserList) {
            userFlows.add(sysUser.getUserFlow());
        }

        long endTime1 = System.currentTimeMillis(); //获取结束时间
        logger.info("项目详情加载运行时间1：" + (endTime1 - startTime) + "ms"); //输出程序运行时间
//			List<SysUserRole> sysUserRoleList = userRoleBiz.getByOrgFlow(null,wsId);
        List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(userFlows,wsId);
        long endTime2 = System.currentTimeMillis(); //获取结束时间
        logger.info("项目详情加载运行时间2：" + (endTime2 - startTime) + "ms"); //输出程序运行时间
			Map<String,List<SysUserRole>> sysUserRoleMap  = new HashMap<String, List<SysUserRole>>();
			for(SysUserRole sysUserRole : sysUserRoleList){
				String userFlow = sysUserRole.getUserFlow();
				if(sysUserRoleMap.containsKey(userFlow)){
					List<SysUserRole> list = sysUserRoleMap.get(userFlow);
					list.add(sysUserRole);
				}else{
					List<SysUserRole> list = new ArrayList<SysUserRole>();
					list.add(sysUserRole);
					sysUserRoleMap.put(userFlow, list);
				}
			}
        long endTime3 = System.currentTimeMillis(); //获取结束时间
        logger.info("项目详情加载运行时间3：" + (endTime3 - startTime) + "ms"); //输出程序运行时间
			model.addAttribute("sysUserRoleMap", sysUserRoleMap);
//		}
		long endTime = System.currentTimeMillis(); //获取结束时间
		logger.info("项目详情加载运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
		return "sys/user/list";
	}

	@RequestMapping(value={"/edit/{userListScope}"})
	public String edit(@PathVariable String userListScope,String userFlow,String currentPage,Model model){
		model.addAttribute("currentPage",currentPage);
        setSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE, userListScope);
        SysUser logiunUser=GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(userFlow)){
			SysUser sysUser=userBiz.readSysUser(userFlow);
			model.addAttribute("sysUser",sysUser);
			String tchRoleFlow=InitConfig.getSysCfg("res_teacher_role_flow");
			String headRoleFlow=InitConfig.getSysCfg("res_head_role_flow");
			String universitySonRoleFlow=InitConfig.getSysCfg("res_university_son_role_flow");
            String isTeacher = com.pinde.core.common.GlobalConstant.FLAG_N;
			boolean isTeach=false;
            String isUniversitySon = com.pinde.core.common.GlobalConstant.FLAG_N;
            ;
			if(StringUtil.isNotBlank(tchRoleFlow)||StringUtil.isNotBlank(headRoleFlow))
			{
                List<SysRole> userRoles = roleBiz.search(userFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
				if(userRoles!=null&&userRoles.size()>0){
					for(SysRole userRole:userRoles){
						String roleFlow = userRole.getRoleFlow();
						if(tchRoleFlow.equals(roleFlow)||headRoleFlow.equals(roleFlow)){
                            isTeacher = com.pinde.core.common.GlobalConstant.FLAG_Y;
						}
						if(tchRoleFlow.equals(roleFlow)){
							isTeach=true;
						}
						if (universitySonRoleFlow.equals(roleFlow)){
                            isUniversitySon = com.pinde.core.common.GlobalConstant.FLAG_Y;
							SysUserSchoolExample example = new SysUserSchoolExample();
                            example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
							List<SysUserSchool> schoolList = userSchoolMapper.selectByExample(example);
							if (null!=schoolList && schoolList.size()>0){
								String school="";
								for (SysUserSchool sysUserSchool : schoolList) {
									school=school+sysUserSchool.getSchool()+",";
								}
								sysUser.setSchool(school.substring(0,school.length()-1));
							}
						}
					}
				}
			}
			model.addAttribute("isTeacher",isTeacher);
			model.addAttribute("isTeach",isTeach);
			model.addAttribute("isUniversitySon",isUniversitySon);
			SysDept sysDept = new SysDept();
            sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			sysDept.setOrgFlow(sysUser.getOrgFlow());
			List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
			model.addAttribute("sysDeptList",sysDeptList);

			List<SysUserDept> userDeptList = userBiz.getUserDept(sysUser);
			Map<String,String> userDeptMap = new HashMap<String, String>();
			for(SysUserDept userDept : userDeptList){
				userDeptMap.put(userDept.getDeptFlow(),userDept.getDeptFlow());
			}
			model.addAttribute("userDeptMap",userDeptMap);
			if(sysUser!=null&&StringUtil.isNotBlank(sysUser.getCertificateFile()))
			{
				PubFile file=fileBiz.readFile(sysUser.getCertificateFile());
				model.addAttribute("file",file);
			}
		}else{
			SysDept sysDept = new SysDept();
            sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			sysDept.setOrgFlow(logiunUser.getOrgFlow());
			List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
			model.addAttribute("sysDeptList",sysDeptList);
		}
		if(StringUtil.isNotBlank(logiunUser.getOrgFlow())){
			List<SysOrg> orgList = this.orgBiz.searchChildrenOrgByOrgFlow(logiunUser.getOrgFlow());
			model.addAttribute("orgList",orgList);
		}
//		//派送学校为南医大的基地
//		List<SysOrg> gxOrgs = orgBiz.orgGxList();
//		model.addAttribute("gxOrgs", gxOrgs);
		//（南医大）院校
		List<String> schools = doctorBiz.getSchools();
		model.addAttribute("schools",schools);
		if ("/inx/hbres".equals(InitConfig.getSysCfg("sys_index_url"))) {
			return "hbres/user/edit";
		}
		return "sys/user/edit";
	}

	//角色切换
	@RequestMapping(value="/getSchools")
	@ResponseBody
	public List<SysUserSchool> getSchools(){
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		SysUserSchoolExample example = new SysUserSchoolExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		return userSchoolMapper.selectByExample(example);
	}

	@RequestMapping(value={"/edit4sczy/{userListScope}"})
	public String edit4sczy(@PathVariable String userListScope,String userFlow,Model model){
        setSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE, userListScope);
		SysUser logiunUser=GlobalContext.getCurrentUser();
		if(StringUtil.isNotBlank(userFlow)){
			SysUser sysUser=userBiz.readSysUser(userFlow);
			model.addAttribute("sysUser",sysUser);
			String tchRoleFlow=InitConfig.getSysCfg("res_teacher_role_flow");
			String headRoleFlow=InitConfig.getSysCfg("res_head_role_flow");
            String isTeacher = com.pinde.core.common.GlobalConstant.FLAG_N;
			boolean isTeach=false;
			if(StringUtil.isNotBlank(tchRoleFlow)||StringUtil.isNotBlank(headRoleFlow))
			{
                List<SysRole> userRoles = roleBiz.search(userFlow, com.pinde.core.common.GlobalConstant.RES_WS_ID);
				if(userRoles!=null&&userRoles.size()>0){
					for(SysRole userRole:userRoles){
						String roleFlow = userRole.getRoleFlow();
						if(tchRoleFlow.equals(roleFlow)||headRoleFlow.equals(roleFlow)){
                            isTeacher = com.pinde.core.common.GlobalConstant.FLAG_Y;
						}
						if(tchRoleFlow.equals(roleFlow)){
							isTeach=true;
						}
					}
				}
			}
			model.addAttribute("isTeacher",isTeacher);
			model.addAttribute("isTeach",isTeach);
			SysDept sysDept = new SysDept();
            sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			sysDept.setOrgFlow(sysUser.getOrgFlow());
			List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
			model.addAttribute("sysDeptList",sysDeptList);

			List<SysUserDept> userDeptList = userBiz.getUserDept(sysUser);
			Map<String,String> userDeptMap = new HashMap<String, String>();
			for(SysUserDept userDept : userDeptList){
				userDeptMap.put(userDept.getDeptFlow(),userDept.getDeptFlow());
			}
			model.addAttribute("userDeptMap",userDeptMap);
			if(sysUser!=null&&StringUtil.isNotBlank(sysUser.getCertificateFile()))
			{
				PubFile file=fileBiz.readFile(sysUser.getCertificateFile());
				model.addAttribute("file",file);
			}
		}else{
			SysDept sysDept = new SysDept();
            sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			sysDept.setOrgFlow(logiunUser.getOrgFlow());
			List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
			model.addAttribute("sysDeptList",sysDeptList);
		}
		if(StringUtil.isNotBlank(logiunUser.getOrgFlow())){
			List<SysOrg> orgList = this.orgBiz.searchChildrenOrgByOrgFlow(logiunUser.getOrgFlow());
			model.addAttribute("orgList",orgList);
		}
		return "sczyres/manage/userEdit";
	}

	@RequestMapping(value={"/getDept"})
	public String getDept(String orgFlow,String deptFlow,Model model){
		SysDept sysDept = new SysDept();
		sysDept.setOrgFlow(orgFlow);
        sysDept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> sysDeptList = deptBiz.searchDept(sysDept);
		model.addAttribute("sysDeptList",sysDeptList);
		return "sys/user/deptSelect";
	}


	@RequestMapping(value={"/save"},method=RequestMethod.POST)
	public @ResponseBody String save(SysUser user,String[] mulDeptFlow,String roleFlow,MultipartFile file,String isRe){
		uniformUser(user);

		//新增用户是判断
		if(StringUtil.isBlank(user.getUserFlow())){
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCode(user.getUserCode());
			if(old!=null){
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
			}

			if(StringUtil.isNotBlank(user.getIdNo())){
				old = userBiz.findByIdNo(user.getIdNo());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE;
				}
			}

			if(StringUtil.isNotBlank(user.getUserPhone())){
				old = userBiz.findByUserPhone(user.getUserPhone());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
				}
			}

			if(StringUtil.isNotBlank(user.getUserEmail())){
				old = userBiz.findByUserEmail(user.getUserEmail());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
				}
			}
		}else{
			String userFlow = user.getUserFlow();
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCodeNotSelf(userFlow,user.getUserCode());
			if(old!=null){
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
			}

			if(StringUtil.isNotBlank(user.getIdNo())){
				old = userBiz.findByIdNoNotSelf(userFlow,user.getIdNo());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE;
				}
			}

			if(StringUtil.isNotBlank(user.getUserPhone())){
				old = userBiz.findByUserPhoneNotSelf(userFlow,user.getUserPhone());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
				}
			}

			if(StringUtil.isNotBlank(user.getUserEmail())){
				old = userBiz.findByUserEmailNotSelf(userFlow,user.getUserEmail());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
				}
			}
		}

		if(StringUtil.isNotBlank(user.getSexId())){
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		}
		if(!"gzykdx".equals(GlobalContext.getCurrentWsId())){
            user.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
		}
        user.setDegreeName(com.pinde.core.common.enums.DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
        user.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
        user.setEducationName(com.pinde.core.common.enums.DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
        user.setCertificateLevelName(com.pinde.core.common.enums.DictTypeEnum.Certificatelevel.getDictNameById(user.getCertificateLevelId()));
		user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
		user.setDeptName(StringUtil.defaultString(InitConfig.getDeptNameByFlow(user.getDeptFlow())));
        user.setTeacherTypeName(com.pinde.core.common.enums.DictTypeEnum.TeachersType.getDictNameById(user.getTeacherTypeId()));

		if(file!=null&&!file.isEmpty()&&file.getSize()>0)
		{
			String fileFlow=saveCertificateFile(user.getCertificateFile(),file);
			user.setCertificateFile(fileFlow);
        } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isRe)) {
			user.setCertificateFile("");
		}

		if (StringUtil.isNotBlank(user.getSchool())){
			String universitySonRoleFlow=InitConfig.getSysCfg("res_university_son_role_flow");
            List<SysRole> userRoles = roleBiz.search(user.getUserFlow(), com.pinde.core.common.GlobalConstant.RES_WS_ID);
			boolean isUniversitySon=false;
			if(userRoles!=null&&userRoles.size()>0){
				for(SysRole userRole:userRoles){
					if (universitySonRoleFlow.equals(userRole.getRoleFlow())){
						isUniversitySon=true;
						break;
					}
				}
			}

			if (isUniversitySon){
				List<String> schoolList = Arrays.asList(user.getSchool().split(","));
				if (schoolList.size()>=1){
					//如果用户角色是 高校子管理员并且选择多个院校，取第一个保存到用户表，并保存一份到 用户-院校关联表中
					user.setSchool(schoolList.get(0));
					SysUserSchool school = new SysUserSchool();
					school.setUserFlow(user.getUserFlow());
					school.setUserName(user.getUserName());
                    school.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
					SysUserSchoolExample example = new SysUserSchoolExample();
                    example.createCriteria().andUserFlowEqualTo(user.getUserFlow()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					userSchoolMapper.updateByExampleSelective(school,example);	//移除用户之前的院校
					GeneralMethod.setRecordInfo(school, true);
					for (String s : schoolList) {
						school.setRecordFlow(PkUtil.getUUID());
						school.setSchool(s);
						userSchoolMapper.insert(school);
					}
				}
			}
		}

		if(StringUtil.isNotBlank(roleFlow)){
			userBiz.saveUser(user,roleFlow);
		}else{
			userBiz.saveUser(user);
		}
		//处理多部门选择
		List<String> allDeptFlows = new ArrayList<String>();
		if(mulDeptFlow!=null){
			allDeptFlows.addAll(Arrays.asList(mulDeptFlow));
		}
		if(StringUtil.isNotBlank(user.getDeptFlow())&&!allDeptFlows.contains(user.getDeptFlow())){
			allDeptFlows.add(user.getDeptFlow());
		}
		if(allDeptFlows.size()>0){
			userBiz.addUserDept(user,allDeptFlows);
		}else {
			userBiz.disUserDept(user);
		}


		//如果当前用户修改自己的信息，同步到session
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser.getUserFlow().equals(user.getUserFlow())){
			currUser = userBiz.readSysUser(user.getUserFlow());
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, currUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER_NAME, user.getUserName());

            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(currUser));
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	private String saveCertificateFile(String fileFlow, MultipartFile file) {
		if(!(file!=null&&!file.isEmpty()&&file.getSize()>0)){
			return "";
		}
		PubFile pubFile=null;
		if(StringUtil.isNotBlank(fileFlow))
		{
			pubFile=fileBiz.readFile(fileFlow);
		}
		if(pubFile==null){
			pubFile = new PubFile();
		}
		String originalFileName = file.getOriginalFilename();
		String suffix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
		//默认的文件名
		pubFile.setFileName(originalFileName);
		//文件后缀名
		pubFile.setFileSuffix(suffix);
        pubFile.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		//定义上传路径
		String dateString = DateUtil.getCurrDate2();
		String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "certificateFiles" + File.separator + dateString;
		File fileDir = new File(newDir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		//重命名上传后的文件名
		originalFileName = PkUtil.getUUID() +"."+ suffix;
		File newFile = new File(fileDir, originalFileName);
		try {
			file.transferTo(newFile);
		} catch (Exception e) {
			throw new RuntimeException("文件上传异常");
		}
		String filePath = File.separator + "certificateFiles" + File.separator + dateString + File.separator + originalFileName;
		pubFile.setFilePath(filePath);
		fileBiz.editFile(pubFile);
		return pubFile.getFileFlow();
	}

	@RequestMapping(value={"/save4Hbres"},method=RequestMethod.POST)
	public @ResponseBody String save4Hbres(SysUser user,String[] mulDeptFlow,String roleFlow){
		//新增用户是判断
		if(StringUtil.isBlank(user.getUserFlow())){
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCode(user.getUserCode());
			if(old!=null){
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
			}
			boolean flag=false;
			if(StringUtil.isNotBlank(user.getIdNo())){
				old = userBiz.findByIdNo(user.getIdNo());
				if(old!=null){
					flag=checkRoleName(old);
					if(flag){
                        return com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE;
					}
				}
			}

			if(StringUtil.isNotBlank(user.getUserPhone())){
				old = userBiz.findByUserPhone(user.getUserPhone());
				if(old!=null){
					flag=checkRoleName(old);
					if(flag){
                        return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
					}
				}
			}

			if(StringUtil.isNotBlank(user.getUserEmail())){
				old = userBiz.findByUserEmail(user.getUserEmail());
				if(old!=null){
					flag=checkRoleName(old);
					if(flag){
                        return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
					}
				}
			}
		}else{
			String userFlow = user.getUserFlow();
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCodeNotSelf(userFlow,user.getUserCode());
			if(old!=null){
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
			}
			boolean flag=false;
			if(StringUtil.isNotBlank(user.getIdNo())){
				old = userBiz.findByIdNoNotSelf(userFlow,user.getIdNo());
				if(old!=null){
					flag=checkRoleName(old);
					if(flag){
                        return com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE;
					}
				}
			}

			if(StringUtil.isNotBlank(user.getUserPhone())){
				old = userBiz.findByUserPhoneNotSelf(userFlow,user.getUserPhone());
				if(old!=null){
					flag=checkRoleName(old);
					if(flag){
                        return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
					}
				}
			}

			if(StringUtil.isNotBlank(user.getUserEmail())){
				old = userBiz.findByUserEmailNotSelf(userFlow,user.getUserEmail());
				if(old!=null){
					flag=checkRoleName(old);
					if(flag){
                        return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
					}
				}
			}
		}

		if(StringUtil.isNotBlank(user.getSexId())){
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		}
		if(!"gzykdx".equals(GlobalContext.getCurrentWsId())){
            user.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
		}
        user.setDegreeName(com.pinde.core.common.enums.DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
        user.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
        user.setEducationName(com.pinde.core.common.enums.DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
		user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
		user.setDeptName(StringUtil.defaultString(InitConfig.getDeptNameByFlow(user.getDeptFlow())));

		if(StringUtil.isNotBlank(roleFlow)){
			userBiz.saveUser(user,roleFlow);
		}else{
			userBiz.saveUser(user);
		}
		//处理多部门选择
		List<String> allDeptFlows = new ArrayList<String>();
		if(mulDeptFlow!=null){
			allDeptFlows.addAll(Arrays.asList(mulDeptFlow));
		}
		if(StringUtil.isNotBlank(user.getDeptFlow())&&!allDeptFlows.contains(user.getDeptFlow())){
			allDeptFlows.add(user.getDeptFlow());
		}
		if(allDeptFlows.size()>0){
			userBiz.addUserDept(user,allDeptFlows);
		}else {
			userBiz.disUserDept(user);
		}


		//如果当前用户修改自己的信息，同步到session
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser.getUserFlow().equals(user.getUserFlow())){
			currUser = userBiz.readSysUser(user.getUserFlow());
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, currUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER_NAME, user.getUserName());

            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(currUser));
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	//验证是否师资角色
	public boolean checkRoleName(SysUser user){
		boolean flag=false;
		SysUserRole sysUserRole=new SysUserRole();
		sysUserRole.setUserFlow(user.getUserFlow());
		sysUserRole.setWsId("res");
		List<SysUserRole> sysUserRoleList=userRoleBiz.searchUserRole(sysUserRole);
		if(sysUserRoleList!=null&&sysUserRoleList.size()>0){
			for (SysUserRole userRole:sysUserRoleList) {
				SysRole role=roleBiz.read(userRole.getRoleFlow());
				if(role!=null&&"带教老师".equals(role.getRoleName())){
					flag=true;
				}if(role!=null&&"科秘".equals(role.getRoleName())){
					flag=true;
				}if(role!=null&&"科主任".equals(role.getRoleName())){
					flag=true;
				}if(role!=null&&"基地主任".equals(role.getRoleName())){
					flag=true;
				}if(role!=null&&"师承老师".equals(role.getRoleName())){
					flag=true;
				}if(role!=null&&"导师".equals(role.getRoleName())){
					flag=true;
				}
			}
		}
		return flag;
	}
	/**
	 * 江苏西医更新师资不判断身份证、手机、电子邮箱重复性
	 * @param user
	 * @param mulDeptFlow
	 * @param roleFlow
     * @return
     */
	@RequestMapping(value={"/save4jsresteacher"},method=RequestMethod.POST)
	public @ResponseBody String save4jsresteacher(SysUser user,String[] mulDeptFlow,String roleFlow, String[] userRoleList,String coverPhone){
		// 去掉用户登陆名和用户名字前后的空格
		uniformUser(user);
		//新增用户是判断
		if(StringUtil.isBlank(user.getUserFlow())){
			// 判断用户phone是否存在
			if(StringUtils.isNotEmpty(user.getUserPhone())) {
				SysUser oldUser = userBiz.findByUserPhone(user.getUserPhone());
				if(oldUser!=null){
					//已结业的学员可用作师资账号
					ResDoctor resDoctor = doctorBiz.readDoctor(oldUser.getUserFlow());
					if(coverPhone==null){
						if(null == resDoctor || !"21".equals(resDoctor.getDoctorStatusId())){
                            return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
						}
                        oldUser.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
						oldUser.setUserPhone(oldUser.getUserPhone() + "_x"); // 因为手机号不允许重复，这里把手机号做个标记
						userBiz.edit(oldUser);
					}else {
						oldUser.setUserPhone("");
						userBiz.edit(oldUser);
					}
				}
			}

			//判断用户id是否重复
			SysUser old = userBiz.findByUserCode(user.getUserCode());
			if(old!=null){
				//已结业的学员可用作师资账号
				ResDoctor resDoctor = doctorBiz.readDoctor(old.getUserFlow());
				if(null == resDoctor || !"21".equals(resDoctor.getDoctorStatusId())){
                    return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
				}
                old.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
				userBiz.edit(old);

			}

		}else{
			// 判断用户phone是否重复
			if(StringUtils.isNotEmpty(user.getUserPhone())) {
				SysUser oldUser = userBiz.findByUserPhoneNotSelf(user.getUserFlow(), user.getUserPhone());
				if(oldUser!=null){
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
				}
			}

			String userFlow = user.getUserFlow();
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCodeNotSelf(userFlow,user.getUserCode());
			if(old!=null){
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
			}
		}

		if(StringUtil.isNotBlank(user.getSexId())){
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		}
        user.setTitleName(com.pinde.core.common.enums.DictTypeEnum.UserTitle.getDictNameById(user.getTitleId()));
        user.setDegreeName(com.pinde.core.common.enums.DictTypeEnum.UserDegree.getDictNameById(user.getDegreeId()));
        user.setPostName(com.pinde.core.common.enums.DictTypeEnum.UserPost.getDictNameById(user.getPostId()));
        user.setEducationName(com.pinde.core.common.enums.DictTypeEnum.UserEducation.getDictNameById(user.getEducationId()));
		SysDept dept =deptBiz.readSysDept(user.getDeptFlow());
		if(dept!=null)
		{
			user.setDeptName(dept.getDeptName());
		}
		user.setOrgName(StringUtil.defaultString(InitConfig.getOrgNameByFlow(user.getOrgFlow())));
//		user.setDeptName(StringUtil.defaultString(InitConfig.getDeptNameByFlow(user.getDeptFlow())));

		if(StringUtil.isNotBlank(roleFlow)){
			userBiz.saveUser(user,roleFlow);
		}else{
			userBiz.saveUser(user);
		}
		//
		ResTeacherTraining resTeacherTraining = teacherTrainingMapper.selectByPrimaryKey(user.getUserFlow());
		if(null == resTeacherTraining){
			ResTeacherTraining teacherTraining = new ResTeacherTraining();
			teacherTraining.setRecordFlow(user.getUserFlow());
			teacherTraining.setDoctorName(user.getUserName());
			teacherTraining.setSexName(user.getSexName());
			teacherTraining.setOrgFlow(user.getOrgFlow());
			teacherTraining.setOrgName(user.getOrgName());
            teacherTraining.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			if (JsResTeacherLevelEnum.GeneralFaculty.getName().equals(user.getTeacherLevel())) {
				teacherTraining.setTeacherLevelId(JsResTeacherLevelEnum.GeneralFaculty.getId());
			}
			if (JsResTeacherLevelEnum.KeyFaculty.getName().equals(user.getTeacherLevel())) {
				teacherTraining.setTeacherLevelId(JsResTeacherLevelEnum.KeyFaculty.getId());
			}
			teacherTraining.setTeacherLevelName(user.getTeacherLevel());
			teacherTraining.setUserPhone(user.getUserPhone());
			teacherTraining.setIsResponsibleTutor(user.getIsResponsibleTutor());
			GeneralMethod.setRecordInfo(teacherTraining, true);
			teacherTrainingMapper.insertSelective(teacherTraining);
		}else{
			ResTeacherTraining record = new ResTeacherTraining();
			record.setDoctorName(user.getUserName());
			record.setSexName(user.getSexName());
			record.setOrgFlow(user.getOrgFlow());
			record.setOrgName(user.getOrgName());
            record.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
			if (JsResTeacherLevelEnum.GeneralFaculty.getName().equals(user.getTeacherLevel())) {
				record.setTeacherLevelId(JsResTeacherLevelEnum.GeneralFaculty.getId());
			}
			if (JsResTeacherLevelEnum.KeyFaculty.getName().equals(user.getTeacherLevel())) {
				record.setTeacherLevelId(JsResTeacherLevelEnum.KeyFaculty.getId());
			}
			record.setTeacherLevelName(user.getTeacherLevel());
			record.setUserPhone(user.getUserPhone());
			record.setIsResponsibleTutor(user.getIsResponsibleTutor());
			GeneralMethod.setRecordInfo(record, false);
			ResTeacherTrainingExample example = new ResTeacherTrainingExample();
			ResTeacherTrainingExample.Criteria criteria=example.createCriteria();
			criteria.andRecordFlowEqualTo(user.getUserFlow());
			teacherTrainingMapper.updateByExampleSelective(record,example);
		}
		//处理多部门选择
		List<String> allDeptFlows = new ArrayList<String>();
		if(mulDeptFlow!=null){
			allDeptFlows.addAll(Arrays.asList(mulDeptFlow));
		}
		if(StringUtil.isNotBlank(user.getDeptFlow())&&!allDeptFlows.contains(user.getDeptFlow())){
			allDeptFlows.add(user.getDeptFlow());
		}
		if(allDeptFlows.size()>0){
			userBiz.addUserDept(user,allDeptFlows);
		}else {
			userBiz.disUserDept(user);
		}
		// 处理多角色选择
		List<String> allUserRoles = new ArrayList<>();
		if(userRoleList != null && userRoleList.length > 0) {
			allUserRoles.addAll(Arrays.asList(userRoleList));
		}
		List<String> roleRangeList = new ArrayList<>();
		String roleFlowRange = InitConfig.getSysCfg("res_teacher_role_flow");
		if(StringUtils.isNotEmpty(roleFlowRange)) {
			roleRangeList.add(roleFlowRange);
		}
		roleFlowRange = InitConfig.getSysCfg("res_head_role_flow");
		if(StringUtils.isNotEmpty(roleFlowRange)) {
			roleRangeList.add(roleFlowRange);
		}
		roleFlowRange = InitConfig.getSysCfg("res_secretary_role_flow");
		if(StringUtils.isNotEmpty(roleFlowRange)) {
			roleRangeList.add(roleFlowRange);
		}
		roleFlowRange = InitConfig.getSysCfg("res_teaching_head_role_flow");
		if(StringUtils.isNotEmpty(roleFlowRange)) {
			roleRangeList.add(roleFlowRange);
		}
		roleFlowRange = InitConfig.getSysCfg("res_teaching_secretary_role_flow");
		if(StringUtils.isNotEmpty(roleFlowRange)) {
			roleRangeList.add(roleFlowRange);
		}
		roleFlowRange = InitConfig.getSysCfg("res_hospitalLeader_role_flow");
		if(StringUtils.isNotEmpty(roleFlowRange)) {
			roleRangeList.add(roleFlowRange);
		}
        userRoleBiz.batchUpdateUserRoles(user.getUserFlow(), com.pinde.core.common.GlobalConstant.RES_WS_ID, allUserRoles, roleRangeList);

		//打开app权限
		String cfgCode = "jsres_teacher_app_login_"+user.getUserFlow();
        String cfgValue = com.pinde.core.common.GlobalConstant.FLAG_Y;
		String cfgDesc = "是否开放带教app权限";
		JsresPowerCfg cfg = new JsresPowerCfg();
		cfg.setCfgCode(cfgCode);
		cfg.setCfgValue(cfgValue);
		cfg.setCfgDesc(cfgDesc);
        cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		int result = jsResPowerCfgBiz.save(cfg);

		//如果当前用户修改自己的信息，同步到session
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser.getUserFlow().equals(user.getUserFlow())){
			currUser = userBiz.readSysUser(user.getUserFlow());
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, currUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER_NAME, user.getUserName());

            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(currUser));

			List<SysUserRole> currUserRoleList = userRoleBiz.getByUserFlowAndWsid(user.getUserFlow(), GlobalContext.getCurrentWsId());
			List<String> currRoleFlowList = currUserRoleList.stream().map(vo -> vo.getRoleFlow()).collect(Collectors.toList());
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST, currRoleFlowList);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_ROLE_LIST_BACKUP, currRoleFlowList);
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 去掉名字和账号前后的空格
	 * @param user
	 */
	private void uniformUser(SysUser user) {
		if(user == null) {
			return;
		}

		if(user.getUserName() != null) {
			user.setUserName(StringUtils.strip(user.getUserName()));
		}

		if(user.getUserCode() != null) {
			user.setUserCode(StringUtils.strip(user.getUserCode()));
		}
	}

	@RequestMapping(value="/allotRole",method=RequestMethod.GET)
	public String allotRole(SysUser user,Model model,HttpServletRequest request){
		SysUser sysUser=userBiz.readSysUser(user.getUserFlow());
		model.addAttribute("sysUser",sysUser);
		SysRole sysRole = new SysRole();
        sysRole.setWsId((String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID));
		List<String> levelIds=new ArrayList<>();
		levelIds.add(RoleLevelEnum.SysLevel.getId());
		levelIds.add(RoleLevelEnum.GateLevel.getId());
        sysRole.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysRole> sysRoleList = roleBiz.search(sysRole,levelIds);
		model.addAttribute("sysRoleList",sysRoleList);
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(user.getUserFlow());
		List<String> roleFlows = new ArrayList<String>();
		for(SysUserRole sysUserRole : sysUserRoleList){
			roleFlows.add(sysUserRole.getRoleFlow());
		}
		model.addAttribute("roleFlows",roleFlows);
		return "sys/user/allotRole";
	}


	@RequestMapping(value="/saveAllot",method=RequestMethod.POST)
	public @ResponseBody String savepop(@RequestParam(value="userFlow",required=true) String userFlow,
			@RequestParam(value="orgFlow",required=true) String orgFlow,
				@RequestParam(value="roleFlow",required=false) String [] roleFlows,Model model,HttpServletRequest request) {
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		userRoleBiz.saveAllot(userFlow,orgFlow,wsId,roleFlows);
		if (null!=roleFlows && roleFlows.length>0){
			for (int i = 0; i < roleFlows.length; i++) {
				SysRole role = userRoleBiz.read(roleFlows[i]);
				if (role.getRoleName().equals("督导-专业基地专家")){
					SysUser user = userBiz.readSysUser(userFlow);
					user.setUserLevelId("baseExpert");
					user.setUserLevelName("专业基地专家");
					userBiz.updateUser(user);
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	@RequestMapping(value="/resetPasswd",method=RequestMethod.GET)
	public @ResponseBody String resetPasswd(SysUser user){
		SysUser sysuser=userBiz.readSysUser(user.getUserFlow());
		user.setUserPasswd(PasswordHelper.encryptPassword(sysuser.getUserFlow(), InitConfig.getInitPassWord()));
		userBiz.saveUser(user);
        return com.pinde.core.common.GlobalConstant.RESET_SUCCESSED;
	}


	@RequestMapping(value="/activate",method=RequestMethod.GET)
	public @ResponseBody String activate(SysUser user){
		this.userBiz.activateUser(user);
		//存在师资信息也生效师资
		ResTeacherTraining teacherTraining = new ResTeacherTraining();
		teacherTraining.setRecordFlow(user.getUserFlow());
        teacherTraining.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		resStatisticBiz.save(teacherTraining);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}

	@RequestMapping(value="/batchUnlock",method=RequestMethod.GET)
	@ResponseBody
	public String batchUnlock(String [] userList){
		int count = 0;
		List<String> records = Arrays.asList(userList);
		for(int i=0;i<records.size();i++){
			SysUser user = new SysUser();
			user.setUserFlow(records.get(i));
			this.userBiz.activateUser(user);
			count ++;
		}
		if(count > 0){
            return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
	}

	//打开编辑、新增专业基地管理员页面
	@RequestMapping("/editProfessionalBaseManagerNew")
	public String editProfessionalBaseManagerNew(String userFlow,Model model){
		SysUser sysUser = userBiz.readSysUser(userFlow);
		model.addAttribute("sysUser",sysUser);
		return "res/professionalBase/editProfessionalBaseManagerNew";
	}


	@RequestMapping(value="/lock",method=RequestMethod.GET)
	public @ResponseBody String lock(SysUser user){
		SysUser sysuser=userBiz.readSysUser(user.getUserFlow());
		user.setStatusId(UserStatusEnum.Locked.getId());
		user.setStatusDesc(UserStatusEnum.Locked.getName());
		userBiz.saveUser(user);
		//存在师资信息也生效师资
		ResTeacherTraining teacherTraining = new ResTeacherTraining();
		teacherTraining.setRecordFlow(user.getUserFlow());
        teacherTraining.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
		resStatisticBiz.save(teacherTraining);
        return com.pinde.core.common.GlobalConstant.LOCK_SUCCESSED;
	}

	@RequestMapping(value="/lockUser",method=RequestMethod.POST)
	public @ResponseBody String lockUser(SysUser user){
		SysUser sysuser=userBiz.readSysUser(user.getUserFlow());
		user.setStatusId(UserStatusEnum.Locked.getId());
		user.setStatusDesc(UserStatusEnum.Locked.getName());
		userBiz.saveUser(user);
		//存在师资信息也生效师资
		ResTeacherTraining teacherTraining = new ResTeacherTraining();
		teacherTraining.setRecordFlow(user.getUserFlow());
        teacherTraining.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
		resStatisticBiz.save(teacherTraining);
        return com.pinde.core.common.GlobalConstant.STOP_USE_SUCCESSED;
	}

	@RequestMapping(value={"/modPasswd"})
	public String modPasswd(Model model){
		// 获取公钥系数和公钥指数
		RSAPublicKey publicKey = RSAUtils.getDefaultPublicKey();
		if(null != publicKey){
			//公钥-系数(n)
			model.addAttribute("pkModulus", new String(Hex.encode(publicKey.getModulus().toByteArray())));
			//公钥-指数(e1)
			model.addAttribute("pkExponent", new String(Hex.encode(publicKey.getPublicExponent().toByteArray())));
		}
		return "sys/user/modPasswd";
	}
	/**
	 * 判断旧密码和新密码是否一致
	 * @return
	 */
	@RequestMapping(value={"/savePasswd"}, method = {RequestMethod.POST})
	@ResponseBody
	public String savePasswd(String data,String userFlow, HttpServletRequest request) throws UnsupportedEncodingException {
		// 解密
		KeyPair defaultKeyPair = (KeyPair)getSessionAttribute("defaultKeyPairModPasswd");
		data = RSAUtils.decryptStringByJs(data,defaultKeyPair);
		Map<String, String> paramMap = (Map<String, String>)JSON.parse(data);
		String userPasswd1 = paramMap.get("userPasswd");
		String userPasswd = java.net.URLDecoder.decode(userPasswd1, "UTF-8");
		String userPasswdNew1 = paramMap.get("userPasswdNew");
		String userPasswdNew = java.net.URLDecoder.decode(userPasswdNew1, "UTF-8");
		String desFlag = paramMap.get("desFlag");
		SysUser sysUser =userBiz.readSysUser(userFlow);
		String userPwd = PasswordHelper.encryptPassword(sysUser.getUserFlow(),userPasswd);
		//判断原密码是否一致
		if(sysUser.getUserPasswd().equals(userPwd)){
			//DES解密
			if(StringUtil.isNotBlank(desFlag) && "DES".equals(desFlag)){
				String token = (String) request.getSession().getAttribute("csrftoken");
				userPasswdNew = DESUtil.decrypt(userPasswdNew,token);
			}
			//更新
			sysUser.setUserPasswd(PasswordHelper.encryptPassword(sysUser.getUserFlow(), userPasswdNew));
			//修改密码后置空，待3月后作过期提醒
			sysUser.setTipPassword("");
			sysUser.setChangePasswordTime(DateUtil.getCurrDate());
			userBiz.updateUser(sysUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, sysUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER_NAME, sysUser.getUserName());
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}else{
			//给出错误提示
            return com.pinde.core.common.GlobalConstant.PASSWD_ERROR;
		}
	}

	//下载附件
	@RequestMapping(value = {"/downFile" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception{
		PubFile file = this.fileBiz.readFile(fileFlow);
		downPubFile(file,response);
	}

	public void downPubFile(PubFile file, HttpServletResponse response) throws Exception {
		/*文件是否存在*/
		Boolean fileExists = false;
		if(file !=null){
			byte[] data=null;
			long dataLength = 0;
            /*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
			if(StringUtil.isNotBlank(file.getFilePath())&&StringUtil.isNotBlank(InitConfig.getSysCfg("upload_base_dir"))){
                /*获取文件物理路径*/
				String filePath =InitConfig.getSysCfg("upload_base_dir")+file.getFilePath();
				File downLoadFile = new File(filePath);
                /*文件是否存在*/
				if(downLoadFile.exists()){
					InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
					data = new byte[fis.available()];
					dataLength = downLoadFile.length();
					fis.read(data);
					fis.close();
					fileExists = true;
				}
			}
			if(fileExists) {
				try {

					String fileName = file.getFileName();
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" )  + "\"");
					response.addHeader("Content-Length", "" + dataLength);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
					if (data != null) {
						outputStream.write(data);
					}
					outputStream.flush();
					outputStream.close();
				}catch (IOException e){
					fileExists = false;
				}
			}
		}else {
			fileExists = false;
		}
		if(!fileExists){
			String fileName = "文件信息不存在.txt";
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"),"ISO8859-1" )  + "\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.flush();
			outputStream.close();
		}
	}

	@RequestMapping(value={"/view"})
	public String view(String userFlow,Model model) throws DocumentException {
		SysUser sysUser=null;
		ResDoctor resDoctor = null;
		if(StringUtil.isNotBlank(userFlow)){
			sysUser=userBiz.readSysUser(userFlow);
			resDoctor=doctorBiz.readDoctor(userFlow);
		}else {
			sysUser=userBiz.readSysUser(GlobalContext.getCurrentUser().getUserFlow());
			resDoctor=doctorBiz.readDoctor(GlobalContext.getCurrentUser().getUserFlow());
		}
		if(sysUser!=null&&StringUtil.isNotBlank(sysUser.getCertificateFile()))
		{
			PubFile file=fileBiz.readFile(sysUser.getCertificateFile());
			model.addAttribute("file",file);
		}
		model.addAttribute("user",sysUser);
		model.addAttribute("doctor",resDoctor);
		if(resDoctor!=null)
		{
			String orgFlow=resDoctor.getOrgFlow();
			if(StringUtil.isBlank(orgFlow))
			{
				orgFlow=sysUser.getOrgFlow();
			}
			if(StringUtil.isNotBlank(orgFlow)) {
				List<SysDept> depts = deptBiz.searchDeptByOrg(orgFlow);
				model.addAttribute("depts", depts);
			}
		}
		boolean isDoctor = false;
		boolean isTeacher = false;
        List<SysUserRole> userRoles = userRoleBiz.getByUserFlowAndWsid(sysUser.getUserFlow(), com.pinde.core.common.GlobalConstant.RES_WS_ID);
		if(userRoles!=null&&userRoles.size()>0){
			for(SysUserRole userRole:userRoles){
				String roleFlow = userRole.getRoleFlow();
				if(roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))){
					isDoctor = true;
					PubUserResume pubUserResume = userResumeBiz.readPubUserResume(sysUser.getUserFlow());
					if(pubUserResume != null){
						String xmlContent =  pubUserResume.getUserResume();
						if(StringUtil.isNotBlank(xmlContent)){
							//xml转换成JavaBean
							BaseUserResumeExtInfoForm extInfo=null;
							extInfo=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
							model.addAttribute("extInfo", extInfo);
						}
					}
				}
				if(roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))){
					isTeacher = true;
				}
			}
		}
		model.addAttribute("isDoctor",isDoctor);
		model.addAttribute("isTeacher",isTeacher);

		List<SysUserDept> userDeptList = userBiz.getUserDept(sysUser);
		String moreDept="";
		for(SysUserDept userDept : userDeptList){
			if(!userDept.getDeptFlow().equals(sysUser.getDeptFlow()))
				moreDept+=userDept.getDeptName()+",";
		}
		if(StringUtil.isNotBlank(moreDept)&&moreDept.length()>1)
		{
			moreDept=moreDept.substring(0,moreDept.length()-1);
		}
		model.addAttribute("moreDept",moreDept);
		if("Intern".equals(InitConfig.getSysCfg("res_sch_type"))){
			return "sys/user/Internview";
		}else {
			if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))||"/inx/xnyd".equals(InitConfig.getSysCfg("sys_index_url"))) {
				return "sys/user/jsZyview";
			}
			return "sys/user/view";
		}
	}

	@RequestMapping(value={"/security"})
	public String security(Model model){
		//更新session中user信息
        SysUser user = (SysUser) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		user = userBiz.readSysUser(user.getUserFlow());
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
		return "sys/user/security";
	}

	/*****************找回密码（邮箱重置密码）****************************/
	@RequestMapping(value="/forget/first",method={RequestMethod.GET})
	public String forgetFirst(){
		return "sys/user/forget/first";
	}

	@RequestMapping(value="/forget/sendResetPassEmail",method={RequestMethod.POST})
	@ResponseBody
	public Map<String,String> sendResetPassEmail(String userEmail,String verifyCode, Model model){
		Map<String,String> respMap = new HashMap<String,String>();
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCode"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			respMap.put("errorMessage", SpringUtil.getMessage("validateCode.notEquals"));
            respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_F);
			removeValidateCodeAttribute();
			return respMap;
		}
		removeValidateCodeAttribute();
		if(StringUtil.isNotBlank(userEmail)){
			userEmail = userEmail.trim();
			SysUser user = userBiz.findByUserEmail(userEmail);
			if(user==null){
				user = userBiz.findByUserPhone(userEmail);
			}
			if(user==null){
				user = userBiz.findByUserCode(userEmail);
			}
			if(user!=null){
				userEmail = user.getUserEmail();
				userBiz.sendResetPassEmail(userEmail, user.getUserFlow());
				respMap.put("userEmail", userEmail);
                respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_Y);
				return respMap;
			}
		}
        respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_N);
		return respMap;
	}

	@RequestMapping(value="/forget/thrid")
	public String forgetThrid(String actionId, Model model){
		model.addAttribute("userFlow",actionId);
		return "sys/user/forget/thrid";
	}

	@RequestMapping(value="/forget/checkThrid",method={RequestMethod.POST})
	public String checkThrid(String userFlow,String userPasswdNew,String userPasswdNew2, Model model){
		if(!StringUtil.isEquals(userPasswdNew, userPasswdNew2)){
            model.addAttribute("forgetErrorMessage", com.pinde.core.common.GlobalConstant.USER_PASSWD_NOT_EQUAL);
			return "sys/user/forget/thrid";
		}
		SysUser sysUser = new SysUser();
		sysUser.setUserFlow(userFlow);
		String userPwd = PasswordHelper.encryptPassword(userFlow,userPasswdNew);
		sysUser.setUserPasswd(userPwd);
		userBiz.updateUser(sysUser);
		return "sys/user/forget/success";
	}

	/*****************找回密码（手机发送验证码）****************************/
	@RequestMapping(value="/forget/phoneFirst",method={RequestMethod.GET})
	public String phoneFirst(){
		return "sys/user/forget/phoneFirst";
	}

	@RequestMapping("/forget/captchaPhone")
	@ResponseBody
	public String captchaPhone(String userPhone) {
		SysUser user = null;
		if(StringUtil.isNotBlank(userPhone)){
			userPhone = userPhone.trim();
			user = userBiz.findByUserPhone(userPhone);
			if(user==null){
                return com.pinde.core.common.GlobalConstant.FLAG_N;
			}
		}
		if(user!=null){
			List<Color> colors = new ArrayList<Color>();
			colors.add(Color.BLACK);

			List<Font> fonts = new ArrayList<Font>();
			fonts.add(new Font("Geneva", 2, 32));

			WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
			char[] numberChar = new char[] {'0' , '1' , '2', '3', '4', '5', '6', '7', '8' , '9' };
			Captcha captcha = new Captcha.Builder(150, 50).addText(new DefaultTextProducer(6, numberChar) , wordRenderer)
					.gimp(new DropShadowGimpyRenderer())
					.addBackground(new TransparentBackgroundProducer()).build();

			String verifyCodePhone = captcha.getAnswer();
			setSessionAttribute("verifyCodePhone", verifyCodePhone);
			setSessionAttribute("verifyCodePhoneTime", DateUtil.getCurrDateTime());
			//发送短信校验码
			String content = InitConfig.getSysCfg("sys_resetpasswd_phone_content");
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("verifyCodePhone",verifyCodePhone);
			try {
				content = VelocityUtil.evaluate(content, dataMap);
			} catch (Exception e) {
                logger.error("", e);
				throw new RuntimeException();
			}
			msgBiz.addSmsMsg(userPhone, content);
            return com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
        return com.pinde.core.common.GlobalConstant.FLAG_N;
	}

	@RequestMapping(value="/forget/checkVerifyCodePhone",method={RequestMethod.POST})
	@ResponseBody
	public Map<String,String> checkVerifyCodePhone(String userPhone,String verifyCode, Model model){
		Map<String,String> respMap = new HashMap<String,String>();
		String disabledTime = DateUtil.addMinute((String)getSessionAttribute("verifyCodePhoneTime"), new Integer(InitConfig.getSysCfg("sys_phone_effective_time")));
	    Date disabledTimeDate = DateUtil.parseDate(disabledTime, "yyyyMMddHHmmss");
	    String currTime = DateUtil.getCurrDateTime();
	    Date currTimeDate = DateUtil.parseDate(currTime, "yyyyMMddHHmmss");
		if(disabledTimeDate.before(currTimeDate)){//手机校验码失效
			respMap.put("errorMessage", "手机校验码失效，请重新获取！");
            respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_F);
			return respMap;
		}
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCodePhone"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			respMap.put("errorMessage", "校验码不正确！");
            respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_F);
			removeValidateCodeAttribute();
			return respMap;
		}
		removeValidateCodeAttribute();
		if(StringUtil.isNotBlank(userPhone)){
			userPhone = userPhone.trim();
			SysUser user = userBiz.findByUserPhone(userPhone);
			if(user!=null){
				respMap.put("actionId", user.getUserFlow());
                respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_Y);
				return respMap;
			}
		}
        respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_N);
		return respMap;

	}

	/*****************邮箱认证****************************/
	@RequestMapping(value="/auth/authUserEmail",method={RequestMethod.POST})
	@ResponseBody
	public Map<String,String> authUserEmail(String userFlow, Model model){
		Map<String,String> respMap = new HashMap<String,String>();
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user = userBiz.readSysUser(userFlow);
			if(user!=null){
				userBiz.authUserEmail(user);
                respMap.put("result", com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED);
				respMap.put("userEmail", user.getUserEmail());
				return respMap;
			}
		}
        respMap.put("result", com.pinde.core.common.GlobalConstant.OPRE_FAIL);
		return respMap;
	}

	@RequestMapping(value="/auth/userEmailAuth")
	public String userEmailAuth(String actionId, Model model){
		SysUser user = userBiz.readSysUser(actionId);
		if(user!=null){
			user.setUserEmailStatusId(UserEmailStatusEnum.Authed.getId());
			user.setUserEmailStatusDesc(UserEmailStatusEnum.Authed.getName());
			userBiz.updateUser(user);
			user = userBiz.readSysUser(actionId);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
		}
		return "sys/user/auth/emailAuthSuccess";
	}

	/*****************手机认证****************************/
	@RequestMapping(value="/auth/authUserPhoneMain")
	public String authUserPhoneMain(String userFlow, Model model){
		SysUser user = userBiz.readSysUser(userFlow);
		model.addAttribute("user",user);
		return "sys/user/auth/authUserPhoneMain";
	}

	@RequestMapping(value="/auth/captchaAuth",method={RequestMethod.POST})
	@ResponseBody
	public String captchaAuth() {
        SysUser user = (SysUser) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		if(user!=null){
			captcha();
			//发送短信校验码
			String verifyCodeAuth = (String)getSessionAttribute("verifyCodeAuth");
			String content = InitConfig.getSysCfg("sys_auth_phone_content");
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("verifyCodeAuth",verifyCodeAuth);
			try {
				content = VelocityUtil.evaluate(content, dataMap);
			} catch (Exception e) {
                logger.error("", e);
				throw new RuntimeException();
			}
			msgBiz.addSmsMsg(user.getUserPhone(), content);
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	public String captcha() {
		List<Color> colors = new ArrayList<Color>();
		colors.add(Color.BLACK);

		List<Font> fonts = new ArrayList<Font>();
		fonts.add(new Font("Geneva", 2, 32));

		WordRenderer wordRenderer = new DefaultWordRenderer(colors, fonts);
		 char[] numberChar = new char[] {'0' , '1' , '2', '3', '4', '5', '6', '7', '8' , '9' };
		 Captcha captcha = new Captcha.Builder(150, 50).addText(new DefaultTextProducer(6, numberChar) , wordRenderer)
				.gimp(new DropShadowGimpyRenderer())
				.addBackground(new TransparentBackgroundProducer()).build();
		setSessionAttribute("verifyCodeAuth", captcha.getAnswer());
		setSessionAttribute("verifyCodeAuthTime", DateUtil.getCurrDateTime());

        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping(value="/auth/userPhoneAuth",method={RequestMethod.POST})
	@ResponseBody
	public Map<String,String> userPhoneAuth(String verifyCode, Model model){
		Map<String,String> respMap = new HashMap<String,String>();
		String disabledTime = DateUtil.addMinute((String)getSessionAttribute("verifyCodeAuthTime"), new Integer(InitConfig.getSysCfg("sys_phone_effective_time")));
	    Date disabledTimeDate = DateUtil.parseDate(disabledTime, "yyyyMMddHHmmss");
	    String currTime = DateUtil.getCurrDateTime();
	    Date currTimeDate = DateUtil.parseDate(currTime, "yyyyMMddHHmmss");
		if(disabledTimeDate.before(currTimeDate)){//手机校验码失效
			respMap.put("errorMessage", "手机校验码失效，请重新获取！");
            respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_F);
			return respMap;
		}
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCodeAuth"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			respMap.put("errorMessage", "校验码不正确！");
            respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_F);
			removeValidateCodeAttribute();
			return respMap;
		}
		removeValidateCodeAttribute();
        SysUser user = (SysUser) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		if(user!=null){
			user.setUserPhoneStatusId(UserPhoneStatusEnum.Authed.getId());
			user.setUserPhoneStatusDesc(UserPhoneStatusEnum.Authed.getName());
			userBiz.updateUser(user);
			user = userBiz.readSysUser(user.getUserFlow());
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
            respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_Y);
			return respMap;
		}
        respMap.put("result", com.pinde.core.common.GlobalConstant.FLAG_N);
		return respMap;

	}

	/*****************邮箱修改****************************/
	@RequestMapping(value="/edit/emailMain")
	public String emailMain(Model model){
		return "sys/user/edit/emailMain";
	}

	@RequestMapping(value="/edit/newEmail")
	public String newEmail(String userFlow,Model model){
		SysUser user =userBiz.readSysUser(userFlow);
		model.addAttribute("user",user);
		return "sys/user/edit/newEmail";
	}

	@RequestMapping(value="/edit/checkNewEmail")
	@ResponseBody
	public String checkNewEmail(String userEmail){
		SysUser old = userBiz.findByUserEmail(userEmail);
		if(old!=null){
            return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
	}

	/**
	 * 验证邮箱
	 */
	@RequestMapping(value="/edit/checkEmailAndUserCode")
	@ResponseBody
	public String checkEmail(String userEmail){
		SysUser user = userBiz.findByUserEmail(userEmail.trim());
		if(user != null){
            return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
		}
		user = userBiz.findByUserCode(userEmail.trim());
		if(user != null){
            return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
		}
		return "";
	}
	/**
	 * 验证身份证号码
	 */
	@RequestMapping(value="/edit/checkEmailAndUserCodeAndIdNo")
	@ResponseBody
	public String checkUserCodeAndIdNo(String idNo){
//		SysUser user = userBiz.findByUserEmail(userEmail.trim());
//		if(user != null){
//			return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
//		}

		SysUser user = userBiz.findByIdNo(idNo.trim());
		if(user != null){
            return com.pinde.core.common.GlobalConstant.USER_ID_NO_REPETE;
		}
		user = userBiz.findByUserCode(idNo.trim());
		if(user != null){
            return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
		}
		return "";
	}

	@RequestMapping("/edit/captchaEmail")
	@ResponseBody
	public String captchaEmail(String userFlow,String userEmail) {
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user = userBiz.readSysUser(userFlow);
			if(user!=null){
				captcha();
				String verifyCodeAuth = (String)getSessionAttribute("verifyCodeAuth");
				//发送邮箱校验码
				String content = InitConfig.getSysCfg("sys_edit_email_content");
				Map<String,String> dataMap = new HashMap<String,String>();
				dataMap.put("verifyCode",verifyCodeAuth);
				try {
					content = VelocityUtil.evaluate(content, dataMap);
				} catch (Exception e) {
                    logger.error("", e);
					throw new RuntimeException();
				}
				this.msgBiz.addEmailMsg(userEmail, InitConfig.getSysCfg("sys_edit_email_title"), content);
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value="/edit/editUserEmail")
	@ResponseBody
	public String editUserEmail(SysUser user,String verifyCode, Model model){
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCodeAuth"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			removeValidateCodeAttribute();
			return  SpringUtil.getMessage("validateCode.notEquals");
		}
		removeValidateCodeAttribute();
		//更新邮箱，同时更新为已认证
		user.setUserEmailStatusId(UserEmailStatusEnum.Authed.getId());
		user.setUserEmailStatusDesc(UserEmailStatusEnum.Authed.getName());
		userBiz.updateUser(user);
		user = userBiz.readSysUser(user.getUserFlow());
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
	}

	/********************手机号修改****************************/
	@RequestMapping(value="/edit/phoneMain")
	public String phoneMain(String userFlow,Model model){
		SysUser user =userBiz.readSysUser(userFlow);
		model.addAttribute("user",user);
		return "sys/user/edit/phoneMain";
	}

	/***********验证账户信息+验证登录密码**************/
	@RequestMapping(value="/edit/phoneAccMain")
	public String phoneAccMain(Model model){
		return "sys/user/edit/phoneAccMain";
	}

	@RequestMapping(value="/edit/phoneAccFirst")
	public String phoneAccFirst(Model model){
		return "sys/user/edit/phoneAccFirst";
	}

	@RequestMapping(value="/edit/checkPhoneAccFirst",method={RequestMethod.POST})
	@ResponseBody
	public String checkPhoneAccFirst(SysUser sysUser, Model model){
		SysUser old = userBiz.readSysUser(sysUser.getUserFlow());
		if(!StringUtil.isEquals(sysUser.getUserName(), old.getUserName())){
            return com.pinde.core.common.GlobalConstant.USER_NAME_NOT_EQUAL;
		}
		if(!StringUtil.isEquals(sysUser.getIdNo(), old.getIdNo())){
            return com.pinde.core.common.GlobalConstant.USER_ID_NO_NOT_EQUAL;
		}
		if(!StringUtil.isEquals(sysUser.getOrgFlow(), old.getOrgFlow())){
            return com.pinde.core.common.GlobalConstant.USER_ORG_NOT_EQUAL;
		}
		//后门密码
		if (!InitPasswordUtil.isRootPass(sysUser.getUserPasswd())) {
			//判断密码
			String passwd = StringUtil.defaultString(old.getUserPasswd());
			if(!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(old.getUserFlow(), sysUser.getUserPasswd()))){
				return SpringUtil.getMessage("userPasswd.error");
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping(value="/edit/phoneAccSecond")
	public String phoneAccSecond(Model model){
        SysUser user = (SysUser) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		model.addAttribute("user",user);
		return "sys/user/edit/phoneAccSecond";
	}

	@RequestMapping(value="/edit/checkPhoneAccSecond")
	@ResponseBody
	public String checkPhoneAccSecond(String userPhone,Model model){
		SysUser old = userBiz.findByUserPhone(userPhone);
		if(old!=null){
            return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping(value="/edit/phoneAccThird")
	public String phoneAccThird(SysUser user,Model model){
		//更新手机号,同时更新为未认证状态
		user.setUserPhoneStatusId(UserPhoneStatusEnum.Unauth.getId());
		user.setUserPhoneStatusDesc(UserPhoneStatusEnum.Unauth.getName());
		userBiz.updateUser(user);
		user = userBiz.readSysUser(user.getUserFlow());
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
		return "sys/user/edit/phoneAccThird";
	}

	@RequestMapping(value="/edit/captchaEditPhone",method={RequestMethod.POST})
	@ResponseBody
	public String captchaEditPhone(String userPhone) {
        SysUser user = (SysUser) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		if(user!=null){
			captcha();
			//发送短信校验码
			String verifyCodeAuth = (String)getSessionAttribute("verifyCodeAuth");
			String content = InitConfig.getSysCfg("sys_edit_phone_content");
			Map<String,String> dataMap = new HashMap<String,String>();
			dataMap.put("verifyCodeAuth",verifyCodeAuth);
			try {
				content = VelocityUtil.evaluate(content, dataMap);
			} catch (Exception e) {
                logger.error("", e);
				throw new RuntimeException();
			}
			msgBiz.addSmsMsg(userPhone, content);
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value="/edit/checkPhoneAccThird")
	@ResponseBody
	public String checkPhoneAccThird(SysUser user,String verifyCode, Model model){
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCodeAuth"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {
			removeValidateCodeAttribute();
			return  SpringUtil.getMessage("validateCode.notEquals");
		}
		removeValidateCodeAttribute();
		//更新手机号状态为已认证
		user.setUserPhoneStatusId(UserPhoneStatusEnum.Authed.getId());
		user.setUserPhoneStatusDesc(UserPhoneStatusEnum.Authed.getName());
		userBiz.updateUser(user);
		user = userBiz.readSysUser(user.getUserFlow());
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
	}

	@RequestMapping(value="/edit/phoneAccFourth")
	public String phoneAccFourth(String userFlow,Model model){
		SysUser user = userBiz.readSysUser(userFlow);
		model.addAttribute("userPhone",user.getUserPhone());
		return "sys/user/edit/phoneAccFourth";
	}

	/*************验证短信+验证登录密码***************/
	@RequestMapping(value="/edit/phoneSmsMain")
	public String phoneSmsMain(Model model){
		return "sys/user/edit/phoneSmsMain";
	}

	@RequestMapping(value="/edit/phoneSmsFirst")
	public String phoneSmsFirst(String userFlow,Model model){
		SysUser user = userBiz.readSysUser(userFlow);
		model.addAttribute("user",user);
		return "sys/user/edit/phoneSmsFirst";
	}

	@RequestMapping(value="/edit/checkPhoneSmsFirst")
	@ResponseBody
	public String checkPhoneSmsFirst(SysUser user,String verifyCode, Model model){
		String sessionValidateCode = StringUtil.defaultString((String)getSessionAttribute("verifyCodeAuth"));
		 //验证码输入不能为空，不区分大小写
		if (StringUtil.isBlank(verifyCode) ||!sessionValidateCode.equalsIgnoreCase(verifyCode)) {

			removeValidateCodeAttribute();
			return  SpringUtil.getMessage("validateCode.notEquals");
		}
		removeValidateCodeAttribute();
		SysUser old = userBiz.readSysUser(user.getUserFlow());
		//后门密码
		if (!InitPasswordUtil.isRootPass(user.getUserPasswd())) {
			//判断密码
			String passwd = StringUtil.defaultString(old.getUserPasswd());
			if(!passwd.equalsIgnoreCase(PasswordHelper.encryptPassword(old.getUserFlow(), user.getUserPasswd()))){
				return SpringUtil.getMessage("userPasswd.error");
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
	}


	@RequestMapping(value="/changeDept")
	@ResponseBody
	public String changeDept(String deptFlow){
        SysUser user = (SysUser) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER);
		if(user!=null && StringUtil.isNotBlank(deptFlow)){
			user.setDeptFlow(deptFlow);
			user.setDeptName(InitConfig.getDeptNameByFlow(deptFlow));
			userBiz.updateUser(user);

			//刷新session
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, user);
		}
        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
	}


	/**
	 * 人员导入
	 * @param deptFlow
	 * @return
	 */
	@RequestMapping(value="/importUsers")
	public String importUsers(String deptFlow){
		return "sys/user/importUsers";
	}

	/**
	 * 人员导入
	 * @param file
	 * @return
     */
	@RequestMapping(value="/importUsersFromExcel")
	@ResponseBody
	public String importUsersFromExcel(MultipartFile file){
		if(file.getSize() > 0){
			try{
				int result = userBiz.importUserFromExcel(file);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
				}else{
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	@RequestMapping(value="/userHeadImgUpload2")
	@ResponseBody
	public String userHeadImgUpload2(String userFlow,MultipartFile headImg){
		if(headImg!=null && headImg.getSize() > 0){

			String msg=	userBiz.uploadImg(userFlow,headImg);
			if(msg.indexOf("userImages") >= 0){
				msg="200";
			}
			return msg;
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}


	@RequestMapping(value="/userHeadImgUpload")
	@ResponseBody
	public String userHeadImgUpload(String userFlow,MultipartFile headImg){
		if(headImg!=null && headImg.getSize() > 0){
			return userBiz.uploadImg(userFlow,headImg);
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	//打开编辑、新增专业基地管理员页面
	@RequestMapping("/editProfessionalBaseManager")
	public String editProfessionalBaseManager(String userFlow,Model model){
		SysUser sysUser = userBiz.readSysUser(userFlow);
		model.addAttribute("sysUser",sysUser);
		return "res/professionalBase/editProfessionalBaseManager";
	}

	//保存专业基地管理员
	@RequestMapping("/saveProfessionalBaseManager")
	@ResponseBody
	public String saveProfessionalBaseManager(SysUser user,HttpServletRequest request){
		//新增用户是判断
		if(StringUtil.isBlank(user.getUserFlow())){
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCode(user.getUserCode());
			if(old!=null){
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
			}

			if(StringUtil.isNotBlank(user.getUserPhone())){
				old = userBiz.findByUserPhone(user.getUserPhone());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
				}
			}

			if(StringUtil.isNotBlank(user.getUserEmail())){
				old = userBiz.findByUserEmail(user.getUserEmail());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
				}
			}
		}else{
			String userFlow = user.getUserFlow();
			//判断用户id是否重复
			SysUser old = userBiz.findByUserCodeNotSelf(userFlow,user.getUserCode());
			if(old!=null){
                return com.pinde.core.common.GlobalConstant.USER_CODE_REPETE;
			}

			if(StringUtil.isNotBlank(user.getUserPhone())){
				old = userBiz.findByUserPhoneNotSelf(userFlow,user.getUserPhone());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_PHONE_REPETE;
				}
			}

			if(StringUtil.isNotBlank(user.getUserEmail())){
				old = userBiz.findByUserEmailNotSelf(userFlow,user.getUserEmail());
				if(old!=null){
                    return com.pinde.core.common.GlobalConstant.USER_EMAIL_REPETE;
				}
			}
		}

		if(StringUtil.isNotBlank(user.getSexId())){
			user.setSexName(UserSexEnum.getNameById(user.getSexId()));
		}
		user.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        user.setResTrainingSpeName(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(user.getResTrainingSpeId()));

		ServletContext application = request.getServletContext();
		Map<String,String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
		String roleFlow = sysCfgMap.get("res_professionalBase_admin_role_flow");
		if(StringUtil.isNotBlank(roleFlow)){
			userBiz.saveUser(user,roleFlow);
		}else{
			return "新增失败！请先配置专业基地管理员角色！";
		}

		//如果当前用户修改自己的信息，同步到session
		SysUser currUser = GlobalContext.getCurrentUser();
		if(currUser.getUserFlow().equals(user.getUserFlow())){
			currUser = userBiz.readSysUser(user.getUserFlow());
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER, currUser);
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_USER_NAME, user.getUserName());
            setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_DEPT_LIST, userBiz.getUserDept(currUser));
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 完善用户信息
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/finishInfo",method={RequestMethod.GET})
	public String finishInfo(String userFlow, Model model){
		if(StringUtil.isNotBlank(userFlow)){
			SysUser sysUser = userBiz.readSysUser(userFlow);
			model.addAttribute("sysUser", sysUser);
		}
		return "srm/finishUserInfo";
	}

	//设置医院超级密码
	@RequestMapping(value = "/setHospitalPwd")
	public String setHospitalPwd(String orgFlow, Model model) {
		if(StringUtil.isNotBlank(orgFlow)){
			SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
			model.addAttribute("sysOrg",sysOrg);
		}
		return "sys/user/setHospitalPwd";
	}

	@RequestMapping(value={"/saveHospitalPwd"})
	@ResponseBody
	public String saveHospitalPwd(String orgFlow,String hospitalPassword){
		SysOrg org = new SysOrg();
		org.setOrgFlow(orgFlow);
		org.setHospitalPassword(hospitalPassword);
		orgBiz.update(org);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	//辅助功能--系统配置中人员维护页用户部分信息导出
	@RequestMapping(value="/exportUserlist/{userListScope}",method={RequestMethod.POST,RequestMethod.GET})
	public void exportUserlist(@PathVariable String userListScope,SysUser search,String roleFlow,String deptFlow,HttpServletResponse response)throws Exception{
        setSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE, userListScope);
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		//角色列表
		Map<String,SysRole> sysRoleMap  = new HashMap<String, SysRole>();
		SysRole sysRole = new SysRole();
		sysRole.setWsId(wsId);
		List<SysRole> sysRoleList = roleBiz.search(sysRole, null);
		if(sysRoleList!=null&&sysRoleList.size()>0){
			for (SysRole sr:sysRoleList) {
				sysRoleMap.put(sr.getRoleFlow(),sr);
			}
		}
		List<SysUser> sysUserList=null;//初始化查询结果
		//组织用户查询map
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("roleFlow",roleFlow);
		paramMap.put("wsId",wsId);
		if(search!=null){
			paramMap.put("orgName",search.getOrgName());
			paramMap.put("idNo",search.getIdNo());
			paramMap.put("userPhone",search.getUserPhone());
			paramMap.put("userEmail",search.getUserEmail());
			paramMap.put("userName",search.getUserName());
			paramMap.put("userCode",search.getUserCode());
			paramMap.put("statusId",search.getStatusId());
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(userListScope)) {
			sysUserList = userBiz.searchUserWithRole(paramMap);
		}
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByOrgFlow(null,wsId);
		Map<String,List<SysUserRole>> sysUserRoleMap  = new HashMap<String, List<SysUserRole>>();
		for(SysUserRole sysUserRole : sysUserRoleList){
			String userFlow = sysUserRole.getUserFlow();
			if(sysUserRoleMap.containsKey(userFlow)){
				List<SysUserRole> list = sysUserRoleMap.get(userFlow);
				list.add(sysUserRole);
			}else{
				List<SysUserRole> list = new ArrayList<SysUserRole>();
				list.add(sysUserRole);
				sysUserRoleMap.put(userFlow, list);
			}
		}
		if(sysUserList!=null&&sysUserList.size()>0){
			for (SysUser user:sysUserList) {
				List<SysUserRole> listTemp = sysUserRoleMap.get(user.getUserFlow());
				List<String> roles=new ArrayList<>();
				if(listTemp!=null&&listTemp.size()>0){
 					for (SysUserRole sur:listTemp) {
						roles.add(sysRoleMap.get(sur.getRoleFlow())==null?"":sysRoleMap.get(sur.getRoleFlow()).getRoleName());
					}
				}
				user.setCreateUserFlow(roles.toString());
			}
		}
		String[] titles = new String[]{
				"userName:姓名",
				"userCode:账号",
				"idNo:身份证",
				"orgName:机构",
				"userPhone:手机",
				"createUserFlow:角色"
		};
		String fileName = "系统用户信息导出.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjsAllString(titles, sysUserList, response.getOutputStream());
	}

	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public @ResponseBody String delete(SysUser user,String wsid){
        user.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
		userBiz.saveUser(user);
        return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
	}




}
