package com.pinde.sci.ctrl.cfg;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.SpringUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResPowerCfgBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.JsresPowerCfgMapper;
import com.pinde.sci.enums.jsres.CheckStatusEnum;
import com.pinde.sci.model.mo.JsresPowerCfg;
import com.pinde.sci.model.mo.ResDoctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
@RequestMapping("/jsres/powerCfg")
public class JsresPowerCfgController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(JsresPowerCfgController.class);

	@Autowired
	private IJsResPowerCfgBiz jsResPowerCfgBiz;
	@Autowired
	private IResDoctorBiz resDoctorBiz;
	@Autowired
	private IUserBiz userBiz;
	@Autowired
	private IOrgBiz orgBiz;

	public static String jsresPowerCfgMap (String cfgCode){
		JsresPowerCfgMapper jsresPowerCfgMapper = SpringUtil.getBean(JsresPowerCfgMapper.class);
		JsresPowerCfg jsresPowerCfg = jsresPowerCfgMapper.selectByPrimaryKey(cfgCode);
		String value = "";
		if(jsresPowerCfg != null){
			value = jsresPowerCfg.getCfgValue();
		}
		return value;
	}

	@RequestMapping(value="/saveOne",method= RequestMethod.POST)
	@ResponseBody
	public String saveOne(String userFlow,HttpServletRequest request){
		String [] cfgCodes = request.getParameterValues("cfgCode");
		if(cfgCodes!=null){
			List<JsresPowerCfg> cfgList = new ArrayList<JsresPowerCfg>();
			for(String cfgCode : cfgCodes){
				String cfgValue = request.getParameter(cfgCode);
				String cfgDesc = request.getParameter(cfgCode+"_desc");
				JsresPowerCfg cfg = new JsresPowerCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(cfgValue);
				cfg.setCfgDesc(cfgDesc);
				cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
				// 状态
                cfg.setCheckStatusId(CheckStatusEnum.Auditing.getId());
                cfg.setCheckStatusName(CheckStatusEnum.Auditing.getName());
				cfgList.add(cfg);
			}
			int result = jsResPowerCfgBiz.saveList(cfgList);
			if(GlobalConstant.ZERO_LINE != result){
				ResDoctor doctor = new ResDoctor();
				doctor.setDoctorFlow(userFlow);
				doctor.setCheckStatusId("");
				doctor.setCheckStatusName("");
				JsresPowerCfg cfg = jsResPowerCfgBiz.read("jsres_doctor_app_login_"+userFlow);
				if(null != cfg && "N".equals(cfg.getCfgValue())){
					cfg = jsResPowerCfgBiz.read("jsres_doctor_app_menu_"+userFlow);
				}
				if(null != cfg && "N".equals(cfg.getCfgValue())){
					cfg = jsResPowerCfgBiz.read("jsres_doctor_exam_"+userFlow);
				}
				if(null != cfg && "N".equals(cfg.getCfgValue())){
					cfg = jsResPowerCfgBiz.read("jsres_doctor_graduation_exam_"+userFlow);
				}
				if(null != cfg && "N".equals(cfg.getCfgValue())){
					cfg = jsResPowerCfgBiz.read("jsres_doctor_manual_"+userFlow);
				}
				if(null != cfg && "Y".equals(cfg.getCfgValue())){
					doctor.setIsSubmitId("NotSubmit");
					doctor.setIsSubmitName("未提交");
					doctor.setSubmitTime("");
					doctor.setCheckTime("");
				}else{
					doctor.setIsSubmitId("");
					doctor.setIsSubmitName("");
				}
				resDoctorBiz.updateSubmit(doctor);
				return GlobalConstant.SAVE_SUCCESSED;
			}
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "/saveTime", method = RequestMethod.POST)
	@ResponseBody
	public String saveTime(HttpServletRequest request, String key, String powerStartTime, String powerEndTime,String userFlow) {
		JsresPowerCfg cfg = jsResPowerCfgBiz.read(key);
		if (cfg == null) cfg = new JsresPowerCfg();
		cfg.setCfgCode(key);
		cfg.setCfgValue(GlobalConstant.RECORD_STATUS_Y);
		cfg.setCfgDesc("学员数据审核权限期限");
		cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
		cfg.setPowerStartTime(powerStartTime);
		cfg.setPowerEndTime(powerEndTime);
		int result = jsResPowerCfgBiz.save(cfg);
		if (GlobalConstant.ZERO_LINE != result) {
			ResDoctor doctor = new ResDoctor();
			doctor.setDoctorFlow(userFlow);
			doctor.setIsSubmitId("NotSubmit");
			doctor.setIsSubmitName("未提交");
			doctor.setCheckStatusId("");
			doctor.setCheckStatusName("");
			resDoctorBiz.updateSubmit(doctor);
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = "/savePayPower", method = RequestMethod.POST)
	@ResponseBody
	public String savePayPower(String startDate, String endDate,String orgFlow) {
		int result = 0;
		if(StringUtil.isNotBlank(startDate)){
			String key = "jsres_payPower_startDate_"+orgFlow;
			JsresPowerCfg cfg = jsResPowerCfgBiz.read(key);
			if (cfg == null) cfg = new JsresPowerCfg();
			cfg.setCfgCode(key);
			cfg.setCfgValue(startDate);
			cfg.setCfgDesc("付费功能开始时间");
			cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			result += jsResPowerCfgBiz.save(cfg);
		}
		if(StringUtil.isNotBlank(endDate)){
			String key = "jsres_payPower_endDate_"+orgFlow;
			JsresPowerCfg cfg = jsResPowerCfgBiz.read(key);
			if (cfg == null) cfg = new JsresPowerCfg();
			cfg.setCfgCode(key);
			cfg.setCfgValue(endDate);
			cfg.setCfgDesc("付费功能结束时间");
			cfg.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
			result += jsResPowerCfgBiz.save(cfg);
		}
		if(result > 0){
			Map<String,Object> map = new HashMap<>();
			map.put("orgFlow",orgFlow);
			map.put("isSubmitId","N");
			orgBiz.updateOrgSubmit(map);
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value="/updateSubmit",method= RequestMethod.POST)
	@ResponseBody
	public Object updateSubmit(String[] userFlows){
		Map<String,Object> map = new HashMap<>();
		if(null != userFlows && userFlows.length>0){
			List<String> userFlowList = Arrays.asList(userFlows);
			map.put("list",userFlowList);
			map.put("submitTime",DateUtil.getCurrDateTime2());
			int count = resDoctorBiz.updateSubmitAll(map);
			if(count > 0) {
				Map<String,Object> resultMap = new HashMap<>();
				StringBuilder builder = new StringBuilder();
				builder.append("本次提交数据" + userFlowList.size() + "条 \r\n ");
				List<Map<String,Object>> dataList = resDoctorBiz.getCountBySessionNumber(userFlowList);
				if(null != dataList && dataList.size()>0) {
					for (Map<String,Object> m:dataList) {
						builder.append(m.get("sessionNumber") + "级：" + m.get("countNumber")+ "人 \r\n ");
					}
				}
//				return GlobalConstant.OPRE_SUCCESSED;
				return builder;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}

	@RequestMapping(value="/updateTeaSubmit",method= RequestMethod.POST)
	@ResponseBody
	public String updateTeaSubmit(String[] userFlows){
		if(null != userFlows && userFlows.length>0){
			List<String> userFlowList = Arrays.asList(userFlows);
			int count = userBiz.updateTeaSubmit(userFlowList);
			if(count > 0) {
				return GlobalConstant.OPRE_SUCCESSED;
			}
		}
		return GlobalConstant.OPRE_FAIL;
	}
}

