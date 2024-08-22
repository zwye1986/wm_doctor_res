package com.pinde.sci.ctrl.res;

import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.Docx4jUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IPubWorkLogBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.enums.pub.LogTypeEnum;
import com.pinde.sci.model.mo.PubWorkLog;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author tiger
 *
 */
@Controller
@RequestMapping("/res/log")
public class ResLogController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResLogController.class);
	
	@Autowired
	private IPubWorkLogBiz workLogBiz;
	
	@RequestMapping(value="/list")
	public String list(String logTypeId){
		return "res/log/list";
	}
	
	/**
	 * 加载日志列表
	 * @param logTypeId
	 * @return
	 */
	@RequestMapping(value="/loadLog")
	public String loadLog(String logTypeId, Integer currentPage,HttpServletRequest request, Model model){
		if(LogTypeEnum.Day.getId().equals(logTypeId)){
			return "res/log/dayLogList";
		}else{
			PubWorkLog workLog = new PubWorkLog();
			workLog.setLogTypeId(logTypeId);
			workLog.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			PageHelper.startPage(currentPage, getPageSize(request));
			List<PubWorkLog> workLogList = workLogBiz.searchWorkLogList(workLog, null, null, null);
			model.addAttribute("workLogList", workLogList);
			return "res/log/weekLogList";
		}
	}
	
	/**
	 * 编辑日志
	 * @param logTypeId
	 * @return
	 */
	@RequestMapping(value="/edit")
	public String edit(String logFlow, Model model) {
		String currentDate = DateUtil.getCurrDate();
		int curWeek = DateUtil.weekOfYear(currentDate);
		String startDate = DateUtil.getFirstDateOfWeek(currentDate);
		String endDate = DateUtil.getLastDateOfWeek(currentDate);
		model.addAttribute("curWeek",curWeek);
		model.addAttribute("startDate",startDate);
		model.addAttribute("endDate",endDate);
		if(StringUtil.isNotBlank(logFlow)){
			PubWorkLog workLog = workLogBiz.readWorkLog(logFlow);
			model.addAttribute("workLog", workLog);
		}
		return "res/log/edit";
	}
	
	/**
	 * 保存日志
	 * @param workLog
	 * @return
	 */
	@RequestMapping(value="/saveLog")
	@ResponseBody
	public String saveLog(PubWorkLog workLog){
		if(StringUtil.isNotBlank(workLog.getLogContent())){//去除富文本自带的<p></p>
			workLog.setLogContent(workLog.getLogContent().substring(3,workLog.getLogContent().length()-4));
		}
		int result = workLogBiz.saveWorkLog(workLog);
		if(GlobalConstant.ZERO_LINE != result){
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	
	/**
	 * 获取日志JSON数据
	 * @param workLog
	 * @param startDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getWorkLogListJsonData")
	@ResponseBody
	public Object getWorkLogListJsonData(PubWorkLog workLog, String startDate, String endDate, Model model){
		if (workLog!=null) {
			workLog.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		}
		List<PubWorkLog> workLogList = workLogBiz.searchWorkLogList(workLog, startDate, endDate, null);
		return workLogList;
	}
	
	
	/**
	 * 获取一条日志记录
	 * @param workLog
	 * @return
	 */
	@RequestMapping(value="/getLog")
	public String getLog(String logFlow, Model model){
		if(StringUtil.isNotBlank(logFlow)){
			PubWorkLog workLog = workLogBiz.readWorkLog(logFlow);
			model.addAttribute("workLog", workLog);
		}
		return "res/log/view";
	}
	
	/**
	 * 删除日志
	 * @param workLog
	 * @return
	 */
	@RequestMapping(value="/delLog")
	@ResponseBody
	public String delLog(String logFlow, Model model){
		if(StringUtil.isNotBlank(logFlow)){
			PubWorkLog workLog = new PubWorkLog();
			workLog.setLogFlow(logFlow);
			workLog.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
			int result = workLogBiz.saveWorkLog(workLog);
			if(GlobalConstant.ZERO_LINE != result){
				return GlobalConstant.DELETE_SUCCESSED;
			}
		}
		return GlobalConstant.DELETE_FAIL;
	}
	//导出证明
	@RequestMapping("/exportWord")
	public void exportWord(String logTypeId, String startDate, String endDate, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String name = "培训日志.docx";
		String titleName = "日志";
		String path = "/jsp/res/log/logTemp.docx";
		if(LogTypeEnum.Week.getId().equals(logTypeId)){
			titleName = "周记";
		}else if(LogTypeEnum.Month.getId().equals(logTypeId)){
			titleName = "月记";
		}
		PubWorkLog workLog = new PubWorkLog();
		workLog.setLogTypeId(logTypeId);
		workLog.setUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		List<PubWorkLog> workLogList = workLogBiz.searchWorkLogList(workLog, startDate, endDate, GlobalConstant.FLAG_Y);
		List<ItemGroupData> groupDataList = new ArrayList<ItemGroupData>();
		for(PubWorkLog log : workLogList){
			Map<String,Object> objMap = new HashMap<>();
			String logContent = "日期："+log.getFillDate()+"\n"+log.getLogContent();
			if(LogTypeEnum.Week.getId().equals(logTypeId)){
				logContent = "第"+log.getWeekNum()+"周  日期："+log.getFillDate()+"~"+log.getEndDate()+"\n"+log.getLogContent();
			}else if(LogTypeEnum.Month.getId().equals(logTypeId)){
				logContent = log.getFillDate()+"  填写日期："+DateUtil.transDate(log.getCreateTime())+"\n"+log.getLogContent();
			}
			objMap.put("logContent",logContent);
			ItemGroupData igd = new ItemGroupData();
			igd.setObjMap(objMap);
			groupDataList.add(igd);
		}
		Map<String,Object> dataMap = new HashMap();
		dataMap.put("groupDataList",groupDataList);
		dataMap.put("titleName",titleName);
		ServletContext context = request.getServletContext();
		WordprocessingMLPackage temeplete = Docx4jUtil.convert2(new File(context.getRealPath(path)), dataMap, null, true);
		if (temeplete != null) {
			response.setHeader("Content-disposition", "attachment; filename=" + new String(name.getBytes("gbk"), "ISO8859-1") + "");
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
			ServletOutputStream out = response.getOutputStream();
			(new SaveToZipFile(temeplete)).save(out);
			out.flush();
		}
	}
}
