package com.pinde.sci.ctrl.osca;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pinde.core.entyties.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.osca.IOscaBaseBiz;
import com.pinde.sci.biz.osca.IOscaFormCfgBiz;
import com.pinde.sci.biz.osca.IOscaProvincialBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.osca.AssessmentProEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.osca.OscaFromCfgItemExt;
import com.pinde.sci.model.osca.OscaFromCfgTitleExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/osca/provincial")
public class OscaProvincialController extends GeneralController{
	@Autowired
	IOscaProvincialBiz provincialBiz;
	@Autowired
	IOrgBiz orgBiz;
	@Autowired
	IOscaFormCfgBiz formCfgBiz;
	@Autowired
	IOscaBaseBiz oscaBaseBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IDictBiz dictBiz;

	@RequestMapping("/skillsAssessmentList")
	public String list(OscaSkillsAssessment skillsAssessment, Model model, HttpServletRequest request,Integer currentPage,String trainingTypeId){
		SysDict searchDict = new SysDict();
		searchDict.setRecordStatus("Y");
		searchDict.setDictTypeId(DictTypeEnum.OscaTrainingType.getId()+"."+trainingTypeId);
		List<SysDict> dictList = dictBiz.searchDictList(searchDict);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<OscaSkillsAssessment> skillsAssessments =provincialBiz.searchSkillsAssessment(skillsAssessment,dictList);
		model.addAttribute("skillsAssessments",skillsAssessments);
		//人数
		Map<String,Integer> doctorNumMap = new HashMap<>();
		if(skillsAssessments!=null&&skillsAssessments.size()>0){
			for(OscaSkillsAssessment oscaSkillsAssessment:skillsAssessments){
				String clinicalFlow = oscaSkillsAssessment.getClinicalFlow();
				int doctorNum = provincialBiz.queryDoctorNum(clinicalFlow);
				doctorNumMap.put(clinicalFlow,doctorNum);
			}
		}
		//考核时间
		Map<String,List<OscaSkillsAssessmentTime>> timeMap = new HashMap<>();
		if(skillsAssessments!=null&&skillsAssessments.size()>0){
			for(OscaSkillsAssessment oscaSkillsAssessment:skillsAssessments){
				String clinicalFlow = oscaSkillsAssessment.getClinicalFlow();
				List<OscaSkillsAssessmentTime> oscaSkillsAssessmentTimes = oscaBaseBiz.getAssessmentTimes(clinicalFlow);
				timeMap.put(clinicalFlow,oscaSkillsAssessmentTimes);
			}
		}
		model.addAttribute("timeMap",timeMap);
		model.addAttribute("timeMapSize",timeMap.size());
		//考点：
		SysOrg org = new SysOrg();
		org.setIsExamOrg("Y");
		List<SysOrg> examOrgList = orgBiz.queryAllSysOrg(org);
		model.addAttribute("examOrgList", examOrgList);
		model.addAttribute("doctorNumMap",doctorNumMap);
		return "osca/provincial/skillsAssessmentList";
	}

	@RequestMapping("/doctorList")
	public String doctorList(String clinicalFlow,Model model, HttpServletRequest request,Integer currentPage){
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> doctorList = provincialBiz.searchDoctorList(clinicalFlow);
		model.addAttribute("doctorList",doctorList);
		return "osca/provincial/doctorList";
	}

	@RequestMapping("/doctorScoreList")
	public String doctorScoreList(String speId,String clinicalYear,String orgFlow,String trainingOrgFlow,String trainCategoryId,
								  Model model, HttpServletRequest request,Integer currentPage,String resultId,String trainingTypeId,
								  String idNo,String gradeDoctorName,String orgCityId){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("speId",speId);
		paramMap.put("clinicalYear",clinicalYear);
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("trainingOrgFlow",trainingOrgFlow);
		paramMap.put("trainCategoryId",trainCategoryId);
		paramMap.put("resultId",resultId);
		paramMap.put("idNo",idNo);
		paramMap.put("gradeDoctorName",gradeDoctorName);
		paramMap.put("orgCityId",orgCityId);
		SysDict searchDict = new SysDict();
		searchDict.setRecordStatus("Y");
		searchDict.setDictTypeId(DictTypeEnum.OscaTrainingType.getId()+"."+trainingTypeId);
		List<SysDict> dictList = dictBiz.searchDictList(searchDict);
		paramMap.put("dictList",dictList);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = provincialBiz.searchDoctorScoreList(paramMap);
		Map<String,String> scoreMap = new HashMap<>();//得分map
		List<String> clinicalFlows = new ArrayList<>();//发布成绩用clinical_flow List
		if(resultMapList!=null&&resultMapList.size()>0){
			for(Map<String,Object> map:resultMapList){
				String clinicalFlow = (String)map.get("clinicalFlow");
				String doctorFlow = (String)map.get("doctorFlow");
				if(!clinicalFlows.contains(clinicalFlow)){
					clinicalFlows.add(clinicalFlow);
				}
				List<Map<String,Object>> doctorScores = provincialBiz.queryDocScoresByOrder(doctorFlow,clinicalFlow);
				double sumDoc = 0;
				DecimalFormat df = new DecimalFormat("###");
				if(doctorScores!=null&&doctorScores.size()>0){
					for(Map<String,Object> doctorScore:doctorScores){
						String score1 = "0";
						if(doctorScore.get("examScore")!=null){
							score1 = df.format(Double.parseDouble(doctorScore.get("examScore").toString()));
						}
						if(StringUtil.isNotBlank(score1)){
							Double scoreDoc = Double.parseDouble(score1);
							sumDoc+=scoreDoc;
						}
					}
				}
				scoreMap.put(clinicalFlow+doctorFlow,df.format(sumDoc));
			}
		}
		model.addAttribute("clinicalFlows",clinicalFlows);
		model.addAttribute("scoreMap",scoreMap);
		model.addAttribute("resultMapList",resultMapList);
		// 培训基地：
		List<SysOrg> orgList = orgBiz.searchSysOrg();
		model.addAttribute("orgList", orgList);
		//考点：
		SysOrg org = new SysOrg();
		org.setIsExamOrg("Y");
		List<SysOrg> examOrgList = orgBiz.queryAllSysOrg(org);
		model.addAttribute("examOrgList", examOrgList);
		return "osca/provincial/doctorScoreList";
	}

	//省厅成绩发布
	@RequestMapping("/releaseScores")
	@ResponseBody
	public int releaseScores(String[] clinicalFlows){
		for(String clinicalFlow:clinicalFlows){
			int result = oscaBaseBiz.isShowOpt(clinicalFlow,"province",null);
			if(result!=1){
				return 0;
			}
		}
		return 1;
	}

	@RequestMapping("/doctorScore")
	public String doctorScore(String doctorFlow,String clinicalFlow,Model model){
		List<Map<String,Object>> doctorScores = provincialBiz.queryDocScoresByOrder(doctorFlow,clinicalFlow);
		model.addAttribute("doctorScores",doctorScores);
		double sumAll = 0;
		double sumDoc = 0;
		DecimalFormat df= new DecimalFormat("###");
		if(doctorScores!=null&&doctorScores.size()>0){
			for(Map<String,Object> doctorScore:doctorScores){
				String score0 = "0";
				String score1 = "0";
				if(doctorScore.get("stationScore")!=null){
					score0 = df.format(Double.parseDouble(doctorScore.get("stationScore").toString()));
				}
				if(doctorScore.get("examScore")!=null){
					score1 = df.format(Double.parseDouble(doctorScore.get("examScore").toString()));
				}
				if(StringUtil.isNotBlank(score0)){
					Double scoreAll = 	Double.parseDouble(score0);
					sumAll+=scoreAll;
				}
				if(StringUtil.isNotBlank(score1)){
					Double scoreDoc = Double.parseDouble(score1);
					sumDoc+=scoreDoc;
				}
			}
		}
		model.addAttribute("sumAll",df.format(sumAll));
		model.addAttribute("sumDoc",df.format(sumDoc));
		return "osca/provincial/doctorScore";
	}

	@RequestMapping("/subjectList/{roleFlag}")
	public String subjectList(OscaSubjectMain subjectMain, Model model, Integer currentPage, HttpServletRequest request,@PathVariable String roleFlag){
		model.addAttribute("roleFlag",roleFlag);
		PageHelper.startPage(currentPage,getPageSize(request));
		if("hospital".equals(roleFlag)){
			subjectMain.setIsReleased("Y");
			subjectMain.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		}
		if("province".equals(roleFlag)){
			subjectMain.setActionTypeId(AssessmentProEnum.ProvincialPlan.getId());
		}
		List<OscaSubjectMain> subjectMains = provincialBiz.searchSubjects(subjectMain);
		model.addAttribute("subjectMains",subjectMains);
		return "osca/provincial/subjectList";
	}

	@RequestMapping(value="/uploadFile")
	@ResponseBody
	public Map<String,String> uploadFile(MultipartFile checkFile,String isClinicalFile){
		Map<String, String> map = null;
		if(checkFile!=null && checkFile.getSize() > 0){
			String fileAddress=StringUtil.isNotBlank(isClinicalFile)?"clinicalStationFile":"stationFile";
			map=provincialBiz.uploadFile(checkFile,fileAddress);
		}
		return map;
	}
	//下载附件
	@RequestMapping(value = {"/downFile" }, method = RequestMethod.GET)
	public void fileDown(String fileFlow,HttpServletRequest request,HttpServletResponse response) throws Exception{
		PubFile file = this.fileBiz.readFile(fileFlow);
		downPubFile(file,request,response);
	}

	public void downPubFile(PubFile file, HttpServletRequest request,HttpServletResponse response) throws Exception {
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
					fileName = URLEncoder.encode(fileName, "UTF-8");
					if(StringUtil.isNotBlank(file.getFileSuffix())&&StringUtil.isNotBlank(fileName) && !fileName.contains(file.getFileSuffix())) {
						fileName += "." + file.getFileSuffix();
					}
					response.reset();
					response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
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
			System.err.println(request.getRequestURL());
			System.err.println(request.getServletPath());
			String httpurl=request.getRequestURL().toString();
			String servletPath=request.getServletPath();
			String hostUrl="/pdsci/jsp/file/fileNotExists.jsp";
			System.err.println(hostUrl);
            /*设置页面编码为UTF-8*/
			response.setHeader("Content-Type","text/html;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			outputStream.write((" <script type=\"text/javascript\" src=\"/pdsci/js/jquery-3.5.1.min.js\"></script>" +
					"<script>$(function(){location.href='"+hostUrl+"';});</script>文件不存在！").getBytes("UTF-8"));//将字符串转化为一个字节数组（以UTF-8编码格式，默认本地编码）
			outputStream.flush();
			outputStream.close();
		}
	}
	//打开编辑、新增页面
	@RequestMapping("/editSubject/{roleFlag}")
	public String edit(String subjectFlow,Model model,@PathVariable String roleFlag){
		model.addAttribute("roleFlag",roleFlag);
		//加载评分表单列表
		OscaFrom from = new OscaFrom();
		List<OscaFrom> froms = new ArrayList<>();
		if("hospital".equals(roleFlag)){
			from.setIsReleased("Y");
			froms = formCfgBiz.searchHospitalFrom(from);
		}
		if("province".equals(roleFlag)){
			from.setOrgFlow("jsst");
			from.setIsReleased("Y");
			froms = formCfgBiz.searchForm(from);
		}
		model.addAttribute("froms",froms);
		//加载主方案
		OscaSubjectMain subjectMain = provincialBiz.readSubject(subjectFlow);
		model.addAttribute("subjectMain",subjectMain);
		//加载考站
		List<OscaSubjectStation> subjectStations = provincialBiz.searchSubjectStationByMain(subjectFlow);
		model.addAttribute("subjectStations",subjectStations);
		if(subjectStations!=null&&subjectStations.size()>0){
			model.addAttribute("size",subjectStations.size());
		}else{
			model.addAttribute("size",0);
		}
		//考站试卷
		Map<String,List<PubFile>> fileMap = new HashMap<>();
		//加载评分表
		Map<String,List<OscaSubjectStationFrom>> fromMap = new HashMap<>();
		if(subjectStations!=null&&subjectStations.size()>0){
			for(OscaSubjectStation subjectStation:subjectStations){
				String stationFlow = subjectStation.getStationFlow();
				List<OscaSubjectStationFrom> subjectStationFroms = provincialBiz.searchFromByStaion(stationFlow,"sign");
					fromMap.put(stationFlow,subjectStationFroms);

				if("hospital".equals(roleFlag)){
					List<PubFile> files = provincialBiz.findStationFiles(stationFlow,GlobalContext.getCurrentUser().getOrgFlow());
					fileMap.put(stationFlow,files);
				}
			}
		}
		model.addAttribute("fileMap",fileMap);
		model.addAttribute("fromMap",fromMap);
		//判断是否可编辑表示符
		Map<String,List<OscaSubjectStationFrom>> fromMap2 = new HashMap<>();
		if(subjectMain!=null){
			String actionTypeId = subjectMain.getActionTypeId();
			if(AssessmentProEnum.ProvincialPlan.getId().equals(actionTypeId)&&"hospital".equals(roleFlag)){
				model.addAttribute("editFlag","editFlag");
				//加载医院自定义评分表
				if(subjectStations!=null&&subjectStations.size()>0){
					for(OscaSubjectStation subjectStation:subjectStations){
						String stationFlow = subjectStation.getStationFlow();
						List<OscaSubjectStationFrom> subjectStationFroms = provincialBiz.searchFromByStaion(stationFlow,GlobalContext.getCurrentUser().getOrgFlow());
						fromMap2.put(stationFlow,subjectStationFroms);
					}
				}
			}
		}
		model.addAttribute("fromMap2",fromMap2);
		return "osca/provincial/editSubject";
	}

	//上传试卷页面
	@RequestMapping("/uploadClinicalStationFiles")
	public String uploadClinicalStationFiles(String stationFlow,Model model, String clinicalFlow){
		//考站试卷
		List<PubFile> files = provincialBiz.findClinicalStationFiles(stationFlow,clinicalFlow,GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("files",files);
		return "osca/provincial/uploadClinicalStationFiles";
	}
	//上传试卷页面
	@RequestMapping("/saveClinicalFiles")
	@ResponseBody
	public int saveClinicalFiles(String[] fileFlows,String jsondata,String stationFlow,Model model, String clinicalFlow){
		if(StringUtil.isNotBlank(clinicalFlow)&&StringUtil.isNotBlank(stationFlow)) {
			List<String> flows = null;
			if (fileFlows != null) {
				flows = Arrays.asList(fileFlows);
				//考站试卷
				int c = provincialBiz.saveClinicalFiles(stationFlow, clinicalFlow, GlobalContext.getCurrentUser().getOrgFlow(), flows);
				return c;
			}
		}
		return 0;
	}

	//发布，删除
	@RequestMapping("/saveSubject")
	@ResponseBody
	public int saveSubject(OscaSubjectMain subjectMain,String editFlag){
		if( "release".equals(editFlag)){
			subjectMain.setIsReleased("Y");
		}
		if( "delete".equals(editFlag)){
			subjectMain.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		}
		if("unrelease".equals(editFlag)){
			subjectMain.setIsReleased("N");
		}
		return provincialBiz.edit(subjectMain);
	}

	//保存整套考核方案（方案，关联考站，关联评分表）
	@RequestMapping("/save/{roleFlag}")
	@ResponseBody
	public int save(String jsondata,@PathVariable String roleFlag){
		System.err.println(jsondata);
		Map<String,Object> resultMap = JSON.parseObject(jsondata);
		List<Map<String,Object>> stationMapList = (List<Map<String,Object>>)resultMap.get("stationDatas");
		Map<String,Object> subjectMap = (Map<String,Object>)resultMap.get("subject");
		//保存项目主表
		OscaSubjectMain subjectMain = new OscaSubjectMain();

		if(subjectMap!=null&&subjectMap.size()>0){
			String subjectName = (String)subjectMap.get("subjectName");
			String trainingSpeId = (String)subjectMap.get("trainingSpeId");
			String trainingSpeName = (String)subjectMap.get("trainingSpeName");
			String trainingTypeId = (String)subjectMap.get("trainingTypeId");
			String trainingTypeName = (String)subjectMap.get("trainingTypeName");
			String passPer0 = (String)subjectMap.get("passPer");
			String isResave = (String)subjectMap.get("isResave");
			if(StringUtil.isNotBlank(passPer0)) {
				int passPer = Integer.parseInt(passPer0);
				subjectMain.setPassPer(passPer);
			}
			String actionTypeId = null;
			String actionTypeName = null;
			if("hospital".equals(roleFlag)){
				actionTypeId = AssessmentProEnum.HospitalPlan.getId();
				actionTypeName = AssessmentProEnum.HospitalPlan.getName();
				subjectMain.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
				subjectMain.setOrgName(GlobalContext.getCurrentUser().getOrgName());
				subjectMain.setIsReleased("Y");
			}
			if("province".equals(roleFlag)){
				actionTypeId = AssessmentProEnum.ProvincialPlan.getId();
				actionTypeName = AssessmentProEnum.ProvincialPlan.getName();
				subjectMain.setOrgFlow("jsst");
				subjectMain.setOrgName("江苏省厅");
				subjectMain.setIsReleased("N");
			}
			int stationNum = stationMapList.size();

			subjectMain.setActionTypeId(actionTypeId);
			subjectMain.setActionTypeName(actionTypeName);
			subjectMain.setTrainingSpeName(trainingSpeName);
			subjectMain.setTrainingSpeId(trainingSpeId);
			subjectMain.setTrainingTypeName(trainingTypeName);
			subjectMain.setTrainingTypeId(trainingTypeId);
			subjectMain.setSubjectName(subjectName);
			subjectMain.setStationNum(stationNum);
			subjectMain.setIsResave(isResave);
			String subjectFlow = "";
			int result1 = 0;
			if(StringUtil.isNotBlank((String)subjectMap.get("subjectFlow"))){
				subjectFlow = (String)subjectMap.get("subjectFlow");
				subjectMain.setSubjectFlow(subjectFlow);
				result1 =  provincialBiz.edit(subjectMain);
			}else{
				subjectFlow = PkUtil.getUUID();
				subjectMain.setSubjectFlow(subjectFlow);
				result1 = provincialBiz.edit2(subjectMain);
			}
			if(result1 == 0){
				return 0;
			}
			//保存考站
			if(stationMapList!=null&&stationMapList.size()>0){
				for(Map<String,Object> stationMap:stationMapList){
					String partFlow = (String)stationMap.get("partFlow");
					String stationFlow = (String)stationMap.get("stationFlow");
					String examinedContent = (String)stationMap.get("examinedContent");
					String stationName = (String)stationMap.get("stationName");
					String stationScore = (String)stationMap.get("stationScore");
					int score = Integer.parseInt(stationScore);
					int ordinal = (int)stationMap.get("ordinal");
					List<Map<String,Object>> fromFlows = (List<Map<String,Object>>)stationMap.get("fromFlow");
					List<String> fileFlows = (List<String>)stationMap.get("fileFlows");
					OscaSubjectStation subjectStation = new OscaSubjectStation();
					subjectStation.setPartFlow(partFlow);
					subjectStation.setPartName(DictTypeEnum.ExamPart.getDictNameById(partFlow));
					subjectStation.setStationFlow(stationFlow);
					subjectStation.setExaminedContent(examinedContent);
					subjectStation.setStationName(stationName);
					subjectStation.setStationScore(score);
					subjectStation.setOrdinal(ordinal);
					subjectStation.setSubjectFlow(subjectFlow);
					subjectStation.setSubjectName(subjectName);
					int result2 = provincialBiz.editStation(subjectStation,fromFlows,fileFlows);
					if(result2 == 0){
						return 0;
					}
				}
				return 1;
			}
			return 0;
		}
		return 0;
	}

	//保存省级方案医院自定义表单
	@RequestMapping("/saveHospitalForms")
	@ResponseBody
	public int saveHospitalForms(String jsondata){
//		System.err.println(jsondata);
		Map<String,Object> resultMap = JSON.parseObject(jsondata);
		List<Map<String,Object>> mapList = (List<Map<String,Object>>)resultMap.get("formDatas");
		if(mapList!=null&&mapList.size()>0){
			for(Map<String,Object> map:mapList){
				String stationFlow = (String)map.get("stationFlow");
				String stationName = (String)map.get("stationName");
				List<Map<String,Object>> fromFlowMaps = (List<Map<String,Object>>)map.get("fromFlow");
				List<String> fileFlows = (List<String>)map.get("fileFlows");
				int result = provincialBiz.saveHospitalForms(stationFlow,stationName,fromFlowMaps,fileFlows);
				if(result==0){
					return 0;
				}
			}
			return 1;
		}
		return 0;
	}

	//删除考站
	@RequestMapping("/deleteStation")
	@ResponseBody
	public int deleteStation(String stationFlow){
		if(StringUtil.isNotBlank(stationFlow)){
			OscaSubjectStation subjectStation = new OscaSubjectStation();
			subjectStation.setStationFlow(stationFlow);
			subjectStation.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			return provincialBiz.editStationOnly(subjectStation);
		}
		return 0;
	}

	/**
	 * 学员各站成绩导出操作
	 */
	@RequestMapping("/exportExcel")
	public void exportScoreExcel(String speId,String clinicalYear,String orgFlow,String trainingOrgFlow,
								 String trainCategoryId,String resultId,String idNo,HttpServletResponse response,
								 String gradeDoctorName,String orgCityId) throws Exception {
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("speId",speId);
		paramMap.put("clinicalYear",clinicalYear);
		paramMap.put("orgFlow",orgFlow);
		paramMap.put("trainingOrgFlow",trainingOrgFlow);
		paramMap.put("trainCategoryId",trainCategoryId);
		paramMap.put("resultId",resultId);
		paramMap.put("idNo",idNo);
		paramMap.put("gradeDoctorName",gradeDoctorName);
		paramMap.put("orgCityId",orgCityId);
		List<Map<String,Object>> resultMapList = provincialBiz.searchDoctorScoreList(paramMap);
		List<String> titleLst = new ArrayList<>();
		titleLst.add("姓名");
		titleLst.add("考核年份");
		titleLst.add("考核名称");
		titleLst.add("培训年限");
		titleLst.add("证件号码");
		titleLst.add("培训届别");
		titleLst.add("培训基地");
		titleLst.add("所在考点");
		titleLst.add("考核专业");
		titleLst.add("第一站");
		titleLst.add("第二站");
		titleLst.add("第三站");
		titleLst.add("第四站");
		titleLst.add("第五站");
		titleLst.add("第六站");
		titleLst.add("第七站");
		titleLst.add("第八站");
		titleLst.add("第九站");
		titleLst.add("总分");
		titleLst.add("是否通过");
		Map<String,String> yearMap = new HashMap<>();
		yearMap.put("OneYear","一年");
		yearMap.put("TwoYear","两年");
		yearMap.put("ThreeYear","三年");

		String fileName = "学员成绩信息.xls";
		String[] titles = titleLst.toArray(new String[titleLst.size()]);
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		// 为工作簿添加sheet
		HSSFSheet sheet = wb.createSheet("学员成绩信息");
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		//列宽自适应
		HSSFRow row = sheet.createRow(0);
		HSSFCell cellTitle = null;
		for (int i = 0; i < titles.length; i++) {
			cellTitle = row.createCell(i);
			cellTitle.setCellValue(titles[i]);
			cellTitle.setCellStyle(styleCenter);
		}
		int rownum = 1;
		if (resultMapList != null && !resultMapList.isEmpty()) {
			for (Map<String, Object> map : resultMapList) {
				HSSFRow hr = sheet.createRow(rownum);
				HSSFCell cell = hr.createCell(0);
				cell.setCellValue((String) map.get("doctorName"));
				HSSFCell cell1 = hr.createCell(1);
				cell1.setCellValue((String) map.get("clinicalYear"));
				HSSFCell cell2 = hr.createCell(2);
				cell2.setCellValue((String) map.get("clinicalName"));
				HSSFCell cell3 = hr.createCell(3);
				cell3.setCellValue(yearMap.get((String) map.get("trainingYears")));
				HSSFCell cell4 = hr.createCell(4);
				cell4.setCellValue((String) map.get("idNo"));
				HSSFCell cell5 = hr.createCell(5);
				cell5.setCellValue((String) map.get("sessionNumber"));
				HSSFCell cell6 = hr.createCell(6);
				cell6.setCellValue((String) map.get("rdorgName"));
				HSSFCell cell7 = hr.createCell(7);
				cell7.setCellValue((String) map.get("osorgName"));
				HSSFCell cell8 = hr.createCell(8);
				cell8.setCellValue((String) map.get("speName"));

				String doctorFlow = (String)map.get("doctorFlow");
				String clinicalFlow = (String)map.get("clinicalFlow");
				List<Map<String,Object>> doctorScores = provincialBiz.queryDocScoresByOrder(doctorFlow,clinicalFlow);
				double sumDoc = 0;
				DecimalFormat df= new DecimalFormat("######0.00");
				int i = 9;
				if(doctorScores!=null&&doctorScores.size()>0) {
					for (Map<String, Object> doctorScore : doctorScores) {
						HSSFCell newCell ;
						newCell = hr.createCell(i);
						newCell.setCellValue((String) doctorScore.get("examScore"));
						String score1 = (String) doctorScore.get("examScore");
						if (StringUtil.isNotBlank(score1)) {
							Double scoreDoc = Double.parseDouble(score1);
							sumDoc += scoreDoc;
						}
						i++;
					}
				}
				HSSFCell cell18 = hr.createCell(18);
				cell18.setCellValue(df.format(sumDoc));
				HSSFCell cell19 = hr.createCell(19);
				cell19.setCellValue((String) map.get("isPassName"));
				rownum++;
			}
		}
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	//打开编辑、新增页面
	@RequestMapping("/showPassedInfo")
	public String showPassedInfo(String subjectFlow,Model model, String roleFlag, String isEdit){
		model.addAttribute("roleFlag",roleFlag);
		//加载主方案
		OscaSubjectMain subjectMain = provincialBiz.readSubject(subjectFlow);
		model.addAttribute("subjectMain",subjectMain);

		List<Map<String,String>> parts=provincialBiz.getSubjectParts(subjectFlow);
		Map<String,OscaSubjectPartScore> partScoreMap=new HashMap<>();
		if(parts!=null&&parts.size()>0)
		{
			for(Map<String,String> part:parts)
			{
				if(part!=null){
					OscaSubjectPartScore partScore=provincialBiz.getSubjectPartScore(subjectFlow,part.get("partFlow"));
					partScoreMap.put(part.get("partFlow"),partScore);
				}
			}
		}
		model.addAttribute("parts",parts);
		model.addAttribute("partScoreMap",partScoreMap);
		//加载考站
		Map<String,OscaSubjectStationScore> stationScoreMap=new HashMap<>();
		Map<String,Integer> baseScoreMap=new HashMap<>();
		Map<String,List<OscaSubjectStation>> partStationsMap=new HashMap<>();
		List<OscaSubjectStation> subjectStations = provincialBiz.searchSubjectStationByMain(subjectFlow);
		int allScore=0;
		if(subjectStations!=null&&subjectStations.size()>0)
		{
			for(OscaSubjectStation s:subjectStations)
			{
				OscaSubjectStationScore stationScore=provincialBiz.getSubjectStationScore(subjectFlow,s.getStationFlow());
				stationScoreMap.put(s.getStationFlow(),stationScore);
				List<OscaSubjectStation> stations=partStationsMap.get(s.getPartFlow());
				if(stations==null)
					stations=new ArrayList<>();
				stations.add(s);
				partStationsMap.put(s.getPartFlow(),stations);

				Integer baseScore=baseScoreMap.get(s.getPartFlow());
				if(baseScore==null) baseScore=0;
				baseScore+=s.getStationScore();
				baseScoreMap.put(s.getPartFlow(),baseScore);

				allScore+=s.getStationScore();
			}
		}
		model.addAttribute("stations",subjectStations);
		model.addAttribute("stationScoreMap",stationScoreMap);
		model.addAttribute("partStationsMap",partStationsMap);
		model.addAttribute("allScore",allScore);
		model.addAttribute("baseScoreMap",baseScoreMap);
		if(GlobalConstant.FLAG_Y.equals(isEdit))
		{
			return "osca/provincial/editPassedInfo";
		}else{
			return "osca/provincial/showPassedInfo";
		}
	}
	@RequestMapping("/savePassedInfo")
	@ResponseBody
	public String savePassedInfo(String subjectFlow,String allScore,String stationScores,String partScores){
		OscaSubjectMain m=provincialBiz.readSubject(subjectFlow);
		if(m==null)
		{
			return "考核方案不存在，请刷新列表页面！";
		}
		JSONArray stationScoreList = JSON.parseArray(stationScores);
		JSONArray partScoreList = JSON.parseArray(partScores);

		int result=provincialBiz.savePassedInfo(m,allScore,stationScoreList,partScoreList);
	    if(result==0)
			return "0";
		else
			return "1";
	}

	@RequestMapping("/readFromList")
	@ResponseBody
	public String readFromList(String subjectFlow){
		List<OscaSubjectStationFrom> list = formCfgBiz.selectSubjectStationFromByFlow(subjectFlow);
		if (null != list && !list.isEmpty()){
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping("/showFrom")
	public String showFrom(String subjectFlow,Model model){
		List<OscaSubjectStationFrom> list = formCfgBiz.selectSubjectStationFromByFlow(subjectFlow);
		model.addAttribute("list",list);
		model.addAttribute("subjectFlow",subjectFlow);
		return "osca/provincial/searchFromMain";
	}

	@RequestMapping("/fromInfo")
	public String fromInfo(String fromFlow,Model model) throws DocumentException {
		OscaFrom oscaFrom = formCfgBiz.readFrom(fromFlow);
		if (null !=oscaFrom){
			if (StringUtil.isNotBlank(oscaFrom.getFromTypeId())
					&& oscaFrom.getFromTypeId().equals("1")
					&& StringUtil.isNotBlank(oscaFrom.getFromUrl())){
				String path=oscaFrom.getFromUrl();
				int end = path.indexOf(".jsp");
				path=path.substring(3,end);
				return path;
			}
		}
		OscaFrom from = formCfgBiz.readFrom(fromFlow);
		model.addAttribute("from",from);
		if(from.getRromContent()!=null){
			Document dom = DocumentHelper.parseText(from.getRromContent());
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if(titleElementList != null && !titleElementList.isEmpty()){
				List<OscaFromCfgTitleExt> titleFormList = new ArrayList<OscaFromCfgTitleExt>();
				int titleSum = 0;
				for(Element te :titleElementList) {
					OscaFromCfgTitleExt titleForm = new OscaFromCfgTitleExt();
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					titleForm.setTypeName(te.attributeValue("typeName"));
					titleForm.setOrderNum(te.attributeValue("orderNum"));
					List<Element> itemElementList = te.elements("item");
					List<OscaFromCfgItemExt> itemFormList = null;
					if (itemElementList != null && !itemElementList.isEmpty()) {
						itemFormList = new ArrayList<OscaFromCfgItemExt>();
						for (Element ie : itemElementList) {
							OscaFromCfgItemExt itemForm = new OscaFromCfgItemExt();
							itemForm.setId(ie.attributeValue("id"));
							itemForm.setName(ie.element("name") == null ? "" : StringUtil.toHtml(ie.element("name").getTextTrim()));
							itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
							itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
							itemFormList.add(itemForm);
						}
					}
					titleForm.setItemList(itemFormList);
					titleFormList.add(titleForm);
					titleSum++;
				}
				model.addAttribute("titleSum", titleSum);
				model.addAttribute("titleFormList", titleFormList);
			}
		}
		return "/osca/provincial/readFrom";
	}

	@RequestMapping("/exportFrom")
	public void exportFrom(String fromFlow, HttpServletResponse response) throws Exception{
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //靠右垂直居中
		styleRight.setAlignment(HorizontalAlignment.RIGHT);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle titleStyle = wb.createCellStyle();//水平垂直居中
		titleStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFFont font = wb.createFont();	//设置字体
		font.setBold(true);//粗体显示
		HSSFCellStyle fontCenterStyle = wb.createCellStyle();	//粗图居中显示
		fontCenterStyle.setFont(font);
		fontCenterStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		fontCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle fontLeftStyle = wb.createCellStyle();	//粗图居中显示
		fontLeftStyle.setFont(font);
		fontLeftStyle.setAlignment(HorizontalAlignment.LEFT);
		fontLeftStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		//查看表单信息
		OscaFrom oscaFrom = formCfgBiz.readFrom(fromFlow);
		String fileName = oscaFrom.getFromName()+".xls";	//文件名称
		HSSFSheet sheet = wb.createSheet(oscaFrom.getFromName());//sheet名称
		//设置每一列的宽度 行和列都是从0开始
		sheet.setColumnWidth(0,4000);
		sheet.setColumnWidth(1,4000);
		sheet.setColumnWidth(2,20000);
		sheet.setColumnWidth(3,4000);
		sheet.setColumnWidth(4,4000);

		//前四行固定样式
		HSSFRow row0 = sheet.createRow(0);	//第一行
		HSSFCell cell = row0.createCell(0);
		cell.setCellValue(oscaFrom.getFromName());
		cell.setCellStyle(fontCenterStyle);
		CellRangeAddress address = new CellRangeAddress(0, 0, 0, 4);//合并第一行
		sheet.addMergedRegion(address);

		HSSFRow row1 = sheet.createRow(1);//第二行
		String[] titles1 = new String[]{
				"评分人：",
				"",
				"",
				"学员姓名：",
				""
		};
		HSSFCell cellTitle2 = null;
		for (int i = 0; i < titles1.length; i++) {
			cellTitle2 = row1.createCell(i);
			cellTitle2.setCellValue(titles1[i]);
			cellTitle2.setCellStyle(fontLeftStyle);
			cellTitle2.setCellType(CellType.STRING);
		}
		CellRangeAddress address1 = new CellRangeAddress(1, 1, 0, 2);
		sheet.addMergedRegion(address1);
		CellRangeAddress address2 = new CellRangeAddress(1, 1, 3, 4);
		sheet.addMergedRegion(address2);

		HSSFRow row2 = sheet.createRow(2);//第三行
		String[] titles2 = new String[]{
				"评分项目：",
				"",
				"评分要素",
				"分值",
				"得分"
		};
		HSSFCell cellTitle3 = null;
		for (int i = 0; i < titles1.length; i++) {
			cellTitle3 = row2.createCell(i);
			cellTitle3.setCellValue(titles2[i]);
			cellTitle3.setCellStyle(fontCenterStyle);
			cellTitle3.setCellType(CellType.STRING);
		}
		CellRangeAddress address3 = new CellRangeAddress(2, 2, 0, 1);
		sheet.addMergedRegion(address3);

		HSSFRow row4 = sheet.createRow(3);//第四行
		String[] titles3 = new String[]{
				"一级项目",
				"二级子项目",
				"",
				"",
				""
		};
		HSSFCell cellTitle4 = null;
		for (int i = 0; i < titles1.length; i++) {
			cellTitle4 = row4.createCell(i);
			cellTitle4.setCellValue(titles3[i]);
			cellTitle4.setCellStyle(fontCenterStyle);
			cellTitle4.setCellType(CellType.STRING);
		}
		CellRangeAddress address4 = new CellRangeAddress(2, 3, 2, 2);
		sheet.addMergedRegion(address4);
		CellRangeAddress address5 = new CellRangeAddress(2, 3, 3, 3);
		sheet.addMergedRegion(address5);
		CellRangeAddress address6 = new CellRangeAddress(2, 3, 4, 4);
		sheet.addMergedRegion(address6);

		//要导出的数据
		List<OscaFromCfgTitleExt> oscaFromCfgTitleExts = exportFromData(fromFlow);
		int num=0; //记录行数，处理一二级项目（excel表）数据
		BigDecimal decimal = new BigDecimal(0);		//总分
		HashMap<String, Integer> map = new HashMap<>();		//Key:一级项目名称   value:一级项目的评分要素数量
		HashMap<String, Integer> rowMap = new HashMap<>();	//Key:一级项目名称   value:该一级项目在excel导出的起始行
		HashSet<String> set = new HashSet<>();
		for (OscaFromCfgTitleExt ext : oscaFromCfgTitleExts) {	//计算每一个一级项目的评分要素数量
			if (null != map.get(ext.getName())){
				Integer old = map.get(ext.getName());
				old=old+ext.getItemList().size();
				map.put(ext.getName(),old);
			}else {
				map.put(ext.getName(),ext.getItemList().size());
			}
		}
		//开始导出数据
		for (OscaFromCfgTitleExt ext : oscaFromCfgTitleExts) {
			for (int i = 0; i < ext.getItemList().size(); i++) {	//每一行数据
				decimal=decimal.add(new BigDecimal(ext.getItemList().get(i).getScore()));
				HSSFRow row = sheet.createRow(4+num);
				HSSFCell cellTitle = null;
				if (i==0){	//该一级项目第一行
					String[] titles = new String[]{ext.getName(), StringUtil.isEmpty(ext.getTypeName())?"无":ext.getTypeName(),
							ext.getItemList().get(i).getName(), ext.getItemList().get(i).getScore(), ""};
					for (int u = 0; u < titles1.length; u++) {
						cellTitle = row.createCell(u);
						cellTitle.setCellStyle(styleLeft);
						if (u==3){
							cellTitle.setCellValue(Integer.parseInt(titles[u]));
							cellTitle.setCellType(CellType.NUMERIC);
							cellTitle.setCellStyle(titleStyle);
						}else {
							cellTitle.setCellValue(titles[u]);
							cellTitle.setCellType(CellType.STRING);
						}

					}
					if (rowMap.get(ext.getName())==null){
						rowMap.put(ext.getName(),num);//记录该一级项目的起始行
					}
					if (!set.contains(ext.getName())){	//如果有相同的一级项目，合并单元格
						CellRangeAddress address8 = new CellRangeAddress(4+rowMap.get(ext.getName()), map.get(ext.getName())+3+num, 0, 0);//起始行，终止行，起始列，终止列
						sheet.addMergedRegion(address8);
						set.add(ext.getName());
					}
				}else {
					String[] titles = new String[]{"", StringUtil.isEmpty(ext.getTypeName())?"无":ext.getTypeName(), ext.getItemList().get(i).getName(), ext.getItemList().get(i).getScore(), ""};
					for (int u = 0; u < titles1.length; u++) {
						cellTitle = row.createCell(u);
						cellTitle.setCellStyle(styleLeft);
						if (u==3){
							cellTitle.setCellValue(Integer.parseInt(titles[u]));
							cellTitle.setCellType(CellType.NUMERIC);
							cellTitle.setCellStyle(titleStyle);
						}else {
							cellTitle.setCellValue(titles[u]);
							cellTitle.setCellType(CellType.STRING);
						}
					}
				}
				num++;
			}
			//合并二级子项目的单元格
			CellRangeAddress address7 = new CellRangeAddress(num+4-ext.getItemList().size(), num+3, 1, 1);//起始行，终止行，起始列，终止列
			sheet.addMergedRegion(address7);
		}

		//固定的样式
		HSSFRow row5 = sheet.createRow(4+num);
		String[] titles5 = new String[]{
				"合计",
				"",
				"",
				"",
				""
		};
		HSSFCell cellTitle5 = null;
		for (int i = 0; i < titles1.length; i++) {
			cellTitle5 = row5.createCell(i);
			cellTitle5.setCellValue(titles5[i]);
			cellTitle5.setCellStyle(styleRight);
			cellTitle5.setCellType(CellType.STRING);
			if (i==3){
				cellTitle5.setCellValue(decimal.doubleValue());
				cellTitle5.setCellStyle(titleStyle);
				cellTitle5.setCellType(CellType.NUMERIC);
			}
		}
		CellRangeAddress address9 = new CellRangeAddress(4+num, 4+num, 0, 2);//起始行，终止行，起始列，终止列
		sheet.addMergedRegion(address9);

		HSSFRow row6 = sheet.createRow(5+num);
		String[] titles6 = new String[]{
				"评审人签字：",
				"",
				"",
				"",
				""
		};
		HSSFCell cellTitle6 = null;
		for (int i = 0; i < titles1.length; i++) {
			cellTitle6 = row6.createCell(i);
			cellTitle6.setCellValue(titles6[i]);
			cellTitle6.setCellStyle(styleLeft);
			cellTitle6.setCellType(CellType.STRING);
		}
		CellRangeAddress address61 = new CellRangeAddress(5+num, 5+num, 1, 4);//起始行，终止行，起始列，终止列
		sheet.addMergedRegion(address61);

		HSSFRow row7 = sheet.createRow(6+num);
		String[] titles7 = new String[]{
				"评审时间：",
				"",
				"",
				"",
				""
		};
		HSSFCell cellTitle7 = null;
		for (int i = 0; i < titles1.length; i++) {
			cellTitle7 = row7.createCell(i);
			cellTitle7.setCellValue(titles7[i]);
			cellTitle7.setCellStyle(styleLeft);
			cellTitle7.setCellType(CellType.STRING);
		}
		CellRangeAddress address71 = new CellRangeAddress(6+num, 6+num, 1, 4);//起始行，终止行，起始列，终止列
		sheet.addMergedRegion(address71);

		//如果是固定表单，最下面有备注
		if (fromFlow.equals("00000001copy") || fromFlow.equals("00000002copy")
				|| fromFlow.equals("00000003copy") || fromFlow.equals("00000004copy")
				|| fromFlow.equals("00000005copy") || fromFlow.equals("00000006copy")
				|| fromFlow.equals("00000007copy") ){
			HSSFRow rowlast = sheet.createRow(7+num);
			HSSFCell celllast = rowlast.createCell(0);
			switch (fromFlow){
				case "00000001copy":
				case "00000002copy":
				case "00000004copy":
				case "00000005copy":
				case "00000007copy":
					celllast.setCellValue("（注：折合成20分后计入第二部分总分）");
				case "00000003copy":
					celllast.setCellValue("备注：请注明诊疗技术操作名称。");
					break;
				case "00000006copy":
					celllast.setCellValue("备注：请注明手术操作名称。");
			}

			celllast.setCellStyle(styleLeft);
			CellRangeAddress addresslast = new CellRangeAddress(7+num, 7+num, 0, 4);//合并第一行
			sheet.addMergedRegion(addresslast);
		}

		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}

	@RequestMapping("/exportProgrammeFrom")
	public void exportProgrammeFrom(String subjectFlow, HttpServletResponse response) throws Exception{
		//创建工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		//定义将用到的样式
		HSSFCellStyle styleCenter = wb.createCellStyle(); //居中
		styleCenter.setAlignment(HorizontalAlignment.CENTER);
		HSSFCellStyle styleLeft = wb.createCellStyle();  //靠左垂直居中
		styleLeft.setAlignment(HorizontalAlignment.LEFT);
		styleLeft.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle styleRight = wb.createCellStyle(); //靠右垂直居中
		styleRight.setAlignment(HorizontalAlignment.RIGHT);
		styleRight.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle titleStyle = wb.createCellStyle();//水平垂直居中
		titleStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFFont font = wb.createFont();	//设置字体
		font.setBold(true);//粗体显示
		HSSFCellStyle fontCenterStyle = wb.createCellStyle();	//粗图居中显示
		fontCenterStyle.setFont(font);
		fontCenterStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
		fontCenterStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		HSSFCellStyle fontLeftStyle = wb.createCellStyle();	//粗图居中显示
		fontLeftStyle.setFont(font);
		fontLeftStyle.setAlignment(HorizontalAlignment.LEFT);
		fontLeftStyle.setVerticalAlignment(VerticalAlignment.CENTER);

		OscaSubjectMain subject = provincialBiz.readSubject(subjectFlow);
		String fileName = subject.getSubjectName()+"评分表单.xls";	//文件名称

		HashMap<String, Integer> nameMap = new HashMap<String, Integer>();//防止sheet名称重复，重复则 +1
		//要导出的数据
		List<OscaSubjectStationFrom> list = formCfgBiz.selectSubjectStationFromByFlow(subjectFlow);
		if (null != list && !list.isEmpty()){
			for (OscaSubjectStationFrom subjectStationFrom : list) {
				String sheetName=subjectStationFrom.getStationName();
				if (null != nameMap.get(sheetName)){	//sheet名称是否重复
					Integer repeatNum=nameMap.get(sheetName);	//获取重复次数
					repeatNum++;
					nameMap.put(sheetName,repeatNum);
					sheetName=sheetName+repeatNum;

				}else {
					nameMap.put(sheetName,0);	//未出现重复，重复次数默认0
				}
				HSSFSheet sheet = wb.createSheet(sheetName);//sheet名称
				//设置每一列的宽度 行和列都是从0开始
				sheet.setColumnWidth(0,4000);
				sheet.setColumnWidth(1,4000);
				sheet.setColumnWidth(2,20000);
				sheet.setColumnWidth(3,4000);
				sheet.setColumnWidth(4,4000);

				//前四行固定样式
				HSSFRow row0 = sheet.createRow(0);	//第一行
				HSSFCell cell = row0.createCell(0);
				cell.setCellValue(subjectStationFrom.getFromName()+"("+subjectStationFrom.getStationName()+")");
				cell.setCellStyle(fontCenterStyle);
				CellRangeAddress address = new CellRangeAddress(0, 0, 0, 4);//合并第一行
				sheet.addMergedRegion(address);

				HSSFRow row1 = sheet.createRow(1);//第二行
				String[] titles1 = new String[]{
						"评分人：",
						"",
						"",
						"学员姓名：",
						""
				};
				HSSFCell cellTitle2 = null;
				for (int i = 0; i < titles1.length; i++) {
					cellTitle2 = row1.createCell(i);
					cellTitle2.setCellValue(titles1[i]);
					cellTitle2.setCellStyle(fontLeftStyle);
					cellTitle2.setCellType(CellType.STRING);
				}
				CellRangeAddress address1 = new CellRangeAddress(1, 1, 0, 2);
				sheet.addMergedRegion(address1);
				CellRangeAddress address2 = new CellRangeAddress(1, 1, 3, 4);
				sheet.addMergedRegion(address2);

				HSSFRow row2 = sheet.createRow(2);//第三行
				String[] titles2 = new String[]{
						"评分项目：",
						"",
						"评分要素",
						"分值",
						"得分"
				};
				HSSFCell cellTitle3 = null;
				for (int i = 0; i < titles1.length; i++) {
					cellTitle3 = row2.createCell(i);
					cellTitle3.setCellValue(titles2[i]);
					cellTitle3.setCellStyle(fontCenterStyle);
					cellTitle3.setCellType(CellType.STRING);
				}
				CellRangeAddress address3 = new CellRangeAddress(2, 2, 0, 1);
				sheet.addMergedRegion(address3);

				HSSFRow row4 = sheet.createRow(3);//第四行
				String[] titles3 = new String[]{
						"一级项目",
						"二级子项目",
						"",
						"",
						""
				};
				HSSFCell cellTitle4 = null;
				for (int i = 0; i < titles1.length; i++) {
					cellTitle4 = row4.createCell(i);
					cellTitle4.setCellValue(titles3[i]);
					cellTitle4.setCellStyle(fontCenterStyle);
					cellTitle4.setCellType(CellType.STRING);
				}
				CellRangeAddress address4 = new CellRangeAddress(2, 3, 2, 2);
				sheet.addMergedRegion(address4);
				CellRangeAddress address5 = new CellRangeAddress(2, 3, 3, 3);
				sheet.addMergedRegion(address5);
				CellRangeAddress address6 = new CellRangeAddress(2, 3, 4, 4);
				sheet.addMergedRegion(address6);

				//每一个sheet要导出的数据
				List<OscaFromCfgTitleExt> oscaFromCfgTitleExts = exportFromData(subjectStationFrom.getFromFlow());
				int num=0; //记录行数，处理一二级项目（excel表）数据
				BigDecimal decimal = new BigDecimal(0);		//总分
				HashMap<String, Integer> map = new HashMap<>();		//Key:一级项目名称   value:一级项目的评分要素数量
				HashMap<String, Integer> rowMap = new HashMap<>();	//Key:一级项目名称   value:该一级项目在excel导出的起始行
				HashSet<String> set = new HashSet<>();
				for (OscaFromCfgTitleExt ext : oscaFromCfgTitleExts) {	//计算每一个一级项目的评分要素数量
					if (null != map.get(ext.getName())){
						Integer old = map.get(ext.getName());
						old=old+ext.getItemList().size();
						map.put(ext.getName(),old);
					}else {
						map.put(ext.getName(),ext.getItemList().size());
					}
				}
				//开始导出数据
				for (OscaFromCfgTitleExt ext : oscaFromCfgTitleExts) {
					for (int i = 0; i < ext.getItemList().size(); i++) {	//每一行数据
						decimal=decimal.add(new BigDecimal(ext.getItemList().get(i).getScore()));
						HSSFRow row = sheet.createRow(4+num);
						HSSFCell cellTitle = null;
						if (i==0){	//该一级项目第一行
							String[] titles = new String[]{ext.getName(), StringUtil.isEmpty(ext.getTypeName())?"无":ext.getTypeName(),
									ext.getItemList().get(i).getName(), ext.getItemList().get(i).getScore(), ""};
							for (int u = 0; u < titles1.length; u++) {
								cellTitle = row.createCell(u);
								cellTitle.setCellStyle(styleLeft);
								if (u==3){
									cellTitle.setCellValue(Integer.parseInt(titles[u]));
									cellTitle.setCellType(CellType.NUMERIC);
									cellTitle.setCellStyle(titleStyle);
								}else {
									cellTitle.setCellValue(titles[u]);
									cellTitle.setCellType(CellType.STRING);
								}

							}
							if (rowMap.get(ext.getName())==null){
								rowMap.put(ext.getName(),num);//记录该一级项目的起始行
							}
							if (!set.contains(ext.getName())){	//如果有相同的一级项目，合并单元格
								CellRangeAddress address8 = new CellRangeAddress(4+rowMap.get(ext.getName()), map.get(ext.getName())+3+num, 0, 0);//起始行，终止行，起始列，终止列
								sheet.addMergedRegion(address8);
								set.add(ext.getName());
							}
						}else {
							String[] titles = new String[]{"", StringUtil.isEmpty(ext.getTypeName())?"无":ext.getTypeName(), ext.getItemList().get(i).getName(), ext.getItemList().get(i).getScore(), ""};
							for (int u = 0; u < titles1.length; u++) {
								cellTitle = row.createCell(u);
								cellTitle.setCellStyle(styleLeft);
								if (u==3){
									cellTitle.setCellValue(Integer.parseInt(titles[u]));
									cellTitle.setCellType(CellType.NUMERIC);
									cellTitle.setCellStyle(titleStyle);
								}else {
									cellTitle.setCellValue(titles[u]);
									cellTitle.setCellType(CellType.STRING);
								}
							}
						}
						num++;
					}
					//合并二级子项目的单元格
					CellRangeAddress address7 = new CellRangeAddress(num+4-ext.getItemList().size(), num+3, 1, 1);//起始行，终止行，起始列，终止列
					sheet.addMergedRegion(address7);
				}

				//固定的样式
				HSSFRow row5 = sheet.createRow(4+num);
				String[] titles5 = new String[]{
						"合计",
						"",
						"",
						"",
						""
				};
				HSSFCell cellTitle5 = null;
				for (int i = 0; i < titles1.length; i++) {
					cellTitle5 = row5.createCell(i);
					cellTitle5.setCellValue(titles5[i]);
					cellTitle5.setCellStyle(styleRight);
					cellTitle5.setCellType(CellType.STRING);
					if (i==3){
						cellTitle5.setCellValue(decimal.doubleValue());
						cellTitle5.setCellStyle(titleStyle);
						cellTitle5.setCellType(CellType.NUMERIC);
					}
				}
				CellRangeAddress address9 = new CellRangeAddress(4+num, 4+num, 0, 2);//起始行，终止行，起始列，终止列
				sheet.addMergedRegion(address9);

				HSSFRow row6 = sheet.createRow(5+num);
				String[] titles6 = new String[]{
						"评审人签字：",
						"",
						"",
						"",
						""
				};
				HSSFCell cellTitle6 = null;
				for (int i = 0; i < titles1.length; i++) {
					cellTitle6 = row6.createCell(i);
					cellTitle6.setCellValue(titles6[i]);
					cellTitle6.setCellStyle(styleLeft);
					cellTitle6.setCellType(CellType.STRING);
				}
				CellRangeAddress address61 = new CellRangeAddress(5+num, 5+num, 1, 4);//起始行，终止行，起始列，终止列
				sheet.addMergedRegion(address61);

				HSSFRow row7 = sheet.createRow(6+num);
				String[] titles7 = new String[]{
						"评审时间：",
						"",
						"",
						"",
						""
				};
				HSSFCell cellTitle7 = null;
				for (int i = 0; i < titles1.length; i++) {
					cellTitle7 = row7.createCell(i);
					cellTitle7.setCellValue(titles7[i]);
					cellTitle7.setCellStyle(styleLeft);
					cellTitle7.setCellType(CellType.STRING);
				}
				CellRangeAddress address71 = new CellRangeAddress(6+num, 6+num, 1, 4);//起始行，终止行，起始列，终止列
				sheet.addMergedRegion(address71);

				//如果是固定表单，最下面有备注
				if (subjectStationFrom.getFromFlow().equals("00000001") || subjectStationFrom.getFromFlow().equals("00000002")
						|| subjectStationFrom.getFromFlow().equals("00000003") || subjectStationFrom.getFromFlow().equals("00000004")
						|| subjectStationFrom.getFromFlow().equals("00000005") || subjectStationFrom.getFromFlow().equals("00000006")
						|| subjectStationFrom.getFromFlow().equals("00000007") ){
					HSSFRow rowlast = sheet.createRow(7+num);
					HSSFCell celllast = rowlast.createCell(0);
					switch (subjectStationFrom.getFromFlow()){
						case "00000001":
						case "00000002":
						case "00000004":
						case "00000005":
						case "00000007":
							celllast.setCellValue("（注：折合成20分后计入第二部分总分）");
						case "00000003":
							celllast.setCellValue("备注：请注明诊疗技术操作名称。");
							break;
						case "00000006":
							celllast.setCellValue("备注：请注明手术操作名称。");
					}

					celllast.setCellStyle(styleLeft);
					CellRangeAddress addresslast = new CellRangeAddress(7+num, 7+num, 0, 4);//合并第一行
					sheet.addMergedRegion(addresslast);
				}
			}
		}

		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.setCookie(response);
		wb.write(response.getOutputStream());
	}

	//处理表单数据
	private List<OscaFromCfgTitleExt> exportFromData(String fromFlow) throws DocumentException {
		List<OscaFromCfgTitleExt> titleFormList = new ArrayList<OscaFromCfgTitleExt>();
		OscaFrom from = formCfgBiz.readFrom(fromFlow);
		//如果表单是固定表单，就使用对应的自定义表单的数据
		if (from.getFromTypeId().equals("1")){
			from = formCfgBiz.readFrom(fromFlow+"copy");
		}
		if(from.getRromContent()!=null){
			Document dom = DocumentHelper.parseText(from.getRromContent());
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if(titleElementList != null && !titleElementList.isEmpty()){
				int titleSum = 0;
				for(Element te :titleElementList) {
					OscaFromCfgTitleExt titleForm = new OscaFromCfgTitleExt();
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					titleForm.setTypeName(te.attributeValue("typeName"));
					titleForm.setOrderNum(te.attributeValue("orderNum"));
					List<Element> itemElementList = te.elements("item");
					List<OscaFromCfgItemExt> itemFormList = null;
					if (itemElementList != null && !itemElementList.isEmpty()) {
						itemFormList = new ArrayList<OscaFromCfgItemExt>();
						for (Element ie : itemElementList) {
							OscaFromCfgItemExt itemForm = new OscaFromCfgItemExt();
							itemForm.setId(ie.attributeValue("id"));
							itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
							itemForm.setScore(ie.element("score") == null ? "" : ie.element("score").getTextTrim());
							itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
							itemFormList.add(itemForm);
						}
					}
					titleForm.setItemList(itemFormList);
					titleFormList.add(titleForm);
					titleSum++;
				}
			}
		}
		return titleFormList;
	}
}

