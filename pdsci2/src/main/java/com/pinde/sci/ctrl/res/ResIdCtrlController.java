package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResIdCtrlBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/idCtrl")
public class ResIdCtrlController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResIdCtrlController.class);
	
	@Autowired
	private IResIdCtrlBiz idCtrlBiz;
	@Autowired
	private IOrgBiz sysOrgBiz;
	@Autowired
	private IUserBiz userBiz;
	
	//分配记录列表
	@RequestMapping("/mainList")
	public String mainList(ResIdctrlMain resIdctrlMain,Model model,Integer currentPage,HttpServletRequest request){
		PageHelper.startPage(currentPage,getPageSize(request));
		List<ResIdctrlMain> idctrlMainList = idCtrlBiz.searchMain(resIdctrlMain);
		model.addAttribute("idctrlMainList",idctrlMainList);
		return "res/idCtrl/mainList";
	}

	//打开新增ID页面
	@RequestMapping("/addId")
	public String addId(Model model){
		SysOrg searchOrg = new SysOrg();
		searchOrg.setOrgTypeId("Hospital");
		List<SysOrg> orgList = sysOrgBiz.queryAllSysOrg(searchOrg);
		model.addAttribute("orgList",orgList);
		return "res/idCtrl/addId";
	}

	//保存新增ID
	@RequestMapping("/saveIds")
	@ResponseBody
	public int saveIds(ResIdctrlMain resIdctrlMain){
		return idCtrlBiz.saveIds(resIdctrlMain);
	}

	//打开分配的ID详情页面
	@RequestMapping("/details")
	public String details(String recordFlow,String currentPage,Model model){
		ResIdctrlDetail searchDetail = new ResIdctrlDetail();
		searchDetail.setIdctrlMainFlow(recordFlow);
		List<ResIdctrlDetail> idctrlDetails = idCtrlBiz.searchDetail(searchDetail);
		model.addAttribute("idctrlDetails",idctrlDetails);
		return "res/idCtrl/details";
	}

	//删除ID
	@RequestMapping("/delId")
	@ResponseBody
	public int delId(ResIdctrlDetail resIdctrlDetail){
		resIdctrlDetail.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
		return idCtrlBiz.editId(resIdctrlDetail);
	}

	//学员_ID列表
	@RequestMapping("/doctorIdList")
	public String doctorIdList(ResDoctor doctor,String nearOut,Model model,Integer currentPage,HttpServletRequest request){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();

		String currentDate = DateUtil.getCurrDate();
		String currentYear = currentDate.split("-")[0];
		String currentMonth = currentDate.split("-")[1];

		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("doctorName",doctor.getDoctorName());
		paramMap.put("sessionNumber",doctor.getSessionNumber());
		paramMap.put("trainingSpeId",doctor.getTrainingSpeId());
		paramMap.put("trainingYears",doctor.getTrainingYears());
		paramMap.put("graduationYear",doctor.getGraduationYear());
		paramMap.put("nearOut",nearOut);
		paramMap.put("currentYear",currentYear);
		paramMap.put("currentMonth",currentMonth);
		paramMap.put("orgFlow",orgFlow);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = idCtrlBiz.doctorIdList(paramMap);
		model.addAttribute("resultMapList",resultMapList);

		Map<String,Object> bindFlagMap = new HashMap<>();//判断是否可以绑定
		if(resultMapList!=null&&resultMapList.size()>0){
			for(Map<String,Object> map:resultMapList){
				String userFlow = (String)map.get("USER_FLOW");
				if(StringUtil.isNotBlank((String)map.get("END_DATE"))){
					String year = ((String)map.get("END_DATE")).split("-")[0];
					if(currentYear.equals(year)&&currentMonth.equals("08")){
                        bindFlagMap.put(userFlow, GlobalConstant.FLAG_Y);
					}
				}else {
                    bindFlagMap.put(userFlow, GlobalConstant.FLAG_Y);
				}
			}
		}
		model.addAttribute("bindFlagMap",bindFlagMap);
		return "res/idCtrl/doctorIdList";
	}

	//管理员ID列表
	@RequestMapping("/myIdList")
	public String myIdList(Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		List<ResIdctrlDetail> idctrlDetails=null;
		if(StringUtil.isNotBlank(orgFlow)){
			ResIdctrlDetail search = new ResIdctrlDetail();
			search.setOrgFlow(orgFlow);
			idctrlDetails = idCtrlBiz.searchDetail(search);
			model.addAttribute("idctrlDetails",idctrlDetails);
		}
		return "res/idCtrl/myIdList";
	}

	//绑定ID页面
	@RequestMapping("/bindId")
	public String bindId(String doctorFlow,String endDate,Model model){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		List<ResIdctrlDetail> idctrlDetails=null;
		if(StringUtil.isNotBlank(orgFlow)){
			ResIdctrlDetail search = new ResIdctrlDetail();
			search.setOrgFlow(orgFlow);
			search.setIsBinding("NoBind");
			idctrlDetails = idCtrlBiz.searchDetail(search);
			model.addAttribute("idctrlDetails",idctrlDetails);
		}
		model.addAttribute("doctorFlow",doctorFlow);
		model.addAttribute("endDate",endDate);
		return "res/idCtrl/bindingIdList";
	}

	//批量绑定页面
	@RequestMapping("/bindingIds")
	public String bindingIds(Model model,String num){
		SysUser currentUser = GlobalContext.getCurrentUser();
		String orgFlow = currentUser.getOrgFlow();
		List<ResIdctrlDetail> idctrlDetails=null;
		if(StringUtil.isNotBlank(orgFlow)){
			ResIdctrlDetail search = new ResIdctrlDetail();
			search.setOrgFlow(orgFlow);
			search.setIsBinding("NoBind");
			idctrlDetails = idCtrlBiz.searchDetail(search);
			model.addAttribute("idctrlDetails",idctrlDetails);
		}
		return "res/idCtrl/bindingIdsList";
	}

	//绑定ID
	@RequestMapping("/saveBindingId")
	@ResponseBody
	public int saveBindingId(ResIdctrlDetail detail){
		SysUser user = userBiz.readSysUser(detail.getDoctorFlow());
		detail.setDoctorName(user.getUserName());
		SysUser currentUser = GlobalContext.getCurrentUser();
		detail.setOperUserFlow(currentUser.getUserFlow());
		detail.setOperUserName(currentUser.getUserName());
		return idCtrlBiz.bindingID(detail);
	}

	//批量绑定ID
	@RequestMapping("/saveBindingIds")
	@ResponseBody
	public int saveBindingIds(String data){
		SysUser currentUser = GlobalContext.getCurrentUser();
		List<Map<String,Object>> mapList = JSON.parseObject(data, ArrayList.class);
		for(Map<String,Object> map:mapList){
			String userFlow = (String)map.get("userFlow");
			String endDate = (String)map.get("endDate");
			String recordFlow = (String)map.get("recordFlow");
			SysUser user = userBiz.readSysUser(userFlow);

			ResIdctrlDetail detail = new ResIdctrlDetail();
			detail.setDoctorFlow(userFlow);
			detail.setEndDate(endDate);
			detail.setRecordFlow(recordFlow);
			detail.setDoctorName(user.getUserName());
			detail.setOperUserFlow(currentUser.getUserFlow());
			detail.setOperUserName(currentUser.getUserName());

			int result = idCtrlBiz.bindingID(detail);
			if(result==-1||result==-2){
				return result;
			}
		}
		return 1;
	}
}
