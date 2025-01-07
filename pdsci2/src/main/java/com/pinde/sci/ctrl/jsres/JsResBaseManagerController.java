package com.pinde.sci.ctrl.jsres;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.common.enums.RegistryTypeEnum;
import com.pinde.core.common.enums.ResDocTypeEnum;
import com.pinde.core.common.sci.dao.SchAndStandardDeptCfgMapper;
import com.pinde.core.common.sci.dao.SchRotationDeptMapper;
import com.pinde.core.common.sci.dao.SysDeptMapper;
import com.pinde.core.common.sci.dao.SysOrgBatchMapper;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.pdf.DocumentVo;
import com.pinde.core.pdf.PdfDocumentGenerator;
import com.pinde.core.pdf.utils.ResourceLoader;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.*;
import com.pinde.core.vo.ResDeptRelStdDeptVO;
import com.pinde.core.vo.ResOrgSepVO;
import com.pinde.core.vo.SysDeptVO;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sch.impl.SchRotationGroupBizImpl;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.jsres.JsResDoctorRecruitExtMapper;
import com.pinde.sci.dao.sys.SysDeptExtMapper;
import com.pinde.sci.form.jsres.*;
import com.pinde.sci.form.jsres.BaseSpeDept.BaseSpeDeptExtForm;
import com.pinde.sci.form.jsres.BaseSpeDept.TrainingForm;
import com.pinde.core.model.ResBaseExt;
import com.pinde.core.model.SchProcessExt;
import com.pinde.core.model.SysOrgExt;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author PPBear
 *
 */
@Controller
@RequestMapping("/jsres/base")
public class JsResBaseManagerController extends GeneralController {
	Logger logger = LoggerFactory.getLogger(JsResBaseManagerController.class);

	final static String LING = "0";
	final static String Jxcf = "1";
	final static String Ynbltl = "2";
	final static String Wzbltl = "3";
	final static String Swbltl = "5";
	final static String Jxbltl = "11";
	final static String Xsjz = "4";
	final static String Rkjy = "6";
	final static String Ckks = "7";
	final static String Jnpx = "8";
	final static String Yph = "9";
	final static String Jxhz = "10";
	final static String Lcczjnzd = "12";
	final static String Lcblsxzd = "13";
	final static String Ssczzd = "14";
	final static String Yxzdbgsxzd = "15";
	final static String Lcwxyd = "16";
	final static String Ryjy = "17";
	final static String Rzyjdjy = "18";
	final static String Cjbg = "19";
	final static String Bgdfx = "20";
	final static String Jxsj = "21";
	final static String Sjys = "22";
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IJsResBaseBiz baseBiz;
	@Autowired
	private IResOrgSpeBiz resOrgSpeBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IResDoctorProcessBiz doctorProcessBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private ISchMonthlyReportBiz schMonthlyReportBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private SchRotationGroupBizImpl rotationGroupBiz;
	@Autowired
	private SchRotationDeptMapper rotationDeptMapper;
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	@Autowired
	private IDeptBasicInfoBiz deptBasicInfoBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private ISchAndStandardDeptCfgBiz deptCfgBiz;
	@Autowired
	private SysOrgBatchMapper orgBatchMapper;
	@Autowired
	private SchAndStandardDeptCfgMapper schAndStandardDeptCfgMapper;
	@Autowired
	private IResOrgSpeAssignBiz speAssignBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IUserBiz userBiz;

	@Autowired
	private IJsResDeptManagementBiz deptManagementBiz;

	@Autowired
	private JsResDoctorRecruitExtMapper jsResDoctorRecruitExtMapper;

	@Autowired
	private SysDeptExtMapper sysDeptExtMapper;

	@RequestMapping("/baseAudit")
	@ResponseBody
	public String baseAudit(String baseFlow, String status) {
		ResBase resBase = new ResBase();
		resBase.setOrgFlow(baseFlow);
		/*if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(status)) {
			resBase.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.ChargePassed.getId());
			resBase.setBaseStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.ChargePassed.getName());
		}*/
        if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(status)) {
            resBase.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getId());
            resBase.setBaseStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.NotPassed.getName());
		}
		int result = baseBiz.saveResBase(resBase);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 查找协同基地（未用）
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/findCoopBase")
	public String findCoopBase(Model model, String sessionNumber) {
		SysUser user = GlobalContext.getCurrentUser();
		Map<Object, Object> jointOrgMap = new HashMap<Object, Object>();

		ResJointOrgExample resJointOrgExample = new ResJointOrgExample();
        resJointOrgExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andSessionNumberEqualTo(sessionNumber).andOrgFlowEqualTo(user.getOrgFlow());
		List<ResJointOrg> jointOrgs = jointOrgBiz.readResJointOrgByExample(resJointOrgExample);
		if (jointOrgs != null && !jointOrgs.isEmpty()) {
			for (ResJointOrg org : jointOrgs) {
				String key = org.getJointOrgFlow();
				SysOrg sysOrg = orgBiz.readSysOrg(org.getJointOrgFlow());
				jointOrgMap.put(key, sysOrg);
			}
		}
		model.addAttribute("jointOrgs", jointOrgs);
		model.addAttribute("jointOrgMap", jointOrgMap);
		return "jsres/hospital/hos/editCoopBase";
	}

	/**
	 * 市厅查找基地信息
	 */
	@RequestMapping("/findBaseInfo")
	public ModelAndView findBaseInfo(String role, Integer currentPage, ResBase base, SysOrg org, HttpServletRequest request, Model model, String auditFlag) {
		SysUser user = GlobalContext.getCurrentUser();
		SysOrg sOrg = new SysOrg();
		List<SysOrg> orgList = new ArrayList<SysOrg>();
		List<String> orgFlowList = new ArrayList<String>();
		ModelAndView mav = new ModelAndView();
		SysOrg sysOrg = orgBiz.readSysOrg(user.getOrgFlow());
		sOrg.setOrgProvId(sysOrg.getOrgProvId());
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) {
			sOrg.setOrgCityId(sysOrg.getOrgCityId());
		}
        sOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		orgList = orgBiz.searchOrg(sOrg);
		for (SysOrg o : orgList) {
			orgFlowList.add(o.getOrgFlow());
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(auditFlag)) {
            base.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("org", org);
		paramMap.put("base", base);
		paramMap.put("orgFlowList", orgFlowList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResBaseExt> resBaseExtList = baseBiz.searchResBaseExtList(paramMap);
		model.addAttribute("resBaseExtList", resBaseExtList);
        if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(role)) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(auditFlag)) {
				mav.setViewName("jsres/city/hospital/auditHospitals");
			} else {
				mav.setViewName("jsres/hospitalList");
			}
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_PROVINCE.equals(role)) {
			model.addAttribute("resBaseExtList", resBaseExtList);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(auditFlag)) {
				mav.setViewName("jsres/province/hospital/auditHospitals");
			} else {
				mav.setViewName("jsres/hospitalList");
			}
		}
		return mav;
	}

	/**
	 * 查找专业基地
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/findTrainSpe")
	public ModelAndView findTrainSpe(Model model, String trainCategoryType, String orgFlow, String editFlag) {
		ModelAndView mav = new ModelAndView();
		ResOrgSpe search = new ResOrgSpe();
		search.setOrgFlow(orgFlow);
        search.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<ResOrgSpe> resSpesList = resOrgSpeBiz.searchResOrgSpeList(search, trainCategoryType);
		if (resSpesList != null && resSpesList.size() > 0) {
			Map<String, ResOrgSpe> rbsMap = new HashMap<String, ResOrgSpe>();
			for (ResOrgSpe rbs : resSpesList) {
				String key = rbs.getSpeTypeId() + rbs.getSpeId();
				rbsMap.put(key, rbs);
			}
			mav.addObject("rbsMap", rbsMap);
		}
		ResBase resBase = baseBiz.readBase(orgFlow);
		model.addAttribute("resBase", resBase);
		if (resBase != null) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag) || (StringUtil.isBlank(resBase.getBaseStatusId()) && getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL))) {
				mav.setViewName("jsres/hospital/hos/editSpe");
			} else {
				model.addAttribute("baseInfoName", trainCategoryType);
				mav.setViewName("jsres/city/hospital/speView");
			}
		} else {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag)) {
				mav.setViewName("jsres/hospital/hos/editSpe");
			} else {
				ResOrgSpe resOrgSpe = new ResOrgSpe();
				resOrgSpe.setOrgFlow(orgFlow);
				List<ResOrgSpe> exitList = resOrgSpeBiz.searchResOrgSpeList(resOrgSpe, null);
				if (exitList.size() > 0 && exitList != null) {
					model.addAttribute("baseInfoName", trainCategoryType);
					mav.setViewName("jsres/city/hospital/speView");
				} else {
					mav.setViewName("jsres/hospital/hos/editSpe");
				}
			}
		}
		return mav;
	}

	/**
	 * 查找信息
	 *
	 * @param baseFlow
	 * @param baseInfoName
	 * @param editFlag
	 * @param viewFlag
	 * @return
	 */
	@RequestMapping("/findAllBaseInfo")
	public ModelAndView findAllBaseInfo(String baseFlow, String baseInfoName, String editFlag, String viewFlag, String sessionNumber, String ishos, String baseInfoMain) {
		ModelAndView mav = new ModelAndView();

		//省厅或者客服角色，只能查看
		List<SysUserRole> sysUserRoleList = userRoleBiz.getByUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		if (null != sysUserRoleList && sysUserRoleList.size() > 0) {
			for (SysUserRole userRole : sysUserRoleList) {
				if (StringUtil.isNotBlank(userRole.getRoleFlow()) && (
						userRole.getRoleFlow().equals(InitConfig.getSysCfg("res_global_role_flow")) ||
								userRole.getRoleFlow().equals(InitConfig.getSysCfg("res_maintenance_role_flow")))) {
                    mav.addObject("isglobal", com.pinde.core.common.GlobalConstant.FLAG_Y);
                    viewFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
			}
		}

		SysOrg sysOrg = orgBiz.readSysOrg(baseFlow);
        mav.addObject("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		mav.addObject("sysOrg", sysOrg);
		mav.addObject("ishos", ishos);
		if (!GlobalContext.getCurrentUser().getOrgFlow().equals(baseFlow)) {
            mav.addObject("isJoin", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}

		if (StringUtil.isBlank(sessionNumber)) {
			sessionNumber = DateUtil.getYear();
		}
		ResBase resBase = baseBiz.readBaseBySessionNumber(baseFlow, sessionNumber);
		mav.addObject("resBase", resBase);
		mav.addObject("sessionNumber", sessionNumber);
		String productType = "";
        if (com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(baseInfoName)) {
			productType = baseInfoName;
        } else if (com.pinde.core.common.GlobalConstant.TEACH_CONDITION.equals(baseInfoName)) {
			productType = baseInfoName;
        } else if (com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION.equals(baseInfoName)) {
			productType = baseInfoName;
        } else if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(baseInfoName)) {
			productType = baseInfoName;
        } else if (com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(baseInfoName)) {
			productType = baseInfoName;
		}
		/*if(StringUtil.isNotBlank(baseFlow)&&StringUtil.isNotBlank(productType)) {
			if (com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION.equals(productType) || com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(productType)) {//查询附件
				List<PubFile> files = pubFileBiz.findFileByTypeFlow(productType, baseFlow);
				Map<String,PubFile> fileMap=new HashMap<>();
				if(files!=null&&files.size()>0)
				{
					for(PubFile f:files)
					{
						fileMap.put(f.getFileUpType(),f);
					}
				}
				mav.addObject("fileMap", fileMap);
				if (com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(productType)) {
					mav.addObject("files", files);
				}
			}else {
				//查询附件
				List<PubFile> files = pubFileBiz.findFileByTypeFlow(productType, baseFlow);
				mav.addObject("files", files);
			}
		}*/


		if (StringUtil.isNotBlank(baseFlow) && StringUtil.isNotBlank(productType)) {
            if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(productType) || com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(productType) || com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION.equals(productType)
                    || com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(productType)) {//查询附件
				List<PubFile> files = new ArrayList<>();
				//教学条件
                List<PubFile> teachConditionList = pubFileBiz.findFileByTypeFlow(com.pinde.core.common.GlobalConstant.TEACH_CONDITION, baseFlow + sessionNumber);
				files.addAll(teachConditionList);
				Map<String, PubFile> fileMap = new HashMap<>();
                if (com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(productType) || (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(productType) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag))) {
					//组织管理
                    List<PubFile> orgManageList = pubFileBiz.findFileByTypeFlow(com.pinde.core.common.GlobalConstant.ORG_MANAGE, baseFlow + sessionNumber);
					if (orgManageList != null && orgManageList.size() > 0) {
						for (PubFile f : orgManageList) {
							fileMap.put(f.getFileUpType(), f);
						}
					}
					mav.addObject("fileMap", fileMap);
					files.addAll(orgManageList);
				}
                if (com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION.equals(productType) || com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(productType)) {
					//支撑条件
                    List<PubFile> supportConditionList = pubFileBiz.findFileByTypeFlow(com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION, baseFlow + sessionNumber);
					if (supportConditionList != null && supportConditionList.size() > 0) {
						for (PubFile f : supportConditionList) {
							fileMap.put(f.getFileUpType(), f);
						}
					}
					mav.addObject("fileMap", fileMap);
					files.addAll(supportConditionList);
				}
				mav.addObject("files", files);
			}
		}
        if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(baseInfoName) || com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(baseInfoName)) {
			ResJointOrgExample jointOrgExample = new ResJointOrgExample();
            jointOrgExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andJointOrgFlowEqualTo(baseFlow).andSessionNumberEqualTo(sessionNumber);
			List<ResJointOrg> resJointOrgList = jointOrgBiz.readResJointOrgByExample(jointOrgExample);
			// 自身是协同单位
            String jointOrgFlag = com.pinde.core.common.GlobalConstant.FLAG_N;
			if (CollectionUtils.isNotEmpty(resJointOrgList)) {
                jointOrgFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
			}
			mav.addObject("jointOrgFlag", jointOrgFlag);
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag) || com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(baseInfoName)) {

			} else {
				// 查协同单位的 协同关系协议
				jointOrgExample = new ResJointOrgExample();
                jointOrgExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
						.andOrgFlowEqualTo(baseFlow).andSessionNumberEqualTo(sessionNumber);
				resJointOrgList = jointOrgBiz.readResJointOrgByExample(jointOrgExample);
				// 有协同单位
				if (CollectionUtils.isNotEmpty(resJointOrgList)) {
					List<String> jointFlowList = resJointOrgList.stream().map(vo -> vo.getJointFlow()).collect(Collectors.toList());
					List<PubFile> jointContractFileList = pubFileBiz.findFileByTypeFlows("jointContract", jointFlowList);
					Map<String, List<PubFile>> orgFlowToFileMap = jointContractFileList.stream().collect(Collectors.groupingBy(vo -> vo.getProductFlow()));
					List<Map<String, Object>> jointContractInfoList = new ArrayList<>();
					for (ResJointOrg resJointOrg : resJointOrgList) {
						Map<String, Object> jointMap = new HashMap<>();
						jointMap.put("orgName", resJointOrg.getJointOrgName());
						jointMap.put("orgFlow", resJointOrg.getJointOrgFlow());
						jointMap.put("speId", resJointOrg.getSpeId());
						jointMap.put("speName", resJointOrg.getSpeName());
						jointMap.put("fileList", orgFlowToFileMap.get(resJointOrg.getJointFlow()));
						jointContractInfoList.add(jointMap);
					}
					mav.addObject("jointContractList", jointContractInfoList);
				}
			}
		}

		if (resBase != null) {
			String Xml = resBase.getBaseInfo();
			String json = resBase.getBaseExtInfo();
			if (StringUtil.isNotBlank(Xml)) {
				BaseExtInfoForm baseExtInfoForm = JaxbUtil.converyToJavaBean(Xml, BaseExtInfoForm.class);
				BaseExtInfo baseExtInfo = JSON.parseObject(json, BaseExtInfo.class);
                if (com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(baseInfoName)) {
					baseInfoSet(mav, baseExtInfoForm, sysOrg, baseExtInfo);
					mav.addObject("educationInfo", baseExtInfoForm.getEducationInfo());
                } else if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(baseInfoName)) {
					baseInfoSet(mav, baseExtInfoForm, sysOrg, baseExtInfo);
                } else if (com.pinde.core.common.GlobalConstant.ORG_MANAGE.equals(baseInfoName)) {
					mav.addObject("organizationManage", baseExtInfoForm.getOrganizationManage());
                } else if (com.pinde.core.common.GlobalConstant.TEACH_CONDITION.equals(baseInfoName)) {
					mav.addObject("educationInfo", baseExtInfoForm.getEducationInfo());
					if(baseExtInfo != null) {
						mav.addObject("baseExtInfoEducationInfo", baseExtInfo.getBaseExtInfoEducationInfo());
					}
                } else if (com.pinde.core.common.GlobalConstant.SUPPORT_CONDITION.equals(baseInfoName)) {
					mav.addObject("supportCondition", baseExtInfoForm.getSupportCondition());
				}
			}
            if (com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(baseInfoName)) {
				mav.setViewName("jsres/hospital/hos/editBasicInfoMainDuplicate");
				baseInfoEditSet(sessionNumber, baseFlow, mav);
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(baseInfoMain)) {
				mav.setViewName("jsres/hospital/hos/basicInfoMainInfo");
            } else if ((StringUtil.isBlank(resBase.getBaseStatusId()) || com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag)) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewFlag)) {
				mav.setViewName("jsres/hospital/hos/edit" + baseInfoName);
                if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(baseInfoName)) {
					baseInfoEditSet(sessionNumber, baseFlow, mav);
				}
			} else {
				mav.addObject("baseInfoName", baseInfoName);
				mav.setViewName("jsres/city/hospital/" + baseInfoName.substring(0, 1).toLowerCase() + baseInfoName.substring(1, baseInfoName.length()));
			}

			String baseExtInfo = resBase.getBaseExtInfo();
			BaseExtInfo baseExtInfoJson = JSON.parseObject(baseExtInfo, BaseExtInfo.class);
			mav.addObject("baseExtInfo", baseExtInfoJson);
		} else {//无记录
//			mav.addObject("sysOrg", new SysOrg());
            if (com.pinde.core.common.GlobalConstant.BASIC_MAIN_ALL.equals(baseInfoName)) {
				mav.setViewName("jsres/hospital/hos/editBasicInfoMainDuplicate");
				baseInfoEditSet(sessionNumber, baseFlow, mav);
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(baseInfoMain)) {
				mav.setViewName("jsres/hospital/hos/basicInfoMainInfo");
            } else if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(editFlag)) {
				mav.setViewName("jsres/hospital/hos/edit" + baseInfoName);
                if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(baseInfoName)) {
					baseInfoEditSet(sessionNumber, baseFlow, mav);
				}
			} else {
				mav.setViewName("jsres/city/hospital/" + baseInfoName.substring(0, 1).toLowerCase() + baseInfoName.substring(1, baseInfoName.length()));
            }/*else if(com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewFlag)){
				mav.setViewName("jsres/city/hospital/"+baseInfoName.substring(0,1).toLowerCase()+baseInfoName.substring(1, baseInfoName.length()));
			}else{
				mav.setViewName("jsres/hospital/hos/edit"+baseInfoName);
			}*/
		}
		return mav;
	}

	private void baseInfoEditSet(String sessionNumber, String orgFlow, ModelAndView mav) {
		// 所有关联协同单位关系（特定年份）
		ResJointOrg resJointOrg = new ResJointOrg();
		resJointOrg.setSessionNumber(sessionNumber);
        resJointOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<ResJointOrg> resJointOrgAllList = jointOrgBiz.searchResJoint(resJointOrg);

		// 所有协同单位
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgLevelIdNotIn(Arrays.asList("CountryOrg", "ProvinceOrg")); // 去掉56家基地
		sysOrg.setOrgTypeId("Hospital");
		List<SysOrg> sysOrgList = orgBiz.selectJointOrgAllList(sysOrg);
		// sessionNumber还未关联的
		Map<String, SysOrg> flowToEntityMap = sysOrgList.stream().collect(Collectors.toMap(vo -> vo.getOrgFlow(), Function.identity(), (vo1, vo2) -> vo1));
		List<SysOrg> unjointOrgList = new ArrayList<>();
		List<String> allOrgFlowList = sysOrgList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
		List<String> jointOrgList = resJointOrgAllList.stream().map(vo -> vo.getJointOrgFlow()).collect(Collectors.toList());
		allOrgFlowList.removeAll(jointOrgList);
		for (String unjointOrgFlow : allOrgFlowList) {
			unjointOrgList.add(flowToEntityMap.get(unjointOrgFlow));
		}

		List<ResJointOrg> selfJointOrgList = resJointOrgAllList.stream().filter(vo -> vo.getOrgFlow().equals(orgFlow)).collect(Collectors.toList());
		resJointOrgAllList.removeAll(selfJointOrgList);


		// 有协同单位
		if (CollectionUtils.isNotEmpty(selfJointOrgList)) {
			List<String> jointFlowList = selfJointOrgList.stream().map(vo -> vo.getJointFlow()).collect(Collectors.toList());
			List<PubFile> jointContractFileList = pubFileBiz.findFileByTypeFlows("jointContract", jointFlowList);
			Map<String, List<PubFile>> orgFlowToFileMap = jointContractFileList.stream().collect(Collectors.groupingBy(vo -> vo.getProductFlow()));
			List<Map<String, Object>> jointContractInfoList = new ArrayList<>();
			for (ResJointOrg selfJointOrg : selfJointOrgList) {
				Map<String, Object> jointMap = new HashMap<>();
				jointMap.put("orgName", selfJointOrg.getJointOrgName());
				jointMap.put("orgFlow", selfJointOrg.getJointOrgFlow());
				jointMap.put("speId", selfJointOrg.getSpeId());
				jointMap.put("speName", selfJointOrg.getSpeName());
				jointMap.put("fileList", orgFlowToFileMap.get(selfJointOrg.getJointFlow()));
				jointContractInfoList.add(jointMap);
			}
			mav.addObject("jointContractList", jointContractInfoList);
		}


		mav.addObject("unjointOrgList", unjointOrgList);
	}

	private void baseInfoSet(ModelAndView mav, BaseExtInfoForm baseExtInfoForm, SysOrg sysOrg, BaseExtInfo baseExtInfo) {
		mav.addObject("basicInfo", baseExtInfoForm.getBasicInfo());    //基本信息
		if (baseExtInfoForm.getSysOrg() != null) { // 兼容历史数据
			SysOrg sysOrgForm = baseExtInfoForm.getSysOrg();
			sysOrgForm.setOrgProvId(sysOrg.getOrgProvId());
			sysOrgForm.setOrgCityId(sysOrg.getOrgCityId());
			sysOrgForm.setOrgAreaId(sysOrg.getOrgAreaId());
			sysOrgForm.setOrgProvName(sysOrg.getOrgProvName());
			sysOrgForm.setOrgCityName(sysOrg.getOrgCityName());
			sysOrgForm.setOrgAreaName(sysOrg.getOrgAreaName());
			sysOrgForm.setOrgName(sysOrg.getOrgName());
			mav.addObject("sysOrg", sysOrgForm); // 组织信息
		}
		mav.addObject("educationInfo", baseExtInfoForm.getEducationInfo());    //教学条件
		mav.addObject("organizationManage", baseExtInfoForm.getOrganizationManage());    //组织管理
		mav.addObject("supportCondition", baseExtInfoForm.getSupportCondition());    //支撑条件
		if(baseExtInfo != null) {
			mav.addObject("baseExtInfoEducationInfo", baseExtInfo.getBaseExtInfoEducationInfo()); // 扩展的教学条件
		}
	}

	/**
	 * 保存基本信息
	 *
	 * @param form
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveBase")
	@ResponseBody
	public String saveBase(String flag, BaseInfoForm form, String index, String type,
						   String fileFlows[], HttpServletRequest request,
						   String[] jointOrgFlows, String[] speIds, String[] fileUploadNum, String[] jointContractFileFlows, String[] fileRemainNum,
						   BaseExtInfo baseExtInfo) throws Exception {
        if (com.pinde.core.common.GlobalConstant.BASIC_INFO.equals(flag)) {
			if (form == null || form.getBasicInfo() == null || form.getBasicInfo().getContactManList() == null) {
				return "请填写联络员数据";
			}
		}

		int result = baseBiz.saveBaseInfo(flag, form, index, type, fileFlows, request, jointOrgFlows, speIds, fileUploadNum, jointContractFileFlows, fileRemainNum, baseExtInfo);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}


	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setAutoGrowNestedPaths(true);
		binder.setAutoGrowCollectionLimit(1024);
	}

	//下载附件
	@RequestMapping(value = {"/downFile"}, method = RequestMethod.GET)
	public void fileDown(String fileFlow, final HttpServletResponse response) throws Exception {
		PubFile file = this.pubFileBiz.readFile(fileFlow);
		downPubFile(file, response);
	}

	public void downPubFile(PubFile file, HttpServletResponse response) throws Exception {
		/*文件是否存在*/
		Boolean fileExists = false;
		if (file != null) {
			byte[] data = null;
			long dataLength = 0;
			/*如果上传类型为“1”(文件保存在磁盘)，且文件相对路径不为空*/
			if (StringUtil.isNotBlank(file.getFilePath()) && StringUtil.isNotBlank(InitConfig.getSysCfg("upload_base_dir"))) {
				/*获取文件物理路径*/
				String filePath = InitConfig.getSysCfg("upload_base_dir") + file.getFilePath();
				File downLoadFile = new File(filePath);
				/*文件是否存在*/
				if (downLoadFile.exists()) {
					InputStream fis = new BufferedInputStream(new FileInputStream(filePath));
					data = new byte[fis.available()];
					dataLength = downLoadFile.length();
					fis.read(data);
					fis.close();
					fileExists = true;
				}
			}
			if (fileExists) {
				try {

					String fileName = file.getFileName();
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gbk"), "ISO8859-1") + "\"");
					response.addHeader("Content-Length", "" + dataLength);
					response.setContentType("application/octet-stream;charset=UTF-8");
					OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
					if (data != null) {
						outputStream.write(data);
					}
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					fileExists = false;
				}
			}
		} else {
			fileExists = false;
		}
		if (!fileExists) {
			/*设置页面编码为UTF-8*/
			response.setHeader("Content-Type", "text/html;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}

	@RequestMapping("/orgShowSpeInfo")
	public String orgShowSpeInfo(String speFlow, Model model, String orgFlow, String isJoin, String sessionNumber, String onlyRead, String ishos) {
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		model.addAttribute("isJoin", isJoin);
		if (StringUtil.isBlank(sessionNumber)) {
			sessionNumber = DateUtil.getYear();
		}
		model.addAttribute("sessionNumber", sessionNumber);
		model.addAttribute("onlyRead", onlyRead);
		model.addAttribute("ishos", ishos);
		return "jsres/speAdmin/speInfo/main";
	}


	@RequestMapping("/showSpeInfo")
	public String showSpeInfo(String speFlow, Model model, String orgFlow, String isJoin, String sessionNumber) {
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("sessionNumber", sessionNumber);
		return "jsres/speAdmin/speInfo/main";
	}


	@RequestMapping("/showTrainingDetailInfo")
	public String showTraingingDetailInfo(String deptFlow, String speFlow, Model model, String orgFlow, String isJoin, String onlyRead, String isglobal, String sessionNumber) {
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("deptFlow", deptFlow);
		model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("onlyRead", onlyRead);
		model.addAttribute("isglobal", isglobal);
		model.addAttribute("sessionNumber", sessionNumber);

		ResBaseSpeDept baseSpeDept = deptBasicInfoBiz.readByOrgAndDept(orgFlow, deptFlow,sessionNumber);
		if (null!=baseSpeDept && StringUtil.isNotBlank(baseSpeDept.getBaseInfo())){
			String Xml=baseSpeDept.getBaseInfo();
			if (StringUtil.isNotBlank(Xml)) {
				BaseSpeDeptExtForm speDeptExtForm= JaxbUtil.converyToJavaBean(Xml, BaseSpeDeptExtForm.class);
				TrainingForm form = speDeptExtForm.getTrainingForm();
				model.addAttribute("trainingForm",form);
			}
		}
		//查询附件
        List<PubFile> files = pubFileBiz.findFileByTypeFlow(com.pinde.core.common.GlobalConstant.TRAINING, orgFlow + deptFlow);
		Map<String,PubFile> fileMap=new HashMap<>();
		if(files!=null&&files.size()>0)
		{
			for(PubFile f:files)
			{
				fileMap.put(f.getFileUpType(),f);
			}
		}
		model.addAttribute("fileMap",fileMap);
		model.addAttribute("files", files);

		model.addAttribute("baseSpeDept", baseSpeDept);

		return "jsres/kzr/mainInfo/trainingDetail";
	}

	@RequestMapping("/showDiagDiseaseDetailInfo")
	public String showDiagDiseaseDetailInfo(String deptFlow, String speFlow, Model model, String orgFlow, String isJoin, String onlyRead, String isglobal, String sessionNumber, String standardDeptId) {
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("deptFlow", deptFlow);
		model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("onlyRead", onlyRead);
		model.addAttribute("isglobal", isglobal);
		model.addAttribute("sessionNumber", sessionNumber);

		Map<String, String> paramMap=new HashMap<>();

		if(StringUtils.isEmpty(standardDeptId)) {
			SchAndStandardDeptCfg schAndStandardDeptCfg = deptCfgBiz.readBySchDeptFlowAndOrgFlow(deptFlow, orgFlow);
			if(null != schAndStandardDeptCfg) {
				standardDeptId = schAndStandardDeptCfg.getStandardDeptId();
			}
		}

		paramMap.put("standardDeptId",standardDeptId);
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("speFlow",speFlow);
		paramMap.put("stype","DiagDisease");
		paramMap.put("dtype","dept");
		paramMap.put("deptFlow",deptFlow);
		//查询单个指标的数据（诊疗疾病范围所有指标，医疗设备仪器最上面表中的指标）
		paramMap.put("sessionNumber",sessionNumber);
		List<Map<String, String>> infoList = deptBasicInfoBiz.searchResBaseSpeDeptInfoData(paramMap);
		if (null!=infoList && infoList.size()>0){
			model.addAttribute("infoList",infoList);
			model.addAttribute("tableAllArrNum",infoList.get(0).get("arrNum"));
		}

		return "jsres/kzr/mainInfo/diagDiseaseDetail";
	}

	@RequestMapping("/showEquipmentInstrumentsDetailInfo")
	public String showEquipmentInstrumentsDetailInfo(String deptFlow, String speFlow, Model model, String orgFlow, String isJoin, String onlyRead, String isglobal, String sessionNumber, String standardDeptId) {
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("deptFlow", deptFlow);
		model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("onlyRead", onlyRead);
		model.addAttribute("isglobal", isglobal);
		model.addAttribute("sessionNumber", sessionNumber);

		Map<String, String> paramMap=new HashMap<>();
		paramMap.put("standardDeptId",standardDeptId);
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("speFlow",speFlow);
		paramMap.put("stype","EquipmentInstruments");
		paramMap.put("dtype","dept");
		paramMap.put("deptFlow",deptFlow);
		paramMap.put("standardDeptId",speFlow);

		paramMap.put("sessionNumber",sessionNumber);
		List<Map<String, String>> infoList = deptBasicInfoBiz.searchResBaseSpeDeptInfoData(paramMap);
		if (null!=infoList && infoList.size()>0){
			model.addAttribute("infoList",infoList);
			model.addAttribute("tableAllArrNum",infoList.get(0).get("arrNum"));
		}
		// todo 这个东西要特别看一下 查询单个指标的数据（诊疗疾病范围所有指标，医疗设备仪器最上面表中的指标）
		// 医疗设备仪器表 重症监护科3500 是两张表，数据需要查询两次
		if (speFlow.equals("3500")){
			paramMap.put("standardDeptId","350001");
			List<Map<String, String>> infoList2 = deptBasicInfoBiz.searchResBaseSpeDeptInfoData(paramMap);
			if (null!=infoList2 && infoList2.size()>0){
				model.addAttribute("infoList2",infoList2);
				model.addAttribute("tableAllArrNum2",infoList2.get(0).get("arrNum"));
			}
		}

		return "jsres/kzr/mainInfo/equipmentInstrumentsDetail";
	}

	@RequestMapping("/showDeptInfo")
	public String showDeptInfo(String deptFlow, String speFlow, Model model, String orgFlow, String isJoin, String onlyRead, String isglobal
			, String viewFlag, String rotationRequire) {
		model.addAttribute("speFlow", speFlow);
		SysDept deptQuery = new SysDept();
		deptQuery.setDeptFlow(deptFlow);
		deptQuery.setOrgFlow(orgFlow);
		deptQuery.setRotationRequire(rotationRequire);
		List<Map<String, String>> speList = sysDeptExtMapper.searchDeptByUnionSpe(deptQuery, null);
		speList = speList.stream().filter(map -> StringUtils.isNotEmpty(map.get("speFlow"))).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(speList) && StringUtils.isNotEmpty(speFlow)) {
			Map<String, String> speMap = new HashMap<>();
			speMap.put("speFlow", speFlow);
			speList.add(speMap);
		}
		if(CollectionUtils.isNotEmpty(speList)) {
			speList.forEach(map -> {
                map.put("speName", com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(map.get("speFlow")));
			});
		}
		model.addAttribute("speList" , speList);

		model.addAttribute("deptFlow", deptFlow);
		model.addAttribute("orgFlow", orgFlow);
        if (!com.pinde.core.common.GlobalConstant.FLAG_Y.equals(viewFlag)) {
            model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
		}else {
            model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("onlyRead", onlyRead);
		model.addAttribute("isglobal", isglobal);
		return "jsres/kzr/mainInfo/main";
	}

	// 查询标准科室下各轮转科室的信息
	@RequestMapping("/showStandardDeptInfo")
	public String showStandardDeptInfo(String standardDeptFlow, String speFlow, Model model, String orgFlow, String isJoin, String onlyRead, String isglobal, String rotationRequire) {
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("onlyRead", onlyRead);
		model.addAttribute("isglobal", isglobal);
		model.addAttribute("rotationRequire", rotationRequire);

		// 查未被禁用的基地科室
		SysDeptVO sysDeptVO = new SysDeptVO();
		sysDeptVO.setStandardDeptFlow(standardDeptFlow);
		sysDeptVO.setOrgFlow(orgFlow);
        sysDeptVO.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<SysDeptVO> sysDeptVOList = deptManagementBiz.selectSysDeptRelStdDept(sysDeptVO);

		model.addAttribute("resDeptRelStdDeptVOList", sysDeptVOList);
		return "jsres/kzr/mainInfo/standardDeptInfo";
	}

	@RequestMapping("/showTrainingInfo")
	public String showTrainingInfo(String standardDeptFlow, String speFlow, Model model, String orgFlow, String isJoin, String onlyRead, String isglobal, String sessionNumber) {
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("onlyRead", onlyRead);
		model.addAttribute("isglobal", isglobal);
		model.addAttribute("sessionNumber", sessionNumber);

		// 查未被禁用的基地科室
		SysDeptVO sysDeptVO = new SysDeptVO();
		sysDeptVO.setStandardDeptFlow(standardDeptFlow);
        sysDeptVO.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		sysDeptVO.setOrgFlow(orgFlow);
		List<SysDeptVO> sysDeptVOList = deptManagementBiz.selectSysDeptRelStdDept(sysDeptVO);
		model.addAttribute("resDeptRelStdDeptVOList", sysDeptVOList);
		return "jsres/kzr/mainInfo/trainingInfo";
	}

	@RequestMapping("/showDiagDiseaseInfo")
	public String showDiagDiseaseInfo(String standardDeptFlow, String speFlow, Model model, String orgFlow, String isJoin, String onlyRead, String isglobal, String sessionNumber) {
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("onlyRead", onlyRead);
		model.addAttribute("isglobal", isglobal);
		model.addAttribute("sessionNumber", sessionNumber);
		model.addAttribute("standardDeptFlow", standardDeptFlow);

		ResDeptRelStdDeptVO deptRelStdDeptVO = new ResDeptRelStdDeptVO();
		SysDeptVO sysDeptVO = new SysDeptVO();
		sysDeptVO.setStandardDeptFlow(standardDeptFlow);
		sysDeptVO.setOrgFlow(orgFlow);
        sysDeptVO.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<SysDeptVO> sysDeptVOList = deptManagementBiz.selectSysDeptRelStdDept(sysDeptVO);
		model.addAttribute("resDeptRelStdDeptVOList", sysDeptVOList);
		return "jsres/kzr/mainInfo/diagDiseaseInfo";
	}

	@RequestMapping("/showEquipmentInstrumentsInfo")
	public String showEquipmentInstrumentsInfo(String standardDeptFlow, String speFlow, Model model, String orgFlow, String isJoin, String onlyRead, String isglobal, String sessionNumber) {
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("orgFlow", orgFlow);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("onlyRead", onlyRead);
		model.addAttribute("isglobal", isglobal);
		model.addAttribute("sessionNumber", sessionNumber);
		model.addAttribute("standardDeptFlow", standardDeptFlow);

		SysDeptVO sysDeptVO = new SysDeptVO();
		sysDeptVO.setStandardDeptFlow(standardDeptFlow);
		sysDeptVO.setOrgFlow(orgFlow);
        sysDeptVO.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<SysDeptVO> sysDeptVOList = deptManagementBiz.selectSysDeptRelStdDept(sysDeptVO);
		model.addAttribute("resDeptRelStdDeptVOList", sysDeptVOList);
		return "jsres/kzr/mainInfo/equipmentInstrumentsInfo";
	}

	@RequestMapping("/editSpeInfo")
	public String editSpeInfo(String speFlow, Model model, String orgFlow, String isJoin, String sessionNumber) {
		model.addAttribute("sessionNumber", sessionNumber);
		model.addAttribute("speFlow", speFlow);
		model.addAttribute("orgFlow", orgFlow);
		model.addAttribute("isJoin", isJoin);
        model.addAttribute("viewFlag", com.pinde.core.common.GlobalConstant.FLAG_N);
        model.addAttribute("editFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		return "jsres/speAdmin/speInfo/main";
	}


	//**********************************   基地专业管理  **************************************************

	/**
	 * 保存专业基地信息
	 *
	 * @param form
	 * @return
	 */
	@RequestMapping("/saveTrainSpe")
	@ResponseBody
	public String saveTrainSpe(@RequestBody ResOrgSpeForm form, String trainCategoryTypeId) {
		String orgFlow = form.getOrgFlow();
		if (StringUtil.isNotBlank(orgFlow)) {
			String orgName = form.getOrgName();
			List<ResOrgSpe> resBaseSpeList = form.getOrgSpeList();
			int result = baseBiz.saveTrainSpe(resBaseSpeList, trainCategoryTypeId, orgFlow, orgName);
            if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 提交操作
	 *
	 * @param baseFlow
	 * @return
	 */
	@RequestMapping("/submitBaseInfo")
	@ResponseBody
	public String submitBaseInfo(String baseFlow) {
		ResBase resBase = new ResBase();
		resBase.setOrgFlow(baseFlow);
        resBase.setBaseStatusId(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getId());
        resBase.setBaseStatusName(com.pinde.core.common.enums.ResDoctorAuditStatusEnum.Auditing.getName());
		int result = baseBiz.saveResBase(resBase);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 跳转界面
	 *
	 * @return
	 */
	@RequestMapping("/hospitalMain")
	public String hospitalMain(Model model) {
		return "jsres/city/hospital/hospitalMain";
	}

	@RequestMapping("/main")
	public String main(Model model, String baseFlow, String isJoin, String ishos, String sessionNumber, String menu) {
		if (StringUtil.isBlank(sessionNumber)) {
			sessionNumber = DateUtil.getYear();
		}
		ResBase resBase = baseBiz.readBaseBySessionNumber(baseFlow, sessionNumber);
		List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByOrgFlow(baseFlow);
		model.addAttribute("jointOrgs", jointOrgs);
		model.addAttribute("resBase", resBase);
		model.addAttribute("isJoin", isJoin);    //是否是协同基地 Y是
		model.addAttribute("baseFlow", baseFlow);
		model.addAttribute("ishos", ishos);
		model.addAttribute("sessionNumber", sessionNumber);
		if (menu != null && menu.length() > 0) {
			return "jsres/hospital/hos/" + menu;
		}

        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(ishos) || com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isJoin)) { // 省厅用原来的页面，有专业基地的， 协同单位管理也用原来的有专业基地的页面
			return "jsres/hospital/hos/mainKjc";
		}else {
			return "jsres/hospital/hos/main";
		}
	}

	/**
	 * 跳转页面用于选审核专业
	 *
	 * @return
	 */
	@RequestMapping("/spePage")
	public String spePage() {
		return "jsres/city/hospital/trainSpeMainView";
	}

	/**
	 * 跳转页面用于tag跳转
	 *
	 * @return
	 */
	@RequestMapping("/trainSpeMain")
	public String trainSpeMain(String orgFlow, Model model, String isJoin, String ishos, String sessionNumber) {
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("ishos", ishos);
		model.addAttribute("orgFlow", orgFlow);
		model.addAttribute("sessionNumber", sessionNumber);

		List<ResJointOrg> resJointOrgList = searchJointOrgList(orgFlow, sessionNumber);
		model.addAttribute("resJointOrgList", resJointOrgList);

		return "jsres/hospital/hos/trainSpeMain";
	}

	@RequestMapping(value = "searchJointOrgList")
	@ResponseBody
	public List<ResJointOrg> searchJointOrgList(String orgFlow, String sessionNumber) {
		if(StringUtils.isAnyBlank(orgFlow, sessionNumber)) {
			return Collections.emptyList();
		}
		// 查询协同基地，页面可以主协数据一起展示
		ResJointOrgExample resJointOrgExample = new ResJointOrgExample();
        resJointOrgExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
				.andOrgFlowEqualTo(orgFlow).andSessionNumberEqualTo(sessionNumber);
		List<ResJointOrg> resJointOrgList = jointOrgBiz.readResJointOrgByExample(resJointOrgExample);

		return resJointOrgList;
	}

	@RequestMapping("trainSpeBaseCapacitySave")
	@ResponseBody
	public String trainSpeBaseCapacitySave(String recordFlow, String baseCapacity, HttpServletRequest request) {
		if(StringUtils.isAnyBlank(recordFlow, baseCapacity)) {
			return "入参不完整";
		}

		Map<String, Object> param = new HashMap<>();
		param.put("orgSpeFlow", recordFlow);
		param.put("baseCapacity", baseCapacity);
		param.put("modifyTime", DateUtil.getCurrDateTime());
		SysUser currUser = GlobalContext.getCurrentUser();
		if(null != currUser) {
			param.put("modifyUserFlow", currUser.getUserFlow());
		}else {
			param.put("modifyUserFlow", "system");
		}
		speAssignBiz.updateOrgSpeByFlow(param);
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 可以主基地和协同单位信息一起查，汇总到一起
	 * @param orgFlow
	 * @param model
	 * @param isJoin
	 * @param ishos
	 * @param sessionNumber
	 * @param speId
	 * @param speOpt
	 * @return
	 */
	@RequestMapping("/trainSpeMainInfo")
	public String trainSpeMainInfo(String orgFlow, Model model, String isJoin, String ishos, String sessionNumber, String speId, String[] speOpt) {
		// 当没有协同单位时，没有主协单位选择，只有一个orgFlow，这里处理一下，使有没有主协单位选择时的操作一致
		if(ArrayUtils.isEmpty(speOpt)) {
			speOpt = new String[]{orgFlow};
		}
		model.addAttribute("isJoin", isJoin);
		model.addAttribute("ishos", ishos);
		model.addAttribute("orgFlow", orgFlow);
		model.addAttribute("sessionNumber", sessionNumber);
		model.addAttribute("speId", speId);
		model.addAttribute("orgFlowList", Arrays.stream(speOpt).collect(Collectors.toList()));
		if (StringUtil.isNotBlank(orgFlow)) {
			List<Map<String, String>> deptMap = deptCfgBiz.searchByLastRotation(orgFlow);
			model.addAttribute("deptMap", deptMap);
		}
		// 非当年时，只展示专业基地编码，专业基地名称，住院医师(xx级在培），在校专硕(xx级在培)，当年时展示全部
		Map<String, String> paramMap = new HashMap<>();
//		paramMap.put("orgFlow", orgFlow);
		paramMap.put("orgFlowList", String.join(",", speOpt));
		paramMap.put("assignYear", sessionNumber);
		paramMap.put("speId", speId);
		List<Map<String, String>> orgSpeList = speAssignBiz.searchAssignOrgSpeList(paramMap);
		for (Map<String, String> orgSpeMap : orgSpeList) { // 特殊处理：把助理全科给去掉
			if("50".equals(orgSpeMap.get("SPE_ID"))) {
				orgSpeList.remove(orgSpeMap);
				break;
			}
		}
		Map<String, List<Map<String, String>>> speIdToListMap = orgSpeList.stream().collect(Collectors.groupingBy(vo -> vo.get("SPE_ID")));
		List<Map<String, String>> orgSpeTempList = new ArrayList<>();
		speIdToListMap.forEach((key, values) -> {
			orgSpeTempList.add(values.get(0));
		});
		orgSpeList = orgSpeTempList; // 改成了多主协单位，但为了改动小，结构还和之前一致

		ResOrgSpeExample orgSpeExample = new ResOrgSpeExample();
        orgSpeExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andSessionYearEqualTo(sessionNumber).andOrgFlowIn(Arrays.stream(speOpt).collect(Collectors.toList()));
		List<ResOrgSpe> resOrgSpeList = speAssignBiz.findOrgSpeByExample(orgSpeExample);
		Map<String, List<ResOrgSpe>> speIdToEntityListMap = resOrgSpeList.stream().collect(Collectors.groupingBy(vo -> vo.getSpeId()));
		boolean mainBase = speOpt.length == 1 && orgFlow.equals(speOpt[0]);
		model.addAttribute("mainBase", mainBase);
		orgSpeList.forEach(orgSpe -> {
			String curSpeId = orgSpe.get("SPE_ID");
			List<ResOrgSpe> resOrgSpeList2 = speIdToEntityListMap.get(curSpeId);
			if(CollectionUtils.isNotEmpty(resOrgSpeList2)) {
				if(mainBase) {
					orgSpe.put("ORG_SPE_FLOW", resOrgSpeList2.get(0).getOrgSpeFlow());
				}
				orgSpe.put("BASE_CAPACITY", resOrgSpeList2.stream().filter(vo -> StringUtils.isNotEmpty(vo.getBaseCapacity())).map(vo -> Integer.parseInt(vo.getBaseCapacity()))
						.reduce(0, (vo1, vo2) -> vo1 + vo2).toString());
			}
		});


		if (StringUtil.isNotBlank(speId) && CollectionUtils.isEmpty(orgSpeList)) {
			HashMap<String, String> map = new HashMap<>();
			map.put("SPE_ID", speId);
            map.put("SPE_NAME", dictBiz.readDict(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId(), speId).getDictName());
			List<ResOrgSpe> resOrgSpeList2 = speIdToEntityListMap.get(speId);
			if(CollectionUtils.isNotEmpty(resOrgSpeList2)) {
				if(mainBase) {
					map.put("ORG_SPE_FLOW", resOrgSpeList2.get(0).getOrgSpeFlow());
				}
				map.put("BASE_CAPACITY", resOrgSpeList2.stream().filter(vo -> StringUtils.isNotEmpty(vo.getBaseCapacity())).map(vo -> Integer.parseInt(vo.getBaseCapacity()))
						.reduce(0, (vo1, vo2) -> vo1 + vo2).toString());
			}
			orgSpeList.add(map);
		}
		if (StringUtil.isBlank(speId)) {
			List<String> speIdList = orgSpeList.stream().map(e -> e.get("SPE_ID")).collect(Collectors.toList());
            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
			List<String> dictIdList = sysDictList.stream().map(SysDict::getDictId).collect(Collectors.toList());
			for (String dictId : dictIdList) {
				if (!speIdList.contains(dictId) && !dictId.equals("50") && speIdToEntityListMap.get(dictId) != null) {
					HashMap<String, String> map = new HashMap<>();
					map.put("SPE_ID", dictId);
                    map.put("SPE_NAME", dictBiz.readDict(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId(), dictId).getDictName());
					List<ResOrgSpe> resOrgSpeList2 = speIdToEntityListMap.get(dictId);
					if(CollectionUtils.isNotEmpty(resOrgSpeList2)) {
						if(mainBase) {
							map.put("ORG_SPE_FLOW", resOrgSpeList2.get(0).getOrgSpeFlow());
						}
						map.put("BASE_CAPACITY", resOrgSpeList2.stream().filter(vo -> StringUtils.isNotEmpty(vo.getBaseCapacity())).map(vo -> Integer.parseInt(vo.getBaseCapacity()))
								.reduce(0, (vo1, vo2) -> vo1 + vo2).toString());
					}
					orgSpeList.add(map);
				}
			}
		}

		String curYear = DateUtil.getYear();
		// 状态的处理，如果有一家是open，那结果是open，
		// 如果没有open，有一家是stop，那结果是stop
		// 如果没有open，没有stop,即只有close，那结果是close
		for (Map<String, String> orgSpe : orgSpeList) {
			ResOrgSpeExample resOrgSpeExample = new ResOrgSpeExample();
            resOrgSpeExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSpeIdEqualTo(orgSpe.get("SPE_ID"))
					.andOrgFlowIn(Arrays.stream(speOpt).collect(Collectors.toList())).andSessionYearEqualTo(sessionNumber);
			List<ResOrgSpe> orgSpeList2 = speAssignBiz.findOrgSpeByExample(resOrgSpeExample);
			if(CollectionUtils.isEmpty(orgSpeList2)) {
				orgSpe.put("STATUS", "close");
			}else {
				ResOrgSpe resOrgSpe = orgSpeList2.stream().filter(vo -> "open".equals(vo.getStatus())).findFirst().orElse(null);
				if(resOrgSpe != null) {
					orgSpe.put("STATUS", "open");
				}else {
					resOrgSpe = orgSpeList2.stream().filter(vo -> "stop".equals(vo.getStatus())).findFirst().orElse(null);
					if(resOrgSpe != null) {
						orgSpe.put("STATUS", "stop");
					}else {
						orgSpe.put("STATUS", "close");
					}
				}
			}

			if(curYear.equals(sessionNumber)) {
				String minRecruitCapacity = orgSpeList2.stream().filter(vo -> StringUtils.isNotEmpty(vo.getMinRecruitCapacity())).map(vo -> Integer.parseInt(vo.getMinRecruitCapacity()))
						.reduce(0, (vo1, vo2) -> vo1 + vo2).toString();
				orgSpe.put("minRecruitCapacity", minRecruitCapacity);
			}
		}

		if(curYear.equals(sessionNumber)) {
			// 在培住院医师
			Map<String, Object> doctorRecruitMap = new HashMap<>();
			doctorRecruitMap.put("doctorStatusId", "20");
			doctorRecruitMap.put("orgFlowList", Arrays.stream(speOpt).collect(Collectors.toList()));
			doctorRecruitMap.put("trainYearList", Arrays.asList("OneYear", "TwoYear", "ThreeYear"));
			doctorRecruitMap.put("docTypeList", Arrays.asList("Company", "CompanyEntrust", "Social"/*, "Graduate"*/));
			// 查医院全部在培人员，sql来自【学员信息查询】页面列表sql
			List<Map<String, Object>> speTrainCountList = jsResDoctorRecruitExtMapper.countTrainingDoctor(doctorRecruitMap);
			Map<Object, Map<String, Object>> speTrainCountMap = speTrainCountList.stream().collect(Collectors.toMap(vo -> vo.get("SPEID"), vo -> vo, (vo1, vo2) -> vo1));

			for (Map<String, String> orgSpe : orgSpeList) {
				String tempSpeId = orgSpe.get("SPE_ID");
				if (speTrainCountMap.get(tempSpeId) != null) {
						orgSpe.put("zyysTotal", ((BigDecimal) speTrainCountMap.get(tempSpeId).get("TRAININGCOUNT")).toPlainString());
				}
			}

			// 在培在校专硕
			doctorRecruitMap = new HashMap<>();
			doctorRecruitMap.put("doctorStatusId", "20");
			doctorRecruitMap.put("orgFlowList", Arrays.stream(speOpt).collect(Collectors.toList()));
			doctorRecruitMap.put("trainYearList", Arrays.asList("OneYear", "TwoYear", "ThreeYear"));
			doctorRecruitMap.put("docTypeList", Arrays.asList("Graduate"));
			// 查医院全部在培人员，sql来自【学员信息查询】页面列表sql
			speTrainCountList = jsResDoctorRecruitExtMapper.countTrainingDoctor(doctorRecruitMap);
			speTrainCountMap = speTrainCountList.stream().collect(Collectors.toMap(vo -> vo.get("SPEID"), vo -> vo, (vo1, vo2) -> vo1));

			for (Map<String, String> orgSpe : orgSpeList) {
				String tempSpeId = orgSpe.get("SPE_ID");
				if (speTrainCountMap.get(tempSpeId) != null) {
					orgSpe.put("zxzsTotal", ((BigDecimal) speTrainCountMap.get(tempSpeId).get("TRAININGCOUNT")).toPlainString());
				}

				String zyysTotal = orgSpe.get("zyysTotal");
				String zxzsTotal = orgSpe.get("zxzsTotal");
				Integer trainingSumTotal = null;
				if(StringUtils.isNotEmpty(zyysTotal)) {
					trainingSumTotal = Integer.parseInt(zyysTotal);
				}
				if(StringUtils.isNotEmpty(zxzsTotal)) {
					if(trainingSumTotal == null) {
						trainingSumTotal = Integer.parseInt(zxzsTotal);
					}else {
						trainingSumTotal += Integer.parseInt(zxzsTotal);
					}
				}

				orgSpe.put("trainingSumTotal", trainingSumTotal == null ? "" : String.valueOf(trainingSumTotal));
			}

			for (Map<String, String> orgSpe : orgSpeList) {
				String trainingSumTotal = orgSpe.get("trainingSumTotal");
				String baseCapacity = orgSpe.get("BASE_CAPACITY");
				if (StringUtils.isNotEmpty(trainingSumTotal) && StringUtils.isNotEmpty(baseCapacity) && !"0".equals(baseCapacity)) {
					int trainingSumTotalInt = Integer.parseInt(trainingSumTotal);
					int baseCapacityInt = Integer.parseInt(baseCapacity);
					double capacityUsePercent = Math.round((double) trainingSumTotalInt * 1000 / baseCapacityInt);
					capacityUsePercent /= 10;
					orgSpe.put("capacityUsePercent", Double.toString(capacityUsePercent));
				}
			}
		}

		orgSpeList.sort(Comparator.comparingInt(o -> o.get("SPE_ID").hashCode()));
		model.addAttribute("orgSpeList", orgSpeList);

		HashMap<String, String> resultMap = new HashMap<>();
		//查询各专业基地的负责人、教学主任、教学秘书
		/*List<ResBaseSpeDept> speDeptList = deptBasicInfoBiz.readByOrgFlowAndYear(orgFlow, sessionNumber);
		for (ResBaseSpeDept speDept : speDeptList) {
			BaseSpeDeptExtForm baseSpeDeptExtForm = JaxbUtil.converyToJavaBean(speDept.getBaseInfo(), BaseSpeDeptExtForm.class);
			if(baseSpeDeptExtForm != null && baseSpeDeptExtForm.getDeptBasicInfoForm() != null){ // 先判个空
				DeptBasicInfoForm infoForm = baseSpeDeptExtForm.getDeptBasicInfoForm();
				resultMap.put("speRespName"+speDept.getSpeFlow(),infoForm.getSpeRespName());
				resultMap.put("speDirName"+speDept.getSpeFlow(),infoForm.getSpeDirName());
				resultMap.put("speSceName"+speDept.getSpeFlow(),infoForm.getSpeSceName());
			}
		}*/
		if(curYear.equals(sessionNumber)) {
			//查询各专业的师资人数
			List<Map<String, String>> szList = deptBasicInfoBiz.countByOrgListAndSpe(Arrays.stream(speOpt).collect(Collectors.toList()));
			for (Map<String, String> map : szList) {
				resultMap.put("sz" + map.get("speId"), map.get("num"));
			}

			//查询住院医师和在校专硕的人数
			Map<String, String> param = new HashMap<>();
			param.put("orgFlowList", String.join(",", speOpt));
			param.put("doctorType", "zyys");
			param.put("sessionNumber", sessionNumber);
			List<Map<String, Object>> zyysDoctorList = deptBasicInfoBiz.countDoctorNum(param);
			for (Map<String, Object> map : zyysDoctorList) {
				resultMap.put("zyys" + map.get("speId"), ((BigDecimal)map.get("num")).toPlainString());
			}
			param.put("doctorType", "zxzs");
			List<Map<String, Object>> zxzsDoctorList = deptBasicInfoBiz.countDoctorNum(param);
			for (Map<String, Object> map : zxzsDoctorList) {
				resultMap.put("zxzs" + map.get("speId"), ((BigDecimal)map.get("num")).toPlainString());
			}

			model.addAttribute("resultMap", resultMap);
		}
		return "jsres/hospital/hos/trainSpeMainInfo";
	}

	/**
	 * 基地专业管理
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/orgSpeManage")
	public String orgSpeManage(Model model) {
		SysOrg sysOrg = new SysOrg();
        sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
		sysOrg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			sysOrg.setOrgCityId(org.getOrgCityId());
		}
		List<SysOrg> orgList = orgBiz.searchOrg(sysOrg);
		model.addAttribute("orgList", orgList);
		model.addAttribute("sessionYear", DateUtil.getYear());
		return "jsres/system/sys/baseSpecialList";
	}

	/**
	 * 查询分数配置信息
	 *
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping("/loadScoreConf")
	public String loadScoreConf(Integer currentPage, ResPassScoreCfg resPassScoreCfg, Model model, HttpServletRequest request) {

//		ResPassScoreCfg scoreCfg = baseBiz.readResPassScoreCfg(resPassScoreCfg);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<ResPassScoreCfg> cfgList = baseBiz.readCfgs(resPassScoreCfg);
		model.addAttribute("cfgList", cfgList);
		return "jsres/system/sys/scoreCfgList";
	}

	/**
	 * 成绩合格线配置
	 *
	 * @return
	 */
	@RequestMapping("/scoreCfg")
	public String scoreCfg() {
		return "jsres/system/sys/scoreCfgQueryMain";
	}

	/**
	 * 编辑
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/setScoreConf")
	public String setScoreConf(String cfgYear, Model model) {
		ResPassScoreCfg scoreCfg = new ResPassScoreCfg();
		if (StringUtil.isNotBlank(cfgYear)) {
			scoreCfg.setCfgYear(cfgYear);
			ResPassScoreCfg resPassScoreCfg = baseBiz.readResPassScoreCfg(scoreCfg);
			model.addAttribute("resPassScoreCfg", resPassScoreCfg);
		}
		return "jsres/system/sys/addScore";
	}

	/**
	 * 删除
	 *
	 * @param cfgYear
	 * @return
	 */
	@RequestMapping("/delScoreConf")
	@ResponseBody
	public String delScoreConf(String cfgYear) {

		int result = baseBiz.delScoreConf(cfgYear);
        if (result > com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 保存基地专业信息
	 *
	 * @param orgSpe
	 * @return
	 */
	@RequestMapping("/saveOrgSpeManage")
	@ResponseBody
	public String saveOrgSpeManage(ResOrgSpe orgSpe) throws Exception {
		int result = 0;
		// 特殊标识，处理全部的org
        if (com.pinde.core.common.GlobalConstant.OPER_ALL.equals(orgSpe.getOrgFlow())) {
			// 与页面列表一样的查法，保证只更新页面上显示的那些医院
			SysOrg sysOrg = new SysOrg();
            sysOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			sysOrg.setOrgProvId(org.getOrgProvId());
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
				sysOrg.setOrgCityId(org.getOrgCityId());
			}
			List<SysOrg> orgList = orgBiz.searchOrg(sysOrg);
			if(CollectionUtils.isNotEmpty(orgList)) {
				resOrgSpeBiz.saveOrgSpeManageAll(orgSpe, orgList);
				return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
			}
		}else {
			result = resOrgSpeBiz.saveOrgSpeManage(orgSpe);
		}

        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 保存年份合格线配置
	 *
	 * @param resPassScoreCfg
	 * @param model
	 * @return
	 */
	@RequestMapping("/saveCfg")
	@ResponseBody
	public String saveCfg(ResPassScoreCfg resPassScoreCfg, Model model) {
		int result = baseBiz.savePassScoreCfg(resPassScoreCfg);
		if (result > 0) {
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 加载基地专业信息
	 *
	 * @param resOrgSpe
	 * @param model
	 * @return
	 */
	@RequestMapping("/serachOrgSpeList")
	public String serachOrgSpeList(ResOrgSpe resOrgSpe, Model model) {
        if (com.pinde.core.common.GlobalConstant.OPER_ALL.equals(resOrgSpe.getOrgFlow())) {
			model.addAttribute("orgFlow", resOrgSpe.getOrgFlow());
			model.addAttribute("orgName", resOrgSpe.getOrgName());
			model.addAttribute("sessionYear", resOrgSpe.getSessionYear());

            String desc = com.pinde.core.common.GlobalConstant.ORG_SPE_ALL_WS_ID + "_" + resOrgSpe.getSessionYear();
			SysOrg org = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			desc += "_" + org.getOrgProvId();

            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
				desc += "_" + org.getOrgCityId();
			}
			SysCfgExample example = new SysCfgExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andWsIdEqualTo(com.pinde.core.common.GlobalConstant.ORG_SPE_ALL_WS_ID)
							.andCfgDescEqualTo(desc);
			List<SysCfg> sysCfgList = cfgBiz.readByExample(example);
			Map<String, ResOrgSpe> orgSpeMap = new HashMap<String, ResOrgSpe>();
			for (SysCfg sysCfg : sysCfgList) {
				ResOrgSpe tempSpe = new ResOrgSpe();
				String cfgValue = sysCfg.getCfgValue();
				String status = "";
				String minCapacity = "";
				if(cfgValue != null) {
					String[] statusCapacity = cfgValue.split(",");
					status = statusCapacity[0];
					if(statusCapacity.length == 2) {
						minCapacity = statusCapacity[1];
					}
				}
				tempSpe.setStatus(status);
				tempSpe.setMinRecruitCapacity(minCapacity);
				// 去掉前缀 ，取后面的spetypeid+speid
				int idx = sysCfg.getCfgCode().lastIndexOf("_") + 1;
				orgSpeMap.put(sysCfg.getCfgCode().substring(idx), tempSpe);
				model.addAttribute("orgSpeMap", orgSpeMap);
			}
		}else {
			String orgFlow = resOrgSpe.getOrgFlow();
			model.addAttribute("orgFlow", orgFlow);
			model.addAttribute("orgName", resOrgSpe.getOrgName());
			model.addAttribute("sessionYear", resOrgSpe.getSessionYear());
			if (StringUtil.isNotBlank(orgFlow) && StringUtil.isNotBlank(resOrgSpe.getSessionYear())) {
				ResOrgSpe serach = new ResOrgSpe();
				serach.setOrgFlow(orgFlow);
                serach.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				serach.setSessionYear(resOrgSpe.getSessionYear());
				List<ResOrgSpe> resBaseList = resOrgSpeBiz.searchResOrgSpeList(serach);
				model.addAttribute("resBaseList", resBaseList);
				if (resBaseList != null && !resBaseList.isEmpty()) {
					Map<String, ResOrgSpe> orgSpeMap = new HashMap<String, ResOrgSpe>();
					for (ResOrgSpe os : resBaseList) {
						String key = os.getSpeTypeId() + os.getSpeId();
						orgSpeMap.put(key, os);
					}
					model.addAttribute("orgSpeMap", orgSpeMap);
				}
			}
		}
		return "jsres/system/sys/loadOrgSpeList";
	}

	@RequestMapping("/searchJOrgInfo")
	public String searchJOrgInfo(Model model, String orgFlow) {
		List<String> orgFlowList = new ArrayList<String>();
		if (StringUtil.isNotBlank(orgFlow)) {
			List<ResJointOrg> jointOrgList = jointOrgBiz.searchResJointByOrgFlow(orgFlow);
			if (jointOrgList != null && !jointOrgList.isEmpty()) {
				for (ResJointOrg org : jointOrgList) {
					orgFlowList.add(org.getJointOrgFlow());
				}
				List<SysOrg> orgList = orgBiz.searchOrgFlowIn(orgFlowList);
				model.addAttribute("orgList", orgList);
			}
		}
		return "jsres/global/hospital/basicGeneralDoc";
	}

	@RequestMapping("/baseInfo")
	public String baseInfo(Model model, String orgCityName, String type, Integer currentPage, HttpServletRequest request) {
		Map<String, String> jointFlagMap = new HashMap<String, String>();
		SysOrg sysOrg = new SysOrg();
		SysUser currUser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
		sysOrg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			sysOrg.setOrgCityId(org.getOrgCityId());
		}
        sysOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if (StringUtil.isNotBlank(type)) {
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(type)) {
                sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
            } else if (com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId().equals(type)) {
                sysOrg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
			}
		}
		if (StringUtil.isNotBlank(orgCityName)) {
			sysOrg.setOrgName(orgCityName);
		}
		if (currentPage == null)
			currentPage = 1;
		int pageSize = getPageSize(request);
		PageHelper.startPage(currentPage, pageSize);
		List<SysOrg> orgList = orgBiz.searchAllSysOrg(sysOrg);
		Map<String, ResBase> baseMap = new HashMap<>();
		Map<String, List<ResJointOrg>> jointOrgMap = new HashMap<>();
		if (orgList != null && !orgList.isEmpty()) {
			List<String> orgFlows = new ArrayList<>();
			for (SysOrg o : orgList) {
				orgFlows.add(o.getOrgFlow());
				List<ResJointOrg> jointOrgs = jointOrgBiz.searchResJointByOrgFlow(o.getOrgFlow());
				if (jointOrgs != null && !jointOrgs.isEmpty()) {
                    jointFlagMap.put(o.getOrgFlow(), com.pinde.core.common.GlobalConstant.FLAG_Y);
					jointOrgMap.put(o.getOrgFlow(), jointOrgs);
					for (ResJointOrg resJointOrg : jointOrgs) {
						orgFlows.add(resJointOrg.getJointOrgFlow());
					}
				}
			}
			if (orgFlows.size() > 0) {
				List<ResBase> resBases = baseBiz.readBaseList(orgFlows);
				if (resBases != null && !resBases.isEmpty()) {
					for (ResBase resBase : resBases) {
						baseMap.put(resBase.getOrgFlow(), resBase);
					}
				}
			}
		}
		model.addAttribute("jointOrgMap", jointOrgMap);
		model.addAttribute("baseMap", baseMap);
		model.addAttribute("jointFlagMap", jointFlagMap);
		model.addAttribute("orgList", orgList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pageSize", pageSize);
		return "jsres/global/hospital/basicGeneralDoc";
	}

	@RequestMapping(value = "/verification")
	@ResponseBody
	public String verification(String resultFlow) {
		SysUser user = GlobalContext.getCurrentUser();
		ResDoctorSchProcess doctorSchProcess = doctorProcessBiz.searchByResultFlow(resultFlow);
        String afterEvaluationId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
		if (doctorSchProcess != null) {
//			List<ResRec> recs=resRecBiz.searchResRecWithBLOBs(afterEvaluationId,doctorSchProcess.getProcessFlow(),user.getUserFlow());
			List<ResSchProcessExpress> recs = expressBiz.searchResRecExpressWithBLOBs(afterEvaluationId, doctorSchProcess.getProcessFlow());
			if (recs.size() > 0) {
                String cksh = com.pinde.core.common.GlobalConstant.FLAG_N;
				JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_" + user.getOrgFlow() + "_org_cksh");
				if (cfg != null) {
					cksh = cfg.getCfgValue();
				}
				ResSchProcessExpress rec = recs.get(0);
				// 如果配置需要科主任 才走该判断 禅道需求 2221
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cksh) && StringUtil.isBlank(rec.getHeadAuditStatusId()) && StringUtil.isNotBlank(rec.getManagerAuditUserFlow())) {
					return "-1";
				}
				Map<String, Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());

				String szkskhxzztpj = (String) formDataMap.get("szkskhxzztpj_id");
				if (!"1".equals(szkskhxzztpj)) {
					return "-2";
				}

                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;

	}

	@RequestMapping(value = "/relationDownload")
	public void relationDownload(String resultFlow, HttpServletRequest request, HttpServletResponse response) throws Exception {
		//定义数据容器
		final Map<String, Object> dataMap = new HashMap<String, Object>();

		//获取当前用户
		SysUser user = GlobalContext.getCurrentUser();

		//新建word模板
		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();

		//context获取路径
		ServletContext context = request.getServletContext();

		//水印
		String watermark = "";

		//文件名称
		String name = "";

		//获取个人基础信息
		ResDoctor doctor = resDoctorBiz.searchByUserFlow(user.getUserFlow());
		dataMap.put("userName", user.getUserName());
		dataMap.put("sessionNumber", doctor.getSessionNumber());
		dataMap.put("trainingSpeName", doctor.getTrainingSpeName());

		//轮转科室名称
		SchArrangeResult schArrangeResult = resultBiz.readSchArrangeResult(resultFlow);
		if (schArrangeResult != null) {
			dataMap.put("startDate", StringUtil.defaultIfEmpty(schArrangeResult.getSchStartDate(), " "));
			dataMap.put("endDate", StringUtil.defaultIfEmpty(schArrangeResult.getSchEndDate(), " "));
			dataMap.put("standardDeptName", schArrangeResult.getDeptName());
			dataMap.put("monthCount", schArrangeResult.getSchMonth());
		}

		//获取resrec数据
		ResDoctorSchProcess doctorSchProcess = doctorProcessBiz.searchByResultFlow(resultFlow);
		String processFlow = doctorSchProcess.getProcessFlow();
		String operUserFlow = user.getUserFlow();
		Map<String, Object> processPerMap = resRecBiz.getRecProgressIn(operUserFlow, processFlow, null, null);
		if (processPerMap == null) {
			processPerMap = new HashMap<String, Object>();
		}
		//获取不同类型并定义接受
		if (processPerMap != null) {
            String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
            String diseaseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
            String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
            String operationRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();

			String caseRegistry = (String) processPerMap.get(processFlow + caseRegistryId);
			String caseRegistryReqNum = (String) processPerMap.get(processFlow + caseRegistryId + "ReqNum");
			String caseRegistryFinished = (String) processPerMap.get(processFlow + caseRegistryId + "Finished");

			String diseaseRegistry = (String) processPerMap.get(processFlow + diseaseRegistryId);
			String diseaseRegistryReqNum = (String) processPerMap.get(processFlow + diseaseRegistryId + "ReqNum");
			String diseaseRegistryFinished = (String) processPerMap.get(processFlow + diseaseRegistryId + "Finished");

			String skillRegistry = (String) processPerMap.get(processFlow + skillRegistryId);
			String skillRegistryReqNum = (String) processPerMap.get(processFlow + skillRegistryId + "ReqNum");
			String skillRegistryFinished = (String) processPerMap.get(processFlow + skillRegistryId + "Finished");

			String skillAndOperationRegistry = (String) processPerMap.get(processFlow + operationRegistryId);
			String skillAndOperationRegistryReqNum = (String) processPerMap.get(processFlow + operationRegistryId + "ReqNum");
			String skillAndOperationRegistryFinished = (String) processPerMap.get(processFlow + operationRegistryId + "Finished");

            String recTypeIdt = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
//			int teachingRounds=0;
//			int difficult=0;
//			int lecture=0;
//			int death=0;
			int jxcf = 0;
			int xjk = 0;
			int rkjy = 0;
			int jnpx = 0;
			int yph = 0;
			int jxhz = 0;
			int jxbltl = 0;
			int lcczjnzd = 0;
			int lcblsxzd = 0;
			int ssczzd = 0;
			int yxzdbgsxzd = 0;
			int lcwxyd = 0;
			int ryjy = 0;
			int rzyjdjy = 0;
			int cjbg = 0;
			int ynbltl = 0;
			int wzbltl = 0;
			int swbltl = 0;
			int bgdfx = 0;
			int jxsj = 0;
			int sjys = 0;
			List<String> recTypes = new ArrayList<String>();
			recTypes.add(recTypeIdt);
			List<ResRec> recs = resRecBiz.searchRecByProcessWithBLOBs(recTypes, processFlow, user.getUserFlow());
			for (ResRec resRec : recs) {
				String content = resRec.getRecContent();
				Document document = DocumentHelper.parseText(content);
				Element root = document.getRootElement();
				Element ec = root.element("activity_way");
				if (ec != null) {
					String text = ec.attributeValue("id");
					if (Jxcf.equals(text)) {
						jxcf++;
					} else if (Ynbltl.equals(text)) {
						ynbltl++;
					} else if (Wzbltl.equals(text)) {
						wzbltl++;
					} else if (Xsjz.equals(text)) {
						xjk++;
					} else if (Swbltl.equals(text)) {
						swbltl++;
					} else if (Rkjy.equals(text)) {
						rkjy++;
					} else if (Ckks.equals(text)) {
//						ckkh++;
					} else if (Jnpx.equals(text)) {
						jnpx++;
					} else if (Yph.equals(text)) {
						yph++;
					} else if (Jxhz.equals(text)) {
						jxhz++;
					} else if (Jxbltl.equals(text)) {
						jxbltl++;
					} else if (Lcczjnzd.equals(text)) {
						lcczjnzd++;
					} else if (Lcblsxzd.equals(text)) {
						lcblsxzd++;
					} else if (Ssczzd.equals(text)) {
						ssczzd++;
					} else if (Yxzdbgsxzd.equals(text)) {
						yxzdbgsxzd++;
					} else if (Lcwxyd.equals(text)) {
						lcwxyd++;
					} else if (Ryjy.equals(text)) {
						ryjy++;
					} else if (Rzyjdjy.equals(text)) {
						rzyjdjy++;
					} else if (Cjbg.equals(text)) {
						cjbg++;
					} else if (Bgdfx.equals(text)) {
						bgdfx++;
					} else if (Jxsj.equals(text)) {
						jxsj++;
					} else if (Sjys.equals(text)) {
						sjys++;
					}
				}
			}
			List<TeachingActivityInfo> infos = resRecBiz.searchJoinActivityByProcessFlow(processFlow);
			String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			JsresPowerCfg orgApprove = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_org_ctrl_approve_activity");//教学活动评价配置
			JsresPowerCfg approve = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_org_approve_activity");//教学活动评价配置评审类型
            if (null != orgApprove && null != approve && StringUtil.isNotNullAndEquala(approve.getCfgValue(), orgApprove.getCfgValue(), com.pinde.core.common.GlobalConstant.FLAG_Y)) {        //开启必评
				infos = resRecBiz.searchJoinActivityByProcessFlownotScore(processFlow);
			} else {
				infos = resRecBiz.searchJoinActivityByProcessFlow(processFlow);
			}
			if (infos != null && infos.size() > 0) {
				for (TeachingActivityInfo info : infos) {
					String text = info.getActivityTypeId();
					if (Jxcf.equals(text)) {
						jxcf++;
					} else if (Ynbltl.equals(text)) {
						ynbltl++;
					} else if (Wzbltl.equals(text)) {
						wzbltl++;
					} else if (Xsjz.equals(text)) {
						xjk++;
					} else if (Swbltl.equals(text)) {
						swbltl++;
					} else if (Rkjy.equals(text)) {
						rkjy++;
					} else if (Ckks.equals(text)) {
//						ckkh++;
					} else if (Jnpx.equals(text)) {
						jnpx++;
					} else if (Yph.equals(text)) {
						yph++;
					} else if (Jxhz.equals(text)) {
						jxhz++;
					} else if (Jxbltl.equals(text)) {
						jxbltl++;
					} else if (Lcczjnzd.equals(text)) {
						lcczjnzd++;
					} else if (Lcblsxzd.equals(text)) {
						lcblsxzd++;
					} else if (Ssczzd.equals(text)) {
						ssczzd++;
					} else if (Yxzdbgsxzd.equals(text)) {
						yxzdbgsxzd++;
					} else if (Lcwxyd.equals(text)) {
						lcwxyd++;
					} else if (Ryjy.equals(text)) {
						ryjy++;
					} else if (Rzyjdjy.equals(text)) {
						rzyjdjy++;
					} else if (Cjbg.equals(text)) {
						cjbg++;
					} else if (Bgdfx.equals(text)) {
						bgdfx++;
					} else if (Jxsj.equals(text)) {
						jxsj++;
					} else if (Sjys.equals(text)) {
						sjys++;
					}
				}
			}
			dataMap.put("userName", user.getUserName());
			dataMap.put("sessionNumber", doctor.getSessionNumber());
			dataMap.put("trainingSpeName", doctor.getTrainingSpeName());

			dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

			dataMap.put("diseaseRegistry", StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
			dataMap.put("diseaseRegistryReqNum", StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
			dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

			dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

			dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
			dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
			dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

			dataMap.put("jxcf", String.valueOf(jxcf));
//			dataMap.put("ynbltl",String.valueOf(ynbltl));
//			dataMap.put("wzbltl",String.valueOf(wzbltl));
			dataMap.put("xjk", String.valueOf(xjk));
//			dataMap.put("swbltl",String.valueOf(swbltl));
			dataMap.put("rkjy", String.valueOf(rkjy));
//			dataMap.put("ckkh",String.valueOf(ckkh));
			dataMap.put("jnpx", String.valueOf(jnpx));
			dataMap.put("yph", String.valueOf(yph));
			dataMap.put("jxhz", String.valueOf(jxhz));
			dataMap.put("jxbltl", String.valueOf(jxbltl));
			dataMap.put("ynbltl", String.valueOf(ynbltl));
			dataMap.put("wzbltl", String.valueOf(wzbltl));
			dataMap.put("swbltl", String.valueOf(swbltl));
			dataMap.put("lcczjnzd", String.valueOf(lcczjnzd));
			dataMap.put("lcblsxzd", String.valueOf(lcblsxzd));
			dataMap.put("ssczzd", String.valueOf(ssczzd));
			dataMap.put("yxzdbgsxzd", String.valueOf(yxzdbgsxzd));
			dataMap.put("lcwxyd", String.valueOf(lcwxyd));
			dataMap.put("ryjy", String.valueOf(ryjy));
			dataMap.put("rzyjdjy", String.valueOf(rzyjdjy));
			dataMap.put("cjbg", String.valueOf(cjbg));
			dataMap.put("bgdfx", String.valueOf(bgdfx));
			dataMap.put("jxsj", String.valueOf(jxsj));
			dataMap.put("sjys", String.valueOf(sjys));
		}
        String afterEvaluationId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
//		List<ResRec> recs=null;
		List<ResSchProcessExpress> recs = null;
		if (doctorSchProcess != null) {
			recs = expressBiz.searchResRecExpressWithBLOBs(afterEvaluationId, doctorSchProcess.getProcessFlow());
		}
		if (recs != null && recs.size() > 0) {
			ResSchProcessExpress resRecs = recs.get(0);
			Map<String, Object> formDataMap = resRecBiz.parseRecContent(resRecs.getRecContent());

			String attendance = formDataMap.get("attendance").toString();
			attendance = attendance.replace("x", "");

			dataMap.put("attendance", StringUtil.defaultIfEmpty(attendance, " "));
			dataMap.put("leave", StringUtil.defaultIfEmpty((String) formDataMap.get("leave"), " "));
			dataMap.put("sickLeave", StringUtil.defaultIfEmpty((String) formDataMap.get("sickLeave"), " "));
			dataMap.put("materLeave", StringUtil.defaultIfEmpty((String) formDataMap.get("materLeave"), " "));
			dataMap.put("absenteeism", StringUtil.defaultIfEmpty((String) formDataMap.get("absenteeism"), " "));
//
//			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(formDataMap.get("blsywc"), "0"));
//			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(formDataMap.get("blsyjwc"), "0"));
//			dataMap.put("caseRegistry",StringUtil.defaultIfEmpty(formDataMap.get("blswcbl"), "0"));
//
//
//			dataMap.put("diseaseRegistry",StringUtil.defaultIfEmpty(formDataMap.get("bzswcbl"), "0"));
//			dataMap.put("diseaseRegistryReqNum",StringUtil.defaultIfEmpty(formDataMap.get("bzsywc"), "0"));
//			dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(formDataMap.get("bzsyjwc"), "0"));
//
//			dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(formDataMap.get("czswcbl"), "0"));
//			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(formDataMap.get("czsywc"), "0"));
//			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(formDataMap.get("czsyjwc"), "0"));
//
//			dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(formDataMap.get("ssswcbl"), "0"));
//			dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(formDataMap.get("sssywc"), "0"));
//			dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(formDataMap.get("sssyjwc"), "0"));

			String zsgjflfg = (String) formDataMap.get("zsgjflfg_id");
			if ("1".equals(zsgjflfg)) {
				dataMap.put("zsgjflfgJXCF", "√");
			}
			if ("2".equals(zsgjflfg)) {
				dataMap.put("zsgjflfgYN", "√");
			}
			if ("3".equals(zsgjflfg)) {
				dataMap.put("zsgjflfgWZBLTL", "√");
			}
			if ("4".equals(zsgjflfg)) {
				dataMap.put("zsgjflfgXSJZ", "√");
			}
			String lxgwzz = (String) formDataMap.get("lxgwzz_id");
			if ("1".equals(lxgwzz)) {
				dataMap.put("lxgwzzJXCF", "√");
			}
			if ("2".equals(lxgwzz)) {
				dataMap.put("lxgwzzYN", "√");
			}
			if ("3".equals(lxgwzz)) {
				dataMap.put("lxgwzzWZBLTL", "√");
			}
			if ("4".equals(lxgwzz)) {
				dataMap.put("lxgwzzXSJZ", "√");
			}
			String ybrwzx = (String) formDataMap.get("ybrwzx_id");
			if ("1".equals(ybrwzx)) {
				dataMap.put("ybrwzxJXCF", "√");
			}
			if ("2".equals(ybrwzx)) {
				dataMap.put("ybrwzxYN", "√");
			}
			if ("3".equals(ybrwzx)) {
				dataMap.put("ybrwzxWZBLTL", "√");
			}
			if ("4".equals(lxgwzz)) {
				dataMap.put("ybrwzxXSJZ", "√");
			}
			String rjgtbdnl = (String) formDataMap.get("rjgtbdnl_id");
			if ("1".equals(rjgtbdnl)) {
				dataMap.put("rjgtbdnlJXCF", "√");
			}
			if ("2".equals(rjgtbdnl)) {
				dataMap.put("rjgtbdnlYN", "√");
			}
			if ("3".equals(rjgtbdnl)) {
				dataMap.put("rjgtbdnlWZBLTL", "√");
			}
			if ("4".equals(rjgtbdnl)) {
				dataMap.put("rjgtbdnlXSJZ", "√");
			}
			String tjxzjsxm = (String) formDataMap.get("tjxzjsxm_id");
			if ("1".equals(tjxzjsxm)) {
				dataMap.put("tjxzjsxmJXCF", "√");
			}
			if ("2".equals(tjxzjsxm)) {
				dataMap.put("tjxzjsxmYN", "√");
			}
			if ("3".equals(tjxzjsxm)) {
				dataMap.put("tjxzjsxmWZBLTL", "√");
			}
			if ("4".equals(tjxzjsxm)) {
				dataMap.put("tjxzjsxmXSJZ", "√");
			}
			String jbllzwcd = (String) formDataMap.get("jbllzwcd_id");
			if ("1".equals(jbllzwcd)) {
				dataMap.put("jbllzwcdJXCF", "√");
			}
			if ("2".equals(jbllzwcd)) {
				dataMap.put("jbllzwcdYN", "√");
			}
			if ("3".equals(jbllzwcd)) {
				dataMap.put("jbllzwcdWZBLTL", "√");
			}
			if ("4".equals(jbllzwcd)) {
				dataMap.put("jbllzwcdXSJZ", "√");
			}
			String njbjnzwcd = (String) formDataMap.get("njbjnzwcd_id");
			if ("1".equals(njbjnzwcd)) {
				dataMap.put("njbjnzwcdJXCF", "√");
			}
			if ("2".equals(njbjnzwcd)) {
				dataMap.put("njbjnzwcdYN", "√");
			}
			if ("3".equals(njbjnzwcd)) {
				dataMap.put("njbjnzwcdWZBLTL", "√");
			}
			if ("4".equals(njbjnzwcd)) {
				dataMap.put("njbjnzwcdXSJZ", "√");
			}

			String lcswnl = (String) formDataMap.get("lcswnl_id");
			if ("1".equals(lcswnl)) {
				dataMap.put("lcswnlJXCF", "√");
			}
			if ("2".equals(lcswnl)) {
				dataMap.put("lcswnlYN", "√");
			}
			if ("3".equals(lcswnl)) {
				dataMap.put("lcswnlWZBLTL", "√");
			}
			if ("4".equals(lcswnl)) {
				dataMap.put("lcswnlXSJZ", "√");
			}

			String lczlnl = (String) formDataMap.get("lczlnl_id");
			if ("1".equals(lczlnl)) {
				dataMap.put("lczlnlJXCF", "√");
			}
			if ("2".equals(lczlnl)) {
				dataMap.put("lczlnlYN", "√");
			}
			if ("3".equals(lczlnl)) {
				dataMap.put("lczlnlWZBLTL", "√");
			}
			if ("4".equals(lczlnl)) {
				dataMap.put("lczlnlXSJZ", "√");
			}
			String jjclnl = (String) formDataMap.get("jjclnl_id");
			if ("1".equals(jjclnl)) {
				dataMap.put("jjclnlJXCF", "√");
			}
			if ("2".equals(jjclnl)) {
				dataMap.put("jjclnlYN", "√");
			}
			if ("3".equals(jjclnl)) {
				dataMap.put("jjclnlWZBLTL", "√");
			}
			if ("4".equals(jjclnl)) {
				dataMap.put("jjclnlXSJZ", "√");
			}
//			dataMap.put("teachingRounds", StringUtil.defaultIfEmpty(formDataMap.get("jxcb"), "0"));
//			dataMap.put("difficult", StringUtil.defaultIfEmpty(formDataMap.get("nwzbltl"), "0"));
//			dataMap.put("lecture", StringUtil.defaultIfEmpty(formDataMap.get("xsjz"), "0"));
//			dataMap.put("death", StringUtil.defaultIfEmpty(formDataMap.get("swbltl"), "0"));


			boolean f = false;
			f = resDoctorBiz.getCkkPower(doctor.getDoctorFlow());
			if (f) {
				if (StringUtil.isBlank(StringUtil.defaultIfEmpty((String) formDataMap.get("theoreResult"), " "))) {
					ResScore outScore = resScoreBiz.getScoreByProcess(processFlow);
					if (outScore != null && outScore.getTheoryScore() != null) {
						dataMap.put("theoreResult", StringUtil.defaultIfEmpty(outScore.getTheoryScore().toString(), " "));
					} else {
						dataMap.put("theoreResult", StringUtil.defaultIfEmpty("暂未参加出科考核", " "));
					}
				} else {
					dataMap.put("theoreResult", StringUtil.defaultIfEmpty((String) formDataMap.get("theoreResult"), " "));
				}
			} else {
				dataMap.put("theoreResult", StringUtil.defaultIfEmpty((String) formDataMap.get("theoreResult"), " "));
			}
			dataMap.put("skillName", StringUtil.defaultIfEmpty((String) formDataMap.get("skillName"), " "));
			dataMap.put("score", StringUtil.defaultIfEmpty((String) formDataMap.get("score"), " "));
			dataMap.put("examiner1", StringUtil.defaultIfEmpty((String) formDataMap.get("examiner1"), " "));
			dataMap.put("examiner2", StringUtil.defaultIfEmpty((String) formDataMap.get("examiner2"), " "));

			String szkskhxzztpj = (String) formDataMap.get("szkskhxzztpj_id");
			if ("1".equals(szkskhxzztpj)) {
				dataMap.put("szkskhxzztpjJXCF", "√");
			}
			if (LING.equals(szkskhxzztpj)) {
				dataMap.put("szkskhxzztpjLING", "√");
			}
			dataMap.put("teacherName", StringUtil.defaultIfEmpty((String) formDataMap.get("teacherName"), " "));
			dataMap.put("teacherDate", StringUtil.defaultIfEmpty((String) formDataMap.get("teacherDate"), " "));
			dataMap.put("directorName", StringUtil.defaultIfEmpty((String) formDataMap.get("directorName"), " "));
			dataMap.put("directorDate", StringUtil.defaultIfEmpty((String) formDataMap.get("directorDate"), " "));

		}
		if (dataMap.get("caseRegistryReqNum") == null || "0".equals(dataMap.get("caseRegistryReqNum"))) {
			dataMap.put("caseRegistryReqNum", "0");
			dataMap.put("caseRegistry", "100");
		}
		if (dataMap.get("caseRegistryFinished") == null) {
			dataMap.put("caseRegistryFinished", "0");
		}
		if (dataMap.get("caseRegistry") == null) {
			dataMap.put("caseRegistry", "0");
		}
		if (dataMap.get("diseaseRegistry") == null) {
			dataMap.put("diseaseRegistry", "0");
		}
		if (dataMap.get("diseaseRegistryReqNum") == null || "0".equals(dataMap.get("diseaseRegistryReqNum"))) {
			dataMap.put("diseaseRegistryReqNum", "0");
			dataMap.put("diseaseRegistry", "100");
		}
		if (dataMap.get("diseaseRegistryFinished") == null) {
			dataMap.put("diseaseRegistryFinished", "0");
		}
		if (dataMap.get("skillRegistry") == null) {
			dataMap.put("skillRegistry", "0");
		}
		if (dataMap.get("skillRegistryReqNum") == null || "0".equals(dataMap.get("skillRegistryReqNum"))) {
			dataMap.put("skillRegistryReqNum", "0");
			dataMap.put("skillRegistry", "100");
		}
		if (dataMap.get("skillRegistryFinished") == null) {
			dataMap.put("skillRegistryFinished", "0");
		}

		if (dataMap.get("operationRegistry") == null) {
			dataMap.put("operationRegistry", "0");
		}
		if (dataMap.get("operationRegistryReqNum") == null || "0".equals(dataMap.get("operationRegistryReqNum"))) {
			dataMap.put("operationRegistryReqNum", "0");
			dataMap.put("operationRegistry", "100");
		}
		if (dataMap.get("operationRegistryFinished") == null) {
			dataMap.put("operationRegistryFinished", "0");
		}

		if (dataMap.get("zsgjflfgJXCF") == null) {
			dataMap.put("zsgjflfgJXCF", "□");
		}
		if (dataMap.get("zsgjflfgYN") == null) {
			dataMap.put("zsgjflfgYN", "□");
		}
		if (dataMap.get("zsgjflfgWZBLTL") == null) {
			dataMap.put("zsgjflfgWZBLTL", "□");
		}
		if (dataMap.get("zsgjflfgXSJZ") == null) {
			dataMap.put("zsgjflfgXSJZ", "□");
		}
		if (dataMap.get("lxgwzzJXCF") == null) {
			dataMap.put("lxgwzzJXCF", "□");
		}
		if (dataMap.get("lxgwzzYN") == null) {
			dataMap.put("lxgwzzYN", "□");
		}
		if (dataMap.get("lxgwzzWZBLTL") == null) {
			dataMap.put("lxgwzzWZBLTL", "□");
		}
		if (dataMap.get("lxgwzzXSJZ") == null) {
			dataMap.put("lxgwzzXSJZ", "□");
		}
		if (dataMap.get("ybrwzxJXCF") == null) {
			dataMap.put("ybrwzxJXCF", "□");
		}
		if (dataMap.get("ybrwzxYN") == null) {
			dataMap.put("ybrwzxYN", "□");
		}
		if (dataMap.get("ybrwzxWZBLTL") == null) {
			dataMap.put("ybrwzxWZBLTL", "□");
		}
		if (dataMap.get("ybrwzxXSJZ") == null) {
			dataMap.put("ybrwzxXSJZ", "□");
		}
		if (dataMap.get("rjgtbdnlJXCF") == null) {
			dataMap.put("rjgtbdnlJXCF", "□");
		}
		if (dataMap.get("rjgtbdnlYN") == null) {
			dataMap.put("rjgtbdnlYN", "□");
		}
		if (dataMap.get("rjgtbdnlWZBLTL") == null) {
			dataMap.put("rjgtbdnlWZBLTL", "□");
		}
		if (dataMap.get("rjgtbdnlXSJZ") == null) {
			dataMap.put("rjgtbdnlXSJZ", "□");
		}
		if (dataMap.get("tjxzjsxmJXCF") == null) {
			dataMap.put("tjxzjsxmJXCF", "□");
		}
		if (dataMap.get("tjxzjsxmYN") == null) {
			dataMap.put("tjxzjsxmYN", "□");
		}
		if (dataMap.get("tjxzjsxmWZBLTL") == null) {
			dataMap.put("tjxzjsxmWZBLTL", "□");
		}
		if (dataMap.get("tjxzjsxmXSJZ") == null) {
			dataMap.put("tjxzjsxmXSJZ", "□");
		}
		if (dataMap.get("jbllzwcdJXCF") == null) {
			dataMap.put("jbllzwcdJXCF", "□");
		}
		if (dataMap.get("jbllzwcdYN") == null) {
			dataMap.put("jbllzwcdYN", "□");
		}
		if (dataMap.get("jbllzwcdWZBLTL") == null) {
			dataMap.put("jbllzwcdWZBLTL", "□");
		}
		if (dataMap.get("jbllzwcdXSJZ") == null) {
			dataMap.put("jbllzwcdXSJZ", "□");
		}
		if (dataMap.get("njbjnzwcdJXCF") == null) {
			dataMap.put("njbjnzwcdJXCF", "□");
		}
		if (dataMap.get("njbjnzwcdYN") == null) {
			dataMap.put("njbjnzwcdYN", "□");
		}
		if (dataMap.get("njbjnzwcdWZBLTL") == null) {
			dataMap.put("njbjnzwcdWZBLTL", "□");
		}
		if (dataMap.get("njbjnzwcdXSJZ") == null) {
			dataMap.put("njbjnzwcdXSJZ", "□");
		}
		if (dataMap.get("lcswnlJXCF") == null) {
			dataMap.put("lcswnlJXCF", "□");
		}
		if (dataMap.get("lcswnlYN") == null) {
			dataMap.put("lcswnlYN", "□");
		}
		if (dataMap.get("lcswnlWZBLTL") == null) {
			dataMap.put("lcswnlWZBLTL", "□");
		}
		if (dataMap.get("lcswnlXSJZ") == null) {
			dataMap.put("lcswnlXSJZ", "□");
		}
		if (dataMap.get("lczlnlJXCF") == null) {
			dataMap.put("lczlnlJXCF", "□");
		}
		if (dataMap.get("lczlnlYN") == null) {
			dataMap.put("lczlnlYN", "□");
		}
		if (dataMap.get("lczlnlWZBLTL") == null) {
			dataMap.put("lczlnlWZBLTL", "□");
		}
		if (dataMap.get("lczlnlXSJZ") == null) {
			dataMap.put("lczlnlXSJZ", "□");
		}
		if (dataMap.get("jjclnlJXCF") == null) {
			dataMap.put("jjclnlJXCF", "□");
		}
		if (dataMap.get("jjclnlYN") == null) {
			dataMap.put("jjclnlYN", "□");
		}
		if (dataMap.get("jjclnlWZBLTL") == null) {
			dataMap.put("jjclnlWZBLTL", "□");
		}
		if (dataMap.get("jjclnlXSJZ") == null) {
			dataMap.put("jjclnlXSJZ", "□");
		}
		if (dataMap.get("szkskhxzztpjJXCF") == null) {
			dataMap.put("szkskhxzztpjJXCF", "□");
		}
		if (dataMap.get("szkskhxzztpjLING") == null) {
			dataMap.put("szkskhxzztpjLING", "□");
		}
		//获取本人所有排班
	/*	List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlowSun(dept.getGroupFlow(),dept.getStandardDeptId(),user.getUserFlow());
		if(resultList!=null && resultList.size()>0){
			Float monthCount = 0f;
			String startDate = null;
			String endDate = null;
			for(SchArrangeResult sar : resultList){
				String month = sar.getSchMonth();
				if(StringUtil.isNotBlank(month)){
					Float currMonth = Float.parseFloat(month);
					if(currMonth!=null){
						monthCount+=currMonth;
					}

					if(startDate==null){
						startDate = sar.getSchStartDate();
					}

					endDate = sar.getSchEndDate();
				}

			}

			dataMap.put("monthCount",monthCount.toSt　ring());
			dataMap.put("startDate",startDate);
			dataMap.put("endDate",endDate);
		}*/


//		String path = "/jsp/jsres/doctor/ckkhb.docx";//模板
//		temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
//		name = "江苏省住院医师规范化培训临床轮转出科表.docx";
//		if(temeplete!=null){
//			response.setHeader("Content-disposition","attachment; filename="+new String(name.getBytes("gbk"),"ISO8859-1" ) +"");
//			response.setContentType ("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
//			ServletOutputStream out = response.getOutputStream ();
//			(new SaveToZipFile (temeplete)).save (out);
//			out.flush ();
//		}
		final String fileName = "江苏省住院医师规范化培训临床轮转出科表";
		String outputFileClass = ResourceLoader.getPath("/");
		String outputFile = new File(outputFileClass) + "/load/" + fileName + ".pdf";
		File file = new File(outputFile);
		// 模板数据
		DocumentVo vo = new DocumentVo() {
			@Override
			public String findPrimaryKey() {
				return fileName;
			}

			@Override
			public Map<String, Object> fillDataMap() {
				return dataMap;
			}
		};
		String template = "jsres/ckkhbTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, vo, outputFile);

		pubFileBiz.downFile(file, response);
	}


	@RequestMapping(value = "/download")
	public void download(String recordFlow, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		SysUser user = GlobalContext.getCurrentUser();
		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
		ServletContext context = request.getServletContext();
		String watermark = "";
		String name = "";
		//获取个人基础信息
		ResDoctor doctor = resDoctorBiz.searchByUserFlow(user.getUserFlow());
		//轮转科室名称
		SchRotationDept dept = schRotationDeptBiz.readSchRotationDept(recordFlow);
		//百分比Map
		Map<String, String> processPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow(), recordFlow);

		//获取不同类型并定义接受
        String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
        String diseaseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
        String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
        String operationRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();

		String caseRegistry = processPerMap.get(recordFlow + caseRegistryId);
		String caseRegistryReqNum = processPerMap.get(recordFlow + caseRegistryId + "ReqNum");
		String caseRegistryFinished = processPerMap.get(recordFlow + caseRegistryId + "Finished");

		String diseaseRegistry = processPerMap.get(recordFlow + diseaseRegistryId);
		String diseaseRegistryReqNum = processPerMap.get(recordFlow + diseaseRegistryId + "ReqNum");
		String diseaseRegistryFinished = processPerMap.get(recordFlow + diseaseRegistryId + "Finished");

		String skillRegistry = processPerMap.get(recordFlow + skillRegistryId);
		String skillRegistryReqNum = processPerMap.get(recordFlow + skillRegistryId + "ReqNum");
		String skillRegistryFinished = processPerMap.get(recordFlow + skillRegistryId + "Finished");

		String skillAndOperationRegistry = processPerMap.get(recordFlow + operationRegistryId);
		String skillAndOperationRegistryReqNum = processPerMap.get(recordFlow + operationRegistryId + "ReqNum");
		String skillAndOperationRegistryFinished = processPerMap.get(recordFlow + operationRegistryId + "Finished");

        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
//		int teachingRounds=0;
//		int difficult=0;
//		int lecture=0;
//		int death=0;
		int jxcf = 0;
		int xjk = 0;
		int rkjy = 0;
		int yph = 0;
		int jxhz = 0;
		int jxbltl = 0;
		int lcczjnzd = 0;
		int lcblsxzd = 0;
		int ssczzd = 0;
		int yxzdbgsxzd = 0;
		int lcwxyd = 0;
		int ryjy = 0;
		int rzyjdjy = 0;
		int cjbg = 0;
		int bgdfx = 0;
		int ynbltl = 0;
		int wzbltl = 0;
		int swbltl = 0;
		List<ResRec> rec = resRecBiz.searchRecByProcess(recTypeId, recordFlow, user.getUserFlow());
		for (ResRec resRec : rec) {
			String content = resRec.getRecContent();
			Document document = DocumentHelper.parseText(content);
			Element root = document.getRootElement();
			Element ec = root.element("activity_way");
			if (ec != null) {
				String text = ec.attributeValue("id");
				if (Jxcf.equals(text)) {
					jxcf++;
				} else if (Ynbltl.equals(text)) {
					ynbltl++;
				} else if (Wzbltl.equals(text)) {
					wzbltl++;
				} else if (Xsjz.equals(text)) {
					xjk++;
				} else if (Swbltl.equals(text)) {
					swbltl++;
				} else if (Rkjy.equals(text)) {
					rkjy++;
				} else if (Ckks.equals(text)) {
//						ckkh++;
				} else if (Jnpx.equals(text)) {
//						jnpx++;
				} else if (Yph.equals(text)) {
					yph++;
				} else if (Jxhz.equals(text)) {
					jxhz++;
				} else if (Jxbltl.equals(text)) {
					jxbltl++;
				} else if (Lcczjnzd.equals(text)) {
					lcczjnzd++;
				} else if (Lcblsxzd.equals(text)) {
					lcblsxzd++;
				} else if (Ssczzd.equals(text)) {
					ssczzd++;
				} else if (Yxzdbgsxzd.equals(text)) {
					yxzdbgsxzd++;
				} else if (Lcwxyd.equals(text)) {
					lcwxyd++;
				} else if (Ryjy.equals(text)) {
					ryjy++;
				} else if (Rzyjdjy.equals(text)) {
					rzyjdjy++;
				} else if (Cjbg.equals(text)) {
					cjbg++;
				} else if (Bgdfx.equals(text)) {
					bgdfx++;
				}
			}
		}
		List<TeachingActivityInfo> infos = resRecBiz.searchJoinActivityByStrandDeptFlow(doctor.getDoctorFlow(), recordFlow);
		if (infos != null && infos.size() > 0) {
			for (TeachingActivityInfo info : infos) {
				String text = info.getActivityTypeId();
				if (Jxcf.equals(text)) {
					jxcf++;
				} else if (Ynbltl.equals(text)) {
					ynbltl++;
				} else if (Wzbltl.equals(text)) {
					wzbltl++;
				} else if (Xsjz.equals(text)) {
					xjk++;
				} else if (Swbltl.equals(text)) {
					swbltl++;
				} else if (Rkjy.equals(text)) {
					rkjy++;
				} else if (Ckks.equals(text)) {
//						ckkh++;
				} else if (Jnpx.equals(text)) {
//						jnpx++;
				} else if (Yph.equals(text)) {
					yph++;
				} else if (Jxhz.equals(text)) {
					jxhz++;
				} else if (Jxbltl.equals(text)) {
					jxbltl++;
				} else if (Lcczjnzd.equals(text)) {
					lcczjnzd++;
				} else if (Lcblsxzd.equals(text)) {
					lcblsxzd++;
				} else if (Ssczzd.equals(text)) {
					ssczzd++;
				} else if (Yxzdbgsxzd.equals(text)) {
					yxzdbgsxzd++;
				} else if (Lcwxyd.equals(text)) {
					lcwxyd++;
				} else if (Ryjy.equals(text)) {
					ryjy++;
				} else if (Rzyjdjy.equals(text)) {
					rzyjdjy++;
				} else if (Cjbg.equals(text)) {
					cjbg++;
				} else if (Bgdfx.equals(text)) {
					bgdfx++;
				}
			}
		}

		//获取resrec数据
        String afterEvaluationId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
//		List<ResRec> recs=resRecBiz.searchResRecWithBLOBsByRotationDeptFlow(afterEvaluationId,recordFlow,user.getUserFlow());
		List<ResSchProcessExpress> recs = expressBiz.searchResRecWithBLOBsByRotationDeptFlow(afterEvaluationId, recordFlow, user.getUserFlow());
		if (recs != null && recs.size() > 0) {
			ResSchProcessExpress resRecs = recs.get(0);
			Map<String, Object> formDataMap = resRecBiz.parseRecContent(resRecs.getRecContent());
			dataMap.put("attendance", StringUtil.defaultIfEmpty((String) formDataMap.get("attendance"), " "));
			dataMap.put("leave", StringUtil.defaultIfEmpty((String) formDataMap.get("leave"), " "));
			dataMap.put("sickLeave", StringUtil.defaultIfEmpty((String) formDataMap.get("sickLeave"), " "));
			dataMap.put("materLeave", StringUtil.defaultIfEmpty((String) formDataMap.get("materLeave"), " "));
			dataMap.put("absenteeism", StringUtil.defaultIfEmpty((String) formDataMap.get("absenteeism"), " "));
		}
		/*	String zsgjflfg = formDataMap.get("zsgjflfg_id");
			if(JXCF.equals(zsgjflfg)){
				dataMap.put("zsgjflfgJXCF","√");
			}
			if(YN.equals(zsgjflfg)){
				dataMap.put("zsgjflfgYN","√");
			}
			if(WZBLTL.equals(zsgjflfg)){
				dataMap.put("zsgjflfgWZBLTL","√");
			}
			if(XSJZ.equals(zsgjflfg)){
				dataMap.put("zsgjflfgXSJZ","√");
			}
			String lxgwzz = formDataMap.get("lxgwzz_id");
			if(JXCF.equals(lxgwzz)){
				dataMap.put("lxgwzzJXCF","√");
			}
			if(YN.equals(lxgwzz)){
				dataMap.put("lxgwzzYN","√");
			}
			if(WZBLTL.equals(lxgwzz)){
				dataMap.put("lxgwzzWZBLTL","√");
			}
			if(XSJZ.equals(lxgwzz)){
				dataMap.put("lxgwzzXSJZ","√");
			}
			String ybrwzx = formDataMap.get("ybrwzx_id");
			if(JXCF.equals(ybrwzx)){
				dataMap.put("ybrwzxJXCF","√");
			}
			if(YN.equals(ybrwzx)){
				dataMap.put("ybrwzxYN","√");
			}
			if(WZBLTL.equals(ybrwzx)){
				dataMap.put("ybrwzxWZBLTL","√");
			}
			if(XSJZ.equals(lxgwzz)){
				dataMap.put("ybrwzxXSJZ","√");
			}
			String rjgtbdnl = formDataMap.get("rjgtbdnl_id");
			if(JXCF.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlJXCF","√");
			}
			if(YN.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlYN","√");
			}
			if(WZBLTL.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlWZBLTL","√");
			}
			if(XSJZ.equals(rjgtbdnl)){
				dataMap.put("rjgtbdnlXSJZ","√");
			}
			String tjxzjsxm = formDataMap.get("tjxzjsxm_id");
			if(JXCF.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmJXCF","√");
			}
			if(YN.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmYN","√");
			}
			if(WZBLTL.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmWZBLTL","√");
			}
			if(XSJZ.equals(tjxzjsxm)){
				dataMap.put("tjxzjsxmXSJZ","√");
			}
			String jbllzwcd = formDataMap.get("jbllzwcd_id");
			if(JXCF.equals(jbllzwcd)){
				dataMap.put("jbllzwcdJXCF","√");
			}
			if(YN.equals(jbllzwcd)){
				dataMap.put("jbllzwcdYN","√");
			}
			if(WZBLTL.equals(jbllzwcd)){
				dataMap.put("jbllzwcdWZBLTL","√");
			}
			if(XSJZ.equals(jbllzwcd)){
				dataMap.put("jbllzwcdXSJZ","√");
			}
			String njbjnzwcd = formDataMap.get("njbjnzwcd_id");
				if(JXCF.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdJXCF","√");
				}
				if(YN.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdYN","√");
				}
				if(WZBLTL.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdWZBLTL","√");
				}
				if(XSJZ.equals(njbjnzwcd)){
					dataMap.put("njbjnzwcdXSJZ","√");
				}

			String lcswnl = formDataMap.get("lcswnl_id");
				if(JXCF.equals(lcswnl)){
					dataMap.put("lcswnlJXCF","√");
				}
				if(YN.equals(lcswnl)){
					dataMap.put("lcswnlYN","√");
				}
				if(WZBLTL.equals(lcswnl)){
					dataMap.put("lcswnlWZBLTL","√");
				}
				if(XSJZ.equals(lcswnl)){
					dataMap.put("lcswnlXSJZ","√");
				}

			String lczlnl = formDataMap.get("lczlnl_id");
				if(JXCF.equals(lczlnl)){
					dataMap.put("lczlnlJXCF","√");
				}
				if(YN.equals(lczlnl)){
					dataMap.put("lczlnlYN","√");
				}
				if(WZBLTL.equals(lczlnl)){
					dataMap.put("lczlnlWZBLTL","√");
				}
				if(XSJZ.equals(lczlnl)){
					dataMap.put("lczlnlXSJZ","√");
				}
			String jjclnl = formDataMap.get("jjclnl_id");
				if(JXCF.equals(jjclnl)){
					dataMap.put("jjclnlJXCF","√");
				}
				if(YN.equals(jjclnl)){
					dataMap.put("jjclnlYN","√");
				}
				if(WZBLTL.equals(jjclnl)){
					dataMap.put("jjclnlWZBLTL","√");
				}
				if(XSJZ.equals(jjclnl)){
					dataMap.put("jjclnlXSJZ","√");
				}
			dataMap.put("theoreResult", StringUtil.defaultIfEmpty(formDataMap.get("theoreResult"), " "));
			dataMap.put("skillName", StringUtil.defaultIfEmpty(formDataMap.get("skillName"), " "));
			dataMap.put("score", StringUtil.defaultIfEmpty(formDataMap.get("score"), " "));
			dataMap.put("examiner1", StringUtil.defaultIfEmpty(formDataMap.get("examiner1"), " "));
			dataMap.put("examiner2", StringUtil.defaultIfEmpty(formDataMap.get("examiner2"), " "));

			String szkskhxzztpj = formDataMap.get("szkskhxzztpj_id");
				if(JXCF.equals(szkskhxzztpj)){
					dataMap.put("szkskhxzztpjJXCF","√");
				}
				if(LING.equals(szkskhxzztpj)){
					dataMap.put("szkskhxzztpjLING","√");
				}
			dataMap.put("teacherName", StringUtil.defaultIfEmpty(formDataMap.get("teacherName"), " "));
			dataMap.put("teacherDate", StringUtil.defaultIfEmpty(formDataMap.get("teacherDate"), " "));
			dataMap.put("directorName", StringUtil.defaultIfEmpty(formDataMap.get("directorName"), " "));
			dataMap.put("directorDate", StringUtil.defaultIfEmpty(formDataMap.get("directorDate"), " "));

		}*/
		if (dataMap.get("zsgjflfgJXCF") == null) {
			dataMap.put("zsgjflfgJXCF", "□");
		}
		if (dataMap.get("zsgjflfgYN") == null) {
			dataMap.put("zsgjflfgYN", "□");
		}
		if (dataMap.get("zsgjflfgWZBLTL") == null) {
			dataMap.put("zsgjflfgWZBLTL", "□");
		}
		if (dataMap.get("zsgjflfgXSJZ") == null) {
			dataMap.put("zsgjflfgXSJZ", "□");
		}
		if (dataMap.get("lxgwzzJXCF") == null) {
			dataMap.put("lxgwzzJXCF", "□");
		}
		if (dataMap.get("lxgwzzYN") == null) {
			dataMap.put("lxgwzzYN", "□");
		}
		if (dataMap.get("lxgwzzWZBLTL") == null) {
			dataMap.put("lxgwzzWZBLTL", "□");
		}
		if (dataMap.get("lxgwzzXSJZ") == null) {
			dataMap.put("lxgwzzXSJZ", "□");
		}
		if (dataMap.get("ybrwzxJXCF") == null) {
			dataMap.put("ybrwzxJXCF", "□");
		}
		if (dataMap.get("ybrwzxYN") == null) {
			dataMap.put("ybrwzxYN", "□");
		}
		if (dataMap.get("ybrwzxWZBLTL") == null) {
			dataMap.put("ybrwzxWZBLTL", "□");
		}
		if (dataMap.get("ybrwzxXSJZ") == null) {
			dataMap.put("ybrwzxXSJZ", "□");
		}
		if (dataMap.get("rjgtbdnlJXCF") == null) {
			dataMap.put("rjgtbdnlJXCF", "□");
		}
		if (dataMap.get("rjgtbdnlYN") == null) {
			dataMap.put("rjgtbdnlYN", "□");
		}
		if (dataMap.get("rjgtbdnlWZBLTL") == null) {
			dataMap.put("rjgtbdnlWZBLTL", "□");
		}
		if (dataMap.get("rjgtbdnlXSJZ") == null) {
			dataMap.put("rjgtbdnlXSJZ", "□");
		}
		if (dataMap.get("tjxzjsxmJXCF") == null) {
			dataMap.put("tjxzjsxmJXCF", "□");
		}
		if (dataMap.get("tjxzjsxmYN") == null) {
			dataMap.put("tjxzjsxmYN", "□");
		}
		if (dataMap.get("tjxzjsxmWZBLTL") == null) {
			dataMap.put("tjxzjsxmWZBLTL", "□");
		}
		if (dataMap.get("tjxzjsxmXSJZ") == null) {
			dataMap.put("tjxzjsxmXSJZ", "□");
		}
		if (dataMap.get("jbllzwcdJXCF") == null) {
			dataMap.put("jbllzwcdJXCF", "□");
		}
		if (dataMap.get("jbllzwcdYN") == null) {
			dataMap.put("jbllzwcdYN", "□");
		}
		if (dataMap.get("jbllzwcdWZBLTL") == null) {
			dataMap.put("jbllzwcdWZBLTL", "□");
		}
		if (dataMap.get("jbllzwcdXSJZ") == null) {
			dataMap.put("jbllzwcdXSJZ", "□");
		}
		if (dataMap.get("njbjnzwcdJXCF") == null) {
			dataMap.put("njbjnzwcdJXCF", "□");
		}
		if (dataMap.get("njbjnzwcdYN") == null) {
			dataMap.put("njbjnzwcdYN", "□");
		}
		if (dataMap.get("njbjnzwcdWZBLTL") == null) {
			dataMap.put("njbjnzwcdWZBLTL", "□");
		}
		if (dataMap.get("njbjnzwcdXSJZ") == null) {
			dataMap.put("njbjnzwcdXSJZ", "□");
		}
		if (dataMap.get("lcswnlJXCF") == null) {
			dataMap.put("lcswnlJXCF", "□");
		}
		if (dataMap.get("lcswnlYN") == null) {
			dataMap.put("lcswnlYN", "□");
		}
		if (dataMap.get("lcswnlWZBLTL") == null) {
			dataMap.put("lcswnlWZBLTL", "□");
		}
		if (dataMap.get("lcswnlXSJZ") == null) {
			dataMap.put("lcswnlXSJZ", "□");
		}
		if (dataMap.get("lczlnlJXCF") == null) {
			dataMap.put("lczlnlJXCF", "□");
		}
		if (dataMap.get("lczlnlYN") == null) {
			dataMap.put("lczlnlYN", "□");
		}
		if (dataMap.get("lczlnlWZBLTL") == null) {
			dataMap.put("lczlnlWZBLTL", "□");
		}
		if (dataMap.get("lczlnlXSJZ") == null) {
			dataMap.put("lczlnlXSJZ", "□");
		}
		if (dataMap.get("jjclnlJXCF") == null) {
			dataMap.put("jjclnlJXCF", "□");
		}
		if (dataMap.get("jjclnlYN") == null) {
			dataMap.put("jjclnlYN", "□");
		}
		if (dataMap.get("jjclnlWZBLTL") == null) {
			dataMap.put("jjclnlWZBLTL", "□");
		}
		if (dataMap.get("jjclnlXSJZ") == null) {
			dataMap.put("jjclnlXSJZ", "□");
		}
		if (dataMap.get("szkskhxzztpjJXCF") == null) {
			dataMap.put("szkskhxzztpjJXCF", "□");
		}
		if (dataMap.get("szkskhxzztpjLING") == null) {
			dataMap.put("szkskhxzztpjLING", "□");
		}
		//获取本人所有排班
		List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), user.getUserFlow());
		if (resultList != null && resultList.size() > 0) {
			Float monthCount = 0f;
			String startDate = null;
			String endDate = null;
			for (SchArrangeResult sar : resultList) {
				String month = sar.getSchMonth();
				if (StringUtil.isNotBlank(month)) {
					Float currMonth = Float.parseFloat(month);
					if (currMonth != null) {
						monthCount += currMonth;
					}
				}
				if (startDate == null) {
					startDate = sar.getSchStartDate();
				}

				endDate = sar.getSchEndDate();

			}

			dataMap.put("monthCount", monthCount.toString());
			dataMap.put("startDate", startDate);
			dataMap.put("endDate", endDate);
		}

		dataMap.put("userName", user.getUserName());
		dataMap.put("sessionNumber", doctor.getSessionNumber());
		dataMap.put("trainingSpeName", doctor.getTrainingSpeName());
		dataMap.put("standardDeptName", dept.getStandardDeptName());

		String cfgCode = "jsres_doctor_app_menu_" + doctor.getDoctorFlow();
		JsresPowerCfg cfg = jsResPowerCfgBiz.read(cfgCode);
        if ((cfg != null && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(cfg.getCfgValue()))) {
			if (caseRegistryReqNum == null || "0".equals(caseRegistryReqNum)) {
				dataMap.put("caseRegistry", "100");
			} else {
				dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
			}
			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

			if (diseaseRegistryReqNum == null || "0".equals(diseaseRegistryReqNum)) {
				dataMap.put("diseaseRegistry", "100");
			} else {
				dataMap.put("diseaseRegistry", StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
			}
			dataMap.put("diseaseRegistryReqNum", StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
			dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

			if (skillRegistryReqNum == null || "0".equals(skillRegistryReqNum)) {
				dataMap.put("skillRegistry", "100");
			} else {
				dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
			}
			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

			if (skillAndOperationRegistryReqNum == null || "0".equals(skillAndOperationRegistryReqNum)) {
				dataMap.put("operationRegistry", "100");
			} else {
				dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
			}
			dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
			dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

			dataMap.put("jxcf", String.valueOf(jxcf));
//			dataMap.put("ynbltl",String.valueOf(ynbltl));
//			dataMap.put("wzbltl",String.valueOf(wzbltl));
			dataMap.put("xjk", String.valueOf(xjk));
//			dataMap.put("swbltl",String.valueOf(swbltl));
			dataMap.put("rkjy", String.valueOf(rkjy));
//			dataMap.put("ckkh",String.valueOf(ckkh));
//			dataMap.put("jnpx",String.valueOf(jnpx));
			dataMap.put("yph", String.valueOf(yph));
			dataMap.put("jxhz", String.valueOf(jxhz));
			dataMap.put("jxbltl", String.valueOf(jxbltl));
			dataMap.put("wzbltl", String.valueOf(wzbltl));
			dataMap.put("swbltl", String.valueOf(swbltl));
			dataMap.put("ynbltl", String.valueOf(ynbltl));
			dataMap.put("lcczjnzd", String.valueOf(lcczjnzd));
			dataMap.put("lcblsxzd", String.valueOf(lcblsxzd));
			dataMap.put("ssczzd", String.valueOf(ssczzd));
			dataMap.put("yxzdbgsxzd", String.valueOf(yxzdbgsxzd));
			dataMap.put("lcwxyd", String.valueOf(lcwxyd));
			dataMap.put("ryjy", String.valueOf(ryjy));
			dataMap.put("rzyjdjy", String.valueOf(rzyjdjy));
			dataMap.put("cjbg", String.valueOf(cjbg));
			dataMap.put("bgdfx", String.valueOf(bgdfx));
		} else {
			dataMap.put("caseRegistry", "0");
			dataMap.put("caseRegistryReqNum", "0");
			dataMap.put("caseRegistryFinished", "0");

			dataMap.put("diseaseRegistry", "0");
			dataMap.put("diseaseRegistryReqNum", "0");
			dataMap.put("diseaseRegistryFinished", "0");

			dataMap.put("skillRegistry", "0");
			dataMap.put("skillRegistryReqNum", "0");
			dataMap.put("skillRegistryFinished", "0");

			dataMap.put("operationRegistry", "0");
			dataMap.put("operationRegistryReqNum", "0");
			dataMap.put("operationRegistryFinished", "0");

			dataMap.put("teachingRounds", "0");
			dataMap.put("difficult", "0");
			dataMap.put("lecture", "0");
			dataMap.put("death", "0");
		}

		String path = "/jsp/jsres/doctor/ckkhb.docx";//模板
		temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap, watermark, true);
		name = "江苏省住院医师规范化培训临床轮转出科表.docx";
		if (temeplete != null) {
			response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream();
			(new SaveToZipFile(temeplete)).save(out);
			out.flush();
		}
	}

	@RequestMapping(value = "/download4PDF")
	public void download4PDF(String recordFlow, HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Map<String, Object> dataMap = new HashMap<String, Object>();
		SysUser user = GlobalContext.getCurrentUser();
		WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
		ServletContext context = request.getServletContext();
		String watermark = "";
		String name = "";
		//获取个人基础信息
		ResDoctor doctor = resDoctorBiz.searchByUserFlow(user.getUserFlow());
		//轮转科室名称
		SchRotationDept dept = schRotationDeptBiz.readSchRotationDept(recordFlow);
		//百分比Map
		Map<String, String> processPerMap = resRecBiz.getProcessPer(doctor.getDoctorFlow(), doctor.getRotationFlow(), recordFlow);

		//获取不同类型并定义接受
        String caseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId();
        String diseaseRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.DiseaseRegistry.getId();
        String skillRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.SkillRegistry.getId();
        String operationRegistryId = com.pinde.core.common.enums.ResRecTypeEnum.OperationRegistry.getId();

		String caseRegistry = processPerMap.get(recordFlow + caseRegistryId);
		String caseRegistryReqNum = processPerMap.get(recordFlow + caseRegistryId + "ReqNum");
		String caseRegistryFinished = processPerMap.get(recordFlow + caseRegistryId + "Finished");

		String diseaseRegistry = processPerMap.get(recordFlow + diseaseRegistryId);
		String diseaseRegistryReqNum = processPerMap.get(recordFlow + diseaseRegistryId + "ReqNum");
		String diseaseRegistryFinished = processPerMap.get(recordFlow + diseaseRegistryId + "Finished");

		String skillRegistry = processPerMap.get(recordFlow + skillRegistryId);
		String skillRegistryReqNum = processPerMap.get(recordFlow + skillRegistryId + "ReqNum");
		String skillRegistryFinished = processPerMap.get(recordFlow + skillRegistryId + "Finished");

		String skillAndOperationRegistry = processPerMap.get(recordFlow + operationRegistryId);
		String skillAndOperationRegistryReqNum = processPerMap.get(recordFlow + operationRegistryId + "ReqNum");
		String skillAndOperationRegistryFinished = processPerMap.get(recordFlow + operationRegistryId + "Finished");

        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.CampaignRegistry.getId();
//		int teachingRounds=0;
//		int difficult=0;
//		int lecture=0;
//		int death=0;
		int jxcf = 0;
		int xjk = 0;
		int rkjy = 0;
		int yph = 0;
		int jxhz = 0;
		int jxbltl = 0;
		int lcczjnzd = 0;
		int lcblsxzd = 0;
		int ssczzd = 0;
		int yxzdbgsxzd = 0;
		int lcwxyd = 0;
		int ryjy = 0;
		int rzyjdjy = 0;
		int cjbg = 0;
		int ynbltl = 0;
		int wzbltl = 0;
		int swbltl = 0;
		int bgdfx = 0;
		List<ResRec> rec = resRecBiz.searchRecByProcess(recTypeId, recordFlow, user.getUserFlow());
		for (ResRec resRec : rec) {
			String content = resRec.getRecContent();
			Document document = DocumentHelper.parseText(content);
			Element root = document.getRootElement();
			Element ec = root.element("activity_way");
			if (ec != null) {
				String text = ec.attributeValue("id");
				if (Jxcf.equals(text)) {
					jxcf++;
				} else if (Ynbltl.equals(text)) {
					ynbltl++;
				} else if (Wzbltl.equals(text)) {
					wzbltl++;
				} else if (Xsjz.equals(text)) {
					xjk++;
				} else if (Swbltl.equals(text)) {
					swbltl++;
				} else if (Rkjy.equals(text)) {
					rkjy++;
				} else if (Ckks.equals(text)) {
//						ckkh++;
				} else if (Jnpx.equals(text)) {
//						jnpx++;
				} else if (Yph.equals(text)) {
					yph++;
				} else if (Jxhz.equals(text)) {
					jxhz++;
				} else if (Jxbltl.equals(text)) {
					jxbltl++;
				} else if (Lcczjnzd.equals(text)) {
					lcczjnzd++;
				} else if (Lcblsxzd.equals(text)) {
					lcblsxzd++;
				} else if (Ssczzd.equals(text)) {
					ssczzd++;
				} else if (Yxzdbgsxzd.equals(text)) {
					yxzdbgsxzd++;
				} else if (Lcwxyd.equals(text)) {
					lcwxyd++;
				} else if (Ryjy.equals(text)) {
					ryjy++;
				} else if (Rzyjdjy.equals(text)) {
					rzyjdjy++;
				} else if (Cjbg.equals(text)) {
					cjbg++;
				} else if (Bgdfx.equals(text)) {
					bgdfx++;
				}
			}
		}

		List<TeachingActivityInfo> infos = new ArrayList<>();
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		JsresPowerCfg orgApprove = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_org_ctrl_approve_activity");//教学活动评价配置
		JsresPowerCfg approve = jsResPowerCfgBiz.read("jsres_" + orgFlow + "_org_approve_activity");//教学活动评价配置评审类型
        if (null != orgApprove && null != approve && StringUtil.isNotNullAndEquala(approve.getCfgValue(), orgApprove.getCfgValue(), com.pinde.core.common.GlobalConstant.FLAG_Y)) {    //开启必评
			infos = resRecBiz.searchJoinActivityByStrandDeptFlowAndNotScore(doctor.getDoctorFlow(), recordFlow);
		} else {
			infos = resRecBiz.searchJoinActivityByStrandDeptFlow(doctor.getDoctorFlow(), recordFlow);
		}
		if (infos != null && infos.size() > 0) {
			for (TeachingActivityInfo info : infos) {
				String text = info.getActivityTypeId();
				if (Jxcf.equals(text)) {
					jxcf++;
				} else if (Ynbltl.equals(text)) {
					ynbltl++;
				} else if (Wzbltl.equals(text)) {
					wzbltl++;
				} else if (Xsjz.equals(text)) {
					xjk++;
				} else if (Swbltl.equals(text)) {
					swbltl++;
				} else if (Rkjy.equals(text)) {
					rkjy++;
				} else if (Ckks.equals(text)) {
//						ckkh++;
				} else if (Jnpx.equals(text)) {
//						jnpx++;
				} else if (Yph.equals(text)) {
					yph++;
				} else if (Jxhz.equals(text)) {
					jxhz++;
				} else if (Jxbltl.equals(text)) {
					jxbltl++;
				} else if (Lcczjnzd.equals(text)) {
					lcczjnzd++;
				} else if (Lcblsxzd.equals(text)) {
					lcblsxzd++;
				} else if (Ssczzd.equals(text)) {
					ssczzd++;
				} else if (Yxzdbgsxzd.equals(text)) {
					yxzdbgsxzd++;
				} else if (Lcwxyd.equals(text)) {
					lcwxyd++;
				} else if (Ryjy.equals(text)) {
					ryjy++;
				} else if (Rzyjdjy.equals(text)) {
					rzyjdjy++;
				} else if (Cjbg.equals(text)) {
					cjbg++;
				} else if (Bgdfx.equals(text)) {
					bgdfx++;
				}
			}
		}

		//获取resrec数据
        String afterEvaluationId = com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId();
		List<ResSchProcessExpress> recs = expressBiz.searchResRecWithBLOBsByRotationDeptFlow(afterEvaluationId, recordFlow, user.getUserFlow());
		if (recs != null && recs.size() > 0) {
			ResSchProcessExpress resRecs = recs.get(0);
			Map<String, Object> formDataMap = resRecBiz.parseRecContent(resRecs.getRecContent());
			dataMap.put("attendance", StringUtil.defaultIfEmpty((String) formDataMap.get("attendance"), " "));
			dataMap.put("leave", StringUtil.defaultIfEmpty((String) formDataMap.get("leave"), " "));
			dataMap.put("sickLeave", StringUtil.defaultIfEmpty((String) formDataMap.get("sickLeave"), " "));
			dataMap.put("materLeave", StringUtil.defaultIfEmpty((String) formDataMap.get("materLeave"), " "));
			dataMap.put("absenteeism", StringUtil.defaultIfEmpty((String) formDataMap.get("absenteeism"), " "));
		}
		if (dataMap.get("zsgjflfgJXCF") == null) {
			dataMap.put("zsgjflfgJXCF", "□");
		}
		if (dataMap.get("zsgjflfgYN") == null) {
			dataMap.put("zsgjflfgYN", "□");
		}
		if (dataMap.get("zsgjflfgWZBLTL") == null) {
			dataMap.put("zsgjflfgWZBLTL", "□");
		}
		if (dataMap.get("zsgjflfgXSJZ") == null) {
			dataMap.put("zsgjflfgXSJZ", "□");
		}
		if (dataMap.get("lxgwzzJXCF") == null) {
			dataMap.put("lxgwzzJXCF", "□");
		}
		if (dataMap.get("lxgwzzYN") == null) {
			dataMap.put("lxgwzzYN", "□");
		}
		if (dataMap.get("lxgwzzWZBLTL") == null) {
			dataMap.put("lxgwzzWZBLTL", "□");
		}
		if (dataMap.get("lxgwzzXSJZ") == null) {
			dataMap.put("lxgwzzXSJZ", "□");
		}
		if (dataMap.get("ybrwzxJXCF") == null) {
			dataMap.put("ybrwzxJXCF", "□");
		}
		if (dataMap.get("ybrwzxYN") == null) {
			dataMap.put("ybrwzxYN", "□");
		}
		if (dataMap.get("ybrwzxWZBLTL") == null) {
			dataMap.put("ybrwzxWZBLTL", "□");
		}
		if (dataMap.get("ybrwzxXSJZ") == null) {
			dataMap.put("ybrwzxXSJZ", "□");
		}
		if (dataMap.get("rjgtbdnlJXCF") == null) {
			dataMap.put("rjgtbdnlJXCF", "□");
		}
		if (dataMap.get("rjgtbdnlYN") == null) {
			dataMap.put("rjgtbdnlYN", "□");
		}
		if (dataMap.get("rjgtbdnlWZBLTL") == null) {
			dataMap.put("rjgtbdnlWZBLTL", "□");
		}
		if (dataMap.get("rjgtbdnlXSJZ") == null) {
			dataMap.put("rjgtbdnlXSJZ", "□");
		}
		if (dataMap.get("tjxzjsxmJXCF") == null) {
			dataMap.put("tjxzjsxmJXCF", "□");
		}
		if (dataMap.get("tjxzjsxmYN") == null) {
			dataMap.put("tjxzjsxmYN", "□");
		}
		if (dataMap.get("tjxzjsxmWZBLTL") == null) {
			dataMap.put("tjxzjsxmWZBLTL", "□");
		}
		if (dataMap.get("tjxzjsxmXSJZ") == null) {
			dataMap.put("tjxzjsxmXSJZ", "□");
		}
		if (dataMap.get("jbllzwcdJXCF") == null) {
			dataMap.put("jbllzwcdJXCF", "□");
		}
		if (dataMap.get("jbllzwcdYN") == null) {
			dataMap.put("jbllzwcdYN", "□");
		}
		if (dataMap.get("jbllzwcdWZBLTL") == null) {
			dataMap.put("jbllzwcdWZBLTL", "□");
		}
		if (dataMap.get("jbllzwcdXSJZ") == null) {
			dataMap.put("jbllzwcdXSJZ", "□");
		}
		if (dataMap.get("njbjnzwcdJXCF") == null) {
			dataMap.put("njbjnzwcdJXCF", "□");
		}
		if (dataMap.get("njbjnzwcdYN") == null) {
			dataMap.put("njbjnzwcdYN", "□");
		}
		if (dataMap.get("njbjnzwcdWZBLTL") == null) {
			dataMap.put("njbjnzwcdWZBLTL", "□");
		}
		if (dataMap.get("njbjnzwcdXSJZ") == null) {
			dataMap.put("njbjnzwcdXSJZ", "□");
		}
		if (dataMap.get("lcswnlJXCF") == null) {
			dataMap.put("lcswnlJXCF", "□");
		}
		if (dataMap.get("lcswnlYN") == null) {
			dataMap.put("lcswnlYN", "□");
		}
		if (dataMap.get("lcswnlWZBLTL") == null) {
			dataMap.put("lcswnlWZBLTL", "□");
		}
		if (dataMap.get("lcswnlXSJZ") == null) {
			dataMap.put("lcswnlXSJZ", "□");
		}
		if (dataMap.get("lczlnlJXCF") == null) {
			dataMap.put("lczlnlJXCF", "□");
		}
		if (dataMap.get("lczlnlYN") == null) {
			dataMap.put("lczlnlYN", "□");
		}
		if (dataMap.get("lczlnlWZBLTL") == null) {
			dataMap.put("lczlnlWZBLTL", "□");
		}
		if (dataMap.get("lczlnlXSJZ") == null) {
			dataMap.put("lczlnlXSJZ", "□");
		}
		if (dataMap.get("jjclnlJXCF") == null) {
			dataMap.put("jjclnlJXCF", "□");
		}
		if (dataMap.get("jjclnlYN") == null) {
			dataMap.put("jjclnlYN", "□");
		}
		if (dataMap.get("jjclnlWZBLTL") == null) {
			dataMap.put("jjclnlWZBLTL", "□");
		}
		if (dataMap.get("jjclnlXSJZ") == null) {
			dataMap.put("jjclnlXSJZ", "□");
		}
		if (dataMap.get("szkskhxzztpjJXCF") == null) {
			dataMap.put("szkskhxzztpjJXCF", "□");
		}
		if (dataMap.get("szkskhxzztpjLING") == null) {
			dataMap.put("szkskhxzztpjLING", "□");
		}
		//获取本人所有排班
		List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), user.getUserFlow());
		if (resultList != null && resultList.size() > 0) {
			Float monthCount = 0f;
			String startDate = null;
			String endDate = null;
			for (SchArrangeResult sar : resultList) {
				String month = sar.getSchMonth();
				if (StringUtil.isNotBlank(month)) {
					Float currMonth = Float.parseFloat(month);
					if (currMonth != null) {
						monthCount += currMonth;
					}
				}
				if (startDate == null) {
					startDate = sar.getSchStartDate();
				}

				endDate = sar.getSchEndDate();

			}

			dataMap.put("monthCount", monthCount.toString());
			dataMap.put("startDate", startDate);
			dataMap.put("endDate", endDate);
		}

		dataMap.put("userName", user.getUserName());
		dataMap.put("sessionNumber", doctor.getSessionNumber());
		dataMap.put("trainingSpeName", doctor.getTrainingSpeName());
		dataMap.put("standardDeptName", dept.getStandardDeptName());

		String cfgCode = "jsres_doctor_app_menu_" + doctor.getDoctorFlow();
		JsresPowerCfg cfg = jsResPowerCfgBiz.read(cfgCode);
        if ((cfg != null && com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(cfg.getCfgValue()))) {
			if (caseRegistryReqNum == null || "0".equals(caseRegistryReqNum)) {
				dataMap.put("caseRegistry", "100");
			} else {
				dataMap.put("caseRegistry", StringUtil.defaultIfEmpty(caseRegistry, "0"));
			}
			dataMap.put("caseRegistryReqNum", StringUtil.defaultIfEmpty(caseRegistryReqNum, "0"));
			dataMap.put("caseRegistryFinished", StringUtil.defaultIfEmpty(caseRegistryFinished, "0"));

			if (diseaseRegistryReqNum == null || "0".equals(diseaseRegistryReqNum)) {
				dataMap.put("diseaseRegistry", "100");
			} else {
				dataMap.put("diseaseRegistry", StringUtil.defaultIfEmpty(diseaseRegistry, "0"));
			}
			dataMap.put("diseaseRegistryReqNum", StringUtil.defaultIfEmpty(diseaseRegistryReqNum, "0"));
			dataMap.put("diseaseRegistryFinished", StringUtil.defaultIfEmpty(diseaseRegistryFinished, "0"));

			if (skillRegistryReqNum == null || "0".equals(skillRegistryReqNum)) {
				dataMap.put("skillRegistry", "100");
			} else {
				dataMap.put("skillRegistry", StringUtil.defaultIfEmpty(skillRegistry, "0"));
			}
			dataMap.put("skillRegistryReqNum", StringUtil.defaultIfEmpty(skillRegistryReqNum, "0"));
			dataMap.put("skillRegistryFinished", StringUtil.defaultIfEmpty(skillRegistryFinished, "0"));

			if (skillAndOperationRegistryReqNum == null || "0".equals(skillAndOperationRegistryReqNum)) {
				dataMap.put("operationRegistry", "100");
			} else {
				dataMap.put("operationRegistry", StringUtil.defaultIfEmpty(skillAndOperationRegistry, "0"));
			}
			dataMap.put("operationRegistryReqNum", StringUtil.defaultIfEmpty(skillAndOperationRegistryReqNum, "0"));
			dataMap.put("operationRegistryFinished", StringUtil.defaultIfEmpty(skillAndOperationRegistryFinished, "0"));

			dataMap.put("jxcf", String.valueOf(jxcf));
//			dataMap.put("ynbltl",String.valueOf(ynbltl));
//			dataMap.put("wzbltl",String.valueOf(wzbltl));
			dataMap.put("xjk", String.valueOf(xjk));
//			dataMap.put("swbltl",String.valueOf(swbltl));
			dataMap.put("rkjy", String.valueOf(rkjy));
//			dataMap.put("ckkh",String.valueOf(ckkh));
//			dataMap.put("jnpx",String.valueOf(jnpx));
			dataMap.put("yph", String.valueOf(yph));
			dataMap.put("jxhz", String.valueOf(jxhz));
			dataMap.put("jxbltl", String.valueOf(jxbltl));
			dataMap.put("ynbltl", String.valueOf(ynbltl));
			dataMap.put("wzbltl", String.valueOf(wzbltl));
			dataMap.put("swbltl", String.valueOf(swbltl));
			dataMap.put("lcczjnzd", String.valueOf(lcczjnzd));
			dataMap.put("lcblsxzd", String.valueOf(lcblsxzd));
			dataMap.put("ssczzd", String.valueOf(ssczzd));
			dataMap.put("yxzdbgsxzd", String.valueOf(yxzdbgsxzd));
			dataMap.put("lcwxyd", String.valueOf(lcwxyd));
			dataMap.put("ryjy", String.valueOf(ryjy));
			dataMap.put("rzyjdjy", String.valueOf(rzyjdjy));
			dataMap.put("cjbg", String.valueOf(cjbg));
			dataMap.put("bgdfx", String.valueOf(bgdfx));
		} else {
			dataMap.put("caseRegistry", "0");
			dataMap.put("caseRegistryReqNum", "0");
			dataMap.put("caseRegistryFinished", "0");

			dataMap.put("diseaseRegistry", "0");
			dataMap.put("diseaseRegistryReqNum", "0");
			dataMap.put("diseaseRegistryFinished", "0");

			dataMap.put("skillRegistry", "0");
			dataMap.put("skillRegistryReqNum", "0");
			dataMap.put("skillRegistryFinished", "0");

			dataMap.put("operationRegistry", "0");
			dataMap.put("operationRegistryReqNum", "0");
			dataMap.put("operationRegistryFinished", "0");

			dataMap.put("jxcf", "0");
//			dataMap.put("ynbltl","0");
//			dataMap.put("wzbltl","0");
			dataMap.put("xjk", "0");
//			dataMap.put("swbltl","0");
			dataMap.put("rkjy", "0");
//			dataMap.put("ckkh","0");
//			dataMap.put("jnpx","0");
			dataMap.put("yph", "0");
			dataMap.put("jxhz", "0");
			dataMap.put("jxbltl", "0");
			dataMap.put("ynbltl", "0");
			dataMap.put("wzbltl", "0");
			dataMap.put("swbltl", "0");
			dataMap.put("lcczjnzd", "0");
			dataMap.put("lcblsxzd", "0");
			dataMap.put("ssczzd", "0");
			dataMap.put("yxzdbgsxzd", "0");
			dataMap.put("lcwxyd", "0");
			dataMap.put("ryjy", "0");
			dataMap.put("rzyjdjy", "0");
			dataMap.put("cjbg", "0");
			dataMap.put("bgdfx", "0");
		}

		final String fileName = "江苏省住院医师规范化培训临床轮转出科表";
		String outputFileClass = ResourceLoader.getPath("/");
		String outputFile = new File(outputFileClass) + "/load/" + fileName + ".pdf";
		File file = new File(outputFile);
		// 模板数据
		DocumentVo vo = new DocumentVo() {
			@Override
			public String findPrimaryKey() {
				return fileName;
			}

			@Override
			public Map<String, Object> fillDataMap() {
				return dataMap;
			}
		};
		String template = "jsres/ckkhbTemplate.html";
		PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
		// 生成pdf
		pdfGenerator.generate(template, vo, outputFile);

		pubFileBiz.downFile(file, response);
	}

	//角色切换
	@RequestMapping(value = "/getRoles")
	@ResponseBody
	public List<Map<String, String>> getRoles() {
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		List<SysUserRole> userRoles = userRoleBiz.getByUserFlow(userFlow);
		if (userRoles != null && !userRoles.isEmpty()) {
			List<Map<String, String>> roles = new ArrayList<Map<String, String>>();
			for (SysUserRole sur : userRoles) {
				String roleFlow = sur.getRoleFlow();
				Map<String, String> role = getRoleUrl(roleFlow);
				if (role != null) {
					roles.add(role);
				}
			}
			return roles;
		}
		return null;
	}

	public Map<String, String> getRoleUrl(String roleFlow) {
		if (StringUtil.isNotBlank(roleFlow)) {
			Map<String, String> role = new HashMap<String, String>();
			role.put("roleFlow", roleFlow);
			if (roleFlow.equals(InitConfig.getSysCfg("res_global_role_flow"))) {//省级部门
				role.put("roleName", InitConfig.getSysCfgDesc("res_global_role_flow"));
				role.put("roleIndex", "/jsres/manage/global");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_qkzx_role_flow"))) {//审核部门之全科中心
				role.put("roleName", InitConfig.getSysCfgDesc("res_qkzx_role_flow"));
				role.put("roleIndex", "/jsres/manage/province");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_bjw_role_flow"))) {//审核部门之毕教委
				role.put("roleName", InitConfig.getSysCfgDesc("res_bjw_role_flow"));
				role.put("roleIndex", "/jsres/manage/province");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_zyglj_role_flow"))) {//审核部门之中医管理局
				role.put("roleName", InitConfig.getSysCfgDesc("res_zyglj_role_flow"));
				role.put("roleIndex", "/jsres/manage/province");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_school_role_flow"))) {//学校
				role.put("roleName", InitConfig.getSysCfgDesc("res_school_role_flow"));
				role.put("roleIndex", "/jsres/manage/school");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_charge_role_flow"))) {//主管部门
				role.put("roleName", InitConfig.getSysCfgDesc("res_charge_role_flow"));
				role.put("roleIndex", "/jsres/manage/charge");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_admin_role_flow"))) {//医院管理员
				role.put("roleName", InitConfig.getSysCfgDesc("res_admin_role_flow"));
				role.put("roleIndex", "/jsres/manage/local");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_head_role_flow"))) {//科主任
				role.put("roleName", InitConfig.getSysCfgDesc("res_head_role_flow"));
				role.put("roleIndex", "/jsres/kzr/index");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_teaching_head_role_flow"))) {//教学主任
				role.put("roleName", InitConfig.getSysCfgDesc("res_teaching_head_role_flow"));
				role.put("roleIndex", "/jsres/kzr/index");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_secretary_role_flow"))) {//科秘
				role.put("roleName", InitConfig.getSysCfgDesc("res_secretary_role_flow"));
				role.put("roleIndex", "/jsres/km/index");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_teaching_secretary_role_flow"))) {//教学秘书
				role.put("roleName", InitConfig.getSysCfgDesc("res_teaching_secretary_role_flow"));
				role.put("roleIndex", "/jsres/km/index");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_teacher_role_flow"))) {//带教老师
				role.put("roleName", InitConfig.getSysCfgDesc("res_teacher_role_flow"));
				role.put("roleIndex", "/jsres/teacher/index");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_responsible_teacher_role_flow"))) {//责任导师
				role.put("roleName", InitConfig.getSysCfgDesc("res_responsible_teacher_role_flow"));
				role.put("roleIndex", "/res/responsibleTeacher/index");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_doctor_role_flow"))) {//学员
				role.put("roleName", InitConfig.getSysCfgDesc("res_doctor_role_flow"));
				role.put("roleIndex", "/jsres/doctor/index");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_professionalBase_admin_role_flow"))) {//专业基地管理员
				role.put("roleName", InitConfig.getSysCfgDesc("res_professionalBase_admin_role_flow"));
				role.put("roleIndex", "//jsres/manage/speAdmin");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_professionalBase_adminSecretary_role_flow"))) {//专业基地秘书
				role.put("roleName", InitConfig.getSysCfgDesc("res_professionalBase_adminSecretary_role_flow"));
				role.put("roleIndex", "/jsres/manage/speAdminSecretary");
			} else if (roleFlow.equals(InitConfig.getSysCfg("res_hospitalLeader_role_flow"))) { //督导 评分专家
				role.put("roleName", InitConfig.getSysCfgDesc("res_hospitalLeader_role_flow"));
				role.put("roleIndex", "/jsres/manage/hospitalLeader");
			}
			return role;
		}
		return null;
	}

	/**
	 * 科室轮转查询  住院医师
	 */
	@RequestMapping(value = {"/doc/schDept"})
	public String schDept(String startDate, String endDate, String schDeptFlow, String[] datas, Integer currentPage,
						  String sessionNumber, Model model, HttpServletRequest request) {
		List<String> titleDate = null;
        boolean isWeek = com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if (!StringUtil.isNotBlank(startDate)) {
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate, -8);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			if (isWeek) {
				titleDate = getWeeksByTwoDate(startDate, endDate);
			} else {
				String schStartMonth = startDate.substring(0, 7);
				String schEndMonth = endDate.substring(0, 7);
				titleDate = getMonthsByTwoMonth(schStartMonth, schEndMonth);
			}
			model.addAttribute("titleDate", titleDate);
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("schDeptList", schDeptList);
			SysDeptExample example = new SysDeptExample();
            SysDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
			List<SysDept> allDeptList = sysDeptMapper.selectByExample(example);
			model.addAttribute("allDeptList", allDeptList);
			if (StringUtil.isNotBlank(schDeptFlow)) {
				criteria.andDeptFlowEqualTo(schDeptFlow);
			}
			List<String> docTypeList = new ArrayList<String>();
			//人员类型
			if (datas != null && datas.length > 0) {
				for (String s : datas) {
					docTypeList.add(s);
				}
			} else {
                for (ResDocTypeEnum type : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
					docTypeList.add(type.getId());
				}
			}
			PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
			List<SysDept> deptList = sysDeptMapper.selectByExample(example);
			model.addAttribute("deptList", deptList);
			sessionNumber = "".equals(sessionNumber) ? null : sessionNumber;
			Map<String, Object> parmMap = new HashMap<>();
			parmMap.put("schStartDate", startDate);
			parmMap.put("SchEndDate", endDate);
			parmMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
			parmMap.put("docTypeList", docTypeList);
			parmMap.put("doctorStatusId", 20);        //在培
            parmMap.put("trainingSpeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());  //住院医师
//			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());
			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg1(parmMap);
			if (arrResultList != null && arrResultList.size() > 0) {
				Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for (SchArrangeResult sar : arrResultList) {
					if (null != sessionNumber && StringUtil.isNotBlank(sessionNumber) && !sessionNumber.equals(sar.getSessionNumber())) {//sessionNumber届别过滤
						continue;
					}
					if (null != schDeptFlow && StringUtil.isNotBlank(schDeptFlow) && !schDeptFlow.equals(sar.getSchDeptFlow()))//轮转科室过滤
					{
						continue;
					}
					String schDeptFlow2 = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
						if (isWeek) {
							List<String> weeks = getWeeksByTwoDate(resultStartDate, resultEndDate);
							if (weeks != null) {
								for (String week : weeks) {
									String key = schDeptFlow2 + week;
									if (resultListMap.get(key) == null) {
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key, sarList);
									} else {
										resultListMap.get(key).add(sar);
									}
								}
							}
						} else {
							resultStartDate = resultStartDate.substring(0, 7);
							resultEndDate = resultEndDate.substring(0, 7);
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
							if (months != null) {
								for (String month : months) {
									String key = schDeptFlow2 + month;
									if (resultListMap.get(key) == null) {
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key, sarList);
									} else {
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}
				model.addAttribute("resultListMap", resultListMap);
			}
			model.addAttribute("datas", datas);
		}
		return "jsres/doctor/schDept";
	}

	/**
	 * 科室轮转查询 助理全科
	 */
	@RequestMapping(value = {"/doc/schDeptAcc"})
	public String schDeptAcc(String startDate, String endDate, String schDeptFlow, String[] datas, Integer currentPage,
							 String sessionNumber, Model model, HttpServletRequest request) {
		List<String> titleDate = null;
        boolean isWeek = com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if (!StringUtil.isNotBlank(startDate)) {
			endDate = DateUtil.getCurrDate();
			startDate = DateUtil.newDateOfAddMonths(endDate, -8);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		}
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			if (isWeek) {
				titleDate = getWeeksByTwoDate(startDate, endDate);
			} else {
				String schStartMonth = startDate.substring(0, 7);
				String schEndMonth = endDate.substring(0, 7);
				titleDate = getMonthsByTwoMonth(schStartMonth, schEndMonth);
			}
			model.addAttribute("titleDate", titleDate);
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
			model.addAttribute("schDeptList", schDeptList);
			SysDeptExample example = new SysDeptExample();
            SysDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());
			List<SysDept> allDeptList = sysDeptMapper.selectByExample(example);
			model.addAttribute("allDeptList", allDeptList);
			if (StringUtil.isNotBlank(schDeptFlow)) {
				criteria.andDeptFlowEqualTo(schDeptFlow);
			}
			List<String> docTypeList = new ArrayList<String>();
			//人员类型
			if (datas != null && datas.length > 0) {
				for (String s : datas) {
					docTypeList.add(s);
				}
			} else {
                for (ResDocTypeEnum type : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
					docTypeList.add(type.getId());
				}
			}
			PageHelper.startPage(currentPage, getPageSize(request));//分页,必须放在分页sql前
			List<SysDept> deptList = sysDeptMapper.selectByExample(example);
			model.addAttribute("deptList", deptList);
			sessionNumber = "".equals(sessionNumber) ? null : sessionNumber;
			Map<String, Object> parmMap = new HashMap<>();
			parmMap.put("schStartDate", startDate);
			parmMap.put("SchEndDate", endDate);
			parmMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
			parmMap.put("docTypeList", docTypeList);
			parmMap.put("doctorStatusId", 20);        //在培
            parmMap.put("trainingSpeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());  //助理全科
//			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());
			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg1(parmMap);
			if (arrResultList != null && arrResultList.size() > 0) {
				Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for (SchArrangeResult sar : arrResultList) {
					if (null != sessionNumber && StringUtil.isNotBlank(sessionNumber) && !sessionNumber.equals(sar.getSessionNumber())) {//sessionNumber届别过滤
						continue;
					}
					if (null != schDeptFlow && StringUtil.isNotBlank(schDeptFlow) && !schDeptFlow.equals(sar.getSchDeptFlow()))//轮转科室过滤
					{
						continue;
					}
					String schDeptFlow2 = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
						if (isWeek) {
							List<String> weeks = getWeeksByTwoDate(resultStartDate, resultEndDate);
							if (weeks != null) {
								for (String week : weeks) {
									String key = schDeptFlow2 + week;
									if (resultListMap.get(key) == null) {
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key, sarList);
									} else {
										resultListMap.get(key).add(sar);
									}
								}
							}
						} else {
							resultStartDate = resultStartDate.substring(0, 7);
							resultEndDate = resultEndDate.substring(0, 7);
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
							if (months != null) {
								for (String month : months) {
									String key = schDeptFlow2 + month;
									if (resultListMap.get(key) == null) {
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key, sarList);
									} else {
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}
				model.addAttribute("resultListMap", resultListMap);
			}
			model.addAttribute("datas", datas);
		}
		return "jsres/doctor/schDeptAcc";
	}

	@RequestMapping("/template/exportExcel")
	public void exportExcel(String startDate, String endDate, String schDeptFlow, String sessionNumber, String[] datas,
							HttpServletResponse response) throws IOException, Exception {
		List<String> titleDate = null;
        boolean isWeek = com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			if (isWeek) {
				titleDate = getWeeksByTwoDate(startDate, endDate);
			} else {
				String schStartMonth = startDate.substring(0, 7);
				String schEndMonth = endDate.substring(0, 7);
				titleDate = getMonthsByTwoMonth(schStartMonth, schEndMonth);
			}
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
			SysDeptExample example = new SysDeptExample();
            SysDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());

			if (StringUtil.isNotBlank(schDeptFlow)) {
				criteria.andDeptFlowEqualTo(schDeptFlow);
			}
			List<SysDept> deptList = sysDeptMapper.selectByExample(example);

			List<String> docTypeList = new ArrayList<String>();
			//人员类型
			if (datas != null && datas.length > 0) {
				for (String s : datas) {
					docTypeList.add(s);
				}
			} else {
                for (ResDocTypeEnum type : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
					docTypeList.add(type.getId());
				}
			}
			schDeptFlow = "".equals(schDeptFlow) ? null : schDeptFlow;
			sessionNumber = "".equals(sessionNumber) ? null : sessionNumber;
			Map<String, Object> parmMap = new HashMap<>();
			parmMap.put("schStartDate", startDate);
			parmMap.put("SchEndDate", endDate);
			parmMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
			parmMap.put("docTypeList", docTypeList);
            parmMap.put("trainingSpeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());  //住院医师
			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg1(parmMap);
//			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());
			if (arrResultList != null && arrResultList.size() > 0) {
				Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for (SchArrangeResult sar : arrResultList) {
					if ((null != sessionNumber && StringUtil.isNotBlank(sessionNumber) && !sessionNumber.equals(sar.getSessionNumber())) || (null != schDeptFlow && StringUtil.isNotBlank(schDeptFlow) && !schDeptFlow.equals(sar.getSchDeptFlow()))) {//sessionNumber届别及科室过滤
						continue;
					}
					String deptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
						if (isWeek) {
							List<String> weeks = getWeeksByTwoDate(resultStartDate, resultEndDate);
							if (weeks != null) {
								for (String week : weeks) {
									String key = deptFlow + week;
									if (resultListMap.get(key) == null) {
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key, sarList);
									} else {
										resultListMap.get(key).add(sar);
									}
								}
							}
						} else {
							resultStartDate = resultStartDate.substring(0, 7);
							resultEndDate = resultEndDate.substring(0, 7);
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
							if (months != null) {
								for (String month : months) {
									String key = deptFlow + month;
									if (resultListMap.get(key) == null) {
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key, sarList);
									} else {
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}
				//创建工作簿
				HSSFWorkbook wb = new HSSFWorkbook();
				// 为工作簿添加sheet
				HSSFSheet sheet = wb.createSheet("sheet1");

				//定义将用到的样式
				HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
				styleCenter.setAlignment(HorizontalAlignment.CENTER);

				sheet.setColumnWidth(0, 3000);
				HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
				styleLeft.setAlignment(HorizontalAlignment.LEFT);
				styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

				HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
				stylevwc.setAlignment(HorizontalAlignment.CENTER);
				stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

				HSSFRow titleRow = sheet.createRow(0);
				HSSFCell cell2 = titleRow.createCell(0);
				cell2.setCellValue("科室名称");
				cell2.setCellStyle(styleLeft);
				int colnum1 = 1;
				for (String Date : titleDate) {
					HSSFCell deptCell2 = titleRow.createCell(colnum1);
					deptCell2.setCellValue(Date);
					deptCell2.setCellStyle(styleCenter);
					colnum1++;
				}
				//列宽自适应
				Map<Integer, Integer> colWidthAuto = new HashMap<Integer, Integer>();
				List<Integer> deptFLowList = new ArrayList<Integer>();
				if (deptList != null && deptList.size() > 0 && resultListMap != null) {
					int rownum = 1;
					for (SysDept rd : deptList) {
						if (null != schDeptFlow && !schDeptFlow.equals(rd.getDeptFlow())) {
							continue;
						}
						//HSSFRow rowDept = sheet.createRow(rownum);
						int j = 0;
						for (String tDate : titleDate) {
							String flow = rd.getDeptFlow() + tDate;
							List<SchArrangeResult> sars = resultListMap.get(flow);
							if (sars != null && !sars.isEmpty()) {
								deptFLowList.add(sars.size());
							}
						}
						if (deptFLowList != null && deptFLowList.isEmpty()) {
							deptFLowList.add(1);
						}
						j = Collections.max(deptFLowList);
						for (int i = 0; i < j; i++) {
							HSSFRow rowDate = sheet.createRow(rownum + i);
							HSSFCell cell1 = rowDate.createCell(0);
							cell1.setCellValue(rd.getDeptName());
							cell1.setCellStyle(styleLeft);
							int colnum = 1;
							for (String tDate : titleDate) {
								Integer width = colWidthAuto.get(colnum);
								if (width == null) {
									width = 0;
								}
								String flow = rd.getDeptFlow() + tDate;
								List<SchArrangeResult> sars = resultListMap.get(flow);
								if (sars != null && !sars.isEmpty()) {
									String doctorName = "";
									if (sars.size() > i) {
										SchArrangeResult result = sars.get(i);
										HSSFCell cell = rowDate.createCell(colnum);
										doctorName = doctorName + result.getDoctorName() + "(" + result.getSchStartDate() + "~" + result.getSchEndDate() + ")";
										cell.setCellValue(doctorName);
										cell.setCellStyle(styleLeft);
										Integer dateNewWidth = doctorName.getBytes().length * 1 * 256;
										width = width < dateNewWidth ? dateNewWidth : width;
										sheet.setColumnWidth(colnum, width);
										colWidthAuto.put(colnum, width);
									}
								} else {
									HSSFCell cell = rowDate.createCell(colnum);
									String doctorName = "";
									cell.setCellValue(doctorName);
									cell.setCellStyle(styleLeft);
								}
								colnum++;
							}
						}
						//合并单元格
						if (j > 1) {
							sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + j - 1, 0, 0));
						}
//						sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + j - 1, 0, 0));
						deptFLowList.clear();
//						HSSFCell cell = rowDept.createCell(0);
//						cell.setCellValue(rd.getDeptName());
//						cell.setCellStyle(styleLeft);
//						int colnum = 1;
//						for (String Date : titleDate) {
//							HSSFCell deptCell = rowDept.createCell(colnum);
//							deptCell.setCellValue(Date);
//							deptCell.setCellStyle(styleCenter);
//							colnum++;
//						}
						rownum = rownum + (j);
					}
				}
				String fileName = "学员排班表.xls";
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				response.setContentType("application/octet-stream;charset=UTF-8");
				wb.write(response.getOutputStream());

			}
		}
	}

	@RequestMapping("/template/exportExcelAcc")
	public void exportExcelAcc(String startDate, String endDate, String schDeptFlow, String sessionNumber, String[] datas,
							   HttpServletResponse response) throws IOException, Exception {
		List<String> titleDate = null;
        boolean isWeek = com.pinde.core.common.enums.SchUnitEnum.Week.getId().equals(InitConfig.getSysCfg("res_rotation_unit"));
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
			if (isWeek) {
				titleDate = getWeeksByTwoDate(startDate, endDate);
			} else {
				String schStartMonth = startDate.substring(0, 7);
				String schEndMonth = endDate.substring(0, 7);
				titleDate = getMonthsByTwoMonth(schStartMonth, schEndMonth);
			}
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(GlobalContext.getCurrentUser().getOrgFlow());
			SysDeptExample example = new SysDeptExample();
            SysDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andOrgFlowEqualTo(GlobalContext.getCurrentUser().getOrgFlow());

			if (StringUtil.isNotBlank(schDeptFlow)) {
				criteria.andDeptFlowEqualTo(schDeptFlow);
			}
			List<SysDept> deptList = sysDeptMapper.selectByExample(example);

			List<String> docTypeList = new ArrayList<String>();
			//人员类型
			if (datas != null && datas.length > 0) {
				for (String s : datas) {
					docTypeList.add(s);
				}
			} else {
                for (ResDocTypeEnum type : com.pinde.core.common.enums.ResDocTypeEnum.values()) {
					docTypeList.add(type.getId());
				}
			}
			schDeptFlow = "".equals(schDeptFlow) ? null : schDeptFlow;
			sessionNumber = "".equals(sessionNumber) ? null : sessionNumber;
			Map<String, Object> parmMap = new HashMap<>();
			parmMap.put("schStartDate", startDate);
			parmMap.put("SchEndDate", endDate);
			parmMap.put("orgFlow", GlobalContext.getCurrentUser().getOrgFlow());
			parmMap.put("docTypeList", docTypeList);
            parmMap.put("trainingSpeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());  //助理全科
			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg1(parmMap);
//			List<SchArrangeResult> arrResultList = resultBiz.searchArrangeResultByDateAndOrg(startDate, endDate,GlobalContext.getCurrentUser().getOrgFlow());
			if (arrResultList != null && arrResultList.size() > 0) {
				Map<String, List<SchArrangeResult>> resultListMap = new HashMap<String, List<SchArrangeResult>>();
				for (SchArrangeResult sar : arrResultList) {
					if ((null != sessionNumber && StringUtil.isNotBlank(sessionNumber) && !sessionNumber.equals(sar.getSessionNumber())) || (null != schDeptFlow && StringUtil.isNotBlank(schDeptFlow) && !schDeptFlow.equals(sar.getSchDeptFlow()))) {//sessionNumber届别及科室过滤
						continue;
					}
					String deptFlow = sar.getSchDeptFlow();
					String resultStartDate = sar.getSchStartDate();
					String resultEndDate = sar.getSchEndDate();
					if (StringUtil.isNotBlank(resultStartDate) && StringUtil.isNotBlank(resultEndDate)) {
						if (isWeek) {
							List<String> weeks = getWeeksByTwoDate(resultStartDate, resultEndDate);
							if (weeks != null) {
								for (String week : weeks) {
									String key = deptFlow + week;
									if (resultListMap.get(key) == null) {
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key, sarList);
									} else {
										resultListMap.get(key).add(sar);
									}
								}
							}
						} else {
							resultStartDate = resultStartDate.substring(0, 7);
							resultEndDate = resultEndDate.substring(0, 7);
							//计算该轮转科室该月人数
							List<String> months = getMonthsByTwoMonth(resultStartDate, resultEndDate);
							if (months != null) {
								for (String month : months) {
									String key = deptFlow + month;
									if (resultListMap.get(key) == null) {
										List<SchArrangeResult> sarList = new ArrayList<SchArrangeResult>();
										sarList.add(sar);
										resultListMap.put(key, sarList);
									} else {
										resultListMap.get(key).add(sar);
									}
								}
							}
						}
					}
				}
				//创建工作簿
				HSSFWorkbook wb = new HSSFWorkbook();
				// 为工作簿添加sheet
				HSSFSheet sheet = wb.createSheet("sheet1");

				//定义将用到的样式
				HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
				styleCenter.setAlignment(HorizontalAlignment.CENTER);

				sheet.setColumnWidth(0, 3000);
				HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
				styleLeft.setAlignment(HorizontalAlignment.LEFT);
				styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);

				HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
				stylevwc.setAlignment(HorizontalAlignment.CENTER);
				stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);

				HSSFRow titleRow = sheet.createRow(0);
				HSSFCell cell2 = titleRow.createCell(0);
				cell2.setCellValue("科室名称");
				cell2.setCellStyle(styleLeft);
				int colnum1 = 1;
				for (String Date : titleDate) {
					HSSFCell deptCell2 = titleRow.createCell(colnum1);
					deptCell2.setCellValue(Date);
					deptCell2.setCellStyle(styleCenter);
					colnum1++;
				}
				//列宽自适应
				Map<Integer, Integer> colWidthAuto = new HashMap<Integer, Integer>();
				List<Integer> deptFLowList = new ArrayList<Integer>();
				if (deptList != null && deptList.size() > 0 && resultListMap != null) {
					int rownum = 1;
					for (SysDept rd : deptList) {
						if (null != schDeptFlow && !schDeptFlow.equals(rd.getDeptFlow())) {
							continue;
						}
						//HSSFRow rowDept = sheet.createRow(rownum);
						int j = 0;
						for (String tDate : titleDate) {
							String flow = rd.getDeptFlow() + tDate;
							List<SchArrangeResult> sars = resultListMap.get(flow);
							if (sars != null && !sars.isEmpty()) {
								deptFLowList.add(sars.size());
							}
						}
						if (deptFLowList != null && deptFLowList.isEmpty()) {
							deptFLowList.add(1);
						}
						j = Collections.max(deptFLowList);
						for (int i = 0; i < j; i++) {
							HSSFRow rowDate = sheet.createRow(rownum + i);
							HSSFCell cell1 = rowDate.createCell(0);
							cell1.setCellValue(rd.getDeptName());
							cell1.setCellStyle(styleLeft);
							int colnum = 1;
							for (String tDate : titleDate) {
								Integer width = colWidthAuto.get(colnum);
								if (width == null) {
									width = 0;
								}
								String flow = rd.getDeptFlow() + tDate;
								List<SchArrangeResult> sars = resultListMap.get(flow);
								if (sars != null && !sars.isEmpty()) {
									String doctorName = "";
									if (sars.size() > i) {
										SchArrangeResult result = sars.get(i);
										HSSFCell cell = rowDate.createCell(colnum);
										doctorName = doctorName + result.getDoctorName() + "(" + result.getSchStartDate() + "~" + result.getSchEndDate() + ")";
										cell.setCellValue(doctorName);
										cell.setCellStyle(styleLeft);
										Integer dateNewWidth = doctorName.getBytes().length * 1 * 256;
										width = width < dateNewWidth ? dateNewWidth : width;
										sheet.setColumnWidth(colnum, width);
										colWidthAuto.put(colnum, width);
									}
								} else {
									HSSFCell cell = rowDate.createCell(colnum);
									String doctorName = "";
									cell.setCellValue(doctorName);
									cell.setCellStyle(styleLeft);
								}
								colnum++;
							}
						}
						//合并单元格
						sheet.addMergedRegion(new CellRangeAddress(rownum, rownum + j - 1, 0, 0));
						deptFLowList.clear();
//						HSSFCell cell = rowDept.createCell(0);
//						cell.setCellValue(rd.getDeptName());
//						cell.setCellStyle(styleLeft);
//						int colnum = 1;
//						for (String Date : titleDate) {
//							HSSFCell deptCell = rowDept.createCell(colnum);
//							deptCell.setCellValue(Date);
//							deptCell.setCellStyle(styleCenter);
//							colnum++;
//						}
						rownum = rownum + (j);
					}
				}
				String fileName = "学员排班表.xls";
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				response.setContentType("application/octet-stream;charset=UTF-8");
				wb.write(response.getOutputStream());

			}
		}
	}

	/**
	 * 获取两个日期之间的所有周
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getWeeksByTwoDate(String startDate, String endDate) {
		List<String> weeks = null;
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate) <= 0) {
			weeks = new ArrayList<String>();
			String startYear = startDate.substring(0, 4);
			String endYear = endDate.substring(0, 4);
			if (startYear.equals(endYear)) {
				long startDays = DateUtil.signDaysBetweenTowDate(startDate, startYear + "-01-01");
				long endDays = DateUtil.signDaysBetweenTowDate(endDate, startYear + "-01-01");
				long startWeek = weekFormat(startDays);
				long endWeek = weekFormat(endDays);
				while (startWeek <= endWeek) {
					weeks.add(startYear + "-" + startWeek);
					startWeek++;
				}
			} else {
				int start = Integer.parseInt(startYear);
				int end = Integer.parseInt(endYear);
				while (start <= end) {
					String currYear = String.valueOf(start);
					long dayNum = 0;
					if (startYear.equals(currYear)) {
						dayNum = DateUtil.signDaysBetweenTowDate(startDate, startYear + "-01-01");
						long endNum = DateUtil.signDaysBetweenTowDate(currYear + "-12-31", currYear + "-01-01");
						long startWeek = weekFormat(dayNum);
						long endWeek = weekFormat(endNum);
						while (startWeek <= endWeek) {
							weeks.add(currYear + "-" + startWeek);
							startWeek++;
						}
					} else if (endYear.equals(currYear)) {
						dayNum = DateUtil.signDaysBetweenTowDate(endDate, currYear + "-01-01");
						long startWeek = 1;
						long endWeek = weekFormat(dayNum);
						while (startWeek <= endWeek) {
							weeks.add(currYear + "-" + startWeek);
							startWeek++;
						}
					} else {
						dayNum = DateUtil.signDaysBetweenTowDate(currYear + "-12-31", currYear + "-01-01");
						long startWeek = 1;
						long endWeek = weekFormat(dayNum);
						while (startWeek <= endWeek) {
							weeks.add(currYear + "-" + startWeek);
							startWeek++;
						}
					}
					start++;
				}
			}
		}
		return weeks;
	}

	private long weekFormat(long days) {
		long result = 1;
		if (days != 0) {
			result = (long) Math.ceil(days / 7.0);
		}
		return result;
	}

	/**
	 * 获取两个月份之间的所有月
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	private List<String> getMonthsByTwoMonth(String startDate, String endDate) {
		List<String> months = null;
		if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate) && startDate.compareTo(endDate) <= 0) {
			months = new ArrayList<String>();
			months.add(startDate);
			if (!startDate.equals(endDate)) {
				String currDate = startDate;
				while (!currDate.equals(endDate)) {
					currDate = DateUtil.newMonthOfAddMonths(currDate, 1);
					months.add(currDate);
				}
			}
		}
		return months;
	}

	/**
	 * 加载基地专业
	 *
	 * @return
	 */
	@RequestMapping(value = "/searchResOrgSpeList", method = {RequestMethod.GET})
	@ResponseBody
	public List<ResOrgSpe> searchResOrgSpeList(String sessionNumber, ResOrgSpe resOrgSpe, Model model) {
		List<ResOrgSpe> speList = null;
        resOrgSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
		sessionNumber = ("").equals(sessionNumber) ? InitConfig.getSysCfg("res_reg_year") : sessionNumber;
		int year = Integer.parseInt(sessionNumber);
		if (year >= 2015) {
			speList = resOrgSpeBiz.searchResOrgSpeList(resOrgSpe, null);
		} else {
			String speTypeId = resOrgSpe.getSpeTypeId();
			if (StringUtil.isNotBlank(speTypeId)) {
				Map<String, String> speMap = InitConfig.getDictNameMap(speTypeId);
				speList = new ArrayList<ResOrgSpe>();
				ResOrgSpe orgSpe = null;
				for (Map.Entry<String, String> map : speMap.entrySet()) {
					orgSpe = new ResOrgSpe();
					orgSpe.setSpeId(map.getValue());
					orgSpe.setSpeName(map.getKey());
					speList.add(orgSpe);
				}
			}
		}
		return speList;
	}

	/**
	 * 医师工作量查询查询条件页  住院医师
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/docWorkQuery")
	public String docWorkQuery(Model model) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		//model.addAttribute("org",org);
		//如果当前基地含有协同基地则展示基地查询项（包含当前基地和协同基地）
		List<SysOrg> orgs = null;
		List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
		List<String> orgFlowList = null;
		if (joinOrgs != null && !joinOrgs.isEmpty()) {
			orgs = new ArrayList<>();
			orgs.add(currentOrg);
			orgFlowList = new ArrayList<>();
			orgFlowList.add(currentOrg.getOrgFlow());
			for (ResJointOrg jointOrg : joinOrgs) {
				//orgFlowList.add(jointOrg.getJointOrgFlow());
				SysOrg joOrg = orgBiz.readSysOrg(jointOrg.getJointOrgFlow());
				orgs.add(joOrg);
			}
		}
		//是否含有协同基地
		if (joinOrgs != null && joinOrgs.size() > 0) {
			model.addAttribute("hasJointOrg", "1");
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("orgs", orgs);
		return "jsres/hospital/docWork/workQuery";
	}

	/**
	 * 医师工作量查询查询条件页  助理全科
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/docWorkQueryAcc")
	public String docWorkQueryAcc(Model model) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		//model.addAttribute("org",org);
		//如果当前基地含有协同基地则展示基地查询项（包含当前基地和协同基地）
		List<SysOrg> orgs = null;
		List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
		List<String> orgFlowList = null;
		if (joinOrgs != null && !joinOrgs.isEmpty()) {
			orgs = new ArrayList<>();
			orgs.add(currentOrg);
			orgFlowList = new ArrayList<>();
			orgFlowList.add(currentOrg.getOrgFlow());
			for (ResJointOrg jointOrg : joinOrgs) {
				//orgFlowList.add(jointOrg.getJointOrgFlow());
				SysOrg joOrg = orgBiz.readSysOrg(jointOrg.getJointOrgFlow());
				orgs.add(joOrg);
			}
		}
		//是否含有协同基地
		if (joinOrgs != null && joinOrgs.size() > 0) {
			model.addAttribute("hasJointOrg", "1");
		}
		model.addAttribute("orgFlowList", orgFlowList);
		model.addAttribute("orgs", orgs);
		return "jsres/hospital/docWork/workQueryAcc";
	}


	/**
	 * 医师工作量查询(基地角色)
	 *
	 * @param trainingTypeId
	 * @param trainingSpeId
	 * @param sessionNumber
	 * @param orgFlow
	 * @param userName
	 * @param idNo
	 * @param graduationYear
	 * @param datas（存放人员类型）
	 * @param sort（存放排序方法）
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/doc/docWorking"})
	public String schDept(String trainingTypeId, String trainingSpeId, String sessionNumber, String orgFlow,
						  String userName, String idNo, String graduationYear, String[] datas, String sort,
						  Integer currentPage, HttpServletRequest request, Model model, String baseFlag) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		List<ResJointOrg> resJointOrgs = null;
		String userOrgFlow = "";
		if (currentUser != null) {
			userOrgFlow = currentUser.getOrgFlow();
		}
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		List<String> recTypeIds = new ArrayList<String>();
		for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
				recTypeIds.add(regType.getId());
			}
		}
		List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
		//是否有协同基地
		List<String> orgFlowList = new ArrayList<>();
		if (joinOrgs != null && !joinOrgs.isEmpty()) {
			model.addAttribute("hasJointOrg", "1");
			//筛选条件orgFlow是否为空
			if (StringUtil.isBlank(orgFlow)) {
				//orgFlowList添加当前基地Flow
				orgFlowList.add(currentOrg.getOrgFlow());
				//orgFlowList添加协同基地Flow
				for (ResJointOrg jointOrg : joinOrgs) {
					orgFlowList.add(jointOrg.getJointOrgFlow());
				}
			} else {
				//如果筛选条件orgFlow非空，orgFlowList添加orgFlow
				orgFlowList.add(orgFlow);
			}
		} else {
			//无协同基地则只需添加当前基地
			orgFlowList.add(currentOrg.getOrgFlow());
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("orgFlowList", orgFlowList);
		parMp.put("docTypeList", docTypeList);
		parMp.put("typeId", trainingTypeId);
		parMp.put("speId", trainingSpeId);
		/*//根据当前系统月份判断，若>9月份，则年级取当前。若<9月份，取当前年级-1。
		String m = DateUtil.getMonth().replace("-",""); //当前系统月份
		String dm = DateUtil.getYear()+"09";//指定9月份
		if(m.compareTo(dm)<0){
			int sessionNumber2 = Integer.parseInt(sessionNumber)-1;
			sessionNumber = sessionNumber2+"";
		}*/
		parMp.put("sessionNumber", sessionNumber);
		parMp.put("sort", sort);
		parMp.put("userName", userName);
		parMp.put("idNo", idNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("recTypeIds", recTypeIds);
		parMp.put("baseFlag", baseFlag);
		parMp.put("userOrgFlow", userOrgFlow);
//		if(StringUtil.isNotEmpty(userOrgFlow)){
//			resJointOrgs = jointOrgBiz.searchResJointByJointOrgFlow(userOrgFlow);
//			if(CollectionUtils.isNotEmpty(resJointOrgs)){
//				parMp.put("jointOrgFlowList",orgFlowList);
//				parMp.put("orgFlowList",null);
//			}
//		}
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> rltLst = resultBiz.docWorkingSearch(parMp);
//		if(null != rltLst && rltLst.size() > 0){
//			for(Map<String, Object> map : rltLst){
//				Integer reqSum = 0;//培训数据：要求数
//				Integer compSum = 0;//培训数据：完成数
//				Integer rotationSum = 0;//科室要求数
//				Integer lunzhuanSum = 0;//已轮转科室数
//				Integer chukeSum = 0;//出科科室数
//				parMp.clear();;
//				parMp.put("orgFlow", orgFlow);
//				parMp.put("userFlow", map.get("doctorFlow"));
//				List<Map<String,Object>> lst = resultBiz.docWorkingDetail(parMp);
//				if(null != lst){
//					rotationSum = lst.size();
//					if(lst.size() > 0){
//						for(Map<String, Object> mp : lst){
//							reqSum += Integer.valueOf(String.valueOf(mp.get("reqNum")));
//							compSum += Integer.valueOf(String.valueOf(mp.get("completeNum")));
//							if(Integer.valueOf(String.valueOf(mp.get("completeNum"))) > 0){
//								if(Integer.valueOf(String.valueOf(mp.get("evaluationNum"))) > 0){
//									chukeSum++;
//								}else{
//									lunzhuanSum++;
//								}
//							}
//						}
//					}
//				}
//				map.put("reqNum", reqSum);
//				map.put("completeNum", compSum);
//				map.put("rotationNum", rotationSum);
//				map.put("lunzhuanNum", lunzhuanSum);
//				map.put("chukeNum", chukeSum);
//			}
//		}
		model.addAttribute("rltLst", rltLst);
		return "jsres/hospital/docWork/workList";
	}

	@RequestMapping(value = {"/doc/export"})
	public void docExport(String trainingTypeId, String trainingSpeId, String sessionNumber, String orgFlow,
						  String userName, String idNo, String graduationYear, String[] datas, String sort,
						  Integer currentPage, HttpServletRequest request, Model model, String baseFlag, HttpServletResponse response) throws Exception {
		String fileName = "学生工作量统计.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		response.setContentType("application/octet-stream;charset=UTF-8");

		SysUser currentUser = GlobalContext.getCurrentUser();
		String userOrgFlow = "";
		if (currentUser != null) {
			userOrgFlow = currentUser.getOrgFlow();
		}
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<String> docTypeList = new ArrayList<String>();
		if (datas != null && datas.length > 0) {
			for (String s : datas) {
				docTypeList.add(s);
			}
		}
		List<String> recTypeIds = new ArrayList<String>();
		for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
				recTypeIds.add(regType.getId());
			}
		}
		List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByOrgFlow(currentUser.getOrgFlow());
		//是否有协同基地
		List<String> orgFlowList = new ArrayList<>();
		if (joinOrgs != null && !joinOrgs.isEmpty()) {
			model.addAttribute("hasJointOrg", "1");
			//筛选条件orgFlow是否为空
			if (StringUtil.isBlank(orgFlow)) {
				//orgFlowList添加当前基地Flow
				orgFlowList.add(currentOrg.getOrgFlow());
				//orgFlowList添加协同基地Flow
				for (ResJointOrg jointOrg : joinOrgs) {
					orgFlowList.add(jointOrg.getJointOrgFlow());
				}
			} else {
				//如果筛选条件orgFlow非空，orgFlowList添加orgFlow
				orgFlowList.add(orgFlow);
			}
		} else {
			//无协同基地则只需添加当前基地
			orgFlowList.add(currentOrg.getOrgFlow());
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("orgFlowList", orgFlowList);
		parMp.put("docTypeList", docTypeList);
		parMp.put("typeId", trainingTypeId);
		parMp.put("speId", trainingSpeId);
		parMp.put("sessionNumber", sessionNumber);
		parMp.put("sort", sort);
		parMp.put("userName", userName);
		parMp.put("idNo", idNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("recTypeIds", recTypeIds);
		parMp.put("baseFlag", baseFlag);
		parMp.put("userOrgFlow", userOrgFlow);
		List<Map<String, Object>> rltLst = resultBiz.docWorkingSearch(parMp);

		String[] titles = new String[8];
		titles[0] = "doctorName:姓名";
		titles[1] = "trainingSpeName:专业";
		titles[2] = "sessionNumber:年级";
		titles[3] = "reqNum,completeNum,auditNum:培训数据(要求数/完成数/审核数)";
		titles[4] = "cbl.%:完成(比例)";
		titles[5] = "abl.%:审核(比例)";
		titles[6] = "rotationNum,ylz,schNum:轮转科室(要求数/已轮转/已出科)";
		titles[7] = "rotationNum,afterNum:出科要求表(要求数/上传数)";
		ExcleUtile.exportSimpleExcleByObjs(titles, rltLst, response.getOutputStream());

	}

	/**
	 * 医师工作量详情查询
	 */
	@RequestMapping(value = {"/docWorkDetailSearch"})
	public String docWorkDetail(String userFlow, Model model) {
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("orgFlow", orgFlow);
		parMp.put("userFlow", userFlow);
		ResDoctor resDoctor = resDoctorBiz.findByFlow(userFlow);
		String rotation_flow = resDoctor.getRotationFlow();
		if (StringUtil.isNotBlank(rotation_flow)) {
			parMp.put("rotationFlow", rotation_flow);
			List<Map<String, Object>> rltLst = resultBiz.docWorkingDetail(parMp);
			model.addAttribute("rltLst", rltLst);
		}
		return "jsres/doctor/docWorkDetail";
	}

	public Map<String, List<SchProcessExt>> zuzhuangMap(List<SchProcessExt> list) {
		Map<String, List<SchProcessExt>> map = new HashMap<>();
		if (list != null && list.size() > 0) {
			for (SchProcessExt ext : list) {
				List<SchProcessExt> tempList = map.get(ext.getDeptFlow());
				if (tempList == null) {
					tempList = new ArrayList<>();
					map.put(ext.getDeptFlow(), tempList);
				}
				tempList.add(ext);
			}
		}
		return map;
	}

	public Map<String, Integer> getDeptMaxSize(Map<String, List<SchProcessExt>> map1,
											   Map<String, List<SchProcessExt>> map2,
											   Map<String, List<SchProcessExt>> map3) {
		Map<String, Integer> map = new HashMap<>();
		if (map1 != null && map1.size() > 0) {
			for (String key : map1.keySet()) {
				List<SchProcessExt> list = map1.get(key);
				if (list != null)
					map.put(key, list.size());
				else
					map.put(key, 0);
			}
		}
		if (map2 != null && map2.size() > 0) {
			for (String key : map2.keySet()) {
				List<SchProcessExt> list = map2.get(key);
				Integer count = map.get(key);
				int thisCount = 0;
				if (list != null)
					thisCount = list.size();
				if (count == null) {
					count = 0;
				}
				if (thisCount > count) {
					map.put(key, thisCount);
				}
			}
		}
		if (map3 != null && map3.size() > 0) {
			for (String key : map3.keySet()) {
				List<SchProcessExt> list = map3.get(key);
				Integer count = map.get(key);
				int thisCount = 0;
				if (list != null)
					thisCount = list.size();
				if (count == null) {
					count = 0;
				}
				if (thisCount > count) {
					map.put(key, thisCount);
				}
			}
		}
		return map;
	}

	/**
	 * 月报查询  住院医师
	 *
	 * @return
	 */
	@RequestMapping(value = {"/searchMonthlyReport"})
	public void searchMonthlyReport(String resultFlow, String schStartDate, String schStartDate1,
									HttpServletResponse response) throws Exception {
		logger.info("[JsResBaseManagerController.searchMonthlyReport start...");
		SysUser sysUser = GlobalContext.getCurrentUser();
		String orgFlow = sysUser.getOrgFlow();
//		String orgFlow="031f5fd06af3466c8bb9ea05d51b8d33";
		Map<String, Object> mapIn = new HashMap<>();
		mapIn.put("orgFlow", orgFlow);
		mapIn.put("schStartDate", schStartDate);
        mapIn.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<SchProcessExt> resultMIn = schMonthlyReportBiz.searchPlanAccessDoc(mapIn);
		Map<String, List<SchProcessExt>> mInDeptMap = zuzhuangMap(resultMIn);
		Map<String, Object> mapOut = new HashMap<>();
		mapOut.put("orgFlow", orgFlow);
		mapOut.put("schEndDate", schStartDate);
        mapOut.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<SchProcessExt> resultMOut = schMonthlyReportBiz.searchPlanAccessDoc(mapOut);
		Map<String, List<SchProcessExt>> mOutDeptMap = zuzhuangMap(resultMOut);
		Map<String, Integer> deptCountMap = getDeptMaxSize(mInDeptMap, mOutDeptMap, null);
		List<Map<String, String>> dataList = new ArrayList<>();

		SysDeptExample sysDeptExample = new SysDeptExample();
		SysDeptExample.Criteria sysDeptExampleCriteria = sysDeptExample.createCriteria();
		sysDeptExampleCriteria.andOrgFlowEqualTo(orgFlow);
        sysDeptExampleCriteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDept> sysDeptList = sysDeptMapper.selectByExample(sysDeptExample);
		Map<String, SysDept> sysDeptMap = sysDeptList.stream().collect(Collectors.toMap(vo -> vo.getDeptFlow(), vo -> vo, (vo1, vo2) -> vo1));
		SysOrg so = orgBiz.readSysOrg(orgFlow);

		if (deptCountMap != null && deptCountMap.size() > 0) {
			logger.info("[JsResBaseManagerController.searchMonthlyReport enter for1, size: {}", deptCountMap.size());
			for (String key : deptCountMap.keySet()) {
				Integer maxCount = deptCountMap.get(key);
				List<SchProcessExt> inList = mInDeptMap.get(key);
				List<SchProcessExt> outList = mOutDeptMap.get(key);
				if (maxCount != null && maxCount > 0) {
					for (int i = 0; i < maxCount; i++) {
						Map<String, String> data = new HashMap<>();
						SchProcessExt inExt = null;
						if (inList != null && inList.size() > 0 && inList.size() > i) {
							inExt = inList.get(i);
						}
						SchProcessExt outExt = null;
						if (outList != null && outList.size() > 0 && outList.size() > i) {
							outExt = outList.get(i);
						}
						String deptName = "";
						String inDocName = "";
						String inDate = "";
						String outDocName = "";
						String outTeaName = "";
						String outDate = "";
						if (inExt != null) {
							deptName = inExt.getDeptName();
							SysDept sysDept = sysDeptMap.getOrDefault(inExt.getDeptFlow(), new SysDept());
							String deptOrgFlow = sysDept.getOrgFlow();
							if (StringUtil.isNotBlank(deptOrgFlow)) {

								if (so != null && !so.getOrgFlow().equals(inExt.getSysUser().getOrgFlow())) {
									deptName += ("[" + so.getOrgName() + "]");
								}
							}
							inDocName = inExt.getSysUser().getUserName();
							inDate = inExt.getSchStartDate();
						}
						if (outExt != null) {
							deptName = outExt.getDeptName();
							SysDept sysDept = sysDeptMap.getOrDefault(outExt.getDeptFlow(), new SysDept());
							String deptOrgFlow = sysDept.getOrgFlow();
							if (StringUtil.isNotBlank(deptOrgFlow)) {
								if (so != null && !so.getOrgFlow().equals(outExt.getSysUser().getOrgFlow())) {
									deptName += ("[" + so.getOrgName() + "]");
								}
							}
							outDocName = outExt.getSysUser().getUserName();
							outTeaName = outExt.getTeacherUserName();
							outDate = outExt.getSchEndDate();
						}
						data.put("deptName", deptName);
						data.put("inDocName", inDocName);
						data.put("inDate", inDate);
						data.put("outDocName", outDocName);
						data.put("outTeaName", outTeaName);
						data.put("outDate", outDate);
						dataList.add(data);
					}
				}
			}
		}
//		System.err.println(JSON.toJSONString(mInDeptMap));
//		System.err.println(JSON.toJSONString(mOutDeptMap));
//		System.err.println(JSON.toJSONString(deptCountMap));
//		System.err.println("dataList:"+JSON.toJSONString(dataList));
		Map<String, Object> mapAlreadyIn = new HashMap<>();
		mapAlreadyIn.put("orgFlow", orgFlow);
		mapAlreadyIn.put("schStartDate", schStartDate1);
		List<SchProcessExt> resultAlreadyIn = schMonthlyReportBiz.searchAlreadyInDoc(mapAlreadyIn);
		//查询上月已出科和未出科的学员
		Map<String, Object> mapInAndOutDoc = new HashMap<>();
		mapInAndOutDoc.put("orgFlow", orgFlow);
		mapInAndOutDoc.put("schEndDate", schStartDate1);
        mapInAndOutDoc.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.DoctorTrainingSpe.getId());
		List<SchProcessExt> resultInAndOutDoc = schMonthlyReportBiz.searchInAndOutDoc(mapInAndOutDoc);
		//上月已出科学员
		List<SchProcessExt> resultLastMonthOutDoc = new ArrayList<>();
		//上月未出科学员
		List<SchProcessExt> resultLastMonthNotOutDoc = new ArrayList<>();
		Map<String, SchArrangeResult> arrangeResultMap = new HashMap<>();
		Map<String, SchRotationGroup> rotationGroupFlowToEntityMap = new HashMap<>();
		Map<String, SchRotationDept> keyToRotationDeptMap = new HashMap<>();
		if (resultInAndOutDoc != null && !resultInAndOutDoc.isEmpty()) {
			for (int i = 1; i < resultInAndOutDoc.size(); i++) {
				if (resultInAndOutDoc.get(i).getResRec() == null &&
						resultInAndOutDoc.get(i - 1).getSysUser().getUserName()
								.equals(resultInAndOutDoc.get(i).getSysUser().getUserName())) {
					resultInAndOutDoc.remove(i);
				}

			}

			List<String> resultFlowList = resultInAndOutDoc.stream().map(vo -> vo.getSchResultFlow()).collect(Collectors.toList());
			SchArrangeResultExample schArrangeResultExample = new SchArrangeResultExample();
            schArrangeResultExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andResultFlowIn(resultFlowList);
			List<SchArrangeResult> schArrangeResultList = schArrangeResultBiz.readSchArrangeResultByExample(schArrangeResultExample);
			arrangeResultMap.putAll(schArrangeResultList.stream().collect(Collectors.toMap(vo -> vo.getResultFlow(), Function.identity(), (vo1, vo2) -> vo1)));
			List<String> standardGroupFlowList = schArrangeResultList.stream().map(vo -> vo.getStandardGroupFlow()).collect(Collectors.toList());
			SchRotationGroupExample schRotationGroupExample = new SchRotationGroupExample();
            schRotationGroupExample.createCriteria().andGroupFlowIn(standardGroupFlowList).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SchRotationGroup> schRotationGroupList = rotationGroupBiz.readSchRotationGroupByExample(schRotationGroupExample);
			rotationGroupFlowToEntityMap.putAll(schRotationGroupList.stream().collect(Collectors.toMap(vo -> vo.getGroupFlow(), Function.identity(), (vo1, vo2) -> vo1)));
			logger.info("[JsResBaseManagerController.searchMonthlyReport enter for2, size: {}", resultInAndOutDoc.size());

			List<String> resultFlow2List = new ArrayList<>();
			resultInAndOutDoc.forEach(inAndOutDoc -> {
				resultFlow2List.add(inAndOutDoc.getSchResultFlow());
			});
			List<SchArrangeResult> resultList = new ArrayList<>();
			for (String resultFlow2 : resultFlow2List) {
				SchArrangeResult result = arrangeResultMap.get(resultFlow2);
				if(result != null && rotationGroupFlowToEntityMap.get(result.getStandardGroupFlow()) != null) {
					resultList.add(result);
				}
			}
			List<SchRotationDept> schRotationDeptList = readStandardRotationDeptList(resultList);
			keyToRotationDeptMap.putAll(schRotationDeptList.stream().collect(Collectors.toMap(vo -> vo.getRotationFlow() + vo.getStandardDeptId() + vo.getGroupFlow(), Function.identity(), (vo1, vo2) -> vo1)));
			Map<String, List<SchArrangeResult>> keyToResultMap = resultList.stream().collect(Collectors.groupingBy(vo -> vo.getRotationFlow() + vo.getStandardDeptId() + vo.getStandardGroupFlow()));
			List<String> recordFlowList = new ArrayList<>();
			List<String> doctorFlowList = new ArrayList<>();
			keyToRotationDeptMap.forEach((key, value) -> {
				List<SchArrangeResult> schArrangeResult = keyToResultMap.get(key);
				if(schArrangeResult != null && schArrangeResult.size() > 0) {
					for (SchArrangeResult result2 : schArrangeResult) {
						recordFlowList.add(value.getRecordFlow());
						doctorFlowList.add(result2.getDoctorFlow());
					}
				}
			});
			List<ResSchProcessExpress> resSchProcessExpresseList = expressBiz.queryResRecList(recordFlowList, doctorFlowList, AfterRecTypeEnum.AfterSummary.getId());
			Map<String, ResSchProcessExpress> keyToProcessExpressMap = resSchProcessExpresseList.stream().collect(Collectors.toMap(vo -> vo.getSchRotationDeptFlow() + vo.getOperUserFlow(), Function.identity(), (vo1, vo2) -> vo1));

			for (int i = 0; i < resultInAndOutDoc.size(); i++) {
				resultFlow = resultInAndOutDoc.get(i).getSchResultFlow();
				List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
				SchArrangeResult result = arrangeResultMap.get(resultFlow);
				SchRotationDept schRotationDept = null;
				if (result != null && rotationGroupFlowToEntityMap.get(result.getStandardGroupFlow()) != null) {
					schRotationDept = keyToRotationDeptMap.get(result.getRotationFlow() + result.getStandardDeptId() + result.getStandardGroupFlow());
				}
				if (result != null && StringUtil.isNotBlank(result.getDoctorFlow()) && schRotationDept != null && StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
//					ResRec rec =resRecBiz.queryResRec(schRotationDept.getRecordFlow(),result.getDoctorFlow(), AfterRecTypeEnum.AfterSummary.getId());
					ResSchProcessExpress rec = keyToProcessExpressMap.get(schRotationDept.getRecordFlow() + result.getDoctorFlow());
					String content = null == rec ? "" : rec.getRecContent();
					if (StringUtil.isNotBlank(content)) {
						Document doc = DocumentHelper.parseText(content);
						Element root = doc.getRootElement();
						List<Element> imageEles = root.elements();
						if (imageEles != null && imageEles.size() > 0) {
							for (Element image : imageEles) {
								Map<String, Object> recContent = new HashMap<String, Object>();
								String imageFlow = image.attributeValue("imageFlow");
								List<Element> elements = image.elements();
								for (Element attr : elements) {
									String attrName = attr.getName();
									String attrValue = attr.getText();
									recContent.put(attrName, attrValue);
								}
								imagelist.add(recContent);
							}
						}
					}
				}
				if (imagelist.size() > 0) {
					if (resultInAndOutDoc.get(i).getResRec() != null && resultInAndOutDoc.get(i).getResRec().getAuditStatusId() != "") {
						resultLastMonthOutDoc.add(resultInAndOutDoc.get(i));
					} else {
						resultInAndOutDoc.get(i).setSchFlag("带教未审核");
						resultLastMonthNotOutDoc.add(resultInAndOutDoc.get(i));
					}
				} else {
					resultInAndOutDoc.get(i).setSchFlag("学员未上传");
					resultLastMonthNotOutDoc.add(resultInAndOutDoc.get(i));
				}
			}
		}
		Map<String, List<SchProcessExt>> alreadyInDeptMap = zuzhuangMap(resultAlreadyIn);
		Map<String, List<SchProcessExt>> lastMonthOutDeptMap = zuzhuangMap(resultLastMonthOutDoc);
		Map<String, List<SchProcessExt>> lastMonthNotOutDeptMap = zuzhuangMap(resultLastMonthNotOutDoc);
		Map<String, Integer> deptCountMap2 = getDeptMaxSize(alreadyInDeptMap, lastMonthOutDeptMap, lastMonthNotOutDeptMap);
		List<Map<String, String>> dataList2 = new ArrayList<>();
		if (deptCountMap2 != null && deptCountMap2.size() > 0) {
			logger.info("[JsResBaseManagerController.searchMonthlyReport enter for3 size: {}", deptCountMap2.size());
			// 这个for去不掉了，改成多线程处理
			List<CompletableFuture<Void>> futures = new ArrayList<>();
			for (String key : deptCountMap2.keySet()) {
				CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
					exportMonthlyReportAsync(key, deptCountMap2, alreadyInDeptMap, lastMonthOutDeptMap, lastMonthNotOutDeptMap,arrangeResultMap, rotationGroupFlowToEntityMap, keyToRotationDeptMap, sysDeptMap, so, dataList2);
				});
				futures.add(future);
			}
			CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get();
		}
		String[] arrFileName = schStartDate.split("-");
		String[] arrFileName2 = schStartDate1.split("-");
		String fileName = arrFileName[0] + "年" + arrFileName[1] + "月住院医师规范化培训工作报告.xls";
		String sheet1Title = arrFileName[0] + "年" + arrFileName[1] + "月住院医师规范化培训工作计划报告";
		String sheet2Title = arrFileName2[0] + "年" + arrFileName2[1] + "月住院医师规范化培训工作小结报告";
		logger.info("[JsResBaseManagerController.searchMonthlyReport] export start...");
		schMonthlyReportBiz.export4MonthlyReport(fileName, sheet1Title, sheet2Title, dataList, dataList2, response);
		logger.info("[JsResBaseManagerController.searchMonthlyReport] end...");
	}

	private void exportMonthlyReportAsync(String key, Map<String, Integer> deptCountMap2, Map<String, List<SchProcessExt>> alreadyInDeptMap, Map<String, List<SchProcessExt>> lastMonthOutDeptMap, Map<String, List<SchProcessExt>> lastMonthNotOutDeptMap, Map<String, SchArrangeResult> arrangeResultMap, Map<String, SchRotationGroup> rotationGroupFlowToEntityMap, Map<String, SchRotationDept> keyToRotationDeptMap, Map<String, SysDept> sysDeptMap, SysOrg so, List<Map<String, String>> dataList2) {
		Integer maxCount = deptCountMap2.get(key);
		List<SchProcessExt> inList = alreadyInDeptMap.get(key);
		List<SchProcessExt> outList = lastMonthOutDeptMap.get(key);
		List<SchProcessExt> notOutList = lastMonthNotOutDeptMap.get(key);

		List<String> userFlowList = new ArrayList<>();
		if (inList != null && inList.size() > 0) {
			for (SchProcessExt schProcessExt : inList) {
				if (!userFlowList.contains(schProcessExt.getSysUser().getUserFlow())) {
					userFlowList.add(schProcessExt.getSysUser().getUserFlow());
				}
			}
		}
		if (notOutList != null && notOutList.size() > 0) {
			for (SchProcessExt schProcessExt : notOutList) {
				if (!userFlowList.contains(schProcessExt.getSysUser().getUserFlow())) {
					userFlowList.add(schProcessExt.getSysUser().getUserFlow());
				}
			}
		}

		List<ResDoctor> resDoctorList = new ArrayList<>();
		if (userFlowList.size() > 0) {
			ResDoctorExample resDoctorExample = new ResDoctorExample();
            resDoctorExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowIn(userFlowList);
			resDoctorList = resDoctorBiz.readDoctorByExample(resDoctorExample);
		}
		Map<String, ResDoctor> doctorFlowToEntityMap = resDoctorList.stream().collect(Collectors.toMap(vo -> vo.getDoctorFlow(), Function.identity(), (vo1, vo2) -> vo1));

		List<String> doctorFlowList = new ArrayList<>();
		for (ResDoctor resDoctor : resDoctorList) {
			if (!doctorFlowList.contains(resDoctor.getDoctorFlow())) {
				doctorFlowList.add(resDoctor.getDoctorFlow());
			}
		}
		List<SchArrangeResult> schArrangeResultList = new ArrayList<>();
		List<SysUser> sysUserList = new ArrayList<>();
		if (doctorFlowList != null && doctorFlowList.size() > 0) {
			SchArrangeResultExample schArrangeResultExample = new SchArrangeResultExample();
            schArrangeResultExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andDoctorFlowIn(doctorFlowList);
			schArrangeResultList = schArrangeResultBiz.readSchArrangeResultByExample(schArrangeResultExample);

			SysUserExample sysUserExample = new SysUserExample();
            sysUserExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
					.andUserFlowIn(doctorFlowList);
			sysUserList = userBiz.readSysUserByExample(sysUserExample);
		}
		Map<String, SysUser> userFlowToEntityMap = sysUserList.stream().collect(Collectors.toMap(vo -> vo.getUserFlow(), Function.identity(), (vo1, vo2) -> vo1));

		Map<String, List<SchArrangeResult>> doctorFlowToArrangeResultMap = schArrangeResultList.stream().collect(Collectors.groupingBy(vo -> vo.getDoctorFlow()));
		for (Map.Entry<String, List<SchArrangeResult>> entry : doctorFlowToArrangeResultMap.entrySet()) {
			List<SchArrangeResult> value = entry.getValue();
			value = value.stream().sorted((vo1, vo2) -> vo1.getSchStartDate().compareTo(vo2.getSchStartDate())).collect(Collectors.toList());
			entry.setValue(value);
		}

		if (maxCount != null && maxCount > 0) {
			Map<String, Map<String, String>> finishPer2List = new HashMap<>();
			List<ResDoctor> allDoctorList = new ArrayList<>();
			Map<String, List<SchArrangeResult>> arrResultListMap = new HashMap<>();
			for(int i = 0; i < maxCount; i++) {
				SchProcessExt alreadyOutExt = null;
				if (outList != null && outList.size() > 0 && outList.size() > i) {
					alreadyOutExt = outList.get(i);
				}
				SchProcessExt notOutExt = null;
				if (notOutList != null && notOutList.size() > 0 && notOutList.size() > i) {
					notOutExt = notOutList.get(i);
				}
				if(alreadyOutExt != null && doctorFlowToEntityMap.get(alreadyOutExt.getSysUser().getUserFlow()) != null) {
					allDoctorList.add(doctorFlowToEntityMap.get(alreadyOutExt.getSysUser().getUserFlow()));
				}
				if(notOutExt != null && doctorFlowToEntityMap.get(notOutExt.getSysUser().getUserFlow()) != null) {
					allDoctorList.add(doctorFlowToEntityMap.get(notOutExt.getSysUser().getUserFlow()));
				}
				for (ResDoctor resDoctor : allDoctorList) {
					arrResultListMap.put(resDoctor.getDoctorFlow(), doctorFlowToArrangeResultMap.get(resDoctor.getDoctorFlow()));
				}
			}

			if(CollectionUtils.isNotEmpty(allDoctorList)) {
				finishPer2List = resRecBiz.getFinishPer2List(arrResultListMap, allDoctorList, userFlowToEntityMap, arrangeResultMap, rotationGroupFlowToEntityMap, keyToRotationDeptMap);
			}

			for (int i = 0; i < maxCount; i++) {
				Map<String, String> data = new HashMap<>();
				SchProcessExt alreadyInExt = null;
				if (inList != null && inList.size() > 0 && inList.size() > i) {
					alreadyInExt = inList.get(i);
				}
				SchProcessExt alreadyOutExt = null;
				if (outList != null && outList.size() > 0 && outList.size() > i) {
					alreadyOutExt = outList.get(i);
				}
				SchProcessExt notOutExt = null;
				if (notOutList != null && notOutList.size() > 0 && notOutList.size() > i) {
					notOutExt = notOutList.get(i);
				}
				String deptName = "";
				String inDocName = "";
				String inTeacherName = "";
				String inDate = "";
				String outDocName = "";
				String outTeaName = "";
				String outSchPer = "";
				String outDate = "";
				String notOutDocName = "";
				String notOutTeaName = "";
				String notOutSchPer = "";
				String notOutDate = "";
				String reason = "";
				if (alreadyInExt != null) {
					deptName = alreadyInExt.getDeptName();
					SysDept sysDept = sysDeptMap.getOrDefault(alreadyInExt.getDeptFlow(), new SysDept());
					String deptOrgFlow = sysDept.getOrgFlow();
					if (StringUtil.isNotBlank(deptOrgFlow)) {
						if (so != null && !so.getOrgFlow().equals(alreadyInExt.getSysUser().getOrgFlow())) {
							deptName += ("[" + so.getOrgName() + "]");
						}
					}
					inDocName = alreadyInExt.getSysUser().getUserName();
					inTeacherName = alreadyInExt.getTeacherUserName();
					inDate = alreadyInExt.getSchStartDate();
				}
				if (alreadyOutExt != null) {
					String schPer1 = "";
					if (alreadyOutExt.getSchPer() != null) {
						schPer1 = alreadyOutExt.getSchPer() + "";
					}
					deptName = alreadyOutExt.getDeptName();
					SysDept sysDept = sysDeptMap.getOrDefault(alreadyOutExt.getDeptFlow(), new SysDept());
					String deptOrgFlow = sysDept.getOrgFlow();
					if (StringUtil.isNotBlank(deptOrgFlow)) {
						if (so != null && !so.getOrgFlow().equals(alreadyOutExt.getSysUser().getOrgFlow())) {
							deptName += ("[" + so.getOrgName() + "]");
						}
					}
					outDocName = alreadyOutExt.getSysUser().getUserName();
					outTeaName = alreadyOutExt.getTeacherUserName();
					outSchPer = schPer1;
					outDate = alreadyOutExt.getSchEndDate();
				}
				if (notOutExt != null) {
					String schPer2 = "";
					if (notOutExt.getSchPer() != null) {
						schPer2 = notOutExt.getSchPer() + "";
					}
					deptName = notOutExt.getDeptName();
					SysDept sysDept = sysDeptMap.getOrDefault(notOutExt.getDeptFlow(), new SysDept());
					String deptOrgFlow = sysDept.getOrgFlow();
					if (StringUtil.isNotBlank(deptOrgFlow)) {
						if (so != null && !so.getOrgFlow().equals(notOutExt.getSysUser().getOrgFlow())) {
							deptName += ("[" + so.getOrgName() + "]");
						}
					}
					notOutDocName = notOutExt.getSysUser().getUserName();
					notOutTeaName = notOutExt.getTeacherUserName();
					notOutSchPer = schPer2;
					notOutDate = notOutExt.getSchEndDate();
					reason = notOutExt.getSchFlag();
				}
				if (null != alreadyOutExt) {
					ResDoctor doctor = doctorFlowToEntityMap.get(alreadyOutExt.getSysUser().getUserFlow());
					//计算登记进度
					if (null != doctor) {
						Map<String, String> alreadyOutPerMap = finishPer2List.get(doctor.getDoctorFlow() + alreadyOutExt.getSchResultFlow());
						if (alreadyOutPerMap == null) {
							System.out.println("================" + doctor.getDoctorFlow());
						} else {
							outSchPer = alreadyOutPerMap.get(alreadyOutExt.getSchResultFlow()) + "%";
						}
					}
				}
				if (null != notOutExt) {
					ResDoctor doctor2 = doctorFlowToEntityMap.get(notOutExt.getSysUser().getUserFlow());
					//计算登记进度
					if (null != doctor2) {
						Map<String, String> alreadyNotOutPerMap = finishPer2List.get(doctor2.getDoctorFlow() + notOutExt.getSchResultFlow());
						if (alreadyNotOutPerMap == null) {
							System.out.println("================" + doctor2.getDoctorFlow());
						} else {
							notOutSchPer = alreadyNotOutPerMap.get(notOutExt.getSchResultFlow()) + "%";
						}
					}
				}
				data.put("deptName", deptName);
				data.put("inDocName", inDocName);
				data.put("inTeacherName", inTeacherName);
				data.put("inDate", inDate);
				data.put("outDocName", outDocName);
				data.put("outTeaName", outTeaName);
				data.put("outSchPer", outSchPer);
				data.put("outDate", outDate);
				data.put("notOutDocName", notOutDocName);
				data.put("notOutTeaName", notOutTeaName);
				data.put("notOutSchPer", notOutSchPer);
				data.put("notOutDate", notOutDate);
				data.put("reason", reason);
				dataList2.add(data);
			}
		}
	}


	/**
	 * 月报查询  助理全科
	 *
	 * @return
	 */
	@RequestMapping(value = {"/searchMonthlyReportAcc"})
	public void searchMonthlyReportAcc(String resultFlow, String schStartDate, String schStartDate1,
									   HttpServletResponse response) throws Exception {
		SysUser sysUser = GlobalContext.getCurrentUser();
		String orgFlow = sysUser.getOrgFlow();
//		String orgFlow="031f5fd06af3466c8bb9ea05d51b8d33";
		Map<String, Object> mapIn = new HashMap<>();
		mapIn.put("orgFlow", orgFlow);
		mapIn.put("schStartDate", schStartDate);
        mapIn.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());
		List<SchProcessExt> resultMIn = schMonthlyReportBiz.searchPlanAccessDoc(mapIn);
		Map<String, List<SchProcessExt>> mInDeptMap = zuzhuangMap(resultMIn);
		Map<String, Object> mapOut = new HashMap<>();
		mapOut.put("orgFlow", orgFlow);
		mapOut.put("schEndDate", schStartDate);
        mapOut.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());
		List<SchProcessExt> resultMOut = schMonthlyReportBiz.searchPlanAccessDoc(mapOut);
		Map<String, List<SchProcessExt>> mOutDeptMap = zuzhuangMap(resultMOut);
		Map<String, Integer> deptCountMap = getDeptMaxSize(mInDeptMap, mOutDeptMap, null);
		List<Map<String, String>> dataList = new ArrayList<>();
		if (deptCountMap != null && deptCountMap.size() > 0) {
			for (String key : deptCountMap.keySet()) {
				Integer maxCount = deptCountMap.get(key);
				List<SchProcessExt> inList = mInDeptMap.get(key);
				List<SchProcessExt> outList = mOutDeptMap.get(key);
				if (maxCount != null && maxCount > 0) {
					for (int i = 0; i < maxCount; i++) {
						Map<String, String> data = new HashMap<>();
						SchProcessExt inExt = null;
						if (inList != null && inList.size() > 0 && inList.size() > i) {
							inExt = inList.get(i);
						}
						SchProcessExt outExt = null;
						if (outList != null && outList.size() > 0 && outList.size() > i) {
							outExt = outList.get(i);
						}
						String deptName = "";
						String inDocName = "";
						String inDate = "";
						String outDocName = "";
						String outTeaName = "";
						String outDate = "";
						if (inExt != null) {
							deptName = inExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(inExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if (StringUtil.isNotBlank(deptOrgFlow)) {
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if (so != null && !so.getOrgFlow().equals(inExt.getSysUser().getOrgFlow())) {
									deptName += ("[" + so.getOrgName() + "]");
								}
							}
							inDocName = inExt.getSysUser().getUserName();
							inDate = inExt.getSchStartDate();
						}
						if (outExt != null) {
							deptName = outExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(outExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if (StringUtil.isNotBlank(deptOrgFlow)) {
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if (so != null && !so.getOrgFlow().equals(outExt.getSysUser().getOrgFlow())) {
									deptName += ("[" + so.getOrgName() + "]");
								}
							}
							outDocName = outExt.getSysUser().getUserName();
							outTeaName = outExt.getTeacherUserName();
							outDate = outExt.getSchEndDate();
						}
						data.put("deptName", deptName);
						data.put("inDocName", inDocName);
						data.put("inDate", inDate);
						data.put("outDocName", outDocName);
						data.put("outTeaName", outTeaName);
						data.put("outDate", outDate);
						dataList.add(data);
					}
				}
			}
		}
//		System.err.println(JSON.toJSONString(mInDeptMap));
//		System.err.println(JSON.toJSONString(mOutDeptMap));
//		System.err.println(JSON.toJSONString(deptCountMap));
//		System.err.println("dataList:"+JSON.toJSONString(dataList));
		Map<String, Object> mapAlreadyIn = new HashMap<>();
		mapAlreadyIn.put("orgFlow", orgFlow);
		mapAlreadyIn.put("schStartDate", schStartDate1);
		List<SchProcessExt> resultAlreadyIn = schMonthlyReportBiz.searchAlreadyInDoc(mapAlreadyIn);
		//查询上月已出科和未出科的学员
		Map<String, Object> mapInAndOutDoc = new HashMap<>();
		mapInAndOutDoc.put("orgFlow", orgFlow);
		mapInAndOutDoc.put("schEndDate", schStartDate1);
        mapInAndOutDoc.put("trainingTypeId", com.pinde.core.common.enums.TrainCategoryEnum.AssiGeneral.getId());
		List<SchProcessExt> resultInAndOutDoc = schMonthlyReportBiz.searchInAndOutDoc(mapInAndOutDoc);
		//上月已出科学员
		List<SchProcessExt> resultLastMonthOutDoc = new ArrayList<>();
		//上月未出科学员
		List<SchProcessExt> resultLastMonthNotOutDoc = new ArrayList<>();
		if (resultInAndOutDoc != null && !resultInAndOutDoc.isEmpty()) {
			for (int i = 1; i < resultInAndOutDoc.size(); i++) {
				if (resultInAndOutDoc.get(i).getResRec() == null &&
						resultInAndOutDoc.get(i - 1).getSysUser().getUserName()
								.equals(resultInAndOutDoc.get(i).getSysUser().getUserName())) {
					resultInAndOutDoc.remove(i);
				}

			}
			for (int i = 0; i < resultInAndOutDoc.size(); i++) {
				resultFlow = resultInAndOutDoc.get(i).getSchResultFlow();
				List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
				SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
				SchRotationDept schRotationDept = readStandardRotationDept(resultFlow);
				if (result != null && StringUtil.isNotBlank(result.getDoctorFlow()) && schRotationDept != null && StringUtil.isNotBlank(schRotationDept.getRecordFlow())) {
//					ResRec rec =resRecBiz.queryResRec(schRotationDept.getRecordFlow(),result.getDoctorFlow(), AfterRecTypeEnum.AfterSummary.getId());
					ResSchProcessExpress rec = expressBiz.queryResRec(schRotationDept.getRecordFlow(), result.getDoctorFlow(), AfterRecTypeEnum.AfterSummary.getId());
					String content = null == rec ? "" : rec.getRecContent();
					if (StringUtil.isNotBlank(content)) {
						Document doc = DocumentHelper.parseText(content);
						Element root = doc.getRootElement();
						List<Element> imageEles = root.elements();
						if (imageEles != null && imageEles.size() > 0) {
							for (Element image : imageEles) {
								Map<String, Object> recContent = new HashMap<String, Object>();
								String imageFlow = image.attributeValue("imageFlow");
								List<Element> elements = image.elements();
								for (Element attr : elements) {
									String attrName = attr.getName();
									String attrValue = attr.getText();
									recContent.put(attrName, attrValue);
								}
								imagelist.add(recContent);
							}
						}
					}
				}
				if (imagelist.size() > 0) {
					if (resultInAndOutDoc.get(i).getResRec() != null && resultInAndOutDoc.get(i).getResRec().getAuditStatusId() != "") {
						resultLastMonthOutDoc.add(resultInAndOutDoc.get(i));
					} else {
						resultInAndOutDoc.get(i).setSchFlag("带教未审核");
						resultLastMonthNotOutDoc.add(resultInAndOutDoc.get(i));
					}
				} else {
					resultInAndOutDoc.get(i).setSchFlag("学员未上传");
					resultLastMonthNotOutDoc.add(resultInAndOutDoc.get(i));
				}
			}
		}
		Map<String, List<SchProcessExt>> alreadyInDeptMap = zuzhuangMap(resultAlreadyIn);
		Map<String, List<SchProcessExt>> lastMonthOutDeptMap = zuzhuangMap(resultLastMonthOutDoc);
		Map<String, List<SchProcessExt>> lastMonthNotOutDeptMap = zuzhuangMap(resultLastMonthNotOutDoc);
		Map<String, Integer> deptCountMap2 = getDeptMaxSize(alreadyInDeptMap, lastMonthOutDeptMap, lastMonthNotOutDeptMap);
		List<Map<String, String>> dataList2 = new ArrayList<>();
		if (deptCountMap2 != null && deptCountMap2.size() > 0) {
			for (String key : deptCountMap2.keySet()) {
				Integer maxCount = deptCountMap2.get(key);
				List<SchProcessExt> inList = alreadyInDeptMap.get(key);
				List<SchProcessExt> outList = lastMonthOutDeptMap.get(key);
				List<SchProcessExt> notOutList = lastMonthNotOutDeptMap.get(key);
				if (maxCount != null && maxCount > 0) {
					for (int i = 0; i < maxCount; i++) {
						Map<String, String> data = new HashMap<>();
						SchProcessExt alreadyInExt = null;
						if (inList != null && inList.size() > 0 && inList.size() > i) {
							alreadyInExt = inList.get(i);
						}
						SchProcessExt alreadyOutExt = null;
						if (outList != null && outList.size() > 0 && outList.size() > i) {
							alreadyOutExt = outList.get(i);
						}
						SchProcessExt notOutExt = null;
						if (notOutList != null && notOutList.size() > 0 && notOutList.size() > i) {
							notOutExt = notOutList.get(i);
						}
						String deptName = "";
						String inDocName = "";
						String inTeacherName = "";
						String inDate = "";
						String outDocName = "";
						String outTeaName = "";
						String outSchPer = "";
						String outDate = "";
						String notOutDocName = "";
						String notOutTeaName = "";
						String notOutSchPer = "";
						String notOutDate = "";
						String reason = "";
						if (alreadyInExt != null) {
							deptName = alreadyInExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(alreadyInExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if (StringUtil.isNotBlank(deptOrgFlow)) {
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if (so != null && !so.getOrgFlow().equals(alreadyInExt.getSysUser().getOrgFlow())) {
									deptName += ("[" + so.getOrgName() + "]");
								}
							}
							inDocName = alreadyInExt.getSysUser().getUserName();
							inTeacherName = alreadyInExt.getTeacherUserName();
							inDate = alreadyInExt.getSchStartDate();
						}
						if (alreadyOutExt != null) {
							String schPer1 = "";
							if (alreadyOutExt.getSchPer() != null) {
								schPer1 = alreadyOutExt.getSchPer() + "";
							}
							deptName = alreadyOutExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(alreadyOutExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if (StringUtil.isNotBlank(deptOrgFlow)) {
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if (so != null && !so.getOrgFlow().equals(alreadyOutExt.getSysUser().getOrgFlow())) {
									deptName += ("[" + so.getOrgName() + "]");
								}
							}
							outDocName = alreadyOutExt.getSysUser().getUserName();
							outTeaName = alreadyOutExt.getTeacherUserName();
							outSchPer = schPer1;
							outDate = alreadyOutExt.getSchEndDate();
						}
						if (notOutExt != null) {
							String schPer2 = "";
							if (notOutExt.getSchPer() != null) {
								schPer2 = notOutExt.getSchPer() + "";
							}
							deptName = notOutExt.getDeptName();
							SysDept sysDept = sysDeptMapper.selectByPrimaryKey(notOutExt.getDeptFlow());
							String deptOrgFlow = sysDept.getOrgFlow();
							if (StringUtil.isNotBlank(deptOrgFlow)) {
								SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
								if (so != null && !so.getOrgFlow().equals(notOutExt.getSysUser().getOrgFlow())) {
									deptName += ("[" + so.getOrgName() + "]");
								}
							}
							notOutDocName = notOutExt.getSysUser().getUserName();
							notOutTeaName = notOutExt.getTeacherUserName();
							notOutSchPer = schPer2;
							notOutDate = notOutExt.getSchEndDate();
							reason = notOutExt.getSchFlag();
						}
						if (null != alreadyOutExt) {
							ResDoctor doctor = resDoctorBiz.readDoctor(alreadyOutExt.getSysUser().getUserFlow());
							//计算登记进度
							if (null != doctor) {
								List<SchArrangeResult> results = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctor.getDoctorFlow());
								Map<String, String> alreadyOutPerMap = resRecBiz.getFinishPer(results, doctor.getDoctorFlow());
								if (alreadyOutPerMap == null) {
									System.out.println("================" + doctor.getDoctorFlow());
								} else {
									outSchPer = alreadyOutPerMap.get(alreadyOutExt.getSchResultFlow()) + "%";
								}
							}
						}
						if (null != notOutExt) {
							ResDoctor doctor2 = resDoctorBiz.readDoctor(notOutExt.getSysUser().getUserFlow());
							//计算登记进度
							if (null != doctor2) {
								List<SchArrangeResult> results2 = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctor2.getDoctorFlow());
								Map<String, String> alreadyNotOutPerMap = resRecBiz.getFinishPer(results2, doctor2.getDoctorFlow());
								if (alreadyNotOutPerMap == null) {
									System.out.println("================" + doctor2.getDoctorFlow());
								} else {
									notOutSchPer = alreadyNotOutPerMap.get(notOutExt.getSchResultFlow()) + "%";
								}
							}
						}
						data.put("deptName", deptName);
						data.put("inDocName", inDocName);
						data.put("inTeacherName", inTeacherName);
						data.put("inDate", inDate);
						data.put("outDocName", outDocName);
						data.put("outTeaName", outTeaName);
						data.put("outSchPer", outSchPer);
						data.put("outDate", outDate);
						data.put("notOutDocName", notOutDocName);
						data.put("notOutTeaName", notOutTeaName);
						data.put("notOutSchPer", notOutSchPer);
						data.put("notOutDate", notOutDate);
						data.put("reason", reason);
						dataList2.add(data);
					}
				}
			}
		}
		String[] arrFileName = schStartDate.split("-");
		String[] arrFileName2 = schStartDate1.split("-");
		String fileName = arrFileName[0] + "年" + arrFileName[1] + "月住院医师规范化培训工作报告.xls";
		String sheet1Title = arrFileName[0] + "年" + arrFileName[1] + "月住院医师规范化培训工作计划报告";
		String sheet2Title = arrFileName2[0] + "年" + arrFileName2[1] + "月住院医师规范化培训工作小结报告";
		schMonthlyReportBiz.export4MonthlyReport(fileName, sheet1Title, sheet2Title, dataList, dataList2, response);
	}

	private SchRotationDept readStandardRotationDept(SchArrangeResult result) {
		SchRotationDept rotationDept = null;
		if (result != null) {
			String rotationFlow = result.getRotationFlow();
			String standardDeptId = result.getStandardDeptId();
			if (StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(standardDeptId)) {
				SchRotationDeptExample example = new SchRotationDeptExample();
                SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
						.andRotationFlowEqualTo(rotationFlow)
						.andStandardDeptIdEqualTo(standardDeptId)
						.andOrgFlowIsNull()
						.andGroupFlowEqualTo(result.getStandardGroupFlow());
				List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExampleWithBLOBs(example);
				if (rotationDeptList != null && rotationDeptList.size() > 0) {
					rotationDept = rotationDeptList.get(0);
				}
			}
		}
		return rotationDept;
	}

	private List<SchRotationDept> readStandardRotationDeptList(List<SchArrangeResult> resultList) {
		if(org.apache.commons.collections4.CollectionUtils.isEmpty(resultList)) {
			return Collections.emptyList();
		}

		List<String> rotationFlowList = new ArrayList<>();
		List<String> standardDeptIdList = new ArrayList<>();
		List<String> groupFlowList = new ArrayList<>();
		for (SchArrangeResult result : resultList) {
			rotationFlowList.add(result.getRotationFlow());
			standardDeptIdList.add(result.getStandardDeptId());
			groupFlowList.add(result.getStandardGroupFlow());
		}
		return rotationDeptMapper.selectListWithBLOBs(rotationFlowList, standardDeptIdList, groupFlowList);
	}

	public SchRotationDept readStandardRotationDept(String resultFlow) {
		SchRotationDept rotationDept = null;
		if (StringUtil.isNotBlank(resultFlow)) {
			SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(resultFlow);
			if (result != null) {
				String rotationFlow = result.getRotationFlow();
				String standardDeptId = result.getStandardDeptId();
				SchRotationGroup group = rotationGroupBiz.readSchRotationGroup(result.getStandardGroupFlow());
				if (group != null) {
					if (StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(standardDeptId)) {
						SchRotationDeptExample example = new SchRotationDeptExample();
                        SchRotationDeptExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
								.andRotationFlowEqualTo(rotationFlow)
								.andStandardDeptIdEqualTo(standardDeptId)
								.andOrgFlowIsNull()
								.andGroupFlowEqualTo(result.getStandardGroupFlow());
						List<SchRotationDept> rotationDeptList = rotationDeptMapper.selectByExampleWithBLOBs(example);
						if (rotationDeptList != null && rotationDeptList.size() > 0) {
							rotationDept = rotationDeptList.get(0);
						}
					}
				}
			}
		}
		return rotationDept;
	}

	/**
	 * 月报查询  住院医师
	 *
	 * @return
	 */
	@RequestMapping(value = {"/showMonthlyReportList"})
	public String showMonthlyReportList() {
		return "jsres/hospital/monthlyReportList";
	}

	/**
	 * 月报查询 助理全科
	 *
	 * @return
	 */
	@RequestMapping(value = {"/showMonthlyReportListAcc"})
	public String showMonthlyReportListAcc() {
		return "jsres/hospital/monthlyReportListAcc";
	}

	/****************************高******校******管******理******员******角******色************************************************/
	/**
	 * 医师工作量查询查询条件页
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/docWorkQueryForUni")
	public String docWorkQueryForUni(Model model) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		//查询高校学员所在的所有培训基地
		List<SysOrg> orgs = orgBiz.getUniOrgs(currentOrg.getSendSchoolId(), currentOrg.getSendSchoolName());
		model.addAttribute("orgs", orgs);
		return "jsres/university/docWork/workQuery";
	}

	/**
	 * 医师工作量查询(高校角色)
	 *
	 * @param trainingTypeId
	 * @param trainingSpeId
	 * @param sessionNumber
	 * @param orgFlow
	 * @param userName
	 * @param idNo
	 * @param graduationYear
	 * @param sort（存放排序方法）
	 * @param currentPage
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = {"/docWorkingForUni"})
	public String docWorkingForUni(String trainingTypeId, String trainingSpeId, String sessionNumber, String orgFlow,
								   String userName, String idNo, String graduationYear, String sort,
								   Integer currentPage, HttpServletRequest request, Model model) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		SysOrg currentOrg = orgBiz.readSysOrg(currentUser.getOrgFlow());
		List<String> docTypeList = new ArrayList<String>();
        docTypeList.add(com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
		List<String> recTypeIds = new ArrayList<String>();
		for (RegistryTypeEnum regType : RegistryTypeEnum.values()) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
				recTypeIds.add(regType.getId());
			}
		}
		//查询高校学员所在的所有培训基地
		List<SysOrg> orgs = orgBiz.getUniOrgs(currentOrg.getSendSchoolId(), currentOrg.getSendSchoolName());
		List<String> orgFlowList = new ArrayList<>();
		//判断查询条件是否含有orgFlow
		if (StringUtil.isNotBlank(orgFlow)) {
			orgFlowList.add(orgFlow);
		} else {
			//如果orgFlow为空则查询所有基地
			if (orgs != null && !orgs.isEmpty()) {
				for (SysOrg orgTemp : orgs) {
					orgFlowList.add(orgTemp.getOrgFlow());
				}
			}
		}
		Map<String, Object> parMp = new HashMap<String, Object>();
		if (StringUtil.isNotEmpty(currentUser.getSchool())) {
			parMp.put("school", currentUser.getSchool());
		}
		parMp.put("orgFlowList", orgFlowList);
		parMp.put("docTypeList", docTypeList);
		parMp.put("typeId", trainingTypeId);
		parMp.put("speId", trainingSpeId);
		parMp.put("sessionNumber", sessionNumber);
		parMp.put("sort", sort);
		parMp.put("userName", userName);
		parMp.put("idNo", idNo);
		parMp.put("graduationYear", graduationYear);
		parMp.put("recTypeIds", recTypeIds);
		parMp.put("roleFlag", "Uni");
		parMp.put("workOrgId", currentOrg.getSendSchoolId());
		parMp.put("workOrgName", currentOrg.getSendSchoolName());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, Object>> rltLst = resultBiz.docWorkingSearch(parMp);
		model.addAttribute("rltLst", rltLst);
		return "jsres/university/docWork/workList";
	}

	@RequestMapping(value = {"/uni/orgTeaAuditInfo"})
	public String orgTeaAuditInfo(
			String sessionNumber,
			Integer currentPage,
			HttpServletRequest request,
			Model model) {
		Map<String, Object> parMp = new HashMap<String, Object>();
		parMp.put("sessionNumber", sessionNumber);
		SysOrg currOrg = orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
        parMp.put("doctorTypeId", com.pinde.core.common.enums.ResDocTypeEnum.Graduate.getId());
		parMp.put("workOrgId", currOrg.getSendSchoolId());
		parMp.put("workOrgName", currOrg.getSendSchoolName());
		List<Map<String, Object>> rltLst = resultBiz.orgTeaAuditInfoForUni(parMp);
		model.addAttribute("rltLst", rltLst);
		return "jsres/university/orgTeaAuditInfo";
	}

	/**
	 * @Department：研发部
	 * @Description 查询培训基地列表
	 * @Author fengxf
	 * @Date 2020/9/2
	 *//*
	@RequestMapping("/searchBaseInfoList")
	public String searchBaseInfoList(Model model, SysOrgExt sysOrgExt, String type, Integer currentPage, HttpServletRequest request){
		SysUser currUser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
		sysOrgExt.setOrgProvId(org.getOrgProvId());
		if(getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)){
			sysOrgExt.setOrgCityId(org.getOrgCityId());
		}
		sysOrgExt.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if(StringUtil.isNotBlank(type)){
			if(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(type)){
				sysOrgExt.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
			}else if(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId().equals(type)){
				sysOrgExt.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
			}
		}
		List<SysOrg> orgList = orgBiz.searchOrgByJoin(sysOrgExt);	//主基地
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchByOrgName(sysOrgExt.getOrgName());
		HashMap<String, List<ResJointOrg>> jointOrgMap = new HashMap<>(); //主协同关系
		HashMap<String, Integer> jointOrgNumMap = new HashMap<>(); //主协同数量关系
		List<ResOrgSpe> speList = orgBiz.searchByCountOrg(sysOrgExt.getOrgName()); //主基地专业信息
		HashMap<String, Integer> speNumMap = new HashMap<>(); //主基地专业数量
		HashMap<String, List<ResOrgSpe>> speMap = new HashMap<>();
		List<ResJointOrg> jointOrgs=new ArrayList<>();
		List<ResOrgSpe> spes=new ArrayList<>();
		List<String> orgFlowList=new ArrayList<>();
		for (SysOrg sysOrg : orgList) {
			jointOrgs = jointOrgList.stream().
					filter(jointOrg -> jointOrg.getOrgFlow().equals(sysOrg.getOrgFlow())).collect(Collectors.toList());
			jointOrgMap.put(sysOrg.getOrgFlow(),jointOrgs);
			jointOrgNumMap.put(sysOrg.getOrgFlow(),jointOrgs.size());
			spes=speList.stream().filter(s-> s.getOrgFlow().equals(sysOrg.getOrgFlow())).collect(Collectors.toList());
			speMap.put(sysOrg.getOrgFlow(),spes);
			speNumMap.put(sysOrg.getOrgFlow(),spes.size());
			orgFlowList.add(sysOrg.getOrgFlow());
		}
		List<ResBase> baseList = baseBiz.readBaseList(orgFlowList);
		HashMap<String, BasicInfoForm> resBaseMap = new HashMap<>();
		HashMap<String, ResBase> baseMap = new HashMap<>();
		if (null!=baseList && baseList.size()>0){
			for (ResBase resBase : baseList) {
				baseMap.put(resBase.getOrgFlow(),resBase);
				if (StringUtil.isNotBlank(resBase.getBaseInfo())){
					String Xml=resBase.getBaseInfo();
					if (StringUtil.isNotBlank(Xml)) {
						BaseExtInfoForm baseExtInfoForm=JaxbUtil.converyToJavaBean(Xml, BaseExtInfoForm.class);
						resBaseMap.put(resBase.getOrgFlow(),baseExtInfoForm.getBasicInfo());
					}
				}
			}
		}
		model.addAttribute("orgList",orgList);
		model.addAttribute("jointOrgMap",jointOrgMap);
		model.addAttribute("jointOrgNumMap",jointOrgNumMap);
		model.addAttribute("speMap",speMap);
		model.addAttribute("speNumMap",speNumMap);
		model.addAttribute("resBaseMap",resBaseMap);
		model.addAttribute("baseMap",baseMap);
		return "jsres/global/hospital/orgList";
	}*/

	/**
	 * @Department：研发部
	 * @Description 查询培训基地列表
	 * @Author fengxf 上面注释的searchBaseInfoList是新的页面，老板要求还原的
	 * @Date 2020/9/2
	 */
	@RequestMapping("/searchBaseInfoList")
	public String searchBaseInfoList(Model model, SysOrgExt sysOrgExt, String type, Integer currentPage, HttpServletRequest request) {
		SysUser currUser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
		sysOrgExt.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			sysOrgExt.setOrgCityId(org.getOrgCityId());
		}
        sysOrgExt.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if (StringUtil.isNotBlank(type)) {
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(type)) {
                sysOrgExt.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
            } else if (com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId().equals(type)) {
                sysOrgExt.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
			}
		}
		if (currentPage == null) {
			currentPage = 1;
		}

		if (StringUtil.isBlank(sysOrgExt.getSessionYear())) {
			sysOrgExt.setSessionYear(DateUtil.getYear());
		}
		model.addAttribute("sessionYear", sysOrgExt.getSessionYear());
		List<SysOrgExt> sysOrgExtList = orgBiz.searchOrgListByParamNew(sysOrgExt);
//		List<SysOrgExt> sysOrgExtList = orgBiz.searchOrgListByParam(sysOrgExt);
		model.addAttribute("sysOrgExtList", sysOrgExtList);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String, String>> jointCountList = orgBiz.getJointOrgCountByParamNew(sysOrgExt);
//		List<Map<String, String>> jointCountList = orgBiz.getJointOrgCountByParam(sysOrgExt);
		model.addAttribute("jointCountList", jointCountList);
		return "jsres/global/hospital/orgList";
	}

	@RequestMapping("/exporthBaseInfoList")
	public void exporthBaseInfoList(SysOrgExt sysOrgExt, String type, HttpServletResponse response) throws IOException {
		SysUser currUser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
		sysOrgExt.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			sysOrgExt.setOrgCityId(org.getOrgCityId());
		}
        sysOrgExt.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
		if (StringUtil.isNotBlank(type)) {
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(type)) {
                sysOrgExt.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
            } else if (com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId().equals(type)) {
                sysOrgExt.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
			}
		}
		List<SysOrg> orgList = orgBiz.searchOrgByJoin(sysOrgExt);    //主基地
		List<ResJointOrg> jointOrgList = jointOrgBiz.searchByOrgName(sysOrgExt.getOrgName());
		HashMap<String, String> jointOrgMap = new HashMap<>(); //主协同关系
		List<ResOrgSpe> speList = orgBiz.searchByCountOrg(sysOrgExt.getOrgName()); //主基地专业信息
		HashMap<String, String> speMap = new HashMap<>();
		List<ResJointOrg> jointOrgs = new ArrayList<>();
		List<ResOrgSpe> spes = new ArrayList<>();
		List<String> orgFlowList = new ArrayList<>();
		for (SysOrg sysOrg : orgList) {
			jointOrgs = jointOrgList.stream().
					filter(jointOrg -> jointOrg.getOrgFlow().equals(sysOrg.getOrgFlow())).collect(Collectors.toList());
			String jointMsg = "";
			for (ResJointOrg jointOrg : jointOrgs) {
				jointMsg += jointOrg.getJointOrgName() + ";";
			}
			jointOrgMap.put(sysOrg.getOrgFlow(), jointMsg);
			jointOrgMap.put(sysOrg.getOrgFlow() + "Num", String.valueOf(jointOrgs.size()));

			spes = speList.stream().filter(s -> s.getOrgFlow().equals(sysOrg.getOrgFlow())).collect(Collectors.toList());
			String speMsg = "";
			for (ResOrgSpe spe : spes) {
				speMsg += spe.getSpeName() + ";";
			}
			speMap.put(sysOrg.getOrgFlow(), speMsg);
			speMap.put(sysOrg.getOrgFlow() + "Num", String.valueOf(spes.size()));
			orgFlowList.add(sysOrg.getOrgFlow());
		}
		List<ResBase> baseList = baseBiz.readBaseList(orgFlowList);
		SysOrgBatchExample example = new SysOrgBatchExample();
		example.createCriteria().andOrgFlowIn(orgFlowList);
		List<SysOrgBatch> batchList = orgBatchMapper.selectByExample(example);
		HashMap<String, String> batchMap = new HashMap<>();
		for (SysOrgBatch batch : batchList) {
			batchMap.put(batch.getOrgFlow(), batch.getOrgBatch());
		}
		HashMap<String, BasicInfoForm> resBaseMap = new HashMap<>();
		HashMap<String, ResBase> baseMap = new HashMap<>();
		if (null != baseList && baseList.size() > 0) {
			for (ResBase resBase : baseList) {
				baseMap.put(resBase.getOrgFlow(), resBase);
				if (StringUtil.isNotBlank(resBase.getBaseInfo())) {
					String Xml = resBase.getBaseInfo();
					if (StringUtil.isNotBlank(Xml)) {
						BaseExtInfoForm baseExtInfoForm = JaxbUtil.converyToJavaBean(Xml, BaseExtInfoForm.class);
						resBaseMap.put(resBase.getOrgFlow(), baseExtInfoForm.getBasicInfo());
					}
				}
			}
		}

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);

		HSSFCellStyle colorStyleCenter = wb.createCellStyle(); //居中
		colorStyleCenter.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		colorStyleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		colorStyleCenter.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());
		colorStyleCenter.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font font = wb.createFont();    //设置字体
		font.setBold(true);//粗体显示
		colorStyleCenter.setFont(font);

		String fileName = "培训基地清单.xls";    //文件名称
		HSSFSheet sheet = wb.createSheet("培训基地清单");//sheet名称
		HSSFRow row1 = sheet.createRow(0);//第一行
		String[] titles1 = new String[]{
				"序号",
				"省份",
				"地市",
				"培训基地名称",
				"遴选批次",
				"基地编号",
				"基地级别",
				"培训基地（医院负责人）",
				"",
				"职能部门负责人",
				"",
				"住培业务负责人",
				"",
				"专业基地数量",
				"专业",
				"协同单位数量",
				"协同单位"
		};
		HSSFCell cellTitle1 = null;
		for (int i = 0; i < titles1.length; i++) {
			cellTitle1 = row1.createCell(i);
			cellTitle1.setCellValue(titles1[i]);
			cellTitle1.setCellStyle(colorStyleCenter);
			cellTitle1.setCellType(CellType.STRING);
		}
		HSSFRow row2 = sheet.createRow(1);//第一行
		String[] titles2 = new String[]{
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"姓名",
				"联系方式",
				"姓名",
				"联系方式",
				"姓名",
				"联系方式",
				"",
				"",
				"",
				""
		};
		HSSFCell cellTitle2 = null;
		for (int i = 0; i < titles1.length; i++) {
			cellTitle2 = row2.createCell(i);
			cellTitle2.setCellValue(titles2[i]);
			cellTitle2.setCellStyle(colorStyleCenter);
			cellTitle2.setCellType(CellType.STRING);
		}
		for (int i = 0; i < 7; i++) {
			CellRangeAddress address4 = new CellRangeAddress(0, 1, i, i);
			sheet.addMergedRegion(address4);
		}
		sheet.setColumnWidth(1, 2000);
		sheet.setColumnWidth(2, 4000);
		sheet.setColumnWidth(3, 8000);
		sheet.setColumnWidth(4, 4000);
		sheet.setColumnWidth(5, 4000);
		sheet.setColumnWidth(6, 6000);
		sheet.setColumnWidth(7, 4000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		sheet.setColumnWidth(12, 4000);
		sheet.setColumnWidth(13, 4000);
		sheet.setColumnWidth(14, 16000);
		sheet.setColumnWidth(15, 4000);
		sheet.setColumnWidth(16, 16000);
		CellRangeAddress address = new CellRangeAddress(0, 0, 7, 8);
		sheet.addMergedRegion(address);
		CellRangeAddress address2 = new CellRangeAddress(0, 0, 9, 10);
		sheet.addMergedRegion(address2);
		CellRangeAddress address3 = new CellRangeAddress(0, 0, 11, 12);
		sheet.addMergedRegion(address2);
		for (int i = 13; i < 17; i++) {
			CellRangeAddress address4 = new CellRangeAddress(0, 1, i, i);
			sheet.addMergedRegion(address4);
		}

		for (int i = 0; i < orgList.size(); i++) {
			HSSFRow row = sheet.createRow(i + 2);//第一行
			SysOrg sysOrg = orgList.get(i);
			String orgFlow = sysOrg.getOrgFlow();
			String speNum = "0";
			String jointNum = "0";
			if (StringUtil.isNotBlank(speMap.get(orgFlow + "Num"))) {
				speNum = speMap.get(orgFlow + "Num");
			}
			if (StringUtil.isNotBlank(jointOrgMap.get(orgFlow + "Num"))) {
				jointNum = jointOrgMap.get(orgFlow + "Num");
			}
			String[] titles = new String[]{
					String.valueOf(i + 1),
					sysOrg.getOrgProvName(),
					sysOrg.getOrgCityName(),
					sysOrg.getOrgName(),
					batchMap.get(orgFlow),
					sysOrg.getOrgCode(),
					baseMap.get(orgFlow).getBaseGradeName(),
					sysOrg.getOrgPersonInCharge(),
					"",
					resBaseMap.get(orgFlow).getZnbmfzrmc(),
					resBaseMap.get(orgFlow).getZnbmfzrdh(),
					resBaseMap.get(orgFlow).getZpywlxrmc(),
					resBaseMap.get(orgFlow).getZpywlxrdh(),
					speNum,
					speMap.get(orgFlow),
					jointNum,
					jointOrgMap.get(orgFlow)
			};
			HSSFCell cellTitle = null;
			for (int j = 0; j < titles.length; j++) {
				cellTitle = row.createCell(j);
				cellTitle.setCellValue(titles[j]);
				cellTitle.setCellStyle(styleCenter);
				cellTitle.setCellType(CellType.STRING);
			}
		}
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());

	}

	@RequestMapping("/editCoopBase")
	public String editCoopBase(String orgFlow, String sessionNumber, Model model, HttpServletRequest request) throws IOException {
		// 所有关联协同单位关系（特定年份）
		ResJointOrg resJointOrg = new ResJointOrg();
		resJointOrg.setSessionNumber(sessionNumber);
        resJointOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<ResJointOrg> resJointOrgAllList = jointOrgBiz.searchResJoint(resJointOrg);

		// 将当前的协同单位放前面
		Collections.sort(resJointOrgAllList, (j1, j2) -> {
			if(j1.getOrgFlow().equals(orgFlow)) {
				return -1;
			}else if(j2.getOrgFlow().equals(orgFlow)) {
				return 1;
			}else {
				return 0;
			}
		});

		int jointCount = 0;
		for (ResJointOrg jointOrg : resJointOrgAllList) {
			if(jointOrg.getOrgFlow().equals(orgFlow)) {
				jointCount++;
			}else {
				break;
			}
		}
		model.addAttribute("jointCount", jointCount);

		// 所有协同单位
		SysOrg sysOrg = new SysOrg();
		sysOrg.setOrgLevelIdNotIn(Arrays.asList("CountryOrg", "ProvinceOrg")); // 去掉56家基地
		sysOrg.setOrgTypeId("Hospital");
		List<SysOrg> sysOrgList = orgBiz.selectJointOrgAllList(sysOrg);

		// sessionNumber还未关联的
		Map<String, SysOrg> flowToEntityMap = sysOrgList.stream().collect(Collectors.toMap(vo -> vo.getOrgFlow(), Function.identity(), (vo1, vo2) -> vo1));
		List<SysOrg> unjointOrgList = new ArrayList<>();
		List<String> allOrgFlowList = sysOrgList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
		List<String> jointOrgList = resJointOrgAllList.stream().map(vo -> vo.getJointOrgFlow()).collect(Collectors.toList());
		allOrgFlowList.removeAll(jointOrgList);
		for (String unjointOrgFlow : allOrgFlowList) {
			unjointOrgList.add(flowToEntityMap.get(unjointOrgFlow));
		}

		model.addAttribute("unjointOrgList", unjointOrgList);
		model.addAttribute("jointOrgList", resJointOrgAllList);
		model.addAttribute("curOrgFlow", orgFlow);
		model.addAttribute("curOrgName", GlobalContext.getCurrentUser().getOrgName());
		model.addAttribute("sessionNumber", sessionNumber);

		return "jsres/hospital/hos/updateCoopBase";
	}

	@RequestMapping("/saveCoopBase")
	@ResponseBody
	public String saveCoopBase(@RequestBody List<ResJointOrg> jointOrgList, HttpServletRequest request) {
		SysUser user = GlobalContext.getCurrentUser();
		String orgFlow = user.getOrgFlow();
		String sessionNumber = request.getParameter("sessionNumber");
		// todo 校验数据，先不做
		// 先删除原有关联
		jointOrgBiz.deleteByOrgFlow(orgFlow, sessionNumber);
		// 再增加新的关联
		if(CollectionUtils.isNotEmpty(jointOrgList)) {
			jointOrgList.forEach(jointOrg -> {
				jointOrg.setJointFlow(PkUtil.getUUID());
                jointOrg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
				jointOrg.setSessionNumber(sessionNumber);
				GeneralMethod.setRecordInfo(jointOrg, true);
			});

			jointOrgBiz.saveAll(jointOrgList);
		}

        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 卫健委 专业基地清单主页面
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/showProfBaseList")
	public String showProfBaseList(Model model, HttpServletRequest request) {
		logger.info("[JsResBaseManagerController.showProfBaseList] start");
		// 查出所有的培训基地
		SysUser currUser = GlobalContext.getCurrentUser();
		SysOrg org = orgBiz.readSysOrg(currUser.getOrgFlow());
		SysOrgExample orgExample = new SysOrgExample();
        SysOrgExample.Criteria orgCriteria = orgExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andOrgProvIdEqualTo(org.getOrgProvId())
                .andOrgTypeIdEqualTo(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId()).andOrgLevelIdIn(Arrays.asList(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId()));
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
			orgCriteria.andOrgCityIdEqualTo(org.getOrgCityId());
		}
		orgExample.setOrderByClause("org_code, ordinal");
		// 所有的基地列表
		List<SysOrg> orgList = orgBiz.searchOrgByExample(orgExample);
		logger.info("[JsResBaseManagerController.showProfBaseList] orgList size: {}", orgList);

		// 查出培训基地所有的协同单位（默认年份：当前）
		List<ResJointOrg> jointOrgList = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(orgList)) {
			ResJointOrgExample jointOrgExample = new ResJointOrgExample();
			List<String> orgFlowList = orgList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
            jointOrgExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSessionNumberEqualTo(DateUtil.getYear())
					.andOrgFlowIn(orgFlowList);
			jointOrgExample.setOrderByClause("NLSSORT(joint_org_name,'NLS_SORT = SCHINESE_PINYIN_M')");
			jointOrgList = jointOrgBiz.readResJointOrgByExample(jointOrgExample);
			logger.info("[JsResBaseManagerController.showProfBaseList] jointOrgList size: {}", jointOrgList);
		}

		model.addAttribute("mainBaseList", orgList);
		model.addAttribute("jointOrgList", jointOrgList);

		logger.info("[JsResBaseManagerController.showProfBaseList] end");
		return "/jsres/base/profBaseList/profBaseMain";
	}

	/**
	 * 可以没有jointOrg协同单位
	 * 模仿/trainSpeMainInfo来写
	 * 各家医院人员培训信息汇总
	 * @param sessionNumber
	 * @param mainBase
	 * @param jointOrg
	 * @param profDept
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "profBaseList")
	public String profBaseList(String sessionNumber, String[] mainBase, String[] jointOrg, String[] profDept, Model model, String ishos, HttpServletRequest request) {
		logger.info("[JsResBaseManagerController.profBaseList] start");
		// 入参不完整，直接返回，前台无记录
		if(StringUtils.isEmpty(sessionNumber) || ArrayUtils.isEmpty(mainBase) || ArrayUtils.isEmpty(profDept)) {
			model.addAttribute("orgSpeList", new ArrayList<ResOrgSepVO>());
			return "/jsres/base/profBaseList/profBaseList";
		}

		model.addAttribute("ishos", ishos);
		model.addAttribute("sessionNumber", sessionNumber);

		List<ResOrgSepVO> resList = getProfBaseData(sessionNumber, mainBase, jointOrg, profDept);
		model.addAttribute("orgSpeList", resList);
		logger.info("[JsResBaseManagerController.profBaseList] end");
		return "/jsres/base/profBaseList/profBaseList";
	}

	private List<ResOrgSepVO> getProfBaseData(String sessionNumber, String[] mainBase, String[] jointOrg, String[] profDept) {
		Map<String, ResOrgSepVO> speIdToOrgSpeVOMap = new HashMap<>();
		// 非当年时，只展示专业基地编码，专业基地名称，住院医师(xx级在培），在校专硕(xx级在培)，当年时展示全部，只展示开启状态的专业基地
		ResOrgSpeExample orgSpeExample = new ResOrgSpeExample();
		List<String> orgFlowList = Arrays.stream(mainBase).collect(Collectors.toList());
		if(ArrayUtils.isNotEmpty(jointOrg)) {
			orgFlowList.addAll(Arrays.stream(jointOrg).collect(Collectors.toList()));
		}
		List<String> speIdList = Arrays.stream(profDept).collect(Collectors.toList());
        orgSpeExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSpeTypeIdEqualTo(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId())
						.andSpeIdIn(speIdList).andOrgFlowIn(orgFlowList).andSessionYearEqualTo(sessionNumber).andStatusEqualTo("open"); // 只查开启的专业
		List<ResOrgSpe> orgSpeList = speAssignBiz.findOrgSpeByExample(orgSpeExample);
		Map<String, List<ResOrgSpe>> speIdToOrgSpeMap = orgSpeList.stream().collect(Collectors.groupingBy(vo -> vo.getSpeId()));

		speIdToOrgSpeMap.forEach((key, val) -> {
			ResOrgSepVO resOrgSepVO = new ResOrgSepVO();
			// 开启总数
			resOrgSepVO.setOpenSpeBases(String.valueOf(val.size()));
			resOrgSepVO.setSpeName(val.get(0).getSpeName());
			resOrgSepVO.setSpeId(val.get(0).getSpeId());
			if(DateUtil.getYear().equals(sessionNumber)) {
				Integer baseCapacity = val.stream().filter(vo -> StringUtils.isNotEmpty(vo.getBaseCapacity())).map(vo -> Integer.parseInt(vo.getBaseCapacity()))
						.reduce(0, (v1, v2) -> v1 + v2);
				resOrgSepVO.setBaseCapacity(baseCapacity.toString());
				Integer minTrainingCapacity = val.stream().filter(vo -> StringUtils.isNotEmpty(vo.getMinRecruitCapacity())).map(vo -> Integer.parseInt(vo.getMinRecruitCapacity()))
						.reduce(0, (v1, v2) -> v1 + v2);
				resOrgSepVO.setMinRecruitCapacity(minTrainingCapacity.toString());
				resOrgSepVO.setOrgFlowList(val.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList()));
			}
			speIdToOrgSpeVOMap.put(key, resOrgSepVO);
		});

		if(DateUtil.getYear().equals(sessionNumber)) {
			// 查人，当年在培的人数，所有在培的人数
			// 在培住院医师
			Map<String, Object> doctorRecruitMap = new HashMap<>();
			doctorRecruitMap.put("doctorStatusId", "20"); // 在培
			doctorRecruitMap.put("orgFlowList", orgFlowList);
			doctorRecruitMap.put("trainYearList", Arrays.asList("OneYear", "TwoYear", "ThreeYear"));
			doctorRecruitMap.put("docTypeList", Arrays.asList("Company", "CompanyEntrust", "Social"));
			doctorRecruitMap.put("speIdList", speIdList);
            doctorRecruitMap.put("orgGroup", com.pinde.core.common.GlobalConstant.FLAG_Y);
			// 查医院全部在培人员，sql来自【学员信息查询】页面列表sql
			List<Map<String, Object>> speTrainCountList = jsResDoctorRecruitExtMapper.countTrainingDoctor(doctorRecruitMap);
			Map<String, List<Map<String, Object>>> speTrainCountMap = speTrainCountList.stream().collect(Collectors.groupingBy(vo -> (String)vo.get("SPEID")));

			speTrainCountMap.forEach((key, vals) -> {
				BigDecimal[] inHospitalDoctors = new BigDecimal[]{BigDecimal.ZERO};
				ResOrgSepVO resOrgSepVO = speIdToOrgSpeVOMap.get(key);
				if (null == resOrgSepVO) {
					return;
				}
				vals.forEach(val -> {
					if(!speIdToOrgSpeMap.containsKey(val.get("SPEID"))) {
						return;
					}
					List<ResOrgSpe> resOrgSpeList = speIdToOrgSpeMap.get(val.get("SPEID"));
					List<String> tempOrgFlowList = resOrgSpeList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
					if(!tempOrgFlowList.contains(val.get("ORG_FLOW"))) {
						return;
					}

					inHospitalDoctors[0] = inHospitalDoctors[0].add((BigDecimal) val.get("TRAININGCOUNT"));
				});
				resOrgSepVO.setInHospitalDoctors(inHospitalDoctors[0].toPlainString());
			});


			// 在培在校专硕医师
			doctorRecruitMap = new HashMap<>();
			doctorRecruitMap.put("doctorStatusId", "20"); // 在培
			doctorRecruitMap.put("orgFlowList", orgFlowList);
			doctorRecruitMap.put("trainYearList", Arrays.asList("OneYear", "TwoYear", "ThreeYear"));
			doctorRecruitMap.put("docTypeList", Arrays.asList("Graduate"));
			doctorRecruitMap.put("speIdList", speIdList);
            doctorRecruitMap.put("orgGroup", com.pinde.core.common.GlobalConstant.FLAG_Y);
			// 查医院全部在校专硕，sql来自【学员信息查询】页面列表sql
			speTrainCountList = jsResDoctorRecruitExtMapper.countTrainingDoctor(doctorRecruitMap);
			speTrainCountMap = speTrainCountList.stream().collect(Collectors.groupingBy(vo -> (String)vo.get("SPEID")));
			speTrainCountMap.forEach((key, vals) -> {
				BigDecimal[] inCollegeMasters = new BigDecimal[]{BigDecimal.ZERO};
				ResOrgSepVO resOrgSepVO = speIdToOrgSpeVOMap.get(key);
				if (null == resOrgSepVO) {
					return;
				}
				vals.forEach(val -> {
					if (!speIdToOrgSpeMap.containsKey(val.get("SPEID"))) {
						return;
					}
					List<ResOrgSpe> resOrgSpeList = speIdToOrgSpeMap.get(val.get("SPEID"));
					List<String> tempOrgFlowList = resOrgSpeList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
					if (!tempOrgFlowList.contains(val.get("ORG_FLOW"))) {
						return;
					}
					inCollegeMasters[0] = inCollegeMasters[0].add((BigDecimal) val.get("TRAININGCOUNT"));
				});
				resOrgSepVO.setInCollegeMasters(inCollegeMasters[0].toPlainString());
			});

			// 在培总数
			speIdToOrgSpeVOMap.forEach((key, val) -> {
				String inHospitalDoctors = val.getInHospitalDoctors();
				String inCollegeMasters = val.getInCollegeMasters();
				if(StringUtils.isNoneEmpty(inHospitalDoctors, inCollegeMasters)) {
					val.setInTrains(String.valueOf(Integer.parseInt(inHospitalDoctors) + Integer.parseInt(inCollegeMasters)));
				}else if(StringUtils.isNotEmpty(inHospitalDoctors)) {
					val.setInTrains(inHospitalDoctors);
				}else if(StringUtils.isNotEmpty(inCollegeMasters)) {
					val.setInTrains(inCollegeMasters);
				}

				// 容量使用率
				if(StringUtils.isNoneEmpty(val.getInTrains(), val.getBaseCapacity()) && !"0".equals(val.getBaseCapacity())) {
					BigDecimal inTrainsDec = new BigDecimal(val.getInTrains());
					BigDecimal baseCapacityDec = new BigDecimal(val.getBaseCapacity());
					BigDecimal capacityUsePer = inTrainsDec.divide(baseCapacityDec, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(1, BigDecimal.ROUND_HALF_UP);
					val.setTrainingCapacityUsePer(capacityUsePer.toPlainString());
				}
			});
		}

		//查询一年的住院医师和在校专硕的人数
		Map<String, String> param = new HashMap<>();
		param.put("orgFlowList", String.join(",", orgFlowList));
		param.put("doctorType", "zyys");
		param.put("sessionNumber", sessionNumber);
		param.put("speIdList", String.join(",", profDept));
        param.put("orgGroup", com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<Map<String, Object>> zyysDoctorList = deptBasicInfoBiz.countDoctorNum(param);
		Map<String, BigDecimal[]> speCountMap = new HashMap<>();
		for (Map<String, Object> map : zyysDoctorList) {
			ResOrgSepVO resOrgSepVO = speIdToOrgSpeVOMap.get(map.get("speId"));
			if(resOrgSepVO == null) {
				continue;
			}
			if(!speIdToOrgSpeMap.containsKey((map.get("speId")))) {
				continue;
			}
			List<ResOrgSpe> resOrgSpeList = speIdToOrgSpeMap.get((map.get("speId")));
			List<String> tempOrgFlowList = resOrgSpeList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
			if(!tempOrgFlowList.contains(map.get("orgFlow"))) {
				continue;
			}
			BigDecimal[] curInHospitalDoctors = speCountMap.getOrDefault(map.get("speId"), new BigDecimal[]{BigDecimal.ZERO});
			curInHospitalDoctors[0] = curInHospitalDoctors[0].add((BigDecimal)map.get("num"));
			speCountMap.put((String) map.get("speId"), curInHospitalDoctors);
			resOrgSepVO.setCurInHospitalDoctors(curInHospitalDoctors[0].toPlainString());
		}
		param.put("doctorType", "zxzs");
		List<Map<String, Object>> zxzsDoctorList = deptBasicInfoBiz.countDoctorNum(param);
		speCountMap = new HashMap<>();
		for (Map<String, Object> map : zxzsDoctorList) {
			ResOrgSepVO resOrgSepVO = speIdToOrgSpeVOMap.get(map.get("speId"));
			if(resOrgSepVO == null) {
				continue;
			}
			if(!speIdToOrgSpeMap.containsKey((map.get("speId")))) {
				continue;
			}
			List<ResOrgSpe> resOrgSpeList = speIdToOrgSpeMap.get((map.get("speId")));
			List<String> tempOrgFlowList = resOrgSpeList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
			if(!tempOrgFlowList.contains(map.get("orgFlow"))) {
				continue;
			}
			BigDecimal[] curInCollegeMasters = speCountMap.getOrDefault(map.get("speId"), new BigDecimal[]{BigDecimal.ZERO});
			curInCollegeMasters[0] = curInCollegeMasters[0].add((BigDecimal)map.get("num"));
			speCountMap.put((String) map.get("speId"), curInCollegeMasters);
			resOrgSepVO.setCurInCollegeMasters(curInCollegeMasters[0].toPlainString());
		}

		List<ResOrgSepVO> resList = new ArrayList<>(speIdToOrgSpeVOMap.values());
		Collections.sort(resList, (o1, o2) -> o1.getSpeId().compareTo(o2.getSpeId()));
		return resList;
	}

	@RequestMapping(value="/profBaseOneDialog")
	public String profBaseOneDialog(String orgFLowList, String sessionNumber, String speId, Model model, HttpServletRequest request) {
		logger.info("[JsResBaseManagerController.profBaseOneDialog] start");
		List<String> allOrgFlowList = new ArrayList<>();
		// orgFLowList是这个样子的，解析一下：main:joint,joint;main:;main:joint;
		if(StringUtils.isEmpty(orgFLowList)) {
			orgFLowList = request.getParameter("orgFlowList");
		}
		Map<String, List<String>> mainToJointMap = new TreeMap<>();
		String[] mainJointArr = orgFLowList.split(";");
		for(String mainJoint: mainJointArr) {
			List<String> jointList = new ArrayList<>();
			String[] splitMainJoint = mainJoint.split(":");
			if(splitMainJoint.length == 2) {
				String[] jointArr = splitMainJoint[1].split(",");
				jointList.addAll(Arrays.stream(jointArr).collect(Collectors.toList()));
			}
			mainToJointMap.put(splitMainJoint[0], jointList);
			// 同时把所有的org_flow保存一下
			allOrgFlowList.add(splitMainJoint[0]);
			allOrgFlowList.addAll(jointList);
		}

		model.addAttribute("sessionNumber", sessionNumber);
		// 非当年时，只展示专业基地编码，专业基地名称，住院医师(xx级在培），在校专硕(xx级在培)，当年时展示全部，只展示开启状态的专业基地
		ResOrgSpeExample orgSpeExample = new ResOrgSpeExample();
		List<String> speIdList = Arrays.asList(speId);
        orgSpeExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSpeTypeIdEqualTo(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId())
				.andSpeIdIn(speIdList).andOrgFlowIn(allOrgFlowList).andSessionYearEqualTo(sessionNumber).andStatusEqualTo("open"); // 只查开启的专业
		List<ResOrgSpe> orgSpeList = speAssignBiz.findOrgSpeByExample(orgSpeExample);
		Map<String, List<ResOrgSpe>> speIdToOrgSpeMap = orgSpeList.stream().collect(Collectors.groupingBy(vo -> vo.getSpeId()));
		Map<String, ResOrgSepVO> orgToOrgSpeVOMap = new HashMap<>();
		orgSpeList.forEach(val -> {
			ResOrgSepVO resOrgSepVO = new ResOrgSepVO();
			// 开启总数
			resOrgSepVO.setOrgName(val.getOrgName());
			resOrgSepVO.setOrgFlow(val.getOrgFlow());
			resOrgSepVO.setSpeId(val.getSpeId());
			if(DateUtil.getYear().equals(sessionNumber)) {
				resOrgSepVO.setBaseCapacity(val.getBaseCapacity());
				resOrgSepVO.setMinRecruitCapacity(val.getMinRecruitCapacity());
			}
			orgToOrgSpeVOMap.put(val.getOrgFlow(), resOrgSepVO);
		});

		if(DateUtil.getYear().equals(sessionNumber)) {
			// 查人，当年在培的人数，所有在培的人数
			// 在培住院医师
			Map<String, Object> doctorRecruitMap = new HashMap<>();
			doctorRecruitMap.put("doctorStatusId", "20"); // 在培
			doctorRecruitMap.put("orgFlowList", allOrgFlowList);
			doctorRecruitMap.put("trainYearList", Arrays.asList("OneYear", "TwoYear", "ThreeYear"));
			doctorRecruitMap.put("docTypeList", Arrays.asList("Company", "CompanyEntrust", "Social"));
			doctorRecruitMap.put("speIdList", speIdList);
            doctorRecruitMap.put("orgGroup", com.pinde.core.common.GlobalConstant.FLAG_Y);
			// 查医院全部在培人员，sql来自【学员信息查询】页面列表sql
			List<Map<String, Object>> speTrainCountList = jsResDoctorRecruitExtMapper.countTrainingDoctor(doctorRecruitMap);
			Map<String, Map<String, Object>> speTrainCountMap = speTrainCountList.stream().collect(Collectors.toMap(vo -> (String) vo.get("ORG_FLOW"), vo -> vo, (vo1, vo2) -> vo1));
			speTrainCountMap.forEach((key, val) -> {
				if(!speIdToOrgSpeMap.containsKey(val.get("SPEID"))) {
					return;
				}
				List<ResOrgSpe> resOrgSpeList = speIdToOrgSpeMap.get(val.get("SPEID"));
				List<String> tempOrgFlowList = resOrgSpeList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
				if(!tempOrgFlowList.contains(val.get("ORG_FLOW"))) {
					return;
				}
				ResOrgSepVO resOrgSepVO = orgToOrgSpeVOMap.get(key);
				if (null != resOrgSepVO) {
					resOrgSepVO.setInHospitalDoctors(((BigDecimal) val.get("TRAININGCOUNT")).toPlainString());
				}
			});

			// 在培在校专硕医师
			doctorRecruitMap = new HashMap<>();
			doctorRecruitMap.put("doctorStatusId", "20"); // 在培
			doctorRecruitMap.put("orgFlowList", allOrgFlowList);
			doctorRecruitMap.put("trainYearList", Arrays.asList("OneYear", "TwoYear", "ThreeYear"));
			doctorRecruitMap.put("docTypeList", Arrays.asList("Graduate"));
			doctorRecruitMap.put("speIdList", speIdList);
            doctorRecruitMap.put("orgGroup", com.pinde.core.common.GlobalConstant.FLAG_Y);
			// 查医院全部在培人员，sql来自【学员信息查询】页面列表sql
			speTrainCountList = jsResDoctorRecruitExtMapper.countTrainingDoctor(doctorRecruitMap);
			speTrainCountMap = speTrainCountList.stream().collect(Collectors.toMap(vo -> (String) vo.get("ORG_FLOW"), vo -> vo, (vo1, vo2) -> vo1));
			speTrainCountMap.forEach((key, val) -> {
				if(!speIdToOrgSpeMap.containsKey(val.get("SPEID"))) {
					return;
				}
				List<ResOrgSpe> resOrgSpeList = speIdToOrgSpeMap.get(val.get("SPEID"));
				List<String> tempOrgFlowList = resOrgSpeList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
				if(!tempOrgFlowList.contains(val.get("ORG_FLOW"))) {
					return;
				}

				ResOrgSepVO resOrgSepVO = orgToOrgSpeVOMap.get(key);
				if (null != resOrgSepVO) {
					resOrgSepVO.setInCollegeMasters(((BigDecimal) val.get("TRAININGCOUNT")).toPlainString());
				}
			});

			// 在培总数
			orgToOrgSpeVOMap.forEach((key, val) -> {
				String inHospitalDoctors = val.getInHospitalDoctors();
				String inCollegeMasters = val.getInCollegeMasters();
				if(StringUtils.isNoneEmpty(inHospitalDoctors, inCollegeMasters)) {
					val.setInTrains(String.valueOf(Integer.parseInt(inHospitalDoctors) + Integer.parseInt(inCollegeMasters)));
				}else if(StringUtils.isNotEmpty(inHospitalDoctors)) {
					val.setInTrains(inHospitalDoctors);
				}else if(StringUtils.isNotEmpty(inCollegeMasters)) {
					val.setInTrains(inCollegeMasters);
				}

				// 容量使用率
				if(StringUtils.isNoneEmpty(val.getInTrains(), val.getBaseCapacity()) && !"0".equals(val.getBaseCapacity())) {
					BigDecimal inTrainsDec = new BigDecimal(val.getInTrains());
					BigDecimal baseCapacityDec = new BigDecimal(val.getBaseCapacity());
					BigDecimal capacityUsePer = inTrainsDec.divide(baseCapacityDec, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).setScale(1, BigDecimal.ROUND_HALF_UP);
					val.setTrainingCapacityUsePer(capacityUsePer.toPlainString());
				}
			});
		}

		//查询一年的住院医师和在校专硕的人数
		Map<String, String> param = new HashMap<>();
		param.put("orgFlowList", String.join(",", allOrgFlowList));
		param.put("doctorType", "zyys");
		param.put("sessionNumber", sessionNumber);
		param.put("speIdList", String.join(",", speIdList));
        param.put("orgGroup", com.pinde.core.common.GlobalConstant.FLAG_Y);
		List<Map<String, Object>> zyysDoctorList = deptBasicInfoBiz.countDoctorNum(param);
		for (Map<String, Object> map : zyysDoctorList) {
			ResOrgSepVO resOrgSepVO = orgToOrgSpeVOMap.get(map.get("orgFlow"));
			if(resOrgSepVO == null) {
				continue;
			}
			if(!speIdToOrgSpeMap.containsKey((map.get("speId")))) {
				continue;
			}
			List<ResOrgSpe> resOrgSpeList = speIdToOrgSpeMap.get((map.get("speId")));
			List<String> tempOrgFlowList = resOrgSpeList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
			if(!tempOrgFlowList.contains(map.get("orgFlow"))) {
				continue;
			}
			resOrgSepVO.setCurInHospitalDoctors(((BigDecimal)map.get("num")).toPlainString());
		}
		param.put("doctorType", "zxzs");
		List<Map<String, Object>> zxzsDoctorList = deptBasicInfoBiz.countDoctorNum(param);
		for (Map<String, Object> map : zxzsDoctorList) {
			ResOrgSepVO resOrgSepVO = orgToOrgSpeVOMap.get(map.get("orgFlow"));
			if(resOrgSepVO == null) {
				continue;
			}
			if(!speIdToOrgSpeMap.containsKey((map.get("speId")))) {
				continue;
			}
			List<ResOrgSpe> resOrgSpeList = speIdToOrgSpeMap.get((map.get("speId")));
			List<String> tempOrgFlowList = resOrgSpeList.stream().map(vo -> vo.getOrgFlow()).collect(Collectors.toList());
			if(!tempOrgFlowList.contains(map.get("orgFlow"))) {
				continue;
			}
			resOrgSepVO.setCurInCollegeMasters(((BigDecimal)map.get("num")).toPlainString());
		}

		// 查一下sys_org表，把org_code添上
		SysOrgExample orgExample = new SysOrgExample();
        orgExample.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
				.andOrgFlowIn(allOrgFlowList);
		List<SysOrg> sysOrgList = orgBiz.searchOrgByExample(orgExample);
		Map<String, SysOrg> orgToEntityMap = sysOrgList.stream().collect(Collectors.toMap(vo -> vo.getOrgFlow(), vo -> vo, (vo1, vo2) -> vo1));

		List<ResOrgSepVO> resList = new ArrayList<>();
		mainToJointMap.forEach((key, val) -> {
			ResOrgSepVO mainOrgVO = orgToOrgSpeVOMap.get(key);
			if(null != mainOrgVO) {
				List<ResOrgSepVO> jointOrgList = new ArrayList<>();
				for(String jointFlow: val) {
					ResOrgSepVO jointOrgVO = orgToOrgSpeVOMap.get(jointFlow);
					if(null != jointOrgVO) {
						// 顺便把OrgCode添上,历史原因，放在baseCode里，少改一点前端代码
						jointOrgVO.setBaseCode(orgToEntityMap.getOrDefault(jointOrgVO.getOrgFlow(), new SysOrg()).getOrgCode());
						jointOrgVO.setOrgName(orgToEntityMap.getOrDefault(jointOrgVO.getOrgFlow(), new SysOrg()).getOrgName());
						jointOrgList.add(jointOrgVO);
					}
				}
				jointOrgList = jointOrgList.stream().sorted((vo1, vo2) -> {
					if(StringUtils.isNotEmpty(vo1.getBaseCode()) && StringUtils.isNotEmpty(vo2.getBaseCode())) {
						if(NumberUtils.isDigits(vo1.getBaseCode()) && NumberUtils.isDigits(vo2.getBaseCode())) {
							return Integer.compare(Integer.parseInt(vo1.getBaseCode()), Integer.parseInt(vo2.getBaseCode()));
						}
						return vo1.getBaseCode().compareTo(vo2.getBaseCode());
					}else if(StringUtils.isNotEmpty(vo1.getBaseCode())){
						return -1;
					}else if(StringUtils.isNotEmpty(vo2.getBaseCode())) {
						return 1;
					}else return 0;
				}).collect(Collectors.toList());
				mainOrgVO.setJointOrgList(jointOrgList);
				// 顺便把orgCode添上,历史原因，放在baseCode里，少改一点前端代码
				mainOrgVO.setBaseCode(orgToEntityMap.getOrDefault(mainOrgVO.getOrgFlow(), new SysOrg()).getOrgCode());
				mainOrgVO.setOrgName(orgToEntityMap.getOrDefault(mainOrgVO.getOrgFlow(), new SysOrg()).getOrgName());
				resList.add(mainOrgVO);
			}

		});

		List<ResOrgSepVO> orgSpeVOList = resList.stream().sorted((vo1, vo2) -> {
			if(StringUtils.isNotEmpty(vo1.getBaseCode()) && StringUtils.isNotEmpty(vo2.getBaseCode())) {
				if(NumberUtils.isDigits(vo1.getBaseCode()) && NumberUtils.isDigits(vo2.getBaseCode())) {
					return Integer.compare(Integer.parseInt(vo1.getBaseCode()), Integer.parseInt(vo2.getBaseCode()));
				}
				return vo1.getBaseCode().compareTo(vo2.getBaseCode());
			}else if(StringUtils.isNotEmpty(vo1.getBaseCode())){
				return -1;
			}else if(StringUtils.isNotEmpty(vo2.getBaseCode())) {
				return 1;
			}else return 0;
		}).collect(Collectors.toList());
		model.addAttribute("orgSpeList", orgSpeVOList);

		logger.info("[JsResBaseManagerController.profBaseOneDialog] end");
		return "/jsres/base/profBaseList/profBaseOneDialog";
	}

	@RequestMapping("profBaseInfoExport")
	public void exportInfo(String sessionNumber, String[] mainBase, String[] jointOrg, String[] profDept, HttpServletResponse response) throws IOException{
		List<ResOrgSepVO> data = getProfBaseData(sessionNumber, mainBase, jointOrg, profDept);

		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		String sheetName = "专业基地清单(" + sessionNumber + "年)";
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		Font headerFont = wb.createFont();
		headerFont.setBold(true);
//		headerFont.setBoldweight((short) 17);
		stylevwc.setFont(headerFont);

		List<String> titleList=new ArrayList<>();
		boolean curYear = DateUtil.getYear().equals(sessionNumber);
		titleList.add("专业基地编码");
		titleList.add("专业基地名称");
		titleList.add("住院医师(" + sessionNumber + ")在培");
		titleList.add("在校专硕(" + sessionNumber + "在培)");
		if(curYear) {
			titleList.add("住院医师(在培)");
			titleList.add("在校专硕(在培)");
			titleList.add("在培人员总数");
			titleList.add("培训总容量");
			titleList.add("最小培训容量");
			titleList.add("容量使用率");
			titleList.add("总数");
		}
		String[] titles=new String[titleList.size()];
		titleList.toArray(titles);
		//列宽自适应
		HSSFRow rowDep = sheet.createRow(0);
		rowDep.setHeightInPoints(20);

		HSSFCell cellTitle = null;
		for(int i = 0 ; i<titles.length ; i++){
			cellTitle = rowDep.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(stylevwc);
			sheet.setDefaultColumnWidth(20);
		}

		int rownum = 1;
		if(data!=null&&!data.isEmpty()){
			for(ResOrgSepVO resOrgSpe : data){
				int columnNum = 0;
				HSSFRow rowDepts= sheet.createRow(rownum++);
				HSSFCell cell = rowDepts.createCell(columnNum++);//序号
				cell.setCellValue(resOrgSpe.getSpeId());
				cell.setCellStyle(styleCenter);
				HSSFCell cell2 = rowDepts.createCell(columnNum++);
				cell2.setCellValue(resOrgSpe.getSpeName());
				cell2.setCellStyle(styleCenter);
				HSSFCell cell3 = rowDepts.createCell(columnNum++);
				cell3.setCellValue(StringUtil.defaultIfEmpty(resOrgSpe.getCurInHospitalDoctors(), "0"));
				cell3.setCellStyle(styleCenter);
				HSSFCell cell4 = rowDepts.createCell(columnNum++);
				cell4.setCellValue(StringUtil.defaultIfEmpty(resOrgSpe.getCurInCollegeMasters(), "0"));
				cell4.setCellStyle(styleCenter);
				if(curYear) {
					HSSFCell cell61 = rowDepts.createCell(columnNum++);
					cell61.setCellValue(StringUtil.defaultIfEmpty(resOrgSpe.getInHospitalDoctors(), "0"));
					cell61.setCellStyle(styleCenter);
					HSSFCell cell62 = rowDepts.createCell(columnNum++);
					cell62.setCellValue(StringUtil.defaultIfEmpty(resOrgSpe.getInCollegeMasters(), "0"));
					cell62.setCellStyle(styleCenter);
					HSSFCell cell6 = rowDepts.createCell(columnNum++);
					cell6.setCellValue(StringUtil.defaultIfEmpty(resOrgSpe.getInTrains(), "0"));
					cell6.setCellStyle(styleCenter);
					HSSFCell cell7 = rowDepts.createCell(columnNum++);
					cell7.setCellValue(StringUtil.defaultIfEmpty(resOrgSpe.getBaseCapacity(), "0"));
					cell7.setCellStyle(styleCenter);
					HSSFCell cell8 = rowDepts.createCell(columnNum++);
					cell8.setCellValue(StringUtil.defaultIfEmpty(resOrgSpe.getMinRecruitCapacity(), "0"));
					cell8.setCellStyle(styleCenter);
					HSSFCell cell9 = rowDepts.createCell(columnNum++);
					cell9.setCellValue(StringUtil.defaultIfEmpty(resOrgSpe.getTrainingCapacityUsePer(), "0") + "%");
					cell9.setCellStyle(styleCenter);
					HSSFCell cell10 = rowDepts.createCell(columnNum++);
					cell10.setCellValue(StringUtil.defaultIfEmpty(resOrgSpe.getOpenSpeBases(), "0"));
					cell10.setCellStyle(styleCenter);
				}
			}
		}

		String fileName = "专业基地清单信息(" + sessionNumber + "年).xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}
}
