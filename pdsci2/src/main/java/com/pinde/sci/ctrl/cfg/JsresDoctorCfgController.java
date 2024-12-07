package com.pinde.sci.ctrl.cfg;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sch.ISchManualBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.model.mo.JsresPowerCfg;
import com.pinde.sci.model.mo.ResDoctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/jsres/doctorCfg")
public class JsresDoctorCfgController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(JsresDoctorCfgController.class);
	
	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private ISchManualBiz schManualBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;

	@RequestMapping(value = {"/main" }, method = RequestMethod.GET)
	public String main (Model model) throws Exception{
		return "jsres/cfg/doctorCfg/main";
	}
	@RequestMapping(value = {"/changeTime" }, method = RequestMethod.GET)
	public String changeTime (Model model,String key,String userFlow) throws Exception{
		JsresPowerCfg cfg=jsResPowerCfgBiz.read(key);
		model.addAttribute("cfg",cfg);
		return "jsres/cfg/doctorCfg/changeTime";
	}
	@RequestMapping(value = {"/importTime" }, method = RequestMethod.GET)
	public String importTime (Model model) throws Exception{
		return "jsres/cfg/doctorCfg/importTime";
	}

	/**
	 * 查询
	 * @param model
	 * @param currentPage
	 * @param request
	 * @param orgFlow
	 * @param sessionNumber
	 * @param workOrgId
	 * @param userName
	 * @param idNo
	 * @param datas
     * @return
     * @throws Exception
     */
	@RequestMapping(value = {"/userList" })
	public String userList (Model model,Integer currentPage,HttpServletRequest request,
							String orgFlow,String sessionNumber,String workOrgId,String userName,String idNo,String userCode,String trainingYears,String trainingTypeId
							,String datas[]	,String ifOpen,String[] powerTypeId,String checkStatusId,String startTime,String endTime,String doctorCategoryId) throws Exception{
		List<String> docTypeList=new ArrayList<>();
		if(datas!=null&&datas.length>0) {
			docTypeList.addAll(Arrays.asList(datas));
		}
		Map<String,Object> params=new HashMap<>();
		params.put("orgFlow",orgFlow);
		params.put("sessionNumber",sessionNumber);
		params.put("workOrgId",workOrgId);
		params.put("userName",userName);
		params.put("idNo",idNo);
		params.put("docTypeList",docTypeList);
		params.put("userCode",userCode);//登录名
		params.put("trainingYears",trainingYears);//培养年限
		params.put("checkStatusId",checkStatusId);//审核状态
		params.put("startTime",startTime);//数据审核区间
		params.put("endTime",endTime);//数据审核区间
        params.put("trainingTypeId",trainingTypeId);
		if("all".equals(doctorCategoryId)){
			doctorCategoryId = "";
		}
		params.put("doctorCategoryId",doctorCategoryId);
		if(currentPage==null){
			currentPage=1;
		}
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> list = new ArrayList<>();
		if(StringUtil.isNotBlank(ifOpen)){
			params.put("ifOpen",ifOpen);
			for(int i=0;i<powerTypeId.length;i++){
                params.put(powerTypeId[i], com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
			list=schManualBiz.userListByJsResPower(params);
		}else {
			list=schManualBiz.userList(params);
		}
		Map<String,JsresPowerCfg> cfgMap=new HashMap<>();
		for(Map<String,Object> map:list)
		{
			String userFlow= (String) map.get("userFlow");
			cfgMap.put(userFlow,jsResPowerCfgBiz.read("jsres_doctor_data_time_"+userFlow));
		}
		model.addAttribute("cfgMap",cfgMap);
		model.addAttribute("list",list);
		return "jsres/cfg/doctorCfg/userList";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public String save(String[] userFlows, String recordStatus,String cfg) {
		List<JsresPowerCfg> cfgList = new ArrayList<JsresPowerCfg>();
		for (String userFlow : userFlows) {
			String cfgCode = cfg + userFlow;
			JsresPowerCfg jsresCfg = new JsresPowerCfg();
			jsresCfg.setCfgCode(cfgCode);
			jsresCfg.setCfgValue(recordStatus);
			if("jsres_doctor_app_login_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放学员app登录权限");
			}else if("jsres_doctor_app_menu_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放学员付费菜单");
			}else if("jsres_doctor_exam_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放学员出科考核表");
			}else if("jsres_doctor_manual_".equals(cfg)){
				jsresCfg.setCfgDesc("是否开放学员手册");
			}
            jsresCfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			cfgList.add(jsresCfg);
		}
		int result = jsResPowerCfgBiz.saveList(cfgList);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recordStatus)) {
				List<String> flowList = Arrays.asList(userFlows);
				if(null != flowList && flowList.size()>0){
					for (String userFlow:flowList) {
						ResDoctor doctor = new ResDoctor();
						doctor.setDoctorFlow(userFlow);
						doctor.setCheckStatusId("");
						doctor.setCheckStatusName("");
						JsresPowerCfg jpc = jsResPowerCfgBiz.read("jsres_doctor_app_login_"+userFlow);
                        if (null != jpc && com.pinde.core.common.GlobalConstant.FLAG_N.equals(jpc.getCfgValue())) {
							jpc = jsResPowerCfgBiz.read("jsres_doctor_app_menu_"+userFlow);
						}
                        if (null != jpc && com.pinde.core.common.GlobalConstant.FLAG_N.equals(jpc.getCfgValue())) {
							jpc = jsResPowerCfgBiz.read("jsres_doctor_exam_"+userFlow);
						}
                        if (null != jpc && com.pinde.core.common.GlobalConstant.FLAG_N.equals(jpc.getCfgValue())) {
							jpc = jsResPowerCfgBiz.read("jsres_doctor_graduation_exam_"+userFlow);
						}
                        if (null != jpc && com.pinde.core.common.GlobalConstant.FLAG_N.equals(jpc.getCfgValue())) {
							jpc = jsResPowerCfgBiz.read("jsres_doctor_manual_"+userFlow);
						}
                        if (null != jpc && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jpc.getCfgValue())) {
							doctor.setIsSubmitId("NotSubmit");
							doctor.setIsSubmitName("未提交");
						}else{
							doctor.setIsSubmitId("");
							doctor.setIsSubmitName("");
						}
						resDoctorBiz.updateSubmit(doctor);
					}
                    return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
				}
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
			}
			resDoctorBiz.saveSubmitAll(Arrays.asList(userFlows));
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	/**
	 * @Author shengl
	 * @Description 批量操作付费功能
	 * @Date  2020-12-21
	**/
	@RequestMapping(value = "/saveAll", method = RequestMethod.POST)
	@ResponseBody
	public String saveAll(String[] userFlows, String recordStatus,String cfg) {
		List<JsresPowerCfg> cfgList = new ArrayList<JsresPowerCfg>();
		for (String userFlow : userFlows) {
			String appMenu = "jsres_doctor_app_menu_" + userFlow;
			JsresPowerCfg jsresCfg = new JsresPowerCfg();
			jsresCfg.setCfgCode(appMenu);
			jsresCfg.setCfgValue(recordStatus);
            jsresCfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            jsresCfg.setCfgDesc("是否开放学员付费菜单");
            cfgList.add(jsresCfg);

            String activity = "jsres_doctor_activity_" + userFlow;
            jsresCfg = new JsresPowerCfg();
            jsresCfg.setCfgCode(activity);
            jsresCfg.setCfgValue(recordStatus);
            jsresCfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            jsresCfg.setCfgDesc("是否开放学员教学活动功能");
            cfgList.add(jsresCfg);

            String attendance = "jsres_doctor_attendance_" + userFlow;
            jsresCfg = new JsresPowerCfg();
            jsresCfg.setCfgCode(attendance);
            jsresCfg.setCfgValue(recordStatus);
            jsresCfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            jsresCfg.setCfgDesc("是否开放学员考勤功能");
            cfgList.add(jsresCfg);
		}
		int result = jsResPowerCfgBiz.saveList(cfgList);
        if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
            if (com.pinde.core.common.GlobalConstant.RECORD_STATUS_N.equals(recordStatus)) {
				List<String> flowList = Arrays.asList(userFlows);
				if(null != flowList && flowList.size()>0){
					for (String userFlow:flowList) {
						ResDoctor doctor = new ResDoctor();
						doctor.setDoctorFlow(userFlow);
						doctor.setCheckStatusId("");
						doctor.setCheckStatusName("");
						JsresPowerCfg jpc = jsResPowerCfgBiz.read("jsres_doctor_app_menu_"+userFlow);
                        if (null != jpc && com.pinde.core.common.GlobalConstant.FLAG_N.equals(jpc.getCfgValue())) {
							jpc = jsResPowerCfgBiz.read("jsres_doctor_manual_"+userFlow);
						}
                        if (null != jpc && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(jpc.getCfgValue())) {
							doctor.setIsSubmitId("NotSubmit");
							doctor.setIsSubmitName("未提交");
						}else{
							doctor.setIsSubmitId("");
							doctor.setIsSubmitName("");
						}
						resDoctorBiz.updateSubmit(doctor);
					}
                    return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
				}
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
			}
			resDoctorBiz.saveSubmitAll(Arrays.asList(userFlows));
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/importExcel")
	@ResponseBody
	public String importExcel(MultipartFile file){
		if(file.getSize() > 0){
			try{
				ExcelUtile result = (ExcelUtile) jsResPowerCfgBiz.importTime(file);
				if(null!=result)
				{
					String code= (String) result.get("code");
					int count=(Integer) result.get("count");
					String msg= (String) result.get("msg");
					if("1".equals(code))
					{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL + msg;
					}else{
                        return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + count + "条记录！";
					}
				}else {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 导出学员信息
	 */
	@RequestMapping("/exportDoctor")
	@ResponseBody
	public void exportDoctor(Model model,String orgFlow,String sessionNumber,String workOrgId,String userName,String idNo,String trainingYears,String trainingTypeId
			,String datas[],HttpServletResponse reponse,String ifOpen,String[] powerTypeId,String checkStatusId) throws IOException{

		List<String> docTypeList=new ArrayList<>();
		if(datas!=null&&datas.length>0) {
			docTypeList.addAll(Arrays.asList(datas));
		}
		Map<String,Object> params=new HashMap<>();
		params.put("orgFlow",orgFlow);
		params.put("sessionNumber",sessionNumber);
		params.put("workOrgId",workOrgId);
		params.put("userName",userName);
		params.put("idNo",idNo);
		params.put("docTypeList",docTypeList);
		params.put("trainingTypeId",trainingTypeId);
		params.put("trainingYears",trainingYears);//培养年限
		params.put("checkStatusId",checkStatusId);

		List<Map<String,Object>> list = new ArrayList<>();
		if(StringUtil.isNotBlank(ifOpen)){
			params.put("ifOpen",ifOpen);
			for(int i=0;i<powerTypeId.length;i++){
                params.put(powerTypeId[i], com.pinde.core.common.GlobalConstant.FLAG_Y);
			}
			list=schManualBiz.userListByJsResPower(params);
		}else {
			list=schManualBiz.userList(params);
		}

		jsResPowerCfgBiz.exportInfo(list,docTypeList,reponse);
	}

}

