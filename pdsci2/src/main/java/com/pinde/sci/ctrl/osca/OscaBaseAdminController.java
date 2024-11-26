
package com.pinde.sci.ctrl.osca;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.DictTypeEnum;
import com.pinde.core.model.SysDict;
import com.pinde.core.page.PageHelper;
import com.pinde.core.pdf.DocumentVo;
import com.pinde.core.pdf.PdfDocumentGenerator;
import com.pinde.core.pdf.utils.ResourceLoader;
import com.pinde.core.util.*;
import com.pinde.sci.YuYinUtil.DemoException;
import com.pinde.sci.YuYinUtil.TtsUtil;
import com.pinde.sci.biz.osca.IOscaBaseBiz;
import com.pinde.sci.biz.osca.IOscaDoctorOrderdeBiz;
import com.pinde.sci.biz.osca.IOscaProvincialBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.osca.AuditStatusEnum;
import com.pinde.sci.enums.osca.DoctorScoreEnum;
import com.pinde.sci.enums.osca.ExamStatusEnum;
import com.pinde.sci.enums.osca.SignStatusEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.osca.OscaCheckInfoExt;
import com.pinde.sci.model.osca.OscaFromCfgItemExt;
import com.pinde.sci.model.osca.OscaFromCfgTitleExt;
import com.pinde.sci.model.osca.OscaSkillsAssessmentExt;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

@Controller
@RequestMapping("/osca/base")
public class OscaBaseAdminController extends GeneralController {

	@Autowired
	private IOscaBaseBiz baseBiz;
	@Autowired
	private IDictBiz dictBiz;
	@Autowired
	private IOscaProvincialBiz provincialBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOscaDoctorOrderdeBiz oscaDoctorOrderdeBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	/**
	 * 引导页
	 */
	@RequestMapping("/guideInfo")
	public String guideInfo(Integer currentPage,HttpServletRequest request,Model model){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		int count = baseBiz.queryAppointCount(orgFlow);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> dataList = baseBiz.queryGuideInfoList(orgFlow);
		model.addAttribute("count",count);
		model.addAttribute("dataList",dataList);
		if(count > 0 && null != dataList && dataList.size() == 0){
            return checkInfoList(null, null, null, null, null, GlobalConstant.FLAG_Y, request, model, GlobalConstant.FLAG_Y);
		}
		return "osca/base/guideInfo";
	}
	/**
	 * 考核信息列表查询
     */
	@RequestMapping("/checkInfoList")
	public String checkInfoList(Integer currentPage, String clinicalYear, String speId, String trainingTypeId, String clinicalName, String isLocal,
                                HttpServletRequest request, Model model, String jumpFlag){
		model.addAttribute("jumpFlag",jumpFlag);
		Map<String,Object> param = new HashMap<>();
		param.put("clinicalYear",clinicalYear);
		param.put("speId",speId);
		param.put("clinicalName",clinicalName);
		param.put("isLocal",isLocal);
		SysDict searchDict = new SysDict();
        searchDict.setRecordStatus(GlobalConstant.FLAG_Y);
		searchDict.setDictTypeId(DictTypeEnum.OscaTrainingType.getId()+"."+trainingTypeId);
		List<SysDict> dictList = dictBiz.searchDictList(searchDict);
		param.put("dictList",dictList);
		param.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> dataList = baseBiz.queryDataList(param);
		model.addAttribute("dataList",dataList);
		return "osca/base/checkInfoList";
	}

	/**
	 * 新增/编辑考核信息列表界面
     */
	@RequestMapping(value="/addCheckInfo")
	public String addCheckInfo(String clinicalFlow,String flag,Model model){
		if(StringUtil.isNotBlank(clinicalFlow)){
			OscaSkillsAssessment osa = baseBiz.queryDataByFlow(clinicalFlow);
			List<OscaSkillsAssessmentTime> times=baseBiz.queryOsaTimeList(clinicalFlow);
			List<String> flowList=baseBiz.queryDoctorFlowList(clinicalFlow,null);
			model.addAttribute("osa",osa);
			model.addAttribute("times",times);
			model.addAttribute("passedNum",flowList==null?0:flowList.size());
		}
		model.addAttribute("flag",flag);
		return "/osca/base/addCheckInfo";
	}

	/**
	 * 培训专业与考核方案的配置关系查询
	 */
	@RequestMapping(value="/querySpeRelation")
	@ResponseBody
	public List<OscaSubjectMain> querySpeRelation(String speId, String actionTypeId){
		return baseBiz.querySpeRelation(speId,actionTypeId);
	}

	/**
	 * 加载基地(考点)下结业考核信息
	 */
	@RequestMapping(value="/queryInitSpe")
	@ResponseBody
	public List<OscaOrgSpe> queryInitSpe(){
		String orgFlow = GlobalContext.getCurrentUser().getOrgFlow();
		return baseBiz.queryInitSpe(orgFlow);
	}

	/**
	 * 新增考核信息保存操作
	 */
	@RequestMapping("/saveCheckInfo")
	@ResponseBody
	public String saveCheckInfo(OscaSkillsAssessment osa, String jsondata){
		List<OscaSkillsAssessmentTime> times=new ArrayList<>();
		Map<String,Object> mp = JSON.parseObject(jsondata,Map.class);
		List<Map<String,String>> timeList=(List<Map<String,String>>)mp.get("timeList");
		if(timeList!=null&&timeList.size()>0){
			for (Map<String,String> tempMap:timeList) {
				OscaSkillsAssessmentTime t=new OscaSkillsAssessmentTime();
				t.setRecrodFlow(tempMap.get("recrodFlow"));
				t.setExamStartTime(tempMap.get("examStartTime"));
				t.setExamEndTime(tempMap.get("examEndTime"));
				t.setTestNumber(tempMap.get("testNumber"));
				times.add(t);
			}
		}
//		int num = baseBiz.saveCheckInfo(osa);
		int num = baseBiz.saveCheckInfoNew(osa,times);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 考核信息发布操作
	 */
	@RequestMapping("/releasedInfo")
	@ResponseBody
	public String releasedInfo(String clinicalFlow){
		int num = baseBiz.releasedInfo(clinicalFlow);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 考核信息删除操作
	 */
	@RequestMapping("/delCheckInfo")
	@ResponseBody
	public String delCheckInfo(String clinicalFlow,String isReleased){
		int num = baseBiz.delCheckInfo(clinicalFlow,isReleased);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 查看考核信息二维码
	 */
	@RequestMapping("/queryQrCode")
	public String signUrl(String clinicalFlow,Model model){
		if(StringUtil.isNotBlank(clinicalFlow)){
			OscaSkillsAssessment osa = baseBiz.queryDataByFlow(clinicalFlow);
			model.addAttribute("codeInfo",osa.getCodeInfo());
			model.addAttribute("clinicalName",osa.getClinicalName());
		}
		return "osca/base/checkInfoQrCode";
	}

	/**
	 * 考核信息跳转页面（默认预约信息标签页）
	 */
	@RequestMapping("/checkInfoManage")
	public String checkInfoManage(Integer currentPage1, String auditStatusId, String appointDoctorName, String year,
                                  String clinicalFlow, String clinicalName, String speName, String sessionId,
                                  String initFlag, HttpServletRequest request, Model model, String isLocal) throws IOException{
		Map<String,String> param = new HashMap<>();
		param.put("auditStatusId",auditStatusId);
		param.put("doctorName",appointDoctorName);
		param.put("clinicalFlow",clinicalFlow);
		param.put("sessionId",sessionId);
		param.put("year", year);
		clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
		speName = java.net.URLDecoder.decode(speName,"UTF-8");
		PageHelper.startPage(currentPage1,getPageSize(request));
		List<OscaCheckInfoExt> appointList = baseBiz.queryAppointList(param);
		model.addAttribute("appointList",appointList);
		model.addAttribute("clinicalName",clinicalName);
		model.addAttribute("speName",speName);
		model.addAttribute("isLocal",isLocal);
		model.addAttribute("year",year);
		OscaSkillsAssessment oscaSkillsAssessment = baseBiz.queryDataByFlow(clinicalFlow);
		model.addAttribute("isGradeReleased",oscaSkillsAssessment.getIsGradeReleased());
		if(StringUtil.isNotBlank(initFlag) && initFlag.equals(GlobalConstant.RECORD_STATUS_Y)){
			return "osca/base/checkInfoManage";
		}
		return "osca/base/appointManage";
	}
	/**
	 * 预约学员审核操作
	 */
	@RequestMapping("/auditAppoint")
	@ResponseBody
	public String auditAppoint(String jsonData,String reason){
		Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
		List<String> recordLst = (List<String>)mp.get("recordLst");
		String auditStatusId = (String)mp.get("auditStatusId");
		int num = 0;
		if(null != recordLst && recordLst.size() > 0){
			for (int i = 0; i < recordLst.size(); i++) {
				num += baseBiz.auditAppoint(recordLst.get(i),auditStatusId,reason);
			}
		}
		if (num > 0) {
			return "信息审核成功！";
		}
		return "信息审核失败！";
	}

	/**
	 * 预约学员导出操作
	 */
	@RequestMapping(value="/expAppoint", method={RequestMethod.POST,RequestMethod.GET})
	public void expAppoint(String auditStatusId,String appointDoctorName,String clinicalFlow,HttpServletResponse response) throws Exception {
		Map<String,String> param = new HashMap<>();
		param.put("auditStatusId",auditStatusId);
		param.put("doctorName",appointDoctorName);
		param.put("clinicalFlow",clinicalFlow);
		List<OscaCheckInfoExt> appointList = baseBiz.queryAppointList(param);
		String[] titles = new String[]{
				"ticketNumber:准考证编号",
				"doctorName:姓名",
				"sysUser.IdNo:证件号码",
				"sysUser.sexName:性别",
				"doctor.sessionNumber:培训届别",
				"doctor.orgName:培训基地",
				"skillAssess.speName:考核专业",
				"sysUser.userPhone:联系方式",
				"appointTime:学员预约时间",
				"auditStatusName:状态"
		};
		String fileName = "预约学员名单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, appointList, response.getOutputStream());
	}

	/**
	 * 签到管理列表查询
	 */
	@RequestMapping("/signManage")
	public String signManage(Integer currentPage2, String siginStatusId, String signDoctorName, String clinicalFlow, String clinicalName,
                             String speName, HttpServletRequest request, Model model, String recrodFlow) throws IOException{
		//查询该考核的所有考核时间
		List<OscaSkillsAssessmentTime> timeList = baseBiz.queryOsaTimeList(clinicalFlow);
		model.addAttribute("timeList",timeList);
		Map<String,String> param = new HashMap<>();
		param.put("auditStatusId", AuditStatusEnum.Passed.getId());//预约审核通过
		param.put("siginStatusId",siginStatusId);
		param.put("doctorName",signDoctorName);
		param.put("clinicalFlow",clinicalFlow);
		if(timeList!=null&&timeList.size()>0){
			for(OscaSkillsAssessmentTime time:timeList){
				String flow = time.getRecrodFlow();
				if(flow.equals(recrodFlow)){
					param.put("examStartTime",time.getExamStartTime());
					param.put("examEndTime",time.getExamEndTime());
				}
			}
		}
		param.put("recrodFlow",recrodFlow);
		clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
		speName = java.net.URLDecoder.decode(speName,"UTF-8");
		PageHelper.startPage(currentPage2,getPageSize(request));
		List<OscaCheckInfoExt> signList = baseBiz.queryAppointList(param);
		model.addAttribute("currentPage2",currentPage2);
		model.addAttribute("signList",signList);
		model.addAttribute("clinicalName",clinicalName);
		model.addAttribute("speName",speName);
		return "osca/base/signManage";
	}

	/**
	 * 考核信息发布操作
	 */
	@RequestMapping("/changeSign")
	@ResponseBody
	public String changeSign(String recordFlow,String signStatusId){
		int num = baseBiz.changeSign(recordFlow,signStatusId);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}

	/**
	 * 签到学员导出操作
	 */
	@RequestMapping(value="/expSign", method={RequestMethod.POST,RequestMethod.GET})
	public void expSign(String siginStatusId,String signDoctorName,String clinicalFlow,HttpServletResponse response,String recrodFlow) throws Exception {
		Map<String,String> param = new HashMap<>();
		List<OscaSkillsAssessmentTime> timeList = baseBiz.queryOsaTimeList(clinicalFlow);
		if(timeList!=null&&timeList.size()>0){
			for(OscaSkillsAssessmentTime time:timeList){
				String flow = time.getRecrodFlow();
				if(flow.equals(recrodFlow)){
					param.put("examStartTime",time.getExamStartTime());
					param.put("examEndTime",time.getExamEndTime());
				}
			}
		}
		param.put("auditStatusId", AuditStatusEnum.Passed.getId());//预约审核通过
		param.put("siginStatusId",siginStatusId);
		param.put("doctorName",signDoctorName);
		param.put("clinicalFlow",clinicalFlow);
		List<OscaCheckInfoExt> signtList = baseBiz.queryAppointList(param);
		String[] titles = new String[]{
				"doctorName:姓名",
				"sysUser.IdNo:证件号码",
				"ticketNumber:准考证号",
				"sysUser.sexName:性别",
				"doctor.sessionNumber:培训届别",
				"doctor.orgName:培训基地",
				"skillAssess.speName:考核专业",
				"sysUser.userPhone:联系方式",
				"siginTime:签到时间",
				"siginStatusName:状态"
		};
		String fileName = "签到学员名单.xls";
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		ExcleUtile.exportSimpleExcleByObjs(titles, signtList, response.getOutputStream());
	}


	/**
	 * 考场安排查询
	 */
	@RequestMapping("/roomManage")
	public String roomManage(String clinicalFlow,String clinicalName,String speName,Model model) throws IOException{
		clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
		speName = java.net.URLDecoder.decode(speName,"UTF-8");
		Map<String,Map<String,Object>> stationMap = new LinkedHashMap<>();
		List<Map<String,Object>> roomList = baseBiz.queryRoomList(clinicalFlow);
		if(null!=roomList && roomList.size()>0){
			for(Map<String,Object> room : roomList){
				String key = (String)room.get("STATION_FLOW");
				if(!stationMap.containsKey(key)){
					stationMap.put(key,room);
				}
			}
			model.addAttribute("stationMap",stationMap);
		}
		model.addAttribute("roomList",roomList);
		model.addAttribute("clinicalName",clinicalName);
		model.addAttribute("speName",speName);
		return "osca/base/roomManage";
	}

	/**
	 * 新增/编辑考场界面
	 */
	@RequestMapping(value="/addRoom")
	public String addRoom(String clinicalFlow,String clinicalName,String subjectFlow,String recordFlow,String flag,String stationFlow,Model model) throws IOException{
		clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
		if(StringUtil.isNotBlank(recordFlow)){
			OscaSkillRoom osr = baseBiz.queryRoomByFlow(recordFlow);
			model.addAttribute("osr",osr);
			List<OscaSkillRoomTea> teaList = baseBiz.queryRoomTeaList(recordFlow);
			model.addAttribute("teaList",teaList);
		}
		SysDict sysDict = new SysDict();
		sysDict.setDictTypeId("ExamRoom");
		sysDict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		List<SysDict> examRoomList = dictBiz.searchDictList(sysDict);
		List<OscaSubjectStation> stationList = baseBiz.queryStationList(subjectFlow);
		model.addAttribute("examRoomList",examRoomList);
		model.addAttribute("stationList",stationList);
		model.addAttribute("clinicalFlow",clinicalFlow);
		model.addAttribute("clinicalName",clinicalName);
		if(GlobalConstant.RECORD_STATUS_Y.equals(flag)){
			model.addAttribute("stationFlow",stationFlow);
			return "/osca/base/addRoomNew";
		}
		return "/osca/base/addRoom";
	}
	/**
	 * 考场下设考官界面
	 */
	@RequestMapping(value="/roomTeacher")
	public String roomTeacher(String userName,String stationName,String [] exitTeaLst,Model model)throws IOException{
		stationName = java.net.URLDecoder.decode(stationName,"UTF-8");
		List<SysUser> teaList = baseBiz.queryTeaList(userName);
		model.addAttribute("teaList",teaList);
		model.addAttribute("stationName",stationName);
		model.addAttribute("exitTeaLst",exitTeaLst==null?exitTeaLst:Arrays.asList(exitTeaLst));
		return "/osca/base/roomTeacher";
	}
	/**
	 * 新增考场保存操作
	 */
	@RequestMapping("/saveRoom")
	@ResponseBody
	public String saveRoom(String jsonData, String jsonDataDetail, OscaSkillRoom room, String backRoomFlow)throws IOException{
		jsonData = java.net.URLDecoder.decode(jsonData,"UTF-8");
		Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
		List<String> stationFlowLst = (List<String>)mp.get("stationFlowLst");
		List<String> stationNameLst = (List<String>)mp.get("stationNameLst");
		jsonDataDetail = java.net.URLDecoder.decode(jsonDataDetail,"UTF-8");
		JSONArray jsonArray = JSON.parseArray(jsonDataDetail);
		int num = 0;
		if(null != stationFlowLst && stationFlowLst.size() > 0){
			room.setRoomFlow(backRoomFlow);
			for(int i = 0;i < stationFlowLst.size(); i++){
				room.setStationFlow(stationFlowLst.get(i));
				room.setStationName(stationNameLst.get(i));
				room.setOrdinal(i+1);
				if(null != jsonArray && jsonArray.size() > 0){
					for(int j = 0;j < jsonArray.size();j++){
						Map<String,Object> teaMp = JSON.parseObject(jsonArray.get(j).toString(),Map.class);
						if(((String)teaMp.get("stationFlow")).equals(stationFlowLst.get(i))){
							num += baseBiz.saveRoom(room,teaMp);
							break;
						}
					}
				}else{//新增未设考官的考场
					num += baseBiz.saveRoom(room,null);
					room.setRecordFlow("");//新增多考站（未设考官），期间room流水号并非为空导致下个考站不能新增
				}
			}
		}
		if (num > 0) {
			return GlobalConstant.SAVE_SUCCESSED;
		}else if(num == -1){
			return "该考官已扫描学生二维码但未提交成绩，暂无法调整！";
		}else{
			return GlobalConstant.SAVE_FAIL;
		}
	}
	/**
	 * 考场信息删除操作
	 */
	@RequestMapping("/delRoom")
	@ResponseBody
	public String delRoom(String clinicalFlow,String recordFlow){
		int num = baseBiz.delRoom(recordFlow,clinicalFlow);
		if (StringUtil.isBlank(recordFlow) && num > 0) {
			return "成功清除所有考场";
		}else if(StringUtil.isNotBlank(recordFlow) && num == 1){
			return "成功删除此考场";
		}
		if(num == -1 && StringUtil.isBlank(recordFlow)){
			return "考核已开始，无法清空考场";
		}else if(num == -1 && StringUtil.isNotBlank(recordFlow)){
			return "考核已开始，无法删除考场";
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 考核进度查询
	 */
	@RequestMapping("/scheduleManage")
	public String scheduleManage(Integer currentPage3, String categoryId, String clinicalFlow, String subjectFlow, String clinicalName,
                                 String speName, HttpServletRequest request, Model model, String recrodFlow) throws IOException{
		//查询改考核的所有考核时间
		List<OscaSkillsAssessmentTime> timeList = baseBiz.queryOsaTimeList(clinicalFlow);
		model.addAttribute("timeList",timeList);
		Map<String,String> param = new HashMap<>();
		if(timeList!=null&&timeList.size()>0){
			for(OscaSkillsAssessmentTime time:timeList){
				String flow = time.getRecrodFlow();
				if(flow.equals(recrodFlow)){
					param.put("examStartTime",time.getExamStartTime());
					param.put("examEndTime",time.getExamEndTime());
				}			}
		}
		param.put("clinicalFlow",clinicalFlow);
		param.put("auditStatusId",AuditStatusEnum.Passed.getId());
		param.put("signStatusId", SignStatusEnum.SignIn.getId());
		param.put("categoryId", categoryId);
		clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
		speName = java.net.URLDecoder.decode(speName,"UTF-8");
		PageHelper.startPage(currentPage3,getPageSize(request));
		List<Map<String,Object>> scheduleList = baseBiz.queryScheduleList(param);
		List<OscaSubjectStation> stationList = baseBiz.queryStationList(subjectFlow);
		model.addAttribute("scheduleList",scheduleList);
		model.addAttribute("stationList",stationList);
		model.addAttribute("clinicalName",clinicalName);
		model.addAttribute("speName",speName);
		return "osca/base/scheduleManage";
	}
	/**
	 * 候考管理
	 */
	@RequestMapping("/houkaoManage")
	public String houkaoManage(String clinicalFlow, String subjectFlow, String clinicalName, String speName,
                               HttpServletRequest request, Model model, String recrodFlow) throws IOException{
		//查询改考核的所有考核时间
		List<OscaSkillsAssessmentTime> timeList = baseBiz.queryOsaTimeList(clinicalFlow);
		model.addAttribute("timeList",timeList);
		clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
		speName = java.net.URLDecoder.decode(speName,"UTF-8");
		List<OscaSubjectStation> stationList = baseBiz.queryStationList(subjectFlow);
		if(stationList!=null)
		{
			Map<String,List<OscaSkillRoom>> roomMap=new HashMap<>();
			String orgFlow=GlobalContext.getCurrentUser().getOrgFlow();

            //候考学员
            Map<String,List<OscaSkillDocStation>> waitDocMap=new HashMap<>();

            //max最大长度
            int maxLength=-999;
            //考核中学员
            Map<String,OscaSkillDocStation> assessingDocMap=new HashMap<>();
            Map<String,List<String>> doctorFlowsMap=new HashMap<>();
            int i=0;
			for(OscaSubjectStation station:stationList)
			{
				List<OscaSkillRoom> rooms = baseBiz.findClinicalStationRooms(station.getStationFlow(),clinicalFlow,orgFlow);
				roomMap.put(station.getStationFlow(),rooms);

				List<OscaSkillDocStation> docStations=baseBiz.findStationHouKaoDocs(clinicalFlow,station.getStationFlow());
				List<String> doctorFlows=new ArrayList<>();
                if(docStations!=null)
                {
                    //候考学员
                    List<OscaSkillDocStation> waitDocs=new ArrayList<>();
                    for(OscaSkillDocStation docStation:docStations)
                    {
                        if(ExamStatusEnum.StayAssessment.getId().equals(docStation.getExamStatusId()))
                        {
                        	if(i>0){
								List<String> preDoctorFlows=doctorFlowsMap.get(stationList.get(i-1).getStationFlow());
								if(preDoctorFlows!=null&&preDoctorFlows.contains(docStation.getDoctorFlow()))
								{
									waitDocs.add(docStation);
								}
							}else{
								waitDocs.add(docStation);
							}
                        }
                        if(ExamStatusEnum.AssessIng.getId().equals(docStation.getExamStatusId()))
                        {
                        	if(i>0){
								List<String> preDoctorFlows=doctorFlowsMap.get(stationList.get(i-1).getStationFlow());
								if(preDoctorFlows!=null&&preDoctorFlows.contains(docStation.getDoctorFlow()))
								{
									assessingDocMap.put(docStation.getStationFlow()+docStation.getRoomFlow(),docStation);
								}
							}else{
								assessingDocMap.put(docStation.getStationFlow()+docStation.getRoomFlow(),docStation);
							}
                        }
                        if(ExamStatusEnum.Assessment.getId().equals(docStation.getExamStatusId()))
                        {
							doctorFlows.add(docStation.getDoctorFlow());
                        }
                    }
                    if(waitDocs.size()>maxLength)
                    {
                        maxLength=waitDocs.size();
                    }
                    waitDocMap.put(station.getStationFlow(),waitDocs);
                }
				doctorFlowsMap.put(station.getStationFlow(),doctorFlows);
                i++;
			}

			model.addAttribute("maxLength",maxLength);
			model.addAttribute("assessingDocMap",assessingDocMap);
			model.addAttribute("waitDocMap",waitDocMap);
			model.addAttribute("roomMap",roomMap);
		}
		model.addAttribute("stationList",stationList);
		model.addAttribute("clinicalName",clinicalName);
		model.addAttribute("speName",speName);
		return "osca/base/houkaoManage";
	}
	@RequestMapping("/skipDoc")
    @ResponseBody
	public String skipDoc(String recordFlow) throws IOException{
        OscaSkillDocStation docStation=baseBiz.getDocStation(recordFlow);
        if(docStation==null)
        {
            return "请选择需要跳过的学员！";
        }
        String date=DateUtil.getCurrDateTime2();
        docStation.setHoukaoTime(date);
        docStation.setExamStatusId(ExamStatusEnum.StayAssessment.getId());
        docStation.setExamStatusName(ExamStatusEnum.StayAssessment.getName());
        docStation.setRoomFlow("");
        docStation.setRoomName("");
        int c=baseBiz.saveDocStation(docStation);
        if(c==0)
        {
            return GlobalConstant.OPRE_FAIL;
        }
		return GlobalConstant.OPRE_SUCCESSED;
	}
	@RequestMapping("/nextDoc")
    @ResponseBody
	public String nextDoc(String recordFlow,String roomFlow,String roomName) throws IOException{
        OscaSkillDocStation docStation=baseBiz.getDocStation(recordFlow);
        if(docStation==null)
        {
            return "请选择需要跳过的学员！";
        }
        if(ExamStatusEnum.Assessment.getId().equals(docStation.getExamStatusId()))
		{

			return "此学员已考核，请刷新页面！";
		}
		docStation.setExamStatusId(ExamStatusEnum.AssessIng.getId());
		docStation.setExamStatusName(ExamStatusEnum.AssessIng.getName());
		docStation.setRoomFlow(roomFlow);
		docStation.setRoomName(roomName);
		int c=baseBiz.saveDocStation(docStation);
        if(c==0)
        {
            return GlobalConstant.OPRE_FAIL;
        }
		return GlobalConstant.OPRE_SUCCESSED;
	}
	@RequestMapping("/nextDocYuYin")
    @ResponseBody
	public Map<String,String> nextDocYuYin(String clinicalFlow,String stationFlow,String recordFlow,String roomFlow,String roomName) throws IOException{
		Map<String,String> map=new HashMap();
		map.put("code","1");
		map.put("msg",GlobalConstant.OPRE_SUCCESSED);
		OscaSubjectStation station=baseBiz.readStation(stationFlow);
		OscaSkillDocStation assessDoc=baseBiz.getStationAssessDoc(clinicalFlow,stationFlow,roomFlow);
		if(assessDoc!=null)
		{
			map.put("code","0");
			map.put("msg","此房间学员【"+assessDoc.getDoctorName()+"】正在考核");
			return map;
		}
        OscaSkillDocStation docStation=baseBiz.getDocStation(recordFlow);
        if(docStation==null)
        {
			map.put("code","0");
			map.put("msg","请选择呼叫的学员！");
			return map;
        }
		try {
			map= TtsUtil.run("请"+docStation.getDoctorName()+"到"+station.getStationName()+"，"+roomName+"参加考试！");
		} catch (DemoException e) {
			map.put("code","0");
			map.put("msg",e.getMessage());
		}
		return map;
	}
	/**
	 * 成绩管理查询
	 */
	@RequestMapping("/gradeManage")
	public String gradeManage(Integer currentPage4, String ticketNumber, String gradeDoctorName, String trainCategoryId,
                              String resultId, String order, String clinicalFlow, String subjectFlow, String clinicalName,
                              String speName, HttpServletRequest request, Model model, String recrodFlow) throws IOException{
		//查询改考核的所有考核时间
		List<OscaSkillsAssessmentTime> timeList = baseBiz.queryOsaTimeList(clinicalFlow);
		model.addAttribute("timeList",timeList);
		Map<String,String> param = new HashMap<>();
		if(timeList!=null&&timeList.size()>0){
			for(OscaSkillsAssessmentTime time:timeList){
				String flow = time.getRecrodFlow();
				if(flow.equals(recrodFlow)){
					param.put("examStartTime",time.getExamStartTime());
					param.put("examEndTime",time.getExamEndTime());
				}
			}
		}
		param.put("clinicalFlow",clinicalFlow);
		param.put("auditStatusId",AuditStatusEnum.Passed.getId());
		param.put("signStatusId", SignStatusEnum.SignIn.getId());
		param.put("ticketNumber", ticketNumber);
		param.put("gradeDoctorName", gradeDoctorName);
		param.put("trainCategoryId", trainCategoryId);
		param.put("resultId", resultId);
		param.put("order", order);
		clinicalName = java.net.URLDecoder.decode(clinicalName,"UTF-8");
		speName = java.net.URLDecoder.decode(speName,"UTF-8");
		PageHelper.startPage(currentPage4,getPageSize(request));
		List<Map<String,Object>> gradeList = baseBiz.queryGradeList(param);
		List<OscaSubjectStation> stationList = baseBiz.queryStationList(subjectFlow);
		List<OscaDoctorAssessment> odaList = baseBiz.queryDoctorList(clinicalFlow);
		OscaSkillsAssessment osa = baseBiz.queryDataByFlow(clinicalFlow);
		int passedCount = 0;
		if(null != odaList && odaList.size() > 0){
			for(OscaDoctorAssessment oda : odaList){
				if(DoctorScoreEnum.Passed.getId().equals(oda.getIsPass())){
					passedCount++;
				}
			}
		}
		DecimalFormat df = new DecimalFormat("0.00");
		model.addAttribute("percent",(odaList == null || odaList.size() == 0)?"":df.format((double)passedCount * 100 / (double)odaList.size()));
		model.addAttribute("osa",osa);
		model.addAttribute("isGradeReleased",osa==null?"":osa.getIsGradeReleased());
		model.addAttribute("gradeList",gradeList);
		model.addAttribute("stationList",stationList);
		model.addAttribute("clinicalName",clinicalName);
		model.addAttribute("speName",speName);
		return "osca/base/gradeManage";
	}
	/**
	 * 学生能否查看考核成绩操作
	 */
	@RequestMapping("/isShowOpt")
	@ResponseBody
	public String isShowOpt(String clinicalFlow,String isShow,String flag){
		int num = baseBiz.isShowOpt(clinicalFlow,isShow,flag);
		if (num > 0) {
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}
	/**
	 * 学员各站成绩导出操作
	 */
	@RequestMapping(value="/exportGradeExcel", method={RequestMethod.POST,RequestMethod.GET})
	public void exportGradeExcel(String ticketNumber,String gradeDoctorName,String trainCategoryId,String resultId,String order,
								 String clinicalFlow,String subjectFlow,HttpServletResponse response,String recrodFlow) throws Exception {
		Map<String,String> param = new HashMap<>();
		List<OscaSkillsAssessmentTime> timeList = baseBiz.queryOsaTimeList(clinicalFlow);
		if(timeList!=null&&timeList.size()>0){
			for(OscaSkillsAssessmentTime time:timeList){
				String flow = time.getRecrodFlow();
				if(flow.equals(recrodFlow)){
					param.put("examStartTime",time.getExamStartTime());
					param.put("examEndTime",time.getExamEndTime());
				}
			}
		}
		param.put("clinicalFlow",clinicalFlow);
		param.put("auditStatusId",AuditStatusEnum.Passed.getId());
		param.put("signStatusId", SignStatusEnum.SignIn.getId());
		param.put("ticketNumber", ticketNumber);
		param.put("gradeDoctorName", gradeDoctorName);
		param.put("trainCategoryId", trainCategoryId);
		param.put("resultId", resultId);
		param.put("order", order);
		List<Map<String,Object>> gradeList = baseBiz.queryGradeList(param);
		List<OscaSubjectStation> stationList = baseBiz.queryStationList(subjectFlow);
		List<String> titleLst = new ArrayList<>();
		titleLst.add("准考证号");
		titleLst.add("姓名");
		titleLst.add("性别");
		titleLst.add("身份证号");
		titleLst.add("培训基地");
		titleLst.add("所在单位");
		titleLst.add("培训专业");
		titleLst.add("考试阶段");
		if(null != stationList && stationList.size() > 0){
			for(OscaSubjectStation station : stationList){
				titleLst.add(station.getStationName());
			}
		}
		titleLst.add("总分");
		titleLst.add("考试结果");
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
		if (gradeList != null && !gradeList.isEmpty()) {
			for (Map<String, Object> map : gradeList) {
				double scoreSum = 00.00;
				HSSFRow hr = sheet.createRow(rownum);
				HSSFCell cell = hr.createCell(0);
				cell.setCellValue((String) map.get("TICKET_NUMBER"));
				HSSFCell cell1 = hr.createCell(1);
				cell1.setCellValue((String) map.get("DOCTOR_NAME"));
				HSSFCell cell22 = hr.createCell(2);
				cell22.setCellValue((String) map.get("SEX_NAME"));
				HSSFCell cell32 = hr.createCell(3);
				cell32.setCellValue((String) map.get("ID_NO"));
				HSSFCell cell2 = hr.createCell(4);
				cell2.setCellValue((String) map.get("ORG_NAME"));
				HSSFCell cell3 = hr.createCell(5);
				cell3.setCellValue((String) map.get("WORK_ORG_NAME"));
				HSSFCell cell4 = hr.createCell(6);
				cell4.setCellValue((String) map.get("SPE_NAME"));
				HSSFCell cell5 = hr.createCell(7);
				cell5.setCellValue((String) map.get("TRAINING_TYPE_NAME"));

				for(int i = 0;i < stationList.size(); i++){
					if(null != map.get("STATION_NAME") && null != map.get("EXAM_SCORE")){
						HSSFCell hc = hr.createCell(8+i);
						String [] scoreArry = ((String) map.get("EXAM_SCORE")).split(",");
						String [] stationArry = ((String) map.get("STATION_FLOW")).split(",");
						for(int j = 0;j < stationArry.length; j++){
							if(stationList.get(i).getStationFlow().equals(stationArry[j])){
								hc.setCellValue("*".equals(scoreArry[j])?"":scoreArry[j]+"");
								if(!"*".equals(scoreArry[j])){
									BigDecimal b = new BigDecimal(Double.toString(Double.valueOf(scoreArry[j])));
									BigDecimal one = new BigDecimal("1");
									scoreSum= scoreSum + b.divide(one,2,BigDecimal.ROUND_HALF_UP).doubleValue();
								}
								break;
							}
						}
					}
				}

				HSSFCell cellSum = hr.createCell(8+stationList.size());
				DecimalFormat df = new DecimalFormat("#.00");
				cellSum.setCellValue(df.format(scoreSum));
				HSSFCell cellLast = hr.createCell(9+stationList.size());
				cellLast.setCellValue((String) map.get("IS_PASS_NAME"));
				rownum++;
			}
		}
		fileName = URLEncoder.encode(fileName, "UTF-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		response.setContentType("application/octet-stream;charset=UTF-8");
		wb.write(response.getOutputStream());
	}

	/**
	 * 成绩管理编辑界面
	 */
	@RequestMapping(value="/editGradeOpt")
	public String editGradeOpt(String clinicalFlow, String subjectFlow, String doctorFlow, Model model, String currentPage){
		Map<String,String> param = new HashMap<>();
		param.put("clinicalFlow",clinicalFlow);
		param.put("subjectFlow",subjectFlow);
		param.put("doctorFlow",doctorFlow);
		SysUser user=userBiz.readSysUser(doctorFlow);
		model.addAttribute("user",user);
		Map<String,Object> dataMap = baseBiz.queryDoctorGrade(param);
		List<OscaSubjectStation> stationList = baseBiz.queryStationList(subjectFlow);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("dataMap",dataMap);
		model.addAttribute("stationList",stationList);
		OscaSkillsAssessment skillAssess=baseBiz.queryDataByFlow(clinicalFlow);
		OscaSubjectMain main = provincialBiz.readSubject(subjectFlow);
		Integer passPer=main.getPassPer();
		if(passPer==null||passPer==0)
		{
			passPer=60;
		}
		model.addAttribute("passPer",passPer);
		model.addAttribute("main",main);
		return "/osca/base/editGradeOpt";
	}
	/**
	 * 编辑考生各站成绩保存操作
	 */
	@RequestMapping("/saveGrade")
	@ResponseBody
	public String saveGrade(String jsonData,String clinicalFlow,String clinicalName,String doctorFlow,String doctorName,String resultId)throws IOException{
		jsonData = java.net.URLDecoder.decode(jsonData,"UTF-8");
		Map<String,Object> mp = JSON.parseObject(jsonData,Map.class);
		List<String> stationFlowLst = (List<String>)mp.get("stationFlowLst");
		List<String> stationNameLst = (List<String>)mp.get("stationNameLst");
		List<String> scoreLst = (List<String>)mp.get("scoreLst");
		List<String> docRoomFlowLst = (List<String>)mp.get("docRoomFlowLst");
		int num = 0;
		if(null != stationFlowLst && stationFlowLst.size() > 0){
			for(int i = 0;i < stationFlowLst.size(); i++){
				OscaSkillRoomDoc roomDoc = new OscaSkillRoomDoc();
				if(!docRoomFlowLst.get(i).equals("docRoomFlowNewFlag")){//修改
					roomDoc.setDocRoomFlow(docRoomFlowLst.get(i));
					roomDoc.setExamScore(scoreLst.get(i));
					roomDoc.setExamSaveScore(scoreLst.get(i));
					roomDoc.setIsAdminAudit(GlobalConstant.RECORD_STATUS_Y);
				}else{
					roomDoc.setDoctorFlow(doctorFlow);
					SysUser user=userBiz.readSysUser(doctorFlow);
					roomDoc.setDoctorName(user.getUserName());
					roomDoc.setRoomRecordFlow("Unknown");
					roomDoc.setRoomFlow("Unknown");
					roomDoc.setRoomName("Unknown");
					roomDoc.setClinicalFlow(clinicalFlow);
					roomDoc.setClinicalName(clinicalName);
					roomDoc.setStationFlow(stationFlowLst.get(i));
					roomDoc.setStationName(stationNameLst.get(i));
					roomDoc.setExamStatusId(ExamStatusEnum.Assessment.getId());
					roomDoc.setExamStatusName(ExamStatusEnum.Assessment.getName());
					roomDoc.setExamScore(scoreLst.get(i));
					roomDoc.setExamSaveScore(scoreLst.get(i));
					roomDoc.setIsAdminAudit(GlobalConstant.RECORD_STATUS_Y);
				}
				num += baseBiz.saveGrade(roomDoc);
			}
		}
		if (num > 0) {
			baseBiz.updateIsPass(clinicalFlow,doctorFlow,resultId);
//			baseBiz.updateDoctorAssessment(clinicalFlow,doctorFlow,GlobalContext.getCurrentUser());
			return GlobalConstant.SAVE_SUCCESSED;
		}else{
			return GlobalConstant.SAVE_FAIL;
		}
	}
	@RequestMapping("/chekIsPass")
	@ResponseBody
	public String chekIsPass(String subjectFlow,String parts,String stationExamScores)throws IOException{

		OscaSubjectMain main = provincialBiz.readSubject(subjectFlow);
		if(main==null)
		{
			return "提交数据格式错误，请重新打开编辑页面";
		}
		if(StringUtil.isBlank(parts)||StringUtil.isBlank(stationExamScores))
		{
			return "提交数据格式错误，请重新打开编辑页面";
		}
		JSONArray stationScoreList = JSON.parseArray(stationExamScores);
		if(stationScoreList!=null&&stationScoreList.size()<=0)
		{
			return "提交数据格式错误，请重新打开编辑页面";
		}
		JSONArray partFlows = JSON.parseArray(parts);
		if(partFlows!=null&&partFlows.size()<=0)
		{
			return "提交数据格式错误，请重新打开编辑页面";
		}
		//总分
		Integer allScore=main.getAllScore();
		//每一部分的分数
		Map<String,Integer> partPassScoreMap=new HashMap<>();
		List<OscaSubjectPartScore> partScores=provincialBiz.getOscaSubjectPartScores(subjectFlow);
		if(partScores!=null)
		{
			for(OscaSubjectPartScore partScore:partScores)
			{
				partPassScoreMap.put(partScore.getPartFlow(),partScore.getPartScore());
			}
		}
		//每一站的合格分
		Map<String,Integer> stationPassScoreMap=new HashMap<>();
		List<OscaSubjectStationScore> stationScores=provincialBiz.getOscaSubjectStationScores(subjectFlow);
		if(stationScores!=null)
		{
			for(OscaSubjectStationScore stationScore:stationScores)
			{
				stationPassScoreMap.put(stationScore.getStationFlow(),stationScore.getStationScore());
			}
		}

		//获取考核方案下的站点
		List<OscaSubjectStation> stations = baseBiz.queryStationList(subjectFlow);
		if (stations != null) {
			//如果学员所有站点都没有考核完，直接是不通过
			if (stationScoreList.size() < stations.size()) {
                return GlobalConstant.FLAG_N;
			}else {
				//每部分的站点数量
				Map<String, List<OscaSubjectStation>> partMap = new HashMap<>();
				for (OscaSubjectStation s : stations) {
					List<OscaSubjectStation> temp = partMap.get(s.getPartFlow());
					if (temp == null)
						temp = new ArrayList<>();
					temp.add(s);
					partMap.put(s.getPartFlow(), temp);
				}
				//考核总分
				double examAllScore = 0;
				//考核分数
				Map<String, Integer> examScoreMap = new HashMap<>();
				for (Object d : stationScoreList) {
					Map<String, Object> m= (Map<String, Object>) d;
					Integer examScore = (Integer) m.get("stationScore");
					if(examScore==null) examScore=0;
					examAllScore += examScore;
					examScoreMap.put((String) m.get("stationFlow"), examScore);
				}
				//如果合格总分配置了 并且 考核 总分小于合格总分 直接不通过
				if(allScore!=null&&allScore>examAllScore)
				{
                    return GlobalConstant.FLAG_N;
				}else {
					//有几部分
					int partCount = partMap.size();
					int partPassCount = 0;
					if (partCount > 0) {
						//计算每一部分的总分，考核总分，以及是否通过
						for (String key : partMap.keySet()) {
							List<OscaSubjectStation> temp = partMap.get(key);
							double examAll = 0;
							for (OscaSubjectStation s : temp) {
								Integer examScore = examScoreMap.get(s.getStationFlow());
								examAll += examScore;
							}
							Integer partPassScore=partPassScoreMap.get(key);
							//如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
							if(partPassScore==null||examAll>=partPassScore)
							{
								partPassCount++;
							}
						}
						//所有部分都合格 则计算每一站是否都合格
						if (partCount == partPassCount) {
							int stationPassCount=0;
							for (OscaSubjectStation s : stations) {
								Integer stationPassScore=stationPassScoreMap.get(s.getStationFlow());
								Integer examScore = examScoreMap.get(s.getStationFlow());
								//如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
								if(stationPassScore==null||examScore>=stationPassScore)
								{
									stationPassCount++;
								}
							}
							if(stationPassCount==stations.size()) {

                                return GlobalConstant.FLAG_Y;
							}else{
                                return GlobalConstant.FLAG_N;
							}
						}else{
                            return GlobalConstant.FLAG_N;
						}
					}
				}

			}
		}
        return GlobalConstant.FLAG_N;
	}

	/**
	 * 学员各站成绩导入
	 */
	@RequestMapping(value="/importGradeExcel")
	@ResponseBody
	public String importGradeExcel(MultipartFile file, String clinicalFlow){
		if(file.getSize() > 0){
			try{
				int result = baseBiz.importGradeExcel(file,clinicalFlow);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	//打开预约学员导入页面
	@RequestMapping(value="/toImportStudents")
	public String toImportStudents(String clinicalFlow,Model model){
		//查询考核时间段
		List<OscaSkillsAssessmentTime> skillsAssessmentTimes = baseBiz.getAssessmentTimes(clinicalFlow);
		model.addAttribute("skillsAssessmentTimes",skillsAssessmentTimes);
		return "/osca/base/importStudent";
	}

	/**
	 * 预约学员导入
	 */
	@RequestMapping(value="/importStudents")
	@ResponseBody
	public String importStudents(MultipartFile file, String clinicalFlow, String time, String startTime, String endTime, String timeFlow){
		String examStartTime = "";
		String examEndTime = "";
		String isNewTime = "";
		if(StringUtil.isNotBlank(time)&&(!"add".equals(time))){
			examStartTime = time.substring(0,16);
			examEndTime = time.substring(17,33);
			isNewTime = timeFlow;
		}else{
			examStartTime = startTime;
			examEndTime = endTime;
            isNewTime = GlobalConstant.FLAG_Y;
		}
		if(file.getSize() > 0){
			try{
				int result = baseBiz.importStudents(file,clinicalFlow,examStartTime,examEndTime,isNewTime);
				if(GlobalConstant.ZERO_LINE != result){
					return GlobalConstant.UPLOAD_SUCCESSED + "导入"+result+"条记录！";
				}else{
					return GlobalConstant.UPLOAD_FAIL;
				}
			}catch(RuntimeException re){
				re.printStackTrace();
				return re.getMessage();
			}
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 学员各站考官评分界面
	 */
	@RequestMapping(value="/searchScoreForm")
	public String searchScoreForm(String clinicalFlow,String doctorFlow,String stationFlow,Model model){
		Map<String,String> param = new HashMap<>();
		param.put("clinicalFlow",clinicalFlow);
		param.put("doctorFlow",doctorFlow);
		param.put("stationFlow",stationFlow);
		List<OscaSkillDocScore> scoreList = baseBiz.queryDocFormScore(param);
		model.addAttribute("scoreList",scoreList);
		return "/osca/base/stationFormScore";
	}
	@RequestMapping(value="/formScoreDetail")
	public String formScoreDetail(String scoreFlow,Model model) throws DocumentException {
		OscaSkillDocScore score = baseBiz.queryDocScoreByFlow(scoreFlow);
		if(StringUtil.isNotBlank(score.getFromContent())){
			Document doc = DocumentHelper.parseText(score.getFromContent());
			if("1".equals(score.getFromTypeId())){//固定表单
				Map<String,Object> map = new HashMap<>();
				Element root = doc.getRootElement();
				List<Element> eleLst = root.elements();
				if(null != eleLst && eleLst.size() > 0){
					for(Element attr : eleLst){
						map.put(attr.getName(),attr.getText());
					}
				}
				model.addAttribute("dataMap",map);
			}else if("0".equals(score.getFromTypeId())){//自定义表单
				List<OscaFromCfgTitleExt> titleFormList = new ArrayList<OscaFromCfgTitleExt>();
				Map<String,Object> dataMap = new HashMap<>();
				List<Element> titleElementList = doc.selectNodes("//title");
				List<Element> scoreItemList = doc.selectNodes("//scoreItem");
				if(null != titleElementList && !titleElementList.isEmpty()) {
					for (Element te : titleElementList) {
						OscaFromCfgTitleExt titleForm = new OscaFromCfgTitleExt();
						titleForm.setId(te.attributeValue("id"));
						titleForm.setName(te.attributeValue("name"));
						titleForm.setTypeName(te.attributeValue("typeName"));
						List<Element> itemElementList = te.elements("item");
						List<OscaFromCfgItemExt> itemFormList = new ArrayList<OscaFromCfgItemExt>();
						if (null != itemElementList && !itemElementList.isEmpty()) {
							OscaFromCfgItemExt itemForm = null;
							for (Element ie : itemElementList) {
								itemForm = new OscaFromCfgItemExt();
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
				if(null != scoreItemList && !scoreItemList.isEmpty()){
					for (Element te : scoreItemList) {
						dataMap.put(te.attributeValue("id"),te.getTextTrim());
					}
				}
				model.addAttribute("dataMap",dataMap);
				model.addAttribute("titleFormList", titleFormList);
			}
		}
		model.addAttribute("score",score);
        model.addAttribute("view", GlobalConstant.FLAG_Y);//标识表单只可查看
		return "/osca/base/formScoreDetail";
	}

	@RequestMapping(value="/roomList" , method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView selectDictList(SysDict sysDict) {
		ModelAndView mav = new ModelAndView("osca/base/roomListDict");
		if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
			sysDict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
			sysDict.setOrgName(GlobalContext.getCurrentUser().getOrgName());
			List<SysDict> dictList = this.dictBiz.searchDictList(sysDict);
			mav.addObject("dictList",dictList);
		}
		return mav;
	}

	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value="dictFlow",required=false) String dictFlow) {
		ModelAndView mav = new ModelAndView("osca/base/editRoomDict");
		if(StringUtil.isNotBlank(dictFlow)){
			SysDict dict = this.dictBiz.readDict(dictFlow);
			mav.addObject("dict" , dict);
		}
		return mav;
	}
	@RequestMapping(value="/save" , method=RequestMethod.POST)
	public @ResponseBody
    String saveDict(SysDict dict , String parentDictFlow) {
		String dictTypeId = "";
		dict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
		dict.setOrgName(GlobalContext.getCurrentUser().getOrgName());
		if(StringUtil.isBlank(parentDictFlow)){
			dictTypeId = dict.getDictTypeId();
			DictTypeEnum dictTypeEnum =  DictTypeEnum.valueOf(dictTypeId);
			dict.setDictTypeName(dictTypeEnum.getName());
		}else{
			SysDict parentDict = this.dictBiz.readDict(parentDictFlow);
			dictTypeId = parentDict.getDictTypeId()+"."+parentDict.getDictId();
			dict.setDictTypeName(parentDict.getDictName());
			dict.setDictTypeId(dictTypeId);
		}

		if(StringUtil.isBlank(dict.getDictFlow())){
			//新增字典时判断该类型字典代码是否存在
			SysDict sysDict=dictBiz.readDict(dictTypeId,dict.getDictId(),dict.getOrgFlow());
			if(null!=sysDict){
				return GlobalConstant.OPRE_FAIL_FLAG;
			}
			this.dictBiz.addDict(dict);
		}else{
			//修改字典时，字典代码可以与自身相同，可以是新ID，但不能与其他字典相同
			List<SysDict> dictList=dictBiz.searchDictListByDictTypeIdNotIncludeSelf(dict);
			for(SysDict sysDict:dictList){
				if(dict.getDictId().equals(sysDict.getDictId())){
					return GlobalConstant.OPRE_FAIL_FLAG;
				}
			}
			this.dictBiz.modeDictAndSubDict(dict);
		}
		return GlobalConstant.SAVE_SUCCESSED;
	}

	/**
	 * 学生能否查看评分表操作
	 */
	@RequestMapping("/isShowFroom")
	@ResponseBody
	public String isShowFroom(String clinicalFlow,String isShowFroom){
		int num = baseBiz.isShowFroom(clinicalFlow,isShowFroom);
		if (num > 0) {
			return GlobalConstant.OPERATE_SUCCESSED;
		}
		return GlobalConstant.OPERATE_FAIL;
	}

	@RequestMapping("/screenDisplay")
	public String screenDisplay(Integer currentPage,Model model){
		Map<String,String> paramMp = new HashMap<>();
		paramMp.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		PageHelper.startPage(currentPage,10);
		List<Map<String,Object>> dataList = baseBiz.queryRoomDoctorList(paramMp);
		model.addAttribute("dataList",dataList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("total",PageHelper.total);
		model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
		return "osca/base/screenContent";
	}

	@RequestMapping("/screenDisplay2/wait")
	public String screenDisplay2(Integer currentPage, Model model, String[] clinicalFlow){//根据选择的考核场次显示
        List<String> clinicalFlows = null;
        if(null!=clinicalFlow && clinicalFlow.length>0){
            clinicalFlows = Arrays.asList(clinicalFlow);
        }
		Map<String,Object> paramMp = new HashMap<>();
		paramMp.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		paramMp.put("clinicalFlows",clinicalFlows);
		PageHelper.startPage(currentPage,10);
		List<Map<String,Object>> dataList = baseBiz.queryRoomDoctorList2(paramMp);
		model.addAttribute("dataList",dataList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("clinicalFlows",clinicalFlows);
		model.addAttribute("total",PageHelper.total);
		model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
		return "osca/base/screenContent";
	}

	@RequestMapping("/screenDisplay2/process")
	public String processDisplay(Integer currentPage, Model model, String[] clinicalFlow){
		List<String> clinicalFlows = Arrays.asList(clinicalFlow);
		int stationNum = 0;
		if(clinicalFlows!=null&&clinicalFlows.size()>0){
			for(String flow:clinicalFlows){
				OscaSkillsAssessment skillsAssessment = baseBiz.queryDataByFlow(flow);
				String subjectFlow = skillsAssessment.getSubjectFlow();
				List<OscaSubjectStation> subjectStationList = baseBiz.queryStationList(subjectFlow);
				if(subjectStationList!=null) {
					int size = subjectStationList.size();
					if (size > stationNum) {
						stationNum = size;
					}
				}
			}
		}
		model.addAttribute("stationNum",stationNum);
		Map<String,Object> paramMap = new HashMap<>();
		List<Integer> stationNumList = new ArrayList<>();
		for(int i=1;i<=stationNum;i++){
			stationNumList.add(i);
		}
		paramMap.put("stationNumList",stationNumList);
		paramMap.put("clinicalFlows",clinicalFlows);
		paramMap.put("auditStatusId",AuditStatusEnum.Passed.getId());
		paramMap.put("signStatusId", SignStatusEnum.SignIn.getId());
		PageHelper.startPage(currentPage,10);
		List<Map<String,Object>> resultMapList = baseBiz.studentsProcess(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("clinicalFlows",clinicalFlows);
		model.addAttribute("total",PageHelper.total);
		model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
		return "osca/base/studentProcessScreen";
	}

	@RequestMapping("/screenSelect")
	public String screenSelect(Model model, String type){
		Map<String,Object> paramMp = new HashMap<>();
		String date = DateUtil.getCurrDate();
		paramMp.put("date",date);
		paramMp.put("orgFlow",GlobalContext.getCurrentUser().getOrgFlow());
		List<Map<String,Object>> dataList = baseBiz.screenSelect(paramMp);
		model.addAttribute("type",type);
		model.addAttribute("dataList",dataList);
		model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
		return "osca/base/screenSelect";
	}



	@RequestMapping("/goScreen")
	public String goScreen(Integer currentPage, Model model, String clinicalFlow){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("clinicalFlow",clinicalFlow);
		PageHelper.startPage(currentPage,10);
		List<Map<String,Object>> resultMapList = baseBiz.screenInfo(paramMap);
		model.addAttribute("resultMapList",resultMapList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("total",PageHelper.total);
		model.addAttribute("orgName",GlobalContext.getCurrentUser().getOrgName());
		model.addAttribute("clinicalFlow",clinicalFlow);
		return "osca/base/screenContent2";
	}



	@RequestMapping(value="/dictList" , method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView dictList(SysDict sysDict) {
		ModelAndView mav = new ModelAndView("osca/base/dictList");
		if(StringUtil.isNotBlank(sysDict.getDictTypeId())){
			int level = DictTypeEnum.valueOf(sysDict.getDictTypeId()).getLevel();
			if(level>1){
				mav = new ModelAndView("osca/base/mullist");
				mav.addObject("level" , level);
			}
			List<SysDict> dictList = this.dictBiz.searchDictList(sysDict);
			//可简写mav.addObject(dictList);
			mav.addObject("dictList",dictList);
		}
		return mav;
	}

	@RequestMapping(value="/editDict",method=RequestMethod.GET)
	public ModelAndView editDict(@RequestParam(value="dictFlow",required=false) String dictFlow) {
		ModelAndView mav = new ModelAndView("osca/base/editDict");
		if(StringUtil.isNotBlank(dictFlow)){
			SysDict dict = this.dictBiz.readDict(dictFlow);
			mav.addObject("dict" , dict);
		}
		return mav;
	}
	@RequestMapping("/examScoreCfg")
	public String examScoreCfg(Model model){
		String orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		OscaExamDifferScore differScore = baseBiz.readDiffierScoreByOrgFlow(orgFlow);
		model.addAttribute("differScore",differScore);
		return "osca/base/examScoreCfg";
	}

	@RequestMapping("/saveExamScoreCfg")
	@ResponseBody
	public String saveExamScoreCfg(OscaExamDifferScore differScore){
		String orgFlow=differScore.getOrgFlow();
		if(StringUtil.isBlank(differScore.getOrgFlow()))
		{

			orgFlow =GlobalContext.getCurrentUser().getOrgFlow();
		}
		SysOrg sysOrg = orgBiz.readSysOrg(orgFlow);
		differScore.setOrgFlow(orgFlow);
		if(sysOrg!=null){
			differScore.setOrgName(sysOrg.getOrgName());
		}
		OscaExamDifferScore old = baseBiz.readDiffierScoreByOrgFlow(orgFlow);
		if(old!=null)
			differScore.setCfgFlow(old.getCfgFlow());
		int result = baseBiz.saveExamDiffierScoreCfg(differScore);
		if(result>GlobalConstant.ZERO_LINE){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 * 准考证下载
	 */
	@RequestMapping(value = "/downTicket")
	public void downTicket(String clinicalFlow,String[] recordFlows, final HttpServletResponse response,HttpServletRequest request) throws Exception {
		if(null != recordFlows && recordFlows.length > 0) {
			String outputFileClass = ResourceLoader.getPath("");
			String directoryName = new File(outputFileClass) + "/ticket/" + clinicalFlow + "/" ;
			List<String> recordFlowList = Arrays.asList(recordFlows);
			for (String recordFlow : recordFlowList) {
				OscaDoctorAssessment oda = oscaDoctorOrderdeBiz.selectDoctorAssessmentByRecordFlow(recordFlow);
				String doctorFlow = oda.getDoctorFlow();
				final String fileName = doctorFlow;
				String outputFile = directoryName + fileName + ".pdf";
				File file = new File(outputFile);
				if (file != null && !file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}
				//root 储存的数据
				final Map<String, Object> root = new HashMap<String, Object>();

				Map<String, String> doctorMap = new HashMap<>();
				doctorMap.put("doctorFlow", doctorFlow);
				doctorMap.put("clinicalFlow", clinicalFlow);
				List<OscaSkillsAssessmentExt> oscaSkillsAssessmentExt = oscaDoctorOrderdeBiz.selectTicketInfo(doctorMap);
				String signUrl = "";
				OscaSkillsAssessmentExt osaExt = new OscaSkillsAssessmentExt();
				if (oscaSkillsAssessmentExt != null && oscaSkillsAssessmentExt.size() > 0) {
					if (oscaSkillsAssessmentExt.get(0) != null && oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment() != null) {
//						signUrl = oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment().getCodeInfo();
						signUrl = qrCode(clinicalFlow, oscaSkillsAssessmentExt.get(0).getOscaDoctorAssessment().getCodeInfo(), doctorFlow);
						osaExt = oscaSkillsAssessmentExt.get(0);
					}
				}
				String baseUrl = InitConfig.getSysCfg("upload_base_url");
				root.put("signUrl", baseUrl + "/" + signUrl);
				root.put("oscaSkillsAssessmentExt", osaExt);
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("clinicalFlow", clinicalFlow);
				List<OscaSubjectStation> subjectStations = oscaDoctorOrderdeBiz.getStations(paramMap);
				List<String> partFlows = new ArrayList<>();
				if (subjectStations != null && subjectStations.size() > 0) {
					for (OscaSubjectStation station : subjectStations) {
						String partFlow = station.getPartFlow();
						if (!partFlows.contains(partFlow)) {
							partFlows.add(partFlow);
						}
					}
				}
				root.put("subjectStations", subjectStations);
				root.put("partFlows", partFlows);
				DecimalFormat df = new DecimalFormat("#0.0%");
				double size = subjectStations.size();
				double w = 1 / size;
				String width = df.format(w);
				root.put("width", width);
				if(StringUtil.isBlank(osaExt.getSysUser().getUserHeadImg())){
					root.put("headImg", baseUrl + "up-pic.jpg");
				}else {
					root.put("headImg", baseUrl + osaExt.getSysUser().getUserHeadImg());
				}
				// 模板数据
				DocumentVo vo = new DocumentVo() {
					@Override
					public String findPrimaryKey() {
						return fileName;
					}

					@Override
					public Map<String, Object> fillDataMap() {
						return root;
					}
				};

				String template = "jsres/doctorTicket.html";
				PdfDocumentGenerator pdfGenerator = new PdfDocumentGenerator();
				// 生成pdf
				pdfGenerator.generate(template, vo, outputFile);
			}

			File directory = new File(directoryName);
			File zipFlie = new File("准考证.zip");
			String zipFolderName = "";
			ZipUtil.makeDirectoryToZip(directory, zipFlie, zipFolderName, 7);
			//4.输出
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(zipFlie));
			byte[] dt = new byte[bis.available()];
			int len = 2048;
			byte[] b = new byte[len];
			response.reset();
			response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(zipFlie.getName().getBytes("gbk"), "ISO8859-1") + "\"");
			response.addHeader("Content-Length", "" + dt.length);
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
			while ((len = bis.read(b)) != -1) {
				outputStream.write(b, 0, len);
			}
			bis.close();
			outputStream.flush();
			outputStream.close();
			if(directory.exists()&&directory.isFile()) {
				directory.delete();
			}
		}

	}

	private String qrCode(String clinicalFlow,String text,String doctorFlow) {
		int width = 140; // 二维码图片的宽
		int height = 140; // 二维码图片的高
		String format = "png"; // 二维码图片的格式
		String realPath = InitConfig.getSysCfg("upload_base_dir");
		File file = new File(realPath + File.separator + "clinicalFlow");
		if (!file.exists()) {
			file.mkdirs();
		}
		// 绝对路径
		String basePath = "clinicalFlow" + File.separator + doctorFlow;
		String path = realPath + File.separator + basePath + "." + format;
		try {
			QRCodeUtil.generateQRCode(text, width, height, format, path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return basePath + "." + format;
	}

}