package com.pinde.sci.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pinde.core.commom.enums.GeneralEnum;
import com.pinde.core.config.*;
import com.pinde.core.entyties.SysDict;
import com.pinde.core.license.PdLicense;
import com.pinde.core.util.*;
import com.pinde.sci.biz.jsres.IjsresLoginInfoBiz;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.util.RegionUtil;
import com.pinde.sci.ctrl.util.InitPasswordUtil;
import com.pinde.sci.enums.BaseEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 系统初始化操作
 * 
 * @author shijian
 * @create 2014.04.2
 */
public class InitConfig implements ServletContextListener {

	private final static Logger logger = LoggerFactory.getLogger(InitConfig.class);
	
	public static boolean licenseed = false;
	//public static ProjPage projApplyPage;
	//科研
	public static Map<String, Map<String, String>> configMap;
	//住院医师
	public static IrbFormRequestUtil resFormRequestUtil;
	//系统
	private static Map<String,WorkStation> workStationMap;
	private static Map<String,WorkStation> menuWorkStationMap;
	private static Map<String, Module> menuModuleMap;
	private static Map<String,MenuSet> menuSetMap;
	private static Map<String,Map<String,String>> sysDictNameMap;
	private static Map<String, SysRole> sysRoleMap;
	private static Map<String,List<SysRole>> sysRoleWsMap = new HashMap<>();
	private static Map<String,SysOrg> sysOrgMap ;
	private static Map<String,SysOrg> sysOrgNameMap ;
	private static List<SysOrg> sysOrgList ;
	private static Map<String, SysDept> sysDeptMap;
	private static Map<String, String> sysCfgMap = new HashMap<String, String>();
	private static Map<String, String> sysCfgDescMap;
	private static Map<String, List<String>> cityMap;

	public static Map<String,String> jsResLoginUrlMap;
	public static Map<String,String> jsResLoginBannerImgMap;
	public static Map<String,String> jsResLoginTitleMap;

	public static Map<String,String> weekPasswordMap;



	public static void refresh(ServletContext context) {
		logger.debug("开始刷新内存...");
		//加载工作站
		_loadWorkStation(context);
		//加载字典
		_loadDict(context);
		//加载系统代码
		_loadEnum(context);
		//加载角色
		_loadRole(context);
		//加载机构和部门
		//加载机构
		_loadOrg(context);
		//加载部门
		_loadDept(context);
		//加载系统配置
		_loadSysCfg(context);
		//校验License
		_checkLicense(context);
		//加载省市关系
		_getCityMap();
		//加载江苏西医各个基地首页
		_loadJsresLogin(context);
		//加载访问权限
		loadAccessAuthority(context);
		//读取弱密码
		__weekPassword(context);
	}

	private static void __weekPassword(ServletContext context) {
		logger.debug("开始读取弱密码...");
		try {
			Map<String,String> weekPasswords = new HashMap<String,String>();
			BufferedReader br = new BufferedReader(new FileReader(SpringUtil.getResource("classpath:weak_password/weekPassword1.txt").getFile()));
			for (String s = null; (s = br.readLine()) != null;) {
				weekPasswords.put(s,s);
			}
			br.close();

			BufferedReader br2 = new BufferedReader(new FileReader(SpringUtil.getResource("classpath:weak_password/weekPassword2.txt").getFile()));
			for (String s = null; (s = br2.readLine()) != null;) {
				weekPasswords.put(s,s);
			}
			br2.close();

			BufferedReader br3 = new BufferedReader(new FileReader(SpringUtil.getResource("classpath:weak_password/weekPassword3.txt").getFile()));
			for (String s = null; (s = br3.readLine()) != null;) {
				weekPasswords.put(s,s);
			}
			br3.close();
			weekPasswordMap = weekPasswords;

		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.debug("读取弱密码结束...");
	}

	private static void _checkLicense(ServletContext context) {
		logger.debug("开始License验证...");
        String licenseStringEncoded = getSysCfg("license.key");
        licenseed = PdLicense.checkLicense(licenseStringEncoded);
        logger.debug("从数据库加载license.key文件,加载结果："+licenseed);
        if(licenseed==false){
            logger.debug("从classpath加载license.key文件");
            try {
                File licenseFile = SpringUtil.getResource("classpath:license.key").getFile();
                licenseStringEncoded = FileUtils.readFileToString(licenseFile);
                licenseed = PdLicense.checkLicense(licenseStringEncoded);
                logger.debug("从classpath加载license.key文件,加载结果："+licenseed);
            } catch (IOException e) {
                logger.debug("从classpath加载license.key文件失败",e);
            }
        }

		context.setAttribute("licenseed", licenseed);
		context.setAttribute("machineId", ServerUtil.getMachineId());
		context.setAttribute("licenseedWorkStation", PdLicense.getLicensedWorkStation());
		context.setAttribute("issueDate", PdLicense.getIssueDate());
		context.setAttribute("validDate", PdLicense.getValidDate());
		logger.debug("License验证结果：" + licenseed);
		if (licenseed == false) {
			logger.debug("系统License校验失败，不能启动系统...");
//			throw new RuntimeException("系统License校验失败，不能启动系统...");
		}
	}

	/**
	 * @Department：研发部
	 * @Description 加载访问权限
	 * @Author Zjie
	 * @Date 2020/9/10
	 */
	private static void loadAccessAuthority(ServletContext context) {
		InputStream inputStream = InitConfig.class.getClassLoader().getResourceAsStream("accessAuthority.properties");
		Properties prop = new Properties();
		try {
			prop.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		String doctorAccessAuthority = prop.getProperty("doctor");//学员
		String teacherAccessAuthority = prop.getProperty("teacher");//带教老师
		String responsibleTeacherAccessAuthority = prop.getProperty("responsibleTeacher");//责任导师
		String secretaryAccessAuthority = prop.getProperty("secretary");//教秘
		String teachingSecretaryAccessAuthority = prop.getProperty("teachingSecretary");//教秘
		String headAccessAuthority = prop.getProperty("head");//科主任
		String schoolAccessAuthority = prop.getProperty("school");//学校
		String universityAccessAuthority = prop.getProperty("university");//高校管理员
		String zygljAccessAuthority = prop.getProperty("zyglj");//审核部门之中医管理局
		String bjwAuthority = prop.getProperty("bjw");//审核部门之毕教委
		String qkzxAuthority = prop.getProperty("qkzx");//审核部门之全科中心
		String instituteAuthority = prop.getProperty("institute");//研究所
		String nurseAuthority = prop.getProperty("nurse");//护士
		String businessAuthority = prop.getProperty("business");//商务角色
		String testAuthority = prop.getProperty("test");//外省基地管理员角色
		String adminAccessAuthority = prop.getProperty("admin");//医院管理员//免费医院管理员
		String hospitalSecretaryAccessAuthority = prop.getProperty("hospitalSecretary");//医院秘书
		String speAdminAccessAuthority = prop.getProperty("speAdmin");//专业基地管理员
		String speAdminSecretaryAccessAuthority = prop.getProperty("speAdminSecretary");//专业基地管理员
		String globalAccessAuthority = prop.getProperty("global");//省级部门
		String qualityAccessAuthority = prop.getProperty("quality");//质控组
		String chargeAccessAuthority = prop.getProperty("charge");//主管部门（市局）
		String managementAccessAuthority = prop.getProperty("management");//督导-管理专家
		String expertLeaderAccessAuthority = prop.getProperty("expertLeader");//督导-专业专家
		String baseExpertAccessAuthority = prop.getProperty("baseExpert");//督导-专业基地专家
		String hospitalLeaderAccessAuthority = prop.getProperty("hospitalLeader");//督导-评分专家
		String phyAssAccessAuthority = prop.getProperty("phyAss");//督导-评分专家
		/*List<String> allMenus = new ArrayList<>();
		if (StringUtil.isNotBlank(doctorAccessAuthority)) {
			allMenus.addAll(Arrays.asList(doctorAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(teacherAccessAuthority)) {
			allMenus.addAll(Arrays.asList(teacherAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(responsibleTeacherAccessAuthority)) {
			allMenus.addAll(Arrays.asList(responsibleTeacherAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(secretaryAccessAuthority)) {
			allMenus.addAll(Arrays.asList(secretaryAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(teachingSecretaryAccessAuthority)) {
			allMenus.addAll(Arrays.asList(teachingSecretaryAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(headAccessAuthority)) {
			allMenus.addAll(Arrays.asList(headAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(schoolAccessAuthority)) {
			allMenus.addAll(Arrays.asList(schoolAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(universityAccessAuthority)) {
			allMenus.addAll(Arrays.asList(universityAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(zygljAccessAuthority)) {
			allMenus.addAll(Arrays.asList(zygljAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(bjwAuthority)) {
			allMenus.addAll(Arrays.asList(bjwAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(qkzxAuthority)) {
			allMenus.addAll(Arrays.asList(qkzxAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(instituteAuthority)) {
			allMenus.addAll(Arrays.asList(instituteAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(nurseAuthority)) {
			allMenus.addAll(Arrays.asList(nurseAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(businessAuthority)) {
			allMenus.addAll(Arrays.asList(businessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(testAuthority)) {
			allMenus.addAll(Arrays.asList(testAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(adminAccessAuthority)) {
			allMenus.addAll(Arrays.asList(adminAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(hospitalSecretaryAccessAuthority)) {
			allMenus.addAll(Arrays.asList(hospitalSecretaryAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(speAdminAccessAuthority)) {
			allMenus.addAll(Arrays.asList(speAdminAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(speAdminSecretaryAccessAuthority)) {
			allMenus.addAll(Arrays.asList(speAdminSecretaryAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(chargeAccessAuthority)) {
			allMenus.addAll(Arrays.asList(chargeAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(globalAccessAuthority)) {
			allMenus.addAll(Arrays.asList(globalAccessAuthority.split(",")));
		}
		if (StringUtil.isNotBlank(qualityAccessAuthority)) {
			allMenus.addAll(Arrays.asList(qualityAccessAuthority.split(",")));
		}
		if(StringUtil.isNotBlank(managementAccessAuthority)){
			allMenus.addAll(Arrays.asList(managementAccessAuthority.split(",")));
		}
		if(StringUtil.isNotBlank(expertLeaderAccessAuthority)){
			allMenus.addAll(Arrays.asList(expertLeaderAccessAuthority.split(",")));
		}
		if(StringUtil.isNotBlank(baseExpertAccessAuthority)){
			allMenus.addAll(Arrays.asList(baseExpertAccessAuthority.split(",")));
		}
		if(StringUtil.isNotBlank(hospitalLeaderAccessAuthority)){
			allMenus.addAll(Arrays.asList(hospitalLeaderAccessAuthority.split(",")));
		}
		if(StringUtil.isNotBlank(phyAssAccessAuthority)){
			allMenus.addAll(Arrays.asList(phyAssAccessAuthority.split(",")));
		}*/
		context.setAttribute("doctorAccessAuthority", doctorAccessAuthority);
		context.setAttribute("teacherAccessAuthority", teacherAccessAuthority);
		context.setAttribute("responsibleTeacherAccessAuthority", responsibleTeacherAccessAuthority);
		context.setAttribute("secretaryAccessAuthority", secretaryAccessAuthority);
		context.setAttribute("teachingSecretaryAccessAuthority", teachingSecretaryAccessAuthority);
		context.setAttribute("headAccessAuthority", headAccessAuthority);
		context.setAttribute("schoolAccessAuthority", schoolAccessAuthority);
		context.setAttribute("universityAccessAuthority", universityAccessAuthority);
		context.setAttribute("zygljAccessAuthority", zygljAccessAuthority);
		context.setAttribute("bjwAuthority", bjwAuthority);
		context.setAttribute("qkzxAuthority", qkzxAuthority);
		context.setAttribute("instituteAuthority", instituteAuthority);
		context.setAttribute("nurseAuthority", nurseAuthority);
		context.setAttribute("businessAuthority", businessAuthority);
		context.setAttribute("testAuthority", testAuthority);
		context.setAttribute("adminAccessAuthority", adminAccessAuthority);
		context.setAttribute("hospitalSecretaryAccessAuthority", hospitalSecretaryAccessAuthority);
		context.setAttribute("speAdminAccessAuthority", speAdminAccessAuthority);
		context.setAttribute("speAdminSecretaryAccessAuthority", speAdminSecretaryAccessAuthority);
		context.setAttribute("globalAccessAuthority", globalAccessAuthority);
		context.setAttribute("qualityAccessAuthority", qualityAccessAuthority);
		context.setAttribute("chargeAccessAuthority", chargeAccessAuthority);
		context.setAttribute("managementAccessAuthority", managementAccessAuthority);
		context.setAttribute("expertLeaderAccessAuthority", expertLeaderAccessAuthority);
		context.setAttribute("baseExpertAccessAuthority", baseExpertAccessAuthority);
		context.setAttribute("hospitalLeaderAccessAuthority", hospitalLeaderAccessAuthority);
		context.setAttribute("phyAssAccessAuthority", phyAssAccessAuthority);
//		context.setAttribute("allMenus", allMenus);
	}

	public static String getWorkStationName(String workStationId) {
		if (workStationMap != null) {
			WorkStation workStation = workStationMap.get(workStationId);
			if (workStation != null) {
				return workStation.getWorkStationName();
			}
		}
		return null;
	}

	public static WorkStation getWorkStation(String workStationId) {
		if (workStationMap != null) {
			return workStationMap.get(workStationId);
		}
		return null;
	}

	public static String getWorkStationIdByMenuId(String menuId) {
		if (menuWorkStationMap != null) {
			WorkStation workStation = menuWorkStationMap.get(menuId);
			if (workStation != null) {
				return workStation.getWorkStationId();
			}
		}
		return null;
	}

	public static String getModuleIdByMenuId(String menuId) {
		if (menuModuleMap != null) {
			Module module = menuModuleMap.get(menuId);
			if (module != null) {
				return module.getModuleId();
			}
		}
		return null;
	}

	public static String getMenuSetIdByMenuId(String menuId) {
		if (menuSetMap != null) {
			MenuSet menuSet = menuSetMap.get(menuId);
			if (menuSet != null) {
				return menuSet.getSetId();
			}
		}
		return null;
	}

	private static void _loadWorkStation(ServletContext context) {
		logger.debug("开始加载工作站配置文件...");
		try {
			String workType=sysCfgMap.get("workStation.type");
			String local="classpath:workStation.xml";
			if(StringUtil.isNotBlank(workType))
			{
				local="classpath:workStation_"+workType+".xml";
			}
			WorkStationParse parse = new WorkStationParse(SpringUtil.getResource(local).getFile());
			//只加载授权的工作站
			List<WorkStation> workStationList = parse.parse();
			List<String> licensedWorkStation = PdLicense.getLicensedWorkStation();
			List<WorkStation> workStationLicenseList = new ArrayList<WorkStation>();
			for (WorkStation workStation : workStationList) {
				if (licensedWorkStation.contains(workStation.getWorkStationId())) {
					workStationLicenseList.add(workStation);
				}
			}

			Map<String, WorkStation> menuWorkStationMap = new HashMap<String, WorkStation>();
			Map<String, Module> menuModuleMap = new HashMap<String, Module>();
			Map<String, MenuSet> menuSetMap = new HashMap<String, MenuSet>();
			Map<String, WorkStation> workStationMap = new HashMap<String, WorkStation>();
//			int imodule = 1;

//			IDictBiz dictBiz = (IDictBiz) SpringUtil.getBean(IDictBiz.class);
			for (WorkStation workStation : workStationLicenseList) {
				workStationMap.put(workStation.getWorkStationId(), workStation);
				List<Module> moduleList = workStation.getModuleList();
				for (Module module : moduleList) {
					//查数据库，看模块名有没有被重新定义
//					SysDict moduleDict = dictBiz.readDict(DictTypeEnum.SysModule.getId(), module.getModuleId());
//					if(moduleDict==null){
//						SysDict dict = new SysDict();
//						dict.setDictTypeId(DictTypeEnum.SysModule.getId());
//						dict.setDictTypeName(DictTypeEnum.SysModule.getName());
//						dict.setDictId(module.getModuleId());
//						dict.setDictName(module.getModuleName());
//						dict.setOrdinal(imodule++);
//						dict.setDictDesc(module.getModuleName());
//						dictBiz.addDict(dict);
//					}else{
//						if(GlobalConstant.RECORD_STATUS_Y.equals(moduleDict.getRecordStatus())){
////							module.setModuleName(moduleDict.getDictName());
//						}
//					}

					int imenuset = 1;

					List<MenuSet> menuSetList = module.getMenuSetList();
					for (MenuSet menuSet : menuSetList) {
						//查数据库，看一级菜单有没有被重新定义
//						SysDict menuSetDict = dictBiz.readDict(DictTypeEnum.SysModule.getId()+"."+module.getModuleId(), menuSet.getSetId());
//						if(menuSetDict==null){
//							SysDict dict = new SysDict();
//							dict.setDictTypeId(DictTypeEnum.SysModule.getId()+"."+module.getModuleId());
//							dict.setDictTypeName(menuSet.getSetName());
//							dict.setDictId(menuSet.getSetId());
//							dict.setDictName(menuSet.getSetName());
//							dict.setOrdinal(Integer.parseInt(imodule+""+(imenuset++)));
//							dict.setDictDesc(menuSet.getSetName());
//							dictBiz.addDict(dict);
//						}else{
//							if(GlobalConstant.RECORD_STATUS_Y.equals(menuSetDict.getRecordStatus())){
////								menuSet.setSetName(menuSetDict.getDictName());
//							}
//						}

						int imenu = 1;

						List<Menu> menuList = menuSet.getMenuList();
						for (Menu menu : menuList) {
							//查数据库，看二级菜单名有没有被重新定义
//							SysDict menuDict = dictBiz.readDict(DictTypeEnum.SysModule.getId()+"."+module.getModuleId()+"."+menuSet.getSetId(), menu.getMenuId());
//							if(menuDict==null){
//								SysDict dict = new SysDict();
//								dict.setDictTypeId(DictTypeEnum.SysModule.getId()+"."+module.getModuleId()+"."+menuSet.getSetId());
//								dict.setDictTypeName(menu.getMenuName());
//								dict.setDictId(menu.getMenuId());
//								dict.setDictName(menu.getMenuName());
//								dict.setOrdinal(Integer.parseInt(imodule+""+imenuset+""+(imenu++)));
//								dict.setDictDesc(menu.getMenuName());
//								dictBiz.addDict(dict);
//							}else{
//								if(GlobalConstant.RECORD_STATUS_Y.equals(menuDict.getRecordStatus())){
////									menu.setMenuName(menuDict.getDictName());
//								}
//							}

							menuModuleMap.put(menu.getMenuId(), module);
							menuSetMap.put(menu.getMenuId(), menuSet);
							menuWorkStationMap.put(menu.getMenuId(), workStation);
						}
					}
				}
			}
			context.setAttribute("workStationList", workStationLicenseList);
//			GlobalContext.workStationList = workStationLicenseList;
			context.setAttribute("workStationMap", workStationMap);
			context.setAttribute("allWorkStation", workStationList);
			InitConfig.workStationMap = workStationMap;
			InitConfig.menuWorkStationMap = menuWorkStationMap;
			InitConfig.menuModuleMap = menuModuleMap;
			InitConfig.menuSetMap = menuSetMap;
		} catch (Exception e) {
			logger.error("系统工作站加载失败，不能启动系统...", e);
			throw new RuntimeException("系统工作站加载失败，不能启动系统...");
		}
	}

	private static void _loadDict(ServletContext context) {
		Map<String, List<SysDict>> sysListDictMap = new HashMap<String, List<SysDict>>();
		Map<String, String> sysDictIdMap = new HashMap<String, String>();
		Map<String, Map<String, String>> sysDictNameMap = new HashMap<String, Map<String, String>>();
		List<DictTypeEnum> dictTypeEnumList = (List<DictTypeEnum>) EnumUtil.toList(DictTypeEnum.class);
		for (DictTypeEnum dictTypeEnum : dictTypeEnumList) {
			String dictTypeId = dictTypeEnum.getId();
			Map<String, String> dictNameMap = new HashMap<String, String>();
			sysDictNameMap.put(dictTypeId, dictNameMap);
			IDictBiz dictBiz = SpringUtil.getBean(IDictBiz.class);

			SysDict sysDict = new SysDict();
			sysDict.setDictTypeId(dictTypeId);
			sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);

			List<SysDict> sysDictList = dictBiz.searchDictList(sysDict);
			for (SysDict dict : sysDictList) {
				String typeId = dict.getDictTypeId() + "." + dict.getDictId();
				sysDictIdMap.put(typeId, dict.getDictName());
				dictNameMap.put(dict.getDictName(), dict.getDictId());
				if (dictTypeEnum.getLevel() > 1) {
					sysDict.setDictTypeId(typeId);
					List<SysDict> sysDictSecondList = dictBiz.searchDictList(sysDict);

					if (sysDictSecondList != null && sysDictSecondList.size() > 0) {
						for (SysDict sDict : sysDictSecondList) {
							String tTypeId = typeId + "." + sDict.getDictId();
							String tTypeName = dict.getDictName() + "." + sDict.getDictName();

							sysDictIdMap.put(tTypeId, tTypeName);

							if (dictTypeEnum.getLevel() > 2) {
								sysDict.setDictTypeId(tTypeId);
								List<SysDict> sysDictThirdList = dictBiz.searchDictList(sysDict);

								if (sysDictThirdList != null && sysDictThirdList.size() > 0) {
									for (SysDict tDict : sysDictThirdList) {
										sysDictIdMap.put(tTypeId + "." + tDict.getDictId(), tTypeName + "." + tDict.getDictName());
									}
								}

								sysListDictMap.put(tTypeId, sysDictThirdList);
								context.setAttribute("dictTypeEnum" + tTypeId + "List", sysDictThirdList);
							}

						}
					}

					sysListDictMap.put(typeId, sysDictSecondList);
					context.setAttribute("dictTypeEnum" + typeId + "List", sysDictSecondList);
				}
			}

			sysListDictMap.put(dictTypeId, sysDictList);
			context.setAttribute("dictTypeEnum" + dictTypeEnum.name() + "List", sysDictList);
		}
		//移除助理全科字典项（数据库未拆分）
		List<SysDict> list = (List<SysDict>) context.getAttribute("dictTypeEnumDoctorTrainingSpeList");
		List<SysDict> collect = list.stream().filter(sysDict -> !sysDict.getDictId().equals("50")).collect(Collectors.toList());
		context.setAttribute( "dictTypeEnumDoctorTrainingSpeList", collect);

//		GlobalContext.sysDictListMap = sysListDictMap;
		context.setAttribute("sysDictIdMap", sysDictIdMap);
		InitConfig.sysDictNameMap = sysDictNameMap;
		DictTypeEnum.sysDictIdMap = sysDictIdMap;
		DictTypeEnum.sysListDictMap = sysListDictMap;
	}

	public static Map<String, String> getDictNameMap(String dictTypeId) {
		if (sysDictNameMap != null) {
			return sysDictNameMap.get(dictTypeId);
		}
		return null;
	}

	private static void _loadEnum(ServletContext context) {
		Set<Class<?>> set = ClassUtil.getClasses("com.pinde.sci.enums");
		for (Class<?> cls : set) {
			if(BaseEnum.class.isAssignableFrom(cls)) {
				Class[] innerEnums = cls.getDeclaredClasses();
				if(innerEnums != null) {
					for(int i = 0; i < innerEnums.length; i++) {
						if(Enum.class.isAssignableFrom(innerEnums[i])) {
							String mixName = innerEnums[i].getSimpleName();
							context.setAttribute(StringUtil.uncapitalize(mixName.substring(mixName.lastIndexOf("$") + 1)) + "List", EnumUtil.toList((Class<? extends GeneralEnum>) innerEnums[i]));
						}
					}
				}
			}else if(GeneralEnum.class.isAssignableFrom(cls)){
				List<GeneralEnum> enumList = (List<GeneralEnum>) EnumUtil.toList((Class<? extends GeneralEnum>) cls);
				context.setAttribute(StringUtil.uncapitalize(cls.getSimpleName()) + "List", enumList);
				for (GeneralEnum genum : enumList) {
					context.setAttribute(StringUtil.uncapitalize(cls.getSimpleName()) + genum.name(), genum);
				}
			}
		}
	}

	public static SysRole getSysRole(String orgFlow) {
		if (sysRoleMap != null) {
			SysRole sysRole = sysRoleMap.get(orgFlow);
//			if(sysRole!=null){
//				return sysRole.getRoleName();
//			}
			return sysRole;
		}
		return null;
	}

	public static void _loadRole(ServletContext context) {
		Map<String, SysRole> sysRoleMap = new HashMap<String, SysRole>();
		Map<String, List<SysRole>> sysRoleWsMap = new HashMap<String, List<SysRole>>();
		IRoleBiz roleBiz = SpringUtil.getBean(IRoleBiz.class);
		List<SysRole> sysRoleList = roleBiz.search(new SysRole(), null);
		for (SysRole sysRole : sysRoleList) {
			sysRoleMap.put(sysRole.getRoleFlow(), sysRole);
			List<SysRole> roleList = sysRoleWsMap.get(sysRole.getWsId());
			if (roleList == null) {
				roleList = new ArrayList<SysRole>();
			}
			roleList.add(sysRole);
			sysRoleWsMap.put(sysRole.getWsId(), roleList);
		}
		InitConfig.sysRoleMap = sysRoleMap;
		InitConfig.sysRoleWsMap = sysRoleWsMap;
		context.setAttribute("sysRoleMap", sysRoleMap);
		context.setAttribute("sysRoleWsMap", sysRoleWsMap);
	}

	public static String getOrgNameByFlow(String orgFlow) {
		if (sysOrgMap != null) {
			SysOrg sysOrg = sysOrgMap.get(orgFlow);
			if (sysOrg != null) {
				return sysOrg.getOrgName();
			}
		}
		return null;
	}

	public static void _loadOrg(ServletContext context) {
		Map<String, SysOrg> sysOrgMap = new HashMap<String, SysOrg>();
		Map<String, SysOrg> sysOrgNameMap = new HashMap<String, SysOrg>();
		IOrgBiz orgBiz = SpringUtil.getBean(IOrgBiz.class);
		SysOrg search = new SysOrg();
		search.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysOrg> sysOrgList = orgBiz.searchOrg(search);
		for (SysOrg sysOrg : sysOrgList) {
			sysOrgMap.put(sysOrg.getOrgFlow(), sysOrg);
			sysOrgNameMap.put(sysOrg.getOrgName(), sysOrg);
		}
		context.setAttribute("sysOrgList", sysOrgList);
		context.setAttribute("sysOrgMap", sysOrgMap);
		InitConfig.sysOrgMap = sysOrgMap;
		InitConfig.sysOrgNameMap = sysOrgNameMap;
		InitConfig.sysOrgList = sysOrgList;
	}

	public static SysOrg getSysOrgByName(String orgName) {
		return InitConfig.sysOrgNameMap.get(orgName);
	}

	public static String getDeptNameByFlow(String deptFlow) {
		if (sysDeptMap != null) {
			SysDept sysDept = sysDeptMap.get(deptFlow);
			if (sysDept != null) {
				return sysDept.getDeptName();
			}
		}
		return null;
	}

	public static void _loadDept(ServletContext context) {
		Map<String, SysDept> sysDeptMap = new HashMap<String, SysDept>();
		IDeptBiz deptBiz = SpringUtil.getBean(IDeptBiz.class);
		SysDept search = new SysDept();
		search.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> sysDeptList = deptBiz.searchDept(search);
		for (SysDept sysDept : sysDeptList) {
			sysDeptMap.put(sysDept.getDeptFlow(), sysDept);
		}
		context.setAttribute("sysDeptMap", sysDeptMap);
		InitConfig.sysDeptMap = sysDeptMap;
	}

	public static void _loadSysCfg(ServletContext context) {
		sysCfgMap = new HashMap<String, String>();
		sysCfgDescMap = new HashMap<String, String>();
		ICfgBiz cfgBiz = SpringUtil.getBean(ICfgBiz.class);
		List<SysCfg> sysCfgList = cfgBiz.search(new SysCfg());
		for(SysCfg sysCfg : sysCfgList){
			if(StringUtil.isNotBlank(sysCfg.getCfgDesc())){
				sysCfgDescMap.put(sysCfg.getCfgCode(), sysCfg.getCfgDesc());
			}
			sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
			if(StringUtil.isNotBlank(sysCfg.getCfgBigValue())){
				sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());
			}
		}

		Properties prop=null;
		try {
			InputStream inputStream = InitConfig.class.getClassLoader().getResourceAsStream("cfg.properties");
			prop=new Properties();
			prop.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
			prop=null;
		}
		if(prop!=null)
		{
			for (Object key:prop.keySet())
			{
				sysCfgMap.put((String)key,(String)prop.get(key));
			}
		}
		context.setAttribute("sysCfgMap",sysCfgMap);
		context.setAttribute("sysCfgDescMap", sysCfgDescMap);
	}

	public static String getSysCfg(String key){
		return StringUtil.defaultString(sysCfgMap.get(key));
	}

	public static String getSysCfgDesc(String key){
		return StringUtil.defaultString(sysCfgDescMap.get(key));
	}

	public static void main(String[] args) {
		System.err.println(StringUtil.uncapitalize("AvbD"));
	}

	public static  List<String> getCityMap(String provId){
		if(InitConfig.cityMap!=null){
			return InitConfig.cityMap.get(provId);
		}
		return null;
	}

	/**
	 * 通过省的ID来获得一个市的List
	 *
	 * @param
	 * @return
	 */
	public static void _getCityMap() {
		List<String> cityIdList = null;
		Map<String, List<String>> cityMap = new HashMap<String, List<String>>();
		try {
//			ApplicationContext contextsci = new ClassPathXmlApplicationContext("spring-context.xml");
			//File f = new File("D:\\provCityAreaJson.min.json");

			InputStreamReader read = new InputStreamReader(RegionUtil.class.getResourceAsStream("/provCityAreaJson.min.json"), "UTF-8");
			//new InputStreamReader(new FileInputStream(f),"UTF-8");
			BufferedReader br = new BufferedReader(read);
			String line = "";
			StringBuffer buffer = new StringBuffer();
			while ((line = br.readLine()) != null) {
				buffer.append(line);
			}
			String fileContent = buffer.toString();

			JSONArray provArray = JSON.parseArray(fileContent);
			for (Object provObj : provArray.toArray()) {
				JSONObject prov = (JSONObject) provObj;
				String v = prov.getString("v");
				if (!cityMap.containsKey(v)) {
					cityIdList = new ArrayList<String>();
				} else {
					cityIdList = cityMap.get(v);
				}
				String n = prov.getString("n");
				JSONArray cityArray = prov.getJSONArray("s");
				for (Object cityObj : cityArray.toArray()) {
					JSONObject city = (JSONObject) cityObj;
					String vc = city.getString("v");
					cityIdList.add(vc);
				}
				cityMap.put(v, cityIdList);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		InitConfig.cityMap = cityMap;
	}

	/**
	 * 初始化入口
	 */
	public void contextInitialized(ServletContextEvent event) {
		logger.debug("系统初始化...");
		ServletContext context = event.getServletContext();
		//加载字典
		_loadDict(context);
		//加载系统代码
		_loadEnum(context);
		//加载角色
		_loadRole(context);
		//加载机构
		_loadOrg(context);
		//加载部门
		_loadDept(context);
		//加载系统配置
		_loadSysCfg(context);
		//校验License
		_checkLicense(context);
		//加载工作站
		_loadWorkStation(context);
		//加载省市关系
		_getCityMap();
		//加载江苏西医各个基地首页
		_loadJsresLogin(context);
		//加载webservice
//		SpringUtil.getBean(InitWebService.class).loadWebservice();

		_loadSysFile();

		//加载访问权限
		loadAccessAuthority(context);
		//读取弱密码
		__weekPassword(context);
	}

	private static void _loadJsresLogin(ServletContext context) {
		logger.debug("Load JSRES Index Page Start...");
		jsResLoginTitleMap = new HashMap<String, String>();
		jsResLoginUrlMap = new HashMap<String, String>();
		jsResLoginBannerImgMap = new HashMap<String, String>();
//		jsResLoginUrlMap.put("www.js.ezhupei.test.com","/inx/jsres/test");
//		jsResLoginUrlMap.put("js.ezhupei.test.com","/inx/jsres/test");
//		jsResLoginTitleMap.put("www.js.ezhupei.test.com","江苏西医测试系统头信息");
//		jsResLoginTitleMap.put("js.ezhupei.test.com","江苏西医测试系统头信息");

		IjsresLoginInfoBiz ijsresLoginInfoBiz = SpringUtil.getBean(IjsresLoginInfoBiz.class);
		List<JsresLoginInfo> list = ijsresLoginInfoBiz.search(new JsresLoginInfo());
		for(JsresLoginInfo bean : list){
			String key=bean.getLoginDomain();
			if(StringUtil.isNotBlank(key))
			{
				jsResLoginUrlMap.put(key,bean.getLoginUrl());
				jsResLoginTitleMap.put(key,bean.getLoginTitle());
				jsResLoginBannerImgMap.put(key,bean.getLoginDomainSuffix());
			}
		}
		context.setAttribute("jsResLoginTitleMap",jsResLoginTitleMap);
		context.setAttribute("jsResLoginUrlMap", jsResLoginUrlMap);
		logger.debug("Load JSRES Index Page End...");
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

	/**
	 * 成都进修系统科室字典维护
	 * @param context
     */
	public static void refreshDict(ServletContext context){
		_loadDict(context);
	}

	public static String getJxInitPassWord(){
		return InitPasswordUtil.getInitPass();
	}
	public static String getInitPassWord(){
		return InitPasswordUtil.getInitPass();
	}

	public static String getInitPass(){
		return InitPasswordUtil.getInitPassword();
	}

	private static void _loadSysFile(){
		try {
			String filePath = System.getProperty("java.home")+"\\bin\\jacob-1.17-M2-x64.dll";//获取jdk目录
			logger.debug(">>>>>>>>>>>>>>>>\n"+filePath);
			File file = new File(filePath);
			if(!file.exists()) {
				File pFile = file.getParentFile();
				pFile.mkdirs();
				try {
					file.createNewFile();
				}catch (Exception e) {
					logger.debug("创建文件失败(About jacob-1.17-M2-x64.dll)...");
//					e.printStackTrace();
				}

			}
			if(!file.exists()){
				filePath = System.getProperty("catalina.home")+"\\bin\\jacob-1.17-M2-x64.dll";//获取tomcat目录
				System.out.println(filePath);
				file = new File(filePath);
				if(!file.exists()) {
					File pFile = file.getParentFile();
					pFile.mkdirs();
					try {
						file.createNewFile();
					}catch (Exception e) {
						logger.debug("创建文件失败(About jacob-1.17-M2-x64.dll)...");
						e.printStackTrace();
					}

				}

			}
			if(file.exists()) {
				if(file.length()<102400) {//当前文件大小小于100K
					logger.debug("开始从资源文件复制jacob-1.17-M2-x64.dll...");
					File sourceFile = SpringUtil.getResource("classpath:jacob-1.17-M2-x64.dll").getFile();
					logger.debug(sourceFile.getPath());
//				Files.copy(sourceFile.toPath(), file.toPath());
					FileUtils.copyFile(sourceFile, file);
//				if(sourceFile.renameTo(file)){
//					logger.debug("复制成功，已经将jacob-1.17-M2-x64.dll拷贝至"+filePath+"...");
//				}else{
//					logger.debug("文件复制失败，请将jacob-1.17-M2-x64.dll文件放在 %JAVA_HOME%\\bin 目录下");
//				}
				}
			}else{
				logger.debug("文件复制失败，请将jacob-1.17-M2-x64.dll文件放在 %JAVA_HOME%\\bin 目录下...");
			}

		} catch (Exception e) {
			logger.debug("文件复制失败，请将jacob-1.17-M2-x64.dll文件放在 %JAVA_HOME%\\bin 目录下...");
			e.printStackTrace();
		}
	}

}
