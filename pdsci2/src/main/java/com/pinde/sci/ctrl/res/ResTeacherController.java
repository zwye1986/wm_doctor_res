package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.*;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.*;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.IrbSingleForm;
import com.pinde.sci.dao.base.SysUserDeptMapper;
import com.pinde.sci.dao.sys.SysOrgExtMapper;
import com.pinde.sci.form.jszy.BaseUserResumeExtInfoForm;
import com.pinde.sci.form.res.ResAssessCfgItemForm;
import com.pinde.sci.form.res.ResAssessCfgTitleForm;
import com.pinde.sci.form.sch.SchGradeFrom;
import com.pinde.sci.model.mo.JsresAttendance;
import com.pinde.sci.model.mo.JsresAttendanceDetail;
import com.pinde.sci.model.mo.PubFile;
import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.ResAppeal;
import com.pinde.sci.model.mo.ResAssessCfg;
import com.pinde.sci.model.mo.ResDiscipleInfo;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.ResRec;
import com.pinde.sci.model.mo.ResScore;
import com.pinde.sci.model.mo.ResUserSpe;
import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SchDeptExternalRel;
import com.pinde.sci.model.mo.SchDoctorAbsence;
import com.pinde.sci.model.mo.SchDoctorDept;
import com.pinde.sci.model.mo.SchRotation;
import com.pinde.sci.model.mo.SchRotationDept;
import com.pinde.sci.model.mo.SysCfg;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUserRole;
import com.pinde.sci.model.res.ResDoctorExt;
import com.pinde.sci.model.res.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;

@Controller
@RequestMapping("/res/teacher")
public class ResTeacherController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResTeacherController.class);
	private static final String GZZY_ORG_FLOW="5cb53b872c38457a8e2a798d6c4d002f";

	@Autowired
	private ISchArrangeBiz arrangeBiz;
	@Autowired
	private IResRecBiz resRecBiz;
	@Autowired
	private IResSchProcessExpressBiz expressBiz;
	@Autowired
	private IResPowerCfgBiz resPowerCfgBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IResDoctorProcessBiz doctorProcessBiz;
	@Autowired
	private ISchArrangeResultBiz resultBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IResAssessCfgBiz assessCfgBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private ISchDoctorAbsenceBiz schDoctorAbsenceBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResUserSpeBiz resUserSpeBiz;
	@Autowired
	private SysUserDeptMapper sysUserDeptMapper;
	@Autowired
	private IResSignInBiz signBiz;
	@Autowired
	private ISchDoctorDeptBiz doctorDeptBiz;
	@Autowired
	private IUserRoleBiz userRoleBiz;
	@Autowired
	private ISchRotationBiz schRotationtBiz;
	@Autowired
	private IResScoreBiz resScoreBiz;
	@Autowired
	private IResAttendanceBiz resAttendanceBiz;
	@Autowired
	private IResGradeBiz resGradeBiz;
	@Autowired
	private ISchArrangeResultBiz schArrangeResultBiz;
	@Autowired
	private SysOrgExtMapper orgExtMapper;
	@Autowired
	private IResDiscipleInfoBiz resDiscipleInfoBiz;
	@Autowired
	private IPubUserResumeBiz userResumeBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExtRelBiz;

	@RequestMapping(value={"/saveAfterFile"})
	@ResponseBody
	public Object saveAfterFile(MultipartFile file,String resultFlow) throws Exception{
		Map<String,String> map=new HashMap<>();
		String result = fileBiz.addAfterFile(file, "resAfterFile", "");
		if(result != "fail"){
			SchArrangeResult result1=schArrangeResultBiz.readSchArrangeResult(resultFlow);
			if(result1!=null)
			{
				result1.setAfterFileFlow(result);
				int l=schArrangeResultBiz.saveSchArrangeResult(result1);
				if(l==1)
				{
                    map.put("result", com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED);
					map.put("fileFlow",result);
				}
			}
		}else{
            map.put("result", com.pinde.core.common.GlobalConstant.UPLOAD_FAIL);
			map.put("fileFlow",result);
		}
		return map;
	}
	@RequestMapping(value={"/saveSkillFile"})
	@ResponseBody
	public Object saveSkillFile(MultipartFile file,String resultFlow) throws Exception{
		Map<String,String> map=new HashMap<>();
		String result = fileBiz.addAfterFile(file,"SkillFile",resultFlow);
		if(result != "fail"){
			PubFile pubFile=fileBiz.readFile(result);
			map.put("fileName",pubFile.getFileName());
			map.put("filePath",pubFile.getFilePath());
			map.put("createTime",DateUtil.transDateTime(pubFile.getCreateTime()));
			map.put("fileFlow",result);
            map.put("result", com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED);
		}else{
            map.put("result", com.pinde.core.common.GlobalConstant.UPLOAD_FAIL);
			map.put("fileFlow",result);
		}
		return map;
	}
	@RequestMapping(value={"/delAfterFile"})
	@ResponseBody
	public Object delAfterFile(String fileFlow,String resultFlow) throws Exception{
		SchArrangeResult result1=schArrangeResultBiz.readSchArrangeResult(resultFlow);
		if(result1!=null)
		{
			fileBiz.deleteAfterFile(fileFlow);
			result1.setAfterFileFlow("");
			int l=schArrangeResultBiz.editSchArrangeResult(result1);
			if(l==1)
			{
                return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}
	@RequestMapping(value={"/delSkillFile"})
	@ResponseBody
	public Object delSkillFile(String fileFlow) throws Exception {
		int c = fileBiz.deleteAfterFile(fileFlow);
		if (c != 0)
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;

        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
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
            /*设置页面编码为UTF-8*/
			response.setHeader("Content-Type","text/html;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write("<a href='javascript:history.go(-1)'>未发现文件,点击返回上一页</a>".getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}
	/**
	 *  显示概况
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showView/{roleFlag}")
	public String showView(@PathVariable String roleFlag,/*String schDeptFlow,*/Model model,String doctorName,
						   String isCurrentFlag,Integer currentPage,HttpServletRequest request,String [] datas){
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
//		Map<String,BigDecimal> countMap = this.resRecBiz.searchAuditCount(GlobalContext.getCurrentUser().getUserFlow(), roleFlag);
//		model.addAttribute("countMap", countMap);
		model.addAttribute("roleFlag", roleFlag);
//
//		ResRec rec = new ResRec();
//		rec.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
//		//rec.setStatusId(RecStatusEnum.Submit.getId());
//		ResDoctorSchProcess process = new ResDoctorSchProcess();
//		process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
//		List<ResRecExt> recExtList = this.resRecBiz.searchRegistryList(GlobalContext.getCurrentUser().getUserFlow(), roleFlag,rec,process);
//		model.addAttribute("recExtList",recExtList);

		SysUser user = GlobalContext.getCurrentUser();

		if(user!=null && StringUtil.isNotBlank(user.getDeptFlow())){
			//当前带教老师轮转科室列表
			List<String> schDeptFlows = new ArrayList<String>();
			List<SysUserDept> userDeptList = userBiz.searchUserDeptByUser(user.getUserFlow());
			if(userDeptList!=null && userDeptList.size()>0){
				List<String> deptFlows = new ArrayList<String>();
				for(SysUserDept sud : userDeptList){
					deptFlows.add(sud.getDeptFlow());
				}
				List<SchDept> schDeptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
				if(schDeptList!=null && schDeptList.size()>0){
					for(SchDept sd : schDeptList){
						schDeptFlows.add(sd.getSchDeptFlow());
					}
				}
			}

				//当前轮转医师
				ResDoctorSchProcess process = new ResDoctorSchProcess();

				List<ResDoctorSchProcess> processList = null;

            if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
					process.setTeacherUserFlow(user.getUserFlow());
					process.setIsCurrentFlag(isCurrentFlag);
					List<String> recTypeIds = new ArrayList<String>();

					for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
							recTypeIds.add(regType.getId());
						}
					}

					if(recTypeIds.size()>0){
						//是否存在待审核数据
                        List<Map<String, Object>> waitAuditMapList = resRecBiz.searchDoctorNotAuditCount(/*schDeptFlow*/null, user.getUserFlow(), com.pinde.core.common.GlobalConstant.FLAG_N, recTypeIds);
						if(waitAuditMapList!=null && waitAuditMapList.size()>0){
							Map<String,Object> waitAuditMap = new HashMap<String, Object>();
							for(Map<String,Object> map : waitAuditMapList){
								waitAuditMap.put((String)map.get("recKey"),map.get("finishCount"));
							}
							model.addAttribute("waitAuditMap",waitAuditMap);
						}
					}

                List<Map<String, Object>> waitAuditAppealMapList = resRecBiz.searchNotAuditAppealCount(/*schDeptFlow*/null, user.getUserFlow(), com.pinde.core.common.GlobalConstant.FLAG_N);
					if(waitAuditAppealMapList!=null && waitAuditAppealMapList.size()>0){
						Map<String,Object> waitAuditAppealMap = new HashMap<String, Object>();
						for(Map<String,Object> map : waitAuditAppealMapList){
							waitAuditAppealMap.put((String)map.get("appealKey"),map.get("appealSum"));
						}
						model.addAttribute("waitAuditAppealMap",waitAuditAppealMap);
					}
					//权限期间是否开通
					String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
					PageHelper.startPage(currentPage,getPageSize(request));

					if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
						Map<String,Object> param = new HashMap<>();
						param.put("isOpen",isOpen);
						param.put("doctorName",doctorName);
						param.put("doctorProcess",process);
						param.put("docTypeList",docTypeList);
						processList = doctorProcessBiz.searchDoctorProcess(param);//带教审核数据
					}else{
						processList = doctorProcessBiz.searchDoctorProcess(isOpen,doctorName,process);//带教审核数据
					}


            } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
					process.setHeadUserFlow(user.getUserFlow());
					//带教老师审核情况
                List<Map<String, Object>> auditMapList = resRecBiz.searchTeacherAuditCount(user.getUserFlow(), com.pinde.core.common.GlobalConstant.FLAG_Y);
                List<Map<String, Object>> notAuditMapList = resRecBiz.searchTeacherAuditCount(user.getUserFlow(), com.pinde.core.common.GlobalConstant.FLAG_N);
					List<String> teacherFlows = new ArrayList<String>();
					if(auditMapList!=null && auditMapList.size()>0){
						Map<String,Map<String,Object>> auditMap = new HashMap<String, Map<String,Object>>();
						for(Map<String,Object> map : auditMapList){
							String teacherFlow = (String)map.get("teacherFlow");
							if(!teacherFlows.contains(teacherFlow)){
								teacherFlows.add(teacherFlow);
							}
							auditMap.put(teacherFlow,map);
						}
						model.addAttribute("auditMap",auditMap);
					}
					if(notAuditMapList!=null && notAuditMapList.size()>0){
						Map<String,Map<String,Object>> notAuditMap = new HashMap<String, Map<String,Object>>();
						for(Map<String,Object> map : notAuditMapList){
							String teacherFlow = (String)map.get("teacherFlow");
							if(!teacherFlows.contains(teacherFlow)){
								teacherFlows.add(teacherFlow);
							}
							notAuditMap.put(teacherFlow,map);
						}
						model.addAttribute("notAuditMap",notAuditMap);
					}
					model.addAttribute("teacherFlows",teacherFlows);

					//待入科查询
					List<SchArrangeResult> resultList = resultBiz.willInDoctor(user.getOrgFlow(),user.getUserFlow());
					if(resultList!=null && resultList.size()>0){
						List<String> doctorFlows = new ArrayList<String>();
						List<SchArrangeResult> willInResult = new ArrayList<SchArrangeResult>();
						for(SchArrangeResult result : resultList){
							if(schDeptFlows.contains(result.getSchDeptFlow())){
								if(!doctorFlows.contains(result.getDoctorFlow())){
									doctorFlows.add(result.getDoctorFlow());
									willInResult.add(result);
								}
							}
						}
						if(willInResult!=null && willInResult.size()>0){
							model.addAttribute("willInResult",willInResult);

							List<String> userFlows = new ArrayList<String>();
							for(SchArrangeResult sar : willInResult){
								userFlows.add(sar.getDoctorFlow());
							}

							List<SysUser> willInUserList = userBiz.searchSysUserByuserFlows(userFlows);
							if(willInUserList!=null && willInUserList.size()>0){
								Map<String,SysUser> willInUserMap = new HashMap<String, SysUser>();
								for(SysUser su : willInUserList){
									willInUserMap.put(su.getUserFlow(),su);
								}
								model.addAttribute("willInUserMap",willInUserMap);
							}

							List<ResDoctor> willInDoctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
							if(willInDoctorList!=null && willInDoctorList.size()>0){
								Map<String,ResDoctor> willInDoctorMap = new HashMap<String, ResDoctor>();
								for(ResDoctor rd : willInDoctorList){
									willInDoctorMap.put(rd.getDoctorFlow(),rd);
								}
								model.addAttribute("willInDoctorMap",willInDoctorMap);
							}

//							List<ResDoctorSchProcess> isRotationProcess = doctorProcessBiz.searchCurrProcessByUserFlows(userFlows);
//							if(isRotationProcess!=null && isRotationProcess.size()>0){
//								Map<String,ResDoctorSchProcess> isRotationProcessMap = new HashMap<String, ResDoctorSchProcess>();
//								for(ResDoctorSchProcess rdsp : isRotationProcess){
//									isRotationProcessMap.put(rdsp.getUserFlow(),rdsp);
//								}
//								model.addAttribute("isRotationProcessMap",isRotationProcessMap);
//							}

						}
					}

					//processList = doctorProcessBiz.searchDoctorProcess(process);
					//权限期间是否开通
					String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
					processList = doctorProcessBiz.searchCurrentProcessByUserFlow(isOpen,user.getUserFlow(),process.getIsCurrentFlag());


            } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)) {
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
					processList = doctorProcessBiz.searchProcessByUserSpe(user.getUserFlow(),process.getIsCurrentFlag());
				}


				if(processList!=null && processList.size()>0){
					model.addAttribute("processList",processList);

					List<SchArrangeResult> resultList = new ArrayList<SchArrangeResult>();
					Map<String,String> finishPreMap = new HashMap<String, String>();

					List<String> userFlows = new ArrayList<String>();
					Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
					Map<String,SchArrangeResult> resultMapMap = new HashMap<String, SchArrangeResult>();
					Map<String,PubFile> fileMap = new HashMap<String, PubFile>();
					for(ResDoctorSchProcess processTemp : processList){
						userFlows.add(processTemp.getUserFlow());

						//出科小结情况
                        List<ResSchProcessExpress> afterList = expressBiz.searchByProcessFlowAndRecTypeIdClob(processTemp.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());

						if(afterList!=null && afterList.size()>0){
							Map<String,ResSchProcessExpress> afterMap = new HashMap<String, ResSchProcessExpress>();
							for(ResSchProcessExpress rec : afterList){
								afterMap.put(rec.getProcessFlow(),rec);
							}
							model.addAttribute("afterMap",afterMap);
						}

						//出科成绩
                        List<ResSchProcessExpress> evaluationList = expressBiz.searchByProcessFlowAndRecTypeIdClob(processTemp.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());

						if(evaluationList!=null && evaluationList.size()>0){
							Map<String,String> evaluationMap = new HashMap<String, String>();
							Map<String, ResSchProcessExpress> recMap = new HashMap<String, ResSchProcessExpress>();
							for(ResSchProcessExpress rec : evaluationList){
								Map<String,Object> scoreMap = resRecBiz.parseRecContent(rec.getRecContent());
								if(scoreMap!=null){
									evaluationMap.put(rec.getOperUserFlow()+rec.getSchDeptFlow(), (String) scoreMap.get("totalScore"));
									recMap.put(rec.getProcessFlow(),rec);
								}
							}
							model.addAttribute("evaluationMap", evaluationMap);
							model.addAttribute("recMap", recMap);
						}
						//计算登记百分比
//						resultList.clear();
                        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
							String resultFlow = processTemp.getSchResultFlow();
							SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
							resultMapMap.put(processTemp.getProcessFlow(), result);
							if (result != null) {
								PubFile file = fileBiz.readFile(result.getAfterFileFlow());
								fileMap.put(processTemp.getProcessFlow(), file);
							}
						}
//						if(result!=null){
//							resultList.add(result);
//							Map<String,String> finishPre = resRecBiz.getFinishPer(resultList,processTemp.getUserFlow());
//							if(finishPre!=null){
//								finishPreMap.put(processTemp.getUserFlow(),finishPre.get(resultFlow));
//							}
//						}

						//轮转中记录
						processMap.put(processTemp.getUserFlow(),processTemp);
					}
					model.addAttribute("processMap",processMap);
					model.addAttribute("resultMap",resultMapMap);
					model.addAttribute("fileMap",fileMap);
					model.addAttribute("finishPreMap",finishPreMap);

					List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
					if(doctorList!=null && doctorList.size()>0){
						model.addAttribute("doctorList",doctorList);

						Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
						Map<String,Integer> categoryNum = new HashMap<String, Integer>();
						for(ResDoctor doctor : doctorList){
							SysUser sysUser = userBiz.readSysUser(doctor.getDoctorFlow());
							doctor.setDoctorName(sysUser.getUserName());
							String key = doctor.getDoctorCategoryName();
							if(categoryNum.get(key)==null){
								categoryNum.put(key,1);
							}else{
								categoryNum.put(key,categoryNum.get(key)+1);
							}
							doctorMap.put(doctor.getDoctorFlow(),doctor);
						}
						model.addAttribute("doctorMap",doctorMap);
						model.addAttribute("categoryNum",categoryNum);

						//读取用户信息
						List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
						if(userList!=null && userList.size()>0){
							Map<String,SysUser> userMap = new HashMap<String, SysUser>();
							for(SysUser su : userList){
								userMap.put(su.getUserFlow(),su);
							}
							model.addAttribute("userMap",userMap);
						}
					}


				}

				//已出科医师
				process.setIsCurrentFlag(null);
            process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
				List<ResDoctorSchProcess> afterProcessList = null;
				//权限期间是否开通
				String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
				afterProcessList = doctorProcessBiz.searchDoctorProcess(isOpen,doctorName,process);

			if(afterProcessList!=null && afterProcessList.size()>0){
					List<String> userFlows = new ArrayList<String>();
					for(ResDoctorSchProcess processTemp : afterProcessList){
						userFlows.add(processTemp.getUserFlow());
					}

					List<ResDoctor> afterDoctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
					model.addAttribute("afterDoctorList",afterDoctorList);
				}
				String currDate = DateUtil.getCurrDate();
				currDate = DateUtil.newDateOfAddMonths(currDate,1);

				List<ResDoctor> willInDoctorList = doctorBiz.searchMonthRotationDoctor(/*schDeptFlow*/null,currDate.substring(0,7));
				model.addAttribute("willInDoctorList",willInDoctorList);
			}
//		}

        isCurrentFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		model.addAttribute("isCurrentFlag",isCurrentFlag);
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			return "res/teacher/view4jszy";
		}
		return "res/teacher/view";
	}

	/**
	 * 科室学员入科功能
	 * @param roleFlag
	 * @param model
	 * @param doctorName
	 * @param isCurrentFlag
	 * @param currentPage
     * @param request
     * @return
     */
	@RequestMapping(value="/inDeptView/{roleFlag}")
	public String inDeptView(@PathVariable String roleFlag, Model model,String doctorName,
						   String isCurrentFlag,Integer currentPage,HttpServletRequest request,String [] datas) {
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if (datas != null && datas.length > 0) {
			docTypeList = Arrays.asList(datas);
			for (String d : datas) {
				dataStr += d + ",";
			}
		}
		model.addAttribute("dataStr", dataStr);
		model.addAttribute("roleFlag", roleFlag);
		SysUser user = GlobalContext.getCurrentUser();
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		//待入科查询
		Map<String, Object> param = new HashMap<>();
		param.put("userFlow", user.getUserFlow());
		param.put("deptFlow", user.getDeptFlow());
		param.put("isCurrentFlag", isCurrentFlag);
		param.put("doctorName", doctorName);
		param.put("isOpen", isOpen);
		param.put("docTypeList", docTypeList);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SchArrangeResult> resultList = resultBiz.willInDoctor(param);
		if(resultList!=null)
		{
			List<String> userFlows=new ArrayList<>();
			for(SchArrangeResult r:resultList)
			{
				if(!userFlows.contains(r.getDoctorFlow()))
				{
					userFlows.add(r.getDoctorFlow());
				}
			}
			if(userFlows.size()>0)
			{
				List<SysUser> willInUserList = userBiz.searchSysUserByuserFlows(userFlows);
				if(willInUserList!=null && willInUserList.size()>0){
					Map<String,SysUser> willInUserMap = new HashMap<String, SysUser>();
					for(SysUser su : willInUserList){
						willInUserMap.put(su.getUserFlow(),su);
					}
					model.addAttribute("willInUserMap",willInUserMap);
				}
				List<ResDoctor> willInDoctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
				if(willInDoctorList!=null && willInDoctorList.size()>0){
					Map<String,ResDoctor> willInDoctorMap = new HashMap<String, ResDoctor>();
					for(ResDoctor rd : willInDoctorList){
						willInDoctorMap.put(rd.getDoctorFlow(),rd);
					}
					model.addAttribute("willInDoctorMap",willInDoctorMap);
				}
				List<ResDiscipleInfo> resDiscipleInfos = resDiscipleInfoBiz.readResDiscipleInfos(userFlows);
				if(resDiscipleInfos!=null&&resDiscipleInfos.size()>0){
					Map<String,ResDiscipleInfo> resDiscipleInfoMap = new HashMap<String, ResDiscipleInfo>();
					for(ResDiscipleInfo resDiscipleInfo : resDiscipleInfos){
						resDiscipleInfoMap.put(resDiscipleInfo.getDoctorFlow(),resDiscipleInfo);
					}
					model.addAttribute("resDiscipleInfoMap",resDiscipleInfoMap);
				}
			}
		}
		model.addAttribute("resultList",resultList);
		return "res/teacher/inDeptView";
	}
	/**
	 *  出科成绩查询
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/outDeptView/{role}")
	public String outDeptView(Model model,@PathVariable String role,String doctorName,String[] doctorTypeIdList,
							  String trainingYears,
								String trainingSpeId,String sessionNumber,String isCurrentFlag,
								String startDate,String endDate,Integer currentPage,HttpServletRequest request) {
		SysUser user = GlobalContext.getCurrentUser();

		model.addAttribute("role",role);
		List<String> typeList = null;
		//复选框勾选标识
		Map<String, String> doctorTypeSelectMap = new HashMap<>();
		SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if (doctorTypeIdList != null && doctorTypeIdList.length > 0) {
			typeList = Arrays.asList(doctorTypeIdList);
			for (SysDict dict : dictList) {
				if (typeList.contains(dict.getDictId())) {
					doctorTypeSelectMap.put(dict.getDictId(), "checked");
				}
			}
		}
		if (doctorTypeIdList == null) {
			typeList = new ArrayList<>();
			for (SysDict dict : dictList) {
				typeList.add(dict.getDictId());
				doctorTypeSelectMap.put(dict.getDictId(), "checked");
			}
		}
		model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("role", role);
		paramMap.put("isOpen", isOpen);
		paramMap.put("doctorName", doctorName);
		paramMap.put("trainingSpeId", trainingSpeId);
		paramMap.put("sessionNumber", sessionNumber);
		paramMap.put("isCurrentFlag", isCurrentFlag);
		paramMap.put("trainingYears", trainingYears);
		paramMap.put("typeList", typeList);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("userFlow", user.getUserFlow());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> processList = doctorProcessBiz.readHeadOrTeaStudents(paramMap);
		model.addAttribute("processList", processList);
		if (processList != null && processList.size() > 0) {

			Map<String,ResSchProcessExpress> afterMap = new HashMap<String, ResSchProcessExpress>();
			Map<String, ResSchProcessExpress> recMap = new HashMap<String, ResSchProcessExpress>();
			Map<String, ResScore> schScoreMap = new HashMap<String, ResScore>();
			for (Map<String,Object> processTemp : processList) {
				String processFlow= (String) processTemp.get("processFlow");
				String userFlow= (String) processTemp.get("userFlow");
				if(StringUtil.isNotBlank(processFlow))
				{
                    ResSchProcessExpress after = expressBiz.queryResRecByProcessAndType(processFlow, com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
					afterMap.put(processFlow,after);
                    ResSchProcessExpress evl = expressBiz.queryResRecByProcessAndType(processFlow, com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
					recMap.put(processFlow,evl);
					ResScore score=resScoreBiz.getScoreByProcess(processFlow);
					schScoreMap.put(processFlow,score);

				}
			}

			model.addAttribute("schScoreMap", schScoreMap);
			model.addAttribute("afterMap", afterMap);
			model.addAttribute("recMap", recMap);
		}
		return "res/teacher/outDeptView";
	}

	/**
	 * 上传技能打分
	 * @param model
	 * @param role
	 * @param doctorName
	 * @param doctorTypeIdList
	 * @param trainingYears
	 * @param trainingSpeId
	 * @param sessionNumber
	 * @param isCurrentFlag
	 * @param startDate
	 * @param endDate
     * @param currentPage
     * @param request
     * @return
     */
	@RequestMapping(value="/doctorList/{role}")
	public String doctorList(Model model,@PathVariable String role,String doctorName,String[] doctorTypeIdList,
							  String trainingYears,
								String trainingSpeId,String sessionNumber,String isCurrentFlag,
								String startDate,String endDate,Integer currentPage,HttpServletRequest request) {
		SysUser user = GlobalContext.getCurrentUser();
		model.addAttribute("role",role);
		List<String> typeList = null;
		//复选框勾选标识
		Map<String, String> doctorTypeSelectMap = new HashMap<>();
		SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if (doctorTypeIdList != null && doctorTypeIdList.length > 0) {
			typeList = Arrays.asList(doctorTypeIdList);
			for (SysDict dict : dictList) {
				if (typeList.contains(dict.getDictId())) {
					doctorTypeSelectMap.put(dict.getDictId(), "checked");
				}
			}
		}
		if (doctorTypeIdList == null) {
			typeList = new ArrayList<>();
			for (SysDict dict : dictList) {
				typeList.add(dict.getDictId());
				doctorTypeSelectMap.put(dict.getDictId(), "checked");
			}
		}
		model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("role", role);
		paramMap.put("isOpen", isOpen);
		paramMap.put("doctorName", doctorName);
		paramMap.put("trainingSpeId", trainingSpeId);
		paramMap.put("sessionNumber", sessionNumber);
		paramMap.put("isCurrentFlag", isCurrentFlag);
		paramMap.put("trainingYears", trainingYears);
		paramMap.put("typeList", typeList);
		paramMap.put("startDate", startDate);
		paramMap.put("endDate", endDate);
		paramMap.put("userFlow", user.getUserFlow());
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> processList = doctorProcessBiz.readHeadOrTeaStudents(paramMap);
		model.addAttribute("processList", processList);
		if (processList != null && processList.size() > 0) {
			Map<String,ResSchProcessExpress> afterMap = new HashMap<String, ResSchProcessExpress>();
			Map<String, ResSchProcessExpress> recMap = new HashMap<String, ResSchProcessExpress>();
			Map<String, ResScore> schScoreMap = new HashMap<String, ResScore>();
			Map<String, ResScore> scoreMapNew = new HashMap<String, ResScore>();
			for (Map<String,Object> processTemp : processList) {
				String processFlow= (String) processTemp.get("processFlow");
				String userFlow= (String) processTemp.get("userFlow");
				String resultFlow= (String) processTemp.get("resultFlow");
				if(StringUtil.isNotBlank(processFlow))
				{
                    ResSchProcessExpress after = expressBiz.queryResRecByProcessAndType(processFlow, com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
					afterMap.put(processFlow,after);
                    ResSchProcessExpress evl = expressBiz.queryResRecByProcessAndType(processFlow, com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
					recMap.put(processFlow,evl);
					ResScore score=resScoreBiz.getScoreByProcess(processFlow);
					schScoreMap.put(processFlow,score);
					//技能上传分数
					ResScore scoreNew=resScoreBiz.getScoreByResult(resultFlow);
					scoreMapNew.put(resultFlow,scoreNew);
				}
			}

			model.addAttribute("schScoreMap", schScoreMap);
			model.addAttribute("scoreMapNew", scoreMapNew);
			model.addAttribute("afterMap", afterMap);
			model.addAttribute("recMap", recMap);
		}
		return "res/teacher/doctorList";
	}

	@RequestMapping(value="/showUpolad")
	public String showUpolad(Model model,String resultFlow,String isAdmin,HttpServletRequest request) {
		List<PubFile> list=fileBiz.findFileByLikeTypeFlow("SkillFile",resultFlow);
		model.addAttribute("list",list);
		model.addAttribute("isAdmin",isAdmin);
		return "res/teacher/showUpolad";
	}

	/**
	 *  显示概况
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showView4head")
	public String showView4head(Model model,String doctorName,String[] doctorTypeIdList,String workDeptName,String trainingYears,
								String trainingSpeId,String sessionNumber,String isCurrentFlag,
								String startDate,String endDate,Integer currentPage,HttpServletRequest request) {
		SysUser user = GlobalContext.getCurrentUser();
		if (user != null && StringUtil.isNotBlank(user.getDeptFlow())) {
			List<String> typeList = null;
			//复选框勾选标识
			Map<String, String> doctorTypeSelectMap = new HashMap<>();
			SysDict sysDict = new SysDict();
            sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
            sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			List<SysDict> dictList = dictBiz.searchDictList(sysDict);
			if (doctorTypeIdList != null && doctorTypeIdList.length > 0) {
				typeList = Arrays.asList(doctorTypeIdList);
				for (SysDict dict : dictList) {
					if (typeList.contains(dict.getDictId())) {
						doctorTypeSelectMap.put(dict.getDictId(), "checked");
					}
				}
			}
			if (doctorTypeIdList == null) {
				typeList = new ArrayList<>();
				for (SysDict dict : dictList) {
					typeList.add(dict.getDictId());
					doctorTypeSelectMap.put(dict.getDictId(), "checked");
				}
			}
			model.addAttribute("doctorTypeSelectMap", doctorTypeSelectMap);
			//权限期间是否开通
			String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("doctorName", doctorName);
			paramMap.put("trainingSpeId", trainingSpeId);
			paramMap.put("sessionNumber", sessionNumber);
			paramMap.put("isCurrentFlag", isCurrentFlag);
			paramMap.put("trainingYears", trainingYears);
			paramMap.put("workDeptName", workDeptName);
			paramMap.put("typeList", typeList);
			paramMap.put("startDate", startDate);
			paramMap.put("endDate", endDate);
			paramMap.put("isOpen", isOpen);
			paramMap.put("deptFlow", user.getDeptFlow());
			paramMap.put("userFlow", user.getUserFlow());
			PageHelper.startPage(currentPage, getPageSize(request));
			List<SchArrangeResult> resultList=doctorProcessBiz.searchResultsByUserFlow(paramMap);

			Map<String, ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
			Map<String, ResSchProcessExpress> afterMap = new HashMap<String, ResSchProcessExpress>();
			Map<String, PubFile> fileMap = new HashMap<String, PubFile>();
			Map<String, ResSchProcessExpress> recMap = new HashMap<String, ResSchProcessExpress>();
			if (resultList != null && resultList.size() > 0) {
				List<String> userFlows=new ArrayList<>();
				for(SchArrangeResult r:resultList)
				{
					if(!userFlows.contains(r.getDoctorFlow()))
					{
						userFlows.add(r.getDoctorFlow());
					}

					if (r != null) {
						PubFile file = fileBiz.readFile(r.getAfterFileFlow());
						fileMap.put(r.getResultFlow(), file);
					}
					ResDoctorSchProcess process=doctorProcessBiz.searchByResultFlow(r.getResultFlow());
					if(process!=null)
					{
						processMap.put(r.getResultFlow(),process);
						String processFlow=process.getProcessFlow();
                        ResSchProcessExpress after = expressBiz.queryResRecByProcessAndType(processFlow, com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
						afterMap.put(processFlow,after);
                        ResSchProcessExpress evl = expressBiz.queryResRecByProcessAndType(processFlow, com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
						recMap.put(processFlow,evl);
					}
				}
				if(userFlows.size()>0)
				{
					List<SysUser> willInUserList = userBiz.searchSysUserByuserFlows(userFlows);
					if(willInUserList!=null && willInUserList.size()>0){
						Map<String,SysUser> userMap = new HashMap<String, SysUser>();
						for(SysUser su : willInUserList){
							userMap.put(su.getUserFlow(),su);
						}
						model.addAttribute("userMap",userMap);
					}
					List<ResDoctor> willInDoctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
					if(willInDoctorList!=null && willInDoctorList.size()>0){
						Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
						for(ResDoctor rd : willInDoctorList){
							doctorMap.put(rd.getDoctorFlow(),rd);
						}
						model.addAttribute("doctorMap",doctorMap);
					}
				}
			}
			model.addAttribute("fileMap", fileMap);
			model.addAttribute("recMap", recMap);
			model.addAttribute("afterMap", afterMap);
			model.addAttribute("resultList", resultList);
			model.addAttribute("processMap", processMap);
		}
		return "res/teacher/view4head";
	}

	/**
	 * 修改带教
	 */
	@RequestMapping(value = "/showChooseTea", method = RequestMethod.GET)
	public String showChooseTea(String schDeptFlow,String teacherUserFlow,String resultFlow, Model model) {
		if (StringUtil.isNotBlank(schDeptFlow)) {
			SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
			if (dept != null) {
				String deptFlow = dept.getDeptFlow();
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(dept.getIsExternal())) {
					SchDeptExternalRel deptExtRel = deptExtRelBiz.readSchDeptExtRelBySchDept(schDeptFlow);
					if (deptExtRel != null) {
						deptFlow = deptExtRel.getRelDeptFlow();
					}
				}

				if (StringUtil.isNotBlank(deptFlow)) {
					String teacherRoleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
					if (StringUtil.isNotBlank(teacherRoleFlow)) {
						List<SysUser> teacherList = userBiz.searchUserByDeptAndRole(deptFlow, teacherRoleFlow);
						model.addAttribute("teacherList", teacherList);
					}
				}
			}
		}
		if(StringUtil.isNotBlank(resultFlow)){
			ResDoctorSchProcess process=doctorProcessBiz.searchByResultFlow(resultFlow);
			model.addAttribute("process",process);
		}
		model.addAttribute("teacherUserFlow",teacherUserFlow);
		model.addAttribute("resultFlow",resultFlow);
		return "res/teacher/showChooseTea";
	}

	/**
	 * 保存带教
	 */
	@RequestMapping(value="/saveChooseTea",method=RequestMethod.POST)
	@ResponseBody
	public String saveChooseTea(String teacherUserName,String teacherUserFlow,String resultFlow){
		this.doctorProcessBiz.saveChooseTea(teacherUserName,teacherUserFlow, resultFlow );
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 *  显示概况
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showView/kmHead")
	public String kmHeadShowView(Model model,String doctorName,Integer currentPage,HttpServletRequest request){
		SysUser user = GlobalContext.getCurrentUser();

		if(user!=null && StringUtil.isNotBlank(user.getDeptFlow())){
			//当前带教老师轮转科室列表
			List<String> schDeptFlows = new ArrayList<String>();
			List<SysUserDept> userDeptList = userBiz.searchUserDeptByUser(user.getUserFlow());
			if(userDeptList!=null && userDeptList.size()>0){
				List<String> deptFlows = new ArrayList<String>();
				for(SysUserDept sud : userDeptList){
					deptFlows.add(sud.getDeptFlow());
				}
				List<SchDept> schDeptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
				if(schDeptList!=null && schDeptList.size()>0){
					for(SchDept sd : schDeptList){
						schDeptFlows.add(sd.getSchDeptFlow());
					}
				}
			}

			//权限期间是否开通
			String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
			List<SchArrangeResult> resultList = new ArrayList<SchArrangeResult>();
//			Map<String,String>

		}
		return "res/teacher/kmView";
	}

	/**
	 * 评价学员
	 * @param roleFlag
	 * @param model
	 * @param doctorName
	 * @param isCurrentFlag
	 * @param currentPage
	 * @param request
     * @return
     */
	@RequestMapping(value="/evaluate/{roleFlag}")
	public String evaluate(@PathVariable String roleFlag,/*String schDeptFlow,*/Model model,String doctorName,
						   String isCurrentFlag,Integer currentPage,HttpServletRequest request){
//		Map<String,BigDecimal> countMap = this.resRecBiz.searchAuditCount(GlobalContext.getCurrentUser().getUserFlow(), roleFlag);
//		model.addAttribute("countMap", countMap);
		model.addAttribute("roleFlag", roleFlag);
//
//		ResRec rec = new ResRec();
//		rec.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
//		//rec.setStatusId(RecStatusEnum.Submit.getId());
//		ResDoctorSchProcess process = new ResDoctorSchProcess();
//		process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
//		List<ResRecExt> recExtList = this.resRecBiz.searchRegistryList(GlobalContext.getCurrentUser().getUserFlow(), roleFlag,rec,process);
//		model.addAttribute("recExtList",recExtList);

		SysUser user = GlobalContext.getCurrentUser();

		if(user!=null && StringUtil.isNotBlank(user.getDeptFlow())){
			//当前带教老师轮转科室列表
			List<String> schDeptFlows = new ArrayList<String>();
			List<SysUserDept> userDeptList = userBiz.searchUserDeptByUser(user.getUserFlow());
			if(userDeptList!=null && userDeptList.size()>0){
				List<String> deptFlows = new ArrayList<String>();
				for(SysUserDept sud : userDeptList){
					deptFlows.add(sud.getDeptFlow());
				}
				List<SchDept> schDeptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
				if(schDeptList!=null && schDeptList.size()>0){
					for(SchDept sd : schDeptList){
						schDeptFlows.add(sd.getSchDeptFlow());
					}
				}
			}

//			if(schDeptList!=null && schDeptList.size()>0){
//				schDeptFlow = StringUtil.defaultIfEmpty(schDeptFlow,schDeptList.get(0).getSchDeptFlow());
//				if(schDeptList!=null && schDeptList.size()>0){
//					model.addAttribute("schDeptList",schDeptList);

//					Map<String,String> deptNameMap = new HashMap<String, String>();
//					for(SchDept dept : schDeptList){
//						deptNameMap.put(dept.getSchDeptFlow(),dept.getSchDeptName());
//					}
//					model.addAttribute("schDeptName",deptNameMap.get(schDeptFlow));
//				}
//				model.addAttribute("schDeptFlow",schDeptFlow);

			//当前轮转医师
			ResDoctorSchProcess process = new ResDoctorSchProcess();

			List<ResDoctorSchProcess> processList = null;

            if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
				process.setTeacherUserFlow(user.getUserFlow());
				process.setIsCurrentFlag(isCurrentFlag);

				//查询后台配置的轮转登记数据类型
				List<String> recTypeIds = new ArrayList<String>();
				for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
						recTypeIds.add(regType.getId());
					}
				}

				if(recTypeIds.size()>0){
					//查询待审核数据
                    List<Map<String, Object>> waitAuditMapList = resRecBiz.searchDoctorNotAuditCount(/*schDeptFlow*/null, user.getUserFlow(), com.pinde.core.common.GlobalConstant.FLAG_N, recTypeIds);
					if(waitAuditMapList!=null && waitAuditMapList.size()>0){
						Map<String,Object> waitAuditMap = new HashMap<String, Object>();
						for(Map<String,Object> map : waitAuditMapList){
							waitAuditMap.put((String)map.get("recKey"),map.get("finishCount"));
						}
						model.addAttribute("waitAuditMap",waitAuditMap);
					}
				}

                List<Map<String, Object>> waitAuditAppealMapList = resRecBiz.searchNotAuditAppealCount(/*schDeptFlow*/null, user.getUserFlow(), com.pinde.core.common.GlobalConstant.FLAG_N);
				if(waitAuditAppealMapList!=null && waitAuditAppealMapList.size()>0){
					Map<String,Object> waitAuditAppealMap = new HashMap<String, Object>();
					for(Map<String,Object> map : waitAuditAppealMapList){
						waitAuditAppealMap.put((String)map.get("appealKey"),map.get("appealSum"));
					}
					model.addAttribute("waitAuditAppealMap",waitAuditAppealMap);
				}
				//权限期间是否开通
				String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
				PageHelper.startPage(currentPage,getPageSize(request));
				processList = doctorProcessBiz.searchDoctorProcess(isOpen,doctorName,process);//带教审核数据


            } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
				process.setHeadUserFlow(user.getUserFlow());
				//带教老师审核情况
				//带教审核过的数据
                List<Map<String, Object>> auditMapList = resRecBiz.searchTeacherAuditCount(user.getUserFlow(), com.pinde.core.common.GlobalConstant.FLAG_Y);
				//带教未审核过的数据
                List<Map<String, Object>> notAuditMapList = resRecBiz.searchTeacherAuditCount(user.getUserFlow(), com.pinde.core.common.GlobalConstant.FLAG_N);

				List<String> teacherFlows = new ArrayList<String>();
				if(auditMapList!=null && auditMapList.size()>0){
					Map<String,Map<String,Object>> auditMap = new HashMap<String, Map<String,Object>>();
					for(Map<String,Object> map : auditMapList){
						String teacherFlow = (String)map.get("teacherFlow");
						if(!teacherFlows.contains(teacherFlow)){
							teacherFlows.add(teacherFlow);
						}
						auditMap.put(teacherFlow,map);
					}
					model.addAttribute("auditMap",auditMap);
				}
				if(notAuditMapList!=null && notAuditMapList.size()>0){
					Map<String,Map<String,Object>> notAuditMap = new HashMap<String, Map<String,Object>>();
					for(Map<String,Object> map : notAuditMapList){
						String teacherFlow = (String)map.get("teacherFlow");
						if(!teacherFlows.contains(teacherFlow)){
							teacherFlows.add(teacherFlow);
						}
						notAuditMap.put(teacherFlow,map);
					}
					model.addAttribute("notAuditMap",notAuditMap);
				}
				model.addAttribute("teacherFlows",teacherFlows);

				//待入科查询
				List<SchArrangeResult> resultList = resultBiz.willInDoctor(user.getOrgFlow(),user.getUserFlow());
				if(resultList!=null && resultList.size()>0){
					List<String> doctorFlows = new ArrayList<String>();
					List<SchArrangeResult> willInResult = new ArrayList<SchArrangeResult>();
					for(SchArrangeResult result : resultList){
						if(schDeptFlows.contains(result.getSchDeptFlow())){
							if(!doctorFlows.contains(result.getDoctorFlow())){
								doctorFlows.add(result.getDoctorFlow());
								willInResult.add(result);
							}
						}
					}
					if(willInResult!=null && willInResult.size()>0){
						model.addAttribute("willInResult",willInResult);

						List<String> userFlows = new ArrayList<String>();
						for(SchArrangeResult sar : willInResult){
							userFlows.add(sar.getDoctorFlow());
						}

						List<SysUser> willInUserList = userBiz.searchSysUserByuserFlows(userFlows);
						if(willInUserList!=null && willInUserList.size()>0){
							Map<String,SysUser> willInUserMap = new HashMap<String, SysUser>();
							for(SysUser su : willInUserList){
								willInUserMap.put(su.getUserFlow(),su);
							}
							model.addAttribute("willInUserMap",willInUserMap);
						}

						List<ResDoctor> willInDoctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
						if(willInDoctorList!=null && willInDoctorList.size()>0){
							Map<String,ResDoctor> willInDoctorMap = new HashMap<String, ResDoctor>();
							for(ResDoctor rd : willInDoctorList){
								willInDoctorMap.put(rd.getDoctorFlow(),rd);
							}
							model.addAttribute("willInDoctorMap",willInDoctorMap);
						}

//							List<ResDoctorSchProcess> isRotationProcess = doctorProcessBiz.searchCurrProcessByUserFlows(userFlows);
//							if(isRotationProcess!=null && isRotationProcess.size()>0){
//								Map<String,ResDoctorSchProcess> isRotationProcessMap = new HashMap<String, ResDoctorSchProcess>();
//								for(ResDoctorSchProcess rdsp : isRotationProcess){
//									isRotationProcessMap.put(rdsp.getUserFlow(),rdsp);
//								}
//								model.addAttribute("isRotationProcessMap",isRotationProcessMap);
//							}

					}
				}

				//processList = doctorProcessBiz.searchDoctorProcess(process);
				//权限期间是否开通
				String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
				processList = doctorProcessBiz.searchCurrentProcessByUserFlow(isOpen,user.getUserFlow(),process.getIsCurrentFlag());


            } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)) {
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
				processList = doctorProcessBiz.searchProcessByUserSpe(user.getUserFlow(),process.getIsCurrentFlag());
			}

			if(processList!=null && processList.size()>0){
				model.addAttribute("processList",processList);

				List<SchArrangeResult> resultList = new ArrayList<SchArrangeResult>();

				for(ResDoctorSchProcess schProcess:processList){
					String schResultFlow = schProcess.getSchResultFlow();
					if(StringUtil.isNotBlank(schResultFlow)){
						SchArrangeResult result = schArrangeResultBiz.readSchArrangeResult(schResultFlow);
						resultList.add(result);
					}

					//出科小结情况
                    List<ResSchProcessExpress> afterList = expressBiz.searchByProcessFlowAndRecTypeIdClob(schProcess.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());

					if(afterList!=null && afterList.size()>0){
						Map<String,ResSchProcessExpress> afterMap = new HashMap<String, ResSchProcessExpress>();
						for(ResSchProcessExpress rec : afterList){
							afterMap.put(rec.getProcessFlow(),rec);
						}
						model.addAttribute("afterMap",afterMap);
					}

					//出科成绩
                    List<ResSchProcessExpress> evaluationList = expressBiz.searchByProcessFlowAndRecTypeIdClob(schProcess.getProcessFlow(), com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
					if(evaluationList!=null && evaluationList.size()>0){
						Map<String,String> evaluationMap = new HashMap<String, String>();
						Map<String, ResSchProcessExpress> recMap = new HashMap<String, ResSchProcessExpress>();
						for(ResSchProcessExpress rec : evaluationList){
							Map<String,Object> scoreMap = resRecBiz.parseRecContent(rec.getRecContent());
							if(scoreMap!=null){
								evaluationMap.put(rec.getOperUserFlow()+rec.getSchDeptFlow(), (String) scoreMap.get("totalScore"));
								recMap.put(rec.getProcessFlow(),rec);
							}
						}
						model.addAttribute("evaluationMap", evaluationMap);
						model.addAttribute("recMap", recMap);
					}
				}
				model.addAttribute("resultList",resultList);


				Map<String,String> finishPreMap = new HashMap<String, String>();

				List<String> userFlows = new ArrayList<String>();
				Map<String,ResDoctorSchProcess> processMap = new HashMap<String, ResDoctorSchProcess>();
				for(ResDoctorSchProcess processTemp : processList){
					userFlows.add(processTemp.getUserFlow());

					//计算登记百分比
//						resultList.clear();
//						String resultFlow = processTemp.getSchResultFlow();
//						SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
//						if(result!=null){
//							resultList.add(result);
//							Map<String,String> finishPre = resRecBiz.getFinishPer(resultList,processTemp.getUserFlow());
//							if(finishPre!=null){
//								finishPreMap.put(processTemp.getUserFlow(),finishPre.get(resultFlow));
//							}
//						}

					//轮转中记录
					processMap.put(processTemp.getUserFlow(),processTemp);
				}
				model.addAttribute("processMap",processMap);
				model.addAttribute("finishPreMap",finishPreMap);

				List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
				if(doctorList!=null && doctorList.size()>0){
					model.addAttribute("doctorList",doctorList);

					Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
					Map<String,Integer> categoryNum = new HashMap<String, Integer>();
					for(ResDoctor doctor : doctorList){
						SysUser user2 = userBiz.readSysUser(doctor.getDoctorFlow());
						doctor.setDoctorName(user2.getUserName());
						String key = doctor.getDoctorCategoryName();
						if(categoryNum.get(key)==null){
							categoryNum.put(key,1);
						}else{
							categoryNum.put(key,categoryNum.get(key)+1);
						}
						doctorMap.put(doctor.getDoctorFlow(),doctor);
					}
					model.addAttribute("doctorMap",doctorMap);
					model.addAttribute("categoryNum",categoryNum);

					//读取用户信息
					List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
					if(userList!=null && userList.size()>0){
						Map<String,SysUser> userMap = new HashMap<String, SysUser>();
						for(SysUser su : userList){
							userMap.put(su.getUserFlow(),su);
						}
						model.addAttribute("userMap",userMap);
					}
				}

			}

			//已出科医师
			process.setIsCurrentFlag(null);
            process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
			List<ResDoctorSchProcess> afterProcessList = null;
			//权限期间是否开通
			String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
			afterProcessList = doctorProcessBiz.searchDoctorProcess(isOpen,doctorName,process);

			if(afterProcessList!=null && afterProcessList.size()>0){
				List<String> userFlows = new ArrayList<String>();
				for(ResDoctorSchProcess processTemp : afterProcessList){
					userFlows.add(processTemp.getUserFlow());
				}

				List<ResDoctor> afterDoctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
				model.addAttribute("afterDoctorList",afterDoctorList);
			}
			String currDate = DateUtil.getCurrDate();
			currDate = DateUtil.newDateOfAddMonths(currDate,1);

			List<ResDoctor> willInDoctorList = doctorBiz.searchMonthRotationDoctor(/*schDeptFlow*/null,currDate.substring(0,7));
			model.addAttribute("willInDoctorList",willInDoctorList);
		}



		return "res/teacher/evaluateDoctor";
	}





	/**
	 * 审核列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/auditList",method={RequestMethod.GET,RequestMethod.POST})
	public String auditList(String roleFlag,Model model){
		model.addAttribute("roleFlag",roleFlag);
        model.addAttribute("isCurrentFlag", com.pinde.core.common.GlobalConstant.FLAG_Y);
		return "redirect:/res/teacher/auditListContent";
	}

	/**
	 * 审核列表加载的内容
	 * @param recTypeId
	 * @param roleFlag
	 * @param datas 学员类型
	 * @param dshStatus 审核状态培训数据
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/auditListContent",method={RequestMethod.GET,RequestMethod.POST})
	public String auditListContent(ResDoctor searchDoctor,String recTypeId,String roleFlag,ResDoctorSchProcess process,
								   Integer currentPage,HttpServletRequest request,Model model,String typeId,String [] datas,String dshStatus){
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
//		ResDoctorSchProcess process = new ResDoctorSchProcess();

		Map<String,String> roleFlagMap = new HashMap<String, String>();
		roleFlagMap.put("roleFlag",roleFlag);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
			process.setTeacherUserFlow(userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
			process.setHeadUserFlow(userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SECRETARY.equals(roleFlag)) {
			roleFlagMap.put("val",userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)) {
			roleFlagMap.put("val",userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleFlag)) {
			roleFlagMap.put("val",GlobalContext.getCurrentUser().getOrgFlow());
		}
//		if(StringUtil.isNotBlank(isCurrentFlag)){
//			process.setIsCurrentFlag(isCurrentFlag);
//		}
//		process.setStartDate(startDate);
//		process.setEndDate(endDate);
		//List<ResDoctorSchProcess> processList = doctorProcessBiz.searchDoctorProcess(process);

		if(currentPage==null){
			currentPage=1;
		}
		List<String> recTypeIds = new ArrayList<>();
		if(StringUtil.isBlank(typeId)){
			for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))
						&& !(regType.getId().equals("CampaignNoItemRegistry") && "shrjyy".equals(InitConfig.getSysCfg("res_form_category")))){
					recTypeIds.add(regType.getId());
				}
			}
		}
		List<ResDoctorSchProcess> processList = null;
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		Map<String,Object> param = new HashMap<>();
		param.put("isOpen",isOpen);
		param.put("doctor",searchDoctor);
		param.put("process",process);
		param.put("roleFlagMap",roleFlagMap);
		param.put("docTypeList",docTypeList);
		param.put("basicPractice",typeId);
		param.put("recDshStatus",dshStatus);
		param.put("recTypeIds",recTypeIds);
		PageHelper.startPage(currentPage,getPageSize(request));
//		processList = doctorProcessBiz.searchProcessByDoctorNew(isOpen,searchDoctor,process,roleFlagMap,typeId);
		processList = doctorProcessBiz.searchProcessByDoctorNew(param);

		if(processList!=null && processList.size()>0){
			model.addAttribute("processList",processList);

			List<SchArrangeResult> resultList = new ArrayList<SchArrangeResult>();
			Map<String,String> finishPreMap = new HashMap<String, String>();
			List<String> resultFlows = new ArrayList<String>();

			List<String> userFlows = new ArrayList<String>();
			List<String> processFlows = new ArrayList<String>();
			for(ResDoctorSchProcess processTemp : processList){
				String resultFlow = processTemp.getSchResultFlow();
				if(!userFlows.contains(processTemp.getUserFlow())){
					userFlows.add(processTemp.getUserFlow());
				}
				if(!processFlows.contains(processTemp.getProcessFlow())){
					processFlows.add(processTemp.getProcessFlow());
				}
				resultFlows.add(resultFlow);
                if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
					resultList.clear();
					SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
					if(result!=null){
						resultList.add(result);
						Map<String,String> finishPre = resRecBiz.getFinishPer(resultList,processTemp.getUserFlow());
						if(finishPre!=null){
							finishPreMap.put(processTemp.getUserFlow(),finishPre.get(resultFlow));
						}
					}
				}
			}
			model.addAttribute("finishPreMap",finishPreMap);


			//岗前培训数据
            List<ResRec> recList = resRecBiz.searchRecByUserAndSchdept(userFlows, processFlows, com.pinde.core.common.enums.ResRecTypeEnum.PreTrainForm.getId(), null);
			if(recList!=null && recList.size()>0){
				Map<String,ResRec> preTrainMap = new HashMap<String, ResRec>();
				for(ResRec rec : recList){
					preTrainMap.put(rec.getOperUserFlow()+rec.getProcessFlow(),rec);
				}
				model.addAttribute("preTrainMap",preTrainMap);
			}


			List<Map<String,Object>> finishCountMapList = resRecBiz.countRecWithDoc(userFlows,processFlows);
			if(finishCountMapList!=null && finishCountMapList.size()>0){
				Map<String,Object> finishCountMap = new HashMap<String,Object>();
				for(Map<String,Object> map : finishCountMapList){
					finishCountMap.put((String)map.get("recKey"),map.get("finishCount"));
				}
				model.addAttribute("finishCountMap",finishCountMap);
			}

			List<Map<String,Object>> waitAuditCountMapList = resRecBiz.countRecWithDoc(userFlows,processFlows,roleFlag);
			if(waitAuditCountMapList!=null && waitAuditCountMapList.size()>0){
				Map<String,Object> waitAuditCountMap = new HashMap<String,Object>();
				for(Map<String,Object> map : waitAuditCountMapList){
					waitAuditCountMap.put((String)map.get("recKey"),map.get("finishCount"));
				}
				model.addAttribute("waitAuditCountMap",waitAuditCountMap);
			}

			List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				//List<String> rotationFlows = new ArrayList<String>();
				Map<String,ResDoctor> doctorMap = new HashMap<String,ResDoctor>();
				for(ResDoctor doctor : doctorList){
					SysUser user = userBiz.readSysUser(doctor.getDoctorFlow());
					doctor.setDoctorName(user.getUserName());
					doctorMap.put(doctor.getDoctorFlow(),doctor);
//					if(!rotationFlows.contains(doctor.getRotationFlow()) && StringUtil.isNotBlank(doctor.getRotationFlow())){
//						rotationFlows.add(doctor.getRotationFlow());
//					}
				}
				model.addAttribute("doctorMap",doctorMap);

				//用户信息
				List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
				if(userList!=null && userList.size()>0){
					Map<String,SysUser> userMap = new HashMap<String, SysUser>();
					for(SysUser user : userList){
						userMap.put(user.getUserFlow(),user);
					}
					model.addAttribute("userMap",userMap);
				}


				List<SchArrangeResult> searchReqResultList = resultBiz.searchArrangeResultByResultFlow(resultFlows);
				if(searchReqResultList!=null && searchReqResultList.size()>0){
					Map<String,SchArrangeResult> resultMap = new HashMap<String, SchArrangeResult>();
					Map<String,Map<String,String>> finishMap = new HashMap<String, Map<String,String>>();
					List<String> rotationFlows = new ArrayList<String>();
					List<String> standardDeptId = new ArrayList<String>();
					for(SchArrangeResult result : searchReqResultList){
						if(StringUtil.isNotBlank(result.getRotationFlow())){
							rotationFlows.add(result.getRotationFlow());
						}
						standardDeptId.add(StringUtil.defaultIfEmpty(result.getStandardDeptId(),""));
						resultMap.put(result.getResultFlow(),result);
						resultList.clear();
						resultList.add(result);
						Map<String,String> finishPre = resRecBiz.getFinishPer(resultList,result.getDoctorFlow());
						finishMap.put(result.getResultFlow(),finishPre);
					}
					model.addAttribute("resultMap",resultMap);
					model.addAttribute("finishMap",finishMap);

					if(rotationFlows.size()>0){
						//要求数计算
						List<Map<String,Object>> reqMapList = schRotationDeptBiz.countReqWithSchDept(rotationFlows,standardDeptId);
						if(reqMapList!=null && reqMapList.size()>0){
							Map<String,Object> reqMap = new HashMap<String,Object>();
							for(Map<String,Object> map : reqMapList){
								reqMap.put((String)map.get("reqKey"),map.get("reqSum"));
							}
							model.addAttribute("reqMap",reqMap);
						}
					}
				}
			}

			List<Map<String,Object>> appealCountMapList = resRecBiz.appealCountWithUser(userFlows,processFlows);
			if(appealCountMapList!=null && appealCountMapList.size()>0){
				Map<String,Object> appealMap = new HashMap<String, Object>();
				for(Map<String,Object> map : appealCountMapList){
					appealMap.put((String)map.get("appealKey"),map.get("appealSum"));
				}
				model.addAttribute("appealMap",appealMap);
			}

            List<Map<String, Object>> waitAuditAppealCountMapList = resRecBiz.appealCountWithUser(userFlows, processFlows, com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER);
			if(waitAuditAppealCountMapList!=null && waitAuditAppealCountMapList.size()>0){
				Map<String,Object> waitAuditAppealCountMap = new HashMap<String, Object>();
				for(Map<String,Object> map : waitAuditAppealCountMapList){
					waitAuditAppealCountMap.put((String)map.get("appealKey"),map.get("appealSum"));
				}
				model.addAttribute("waitAuditAppealCountMap",waitAuditAppealCountMap);
			}
		}

//		if(StringUtil.isNotBlank(recTypeId)){
//			ResRec rec = new ResRec();
//			rec.setRecTypeId(recTypeId);
//			//rec.setStatusId(RecStatusEnum.Submit.getId());
//			ResDoctorSchProcess process = null;
//			if(StringUtil.isNotBlank(isCurrentFlag)){
//				process = new ResDoctorSchProcess();
//				process.setIsCurrentFlag(isCurrentFlag);
//			}
//			List<ResRecExt> recExtTempList = this.resRecBiz.searchRegistryList(GlobalContext.getCurrentUser().getUserFlow(), roleFlag,rec,process);
//			if(recExtTempList!=null && recExtTempList.size()>0){
//				List<ResRecExt> recExtList = new ArrayList<ResRecExt>();
//				List<String> userFlows = new ArrayList<String>();
//				Map<String,Integer> countMap = new HashMap<String, Integer>();
//				for(ResRecExt recExt : recExtTempList){
//					String userFlow = recExt.getDoctorExt().getDoctorFlow();
//
//					if(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId().equals(recExt.getRecTypeId()) || com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId().equals(recExt.getRecTypeId())){
//						boolean auditAgree = false;
//						if(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)){
//							auditAgree = !RecStatusEnum.TeacherAuditY.getId().equals(recExt.getAuditStatusId());
//						}else if(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)){
//							auditAgree = !RecStatusEnum.HeadAuditY.getId().equals(recExt.getHeadAuditStatusId());
//						}
//						if(auditAgree){
//							recExtList.add(recExt);
//						}
//					}else{
//						if(!userFlows.contains(userFlow)){
//							recExtList.add(recExt);
//							userFlows.add(userFlow);
//
//							String rotationFlow = recExt.getDoctorExt().getRotationFlow();
//							String schDeptFlow = recExt.getSchDeptFlow();
//							if(StringUtil.isNotBlank(rotationFlow) && StringUtil.isNotBlank(schDeptFlow)){
//								List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchDeptReq(rotationFlow,schDeptFlow,recTypeId);
//								if(deptReqList!=null && deptReqList.size()>0){
//									for(SchRotationDeptReq deptReq : deptReqList){
//										if(countMap.get(userFlow+"reqCount")==null){
//											if(deptReq.getReqNum()!=null){
//												countMap.put(userFlow+"reqCount",deptReq.getReqNum().intValue());
//											}
//										}else{
//											if(deptReq.getReqNum()!=null){
//												countMap.put(userFlow+"reqCount",countMap.get(userFlow+"reqCount")+deptReq.getReqNum().intValue());
//											}
//										}
//									}
//								}
//							}
//						}
//						if(countMap.get(userFlow+"finishCount")==null){
//							countMap.put(userFlow+"finishCount",1);
//						}else{
//							countMap.put(userFlow+"finishCount",countMap.get(userFlow+"finishCount")+1);
//						}
//
//						if(StringUtil.isBlank(recExt.getAuditStatusId())){
//							if(countMap.get(userFlow+"notAuditCount")==null){
//								countMap.put(userFlow+"notAuditCount",1);
//							}else{
//								countMap.put(userFlow+"notAuditCount",countMap.get(userFlow+"notAuditCount")+1);
//							}
//						}else{
//							if(countMap.get(userFlow+"auditCount")==null){
//								countMap.put(userFlow+"auditCount",1);
//							}else{
//								countMap.put(userFlow+"auditCount",countMap.get(userFlow+"auditCount")+1);
//							}
//						}
//					}
//				}
//				model.addAttribute("recExtList", recExtList);
//				model.addAttribute("countMap", countMap);
//			}
//		}
		if(JszyTCMPracticEnum.BasicPractice.getId().equals(typeId)){
			return "res/teacher/practicAuditList";
		}else {
			return "res/teacher/auditList";
		}
	}

	@RequestMapping(value="/recAuditList",method=RequestMethod.GET)
	public String recAuditList(String recTypeId,String roleFlag,String doctorFlow,String schDeptFlow,String processFlow,Model model,String typeId){//String isCurrentFlag
		if(StringUtil.isNotBlank(doctorFlow)){
			model.addAttribute("typeId",typeId);
			List<ResRec> recList = resRecBiz.searchByRecForAudit(processFlow,recTypeId);
			model.addAttribute("recList", recList);

			List<ResAppeal> appealList = resRecBiz.searchAppealForAudit(processFlow,recTypeId);
			model.addAttribute("appealList", appealList);

            model.addAttribute("recTypeName", com.pinde.core.common.enums.ResRecTypeEnum.getNameById(recTypeId));

			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);

			if(StringUtil.isNotBlank(processFlow)){
				ResDoctorSchProcess process = doctorProcessBiz.read(processFlow);
				model.addAttribute("process",process);
			}
            model.addAttribute("complete", recList.size());
		}

//		if(StringUtil.isNotBlank(doctorFlow)){
//			List<ResRecExt> recExtList = this.resRecBiz.searchAuditList(GlobalContext.getCurrentUser().getUserFlow(), roleFlag,recTypeId,doctorFlow,isCurrentFlag);
//			if(recExtList!=null && recExtList.size()>0){
//				Map<String,Map<String,String>> formDataMapMap = new HashMap<String, Map<String,String>>();
//				for(ResRecExt recEct : recExtList){
//					Map<String,String> formDataMap = resRecBiz.parseRecContent(recEct.getRecContent());
//					formDataMapMap.put(recEct.getRecFlow(), formDataMap);
//				}
//				model.addAttribute("formDataMapMap", formDataMapMap);
//				model.addAttribute("recExtList", recExtList);
//			}

//		}
			SysUser user = userBiz.readSysUser(doctorFlow);
			model.addAttribute("user", user);
		return "res/teacher/recAuditList";
	}

	@RequestMapping(value="/afterAudit",method=RequestMethod.GET)
	public String afterAudit(String processFlow){

		return "";
	}



	/**
	 * 显示审核界面
	 * @param recFlow
	 * @param recTypeId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/showAudit",method=RequestMethod.GET)
	public String showAudit(String role,String schDeptFlow,String operUserFlow,String recFlow,String recTypeId,String roleFlag, Model model ){

        model.addAttribute("role",role);
        if (com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId().equals(recTypeId)) {
			ResSchProcessExpress express = expressBiz.readResExpress(recFlow);
			Map<String,Object> formDataMap = resRecBiz.parseRecContent(express.getRecContent());
			model.addAttribute("formDataMap", formDataMap);

			String version = null;
			String recForm = null;
			String medicineTypeId = "";
			if(express!=null){
				version = express.getRecVersion();
				recForm = express.getRecForm();
				medicineTypeId = express.getMedicineType();
			}
			String processFlow = express.getProcessFlow();
			ResDoctorSchProcess resDoctorSchProcess = doctorProcessBiz.read(processFlow);
			String type="";
			if(resDoctorSchProcess!=null){
				SchRotationDept dept=schRotationDeptBiz.readStandardRotationDept(resDoctorSchProcess.getSchResultFlow());

				if(dept!=null)
				{
					if(JszyTCMPracticEnum.BasicPractice.getId().equals(dept.getPracticOrTheory()))
					{
						type="B";
					}
				}
			}
			String jspPath = resRecBiz.getFormPath(recTypeId,version,recForm, type,medicineTypeId);
			model.addAttribute("jspPath", jspPath);

			String view = null;
			if(express!=null){
				view = "res/teacher/audit/"+recTypeId;
				model.addAttribute("rec", express);
			}
			return view;
		}else{
			ResRec rec = this.resRecBiz.readResRec(recFlow);

			Map<String,Object> formDataMap = resRecBiz.parseRecContent(rec.getRecContent());
			model.addAttribute("formDataMap", formDataMap);

//		String rotationFlow = "";
//		if(StringUtil.isNotBlank(operUserFlow)){
//			ResDoctor doctor =  doctorBiz.readDoctor(operUserFlow);
//			if(doctor!=null){
//				rotationFlow = doctor.getRotationFlow();
//				List<SchRotationDeptReq> deptReqList = schRotationDeptBiz.searchDeptReq(doctor.getRotationFlow(),schDeptFlow,recTypeId);
//				model.addAttribute("deptReqList",deptReqList);
//			}
//		}

			String medicineTypeId = "";
			String version = null;
			String recForm = null;
			if(rec!=null){
				version = rec.getRecVersion();
				recForm = rec.getRecForm();
				medicineTypeId = rec.getMedicineType();
			}
			String jspPath = resRecBiz.getFormPath(recTypeId,version,recForm, "",medicineTypeId);
			model.addAttribute("jspPath", jspPath);

			String view = null;
			if(rec!=null){
				view = "res/teacher/audit/common";
				model.addAttribute("rec", rec);
			}
			return view;
		}

	}

	/**
	 * 出科小结提交
	 * @return
     */
	private String auditNew(String recFlow,String recTypeId,HttpServletRequest req,String processFlow){

		ResSchProcessExpress express = expressBiz.readResExpress(recFlow);
		if(express!=null){

			String rotationFlow = "";
			String doctorFlow = express.getOperUserFlow();
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			if(doctor!=null){
				rotationFlow = doctor.getRotationFlow();
			}

			String recContent;

			String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
			String productType = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("res_form_category_"+rotationFlow),InitConfig.getSysCfg("res_form_category"));//与对应开关保持一致
			if(StringUtil.isBlank(productType)){
                productType = com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT;
			}
			String currVer = InitConfig.resFormRequestUtil.getVersionMap().get(productType+"_"+recTypeId);
			if(StringUtil.isBlank(currVer)){
                currVer = InitConfig.resFormRequestUtil.getVersionMap().get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + recTypeId);
			}
			if(StringUtil.isBlank(currVer)){
                currVer = com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT_VER;
			}
			Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(recTypeId);
			IrbSingleForm singleForm = singleFormMap.get(productType+"_"+currVer);
			if(singleForm == null){
                singleForm = singleFormMap.get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + currVer);
			}
			if(singleForm == null){
                throw new RuntimeException("未发现表单 模版类型:" + productType + ",表单类型:" + com.pinde.core.common.enums.ResRecTypeEnum.getNameById(recTypeId) + ",版本号:" + currVer);
			}
			recContent = expressBiz.getRecContent(recTypeId, singleForm.getItemList(), req);

			express.setRecContent(recContent);

			int result = 0;
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(req.getParameter("isAgree"))) {
				ResDoctorSchProcess process = new ResDoctorSchProcess();
				process.setProcessFlow(processFlow);
                process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);

				if(StringUtil.isNotBlank(req.getParameter("totalScore"))){
					String score = req.getParameter("totalScore");
					Float f = Float.parseFloat(score);
					process.setSchScore(BigDecimal.valueOf(f));
				}
				result = expressBiz.editAndOut(express,process);
			}else{
				result = expressBiz.edit(express);
			}
            if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 审核
	 * @param recFlow
	 * @param recTypeId
	 * @param roleFlag
	 * @param processFlow
	 * @param auditResult
	 * @param auditAppraise
     * @param req
     * @return
     */
	@RequestMapping(value="/audit",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String audit(String recFlow,String recTypeId,String roleFlag,String processFlow,String auditResult,String auditAppraise,HttpServletRequest req){
		if(StringUtil.isNotBlank(recFlow)){
			//区分是（带教/科主任--出科小结提交功能） 还是 （培训数据审核--审核通过功能）
            if (com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId().equals(recTypeId)) {
				return auditNew(recFlow,recTypeId,req,processFlow);
			}else{
				ResRec rec = resRecBiz.readResRec(recFlow);
				if(rec!=null){
					recTypeId = rec.getRecTypeId();

					SysUser user = GlobalContext.getCurrentUser();

					String rotationFlow = "";
					String doctorFlow = rec.getOperUserFlow();
					ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
					if(doctor!=null){
						rotationFlow = doctor.getRotationFlow();
					}

					String elementName = "";
					String auditStatusName = "";
                    if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
						rec.setAuditStatusId(auditResult);
						rec.setAuditStatusName(RecStatusEnum.getNameById(auditResult));
						rec.setAuditTime(DateUtil.getCurrDateTime());
						rec.setAuditUserFlow(user.getUserFlow());
						rec.setAuditUserName(user.getUserName());
						elementName = "deptAppraise";
						auditStatusName = RecStatusEnum.getNameById(auditResult);
                    } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
						rec.setHeadAuditStatusId(auditResult);
						rec.setHeadAuditStatusName(RecStatusEnum.getNameById(auditResult));
						rec.setHeadAuditTime(DateUtil.getCurrDateTime());
						rec.setHeadAuditUserFlow(user.getUserFlow());
						rec.setHeadAuditUserName(user.getUserName());
						elementName = "deptHeadAutograth";
						auditStatusName = RecStatusEnum.getNameById(auditResult);
                    } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_SECRETARY.equals(roleFlag)) {
                        rec.setHeadAuditStatusId(auditResult);
                        rec.setHeadAuditStatusName(RecStatusEnum.getNameById(auditResult));
                        rec.setHeadAuditTime(DateUtil.getCurrDateTime());
                        rec.setHeadAuditUserFlow(user.getUserFlow());
                        rec.setHeadAuditUserName(user.getUserName());
                        elementName = "deptHeadAutograth";
                        auditStatusName = RecStatusEnum.getNameById(auditResult);
                    }
//				if(RecStatusEnum.TeacherAuditN.getId().equals(auditResult) || RecStatusEnum.HeadAuditN.getId().equals(auditResult)){
//					rec.setStatusId(RecStatusEnum.Edit.getId());
//					rec.setStatusName(RecStatusEnum.Edit.getName());
//				}

					String recContent = rec.getRecContent();
					if(StringUtil.isNotBlank(recContent) && StringUtil.isNotBlank(auditAppraise)){
						try {
							Document doc = DocumentHelper.parseText(recContent);
							Element root = doc.getRootElement();
							if(root!=null){
								Element appraise = root.addElement(elementName);
								appraise.addAttribute("operTime",DateUtil.getCurrDateTime());
								appraise.addAttribute("operUserName",user.getUserName());
								appraise.addAttribute("auditStatusName",auditStatusName);
								appraise.setText(auditAppraise);
							}
							recContent = doc.asXML();
						} catch (Exception e) {
                            logger.error("", e);
						}
					}

                    if (StringUtil.isNotBlank(recContent) && (com.pinde.core.common.enums.ResRecTypeEnum.EmergencyCase.getId().equals(rec.getRecTypeId())
                            || com.pinde.core.common.enums.ResRecTypeEnum.HospitalizationLog.getId().equals(rec.getRecTypeId())
                            || com.pinde.core.common.enums.ResRecTypeEnum.CaseRegistry.getId().equals(rec.getRecTypeId())
					))
					{
						try {
							//校验上传文件大小及格式
							String checkResult=checkFiles(req);
							if(!"1".equals(checkResult))
							{
								return checkResult;
							}
							Document doc = DocumentHelper.parseText(recContent);
							Element root = doc.getRootElement();
							if(root!=null){
								String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
								String productType = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("res_form_category_"+rotationFlow),InitConfig.getSysCfg("res_form_category"));//与对应开关保持一致
								if(StringUtil.isBlank(productType)){
                                    productType = com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT;
								}
								String currVer = InitConfig.resFormRequestUtil.getVersionMap().get(productType+"_"+recTypeId);
								if(StringUtil.isBlank(currVer)){
                                    currVer = InitConfig.resFormRequestUtil.getVersionMap().get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + recTypeId);
								}
								if(StringUtil.isBlank(currVer)){
                                    currVer = com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT_VER;
								}
								Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(recTypeId);
								IrbSingleForm singleForm = singleFormMap.get(productType+"_"+currVer);
								if(singleForm == null){
                                    singleForm = singleFormMap.get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + currVer);
								}
								if(singleForm == null){
                                    throw new RuntimeException("未发现表单 模版类型:" + productType + ",表单类型:" + com.pinde.core.common.enums.ResRecTypeEnum.getNameById(recTypeId) + ",版本号:" + currVer);
								}
								recContent = resRecBiz.getTeaAuditRecContent(recTypeId, singleForm.getItemList(), req,root);
							}
						} catch (Exception e) {
                            logger.error("", e);
						}
					}
                    if (StringUtil.isNotBlank(recContent) && (com.pinde.core.common.enums.ResRecTypeEnum.EmergencyCase.getId().equals(rec.getRecTypeId())))
					{
						try {
							Document doc = DocumentHelper.parseText(recContent);
							Element root = doc.getRootElement();
							if(root!=null){
								Element appraise = root.addElement("teacherSignature");
								appraise.setText(user.getUserName());
							}
							recContent = doc.asXML();
						} catch (Exception e) {
                            logger.error("", e);
						}
					}

                    if (com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId().equals(rec.getRecTypeId())) {
						String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
						String productType = StringUtil.defaultIfEmpty(InitConfig.getSysCfg("res_form_category_"+rotationFlow),InitConfig.getSysCfg("res_form_category"));//与对应开关保持一致
						if(StringUtil.isBlank(productType)){
                            productType = com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT;
						}
						String currVer = InitConfig.resFormRequestUtil.getVersionMap().get(productType+"_"+recTypeId);
						if(StringUtil.isBlank(currVer)){
                            currVer = InitConfig.resFormRequestUtil.getVersionMap().get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + recTypeId);
						}
						if(StringUtil.isBlank(currVer)){
                            currVer = com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT_VER;
						}
						Map<String,IrbSingleForm> singleFormMap = InitConfig.resFormRequestUtil.getFormMap().get(recTypeId);
						IrbSingleForm singleForm = singleFormMap.get(productType+"_"+currVer);
						if(singleForm == null){
                            singleForm = singleFormMap.get(com.pinde.core.common.GlobalConstant.RES_FORM_PRODUCT + "_" + currVer);
						}
						if(singleForm == null){
                            throw new RuntimeException("未发现表单 模版类型:" + productType + ",表单类型:" + com.pinde.core.common.enums.ResRecTypeEnum.getNameById(recTypeId) + ",版本号:" + currVer);
						}
						recContent = resRecBiz.getRecContent(rec.getRecContent(), recTypeId, singleForm.getItemList(), req);
					}

					rec.setRecContent(recContent);

					int result = 0;
                    if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(req.getParameter("isAgree"))) {
						ResDoctorSchProcess process = new ResDoctorSchProcess();
						process.setProcessFlow(processFlow);
                        process.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_N);

						if(StringUtil.isNotBlank(req.getParameter("totalScore"))){
							String score = req.getParameter("totalScore");
							Float f = Float.parseFloat(score);
							process.setSchScore(BigDecimal.valueOf(f));
						}
						result = resRecBiz.editAndOut(rec,process);
					}else{
						result = resRecBiz.edit(rec);
					}
                    if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
                        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
					}
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}


	private String checkFiles(HttpServletRequest request) {
		String result="1";
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			List<String> fileSuffix=new ArrayList<>();
			fileSuffix.add(".DOC");
			fileSuffix.add(".DOCX");
			fileSuffix.add(".PPT");
			fileSuffix.add(".PDF");
			fileSuffix.add(".JPG");
			fileSuffix.add(".PNG");
			//转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while(iter.hasNext()){
				//记录上传过程起始时的时间，用来计算上传时间
				//int pre = (int) System.currentTimeMillis();
				//取得上传文件
				List<MultipartFile> files = multiRequest.getFiles(iter.next());
				if(files != null&&files.size()>0){
					for(MultipartFile file:files) {
						if(!file.isEmpty()) {
							//取得当前上传文件的文件名称
							String fileName = file.getOriginalFilename();
							String suffix = fileName.substring(fileName.lastIndexOf(".")).toUpperCase();
							if (!fileSuffix.contains(suffix)) {
								return "批改附件【" + fileName + "】的文件格式不正确，只能上传pdf,word,jpg,png格式的文件。";
							}
							if (file.getSize() > 10 * 1024 * 1024) {
								return "批改附件【" + fileName + "】的大小超过10M，不得保存";
							}
						}
					}
				}
			}
		}
		return result;
	}
	@RequestMapping(value="/tdUpdate",method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String tdUpdate(ResRec rec){
		int result = resRecBiz.edit(rec);
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
	}

	/**
	 *  考核审批
	 *  datas 学员类型
	 *  dshStatus 待审核状态培训数据
	 */
	@RequestMapping(value="/audit/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String audit(@PathVariable String roleFlag,Model model,String typeId,String [] datas,String dshStatus){
		model.addAttribute("roleFlag",roleFlag);
		model.addAttribute("typeId",typeId);
		model.addAttribute("datas",datas);
		model.addAttribute("dshStatus",dshStatus);
		return "redirect:/res/teacher/auditListContent";
	}
	/**
	 * 查询登记数据
	 * @param roleFlag
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/searchRegisterInfo/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String searchRegisterInfoByRoleFlag(@PathVariable String roleFlag, String onlyOrg, String orgFlow,String orgName,Integer currentPage, Model model,
											   String doctorName,String sessionNumber,String trainingSpeId,String isCurrentFlag){
		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("onlyOrg", onlyOrg);
		model.addAttribute("orgFlow", orgFlow);
		model.addAttribute("orgName", orgName);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("doctorName", doctorName);
		model.addAttribute("sessionNumber", sessionNumber);
		model.addAttribute("trainingSpeId", trainingSpeId);
		model.addAttribute("isCurrentFlag",isCurrentFlag);
		return  "redirect:/res/teacher/searchInfo";
	}

	/**
	 * 登记数据查询
	 * @param roleFlag
	 * @param model
	 * @param process
	 * @param orgFlow
	 * @param currentPage
	 * @param request
     * @param searchDoctor
     * @return
     */
	@RequestMapping(value="/searchInfo",method={RequestMethod.GET,RequestMethod.POST})
	public String searchInfo(String roleFlag,Model model,ResDoctorSchProcess process,String orgFlow,String graduationYear,
							 String[] doctorTypeIdList,String orgName,Integer currentPage,HttpServletRequest request,ResDoctor searchDoctor){
        setSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_DEPT_LIST, deptBiz.searchDeptByOrg(GZZY_ORG_FLOW));
		//查询是否为进修过程页面
		String flag = InitConfig.getSysCfg("is_show_jxres");

        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(orgName)) {
            model.addAttribute("click", com.pinde.core.common.GlobalConstant.FLAG_Y);
		}
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		String workOrgId = "";
		model.addAttribute("roleFlag",roleFlag);
		Map<String,String> roleFlagMap = null;
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
			process.setTeacherUserFlow(userFlow);
			roleFlagMap = new HashMap<String, String>();
			roleFlagMap.put("roleFlag",roleFlag);
			roleFlagMap.put("val",userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
//			process.setHeadUserFlow(userFlow);
			roleFlagMap = new HashMap<String, String>();
			roleFlagMap.put("roleFlag",roleFlag);
			roleFlagMap.put("val",userFlow);
			roleFlagMap.put("deptFlow",GlobalContext.getCurrentUser().getDeptFlow());
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)) {
			roleFlagMap = new HashMap<String, String>();
			roleFlagMap.put("roleFlag",roleFlag);
			roleFlagMap.put("val",userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleFlag)) {
			//医院管理员增加查协同基地学员轮转情况
			List<SysOrg> orgList=new ArrayList<>();
			SysOrg sysOrg=orgBiz.readSysOrg(GlobalContext.getCurrentUser().getOrgFlow());
			orgList.add(sysOrg);
			List<SysOrg> jointOrgList = orgBiz.searchJointOrgsByOrg(GlobalContext.getCurrentUser().getOrgFlow());
			if(jointOrgList!=null&&jointOrgList.size()>0){
				for (SysOrg so:jointOrgList) {
					orgList.add(so);
				}
			}
			model.addAttribute("orgList",orgList);
			orgFlow = StringUtil.isBlank(orgFlow)?GlobalContext.getCurrentUser().getOrgFlow():orgFlow;
			roleFlagMap = new HashMap<String, String>();
			roleFlagMap.put("roleFlag",roleFlag);
			roleFlagMap.put("val",orgFlow);
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
			model.addAttribute("schDeptList",schDeptList);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(roleFlag)) {
			SysUser user=GlobalContext.getCurrentUser();
			String medicineTypeId=user.getMedicineTypeId();
			roleFlagMap = new HashMap<String, String>();
			roleFlagMap.put("roleFlag",roleFlag);
			roleFlagMap.put("medicineTypeId",medicineTypeId);
			roleFlagMap.put("val",orgFlow);
			SysOrg searchOrg = new SysOrg();
            searchOrg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
			List<SysOrg> orgList = orgBiz.searchSysOrg(searchOrg);
			model.addAttribute("orgs",orgList);
			if(StringUtil.isNotBlank(orgFlow)) {
				List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
				model.addAttribute("schDeptList",schDeptList);
			}
        } else if (com.pinde.core.common.GlobalConstant.USER_LIST_UNIVERSITY.equals(roleFlag)) {
			SysUser user=GlobalContext.getCurrentUser();
			String medicineTypeId=user.getMedicineTypeId();
			roleFlagMap = new HashMap<String, String>();
			roleFlagMap.put("roleFlag",roleFlag);
			roleFlagMap.put("medicineTypeId",medicineTypeId);
			roleFlagMap.put("val",orgFlow);
			SysOrg currentOrg = orgBiz.readSysOrg(user.getOrgFlow());
			workOrgId = currentOrg.getSendSchoolId();
			searchDoctor.setWorkOrgId(workOrgId);
			List<SysOrg> orgList = orgExtMapper.searchOrgs4hbUniversity(workOrgId);
			model.addAttribute("orgs",orgList);
			if(StringUtil.isNotBlank(orgFlow)) {
				List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(orgFlow);
				model.addAttribute("schDeptList",schDeptList);
			}
		}
		if(currentPage==null){
			currentPage=1;
		}

		List<String> typeList =null;
		//复选框勾选标识
		Map<String,String> doctorTypeSelectMap = new HashMap<>();
		SysDict sysDict = new SysDict();
        sysDict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorType.getId());
        sysDict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> dictList = dictBiz.searchDictList(sysDict);
		if(doctorTypeIdList!=null&&doctorTypeIdList.length>0){
			typeList=Arrays.asList(doctorTypeIdList);
			for (SysDict dict:dictList){
				if(typeList.contains(dict.getDictId())){
					doctorTypeSelectMap.put(dict.getDictId(),"checked");
				}
			}
		}
		if(doctorTypeIdList==null)
		{
			typeList=new ArrayList<>();
			for (SysDict dict:dictList){
				typeList.add(dict.getDictId());
				doctorTypeSelectMap.put(dict.getDictId(),"checked");
			}
		}

		model.addAttribute("doctorTypeSelectMap",doctorTypeSelectMap);
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		searchDoctor.setGraduationYear(graduationYear);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResDoctor> doctorList = doctorProcessBiz.searchProcessByDoctorNew2(flag,isOpen,searchDoctor,process,roleFlagMap,typeList);

		model.addAttribute("doctorList", doctorList);


		List<String> userFlows = new ArrayList<String>();
		for(ResDoctor doctor : doctorList){
			if(!userFlows.contains(doctor.getDoctorFlow())){
				userFlows.add(doctor.getDoctorFlow());
			}
		}
		if(userFlows!=null && !userFlows.isEmpty()){
			List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser user : userList){
					userMap.put(user.getUserFlow(),user);
				}
				model.addAttribute("userMap",userMap);
			}
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag)) {
			return "/res/teacher/registerInfo_jx";
		}
		if ("/inx/jszy".equals(InitConfig.getSysCfg("sys_index_url"))) {
			return "/res/teacher/registerInfo4jszy";
		}
		return "/res/teacher/registerInfo";
	}
	/**
	 * 轮转数据详情
	 */
	@RequestMapping("/schDetails")
	public String schDetails(String doctorFlow,Model model){
		List<SchArrangeResult> arrResultList = schArrangeResultBiz.searchSchArrangeResultByDoctor(doctorFlow);
		model.addAttribute("arrResultList",arrResultList);

		Map<String,Object> processMsg = new HashMap<>();
		if(arrResultList!=null&& arrResultList.size()>0){
			for(SchArrangeResult temp: arrResultList){
				String resultFlow = temp.getResultFlow();
				ResDoctorSchProcess resDoctorSchProcess = doctorProcessBiz.searchByResultFlow(resultFlow);
				processMsg.put(resultFlow,resDoctorSchProcess);
			}
		}
		model.addAttribute("processMsg",processMsg);
		return "res/teacher/details";
	}
	/**
	 * 登记数据详情
	 * @param doctorFlow
	 * @param processFlow
	 * @param recTypeId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/details",method={RequestMethod.GET,RequestMethod.POST})
	public String details(String doctorFlow,String processFlow,String recTypeId,Model model,HttpServletRequest request) throws Exception{
		ResDoctorSchProcess resDoctorSchProcess = doctorProcessBiz.read(processFlow);
		String resultFlow = resDoctorSchProcess.getSchResultFlow();
		SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
		SchRotationDept schRotationDept = schRotationDeptBiz.readStandardRotationDeptByLocal(result.getRotationFlow(),result.getStandardGroupFlow(),result.getStandardDeptId());
		model.addAttribute("typeId",schRotationDept.getPracticOrTheory());

		SysUser user=userBiz.readSysUser(doctorFlow);
		model.addAttribute("user", user);
		if (StringUtil.isBlank(recTypeId)) {
			return "res/teacher/registrationRecord";
		}
		model.addAttribute("resDoctorSchProcess",resDoctorSchProcess);
		Map<String, Map<String,Object>> resRecMap=new HashMap<String, Map<String,Object>>();
		List<Map<String, String>>  titleMap  = new ArrayList<Map<String, String>>();
		List<ResRec> resRecList=resRecBiz.searchResRecWithBLOBs(recTypeId,processFlow,doctorFlow);
		if(resRecList!=null && !resRecList.isEmpty()){
				Map<String,Object> contentMap = null;
				ResRec rec  =resRecList.get(0);
				titleMap = resRecBiz.parseTitle(rec.getRecForm(), rec.getRecTypeId(), rec.getRecVersion());
				for(ResRec r:resRecList){
					if(titleMap!=null && !titleMap.isEmpty()){
						contentMap =  new HashMap<String,Object>();
						contentMap = resRecBiz.parseContent(r.getRecContent());
						 String key = r.getRecFlow();
						 resRecMap.put(key, contentMap);
					}

				}
		}

		//为登记表单查询完成数和要求数
		List<SchArrangeResult> arrResultList=new ArrayList<>();
		arrResultList.add(result);
		Map<String,String> finishPer= resRecBiz.getFinishPer(arrResultList,doctorFlow);
//		System.out.println("==========:"+ JSON.toJSONString(finishPer));
		model.addAttribute("finishPer",finishPer);
		model.addAttribute("resultFlow",resultFlow);
		model.addAttribute("titleMap", titleMap);
		model.addAttribute("resRecMap", resRecMap);
		model.addAttribute("resRecList", resRecList);
		return "res/teacher/registrationRecord";
	}

	/**
	 * 一键审核
	 */
	@RequestMapping(value="/oneKeyAudit",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String oneKeyAudit(String recTypeId,String processFlow,String operUserFlow){
		int result = resRecBiz.oneKeyAudit(recTypeId,processFlow,operUserFlow);
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}
    /**
     * 一键审核
     */
    @RequestMapping(value="/oneKeyAuditAll",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String oneKeyAuditAll(String auditType){
//        type= "1";
        int result = resRecBiz.oneKeyAuditAll(auditType);
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
        }
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
    }

	/**
	 * 申述审批
	 */
	@RequestMapping(value="/appealAudit/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String appealAudit(@PathVariable String roleFlag,ResAppeal appeal,String isCurrentFlag,Model model){
		model.addAttribute("roleFlag",roleFlag);

		String userFlow = GlobalContext.getCurrentUser().getUserFlow();

		if(appeal!=null && appeal.getAuditStatusId()==null){
            isCurrentFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}

		ResDoctorSchProcess doctorProcess = new ResDoctorSchProcess();
		doctorProcess.setIsCurrentFlag(isCurrentFlag);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
			doctorProcess.setTeacherUserFlow(userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
			doctorProcess.setHeadUserFlow(userFlow);
		}
		List<ResDoctorSchProcess> doctorProcessList=null;

		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		doctorProcessList = doctorProcessBiz.searchDoctorProcess(isOpen,null,doctorProcess);

		if(doctorProcessList!=null && doctorProcessList.size()>0){
			model.addAttribute("doctorProcessList",doctorProcessList);

			List<String> schDeptFlows = new ArrayList<String>();
			for(ResDoctorSchProcess doctorProcessTemp : doctorProcessList){
				schDeptFlows.add(doctorProcessTemp.getSchDeptFlow());
			}
			appeal.setAuditStatusId(RecStatusEnum.TeacherAuditN.getId());
			appeal.setStatusId(RecStatusEnum.Submit.getId());
			List<ResAppeal> appealList = resRecBiz.searchAppeal(schDeptFlows,appeal);
			if(appealList!=null && appealList.size()>0){
				Map<String,List<ResAppeal>> appealMap = new HashMap<String, List<ResAppeal>>();
				for(ResAppeal appealTemp : appealList){
					String key = appealTemp.getSchDeptFlow()+appealTemp.getOperUserFlow();
					if(appealMap.get(key)==null){
						List<ResAppeal> appealTempList = new ArrayList<ResAppeal>();
						appealTempList.add(appealTemp);
						appealMap.put(key,appealTempList);
					}else{
						appealMap.get(key).add(appealTemp);
					}
				}
				model.addAttribute("appealMap",appealMap);
			}
		}
		return "res/teacher/audit/appealAuditList";
	}

	/**
	 * 登记统计报表
	 */
	@RequestMapping(value="/registryReport/{roleFlag}",method=RequestMethod.GET)
	public String registryReport(@PathVariable String roleFlag,Model model){
		model.addAttribute("roleFlag",roleFlag);
		return "res/teacher/report/registryReportMain";
	}
	@RequestMapping(value="/registryReportList",method=RequestMethod.GET)
	public String registryReportList(String recTypeId,String roleFlag,String isCurrentFlag, Model model){
		if(StringUtil.isNotBlank(recTypeId)){
			ResDoctorSchProcess process = null;
			if(StringUtil.isNotBlank(isCurrentFlag)){
				process = new ResDoctorSchProcess();
				process.setIsCurrentFlag(isCurrentFlag);
			}

			ResRec rec = new ResRec();
			rec.setRecTypeId(recTypeId);
			//rec.setStatusId(RecStatusEnum.Submit.getId());
			List<ResRecExt> recExtList = this.resRecBiz.searchRegistryList(GlobalContext.getCurrentUser().getUserFlow(), roleFlag,rec,process);
			model.addAttribute("recExtList", recExtList);
		}
		return "res/teacher/report/registryReportList";
	}

	/**
	 * 申述查询
	 */
	@RequestMapping(value="/appealReport/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String appealReport(@PathVariable String roleFlag,String isCurrentFlag,ResAppeal appeal,Model model){
		model.addAttribute("roleFlag",roleFlag);

		String userFlow = GlobalContext.getCurrentUser().getUserFlow();

		if(appeal!=null && appeal.getRecTypeId()==null){
            isCurrentFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}

		ResDoctorSchProcess doctorProcess = new ResDoctorSchProcess();
		doctorProcess.setIsCurrentFlag(isCurrentFlag);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
			doctorProcess.setTeacherUserFlow(userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
			doctorProcess.setHeadUserFlow(userFlow);
		}
		List<ResDoctorSchProcess> doctorProcessList=null;
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		doctorProcessList = doctorProcessBiz.searchDoctorProcess(isOpen,null,doctorProcess);

		if(doctorProcessList!=null && doctorProcessList.size()>0){
			model.addAttribute("doctorProcessList",doctorProcessList);

			List<String> schDeptFlows = new ArrayList<String>();
			for(ResDoctorSchProcess doctorProcessTemp : doctorProcessList){
				if(!schDeptFlows.contains(doctorProcessTemp.getSchDeptFlow())){
					schDeptFlows.add(doctorProcessTemp.getSchDeptFlow());
				}
			}

			List<ResAppeal> appealList = resRecBiz.searchAppeal(schDeptFlows,appeal);
			if(appealList!=null && appealList.size()>0){
				Map<String,List<ResAppeal>> appealMap = new HashMap<String, List<ResAppeal>>();
				for(ResAppeal appealTemp : appealList){
					String key = appealTemp.getSchDeptFlow()+appealTemp.getOperUserFlow();
					if(appealMap.get(key)==null){
						List<ResAppeal> appealTempList = new ArrayList<ResAppeal>();
						appealTempList.add(appealTemp);
						appealMap.put(key,appealTempList);
					}else{
						appealMap.get(key).add(appealTemp);
					}
				}
				model.addAttribute("appealMap",appealMap);
			}
		}
		return "res/teacher/report/appealReport";
	}

	/**
	 *  出科情况查询
	 */
	@RequestMapping(value="/docoutReport/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String docoutReport(@PathVariable String roleFlag,String isCurrentFlag,ResRec rec,Model model){
		model.addAttribute("roleFlag",roleFlag);

		String userFlow = GlobalContext.getCurrentUser().getUserFlow();

		if(rec!=null && rec.getAuditStatusId()==null){
            isCurrentFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}

		ResDoctorSchProcess doctorProcess = new ResDoctorSchProcess();
		doctorProcess.setIsCurrentFlag(isCurrentFlag);
        doctorProcess.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
			doctorProcess.setTeacherUserFlow(userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
			doctorProcess.setHeadUserFlow(userFlow);
		}

		List<ResDoctorSchProcess> doctorProcessList = null;
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		doctorProcessList = doctorProcessBiz.searchDoctorProcess(isOpen,null,doctorProcess);

		if(doctorProcessList!=null && doctorProcessList.size()>0){
			model.addAttribute("doctorProcessList",doctorProcessList);

			List<String> schDeptFlows = new ArrayList<String>();
			for(ResDoctorSchProcess doctorProcessTemp : doctorProcessList){
				if(!schDeptFlows.contains(doctorProcessTemp.getSchDeptFlow())){
					schDeptFlows.add(doctorProcessTemp.getSchDeptFlow());
				}
			}

			Map<String,ResRec> recMap = new HashMap<String,ResRec>();

            rec.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
			//rec.setStatusId(RecStatusEnum.Submit.getId());
			List<ResRec> recSummaryList = resRecBiz.searchResRec(schDeptFlows,rec);
			if(recSummaryList!=null && recSummaryList.size()>0){
				for(ResRec recTemp : recSummaryList){
					String key = recTemp.getSchDeptFlow()+recTemp.getOperUserFlow()+"summary";
					recMap.put(key,recTemp);
				}
			}

            rec.setRecTypeId(com.pinde.core.common.enums.ResRecTypeEnum.AfterEvaluation.getId());
			List<ResRec> recEvaluationList = resRecBiz.searchResRec(schDeptFlows,rec);
			Map<String,String> scoreMap = null;
			if(recEvaluationList!=null && recEvaluationList.size()>0){
				scoreMap = new HashMap<String, String>();
				for(ResRec recTemp : recEvaluationList){
					String key = recTemp.getSchDeptFlow()+recTemp.getOperUserFlow()+"evaluation";
					recMap.put(key,recTemp);
					if(StringUtil.isNotBlank(recTemp.getRecContent())){
						Map<String,Object> recContentMap = resRecBiz.parseRecContent(recTemp.getRecContent());
						scoreMap.put(key, (String) recContentMap.get("totalScore"));
					}
				}
			}
			model.addAttribute("recMap",recMap);
			model.addAttribute("scoreMap",scoreMap);
		}
		return "res/teacher/report/docoutReport";
	}

	/**
	 *   出科情况查询
	 */
	@RequestMapping(value="/prepareinReport/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String prepareinReport(@PathVariable String roleFlag,ResRec rec,Model model){
		model.addAttribute("roleFlag",roleFlag);

		String userFlow = GlobalContext.getCurrentUser().getUserFlow();

		ResDoctorSchProcess doctorProcess = new ResDoctorSchProcess();
        doctorProcess.setSchFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
			doctorProcess.setTeacherUserFlow(userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
			doctorProcess.setHeadUserFlow(userFlow);
		}
		List<ResDoctorSchProcess> doctorProcessList=null;
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		doctorProcessList = doctorProcessBiz.searchDoctorProcess(isOpen,null,doctorProcess);

		if(doctorProcessList!=null && doctorProcessList.size()>0){
			model.addAttribute("doctorProcessList",doctorProcessList);

			List<String> doctorFlows = new ArrayList<String>();
			for(ResDoctorSchProcess doctorProcessTemp : doctorProcessList){
				if(!doctorFlows.contains(doctorProcessTemp.getUserFlow())){
					doctorFlows.add(doctorProcessTemp.getUserFlow());
				}
			}
			List<SchArrangeResult> resultList = resultBiz.searchArrangeResultByDoctorFlows(doctorFlows);
			if(resultList!=null && resultList.size()>0){
				Map<String,Map<String,Object>> resultMap = new HashMap<String,Map<String,Object>>();

				Map<String,List<String>> resultFlowMap = new HashMap<String, List<String>>();
				Map<String,SchArrangeResult> allResultMap = new HashMap<String,SchArrangeResult>();
				for(SchArrangeResult result : resultList){
					allResultMap.put(result.getResultFlow(),result);
					if(resultFlowMap.get(result.getDoctorFlow())==null){
						List<String> resultFlowList = new ArrayList<String>();
						resultFlowList.add(result.getResultFlow());
						resultFlowMap.put(result.getDoctorFlow(),resultFlowList);
					}else{
						resultFlowMap.get(result.getDoctorFlow()).add(result.getResultFlow());
					}
				}

				Integer count = 0;
				for(ResDoctorSchProcess doctorProcessTemp : doctorProcessList){
					Map<String,Object> dataTempMap = new HashMap<String, Object>();

					String resultFlow = doctorProcessTemp.getSchResultFlow();

					dataTempMap.put("currentResult",allResultMap.get(resultFlow));
					List<String> resultFlowsTemp = resultFlowMap.get(doctorProcessTemp.getUserFlow());
					if(resultFlowsTemp!=null){
						int index = resultFlowsTemp.indexOf(resultFlow);
						if(index!=-1 && (index+1)<=(resultFlowsTemp.size()-1)){
							String nextFlow = resultFlowsTemp.get(index+1);
							dataTempMap.put("nextResult",allResultMap.get(nextFlow));
							count++;
						}
					}
					resultMap.put(doctorProcessTemp.getUserFlow(),dataTempMap);
				}

				model.addAttribute("resultMap",resultMap);
				model.addAttribute("count",count);
			}

		}

		return "res/teacher/report/prepareinReport";
	}

	/**
	 * 轮转计划列表
	 */
	@RequestMapping(value="/resultList",method={RequestMethod.GET,RequestMethod.POST})
	public String resultList(String doctorFlow,Model model){
		if(StringUtil.isNotBlank(doctorFlow)){
			ResDoctor doctor = doctorBiz.readDoctor(doctorFlow);
			model.addAttribute("doctor",doctor);

			List<SchArrangeResult> resultList = resultBiz.searchSchArrangeResultByDoctor(doctorFlow);
			model.addAttribute("resultList",resultList);
		}
		return "res/teacher/report/resultList";
	}

	/**
	 * 带教老师考评查询
	 */
	@RequestMapping(value="/teacherScore/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String teacherScore(@PathVariable String roleFlag,String isCurrentFlag,String sessionNumber,Model model){
		model.addAttribute("roleFlag",roleFlag);

		SysUser user = GlobalContext.getCurrentUser();

		String cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
		List<ResAssessCfgTitleForm> titleFormList = _getTitleFormList(cfgCodeId,user.getOrgFlow());
		model.addAttribute("titleFormList",titleFormList);

        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId();
		if(sessionNumber==null){
            isCurrentFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
//		List<ResRecExt> recExtList = resRecBiz.searchScoreList(user.getUserFlow(),roleFlag,sessionNumber,recTypeId,isCurrentFlag);
		Map<String,Object> paramMap = new HashMap<>();
		List<DeptTeacherGradeInfoExt> recExtList = resGradeBiz.searchScoreList(user.getUserFlow(),roleFlag,sessionNumber,recTypeId,isCurrentFlag);
		if(recExtList!=null && recExtList.size()>0){
            if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
				model.addAttribute("recExtList",recExtList);
            } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
				Map<String,String> teacherMap = new HashMap<String,String>();
				Map<String,List<DeptTeacherGradeInfoExt>> resExtMap = new HashMap<String, List<DeptTeacherGradeInfoExt>>();
				for(DeptTeacherGradeInfoExt recExt : recExtList){
					ResDoctorSchProcess doctorProcess = recExt.getProcess();
					if(doctorProcess != null){
						teacherMap.put(doctorProcess.getTeacherUserFlow(),doctorProcess.getTeacherUserName());
						if(resExtMap.get(doctorProcess.getTeacherUserFlow())==null){
							List<DeptTeacherGradeInfoExt> recExtTempList = new ArrayList<DeptTeacherGradeInfoExt>();
							recExtTempList.add(recExt);
							resExtMap.put(doctorProcess.getTeacherUserFlow(),recExtTempList);
						}else{
							resExtMap.get(doctorProcess.getTeacherUserFlow()).add(recExt);
						}
					}
				}
				model.addAttribute("teacherMap",teacherMap);
				model.addAttribute("resExtMap",resExtMap);
			}

			Map<String,Map<String,Object>> scoreMap = new HashMap<String, Map<String,Object>>();
			for(DeptTeacherGradeInfoExt recExt : recExtList){
				Map<String,Object> scoreDate = resRecBiz.parseGradeXml(recExt.getRecContent());
				scoreMap.put(recExt.getSchDeptFlow()+recExt.getOperUserFlow(),scoreDate);
			}
			model.addAttribute("scoreMap",scoreMap);
		}

		return "res/teacher/teacherScore";
	}

	private List<ResAssessCfgTitleForm> _getTitleFormList(String cfgCodeId,String orgFlow){
		List<ResAssessCfgTitleForm> titleFormList = null;

		ResAssessCfg search = new ResAssessCfg();
		search.setCfgCodeId(cfgCodeId);
//		search.setOrgFlow(orgFlow);
		List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
		if(assessCfgList != null && !assessCfgList.isEmpty()){
			ResAssessCfg assessCfg = assessCfgList.get(0);
			try {
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					titleFormList = new ArrayList<ResAssessCfgTitleForm>();
					for(Element te :titleElementList){
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for(Element ie : itemElementList){
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
				}
			} catch (Exception e) {
                logger.error("", e);
			}
		}
		return titleFormList;
	}

	@RequestMapping(value="/headScore",method={RequestMethod.GET,RequestMethod.POST})
	public String headScore(String isCurrentFlag,String sessionNumber,Model model){
		SysUser user = GlobalContext.getCurrentUser();

		String cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
		List<ResAssessCfgTitleForm> titleFormList = _getTitleFormList(cfgCodeId,user.getOrgFlow());
		model.addAttribute("titleFormList",titleFormList);

        String recTypeId = com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId();
		if(sessionNumber==null){
            isCurrentFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
//		List<ResRecExt> recExtList = resRecBiz.searchScoreList(user.getUserFlow(),com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD,sessionNumber,recTypeId,isCurrentFlag);
        List<DeptTeacherGradeInfoExt> recExtList = resGradeBiz.searchScoreList(user.getUserFlow(), com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD, sessionNumber, recTypeId, isCurrentFlag);
		if(recExtList!=null && recExtList.size()>0){
			model.addAttribute("recExtList",recExtList);

			Map<String,Map<String,Object>> scoreMap = new HashMap<String, Map<String,Object>>();
			for(DeptTeacherGradeInfoExt recExt : recExtList){
				Map<String,Object> scoreDate = resRecBiz.parseGradeXml(recExt.getRecContent());
				scoreMap.put(recExt.getSchDeptFlow()+recExt.getOperUserFlow(),scoreDate);
			}
			model.addAttribute("scoreMap",scoreMap);
		}

		return "res/teacher/headScore";
	}

	@RequestMapping(value="/teacherAudit",method={RequestMethod.GET,RequestMethod.POST})
	public String teacherAudit(String isCurrentFlag,String schDeptFlow,Model model){
		SysUser user = GlobalContext.getCurrentUser();
		List<SchDept> deptList = schDeptBiz.searchSchDept(user.getDeptFlow());
		model.addAttribute("deptList",deptList);

		if(schDeptFlow==null){
            isCurrentFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}

		List<ResRecExt> recExtList = resRecBiz.searchTeacherAudit(schDeptFlow,isCurrentFlag,user.getUserFlow());
		if(recExtList!=null && recExtList.size()>0){
			Map<String,Map<String,Object>> auditCountMap = new HashMap<String, Map<String,Object>>();
			for(ResRecExt recExt : recExtList){
				ResDoctorSchProcess process = recExt.getProcess();
				if(process!=null){
					String key = process.getSchDeptFlow()+process.getTeacherUserFlow();
					if(auditCountMap.get(key)==null){
						Map<String,Object> dataMap = new HashMap<String, Object>();
						dataMap.put("schDeptName",process.getSchDeptName());
						dataMap.put("teacherUserName",process.getTeacherUserName());
						if(StringUtil.isNotBlank(recExt.getAuditStatusId())){
							dataMap.put("isNotAudit",0);
							dataMap.put("isAudit",1);
						}else{
							dataMap.put("isNotAudit",1);
							dataMap.put("isAudit",0);
						}
						auditCountMap.put(key,dataMap);
					}else{
						Map<String,Object> dataMap = auditCountMap.get(key);
						if(StringUtil.isNotBlank(recExt.getAuditStatusId())){
							dataMap.put("isAudit",(Integer)dataMap.get("isAudit")+1);
						}else{
							dataMap.put("isNotAudit",(Integer)dataMap.get("isNotAudit")+1);
						}
					}
				}
			}
			model.addAttribute("auditCountMap",auditCountMap);
		}

		return "res/teacher/teacherAudit";
	}

	/**
	 * 请假审批
	 * @param model
	 * @return
	 */
	@RequestMapping(value={"/absenceAudit/{resRoleScope}"},method={RequestMethod.GET,RequestMethod.POST})
	public String absenceAudit(@PathVariable String resRoleScope, SchDoctorAbsence doctorAbsence, Integer currentPage, HttpServletRequest request,  Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		doctorAbsence.setOrgFlow(currUser.getOrgFlow());
        doctorAbsence.setIsRegister(com.pinde.core.common.GlobalConstant.FLAG_N);
		//查询后台配置是否为进修过程页面
		String flag = InitConfig.getSysCfg("is_show_jxres");

		if("teaching".equals(resRoleScope)){//带教
			doctorAbsence.setTeachingFlow(currUser.getUserFlow());
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)) {
			doctorAbsence.setTeacherFlow(currUser.getUserFlow());
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)) {
			doctorAbsence.setTrainingSpeId(currUser.getResTrainingSpeId());
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<SchDoctorAbsence> doctorAbsenceList = schDoctorAbsenceBiz.searchSchDoctorAbsenceList(doctorAbsence);
		if(doctorAbsenceList!=null && !doctorAbsenceList.isEmpty()){
			List<String> fileFlowList = new ArrayList<String>();
			for(SchDoctorAbsence da : doctorAbsenceList){
				if(StringUtil.isNotBlank(da.getMakingFile())){
					fileFlowList.add(da.getMakingFile());
				}
			}
			Map<String,PubFile> fileMap = new HashMap<String, PubFile>();
			if(fileFlowList.size() > 0){
				List<PubFile> pubFileList =	fileBiz.searchFile(fileFlowList);
				for(PubFile file :pubFileList){
					fileMap.put(file.getFileFlow(), file);
				}
				model.addAttribute("fileMap", fileMap);
			}
		}
		model.addAttribute("doctorAbsenceList", doctorAbsenceList);
		model.addAttribute("resRoleScope", resRoleScope);

		// 医院管理员
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(resRoleScope)) {
			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(currUser.getOrgFlow());
			model.addAttribute("schDeptList", schDeptList);
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag))
			return "res/college/absenceAuditList_jx";
		return "res/college/absenceAuditList";
	}

	/**
	 * 打开填写审核意见页面
	 */
	@RequestMapping(value="/saveAbsenceInfo")
	public String saveAbsenceInfo(String absenceFlow,String agreeFlag,String resRoleScope,Model model){
		model.addAttribute("absenceFlow",absenceFlow);
		model.addAttribute("agreeFlag",agreeFlag);
		model.addAttribute("resRoleScope",resRoleScope);
		return "res/college/saveAbsenceInfo";
	}

	/**
	 * 保存请假审批
	 * @param
	 * @return
	 */
	@RequestMapping(value="/saveAbsenceAudit")
	@ResponseBody
	public String saveAbsenceAudit(SchDoctorAbsence doctorAbsence0) throws ParseException {
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/log/add",method={RequestMethod.GET,RequestMethod.POST})
	public String addRecord(){
		return "res/log/add";
	}

	/**
	 * 缺勤登记
	 */
	@RequestMapping(value={"/absentManage/{resRoleScope}"},method={RequestMethod.GET,RequestMethod.POST})
	public String absentRegist(@PathVariable String resRoleScope,SchDoctorAbsence doctorAbsence,ResDoctor doctor,Integer currentPage,
							   HttpServletRequest request,  Model model){
		model.addAttribute("resRoleScope",resRoleScope);

		SysUser currUser = GlobalContext.getCurrentUser();
        if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(resRoleScope)) {
			doctorAbsence.setOrgFlow(currUser.getOrgFlow());
			doctor.setOrgFlow(currUser.getOrgFlow());
		}
		List<ResDoctorExt> doctorList = null;
//		if(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope) || com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)){
		Map<String,Object> paramMap = new HashMap<String,Object>();
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)) {//带教老师
			paramMap.put("teacherUserFlow", currUser.getUserFlow());
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)) {//科主任
			paramMap.put("headUserFlow", currUser.getUserFlow());
		}
		if("headSecretary".equals(resRoleScope)){//规培秘书
			paramMap.put("headSecretaryFlow", currUser.getUserFlow());
		}
		paramMap.put("doctor", doctor);
		doctorList = doctorBiz.searchDocByteacher(paramMap);
//		}
//		else {
			//PageHelper.startPage(currentPage,getPageSize(request));
//			doctor.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//			doctorList = doctorBiz.searchByDoc(doctor);
//		}

		model.addAttribute("doctorList",doctorList);

		Map<String,Object> paramMap2 = new HashMap<String,Object>();
		paramMap2.put("doctorAbsence", doctorAbsence);
		List<SchDoctorAbsence> doctorAbsenceList = schDoctorAbsenceBiz.searchDoctorAbsence(paramMap2);

		if(doctorAbsenceList != null && doctorAbsenceList.size()>0){
			Map<String,List<SchDoctorAbsence>> docAbsenceMap = new HashMap<String,List<SchDoctorAbsence>>();
			for(SchDoctorAbsence docAbsence : doctorAbsenceList){
				if(docAbsenceMap.get(docAbsence.getDoctorFlow()) == null){
					List<SchDoctorAbsence> docAbsenceTempList = new ArrayList<SchDoctorAbsence>();
					docAbsenceTempList.add(docAbsence);
					docAbsenceMap.put(docAbsence.getDoctorFlow(),docAbsenceTempList);
				}else{
					docAbsenceMap.get(docAbsence.getDoctorFlow()).add(docAbsence);
				}
			}
			model.addAttribute("docAbsenceMap",docAbsenceMap);
		}

		// 医院管理员、平台管理
//		if(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(resRoleScope) || com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(resRoleScope)){
//			List<SchDept> schDeptList = schDeptBiz.searchSchDeptList(currUser.getOrgFlow());
//			model.addAttribute("schDeptList", schDeptList);
//		}
		return "res/college/absenceList";
	}

	//缺勤导出
	@RequestMapping(value={"/exportDoc/{resRoleScope}"},method={RequestMethod.GET,RequestMethod.POST})
	public void exportDoc(@PathVariable String resRoleScope,SchDoctorAbsence doctorAbsence,ResDoctor doctor, Model model,HttpServletResponse response) throws Exception {
		model.addAttribute("resRoleScope",resRoleScope);
		SysUser currUser = GlobalContext.getCurrentUser();
        if (!com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(resRoleScope)) {
			doctorAbsence.setOrgFlow(currUser.getOrgFlow());
			doctor.setOrgFlow(currUser.getOrgFlow());
		}
		List<ResDoctorExt> doctorList = null;
		Map<String,Object> paramMap = new HashMap<String,Object>();
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(resRoleScope)) {//带教老师
			paramMap.put("teacherUserFlow", currUser.getUserFlow());
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(resRoleScope)) {//科主任
			paramMap.put("headUserFlow", currUser.getUserFlow());
		}
		if("headSecretary".equals(resRoleScope)){//规培秘书
			paramMap.put("headSecretaryFlow", currUser.getUserFlow());
		}
		paramMap.put("doctor", doctor);
		doctorList = doctorBiz.searchDocByteacher(paramMap);

		Map<String,Object> paramMap2 = new HashMap<String,Object>();
		List<SchDoctorAbsence> doctorAbsenceList = schDoctorAbsenceBiz.searchDoctorAbsence(paramMap2);

		Map<String,List<SchDoctorAbsence>> docAbsenceMap = new HashMap<String,List<SchDoctorAbsence>>();
		if(doctorAbsenceList != null && doctorAbsenceList.size()>0){
			for(SchDoctorAbsence docAbsence : doctorAbsenceList){
				if(docAbsenceMap.get(docAbsence.getDoctorFlow()) == null){
					List<SchDoctorAbsence> docAbsenceTempList = new ArrayList<SchDoctorAbsence>();
					docAbsenceTempList.add(docAbsence);
					docAbsenceMap.put(docAbsence.getDoctorFlow(),docAbsenceTempList);
				}else{
					docAbsenceMap.get(docAbsence.getDoctorFlow()).add(docAbsence);
				}
			}
		}
		List<Map<String,Object>> exportMapList = new ArrayList<>();
		if(doctorList!=null&&doctorList.size()>0){
			for(ResDoctorExt doctorExt:doctorList){
				String doctorFlow = doctorExt.getDoctorFlow();
				List<SchDoctorAbsence> absenceList = docAbsenceMap.get(doctorFlow);
				if(absenceList!=null&&absenceList.size()>0){
					for(SchDoctorAbsence absence:absenceList){
						Map<String,Object> subMap = new HashMap<>();
						subMap.put("userName",doctorExt.getSysUser().getUserName());
						subMap.put("sexName",doctorExt.getSysUser().getSexName());
						subMap.put("userPhone",doctorExt.getSysUser().getUserPhone());
						subMap.put("sessionNumber",doctorExt.getSessionNumber());
						subMap.put("speName",doctorExt.getTrainingSpeName());
						subMap.put("schDeptName",absence.getSchDeptName());
						subMap.put("startDate",absence.getStartDate());
						subMap.put("endDate",absence.getEndDate());
						subMap.put("intervalDay",absence.getIntervalDay());
						subMap.put("absenceTypeName",absence.getAbsenceTypeName());
						subMap.put("absenceReson",absence.getAbsenceReson());
						exportMapList.add(subMap);
					}
				}
			}
		}
		String fileName = "缺勤信息统计.xls";
		String titles[] = {
				"userName:姓名",
				"sexName:性别",
				"userPhone:联系方式",
				"sessionNumber:年级",
				"speName:培训专业",
				"schDeptName:轮转科室",
				"startDate:开始时间",
				"endDate:结束时间",
				"intervalDay:缺勤天数",
				"absenceTypeName:缺勤类型",
				"absenceReson:缺勤原因"
		};
		fileName = URLEncoder.encode(fileName, "UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportMapList, response.getOutputStream());
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	//导入缺勤人员
	@RequestMapping(value="/importAbsence")
	@ResponseBody
	public String importStudentExcel(MultipartFile file){
//		if(file.getSize() > 0){
//			try{
//				int result = schDoctorAbsenceBiz.importDict(file);
//				if(com.pinde.core.common.GlobalConstant.ZERO_LINE != result){
//					return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
//				}else{
//					return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
//				}
//			}catch(RuntimeException e) {
//				logger.error("",e);
//				return re.getMessage();
//			}
//		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}


	@RequestMapping(value="/showMarks/{scope}" )
	public String showMarks(@PathVariable String scope, Model model,String isShow,HttpServletRequest request,String sessionNumber,String doctorName,Integer currentPage,String orgFlow){
       model.addAttribute("scope",scope);
        if (StringUtil.isBlank(sessionNumber) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isShow)) {
			boolean yearFlag=false;
            List<SysDict> dicts = com.pinde.core.common.enums.DictTypeEnum.DoctorSessionNumber.getSysDictList();
			for (SysDict sd:dicts) {
				if(DateUtil.getYear().equals(sd.getDictName())){
					yearFlag=true;
				}
			}
			if(yearFlag){
				sessionNumber = DateUtil.getYear();
			}else {
            	sessionNumber = dicts.get(0).getDictName();
			}
        }
        SysUser currentUser = GlobalContext.getCurrentUser();
        model.addAttribute("sessionNumber",sessionNumber);
        ResDoctorScoreExt doctorExt = new ResDoctorScoreExt();

		doctorExt.setDoctorName(doctorName);
        doctorExt.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		doctorExt.setSessionNumber(sessionNumber);

        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_GLOBAL.equals(scope)) {//平台
			doctorExt.setOrgFlow(orgFlow);
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(scope)) {//医院
			doctorExt.setOrgFlow(currentUser.getOrgFlow());
		}
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR.equals(scope)) {//学员
			SysUser user = new SysUser();
			user.setUserFlow(currentUser.getUserFlow());
			doctorExt.setSysUser(user);
		}
		if (currentPage == null) {
			currentPage = 1;
		}
		List<SysOrg> orgList = orgBiz.searchSysOrg();
		model.addAttribute("orgList", orgList);
		model.addAttribute("orgFlow",orgFlow);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResDoctorScoreExt> doctorExtList = doctorBiz.searchDocGrades(doctorExt);
		model.addAttribute("doctorList", doctorExtList);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_DOCTOR.equals(scope)) {
			return "res/doctor/theoreticalMarks";
		}
		return "res/college/theoreticalMarks";
	}

	@RequestMapping(value="/showImport")
	public String showImport(){return "res/college/importGrades";}

	@RequestMapping(value="importGrades")
	public @ResponseBody String importGrades(MultipartFile file){
		if(file.getSize() > 0){
			try{
				int result = resScoreBiz.importScoreFromExcel(file);
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
	@RequestMapping(value="/showSysRecords" )
	public String showSysRecords(Model model,String isShow,HttpServletRequest request,String sessionNumber,String startDate,String endDate,Integer currentPage){
        if (StringUtil.isBlank(sessionNumber) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isShow)) {
            sessionNumber = DateUtil.getYear();
        }
        model.addAttribute("sessionNumber",sessionNumber);
        String wsId = (String) getSessionAttribute(com.pinde.core.common.GlobalConstant.CURRENT_WS_ID);
		SysCfg cfg=new SysCfg();
		cfg.setWsId(wsId);
		List<SysCfg> sysCfgList=cfgBiz.search(cfg);
		Map<String, String> sysCfgMap=new HashMap<String, String>();
		if(sysCfgList != null && sysCfgList.size() > 0){
			for(SysCfg sysCfg:sysCfgList ){
				sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
				if(StringUtil.isNotBlank(sysCfg.getCfgBigValue())){
					sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgBigValue());
				}
			}
		}
		List<String> recTypeIds = new ArrayList<>();
		for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
				recTypeIds.add(regType.getId());
			}
		}
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
		Map<String,Object> beMap= new HashMap<>();
        if(StringUtil.isNotBlank(startDate)){
            startDate = DateUtil.transDateTime(startDate,"yyyy-MM-dd","yyyyMMdd")+"000000";
        }
        if(StringUtil.isNotBlank(endDate)){
            endDate = DateUtil.transDateTime(endDate,"yyyy-MM-dd","yyyyMMdd")+"235959";
        }
		beMap.put("startDate",startDate);
		beMap.put("endDate",endDate);
		beMap.put("sessionNumber",sessionNumber);
		beMap.put("recTypeIds",recTypeIds);
		PageHelper.startPage(currentPage, getPageSize(request));
		List<Map<String,Object>> records = doctorBiz.searchSysRecords(beMap);
		model.addAttribute("sysCfgMap",sysCfgMap);
		model.addAttribute("records",records);
		return "res/college/stuSysRecords";
	}
	@RequestMapping(value="/teachPlanList",method={RequestMethod.GET,RequestMethod.POST})
	public String teachPlanList(String userFlow,String schDeptFlow,Model model){
		userFlow = StringUtil.defaultIfEmpty(userFlow,GlobalContext.getCurrentUser().getUserFlow());
		if(StringUtil.isNotBlank(userFlow)){
			SysUser user = userBiz.readSysUser(userFlow);
			if(user!=null){
				String deptFlow = user.getDeptFlow();
				if(StringUtil.isNotBlank(deptFlow)){
					List<SchDept> schDeptList =  schDeptBiz.searchSchDept(deptFlow);
					model.addAttribute("schDeptList",schDeptList);
				}
			}
//			List<SchDept> schDeptList = schDeptBiz.searchTeachDept(userFlow);
//			model.addAttribute("schDeptList",schDeptList);

//			if(StringUtil.isNotBlank(schDeptFlow)){
//				List<SchDept> schDeptList =  new ArrayList<SchDept>();
//				SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
//				schDeptList.add(dept);
//				model.addAttribute("schDeptList",schDeptList);
//			}

            List<ResRec> recList = resRecBiz.searchByRecWithBLOBs(com.pinde.core.common.enums.ResRecTypeEnum.TeachRegistry.getId(), userFlow);
			if(recList!=null && recList.size()>0){
				Map<String,Map<String,Object>> contentMap = new HashMap<String, Map<String,Object>>();
				Map<String,ResRec> recMap = new HashMap<String, ResRec>();
				for(ResRec rec : recList){
					recMap.put(rec.getSchDeptFlow(),rec);

					String content = rec.getRecContent();
					Map<String,Object> recContentMap = resRecBiz.parseContent(content);
					contentMap.put(rec.getRecFlow(),recContentMap);
				}
				model.addAttribute("contentMap",contentMap);
				model.addAttribute("recMap",recMap);
			}
		}
		return "res/teacher/plan/list";
	}

	@RequestMapping(value="/editTeachPlan")
	public String editTeachPlan(String recFlow,String schDeptFlow,String recordFlow,Model model){
		model.addAttribute("user",GlobalContext.getCurrentUser());

		if(StringUtil.isNotBlank(schDeptFlow)){
			SchDept schDept = schDeptBiz.readSchDept(schDeptFlow);
			model.addAttribute("schDept",schDept);
		}

		if(StringUtil.isNotBlank(recFlow)){
			ResRec rec = resRecBiz.readResRec(recFlow);
			if(rec!=null){
				model.addAttribute("rec",rec);

				if(StringUtil.isNotBlank(recordFlow)){
					String content = rec.getRecContent();
					Map<String,Object> dataMap = resRecBiz.parseTeachPlanContent(content,recordFlow);
					model.addAttribute("dataMap",dataMap);
				}
			}
		}
		return "res/teacher/plan/edit";
	}

	@RequestMapping(value="/saveTeachPlan")
	@ResponseBody
	public String saveTeachPlan(String recFlow,String schDeptFlow,HttpServletRequest req){
        int result = resRecBiz.saveRegistryForm(com.pinde.core.common.enums.ResRecTypeEnum.TeachRegistry.getId(), recFlow, schDeptFlow, null, req, null, "", "");
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	@RequestMapping(value="/preTrainRecords",method={RequestMethod.GET,RequestMethod.POST})
	public String preTrainRecords(String isCurrentFlag,String userName,Model model){
		if(userName==null){
            isCurrentFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
			model.addAttribute("isCurrentFlag",isCurrentFlag);
		}
		ResDoctorSchProcess process = new ResDoctorSchProcess();
		process.setTeacherUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		process.setIsCurrentFlag(isCurrentFlag);

		ResDoctor doctor = new ResDoctor();
		doctor.setDoctorName(userName);
		List<ResDoctorSchProcess> processList = null;
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");

		processList = doctorProcessBiz.searchProcessByDoctorNew(isOpen,doctor,process,null,"");

		if(processList!=null && processList.size()>0){
			model.addAttribute("processList",processList);

			List<String> userFlows = new ArrayList<String>();
			List<String> processFlows = new ArrayList<String>();
			for(ResDoctorSchProcess processTemp : processList){
				String userFlow = processTemp.getUserFlow();
				String processFlow = processTemp.getProcessFlow();
				if(!userFlows.contains(userFlow)){
					userFlows.add(userFlow);
				}
				if(!processFlows.contains(processFlow)){
					processFlows.add(processFlow);
				}
			}
            List<ResRec> recList = resRecBiz.searchRecByUserAndSchdept(userFlows, processFlows, com.pinde.core.common.enums.ResRecTypeEnum.PreTrainForm.getId(), null);
			if(recList!=null && recList.size()>0){
				Map<String,ResRec> recMap = new HashMap<String, ResRec>();
				for(ResRec rec : recList){
					recMap.put(rec.getOperUserFlow()+rec.getProcessFlow(),rec);
				}
				model.addAttribute("recMap",recMap);
			}

			List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
				for(ResDoctor doctorTemp : doctorList){
					doctorMap.put(doctorTemp.getDoctorFlow(),doctorTemp);
				}
				model.addAttribute("doctorMap",doctorMap);
			}
		}

		return "res/edu/teacher/preTrainRecords";
	}

	/**
	 * 年度培训表
	 * @param isCurrentFlag
	 * @param userName
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/annualTrainRecords",method={RequestMethod.GET,RequestMethod.POST})
	public String annualTrainRecords(String isCurrentFlag,String userName,Model model){
		if(userName==null){
            isCurrentFlag = com.pinde.core.common.GlobalConstant.FLAG_Y;
			model.addAttribute("isCurrentFlag",isCurrentFlag);
		}
		ResDoctorSchProcess process = new ResDoctorSchProcess();
		process.setTeacherUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		process.setIsCurrentFlag(isCurrentFlag);

		ResDoctor doctor = new ResDoctor();
		doctor.setDoctorName(userName);
		List<ResDoctorSchProcess> processList=null;

		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		processList = doctorProcessBiz.searchProcessByDoctorNew(isOpen,doctor,process,null,"");

		if(processList!=null && processList.size()>0){
			List<String> userFlows = new ArrayList<String>();
			for(ResDoctorSchProcess processTemp : processList){
				if(!userFlows.contains(processTemp.getUserFlow())){
					userFlows.add(processTemp.getUserFlow());
				}
			}
			model.addAttribute("userFlows",userFlows);

            List<ResRec> recList = resRecBiz.searchRecByUserFlows(userFlows, com.pinde.core.common.enums.ResRecTypeEnum.AnnualTrainForm.getId());
			if(recList!=null && recList.size()>0){
				Map<String,ResRec> recMap = new HashMap<String, ResRec>();
				for(ResRec rec : recList){
					recMap.put(rec.getOperUserFlow(),rec);
				}
				model.addAttribute("recMap",recMap);
			}

			List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
				for(ResDoctor doctorTemp : doctorList){
					doctorMap.put(doctorTemp.getDoctorFlow(),doctorTemp);
				}
				model.addAttribute("doctorMap",doctorMap);
			}
		}

		return "res/edu/teacher/annualTrainRecords";
	}

	/**
	 * 全局表单审核,基地主任
	 * @param recTypeId
	 * @param roleFlag
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/globalFormAudit/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String globalFormAudit(@PathVariable String roleFlag,String recTypeId,String userFlow,String deptFlow,Model model){
		model.addAttribute("roleFlag",roleFlag);

		userFlow = StringUtil.defaultIfEmpty(userFlow,GlobalContext.getCurrentUser().getUserFlow());
		if(StringUtil.isNotBlank(recTypeId)){
			//获取用户所有部门
			SysUser user = new SysUser();
			user.setUserFlow(userFlow);
			List<SysUserDept> sysDeptList = userBiz.getUserDept(user);
			if(sysDeptList!=null && sysDeptList.size()>0){
				List<String> deptFlows = new ArrayList<String>();
				for(SysUserDept sud : sysDeptList){
					String currDeptFlow = sud.getDeptFlow();
					if(!deptFlows.contains(currDeptFlow)){
						deptFlows.add(currDeptFlow);
					}
				}
				List<SysDept> sDeptList = deptBiz.searchDeptByFlows(deptFlows);
				model.addAttribute("sDeptList",sDeptList);
			}

			List<SchDept> deptList = null;
			if(StringUtil.isNotBlank(deptFlow)){
				deptList = schDeptBiz.searchSchDept(deptFlow);
			}else{
				//获取用户所有轮转科室
				deptList = schDeptBiz.userSchDept(userFlow);
			}
			if(deptList!=null && deptList.size()>0){
				List<String> schDeptFlows = new ArrayList<String>();
				for(SchDept sd : deptList){
					String currSdFlow = sd.getSchDeptFlow();
					if(!deptList.contains(currSdFlow)){
						schDeptFlows.add(currSdFlow);
					}
				}
				if(schDeptFlows.size()>0){
					List<ResRec> recList = resRecBiz.searchByDeptWithBLOBs(recTypeId,schDeptFlows);
					if(recList!=null && recList.size()>0){
						model.addAttribute("recList",recList);

						Map<String,List<Map<String,String>>> viewListMap = new HashMap<String, List<Map<String,String>>>();
						Map<String,List<ResRec>> recListMap = new HashMap<String, List<ResRec>>();
						List<String> userFlows = new ArrayList<String>();
						for(ResRec rec : recList){
							String content = rec.getRecContent();
							String key = rec.getRecFlow();
							String operUserFlow = rec.getOperUserFlow();

							String form = rec.getRecForm();
							String type = rec.getRecTypeId();
							String ver = rec.getRecVersion();

							List<Map<String,String>> viewMapList = resRecBiz.parseViewValue(form,type,ver,content);
							viewListMap.put(key,viewMapList);

							if(recListMap.get(operUserFlow)==null){
								List<ResRec> recs = new ArrayList<ResRec>();
								recs.add(rec);
								recListMap.put(operUserFlow,recs);
								userFlows.add(operUserFlow);
							}else{
								recListMap.get(operUserFlow).add(rec);
							}
						}
						model.addAttribute("viewListMap",viewListMap);
						model.addAttribute("recListMap",recListMap);

						if(userFlows!=null && userFlows.size()>0){
							List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
							model.addAttribute("userList",userList);

							List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
							if(doctorList!=null && doctorList.size()>0){
								Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
								for(ResDoctor doctor : doctorList){
									doctorMap.put(doctor.getDoctorFlow(),doctor);
								}
								model.addAttribute("doctorMap",doctorMap);
							}
						}
					}
				}
			}
		}
		return "/res/teacher/globalFormAudit";
	}

	/**
	 * 出科考核数据查询
	 * @param roleFlag
	 * @param model
	 * @param doctor
	 * @param process
	 * @param schDeptFlow
     * @param isFirst
     * @return
     */
	@RequestMapping(value="/afterFormAudit/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String afterFormAudit(@PathVariable String roleFlag,Model model,ResDoctor doctor,ResDoctorSchProcess process, String schDeptFlow,String isFirst,Integer currentPage, HttpServletRequest request,String [] datas,String dshStatus){
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}
		model.addAttribute("dataStr",dataStr);
		model.addAttribute("roleFlag",roleFlag);
//		//默认选中界别
//		if (StringUtil.isBlank(isFirst)) {
//			String sessionNumber = com.pinde.core.common.enums.DictTypeEnum.DoctorSessionNumber.getSysDictList().get(0).getDictName();
//			doctor.setSessionNumber(sessionNumber);
//			model.addAttribute("sessionNumber1",sessionNumber);
//		}
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        //process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(currentPage==null){
			currentPage=1;
		}
		Map<String,String> roleFlagMap = new HashMap<String, String>();
		roleFlagMap.put("roleFlag",roleFlag);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
			process.setTeacherUserFlow(userFlow);
			//processList = doctorProcessBiz.searchProcessByDoctor(doctor, process);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
			roleFlagMap.put("val",userFlow);
			//process.setHeadUserFlow(userFlow);
			//processList = doctorProcessBiz.searchProcessByDoctor(doctor, process);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)) {
			roleFlagMap.put("val",userFlow);
			//processList = doctorProcessBiz.searchCurrentProcessByUserFlow(userFlow,process.getIsCurrentFlag());
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_PROFESSIONALBASE.equals(roleFlag)) {
			roleFlagMap.put("val",userFlow);
			roleFlagMap.put("trainingSpeId", GlobalContext.getCurrentUser().getResTrainingSpeId());
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleFlag)) {
			roleFlagMap.put("val",GlobalContext.getCurrentUser().getOrgFlow());
			//processList = doctorProcessBiz.searchProcessByDoctor(doctor, process);
		}

		String cfg13= InitConfig.getSysCfg("jswjw_"+GlobalContext.getCurrentUser().getOrgFlow()+"_P013");
		String f=InitConfig.getSysCfg("res_isGlobalSch_flag");
		model.addAttribute("cfg13",cfg13);
        String haveMonth = com.pinde.core.common.GlobalConstant.FLAG_N;
		String monthFlag = InitConfig.getSysCfg("jswjw_"+ GlobalContext.getCurrentUser().getOrgFlow()+"_M001");
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(monthFlag))
		{
            haveMonth = com.pinde.core.common.GlobalConstant.FLAG_Y;
		}
		model.addAttribute("isMonthOpen",haveMonth);

		List<String> recTypeIds = new ArrayList<String>();
		List<String> recTypeIds2 = new ArrayList<String>();
		List<String> recTypeIds3 = new ArrayList<String>();
		List<String> recTypeIds4 = new ArrayList<String>();

        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
					InitConfig.getSysCfg("res_"+AfterRecTypeEnum.DOPS.getId()+"_form_flag"))) {
				recTypeIds.add(AfterRecTypeEnum.DOPS.getId());
				recTypeIds2.add(AfterRecTypeEnum.DOPS.getId());
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
					InitConfig.getSysCfg("res_"+AfterRecTypeEnum.Mini_CEX.getId()+"_form_flag"))) {
				recTypeIds.add(AfterRecTypeEnum.Mini_CEX.getId());
				recTypeIds2.add(AfterRecTypeEnum.Mini_CEX.getId());
			}

            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
					InitConfig.getSysCfg("res_"+AfterRecTypeEnum.DiscipleSummary.getId()+"_form_flag"))) {
				recTypeIds3.add(AfterRecTypeEnum.DiscipleSummary.getId());
				recTypeIds2.add(AfterRecTypeEnum.DiscipleSummary.getId());
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
					InitConfig.getSysCfg("res_"+AfterRecTypeEnum.AfterSummary.getId()+"_form_flag"))) {
				recTypeIds3.add(AfterRecTypeEnum.AfterSummary.getId());
				recTypeIds2.add(AfterRecTypeEnum.AfterSummary.getId());
			}
            if (haveMonth.equals(com.pinde.core.common.GlobalConstant.FLAG_Y))
			{
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
						InitConfig.getSysCfg("res_"+AfterRecTypeEnum.MonthlyAssessment_clinic.getId()+"_form_flag"))) {
					recTypeIds4.add(AfterRecTypeEnum.MonthlyAssessment_clinic.getId());
					recTypeIds2.add(AfterRecTypeEnum.MonthlyAssessment_clinic.getId());
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
						InitConfig.getSysCfg("res_"+AfterRecTypeEnum.MonthlyAssessment_inpatientArea.getId()+"_form_flag"))) {
					recTypeIds4.add(AfterRecTypeEnum.MonthlyAssessment_inpatientArea.getId());
					recTypeIds2.add(AfterRecTypeEnum.MonthlyAssessment_inpatientArea.getId());
				}
			}
		}else{
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
					InitConfig.getSysCfg("res_"+AfterRecTypeEnum.DOPS.getId()+"_form_flag"))) {
				recTypeIds2.add(AfterRecTypeEnum.DOPS.getId());
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
					InitConfig.getSysCfg("res_"+AfterRecTypeEnum.Mini_CEX.getId()+"_form_flag"))) {
				recTypeIds2.add(AfterRecTypeEnum.Mini_CEX.getId());
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
					InitConfig.getSysCfg("res_"+AfterRecTypeEnum.DiscipleSummary.getId()+"_form_flag"))) {
				recTypeIds.add(AfterRecTypeEnum.DiscipleSummary.getId());
				recTypeIds2.add(AfterRecTypeEnum.DiscipleSummary.getId());
			}
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
					InitConfig.getSysCfg("res_"+AfterRecTypeEnum.AfterSummary.getId()+"_form_flag"))) {
				recTypeIds.add(AfterRecTypeEnum.AfterSummary.getId());
				recTypeIds2.add(AfterRecTypeEnum.AfterSummary.getId());
			}
            if (haveMonth.equals(com.pinde.core.common.GlobalConstant.FLAG_Y))
			{
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
						InitConfig.getSysCfg("res_"+AfterRecTypeEnum.MonthlyAssessment_clinic.getId()+"_form_flag"))) {
					recTypeIds.add(AfterRecTypeEnum.MonthlyAssessment_clinic.getId());
					recTypeIds2.add(AfterRecTypeEnum.MonthlyAssessment_clinic.getId());
				}
                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
						InitConfig.getSysCfg("res_"+AfterRecTypeEnum.MonthlyAssessment_inpatientArea.getId()+"_form_flag"))) {
					recTypeIds.add(AfterRecTypeEnum.MonthlyAssessment_inpatientArea.getId());
					recTypeIds2.add(AfterRecTypeEnum.MonthlyAssessment_inpatientArea.getId());
				}
			}
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(
				InitConfig.getSysCfg("res_"+AfterRecTypeEnum.AfterEvaluation.getId()+"_form_flag"))) {
			recTypeIds.add(AfterRecTypeEnum.AfterEvaluation.getId());
			recTypeIds2.add(AfterRecTypeEnum.AfterEvaluation.getId());
		}
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(cfg13) && !com.pinde.core.common.GlobalConstant.FLAG_Y.equals(f)) {
            recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptEval.getId());
            recTypeIds2.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptEval.getId());
		}

		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		List<ResDoctorSchProcess> processList = null;
		Map<String,Object> param = new HashMap<>();
		param.put("isOpen",isOpen);
		param.put("doctor",doctor);
		param.put("process",process);
		param.put("roleFlagMap",roleFlagMap);
		param.put("docTypeList",docTypeList);
		param.put("dshStatus",dshStatus);
		param.put("recTypeIds",recTypeIds);
		param.put("recTypeIds3",recTypeIds3);
		param.put("recTypeIds4",recTypeIds4);
		PageHelper.startPage(currentPage, getPageSize(request));
//		processList = doctorProcessBiz.searchProcessByDoctorNew(isOpen,doctor, process,roleFlagMap,"");
		processList = doctorProcessBiz.searchProcessByDoctorNew(param);

		if(processList!=null && processList.size()>0){
			model.addAttribute("processList",processList);
			List<String> userFlows = new ArrayList<String>();
			Map<String,SchArrangeResult> resultMapMap = new HashMap<String, SchArrangeResult>();
			Map<String,PubFile> fileMap = new HashMap<String, PubFile>();
			for(ResDoctorSchProcess rdsp : processList){
				if(!userFlows.contains(rdsp.getUserFlow()))
					userFlows.add(rdsp.getUserFlow());

				String resultFlow = rdsp.getSchResultFlow();
				SchArrangeResult result = resultBiz.readSchArrangeResult(resultFlow);
				resultMapMap.put(rdsp.getProcessFlow(), result);
				if (result != null) {
					PubFile file = fileBiz.readFile(result.getAfterFileFlow());
					fileMap.put(rdsp.getProcessFlow(), file);
				}
			}

			model.addAttribute("resultMap",resultMapMap);
			model.addAttribute("fileMap",fileMap);

			List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
			if(userList!=null && userList.size()>0){
				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser su : userList){
					userMap.put(su.getUserFlow(),su);
				}
				model.addAttribute("userMap",userMap);
			}

			List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
				for(ResDoctor rd : doctorList){
					doctorMap.put(rd.getDoctorFlow(),rd);
				}
				model.addAttribute("doctorMap",doctorMap);
			}
		}
//		List<SysUser> userList = userBiz.searchAfterAuditUser(process,null);
//		model.addAttribute("userList",userList);

		if(recTypeIds2.size()>0){
//			List<ResRec> recList = resRecBiz.searchAfterAuditRec(process,null,recTypeIds,roleFlagMap);
			List<ResSchProcessExpress> recList = expressBiz.searchAfterAuditRec(process,null,recTypeIds2,roleFlagMap);

			if(recList!=null && recList.size()>0){
				Map<String,ResSchProcessExpress> recMap = new HashMap<String, ResSchProcessExpress>();
				for(ResSchProcessExpress rec : recList){
					String key = rec.getProcessFlow()+rec.getRecTypeId();
					recMap.put(key,rec);
				}
				model.addAttribute("recMap",recMap);
			}
		}
        String flag = InitConfig.getSysCfg("is_show_jxres");//查询后台配置是否为进修过程管理页面
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(flag))
            return "/res/teacher/afterFormAuditList_jx";
		return "/res/teacher/afterFormAuditList";
	}

	//批量导出DOPS
	@RequestMapping("/batchExport")
	public void batchExport(String recType,String[] data,HttpServletRequest request,HttpServletResponse response) throws Exception {
		String folder=System.getProperty("java.io.tmpdir")+ File.separator;
		String zipFile = PkUtil.getUUID();
		String dir = folder+zipFile;
		File dirFile = new File(dir);
		if(dirFile.exists()==false){
			dirFile.mkdirs();
		}

		if(data!=null&&data.length>0){
			int i = 0;
			for(String recFlow:data){
				if(StringUtil.isNotBlank(recFlow)){
					ResSchProcessExpress resSchProcessExpress = resRecBiz.readResSchProcessExpress(recFlow);
					if(StringUtil.isNotBlank(resSchProcessExpress.getRecContent())){
						Map<String,Object> formDataMap= resRecBiz.parseRecContent(resSchProcessExpress.getRecContent());

						WordprocessingMLPackage temeplete1 = new WordprocessingMLPackage();
						String path = "";
						String name = "";
						if("DOPS".equals(recType)){
							path = "/jsp/res/resRecWord/WesternMedicine/hbszyysgfhpxglpt/DOPS.docx";//模板
							name = "临床操作技能评估量化表_"+resSchProcessExpress.getOperUserName()+"("+i+").docx";
						}else if("Mini_CEX".equals(recType)){
							path = "/jsp/res/resRecWord/WesternMedicine/hbszyysgfhpxglpt/Mini_CEX.docx";//模板
							name = "迷你临床演练评估量化表_"+resSchProcessExpress.getOperUserName()+"("+i+").docx";
						}
						ServletContext context = request.getServletContext();
						String watermark = "";
						temeplete1 = Docx4jUtil.convert(new File(context.getRealPath(path)), formDataMap, watermark, true);

						File f = new File(dir+File.separator+name);
						temeplete1.save(f);
						i++;
					}
				}
			}
		}

		ZipUtil.makeDirectoryToZip(dirFile, new File(folder+zipFile+".zip"), "", 7);
		String fileName = "";
		if("DOPS".equals(recType)){
			fileName = "临床操作技能评估量化表.zip";
		}else if("Mini_CEX".equals(recType)){
			fileName = "迷你临床演练评估量化表.zip";
		}
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
		byte[] data2 = getByte(new File(folder+zipFile+".zip"));
		if (data2 != null) {
			outputStream.write(data2);
			new File(folder+zipFile+".zip").delete();
		}
		outputStream.flush();
		outputStream.close();
	}

	public static byte[] getByte(File file) throws Exception {
		if (file == null) {
			return null;
		}
		try {
			int length = (int) file.length();
			if (length > Integer.MAX_VALUE) {    //当文件的长度超过了int的最大值
				System.out.println("this file is max ");
			}
			FileInputStream stream = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(length);
			byte[] b = new byte[length];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	@RequestMapping(value="/globalRegAudit/{roleFlag}",method={RequestMethod.GET,RequestMethod.POST})
	public String globalRegAudit(@PathVariable String roleFlag,Model model){
		model.addAttribute("roleFlag",roleFlag);

		return "/res/teacher/globalRegAudit";
	}
	@RequestMapping(value="/globalRegAuditList",method={RequestMethod.GET,RequestMethod.POST})
	public String globalRegAuditList(String roleFlag,String recTypeId,String doctorFlow,ResDoctorSchProcess process,String year,Model model, String operUserFlow, String trainingSpeId){
		String url = "/res/teacher/globalRegAuditList";

		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
        //process.setIsCurrentFlag(com.pinde.core.common.GlobalConstant.FLAG_Y);
		Map<String,String> roleFlagMap = new HashMap<String, String>();
		roleFlagMap.put("roleFlag",roleFlag);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER.equals(roleFlag)) {
			process.setTeacherUserFlow(userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD.equals(roleFlag)) {
			//process.setHeadUserFlow(userFlow);
			roleFlagMap.put("val",userFlow);
        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_MANAGER.equals(roleFlag)) {
			roleFlagMap.put("val",userFlow);
			roleFlagMap.put("trainingSpeId", trainingSpeId);

			SysUser curUser=GlobalContext.getCurrentUser();
			String curUserFlow=curUser.getUserFlow();
			List<ResUserSpe> resUserSpeList=resUserSpeBiz.searchUserSpesByUser(curUserFlow);
			model.addAttribute("resUserSpeList", resUserSpeList);

        } else if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(roleFlag)) {
			roleFlagMap.put("val",GlobalContext.getCurrentUser().getOrgFlow());
		}

		roleFlagMap.put("year",year);
		roleFlagMap.put("doctorFlow",doctorFlow);

		List<ResDoctorSchProcess> processList=null;
		//权限期间是否开通
		String isOpen = InitConfig.getSysCfg("res_permit_open_doc");
		processList = doctorProcessBiz.searchProcessByDoctorNew(isOpen,null,process,roleFlagMap,"");

		if(processList!=null && processList.size()>0){
			model.addAttribute("processList",processList);

			List<String> userFlows = new ArrayList<String>();
			for(ResDoctorSchProcess rdsp : processList){
				userFlows.add(rdsp.getUserFlow());
			}

			List<SysUser> userList = userBiz.searchSysUserByuserFlows(userFlows);
			if(userList!=null && userList.size()>0){
				model.addAttribute("userList",userList);

				Map<String,SysUser> userMap = new HashMap<String, SysUser>();
				for(SysUser su : userList){
					userMap.put(su.getUserFlow(),su);
				}
				model.addAttribute("userMap",userMap);
			}

			List<ResDoctor> doctorList = doctorBiz.searchDoctorByuserFlow(userFlows);
			if(doctorList!=null && doctorList.size()>0){
				Map<String,ResDoctor> doctorMap = new HashMap<String, ResDoctor>();
				for(ResDoctor rd : doctorList){
					doctorMap.put(rd.getDoctorFlow(),rd);
				}
				model.addAttribute("doctorMap",doctorMap);
			}
		}

		if(StringUtil.isNotBlank(recTypeId)){
			List<String> recTypeIds = new ArrayList<String>();
			recTypeIds.add(recTypeId);

			process.setUserFlow(doctorFlow);

			List<ResRec> recList = null;
			if(RegistryTypeEnum.AnnualTrainForm.getId().equals(recTypeId)){
				url = "/res/teacher/annualTrainFormList";
				if(StringUtil.isNotBlank(doctorFlow)){
					recList = resRecBiz.searchAfterAuditRec(process,null,recTypeIds,roleFlagMap);
					if(recList!=null && recList.size()>0){
						Map<String,Map<String,Object>> recDataMap = new HashMap<String, Map<String,Object>>();
						for(ResRec rr : recList){
							String recContent = rr.getRecContent();
							Map<String,Object> dataMap = resRecBiz.parseRecContent(recContent);
							recDataMap.put(rr.getRecFlow(),dataMap);
						}
						model.addAttribute("recDataMap",recDataMap);
					}
				}
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.CourseScore.getId().equals(recTypeId)) {
				url = "/res/teacher/courseScoreList";
				recList = resRecBiz.searchSXSRecData(recTypeIds,roleFlagMap);
				if(recList!=null && recList.size()>0){
					Map<String,ResRec> viewListMap = new HashMap<String, ResRec>();
					for(ResRec recTemp : recList){
						viewListMap.put(recTemp.getOperUserFlow(),recTemp);
					}
					model.addAttribute("viewListMap",viewListMap);
				}
			}else{
				String recTypeName = GlobalRecTypeEnum.getNameById(recTypeId);
				model.addAttribute("recTypeName",recTypeName);

				//recList = resRecBiz.searchAfterAuditRec(process,null,recTypeIds,roleFlagMap);
				recList = resRecBiz.searchSXSRecData(recTypeIds,roleFlagMap);
				if(recList!=null && recList.size()>0){
					Map<String,List<Map<String,String>>> viewListMap = new HashMap<String, List<Map<String,String>>>();
					for(ResRec recTemp : recList){
						String content = recTemp.getRecContent();
						String form = recTemp.getRecForm();
						String type = recTemp.getRecTypeId();
						String ver = recTemp.getRecVersion();

						List<Map<String,String>> viewInfoList = resRecBiz.parseViewValue(form,type,ver,content);
						viewListMap.put(recTemp.getRecFlow(),viewInfoList);
					}
					model.addAttribute("viewListMap",viewListMap);
				}
			}
			model.addAttribute("recList",recList);
		}
		return url;
	}

	/**
	 * 审核所有rec
	 * @param recFlows
	 * @param rec
	 * @return
	 */
	@RequestMapping(value="/auditRecs",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public String auditRecs(String[] recFlows,ResRec rec){
		int result = resRecBiz.auditRecs(recFlows,rec);
        if (result != com.pinde.core.common.GlobalConstant.ZERO_LINE) {
            return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
		}
        return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
	}

	@RequestMapping(value="/showDocAndUser",method={RequestMethod.GET,RequestMethod.POST})
	public String showDocAndUserInfo(String flow, Model model) throws DocumentException {
		if(StringUtil.isNotBlank(flow)){
			ResDoctor doctor=doctorBiz.searchByUserFlow(flow);
			model.addAttribute("doctor", doctor);
			SysUser user=userBiz.findByFlow(flow);
			model.addAttribute("user", user);
			//大字段信息
			PubUserResume pubUserResume = userResumeBiz.readPubUserResume(flow);
			if(pubUserResume != null){
				String xmlContent =  pubUserResume.getUserResume();
				if(StringUtil.isNotBlank(xmlContent)){
					//xml转换成JavaBean
					BaseUserResumeExtInfoForm extInfo=null;
					extInfo=userResumeBiz.converyToJavaBean(xmlContent,BaseUserResumeExtInfoForm.class);
					model.addAttribute("extInfo", extInfo);
				}
			}

			model.addAttribute("rotationInUse",false);
			List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchSchDoctorDept(flow);
			if(doctorDeptList!=null && doctorDeptList.size()>0){
				model.addAttribute("rotationInUse",true);
			}else{
				List<SchArrangeResult> resultList = resultBiz.searchSchArrangeResultByDoctor(flow);
				if(resultList!=null && resultList.size()>0){
					model.addAttribute("rotationInUse",true);
				}
			}
		}

		SysUser user=GlobalContext.getCurrentUser();
		List<SchRotation> rotationList = schRotationtBiz.searchSchRotationByIsPublish();
		model.addAttribute("rotationList",rotationList);


		SysDept dept = new SysDept();
        dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> deptList = deptBiz.searchDept(dept);
		model.addAttribute("deptList",deptList);

		//所有导师
		String tutorRoleFlow = InitConfig.getSysCfg("res_tutor_role_flow");
		if(StringUtil.isNotBlank(tutorRoleFlow)){
			SysUserRole searchObj = new SysUserRole();
			searchObj.setRoleFlow(tutorRoleFlow);
			List<SysUserRole> tutorRoleList = userRoleBiz.searchUserRole(searchObj);
			if(tutorRoleList!=null && tutorRoleList.size()>0){
				List<String> userFlows = new ArrayList<String>();
				for(SysUserRole sur : tutorRoleList){
					userFlows.add(sur.getUserFlow());
				}
				List<SysUser> tutorList = userBiz.searchSysUserByuserFlows(userFlows);
				model.addAttribute("tutorList",tutorList);
			}
		}

		if("Intern".equals(InitConfig.getSysCfg("res_sch_type"))){
			return "/res/teacher/showInternUserInfo";
		}else {
			return "/res/teacher/showDocAndUserInfo";//弃用editDocSimple.jsp
		}
	}


	@RequestMapping(value="/tutorView",method={RequestMethod.GET,RequestMethod.POST})
	public String tutorView(String sessionNumber,
			String doctorCategoryId,
			String trainingSpeId,
			//String specialized,
			//String deptFlow,
			String userName,
			//String doctorCode,
			//String idNo,
			Integer currentPage,
			String orgFlow,
			String deptFlow,
			HttpServletRequest request,
			Model model){

		SysUser currUser = GlobalContext.getCurrentUser();

		ResDoctorExt doctorExt = new ResDoctorExt();
		SysUser user = new SysUser();
		doctorExt.setSysUser(user);

        doctorExt.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		doctorExt.setSessionNumber(sessionNumber);
		doctorExt.setDoctorCategoryId(doctorCategoryId);
		doctorExt.setTrainingSpeId(trainingSpeId);
		doctorExt.setOrgFlow(orgFlow);
		//doctorExt.setSpecialized(specialized);
		//doctorExt.setDeptFlow(deptFlow);
		//doctorExt.setDoctorCode(doctorCode);

		user.setUserName(userName);
		//user.setIdNo(idNo);
		//user.setOrgFlow(orgFlow);
		user.setDeptFlow(deptFlow);

		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));

		doctorExt.setTutorFlow(currUser.getUserFlow());

		List<ResDoctorExt> doctorExtList = doctorBiz.searchDocUser(doctorExt, "");
		if(doctorExtList!=null && doctorExtList.size()>0){
			model.addAttribute("doctorExtList",doctorExtList);

			List<String> doctorFlows = new ArrayList<String>();
			for(ResDoctorExt doctorExtTemp : doctorExtList){
				doctorFlows.add(doctorExtTemp.getDoctorFlow());
			}

			List<Map<String,Object>> resultCountMapList = resultBiz.countResultByUser(doctorFlows);
			if(resultCountMapList!=null && resultCountMapList.size()>0){
				Map<String,Object> resultCountMap = new HashMap<String, Object>();
				for(Map<String,Object> map : resultCountMapList){
					resultCountMap.put((String)map.get("key"),map.get("value"));
				}
				model.addAttribute("resultCountMap",resultCountMap);
			}
			List<Map<String,Object>> processCountMapList = resRecBiz.countProcessByUser(doctorFlows);
			if(processCountMapList!=null && processCountMapList.size()>0){
				Map<String,Object> processCountMap = new HashMap<String, Object>();
				for(Map<String,Object> map : processCountMapList){
					processCountMap.put((String)map.get("key"),map.get("value"));
				}
				model.addAttribute("processCountMap",processCountMap);
			}
		}

		SysDept dept = new SysDept();
        dept.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		dept.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		List<SysDept> deptList = deptBiz.searchDept(dept);
		model.addAttribute("deptList",deptList);

		List<SysOrg> orgList = orgBiz.searchSysOrg();
		model.addAttribute("orgList",orgList);

        model.addAttribute("scope", com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TUTOR);

		return "/res/teacher/tutor/registryNoteList";
	}

	@RequestMapping(value="/signin",method={RequestMethod.GET,RequestMethod.POST})
	public String signin(Model model){
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		SysUserDeptExample example = new SysUserDeptExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysUserDept> deptList = sysUserDeptMapper.selectByExample(example);
		List<String> deptFlows = new ArrayList<String>();
		String deptFlowsStr = "";
		for(SysUserDept dept: deptList){
			deptFlows.add(dept.getDeptFlow());
			if(StringUtil.isNotBlank(deptFlowsStr)){
				deptFlowsStr+=",";
			}
			deptFlowsStr+=dept.getDeptFlow();
		}
		model.addAttribute("signUrl","func://funcFlow=signin&teacherDeptFlows="+deptFlowsStr);
		return "/res/teacher/signin";
	}
	@RequestMapping(value="/signinData",method={RequestMethod.GET,RequestMethod.POST})
	public String signinData(Model model,String signDate){
//		if(StringUtil.isBlank(signDate)){
//			signDate = DateUtil.getCurrDate();
//		}
		String userFlow = GlobalContext.getCurrentUser().getUserFlow();
		SysUserDeptExample example = new SysUserDeptExample();
        example.createCriteria().andUserFlowEqualTo(userFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		List<SysUserDept> deptList = sysUserDeptMapper.selectByExample(example);
		List<String> deptFlows = new ArrayList<String>();
		for(SysUserDept dept: deptList){
			deptFlows.add(dept.getDeptFlow());
		}
//		List<ResSignin> signList = doctorBiz.searchSignList(deptFlows,signDate);
//		model.addAttribute("signList",signList);

		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deptFlows",deptFlows);
		paramMap.put("signDate",DateUtil.getCurrDate());
		List<Map<String,Object>> signInMaps = signBiz.searchSign(paramMap);
		model.addAttribute("signInMaps",signInMaps);

		return "/res/teacher/signinData";
	}



    @RequestMapping(value="/exportMarks/{scope}" )
    @ResponseBody
    public void exportMarks(@PathVariable String scope,HttpServletResponse response, String sessionNumber, String doctorName, String orgFlow) throws Exception {
        SysUser currentUser = GlobalContext.getCurrentUser();
		ResDoctorScoreExt doctorExt = new ResDoctorScoreExt();
        doctorExt.setOrgFlow(orgFlow);
        if (com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_ADMIN.equals(scope)) {//医院
			doctorExt.setOrgFlow(currentUser.getOrgFlow());
		}
        doctorExt.setDoctorName(doctorName);
        doctorExt.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        doctorExt.setSessionNumber(sessionNumber);
        List<ResDoctorScoreExt> doctorExtList = doctorBiz.searchDocGrades(doctorExt);
        String[] titles={
                "sysUser.userName:姓名",
                "sysUser.sexName:性别",
                "sysUser.userCode:学号",
                "orgName:实习基地",
                "sessionNumber:届别",
                "resScore.octScore:10月",
                "resScore.marScore:3月",
                "resScore.junScore:6月",
                "avgScore:平均分"
        };

        String fileName = "实习理论考试查询.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		ExcleUtile.exportSimpleExcleByObjs(titles, doctorExtList, response.getOutputStream());
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

    @RequestMapping(value="/exportSysRecords" )
    @ResponseBody
    public void exportSysRecords(HttpServletResponse response,String sessionNumber,String startDate,String endDate) throws Exception {
        List<String> titleList = new ArrayList<>();
        titleList.add("orgName:单位");
        titleList.add("AfterSummary:出科小结");
        List<String> recTypeIds = new ArrayList<>();
        for(RegistryTypeEnum regType : RegistryTypeEnum.values()){
            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(InitConfig.getSysCfg("res_registry_type_" + regType.getId()))) {
                recTypeIds.add(regType.getId());
                titleList.add(regType.getId()+":"+regType.getName());
            }
        }
        titleList.add("TeacherGrade:带教评价");
        titleList.add("DeptGrade:科室评价");
        titleList.add("stuNum:实习生数");
        titleList.add("siginNum:考勤签到数");
        titleList.add("avgCount:平均签到次数");
        String[] titles = (String[])titleList.toArray(new String[titleList.size()]);
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.AfterSummary.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId());
        recTypeIds.add(com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId());
        Map<String,Object> beMap= new HashMap<>();
        if(StringUtil.isNotBlank(startDate)){
            startDate = DateUtil.transDateTime(startDate,"yyyy-MM-dd","yyyyMMdd")+"000000";
        }
        if(StringUtil.isNotBlank(endDate)){
            endDate = DateUtil.transDateTime(endDate,"yyyy-MM-dd","yyyyMMdd")+"235959";
        }
        beMap.put("startDate",startDate);
        beMap.put("endDate",endDate);
        beMap.put("sessionNumber",sessionNumber);
        beMap.put("recTypeIds",recTypeIds);
        List<Map<String,Object>> records = doctorBiz.searchSysRecords(beMap);
//        model.addAttribute("sysCfgMap",sysCfgMap);
//        model.addAttribute("records",records);
        ExcleUtile.exportSimpleExcleByObjs(titles, records, response.getOutputStream());
        String fileName = "实习生系统数据导出.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }

	/**
	 * 查看考勤信息
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param schStartDate
	 * @param schEndDate
	 * @param schDeptFlow
	 * @param teacherName
	 * @param statueId
	 * @param studentName
	 * @return
	 */
	@RequestMapping(value="attendanceManage/{roleId}")
	public String attendanceManage(Model model, Integer currentPage, @PathVariable String roleId,
								   String sessionNumber, HttpServletRequest request, String schStartDate,
								   String schEndDate, String schDeptFlow, String teacherName,
								   String statueId, String studentName, String searchType,String [] datas){
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}else{
            List<SysDict> dicts = com.pinde.core.common.enums.DictTypeEnum.DoctorType.getSysDictList();
			if(dicts!=null)
			{
				int i=0;
				datas=new String[dicts.size()];
				for(SysDict dict:dicts)
				{
					datas[i++]=dict.getDictId();
				}
				docTypeList = Arrays.asList(datas);
				for(String d : datas){
					dataStr += d+",";
				}
			}
		}
		model.addAttribute("dataStr",dataStr);
		model.addAttribute("docTypeList",docTypeList);
		SysUser sysUser=GlobalContext.getCurrentUser();
		List<SchDept> deptList = schDeptBiz.searchSchDeptList(sysUser.getOrgFlow());
		model.addAttribute("deptList",deptList);
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getCurrDate();
		}
		if(StringUtil.isBlank(schStartDate)){
//			schStartDate=DateUtil.addDate(DateUtil.getCurrDate(),-30);
			schStartDate=DateUtil.getFirstDayOfMonth();

		}
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("sessionNumber",sessionNumber);
		beMap.put("docTypeList",docTypeList);
		if("manager".equals(roleId)){
			if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
				beMap.put("orgFlow",sysUser.getOrgFlow());
			}
		}
		if("head".equals(roleId)){
			if(StringUtil.isNotBlank(sysUser.getUserFlow())){
				beMap.put("headUserFlow",sysUser.getUserFlow());
			}
		}
		if(StringUtil.isNotBlank(schDeptFlow)){
			beMap.put("schDeptFlow",schDeptFlow);
		}
		if("teacher".equals(roleId)){
			String teacherFlow=sysUser.getUserFlow();
			beMap.put("teacherFlow",teacherFlow);
		}
		if(StringUtil.isNotBlank(teacherName)){
			beMap.put("teacherName",teacherName);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		if("1".equals(statueId)||"0".equals(statueId)){
			beMap.put("statueId",statueId);
		}
		List<ResAttendanceExt> resAttendanceExts = new ArrayList<>();
		PageHelper.startPage(currentPage,getPageSize(request));
		resAttendanceExts=resAttendanceBiz.searchResAttendanceList(beMap);
		Map<String,List> attendanceDetailMap = new HashMap<String,List>();
		for (ResAttendanceExt resAttendanceExt : resAttendanceExts) {
			String jsResAttendanceFlow = resAttendanceExt.getJsresAttendance().getAttendanceFlow();
			if(StringUtil.isNotBlank(jsResAttendanceFlow)){
				List<JsresAttendanceDetail> JsresAttendanceDetails = resAttendanceBiz.searchJsresAttendanceDetailByAttendanceFlow(jsResAttendanceFlow);
				attendanceDetailMap.put(jsResAttendanceFlow,JsresAttendanceDetails);
			}
		}
		model.addAttribute("jsResAttendanceExts",resAttendanceExts);
		model.addAttribute("searchType",searchType);
		model.addAttribute("roleId",roleId);
		model.addAttribute("attendanceDetailMap",attendanceDetailMap);
		model.addAttribute("schEndDate",schEndDate);
		model.addAttribute("schStartDate",schStartDate);
		if("teacher".equals(roleId)){
			return "res/teacher/attendanceSearch";
		}
		return "res/hospital/attendanceSearch";
	}
	/**
	 * 导出考勤表
	 * @param request
	 * @param response
	 * @param schStartDate
	 * @param schEndDate
	 * @param schDeptFlow
	 * @param teacherName
	 * @param statueId
	 * @param studentName
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportAttendance")
	public void exportAttendance(HttpServletRequest request, HttpServletResponse response,String schStartDate,String sessionNumber, String schEndDate, String schDeptFlow, String teacherName, String statueId, String studentName,String roleId,String [] datas)throws Exception{
		SysUser sysUser=GlobalContext.getCurrentUser();
		Map<String,Object> beMap=new HashMap<String,Object>();
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("sessionNumber",sessionNumber);
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			beMap.put("docTypeList",docTypeList);
		}
		if("manager".equals(roleId)){
			if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
				beMap.put("orgFlow",sysUser.getOrgFlow());
			}
		}
		if(StringUtil.isNotBlank(schDeptFlow)){
			beMap.put("schDeptFlow",schDeptFlow);
		}
		if("teacher".equals(roleId)){
			String teacherFlow=sysUser.getUserFlow();
			beMap.put("teacherFlow",teacherFlow);
		}
		if(StringUtil.isNotBlank(teacherName)){
			beMap.put("teacherName",teacherName);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		if("1".equals(statueId)||"0".equals(statueId)){
			beMap.put("statueId",statueId);
		}
		List<ResAttendanceExt> resAttendanceExts = new ArrayList<>();
		resAttendanceExts=resAttendanceBiz.searchResAttendanceList(beMap);
		Map<String,List> attendanceDetailMap = new HashMap<String,List>();
		List<JsresAttendanceDetail> jsresAttendanceDetails = null;
		for (ResAttendanceExt jsResAttendanceExt : resAttendanceExts) {
			String jsResAttendanceFlow = jsResAttendanceExt.getJsresAttendance().getAttendanceFlow();
			if(StringUtil.isNotBlank(jsResAttendanceFlow)){
				jsresAttendanceDetails = resAttendanceBiz.searchJsresAttendanceDetailByAttendanceFlow(jsResAttendanceFlow);
				attendanceDetailMap.put(jsResAttendanceFlow,jsresAttendanceDetails);
			}
			SysDept sysDept=jsResAttendanceExt.getSysDept();
			if(sysDept!=null)
			{
				SchDept temp = schDeptBiz.readSchDept(sysDept.getDeptFlow());
				if(temp!=null){
					String deptOrgFlow = temp.getOrgFlow();
					if(StringUtil.isNotBlank(deptOrgFlow)){
						SysOrg so = orgBiz.readSysOrg(deptOrgFlow);
						if(so!=null && !so.getOrgFlow().equals(sysDept.getOrgFlow())){
							jsResAttendanceExt.getSysDept().setDeptName(jsResAttendanceExt.getSysDept().getDeptName()+("["+so.getOrgName()+"]"));
						}
					}
				}
			}
		}
		String[] headLines = null;
		headLines = new String[]{
				"学员考勤记录表"
		};
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("sheet1");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setWrapText(true);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);
		//列宽自适应
//		sheet.setDefaultColumnWidth((short)50);
		HSSFRow rowDep = sheet.createRow(0);//第一行
		rowDep.setHeightInPoints(20);
		HSSFCell cellOne = rowDep.createCell(0);
		cellOne.setCellStyle(styleCenter);
		cellOne.setCellValue("学员考勤记录表");

		HSSFRow rowTwo = sheet.createRow(1);//第二行
		String[] titles = new String[]{
				"编号",
				"日期",
				"姓名",
				"科室",
				"带教老师",
				"考勤记录",
				"出勤状态",
				"备注",
				"学员备注"
		};
		String[] teacherTitles = new String[]{
				"编号",
				"日期",
				"姓名",
				"考勤记录",
				"出勤状态",
				"备注",
				"学员备注"
		};
		if("teacher".equals(roleId)){
			titles = teacherTitles;
		}
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = rowTwo.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
			sheet.setColumnWidth(i, titles.length * 2 * 256);
		}
        if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE))) {
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 8));//合并单元格
		}else {
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 6));//合并单元格
		}
		int rowNum = 2;
		int inDocTotal=0;
		int outDocTotal=0;
		String[] resultList = null;
		String[] teacherResultList = null;
		if (resAttendanceExts != null && !resAttendanceExts.isEmpty()) {
			for (int i = 0; i < resAttendanceExts.size(); i++, rowNum++) {
				String attendanceDetailRecords = "";
				String attendanceSerfRemarks = "";
				List<JsresAttendanceDetail> newDetails =null;
				newDetails=attendanceDetailMap.get(resAttendanceExts.get(i).getJsresAttendance().getAttendanceFlow());
				if(newDetails != null && !resAttendanceExts.isEmpty()){
					for (JsresAttendanceDetail detail: newDetails) {
						if(detail.getAttendanceFlow().equals(resAttendanceExts.get(i).getJsresAttendance().getAttendanceFlow())){
							if(StringUtil.isNotBlank(detail.getAttendTime())){
								attendanceDetailRecords += "\n"+detail.getAttendTime()+"  ";
							}
							if(StringUtil.isNotBlank(detail.getSelfRemarks())){
								attendanceSerfRemarks += "\n"+detail.getAttendTime() +" :" +detail.getSelfRemarks()+"  ";
							}
						}
					}
				}else {

				}
				if(StringUtil.isBlank(attendanceDetailRecords.trim())){
					attendanceDetailRecords="暂无签到记录！";
				}
				if(StringUtil.isBlank(attendanceSerfRemarks.trim())){
					attendanceSerfRemarks="暂无备注！";
				}
				HSSFRow rowFour = sheet.createRow(rowNum);//第二行
				String statuesName = resAttendanceExts.get(i).getJsresAttendance().getAttendStatusName();
				if(StringUtil.isBlank(statuesName)){
					statuesName="待审核";
				}
				resultList = new String[]{
						i+1+"",
						resAttendanceExts.get(i).getJsresAttendance().getAttendDate(),
						resAttendanceExts.get(i).getUserName(),
						resAttendanceExts.get(i).getSchDept().getSchDeptName(),
						resAttendanceExts.get(i).getJsresAttendance().getTeacherName(),
						attendanceDetailRecords,
						statuesName,
						resAttendanceExts.get(i).getJsresAttendance().getTeacherRemarks(),
						attendanceSerfRemarks
				};
				teacherResultList = new String[]{
						i+1+"",
						resAttendanceExts.get(i).getJsresAttendance().getAttendDate(),
						resAttendanceExts.get(i).getUserName(),
						attendanceDetailRecords,
						statuesName,
						resAttendanceExts.get(i).getJsresAttendance().getTeacherRemarks(),
						attendanceSerfRemarks
				};
				if("teacher".equals(roleId)){
					resultList = teacherResultList;
				}
				for (int j = 0; j < titles.length; j++) {
					HSSFCell cellFirst = rowFour.createCell(j);
					cellFirst.setCellStyle(styleCenter);
					cellFirst.setCellValue(new HSSFRichTextString(resultList[j]));
				}

			}
		}
		String fileName = "学员考勤记录表.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}


	@RequestMapping(value="/attendanceSearchTab/{roleId}")
	public String attendanceSearchTab(Model model, Integer currentPage, @PathVariable String roleId,
									  HttpServletRequest request, String schStartDate, String schEndDate,
									  String schDeptFlow, String trainingTypeId,String sessionNumber, String datas[],
									  String trainingSpeId, String studentName, String searchType, String orgFlow){
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}else{
            List<SysDict> dicts = com.pinde.core.common.enums.DictTypeEnum.DoctorType.getSysDictList();
			if(dicts!=null)
			{
				int i=0;
				datas=new String[dicts.size()];
				for(SysDict dict:dicts)
				{
					datas[i++]=dict.getDictId();
				}
				docTypeList = Arrays.asList(datas);
				for(String d : datas){
					dataStr += d+",";
				}
			}
		}
		model.addAttribute("dataStr",dataStr);
		model.addAttribute("docTypeList",docTypeList);
		SysUser sysUser=GlobalContext.getCurrentUser();
		List<SchDept> deptList = schDeptBiz.searchSchDeptList(sysUser.getOrgFlow());
		model.addAttribute("deptList",deptList);
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getLastDateOfCurrMonth();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.getFirstDayOfMonth();
		}
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("sessionNumber",sessionNumber);
		beMap.put("docTypeList",docTypeList);
		if("manager".equals(roleId)){
			if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
				beMap.put("orgFlow",sysUser.getOrgFlow());
			}
		}
		if("head".equals(roleId)){
			if(StringUtil.isNotBlank(sysUser.getUserFlow())){
				beMap.put("headUserFlow",sysUser.getUserFlow());
			}
		}
		if(StringUtil.isNotBlank(schDeptFlow)){
			beMap.put("schDeptFlow",schDeptFlow);
		}
		if("teacher".equals(roleId)){
			String teacherFlow=sysUser.getUserFlow();
			beMap.put("teacherFlow",teacherFlow);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		List<Map<String,String>> resAttendanceExts = new ArrayList<>();
		PageHelper.startPage(currentPage,getPageSize(request));
		resAttendanceExts=resAttendanceBiz.searchResAttendanceList2(beMap);
		model.addAttribute("jsResAttendanceExts",resAttendanceExts);
		model.addAttribute("searchType",searchType);
		model.addAttribute("roleId",roleId);
		model.addAttribute("schEndDate",schEndDate);
		model.addAttribute("schStartDate",schStartDate);
		return "res/hospital/attendanceSearchTab";
	}

	@RequestMapping(value = "/exportAttendanceTab")
	public void exportAttendanceTab( String roleId,
									HttpServletRequest request, String schStartDate, String schEndDate,
									String schDeptFlow, String trainingTypeId,String sessionNumber, String datas[],
									String trainingSpeId, String studentName, String searchType, String orgFlow,
									HttpServletResponse response)throws Exception{
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}else{
            List<SysDict> dicts = com.pinde.core.common.enums.DictTypeEnum.DoctorType.getSysDictList();
			if(dicts!=null)
			{
				int i=0;
				datas=new String[dicts.size()];
				for(SysDict dict:dicts)
				{
					datas[i++]=dict.getDictId();
				}
				docTypeList = Arrays.asList(datas);
				for(String d : datas){
					dataStr += d+",";
				}
			}
		}
		SysUser sysUser=GlobalContext.getCurrentUser();
		List<SchDept> deptList = schDeptBiz.searchSchDeptList(sysUser.getOrgFlow());
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getLastDateOfCurrMonth();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.getFirstDayOfMonth();
		}
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("sessionNumber",sessionNumber);
		beMap.put("docTypeList",docTypeList);
		if("manager".equals(roleId)){
			if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
				beMap.put("orgFlow",sysUser.getOrgFlow());
			}
		}
		if("head".equals(roleId)){
			if(StringUtil.isNotBlank(sysUser.getUserFlow())){
				beMap.put("headUserFlow",sysUser.getUserFlow());
			}
		}
		if(StringUtil.isNotBlank(schDeptFlow)){
			beMap.put("schDeptFlow",schDeptFlow);
		}
		if("teacher".equals(roleId)){
			String teacherFlow=sysUser.getUserFlow();
			beMap.put("teacherFlow",teacherFlow);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		List<Map<String,String>> doctors = new ArrayList<>();
		List<String> sheetList= TimeUtil.getMonthsByTwoMonth(schStartDate.substring(0,7),schEndDate.substring(0,7));
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();

		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setWrapText(true);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);
		if(sheetList!=null&&sheetList.size()>0)
		{
			Map<Integer, Integer> columnWidth = new HashMap<>();
			for(String month:sheetList)
			{
				HSSFSheet sheet = wb.createSheet(month);
				List<String> days=TimeUtil.findDates2(month);
				String[] titles = new String[]{
						"姓名",
						"年级",
						"专业",
						"轮转科室"
				};
				String[] keys = new String[]{
						"userName",
						"sessionNumber",
						"trainingSpeName",
						"deptName"
				};
				int rowNum=0;
				int cellNum=0;
				HSSFRow headRow = sheet.createRow(rowNum++);//第一行

				HSSFCell cellTitle = null;
				for (int i = 0; i < titles.length; i++) {
					cellTitle = headRow.createCell(cellNum++);
					cellTitle.setCellValue(titles[i]);
					cellTitle.setCellStyle(styleCenter);
					ExcleUtile.setColumnWidth(titles[i].getBytes().length, cellNum-1, columnWidth);
				}
				if(days!=null)
				{
					for(String day:days)
					{
						if(day.compareTo(schStartDate)>=0&&day.compareTo(schEndDate)<=0)
						{
							cellTitle = headRow.createCell(cellNum++);
							cellTitle.setCellValue(day);
							cellTitle.setCellStyle(styleCenter);
							ExcleUtile.setColumnWidth(day.getBytes().length, cellNum-1, columnWidth);
						}
					}
				}
				cellTitle = headRow.createCell(cellNum++);
				cellTitle.setCellValue("出勤（天数）");
				cellTitle.setCellStyle(styleCenter);
				ExcleUtile.setColumnWidth("出勤（天数）".getBytes().length, cellNum-1, columnWidth);

				cellTitle = headRow.createCell(cellNum++);
				cellTitle.setCellValue("缺勤（天数）");
				cellTitle.setCellStyle(styleCenter);
				ExcleUtile.setColumnWidth("缺勤（天数）".getBytes().length, cellNum-1, columnWidth);

				beMap.put("month",month);
				beMap.put("days",days);
				doctors=resAttendanceBiz.exportResAttendanceList2(beMap);
				if(doctors!=null)
				{
					for(Map<String,String> doc:doctors)
					{
						String docFlow=doc.get("userFlow");
						headRow = sheet.createRow(rowNum++);//第一行
						cellNum=0;

						for (int i = 0; i < keys.length; i++) {
							String value=doc.get(keys[i]);
							if(StringUtil.isBlank(value))
							{
								value="";
							}
							cellTitle = headRow.createCell(cellNum++);
							cellTitle.setCellValue(value);
							cellTitle.setCellStyle(styleCenter);
							ExcleUtile.setColumnWidth(value.getBytes().length, cellNum-1, columnWidth);
						}
						int cq=0;
						int qq=0;
						if(days!=null)
						{
							for(String day:days)
							{
								if(day.compareTo(schStartDate)>=0&&day.compareTo(schEndDate)<=0) {
									String value = "×";
									String attendStatus = doc.get(day);
									if (StringUtil.isNotBlank(attendStatus)) {
										if ("1".equals(attendStatus)) {
											value = "√";
											cq++;
										}else if ("-1".equals(attendStatus)) {
											value = "-";
										}else if ("0".equals(attendStatus)) {
											qq++;
										}
									}else{
										qq++;
									}
									cellTitle = headRow.createCell(cellNum++);
									cellTitle.setCellValue(value);
									cellTitle.setCellStyle(styleCenter);
									ExcleUtile.setColumnWidth(value.getBytes().length, cellNum - 1, columnWidth);
								}
							}
						}
						cellTitle = headRow.createCell(cellNum++);
						cellTitle.setCellValue(cq+"");
						cellTitle.setCellStyle(styleCenter);
						ExcleUtile.setColumnWidth(cq+"".getBytes().length, cellNum-1, columnWidth);
						cellTitle = headRow.createCell(cellNum++);
						cellTitle.setCellValue(qq+"");
						cellTitle.setCellStyle(styleCenter);
						ExcleUtile.setColumnWidth(qq+"".getBytes().length, cellNum-1, columnWidth);
					}
				}

				Set<Integer> columnkeys = columnWidth.keySet();
				for (Integer key : columnkeys) {
					int width = columnWidth.get(key);
					sheet.setColumnWidth(key, width * 2 * 156>255*256?70*256:width * 2 * 156);
				}
			}
		}
		String fileName = "学员考勤统计.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	@RequestMapping(value = "/exportAttendanceTab2")
	public void exportAttendanceTab2( String roleId,
									HttpServletRequest request, String schStartDate, String schEndDate,
									String schDeptFlow, String trainingTypeId,String sessionNumber, String datas[],
									String trainingSpeId, String studentName, String searchType, String orgFlow,
									HttpServletResponse response)throws Exception{
		String dataStr = "";
		List<String> docTypeList = new ArrayList<>();
		if(datas!=null&&datas.length>0){
			docTypeList = Arrays.asList(datas);
			for(String d : datas){
				dataStr += d+",";
			}
		}else{
            List<SysDict> dicts = com.pinde.core.common.enums.DictTypeEnum.DoctorType.getSysDictList();
			if(dicts!=null)
			{
				int i=0;
				datas=new String[dicts.size()];
				for(SysDict dict:dicts)
				{
					datas[i++]=dict.getDictId();
				}
				docTypeList = Arrays.asList(datas);
				for(String d : datas){
					dataStr += d+",";
				}
			}
		}
		SysUser sysUser=GlobalContext.getCurrentUser();
		List<SchDept> deptList = schDeptBiz.searchSchDeptList(sysUser.getOrgFlow());
		Map<String,Object> beMap=new HashMap<String,Object>();
		if(StringUtil.isBlank(schEndDate)){
			schEndDate=DateUtil.getLastDateOfCurrMonth();
		}
		if(StringUtil.isBlank(schStartDate)){
			schStartDate=DateUtil.getFirstDayOfMonth();
		}
		beMap.put("schStartDate",schStartDate);
		beMap.put("schEndDate",schEndDate);
		beMap.put("sessionNumber",sessionNumber);
		beMap.put("docTypeList",docTypeList);
		if("manager".equals(roleId)){
			if(StringUtil.isNotBlank(sysUser.getOrgFlow())){
				beMap.put("orgFlow",sysUser.getOrgFlow());
			}
		}
		if("head".equals(roleId)){
			if(StringUtil.isNotBlank(sysUser.getUserFlow())){
				beMap.put("headUserFlow",sysUser.getUserFlow());
			}
		}
		if(StringUtil.isNotBlank(schDeptFlow)){
			beMap.put("schDeptFlow",schDeptFlow);
		}
		if("teacher".equals(roleId)){
			String teacherFlow=sysUser.getUserFlow();
			beMap.put("teacherFlow",teacherFlow);
		}
		if(StringUtil.isNotBlank(studentName)){
			beMap.put("studentName",studentName);
		}
		List<Map<String,String>> doctors = new ArrayList<>();
		List<String> sheetList= TimeUtil.getMonthsByTwoMonth(schStartDate.substring(0,7),schEndDate.substring(0,7));
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();

		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		styleCenter.setVerticalAlignment(VerticalAlignment.CENTER);
		styleCenter.setWrapText(true);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);

		HSSFCellStyle stylevwc = wb.createCellStyle(); //居中
		stylevwc.setAlignment(HorizontalAlignment.CENTER);
		stylevwc.setVerticalAlignment(VerticalAlignment.CENTER);
		styleLeft.setWrapText(true);
		if(sheetList!=null&&sheetList.size()>0)
		{
			Map<Integer, Integer> columnWidth = new HashMap<>();
			for(String month:sheetList)
			{
				HSSFSheet sheet = wb.createSheet(month);
				List<String> days=TimeUtil.findDates2(month);
				String[] titles = new String[]{
						"姓名",
						"年级",
						"专业",
						"轮转科室"
				};
				String[] keys = new String[]{
						"userName",
						"sessionNumber",
						"trainingSpeName",
						"deptName"
				};
				int rowNum=0;
				int cellNum=0;
				HSSFRow headRow = sheet.createRow(rowNum++);//第一行

				HSSFCell cellTitle = null;
				for (int i = 0; i < titles.length; i++) {
					cellTitle = headRow.createCell(cellNum++);
					cellTitle.setCellValue(titles[i]);
					cellTitle.setCellStyle(styleCenter);
					ExcleUtile.setColumnWidth(titles[i].getBytes().length, cellNum-1, columnWidth);
				}
				if(days!=null)
				{
					for(String day:days)
					{
						if(day.compareTo(schStartDate)>=0&&day.compareTo(schEndDate)<=0)
						{
							cellTitle = headRow.createCell(cellNum++);
							cellTitle.setCellValue(day);
							cellTitle.setCellStyle(styleCenter);
							ExcleUtile.setColumnWidth(day.getBytes().length, cellNum-1, columnWidth);
						}
					}
				}
				cellTitle = headRow.createCell(cellNum++);
				cellTitle.setCellValue("打卡天数");
				cellTitle.setCellStyle(styleCenter);
				ExcleUtile.setColumnWidth("打卡天数".getBytes().length, cellNum-1, columnWidth);

				beMap.put("month",month);
				beMap.put("days",days);
				doctors=resAttendanceBiz.exportResAttendanceList3(beMap);
				if(doctors!=null)
				{
					for(Map<String,String> doc:doctors)
					{
						String docFlow=doc.get("userFlow");
						headRow = sheet.createRow(rowNum++);//第一行
						cellNum=0;

						for (int i = 0; i < keys.length; i++) {
							String value=doc.get(keys[i]);
							if(StringUtil.isBlank(value))
							{
								value="";
							}
							cellTitle = headRow.createCell(cellNum++);
							cellTitle.setCellValue(value);
							cellTitle.setCellStyle(styleCenter);
							ExcleUtile.setColumnWidth(value.getBytes().length, cellNum-1, columnWidth);
						}
						int c=0;
						if(days!=null)
						{
							for(String day:days)
							{
								if(day.compareTo(schStartDate)>=0&&day.compareTo(schEndDate)<=0) {
									String value = "×";
									String attendStatus = doc.get(day);
									if (StringUtil.isNotBlank(attendStatus)) {
										value = "√";
										c++;
									}
									cellTitle = headRow.createCell(cellNum++);
									cellTitle.setCellValue(value);
									cellTitle.setCellStyle(styleCenter);
									ExcleUtile.setColumnWidth(value.getBytes().length, cellNum - 1, columnWidth);
								}
							}
						}

						cellTitle = headRow.createCell(cellNum++);
						cellTitle.setCellValue(c+"");
						cellTitle.setCellStyle(styleCenter);
						ExcleUtile.setColumnWidth(c+"".getBytes().length, cellNum-1, columnWidth);
					}
				}

				Set<Integer> columnkeys = columnWidth.keySet();
				for (Integer key : columnkeys) {
					int width = columnWidth.get(key);
					sheet.setColumnWidth(key, width * 2 * 156>255*256?70*256:width * 2 * 156);
				}
			}
		}
		String fileName = "学员考勤统计（不审核）.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	/**
	 * 带教老师一键通过
	 * @param attendanceFlowsStr
	 * @param doctorStr
	 * @param attendDatesStr
	 * @return
	 */
	@RequestMapping(value = "/modifySelected")
	public @ResponseBody String modifySelected(String attendanceFlowsStr,String doctorStr,String attendDatesStr){
		SysUser sysUser=GlobalContext.getCurrentUser();
		String[] attendanceFlows = attendanceFlowsStr.split(",");
		String[] doctorFlows = doctorStr.split(",");
		String[] attendDates = attendDatesStr.split(",");
		for (int i = 0; i < doctorFlows.length; i++) {
			JsresAttendance attendance =null;
			String attendanceFlow ="";
			if(i<attendanceFlows.length){
				attendanceFlow = attendanceFlows[i];
			}
			if(attendanceFlow.length() != 0)
			{
				attendance = resAttendanceBiz.searchJsresAttendanceByAttendanceFlow(attendanceFlow);
				attendance.setAttendStatus("1");
				attendance.setAttendStatusName("出勤");
			}else {
				attendance = new JsresAttendance();
				String doctorFlow = doctorFlows[i];
				String attendDate = attendDates[i];
				SysUser doctor = resAttendanceBiz.searchSysUserByUserFlow(doctorFlow);
				attendance.setDoctorFlow(doctorFlow);
				attendance.setDoctorName(doctor.getUserName());
				attendance.setAttendDate(attendDate);
				attendance.setTeacherFlow(sysUser.getUserFlow());
				attendance.setTeacherName(sysUser.getUserName());
				attendance.setAttendStatus("1");
				attendance.setAttendStatusName("出勤");
			}
			resAttendanceBiz.saveJsresAttendance(attendance);
		}
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}
	/**
	 * 带教老师修改出勤状态或添加备注
	 * @param attendanceFlow
	 * @param statueId
	 * @param statueName
	 * @param doctorFlow
	 * @param attendDate
	 * @param remarks
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyAttendance")
	public @ResponseBody String modifyAttendance(String attendanceFlow, String statueId, String statueName, String doctorFlow, String attendDate, String remarks)throws Exception {
		JsresAttendance jsresAttendance = null;
		statueName = java.net.URLDecoder.decode(statueName,"UTF-8");
		remarks = java.net.URLDecoder.decode(remarks,"UTF-8");
		SysUser sysUser=GlobalContext.getCurrentUser();
		SysUser doctor = resAttendanceBiz.searchSysUserByUserFlow(doctorFlow);
		if(StringUtil.isNotBlank(attendanceFlow)){
			jsresAttendance = resAttendanceBiz.searchJsresAttendanceByAttendanceFlow(attendanceFlow);
			if(jsresAttendance!=null){
				jsresAttendance.setAttendStatus(statueId);
				if(StringUtil.isNotBlank(statueName)){
					jsresAttendance.setAttendStatusName(statueName);
				}
				if(StringUtil.isNotBlank(remarks)){
					jsresAttendance.setTeacherRemarks(remarks);
				}
			}
		}else {
			jsresAttendance = new JsresAttendance();
			jsresAttendance.setDoctorFlow(doctorFlow);
			jsresAttendance.setDoctorName(doctor.getUserName());
			jsresAttendance.setAttendDate(attendDate);
			jsresAttendance.setTeacherFlow(sysUser.getUserFlow());
			jsresAttendance.setTeacherName(sysUser.getUserName());
			jsresAttendance.setAttendStatus(statueId);
			if(StringUtil.isNotBlank(statueName)){
				jsresAttendance.setAttendStatusName(statueName);
			}
			if(StringUtil.isNotBlank(remarks)){
				jsresAttendance.setTeacherRemarks(remarks);
			}
		}
		resAttendanceBiz.saveJsresAttendance(jsresAttendance);
        return com.pinde.core.common.GlobalConstant.OPERATE_SUCCESSED;
	}


	/**
	 * 带教/科室对学员评分
	 * @param recFlow
	 * @param recTypeId
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/grade")
	public String grade(String resultFlow,String schDeptFlow,String doctorFlow,String roleFlag,String isShow,String recFlow,String recTypeId,String processFlow,Model model) throws Exception{

		model.addAttribute("roleFlag", roleFlag);
		model.addAttribute("isShow", isShow);
		model.addAttribute("processFlow",processFlow);
		SysUser user = GlobalContext.getCurrentUser();
        //当前登陆人的userFlow
		String userFlow =user.getUserFlow();
		List<SchGradeFrom> results = arrangeBiz.searchGradeByDoctorFlow(schDeptFlow,userFlow,processFlow,doctorFlow,null,null,resultFlow);
		if(results!=null && results.size()>0) {
			model.addAttribute("results", results);
            if (roleFlag.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_TEACHER) && StringUtil.isNotBlank(results.get(0).getIsTeacher())) {
				recFlow = results.get(0).getIsTeacher();
            } else if (roleFlag.equals(com.pinde.core.common.GlobalConstant.RES_ROLE_SCOPE_HEAD) && StringUtil.isNotBlank(results.get(0).getIsHead())) {
				recFlow = results.get(0).getIsHead();
			}
		}

		if(StringUtil.isNotBlank(recTypeId)){
			String cfgCodeId = null;
            if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.TeacherAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.DeptGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.DeptAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.HeadDoctorGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.HeadDoctorAssess.getId();
            } else if (com.pinde.core.common.enums.ResRecTypeEnum.TeacherDoctorGrade.getId().equals(recTypeId)) {
				cfgCodeId = ResAssessTypeEnum.TeacherDoctorAssess.getId();
			}

			DeptTeacherGradeInfo deptTeacherGradeInfo = resGradeBiz.readResGrade(recFlow);
			if(deptTeacherGradeInfo != null){
				model.addAttribute("rec",deptTeacherGradeInfo);
				Map<String,Object> gradeMap = resRecBiz.parseGradeXml(deptTeacherGradeInfo.getRecContent());
				model.addAttribute("gradeMap",gradeMap);
			}

			ResAssessCfg search = new ResAssessCfg();
			search.setCfgCodeId(cfgCodeId);
			List<ResAssessCfg> assessCfgList = assessCfgBiz.searchAssessCfgList(search);
			if(assessCfgList != null && !assessCfgList.isEmpty()){
				ResAssessCfg assessCfg = assessCfgList.get(0);
				model.addAttribute("assessCfg",assessCfg);
				Document dom = DocumentHelper.parseText(assessCfg.getCfgBigValue());
				String titleXpath = "//title";
				List<Element> titleElementList = dom.selectNodes(titleXpath);
				if(titleElementList != null && !titleElementList.isEmpty()){
					List<ResAssessCfgTitleForm> titleFormList = new ArrayList<ResAssessCfgTitleForm>();
					for(Element te :titleElementList){
						ResAssessCfgTitleForm titleForm = new ResAssessCfgTitleForm();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						List<Element> itemElementList = te.elements("item");
						List<ResAssessCfgItemForm> itemFormList = null;
						if(itemElementList != null && !itemElementList.isEmpty()){
							itemFormList = new ArrayList<ResAssessCfgItemForm>();
							for(Element ie : itemElementList){
								ResAssessCfgItemForm itemForm = new ResAssessCfgItemForm();
								itemForm.setId(ie.attributeValue("id"));
								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
								itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
								itemFormList.add(itemForm);
							}
						}
						titleForm.setItemList(itemFormList);
						titleFormList.add(titleForm);
					}
					model.addAttribute("titleFormList", titleFormList);
				}
			}
		}
		return "gzzyjxres/teacher/grade";
	}

	@RequestMapping("/importThoerySkill")
	public String importThoerySkill(){
		return "res/teacher/importTheorySkill";
	}

	@RequestMapping(value="/importThoerySkillScore")
	@ResponseBody
	public String importThoerySkillScore(MultipartFile file){
		if(file.getSize() > 0){
			try{
				int result = resScoreBiz.importTheorySkillGrades(file);
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

}
