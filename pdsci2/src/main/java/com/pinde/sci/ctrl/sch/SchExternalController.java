package com.pinde.sci.ctrl.sch;

import com.pinde.core.util.StringUtil;
import com.pinde.core.util.TimeUtil;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SchDeptRel;
import com.pinde.sci.model.mo.SchExternalDept;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sch/external")
public class SchExternalController extends GeneralController{
	
	private static Logger logger = LoggerFactory.getLogger(SchExternalController.class);
	
	@Autowired
	private IDeptBiz deptBiz;
	@Autowired
	private ISchDeptBiz schDeptBiz;
	@Autowired
	private ISchDeptRelBiz deptRelBiz;
	@Autowired
	private ISchDeptExternalRelBiz deptExtRelBiz;
	@Autowired
	private IOrgBiz orgBiz;
	@Autowired
	private IResJointOrgBiz jointOrgBiz;
	@Autowired
	private ISchRotationDeptBiz schRotationDeptBiz;
	@Autowired
	private ISchExternalDeptBiz schExternalDeptBiz;

	/**
	 * 设置对外开放时间
	 * @param schDeptFlow
	 * @param orgFlow
	 * @param model
	 * @return
     * @throws Exception
     */
	@RequestMapping(value = {"/setExternalDept" }, method = RequestMethod.GET)
	public String setExternalDept (String schDeptFlow,String orgFlow,Model model) throws Exception{
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		model.addAttribute("orgFlow",orgFlow);
		
		if(StringUtil.isNotBlank(schDeptFlow)){
			SchDept dept = schDeptBiz.readSchDept(schDeptFlow);
			model.addAttribute("schDept",dept);
			List<SchDeptRel> deptRelList = deptRelBiz.searchRelBySchDept(schDeptFlow);
			model.addAttribute("standardDepts",deptRelList);
		}
		
		return "sch/external/setExternalDept";
	}

	@RequestMapping(value = {"/getExternalTimes" }, method = RequestMethod.GET)
	public String getExternalTimes(String orgFlow,String schDeptFlow,Model model) throws Exception{
		orgFlow = StringUtil.defaultIfEmpty(orgFlow,GlobalContext.getCurrentUser().getOrgFlow());
		List<SchExternalDept> depts=schExternalDeptBiz.getSchDeptExtDepts(orgFlow,schDeptFlow);
		model.addAttribute("extDepts",depts);
		return "sch/external/getExternalTimes";
	}
	@RequestMapping(value = {"/changePeopleNum" }, method = RequestMethod.GET)
	public String changePeopleNum(String recordFlow,Model model) throws Exception{
		SchExternalDept schExternalDept=schExternalDeptBiz.readByFlow(recordFlow);
		model.addAttribute("schExternalDept",schExternalDept);

		SchDept schDept=schDeptBiz.readSchDept(schExternalDept.getSchDeptFlow());
		model.addAttribute("schDept",schDept);
		return "sch/external/changePeopleNum";
	}
	@RequestMapping(value = {"/updateExtDept" })
	@ResponseBody
	public String updateExtDept(SchExternalDept schExternalDept,Model model) throws Exception{
		SchExternalDept old=schExternalDeptBiz.readByFlow(schExternalDept.getRecordFlow());
		if(old.getPeopleNum().compareTo(schExternalDept.getPeopleNum())<0)
		{
			int count=schExternalDeptBiz.save(schExternalDept);
			if(count==0)
			{
                return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
			}
		}else if(old.getPeopleNum().compareTo(schExternalDept.getPeopleNum())>0)
		{
             int peopleNum=Integer.valueOf(schExternalDept.getPeopleNum());
			Map<String,Object>time=new HashMap<>();
			time.put("startDate",old.getStartDate());
			time.put("endDate",old.getEndDate());
			time.put("schDeptFlow",old.getSchDeptFlow());
			int result=schExternalDeptBiz.isHaveSelect(time);
			if(result<peopleNum)
			{
				int count=schExternalDeptBiz.save(schExternalDept);
				if(count==0)
				{
                    return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
				}
			}else{
				return "已有"+result+"人安排本科室，无法修改";
			}

		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}

	@RequestMapping(value = {"/saveExtDept" })
	@ResponseBody
	public  String saveExtDept(SchExternalDept schExternalDept,Model model) throws Exception{
		//校验开放时间是否有重复
		int result=schExternalDeptBiz.checkTime(schExternalDept);
		if(result>0)
		{
			return "该科室已有开放时间，无法添加！";
		}
		SchDept schDept=schDeptBiz.readSchDept(schExternalDept.getSchDeptFlow());
		schExternalDept.setOrgName(schDept.getOrgName());
		schExternalDept.setOrgFlow(schDept.getOrgFlow());
		schExternalDept.setDeptFlow(schDept.getDeptFlow());
		schExternalDept.setDeptName(schDept.getDeptName());
		schExternalDept.setSchDeptFlow(schDept.getSchDeptFlow());
		schExternalDept.setSchDeptName(schDept.getSchDeptName());
		if(StringUtil.isNotBlank(schExternalDept.getStandardDeptId()))
		{
            schExternalDept.setStandardDeptName(com.pinde.core.common.enums.DictTypeEnum.StandardDept.getDictNameById(schExternalDept.getStandardDeptId()));
		}
		//时间段按自然月拆分
		Map<String,String>time=new HashMap<>();
		time.put("startDate",schExternalDept.getStartDate());
		time.put("endDate",schExternalDept.getEndDate());
		if(schExternalDept.getStartDate().compareTo(schExternalDept.getEndDate())>0)
		{
			return "开始时间大于结束时间，请重新输入！";
		}
		int months= TimeUtil.getMonths(time);
		if(months<=0)
		{
			return "开始时间与结束时间的月份差，需要为自然月的整数倍！";
		}
		List<Map<String,String>> moreTimes=TimeUtil.getTimes(time);
		int count=schExternalDeptBiz.saveTimes(moreTimes,schExternalDept);
		if(moreTimes!=null&&moreTimes.size()>0&&count==moreTimes.size())
		{
            return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.SAVE_FAIL;
	}

	@RequestMapping(value = {"/delExternalDept" })
	@ResponseBody
	public String delExternalDept(String []recordFlow,Model model) throws Exception{
		if(recordFlow.length==0)
		{
			return "请选择需要删除的数据!";
		}
		for(String flow:recordFlow)
		{
			SchExternalDept schExternalDept=schExternalDeptBiz.readByFlow(flow);
			Map<String,Object>time=new HashMap<>();
			time.put("startDate",schExternalDept.getStartDate());
			time.put("endDate",schExternalDept.getEndDate());
			time.put("schDeptFlow",schExternalDept.getSchDeptFlow());
			int result=schExternalDeptBiz.isHaveSelect(time);
			if(result>0)
			{
				return "轮转科室【"+schExternalDept.getSchDeptName()+"】，标准科室【"+schExternalDept.getStandardDeptName()+"】已有其他医院抢占时间或安排学员，无法删除！";
			}
		}
		int count=schExternalDeptBiz.delExternalDept(recordFlow);
		if(count==recordFlow.length)
		{
            return com.pinde.core.common.GlobalConstant.DELETE_SUCCESSED;
		}
        return com.pinde.core.common.GlobalConstant.DELETE_FAIL;
	}

}

