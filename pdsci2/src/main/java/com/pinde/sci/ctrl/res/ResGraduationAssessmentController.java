package com.pinde.sci.ctrl.res;

import com.alibaba.fastjson.JSON;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.res.IResDiscipleInfoBiz;
import com.pinde.sci.biz.res.IResDiscipleTeacherInfoBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResGraduationAssessmentBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.FileUtil;
import com.pinde.sci.enums.res.DiscipleStatusEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.res.ResGraduationAssessmentExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.*;

/**
 * 
 * @author tiger
 *
 */
@Controller
@RequestMapping("/res/graduation")
public class ResGraduationAssessmentController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResGraduationAssessmentController.class);

	@Autowired
	private IResDoctorBiz doctorBiz;
	@Autowired
	private IResDiscipleInfoBiz discipleInfoBiz;
	@Autowired
	private IResDiscipleTeacherInfoBiz teacherInfoBiz;
	@Autowired
	private IResGraduationAssessmentBiz graduationAssessmentBiz;
	@Autowired
	private IFileBiz pubFileBiz;
	/**
	 * 结业考核情况表
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/main/{role}")
	public String main(Model model, @PathVariable String role,String doctorFlow) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(role.equals("student"))
		{
			doctorFlow=currUser.getUserFlow();
		}
		ResGraduationAssessmentExt ext=graduationAssessmentBiz.getDocGraduationAssessment(doctorFlow);
		model.addAttribute("ext",ext);
		ResDoctor doctor=doctorBiz.readDoctor(currUser.getUserFlow());
		model.addAttribute("doctor",doctor);
		//查询附件
		if(StringUtil.isNotBlank(ext.getRecordFlow())){
			List<PubFile> discipleFiles = pubFileBiz.searchByProductFlow(ext.getRecordFlow());
			model.addAttribute("discipleFiles",discipleFiles);
		}
		return "res/disciple/graduationAssessment";
	}
	/**
	 * 结业考核情况表
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/showInfo")
	public String showInfo(Model model,String recordFlow,String role) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		ResGraduationAssessmentWithBLOBs ext=graduationAssessmentBiz.getGraduationAssessmentWithBlobByFlow(recordFlow);
		ResGraduationAssessmentExt ext2=null;
		if(ext!=null) ext2=graduationAssessmentBiz.getDocGraduationAssessment(ext.getDoctorFlow());
		model.addAttribute("ext",ext2);
		return "res/disciple/graduationAssessmentInfo";
	}

	@RequestMapping(value="/save/{role}")
	@ResponseBody
	public String main(Model model, @PathVariable String role,ResGraduationAssessmentWithBLOBs graduationAssessmentWithBLOBs,String statusId,
					   HttpServletRequest request, String jsonData) throws Exception{
		SysUser currUser = GlobalContext.getCurrentUser();
		if(role.equals("student"))//学员提交
		{
			//校验上传文件大小及格式
			String checkResult = checkFiles(request);
			if (!"1".equals(checkResult)) {
				return checkResult;
			}
			if("studentSave".equals(statusId)){
				//状态为空则为保存

			}else if ("studentSub".equals(statusId)){
				//提交状态
				graduationAssessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.Submit.getId());
				graduationAssessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.Submit.getName());
			}

		}
		if(role.equals("teacher"))//老师审核
		{
			if(statusId.equals("tearcherAudit"))
			{
				graduationAssessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.DiscipleAudit.getId());
				graduationAssessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.DiscipleAudit.getName());
			}
			if(statusId.equals("tearcherBack"))
			{
				graduationAssessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.DiscipleBack.getId());
				graduationAssessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.DiscipleBack.getName());
			}
			graduationAssessmentWithBLOBs.setAuditUserFlow(currUser.getUserFlow());
			graduationAssessmentWithBLOBs.setAuditUserName(currUser.getUserName());
		}
		if(role.equals("admin"))//管理员审核
		{
			if(statusId.equals("adminAudit"))
			{
				graduationAssessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.AdminAudit.getId());
				graduationAssessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.AdminAudit.getName());
			}
			if(statusId.equals("adminBack"))
			{
//				graduationAssessmentWithBLOBs.setAdminStatusId(DiscipleStatusEnum.AdminBack.getId());
//				graduationAssessmentWithBLOBs.setAdminStatusName(DiscipleStatusEnum.AdminBack.getName());
				//管理员不通过，把状态更新到学员未提交状态
				graduationAssessmentWithBLOBs.setAuditStatusId(DiscipleStatusEnum.AdminBack.getId());
				graduationAssessmentWithBLOBs.setAuditStatusName(DiscipleStatusEnum.AdminBack.getName());
			}
			graduationAssessmentWithBLOBs.setAdminUserFlow(currUser.getUserFlow());
			graduationAssessmentWithBLOBs.setAdminUserName(currUser.getUserName());
		}
		int count=graduationAssessmentBiz.save(graduationAssessmentWithBLOBs);
		//        学员端多图片上传
		if (role.equals("student")) {
			jsonData = java.net.URLDecoder.decode(jsonData, "UTF-8");
			Map<String, Object> mp = JSON.parseObject(jsonData, Map.class);
			//上传文件的流水号
			List<String> fileFlows = (List<String>) mp.get("fileFlows");
			//处理不在本次保存中的文件
			upadteFileInfo(graduationAssessmentWithBLOBs.getRecordFlow(), fileFlows);
			//处理上传文件
			addUploadFile(graduationAssessmentWithBLOBs.getRecordFlow(), request, "GraduatAssess");
		}
		if(count==GlobalConstant.ONE_LINE)
		{
			return GlobalConstant.SAVE_SUCCESSED;
		}
		return GlobalConstant.SAVE_FAIL;
	}

	/**
	 *
     * @return
     */
	@RequestMapping(value="/docScordImg")
	@ResponseBody
	public String docScordImg(String recordFlow,MultipartFile graduationFile){
		if(graduationFile!=null && graduationFile.getSize() > 0){
			return graduationAssessmentBiz.uploadImg(recordFlow,graduationFile);
		}
		return GlobalConstant.UPLOAD_FAIL;
	}

	//保存上传的附件
	private void addUploadFile(String recordFlow, HttpServletRequest request, String noteTypeId) {
		//以下为多文件上传********************************************
		//创建一个通用的多部分解析器
		List<PubFile> pubFiles=new ArrayList<>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
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
						//保存附件
						PubFile pubFile = new PubFile();
						//取得当前上传文件的文件名称
						String oldFileName = file.getOriginalFilename();
						//如果名称不为“”,说明该文件存在，否则说明该文件不存在
						if (StringUtil.isNotBlank(oldFileName)) {
							//定义上传路径
							String dateString = DateUtil.getCurrDate2();
							String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "discipleFiles" + File.separator + noteTypeId + File.separator + dateString+ File.separator+recordFlow;
							File fileDir = new File(newDir);
							if (!fileDir.exists()) {
								fileDir.mkdirs();
							}
							//重命名上传后的文件名
							String originalFilename = "";
							originalFilename = PkUtil.getUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
							File newFile = new File(fileDir, originalFilename);
							try {
								file.transferTo(newFile);
							} catch (Exception e) {
								e.printStackTrace();
								throw new RuntimeException("保存文件失败！");
							}
							String filePath = File.separator + "discipleFiles" +  File.separator + noteTypeId + File.separator + dateString + File.separator+recordFlow+ File.separator + originalFilename;
							pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
							pubFile.setFilePath(filePath);
							pubFile.setFileName(oldFileName);
							pubFile.setFileSuffix(oldFileName.substring(oldFileName.lastIndexOf(".")));
							pubFile.setProductType(noteTypeId);
							pubFile.setProductFlow(recordFlow);
							pubFiles.add(pubFile);
						}
					}
				}
				//记录上传该文件后的时间
				//int finaltime = (int) System.currentTimeMillis();
			}
		}
		if(pubFiles.size()>0)
		{
			pubFileBiz.saveFiles(pubFiles);
		}
	}

	//处理文件
	private void upadteFileInfo(String recordFlow, List<String> fileFlows) {
		//查询出不在本次保存中的文件信息
		List<PubFile> files=pubFileBiz.searchByProductFlowAndNotInFileFlows(recordFlow,fileFlows);
		//删除服务器中相应的文件
		if(files!=null&&files.size()>0)
		{
			String basePath = InitConfig.getSysCfg("upload_base_dir");
			for (PubFile pubFile : files) {
				if (StringUtil.isNotBlank(pubFile.getFilePath())) {
//					String filePath = basePath + pubFile.getFilePath();
//					FileUtil.deletefile(filePath);
				}
				pubFile.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
				pubFileBiz.editFile(pubFile);
			}
		}
	}
	private String checkFiles(HttpServletRequest request) {
		String result="1";
		ServletContext application = request.getServletContext();
		Map<String, String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
		String imageSuffixStr = sysCfgMap.get("inx_image_support_suffix");
		String[] imageSuffixArr = imageSuffixStr.split(",");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		//判断 request 是否有文件上传,即多部分请求
		if(multipartResolver.isMultipart(request)){
			List<String> fileSuffix=new ArrayList<>();
			fileSuffix.addAll(Arrays.asList(imageSuffixArr));
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

						//取得当前上传文件的文件名称
						String fileName = file.getOriginalFilename();
						String suffix=fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
						if(!fileSuffix.contains(suffix))
						{
							return fileName + "的文件格式不正确，只能上传" + imageSuffixStr + "图片格式的文件。";
						}
						if (file.getSize() > 10 * 1024 * 1024) {
							return fileName+ "的大小超过10M，不得保存";
						}
					}
				}
			}
		}
		return result;
	}
}
