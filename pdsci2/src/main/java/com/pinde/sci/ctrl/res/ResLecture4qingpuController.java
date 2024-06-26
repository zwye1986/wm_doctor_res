package com.pinde.sci.ctrl.res;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.res.QingpuLectureTypeEnum;
import com.pinde.sci.enums.sch.SchUnitEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.form.res.QingpuLectureCfgItemExt;
import com.pinde.sci.form.res.QingpuLectureCfgTitleExt;
import com.pinde.sci.model.mo.*;
import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/lecture4qingpu")
public class ResLecture4qingpuController extends GeneralController {
	@Autowired
	private IResLectureInfoBiz resLectureInfoBiz;
	@Autowired
	private IResLectureEvaDetailBiz resLectureEvaDetailBiz;
	@Autowired
	private IResLectureScanRegistBiz resLectureScanRegistBiz;
	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IResQingpuLectureCfgBiz lectureCfgBiz;
	@Autowired
	private IUserBiz userBiz;

	/**
	 * 讲座查询
	 */
	@RequestMapping("/manager/lectureView")
	public String lectureView(ResLectureInfo resLectureInfo, Model model, Integer currentPage, HttpServletRequest request) {
		PageHelper.startPage(currentPage, getPageSize(request));
		String lectureTrainDate = resLectureInfo.getLectureTrainDate();
		String lectureTeacherName = resLectureInfo.getLectureTeacherName();
		String content = resLectureInfo.getLectureContent();
		String lectureTypeId = resLectureInfo.getLectureTypeId();
		String place = resLectureInfo.getLectureTrainPlace();
		SysUser user= GlobalContext.getCurrentUser();
		String orgFlow = user.getOrgFlow();
		List<ResLectureInfo> lectureInfos = resLectureInfoBiz.SearchByDateContentTeacherNameType(orgFlow,lectureTrainDate, content, lectureTeacherName, lectureTypeId,place);
		if (lectureInfos != null) {
			model.addAttribute("lectureInfos", lectureInfos);
		}
		Map<String, Integer> evaMap = new HashMap<String, Integer>();
		if (lectureInfos != null && lectureInfos.size() > 0) {
			for (ResLectureInfo lectureInfo : lectureInfos) {
				String lectureFlow = lectureInfo.getLectureFlow();
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.SearchByLectureFlow(lectureFlow);
				if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
					double sum = 0, i = 0;
					for (ResLectureEvaDetail lectureEvaDetail : lectureEvaDetails) {
						String evaScore ="0";
						if(StringUtil.isNotBlank(lectureEvaDetail.getEvaScore())){
						 	evaScore = lectureEvaDetail.getEvaScore();
						}
						double score = Double.parseDouble(evaScore);
						sum += score;
						i++;
					}
					double result = sum / i;
					int eva = (int) Math.round(result);
					evaMap.put(lectureFlow, eva);
				}
			}
			model.addAttribute("evaMap", evaMap);
		}
		return "res/manager/lectureView4qingpu";
	}

	/**
	 * 讲座查询
	 */
	@RequestMapping("/exportLectureInfo")
	public void exportLectureInfo(ResLectureInfo resLectureInfo, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String lectureTrainDate = resLectureInfo.getLectureTrainDate();
		String lectureTeacherName = resLectureInfo.getLectureTeacherName();
		String content = resLectureInfo.getLectureContent();
		String lectureTypeId = resLectureInfo.getLectureTypeId();
		String place = resLectureInfo.getLectureTrainPlace();
		SysUser user= GlobalContext.getCurrentUser();
		String orgFlow = user.getOrgFlow();
		List<ResLectureInfo> lectureInfos = resLectureInfoBiz.SearchByDateContentTeacherNameType(orgFlow,lectureTrainDate, content, lectureTeacherName, lectureTypeId,place);
		Map<String, Integer> evaMap = new HashMap<String, Integer>();
		if (lectureInfos != null && lectureInfos.size() > 0) {
			for (ResLectureInfo lectureInfo : lectureInfos) {
				String lectureFlow = lectureInfo.getLectureFlow();
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.SearchByLectureFlow(lectureFlow);
				if (lectureEvaDetails != null && lectureEvaDetails.size() > 0) {
					double sum = 0, i = 0;
					for (ResLectureEvaDetail lectureEvaDetail : lectureEvaDetails) {
						String evaScore ="0";
						if(StringUtil.isNotBlank(lectureEvaDetail.getEvaScore())){
							evaScore = lectureEvaDetail.getEvaScore();
						}
						double score = Double.parseDouble(evaScore);
						sum += score;
						i++;
					}
					double result = sum / i;
					int eva = (int) Math.round(result);
					evaMap.put(lectureFlow, eva);
				}
			}
			model.addAttribute("evaMap", evaMap);
		}

		List<Map<String,Object>> exportList = new ArrayList<>();
		if(lectureInfos!=null && lectureInfos.size()>0){
			for(ResLectureInfo lectureInfo:lectureInfos){
				Map<String,Object> map = new HashMap<>();
				map.put("lectureContent",lectureInfo.getLectureContent());
				map.put("lectureTeacherName",lectureInfo.getLectureTeacherName());
				map.put("lectureTrainDate",lectureInfo.getLectureTrainDate());
				map.put("lectureDescription",lectureInfo.getLectureDescription());
				map.put("score",evaMap.get(lectureInfo.getLectureFlow()));
				exportList.add(map);
			}
		}
		String [] titles = {
				"lectureContent:讲座标题",
				"lectureTeacherName:主讲人",
				"lectureTrainDate:讲座日期",
				"lectureDescription:讲座内容",
				"score:评价均分"
		};
		String fileName = "讲座信息列表导出.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		ExcleUtile.exportSimpleExcleByObjs(titles, exportList, response.getOutputStream());
		response.setContentType("application/octet-stream;charset=UTF-8");
	}

	/**
	 * 讲座添加/修改
	 */
	@RequestMapping("/manager/lectureDetail")
	public String addLecture(String lectureFlow, Model model) {
		SysUser currentUser = GlobalContext.getCurrentUser();
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		model.addAttribute("lectureInfo", lectureInfo);
		if(StringUtil.isBlank(lectureFlow)) {
			lectureFlow = PkUtil.getUUID();
			model.addAttribute("signUrl", "func://funcFlow=lectureSignin&lectureFlow=" + lectureFlow);
			model.addAttribute("lectureFlow",lectureFlow);
		}else{
			model.addAttribute("signUrl", "func://funcFlow=lectureSignin&lectureFlow=" + lectureFlow);
			model.addAttribute("lectureFlow",lectureFlow);
		}
		String roleFlow = "";
		if(StringUtil.isNotBlank(InitConfig.getSysCfg("res_teacher_role_flow"))){
			roleFlow = InitConfig.getSysCfg("res_teacher_role_flow");
		}
		List<SysUser> teachers = userBiz.findUserByOrgFlowAndRoleFlow(currentUser.getOrgFlow(),roleFlow);
		model.addAttribute("teachers",teachers);
		return "res/manager/lectureAdd4qingpu";
	}

	/**
	 * 讲座保存
	 */
	@RequestMapping("/manager/saveLecture")
	@ResponseBody
	public String saveLecture(ResLectureInfo lectureInfo){
		SysUser current = GlobalContext.getCurrentUser();
		String orgFlow = current.getOrgFlow();
		lectureInfo.setOrgFlow(orgFlow);
		String lectureTypeId = lectureInfo.getLectureTypeId();
		String lectureTypeName = QingpuLectureTypeEnum.getNameById(lectureTypeId);
//		if(!lectureTypeId.equals(QingpuLectureTypeEnum.TrainingForTeachers.getId())){
//			QingpuLectureEvalCfg search = new QingpuLectureEvalCfg();
//			search.setTypeId(lectureTypeId);
//			search.setOrgFlow(orgFlow);
//			List<QingpuLectureEvalCfg> cfg = lectureCfgBiz.searchLectureEvalCfgList(search);
//			if(cfg==null||cfg.size()==0){
//				return "-1";
//			}
//			String cfgBigValue = cfg.get(0).getCfgBigValue();
//			lectureInfo.setEvalFormContent(cfgBigValue);
//		}
		String lectureLevelId = lectureInfo.getLectureLevelId();
		String lectureLevelName = DictTypeEnum.LectureLevel.getDictNameById(lectureLevelId);
		lectureInfo.setLectureTypeName(lectureTypeName);
		lectureInfo.setLectureLevelName(lectureLevelName);
		resLectureInfoBiz.editLectureInfo(lectureInfo);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;

	}
	/**
	 * 讲座删除
	 */
	@RequestMapping("/manager/delLecture")
	@ResponseBody
	public String delLecture(String lectureFlow){
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		lectureInfo.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		resLectureInfoBiz.editLectureInfo(lectureInfo);
		return GlobalConstant.DELETE_SUCCESSED;
	}

	/**
	 * 打开签到二维码
	 */
	@RequestMapping("/manager/signUrl")
	public String signUrl(String lectureFlow,Model model){
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		String signUrl = lectureInfo.getLectureCodeUrl();
		model.addAttribute("signUrl",signUrl);
		return "res/manager/lectureSign4qingpu";
	}

	/**
	 * 打开扫码或报名页面
	 */
	@RequestMapping("/manager/getEva")
	public String getEva(String lectureFlow,String flag,Model model){
		model.addAttribute("flag",flag);
		model.addAttribute("lectureFlow",lectureFlow);
		return "res/manager/evaView4qingpu";
	}

	/**
	 * 扫码页面
	 */
	@RequestMapping("/manager/evaList")
	public String evaList(String lectureFlow,Model model){
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		model.addAttribute("lectureInfo",lectureInfo);
		List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchIsScan(lectureFlow, null);
		Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
		if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
			for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
				String operUserFlow = lectureScanRegist.getOperUserFlow();
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(operUserFlow,lectureFlow);
				if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
					ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
					evaDetailMap.put(operUserFlow,lectureEvaDetail);
				}
			}
			model.addAttribute("lectureScanRegists",lectureScanRegists);
		}
		model.addAttribute("evaDetailMap",evaDetailMap);
		return "res/manager/evaList4qingpu";
	}

	/**
	 * 报名页面
	 */
	@RequestMapping("/manager/noRegist")
	public String noRegist(String lectureFlow,Model model){
		List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, null);
		if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
			model.addAttribute("lectureScanRegists",lectureScanRegists);
		}
		return "res/manager/noRegist4qingpu";
	}

	/**
	 * 学员信息导出
	 * @param flag 已扫码/已报名标识
	 * @param lectureFlow 讲座流水号
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/manager/expDoctorExcel", method={RequestMethod.POST,RequestMethod.GET})
	public void expDoctorExcel(String lectureFlow,String flag, HttpServletResponse response) throws Exception {
		List<ResLectureScanRegist> dataList = resLectureScanRegistBiz.queryScanRegistDocList(lectureFlow,flag);
		String[] titles = new String[]{
				"operUserName:姓名",
				"trainingSpeName:专业",
				"sessionNumber:届别"
		};
		ExcleUtile.exportSimpleExcleByObjs(titles, dataList,response.getOutputStream());
		String fileName = ("scan".equals(flag)?"扫码":"报名")+"学员名单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
	}
	/**
	 * 报名讲座
	 */
	@RequestMapping("/lectureRegist")
	public String lectureRegist(String lectureFlow,Model model){
		boolean successFlag = true;
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		if(resLectureScanRegistBiz.editLectureScanRegist(lectureFlow,lectureInfo.getLimitNum())==0){
			successFlag = false;
		}
		model.addAttribute("successFlag",successFlag);
		if(successFlag){
			String lectureTrainDate = lectureInfo.getLectureTrainDate();
			String lectureStartTime = lectureInfo.getLectureStartTime();
			String year = lectureTrainDate.substring(0,4);
			model.addAttribute("year",year);
			String month = lectureTrainDate.substring(5,7);
			model.addAttribute("month",month);
			String day =  lectureTrainDate.substring(8,10);
			model.addAttribute("day",day);
			String hour = lectureStartTime.substring(0,2);
			model.addAttribute("hour",hour);
			String min = lectureStartTime.substring(3,5);
			model.addAttribute("min",min);
		}
		return "res/doctor/lectureRegist4qingpu";
	}
	@RequestMapping("/doctorLectureView")
	public String doctorLectureView(){
		return "res/doctor/lectureView4qingpu";
	}

	/**
	 * 最新讲座查询
	 */
	@RequestMapping("/getNewLectures")
	public String getNewLectures(Model model) {
		SysUser currUser = GlobalContext.getCurrentUser();
		String orgFlow = currUser.getOrgFlow();
		if(StringUtil.isBlank(orgFlow)){
			orgFlow = doctorBiz.readDoctor(currUser.getUserFlow()).getOrgFlow();
		}
		String userFlow = currUser.getUserFlow();
		List<ResLectureInfo> lectureInfos = resLectureInfoBiz.searchNewLectures(orgFlow, "", "",null);
		model.addAttribute("lectureInfos",lectureInfos);
		Map<String,ResLectureScanRegist> registMap = new HashMap<>();
		if(lectureInfos!=null&&lectureInfos.size()>0){
			for(ResLectureInfo lectureInfo:lectureInfos){
				String lectureFlow = lectureInfo.getLectureFlow();
				ResLectureScanRegist lectureScanRegist = resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
				registMap.put(lectureFlow,lectureScanRegist);
			}
			model.addAttribute("registMap",registMap);
		}
		return "res/doctor/newLectures4qingpu";
	}
	/**
	 * 历史讲座查询
	 */
	@RequestMapping("/getHistoryLectures")
	public String getHistoryLectures(Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String userFlow = currentUser.getUserFlow();
		List<ResLectureScanRegist> lectureScanRegists = resLectureScanRegistBiz.searchByUserFLowAndRegist(userFlow);
		List<ResLectureInfo> lectureInfos = new ArrayList<ResLectureInfo>();
		Map<String,ResLectureEvaDetail> evaDetailMap = new HashMap<>();
		Map<String,Integer> evaMap = new HashMap<>();
		Map<String,String> scanMap = new HashMap<>();
		Map<String,String> scan2Map = new HashMap<>();
		Map<String,ResLectureScanRegist> registMap = new HashMap<>();
		if(lectureScanRegists!=null&&lectureScanRegists.size()>0){
			String currDateTime = DateUtil.getCurrDateTime();
			String currDate = currDateTime.substring(0,4)+"-"+currDateTime.substring(4,6)+"-"+currDateTime.substring(6,8);
			String currTime = currDateTime.substring(8,10)+":"+currDateTime.substring(10,12);
			for(ResLectureScanRegist lectureScanRegist:lectureScanRegists){
				String isScan = lectureScanRegist.getIsScan();
				String isScan2 = lectureScanRegist.getIsScan2();
				String lectureFlow = lectureScanRegist.getLectureFlow();
				ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
				String lectureEndTime = lectureInfo.getLectureEndTime();
				String lectureTrainDate = lectureInfo.getLectureTrainDate();
				//判断是否到评价期限
				String date = lectureInfo.getLectureTrainDate();
				String time = lectureInfo.getLectureEndTime();
				String unitID = lectureInfo.getLectureUnitId();
				String period = lectureInfo.getLectureEvaPeriod();
				String startDate = date.substring(0,4)+date.substring(5,7)+date.substring(8,10)+time.substring(0,2)+time.substring(3,5)+"00";
				int step = 0;
				if(SchUnitEnum.Hour.getId().equals(unitID)){
					step = Integer.parseInt(period);
				}
				if(SchUnitEnum.Day.getId().equals(unitID)){
					step = Integer.parseInt(period)*24;
				}
				if(SchUnitEnum.Week.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*7;
				}
				if(SchUnitEnum.Month.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*30;
				}
				if(SchUnitEnum.Year.getId().equals(unitID)){
					step = Integer.parseInt(period)*24*365;
				}
				String endDate = DateUtil.addHour(startDate,step);
				String currentDate = DateUtil.getCurrDateTime();
				int dateFlag = endDate.compareTo(currentDate);
				//判断结束
				if(GlobalConstant.RECORD_STATUS_Y.equals(lectureInfo.getRecordStatus())&&((lectureEndTime.compareTo(currTime)<0&&lectureTrainDate.compareTo(currDate)==0)||(lectureTrainDate.compareTo(currDate)<0))){
					lectureInfos.add(lectureInfo);
					if("Y".equals(isScan))
					{
						scanMap.put(lectureFlow,"Y");
					}
					if("Y".equals(isScan2)) {
						scan2Map.put(lectureFlow, "Y");
					}
					evaMap.put(lectureFlow,dateFlag);
				}
				List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
				if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0){
					ResLectureEvaDetail lectureEvaDetail = lectureEvaDetails.get(0);
					evaDetailMap.put(lectureFlow,lectureEvaDetail);
				}
				registMap.put(lectureFlow,lectureScanRegist);
			}
		}
		model.addAttribute("scanMap",scanMap);
		model.addAttribute("evaMap",evaMap);
		model.addAttribute("registMap",registMap);
		model.addAttribute("evaDetailMap",evaDetailMap);
		model.addAttribute("lectureInfos",lectureInfos);
		return "res/doctor/historyLectures4qingpu";
	}

	@RequestMapping(value="/upload")
	public String upload(String registFlow,String isUpload,Model model) throws DocumentException {

		ResLectureScanRegist regist=resLectureScanRegistBiz.readByFlow(registFlow);
		model.addAttribute("regist",regist);
		if(regist!=null)
		{
			String content=regist.getProofUrls();
			if(StringUtil.isBlank(content))
			{
				Document dom = DocumentHelper.createDocument();
				Element root= dom.addElement("LectureImages");
				content=root.asXML();
				regist.setProofUrls(content);
				resLectureScanRegistBiz.saveRegist(regist);
			}
			List<Map<String, String>> imageList=parseImageXml(content);
			model.addAttribute("imageList", imageList);
		}
		model.addAttribute("isUpload", isUpload);
		return "res/doctor/uploadImage";
	}

	public List<Map<String, String>> parseImageXml(String content) throws DocumentException {
		if(StringUtil.isNotBlank(content)) {
			Document document = DocumentHelper.parseText(content);
			Element elem = document.getRootElement();
			List<Element> ec = elem.elements();
			List<Map<String, String>> imageList = new ArrayList<Map<String, String>>();
			for (Element element : ec) {
				Map<String, String> imageMap = new HashMap<String, String>();
				String imageFlow = element.attributeValue("imageFlow");
				imageMap.put("imageFlow", imageFlow);
				List<Element> items = element.elements();
				for (Element item : items) {
					String itemName = item.getName();
					String itemText = item.getTextTrim();
					imageMap.put(itemName, itemText);
				}
				imageList.add(imageMap);
			}
			return imageList;
		}
		return null;
	}

	@RequestMapping(value="/registImgUpload")
	@ResponseBody
	public Map<String,String> registImgUpload(String registFlow,MultipartFile checkFile){
		Map<String, String> map = null;
		if(checkFile!=null && checkFile.getSize() > 0){
			String fileAddress="LectureFlie";
			map=resLectureScanRegistBiz.registImg(registFlow,checkFile,fileAddress);
		}
		return map;
	}

	@RequestMapping(value="/registImgDelete")
	@ResponseBody
	public String registImgDelete(String registFlow, String imageFlow) throws DocumentException{
		ResLectureScanRegist regist=resLectureScanRegistBiz.readByFlow(registFlow);
		if(StringUtil.isNotBlank(regist.getProofUrls())) {
			String content = regist.getProofUrls();
			Document document = DocumentHelper.parseText(content);
			Element elem = document.getRootElement();
			Node delNode = elem.selectSingleNode("image[@imageFlow='" + imageFlow + "']");
			if (delNode != null) {
				delNode.detach();
				regist.setProofUrls(document.asXML());
				resLectureScanRegistBiz.saveRegist(regist);
				return GlobalConstant.OPRE_SUCCESSED_FLAG;
			} else {
				return GlobalConstant.OPRE_FAIL_FLAG;
			}
		}
		return GlobalConstant.OPRE_FAIL_FLAG;
	}

	/**
	 * 进入评分页面
	 */
	@RequestMapping("/evaluate")
	public String evaluate(String lectureFlow,String flag,Model model) throws DocumentException {
		ResLectureInfo resLectureInfo = resLectureInfoBiz.read(lectureFlow);
		String lectureTypeId = resLectureInfo.getLectureTypeId();
		model.addAttribute("lectureFlow",lectureFlow);
		model.addAttribute("flag",flag);
//		if(QingpuLectureTypeEnum.TrainingForTeachers.getId().equals(lectureTypeId)){
			SysUser current = GlobalContext.getCurrentUser();
			String userFlow = current.getUserFlow();
			List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
			if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
				ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
				if(resLectureEvaDetail!=null) {
					model.addAttribute("resLectureEvaDetail", resLectureEvaDetail);
				}
			}
			return "res/doctor/addEvaluate4qingpu";
//		}else{
//			SysUser current = GlobalContext.getCurrentUser();
//			String userFlow = current.getUserFlow();
//			List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,lectureFlow);
//			if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
//				ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
//				if(resLectureEvaDetail!=null) {
//					model.addAttribute("resLectureEvaDetail", resLectureEvaDetail);
//					Map<String,Object> gradeMap = parseGradeXml(resLectureEvaDetail.getEvalFormContent());
//					model.addAttribute("gradeMap",gradeMap);
//				}
//			}
//			if(StringUtil.isNotBlank(resLectureInfo.getEvalFormContent())) {
//				Document dom = DocumentHelper.parseText(resLectureInfo.getEvalFormContent());
//				String titleXpath = "//title";
//				List<Element> titleElementList = dom.selectNodes(titleXpath);
//				if (titleElementList != null && !titleElementList.isEmpty()) {
//					List<QingpuLectureCfgTitleExt> titleFormList = new ArrayList<QingpuLectureCfgTitleExt>();
//					for (Element te : titleElementList) {
//						QingpuLectureCfgTitleExt titleForm = new QingpuLectureCfgTitleExt();
//						titleForm.setId(te.attributeValue("id"));
//						titleForm.setName(te.attributeValue("name"));
//						titleForm.setOrder(te.attributeValue("order"));
//						List<Element> itemElementList = te.elements("item");
//						List<QingpuLectureCfgItemExt> itemFormList = null;
//						if (itemElementList != null && !itemElementList.isEmpty()) {
//							itemFormList = new ArrayList<QingpuLectureCfgItemExt>();
//							for (Element ie : itemElementList) {
//								QingpuLectureCfgItemExt itemForm = new QingpuLectureCfgItemExt();
//								itemForm.setId(ie.attributeValue("id"));
//								itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
//								String score = ie.element("score") == null ? "" : ie.element("score").getTextTrim();
//								itemForm.setScore(score);
//								itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
//								itemFormList.add(itemForm);
//							}
//						}
//						titleForm.setItemList(itemFormList);
//						titleFormList.add(titleForm);
//					}
//					model.addAttribute("titleFormList", titleFormList);
//				}
//			}
//			return "res/doctor/gradeForm4qp";
//		}
	}

	/**
	 * 管理员查看评分页面
	 */
	@RequestMapping("/getdocEva")
	public String getdocEva(String lectureFlow,String doctorFlow,Model model) throws DocumentException {
		ResLectureInfo resLectureInfo = resLectureInfoBiz.read(lectureFlow);
		String lectureTypeId = resLectureInfo.getLectureTypeId();
		model.addAttribute("lectureFlow",lectureFlow);
		model.addAttribute("flag","N");
		List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(doctorFlow,lectureFlow);
		if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
			ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
			if(resLectureEvaDetail!=null) {
				model.addAttribute("resLectureEvaDetail", resLectureEvaDetail);
				Map<String,Object> gradeMap = parseGradeXml(resLectureEvaDetail.getEvalFormContent());
				model.addAttribute("gradeMap",gradeMap);
			}
		}
		if(StringUtil.isNotBlank(resLectureInfo.getEvalFormContent())) {
			Document dom = DocumentHelper.parseText(resLectureInfo.getEvalFormContent());
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if (titleElementList != null && !titleElementList.isEmpty()) {
				List<QingpuLectureCfgTitleExt> titleFormList = new ArrayList<QingpuLectureCfgTitleExt>();
				for (Element te : titleElementList) {
					QingpuLectureCfgTitleExt titleForm = new QingpuLectureCfgTitleExt();
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					titleForm.setOrder(te.attributeValue("order"));
					List<Element> itemElementList = te.elements("item");
					List<QingpuLectureCfgItemExt> itemFormList = null;
					if (itemElementList != null && !itemElementList.isEmpty()) {
						itemFormList = new ArrayList<QingpuLectureCfgItemExt>();
						for (Element ie : itemElementList) {
							QingpuLectureCfgItemExt itemForm = new QingpuLectureCfgItemExt();
							itemForm.setId(ie.attributeValue("id"));
							itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
							String score = ie.element("score") == null ? "" : ie.element("score").getTextTrim();
							itemForm.setScore(score);
							itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
							itemFormList.add(itemForm);
						}
					}
					titleForm.setItemList(itemFormList);
					titleFormList.add(titleForm);
				}
				model.addAttribute("titleFormList", titleFormList);
			}
		}
		return "res/doctor/gradeForm4qp";
	}

	/**
	 * 管理员导出评分详情
	 */
	@RequestMapping("/exportDocEva")
	public void exportDocEva(String lectureFlow,String doctorFlow,Model model,HttpServletResponse response) throws Exception {
		ResLectureInfo resLectureInfo = resLectureInfoBiz.read(lectureFlow);
		List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(doctorFlow,lectureFlow);
		Map<String,Object> gradeMap = null;
		if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
			ResLectureEvaDetail resLectureEvaDetail = lectureEvaDetails.get(0);
			if(resLectureEvaDetail!=null) {
				model.addAttribute("resLectureEvaDetail", resLectureEvaDetail);
				gradeMap = parseGradeXml(resLectureEvaDetail.getEvalFormContent());
			}
		}
		if(StringUtil.isNotBlank(resLectureInfo.getEvalFormContent())) {
			Document dom = DocumentHelper.parseText(resLectureInfo.getEvalFormContent());
			String titleXpath = "//title";
			List<Element> titleElementList = dom.selectNodes(titleXpath);
			if (titleElementList != null && !titleElementList.isEmpty()) {
				List<QingpuLectureCfgTitleExt> titleFormList = new ArrayList<QingpuLectureCfgTitleExt>();
				for (Element te : titleElementList) {
					QingpuLectureCfgTitleExt titleForm = new QingpuLectureCfgTitleExt();
					titleForm.setId(te.attributeValue("id"));
					titleForm.setName(te.attributeValue("name"));
					titleForm.setOrder(te.attributeValue("order"));
					List<Element> itemElementList = te.elements("item");
					List<QingpuLectureCfgItemExt> itemFormList = null;
					if (itemElementList != null && !itemElementList.isEmpty()) {
						itemFormList = new ArrayList<QingpuLectureCfgItemExt>();
						for (Element ie : itemElementList) {
							QingpuLectureCfgItemExt itemForm = new QingpuLectureCfgItemExt();
							itemForm.setId(ie.attributeValue("id"));
							itemForm.setName(ie.element("name") == null ? "" : ie.element("name").getTextTrim());
							String score = ie.element("score") == null ? "" : ie.element("score").getTextTrim();
							itemForm.setScore(score);
							itemForm.setOrder(ie.element("order") == null ? "" : ie.element("order").getTextTrim());
							itemFormList.add(itemForm);
						}
					}
					titleForm.setItemList(itemFormList);
					titleFormList.add(titleForm);
				}
				List<Map<String, Object>> exportList = new ArrayList<>();
				if (titleFormList != null && titleElementList.size() > 0) {
					for (QingpuLectureCfgTitleExt titleExt : titleFormList) {
						List<QingpuLectureCfgItemExt> itemExts = titleExt.getItemList();
						if (itemExts != null && itemExts.size() > 0) {
							for (QingpuLectureCfgItemExt itemExt : itemExts) {
								Map<String, Object> map = new HashMap<>();
								map.put("order", itemExt.getOrder());
								map.put("name", itemExt.getName());
								map.put("score", itemExt.getScore());
								map.put("docScore", ((Map<String, String>) gradeMap.get(itemExt.getId())).get("score"));
								exportList.add(map);
							}
						}
					}
				}
				String[] titles = {
						"order:项目",
						"name:评分细则",
						"score:总分",
						"docScore:学员打分"
				};
				String fileName = "讲座明细导出.xls";
				fileName = URLEncoder.encode(fileName, "UTF-8");
				response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
				ExcleUtile.exportSimpleExcleByObjs(titles, exportList, response.getOutputStream());
				response.setContentType("application/octet-stream;charset=UTF-8");
			}
		}
	}

	public Map<String,Object> parseGradeXml(String recContent){
		Map<String,Object> gradeMap = null;
		if(StringUtil.isNotBlank(recContent)){
			try {
				Document doc = DocumentHelper.parseText(recContent);
				Element root = doc.getRootElement();
				if(root!=null){
					List<Element> items = root.elements("grade");
					if(items!=null && items.size()>0){
						gradeMap = new HashMap<String, Object>();
						for(Element e : items){
							String assessId = e.attributeValue("assessId");
							Map<String,String> dataMap = new HashMap<String, String>();
							gradeMap.put(assessId,dataMap);

							Element score = e.element("score");
							if(score!=null){
								String scoreStr = score.getText();
								dataMap.put("score",scoreStr);
							}
						}

						Element totalScore = root.element("totalScore");
						if(totalScore!=null){
							gradeMap.put("totalScore",totalScore.getText());
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return gradeMap;
	}

	/**
	 * 保存评分(评价表单)
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/saveGrade",method={RequestMethod.POST})
	@ResponseBody
	public String saveGrade(ResLectureEvaDetail lectureEvaDetail,HttpServletRequest request,String totalScore){
		String recContent = createGradeXml(request.getParameterMap());
		if(lectureEvaDetail != null){
			lectureEvaDetail.setEvalFormContent(recContent);
			lectureEvaDetail.setEvaScore(totalScore);
			SysUser current = GlobalContext.getCurrentUser();
			String userFlow = current.getUserFlow();
			String userName = current.getUserName();
			if(StringUtil.isNotBlank(userFlow)){
				lectureEvaDetail.setOperUserFlow(userFlow);
			}
			if(StringUtil.isNotBlank(userName)){
				lectureEvaDetail.setOperUserName(userName);
			}
		}
		if(resLectureEvaDetailBiz.editResLectureEvaDetail(lectureEvaDetail) != GlobalConstant.ZERO_LINE){
			return "1";
		}
		return GlobalConstant.SAVE_FAIL;
	}

	private String createGradeXml(Map<String,String[]> gradeInfo){
		Document document = DocumentHelper.createDocument();
		Element rootEle = document.addElement("gradeInfo");
		if(gradeInfo!=null){
			String[] assessIds = gradeInfo.get("assessId");
			String[] scores = gradeInfo.get("score");
			if(assessIds!=null && assessIds.length>0){
				for(int i = 0 ; i<assessIds.length ; i++){
					String assessId = assessIds[i];
					String score = scores[i];
					Element item = rootEle.addElement("grade");
					item.addAttribute("assessId",assessId);

					Element scoreElement = item.addElement("score");
					scoreElement.setText(score);
				}
			}

			String[] totalScore = gradeInfo.get("totalScore");
			if(totalScore!=null && totalScore.length>0 && StringUtil.isNotBlank(totalScore[0])){
				Element item = rootEle.addElement("totalScore");
				item.setText(totalScore[0]);
			}
		}
		return document.asXML();
	}
	/**
	 * 保存评价
	 */
	@RequestMapping("/saveEvaluate")
	@ResponseBody
	public String saveEvaluate(ResLectureEvaDetail resLectureEvaDetail){
		SysUser current = GlobalContext.getCurrentUser();
		String userFlow = current.getUserFlow();
		String userName = current.getUserName();
		if(StringUtil.isNotBlank(userFlow)){
			resLectureEvaDetail.setOperUserFlow(userFlow);
		}
		if(StringUtil.isNotBlank(userName)){
			resLectureEvaDetail.setOperUserName(userName);
		}
		List<ResLectureEvaDetail> lectureEvaDetails = resLectureEvaDetailBiz.searchByUserFlowLectureFlow(userFlow,resLectureEvaDetail.getLectureFlow());
		if(lectureEvaDetails!=null&&lectureEvaDetails.size()>0) {
			return  "已经评价过讲座信息！请刷新页面后重试！";
		}
		resLectureEvaDetailBiz.editResLectureEvaDetail(resLectureEvaDetail);
		return GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	@RequestMapping("/uploadFile")
	public String uploadFile(String lectureFlow, Model model) {
		model.addAttribute("lectureFlow", lectureFlow);
		return "res/manager/uploadFile";
	}

	@RequestMapping("/delFile")
	@ResponseBody
	public String delFile(String lectureFlow, Model model) {
		ResLectureInfo save = new ResLectureInfo();
		save.setLectureFlow(lectureFlow);
		save.setCoursewareUrl("");
		resLectureInfoBiz.editLectureInfo(save);
		return "1";
	}

	@RequestMapping(value = "/checkUploadFile", method = {RequestMethod.POST})
	public String checkUploadFile(String lectureFlow, MultipartFile uploadFile, Model model) {
		if(uploadFile.getSize()>10*1024*1024){
			model.addAttribute("tooBig","文件不得大于10M");
			return "res/manager/uploadFile";
		}
		model.addAttribute("lectureFlow", lectureFlow);
		if(uploadFile!=null && (!uploadFile.isEmpty())){
			String originalFileName = uploadFile.getOriginalFilename();
			//定义上传路径
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "coursewareUrl" + File.separator + dateString;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//重命名上传后的文件名
			originalFileName = PkUtil.getUUID() + originalFileName;
			File newFile = new File(fileDir, originalFileName);
			try {
				uploadFile.transferTo(newFile);
			} catch (Exception e) {
				throw new RuntimeException("文件上传异常");
			}
			String filePath = "/coursewareUrl/" + dateString + "/" + originalFileName;
			ResLectureInfo save = new ResLectureInfo();
			save.setLectureFlow(lectureFlow);
			save.setCoursewareUrl(filePath);
			resLectureInfoBiz.editLectureInfo(save);
			model.addAttribute("result","Y");
		}
		return "res/manager/uploadFile";
	}
	/**
	 * 取消报名讲座
	 */
	@RequestMapping("/lectureRegistCancel")
	@ResponseBody
	public String lectureRegistCancel(String lectureFlow){
		SysUser currUser = GlobalContext.getCurrentUser();
		String userFlow = currUser.getUserFlow();
		if(StringUtil.isNotBlank(lectureFlow))
		{
			ResLectureScanRegist regist=resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
			if(regist==null)
			{
				return "你未报名，无法取消报名信息！";
			}
			if(StringUtil.isBlank(regist.getIsRegist()))
			{
				return "你未报名，无法取消报名信息！";
			}
			if (!GlobalConstant.FLAG_Y.equals(regist.getIsRegist()))
			{
				return "你已取消报名！";
			}
			if(GlobalConstant.FLAG_Y.equals(regist.getIsScan()))
			{
				return "你已扫码签到，无法取消报名信息！";
			}
			regist.setIsRegist(GlobalConstant.FLAG_N);
			int c=resLectureScanRegistBiz.saveRegist(regist);
			if(c>0){
				return GlobalConstant.OPERATE_SUCCESSED;
			}
			return GlobalConstant.OPERATE_FAIL;
		}else{
			return "请选择需要取消报名的讲座！";
		}
	}
	/**
	 * 报名讲座(新)
	 */
	@RequestMapping("/lectureRegistNew")
	public synchronized String lectureRegistNew(String lectureFlow,String roleId,Model model){
		SysUser currUser = GlobalContext.getCurrentUser();
		String userFlow = currUser.getUserFlow();
		ResLectureScanRegist regist=resLectureScanRegistBiz.searchByUserFlowAndLectureFlow(userFlow,lectureFlow);
		if(regist!=null&&GlobalConstant.FLAG_Y.equals(regist.getIsRegist()))
		{
			model.addAttribute("isRegiest", "N");
			model.addAttribute("msg", "已经报过名了！！请刷新列表");
			return "res/doctor/lectureRegist4qingpu";
		}
		List<ResLectureScanRegist> resLectureScanRegists=resLectureScanRegistBiz.searchRegistByLectureFlow(lectureFlow, null);
		ResLectureInfo lectureInfo = resLectureInfoBiz.read(lectureFlow);
		if(StringUtil.isBlank(lectureInfo.getLimitNum())||resLectureScanRegists==null||resLectureScanRegists.size()<Integer.valueOf(lectureInfo.getLimitNum())) {
			List<ResLectureInfo> infos=resLectureScanRegistBiz.checkJoinList(lectureFlow,userFlow);
			if(infos!=null&&infos.size()>0)
			{
				ResLectureInfo resLectureInfo=infos.get(0);
				model.addAttribute("isRegiest", "N");
				model.addAttribute("msg", "已报名同一时间【"+resLectureInfo.getLectureContent()+"】，不能报名！");
				return "res/doctor/lectureRegist4qingpu";
			}
			int count= resLectureScanRegistBiz.editLectureScanRegist(lectureFlow);
			if(count<0)
			{
				model.addAttribute("isRegiest", "N");
				model.addAttribute("msg", "该讲座报名人数已满，请刷新列表页面！");
				return "res/doctor/lectureRegist4qingpu";
			}
			if(count==0)
			{
				model.addAttribute("isRegiest", "N");
				model.addAttribute("msg", "报名失败，请刷新列表页面！");
				return "res/doctor/lectureRegist4qingpu";
			}
			String lectureTrainDate = lectureInfo.getLectureTrainDate();
			String lectureStartTime = lectureInfo.getLectureStartTime();
			String year = lectureTrainDate.substring(0, 4);
			model.addAttribute("year", year);
			String month = lectureTrainDate.substring(5, 7);
			model.addAttribute("month", month);
			String day = lectureTrainDate.substring(8, 10);
			model.addAttribute("day", day);
			String hour = lectureStartTime.substring(0, 2);
			model.addAttribute("hour", hour);
			String min = lectureStartTime.substring(3, 5);
			model.addAttribute("min", min);
			model.addAttribute("isRegiest", "Y");
		}else{
			model.addAttribute("isRegiest", "N");
			model.addAttribute("msg", "该讲座报名人数已满，请刷新列表页面！");
		}
		return "res/doctor/lectureRegist4qingpu";
	}
}


