package com.pinde.sci.ctrl.fstu;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.fstu.IAcademicBiz;
import com.pinde.sci.biz.fstu.IFstuFileBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.enums.fstu.AcademicAndScoreEnum;
import com.pinde.sci.form.fstu.FstuAcademicActivityAndFileForm;
import com.pinde.sci.model.mo.FstuAcademicActivity;
import com.pinde.sci.model.mo.PubFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/fstu/academic")
public class AcademicManageController extends GeneralController{
	@Autowired
	private IAcademicBiz academicBiz;
	@Autowired
	private IFileBiz fileBiz;
	@Autowired
	private IFstuFileBiz fstuFileBiz;
	/**
	 * 学术申请查询
	 */
	@RequestMapping(value="/academicApply/{roleFlag}")
	public String academicApply(@PathVariable String roleFlag,Integer currentPage, HttpServletRequest request,FstuAcademicActivity activity,Model model){
		model.addAttribute("roleFlag",roleFlag);
		PageHelper.startPage(currentPage,getPageSize(request));
		if(StringUtil.isNotBlank(roleFlag) && !roleFlag.equals("admin")){
			activity.setApplyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		}
		List<FstuAcademicActivity> academicList = academicBiz.searchAcademicActivity(activity,"academic");
		model.addAttribute("academicList",academicList);
		return "/fstu/acdm/academicApply";
	}

	/**
	 * 新增编辑学术申请界面
     */
	@RequestMapping(value="/addAcademic")
	public String addAcademic(String academicFlow,Model model){
		model.addAttribute("user",GlobalContext.getCurrentUser());
		if(StringUtil.isNotBlank(academicFlow)){
			FstuAcademicActivity acActivity = academicBiz.searchAcademicByFlow(academicFlow);
			model.addAttribute("acActivity",acActivity);
			List<PubFile> fileList = fileBiz.searchByProductFlow(academicFlow);
			model.addAttribute("fileList",fileList);
		}
		return "/fstu/acdm/addAcademicOption";
	}

	/**
	 * 上传文件
	 * @param file
	 * @throws Exception
	 */
	@RequestMapping(value={"/uploadFile"})
	@ResponseBody
	public String uploadFile(MultipartFile file) throws Exception{
		if(file!=null && !file.isEmpty()){
			return academicBiz.saveFileToDirs(file, "fstuAcdm");
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	/**
	 * 学术申请保存操作
     */
	@RequestMapping("/saveAcademic")
	@ResponseBody
	public String saveAcademic(FstuAcademicActivity activity,String filePath){
		int num = academicBiz.saveAcademicActivity(activity,filePath);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	/**
	 * 学术申请保存操作
	 */
	@RequestMapping("/saveAcademicAndFile")
	@ResponseBody
	public String saveAcademicAndFile(String jsondata, HttpServletRequest request){
		FstuAcademicActivityAndFileForm aList = JSON.parseObject(jsondata, FstuAcademicActivityAndFileForm.class);
		FstuAcademicActivity activity = aList.getAcademicActivity();
		//保存附件
		List<PubFile> pubFileList = new ArrayList<>();
		String resultPath = "";
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		List<MultipartFile> fileList = multipartRequest.getFiles("files");
		for (int i = 0; i < fileList.size(); i++) {
			PubFile pubFile = aList.getFileList().get(i);
			if (fileList.get(i) != null && !fileList.get(i).isEmpty()) {
				//保存附件
				String flow = pubFile.getFileFlow();
				if (StringUtil.isNotBlank(flow)) {
					pubFile.setFileFlow(flow);
				}
				resultPath = fstuFileBiz.saveFileToDirs(fileList.get(i), "fstuAcademic", flow);
				pubFile.setFileName(fileList.get(i).getOriginalFilename());
				pubFile.setFilePath(InitConfig.getSysCfg("upload_base_dir") + File.separator + resultPath);
				pubFile.setFileUpType("1");
			}
			pubFileList.add(pubFile);
		}
		int num =academicBiz.saveAcademicAndFiles(activity,pubFileList);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}
	/**
	 * 学术申请删除操作
	 */
	@RequestMapping("/delAcademic")
	@ResponseBody
	public String delAcademic(String academicFlow){
		int num = academicBiz.delAcademicByFlow(academicFlow);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.DELETE_SUCCESSED;
		}
		return GlobalConstant.DELETE_FAIL;
	}

	/**
	 * 学术申请提交操作
	 */
	@RequestMapping("/submitAcademic")
	@ResponseBody
	public String submitAcademic(String academicFlow){
		int num = academicBiz.updateAcademicByFlow(academicFlow);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 申述申请查看操作
	 */
	@RequestMapping("/queryAcademic")
	public String queryAcademic(String academicFlow,Model model){
		if(StringUtil.isNotBlank(academicFlow)){
			FstuAcademicActivity acActivity = academicBiz.searchAcademicByFlow(academicFlow);
			model.addAttribute("acActivity",acActivity);
			/*PubFile pubFile = academicBiz.seachUpFile(academicFlow);
			model.addAttribute("pubFile",pubFile);*/
			List<PubFile> fileList = fileBiz.searchByProductFlow(academicFlow);
			model.addAttribute("fileList",fileList);
		}
		return "/fstu/acdm/queryAcademic";
	}

	/**
	 * 学术申请审核查询
	 */
	@RequestMapping(value="/auditAcademic")
	public String auditAcademic(Integer currentPage, HttpServletRequest request,FstuAcademicActivity activity,String flag,Model model){
		PageHelper.startPage(currentPage,getPageSize(request));
		if(StringUtil.isNotBlank(flag))//初次加载送审后所有状态记录
			activity.setAuditStatusId(flag);
		List<FstuAcademicActivity> academicList = academicBiz.searchAcademicActivity(activity,"academic");
		model.addAttribute("academicList",academicList);
		return "/fstu/acdm/auditAcademic";
	}

	/**
	 * 学术申请审核界面
	 */
	@RequestMapping(value="/auditAcademicOption")
	public String auditAcademicOption(String academicFlow,Model model){
		if(StringUtil.isNotBlank(academicFlow)){
			FstuAcademicActivity acActivity = academicBiz.searchAcademicByFlow(academicFlow);
			model.addAttribute("acActivity",acActivity);
			/*PubFile pubFile = academicBiz.seachUpFile(academicFlow);
			model.addAttribute("pubFile",pubFile);*/
			List<PubFile> fileList = fileBiz.searchByProductFlow(academicFlow);
			model.addAttribute("fileList",fileList);
		}
		return "/fstu/acdm/auditAcademicOption";
	}

	/**
	 * 学术审核同意/退回/保存操作
	 */
	@RequestMapping("/updateAcademicActivity")
	@ResponseBody
	public String updateAcademicActivity(FstuAcademicActivity activity){
		FstuAcademicActivity param = new FstuAcademicActivity();
		if(null != activity){
			if("Passed".equals(activity.getAuditStatusId())){
				param.setAuditStatusId(AcademicAndScoreEnum.AcaAtyPassed.getId());
				param.setAuditStatusName(AcademicAndScoreEnum.AcaAtyPassed.getName());
			}
			else if("Backed".equals(activity.getAuditStatusId()))
			{
				param.setAuditStatusId(AcademicAndScoreEnum.AcaAtyBacked.getId());
				param.setAuditStatusName(AcademicAndScoreEnum.AcaAtyBacked.getName());
			}
			param.setAcademicFlow(activity.getAcademicFlow());
			param.setHeaderAdvice(activity.getHeaderAdvice());
			param.setAdminAdvice(activity.getAdminAdvice());
			param.setAdminFlow(GlobalContext.getCurrentUser().getUserFlow());
			param.setAuditTime(DateUtil.getCurrentTime());
		}
		int num = academicBiz.updateAcademicActivity(param);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 学术报销申请查询
	 */
	@RequestMapping(value="/expenseApply/{roleFlag}")
	public String expenseApply(@PathVariable String roleFlag,Integer currentPage, HttpServletRequest request,FstuAcademicActivity activity,Model model){
		model.addAttribute("roleFlag",roleFlag);
		PageHelper.startPage(currentPage,getPageSize(request));
		if(StringUtil.isNotBlank(roleFlag) && !roleFlag.equals("admin")){
			activity.setApplyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
		}
		activity.setAuditStatusId(AcademicAndScoreEnum.AcaAtyPassed.getId());//学术申请同意后
		List<FstuAcademicActivity> academicList = academicBiz.searchAcademicActivity(activity,"expense");
		model.addAttribute("academicList",academicList);
		return "/fstu/acdm/expenseApply";
	}

	/**
	 * 编辑学术报销申请界面
	 */
	@RequestMapping(value="/expenseReq")
	public String expenseReq(String academicFlow,Model model) {
		if (StringUtil.isNotBlank(academicFlow)) {
			FstuAcademicActivity acActivity = academicBiz.searchAcademicByFlow(academicFlow);
			model.addAttribute("acActivity", acActivity);
			/*PubFile pubFile = academicBiz.seachUpFile(academicFlow);
			model.addAttribute("pubFile",pubFile);*/
			List<PubFile> fileList = fileBiz.searchByProductFlow(academicFlow);
			model.addAttribute("fileList",fileList);
		}
		return "/fstu/acdm/editExpenseOption";
	}

	/**
	 * 学术报销申请提交操作
	 */
	@RequestMapping("/submitExpense")
	@ResponseBody
	public String submitExpense(String academicFlow,String academicSummary){
		int num = academicBiz.updateExpenseByFlow(academicFlow,academicSummary);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 学术报销申请审核查询
	 */
	@RequestMapping(value="/auditExpense")
	public String auditExpense(Integer currentPage, HttpServletRequest request,FstuAcademicActivity activity,String range,String flag,Model model){
		PageHelper.startPage(currentPage,getPageSize(request));
		//初次加载学术申请审核通过且报销申请待审核的所有记录
		if(StringUtil.isNotBlank(range))
			activity.setAuditStatusId(AcademicAndScoreEnum.AcaAtyPassed.getId());
		if(StringUtil.isNotBlank(flag))
			activity.setExpenseStatusId(flag);
		List<FstuAcademicActivity> academicList = academicBiz.searchAcademicActivity(activity,"expense");
		model.addAttribute("academicList",academicList);
		return "/fstu/acdm/auditExpense";
	}

	/**
	 * 学术报销申请审核界面
	 */
	@RequestMapping(value="/auditExpenseOption")
	public String auditExpenseOption(String academicFlow,Model model){
		if(StringUtil.isNotBlank(academicFlow)){
			FstuAcademicActivity acActivity = academicBiz.searchAcademicByFlow(academicFlow);
			model.addAttribute("acActivity",acActivity);
			List<PubFile> fileList = fileBiz.searchByProductFlow(academicFlow);
			model.addAttribute("fileList",fileList);
		}
		return "/fstu/acdm/auditExpenseOption";
	}

	/**
	 * 学术报销审核同意/退回/保存操作
	 */
	@RequestMapping("/auditOption")
	@ResponseBody
	public String auditOption(FstuAcademicActivity activity){
		FstuAcademicActivity param = new FstuAcademicActivity();
		if(null != activity){
			if("Passed".equals(activity.getAuditStatusId())){
				param.setExpenseStatusId(AcademicAndScoreEnum.AcaExpPassed.getId());
				param.setExpenseStatusName(AcademicAndScoreEnum.AcaExpPassed.getName());
			}else if("Backed".equals(activity.getAuditStatusId())){
				param.setExpenseStatusId(AcademicAndScoreEnum.AcaExpBacked.getId());
				param.setExpenseStatusName(AcademicAndScoreEnum.AcaExpBacked.getName());
			}
			param.setAcademicFlow(activity.getAcademicFlow());
			param.setMeetingFee(activity.getMeetingFee());
			param.setMaterialFee(activity.getMaterialFee());
			param.setTrafficFee(activity.getTrafficFee());
			param.setCostumeFee(activity.getCostumeFee());
			param.setFoodFeeWhether(activity.getFoodFeeWhether());
			param.setFoodFee(activity.getFoodFee());
			param.setSubsidyFee(activity.getSubsidyFee());
			param.setOtherFee(activity.getOtherFee());
			param.setSumFee(activity.getSumFee());
			param.setExpenseAdminAdvice(activity.getExpenseAdminAdvice());
			param.setExpenseAdminFlow(GlobalContext.getCurrentUser().getUserFlow());
			param.setExpenseAuditTime(DateUtil.getCurrDateTime());
		}
		int num = academicBiz.updateAcademicActivity(param);
		if (num == GlobalConstant.ONE_LINE) {
			return GlobalConstant.OPRE_SUCCESSED;
		}
		return GlobalConstant.OPRE_FAIL;
	}

	/**
	 * 返回学术报销打印页面
	 */
	@RequestMapping("/printExpense")
	public String printExpense(String academicFlow,Model model){
		if(StringUtil.isNotBlank(academicFlow)){
			FstuAcademicActivity acActivity = academicBiz.searchAcademicByFlow(academicFlow);
			model.addAttribute("acActivity",acActivity);
		}
		return "/fstu/acdm/printInfo";
	}
}